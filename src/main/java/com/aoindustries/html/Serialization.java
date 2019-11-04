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

import com.aoindustries.util.StringUtility;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * The type of serialization ({@link #SGML} or {@link #XML}).
 *
 * @author  AO Industries, Inc.
 */
public enum Serialization {
	SGML {
		@Override
		public String getContentType() {
			return Html.CONTENT_TYPE_HTML;
		}

		@Override
		public String getSelfClose() {
			return ">";
		}

		// Override to write single character instead of string
		@Override
		public Serialization selfClose(Appendable out) throws IOException {
			out.append('>');
			return this;
		}
	},
	XML {
		@Override
		public String getContentType() {
			return Html.CONTENT_TYPE_XHTML;
		}

		@Override
		public String getSelfClose() {
			return " />";
		}
	};

	/**
	 * Gets the content-type header to use for this serialization.
	 */
	abstract public String getContentType();

	/**
	 * Gets the self-closing tag characters.
	 */
	abstract public String getSelfClose();

	/**
	 * Appends the self-closing tag characters.
	 */
	public Serialization selfClose(Appendable out) throws IOException {
		out.append(getSelfClose());
		return this;
	}

	/**
	 * Determine if the content may be served as <code>application/xhtml+xml</code> by the
	 * rules defined in <a href="http://www.w3.org/TR/xhtml-media-types/">http://www.w3.org/TR/xhtml-media-types/</a>
	 * Default to <code>application/xhtml+xml</code> as discussed at
	 * <a href="https://web.archive.org/web/20080913043830/http://www.smackthemouse.com/xhtmlxml">http://www.smackthemouse.com/xhtmlxml</a>
	 */
	public static Serialization select(Iterator<? extends String> acceptHeaderValues) {
		// Some test accept headers:
		//   Firefox: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5
		//   IE 6: */*
		//   IE 8: */*
		//   IE 8 Compat: */*

		boolean hasAcceptHeader = false;
		boolean hasAcceptApplicationXhtmlXml = false;
		boolean hasAcceptTextHtml = false;
		boolean hasAcceptStarStar = false;
		if(acceptHeaderValues != null) {
			while(acceptHeaderValues.hasNext()) {
				hasAcceptHeader = true;
				for(String value : StringUtility.splitString(acceptHeaderValues.next(), ',')) {
					value = value.trim();
					List<String> params = StringUtility.splitString(value, ';');
					int paramsSize = params.size();
					if(paramsSize > 0) {
						String acceptType = params.get(0).trim();
						if(acceptType.equals("*/*")) {
							// No q parameter parsing for */*
							hasAcceptStarStar = true;
						} else if(
							// Parse and check the q for these two types
							acceptType.equalsIgnoreCase(Html.CONTENT_TYPE_XHTML)
							|| acceptType.equalsIgnoreCase(Html.CONTENT_TYPE_HTML)
						) {
							// Find any q value
							boolean hasNegativeQ = false;
							for(int paramNum = 1; paramNum < paramsSize; paramNum++) {
								String paramSet = params.get(paramNum).trim();
								if(paramSet.startsWith("q=") || paramSet.startsWith("Q=")) {
									try {
										float q = Float.parseFloat(paramSet.substring(2).trim());
										if(q < 0) {
											hasNegativeQ = true;
											break;
										}
									} catch(NumberFormatException err) {
										// Intentionally ignored
									}
								}
							}
							if(!hasNegativeQ) {
								if(acceptType.equalsIgnoreCase(Html.CONTENT_TYPE_XHTML)) hasAcceptApplicationXhtmlXml = true;
								else if(acceptType.equalsIgnoreCase(Html.CONTENT_TYPE_HTML)) hasAcceptTextHtml = true;
								else throw new AssertionError("Unexpected value for acceptType: " + acceptType);
							}
						}
					}
				}
			}
		}
		// If the Accept header explicitly contains application/xhtml+xml  (with either no "q" parameter or a positive "q" value) deliver the document using that media type.
		if(hasAcceptApplicationXhtmlXml) return XML;
		// If the Accept header explicitly contains text/html  (with either no "q" parameter or a positive "q" value) deliver the document using that media type.
		if(hasAcceptTextHtml) return SGML;
		// If the accept header contains "*/*" (a convention some user agents use to indicate that they will accept anything), deliver the document using text/html.
		if(hasAcceptStarStar) return SGML;
		// If has no accept headers
		if(!hasAcceptHeader) return XML;
		// This choice is not clear from either of the cited documents.  If there is an accept line,
		// and it doesn't have */* or application/xhtml+xml or text/html, we'll serve as text/html
		// since it is a fairly broken client anyway and would be even less likely to know xhtml.
		return SGML;
	}

	public static Serialization select(Iterable<? extends String> acceptHeaderValues) {
		return select(acceptHeaderValues.iterator());
	}

	public static Serialization select(Enumeration<? extends String> acceptHeaderValues) {
		// TODO: commons-collections EnumerationIterator?  Worth adding dependency for one method?
		return select(
			new Iterator<String>() {
				@Override
				public boolean hasNext() {
					return acceptHeaderValues.hasMoreElements();
				}
				@Override
				public String next() {
					return acceptHeaderValues.nextElement();
				}
				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}
			}
		);
	}
}