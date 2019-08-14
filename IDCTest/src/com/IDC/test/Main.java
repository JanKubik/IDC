package com.IDC.test;

public class Main {

	public static void main(String[] args) {
		
		/*	Task a) - object created and ready to perform table creation for given country and time
		 * 
		 * Add your path to .dat file	 */		
		
		
		DataIO dataSet  =new DataIO("F:\\ProjectICD\\Data.dat", "Czech Republic","2010 Q4");
		
		
		// Task b) - gathering information for given Vendor
		
		System.out.println(dataSet.getVendorSalesShares("Dell"));
		
		
		// Task c) - gathering No of row for given Vendor
		
		System.out.println("Row number in table for given vendor is:" + dataSet.getIndexRaw("Dell"));
		
		
		/* Task d)
		 * 
		 * Method getVendorsList() sorts the Vendors alphabetically.  Than Units and Shares are assigned.*/
		
		
		/* Task e)
		 * 
		 * 	My appology, not finished due to lack of my free time
		 *  The Idea behind the scene is:
		 *  As three Arrays are in relation, I am thinking to make a new Array of sorted Units. -Done
		 *  than to compare it to original one, this would give me the index of previous place.
		 *  Then, Vendors and Shares on that old place will populate new arrays on corresponding new address
		 *   
		 *  so far work in progress:
		
			dataSet.indexShift();
		
		
		/* Task f)
		 * HTML file has been created as default on disc c:\Users\Public\Documents\	 */
		
		HTMLTable table =new HTMLTable("F:\\ProjectICD\\Data.dat", "Czech Republic","2010 Q3");
		
		/*Task g)
		 * Export class created with corresponding functions and description */
		
	}

	}


