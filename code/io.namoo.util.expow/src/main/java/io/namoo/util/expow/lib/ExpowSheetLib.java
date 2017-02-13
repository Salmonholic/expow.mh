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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.namoo.util.expow.api.ExpowCell;
import io.namoo.util.expow.api.ExpowColumn;
import io.namoo.util.expow.api.ExpowRow;
import io.namoo.util.expow.api.ExpowSheet;

public class ExpowSheetLib implements ExpowSheet {
	//
	private int index; 
	private String name; 
	
	private String rowKey; 
	private int rowKeyColumnIndex; 
	private List<ExpowRow> rows; 
	
	private String columnKey; 
	private int columnKeyRowIndex; 
	private List<ExpowColumn> columns; 
	
	public ExpowSheetLib(int index, String name) {
		// 
		if (index < 0 || name == null) {
			throw new IllegalArgumentException( 
					String.format("Index(%d) shoud be plus or name(%s) should not be null.", 
							index, 
							name));
		}
		
		this.index = index; 
		this.name = name; 
		this.initialize();   
		this.rows = new ArrayList<ExpowRow>(); 
		this.columns = new ArrayList<ExpowColumn>(); 
	}

	private void initialize() {
		// 
		this.rowKey = null; 
		this.columnKey = null; 
		this.rowKeyColumnIndex = -1;
		this.columnKeyRowIndex = -1;  
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
	public int getSheetIndex() {
		return index;
	}

	@Override
	public String getSheetName() {
		return name;
	}

	@Override
	public int countRow() {
		return rows.size(); 
	}
	
	@Override
	public int countColumn() {
		return columns.size(); 
	}
	
	@Override
	public String getRowKey() {
		return rowKey; 
	}
	
	@Override
	public String getColumnKey() {
		return columnKey; 
	}
	
	@Override
	public void setRowKey(String keyString) {
		// 
		this.rowKey = keyString;  
		if (rowKeyColumnIndex == -1) {
			// 
			rowKeyColumnIndex = findRowKeyColumnIndex(); 
			if (rowKeyColumnIndex == -1) {
				throw new IllegalArgumentException(
						String.format("No such a row key string:%s", 
								keyString));  
			}
		}
	}
	
	@Override
	public void setColumnKey(String keyString) {
		// 
		this.columnKey = keyString; 
		if (columnKeyRowIndex == -1) {
			// 
			columnKeyRowIndex = findColumnKeyRowIndex(); 
			
			if (columnKeyRowIndex == -1) {
				throw new IllegalArgumentException(
						String.format("No such a column key string:%s", 
								keyString));  
			}
		}
	}
 
	@Override
	public ExpowRow requestRowKeyRow(String rowKeyValue) {
		// 
		this.initialize();
		
		if (rowKey == null) { 
			// 
			this.setRowKey(rowKeyValue);
			
			if (rowKey == null) {
				throw new IllegalArgumentException(
						String.format("Key string is not specified for %s.", rowKeyValue)); 
			}
		}
		
		return this.findRowInColumnValue(rowKeyColumnIndex, rowKeyValue);
	}

	@Override
	public ExpowColumn requestColumnKeyColumn(String columnKeyValue) {
		// 
		this.initialize();

		if (columnKey == null) {
			// 
			this.setColumnKey(columnKeyValue); 
			
			if (columnKey == null) {
				throw new RuntimeException(
						String.format("Key string is not specified for %s.", columnKeyValue)); 
			}
		}
		
		return this.findColumnInRowValue(columnKeyRowIndex, columnKeyValue);
	}

	@Override
	public ExpowColumn requestColumnExpanding(int index) {
		// 
		if (index >= columns.size()) {
			columns.add(new ExpowColumnLib(index)); 
		}
		
		return this.columns.get(index); 
	}
	
	@Override
	public ExpowColumn requestColumn(int index) {
		// 
		if (index < 0 || index > columns.size()) {
			// 
			throw new IndexOutOfBoundsException( 
					String.format("Bound is %d, but it's %d", 
							columns.size(), 
							index));
		} 

		return this.columns.get(index); 
	}

	@Override
	public ExpowRow requestRow(int index) {
		// 
		if (index < 0 || index > rows.size()) {
			// 
			throw new IndexOutOfBoundsException( 
					String.format("Bound is %d, but it's %d", 
							rows.size(), 
							index));
		}
		
		return rows.get(index); 
	}
	
	public void addRow(ExpowRow row) {
		// 
		rows.add(row); 
	}
	 
	public void addColumn(ExpowColumn column) {
		// 
		columns.add(column);
	}

	private ExpowRow findRowInColumnValue(int columnIndex, String valueStr) {
		//
		ExpowRow resultRow = null; 
		for (ExpowRow row : this.rows) {
			if (row.hasCellValueAt(columnIndex, valueStr)) {
				resultRow = row; 
				break; 
			}
		}
		
		return resultRow; 
	}
	
	private ExpowColumn findColumnInRowValue(int rowIndex, String valueStr) {
		//
		ExpowColumn resultColumn = null; 
		for (ExpowColumn column : this.columns) {
			if (column.hasCellValueAt(rowIndex, valueStr)) {
				resultColumn = column; 
				break; 
			}
		}
		
		return resultColumn; 
	}

	private int findRowKeyColumnIndex() {
		// 
		for(ExpowRow row : this.rows) {
			// 
			if (row.hasCellValue(rowKey)) {
				ExpowCell cell = row.requestCell(rowKey); 
				return cell.getColumnIndex(); 
			}
		}
		
		return -1; 
	}
	
	private int findColumnKeyRowIndex() {
		// 
		for(ExpowColumn column : this.columns) {
			// 
			if (column.hasCellValue(columnKey)) {
				ExpowCell cell = column.requestCell(columnKey); 
				return cell.getRowIndex(); 
			}
		}
		
		return -1; 
	}
}