package pageobjects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import property.IHomePage;
import testsuitebase.SuiteBase;
import testsuitebase.TestResultStatus;
import utility.SeleniumUtils;
import utility.WebPageElements;

public class WhatifBBK extends SeleniumUtils implements IHomePage {

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

	public void sleepRandomly() {
		try {
			Thread.sleep(getMillis());
			System.out.println(getMillis());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}
	}

	public static long getMillis() {
		// define the range
		int max = 10000;
		int min = 1000;
		int range = max - min + 1;

		// generate random numbers within 1 to 10
		int rand = (int) (Math.random() * range) + min;
		// Output is different everytime this code is executed

		return rand;
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

	}

	// click on Add button for Scenario
	public void WIAAddbutton(WebDriver driver, String viewName, String Verify, String ReportName)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		By ele1 = By.xpath("(//label[text()='" + Verifysp[0]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-plus '])[1]");
		waitForElementPresent(driver, 120, ele1, " Add icon for " + Verifysp[0]);
		click(driver, ele1, "by drilling down to " + ReportName + " - " + viewName + " - " + Verifysp[0] + " Add icon");
		Thread.sleep(2000);
	}

	// Add name and Description for Scenario
	public void WIAaddScenariopop(WebDriver driver, String Verify3, String Verify) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		waitForElementPresent(driver, 120, addscenarioname);
		click(driver, addscenarioname);
		Thread.sleep(2000);
		setText(driver, addscenarioname, Verify3sp[0]);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, adddesc);
		click(driver, adddesc);
		Thread.sleep(200);
		setText(driver, adddesc, Verify3sp[1]);
		Thread.sleep(2000);
		By ele3 = null;
		if (Verify.contains("Portfolio")) {
			ele3 = By.xpath("//span[text()='Create']");
		} else {
			ele3 = By.xpath("//span[text()='Save']");
		}
		System.out.println(ele3);
		System.out.println(Verify);
		waitForElementPresent(driver, 120, ele3, " Save/Create button for  " + Verify);
		click(driver, ele3, "Save/Create button for " + Verify);
		Thread.sleep(2000);
	}

	// Tc05 // 06 today date Add name and Description for Scenario
	public void WIAaddScenariopoptodaydate(WebDriver driver, String Verify3, String Verify)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		waitForElementPresent(driver, 120, addscenarioname);
		click(driver, addscenarioname);
		Thread.sleep(2000);
		String scenarioName = Verify3sp[0] + "-" + sdf.format(timestamp);
		String scenarioDesc = Verify3sp[1] + "-" + sdf.format(timestamp);
		setText(driver, addscenarioname, scenarioName);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, adddesc);
		click(driver, adddesc);
		Thread.sleep(200);
		setText(driver, adddesc, scenarioDesc);
		Thread.sleep(2000);
		By ele3 = null;
		if (Verify.contains("Portfolio")) {
			ele3 = By.xpath("//span[text()='Create']");
		} else {
			ele3 = By.xpath("//span[text()='Save']");
		}
		System.out.println(ele3);
		System.out.println(Verify);
		waitForElementPresent(driver, 120, ele3, " Save/Create button for  " + Verify);
		click(driver, ele3, "Save/Create button for " + Verify);
		Thread.sleep(2000);
	}

	// Tc 07 Edit name and Description for Scenario
	public void EditScenariopop(WebDriver driver, String Verify3) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		waitForElementPresent(driver, 120, addscenarioname);
		click(driver, addscenarioname);
		Thread.sleep(2000);
		clearText(driver, addscenarioname);
		Thread.sleep(200);
		setText(driver, addscenarioname, Verify3sp[0]);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, adddesc);
		click(driver, adddesc);
		Thread.sleep(1000);
		clearText(driver, adddesc);
		Thread.sleep(200);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String scenarioName = Verify3sp[0] + "-" + sdf.format(timestamp);
		setText(driver, adddesc, scenarioName);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		Thread.sleep(2000);
	}

	// click Scenario Search Facility
	public void WIASearchFacility(WebDriver driver, String viewName, String Verify, String ReportName)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		By ele1 = By.xpath("(//label[text()='" + Verifysp[0]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-search '])[2]");
		waitForElementPresent(driver, 120, ele1, " Search Facility for " + Verifysp[0]);
		click(driver, ele1,
				"by drilling down to " + ReportName + " - " + viewName + " - " + Verifysp[0] + " Search Facility");
		Thread.sleep(2000);
	}

	// click Exposure Search
	public void WIASearchExposure(WebDriver driver, String viewName, String Verify, String ReportName)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		By ele1 = By.xpath("(//label[text()='" + Verifysp[0]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-search '])[1]");
		waitForElementPresent(driver, 120, ele1, " Search Facility for " + Verifysp[0]);
		click(driver, ele1, "by drilling down to " + ReportName + " - " + viewName + " - " + Verifysp[0] + " Search");
		Thread.sleep(2000);
	}

	// Header for SCENARIO
	// Header for Search
	public void WIAHeadername(WebDriver driver, String Verify2) throws InterruptedException {
		Thread.sleep(2000);
		By ele1 = By.xpath("//div[text()='" + Verify2 + "']");
		waitForElementPresentJS(driver, 90, ele1, " Header for " + Verify2);
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

	// displayed Created GFRN- Exposure Key
	public void WIAExposureverfiy(WebDriver driver, String Verify, String Report, String Verify4)
			throws InterruptedException {

		waitForLoad(driver, 90);
		Thread.sleep(2000);
		WebElement extractxpathid = driver
				.findElement(By.xpath("//label[text()='" + Verify + "']//following::span[text()='" + Report + "']"));

		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		By filter = By.xpath("//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div");
		WebElement filterscenarioName = driver.findElement(filter);
		waitForLoad(driver, 90);
		Thread.sleep(2000);
		String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
		System.out.println(filterscenarioNamea);
		if (filterscenarioName.isDisplayed()) {
			Add_Log.info("Successfully displayed Created/Search " + Verify + " Key = " + filterscenarioNamea);
			Reporter.log("Successfully displayed Created/Search " + Verify + " Key = " + filterscenarioNamea);
		} else {
			Add_Log.info("Unable to displayed Created/Search " + Verify + " Key = " + Verify4);
			Reporter.log("Unable to displayed Created/Search " + Verify + " Key = " + Verify4);
			TestResultStatus.mpaskeysnew.put(methodName(),
					"Unable to displayed Created/Search " + Verify + " Key = " + Verify4);
			Assert.fail();

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

	// get Created scenario id
	public String Getscenarioid(WebDriver driver, String Verify, String Report) throws InterruptedException {

		String[] Verifysp = Verify.split("\\,");
		WebElement extractxpathid = driver.findElement(
				By.xpath("//label[text()='" + Verifysp[0] + "']//following::span[text()='" + Report + "']"));
		System.out.println(extractxpathid);
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		By filter = By.xpath("//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1]
				+ " x-grid-cell-first']//div");
		WebElement scenarioid = driver.findElement(filter);
		String scenarioidstring = scenarioid.getAttribute("textContent");
		System.out.println(scenarioidstring);
		if (scenarioid.isDisplayed()) {
			Add_Log.info("Successfully displayed Created scenario Id = " + scenarioidstring);
			Reporter.log("Successfully displayed Created scenario Id= " + scenarioidstring);
		} else {
			Add_Log.info("Unable to displayed Created scenario");
			Reporter.log("Unable to displayed Created scenario");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Created scenario");
			Assert.fail();
		}
		return scenarioidstring;
	}

	// COB select
	public void cobselect(WebDriver driver, String reportingPeriod) throws InterruptedException {
		Thread.sleep(2000);
		click(driver, cobselect);
		Thread.sleep(2000);
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

	// Enter GFRN and Search / and add
	public void EnterGFRNandSearch(WebDriver driver, String Verify2, String Verify4, String Verify)
			throws InterruptedException {

		String[] Verify2sp = Verify2.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		try {
			String sort1 = "(//*[text()='SEARCH']//following::span[text()='GFRN'])[1]//following::input[@name='searchInput']";
			By sort = By.xpath(sort1);
			Thread.sleep(2000);
			WebPageElements sort_dll = new WebPageElements(Verify2sp[1], "xpath", sort1);
			WebElement sort_dlle = driver.findElement(sort);
			click(driver, sort, "by drilling down to " + Verify2sp[1] + " and click on GFRN Search");
			Thread.sleep(2000);
			waitForLoad(driver, 900);
			Thread.sleep(2000);
			setTextenter(driver, sort_dll, Verify4);
			Thread.sleep(2000);
			waitForLoad(driver, 900);
			waitForLoad(driver, 900);
			// font[text()='HK2151846-1']

			By eletext = By.xpath("//font[text()='" + Verify4 + "'] | //li[text()='" + Verify4 + "']");
			// waitForElementPresentnoAssert(driver, 30, eletext, Verify4);
			waitForVisiblilitynoAssert(driver, 30, eletext, Verify4);
			clicknoassert(driver, eletext, Verify4);
			Thread.sleep(2000);
			waitForLoad(driver, 900);

			String sort2 = "(//label[text()='FACILITY SEARCH RESULT']//following::td)[2]//div";
			By sort21 = By.xpath(sort2);
			Thread.sleep(2000);
			WebPageElements sort2_dll = new WebPageElements("FACILITY SEARCH RESULT", "xpath", sort2);
			waitForElementPresent_noAs(driver, 30, sort2_dll);
			WebElement sort2_dlle = driver.findElement(sort21);
			String valuetext = sort2_dlle.getText();
			System.out.println(valuetext);
			if (Verify4.contains(valuetext)) {
				Add_Log.info("Successfully GFRN " + Verify4 + " selected");
				Reporter.log("Successfully GFRN " + Verify4 + " selected");
			} else {
				Add_Log.info("Unable GFRN " + Verify4 + " selected");
				Reporter.log("Unable GFRN " + Verify4 + " selected");
				TestResultStatus.mpaskeysnew.put(methodName(), "Unable GFRN " + Verify4 + " selected");
				Assert.fail();
			}
		} catch (Exception e1) {

		}
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		click(driver, add_btninside);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		if (Verifysp[1].equals("Guarantee")) {
			By clickguarantee = By.xpath("//span[text()='Guarantee']");
			click(driver, clickguarantee, "Expand Secondary Header Guarantee");
			Thread.sleep(2000);
			waitForLoad(driver, 900);
		}

	}

	// Enter GFRN and Search / and add
	public void EnterGFRNandSearchAssert(WebDriver driver, String Verify2, String Verify4, String Verify)
			throws InterruptedException {

		String[] Verify2sp = Verify2.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		String sort1 = "(//*[text()='SEARCH']//following::span[text()='GFRN'])[1]//following::input[@name='searchInput']";
		By sort = By.xpath(sort1);
		Thread.sleep(2000);
		WebPageElements sort_dll = new WebPageElements(Verify2sp[1], "xpath", sort1);
		WebElement sort_dlle = driver.findElement(sort);
		click(driver, sort, "by drilling down to " + Verify2sp[1] + " and click on GFRN Search");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		Thread.sleep(2000);
		setTextenter(driver, sort_dll, Verify4);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		// font[text()='HK2151846-1']

		By eletext = By.xpath("//font[text()='" + Verify4 + "'] | //li[text()='" + Verify4 + "']");
		waitForElementPresent(driver, 30, eletext, "text");
		click(driver, eletext, "text");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		String sort2 = "(//label[text()='FACILITY SEARCH RESULT']//following::td)[2]//div";
		By sort21 = By.xpath(sort2);
		Thread.sleep(2000);
		WebPageElements sort2_dll = new WebPageElements("FACILITY SEARCH RESULT", "xpath", sort2);
		waitForElementPresent(driver, 30, sort2_dll);
		WebElement sort2_dlle = driver.findElement(sort21);
		String valuetext = sort2_dlle.getText();
		System.out.println(valuetext);
		if (Verify4.contains(valuetext)) {
			Add_Log.info("Successfully GFRN " + Verify4 + " selected");
			Reporter.log("Successfully GFRN " + Verify4 + " selected");
		} else {
			Add_Log.info("Unable GFRN " + Verify4 + " selected");
			Reporter.log("Unable GFRN " + Verify4 + " selected");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable GFRN " + Verify4 + " selected");
			Assert.fail();
		}

		Thread.sleep(2000);
		waitForLoad(driver, 900);
		click(driver, add_btninside);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		if (Verifysp[1].equals("Guarantee")) {
			By clickguarantee = By.xpath("//span[text()='Guarantee']");
			click(driver, clickguarantee, "Expand Secondary Header Guarantee");
			Thread.sleep(2000);
			waitForLoad(driver, 900);

		}
	}

	// Enter Portfolio and Search / and add
	public void EnterPortfolioandSearch(WebDriver driver, String Verify2, String Verify4) throws InterruptedException {
		String[] Verify2sp = Verify2.split("\\,");
		click(driver, portoflioinput);
		Thread.sleep(2000);
		// setText(driver,portoflioinput,Verify4);
		waitForLoad(driver, 900);
		Thread.sleep(2000);
		setTextenter(driver, portoflioinput, Verify4);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		// font[text()='HK2151846-1']
		By eletext = By.xpath("//font[text()='" + Verify4 + "'] | //li[text()='" + Verify4 + "']");
		waitForElementPresent(driver, 120, eletext, "text");
		click(driver, eletext, "text");
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
	}

	// select 1st row radio button for Exposure key
	public void RadiobuttonExposurekey(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		String id = "(//*[text()='Exposure']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]";
		String name = "Radio Button to select the Exposure key";
		WebPageElements radiobutton = new WebPageElements(name, "xpath", id);
		waitForElementPresent1(driver, 120, radiobutton);
		click(driver, radiobutton);
		Thread.sleep(2000);
	}

	// select 1st row radio button for Collateral key
	public void RadiobuttonCollateralkey(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		String idcoll = "(//*[text()='Collateral']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]";
		String namecoll = "Radio Button to select the Collateral key";
		WebPageElements radiobuttoncoll = new WebPageElements(namecoll, "xpath", idcoll);
		waitForElementPresent1(driver, 120, radiobuttoncoll);
		click(driver, radiobuttoncoll);
		Thread.sleep(2000);
	}

	// CLick on Eligibility Check Submit icon and verify
	public void EligibilityCheckSubmiticonverify(WebDriver driver) throws InterruptedException {
		By ele1 = By.xpath(
				"//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small hasIcon icon icon-check-mark-submit ']");
		waitForElementPresent(driver, 120, ele1, " Sumbit icon ");
		click(driver, ele1, "by drilling down to  Eligibility Check Submit icon");
		By greenicon = By.xpath("//div[@class='icon icon-check-circle']");
		waitForElementPresent(driver, 60, greenicon, "Green Icon");
		WebElement greeniconweb = driver.findElement(greenicon);
		if (greeniconweb.isDisplayed()) {
			Add_Log.info("Successfully Eligibility Check Green Icon displayed");
			Reporter.log("Successfully Eligibility Check Green Icon displayed");
		} else {
			Add_Log.info("Unable to Eligibility Check Error displayed");
			Reporter.log("Unable to Eligibility Check Error displayed");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to Eligibility Check Error displayed");
			Assert.fail();
		}
	}

	// Click on Eligibility calculator
	public void eligibilitycalculator(WebDriver driver) throws InterruptedException {
		By filter = By
				.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small hasIcon icon icon-calculator ']");
		click(driver, filter, "Eligibility icon-calculator");
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
	}

	// Verify value display under Summary,Exposure, Mitigant
	public void VerifyEligibilitySME(WebDriver driver) throws InterruptedException {
		
		try {
			By ErrorSnackBar = By.xpath("//div[contains(@class,'x-message-box-error')]");
			if (driver.findElements(ErrorSnackBar).size() > 0) {
				WebElement errormessage = driver.findElement(By.xpath("//div[contains(@class,'x-message-box-error')]//following::div[contains(text(),'System Error')]"));
				Reporter.log("Error message: " + errormessage.getText());
				Add_Log.info("Error message: " + errormessage.getText());
				TestResultStatus.mpaskeysnew.put(methodName(),"Error message: " + errormessage.getText());
				ITestResult result = Reporter.getCurrentTestResult();
				result.setAttribute("DontRetry", Boolean.TRUE.toString());
				Assert.fail();
			}
		} catch (NoSuchElementException e1) {
			
		}
		String idsummary = "(//label[text()='Summary']//following::td)[1]";
		String descsummary = "Desc summary";
		WebPageElements summarydesc = new WebPageElements(descsummary, "xpath", idsummary);
		waitForElementPresent1(driver, 120, summarydesc);
		By idsummarytext = By.xpath(idsummary);
		WebElement summarydescs = driver.findElement(idsummarytext);
		if (summarydescs.isDisplayed()) {
			Add_Log.info("Successfully summary is displayed");
			Reporter.log("Successfully summary is displayed");
		} else {
			Add_Log.info("Unable to display summary");
			Reporter.log("Unable to display summary");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to display summary");
			Assert.fail();
		}
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		By expsouretab = By.xpath(
				("(//span[@class='x-tab-icon-el x-tab-icon-el-secondary-tabs-elements  ']//following::span[text()='Exposure'])[1]"));
		click(driver, expsouretab, "Expsoure tab");
		Thread.sleep(2000);
		String idexpsoure = "((//label[text()='Exposure'])[2]//following::td)[1]";
		String idexpsouretext = "Exposure tab";
		WebPageElements idexpsourewe = new WebPageElements(idexpsouretext, "xpath", idexpsoure);
		By idexpsoureweby = By.xpath(idexpsoure);
		WebElement idexpsouree = driver.findElement(idexpsoureweby);
		waitForElementPresent1(driver, 120, idexpsourewe);
		if (idexpsouree.isDisplayed()) {
			Add_Log.info("Successfully Exposure is displayed");
			Reporter.log("Successfully Exposure is displayed");

		} else {
			Add_Log.info("Unable to display Exposure");
			Reporter.log("Unable to display Exposure");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to display Exposure");
			Assert.fail();
		}
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		By mig1tab = By.xpath(
				("(//span[@class='x-tab-icon-el x-tab-icon-el-secondary-tabs-elements  ']//following::span[text()='Mitigant'])[1]"));
		click(driver, mig1tab, "Mitigant tab");
		String idmig = "(//label[text()='Mitigant']//following::td)[1]";
		String idmigtext = "Mitigant tab";
		WebPageElements idmigwe = new WebPageElements(idmigtext, "xpath", idmig);
		waitForElementPresent1(driver, 120, idmigwe);
		By idmigweby = By.xpath(idmig);
		WebElement idmige = driver.findElement(idmigweby);
		if (idmige.isDisplayed()) {
			Add_Log.info("Successfully Mitigant is displayed");
			Reporter.log("Successfully Mitigant is displayed");
		} else {
			Add_Log.info("Unable to display Mitigant");
			Reporter.log("Unable to display Mitigant");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to display Mitigant");
			Assert.fail();
		}
	}

	// VerifyEligibilityERROR
	public void VerifyEligibilityERROR(WebDriver driver) throws InterruptedException {

		By redicon = By.xpath("//div[@class='icon icon-warning-alt']");
		waitForElementPresent(driver, 60, redicon, "Red Icon");
		WebElement rediconweb = driver.findElement(redicon);
		if (rediconweb.isDisplayed()) {
			Add_Log.info("Successfully able to get error message  Eligibility Check");
			Reporter.log("Successfully able to get error message  Eligibility Check");
		} else {
			Add_Log.info("Unable to get error message  Eligibility Check");
			Reporter.log("Unable to get error message  Eligibility Check");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable get error message  Eligibility Check");
			Assert.fail();
		}
		WebElement extractxpathid = driver
				.findElement(By.xpath("//label[text()='Eligibility Check']//following::span[text()='Reason']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		By filtern = By
				.xpath("//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "')]//div");
		WebElement filterscenarioNamen = driver.findElement(filtern);
		String filterscenarioNamean = filterscenarioNamen.getAttribute("textContent");
		System.out.println(filterscenarioNamean);
		Add_Log.info("Successfully displayed error message Reason = " + filterscenarioNamean);
		Reporter.log("Successfully displayed error message Reason = " + filterscenarioNamean);
	}

	// displayed Created Collateral Mitigant Key
	public void WIACollMitigantKey(WebDriver driver, String Verify, String Report) throws InterruptedException {
		Thread.sleep(2000);
		By extractxpathidpath = By.xpath("//label[text()='" + Verify + "']//following::span[text()='" + Report + "']");
		waitForElementPresent(driver, 60, extractxpathidpath, Verify + " table content " + Report);
		WebElement extractxpathid = driver.findElement(extractxpathidpath);
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		By filter = By.xpath("//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div");
		WebElement filterscenarioName = driver.findElement(filter);
		String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
		System.out.println(filterscenarioNamea);
		if (filterscenarioNamea.startsWith("HC")) {
			Add_Log.info("Successfully displayed Created/Search " + Verify + " Key = " + filterscenarioNamea);
			Reporter.log("Successfully displayed Created/Search " + Verify + " Key = " + filterscenarioNamea);
		} else {
			Add_Log.info("Unable to displayed Clone/Edit " + Verify + " Key = ");
			Reporter.log("Unable to displayed Clone/Edit " + Verify + " Key = ");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Clone/Edit " + Verify + " Key = ");
			Assert.fail();
		}
	}

	// Enter value in Clone Coll for Tc1
	public void WIACloneColl(WebDriver driver, String Verify, String Verify2, String actualViews,
			String InputactualViews) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		List<String> views2 = new ArrayList<>();
		String[] actualViewsp = actualViews.split("\\,");
		String[] InputactualViewsp = InputactualViews.split("\\,");

		List<WebElement> options2 = driver.findElements(By.xpath("//div[text()='" + Verify2sp[2]
				+ "']//following::label[contains(@class,'mandatoryLabel')]|//div[text()='" + Verify2sp[2]
				+ "']//following::label[contains(@class,'x-form-item-label x-form-item-label-default  x-form-item-label-top x-unselectable')]"));
		System.out.println(options2);
		for (WebElement ele : options2) {
			views2.add(ele.getAttribute(("textContent")));
		}
		System.out.println(views2);
		for (int i1 = 0; i1 < actualViewsp.length; i1++) {

			if (views2.get(i1).equals(actualViewsp[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + views2.get(i1) + " under " + Verify2sp[2] + " all tabs "
						+ Verifysp[1]);
				Reporter.log("Successfully displayed " + views2.get(i1) + " under " + Verify2sp[2] + " all tabs  "
						+ Verifysp[1]);
			} else {
				Add_Log.info("Unable to display " + actualViewsp[i1].trim().toString() + " under " + Verify2sp[2]
						+ " all tabs " + Verifysp[1]);
				Reporter.log("Unable to display " + actualViewsp[i1].trim().toString() + " under " + Verify2sp[2]
						+ " all tabs " + Verifysp[1]);
				TestResultStatus.mpaskeysnew.put(methodName(), "Unable to display " + actualViewsp[i1].trim().toString()
						+ " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
				Assert.fail();
			}
		}
		String aggreationfilter = null;
		String inputtextsearch1 = null;
		WebPageElements inputtextfeildid = null;
		for (int i1 = 0; i1 < InputactualViewsp.length; i1++) {
		}
		for (int i = 0; i < views2.size(); i++) {

			System.out.print(views2.get(i) + " " + InputactualViewsp[i].trim().toString());

			aggreationfilter = "(//div[text()='" + Verify2sp[2] + "']//following::span[text()='" + views2.get(i)
					+ "']//following::div[contains(@id,'trigger-picker')])[1]";
			inputtextsearch1 = "(//div[text()='" + Verify2sp[2] + "']//following::span[text()='" + views2.get(i)
					+ "']//following::input)[1]";
			Thread.sleep(1000);

			if (views2.get(i).equalsIgnoreCase("Next Review Date")) {
				inputtextfeildid = new WebPageElements(InputactualViewsp[i].trim().toString(), "xpath",
						inputtextsearch1);
				clearText(driver, inputtextfeildid);
				Thread.sleep(1000);
				setTextenter(driver, inputtextfeildid, InputactualViewsp[i]);
				Thread.sleep(1000);
			}

			if (views2.get(i).equalsIgnoreCase("Parent Customer ID") | views2.get(i).equalsIgnoreCase("Customer ID")) {
				Add_Log.info("No Edit for " + views2.get(i));
				Reporter.log("No Edit for " + views2.get(i));
			}

			if (views2.get(i).equalsIgnoreCase("Mitigant Value")) {
				Thread.sleep(500);
				inputtextfeildid = new WebPageElements(InputactualViewsp[i].trim().toString(), "xpath",
						inputtextsearch1);
				clearText(driver, inputtextfeildid);
				Thread.sleep(1000);
				setTextenter(driver, inputtextfeildid, InputactualViewsp[i]);
				Thread.sleep(1000);
			} else {
				try {
					WebElement aggreationfilterweb = driver.findElement(By.xpath(aggreationfilter));
					By aggreationfilterid = (By.xpath(aggreationfilter));
					WebDriverWait wait = new WebDriverWait(driver, 10);
					wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
					clicknoassert(driver, aggreationfilterid, views2.get(i));
					inputtextfeildid = new WebPageElements(InputactualViewsp[i].trim().toString(), "xpath",
							inputtextsearch1);
					clearText(driver, inputtextfeildid);
					Thread.sleep(1000);
					setTextenter(driver, inputtextfeildid, InputactualViewsp[i]);
					Thread.sleep(1000);
				} catch (Exception e1) {
					Add_Log.info("No click with no drop down for " + views2.get(i));
					Reporter.log("No click with no drop down for " + views2.get(i));
				}
			}

		}
		// click on legalenforceabilitycertainty = Y
		waitForElementPresent(driver, 120, legalenforceabilitycertainty);
		click(driver, legalenforceabilitycertainty);
		// click on isfirstliened = Y
		waitForElementPresent(driver, 120, isfirstliened);
		click(driver, isfirstliened);

		waitForLoad(driver, 120);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		Thread.sleep(4000);
		waitForLoad(driver, 120);
	}

	// added method for tc 4
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

	// Search Scenario pop box enter value and select the Scenario
	// method for tc 6
	public void WIAaddscenarioSearchtab(WebDriver driver, String viewName, String ReportName, String Verify2,
			String Verify3, String Verify) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		String scenarioName = Verify3sp[1] + "-" + sdf.format(timestamp);
		waitForElementPresent(driver, 120, searchinput);
		click(driver, searchinput);
		Thread.sleep(2000);
		setText(driver, searchinput, scenarioName);
		waitForElementPresent(driver, 120, searchbutton);
		click(driver, searchbutton);
		Thread.sleep(2000);
		String SearchInputtable = "//div[text()='" + Verify2 + "']//following::tr";
		WebPageElements searchinputtable = new WebPageElements(" Search Input table", "xpath", SearchInputtable);
		waitForElementPresent(driver, 120, searchinputtable);
		By sort = By.xpath("//div[text()='" + Verify2 + "']//following::span[contains(@class,'filterswitch')]");
		click(driver, sort, "by drilling down to " + Verify2 + " and click on Sort/Filters");
		click(driver, sortscenariosearchresultnamebbk);
		Thread.sleep(2000);
		setText(driver, sortscenariosearchresultnamebbk, scenarioName);
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

	// Tc 07 click Edit Icon
	public void Scenarioediticon(WebDriver driver, String Verify) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		By ele1 = By.xpath("(//label[text()='" + Verifysp[0]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-edit '])[1]");
		waitForElementPresent(driver, 120, ele1, " Edit icon for " + Verifysp[0]);
		click(driver, ele1, Verifysp[0] + " Edit icon");
		Thread.sleep(2000);

	}

	// Verify table content for Scenario
	public void Scenariotablecontent(WebDriver driver, String Verify, String actualViews) throws InterruptedException {
		String[] actualViewsp = actualViews.split("\\,");
		List<String> views2 = new ArrayList<>();
		// int size1 = driver.findElements(By.xpath(ExposureSUMMARYFIELD)).size();
		List<WebElement> options2 = driver.findElements(By.xpath(
				"(//div[contains(@class,'x-container inline-filter')])[1]//preceding::div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']"));
		for (WebElement ele : options2) {
			views2.add(ele.getAttribute(("data-qtip")));
		}
		System.out.println(views2);

		for (int i1 = 0; i1 < actualViewsp.length; i1++) {

			if (views2.get(i1).equals(actualViewsp[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + views2.get(i1) + " under table row content for " + Verify);
				Reporter.log("Successfully displayed " + views2.get(i1) + " under table row content for " + Verify);
			} else {
				Add_Log.info("Unable to display " + actualViewsp[i1].trim().toString() + " under table row content for "
						+ Verify);
				Reporter.log("Unable to display " + actualViewsp[i1].trim().toString() + " under table row content for "
						+ Verify);
				TestResultStatus.mpaskeysnew.put(methodName(), "Unable to display " + actualViewsp[i1].trim().toString()
						+ " under table row content for " + Verify);
				Assert.fail();
			}
		}
	}

	// Create new Exposure
	public void ExpsouseCreate(WebDriver driver, String actualViews, String InputactualViews, String Verify,
			String Verify2) throws InterruptedException {
		String[] actualViewsp = actualViews.split("\\,");
		String[] InputactualViewsp = InputactualViews.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		List<String> views = new ArrayList<>();
		List<WebElement> options2 = null;
		if (Verify2sp[1].equals("CREATE EXPOSURE")) {
			options2 = driver.findElements(By.xpath("//div[text()='" + Verify2sp[1]
					+ "']//following::label[contains(@class,'x-form-item-label x-form-item-label-default  x-form-item-label-top x-unselectable')] | //div[@class='x-title x-btn-group-header-title x-btn-group-header-title-default x-box-item x-title-default x-title-rotate-none x-title-align-left']"));
		}
		if (Verify2sp[1].equals("CREATE COLLATERAL")) {
			options2 = driver.findElements(By.xpath("//div[text()='" + Verify2sp[1]
					+ "']//following::label[contains(@class,'x-form-item-label x-form-item-label-default mandatoryLabel x-form-item-label-top x-unselectable')] | //div[@class='x-title x-btn-group-header-title x-btn-group-header-title-default x-box-item x-title-default x-title-rotate-none x-title-align-left']"));
		}
		if (Verify2sp[1].equals("CREATE GUARANTEE")) {
			options2 = driver.findElements(By.xpath("//div[text()='" + Verify2sp[1]
					+ "']//following::label[contains(@class,'x-form-item-label x-form-item-label-default  x-form-item-label-top x-unselectable')]"));
		}
		System.out.println(options2);
		for (WebElement ele : options2) {
			views.add(ele.getAttribute(("textContent")));
		}

		System.out.println(views);
		for (int i1 = 0; i1 < actualViewsp.length; i1++) {
			if (views.get(i1).equals(actualViewsp[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + views.get(i1) + " under " + Verify2sp[1] + " all tabs "
						+ Verifysp[1]);
				Reporter.log("Successfully displayed " + views.get(i1) + " under " + Verify2sp[1] + " all tabs  "
						+ Verifysp[1]);
			} else {
				Add_Log.info("Unable to display " + actualViewsp[i1].trim().toString() + " under " + Verify2sp[1]
						+ " all tabs " + Verifysp[1]);
				Reporter.log("Unable to display " + actualViewsp[i1].trim().toString() + " under " + Verify2sp[1]
						+ " all tabs " + Verifysp[1]);
				TestResultStatus.mpaskeysnew.put(methodName(), "Unable to display " + actualViewsp[i1].trim().toString()
						+ " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
				Assert.fail();
			}
		}
		if (Verify2sp[1].equals("CREATE EXPOSURE")) {
			views.remove("Is Exposure Default");
			views.remove("Volcker Covered Fund Flag");
			views.remove("Asset Class");
		}
		if (Verify2sp[1].equals("CREATE COLLATERAL")) {
			views.remove("Legal Enforceability Certainty");
			views.remove("Is First Liened");
		}
		if (Verify2sp[1].equals("CREATE GUARANTEE")) {
			views.remove("Guarantors Asset Class");
		}
		System.out.println(views);
		String aggreationfilter = null;
		String inputtextsearch1 = null;

		for (int i = 0; i < views.size(); i++) {

			System.out.print(views.get(i) + " " + InputactualViewsp[i].trim().toString());
			if (views.get(i).equals("Parent Customer Id (GFPID)") || views.get(i).equals("Customer Id (GFCID)")
					|| views.get(i).equals("GAAP Amount USD") || views.get(i).equals("Mitigant Value")) {
				aggreationfilter = "(//div[text()='" + Verify2sp[1] + "']//following::span[text()='" + views.get(i)
						+ "']//following::input)[1]";
				inputtextsearch1 = "(//div[text()='" + Verify2sp[1] + "']//following::span[text()='" + views.get(i)
						+ "']//following::input)[1]";
			} else {
				aggreationfilter = "(//div[text()='" + Verify2sp[1] + "']//following::span[text()='" + views.get(i)
						+ "']//following::div[contains(@id,'trigger-picker')])[1]";
				inputtextsearch1 = "(//div[text()='" + Verify2sp[1] + "']//following::span[text()='" + views.get(i)
						+ "']//following::input)[1]";
			}

			// WebElement aggreationfilterweb =
			// driver.findElement(By.xpath(aggreationfilter));
			By aggreationfilterid = (By.xpath(aggreationfilter));
			Thread.sleep(1000);
			// wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
			click(driver, aggreationfilterid, views.get(i));
			WebPageElements inputtextfeildid = new WebPageElements(InputactualViewsp[i].trim().toString(), "xpath",
					inputtextsearch1);
			clearText(driver, inputtextfeildid);
			Thread.sleep(500);
			setTextenter(driver, inputtextfeildid, InputactualViewsp[i]);
			Thread.sleep(500);
		}

		Thread.sleep(500);
		waitForLoad(driver, 120);
		Thread.sleep(100);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		Thread.sleep(500);
	}

	// Create Expsouse Createcopy
	public void ExpsouseCreatecopy(WebDriver driver, String viewName, String ReportName, String actualViews,
			String InputactualViews, String Verify, String Verify2) throws InterruptedException {
		String[] actualViewsp = actualViews.split("\\,");
		String[] InputactualViewsp = InputactualViews.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String aggreationfilter = null;
		String inputtextsearch1 = null;
		// Click on Add button
		WIAAddbutton(driver, viewName, Verifysp[1], ReportName);
		// Header for Exposure
		WIAHeadername(driver, Verify2sp[1]);
		System.out.println(actualViewsp.length);
		System.out.println(InputactualViewsp.length);
		for (int i = 0; i < actualViewsp.length; i++) {

			System.out.print(actualViewsp[i].trim().toString() + " " + InputactualViewsp[i].trim().toString());
			if (actualViewsp[i].trim().toString().equals("Parent Customer Id (GFPID)")
					|| actualViewsp[i].trim().toString().equals("Customer Id (GFCID)")
					|| actualViewsp[i].trim().toString().equals("GAAP Amount USD")) {
				aggreationfilter = "(//div[text()='" + Verify2sp[1] + "']//following::span[text()='"
						+ actualViewsp[i].trim().toString() + "']//following::input)[1]";
				inputtextsearch1 = "(//div[text()='" + Verify2sp[1] + "']//following::span[text()='"
						+ actualViewsp[i].trim().toString() + "']//following::input)[1]";
			} else {
				aggreationfilter = "(//div[text()='" + Verify2sp[1] + "']//following::span[text()='"
						+ actualViewsp[i].trim().toString() + "']//following::div[contains(@id,'trigger-picker')])[1]";
				inputtextsearch1 = "(//div[text()='" + Verify2sp[1] + "']//following::span[text()='"
						+ actualViewsp[i].trim().toString() + "']//following::input)[1]";
			}

			WebElement aggreationfilterweb = driver.findElement(By.xpath(aggreationfilter));
			By aggreationfilterid = (By.xpath(aggreationfilter));
			WebDriverWait wait = new WebDriverWait(driver, 120);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
			click(driver, aggreationfilterid, actualViewsp[i]);
			WebPageElements inputtextfeildid = new WebPageElements(InputactualViewsp[i].trim().toString(), "xpath",
					inputtextsearch1);
			clearText(driver, inputtextfeildid);
			Thread.sleep(2000);
			setTextenter(driver, inputtextfeildid, InputactualViewsp[i]);
			Thread.sleep(1000);
		}

		Thread.sleep(1000);
		waitForLoad(driver, 120);
		Thread.sleep(1000);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		Thread.sleep(500);
	}

	// Verify Create Expsoure on landing page
	public String ExpsCreateVerify(WebDriver driver, String Verify3, String Verify, String Report)
			throws InterruptedException {
		String Sortvalue = null;
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");
		List<String> views = new ArrayList<>();
		WebElement extractxpathid = driver.findElement(
				By.xpath("//label[text()='" + Verifysp[1] + "']//following::span[text()='" + Reportsp[1] + "']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		String xpathdivtablestring = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1]
				+ "']//div";
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
			try {
				Sortvalue = views.get(0) ;
				System.out.println("Sortvalue = " + Sortvalue);
				Sortvalue = views.get(1);
				System.out.println("Sortvalue = " + Sortvalue);

				System.out.println(filterscenarioNamea);
				System.out.println(Verify3);

				if (filterscenarioNamea.isEmpty()) {
					Add_Log.info("Unable to displayed Created/Sort " + Verifysp[1]);
					Reporter.log("Unable to displayed Created/Sort " + Verifysp[1]);
					TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Created " + Verifysp[1]);
					Assert.fail();
				} else {
					Add_Log.info("Successfully displayed Created/Sort " + Verifysp[1] + " = " + views);
					Reporter.log("Successfully displayed Created/Sort " + Verifysp[1] + " = " + views);

				}
			}

			catch (Exception e1) {
				Sortvalue = views.get(0) ;
				System.out.println("Sortvalue = " + Sortvalue);
				Add_Log.info("Successfully displayed Created/Sort " + Verifysp[1] + " = " + views);
				Reporter.log("Successfully displayed Created/Sort " + Verifysp[1] + " = " + views);
				
			}

		}
		return Sortvalue;
	}

	// Verify Delete Expsoure on landing page
	public void ExpsoureDeleteicon(WebDriver driver, String Verify, String Report) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");
		waitForLoad(driver, 120);
		By ele1 = null;
		By ele2 = null;
		if (Verifysp[1].equals("Exposure")) {
			ele1 = By.xpath(
					"(//div[@class='x-column-header x-column-header-checkbox x-column-header-align-left x-box-item x-column-header-coreselectcolumn x-unselectable x-column-header-first'])[1]");
			ele2 = By.xpath("(//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-trash '])[1]");
		}
		if (Verifysp[1].equals("Collateral")) {
			ele1 = By.xpath(
					"(//div[@class='x-column-header x-column-header-checkbox x-column-header-align-left x-box-item x-column-header-coreselectcolumn x-unselectable x-column-header-first'])[2]");
			ele2 = By.xpath("(//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-trash '])[2]");
		}
		if (Verifysp[1].equals("Guarantee")) {
			ele1 = By.xpath(
					"//label[text()='Guarantee']//following::div[@class='x-column-header x-column-header-checkbox x-column-header-align-left x-box-item x-column-header-coreselectcolumn x-unselectable x-column-header-first']");
			ele2 = By.xpath(
					"//label[text()='Guarantee']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-trash ']");
		}
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

	//////////////////////////////////////////////////////////////////////////////////////
	// TC001 // TC 33
	@SuppressWarnings("unlikely-arg-type")
	public void WIAaddscenarioSearch(WebDriver driver, String viewName, String ReportName, String Verify2,
			String Verify3, String Verify, String Report, String Verify4, String actualViews, String InputactualViews,
			String reportingPeriod) throws InterruptedException {

		// Verify2[2] = CLONE COLLATERAL
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		CapitalWhtif(driver, viewName, ReportName, Verify);
		// click on Add button for Scenario
		WIAAddbutton(driver, viewName, Verify, ReportName);
		// Header for SCENARIO
		WIAHeadername(driver, Verify2sp[1]);
		// Add name and Description for Scenario
		WIAaddScenariopop(driver, Verify3, Verify);
		// displayed Created scenario Name
		WIAscenarioverfiy(driver, viewName, Verify3, Verify, Reportsp[0]);
		// click Scenario Search Facility
		WIASearchFacility(driver, viewName, Verifysp[0], ReportName);
		// Header for Search
		WIAHeadername(driver, Verify2sp[0]);
		// if error
		Snackbar(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// COB select
		cobselect(driver, reportingPeriod);
		// Enter GFRN and Search / and add

		EnterGFRNandSearch(driver, Verify2, Verify4, Verify);

		// Added retry due to uneven data visibility
		boolean success = false;
		int count = 0;
		int maxTries = 8;
		while (!success) {
			try {
				Add_Log.info("Try 1");
				Reporter.log("Try 1");
				// displayed Created GFRN- Exposure Key
				WIAExposureverfiy(driver, Verifysp[1], Reportsp[1], Verify4);
				success = true;
				break;
			} catch (Exception e1) {
				Add_Log.info("Catach 1");
				Reporter.log("Catach 1");
				// click Scenario Search Facility
				WIASearchFacility(driver, viewName, Verifysp[0], ReportName);
				// Header for Search
				WIAHeadername(driver, Verify2sp[0]);
				// if error
				Snackbar(driver);
				Thread.sleep(2000);
				waitForLoad(driver, 900);
				// COB select
				cobselect(driver, reportingPeriod);
				// Enter GFRN and Search / and add
				EnterGFRNandSearch(driver, Verify2, Verify4, Verify);
				// displayed Created GFRN- Exposure Key
				WIAExposureverfiy(driver, Verifysp[1], Reportsp[1], Verify4);
				success = true;
				if (++count == maxTries)
					throw e1;
			}
		}
		// select 1st row radio button for Exposure key
		RadiobuttonExposurekey(driver);
		// select 1st row radio button for Collateral key
		RadiobuttonCollateralkey(driver);
		// click on clone
		waitForElementPresent1(driver, 120, cloneiconcoll);
		click(driver, cloneiconcoll);
		// verify clone header
		WIAHeadername(driver, Verify2sp[2]);
		// Enter value in Clone Coll for Tc1
		WIACloneColl(driver, Verify, Verify2, actualViews, InputactualViews);
		// displayed Created Collateral Mitigant Key
		WIACollMitigantKey(driver, Verifysp[2], Reportsp[2]);
		// select 1st row radio button for Collateral key
		RadiobuttonCollateralkey(driver);
		// CLick on Eligibility Check Submit icon and verify
		EligibilityCheckSubmiticonverify(driver);
		// Click on Eligibility calculator
		eligibilitycalculator(driver);
		// Verify value display under Summary,Exposure, Mitigant
		VerifyEligibilitySME(driver);

	}

	// TC 03
	public boolean AggregationCheckcommon(WebDriver driver, String viewName, String actualViews, String ReportName,
			String Verify) throws InterruptedException {

		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		List<String> views = new ArrayList<>();
		CapitalWhtif(driver, viewName, ReportName, Verify);
		int size = actualViewsp.length;
		Thread.sleep(500);
		for (int i = 1; i <= size; i++) {
			views.add(getText(driver, By.xpath(
					"//span[text()='" + ReportName + "']/ancestor::table//following-sibling::table[" + i + "]//span"),
					" Filter criteria #" + i));
		}
		for (int i = 0; i < actualViewsp.length; i++) {
			if (views.get(i).equals(actualViewsp[i].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp[i].trim().toString()
						+ " aggregation after drilling down for " + ReportName);
				Reporter.log("Successfully displayed " + actualViewsp[i].trim().toString()
						+ " aggregation after drilling down for " + ReportName);
			} else {
				Add_Log.info(actualViewsp[i].trim().toString()
						+ " aggregation is not displayed after drilling down for " + ReportName);
				Reporter.log(actualViewsp[i].trim().toString()
						+ " aggregation is not displayed after drilling down for  " + ReportName);
				TestResultStatus.mpaskeysnew.put(methodName(), actualViewsp[i].trim().toString()
						+ " aggregation is not displayed after drilling down for  " + ReportName);
				flag = true;
			}
		}
		if (flag) {
			TestResultStatus.Testfail = true;
			allFlag = true;
			Assert.fail();
		} else {
			Add_Log.info("Successfully all aggregations are displayed after drilling down " + ReportName);
			Reporter.log("Successfully all aggregations are displayed after drilling down " + ReportName);
		}
		return allFlag;
	}

	// TC 04
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

	// Tc05 old but re use in other method
	public void WIAaddscenarioCreatenew(WebDriver driver, String viewName, String ReportName, String Verify2,
			String Verify3, String Verify, String Report) throws InterruptedException {

		// CapitalWhtif added in Main method to re-use this code
		// click on Add button for Scenario
		String[] Reportsp = Report.split("\\,");
		WIAAddbutton(driver, viewName, Verify, ReportName);
		// Header for SCENARIO
		WIAHeadername(driver, Verify2);
		// Add name and Description for Scenario
		WIAaddScenariopop(driver, Verify3, Verify);
		// displayed Created scenario Name
		WIAscenarioverfiy(driver, viewName, Verify3, Verify, Reportsp[0]);
		/*
		 * // Get Scenario id String id2 =Getscenarioid(driver, Verify, Reportsp[1]);
		 * System.out.println(id2); return id2;
		 */
	}

	// Tc05
	public void WIAaddscenarioCreatetodaydate(WebDriver driver, String viewName, String ReportName, String Verify2,
			String Verify3, String Verify, String Report) throws InterruptedException {

		// CapitalWhtif added in Main method to re-use this code
		// click on Add button for Scenario
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		WIAAddbutton(driver, viewName, Verify, ReportName);
		// Header for SCENARIO
		WIAHeadername(driver, Verify2sp[0]);
		// Add name and Description for Scenario
		WIAaddScenariopoptodaydate(driver, Verify3, Verify);
		// displayed Created scenario Name
		WIAscenarioverfiytodaydate(driver, viewName, Verify3, Verify, Reportsp[0]);
		/*
		 * // Get Scenario id String id2 =Getscenarioid(driver, Verify, Reportsp[1]);
		 * System.out.println(id2); return id2;
		 */
	}

	// Tc06
	public void WIAaddscenarioSearch(WebDriver driver, String viewName, String ReportName, String Verify2,
			String Verify3, String Verify, String Report) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		// CapitalWhtif added in Main method to re-use this code
		// click on Search icon for Scenario
		WIASearchicon(driver, viewName, Verify, ReportName);
		// Header for SCENARIO
		WIAHeadername(driver, Verify2sp[1]);
		// Search Scenario pop box enter value and select the Scenario
		WIAaddscenarioSearchtab(driver, viewName, ReportName, Verify2sp[1], Verify3, Verify);
		// displayed Created / Search scenario Name
		WIAscenarioverfiytodaydate(driver, viewName, Verify3, Verify, Reportsp[0]);
	}

	// TC07

	public void EditScenario(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Verify4, String Report) throws InterruptedException {
		String[] Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] Reportsp = Report.split("\\,");
		CapitalWhtif(driver, viewName, ReportName, Verify);
		// Tc05 Create Scenario 1
		WIAaddscenarioCreatenew(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify, Reportsp[0]);
		// Tc 07 click Edit Icon
		Scenarioediticon(driver, Verify);
		// Header for SCENARIO
		WIAHeadername(driver, Verify2sp[1]);
		// String add current date
		String scenarioName = Verify3sp[0] + "-" + sdf.format(timestamp);
		// Tc 07 Edit name and Description for Scenario
		EditScenariopop(driver, Verify3);
		// displayed Created / Search scenario Description
		WIAscenarioverfiy(driver, viewName, scenarioName, Verify, Reportsp[1]);
	}

	// TC08
	public void ScenarioSearchfacilty(WebDriver driver, String viewName, String ReportName, String Verify2,
			String Verify3, String Verify, String Verify4, String Report, String reportingPeriod)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		CapitalWhtif(driver, viewName, ReportName, Verify);
		// click on Add button for Scenario
		WIAAddbutton(driver, viewName, Verify, ReportName);
		// Header for SCENARIO
		WIAHeadername(driver, Verify2sp[1]);
		// Add name and Description for Scenario
		WIAaddScenariopop(driver, Verify3, Verify);
		// displayed Created scenario Name
		WIAscenarioverfiy(driver, viewName, Verify3, Verify, Reportsp[0]);
		// click Scenario Search Facility
		WIASearchFacility(driver, viewName, Verifysp[0], ReportName);
		// Header for Search
		WIAHeadername(driver, Verify2sp[0]);
		// if error
		Snackbar(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// COB select
		cobselect(driver, reportingPeriod);
		// Enter GFRN and Search / and add
		EnterGFRNandSearchAssert(driver, Verify2, Verify4, Verify);
		// displayed Created GFRN- Exposure Key
		WIAExposureverfiy(driver, Verifysp[1], Reportsp[1], Verify4);
	}

	// TC 09
	public void ScenarioSortfilter(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String actualViews) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		CapitalWhtif(driver, viewName, ReportName, Verify);
		// Tc05 old Create Scenario
		WIAaddscenarioCreatenew(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify, Reportsp[0]);
		// Verify table content for Scenario
		Scenariotablecontent(driver, Verify, actualViews);
		// click on Sort icon
		String sortclick = "(//label[text()='" + Verifysp[0]
				+ "']//following::span[contains(@class,'filterswitch')])[1]";
		By sort = By.xpath(sortclick);
		waitForElementPresent(driver, 120, sort, " " + Verifysp[0] + " and click on Sort/Filters");
		click(driver, sort, "by drilling down to " + Verifysp[0] + " and click on Sort/Filters");
		Thread.sleep(2000);
		// Added code after sort is visible.
		Add_Log.info("Code after sort is visible in UI");
		Reporter.log("Code after sort is visible in UI");
		TestResultStatus.mpaskeysnew.put(methodName(), "Code after sort is visible in UI");
		Assert.fail();
	}

	// TC 10 and TC 17 TC24
	public void CreateExposure(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String actualViews, String InputactualViews) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		// CapitalWhtif added in Main method to re-use this code
		// Tc05old Create Scenario
		WIAaddscenarioCreatenew(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify, Reportsp[0]);
		// click on Add button for Exposure
		WIAAddbutton(driver, viewName, Verifysp[1], ReportName);
		// Header for Exposure
		WIAHeadername(driver, Verify2sp[1]);
		// Header for Exposure
		ExpsouseCreate(driver, actualViews, InputactualViews, Verify, Verify2);
		// Verify Create Expsoure on landing page
		ExpsCreateVerify(driver, Verify3, Verify, Report);
	}

	// TC 11 and TC 18 TC 25
	public void ExposureDelete(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		// Tc05old Create Scenario
		WIAaddscenarioCreatenew(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify, Reportsp[0]);
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
		// Enter GFRN and Search / and add
		EnterGFRNandSearch(driver, Verify2, Verify4, Verify);
		// Verify Create Expsoure on landing page
		// Added retry due to uneven data visibility
		boolean success = false;
		int count = 0;
		int maxTries = 8;
		while (!success) {
			try {
				ExpsCreateVerify(driver, Verify3, Verify, Report);
				success = true;
				break;
			} catch (Exception e1) {
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
				// Enter GFRN and Search / and add
				EnterGFRNandSearch(driver, Verify2, Verify4, Verify);
				// Verify Create Expsoure on landing page
				ExpsCreateVerify(driver, Verify3, Verify, Report);
				success = true;
				if (++count == maxTries)
					throw e1;
			}
		}
		// Delete Exposure icon
		ExpsoureDeleteicon(driver, Verify, Report);
	}

	// TC012 and TC 19 / TC 26
	public void ExposureDownload(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod, String Fileformat, String FileNameac)
			throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";

		// Tc05old Create Scenario
		WIAaddscenarioCreatenew(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify, Reportsp[0]);
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
		// Enter GFRN and Search / and add
		EnterGFRNandSearch(driver, Verify2, Verify4, Verify);
		// Verify Create Expsoure on landing page
		boolean success = false;
		int count = 0;
		int maxTries = 8;
		while (!success) {
			try {
				ExpsCreateVerify(driver, Verify3, Verify, Report);
				success = true;
				break;
			} catch (Exception e1) {
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
				// Enter GFRN and Search / and add
				EnterGFRNandSearch(driver, Verify2, Verify4, Verify);
				// Verify Create Expsoure on landing page
				ExpsCreateVerify(driver, Verify3, Verify, Report);
				success = true;
				if (++count == maxTries)
					throw e1;
			}
		}
		// click on download icon
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By ele1 = By.xpath("(//label[text()='" + Verifysp[1]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-excel '])[1]");
		waitForElementPresent(driver, 120, ele1, " Download icon" + Verifysp[1]);
		clicknosleep(driver, ele1, " Download icon " + Verifysp[1]);
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
	}

	// TC013 and TC 20 / TC 27
	public void ExposureDownloadSelect(WebDriver driver, String viewName, String ReportName, String Verify2,
			String Verify3, String Verify, String Report, String Verify4, String reportingPeriod, String Fileformat,
			String FileNameac) throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";

		// Tc05old Create Scenario
		WIAaddscenarioCreatenew(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify, Reportsp[0]);
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
		// Enter GFRN and Search / and add
		EnterGFRNandSearch(driver, Verify2, Verify4, Verify);
		// Verify Create Expsoure on landing page
		boolean success = false;
		int count = 0;
		int maxTries = 8;
		while (!success) {
			try {
				ExpsCreateVerify(driver, Verify3, Verify, Report);
				success = true;
				break;
			} catch (Exception e1) {
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
				// Enter GFRN and Search / and add
				EnterGFRNandSearch(driver, Verify2, Verify4, Verify);
				// Verify Create Expsoure on landing page
				ExpsCreateVerify(driver, Verify3, Verify, Report);
				success = true;
				if (++count == maxTries)
					throw e1;
			}
		}
		// click on download icon
		Thread.sleep(2000);
		waitForLoad(driver, 120);

		By ele1 = By.xpath("(//label[text()='" + Verifysp[1]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-download '])[1]");
		waitForElementPresent(driver, 120, ele1, "Download Selected Exposures" + Verifysp[1]);
		clicknosleep(driver, ele1, "Download Selected Exposures" + Verifysp[1]);
		waitForLoad(driver, 900);
		By closepop = By.xpath("//span[text()='OK']");
		click(driver, closepop, " click ok on popup meeasge");
		WebElement closewindow = driver.findElement(closepop);
		if (closewindow.isDisplayed()) {
			click(driver, closepop, " click ok on popup meeasge");
		} else {
			Reporter.log("Able to download Expsoure file without selecting Expsoure key using select download icon");
			Add_Log.info("Able to download Expsoure file without selecting Expsoure key using select download icon");
			TestResultStatus.mpaskeysnew.put(methodName(),
					"Able to download Expsoure file without selecting Expsoure key using select download icon");
			Assert.fail();
		}

		By ele2 = By.xpath("(//label[text()='" + Verifysp[1] + "']//following::div[@class='x-grid-row-checker'])[1]");
		waitForElementPresent(driver, 120, ele2, " Check box for 1st Key " + Verifysp[1]);
		click(driver, ele2, "  Check box for 1st Key " + Verifysp[1]);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By ele3 = By.xpath("(//label[text()='" + Verifysp[1]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-download '])[1]");
		waitForElementPresent(driver, 120, ele3, " Download icon" + Verifysp[1]);
		clicknosleep(driver, ele3, " Download icon " + Verifysp[1]);
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
	}

	// TC014 and TC 21 / TC 28
	public void ExposureSortFilter(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod, String actualViews)
			throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] Verify4p = Verify4.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		// Tc05old Create Scenario
		WIAaddscenarioCreatenew(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify, Reportsp[0]);
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
		// Enter GFRN and Search / and add
		EnterGFRNandSearch(driver, Verify2, Verify4p[0], Verify);
		// Verify Create Expsoure on landing page
		String sortvalue = null;
		boolean success = false;
		int count = 0;
		int maxTries = 8;
		while (!success) {
			try {
				sortvalue = ExpsCreateVerify(driver, Verify3, Verify, Report);
				success = true;
				break;
			} catch (Exception e1) {
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
				// Enter GFRN and Search / and add
				EnterGFRNandSearch(driver, Verify2, Verify4p[0], Verify);
				// Verify Create Expsoure on landing page
				sortvalue = ExpsCreateVerify(driver, Verify3, Verify, Report);
				success = true;
				if (++count == maxTries)
					throw e1;
			}
		}
		// Verify table content
		String ExposureSUMMARYFIELD = "//label[text()='" + Verifysp[1]
				+ "']//following::div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']";
		WebPageElements exposuresummaryfield = new WebPageElements("Exposure Summary filter", "xpath",
				ExposureSUMMARYFIELD);
		List<String> views = new ArrayList<>();
		waitForElementPresent(driver, 30, exposuresummaryfield);
		for (int i = 1; i < actualViewsp.length + 1; i++) {
			views.add(getatt(driver, By.xpath("(" + ExposureSUMMARYFIELD + ")[" + i + "]"),
					"  Exposure  table Feild Content #" + i));
		}
		System.out.println(views);
		for (int i1 = 0; i1 < actualViewsp.length; i1++) {
			if (views.get(i1).equals(actualViewsp[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp[i1].trim().toString() + "= " + Verifysp[1]
						+ " table content");
				Reporter.log("Successfully displayed " + actualViewsp[i1].trim().toString() + "= " + Verifysp[1]
						+ " table content");

			} else {
				Add_Log.info("Unable to displayed " + actualViewsp[i1].trim().toString() + "= " + Verifysp[1]
						+ " table content");
				Reporter.log("Unable to displayed " + actualViewsp[i1].trim().toString() + "= " + Verifysp[1]
						+ " table content");
				TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed "
						+ actualViewsp[i1].trim().toString() + "= " + Verifysp[1] + " table content");
			}
		}

		// click on Sort Filter
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		String sortclick = "//label[text()='" + Verifysp[1] + "']//following::span[contains(@class,'filterswitch')]";
		By sort = By.xpath(sortclick);
		click(driver, sort, "by drilling down to " + Verifysp[1] + " and click on Sort/Filters");
		Thread.sleep(2000);
		String filterinput = "(//span[text()='" + Reportsp[1]
				+ "']//following::input[@class='operatorValueFieldCls x-form-text x-form-text-default  '])[2]";
		By filter = By.xpath(filterinput);
		WebPageElements filterinput_dll = new WebPageElements("Sort/filiter", "xpath", filterinput);
		Thread.sleep(2000);
		setTextenter(driver, filterinput_dll, sortvalue);
		Thread.sleep(2000);
		waitForLoad(driver, 100);
		// Verify Create Expsoure on landing page // Sort same
		ExpsCreateVerify(driver, Verify3, Verify, Report);
	}

	// Enter Facilty and Search / and add

	public void FaciltySearchvalue(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod)
			throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] Verify4p = Verify4.split("\\,");
		String sort2 = null;
		By sort21 = null;
		String sort1 = null;
		By sort = null;
		try {
			// Enter value in Search Expsoure
			sort1 = "(//*[text()='SEARCH']//following::span[text()='" + Reportsp[1]
					+ "'])[1]//following::input[@name='searchInput']";
			sort = By.xpath(sort1);
			Thread.sleep(2000);
			WebPageElements sort_dll = new WebPageElements(Verify2sp[1], "xpath", sort1);
			WebElement sort_dlle = driver.findElement(sort);
			click(driver, sort, "by drilling down to " + Verify2sp[1] + " and click " + Reportsp[1] + " Search");
			Thread.sleep(2000);
			waitForLoad(driver, 900);
			Thread.sleep(2000);
			setTextenter(driver, sort_dll, Verify4);
			Thread.sleep(2000);
			waitForLoad(driver, 900);
			waitForLoad(driver, 900);
			By eletext = By.xpath("//font[text()='" + Verify4 + "'] | //li[text()='" + Verify4 + "']");
			waitForVisiblilitynoAssert(driver, 30, eletext, Verify4);
			Thread.sleep(2000);
			clicknoassert(driver, eletext, Verify4);
			Thread.sleep(2000);
			waitForLoad(driver, 900);
			Thread.sleep(2000);
			waitForLoad(driver, 900);

			WebPageElements sort2_dll = null;

			if (Verifysp[1].equals("Exposure")) {
				sort2 = "(//label[text()='EXPOSURE SEARCH RESULT']//following::td)[2]//div";
				sort2_dll = new WebPageElements("EXPOSURE SEARCH RESULT", "xpath", sort2);
			}
			if (Verifysp[1].equals("Collateral")) {
				sort2 = "(//label[text()='COLLATERAL SEARCH RESULT']//following::td)[2]//div";
				sort2_dll = new WebPageElements("COLLATERAL SEARCH RESULT", "xpath", sort2);
			}
			if (Verifysp[1].equals("Guarantee")) {
				sort2 = "(//label[text()='GUARANTEE SEARCH RESULT']//following::td)[2]//div";
				sort2_dll = new WebPageElements("COLLATERAL SEARCH RESULT", "xpath", sort2);
			}
			sort21 = By.xpath(sort2);
			Thread.sleep(2000);

			waitForElementPresent_noAs(driver, 30, sort2_dll);
			WebElement sort2_dlle = driver.findElement(sort21);
			String valuetext = sort2_dlle.getText();
			System.out.println(valuetext);
			if (Verify4.contains(valuetext)) {
				Add_Log.info("Successfully " + Reportsp[1] + " " + Verify4 + " selected");
				Reporter.log("Successfully " + Reportsp[1] + " " + Verify4 + " selected");
			} else {
				Add_Log.info("Unable " + Reportsp[1] + " " + Verify4 + " selected");
				Reporter.log("Unable " + Reportsp[1] + " " + Verify4 + " selected");
				TestResultStatus.mpaskeysnew.put(methodName(), "Unable " + Reportsp[1] + " " + Verify4 + " selected");
				Assert.fail();
			}

			Thread.sleep(2000);
			By checkbox = By.xpath("//div[@class='x-grid-row-checker']");
			click(driver, checkbox, "Grid row Checker box");
			Thread.sleep(2000);
			waitForLoad(driver, 900);
			Thread.sleep(2000);
		} catch (Exception e1) {

		}
		waitForLoad(driver, 900);
		click(driver, add_btninside);

		if (viewName.contains("Banking Book")) {
			if (Verifysp[1].equals("Guarantee")) {
				By clickguarantee = By.xpath("//span[text()='Guarantee']");
				click(driver, clickguarantee, "Expand Secondary Header Guarantee");
				Thread.sleep(2000);
				waitForLoad(driver, 900);
			}
		}

	}

	// Tc015 and16 TC 22 and TC 29 30
	public void ExposureSearch(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod)
			throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] Verify4p = Verify4.split("\\,");
		String sort2 = null;
		By sort21 = null;
		String sort1 = null;
		By sort = null;

		// click Scenario Search Facility
		WIASearchExposure(driver, viewName, Verifysp[1], ReportName);
		// Header for Search
		WIAHeadername(driver, Verify2sp[1]);
		// COB select
		cobselect(driver, reportingPeriod);
		Thread.sleep(1000);
		// Enter value in Search Expsoure
		FaciltySearchvalue(driver, viewName, ReportName, Verify2, Verify3, Verify, Report, Verify4, reportingPeriod);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		// Verify Create Expsoure on landing page // Sort same
		// Added retry due to uneven data visibility
		boolean success = false;
		int count = 0;
		int maxTries = 8;
		while (!success) {
			try {
				Add_Log.info("Try 1");
				Reporter.log("Try 1");
				ExpsCreateVerify(driver, Verify3, Verify, Report);
				success = true;
				break;
			} catch (Exception e1) {
				Add_Log.info("Catch 1");
				Reporter.log("Catch 1");
				// click Scenario Search Facility
				WIASearchExposure(driver, viewName, Verifysp[1], ReportName);
				// Header for Search
				WIAHeadername(driver, Verify2sp[1]);
				// COB select
				cobselect(driver, reportingPeriod);
				Thread.sleep(1000);
				// Enter value in Search Expsoure
				FaciltySearchvalue(driver, viewName, ReportName, Verify2, Verify3, Verify, Report, Verify4,
						reportingPeriod);
				Thread.sleep(2000);
				waitForLoad(driver, 900);
				waitForLoad(driver, 900);
				ExpsCreateVerify(driver, Verify3, Verify, Report);
				success = true;
				if (++count == maxTries)
					throw e1;
				
			}
		}
	}

	// TC016 and TC23 / Tc 30
	public void ExposureCloneEdit(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
			String Verify, String Report, String Verify4, String reportingPeriod, String actualViews,
			String InputactualViews) throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		// Copy all steps from TC15 in main method
		// Click on Clone
		Thread.sleep(2000);
		waitForElementPresent1(driver, 120, cloneiconexp);
		click(driver, cloneiconexp);
		// verify clone header
		WIAHeadername(driver, Verify2sp[2]);
		// Enter value for Clone Exposure
		String aggreationfilter = null;
		String inputtextsearch1 = null;
		WebPageElements inputtextfeildid = null;
		aggreationfilter = "(//div[text()='" + Verify2sp[2] + "']//following::span[text()='" + actualViews
				+ "']//following::div[contains(@id,'trigger-picker')])[1]";
		inputtextsearch1 = "(//div[text()='" + Verify2sp[2] + "']//following::span[text()='" + actualViews
				+ "']//following::input)[1]";
		Thread.sleep(1000);
		inputtextfeildid = new WebPageElements(InputactualViews, "xpath", inputtextsearch1);
		clearText(driver, inputtextfeildid);
		Thread.sleep(1000);
		setTextenter(driver, inputtextfeildid, InputactualViews);
		Thread.sleep(1000);
		waitForLoad(driver, 120);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		Thread.sleep(4000);
		waitForLoad(driver, 120);
		// Verified Edit Value in landing Page
		WebElement extractxpathid = null;
		if (Verifysp[1].equals("Exposure")) {
			extractxpathid = driver
					.findElement(By.xpath("//label[text()='" + Verifysp[1] + "']//following::span[text()='Type']"));
		}
		if (Verifysp[1].equals("Collateral")) {
			extractxpathid = driver.findElement(
					By.xpath("//label[text()='" + Verifysp[1] + "']//following::span[text()='Asset Class']"));
		}
		if (Verifysp[1].equals("Guarantee")) {
			extractxpathid = driver.findElement(By.xpath(
					"//label[text()='" + Verifysp[1] + "']//following::span[text()='Regulatory Segment Description']"));
		}
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		String xpathdivtablestring = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1]
				+ "']//div";
		String filterscenarioNamea = null;
		By filter = By.xpath(xpathdivtablestring);
		WebElement filterscenarioName = driver.findElement(filter);
		filterscenarioNamea = filterscenarioName.getAttribute("textContent");
		System.out.println(filterscenarioNamea);
		System.out.println(Verify3);

		if (InputactualViews.contains(filterscenarioNamea)) {
			Add_Log.info("Successfully displayed Clone/Edit " + Verify2sp[2] + " = " + InputactualViews);
			Reporter.log("Successfully displayed Clone/Edit " + Verify2sp[2] + " = " + InputactualViews);

		} else {
			Add_Log.info("Unable to displayed Clone/Edit " + Verify2sp[2] + " = " + InputactualViews);
			Reporter.log("Unable to displayed Clone/Edit " + Verify2sp[2] + " = " + InputactualViews);
			TestResultStatus.mpaskeysnew.put(methodName(),
					"Unable to displayed Clone/Edit " + Verify2sp[2] + " = " + InputactualViews);
			Assert.fail();
		}

	}

	// TC032
	@SuppressWarnings("unlikely-arg-type")
	public void EligibilityCheckError(WebDriver driver, String viewName, String ReportName, String Verify2,
			String Verify3, String Verify, String Report, String Verify4, String actualViews, String InputactualViews,
			String reportingPeriod) throws InterruptedException {
		// Verify2[2] = CLONE COLLATERAL
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		CapitalWhtif(driver, viewName, ReportName, Verify);
		// click on Add button for Scenario
		WIAAddbutton(driver, viewName, Verify, ReportName);
		// Header for SCENARIO
		WIAHeadername(driver, Verify2sp[1]);
		// Add name and Description for Scenario
		WIAaddScenariopop(driver, Verify3, Verify);
		// displayed Created scenario Name
		WIAscenarioverfiy(driver, viewName, Verify3, Verify, Reportsp[0]);
		// click Scenario Search Facility
		WIASearchFacility(driver, viewName, Verifysp[0], ReportName);
		// Header for Search
		WIAHeadername(driver, Verify2sp[0]);
		// if error
		Snackbar(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// COB select
		cobselect(driver, reportingPeriod);
		// Enter GFRN and Search / and add
		EnterGFRNandSearch(driver, Verify2, Verify4, Verify);
		// displayed Created GFRN- Exposure Key
		boolean success = false;
		int count = 0;
		int maxTries = 8;
		while (!success) {
			try {
				WIAExposureverfiy(driver, Verifysp[1], Reportsp[1], Verify4);
				success = true;
				break;
			} catch (Exception e1) {
				// click Scenario Search Facility
				WIASearchFacility(driver, viewName, Verifysp[0], ReportName);
				// Header for Search
				WIAHeadername(driver, Verify2sp[0]);
				// if error
				Snackbar(driver);
				Thread.sleep(2000);
				waitForLoad(driver, 900);
				// COB select
				cobselect(driver, reportingPeriod);
				// Enter GFRN and Search / and add
				EnterGFRNandSearch(driver, Verify2, Verify4, Verify);
				// displayed Created GFRN- Exposure Key
				WIAExposureverfiy(driver, Verifysp[1], Reportsp[1], Verify4);
				if (++count == maxTries)
					throw e1;
			}
		}

		// select 1st row radio button for Exposure key
		RadiobuttonExposurekey(driver);
		// select 1st row radio button for Collateral key
		RadiobuttonCollateralkey(driver);
		// Click on Eligibility submit
		By ele1 = By.xpath(
				"//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small hasIcon icon icon-check-mark-submit ']");
		waitForElementPresent(driver, 120, ele1, " Sumbit icon ");
		click(driver, ele1, "by drilling down to  Eligibility Check Submit icon");
		// Verify value display under Summary,Exposure, Mitigant
		VerifyEligibilityERROR(driver);
	}

	// TC 33
	@SuppressWarnings("unlikely-arg-type")
	public void EligibilityCheckdownload(WebDriver driver, String viewName, String ReportName, String Verify2,
			String Verify3, String Verify, String Report, String Verify4, String actualViews, String InputactualViews,
			String reportingPeriod, String Fileformat, String FileNameac) throws InterruptedException, IOException {
		// TC 01 added in man method
		By expsouretab = By.xpath(
				("(//span[@class='x-tab-icon-el x-tab-icon-el-secondary-tabs-elements  ']//following::span[text()='Eligibility Check'])[1]"));
		click(driver, expsouretab, "Expsoure tab");
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By ele1 = By.xpath(
				"(//label[text()='Eligibility Check']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-excel '])[1]");
		waitForElementPresent(driver, 120, ele1, " Download icon Eligibility Check");
		clicknosleep(driver, ele1, " Download icon Eligibility Check");
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
	}
}

