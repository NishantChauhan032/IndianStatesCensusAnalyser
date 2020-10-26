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

public class StatesCSV {

	public int loadIndianStateCode(String STATE_CODE_DATA) throws CensusAnalyserException {
		int numOfRecords = 0;
		try (Reader reader = Files.newBufferedReader(Paths.get(STATE_CODE_DATA));) {
			CsvToBeanBuilder<StateCodeData> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(StateCodeData.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<StateCodeData> csvToBean = csvToBeanBuilder.build();
			Iterator<StateCodeData> csvStateCodeIterator = csvToBean.iterator();
			Iterable<StateCodeData> stateCodeIterable = () -> csvStateCodeIterator;
			numOfRecords = (int) StreamSupport.stream(stateCodeIterable.spliterator(), false).count();
			return numOfRecords;
		} catch (IOException e) {
				throw new CensusAnalyserException(e.getMessage(), ExceptionType.FILE_NOT_FOUND);
		}catch (IllegalStateException e) {
				throw new CensusAnalyserException(e.getMessage(), ExceptionType.UNABLE_TO_PARSE);
		}catch (RuntimeException e) {
				throw new CensusAnalyserException(e.getMessage(), ExceptionType.INTERNAL_ISSUES_IN_CSV_FILE);
		}
		
	}
}