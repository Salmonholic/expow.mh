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
	public boolean isEmpty(); 
	public String toJson();
	public String toPrettyJson();
	public int getColumnIndex();
	public int getRowIndex();
	public void setRowIndex(int rowIndex); 
	public String getValue();
	public double getValueAsDouble(); 
	public int getValueAsInt(); 
	public String getCellTypeAsStr();
}