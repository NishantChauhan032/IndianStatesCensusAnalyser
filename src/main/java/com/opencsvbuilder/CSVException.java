package com.opencsvbuilder;

public class CSVException extends Throwable {
	public CSVException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

	public enum ExceptionType {
		UNABLE_TO_PARSE, CSV_FILE_INTERNAL_ISSUES
	}

	ExceptionType type;
}
