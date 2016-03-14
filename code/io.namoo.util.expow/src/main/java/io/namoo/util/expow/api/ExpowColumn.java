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