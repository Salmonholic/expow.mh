/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.util.expow.api;

import java.util.List;

public interface ArraySheet {
	//
	public String toJson();
	public String toPrettyJson(); 
	public String toPrettyTable(); 
	public String toPrettyRow(int rowIndex); 
	public String toPrettyColumn(int columnIndex); 
	public int getSheetIndex();
	public String getSheetName();
	public int countRow();
	public String requestCellValue(int rowIndex, int columnIndex); 
	public String[] requestColumnArray(int columnIndex); 
	public String[] requestRowArray(int rowIndex); 
	public List<XYCoord> requestValueCoordinates(String cellValue); 
}