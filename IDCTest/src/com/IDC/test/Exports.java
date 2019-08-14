package com.IDC.test;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Exports {

	
	public static void exportExcell () {
		/**
		 * By using JXL API we can write or gather a data to xls format.
		 * We import the jxl.jar library and create the path
		 * We create a WritableWorkbook in selected file destination
		 * Then we create WritableSheet to which we can add cells
		 * Than we call to write to workbook and close.
		 *  
		 * Ex:
		 * try{
		 * WritableWorkBook workbook = Workbook.createWorkbook(new File("c:\\...));
		 * WritableSheet sheet = workbook.createSheet("Sheet1",0);
		 * 
		 * Label label = new Label(0,0,"Some text");
		 * sheet.addCell(label);
		 * 
		 * Number number = new Number(0,1,45.15);
		 * sheet.addCell(number);
		 * 
		 * workbook.write();
		 * workbook.close();
		 * 
		 * }catch(......)
		 * 
		 */
		
	}
	
	public static void exportCVS () {
		
		/**
		 * Writing CVS file is like normal writing.
		 * Data are separated by comma, tab or custom separators
		 * We open for writing.
		 * we append the text than we close.
		 *  
		 * example of usage of CVSUtils:
		 * 
		 * FileWriter writer = new FileWriter("c:\\.....");
		 * CSVUtils.writeLine(writer, Arrays.asList("Hello", "There", "How ", "R", "You"));
		 * 
		 *         
		 * 
		 * 
		 */
		
	}
	
	

}
