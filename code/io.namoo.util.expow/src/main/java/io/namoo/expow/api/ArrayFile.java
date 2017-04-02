/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.expow.api;

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