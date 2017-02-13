/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.util.expow.api;

public interface ExpowSheet {
	//
	public String toJson();
	public String toPrettyJson(); 
	public int getSheetIndex();
	public int countRow();
	public void addRow(ExpowRow row); 
	public int countColumn();
	public String getSheetName();
	public String getRowKey(); 
	public String getColumnKey(); 
	public void setRowKey(String keyString);
	public void setColumnKey(String keyString);
	public ExpowRow requestRowKeyRow(String rowKeyValue);
	public ExpowColumn requestColumnKeyColumn(String columnKeyValue);
	public ExpowColumn requestColumnExpanding(int index);
	public ExpowColumn requestColumn(int index);
	public ExpowRow requestRow(int index); 
}