package com.interpreter.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;

import com.interpreter.exception.FunctionalException;
import com.interpreter.model.Command;
import com.interpreter.model.UserScript;
import com.interpreter.utils.Utils;

public class InterpreterServiceImpl implements InterpreterService {

	/**
	 * session object contains all command executed by user
	 */
	@Autowired
	private UserScript userScript;

	@Override
	public String interpret(Command command) throws FunctionalException, Exception {
		// verify code syntax
		String code = command.getCode();
		boolean isMatched = Pattern.matches("%.* .*", code);
		if (!isMatched) {
			throw new FunctionalException("incorrect syntax of request");
		}

		String[] splitedCode = code.split(" ", 2);
		String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
		// temporary file with name contains sessionId of user to avoid conflict
		String fileName = InterpreterService.prefixFileName + sessionId + InterpreterService.fileExtension;
		StringBuilder builder = new StringBuilder();
		String commandType = splitedCode[0].substring(1);
		String content = this.userScript.getScript() + splitedCode[1];
		boolean isWrongCode = false;
		try {
			File script = Utils.createScriptFile(fileName, content);
			// execute process with user code
			ProcessBuilder pb = new ProcessBuilder(commandType, fileName);
			Process p = pb.start();

			// get error output of process
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String s = null;
			while ((s = in.readLine()) != null) {
				builder.append(s + InterpreterService.endOfLine);
				isWrongCode = true;
			}
			in.close();

			// get output of process
			in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((s = in.readLine()) != null) {
				builder.append(s + InterpreterService.endOfLine);
			}
			in.close();
			// add code to session object (user) when code works good
			if (!isWrongCode) {
				this.userScript.append(splitedCode[1] + InterpreterService.endOfLine);
			}
			// delete temporary file
			script.delete();

			return builder.toString();
		} catch (Exception e) {
			throw e;
		}
	}

	/** getters and setters */

	public UserScript getUserScript() {
		return userScript;
	}

	public void setUserScript(UserScript userScript) {
		this.userScript = userScript;
	}

}
