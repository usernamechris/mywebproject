package org.mycompany.myapp.controller;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardControllerTest{

	private static final Logger logger =
			LoggerFactory.getLogger(BoardControllerTest.class);
	
	@Inject
	private WebApplicationContext wac;
	
	// 브라우저에서 요청과 응답을 의미하는 객체
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		logger.info("setup..............");
	}
	
	@Test
	public void testDoA() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/doA")); // get방식으로 doA호출
		mockMvc.perform(MockMvcRequestBuilders.get("/doB")); // get방식으로 doB호출
		mockMvc.perform(MockMvcRequestBuilders.get("/doC")); // get방식으로 doC호출
		mockMvc.perform(MockMvcRequestBuilders.get("/doD")); // get방식으로 doD호출
		mockMvc.perform(MockMvcRequestBuilders.get("/doE")); // get방식으로 doE호출
		mockMvc.perform(MockMvcRequestBuilders.get("/doF")); // get방식으로 doF호출
		mockMvc.perform(MockMvcRequestBuilders.get("/doJSON")); // get방식으로 doJSON호출
	}
}
