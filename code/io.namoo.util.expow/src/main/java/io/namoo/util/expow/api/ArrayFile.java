package io.namoo.util.expow.api;

import java.util.Iterator;

public interface ArrayFile {
	//
	public String toJson();
	public String toPrettyJson();
	public int countSheet();
	public String getFileName();
	public ArraySheet requestSheet(String sheetName);
	public ArraySheet requestSheet(int sheetIndex); 
	public Iterator<ArraySheet> iteratorOfSheets(); 
}