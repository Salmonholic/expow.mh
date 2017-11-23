/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.expow.api;

public interface ExpowSheet {
	//
	String toJson();
	String toPrettyJson(); 
	int getSheetIndex();
	int countRow();
	int countColumn();
	void addRow(ExpowRow row); 
	String getSheetName();
	ExpowRow requestRow(int index); 
	ExpowRow requestRow(String rowKey);
	ExpowColumn requestColumn(int index);
	ExpowColumn requestColumn(String columnKey);
	ExpowColumn requestColumnExpanding(int index);
}