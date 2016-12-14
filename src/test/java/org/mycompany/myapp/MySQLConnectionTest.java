package org.mycompany.myapp;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySQLConnectionTest {

	private static final String DRIVER =
			"com.mysql.jdbc.Driver";
	private static final String URL =
			"jdbc:mysql://db4free.net:3306/db4freehan";
	private static final String USER =
			"db4freehan";
	private static final String PW =
			"db4freehanpw";
	
	@Test
	public void testConnection() throws Exception {
		
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)) {
			System.out.println("##con: " + con);
			assertTrue(con.toString().contains("Connection@"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
