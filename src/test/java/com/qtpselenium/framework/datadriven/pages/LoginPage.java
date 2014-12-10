package com.qtpselenium.framework.datadriven.pages;

import static com.qtpselenium.framework.datadriven.TestBase.APPLICATION_LOG;

import org.openqa.selenium.WebDriver;

import com.qtpselenium.framework.datadriven.MyWebElement;
import com.qtpselenium.framework.datadriven.TestBase;
import static com.qtpselenium.framework.datadriven.TestBase.*;

public class LoginPage  {

	TestBase tb;
	WebDriver driver;
	
	public LoginPage(TestBase tb) {
		this.tb=tb;
		this.driver=tb.driver;
		// TODO Auto-generated constructor stub
	}

		

		public  MyWebElement getElement(String CaseID){
		
			APPLICATION_LOG.debug("CaseID to find element property "+ CaseID);
				switch (CaseID){
		
				case "SearchText":
					APPLICATION_LOG.debug("Entered into SearchText" );
					return new MyWebElement(driver, "xpath", ".//*[@id='searchkey']" );
					
				case "SearchSubmit":
					APPLICATION_LOG.debug("Entered into SearchSubmit" );
						return new MyWebElement(driver, "xpath", ".//*[@id='sform']/input[5]");
		}
				return null;
		
		}
	
	public void registerUser(){
		
		
	}

	public LandingPage skuSearch(String searchText) {
		APPLICATION_LOG.debug("LoginPage");
		//driver.get(prop.getProperty("testSiteLadingPageURL"));
	
		APPLICATION_LOG.debug("Search text item is "+ searchText);
		tb.setText(getElement("SearchText"),searchText );
		
		tb.clickButton(getElement("SearchSubmit"));
		APPLICATION_LOG.debug("Search text item is clicked");
		
		return  new LandingPage(tb) ;
	}
	

}
