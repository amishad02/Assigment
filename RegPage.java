package pageobjects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import property.IHomePage;
import testsuitebase.TestResultStatus;
import utility.SeleniumUtils;
import utility.WebPageElements;

public class RegPage extends SeleniumUtils implements IHomePage {
	public DecimalFormat df = new DecimalFormat("#.##");
	public String testName = null;
	public boolean Testskip = false;
	public boolean Testfail = false;
	SoftAssert s_assert = null;
	public double finish, start;
	public double end;
	boolean allFlag = false;
	private String value;

	public void rundescript(WebDriver driver,String methodName, String RunDescription) throws InterruptedException {

		waitForElementPresent(driver, 90, rundescription_ddl);
		click(driver, rundescription_ddl);
		Thread.sleep(2000);
		By rundescriptionvalue = By.xpath("//li[text()='" + RunDescription + "']");
		click(driver, rundescriptionvalue, RunDescription + " value in Run Description Drop down list");
		Thread.sleep(4000);
	}

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
		waitForElementPresent(driver, 90, filter, actualViewsp[2]);
		driver.findElement(filter).click();
		try {
		click(driver, filter, actualViews);
	}
		catch (Exception e) {
		}
	}

	public void summaryGridLoad(WebDriver driver,String methodName,String viewName, String Verify) throws InterruptedException {

		waitForElementPresent(driver, 900, expand_btn1);
		By ele = By.xpath("//span[text()='Summary']//following::span[text()='" + viewName + "']");
		click(driver, ele, viewName + " option under Summary");
		Thread.sleep(2000);
		waitForLoad(driver, 200);
		Thread.sleep(2000);
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 200);
		By tabledata = By.xpath("//label[text()='" + Verify  + "']//following::tr[4]/td/div[@class='x-grid-cell-inner ']");
		System.out.println(tabledata);
		waitForElementPresent(driver, 90, tabledata, Verify );
	
		try {
			driver.findElement(tabledata).isDisplayed();
			Add_Log.info("Successfully able to launch " + viewName + " Summary report");
			Reporter.log("Successfully able to launch " + viewName + " Summary report");

		} catch (Exception e) {
			Add_Log.info("Not able to launch " + viewName + " summary report");
			Reporter.log("Not able to launch " + viewName + " summary report");
			TestResultStatus.mpaskeysnew.put(methodName,"Not able to launch " + viewName + " summary report");
			TestResultStatus.Testfail = true;
			Snackbar(driver);
			Assert.fail();
		}

	}

	public void summaryGridLoadfile(WebDriver driver,String methodName, String viewName, String Fileformat, String Environment, String Verify)
			throws InterruptedException {

		String csvFileName = viewName;
		// String downloadPath = System.getProperty("user.dir") +
		// "\\src\\main\\resources\\excelfiles";
		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
		;

		File dir = new File(downloadPath);

		for (File file : dir.listFiles()) {
			if (file.getName().contains("Summary")) {
				file.delete();
				Reporter.log("Successfully Deleted Existing File before clicking on download");
				Add_Log.info("Successfully Deleted Existing File before clicking on download");
			}
		}
		waitForLoad(driver, 120);
		Thread.sleep(1000);
		waitForElementPresent(driver, 900, expand_btn1);
		By ele = By.xpath("//span[text()='Summary']//following::span[text()='" + viewName + "']");
		click(driver, ele, viewName + " option under Summary");
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		Thread.sleep(2000);
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 500);
		By tabledata = By.xpath("//label[text()='" + Verify  + "']//following::tr[2]/td/div[@class='x-grid-cell-inner ']");
		waitForElementPresent(driver, 90, tabledata, Verify );
		try {
			driver.findElement(tabledata).isDisplayed();
			Add_Log.info("Successfully able to launch " + viewName + " Summary report");
			Reporter.log("Successfully able to launch " + viewName + " Summary report");

		} catch (Exception e) {
			Add_Log.info("Not able to launch " + viewName + " summary report");
			Reporter.log("Not able to launch " + viewName + " summary report");
			TestResultStatus.mpaskeysnew.put(methodName,"Not able to launch " + viewName + " summary report");
			TestResultStatus.Testfail = true;
			Snackbar(driver);
			Assert.fail();
		}

		By downloadIcon = By.xpath(
				"//label[text()='" + viewName + "']//following::span[contains(@class,'icon-" + Fileformat + "')]");
		waitForElementPresent(driver, 90, downloadIcon, Fileformat + " icon " + viewName);
		click(driver, downloadIcon, Fileformat + " icon " + viewName);
		start = System.currentTimeMillis();
		String totalTime = null;

		try {
			long beforeCount = Files.list(Paths.get(downloadPath)).count();
			System.out.println(beforeCount);
			long afterCount = beforeCount;
			int i = 1, n = 0;
			while (beforeCount >= afterCount) {
				Thread.sleep(1000);
				afterCount = Files.list(Paths.get(downloadPath)).count();
				// afterCount =
				// Files.list(Paths.get("./src/main/resources/excelfiles")).count();
				// System.out.println(i++);
				i++;
			}
			end = System.currentTimeMillis();

			Double tt = (end - start) / 1000;
			totalTime = df.format(tt);
			System.out.println(totalTime);
			System.out.println("Time took to download report:" + i + " seconds");
			Thread.sleep(19000);

			int j = 0;
			String fileName = latestFileName();
			while (fileName.contains("crdownload")) {
				Thread.sleep(1000);
				fileName = latestFileName();
				j++;
			}
			System.out.println(fileName + " & " + viewName);
			if (fileName.contains("Summary")) {
				Add_Log.info("Successfully downloaded " + Fileformat + " report for " + viewName
						+ " Summary Report Time took to download " + i + " sec");
				Reporter.log("Successfully downloaded " + Fileformat + " report for " + viewName
						+ " Summary Report Time took to download " + i + " sec");
			} else {
				Add_Log.info(Fileformat + " report not downloaded for " + viewName + " Exposure report");
				Reporter.log(Fileformat + " report not downloaded for " + viewName + " Exposure report");
				TestResultStatus.mpaskeysnew.put(methodName,Fileformat + " report not downloaded for " + viewName + " Exposure report");
				TestResultStatus.Testfail = true;
				Assert.fail();
			}
		} catch (Exception e) {
			Add_Log.info("Excel report not downloaded");
			Reporter.log("Excel report not downloaded");
			TestResultStatus.mpaskeysnew.put(methodName,"Excel report not downloaded");
			Assert.fail();
		}

	}

	public void exposureGridLoad(WebDriver driver,String methodName, String viewName, String ID, String RiskMetric,String Verify)
			throws InterruptedException {
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
		waitForElementPresent(driver, 900, expand_btn);
		click(driver, expand_btn);
		Thread.sleep(1000);
		By ele = By.xpath(
				"//span[text()='Work-Stream Drill-Down [Exposure]']//following::span[text()='" + viewName + "']");
		click(driver, ele, viewName + " option under Work-Stream Drill-Down [Exposure]");
		Thread.sleep(2000);
		// By collpase = By.xpath("//div[text()='" + viewName + " Summary']");
		// click(driver, collpase, viewName + " Collapse Summary");
		Thread.sleep(2000);
		waitForLoad(driver, 30);
		Thread.sleep(2000);
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
		waitForLoad(driver, 900);
		By tabledata = By.xpath("//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		waitForElementPresent(driver, 90, tabledata, Verify);
		try {
			driver.findElement(tabledata).isDisplayed();
			Add_Log.info("Successfully able to launch " + viewName + " Exposure report");
			Reporter.log("Successfully able to launch " + viewName + " Exposure report");

		} catch (Exception e) {
			Add_Log.info("Not able to launch " + viewName + " Exposure report");
			Reporter.log("Not able to launch " + viewName + " Exposure report");
			TestResultStatus.mpaskeysnew.put(methodName,"Not able to launch " + viewName + " Exposure report");
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

	}

	public void DDLExpanddown(WebDriver driver,String methodName, String actualViews, String actualViews2, String Verify2)
			throws InterruptedException {
		String[] actualViewsp = actualViews.split(",");
		waitForElementPresent(driver, 900, expand_btn1);
		Thread.sleep(1000);
		By ddlselect = By.xpath("(//div[input[@name='display" + actualViews2 + "']]/following-sibling::div)[2]");
		waitForElementPresent(driver, 120, ddlselect, actualViews2);
		click(driver, ddlselect, actualViews2);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By titleheader = By.xpath("//*[text()='" + Verify2 + "']");
		waitForElementPresent(driver, 90, titleheader, Verify2 + " dialog box header");
		By expand = By.xpath("//span[text()='" + actualViewsp[0] + "']/preceding-sibling::img[2]");
		waitForElementPresent(driver, 60, expand, actualViewsp[0]);
		click(driver, expand, actualViewsp[0]);
		Thread.sleep(2000);
		waitForLoad(driver, 60);
		By expanddown = By.xpath("//span[text()='" + actualViewsp[1] + "']/preceding-sibling::input");
		waitForElementPresent(driver, 60, expanddown, actualViewsp[1]);
		click(driver, expanddown, actualViewsp[1]);
		Thread.sleep(2000);
		waitForLoad(driver, 60);
		if (IsCheckBoxSelected(driver, By.xpath("//span[text()='" + actualViewsp[1] + "']/preceding-sibling::input"))) {
			Add_Log.info("Successfully able to select " + actualViewsp[1] + " option from dashboard " + actualViews2
					+ "summary screen");
			Reporter.log("Successfully able to select " + actualViewsp[1] + " option from dashboard " + actualViews2
					+ "summary screen");
		} else {
			Add_Log.info(
					"Not able to select " + actualViewsp[1] + " option from dashboard " + actualViews2 + " summary screen");
			Reporter.log(
					"Not able to select " + actualViewsp[1] + " option from dashboard " + actualViews2 + " summary screen");
			TestResultStatus.mpaskeysnew.put(methodName,"Not able to select " + actualViewsp[1] + " option from dashboard " + actualViews2 + " summary screen");
			TestResultStatus.Testfail = true;
			Snackbar(driver);
			Assert.fail();
		}
		click(driver, apply_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 60);

	}

	public void DDLExpand1(WebDriver driver,String methodName, String actualViews, String actualViews2, String Verify2, String Verify )
			throws InterruptedException {
		String[] actualViewsp = actualViews.split(",");
		waitForElementPresent(driver, 900, expand_btn1);
		Thread.sleep(1000);
		By ddlselect = By.xpath("(//div[input[@name='display" + actualViews2 + "']]/following-sibling::div)[2]");
		waitForElementPresent(driver, 120, ddlselect, actualViews2);
		click(driver, ddlselect, actualViews2);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By titleheader = By.xpath("//*[text()='" + Verify2 + "']");
		waitForElementPresent(driver, 90, titleheader, Verify2 + " dialog box header");
		By expand = By.xpath("//span[contains(text(),'" + actualViewsp[0] + "')]/preceding-sibling::img[2]");
		waitForElementPresent(driver, 60, expand, actualViewsp[0]);
		click(driver, expand, actualViewsp[0]);
		Thread.sleep(2000);
		waitForLoad(driver, 60);
		By expanddown = By.xpath("//*[contains(text(),'" + actualViewsp[1] + "')]/preceding-sibling::input");
		waitForElementPresent(driver, 60, expanddown, actualViewsp[1]);
		click(driver, expanddown, actualViewsp[1]);
		Thread.sleep(2000);
		waitForLoad(driver, 60);
		if (IsCheckBoxSelected(driver,
				By.xpath("//*[contains(text(),'" + actualViewsp[1] + "')]/preceding-sibling::input"))) {
			Add_Log.info("Successfully able to select " + actualViewsp[1] + " option from dashboard " + actualViews2
					+ "summary screen");
			Reporter.log("Successfully able to select " + actualViewsp[1] + " option from dashboard " + actualViews2
					+ "summary screen");
		} else {
			Add_Log.info(
					"Not able to select " + actualViewsp[1] + " option from dashboard " + actualViews2 + " summary screen");
			Reporter.log(
					"Not able to select " + actualViewsp[1] + " option from dashboard " + actualViews2 + " summary screen");
			TestResultStatus.mpaskeysnew.put(methodName,"Not able to select " + actualViewsp[1] + " option from dashboard " + actualViews2 + " summary screen");
			TestResultStatus.Testfail = true;
			Snackbar(driver);
			Assert.fail();
		}
		click(driver, apply_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 60);

	}

	public void DDLdragdrop2(WebDriver driver,String methodName, String actualViews, String actualViews2, String Verify2, String Verify )
			throws InterruptedException {

		waitForElementPresent(driver, 900, expand_btn1);
		Thread.sleep(1000);
		By ddlselect = By.xpath("(//div[input[@name='display" + actualViews2 + "']]/following-sibling::div)[2]");
		waitForElementPresent(driver, 120, ddlselect, actualViews2);
		click(driver, ddlselect, actualViews2);
		Thread.sleep(2000);
		By titleheader = By.xpath("//*[text()='" + Verify2 + "']");
		Actions action = new Actions(driver);
		waitForElementPresent(driver, 120, titleheader, Verify2 + " dialog box header");
		By from = null;
		if (Verify2.equals("SENIOR RISK MANAGER"))
		{
		waitForVisiblility(driver, 120, By.xpath("//ul/li[3]"), " 3rd data from the row for "+ actualViews2 );
		from = By.xpath("//ul/li[3]");
		}
		else 
		{
		waitForVisiblility(driver, 120, By.xpath("//ul/li[2]"), " 1st data from the row for "+ actualViews2 );
		from = By.xpath("//ul/li[2]");
		}
			
		//By from = By.xpath("//ul/li[1]");
		click(driver, from, " 1st data from the row for "+ actualViews2 );
		Thread.sleep(2000);
		By addto = By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-default-small x-form-itemselector-add ']");
		click(driver, addto, "Add to Selected");
		Thread.sleep(2000);
		click(driver, apply_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 90);
		By textlabel2 = By.xpath("//*[text()='" + Verify2 + "']");
		WebElement textlabel = driver.findElement(By.xpath("//*[@name='display" + actualViews2 + "']"));
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOf(textlabel)).getAttribute("value");
		value = textlabel.getAttribute("value");
		
		if (value.isEmpty()) {
			
			Reporter.log("Not able to view selected value option from dashboard " + actualViews2 + " summary screen");
			Add_Log.info("Not able to view selected value option from dashboard " + actualViews2 + " summary screen");
			TestResultStatus.mpaskeysnew.put(methodName,"Not able to view selected value  option from dashboard " + actualViews2 + " summary screen");
			Snackbar(driver);
			Assert.fail();
			
		} else {	
			Reporter.log("Successfully able to view selected value = " + value + " option from dashboard " + actualViews2
					+ " summary screen");
			Add_Log.info("Successfully able to view selected value = " + value + " option from dashboard " + actualViews2
					+ " summary screen");			
		}

	}

	/*
	 * public void DDLdragdropxrr(WebDriver driver,String methodName, String actualViews, String
	 * Verify, String Verify2) throws InterruptedException {
	 * 
	 * waitForElementPresent(driver, 900, expand_btn1); Thread.sleep(1000); By
	 * ddlselect = By.xpath("(//div[input[@name='display" + Verify +
	 * "']]/following-sibling::div)[2]"); waitForElementPresent(driver, 120,
	 * ddlselect, Verify); click(driver, ddlselect, Verify); Thread.sleep(2000); By
	 * titleheader = By.xpath("//*[text()='" + Verify2 + "']"); Actions action = new
	 * Actions(driver); waitForElementPresent(driver, 120, titleheader, Verify2 +
	 * " dialog box header"); waitForVisiblility(driver, 10,
	 * By.xpath("//ul/li[text()='" + actualViews + "']"), actualViews); WebElement
	 * From = driver.findElement(By.xpath("//ul/li[text()='" + actualViews + "']"));
	 * WebElement To = driver.findElement(By.
	 * xpath("(//div[@class='x-boundlist x-boundlist-default'])[2]"));
	 * action.moveToElement(From).build().perform(); Thread.sleep(5000);
	 * action.doubleClick(From).build().perform(); Thread.sleep(2000); By ele1 = By.
	 * xpath("(//div[@class='x-boundlist x-boundlist-default'])[2]//ul/li[text()='"
	 * + actualViews + "']"); try { waitForElementPresent3(driver, 90, ele1,
	 * " selected " + actualViews); } catch (Exception e) {
	 * action.moveToElement(From).build().perform(); Thread.sleep(2000);
	 * action.doubleClick(From).build().perform(); Thread.sleep(2000);
	 * waitForElementPresent3(driver, 90, ele1, " selected " + actualViews); }
	 * 
	 * waitForLoad(driver, 90); click(driver, apply_btn); Thread.sleep(2000);
	 * waitForLoad(driver, 90); By textlabel2= By.xpath("//*[text()='" + Verify2 +
	 * "']"); WebElement textlabel =
	 * driver.findElement(By.xpath("//*[@name='display" + Verify + "']"));
	 * WebDriverWait wait = new WebDriverWait(driver, 120);
	 * wait.until(ExpectedConditions.visibilityOf(textlabel)).getAttribute("value");
	 * value =textlabel.getAttribute("value"); System.out.println(value
	 * +"********** "+ actualViews); if (value.equals(actualViews)) {
	 * Reporter.log("Successfully able to select " + actualViews +
	 * " option from dashboard " + Verify + "summary screen");
	 * Add_Log.info("Successfully able to select " + actualViews +
	 * " option from dashboard " + Verify + "summary screen"); } else {
	 * 
	 * Reporter.log( "Not able to select " + actualViews + " option from dashboard "
	 * + Verify + " summary screen"); Add_Log.info( "Not able to select " +
	 * actualViews + " option from dashboard " + Verify + " summary screen");
	 * Snackbar(driver); Assert.fail(); }
	 * 
	 * }
	 */

	public void DDLRelationshipnaged(WebDriver driver,String methodName, String actualViews) throws InterruptedException {

		waitForElementPresent(driver, 900, expand_btn1);
		Thread.sleep(1000);
		waitForElementPresent(driver, 120, relationship_managed_ddl);
		click(driver, relationship_managed_ddl);
		Thread.sleep(1000);
		waitForVisiblility(driver, 120,
				By.xpath("///span[contains(text(),'" + actualViews + "')]/preceding-sibling::input"), actualViews);
		click(driver, By.xpath("//span[contains(text(),'" + actualViews + "')]/preceding-sibling::input"), actualViews);
		Thread.sleep(1000);
		waitForLoad(driver, 120);
		if (IsCheckBoxSelected(driver,
				By.xpath("//span[contains(text(),'" + actualViews + "')]/preceding-sibling::input"))) {
			Add_Log.info(
					"Successfully able to select " + actualViews + " Managed geography option from summary screen");
			Reporter.log(
					"Successfully able to select " + actualViews + " Managed geography option from summary screen");
		} else {
			Add_Log.info("Not able to select " + actualViews + " Managed geography option from summary screen");
			Reporter.log("Not able to select " + actualViews + " Managed geography option from summary screen");
			TestResultStatus.mpaskeysnew.put(methodName,"Not able to select " + actualViews + " Managed geography option from summary screen");
			TestResultStatus.Testfail = true;
			Snackbar(driver);
			Assert.fail();
		}
		click(driver, apply_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 60);

	}

	public void DDList(WebDriver driver,String methodName, String actualViews, String actualViews2, String Verify ) throws InterruptedException {

		waitForElementPresent(driver, 900, expand_btn1);
		Thread.sleep(1000);
		By ddlselect = By.xpath("//div[input[@name='" + actualViews2 + "']]/following-sibling::div");
		waitForElementPresent(driver, 120, ddlselect, actualViews2);
		click(driver, ddlselect, actualViews2);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By ddlexpand = By.xpath("//ul/li[text()='" + actualViews + "']");
		waitForElementPresent(driver, 120, ddlexpand, actualViews);
		click(driver, ddlexpand, actualViews);
		Thread.sleep(2000);
		String selectedText = driver.findElement(By.cssSelector("li.x-boundlist-selected")).getAttribute("textContent");
		System.out.println(selectedText);
		if (selectedText.equals(actualViews)) {
			Reporter.log("Successfully able to select " + actualViews + " option from dashboard " + actualViews2
					+ "summary screen");
			Add_Log.info("Successfully able to select " + actualViews + " option from dashboard " + actualViews2
					+ "summary screen");
		} else {

			Reporter.log("Not able to select " + actualViews + " option from dashboard " + actualViews2 + " summary screen");
			Add_Log.info("Not able to select " + actualViews + " option from dashboard " + actualViews2 + " summary screen");
			TestResultStatus.mpaskeysnew.put(methodName,"Not able to select " + actualViews + " option from dashboard " + actualViews2 + " summary screen");
			Snackbar(driver);
			Assert.fail();
		}

	}

	public void summaryGridExposurereport(WebDriver driver,String methodName, String viewName, String actualViews, String Report,String Verify)
			throws InterruptedException {
		Actions action = new Actions(driver);
		String[] actualViewsp = actualViews.split(",");
		summaryGridLoad(driver,methodName, viewName, Verify);
		Thread.sleep(2000);
		clicktable(driver, actualViews);		
		Thread.sleep(5000);
		waitForLoad(driver, 900);
		By exposurereport = By.xpath("//label[text()='" + Report + "']");
		Thread.sleep(5000);
		waitForElementPresent(driver, 120, exposurereport, Report);
		try {
			driver.findElement(exposurereport).isDisplayed();
			Add_Log.info("Successfully able to view " + Report + " report");
			Reporter.log("Successfully able to view " + Report + " report");
	 

		} catch (Exception e) {
			Add_Log.info("Not able to launch " + Report + " report");
			Reporter.log("Not able to launch " + Report + " report");
			TestResultStatus.mpaskeysnew.put(methodName,"Not able to launch " + Report + " report");
			TestResultStatus.Testfail = true;
			Snackbar(driver);
			Assert.fail();
		}
	}

	public void summaryGridheader(WebDriver driver,String methodName, String viewName, String actualViews, String Report, String Verify)
			throws InterruptedException {
		Actions action = new Actions(driver);
		String[] actualViewsp = actualViews.split(",");
		summaryGridLoad(driver, methodName,viewName, Verify);
		Thread.sleep(2000);
		By filter1 = By.xpath(
				"//*[contains(@class,'x-column-header x-column-header-align-center x-box-item x-column-header-default x-box-layout-ct x-unselectable x-group-header')]//*[text()='"
						+ actualViewsp[0] + "']");
		By filter2 = By.xpath(
				"//*[contains(@class,'x-column-header x-column-header-align-center x-box-item x-column-header-default x-box-layout-ct x-unselectable x-group-header')]//*[text()='"
						+ actualViewsp[1] + "']");
		By filter3 = By.xpath(
				"//*[contains(@class,'x-column-header x-column-header-align-center x-box-item x-column-header-default x-box-layout-ct x-unselectable x-group-header')]//*[text()='"
						+ actualViewsp[2] + "']");
		By filter4 = By.xpath(
				"//*[contains(@class,'x-column-header x-column-header-align-center x-box-item x-column-header-default x-box-layout-ct x-unselectable x-group-header')]//*[text()='"
						+ actualViewsp[3] + "']");
//		By filter5 = By.xpath("//*[contains(@class,'x-column-header x-column-header-align-center x-box-item x-column-header-default x-box-layout-ct x-unselectable x-group-header')]//*[text()='"+ actualViewsp[4] + "']");
		By filter6 = By.xpath(
				"//*[contains(@class,'x-column-header x-column-header-align-center x-box-item x-column-header-default x-box-layout-ct x-unselectable x-group-header')]//*[text()='"
						+ actualViewsp[5] + "']");
		By filter7 = By.xpath(
				"//*[contains(@class,'x-column-header x-column-header-align-center x-box-item x-column-header-default x-box-layout-ct x-unselectable x-group-header')]//*[text()='"
						+ actualViewsp[6] + "']");
//	System.out.println(filter1);
//	System.out.println(filter2);
//	System.out.println(filter3);
//	System.out.println(filter4);
//	System.out.println(filter5);
//	System.out.println(filter6);
//	System.out.println(filter7);
		try {
			waitfordisplay(driver, 20, filter1, actualViewsp[0] );
			waitfordisplay(driver, 20, filter2, actualViewsp[1] );
			waitfordisplay(driver, 20, filter3, actualViewsp[2] );
			waitfordisplay(driver, 20, filter4, actualViewsp[3] );
		//	waitfordisplay(driver, 20, filter5, actualViewsp[4] );
			waitfordisplay(driver, 20, filter6, actualViewsp[5] );
			waitfordisplay(driver, 20, filter7, actualViewsp[6] );
			/*driver.findElement(filter1).isDisplayed();
			driver.findElement(filter2).isDisplayed();
			driver.findElement(filter3).isDisplayed();
			driver.findElement(filter4).isDisplayed();
			driver.findElement(filter5).isDisplayed();
			driver.findElement(filter6).isDisplayed();
			driver.findElement(filter7).isDisplayed();*/
			Add_Log.info("Successfully able to view " + actualViews + " Summary Column's");
			Reporter.log("Successfully able to view " + actualViews + " Summary Column's");

		} catch (Exception e) {
			Add_Log.info("Not able to view " + actualViews + " Summary Column's");
			Reporter.log("Not able to view " + actualViews + " Summary Column's");
			TestResultStatus.mpaskeysnew.put(methodName,"Not able to view " + actualViews + " Summary Column's");
			TestResultStatus.Testfail = true;
			Snackbar(driver);
			Assert.fail();
		}

	}

	@SuppressWarnings("unlikely-arg-type")
	public void summaryGridExposureFilter(WebDriver driver,String methodName, String viewName, String actualViews, String Report,String actualViews2,String Verify,String Verify2) throws InterruptedException {
		boolean flag = true;

		summaryGridExposurereport(driver,methodName, viewName, actualViews, Report,Verify);
		List<WebElement> options = driver.findElements(By.xpath(ExposureSUMMARYFIELD));
		for (WebElement ele : options) {
			System.out.println(ele.getAttribute(("data-qtip")));
		}
		List<String> views = new ArrayList<>();
		String[] actualViews2p = actualViews2.split(",");
		waitForElementPresent(driver, 90, summaryFields);
		int size = driver.findElements(By.xpath(SUMMARYFIELDS)).size();
		for (int i = 1; i <= size; i++) {
			views.add(getText(driver, By.xpath("(" + SUMMARYFIELDS + ")[" + i + "]"), "  Summary Feild Content #" + i));
		}
		for (int i = 0; i < actualViews2p.length; i++) {
			if (views.get(i).equals(actualViews2p[i].trim().toString())) {
				// if (views.equals(Verify1[i].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViews2p[i].trim().toString() + " Summary Feild Content under "
						+ viewName + Report);
				Reporter.log("Successfully displayed " + actualViews2p[i].trim().toString() + " Summary Feild Content under "
						+ viewName + Report);
			} else {
				Add_Log.info(
						actualViews2p[i].trim().toString() + " is not displayed under Summary Feild " + viewName + Report);
				Reporter.log(
						actualViews2p[i].trim().toString() + " is not displayed under Summary Feild " + viewName + Report);
				TestResultStatus.mpaskeysnew.put(methodName,actualViews2p[i].trim().toString() + " is not displayed under Summary Feild " + viewName + Report);
				Snackbar(driver);
			//	Assert.fail();
			}
		}

		List<String> views2 = new ArrayList<>();
		String[] Verify2p1 = Verify2.split(",");
		waitForElementPresent(driver, 90, exposuresummaryfield);
		// int size1 = driver.findElements(By.xpath(ExposureSUMMARYFIELD)).size();
		List<WebElement> options2 = driver.findElements(By.xpath(ExposureSUMMARYFIELD));
		for (WebElement ele : options2) {
			views2.add(ele.getAttribute(("data-qtip")));
		}
		System.out.println(views2);
		Add_Log.info("Successfully fetched text" + views2 + " under Expsoure Summary Feild Content");
		Reporter.log("Successfully fetched text" + views2 + " under Expsoure Summary Feild Content");

		for (int i = 0; i < Verify2p1.length; i++) {
			if (views2.contains(Verify2p1[i].trim().toString())) {
				Add_Log.info("Successfully displayed " + Verify2p1[i].trim().toString()
						+ " Expsoure Summary Feild Content under " + viewName + Report);
				Reporter.log("Successfully displayed " + Verify2p1[i].trim().toString()
						+ " Expsoure Summary Feild Content under " + viewName + Report);
			} else {
				Add_Log.info(
						Verify2p1[i].trim().toString() + "*** Expsoure Summary Feild Content under " + viewName + Report);
				Reporter.log(
						Verify2p1[i].trim().toString() + "*** Expsoure Summary Feild Content under " + viewName + Report);
			//	TestResultStatus.mpaskeysnew.put(methodName,Verify2p1[i].trim().toString() + " Expsoure Summary Feild Content under " + viewName + Report);
			//	Assert.fail();
			}

		}

	}
	@SuppressWarnings("unlikely-arg-type")
	public void summaryGridExposureFilternew(WebDriver driver,String methodName, String viewName, String actualViews, String Report,String actualViews2,String Verify,String Verify2) throws InterruptedException {
		boolean flag = true;

		waitForElementPresent(driver, 900, expand_btn1);
		By ele = By.xpath("//span[text()='Summary']//following::span[text()='" + viewName + "']");
		click(driver, ele, viewName + " option under Summary");
		Thread.sleep(2000);
		waitForLoad(driver, 200);
		Thread.sleep(2000);
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 200);
		By tabledata = By.xpath("//label[text()='" + Verify  + "']//following::tr[2]/td/div[@class='x-grid-cell-inner ']");
		System.out.println(tabledata);
		waitForElementPresent(driver, 90, tabledata, Verify );
	
		try {
			driver.findElement(tabledata).isDisplayed();
			Add_Log.info("Successfully able to launch " + viewName + " Summary report");
			Reporter.log("Successfully able to launch " + viewName + " Summary report");

		} catch (Exception e) {
			Add_Log.info("Not able to launch " + viewName + " summary report");
			Reporter.log("Not able to launch " + viewName + " summary report");
			TestResultStatus.mpaskeysnew.put(methodName,"Not able to launch " + viewName + " summary report");
			TestResultStatus.Testfail = true;
			Snackbar(driver);
			Assert.fail();
		}
		Thread.sleep(2000);
		String[] actualViewsp = actualViews.split(",");
		WebElement extractxpathid = driver.findElement(By.xpath(
				"//*[text()='" + actualViewsp[0] + "']//following::div[2]//*[text()='" + actualViewsp[1] + "']"));
		System.out.println(extractxpathid);
		System.out.println(extractxpathid.getAttribute("id"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		By filter = By.xpath("(//*[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "'])["
				+ actualViewsp[2] + "]//a");
		System.out.println(filter);
		WebElement filtermove = driver.findElement(filter);
		waitForElementPresent(driver, 90, filter, actualViewsp[2]);
		new Actions(driver).moveToElement(filtermove).perform();
		Thread.sleep(500);
		driver.findElement(filter).click();
		try {
		click(driver, filter, actualViews);
	}
		catch (Exception e) {
		}
		Thread.sleep(5000);
		waitForLoad(driver, 900);
		By exposurereport = By.xpath("//label[text()='" + Report + "']");
		Thread.sleep(5000);
		waitForElementPresent(driver, 120, exposurereport, Report);
		try {
			driver.findElement(exposurereport).isDisplayed();
			Add_Log.info("Successfully able to view " + Report + " report");
			Reporter.log("Successfully able to view " + Report + " report");
	 

		} catch (Exception e) {
			Add_Log.info("Not able to launch " + Report + " report");
			Reporter.log("Not able to launch " + Report + " report");
			TestResultStatus.mpaskeysnew.put(methodName,"Not able to launch " + Report + " report");
			TestResultStatus.Testfail = true;
			Snackbar(driver);
			Assert.fail();
		}		
		
	Thread.sleep(90000);
		List<String> views = new ArrayList<>();
		String[] actualViews2p = actualViews2.split(",");
		waitForElementPresent(driver, 90, summaryFields);
		int size = driver.findElements(By.xpath(SUMMARYFIELDS)).size();
		for (int i = 1; i <= size; i++) {
			views.add(getText(driver, By.xpath("(" + SUMMARYFIELDS + ")[" + i + "]"), "  Summary Feild Content #" + i));
		}
		for (int i = 0; i < actualViews2p.length; i++) {
			if (views.get(i).equals(actualViews2p[i].trim().toString())) {
				// if (views.equals(Verify1[i].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViews2p[i].trim().toString() + " Summary Feild Content under "
						+ viewName + Report);
				Reporter.log("Successfully displayed " + actualViews2p[i].trim().toString() + " Summary Feild Content under "
						+ viewName + Report);
			} else {
				Add_Log.info(
						actualViews2p[i].trim().toString() + " is not displayed under Summary Feild " + viewName + Report);
				Reporter.log(
						actualViews2p[i].trim().toString() + " is not displayed under Summary Feild " + viewName + Report);
				TestResultStatus.mpaskeysnew.put(methodName,actualViews2p[i].trim().toString() + " is not displayed under Summary Feild " + viewName + Report);
				Snackbar(driver);
				Assert.fail();
			}
		}

		List<String> views2 = new ArrayList<>();
		String[] Verify2p1 = Verify2.split(",");
		waitForElementPresent(driver, 90, exposuresummaryfield);
		// int size1 = driver.findElements(By.xpath(ExposureSUMMARYFIELD)).size();
		List<WebElement> options2 = driver.findElements(By.xpath(ExposureSUMMARYFIELD));
		for (WebElement ele2 : options2) {
			views2.add(ele2.getAttribute(("data-qtip")));
		}
		System.out.println(views2);
		Add_Log.info("Successfully fetched text" + views2 + " under Expsoure Summary Feild Content");
		Reporter.log("Successfully fetched text" + views2 + " under Expsoure Summary Feild Content");

		for (int i = 0; i < Verify2p1.length; i++) {
			if (views2.contains(Verify2p1[i].trim().toString())) {
				Add_Log.info("Successfully displayed " + Verify2p1[i].trim().toString()
						+ " Expsoure Summary Feild Content under " + viewName + Report);
				Reporter.log("Successfully displayed " + Verify2p1[i].trim().toString()
						+ " Expsoure Summary Feild Content under " + viewName + Report);
			} else {
				Add_Log.info(
						Verify2p1[i].trim().toString() + " Expsoure Summary Feild Content under " + viewName + Report);
				Reporter.log(
						Verify2p1[i].trim().toString() + " Expsoure Summary Feild Content under " + viewName + Report);
				TestResultStatus.mpaskeysnew.put(methodName,Verify2p1[i].trim().toString() + " Expsoure Summary Feild Content under " + viewName + Report);
				Assert.fail();
			}

		}

	}
}

