package com.opencsvbuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<E> {
	public Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvClass) throws CSVException;
	public List<E> getCsvFileList(Reader reader, Class<E> csvClass) throws CSVException;
}
