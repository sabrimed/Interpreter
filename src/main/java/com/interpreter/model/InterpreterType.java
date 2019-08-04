package com.interpreter.model;

public enum InterpreterType {

	PYTHON("python");
	
	
	private final String value;
	
	InterpreterType(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
