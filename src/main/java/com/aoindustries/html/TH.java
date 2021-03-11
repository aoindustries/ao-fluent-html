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
 * See <a href="https://html.spec.whatwg.org/multipage/tables.html#the-th-element">4.9.10 The th element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Flow content, but with no header, footer, sectioning content, or heading content descendants.
public class TH<
	D  extends AnyDocument<D>,
	PC extends TR_content<D, PC>
> extends
	NormalText<D, PC, TH<D, PC>, TH__<D, PC>, TH_c<D, PC>> implements
	com.aoindustries.html.attributes.Integer.Colspan<TH<D, PC>>,
	com.aoindustries.html.attributes.Integer.Rowspan<TH<D, PC>>,
	// TODO: headers
	// TODO: scope
	// TODO: abbr
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<TH<D, PC>>
{

	public TH(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected TH<D, PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<th", false);
		return this;
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		document.autoIndent(out).unsafe(out, closeAttributes ? "></th>" : "</th>", false).autoNl(out);
	}

	@Override
	protected TH__<D, PC> new__() {
		return new TH__<>(this);
	}

	@Override
	protected TH_c<D, PC> new_c() {
		return new TH_c<>(this);
	}
}
