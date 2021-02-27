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

import com.aoindustries.io.function.IOConsumerE;
import com.aoindustries.io.function.IORunnableE;
import java.io.IOException;

/**
 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface U_factory<__ extends UnionContent.Palpable_Phrasing<__>> extends Content<__> {

	/**
	 * Opens a new u element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
	 * </p>
	 */
	default U<__> u() throws IOException {
		@SuppressWarnings(value = "unchecked")
		__ pc = (__) this;
		return new U<>(getDocument(), pc).writeOpen();
	}

	/**
	 * Creates a u element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default<Ex extends Throwable> __ u__(IORunnableE<Ex> u) throws IOException, Ex {
		return u().__(u);
	}

	/**
	 * Creates a u element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default<Ex extends Throwable> __ u__(IOConsumerE<? super U__<__>, Ex> u) throws IOException, Ex {
		return u().__(u);
	}

	/**
	 * Creates a u element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ u__(Object text) throws IOException {
		return u().__(text);
	}

	/**
	 * Creates an empty u element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ u__() throws IOException {
		return u().__();
	}
}