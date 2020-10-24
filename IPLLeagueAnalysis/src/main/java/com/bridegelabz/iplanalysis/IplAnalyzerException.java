package com.bridegelabz.iplanalysis;

public class IplAnalyzerException extends Exception {

	public enum ExceptionType {
		WRONG_FILE, NO_DATA_FOUND, INVALID_FILE_PATH, INVALID_CLASS;
	}

	ExceptionType type;

	public IplAnalyzerException(String message, ExceptionType type) {
		super(message);
		System.out.println(message);
		this.type = type;
	}

}
