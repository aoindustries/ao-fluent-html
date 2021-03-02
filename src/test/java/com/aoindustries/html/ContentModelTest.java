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
import com.aoindustries.lang.reflect.Classes;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests <code>*Content</code> content model interfaces,
 * which confirm a class implements the expected set of interfaces.
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ContentModelTest {

	/**
	 * Gets the set of all <code>*Content</code> content model interfaces.
	 */
	static Class<? extends Content>[] getAllContentModels() {
		return new Class[] {
			Content.class,
			EmbeddedContent.class,
			FlowContent.class,
			HeadingContent.class,
			InteractiveContent.class,
			MetadataContent.class,
			PalpableContent.class,
			PhrasingContent.class,
			ScriptSupportingContent.class,
			SectioningContent.class,
			TextContent.class
		};
	}

	static void testContentModels(Class<? extends Content> clazz, Class<? extends Content> ... expected) {
		// Check parameters
		for(Class<? extends Content> iface : expected) {
			Assert.assertTrue(iface.isInterface());
			Assert.assertTrue(iface.getName().endsWith("Content"));
		}
		// First make sure has all the expected
		for(Class<? extends Content> iface : expected) {
			Assert.assertTrue(
				clazz.getName() + " must be assignable to " + iface.getName(),
				iface.isAssignableFrom(clazz)
			);
		}
		// Next make sure no unexpected by pattern
		for(Class<? extends Content> iface : Classes.getAllClasses(clazz, Content.class)) {
			Assert.assertTrue(iface.isAssignableFrom(clazz));
			if(iface != clazz) {
				Assert.assertTrue(iface.isInterface());
				if(iface.getName().endsWith("Content")) {
					Assert.assertNotEquals(
						clazz.getName() + " may not implement " + iface.getName(),
						-1,
						AoArrays.indexOf(expected, iface)
					);
				}
			}
		}
		// Next make sure no unexpected versus master list
		for(Class<? extends Content> iface : getAllContentModels()) {
			if(iface != clazz && AoArrays.indexOf(expected, iface) == -1) {
				Assert.assertFalse(
					clazz.getName() + " may not be assignable to " + iface.getName(),
					iface.isAssignableFrom(clazz)
				);
			}
		}
	}

	@Test
	public void testNoImplementInherited() {
		for(Class<? extends Content> iface : getAllContentModels()) {
			InheritanceTests.testNoImplementInherited(iface);
		}
	}
}
