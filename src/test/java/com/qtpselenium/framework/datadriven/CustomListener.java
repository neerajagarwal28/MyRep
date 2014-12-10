package com.qtpselenium.framework.datadriven;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

	public class CustomListener extends TestListenerAdapter implements IInvokedMethodListener{
		// override
		
		
		public void onTestFailure(ITestResult tr){
			// own code
			TestBase.APPLICATION_LOG.debug("Fail - "+ tr.getName());
		}
		
		public void onTestSkipped(ITestResult tr) {
			TestBase.APPLICATION_LOG.debug("Skipped - "+ tr.getName());
		}
		
		public void onTestSuccess(ITestResult tr){
			TestBase.APPLICATION_LOG.debug("Success - "+ tr.getName());
			// report , logs
		}
		
		public void afterInvocation(IInvokedMethod method, ITestResult result) {
			
		}
		
		public void beforeInvocation(IInvokedMethod arg0, ITestResult test) {}

}
