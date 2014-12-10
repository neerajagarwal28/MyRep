package com.qtpselenium.framework.datadriven;

import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.qtpselenium.framework.datadriven.util.Utility;
import com.qtpselenium.framework.datadriven.util.Xls_Reader;
//import org.apache.http.client.fluent.Request;


public class TestBase  {
	
	public static Properties prop;
	public static Logger APPLICATION_LOG = Logger.getLogger("devpinoyLogger");
	public   WebDriver driver;
	public  MyWebElement  element;
	WebDriverWait wait;
	public Actions builder = null;
	

	
	
	public void init() throws IOException{
				
		if (prop==null){
			String path=System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties";
			 prop= new Properties();
				try{
						FileInputStream fs = new FileInputStream(path);
						prop.load(fs);
					}catch(Exception e){
						e.printStackTrace();
					}
		}
	}
	
	public boolean validateRunmodes(String testName, String suiteName, String dataRunmode) throws IOException{
		BasicConfigurator.configure();
		boolean validateRunmodes= false;

		APPLICATION_LOG.debug("Validating runmode for:  "+ testName + " in Suite Name: "+ suiteName);
		
		//Suitre Run Mode
		
		boolean isSuiteRunMode=Utility.isSuiteRunnable(new Xls_Reader(System.getProperty("user.dir")+"\\Suite.xls"), suiteName);
		boolean isTestRunMode=Utility.isTestCaseRunnable(new Xls_Reader(System.getProperty("user.dir")+"\\"+ suiteName+".xls"), testName);		
		boolean dataSetRunmode=false;
		if(dataRunmode.equalsIgnoreCase("Y"))
			dataSetRunmode=true;
		if(!(isSuiteRunMode && isTestRunMode && dataSetRunmode))
			
			try{
				
			throw new SkipException("Skipping The test "+ testName + " inside the suite " + suiteName);
			
			}catch(Exception e){
				System.out.println("isSuiteRunMode:  "+ isSuiteRunMode + "   isTestRunMode:  "+ isTestRunMode+ "  dataSetRunmode:  "+ dataSetRunmode);
				System.out.println("Skipping The test  "+ testName + "  inside the suite " + suiteName);
			}else
				validateRunmodes= true;
		
		return validateRunmodes;
		
}
	
public  void launchApplication(String BrowserName, String sURL){
		
		
		if (BrowserName.equalsIgnoreCase("Firefox")){
			FirefoxProfile fp= new FirefoxProfile();
			fp.setAcceptUntrustedCertificates(true);
			fp.setAssumeUntrustedCertificateIssuer(false);
			driver= new FirefoxDriver(fp);
			driver.get(sURL);
			APPLICATION_LOG.debug("Browser Name: " +BrowserName + " Launched with " +sURL ) ;
		}else if (BrowserName.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
			driver= new ChromeDriver();
			driver.get(sURL);
			APPLICATION_LOG.debug("Browser Name: " +BrowserName + " Launched with " +sURL ) ;
		}else if (BrowserName.equalsIgnoreCase("InternetExplorer")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\IEDriverServer.exe");
			driver= new InternetExplorerDriver();		
			driver.get(sURL);
			APPLICATION_LOG.debug("Browser Name: " +BrowserName + " Launched with " +sURL ) ;
			
		}
		APPLICATION_LOG.debug("Browser is Launched");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	
	
	
	/*
	
	
	
	
	//firefox.exe -p profilemanager
	public void FirefoxBrowser(String sURL){
		//ProfilesIni prof= new ProfilesIni();
		FirefoxProfile fp= new FirefoxProfile();
		fp.setAcceptUntrustedCertificates(true);
		fp.setAssumeUntrustedCertificateIssuer(false);
		//FirefoxProfile p = prof.getProfile("Selenium_User");
		driver= new FirefoxDriver(fp);
		System.out.println("Firefox Browser");
		driver.get(sURL);
		//checkResponse(driver.getCurrentUrl());
		driver.manage().window().maximize();
		
		}
	
	public  void ChomrBrowser(String sURL){
		
		System.setProperty("webdriver.chrome.driver", "C:\\Staples\\Selenium\\chromedriver.exe");
		driver= new ChromeDriver();
		System.out.println("Chrome Browser");
		driver.get(sURL);
		//checkResponse(driver.getCurrentUrl());
		driver.manage().window().maximize();
		
		}
	public  void InternetExplorerBrowser(String sURL){
		
		System.setProperty("webdriver.ie.driver", "C:\\Staples\\Selenium\\IEDriver\\IEDriverServer.exe");
		driver= new InternetExplorerDriver();
		System.out.println("IE Browser");
		driver.get(sURL);
		//checkResponse(driver.getCurrentUrl());
		driver.manage().window().maximize();
		
		}
		
		*/
	//Close the Browser
	public void closeBrowser() throws IOException {
		
		driver.close();
	}
	//Close all window
	public void quitBrowser() {
		
		driver.quit();
	}
	
	
	public void capturescreesnhsot() throws IOException{
		
		Random rand = new Random(); 
		int randomInt = rand.nextInt(100);
		System.out.println(driver.getTitle());
		
		File scrFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\screesnshot\\bbc"+randomInt+".jpg"));
		System.out.println("captured the screen shot");
	}
	
	public void HtmlUnitDriver(String sURL){
		
		driver= new HtmlUnitDriver(BrowserVersion.FIREFOX_24);
		driver.get(sURL);
		//checkResponse(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		
		
		}
	
	public void clickButton(String strProperty, String strLocator){
		try{
			MyWebElement element = new MyWebElement(driver, strLocator, strProperty );
			clickButton(element);
		}catch(Exception e){
			System.out.println("Unable to find element with property:" + strProperty + " and locator: " + strLocator);
		}
	}
	
	public void clickButton(MyWebElement element){
		if(element.isEnabled() && element.isDisplayed()){
			element.click();
			//checkResponse(driver.getCurrentUrl());
		}else{
			System.out.println("Element with property is not enabled.");
		}
	}

	public void setText(String strProperty, String strLocator, String strText){
		try{
			MyWebElement element = new MyWebElement(driver, strLocator, strProperty );
			setText(element, strText);
		}catch(Exception e){
			System.out.println("Unable to find element with property:" + strProperty + " and locator: " + strLocator);
		}
	}
	
	public void setText(MyWebElement element,String strText){
		if(element.isEnabled()){
			element.sendKeys(strText);
			APPLICATION_LOG.debug("SearchText field value :" + strText );
		}else{
			System.out.println("Element with property is not enabled.");
		}
	}
	
	public String findTextwithAttribute(String strProperty, String strLocator, String strAtrrName){
		try{
			MyWebElement element = new MyWebElement(driver, strLocator, strProperty );
			return findTextwithAttribute(element, strAtrrName);
		}catch(Exception e){
			System.out.println("Unable to find element with property:" + strProperty + " and locator: " + strLocator);
		}
		return null;
	}
	
	public String findTextwithAttribute(MyWebElement element,String strAtrrName){
		if(element.isEnabled()){
			if(strAtrrName.equalsIgnoreCase("text"))
				return element.getText();
			else
				return element.getAttribute(strAtrrName);
			 
		}else{
			System.out.println("Element with property is not enabled.");
		}
		
		return null;
	}
	
	public String clearText(String strProperty, String strLocator){
		try{
			MyWebElement element = new MyWebElement(driver, strLocator, strProperty );
			clearText(element);
		}catch(Exception e){
			System.out.println("Unable to find element with property:" + strProperty + " and locator: " + strLocator);
		}
		return null;
	}
	
	public void clearText(MyWebElement element){
		if(element.isEnabled()){
			 element.clear();
			 
		}else{
			System.out.println("Element with property is not enabled.");
		}
	}	
		public String getLinkText(String strProperty, String strLocator){
			try{
				MyWebElement element = new MyWebElement(driver, strLocator, strProperty );
				return getLinkText(element);
			}catch(Exception e){
				System.out.println("Unable to find element with property:" + strProperty + " and locator: " + strLocator);
			}
			return null;
		}
		
		public String getLinkText(MyWebElement element){
			if(element.isEnabled()){
				 return element.getText();
				 
			}else{
				System.out.println("Element with property is not enabled.");
			}
			
			return null;
		}
		/*
		public boolean checkResponse (String sURL){
			
			try {
				int respcode=  Request.Get(sURL).execute().returnResponse().getStatusLine()
						.getStatusCode();
      
				System.out.println("Response Code of URL "+ sURL + " is   " + respcode );
        		if (respcode==200)
					return true;
										
				} catch (Exception e) {
					throw new RuntimeException(e);
        
				}
			return false;

		}
		*/
		public  void MouseHover(String strProperty, String strLocator){
			try{
				MyWebElement element = new MyWebElement(driver, strLocator, strProperty );
				if(element.isEnabled()){
					Robot robot = new Robot();
					robot.mouseMove(0, 0);
					Actions builder = new Actions(driver);
					builder.moveToElement(element).build().perform();
				}else{
					System.out.println("Element with property is not enabled.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	
		public void SelectByValueFromDropDown(String strProperty, String strLocator, String strValue){
			try{
			MyWebElement element = new MyWebElement(driver, strLocator, strProperty );
			
			
			if(element.isEnabled()){ 
			List<WebElement> lsdropdownValue= element.findElements(By.tagName("option"));
			Select selElement=  new Select(element);
			selElement.selectByVisibleText(strValue);
						
			for( int i=0;i<lsdropdownValue.size();i++) {
				 element = new MyWebElement(driver, strLocator, strProperty );
				lsdropdownValue= element.findElements(By.tagName("option"));
				if (lsdropdownValue.get(i).getText().trim().contains(strValue)){
				System.out.println(strValue +" is selected " + lsdropdownValue.get(i).getAttribute("Selected"));
				break;
				}
			
			}
			
			}else{
				
				System.out.println("Element with property is not enabled.");
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
			
			public void SelectByIndexFromDropDown(String strProperty, String strLocator, int iIndex){
				try{
				MyWebElement element = new MyWebElement(driver, strLocator, strProperty );
				
				String strValue;
				
				if(element.isEnabled()){
					Select selElement=  new Select(element);
					selElement.selectByIndex(iIndex);
					List<WebElement> lsdropdownValue= element.findElements(By.tagName("option"));
					strValue= lsdropdownValue.get(iIndex).getText();
					 System.out.println(strValue +" is selected " + lsdropdownValue.get(iIndex).getAttribute("Selected"));
				}else{
					
					System.out.println("Element with property is not enabled.");
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
					
				}
			
			public void SelectradioButtonByValueByXpath(String strProperty, String strValue){
				try{
				List<WebElement> element = driver.findElements(By.xpath(strProperty));
				
				for(int i =0;i<element.size();i++){
					
					 element = driver.findElements(By.xpath(strProperty));
					if (element.get(i).getAttribute("value").trim().contains(strValue)){
					
						element.get(i).click();
						System.out.println(element.get(i).getAttribute("value").trim() +" is clicked " + element.get(i).getAttribute("Checked"));
					}
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			// Explicty Wait by using  property an dLocator
			
			public void explicitywait (String strProperty, String strLocator,int TimeinSec){
				
				MyWebElement element = new MyWebElement(driver, strLocator, strProperty );
				wait = new WebDriverWait(driver, TimeinSec);
				wait.until(ExpectedConditions.visibilityOf(element));
				
				if(element.isEnabled()){ 
					
					System.out.println("Able to find element with property:" + strProperty + " and locator: " + strLocator);
				}
				
				
			}
			
			public boolean Fluentexplicitywait (String strProperty, String strLocator,int TimeinTotalSec, int TimeinFlySec){
				
				
				try{
				MyWebElement element = new MyWebElement(driver, strLocator, strProperty );
				FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
					       .withTimeout(TimeinTotalSec, TimeUnit.SECONDS)
					       .pollingEvery(TimeinFlySec, TimeUnit.SECONDS)
					       .ignoring(NoSuchElementException.class);

				fwait.until(ExpectedConditions.visibilityOf(element));
				return true;
				}catch(Exception e){
					return false;
				}
						
				
				
			}
			
			public void deleteAllCookies(){
				
				driver.manage().deleteAllCookies();
			}
			
			public  Hashtable<Object,Hashtable<String,String>> getCookiesNameAndValue (){
				
				Hashtable<String, String> cookieTable;
				Hashtable<Object,Hashtable<String, String> > listCookieTable;
				cookieTable = new Hashtable<String, String>();
				listCookieTable = new Hashtable<Object,Hashtable<String, String> >();
				int i=0;
				Set<Cookie> cookies= driver.manage().getCookies();
				Iterator<Cookie> iter =cookies.iterator();
				
				
						while(iter.hasNext()){
								Cookie c= iter.next();
								cookieTable.put(c.getName(), c.getValue());
								System.out.println(i);
								listCookieTable.put(i, cookieTable);
								i++;
							}
						
						
						return  listCookieTable;
						
						
				}
			
			public void navigateURL(String sURL){
				try{
					if (!(sURL=="") || (sURL==null)){
						driver.navigate().to(sURL);
					}else{
					  System.out.println("No URL found for Navigate function");
						
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}	
			
			public void browserBack(){
				driver.navigate().forward();
			}
				
			public void browserForward(){

				
				driver.navigate().forward();
			}
			
			public void FirefoxBrowserDownloadFile(String sURL, String spath){
				
				FirefoxProfile profile = new FirefoxProfile(); 
			    profile.setPreference("browser.download.folderList", 2);     
			    profile.setPreference("browser.download.dir",spath);
			    profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/msword,application/x-rar-compressed,application/octet-stream,application/csv,text/csv, application/vnd.ms-excel");
			    driver = new FirefoxDriver(profile);
			    driver.get(sURL);
				//checkResponse(driver.getCurrentUrl());
				driver.manage().window().maximize();
				
				}
			
			public static ResultSet getExecutionRowInfo(String sSheetName) throws IOException, SQLException{
				Connection connection = null;
				Statement st;
				ResultSet rs;
				try
				{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				String path= System.getProperty("user.dir")+"\\datatable.xls";
				connection = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+ path + ";DriverID=22;READONLY=true;");
				st = connection.createStatement();
				rs = st.executeQuery("select ProductName,Qty from ["+sSheetName+"$]");
				
				System.out.println(rs);
				return rs;
				
				}
				catch(Exception e)
				{
					if (connection != null)	connection.close();
					e.printStackTrace();
					return null;
				}
				
				
			}
				
			public  void closePopupIfPresent(){
				
				Set<String> winIds = driver.getWindowHandles();
				System.out.println("Total windows -> "+ winIds.size());
				
				if(winIds.size() == 2){
					Iterator<String> iter = winIds.iterator();
					String mainWinID = iter.next();
					String popupWinID = iter.next();
					driver.switchTo().window(popupWinID);
					driver.close();
					driver.switchTo().window(mainWinID);
					
				}
			
			}
			
			public int find(String[] ListArray, String Value) {
			    for(int i=0; i<ListArray.length; i++) 
			         if(ListArray[i].contains(Value.trim()))
			             return i;
			    		
				return 0;
			}
			
			//Function is used to check that element is exist and it should be having size more then and equal to 1.
			//Return true if condition match otherwise false
			//Input requried Property of the lement and locator
			
			
			public boolean isElementExist(String strProperty, String strLocator ) {
				try{
				List<WebElement> elemets= findElements(driver,strLocator,strProperty);
				if (elemets.size()>=1){
					System.out.println("Element exist");
					return true;
				}else
					return false;
				}catch(Exception e){
					System.out.println(strProperty + "not found");
					return false;
				}
				
				
			
		}
			
			public int elementSize(String strProperty, String strLocator ) {
				try{
				List<WebElement> elemets= findElements(driver,strLocator,strProperty);
				if (elemets.size()>=1){
					
					return elemets.size();
				}else
					return 0;
				}catch(Exception e){
					System.out.println(strProperty + "not found");
					return 0;
				}
					
		}
			
			public List<WebElement> listoOFWebElements(String strProperty, String strLocator ) {
				try{
				List<WebElement> elemets= findElements(driver,strLocator,strProperty);
				if (elemets.size()>=1){
					
					return elemets;
				}else
					return null;
				}catch(Exception e){
					System.out.println(strProperty + "not found");
					return null;
				}
				
				
			
		}
			
			
			public List<WebElement> findElements(WebDriver driver, String bylocator, String objProperty){
				
				try{
				if (bylocator.equalsIgnoreCase("css"))
					return driver.findElements(By.cssSelector(objProperty));
				else if(bylocator.equalsIgnoreCase("class"))
					return driver.findElements(By.cssSelector(objProperty));
				else if(bylocator.equalsIgnoreCase("id"))
					return driver.findElements(By.id(objProperty));
				else if(bylocator.equalsIgnoreCase("linkText"))
					return driver.findElements(By.linkText(objProperty));
				else if(bylocator.equalsIgnoreCase("partialLinkText"))
					return driver.findElements(By.partialLinkText(objProperty));
				else if(bylocator.equalsIgnoreCase("name"))
					return driver.findElements(By.name(objProperty));
				else if(bylocator.equalsIgnoreCase("tagName"))
					return driver.findElements(By.tagName(objProperty));
				else if(bylocator.equalsIgnoreCase("xpath"))
					return driver.findElements(By.xpath(objProperty));
				else if(bylocator.equalsIgnoreCase("tagName"))
					return driver.findElements(By.tagName(objProperty));
				else
					System.out.println("Empty");
				     return null;
				}catch(Exception e){
					System.out.println(e.getMessage());
					return null;
				}
				
			}	
			
			
	
}
