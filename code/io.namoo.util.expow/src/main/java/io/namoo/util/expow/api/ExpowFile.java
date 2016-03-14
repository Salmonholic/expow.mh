package io.namoo.util.expow.api;

import java.util.Iterator;

public interface ExpowFile {
	//
	public String toJson();
	public String toPrettyJson();
	public int countSheet();
	public String getFileName();
	public ExpowSheet requestSheet(String sheetName);
	public ExpowSheet requestSheet(int sheetIndex); 
	public Iterator<ExpowSheet> iteratorOfSheets(); 
}