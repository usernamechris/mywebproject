package org.mycompany.myapp;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

public class SampleRestControllerTest extends TestConfig {
	private final String URL = "/restSample/";

	@Test
	public void restTest() throws Exception {
		ResultActions resutlAction = mockMvc.perform(MockMvcRequestBuilders.get(URL + "hello"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(content().string("Hello World"));
		
		resutlAction = mockMvc.perform(MockMvcRequestBuilders.get(URL + "sendVO"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.mno").value(123))
				.andExpect(jsonPath("$.firstName").value("길동"))
				.andExpect(jsonPath("$.lastName").value("홍"));
		
		resutlAction = mockMvc.perform(MockMvcRequestBuilders.get(URL + "sendList"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
}
