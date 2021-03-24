package testcases;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ISuiteResult;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageobjects.FrontPage;
import testsuitebase.ReTryFail;
import testsuitebase.SuiteBase;
import testsuitebase.TestResultStatus;
import utility.Read_XLS;
import utility.SuiteUtility;

public class Whatif_TCv2 extends SuiteBase {

	Read_XLS FilePath = null;
	String TestCaseName = null;
	String SheetName = "Whatifv2";
	String ResultSheetName = "Result";
	public boolean TestCasePass = true;
	String Collateral_view = null;
	public static String Sub_FolderName = "";
	public static String FolderName_New = "";
	public boolean Testskip = false;
	public boolean Testfail = false;/*
	public double totalTime;*/
	int fileCount = 0;
	public int i = 0;
	public HashMap<String, WebDriver> driverInstanceTL = new HashMap<>();
	static public HashMap<String, String> TestResultTL = new HashMap<>();
	static public HashMap<String, String> Resmark = new HashMap<>();
	static public ArrayList<String> failureReasons = new ArrayList<>();
	// public String methodName =
	// Thread.currentThread().getStackTrace()[1].getMethodName();
	public static String resultout = null;
	String Environment;
	String parent;

	public FrontPage FP = new FrontPage();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws IOException {
		// System.out.println("before");

		init();
		// System.out.println("after");
		FilePath = TestCaseListExcelsearchContains;
		URLs = TestCaseListExcelsearchContains.getEnvUrl("Environment");
		System.out.println(URLs + " URLs");
		// System.out.println(URLs.size());
		for (String env : URLs.keySet()) {
			Environment = env;
		}

	}
	@BeforeMethod(alwaysRun = true)	
	public void BeforeMethod() {
		sleepRandomly();
	}
		
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC001(LinkedHashMap<String, String> data) throws InterruptedException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String reportingPeriod = getData(data, "reportingPeriod");		
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.WIAaddscenarioSearch( getDriver(), viewName,ReportName, Verify2, Verify3,Verify,Report,Verify4,actualViews,InputactualViews, reportingPeriod);

			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC002(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.WIAaddTradeview( getDriver(), viewName,ReportName, Verify2, Verify3,Verify,Report,Verify4,reportingPeriod);

			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC003(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String actualViews = getData(data, "actualViews");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.AggregationCheckcommon(getDriver(), viewName, actualViews, ReportName,Verify);

			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC004(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String actualViews = getData(data, "actualViews");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.WIAheader(getDriver(), viewName, actualViews, ReportName,Verify);		
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC005(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.WIAaddscenarioCreatetodaydate(getDriver(), viewName, ReportName, Verify2,Verify3,Verify,Report);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dependsOnMethods="WhatifBBK_TC005", dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC006(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.WIAaddscenarioSearch(getDriver(), viewName, ReportName, Verify2, Verify3, Verify,Report);		
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC007(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.EditScenario(getDriver(), viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report);	
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC008(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.ScenarioSearchfacilty( getDriver(), viewName,ReportName, Verify2, Verify3,Verify,Verify4,Report,reportingPeriod);				
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC009(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.ScenarioSortfilter(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,actualViews);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC010(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.CreateExposure(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,actualViews,InputactualViews);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC011(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String reportingPeriod = getData(data, "reportingPeriod");	
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.ExposureDelete(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC012(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		String reportingPeriod = getData(data, "reportingPeriod");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.ExposureDownload(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod,Fileformat, FileNameac);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC013(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		String reportingPeriod = getData(data, "reportingPeriod");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.ExposureDownloadSelect(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod,Fileformat, FileNameac);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC014(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		String reportingPeriod = getData(data, "reportingPeriod");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.ExposureSortFilter(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod,actualViews);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC015(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		String reportingPeriod = getData(data, "reportingPeriod");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.WIAaddscenarioCreatenew(getDriver(), viewName, ReportName, Verify2sp[0], Verify3, Verify, Reportsp[0]);
				WFBBK.ExposureSearch(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC016(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		String reportingPeriod = getData(data, "reportingPeriod");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.WIAaddscenarioCreatenew(getDriver(), viewName, ReportName, Verify2sp[0], Verify3, Verify, Reportsp[0]);
				WFBBK.ExposureSearch(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod);
				WFBBK.ExposureCloneEdit(getDriver(),viewName, ReportName, Verify2, Verify3, Verify, Report, Verify4, reportingPeriod, actualViews, InputactualViews);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC017(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.CreateExposure(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,actualViews,InputactualViews);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC018(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String reportingPeriod = getData(data, "reportingPeriod");	
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.ExposureDelete(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC019(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		String reportingPeriod = getData(data, "reportingPeriod");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.ExposureDownload(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod,Fileformat, FileNameac);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC020(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		String reportingPeriod = getData(data, "reportingPeriod");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.ExposureDownloadSelect(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod,Fileformat, FileNameac);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC021(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		String reportingPeriod = getData(data, "reportingPeriod");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.ExposureSortFilter(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod,actualViews);
			}
		}
	}


	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC022(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		String reportingPeriod = getData(data, "reportingPeriod");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.WIAaddscenarioCreatenew(getDriver(), viewName, ReportName, Verify2sp[0], Verify3, Verify, Reportsp[0]);
				WFBBK.ExposureSearch(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC023(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		String reportingPeriod = getData(data, "reportingPeriod");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.WIAaddscenarioCreatenew(getDriver(), viewName, ReportName, Verify2sp[0], Verify3, Verify, Reportsp[0]);
				WFBBK.ExposureSearch(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod);
				WFBBK.ExposureCloneEdit(getDriver(),viewName, ReportName, Verify2, Verify3, Verify, Report, Verify4, reportingPeriod, actualViews, InputactualViews);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC024(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.CreateExposure(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,actualViews,InputactualViews);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC025(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String reportingPeriod = getData(data, "reportingPeriod");	
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.ExposureDelete(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC026(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		String reportingPeriod = getData(data, "reportingPeriod");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.ExposureDownload(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod,Fileformat, FileNameac);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC027(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		String reportingPeriod = getData(data, "reportingPeriod");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.ExposureDownloadSelect(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod,Fileformat, FileNameac);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC028(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		String reportingPeriod = getData(data, "reportingPeriod");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.ExposureSortFilter(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod,actualViews);
			}
		}
	}


	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC029(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		String reportingPeriod = getData(data, "reportingPeriod");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.WIAaddscenarioCreatenew(getDriver(), viewName, ReportName, Verify2sp[0], Verify3, Verify, Reportsp[0]);
				WFBBK.ExposureSearch(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC030(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String Report = getData(data, "Report");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		String reportingPeriod = getData(data, "reportingPeriod");
		String[] Verify2sp = Verify2.split("\\,");
		String[] Reportsp = Report.split("\\,");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFBBK.WIAaddscenarioCreatenew(getDriver(), viewName, ReportName, Verify2sp[0], Verify3, Verify, Reportsp[0]);
				WFBBK.ExposureSearch(getDriver(),viewName,ReportName, Verify2,Verify3,Verify,Report,Verify4,reportingPeriod);					
				WFBBK.ExposureCloneEdit(getDriver(),viewName, ReportName, Verify2, Verify3, Verify, Report, Verify4, reportingPeriod, actualViews, InputactualViews);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC032(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String reportingPeriod = getData(data, "reportingPeriod");		
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.EligibilityCheckError(getDriver(), viewName,ReportName, Verify2, Verify3,Verify,Report,Verify4,actualViews,InputactualViews, reportingPeriod);

			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "BBK", alwaysRun = true)
	public void WhatifBBK_TC033(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String InputactualViews = getData(data, "InputactualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFBBK.WIAaddscenarioSearch(getDriver(), viewName,ReportName, Verify2, Verify3,Verify,Report,Verify4,actualViews,InputactualViews, reportingPeriod);
				WFBBK.EligibilityCheckdownload(getDriver(), viewName,ReportName, Verify2, Verify3,Verify,Report,Verify4,actualViews,InputactualViews, reportingPeriod,Fileformat,FileNameac);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC034(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String actualViews = getData(data, "actualViews");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.WIAheader(getDriver(), viewName, actualViews, ReportName,Verify);		
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC035(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.CapitalWhtif(getDriver(), viewName, ReportName,Verify);
				WFSACCR.WIAaddscenarioCreatetodaydate(getDriver(), viewName, ReportName, Verify2,Verify3,Verify,Report);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC036(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.SearchPortolio(getDriver(), viewName, ReportName, Verify2, Verify3, Verify, Report, Verify4, reportingPeriod);		
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC037(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.EditPortolio(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC038(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.SearchPortolioID(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC039(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		String InputactualViews = getData(data, "InputactualViews");
		String Inputtext1 = getData(data, "Inputtext1");
		String Inputtext2 = getData(data, "Inputtext2");
		String Inputtext3 = getData(data, "Inputtext3");
		String actualViews2 = getData(data, "actualViews2");
		String actualViews3 = getData(data, "actualViews3");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.CreateTrade(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3,InputactualViews);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC040(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		String InputactualViews = getData(data, "InputactualViews");
		String Inputtext1 = getData(data, "Inputtext1");
		String Inputtext2 = getData(data, "Inputtext2");
		String Inputtext3 = getData(data, "Inputtext3");
		String actualViews2 = getData(data, "actualViews2");
		String actualViews3 = getData(data, "actualViews3");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.TradeClone(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,InputactualViews,actualViews);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC041(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.TradeDelete(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC042(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.TradeDownload(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Fileformat, FileNameac);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC043(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.TradeSortFilter(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,actualViews);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC044(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		String InputactualViews = getData(data, "InputactualViews");
		String Inputtext1 = getData(data, "Inputtext1");
		String Inputtext2 = getData(data, "Inputtext2");
		String Inputtext3 = getData(data, "Inputtext3");
		String actualViews2 = getData(data, "actualViews2");
		String actualViews3 = getData(data, "actualViews3");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.TradeHypothetical(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,InputactualViews,actualViews);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC045(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		String InputactualViews = getData(data, "InputactualViews");
		String Inputtext1 = getData(data, "Inputtext1");
		String Inputtext2 = getData(data, "Inputtext2");
		String Inputtext3 = getData(data, "Inputtext3");
		String actualViews2 = getData(data, "actualViews2");
		String actualViews3 = getData(data, "actualViews3");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.CreateCSA(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3,InputactualViews);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC046(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		String InputactualViews = getData(data, "InputactualViews");
		String Inputtext1 = getData(data, "Inputtext1");
		String Inputtext2 = getData(data, "Inputtext2");
		String Inputtext3 = getData(data, "Inputtext3");
		String actualViews2 = getData(data, "actualViews2");
		String actualViews3 = getData(data, "actualViews3");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.CSAClone(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,InputactualViews,actualViews);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC047(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.CSADelete(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC048(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.CSADownload(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Fileformat, FileNameac);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC049(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String actualViews4 = getData(data, "actualViews4");
		String reportingPeriod = getData(data, "reportingPeriod");	
		String InputactualViews = getData(data, "InputactualViews");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.CSASortFilter(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,actualViews,InputactualViews,actualViews4);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC050(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.Resultdownload( getDriver(), viewName,ReportName, Verify2, Verify3,Verify,Report,Verify4,reportingPeriod,Fileformat,FileNameac,actualViews);

			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC051(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		String InputactualViews = getData(data, "InputactualViews");
		String Inputtext1 = getData(data, "Inputtext1");
		String Inputtext2 = getData(data, "Inputtext2");
		String Inputtext3 = getData(data, "Inputtext3");
		String actualViews2 = getData(data, "actualViews2");
		String actualViews3 = getData(data, "actualViews3");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.CreateCSA(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3,InputactualViews);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC052(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		String InputactualViews = getData(data, "InputactualViews");
		String Inputtext1 = getData(data, "Inputtext1");
		String Inputtext2 = getData(data, "Inputtext2");
		String Inputtext3 = getData(data, "Inputtext3");
		String actualViews2 = getData(data, "actualViews2");
		String actualViews3 = getData(data, "actualViews3");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.CSAClone(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,InputactualViews,actualViews);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC053(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.CSADelete(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC054(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");	
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.CSADownload(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Fileformat, FileNameac);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC055(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String actualViews4 = getData(data, "actualViews4");
		String reportingPeriod = getData(data, "reportingPeriod");	
		String InputactualViews = getData(data, "InputactualViews");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.CSASortFilter(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,actualViews,InputactualViews,actualViews4);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void WhatifSACCR_TC056(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Report = getData(data, "Report");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				WFSACCR.Resultdownload(getDriver(), viewName,ReportName, Verify2, Verify3,Verify,Report,Verify4,reportingPeriod,Fileformat,FileNameac,actualViews);

			}
		}
	}
	
	//New test cases from Harika 
	
	   @Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	    public void WhatifSACCR_TC057(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

	        TestCaseName = getData(data, "TestCaseName");
	        CaseToRun = getData(data, "CaseToRun");
	        String Instance = getData(data, "Instance");
	        String Role = getData(data, "Role");
	        String viewName = getData(data, "viewName");
	        String ReportName = getData(data, "ReportName");
	        String Report = getData(data, "Report");
	        String Verify = getData(data, "Verify");
	        String Verify2 = getData(data, "Verify2");
	        String Verify3 = getData(data, "Verify3");
	        String Verify4 = getData(data, "Verify4");
	        String actualViews = getData(data, "actualViews");
	        String reportingPeriod = getData(data, "reportingPeriod");
	        if (CaseToRun.equalsIgnoreCase("N")) {
	            System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
	            Testskip = true;
	            throw new SkipException(
	                    TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
	        } else {
	            for (String Key : URLs.keySet()) {
	                System.out.println(URLs.get(Key));
	                credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
	                for (int j = 0; j < credentials.size(); j++) {
	                    users = credentials.get(j);
	                    username = users.get("username");
	                    encPass = users.get("password");
	                }
	                password = dpass.decryptUserPassword(encPass);
	                loadWebBrowser();
	                objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
	                WFSACCR.WIaddverifyTrade( getDriver(), viewName,ReportName, Verify2, Verify3,Verify,Report,Verify4,reportingPeriod);

	            }
	        }
	    }

	    @Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC058(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			String InputactualViews = getData(data, "InputactualViews");
			String Inputtext1 = getData(data, "Inputtext1");
			String Inputtext2 = getData(data, "Inputtext2");
			String Inputtext3 = getData(data, "Inputtext3");
			String actualViews2 = getData(data, "actualViews2");
			String actualViews3 = getData(data, "actualViews3");
			if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.CreateTrade(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3,InputactualViews);
					WFSACCR.CreateHTtrade(getDriver());
					WFSACCR.VerifyPortfolioResults(getDriver());
					Thread.sleep(6000);
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC059(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			String InputactualViews = getData(data, "InputactualViews");
			String Inputtext1 = getData(data, "Inputtext1");
			String Inputtext2 = getData(data, "Inputtext2");
			String Inputtext3 = getData(data, "Inputtext3");
			String actualViews2 = getData(data, "actualViews2");
			String actualViews3 = getData(data, "actualViews3");
			if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.WIAaddTradeview( getDriver(), viewName,ReportName, Verify2, Verify3,Verify,Report,Verify4,reportingPeriod);
					WFSACCR.VerifyRWAProfileResults(getDriver());
					WFSACCR.VerifyTradeEADResults(getDriver());
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC060(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String InputactualViews = getData(data, "InputactualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.SearchPortolioID(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
					WFSACCR.validate_column_code(getDriver(),InputactualViews,actualViews);
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC061(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String InputactualViews = getData(data, "InputactualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.SearchPortolioID(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
					WFSACCR.validate_column_code(getDriver(),InputactualViews,actualViews);
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC062(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String InputactualViews = getData(data, "InputactualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.SearchPortolioID(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
					WFSACCR.validate_column_code(getDriver(),InputactualViews,actualViews);
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC063(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String InputactualViews = getData(data, "InputactualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.SearchPortolioID(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
					WFSACCR.validate_column_code(getDriver(),InputactualViews,actualViews);
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC064(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String InputactualViews = getData(data, "InputactualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.SearchPortolioID(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
					WFSACCR.validate_column_code(getDriver(),InputactualViews,actualViews);
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC066(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String InputactualViews = getData(data, "InputactualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.SearchPortolioID(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
					WFSACCR.validate_column_code(getDriver(),InputactualViews,actualViews);
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC067(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String InputactualViews = getData(data, "InputactualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.SearchPortolioID(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
					WFSACCR.validate_column_code(getDriver(),InputactualViews,actualViews);
				}
			}
		}

	    @Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	    public void WhatifSACCR_TC068(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

	        TestCaseName = getData(data, "TestCaseName");
	        CaseToRun = getData(data, "CaseToRun");
	        String Instance = getData(data, "Instance");
	        String Role = getData(data, "Role");
	        String viewName = getData(data, "viewName");
	        String ReportName = getData(data, "ReportName");
	        String Report = getData(data, "Report");
	        String Verify = getData(data, "Verify");
	        String Verify2 = getData(data, "Verify2");
	        String Verify3 = getData(data, "Verify3");
	        String Verify4 = getData(data, "Verify4");
	        String actualViews = getData(data, "actualViews");
	        String reportingPeriod = getData(data, "reportingPeriod");
	        if (CaseToRun.equalsIgnoreCase("N")) {
	            System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
	            Testskip = true;
	            throw new SkipException(
	                    TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
	        } else {
	            for (String Key : URLs.keySet()) {
	                System.out.println(URLs.get(Key));
	                credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
	                for (int j = 0; j < credentials.size(); j++) {
	                    users = credentials.get(j);
	                    username = users.get("username");
	                    encPass = users.get("password");
	                }
	                password = dpass.decryptUserPassword(encPass);
	                loadWebBrowser();
	                objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
	                WFSACCR.SearchPortolioID(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
	                WFSACCR.MarginTcs(getDriver(),"One To One","Existing Portfolio");
	                Thread.sleep(3000);
	            }
	        }
	    }

	    @Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	    public void WhatifSACCR_TC069(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

	        TestCaseName = getData(data, "TestCaseName");
	        CaseToRun = getData(data, "CaseToRun");
	        String Instance = getData(data, "Instance");
	        String Role = getData(data, "Role");
	        String viewName = getData(data, "viewName");
	        String ReportName = getData(data, "ReportName");
	        String Report = getData(data, "Report");
	        String Verify = getData(data, "Verify");
	        String Verify2 = getData(data, "Verify2");
	        String Verify3 = getData(data, "Verify3");
	        String Verify4 = getData(data, "Verify4");
	        String actualViews = getData(data, "actualViews");
	        String reportingPeriod = getData(data, "reportingPeriod");
	        if (CaseToRun.equalsIgnoreCase("N")) {
	            System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
	            Testskip = true;
	            throw new SkipException(
	                    TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
	        } else {
	            for (String Key : URLs.keySet()) {
	                System.out.println(URLs.get(Key));
	                credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
	                for (int j = 0; j < credentials.size(); j++) {
	                    users = credentials.get(j);
	                    username = users.get("username");
	                    encPass = users.get("password");
	                }
	                password = dpass.decryptUserPassword(encPass);
	                loadWebBrowser();
	                objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
	                WFSACCR.SearchPortolioID(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
	                WFSACCR.MarginTcs(getDriver(),"One To Multiple","Existing Portfolio");
	            }
	        }
	    }

	    @Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	    public void WhatifSACCR_TC070(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

	        TestCaseName = getData(data, "TestCaseName");
	        CaseToRun = getData(data, "CaseToRun");
	        String Instance = getData(data, "Instance");
	        String Role = getData(data, "Role");
	        String viewName = getData(data, "viewName");
	        String ReportName = getData(data, "ReportName");
	        String Report = getData(data, "Report");
	        String Verify = getData(data, "Verify");
	        String Verify2 = getData(data, "Verify2");
	        String Verify3 = getData(data, "Verify3");
	        String Verify4 = getData(data, "Verify4");
	        String actualViews = getData(data, "actualViews");
	        String reportingPeriod = getData(data, "reportingPeriod");
	        if (CaseToRun.equalsIgnoreCase("N")) {
	            System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
	            Testskip = true;
	            throw new SkipException(
	                    TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
	        } else {
	            for (String Key : URLs.keySet()) {
	                System.out.println(URLs.get(Key));
	                credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
	                for (int j = 0; j < credentials.size(); j++) {
	                    users = credentials.get(j);
	                    username = users.get("username");
	                    encPass = users.get("password");
	                }
	                password = dpass.decryptUserPassword(encPass);
	                loadWebBrowser();
	                objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
	                WFSACCR.SearchPortolioID(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
	                WFSACCR.MarginTcs(getDriver(),"Unmargin","Existing Portfolio");

	            }
	        }
	    }

	    @Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC071(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String InputactualViews = getData(data, "InputactualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.SearchPortolioID(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
					WFSACCR.TradeCountValidation(getDriver(),"Small");
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC072(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String InputactualViews = getData(data, "InputactualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.SearchPortolioID(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
					WFSACCR.TradeCountValidation(getDriver(),"Medium");
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC073(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String InputactualViews = getData(data, "InputactualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.SearchPortolioID(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod);
					WFSACCR.TradeCountValidation(getDriver(),"Large");
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC074(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			String InputactualViews = getData(data, "InputactualViews");
			String Inputtext1 = getData(data, "Inputtext1");
			String Inputtext2 = getData(data, "Inputtext2");
			String Inputtext3 = getData(data, "Inputtext3");
			String actualViews2 = getData(data, "actualViews2");
			String actualViews3 = getData(data, "actualViews3");
	        String marginalID = getData(data, "ID");

	        if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.CreateTrade_New(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3,InputactualViews,marginalID);
					WFSACCR.validate_column_code(getDriver(),InputactualViews,actualViews);
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC075(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			String InputactualViews = getData(data, "InputactualViews");
			String Inputtext1 = getData(data, "Inputtext1");
			String Inputtext2 = getData(data, "Inputtext2");
			String Inputtext3 = getData(data, "Inputtext3");
			String actualViews2 = getData(data, "actualViews2");
			String actualViews3 = getData(data, "actualViews3");
	        String marginalID = getData(data, "ID");

	        if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.CreateTrade_New(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3,InputactualViews,marginalID);
					WFSACCR.validate_column_code(getDriver(),InputactualViews,actualViews);
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC076(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			String InputactualViews = getData(data, "InputactualViews");
			String Inputtext1 = getData(data, "Inputtext1");
			String Inputtext2 = getData(data, "Inputtext2");
			String Inputtext3 = getData(data, "Inputtext3");
			String actualViews2 = getData(data, "actualViews2");
			String actualViews3 = getData(data, "actualViews3");
	        String marginalID = getData(data, "ID");

	        if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.CreateTrade_New(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3,InputactualViews,marginalID);
					WFSACCR.validate_column_code(getDriver(),InputactualViews,actualViews);
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC077(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			String InputactualViews = getData(data, "InputactualViews");
			String Inputtext1 = getData(data, "Inputtext1");
			String Inputtext2 = getData(data, "Inputtext2");
			String Inputtext3 = getData(data, "Inputtext3");
			String actualViews2 = getData(data, "actualViews2");
			String actualViews3 = getData(data, "actualViews3");
	        String marginalID = getData(data, "ID");
	        if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.CreateTrade_New(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3,InputactualViews,marginalID);
					WFSACCR.validate_column_code(getDriver(),InputactualViews,actualViews);
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC078(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			String InputactualViews = getData(data, "InputactualViews");
			String Inputtext1 = getData(data, "Inputtext1");
			String Inputtext2 = getData(data, "Inputtext2");
			String Inputtext3 = getData(data, "Inputtext3");
			String actualViews2 = getData(data, "actualViews2");
			String actualViews3 = getData(data, "actualViews3");
	        String marginalID = getData(data, "ID");
	        if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.CreateTrade_New(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3,InputactualViews,marginalID);
					WFSACCR.validate_column_code(getDriver(),InputactualViews,actualViews);
				}
			}
		}

		@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
		public void WhatifSACCR_TC081(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

			TestCaseName = getData(data, "TestCaseName");
			CaseToRun = getData(data, "CaseToRun");
			String Instance = getData(data, "Instance");
			String Role = getData(data, "Role");
			String viewName = getData(data, "viewName");
			String ReportName = getData(data, "ReportName");
			String Report = getData(data, "Report");
			String Verify = getData(data, "Verify");
			String Verify2 = getData(data, "Verify2");
			String Verify3 = getData(data, "Verify3");
			String Verify4 = getData(data, "Verify4");
			String actualViews = getData(data, "actualViews");
			String reportingPeriod = getData(data, "reportingPeriod");
			String InputactualViews = getData(data, "InputactualViews");
			String Inputtext1 = getData(data, "Inputtext1");
			String Inputtext2 = getData(data, "Inputtext2");
			String Inputtext3 = getData(data, "Inputtext3");
			String actualViews2 = getData(data, "actualViews2");
			String actualViews3 = getData(data, "actualViews3");
	        String marginalID = getData(data, "ID");

	        if (CaseToRun.equalsIgnoreCase("N")) {
				System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
				Testskip = true;
				throw new SkipException(
						TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
			} else {
				for (String Key : URLs.keySet()) {
					System.out.println(URLs.get(Key));
					credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
					for (int j = 0; j < credentials.size(); j++) {
						users = credentials.get(j);
						username = users.get("username");
						encPass = users.get("password");
					}
					password = dpass.decryptUserPassword(encPass);
					loadWebBrowser();
					objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
					WFSACCR.CreateTrade_New(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3,InputactualViews,marginalID);
					WFSACCR.validate_column_code(getDriver(),InputactualViews,actualViews);
				}
			}
		}

	    @Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	    public void WhatifSACCR_TC082(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

	        TestCaseName = getData(data, "TestCaseName");
	        CaseToRun = getData(data, "CaseToRun");
	        String Instance = getData(data, "Instance");
	        String Role = getData(data, "Role");
	        String viewName = getData(data, "viewName");
	        String ReportName = getData(data, "ReportName");
	        String Report = getData(data, "Report");
	        String Verify = getData(data, "Verify");
	        String Verify2 = getData(data, "Verify2");
	        String Verify3 = getData(data, "Verify3");
	        String Verify4 = getData(data, "Verify4");
	        String actualViews = getData(data, "actualViews");
	        String reportingPeriod = getData(data, "reportingPeriod");
	        String InputactualViews = getData(data, "InputactualViews");
	        String Inputtext1 = getData(data, "Inputtext1");
	        String Inputtext2 = getData(data, "Inputtext2");
	        String Inputtext3 = getData(data, "Inputtext3");
	        String actualViews2 = getData(data, "actualViews2");
	        String actualViews3 = getData(data, "actualViews3");
	        String marginalID = getData(data, "ID");
	        if (CaseToRun.equalsIgnoreCase("N")) {
	            System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
	            Testskip = true;
	            throw new SkipException(
	                    TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
	        } else {
	            for (String Key : URLs.keySet()) {
	                System.out.println(URLs.get(Key));
	                credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
	                for (int j = 0; j < credentials.size(); j++) {
	                    users = credentials.get(j);
	                    username = users.get("username");
	                    encPass = users.get("password");
	                }
	                password = dpass.decryptUserPassword(encPass);
	                loadWebBrowser();
	                objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
	                WFSACCR.create_twoHTTrade(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3,InputactualViews,marginalID);
	                WFSACCR.MarginTcs(getDriver(),"One To One","HT Trade");
	            }
	        }
	    }

	    @Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	    public void WhatifSACCR_TC083(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

	        TestCaseName = getData(data, "TestCaseName");
	        CaseToRun = getData(data, "CaseToRun");
	        String Instance = getData(data, "Instance");
	        String Role = getData(data, "Role");
	        String viewName = getData(data, "viewName");
	        String ReportName = getData(data, "ReportName");
	        String Report = getData(data, "Report");
	        String Verify = getData(data, "Verify");
	        String Verify2 = getData(data, "Verify2");
	        String Verify3 = getData(data, "Verify3");
	        String Verify4 = getData(data, "Verify4");
	        String actualViews = getData(data, "actualViews");
	        String reportingPeriod = getData(data, "reportingPeriod");
	        String InputactualViews = getData(data, "InputactualViews");
	        String Inputtext1 = getData(data, "Inputtext1");
	        String Inputtext2 = getData(data, "Inputtext2");
	        String Inputtext3 = getData(data, "Inputtext3");
	        String actualViews2 = getData(data, "actualViews2");
	        String actualViews3 = getData(data, "actualViews3");
	        String marginalID = getData(data, "ID");
	        if (CaseToRun.equalsIgnoreCase("N")) {
	            System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
	            Testskip = true;
	            throw new SkipException(
	                    TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
	        } else {
	            for (String Key : URLs.keySet()) {
	                System.out.println(URLs.get(Key));
	                credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
	                for (int j = 0; j < credentials.size(); j++) {
	                    users = credentials.get(j);
	                    username = users.get("username");
	                    encPass = users.get("password");
	                }
	                password = dpass.decryptUserPassword(encPass);
	                loadWebBrowser();
	                objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
	                WFSACCR.create_twoHTTrade(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3,InputactualViews,marginalID);
	                WFSACCR.MarginTcs(getDriver(),"One To Multiple","HT Trade");
	            }
	        }
	    }

	    @Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	    public void WhatifSACCR_TC084(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

	        TestCaseName = getData(data, "TestCaseName");
	        CaseToRun = getData(data, "CaseToRun");
	        String Instance = getData(data, "Instance");
	        String Role = getData(data, "Role");
	        String viewName = getData(data, "viewName");
	        String ReportName = getData(data, "ReportName");
	        String Report = getData(data, "Report");
	        String Verify = getData(data, "Verify");
	        String Verify2 = getData(data, "Verify2");
	        String Verify3 = getData(data, "Verify3");
	        String Verify4 = getData(data, "Verify4");
	        String actualViews = getData(data, "actualViews");
	        String reportingPeriod = getData(data, "reportingPeriod");
	        String InputactualViews = getData(data, "InputactualViews");
	        String Inputtext1 = getData(data, "Inputtext1");
	        String Inputtext2 = getData(data, "Inputtext2");
	        String Inputtext3 = getData(data, "Inputtext3");
	        String actualViews2 = getData(data, "actualViews2");
	        String actualViews3 = getData(data, "actualViews3");
	        String marginalID = getData(data, "ID");
	        if (CaseToRun.equalsIgnoreCase("N")) {
	            System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
	            Testskip = true;
	            throw new SkipException(
	                    TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
	        } else {
	            for (String Key : URLs.keySet()) {
	                System.out.println(URLs.get(Key));
	                credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
	                for (int j = 0; j < credentials.size(); j++) {
	                    users = credentials.get(j);
	                    username = users.get("username");
	                    encPass = users.get("password");
	                }
	                password = dpass.decryptUserPassword(encPass);
	                loadWebBrowser();
	                objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
	                WFSACCR.CreateUnmarginedHTTrade(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3,InputactualViews,"Yes");
	                WFSACCR.MarginTcs(getDriver(),"Unmargin","HT Trade");
	            }
	        }
	    }

	    @Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	    public void WhatifSACCR_TC085(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

	        TestCaseName = getData(data, "TestCaseName");
	        CaseToRun = getData(data, "CaseToRun");
	        String Instance = getData(data, "Instance");
	        String Role = getData(data, "Role");
	        String viewName = getData(data, "viewName");
	        String ReportName = getData(data, "ReportName");
	        String Report = getData(data, "Report");
	        String Verify = getData(data, "Verify");
	        String Verify2 = getData(data, "Verify2");
	        String Verify3 = getData(data, "Verify3");
	        String Verify4 = getData(data, "Verify4");
	        String actualViews = getData(data, "actualViews");
	        String reportingPeriod = getData(data, "reportingPeriod");
	        String ReportNameTitle = getData(data, "ReportNameTitle");
	        String ID = getData(data, "ID");
	        String FileNameac=getData(data,"FileNameac");
	        String InputactualViews = getData(data, "InputactualViews");
	        String Inputtext1 = getData(data, "Inputtext1");
	        String Inputtext2 = getData(data, "Inputtext2");
	        String Inputtext3 = getData(data, "Inputtext3");
	        String actualViews2 = getData(data, "actualViews2");
	        String actualViews3 = getData(data, "actualViews3");
	        if (CaseToRun.equalsIgnoreCase("N")) {
	            System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
	            Testskip = true;
	            throw new SkipException(
	                    TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
	        } else {
	            for (String Key : URLs.keySet()) {
	                System.out.println(URLs.get(Key));
	                credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
	                for (int j = 0; j < credentials.size(); j++) {
	                    users = credentials.get(j);
	                    username = users.get("username");
	                    encPass = users.get("password");
	                }
	                password = dpass.decryptUserPassword(encPass);
	                loadWebBrowser();
	                objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
	                WFSACCR.CreateTrade(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3,InputactualViews);
	                WFSACCR.UploadMTMValues(getDriver(),ReportNameTitle,ID,FileNameac);
	                WFSACCR.CreateHTtrade(getDriver());

	            }
	        }
	    }

	    @Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatifv2", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	    public void WhatifSACCR_TC086(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

	        TestCaseName = getData(data, "TestCaseName");
	        CaseToRun = getData(data, "CaseToRun");
	        String Instance = getData(data, "Instance");
	        String Role = getData(data, "Role");
	        String viewName = getData(data, "viewName");
	        String ReportName = getData(data, "ReportName");
	        String Report = getData(data, "Report");
	        String Verify = getData(data, "Verify");
	        String Verify2 = getData(data, "Verify2");
	        String Verify3 = getData(data, "Verify3");
	        String Verify4 = getData(data, "Verify4");
	        String actualViews = getData(data, "actualViews");
	        String reportingPeriod = getData(data, "reportingPeriod");
	        String InputactualViews = getData(data, "InputactualViews");
	        String Inputtext1 = getData(data, "Inputtext1");
	        String Inputtext2 = getData(data, "Inputtext2");
	        String Inputtext3 = getData(data, "Inputtext3");
	        String actualViews2 = getData(data, "actualViews2");
	        String actualViews3 = getData(data, "actualViews3");
	        String ReportNameTitle = getData(data, "ReportNameTitle");
	        String ID = getData(data, "ID");
	        String FileNameac=getData(data,"FileNameac");

	        if (CaseToRun.equalsIgnoreCase("N")) {
	            System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
	            Testskip = true;
	            throw new SkipException(
	                    TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
	        } else {
	            for (String Key : URLs.keySet()) {
	                System.out.println(URLs.get(Key));
	                credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
	                for (int j = 0; j < credentials.size(); j++) {
	                    users = credentials.get(j);
	                    username = users.get("username");
	                    encPass = users.get("password");
	                }
	                password = dpass.decryptUserPassword(encPass);
	                loadWebBrowser();
	                objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
	                WFSACCR.CreateUnmarginedHTTrade(getDriver(),viewName,ReportName, Verify2, Verify3,Verify,Report, Verify4, reportingPeriod,Inputtext1, Inputtext2, Inputtext3, actualViews, actualViews2, actualViews3,InputactualViews,"No");
	                WFSACCR.validate_column_code(getDriver(),ReportNameTitle,"0");
	                WFSACCR.UploadMTMValues(getDriver(),ReportNameTitle,ID,FileNameac);
	               // WFSACCR.UploadMTMValues(getDriver(),"CMTM","","MTMTradeUpload");
	            }
	        }
	    }
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@AfterMethod(alwaysRun = true)
	public void reporterDataResults(ITestResult Result) throws InterruptedException, IOException {
		boolean flag = false;
		Testfail = TestResultStatus.Testfail;

		/*System.out.println("test fail flag in AfterMethod: " + Testfail); */
		if (Result.getStatus() == ITestResult.SKIP) {
			Resmark.put(Result.getName(), "");
			try {
			captureScreenShot(Result, "SKIP", getDriver());
			}
			catch (Exception e1) {

			} 
			Reporter.log(Result.getName() + " is SKIPPED");
			Add_Log.info(Result.getName() + " is SKIPPED");
			TestResultTL.put(Result.getName(), "SKIP");
			if (!(getDriver() == null)) {
				closeWebBrowser();
			}
		} else if (Result.getStatus() == ITestResult.FAILURE) {
			
			Collection<String> values = TestResultStatus.mpaskeysnew.get(Result.getName());
			String resultout = String.join(" | ", values);
			System.out.println(resultout);
			Resmark.put(Result.getName(), resultout);

			captureScreenShot(Result, "FAIL", getDriver());
			Reporter.log(Result.getName() + " is FAIL");
			Add_Log.info(Result.getName() + " is FAIL");
			if (!(getDriver() == null)) {
				closeWebBrowser();
			}
			TestResultTL.put(Result.getName(), "FAIL");
			
		} else {
		//	captureScreenShot(Result, "PASS", getDriver());
			Collection<String> values = TestResultStatus.mpaskeysnew.get(Result.getName());
			String resultout = String.join(" | ", values);
			System.out.println(resultout);
			Resmark.put(Result.getName(), resultout);
			Resmark.put(Result.getName(), "");
			Reporter.log(Result.getName() + " is PASS");
			Add_Log.info(Result.getName() + " is PASS");
			if (!(getDriver() == null)) {
				closeWebBrowser();
			}
			TestResultTL.put(Result.getName(), "PASS");

		}
		Testskip = false;
		TestResultStatus.Testfail = false;

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@AfterClass(alwaysRun = true)
	public void afterclass() throws IOException {

		SuiteUtility.WriteResultUtilitynew(FilePath, SheetName, "Remarkresult", Resmark);
		SuiteUtility.WriteResultUtility(FilePath, SheetName, "Pass/Fail/Skip", TestResultTL);
	}


	@AfterSuite(alwaysRun = true)
	public void aftersuite() throws IOException {
		objFD.reportLog("Dashboard_Tests", "Report", "xls");
		if (System.getProperty("os.name").contains("Windows")) {
			Process process = Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
			process.destroy();
		}
		/*
		 * if (i==2){
		 * System.out.println(" 2 ERROR occured :::: java programm has stopped!!!");
		 * Reporter.
		 * log(" 2 Over 20 Test Cases Selenium Execution Stopped contact devops team");
		 * Add_Log.
		 * info("2  Over 20 Test Cases Selenium Execution Stopped contact devops team");
		 * Assert.fail(); }
		 */
	}


}

