package com.qtpselenium.framework.datadriven.pages;

import org.openqa.selenium.WebDriver;

import com.qtpselenium.framework.datadriven.TestBase;
import static com.qtpselenium.framework.datadriven.TestBase.*;

public class LandingPage {
	
	
	TestBase tb;
	WebDriver driver;
	
	public LandingPage(TestBase tb) {
		this.tb=tb;
		this.driver=tb.driver;
		// TODO Auto-generated constructor stub
	}

	
	public void gotoprofile(){
		
		APPLICATION_LOG.debug("Landing Page: Gotot Profile");
		System.out.println("Landing Page: Gotot Profile");
	}
	
	

}
