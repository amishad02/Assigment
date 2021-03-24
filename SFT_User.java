package pageobjects;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import property.IHomePage;
import testsuitebase.TestResultStatus;
import utility.SeleniumUtils;
import utility.WebPageElements;

public class SFT_User extends SeleniumUtils implements IHomePage{
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
	
	// main selection
		public void Capitalsftportal(WebDriver driver, String viewName, String ReportName, String Verify)
				throws InterruptedException {
			
			waitForLoad(driver, 200);
			By ele1 = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
			waitForElementPresent(driver, 120, ele1, " expand Aggregation");
			click(driver, ele1, "by drilling down to " + ReportName + " expand aggregation");
			Thread.sleep(2000);
			
			waitForLoad(driver, 900);
			By ele3 = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
			click(driver, ele3, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
			Thread.sleep(2000);
			waitForLoad(driver, 900);

		}
	
		
		
		
		
		public boolean AggregationCheckcommon(WebDriver driver, String viewName, String actualViews, String ReportName,
				String Verify) throws InterruptedException {

			boolean flag = false;
			String[] actualViewsp = actualViews.split("\\,");
			List<String> views = new ArrayList<>();
			Capitalsftportal(driver, viewName, ReportName, Verify);
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

		public void SFTPheader(WebDriver driver, String viewName, String actualViews, String ReportName, String Verify)
				throws InterruptedException {
			boolean flag = false;
			String[] actualViewsp = actualViews.split("\\,");
			List<String> views = new ArrayList<>();
			Capitalsftportal(driver, viewName, ReportName, Verify);
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
		
		
		// COB select
		public void cobselect(WebDriver driver, String reportingPeriod) throws InterruptedException {
			Thread.sleep(2000);
			waitForLoadbaba(driver, 200);
			waitForElementPresent(driver, 120, sftcobselect);
			click(driver, sftcobselect);
			Thread.sleep(2000);
			//By cobvalue = By.xpath("//li[text()='" + reportingPeriod + "']");
			By cobvalue = By.xpath("(//ul//li[contains(@class,'x-boundlist-item')])[1]");
			click(driver, cobvalue, "reporting Period");
			Thread.sleep(2000);
			waitForLoad(driver, 900);
		}

		
		public void Mandatoryfields(WebDriver driver, String reportingPeriod,String Verify3,String Verify4) throws InterruptedException {
			String[] Verify4sp = Verify4.split("\\,");
			cobselect(driver, reportingPeriod);
			waitForElementPresent(driver, 120, obligorsearch);
			setTextenter(driver, obligorsearch, Verify4);
			waitForElementPresent(driver, 120, nettingaggrement);
			setTextenter(driver, nettingaggrement, Verify3);
			waitForElementPresent(driver, 120, apply_btn);
			click(driver, apply_btn);
			
		}
		
		
		public void nettingsetdata(WebDriver driver, String viewName, String ReportName, String Verify, String reportingPeriod,String Verify3,String Verify4) throws InterruptedException {
			
			Capitalsftportal(driver, viewName, ReportName, Verify);
			//Mandatoryfields(driver, reportingPeriod, Verify3, Verify4);
			cobselect(driver, reportingPeriod);
			EnterobligorandSearch(driver, Verify4);
			EnternettingAggrementandSearch(driver, Verify3);
			waitForElementPresent(driver, 120, apply_btn);
			click(driver, apply_btn);
			Nettingsetdatadisplay(driver);
		}
		
		public void nettingsetdatadownload(WebDriver driver, String viewName, String ReportName, String Verify, String reportingPeriod,String Verify3,String Verify4,String Fileformat,String FileNameac) throws InterruptedException, IOException {
			String[] Verifysp = Verify.split("\\,");
			String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
			Capitalsftportal(driver, viewName, ReportName, Verify);
			//Mandatoryfields(driver, reportingPeriod, Verify3, Verify4);
			cobselect(driver, reportingPeriod);
			EnterobligorandSearch(driver, Verify4);
			EnternettingAggrementandSearch(driver, Verify3);
			waitForElementPresent(driver, 120, apply_btn);
			click(driver, apply_btn);
			Nettingsetdatadisplay(driver);
			Thread.sleep(2000);
			waitForLoad(driver, 120);
			By ele1 = By.xpath("(//label[text()='" + Verifysp[0]
					+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-excel '])[1]");
			waitForElementPresent(driver, 120, ele1, " Download icon" + Verifysp[0]);
			click(driver, ele1, " Download icon " + Verifysp[0]);
			downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
		}
		
     public void transactionToview(WebDriver driver, String viewName, String ReportName, String Verify, String reportingPeriod,String Verify3,String Verify4) throws InterruptedException {
    	 String[] Verifysp = Verify.split("\\,");
			Capitalsftportal(driver, viewName, ReportName, Verify);
			//Mandatoryfields(driver, reportingPeriod, Verify3, Verify4);
			cobselect(driver, reportingPeriod);
			EnterobligorandSearch(driver, Verify4);
			EnternettingAggrementandSearch(driver, Verify3);
			waitForElementPresent(driver, 120, apply_btn);
			click(driver, apply_btn);
			Nettingsetdatadisplay(driver); 
			By checkbox = By.xpath("//div[@class='x-grid-row-checker']");
			click(driver, checkbox, "Grid row Checker box");
			Thread.sleep(2000);
			By ele1 = By.xpath("//label[text()='" + Verifysp[0]
					+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-run ']");
			waitForElementPresent(driver, 120, ele1, " Play run icon" + Verifysp[0]);
			click(driver, ele1, " Play run icon " + Verifysp[0]);
			Transactiondatadisplay(driver);
     }
		
     
 	public void nettingsetdatafilter(WebDriver driver, String viewName, String ReportName, String Verify, String reportingPeriod,String Verify3,String Verify4,String actualViews) throws InterruptedException {
 		String[] Verifysp = Verify.split("\\,");
		Capitalsftportal(driver, viewName, ReportName, Verify);
		//Mandatoryfields(driver, reportingPeriod, Verify3, Verify4);
		cobselect(driver, reportingPeriod);
		EnterobligorandSearch(driver, Verify4);
		EnternettingAggrementandSearch(driver, Verify3);
		waitForElementPresent(driver, 120, apply_btn);
		click(driver, apply_btn);
		Nettingsetdatadisplay(driver);
		Nettingtablecontent(driver, Verify, actualViews);
		String sortclick = "(//label[text()='" + Verifysp[0]
				+ "']//following::span[contains(@class,'filterswitch')])[1]";
		By sort = By.xpath(sortclick);
		waitForElementPresent(driver, 120, sort, " " + Verifysp[0] + " and click on Sort/Filters");
		click(driver, sort, "by drilling down to " + Verifysp[0] + " and click on Sort/Filters");
		Thread.sleep(2000);
		
	}
 	
 	public void nettingsetdatafilter1(WebDriver driver, String viewName, String ReportName, String Verify, String reportingPeriod,String Verify3,String Verify4,String actualViews,String Verify2,String Report,String actualViews4) throws InterruptedException, IOException {
 		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViews4p = actualViews4.split("\\,");
		Capitalsftportal(driver, viewName, ReportName, Verify);
		//Mandatoryfields(driver, reportingPeriod, Verify3, Verify4);
		cobselect(driver, reportingPeriod);
		EnterobligorandSearch(driver, Verify4);
		EnternettingAggrementandSearch(driver, Verify3);
		waitForElementPresent(driver, 120, apply_btn);
		click(driver, apply_btn);
		Nettingsetdatadisplay(driver);
		Nettingtablecontent(driver, Verify, actualViews);
		/*String sortclick = "(//label[text()='" + Verifysp[0]
				+ "']//following::span[contains(@class,'filterswitch')])[1]";
		By sort = By.xpath(sortclick);
		waitForElementPresent(driver, 120, sort, " " + Verifysp[0] + " and click on Sort/Filters");
		click(driver, sort, "by drilling down to " + Verifysp[0] + " and click on Sort/Filters");
		Thread.sleep(2000);*/
		SortFilter(driver, viewName, ReportName, Verify2, Verify3, Verify, Report, Verify4, reportingPeriod, actualViews, actualViews4);
	}
 	
 	public void Transactionsetdatadownload(WebDriver driver, String viewName, String ReportName, String Verify, String reportingPeriod,String Verify3,String Verify4,String Fileformat,String FileNameac) throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
		Capitalsftportal(driver, viewName, ReportName, Verify);
		//Mandatoryfields(driver, reportingPeriod, Verify3, Verify4);
		cobselect(driver, reportingPeriod);
		EnterobligorandSearch(driver, Verify4);
		EnternettingAggrementandSearch(driver, Verify3);
		waitForElementPresent(driver, 120, apply_btn);
		click(driver, apply_btn);
		Nettingsetdatadisplay(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By checkbox = By.xpath("//div[@class='x-grid-row-checker']");
		click(driver, checkbox, "Grid row Checker box");
		Thread.sleep(2000);
		By ele1 = By.xpath("//label[text()='" + Verifysp[0]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-run ']");
		waitForElementPresent(driver, 120, ele1, " Play run icon" + Verifysp[0]);
		click(driver, ele1, " Play run icon " + Verifysp[0]);
		Transactiondatadisplay(driver);
		By ele2 = By.xpath("(//label[text()='" + Verifysp[1]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-excel '])[1]");
		waitForElementPresent(driver, 120, ele2, " Download icon" + Verifysp[1]);
		click(driver, ele2, " Download icon " + Verifysp[1]);
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
	}
	
 	public void Transactionsetdatafilter(WebDriver driver, String viewName, String ReportName, String Verify, String reportingPeriod,String Verify3,String Verify4,String actualViews) throws InterruptedException {
 		String[] Verifysp = Verify.split("\\,");
		Capitalsftportal(driver, viewName, ReportName, Verify);
		//Mandatoryfields(driver, reportingPeriod, Verify3, Verify4);
		cobselect(driver, reportingPeriod);
		EnterobligorandSearch(driver, Verify4);
		EnternettingAggrementandSearch(driver, Verify3);
		waitForElementPresent(driver, 120, apply_btn);
		click(driver, apply_btn);
		Nettingsetdatadisplay(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By checkbox = By.xpath("//div[@class='x-grid-row-checker']");
		click(driver, checkbox, "Grid row Checker box");
		Thread.sleep(2000);
		By ele1 = By.xpath("//label[text()='" + Verifysp[0]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-run ']");
		waitForElementPresent(driver, 120, ele1, " Play run icon" + Verifysp[0]);
		click(driver, ele1, " Play run icon " + Verifysp[0]);
		Transactiondatadisplay(driver);
		//Transactiontablecontent(driver, Verifysp[1], actualViews);
		String sortclick = "(//label[text()='" + Verifysp[1]
				+ "']//following::span[contains(@class,'filterswitch')])[1]";
		By sort = By.xpath(sortclick);
		waitForElementPresent(driver, 120, sort, " " + Verifysp[1] + " and click on Sort/Filters");
		click(driver, sort, "by drilling down to " + Verifysp[1] + " and click on Sort/Filters");
		Thread.sleep(2000);
		
	}
 	
 	public void Transactionsetdatafilter1(WebDriver driver, String viewName, String ReportName, String Verify, String reportingPeriod,String Verify3,String Verify4,String actualViews,String Verify2,String Report,String actualViews4) throws InterruptedException, IOException {
 		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViews4p = actualViews4.split("\\,");
		Capitalsftportal(driver, viewName, ReportName, Verify);
		//Mandatoryfields(driver, reportingPeriod, Verify3, Verify4);
		cobselect(driver, reportingPeriod);
		EnterobligorandSearch(driver, Verify4);
		EnternettingAggrementandSearch(driver, Verify3);
		waitForElementPresent(driver, 120, apply_btn);
		click(driver, apply_btn);
		Nettingsetdatadisplay(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By checkbox = By.xpath("//div[@class='x-grid-row-checker']");
		click(driver, checkbox, "Grid row Checker box");
		Thread.sleep(2000);
		By ele1 = By.xpath("//label[text()='" + Verifysp[0]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-run ']");
		waitForElementPresent(driver, 120, ele1, " Play run icon" + Verifysp[0]);
		click(driver, ele1, " Play run icon " + Verifysp[0]);
		Transactiondatadisplay(driver);
		//Transactiontablecontent(driver, Verify, actualViews);
	/*	String sortclick = "(//label[text()='" + Verifysp[1]
				+ "']//following::span[contains(@class,'filterswitch')])[1]";
		By sort = By.xpath(sortclick);
		waitForElementPresent(driver, 120, sort, " " + Verifysp[1] + " and click on Sort/Filters");
		click(driver, sort, "by drilling down to " + Verifysp[1] + " and click on Sort/Filters");
		Thread.sleep(2000);  */
		SortFilter(driver, viewName, ReportName, Verify2, Verify3, Verify, Report, Verify4, reportingPeriod, actualViews, actualViews4);
	}
 	
 	public void TransactionsIddatadownload(WebDriver driver, String viewName, String ReportName, String Verify,String Verify2, String reportingPeriod,String Verify3,String Verify4,String Fileformat,String FileNameac,String Report,String actualViews,String actualViews4) throws InterruptedException, IOException {
 		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViews4p = actualViews4.split("\\,");
		
		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
		Capitalsftportal(driver, viewName, ReportName, Verify);
		//Mandatoryfields(driver, reportingPeriod, Verify3, Verify4);
		cobselect(driver, reportingPeriod);
		EnterobligorandSearch(driver, Verify4);
		EnternettingAggrementandSearch(driver, Verify3);
		waitForElementPresent(driver, 120, apply_btn);
		click(driver, apply_btn);
		Nettingsetdatadisplay(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By checkbox = By.xpath("//div[@class='x-grid-row-checker']");
		click(driver, checkbox, "Grid row Checker box");
		Thread.sleep(2000);
		By ele1 = By.xpath("//label[text()='" + Verifysp[0]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-run ']");
		waitForElementPresent(driver, 120, ele1, " Play run icon" + Verifysp[0]);
		click(driver, ele1, " Play run icon " + Verifysp[0]);
		DownloadTransactionID(driver, viewName, ReportName, Verify2, Verify3, Verify, Report, Verify4, reportingPeriod, actualViews, actualViews4);
	     
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
	     }
 	
 	public void NonzeroTransactionsdisplay(WebDriver driver, String viewName, String ReportName, String Verify, String reportingPeriod,String Verify3,String Verify4,String actualViews,String Report) throws InterruptedException {
 		String[] Verifysp = Verify.split("\\,");
 		String[] Reportsp = Report.split("\\,");
		Capitalsftportal(driver, viewName, ReportName, Verify);
		//Mandatoryfields(driver, reportingPeriod, Verify3, Verify4);
		cobselect(driver, reportingPeriod);
		EnterobligorandSearch(driver, Verify4);
		EnternettingAggrementandSearch(driver, Verify3);
		waitForElementPresent(driver, 120, apply_btn);
		click(driver, apply_btn);
		Nettingsetdatadisplay(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By checkbox = By.xpath("//div[@class='x-grid-row-checker']");
		click(driver, checkbox, "Grid row Checker box");
		Thread.sleep(2000);
		By ele1 = By.xpath("//label[text()='" + Verifysp[0]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-run ']");
		waitForElementPresent(driver, 120, ele1, " Play run icon" + Verifysp[0]);
		click(driver, ele1, " Play run icon " + Verifysp[0]);
		Transactiondatadisplay(driver);
		NonzeroButton(driver, Verify);
		Transactionvalue(driver, Verifysp[1], Reportsp[0]);
		Transactionvalue(driver, Verifysp[1], Reportsp[1]);
		//Transactionvalue(driver, Verifysp[1], Reportsp[2]);
 	}
 	
 	public void RedirecttoSFTPortal(WebDriver driver, String viewName, String ReportName, String Verify,String Verify2, String reportingPeriod,String Verify3,String Verify4,String actualViews,String Report) throws InterruptedException {
 		String[] Verifysp = Verify.split("\\,");
 		String[] Reportsp = Report.split("\\,");
		Capitalsftportal(driver, viewName, ReportName, Verify);
		//Mandatoryfields(driver, reportingPeriod, Verify3, Verify4);
		cobselect(driver, reportingPeriod);
		EnterobligorandSearch(driver, Verify4);
		EnternettingAggrementandSearch(driver, Verify3);
		waitForElementPresent(driver, 120, apply_btn);
		click(driver, apply_btn);
		Nettingsetdatadisplay(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By checkbox = By.xpath("//div[@class='x-grid-row-checker']");
		click(driver, checkbox, "Grid row Checker box");
		Thread.sleep(2000);
		By ele1 = By.xpath("//label[text()='" + Verifysp[0]
				+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-run ']");
		waitForElementPresent(driver, 120, ele1, " Play run icon" + Verifysp[0]);
		click(driver, ele1, " Play run icon " + Verifysp[0]);
		Transactiondatadisplay(driver);
		//Transactionnettingseidclick(driver, Verify2);
		Transactionnettingseidclick1(driver, Verify2, Verify);
		VerifyNettingsetid(driver, Verify2);
 	}
 	 
 	public void VerifyNettingsetid(WebDriver driver,String Verify2)throws InterruptedException {
		List<String> views = new ArrayList<>();
		waitForLoadbaba(driver, 9000);
		waitForElementPresent(driver, 120, perspectivecombbox);
		waitForLoadbaba(driver, 9000);
		waitForLoadbaba(driver, 9000);
		waitForLoadbaba(driver, 9000);
		Thread.sleep(2000);
		WebElement extractxpathid = driver
				.findElement(By.xpath("//span[text()='Basel SFT Trade Detail Info']//following::span[text()='Netting Set ID']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		 By filter = By.xpath("//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "')]//div");

	       waitForElementPresent(driver, 900, filter, "text");
		WebElement filterscenarioName = driver.findElement(filter);
		waitForLoadbaba(driver, 900);
		Thread.sleep(2000);
		String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
		System.out.println(filterscenarioNamea);
		if (filterscenarioName.isDisplayed()) {
			Add_Log.info("Successfully displayed Search Netting set ID Transaction");
			Reporter.log("Successfully displayed Search Netting set ID Transaction");
		} else {
			Add_Log.info("Unable to displayed Search Netting set ID Transaction");
			Reporter.log("Unable to displayed Search Netting set ID Transaction");
			TestResultStatus.mpaskeysnew.put(methodName(),
					"Unable to displayed Search Netting set ID Transaction");
			Assert.fail();

		} 
		int size = driver.findElements(By.xpath(
				"//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "')]//div"))
				.size();
		System.out.println(size);
		int i;
		for (i = 1; i <= size; i++) {
			views.add(getatt3(driver, By.xpath("(//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "')]//div)[" + i + "]"), " Nettingset  ID # " + i));
		}
		for (int i2 = 0; i2 < views.size(); i2++) {
			if (views.get(0).contains(Verify2)) {
				Add_Log.info("Successfully displayed Transaction ID " + views.get(0));
				Reporter.log("Successfully displayed Transaction ID " + views.get(0));
			} else {
				Add_Log.info("Unable to displayed Transaction ID  " + views.get(0));
				Reporter.log("Unable to displayed Transaction ID  " + views.get(0));
				TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Transaction ID  " + views.get(0));
				ITestResult result = Reporter.getCurrentTestResult();
				result.setAttribute("DontRetry", Boolean.TRUE.toString());
				Assert.fail();
			} 
		}   
	}

		public void EnterobligorandSearch(WebDriver driver,String Verify4)
				throws InterruptedException {
			try {
				String sort1 = "//div/input[@name='obligorCombo']";
				By sort = By.xpath(sort1);
				Thread.sleep(2000);
				WebPageElements sort_dll = new WebPageElements("Obligor", "xpath", sort1);
				WebElement sort_dlle = driver.findElement(sort);
				click(driver, sort, "  click on obligor Search");
				Thread.sleep(2000);
				waitForLoad(driver, 900);
				Thread.sleep(2000);
				setText(driver, sort_dll, Verify4);
				Thread.sleep(2000);
				waitForLoadbaba(driver, 9000);
				waitForLoadbaba(driver, 9000);
				
				By eletext = By.xpath("//li//font[text()='" + Verify4 + "']");
				waitForElementPresent(driver, 90000, eletext, "text");
				click(driver, eletext, "text");
				Thread.sleep(2000);
				waitForLoad(driver, 900);
				String sort2 = "//span[text()='Obligor']/parent::label/following-sibling::div[1]//input";
				By sort21 = By.xpath(sort2);
				Thread.sleep(2000);
				WebPageElements sort2_dll = new WebPageElements("FACILITY SEARCH RESULT", "xpath", sort2);
				waitForElementPresent(driver, 30, sort2_dll);
				WebElement sort2_dlle = driver.findElement(sort21);
				String valuetext = sort2_dlle.getText();
				System.out.println(valuetext);
				if (Verify4.contains(valuetext)) {
					Add_Log.info("Successfully Obligor " + Verify4 + " selected");
					Reporter.log("Successfully Obligor " + Verify4 + " selected");
				} else {
					Add_Log.info("Unable Obligor " + Verify4 + " selected");
					Reporter.log("Unable Obligor " + Verify4 + " selected");
					TestResultStatus.mpaskeysnew.put(methodName(), "Unable Obligor " + Verify4 + " selected");
					Assert.fail();
				}
				
			} catch (Exception e1) {

			}
			
			

		}

		public void EnternettingAggrementandSearch(WebDriver driver,String Verify3)
				throws InterruptedException {

		
			try {
				String sort1 = "//div/input[@name='nettingAggreementIdCombo']";
				By sort = By.xpath(sort1);
				Thread.sleep(2000);
				WebPageElements sort_dll = new WebPageElements("Netting Aggrement", "xpath", sort1);
				WebElement sort_dlle = driver.findElement(sort);
				click(driver, sort, "  click on Netting Aggrement Search");
				Thread.sleep(2000);
				waitForLoad(driver, 900);
				Thread.sleep(2000);
				setText(driver, sort_dll, Verify3);
				Thread.sleep(2000);
				waitForLoadbaba(driver, 9000);
				waitForLoadbaba(driver, 9000);
				
				By eletext = By.xpath("//li//font[text()='" + Verify3 + "']");
				waitForElementPresent(driver, 90000, eletext, "text");
				click(driver, eletext, "text");
				Thread.sleep(2000);
				waitForLoad(driver, 900);
				String sort2 = "//span[text()='Netting Agreement ID']/parent::label/following-sibling::div[1]";
				By sort21 = By.xpath(sort2);
				Thread.sleep(2000);
				WebPageElements sort2_dll = new WebPageElements("FACILITY SEARCH RESULT", "xpath", sort2);
				waitForElementPresent(driver, 30, sort2_dll);
				WebElement sort2_dlle = driver.findElement(sort21);
				String valuetext = sort2_dlle.getText();
				System.out.println(valuetext);
				if (Verify3.contains(valuetext)) {
					Add_Log.info("Successfully Netting Aggrement " + Verify3 + " selected");
					Reporter.log("Successfully Netting Aggrement " + Verify3 + " selected");
				} else {
					Add_Log.info("Unable Netting Aggrement " + Verify3 + " selected");
					Reporter.log("Unable Netting Aggrement " + Verify3 + " selected");
					TestResultStatus.mpaskeysnew.put(methodName(), "Unable Netting Aggrement " + Verify3 + " selected");
					Assert.fail();
				}
				
			} catch (Exception e1) {

			}
			
			

		}

		
		
		public void Nettingsetdatadisplay(WebDriver driver)
				throws InterruptedException {

			waitForLoad(driver, 90);
			Thread.sleep(2000);
			WebElement extractxpathid = driver
					.findElement(By.xpath("(//label[text()='Netting Set']//following::span[text()='Netting Set ID'])[1]"));

			String pathnum = extractxpathid.getAttribute("id");
			String[] xpathid = pathnum.split("-");
			System.out.println(xpathid[0]);
			System.out.println(xpathid[1]);
			System.out.println(xpathid[2]);
			By filter = By.xpath("//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "')]//div");
			WebElement filterscenarioName = driver.findElement(filter);
			waitForLoad(driver, 90);
			Thread.sleep(2000);
			String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
			System.out.println(filterscenarioNamea);
			if (filterscenarioName.isDisplayed()) {
				Add_Log.info("Successfully displayed Search Netting set ID");
				Reporter.log("Successfully displayed Search Netting set ID");
			} else {
				Add_Log.info("Unable to displayed Search Netting set ID");
				Reporter.log("Unable to displayed Search Netting set ID");
				TestResultStatus.mpaskeysnew.put(methodName(),
						"Unable to displayed Search Netting set ID");
				Assert.fail();

			} 
		}

		public void Transactiondatadisplay(WebDriver driver)
				throws InterruptedException {
			List<String> views = new ArrayList<>();
			waitForLoad(driver, 90);
			Thread.sleep(2000);
			WebElement extractxpathid = driver
					.findElement(By.xpath("//label[text()='Transaction']//following::span[text()='Transaction ID']"));

			String pathnum = extractxpathid.getAttribute("id");
			String[] xpathid = pathnum.split("-");
			System.out.println(xpathid[0]);
			System.out.println(xpathid[1]);
			System.out.println(xpathid[2]);
			/*	 By filter = By.xpath("//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div");
			WebElement filterscenarioName = driver.findElement(filter);
			waitForLoad(driver, 90);
			Thread.sleep(2000);
			String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
			System.out.println(filterscenarioNamea);
			if (filterscenarioName.isDisplayed()) {
				Add_Log.info("Successfully displayed Search Netting set ID Transaction");
				Reporter.log("Successfully displayed Search Netting set ID Transaction");
			} else {
				Add_Log.info("Unable to displayed Search Netting set ID Transaction");
				Reporter.log("Unable to displayed Search Netting set ID Transaction");
				TestResultStatus.mpaskeysnew.put(methodName(),
						"Unable to displayed Search Netting set ID Transaction");
				Assert.fail();

			} */
			int size = driver.findElements(By.xpath(
					"//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div//a"))
					.size();
			System.out.println(size);
			int i;
			for (i = 1; i <= size; i++) {
				views.add(getatt3(driver, By.xpath("(//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-"
						+ xpathid[1] + "']//div//a)[" + i + "]"), " Transaction ID # " + i));
			}
			for (int i2 = 0; i2 < views.size(); i2++) {
				if (views.get(i2).startsWith("0")) {
					Add_Log.info("Unable to displayed Transaction ID  " + views.get(i2));
					Reporter.log("Unable to displayed Transaction ID  " + views.get(i2));
					TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Transaction ID  " + views.get(i2));
					ITestResult result = Reporter.getCurrentTestResult();
					result.setAttribute("DontRetry", Boolean.TRUE.toString());
					Assert.fail();
				} else {
					Add_Log.info("Successfully displayed Transaction ID " + views.get(i2));
					Reporter.log("Successfully displayed Transaction ID " + views.get(i2));
				} 
			}   
		}

		public void DownloadTransactionID(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
				String Verify, String Report, String Verify4, String reportingPeriod, String actualViews, String actualViews4)
				throws InterruptedException {
		/*	List<String> views = new ArrayList<>();
			waitForLoad(driver, 90);
			Thread.sleep(2000);
			WebElement extractxpathid = driver
					.findElement(By.xpath("//label[text()='Transaction']//following::span[text()='Transaction ID']"));

			String pathnum = extractxpathid.getAttribute("id");
			String[] xpathid = pathnum.split("-");
			System.out.println(xpathid[0]);
			System.out.println(xpathid[1]);
			System.out.println(xpathid[2]);
			
			int size = driver.findElements(By.xpath(
					"//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div//a"))
					.size();
			System.out.println(size);
			int i;
			for (i = 1; i <= size; i++) {
				views.add(getatt3(driver, By.xpath("(//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-"
						+ xpathid[1] + "']//div//a)[" + i + "]"), " Transaction ID # " + i));
			}
			for (int i2 = 0; i2 < views.size(); i2++) {
				if (views.get(i2).startsWith("A")) {
					Add_Log.info("Unable to displayed Transaction ID  " + views.get(i2));
					Reporter.log("Unable to displayed Transaction ID  " + views.get(i2));
					TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Transaction ID  " + views.get(i2));
					ITestResult result = Reporter.getCurrentTestResult();
					result.setAttribute("DontRetry", Boolean.TRUE.toString());
					Assert.fail();
				} else {
					Add_Log.info("Successfully displayed Transaction ID " + views.get(i2));
					Reporter.log("Successfully displayed Transaction ID " + views.get(i2));
				} 
				if(views.get(i2).equals(Verify2))
				{
					By eletext = By.xpath("//a[text()='" + Verify2 + "']");
					waitForElementPresent(driver, 90000, eletext, "Transaction ID" + Verify2);
					click(driver, eletext, "Transaction ID" + Verify2 );
				}
				else
				{
					Add_Log.info("Unable to displayed Transaction ID  " + Verify2);
					Reporter.log("Unable to displayed Transaction ID  " + Verify2);
				}
			}  */
			
			String[] Verifysp = Verify.split("\\,");
			String[] Verify2sp = Verify2.split("\\,");
			String[] Verify3sp = Verify3.split("\\,");
			String[] Reportsp = Report.split("\\,");
			String[] actualViewsp = actualViews.split("\\,");
			String[] actualViews4p = actualViews4.split("\\,");
			
			for(int i=0;i<Verifysp.length;i++)
			System.out.println(Verifysp[i]);
			List<String> views2 = new ArrayList<>();
			
			//Sortfliter
			List<String> views = new ArrayList<>();
			WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verifysp[1]+"']//following::span[text()='"+Reportsp[0]+"']"));
			String pathnum = extractxpathid.getAttribute("id");
			String[] xpathid = pathnum.split("-");
			System.out.println(xpathid[0]);
			System.out.println(xpathid[1]);
			System.out.println(xpathid[2]);

			String xpathdivtable = "(//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "')]//div)[1]";
			By filter = By.xpath(xpathdivtable);
			WebElement filterscenarioName = driver.findElement(filter);
			String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
			List<WebElement> options2 = driver.findElements(filter);
			for (WebElement ele : options2) {
				views.add(ele.getAttribute(("textContent")));
			}
			System.out.println(views);
			//System.out.println(views.get(1));
			Thread.sleep(2000);	
			try {
				String filterinput = null;
				if(Verifysp[1].equals("Netting Set"))
				{
				filterinput ="(//div[contains(@data-uipath,'sftUserPortalView/nettingSetElement/nettingSet')]//div[contains(@class,'x-container inline-filter')]//div[contains(@class,'filterCt ')])[2]//input";
				}
				if(Verifysp[1].equals("Transaction"))
				{
				filterinput ="(//div[contains(@data-uipath,'sftUserPortalView/sftUserTransactionElement')]//div[contains(@class,'x-container inline-filter')]//div[contains(@class,'filterCt ')])[2]//input";
				}
				WebElement tri=driver.findElement(By.xpath(filterinput));
				String byt=tri.getAttribute("id");
				System.out.println(byt);
				WebPageElements filterinput_dll = new WebPageElements("Sort/filiter", "xpath", filterinput);
				setTextenter(driver, filterinput_dll,views.get(0));
				Thread.sleep(2000);
				waitForLoad(driver, 100);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				
			}
		//	String SearchInputtable2 = "(//label[text()='"+Verifysp[1]+"']//following::tr)[2]";
		//	WebPageElements searchinputtable2  = new WebPageElements(" Search Input table 2nd row", "xpath",SearchInputtable2);
		//	waitForElementInvisibilty(driver, 120, searchinputtable2);
			//String textLabel = driver.findElement(By.xpath(SORTGETTEXT + "[" + Verify2 + "]")).getText();
			//System.out.println(textLabel);
			
			List<String> textlabel = new ArrayList<>();
			String xpathdivtable2 = "(//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "')]//div)[1]";
			By filter2 = By.xpath(xpathdivtable2);
			WebElement filterscenarioName2 = driver.findElement(filter2);
			String filterscenarioNamea2 = filterscenarioName2.getAttribute("textContent");
			List<WebElement> options3 = driver.findElements(filter2);
			for (WebElement ele3 : options3) {
				textlabel.add(ele3.getAttribute(("textContent")));
			}
			System.out.println(textlabel.toString().trim());
			//System.out.println(views.get(1));
				if (textlabel.toString().contains(views.get(0))) {
				Reporter.log("Successfully Sort Value");
				Add_Log.info("Successfully Sort Value");
			} else {
				Reporter.log("Unable to Sort value");
				Add_Log.info("Unable to Sort value");
				TestResultStatus.mpaskeysnew.put(methodName(), "Unable to Sort value");
				Assert.fail();
			}
				By eletext = By.xpath("//a[text()='" + views.get(0) + "']");
				waitForElementPresent(driver, 90000, eletext, "Transaction ID" + views.get(0));
				click(driver, eletext, "Transaction ID" + views.get(0) );
			String ExposureSUMMARYFIELD = "(//label[text()='"+Verifysp[1]+"']//following::div[@class='x-grid-header-ct x-docked x-grid-header-ct-default x-docked-top x-grid-header-ct-docked-top x-grid-header-ct-default-docked-top x-box-layout-ct'])[1]//child::div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']";
			WebPageElements exposuresummaryfield = new WebPageElements("Exposure Summary filter", "xpath",
					ExposureSUMMARYFIELD);
			
			waitForElementPresent(driver, 30, exposuresummaryfield);
			int size1 = driver.findElements(By.xpath(ExposureSUMMARYFIELD)).size();
			for (int i = 1; i <= size1; i++) {
				views2.add(getatt(driver, By.xpath("(" + ExposureSUMMARYFIELD + ")[" + i + "]"), Verifysp[1]+ " table Feild Content #" + i));	
			}
			System.out.println(views2);
		
		System.out.println(actualViews4p.length);
		for (int i1 = 0; i1 < actualViews4p.length; i1++) {

			if (views2.get(i1).equals(actualViews4p[i1].trim().toString()))
			{
				Add_Log.info("Successfully displayed " + views2.get(i1) + " under ");
				Reporter.log("Successfully displayed " + views2.get(i1) + " under ");
			} else {
				Add_Log.info("Unable to display "+ actualViews4p[i1].trim().toString() + " under ");
				Reporter.log("Unable to display "+ actualViews4p[i1].trim().toString() + " under ");
				TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display "+ actualViews4p[i1].trim().toString() + " under " );
				Assert.fail();
			}	
		}
			
		}
         
		
		public void Transactionnettingseidclick(WebDriver driver,String Verify2)
				throws InterruptedException {
			List<String> views = new ArrayList<>();
			waitForLoad(driver, 90);
			Thread.sleep(2000);
			WebElement extractxpathid = driver
					.findElement(By.xpath("//label[text()='Transaction']//following::span[text()='Netting Set ID']"));

			String pathnum = extractxpathid.getAttribute("id");
			String[] xpathid = pathnum.split("-");
			System.out.println(xpathid[0]);
			System.out.println(xpathid[1]);
			System.out.println(xpathid[2]);
			
			int size = driver.findElements(By.xpath(
					"//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + " x-grid-cell-first']//div//a"))
					.size();
			System.out.println(size);
			int i;
			for (i = 1; i < size; i++) {
				views.add(getatt3(driver, By.xpath("(//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-"
						+ xpathid[1] + " x-grid-cell-first']//div//a)[" + i + "]"), " Netting Set ID # " + i));
			}
			for (int i2 = 0; i2 < views.size(); i2++) {
				if (views.get(i2).startsWith("*")) {
					Add_Log.info("Unable to displayed Netting Set ID  " + views.get(i2));
					Reporter.log("Unable to displayed Netting Set ID  " + views.get(i2));
					TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Netting Set ID  " + views.get(i2));
					ITestResult result = Reporter.getCurrentTestResult();
					result.setAttribute("DontRetry", Boolean.TRUE.toString());
					Assert.fail();
				} else {
					Add_Log.info("Successfully displayed Netting Set ID " + views.get(i2));
					Reporter.log("Successfully displayed Netting Set ID " + views.get(i2));
				} 
				if(views.get(i2).equals(Verify2))
				{
					By eletext = By.xpath("//a[text()='" + Verify2 + "']");
					waitForElementPresent(driver, 90000, eletext, "Netting Set ID " + Verify2);
					click(driver, eletext, "Netting Set ID " + Verify2 );
					String parent=driver.getWindowHandle();
					Set<String> handles=driver.getWindowHandles();
					Iterator<String> I1=handles.iterator();
					while(I1.hasNext())
					{
						String child=I1.next();
						if(!parent.equals(child))
						{
							driver.switchTo().window(child);
						}
					}
					Thread.sleep(2000);
					waitForLoad(driver, 900);
					String URL=driver.getCurrentUrl();
					if(URL.contains("baselsftee"))
					{
						Add_Log.info("Successfully Redirected to SFT");
						Reporter.log("Successfully Redirected to SFT");
					} else {
						Add_Log.info("Unable to Redirected to SFT");
						Reporter.log("Unable to Redirected to SFT");
						TestResultStatus.mpaskeysnew.put(methodName(),
								"Unable to Redirected to SFT");
						Assert.fail();

					} 
				}
				else
				{
					Add_Log.info("Unable to displayed Netting Set ID  " + Verify2);
					Reporter.log("Unable to displayed Netting Set ID  " + Verify2);
				}
			}  
			
		}
        
		public void Transactionnettingseidclick1(WebDriver driver,String Verify2,String Verify)
				throws InterruptedException {
			String[] Verifysp = Verify.split("\\,");
			List<String> views = new ArrayList<>();
			waitForLoad(driver, 90);
			Thread.sleep(2000);
			WebElement extractxpathid = driver
					.findElement(By.xpath("//label[text()='Transaction']//following::span[text()='Netting Set ID']"));

			String pathnum = extractxpathid.getAttribute("id");
			String[] xpathid = pathnum.split("-");
			System.out.println(xpathid[0]);
			System.out.println(xpathid[1]);
			System.out.println(xpathid[2]);
			
		/*	int size = driver.findElements(By.xpath(
					"//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + " x-grid-cell-first']//div//a"))
					.size();
			System.out.println(size);
			int i;
			for (i = 1; i < size; i++) {
				views.add(getatt3(driver, By.xpath("(//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-"
						+ xpathid[1] + " x-grid-cell-first']//div//a)[" + i + "]"), " Netting Set ID # " + i));
			}
			for (int i2 = 0; i2 < views.size(); i2++) {
				if (views.get(i2).startsWith("*")) {
					Add_Log.info("Unable to displayed Netting Set ID  " + views.get(i2));
					Reporter.log("Unable to displayed Netting Set ID  " + views.get(i2));
					TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed Netting Set ID  " + views.get(i2));
					ITestResult result = Reporter.getCurrentTestResult();
					result.setAttribute("DontRetry", Boolean.TRUE.toString());
					Assert.fail();
				} else {
					Add_Log.info("Successfully displayed Netting Set ID " + views.get(i2));
					Reporter.log("Successfully displayed Netting Set ID " + views.get(i2));
				}   */
			
			String xpathdivtable = "(//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "')]//div)[1]";
			By filter = By.xpath(xpathdivtable);
			WebElement filterscenarioName = driver.findElement(filter);
			String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
			List<WebElement> options2 = driver.findElements(filter);
			for (WebElement ele : options2) {
				views.add(ele.getAttribute(("textContent")));
			}
			System.out.println(views);
			//System.out.println(views.get(1));
			Thread.sleep(2000);	
			try {
				String filterinput = null;
				if(Verifysp[1].equals("Netting Set"))
				{
				filterinput ="(//div[contains(@data-uipath,'sftUserPortalView/nettingSetElement/nettingSet')]//div[contains(@class,'x-container inline-filter')]//div[contains(@class,'filterCt ')])[2]//input";
				}
				if(Verifysp[1].equals("Transaction"))
				{
				filterinput ="(//div[contains(@data-uipath,'sftUserPortalView/sftUserTransactionElement')]//div[contains(@class,'x-container inline-filter')]//div[contains(@class,'filterCt ')])[1]//input";
				}
				WebElement tri=driver.findElement(By.xpath(filterinput));
				String byt=tri.getAttribute("id");
				System.out.println(byt);
				WebPageElements filterinput_dll = new WebPageElements("Sort/filiter", "xpath", filterinput);
				setTextenter(driver, filterinput_dll,views.get(0));
				Thread.sleep(2000);
				waitForLoad(driver, 100);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				
			}
		//	String SearchInputtable2 = "(//label[text()='"+Verifysp[1]+"']//following::tr)[2]";
		//	WebPageElements searchinputtable2  = new WebPageElements(" Search Input table 2nd row", "xpath",SearchInputtable2);
		//	waitForElementInvisibilty(driver, 120, searchinputtable2);
			//String textLabel = driver.findElement(By.xpath(SORTGETTEXT + "[" + Verify2 + "]")).getText();
			//System.out.println(textLabel);
			
			List<String> textlabel = new ArrayList<>();
			String xpathdivtable2 = "(//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "')]//div)[1]";
			By filter2 = By.xpath(xpathdivtable2);
			WebElement filterscenarioName2 = driver.findElement(filter2);
			String filterscenarioNamea2 = filterscenarioName2.getAttribute("textContent");
			List<WebElement> options3 = driver.findElements(filter2);
			for (WebElement ele3 : options3) {
				textlabel.add(ele3.getAttribute(("textContent")));
			}
			System.out.println(textlabel.toString().trim());
			//System.out.println(views.get(1));
				if (textlabel.toString().contains(views.get(0))) {
				Reporter.log("Successfully Sort Value");
				Add_Log.info("Successfully Sort Value");
			} else {
				Reporter.log("Unable to Sort value");
				Add_Log.info("Unable to Sort value");
				TestResultStatus.mpaskeysnew.put(methodName(), "Unable to Sort value");
				Assert.fail();
			}
			
				
					By eletext = By.xpath("//a[text()='" + views.get(0) + "']");
					waitForElementPresent(driver, 90000, eletext, "Netting Set ID " + views.get(0));
					click(driver, eletext, "Netting Set ID " + views.get(0) );
					String parent=driver.getWindowHandle();
					Set<String> handles=driver.getWindowHandles();
					Iterator<String> I1=handles.iterator();
					while(I1.hasNext())
					{
						String child=I1.next();
						if(!parent.equals(child))
						{
							driver.switchTo().window(child);
						}
					}
					Thread.sleep(2000);
					waitForLoad(driver, 900);
					String URL=driver.getCurrentUrl();
					if(URL.contains("baselsftee"))
					{
						Add_Log.info("Successfully Redirected to SFT");
						Reporter.log("Successfully Redirected to SFT");
					} else {
						Add_Log.info("Unable to Redirected to SFT");
						Reporter.log("Unable to Redirected to SFT");
						TestResultStatus.mpaskeysnew.put(methodName(),
								"Unable to Redirected to SFT");
						Assert.fail();

					} 
				
			}  
			
		

		// Verify table content for netting
		public void Nettingtablecontent(WebDriver driver, String Verify, String actualViews) throws InterruptedException {
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
		
		// Verify table content for Transaction
				public void Transactiontablecontent(WebDriver driver, String Verify, String actualViews) throws InterruptedException {
					String[] actualViewsp = actualViews.split("\\,");
					List<String> views2 = new ArrayList<>();
					// int size1 = driver.findElements(By.xpath(ExposureSUMMARYFIELD)).size();
					List<WebElement> options2 = driver.findElements(By.xpath(
							"(//div[contains(@class,'x-container inline-filter')])[1]/following::div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']"));
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
				
				
				// select  radio button for  Non-zero transactions
				public void NonzeroButton(WebDriver driver, String Verify) throws InterruptedException {
					String[] Verifysp = Verify.split("\\,");
					Thread.sleep(2000);
					By ele1 = By.xpath("//label[text()='" + Verifysp[1]
							+ "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-switch ']");
					waitForElementPresent(driver, 120, ele1, " Play run icon" + Verifysp[1]);
					click(driver, ele1, " Play run icon " + Verifysp[1]);
				}
				
				// displayed Transaction Result Exposure/Collateral/EAD value
				public void Transactionvalue(WebDriver driver, String Verify, String Report) throws InterruptedException {
					String[] Verifysp = Verify.split("\\,");
					String[] Reportsp = Report.split("\\,");
					List<String> views = new ArrayList<>();
					WebElement extractxpathid = driver.findElement(
							By.xpath("//label[text()='" + Verify + "']//following::span[text()='" + Report + "']"));
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
					for (i = 1; i <= size; i++) {
						views.add(getatt3(driver, By.xpath("(//td[@class='x-grid-cell x-grid-td x-grid-cell-numbercolumn-"
								+ xpathid[1] + " x-align-right']//div)[" + i + "]"), Report + "  Value # " + i));
					}
					for (int i2 = 0; i2 < views.size(); i2++)
						if (views.get(i2).startsWith("0")) {
							Add_Log.info("Unable to displayed "+ Report +" value " + views.get(i2));
							Reporter.log("Unable to displayed "+ Report +" value " + views.get(i));
							TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed JTD RWA value " + views.get(i2));
							ITestResult result = Reporter.getCurrentTestResult();
							result.setAttribute("DontRetry", Boolean.TRUE.toString());
							Assert.fail();
						} else {
							Add_Log.info("Successfully displayed "+ Report +" value " + views.get(i2));
							Reporter.log("Successfully displayed "+ Report +" value " + views.get(i2));
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
			System.out.println(fileName + " & " + Verifysp[0]);
			System.out.println(FileNameac + "************");
			if (fileName.contains(FileNameac)) {
				Add_Log.info("Successfully downloaded " + Fileformat + " report for " + Verifysp[0] + " " + ReportName
						+ " Report Time took to download " + totalTime + " sec");
				Reporter.log("Successfully downloaded " + Fileformat + " report for " + Verifysp[0] + " " + ReportName
						+ " Report Time took to download " + totalTime + " sec");
			} else {
				Add_Log.info(Fileformat + "if satatment ******report not downloaded for " + Verifysp[0] + " " + ReportName
						+ " report");
				Reporter.log(Fileformat + "if satatment  ******report not downloaded for " + Verifysp[0] + " " + ReportName
						+ " report");
				TestResultStatus.mpaskeysnew.put(methodName(), Fileformat + "if satatment  ******report not downloaded for "
						+ Verifysp[0] + " " + ReportName + " report");
			}

		}

		
		
		public void SortFilter(WebDriver driver, String viewName, String ReportName, String Verify2, String Verify3,
				String Verify, String Report, String Verify4, String reportingPeriod, String actualViews, String actualViews4) throws InterruptedException, IOException { 
			String[] Verifysp = Verify.split("\\,");
			String[] Verify2sp = Verify2.split("\\,");
			String[] Verify3sp = Verify3.split("\\,");
			String[] Reportsp = Report.split("\\,");
			String[] actualViewsp = actualViews.split("\\,");
			String[] actualViews4p = actualViews4.split("\\,");
			
			for(int i=0;i<Verifysp.length;i++)
			System.out.println(Verifysp[i]);
			List<String> views2 = new ArrayList<>();
			
			//Sortfliter
			List<String> views = new ArrayList<>();
			WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verifysp[1]+"']//following::span[text()='"+Reportsp[0]+"']"));
			String pathnum = extractxpathid.getAttribute("id");
			String[] xpathid = pathnum.split("-");
			System.out.println(xpathid[0]);
			System.out.println(xpathid[1]);
			System.out.println(xpathid[2]);

			String xpathdivtable = "(//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "')]//div)[1]";
			By filter = By.xpath(xpathdivtable);
			WebElement filterscenarioName = driver.findElement(filter);
			String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
			List<WebElement> options2 = driver.findElements(filter);
			for (WebElement ele : options2) {
				views.add(ele.getAttribute(("textContent")));
			}
			System.out.println(views);
			//System.out.println(views.get(1));
			Thread.sleep(2000);	
			try {
				String filterinput = null;
				if(Verifysp[1].equals("Netting Set"))
				{
				filterinput ="(//div[contains(@data-uipath,'sftUserPortalView/nettingSetElement/nettingSet')]//div[contains(@class,'x-container inline-filter')]//div[contains(@class,'filterCt ')])[2]//input";
				}
				if(Verifysp[1].equals("Transaction"))
				{
				filterinput ="(//div[contains(@data-uipath,'serPortalView/transactionElement/transactionGrid')]//div[contains(@class,'x-container inline-filter')]//div[contains(@class,'filterCt ')])[1]//input";
				}
				WebElement tri=driver.findElement(By.xpath(filterinput));
				String byt=tri.getAttribute("id");
				System.out.println(byt);
				WebPageElements filterinput_dll = new WebPageElements("Sort/filiter", "xpath", filterinput);
				setTextenter(driver, filterinput_dll,views.get(0));
				Thread.sleep(2000);
				waitForLoad(driver, 100);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				
			}
		//	String SearchInputtable2 = "(//label[text()='"+Verifysp[1]+"']//following::tr)[2]";
		//	WebPageElements searchinputtable2  = new WebPageElements(" Search Input table 2nd row", "xpath",SearchInputtable2);
		//	waitForElementInvisibilty(driver, 120, searchinputtable2);
			//String textLabel = driver.findElement(By.xpath(SORTGETTEXT + "[" + Verify2 + "]")).getText();
			//System.out.println(textLabel);
			
			List<String> textlabel = new ArrayList<>();
			String xpathdivtable2 = "(//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "')]//div)[1]";
			By filter2 = By.xpath(xpathdivtable2);
			WebElement filterscenarioName2 = driver.findElement(filter2);
			String filterscenarioNamea2 = filterscenarioName2.getAttribute("textContent");
			List<WebElement> options3 = driver.findElements(filter2);
			for (WebElement ele3 : options3) {
				textlabel.add(ele3.getAttribute(("textContent")));
			}
			System.out.println(textlabel.toString().trim());
			//System.out.println(views.get(1));
				if (textlabel.toString().contains(views.get(0))) {
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
			int size1 = driver.findElements(By.xpath(ExposureSUMMARYFIELD)).size();
			for (int i = 1; i <= size1; i++) {
				views2.add(getatt(driver, By.xpath("(" + ExposureSUMMARYFIELD + ")[" + i + "]"), Verifysp[1]+ " table Feild Content #" + i));	
			}
			System.out.println(views2);
		
		System.out.println(actualViews4p.length);
		for (int i1 = 0; i1 < actualViews4p.length; i1++) {

			if (views2.get(i1).equals(actualViews4p[i1].trim().toString()))
			{
				Add_Log.info("Successfully displayed " + views2.get(i1) + " under ");
				Reporter.log("Successfully displayed " + views2.get(i1) + " under ");
			} else {
				Add_Log.info("Unable to display "+ actualViews4p[i1].trim().toString() + " under ");
				Reporter.log("Unable to display "+ actualViews4p[i1].trim().toString() + " under ");
				TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display "+ actualViews4p[i1].trim().toString() + " under " );
				Assert.fail();
			}	
		}
		}
		
		
		
		
		
		
		
		
		
		
		/*String sortclick = "//label[text()='"+Verifysp[1]+"']//following::span[contains(@class,'filterswitch')]";
		By sort = By.xpath(sortclick);
		click(driver, sort,"by drilling down to " + Verifysp[1]+ " and click on Sort/Filters");
		Thread.sleep(2000);	*/
		
		
		
		
		
}

