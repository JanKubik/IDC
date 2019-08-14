package com.IDC.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Class DataIO provides variety of functionalities
 * to read a .dat file
 * to separate data for each column
 * to calculate a market share for given period time
 * to calculate a total sales for given period time
 * to find a number of row in the table for given vendor * 
 * @author Jan Kubik 
 * @category job application 2019/14/08
 *
 */
public class DataIO {
	
	static protected String filePath =null;
	static protected String country =null;
	static protected String time =null;
	
	List<String[]>record = new ArrayList<String[]>();
	List<String>salesUnits = new ArrayList<String>();
	List<String>vendsSortByUnits = new ArrayList<String>();
	List<String>sharesSortByUnits = new ArrayList<String>();
	 List<Float>descUnits = new ArrayList<Float>();
	Set<String> vendor = new HashSet<>();
	Set<String> countries = new HashSet<>();
	Set<String> timeScale = new HashSet<>();
	
	private String[] columnHeaders =new String[5]; 
	private int rwc = 0;
	private int indexVendor = 0;
	private int indexCountry = 0;
	private int indexTimeScale= 0;
	private int indexUnits = 0;
	private float unitTotal = 0;
	
	/**
	 * 	DataIO(String path,String country,String time)
	 *  constructor	
	 *  
	 */
	public DataIO(String path,String counTry,String tiMe){
	filePath = path;
	country = counTry;
	time = tiMe;
	
	readDatFile();	
	getVendorsList();
	vendorSales();
	getShares();
}	
	
	/**
	 * 	List<String[]> readDatFile()
	 *  Reads .dat file 
	 *  Returns List of records broken down to  String arrays
	 */
	public List<String[]> readDatFile(){
		
		String temp =null;
		String vd = "Vendor";
		String ts = "Timescale";
		String ct = "Country";				
		
	try
	{
		BufferedReader br = new BufferedReader(new FileReader(filePath));
			
		while ((temp = br.readLine()) != null)		// read throuh all .dat file by lines
		{
			
			String[] columnNames = temp.split("\\s*,\\s*");  // splits raw to columns Strings -trims whitespaces
						
			if(rwc==0) {
			columnHeaders=columnNames.clone();  // list of Headers
			
			for(int i=0; i<= columnNames.length-1;i++){
				
				switch(columnNames[i]) {		// assign indexes to header names
				case "Country" :
					indexCountry = i;
					break;				
				case "Timescale" :
					indexTimeScale = i;
					break;					
				case "Vendor" :
					indexVendor =i;
					break;					
				case "Units" :
					indexUnits = i;
					break;					
				default:
					System.out.println("No columnName in switch");
				}				
			}		    
		}			

		if(vd.equalsIgnoreCase(columnNames[indexVendor])==false) // list of Vendors- excluding header
		vendor.add(columnNames[indexVendor]); 
		
		if(ct.equalsIgnoreCase(columnNames[indexCountry])==false) // list of Country- excluding header
			countries.add(columnNames[indexCountry]);
		
		if(ts.equalsIgnoreCase(columnNames[indexTimeScale])==false) // list of TimeScale- excluding header
			timeScale.add(columnNames[indexTimeScale]);
			
			record.add(columnNames);
			rwc++;
	}
		br.close();	
		
	}
	catch (FileNotFoundException e)
	{
		System.out.print("No file found");
	}
	catch (IOException e)
	{
		System.out.print("There was a problem to read a file");
	}
	return record;
}
	
	/**
	 * 	String[] vendorSales()
	 *  For each ordered Vendor finds a sale Units based on region and time 
	 */
	
	public String[] vendorSales(){
		
		int i= 0;
		float unit = 0;
		
		String vendorSales[] = new String[getNoOfVendors()];
		String [] rw = new String[getNoOfVendors()];		
	
		for(String vd : getVendorsList()) {				//iterate through vendors and records
				
			for(String[] rc: record) {
				rw = rc;
		
				String jrw = String.join(" ", rw); //connecting strings to one easy search
			
					if ((jrw.contains(country)) && (jrw.contains(time)) && (jrw.contains(vd))) {
					unit = unit + Float.parseFloat(rw[indexUnits]);	
			}
		}
		vendorSales[i]=String.format ("%.3f", unit);
		
		i++;
		unitTotal=unitTotal+unit;
		unit=0;
		}
		 salesUnits = Arrays.asList(vendorSales);
		
		return vendorSales;
	}	
	
	/** 
	 * Returns a List of sorted Vendors- alphabetically 
	 */
	public List<String> getVendorsList() {
		
		List<String>sortedVendors = new ArrayList<String>(vendor);
		Collections.sort(sortedVendors,String.CASE_INSENSITIVE_ORDER);
		return sortedVendors ;
	}	
	
	/**
	 *  Returns Set of countries
	 */
	public Set<String> getCountriesList() {
		return countries;
	}
	
	/**
	 * Set<String> getTimeList()
	 *  Returns Set of time-periods
	 */
	public Set<String> getTimeList() {
		return timeScale;
	}
	
	/**
	 * String[] getColumnHeaders()
	 * Returns array of the columns name
	 */
    public String[] getColumnHeaders() {
    	return columnHeaders;
    }
    
    /**
     * Set<String> getTimeList()
     *  returns number of records in the .dat file
     */
    public int getRowCount() {
        return rwc;
    }	
    
    /**
     * Set<String> getTimeList()
     *  returns a List of sold units - indexed by sorted Vendor
     */
    public List<String>getUnits(){
    	return salesUnits;
    }    
	
    /**
     *  String getFilePath()
     *  returns the filepath of source of .dat file
     */
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 *  int getNoOfVendors()
	 *  returns the number of Vendors in .dat file
	 */
	public int getNoOfVendors(){
		return getVendorsList().size();
	}
	
	/**
	 *  Float[] getShares()
	 *  Returns Float number of % of market shares - indexed by Vendors
	 */
	public Float[] getShares() {		
		Float[] shares = new Float[getNoOfVendors()];
		for(int i=0; i<=getNoOfVendors()-1;i++ ) {
			shares[i]=(Float.parseFloat(salesUnits.get(i)))/((unitTotal)/100);
			DecimalFormat decimalFormat = new DecimalFormat("#.#");			
			shares[i]=Float.valueOf(decimalFormat.format(shares[i])); 
		//	System.out.println("Vendor\t " + getVendorsList().get(i) + "\t Sales\t\t " + salesUnits.get(i) + "\t Shares\t\t "+ shares[i]);
		}				
		return shares;
	}	
	
	/**
	 *  int getIndexOfVendor(String)
	 *  Returns index -int number for given parameter Vendor
	 */
	public int getIndexOfVendor(String vendors) {
		String vendor = vendors;
		
		int index =0;
		List<String> vList =getVendorsList();
	
		try {
			for (int i = 0; i <= getVendorsList().size(); i++) {
				if (vList.get(i).equals(vendor)) {
				index = i;
				break;
				}
			} 
	} catch (Exception e) {
		System.out.println("No vendor's name found in the list of vendors");
	}
	return index;
	}

    /**
     * int getIndexRaw(String)
     * Returns number of raw from table for parameter Vendor 
     */
	public int getIndexRaw(String vendors) {
		try {
			String vendor = vendors;
			return (getIndexOfVendor(vendor)+1);
		} catch (Exception e) {
			System.out.println("No existing vendor ");
			return -1;
		}	
	}

	/**
	 * String getTotalUnits()
	 * Returns String value of total sales in .dat file 
	 */	
	public String getTotalUnits() {
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String unitotals = decimalFormat.format(unitTotal);
		return unitotals;
	}
	
	/**
	 * String getVendorSalesShares(String vendor)
	 * Returns String value of sales on market share for given vendor 
	 */	
	public String getVendorSalesShares(String vendor) {
		String venDor = vendor;
		int indexVendor= getIndexOfVendor(venDor);		
		String vendorSales=vendorSales()[indexVendor];
		Float shares = getShares()[indexVendor];
		
		String vendorSalesShares= "Vendor:\t"+vendor+"\t" + "Sales:\t"+ vendorSales+"\t MarketShare:\t" +shares ;
		return vendorSalesShares;
	}
	
	/**
	 * sortUnits())
	 * Sorts the units in descendant manner
	 */	
	public List<Float> sortUnits() {
		
		 float temporary=0;
		 
		 for(String temp:getUnits()) {
			descUnits.add((Float.parseFloat(temp))*-1) ;
		 }
		  Collections.sort(descUnits);
		  
		  for(int i=0;i<descUnits.size();i++) {			  	
			temporary= (descUnits.get(i)*-1);
			descUnits.set(i,temporary);			
		  }
		  
		// System.out.println(descUnits);
		 
		 return descUnits;
		
		}
	
	public void indexShift() {
		
		for(int i=0;i<getUnits().size();i++) {
			for(int y=0;y<getUnits().size();y++) {
			if	(sortUnits().get(i).toString().equals(getUnits().get(y))){
			/*if(vendsSortByUnits.get(i).isBlank())*/ vendsSortByUnits.add(i, getUnits().get(y));
			/*	if(sharesSortByUnits.get(i).isEmpty()) */sharesSortByUnits.add(i, getUnits().get(y));				
				}
			}			
		//	System.out.println("Vendor\t"+getUnits().get(i)+"\t Units\t"+vendsSortByUnits.get(i)+"\tShares\t"+sharesSortByUnits.get(i));
		}
		
		
	}
	
}
