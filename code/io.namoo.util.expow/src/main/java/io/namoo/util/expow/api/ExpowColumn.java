/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.util.expow.api;

import java.util.Iterator;
import java.util.ListIterator;

public interface ExpowColumn {
	//
	public String toJson();
	public String toPrettyJson();
	public void addCell(ExpowCell cell); 
	public boolean hasCellValue(String valueStr);
	public boolean hasCellValueAt(int rowIndex, String valueStr);
	public ExpowCell requestCell(int rowIndex);
	public ExpowCell requestCell(String valueStr);
	public ExpowCell requestUnderCellOf(String valueStr);
	public ListIterator<ExpowCell> requestCellsFrom(String valueStr); 
	public Iterator<ExpowCell> iteratorOfCells(); 
	public int countCells();
	public int getIndex();
}