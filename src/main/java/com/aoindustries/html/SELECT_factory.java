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
 * See <a href="https://html.spec.whatwg.org/#the-select-element">4.10.7 The select element</a>.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface SELECT_factory<__ extends Union_Interactive_Phrasing<__>> extends Content<__> {

	/**
	 * Opens a new select element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-select-element">4.10.7 The select element</a>.
	 * </p>
	 */
	default SELECT<__> select() throws IOException {
		@SuppressWarnings(value = "unchecked")
		__ pc = (__)this;
		Document document = getDocument();
		return new SELECT<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a select element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-select-element">4.10.7 The select element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ select__(IORunnableE<Ex> select) throws IOException, Ex {
		return select().__(select);
	}

	/**
	 * Creates a select element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-select-element">4.10.7 The select element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ select__(IOConsumerE<? super SELECT__<__>, Ex> select) throws IOException, Ex {
		return select().__(select);
	}

	/**
	 * Creates an empty select element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-select-element">4.10.7 The select element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ select__() throws IOException {
		return select().__();
	}

	/**
	 * Creates a select element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/#the-select-element">4.10.7 The select element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	default SELECT_c<__> select_c() throws IOException {
		return select()._c();
	}
}
