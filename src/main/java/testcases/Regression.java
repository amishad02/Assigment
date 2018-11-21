package testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testsuitebase.SuiteBase;
import testsuitebase.TestResultStatus;
import utility.Read_XLS;
import utility.SuiteUtility;

public class Regression extends SuiteBase {

	Read_XLS FilePath = null;
	String TestCaseName = null;
	String Environment;

	@BeforeTest(alwaysRun = true)
	public void setUp() throws IOException {
		init();
		FilePath = TestCaseListExcelsearchContains;
		URLs = TestCaseListExcelsearchContains.getEnvUrl("Environment");
		for (String env : URLs.keySet()) {
			Environment = env;
		}
	}

	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC1(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.filtersCheck(getDriver(), AddRule, Filtername);											
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void RegTC44(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.filtersCheck(getDriver(), AddRule, Filtername);											
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC2(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.filtersCheck(getDriver(), AddRule, Filtername);											
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC3(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.filtersCheck(getDriver(), AddRule, Filtername);											
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC4(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.filtersCheck(getDriver(), AddRule, Filtername);											
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC5(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.filtersCheck(getDriver(), AddRule, Filtername);											
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC6(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.filtersCheck(getDriver(), AddRule, Filtername);											
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC7(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.filtersCheck(getDriver(), AddRule, Filtername);											
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC8(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC9(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String category = getData(data, "Category");
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);	
			/*HP.controlUnitGeography(getDriver(),AddRule,Level1,Level2,Level3,Level4);*/											
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC10(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC11(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC12(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.filtersCheck(getDriver(), AddRule, Filtername);											
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC13(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC14(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC15(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC16(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC17(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC18(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");		
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.IDReviewResultCheck(getDriver(), AddRule, Filtername);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC19(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC20(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC21(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");		
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.IDReviewResultCheck(getDriver(), AddRule, Filtername);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC22(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC23(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC24(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.topExposuresPopupCheck(getDriver(), AddRule,Filtername);											
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC25(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.topExposuresPopupCheck(getDriver(), AddRule,Filtername);											
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC26(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.topExposuresPopupCheck(getDriver(), AddRule,Filtername);											
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC27(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC28(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");		
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.IDReviewResultCheck(getDriver(), AddRule, Filtername);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC29(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC30(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC31(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC32(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC33(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC34(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC35(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC36(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC37(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC38(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC39(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC40(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC41(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC42(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@Test(dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class)
	public void SanityTC43(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, AWTException {
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");	
		String AddRule = getData(data, "AddRule");
		String Filtername = getData(data, "Filtername");
		String VerifyChip = getData(data, "VerifyChip");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			loadWebBrowser(); 
			getDriver().get(URLs.get(Environment));
			Thread.sleep(2000);
			HP.checkLevel(getDriver(), AddRule, Filtername, VerifyChip);										
		}
	}
	
	@AfterMethod(alwaysRun = true)
	public void reporterDataResults(ITestResult Result) {
		if (Result.getStatus() == ITestResult.SKIP) {
			TestResult.put(Result.getName(), "SKIP");
			Reporter.log(Result.getName() + " is SKIPPED");
			Add_Log.info(Result.getName() + " is SKIPPED");
		} else if (Result.getStatus() == ITestResult.FAILURE) {
			TestResult.put(Result.getName(), "FAIL");
			captureScreenShot(Result, "FAIL", getDriver());
			Reporter.log(Result.getName() + " is FAIL");
			Add_Log.info(Result.getName() + " is FAIL");
			if (!(getDriver() == null)) {
				closeWebBrowser();
			}
		} else {
			TestResult.put(Result.getName(), "PASS");
			Reporter.log(Result.getName() + " is PASS");
			Add_Log.info(Result.getName() + " is PASS");
			if (!(getDriver() == null)) {
				closeWebBrowser();
			}
		}

	}
	
	

	@AfterSuite(alwaysRun = true)
	public void aftersuite() {
		SuiteUtility.WriteResultUtility(FilePath, "Regression", "Pass/Fail/Skip", TestResult);
		objFD.reportLog("RegressionSh_Tests", "SanityReport", "xls");
	}
}
