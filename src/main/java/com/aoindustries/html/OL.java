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

/**
 * See <a href="https://html.spec.whatwg.org/#the-ol-element">4.4.5 The ol element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class OL<PC extends PalpableContent<PC>> extends
	Normal<OL<PC>, PC, OL__<PC>, OL_c<PC>> implements
	// TOOD: reversed
	// TODO: start
	// TODO: type
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<OL<PC>>
{

	public OL(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected OL<PC> writeOpen() throws IOException {
		document.out.write("<ol");
		return this;
	}

	@Override
	protected void writeClose(boolean closeAttributes) throws IOException {
		document.out.write(closeAttributes ? "></ol>" : "</ol>");
	}

	@Override
	protected OL__<PC> new__() {
		return new OL__<>(this);
	}

	@Override
	protected OL_c<PC> new_c() {
		return new OL_c<>(this);
	}
}