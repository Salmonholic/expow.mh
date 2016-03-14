package io.namoo.util.expow.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.namoo.util.expow.api.ArrayFile;
import io.namoo.util.expow.api.ArraySheet;

public class ArrayFileLib implements ArrayFile{
	//
	private String fileName; 
	private List<ArraySheet> sheets; 
	
	public ArrayFileLib(String fileName) {
		// 
		this.fileName = fileName; 
		this.sheets = new ArrayList<ArraySheet>(); 
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
	public String getFileName() {
		return fileName; 
	}
	
	@Override
	public int countSheet() {
		// 
		return sheets.size(); 
	}
	
	public void addSheet(ArraySheet arraySheet) {
		// 
		sheets.add(arraySheet); 
	}
	
	@Override
	public ArraySheet requestSheet(String sheetName) {
		// 
		for (ArraySheet sheet : sheets) {
			if (sheet.getSheetName().equals(sheetName)) {
				return sheet; 
			}
		}

		throw new NoSuchElementException(
				String.format("No such a sheet:%s", 
						sheetName));
	}

	@Override
	public ArraySheet requestSheet(int index) {
		// 
		if (index < 0 || index >= sheets.size()) {
			throw new NoSuchElementException(
					String.format("Index out of bound:%d", 
							index));
		}
		
		return sheets.get(index);  
	}

	public Iterator<ArraySheet> iteratorOfSheets() {
		// 
		return this.sheets.iterator(); 
	}
}