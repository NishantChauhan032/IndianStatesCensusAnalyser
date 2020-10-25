package com.capg;

public class CensusAnalyserException extends Exception {

	public ExceptionType type;

	public CensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

	public enum ExceptionType {
		FILE_NOT_FOUND, UNABLE_TO_PARSE, NO_CENSUS_DATA_FOUND, INTERNAL_ISSUES_IN_CSV_FILE
	}
}

