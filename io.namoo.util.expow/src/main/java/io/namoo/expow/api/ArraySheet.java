/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.expow.api;

import java.util.List;

public interface ArraySheet {
	//
	String toJson();
	String toPrettyJson(); 
	String toPrettyTable(); 
	String toPrettyRow(int rowIndex); 
	String toPrettyColumn(int columnIndex); 
	int getSheetIndex();
	String getSheetName();
	int countRow();
	String requestCellValue(int rowIndex, int columnIndex); 
	String[] requestColumnArray(int columnIndex); 
	String[] requestRowArray(int rowIndex); 
	List<XYCoord> requestValueCoordinates(String cellValue); 
}