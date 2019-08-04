package com.interpreter.model;

import java.io.Serializable;

public class UserScript implements Serializable {
	private StringBuilder script = new StringBuilder();

	public void init() {
		if (this.script == null) {
			this.script = new StringBuilder();
		} else {
			this.clear();
		}
	}

	public String getScript() {
		return script.toString();
	}

	public void append(String script) {
		this.script.append(script);
	}

	public void clear() {
		this.script.setLength(0);
	}

}
