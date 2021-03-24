package pageobjects;

import java.text.DecimalFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import property.IHomePage;
import testsuitebase.TestResultStatus;
import utility.SeleniumUtils;

public class Tryout extends SeleniumUtils implements IHomePage {
	public DecimalFormat df = new DecimalFormat("#.##");
	public String testName = null;
	public boolean Testskip = false;
	public boolean Testfail = false;
	SoftAssert s_assert = null;
	public double finish, start;
	public double end;
	boolean allFlag = false; 

	public void summaryGridLoad(WebDriver driver, String viewName) throws InterruptedException {

		waitForElementPresent(driver, 90, expand_btn1);
		By ele = By.xpath("//span[text()='Summary']//following::span[text()='" + viewName + "']");
		click(driver, ele, viewName + " option under Summary");
		Thread.sleep(2000);
		waitForLoad(driver, 30);
		Thread.sleep(2000);
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 60);
		String selectedText1 = driver.findElement(By.xpath("(//*[text()='OSUC'])[1]")).getAttribute("id");
		System.out.println("Text 1"+selectedText1);
		String selectedText = driver.findElement(By.cssSelector("li.x-boundlist-selected")).getAttribute("textContent");
		System.out.println("Text 2" +selectedText);
		By tabledata = By.xpath("//label[text()='" + viewName + "']//following::tr[2]");
		try {
			driver.findElement(tabledata).isDisplayed();
			Add_Log.info("Successfully able to launch " + viewName + " Summary report");
			Reporter.log("Successfully able to launch " + viewName + " Summary report");

		} catch (Exception e) {
			Add_Log.info("Not able to launch " + viewName + " summary report");
			Reporter.log("Not able to launch " + viewName + " summary report");
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
		
		try{    
            int a[]=new int[5];    
            a[5]=30/0;    
           }    
           catch(ArithmeticException e)  
              {  
               System.out.println("Arithmetic Exception occurs");  
              }    
           catch(ArrayIndexOutOfBoundsException e)  
              {  
               System.out.println("ArrayIndexOutOfBounds Exception occurs");  
              }    
           catch(Exception e)  
              {  
               System.out.println("Parent Exception occurs");  
              }             
           System.out.println("rest of the code");    
}  


	public void summaryGridExposurereport(WebDriver driver, String viewName, String actualViews, String Report)
			throws InterruptedException {
		Actions action = new Actions(driver);
		String[] actualViewsp = actualViews.split(",");
		summaryGridLoad(driver, viewName);
		Thread.sleep(2000);
		By filter = By.xpath("//*[text()='" + actualViewsp[0] + "']//following::tr[" + actualViewsp[1] + "]//td["
				+ actualViewsp[2] + "]//a");
		click(driver, filter, actualViews);
		/*
		 * WebElement To = driver.findElement(By.xpath("//*[text()='"+actualViewsp[0]+
		 * "']//following::tr["+actualViewsp[1]+"]//td["+actualViewsp[2]+"]"));
		 * action.click(To).build().perform();
		 */
		Thread.sleep(5000);
		waitForLoad(driver, 60);
		By exposurereport = By.xpath("//label[text()='" + Report + "']");
		Thread.sleep(5000);
		try {
			driver.findElement(exposurereport).isDisplayed();
			Add_Log.info("Successfully able to view " + Report + " report");
			Reporter.log("Successfully able to view " + Report + " report");

		} catch (Exception e) {
			Add_Log.info("Not able to launch " + Report + " report");
			Reporter.log("Not able to launch " + Report + " report");
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
	}
	
}

