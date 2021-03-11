/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021  AO Industries, Inc.
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

import java.io.IOException;
import java.io.Writer;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-output-element">4.10.12 The output element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class OUTPUT<
	D  extends AnyDocument<D>,
	PC extends Union_Palpable_Phrasing<D, PC>
> extends
	NormalText<D, PC, OUTPUT<D, PC>, OUTPUT__<D, PC>, OUTPUT_c<D, PC>> implements
	// TODO: for
	// TODO: form
	com.aoindustries.html.attributes.Text.Name<OUTPUT<D, PC>>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<OUTPUT<D, PC>>
{

	public OUTPUT(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected OUTPUT<D, PC> writeOpen(Writer out) throws IOException {
		document.autoIndent(out).unsafe(out, "<output", false);
		return this;
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		document.autoIndent(out).unsafe(out, closeAttributes ? "></output>" : "</output>", false);
	}

	@Override
	protected OUTPUT__<D, PC> new__() {
		return new OUTPUT__<>(this);
	}

	@Override
	protected OUTPUT_c<D, PC> new_c() {
		return new OUTPUT_c<>(this);
	}
}
