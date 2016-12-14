package org.mycompany.myapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	DataSourceTest.class,
	MyBatisTest.class,
	MySQLConnectionTest.class,
	SampleRestControllerTest.class,
	})
public class AllTests {

}
