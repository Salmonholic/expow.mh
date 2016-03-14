package io.namoo.util.expow.demo;

import java.util.Iterator;

import io.namoo.util.expow.api.ExpowCell;
import io.namoo.util.expow.api.ExpowColumn;
import io.namoo.util.expow.api.ExpowFile;
import io.namoo.util.expow.api.ExpowFileReader;
import io.namoo.util.expow.api.ExpowRow;
import io.namoo.util.expow.api.ExpowSheet;

public class ExpowFileDemo {
	//
	public static void main(String[] args) { 
		//
		String fileName = "./src/test/resources/SK_Skill_Role_Map_151021.xlsx";
		ExpowFile file = ExpowFileReader.read(fileName); 
		ExpowSheet powSheet = file.requestSheet("SoftwareDevelopment"); 
		readColumnTest(powSheet); 
		readRowTest(powSheet); 
	}
	
	public static void readColumnTest(ExpowSheet powSheet) {
		//
		ExpowColumn skillColumn = powSheet.requestColumnKeyColumn("단위기술");
		Iterator<ExpowCell> cellIter = skillColumn.requestCellsFrom("단위기술"); 
		while(cellIter.hasNext()) {
			ExpowCell cell = cellIter.next(); 
			if (!cell.isEmpty()) {
				ExpowRow skillRow = powSheet.requestRowKeyRow(cell.getValue()); 
				ExpowCell rowCell = skillRow.requestCell(cell.getColumnIndex()+2); 
				System.out.println(rowCell.toString()); 
			}
		}
	}

	public static void readRowTest(ExpowSheet powSheet) {
		//
		ExpowRow roleRow = powSheet.requestRowKeyRow("요구조건");
		Iterator<ExpowCell> cellIter = roleRow.requestCellsFrom("요구조건");
		while(cellIter.hasNext()) {
			ExpowCell cell = cellIter.next(); 
			if (!cell.isEmpty()) {
				ExpowRow skillRow = powSheet.requestRowKeyRow(cell.getValue()); 
				ExpowCell rowCell = skillRow.requestCell(cell.getColumnIndex()+2); 
			}
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