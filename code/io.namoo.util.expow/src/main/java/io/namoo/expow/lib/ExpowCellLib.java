/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.expow.lib;

import org.apache.poi.ss.usermodel.CellType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.namoo.expow.api.ExpowCell;

public class ExpowCellLib implements ExpowCell {
	//
	private static final String BadCellType = "BadCellType"; 
	
	private int x; 
	private int y; 
	private String value; 
	private CellType type;  
	
	public ExpowCellLib(int columnIndex, CellType cellType, String value) {
		// 
		this(0, columnIndex, cellType, value);
	}

	public ExpowCellLib(int rowIndex, int columnIndex, CellType cellType, String value) {
		// 
		this.x = columnIndex; 
		this.y = rowIndex; 
		this.value = value;
		this.type = cellType;
		if (getCellTypeAsStr().equals(BadCellType)) {
			throw new IllegalArgumentException(
					String.format("Cell type is not valid:%d", 
							cellType));
		}
		
		if (value == null) {
			throw new IllegalArgumentException(
					String.format("Cell value can't be null."));
		}
	}

	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder(); 
		
		builder.append("x:").append(x); 
		builder.append(",y:").append(y); 
		builder.append(", value:").append(value); 
		builder.append(", type:").append(type); 

		return builder.toString(); 
	}
	
	public static ExpowCellLib getSample() {
		//
		return new ExpowCellLib(0, 0, CellType.STRING, "Hello"); 
	}
	
	@Override
	public boolean isEmpty() {
		// 
		if (value.isEmpty()) {
			return true; 
		}
		
		return false; 
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
	public int getColumnIndex() {
		return x; 
	}
	
	@Override
	public int getRowIndex() {
		return y; 
	}
	
	public void setRowIndex(int rowIndex) {
		// 
		this.y = rowIndex; 
	}
	
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int  getValueAsInt() {
		//
		Double doubleValue = Double.valueOf(value);
		return doubleValue.intValue();
	}

	@Override
	public double getValueAsDouble() {
		return Double.valueOf(value);
	}

	@Override
	public String getCellTypeAsStr() {
		// 
		String cellTypeStr = ""; 
		
		switch(type) {
		case BLANK:  
			cellTypeStr = "CELL_TYPE_BLANK"; 
			break; 
		case BOOLEAN: 
			cellTypeStr = "CELL_TYPE_BOOLEAN"; 
			break; 
		case ERROR:
			cellTypeStr = "CELL_TYPE_ERROR"; 
			break; 
		case FORMULA: 
			cellTypeStr = "CELL_TYPE_FORMULA"; 
			break; 
		case NUMERIC: 
			cellTypeStr = "CELL_TYPE_NUMERIC"; 
			break; 
		case STRING: 
			cellTypeStr = "CELL_TYPE_STRING"; 
			break; 
		default: 
			cellTypeStr = BadCellType;  
		}
		
		return cellTypeStr; 
	}

}