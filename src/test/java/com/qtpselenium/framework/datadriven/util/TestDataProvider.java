package com.qtpselenium.framework.datadriven.util;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	
	@DataProvider(name="PortfolioDataProvider")
	public static Object[][] getDataSuiteA(Method m) throws IOException{
		
		Xls_Reader xls1= new Xls_Reader(System.getProperty("user.dir")+"\\"+Constants.PORTFOLIO_SUITE+".xls");
		return Utility.getData(xls1, m.getName());
		
		
	}
	@DataProvider(name="suiteBDataProvider")
	public static Object[][] getDataSuiteB(Method m) throws IOException{
		
		Xls_Reader xls1= new Xls_Reader(System.getProperty("user.dir")+"\\"+Constants.SUITEB_SUITE+".xls");
		return Utility.getData(xls1, m.getName());
		
		
	}
	

}
