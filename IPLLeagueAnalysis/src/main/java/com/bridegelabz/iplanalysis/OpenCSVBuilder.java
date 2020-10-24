package com.bridegelabz.iplanalysis;

import java.util.List;
import java.io.Reader;
import java.util.Iterator;

import com.bridegelabz.iplanalysis.IplAnalyzerException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<E> implements ICSVBuilder<E> {

	private CsvToBean<E> getCsvToBean(Reader reader, Class className) throws IplAnalyzerException {
		try {
			CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader)
					                .withIgnoreLeadingWhiteSpace(true)
					                 .withType(className)
					                 .build();
			return csvToBean;
		} catch (IllegalStateException e) {
			throw new IplAnalyzerException(e.getMessage(),ExceptionType.INVALID_CLASS);
		}
			}
	
	@Override
	public Iterator<E> getIterator(Reader reader, Class className) throws IplAnalyzerException  {
		return this.getCsvToBean(reader, className).iterator();
	}


	@Override
	public List getList(Reader reader, Class className) throws IllegalStateException, IplAnalyzerException  {
		return this.getCsvToBean(reader, className).parse();
	}

}
