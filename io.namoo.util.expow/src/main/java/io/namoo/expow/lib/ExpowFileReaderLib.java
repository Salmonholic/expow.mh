/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.expow.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.namoo.expow.api.ArrayFile;
import io.namoo.expow.api.ExpowFile;
import io.namoo.expow.api.ExpowFileReader;
import io.namoo.expow.api.ExpowSheet;

public class ExpowFileReaderLib implements ExpowFileReader {
	//
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public ExpowFileReaderLib() {
		// 
	}
	
	@Override
	public ArrayFile readAsArray(String fileName) {
		// 
		ArrayFile arrayFile = new ArrayFileLib(fileName); 
		XSSFWorkbook workbook = openWorkbook(fileName);
		readAllSheetsAsArray(workbook, arrayFile); 
		closeWorkbook(workbook);
		
		return arrayFile; 
	}
	
	@Override
	public ExpowFile read(String fileName) {
		//
		ExpowFileLib expowFile = new ExpowFileLib(fileName); 
		XSSFWorkbook workbook = openWorkbook(fileName);
		readAllSheets(workbook, expowFile);
		closeWorkbook(workbook);
		
		return expowFile; 
	}

	@Override
	public ExpowFile read(String fileName, int sheetIndex) {
		// 
		ExpowFileLib expowFile = new ExpowFileLib(fileName); 
		XSSFWorkbook workbook = openWorkbook(fileName);
		readSheet(workbook, expowFile, sheetIndex);
		closeWorkbook(workbook);	
		
		return expowFile; 
	}
	
	@Override
	public ExpowFile read(String fileName, int sheetIndex, int startIndex, int endIndex) {
		// 
		ExpowFileLib expowFile = new ExpowFileLib(fileName); 
		XSSFWorkbook workbook = openWorkbook(fileName);
		readSheet(workbook, expowFile, sheetIndex, startIndex, endIndex);
		closeWorkbook(workbook);	
		
		return expowFile; 
	}

	private XSSFWorkbook openWorkbook(String fileName) {
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

	private void closeWorkbook(XSSFWorkbook workbook) {
		//
		try {
			workbook.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void readAllSheets(XSSFWorkbook workbook, ExpowFileLib expowFile) {
		//
		int sheetCount = workbook.getNumberOfSheets();
		for (int index = 0; index < sheetCount; index++) {
			XSSFSheet sheet = workbook.getSheetAt(index);
			ExpowSheet expowSheet = new ExpowSheetLib(index, sheet.getSheetName());
			fillSheet(sheet, expowSheet);
			expowFile.addSheet(expowSheet);
		}
	}

	private void readSheet(XSSFWorkbook workbook, ExpowFileLib expowFile, int sheetIndex) {
		//
		int sheetCount = workbook.getNumberOfSheets();
		for (int index = 0; index < sheetCount; index++) {
			if (index != sheetIndex) {
				continue; 
			}
			
			XSSFSheet sheet = workbook.getSheetAt(index);
			ExpowSheet expowSheet = new ExpowSheetLib(index, sheet.getSheetName());
			fillSheet(sheet, expowSheet); 
			expowFile.addSheet(expowSheet);
			break; 
		}
	}

	private void readSheet(XSSFWorkbook workbook, ExpowFileLib expowFile, int sheetIndex, int startIndex, int endIndex) {
		//
		int sheetCount = workbook.getNumberOfSheets();
		for (int index = 0; index < sheetCount; index++) {
			if (index != sheetIndex) {
				continue; 
			}
			
			XSSFSheet sheet = workbook.getSheetAt(index);
			ExpowSheet expowSheet = new ExpowSheetLib(index, sheet.getSheetName());
			fillSheet(sheet, expowSheet, startIndex, endIndex); 
			expowFile.addSheet(expowSheet);
			break; 
		}
	}

	private void readAllSheetsAsArray(XSSFWorkbook workbook, ArrayFile arrayFile) {
		//
		int sheetCount = workbook.getNumberOfSheets();
		for (int index = 0; index < sheetCount; index++) {
			XSSFSheet sheet = workbook.getSheetAt(index);
			ArraySheetLib arraySheet = new ArraySheetLib(index, sheet.getSheetName());
			fillSheetAsArray(sheet, arraySheet);
			((ArrayFileLib)arrayFile).addSheet(arraySheet);
		}
	}

	private void fillSheetAsArray(XSSFSheet sheet, ArraySheetLib arraySheet) {
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
	private void fillSheet(XSSFSheet sheet, ExpowSheet expowSheet) {
		//
		Iterator<Row> rowIter = sheet.iterator();

		int rowIndex = 0;
		while (rowIter.hasNext()) {
			Row row = rowIter.next();
			ExpowRowLib expowRow = new ExpowRowLib(rowIndex);
			
			int lastColumn = row.getLastCellNum(); 
			for(int columnIndex = 0; columnIndex<lastColumn; columnIndex++) {
				// 
				Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK); 

				ExpowCellLib powCell = new ExpowCellLib(
						rowIndex, 
						columnIndex, 
						cell.getCellTypeEnum(), 
						getCellValueAsString(cell));
				expowRow.addCell(powCell);
				expowSheet.requestColumnExpanding(columnIndex).addCell(powCell);
			}
			
			expowSheet.addRow(expowRow);
			rowIndex++;
		}
	}

	@SuppressWarnings("deprecation")
	private void fillSheet(XSSFSheet sheet, ExpowSheet expowSheet, int startIndex, int endIndex) {
		//
		Iterator<Row> rowIter = sheet.iterator();

		int rowIndex = 0;
		while (rowIter.hasNext()) {
			if (rowIndex == startIndex) {
				break; 
			}
			rowIter.next();
			rowIndex++; 
		}
		
		while(rowIter.hasNext()) {
			if (rowIndex == endIndex) {
				break; 
			}
			
			Row row = rowIter.next(); 
			ExpowRowLib expowRow = new ExpowRowLib(rowIndex);
			
			int lastColumn = row.getLastCellNum(); 
			for(int columnIndex = 0; columnIndex<lastColumn; columnIndex++) {
				// 
				Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK); 

				ExpowCellLib powCell = new ExpowCellLib(
						rowIndex, 
						columnIndex, 
						cell.getCellTypeEnum(), 
						getCellValueAsString(cell));
				expowRow.addCell(powCell);
				expowSheet.requestColumnExpanding(columnIndex).addCell(powCell);
			}
			
			expowSheet.addRow(expowRow);
			rowIndex++;
		}
	}

	@SuppressWarnings("deprecation")
	private String getCellValueAsString(Cell cell) {
		//
		String cellValue = "";
		switch (cell.getCellTypeEnum()) {  
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
			if (DateUtil.isCellDateFormatted(cell)) {
				cellValue = dateFormat.format(cell.getDateCellValue()); 
			} else {
				cellValue = String.valueOf(cell.getNumericCellValue());
			}
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