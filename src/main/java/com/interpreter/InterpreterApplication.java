package com.interpreter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.interpreter.*")
public class InterpreterApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterpreterApplication.class, args);
	}

}
