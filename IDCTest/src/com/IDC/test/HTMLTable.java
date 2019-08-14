package com.IDC.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class HTMLTable provides construction of HTML table
 * @author Jan Kubik 
 * @category job application 2019/14/08
 *
 */

public class HTMLTable extends DataIO {
	
	/*
	 * Constructor
	 */
	HTMLTable(String path, String country, String time) {
		super(path, country, time);
	
	htmlTable();	 
	}	
	 
	/**
	 * 	htmlTable(),
	 * 	Creates table,
	 * input data via 	getVendorsList(),
	 * 					getUnits(),
	 * 					getShares()
	 */
	public void htmlTable() {
		
		List<String> v = getVendorsList();
		List<String> u = getUnits();
		Float[] s = getShares();
		
		File files = new File ("c:\\Users\\Public\\Documents\\table_"+ country.substring(0,3)+"_"+time.replaceAll("\\s+","")+".html");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(files));
			
			bw.write("<html>\r\n" +
					"<head>\r\n" +
					"<style>\r\n" +
					"table {\r\n" +
					"	border-collapse: collapse;\r\n" +
					"	width: 70%; \r\n"+
					"}\r\n"	+
					"table, td, th {\r\n" +
					"   border: 1px solid black;\r\n" +
					"}\r\n"+
					"th {\r\n" +
					"   background-color: #f5f5f5;\r\n" +
					"}\r\n"+
					"table, td, th {\r\n" +
					"	height: 30px;\r\n" + 
					"	vertical-align: center;\r\n" +					
					"}\r\n"+
					"</style>\r\n" +
					"</head>\r\n"+
				    "<body>\r\n" +
					"<font face='Times New Roman'"+
				    "size =3>\r\n" + " Table 1, PC Quarterly Market Share,"+ country + 
				    " , " + time + "\r\n" +
				    "</font>\r\n"+
				    "<table>\r\n" +
				       "<tr>\r\n"+
				       "<th><center>Vendor</center></th>\r\n" +
				       "<th><center>Units</center></th>\r\n" +
				       "<th><center>Share</center></th>\r\n" +
				       "</tr>\r\n" +
				       "<tr>");
				      				       
 		for(int i=0;i<=getNoOfVendors()-1;i++) {
 			 			
 			bw.write("<td><center> " + v.get(i) + "</center> </td>\r\n" +
 					"<td><center> " + u.get(i) + "</center> </td>\r\n" +
 					"<td><center> " + s[i] +" %" + "</center> </td>\r\n" +
 					"</tr>\r\n" +
 					"<tr>\r\n");
 				}
 		
 		
bw.write(			"<td><center> Total </center></td>\r\n"+
					"<td><center>"+ getTotalUnits() +"</center></td>\r\n"+
					"<td><center> 100 % </center></td>\r\n"+
					"</tr>" + 
				    "</table>" + 
				    "</body>"  + 
				    "</html>");
			bw.close();
			
		} catch (IOException e) {
			System.out.print("Opening writer problem");
			e.printStackTrace();
		}
		
			
	}

}