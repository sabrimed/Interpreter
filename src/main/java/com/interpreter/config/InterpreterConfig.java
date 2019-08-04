package com.interpreter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.interpreter.model.UserScript;
import com.interpreter.service.InterpreterService;
import com.interpreter.service.InterpreterServiceImpl;

@Configuration
public class InterpreterConfig {

	@Bean
	@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = WebApplicationContext.SCOPE_SESSION)
	public UserScript userScript() {
		return new UserScript();
	}

	@Bean
	public InterpreterService interpreterService() {
		return new InterpreterServiceImpl();
	}
}
