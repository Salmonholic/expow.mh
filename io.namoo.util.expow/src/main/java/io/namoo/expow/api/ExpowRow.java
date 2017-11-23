/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.expow.api;

import java.util.ListIterator;

public interface ExpowRow {
	//
	String toJson();
	String toPrettyJson(); 
	boolean hasCellValue(String valueStr);
	boolean hasCellValueAt(int columnIndex, String valueStr);
	ExpowCell requestCell(int columnIndex);
	ExpowCell requestCell(String valueStr);
	ExpowCell requestRightCellOf(String valueStr); 
	ListIterator<ExpowCell> requestCellsIteratorFrom(String valueStr);
	ListIterator<ExpowCell> requestCellsIterator(); 
	int countCells();
	int getRowIndex();
}