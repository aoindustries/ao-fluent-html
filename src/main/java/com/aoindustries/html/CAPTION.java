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
 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-caption-element">4.9.2 The caption element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Flow content, but with no descendant table elements.
public class CAPTION<
	D  extends AnyDocument<D>,
	PC extends TABLE_content<D, PC>
> extends
	NormalText<D, PC, CAPTION<D, PC>, CAPTION__<D, PC>, CAPTION_c<D, PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<CAPTION<D, PC>>
{

	public CAPTION(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected CAPTION<D, PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<caption", false);
		return this;
	}

	@Override
	protected void doBeforeBody(Writer out) throws IOException {
		document.autoNl(out);
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		if(closeAttributes) {
			document.autoIndent(out).unsafe(out, "></caption>", false);
		} else {
			document.autoNli(out).unsafe(out, "</caption>", false);
		}
		document.autoNl(out);
	}

	@Override
	protected CAPTION__<D, PC> new__() {
		return new CAPTION__<>(this);
	}

	@Override
	protected CAPTION_c<D, PC> new_c() {
		return new CAPTION_c<>(this);
	}
}
