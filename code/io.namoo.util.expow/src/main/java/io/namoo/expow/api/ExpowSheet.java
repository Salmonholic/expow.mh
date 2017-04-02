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
	void addRow(ExpowRow row); 
	int countColumn();
	String getSheetName();
	String getRowKey(); 
	String getColumnKey(); 
	void setRowKey(String keyString);
	void setColumnKey(String keyString);
	ExpowRow requestRowKeyRow(String rowKeyValue);
	ExpowColumn requestColumnKeyColumn(String columnKeyValue);
	ExpowColumn requestColumnExpanding(int index);
	ExpowColumn requestColumn(int index);
	ExpowRow requestRow(int index); 
}