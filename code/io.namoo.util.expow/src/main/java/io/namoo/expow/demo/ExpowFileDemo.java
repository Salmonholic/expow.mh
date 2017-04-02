/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.expow.demo;

import java.util.Iterator;

import io.namoo.expow.api.ExpowCell;
import io.namoo.expow.api.ExpowColumn;
import io.namoo.expow.api.ExpowFile;
import io.namoo.expow.api.ExpowFileReader;
import io.namoo.expow.api.ExpowRow;
import io.namoo.expow.api.ExpowSheet;

public class ExpowFileDemo {
	//
	public static void main(String[] args) { 
		//
		String fileName = "./src/test/resources/SkillRoleMap.xlsx";
		ExpowFile file = ExpowFileReader.read(fileName); 
		ExpowSheet powSheet = file.requestSheet("SoftwareDevelopment"); 
		// readColumnTest(powSheet); 
		readRowTest(powSheet); 
	}
	
	public static void readColumnTest(ExpowSheet powSheet) {
		//
		ExpowColumn skillColumn = powSheet.requestColumnKeyColumn("단위기술");
		Iterator<ExpowCell> columnCellIter = skillColumn.requestCellsFrom("단위기술"); 
		while(columnCellIter.hasNext()) {
			ExpowCell columnCell = columnCellIter.next(); 
			System.out.println(columnCell.toString()); 
			// System.out.println(columnCell.toPrettyJson()); 
		}
	}

	public static void readRowTest(ExpowSheet powSheet) {
		//
		ExpowRow roleRow = powSheet.requestRowKeyRow("Java");
		Iterator<ExpowCell> rowCellIter = roleRow.requestCellsIteratorFrom("Java");
		while(rowCellIter.hasNext()) {
			ExpowCell rowCell = rowCellIter.next(); 
			System.out.println(rowCell.toString());
			// System.out.println(rowCell.toJson());
			
			//if (!cell.isEmpty()) {
			//	ExpowRow skillRow = powSheet.requestRowKeyRow(cell.getValue()); 
			//	ExpowCell rowCell = skillRow.requestCell(cell.getColumnIndex()+2); 
			// }
		}
		
//		ListIterator<PowCell> cellIter = powSheet.requestAllUnderCellsOf("서버개발자(L39)"); 
//		while(cellIter.hasNext()) {
//			System.out.println("--> " + cellIter.next().toString()); 
//		}
		
		// System.out.println(excelSheet.toString()); 
//		ExpowRow javaRow = excelSheet.requestRowKeyRow("Java"); 
//		System.out.println(javaRow.toString()); 
//		ExpowRow umlRow = excelSheet.requestRowKeyRow("UML"); 
//		System.out.println(umlRow.toString()); 
//		powSheet.setColumnKey("단위기술"); 
//		ExpowColumn skillColumn = powSheet.requestColumnKeyColumn("단위기술");
//		System.out.println(skillColumn.toString()); 
//		ExpowColumn baseColumn = excelSheet.requestColumnKeyColumn("SW 개발자(L12)");  
//		System.out.println(baseColumn.toString());
//		PowCell targetCell = powSheet.requestRightCellOf("Java");
//		System.out.println(targetCell.toString()); 
	}
}