package org.mycompany.myapp;


import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MyBatisTest extends TestConfig {
	
	@Test
	public void testFActory() {
		System.out.println("##factory: " + sqlFactory);
	}

	@Test
	public void testSession() throws Exception {
		try(SqlSession session = sqlFactory.openSession()) {
			System.out.println("##session: " + session);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
