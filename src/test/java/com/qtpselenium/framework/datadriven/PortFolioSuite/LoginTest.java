package com.qtpselenium.framework.datadriven.PortFolioSuite;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import static com.qtpselenium.framework.datadriven.TestBase.*;
import com.qtpselenium.framework.datadriven.pages.LandingPage;
import com.qtpselenium.framework.datadriven.pages.LoginPage;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

public class LoginTest{
	
	

	LoginPage lgPageObj= null;
	TestBase  tbPageObj=null;
	String className;
	private Boolean isTestExecutable=false;
	
	@Test(dataProviderClass=TestDataProvider.class, dataProvider="PortfolioDataProvider")
	public  void LoginTest (Hashtable<String,String> table) throws IOException{
		
		tbPageObj = new TestBase();
		tbPageObj.init();
		
		className = this.getClass().getSimpleName();
		
		isTestExecutable= tbPageObj.validateRunmodes(className, Constants.PORTFOLIO_SUITE,table.get(Constants.RUNMODE_COL) );
		if (isTestExecutable)
			
			tbPageObj.launchApplication(table.get(Constants.BROWSER_COL),prop.getProperty("testSiteLadingPageURL") );
			lgPageObj = new LoginPage(tbPageObj);
			LandingPage pglgPageObj=lgPageObj.skuSearch(table.get(Constants.SEARCHTEXT_COL));
			pglgPageObj.gotoprofile();
			tbPageObj.closeBrowser();
	}

	@AfterTest
	
	public void AfterTestActivity() throws IOException{
		
		APPLICATION_LOG.debug("Test Script "+ className + " is completed");
		prop=null;
		isTestExecutable=false;
		
		
		
	}
	
	


}
