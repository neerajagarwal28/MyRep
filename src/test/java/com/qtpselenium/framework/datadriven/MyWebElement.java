package com.qtpselenium.framework.datadriven;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MyWebElement implements WebElement {

	private WebElement element;
	private List<WebElement> lelement;
	 

	public MyWebElement(WebDriver driver, String strLocator,String strProperty ) {
		
		
		element =findElement(driver, strLocator, strProperty);
	}
	
	
	
	public WebElement findElement(WebDriver driver, String bylocator, String objProperty){
		
		try{
		if (bylocator.equalsIgnoreCase("css"))
			return driver.findElement(By.cssSelector(objProperty));
		else if(bylocator.equalsIgnoreCase("class"))
			return driver.findElement(By.cssSelector(objProperty));
		else if(bylocator.equalsIgnoreCase("id"))
			return driver.findElement(By.id(objProperty));
		else if(bylocator.equalsIgnoreCase("linkText"))
			return driver.findElement(By.linkText(objProperty));
		else if(bylocator.equalsIgnoreCase("partialLinkText"))
			return driver.findElement(By.partialLinkText(objProperty));
		else if(bylocator.equalsIgnoreCase("name"))
			return driver.findElement(By.name(objProperty));
		else if(bylocator.equalsIgnoreCase("tagName"))
			return driver.findElement(By.tagName(objProperty));
		else if(bylocator.equalsIgnoreCase("xpath"))
			return driver.findElement(By.xpath(objProperty));
		else if(bylocator.equalsIgnoreCase("tagName"))
			return driver.findElement(By.tagName(objProperty));
		else
			System.out.println("Empty");
		     return null;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	



	public WebElement getElement() {
		return element;
	}
	
	@Override
	public void clear() {
		element.clear();
	}

	@Override
	public void click() {
		element.click();
		handlePopUps();
	}

	@Override
	public WebElement findElement(By arg0) {
		return element.findElement(arg0);
	}

	@Override
	public List<WebElement> findElements(By arg0) {
		return element.findElements(arg0);
	}

	@Override
	public String getAttribute(String arg0) {
		return element.getAttribute(arg0);
	}

	@Override
	public String getCssValue(String arg0) {
		return element.getCssValue(arg0);
	}

	@Override
	public Point getLocation() {
		return element.getLocation();
	}

	@Override
	public Dimension getSize() {
		return element.getSize();
	}

	@Override
	public String getTagName() {
		return element.getTagName();
	}

	@Override
	public String getText() {
		return element.getText();
	}

	@Override
	public boolean isDisplayed() {
		return element.isDisplayed();
	}

	@Override
	public boolean isEnabled() {
		return element.isDisplayed();
	}

	@Override
	public boolean isSelected() {
		return element.isSelected();
	}

	@Override
	public void sendKeys(CharSequence... arg0) {
		element.sendKeys(arg0);
		System.out.println(arg0 + "value entered");
	}

	@Override
	public void submit() {
		element.submit();
	}
	
	public void handlePopUps(){
		/*if(popUpAppear){
			handle it
		}else{
			do nothing
		}*/
	}



}

		
		
	
	


