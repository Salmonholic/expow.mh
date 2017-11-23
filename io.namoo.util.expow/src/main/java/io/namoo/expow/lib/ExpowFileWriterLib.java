/*******************************************************************************
 * Copyright(c) 2015-2020 Incheon International Airport Corporation. 
 * All rights reserved. This software is the proprietary information of 
 * Incheon International Airport Corporation.
 *******************************************************************************/
package io.namoo.expow.lib;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.namoo.expow.api.ArraySheet;
import io.namoo.expow.api.ExpowCell;
import io.namoo.expow.api.ExpowFile;
import io.namoo.expow.api.ExpowFileReader;
import io.namoo.expow.api.ExpowFileReaderFactory;
import io.namoo.expow.api.ExpowFileWriter;
import io.namoo.expow.api.ExpowRow;
import io.namoo.expow.api.ExpowSheet;

/**
 *
 * @author <a href="mailto:mhjang@nextree.co.kr">Jang, Mihyeon</a>
 * @since 2017. 11. 21.
 */
public class ExpowFileWriterLib implements ExpowFileWriter {
    //
    private ExpowFileReader reader;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public ExpowFileWriterLib() {
        //
        this.reader = ExpowFileReaderFactory.newInstance();
    }
    
    @Override
    public void writeAsArray(ArraySheet arraySheet) {
        //
        
    }

    @Override
    public void write(String fileName, ExpowFile file) {
        //
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            XSSFWorkbook workbook = converToWorkbook(file);
            workbook.write(fos);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @Override
    public void write(String fileName, ExpowSheet sheet) {
        //
        ExpowFile oldFile  = reader.read(fileName);
        int sheetIndex = sheet.getSheetIndex();
        ExpowSheet oldSheet = oldFile.requestSheet(sheetIndex);
    }

    @Override
    public void write(String fileName, int sheetIndex, int startIndex, int endIndex, List<ExpowRow> rows) {
        // TODO Auto-generated method stub
        
    }
    
    private XSSFWorkbook converToWorkbook(ExpowFile file) {
        //
        XSSFWorkbook workbook = new XSSFWorkbook();
        int sheetCount = file.countSheet();
        for (int sheetIndex = 0; sheetIndex<sheetCount; sheetIndex++) {
            ExpowSheet powSheet = file.requestSheet(sheetIndex);
            XSSFSheet sheet = workbook.createSheet(powSheet.getSheetName());
            sheet = fillSheet(sheet, powSheet);
        }
        
        return workbook;
    }
    
    private XSSFSheet fillSheet(XSSFSheet sheet, ExpowSheet powSheet) {
        //
        int rowCount = powSheet.countRow();
        for (int rowIndex = 0; rowIndex<rowCount; rowIndex++) {
            XSSFRow row = sheet.createRow(rowIndex);
            ExpowRow powRow = powSheet.requestRow(rowIndex);
            int cellCount = powRow.countCells();
            for (int columnIndex=0; columnIndex<cellCount; columnIndex++) {
                ExpowCell powCell = powRow.requestCell(columnIndex);
                Cell cell = row.createCell(columnIndex);
                CellType cellType = getCellType(powCell);
                cell.setCellType(cellType);
                String value = powCell.getValue();
                cell.setCellValue(value);
            }
        }
        return sheet;
    }
    
    @SuppressWarnings("deprecation")
    private CellType getCellType(ExpowCell cell) {
        //
        CellType cellType = CellType._NONE;
        
        switch (cell.getCellTypeAsStr()) {
        case "CELL_TYPE_BLANK":
            cellType = CellType.BLANK;
            break;
        case "CELL_TYPE_BOOLEAN":
            cellType = CellType.BOOLEAN;
            break;
        case "CELL_TYPE_ERROR":
            cellType = CellType.ERROR;
            break;
        case "CELL_TYPE_FORMULA":
            cellType = CellType.FORMULA;
            break;
        case "CELL_TYPE_NUMERIC":
            cellType = CellType.NUMERIC;
            break;
        case "CELL_TYPE_STRING":
            cellType = CellType.STRING;
            break;
        default:
            cellType = CellType._NONE;
        }
        
        return cellType;
    }

}
