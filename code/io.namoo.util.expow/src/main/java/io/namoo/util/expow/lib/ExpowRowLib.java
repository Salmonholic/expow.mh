/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.util.expow.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.Cell;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.namoo.util.expow.api.ExpowCell;
import io.namoo.util.expow.api.ExpowRow;

public class ExpowRowLib implements ExpowRow {
	//
	public static final int IndexLimit = 500000; 

	private int rowIndex; 
	private List<ExpowCell> rowCells; 
	 
	public ExpowRowLib(int rowIndex) {
		// 
		if (rowIndex < 0 || rowIndex > IndexLimit) {
			// 
			throw new IllegalArgumentException(
					String.format("Column index is not valid:%d", 
							rowIndex));
		}

		this.rowIndex = rowIndex; 
		this.rowCells = new ArrayList<ExpowCell>(); 
	}
 
	public static ExpowRowLib getSample(int rowIndex) {
		// 
		ExpowRowLib sampleRow = new ExpowRowLib(rowIndex);
		sampleRow.addCell(new ExpowCellLib(0,Cell.CELL_TYPE_STRING, "Hello"));
		sampleRow.addCell(new ExpowCellLib(1,Cell.CELL_TYPE_STRING, "My"));
		sampleRow.addCell(new ExpowCellLib(2,Cell.CELL_TYPE_STRING, "Friend"));
		 
		return sampleRow; 
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
		ExpowCell resultCell = null; 
		for(ExpowCell cell : rowCells) {
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
	public ExpowCell requestRightCellOf(String valueStr) {
		// 
		ExpowCell resultCell = requestCell(valueStr);
		return requestCell(resultCell.getColumnIndex()+1); 
	}

	@Override
	public ListIterator<ExpowCell> requestCellsIteratorFrom(String valueStr) {
		// 
		int targetIndex = -1; 
		for(ExpowCell cell : rowCells) {
			if(cell.getValue().equals(valueStr)) {
				targetIndex = cell.getColumnIndex(); 
				break; 
			}
		}
		
		if (targetIndex == -1) {
			throw new NoSuchElementException(
					String.format("No such cell with value:%s", 
							valueStr));
		}

		return rowCells.listIterator(targetIndex+1); 
	}
	
	@Override
	public ListIterator<ExpowCell> requestCellsIterator() {
		// 
		return rowCells.listIterator(0); 
	}

	@Override
	public boolean hasCellValue(String valueStr) {
		// 
		for(ExpowCell cell : rowCells) {
			if(cell.getValue().equals(valueStr)) {
				return true; 
			}
		}
		
		return false; 
	}
	
	@Override
	public boolean hasCellValueAt(int columnIndex, String valueStr) {
		// 
		if (valueStr == null) {
			throw new IllegalArgumentException("Can't find the cell with null value."); 
		}
		
		ExpowCell cell = requestCell(columnIndex);  
		
		if(cell.getValue().equals(valueStr)) {
			return true; 
		}
		
		return false; 
	}

	@Override
	public ExpowCell requestCell(int columnIndex) {
		//
		if (columnIndex >= rowCells.size()) {
			throw new IndexOutOfBoundsException(
					String.format("Request column index(%d) is bigger then cell count(%d)", 
							columnIndex, 
							rowCells.size())); 
		}
		
		return rowCells.get(columnIndex); 
	}
	
	public void addCell(ExpowCell expowCell) {
		//
		if (expowCell == null) {
			throw new IllegalArgumentException("Can't add null(cell)."); 
		}
		expowCell.setRowIndex(rowIndex); 
		rowCells.add(expowCell); 
	}
	
	@Override
	public int countCells() {
		// 
		return rowCells.size(); 
	}
	
	@Override
	public int getRowIndex() {
		return rowIndex; 
	}
}