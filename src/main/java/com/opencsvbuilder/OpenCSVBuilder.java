package com.opencsvbuilder;

import java.io.Reader;

import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<E> implements ICSVBuilder<E> {

	@Override
	public Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvClass) throws CSVException {
		return this.getCSVtoBean(reader, csvClass).iterator();
	}

	public List<E> getCsvFileList(Reader reader, Class<E> csvClass) throws CSVException {
		return this.getCSVtoBean(reader, csvClass).parse();
	}

	public CsvToBean<E> getCSVtoBean(Reader reader, Class<E> csvClass) throws CSVException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean;
		} catch (IllegalStateException e) {
			throw new CSVException(e.getMessage(), CSVException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
}
