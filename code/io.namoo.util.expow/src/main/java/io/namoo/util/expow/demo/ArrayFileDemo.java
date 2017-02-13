/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.util.expow.demo;

import io.namoo.util.expow.api.ArrayFile;
import io.namoo.util.expow.api.ArraySheet;
import io.namoo.util.expow.api.ExpowFileReader;

public class ArrayFileDemo {
	//
	public static void main(String[] args) { 
		//
		String fileName = "./src/test/resources/SkillRoleMap.xlsx";
		ArrayFile file = ExpowFileReader.readAsArray(fileName); 
		ArraySheet arraySheet = file.requestSheet("SoftwareDevelopment"); 
		
		//System.out.print(arraySheet.toPrettyTable());
		// System.out.print((new Gson()).toJson(arraySheet.requestValueCoordinates("Mandatory")));  
		// readColumnTest(arraySheet); 
		readRowTest(arraySheet); 
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
	}
}