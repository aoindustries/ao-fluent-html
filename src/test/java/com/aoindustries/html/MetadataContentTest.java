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

import com.aoindustries.collections.AoArrays;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see  MetadataContent
 *
 * @author  AO Industries, Inc.
 */
public class MetadataContentTest {

	@Test
	@SuppressWarnings("unchecked")
	public void testUnions() {
		UnionContentTest.testUnions(
			MetadataContent.class,
			//
			// Unions:
			//
			UnionContent.COLGROUP_ScriptSupporting.class,
			UnionContent.Metadata_Phrasing.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testContentModels() {
		ContentModelTest.testContentModels(
			MetadataContent.class,
			//
			// Content models:
			//
			Content.class,
			ScriptSupportingContent.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testElementContentModels() {
		ElementContentModelTest.testElementContentModels(
			MetadataContent.class
			//
			// Per-element content models:
			//
			// None
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testFactories() {
		FactoryTest.testFactories(
			MetadataContent.class,
			//
			// Factories:
			//
			BASE_factory.class,
			LINK_factory.class,
			META_factory.class,
			NOSCRIPT_factory.class,
			SCRIPT_factory.class,
			STYLE_factory.class,
			TEMPLATE_factory.class, // WHATWG only
			TITLE_factory.class
		);
	}

	@Test
	public void testNoImplementInherited() {
		Assert.assertNotEquals(
			"Must be included in " + ContentModelTest.class.getName() + ".getAllContentModels()",
			-1,
			AoArrays.indexOf(ContentModelTest.getAllContentModels(), MetadataContent.class)
		);
		InheritanceTests.testNoImplementInherited(MetadataContent.class);
	}
}
