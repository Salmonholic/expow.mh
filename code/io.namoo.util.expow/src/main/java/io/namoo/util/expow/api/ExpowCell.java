package io.namoo.util.expow.api;

public interface ExpowCell {
	//
	public boolean isEmpty(); 
	public String toJson();
	public String toPrettyJson();
	public int getColumnIndex();
	public int getRowIndex();
	public void setRowIndex(int rowIndex); 
	public String getValue();
	public double getValueAsDouble(); 
	public int getValueAsInt(); 
	public String getCellTypeAsStr();
}