/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.expow.lib;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.namoo.expow.api.ArraySheet;
import io.namoo.expow.api.XYCoord;

public class ArraySheetLib implements ArraySheet {
	//
	private int index; 
	private String name; 
	private List<String[]> rows; 
	
	public ArraySheetLib(int index, String name) {
		// 
		if (index < 0 || name == null) {
			throw new IllegalArgumentException( 
					String.format("Index(%d) shoud be plus or name(%s) should not be null.", 
							index, 
							name));
		}
		
		this.index = index; 
		this.name = name; 
		this.rows = new ArrayList<String[]>(); 
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
	public String toPrettyTable() {
		// 
		StringBuilder builder =  new StringBuilder(); 
		for(int i=0; i<rows.size(); i++) {
			builder.append(toPrettyRow(i)); 
		}
		
		return builder.toString(); 
	}
	
	@Override
	public String toPrettyRow(int rowIndex) {
		// 
		StringBuilder builder = new StringBuilder(); 
		int columnIndex = 0; 
		for (String cell : rows.get(rowIndex)) {
			if (columnIndex != 0) {
				builder.append(","); 
			}
			builder.append(String.format("[%d:%d]-{%s}", rowIndex, columnIndex++, cell)); 
		}
		builder.append("\n"); 
		
		return builder.toString(); 
	}

	@Override
	public String toPrettyColumn(int columnIndex) {
		// 
		StringBuilder builder = new StringBuilder(); 
		int rowIndex = 0; 
		for (String[] rowArray : rows) {
			if (columnIndex != 0) {
				builder.append(",");
			}
			builder.append(String.format("[%d:%d]-{%s}", rowIndex++, columnIndex, rowArray[columnIndex])); 
		}
		builder.append("\n"); 
		
		return builder.toString(); 
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
		return this.rows.size(); 
	}

	@Override
	public String requestCellValue(int rowIndex, int columnIndex) {
		return rows.get(rowIndex)[columnIndex]; 
	}
	
	@Override
	public String[] requestColumnArray(int columnIndex) {
		//
		int rowLength = rows.size(); 
		
		String[] tempColumnArray = new String[rowLength]; 
		for (int i=0; i<rowLength; i++) {
			tempColumnArray[i] = rows.get(i)[columnIndex]; 
		}
		return tempColumnArray; 
	}
	
	@Override
	public String[] requestRowArray(int rowIndex) {
		return rows.get(rowIndex); 
	}
	
	public void addRow(String[] rowArray) {
		//
		rows.add(rowArray); 
	}
	
	@Override
	public List<XYCoord> requestValueCoordinates(String cellValue) {
		// 
		List<XYCoord> coordinateXYs = new ArrayList<XYCoord>(); 

		int rowIndex = 0; 
		for(String[] rowArray : rows) {
			for(int columnIndex=0; columnIndex < rowArray.length;columnIndex++) {
				String cell = rowArray[columnIndex].trim(); 
				if (cell.equals(cellValue)) {
					coordinateXYs.add(new XYCoord(columnIndex, rowIndex)); 
				}
			}
			rowIndex++; 
		}
		
		return coordinateXYs; 
	}
}