package com.interpreter.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Utils {

	/**
	 * create file in server with user code
	 * 
	 * @param fileName name of file
	 * @param content  content of file
	 * @return file
	 * @throws Exception
	 */
	public static File createScriptFile(String fileName, String content) throws Exception {
		File script = new File(fileName);
		try (BufferedWriter out = new BufferedWriter(new FileWriter(script))) {
			out.write(content);
			return script;
		} catch (Exception e) {
			throw e;
		}
	}
}
