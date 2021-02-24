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
 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class H2<PC extends HeadingContent<PC>> extends
	NormalTextElement<H2<PC>, PC, H2.H2Content<PC>, H2.H2CloseableContent<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<H2<PC>>
{

	public static class H2Content<PC extends HeadingContent<PC>> extends
		NormalTextContent<PC, H2Content<PC>> implements
		PhrasingContent<H2Content<PC>> {

		protected H2Content(H2<PC> element) {
			super(element);
		}
	}

	public static class H2CloseableContent<PC extends HeadingContent<PC>> extends
		CloseableNormalTextContent<PC, H2CloseableContent<PC>> implements
		PhrasingContent<H2CloseableContent<PC>> {

		protected H2CloseableContent(H2<PC> element) {
			super(element);
		}
	}

	public H2(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected H2<PC> writeOpen() throws IOException {
		document.out.write("<h2");
		return this;
	}

	@Override
	protected void writeClose() throws IOException {
		document.out.write("</h2>");
	}

	@Override
	protected H2Content<PC> newC() {
		return new H2Content<>(this);
	}

	@Override
	protected H2CloseableContent<PC> newCC() {
		return new H2CloseableContent<>(this);
	}
}
