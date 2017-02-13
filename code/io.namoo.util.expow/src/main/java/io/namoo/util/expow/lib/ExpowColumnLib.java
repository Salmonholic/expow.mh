/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.util.expow.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.namoo.util.expow.api.ExpowCell;
import io.namoo.util.expow.api.ExpowColumn;

public class ExpowColumnLib implements ExpowColumn {
	//
	public static final int IndexLimit = 5000; 
	
	private int index; 
	private List<ExpowCell> columnCells; 
	 
	public ExpowColumnLib(int columnIndex) {
		// 
		if (columnIndex < 0 || columnIndex > IndexLimit) {
			// 
			throw new IllegalArgumentException(
					String.format("Column index is not valid:%d", 
							columnIndex));
		}
		
		this.index = columnIndex; 
		this.columnCells = new ArrayList<ExpowCell>(); 
	} 

	@Override
	public String toJson() {
		//
		return (new Gson()).toJson(this); 
	}

	@Override
	public String toPrettyJson() {
		//
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
		return gson.toJson(this); 
	}

	@Override
	public ExpowCell requestCell(String valueStr) {
		// 
		if (valueStr == null) {
			throw new IllegalArgumentException(
					String.format("Request value should not be null."));
		}
		
		ExpowCell resultCell = null; 
		for(ExpowCell cell : columnCells) {
			if(cell.getValue().equals(valueStr)) {
				resultCell = cell; 
			} 
		}
		
		if (resultCell == null) {
			throw new NoSuchElementException(
					String.format("No such a cell with value:%s", 
							valueStr)); 
		}
		
		return resultCell; 
	}
	
	@Override
	public ExpowCell requestUnderCellOf(String valueStr) {
		// 
		ExpowCell resultCell = requestCell(valueStr);
		return requestCell(resultCell.getRowIndex()+1); 
	}

	@Override
	public ListIterator<ExpowCell> requestCellsFrom(String valueStr) {
		// 
		int targetIndex = -1; 
		for(ExpowCell cell : columnCells) {
			if(cell.getValue().equals(valueStr)) {
				targetIndex = cell.getRowIndex(); 
				break; 
			}
		}
		
		if (targetIndex == -1) {
			throw new NoSuchElementException(
					String.format("No such cell with value:%s", 
							valueStr));
		}

		return columnCells.listIterator(targetIndex+1); 
	}
	
	@Override
	public Iterator<ExpowCell> iteratorOfCells() {
		// 
		return columnCells.iterator(); 
	}

	@Override
	public boolean hasCellValue(String valueStr) {
		// 
		if (valueStr == null) {
			throw new IllegalArgumentException("Parameter should not be null."); 
		}
		
		for(ExpowCell cell : columnCells) {
			if(cell.getValue().equals(valueStr)) {
				return true; 
			}
		}
		
		return false; 
	}
	
	@Override
	public boolean hasCellValueAt(int rowIndex, String valueStr) {
		// 
		if (valueStr == null) {
			throw new IllegalArgumentException("Can't find the cell with null value."); 
		}
		
		ExpowCell cell = requestCell(rowIndex);  
		
		if(cell.getValue().equals(valueStr)) {
			return true; 
		}
		
		return false; 
	}

	@Override
	public ExpowCell requestCell(int rowIndex) {
		//
		if (rowIndex < 0 || rowIndex >= columnCells.size()) {
			throw new IndexOutOfBoundsException(
					String.format("Requested row index(%d) is out of bound(%d).", 
							rowIndex, 
							columnCells.size())); 
		}
		
		return columnCells.get(rowIndex); 
	}
	
	public void addCell(ExpowCell expowCell) {
		//
		if (expowCell == null) {
			throw new IllegalArgumentException("Can't add null(cell)."); 
		} 

		columnCells.add(expowCell); 
	}
	
	@Override
	public int countCells() {
		// 
		return columnCells.size(); 
	}
	
	@Override
	public int getIndex() {
		return index; 
	}
}