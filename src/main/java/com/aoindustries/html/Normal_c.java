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
 * See <a href="https://html.spec.whatwg.org/#normal-elements">13.1.2 Elements / Normal elements</a>.
 *
 * @param  <PC>  The parent content model this element is within
 * @param  <_c>  This content model as {@link Closeable}, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
// TODO: Can this extend Normal__?  Should it?
public abstract class Normal_c<
	PC extends Content<PC>,
	_c extends Normal_c<PC, _c>
> implements Content<_c>, Closeable<PC> {

	protected final Normal<?, PC, ?, _c> element;

	protected Normal_c(Normal<?, PC, ?, _c> element) {
		this.element = element;
	}

	@Override
	public Document getDocument() {
		return element.document;
	}

	@Override
	public PC __() throws IOException {
		Document document = element.document;
		document.decDepth();
		element.writeClose(document.getUnsafe(null), false);
		return element.pc;
	}
}
