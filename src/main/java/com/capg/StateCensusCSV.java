package com.capg;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.capg.CensusAnalyserException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusCSV {

	public int loadIndiaCensusData(String STATE_CSV_DATA) throws CensusAnalyserException {
		int numOfDataEntries = 0;
		try (Reader reader = Files.newBufferedReader(Paths.get(STATE_CSV_DATA));) {
			CsvToBeanBuilder<StateCensusData> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(StateCensusData.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<StateCensusData> csvToBean = csvToBeanBuilder.build();
			Iterator<StateCensusData> csvCensusIterator = csvToBean.iterator();
			Iterable<StateCensusData> censusIterable = () -> csvCensusIterator;
			numOfDataEntries = (int) StreamSupport.stream(censusIterable.spliterator(), false).count();
			return numOfDataEntries;
		} catch (IOException e) {
				throw new CensusAnalyserException(e.getMessage(), ExceptionType.FILE_NOT_FOUND);
		}catch (IllegalStateException e) {
				throw new CensusAnalyserException(e.getMessage(), ExceptionType.UNABLE_TO_PARSE);
		}catch (RuntimeException e) {
				throw new CensusAnalyserException(e.getMessage(), ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE);
		}
		
	}
}
