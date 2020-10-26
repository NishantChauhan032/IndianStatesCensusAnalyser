package com.capg;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.capg.jsonutility.Json;
import com.google.gson.Gson;

public class StateCensusAnalyserTest {

	private static final String STATE_CENSUS_DATA_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
	private static final String CSV_DATA_FILE_WRONG_PATH = "./src/main/resources/StateCensusData.csv";
	private static final String INDIAN_CENSUS_CSV_WRONG_DELIMITER = "./src/test/resources/WrongDelimiterData.csv";
	private static final String STATE_CODE_DATA = "./src/test/resources/StateCodeData.csv";
	private static final String CSV_WRONG_DATA = "./src/test/resources/WrongData.csv";
	private static final String STATE_CODE_CSV_WRONG_DELIMITER = "./src/test/resources/StateCodeWrongDelimiter.csv";
	private static final String STATE_CODE_CSV_HEADER_MISSING = "./src/test/resources/StateCodeDataMissingHeader.csv";
	private static final String INDIAN_CENSUS_CSV_HEADER_MISSING = "./src/test/resources/CensusHeaderMissing.csv";
	
	StateCensusAnalyser stateCensusAnalyser = null;


	@Before
	public void Setup() {
		stateCensusAnalyser = new StateCensusAnalyser();
		ExpectedException exceptionRule = ExpectedException.none();
		exceptionRule.expect(CensusAnalyserException.class);
	}

	@Test
	public void givenCsvPath_ShouldReturn_NumberOfRecords() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(STATE_CENSUS_DATA_CSV_FILE_PATH);
			assertEquals(28, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenWrongFile_ShouldThrow_CustomException() {
		try {
			stateCensusAnalyser.loadIndiaCensusData(CSV_DATA_FILE_WRONG_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
		}
	}

	@Test
	public void givenRightCsvFile_ButWrongType_ShouldThrowCustomException() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(CSV_WRONG_DATA);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE, e.type);
		}
	}

	@Test
	public void givenWrongDelimiter_InIndiaCensusData_ShouldThrowCustomException() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_DELIMITER);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE, e.type);
		}
	}

	@Test
	public void givenMissingHeader_InIndiaCensusData_ShouldThrowCustomException() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_HEADER_MISSING);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE, e.type);
		}
	}

	@Test
	public void givenCsvPath_InStateCodeData_ShouldReturnNumberOfRecords() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndianStateCode(STATE_CODE_DATA);
			assertEquals(38, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenWrongCsvPath_InStateCodeData_ShouldThrowCustomException() {
		try {
			stateCensusAnalyser.loadIndianStateCode(CSV_DATA_FILE_WRONG_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
		}
	}

	@Test
	public void givenRightStateCsvFileButWrongType_InStateCodeData_ShouldThrowCustomException() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(CSV_WRONG_DATA);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE, e.type);
		}
	}

	@Test
	public void givenWrongDelimiter_InStateCodeData_ShouldThrowCustomException() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndianStateCode(STATE_CODE_CSV_WRONG_DELIMITER);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE, e.type);
		}
	}

	@Test
	public void givenMissingHeader_InStateCodeData_ShouldThrowCustomException() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndianStateCode(STATE_CODE_CSV_HEADER_MISSING);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE, e.type);
		}
	}

	@Test
	public void givenIndianCensusData_WhenSortedByState_shouldReturnSortedResult() throws CensusAnalyserException {
		String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData(STATE_CENSUS_DATA_CSV_FILE_PATH);
		StateCensusData[] censusCSV = new Gson().fromJson(sortedCensusData, StateCensusData[].class);
		assertEquals("Andhra Pradesh", censusCSV[0].getState());
		assertEquals("West Bengal", censusCSV[censusCSV.length-1].getState());
	}
	
	@Test
	public void givenStateCodeData_whenSortedByStateCode_shouldReturnSortedResult() throws CensusAnalyserException {
		String sortedStateCodeData = stateCensusAnalyser.getStateCodeWiseSortedData(STATE_CODE_DATA);
		StateCodeData[] stateCodeCSV = new Gson().fromJson(sortedStateCodeData, StateCodeData[].class);
		assertEquals("AN", stateCodeCSV[0].getStateCode());
		assertEquals("WB", stateCodeCSV[stateCodeCSV.length-1].getStateCode());
	}
	
	@Test
	public void givenStateCodeData_WhenSortedByPopulation_ShouldReturnSortedResult() throws CensusAnalyserException {
		String sortedCensusData = stateCensusAnalyser.getPopulationWiseSortedCensusData(STATE_CENSUS_DATA_CSV_FILE_PATH );
		new Json().writeList("population", sortedCensusData);
		String readCensuslist = new Json().readList("populationData.json");
		StateCensusData[] censusCSV = new Gson().fromJson(sortedCensusData, StateCensusData[].class);
		StateCensusData[] censusCSVfromFile = new Gson().fromJson(readCensuslist, StateCensusData[].class);
		assertEquals(censusCSV.length, censusCSVfromFile.length);
	}
}