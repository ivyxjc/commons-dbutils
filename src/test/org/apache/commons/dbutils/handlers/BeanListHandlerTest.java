/*
 * Copyright 2003-2005 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.dbutils.handlers;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbutils.BaseTestCase;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.TestBean;

/**
 * BeanListHandlerTest
 */
public class BeanListHandlerTest extends BaseTestCase {

	/**
	 * Constructor for BeanListHandlerTest.
	 */
	public BeanListHandlerTest(String name) {
		super(name);
	}

	public void testHandle() throws SQLException {
		ResultSetHandler h = new BeanListHandler(TestBean.class);
		List results = (List) h.handle(this.rs);

		assertNotNull(results);
		assertEquals(ROWS, results.size());

		Iterator iter = results.iterator();
		TestBean row = null;
		while (iter.hasNext()) {
			row = (TestBean) iter.next();
			assertNotNull(row);
		}

		assertEquals("4", row.getOne());
		assertEquals("5", row.getTwo());
		assertEquals("6", row.getThree());
		assertEquals("not set", row.getDoNotSet());
	}

	public void testEmptyResultSetHandle() throws SQLException {
		ResultSetHandler h = new BeanListHandler(TestBean.class);
		List results = (List) h.handle(this.emptyResultSet);

		assertNotNull(results);
		assertTrue(results.isEmpty());
	}

}
