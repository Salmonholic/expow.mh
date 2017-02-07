package io.namoo.util.expow.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.namoo.util.expow.lib.ArrayFileLib;
import io.namoo.util.expow.lib.ArraySheetLib;
import io.namoo.util.expow.lib.ExpowCellLib;
import io.namoo.util.expow.lib.ExpowFileLib;
import io.namoo.util.expow.lib.ExpowRowLib;
import io.namoo.util.expow.lib.ExpowSheetLib;

public class ExpowFileReader {
	//
	public static ArrayFile readAsArray(String fileName) {
		// 
		ArrayFileLib arrayFile = new ArrayFileLib(fileName); 
		XSSFWorkbook workbook = openWorkbook(fileName);
		readAllSheetsAsArray(workbook, arrayFile); 
		closeWorkbook(workbook);
		
		return arrayFile; 
	}
	
	public static ExpowFile read(String fileName) {
		//
		ExpowFileLib expowFile = new ExpowFileLib(fileName); 
		XSSFWorkbook workbook = openWorkbook(fileName);
		readAllSheets(workbook, expowFile);
		closeWorkbook(workbook);
		
		return expowFile; 
	}

	private static XSSFWorkbook openWorkbook(String fileName) {
		//
		XSSFWorkbook workbook = null;
		try {
			File file = new File(fileName);
			workbook = new XSSFWorkbook(new FileInputStream(file));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return workbook;
	}

	private static void closeWorkbook(XSSFWorkbook workbook) {
		//
		try {
			workbook.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void readAllSheets(XSSFWorkbook workbook, ExpowFileLib expowFile) {
		//
		int sheetCount = workbook.getNumberOfSheets();
		for (int index = 0; index < sheetCount; index++) {
			XSSFSheet sheet = workbook.getSheetAt(index);
			ExpowSheet expowSheet = new ExpowSheetLib(index, sheet.getSheetName());
			fillSheet(sheet, expowSheet);
			expowFile.addSheet(expowSheet);
		}
	}

	private static void readAllSheetsAsArray(XSSFWorkbook workbook, ArrayFileLib arrayFile) {
		//
		int sheetCount = workbook.getNumberOfSheets();
		for (int index = 0; index < sheetCount; index++) {
			XSSFSheet sheet = workbook.getSheetAt(index);
			ArraySheetLib arraySheet = new ArraySheetLib(index, sheet.getSheetName());
			fillSheetAsArray(sheet, arraySheet);
			arrayFile.addSheet(arraySheet);
		}
	}

	private static void fillSheetAsArray(XSSFSheet sheet, ArraySheetLib arraySheet) {
		//
		Iterator<Row> rowIter = sheet.iterator();

		while (rowIter.hasNext()) {
			Row row = rowIter.next();
			Iterator<Cell> cellIter = row.iterator();
			ArrayList<String> rowCells = new ArrayList<String>(); 
			while (cellIter.hasNext()) {
				Cell cell = cellIter.next();
				String cellValue = getCellValueAsString(cell); 
				rowCells.add(cellValue);
			}
			arraySheet.addRow(rowCells.toArray(new String[rowCells.size()]));  
		}
	}

	private static void fillSheet(XSSFSheet sheet, ExpowSheet expowSheet) {
		//
		Iterator<Row> rowIter = sheet.iterator();

		int rowIndex = 0;
		while (rowIter.hasNext()) {
			Row row = rowIter.next();
			ExpowRowLib expowRow = new ExpowRowLib(rowIndex);
			
			int lastColumn = row.getLastCellNum(); 
			for(int columnIndex = 0; columnIndex<lastColumn; columnIndex++) {
				// 
				Cell cell = row.getCell(columnIndex, Row.RETURN_BLANK_AS_NULL); 

				ExpowCellLib powCell = null; 
				if(cell == null) {
					powCell = new ExpowCellLib(rowIndex, columnIndex, Cell.CELL_TYPE_BLANK, ""); 
				} else {
					powCell = new ExpowCellLib(rowIndex, columnIndex, cell.getCellType(),
							getCellValueAsString(cell));
				}
				expowRow.addCell(powCell);
				expowSheet.requestColumnExpanding(columnIndex).addCell(powCell);
			}
			expowSheet.addRow(expowRow);
			rowIndex++;
		}
	}

	private static String getCellValueAsString(Cell cell) {
		//
		String cellValue = "";
		switch (cell.getCellType()) { 
		case Cell.CELL_TYPE_BLANK:
			cellValue = ""; 
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_ERROR:
			cellValue = "ERROR";
			break;
		case Cell.CELL_TYPE_FORMULA:
			cellValue = cell.getCellFormula();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue();
			break;
		}

		return cellValue;
	}
}