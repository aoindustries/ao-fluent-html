/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of ao-fluent-html.
 *
 * ao-fluent-html is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ao-fluent-html is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with ao-fluent-html.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aoindustries.html;

import com.aoindustries.encoding.Coercion;
import com.aoindustries.encoding.MediaEncoder;
import com.aoindustries.encoding.MediaException;
import com.aoindustries.encoding.MediaType;
import com.aoindustries.encoding.MediaWriter;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.encodeTextInXhtmlAttribute;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import com.aoindustries.io.NoCloseWriter;
import com.aoindustries.util.WrappedException;
import java.io.IOException;
import java.util.Locale;

/**
 * See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.
 *
 * @author  AO Industries, Inc.
 */
public class Script extends Element<Script> {

	/**
	 * See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.
	 */
	public enum Type {
		/**
		 * The default type for (X)HTML 5.
		 */
		APPLICATION_JAVASCRIPT("application/javascript"),

		/**
		 * The default type for XHTML 1.0 / HTML 4.
		 */
		TEXT_JAVASCRIPT("text/javascript"),

		/**
		 * A JSON script.
		 */
		APPLICATION_JSON("application/json"),

		/**
		 * A JSON linked data script.
		 */
		APPLICATION_JD_JSON("application/ld+json"),

		APPLICATION_ECMASCRIPT("application/ecmascript");

		private final String contentType;

		private Type(String contentType) {
			this.contentType = contentType;
		}

		@Override
		public String toString() {
			return contentType;
		}

		public String getContentType() {
			return contentType;
		}

		private static boolean assertAllLowerCase() {
			for(Type type : values()) {
				if(!type.contentType.equals(type.contentType.toLowerCase(Locale.ROOT))) throw new AssertionError("Content types must be lowercase as looked-up later");
			}
			return true;
		}
		static {
			assert assertAllLowerCase();
		}
	}

	private final String type;

	public Script(Html html) {
		super(html);
		this.type = null;
	}

	public Script(Html html, String type) {
		super(html);
		this.type = (type == null) ? null : type.toLowerCase(Locale.ROOT);
	}

	public Script(Html html, Type type) {
		super(html);
		this.type = (type == null) ? null : type.getContentType();
	}

	@Override
	protected Script open() throws IOException {
		html.out.write("<script");
		return type();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_script_async.asp">HTML script async Attribute</a>.
	 */
	public Script async(boolean async) throws IOException {
		if(async) {
			if(html.serialization == Serialization.XML) {
				html.out.write(" async=\"async\"");
			} else {
				html.out.write(" async");
			}
		}
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_script_defer.asp">HTML script defer Attribute</a>.
	 */
	public Script defer(boolean defer) throws IOException {
		if(defer) {
			if(html.serialization == Serialization.XML) {
				html.out.write(" defer=\"defer\"");
			} else {
				html.out.write(" defer");
			}
		}
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_script_src.asp">HTML script src Attribute</a>.
	 */
	public Script src(Object src) throws IOException {
		if(src != null) {
			html.out.write(" src=\"");
			// TODO: UrlInXhtmlAttributeEncoder once RFC 3987 supported
			Coercion.write(src, textInXhtmlAttributeEncoder, html.out);
			html.out.write('"');
		}
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.
	 *
	 * @see Doctype#scriptType(java.lang.Appendable)
	 */
	protected Script type() throws IOException {
		if(
			type == null
			|| type.equals(Type.APPLICATION_JAVASCRIPT.getContentType())
			|| type.equals(Type.TEXT_JAVASCRIPT.getContentType())
		) {
			html.doctype.scriptType(html.out);
		} else {
			html.out.write(" type=\"");
			encodeTextInXhtmlAttribute(type, html.out);
			html.out.write('"');
		}
		return this;
	}

	protected MediaType getMediaType() throws IOException {
		try {
			return type == null ? MediaType.JAVASCRIPT : MediaType.getMediaTypeForContentType(type);
		} catch(MediaException e) {
			throw new IOException(e);
		}
	}

	protected MediaEncoder getMediaEncoder(MediaType mediaType) throws IOException {
		try {
			return MediaEncoder.getInstance(null, mediaType, MediaType.XHTML);
		} catch(MediaException e) {
			throw new IOException(e);
		}
	}

	private boolean didBody;

	protected void startBody() throws IOException {
		if(!didBody) {
			html.out.write(">\n");
			cdata.start();
			didBody = true;
		}
	}

	public Script out(Object script) throws IOException {
		if(script != null) {
			if(script instanceof ScriptWriter) return out((ScriptWriter)script);
			if(script instanceof ScriptWriterE) {
				try {
					return outE((ScriptWriterE<?>)script);
				} catch(Error|RuntimeException|IOException e) {
					throw e;
				} catch(Throwable t) {
					throw new WrappedException(t);
				}
			}
			MediaType mediaType = getMediaType();
			MediaEncoder encoder = getMediaEncoder(mediaType);
			startBody();
			// Allow text markup from translations
			Coercion.write(script, mediaType.getMarkupType(), encoder, false, html.out);
		}
		return this;
	}

	/**
	 * Writes the script, automatically closing the script via
	 * {@link #__()} on {@link MediaWriter#close()}.  This is well suited
	 * for use in a try-with-resources block.
	 */
	public MediaWriter out() throws IOException {
		MediaEncoder encoder = getMediaEncoder(getMediaType());
		startBody();
		return new MediaWriter(encoder, html.out) {
			@Override
			public void close() throws IOException {
				__();
			}
		};
	}

	// Java 1.8: @Functional
	public static interface ScriptWriterE<Ex extends Throwable> {
		void writeScript(MediaWriter script) throws IOException, Ex;
	}

	public <Ex extends Throwable> Script outE(ScriptWriterE<Ex> script) throws IOException, Ex {
		if(script != null) {
			MediaEncoder encoder = getMediaEncoder(getMediaType());
			startBody();
			script.writeScript(
				new MediaWriter(
					encoder,
					new NoCloseWriter(html.out)
				)
			);
		}
		return this;
	}

	// Java 1.8: @Functional
	public static interface ScriptWriter extends ScriptWriterE<RuntimeException> {
		@Override
		void writeScript(MediaWriter script) throws IOException;
	}

	public Script out(ScriptWriter script) throws IOException {
		return outE(script);
	}

	// TODO: Hide cdata?
	public class Cdata {
		public Script start(String indent) throws IOException {
			if(
				html.serialization == Serialization.XML
				&& (
					Type.APPLICATION_JAVASCRIPT.getContentType().equals(type)
					|| Type.TEXT_JAVASCRIPT.getContentType().equals(type)
				)
			) {
				if(indent != null) html.out.write(indent);
				html.out.write("// <![CDATA[\n");
			}
			return Script.this;
		}
		public Script start() throws IOException {
			return start(null);
		}
		public Script end(String indent) throws IOException {
			if(
				html.serialization == Serialization.XML
				&& (
					Type.APPLICATION_JAVASCRIPT.getContentType().equals(type)
					|| Type.TEXT_JAVASCRIPT.getContentType().equals(type)
				)
			) {
				if(indent != null) html.out.write(indent);
				html.out.write("// ]]>\n");
			}
			return Script.this;
		}
		public Script end() throws IOException {
			return end(null);
		}
	}
	public final Cdata cdata = new Cdata();

	public Html __() throws IOException {
		if(!didBody) {
			html.out.write("></script>");
		} else {
			// TODO: Track what was written and avoid unnecessary newline?
			html.nl();
			cdata.end();
			html.out.write("</script>");
		}
		return html;
	}
}
