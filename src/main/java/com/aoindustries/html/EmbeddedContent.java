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

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#embedded-content-2">3.2.5.2.6 Embedded content</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories#embedded_content">Embedded content</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public interface EmbeddedContent<PC extends EmbeddedContent<PC>> extends
	//
	// Unions:
	//
	UnionContent.Embedded_Interactive<PC>,
	UnionContent.Embedded_Palpable_Phrasing<PC>,
	//
	// Content models:
	//
	// Inherited from Embedded_Interactive and Embedded_Palpable_Phrasing: Content,
	//
	// Content types:
	//
	// Inherited from Embedded_Interactive: Contents.Embedded.Audio<PC>,
	// Inherited from Embedded_Palpable_Phrasing: Contents.Scripting.Canvas<PC>,
	// Inherited from Embedded_Interactive: Contents.Embedded.Embed<PC>,
	// Inherited from Embedded_Interactive: Contents.Embedded.Iframe<PC>,
	// Inherited from Embedded_Interactive: Contents.Embedded.Img<PC>,
	// Inherited from Embedded_Palpable_Phrasing: // TODO: MathML math
	// Inherited from Embedded_Interactive: Contents.Embedded.Object<PC>,
	Contents.Embedded.Picture<PC>
	// Inherited from Embedded_Palpable_Phrasing: // TODO: SVG svg
	// Inherited from Embedded_Interactive: Contents.Embedded.Video<PC>
{
}
