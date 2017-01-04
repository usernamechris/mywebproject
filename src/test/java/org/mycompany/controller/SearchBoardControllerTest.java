package org.mycompany.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

public class SearchBoardControllerTest extends TestConfig {
	
	@Test
	public void getURL() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/sboard/readPage?page=1&perPageNum=10&searchType&keyword&bno=524298"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk());
	}
}
