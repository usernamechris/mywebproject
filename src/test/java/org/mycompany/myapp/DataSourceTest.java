package org.mycompany.myapp;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;

public class DataSourceTest extends TestConfig {

	@Inject
	private DataSource ds;
	
	@Test
	public void testConection() throws Exception {

		try (Connection con = ds.getConnection()) {
			System.out.println(con);

			assertThat(con, notNullValue());

		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}

