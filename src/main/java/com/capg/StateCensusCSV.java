package com.capg;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.capg.CensusAnalyserException.ExceptionType;
import com.opencsvbuilder.CSVBuilderFactory;
import com.opencsvbuilder.CSVException;
import com.opencsvbuilder.ICSVBuilder;

public class StateCensusCSV {

	public List<StateCensusData> loadIndiaCensusData(String STATE_CSV_DATA) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(STATE_CSV_DATA));) {
			ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<StateCensusData> stateCensusList = icsvBuilder.getCsvFileList(reader, StateCensusData.class);
			return stateCensusList;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.FILE_NOT_FOUND);
		} catch (CSVException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE);
		}
	}
}