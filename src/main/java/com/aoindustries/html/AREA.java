/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2020, 2021  AO Industries, Inc.
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
import java.util.function.Function;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class AREA<
	D  extends AnyDocument<D>,
	PC extends PhrasingContent<D, PC>
> extends VoidElement<D, PC, AREA<D, PC>> implements
	com.aoindustries.html.attributes.Text.Alt<AREA<D, PC>>,
	com.aoindustries.html.attributes.Dimension.Coords<AREA<D, PC>>,
	// TODO: download
	com.aoindustries.html.attributes.Url.Href<AREA<D, PC>>,
	com.aoindustries.html.attributes.String.Hreflang<AREA<D, PC>>,
	com.aoindustries.html.attributes.Text.Media<AREA<D, PC>>,
	// TODO: name? (MDN only)
	// TODO: nohref
	// TODO: ping
	// TODO: referrerpolicy
	com.aoindustries.html.attributes.Enum.Rel<AREA<D, PC>, A.Rel>,
	com.aoindustries.html.attributes.Enum.Shape<AREA<D, PC>, AREA.Shape>,
	com.aoindustries.html.attributes.Enum.Target<AREA<D, PC>, com.aoindustries.html.attributes.Enum.Target.Value>,
	// TODO: type (deprecated since definition is in conflict and doesn't do anything?)
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	com.aoindustries.html.attributes.Integer.TabindexHtml4<AREA<D, PC>>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<AREA<D, PC>>
{

	public AREA(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected AREA<D, PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<area", false);
		return this;
	}

	@Override
	protected void doAfterElement(Writer out) throws IOException {
		document.autoNl(out);
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-shape">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_area_shape.asp">HTML area shape Attribute</a>.</li>
	 * </ul>
	 */
	public enum Shape implements Function<AnyDocument<?>, String> {
		DEFAULT("default"),
		RECT("rect"),
		CIRCLE("circle"),
		POLY("poly");

		private final String value;

		private Shape(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}

		@Override
		public String apply(AnyDocument<?> document) {
			return value;
		}
	}
}
