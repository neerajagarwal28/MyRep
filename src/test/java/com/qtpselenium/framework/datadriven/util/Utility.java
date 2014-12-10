package com.qtpselenium.framework.datadriven.util;

import java.io.IOException;
import java.util.Hashtable;


public class Utility {
	

	// finds if the test suite is runnable 
		public static boolean isSuiteRunnable(Xls_Reader xls , String suiteName){
			boolean isExecutable=false;
			
			for(int rNum=2; rNum <= xls.getRowCount(Constants.SUITE_SHEET) ;rNum++ ){
				
			
				if(xls.getCellData(Constants.SUITE_SHEET, Constants.SUITENAME_COL, rNum).equalsIgnoreCase(suiteName)){
					if(xls.getCellData(Constants.SUITE_SHEET, Constants.RUNMODE_COL, rNum).equalsIgnoreCase("Y")){
						isExecutable=true;
					}else{
						isExecutable=false;
					}
				}

			}
			xls=null; // release memory
			return isExecutable;
			
		}
		
		
		// returns true if runmode of the test is equal to Y
		public static boolean isTestCaseRunnable(Xls_Reader xls, String testCaseName) throws IOException{
			boolean isExecutable=false;
			for(int rNum=2; rNum<= xls.getRowCount(Constants.TESTCASES_SHEET) ; rNum++){
				if(xls.getCellData(Constants.TESTCASES_SHEET, Constants.TESTCASES_COL, rNum).equalsIgnoreCase(testCaseName)){
					if(xls.getCellData(Constants.TESTCASES_SHEET, Constants.RUNMODE_COL, rNum).equalsIgnoreCase("Y")){
						isExecutable= true;
						//getData(xls,testCaseName );
					}else{
						isExecutable= false;
					}
				}
			}
			
			return isExecutable;
			
		}
		
		//Function is used to getdata from excel sheet for the test case.
		
		public static Object[][] getData(Xls_Reader xls,  String testCaseName) throws IOException
		
		{
			int testCaseRowNum=1;
			// Test case start from row no
			for(testCaseRowNum=1;testCaseRowNum<xls.getRowCount(Constants.DATA_SHEET);testCaseRowNum++)
			{
				if(xls.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum).equalsIgnoreCase(testCaseName))
					break;
			}
			//How Many Colum Count
			
			int dataStartRowNum=testCaseRowNum+2;
			int dataColStartRowNum=testCaseRowNum+1;
			
			//rows of the data
			int testRow=1;
			while(!xls.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum+testRow).equalsIgnoreCase(""))
			{
				testRow++;
			}
			//System.out.println("Total DataRow for Testcase:  "  + testCaseName + " is:  "+ testRow);
			
			int testCol=0;
			
			while(!xls.getCellData(Constants.DATA_SHEET, testCol, dataColStartRowNum).equalsIgnoreCase(""))
			{
				testCol++;
			}
			
			//System.out.println("Total Column for Testcase: "  + testCaseName + " is: "+testCol);
			
			Object data[][] = new Object[testRow][1];
			int r=0;
			for(int rNum=dataStartRowNum;rNum<(dataStartRowNum+testRow);rNum++){
				Hashtable<String,String> table= new Hashtable<String,String>();
			
					for (int cNum=0;cNum<testCol;cNum++)
					{
						table.put(xls.getCellData(Constants.DATA_SHEET, cNum, dataColStartRowNum), xls.getCellData(Constants.DATA_SHEET, cNum, rNum));	
					}
					
					data[r][0]=table;
					r++;
			}
			
			return data;
		}		
}

