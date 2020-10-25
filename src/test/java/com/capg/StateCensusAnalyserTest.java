package com.capg;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StateCensusAnalyserTest {

	private static final String STATE_CENSUS_DATA_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
	private static final String CSV_DATA_FILE_WRONG_PATH = "./src/main/resources/StateCensusData.csv";
	private static final String WRONG_CENSUS_CSV_FILE_PATH = "./src/test/resources/WrongCensusData.csv";
	private static final String INDIAN_CENSUS_CSV_WRONG_DELIMITER = "./src/test/resources/WrongDelimiterData.csv";

	
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
	
}
