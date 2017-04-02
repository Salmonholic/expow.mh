/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.expow.api;

public interface ExpowCell {
	//
	boolean isEmpty(); 
	String toJson();
	String toPrettyJson();
	int getColumnIndex();
	int getRowIndex();
	void setRowIndex(int rowIndex); 
	String getValue();
	double getValueAsDouble(); 
	int getValueAsInt(); 
	String getCellTypeAsStr();
}