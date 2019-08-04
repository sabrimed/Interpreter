package com.interpreter.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;

import com.interpreter.exception.FunctionalException;
import com.interpreter.model.Command;
import com.interpreter.model.UserScript;

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
			throw new FunctionalException("incorrect syntax of code");
		}

		String[] splitedCode = code.split(" ", 2);
		String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
		// temporary file with name contains sessionId of user to avoid conflict
		String fileName = InterpreterService.prefixFileName + sessionId + InterpreterService.fileExtension;
		// add code to session object (user)
		this.userScript.append(splitedCode[1] + InterpreterService.endOfLine);
		StringBuilder builder = new StringBuilder();
		String commandType = splitedCode[0].substring(1);
		try {
			File script = createScriptFile(fileName);
			// execute process with user code
			ProcessBuilder pb = new ProcessBuilder(commandType, fileName);
			pb.redirectErrorStream(true);
			Process p = pb.start();
			// get output of process
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String s;
			while ((s = in.readLine()) != null) {
				builder.append(s + InterpreterService.endOfLine);
			}
			in.close();
			// delete temporary file
			script.delete();
			return builder.toString();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * create file in server with user code
	 * 
	 * @param fileName name of file
	 * @return file
	 * @throws Exception
	 */
	public File createScriptFile(String fileName) throws Exception {
		File script = new File(fileName);
		try (BufferedWriter out = new BufferedWriter(new FileWriter(script))) {
			out.write(this.userScript.getScript());
			return script;
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
