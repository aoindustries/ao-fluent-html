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

import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/tag_br.asp">HTML br tag</a>.
 *
 * @author  AO Industries, Inc.
 */
public class Br extends EmptyElement<Br>
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// Not on <br>: Attributes.Event.Mouse.Events<Br>
{

	public Br(Html html) {
		super(html);
	}

	@Override
	protected Br open() throws IOException {
		html.out.write("<br");
		return this;
	}
}