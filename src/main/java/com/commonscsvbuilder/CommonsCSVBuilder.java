package com.commonscsvbuilder;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CommonsCSVBuilder<E> implements ICSVBuilder<E>{
	public Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvClass) throws CSVException {
		try {
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            List<CSVRecord> csvRecords = csvParser.getRecords();
            List<E> list = new ArrayList<>();
         
            for(int i = 1 ; i < csvRecords.size() ; i++) {
            	list.add((E) csvRecords.get(i));
            }
            System.out.println(list);
		    Iterator iterator = list.iterator();
		    return iterator;
		} catch (IllegalStateException e) {
			throw new CSVException(e.getMessage(), CSVException.ExceptionType.UNABLE_TO_PARSE);
		} catch (IOException e) {
			throw new CSVException(e.getMessage(), CSVException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
}

