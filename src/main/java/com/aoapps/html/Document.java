/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020, 2021  AO Industries, Inc.
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
package com.aoapps.html;

import com.aoapps.encoding.ChainWriter;
import com.aoapps.encoding.Doctype;
import com.aoapps.encoding.EncodingContext;
import com.aoapps.encoding.Serialization;
import com.aoapps.html.any.AnyDocument;
import java.io.Writer;

/**
 * Fluent Java DSL for high-performance HTML generation.
 * <p>
 * This class implements all content interfaces and supports all element types.
 * </p>
 * <p>
 * See also <a href="https://github.com/xmlet/HtmlFlow">HtmlFlow</a>.
 * </p>
 *
 * @author  AO Industries, Inc.
 */
final public class Document extends AnyDocument<Document> implements AnyContent<Document> {

	/**
	 * @param  out  May be {@code null}, but must be set to a non-null value again before any additional writes.
	 *              Not doing so may result in {@link IllegalStateException}.
	 *
	 * @see  #setOut(java.io.Writer)
	 */
	public Document(EncodingContext encodingContext, Serialization serialization, Doctype doctype, Writer out) {
		super(encodingContext, serialization, doctype, out);
	}

	/**
	 * @param  out  May be {@code null}, but must be set to a non-null value again before any additional writes.
	 *              Not doing so may result in {@link IllegalStateException}.
	 *
	 * @see  #setOut(java.io.Writer)
	 */
	public Document(Serialization serialization, Doctype doctype, Writer out) {
		this(
			new EncodingContext() {
				@Override
				public Serialization getSerialization() {
					return serialization;
				}
				@Override
				public Doctype getDoctype() {
					return doctype;
				}
			},
			serialization,
			doctype,
			out
		);
	}

	/**
	 * @param  out  May be {@code null}, but must be set to a non-null value again before any additional writes.
	 *              Not doing so may result in {@link IllegalStateException}.
	 *
	 * @see  #setOut(java.io.Writer)
	 */
	public Document(EncodingContext encodingContext, Writer out) {
		this(
			encodingContext,
			encodingContext.getSerialization(),
			encodingContext.getDoctype(),
			out
		);
	}

	/**
	 * @param  out  May be {@code null}, but must be set to a non-null value again before any additional writes.
	 *              Not doing so may result in {@link IllegalStateException}.
	 *
	 * @see  #setOut(java.io.Writer)
	 * @see  EncodingContext#DEFAULT
	 */
	public Document(Writer out) {
		this(EncodingContext.DEFAULT, out);
	}

	/**
	 * Unwraps the given chain writer.
	 */
	public Document(ChainWriter out) {
		this(out.getEncodingContext(), out.getPrintWriter());
	}
}
