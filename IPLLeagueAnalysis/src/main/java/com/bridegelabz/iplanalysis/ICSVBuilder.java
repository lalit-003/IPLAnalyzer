package com.bridegelabz.iplanalysis;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<E> {

	public Iterator<E> getIterator(Reader reader, Class className) throws IllegalStateException, IplAnalyzerException ;
	public List getList(Reader reader, Class className) throws IllegalStateException, IplAnalyzerException ;

}
