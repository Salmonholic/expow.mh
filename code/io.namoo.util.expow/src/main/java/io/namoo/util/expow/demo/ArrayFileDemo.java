package io.namoo.util.expow.demo;

import com.google.gson.Gson;

import io.namoo.util.expow.api.ArrayFile;
import io.namoo.util.expow.api.ArraySheet;
import io.namoo.util.expow.api.ExpowFileReader;

public class ArrayFileDemo {
	//
	public static void main(String[] args) { 
		//
		String fileName = "./src/test/resources/SK_Skill_Role_Map_151021.xlsx";
		ArrayFile file = ExpowFileReader.readAsArray(fileName); 
		ArraySheet arraySheet = file.requestSheet("SoftwareDevelopment"); 
		
		//System.out.print(arraySheet.toPrettyTable());
		System.out.print((new Gson()).toJson(arraySheet.requestValueCoordinates("Mandatory")));  
		// readColumnTest(arraySheet); 
		// readRowTest(arraySheet); 
	}
	
	public static void readColumnTest(ArraySheet arraySheet) {
		//
		System.out.println(arraySheet.toPrettyColumn(2));
	}

	public static void readRowTest(ArraySheet arraySheet) {
		//

		for(int i=0; i<arraySheet.countRow(); i++) {
			System.out.print(arraySheet.toPrettyRow(i)); 
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