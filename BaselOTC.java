package pageobjects;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Driver;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.google.common.base.CaseFormat;

import property.IHomePage;
import testsuitebase.TestResultStatus;
import utility.SeleniumUtils;
import utility.WebPageElements;

public class BaselOTC extends SeleniumUtils implements IHomePage {
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

	// click on Adhoc and navigate page
	public void Adhoc(WebDriver driver, String viewName, String Verify, String ReportName) throws InterruptedException {
		// click on Ad-hoc icon
		By ele1 = By.xpath("//*[contains(@class,'icon-reports ')]/ancestor::a");
		waitForElementPresent(driver, 120, ele1, " Ad-hoc icon  ");
		clickJS(driver, ele1, "Ad-hoc icon");
		Thread.sleep(2000);
		// mouse over Basel Exposure Explain
		By ele2 = By.xpath("//*[contains(text(),'Basel Exposure Explain')]");
		waitForElementPresent(driver, 120, ele2, "Basel Exposure Explain");
		moveToElement(driver, ele2, "Basel Exposure Explain");
		String ClickReport = "//*[text()='" + ReportName + "']";
		WebPageElements clickreport = new WebPageElements(ReportName, "xpath", ClickReport);
		waitForElementPresent(driver, 90, clickreport);
		click(driver, clickreport);
		Thread.sleep(5000);
		String parent = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> I1 = handles.iterator();
		while (I1.hasNext()) {
			String child = I1.next();
			if (!parent.equals(child)) {
				driver.switchTo().window(child);
			}
		}
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForElementPresent(driver, 120, perspectivecombbox);
	}

	/*
	 * public void OTCHeader1(WebDriver driver, String viewName, String Verify,
	 * String ReportName) throws InterruptedException { String[] viewNamesp =
	 * viewName.split("\\,");
	 * 
	 * waitForElementPresent(driver, 120, perspectivecombbox); Thread.sleep(1000);
	 * click(driver,perspectivecombbox); waitForElementPresent_noAs(driver, 120,
	 * usercombobox); click(driver,usercombobox); waitForLoad(driver, 900); String
	 * ClickReportnew
	 * ="//div[contains(@class,'x-panel x-tabpanel-child x-panel-default x-closable x-panel-closable x-panel-default-closable') and not(contains(@style,'display: none'))]//following::ul[@class='x-list-plain']/div[contains(text(),'Basel OTC Exposure Explain')and(contains(@style,'display: block'))]"
	 * ; WebPageElements clickreport2new = new WebPageElements (ReportName,
	 * "xpath",ClickReportnew); waitForElementPresent1(driver, 120,
	 * clickreport2new); click(driver,clickreport2new);
	 * 
	 * Thread.sleep(1000); try { By ele2 = By.
	 * xpath("//*[@class='x-btn tab-menu-button x-unselectable x-btn-default-small x-border-box']"
	 * ); WebElement borderbox=driver.findElement(ele2); if(borderbox.isDisplayed())
	 * { click(driver, ele2, "clicked borderbox"); } else {
	 * System.out.println("border box not present on page"); } } catch (Exception e)
	 * {
	 * 
	 * }
	 * 
	 * By ele4 = By.
	 * xpath("(//*[@class='x-tab x-unselectable x-box-item x-tab-primary-tabs x-top x-tab-top x-tab-primary-tabs-top x-tab-closable']/span/span/span[text()='"
	 * + viewName +"'])[1]"); WebElement element=driver.findElement(ele4);
	 * if(element.isDisplayed()) { Add_Log.info("Successfully displayed");
	 * waitForElementPresent(driver, 120, ele4,viewName ); Thread.sleep(2000);
	 * click(driver, ele4, viewName); waitForLoad(driver, 90); } else {
	 * click(driver,plus); List<WebElement> domainlist = driver.findElements(By.
	 * xpath("//*[@class='x-box-inner x-box-scroller-body-vertical']//div[@class='x-menu-item x-menu-item-default x-box-item']//span"
	 * )); int totaldomains=domainlist.size(); for(int i=3;i<=totaldomains;i++) {
	 * 
	 * String element1=driver.findElement(By.
	 * xpath("//*[@class='x-box-inner x-box-scroller-body-vertical']//div[@class='x-menu-item x-menu-item-default x-box-item']["
	 * + i +"]//span")).getText(); System.out.println(element1);
	 * if(viewName.equals(element1)) { try { By ele3= By.xpath("//*[text()='"+
	 * element1 +"']/parent::a"); WebElement domain=driver.findElement(ele3);
	 * if(domain.isDisplayed()) { Add_Log.info("Successfully displayed " +
	 * element1); Reporter.log("Successfully displayed " + element1);
	 * waitForElementPresent(driver, 120, ele3, element1); click(driver, ele3,
	 * viewName); waitForLoad(driver, 200); } else {
	 * Add_Log.info("Unable to display " + element1);
	 * Reporter.log("Unable to display " + element1);
	 * 
	 * } } catch (java.lang.Exception e) {
	 * 
	 * e.printStackTrace(); } } } } By ele5= By.xpath("//*[contains(text(),'"+
	 * viewName +"')]/ancestor::a[@data-qtip='"+ viewName
	 * +"']/following-sibling::div/div/div/a[3]"); waitForElementPresentJS(driver,
	 * 2000, ele5, "Next page of "+viewName); //waitForElementPresent(driver, 120,
	 * nextpage);
	 * 
	 * }
	 */

	public void OTCHeader1(WebDriver driver, String viewName, String Verify, String ReportName)
			throws InterruptedException {
		String[] viewNamesp = viewName.split("\\,");
		// waitForLoad(driver, 900);

		waitForElementPresent(driver, 120, perspectivecombbox);
		Thread.sleep(1000);
		click(driver, perspectivecombbox);

		waitForElementPresent_noAs(driver, 120, usercombobox);
		click(driver, usercombobox);
		waitForLoad(driver, 900);
		String ClickReportnew = "//div[contains(@class,'x-panel x-tabpanel-child x-panel-default x-closable x-panel-closable x-panel-default-closable') and not(contains(@style,'display: none'))]//following::ul[@class='x-list-plain']/div[contains(text(),'"
				+ ReportName + "')and(contains(@style,'display: block'))]";

		WebPageElements clickreport2new = new WebPageElements(ReportName, "xpath", ClickReportnew);
		waitForElementPresent1(driver, 120, clickreport2new);
		click(driver, clickreport2new);

		Thread.sleep(1000);
		try {
			By ele2 = By.xpath("//*[@class='x-btn tab-menu-button x-unselectable x-btn-default-small x-border-box']");
			WebElement borderbox = driver.findElement(ele2);
			if (borderbox.isDisplayed()) {
				click(driver, ele2, "clicked borderbox");
				By ele3 = By.xpath("(//*[text()='" + viewName + "']/parent::a)[1]");
				waitForElementPresent(driver, 120, ele3, viewName);
				click(driver, ele3, viewName);
			} else {
				System.out.println("border box not present on page");
			}
		} catch (Exception e3) {

			By ele4 = By.xpath(
					"(//*[@class='x-tab x-unselectable x-box-item x-tab-primary-tabs x-top x-tab-top x-tab-primary-tabs-top x-tab-closable']/span/span/span[text()='"
							+ viewName + "'])[1]");
			WebElement element = driver.findElement(ele4);
			if (element.isDisplayed()) {
				Add_Log.info("Successfully displayed");
				waitForElementPresent(driver, 120, ele4, viewName);
				Thread.sleep(2000);
				click(driver, ele4, viewName);
				waitForLoad(driver, 90);
			} else {
				click(driver, plus);
				By ele3 = By.xpath("(//*[text()='" + viewName + "']/parent::a)[1]");
				waitForElementPresent(driver, 120, ele3, viewName);
				click(driver, ele3, viewName);

			}
			waitForLoad(driver, 9000);
			// By ele3= By.xpath("//*[contains(text(),'"+ viewName
			// +"')]/ancestor::a[@data-qtip='"+ viewName
			// +"']/following-sibling::div/div/div/a[3]");
			// waitForElementPresent(driver, 120, ele3, "Next page of "+viewName);
			// waitForElementPresent(driver, 120, nextpage);

		}
	}

	public void prespective_domain(WebDriver driver, String ReportName) throws InterruptedException {
		waitForElementPresent(driver, 120, perspectivecombbox);
		Thread.sleep(1000);
		click(driver, perspectivecombbox);

		waitForElementPresent_noAs(driver, 120, usercombobox);
		click(driver, usercombobox);
		waitForLoad(driver, 900);
		String ClickReportnew = "//div[contains(@class,'x-panel x-tabpanel-child x-panel-default x-closable x-panel-closable x-panel-default-closable') and not(contains(@style,'display: none'))]//following::ul[@class='x-list-plain']/div[contains(text(),'"
				+ ReportName + "')and(contains(@style,'display: block'))]";

		WebPageElements clickreport2new = new WebPageElements(ReportName, "xpath", ClickReportnew);
		waitForElementPresent1(driver, 120, clickreport2new);
		click(driver, clickreport2new);
		waitForLoad(driver, 120);
		Thread.sleep(2000);
	}

	public void domain_selection(WebDriver driver, String viewName) throws InterruptedException {
		try {
			By ele2 = By.xpath("//*[@class='x-btn tab-menu-button x-unselectable x-btn-default-small x-border-box']");
			WebElement borderbox = driver.findElement(ele2);
			if (borderbox.isDisplayed()) {
				click(driver, ele2, "clicked borderbox");
				By ele3 = By.xpath("(//*[text()='" + viewName + "']/parent::a)[1]");
				waitForElementPresent(driver, 120, ele3, viewName);
				click(driver, ele3, viewName);
			} else {
				System.out.println("border box not present on page");
			}
		} catch (Exception e3) {
			By ele4 = By.xpath(
					"(//*[@class='x-tab x-unselectable x-box-item x-tab-primary-tabs x-top x-tab-top x-tab-primary-tabs-top x-tab-closable']/span/span/span[text()='"
							+ viewName + "'])[1]");
			WebElement element = driver.findElement(ele4);
			if (element.isDisplayed()) {
				Add_Log.info("Successfully displayed");
				waitForElementPresent(driver, 120, ele4, viewName);
				Thread.sleep(2000);
				click(driver, ele4, viewName);
				waitForLoad(driver, 90);
			} else {
				click(driver, plus);
				By ele3 = By.xpath("(//*[text()='" + viewName + "']/parent::a)[1]");
				waitForElementPresent(driver, 120, ele3, viewName);
				click(driver, ele3, viewName);

			}
			waitForLoad(driver, 9000);
			// By ele3= By.xpath("//*[contains(text(),'"+ viewName
			// +"')]/ancestor::a[@data-qtip='"+ viewName
			// +"']/following-sibling::div/div/div/a[3]");
			// waitForElementPresent(driver, 120, ele3, "Next page of "+viewName);
			// waitForElementPresent(driver, 120, nextpage);
		}
	}

	public void hiddendomain_selection(WebDriver driver, String viewName) throws InterruptedException {

		try {
			click(driver, plus);
		} catch (Exception e) {

		}
		By ele3 = By.xpath("(//*[text()='" + viewName + "']/parent::a)");
		waitForElementPresent(driver, 120, ele3, viewName);
		click(driver, ele3, viewName);
	}

	public void portfoliocalculate(WebDriver driver, String viewName, String Verify, String Verify3, String ReportName)
			throws InterruptedException {

		// page redirecting to Baselview
		Adhoc(driver, viewName, Verify, ReportName);
		// verify and click header
		OTCHeader1(driver, viewName, Verify, ReportName);
		// select portfolioid
		selectorinalportfolioID(driver, viewName, Verify3, ReportName);
		// click on calculator icon
		calculatoricon(driver, Verify);
	}

	public void portfoliochart(WebDriver driver, String viewName, String Verify, String Verify3, String ReportName)
			throws InterruptedException {

		try {
			// page redirecting to Baselview
			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			// verify and click header
			OTCHeader1(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
		} catch (Exception e) {
			// page redirecting to Baselview
			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			// verify and click header
			OTCHeader1(driver, viewName, Verify, ReportName);
		}
		// select portfolioid
		selectorinalportfolioID(driver, viewName, Verify3, ReportName);
		Thread.sleep(2000);
		// click on chart icon
		Charticon(driver, Verify3, Verify);
	}

	public void portfoliodownload(WebDriver driver, String viewName, String Verify, String Verify3, String ReportName,
			String Fileformat, String FileNameac) throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		try {
			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			OTCHeader1(driver, viewName, Verify, ReportName);
		} catch (Exception e) {
			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			OTCHeader1(driver, viewName, Verify, ReportName);
		}
		Thread.sleep(2000);
		selectorinalportfolioID(driver, viewName, Verify3, ReportName);
		Thread.sleep(2000);
		Downloadicon(driver, Verify3);
		Thread.sleep(2000);
		selectTop(driver, Verify3);
		if (Fileformat.equals("Excel")) {
			downloadexcel(driver, Verify3);
		} else {
			downloadcsv(driver, Verify3);
		}
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
		Downloadicon(driver, Verify3);
		Thread.sleep(2000);
		selectcurrentpage(driver, Verify3);
		if (Fileformat.equals("Excel")) {
			downloadexcel(driver, Verify3);
		} else {
			downloadcsv(driver, Verify3);
		}
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
		Downloadicon(driver, Verify3);
		Thread.sleep(2000);
		selectAllrecords(driver, Verify3);
		if (Fileformat.equals("Excel")) {
			downloadexcel(driver, Verify3);
		} else {
			downloadcsv(driver, Verify3);
		}
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
		Downloadicon(driver, Verify3);
		Thread.sleep(2000);
		selectCSVdelimiter(driver, Verify3);
		if (Fileformat.equals("Excel")) {
			downloadexcel(driver, Verify3);
		} else {
			downloadcsv(driver, Verify3);
		}
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
	}

	public void portfoliofilter(WebDriver driver, String viewName, String Verify, String Verify3, String Verify4,
			String ReportName) throws InterruptedException {
		try {

			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			OTCHeader1(driver, viewName, Verify, ReportName);
		} catch (Exception e) {
			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			OTCHeader1(driver, viewName, Verify, ReportName);
		}
		Thread.sleep(2000);
		selectorinalportfolioID(driver, viewName, Verify3, ReportName);
		Thread.sleep(2000);
		Filtericon(driver, Verify3);
		Thread.sleep(2000);
		Filteredit(driver, Verify4);
		Thread.sleep(2000);
		EditScenariopop(driver, viewName, Verify4, ReportName);
	}

	public void pricingstrategycalculate(WebDriver driver, String viewName, String Verify, String Verify3,
			String ReportName) throws InterruptedException {
		try {

			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			OTCHeader1(driver, viewName, Verify, ReportName);
		} catch (Exception e) {
			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			OTCHeader1(driver, viewName, Verify, ReportName);
		}
		Thread.sleep(2000);
		selectpricingstrategy(driver, viewName, Verify3, ReportName);
		Thread.sleep(2000);
		calculatoricon(driver, Verify);
	}

	public void pricingstrategychart(WebDriver driver, String viewName, String Verify, String Verify3,
			String ReportName) throws InterruptedException {
		try {

			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			OTCHeader1(driver, viewName, Verify, ReportName);
		} catch (Exception e) {
			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			OTCHeader1(driver, viewName, Verify, ReportName);
		}
		Thread.sleep(2000);
		selectpricingstrategy(driver, viewName, Verify3, ReportName);
		Thread.sleep(2000);
		Charticon(driver, Verify3, Verify);
	}

	public void pricingstrategyfilter(WebDriver driver, String viewName, String Verify, String Verify3, String Verify4,
			String ReportName) throws InterruptedException {
		try {

			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			OTCHeader1(driver, viewName, Verify, ReportName);

		} catch (Exception e) {
			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			OTCHeader1(driver, viewName, Verify, ReportName);
		}
		Thread.sleep(2000);
		selectpricingstrategy(driver, viewName, Verify3, ReportName);
		Thread.sleep(2000);
		Filtericon(driver, Verify3);
		Thread.sleep(2000);
		Filteredit(driver, Verify4);
		Thread.sleep(2000);
		EditScenariopop(driver, viewName, Verify4, ReportName);
	}

	public void pricingstrategydownload(WebDriver driver, String viewName, String Verify, String Verify3,
			String ReportName, String Fileformat, String FileNameac) throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		try {

			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			OTCHeader1(driver, viewName, Verify, ReportName);
		} catch (Exception e) {
			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			OTCHeader1(driver, viewName, Verify, ReportName);
		}
		Thread.sleep(2000);
		selectpricingstrategy(driver, viewName, Verify3, ReportName);
		Thread.sleep(2000);
		Downloadicon(driver, Verify3);
		Thread.sleep(2000);
		selectTop(driver, Verify3);
		if (Fileformat.equals("Excel")) {
			downloadexcel(driver, Verify3);
		} else {
			downloadcsv(driver, Verify3);
		}
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
		Downloadicon(driver, Verify3);
		Thread.sleep(2000);
		selectcurrentpage(driver, Verify3);
		if (Fileformat.equals("Excel")) {
			downloadexcel(driver, Verify3);
		} else {
			downloadcsv(driver, Verify3);
		}
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
		Downloadicon(driver, Verify3);
		Thread.sleep(2000);
		selectAllrecords(driver, Verify3);
		if (Fileformat.equals("Excel")) {
			downloadexcel(driver, Verify3);
			message(driver, Verify);
		} else {
			downloadcsv(driver, Verify3);
		}
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
		Downloadicon(driver, Verify3);
		Thread.sleep(2000);
		selectCSVdelimiter(driver, Verify3);
		if (Fileformat.equals("Excel")) {
			downloadexcel(driver, Verify3);
		} else {
			downloadcsv(driver, Verify3);
		}
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);

	}

	public void StressTypecalculate(WebDriver driver, String viewName, String Verify, String Verify3, String ReportName)
			throws InterruptedException {
		try {

			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			OTCHeader1(driver, viewName, Verify, ReportName);
		} catch (Exception e) {
			Adhoc(driver, viewName, Verify, ReportName);
			Thread.sleep(2000);
			OTCHeader1(driver, viewName, Verify, ReportName);
		}
		Thread.sleep(2000);
		selectStressType(driver, viewName, Verify3, ReportName);
		Thread.sleep(2000);
		calculatoricon(driver, Verify);
	}

	public void StressTypedownload(WebDriver driver, String viewName, String Verify, String Verify3, String ReportName,
			String Fileformat, String FileNameac) throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");

		Adhoc(driver, viewName, Verify, ReportName);
		Thread.sleep(2000);
		try {
			OTCHeader1(driver, viewName, Verify, ReportName);
		} catch (Exception e) {
			OTCHeader1(driver, viewName, Verify, ReportName);
		}
		Thread.sleep(2000);
		selectStressType(driver, viewName, Verify3, ReportName);
		Thread.sleep(2000);
		Downloadicon(driver, Verify3);
		Thread.sleep(2000);
		selectTop(driver, Verify3);
		if (Fileformat.equals("Excel")) {
			downloadexcel(driver, Verify3);
		} else {
			downloadcsv(driver, Verify3);
		}
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
		Downloadicon(driver, Verify3);
		Thread.sleep(2000);
		selectcurrentpage(driver, Verify3);
		if (Fileformat.equals("Excel")) {
			downloadexcel(driver, Verify3);

		} else {
			downloadcsv(driver, Verify3);
		}
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
		Downloadicon(driver, Verify3);
		Thread.sleep(2000);
		selectAllrecords(driver, Verify3);
		if (Fileformat.equals("Excel")) {
			downloadexcel(driver, Verify3);
			message(driver, Verify);
		} else {
			downloadcsv(driver, Verify3);
		}
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);
		Downloadicon(driver, Verify3);
		Thread.sleep(2000);
		selectCSVdelimiter(driver, Verify3);
		if (Fileformat.equals("Excel")) {
			downloadexcel(driver, Verify3);
		} else {
			downloadcsv(driver, Verify3);
		}
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);

	}

	public void StressTypechart(WebDriver driver, String viewName, String Verify, String Verify3, String ReportName)
			throws InterruptedException {

		Adhoc(driver, viewName, Verify, ReportName);
		Thread.sleep(2000);
		OTCHeader1(driver, viewName, Verify, ReportName);
		Thread.sleep(2000);
		selectStressType(driver, viewName, Verify3, ReportName);
		Thread.sleep(2000);
		Charticon(driver, Verify3, Verify);
	}

	public void StressTypefilter(WebDriver driver, String viewName, String Verify, String Verify3, String Verify4,
			String ReportName) throws InterruptedException {

		Adhoc(driver, viewName, Verify, ReportName);
		Thread.sleep(2000);
		OTCHeader1(driver, viewName, Verify, ReportName);
		Thread.sleep(2000);
		selectStressType(driver, viewName, Verify3, ReportName);
		Thread.sleep(2000);
		Filtericon(driver, Verify3);
		Thread.sleep(2000);
		Filteredit(driver, Verify4);
		Thread.sleep(2000);
		EditScenariopop(driver, viewName, Verify4, ReportName);
	}

	public void multicolumn(WebDriver driver, String viewName, String Verify, String Verify3, String Verify4,
			String ReportName) throws InterruptedException {

		Adhoc(driver, viewName, Verify, ReportName);
		Thread.sleep(2000);
		OTCHeader1(driver, viewName, Verify, ReportName);
		Thread.sleep(2000);
		multisorting(driver, Verify, Verify3);
	}

	public void globalfilter(WebDriver driver, String viewName, String Verify, String Verify3, String Verify4,
			String ReportName, String actualViews) throws InterruptedException {
		Adhoc(driver, viewName, Verify, ReportName);
		OTCHeader1(driver, viewName, Verify, ReportName);
		headerclick(driver, actualViews);
		selectfunction(driver, actualViews);
		AddScenariopop(driver, Verify3);
		filterverify(driver);
	}

	public void Locatetree(WebDriver driver, String viewName, String Verify, String Verify3, String Verify4,
			String ReportName, String actualViews) throws InterruptedException {
		Adhoc(driver, viewName, Verify, ReportName);
		OTCHeader1(driver, viewName, Verify, ReportName);
		headerclick(driver, actualViews);
		selectfunction(driver, actualViews);
		Locateverify(driver);
	}

	public void Ascending(WebDriver driver, String viewName, String Verify, String Verify3, String Verify4,
			String ReportName, String actualViews) throws InterruptedException {
		Adhoc(driver, viewName, Verify, ReportName);
		OTCHeader1(driver, viewName, Verify, ReportName);
		headerclick(driver, actualViews);
		selectfunction(driver, actualViews);
	}

	public void Descending(WebDriver driver, String viewName, String Verify, String Verify3, String Verify4,
			String ReportName, String actualViews) throws InterruptedException {
		Adhoc(driver, viewName, Verify, ReportName);
		OTCHeader1(driver, viewName, Verify, ReportName);
		headerclick(driver, actualViews);
		selectfunction(driver, actualViews);
	}

	public void data_check(WebDriver driver, String viewName, String Verify, String Verify2, String Verify3,
			String ReportName, String actualViews, String actualViews2) throws InterruptedException {
		String[] Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] actualViews2sp = actualViews2.split("\\,");
		Adhoc(driver, viewName, Verify, ReportName);
		OTCHeader1(driver, viewName, Verify, ReportName);
		ClickAngledown_header(driver, Verify2);
		dropdown(driver, actualViews, actualViews2sp[0]);
		AddScenariopop(driver, Verify3);
		verify_tabledata(driver, Verify2, Verify3);
		Clickdelete_icon(driver, Verify2);
		ClickAngledown_header(driver, Verify2);
		dropdown(driver, actualViews, actualViews2sp[1]);
		AddScenariopop(driver, Verify3);
		verify_tabledata(driver, Verify2, Verify3);
		Clickdelete_icon(driver, Verify2);
		ClickAngledown_header(driver, Verify2);
		dropdown(driver, actualViews, actualViews2sp[2]);
		AddScenariopop(driver, Verify3);
		verify_tabledata(driver, Verify2, Verify3);
		Clickdelete_icon(driver, Verify2);
		ClickAngledown_header(driver, Verify2);
		dropdown(driver, actualViews, actualViews2sp[3]);
		AddScenariopop(driver, Verify3);
		verify_tabledata(driver, Verify2, Verify3);
		Clickdelete_icon(driver, Verify2);
		ClickAngledown_header(driver, Verify2);
		dropdown(driver, actualViews, actualViews2sp[4]);
		AddScenariopop(driver, Verify3);
		verify_tabledata(driver, Verify2, Verify3);
		Clickdelete_icon(driver, Verify2);
		ClickAngledown_header(driver, Verify2);
		dropdown(driver, actualViews, actualViews2sp[5]);
		AddScenariopop(driver, Verify3);
		verify_tabledata(driver, Verify2, Verify3);
		Clickdelete_icon(driver, Verify2);
		ClickAngledown_header(driver, Verify2);
		dropdown(driver, actualViews, actualViews2sp[6]);
		AddScenariopop(driver, Verify3);
		verify_tabledata(driver, Verify2, Verify3);
		Clickdelete_icon(driver, Verify2);
		ClickAngledown_header(driver, Verify2);
		dropdown(driver, actualViews, actualViews2sp[7]);
		AddScenariopopIN(driver, Verify3);
		verify_tabledata(driver, Verify2, Verify3);
		Clickdelete_icon(driver, Verify2);
		ClickAngledown_header(driver, Verify2);
		dropdown(driver, actualViews, actualViews2sp[8]);
		AddScenariopopIN(driver, Verify3);
		verify_tabledata(driver, Verify2, Verify3);
		Clickdelete_icon(driver, Verify2);
	}

	public void numaricdata_check(WebDriver driver, String viewName, String Verify, String Verify2, String Verify3,
			String ReportName, String actualViews, String actualViews2) throws InterruptedException {
		String[] Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] actualViews2sp = actualViews2.split("\\,");
		Adhoc(driver, viewName, Verify, ReportName);
		OTCHeader1(driver, viewName, Verify, ReportName);
		ClickAngledown_header(driver, Verify2);
		dropdown(driver, actualViews, actualViews2sp[0]);
		AddNumaricpop(driver, Verify3, actualViews2);
		// verify_tabledata(driver, Verify2, Verify3);
		Clickdelete_icon(driver, Verify2);
		ClickAngledown_header(driver, Verify2);
		dropdown(driver, actualViews, actualViews2sp[6]);
		AddNumaricpop(driver, Verify3, actualViews2);
		// verify_tabledata(driver, Verify2, Verify3);
		Clickdelete_icon(driver, Verify2);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void removefilter(WebDriver driver, String Filter) throws InterruptedException {

		By filter = By.xpath("//*[contains(text(),'" + Filter + "')]/parent::div/child::span[@class='icon-delete']");
		WebElement remove = driver.findElement(filter);
		waitForElementPresent(driver, 120, filter, Filter);
		click(driver, filter, Filter);

	}

	public void dropdown(WebDriver driver, String actualViews, String actualViews2) throws InterruptedException {

		System.out.println(actualViews2);
		String operation_dd = "//*[@name='" + actualViews + "']";
		WebElement op_dropdown = driver.findElement(By.xpath(operation_dd));
		By aggreationfilterid = (By.xpath(operation_dd));
		WebDriverWait wait = new WebDriverWait(driver, 120);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(op_dropdown));
		click(driver, aggreationfilterid, actualViews);
		Thread.sleep(1000);
		waitForLoad(driver, 120);
		By ddvalue = By.xpath("//li[text()='" + actualViews2 + "']");
		waitForElementPresent(driver, 120, ddvalue, actualViews2);
		click(driver, ddvalue, actualViews2 + " value in " + actualViews);
		Thread.sleep(2000);
		WebElement dd_text = driver.findElement(By.xpath("//*[@name='" + actualViews + "']"));
		String dd_textresult = dd_text.getAttribute("value");
		if (dd_textresult.equals(actualViews2)) {
			Reporter.log("Successfully filter " + actualViews + " and select value " + actualViews2);
			Add_Log.info("Successfully filter " + actualViews + " and select value " + actualViews2);
		} else {
			Reporter.log("No filter " + actualViews + " and select value " + actualViews2);
			Add_Log.info("No filter " + actualViews + " and select value " + actualViews2);
		}
	}

	public void ClickAngledown_header(WebDriver driver, String Verify2) throws InterruptedException {
		String[] Verify2sp = Verify2.split("\\,");
		try {
			// click on original portfolio id angle down
			// By ele5 = By.xpath("(//*[text()='"+ Verify2
			// +"']/ancestor::div[contains(@class,'inlinefilter')]/following-sibling::div/child::div/div/div[contains(@id,'trigger-picker')])");
			By ele5 = By.xpath(
					"//*[text()='Basel Exposure Breakdown']/ancestor::div[@data-ref='titleEl']/following-sibling::div/div[@data-ref='targetEl']//span[text()='"
							+ Verify2
							+ "']/ancestor::div[contains(@class,'inlinefilter')]/following-sibling::div/child::div/div/div[contains(@id,'trigger-picker')]");
			waitForElementPresent(driver, 120, ele5, "  Angle Down  " + Verify2);
			click(driver, ele5, "Angle Down" + Verify2);
		} catch (Exception e) {

		}
	}

	public void Clickdelete_icon(WebDriver driver, String Verify2) {
		String[] Verify2sp = Verify2.split("\\,");
		try {
			// click on original portfolio id delete icon
			// By ele5 = By.xpath("//*[text()='"+ Verify2
			// +"']/ancestor::div[contains(@class,'inlinefilter')]/following-sibling::div/child::div/div/div[contains(@class,'delete
			// ')]");
			By ele5 = By.xpath(
					"//*[text()='Basel Exposure Breakdown']/ancestor::div[@data-ref='titleEl']/following-sibling::div/div[@data-ref='targetEl']//span[text()='"
							+ Verify2
							+ "']/ancestor::div[contains(@class,'inlinefilter')]/following-sibling::div/child::div/div/div[contains(@class,'delete ')]");
			// waitForElementPresent(driver, 120, ele5, " delete icon "+ Verify2);
			moveToElement(driver, ele5, "delete icon" + Verify2);
			click(driver, ele5, "delete icon" + Verify2);
			waitForLoad(driver, 900);
		} catch (Exception e) {

		}
	}

	public void verify_tabledata(WebDriver driver, String Verify2, String Verify3) {
		String table = null;
		if (Verify2.equals("Original Portfolio ID") || Verify2.equals("Pricing Strategy")
				|| Verify2.equals("Stress Type")) {
			table = "(//*[text()='" + Verify2
					+ "']/ancestor::div[contains(@class,'x-grid-header-ct')]/following-sibling::div[contains(@class,'body')]/div/div[contains(@class,'container')])/table";
		}
		if (Verify2.equals("Business Unit")) {
			table = "(//*[text()='" + Verify2
					+ "']/ancestor::div[contains(@class,'x-grid-header-ct')]/following-sibling::div[contains(@class,'body')]/div/div[contains(@class,'container')])[2]/table";
		}
		List<WebElement> element = driver.findElements(By.xpath(table));
		int count = element.size();
		System.out.println(count);

		for (int i = 1; i <= count; i++) {
			String column = null;
			if (Verify2.equals("Original Portfolio ID")) {
				column = "//*[text()='" + Verify2
						+ "']/ancestor::div[contains(@class,'x-grid-header-ct')]/following-sibling::div[contains(@class,'body')]/div/div[contains(@class,'container')]/table["
						+ i + "]/tbody/tr/td[1]/div";
			}
			if (Verify2.equals("Pricing Strategy")) {
				column = "(//*[text()='" + Verify2
						+ "']/ancestor::div[contains(@class,'x-grid-header-ct')]/following-sibling::div[contains(@class,'body')]/div/div[contains(@class,'container')])/table["
						+ i + "]/tbody/tr/td[2]/div";
			}
			if (Verify2.equals("Stress Type")) {
				column = "(//*[text()='" + Verify2
						+ "']/ancestor::div[contains(@class,'x-grid-header-ct')]/following-sibling::div[contains(@class,'body')]/div/div[contains(@class,'container')])/table["
						+ i + "]/tbody/tr/td[3]/div";
			}

			if (Verify2.equals("Business Unit")) {
				column = "(//*[text()='" + Verify2
						+ "']/ancestor::div[contains(@class,'x-grid-header-ct')]/following-sibling::div[contains(@class,'body')]/div/div[contains(@class,'container')])[2]/table["
						+ i + "]/tbody/tr/td[4]/div";
			}

			WebElement id = driver.findElement(By.xpath(column));
			String value = id.getText();
			if (Verify3.contains(value)) {
				Reporter.log("Successfully filter " + Verify2 + " and select value " + Verify3);
				Add_Log.info("Successfully filter " + Verify2 + " and select value " + Verify3);
			} else {
				Reporter.log("Not present on table  " + Verify2 + " and select value " + Verify3);
				Add_Log.info("Not present on table  " + Verify2 + " and select value " + Verify3);
			}
		}
	}

	public void AddNumaricpop(WebDriver driver, String Verify3, String actualViews2) {
		String[] Verify3sp = Verify3.split("\\,");
		String[] actualViews2sp = actualViews2.split("\\,");

		if (actualViews2sp[0].equals("Equals") || actualViews2sp[1].equals("Not Equals")
				|| actualViews2sp[2].equals("Less Than") || actualViews2sp[3].equals("Less Than Equal To")
				|| actualViews2sp[4].equals("Greater Than") || actualViews2sp[5].equals("Greater Than Equal To")) {
			click(driver, numarictextbox);
			setText(driver, numarictextbox, Verify3sp[0]);
		}
		if (actualViews2sp[6].equals("Between")) {
			click(driver, numarictextbox);
			setText(driver, numarictextbox, Verify3sp[0]);
			click(driver, betweentextbox);
			setText(driver, betweentextbox, Verify3sp[1]);
		}
		waitForLoad(driver, 90);
		click(driver, applybtn);
	}

	public void AddScenariopopIN(WebDriver driver, String Verify3) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		waitForElementPresent(driver, 120, textboxin);
		click(driver, textboxin);
		setText(driver, textboxin, Verify3sp[0]);
		click(driver, operationplusicon);
		click(driver, textboxin);
		setText(driver, textboxin, Verify3sp[0]);
		click(driver, operationplusicon);
		waitForLoad(driver, 90);
		click(driver, applybtn);
		waitForLoad(driver, 90);
	}

	public void selectorinalportfolioID(WebDriver driver, String viewName, String Verify3, String ReportName)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		try {

			// click on original portfolio id angle down
			By ele5 = By.xpath(
					"//*[text()='Original Portfolio ID']/ancestor::div[contains(@class,'inlinefilter')]/following-sibling::div/child::div/div/div[contains(@id,'trigger-picker')]");
			waitForElementPresent(driver, 120, ele5, "  Angle Down  ");
			moveToElement(driver, ele5, "Angle Down");
			click(driver, ele5, "Angle Down");

			AddScenariopop(driver, Verify3);
		} catch (Exception e) {

		}
	}

	public void selectpricingstrategy(WebDriver driver, String viewName, String Verify3, String ReportName)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		try {
			// click on pricingstrategy angle down
			By ele5 = By.xpath("(//span[text()='" + viewName
					+ "']/ancestor::div[contains(@class,'iemPgToolBar')]/following::div[contains(@class,'filterCt')]//div[contains(@class,'icon-angle-down')])[2]");
			waitForElementPresent(driver, 120, ele5, "  Angle Down  ");
			moveToElement(driver, ele5, "Angle Down");
			click(driver, ele5, "Angle Down");
			AddScenariopop(driver, Verify3);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void selectStressType(WebDriver driver, String viewName, String Verify3, String ReportName)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		try {
			// click on StressType angle down
			By ele5 = By.xpath("(//span[text()='" + viewName
					+ "']/ancestor::div[contains(@class,'iemPgToolBar')]/following::div[contains(@class,'filterCt')]//div[contains(@class,'icon-angle-down')])[3]");
			waitForElementPresent(driver, 120, ele5, "  Angle Down  ");
			moveToElement(driver, ele5, "Angle Down");
			click(driver, ele5, "Angle Down");
			AddScenariopop(driver, Verify3);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void AddScenariopop(WebDriver driver, String Verify3) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		try {
			if (driver.findElement(By.xpath(TEXTBOX)).isDisplayed()) {
				// wait for text box present
				// waitForElementPresent(driver, 120, textbox);
				// click on text box
				click(driver, textbox);
				Thread.sleep(2000);
				// set text on text box
				setText(driver, textbox, Verify3sp[0]);
				Thread.sleep(2000);
				waitForLoad(driver, 90);
				waitForElementPresent(driver, 120, applybtn);
				// click on Apply button
				click(driver, applybtn);
				waitForLoad(driver, 90);
			} else {
				// click on Apply button
				click(driver, applybtn);
				waitForLoad(driver, 90);
			}
		} catch (Exception e) {

		}

	}

	public void EditScenariopop(WebDriver driver, String viewName, String Verify4, String ReportName)
			throws InterruptedException {
		String[] Verify4sp = Verify4.split("\\,");
		waitForElementPresent(driver, 120, textbox);
		click(driver, textbox);
		Thread.sleep(2000);
		clearText(driver, textbox);
		Thread.sleep(2000);
		setText(driver, textbox, Verify4sp[0]);
		Thread.sleep(2000);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, applybtn);
		click(driver, applybtn);
		waitForLoad(driver, 90);
	}

	public void selectstresstype(WebDriver driver, String viewName, String Verify3, String ReportName)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");

		By ele5 = By.xpath("(//span[text()='" + viewName
				+ "']/ancestor::div[contains(@class,'iemPgToolBar')]/following::div[contains(@class,'filterCt')]//div[contains(@class,'icon-angle-down')])[3]");
		waitForElementPresent(driver, 120, ele5, "  Angle Down  ");
		moveToElement(driver, ele5, "Angle Down");
		click(driver, ele5, "Angle Down");
		// set text on stresstype window
		AddScenariopop(driver, Verify3);
	}

	public void calculatoricon(WebDriver driver, String Verify) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		// click on calculator icon
		By ele1 = By.xpath(
				"//*[contains(text(),'Exposure Breakdown')]/ancestor::a[contains(@data-qtip,'Exposure Breakdown')]/following-sibling::a[contains(@data-qtip,'Total')]");
		waitForElementPresent(driver, 120, ele1, " calulator icon for " + Verifysp[0]);
		click(driver, ele1, Verifysp[0] + " calulator icon");
		Thread.sleep(2000);
		waitForLoad(driver, 90);
		// wait untill Grand total present
		By ele2 = By.xpath("//*[text()='Grand Total']");
		waitfordisplay(driver, 120, ele2, "Grand total present");
		click(driver, ele2, Verifysp[0] + " calulator icon");

		Thread.sleep(2000);
	}

	public void Charticon(WebDriver driver, String Verify3, String Verify) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		int size = Verifysp.length;
		for (int i = 1; i <= size; i++) {
			// click on Grid/chart icon
			By ele1 = By.xpath(
					"//*[contains(text(),'Exposure Breakdown')]/ancestor::a[contains(@data-qtip,'Exposure Breakdown')]/following-sibling::a[contains(@data-qtip,'Grid / Chart')]");
			waitForElementPresent(driver, 120, ele1, " chart icon for " + Verify3sp[0]);
			click(driver, ele1, Verify3sp[0] + " chart icon");
			Thread.sleep(2000);
			// By ele3=By.xpath("//*[@class='x-tool icon-pie-chart x-box-item x-tool-default
			// x-menu-item-cmp x-menu-item-cmp-default']/child::img");
			By ele3 = By.xpath(
					"//div[@class='x-menu icon-menu x-layer x-menu-default x-menu-plain x-border-box']//div//div[@class='x-box-target']/div[contains(@class,'"
							+ Verifysp[i - 1] + "')]");
			waitForElementPresent(driver, 120, ele3, Verifysp[i - 1]);
			// mouse over chart
			// moveToElement(driver, ele3, Verify);
			// click on chart
			click(driver, ele3, " for " + Verifysp[i - 1]);
			Thread.sleep(2000);
			// waitForElementPresent(driver, 120, legendbox);
		}
	}

	public void Downloadicon(WebDriver driver, String Verify3) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		// click on download icon
		By ele1 = By.xpath(
				"//*[contains(text(),'Exposure Breakdown')]/ancestor::a[contains(@data-qtip,'Exposure Breakdown')]/following-sibling::a[contains(@data-qtip,'Download')]");
		waitForElementPresent(driver, 120, ele1, " download icon for " + Verify3sp[0]);
		click(driver, ele1, Verify3sp[0] + " download icon");
		Thread.sleep(2000);
		// wait for download popup window present
		By ele2 = By.xpath("//*[text()='Download items to Excel']");
		waitForElementPresent(driver, 120, ele1, " download popup for " + Verify3sp[0]);
		if (driver.findElement(ele2).isDisplayed()) {

		}
	}

	public void selectTop(WebDriver driver, String Verify3) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");

		String id = "//*[text()='Top']/parent::div[@class='x-form-cb-wrap-inner ']/input";
		String name = "Radio Button to select Top";
		WebPageElements radiobutton = new WebPageElements(name, "xpath", id);
		waitForElementPresent1(driver, 120, radiobutton);
		// click on Top Top Radio button
		click(driver, radiobutton);
		Thread.sleep(2000);

	}

	public void selectcurrentpage(WebDriver driver, String Verify3) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");

		String id = "//*[text()='Current Page']/parent::div[@class='x-form-cb-wrap-inner ']/input";
		String name = "Radio Button to select Current Page";
		WebPageElements radiobutton = new WebPageElements(name, "xpath", id);
		waitForElementPresent1(driver, 120, radiobutton);
		// click on current page radio button
		click(driver, radiobutton);
		Thread.sleep(2000);
	}

	public void selectAllrecords(WebDriver driver, String Verify3) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");

		String id = "//*[text()='All Records']/parent::div[@class='x-form-cb-wrap-inner ']/input";
		String name = "Radio Button to select All Records";
		WebPageElements radiobutton = new WebPageElements(name, "xpath", id);
		waitForElementPresent1(driver, 120, radiobutton);
		// click on all records Radio button
		click(driver, radiobutton);
		Thread.sleep(2000);
	}

	public void selectCSVdelimiter(WebDriver driver, String Verify3) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		String id = "//*[text()='CSV Delimiter']/parent::div[@class='x-form-cb-wrap-inner ']/input";
		String name = "check Box to select CSV Delimiter";
		WebPageElements checkbox = new WebPageElements(name, "xpath", id);
		waitForElementPresent1(driver, 120, checkbox);
		// click on CSV delimter checkbox
		click(driver, checkbox);
		Thread.sleep(2000);

	}

	public void downloadexcel(WebDriver driver, String Verify) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");

		By ele1 = By.xpath("//*[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon-excel ']");
		waitForElementPresent(driver, 120, ele1, " excel icon for ");
		// click on Excel icon
		click(driver, ele1, "excel icon for ");
		Thread.sleep(3000);
	}

	public void message(WebDriver driver, String Verify) throws InterruptedException {
		try {

			WebElement acceptall = driver.findElement(By.xpath(
					"//div[contains(@class,'x-message-box')]//child::div//following-sibling::span[text()='OK']"));
			if (acceptall.isDisplayed() == true) {
				acceptall.click();
				downloadcsv(driver, Verify);
			} else {
				System.out.println("message popup not present");
			}
		} catch (Exception e) {

		}
	}

	public void downloadcsv(WebDriver driver, String Verify) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");

		By ele1 = By.xpath("//*[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon-csv csv ']");
		waitForElementPresent(driver, 120, ele1, " csv icon for " + Verifysp[0]);
		// click on csv icon
		click(driver, ele1, "csv icon for " + Verifysp[0]);
	}

	public void multisorting(WebDriver driver, String Verify, String Verify3) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		By ele1 = By.xpath(
				"//*[contains(text(),'Exposure Breakdown')]/ancestor::a[contains(@data-qtip,'Exposure Breakdown')]/following-sibling::a[contains(@data-qtip,'Multi-Column Sorting')]");
		waitForElementPresent(driver, 120, ele1, " multisorting icon for ");
		// click on multi sorting icon
		click(driver, ele1, " multisorting icon");
		Thread.sleep(2000);
		int size = Verifysp.length;
		for (int i = 1; i <= size; i++) {

			By ele2 = By.xpath("(//input[@name='columnCombobox'])[" + i + "]");
			waitForElementPresent(driver, 120, ele2, " multisorting icon for ");
			// click on drop down
			click(driver, ele2, "   dropdown icon");
			Thread.sleep(2000);

			By ele3 = By.xpath("(//li[text()='" + Verifysp[i - 1] + "'])[" + i + "]");
			// waitForElementPresent(driver, 120, ele3, "");
			Thread.sleep(2000);
			// mouse over item selcet
			moveToElement(driver, ele3, "mouse over element" + Verifysp[i - 1]);
			Thread.sleep(2000);
			// click on item
			click(driver, ele3, "select dropdown " + Verifysp[i - 1]);
			Thread.sleep(2000);
			String id = "(//*[text()='" + Verify3sp[i - 1] + "']/parent::div[@class='x-form-cb-wrap-inner ']/input)["
					+ i + "]";
			String name = "radiobutton to select " + Verify3sp[i - 1];
			WebPageElements radiobutton = new WebPageElements(name, "xpath", id);
			waitForElementPresent1(driver, 120, radiobutton);
			// click on Ascending radiobutton
			click(driver, radiobutton);
			Thread.sleep(2000);
		}
		try {
			// click on apply button
			click(driver, applybtn);
		} catch (Exception e) {
			click(driver, clearbtn);
		}
		waitForLoad(driver, 90);
	}

	public void Editdesignpanel(WebDriver driver, String Verify, String actualViews, String viewName, String Verify3,
			String ReportName, String reportingPeriod, String Fileformat, String FileNameac)
			throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");

		By ele1 = By.xpath(
				"//*[contains(text(),'Exposure Breakdown')]/ancestor::a[contains(@data-qtip,'Exposure Breakdown')]/following-sibling::a[contains(@data-qtip,'Design Panel')]");
		waitForElementPresent(driver, 120, ele1, " Editpanel icon for " + Verifysp[0]);
		click(driver, ele1, Verifysp[0] + " Editpanel icon");
		Thread.sleep(2000);

		criteria(driver, actualViews, viewName, Verify3, ReportName);
		draganddropelement(driver, actualViews, Verify, reportingPeriod);
		EPEkey2(driver, Verify);
		selectCOB(driver, reportingPeriod);
		click(driver, okbtn);
		waitForLoad(driver, 90);
		downloadexcel(driver, Verify);
		downloadfilecommonreg(driver, ReportName, Verify, Fileformat, FileNameac);

	}

	public void criteria(WebDriver driver, String actualViews, String viewName, String Verify3, String ReportName)
			throws InterruptedException {
		String[] actualViewsp = actualViews.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");

		By ele1 = By.xpath("//*[contains(text(),'" + actualViewsp[4] + "')]/ancestor::table[@class='x-grid-item']");
		waitForElementPresent(driver, 120, ele1, " Editpanel icon for ");
		doubleclick(driver, ele1, "double click");
		waitForElementPresent(driver, 120, textbox);
		// click on text box
		Thread.sleep(2000);
		// set text on text box
		setText(driver, textbox, Verify3sp[1]);
		Thread.sleep(2000);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, applybtn);
		// click on Apply button
		click(driver, applybtn);

		By ele2 = By.xpath("(//*[contains(text(),'" + actualViewsp[5] + "')]/ancestor::table)");
		waitForElementPresent(driver, 120, ele2, " Editpanel icon for ");
		doubleclick(driver, ele2, "double click");
		waitForElementPresent(driver, 120, textbox);
		// click on text box
		Thread.sleep(2000);
		// set text on text box
		setText(driver, textbox, Verify3sp[2]);
		Thread.sleep(2000);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, applybtn);
		// click on Apply button
		click(driver, applybtn);

		/*
		 * By ele3 = By.xpath("(//*[contains(text(),'"+ actualViewsp[0]
		 * +"')]/ancestor::table)"); waitForElementPresent(driver, 120, ele3,
		 * " Editpanel icon for "); doubleclick(driver, ele3, "double click");
		 * waitForElementPresent(driver, 120, textbox); //click on text box
		 * Thread.sleep(2000); //set text on text box setText(driver, textbox,
		 * Verify3sp[0]); Thread.sleep(2000); waitForLoad(driver, 90);
		 * waitForElementPresent(driver, 120, applybtn); //click on Apply button
		 * click(driver, applybtn);
		 * 
		 * By ele4 = By.xpath("(//*[contains(text(),'"+ actualViewsp[6]
		 * +"')]/ancestor::table)[2]"); waitForElementPresent(driver, 120, ele4,
		 * " Editpanel icon for "); doubleclick(driver, ele4, "double click");
		 * waitForElementPresent(driver, 120, textbox); //click on text box
		 * Thread.sleep(2000); //set text on text box setText(driver, textbox,
		 * Verify3sp[4]); Thread.sleep(2000); waitForLoad(driver, 90);
		 * waitForElementPresent(driver, 120, applybtn); //click on Apply button
		 * click(driver, applybtn);
		 * 
		 * By ele5 = By.xpath("(//*[contains(text(),'"+ actualViewsp[2]
		 * +"')]/ancestor::table)[2]"); waitForElementPresent(driver, 120, ele5,
		 * " Editpanel icon for "); doubleclick(driver, ele5, "double click");
		 * waitForElementPresent(driver, 120, textbox); //click on text box
		 * Thread.sleep(2000); //set text on text box setText(driver, textbox,
		 * Verify3sp[3]); Thread.sleep(2000); waitForLoad(driver, 90);
		 * waitForElementPresent(driver, 120, applybtn); //click on Apply button
		 * click(driver, applybtn);
		 */
	}

	public void draganddropelement(WebDriver driver, String actualViews, String Verify, String reportingPeriod)
			throws InterruptedException {
		String[] actualViewsp = actualViews.split("\\,");
		Thread.sleep(2000);
		int count = actualViewsp.length;
		System.out.println(count);

		By ele1 = By.xpath("(//*[contains(text(),'Batch Name')]/ancestor::table[@class='x-grid-item'])");
		By ele3 = By
				.xpath("(//*[contains(text(),'" + actualViewsp[1] + "')]/ancestor::table[@class='x-grid-item'])[2]");
		By ele4 = By.xpath("(//*[contains(text(),'Process Type')]/ancestor::table[@class='x-grid-item'])");
		By ele2 = By.xpath(
				"(//*[@class='x-grid-view x-grid-with-row-lines x-fit-item x-grid-view-default x-unselectable']//div//table)[4]");
		dragAndDrop(driver, ele1, ele2, actualViewsp[0]);
		dragAndDrop(driver, ele3, ele2, actualViewsp[1]);
		Thread.sleep(2000);
		dragAndDrop(driver, ele4, ele2, actualViewsp[2]);

		By ele5 = By.xpath(
				"(//*[contains(text(),'Basel Exposure Explain')]/ancestor::table[@class='x-grid-item'])/tbody/tr/td/div/img[2]");
		click(driver, ele5, "");
		waitForLoad(driver, 90);
		By ele6 = By.xpath("//*[contains(text(),'" + actualViewsp[3] + "')]/ancestor::table[@class='x-grid-item']");
		waitfordisplay(driver, 120, ele6, "");
		dragAndDrop(driver, ele6, ele2, actualViewsp[3]);
		click(driver, applychanges);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, filter);

	}

	public void EPEkey2(WebDriver driver, String Verify) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		By ele1 = By.xpath(
				"(//span[text()='Exposure Breakdown']/ancestor::div[contains(@class,'iemPgToolBar')]/following::div[contains(@class,'filterCt')]//div[contains(@class,'icon-angle-down')])[4]");
		waitForElementPresent(driver, 120, ele1, "  Angle Down  ");
		moveToElement(driver, ele1, "Angle Down");
		click(driver, ele1, "Angle Down");
		waitForElementPresent(driver, 120, textbox);
		// click on text box
		Thread.sleep(2000);
		// set text on text box
		setText(driver, textbox, Verifysp[0]);
		Thread.sleep(2000);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, applybtn);
		// click on Apply button
		click(driver, applybtn);
		waitForLoad(driver, 90);
		By ele2 = By.xpath(
				"//span[text()='Exposure Breakdown']/ancestor::div[contains(@class,'iemPgToolBar')]/following-sibling::div[contains(@class,'layout-fit')]/div/div/table[1]");
		waitForElementPresent(driver, 120, ele2, "    ");
		moveToElement(driver, ele2, "");
		contextclick(driver, ele2, "");
		By ele3 = By.xpath("//*[@class='x-menu-item-link']/child::span[text()='View Exposure Breakdown Full Tenors']");
		waitForElementPresent(driver, 120, ele3, "    ");
		// moveToElement(driver, ele3, "");
		// click(driver, ele3, "");
		WebElement breakdown = driver.findElement(ele3);
		breakdown.click();
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, fulltenorwindow);

	}

	public void selectCOB(WebDriver driver, String reportingPeriod) throws InterruptedException {

		String id = "//*[text()='" + reportingPeriod + "']/parent::div[@class='x-form-cb-wrap-inner ']/input";
		String name = "Radio Button to select " + reportingPeriod;
		WebPageElements radiobutton = new WebPageElements(name, "xpath", id);
		waitForElementPresent1(driver, 120, radiobutton);
		// click on COB radio button
		click(driver, radiobutton);
		Thread.sleep(2000);
	}

	public void Filtericon(WebDriver driver, String Verify3) throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		By ele1 = By.xpath(
				"//*[contains(text(),'Exposure Breakdown')]/ancestor::a[contains(@data-qtip,'Exposure Breakdown')]/following-sibling::a[contains(@data-qtip,'Filter')]");
		waitForElementPresent(driver, 120, ele1, " filter icon for " + Verify3sp[0]);
		click(driver, ele1, Verify3sp[0] + " filter icon");
		Thread.sleep(2000);
	}

	public void Filteredit(WebDriver driver, String Verify4) throws InterruptedException {
		String[] Verify4sp = Verify4.split("\\,");
		By ele1 = By.xpath("//*[@class=\"x-component x-component-default\"]/div/span[2]");
		waitForElementPresent(driver, 120, ele1, " filter icon for " + Verify4sp[0]);
		click(driver, ele1, Verify4sp[0] + " filteredit icon");
		Thread.sleep(2000);

	}

	public void currncydropdown(WebDriver driver, String Verify) throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		int m = Verifysp.length;
		for (int i = 1; i <= m; i++) {
			By ele1 = By.xpath(
					"//*[contains(text(),'Exposure Breakdown')]/ancestor::a[contains(@data-qtip,'Exposure Breakdown')]/following-sibling::div[@class='x-field currency-combo x-form-item x-form-item-default x-form-type-text x-form-dirty x-box-item x-toolbar-item x-field-toolbar x-hbox-form-item x-form-item-no-label']/div/div/div[2]");
			waitForElementPresent(driver, 120, ele1, " dropdown icon for ");
			click(driver, ele1, " dropdown icon");
			Thread.sleep(2000);
			By ele3 = By.xpath("//li[text()='" + Verifysp[i - 1] + "']");
			waitForElementPresent(driver, 120, ele3, Verifysp[i - 1]);
			moveToElement(driver, ele3, "move to ");
			click(driver, ele3, Verifysp[i - 1]);
			Thread.sleep(2000);

		}
	}

	public void profilegraph(WebDriver driver, String viewName, String Verify3, String ReportName)
			throws InterruptedException {

		selectorinalportfolioID(driver, viewName, Verify3, ReportName);

		By ele2 = By.xpath(
				"//span[text()='Exposure Breakdown']/ancestor::div[contains(@class,'iemPgToolBar')]/following-sibling::div[contains(@class,'layout-fit')]/div/div/table[1]");
		waitForElementPresent(driver, 120, ele2, "    ");
		moveToElement(driver, ele2, "");
		contextclick(driver, ele2, "");
		By ele3 = By.xpath("//*[@class='x-menu-item-link']/child::span[text()='View Profile Graph']");
		waitForElementPresent(driver, 120, ele3, "    ");
		WebElement breakdown = driver.findElement(ele3);
		breakdown.click();
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, profilegraph);
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();

	}

	public void comparevalues(WebDriver driver, String viewName, String Verify3, String ReportName,
			String reportingPeriod) throws InterruptedException {

		selectorinalportfolioID(driver, viewName, Verify3, ReportName);
		By ele2 = By.xpath(
				"//span[text()='Exposure Breakdown']/ancestor::div[contains(@class,'iemPgToolBar')]/following-sibling::div[contains(@class,'layout-fit')]/div/div/table[1]");
		waitForElementPresent(driver, 120, ele2, "    ");
		moveToElement(driver, ele2, "");
		contextclick(driver, ele2, "");
		By ele3 = By.xpath("//*[@class='x-menu-item-link']/child::span[text()='Compare Values']");
		waitForElementPresent(driver, 120, ele3, "    ");
		WebElement breakdown = driver.findElement(ele3);
		breakdown.click();
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, timeserieswindow);
		selectCOB2(driver, reportingPeriod);
		click(driver, okbtn);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, comparevalues);
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void compareprofile(WebDriver driver, String viewName, String Verify3, String Verify4, String ReportName,
			String reportingPeriod) throws InterruptedException {

		selectorinalportfolioID(driver, viewName, Verify3, ReportName);
		By ele2 = By.xpath(
				"//span[text()='Exposure Breakdown']/ancestor::div[contains(@class,'iemPgToolBar')]/following-sibling::div[contains(@class,'layout-fit')]/div/div/table[1]");
		waitForElementPresent(driver, 120, ele2, "    ");
		moveToElement(driver, ele2, "");
		contextclick(driver, ele2, "");
		By ele3 = By.xpath("//*[@class='x-menu-item-link']/child::span[text()='Compare Profiles']");
		waitForElementPresent(driver, 120, ele3, "    ");
		WebElement breakdown = driver.findElement(ele3);
		breakdown.click();
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, timeserieswindow);
		Tenorcheckbox(driver, Verify4);
		selectCOB2(driver, reportingPeriod);
		click(driver, okbtn);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, compareprofile);
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void selectCOB2(WebDriver driver, String reportingPeriod) throws InterruptedException {
		String[] reportingPeriodsp = reportingPeriod.split("\\,");
		String id = "(//*[text()='" + reportingPeriod
				+ "']/parent::label/following-sibling::div/descendant::input[contains(@id,'checkbox')])[1]";
		String name = "checkbox to select COB1";
		WebPageElements checkbox = new WebPageElements(name, "xpath", id);
		waitForElementPresent1(driver, 120, checkbox);
		// click on COB checkbox
		click(driver, checkbox);

		String id1 = "(//*[text()='" + reportingPeriod
				+ "']/parent::label/following-sibling::div/descendant::input[contains(@id,'checkbox')])[2]";
		String name1 = "checkbox to select COB2";
		WebPageElements checkbox1 = new WebPageElements(name1, "xpath", id1);
		waitForElementPresent1(driver, 120, checkbox1);
		// click on COB checkbox
		click(driver, checkbox1);
		Thread.sleep(2000);
	}

	public void Tenorcheckbox(WebDriver driver, String Verify4) throws InterruptedException {
		String[] Verify4sp = Verify4.split("\\,");
		String id = "//*[text()='" + Verify4sp[0] + "']/parent::div[@class='x-form-cb-wrap-inner ']/input";
		String name = "checkbox to select " + Verify4sp[0];
		WebPageElements checkbox = new WebPageElements(name, "xpath", id);
		waitForElementPresent1(driver, 120, checkbox);
		// click on COB checkbox
		click(driver, checkbox);
		Thread.sleep(2000);
	}

	public void headerclick(WebDriver driver, String actualViews) throws InterruptedException {
		String[] actualViewsp = actualViews.split("\\,");
		WebElement sort1_dlle = driver.findElement(By.xpath(
				"//span[text()='Exposure Breakdown']/ancestor::div[contains(@class,'iemPgToolBar')]/following::div[contains(@class,'inlinefilter')]/span/span/span[text()='"
						+ actualViewsp[0] + "']"));
		String valuetext = sort1_dlle.getText();
		System.out.println(valuetext);
		if (actualViewsp[0].contains(valuetext)) {
			By ele = By
					.xpath("//*[text()='" + actualViewsp[0] + "']/parent::span[@class='x-column-header-text-wrapper']");
			waitForElementPresent(driver, 120, ele, "  header  ");
			moveToElement(driver, ele, "header");
			click(driver, ele, "header");
			By ele1 = By.xpath("//*[text()='" + actualViewsp[0]
					+ "']/ancestor::div[contains(@class,'inlinefilter')]/child::div[@class='x-column-header-trigger']");
			waitForElementPresent(driver, 120, ele1, "    ");
			moveToElement(driver, ele1, "");
			click(driver, ele1, "");
		} else {

		}
	}

	public void headercount(WebDriver driver, String actualViews, String Verify) throws InterruptedException {
		String[] actualViewsp = actualViews.split("\\,");

		WebElement sort1_dlle = driver.findElement(By.xpath(
				"//span[text()='Exposure Breakdown']/ancestor::div[contains(@class,'iemPgToolBar')]/following::div[contains(@class,'inlinefilter')]/span/span/span[text()='"
						+ actualViewsp[0] + "']"));
		String valuetext = sort1_dlle.getText();
		System.out.println(valuetext);
		if (actualViewsp[0].contains(valuetext)) {
			By ele = By
					.xpath("//*[text()='" + actualViewsp[0] + "']/parent::span[@class='x-column-header-text-wrapper']");
			waitForElementPresent(driver, 120, ele, "  header  ");
			moveToElement(driver, ele, "header");
			click(driver, ele, "header");
			By ele1 = By.xpath("//*[text()='" + actualViewsp[0]
					+ "']/ancestor::div[contains(@class,'inlinefilter')]/child::div[@class='x-column-header-trigger']");
			waitForElementPresent(driver, 120, ele1, "    ");
			moveToElement(driver, ele1, "");
			click(driver, ele1, "");
		} else {

		}

		if (actualViewsp[1].equals("Aggregate")) {
			By ele2 = By.xpath("//*[text()='" + actualViewsp[1] + "']");
			waitForElementPresent(driver, 120, ele2, "Aggregate  ");
			moveToElement(driver, ele2, "Aggregate");
			click(driver, ele2, "Aggregate");
			waitForLoad(driver, 90);
			By ele3 = By.xpath("//*[text()='Count']/parent::a");
			waitForElementPresent(driver, 120, ele3, "  Count  ");
			moveToElement(driver, ele3, "Count");
			click(driver, ele3, "Count");
			waitForLoad(driver, 90);
		} else {
			System.out.println(actualViews + " not present on list");
		}

		WebElement sort2_dlle = driver.findElement(By.xpath(
				"//span[text()='Exposure Breakdown']/ancestor::div[contains(@class,'iemPgToolBar')]/following::div[contains(@class,'inlinefilter')]/span/span/span[text()='1M']"));
		String valuetext2 = sort2_dlle.getText();
		System.out.println(valuetext2);

		if (actualViewsp[2].contains(valuetext2)) {
			Add_Log.info("Successfully displayed  Name = " + valuetext2);
			Reporter.log("Successfully displayed  Name = " + valuetext2);
			By ele = By.xpath("(//*[text()='1M']/parent::span[@class='x-column-header-text-wrapper'])[1]");
			waitForElementPresent(driver, 120, ele, "  header  ");
			moveToElement(driver, ele, "header");
			click(driver, ele, "header");
			By ele1 = By.xpath(
					"(//*[text()='1M']/ancestor::div[contains(@class,'inlinefilter')]/child::div[@class='x-column-header-trigger'])[1]");
			waitForElementPresent(driver, 120, ele1, "header angledown");
			moveToElement(driver, ele1, "header angledown");
			click(driver, ele1, "header angledown");
		} else {
			System.out.println(actualViews + " not present on list");
		}
		if (actualViewsp[1].equals("Aggregate")) {
			By ele2 = By.xpath("//*[text()='" + actualViewsp[1] + "']");
			waitForElementPresent(driver, 120, ele2, "Aggregate  ");
			moveToElement(driver, ele2, "Aggregate");
			click(driver, ele2, "Aggregate");
			waitForLoad(driver, 90);
			By ele3 = null;
			if (actualViewsp[3].equals("Sum")) {
				ele3 = By.xpath("//*[text()='" + actualViewsp[3] + "']/parent::a");
			}
			if (actualViewsp[3].equals("Max")) {
				ele3 = By.xpath("//*[text()='" + actualViewsp[3] + "']/parent::a");
			}
			if (actualViewsp[3].equals("Min")) {
				ele3 = By.xpath("//*[text()='" + actualViewsp[3] + "']/parent::a");
			}
			waitForElementPresent(driver, 120, ele3, actualViewsp[3]);
			moveToElement(driver, ele3, actualViewsp[3]);
			click(driver, ele3, actualViewsp[3]);
			waitForLoad(driver, 90);
		} else {
			System.out.println(actualViews + " not present on list");
		}
	}

	public void selectfunction(WebDriver driver, String actualViews) throws InterruptedException {
		String[] actualViewsp = actualViews.split("\\,");
		By ele2 = null;
		if (actualViewsp[1].equals("Locate In Tree")) {
			ele2 = By.xpath("//*[text()='" + actualViewsp[1] + "']");
		}
		if (actualViewsp[1].equals("Sort Ascending")) {
			ele2 = By.xpath("//*[text()='" + actualViewsp[1] + "']");
		}
		if (actualViewsp[1].equals("Sort Descending")) {
			ele2 = By.xpath("//*[text()='" + actualViewsp[1] + "']");
		}
		if (actualViewsp[1].equals("Set Global Filter")) {
			ele2 = By.xpath("//*[text()='" + actualViewsp[1] + "']");
		}
		if (actualViewsp[1].equals("Aggregate")) {
			ele2 = By.xpath("//*[text()='" + actualViewsp[1] + "']");
		}
		if (actualViewsp[1].equals("View Variance")) {
			ele2 = By.xpath("//*[text()='" + actualViewsp[1] + "']");
		}
		waitForElementPresent(driver, 120, ele2, actualViewsp[1]);
		moveToElement(driver, ele2, actualViewsp[1]);
		click(driver, ele2, actualViewsp[1]);
		waitForLoad(driver, 90);

	}

	public void Locateverify(WebDriver driver) throws InterruptedException {

		By tree = By.xpath("//*[text()='Stress Type']/ancestor::table[@class='x-grid-item x-grid-item-selected']");
		WebElement treecenarioName = driver.findElement(tree);
		waitForLoad(driver, 90);
		Thread.sleep(2000);
		String treescenarioNamea = treecenarioName.getAttribute("textContent");
		System.out.println(treescenarioNamea);
		if (treecenarioName.isDisplayed()) {
			Add_Log.info("Successfully displayed ");
			Reporter.log("Successfully displayed ");
		} else {
			Add_Log.info("Unable to displayed");
			Reporter.log("Unable to displayed ");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed ");
			Assert.fail();

		}
	}

	public void filterverify(WebDriver driver) throws InterruptedException {

		By tree = By.xpath("//*[contains(text(),'Stress Type')]/ancestor::div[@class='iemFilterV  ']");
		WebElement treecenarioName = driver.findElement(tree);
		waitForLoad(driver, 90);
		Thread.sleep(2000);
		String treescenarioNamea = treecenarioName.getAttribute("textContent");
		System.out.println(treescenarioNamea);
		if (treecenarioName.isDisplayed()) {
			Add_Log.info("Successfully displayed ");
			Reporter.log("Successfully displayed ");
		} else {
			Add_Log.info("Unable to displayed");
			Reporter.log("Unable to displayed ");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed ");
			Assert.fail();

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

	}

	public void context_funtionaityselect(WebDriver driver, String Report, String Verify2) throws InterruptedException {

		waitForLoad(driver, 900);
		// By element=By.xpath("//*[text()='"+ Verify2
		// +"']/ancestor::div[contains(@class,'x-grid-header-ct')]/following-sibling::div[contains(@class,'body')]/div/div[contains(@class,'container')]/table[1]");
		By element = By.xpath("//span[contains(text(),'" + Verify2
				+ "')]/ancestor::div[contains(@class,'iemPgToolBar')]/following-sibling::div[contains(@class,'layout-fit')]/div/div/table[1]");
		WebElement focus_element = driver.findElement(element);
		// waitfordisplay(driver, 90, element, "");
		contextclick(driver, element, "value");

		By ele3 = By.xpath("//*[@class='x-menu-item-link']/child::span[contains(text(),'" + Report + "')]");
		waitForElementPresent(driver, 120, ele3, Report);
		WebElement breakdown = driver.findElement(ele3);
		if (breakdown.isDisplayed()) {
			Add_Log.info("Successfully displayed " + Report);
			Reporter.log("Successfully displayed " + Report);
		} else {
			Add_Log.info("Unable to displayed" + Report);
			Reporter.log("Unable to displayed " + Report);
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed " + Report);
			Assert.fail();

		}
		// breakdown.click();
		click(driver, ele3, Report);
	}

	public void select_measures(WebDriver driver, String Verify4) throws InterruptedException {
		String[] Verify4sp = Verify4.split("\\,");
		waitForElementPresent(driver, 120, timeserieswindow);
		int measurecount = Verify4sp.length;
		System.out.println(measurecount);
		for (int i = 0; i < measurecount; i++) {

			String id = "//*[text()='" + Verify4sp[i] + "']/parent::div[@class='x-form-cb-wrap-inner ']/input";
			String name = "checkbox to select " + Verify4sp[i];
			WebPageElements checkbox = new WebPageElements(name, "xpath", id);
			waitForElementPresent1(driver, 120, checkbox);
			// click on measure checkbox
			click(driver, checkbox);
			Thread.sleep(2000);
		}
	}

	public void RWA_comparevalues(WebDriver driver, String viewName, String Verify, String Verify2, String Verify3,
			String Verify4, String Report, String ReportName, String reportingPeriod) throws InterruptedException {

		Adhoc(driver, viewName, Verify, ReportName);
		OTCHeader1(driver, viewName, Verify, ReportName);
		context_funtionaityselect(driver, Report, Verify2);
		select_measures(driver, Verify4);
		selectCOB2(driver, reportingPeriod);
		click(driver, ok_btn);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, comparevalues);
		Thread.sleep(2000);
	}

	public void SCCC_comparevalues(WebDriver driver, String viewName, String Verify, String Verify2, String Verify3,
			String Verify4, String Report, String ReportName, String reportingPeriod) throws InterruptedException {

		Adhoc(driver, viewName, Verify, ReportName);
		OTCHeader1(driver, viewName, Verify, ReportName);
		context_funtionaityselect(driver, Report, Verify2);
		select_measures(driver, Verify4);
		selectCOB2(driver, reportingPeriod);
		click(driver, ok_btn);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, comparevalues);
		Thread.sleep(2000);
	}

	public void Trade_comparevalues(WebDriver driver, String viewName, String Verify, String Verify2, String Verify3,
			String Verify4, String Report, String ReportName, String reportingPeriod) throws InterruptedException {

		Adhoc(driver, viewName, Verify, ReportName);
		OTCHeader1(driver, viewName, Verify, ReportName);
		context_funtionaityselect(driver, Report, Verify2);
		select_measures(driver, Verify4);
		selectCOB2(driver, reportingPeriod);
		click(driver, ok_btn);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, comparevalues);
		Thread.sleep(2000);
	}

	public void Trade_ViewMoment(WebDriver driver, String viewName, String Verify, String Verify2, String Verify3,
			String Verify4, String Report, String ReportName, String actualViews, String actualViews2,
			String reportingPeriod, String Fileformat, String FileNameac) throws InterruptedException, IOException {
		String[] actualViews2sp = actualViews2.split("\\,");
		Adhoc(driver, viewName, Verify, ReportName);
		OTCHeader1(driver, viewName, Verify, ReportName);
		context_funtionaityselect(driver, Report, Verify2);
		waitForElementPresent(driver, 120, movementanalysis);
		dropdown(driver, actualViews, actualViews2sp[0]);
		selectCOB2(driver, reportingPeriod);
		click(driver, ok_btn);
		waitForProcess(driver, 900);
		downloadexcel(driver, Verify4);
		downloadfilecommonreg(driver, ReportName, Verify4, Fileformat, FileNameac);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		context_funtionaityselect(driver, Report, Verify2);
		waitForElementPresent(driver, 120, movementanalysis);
		dropdown(driver, actualViews, actualViews2sp[1]);
		selectCOB2(driver, reportingPeriod);
		click(driver, ok_btn);
		waitForProcess(driver, 900);
		downloadexcel(driver, Verify4);
		downloadfilecommonreg(driver, ReportName, Verify4, Fileformat, FileNameac);
		Actions action1 = new Actions(driver);
		action1.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void Data_Selection(WebDriver driver, String Verify3) throws InterruptedException {

		String id = "//*[text()='" + Verify3 + "']/parent::div[@class='x-form-cb-wrap-inner ']/input";
		String name = "Radio Button to " + Verify3;
		WebPageElements radiobutton = new WebPageElements(name, "xpath", id);
		waitForElementPresent1(driver, 120, radiobutton);
		// click on Data_Selection Radio button
		click(driver, radiobutton);
		Thread.sleep(2000);
	}

	public void Tradedetails(WebDriver driver, String viewName, String Verify, String ReportName, String Verify2,
			String Verify3, String Verify4, String Report, String reportingPeriod, String Fileformat, String FileNameac)
			throws InterruptedException, IOException {
		String[] Verify3sp = Verify3.split("\\,");
		Adhoc(driver, viewName, Verify, ReportName);
		OTCHeader1(driver, viewName, Verify, ReportName);
		context_funtionaityselect(driver, Report, Verify2);
		waitForElementPresent(driver, 120, view_tradedetails);
		selectCOB(driver, reportingPeriod);
//				Data_Selection(driver, Verify3sp[1]);
		click(driver, ok_btn);
		waitForProcess(driver, 900);
		downloadexcel(driver, Verify4);
		downloadfilecommonreg(driver, ReportName, Verify4, Fileformat, FileNameac);
		Actions action1 = new Actions(driver);
		action1.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void OTC_Transaction(WebDriver driver, String viewName, String Verify, String ReportName, String Verify2,
			String Report, String reportingPeriod) throws InterruptedException {

		Adhoc(driver, viewName, Verify, ReportName);
		OTCHeader1(driver, viewName, Verify, ReportName);
		context_funtionaityselect(driver, Report, Verify2);
		waitForElementPresent(driver, 120, otc_transaction);
		selectCOB(driver, reportingPeriod);
		click(driver, ok_btn);
		waitForProcess(driver, 900);
	}

	public void open_transactions(WebDriver driver, String viewName, String Verify, String ReportName, String Verify2,
			String Report, String actualViews) throws InterruptedException {
		String[] actualViewsp = actualViews.split("\\,");
		Adhoc(driver, viewName, Verify, ReportName);
		OTCHeader1(driver, viewName, Verify, ReportName);
		By ele1 = By.xpath(
				"//*[contains(text(),'Trade Info')]/ancestor::a[contains(@data-qtip,'Trade Info')]/following-sibling::a[contains(@data-qtip,'Design Panel')]");
		waitForElementPresent(driver, 120, ele1, " Editpanel icon for ");
		click(driver, ele1, " Editpanel icon");
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, applychanges);
		searchheader(driver, actualViewsp[0]);
		searchheader(driver, actualViewsp[1]);
		searchheader(driver, actualViewsp[2]);
		click(driver, applychanges);
		context_funtionaityselect(driver, Report, Verify2);
		waitForElementPresent(driver, 120, open_transaction);
		By viewer = By.xpath("//a[contains(@href,'TransactionViewer')]");
		waitForElementPresent(driver, 120, viewer, " TransactionViewer URL ");
		click(driver, viewer, " TransactionViewer URL ");
	}

	public void BVI_profilegraph(WebDriver driver, String viewName, String ReportName, String Verify, String Filter,
			String Report, String Verify2) throws InterruptedException {
		String[] Filtersp = Filter.split("\\,");
		Adhoc(driver, viewName, Verify, ReportName);
		OTCHeader1(driver, viewName, Verify, ReportName);
		// prespective_domain(driver, ReportName);
		// domain_selection(driver, viewName);
		String map = driver.getCurrentUrl();
		if (map.contains("uat")) {
			removefilter(driver, Filtersp[0]);
			removefilter(driver, Filtersp[1]);
			removefilter(driver, Filtersp[2]);
		} else {

		}
		context_funtionaityselect(driver, Report, Verify2);
		waitForElementPresent(driver, 120, profilegraph);
		Actions action1 = new Actions(driver);
		action1.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void CR01_profilegraph(WebDriver driver, String viewName, String ReportName, String Verify, String Filter,
			String Report, String Verify2) throws InterruptedException {
		String[] Filtersp = Filter.split("\\,");
		Adhoc(driver, viewName, Verify, ReportName);
		prespective_domain(driver, ReportName);
		// domain_selection(driver, viewName);
		hiddendomain_selection(driver, viewName);
		removefilter(driver, Filtersp[0]);
		removefilter(driver, Filtersp[1]);
		removefilter(driver, Filtersp[2]);
		context_funtionaityselect(driver, Report, Verify2);
		waitForElementPresent(driver, 120, profilegraph);
		Actions action1 = new Actions(driver);
		action1.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void CR01_comparevalues(WebDriver driver, String viewName, String ReportName, String Verify, String Filter,
			String Report, String Verify2, String Verify4, String reportingPeriod) throws InterruptedException {
		String[] Filtersp = Filter.split("\\,");
		Adhoc(driver, viewName, Verify, ReportName);
		prespective_domain(driver, ReportName);
		// domain_selection(driver, viewName);
		hiddendomain_selection(driver, viewName);
		removefilter(driver, Filtersp[0]);
		removefilter(driver, Filtersp[1]);
		removefilter(driver, Filtersp[2]);
		context_funtionaityselect(driver, Report, Verify2);
		select_measures(driver, Verify4);
		selectCOB2(driver, reportingPeriod);
		click(driver, ok_btn);
		waitForLoad(driver, 90);
		try {
			By ErrorSnackBar = By.xpath("//div[contains(@class,'x-message-box-error')]");
			if (driver.findElements(ErrorSnackBar).size() > 0) {
				WebElement errormessage = driver.findElement(By.xpath(
						"//div[contains(@class,'x-message-box-error')]//following::div[contains(text(),'In selected row')]"));
				Reporter.log("Error message: " + errormessage.getText());
				Add_Log.info("Error message: " + errormessage.getText());
				TestResultStatus.mpaskeysnew.put(methodName(), "Error message: " + errormessage.getText());
				ITestResult result = Reporter.getCurrentTestResult();
				result.setAttribute("DontRetry", Boolean.TRUE.toString());
				Assert.fail();
			}
		} catch (NoSuchElementException e1) {

		}
		waitForElementPresent(driver, 120, comparevalues);
		Thread.sleep(2000);
		Actions action1 = new Actions(driver);
		action1.sendKeys(Keys.ESCAPE).build().perform();

	}

	public void CR01_compareprofile(WebDriver driver, String viewName, String ReportName, String Verify, String Filter,
			String Report, String Verify2, String Verify4, String reportingPeriod) throws InterruptedException {
		String[] Filtersp = Filter.split("\\,");
		Adhoc(driver, viewName, Verify, ReportName);
		prespective_domain(driver, ReportName);
		// domain_selection(driver, viewName);
		hiddendomain_selection(driver, viewName);
		removefilter(driver, Filtersp[0]);
		removefilter(driver, Filtersp[1]);
		removefilter(driver, Filtersp[2]);
		context_funtionaityselect(driver, Report, Verify2);
		Tenorcheckbox(driver, Verify4);
		selectCOB2(driver, reportingPeriod);
		click(driver, ok_btn);
		waitForLoad(driver, 90);
		try {
			By ErrorSnackBar = By.xpath("//div[contains(@class,'x-message-box-error')]");
			if (driver.findElements(ErrorSnackBar).size() > 0) {
				WebElement errormessage = driver.findElement(By.xpath(
						"//div[contains(@class,'x-message-box-error')]//following::div[contains(text(),'In selected row')]"));
				Reporter.log("Error message: " + errormessage.getText());
				Add_Log.info("Error message: " + errormessage.getText());
				TestResultStatus.mpaskeysnew.put(methodName(), "Error message: " + errormessage.getText());
				ITestResult result = Reporter.getCurrentTestResult();
				result.setAttribute("DontRetry", Boolean.TRUE.toString());
				Assert.fail();
			}
		} catch (NoSuchElementException e1) {

		}
		waitForElementPresent(driver, 120, compareprofile);
		Thread.sleep(2000);
		Actions action1 = new Actions(driver);
		action1.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void Credit_Spread_profilegraph(WebDriver driver, String viewName, String ReportName, String Verify,
			String Filter, String Report, String Verify2) throws InterruptedException {

		Adhoc(driver, viewName, Verify, ReportName);
		prespective_domain(driver, ReportName);
		// domain_selection(driver, viewName);
		hiddendomain_selection(driver, viewName);
		context_funtionaityselect(driver, Report, Verify2);
		waitForElementPresent(driver, 120, profilegraph);
		Actions action1 = new Actions(driver);
		action1.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void Credit_Spread_comparevalues(WebDriver driver, String viewName, String ReportName, String Verify,
			String Filter, String Report, String Verify2, String Verify4, String reportingPeriod)
			throws InterruptedException {
		String[] Filtersp = Filter.split("\\,");
		Adhoc(driver, viewName, Verify, ReportName);
		prespective_domain(driver, ReportName);
		// domain_selection(driver, viewName);
		hiddendomain_selection(driver, viewName);
		context_funtionaityselect(driver, Report, Verify2);
		// select_measures(driver, Verify4);
		selectCOB2(driver, reportingPeriod);
		click(driver, ok_btn);
		waitForLoad(driver, 90);
		try {
			By ErrorSnackBar = By.xpath("//div[contains(@class,'x-message-box-error')]");
			if (driver.findElements(ErrorSnackBar).size() > 0) {
				WebElement errormessage = driver.findElement(By.xpath(
						"//div[contains(@class,'x-message-box-error')]//following::div[contains(text(),'In selected row')]"));
				Reporter.log("Error message: " + errormessage.getText());
				Add_Log.info("Error message: " + errormessage.getText());
				TestResultStatus.mpaskeysnew.put(methodName(), "Error message: " + errormessage.getText());
				ITestResult result = Reporter.getCurrentTestResult();
				result.setAttribute("DontRetry", Boolean.TRUE.toString());
				Assert.fail();
			}
		} catch (NoSuchElementException e1) {

		}
		waitForElementPresent(driver, 120, comparevalues);
		Thread.sleep(2000);
		Actions action1 = new Actions(driver);
		action1.sendKeys(Keys.ESCAPE).build().perform();

	}

	public void Credit_Spread_compareprofile(WebDriver driver, String viewName, String ReportName, String Verify,
			String Filter, String Report, String Verify2, String Verify4, String reportingPeriod)
			throws InterruptedException {
		String[] Filtersp = Filter.split("\\,");
		Adhoc(driver, viewName, Verify, ReportName);
		prespective_domain(driver, ReportName);
		// domain_selection(driver, viewName);
		hiddendomain_selection(driver, viewName);
		context_funtionaityselect(driver, Report, Verify2);
		Tenorcheckbox(driver, Verify4);
		selectCOB2(driver, reportingPeriod);
		click(driver, ok_btn);
		waitForLoad(driver, 90);
		try {
			By ErrorSnackBar = By.xpath("//div[contains(@class,'x-message-box-error')]");
			if (driver.findElements(ErrorSnackBar).size() > 0) {
				WebElement errormessage = driver.findElement(By.xpath(
						"//div[contains(@class,'x-message-box-error')]//following::div[contains(text(),'In selected row')]"));
				Reporter.log("Error message: " + errormessage.getText());
				Add_Log.info("Error message: " + errormessage.getText());
				TestResultStatus.mpaskeysnew.put(methodName(), "Error message: " + errormessage.getText());
				ITestResult result = Reporter.getCurrentTestResult();
				result.setAttribute("DontRetry", Boolean.TRUE.toString());
				Assert.fail();
			}
		} catch (NoSuchElementException e1) {

		}
		waitForElementPresent(driver, 120, compareprofile);
		Thread.sleep(2000);
		Actions action1 = new Actions(driver);
		action1.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void SFTCounterparty_comparevalues(WebDriver driver, String viewName, String Verify, String Verify2,
			String Verify3, String Verify4, String Report, String ReportName, String reportingPeriod)
			throws InterruptedException {

		Adhoc(driver, viewName, Verify, ReportName);
		prespective_domain(driver, ReportName);
		// domain_selection(driver, viewName);
		// hiddendomain_selection(driver, viewName);
		click(driver, sftplus);
		By ele3 = By.xpath("(//*[text()='" + viewName + "']/parent::a)");
		waitForElementPresent(driver, 120, ele3, viewName);
		click(driver, ele3, viewName);
		context_funtionaityselect(driver, Report, Verify2);
		select_measures(driver, Verify4);
		selectCOB2(driver, reportingPeriod);
		click(driver, ok_btn);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, comparevalues);
		Thread.sleep(2000);
		Actions action1 = new Actions(driver);
		action1.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void SFTExposure_comparevalues(WebDriver driver, String viewName, String Verify, String Verify2,
			String Verify3, String Verify4, String Report, String ReportName, String reportingPeriod)
			throws InterruptedException {

		Adhoc(driver, viewName, Verify, ReportName);
		prespective_domain(driver, ReportName);

		click(driver, sftplus);
		By ele3 = By.xpath("(//*[text()='" + viewName + "']/parent::a)");
		waitForElementPresent(driver, 120, ele3, viewName);
		click(driver, ele3, viewName);
		// context_funtionaityselect(driver,Report,Verify2);
		waitForLoad(driver, 900);
		// By element=By.xpath("//*[text()='"+ Verify2
		// +"']/ancestor::div[contains(@class,'x-grid-header-ct')]/following-sibling::div[contains(@class,'body')]/div/div[contains(@class,'container')]/table[1]");
		By element = By.xpath("(//span[contains(text(),'" + Verify2
				+ "')]/ancestor::div[contains(@class,'iemPgToolBar')]/following-sibling::div[contains(@class,'layout-fit')]/div/div/table[1])[2]");
		WebElement focus_element = driver.findElement(element);
		// waitfordisplay(driver, 90, element, "");
		contextclick(driver, element, "value");

		By ele4 = By.xpath("//*[@class='x-menu-item-link']/child::span[contains(text(),'" + Report + "')]");
		waitForElementPresent(driver, 120, ele4, Report);
		WebElement breakdown = driver.findElement(ele4);
		if (breakdown.isDisplayed()) {
			Add_Log.info("Successfully displayed " + Report);
			Reporter.log("Successfully displayed " + Report);
		} else {
			Add_Log.info("Unable to displayed" + Report);
			Reporter.log("Unable to displayed " + Report);
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to displayed " + Report);
			Assert.fail();

		}
		// breakdown.click();
		click(driver, ele4, Report);

		select_measures(driver, Verify4);
		selectCOB2(driver, reportingPeriod);
		click(driver, ok_btn);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, comparevalues);
		Thread.sleep(2000);
		Actions action1 = new Actions(driver);
		action1.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void SFTTrade_comparevalues(WebDriver driver, String viewName, String Verify, String Verify2, String Verify3,
			String Verify4, String Report, String ReportName, String reportingPeriod) throws InterruptedException {

		Adhoc(driver, viewName, Verify, ReportName);
		prespective_domain(driver, ReportName);
		// domain_selection(driver, viewName);
		// hiddendomain_selection(driver, viewName);
		click(driver, sftplus);
		By ele3 = By.xpath("(//*[text()='" + viewName + "']/parent::a)");
		waitForElementPresent(driver, 120, ele3, viewName);
		click(driver, ele3, viewName);
		context_funtionaityselect(driver, Report, Verify2);
		select_measures(driver, Verify4);
		selectCOB2(driver, reportingPeriod);
		click(driver, ok_btn);
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 120, comparevalues);
		Thread.sleep(2000);
		Actions action1 = new Actions(driver);
		action1.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void SFTTrade_Portfolio_Decision_Tree(WebDriver driver, String viewName, String ReportName,
			String actualViews, String Verify, String Verify2, String Report) throws InterruptedException {
		String[] actualViewsp = actualViews.split("\\,");
		Adhoc(driver, viewName, Verify, ReportName);
		prespective_domain(driver, ReportName);
		// domain_selection(driver, viewName);
		// hiddendomain_selection(driver, viewName);
		click(driver, sftplus);
		By ele3 = By.xpath("(//*[text()='" + viewName + "']/parent::a)");
		waitForElementPresent(driver, 120, ele3, viewName);
		click(driver, ele3, viewName);

		By ele1 = By.xpath(
				"//*[contains(text(),'Basel SFT Trade Detail Info')]/ancestor::a[contains(@data-qtip,'Basel SFT Trade Detail Info')]/following-sibling::a[contains(@data-qtip,'Design Panel')]");
		waitForElementPresent(driver, 120, ele1, " Editpanel icon for ");
		click(driver, ele1, " Editpanel icon");
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, applychanges);
		SFTsearchheader(driver, actualViewsp[0]);
		SFTsearchheader(driver, actualViewsp[1]);
		SFTsearchheader(driver, actualViewsp[2]);
		click(driver, applychanges);
		context_funtionaityselect(driver, Report, Verify2);
	}

	public void searchheader(WebDriver driver, String actualViews) throws InterruptedException {

		By search = By.xpath("(//*[@placeholder='Enter Minimum 3 chars'])");
		waitForElementPresent(driver, 120, search, "search");
		click(driver, search, "search");
		setText(driver, search, actualViews, "");
		By tri = By.xpath("//ul[@class='x-list-plain']//span[contains(text(),'" + actualViews + "')]");
		waitForElementPresent(driver, 120, tri, "    ");
		By to = By.xpath(
				"(//*[@class='x-grid-view x-grid-with-row-lines x-fit-item x-grid-view-default x-unselectable']//div//table)[4]");
		dragAndDrop(driver, tri, to, "");
		clearText(driver, search, "clear search text");
	}

	public void SFTsearchheader(WebDriver driver, String actualViews) throws InterruptedException {

		By search = By.xpath("(//*[@placeholder='Enter Minimum 3 chars'])[3]");
		waitForElementPresent(driver, 120, search, "search");
		click(driver, search, "search");
		setText(driver, search, actualViews, "");
		By tri = By.xpath("//ul[@class='x-list-plain']//span[contains(text(),'" + actualViews + "')]");
		waitForElementPresent(driver, 120, tri, "    ");
		By to = By.xpath(
				"(//*[@class='x-grid-view x-grid-with-row-lines x-fit-item x-grid-view-default x-unselectable']//div//table)[4]");
		dragAndDrop(driver, tri, to, "");
		clearText(driver, search, "clear search text");
	}

	public void View_Basel_Trade_PV_Matrix(WebDriver driver, String viewName, String ReportName, String Verify)
			throws InterruptedException {

		Adhoc(driver, viewName, Verify, ReportName);
		OTCHeader1(driver, viewName, Verify, ReportName);

	}

}

