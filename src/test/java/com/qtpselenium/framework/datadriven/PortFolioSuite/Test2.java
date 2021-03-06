package com.qtpselenium.framework.datadriven.PortFolioSuite;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

public class Test2 extends TestBase {

	@Test(dataProviderClass=TestDataProvider.class, dataProvider="PortfolioDataProvider")
	public void test2(Hashtable<String,String> table) throws IOException{
		String className = this.getClass().getSimpleName();
		APPLICATION_LOG.debug("Execution start for:  "+className);
		validateRunmodes(className, Constants.PORTFOLIO_SUITE,table.get(Constants.RUNMODE_COL) );
		
		
		
	}
	
}
