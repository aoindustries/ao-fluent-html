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
 * A transparent element that can have textual content.
 * <p>
 * See <a href="https://html.spec.whatwg.org/#transparent-content-models">3.2.5.3 Transparent content models</a>.
 * </p>
 *
 * @param  <PC>  The parent content model this element is within,
 *               which may also be the parent content model of child elements
 * @param  <_c>  This content model as {@link Closeable}, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
abstract public class TransparentText<
	E  extends TransparentText<E, PC, _c>,
	PC extends Content<PC>,
	// Would prefer "_c extends PC & Closeable<PC>", but "a type variable may not be followed by other bounds"
	_c extends TransparentText_c<PC, _c>
> extends Transparent<E, PC, _c> {

	protected TransparentText(Document document, PC pc) {
		super(document, pc);
	}

	/**
	 * Ends attributes, writes a text body, then closes this element.
	 *
	 * @return  The parent content model this element is within
	 *
	 * @see  Document#text(java.lang.Object)
	 */
	// Matches NormalText.__(Object)
	public PC __(Object text) throws IOException {
		Writer out = document.getUnsafe(null);
		if(text != null) {
			document.autoIndent(out).unsafe(out, '>').incDepth();
			doBeforeBody(out);
			document.text(out, text).decDepth();
			writeClose(out, false);
		} else {
			writeClose(out, true);
		}
		return pc;
	}
}