package org.mycompany.myapp;

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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

