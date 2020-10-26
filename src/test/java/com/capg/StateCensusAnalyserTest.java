package com.capg;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StateCensusAnalyserTest {

	private static final String STATE_CENSUS_DATA_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
	private static final String CSV_DATA_FILE_WRONG_PATH = "./src/main/resources/StateCensusData.csv";
	private static final String WRONG_CENSUS_CSV_FILE_PATH = "./src/test/resources/WrongDataOfCensus.csv";
	private static final String INDIAN_CENSUS_CSV_WRONG_DELIMITER = "./src/test/resources/WrongDelimiterData.csv";
	private static final String STATE_CODE_DATA = "./src/test/resources/StateCodeData.csv";
	private static final String CSV_WRONG_DATA = "./src/test/resources/WrongData.csv";
	private static final String STATE_CODE_CSV_WRONG_DELIMITER = "./src/test/resources/StateCodeWrongDelimiter.csv";
	private static final String STATE_CODE_CSV_HEADER_MISSING = "./src/test/resources/StateCodeDataMissingHeader.csv";
	
	@Test
	public void givenCSVPath_whenCorrect_ShouldReturnNumberOfRecords() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(STATE_CENSUS_DATA_CSV_FILE_PATH);
			assertEquals(28, numOfRecords);
			System.out.println(numOfRecords);
		} catch (CensusAnalyserException e) {
			e.getMessage();
		}
	}
	@Test
	public void givenWrongPath_ShouldThrow_CustomException() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadIndiaCensusData(CSV_DATA_FILE_WRONG_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
		}
	}


	@Test
	public void givenRightCsvFile_ButWrongType_ShouldThrowCustomException() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadIndiaCensusData(WRONG_CENSUS_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE, e.type);
		}
	}
	
	  @Test
	    public void givenDelimiterInIndiaCensusData_WhenWrong_ShouldThrowCustomException() {
	        try {
	        	StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
	            stateCensusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_DELIMITER);
	        } catch (CensusAnalyserException e) {
	            assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE, e.type);
	        }
	    }
	  
	  @Test
	    public void givenHeaderInIndiaCensusData_WhenMissing_ShouldThrowCustomException() {
	        try {
	        	StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
	            int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_DELIMITER);
	        } catch (CensusAnalyserException e) {
	            assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE, e.type);
	        }
	    }
	  @Test
		public void givenCsvPath_InStateCodeData_ShouldReturn_NumberOfRecords() {
			
			try {
				StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
				int numOfRecords = stateCensusAnalyser.loadIndianStateCode(STATE_CODE_DATA);
				assertEquals(38, numOfRecords);
			} catch (CensusAnalyserException e) {
			}
		}
	  @Test
		public void givenWrongCsvPath_InStateCodeData_ShouldThrow_CustomException() {
			try {
				StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
				ExpectedException exceptionRule = ExpectedException.none();
				exceptionRule.expect(CensusAnalyserException.class);
				stateCensusAnalyser.loadIndianStateCode(CSV_DATA_FILE_WRONG_PATH );
			} catch (CensusAnalyserException e) {
				assertEquals(CensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
			}
		}
	  @Test
		public void givenRightStateCsvFile_ButWrongType_InStateCodeData_ShouldThrow_CustomException() {
			try {
				StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
				ExpectedException exceptionRule = ExpectedException.none();
				exceptionRule.expect(CensusAnalyserException.class);
				int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(CSV_WRONG_DATA);
			} catch (CensusAnalyserException e) {
				assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE, e.type);
			}
		}
	  @Test
		public void givenWrongDelimiter_InStateCodeData_ShouldThrow_CustomException() {
			try {
				StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
				ExpectedException exceptionRule = ExpectedException.none();
				exceptionRule.expect(CensusAnalyserException.class);
				int numOfRecords = stateCensusAnalyser.loadIndianStateCode(STATE_CODE_CSV_WRONG_DELIMITER);
			} catch (CensusAnalyserException e) {
				assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE, e.type);
			}
		}
	  @Test
		public void givenMissingHeader_InStateCodeData_ShouldThrow_CustomException() {
			try {
				StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
				int numOfRecords = stateCensusAnalyser.loadIndianStateCode(STATE_CODE_CSV_HEADER_MISSING);
			} catch (CensusAnalyserException e) {
				assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE, e.type);
			}
		}
}
