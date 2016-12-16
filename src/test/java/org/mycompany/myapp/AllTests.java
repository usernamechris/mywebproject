package org.mycompany.myapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.mycompany.myapp.controller.SampleRestControllerTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	DataSourceTest.class,
	MyBatisTest.class,
	MySQLConnectionTest.class,
	SampleRestControllerTest.class,
	})
public class AllTests {

}
