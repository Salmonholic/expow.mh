package io.namoo.expow.lib;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.namoo.expow.api.ArraySheet;
import io.namoo.expow.api.ExpowCell;
import io.namoo.expow.api.ExpowFile;
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
    public ExpowFileWriterLib() {
        //
    }
    
    @Override
    public void writeAsArray(ArraySheet arraySheet) {
        // not yet
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
