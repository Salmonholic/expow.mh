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
	public String toJson();
	public String toPrettyJson(); 
	public boolean hasCellValue(String valueStr);
	public boolean hasCellValueAt(int columnIndex, String valueStr);
	public ExpowCell requestCell(int columnIndex);
	public ExpowCell requestCell(String valueStr);
	public ExpowCell requestRightCellOf(String valueStr); 
	public ListIterator<ExpowCell> requestCellsIteratorFrom(String valueStr);
	public ListIterator<ExpowCell> requestCellsIterator(); 
	public int countCells();
	public int getRowIndex();
}