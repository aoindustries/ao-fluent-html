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

import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.encodeTextInXhtmlAttribute;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * @author  AO Industries, Inc.
 */
public enum Doctype {
	// See http://www.ibm.com/developerworks/library/x-think45/
	HTML5 {
		@Override
		public String getDoctype(Serialization serialization) {
			return "<!DOCTYPE html>\n";
		}
		@Override
		public String getScriptType() {
			return "";
		}
		@Override
		public Doctype scriptType(Appendable out) throws IOException {
			// Do nothing
			return this;
		}
		@Override
		public String getStyleType() {
			return "";
		}
		@Override
		public Doctype styleType(Appendable out) throws IOException {
			// Do nothing
			return this;
		}
	},
	STRICT {
		@Override
		public String getDoctype(Serialization serialization) {
			switch(serialization) {
				case SGML:
					return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n";
				case XML:
					return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n";
				default:
					throw new AssertionError();
			}
		}
		@Override
		public String getScriptType() {
			return " type=\"text/javascript\"";
		}
		@Override
		public String getStyleType() {
			return " type=\"" + Style.Type.TEXT_CSS.getContentType() + "\"";
		}
		@Override
		public Doctype styleType(Appendable out) throws IOException {
			out.append(" type=\"");
			out.append(Style.Type.TEXT_CSS.getContentType());
			out.append('"');
			return this;
		}
	},
	TRANSITIONAL {
		@Override
		public String getDoctype(Serialization serialization) {
			switch(serialization) {
				case SGML:
					return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n";
				case XML:
					return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n";
				default:
					throw new AssertionError();
			}
		}
		@Override
		public String getScriptType() {
			return STRICT.getScriptType();
		}
		@Override
		public String getStyleType() {
			return STRICT.getStyleType();
		}
	},
	FRAMESET {
		@Override
		public String getDoctype(Serialization serialization) {
			switch(serialization) {
				case SGML:
					return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\" \"http://www.w3.org/TR/html4/frameset.dtd\">\n";
				case XML:
					return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Frameset//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd\">\n";
				default:
					throw new AssertionError();
			}
		}
		@Override
		public String getScriptType() {
			return STRICT.getScriptType();
		}
		@Override
		public String getStyleType() {
			return STRICT.getStyleType();
		}
	},
	NONE {
		@Override
		public String getXmlDeclaration(Serialization serialization, String documentEncoding) {
			return "";
		}
		@Override
		public Doctype xmlDeclaration(Serialization serialization, String documentEncoding, Appendable out) {
			// Do nothing
			return this;
		}
		@Override
		public String getDoctype(Serialization serialization) {
			return "";
		}
		@Override
		public Doctype doctype(Serialization serialization, Appendable out) throws IOException {
			// Do nothing
			return this;
		}
		@Override
		public String getScriptType() {
			// Very old doctype-less, support IE6: http://www.javascriptkit.com/javatutors/languageattri3.shtml
			return " language=\"JavaScript1.3\"";
		}
		@Override
		public String getStyleType() {
			return STRICT.getStyleType();
		}
	};

	private static boolean isUTF8(String documentEncoding) {
		return
			StandardCharsets.UTF_8.name().equalsIgnoreCase(documentEncoding)
			|| Charset.forName(documentEncoding) == StandardCharsets.UTF_8;
	}

	public String getXmlDeclaration(Serialization serialization, String documentEncoding) {
		try {
			StringBuilder sb = new StringBuilder();
			xmlDeclaration(serialization, documentEncoding, sb);
			return sb.toString();
		} catch(IOException e) {
			throw new AssertionError("IOException should not occur on StringBuilder", e);
		}
	}

	public Doctype xmlDeclaration(Serialization serialization, String documentEncoding, Appendable out) throws IOException {
		if(serialization == Serialization.XML && !isUTF8(documentEncoding)) {
			out.append("<?xml version=\"1.0\" encoding=\"");
			encodeTextInXhtmlAttribute(documentEncoding, out);
			out.append("\"?>\n");
		}
		return this;
	}

	/**
	 * Gets the <a href="https://www.w3schools.com/tags/tag_doctype.asp">HTML doctype declaration</a> line.
	 */
	public abstract String getDoctype(Serialization serialization);

	/**
	 * Appends the <a href="https://www.w3schools.com/tags/tag_doctype.asp">HTML doctype declaration</a> line, if any.
	 */
	public Doctype doctype(Serialization serialization, Appendable out) throws IOException {
		out.append(getDoctype(serialization));
		return this;
	}

	/**
	 * Gets the default script type/language attribute, if any.
	 */
	abstract public String getScriptType();

	/**
	 * Appends the default script type/language attribute, if any.
	 */
	public Doctype scriptType(Appendable out) throws IOException {
		out.append(getScriptType());
		return this;
	}

	/**
	 * Gets the default style type attribute, if any.
	 */
	abstract public String getStyleType();

	/**
	 * Appends the default style type attribute, if any.
	 */
	public Doctype styleType(Appendable out) throws IOException {
		out.append(getStyleType());
		return this;
	}
}
