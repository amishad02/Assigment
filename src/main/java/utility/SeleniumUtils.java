package utility;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import testsuitebase.TestResultStatus;


public class SeleniumUtils 
{
	public String element_Exit;
	public Logger Add_Log = Logger.getLogger("rootLogger");		
	public HashMap<String, String> metaData = new HashMap<String, String>();
	public Properties Config_url = new Properties();
	public String data = "";
	int table_size = 0; 
	
	
	public String latestFileName(){
    	File theNewestFile = null;
        File dir = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\");
    	/*File dir = new File(this.getClass().getClassLoader().getResource("excelfiles").getFile());*/
        FileFilter fileFilter = new WildcardFileFilter("*.*");
        File[] files = dir.listFiles(fileFilter);
        if (files.length > 0) {
            /** The newest file comes first **/
            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            theNewestFile = files[0];
        }	         
        /*String test[] = theNewestFile.toString().split("/");
        String fileName = test[test.length];*/
    	return theNewestFile.toString();
    } 
	
	public void click(WebDriver driver, WebPageElements ele) {
		WebElement element = null;
		try {
			element = getWebElement(driver, ele);
			element.click();
			Add_Log.info("Successfully clicked on  " + ele.getName() + " element.");
			Reporter.log("Successfully clicked on " + ele.getName() + " element.");
		} catch (Exception e) {
			try {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				Add_Log.info("Successfully clicked on  " + ele.getName() + " element.");
				Reporter.log("Successfully clicked on " + ele.getName() + " element.");
			} catch (Exception e2) {
				Add_Log.info("Not able to click " + ele.getName() + " element.");
				Reporter.log("Not able to click " + ele.getName() + " element.");
				TestResultStatus.Testfail = true;
				Assert.fail();
			}
		}
	}
	
	public void click(WebDriver driver, By by, String name) {
		WebElement element = driver.findElement(by);
		try {
			element.click();
			Add_Log.info("Successfully clicked on  " + name);
			Reporter.log("Successfully clicked on " + name);
		} catch (Exception e) {
			try {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				Add_Log.info("Successfully clicked on  " + name);
				Reporter.log("Successfully clicked on " + name);
			} catch (Exception e2) {
				Add_Log.info("Not able to click " + name);
				Reporter.log("Not able to click " + name);
				TestResultStatus.Testfail = true;
				Assert.fail();
			}
		}
	}
	
	public void click(WebDriver driver, WebElement element, String name) {	
		try {
			element.click();
			Add_Log.info("Successfully clicked on  " + name);
			Reporter.log("Successfully clicked on " + name);
		} catch (Exception e) {
			try {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				Add_Log.info("Successfully clicked on  " + name);
				Reporter.log("Successfully clicked on " + name);
			} catch (Exception e2) {
				Add_Log.info("Not able to click " + name);
				Reporter.log("Not able to click " + name);
				TestResultStatus.Testfail = true;
				Assert.fail();
			}
		}
	}
        

	public void setText(WebDriver driver, WebPageElements ele, String text) {
		if (text != null) {
			try {
				WebElement element = null;
				element = getWebElement(driver, ele);
				element.sendKeys(text);
				Add_Log.info("Successfully entered " + text + " in " + ele.getName() + " textbox.");
				Reporter.log("Successfully entered " + text + " in " + ele.getName() + " textbox.");
			} catch (Exception e) {
				Add_Log.info(text + " not entered in " + ele.getName() + " textbox.");
				Reporter.log(text + " not entered in " + ele.getName() + " textbox.");
				TestResultStatus.Testfail = true;
				Assert.fail();
			}
		} else {
			Add_Log.info(ele.getName() + " value is blank");
			Reporter.log(ele.getName() + " value is blank");
		}
	}
	
	public void setText(WebDriver driver, By by, String text, String name) {
		WebElement element = driver.findElement(by);
		if (text != null) {
			try {
				element.sendKeys(text);
				Add_Log.info("Successfully entered " + text + " in " + name + " textbox.");
				Reporter.log("Successfully entered " + text + " in " + name + " textbox.");
			} catch (Exception e) {
				Add_Log.info(text + " not entered in " + name + " textbox.");
				Reporter.log(text + " not entered in " + name + " textbox.");
				TestResultStatus.Testfail = true;
				Assert.fail();
			}
		} else {
			Add_Log.info(name + " value is blank");
			Reporter.log(name + " value is blank");
		}
	}
	
	public void autoCompleteText(WebDriver driver, WebPageElements ele, String text) {
		if (text.length() > 0) {
			try {
				WebElement element = null;
				element = getWebElement(driver, ele);
				element.sendKeys(text);
				Thread.sleep(5000);
				element.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
				Add_Log.info("Successfully entered " + text + " in " + ele.getName() + " textbox.");
				Reporter.log("Successfully entered " + text + " in " + ele.getName() + " textbox.");
			} catch (Exception e) {
				Add_Log.info(text + " not entered in " + ele.getName() + " textbox.");
				Reporter.log(text + " not entered in " + ele.getName() + " textbox.");
				TestResultStatus.Testfail = true;
				Assert.fail();
			}
		} else {
			Add_Log.info(ele.getName() + " value is blank");
			Reporter.log(ele.getName() + " value is blank");
		}
	}
	
	public void autoCompleteText(WebDriver driver, By by, String text, String name) {
		WebElement element = driver.findElement(by);
		if (text.length() > 0) {
			try {
				element.sendKeys(text);
				Thread.sleep(5000);
				element.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
				Add_Log.info("Successfully entered " + text + " in " + name + " textbox.");
				Reporter.log("Successfully entered " + text + " in " + name + " textbox.");
			} catch (Exception e) {
				Add_Log.info(text + " not entered in " + name + " textbox.");
				Reporter.log(text + " not entered in " + name + " textbox.");
				TestResultStatus.Testfail = true;
				Assert.fail();
			}
		} else {
			Add_Log.info(name + " value is blank");
			Reporter.log(name + " value is blank");
		}
	}
	
	public void selectDate(WebDriver driver,WebPageElements element,String dateToSelect){
		String day = dateToSelect.substring(3,5);		
		if(day.startsWith("0")){
			day = dateToSelect.substring(4,	5);
		}
		System.out.println(day);
		List<WebElement> allDates = driver.findElements(getLocatorName(element));
		/*List<WebElement> allDates=driver.findElements(By.xpath("//*[@class='calendar']//td"));*/		
		for(WebElement ele:allDates)
		{			
			String date=ele.getText();				
			if(date.equalsIgnoreCase(day))
			{
				ele.click();
				break;
			}
			
		}
	}
	
	/*public void clickNextCal(WebDriver driver){
		
		//List<WebElement> allDates = driver.findElements(getLocatorName(element));
		List<WebElement> allDates=driver.findElements(By.xpath("//*[@class='calendar']//td//div"));		
		for(WebElement ele:allDates)
		{			
			String date=ele.getText();	
			System.out.println(date);
			if(date.contains("›"))
			{
				ele.click();
				System.out.println("clicked on cal");
				break;
			}
			
		}
	}*/
	
	public String getText(WebDriver driver, WebPageElements ele) {
		String text = null;
		try {
			WebElement element = null;
			element = getWebElement(driver, ele);
			text = element.getText();
			Add_Log.info("Successfully fetched text " + text + " from " + ele.getName());
			Reporter.log("Successfully fetched text " + text + " from " + ele.getName());
		} catch (Exception e) {
			Add_Log.info("Not able to fetch text from " + ele.getName());
			Reporter.log("Not able to fetch text from " + ele.getName());
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
		return text;
	}
	
	public String getText(WebDriver driver, By by, String name) {
		WebElement element = driver.findElement(by);
		String text = null;
		try {
			text = element.getText();
			Add_Log.info("Successfully fetched text " + text + " from " + name);
			Reporter.log("Successfully fetched text " + text + " from " + name);
		} catch (Exception e) {
			Add_Log.info("Not able to fetch text from " + name);
			Reporter.log("Not able to fetch text from " + name);
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
		return text;
	}
	
	public String getValue(WebDriver driver, WebPageElements ele) {
		String text = null;
		try {
			WebElement element = null;
			element = getWebElement(driver, ele);
			text = element.getAttribute("value");
			Add_Log.info("Successfully fetched value " + text + " from " + ele.getName());
			Reporter.log("Successfully fetched value " + text + " from " + ele.getName());
		} catch (Exception e) {
			Add_Log.info("Not able to fetch value from " + ele.getName());
			Reporter.log("Not able to fetch value from " + ele.getName());
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
		return text;
	}
	
	public String getValue(WebDriver driver, By by, String name) {
		WebElement element = driver.findElement(by);
		String text = null;
		try {
			text = element.getAttribute("value");
			Add_Log.info("Successfully fetched value " + text + " from " + name);
			Reporter.log("Successfully fetched value " + text + " from " + name);
		} catch (Exception e) {
			Add_Log.info("Not able to fetch value from " + name);
			Reporter.log("Not able to fetch value from " + name);
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
		return text;
	}
	
	
	
	public void clearText(WebDriver driver, WebPageElements ele) {
		try {
			WebElement element = null;
			element = getWebElement(driver, ele);
			element.clear();
			Add_Log.info("Successfully cleared Data from " + ele.getName() + " textbox.");
			Reporter.log("Successfully cleared Data from " + ele.getName() + " textbox.");
		} catch (Exception e) {
			Add_Log.info("Data not cleared from " + ele.getName() + " textbox.");
			Reporter.log("Data not cleared from " + ele.getName() + " textbox.");
		}
	}
	
	public void clearText(WebDriver driver, By by, String name) {
		WebElement element = driver.findElement(by);
		try {
			element.clear();
			Add_Log.info("Successfully cleared Data from " + name + " textbox.");
			Reporter.log("Successfully cleared Data from " + name + " textbox.");
		} catch (Exception e) {
			Add_Log.info("Data not cleared from " + name + " textbox.");
			Reporter.log("Data not cleared from " + name + " textbox.");
		}
	}
	
	public void selectDropdown(WebDriver driver, WebPageElements ele, String text) {
		if (text.length() > 0) {
			try {
				new Select(getWebElement(driver, ele)).selectByVisibleText(text);
				Add_Log.info("Successfully Selected " + text + " in " + ele.getName() + " drop down list.");
				Reporter.log("Successfully Selected " + text + " in " + ele.getName() + " drop down list.");
			} catch (Exception e) {
				Add_Log.info("Not able to Select " + text + " in " + ele.getName() + " drop down list.");
				Reporter.log("Not able to Select " + text + " in " + ele.getName() + " drop down list.");
				TestResultStatus.Testfail = true;
				Assert.fail();
			}
		} else {
			Add_Log.info(ele.getName() + " value is blank");
			Reporter.log(ele.getName() + " value is blank");
		}
	}
	
	public void selectDropdown(WebDriver driver, By by, String text, String name) {
		WebElement element = driver.findElement(by);
		if (text.length() > 0) {
			try {
				new Select(element).selectByVisibleText(text);
				Add_Log.info("Successfully Selected " + text + " in " + name + " drop down list.");
				Reporter.log("Successfully Selected " + text + " in " + name + " drop down list.");
			} catch (Exception e) {
				Add_Log.info("Not able to Select " + text + " in " + name + " drop down list.");
				Reporter.log("Not able to Select " + text + " in " + name + " drop down list.");
				TestResultStatus.Testfail = true;
				Assert.fail();
			}
		} else {
			Add_Log.info(name + " value is blank");
			Reporter.log(name + " value is blank");
		}
	}
	
	public void selectDropdownByValue(WebDriver driver,WebPageElements ele,String text){
		if(text.length()>0)
		{
			try {
				/*waitForElement(driver, ele);*/
				new Select(getWebElement(driver,ele)).selectByValue(text);
			} catch (Exception e) {
				e.printStackTrace();
				Add_Log.info("Not able to Select "+text+" in "+ele.getName()+" drop down list.");
				Reporter.log("Not able to Select "+text+" in "+ele.getName()+" drop down list.");	
				TestResultStatus.Testfail= true;
				Assert.fail();
			}
		}else{
			Add_Log.info(ele.getName()+" value is null in excel");
			/*TestResultStatus.Testfail= true;
			Assert.fail();*/
		}
	}
			
	public void multiSelect(WebDriver driver,String elename,String text,WebPageElements element){
		WebPageElements ele =null;
		if(text.length()>0)
		{
			try {
				String[] values = text.split(";");
				for (int i = 0; i < values.length; i++) {
					
					/*ele = new WebPageElements(values[i],"xpath", "//*[text()='"+values[i]+"']");*/
					String xpath = "//*[contains(text(),'"+values[i]+"')]";
					System.out.println(xpath);
					ele = new WebPageElements(values[i],"xpath", xpath);
					getWebElement(driver,ele).click();
					click(driver, element);
					Add_Log.info("Successfully multiselected element "+elename);
					Reporter.log("Successfully multiselected element "+elename);
				}
			} catch (Exception e) {
				Add_Log.info("Not able to multiselect element "+elename);
				Reporter.log("Not able to multiselect element "+elename);
				TestResultStatus.Testfail= true;
				Assert.fail();
			}
		}else{
			Add_Log.info(elename+ " value is null in excel");
			/*TestResultStatus.Testfail= true;
			Assert.fail();*/
		}
	}
	
	
	public void switchToPopup(WebDriver driver, WebPageElements ele){	
		try {
			click(driver, ele);
			Thread.sleep(3000);
			String childWindowHandler = driver.getWindowHandle();
			driver.switchTo().window(childWindowHandler);
			Add_Log.info("Successfully switched to tab "+ele.getName());
			Reporter.log("Successfully switched to tab "+ele.getName());
		} catch (Exception e) {
			Add_Log.info("Not able to switch to tab "+ele.getName());
			Reporter.log("Not able to switch to tab "+ele.getName());
		}
		
	}
	
	public WebElement getWebElement(WebDriver driver,WebPageElements element){
    	WebElement ele =null;
    	try {
	    		if(element.getLocator().equalsIgnoreCase("xpath"))
	        	{
	    			ele = driver.findElement(By.xpath(element.getValue())); 		    			  		
	        	}else if(element.getLocator().equalsIgnoreCase("id"))
	        	{
	        		ele= driver.findElement(By.id(element.getValue()));  
	        	}else if(element.getLocator().equalsIgnoreCase("name"))
	        	{
	        		ele= driver.findElement(By.name(element.getValue()));  
	        	}else if(element.getLocator().equalsIgnoreCase("linktext"))
	        	{
	        		ele = driver.findElement(By.linkText(element.getValue()));  
	        	}else if(element.getLocator().equalsIgnoreCase("partiallinktext"))
	        	{
	        		ele = driver.findElement(By.partialLinkText(element.getValue()));  
	        	}else if(element.getLocator().equalsIgnoreCase("classname"))
	        	{
	        		ele = driver.findElement(By.className(element.getValue()));  
	        	}else if(element.getLocator().equalsIgnoreCase("tagname"))
	        	{
	        		ele = driver.findElement(By.tagName(element.getValue()));  
	        	}else if(element.getLocator().equalsIgnoreCase("css"))
	        	{
	        		ele = driver.findElement(By.cssSelector(element.getValue()));  
	        	}
	    		
		} catch (NoSuchElementException e) {	
			Add_Log.info("Not able to find element "+element.getName());
			Reporter.log("Not able to find element "+element.getName());
			TestResultStatus.Testfail= true;
			Assert.fail();
		}
    	if(ele==null){
    		TestResultStatus.Testfail= true;
    		Assert.fail();
    	}
    	return ele;
    }
    

   /* public void waitForElement(int miliSeconds, String eleData){
    	
    	try {
    		if(eleData.length()>0){
    			Thread.sleep(miliSeconds);
    		}		  		
		} catch (Exception e) {
			TestResultStatus.Testfail= true;
			Assert.fail();	
		}
    	
    	
	}*/
    
	public void waitForLoad(WebDriver driver, int seconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@class='x-mask-msg-text'][text()='Loading']"))));
			Add_Log.info("Successfully waited for loader to disappear");
			Reporter.log("Successfully waited for loader to disappear");
		} catch (Exception e) {
			Add_Log.info("Loader did not disappear");
			Reporter.log("Loader did not disappear");
			Boolean flag = isAlertPresent(driver);
			if (flag) {
				String Msg = getAlerttext(driver).trim();
				System.out.println(Msg);
				Reporter.log(Msg);
				TestResultStatus.Testfail = true;
				Assert.fail();
			} else {
				Add_Log.info("Loading pop up not closed");
				Reporter.log("Loading pop up not closed");
				TestResultStatus.Testfail = true;
				Assert.fail();
			}
		}
	}
    
	public void waitForElementPresent(WebDriver driver, int seconds, final WebPageElements ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			if (ele.getLocator().equalsIgnoreCase("xpath")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ele.getValue())));
			} else if (ele.getLocator().equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ele.getValue())));
			} else if (ele.getLocator().equalsIgnoreCase("linktext")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(ele.getValue())));
			} else if (ele.getLocator().equalsIgnoreCase("name")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(ele.getValue())));
			} else if (ele.getLocator().equalsIgnoreCase("classname")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ele.getValue())));
			} else if (ele.getLocator().equalsIgnoreCase("css")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.getValue())));
			}
			Add_Log.info("Successfully waited for " + ele.getName() + " to be present on page");
			Reporter.log("Successfully waited for " + ele.getName() + " to be present on page");

		} catch (Exception e) {
			Add_Log.info(ele.getName() + " not present on page");
			Reporter.log(ele.getName() + " not present on page");
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
	}
	
	public void waitForElementPresent(WebDriver driver, int seconds, final By by, String name) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			Add_Log.info("Successfully waited for " + name + " to be present on page");
			Reporter.log("Successfully waited for " + name + " to be present on page");

		} catch (Exception e) {
			Add_Log.info(name + " not present on page");
			Reporter.log(name + " not present on page");
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
	}
	
	public void waitForElementPresent(WebDriver driver, int seconds, List<WebElement> ele , String name) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.visibilityOfAllElements(ele));
			Add_Log.info("Successfully waited for " + name + " to be present on page");
			Reporter.log("Successfully waited for " + name + " to be present on page");

		} catch (Exception e) {
			Add_Log.info(name + " not present on page");
			Reporter.log(name + " not present on page");
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
	}
   
	public void waitForElementPresent(WebDriver driver, int seconds, WebElement element, String name) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			Add_Log.info("Successfully waited for " + name + " to be present on page");
			Reporter.log("Successfully waited for " + name + " to be present on page");

		} catch (Exception e) {
			Add_Log.info(name + " not present on page");
			Reporter.log(name + " not present on page");
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
	}
   
   public By getLocatorName(WebPageElements ele){
	  By locator =null;
	   if(ele.getLocator().equalsIgnoreCase("xpath")){
		   locator= By.xpath(ele.getValue());
	   }else{
		   Add_Log.info("Incorrect Element");
		   Assert.fail();
	   }
	   return locator;
   }
    
    public String getAlerttext(WebDriver driver){
    	String alertText;
    	try {
    		
    		Alert alert = driver.switchTo().alert();
    		alertText = alert.getText();
    		alert.accept();   
    		Add_Log.info("Successfully fetched text from alert");
    		Reporter.log("Successfully fetched text from alert");
    		return alertText;
		} catch (Exception e) {
			Add_Log.info("Alert not present on page.");
			Reporter.log("Alert not present on page.");
			return null;
		}
    	    	
    }
    
    public void verifyMessage(WebDriver driver, WebPageElements ele,String expectedMsg){	
    	
    	/*Thread.sleep(3000);*/
    	waitForElementPresent(driver, 10, ele);
    	String actualMsg = null;
    		Boolean flag =isAlertPresent(driver);
	    	//System.out.println(flag);
	    	if(flag){
	    		actualMsg = getAlerttext(driver).trim();
	    		System.out.println(actualMsg);
	    		if(!actualMsg.equalsIgnoreCase(expectedMsg)){
					Add_Log.info(actualMsg+" does not message match with "+expectedMsg);
					Reporter.log(actualMsg+" does not message match with "+expectedMsg);
					TestResultStatus.Testfail=true;				
					Assert.fail();
				}
	    	}else{
	    		System.out.println(expectedMsg.length());
	    		if(expectedMsg.length()>0){
		    		actualMsg =getWebElement(driver, ele).getText();
					if(!actualMsg.equalsIgnoreCase(expectedMsg)){
						Add_Log.info("Actual message "+actualMsg+" does not match with expected message: "+expectedMsg);
						Reporter.log("Actual message "+actualMsg+" does not match with expected message:"+expectedMsg);	
						Add_Log.info("Actual message is "+actualMsg);
						TestResultStatus.Testfail= true;
						Assert.fail();
					}
		    	}else{
		    		Add_Log.info(ele.getName()+" value is null in excel");	
		    		Reporter.log(ele.getName()+" value is null in excel");	
		    		TestResultStatus.Testfail= true;
		    		Assert.fail();
		    	}
	    	}
	    } 
	
    public void verifyIdentifier(WebDriver driver, String expectedMsg) throws InterruptedException{
    	Thread.sleep(3000);
    	String actualMsg = null;
    		Boolean flag =isAlertPresent(driver);
	    	//System.out.println(flag);
	    	if(flag){
	    		actualMsg = getAlerttext(driver).trim();
	    		System.out.println(actualMsg);
	    		if(!actualMsg.equalsIgnoreCase(expectedMsg)){
					Add_Log.info(actualMsg+" does not message match with "+expectedMsg);
					Reporter.log(actualMsg+" does not message match with "+expectedMsg);
					TestResultStatus.Testfail=true;				
					Assert.fail();
				}
	    	}else{
	    		Add_Log.info("Valid ID entered");
	    	}
	    	
    }
    
    
		
    
    
    
    public boolean isAlertPresent(WebDriver driver) 
    { 
        try 
        { 
            driver.switchTo().alert().accept();
            
        	 String title =driver.getTitle();
            return true; 
        }   // try 
        catch (UnhandledAlertException Ex) 
        { 
            return false; 
        }   // catch 
    }   // isAlertPresent()
    
    public void verifyTextPresent(WebDriver driver, WebPageElements ele, String expText)
    {
    	String actualText = getText(driver, ele);
    	if(!actualText.equalsIgnoreCase(expText)){
    		Add_Log.info(actualText+" does not match with "+expText);
			Reporter.log(actualText+" does not match with "+expText);
			TestResultStatus.Testfail=true;				
			Assert.fail();
    	}else{
    		Add_Log.info(actualText+" match with "+expText);
    		Reporter.log(actualText+" match with "+expText);
    	}
    }
    public void verifyValuePresent(WebDriver driver, WebPageElements ele, String expText)
    {
    	String actualText = getValue(driver, ele);
    	if(!actualText.equalsIgnoreCase(expText)){
    		Add_Log.info(actualText+" does not match with "+expText);
			Reporter.log(actualText+" does not match with "+expText);
			TestResultStatus.Testfail=true;				
			Assert.fail();
    	}else{
    		Add_Log.info(actualText+" match with "+expText);
    		Reporter.log(actualText+" match with "+expText);
    	}
    }
    
                 
    /*public HashMap<String, String> fetchDBdata(String query) throws SQLException 
    {
    	Statement stmt = null;
    	Connection conn = null;
    	try {
    		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());       	
    	    String url = "jdbc:oracle:thin:@vm-17fe-e158.nam.nsroot.net:1522:cloudapp";
    	    conn = DriverManager.getConnection(url,"rdip","rdipwd1");
    	    conn.setAutoCommit(false);
    	    stmt = conn.createStatement();
    	    ResultSet rset = stmt.executeQuery(query);
    	    while (rset.next()) {
    	    	int colcount = rset.getMetaData().getColumnCount();
    	    	System.out.println(colcount);
    	    	for(int i=1;i<=colcount;i++){
    	    		String colname = rset.getMetaData().getColumnName(i);
    	    		String rowdata =rset.getString(i);
        	    	System.out.println(colname+": "+data);
        	    	metaData.put(colname, rowdata);     	    	
    	    	}   	    	 	    	
    	    }  	        	
    	    stmt.close();
    	    conn.close();
    	   	return metaData;
		} catch (Exception e) {
			Add_Log.info("Fail to fetch DB data");
			Reporter.log("Fail to fetch DB data");
			stmt.close();
			conn.close();
			TestResultStatus.Testfail=true;				
			Assert.fail();
			return null;
		}
    	
    }*/
    
    /*public ArrayList<String> getRowData(String query) throws SQLException{
    	ArrayList<String> arrayList = new ArrayList<String>(); 
    	Statement stmt = null;
    	Connection conn = null;
    	try {  
    		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());       	
    	    String url = "jdbc:oracle:thin:@vm-17fe-e158.nam.nsroot.net:1522:cloudapp";
    	    conn = DriverManager.getConnection(url,"rdip","rdipwd1");
    	    conn.setAutoCommit(false);
    	    stmt = conn.createStatement();
    	    ResultSet rset = stmt.executeQuery(query);
    	    int colcount = rset.getMetaData().getColumnCount();
	    	System.out.println(colcount);
	    	for(int i=1;i<=colcount;i++){    
	        	while(rset.next()){
	        		arrayList.add(rset.getString(i));
	        	}
	    	}
	        	stmt.close();
	        	conn.close();
		} catch (Exception e) {
			stmt.close();
        	conn.close();
        	TestResultStatus.Testfail=true;				
			Assert.fail();
			return null;
		}
    	
    	
    	return arrayList;
    }*/
    
    
    public void validateDBData(String excelHeader, String DBheader, HashMap<String, String> hash){
    	try {
    		System.out.println(excelHeader);
    		System.out.println(hash.get(DBheader).toString());
			if(excelHeader.equalsIgnoreCase(hash.get(DBheader).toString())){
				System.out.println("Correct data in DB");
				Reporter.log("Correct data in DB");
				Add_Log.info("Correct data in DB");
			}else{
				System.out.println("Incorrect data in DB");
				Reporter.log("Incorrect data in DB");
				Add_Log.info("Incorrect data in DB");
				TestResultStatus.Testfail=true;				
				Assert.fail();
			}
		} catch (Exception e) {
			Reporter.log("Not able to validate data in DB");
			Add_Log.info("Not able to validate data in DB");
			TestResultStatus.Testfail=true;				
			Assert.fail();
		}
    }
    
    public boolean verifyTextpresent(WebDriver driver,String text){
    	
    	return driver.getPageSource().contains(text);
    	
    }
    
    public void validateSearchData(ArrayList<HashMap<String,Object>> abc, String data){
    	try {
    		String test = abc.get(0).toString();
			if(test.contains(data)){
				
				System.out.println("Correct data in DB");
				Reporter.log("Correct data in DB");
				Add_Log.info("Correct data in DB");
			}else{
				System.out.println("Incorrect data in DB");
				Reporter.log("Incorrect data in DB");
				Add_Log.info("Incorrect data in DB");
				TestResultStatus.Testfail=true;				
				Assert.fail();
			}
		} catch (Exception e) {
			Reporter.log("Not able to validate data in DB");
			Add_Log.info("Not able to validate data in DB");
			TestResultStatus.Testfail=true;				
			Assert.fail();
		}
    }
    
    
   /* public ArrayList<HashMap<String,Object>> getRowData(String query) throws SQLException{
    	try {
    		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());       	
    	    String url = "jdbc:oracle:thin:@vm-17fe-e158.nam.nsroot.net:1522:cloudapp";
    	    Connection con = DriverManager.getConnection(url,"rdip","rdipwd1");
    	    //create statement
    	    Statement stm = null;
    	    stm = con.createStatement();

    	    //query
    	    ResultSet result = null;
    	    boolean returningRows = stm.execute(query);
    	    if (returningRows)
    	      result = stm.getResultSet();
    	    else
    	      return new ArrayList<HashMap<String,Object>>();

    	    //get metadata
    	    ResultSetMetaData meta = null;
    	    meta = result.getMetaData();

    	    //get column names
    	    int colCount = meta.getColumnCount();
    	    ArrayList<String> cols = new ArrayList<String>();
    	    for (int index=1; index<=colCount; index++)
    	      cols.add(meta.getColumnName(index));

    	    //fetch out rows
    	    ArrayList<HashMap<String,Object>> rows = new ArrayList<HashMap<String,Object>>();

    	    while (result.next()) {
    	      HashMap<String,Object> row = new HashMap<String,Object>();
    	      for (String colName:cols) {
    	        Object val = result.getObject(colName);
    	        row.put(colName,val);
    	      }
    	      rows.add(row);
    	    }

    	    //close statement
    	    stm.close();

    	    //pass back rows
    	    return rows;
    	  }
    	  catch (Exception ex) {
    	    System.out.print(ex.getMessage());
    	    return new ArrayList<HashMap<String,Object>>();
    	  }
    }*/
    
    
    
    public boolean getOptions_chk(WebDriver driver,WebPageElements ele, String exp_options ) {
    	
    	boolean result = false;
    	
    	try {			
    		List<WebElement> options;
			
			if(ele.getLocator().equalsIgnoreCase("xpath")){
				int count =0;
				int i=0;
				options = driver.findElements(By.xpath(ele.getValue()));
				String [] exp_viewname_option = exp_options.split(",");
				for(WebElement list1: options)
				{
	
					String act_option= list1.getText();
					if(exp_viewname_option[i].equals(act_option))
					{
						count++;
						i++;
						if(count==options.size())
						{
							result = true;
						}
					}
				}
				
			}
			}
	
			 catch (Exception e) {
			
				Add_Log.info("Not able get options "+ele.getName()+" element.");
				TestResultStatus.Testfail= true;
				Assert.fail();
			}			
						
		
		
    	return result;						
		
						
	}
    
    public void getUtilizationdata(WebDriver driver,WebPageElements ele) {
    
    	WebElement element1=null;
    	try {
    		if(ele.getLocator().equalsIgnoreCase("xpath"))
    		{
    			element1 = getWebElement(driver, ele);
    			element1.click();
    			
    			
    			
    		}
    		
    		
    		
			} catch (Exception e) {
			try {
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", ele);
			} catch (Exception e2) {
				Add_Log.info("Not able click "+ele.getName()+" element.");
				Reporter.log("Not able to click "+ele.getName()+" element.");				
				TestResultStatus.Testfail= true;
				Assert.fail();
			}			
						
		}					
		
						
	}
    
public void sendKey_element(WebDriver driver,WebPageElements ele, String exp_input) {
	WebElement element1=null;
    	
    	try {
    		if(ele.getLocator().equalsIgnoreCase("xpath"))
    		{
    			element1=getWebElement(driver, ele);
    			element1.sendKeys(exp_input);
    			Add_Log.info("Successfully entered  "+exp_input+" in "+ele.getName()+" element.");
				Reporter.log("Successfully entered  "+exp_input+" in "+ele.getName()+" element.");	
  
    		}
    		
    		
    		
			} catch (Exception e) {
			try {
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", ele);
			} catch (Exception e2) {
				Add_Log.info("Not able click "+ele.getName()+" element.");
				Reporter.log("Not able to click "+ele.getName()+" element.");				
				TestResultStatus.Testfail= true;
				Assert.fail();
			}			
						
		}					
		
						
	}



public int getWebElementSize(WebDriver driver,WebPageElements ele) {
	List<WebElement> element_list=null;
	int element_size = 0;
	
	try {
		if(ele.getLocator().equalsIgnoreCase("xpath"))
		{
			element_list= driver.findElements(By.xpath(ele.getValue()));
			element_size=element_list.size();	
			
		}else if(ele.getLocator().equalsIgnoreCase("id"))
    	{
			element_list= driver.findElements(By.id(ele.getValue())); 
			element_size=element_list.size();
			
    	}else if(ele.getLocator().equalsIgnoreCase("name"))
    	{
    		element_list= driver.findElements(By.name(ele.getValue())); 
    		element_size=element_list.size();
    		
    	}else if(ele.getLocator().equalsIgnoreCase("linktext"))
    	{
    		element_list = driver.findElements(By.linkText(ele.getValue()));
    		element_size=element_list.size();
    		
    	}else if(ele.getLocator().equalsIgnoreCase("partiallinktext"))
    	{
    		element_list = driver.findElements(By.partialLinkText(ele.getValue()));
    		element_size=element_list.size();
    		
    	}else if(ele.getLocator().equalsIgnoreCase("classname"))
    	{
    		element_list = driver.findElements(By.className(ele.getValue()));
    		element_size=element_list.size();
    		
    	}else if(ele.getLocator().equalsIgnoreCase("tagname"))
    	{
    		element_list = driver.findElements(By.tagName(ele.getValue()));
    		element_size=element_list.size();
    		
    	}else if(ele.getLocator().equalsIgnoreCase("css"))
    	{
    		element_list = driver.findElements(By.cssSelector(ele.getValue()));
    		element_size=element_list.size();
    	}
		
		
		
		} catch (Exception e) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", ele);
		} catch (Exception e2) {
			Add_Log.info("Not able click "+ele.getName()+" element.");
			Reporter.log("Not able to click "+ele.getName()+" element.");				
			TestResultStatus.Testfail= true;
			Assert.fail();
		}			
					
	}
	return element_size;					
	
					
}

  public void element_chk(WebDriver driver, WebPageElements ele)
  {
	
	  WebElement element=null;
	  try {
		  waitForElementPresent(driver, 60, ele);
		  
		  element=getWebElement(driver, ele);
		 
		  if(element.isDisplayed())
		  {
			  if(element.isEnabled())
			  {
				  element_Exit="Pass";
			  }
		  }
		  else{
			  TestResultStatus.Testfail=true;
			  Assert.fail();
		  }
  		
		} catch (Exception e) {
			Reporter.log("Not able to find "+ele.getName()+" element");
			Add_Log.info("Not able to find "+ele.getName()+" element");
			TestResultStatus.Testfail=true;				
			Assert.fail();
		}
	  
  }
  
  

  
  
  public String[] str_split(String str)
  {
	  String arrStr[]=str.split(",");
	return arrStr;
	  
  }
  
	public void switchToTab(WebDriver driver, WebPageElements element) throws InterruptedException {
		String parent = driver.getWindowHandle();
		Thread.sleep(3000);
		click(driver, element);
		Thread.sleep(3000);
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(parent)) {
				driver.switchTo().window(handle);
				Reporter.log("Successfully clicked on  " + element.getName() + " element and switched the tab.");
				Add_Log.info("Successfully clicked on  " + element.getName() + " element and switched the tab.");
			}
			Thread.sleep(3000);
		}
	}

	public void waitForElementInvisibilty(WebDriver driver, int seconds, final WebPageElements ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			if (ele.getLocator().equalsIgnoreCase("xpath")) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ele.getValue())));
			} else if (ele.getLocator().equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(ele.getValue())));
			} else if (ele.getLocator().equalsIgnoreCase("linktext")) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(ele.getValue())));
			} else if (ele.getLocator().equalsIgnoreCase("name")) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(ele.getValue())));
			} else if (ele.getLocator().equalsIgnoreCase("classname")) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(ele.getValue())));
			} else if (ele.getLocator().equalsIgnoreCase("css")) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(ele.getValue())));
			}
			Add_Log.info(ele.getName() + " present on page");
			Reporter.log(ele.getName() + " present on page");
		} catch (Exception e) {
			Add_Log.info(ele.getName() + " not present on page");
			Reporter.log(ele.getName() + " not present on page");
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
	}
	
	public void waitForElementInvisibilty(WebDriver driver, int seconds, List<WebElement> ele, String eleDesc) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.invisibilityOfAllElements(ele));	
			Add_Log.info("Successfully waited for invisibility of "+eleDesc);
			Reporter.log("Successfully waited for invisibility of "+eleDesc);
		} catch (Exception e) {
			Add_Log.info("Time out for invisibility of "+eleDesc);
			Reporter.log("Time out for invisibility of "+eleDesc);
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
	}
	
	public void waitForElementInvisibilty(WebDriver driver, int seconds, final By by, String name) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			Add_Log.info(name + " present on page");
			Reporter.log(name + " present on page");
		} catch (Exception e) {
			Add_Log.info(name + " not present on page");
			Reporter.log(name + " not present on page");
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
	}
    
  public void config_initialise() throws IOException{
		Config_url = new Properties();
		/*FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\Config\\Config.properties");*/
		FileInputStream fip = new FileInputStream(this.getClass().getClassLoader().getResource("config/Config.properties").getFile().toString());
		Config_url.load(fip);
	} 
 
public void navigator_forceurl(WebDriver driver, String key) throws IOException{
	  config_initialise();
		if(key.equalsIgnoreCase("UAT")){
			driver.get(Config_url.getProperty("forceURL_Navigator_UAT"));
		}else if(key.equalsIgnoreCase("SIT")){
			driver.get(Config_url.getProperty("forceURL_Navigator_SIT"));
		}else if(key.equalsIgnoreCase("PROD")){
			driver.get(Config_url.getProperty("forceURL_Navigator_PRODFIX"));	
		}
}

public void psrm_forceurl(WebDriver driver, String key) throws IOException{
	  config_initialise();
		if(key.equalsIgnoreCase("UAT")){
			driver.get(Config_url.getProperty("forceurl_Psrm_UAT"));
		}else if(key.equalsIgnoreCase("SIT")){
			driver.get(Config_url.getProperty("forceurl_Psrm_SIT"));
		}else if(key.equalsIgnoreCase("PROD")){
			driver.get(Config_url.getProperty("forceurl_Psrm_PRODFIX"));	
		}
}
 
public void ee_forceurl(WebDriver driver, String key) throws IOException{
	  config_initialise();
		if(key.equalsIgnoreCase("UAT")){
			driver.get(Config_url.getProperty("forceurl_ee_UAT"));
		}else if(key.equalsIgnoreCase("SIT")){
			driver.get(Config_url.getProperty("forceurl_ee_SIT"));
		}else if(key.equalsIgnoreCase("PROD")){
			driver.get(Config_url.getProperty("forceurl_ee_PRODFIX"));	
		}
}
    
   //Added by ram

public void waitForElementPresent1(WebDriver driver, int seconds, final WebPageElements ele){
	   try {
		   WebDriverWait wait = new WebDriverWait(driver, seconds);
		   if(ele.getLocator().equalsIgnoreCase("xpath")){
			   wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ele.getValue())));
		   }else if(ele.getLocator().equalsIgnoreCase("id")){
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ele.getValue())));
		   }else if(ele.getLocator().equalsIgnoreCase("linktext")){
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(ele.getValue())));
		   }else if(ele.getLocator().equalsIgnoreCase("name")){
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(ele.getValue())));
		   }else if(ele.getLocator().equalsIgnoreCase("classname")){
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ele.getValue())));
		   }else if(ele.getLocator().equalsIgnoreCase("css")){
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.getValue())));
		   }
		   
		   
		   /*FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(seconds, TimeUnit.SECONDS)
			        .pollingEvery(3, TimeUnit.SECONDS)
			        .ignoring(NoSuchElementException.class);
		   fWait.until(ExpectedConditions.visibilityOf(getWebElement(driver, ele)));*/
		   Add_Log.info("Successfully waited for "+ele.getName()+" to be present on page");
			Reporter.log("Successfully waited for "+ele.getName()+" to be present on page");
		   
	} catch (Exception e) {
		Add_Log.info(ele.getName()+" not present on page");
		Reporter.log(ele.getName()+" not present on page");
		TestResultStatus.Testfail= true;
		Assert.fail();
	}
}
	   
	   //Added by chandani
	   
	   

public void viewName(WebDriver driver, String exp_options ) {

		try {
			driver.findElement(By.xpath("//span[text()='Relationship']/preceding-sibling::img[@class=' x-tree-elbow-img x-tree-elbow-plus x-tree-expander']")).click();
			String[] exp_viewname_option = exp_options.split(",");
		    data = "(//div[contains(@class,' x-panel-body-citirisktreeui')])[2]//table";
			table_size = driver.findElements(By.xpath(data)).size();
    		for (int i = 1; i <= table_size; i++) {
			 	String str1 = "(" + data + "[" + i + "]"+ "//tbody/tr/td/div/span)";
			 	
				String s = driver.findElement(By.xpath(str1)).getText();
				String s1=exp_viewname_option[i-1].toString();
				if (s1.equals(s)) {
					System.out.println(exp_viewname_option[i-1].toString()+" expected option "+s);				
				}else{
					System.out.println(exp_viewname_option[i-1].toString()+" not matched option "+s);		
				}
			}

		} catch (Exception e) {

			Add_Log.info("Not able get options");
			TestResultStatus.Testfail = true;
			Assert.fail();
		}

	}
    
  public int  FetchView(WebDriver driver, String exp_options )
  {   
	  int count=0;
	   data = "(//div[contains(@class,' x-panel-body-citirisktreeui')])[2]//table";
		table_size = driver.findElements(By.xpath(data)).size();
		for (int i = 1; i <= table_size; i++) {
		 	String s = driver.findElement(By.xpath("(" + data + "[" + i + "]"+ "//tbody/tr/td/div/span)")).getText();
		if(exp_options.equals(s))count=i;
		}
		return  count;
  } 

//  public void click(WebDriver driver,WebElement element, String Coumn ) {
//		
//		try {
//			
//			element.click();
//			Add_Log.info("Successfully clicked on  " +Coumn+" element.");
//			Reporter.log("Successfully clicked on "+Coumn+" element.");	
//		} catch (Exception e) {
//			try {
//				JavascriptExecutor executor = (JavascriptExecutor)driver;
//				executor.executeScript("arguments[0].click();", element); 
//	} catch (Exception e2) {
//				Add_Log.info("Not able click "+Coumn+" element.");
//				Reporter.log("Not able to click "+Coumn+" element.");				
//				TestResultStatus.Testfail= true;
//				Assert.fail();
//			}			
//						
//		}
//  }
		
		

	    
	    public WebElement  CustomizedXpath(WebDriver driver , String xpathdata)
	    {
	    	WebElement  ele=null;	
	    	try
	    	{
	    	
	    	  ele=driver.findElement(By.xpath( xpathdata));	
	    	}
	    	catch(Exception e)
	    	{
	    		Add_Log.info("Not able to find  element."+xpathdata);
				Reporter.log("Not able to find  element."+xpathdata);				
				TestResultStatus.Testfail= true;
				Assert.fail();	
	    	}
	    	return  ele;
	    } 
	    
	public void waitForVisiblility(WebDriver driver, int timeinseconds, By by, String name) {
		try {
			FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(timeinseconds, TimeUnit.SECONDS)
					.pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
			fWait.until(ExpectedConditions.visibilityOfElementLocated(by));
			Add_Log.info("Successfully waited for " + name);
			Reporter.log("Failed to locate element" + name);
		} catch (Exception e) {
			Add_Log.info("Failed to locate element" + name);
			Reporter.log("Failed to locate element" + name);
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
	}
	
	public void waitForVisiblility(WebDriver driver, int timeinseconds, WebPageElements ele) {
		WebElement element;
		try {
			element = getWebElement(driver, ele);
			FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(timeinseconds, TimeUnit.SECONDS)
					.pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
			fWait.until(ExpectedConditions.visibilityOf(element));
			Add_Log.info("Successfully waited for " + ele.getName());
			Reporter.log("Failed to locate element" + ele.getName());
		} catch (Exception e) {
			Add_Log.info("Failed to locate element" + ele.getName());
			Reporter.log("Failed to locate element" + ele.getName());
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
	}
	
	public void captureScreenShot(ITestResult result, String status, WebDriver driver){	
		String destDir = "";
		String passfailMethod = result.getMethod().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();
		//To capture screenshot.
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//File scrFile = ((TakesScreenshot) SuiteBase.driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		//If status = fail then set folder name "screenshots/Failures"
		if(status.equalsIgnoreCase("fail")){
			/*destDir = "target/screenshots/Failures";*/
			destDir = "screenshots/failures";
		}
		//If status = pass then set folder name "screenshots/Success"
		else if (status.equalsIgnoreCase("pass")){
			/*destDir = "target/screenshots/Success";*/
			/*destDir = this.getClass().getClassLoader().getResource("screenshots/success").toString();*/
			destDir = "screenshots/success";
		}
		
		//To create folder to store screenshots
		new File(destDir).mkdirs();
		//Set file name with combination of test class name + date time.
		String destFile = passfailMethod+" - "+dateFormat.format(new Date()) + ".jpeg";
		
		try {
			//Store file at destination folder location
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	} 

}
    
    
    
    



