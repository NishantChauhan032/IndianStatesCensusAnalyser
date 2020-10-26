package com.commonscsvbuilder;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<E> {
	public Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvClass) throws CSVException;
}
