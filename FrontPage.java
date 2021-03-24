package pageobjects;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import org.apache.tools.ant.taskdefs.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import property.IHomePage;
import testsuitebase.TestResultStatus;
import utility.SeleniumUtils;
import utility.WebPageElements;

public class FrontPage extends SeleniumUtils implements IHomePage {
	public DecimalFormat df = new DecimalFormat("#.##");
	public String testName = null;
	public boolean Testskip = false;
	public boolean Testfail = false;
	boolean allFlag24 = false;
	boolean allFlag25 = false;
	boolean allFlag26 = false;
	boolean allFlag = false;
	public double finish, start;
	public double end;
	
	public List<String> failedtest = new ArrayList<String>();
	public SoftAssert softAssert = new SoftAssert();
	
	public void clicktable(WebDriver driver,String actualViews) throws InterruptedException {

		String[] actualViewsp = actualViews.split(",");
		WebElement extractxpathid = driver.findElement(By.xpath(
				"//*[text()='" + actualViewsp[0] + "']//following::div[2]//*[text()='" + actualViewsp[1] + "']"));
		System.out.println(extractxpathid.getAttribute("id"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		By filter = By.xpath("(//*[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "'])["
				+ actualViewsp[2] + "]//a");
		click(driver, filter, actualViews);

	}

	public void Snackbar(WebDriver driver, String methodName) throws InterruptedException {
		try {
			By ErrorSnackBar = By.xpath("//img[@class='StausClose']");
			if (driver.findElements(ErrorSnackBar).size() > 0) {
				WebElement errormessage = driver.findElement(By.xpath("//img[@class='StausClose']/following::span[1]"));
				Reporter.log("Status Bar Appear with message: " + errormessage.getText());
				Add_Log.info("Status Bar Appear with message: " + errormessage.getText());
				TestResultStatus.mpaskeysnew.put(methodName, "Status Bar Appear with message: " + errormessage.getText());
			}
		} catch (NoSuchElementException e1) {

		}
	}
	
	public void Checkcitirisk(WebDriver driver,String methodName,String actualViews,String viewName,String ReportName)throws InterruptedException {
		String[] actualViewsp = actualViews.split(",");
		List<String> views = new ArrayList<>();
		boolean flag = false;
		String SearchButton = "//input[contains(@title,'Search')]";
		WebPageElements searchbutton = new WebPageElements("Search Box", "xpath",SearchButton );
		waitForElementPresent1(driver, 120, searchbutton);
		setTextenter(driver, searchbutton, ReportName);
		String Searchpopup = "//div[@class='search-results-container']";
		WebPageElements searchpopup = new WebPageElements("Search Box pop up", "xpath",Searchpopup );
		waitForElementPresent1(driver, 120, searchpopup);
		//div[@class='search-results-container']//child::div[@class='search-results-menu-node-container']//child::span[text()='CitiRisk Regulatory']
		String Searchname = "//div[@class='search-results-container']//child::div[@class='search-results-menu-node-container']//child::span[text()='"+ReportName+"']";
		WebPageElements searchname= new WebPageElements(ReportName, "xpath",Searchname );
		waitForElementPresent1(driver, 120, searchname);
		click(driver,searchname);
		Thread.sleep(1000);	
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		WebElement frameElement = driver.findElement(By.xpath("//iframe[contains(@src,'regulatory') and contains(@id,'gw-content-iframe')]"));
		driver.switchTo().frame(frameElement);
		/*WebElement frameElement2 = driver.findElement(By.xpath("//iframe[contains(@src,'regulatory') and contains(@id,'uxiframe')]"));
		driver.switchTo().frame(frameElement2);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);*/
		String TabName = "//a[contains(@class,'x-tab-primary-tabs x-top x-tab-top x-tab-primary-tabs-top') and not(contains(@style,'display: none'))]//child::span[@class='x-tab-inner x-tab-inner-primary-tabs']";
		WebPageElements tabname= new WebPageElements(TabName, "xpath",TabName );
		waitForElementPresent1(driver, 120, tabname);
		int size = driver.findElements(By.xpath(TabName)).size();
		for (int i = 1; i <= size; i++) {
			views.add(getText(driver, By.xpath("(" + TabName + ")[" + i + "]"), "Tab #" + i));
	}
		if(size >= 3)
		{
			Add_Log.info("Successfully displayed " + views + "  tab visible");
			Reporter.log("Successfully displayed " + views + "  tab visible");
		}
		else
		{
			Add_Log.info("Uable to displayed  all 3 tabs only " + views + "  tab  is visible");
			Reporter.log("Uable to displayed  all 3 tabs only " + views + "  tab  is visible");
			TestResultStatus.mpaskeysnew.put(methodName, "Uable to displayed  all 3 tabs only " + views + "  tab  is visible");
			Assert.fail();
		}
	}
	


	public void viewsCheck(WebDriver driver,String methodName,String actualViews)throws InterruptedException {
		String[] actualViewsp = actualViews.split(",");
		List<String> views = new ArrayList<>();
		boolean flag = false;
		waitForElementPresent(driver, 900, viewOptions);
		int size = driver.findElements(By.xpath(VIEWOPTIONS)).size();
		for (int i = 1; i <= size; i++) {
			views.add(getText(driver, By.xpath("(" + VIEWOPTIONS + ")[" + i + "]"), "View #" + i));
			// System.out.println(views);
		}
		for (int i = 0; i < actualViewsp.length; i++) {
			if (views.contains(actualViewsp[i].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp[i].trim().toString() + " on left side of screen");
				Reporter.log("Successfully displayed " + actualViewsp[i].trim().toString()
						+ " view is displayed on left side of screen");
			} else {
				Add_Log.info(actualViewsp[i].trim().toString() + " is not displayed on left side of screen");
				Reporter.log(actualViewsp[i].trim().toString() + " is not displayed on left side of screen");
				TestResultStatus.mpaskeysnew.put(methodName, actualViewsp[i].trim().toString() + " is not displayed on left side of screen");
				Snackbar(driver,methodName);
				flag = true;
			}
		}
		if (flag) {
			TestResultStatus.Testfail = true;
			Assert.fail();
		} else {
			Add_Log.info("All views are displayed on left side of screen");
			Reporter.log("All views are displayed on left side of screen");
		}
	}

	public void dataVisualizationCheck(WebDriver driver,String methodName,String actualView, String reportingPeriod, String viewName)
			throws InterruptedException {
		String[] actualViews = actualView.split(",");
		List<String> xviews = new ArrayList<>();
		boolean flag = false;

		waitForLoad(driver, 900);
		waitForElementPresent(driver, 900, dataVisualizationHeader);
		int size = driver.findElements(By.xpath(DATAVISUALIZATIONFIELDS)).size();
		for (int i = 1; i <= size; i++) {
			xviews.add(getText(driver, By.xpath("(" + DATAVISUALIZATIONFIELDS + ")[" + i + "]"),
					" Filter criteria #" + i));
		}
		System.out.println(xviews + " New View");

		for (int i1 = 0; i1 < actualViews.length; i1++) {
			// System.out.println(actualViews[i1] + " Actual");

			if (xviews.get(i1).equals(actualViews[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViews[i1].trim().toString()
						+ " filter criteria is displayed in Data Visualisation Criteria screen");
				Reporter.log("Successfully displayed " + actualViews[i1].trim().toString()
						+ " filter criteria is displayed in Data Visualisation Criteria screen");
			} else {
				Add_Log.info(actualViews[i1].trim().toString()
						+ " filter criteria is not displayed in Data Visualisation Criteria screen");
				Reporter.log(actualViews[i1].trim().toString()
						+ " filter criteria is not displayed in Data Visualisation Criteria screen");
				TestResultStatus.mpaskeysnew.put(methodName, actualViews[i1].trim().toString()
						+ " filter criteria is not displayed in Data Visualisation Criteria screen");
				flag = true;
			}
		}

		if (flag) {
			TestResultStatus.Testfail = true;
			Assert.fail();
		} else {
			Add_Log.info("Successfully all filter criterias are displayed in Data Visualisation Criteria screen");
			Reporter.log("Successfully all filter criterias are displayed in Data Visualisation Criteria screen");
		}
	}

	public void dataVisualizationCheckSummaryExposure(WebDriver driver,String methodName,String actualView, String reportingPeriod,
			String viewName, String ReportName)throws InterruptedException {
		String[] actualViews = actualView.split(",");
		List<String> views = new ArrayList<>();
		boolean flag = false;
		waitForLoad(driver, 200);
		Thread.sleep(1000);
		waitForElementPresent(driver, 300, expand_btn);
		click(driver, expand_btn);
		Thread.sleep(1000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 90, dataVisualizationHeader);
		int size = driver.findElements(By.xpath(DATAVISUALIZATIONFIELDSONLY)).size();
		for (int i = 1; i <= size; i++) {
			views.add(getText(driver, By.xpath("(" + DATAVISUALIZATIONFIELDSONLY + ")[" + i + "]"),
					" Filter criteria #" + i));
		}
		System.out.println(views + " New View");
		for (int i = 0; i < actualViews.length; i++) {
			// System.out.println(actualViews[i] + " Actual");
			// System.out.println(views + " Array");
			if (views.get(i).equals(actualViews[i].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViews[i].trim().toString()
						+ " filter criteria is in Data Visualisation Criteria screen under " + ReportName
						+ " and select " + viewName);
				Reporter.log("Successfully displayed " + actualViews[i].trim().toString()
						+ " filter criteria is in Data Visualisation Criteria screen under " + ReportName
						+ " and select " + viewName);
			} else {
				Add_Log.info(actualViews[i].trim().toString()
						+ " filter criteria is not displayed in Data Visualisation Criteria screen under " + ReportName
						+ " and select " + viewName);
				Reporter.log(actualViews[i].trim().toString()
						+ " filter criteria is not displayed in Data Visualisation Criteria screen under " + ReportName
						+ " and select " + viewName);
				TestResultStatus.mpaskeysnew.put(methodName, actualViews[i].trim().toString()
						+ " filter criteria is not displayed in Data Visualisation Criteria screen under " + ReportName
						+ " and select " + viewName);
			
				flag = true;
			}
		}
		if (flag) {
			TestResultStatus.Testfail = true;
			Assert.fail();
		} else {
			Add_Log.info("Successfully all filter criterias are displayed in Data Visualisation Criteria screen under "
					+ ReportName + " and select " + viewName);
			Reporter.log("Successfully all filter criterias are displayed in Data Visualisation Criteria screen under "
					+ ReportName + " and select " + viewName);
		}
	}

	public boolean summaryGridLoad(WebDriver driver,String methodName,String viewName, String reportingPeriod,String Verify)
			throws InterruptedException {
		boolean flag = false;
		Thread.sleep(2000);
		if (!viewName.equalsIgnoreCase("Relationship")) {
			By ele = By.xpath("//span[text()='Summary']//following::span[text()='" + viewName + "']");
			click(driver, ele, viewName + " option under Summary");
			Thread.sleep(2000);
			waitForLoad(driver, 200);
		}
		Thread.sleep(2000);
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		By tabledata = By.xpath("//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		waitForElementPresent(driver, 90, tabledata, Verify);
		try {
			driver.findElement(tabledata).isDisplayed();
			Add_Log.info("Successfully able to select Filter button for " + viewName + " summary screen");
			Reporter.log("Successfully able to select Filter button for " + viewName + " summary screen");

		} catch (Exception e) {
			Add_Log.info("Not able to select Filter button for " + viewName + " summary screen");
			Reporter.log("Not able to select Filter button for " + viewName + " summary screen");
			TestResultStatus.mpaskeysnew.put(methodName, "Not able to select Filter button for " + viewName + " summary screen");
			flag = true;
		}
		if (flag) {
			allFlag = true;
		}
		return allFlag;

	}

	public void summaryLoad(WebDriver driver,String methodName,String reportingPeriod,String Verify)throws InterruptedException {
		Thread.sleep(1000);
		String[] Verifysp = Verify.split(",");
		waitForElementPresent(driver, 120, summary_relationship_option);
		allFlag = summaryGridLoad(driver,methodName, Verifysp[0], reportingPeriod,Verifysp[0]);
		allFlag = summaryGridLoad(driver,methodName, Verifysp[1], reportingPeriod,Verifysp[1]);
		allFlag = summaryGridLoad(driver,methodName, Verifysp[2], reportingPeriod,Verifysp[2]);
		if (allFlag) {
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
	}

	public boolean exposureGridLoad(WebDriver driver,String methodName,String viewName, String reportingPeriod, String ID,
			String BusinessDay, String Verify)throws InterruptedException {
		boolean flag = false;
		boolean flag2 = false;
		String id = null;
		String name = null;
		String name2 = null;
		if (viewName.equalsIgnoreCase("Relationship")) {
			id = "//input[@name='searchCagid']";
			name = "CAGID";
			name2 = "CAGID";
		} else if (viewName.equalsIgnoreCase("Obligor")) {
			id = "//input[@name='searchGfcid']";
			name = "GFCID";
			name2 = "GFCID";
		} else if (viewName.equalsIgnoreCase("Facility")) {
			id = "//input[@name='searchGfrn']";
			name = "Facility";
			name2 = "Facility ID";
		}

		WebPageElements cagid_ddl = new WebPageElements(name, "xpath", id);
		waitForElementPresent(driver, 120, exposure_relationship_option);
		By ele = By.xpath(
				"//span[text()='Work-Stream Drill-Down [Exposure]']//following::span[text()='" + viewName + "']");
		click(driver, ele, viewName + " option under Summary");
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		waitForElementPresent(driver, 90, cagid_ddl);
		click(driver, cagid_ddl);
		setText(driver, cagid_ddl, ID);
		Thread.sleep(2000);
		waitForElementPresent(driver, 90, riskmetric_ddl);
		click(driver, riskmetric_ddl);
		Thread.sleep(1000);
		click(driver, riskmetric_ddl);
		Thread.sleep(500);
		WebElement element = driver.findElement(By.xpath("//span[text()='" + name2 + "']//following::div[2]"));
		System.out.println(element.getCssValue("border-bottom-color"));
		String color = element.getCssValue("border-bottom-color");
		if (color.equals("rgba(76, 169, 119, 1)")) {
			Reporter.log("Successfully Matching found under " + name);
			Add_Log.info("Successfully Matching found under " + name);
		} else {
			// flag2 = true;
		}
		Thread.sleep(2000);
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By tabledata = By.xpath("//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		waitForElementPresent(driver, 10, tabledata, Verify + " for " +viewName + " -" + ID);
		try {
			driver.findElement(tabledata).isDisplayed();
			Add_Log.info("Successfully able to display data under Exposure Summary when select Filter button for "
					+ viewName + " exposure screen");
			Reporter.log("Successfully able to display data under Exposure Summary when select Filter button for "
					+ viewName + " exposure screen");

		} catch (Exception e) {
			Add_Log.info("Not able to display data under Exposure Summary when select Filter button for " + viewName
					+ " exposure screen");
			Reporter.log("Not able to display data under Exposure Summary when select Filter button for " + viewName
					+ " exposure screen");
			TestResultStatus.mpaskeysnew.put(methodName, "Not able to display data under Exposure Summary when select Filter button for " + viewName
					+ " exposure screen");
			flag = true;
		}
		if (flag2) {
			TestResultStatus.Testfail = true;
			Reporter.log("No Matching found under " + name);
			Add_Log.info("No Matching found under " + name);
			Assert.fail();
		}
		if (flag) {
			allFlag = true;
		}
		return allFlag;

	}

	public void exposureLoad(WebDriver driver,String methodName,String reportingPeriod, String ids, String BusinessDay, String Verify)
			throws InterruptedException {
		String[] id = ids.split(",");
		String[] Verifysp = Verify.split(",");
		waitForElementPresent(driver, 120, expand_btn);
		click(driver, expand_btn);
		Thread.sleep(1000);


		waitForElementPresent(driver, 120, exposure_relationship_option);
		allFlag = exposureGridLoad(driver,methodName, "Relationship", reportingPeriod, id[0].toString().trim(), BusinessDay,Verifysp[0]);
		allFlag = exposureGridLoad(driver, methodName,"Obligor", reportingPeriod, id[1].toString().trim(), BusinessDay,Verifysp[1]);
		allFlag = exposureGridLoad(driver, methodName, "Facility", reportingPeriod, id[2].toString().trim(), BusinessDay,Verifysp[2]);
		if (allFlag) {
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
	}

	public boolean summaryGridClear(WebDriver driver,String methodName,String viewName, String reportingPeriod, String BusinessDay, String Verify)
			throws InterruptedException {
		boolean flag = false;

		if (!viewName.equalsIgnoreCase("Relationship")) {
			By ele = By.xpath("//span[text()='Summary']//following::span[text()='" + viewName + "']");
			click(driver, ele, viewName + " option under Summary");
			Thread.sleep(2000);
			waitForLoad(driver, 120);
		}
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		
		try {
		By tabledata = By.xpath("//label[text()='" + Verify + "']//following::tr[2]/td/div[@class='x-grid-cell-inner ']");
		
		waitForElementPresenttable(driver, 30, tabledata, Verify + "Summary Grid");
		}
		catch (Exception e1) {
			By tabledata = By.xpath("//label[text()='" + Verify + "']//following::tr[3]/td/div[@class='x-grid-cell-inner ']");
			waitForElementPresenttable(driver, 30, tabledata, Verify + "Summary Grid");
		}
		Thread.sleep(2000);
		click(driver, clearAll);
		Thread.sleep(2000);
		if (driver.findElement(By.xpath(REPORTING_PERIOD_DDL)).getAttribute("value").isEmpty()) {
			Add_Log.info("Successfully able to select clear all button for " + viewName + " summary screen");
			Reporter.log("Successfully able to select clear all button for " + viewName + " summary screen");
		} else {
			Add_Log.info("Not able to select clear all button for " + viewName + " summary screen");
			Reporter.log("Not able to select clear all button for " + viewName + " summary screen");
			TestResultStatus.mpaskeysnew.put(methodName, "Not able to select clear all button for " + viewName + " summary screen");
			flag = true;
			Assert.fail();
		}
		if (flag) {
			allFlag = true;
		}
		return allFlag;

	}

	public void summaryClear(WebDriver driver,String methodName,String reportingPeriod, String BusinessDay, String Verify)throws InterruptedException {
		Thread.sleep(1000);
		String[] Verifysp = Verify.split(",");
		waitForElementPresent(driver, 200, summary_relationship_option);
		allFlag = summaryGridClear(driver,methodName,"Relationship", reportingPeriod, BusinessDay, Verifysp[0]);
		allFlag = summaryGridClear(driver,methodName, "Obligor", reportingPeriod, BusinessDay,Verifysp[1]);
		allFlag = summaryGridClear(driver,methodName, "Facility", reportingPeriod, BusinessDay,Verifysp[2]);
		if (allFlag) {
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
	}

	@SuppressWarnings("unused")
	public boolean exposureGridClear(WebDriver driver,String methodName,String viewName, String reportingPeriod, String ID,
			String BusinessDay, String Verify)throws InterruptedException {
		boolean flag = false;
		boolean flag2 = false;
		String id = null;
		String name = null;
		String name2 = null;
		By tabledata = null;
		if (viewName.equalsIgnoreCase("Relationship")) {
			id = "//input[@name='searchCagid']";
			name = "CAGID";
			name2 = "CAGID";
			tabledata = By.xpath("//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		} else if (viewName.equalsIgnoreCase("Obligor")) {
			id = "//input[@name='searchGfcid']";
			name = "GFCID";
			name2 = "GFCID";
			tabledata = By.xpath("//label[text()='" + Verify + "']");
		} else if (viewName.equalsIgnoreCase("Facility")) {
			id = "//input[@name='searchGfrn']";
			name = "Facility";
			name2 = "Facility ID";
			tabledata = By.xpath("//label[text()='" + Verify + "']");
		}

		WebPageElements cagid_ddl = new WebPageElements(name, "xpath", id);
		By ele = By.xpath(
				"//span[text()='Work-Stream Drill-Down [Exposure]']//following::span[text()='" + viewName + "']");
		click(driver, ele, viewName + " option under Summary");
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		waitForElementPresent(driver, 200, cagid_ddl);
		click(driver, cagid_ddl);
		setText(driver, cagid_ddl, ID);
		Thread.sleep(2000);
		waitForElementPresent(driver, 200, riskmetric_ddl);
		click(driver, riskmetric_ddl);
		Thread.sleep(1000);
		click(driver, riskmetric_ddl);
		Thread.sleep(500);
		WebElement element = driver.findElement(By.xpath("//span[text()='" + name2 + "']//following::div[2]"));
		System.out.println(element.getCssValue("border-bottom-color"));
		String color = element.getCssValue("border-bottom-color");
		if (color.equals("rgba(76, 169, 119, 1)")) {
			Reporter.log("Successfully Matching found under " + name);
			Add_Log.info("Successfully Matching found under " + name);
		} else {
			// flag2 = true;
		}

		waitForLoad(driver, 120);
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		waitForElementPresent(driver, 90, tabledata, Verify);
		Thread.sleep(2000);
		click(driver, clearAll);
		Thread.sleep(2000);

		if (driver.findElement(By.xpath(REPORTING_PERIOD_DDL)).getAttribute("value").isEmpty()
				&& driver.findElement(By.xpath(id)).getAttribute("value").isEmpty()) {
			Add_Log.info("Successfully able to select clear all button for " + viewName + " exposure screen");
			Reporter.log("Successfully able to select clear all button for " + viewName + " exposure screen");
		} else {
			Add_Log.info("Not able to select clear all button for " + viewName + " exposure screen");
			Reporter.log("Not able to select clear all button for " + viewName + " exposure screen");
			TestResultStatus.mpaskeysnew.put(methodName, "Not able to select clear all button for " + viewName + " exposure screen");
			flag = true;
			Snackbar(driver,methodName);
			Assert.fail();
		}
		if (flag2) {
			TestResultStatus.Testfail = true;
			Reporter.log("No Matching found under " + name);
			Add_Log.info("No Matching found under " + name);
			Assert.fail();
		}

		return allFlag;

	}

	public void exposureClear(WebDriver driver,String methodName,String reportingPeriod, String ids, String BusinessDay, String Verify)
			throws InterruptedException {
		String[] id = ids.split(",");
		String[] Verifysp = Verify.split(",");
		waitForElementPresent(driver, 120, expand_btn);
		click(driver, expand_btn);
		Thread.sleep(1000);
		waitForElementPresent(driver, 120, exposure_relationship_option);
		allFlag = exposureGridClear(driver,methodName, "Relationship", reportingPeriod, id[0].toString().trim(), BusinessDay,Verifysp[0]);
		allFlag = exposureGridClear(driver,methodName, "Obligor", reportingPeriod, id[1].toString().trim(), BusinessDay,Verifysp[1]);
		allFlag = exposureGridClear(driver, methodName,"Facility", reportingPeriod, id[2].toString().trim(), BusinessDay,Verifysp[2]);
		if (allFlag) {
			TestResultStatus.Testfail = true;
			 Assert.fail();
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	public void CheckSummaryExposure(WebDriver driver,String methodName,String actualView, String reportingPeriod, String viewName,
			String ReportName)throws InterruptedException {

		String[] actualViews = actualView.split(",");
		List<String> views = new ArrayList<>();
		boolean flag = false;
		waitForLoad(driver, 120);
		Thread.sleep(1000);
		waitForElementPresent(driver, 120, expand_btn);
		click(driver, expand_btn);
		Thread.sleep(1000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, summaryfieldsonly);
		int size = driver.findElements(By.xpath(SUMMARYFIELDSONLY)).size();
		for (int i = 1; i <= size; i++) {
			views.add(getText(driver, By.xpath("(" + SUMMARYFIELDSONLY + ")[" + i + "]"), " Filter criteria #" + i));
		}
		System.out.println(views + " New View");
			for (int i = 0; i < actualViews.length; i++) {
				// System.out.println(actualViews[i] + " Actual");
				// System.out.println(views + " Array");
				if (views.get(i).equals(actualViews[i].trim().toString())) {
					Add_Log.info("Successfully displayed " + actualViews[i].trim().toString()
							+ " filter criteria under Summary Screen fields for " + viewName);
					Reporter.log("Successfully displayed " + actualViews[i].trim().toString()
							+ " filter criteria under Summary Screen fields for " + viewName);
				} else {
					Add_Log.info(actualViews[i].trim().toString()
							+ " filter criteria is not displayed under Summary Screen fields for " + viewName);
					Reporter.log(actualViews[i].trim().toString()
							+ " filter criteria is not displayed under Summary Screen fields for " + viewName);
				//	TestResultStatus.Multiplekd(methodName,actualViews[i].trim().toString()
					//		+ " filter criteria is not displayed under Summary Screen fields for " + viewName);
					TestResultStatus.mpaskeysnew.put(methodName, actualViews[i].trim().toString()
						+ " filter criteria is not displayed under Summary Screen fields for " + viewName);
					flag = true;
					
				}
			}
			if (flag) {
				TestResultStatus.Testfail = true;
			Assert.fail();	
			} else {
				Add_Log.info("Successfully all filter criterias are displayed under Summary Screen fields for " + viewName);
				Reporter.log("Successfully all filter criterias are displayed under Summary Screen fields for " + viewName);
			}
		}

	@SuppressWarnings({ "unused", "deprecation" })
	public void downloadFileCheck(WebDriver driver,String methodName,String viewName, String reportingPeriod, String ReportName,
			String Fileformat, String Environment, String Verify)throws InterruptedException, IOException {

		String csvFileName = viewName;
		String downloadPath1 = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";

		File dir = new File(downloadPath1);

		for (File file : dir.listFiles()) {
		//	System.out.println(file.getName());
			if (file.getName().contains(viewName) || file.getName().contains("Summary")) {
			//	file.delete();
				//Reporter.log("Successfully Deleted Existing File before clicking on download");
			//	Add_Log.info("Successfully Deleted Existing File before clicking on download");
			}
		}
		waitForLoad(driver, 900);
		Thread.sleep(1000);
		waitForElementPresent(driver, 900, expand_btn);
		click(driver, expand_btn);
		Thread.sleep(1000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 90);
		By tabledata = By.xpath("//label[text()='" + Verify  + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		waitForElementPresent(driver, 90, tabledata, Verify );
		String downloadIcon = "//label[text()='" + viewName + "']//following::span[contains(@class,'icon-" + Fileformat
				+ "')]";
		By downloadIconst = By.xpath(downloadIcon);
		waitForElementPresent(driver, 90, downloadIconst, Fileformat + " icon " + viewName);
		click(driver, downloadIconst, Fileformat + " icon " + viewName);
		downloadfilecommonreg(driver, methodName,ReportName, viewName, Fileformat);
	}

	public void downloadfilecommonreg(WebDriver driver,String methodName,String ReportName, String viewName,
			String Fileformat)
					throws InterruptedException, IOException {

		
		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
		start = System.currentTimeMillis();
		String totalTime = null;
		long beforeCount = 0;

		beforeCount = Files.list(Paths.get(downloadPath)).count();
		long afterCount = beforeCount;
		int i = 1;
		while (beforeCount >= afterCount) {
			Thread.sleep(4000);
			afterCount = Files.list(Paths.get(downloadPath)).count();
			// System.out.println(i++);
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
		TimeUnit.SECONDS.sleep(60);
		String fileName = latestFileName();
		System.out.println(fileName + " & " + viewName);
		if (fileName.contains(viewName) || fileName.contains("Summary")) {
			Add_Log.info("Successfully downloaded " + Fileformat + " report for " + viewName + " " + ReportName
					+ " Report Time took to download " + totalTime + " sec");
			Reporter.log("Successfully downloaded " + Fileformat + " report for " + viewName + " " + ReportName
					+ " Report Time took to download " + totalTime + " sec");
		} else {
			Add_Log.info(Fileformat + "Report not downloaded for " + viewName + " " + ReportName
					+ " report");
			Reporter.log(Fileformat + "Report not downloaded for " + viewName + " " + ReportName
					+ " report");
			TestResultStatus.mpaskeysnew.put(methodName, Fileformat + "Report not downloaded for " + viewName + " " + ReportName
					+ " report");
			Assert.fail();
		}


	}
	public boolean CheckSortandFilterSummary24(WebDriver driver,String methodName,String viewName, String ReportName, String RiskMetric,
			String ids, String reportingPeriod,String Verify)throws InterruptedException {
		String[] idsp = ids.split(",");
		boolean flag = false;
		boolean flagriskmt = false;
		boolean flag2 = false;
		String id = null;
		String name = null;
		String name2 = null;
		String tablename = null;
		if (viewName.equalsIgnoreCase("Relationship")) {
			id = "//input[@name='searchCagid']";
			name = "CAGID";
			name2 = "CAGID";
			tablename = "(//*/tbody/tr/td[2]/div)[2]";
		} else if (viewName.equalsIgnoreCase("Obligor")) {
			id = "//input[@name='searchGfcid']";
			name = "GFCID";
			name2 = "GFCID";
			tablename = "(//*/tbody/tr/td[2]/div)[2]";
		} else if (viewName.equalsIgnoreCase("Facility")) {
			id = "//input[@name='searchGfrn']";
			name = "Facility";
			name2 = "Facility ID";
			tablename = "//*/tbody/tr/td//div[text()='" + idsp[1] + "']";
		}
		By ele = By.xpath(
				"//span[text()='Work-Stream Drill-Down [Exposure]']//following::span[text()='" + viewName + "']");
		click(driver, ele,
				"by drilling down to Work-Stream Drill-Down [Exposure] and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 300);
		WebPageElements cagid_ddl = new WebPageElements(name, "xpath", id);
		click(driver, cagid_ddl);
		setText(driver, cagid_ddl, idsp[0]);
		Thread.sleep(1000);
		waitForElementPresent(driver, 120, riskmetric_ddl);
		click(driver, riskmetric_ddl);
		WebElement element = driver.findElement(By.xpath("//span[text()='" + name2 + "']//following::div[2]"));
		System.out.println(element.getCssValue("border-bottom-color"));
		String color = element.getCssValue("border-bottom-color");
		if (color.equals("rgba(76, 169, 119, 1)")) {
			Reporter.log("Successfully Matching found under " + name);
			Add_Log.info("Successfully Matching found under " + name);
		} else {
			flag2 = true;
		}

		Thread.sleep(2000);
		By riskMetricValue = By.xpath("//li[text()='" + RiskMetric + "']");
		click(driver, riskMetricValue, " value in Risk Metric Drop down list " + RiskMetric);
		Thread.sleep(2000);
		waitForLoad(driver, 200);
		try {
			if (RiskMetric.equalsIgnoreCase("GSST")) {
			
				Thread.sleep(2000);
			}
		} finally {
		}
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 600);
		Thread.sleep(2000);
		By tabledata = By.xpath("//label[text()='" + Verify  + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		System.out.println(tabledata);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			wait.until(ExpectedConditions.elementToBeClickable(tabledata));
			Add_Log.info("Successfully display Exposure Summary under " + RiskMetric + " Risk Metric");
			Reporter.log("Successfully display Exposure Summary under " + RiskMetric + " Risk Metric");		
		} 
		catch (Exception e) {
				By ErrorSnackBar = By.xpath("//img[@class='StausClose']");
				if (driver.findElements(ErrorSnackBar).size() > 0) {
					WebElement errormessage = driver.findElement(By.xpath("//img[@class='StausClose']/following::span[1]"));
					Reporter.log("Status Bar Appear with message: " + errormessage.getText());
					Add_Log.info("Status Bar Appear with message: " + errormessage.getText());
					Reporter.log("***Unable to display Exposure Summary under " + RiskMetric + " Risk Metric");
					Add_Log.info("***Unable to display Exposure Summary under " + RiskMetric + " Risk Metric");
					TestResultStatus.mpaskeysnew.put(methodName, "Unable to display Exposure Summary under " + RiskMetric + " Risk Metric");
					flag = true;
			}
		}
		if (flag) {
			allFlag24 = true;
		//	Assert.fail();
		}
		return allFlag24;

	}

	public boolean CheckSortandFilterSummary25(WebDriver driver,String methodName,String viewName, String ReportName, String RiskMetric,
			String ids, String reportingPeriod,String Verify)throws InterruptedException {
		String[] idsp = ids.split(",");
		boolean flag = false;
		boolean flagriskmt = false;
		boolean flag2 = false;
		String id = null;
		String name = null;
		String name2 = null;
		String tablename = null;
		if (viewName.equalsIgnoreCase("Relationship")) {
			id = "//input[@name='searchCagid']";
			name = "CAGID";
			name2 = "CAGID";
			tablename = "(//*/tbody/tr/td[2]/div)[2]";
		} else if (viewName.equalsIgnoreCase("Obligor")) {
			id = "//input[@name='searchGfcid']";
			name = "GFCID";
			name2 = "GFCID";
			tablename = "(//*/tbody/tr/td[2]/div)[2]";
		} else if (viewName.equalsIgnoreCase("Facility")) {
			id = "//input[@name='searchGfrn']";
			name = "Facility";
			name2 = "Facility ID";
			tablename = "//*/tbody/tr/td//div[text()='" + idsp[1] + "']";
		}
		By ele = By.xpath(
				"//span[text()='Work-Stream Drill-Down [Exposure]']//following::span[text()='" + viewName + "']");
		click(driver, ele,
				"by drilling down to Work-Stream Drill-Down [Exposure] and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 300);
		WebPageElements cagid_ddl = new WebPageElements(name, "xpath", id);
		click(driver, cagid_ddl);
		setText(driver, cagid_ddl, idsp[0]);
		Thread.sleep(1000);
		waitForElementPresent(driver, 120, riskmetric_ddl);
		click(driver, riskmetric_ddl);
		WebElement element = driver.findElement(By.xpath("//span[text()='" + name2 + "']//following::div[2]"));
		System.out.println(element.getCssValue("border-bottom-color"));
		String color = element.getCssValue("border-bottom-color");
		if (color.equals("rgba(76, 169, 119, 1)")) {
			Reporter.log("Successfully Matching found under " + name);
			Add_Log.info("Successfully Matching found under " + name);
		} else {
			flag2 = true;
		}

		Thread.sleep(2000);
		By riskMetricValue = By.xpath("//li[text()='" + RiskMetric + "']");
		click(driver, riskMetricValue, " value in Risk Metric Drop down list " + RiskMetric);
		Thread.sleep(2000);
		waitForLoad(driver, 200);
		try {
			if (RiskMetric.equalsIgnoreCase("GSST")) {
			
				Thread.sleep(2000);
			}
		} finally {
		}
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 600);
		Thread.sleep(2000);
		By tabledata = By.xpath("//label[text()='" + Verify  + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		System.out.println(tabledata);
		WebDriverWait wait = new WebDriverWait(driver, 30);


		try {
			wait.until(ExpectedConditions.elementToBeClickable(tabledata));
			Add_Log.info("Successfully display Exposure Summary under " + RiskMetric + " Risk Metric");
			Reporter.log("Successfully display Exposure Summary under " + RiskMetric + " Risk Metric");		
		} 
		catch (Exception e) {
				By ErrorSnackBar = By.xpath("//img[@class='StausClose']");
				if (driver.findElements(ErrorSnackBar).size() > 0) {
					WebElement errormessage = driver.findElement(By.xpath("//img[@class='StausClose']/following::span[1]"));
					Reporter.log("Status Bar Appear with message: " + errormessage.getText());
					Add_Log.info("Status Bar Appear with message: " + errormessage.getText());
					Reporter.log("***Unable to display Exposure Summary under " + RiskMetric + " Risk Metric");
					Add_Log.info("***Unable to display Exposure Summary under " + RiskMetric + " Risk Metric");
					TestResultStatus.mpaskeysnew.put(methodName, "Unable to display Exposure Summary under " + RiskMetric + " Risk Metric");
					flag = true;
			}
		}
	
		if (flag) {
			allFlag25 = true;
		//	Assert.fail();
		}
		return allFlag25;

	}
	
	public boolean CheckSortandFilterSummary26(WebDriver driver,String methodName,String viewName, String ReportName, String RiskMetric,
			String ids, String reportingPeriod,String Verify)throws InterruptedException {
		String[] idsp = ids.split(",");
		boolean flag = false;
		boolean flagriskmt = false;
		boolean flag2 = false;
		String id = null;
		String name = null;
		String name2 = null;
		String tablename = null;
		if (viewName.equalsIgnoreCase("Relationship")) {
			id = "//input[@name='searchCagid']";
			name = "CAGID";
			name2 = "CAGID";
			tablename = "(//*/tbody/tr/td[2]/div)[2]";
		} else if (viewName.equalsIgnoreCase("Obligor")) {
			id = "//input[@name='searchGfcid']";
			name = "GFCID";
			name2 = "GFCID";
			tablename = "(//*/tbody/tr/td[2]/div)[2]";
		} else if (viewName.equalsIgnoreCase("Facility")) {
			id = "//input[@name='searchGfrn']";
			name = "Facility";
			name2 = "Facility ID";
			tablename = "//*/tbody/tr/td//div[text()='" + idsp[1] + "']";
		}
		By ele = By.xpath(
				"//span[text()='Work-Stream Drill-Down [Exposure]']//following::span[text()='" + viewName + "']");
		click(driver, ele,
				"by drilling down to Work-Stream Drill-Down [Exposure] and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 300);
		WebPageElements cagid_ddl = new WebPageElements(name, "xpath", id);
		click(driver, cagid_ddl);
		setText(driver, cagid_ddl, idsp[0]);
		Thread.sleep(1000);
		waitForElementPresent(driver, 120, riskmetric_ddl);
		click(driver, riskmetric_ddl);
		WebElement element = driver.findElement(By.xpath("//span[text()='" + name2 + "']//following::div[2]"));
		System.out.println(element.getCssValue("border-bottom-color"));
		String color = element.getCssValue("border-bottom-color");
		if (color.equals("rgba(76, 169, 119, 1)")) {
			Reporter.log("Successfully Matching found under " + name);
			Add_Log.info("Successfully Matching found under " + name);
		} else {
			flag2 = true;
		}

		Thread.sleep(2000);
		By riskMetricValue = By.xpath("//li[text()='" + RiskMetric + "']");
		click(driver, riskMetricValue, " value in Risk Metric Drop down list " + RiskMetric);
		Thread.sleep(2000);
		waitForLoad(driver, 200);
		try {
			if (RiskMetric.equalsIgnoreCase("GSST")) {
			
				Thread.sleep(2000);
			}
		} finally {
		}
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 600);
		Thread.sleep(2000);
		By tabledata = By.xpath("//label[text()='" + Verify  + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		System.out.println(tabledata);
		WebDriverWait wait = new WebDriverWait(driver, 30);


		try {
			wait.until(ExpectedConditions.elementToBeClickable(tabledata));
			Add_Log.info("Successfully display Exposure Summary under " + RiskMetric + " Risk Metric");
			Reporter.log("Successfully display Exposure Summary under " + RiskMetric + " Risk Metric");		
		} 
		catch (Exception e) {
				By ErrorSnackBar = By.xpath("//img[@class='StausClose']");
				if (driver.findElements(ErrorSnackBar).size() > 0) {
					WebElement errormessage = driver.findElement(By.xpath("//img[@class='StausClose']/following::span[1]"));
					Reporter.log("Status Bar Appear with message: " + errormessage.getText());
					Add_Log.info("Status Bar Appear with message: " + errormessage.getText());
					Reporter.log("***Unable to display Exposure Summary under " + RiskMetric + " Risk Metric");
					Add_Log.info("***Unable to display Exposure Summary under " + RiskMetric + " Risk Metric");
					TestResultStatus.mpaskeysnew.put(methodName, "Unable to display Exposure Summary under " + RiskMetric + " Risk Metric");
					flag = true;
			}
		}
	
		if (flag) {
			allFlag26 = true;
		//	Assert.fail();
		}
		return allFlag26;

	}
	public void CheckSortandFilterSummaryinto24(WebDriver driver,String methodName,String viewName, String ReportName, String RiskMetric,
			String ids, String reportingPeriod, String Verify )throws InterruptedException {

		String[] riskmeter = RiskMetric.split(",");
		String[] Verifysp = Verify.split(",");
		waitForLoad(driver, 300);
		Thread.sleep(1000);
		waitForElementPresent(driver, 120, expand_btn);
		click(driver, expand_btn);
		Thread.sleep(1000);
		allFlag24 = CheckSortandFilterSummary24(driver,methodName, viewName, ReportName, riskmeter[0].toString().trim(), ids,
				reportingPeriod,Verifysp[0]);
		allFlag24 = CheckSortandFilterSummary24(driver,methodName, viewName, ReportName, riskmeter[1].toString().trim(), ids,reportingPeriod,Verifysp[1]);
	//	allFlag24 = CheckSortandFilterSummary24(driver, methodName,viewName, ReportName, riskmeter[2].toString().trim(), ids,reportingPeriod,Verifysp[2]);
		allFlag24 = CheckSortandFilterSummary24(driver, methodName,viewName, ReportName, riskmeter[3].toString().trim(), ids,
				reportingPeriod,Verifysp[3]);
		allFlag24 = CheckSortandFilterSummary24(driver, methodName,viewName, ReportName, riskmeter[4].toString().trim(), ids,
				reportingPeriod,Verifysp[4]);
		if (allFlag24) {
			TestResultStatus.Testfail = true;
			Reporter.log("No data under one of the RiskMetric ");
			Add_Log.info("No data under one of the RiskMetric ");
			TestResultStatus.mpaskeysnew.put(methodName, "No data under one of the RiskMetric ");
			ITestResult result = Reporter.getCurrentTestResult();
			result.setAttribute("DontRetry", Boolean.TRUE.toString());
			System.out.println(result);
			Reporter.log(result +"test");
			Add_Log.info(result+"test");
			Assert.fail();
		}
		
	}
	
	public void CheckSortandFilterSummaryinto25(WebDriver driver,String methodName,String viewName, String ReportName, String RiskMetric,
			String ids, String reportingPeriod, String Verify )throws InterruptedException {

		String[] riskmeter = RiskMetric.split(",");
		String[] Verifysp = Verify.split(",");
		waitForLoad(driver, 300);
		Thread.sleep(1000);
		waitForElementPresent(driver, 120, expand_btn);
		click(driver, expand_btn);
		Thread.sleep(1000);
		allFlag25 = CheckSortandFilterSummary25(driver,methodName, viewName, ReportName, riskmeter[0].toString().trim(), ids,
				reportingPeriod,Verifysp[0]);
		allFlag25 = CheckSortandFilterSummary25(driver,methodName, viewName, ReportName, riskmeter[1].toString().trim(), ids,reportingPeriod,Verifysp[1]);
	//	allFlag25 = CheckSortandFilterSummary25(driver, methodName,viewName, ReportName, riskmeter[2].toString().trim(), ids,reportingPeriod,Verifysp[2]);
		allFlag25 = CheckSortandFilterSummary25(driver, methodName,viewName, ReportName, riskmeter[3].toString().trim(), ids,
				reportingPeriod,Verifysp[3]);
		allFlag25 = CheckSortandFilterSummary25(driver, methodName,viewName, ReportName, riskmeter[4].toString().trim(), ids,
				reportingPeriod,Verifysp[4]);
		if (allFlag25) {
			TestResultStatus.Testfail = true;
			Reporter.log("No data under one of the RiskMetric ");
			Add_Log.info("No data under one of the RiskMetric ");
			TestResultStatus.mpaskeysnew.put(methodName, "No data under one of the RiskMetric ");
			ITestResult result = Reporter.getCurrentTestResult();
			result.setAttribute("DontRetry", Boolean.TRUE.toString());
			System.out.println(result);
			Reporter.log(result +"test");
			Add_Log.info(result+"test");
			Assert.fail();
		}
		
	}
	public void CheckSortandFilterSummaryinto26(WebDriver driver,String methodName,String viewName, String ReportName, String RiskMetric,
			String ids, String reportingPeriod, String Verify )throws InterruptedException {

		String[] riskmeter = RiskMetric.split(",");
		String[] Verifysp = Verify.split(",");
		waitForLoad(driver, 300);
		Thread.sleep(1000);
		waitForElementPresent(driver, 120, expand_btn);
		click(driver, expand_btn);
		Thread.sleep(1000);
		allFlag26 = CheckSortandFilterSummary26(driver,methodName, viewName, ReportName, riskmeter[0].toString().trim(), ids,
				reportingPeriod,Verifysp[0]);
		allFlag26 = CheckSortandFilterSummary26(driver,methodName, viewName, ReportName, riskmeter[1].toString().trim(), ids,reportingPeriod,Verifysp[1]);
	//	allFlag26 = CheckSortandFilterSummary26(driver, methodName,viewName, ReportName, riskmeter[2].toString().trim(), ids,reportingPeriod,Verifysp[2]);
		allFlag26 = CheckSortandFilterSummary26(driver, methodName,viewName, ReportName, riskmeter[3].toString().trim(), ids,
				reportingPeriod,Verifysp[3]);
		allFlag26 = CheckSortandFilterSummary26(driver, methodName,viewName, ReportName, riskmeter[4].toString().trim(), ids,
				reportingPeriod,Verifysp[4]);
		if (allFlag26) {
			TestResultStatus.Testfail = true;
			Reporter.log("No data under one of the RiskMetric ");
			Add_Log.info("No data under one of the RiskMetric ");
			TestResultStatus.mpaskeysnew.put(methodName, "No data under one of the RiskMetric ");
			ITestResult result = Reporter.getCurrentTestResult();
			result.setAttribute("DontRetry", Boolean.TRUE.toString());
			System.out.println(result);
			Reporter.log(result +"test");
			Add_Log.info(result+"test");
			Assert.fail();
		}
		
	}
	@SuppressWarnings("unchecked")
	public boolean CheckSortandFilteronly(WebDriver driver,String methodName,String viewName, String ReportName, String RiskMetric,
			String ids, String Verify)throws InterruptedException {
		boolean flag = false;

		waitForLoad(driver, 120);
		waitForElementPresent(driver, 120, expand_btn);
		By ele = By.xpath("//span[text()='Relationship']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to Summary' and select " + viewName + " aggregation");
		Thread.sleep(2000);
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		Thread.sleep(5000);
		By tabledata = By.xpath("//label[text()='" + Verify  + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		waitForElementPresent(driver, 90, tabledata, Verify );
		By sort = By.xpath("//label[text()='" + viewName + "']//following::span[contains(@class,'filterswitch')]");
		Thread.sleep(2000);
		click(driver, sort,"by drilling down to " + ReportName + " and select " + viewName + " and click on Sort/Filters");
		Thread.sleep(2000);
		/*
		 * WebElement sortvaluename = driver.findElement(By.xpath("//label[text()='" +
		 * viewName + "']//following::span[contains(@class,'filterswitch')]"));
		 * sortvaluename.click();
		 */
		// driver.findElement(By.xpath(SortName)).sendKeys(ids,Keys.ENTER);

		WebElement linkElements = driver.findElement(By.xpath("(//input[contains(@name,'textfield')])[2]"));
		WebDriverWait wait = new WebDriverWait(driver, 120);

		try {
			wait.until(ExpectedConditions.visibilityOf(linkElements));
			Thread.sleep(2000);
			setText(driver, sortname, ids);
			waitForLoad(driver, 900);
			Thread.sleep(2000);
		} catch (Exception e) {
			Thread.sleep(2000);
			click(driver, sort,
					"by drilling down to " + ReportName + " and select " + viewName + " and click on Sort/Filters");
			Thread.sleep(3000);
			setText(driver, sortname, ids);
			waitForLoad(driver, 900);
		}
		waitForLoad(driver, 900);
		Thread.sleep(3000);

		// Actions actions = new Actions(driver);
		// WebElement we =
		// driver.findElement(By.xpath("(//input[contains(@name,'textfield')])[2]"));
		// actions.moveToElement(we).click(we).build().perform();
		Thread.sleep(10000);
		waitForLoad(driver, 120);
		String pageLabel = null;
		try {
		pageLabel = driver.findElement(By.xpath("(//*[@class='x-grid-cell-inner '])[4]")).getText();
		}
		 catch (Exception e1) {
			 TestResultStatus.mpaskeysnew.put(methodName, "Unable get data value");
			 Reporter.log("Unable get data value");
				Add_Log.info("Unable get data value");
			 Assert.fail();
			}
		waitForLoad(driver, 300);
		System.out.println(pageLabel);

		if (pageLabel.contains(ids)) {
			Reporter.log("Successfully Sort Value");
			Add_Log.info("Successfully Sort Value");
		} else {
			Reporter.log("Unable to Sort value");
			Add_Log.info("Unable to Sort value");
			TestResultStatus.mpaskeysnew.put(methodName, "Unable to Sort value");
			flag = true;
			Assert.fail();
		}
		if (flag) {
			allFlag = true;
		}
		
		return allFlag;

	}

	public boolean CheckSortandFilteronly22(WebDriver driver,String methodName,String viewName, String ReportName, String RiskMetric,
			String ids, String Verify)throws InterruptedException {
		boolean flag = false;
		waitForElementPresent(driver, 120, expand_btn);
		waitForLoad(driver, 120);
		By ele = By.xpath("//span[text()='Relationship']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to Summary' and select " + viewName + " aggregation");
		Thread.sleep(2000);
		// click(driver, filter_btn);
		// Thread.sleep(2000);
		waitForLoad(driver, 120);
		Thread.sleep(5000);
		By tabledata = By.xpath("//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		waitForElementPresent(driver, 90, tabledata, Verify);
		By sort = By.xpath("//label[text()='" + viewName + "']//following::span[contains(@class,'filterswitch')]");
		Thread.sleep(2000);
		click(driver, sort,
				"by drilling down to " + ReportName + " and select " + viewName + " and click on Sort/Filters");
		Thread.sleep(1000);

		try {
			setText(driver, sortname, ids);
			waitForLoad(driver, 900);
		} catch (Exception e) {
			click(driver, sort,
					"by drilling down to " + ReportName + " and select " + viewName + " and click on Sort/Filters");
			Thread.sleep(3000);
			setText(driver, sortname, ids);
			waitForLoad(driver, 900);
		}
		Thread.sleep(30000);
		String pageLabel = null;
		try {
		pageLabel = driver.findElement(By.xpath("(//*[@class='x-grid-cell-inner '])[4]")).getText();
		}
		 catch (Exception e1) {
			 TestResultStatus.mpaskeysnew.put(methodName, "Unable get data value");
			 Reporter.log("Unable get data value");
				Add_Log.info("Unable get data value");
			 Assert.fail();
			}
		System.out.println(pageLabel);

		if (pageLabel.contains(ids)) {
			Reporter.log("Successfully Sort Value");
			Add_Log.info("Successfully Sort Value");
		} else {
			Assert.fail();
			Reporter.log("Unable to Sort value");
			Add_Log.info("Unable to Sort value");
			TestResultStatus.mpaskeysnew.put(methodName, "Unable to Sort value");
			flag = true;
		}
		if (flag) {
			allFlag = true;
		}
		return allFlag;

	}

	public void dataVisualizationCheck1(WebDriver driver,String methodName,String actualView, String reportingPeriod, String viewName)
			throws InterruptedException {
		String[] actualViews = actualView.split(",");
		List<String> xviews = new ArrayList<>();
		boolean flag = false;

		waitForLoad(driver, 60);
		waitForElementPresent(driver, 90, dataVisualizationHeader);
		int size = driver.findElements(By.xpath(
				"//div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto')]"))
				.size();
		for (int i = 1; i <= size; i++) {
			xviews.add(getText(driver, By.xpath("(" + DATAVISUALIZATIONFIELDS + ")[" + i + "]"),
					" Filter criteria #" + i));
		}
		System.out.println(xviews + " New View");

		for (int i1 = 0; i1 < actualViews.length; i1++) {
			// System.out.println(actualViews[i1] + " Actual");

			if (xviews.contains(actualViews[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViews[i1].trim().toString()
						+ " filter criteria is displayed in Data Visualisation Criteria screen");
				Reporter.log("Successfully displayed " + actualViews[i1].trim().toString()
						+ " filter criteria is displayed in Data Visualisation Criteria screen");
			} else {
				Add_Log.info(actualViews[i1].trim().toString()
						+ " filter criteria is not displayed in Data Visualisation Criteria screen");
				Reporter.log(actualViews[i1].trim().toString()
						+ " filter criteria is not displayed in Data Visualisation Criteria screen");
				TestResultStatus.mpaskeysnew.put(methodName, actualViews[i1].trim().toString()
						+ " filter criteria is not displayed in Data Visualisation Criteria screen");
				flag = true;
			}
		}
	}

}

