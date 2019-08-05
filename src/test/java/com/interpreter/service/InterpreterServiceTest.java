package com.interpreter.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.interpreter.model.Command;

@RunWith(SpringRunner.class)
public class InterpreterServiceTest {

	private InterpreterService interpreterService = new InterpreterServiceImpl();

	@Test
	public void interpretTest() {
		try {
			Command command = new Command();
			command.setCode("%python print(2)");
			assertEquals(this.interpreterService.interpret(command), "2");
		} catch (Exception e) {
		}
	}

}
