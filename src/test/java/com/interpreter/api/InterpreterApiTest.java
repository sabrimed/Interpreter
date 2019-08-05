package com.interpreter.api;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.interpreter.model.Command;
import com.interpreter.service.InterpreterService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class InterpreterApiTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private InterpreterService interpreterService;

	@Test
	public void testInterpret() throws Exception {
		Command command = new Command();
		command.setCode("%python print(2)");
		given(interpreterService.interpret(command)).willReturn("2");

		mvc.perform(get("/execute"))
			.andExpect(status().isOk())
			.andExpect(content().json("{'result': '2`\n'}"));
	}
}
