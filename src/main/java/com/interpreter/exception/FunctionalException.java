package com.interpreter.exception;

public class FunctionalException extends Exception {

	public FunctionalException() {
		super();
	}

	public FunctionalException(String msg) {
		super(msg);
	}

	public FunctionalException(Throwable cause) {
		super(cause);
	}

	public FunctionalException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
