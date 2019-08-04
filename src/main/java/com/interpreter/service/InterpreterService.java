package com.interpreter.service;

import com.interpreter.exception.FunctionalException;
import com.interpreter.model.Command;

public interface InterpreterService {

	public static final String prefixFileName = "script";
	public static final String fileExtension = ".py";
	public static final String endOfLine = "\n";

	/**
	 * interpret command in server environment and returns output
	 * 
	 * @param command command to execute
	 * @return output
	 * @throws FunctionalException when syntax of command is incorrect
	 * @throws Exception           when internal server error
	 */
	public String interpret(Command command) throws FunctionalException, Exception;

}