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
import io.namoo.expow.api.ExpowRow;
import io.namoo.expow.api.ExpowSheet;
import io.namoo.expow.lib.ExpowFileReader;

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
			
		}
	}
}