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
 * See <a href="https://html.spec.whatwg.org/multipage/grouping-content.html#the-pre-element">4.4.3 The pre element</a>.
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class PRE<
	D  extends AnyDocument<D>,
	PC extends PalpableContent<D, PC>
> extends
	NormalText<D, PC, PRE<D, PC>, PRE__<D, PC>, PRE_c<D, PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<PRE<D, PC>>
{

	private boolean oldAutonli;
	private boolean oldIndent;

	public PRE(D document, PC pc) {
		super(document, pc);
	}

	/**
	 * Does not have indented content.
	 *
	 * @return {@code false} - does not indent
	 */
	@Override
	protected boolean isContentIndented() {
		return false;
	}

	@Override
	protected PRE<D, PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<pre", false);
		return this;
	}

	@Override
	protected void doBeforeBody(Writer out) throws IOException {
		oldAutonli = document.getAutonli();
		if(oldAutonli) document.setAutonli(false);
		oldIndent = document.getIndent();
		if(oldIndent) document.setIndent(false);
	}

	@Override
	protected void writeClose(Writer out, boolean closeAttributes) throws IOException {
		document
			.setIndent(oldIndent)
			.setAutonli(oldAutonli);
		if(closeAttributes) {
			document.autoIndent(out).unsafe(out, "></pre>", false);
		} else {
			document.unsafe(out, "</pre>", false);
		}
		document.autoNl(out);
	}

	@Override
	protected PRE__<D, PC> new__() {
		return new PRE__<>(this);
	}

	@Override
	protected PRE_c<D, PC> new_c() {
		return new PRE_c<>(this);
	}
}
