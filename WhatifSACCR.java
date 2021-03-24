package pageobjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.poi.hssf.record.Margin;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import property.IHomePage;
import testsuitebase.TestResultStatus;
import utility.SeleniumUtils;
import utility.WebPageElements;

public class WhatifSACCR extends SeleniumUtils implements IHomePage {

	public DecimalFormat df = new DecimalFormat("#.##");
	public String testName = null;
	public boolean Testskip = false;
	public boolean Testfail = false;
	SoftAssert s_assert = null;
	public double finish, start;
	public double end;
	boolean allFlag = false;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	 String downloadFilepath ="\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
	public static String methodName() {
		String methodname = null;
		StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
		for (StackTraceElement elem : stackTraceElements) {
			// System.out.println(elem);
			if (elem.getClassName().contains("testcases")) {
				methodname = elem.getMethodName();
				// System.out.println(methodname);
			}

		}
		return methodname;
	}
	// Temporary hack to fix ecore 
	public void hackecore(WebDriver driver) throws InterruptedException {
	
	By searchicon = By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-search ']");
	waitForElementPresent(driver, 120, searchicon, "icon-search");
	click(driver, searchicon, "icon-search");
	Thread.sleep(2000);
	By ele1 = By.xpath("//div[text()='SEARCH SCENARIO']");
	waitForElementPresent(driver, 30, ele1, " Header for SEARCH SCENARIO");
	Thread.sleep(2000);
	By searchbutton = By.xpath("//span[text()='Search']");
	waitForElementPresent(driver, 120, searchbutton, "search button");
	click(driver, searchbutton, "search button");
	Thread.sleep(2000);
	waitForLoad(driver, 120);
	By searchtable = By.xpath("(//label[text()='Scenario Search Result']/following::td[contains(@class,'row-checker x-grid-cell-first')])[1]");
	waitForElementPresent(driver, 120, searchtable, "search table data");
	click(driver, searchtable, "search table data");
	waitForLoad(driver, 120);
	By selectbutton = By.xpath("//span[text()='Select']");
	waitForElementPresent(driver, 120, selectbutton, "select button");
	click(driver, selectbutton, "select button");
	Thread.sleep(2000);
	waitForLoad(driver, 120);
	}

	public void hackCheckbox(WebDriver driver) throws InterruptedException
	{
		try {
			String errormessage="//div[text()='Please select one check box.']";
			WebPageElements Error=new WebPageElements("errorEcore","xpath",errormessage);
			waitForElementPresent(driver, 120, Error);
			String errorok="//span[text()='OK']";
			WebPageElements ErrorOK=new WebPageElements("errorEcore ok","xpath",errorok);
			click(driver, ErrorOK);
			Thread.sleep(2000);
			String checkall="(//label[text()='Trade View']//following::span[@class='x-column-header-text'])[1]";
			WebPageElements CheckAll=new WebPageElements("select check All","xpath",checkall);
			//waitForElementPresent(driver, 120, Error);
			click(driver, CheckAll);
			Thread.sleep(2000);
			WebPageElements UnCheckAll=new WebPageElements("select uncheck All","xpath",checkall);
			click(driver, UnCheckAll);
			Thread.sleep(2000);
			
			String editclone="(//div[@class='icon icon-edit-advanced'])[1]";
			WebPageElements EditClone=new WebPageElements("Edit Clone","xpath",editclone);
			click(driver, EditClone);
			Thread.sleep(2000);
			waitForLoad(driver, 90);
			
		} catch (Exception e) {
			
		}
		
	}
	// main selection
	public void CapitalWhtif(WebDriver driver, String viewName, String ReportName, String Verify)
			throws InterruptedException {
		String[] viewNamesp = viewName.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		waitForLoad(driver, 200);
		By ele1 = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
		waitForElementPresent(driver, 120, ele1, " expand Aggregation");
		click(driver, ele1, "by drilling down to " + ReportName + " expand aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		By ele2 = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewNamesp[0]
				+ "']//parent::div//img[contains(@class,'x-tree-expander')]");
		click(driver, ele2, "by drilling down to " + ReportName + " and select " + viewNamesp[0] + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		By ele3 = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewNamesp[1] + "']");
		click(driver, ele3, "by drilling down to " + ReportName + " and select " + viewNamesp[1] + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
	//	hackecore(driver);

	}

	// click on Add button for Scenario
	public void WIAAddbutton(WebDriver driver, String viewName, String Verify, String ReportName)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		By ele1 = By.xpath("(//label[text()='" + Verifysp[0]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-plus '])[1]");
		waitForElementPresent(driver, 120, ele1, " Add icon for " + Verifysp[0]);
		click(driver, ele1, "by drilling down to " + ReportName + " - " + viewName + " - " + Verifysp[0] + " Add icon");
		waitForLoad(driver, 900);
		Thread.sleep(2000);
	}

	// Add name and Description for portfolio
	public void WIAaddportfoliopop(WebDriver driver, String Verify3, String Verify,String viewName) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		waitForElementPresent(driver, 120, addscenarioname);
		click(driver, addscenarioname);
		Thread.sleep(2000);
		setText(driver, addscenarioname, Verify3sp[0]);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, adddesc);
		click(driver, adddesc);
		Thread.sleep(2000);
		setText(driver, adddesc, Verify3sp[1]);
		Thread.sleep(2000);
		By ele3 = null;
		if (viewName.contains("SACCR")) {
			ele3 = By.xpath("//span[text()='Create']");
		} else {
			ele3 = By.xpath("//span[text()='Save']");
		}
		System.out.println(ele3);
		System.out.println(Verify);
		waitForElementPresent(driver, 120, ele3, " Save/Create button for  " + Verify);
		click(driver, ele3, "Save/Create button for " + Verify);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		waitForElementInvisibilty(driver, 120, addscenarioname);
	}

	// Edit name portfolio
	public void Editportfoliopop(WebDriver driver, String Verify3, String Verify, String viewName) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		waitForElementPresent(driver, 120, addscenarioname);
		click(driver, addscenarioname);
		Thread.sleep(2000);
		clearText(driver, addscenarioname);
		Thread.sleep(2000);
		setText(driver, addscenarioname, Verify3sp[0]);
		Thread.sleep(2000);
		By ele3 = null;
		if (viewName.contains("SACCR")) {
			ele3 = By.xpath("//span[text()='Create']");
		} else {
			ele3 = By.xpath("//span[text()='Save']");
		}
		System.out.println(ele3);
		System.out.println(Verify);
		waitForElementPresent(driver, 120, ele3, " Save/Create button for  " + Verify);
		click(driver, ele3, "Save/Create button for " + Verify);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		waitForElementInvisibilty(driver, 120, addscenarioname);
	}
	// click Scenario Search Facility
	public void WIASearchFacility(WebDriver driver, String viewName, String Verify, String ReportName)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		By ele1 = By.xpath("(//label[text()='" + Verifysp[0]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-upload '])[1]");
		waitForElementPresent(driver, 120, ele1, " Search Facility for " + Verifysp[0]);
		click(driver, ele1,
				"by drilling down to " + ReportName + " - " + viewName + " - " + Verifysp[0] + " Search Facility");
		Thread.sleep(2000);
	}

	// Header for SCENARIO
	// Header for Search
	public void WIAHeadername(WebDriver driver, String Verify2) throws InterruptedException {
		
		By ele1 = By.xpath("//div[text()='" + Verify2 + "']");
		Thread.sleep(2000);
		
		waitForElementPresent(driver, 30, ele1, " Header for " + Verify2);
		Thread.sleep(2000);
	}

	// if error
	public void Snackbar(WebDriver driver) throws InterruptedException {
		WebElement errormessage = null;
		String errormess = null;
		try {
			By ErrorSnackBar = By.xpath("//img[@class='StausClose']");
			if (driver.findElements(ErrorSnackBar).size() > 0) {
				errormessage = driver.findElement(By.xpath("//img[@class='StausClose']/following::span[1]"));
				errormess = errormessage.getText();
				Reporter.log("Status Bar Appear with message: " + errormess);
				Add_Log.info("Status  Bar Appear with message: " + errormess);
			}
			if (errormess.contains("There is no available COB Date")) {
				Reporter.log("No data : " + errormessage.getText());
				Add_Log.info("No data : " + errormessage.getText());
				TestResultStatus.mpaskeysnew.put(methodName(), "No data : " + errormessage.getText());
				Assert.fail();
			} else {

			}
		} catch (Exception e1) {

		}
	}

	// displayed Created scenario Name
	public void WIAscenarioverfiy(WebDriver driver, String viewName, String Verify3, String Verify, String Report)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		WebElement extractxpathid = driver.findElement(
				By.xpath("//label[text()='" + Verifysp[0] + "']//following::span[text()='" + Report + "']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		By filter = By.xpath("//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div");
		WebElement filterscenarioName = driver.findElement(filter);
		String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
		System.out.println(filterscenarioNamea);
		System.out.println(Verify3);
		if (Verify3sp[0].contains(filterscenarioNamea)) {
			Add_Log.info("Successfully displayed Created scenario Name = " + filterscenarioNamea);
			Reporter.log("Successfully displayed Created scenario Name = " + filterscenarioNamea);
		} else {
			Add_Log.info("Unable to displayed Created scenario Name = " + Verify3sp[0]);
			Reporter.log("Unable to displayed Created scenario Name = " + Verify3sp[0]);
			TestResultStatus.mpaskeysnew.put(methodName(),
					"Unable to displayed Created scenario Name = " + Verify3sp[0]);
			Assert.fail();
		}
		if (viewName.contains("Banking Book")) {
			if (Verifysp[1].equals("Guarantee")) {
				By clickguarantee = By.xpath("//span[text()='Guarantee']");
				click(driver, clickguarantee, "Expand Secondary Header Guarantee");
				Thread.sleep(2000);
				waitForLoad(driver, 900);
			}
		}
	}

	// COB select
	public void cobselect(WebDriver driver, String reportingPeriod) throws InterruptedException {
		Thread.sleep(1000);
		click(driver, cobselect);
		Thread.sleep(1000);
		By cobvalue = By.xpath("(//ul/li)[1]");
		String cobvaluetext = null;
		try {
		WebElement cobvaluebox = driver.findElement(cobvalue);
		cobvaluetext =cobvaluebox.getText();
		System.out.println(cobvaluetext);
		if (cobvaluebox.isDisplayed()) {
			Add_Log.info("Successfully displayed " + cobvaluetext);
			Reporter.log("Successfully displayed " + cobvaluetext);	
		}
		}
		catch(Exception e)
		{
		Add_Log.info("Unable to displayed " +cobvaluetext);
		Reporter.log("Unable to displayed " +cobvaluetext);
		TestResultStatus.mpaskeysnew.put(methodName(), ("Unable to displayed " +cobvaluetext));	
		Assert.fail();
		}
		click(driver, cobvalue, cobvaluetext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
	}

	// Enter Portfolio and Search / and add
	public void EnterPortfolioandSearch(WebDriver driver, String Verify2, String Verify4) throws InterruptedException {
		String[] Verify2sp = Verify2.split("\\,");
		 boolean result = false;
		  int attempts = 0;
	        while(attempts < 8) {
		try {
		click(driver, portoflioinput);
		Thread.sleep(2000);
		clearText(driver, portoflioinput);
		Thread.sleep(2000);
		// setText(driver,portoflioinput,Verify4);
		waitForLoad(driver, 900);
		Thread.sleep(2000);
		setTextenter(driver, portoflioinput, Verify4);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		By eletext = By.xpath("//font[text()='" + Verify4 + "'] | //li[text()='" + Verify4 + "']");
		waitForElementPresentnoAssert(driver, 120, eletext, Verify4 );
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		clicknoassert(driver, eletext, Verify4 );
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchresults.getValue())));
		result = true;
		
		 break;
		}
		catch (Exception e1)
		
		{System.out.println("retrye" + attempts++);
		attempts++;
		}
	        }
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForElementPresent1(driver, 90, searchresults);
		WebElement sort2_dlle = driver.findElement(By.xpath(SEARCHRESULTS));
		String valuetext = sort2_dlle.getText();
		System.out.println(valuetext);
		if (Verify4.contains(valuetext)) {
			Add_Log.info("Successfully Portfolio ID " + Verify4 + " Displayed");
			Reporter.log("Successfully Portfolio ID " + Verify4 + " Displayed");
		} else {
			Add_Log.info("Unable to displayed " + Verify4 + " Portfolio ID");
			Reporter.log("Unable to displayed " + Verify4 + " Portfolio ID");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed " + Verify4 + " Portfolio ID");
			Assert.fail();
		}
		By checkbox = By.xpath("//div[@class='x-grid-row-checker']");
		click(driver, checkbox, "Grid row Checker box");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		click(driver, add_btninside);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
	}
	// Verfiy select Portfolio Trademain page
	public void Tradeverfiy(WebDriver driver) throws InterruptedException {

		By TradeView = By.xpath(
				"(//*[text()='Trade View']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]");
		WebElement TradeViewbox = driver.findElement(TradeView);
		waitForElementPresent(driver, 120, TradeView, "TradeView");
		if (TradeViewbox.isDisplayed()) {
			Add_Log.info("Successfully displayed Trade View table content");
			Reporter.log("Successfully displayed Trade View table content");
		} else {
			Add_Log.info("Unable to displayed Trade View table content");
			Reporter.log("Unable to displayed Trade View table content");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Trade View table content");
			Assert.fail();
		}
	}

	// Verfiy select Portfolio CSA main page
		public void CSAverfiy(WebDriver driver) throws InterruptedException {
 try {
			By CSAView = By.xpath(
					"(//*[text()='CSA VIEW']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]");
			WebElement CSAViewbox = driver.findElement(CSAView);
			if (CSAViewbox.isDisplayed()) {
				Add_Log.info("Successfully displayed CSA VIEW table content");
				Reporter.log("Successfully displayed CSA VIEW table content");
			} else {
				Add_Log.info("Unable to displayed CSA VIEW table content");
				Reporter.log("Unable to displayed CSA VIEW table content");
				TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed CSA VIEW table content");
				//Assert.fail();
			}
 }
 catch(Exception e)
	{
	Add_Log.info("Unable to displayed CSA VIEW table content");
	Reporter.log("Unable to displayed CSA VIEW table content");
	TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed CSA VIEW table content");
	ITestResult result = Reporter.getCurrentTestResult();
	result.setAttribute("DontRetry", Boolean.TRUE.toString());
	//Assert.fail();
	}
		}
	
		
		// Verfiy select Portfolio NetView main page
				public void NetViewverfiy(WebDriver driver) throws InterruptedException {

					By CSAView = By.xpath(
							"(//label[text()='Netting Set View']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]");
					WebElement CSAViewbox = driver.findElement(CSAView);
					if (CSAViewbox.isDisplayed()) {
						Add_Log.info("Successfully displayed Netting Set View table content");
						Reporter.log("Successfully displayed Netting Set View' table content");
					} else {
						Add_Log.info("Unable to displayed Netting Set View table content");
						Reporter.log("Unable to displayed Netting Set View table content");
						TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Netting Set View table content");
						Assert.fail();
					}	
				}
	// Verfiy select Portfolio Trade and CSA view in main page
	public void WIATradeCSAverfiy(WebDriver driver) throws InterruptedException {

		By TradeView = By.xpath(
				"(//*[text()='Trade View']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]");
		WebElement TradeViewbox = driver.findElement(TradeView);
		if (TradeViewbox.isDisplayed()) {
			Add_Log.info("Successfully displayed Trade View table content");
			Reporter.log("Successfully displayed Trade View table content");
		} else {
			Add_Log.info("Unable to displayed Trade View table content");
			Reporter.log("Unable to displayed Trade View table content");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Trade View table content");
			Assert.fail();
		}
		try {
		By CSAView = By.xpath(
				"(//*[text()='CSA VIEW']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]");
		WebElement CSAViewbox = driver.findElement(CSAView);
		if (CSAViewbox.isDisplayed()) {
			Add_Log.info("Successfully displayed CSA VIEW table content");
			Reporter.log("Successfully displayed CSA VIEW table content");
		} else {
			Add_Log.info("Unable to displayed CSA VIEW table content");
			Reporter.log("Unable to displayed CSA VIEW table content");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed CSA VIEW table content");
			ITestResult result = Reporter.getCurrentTestResult();
			result.setAttribute("DontRetry", Boolean.TRUE.toString());
			//Assert.fail();
		}
		}
		catch(Exception e)
		{
		Add_Log.info("Unable to displayed CSA VIEW table content");
		Reporter.log("Unable to displayed CSA VIEW table content");
		TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed CSA VIEW table content");
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("DontRetry", Boolean.TRUE.toString());
		//Assert.fail();
		}
	}

	// Click on Check box for TradeVIew and CSA view
	public void TradeCSAViewradiobutton(WebDriver driver) throws InterruptedException {
		By TradeView1 = By.xpath(
				"(//*[text()='Trade View']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]");
		WebElement TradeViewbox1 = driver.findElement(TradeView1);
		click(driver, TradeView1, "Trade view check box");
		Thread.sleep(2000);
		//By CSAView1 = By.xpath("(//*[text()='CSA VIEW']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]");
		  By CSAView1 = By.xpath("(//*[text()='CSA VIEW']//following::div[@class='x-column-header-inner x-column-header-inner-empty x-column-header-over'])[1]");		
		WebElement CSAViewbox1 = driver.findElement(CSAView1);
		click(driver, CSAView1, "CSA view check all box");
	}

	// Click on Portfolio calculator
	public void Portfoliocalculator(WebDriver driver) throws InterruptedException {

		By calcutor = By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-run ']");
		click(driver, calcutor, "Portfolio icon-calculator ");
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
	}

	// Submit Pop click on get message
	public void Sumbitpopok(WebDriver driver) throws InterruptedException {
		try {
		By submitpop = By.xpath("//div[contains(text(),'submitted')]");
		WebElement submitpopbox = driver.findElement(submitpop);
		String submitpopmessage = submitpopbox.getText();
		System.out.println(submitpopmessage);
		if (submitpopbox.isDisplayed()) {
			Add_Log.info("Popup with message : " + submitpopmessage);
			Reporter.log("Popup with message : " + submitpopmessage);
		}
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		By closepop = By.xpath("//span[text()='OK']");
		clicknoassert(driver, closepop, " click ok on popup meeasge");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		}
		catch (Exception e1)
		{
			try {
				By submitpop = By.xpath("//span[contains(text(),'Calculate RWA')]");
				WebElement submitpopbox = driver.findElement(submitpop);
				String submitpopmessage = submitpopbox.getText();
				System.out.println(submitpopmessage);
				if (submitpopbox.isDisplayed()) {
					Add_Log.info("Popup with message : " + submitpopmessage);
					Reporter.log("Popup with message : " + submitpopmessage);
				}
				clicknoassert(driver, submitpop, "Calculate RWA");
				Thread.sleep(2000);
				waitForLoad(driver, 900);
				By closepop = By.xpath("//span[text()='OK']");
				clicknoassert(driver, closepop, " click ok on popup meeasge");
				Thread.sleep(2000);
				waitForLoad(driver, 900);
			}
			catch (Exception e2)
			{
			By submitpop = By.xpath("//div[contains(text(),'error')]");
			WebElement submitpopbox = driver.findElement(submitpop);
			String submitpopmessage = submitpopbox.getText();
			System.out.println(submitpopmessage);
			if (submitpopbox.isDisplayed()) {
				Add_Log.info("Popup with message : " + submitpopmessage);
				Reporter.log("Popup with message : " + submitpopmessage);
				ITestResult result = Reporter.getCurrentTestResult();
				result.setAttribute("DontRetry", Boolean.TRUE.toString());
				Assert.fail();
			}
		}
		}
	}

	public void downloadfilecommonreg(WebDriver driver, String ReportName, String Verify, String Fileformat,
			String FileNameac) throws InterruptedException, IOException {

		String[] Verifysp = Verify.split("\\,");
		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";

		start = System.currentTimeMillis();
		String totalTime = null;
		long beforeCount = 0;

		beforeCount = Files.list(Paths.get(downloadPath)).count();
		long afterCount = beforeCount;
		int i = 1;
		while (beforeCount >= afterCount) {

			afterCount = Files.list(Paths.get(downloadPath)).count();
			System.out.println(i++);
			if (i == 120) // added for validation
			{

				break;
			}
		}
		end = System.currentTimeMillis();

		Double tt = (end - start) / 1000;
		totalTime = df.format(tt);
		System.out.println(totalTime);
		System.out.println("Time took to download report:" + totalTime + " seconds");
		Thread.sleep(3000);

		String fileName = latestFileName2(FileNameac);
		System.out.println(fileName + " & " + Verifysp[1]);
		System.out.println(FileNameac + "************");
		if (fileName.contains(FileNameac)) {
			Add_Log.info("Successfully downloaded " + Fileformat + " report for " + Verifysp[1] + " " + ReportName
					+ " Report Time took to download " + totalTime + " sec");
			Reporter.log("Successfully downloaded " + Fileformat + " report for " + Verifysp[1] + " " + ReportName
					+ " Report Time took to download " + totalTime + " sec");
		} else {
			Add_Log.info(Fileformat + "if satatment ******report not downloaded for " + Verifysp[1] + " " + ReportName
					+ " report");
			Reporter.log(Fileformat + "if satatment  ******report not downloaded for " + Verifysp[1] + " " + ReportName
					+ " report");
			TestResultStatus.mpaskeysnew.put(methodName(), Fileformat + "if satatment  ******report not downloaded for "
					+ Verifysp[1] + " " + ReportName + " report");
		}

	}
	// Check calculator Status until
	public void Checkcalculatorstatus(WebDriver driver) throws InterruptedException {
		
		By checkstatus = By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-refresh ']");
		By iconcheckcircle = null;
		WebElement iconcheckcirclebox = null;		
		TimeUnit.MINUTES.sleep(1);
		/*	boolean success = false;
		int count = 0;
		int maxTries = 6;
		while (!success) {
			try {
				WebDriverWait wait2 = new WebDriverWait(driver, 300);
				wait2.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@class='icon icon-loading']"))));
				TimeUnit.SECONDS.sleep(30);
				By iconcheckcircle = By.xpath("//div[@class='icon icon-check-circle']");
				WebElement iconcheckcirclebox = driver.findElement(iconcheckcircle);
				WebDriverWait wait = new WebDriverWait(driver, 300);
				wait.until(ExpectedConditions.visibilityOf(iconcheckcirclebox));
				waitForLoad(driver, 900);
			System.out.println("trtw1");
				if (iconcheckcirclebox.isDisplayed()) {
					Add_Log.info("Successfully displayed check icon under status");
					Reporter.log("Successfully displayed check icon under status");
					success = true;
					break;
				}
				
			} catch (Exception e1) {
				click(driver, checkstatus, " icon-refresh 2");
				Thread.sleep(2000);
				waitForLoad(driver, 900);
			//	By iconloading = By.xpath("//div[@class='icon icon-loading']");
			//	WebElement iconcheckloading = driver.findElement(iconloading);
				 boolean result = false;
			        int attempts = 0;
			        while(attempts < 8) {
			            try {
			            	By iconcheckcircle = By.xpath("//div[@class='icon icon-loading']");
							WebElement iconcheckcirclebox = driver.findElement(iconcheckcircle);
							waitForLoad(driver, 900);
							if (iconcheckcirclebox.isDisplayed()) {
								Add_Log.info("displayed loading icon status waiting for result to display");
								Reporter.log("displayed loading icon status waiting for result to display");
							}
							else {
								Add_Log.info("Unable to displayed calacuator value under Result tab");
								Reporter.log("Unable to displayed calacuator value under Result tab");							
							}
			               
			            } catch(Exception e) {
			            	  attempts++;	
			            	  if (++count == maxTries)
						            throw e1;
			            	 
			            }
			          
			        }
				Thread.sleep(2000);
				waitForLoad(driver, 900);

			}
		}
		*/
		boolean success = false;
		int count = 0;
		int maxTries = 8;
		while (!success) {
			try {
				TimeUnit.MINUTES.sleep(1);
				waitForLoad(driver, 900);
				iconcheckcircle = By.xpath("//div[@class='icon icon-check-circle']");
				iconcheckcirclebox = driver.findElement(iconcheckcircle);
				if (iconcheckcirclebox.isDisplayed()) {
					Add_Log.info("Successfully displayed check icon under status");
					Reporter.log("Successfully displayed check icon under status");
					success = true;

				}
			} catch (Exception e1) {
				click(driver, checkstatus, " icon-refresh");
				Thread.sleep(2000);
				waitForLoad(driver, 900);
					try{
						By iconloading = By.xpath("//div[@class='icon icon-loading']");
						WebElement iconcheckloading = driver.findElement(iconloading);
						if (iconcheckloading.isDisplayed()) {
						Add_Log.info("Successfully displayed loading icon under status");
						Reporter.log("Successfully displayed loading icon under status");
						if (++count == maxTries)
						throw e1;
						Add_Log.info("Unable to displayed calacuator value");
						Reporter.log("Unable to displayed calacuator value");
						ITestResult result = Reporter.getCurrentTestResult();
						result.setAttribute("DontRetry", Boolean.TRUE.toString());
				}
					}
				catch (Exception e) {
					iconcheckcircle = By.xpath("//div[@class='icon icon-check-circle']");
					iconcheckcirclebox = driver.findElement(iconcheckcircle);
						if (iconcheckcirclebox.isDisplayed()) {
						Add_Log.info("Successfully displayed check icon under status");
						Reporter.log("Successfully displayed check icon under status");
						success = true;
						}
                       }
				Thread.sleep(2000);
				waitForLoad(driver, 900);
				
			}
		}
		
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// div[@class='icon icon-check-circle']
		if (iconcheckcirclebox.isDisplayed()) {
			Add_Log.info("Successfully displayed check icon under status");
			Reporter.log("Successfully displayed check icon under status");
		} else {
			Add_Log.info("Unable to displayed check icon under status");
			Reporter.log("Unable to displayed check icon under status");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed check icon under status");
			Assert.fail();
		}
	}

	// Check value under Protoflio result
	public void VerfiyProtoflioresult(WebDriver driver) throws InterruptedException {
		By Portfolioresult = By.xpath(
				"(//*[text()='Portfolio Results']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]");
		WebElement Portfolioresultbox = driver.findElement(Portfolioresult);
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Portfolioresultbox);
		 Thread.sleep(500);
		if (Portfolioresultbox.isDisplayed()) {
			Add_Log.info("Successfully displayed Portfolio Results table content");
			Reporter.log("Successfully displayed Portfolio Results table content");
		} else {
			Add_Log.info("Unable to displayed Portfolio Results table content");
			Reporter.log("Unable to displayed Portfolio Results table content");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Portfolio Results table content");
			Assert.fail();
		}
	}
	
	// Check value under Asset result
	public void VerfiyAssetresult(WebDriver driver) throws InterruptedException {
		
		
		 By Assetresulttab = By.xpath("//span[text()='Asset Class Results']");
		 waitForElementPresent(driver, 120, Assetresulttab, "AssetClassResult");
		 click(driver,Assetresulttab, "Asset Class Result Tab" );
		 Thread.sleep(2000);
		 waitForLoad(driver, 900);
		By Assetresult = By.xpath("//label[text()='Asset Results']//following::td[contains(@class,'x-grid-cell-special x-grid-cell-row-checker x-grid-cell-first')][2]");
		WebElement Assetresultbox = driver.findElement(Assetresult);
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Assetresultbox);
		 Thread.sleep(500);
		if (Assetresultbox.isDisplayed()) {
			Add_Log.info("Successfully displayed Asset Results table content");
			Reporter.log("Successfully displayed Asset Results table content");
		} else {
			Add_Log.info("Unable to displayed Asset Results table content");
			Reporter.log("Unable to displayed Asset Results table content");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Asset Results table content");
			Assert.fail();
		}
	}

	// displayed Profilio Result JTD RWA value
	public void JTDRWAvalue(WebDriver driver, String Verify2, String Report) throws InterruptedException {
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		List<String> views = new ArrayList<>();
		WebElement extractxpathid = driver.findElement(
				By.xpath("//label[text()='" + Verify2sp[2] + "']//following::span[text()='" + Reportsp[2] + "']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		int size = driver.findElements(By.xpath(
				"//td[@class='x-grid-cell x-grid-td x-grid-cell-numbercolumn-" + xpathid[1] + " x-align-right']//div"))
				.size();
		System.out.println(size);
		int i;
		for (i = 1; i < size; i++) {
			views.add(getatt3(driver, By.xpath("(//td[@class='x-grid-cell x-grid-td x-grid-cell-numbercolumn-"
					+ xpathid[1] + " x-align-right']//div)[" + i + "]"), " JTD RWA Value # " + i));
		}
		for (int i2 = 0; i2 < views.size(); i2++)
			if (views.get(i2).startsWith("0")) {
				Add_Log.info("Unable to displayed JTD RWA value " + views.get(i2));
				Reporter.log("Unable to displayed JTD RWA value " + views.get(i));
				TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed JTD RWA value " + views.get(i2));
				ITestResult result = Reporter.getCurrentTestResult();
				result.setAttribute("DontRetry", Boolean.TRUE.toString());
				Assert.fail();
			} else {
				Add_Log.info("Successfully displayed JTD RWA value " + views.get(i2));
				Reporter.log("Successfully displayed JTD RWA value " + views.get(i2));
			}
	}

	// Tc035 // 36 today date Add name and Description for Scenario
	public void WIAaddScenariopoptodaydate(WebDriver driver, String Verify3, String Verify, String viewName)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		waitForElementPresent(driver, 120, addscenarioname);
		click(driver, addscenarioname);
		Thread.sleep(2000);
		String scenarioName = Verify3sp[0] + "-" + sdf.format(timestamp);
		String scenarioDesc = Verify3sp[1] + "-" + sdf.format(timestamp);
		//String scenarioName = Verify3sp[0];
	//	String scenarioDesc = Verify3sp[1] ;
		setText(driver, addscenarioname, scenarioName);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, adddesc);
		click(driver, adddesc);
		Thread.sleep(200);
		setText(driver, adddesc, scenarioDesc);
		Thread.sleep(2000);
		By ele3 = null;
		if (viewName.contains("SACCR")) {
			ele3 = By.xpath("//span[text()='Create']");
		} else {
			ele3 = By.xpath("//span[text()='Save']");
		}
		System.out.println(ele3);
		System.out.println(Verify);
		waitForElementPresent(driver, 120, ele3, " Save/Create button for  " + Verify);
		click(driver, ele3, "Save/Create button for " + Verify);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		waitForElementInvisibilty(driver, 120, addscenarioname);
	}

	// Search Scenario pop box enter value and select the Scenario
	// method for tc 6
	public void WIAaddscenarioSearchtab(WebDriver driver, String viewName, String ReportName, String Verify2,
			String Verify3, String Verify) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		String scenarioName = Verify3sp[1] + "-" + sdf.format(timestamp);
		waitForElementPresent(driver, 120, searchinput);
		click(driver, searchinput);
		Thread.sleep(2000);
		setText(driver, searchinput, scenarioName);
		waitForElementPresent(driver, 120, searchbutton);
		click(driver, searchbutton);
		Thread.sleep(2000);
		try {						
			By ErrorSnackBar = By.xpath("//div[text()='SEARCH PORTFOLIO']//following::div[@class='statusView error']/span");
			if (driver.findElements(ErrorSnackBar).size() > 0) {
				WebElement errormessage = driver.findElement(By.xpath("//div[text()='SEARCH PORTFOLIO']//following::div[@class='statusView error']/span"));
				Reporter.log("Status Bar Appear with message: " + errormessage.getText());
				Add_Log.info("Status Bar Appear with message: " + errormessage.getText());
				TestResultStatus.mpaskeysnew.put(methodName(), "Status Bar Appear with message: " + errormessage.getText());
			}
		} catch (NoSuchElementException e1) {

		}
		String SearchInputtable = "//div[text()='" + Verify2 + "']//following::tr";
		WebPageElements searchinputtable = new WebPageElements(" Search Input table", "xpath", SearchInputtable);
		waitForElementPresent(driver, 120, searchinputtable);
		By sort = By.xpath("//div[text()='" + Verify2 + "']//following::span[contains(@class,'filterswitch')]");
		click(driver, sort, "by drilling down to " + Verify2 + " and click on Sort/Filters");
		click(driver, sortscenariosearchresultname);
		Thread.sleep(2000);
		setText(driver, sortscenariosearchresultname, scenarioName);
		Thread.sleep(2000);
		waitForLoad(driver, 100);
		String id = "(//div[text()='" + Verify2
				+ "']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]";
		String name = "Radio Button to select the Scenario";
		WebPageElements radiobutton = new WebPageElements(name, "xpath", id);
		waitForElementPresent1(driver, 120, radiobutton);
		click(driver, radiobutton);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, selectbutton);
		click(driver, selectbutton);
		Thread.sleep(2000);
		try {
			waitForElementInvisibilty(driver, 40, closeicon);
		} catch (Exception e1) {
			waitForElementPresent1(driver, 120, radiobutton);
			click(driver, radiobutton);
			Thread.sleep(2000);
			waitForElementPresent(driver, 120, selectbutton);
			click(driver, selectbutton);
			Thread.sleep(2000);
			waitForElementInvisibilty(driver, 40, closeicon);
		}
	}

	// displayed Created scenario Name today date for tc 5 and 6
	public void WIAscenarioverfiytodaydate(WebDriver driver, String viewName, String Verify3, String Verify,
			String Report) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		WebElement extractxpathid = driver.findElement(
				By.xpath("//label[text()='" + Verifysp[0] + "']//following::span[text()='" + Report + "']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		By filter = By.xpath("//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div");
		WebElement filterscenarioName = driver.findElement(filter);
		String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
		String Verify3sptodaydate = Verify3sp[0] + "-" + sdf.format(timestamp);
	//	String Verify3sptodaydate = Verify3sp[0];	
		System.out.println(Verify3sptodaydate);
		if (Verify3sptodaydate.equals(filterscenarioNamea)) {
			Add_Log.info("Successfully displayed Created scenario Name = " + filterscenarioNamea);
			Reporter.log("Successfully displayed Created scenario Name = " + filterscenarioNamea);
		} else {
			Add_Log.info("Unable to displayed Created scenario Name = " + Verify3sptodaydate);
			Reporter.log("Unable to displayed Created scenario Name = " + Verify3sptodaydate);
			TestResultStatus.mpaskeysnew.put(methodName(),
					"Unable to displayed Created scenario Name = " + Verify3sptodaydate);
			Assert.fail();
		}
		if (viewName.contains("Banking Book")) {
			if (Verifysp[1].equals("Guarantee")) {
				By clickguarantee = By.xpath("//span[text()='Guarantee']");
				click(driver, clickguarantee, "Expand Secondary Header Guarantee");
				Thread.sleep(2000);
				waitForLoad(driver, 900);
			}
		}
	}

	// click on Search icon for Scenario
	public void WIASearchicon(WebDriver driver, String viewName, String Verify, String ReportName)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		By ele1 = By.xpath("(//label[text()='" + Verifysp[0]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-search '])[1]");
		waitForElementPresent(driver, 120, ele1, " Search icon for " + Verifysp[0]);
		click(driver, ele1,
				"by drilling down to " + ReportName + " - " + viewName + " - " + Verifysp[0] + " Search icon");
		Thread.sleep(2000);
	}

	// Create Trade inside 
	public void CreateTradeInside(WebDriver driver, String viewName,String ReportName, String Verify2,String Verify4,String actualViews,String actualViews3, String InputactualViews,String Inputtext1,String Inputtext2, String Inputtext3)
			throws InterruptedException {
		String[] Verify4sp = Verify4.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualView3sp = actualViews3.split("\\,");
		String[] Inputtext1sp = Inputtext1.split("\\,");
		String[] Inputtext2sp = Inputtext2.split("\\,");
		String[] Inputtext3sp = Inputtext3.split("\\,");
		
		String[] InputactualViewsp = InputactualViews.split("\\,");
		String Asofdate = "//b[contains(text(),'AS OF')]/parent::div/parent::div/parent::div/following-sibling::div[1]";
		WebPageElements AsofDate = new WebPageElements("AS OF date", "xpath", Asofdate);
		waitForElementPresent(driver, 90, AsofDate);
		String AsOfDate = getText(driver, AsofDate);
		System.out.println(AsOfDate);
		By ele1 = By.xpath("(//div[text()='CREATE TRADE']//following::input)[1]");
		waitForElementPresent(driver, 120, ele1, " Asset Class Code ");
		click(driver, ele1, "by drilling down to " + ReportName + " - " + viewName + " Asset Class Code");
		Thread.sleep(2000);	
	//	cobselect(driver, AsOfDate);
		By margined = By.xpath("//label[text()='Margined']");
		waitForElementPresent(driver, 120, margined, "Margined");
		click(driver, margined,"Margined");
		String marginedid = "//input[@name='marginID']";
		WebPageElements Marginedid = new WebPageElements("MarginedID", "xpath", marginedid);
		click(driver, Marginedid);
		setTextentern(driver, Marginedid, "24552223");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		By eletext = By.xpath("//font[text()='24552223'] | //li[text()='24552223']");
		waitForElementPresentnoAssert(driver, 120, eletext, "24552223" );
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		clicknoassert(driver, eletext, "24552223" );
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		By AssetClass =By.xpath("//span[text()='2. Asset Class']");
		waitForElementPresent(driver, 120, AssetClass,"Asset Class");
		click(driver, AssetClass, "by drilling down to " + ReportName + " - " + viewName + "Drop down value for Asset Class Code ");
		Thread.sleep(2000);	
		String assetClassCode = "//input[@name='assetClassCode']";
		WebPageElements AssetClassCode = new WebPageElements("asset Class ", "xpath", assetClassCode);
		click(driver, AssetClassCode);
		String eqty = "//li[text()='EQTY']";
		WebPageElements EQTY = new WebPageElements("eqty", "xpath", eqty);
		click(driver, EQTY);
		Thread.sleep(2000);	
		//String tradeCode = "//input[@name='assetClassCode']";
		String tradeCode="//input[@name='tradeType']/following::div[1]";
		WebPageElements TradeCode = new WebPageElements("Trade code", "xpath", tradeCode);
		click(driver, TradeCode);
		
		String volatility = "//li[text()='Volatility']";
		WebPageElements Volatility = new WebPageElements("Volatility", "xpath", volatility);
		Thread.sleep(2000);	
		click(driver, Volatility);
		waitForLoad(driver, 900);
		Thread.sleep(2000);	
		
		String indicator="//label[text()='"+Inputtext1sp[0]+"']/preceding-sibling::input";
		WebPageElements Indicator = new WebPageElements("indicator Sell/Buy", "xpath", indicator);
		click(driver, Indicator);
		Thread.sleep(2000);
		
		String StartDate="//input[@name='startDate']";
		WebPageElements startdate = new WebPageElements("StartDate", "xpath", StartDate);
		click(driver, startdate);
		Thread.sleep(2000);
		setTextentern(driver,startdate,Inputtext2sp[0]);
		Thread.sleep(2000);
		
		String EndDate="//input[@name='endDate']";
		WebPageElements enddate = new WebPageElements("EndDate", "xpath", EndDate);
		click(driver, enddate);
		Thread.sleep(2000);
		setTextentern(driver,enddate,Inputtext2sp[1]);
		Thread.sleep(2000);
		
		String selectCurrency="//span[text()='Currency-1']/following::div[contains(@class,'combotrig ')]";
		WebPageElements SelectCurrency = new WebPageElements("selectCurrency", "xpath", selectCurrency);
		click(driver, SelectCurrency);
		Thread.sleep(2000);
		String Currency1 = "//li[text()='"+Inputtext2sp[2]+"']";
		WebPageElements currency1 = new WebPageElements("Currency-1", "xpath", Currency1);
		Thread.sleep(2000);	
		click(driver, currency1);
		waitForLoad(driver, 900);
		Thread.sleep(2000);
		
		String Notational="//input[@name='notional']";
		WebPageElements notional = new WebPageElements("Notational", "xpath", Notational);
		click(driver, notional);
		Thread.sleep(2000);
		setTextentern(driver,notional,Inputtext2sp[3]);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
			
		String RegID="//input[@name='regID']";
		WebPageElements regID = new WebPageElements("RegID", "xpath", RegID);
		click(driver, regID);
		Thread.sleep(2000);
		setTextentern(driver,regID,Inputtext2sp[4]);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		
/*		
		String IsOption = "//span[text()='Is Option']";
		WebPageElements isoption = new WebPageElements("Is Option", "xpath", IsOption);
		waitForElementPresent(driver, 120, isoption);
		click(driver,isoption);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		Thread.sleep(3000);
		List<String> secondarylabellist = new ArrayList<>();
		List<WebElement> secondarylabel = driver.findElements(By.xpath("//div[text()='CREATE TRADE']//following::span[@class='x-tab-inner x-tab-inner-secondary-tabs-elements']"));
		for (WebElement ele : secondarylabel) {
			secondarylabellist.add(ele.getAttribute(("textContent")));
		}
		System.out.println(secondarylabellist +" "+ secondarylabellist.size());
		System.out.println(actualViewsp.length);
		for (int i1 = 0; i1 < actualViewsp.length; i1++) {

			if (secondarylabellist.get(i1).equals(actualViewsp[i1].trim().toString()))
			{
				Add_Log.info("Successfully displayed " + secondarylabellist.get(i1) + " under " + Verify2sp[1] + " all tabs ");
				Reporter.log("Successfully displayed " + secondarylabellist.get(i1) + " under " + Verify2sp[1] + " all tabs  ");
			} else {
				Add_Log.info("Unable to display "+ actualViewsp[i1].trim().toString() + " under " + Verify2sp[1]+ " all tabs ");
				Reporter.log("Unable to display "+ actualViewsp[i1].trim().toString() + " under " + Verify2sp[1] + " all tabs ");
				TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display "+ actualViewsp[i1].trim().toString() + " under " + Verify2 + " all tabs ");
				Assert.fail();
			}	
		}
		Thread.sleep(2000);	
		click(driver,isoption);
		waitForLoad(driver, 30);
		Thread.sleep(2000);	
		List<String> views2 = new ArrayList<>();

		List<WebElement> options2 = driver.findElements(By.xpath("//span[text()='Common Details']//following::label[contains(@class,' x-form-item-label-top x-unselectable')] "));
		for (WebElement ele : options2) {
			views2.add(ele.getAttribute(("textContent")));
		}
		System.out.println(views2);
		for (int i2 = 0; i2 < InputactualViewsp.length; i2++) {

			if (views2.get(i2).equals(InputactualViewsp[i2].trim().toString())) {
				Add_Log.info("Successfully displayed " + views2.get(i2) + " under " + Verify2sp[1] + " all content");
				Reporter.log("Successfully displayed " + views2.get(i2) + " under " + Verify2sp[1] + " all content");
			} else {
				Add_Log.info("Unable to display "+ InputactualViewsp[i2].trim().toString() + " under " + Verify2sp[1] + " all content");
				Reporter.log("Unable to display "+ InputactualViewsp[i2].trim().toString() + " under " + Verify2sp[1] + " all content");
				TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display "+ InputactualViewsp[i2].trim().toString() + " under " + Verify2sp[1] + " all content");
				Assert.fail();
			}
		}
		String aggreationfilter = null;
		String inputtextsearch1 = null;
		WebPageElements inputtextfeildid = null;
		for (int i = 0; i < actualView3sp.length; i++) {

			if (actualView3sp[i].trim().toString().equals("Currency Type 1st")) {
				aggreationfilter = "(//div[text()='CREATE TRADE']//following::span[text()='"+actualView3sp[i].trim().toString()+"']//following::div[contains(@id,'trigger-picker')])[1]";
				By aggreationfilterpath = By.xpath(aggreationfilter);
				waitForElementPresent(driver, 120, aggreationfilterpath, actualView3sp[i].trim().toString() );
				click(driver, aggreationfilterpath, actualView3sp[i].trim().toString());
				Thread.sleep(1000);
				inputtextsearch1 ="(//div[text()='CREATE TRADE']//following::span[text()='"+actualView3sp[i].trim().toString()+"']//following::div[contains(@id,'trigger-picker')])[1]//following::li[text()='"+Inputtext3sp[i].trim().toString()+"'][1]";
				click(driver, inputtextsearch1, Inputtext3sp[i].trim().toString());
				Thread.sleep(2000);
				waitForLoad(driver, 900);
			}
			if (actualView3sp[i].trim().toString().equals("Currency Type 2nd")) {
				aggreationfilter = "(//div[text()='CREATE TRADE']//following::span[text()='"+actualView3sp[i].trim().toString()+"']//following::div[contains(@id,'trigger-picker')])[1]";
				By aggreationfilterpath = By.xpath(aggreationfilter);
				waitForElementPresent(driver, 120, aggreationfilterpath, actualView3sp[i].trim().toString() );
				click(driver, aggreationfilterpath, actualView3sp[i].trim().toString());
				Thread.sleep(1000);
				inputtextsearch1 ="(//div[text()='CREATE TRADE']//following::span[text()='"+actualView3sp[i].trim().toString()+"']//following::div[contains(@id,'trigger-picker')])[1]//following::li[text()='"+Inputtext3sp[i].trim().toString()+"'][2]";
				click(driver, inputtextsearch1, Inputtext3sp[i].trim().toString());
				Thread.sleep(2000);
				waitForLoad(driver, 900);
			}
			else {
				inputtextsearch1 = "(//div[text()='CREATE TRADE']//following::span[text()='"+actualView3sp[i].trim().toString()+"']//following::input)[1]";
				inputtextfeildid = new WebPageElements(actualView3sp[i].trim().toString(), "xpath",inputtextsearch1);
				clearText(driver, inputtextfeildid);
				Thread.sleep(1000);
				setTextenter(driver, inputtextfeildid, Inputtext3sp[i].trim().toString());
				Thread.sleep(1000);
			}
		} */
		waitForLoad(driver, 120);
		Thread.sleep(2000);
		WebElement Savebutton = driver.findElement(By.xpath(Save_Btninside));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Savebutton);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		Thread.sleep(4000);
		waitForLoad(driver, 120);


	}

	// tc 02
	public void WIAaddTradeview(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		CapitalWhtif(driver, viewName, ReportName, Verify);
		// Hack to fix ecore problem inside CapitalWhtif.
		// click on Add button for Portfolio
		WIAAddbutton(driver, viewName, Verify, ReportName);
		// Header for SCENARIO
		WIAHeadername(driver, Verify2sp[0]);
		// Add name and Description for portfolio
		WIAaddportfoliopop(driver, Verify3, Verify,viewName);
		// displayed Created scenario Name
		WIAscenarioverfiy(driver, viewName, Verify3, Verify, Reportsp[0]);
		// click Scenario Search Facility
		WIASearchFacility(driver, viewName, Verifysp[0], ReportName);
		// Header for Search
		WIAHeadername(driver, Verify2sp[1]);
		// if error
		Snackbar(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// COB select
		cobselect(driver, reportingPeriod);
		// Enter Portfolio and Search / and add
		EnterPortfolioandSearch(driver, Verify2, Verify4);
		// Verfiy select Portfolio Trade and CSA view in main page
		WIATradeCSAverfiy(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Click on Check box for TradeVIew and CSA view
		TradeCSAViewradiobutton(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Click on Calculator for Portfolio
		Portfoliocalculator(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Submit Pop click on get message
		Sumbitpopok(driver);
		// Check calculator Status until
		Checkcalculatorstatus(driver);
		// Check value under Protoflio result
		VerfiyProtoflioresult(driver);
		// displayed Profilio Result JTD RWA value		
		JTDRWAvalue(driver, Verify2, Report);
	}

	// added method for tc 34
	public void Expandallsubheader(WebDriver driver) throws InterruptedException {
		int size1 = driver.findElements(By.xpath("//span[@class='x-tab-inner x-tab-inner-secondary-tabs-elements']"))
				.size();
		for (int i = 1; i <= size1; i++) {
			By expandsecondaryheader = By
					.xpath("(//span[@class='x-tab-inner x-tab-inner-secondary-tabs-elements'])[" + i + "]");
			click(driver, expandsecondaryheader, "Expand Secondary Header");
			Thread.sleep(2000);
			waitForLoad(driver, 120);
		}
	}

	// TC 34
	public void WIAheader(WebDriver driver, String viewName, String actualViews, String ReportName, String Verify)
			throws InterruptedException {
		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		List<String> views = new ArrayList<>();
		CapitalWhtif(driver, viewName, ReportName, Verify);
		// added method for tc 4
		Expandallsubheader(driver);
		int size = actualViewsp.length;
		Thread.sleep(500);
		for (int i = 1; i <= size; i++) {
			views.add(getatt3(driver, By.xpath(
					"(//label[@class='x-component custom-header x-box-item x-component-default x-component-before-title'])["
							+ i + "]"),
					" Filter criteria Header #" + i));
		}
		System.out.println(views);
		for (int i = 0; i < actualViewsp.length; i++) {
			if (views.get(i).equals(actualViewsp[i].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp[i].trim().toString() + " Filter criteria Header "
						+ ReportName + " " + viewName);
				Reporter.log("Successfully displayed " + actualViewsp[i].trim().toString() + " Filter criteria Header "
						+ ReportName + " " + viewName);
			} else {
				Add_Log.info(actualViewsp[i].trim().toString()
						+ " Filter criteria Header is not displayed after drilling down for " + ReportName + "- "
						+ viewName);
				Reporter.log(actualViewsp[i].trim().toString()
						+ " Filter criteria Header is not displayed after drilling down for  " + ReportName + "- "
						+ viewName);
				TestResultStatus.mpaskeysnew.put(methodName(),
						actualViewsp[i].trim().toString()
						+ " Filter criteria Header is not displayed after drilling down for  " + ReportName
						+ "- " + viewName);
				flag = true;
			}
		}
		if (flag) {
			TestResultStatus.Testfail = true;
			allFlag = true;
			Assert.fail();
		} else {
			Add_Log.info("Successfully all Filter criteria Header are displayed after drilling down " + ReportName
					+ " - " + viewName);
			Reporter.log("Successfully all Filter criteria Header are displayed after drilling down " + ReportName
					+ " - " + viewName);
		}
	}

	// for other test cases
	public void CreatePortolio(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		CapitalWhtif(driver, viewName, ReportName, Verify);
		// click on Add button for Portfolio
		WIAAddbutton(driver, viewName, Verify, ReportName);
		// Header for SCENARIO
		WIAHeadername(driver, Verify2sp[0]);
		// Add name and Description for portfolio
		WIAaddportfoliopop(driver, Verify3, Verify,viewName);
		// displayed Created scenario Name
		WIAscenarioverfiy(driver, viewName, Verify3, Verify, Reportsp[0]);

	}
	public void WIACSACreateinside(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String InputactualViews)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] InputactualViewsp = InputactualViews.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		List<String> views2 = new ArrayList<>();
		String csacreatelabel = "//div[text()='"+Verify2sp[1]+"']//following::label[contains(@class,'x-form-item-label x-form-item-label-default  x-form-item-label-top x-unselectable')] | //div[@class='x-title x-btn-group-header-title x-btn-group-header-title-default x-box-item x-title-default x-title-rotate-none x-title-align-left']";
		
		List<WebElement> options2 = driver.findElements(By.xpath(csacreatelabel));
		for (WebElement ele : options2) {
			views2.add(ele.getAttribute(("textContent")));
		}
		System.out.println(views2);
		System.out.println(actualViews);
		int size1 = driver.findElements(By.xpath(csacreatelabel)).size();
		for (int i1 = 0; i1 < size1; i1++) {
			if (views2.get(i1).equals(actualViewsp[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + views2.get(i1) + " under "  + " all tabs " + Verifysp[1]);
				Reporter.log("Successfully displayed " + views2.get(i1) + " under "  + " all tabs  " + Verifysp[1]);
			} else {
				Add_Log.info("Unable to display "+ views2.get(i1) + " under " + " all tabs " + Verifysp[1]);
				Reporter.log("Unable to display "+ views2.get(i1) + " under " + " all tabs " + Verifysp[1]);
				TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display "+ views2.get(i1) + " under " + " all tabs " + Verifysp[1]);
				Assert.fail();
			}
		}
		String aggreationfilter= null;
		String inputtextsearch1 = null;
		for (int i1 = 0; i1 < InputactualViewsp.length; i1++) {
		}
		for (int i = 0; i < views2.size(); i++)  {		
				aggreationfilter = "(//div[text()='"+Verify2sp[1] +"']//following::span[text()='"+views2.get(i)+"']//following::input)[1]";
				inputtextsearch1 = "(//div[text()='"+Verify2sp[1] +"']//following::span[text()='"+views2.get(i)+"']//following::input)[1]";
			WebElement aggreationfilterweb = driver.findElement(By.xpath(aggreationfilter));
			By aggreationfilterid = (By.xpath(aggreationfilter));
			WebDriverWait wait = new WebDriverWait(driver, 120);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
			click(driver, aggreationfilterid, views2.get(i));
			WebPageElements inputtextfeildid = new WebPageElements(InputactualViewsp[i].trim().toString(), "xpath", inputtextsearch1);
			clearText(driver, inputtextfeildid);
			Thread.sleep(1000);
			setTextenter(driver, inputtextfeildid, InputactualViewsp[i]);
			Thread.sleep(1000);
		}

		Thread.sleep(1000);
		waitForLoad(driver, 120);
		Thread.sleep(2000);
		WebElement Savebutton = driver.findElement(By.xpath(Save_Btninside));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Savebutton);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		waitForElementInvisibilty(driver, 120, save_btninside);
		Thread.sleep(2000);
		String SearchInputtable = "//label[text()='CSA VIEW']//following::tr";
		WebPageElements searchinputtable  = new WebPageElements(" Created CSA id  under table content", "xpath",SearchInputtable);
		waitForElementPresent(driver, 120, searchinputtable);
	}
	//Create Netting View Inside 
	public void NetViewCreateinside(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String InputactualViews)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] InputactualViewsp = InputactualViews.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		List<String> views2 = new ArrayList<>();
		String csacreatelabel = "//div[text()='"+Verify2sp[1]+"']//following::label[contains(@class,'x-form-item-label x-form-item-label-default  x-form-item-label-top x-unselectable')] | //div[@class='x-title x-btn-group-header-title x-btn-group-header-title-default x-box-item x-title-default x-title-rotate-none x-title-align-left']";
		
		List<WebElement> options2 = driver.findElements(By.xpath(csacreatelabel));
		for (WebElement ele : options2) {
			views2.add(ele.getAttribute(("textContent")));
		}
		System.out.println(views2);
		System.out.println(actualViews);
		int size1 = driver.findElements(By.xpath(csacreatelabel)).size();
		for (int i1 = 0; i1 < size1; i1++) {
			if (views2.get(i1).equals(actualViewsp[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + views2.get(i1) + " under "  + " all tabs " + Verifysp[1]);
				Reporter.log("Successfully displayed " + views2.get(i1) + " under "  + " all tabs  " + Verifysp[1]);
			} else {
				Add_Log.info("Unable to display "+ views2.get(i1) + " under " + " all tabs " + Verifysp[1]);
				Reporter.log("Unable to display "+ views2.get(i1) + " under " + " all tabs " + Verifysp[1]);
				TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display "+ views2.get(i1) + " under " + " all tabs " + Verifysp[1]);
				Assert.fail();
			}
		}
		String aggreationfilter = null;
		String inputtextsearch1 = null;
		WebPageElements inputtextfeildid = null;
		for (int i = 0; i < InputactualViewsp.length; i++) {
			inputtextsearch1 = "(//div[text()='CREATE NETTINGSET ATTRIBUTE']//following::span[text()='"+InputactualViewsp[i].trim().toString()+"']//following::input)[1]";
			inputtextfeildid = new WebPageElements(InputactualViewsp[i].trim().toString(), "xpath",inputtextsearch1);
			clearText(driver, inputtextfeildid);
			Thread.sleep(1000);
			setTextenter(driver, inputtextfeildid,"Selenium");
			Thread.sleep(1000);
		}
		

		Thread.sleep(1000);
		waitForLoad(driver, 120);
		Thread.sleep(2000);
		WebElement Savebutton = driver.findElement(By.xpath(Save_Btninside));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Savebutton);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		waitForElementInvisibilty(driver, 120, save_btninside);
		Thread.sleep(2000);
		String SearchInputtable = "//label[text()='CSA VIEW']//following::tr";
		WebPageElements searchinputtable  = new WebPageElements(" Created CSA id  under table content", "xpath",SearchInputtable);
		waitForElementPresent(driver, 120, searchinputtable);
	}
	// Tc035
	public void WIAaddscenarioCreatetodaydate(WebDriver driver, String viewName, String ReportName, String Verify2,
			String Verify3, String Verify, String Report) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		// CapitalWhtif added in Main method to re-use this code
		// click on Add button for Scenario
		WIAAddbutton(driver, viewName, Verify, ReportName);
		// Header for SCENARIO
		WIAHeadername(driver, Verify2sp[0]);
		// Add name and Description for Scenario
		WIAaddScenariopoptodaydate(driver, Verify3, Verify,viewName);
		// displayed Created scenario Name
		WIAscenarioverfiytodaydate(driver, viewName, Verify3, Verify, Reportsp[0]);
		//click on Save Secnario 
		String SaveIcon = "//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-save ']";
		WebPageElements saveicon  = new WebPageElements(" Save Icon", "xpath",SaveIcon);
		waitForElementPresent(driver, 120, saveicon);
		click(driver, saveicon);
		Thread.sleep(1000);
		String PickVisible = "//div[contains(@class,'x-form-trigger x-form-trigger-default combotrig')]";
		WebPageElements pickvisible  = new WebPageElements("Pick Visibility of Scenario : ", "xpath",PickVisible);
		waitForElementPresent(driver, 120, pickvisible);
		click(driver, pickvisible);
		Thread.sleep(1000);
		String PickVisibleText = "//li[text()='PRIVATE']";
		WebPageElements pickvisibletext  = new WebPageElements("PRIVATE", "xpath",PickVisibleText);
		waitForElementPresent(driver, 120, pickvisibletext);
		click(driver, pickvisibletext);
		waitForLoad(driver, 120);
		Thread.sleep(1000);
		WebElement Savebutton = driver.findElement(By.xpath(Save_Btninside));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Savebutton);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		waitForLoad(driver, 120);
		Thread.sleep(1000);
	}

	// tc 36
	public void SearchPortolio(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		CapitalWhtif(driver, viewName, ReportName, Verify);
		// click on Search button for Portfolio
		By ele1 = By.xpath("(//label[text()='" + Verifysp[0]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-search '])[1]");
		waitForElementPresent(driver, 120, ele1, " Search icon for " + Verifysp[0]);
		click(driver, ele1,
				"by drilling down to " + ReportName + " - " + viewName + " - " + Verifysp[0] + " Search icon");
		Thread.sleep(2000);
		// Header for SCENARIO
		WIAHeadername(driver, Verify2);
		// Search Scenario pop box enter value and select the Scenario
		WIAaddscenarioSearchtab(driver, viewName, ReportName, Verify2, Verify3, Verify);
		// displayed Created / Search scenario Name
		WIAscenarioverfiytodaydate(driver, viewName, Verify3, Verify, Reportsp[0]);
	}

	// TC037	  
	public void EditPortolio(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod) throws InterruptedException { 
		String[]Verifysp = Verify.split("\\,");
		String[]Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String scenarioName = null;
		// for other test cases 
		CreatePortolio(driver,viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod); 
		// Tc 07 click Edit Icon
		By ele1 = By.xpath("(//label[text()='" + Verifysp[0]+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-edit '])[1]");
		waitForElementPresent(driver, 120, ele1, " Edit icon for " + Verifysp[0]);
		click(driver, ele1, Verifysp[0] + " Edit icon");
		Thread.sleep(2000);
		// Header for SCENARIO
		WIAHeadername(driver, Verify2sp[0]);
		// String add current date String
		scenarioName = Verify3sp[0] + "-" + sdf.format(timestamp);
		// Add name and Description for portfolio
		Editportfoliopop(driver, scenarioName, Verify,viewName);
		// displayed Created scenario Name
		WIAscenarioverfiy(driver, viewName, scenarioName, Verify, Reportsp[0]);
	}
	// TC038
	public void SearchPortolioID(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod) throws InterruptedException { 
		String[]Verifysp = Verify.split("\\,");
		String[]Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String scenarioName = null;
		// for other test cases 
		CreatePortolio(driver,viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
		// click Scenario Search Portolio id
		WIASearchFacility(driver, viewName, Verifysp[0], ReportName);
		// Header for Search
		WIAHeadername(driver, Verify2sp[1]);
		// if error
		Snackbar(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// COB select
		cobselect(driver, reportingPeriod);
		// Enter Portfolio and Search / and add
		EnterPortfolioandSearch(driver, Verify2, Verify4);
		//Verify Create Trade on Landing Page 
		if(Verifysp[1].equals("Trade View"))
		{
			Tradeverfiy(driver);
			}
		if(Verifysp[1].equals("CSA VIEW"))
		{
			CSAverfiy(driver);
			}
		if(Verifysp[1].equals("Netting Set View"))
		{
			By clickguarantee = By.xpath("(//span[text()='Netting Set View'])[1]");
			click(driver, clickguarantee, "Expand Secondary Netting Set View");
			Thread.sleep(3000);
			waitForLoad(driver, 900);
			NetViewverfiy(driver);
			}
		
		else {
			Tradeverfiy(driver);
		}

	}
	// TC 039 Create Trade 
	public void CreateTrade(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod,String Inputtext1,String Inputtext2, String Inputtext3,String actualViews, String actualViews2, String actualViews3, String InputactualViews) throws InterruptedException {
		String[]Verifysp = Verify.split("\\,");
		String[]Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] Reportsp = Report.split("\\,");
		// for other test cases 
		CreatePortolio(driver,viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
		//Click on Add-Create Trade icon
		WIAAddbutton(driver,viewName,Verifysp[1],ReportName);

		// Header for Create
		WIAHeadername(driver, Verify2sp[1]);

		// Create trade inside 
		CreateTradeInside(driver, viewName,ReportName, Verify2,Verify4,actualViews,actualViews3, InputactualViews, Inputtext1,Inputtext2,Inputtext3);
		//Verify Create Trade on Landing Page 
		Tradeverfiy(driver);
	}


	// TC040 Clone/Edit Trade 

	public void TradeClone(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod,String InputactualViews,String actualViews) throws InterruptedException {
		String[]Verifysp = Verify.split("\\,");
		String[] InputactualViewsp = InputactualViews.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		SearchPortolioID(driver,viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
		String clickonclonecheckbox = "(//*[text()='Trade View']//following::div[@class='icon icon-copy'])[1]";
		WebPageElements clickonclonecheckboxpath  = new WebPageElements(" Trade view clone check box", "xpath",clickonclonecheckbox);
		click(driver, clickonclonecheckboxpath);
		String CloneIconTrade = "(//label[text()='"+Verifysp[1]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-copy '])[1]";
		WebPageElements cloneicontrade = new WebPageElements("Clone icon for" + Verifysp[1], "xpath", CloneIconTrade);
		Thread.sleep(2000);
		waitForLoad(driver, 90);
		click(driver, cloneicontrade);
		Thread.sleep(2000);
		waitForLoad(driver, 90);
		// Click on Hyper Trade 		
		String HyperIconTrade = "(//label[text()='"+Verifysp[1]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-switch '])[1]";
		System.out.println(HyperIconTrade);
		WebPageElements HyperIcon  = new WebPageElements("View Hypothetical Trade", "xpath",HyperIconTrade);
		waitForElementPresent(driver, 120, HyperIcon);
		Thread.sleep(2000);
		click(driver,HyperIcon);
		waitForLoad(driver, 90);
		waitForLoad(driver, 90);
		String EditIconTrade = "(//label[text()='"+Verifysp[1]+"']//following::div[@class='icon icon-edit-advanced'])[1]";
		WebPageElements editicontrade = new WebPageElements("Edit icon for " + Verifysp[1], "xpath", EditIconTrade);
		waitForElementPresent(driver, 120, editicontrade);
		click(driver, editicontrade);
		Thread.sleep(2000);
		//click all checkbox and uncheck
		hackCheckbox(driver);
		// Header for Edit
		WIAHeadername(driver, "EDIT TRADE");
		//
		String aggreationfilter = null;
		String inputtextsearch1 = null;
		WebPageElements inputtextfeildid = null;
		for (int i = 0; i < InputactualViewsp.length; i++) {
			inputtextsearch1 = "(//div[text()='EDIT TRADE']//following::span[text()='"+InputactualViewsp[i].trim().toString()+"']//following::input)[1]";
			inputtextfeildid = new WebPageElements(InputactualViewsp[i].trim().toString(), "xpath",inputtextsearch1);
			clearText(driver, inputtextfeildid);
			Thread.sleep(1000);
			setTextenter(driver, inputtextfeildid,actualViewsp[i]);
			Thread.sleep(1000);
		}
		waitForLoad(driver, 120);
		Thread.sleep(2000);
		WebElement Savebutton = driver.findElement(By.xpath(Save_Btninside));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Savebutton);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		Thread.sleep(4000);
		waitForLoad(driver, 900);
		
		//Filtered Edited Hyper Trade
		waitForElementPresent(driver, 120, HyperIcon);
		Thread.sleep(2000);
		
			click(driver,HyperIcon);
			Thread.sleep(2000);
			click(driver,HyperIcon);
			Thread.sleep(5000);
		waitForLoad(driver, 90);
		
		
		//Verified Edit Value in landing Page 
		
		WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='" + Verifysp[1] + "']//following::span[text()='REG VM ID']"));	
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		String xpathdivtablestring = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1]+ "']//div";
		String filterscenarioNamea = null;
		By filter = By.xpath(xpathdivtablestring);
		WebElement filterscenarioName = driver.findElement(filter);
		filterscenarioNamea = filterscenarioName.getAttribute("textContent");
		System.out.println(filterscenarioNamea);
		System.out.println(actualViews);

		if (actualViews.contains(filterscenarioNamea)) {
			Add_Log.info("Successfully displayed Clone/Edit " + Verifysp[1]+ " = " + actualViews);
			Reporter.log("Successfully displayed Clone/Edit " + Verifysp[1] + " = " + actualViews);

		} else {
			Add_Log.info("Unable to displayed Clone/Edit " + Verifysp[1] + " = " + actualViews);
			Reporter.log("Unable to displayed Clone/Edit " + Verifysp[1]+ " = " + actualViews);
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Clone/Edit " + Verifysp[1] + " = " + actualViews);
			Assert.fail();
		}		
	}
	// TC041
	public void TradeDelete(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod) throws InterruptedException { 
		String[]Verifysp = Verify.split("\\,");
		String[]Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String scenarioName = null;
		// TC038
		SearchPortolioID(driver,viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
		//Delete icon 

		By ele1 = By.xpath("(//div[@class='x-column-header x-column-header-checkbox x-column-header-align-left x-box-item x-column-header-coreselectcolumn x-unselectable x-column-header-first'])[1]");
		By ele2 = By.xpath("(//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-trash '])[1]");
		waitForElementPresent(driver, 30, ele1, " Check box for all item " + Verifysp[1]);
		click(driver, ele1, " Check box for " + Verifysp[1]);
		Thread.sleep(1000);
		waitForLoad(driver, 120);	
		waitForElementPresent(driver, 30, ele2, " Delete icon" + Verifysp[1]);
		click(driver, ele2, " Delete icon " + Verifysp[1]);
		Thread.sleep(1000);
		waitForLoad(driver, 120);

		WebElement extractxpathid = driver.findElement(
				By.xpath("//label[text()='" + Verifysp[1] + "']//following::span[text()='" + Reportsp[1] + "']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		String xpathdivtable = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div";
		By filter = By.xpath(xpathdivtable);
		waitForInVisiblility(driver, 120, filter, "Empty table content after deleting all " + Verifysp[1]);
	}

	// TC042
	public void TradeDownload(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod, String Fileformat, String FileNameac) throws InterruptedException, IOException { 
		String[]Verifysp = Verify.split("\\,");
		String[]Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String scenarioName = null;
		// TC038
		SearchPortolioID(driver,viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
		//Downloadicon
		// click on download icon
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By ele1 = By.xpath("(//label[text()='" + Verifysp[1]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-excel '])[1]");
		waitForElementPresent(driver, 120, ele1, " Download icon" + Verifysp[1]);
		clicknosleep(driver, ele1, " Download icon " + Verifysp[1]);
		// download in main method.
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
	}

	// TC043
	public void TradeSortFilter(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod, String actualViews) throws InterruptedException, IOException { 
		String[]Verifysp = Verify.split("\\,");
		String[]Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		List<String> views2 = new ArrayList<>();
		// TC038
		SearchPortolioID(driver,viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
		//Sortfliter
		List<String> views = new ArrayList<>();
		WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verifysp[1]+"']//following::span[text()='"+Reportsp[1]+"']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);

		String xpathdivtable = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div";
		By filter = By.xpath(xpathdivtable);
		WebElement filterscenarioName = driver.findElement(filter);
		String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
		List<WebElement> options2 = driver.findElements(filter);
		for (WebElement ele : options2) {
			views.add(ele.getAttribute(("textContent")));
		}
		System.out.println(views);
		System.out.println(views.get(1));

		String sortclick = "//label[text()='"+Verifysp[1]+"']//following::span[contains(@class,'filterswitch')]";
		By sort = By.xpath(sortclick);
		click(driver, sort,"by drilling down to " + Verifysp[1]+ " and click on Sort/Filters");
		Thread.sleep(2000);	
		String filterinput ="(//span[text()='"+Reportsp[1]+"']//following::input[contains(@name,'textfield')])[4]";
		WebPageElements filterinput_dll = new WebPageElements("Sort/filiter", "xpath", filterinput);
		System.out.println(filterinput);
		setTextenter(driver, filterinput_dll,views.get(1));
		Thread.sleep(2000);
		waitForLoad(driver, 100);
		
		String SearchInputtable2 = "//label[text()='Trade View']//following::tr//div[text()='"+views.get(1)+"']";
		WebPageElements searchinputtable2  = new WebPageElements(" Search Input table 2nd row", "xpath",SearchInputtable2);
		waitForElementPresent(driver, 120, searchinputtable2);
		
		String ExposureSUMMARYFIELD = "(//label[text()='"+Verifysp[1]+"']//following::div[@class='x-grid-header-ct x-docked x-grid-header-ct-default x-docked-top x-grid-header-ct-docked-top x-grid-header-ct-default-docked-top x-box-layout-ct'])[1]//child::div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']";
		WebPageElements exposuresummaryfield = new WebPageElements("Exposure Summary filter", "xpath",
				ExposureSUMMARYFIELD);
		
		waitForElementPresent(driver, 30, exposuresummaryfield);
		int size1 = driver.findElements(By.xpath(ExposureSUMMARYFIELD)).size();
		for (int i = 1; i <= size1; i++) {
			views2.add(getatt(driver, By.xpath("(" + ExposureSUMMARYFIELD + ")[" + i + "]"), Verifysp[1]+ " table Feild Content #" + i));	
		}
		System.out.println(views2);
	
	System.out.println(actualViewsp.length);
	for (int i1 = 0; i1 < actualViewsp.length; i1++) {

		if (views2.get(i1).equals(actualViewsp[i1].trim().toString()))
		{
			Add_Log.info("Successfully displayed " + views2.get(i1) + " under " + Verify2sp[1] + " all tabs ");
			Reporter.log("Successfully displayed " + views2.get(i1) + " under " + Verify2sp[1] + " all tabs  ");
		} else {
			Add_Log.info("**Unable to display "+ actualViewsp[i1].trim().toString() + " under " + Verify2sp[1]+ " all tabs ");
			Reporter.log("**Unable to display "+ actualViewsp[i1].trim().toString() + " under " + Verify2sp[1] + " all tabs ");
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display "+ actualViewsp[i1].trim().toString() + " under " + Verify2 + " all tabs ");
		//	Assert.fail();
		}	
	}
	}
	
	//TC 44 
	
	public void TradeHypothetical(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod,String InputactualViews,String actualViews) throws InterruptedException {
		String[]Verifysp = Verify.split("\\,");
		String[] InputactualViewsp = InputactualViews.split("\\,");
		String[] Reportsp = Report.split("\\,");
		TradeClone(driver,viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,InputactualViews,actualViews);
		String HyperIconTrade = "(//label[text()='"+Verifysp[1]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-switch '])[1]";
		System.out.println(HyperIconTrade);
		WebPageElements HyperIcon  = new WebPageElements("View Hypothetical Trade", "xpath",HyperIconTrade);
		waitForElementPresent(driver, 120, HyperIcon);
		Thread.sleep(2000);
		if(methodName().equals("WhatifSACCR_TC044"))
		{
			
		}
		else {
			click(driver,HyperIcon);
		}
		Thread.sleep(3000);
		waitForLoad(driver, 90);
		WebElement extractxpathid1 = driver.findElement(By.xpath("//label[text()='"+Verifysp[1]+"']//following::span[text()='"+Reportsp[1]+"']"));
		String pathnum1 = extractxpathid1.getAttribute("id");
		String[] xpathid1 = pathnum1.split("-");
		System.out.println(xpathid1[0]);
		System.out.println(xpathid1[1]);
		System.out.println(xpathid1[2]);

		String xpathdivtable = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid1[1] + "']//div";
		By filter = By.xpath(xpathdivtable);
		WebElement filterscenarioName = driver.findElement(filter);
		String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
		
	System.out.println(filterscenarioNamea);
	 if(filterscenarioNamea.startsWith("HT")) {
		 Reporter.log("Successfully able to View Hypothetical Trades only ");
		Add_Log.info("Successfully able to View Hypothetical Trades only ");
	 }
	 else
	 {
		 Reporter.log("Unable to View Hypothetical Trades only ");
		 Add_Log.info("Unable to View Hypothetical Trades only ");
		 TestResultStatus.mpaskeysnew.put(methodName(),"Unable to View Hypothetical Trades only ");
		Assert.fail();
	 }
	}
	
	// TC45
	public void CreateCSA(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod,String Inputtext1,String Inputtext2, String Inputtext3,String actualViews, String actualViews2, String actualViews3, String InputactualViews) throws InterruptedException {
		String[]Verifysp = Verify.split("\\,");
		String[]Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] Reportsp = Report.split("\\,");
		// for other test cases 
		CreatePortolio(driver,viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
		if(Verifysp[1].equals("Netting Set View"))
		{
			By clickguarantee = By.xpath("(//span[text()='Netting Set View'])[1]");
			click(driver, clickguarantee, "Expand Secondary Netting Set View");
			Thread.sleep(3000);
			waitForLoad(driver, 900);
		}
		
		//Click on Add-Create Trade icon
		WIAAddbutton(driver,viewName,Verifysp[1],ReportName);

		// Header for Create
		WIAHeadername(driver, Verify2sp[1]);
		if(Verifysp[1].equals("CSA VIEW"))
		{
		// Create trade inside 
		WIACSACreateinside(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report, actualViews, InputactualViews);
		//Verify Create Trade on Landing Page 
		CSAverfiy(driver);
		}
		if(Verifysp[1].equals("Netting Set View"))
		{
		// Create trade inside 
		NetViewCreateinside(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report, actualViews, InputactualViews);
		//Verify Create Trade on Landing Page 
		NetViewverfiy(driver);
		}
		
	}
	
	public void CSAClone(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod,String InputactualViews,String actualViews) throws InterruptedException {
		String[]Verifysp = Verify.split("\\,");
		String[] InputactualViewsp = InputactualViews.split("\\,");
		List<String> views = new ArrayList<>();
		
		SearchPortolioID(driver,viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
		if(Verifysp[1].equals("CSA VIEW"))
		{
		String clickonclonecheckbox = "(//*[text()='"+Verifysp[1]+"']//following::div[@class='icon icon-copy'])[1]";
		WebPageElements clickonclonecheckboxpath  = new WebPageElements(Verifysp[1] + "clone check box", "xpath",clickonclonecheckbox);
		click(driver, clickonclonecheckboxpath);
		String CloneIconTrade = "(//label[text()='"+Verifysp[1]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-copy '])[1]";
		WebPageElements cloneicontrade = new WebPageElements("Clone icon for" + Verifysp[1], "xpath", CloneIconTrade);
		Thread.sleep(2000);
		waitForLoad(driver, 90);
		click(driver, cloneicontrade);
		Thread.sleep(2000);
		waitForLoad(driver, 90);
		// Header for Edit
		WIAHeadername(driver, "CLONE CSA ATTRIBUTE");
		}
		
		if(Verifysp[1].equals("Netting Set View")) {
			String clickonclonecheckbox = "(//label[text()='"+Verifysp[1]+"']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]//following::div[@class='icon icon-copy']";
			System.out.println(clickonclonecheckbox);
			WebPageElements clickonclonecheckboxpath  = new WebPageElements(Verifysp[1] + "clone check box", "xpath",clickonclonecheckbox);
			click(driver, clickonclonecheckboxpath);
			Thread.sleep(2000);
			waitForLoad(driver, 90);
			WIAHeadername(driver, "CLONE NETTINGSET ATTRIBUTE");
		}
	
		String inputtextsearch1 = null;
		String aggreationfilter = null;
		WebPageElements inputtextfeildid = null;
		for (int i = 0; i < InputactualViewsp.length; i++) {
			if(Verifysp[1].equals("CSA VIEW"))
			{
			inputtextsearch1 = "(//div[text()='CLONE CSA ATTRIBUTE']//following::span[text()='"+InputactualViewsp[i].trim().toString()+"']//following::input)[1]";
			}
			if(Verifysp[1].equals("Netting Set View"))
			{
			inputtextsearch1 = "(//div[text()='CLONE NETTINGSET ATTRIBUTE']//following::span[text()='"+InputactualViewsp[i].trim().toString()+"']//following::input)[1]";
			}
			inputtextfeildid = new WebPageElements(InputactualViewsp[i].trim().toString(), "xpath",inputtextsearch1);
			clearText(driver, inputtextfeildid);
			Thread.sleep(1000);
			setTextenter(driver, inputtextfeildid,actualViews);
			Thread.sleep(1000);
		}
		waitForLoad(driver, 120);
		Thread.sleep(2000);
		WebElement Savebutton = driver.findElement(By.xpath(Save_Btninside));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Savebutton);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		Thread.sleep(4000);
		//Verified Edit Value in landing Page 
		WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='" + Verifysp[1] + "']//following::span[text()='"+InputactualViewsp[0]+"']"));	
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		String xpathdivtablestring = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1]+ "']//div";
		String filterscenarioNamea = null;
		int size1 = driver.findElements(By.xpath(xpathdivtablestring)).size();
		for (int i = 1; i <= size1; i++) {
			By filter = By.xpath(xpathdivtablestring);
			WebElement filterscenarioName = driver.findElement(filter);
			filterscenarioNamea = filterscenarioName.getAttribute("textContent");
			List<WebElement> options2 = driver.findElements(filter);
			for (WebElement ele : options2) {
				views.add(ele.getAttribute(("textContent")));
			}
		System.out.println(views);
		System.out.println(actualViews);
		if (views.contains(actualViews)) {
			Add_Log.info("Successfully displayed Clone/Edit " + Verifysp[1]+ " = " + actualViews);
			Reporter.log("Successfully displayed Clone/Edit " + Verifysp[1] + " = " + actualViews);

		} else {
			Add_Log.info("Unable to displayed Clone/Edit " + Verifysp[1] + " = " + actualViews);
			Reporter.log("Unable to displayed Clone/Edit " + Verifysp[1]+ " = " + actualViews);
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Clone/Edit " + Verifysp[1] + " = " + actualViews);
			Assert.fail();
		}		
	}
	}
	// TC041
		public void CSADelete(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
				String Verify, String Report, String Verify4, String reportingPeriod) throws InterruptedException { 
			String[]Verifysp = Verify.split("\\,");
			String[]Verify2sp = Verify2.split("\\,");
			String[] Verify3sp = Verify3.split("\\,");
			String[] Reportsp = Report.split("\\,");
			String scenarioName = null;
			// TC038
			SearchPortolioID(driver,viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
			//Delete icon 

			By ele1 = By.xpath("(//label[text()='"+Verifysp[1]+"']//following::div[@class='x-column-header-inner x-column-header-inner-empty x-column-header-over'])[1]");
			By ele2 = By.xpath("(//label[text()='"+Verifysp[1]+"']//following::span[contains(@class,'icon-trash')])[1]");
			
			waitForElementPresent(driver, 30, ele1, " Check box for all item " + Verifysp[1]);
			click(driver, ele1, " Check box for " + Verifysp[1]);
			Thread.sleep(1000);
			waitForLoad(driver, 120);	
			waitForElementPresent(driver, 30, ele2, " Delete icon" + Verifysp[1]);
			click(driver, ele2, " Delete icon " + Verifysp[1]);
			Thread.sleep(1000);
			waitForLoad(driver, 120);

			WebElement extractxpathid = driver.findElement(
					By.xpath("//label[text()='" + Verifysp[1] + "']//following::span[text()='" + Reportsp[1] + "']"));
			String pathnum = extractxpathid.getAttribute("id");
			String[] xpathid = pathnum.split("-");
			System.out.println(xpathid[0]);
			System.out.println(xpathid[1]);
			System.out.println(xpathid[2]);
			String xpathdivtable = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div";
			By filter = By.xpath(xpathdivtable);
			waitForInVisiblility(driver, 120, filter, "Empty table content after deleting all " + Verifysp[1]);
		}
		
		// TC048
		public void CSADownload(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
				String Verify, String Report, String Verify4, String reportingPeriod, String Fileformat, String FileNameac) throws InterruptedException, IOException { 
			String[]Verifysp = Verify.split("\\,");
			String[]Verify2sp = Verify2.split("\\,");
			String[] Verify3sp = Verify3.split("\\,");
			String[] Reportsp = Report.split("\\,");
			String scenarioName = null;
			// TC038
			SearchPortolioID(driver,viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
			//Downloadicon
			// click on download icon
			Thread.sleep(2000);
			waitForLoad(driver, 120);
			By ele1 = By.xpath("(//label[text()='" + Verifysp[1]
					+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-excel '])[1]");
			waitForElementPresent(driver, 120, ele1, " Download icon" + Verifysp[1]);
			clicknosleep(driver, ele1, " Download icon " + Verifysp[1]);
			// download in main method.
			downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
		}
		// TC049
		public void CSASortFilter(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
				String Verify, String Report, String Verify4, String reportingPeriod, String actualViews, String InputactualViews,String actualViews4) throws InterruptedException, IOException { 
			String[]Verifysp = Verify.split("\\,");
			String[]Verify2sp = Verify2.split("\\,");
			String[] Verify3sp = Verify3.split("\\,");
			String[] Reportsp = Report.split("\\,");
			String[] actualViewsp = actualViews.split("\\,");
			String[] actualViews4p = actualViews4.split("\\,");
			
			List<String> views2 = new ArrayList<>();
			// TC038
			if(Verifysp[1].equals("CSA VIEW"))
			{
			SearchPortolioID(driver,viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
			}
			if(Verifysp[1].equals("Netting Set View"))
			{
			CSAClone(driver,viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,InputactualViews,actualViews);
			
			}
			//Sortfliter
			List<String> views = new ArrayList<>();
			WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verifysp[1]+"']//following::span[text()='"+Reportsp[1]+"']"));
			String pathnum = extractxpathid.getAttribute("id");
			String[] xpathid = pathnum.split("-");
			System.out.println(xpathid[0]);
			System.out.println(xpathid[1]);
			System.out.println(xpathid[2]);

			String xpathdivtable = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div";
			By filter = By.xpath(xpathdivtable);
			WebElement filterscenarioName = driver.findElement(filter);
			String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
			List<WebElement> options2 = driver.findElements(filter);
			for (WebElement ele : options2) {
				views.add(ele.getAttribute(("textContent")));
			}
			System.out.println(views);
			System.out.println(views.get(1));

			String sortclick = "//label[text()='"+Verifysp[1]+"']//following::span[contains(@class,'filterswitch')]";
			By sort = By.xpath(sortclick);
			click(driver, sort,"by drilling down to " + Verifysp[1]+ " and click on Sort/Filters");
			Thread.sleep(2000);	
			String filterinput = null;
			if(Verifysp[1].equals("CSA VIEW"))
			{
			filterinput ="(//span[text()='"+Reportsp[1]+"']//following::input[@class='operatorValueFieldCls x-form-text x-form-text-default  '])[3]";
			}
			if(Verifysp[1].equals("Netting Set View"))
			{
			filterinput ="(//span[text()='"+Reportsp[1]+"']//following::input[@class='operatorValueFieldCls x-form-text x-form-text-default  '])[4]";
			}
			WebPageElements filterinput_dll = new WebPageElements("Sort/filiter", "xpath", filterinput);
			setTextenter(driver, filterinput_dll,views.get(1));
			Thread.sleep(2000);
			waitForLoad(driver, 100);
		//	String SearchInputtable2 = "(//label[text()='"+Verifysp[1]+"']//following::tr)[2]";
		//	WebPageElements searchinputtable2  = new WebPageElements(" Search Input table 2nd row", "xpath",SearchInputtable2);
		//	waitForElementInvisibilty(driver, 120, searchinputtable2);
			//String textLabel = driver.findElement(By.xpath(SORTGETTEXT + "[" + Verify2 + "]")).getText();
			//System.out.println(textLabel);
			
			List<String> textlabel = new ArrayList<>();
			String xpathdivtable2 = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div";
			By filter2 = By.xpath(xpathdivtable2);
			WebElement filterscenarioName2 = driver.findElement(filter2);
			String filterscenarioNamea2 = filterscenarioName2.getAttribute("textContent");
			List<WebElement> options3 = driver.findElements(filter2);
			for (WebElement ele3 : options3) {
				textlabel.add(ele3.getAttribute(("textContent")));
			}
			System.out.println(textlabel.toString().trim());
			System.out.println(views.get(1));
				if (textlabel.toString().contains(views.get(1))) {
				Reporter.log("Successfully Sort Value");
				Add_Log.info("Successfully Sort Value");
			} else {
				Reporter.log("Unable to Sort value");
				Add_Log.info("Unable to Sort value");
				TestResultStatus.mpaskeysnew.put(methodName(), "Unable to Sort value");
				Assert.fail();
			}
			String ExposureSUMMARYFIELD = "(//label[text()='"+Verifysp[1]+"']//following::div[@class='x-grid-header-ct x-docked x-grid-header-ct-default x-docked-top x-grid-header-ct-docked-top x-grid-header-ct-default-docked-top x-box-layout-ct'])[1]//child::div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']";
			WebPageElements exposuresummaryfield = new WebPageElements("Exposure Summary filter", "xpath",
					ExposureSUMMARYFIELD);
			
			waitForElementPresent(driver, 30, exposuresummaryfield);
			try {
			int size1 = driver.findElements(By.xpath(ExposureSUMMARYFIELD)).size();
			for (int i = 1; i <= size1; i++) {
				views2.add(getatt(driver, By.xpath("(" + ExposureSUMMARYFIELD + ")[" + i + "]"), Verifysp[1]+ " table Feild Content #" + i));	
			}
			System.out.println(views2);
		
		System.out.println(actualViews4p.length);
		for (int i1 = 0; i1 < actualViews4p.length; i1++) {

			if (views2.get(i1).equals(actualViews4p[i1].trim().toString()))
			{
				Add_Log.info("Successfully displayed " + views2.get(i1) + " under " + Verify2sp[1] + " all tabs ");
				Reporter.log("Successfully displayed " + views2.get(i1) + " under " + Verify2sp[1] + " all tabs  ");
			} else {
				Add_Log.info("***Unable to display "+ actualViews4p[i1].trim().toString() + " under " + Verify2sp[1]+ " all tabs ");
				Reporter.log("***Unable to display "+ actualViews4p[i1].trim().toString() + " under " + Verify2sp[1] + " all tabs ");
				TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display "+ actualViews4p[i1].trim().toString() + " under " + Verify2 + " all tabs ");
			//	Assert.fail();
			}	
		}
			}
			catch (Exception e) {}
		}
		// Result download
		public void Resultdownload(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
				String Verify, String Report, String Verify4, String reportingPeriod, String Fileformat,String FileNameac,String actualViews) throws InterruptedException, IOException {
			String[] Verifysp = Verify.split("\\,");
			String[] Verify2sp = Verify2.split("\\,");
			String[] Reportsp = Report.split("\\,");

				CapitalWhtif(driver, viewName, ReportName, Verify);
				// click on Add button for Portfolio
				WIAAddbutton(driver, viewName, Verify, ReportName);
				// Header for SCENARIO
				WIAHeadername(driver, Verify2sp[0]);
				// Add name and Description for portfolio
				WIAaddportfoliopop(driver, Verify3, Verify,viewName);
				// displayed Created scenario Name
				WIAscenarioverfiy(driver, viewName, Verify3, Verify, Reportsp[0]);
				// click Scenario Search Facility
				WIASearchFacility(driver, viewName, Verifysp[0], ReportName);
				// Header for Search
				WIAHeadername(driver, Verify2sp[1]);
				// if error
				Snackbar(driver);
				Thread.sleep(2000);
				waitForLoad(driver, 900);
				// COB select
				cobselect(driver, reportingPeriod);
				// Enter Portfolio and Search / and add
				EnterPortfolioandSearch(driver, Verify2, Verify4);
				// Verfiy select Portfolio Trade and CSA view in main page
				WIATradeCSAverfiy(driver);
				Thread.sleep(2000);
				waitForLoad(driver, 900);
				// Click on Check box for TradeVIew and CSA view
				TradeCSAViewradiobutton(driver);
				Thread.sleep(2000);
				waitForLoad(driver, 900);
				// Click on Calculator for Portfolio
				Portfoliocalculator(driver);
				Thread.sleep(2000);
				waitForLoad(driver, 900);
				// Submit Pop click on get message
				Sumbitpopok(driver);
				// Check calculator Status until
				Checkcalculatorstatus(driver);
				// Check value under Protoflio result
				if(actualViews.equals("PortfolioResult"))
				{
				VerfiyProtoflioresult(driver);
				By ele1 = By.xpath("(//label[text()='"+Verifysp[0]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-excel '])[3]");
				waitForElementPresent(driver, 120, ele1, " Download icon" + Verifysp[0]  );
				clicknosleep(driver, ele1,  " Download icon " + Verifysp[0]);	
				}
				if(actualViews.equals("AssetClassResult"))
				{
				VerfiyAssetresult(driver);
				By ele1 = By.xpath("(//label[text()='"+Verifysp[0]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-excel '])[4]");
				waitForElementPresent(driver, 120, ele1, " Download icon" + Verifysp[0]  );
				clicknosleep(driver, ele1,  " Download icon " + Verifysp[0]);	
				}
			downloadfilecommonreg(driver,ReportName, Verify,Fileformat,FileNameac);
		}
		
		public void validate_column_code(WebDriver driver, String InputactualViews, String expected_value) {
			//(//*[text()='Trade View']//following::table)[+row_num+]//td[column_num]
			String[] expectedCode = expected_value.split(",");
			try {
				int columNumber = getColumnNumber(driver, InputactualViews,"Trade View");
				System.out.println(columNumber);
				waitForLoad(driver,300);
				Thread.sleep(6000);
				String TOTAL_PAGEs = "//*[text()='Trade View']/parent::label/parent::div//div[contains(@id,'corepagingtoolbar')]//div[contains(@class,'x-toolbar-text')][2]";
				WebPageElements w_total_pages = new WebPageElements("Total Pages in the dashboard", "Xpath", TOTAL_PAGEs);
				String total_pages = getText(driver, w_total_pages);
				String pages_in_total[] = total_pages.split(" ");
				String pages=pages_in_total[1].replace(",","");
				int totalPages = Integer.parseInt(pages);
				Thread.sleep(3000);
				for (int pageNum = 1; pageNum <= totalPages; pageNum++) {
					int totalRecords = driver.findElements(By.xpath("//*[text()='Trade View']/parent::label/parent::div//table")).size();
					for (int rowNum = 0; rowNum <= totalRecords - 1; rowNum++) {
						String element = "(//*[text()='Trade View']/parent::label/parent::div//table)[@data-recordindex='" + rowNum + "']//td[" + columNumber + "]//div";
						WebPageElements w_element = new WebPageElements("element", "Xpath", element);
						WebElement ele = getWebElement(driver, w_element);
						String text = ele.getAttribute("innerHTML");
						String actualText=text.replace(",","");

						//=getAttribute1(driver,element,"innerHTML");
						boolean match = false;
						loop:
						for (int i = 0; i < expectedCode.length; i++) {
							if (actualText.contains(expectedCode[i])) {
								match = true;
								break loop;
							}
						}
						Assert.assertTrue(match, "Table value not matching with actual text:" + actualText);
					}
					if (pageNum < totalPages) {
						String NEXT_PAGE = "//a[contains(@class,'btn-next')]";
						WebPageElements w_next_page = new WebPageElements("Next Page Icon", "Xpath", NEXT_PAGE);
						click(driver, w_next_page);
						Thread.sleep(2000);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				TestResultStatus.mpaskeysnew.put(methodName(), "Failed to validate column data");
				Assert.fail("Failed to validate column data");

			}


		}

	    public void validate_column_code2ge(WebDriver driver, String InputactualViews, String expected_value) {
	        //(//*[text()='Trade View']//following::table)[+row_num+]//td[column_num]
	        String[] expectedCode = expected_value.split(",");
	        try {
	            int totalRecords=getTotal_TradeRecords(driver);

	            String Trade_ShowMore_Input="//label[text()='Show']/parent::div//input[@role='spinbutton']";
	            WebPageElements w_Trade_ShowMore_Input=new WebPageElements("Show More","Xpath",Trade_ShowMore_Input);
	            setTextenter(driver,w_Trade_ShowMore_Input,""+totalRecords);
	            waitForLoad(driver,300);

	            int columNumber = getColumnNumber(driver, InputactualViews,"Trade View");
	            System.out.println(columNumber);
	            waitForLoad(driver,300);
	            Thread.sleep(6000);
	            /*String TOTAL_PAGEs = "//*[text()='Trade View']/parent::label/parent::div//div[contains(@id,'corepagingtoolbar')]//div[contains(@class,'x-toolbar-text')][2]";
	            WebPageElements w_total_pages = new WebPageElements("Total Pages in the dashboard", "Xpath", TOTAL_PAGEs);
	            String total_pages = getText(driver, w_total_pages);
	            String pages_in_total[] = total_pages.split(" ");
	            String pages=pages_in_total[1].replace(",","");
	            int totalPages = Integer.parseInt(pages);
	*/
	                for (int rowNum = 0; rowNum <= totalRecords - 1; rowNum++) {
	                    String element = "(//*[text()='Trade View']/parent::label/parent::div//table)[@data-recordindex='" + rowNum + "']//td[" + columNumber + "]//div";
	                    WebPageElements w_element = new WebPageElements("element", "Xpath", element);
	                    WebElement ele = getWebElement(driver, w_element);
	                    String text = ele.getAttribute("innerHTML");
	                    String actualText=text.replace(",","");

	                    //=getAttribute1(driver,element,"innerHTML");
	                    boolean match = false;
	                    loop:
	                    for (int i = 0; i < expectedCode.length; i++) {
	                        if (actualText.equals(expectedCode[i])) {
	                            match = true;
	                            break loop;
	                        }
	                    }
	                    Assert.assertTrue(match, "Table value not matching with actual text:" + actualText);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            TestResultStatus.mpaskeysnew.put(methodName(), "Failed to validate column data");
	            Assert.fail("Failed to validate column data");

	        }


	    }


	    public int getTotal_TradeRecords(WebDriver driver) {
			int total_records_count = 0;
			try {
				String Trade_ShowMore="//*[text()='Trade View']/parent::label/parent::div//a[@data-qtip='Show More']";
				WebPageElements w_Trade_ShowMore=new WebPageElements("Show More","Xpath",Trade_ShowMore);
				String TOTAL_REORDS_DETAILS = "//div//label[contains(text(),'Displaying ')]";
				WebPageElements w_total_records_details = new WebPageElements("Element to find total number of records", "Xpath", TOTAL_REORDS_DETAILS);
				click(driver, w_Trade_ShowMore);
				WebElement ele = getWebElement(driver, w_total_records_details);
				String total_records = ele.getAttribute("innerHTML");
				String[] show_more_data = total_records.split(" ");
				 total_records_count = Integer.parseInt(show_more_data[5]);
			} catch (Exception e) {
				e.printStackTrace();
				TestResultStatus.mpaskeysnew.put(methodName(), "Failed to get total records count");
				Assert.fail("Failed to get total records count");

			}
			return total_records_count;
		}

		public void getTotalRows(WebDriver driver, String section) {
			if (section.equals("Trade View")) {

			}
		}

		public int getColumnNumber(WebDriver driver, String columnName,String tableName) {
			int i = -1;
			boolean found = true;
			String element = null;
			if (columnName == null) {
				Assert.fail("Please enter a valid column Name");
			}
			List<WebElement> column_elements;
			WebElement col_element = null;
			element = "(//*[text()='"+tableName+"']//following::div[contains(@class,'x-grid-header-ct-docked-top')])//div//span[@class='x-column-header-text']";
			WebPageElements ele = new WebPageElements("Column name list", "Xpath", element);
			column_elements = driver.findElements(By.xpath(ele.getValue()));
			for (WebElement list : column_elements) {
				i++;
				String column = list.getAttribute("innerHTML");
				if (column.equalsIgnoreCase(columnName)) {
					found = false;
					break;
				}

			}
			if (found) {
				Assert.fail("Column with given name is not available. Please check");
				TestResultStatus.mpaskeysnew.put(methodName(), "Column with given name is not available. Please check");
			}
			return i;
		}

		public void CreateHTtrade(WebDriver driver) {
			try {

				TradeCSAViewradiobutton(driver);
				Thread.sleep(2000);
				waitForLoad(driver, 900);
				// Click on Calculator for Portfolio
				Portfoliocalculator(driver);
				waitForLoad(driver, 900);
				Thread.sleep(2000);
				Sumbitpopok(driver);
				waitForLoad(driver, 900);
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void VerifyPortfolioResults(WebDriver driver) {
			try {
				int result = 0;
				By portfolioTab = By.xpath("//span//span[text()='Portfolio Results']");
				click(driver, portfolioTab, "Portfolio tab");
				result = driver.findElements(By.xpath("//*[text()='Portfolio Results']/parent::label/parent::div//table")).size();
				if (result >0) {
					Add_Log.info(result + " line(s) are displayed in Portfolio table");
				} else {
					TestResultStatus.mpaskeysnew.put(methodName(), "No data is displayed in Portfolio results table");
					Assert.fail("No data is displayed in Portfolio Results table");
				}
			} catch (Exception e) {
				e.printStackTrace();
				TestResultStatus.mpaskeysnew.put(methodName(), "Failed to verify Portfolio results");

			}
		}

	    public void Create_HT_Trade(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
	                                String Verify, String Report, String Verify4, String reportingPeriod, String Inputtext1, String Inputtext2, String Inputtext3, String actualViews, String actualViews2, String actualViews3, String InputactualViews) throws InterruptedException {
	        String[] Verifysp = Verify.split("\\,");
	        String[] Verify2sp = Verify2.split("\\,");
	        String[] Verify3sp = Verify3.split("\\,");
	        String[] Reportsp = Report.split("\\,");
	        // for other test cases
	        CreatePortolio(driver, viewName, ReportName, Verify2, Verify3, Verify, Report, Verify4, reportingPeriod);
	        //Click on Add-Create Trade icon
	        WIAAddbutton(driver, viewName, Verifysp[1], ReportName);

	        // Header for Create
	        WIAHeadername(driver, Verify2sp[1]);

	        // Create trade inside
	        CreateTrade_Inside(driver, viewName, ReportName, Verify2, Verify4, actualViews, actualViews3, InputactualViews, Inputtext1, Inputtext2, Inputtext3,"24552223");
	        //Verify Create Trade on Landing Page
	        Tradeverfiy(driver);
	    }


	    public void VerifyRWAProfileResults(WebDriver driver) {
			try {
				int result = 0;
				By portfolioTab = By.xpath("//span//span[text()='RWA Profile Results']");
				click(driver, portfolioTab, "RWA Profile Results tab");
				Thread.sleep(3000);
				result = driver.findElements(By.xpath("//*[text()='RWA Profile Results']/parent::label/parent::div//table")).size();
				if (result >0) {
					Add_Log.info(result + " line(s) are displayed in RWA Profile table");
				} else {
					TestResultStatus.mpaskeysnew.put(methodName(), "No data is displayed in RWA Profile table");
					Assert.fail("No data is displayed in RWA Profile table");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				TestResultStatus.mpaskeysnew.put(methodName(), "No lines are displayed in Trade EAD table");
				Assert.fail("No lines are displayed in Trade EAD table");

			}
		}

		public int VerifyTradeEADResults(WebDriver driver) {
			int result=0;
	    	try
			{
			By portfolioTab = By.xpath("//span//span[text()='Trade EAD Results']");
			click(driver, portfolioTab, "Asset Class Results tab");
			 result = driver.findElements(By.xpath("//*[text()='Trade EAD Results']/parent::label/parent::div//table")).size();
				if (result >0) {
					Add_Log.info(result + " line(s) are displayed in Trade EAD table");
				} else {
					TestResultStatus.mpaskeysnew.put(methodName(), "No are displayed in Trade EAD table");
					Assert.fail("No are displayed in Trade EAD table");
				}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			TestResultStatus.mpaskeysnew.put(methodName(), "Failed to verify Trade EAD result table");
			Assert.fail("Failed to verify Trade EAD result table");

		}
			return result;
		}

		public void TradeCountValidation(WebDriver driver,String Type)
		{
			try {
				int count = getTotal_TradeRecords(driver);

				if (Type.equals("Small"))
					Assert.assertTrue(count<=3, "More or less than 3 rows available in Trade Results. Actual count of rows: " + count);
				else if(Type.equals("Medium"))
					Assert.assertTrue(count>3&&count<=100, "More or less than 3 rows available in Trade Results. Actual count of rows: " + count);
				else if(Type.equals("Large"))
					Assert.assertTrue(count>100&&count<=1000, "More or less than 3 rows available in Trade Results. Actual count of rows: " + count);
				else
				{
					TestResultStatus.mpaskeysnew.put(methodName(), Type+" :provided type of validation is not valid");
					Assert.fail(Type+" :provided type of validation is not valid");
				}

			}
			catch (Exception e)
			{
				e.printStackTrace();
				TestResultStatus.mpaskeysnew.put(methodName(), "Failed to validate trace count. Please check");
				Assert.fail("Failed to validate trace count. Please check");

			}
		}

		//tc 57
	    public void WIaddverifyTrade(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
	                                String Verify, String Report, String Verify4, String reportingPeriod) throws InterruptedException {
	        String[] Verifysp = Verify.split("\\,");
	        String[] Verify2sp = Verify2.split("\\,");
	        String[] Reportsp = Report.split("\\,");

	        CapitalWhtif(driver, viewName, ReportName, Verify);
	        // Hack to fix ecore problem inside CapitalWhtif.
	        // click on Add button for Portfolio
	        WIAAddbutton(driver, viewName, Verify, ReportName);
	        // Header for SCENARIO
	        WIAHeadername(driver, Verify2sp[0]);
	        // Add name and Description for portfolio
	        WIAaddportfoliopop(driver, Verify3, Verify, viewName);
	        // displayed Created scenario Name
	        WIAscenarioverfiy(driver, viewName, Verify3, Verify, Reportsp[0]);
	        // click Scenario Search Facility
	        WIASearchFacility(driver, viewName, Verifysp[0], ReportName);
	        // Header for Search
	        WIAHeadername(driver, Verify2sp[1]);
	        // if error
	        Snackbar(driver);
	        Thread.sleep(2000);
	        waitForLoad(driver, 900);
	        // COB select
	        cobselect(driver, reportingPeriod);
	        // Enter Portfolio and Search / and add
	        EnterPortfolioandSearch(driver, Verify2, Verify4);
	        // Verfiy select Portfolio Trade and CSA view in main page
	        WIATradeCSAverfiy(driver);
	        Thread.sleep(2000);
	        waitForLoad(driver, 900);
	        // Click on Check box for TradeVIew and CSA view
	        TradeCSAViewradiobutton(driver);
	        Thread.sleep(2000);
	        waitForLoad(driver, 900);
	        //Export Trade as excel format
	        String fileName=ExportTrade(driver);
	        String filePath=downloadFilepath+"\\"+fileName;
	        // Click on Calculator for Portfolio
	        Portfoliocalculator(driver);
	        Thread.sleep(2000);
	        waitForLoad(driver, 900);
	        // Submit Pop click on get message
	        Sumbitpopok(driver);
	        // Check calculator Status until
	        Checkcalculatorstatus(driver);
	        // Check value under Protoflio result and store them
	        HashMap<Integer,String> PortfolioValue=VerfiyProtoflioresultAndStoreValues(driver);
	        //Modify Current Market value in  exported excel
	        ModifyExcel(driver,filePath,"Current Market to Market","2000");
	        //Upload Modified excel
	        UploadExcel(driver,filePath);
	        //Verify Modified value
	        validate_column_code(driver,"CMTM","2000");
	        // displayed Profilio Result JTD RWA value
	        JTDRWAvalue(driver, Verify2, Report);
			Traderadiobutton(driver);
	        Portfoliocalculator(driver);
	        Thread.sleep(2000);
	        waitForLoad(driver, 900);
	        // Submit Pop click on get message
	        Sumbitpopok(driver);
	        // Check calculator Status until
	        Checkcalculatorstatus(driver);
	        //get updated portfolio value
	        HashMap<Integer,String> UpdatedPrtfolioValue=VerfiyProtoflioresultAndStoreValues(driver);
	        //validate before and values in JTD RWA column
	       if(PortfolioValue.get(4).equals(UpdatedPrtfolioValue.get(4)))
	        {
	            Add_Log.error("Before and After values in JTD RWA column are equal: "+PortfolioValue.get(4)+" and: "+UpdatedPrtfolioValue.get(4));
	            Reporter.log("Before and After values in JTD RWA column are equal: "+PortfolioValue.get(4)+" and: "+UpdatedPrtfolioValue.get(4));
	            TestResultStatus.mpaskeysnew.put(methodName(), "Before and After values in JTD RWA column are equal: "+PortfolioValue.get(4)+" and: "+UpdatedPrtfolioValue.get(4));
	            Assert.fail();

	        }

	    }

	    public String ExportTrade(WebDriver driver)
	    {
	        String downloadFilePath=null;
	       try {
	           waitForLoad(driver,30);
	           String Export_Trade="//*[text()='Trade View']/parent::label/parent::div//span[contains(@class,'icon-excel')]";
	           WebPageElements w_export_trade=new WebPageElements("Export Trade as excel","Xpath",Export_Trade);
	           click(driver,w_export_trade);
	           Thread.sleep(5000);
	            downloadFilePath=isFileDownloaded(downloadFilepath,"Trade_View",".xlsx");
	           //Trade_View_20201116005327
	       }
	       catch (Exception e)
	       {
			   TestResultStatus.mpaskeysnew.put(methodName(), "Failed to export Trade");
			   Assert.fail("Failed to export Trade");

	       }
	       return downloadFilePath;
	    }

	    public HashMap<Integer,String> VerfiyProtoflioresultAndStoreValues(WebDriver driver) throws InterruptedException {
	        HashMap<Integer,String> portfolio=new HashMap<>();
	        By Portfolioresult = By.xpath("(//*[text()='Portfolio Results']//following::table)[1]//td");
	        WebElement Portfolioresultbox = driver.findElement(Portfolioresult);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Portfolioresultbox);
	        Thread.sleep(500);
	        if (Portfolioresultbox.isDisplayed()) {
	            Add_Log.info("Successfully displayed Portfolio Results table content");
	            Reporter.log("Successfully displayed Portfolio Results table content");
	            int count=driver.findElements(Portfolioresult).size();

	            for (int i=1;i<=count;i++)
	            {
	                By Element=By.xpath("//*[text()='Portfolio Results']//following::div//table[1]//td["+i+"]//div");
	                WebElement portfolioElement = driver.findElement(Element);
	                String value=portfolioElement.getAttribute("innerHTML");
	                portfolio.put(i,value);
	            }
	        } else {
	            Add_Log.info("Unable to displayed Portfolio Results table content");
	            Reporter.log("Unable to displayed Portfolio Results table content");
	            TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Portfolio Results table content");
	            Assert.fail();
	        }
	        return portfolio;
	    }

	    public void ModifyExcel(WebDriver driver,String filePath,String colName,String NewData)
	    {
	        try {
	            FileInputStream ipstr = new FileInputStream(filePath);
	            XSSFWorkbook wb = new XSSFWorkbook(ipstr);
	            ipstr.close();
	            XSSFSheet ws = wb.getSheet("Trade View");
	            XSSFRow HeaderRow = ws.getRow(0);
	            XSSFRow row;
	            XSSFCell cell;
	            int numRows = ws.getLastRowNum();
	            int colNum = HeaderRow.getLastCellNum();
	            int colNumber = 0;
	            for(int i=0; i<colNum; i++){
	                if(HeaderRow.getCell(i).getStringCellValue().equals(colName.trim())){
	                    colNumber=i;
	                }
	            }
	            XSSFRow DataRow = ws.getRow(1);
	            row = ws.createRow(++numRows);
	            DataRow.getCell(colNumber).setCellValue(NewData);
	            //cell.setCellValue(data);
	            FileOutputStream opstr = new FileOutputStream(filePath);
	            wb.write(opstr);
	            opstr.close();

	        }
	        catch (Exception e)
	        {
	        	e.printStackTrace();
				TestResultStatus.mpaskeysnew.put(methodName(), "Failed to modify excel file");
				Assert.fail("Failed to modify excel file");


			}
	    }

	    public void UploadExcel(WebDriver driver,String filePath)
	    {
	        try {
	            String UploadTrade="//*[text()='Trade View']/parent::label/parent::div//a[contains(@data-qtip,'Import Existing GFCID/Portfolio')]";
	            WebPageElements w_upload_trade=new WebPageElements("Upload Trade icon","Xpath",UploadTrade);
	            click(driver,w_upload_trade);
	            waitForLoad(driver,60);
	            Thread.sleep(3000);
	            By SendFile=By.xpath("//*[text()='Trade View']//following::div//input[contains(@name,'fileHolder')]");
	            driver.findElement(SendFile).sendKeys(filePath);
	            Thread.sleep(2000);
	            String Upload_Button="//*[text()='Trade View']//following::div//span[text()='Upload']";
	            WebPageElements w_upload_button=new WebPageElements("Upload Button","Xpath",Upload_Button);
	            click(driver,w_upload_button);
	            waitForLoad(driver,300);
	            Thread.sleep(3000);
	        }
	        catch (Exception e)
	        {
	        	e.printStackTrace();
				TestResultStatus.mpaskeysnew.put(methodName(), "Failed to upload excel file");
				Assert.fail("Failed to upload excel file");
			}
	    }

	    public String isFileDownloaded(String downloadPath, String Name, String file_extension) {
	        boolean flag = false;
	        String fileName=null;
	        String downloaded_file=null;
	        try {
	            File dir = new File(downloadPath);
	            File[] dir_contents = dir.listFiles();
	            Arrays.sort(dir_contents, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
	            File latestModifiedFile = dir_contents[0];
	            Add_Log.info("Latest Modified File : " + latestModifiedFile.getName());
	            Reporter.log("Latest Modified File : " + latestModifiedFile.getName());

	            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	            String date_format = dateFormat.format(new Date());


	            fileName = Name+"_"+date_format;
	            for1:
	            for (int i = 0; i < dir_contents.length; i++) {
	                Add_Log.info("Comapring directory file : " + dir_contents[i].getName() + " with desired given file : "
	                        + fileName);
	                Reporter.log("Comparing directory file : " + dir_contents[i].getName() + " with desired given file : "
	                        + fileName);
	                if(dir_contents[i].getName().contains(fileName) && latestModifiedFile.getName().contains(fileName)) {
	                    Assert.assertTrue(dir_contents[i].getName().contains(fileName) && latestModifiedFile.getName().contains(fileName), "File Name is not matching. Please check");
	                    Assert.assertTrue(dir_contents[i].getName().contains(file_extension) && latestModifiedFile.getName().contains(file_extension), "File extension->" + file_extension + " is not matching");
	                    downloaded_file = dir_contents[i].getName();
	                    flag=true;
	                    break for1;
	                }
	            }
	            Assert.assertTrue(flag,"Failed to export Trade. Please check");
	        } catch (Exception e) {
	            Add_Log.info("Failed to download file");
	            Reporter.log("Failed to download file :");
	            //testsuitebase.SuiteBase.logger.log(LogStatus.FAIL, "Failed to download file :" + fileName);
	        }
	        return downloaded_file;
	    }

	    public void UploadMTMValues(WebDriver driver,String ProfileType,String frequency,String fileName)
	    {
	    	try
			{
				String filePath=System.getProperty("user.dir")+"\\src\\main\\resources\\uploadfiles\\"+fileName+".xlsx";
				String UploadMTM_Button="//*[text()='Trade View']/parent::label/parent::div//a[contains(@data-qtip,'Import MTM/Forward MTM')]";
				WebPageElements w_upload_button=new WebPageElements("Import MTM icon","Xpath",UploadMTM_Button);
				click(driver,w_upload_button);
				waitForLoad(driver,60);
				Thread.sleep(3000);
				By MTMScreen=By.xpath("//*[text()='UPLOAD MTM VALUES']");
				WebElement ValidateMTMScreen=driver.findElement(MTMScreen);
				if(ValidateMTMScreen.isDisplayed())
				{
					Add_Log.info("Upload MTM values screen is displayed successfully");
				}
				else {
					Add_Log.error("Upload MTM values screen is not displayed. Please check");
					TestResultStatus.mpaskeysnew.put(methodName(), "Upload MTM values screen is not displayed. Please check");
					Assert.fail("Upload MTM values screen is not displayed. Please check");
				}
				String select_profile="//label[text()='"+ProfileType+"']";
				WebPageElements w_select_profile=new WebPageElements("Select Profile","Xpath",select_profile);
				click(driver,w_select_profile);
				String Frequency_DD="//div[contains(@data-uipath,'Upload MTM Values/saccrRwaPopupElement/frequency')]//div[contains(@id,'trigger-picker')]";
				if(ProfileType.equals("MTM Profile"))
				{
					Thread.sleep(3000);
					String Frequency="//li[text()='"+frequency+"']";
					WebPageElements w_frequency_dd=new WebPageElements("Frequency Dropdown","Xpath",Frequency_DD);
					WebPageElements w_frequency=new WebPageElements("Select Frequency","Xpath",Frequency);
					click(driver,w_frequency_dd);
					Thread.sleep(1000);
					click(driver,w_frequency);
				}
				/*else if(ProfileType.equals("CMTM"))
				{
					Thread.sleep(3000);
					if(driver.findElement(By.xpath(Frequency_DD)).getAttribute("disabled").equals(""))
					{
						Add_Log.error("Frequency is enabled in CMTM profile");
						TestResultStatus.mpaskeysnew.put(methodName(), "Frequency is enabled in CMTM profile");
						Assert.fail("Frequency is enabled in CMTM profile");
					}
					else
					{
						Add_Log.info("Frequency dropdown is in disabled state in CMTM profile");

					}

				}*/
				Thread.sleep(2000);
				By SelectFile=By.xpath("//input[contains(@name,'Upload MTM Values/saccrRwaPopupElement/fileHolder')]");
				driver.findElement(SelectFile).sendKeys(filePath);
				Thread.sleep(2000);
				/*String Preview="//span[text()='Preview']";
				WebPageElements w_preview=new WebPageElements("Preview button","Xpath",Preview);
				click(driver,w_preview);*/
				///validate table code needs to be added
		        Thread.sleep(8000);
		        waitForLoad(driver,30);
	            validateMTM(driver,ProfileType);
				String Upload="//span[text()='Upload']";
				WebPageElements w_upload=new WebPageElements("Upload button","Xpath",Upload);
				click(driver,w_upload);
				waitForLoad(driver,300);
				Thread.sleep(5000);
	            resultPopUp(driver,ProfileType);
	            Thread.sleep(3000);
				if(ProfileType.equals("MTM Profile"))
					validateMTMTrade(driver);
	            else if(ProfileType.equals("CMTM"))
	                validateCMTMTrade(driver);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				TestResultStatus.mpaskeysnew.put(methodName(), "Failed to upload MTM values");
				Assert.fail();

			}
	    }

	    public void validateMTM(WebDriver driver,String profleType)
		{
			try {

	                Thread.sleep(3000);
	                if (driver.findElements(By.xpath("//div[contains(@data-uipath,'saccrMtmPreviewGrid')]//table")).size() > 0) {
	                    Add_Log.info("Preview is displayed successfully");
	                } else {
	                    TestResultStatus.mpaskeysnew.put(methodName(), "Preview table is not displayed");
	                    Assert.fail("Preview table is not displayed");
	                }
	            By element=By.xpath("//div[contains(@data-uipath,'saccrMtmPreviewGrid')]//table[1]//td[1]//div");
	            WebElement w_element=driver.findElement(element);
	            String DisplayedPRofile=w_element.getText();
	            if(profleType.equals("CMTM")) {
	                if(DisplayedPRofile.equals("CMTM"))
	                    Add_Log.info("CMTM profile is displayed successfully");
	                else
	                {
	                    TestResultStatus.mpaskeysnew.put(methodName(), "CMTM profile is not displayed");
	                    Assert.fail("CMTM profile is not displayed");
	                }
	            }
	            else if(profleType.equals("MTM Profile"))
	            {
	                if(DisplayedPRofile.equals("MTM Profile"))
	                Add_Log.info("MTM profile is displayed successfully");
	            else
	            {
	                TestResultStatus.mpaskeysnew.put(methodName(), "MTM profile is not displayed");
	                Assert.fail("MTM profile is not displayed");
	            }
	        }
			}
			catch (Exception e)
			{
				e.printStackTrace();
				TestResultStatus.mpaskeysnew.put(methodName(), "Failed to validate preview table");
				Assert.fail("Failed to validate preview table");
			}

		}

		public void validateMTMTrade(WebDriver driver) {
			try {

				TradeCSAViewradiobutton(driver);
				Thread.sleep(2000);
				waitForLoad(driver, 900);
				// Click on Calculator for Portfolio
				Portfoliocalculator(driver);
				waitForLoad(driver, 900);
				Thread.sleep(2000);
				By CaluculateRWA=By.xpath("//span[text()='Calculate RWA']");
				WebElement CaluculateRWAButton=driver.findElement(CaluculateRWA);
				if(CaluculateRWAButton.isEnabled())
				{
					Add_Log.info("Calculate RWA button is enabled");
				}
				else
				{
					TestResultStatus.mpaskeysnew.put(methodName(), "Calculate RWA button is disabled");
					Assert.fail("Calculate RWA button is disabled");
				}
				waitForLoad(driver, 900);
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
				TestResultStatus.mpaskeysnew.put(methodName(), "Failed to validate Calculate RWA button");
			}
		}

	    public void validateCMTMTrade(WebDriver driver) {
	        try {
	            int columnNumber=getColumnNumber(driver,"CMTM","Trade View");
	            String element = "(//*[text()='Trade View']/parent::label/parent::div)//table[@data-recordindex='0']//td["+columnNumber+"]//div";
	            WebPageElements w_element = new WebPageElements("element", "Xpath", element);
	            WebElement ele = getWebElement(driver, w_element);
	            String actualText = ele.getAttribute("innerHTML");

	            if(actualText.equals("0"))
	            {
	                TestResultStatus.mpaskeysnew.put(methodName(), "CMTM value is 0");
	                Assert.fail("CMTM value is 0");
	            }
	            else
	            {
	                Add_Log.info("CMTM value is not 0");
	            }
	            waitForLoad(driver, 900);
	            Thread.sleep(3000);
	        } catch (Exception e) {
	            e.printStackTrace();
	            TestResultStatus.mpaskeysnew.put(methodName(), "Failed to validate CMTM value");
	        }
	    }

	    public void CreateUnmarginedHTTrade(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
	                                        String Verify, String Report, String Verify4, String reportingPeriod, String Inputtext1, String Inputtext2, String Inputtext3, String actualViews, String actualViews2, String actualViews3, String InputactualViews,String Type) throws InterruptedException {
	        String[] Verifysp = Verify.split("\\,");
	        String[] Verify2sp = Verify2.split("\\,");
	        String[] Verify3sp = Verify3.split("\\,");
	        String[] Reportsp = Report.split("\\,");
	        // for other test cases
	        CreatePortolio(driver, viewName, ReportName, Verify2, Verify3, Verify, Report, Verify4, reportingPeriod);
	        //Click on Add-Create Trade icon
	        WIAAddbutton(driver, viewName, Verifysp[1], ReportName);

	        // Header for Create
	        WIAHeadername(driver, Verify2sp[1]);

	        // Create trade inside
	        CreateUnmarginedTradeInside(driver, viewName, ReportName, Verify2, Verify4, actualViews, actualViews3, InputactualViews, Inputtext1, Inputtext2, Inputtext3,Type);
	        //Verify Create Trade on Landing Page
	        Tradeverfiy(driver);
	    }

	    public void CreateUnmarginedTradeInside(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify4, String actualViews, String actualViews3, String InputactualViews, String Inputtext1, String Inputtext2, String Inputtext3,String Type)
	            throws InterruptedException {
	        String[] Verify4sp = Verify4.split("\\,");
	        String[] Verify2sp = Verify2.split("\\,");
	        String[] actualViewsp = actualViews.split("\\,");
	        String[] actualView3sp = actualViews3.split("\\,");
	        String[] Inputtext1sp = Inputtext1.split("\\,");
	        String[] Inputtext2sp = Inputtext2.split("\\,");
	        String[] Inputtext3sp = Inputtext3.split("\\,");

	        String[] InputactualViewsp = InputactualViews.split("\\,");
	        String Asofdate = "//b[contains(text(),'AS OF')]/parent::div/parent::div/parent::div/following-sibling::div[1]";
	        WebPageElements AsofDate = new WebPageElements("AS OF date", "xpath", Asofdate);
	        waitForElementPresent(driver, 90, AsofDate);
	        String AsOfDate = getText(driver, AsofDate);
	        System.out.println(AsOfDate);
	        By ele1 = By.xpath("(//div[text()='CREATE TRADE']//following::input)[1]");
	        waitForElementPresent(driver, 120, ele1, " Asset Class Code ");
	        click(driver, ele1, "by drilling down to " + ReportName + " - " + viewName + " Asset Class Code");
	        Thread.sleep(2000);
	        //	cobselect(driver, AsOfDate);
	        By margined = By.xpath("//label[text()='Un-Margined']");
	        waitForElementPresent(driver, 120, margined, "Un-Margined");
	        click(driver, margined, "Un-Margined");
	        String select=null;
	        if(Type.equals("No"))
	             select = "//label[text()='No']";
	        else if(Type.equals("Yes"))
	             select = "//label[text()='Yes']";
	        WebPageElements Select = new WebPageElements("Choose Yea or No for CMTM value", "xpath", select);
	        click(driver, Select);
	        Thread.sleep(3000);
	        waitForLoad(driver, 900);
	        if(Type.equals("Yes")) {
	            String CMTM_Input="//div[contains(@id,'citirisktextinput')]//input";
	            WebPageElements w_cMTM_Input=new WebPageElements("CMTM Input Box","Xpath",CMTM_Input);
	            setText(driver,w_cMTM_Input,"347856");
	            Thread.sleep(3000);
	        }
	        By AssetClass = By.xpath("//span[text()='2. Asset Class']");
	        waitForElementPresent(driver, 120, AssetClass, "Asset Class");
	        click(driver, AssetClass, "by drilling down to " + ReportName + " - " + viewName + "Drop down value for Asset Class Code ");
	        Thread.sleep(2000);
	        String assetClassCode = "//input[@name='assetClassCode']";
	        WebPageElements AssetClassCode = new WebPageElements("asset Class ", "xpath", assetClassCode);
	        click(driver, AssetClassCode);
	        String eqty = "//li[text()='EQTY']";
	        WebPageElements EQTY = new WebPageElements("eqty", "xpath", eqty);
	        click(driver, EQTY);
	        Thread.sleep(2000);
	        //String tradeCode = "//input[@name='assetClassCode']";
	        String tradeCode = "//input[@name='tradeType']/following::div[1]";
	        WebPageElements TradeCode = new WebPageElements("Trade code", "xpath", tradeCode);
	        click(driver, TradeCode);

	        String volatility = "//li[text()='Volatility']";
	        WebPageElements Volatility = new WebPageElements("Volatility", "xpath", volatility);
	        Thread.sleep(2000);
	        click(driver, Volatility);
	        waitForLoad(driver, 900);
	        Thread.sleep(2000);

	        String indicator = "//label[text()='" + Inputtext1sp[0] + "']/preceding-sibling::input";
	        WebPageElements Indicator = new WebPageElements("indicator Sell/Buy", "xpath", indicator);
	        click(driver, Indicator);
	        Thread.sleep(2000);

	        String StartDate = "//input[@name='startDate']";
	        WebPageElements startdate = new WebPageElements("StartDate", "xpath", StartDate);
	        click(driver, startdate);
	        Thread.sleep(2000);
	        setTextentern(driver, startdate, Inputtext2sp[0]);
	        Thread.sleep(2000);

	        String EndDate = "//input[@name='endDate']";
	        WebPageElements enddate = new WebPageElements("EndDate", "xpath", EndDate);
	        click(driver, enddate);
	        Thread.sleep(2000);
	        setTextentern(driver, enddate, Inputtext2sp[1]);
	        Thread.sleep(2000);

	        String selectCurrency = "//span[text()='Currency-1']/following::div[contains(@class,'combotrig ')]";
	        WebPageElements SelectCurrency = new WebPageElements("selectCurrency", "xpath", selectCurrency);
	        click(driver, SelectCurrency);
	        Thread.sleep(2000);
	        String Currency1 = "//li[text()='" + Inputtext2sp[2] + "']";
	        WebPageElements currency1 = new WebPageElements("Currency-1", "xpath", Currency1);
	        Thread.sleep(2000);
	        click(driver, currency1);
	        waitForLoad(driver, 900);
	        Thread.sleep(2000);

	        String Notational = "//input[@name='notional']";
	        WebPageElements notional = new WebPageElements("Notational", "xpath", Notational);
	        click(driver, notional);
	        Thread.sleep(2000);
	        setTextentern(driver, notional, Inputtext2sp[3]);
	        Thread.sleep(2000);
	        waitForLoad(driver, 900);

	        String RegID = "//input[@name='regID']";
	        WebPageElements regID = new WebPageElements("RegID", "xpath", RegID);
	        click(driver, regID);
	        Thread.sleep(2000);
	        setTextentern(driver, regID, Inputtext2sp[4]);
	        Thread.sleep(2000);
	        waitForLoad(driver, 900);

	/*
			String IsOption = "//span[text()='Is Option']";
			WebPageElements isoption = new WebPageElements("Is Option", "xpath", IsOption);
			waitForElementPresent(driver, 120, isoption);
			click(driver,isoption);
			waitForLoad(driver, 900);
			waitForLoad(driver, 900);
			Thread.sleep(3000);
			List<String> secondarylabellist = new ArrayList<>();
			List<WebElement> secondarylabel = driver.findElements(By.xpath("//div[text()='CREATE TRADE']//following::span[@class='x-tab-inner x-tab-inner-secondary-tabs-elements']"));
			for (WebElement ele : secondarylabel) {
				secondarylabellist.add(ele.getAttribute(("textContent")));
			}
			System.out.println(secondarylabellist +" "+ secondarylabellist.size());
			System.out.println(actualViewsp.length);
			for (int i1 = 0; i1 < actualViewsp.length; i1++) {

				if (secondarylabellist.get(i1).equals(actualViewsp[i1].trim().toString()))
				{
					Add_Log.info("Successfully displayed " + secondarylabellist.get(i1) + " under " + Verify2sp[1] + " all tabs ");
					Reporter.log("Successfully displayed " + secondarylabellist.get(i1) + " under " + Verify2sp[1] + " all tabs  ");
				} else {
					Add_Log.info("Unable to display "+ actualViewsp[i1].trim().toString() + " under " + Verify2sp[1]+ " all tabs ");
					Reporter.log("Unable to display "+ actualViewsp[i1].trim().toString() + " under " + Verify2sp[1] + " all tabs ");
					TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display "+ actualViewsp[i1].trim().toString() + " under " + Verify2 + " all tabs ");
					Assert.fail();
				}
			}
			Thread.sleep(2000);
			click(driver,isoption);
			waitForLoad(driver, 30);
			Thread.sleep(2000);
			List<String> views2 = new ArrayList<>();

			List<WebElement> options2 = driver.findElements(By.xpath("//span[text()='Common Details']//following::label[contains(@class,' x-form-item-label-top x-unselectable')] "));
			for (WebElement ele : options2) {
				views2.add(ele.getAttribute(("textContent")));
			}
			System.out.println(views2);
			for (int i2 = 0; i2 < InputactualViewsp.length; i2++) {

				if (views2.get(i2).equals(InputactualViewsp[i2].trim().toString())) {
					Add_Log.info("Successfully displayed " + views2.get(i2) + " under " + Verify2sp[1] + " all content");
					Reporter.log("Successfully displayed " + views2.get(i2) + " under " + Verify2sp[1] + " all content");
				} else {
					Add_Log.info("Unable to display "+ InputactualViewsp[i2].trim().toString() + " under " + Verify2sp[1] + " all content");
					Reporter.log("Unable to display "+ InputactualViewsp[i2].trim().toString() + " under " + Verify2sp[1] + " all content");
					TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display "+ InputactualViewsp[i2].trim().toString() + " under " + Verify2sp[1] + " all content");
					Assert.fail();
				}
			}
			String aggreationfilter = null;
			String inputtextsearch1 = null;
			WebPageElements inputtextfeildid = null;
			for (int i = 0; i < actualView3sp.length; i++) {

				if (actualView3sp[i].trim().toString().equals("Currency Type 1st")) {
					aggreationfilter = "(//div[text()='CREATE TRADE']//following::span[text()='"+actualView3sp[i].trim().toString()+"']//following::div[contains(@id,'trigger-picker')])[1]";
					By aggreationfilterpath = By.xpath(aggreationfilter);
					waitForElementPresent(driver, 120, aggreationfilterpath, actualView3sp[i].trim().toString() );
					click(driver, aggreationfilterpath, actualView3sp[i].trim().toString());
					Thread.sleep(1000);
					inputtextsearch1 ="(//div[text()='CREATE TRADE']//following::span[text()='"+actualView3sp[i].trim().toString()+"']//following::div[contains(@id,'trigger-picker')])[1]//following::li[text()='"+Inputtext3sp[i].trim().toString()+"'][1]";
					click(driver, inputtextsearch1, Inputtext3sp[i].trim().toString());
					Thread.sleep(2000);
					waitForLoad(driver, 900);
				}
				if (actualView3sp[i].trim().toString().equals("Currency Type 2nd")) {
					aggreationfilter = "(//div[text()='CREATE TRADE']//following::span[text()='"+actualView3sp[i].trim().toString()+"']//following::div[contains(@id,'trigger-picker')])[1]";
					By aggreationfilterpath = By.xpath(aggreationfilter);
					waitForElementPresent(driver, 120, aggreationfilterpath, actualView3sp[i].trim().toString() );
					click(driver, aggreationfilterpath, actualView3sp[i].trim().toString());
					Thread.sleep(1000);
					inputtextsearch1 ="(//div[text()='CREATE TRADE']//following::span[text()='"+actualView3sp[i].trim().toString()+"']//following::div[contains(@id,'trigger-picker')])[1]//following::li[text()='"+Inputtext3sp[i].trim().toString()+"'][2]";
					click(driver, inputtextsearch1, Inputtext3sp[i].trim().toString());
					Thread.sleep(2000);
					waitForLoad(driver, 900);
				}
				else {
					inputtextsearch1 = "(//div[text()='CREATE TRADE']//following::span[text()='"+actualView3sp[i].trim().toString()+"']//following::input)[1]";
					inputtextfeildid = new WebPageElements(actualView3sp[i].trim().toString(), "xpath",inputtextsearch1);
					clearText(driver, inputtextfeildid);
					Thread.sleep(1000);
					setTextenter(driver, inputtextfeildid, Inputtext3sp[i].trim().toString());
					Thread.sleep(1000);
				}
			} */
	        waitForLoad(driver, 120);
	        Thread.sleep(2000);
	        WebElement Savebutton = driver.findElement(By.xpath(Save_Btninside));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Savebutton);
	        waitForElementPresent(driver, 120, save_btninside);
	        click(driver, save_btninside);
	        Thread.sleep(4000);
	        waitForLoad(driver, 120);


	    }


	    public void resultPopUp(WebDriver driver,String ProfileType) throws InterruptedException {
	        try {
				By resultpopup=null;
	        	if(ProfileType.equals("MTM Profile"))
	        		resultpopup = By.xpath("//div[contains(text(),'Results Uploaded')]");
	        	else if(ProfileType.equals("CMTM"))
					 resultpopup = By.xpath("//div[contains(text(),'CMTM Submitted.')]");
					            Thread.sleep(2000);
	            WebElement w_resultpopup = driver.findElement(resultpopup);
	            String submitpopmessage = w_resultpopup.getText();
	            System.out.println(submitpopmessage);
	            Thread.sleep(4000);
	            if (w_resultpopup.isDisplayed()) {
	                Add_Log.info("Popup with message : " + submitpopmessage);
	                Reporter.log("Popup with message : " + submitpopmessage);
	            }
	            Thread.sleep(2000);
	            waitForLoad(driver, 900);
	            By closepop = By.xpath("//span[text()='OK']");
	            click(driver, closepop, " click ok on popup meeasge");
	            Thread.sleep(4000);
	            waitForLoad(driver, 900);
	        } catch (Exception e1) {
	            e1.printStackTrace();
	            By submitpop = By.xpath("//div[contains(text(),'error')]");
	            WebElement submitpopbox = driver.findElement(submitpop);
	            String submitpopmessage = submitpopbox.getText();
	            System.out.println(submitpopmessage);
	            if (submitpopbox.isDisplayed()) {
	                Add_Log.info("Popup with message : " + submitpopmessage);
	                Reporter.log("Popup with message : " + submitpopmessage);
	                ITestResult result = Reporter.getCurrentTestResult();
	                result.setAttribute("DontRetry", Boolean.TRUE.toString());
	                Assert.fail();
	            }
	        }
	    }

		public void CreateTrade_New(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
								String Verify, String Report, String Verify4, String reportingPeriod, String Inputtext1, String Inputtext2, String Inputtext3, String actualViews, String actualViews2, String actualViews3, String InputactualViews,String marginalID) throws InterruptedException {
			String[] Verifysp = Verify.split("\\,");
			String[] Verify2sp = Verify2.split("\\,");
			String[] Verify3sp = Verify3.split("\\,");
			String[] Reportsp = Report.split("\\,");
			// for other test cases
			CreatePortolio(driver, viewName, ReportName, Verify2, Verify3, Verify, Report, Verify4, reportingPeriod);
			//Click on Add-Create Trade icon
			WIAAddbutton(driver, viewName, Verifysp[1], ReportName);

			// Header for Create
			WIAHeadername(driver, Verify2sp[1]);

			// Create trade inside
			CreateTrade_Inside(driver, viewName, ReportName, Verify2, Verify4, actualViews, actualViews3, InputactualViews, Inputtext1, Inputtext2, Inputtext3,marginalID);
			//Verify Create Trade on Landing Page
			Tradeverfiy(driver);
		}

		public void create_twoHTTrade(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
	                                  String Verify, String Report, String Verify4, String reportingPeriod, String Inputtext1, String Inputtext2, String Inputtext3, String actualViews, String actualViews2, String actualViews3, String InputactualViews,String marginalID) throws InterruptedException {
	        String[] Verifysp = Verify.split("\\,");
	        String[] Verify2sp = Verify2.split("\\,");
	        String[] Verify3sp = Verify3.split("\\,");
	        String[] Reportsp = Report.split("\\,");
	        String[] marginalIDs=marginalID.split("\\,");
	        // for other test cases
	        CreatePortolio(driver, viewName, ReportName, Verify2, Verify3, Verify, Report, Verify4, reportingPeriod);
	        //Click on Add-Create Trade icon
	        WIAAddbutton(driver, viewName, Verifysp[1], ReportName);

	        // Header for Create
	        WIAHeadername(driver, Verify2sp[1]);

	        // Create trade inside
	        CreateTrade_Inside(driver, viewName, ReportName, Verify2, Verify4, actualViews, actualViews3, InputactualViews, Inputtext1, Inputtext2, Inputtext3,marginalIDs[0]);
	        //Verify Create Trade on Landing Page
	        Tradeverfiy(driver);
	        Thread.sleep(4000);
	       // WIAAddbutton(driver, viewName, Verifysp[1], ReportName);

	        // Header for Create
	      //  WIAHeadername(driver, Verify2sp[1]);
	       // CreateTrade_Inside(driver, viewName, ReportName, Verify2, Verify4, actualViews, actualViews3, InputactualViews, Inputtext1, Inputtext2, Inputtext3,marginalIDs[1]);
	    }

		public void CreateTrade_Inside(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify4, String actualViews, String actualViews3, String InputactualViews, String Inputtext1, String Inputtext2, String Inputtext3,String marginalID)
				throws InterruptedException {
			String[] Verify4sp = Verify4.split("\\,");
			String[] Verify2sp = Verify2.split("\\,");
			String[] actualViewsp = actualViews.split("\\,");
			String[] actualView3sp = actualViews3.split("\\,");
			String[] Inputtext1sp = Inputtext1.split("\\,");
			String[] Inputtext2sp = Inputtext2.split("\\,");
			String[] Inputtext3sp = Inputtext3.split("\\,");

			String[] InputactualViewsp = InputactualViews.split("\\,");
			String Asofdate = "//b[contains(text(),'AS OF')]/parent::div/parent::div/parent::div/following-sibling::div[1]";
			WebPageElements AsofDate = new WebPageElements("AS OF date", "xpath", Asofdate);
			waitForElementPresent(driver, 90, AsofDate);
			String AsOfDate = getText(driver, AsofDate);
			System.out.println(AsOfDate);
			By ele1 = By.xpath("(//div[text()='CREATE TRADE']//following::input)[1]");
			waitForElementPresent(driver, 120, ele1, " Asset Class Code ");
			click(driver, ele1, "by drilling down to " + ReportName + " - " + viewName + " Asset Class Code");
			Thread.sleep(2000);
			//	cobselect(driver, AsOfDate);
			By margined = By.xpath("//label[text()='Margined']");
			waitForElementPresent(driver, 120, margined, "Margined");
			click(driver, margined, "Margined");
			String marginedid = "//input[@name='marginID']";
			WebPageElements Marginedid = new WebPageElements("MarginedID", "xpath", marginedid);
			click(driver, Marginedid);
			setTextentern(driver, Marginedid, marginalID);
			Thread.sleep(2000);
			waitForLoad(driver, 900);
			waitForLoad(driver, 900);
			By eletext = By.xpath("//font[text()='"+marginalID+"'] | //li[text()='"+marginalID+"']");
			waitForElementPresentnoAssert(driver, 120, eletext, marginalID);
			Thread.sleep(2000);
			waitForLoad(driver, 900);
			clicknoassert(driver, eletext, marginalID);
			Thread.sleep(2000);
			waitForLoad(driver, 900);
			By AssetClass = By.xpath("//span[text()='2. Asset Class']");
			waitForElementPresent(driver, 120, AssetClass, "Asset Class");
			click(driver, AssetClass, "by drilling down to " + ReportName + " - " + viewName + "Drop down value for Asset Class Code ");
			Thread.sleep(2000);
			String assetClassCode = "//input[@name='assetClassCode']";
			WebPageElements AssetClassCode = new WebPageElements("asset Class ", "xpath", assetClassCode);
			click(driver, AssetClassCode);
			String Code = "//li[text()='"+Verify4+"']";
			WebPageElements Asset_Code = new WebPageElements("eqty", "xpath", Code);
			click(driver, Asset_Code);
			Thread.sleep(2000);



			if(Verify4.equals("FX")||Verify4.equals("IR"))
			{
				String tradeCode = "//input[@name='tradeType']/following::div[1]";
				WebPageElements TradeCode = new WebPageElements("Trade code", "xpath", tradeCode);
				click(driver, TradeCode);

				String volatility = "//li[text()='Volatility']";
				WebPageElements Volatility = new WebPageElements("Volatility", "xpath", volatility);
				Thread.sleep(2000);
				click(driver, Volatility);
				waitForLoad(driver, 900);
				Thread.sleep(2000);
				code_fx(driver,Inputtext1sp,Inputtext2sp);

				if(Verify4.equals("FX"))
				{
					String selectCurrency = "//span[text()='Currency-2']/following::div[contains(@class,'combotrig ')]";
					WebPageElements SelectCurrency = new WebPageElements("selectCurrency", "xpath", selectCurrency);
					click(driver, SelectCurrency);
					Thread.sleep(2000);
					String Currency2_input = "//span[text()='Currency-2']/following::input";
					WebPageElements w_Currency2_input = new WebPageElements("Currency-2", "xpath", Currency2_input);
					Thread.sleep(2000);
					setTextenter(driver,w_Currency2_input,"AMD");
					waitForLoad(driver, 900);
					Thread.sleep(2000);
				}
			}
			else if(Verify4.equals("EQTY"))
			{
				String tradeCode = "//input[@name='tradeType']/following::div[1]";
				WebPageElements TradeCode = new WebPageElements("Trade code", "xpath", tradeCode);
				click(driver, TradeCode);

				String volatility = "//li[text()='Volatility']";
				WebPageElements Volatility = new WebPageElements("Volatility", "xpath", volatility);
				Thread.sleep(2000);
				click(driver, Volatility);
				waitForLoad(driver, 900);
				Thread.sleep(2000);

				code_fx(driver,Inputtext1sp,Inputtext2sp);
				String RegID = "//input[@name='regID']";
				WebPageElements regID = new WebPageElements("RegID", "xpath", RegID);
				click(driver, regID);
				Thread.sleep(2000);
				setTextentern(driver, regID, Inputtext2sp[4]);
				Thread.sleep(2000);
				waitForLoad(driver, 900);
			}
			else if(Verify4.equals("COM"))
			{
				String tradeCode = "//input[@name='tradeType']/following::div[1]";
				WebPageElements TradeCode = new WebPageElements("Trade code", "xpath", tradeCode);
				click(driver, TradeCode);

				String volatility = "//li[text()='Volatility']";
				WebPageElements Volatility = new WebPageElements("Volatility", "xpath", volatility);
				Thread.sleep(2000);
				click(driver, Volatility);
				waitForLoad(driver, 900);
				Thread.sleep(2000);

				String commCateg="//div[contains(@data-uipath,'commCateg')]//div[contains(@id,'trigger-picker')]";
				WebPageElements w_commCateg = new WebPageElements("Trade code", "xpath", commCateg);
				click(driver, w_commCateg);			String RegID = "//input[@name='regID']";
				Thread.sleep(1000);

				String Metals = "//li[text()='Metals']";
				WebPageElements w_Metals = new WebPageElements("Volatility", "xpath", Metals);
				Thread.sleep(2000);
				click(driver, w_Metals);
				waitForLoad(driver, 900);
				Thread.sleep(2000);
			}
			else if(Verify4.equals("CR_DERIV"))
			{

				String tradeCode = "//input[@name='tradeType']/following::div[1]";
				WebPageElements TradeCode = new WebPageElements("Trade code", "xpath", tradeCode);
				click(driver, TradeCode);

				String Index = "//li[text()='Index']";
				WebPageElements w_Index = new WebPageElements("Volatility", "xpath", Index);
				Thread.sleep(2000);
				click(driver, w_Index);
				waitForLoad(driver, 900);
				Thread.sleep(2000);

				code_fx(driver,Inputtext1sp,Inputtext2sp);

				String RegID = "//input[@name='regID']";
				WebPageElements regID = new WebPageElements("RegID", "xpath", RegID);
				click(driver, regID);
				Thread.sleep(2000);
				setTextentern(driver, regID, Inputtext2sp[4]);
				Thread.sleep(2000);
				waitForLoad(driver, 900);

				String creaditRating="//div[contains(@data-uipath,'creaditRating')]//div[contains(@id,'trigger-picker')]";
				WebPageElements w_creaditRating = new WebPageElements("Trade code", "xpath", creaditRating);
				click(driver, w_creaditRating);
				Thread.sleep(1000);

				String Metals = "//li[text()='Investment Grade']";
				WebPageElements w_Metals = new WebPageElements("Volatility", "xpath", Metals);
				Thread.sleep(2000);
				click(driver, w_Metals);
				waitForLoad(driver, 900);
				Thread.sleep(2000);

			}

			waitForLoad(driver, 120);
			Thread.sleep(2000);
			WebElement Savebutton = driver.findElement(By.xpath(Save_Btninside));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Savebutton);
			waitForElementPresent(driver, 120, save_btninside);
			click(driver, save_btninside);
			Thread.sleep(4000);
			waitForLoad(driver, 120);


		}

		 public void code_fx(WebDriver driver,String[] Inputtext1sp,String[] Inputtext2sp) {
			 try {

				 String indicator = "//label[text()='" + Inputtext1sp[0] + "']/preceding-sibling::input";
				 WebPageElements Indicator = new WebPageElements("indicator Sell/Buy", "xpath", indicator);
				 click(driver, Indicator);
				 Thread.sleep(2000);

				 String StartDate = "//input[@name='startDate']";
				 WebPageElements startdate = new WebPageElements("StartDate", "xpath", StartDate);
				 click(driver, startdate);
				 Thread.sleep(2000);
				 setTextentern(driver, startdate, Inputtext2sp[0]);
				 Thread.sleep(2000);

				 String EndDate = "//input[@name='endDate']";
				 WebPageElements enddate = new WebPageElements("EndDate", "xpath", EndDate);
				 click(driver, enddate);
				 Thread.sleep(2000);
				 setTextentern(driver, enddate, Inputtext2sp[1]);
				 Thread.sleep(2000);

				 String selectCurrency = "//span[text()='Currency-1']/following::div[contains(@class,'combotrig ')]";
				 WebPageElements SelectCurrency = new WebPageElements("selectCurrency", "xpath", selectCurrency);
				 click(driver, SelectCurrency);
				 Thread.sleep(2000);
				 String Currency1 = "//li[text()='" + Inputtext2sp[2] + "']";
				 WebPageElements currency1 = new WebPageElements("Currency-1", "xpath", Currency1);
				 Thread.sleep(2000);
				 click(driver, currency1);
				 waitForLoad(driver, 900);
				 Thread.sleep(2000);

				 String Notational = "//input[@name='notional']";
				 WebPageElements notional = new WebPageElements("Notational", "xpath", Notational);
				 click(driver, notional);
				 Thread.sleep(2000);
				 setTextentern(driver, notional, Inputtext2sp[3]);
				 Thread.sleep(2000);
				 waitForLoad(driver, 900);
			 }
			 catch (Exception e)
			 {
			 	e.printStackTrace();
			 }
		 }


	    //for margin test cases
	    public void MarginTcs(WebDriver driver,String MarginType,String TradeType) {
	        //(//*[text()='Trade View']//following::table)[+row_num+]//td[column_num]
	        //String[] expectedCode = expected_value.split(",");
	        try {
	            int columNumber = getColumnNumber(driver, "Margin ID","Trade View");
	            System.out.println(columNumber);

	            Thread.sleep(3000);
				ArrayList<String> list=new ArrayList<>(120);

	            int totalPages=getTotalPages(driver,"Trade View");
	            for (int pageNum = 1; pageNum <= totalPages; pageNum++) {
	                int totalRecords = driver.findElements(By.xpath("//*[text()='Trade View']/parent::label/parent::div//table")).size();
	                for (int rowNum = 0; rowNum <= totalRecords - 1; rowNum++) {
	                    String element = "(//*[text()='Trade View']/parent::label/parent::div//table)[@data-recordindex='" + rowNum + "']//td[" + columNumber + "]//div";
	                    WebPageElements w_element = new WebPageElements("element", "Xpath", element);
	                    WebElement ele = getWebElement(driver, w_element);
	                    String text = ele.getAttribute("innerHTML");
	                    String actualText = text.replace("&nbsp;", "");
	                    list.add(text);
	                }
	                if (pageNum < totalPages) {
	                    String NEXT_PAGE = "//a[contains(@class,'btn-next')]";
	                    WebPageElements w_next_page = new WebPageElements("Next Page Icon", "Xpath", NEXT_PAGE);
	                    click(driver, w_next_page);
	                    Thread.sleep(2000);
	                }
	            }
					//To validate Margin Ids in Trade View table
				if(TradeType.equals("Existing Portfolio"))
				{
	            if(MarginType.equals("One To One")|| MarginType.equals("Unmargin"))
					ValidateTradeMarginIDs(driver, MarginType, list, list.size());}

				if(TradeType.equals("HT Trade")&&MarginType.equals("Unmargin"))
					ValidateTradeMarginIDs(driver, MarginType, list, list.size());

	                if(MarginType.equals("Unmargin"))
	                {
	                    Traderadiobutton(driver);
	                    Portfoliocalculator(driver);
	                    Thread.sleep(3000);
	                    waitForLoad(driver,300);
	                    Thread.sleep(3000);
	                    String CalculateRWA="//*[text()='Calculate RWA']";
	                    WebPageElements w_calculateRWA=new WebPageElements("Calculate RWA value","Xpath",CalculateRWA);
	                    click(driver,w_calculateRWA);
	                    waitForLoad(driver,200);
	                }
	                else {
	                    TradeCSAViewradiobutton(driver);
	                    Portfoliocalculator(driver);
	                }
					Thread.sleep(2000);
					waitForLoad(driver, 900);
					// Submit Pop click on get message
					Sumbitpopok(driver);
					// Check calculator Status until
					Checkcalculatorstatus(driver);
					Thread.sleep(3000);
					//To validate Result Type and Margin IDs in Prtfolio table
					ValidatePortfolioResults(driver, MarginType);

	        } catch (Exception e) {
	            e.printStackTrace();
	            TestResultStatus.mpaskeysnew.put(methodName(), "Failed to validate column data");
	            Assert.fail("Failed to validate column data");

	        }
	    }

	    public int getTotalPages(WebDriver driver,String tableName)
	    {
	        String TOTAL_PAGEs = "//*[text()='"+tableName+"']/parent::label/parent::div//div[contains(@id,'corepagingtoolbar')]//div[contains(@class,'x-toolbar-text')][2]";
	        WebPageElements w_total_pages = new WebPageElements("Total Pages in the dashboard", "Xpath", TOTAL_PAGEs);
	        String total_pages = getText(driver, w_total_pages);
	        String pages_in_total[] = total_pages.split(" ");
	        String pages=pages_in_total[1].replace(",","");
	        int totalPages = Integer.parseInt(pages);
	       return totalPages;
	    }

	    public void ValidateTradeMarginIDs(WebDriver driver,String MarginType,ArrayList<String> list,int count)
	    {
	        try {

	            if (MarginType.equals("One To One")) {
	                for (int i = 0; i < count; i++) {
						if (i + 1 < count) {
							if (list.get(i).equals(list.get(i + 1)))
								Add_Log.info("Margin Id is values are equal as expected");
							 else
								Assert.fail("Table value are not equal:" + list);
						}
					}
	            } else if (MarginType.equals("One To Multiple")) {
	                for (int i = 0; i < count; i++) {
						if (i + 1 < count) {
							if (list.get(i).equals(list.get(i + 1))) {
								Assert.fail("Margin id value are equal for record num:" + i + "The actual ID's of records are:" + list);
							} else {
								Add_Log.info("Margin Id is not equal as expected");
							}
						}
					}
	            } else if (MarginType.equals("Unmargin")) {
	                for (int i = 0; i <count; i++) {
	                    if (list.get(i).equals("")||list.get(i).equals("&nbsp;")) {
	                        Add_Log.info("Margin Id is empty as expected");
	                    } else {
	                        Assert.fail("Table value are equal for record num:" + i + "The actual ID's of records are:" + list);
	                    }
	                }
	            }
	        }
	        catch (Exception e)
	        {
	        e.printStackTrace();
	        Assert.fail();
	        }
	    }

	    public void ValidatePortfolioResults(WebDriver driver,String MarginType)
	    {

	        try {
	            //int totalPages = getTotalPages(driver, "Portfolio Results");
	            int columNumber = getColumnNumber(driver, "Result Type", "Portfolio Results");
	            HashMap<Integer, String> ResultType = new HashMap<>();
	            HashMap<Integer, String>  MarginID = new HashMap<>();

	                int totalRecords = driver.findElements(By.xpath("//*[text()='Portfolio Results']/parent::label/parent::div//table")).size();
	                for (int rowNum = 0; rowNum <= totalRecords - 1; rowNum++) {
	                    String resultType_element = "(//*[text()='Portfolio Results']/parent::label/parent::div//table)[@data-recordindex='" + rowNum + "']//td[2]//div";
	                    WebPageElements w_resultType_element = new WebPageElements("element", "Xpath", resultType_element);
	                    WebElement resultType_WebElement = getWebElement(driver, w_resultType_element);
	                    String resultType = resultType_WebElement.getAttribute("innerHTML");

	                    String marginID_element = "(//*[text()='Portfolio Results']/parent::label/parent::div//table)[@data-recordindex='" + rowNum + "']//td[4]//div";
	                    WebPageElements w_marginID_element = new WebPageElements("element", "Xpath", marginID_element);
	                    WebElement marginID_WebElement = getWebElement(driver, w_marginID_element);
	                    String marginID = marginID_WebElement.getAttribute("innerHTML");

	                    ResultType.put(rowNum + 1, resultType);
	                    MarginID.put(rowNum + 1, marginID);
	                }

	            Thread.sleep(3000);
	            validateresultTypeAndMarginID(ResultType,MarginID,MarginType);
	        }
	        catch (Exception e)
	        {
	        e.printStackTrace();
	        Assert.fail();
	        }
	    }



		public void Traderadiobutton(WebDriver driver) throws InterruptedException {
			By TradeView1 = By.xpath(
					"(//*[text()='Trade View']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]");
			WebElement TradeViewbox1 = driver.findElement(TradeView1);
			click(driver, TradeView1, "Trade view check box");
			Thread.sleep(2000);
			//By CSAView1 = By.xpath("(//*[text()='CSA VIEW']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]");
			}


		public void validateresultTypeAndMarginID(HashMap<Integer,String> resultType,HashMap<Integer,String> marginID,String MarginType)
	    {
	        try {
	            if(MarginType.equals("One To Multiple"))
	            {
	                loop:
	                for(int i=1;i<resultType.size();i++)
	                {
	                    String resul1=resultType.get(i);
	                    if(resul1.equals(resultType.get(i+1)))
	                    {
	                        if (marginID.get(i).equals(marginID.get(i+1)))
	                        {
	                            Assert.fail("Margin ids are equal for same result type+ margin id1:"+marginID.get(i)+"margin id2:"+marginID.get(i+1));
	                            TestResultStatus.mpaskeysnew.put(methodName(),"Margin ids are equal for same result type+ margin id1:"+marginID.get(i)+"margin id2:"+marginID.get(i+1));
	                            break loop;
	                        }else
	                            Add_Log.info("Both the margin Id's of Result Type: "+resultType.get(i));
	                    }
	                    else
	                    {
	                        if(marginID.isEmpty())
	                        {
	                            Assert.fail("Margin Id is empty");
	                            TestResultStatus.mpaskeysnew.put(methodName(),"Margin Id is empty");
	                        }
	                    }

	                }
	            }

	            if(MarginType.equals("One To One"))
	            {
	                loop:
	                for(int i=1;i<resultType.size();i++)
	                {
	                        if (marginID.get(i).equals(marginID.get(i+1)))
	                        Add_Log.info("Margin ID are equal. marginID1: "+marginID.get(i)+"margin id2: "+marginID.get(i+1));

	                    else
	                    {
	                        Assert.fail("Margin ids are not equal. margin id1:"+marginID.get(i)+"margin id2:"+marginID.get(i+1));
	                        TestResultStatus.mpaskeysnew.put(methodName(),"Margin ids are not equal. margin id1:"+marginID.get(i)+"margin id2:"+marginID.get(i+1));
	                        break loop;
	                    }

	                }
	            }
	            if(MarginType.equals("Unmargin"))
	            {
	                for(int i=1;i<=resultType.size();i++)
	                {
	                    if(marginID.get(i).isEmpty()||marginID.get(i).equals("&nbsp;"))
	                    {
	                        Add_Log.info("Margin ID is empty as expected for line:"+i);
	                    }
	                    else
	                    {
	                        Assert.fail("Margin id value is not empty at line number: "+i);
	                        TestResultStatus.mpaskeysnew.put(methodName(),"Margin id value is ont empty at line number: "+i);

	                    }
	                }
	            }



	        }
	        catch (Exception e)
	        {
	        e.printStackTrace();
	            Assert.fail();
	        }

	    }

	   /* GFC2:1017740322
	    Prt2:268071040
	    Margin ID: 13449923
	24552223
	13103227

	    M: 13103227
	    g: 1017471461*/
}




