package pageobjects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class DemandRe extends SeleniumUtils implements IHomePage {
	public DecimalFormat df = new DecimalFormat("#.##");
	public String testName = null;
	public boolean Testskip = false;
	public boolean Testfail = false;
	SoftAssert s_assert = null;
	public double finish, start;
	public double end;
	boolean allFlag = false;

	public void clicktable(WebDriver driver,String methodName, String actualViews) throws InterruptedException {
		
		boolean flag = false;
		String[] actualViewsp = actualViews.split(",");
		WebElement extractxpathid = driver.findElement(By.xpath(
				"//*[text()='" + actualViewsp[0] + "']//following::div[2]//*[text()='" + actualViewsp[1] + "']"));
		// System.out.println(extractxpathid.getAttribute("id"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		By filter = By.xpath("(//*[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "'])["
				+ actualViewsp[2] + "]//a");
		click(driver, filter, actualViews);

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

	public boolean AggregationCheckcommon(WebDriver driver,String methodName, String actualViews, String ReportName)
			throws InterruptedException {

		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		List<String> views = new ArrayList<>();
		waitForLoad(driver, 200);
		if (ReportName.equalsIgnoreCase("Summary")) {
			By ele = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
			waitForElementPresent(driver, 120, ele, " expand Aggregation");
		} else {
			By ele = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
			waitForElementPresent(driver, 120, ele, " expand Aggregation");
			click(driver, ele, "by drilling down to " + ReportName + " expand aggregation");
			Thread.sleep(2000);
		}
		int size = driver.findElements(By.xpath("//span[text()='" + ReportName + "']/ancestor::table//following-sibling::table")).size();
		System.out.println(size);
		Thread.sleep(500);
		for (int i = 1; i < actualViewsp.length+1; i++) {
			views.add(getText(driver, By.xpath(
					"//span[text()='" + ReportName + "']/ancestor::table//following-sibling::table[" + i + "]//span"),
					" Filter criteria #" + i));
		}
		System.out.println(views);
	//	for (int i = 0; i < size; i++) {		
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
				TestResultStatus.mpaskeysnew.put(methodName,actualViewsp[i].trim().toString()
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

	public boolean dataOnDemandRevaluation(WebDriver driver,String methodName, String actualViews, String reportingPeriod,
			String viewName, String ReportName) throws InterruptedException {
		
		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		List<String> views = new ArrayList<>();
		waitForLoad(driver, 200);
		Thread.sleep(1000);
		waitForElementPresent(driver, 120, expand_btnotools);
		click(driver, expand_btnotools);
		Thread.sleep(1000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 90, demandrevheader);
	
		int size = driver.findElements(By.xpath(ONDEMANDREQUESTFIELDSONLY)).size();
		for (int i = 1; i <= size; i++) {
			views.add(getatt(driver, By.xpath("(" + ONDEMANDREQUESTFIELDSONLY + ")[" + i + "]"),
					" Filter criteria #" + i));
		}
		System.out.println(views + " New View");
		
		for (int i = 0; i < actualViewsp.length; i++) {
			// System.out.println(actualViews[i] + " Actual");
			// System.out.println(views + " Array");
			if (views.get(i).equals(actualViewsp[i].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp[i].trim().toString()
						+ " filter criteria is in On Demand Revaluation Request screen under " + ReportName
						+ " and select " + viewName);
				Reporter.log("Successfully displayed " + actualViewsp[i].trim().toString()
						+ " filter criteria is in On Demand Revaluation Request screen under " + ReportName
						+ " and select " + viewName);
			} else {
				Add_Log.info(actualViewsp[i].trim().toString()
						+ " filter criteria is not displayed in On Demand Revaluation Request Criteria screen under "
						+ ReportName + " and select " + viewName);
				Reporter.log(actualViewsp[i].trim().toString()
						+ " filter criteria is not displayed in On Demand Revaluation Request Criteria screen under "
						+ ReportName + " and select " + viewName);
				TestResultStatus.mpaskeysnew.put(methodName,actualViewsp[i].trim().toString()
						+ " filter criteria is not displayed in On Demand Revaluation Request Criteria screen under "
						+ ReportName + " and select " + viewName); 
				flag = true;
			}
		}
		if (flag) {
			TestResultStatus.Testfail = true;
			Assert.fail();
			allFlag = true;
		} else {
			Add_Log.info(
					"Successfully all filter criterias are displayed in  On Demand Revaluation Request Criteria screen under "
							+ ReportName + " and select " + viewName);
			Reporter.log(
					"Successfully all filter criterias are displayed in  On Demand Revaluation Request Criteria screen under "
							+ ReportName + " and select " + viewName);
		}
		return allFlag;
	}

	public boolean dataOnDemandBAUTransactions(WebDriver driver,String methodName, String actualViews, String reportingPeriod,
			String viewName, String ReportName) throws InterruptedException {
		
		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		List<String> views = new ArrayList<>();
		waitForLoad(driver, 200);
		Thread.sleep(1000);
		waitForElementPresent(driver, 120, expand_btnotools);
		click(driver, expand_btnotools);
		Thread.sleep(1000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 90, demandtransactionsheader);
		int size = driver.findElements(By.xpath(DemandTransactionsFIELDSONLY)).size();
		for (int i = 1; i <= size; i++) {
			views.add(getatt(driver, By.xpath("(" + DemandTransactionsFIELDSONLY + ")[" + i + "]"),
					" Filter criteria #" + i));
		}
		System.out.println(views + " New View");
		for (int i = 0; i < actualViewsp.length; i++) {
			// System.out.println(actualViews[i] + " Actual");
			// System.out.println(views + " Array");
			if (views.get(i).equals(actualViewsp[i].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp[i].trim().toString()
						+ " filter criteria is in On Demand BAU Transactions screen under " + ReportName
						+ " and select " + viewName);
				Reporter.log("Successfully displayed " + actualViewsp[i].trim().toString()
						+ " filter criteria is in On Demand BAU Transactions screen under " + ReportName
						+ " and select " + viewName);
			} else {
				Add_Log.info(actualViewsp[i].trim().toString()
						+ " filter criteria is not displayed in On Demand BAU Transactions Criteria screen under "
						+ ReportName + " and select " + viewName);
				Reporter.log(actualViewsp[i].trim().toString()
						+ " filter criteria is not displayed in On Demand BAU Transactions screen under " + ReportName
						+ " and select " + viewName);
				TestResultStatus.mpaskeysnew.put(methodName, actualViewsp[i].trim().toString()
						+ " filter criteria is not displayed in On Demand BAU Transactions screen under " + ReportName
						+ " and select " + viewName);
				flag = true;
			}
		}
		if (flag) {
			TestResultStatus.Testfail = true;
			Assert.fail();
			allFlag = true;
		} else {
			Add_Log.info(
					"Successfully all filter criterias are displayed in  On Demand BAU Transactions Criteria screen under "
							+ ReportName + " and select " + viewName);
			Reporter.log(
					"Successfully all filter criterias are displayed in  On Demand BAU Transactions Criteria screen under "
							+ ReportName + " and select " + viewName);
		}
		return allFlag;
	}

	public void ToolsCreateicon(WebDriver driver,String methodName, String actualViews, String reportingPeriod, String viewName,
			String ReportName) throws InterruptedException {
		
		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		List<String> views = new ArrayList<>();
		waitForLoad(driver, 200);
		Thread.sleep(1000);
		waitForElementPresent(driver, 120, expand_btnotools);
		click(driver, expand_btnotools);
		Thread.sleep(1000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 90, demandrevheader);
		By sort = By.xpath("//*[contains(@class,'small icon icon-plus')]");
		click(driver, sort,
				"by drilling down to " + ReportName + " and select " + viewName + " and click on Sort/Filters");
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, labelcreaterequest);
		int size = driver.findElements(By.xpath(LabelCreateRequest)).size();
		for (int i = 1; i <= size; i++) {
			views.add(getText(driver, By.xpath("(" + LabelCreateRequest + ")[" + i + "]"), " Label name #" + i));
		}
		System.out.println(views + " New View");
		for (int i = 0; i < actualViewsp.length; i++) {
			if (views.get(i).equals(actualViewsp[i].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp[i].trim().toString()
						+ " label for On Demand create Request screen under " + ReportName + " and select " + viewName);
				Reporter.log("Successfully displayed " + actualViewsp[i].trim().toString()
						+ " label for On Demand create Request screen under " + ReportName + " and select " + viewName);
			} else {
				Add_Log.info(actualViewsp[i].trim().toString() + " label for On Demand create Request screen under"
						+ ReportName + " and select " + viewName);
				Reporter.log(actualViewsp[i].trim().toString() + " label for On Demand create Request screen under "
						+ ReportName + " and select " + viewName);
				TestResultStatus.mpaskeysnew.put(methodName, actualViewsp[i].trim().toString() + " label for On Demand create Request screen under "
						+ ReportName + " and select " + viewName);
				Assert.fail();
			}
		}
	}
	/////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unlikely-arg-type")
	public boolean dataVisualizationCriteriachecksanity(WebDriver driver,String methodName, String actualViews, String actualViews2,
			String reportingPeriod, String viewName, String ReportName) throws InterruptedException {
		
		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");
		List<String> views = new ArrayList<>();
		List<String> xviews = new ArrayList<>();
		List<String> xviews1 = new ArrayList<>();
		waitForLoad(driver, 200);
		By ele1 = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
		waitForElementPresent(driver, 120, ele1, " expand Aggregation");
		click(driver, ele1, "by drilling down to " + ReportName + " expand aggregation");
		Thread.sleep(2000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		if (ReportName.equalsIgnoreCase("Single Counterparty Credit Limits")) {
		waitForElementPresent(driver, 900, sccloptionsheader);
		}
		else {
			waitForElementPresent(driver, 900, dataVisualizationHeader);
		}
			
		int size1 = driver.findElements(By.xpath(Blue_Button)).size();
		for (int i = 1; i <= size1; i++) {
			xviews.add(getatt3(driver, By.xpath("(" + Blue_Button + ")[" + i + "]"), " primary button #" + i));
		}
		System.out.println(xviews + "primary button list from web");

		for (int i1 = 0; i1 < actualViewsp2.length; i1++) {

			if (xviews.get(i1).equals(actualViewsp2[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + xviews.get(i1) + " primary button");
				Reporter.log("Successfully displayed " + xviews.get(i1) + " primary button");
			} else {
				Add_Log.info(actualViewsp2[i1].trim().toString() + " primary button is not displayed");
				Reporter.log(actualViewsp2[i1].trim().toString() + " primary button is not displayed");
				TestResultStatus.mpaskeysnew.put(methodName, actualViewsp2[i1].trim().toString() + " primary button is not displayed");
				flag = true;
			}
		}
		int size = driver.findElements(By.xpath(DATAVISUALIZATIONFIELDSNew)).size();
		for (int i = 1; i <= size; i++) {
			xviews1.add(getText(driver, By.xpath("(" + DATAVISUALIZATIONFIELDSNew + ")[" + i + "]"),
					" Filter criteria #" + i));
		}
		System.out.println(xviews1);

		for (int i2 = 0; i2 < actualViewsp.length; i2++) {
			if (xviews1.get(i2).equals(actualViewsp[i2].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp[i2].trim().toString()
						+ " filter criteria is displayed in Data Visualisation Criteria screen");
				Reporter.log("Successfully displayed " + actualViewsp[i2].trim().toString()
						+ " filter criteria is displayed in Data Visualisation Criteria screen");
			} else {
				Add_Log.info(actualViewsp[i2].trim().toString()
						+ " filter criteria is not displayed in Data Visualisation Criteria screen");
				Reporter.log(actualViewsp[i2].trim().toString()
						+ " filter criteria is not displayed in Data Visualisation Criteria screen");
				TestResultStatus.mpaskeysnew.put(methodName, actualViewsp[i2].trim().toString()
						+ " filter criteria is not displayed in Data Visualisation Criteria screen");
				flag = true;
			}
		}

		if (flag) {
			TestResultStatus.Testfail = true;
			Assert.fail();
			allFlag = true;
		} else {
			Add_Log.info("Successfully all filter criterias are displayed under " + ReportName + " - " + viewName
					+ " Data Visualisation Criteria screen");
			Reporter.log("Successfully all filter criterias are displayed under " + ReportName + " - " + viewName
					+ " Data Visualisation Criteria screen");
		}
		return allFlag;
	}

	public boolean dataVisualizationCheckFiltersanity(WebDriver driver,String methodName, String actualViews, String viewName,
			String ReportName, String Verify) throws InterruptedException {
		
		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		List<String> views = new ArrayList<>();
		waitForLoad(driver, 200);
		By ele1 = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
		waitForElementPresent(driver, 120, ele1, " expand Aggregation");
		click(driver, ele1, "by drilling down to " + ReportName + " expand aggregation");
		Thread.sleep(2000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForElementPresent(driver, 90, alltablefeild);
		int size = driver.findElements(By.xpath(AllTableFIELD)).size();
		for (int i = 1; i <= size; i++) {
			views.add(getatt3(driver, By.xpath("(" + AllTableFIELD + ")[" + i + "]"), " Filter criteria #" + i));
		}
		System.out.println(views + " New View");
		for (int i = 0; i < actualViewsp.length; i++) {
			if (views.get(i).equals(actualViewsp[i].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp[i].trim().toString() + " filter criteria is in "
						+ Verify + " screen under " + ReportName + " and select " + viewName);
				Reporter.log("Successfully displayed " + actualViewsp[i].trim().toString() + " filter criteria is in "
						+ Verify + " screen under " + ReportName + " and select " + viewName);
			} else {
				Add_Log.info(actualViewsp[i].trim().toString() + " filter criteria is not displayed in " + Verify
						+ " screen under " + ReportName + " and select " + viewName);
				Reporter.log(actualViewsp[i].trim().toString() + " filter criteria is not displayed in " + Verify
						+ " screen under " + ReportName + " and select " + viewName);
				TestResultStatus.mpaskeysnew.put(methodName,actualViewsp[i].trim().toString() + " filter criteria is not displayed in " + Verify
						+ " screen under " + ReportName + " and select " + viewName);
				flag = true;
			}
		}

		waitForElementPresent(driver, 900, tablefieldfilterheader);
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 600);
		By tabledata = By.xpath("//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		waitForElementPresent(driver, 90, tabledata, Verify);
		try {
			driver.findElement(tabledata).isDisplayed();
			Add_Log.info("Successfully display " + Verify + " table data");
			Reporter.log("Successfully display " + Verify + " table data");

		} catch (Exception e) {
			Add_Log.info("Not able to view " + Verify + " data  table with  all columns under  " + ReportName
					+ " and select " + viewName);
			Reporter.log("Not able to view " + Verify + " data  table with  all columns under  " + ReportName
					+ " and select " + viewName);
			TestResultStatus.mpaskeysnew.put(methodName, "Not able to view " + Verify + " data  table with  all columns under  " + ReportName
					+ " and select " + viewName);
			flag = true;
		}
		if (flag) {
			TestResultStatus.Testfail = true;
			Assert.fail();
			allFlag = true;
		} else {
			Add_Log.info("Successfully able to view " + Verify + " data  table with  all columns under  " + ReportName
					+ " and select " + viewName);
			Reporter.log("Successfully able to view " + Verify + " data  table with  all columns under  " + ReportName
					+ " and select " + viewName);
		}
		return allFlag;
	}

	public boolean riskmeasuretabledisplay(WebDriver driver,String methodName, String Verify) throws InterruptedException {
		String[] actualViewsp2 = Verify.split("\\,");
		
		boolean flag = false;
		By tabledata = By.xpath("//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		waitForElementPresent(driver, 90, tabledata, Verify);
		try {
			driver.findElement(tabledata).isDisplayed();
			Add_Log.info("Successfully display " + Verify + " table data");
			Reporter.log("Successfully display " + Verify + " table data");

		} catch (Exception e) {
			Add_Log.info("Not able to display " + Verify + " table data");
			Reporter.log("Not able to display " + Verify + " table data");
			TestResultStatus.mpaskeysnew.put(methodName, "Not able to display " + Verify + " table data");
			flag = true;
		}
		if (flag) {
			allFlag = true;
			Assert.fail();
		}

		return allFlag;

	}

	public void riskmeasurecheckfilterbase(WebDriver driver,String methodName, String actualViews, String Verify, String viewName,
			String ReportName) throws InterruptedException {
	
		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		List<String> views = new ArrayList<>();
		List<String> xviews = new ArrayList<>();
		List<String> xviews1 = new ArrayList<>();
		waitForLoad(driver, 200);
		Thread.sleep(1000);
		waitForElementPresent(driver, 120, expand_optreport);
		click(driver, expand_optreport);
		Thread.sleep(1000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForElementPresent(driver, 900, dataVisualizationHeader);
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 600);
		Thread.sleep(2000);
		waitForElementPresent(driver, 900, tablefieldfilterheader);
		allFlag = riskmeasuretabledisplay(driver,methodName, Verifysp[0].toString().trim());
		allFlag = riskmeasuretabledisplay(driver,methodName, Verifysp[1].toString().trim());
		allFlag = riskmeasuretabledisplay(driver,methodName, Verifysp[2].toString().trim());
		allFlag = riskmeasuretabledisplay(driver,methodName, Verifysp[3].toString().trim());
		allFlag = riskmeasuretabledisplay(driver,methodName, Verifysp[4].toString().trim());
		if (allFlag) {
			TestResultStatus.Testfail = true;
			Reporter.log("No data under one of the RiskMeasure table display");
			Add_Log.info("No data under one of the RiskMeasure table display");
			TestResultStatus.mpaskeysnew.put(methodName, "No data under one of the RiskMeasure table display");
			Assert.fail();
		}
	}

	public void riskmeasurecheckfiltersanity(WebDriver driver,String methodName, String actualViews, String Verify,
			String reportingPeriod, String viewName, String ReportName) throws InterruptedException {
		
		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		List<String> views = new ArrayList<>();
		List<String> xviews = new ArrayList<>();
		List<String> xviews1 = new ArrayList<>();
		riskmeasurecheckfilterbase(driver,methodName, actualViews, Verify, viewName, ReportName);
		waitForElementPresent(driver, 90, alltablefeild);
		int size = driver.findElements(By.xpath(AllTableFIELD)).size();
		for (int i = 1; i <= size; i++) {
			views.add(getatt3(driver, By.xpath("(" + AllTableFIELD + ")[" + i + "]"), " Filter criteria #" + i));
		}
		System.out.println(views + " New View");
		for (int i = 0; i < actualViewsp.length; i++) {
			if (views.get(i).equals(actualViewsp[i].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp[i].trim().toString()
						+ " filter criteria is under table screen under " + ReportName + " and select " + viewName);
				Reporter.log("Successfully displayed " + actualViewsp[i].trim().toString()
						+ " filter criteria is under table screen under " + ReportName + " and select " + viewName);
			} else {
				Add_Log.info(actualViewsp[i].trim().toString()
						+ " filter criteria is not displayed under table screen under " + ReportName + " and select "
						+ viewName);
				Reporter.log(actualViewsp[i].trim().toString()
						+ " filter criteria is not displayed under table  screen under " + ReportName + " and select "
						+ viewName);
				TestResultStatus.mpaskeysnew.put(methodName, actualViewsp[i].trim().toString()
						+ " filter criteria is not displayed under table  screen under " + ReportName + " and select "
						+ viewName);
				
				Assert.fail();
				flag = true;
			}
		}
	}

	public boolean filtercheckreg(WebDriver driver,String methodName, String viewName, String ReportName, String Verify)
			throws InterruptedException {
		
		boolean flag = false;
		waitForLoad(driver, 200);
		By ele1 = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
		waitForElementPresent(driver, 120, ele1, " expand Aggregation");
		click(driver, ele1, "by drilling down to " + ReportName + " expand aggregation");
		Thread.sleep(2000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForElementPresent(driver, 900, dataVisualizationHeader);
		waitForElementPresent(driver, 900, tablefieldfilterheader);
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 600);
		By tabledata = By.xpath("//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		waitForElementPresent(driver, 90, tabledata, Verify);
		try {
			driver.findElement(tabledata).isDisplayed();
			Add_Log.info("Successfully display " + Verify + " table data");
			Reporter.log("Successfully display " + Verify + " table data");

		} catch (Exception e) {
			Add_Log.info("Not able to view " + Verify + " data  table with  all columns under  " + ReportName
					+ " and select " + viewName);
			Reporter.log("Not able to view " + Verify + " data  table with  all columns under  " + ReportName
					+ " and select " + viewName);
			TestResultStatus.mpaskeysnew.put(methodName, "Not able to view " + Verify + " data  table with  all columns under  " + ReportName
					+ " and select " + viewName);
			flag = true;
			Assert.fail();
		}
		if (flag) {
			TestResultStatus.Testfail = true;
			allFlag = true;
		}
		return flag;
	}

	public void orsortfilterreg(WebDriver driver,String methodName, String viewName, String ReportName, String Verify, String Verify2,
			String Verify3, String ids) throws InterruptedException {
		
		boolean flag = false;
		List<String> views = new ArrayList<>();
		List<String> xviews = new ArrayList<>();
		List<String> xviews1 = new ArrayList<>();
		flag = filtercheckreg(driver,methodName, viewName, ReportName, Verify);
		By sort = By.xpath("//label[text()='" + Verify + "']//following::span[contains(@class,'filterswitch')]");
		Thread.sleep(2000);
		click(driver, sort,
				"by drilling down to " + ReportName + " and select " + viewName + " and click on Sort/Filters button");
		Thread.sleep(2000);
		String id = INPUTTEXTSORT + "[" + Verify3 + "]";
		WebElement inputtextfeild = driver.findElement(By.xpath(INPUTTEXTSORT + "[" + Verify3 + "]"));
		WebPageElements inputtextfeildid = new WebPageElements(viewName, "xpath", id);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		try {
			wait.until(ExpectedConditions.visibilityOf(inputtextfeild));
			setText(driver, inputtextfeildid, ids);
			Thread.sleep(2000);
			waitForLoad(driver, 900);
			Thread.sleep(1000);
		} catch (Exception e) {
			Thread.sleep(2000);
			click(driver, sort,
					"by drilling down to " + ReportName + " and select " + viewName + " and click on Sort/Filters");
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(inputtextfeild));
			setText(driver, inputtextfeildid, ids);
			Thread.sleep(2000);
			waitForLoad(driver, 900);
		}
		waitForLoad(driver, 9000);
		String textLabel = driver.findElement(By.xpath(SORTGETTEXT + "[" + Verify2 + "]")).getText();
		System.out.println(textLabel);

		if (textLabel.contains(ids)) {
			Reporter.log("Successfully Sort Value");
			Add_Log.info("Successfully Sort Value");
		} else {
			Reporter.log("Unable to Sort value");
			Add_Log.info("Unable to Sort value");
			TestResultStatus.mpaskeysnew.put(methodName, "Unable to Sort value");
			Assert.fail();
			flag = true;
		}
		if (flag) {
			TestResultStatus.Testfail = true;
			Reporter.log("No data Sort/Filter under " + ReportName + " and select " + viewName);
			Add_Log.info("No data Sort/Filter under " + ReportName + " and select " + viewName);
			TestResultStatus.mpaskeysnew.put(methodName, "No data Sort/Filter under " + ReportName + " and select " + viewName);
			Assert.fail();
		}
	}

	public boolean downloadfilecommonreg(WebDriver driver,String methodName, String viewName, String ReportName, String Verify,
			String Verify2, String ids, String Fileformat, String Environment, String FileNameac)
					throws InterruptedException, IOException {
		
		boolean flagdownload = false;
		String csvFileName = viewName;
		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
		File dir = new File(downloadPath);

		for (File file : dir.listFiles()) {
			if (file.getName().startsWith(FileNameac)) {
			//	file.delete();
			//	Reporter.log("Successfully Deleted Existing File before clicking on download");
			//	Add_Log.info("Successfully Deleted Existing File before clicking on download");
				Thread.sleep(1000);
			}
		}
		String downloadIcon = "//label[text()='" + Verify + "']//following::span[contains(@class,'icon-" + Fileformat
				+ "')]";
		By downloadIconst = By.xpath(downloadIcon);
		waitForElementPresent(driver, 90, downloadIconst, Fileformat + " icon " + viewName);
		clicknosleep(driver, downloadIconst, Fileformat + " icon " + viewName);
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
			
				if (i >= 20000) // added for validation
				{
					Add_Log.info("Unable to download the file time over 120 sec for " +  ReportName) ;
					Reporter.log("Unable to download the file time over 120 sec for " +  ReportName) ;
					TestResultStatus.mpaskeysnew.put(methodName,"Unable to download the file time over 120 sec for " +  ReportName) ;
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

			String fileName = latestFileName2(FileNameac);
			System.out.println(fileName + " & " + viewName);
			System.out.println(FileNameac + "************");
			if (fileName.contains(FileNameac)) {
				Add_Log.info("Successfully downloaded " + Fileformat + " report for " + viewName + " " + ReportName
						+ " Report Time took to download " + totalTime + " sec");
				Reporter.log("Successfully downloaded " + Fileformat + " report for " + viewName + " " + ReportName
						+ " Report Time took to download " + totalTime + " sec");
			} else {
				Add_Log.info(Fileformat + "if satatment ******report not downloaded for " + viewName + " " + ReportName
						+ " report");
				Reporter.log(Fileformat + "if satatment  ******report not downloaded for " + viewName + " " + ReportName
						+ " report");
				TestResultStatus.mpaskeysnew.put(methodName, Fileformat + "if satatment  ******report not downloaded for " + viewName + " " + ReportName
						+ " report");
			}
		} catch (Exception e) {
			clicknosleep(driver, downloadIconst, Fileformat + " icon " + viewName);
			start = System.currentTimeMillis();
			
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
						Add_Log.info("Unable to download the file time over 120 sec for " +  ReportName) ;
						Reporter.log("Unable to download the file time over 120 sec for " +  ReportName) ;
						TestResultStatus.mpaskeysnew.put(methodName,"Unable to download the file time over 120 sec for " +  ReportName) ;
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

				String fileName = latestFileName2(FileNameac);
				System.out.println(fileName + " & " + viewName);
				System.out.println(FileNameac + "************");
				if (fileName.contains(FileNameac)) {
					Add_Log.info("Successfully downloaded " + Fileformat + " report for " + viewName + " " + ReportName
							+ " Report Time took to download " + totalTime + " sec");
					Reporter.log("Successfully downloaded " + Fileformat + " report for " + viewName + " " + ReportName
							+ " Report Time took to download " + totalTime + " sec");
				} else {
					Add_Log.info(Fileformat + "Re-try catch ******report not downloaded for " + viewName + " " + ReportName
							+ " report");
					Reporter.log(Fileformat + "Re-try catch  ******report not downloaded for " + viewName + " " + ReportName
							+ " report");
					TestResultStatus.mpaskeysnew.put(methodName, Fileformat + "Re-try catch  ******report not downloaded for " + viewName + " " + ReportName
							+ " report");
					flagdownload = true;
				}
			}
				catch (IOException e1) {
					e1.printStackTrace();
				}
		
			
		}

		return flagdownload;

	}

	public void ordownloadfilereg(WebDriver driver,String methodName, String viewName, String ReportName, String Verify, String Verify2,
			String ids, String Fileformat, String Environment, String FileNameac, String actualViews,
			String actualViews2) throws InterruptedException, IOException {
		
		boolean flagdownload = false;
		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualView2sp = actualViews2.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		filtercheckreg(driver,methodName, viewName, ReportName, Verify);
		Thread.sleep(1000);
		if (Verify.equalsIgnoreCase("Trend Report By Relationship")
				|| Verify.equalsIgnoreCase("Trend Report By Obligor")) {
			// Trend Report By Relationship
			treeexpand(driver, methodName, actualViewsp[0], actualView2sp[0], Verify2sp[0]);
			click(driver, filter_btn);
			Thread.sleep(1000);
		}
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		By tabledata = By.xpath("//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		waitForElementPresent(driver, 90, tabledata, Verify);
		flagdownload = downloadfilecommonreg(driver, methodName,viewName, ReportName, Verify, Verify2, ids, Fileformat,
				Environment, FileNameac);
		if (flagdownload) {
			Add_Log.info(
					Fileformat + " flag ******report not downloaded for " + viewName + " " + ReportName + " report");
			Reporter.log(
					Fileformat + " flag ******report not downloaded for " + viewName + " " + ReportName + " report");
			TestResultStatus.mpaskeysnew.put(methodName, Fileformat + " flag ******report not downloaded for " + viewName + " " + ReportName + " report");
			Assert.fail();
		}

	}

	public boolean riskmeasuresortcheck(WebDriver driver,String methodName, String Verify, String ReportName, String viewName,
			String Verify2, String Verify3, String ids) throws InterruptedException {
		
		boolean flag = false;
		By sort = By.xpath("(//label[text()='" + Verify + "']//following::span[contains(@class,'filterswitch')])[1]");
		Thread.sleep(2000);
		click(driver, sort, "by drilling down to " + Verify + " " + ReportName + " and select " + viewName
				+ " and click on Sort/Filters button");
		Thread.sleep(2000);
		String id = "(//label[text()='" + Verify + "']//following::input[contains(@name,'textfield')])[" + Verify3
				+ "]";
		String textlabeloutput = "(//label[text()='" + Verify + "']//following::div//*[@class='x-grid-cell-inner '])["
				+ Verify2 + "]";
		WebElement inputtextfeild = driver.findElement(By.xpath(id));
		WebPageElements inputtextfeildid = new WebPageElements(viewName, "xpath", id);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		try {
			wait.until(ExpectedConditions.visibilityOf(inputtextfeild));
			setText(driver, inputtextfeildid, ids);
			Thread.sleep(2000);
			waitForLoad(driver, 900);
			Thread.sleep(1000);
		} catch (Exception e) {
			Thread.sleep(2000);
			click(driver, sort, "by drilling down to " + Verify + " " + ReportName + " and select " + viewName
					+ " and click on Sort/Filters");
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(inputtextfeild));
			setText(driver, inputtextfeildid, ids);
			Thread.sleep(2000);
			waitForLoad(driver, 900);
		}
		waitForLoad(driver, 9000);
		waitForLoad(driver, 9000);
		waitForLoad(driver, 9000);
		String textLabel = driver.findElement(By.xpath(textlabeloutput)).getText();
		System.out.println(textLabel);

		if (textLabel.contains(ids)) {
			Reporter.log("Successfully Sort Value for " + Verify);
			Add_Log.info("Successfully Sort Value for " + Verify);
		} else {
			Reporter.log("Unable to Sort value " + Verify);
			Add_Log.info("Unable to Sort value " + Verify);
			TestResultStatus.mpaskeysnew.put(methodName, "Unable to Sort value " + Verify);
			flag = true;

		}
		if (flag) {
			allFlag = true;
		}

		return allFlag;

	}

	public void riskmeasurecheckfilterreg(WebDriver driver,String methodName, String actualViews, String Verify, String Verify2,
			String Verify3, String reportingPeriod, String viewName, String ReportName, String ids)
					throws InterruptedException {
		
		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] idsp = ids.split("\\,");
		List<String> views = new ArrayList<>();
		List<String> xviews = new ArrayList<>();
		List<String> xviews1 = new ArrayList<>();
		riskmeasurecheckfilterbase(driver,methodName, actualViews, Verify, viewName, ReportName);
		Thread.sleep(2000);
		allFlag = riskmeasuresortcheck(driver,methodName, Verifysp[0].toString().trim(), ReportName, viewName,
				Verify2sp[0].toString().trim(), Verify3sp[0].toString().trim(), idsp[0].toString().trim());
		allFlag = riskmeasuresortcheck(driver,methodName, Verifysp[1].toString().trim(), ReportName, viewName,
				Verify2sp[1].toString().trim(), Verify3sp[1].toString().trim(), idsp[1].toString().trim());
		allFlag = riskmeasuresortcheck(driver,methodName, Verifysp[2].toString().trim(), ReportName, viewName,
				Verify2sp[2].toString().trim(), Verify3sp[2].toString().trim(), idsp[2].toString().trim());
		allFlag = riskmeasuresortcheck(driver,methodName, Verifysp[3].toString().trim(), ReportName, viewName,
				Verify2sp[3].toString().trim(), Verify3sp[3].toString().trim(), idsp[3].toString().trim());
		allFlag = riskmeasuresortcheck(driver,methodName, Verifysp[4].toString().trim(), ReportName, viewName,
				Verify2sp[4].toString().trim(), Verify3sp[4].toString().trim(), idsp[4].toString().trim());
		if (allFlag) {
			TestResultStatus.Testfail = true;
			Reporter.log("Unable to Sort and Filter " + ReportName + " " + viewName);
			Add_Log.info("Unable to Sort and Filter " + ReportName + " " + viewName);
			TestResultStatus.mpaskeysnew.put(methodName, "Unable to Sort and Filter " + ReportName + " " + viewName);
			Assert.fail();
		}
	}

	public void riskmeasuredownloadfilereg(WebDriver driver,String methodName, String actualViews, String Verify, String Verify2,
			String Verify3, String reportingPeriod, String viewName, String ReportName, String ids, String Fileformat,
			String Environment, String FileNameac) throws InterruptedException, IOException {
		
		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] idsp = ids.split("\\,");
		List<String> views = new ArrayList<>();
		List<String> xviews = new ArrayList<>();
		List<String> xviews1 = new ArrayList<>();
		riskmeasurecheckfilterbase(driver,methodName,actualViews, Verify, viewName, ReportName);
		Thread.sleep(2000);
		flag = downloadfilecommonreg(driver,methodName,viewName, ReportName, Verifysp[0].toString().trim(),
				Verify2sp[0].toString().trim(), idsp[0].toString().trim(), Fileformat, Environment, FileNameac);
		flag = downloadfilecommonreg(driver,methodName,viewName, ReportName, Verifysp[1].toString().trim(),
				Verify2sp[1].toString().trim(), idsp[1].toString().trim(), Fileformat, Environment, FileNameac);
		flag = downloadfilecommonreg(driver,methodName,viewName, ReportName, Verifysp[2].toString().trim(),
				Verify2sp[2].toString().trim(), idsp[2].toString().trim(), Fileformat, Environment, FileNameac);
		flag = downloadfilecommonreg(driver,methodName,viewName, ReportName, Verifysp[3].toString().trim(),
				Verify2sp[3].toString().trim(), idsp[3].toString().trim(), Fileformat, Environment, FileNameac);
		flag = downloadfilecommonreg(driver,methodName,viewName, ReportName, Verifysp[4].toString().trim(),
				Verify2sp[4].toString().trim(), idsp[4].toString().trim(), Fileformat, Environment, FileNameac);
		if (flag) {
			TestResultStatus.Testfail = true;
			Reporter.log(Fileformat + "** report not downloaded for " + viewName + " " + ReportName);
			Add_Log.info(Fileformat + "** report not downloaded for " + viewName + " " + ReportName);
			TestResultStatus.mpaskeysnew.put(methodName, Fileformat + "** report not downloaded for " + viewName + " " + ReportName);
			Assert.fail();
		}
	}
	public void dropdowncontain(WebDriver driver,String methodName, String actualViews, String actualViews2) throws InterruptedException {
		
		waitForLoad(driver, 130);
		System.out.println(actualViews + " " + actualViews2);
		String aggreationfilter = "(//span[text()='" + actualViews
				+ "']//following::div[contains(@id,'trigger-picker')])[1]";
		WebElement aggreationfilterweb = driver.findElement(By.xpath(aggreationfilter));
		By aggreationfilterid = (By.xpath(aggreationfilter));
		WebDriverWait wait = new WebDriverWait(driver, 120);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
		click(driver, aggreationfilterid, actualViews);
		//By filtervalue = By.xpath("//li[text()='" + actualViews2 + "']");
		By filtervalue = By.xpath("(//li[contains(text(),'" + actualViews2 + "')])[1]");
		click(driver, filtervalue, actualViews2 + " value in " + actualViews);
		Thread.sleep(2000);
		click(driver, aggreationfilterid, actualViews);
		Thread.sleep(2000);
		WebElement filterinputtext = driver
				.findElement(By.xpath("(//span[text()='" + actualViews + "']//following::input)[1]"));
		String filterinputtextresult = filterinputtext.getAttribute("value");
		if (filterinputtextresult.contains(actualViews2)) {
			Reporter.log("Successfully filter " + actualViews + " and select value " + actualViews2);
			Add_Log.info("Successfully filter " + actualViews + " and select value " + actualViews2);
		} else {
			Reporter.log("*** No filter " + actualViews + " and select value " + actualViews2);
			Add_Log.info("*** No filter " + actualViews + " and select value " + actualViews2);
			TestResultStatus.mpaskeysnew.put(methodName, "*** No filter " + actualViews + " and select value " + actualViews2);
			Assert.fail();
		}
		waitForLoad(driver, 120);
		

	}

	public void dropdown(WebDriver driver,String methodName, String actualViews, String actualViews2) throws InterruptedException {
				
		waitForLoad(driver, 130);
		System.out.println(actualViews + " " + actualViews2);
		String aggreationfilter = "(//span[text()='" + actualViews
				+ "']//following::div[contains(@id,'trigger-picker')])[1]";
		WebElement aggreationfilterweb = driver.findElement(By.xpath(aggreationfilter));
		By aggreationfilterid = (By.xpath(aggreationfilter));
		WebDriverWait wait = new WebDriverWait(driver, 120);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
		clickJS(driver, aggreationfilterid, actualViews);
		String inputtextsearch1 = "(//span[text()='" + actualViews + "']//following::input)[1]";
		WebPageElements inputtextfeildid = new WebPageElements(actualViews, "xpath", inputtextsearch1);
		clearText(driver, inputtextfeildid);
		Thread.sleep(2000);
		setText(driver, inputtextfeildid, actualViews2);
		Thread.sleep(1000);
		waitForLoad(driver, 120);
		By filtervalue = By.xpath("//li[text()='" + actualViews2 + "']");
		clickJS(driver, filtervalue, actualViews2 + " value in " + actualViews);
		Thread.sleep(2000);
		clickJS(driver, aggreationfilterid, actualViews);
		Thread.sleep(2000);
		WebElement filterinputtext = driver
				.findElement(By.xpath("(//span[text()='" + actualViews + "']//following::input)[1]"));
		System.out.println(filterinputtext);
		String filterinputtextresult = filterinputtext.getAttribute("value");
		if (filterinputtextresult.equals(actualViews2)) {
			Reporter.log("Successfully filter " + actualViews + " and select value " + actualViews2);
			Add_Log.info("Successfully filter " + actualViews + " and select value " + actualViews2);
		} else {
			Reporter.log("*** No filter " + actualViews + " and select value " + actualViews2);
			Add_Log.info("*** No filter " + actualViews + " and select value " + actualViews2);
			TestResultStatus.mpaskeysnew.put(methodName, "*** No filter " + actualViews + " and select value " + actualViews2);
			Assert.fail();
		}
		
		waitForLoad(driver, 120);
		

	}

	// WorkStream
	public void dropdownold(WebDriver driver,String methodName, String actualViews, String actualViews2) throws InterruptedException {

		waitForLoad(driver, 130);
		System.out.println(actualViews + " " + actualViews2);
		String aggreationfilter = "(//span[text()='" + actualViews
				+ "']//following::div[contains(@id,'trigger-picker')])[1]";
		WebElement aggreationfilterweb = driver.findElement(By.xpath(aggreationfilter));
		By aggreationfilterid = (By.xpath(aggreationfilter));
		WebDriverWait wait = new WebDriverWait(driver, 120);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
		click(driver, aggreationfilterid, actualViews);
		Thread.sleep(1000);
		waitForLoad(driver, 120);
		By filtervalue = By.xpath("//li[text()='" + actualViews2 + "']");
		waitForElementPresent(driver, 120, filtervalue, actualViews2);
		click(driver, filtervalue, actualViews2 + " value in " + actualViews);
		Thread.sleep(2000);
		click(driver, aggreationfilterid, actualViews);
		Thread.sleep(2000);
		WebElement filterinputtext = driver
				.findElement(By.xpath("(//span[text()='" + actualViews + "']//following::input)[1]"));
		String filterinputtextresult = filterinputtext.getAttribute("value");
		if (filterinputtextresult.equals(actualViews2)) {
			Reporter.log("Successfully filter " + actualViews + " and select value " + actualViews2);
			Add_Log.info("Successfully filter " + actualViews + " and select value " + actualViews2);
		} else {
			Reporter.log("*** No filter " + actualViews + " and select value " + actualViews2);
			Add_Log.info("*** No filter " + actualViews + " and select value " + actualViews2);
			TestResultStatus.mpaskeysnew.put(methodName, "*** No filter " + actualViews + " and select value " + actualViews2);
			Assert.fail();
		}
		waitForLoad(driver, 120);
	
	}

	public void treeexpand(WebDriver driver,String methodName, String actualViews, String actualViews2, String Verify2)
			throws InterruptedException {
		
		String[] actualViewsphalf = actualViews2.split("\\|");
		System.out.println(actualViews + " " + actualViews2);
		String aggreationfilter = "(//span[text()='" + actualViews
				+ "']//following::div[contains(@id,'trigger-trigger')])[1]";
		WebElement aggreationfilterweb = driver.findElement(By.xpath(aggreationfilter));
		By aggreationfilterid = (By.xpath(aggreationfilter));
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
		click(driver, aggreationfilterid, actualViews);
		Thread.sleep(1000);
		waitForLoad(driver, 120);
		By titleheader = By.xpath("//*[text()='" + Verify2 + "']");
		waitForElementPresent(driver, 120, titleheader, Verify2 + " dialog box header");
		for (int i = 0; i < actualViewsphalf.length; i++) {
			By expand = By.xpath("//span[text()='" + actualViewsphalf[i] + "']/preceding-sibling::img[2]");
			waitForElementPresent3(driver, 90, expand, actualViewsphalf[i]);
			click(driver, expand, actualViewsphalf[i]);
			Thread.sleep(2000);
			waitForLoad(driver, 120);
		}
		String lastOne = actualViewsphalf[actualViewsphalf.length - 1];
		By expanddownselect = By.xpath("//span[text()='" + lastOne + "']/preceding-sibling::input");
		waitForElementPresent3(driver, 200, expanddownselect, lastOne);
		click(driver, expanddownselect, lastOne);
		Thread.sleep(2000);
		waitForLoad(driver, 200);
		if (IsCheckBoxSelected(driver, By.xpath("//span[text()='" + lastOne + "']/preceding-sibling::input"))) {
			Add_Log.info("Successfully able to select " + lastOne + " option from dashboard " + "summary screen");
			Reporter.log("Successfully able to select " + lastOne + " option from dashboard " + "summary screen");
		} else {
			Add_Log.info("*** Not able to select " + lastOne + " option from dashboard " + " summary screen");
			Reporter.log("*** Not able to select " + lastOne + " option from dashboard " + " summary screen");
			TestResultStatus.mpaskeysnew.put(methodName, "*** Not able to select " + lastOne + " option from dashboard " + " summary screen");
			Assert.fail();
		}
		click(driver, apply_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 60);
		WebElement filterinputtext = driver
				.findElement(By.xpath("(//span[text()='" + actualViews + "']//following::input)[1]"));
		String filterinputtextresult = filterinputtext.getAttribute("value");
		if (filterinputtextresult.contains(lastOne)) {
			Reporter.log("Successfully filter " + actualViews + " and select value " + actualViews2);
			Add_Log.info("Successfully filter " + actualViews + " and select value " + actualViews2);
		} else {
			Reporter.log("*** No filter " + actualViews + " and select value " + actualViews2);
			Add_Log.info("*** No filter " + actualViews + " and select value " + actualViews2);
			TestResultStatus.mpaskeysnew.put(methodName, "*** No filter " + actualViews + " and select value " + actualViews2);
			Assert.fail();
		}
		waitForLoad(driver, 120);

	}

	public void dragdrop1(WebDriver driver,String methodName, String actualViews, String actualViews2, String Verify2)
			throws InterruptedException {

		System.out.println(actualViews + " " + actualViews2);
		try {
			String aggreationfilter = "(//span[text()='" + actualViews
					+ "']//following::div[contains(@id,'trigger-trigger')])[1]";
			WebElement aggreationfilterweb = driver.findElement(By.xpath(aggreationfilter));
			By aggreationfilterid = (By.xpath(aggreationfilter));
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
			click(driver, aggreationfilterid, actualViews);
			Thread.sleep(1000);
			waitForLoad(driver, 120);
			By titleheader = By.xpath("//*[text()='" + Verify2 + "']");
			waitForElementPresent3(driver, 30, titleheader, Verify2 + " dialog box header");
			By inputtextsearch = By.xpath("//input[@name='searchValue']");
			String inputtextsearch1 = "//input[@name='searchValue']";
			WebPageElements inputtextfeildid = new WebPageElements(actualViews, "xpath", inputtextsearch1);
			waitForVisiblility1(driver, 120, inputtextsearch, "Search Selection box");
			setText(driver, inputtextfeildid, actualViews2);
			Thread.sleep(2000);
			click(driver, search_btninside);
			waitForLoad(driver, 60);
			try {
				waitForVisiblility(driver, 10, By.xpath("//input[@name='searchValue']//following::li"),
						"Selection option");
				By from = By.xpath("//ul/li[contains(text(),'" + actualViews2 + "')]");
				waitForElementPresent(driver, 20, from, actualViews2);
				try{
					Thread.sleep(500);
					driver.findElement(from).click();
					Add_Log.info("Successfully clicked on  " + actualViews2 + " option on tree.");
					Reporter.log("Successfully clicked on " + actualViews2 + " option on tree.");
				}
				catch (Exception e) {
					Thread.sleep(500);
					driver.findElement(from).click();
					Add_Log.info("Successfully clicked on  " + actualViews2 + " option on tree.");
					Reporter.log("Successfully clicked on " + actualViews2 + " option on tree.");
				}
				Thread.sleep(2000);
				By addto = By.xpath("//span[contains(@class,'itemselector-add')]");
				click(driver, addto, "Add to Selected");
				Thread.sleep(2000);
				click(driver, apply_btn);
				try {
					waitForInVisiblility(driver, 10,
							By.xpath("//div[text()='" + Verify2
									+ "']//ancestor::div[contains(@id,'coreperspectivewindow')]"),
							"coreperspective window");
				} catch (Exception e) {
					click(driver, apply_btn);
					Thread.sleep(2000);
					waitForInVisiblility(driver, 10,
							By.xpath("//div[text()='" + Verify2
									+ "']//ancestor::div[contains(@id,'coreperspectivewindow')]"),
							"coreperspective window");

				}
				waitForLoad(driver, 60);
				WebElement textlabel = driver
						.findElement(By.xpath("(//*[text()='" + actualViews + "']//following::input)[1]"));
				String value = textlabel.getAttribute("value");
				Thread.sleep(2000);

				if (value.contains(actualViews2)) {
					Reporter.log(
							"Successfully able to select " + actualViews + " option from dashboard " + actualViews2);
					Add_Log.info(
							"Successfully able to select " + actualViews + " option from dashboard " + actualViews2);
				} else {

					Reporter.log(
							"*** Not able to select " + actualViews + " option from dashboard " + actualViews2 + " screen");
					Add_Log.info(
							"***Not able to select " + actualViews + " option from dashboard " + actualViews2 + " screen");
					TestResultStatus.mpaskeysnew.put(methodName, "***Not able to select " + actualViews + " option from dashboard " + actualViews2 + " screen");
					Assert.fail();
				}
			} catch (Exception e) {
				By close = By.xpath("//div[contains(@class,'x-window-close-btn')]");
				click(driver, close, "close window");
				Thread.sleep(2000);
			}
		} catch (Exception e) {
		}
		waitForLoad(driver, 120);
	
	}
	
	public void dragdrop1bussinessunit(WebDriver driver,String methodName, String actualViews, String actualViews2, String Verify2)
			throws InterruptedException {

		System.out.println(actualViews + " " + actualViews2);
		try {
			String aggreationfilter = "(//span[text()='" + actualViews
					+ "']//following::div[contains(@id,'trigger-trigger')])[1]";
			WebElement aggreationfilterweb = driver.findElement(By.xpath(aggreationfilter));
			By aggreationfilterid = (By.xpath(aggreationfilter));
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
			click(driver, aggreationfilterid, actualViews);
			Thread.sleep(1000);
			waitForLoad(driver, 120);
			By titleheader = By.xpath("//*[text()='" + Verify2 + "']");
			waitForElementPresent3(driver, 30, titleheader, Verify2 + " dialog box header");
			By inputtextsearch = By.xpath("//input[@name='searchValue']");
			String inputtextsearch1 = "//input[@name='searchValue']";
			String inputtextsearchgettest =getatt3(driver, By.xpath("(//input[@name='searchValue']//following::li)[1]"), "value");
			System.out.println(inputtextsearchgettest);
			WebPageElements inputtextfeildid = new WebPageElements(actualViews, "xpath", inputtextsearch1);
			waitForVisiblility1(driver, 120, inputtextsearch, "Search Selection box");
			setText(driver, inputtextfeildid, inputtextsearchgettest);
			Thread.sleep(2000);
			click(driver, search_btninside);
			waitForLoad(driver, 60);
			try {
				waitForVisiblility(driver, 10, By.xpath("//input[@name='searchValue']//following::li"),
						"Selection option");
				By from = By.xpath("//ul/li[contains(text(),'" + inputtextsearchgettest + "')]");
				waitForElementPresent(driver, 20, from, inputtextsearchgettest);
				try{
					Thread.sleep(500);
					driver.findElement(from).click();
					Add_Log.info("Successfully clicked on  " + inputtextsearchgettest + " option on tree.");
					Reporter.log("Successfully clicked on " + inputtextsearchgettest + " option on tree.");
				}
				catch (Exception e) {
					Thread.sleep(500);
					driver.findElement(from).click();
					Add_Log.info("Successfully clicked on  " + inputtextsearchgettest + " option on tree.");
					Reporter.log("Successfully clicked on " + inputtextsearchgettest + " option on tree.");
				}
				Thread.sleep(2000);
				By addto = By.xpath("//span[contains(@class,'itemselector-add')]");
				click(driver, addto, "Add to Selected");
				Thread.sleep(2000);
				click(driver, apply_btn);
				try {
					waitForInVisiblility(driver, 10,
							By.xpath("//div[text()='" + Verify2
									+ "']//ancestor::div[contains(@id,'coreperspectivewindow')]"),
							"coreperspective window");
				} catch (Exception e) {
					click(driver, apply_btn);
					Thread.sleep(2000);
					waitForInVisiblility(driver, 10,
							By.xpath("//div[text()='" + Verify2
									+ "']//ancestor::div[contains(@id,'coreperspectivewindow')]"),
							"coreperspective window");

				}
				waitForLoad(driver, 60);
				WebElement textlabel = driver
						.findElement(By.xpath("(//*[text()='" + actualViews + "']//following::input)[1]"));
				String value = textlabel.getAttribute("value");
				Thread.sleep(2000);

				if (value.contains(inputtextsearchgettest)) {
					Reporter.log(
							"Successfully able to select " + actualViews + " option from dashboard " + inputtextsearchgettest);
					Add_Log.info(
							"Successfully able to select " + actualViews + " option from dashboard " + inputtextsearchgettest);
				} else {

					Reporter.log(
							"*** Not able to select " + actualViews + " option from dashboard " + inputtextsearchgettest + " screen");
					Add_Log.info(
							"***Not able to select " + actualViews + " option from dashboard " + inputtextsearchgettest + " screen");
					TestResultStatus.mpaskeysnew.put(methodName, "***Not able to select " + actualViews + " option from dashboard " + inputtextsearchgettest + " screen");
					Assert.fail();
				}
			} catch (Exception e) {
				By close = By.xpath("//div[contains(@class,'x-window-close-btn')]");
				click(driver, close, "close window");
				Thread.sleep(2000);
			}
		} catch (Exception e) {
		}
		waitForLoad(driver, 120);
	
	}
	public void  tapselect(WebDriver driver,String methodName, String actualViews, String actualViews2) throws InterruptedException {

		waitForLoad(driver, 120);
		System.out.println(actualViews + " " + actualViews2);
		String aggreationfilter = "//div[text()='" + actualViews + "']";
		WebElement aggreationfilterweb = driver.findElement(By.xpath(aggreationfilter));
		// By aggreationfilterid = (By.xpath(aggreationfilter));
		WebDriverWait wait = new WebDriverWait(driver, 120);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
		By tapoption = By.xpath(
				"//div[text()='" + actualViews + "']//following::span[text()='" + actualViews2 + "']//ancestor::a");
		waitForElementPresent3(driver, 30, tapoption, actualViews2 + " Selection options");
		clickJS(driver, tapoption, actualViews2);
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(
				"//*[text()='" + actualViews + "']//following::span[text()='" + actualViews2 + "']//ancestor::a"));
		String color = element.getCssValue("background-color");
		if (color.equals("rgba(204, 242, 252, 1)")) {
		} else {
			click(driver, tapoption, actualViews2);
			Thread.sleep(2000);

		}
		Thread.sleep(2000);
		if (color.equals("rgba(204, 242, 252, 1)")) {
			Reporter.log("Successfully able to select " + actualViews2 + " for " + actualViews);
			Add_Log.info("Successfully able to select " + actualViews2 + " for " + actualViews);
		} else {
			Reporter.log("*** Not able to select " + actualViews + " option from dashboard " + actualViews2 + " screen");
			Add_Log.info("*** Not able to select " + actualViews + " option from dashboard " + actualViews2 + " screen");
			TestResultStatus.mpaskeysnew.put(methodName, "*** Not able to select " + actualViews + " option from dashboard " + actualViews2 + " screen");
			Assert.fail();
		}
		waitForLoad(driver, 120);
		
	}

	public void inputtextbox(WebDriver driver,String methodName, String actualViews, String actualViews2) throws InterruptedException {
		
		waitForLoad(driver, 120);
		System.out.println(actualViews + " " + actualViews2);
		By inputtextsearch = By.xpath("(//span[text()='" + actualViews + "']//following::input)[1]");
		String inputtextsearch1 = "(//span[text()='" + actualViews + "']//following::input)[1]";
		WebPageElements inputtextfeildid = new WebPageElements(actualViews, "xpath", inputtextsearch1);
		waitForVisiblility1(driver, 10, inputtextsearch, "Search Selection box");
		setText(driver, inputtextfeildid, actualViews2);
		Thread.sleep(2000);
		waitForLoad(driver, 60);
		WebElement textlabel = driver.findElement(By.xpath("(//*[text()='" + actualViews + "']//following::input)[1]"));
		String value = textlabel.getAttribute("value");
		Thread.sleep(2000);

		if (value.equals(actualViews2)) {
			Reporter.log("Successfully able to select " + actualViews + " option from dashboard " + actualViews2);
			Add_Log.info("Successfully able to select " + actualViews + " option from dashboard " + actualViews2);
		} else {

			Reporter.log("*** Not able to select " + actualViews + " option from dashboard " + actualViews2 + " screen");
			Add_Log.info("*** Not able to select " + actualViews + " option from dashboard " + actualViews2 + " screen");
			TestResultStatus.mpaskeysnew.put(methodName, "*** Not able to select " + actualViews + " option from dashboard " + actualViews2 + " screen");
			Assert.fail();
		}
		waitForLoad(driver, 120);
		
		}


	public void eachaggMIDQNumerical(WebDriver driver,String methodName, String actualViews, String actualViews2, String Verify,
			String Verify2, String Verify3, String reportingPeriod, String viewName, String ReportName, String ids,
			String Fileformat) throws InterruptedException {
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualView2sp = actualViews2.split("\\,");

		String[] Verify2sp = Verify2.split("\\,");

		waitForLoad(driver, 200);
		
		By ele1 = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
		waitForElementPresent(driver, 120, ele1, " expand Aggregation");
		click(driver, ele1, "by drilling down to " + ReportName + " expand aggregation");
		Thread.sleep(2000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForElementPresent(driver, 900, dataVisualizationHeader);
		waitForLoad(driver, 900);
		Thread.sleep(2000);
		List valid = Arrays.asList(actualView2sp);
		

		// Denomination
		try {
			dropdown(driver,methodName,actualViewsp[0], actualView2sp[0]);
			//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		}
		catch (Exception e) {
		}
		
	
		// Managed Segment
		try {
			treeexpand(driver,methodName,actualViewsp[1], actualView2sp[1], Verify2sp[0]);
			//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		}catch (Exception e) {
		}
		
		// Current Period
		dropdown(driver,methodName,actualViewsp[2], actualView2sp[2]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Acronym
		dragdrop1(driver,methodName,actualViewsp[3], actualView2sp[3], Verify2sp[1]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Managed Geography
		treeexpand(driver,methodName,actualViewsp[4], actualView2sp[4], Verify2sp[2]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Is Name Specific
		 dropdown(driver,methodName,actualViewsp[5], actualView2sp[5]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Reporting Population
		 dropdown(driver,methodName,actualViewsp[6], actualView2sp[6]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Legal Vehicle
		 dragdrop1(driver,methodName,actualViewsp[7], actualView2sp[7], Verify2sp[3]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Reserve Methodology
		 dropdown(driver,methodName,actualViewsp[8], actualView2sp[8]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Relationship Managed Industry
		 treeexpand(driver,methodName,actualViewsp[9], actualView2sp[9], Verify2sp[4]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// CECL Business Organization
		dragdrop1(driver,methodName,actualViewsp[10], actualView2sp[10], Verify2sp[5]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Business Unit
		dragdrop1bussinessunit(driver,methodName,actualViewsp[11], actualView2sp[11], Verify2sp[6]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Is FRR Performing
		 dropdown(driver,methodName,actualViewsp[12], actualView2sp[12]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Workstream,
		  dropdownold(driver,methodName,actualViewsp[13], actualView2sp[13]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// TDR Indicator
		 tapselect(driver,methodName,actualViewsp[14], actualView2sp[14]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
			Reporter.log("Successfully able to select and filter DVC from " + ReportName + " under " + viewName);
			Add_Log.info("Successfully able to select and filter DVC from " + ReportName + " under " + viewName);
	}
	

	public void eachaggMIDQCateroical(WebDriver driver,String methodName, String actualViews, String actualViews2, String Verify,
			String Verify2, String Verify3, String reportingPeriod, String viewName, String ReportName, String ids,
			String Fileformat) throws InterruptedException {
		
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualView2sp = actualViews2.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		waitForLoad(driver, 200);
		By ele1 = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
		waitForElementPresent(driver, 120, ele1, " expand Aggregation");
		click(driver, ele1, "by drilling down to " + ReportName + " expand aggregation");
		Thread.sleep(2000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForElementPresent(driver, 900, dataVisualizationHeader);
		waitForLoad(driver, 900);
		Thread.sleep(2000);
		// Denomination
		 dropdown(driver,methodName,actualViewsp[0], actualView2sp[0]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Managed Segment
		 treeexpand(driver,methodName,actualViewsp[1], actualView2sp[1], Verify2sp[0]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Current Period
		 dropdown(driver,methodName,actualViewsp[2], actualView2sp[2]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Acronym
		 dragdrop1(driver,methodName,actualViewsp[3], actualView2sp[3], Verify2sp[1]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Managed Geography
		 treeexpand(driver,methodName,actualViewsp[4], actualView2sp[4], Verify2sp[2]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Is Name Specific
		 dropdown(driver,methodName,actualViewsp[5], actualView2sp[5]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Reporting Population
		 dropdown(driver,methodName,actualViewsp[6], actualView2sp[6]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Legal Vehicle
		 dragdrop1(driver,methodName,actualViewsp[7], actualView2sp[7], Verify2sp[3]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Reserve Methodology
		 dropdown(driver,methodName,actualViewsp[8], actualView2sp[8]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Relationship Managed Industry
		 treeexpand(driver,methodName,actualViewsp[9], actualView2sp[9], Verify2sp[4]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// CECL Business Organization
		 dragdrop1(driver,methodName,actualViewsp[10], actualView2sp[10], Verify2sp[5]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Business Unit
		dragdrop1bussinessunit(driver,methodName,actualViewsp[11], actualView2sp[11], Verify2sp[6]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Is FRR Performing
		 dropdown(driver,methodName,actualViewsp[12], actualView2sp[12]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Workstream,
		 dropdownold(driver,methodName,actualViewsp[13], actualView2sp[13]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// TDR Indicator
		 tapselect(driver,methodName,actualViewsp[14], actualView2sp[14]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Threshold (%)
		inputtextbox(driver,methodName,actualViewsp[15], actualView2sp[15]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		
			Reporter.log("Successfully able to select and filter DVC from " + ReportName + " under " + viewName);
			Add_Log.info("Successfully able to select and filter DVC from " + ReportName + " under " + viewName);
		
	}

	public void CECLMovementReport(WebDriver driver,String methodName, String actualViews, String actualViews2, String Verify,
			String Verify2, String Verify3, String reportingPeriod, String viewName, String ReportName, String ids,
			String Fileformat) throws InterruptedException {

		String[] actualViewsp = actualViews.split("\\,");
		String[] actualView2sp = actualViews2.split("\\,");
		// String[] Verify2sp = Verify2.split("\\,");
		waitForLoad(driver, 200);
		By ele1 = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
		waitForElementPresent(driver, 120, ele1, " expand Aggregation");
		click(driver, ele1, "by drilling down to " + ReportName + " expand aggregation");
		Thread.sleep(2000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForElementPresent(driver, 900, dataVisualizationHeader);
		waitForLoad(driver, 900);
		Thread.sleep(2000);

		// Run 1
		dropdowncontain(driver,methodName,actualViewsp[0], actualView2sp[0]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Run 2
		dropdowncontain(driver,methodName,actualViewsp[1], actualView2sp[1]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Run 3
		dropdowncontain(driver,methodName,actualViewsp[2], actualView2sp[2]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Run 4
		dropdowncontain(driver,methodName,actualViewsp[3], actualView2sp[3]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Run 1 Description
		inputtextbox(driver,methodName,actualViewsp[4], actualView2sp[4]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Item 1 Description
		inputtextbox(driver,methodName,actualViewsp[5], actualView2sp[5]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Item 2 Description
		inputtextbox(driver,methodName,actualViewsp[6], actualView2sp[6]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Run 4 Description
		inputtextbox(driver,methodName,actualViewsp[7], actualView2sp[7]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Other Description
		inputtextbox(driver,methodName,actualViewsp[8], actualView2sp[8]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Denomination
		 dropdown(driver,methodName,actualViewsp[9], actualView2sp[9]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Show Adjustments
		 tapselect(driver,methodName,actualViewsp[10], actualView2sp[10]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);	
			Reporter.log("Successfully able to select and filter DVC from " + ReportName + " under " + viewName);
			Add_Log.info("Successfully able to select and filter DVC from " + ReportName + " under " + viewName);
	}
	

	public void trbobligor(WebDriver driver,String methodName, String actualViews, String actualViews2, String Verify, String Verify2,
			String Verify3, String reportingPeriod, String viewName, String ReportName, String ids, String Fileformat)
					throws InterruptedException {
	
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualView2sp = actualViews2.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		waitForLoad(driver, 200);
		By ele1 = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
		waitForElementPresent(driver, 120, ele1, " expand Aggregation");
		click(driver, ele1, "by drilling down to " + ReportName + " expand aggregation");
		Thread.sleep(2000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForElementPresent(driver, 900, dataVisualizationHeader);
		waitForLoad(driver, 900);
		Thread.sleep(2000);
		// Denomination
		 dropdown(driver,methodName,actualViewsp[0], actualView2sp[0]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Managed Segment
		 treeexpand(driver,methodName,actualViewsp[1], actualView2sp[1], Verify2sp[0]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Current Period
		 dropdown(driver,methodName,actualViewsp[2], actualView2sp[2]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Acronym
		dragdrop1(driver,methodName,actualViewsp[3], actualView2sp[3], Verify2sp[1]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Managed Geography
		 treeexpand(driver,methodName,actualViewsp[4], actualView2sp[4], Verify2sp[2]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Is Name Specific
		 dropdown(driver,methodName,actualViewsp[5], actualView2sp[5]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Reporting Population
		 dropdown(driver,methodName,actualViewsp[6], actualView2sp[6]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Reserve Methodology
		 dropdown(driver,methodName,actualViewsp[7], actualView2sp[7]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Relationship Managed Industry
		 treeexpand(driver,methodName,actualViewsp[8], actualView2sp[8], Verify2sp[3]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Business Unit
		dragdrop1bussinessunit(driver,methodName,actualViewsp[9], actualView2sp[9], Verify2sp[4]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Is FRR Performing
		 dropdown(driver,methodName,actualViewsp[10], actualView2sp[10]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// TDR Indicator
		 tapselect(driver,methodName,actualViewsp[11], actualView2sp[11]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
	
			Reporter.log("Successfully able to select and filter DVC from " + ReportName + " under " + viewName);
			Add_Log.info("Successfully able to select and filter DVC from " + ReportName + " under " + viewName);
	}
		

	public void ratingmigdata(WebDriver driver,String methodName, String actualViews, String actualViews2, String Verify, String Verify2,
			String Verify3, String reportingPeriod, String viewName, String ReportName, String ids, String Fileformat)
					throws InterruptedException {
		
		
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualView2sp = actualViews2.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		waitForLoad(driver, 200);
		By ele1 = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
		waitForElementPresent(driver, 120, ele1, " expand Aggregation");
		click(driver, ele1, "by drilling down to " + ReportName + " expand aggregation");
		Thread.sleep(2000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForElementPresent(driver, 900, dataVisualizationHeader);
		waitForLoad(driver, 900);
		Thread.sleep(2000);
		// Denomination
		 dropdown(driver,methodName,actualViewsp[0], actualView2sp[0]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Managed Segment
		 treeexpand(driver,methodName,actualViewsp[1], actualView2sp[1], Verify2sp[0]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Current Period
		 dropdown(driver,methodName,actualViewsp[2], actualView2sp[2]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Acronym
		dragdrop1(driver,methodName,actualViewsp[3], actualView2sp[3], Verify2sp[1]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Managed Geography
		 treeexpand(driver,methodName,actualViewsp[4], actualView2sp[4], Verify2sp[2]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		
		// Prior Period
		 dropdown(driver,methodName,actualViewsp[5], actualView2sp[5]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Is Name Specific
		 dropdown(driver,methodName,actualViewsp[6], actualView2sp[6]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Reporting Population
		 dropdown(driver,methodName,actualViewsp[7], actualView2sp[7]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Reserve Methodology
		 dropdown(driver,methodName,actualViewsp[8], actualView2sp[8]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Relationship Managed Industry
		treeexpand(driver,methodName,actualViewsp[9], actualView2sp[9], Verify2sp[3]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Business Unit
		dragdrop1bussinessunit(driver,methodName,actualViewsp[10], actualView2sp[10], Verify2sp[4]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Is FRR Performing
		 dropdown(driver,methodName,actualViewsp[11], actualView2sp[11]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// TDR Indicator
		tapselect(driver,methodName,actualViewsp[12], actualView2sp[12]);
		//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// Measure
		if (viewName.equalsIgnoreCase("Rating Mig: TOSUC ECL Mvmt")
				|| viewName.equalsIgnoreCase("Rating Mig: Portfolio Attribution")) {
			 dropdown(driver,methodName,actualViewsp[13], actualView2sp[13]);
			//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
			Thread.sleep(2000);
			waitForLoad(driver, 900);
		}
		// Reporting Category
		if (viewName.equalsIgnoreCase("Risk Measure")) {
			 dropdown(driver,methodName,actualViewsp[13], actualView2sp[13]);
			//allFlags = allAsert(flagdrop, flagtreeexpand, flagdropold, flagdrag, flagtapselect, flaginputtext);
			Thread.sleep(2000);
			waitForLoad(driver, 900);
		}
		
			Reporter.log("Successfully able to select and filter DVC from " + ReportName + " under " + viewName);
			Add_Log.info("Successfully able to select and filter DVC from " + ReportName + " under " + viewName);
	}
	
	/*boolean //allFlags;
	public boolean allAsert(boolean flagdrop, boolean flagtreeexpand, boolean flagdropold, boolean flagdrag, boolean flagtapselect, boolean flaginputtext){
		if(flagdrop){
			allFlags = true;
		}
		if (flagtreeexpand)
		{
			allFlags = true;
		}
		if (flagdropold)
		{
			allFlags = true;
		}
		if (flagdrag)
		{
			allFlags = true;
		}
		if (flaginputtext)
		{
			allFlags = true;
		}
		if (flagtapselect)
		{
			allFlags = true;
		}
		return allFlags;
	}*/
	public boolean dataVisualizationCriteriachecksanitySCCL(WebDriver driver,String methodName, String actualViews, String actualViews2,
			String reportingPeriod, String viewName, String Verify,String Verify2, String ReportName) throws InterruptedException {
		
		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");
		String[] Verify2p = Verify2.split("\\,");
		List<String> views = new ArrayList<>();
		List<String> views2 = new ArrayList<>();
		List<String> xviews = new ArrayList<>();
		List<String> xviews1 = new ArrayList<>();
		waitForLoad(driver, 200);
		By ele1 = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
		waitForElementPresent(driver, 120, ele1, " expand Aggregation");
		click(driver, ele1, "by drilling down to " + ReportName + " expand aggregation");
		Thread.sleep(2000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);	
		waitForElementPresent(driver, 900, sccloptionsheader);	
		int size1 = driver.findElements(By.xpath(Blue_Button)).size();
		for (int i = 1; i <= size1; i++) {
			xviews.add(getatt3(driver, By.xpath("(" + Blue_Button + ")[" + i + "]"), " primary button #" + i));
		}
		System.out.println(xviews + " primary button list from web");

		for (int i1 = 0; i1 < actualViewsp2.length; i1++) {

			if (xviews.get(i1).equals(actualViewsp2[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + xviews.get(i1) + " primary button");
				Reporter.log("Successfully displayed " + xviews.get(i1) + " primary button");
			} else {
				Add_Log.info(actualViewsp2[i1].trim().toString() + " primary button is not displayed");
				Reporter.log(actualViewsp2[i1].trim().toString() + " primary button is not displayed");
				TestResultStatus.mpaskeysnew.put(methodName, actualViewsp2[i1].trim().toString() + " primary button is not displayed");
				flag = true;
			}
		}
		int size = driver.findElements(By.xpath(DATAVISUALIZATIONFIELDSNew)).size();
		for (int i = 1; i <= size; i++) {
			xviews1.add(getText(driver, By.xpath("(" + DATAVISUALIZATIONFIELDSNew + ")[" + i + "]"),
					" Filter criteria #" + i));
		}
		System.out.println(xviews1);

		for (int i2 = 0; i2 < actualViewsp.length; i2++) {
			if (xviews1.get(i2).equals(actualViewsp[i2].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp[i2].trim().toString()
						+ " filter criteria is displayed in SCCL Options screen");
				Reporter.log("Successfully displayed " + actualViewsp[i2].trim().toString()
						+ " filter criteria is displayed in SCCL Options screen");
			} else {
				Add_Log.info(actualViewsp[i2].trim().toString()
						+ " filter criteria is not displayed in SCCL Options screen");
				Reporter.log(actualViewsp[i2].trim().toString()
						+ " filter criteria is not displayed in SCCL Options screen");
				TestResultStatus.mpaskeysnew.put(methodName, actualViewsp[i2].trim().toString()
						+ " filter criteria is not displayed in SCCL Options  screen");
				flag = true;
			}
		}

	waitForElementPresent(driver, 90, alltablefeild);
	int size11 = driver.findElements(By.xpath(AllTableFIELD)).size();
	for (int i = 1; i <= size11; i++) {
		views2.add(getatt3(driver, By.xpath("(" + AllTableFIELD + ")[" + i + "]"), " Filter criteria #" + i));
	}
	System.out.println(views2 + " New View");
	for (int i = 0; i < Verify2p.length; i++) {
		if (views2.get(i).trim().toString() .equals(Verify2p[i].trim().toString())) {
			Add_Log.info("Successfully displayed " + Verify2p[i].trim().toString() + " filter criteria is in "
					+ Verify + " screen under " + ReportName + " and select " + viewName);
			Reporter.log("Successfully displayed " + Verify2p[i].trim().toString() + " filter criteria is in "
					+ Verify + " screen under " + ReportName + " and select " + viewName);
		} else {
			Add_Log.info("***** " + Verify2p[i].trim().toString() + " filter criteria is not displayed in " + Verify
					+ " screen under " + ReportName + " and select " + viewName);
			Reporter.log("***** " +Verify2p[i].trim().toString() + " filter criteria is not displayed in " + Verify
					+ " screen under " + ReportName + " and select " + viewName);
			TestResultStatus.mpaskeysnew.put(methodName,Verify2p[i].trim().toString() + " filter criteria is not displayed in " + Verify
					+ " screen under " + ReportName + " and select " + viewName);
			flag = true;
		}
	}

	waitForElementPresent(driver, 900, tablefieldfilterheader);
	By tabledata = By.xpath("//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
	waitForElementPresent(driver, 90, tabledata, Verify);
	try {
		driver.findElement(tabledata).isDisplayed();
		Add_Log.info("Successfully display " + Verify + " table data");
		Reporter.log("Successfully display " + Verify + " table data");

	} catch (Exception e) {
		Add_Log.info("Not able to view " + Verify + " data  table with  all columns under  " + ReportName
				+ " and select " + viewName);
		Reporter.log("Not able to view " + Verify + " data  table with  all columns under  " + ReportName
				+ " and select " + viewName);
		TestResultStatus.mpaskeysnew.put(methodName, "Not able to view " + Verify + " data  table with  all columns under  " + ReportName
				+ " and select " + viewName);
		flag = true;
	}
	if (flag) {
		TestResultStatus.Testfail = true;
		Assert.fail();
		allFlag = true;
	} else {
		Add_Log.info("Successfully able to view " + Verify + " data  table with  all columns under  " + ReportName
				+ " and select " + viewName);
		Reporter.log("Successfully able to view " + Verify + " data  table with  all columns under  " + ReportName
				+ " and select " + viewName);
	}
	return allFlag;
}
	
	public boolean downloadfilecommonregsccl(WebDriver driver,String methodName, String viewName, String ReportName, String Verify,
			String Verify2, String ids, String Fileformat, String Environment, String FileNameac)
					throws InterruptedException, IOException {
		
		boolean flagdownload = false;
		String csvFileName = viewName;
		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
	
		String downloadIcon = "//label[text()='" + Verify + "']//following::span[contains(@class,'icon-" + Fileformat
				+ "')]";
		By downloadIconst = By.xpath(downloadIcon);
		waitForElementPresent(driver, 90, downloadIconst, Fileformat + " icon " + viewName);
		clicknosleep(driver, downloadIconst, Fileformat + " icon " + viewName);
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

		//	System.out.println(i++);
				if (i == 800) // added for validation
				{

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

			String fileName = latestFileName2new(FileNameac);
			System.out.println(fileName + " & " + viewName);
			System.out.println(FileNameac + "************");
			if (fileName.contains(FileNameac)) {
				Add_Log.info("Successfully downloaded " + Fileformat + " report for " + viewName + " " + ReportName
						+ " Report Time took to download " + totalTime + " sec");
				Reporter.log("Successfully downloaded " + Fileformat + " report for " + viewName + " " + ReportName
						+ " Report Time took to download " + totalTime + " sec");
			} else {
				Add_Log.info(Fileformat + "report not downloaded for " + viewName + " " + ReportName
						+ " report");
				Reporter.log(Fileformat + "report not downloaded for " + viewName + " " + ReportName
						+ " report");
				TestResultStatus.mpaskeysnew.put(methodName, Fileformat + "if satatment  ******report not downloaded for " + viewName + " " + ReportName
						+ " report");
			}
		} catch (Exception e) {
			click(driver, downloadIconst, Fileformat + " icon " + viewName);
			start = System.currentTimeMillis();
			try {
				beforeCount = Files.list(Paths.get(downloadPath)).count();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				System.out.println(beforeCount);
				long afterCount = beforeCount;
				int i = 1;
				while (beforeCount >= afterCount) {
					Thread.sleep(1000);
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
				TimeUnit.SECONDS.sleep(45);

				String fileName = latestFileName2(FileNameac);
				System.out.println(fileName + " & " + viewName);
				System.out.println(FileNameac + "************");
				if (fileName.contains(FileNameac)) {
					Add_Log.info("Successfully downloaded " + Fileformat + " report for " + viewName + " " + ReportName
							+ " Report Time took to download " + totalTime + " sec");
					Reporter.log("Successfully downloaded " + Fileformat + " report for " + viewName + " " + ReportName
							+ " Report Time took to download " + totalTime + " sec");
				} else {
					Add_Log.info(Fileformat + "Re-try catch ******report not downloaded for " + viewName + " " + ReportName
							+ " report");
					Reporter.log(Fileformat + "Re-try catch  ******report not downloaded for " + viewName + " " + ReportName
							+ " report");
					TestResultStatus.mpaskeysnew.put(methodName, Fileformat + "Re-try catch  ******report not downloaded for " + viewName + " " + ReportName
							+ " report");
					flagdownload = true;
				}
			
		}

		return flagdownload;

	}
	public boolean filtercheckSCCL(WebDriver driver,String methodName, String viewName, String ReportName, String Verify, String Verify2 ,String Verify3, String ID,String ids, String actualViews,
			String Fileformat, String Environment, String FileNameac)
			throws InterruptedException, IOException {
	//	String[] actualViewsp = actualViews.split(",");
		boolean flagdownload = false;
		boolean flag = false;
		waitForLoad(driver, 200);
		By ele1 = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
		waitForElementPresent(driver, 120, ele1, " expand Aggregation");
		click(driver, ele1, "by drilling down to " + ReportName + " expand aggregation");
		Thread.sleep(2000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForElementPresent(driver, 900, sccloptionsheader);	
		waitForElementPresent(driver, 900, tablefieldfilterheader);
		click(driver, counterpartysearch);
		setTextenterdown(driver, counterpartysearch, ID);
		Thread.sleep(2000);
		click(driver, filter_btn);
		Thread.sleep(2000);
		waitForLoad(driver, 600);
		By tabledata = By.xpath("//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		waitForElementPresent(driver, 90, tabledata, Verify);
		WebElement extractxpathid = driver.findElement(By.xpath("//*[text()='" + actualViews + "']"));
		// System.out.println(extractxpathid.getAttribute("id"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);	
		String textLabel = driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "')]")).getText();
		//String textLabel = driver.findElement(By.xpath("//*[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']")).getText();
		System.out.println(textLabel);
		if(textLabel.equals(ID))
		{
			Add_Log.info("Successfully display " + Verify +  " "+actualViews +"  table data");
			Add_Log.info("Successfully display " + Verify +  " "+actualViews +"  table data");
		}
		else
		{
			Add_Log.info("Not able to view " + Verify +  " "+actualViews +"  table data fetch value = " +textLabel);
			Add_Log.info("Not able to view " + Verify +  " "+actualViews +"  table data fetch value = " +textLabel);
			TestResultStatus.mpaskeysnew.put(methodName, "Not able to view " + Verify +  " "+actualViews +"  table data fetch value = " +textLabel);
			flag = true;
		}
		Thread.sleep(2000);
		click(driver, reset_btninsidecap);
		Thread.sleep(2000);
		waitForLoad(driver, 600);
		By tabledata2 = By.xpath("//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		waitForElementPresent(driver, 90, tabledata2, Verify);
		By sort = By.xpath("//label[text()='" + Verify + "']//following::span[contains(@class,'filterswitch')]");
		Thread.sleep(2000);
		click(driver, sort,"by drilling down to " + ReportName + " and select " + viewName + " and click on Sort/Filters button");
		Thread.sleep(2000);
		String INPUTNumberSORTst = null;
		if(actualViews.equals("Id"))
		{
		INPUTNumberSORTst = INPUTTEXTSORT + "[" + Verify3 + "]";
		}
		else
		{
			INPUTNumberSORTst = INPUTNumberSORT + "[" + Verify3 + "]";
		}
		WebPageElements inputnumbersortwb  = new WebPageElements("Input text field for Sort and Filter", "xpath",INPUTNumberSORTst);
		try {
			waitForVisiblility(driver, 120, inputnumbersortwb);
			setTextenter(driver, inputnumbersortwb, ID);
			Thread.sleep(2000);
			waitForLoad(driver, 900);
			Thread.sleep(1000);
		} catch (Exception e) {
			Thread.sleep(2000);
			click(driver, sort,
					"by drilling down to " + ReportName + " and select " + viewName + " and click on Sort/Filters");
			Thread.sleep(1000);
			waitForVisiblility(driver, 120, inputnumbersortwb);
			setTextenter(driver, inputnumbersortwb, ID);
			Thread.sleep(2000);
			waitForLoad(driver, 900);
		}
		waitForLoad(driver, 9000);
		waitForElementPresent(driver, 90, tabledata, Verify);
		WebElement extractxpathid2 = driver.findElement(By.xpath("//*[text()='" + actualViews + "']"));
		// System.out.println(extractxpathid.getAttribute("id"));
		String pathnum2 = extractxpathid2.getAttribute("id");
		String[] xpathid2 = pathnum2.split("-");
		String textLabel2 = driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid2[1] + "')]")).getText();
	//	String textLabel2 = driver.findElement(By.xpath("//*[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid2[1] + "']")).getText();
		System.out.println(textLabel2);
		if (textLabel2.equals(ID)) {
			Reporter.log("Successfully Sort Value for " +  ReportName + " and select " + viewName );
			Add_Log.info("Successfully Sort Value for " +  ReportName + " and select " + viewName );
		} else {
			Reporter.log("Unable to Sort value" +  ReportName + " and select " + viewName );
			Add_Log.info("Unable to Sort value" +  ReportName + " and select " + viewName );
			TestResultStatus.mpaskeysnew.put(methodName, "Unable to Sort value" +  ReportName + " and select " + viewName );
			flag = true;
			Assert.fail();
		}
		if (flag) {
			TestResultStatus.Testfail = true;
			Reporter.log("No data Sort/Filter under " + ReportName + " and select " + viewName);
			Add_Log.info("No data Sort/Filter under " + ReportName + " and select " + viewName);
			TestResultStatus.mpaskeysnew.put(methodName, "No data Sort/Filter under " + ReportName + " and select " + viewName);
			Assert.fail();
		}
	
		flagdownload = downloadfilecommonregsccl(driver, methodName,viewName, ReportName, Verify, Verify2, ids, Fileformat,
				Environment, FileNameac);
		if (flagdownload) {
			Add_Log.info(
					Fileformat + " flag ******report not downloaded for " + viewName + " " + ReportName + " report");
			Reporter.log(
					Fileformat + " flag ******report not downloaded for " + viewName + " " + ReportName + " report");
			TestResultStatus.mpaskeysnew.put(methodName, Fileformat + " flag ******report not downloaded for " + viewName + " " + ReportName + " report");
			Assert.fail();
		}
		if (flag) {
			TestResultStatus.Testfail = true;
			allFlag = true;
		}
		return flag;
	}

	public void CommentSCCL(WebDriver driver,String methodName, String viewName, String ReportName, String Verify, String ID, String actualViews)
			throws InterruptedException, IOException {
	//	String[] actualViewsp = actualViews.split(",");
		boolean flagdownload = false;
		boolean flag = false;
		waitForLoad(driver, 200);
		By ele1 = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
		waitForElementPresent(driver, 120, ele1, " expand Aggregation");
		click(driver, ele1, "by drilling down to " + ReportName + " expand aggregation");
		Thread.sleep(2000);
		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForElementPresent(driver, 900, sccloptionsheader);	
		waitForElementPresent(driver, 900, tablefieldfilterheader);
		By tabledata = By.xpath("//label[text()='" + Verify + "']//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		waitForElementPresent(driver, 90, tabledata, Verify);
		By tablecheckbox1 = By.xpath("(//div[@class='x-grid-row-checker'])[1]");
		waitForElementPresent(driver, 90, tablecheckbox1, "Check box 1");
		click(driver, tablecheckbox1, "Check Box 1");
		By tablecheckbox2 = By.xpath("(//div[@class='x-grid-row-checker'])[2]");
		waitForElementPresent(driver, 90, tablecheckbox2, "Check box 2");
		click(driver, tablecheckbox2, "Check Box 2");
		Thread.sleep(2000);
		By Commenticon = By.xpath("//a[contains(@data-qtip,'Update Comment')]");
		waitForElementPresent(driver, 90, Commenticon, "Comment Icon");
		click(driver, Commenticon, "Comment Icon");
		By SCCLcommentbox = By.xpath("//div[text()='SCCL BULK COMMENTS ENTRY']");
		waitForElementPresent(driver, 90, SCCLcommentbox, "SCCL BULK COMMENTS ENTRY");
		By ExceptionCategory = By.xpath("//input[@name='fixedComments']");
		waitForElementPresent(driver, 90, ExceptionCategory, "Exception Category");
		click(driver, ExceptionCategory, "Exception Category");
		By UnderInvestigation = By.xpath("//li[text()='Under Investigation']");
		waitForVisiblility(driver, 120, UnderInvestigation, "Under Investigation");
		click(driver, UnderInvestigation, "Under Investigation");
		String commentsText = "//textarea[@name='commentsText']";
		WebPageElements commentsTextweb = new WebPageElements("commentsText", "xpath",commentsText);
		click(driver, commentsTextweb);
		Thread.sleep(2000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String inputcomment = ID+"-"+sdf.format(timestamp);
		setText(driver, commentsTextweb, inputcomment);
		Thread.sleep(2000);
		By Updatebutton = By.xpath("//span[text()='Update']");
		click(driver, Updatebutton, "Update Icon");
		Thread.sleep(2000);
		WebElement extractxpathid = driver.findElement(By.xpath("//*[text()='" + actualViews + "']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		String textLabel = driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "')]")).getText();
		System.out.println(textLabel);
		if (textLabel.startsWith(ID)) {
			Reporter.log("Successfully Updated Comment  "  + textLabel  + " for " +  ReportName + " and select " + viewName );
			Add_Log.info("Successfully Updated Comment "  + textLabel  + " for " +  ReportName + " and select " + viewName );
		} else {
			Reporter.log("Unable to Updated Comment  "+ textLabel  + " for " +  ReportName + " and select " + viewName );
			Add_Log.info("Unable to Updated Comment  "+ textLabel  + " for " +  ReportName + " and select " + viewName );
			TestResultStatus.mpaskeysnew.put(methodName, "Unable to Updated Comment  "+ textLabel  + " for " +  ReportName + " and select " + viewName );
			flag = true;
			Assert.fail();
		}
		click(driver, tablecheckbox1, "Check Box 1");
		Thread.sleep(1000);
		click(driver, tablecheckbox2, "Check Box 2");
		Thread.sleep(1000);
		click(driver, Commenticon, "Comment Icon");
		Thread.sleep(1000);
		waitForElementPresent(driver, 90, SCCLcommentbox, "SCCL BULK COMMENTS ENTRY");
		By Clearbutton = By.xpath("//span[text()='Clear']");
		click(driver, Clearbutton, "Clear Button");
		Thread.sleep(1000);
		click(driver, Updatebutton, "Update Button");
		Thread.sleep(2000);
		WebElement extractxpathid2 = driver.findElement(By.xpath("//*[text()='" + actualViews + "']"));
		String pathnum2 = extractxpathid2.getAttribute("id");
		String[] xpathid2 = pathnum2.split("-");
		String textLabel2 = driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid2[1] + "')]")).getText();
		System.out.println(textLabel2);
		if (textLabel2.equals("Click to Enter Comment")) {
			Reporter.log("Successfully Clear  Comment  "  + textLabel2  + " for " +  ReportName + " and select " + viewName );
			Add_Log.info("Successfully Clear Comment "  + textLabel2  + " for " +  ReportName + " and select " + viewName );
		}
		else {
			Reporter.log("Unable to Clear Comment  "+ textLabel2  + " for " +  ReportName + " and select " + viewName );
		Add_Log.info("Unable to Clear Comment  "+ textLabel2  + " for " +  ReportName + " and select " + viewName );
		TestResultStatus.mpaskeysnew.put(methodName, "Unable to Clear Comment  "+ textLabel2  + " for " +  ReportName + " and select " + viewName );
		flag = true;
		Assert.fail();
	}
	}
}



