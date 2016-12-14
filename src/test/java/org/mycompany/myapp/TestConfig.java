package org.mycompany.myapp;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


// @RunWith, @ContextConfiguratio은 현재 테스트 코드 실행시 스프링이 로딩되로록 함
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class TestConfig {

	@Inject
	protected SqlSessionFactory sqlFactory; // root-context에 정의
	
	@Inject
	protected WebApplicationContext wac;
	
	protected MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
}
