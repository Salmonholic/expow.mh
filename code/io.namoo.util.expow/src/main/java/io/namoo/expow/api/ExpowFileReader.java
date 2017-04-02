/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.expow.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.namoo.expow.lib.ArrayFileLib;
import io.namoo.expow.lib.ArraySheetLib;
import io.namoo.expow.lib.ExpowCellLib;
import io.namoo.expow.lib.ExpowFileLib;
import io.namoo.expow.lib.ExpowRowLib;
import io.namoo.expow.lib.ExpowSheetLib;

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

	@SuppressWarnings("deprecation")
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
					powCell = new ExpowCellLib(rowIndex, columnIndex, CellType.BLANK, ""); 
				} else {
					powCell = new ExpowCellLib(rowIndex, columnIndex, cell.getCellTypeEnum(),
							getCellValueAsString(cell));
				}
				expowRow.addCell(powCell);
				expowSheet.requestColumnExpanding(columnIndex).addCell(powCell);
			}
			expowSheet.addRow(expowRow);
			rowIndex++;
		}
	}

	@SuppressWarnings("deprecation")
	private static String getCellValueAsString(Cell cell) {
		//
		String cellValue = "";
		CellType cellType = cell.getCellTypeEnum(); 
		switch (cellType) {  
		case BLANK:
			cellValue = ""; 
			break;
		case BOOLEAN:
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case ERROR:
			cellValue = "ERROR";
			break;
		case FORMULA:
			cellValue = cell.getCellFormula();
			break;
		case NUMERIC:
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		case _NONE:
			cellValue = "NONE";
			break;
		}

		return cellValue;
	}
}