package com.interpreter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.interpreter.exception.FunctionalException;
import com.interpreter.model.Command;
import com.interpreter.model.Result;
import com.interpreter.service.InterpreterService;

@RestController()
public class InterpreterAPI {

	@Autowired
	private InterpreterService interpreterService;

	/**
	 * API method to execute user command
	 * 
	 * @param command command to execute
	 * @return result of command
	 */
	@GetMapping(path = "/execute", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Result> execute(@RequestBody Command command) {
		Result result = new Result();
		try {
			String output = interpreterService.interpret(command);
			result.setResult(output);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (FunctionalException e) {
			result.setResult(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			result.setResult(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
