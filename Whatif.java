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
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import property.IHomePage;
import testsuitebase.TestResultStatus;
import utility.SeleniumUtils;
import utility.WebPageElements;




public class Whatif extends SeleniumUtils implements IHomePage {

	public DecimalFormat df = new DecimalFormat("#.##");
	public String testName = null;
	public boolean Testskip = false;
	public boolean Testfail = false;
	SoftAssert s_assert = null;
	public double finish, start;
	public double end;
	boolean allFlag = false;
	
	public static String methodName() {
		String methodname = null;
		StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
		for (StackTraceElement elem : stackTraceElements) {
			//System.out.println(elem);
			if (elem.getClassName().contains("testcases")) {
				methodname = elem.getMethodName();
			//	System.out.println(methodname);
			}
			
		}
		return methodname;
	}
	public void Snackbar(WebDriver driver) throws InterruptedException {
		WebElement errormessage= null;
		String errormess =null;
		try {
			By ErrorSnackBar = By.xpath("//img[@class='StausClose']");
			if (driver.findElements(ErrorSnackBar).size() > 0) {
				errormessage = driver.findElement(By.xpath("//img[@class='StausClose']/following::span[1]"));
				errormess = errormessage.getText();
				Reporter.log("Status Bar Appear with message: " + errormess);
				Add_Log.info("Status  Bar Appear with message: " + errormess);				
			}
			if (errormess.contains("There is no available COB Date"))
			{
				Reporter.log("No data : " + errormessage.getText());
				Add_Log.info("No data : " + errormessage.getText());
				TestResultStatus.mpaskeysnew.put(methodName(),"No data : " + errormessage.getText());
				Assert.fail();
			}
			else
			{

			}
		} catch (Exception e1) {

		}
	}

	public boolean AggregationCheckcommon(WebDriver driver, String viewName, String actualViews, String ReportName,String Verify)
			throws InterruptedException {

		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		List<String> views = new ArrayList<>();
		CapitalWhtif(driver, viewName, ReportName,Verify);
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
				TestResultStatus.mpaskeysnew.put(methodName(),actualViewsp[i].trim().toString()
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

	public void CapitalWhtif(WebDriver driver, String viewName, String ReportName,String Verify)
			throws InterruptedException {
		String[] viewNamesp = viewName.split("\\,");
		String[] Verifysp = Verify.split("\\,");		
		waitForLoad(driver, 200);
		/*By ele1 = By.xpath("//*[text()='Capital']//parent::div//img[contains(@class,'x-tree-expander')]");
					waitForElementPresent(driver, 120, ele1, " expand Aggregation");
					click(driver, ele1, "by drilling down to " + ReportName + " expand aggregation");
					Thread.sleep(2000);
					waitForLoad(driver, 900);
					By ele2 = By.xpath("//span[text()='Capital']//following::span[text()='What If Analysis']");
					waitForElementPresent(driver, 120, ele2, " expand Aggregation");
					click(driver, ele2, "by drilling down to " + ReportName + " and select " + viewNamesp[0] + " aggregation");
					Thread.sleep(2000);
					waitForLoad(driver, 900);*/		

		By ele1 = By.xpath("//*[text()='" + ReportName + "']//parent::div//img[contains(@class,'x-tree-expander')]");
		waitForElementPresent(driver, 120, ele1, " expand Aggregation");
		click(driver, ele1, "by drilling down to " + ReportName + " expand aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		By ele2 = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewNamesp[0] + "']//parent::div//img[contains(@class,'x-tree-expander')]");
		click(driver, ele2, "by drilling down to " + ReportName + " and select " + viewNamesp[0] + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		By ele3 = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewNamesp[1] + "']");
		click(driver, ele3, "by drilling down to " + ReportName + " and select " + viewNamesp[1] + " aggregation");
		Thread.sleep(2000);
		waitForLoad(driver, 900);

	}

	public void Expandallsubheader(WebDriver driver)
			throws InterruptedException {
		int size1 = driver.findElements(By.xpath("//span[@class='x-tab-inner x-tab-inner-secondary-tabs-elements']")).size();
		for (int i = 1; i <= size1; i++) {
			By expandsecondaryheader = By.xpath("(//span[@class='x-tab-inner x-tab-inner-secondary-tabs-elements'])[" + i + "]");
			click(driver, expandsecondaryheader,"Expand Secondary Header");
			Thread.sleep(2000);
			waitForLoad(driver, 120);
		}

	}

	public void WIAediticon(WebDriver driver, String viewName,String ReportName,String Verify)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		By ele1 = By.xpath("(//label[text()='" + Verifysp[0] + "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-edit '])[1]");
		waitForElementPresent(driver, 120, ele1, " Edit icon for " + Verifysp[0]  );
		click(driver, ele1, Verifysp[0] + " Edit icon");
		Thread.sleep(2000);	

	}
	public void WIAheader(WebDriver driver, String viewName, String actualViews, String ReportName,String Verify)
			throws InterruptedException {
		boolean flag = false;
		String[] actualViewsp = actualViews.split("\\,");
		List<String> views = new ArrayList<>();
		CapitalWhtif(driver, viewName, ReportName,Verify);
		Expandallsubheader(driver);
		int size = actualViewsp.length;
		Thread.sleep(500);
		for (int i = 1; i <= size; i++) {
			views.add(getatt3(driver, By.xpath(
					"(//label[@class='x-component custom-header x-box-item x-component-default x-component-before-title'])[" + i + "]"),
					" Filter criteria Header #" + i));
		}
		System.out.println(views);
		for (int i = 0; i < actualViewsp.length; i++) {
			if (views.get(i).equals(actualViewsp[i].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp[i].trim().toString()
						+ " Filter criteria Header " + ReportName + " "  + viewName);
				Reporter.log("Successfully displayed " + actualViewsp[i].trim().toString()
						+ " Filter criteria Header " + ReportName+ " "  + viewName);
			} else {
				Add_Log.info(actualViewsp[i].trim().toString()
						+ " Filter criteria Header is not displayed after drilling down for " + ReportName+ "- "  + viewName);
				Reporter.log(actualViewsp[i].trim().toString()
						+ " Filter criteria Header is not displayed after drilling down for  " + ReportName+ "- "  + viewName);
				TestResultStatus.mpaskeysnew.put(methodName(),actualViewsp[i].trim().toString()
						+ " Filter criteria Header is not displayed after drilling down for  " + ReportName+ "- "  + viewName);
				flag = true;
			}
		}
		if (flag) {
			TestResultStatus.Testfail = true;
			allFlag = true;
			Assert.fail();
		} else {
			Add_Log.info("Successfully all Filter criteria Header are displayed after drilling down " + ReportName + " - "  + viewName);
			Reporter.log("Successfully all Filter criteria Header are displayed after drilling down " + ReportName + " - "  + viewName);
		}

	}
	public void WIAaddScenariopop(WebDriver driver, String Verify3,String Verify)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");		
		waitForElementPresent(driver, 120, addscenarioname);
		click(driver, addscenarioname);
		Thread.sleep(2000);
		setText(driver,addscenarioname,Verify3sp[0]);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, adddesc);
		click(driver, adddesc);
		Thread.sleep(2000);
		setText(driver,adddesc,Verify3sp[1]);
		Thread.sleep(2000);
		By ele3 = null;
		if(Verify.contains("Portfolio"))
		{
		ele3 = By.xpath("//span[text()='Create']");
		}
		else
		{
		ele3 = By.xpath("//span[text()='Save']");
		}
		System.out.println(ele3);
		System.out.println(Verify);
		waitForElementPresent(driver, 120, ele3, " Save/Create button for  " + Verify  );
		click(driver, ele3, "Save/Create button for " +Verify );
		Thread.sleep(2000);
	}

	public void WIAAddbutton(WebDriver driver, String viewName, String Verify, String ReportName)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		By ele1 = By.xpath("(//label[text()='" + Verifysp[0] + "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-plus '])[1]");
		waitForElementPresent(driver, 120, ele1, " Add icon for " + Verifysp[0]  );
		click(driver, ele1, "by drilling down to " + ReportName + " - " + viewName + " - " +Verifysp[0]+ " Add icon");
		Thread.sleep(2000);						
	}

	public void WIASearchicon(WebDriver driver, String viewName, String Verify, String ReportName)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		By ele1 = By.xpath("(//label[text()='" + Verifysp[0] + "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-search '])[1]");
		waitForElementPresent(driver, 120, ele1, " Search icon for " + Verifysp[0]  );
		click(driver, ele1, "by drilling down to " + ReportName + " - " + viewName + " - " +Verifysp[0] + " Search icon");
		Thread.sleep(2000);						
	}

	public void WIAscenarioverfiy(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Report)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verifysp[0]+"']//following::span[text()='"+Report+"']"));
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
		if(Verify3sp[0].contains(filterscenarioNamea)){
			Add_Log.info("Successfully displayed Created scenario Name = " +  filterscenarioNamea);
			Reporter.log("Successfully displayed Created scenario Name = "+  filterscenarioNamea);
		}
		else
		{
			Add_Log.info("Unable to displayed Created scenario Name = " +  Verify3sp[0]);
			Reporter.log("Unable to displayed Created scenario Name = "+  Verify3sp[0]);
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed Created scenario Name = "+  Verify3sp[0]);
			Assert.fail();
		}
		if(viewName.contains("Banking Book"))
		{
			if(Verifysp[1].equals("Guarantee"))
			{
				By clickguarantee = By.xpath("//span[text()='Guarantee']");
				click(driver, clickguarantee,"Expand Secondary Header Guarantee");
				Thread.sleep(2000);
				waitForLoad(driver, 900);
			}
		}
	}
	public void WIAscenarioverfiynewid(WebDriver driver, String viewName,String ReportName,String Verify3,String Verify,String Report)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verifysp[0]+"']//following::span[text()='"+Report+"']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);	
		By filter = By.xpath("//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "')]//div");
		WebElement filterscenarioName = driver.findElement(filter);
		String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
		System.out.println(filterscenarioNamea);
		System.out.println(Verify3);
		if(Verify3sp[1].contains(filterscenarioNamea) || Verify3sp[0].contains(filterscenarioNamea)){
			Add_Log.info("Successfully displayed Created scenario Name = " +  filterscenarioNamea);
			Reporter.log("Successfully displayed Created scenario Name = "+  filterscenarioNamea);
		}
		else
		{
			Add_Log.info("Unable to displayed Created scenario Name = " +  Verify3sp[1]);
			Reporter.log("Unable to displayed Created scenario Name = "+  Verify3sp[1]);
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed Created scenario Name = "+  Verify3sp[1]);
			Assert.fail();
		}
		try {
		if(Verifysp[1].equals("Guarantee"))
		{
			By clickguarantee = By.xpath("//span[text()='Guarantee']");
			click(driver, clickguarantee,"Expand Secondary Header Guarantee");
			Thread.sleep(2000);
			waitForLoad(driver, 900);
		}
		}
		catch (Exception e1) {
		}
		
	}
	public void WIAaddscenarioCreatenew(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Report)
			throws InterruptedException {

		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIAAddbutton(driver, viewName, Verify, ReportName);
		WIAHeadername(driver, viewName, Verify2, ReportName);
		WIAaddScenariopop(driver, Verify3, Verify);
		WIAscenarioverfiy(driver, viewName, ReportName, Verify2, Verify3, Verify,Report);
	}

	public void WIAaddscenarioSearchtab(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		waitForElementPresent(driver, 120, searchinput);
		click(driver, searchinput);
		Thread.sleep(2000);
		setText(driver,searchinput,Verify3sp[0]);
		waitForElementPresent(driver, 120, searchbutton);
		click(driver, searchbutton);
		Thread.sleep(2000);
		String SearchInputtable = "//div[text()='"+Verify2+"']//following::tr";
		WebPageElements searchinputtable  = new WebPageElements(" Search Input table", "xpath",SearchInputtable);
		waitForElementPresent(driver, 120, searchinputtable);
		By sort = By.xpath("//div[text()='"+Verify2+"']//following::span[contains(@class,'filterswitch')]");
		click(driver, sort,"by drilling down to " + Verify2+ " and click on Sort/Filters");
		click(driver, sortscenariosearchresultid);
		Thread.sleep(2000);
		setText(driver, sortscenariosearchresultid,Verify3sp[1]);
		Thread.sleep(2000);
		waitForLoad(driver, 100);
		String id = "(//div[text()='"+Verify2+"']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]";
		String name = "Radio Button to select the Scenario";
		WebPageElements radiobutton = new WebPageElements(name, "xpath", id);	
		waitForElementPresent1(driver, 120,radiobutton);
		click(driver, radiobutton); 	
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, selectbutton);
		click(driver, selectbutton);
		Thread.sleep(2000);
		try {	
			waitForElementInvisibilty(driver, 40, closeicon);
		}
		catch (Exception e1) {
			waitForElementPresent1(driver, 120,radiobutton);
			click(driver, radiobutton); 	
			Thread.sleep(2000);
			waitForElementPresent(driver, 120, selectbutton);
			click(driver, selectbutton);
			Thread.sleep(2000);
			waitForElementInvisibilty(driver, 40, closeicon);
		}
	}

	public void WIAaddscenarioSearch(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Report)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIASearchicon(driver, viewName, Verify, ReportName);
		WIAHeadername(driver, viewName, Verify2, ReportName);
		WIAaddscenarioSearchtab(driver, viewName, ReportName, Verify2, Verify3, Verify);
		WIAscenarioverfiy(driver, viewName, ReportName, Verify2, Verify3, Verify,Report);
	}

	public void WIASearchFacility(WebDriver driver, String viewName, String Verify, String ReportName)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		By ele1 = By.xpath("(//label[text()='" + Verifysp[0] + "']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-search '])[2]");
		waitForElementPresent(driver, 120, ele1, " Search Facility for " + Verifysp[0]  );
		click(driver, ele1, "by drilling down to " + ReportName + " - " + viewName + " - " +Verifysp[0] + " Search Facility");
		Thread.sleep(2000);						
	}
	public void WIAHeadername(WebDriver driver, String viewName, String Verify2, String ReportName)
			throws InterruptedException {

		By ele1 = By.xpath("//div[text()='"+Verify2 +"']");
		waitForElementPresent(driver, 120, ele1, " Header for " + Verify2  );
		Thread.sleep(2000);						
	}	

	public void WIAaddscenarioSearchtab(WebDriver driver, String Verify2, String Verify3)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");

		Snackbar(driver);
		waitForElementPresent(driver, 120, searchinput);
		click(driver, searchinput);
		Thread.sleep(2000);
		setText(driver,searchinput,Verify3sp[0]);
		waitForElementPresent(driver, 120, searchbutton);
		click(driver, searchbutton);
		Thread.sleep(2000);
		By sort = By.xpath("//div[text()='"+Verify2+"']//following::span[contains(@class,'filterswitch')]");
		click(driver, sort,"by drilling down to " + Verify2+ " and click on Sort/Filters");
		click(driver, sortscenariosearchresultid);
		Thread.sleep(2000);
		setText(driver, sortscenariosearchresultid,Verify3sp[1]);
		Thread.sleep(2000);
		waitForLoad(driver, 100);
		String id = "(//div[text()='"+Verify2+"']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]";
		String name = "Radio Button to select the Scenario";
		WebPageElements radiobutton = new WebPageElements(name, "xpath", id);	
		waitForElementPresent1(driver, 120,radiobutton);
		click(driver, radiobutton); 	
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, selectbutton);
		click(driver, selectbutton);
		Thread.sleep(2000);
		try {	
			waitForElementInvisibilty(driver, 40, closeicon);
		}
		catch (Exception e1) {
			waitForElementPresent1(driver, 120,radiobutton);
			click(driver, radiobutton); 	
			Thread.sleep(2000);
			waitForElementPresent(driver, 120, selectbutton);
			click(driver, selectbutton);
			Thread.sleep(2000);
			waitForElementInvisibilty(driver, 40, closeicon);
		}
	}

	public void WIAPortfolioSearchtab(WebDriver driver, String Verify2, String Verify4,String reportingPeriod)
			throws InterruptedException {

		waitForElementPresent(driver, 120, portoflioinput);
		//COB select
		click(driver,cobselect);
		Thread.sleep(2000);
		By cobvalue = By.xpath("//li[text()='" + reportingPeriod + "']");
		click(driver, cobvalue, reportingPeriod);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		click(driver, portoflioinput);
		Thread.sleep(2000);
		//setText(driver,portoflioinput,Verify4);
		waitForLoad(driver, 900);
		setText(driver, portoflioinput, Verify4);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		//font[text()='HK2151846-1']
		By ele2 = By.xpath("//li/font[text()='"+Verify4+"']");
		waitForElementPresent(driver, 120, ele2, "Drop down value "  );
		click(driver, ele2, "by drilling down to Drop down");

		Thread.sleep(2000);
		waitForLoad(driver, 900);
		String sort2 = "(//label[text()='Search Results']//following::td)[4]";
		By sort21 = By.xpath(sort2);
		Thread.sleep(2000);
		WebPageElements sort2_dll = new WebPageElements(Verify4, "xpath", sort2);
		waitForElementPresent1(driver, 120,sort2_dll);
		WebElement sort2_dlle = driver.findElement(sort21);
		String valuetext = sort2_dlle.getText();
		System.out.println(valuetext);
		if (Verify4.contains(valuetext))
		{
			Add_Log.info("Successfully Portfolio ID " + Verify4 + " Displayed" );			 
			Reporter.log("Successfully Portfolio ID " + Verify4 + " Displayed" );
		}
		else
		{
			Add_Log.info("Unable to displayed " + Verify4 + " Portfolio ID" );			 
			Reporter.log("Unable to displayed " + Verify4 + " Portfolio ID" );	
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed " + Verify4 + " Portfolio ID" );	
			Assert.fail();
		}
		String sorttext = "(//label[text()='Search Results']//following::td)[1]";
		By sorttextpath = By.xpath(sort2);
		click(driver, sorttextpath, "Radio button ");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		click(driver, add_btninside);
		Thread.sleep(2000);
		waitForLoad(driver, 900);	
	}
	public void WIAscenarioVerfiy(WebDriver driver,String Verify3,String Verify,String Report)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verify+"']//following::span[text()='"+Report+"']"));
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
		if(Verify3sp[0].contains(filterscenarioNamea)){
			Add_Log.info("Successfully displayed Created scenario Name = " +  filterscenarioNamea);
			Reporter.log("Successfully displayed Created scenario Name = "+  filterscenarioNamea);
		}
		else
		{
			Add_Log.info("Unable to displayed Created scenario Name = " +  Verify3sp[0]);
			Reporter.log("Unable to displayed Created scenario Name = "+  Verify3sp[0]);
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed Created scenario Name = "+  Verify3sp[0]);
			Assert.fail();
		}
	}
	public void WIATradeCSAverfiy(WebDriver driver)
			throws InterruptedException {

		By TradeView = By.xpath("(//*[text()='Trade View']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]");
		WebElement TradeViewbox = driver.findElement(TradeView);
		if(TradeViewbox.isDisplayed()){
			Add_Log.info("Successfully displayed Trade View table content");
			Reporter.log("Successfully displayed Trade View table content");
		}
		else
		{
			Add_Log.info("Unable to displayed Trade View table content");
			Reporter.log("Unable to displayed Trade View table content");
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed Trade View table content");
			Assert.fail();
		}
		By CSAView = By.xpath("(//*[text()='CSA VIEW']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]");
		WebElement CSAViewbox = driver.findElement(CSAView);
		if(CSAViewbox.isDisplayed()){
			Add_Log.info("Successfully displayed CSA VIEW table content");
			Reporter.log("Successfully displayed CSA VIEW table content");
		}
		else
		{
			Add_Log.info("Unable to displayed CSA VIEW table content");
			Reporter.log("Unable to displayed CSA VIEW table content");
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed CSA VIEW table content");
			Assert.fail();
		}


	}

	public void WIATradeCSAclick(WebDriver driver)
			throws InterruptedException {

		By TradeView1 = By.xpath("(//*[text()='Trade View']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]");
		WebElement TradeViewbox1 = driver.findElement(TradeView1);
		click(driver,TradeView1,"Trade view check box");
		Thread.sleep(2000);
		By CSAView1 = By.xpath("(//*[text()='CSA VIEW']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]");
		WebElement CSAViewbox1 = driver.findElement(CSAView1);
		click(driver,CSAView1,"CSA view check box");



	}

	public void WIAExposureverfiy(WebDriver driver,String Verify,String Report,String Verify4)
			throws InterruptedException {

		WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verify+"']//following::span[text()='"+Report+"']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		By filter = By.xpath("//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div");
		WebElement filterscenarioName = driver.findElement(filter);
		String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
		System.out.println(filterscenarioNamea);
		if(filterscenarioName.isDisplayed()){
			Add_Log.info("Successfully displayed Created/Search "+ Verify + " Key = " +  filterscenarioNamea);
			Reporter.log("Successfully displayed Created/Search "+ Verify + " Key = "+  filterscenarioNamea);
		}
		else
		{
			Add_Log.info("Unable to displayed Created/Search "+ Verify + " Key = " +  Verify4);
			Reporter.log("Unable to displayed Created/Search "+ Verify + " Key = "+  Verify4);
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed Created/Search "+ Verify + " Key = "+  Verify4);
			Assert.fail();
		}
	}
	@SuppressWarnings("unlikely-arg-type")
	public void WIAExposureverfiyequal(WebDriver driver,String Verify,String Report,String Verify4)
			throws InterruptedException {
		//String[] Reportsp = Report.split("\\,");
		if(Verify.equals("Guarantee"))
		{
			By Guaranteeclick = By.xpath("(//span[text()='Guarantee'])[1]");
			click(driver,Guaranteeclick,"Guarantee");
			Thread.sleep(2000);
			waitForLoad(driver, 900);
		}
		WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verify+"']//following::span[text()='"+Report+"']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		By filter = By.xpath("//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div");
		WebElement filterscenarioName = driver.findElement(filter);
		String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
		System.out.println(filterscenarioNamea);
		System.out.println(Verify4);
		if(filterscenarioNamea.equals(Verify4)){
		
			Add_Log.info("Successfully displayed Created/Search "+ Verify + " " + Report+ " Key = " +  filterscenarioNamea);
			Reporter.log("Successfully displayed Created/Search "+ Verify + " " + Report+ " Key = "+  filterscenarioNamea);
		
		}
		else
		{
			Add_Log.info("Unable to displayed Created/Search "+ Verify +" " + Report+  " Key = " +  Verify4);
			Reporter.log("Unable to displayed Created/Search "+ Verify +" " + Report+  " Key = "+  Verify4);
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed Created/Search "+ Verify +" " + Report+  " Key = "+  Verify4);
			Assert.fail();
		}
	}

	public void WIAaddscenarioSearch(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Report, String Verify4, String reportingPeriod)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIASearchicon(driver, viewName, Verifysp[0], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddscenarioSearchtab(driver,Verify2sp[0], Verify3);
		WIAscenarioVerfiy(driver, Verify3, Verifysp[0],Reportsp[0]);
		WIASearchFacility(driver, viewName, Verifysp[0], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[1], ReportName);
		Snackbar(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// COB select
		click(driver, cobselect);
		Thread.sleep(2000);
		By cobvalue = By.xpath("//li[text()='" + reportingPeriod + "']");
		// By cobvalue =By.xpath("(//span[text()='COB']//following::div[contains(@id,'trigger-picker')])[1]//following::li[1]");
		click(driver, cobvalue, reportingPeriod);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		String sort1 = "(//*[text()='SEARCH']//following::span[text()='GFRN'])[1]//following::input[@name='searchInput']";
		By sort = By.xpath(sort1);
		Thread.sleep(2000);
		WebPageElements sort_dll = new WebPageElements(Verify2sp[1], "xpath", sort1);
		WebElement sort_dlle = driver.findElement(sort);
		click(driver, sort,"by drilling down to " + Verify2+ " and click on GFRN Search");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		Thread.sleep(2000);
		setTextenter(driver, sort_dll, Verify4);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		//font[text()='HK2151846-1']
		By eletext = By.xpath("//font[text()='"+Verify4+"']");
		waitForElementPresent(driver, 120, eletext, "text" );
		click(driver, eletext, "text");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		String sort2 = "(//label[text()='FACILITY SEARCH RESULT']//following::td)[2]//div";
		By sort21 = By.xpath(sort2);
		Thread.sleep(2000);
		WebPageElements sort2_dll = new WebPageElements(Verify2sp[1], "xpath", sort2);
		waitForElementPresent1(driver, 120,sort2_dll);
		WebElement sort2_dlle = driver.findElement(sort21);
		String valuetext = sort2_dlle.getText();
		System.out.println(valuetext);
		if (Verify4.contains(valuetext))
		{
			Add_Log.info("Successfully GFRN " + Verify4 + " selected" );			 
			Reporter.log("Successfully GFRN " + Verify4 + " selected" );
		}
		else
		{
			Add_Log.info("Unable GFRN " + Verify4 + " selected" );			 
			Reporter.log("Unable GFRN " + Verify4 + " selected" );
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable GFRN " + Verify4 + " selected" );
			Assert.fail();
		}
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		click(driver, add_btninside);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		WIAExposureverfiy(driver,Verifysp[1],Reportsp[1],Verify4);
		String id = "(//*[text()='Exposure']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]";
		String name = "Radio Button to select the Exposure key";
		WebPageElements radiobutton = new WebPageElements(name, "xpath", id);	
		waitForElementPresent1(driver, 120,radiobutton);
		click(driver, radiobutton); 	
		Thread.sleep(2000);
		String idcoll = "(//*[text()='Collateral']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[2]";
		String namecoll = "Radio Button to select the Collateral key";
		WebPageElements radiobuttoncoll = new WebPageElements(namecoll, "xpath", idcoll);	
		waitForElementPresent1(driver, 120,radiobuttoncoll);
		click(driver, radiobuttoncoll); 	
		Thread.sleep(2000);
		By ele1 = By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small hasIcon icon icon-check-mark-submit ']");
		waitForElementPresent(driver, 120, ele1, " Sumbit icon " );
		click(driver, ele1, "by drilling down to  Eligibility Check Submit icon");
		String filterscenarioNamea = null;
		try {
			By filter = By.xpath("(//label[text()='Eligibility Check']//following::div[@class='x-grid-cell-inner '])[4]");
			click(driver, filter,"Green"); 
			System.out.println(filter);
			WebElement filterscenarioName = driver.findElement(filter);
			filterscenarioNamea = filterscenarioName.getCssValue("background-color");
			System.out.println(filterscenarioNamea);
			Thread.sleep(2000);
		}
		catch (Exception e1) {
			Thread.sleep(2000);	
			By filter = By.xpath("(//label[text()='Eligibility Check']//following::div[@class='x-grid-cell-inner '])[4]");
			click(driver, filter,"Green"); 
			System.out.println(filter);
			WebElement filterscenarioName = driver.findElement(filter);
			filterscenarioNamea = filterscenarioName.getCssValue("background-color");
			System.out.println(filterscenarioNamea);
			Thread.sleep(2000);
		}

		if(filterscenarioNamea.equals("rgba(76, 169, 119, 1)"))
		{
			Add_Log.info("Successfully Eligibility Check" );
			Reporter.log("Successfully Eligibility Check");
		}
		else
		{
			Add_Log.info("Unable to Eligibility Check");
			Reporter.log("Unable to Eligibility Check");
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to Eligibility Check");
			Assert.fail();
		}
		By filter = By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small hasIcon icon icon-calculator ']");
		click(driver, filter,"icon-calculator");
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);

		String idsummary = "(//label[text()='Summary']//following::td)[1]";
		String descsummary = "Desc summary";
		WebPageElements summarydesc = new WebPageElements(descsummary, "xpath", idsummary);
		waitForElementPresent1(driver, 120,summarydesc);
		By idsummarytext = By.xpath(idsummary);
		WebElement summarydescs = driver.findElement(idsummarytext);
		if(summarydescs.isDisplayed())
		{
			Add_Log.info("Successfully summary is displayed" );
			Reporter.log("Successfully summary is displayed" );
		}
		else
		{
			Add_Log.info("Unable to display summary" );
			Reporter.log("Unable to display summary" );
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display summary" );
			Assert.fail();
		}
		Thread.sleep(2000);	
		waitForLoad(driver, 900);
		By expsouretab = By.xpath(("(//span[@class='x-tab-icon-el x-tab-icon-el-secondary-tabs-elements  ']//following::span[text()='Exposure'])[1]"));
		click(driver, expsouretab,"Expsoure tab");
		Thread.sleep(2000);	
		String idexpsoure = "((//label[text()='Exposure'])[2]//following::td)[1]";
		String idexpsouretext = "Exposure tab";
		WebPageElements idexpsourewe = new WebPageElements(idexpsouretext, "xpath", idexpsoure);
		By idexpsoureweby = By.xpath(idexpsoure);
		WebElement idexpsouree = driver.findElement(idexpsoureweby);
		waitForElementPresent1(driver, 120,idexpsourewe);
		if(idexpsouree.isDisplayed())
		{
			Add_Log.info("Successfully Exposure is displayed" );
			Reporter.log("Successfully Exposure is displayed" );
			
		}
		else
		{
			Add_Log.info("Unable to display Exposure" );
			Reporter.log("Unable to display Exposure" );
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display Exposure" );
			Assert.fail();
		}
		Thread.sleep(2000);	
		waitForLoad(driver, 900);
		By mig1tab = By.xpath(("(//span[@class='x-tab-icon-el x-tab-icon-el-secondary-tabs-elements  ']//following::span[text()='Mitigant'])[1]"));
		click(driver, mig1tab,"Mitigant tab");
		String idmig = "(//label[text()='Mitigant']//following::td)[1]";
		String idmigtext = "Mitigant tab";
		WebPageElements idmigwe = new WebPageElements(idmigtext, "xpath", idmig);
		waitForElementPresent1(driver, 120,idmigwe);
		By idmigweby = By.xpath(idmig);
		WebElement idmige = driver.findElement(idmigweby);			
		if(idmige.isDisplayed())
		{
			Add_Log.info("Successfully Mitigant is displayed" );
			Reporter.log("Successfully Mitigant is displayed" );
		}
		else
		{
			Add_Log.info("Unable to display Mitigant" );
			Reporter.log("Unable to display Mitigant" );
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display Mitigant" );
			Assert.fail();
		}	
	}
	public void WIAaddTradeview(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Report, String Verify4,String reportingPeriod)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIAAddbutton(driver, viewName, Verify, ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddScenariopop(driver, Verify3,Verify);	
		WIASearchFacility(driver, viewName, Verify, ReportName);
		WIAPortfolioSearchtab(driver, Verify2,Verify4,reportingPeriod);
		 
		boolean success2 = false;	  
	while (!success2) {
		
			try
			{
				WIATradeCSAverfiy(driver);
				success2 = true;
			}

			catch (Exception e1) {
				try {
					By errorpopup = By.xpath("//div[contains(text(),'System Error Occurred')]");
					WebElement errorpopupbox = driver.findElement(errorpopup);
					String errorpopmessage = errorpopupbox.getText();
					System.out.println(errorpopmessage);
					if(errorpopupbox.isDisplayed()){
						Add_Log.info("Error Popup with message : " + errorpopmessage );
						Reporter.log("Error Popup with message : " + errorpopmessage );
					}
					Thread.sleep(2000);	
					waitForLoad(driver, 900);
					By closepop = By.xpath("//span[text()='OK']");
					click(driver,closepop," click ok on popup meeasge");
					Thread.sleep(2000);	
					waitForLoad(driver, 900);
					WIASearchFacility(driver, viewName, Verify, ReportName);
					WIAPortfolioSearchtab(driver, Verify2,Verify4,reportingPeriod);	
				}
				finally {
				}
			}
		}
		Thread.sleep(2000);	
		waitForLoad(driver, 900);
		WIATradeCSAclick(driver);
		Thread.sleep(2000);	
		waitForLoad(driver, 900);
		By calcutor = By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-run ']");
		click(driver,calcutor," icon-calculator ");
		Thread.sleep(2000);	
		waitForLoad(driver, 900);
		By submitpop = By.xpath("//div[contains(text(),'submitted')]");
		WebElement submitpopbox = driver.findElement(submitpop);
		String submitpopmessage = submitpopbox.getText();
		System.out.println(submitpopmessage);
		if(submitpopbox.isDisplayed()){
			Add_Log.info("Popup with message : " + submitpopmessage );
			Reporter.log("Popup with message : " + submitpopmessage );
		}
		Thread.sleep(2000);	
		waitForLoad(driver, 900);
		By closepop = By.xpath("//span[text()='OK']");
		click(driver,closepop," click ok on popup meeasge");
		Thread.sleep(2000);	
		waitForLoad(driver, 900);
		By checkstatus = By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-refresh ']");
		boolean success = false;
		int count = 0;
		int maxTries = 8;
		while (!success) {	
			try{
				TimeUnit.MINUTES.sleep(5);
				waitForLoad(driver, 900);
				By iconcheckcircle = By.xpath("//div[@class='icon icon-check-circle']");
				WebElement iconcheckcirclebox = driver.findElement(iconcheckcircle);
				if(iconcheckcirclebox.isDisplayed()){
					Add_Log.info("Successfully displayed check icon under status");
					Reporter.log("Successfully displayed check icon under status");
					success = true;	
				}
			}
			catch (Exception e1)  {
				click(driver,checkstatus," icon-refresh");
				Thread.sleep(2000);		
				waitForLoad(driver, 900);
				By iconloading = By.xpath("//div[@class='icon icon-loading']");
				WebElement iconcheckloading = driver.findElement(iconloading);
				if(iconcheckloading.isDisplayed()){
					Add_Log.info("Successfully displayed check icon under status");
					Reporter.log("Successfully displayed check icon under status");
					if (++count == maxTries) throw e1;
				}
				/*
				 * By checkmessagewait = By.
				 * xpath("//div[contains(text(),'Still in progress, please check after some time')]"
				 * ); WebElement checkmsgwait = driver.findElement(checkmessagewait); String
				 * checkmessagepopwait = checkmsgwait.getText();
				 * System.out.println(checkmessagepopwait);
				 * if(checkmessagepopwait.contains("some time")) {
				 * Add_Log.info("Popup with message : " + checkmessagepopwait );
				 * Reporter.log("Popup with message : " + checkmessagepopwait ); }
				 * click(driver,closepop," click ok on popup meeasge");
				 */
				Thread.sleep(2000);	
				waitForLoad(driver, 900);

			}							
		}
		Thread.sleep(2000);	
		waitForLoad(driver, 900);
		By Portfolioresult = By.xpath("(//*[text()='Portfolio Results']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]");
		WebElement Portfolioresultbox = driver.findElement(Portfolioresult);
		if(Portfolioresultbox.isDisplayed()){
			Add_Log.info("Successfully displayed Portfolio Results table content");
			Reporter.log("Successfully displayed Portfolio Results table content");
		}
		else
		{
			Add_Log.info("Unable to displayed Portfolio Results table content");
			Reporter.log("Unable to displayed Portfolio Results table content");
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed Portfolio Results table content");
			Assert.fail();
		}  
		//div[@class='icon icon-check-circle']
		By iconcheckcircle = By.xpath("//div[@class='icon icon-check-circle']");
		WebElement iconcheckcirclebox = driver.findElement(iconcheckcircle);
		if(iconcheckcirclebox.isDisplayed()){
			Add_Log.info("Successfully displayed check icon under status");
			Reporter.log("Successfully displayed check icon under status");
		}
		else
		{
			Add_Log.info("Unable to displayed check icon under status");
			Reporter.log("Unable to displayed check icon under status");
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed check icon under status");
			Assert.fail();
		}  
	}
	public void WIAscenarioverfiydes(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Report)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verifysp[0]+"']//following::span[text()='"+Report+"']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		By filter = By.xpath("//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div");
		WebElement filterscenarioName = driver.findElement(filter);
		String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
		System.out.println(filterscenarioNamea);
		System.out.println(Verify3sp[0]);

		if(filterscenarioNamea.contains(Verify3sp[0])){
			Add_Log.info("Successfully displayed Created scenario Name = " +  filterscenarioNamea);
			Reporter.log("Successfully displayed Created scenario Name = "+  filterscenarioNamea);
		}
		else
		{
			Add_Log.info("Unable to displayed Created scenario Name = " +  Verify3sp[0]);
			Reporter.log("Unable to displayed Created scenario Name = "+  Verify3sp[0]);
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed Created scenario Name = "+  Verify3sp[0]);
			Assert.fail();
		}

	}
	public void WIAEditScenario(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report)
			throws InterruptedException {
		String[] Verify2sp = Verify2.split("\\,");
		String[] Verify3sp = Verify3.split("\\,");
		String[] Reportsp = Report.split("\\,");
		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIASearchicon(driver, viewName, Verify, ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddscenarioSearchtab(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify);
		WIAscenarioverfiy(driver, viewName, ReportName, Verify2, Verify3, Verify,Reportsp[0]);
		WIAediticon(driver, viewName, ReportName,Verify);
		WIAHeadername(driver, viewName, Verify2sp[1], ReportName);
		waitForElementPresent(driver, 120, adddesc);
		click(driver, adddesc);
		Thread.sleep(2000);
		clearText(driver,adddesc);
		Thread.sleep(2000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String scenarioName = Verify3sp[0]+"-"+sdf.format(timestamp);
		setText(driver, adddesc, scenarioName);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		Thread.sleep(2000);
		WIAscenarioverfiydes(driver, viewName, ReportName, Verify2, Verify3, Verify, Reportsp[1]);

	}

	public void WIAaddscenarioSearchfacilty(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Report, String Verify4, String reportingPeriod)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIASearchicon(driver, viewName, Verifysp[0], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddscenarioSearchtab(driver,Verify2sp[0], Verify3);
		WIAscenarioVerfiy(driver, Verify3, Verifysp[0],Reportsp[0]);
		WIASearchFacility(driver, viewName, Verifysp[0], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[1], ReportName);
		Snackbar(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// COB select
		click(driver, cobselect);
		Thread.sleep(2000);
		By cobvalue = By.xpath("//li[text()='" + reportingPeriod + "']");
		click(driver, cobvalue, reportingPeriod);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		String sort1 = "(//*[text()='SEARCH']//following::span[text()='GFRN'])[1]//following::input[@name='searchInput']";
		By sort = By.xpath(sort1);
		Thread.sleep(2000);
		WebPageElements sort_dll = new WebPageElements(Verify2sp[1], "xpath", sort1);
		WebElement sort_dlle = driver.findElement(sort);
		click(driver, sort,"by drilling down to " + Verify2+ " and click on GFRN Search");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		setTextenter(driver, sort_dll, Verify4);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		//font[text()='HK2151846-1']
		By eletext = By.xpath("//font[text()='"+Verify4+"']");
		waitForElementPresent(driver, 120, eletext, "text" );
		click(driver, eletext, "text");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		String sort2 = "(//label[text()='FACILITY SEARCH RESULT']//following::td)[2]//div";
		By sort21 = By.xpath(sort2);
		Thread.sleep(2000);
		WebPageElements sort2_dll = new WebPageElements(Verify2sp[1], "xpath", sort2);
		waitForElementPresent1(driver, 120,sort2_dll);
		WebElement sort2_dlle = driver.findElement(sort21);
		String valuetext = sort2_dlle.getText();
		System.out.println(valuetext);
		if (Verify4.contains(valuetext))
		{
			Add_Log.info("Successfully GFRN " + Verify4 + " selected" );			 
			Reporter.log("Successfully GFRN " + Verify4 + " selected" );
		}
		else
		{
			Add_Log.info("Unable GFRN " + Verify4 + " selected" );			 
			Reporter.log("Unable GFRN " + Verify4 + " selected" );
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable GFRN " + Verify4 + " selected" );
			Assert.fail();
		}
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		click(driver, add_btninside);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		WIAExposureverfiy(driver,Verifysp[1],Reportsp[1],Verify4);
	}
	public void WIASortfilterScenario(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews)
			throws InterruptedException {
		String[] Verify3sp = Verify3.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIASearchicon(driver, viewName, Verify, ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddscenarioSearchtab(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify);
		WIAscenarioverfiy(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify,Reportsp[0]);
		// Sort Filter to be added 
		List<String> views2 = new ArrayList<>();
		// int size1 = driver.findElements(By.xpath(ExposureSUMMARYFIELD)).size();
		List<WebElement> options2 = driver.findElements(By.xpath("(//div[contains(@class,'x-container inline-filter')])[1]//preceding::div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']"));
		for (WebElement ele : options2) {
			views2.add(ele.getAttribute(("data-qtip")));
		}
		System.out.println(views2);

		for (int i1 = 0; i1 < actualViewsp.length; i1++) {

			if (views2.get(i1).equals(actualViewsp[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + views2.get(i1) + " under table row content for " + Verify);
				Reporter.log("Successfully displayed " + views2.get(i1) + " under table row content for " + Verify);
			} else {
				Add_Log.info("Unable to display "+ actualViewsp[i1].trim().toString() + " under table row content for " + Verify);
				Reporter.log("Unable to display "+ actualViewsp[i1].trim().toString() + " under table row content for " + Verify);
				TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display "+ actualViewsp[i1].trim().toString() + " under table row content for " + Verify);
				Assert.fail();
			}
		}
		String sortclick = "(//label[text()='"+Verifysp[0]+"']//following::span[contains(@class,'filterswitch')])[1]";
		By sort = By.xpath(sortclick);
		waitForElementPresent(driver, 120, sort,  " " + Verifysp[0]+ " and click on Sort/Filters");
		click(driver, sort,"by drilling down to " + Verifysp[0]+ " and click on Sort/Filters");
		Thread.sleep(2000);		
		String filterinput5 ="(//span[text()='Scenario ID']//following::div[@class='x-form-text-wrap x-form-text-wrap-default'])[1]/input";
		By filter5 = By.xpath(filterinput5);
		WebPageElements filterinput_dll5 = new WebPageElements("Sort/filiter", "xpath", filterinput5);
		//	click(driver, filter5,"by drilling down to " + Verify+ " and click on input text for Sort/Filters");
		Thread.sleep(2000);
		setTextenter(driver, filterinput_dll5,Verify4);
		Thread.sleep(2000);
		waitForLoad(driver, 100);
		WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verifysp[0]+"']//following::span[text()='Scenario ID']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		//x-grid-cell x-grid-td x-grid-cell-gridcolumn-2924 x-grid-cell-first
		By filtertable = By.xpath("//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + " x-grid-cell-first']//div");
		WebElement filterscenarioName = driver.findElement(filtertable);
		String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
		System.out.println(filterscenarioNamea);
		if(filterscenarioName.isDisplayed()){
			Add_Log.info("Successfully displayed Sorted Scenario ID = " +  filterscenarioNamea);
			Reporter.log("Successfully displayed Sorted Scenario ID = "+  filterscenarioNamea);
		}
		else
		{
			Add_Log.info("Unable to displayed Sorted Scenario ID  = " +  Verify4);
			Reporter.log("Unable to displayed Sorted Scenario ID  = "+  Verify4);
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed Sorted Scenario ID  = "+  Verify4);
			Assert.fail();
		}
	}

	public void WIAExpsoureCreate(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIASearchicon(driver, viewName, Verifysp[0], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddscenarioSearchtab(driver, viewName, ReportName, Verify2sp[0], Verify3, Verifysp[0]);
		WIAscenarioverfiy(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify,Reportsp[0]);
		WIAAddbutton(driver,viewName,Verifysp[1], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[1], ReportName);
		WIAExpsCreateinside(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report, actualViews, actualViews2);
		WIAAddbutton(driver,viewName,Verifysp[1], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[1], ReportName);
		WIAExpsCreateinside(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report, actualViews, actualViews2);
		WIAAddbutton(driver,viewName,Verifysp[1], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[1], ReportName);
		WIAExpsCreateinside(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report, actualViews, actualViews2);
		WIAExpsCreateverfiymultiple(driver, Verify3,Verify,Report);
	}


	public void WIAExpsCreateinside(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		List<String> views2 = new ArrayList<>();

		List<WebElement> options2 = driver.findElements(By.xpath("//div[text()='"+Verify2sp[1]+"']//following::label[contains(@class,'x-form-item-label x-form-item-label-default  x-form-item-label-top x-unselectable')] | //div[@class='x-title x-btn-group-header-title x-btn-group-header-title-default x-box-item x-title-default x-title-rotate-none x-title-align-left']"));
		for (WebElement ele : options2) {
			views2.add(ele.getAttribute(("textContent")));
		}
		System.out.println(views2);
		for (int i1 = 0; i1 < actualViewsp.length; i1++) {

			if (views2.get(i1).equals(actualViewsp[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + views2.get(i1) + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
				Reporter.log("Successfully displayed " + views2.get(i1) + " under " + Verify2sp[1] + " all tabs  " + Verifysp[1]);
			} else {
				Add_Log.info("Unable to display "+ actualViewsp[i1].trim().toString() + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
				Reporter.log("Unable to display "+ actualViewsp[i1].trim().toString() + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
				TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display "+ actualViewsp[i1].trim().toString() + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
				Assert.fail();
			}
		}
		views2.remove("Is Exposure Default");
		views2.remove("Volcker Covered Fund Flag");
		views2.remove("Asset Class");
		System.out.println(views2);
		String aggreationfilter= null;
		String inputtextsearch1 = null;
		for (int i1 = 0; i1 < actualViewsp2.length; i1++) {
		}
		for (int i = 0; i < views2.size(); i++)  {

			System.out.print(views2.get(i) + " "+ actualViewsp2[i].trim().toString());
			if(views2.get(i).equals("Parent Customer Id (GFPID)") ||views2.get(i).equals("Customer Id (GFCID)") ||views2.get(i).equals("GAAP Amount USD") )
			{
				aggreationfilter = "(//div[text()='"+Verify2sp[1] +"']//following::span[text()='"+views2.get(i)+"']//following::input)[1]";
				inputtextsearch1 = "(//div[text()='"+Verify2sp[1] +"']//following::span[text()='"+views2.get(i)+"']//following::input)[1]";
			}
			else
			{
				aggreationfilter = "(//div[text()='"+Verify2sp[1] +"']//following::span[text()='"+views2.get(i)+"']//following::div[contains(@id,'trigger-picker')])[1]";
				inputtextsearch1 = "(//div[text()='"+Verify2sp[1] +"']//following::span[text()='"+views2.get(i)+"']//following::input)[1]";
			}

			WebElement aggreationfilterweb = driver.findElement(By.xpath(aggreationfilter));
			By aggreationfilterid = (By.xpath(aggreationfilter));
			WebDriverWait wait = new WebDriverWait(driver, 120);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
			click(driver, aggreationfilterid, views2.get(i));
			WebPageElements inputtextfeildid = new WebPageElements(actualViewsp2[i].trim().toString(), "xpath", inputtextsearch1);
			clearText(driver, inputtextfeildid);
			Thread.sleep(1000);
			setTextenter(driver, inputtextfeildid, actualViewsp2[i]);
			Thread.sleep(1000);
		}

		Thread.sleep(1000);
		waitForLoad(driver, 120);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		Thread.sleep(2000);
	}

	public void WIAExpsCreateverfiymultiple(WebDriver driver, String Verify3,String Verify,String Report)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");
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
		System.out.println(filterscenarioNamea);
		System.out.println(Verify3);
		if(filterscenarioNamea.isEmpty()) {
			Add_Log.info("Unable to displayed Created " + Verifysp[1] );
			Reporter.log("Unable to displayed Created " + Verifysp[1] );
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed Created " + Verifysp[1] );
			Assert.fail();
		}
		else
		{	
			Add_Log.info("Successfully displayed Created " + Verifysp[1] + " = " +  views);
			Reporter.log("Successfully displayed Created " + Verifysp[1] + " = " +  views);

		}
	}

	public void WIAExpsoureCreatedelete(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");
		WIAExpsoureCreate(driver, viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By ele1 = By.xpath("(//div[@class='x-column-header x-column-header-checkbox x-column-header-align-left x-box-item x-column-header-coreselectcolumn x-unselectable x-column-header-first'])[1]");
		waitForElementPresent(driver, 120, ele1, " Check box for all item " + Verifysp[1]  );
		click(driver, ele1,  " Check box for " + Verifysp[1]);
		Thread.sleep(1000);
		waitForLoad(driver, 120);
		By ele2 = By.xpath("(//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-trash '])[1]");
		waitForElementPresent(driver, 120, ele1, " Delete icon" + Verifysp[1]  );
		click(driver, ele2,  " Delete icon " + Verifysp[1]);
		Thread.sleep(1000);
		waitForLoad(driver, 120);

		WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verifysp[1]+"']//following::span[text()='"+Reportsp[1]+"']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		String xpathdivtable = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div";
		By filter = By.xpath(xpathdivtable);
		waitForInVisiblility(driver, 120,filter,"Empty table content after deleting all " +Verifysp[1]);			
	}
	public void WIAExpsoureCreatedownload(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2, String FileNameac, String Fileformat)
			throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");

		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
		File dir = new File(downloadPath);

		for (File file : dir.listFiles()) {
			if (file.getName().startsWith(FileNameac)) {
				file.delete();
				Reporter.log("Successfully Deleted Existing File before clicking on download");
				Add_Log.info("Successfully Deleted Existing File before clicking on download");
				Thread.sleep(1000);
			}
		}
		WIAExpsoureCreate(driver, viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);

		Thread.sleep(2000);
		waitForLoad(driver, 120);
		By ele1 = By.xpath("(//label[text()='Exposure']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-excel '])[1]");
		waitForElementPresent(driver, 120, ele1, " Download icon" + Verifysp[1]  );
		clicknosleep(driver, ele1,  " Download icon " + Verifysp[1]);
		downloadfilecommonreg(driver,ReportName, Verify,Fileformat,FileNameac);

	}

	public void WIAExpsoureCreatedownloadselect(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2, String FileNameac, String Fileformat)
			throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");

		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
		File dir = new File(downloadPath);

		for (File file : dir.listFiles()) {
			if (file.getName().startsWith(FileNameac)) {
				file.delete();
				Reporter.log("Successfully Deleted Existing File before clicking on download");
				Add_Log.info("Successfully Deleted Existing File before clicking on download");
				Thread.sleep(1000);
			}
		}
		WIAExpsoureCreate(driver, viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);


		By ele1 = By.xpath("(//label[text()='"+Verifysp[1]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-download '])[1]");
		waitForElementPresent(driver, 120, ele1, " Download icon" + Verifysp[1]  );
		clicknosleep(driver, ele1,  " Download icon " + Verifysp[1]);
		Thread.sleep(2000);	
		waitForLoad(driver, 900);
		By closepop = By.xpath("//span[text()='OK']");
		click(driver,closepop," click ok on popup meeasge");
		WebElement closewindow = driver.findElement(closepop);
		if (closewindow.isDisplayed())
		{
			click(driver,closepop," click ok on popup meeasge");
		}
		else
		{
			Reporter.log("Able to download Expsoure file without selecting Expsoure key using select download icon");
			Add_Log.info("Able to download Expsoure file without selecting Expsoure key using select download icon");
			TestResultStatus.mpaskeysnew.put(methodName(),"Able to download Expsoure file without selecting Expsoure key using select download icon");
			Assert.fail();
		}


		By ele2 = By.xpath("(//label[text()='"+Verifysp[1]+"']//following::div[@class='x-grid-row-checker'])[1]");
		waitForElementPresent(driver, 120, ele2, " Check box for 1st Key " + Verifysp[1]  );
		click(driver, ele2,  "  Check box for 1st Key " + Verifysp[1]);		
		Thread.sleep(2000);
		waitForLoad(driver, 120);                                                       
		By ele3 = By.xpath("(//label[text()='"+Verifysp[1]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-download '])[1]");
		waitForElementPresent(driver, 120, ele3, " Download icon" + Verifysp[1]  );
		clicknosleep(driver, ele3,  " Download icon " + Verifysp[1]);
		downloadfilecommonreg(driver,ReportName, Verify,Fileformat,FileNameac);

	}

	public void downloadfilecommonreg(WebDriver driver, String ReportName, String Verify,
			String Fileformat,String FileNameac)
					throws InterruptedException, IOException {

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
			TestResultStatus.mpaskeysnew.put(methodName(),Fileformat + "if satatment  ******report not downloaded for " + Verifysp[1] + " " + ReportName
					+ " report");
		}


	}



	public void WIAExpsoureSortfilter(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");

		String[] Verify2sp = Verify2.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");


		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIASearchicon(driver, viewName, Verifysp[0], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddscenarioSearchtab(driver, viewName, ReportName, Verify2sp[0], Verify3, Verifysp[0]);
		WIAscenarioverfiy(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify,Reportsp[0]);
		String sortclick = "//label[text()='"+Verifysp[1]+"']//following::span[contains(@class,'filterswitch')]";
		By sort = By.xpath(sortclick);
		click(driver, sort,"by drilling down to " + Verifysp[1]+ " and click on Sort/Filters");
		Thread.sleep(2000);	

		String filterinput ="(//span[text()='"+Reportsp[1]+"']//following::input[@class='operatorValueFieldCls x-form-text x-form-text-default  '])[2]";
		By filter = By.xpath(filterinput);
		WebPageElements filterinput_dll = new WebPageElements("Sort/filiter", "xpath", filterinput);
		setTextenter(driver, filterinput_dll,Verify4);
		Thread.sleep(2000);
		waitForLoad(driver, 100);
		WIAExposureverfiy(driver,Verifysp[1],Reportsp[1],Verify4);

		String ExposureSUMMARYFIELD = "//label[text()='"+Verifysp[1]+"']//following::div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']";
		WebPageElements exposuresummaryfield = new WebPageElements("Exposure Summary filter", "xpath",
				ExposureSUMMARYFIELD);
		List<String> views = new ArrayList<>();
		waitForElementPresent(driver, 30, exposuresummaryfield);
		for (int i = 1; i < actualViewsp.length+1; i++) {
			views.add(getatt(driver, By.xpath("(" + ExposureSUMMARYFIELD + ")[" + i + "]"), "  Exposure  table Feild Content #" + i));	
		}
		System.out.println(views);
		for (int i1 = 0; i1 < actualViewsp.length; i1++) {
			if (views.get(i1).equals(actualViewsp[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp[i1].trim().toString() + "= " +Verifysp[1] +" table content"); 
				Reporter.log("Successfully displayed " + actualViewsp[i1].trim().toString() + "= " +Verifysp[1] +" table content"); 

			} else {
				Add_Log.info("Unable to displayed " +actualViewsp[i1].trim().toString()+ "= " +Verifysp[1] +" table content"); 
				Reporter.log("Unable to displayed " +actualViewsp[i1].trim().toString()+ "= " +Verifysp[1] +" table content"); 					
				TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed " +actualViewsp[i1].trim().toString()+ "= " +Verifysp[1] +" table content"); 					
			}
		}	 
	}
	public void WIAExpsoureSearchnew(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String reportingPeriod)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIASearchicon(driver, viewName, Verifysp[0], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddscenarioSearchtab(driver, viewName, ReportName, Verify2sp[0], Verify3, Verifysp[0]);
		WIAscenarioverfiynewid(driver,viewName,ReportName,Verify3,Verifysp[0],Reportsp[0]);
		WIASearchicon(driver,viewName, Verifysp[1],ReportName);
		WIAHeadername(driver, viewName, Verify2sp[1], ReportName);
		Snackbar(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// COB select
		click(driver, cobselect);
		Thread.sleep(2000);
		By cobvalue = By.xpath("//li[text()='" + reportingPeriod + "']");
		click(driver, cobvalue, reportingPeriod);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
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
		try {
			By eletext = By.xpath("//font[text()='" + Verify4 + "']");
			waitForElementPresent(driver, 30, eletext, "text");
			click(driver, eletext, "text");
			Thread.sleep(2000);
			waitForLoad(driver, 900);
			String sort2 = "(//label[text()='FACILITY SEARCH RESULT']//following::td)[2]//div";
			By sort21 = By.xpath(sort2);
			Thread.sleep(2000);
			WebPageElements sort2_dll = new WebPageElements("FACILITY SEARCH RESULT", "xpath", sort2);
			waitForElementPresent_noAs(driver, 120, sort2_dll);
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
	
		WIAExposureverfiy(driver,Verifysp[1],Reportsp[1],Verify4);
	}
	public void WIAExpsoureCreateEdit(WebDriver driver, String Verify2,String actualViews, String actualViews2)
			throws InterruptedException, IOException {

		String[] Verify2sp = Verify2.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");

		String aggreationfilter = null;
		String inputtextsearch1 = null;
		for (int i1 = 0; i1 < actualViewsp.length; i1++) {
			aggreationfilter = "(//div[text()='"+Verify2 +"']//following::span[text()='"+actualViewsp[i1].trim().toString()+"']//following::div[contains(@id,'trigger-picker')])[1]";
			inputtextsearch1 = "(//div[text()='"+Verify2 +"']//following::span[text()='"+actualViewsp[i1].trim().toString()+"']//following::input)[1]";

			WebElement aggreationfilterweb = driver.findElement(By.xpath(aggreationfilter));
			By aggreationfilterid = (By.xpath(aggreationfilter));
			WebDriverWait wait = new WebDriverWait(driver, 120);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
			click(driver, aggreationfilterid, actualViewsp[i1].trim().toString());
			WebPageElements inputtextfeildid = new WebPageElements(actualViewsp2[i1].trim().toString(), "xpath", inputtextsearch1);
			clearText(driver, inputtextfeildid);
			Thread.sleep(1000);
			setTextenter(driver, inputtextfeildid, actualViewsp2[i1]);
			Thread.sleep(1000);
		}
		Thread.sleep(1000);
		waitForLoad(driver, 120);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		Thread.sleep(4000);
		waitForLoad(driver, 120);
		try {
			WebElement errormessage= null;
			String errormess =null;
			By Errorpopup = By.xpath("//div[@class='x-window x-message-box x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box']");
			//WebElement Errorpopupbox = driver.findElement(Errorpopup);
			WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.elementToBeClickable(Errorpopup));
		By ErrorSnackBar = By.xpath("//div[@class='x-window x-message-box x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box']//following::div[@class='x-component x-window-text x-box-item x-component-default']");
		if (driver.findElements(ErrorSnackBar).size() > 0) {
			errormessage = driver.findElement(By.xpath("//div[@class='x-window x-message-box x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box']//following::div[@class='x-component x-window-text x-box-item x-component-default']"));
			errormess = errormessage.getText();
			Reporter.log("message: " + errormess);
			Add_Log.info("message: " + errormess);	
			TestResultStatus.mpaskeysnew.put(methodName(),"message: " + errormess);
			Assert.fail();
		}
		}
		catch (Exception e1)
		{
		
		}
		Thread.sleep(2000);
		
		waitForLoad(driver, 120);
	}
	public void WIAExpeditverfiygurat(WebDriver driver, String Verify4,String Verify, String ID)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify4sp = Verify4.split("\\,");
		String[] IDsp = ID.split("\\,");
		List<String> views = new ArrayList<>();
		for (int i = 0; i < IDsp.length; i++) {
			WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verifysp[1]+"']//following::span[text()='"+IDsp[i]+"']"));
			System.out.println(extractxpathid);
			String pathnum = extractxpathid.getAttribute("id");
			String[] xpathid = pathnum.split("-");
			System.out.println(xpathid[0]);
			System.out.println(xpathid[1]);
			System.out.println(xpathid[2]);

			String xpathdivtable = "(//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div)[1]";
			By filter = By.xpath(xpathdivtable);
			System.out.println(filter);
			WebElement filterscenarioName = driver.findElement(filter);
			String filterscenarioNamea = filterscenarioName.getAttribute("textContent");
			List<WebElement> options2 = driver.findElements(filter);
			for (WebElement ele : options2) {
				views.add(ele.getAttribute(("textContent")));
			}
		}
		System.out.println(views);
		

		for (int i1 = 0; i1 < Verify4sp.length-1; i1++) {
				if(Verify4sp[i1].trim().toString().contains(views.get(i1))) {
				Add_Log.info("Successfully able to Edit Exposure " + Verify4sp[i1] + " = " +  views.get(i1));
				Reporter.log("Successfully able to Edit Exposure" + Verify4sp[i1] + " = " +  views.get(i1));
			}
			else
			{	
				Add_Log.info("Uableto Edit Exposure " + Verify4sp[i1] + " = " +  views.get(i1));
				Reporter.log("Uableto Edit Exposure " + Verify4sp[i1] + " = " +  views.get(i1));
				TestResultStatus.mpaskeysnew.put(methodName(),"Uableto Edit Exposure " + Verify4sp[i1] + " = " +  views.get(i1));
				Assert.fail();
			}
		}
	}

	public void WIAExpeditverfiy(WebDriver driver, String Verify4,String Verify, String ID)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify4sp = Verify4.split("\\,");
		String[] IDsp = ID.split("\\,");
		List<String> views = new ArrayList<>();
		for (int i = 0; i < IDsp.length; i++) {
			WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verifysp[1]+"']//following::span[text()='"+IDsp[i]+"']"));
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
		}
		System.out.println(views);
		System.out.println(views.toString());

		for (int i1 = 0; i1 < Verify4sp.length-1; i1++) {
			if (views.get(i1).equals(Verify4sp[i1].trim().toString())) {

				Add_Log.info("Successfully able to Edit Exposure " + Verify4sp[i1] + " = " +  views.get(i1));
				Reporter.log("Successfully able to Edit Exposure" + Verify4sp[i1] + " = " +  views.get(i1));
			}
			else
			{	
				Add_Log.info("Uableto Edit Exposure " + Verify4sp[i1] + " = " +  views.get(i1));
				Reporter.log("Uableto Edit Exposure " + Verify4sp[i1] + " = " +  views.get(i1));
				TestResultStatus.mpaskeysnew.put(methodName(),"Uableto Edit Exposure " + Verify4sp[i1] + " = " +  views.get(i1));
				Assert.fail();
			}
		}
	}

	public void WIAExpsoureEdit(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2, String ID)
			throws InterruptedException, IOException {

		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] Verify4sp = Verify4.split("\\,");

		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIASearchicon(driver, viewName, Verifysp[0], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddscenarioSearchtab(driver, viewName, ReportName, Verify2sp[0], Verify3, Verifysp[0]);
		WIAscenarioverfiy(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify,Reportsp[0]);
		try {

			WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verifysp[1]+"']//following::span[text()='"+Reportsp[1]+"']"));
			String pathnum = extractxpathid.getAttribute("id");
			String[] xpathid = pathnum.split("-");
			System.out.println(xpathid[0]);
			System.out.println(xpathid[1]);
			System.out.println(xpathid[2]);

			String xpathdivtable = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div";
			By filter = By.xpath(xpathdivtable);
			WebElement filterscenarioName = driver.findElement(filter);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOf(filterscenarioName));

		}
		catch (Exception e1)
		{
			By ele1 = By.xpath("(//div[@class='x-column-header x-column-header-checkbox x-column-header-align-left x-box-item x-column-header-coreselectcolumn x-unselectable x-column-header-first'])[1]");
			waitForElementPresent(driver, 120, ele1, " Check box for all item " + Verifysp[1]  );
			click(driver, ele1,  " Check box for " + Verifysp[1]);
			Thread.sleep(1000);
			waitForLoad(driver, 120);
			By ele2 = By.xpath("(//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-trash '])[1]");
			waitForElementPresent(driver, 120, ele1, " Delete icon" + Verifysp[1]  );
			click(driver, ele2,  " Delete icon " + Verifysp[1]);
			Thread.sleep(1000);
			waitForLoad(driver, 120);

			WebElement extractxpathid6 = driver.findElement(By.xpath("//label[text()='"+Verifysp[1]+"']//following::span[text()='"+Reportsp[1]+"']"));
			String pathnum6 = extractxpathid6.getAttribute("id");
			String[] xpathid6 = pathnum6.split("-");
			System.out.println(xpathid6[0]);
			System.out.println(xpathid6[1]);
			System.out.println(xpathid6[2]);
			String xpathdivtable6 = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid6[1] + "']//div";
			By filter6 = By.xpath(xpathdivtable6);
			waitForInVisiblility(driver, 120,filter6,"Empty table content after deleting all " +Verifysp[1]);			
		}

		WIAAddbutton(driver,viewName,Verifysp[1], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[1], ReportName);
		WIAExpsoureCreateEdit(driver, Verify2sp[1], actualViews, actualViews2);
		By ele1 = By.xpath("//div[@class='icon icon-edit-advanced']");
		waitForElementPresent(driver, 120, ele1, " Edit icon" + Verifysp[1]  );
		click(driver, ele1,  " Edit icon" + Verifysp[1]);
		WIAHeadername(driver, viewName, "EDIT EXPOSURE", ReportName);
		WIAExpsoureCreateEdit(driver, "EDIT EXPOSURE", actualViews, Verify4);
		Thread.sleep(1000);
		waitForLoad(driver, 120);
		WIAExpeditverfiy(driver, Verify4, Verify, ID);
	}

	//////////////////////////////////////////////////////////

	public void WIAcollCreateinside(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		List<String> views2 = new ArrayList<>();

	//	List<WebElement> options2 = driver.findElements(By.xpath("//div[text()='"+Verify2sp[1]+"']//following::label[contains(@class,'x-form-item-label x-form-item-label-default  x-form-item-label-top x-unselectable')] | //div[@class='x-title x-btn-group-header-title x-btn-group-header-title-default x-box-item x-title-default x-title-rotate-none x-title-align-left']"));
		List<WebElement> options2 = driver.findElements(By.xpath("//div[text()='"+Verify2sp[1]+"']//following::label[contains(@class,'mandatoryLabel')]|//div[text()='"+Verify2sp[1]+"']//following::label[contains(@class,'x-form-item-label x-form-item-label-default  x-form-item-label-top x-unselectable')]"));
		System.out.println(options2);
		for (WebElement ele : options2) {
			views2.add(ele.getAttribute(("textContent")));
		}
		System.out.println(views2);
		for (int i1 = 0; i1 < actualViewsp.length; i1++) {

			if (views2.get(i1).equals(actualViewsp[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + views2.get(i1) + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
				Reporter.log("Successfully displayed " + views2.get(i1) + " under " + Verify2sp[1] + " all tabs  " + Verifysp[1]);
			} else {
				Add_Log.info("Unable to display "+ actualViewsp[i1].trim().toString() + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
				Reporter.log("Unable to display "+ actualViewsp[i1].trim().toString() + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
				TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display "+ actualViewsp[i1].trim().toString() + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
				Assert.fail();
			}
		}
		if(Verifysp[1].equals("Collateral"))
		{
			views2.remove("Is First Liened");
			views2.remove("Legal Enforceability Certainty");
			System.out.println(views2);
		}
		if(Verifysp[1].equals("Guarantee"))
		{
			views2.remove("Is Guarantor an Insurance Company");
			views2.remove("Legal Certainty");
			views2.remove("Is Guarantor Investment Grade");
			views2.remove("Guarantors Asset Class");
			System.out.println(views2);
		}

		String aggreationfilter= null;
		String inputtextsearch1 = null;
		for (int i1 = 0; i1 < actualViewsp2.length; i1++) {

		}
		for (int i = 0; i < views2.size(); i++)  {

			System.out.print(views2.get(i) + "  "+ actualViewsp2[i] + "  ");

			if(views2.get(i).equals("Instrument Id Type") ||views2.get(i).equals("Instrument Id") ||views2.get(i).equals("Parent Customer ID") ||views2.get(i).equals("Customer ID") ||views2.get(i).equals("Mitigant Value") )
			{
				aggreationfilter = "(//div[text()='"+Verify2sp[1] +"']//following::span[text()='"+views2.get(i)+"']//following::input)[1]";
				inputtextsearch1 = "(//div[text()='"+Verify2sp[1] +"']//following::span[text()='"+views2.get(i)+"']//following::input)[1]";
			}
			else
			{
				aggreationfilter = "(//div[text()='"+Verify2sp[1] +"']//following::span[text()='"+views2.get(i)+"']//following::div[contains(@id,'trigger-picker')])[1]";
				inputtextsearch1 = "(//div[text()='"+Verify2sp[1] +"']//following::span[text()='"+views2.get(i)+"']//following::input)[1]";
			}

			WebElement aggreationfilterweb = driver.findElement(By.xpath(aggreationfilter));
			By aggreationfilterid = (By.xpath(aggreationfilter));
			WebDriverWait wait = new WebDriverWait(driver, 120);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
			click(driver, aggreationfilterid, views2.get(i));
			WebPageElements inputtextfeildid = new WebPageElements(views2.get(i), "xpath", inputtextsearch1);
			clearText(driver, inputtextfeildid);
			Thread.sleep(1000);
			setTextenter(driver, inputtextfeildid, actualViewsp2[i]);
			Thread.sleep(1000);
		}

		Thread.sleep(1000);
		waitForLoad(driver, 120);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		Thread.sleep(2000);

	}

	public void WIACollCreate(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIASearchicon(driver, viewName, Verifysp[0], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddscenarioSearchtab(driver, viewName, ReportName, Verify2sp[0], Verify3, Verifysp[0]);
		WIAscenarioverfiy(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify,Reportsp[0]);
		WIAAddbutton(driver,viewName,Verifysp[1], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[1], ReportName);
		WIAcollCreateinside(driver,viewName,ReportName,Verify2,Verify3,Verify,Verify4,Report, actualViews,actualViews2);
		WIAAddbutton(driver,viewName,Verifysp[1], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[1], ReportName);
		WIAcollCreateinside(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report, actualViews, actualViews2);
		WIAAddbutton(driver,viewName,Verifysp[1], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[1], ReportName);
		WIAcollCreateinside(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report, actualViews, actualViews2);
		WIAExpsCreateverfiymultiple(driver, Verify3,Verify,Report);
	}

	public void WIACollCreatedelete(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");
		WIACollCreate(driver, viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		if(Verifysp[1].equals("Collateral")) {
			By ele1 = By.xpath("(//div[@class='x-column-header x-column-header-checkbox x-column-header-align-left x-box-item x-column-header-coreselectcolumn x-unselectable x-column-header-first'])[2]");
			waitForElementPresent(driver, 120, ele1, " Check box for all item " + Verifysp[1]  );
			click(driver, ele1,  " Check box for " + Verifysp[1]);
			Thread.sleep(1000);
			waitForLoad(driver, 120);
			By ele2 = By.xpath("(//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-trash '])[2]");
			waitForElementPresent(driver, 30, ele2, " Delete icon" + Verifysp[1]  );
			click(driver, ele2,  " Delete icon " + Verifysp[1]);
			Thread.sleep(1000);
			waitForLoad(driver, 120);
		}
		if(Verifysp[1].equals("Guarantee"))
		{
			By ele1 = By.xpath("(//div[@class='x-column-header x-column-header-checkbox x-column-header-align-left x-box-item x-column-header-coreselectcolumn x-unselectable x-column-header-first'])[3]");
			waitForElementPresent(driver, 120, ele1, " Check box for all item " + Verifysp[1]  );
			click(driver, ele1,  " Check box for " + Verifysp[1]);
			Thread.sleep(1000);
			waitForLoad(driver, 120);
			By ele2 = By.xpath("(//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-trash '])[3]");
			waitForElementPresent(driver, 30, ele2, " Delete icon" + Verifysp[1]  );
			click(driver, ele2,  " Delete icon " + Verifysp[1]);
			Thread.sleep(1000);
			waitForLoad(driver, 120);
		}
		WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verifysp[1]+"']//following::span[text()='"+Reportsp[1]+"']"));
		String pathnum = extractxpathid.getAttribute("id");
		String[] xpathid = pathnum.split("-");
		System.out.println(xpathid[0]);
		System.out.println(xpathid[1]);
		System.out.println(xpathid[2]);
		String xpathdivtable = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div";
		By filter = By.xpath(xpathdivtable);
		waitForInVisiblility(driver, 60,filter,"Empty table content after deleting all " +Verifysp[1]);			
	}

	public void WIACollCreatedownload(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2, String FileNameac, String Fileformat)
			throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");

		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
		File dir = new File(downloadPath);

		for (File file : dir.listFiles()) {
			if (file.getName().startsWith(FileNameac)) {
				//file.delete();
			//	Reporter.log("Successfully Deleted Existing File before clicking on download");
			//	Add_Log.info("Successfully Deleted Existing File before clicking on download");
				Thread.sleep(1000);
			}
		}
		WIACollCreate(driver, viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);
		Thread.sleep(2000);
		waitForLoad(driver, 120);
		if(Verifysp[1].equals("Collateral"))
		{
			By ele1 = By.xpath("(//label[text()='Exposure']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-excel '])[2]");
			waitForElementPresent(driver, 120, ele1, " Download icon" + Verifysp[1]  );
			clicknosleep(driver, ele1,  " Download icon " + Verifysp[1]);
		}
		if(Verifysp[1].equals("Guarantee"))
		{
			By ele1 = By.xpath("(//label[text()='Exposure']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-excel '])[3]");
			waitForElementPresent(driver, 120, ele1, " Download icon" + Verifysp[1]  );
			clicknosleep(driver, ele1,  " Download icon " + Verifysp[1]);	
		}
		downloadfilecommonreg(driver,ReportName, Verify,Fileformat,FileNameac);

	}
	public void WIACollCreatedownloadselect(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2, String FileNameac, String Fileformat)
			throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");

		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
		File dir = new File(downloadPath);

		for (File file : dir.listFiles()) {
			if (file.getName().startsWith(FileNameac)) {
				file.delete();
				Reporter.log("Successfully Deleted Existing File before clicking on download");
				Add_Log.info("Successfully Deleted Existing File before clicking on download");
				Thread.sleep(1000);
			}
		}
		WIACollCreate(driver, viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);


		By ele1 = By.xpath("(//label[text()='"+Verifysp[1]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-download '])[1]");
		waitForElementPresent(driver, 120, ele1, " Download icon" + Verifysp[1]  );
		click(driver, ele1,  " Download icon " + Verifysp[1]);
		Thread.sleep(2000);	
		waitForLoad(driver, 900);
		By closepop = By.xpath("//span[text()='OK']");
		click(driver,closepop," click ok on popup meeasge");
		WebElement closewindow = driver.findElement(closepop);
		if (closewindow.isDisplayed())
		{
			click(driver,closepop," click ok on popup meeasge");
		}
		else
		{
			Reporter.log("Able to download Collateral file without selecting Mitigant key using select download icon");
			Add_Log.info("Able to download Collateral file without selecting Mitigant key using select download icon");
			TestResultStatus.mpaskeysnew.put(methodName(),"Able to download Collateral file without selecting Mitigant key using select download icon");
			Assert.fail();
		}


		By ele2 = By.xpath("(//label[text()='"+Verifysp[1]+"']//following::div[@class='x-grid-row-checker'])[1]");
		waitForElementPresent(driver, 120, ele2, " Check box for 1st Key " + Verifysp[1]  );
		click(driver, ele2,  "  Check box for 1st Key " + Verifysp[1]);		
		Thread.sleep(2000);
		waitForLoad(driver, 120);                                                       
		By ele3 = By.xpath("(//label[text()='"+Verifysp[1]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-download '])[2]");
		waitForElementPresent(driver, 120, ele3, " Download icon" + Verifysp[1]  );
		clicknosleep(driver, ele3,  " Download icon " + Verifysp[1]);
		downloadfilecommonreg(driver,ReportName, Verify,Fileformat,FileNameac);

	}

	public void WIACollSortfilter(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");
		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIASearchicon(driver, viewName, Verifysp[0], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddscenarioSearchtab(driver, viewName, ReportName, Verify2sp[0], Verify3, Verifysp[0]);
		WIAscenarioverfiy(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify,Reportsp[0]);
		String sortclick = "//label[text()='"+Verifysp[1]+"']//following::span[contains(@class,'filterswitch')]";
		By sort = By.xpath(sortclick);
		click(driver, sort,"by drilling down to " + Verifysp[1]+ " and click on Sort/Filters");
		Thread.sleep(2000);	
		String filterinput = null;
		if(Verifysp[1].equals("Collateral"))
		{
			filterinput ="(//span[text()='"+Reportsp[1]+"']//following::input[@class='operatorValueFieldCls x-form-text x-form-text-default  '])[2]";
		}if(Verifysp[1].equals("Guarantee"))
		{
			filterinput ="(//span[text()='"+Reportsp[1]+"']//following::input[@class='operatorValueFieldCls x-form-text x-form-text-default  '])[2]";
		}

		By filter = By.xpath(filterinput);
		WebPageElements filterinput_dll = new WebPageElements("Sort/filiter", "xpath", filterinput);
		setTextenter(driver, filterinput_dll,Verify4);
		Thread.sleep(2000);
		waitForLoad(driver, 100);
		//	WIAExposureverfiy(driver,Verifysp[1],Reportsp[1],Verify4);

		String ExposureSUMMARYFIELD = "//label[text()='"+Verifysp[1]+"']//following::div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']";
		//iframe[contains(@src,'regulatory') and contains(@id,'gw-content-iframe')]
		//div[text()='CREATE COLLATERAL']//following::label[contains(@class,'x-form-item-label x-form-item-label-default  x-form-item-label-top x-unselectable') and contains(@class,'x-form-item-label x-form-item-label-default mandatoryLabel x-form-item-label-top x-unselectable')]
		WebPageElements exposuresummaryfield = new WebPageElements( Verifysp[1] + " filter", "xpath",ExposureSUMMARYFIELD);
		List<String> views = new ArrayList<>();
		waitForElementPresent(driver, 30, exposuresummaryfield);
		for (int i = 1; i < actualViewsp.length+1; i++) {
			views.add(getatt(driver, By.xpath("(" + ExposureSUMMARYFIELD + ")[" + i + "]"), Verifysp[1] +" filter table Feild Content #" + i));	
		}
		System.out.println(views);
		for (int i1 = 0; i1 < actualViewsp.length; i1++) {
			if (views.get(i1).equals(actualViewsp[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp[i1].trim().toString() + "= " +Verifysp[1] +" table content"); 
				Reporter.log("Successfully displayed " + actualViewsp[i1].trim().toString() + "= " +Verifysp[1] +" table content"); 

			} else {
				Add_Log.info("Unable to displayed " +actualViewsp[i1].trim().toString()+ "= " +Verifysp[1] +" table content"); 
				Reporter.log("Unable to displayed " +actualViewsp[i1].trim().toString()+ "= " +Verifysp[1] +" table content"); 					
				TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed " +actualViewsp[i1].trim().toString()+ "= " +Verifysp[1] +" table content");
				Assert.fail();
			}
		}	 
	}
	public void WIACollSearchnew(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report,String reportingPeriod)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIASearchicon(driver, viewName, Verifysp[0], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddscenarioSearchtab(driver, viewName, ReportName, Verify2sp[0], Verify3, Verifysp[0]);
		WIAscenarioverfiynewid(driver,viewName,ReportName,Verify3,Verify,Reportsp[0]);
		WIASearchicon(driver,viewName, Verifysp[1],ReportName);
		WIAHeadername(driver, viewName, Verify2sp[1], ReportName);
		Snackbar(driver);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		// COB select
		click(driver, cobselect);
		Thread.sleep(2000);
		By cobvalue = By.xpath("//li[text()='" + reportingPeriod + "']");
		click(driver, cobvalue, reportingPeriod);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		String sort1 = "(//*[text()='SEARCH']//following::span[text()='"+Reportsp[1]+"'])[1]//following::input[@name='searchInput']";
		By sort = By.xpath(sort1);
		Thread.sleep(2000);
		WebPageElements sort_dll = new WebPageElements(Verify2sp[1], "xpath", sort1);
		WebElement sort_dlle = driver.findElement(sort);
		click(driver, sort,"by drilling down to " + Verify2+ " and click on "+ Reportsp[1]);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		setTextenter(driver, sort_dll, Verify4);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		//font[text()='HK2151846-1']
		By eletext = By.xpath("//font[text()='"+Verify4+"']");
		waitForElementPresent(driver, 120, eletext, Verify4 );
		click(driver, eletext, Verify4);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		String sort2 = null;
		if(Reportsp[1].equals("Mitigant Key")) {
			sort2 = "(//label[text()='COLLATERAL SEARCH RESULT']//following::td)[2]//div";
		}
		if(Reportsp[1].equals("Guarantee Key")) {
			sort2 = "(//label[text()='GUARANTEE SEARCH RESULT']//following::td)[2]//div";
		}
		By sort21 = By.xpath(sort2);
		Thread.sleep(2000);
		WebPageElements sort2_dll = new WebPageElements(Verify2sp[1], "xpath", sort2);
		waitForElementPresent1(driver, 120,sort2_dll);
		WebElement sort2_dlle = driver.findElement(sort21);
		String valuetext = sort2_dlle.getText();
		System.out.println(valuetext);
		click(driver, sort2,Reportsp[1]);
		Thread.sleep(2000);
		if (Verify4.contains(valuetext))
		{
			Add_Log.info("Successfully " + Reportsp[1] +" " + Verify4 + " selected" );			 
			Reporter.log("Successfully " + Reportsp[1] +" "  + Verify4 + " selected" );
		}
		else
		{
			Add_Log.info("Unable " + Reportsp[1] +" "+ Verify4 + " selected" );			 
			Reporter.log("Unable " + Reportsp[1] +" "+ Verify4 + " selected" );	
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable " + Reportsp[1] +" "+ Verify4 + " selected" );	
			Assert.fail();
		}
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		click(driver, add_btninside);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);
		WIAExposureverfiyequal(driver,Verifysp[1],Reportsp[1],Verify4);
	}

	public void WIACollEdit(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2, String ID)
			throws InterruptedException, IOException {

		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] Verify4sp = Verify4.split("\\,");

		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIASearchicon(driver, viewName, Verifysp[0], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddscenarioSearchtab(driver, viewName, ReportName, Verify2sp[0], Verify3, Verifysp[0]);
		WIAscenarioverfiy(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify,Reportsp[0]);
		try {

			WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verifysp[1]+"']//following::span[text()='"+Reportsp[1]+"']"));
			String pathnum = extractxpathid.getAttribute("id");
			String[] xpathid = pathnum.split("-");
			System.out.println(xpathid[0]);
			System.out.println(xpathid[1]);
			System.out.println(xpathid[2]);

			String xpathdivtable = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "']//div";
			By filter = By.xpath(xpathdivtable);
			WebElement filterscenarioName = driver.findElement(filter);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOf(filterscenarioName));

		}
		catch (Exception e1)
		{
			if(Verifysp[1].equals("Collateral")) {
				By ele1 = By.xpath("(//div[@class='x-column-header x-column-header-checkbox x-column-header-align-left x-box-item x-column-header-coreselectcolumn x-unselectable x-column-header-first'])[2]");
				waitForElementPresent(driver, 120, ele1, " Check box for all item " + Verifysp[1]  );
				click(driver, ele1,  " Check box for " + Verifysp[1]);
				Thread.sleep(1000);
				By ele2 = By.xpath("(//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-trash '])[2]");
				waitForElementPresent(driver, 120, ele2, " Delete icon" + Verifysp[1]  );
				click(driver, ele2,  " Delete icon " + Verifysp[1]);
				Thread.sleep(1000);
				waitForLoad(driver, 120);
			}
			if(Verifysp[1].equals("Guarantee")) {
				By ele1 = By.xpath("(//div[@class='x-column-header x-column-header-checkbox x-column-header-align-left x-box-item x-column-header-coreselectcolumn x-unselectable x-column-header-first'])[3]");
				waitForElementPresent(driver, 120, ele1, " Check box for all item " + Verifysp[1]  );
			//	click(driver, ele1,  " Check box for " + Verifysp[1]);
				Thread.sleep(1000);
				By ele2 = By.xpath("(//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-trash '])[3]");
				waitForElementPresent(driver, 120, ele2, " Delete icon" + Verifysp[1]  );
			//	click(driver, ele2,  " Delete icon " + Verifysp[1]);
				Thread.sleep(1000);
				waitForLoad(driver, 120);
			}
			waitForLoad(driver, 120);



			WebElement extractxpathid6 = driver.findElement(By.xpath("//label[text()='"+Verifysp[1]+"']//following::span[text()='"+Reportsp[1]+"']"));
			String pathnum6 = extractxpathid6.getAttribute("id");
			String[] xpathid6 = pathnum6.split("-");
			System.out.println(xpathid6[0]);
			System.out.println(xpathid6[1]);
			System.out.println(xpathid6[2]);
			String xpathdivtable6 = "//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid6[1] + "']//div";
			By filter6 = By.xpath(xpathdivtable6);
			waitForInVisiblility(driver, 120,filter6,"Empty table content after deleting all " +Verifysp[1]);			
		}

		WIAAddbutton(driver,viewName,Verifysp[1], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[1], ReportName);
		WIAExpsoureCreateEdit(driver, Verify2sp[1], actualViews, actualViews2);
		By ele1 = By.xpath("//label[text()='"+Verifysp[1]+"']//following::div[@class='icon icon-edit-advanced']");
		waitForElementPresent(driver, 120, ele1, " Edit icon" + Verifysp[1]  );
		click(driver, ele1,  " Edit icon" + Verifysp[1]);
		String editverfiy = null;
		if(Verifysp[1].equals("Collateral"))
		{
			editverfiy = "EDIT COLLATERAL";
		}
		if(Verifysp[1].equals("Guarantee"))
		{
			editverfiy = "EDIT GUARANTEE";
		}

		WIAHeadername(driver, viewName, editverfiy, ReportName);
		WIAExpsoureCreateEdit(driver, editverfiy, actualViews, Verify4);
		Thread.sleep(1000);
		waitForLoad(driver, 120);
		WIAExpeditverfiygurat(driver, Verify4, Verify, ID);
	}
	//////////////////////////////////////////////////

	public void WIAEligcheck(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2, String ID)
			throws InterruptedException, IOException {

		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] Verify4sp = Verify4.split("\\,");

		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIASearchicon(driver, viewName, Verifysp[0], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddscenarioSearchtab(driver, viewName, ReportName, Verify2sp[0], Verify3, Verifysp[0]);
		WIAscenarioverfiy(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify,Reportsp[0]);
		Thread.sleep(1000);
		String id = "(//*[text()='Exposure']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]";
		String name = "Radio Button to select the Exposure key";
		WebPageElements radiobutton = new WebPageElements(name, "xpath", id);	
		waitForElementPresent1(driver, 120,radiobutton);
		click(driver, radiobutton); 	
		Thread.sleep(2000);
		String idcoll = "(//*[text()='Collateral']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]";
		String namecoll = "Radio Button to select the Exposure key";
		WebPageElements radiobuttoncoll = new WebPageElements(namecoll, "xpath", idcoll);	
		waitForElementPresent1(driver, 120,radiobuttoncoll);
		click(driver, radiobuttoncoll); 	
		Thread.sleep(2000);
		By ele1 = By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small hasIcon icon icon-check-mark-submit ']");
		waitForElementPresent(driver, 120, ele1, " Sumbit icon " );
		click(driver, ele1, "by drilling down to  Eligibility Check Submit icon");
		String filterscenarioNamea = null;
		try {
			By filter = By.xpath("(//label[text()='Eligibility Check']//following::div[@class='x-grid-cell-inner '])[4]");
			click(driver, filter,"Green"); 
			System.out.println(filter);
			WebElement filterscenarioName = driver.findElement(filter);
			filterscenarioNamea = filterscenarioName.getCssValue("background-color");
			System.out.println(filterscenarioNamea);
			Thread.sleep(2000);
		}
		catch (Exception e1) {
			Thread.sleep(2000);	
			By filter = By.xpath("(//label[text()='Eligibility Check']//following::div[@class='x-grid-cell-inner '])[4]");
			click(driver, filter,"Green"); 
			System.out.println(filter);
			WebElement filterscenarioName = driver.findElement(filter);
			filterscenarioNamea = filterscenarioName.getCssValue("background-color");
			System.out.println(filterscenarioNamea);
			Thread.sleep(2000);
		}

		if(filterscenarioNamea.equals("rgba(76, 169, 119, 1)"))
		{
			Add_Log.info("Successfully Eligibility Check" );
			Reporter.log("Successfully Eligibility Check");
		}
		else
		{
			Add_Log.info("Unable to Eligibility Check");
			Reporter.log("Unable to Eligibility Check");
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to Eligibility Check");
			Assert.fail();
		}
		By filter = By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small hasIcon icon icon-calculator ']");
		click(driver, filter,"icon-calculator");
		waitForLoad(driver, 900);
		waitForLoad(driver, 900);

		String idsummary = "(//label[text()='Summary']//following::td)[1]";
		String descsummary = "Desc summary";
		WebPageElements summarydesc = new WebPageElements(descsummary, "xpath", idsummary);
		waitForElementPresent1(driver, 120,summarydesc);
		By idsummarytext = By.xpath(idsummary);
		WebElement summarydescs = driver.findElement(idsummarytext);
		if(summarydescs.isDisplayed())
		{
			Add_Log.info("Successfully summary is displayed" );
			Reporter.log("Successfully summary is displayed" );
		}
		else
		{
			Add_Log.info("Unable to display summary" );
			Reporter.log("Unable to display summary" );
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display summary" );
			Assert.fail();
		}
		Thread.sleep(2000);	
		waitForLoad(driver, 900);
		By expsouretab = By.xpath(("(//span[@class='x-tab-icon-el x-tab-icon-el-secondary-tabs-elements  ']//following::span[text()='Exposure'])[1]"));
		click(driver, expsouretab,"Expsoure tab");
		Thread.sleep(2000);	
		String idexpsoure = "((//label[text()='Exposure'])[2]//following::td)[1]";
		String idexpsouretext = "Exposure tab";
		WebPageElements idexpsourewe = new WebPageElements(idexpsouretext, "xpath", idexpsoure);
		By idexpsoureweby = By.xpath(idexpsoure);
		WebElement idexpsouree = driver.findElement(idexpsoureweby);
		waitForElementPresent1(driver, 120,idexpsourewe);
		if(idexpsouree.isDisplayed())
		{
			Add_Log.info("Successfully Exposure is displayed" );
			Reporter.log("Successfully Exposure is displayed" );
		}
		else
		{
			Add_Log.info("Unable to display Exposure" );
			Reporter.log("Unable to display Exposure" );
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display Exposure" );
			Assert.fail();
		}
		Thread.sleep(2000);	
		waitForLoad(driver, 900);
		By mig1tab = By.xpath(("(//span[@class='x-tab-icon-el x-tab-icon-el-secondary-tabs-elements  ']//following::span[text()='Mitigant'])[1]"));
		click(driver, mig1tab,"Mitigant tab");
		String idmig = "(//label[text()='Mitigant']//following::td)[1]";
		String idmigtext = "Mitigant tab";
		WebPageElements idmigwe = new WebPageElements(idmigtext, "xpath", idmig);
		waitForElementPresent1(driver, 120,idmigwe);
		By idmigweby = By.xpath(idmig);
		WebElement idmige = driver.findElement(idmigweby);			
		if(idmige.isDisplayed())
		{
			Add_Log.info("Successfully Mitigant is displayed" );
			Reporter.log("Successfully Mitigant is displayed" );
		}
		else
		{
			Add_Log.info("Unable to display Mitigant" );
			Reporter.log("Unable to display Mitigant" );
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display Mitigant" );
			Assert.fail();
		}	
	}

	public void WIAEligcheckerror(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2, String ID)
			throws InterruptedException, IOException {

		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] Verify4sp = Verify4.split("\\,");

		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIASearchicon(driver, viewName, Verifysp[0], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddscenarioSearchtab(driver, viewName, ReportName, Verify2sp[0], Verify3, Verifysp[0]);
		WIAscenarioverfiy(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify,Reportsp[0]);
		Thread.sleep(1000);
		String id = "(//*[text()='Exposure']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]";
		String name = "Radio Button to select the Exposure key";
		WebPageElements radiobutton = new WebPageElements(name, "xpath", id);	
		waitForElementPresent1(driver, 120,radiobutton);
		click(driver, radiobutton); 	
		Thread.sleep(2000);
		String idcoll = "(//*[text()='Collateral']//following::td[contains(@class,'x-grid-cell-row-checker x-grid-cell-first')])[1]";
		String namecoll = "Radio Button to select the Exposure key";
		WebPageElements radiobuttoncoll = new WebPageElements(namecoll, "xpath", idcoll);	
		waitForElementPresent1(driver, 120,radiobuttoncoll);
		click(driver, radiobuttoncoll); 	
		Thread.sleep(2000);
		By ele1 = By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small hasIcon icon icon-check-mark-submit ']");
		waitForElementPresent(driver, 120, ele1, " Sumbit icon " );
		click(driver, ele1, "by drilling down to  Eligibility Check Submit icon");
		String filterscenarioNamea = null;
		try {
			By filter = By.xpath("(//label[text()='Eligibility Check']//following::div[@class='x-grid-cell-inner '])[4]");
			click(driver, filter,"Green"); 
			System.out.println(filter);
			WebElement filterscenarioName = driver.findElement(filter);
			filterscenarioNamea = filterscenarioName.getCssValue("background-color");
			System.out.println(filterscenarioNamea);
			Thread.sleep(2000);
		}
		catch (Exception e1) {
			Thread.sleep(2000);	
			By filter = By.xpath("(//label[text()='Eligibility Check']//following::div[@class='x-grid-cell-inner '])[4]");
			click(driver, filter,"Green"); 
			System.out.println(filter);
			WebElement filterscenarioName = driver.findElement(filter);
			filterscenarioNamea = filterscenarioName.getCssValue("background-color");
			System.out.println(filterscenarioNamea);
			Thread.sleep(2000);
		}

		if(filterscenarioNamea.equals("rgba(76, 169, 119, 1)"))
		{
			Add_Log.info("Unable to get error message Eligibility Check" );
			Reporter.log("Unable to get error message Eligibility Check");
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to get error message Eligibility Check");
			Assert.fail();
		}
		else
		{
			Add_Log.info("Successfully able to get error message  Eligibility Check");
			Reporter.log("Successfully able to get error message  Eligibility Check");

			WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='Eligibility Check']//following::span[text()='Reason']"));
			String pathnum = extractxpathid.getAttribute("id");
			String[] xpathid = pathnum.split("-");
			System.out.println(xpathid[0]);
			System.out.println(xpathid[1]);
			System.out.println(xpathid[2]);	
			By filtern = By.xpath("//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-" + xpathid[1] + "')]//div");
			WebElement filterscenarioNamen = driver.findElement(filtern);
			String filterscenarioNamean = filterscenarioNamen.getAttribute("textContent");
			System.out.println(filterscenarioNamean);
			Add_Log.info("Successfully displayed error message Reason = " +  filterscenarioNamean);
			Reporter.log("Successfully displayed error message Reason = "+  filterscenarioNamean);
		}
	}

	public void WIAEligcheckdownload(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2, String FileNameac, String Fileformat,String ID)
			throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");

		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
		File dir = new File(downloadPath);

		for (File file : dir.listFiles()) {
			if (file.getName().startsWith(FileNameac)) {
				file.delete();
				Reporter.log("Successfully Deleted Existing File before clicking on download");
				Add_Log.info("Successfully Deleted Existing File before clicking on download");
				Thread.sleep(1000);
			}
		}
		WIAEligcheck(driver,viewName,ReportName, Verify2, Verify3, Verify, Verify4, Report,actualViews,actualViews2,ID);
		By expsouretab = By.xpath(("(//span[@class='x-tab-icon-el x-tab-icon-el-secondary-tabs-elements  ']//following::span[text()='Eligibility Check'])[1]"));
		click(driver, expsouretab,"Expsoure tab");
		Thread.sleep(2000);
		waitForLoad(driver, 120);		
		By ele1 = By.xpath("(//label[text()='Eligibility Check']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-excel '])[1]");
		waitForElementPresent(driver, 120, ele1, " Download icon Eligibility Check" );
		clicknosleep(driver, ele1,  " Download icon Eligibility Check");		
		downloadfilecommonreg(driver,ReportName, Verify,Fileformat,FileNameac);

	}
	////////////////////////////////

	public void WIAPortfolioIDsearch(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Report, String Verify4)
			throws InterruptedException {
		String[] Verify4sp = Verify4.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIAAddbutton(driver, viewName, Verify, ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddScenariopop(driver, Verify3,Verify);	
		WIASearchFacility(driver, viewName, Verify, ReportName);
		//WIAPortfolioSearchtab(driver, Verify2,Verify4);
		waitForElementPresent(driver, 120, portoflioinput);
		click(driver, portoflioinput);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		setTextenter(driver, portoflioinput, Verify4sp[0]);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		By eletext = By.xpath("//font[text()='"+Verify4sp[0]+"']");
		waitForElementPresent(driver, 120, eletext, Verify4sp[0] );
		click(driver, eletext, Verify4sp[0]);
		waitForLoad(driver, 900);
		String SearchInputtable = "//div[text()='"+Verify2sp[1]+"']//following::tr";
		WebPageElements searchinputtable  = new WebPageElements(" Search Input table", "xpath",SearchInputtable);
		waitForElementPresent(driver, 120, searchinputtable);
		clearText(driver, PortoflioInputby, Verify4sp[0]);
		Thread.sleep(2000);
		click(driver, closeicon);
		Thread.sleep(2000);
		WIASearchFacility(driver, viewName, Verify, ReportName);
		waitForElementPresent(driver, 120, gfcidInput);
		click(driver, gfcidInput);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		setTextenter(driver, gfcidInput, Verify4sp[1]);
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		By eletext2 = By.xpath("//font[text()='"+Verify4sp[1]+"']");
		waitForElementPresent(driver, 120, eletext2, Verify4sp[1] );
		click(driver, eletext2, Verify4sp[1]);
		waitForElementPresent(driver, 120, searchinputtable);
		String filterinput ="(//div[text()='SEARCH']//following::span[text()='Portfolio ID'])[2]//following::input[3]";
		By filter = By.xpath(filterinput);
		WebPageElements filterinput_dll = new WebPageElements("Sort/filiter", "xpath", filterinput);
		setTextenter(driver, filterinput_dll,Verify4sp[0]);
		Thread.sleep(2000);
		waitForLoad(driver, 100);
		String sort2 = "(//label[text()='Search Results']//following::td)[4]";
		By sort21 = By.xpath(sort2);
		Thread.sleep(2000);
		WebPageElements sort2_dll = new WebPageElements(Verify4sp[0], "xpath", sort2);
		waitForElementPresent1(driver, 120,sort2_dll);
		WebElement sort2_dlle = driver.findElement(sort21);
		String valuetext = sort2_dlle.getText();
		System.out.println(valuetext);
		if (Verify4sp[0].contains(valuetext))
		{
			Add_Log.info("Successfully Portfolio ID " + Verify4 + " Displayed" );			 
			Reporter.log("Successfully Portfolio ID " + Verify4 + " Displayed" );
		}
		else
		{
			Add_Log.info("Unable to displayed " + Verify4 + " Portfolio ID" );			 
			Reporter.log("Unable to displayed " + Verify4 + " Portfolio ID" );	
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed " + Verify4 + " Portfolio ID" );	
			Assert.fail();
		}
		String sorttext = "(//label[text()='Search Results']//following::td)[1]";
		By sorttextpath = By.xpath(sorttext);
		click(driver, sorttextpath, "Radio button ");
		Thread.sleep(2000);
		waitForLoad(driver, 900);
		click(driver, add_btninside);
		Thread.sleep(2000);
		waitForLoad(driver, 900);	
	}
	public void WIAaddTradeCreatinside(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report,String Inputtext1,String Inputtext2, String Inputtext3,String actualViews, String actualViews2,String actualViews3)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Verify4sp = Verify4.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");
		String[] actualViewsp3 = actualViews3.split("\\,");
		String[] Inputtext1sp = Inputtext1.split("\\,");
		String[] Inputtext2sp = Inputtext2.split("\\,");
		String[] Inputtext3sp = Inputtext3.split("\\,");
	By ele1 = By.xpath("(//div[text()='CREATE TRADE']//following::input)[1]");
	waitForElementPresent(driver, 120, ele1, " Asset Class Code "  );
	click(driver, ele1, "by drilling down to " + ReportName + " - " + viewName + " Asset Class Code");
	Thread.sleep(2000);	
	By ele2 = By.xpath("//li[text()='"+Verify4sp[0]+"']");
	waitForElementPresent(driver, 120, ele2, "Drop down value for Asset Class Code "  );
	click(driver, ele2, "by drilling down to " + ReportName + " - " + viewName + "Drop down value for Asset Class Code ");
	Thread.sleep(2000);	

	String IsOption = "//span[text()='Is Option']";
	WebPageElements isoption = new WebPageElements("Is Option", "xpath", IsOption);
	waitForElementPresent(driver, 120, isoption);

	/*String TradeDetailsEntry = "//div[text()='Asset Specific Trade Details Entry']";
	WebPageElements tradedetailsentry = new WebPageElements("Asset Specific Trade Details Entry'", "xpath", TradeDetailsEntry);
	waitForElementPresent(driver, 120, tradedetailsentry);*/

	List<String> views2 = new ArrayList<>();

	List<WebElement> options2 = driver.findElements(By.xpath("//span[text()='Common Details']//following::label[contains(@class,' x-form-item-label-top x-unselectable')] "));
	for (WebElement ele : options2) {
		views2.add(ele.getAttribute(("textContent")));
	}
	System.out.println(views2);
	for (int i1 = 0; i1 < actualViewsp2.length; i1++) {

		if (views2.get(i1).equals(actualViewsp2[i1].trim().toString())) {
			Add_Log.info("Successfully displayed " + views2.get(i1) + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
			Reporter.log("Successfully displayed " + views2.get(i1) + " under " + Verify2sp[1] + " all tabs  " + Verifysp[1]);
		} else {
			Add_Log.info("Unable to display "+ actualViewsp2[i1].trim().toString() + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
			Reporter.log("Unable to display "+ actualViewsp2[i1].trim().toString() + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
			TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display "+ actualViewsp2[i1].trim().toString() + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
			Assert.fail();
		}
	}
	String aggreationfilter= null;
	String inputtextsearch1 = null;
	System.out.println(Inputtext1sp.length);
	System.out.println(views2.size());
	for (int i = 0; i < views2.size(); i++)  {

		System.out.print(views2.get(i) + " "+ Inputtext1sp[i].trim().toString());

		inputtextsearch1 = "(//span[text()='Common Details']//following::span[text()='"+views2.get(i)+"']//following::input)[1]";
		aggreationfilter = "(//span[text()='Common Details']//following::span[text()='"+views2.get(i)+"']//following::input)[1]";
		WebElement aggreationfilterweb = driver.findElement(By.xpath(aggreationfilter));
		By aggreationfilterid = (By.xpath(aggreationfilter));
		WebDriverWait wait = new WebDriverWait(driver, 120);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
		click(driver, aggreationfilterid, views2.get(i));
		WebPageElements inputtextfeildid = new WebPageElements(Inputtext1sp[i].trim().toString(), "xpath", inputtextsearch1);
		clearText(driver, inputtextfeildid);
		Thread.sleep(1000);
		setTextenter(driver, inputtextfeildid, Inputtext1sp[i]);
		Thread.sleep(1000);
	}
	click(driver,save_btninside);
	Thread.sleep(1000);

	String SearchInputtable = "//label[text()='Trade View']//following::tr";
	WebPageElements searchinputtable  = new WebPageElements(" Search Input table", "xpath",SearchInputtable);
	waitForElementPresent(driver, 120, searchinputtable);
}

	public void WIAaddTradeCreatenew(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report,String Inputtext1,String Inputtext2, String Inputtext3,String actualViews, String actualViews2,String actualViews3)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Verify4sp = Verify4.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");
		String[] actualViewsp3 = actualViews3.split("\\,");
		String[] Inputtext1sp = Inputtext1.split("\\,");
		String[] Inputtext2sp = Inputtext2.split("\\,");
		String[] Inputtext3sp = Inputtext3.split("\\,");
		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIAAddbutton(driver, viewName, Verify, ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddScenariopop(driver, Verify3, Verifysp[0]);
		WIAscenarioverfiy(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify,Report);
		WIAAddbutton(driver,viewName,Verifysp[1], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[1], ReportName);
		WIAaddTradeCreatinside(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report, Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3);
		String editIcon = "//label[text()='Trade View']//following::tr//div[@class='icon icon-edit-advanced']";
		WebPageElements EditIcon  = new WebPageElements(" Edit 1st table content", "xpath",editIcon);
		waitForElementPresent(driver, 120, EditIcon);
		click(driver,EditIcon);
		WIAHeadername(driver, viewName, "EDIT TRADE", ReportName);
		String IsOptionCheck = "(//span[text()='Is Option']//following::input)[1]";
		WebPageElements isoptioncheck  = new WebPageElements("Is option check box", "xpath",IsOptionCheck);
		click(driver,isoptioncheck);	
		Thread.sleep(2000);
		String AssetSD  = "//span[text()='Asset Specific Details']";
		WebPageElements Assetsdetails  = new WebPageElements("Asset Specific Details", "xpath",AssetSD);
		click(driver,Assetsdetails);	
		Thread.sleep(2000);
		String aggreationfilter= null;
		String inputtextsearch1 = null;
		Thread.sleep(1000);
		List<String> views4 = new ArrayList<>();
		for (int i4 = 0; i4 < actualViewsp.length; i4++) {
			System.out.println(actualViewsp.toString() + Inputtext3sp[i4] );
		inputtextsearch1 = "(//span[text()='Asset Specific Details']//following::span[text()='"+actualViewsp[i4].trim().toString()+"']//following::input)[1]";
		aggreationfilter = "(//span[text()='Asset Specific Details']//following::span[text()='"+actualViewsp[i4].trim().toString()+"']//following::input)[1]";
		WebElement aggreationfilterweb = driver.findElement(By.xpath(aggreationfilter));
		By aggreationfilterid = (By.xpath(aggreationfilter));
		WebDriverWait wait = new WebDriverWait(driver, 120);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(aggreationfilterweb));
		click(driver, aggreationfilterid, actualViewsp.toString());
		WebPageElements inputtextfeildid = new WebPageElements(Inputtext3sp[i4].trim().toString(), "xpath", inputtextsearch1);
		clearText(driver, inputtextfeildid);
		Thread.sleep(1000);
		setTextenter(driver, inputtextfeildid, Inputtext3sp[i4]);
		Thread.sleep(1000);
		}
		String Save_Btninside2 = "(//span[text()='Save'])[2]";
		WebPageElements save_btninside2 = new WebPageElements("Save button under Edit Scenario", "xpath", Save_Btninside2);
		Thread.sleep(1000);
		click(driver, save_btninside2);
		Thread.sleep(1000);
		By CommonDetails = By.xpath("//span[text()='Common Details']");
		WebElement CommonDetailsbox = driver.findElement(CommonDetails);
		if(CommonDetailsbox.isDisplayed()){
			try {
			click(driver, save_btninside);
			Thread.sleep(1000);
			String CloseWindow = "//img[@class='x-tool-img x-tool-close']";
			WebPageElements closewindow = new WebPageElements("Close Window", "xpath", CloseWindow);
			click(driver, closewindow);
			Thread.sleep(1000);
			}
			catch (Exception e1) {
			}
			}
		String SearchInputtable = "//label[text()='Trade View']//following::tr";
		WebPageElements searchinputtable  = new WebPageElements(" Search Input table", "xpath",SearchInputtable);
		waitForElementPresent(driver, 120, searchinputtable);
	}

	public void WIAaddTradeClonenew(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report,String Inputtext1,String Inputtext2, String Inputtext3,String actualViews, String actualViews2,String actualViews3)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		WIAaddTradeCreatenew(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report, Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3);
		String SearchInputtable2 = "(//label[text()='"+Verifysp[1]+"']//following::tr)[1]//td[@class='x-grid-cell x-grid-td x-grid-cell-coreselectcolumn-5439 x-grid-cell-special x-grid-cell-row-checker x-grid-cell-first']";
		WebPageElements searchinputtable2  = new WebPageElements(" check box", "xpath",SearchInputtable2);
//		click(driver, searchinputtable2);
		String CloneIconTrade = "(//label[text()='"+Verifysp[1]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-copy '])[1]";
		WebPageElements cloneicontrade = new WebPageElements("Clone icon for" + Verifysp[1], "xpath", CloneIconTrade);
		Thread.sleep(1000);
		click(driver, cloneicontrade);
		Thread.sleep(2000);
		String SearchInputtable2row = "(//label[text()='"+Verifysp[1]+"']//following::tr)[2]";
		WebPageElements searchinputtable2row  = new WebPageElements(" Search Input table", "xpath",SearchInputtable2row);
		waitForElementPresent(driver, 120, searchinputtable2row);
	
	}

	public void WIAaddTradeDelete(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report,String Inputtext1,String Inputtext2, String Inputtext3,String actualViews, String actualViews2,String actualViews3)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		WIAaddTradeClonenew(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report, Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3);
		By ele1 = By.xpath("(//label[text()='"+Verifysp[1]+"']//following::div[@class='x-column-header x-column-header-checkbox x-column-header-align-left x-box-item x-column-header-coreselectcolumn x-unselectable x-column-header-first'])[1]");
		waitForElementPresent(driver, 120, ele1, " Check box for all item " + Verifysp[1]  );
		click(driver, ele1,  " Check box for " + Verifysp[1]);
		Thread.sleep(1000);
		waitForLoad(driver, 120);	
		By ele2 = By.xpath("(//label[text()='"+Verifysp[1]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-trash '])[1]");
		waitForElementPresent(driver, 30, ele2, " Delete icon" + "Trade"  );
		click(driver, ele2,  " Delete icon " + "Trade");
		Thread.sleep(1000);
		waitForLoad(driver, 120);
		String SearchInputtable2 = "//label[text()='"+Verifysp[1]+"']//following::tr";
		WebPageElements searchinputtable2  = new WebPageElements(" Search Input table", "xpath",SearchInputtable2);
		waitForElementInvisibilty(driver, 120, searchinputtable2);
	}
	public void WIAaddTradeDownload(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report,String Inputtext1,String Inputtext2, String Inputtext3,String actualViews, String actualViews2,String actualViews3,String Fileformat,String FileNameac)
			throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");

		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
		File dir = new File(downloadPath);

		/*
		 * for (File file : dir.listFiles()) { if
		 * (file.getName().startsWith(FileNameac)) { file.delete();
		 * Reporter.log("Successfully Deleted Existing File before clicking on download"
		 * );
		 * Add_Log.info("Successfully Deleted Existing File before clicking on download"
		 * ); Thread.sleep(1000); } }
		 */
		WIAaddTradeClonenew(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report, Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3);
		Thread.sleep(2000);
		waitForLoad(driver, 120);

		By ele1 = By.xpath("(//label[text()='"+Verifysp[1]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-excel '])[1]");
		waitForElementPresent(driver, 120, ele1, " Download icon" + Verifysp[1]  );
		clicknosleep(driver, ele1,  " Download icon " + Verifysp[1]);	

		downloadfilecommonreg(driver,ReportName, Verify,Fileformat,FileNameac);
	}
	
	public void WIAaddTradeSortfilter(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report,String Inputtext1,String Inputtext2, String Inputtext3,String actualViews, String actualViews2,String actualViews3, String actualViews4)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");
		
		List<String> views2 = new ArrayList<>();
		String[] actualViewsp4 = actualViews4.split("\\,");
		WIAaddTradeClonenew(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Reportsp[0], Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3);
		
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

		String filterinput ="(//span[text()='"+Reportsp[1]+"']//following::input[@class='operatorValueFieldCls x-form-text x-form-text-default  '])[3]";
		WebPageElements filterinput_dll = new WebPageElements("Sort/filiter", "xpath", filterinput);
		setTextenter(driver, filterinput_dll,views.get(1));
		Thread.sleep(2000);
		waitForLoad(driver, 100);
		String SearchInputtable2 = "(//label[text()='"+Verifysp[1]+"']//following::tr)[2]";
		WebPageElements searchinputtable2  = new WebPageElements(" Search Input table 2nd row", "xpath",SearchInputtable2);
		waitForElementInvisibilty(driver, 120, searchinputtable2);
	
		String ExposureSUMMARYFIELD = "(//label[text()='"+Verifysp[1]+"']//following::div[@class='x-grid-header-ct x-docked x-grid-header-ct-default x-docked-top x-grid-header-ct-docked-top x-grid-header-ct-default-docked-top x-box-layout-ct'])[1]//child::div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']";
		WebPageElements exposuresummaryfield = new WebPageElements("Exposure Summary filter", "xpath",
				ExposureSUMMARYFIELD);
		
		waitForElementPresent(driver, 30, exposuresummaryfield);
		int size1 = driver.findElements(By.xpath(ExposureSUMMARYFIELD)).size();
		for (int i = 1; i <= size1; i++) {
			views2.add(getatt(driver, By.xpath("(" + ExposureSUMMARYFIELD + ")[" + i + "]"), Verifysp[1]+ " table Feild Content #" + i));	
		}
		System.out.println(views2);
		
		for (int i1 = 0; i1 < actualViewsp4.length; i1++) {
			if (views2.get(i1).equals(actualViewsp4[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp4[i1].trim().toString() + "= " +Verifysp[1] +" table content"); 
				Reporter.log("Successfully displayed " + actualViewsp4[i1].trim().toString() + "= " +Verifysp[1] +" table content"); 

			} else {
				Add_Log.info("Unable to displayed " +actualViewsp4[i1].trim().toString()+ "= " +Verifysp[1] +" table content"); 
				Reporter.log("Unable to displayed " +actualViewsp4[i1].trim().toString()+ "= " +Verifysp[1] +" table content"); 					
				TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed " +actualViewsp4[i1].trim().toString()+ "= " +Verifysp[1] +" table content");
				Assert.fail();
			}
		}	 
	}
	//////////////////////////////////////////////////
	
	public void WIACSACreateinside(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");
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
				Add_Log.info("Successfully displayed " + views2.get(i1) + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
				Reporter.log("Successfully displayed " + views2.get(i1) + " under " + Verify2sp[1] + " all tabs  " + Verifysp[1]);
			} else {
				Add_Log.info("Unable to display "+ views2.get(i1) + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
				Reporter.log("Unable to display "+ views2.get(i1) + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
				TestResultStatus.mpaskeysnew.put(methodName(),"Unable to display "+ views2.get(i1) + " under " + Verify2sp[1] + " all tabs " + Verifysp[1]);
				Assert.fail();
			}
		}
		String aggreationfilter= null;
		String inputtextsearch1 = null;
		for (int i1 = 0; i1 < actualViewsp2.length; i1++) {
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
			WebPageElements inputtextfeildid = new WebPageElements(actualViewsp2[i].trim().toString(), "xpath", inputtextsearch1);
			clearText(driver, inputtextfeildid);
			Thread.sleep(1000);
			setTextenter(driver, inputtextfeildid, actualViewsp2[i]);
			Thread.sleep(1000);
		}

		Thread.sleep(1000);
		waitForLoad(driver, 120);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		waitForElementInvisibilty(driver, 120, save_btninside);
		Thread.sleep(2000);
		String SearchInputtable = "//label[text()='CSA VIEW']//following::tr";
		WebPageElements searchinputtable  = new WebPageElements(" Created CSA id  under table content", "xpath",SearchInputtable);
		waitForElementPresent(driver, 120, searchinputtable);
	}
	
	
	
	
	public void WIACSAviewCreate(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp = Verify2.split("\\,");
		String[] actualViewsp = actualViews.split("\\,");
		String[] actualViewsp2 = actualViews2.split("\\,");
		String[] Reportsp = Report.split("\\,");

		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIAAddbutton(driver, viewName, Verify, ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddScenariopop(driver, Verify3, Verify);
		WIAscenarioverfiy(driver, viewName, ReportName, Verify2sp[0], Verify3, Verify,Reportsp[0]);
		WIAAddbutton(driver,viewName,Verifysp[1], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[1], ReportName);
		WIACSACreateinside(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report, actualViews, actualViews2);

	}
	public void WIACSAviewCloneEdit(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2)
			throws InterruptedException {
		
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");
		List<String> views = new ArrayList<>();
		WIACSAviewCreate(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report, actualViews, actualViews2);
		
		String CloneIconTrade = "(//label[text()='"+Verifysp[1]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-copy '])[1]";
		String SearchInputtable2 = "(//label[text()='"+Verifysp[1]+"']//following::tr)[1]";
		WebPageElements searchinputtable2  = new WebPageElements(" Search Input table", "xpath",SearchInputtable2);
		click(driver, searchinputtable2);
		WebPageElements cloneicontrade = new WebPageElements("Clone icon for" + Verifysp[1], "xpath", CloneIconTrade);
		Thread.sleep(1000);
		click(driver, cloneicontrade);
		Thread.sleep(2000);
		waitForElementPresent(driver, 120, save_btninside);
		click(driver, save_btninside);
		waitForElementInvisibilty(driver, 120, save_btninside);
		Thread.sleep(2000);
		String SearchInputtable2row = "(//label[text()='"+Verifysp[1]+"']//following::tr)[2]";
		WebPageElements searchinputtable2row  = new WebPageElements(" Search Input table", "xpath",SearchInputtable2row);
		waitForElementPresent(driver, 120, searchinputtable2row);
		click(driver, searchinputtable2);
		
		//EDIT 
		String SearchInputtable = "//label[text()='Trade View']//following::tr";
		WebPageElements searchinputtable  = new WebPageElements(" Search Input table", "xpath",SearchInputtable);
		waitForElementPresent(driver, 120, searchinputtable);
		String editIcon = "//label[text()='Trade View']//following::tr//div[@class='icon icon-edit-advanced']";
		WebPageElements EditIcon  = new WebPageElements(" Edit 1st table content", "xpath",editIcon);
		waitForElementPresent(driver, 120, EditIcon);
		click(driver,EditIcon);
		WIAHeadername(driver, viewName, "EDIT CSA ATTRIBUTES", ReportName);
		String aggreationfilter = "(//div[text()='EDIT CSA ATTRIBUTES']//following::span[text()='CSA Type']//following::input)[1]";
		WebPageElements aggreationfilterweb =  new WebPageElements(" Text box for CSA type", "xpath",aggreationfilter);
		Thread.sleep(1000);
		waitForElementPresent(driver, 120, aggreationfilterweb);
		click(driver, aggreationfilterweb);
		clearText(driver, aggreationfilterweb);
		Thread.sleep(1000);
		setTextenter(driver, aggreationfilterweb, Verify4);
		Thread.sleep(1000);
		click(driver, save_btninside);
	waitForElementInvisibilty(driver, 120, save_btninside);
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
	System.out.println(filterscenarioNamea);
	System.out.println(Verify3);
	if(filterscenarioNamea.equals(Verify4)) {
		Add_Log.info("Successfully displayed Edited content for " + Verifysp[1] );
		Reporter.log("Successfully displayed Edited content for " + Verifysp[1] );
	}
	else {
		Add_Log.info("Unable to  displayed Edited content for " + Verifysp[1] );
		Reporter.log("Unable to  displayed Edited content for " + Verifysp[1] );
		TestResultStatus.mpaskeysnew.put(methodName(),"Unable to  displayed Edited content for " + Verifysp[1] );
		Assert.fail();
	}
	}	
	

	public void WICSADelete(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		WIACSAviewCreate(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report, actualViews, actualViews2);
		By ele1 = By.xpath("(//label[text()='"+Verifysp[1]+"']//following::div[@class='x-column-header x-column-header-checkbox x-column-header-align-left x-box-item x-column-header-coreselectcolumn x-unselectable x-column-header-first'])[1]");
		waitForElementPresent(driver, 120, ele1, " Check box for all item " + Verifysp[1]  );
		click(driver, ele1,  " Check box for " + Verifysp[1]);
		Thread.sleep(1000);
		waitForLoad(driver, 120);	
		By ele2 = By.xpath("(//label[text()='"+Verifysp[1]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-trash '])[1]");
		waitForElementPresent(driver, 30, ele2, " Delete icon" + "Trade"  );
		click(driver, ele2,  " Delete icon " + "Trade");
		Thread.sleep(1000);
		waitForLoad(driver, 120);
		String SearchInputtable2 = "//label[text()='"+Verifysp[1]+"']//following::tr";
		WebPageElements searchinputtable2  = new WebPageElements(" Search Input table", "xpath",SearchInputtable2);
		waitForElementInvisibilty(driver, 120, searchinputtable2);
	}
	public void WICSADownload(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2,String Fileformat,String FileNameac)
			throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");

		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
		File dir = new File(downloadPath);

		for (File file : dir.listFiles()) {
			if (file.getName().startsWith(FileNameac)) {
				file.delete();
				Reporter.log("Successfully Deleted Existing File before clicking on download");
				Add_Log.info("Successfully Deleted Existing File before clicking on download");
				Thread.sleep(1000);
			}
		}
		WIACSAviewCreate(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report, actualViews, actualViews2);
		Thread.sleep(2000);
		waitForLoad(driver, 120);

		By ele1 = By.xpath("(//label[text()='"+Verifysp[1]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-excel '])[1]");
		waitForElementPresent(driver, 120, ele1, " Download icon" + Verifysp[1]  );
		clicknosleep(driver, ele1,  " Download icon " + Verifysp[1]);	

		downloadfilecommonreg(driver,ReportName, Verify,Fileformat,FileNameac);
	}
	public void WIACSASortfilter(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2,String actualViews3)
			throws InterruptedException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");
		
		List<String> views2 = new ArrayList<>();
		String[] actualViewsp3 = actualViews3.split("\\,");
		WIACSAviewCloneEdit(driver, viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);
		List<String> views = new ArrayList<>();
		WebElement extractxpathid = driver.findElement(By.xpath("//label[text()='"+Verifysp[1]+"']//following::span[text()='"+Reportsp[2]+"']"));
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

		String filterinput ="(//span[text()='"+Reportsp[1]+"']//following::input[@class='operatorValueFieldCls x-form-text x-form-text-default  '])[3]";
		WebPageElements filterinput_dll = new WebPageElements("Sort/filiter", "xpath", filterinput);
		setTextenter(driver, filterinput_dll,views.get(1));
		Thread.sleep(2000);
		waitForLoad(driver, 100);
		String SearchInputtable2 = "(//label[text()='"+Verifysp[1]+"']//following::tr)[2]";
		WebPageElements searchinputtable2  = new WebPageElements(" Search Input table 2nd row", "xpath",SearchInputtable2);
		waitForElementInvisibilty(driver, 120, searchinputtable2);
	
		String ExposureSUMMARYFIELD = "(//label[text()='"+Verifysp[1]+"']//following::div[@class='x-grid-header-ct x-docked x-grid-header-ct-default x-docked-top x-grid-header-ct-docked-top x-grid-header-ct-default-docked-top x-box-layout-ct'])[1]//child::div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']";
		WebPageElements exposuresummaryfield = new WebPageElements("Exposure Summary filter", "xpath",
				ExposureSUMMARYFIELD);
		
		waitForElementPresent(driver, 30, exposuresummaryfield);
		int size1 = driver.findElements(By.xpath(ExposureSUMMARYFIELD)).size();
		for (int i = 1; i <= size1; i++) {
			views2.add(getatt(driver, By.xpath("(" + ExposureSUMMARYFIELD + ")[" + i + "]"), Verifysp[1]+ " table Feild Content #" + i));	
		}
		System.out.println(views2);
		
		for (int i1 = 0; i1 < actualViewsp3.length; i1++) {
			if (views2.get(i1).equals(actualViewsp3[i1].trim().toString())) {
				Add_Log.info("Successfully displayed " + actualViewsp3[i1].trim().toString() + "= " +Verifysp[1] +" table content"); 
				Reporter.log("Successfully displayed " + actualViewsp3[i1].trim().toString() + "= " +Verifysp[1] +" table content"); 

			} else {
				Add_Log.info("Unable to displayed " +actualViewsp3[i1].trim().toString()+ "= " +Verifysp[1] +" table content"); 
				Reporter.log("Unable to displayed " +actualViewsp3[i1].trim().toString()+ "= " +Verifysp[1] +" table content");
				TestResultStatus.mpaskeysnew.put(methodName(),"Unable to displayed " +actualViewsp3[i1].trim().toString()+ "= " +Verifysp[1] +" table content");
				Assert.fail();
			}
		}	 
	}
	public void WIPortfolioResultsDownload(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report, String actualViews, String actualViews2,String Fileformat,String FileNameac,String reportingPeriod)
			throws InterruptedException, IOException {
		String[] Verifysp = Verify.split("\\,");
		String[] Reportsp = Report.split("\\,");

		String downloadPath = "\\\\sd-49cd-8b6b\\Selenium\\OneMartFile";
		File dir = new File(downloadPath);

		for (File file : dir.listFiles()) {
			if (file.getName().startsWith(FileNameac)) {
				file.delete();
				Reporter.log("Successfully Deleted Existing File before clicking on download");
				Add_Log.info("Successfully Deleted Existing File before clicking on download");
				Thread.sleep(1000);
			}
		}
		WIAaddTradeview(driver, viewName,ReportName, Verify2, Verify3,Verify,Report,Verify4,reportingPeriod);
		Thread.sleep(2000);
		waitForLoad(driver, 120);

		By ele1 = By.xpath("(//label[text()='"+Verifysp[0]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-excel '])[3]");
		waitForElementPresent(driver, 120, ele1, " Download icon" + Verifysp[0]  );
		clicknosleep(driver, ele1,  " Download icon " + Verifysp[0]);	

		downloadfilecommonreg(driver,ReportName, Verify,Fileformat,FileNameac);
	}
	
	public void WIATradeHypothetical(WebDriver driver, String viewName,String ReportName, String Verify2, String Verify3,String Verify,String Verify4,String Report,String Inputtext1,String Inputtext2, String Inputtext3,String actualViews, String actualViews2,String actualViews3, String reportingPeriod)
			throws InterruptedException {
		
		String[] Verifysp = Verify.split("\\,");
		String[] Verify2sp= Verify2.split("\\,");
		String[] Verify4sp= Verify4.split("\\,");
		String[] Reportsp = Report.split("\\,");

		CapitalWhtif(driver, viewName, ReportName,Verify);
		WIAAddbutton(driver, viewName, Verify, ReportName);
		WIAHeadername(driver, viewName, Verify2sp[0], ReportName);
		WIAaddScenariopop(driver, Verify3,Verify);	
		WIASearchFacility(driver, viewName, Verifysp[0], ReportName);
		WIAPortfolioSearchtab(driver, Verify2,Verify4sp[0],reportingPeriod);
		boolean success2 = false;
		while (!success2) {
			try
			{
				WIATradeCSAverfiy(driver);
				success2 = true;
			}

			catch (Exception e1) {
				try {
					By errorpopup = By.xpath("//div[contains(text(),'System Error Occurred')]");
					WebElement errorpopupbox = driver.findElement(errorpopup);
					String errorpopmessage = errorpopupbox.getText();
					System.out.println(errorpopmessage);
					if(errorpopupbox.isDisplayed()){
						Add_Log.info("Error Popup with message : " + errorpopmessage );
						Reporter.log("Error Popup with message : " + errorpopmessage );
					}
					Thread.sleep(2000);	
					waitForLoad(driver, 900);
					By closepop = By.xpath("//span[text()='OK']");
					click(driver,closepop," click ok on popup meeasge");
					Thread.sleep(2000);	
					waitForLoad(driver, 900);
					WIASearchFacility(driver, viewName, Verify, ReportName);
					WIAPortfolioSearchtab(driver, Verify2,Verify4,reportingPeriod);	
				}
				finally {
				}
			}
		}
		Thread.sleep(2000);	
		waitForLoad(driver, 900);
		WIATradeCSAclick(driver);
		Thread.sleep(2000);	
		waitForLoad(driver, 900);
		WIAAddbutton(driver,viewName,Verifysp[1], ReportName);
		WIAHeadername(driver, viewName, Verify2sp[2], ReportName);
		WIAaddTradeCreatinside(driver, viewName, ReportName, Verify2, Verify3, Verify, Verify4sp[1], Report, Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3);
		String HyperIconTrade = "(//label[text()='"+Verifysp[1]+"']//following::span[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-switch '])[1]";
		System.out.println(HyperIconTrade);
		WebPageElements HyperIcon  = new WebPageElements("View Hypothetical Trade", "xpath",HyperIconTrade);
		waitForElementPresent(driver, 120, HyperIcon);
		Thread.sleep(2000);
		click(driver,HyperIcon);
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
	
}

	

