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
	String toJson();
	String toPrettyJson();
	int countSheet();
	String getFileName();
	ArraySheet requestSheet(String sheetName);
	ArraySheet requestSheet(int sheetIndex); 
	Iterator<ArraySheet> iteratorOfSheets(); 
}