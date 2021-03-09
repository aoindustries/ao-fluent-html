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
 * See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-progress-element">4.10.13 The progress element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Phrasing content, but there must be no progress element descendants.
public class PROGRESS<PC extends Union_Palpable_Phrasing<PC>> extends
	NormalText<PROGRESS<PC>, PC, PROGRESS__<PC>, PROGRESS_c<PC>> implements
	// TODO: value
	// TODO: max
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<PROGRESS<PC>>
{

	public PROGRESS(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected PROGRESS<PC> writeOpen(Writer out) throws IOException {
		document.autoIndent(out).unsafe(out, "<progress", false);
		return this;
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		document.autoIndent(out).unsafe(out, closeAttributes ? "></progress>" : "</progress>", false);
	}

	@Override
	protected PROGRESS__<PC> new__() {
		return new PROGRESS__<>(this);
	}

	@Override
	protected PROGRESS_c<PC> new_c() {
		return new PROGRESS_c<>(this);
	}
}