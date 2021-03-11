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
 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-strong-element">4.5.3 The strong element</a>.
 *
 * @param  <D>   This document type
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface STRONG_factory<
	D  extends AnyDocument<D>,
	__ extends Union_Palpable_Phrasing<D, __>
> extends Content<D, __> {

	/**
	 * Opens a new strong element.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-strong-element">4.5.3 The strong element</a>.
	 * </p>
	 */
	default STRONG<D, __> strong() throws IOException {
		@SuppressWarnings("unchecked")
		__ pc = (__)this;
		D document = getDocument();
		return new STRONG<>(document, pc).writeOpen(document.getUnsafe(null));
	}

	/**
	 * Creates a strong element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-strong-element">4.5.3 The strong element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ strong__(IORunnableE<Ex> strong) throws IOException, Ex {
		return strong().__(strong);
	}

	/**
	 * Creates a strong element with no attributes and the given body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-strong-element">4.5.3 The strong element</a>.
	 * </p>
	 *
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default <Ex extends Throwable> __ strong__(IOConsumerE<? super STRONG__<D, __>, Ex> strong) throws IOException, Ex {
		return strong().__(strong);
	}

	/**
	 * Creates a strong element with no attributes and a text body.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-strong-element">4.5.3 The strong element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ strong__(Object text) throws IOException {
		return strong().__(text);
	}

	/**
	 * Creates an empty strong element with no attributes.
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-strong-element">4.5.3 The strong element</a>.
	 * </p>
	 *
	 * @return  This content model, which will be the parent content model of child elements
	 */
	default __ strong__() throws IOException {
		return strong().__();
	}

	/**
	 * Creates a strong element with no attributes then begins element content
	 * <p>
	 * See <a href="https://html.spec.whatwg.org/multipage/text-level-semantics.html#the-strong-element">4.5.3 The strong element</a>.
	 * </p>
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 */
	default STRONG_c<D, __> strong_c() throws IOException {
		return strong()._c();
	}
}
