/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.expow.api;

import java.util.Iterator;
import java.util.ListIterator;

public interface ExpowColumn {
	//
	String toJson();
	String toPrettyJson();
	void addCell(ExpowCell cell); 
	boolean hasCellValue(String valueStr);
	boolean hasCellValueAt(int rowIndex, String valueStr);
	ExpowCell requestCell(int rowIndex);
	ExpowCell requestCell(String valueStr);
	ExpowCell requestUnderCellOf(String valueStr);
	ListIterator<ExpowCell> requestCellsFrom(String valueStr); 
	Iterator<ExpowCell> iteratorOfCells(); 
	int countCells();
	int getIndex();
}