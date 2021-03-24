package pageobjects;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import property.IHomePage;
import testsuitebase.TestResultStatus;
import utility.SeleniumUtils;
import utility.WebPageElements;

public class TimePerfp extends SeleniumUtils implements IHomePage {
	public DecimalFormat df = new DecimalFormat("#.##");
	public String testName = null;
	public boolean Testskip = false;
	public boolean Testfail = false;
	SoftAssert s_assert = null;
	public double finish, start;
	public double end;
	boolean allFlag = false;

	public void Snackbar(WebDriver driver) throws InterruptedException {
		try {
			By ErrorSnackBar = By.xpath("//img[@class='StausClose']");
			if (driver.findElements(ErrorSnackBar).size() > 0) {
				WebElement errormessage = driver.findElement(By.xpath("//img[@class='StausClose']/following::span[1]"));
				Reporter.log("Status Bar Appear with message: " + errormessage.getText());
				Add_Log.info("Status Bar Appear with message: " + errormessage.getText());
				// Assert.fail();
			}
		} catch (NoSuchElementException e1) {

		}
	}

	public String summaryGridLoadTime(WebDriver driver, String methodName, String viewName, String reportingPeriod,
			String Verify) throws InterruptedException {

		waitForElementPresent(driver, 300, expand_btn1);
		By ele = By.xpath("//span[text()='Summary']//following::span[text()='" + viewName + "']");
		click(driver, ele, viewName + " option under Summary");
		Thread.sleep(2000);
		waitForLoad(driver, 300);
		Thread.sleep(2000);
		click(driver, filter_btn);
		Thread.sleep(2000);
		start = System.currentTimeMillis();
		waitForLoad(driver, 300);
		By tabledata = By
				.xpath("//label[text()='" + Verify + "']//following::tr[2]/td/div[@class='x-grid-cell-inner ']");
		end = System.currentTimeMillis();
		try {
			waitForElementPresent(driver, 90, tabledata, Verify);
			driver.findElement(tabledata).isDisplayed();
			Add_Log.info("Successfully able to launch " + viewName + " Summary report");
			Reporter.log("Successfully able to launch " + viewName + " Summary report");

		} catch (Exception e) {
			Add_Log.info("Not able to launch " + viewName + " summary report");
			Reporter.log("Not able to launch " + viewName + " summary report");
			TestResultStatus.mpaskeysnew.put(methodName, "Not able to launch " + viewName + " summary report");
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
		double totalTime = ((end - start) + 2000) / 1000;
		String totaltime = df.format(totalTime);
		System.out.println("total time to load " + totaltime);
		return totaltime;

	}

	public String downloadLoadTime(WebDriver driver, String methodName, String ReportName, String viewName,
			String RiskMetric, String Fileformat, String reportingPeriod, String ID, String Environment, String Verify)
			throws InterruptedException {

		String csvFileName = null;
		String id = null;
		String name = null;
		String name2 = null;
		String tabledata = null;
		String downloadIcon = null;
		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";

		if (ReportName.equalsIgnoreCase("Summary")) {
			csvFileName = "Summary";
			tabledata = "//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']";
			downloadIcon = "//label[text()='" + viewName + "']//following::span[contains(@class,'icon-" + Fileformat
					+ "')]";
		} else if (ReportName.equalsIgnoreCase("Work-Stream Drill-Down [Exposure]")) {
			csvFileName = "ExposureSummary";
			tabledata = "//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']";
			downloadIcon = "//*[@class ='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-" + Fileformat + " ']";
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

		}
		WebPageElements cagid_ddl = new WebPageElements(name, "xpath", id);
		File dir = new File(downloadPath);

		for (File file : dir.listFiles()) {
			if (file.getName().contains("Summary")) {
				// file.delete();
				// Reporter.log("Successfully Deleted Existing File before clicking on
				// download");
				// Add_Log.info("Successfully Deleted Existing File before clicking on
				// download");
			}
		}
		waitForLoad(driver, 300);
		Thread.sleep(1000);
		if (ReportName.equalsIgnoreCase("Summary")) {
			waitForElementPresent(driver, 300, expand_btn1);
			By ele = By.xpath("//span[text()='Summary']//following::span[text()='" + viewName + "']");
			click(driver, ele, viewName + " option under Summary");
			Thread.sleep(2000);
			waitForLoad(driver, 900);
		} else if (ReportName.equalsIgnoreCase("Work-Stream Drill-Down [Exposure]")) {
			waitForElementPresent(driver, 120, expand_btn);
			click(driver, expand_btn);
			Thread.sleep(1000);
			By ele = By.xpath(
					"//span[text()='Work-Stream Drill-Down [Exposure]']//following::span[text()='" + viewName + "']");
			click(driver, ele, viewName + " option under Work-Stream Drill-Down [Exposure]");
			Thread.sleep(2000);
			waitForLoad(driver, 900);
			Thread.sleep(2000);
			waitForElementPresent(driver, 900, cagid_ddl);
			click(driver, cagid_ddl);
			setText(driver, cagid_ddl, ID);
			Thread.sleep(2000);
			waitForElementPresent(driver, 120, riskmetric_ddl);
			click(driver, riskmetric_ddl);
			Thread.sleep(2000);
			By riskMetricValue = By.xpath("//li[text()='" + RiskMetric + "']");
			click(driver, riskMetricValue, " value " + RiskMetric + "in Risk Metric Drop down list");
			Thread.sleep(2000);
			waitForLoad(driver, 900);
		}
		Thread.sleep(2000);
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 900);

		By tabledatast = By.xpath(tabledata);
		try {
			waitForElementPresent(driver, 90, tabledata, Verify);
			driver.findElement(tabledatast).isDisplayed();
			Add_Log.info("Successfully able to launch " + viewName + " " + ReportName + " report");
			Reporter.log("Successfully able to launch " + viewName + " " + ReportName + " report");

		} catch (Exception e) {
			Add_Log.info("Not able to launch " + viewName + " " + ReportName + " report");
			Reporter.log("Not able to launch " + viewName + " " + ReportName + " report");
			TestResultStatus.mpaskeysnew.put(methodName,
					"Not able to launch " + viewName + " " + ReportName + " report");
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
		By downloadIconst = By.xpath(downloadIcon);
		waitForElementPresent(driver, 90, downloadIconst, Fileformat + " icon " + viewName);
		clicknosleep(driver, downloadIconst, Fileformat + " icon " + viewName);
		Add_Log.info("Waiting for file download to complete") ;
		Reporter.log("Waiting for file download to complete") ;
		start = System.currentTimeMillis();
		String totalTime = null;
		long beforeCount = 0;
		try {
			beforeCount = Files.list(Paths.get(downloadPath)).count();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
		//	System.out.println(beforeCount);
			long afterCount = beforeCount;
			int i = 1;
			while (beforeCount >= afterCount ) {
				afterCount = Files.list(Paths.get(downloadPath)).count();
				
				
				
			System.out.println(i++  + "second");
			
				if (i >= 30000) // added for validation
				{
					Add_Log.info("Unable to download the file time over 120 sec for " +  ReportName + "  > " +  viewName + "  > "+ name2 + "  > "+ ID + "  > "+ RiskMetric) ;
					Reporter.log("Unable to download the file time over 120 sec for " +  ReportName + "  > " +  viewName + "  > "+ name2 + "  > "+ ID + "  > "+ RiskMetric) ;
					TestResultStatus.mpaskeysnew.put(methodName,"Unable to download the file time over 120 sec for " +  ReportName + "  > " +  viewName + "  > "+ name2 + "  > "+ ID + "  > "+ RiskMetric) ;
					TestResultStatus.Testfail = true;
					Assert.fail();
					break;
					
				}
				
				
			}

			end = System.currentTimeMillis();	
			System.out.println("End");
			Double tt = (end - start) / 1000;
			totalTime = df.format(tt);
			System.out.println(totalTime);
			System.out.println("Time took to download report:" + totalTime + " seconds");
			TimeUnit.SECONDS.sleep(45);
		
		/*
		 * start = System.currentTimeMillis(); String totalTime = null; long beforeCount
		 * = 0; try { beforeCount = Files.list(Paths.get(downloadPath)).count(); } catch
		 * (IOException e1) { // TODO Auto-generated catch block e1.printStackTrace(); }
		 * 
		 * try { //System.out.println(beforeCount); // long afterCount = beforeCount;
		 * 
		 * 
		 * 
		 * while (beforeCount >= afterCount) { int i = 1; Thread.sleep(4000); afterCount
		 * = Files.list(Paths.get(downloadPath)).count(); // System.out.println(i++); if
		 * (start >= 20000000) // added for validation { //Assert.fail(); //break;
		 * 
		 * } System.out.println(start); end = System.currentTimeMillis();
		 * System.out.println(end + "end"); Double tt = (end - start) / 1000; totalTime
		 * = df.format(tt); System.out.println(df.format(start) + " format");
		 * System.out.println(totalTime);
		 * 
		 * System.out.println("Time took to download report:" + totalTime + " seconds");
		 * TimeUnit.SECONDS.sleep(45);
		 */

		String fileName = latestFileName();

		System.out.println(fileName + " & " + viewName);
		if (fileName.contains("Summary") || fileName.contains("tmp")) {
			Add_Log.info("Successfully downloaded " + Fileformat + " report for " + viewName + " " + ReportName
					+ " Report Time took to download " + totalTime + " sec");
			Reporter.log("Successfully downloaded " + Fileformat + " report for " + viewName + " " + ReportName
					+ " Report Time took to download " + totalTime + " sec");
		} else {
			Add_Log.info(Fileformat + " report not downloaded for " + viewName + " " + ReportName + " report");
			Reporter.log(Fileformat + " report not downloaded for " + viewName + " " + ReportName + " report");
			TestResultStatus.mpaskeysnew.put(methodName,
					Fileformat + " report not downloaded for " + viewName + " " + ReportName + " report");
			TestResultStatus.Testfail = true;
			Assert.fail();
		}
	}catch(

	Exception e)
	{
		Add_Log.info("Excel report not downloaded for " + viewName + " " + ReportName);
		Reporter.log("Excel report not downloaded for " + viewName + " " + ReportName);
		TestResultStatus.mpaskeysnew.put(methodName, "Excel report not downloaded for " + viewName + " " + ReportName);
		Assert.fail();
	}return totalTime;

	}

	public String exposureGridLoadTime(WebDriver driver, String methodName, String viewName, String reportingPeriod,
			String ID, String RiskMetric, String Verify) throws InterruptedException {
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
		waitForElementPresent(driver, 120, expand_btn);
		click(driver, expand_btn);
		Thread.sleep(1000);
		By ele = By.xpath(
				"//span[text()='Work-Stream Drill-Down [Exposure]']//following::span[text()='" + viewName + "']");
		click(driver, ele, viewName + " option under Work-Stream Drill-Down [Exposure]");
		Thread.sleep(5000);
		By collpase = By.xpath("//div[text()='" + viewName + " Summary']");
		click(driver, collpase, viewName + " Collapse Summary");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		Thread.sleep(2000);
		waitForElementPresent(driver, 900, cagid_ddl);
		click(driver, cagid_ddl);
		setText(driver, cagid_ddl, ID);
		Thread.sleep(2000);
		waitForElementPresent(driver, 900, riskmetric_ddl);
		click(driver, riskmetric_ddl);
		Thread.sleep(2000);
		By riskMetricValue = By.xpath("//li[text()='" + RiskMetric + "']");
		click(driver, riskMetricValue, " value " + RiskMetric + "in Risk Metric Drop down list");
		WebElement element = driver.findElement(By.xpath("//span[text()='" + name2 + "']//following::div[2]"));
		System.out.println(element.getCssValue("border-bottom-color"));
		String color = element.getCssValue("border-bottom-color");
		if (color.equals("rgba(76, 169, 119, 1)")) {
			Reporter.log("Successfully Matching found under " + name);
			Add_Log.info("Successfully Matching found under " + name);
		} else {
			flag2 = true;
		}
		waitForLoad(driver, 900);
		click(driver, filter_btn);
		Thread.sleep(2000);
		start = System.currentTimeMillis();
		waitForLoad(driver, 2000);
		By tabledata = By
				.xpath("//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		Thread.sleep(1000);
		waitForElementPresent(driver, 90, tabledata, Verify);
		WebElement pageLabel = driver.findElement(tabledata);
		WebDriverWait wait = new WebDriverWait(driver, 500);
		wait.until(ExpectedConditions.visibilityOf(pageLabel));
		end = System.currentTimeMillis();
		try {
			waitForElementPresent(driver, 90, tabledata, viewName);
			driver.findElement(tabledata).isDisplayed();
			Add_Log.info("Successfully able to launch " + viewName + " Exposure report");
			Reporter.log("Successfully able to launch " + viewName + " Exposure report");

		} catch (Exception e) {
			Add_Log.info("Not able to launch " + viewName + " Exposure report");
			Reporter.log("Not able to launch " + viewName + " Exposure report");
			TestResultStatus.mpaskeysnew.put(methodName, "Not able to launch " + viewName + " Exposure report");
			TestResultStatus.Testfail = true;
			Snackbar(driver);
			Assert.fail();
		}
		if (flag2) {
			TestResultStatus.Testfail = true;
			Reporter.log("No Matching found under " + name);
			Add_Log.info("No Matching found under " + name);
			// Assert.fail();
		}

		double totalTime = ((end - start) + 2000) / 1000;
		String totaltime = df.format(totalTime);
		System.out.println("total time to load " + totaltime);
		return totaltime;

	}

}

