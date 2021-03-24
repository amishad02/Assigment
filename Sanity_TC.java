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

public class Sanity_TC extends SuiteBase {

	Read_XLS FilePath = null;
	String TestCaseName = null;
	String SheetName = "SanityTC";
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
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC000(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				FP.Checkcitirisk(getDriver(), methodName, actualViews, viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);

			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC001(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
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
				FP.viewsCheck(getDriver(), methodName, actualViews);
				SB.Snackbar(getDriver(), methodName);				
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC002(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
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
				FP.dataVisualizationCheck(getDriver(), methodName, actualViews, reportingPeriod, viewName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC003(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String ReportName = getData(data, "ReportName");
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
				DR.AggregationCheckcommon(getDriver(), methodName, actualViews, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC004(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String ReportName = getData(data, "ReportName");
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
				DR.AggregationCheckcommon(getDriver(), methodName, actualViews, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC005(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				FP.dataVisualizationCheckSummaryExposure(getDriver(), methodName, actualViews, reportingPeriod,
						viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC006(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				FP.dataVisualizationCheckSummaryExposure(getDriver(), methodName, actualViews, reportingPeriod,
						viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC007(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				FP.dataVisualizationCheckSummaryExposure(getDriver(), methodName, actualViews, reportingPeriod,
						viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC008(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				FP.dataVisualizationCheckSummaryExposure(getDriver(), methodName, actualViews, reportingPeriod,
						viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC009(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				FP.dataVisualizationCheckSummaryExposure(getDriver(), methodName, actualViews, reportingPeriod,
						viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC010(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				FP.dataVisualizationCheckSummaryExposure(getDriver(), methodName, actualViews, reportingPeriod,
						viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC011(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String Verify = getData(data, "Verify");
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
				FP.summaryLoad(getDriver(), methodName, reportingPeriod,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC012(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String ids = getData(data, "ID");
		String BusinessDay = getData(data, "BusinessDay");
		String Verify = getData(data, "Verify");
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
				FP.exposureLoad(getDriver(), methodName, reportingPeriod, ids, BusinessDay,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC013(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String BusinessDay = getData(data, "BusinessDay");
		String Verify = getData(data, "Verify");
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
				FP.summaryClear(getDriver(), methodName, reportingPeriod, BusinessDay,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC014(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String ids = getData(data, "ID");
		String BusinessDay = getData(data, "BusinessDay");
		String Verify = getData(data, "Verify");
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
				FP.exposureClear(getDriver(), methodName, reportingPeriod, ids, BusinessDay,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC015(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String ReportName = getData(data, "ReportName");
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
				FP.CheckSummaryExposure(getDriver(), methodName, actualViews, reportingPeriod, viewName, ReportName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC016(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String ReportName = getData(data, "ReportName");
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
				FP.CheckSummaryExposure(getDriver(), methodName, actualViews, reportingPeriod, viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC017(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String ReportName = getData(data, "ReportName");
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
				FP.CheckSummaryExposure(getDriver(), methodName, actualViews, reportingPeriod, viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC018(LinkedHashMap<String, String> data)
			throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String ReportName = getData(data, "ReportName");
		String Fileformat = getData(data, "Fileformat");
		String Verify = getData(data, "Verify");
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
				FP.downloadFileCheck(getDriver(), methodName, viewName, reportingPeriod, ReportName, Fileformat,
						Environment,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC019(LinkedHashMap<String, String> data)
			throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String ReportName = getData(data, "ReportName");
		String Fileformat = getData(data, "Fileformat");
		String Verify = getData(data, "Verify");
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
				FP.downloadFileCheck(getDriver(), methodName, viewName, reportingPeriod, ReportName, Fileformat,
						Environment,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC020(LinkedHashMap<String, String> data)
			throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String ReportName = getData(data, "ReportName");
		String Fileformat = getData(data, "Fileformat");
		String Verify = getData(data, "Verify");
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
				FP.downloadFileCheck(getDriver(), methodName, viewName, reportingPeriod, ReportName, Fileformat,
						Environment,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC021(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String ReportName = getData(data, "ReportName");
		String ids = getData(data, "ID");
		String RiskMetric = getData(data, "RiskMetric");
		String Verify = getData(data, "Verify");
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
				FP.CheckSortandFilteronly(getDriver(), methodName, viewName, ReportName, RiskMetric, ids,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC022(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String ReportName = getData(data, "ReportName");
		String ids = getData(data, "ID");
		String RiskMetric = getData(data, "RiskMetric");
		String Verify = getData(data, "Verify");
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
				FP.CheckSortandFilteronly22(getDriver(), methodName, viewName, ReportName, RiskMetric, ids,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC023(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String ReportName = getData(data, "ReportName");
		String ids = getData(data, "ID");
		String RiskMetric = getData(data, "RiskMetric");
		String Verify = getData(data, "Verify");
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
				FP.CheckSortandFilteronly(getDriver(), methodName, viewName, ReportName, RiskMetric, ids,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC024(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String ReportName = getData(data, "ReportName");
		String ids = getData(data, "ID");
		String RiskMetric = getData(data, "RiskMetric");
		String Verify = getData(data, "Verify");
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
				FP.CheckSortandFilterSummaryinto24(getDriver(), methodName, viewName, viewName, RiskMetric, ids,
						reportingPeriod,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC025(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String ReportName = getData(data, "ReportName");
		String ids = getData(data, "ID");
		String RiskMetric = getData(data, "RiskMetric");
		String Verify = getData(data, "Verify");
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
				FP.CheckSortandFilterSummaryinto25(getDriver(), methodName, viewName, viewName, RiskMetric, ids,
						reportingPeriod,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC026(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String ReportName = getData(data, "ReportName");
		String ids = getData(data, "ID");
		String RiskMetric = getData(data, "RiskMetric");
		String Verify = getData(data, "Verify");
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
				FP.CheckSortandFilterSummaryinto26(getDriver(), methodName, viewName, viewName, RiskMetric, ids,
						reportingPeriod,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	/////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC027(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				DR.dataVisualizationCriteriachecksanity(getDriver(), methodName, actualViews, actualViews2,
						reportingPeriod, viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC028(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
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
				DR.dataVisualizationCheckFiltersanity(getDriver(), methodName, actualViews, viewName, ReportName,
						Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC029(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				DR.dataVisualizationCriteriachecksanity(getDriver(), methodName, actualViews, actualViews2,
						reportingPeriod, viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
/*
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC030(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
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
				DR.dataVisualizationCheckFiltersanity(getDriver(), methodName, actualViews, viewName, ReportName,
						Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
*/
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC031(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				DR.dataVisualizationCriteriachecksanity(getDriver(), methodName, actualViews, actualViews2,
						reportingPeriod, viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC032(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
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
				DR.dataVisualizationCheckFiltersanity(getDriver(), methodName, actualViews, viewName, ReportName,
						Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC033(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				DR.dataVisualizationCriteriachecksanity(getDriver(), methodName, actualViews, actualViews2,
						reportingPeriod, viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC034(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
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
				DR.dataVisualizationCheckFiltersanity(getDriver(), methodName, actualViews, viewName, ReportName,
						Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC035(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				DR.dataVisualizationCriteriachecksanity(getDriver(), methodName, actualViews, actualViews2,
						reportingPeriod, viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC036(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
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
				DR.dataVisualizationCheckFiltersanity(getDriver(), methodName, actualViews, viewName, ReportName,
						Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC037(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				DR.dataVisualizationCriteriachecksanity(getDriver(), methodName, actualViews, actualViews2,
						reportingPeriod, viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC038(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
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
				DR.dataVisualizationCheckFiltersanity(getDriver(), methodName, actualViews, viewName, ReportName,
						Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC039(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				DR.dataVisualizationCriteriachecksanity(getDriver(), methodName, actualViews, actualViews2,
						reportingPeriod, viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC040(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
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
				DR.dataVisualizationCheckFiltersanity(getDriver(), methodName, actualViews, viewName, ReportName,
						Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC041(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				DR.dataVisualizationCriteriachecksanity(getDriver(), methodName, actualViews, actualViews2,
						reportingPeriod, viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC042(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
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
				DR.dataVisualizationCheckFiltersanity(getDriver(), methodName, actualViews, viewName, ReportName,
						Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC043(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				DR.dataVisualizationCriteriachecksanity(getDriver(), methodName, actualViews, actualViews2,
						reportingPeriod, viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC044(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
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
				DR.riskmeasurecheckfiltersanity(getDriver(), methodName, actualViews, Verify, reportingPeriod, viewName,
						ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	/////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC045(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String ReportName = getData(data, "ReportName");
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
				DR.AggregationCheckcommon(getDriver(), methodName, actualViews, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

// SCCL tc move to Daily  

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC058(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String ReportName = getData(data, "ReportName");
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
				DR.AggregationCheckcommon(getDriver(), methodName, actualViews, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC059(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				DR.dataOnDemandRevaluation(getDriver(), methodName, actualViews, reportingPeriod, viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC060(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				DR.dataOnDemandBAUTransactions(getDriver(), methodName, actualViews, reportingPeriod, viewName,
						ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Sanity_TC061(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				DR.ToolsCreateicon(getDriver(), methodName, actualViews, reportingPeriod, viewName, ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Tools", alwaysRun = true)
	public void Sanity_TC062(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String Verify = getData(data, "Verify");
		String ReportName = getData(data, "ReportName");
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
				TB.toolSearchx(getDriver(), methodName,actualViews,Verify,ReportName);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Tools", alwaysRun = true)
	public void Sanity_TC063(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String Verify = getData(data, "Verify");
		String ReportName = getData(data, "ReportName");
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
				TB.breadCrumbCheck(getDriver(), methodName, ReportName, viewName,Verify);
				//SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Tools", alwaysRun = true)
	public void Sanity_TC064(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String Verify = getData(data, "Verify");
		String ReportName = getData(data, "ReportName");
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
				TB.cobCompare(getDriver(), methodName,ReportName,viewName);
				//SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Tools", alwaysRun = true)
	public void Sanity_TC065(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.greenLightCheck(getDriver(), methodName, actualViews);
				//SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Tools", alwaysRun = true)
	public void Sanity_TC066(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.adhocAnalysischeck(getDriver(), methodName, actualViews,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Tools", alwaysRun = true)
	public void Sanity_TC067(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.adjustmentsCheck1(getDriver(), methodName, actualViews,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Tools", alwaysRun = true)
	public void Sanity_TC068(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.operationalReportsCheck(getDriver(), methodName, actualViews);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Tools", alwaysRun = true)
	public void Sanity_TC069(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.overlaysGatewayCheck(getDriver(), methodName, actualViews);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Tools", alwaysRun = true)
	public void Sanity_TC070(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.managementReportingCheck(getDriver(), methodName, actualViews);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Tools", alwaysRun = true)
	public void Sanity_TC071(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.baselViewCheck(getDriver(), methodName, actualViews);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Tools", alwaysRun = true)
	public void Sanity_TC072(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.BBTCCARcheck(getDriver(), methodName, actualViews);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Tools", alwaysRun = true)
	public void Sanity_TC073(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.dataDictionaryCheck(getDriver(), methodName, actualViews);
				
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Tools", alwaysRun = true)
	public void Sanity_TC074(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String Verify = getData(data, "Verify");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.iconHelp(getDriver(), methodName, actualViews,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "Tools", alwaysRun = true)
	public void Sanity_TC075(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.costOfCreditCheck(getDriver(), methodName, actualViews);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "GreenLight", alwaysRun = true)
	public void Sanity_TC076(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String Verify = getData(data, "Verify");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.greenLightWindowCheck(getDriver(), methodName, actualViews,Verify);
				//SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "GreenLight", alwaysRun = true)
	public void Sanity_TC077(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String Verify = getData(data, "Verify");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.greenLightWindowCheck(getDriver(), methodName, actualViews,Verify);
				//SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "GreenLight", alwaysRun = true)
	public void Sanity_TC078(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String Verify = getData(data, "Verify");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.greenLightWindowCheck(getDriver(), methodName, actualViews,Verify);
				//SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "GreenLight", alwaysRun = true)
	public void Sanity_TC079(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String Verify = getData(data, "Verify");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.greenLightWindowCheck(getDriver(), methodName, actualViews,Verify);
				//SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "GreenLight", alwaysRun = true)
	public void Sanity_TC080(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String Verify = getData(data, "Verify");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.greenLightWindowCheck(getDriver(), methodName, actualViews,Verify);
				//SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "GreenLight", alwaysRun = true)
	public void Sanity_TC081(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String Verify = getData(data, "Verify");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.verifyReset(getDriver(), methodName, actualViews,Verify);
				//SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTCTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "GreenLight", alwaysRun = true)
	public void Sanity_TC082(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String SearchInToolbar_Input = getData(data, "SearchInToolbar_Input");
		String actualViews = getData(data, "actualViews");
		String reportingPeriod = getData(data, "reportingPeriod");
		String Verify = getData(data, "Verify");
		String viewName = getData(data, "viewName");
		String ReportName = getData(data, "ReportName");
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
				TB.onDemandRevolutionFilterCheck(getDriver(), methodName, actualViews,ReportName,viewName);
				//DR.onDemandRevolutionFilterCheck(getDriver(), methodName, actualViews,ReportName,viewName);
				//SB.Snackbar(getDriver(), methodName);
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
		//	captureScreenShot(Result, "SKIP", getDriver());
			Reporter.log(Result.getName() + " is SKIPPED");
			Add_Log.info(Result.getName() + " is SKIPPED");
			TestResultTL.put(Result.getName(), "SKIP");
			if (!(getDriver() == null)) {
				closeWebBrowser();
			}
		} else if (Result.getStatus() == ITestResult.FAILURE) {
			// i++;  //increase counter here
			/*
			 * int success_tests = ITestResult.FAILURE; if (success_tests> 5) {
			 * System.out.println("Over 5 failed"); }
			 */
			
			/*
			 Map<String, String> map = new HashMap<>() ; String methodName =
			 Thread.currentThread().getStackTrace()[1].getMethodName(); for (String str :
			 TestResultStatus.failureReason) { map.put(methodName, str);
			 System.out.println(map); System.out.println(Resmark); }
			 */
			// System.out.println(TestResultStatus.failureReason);

			/*
			 String[] values = TestResultStatus.map.values().toArray(new String[0]);
			 System.out.println(Arrays.toString(values)); String resultout =
			 String.join(" | ", values); System.out.println(resultout);
			 */
			/*
			 resultout = String.join(" | ", TestResultStatus.failureReason);
			 System.out.println(resultout);
			 */

			// Resmark.put(methodName, resultout);

			// TestResultStatus.mpaskeysnew.get(methodName);

			Collection<String> values = TestResultStatus.mpaskeysnew.get(Result.getName());
			String resultout = String.join(" | ", values);
			System.out.println(resultout);
			// String keysname = TestResultStatus.mpaskeysnew.keys().toString();
			Resmark.put(Result.getName(), resultout);

			captureScreenShot(Result, "FAIL", getDriver());
			Reporter.log(Result.getName() + " is FAIL");
			Add_Log.info(Result.getName() + " is FAIL");
			if (!(getDriver() == null)) {
				closeWebBrowser();
			}
			TestResultTL.put(Result.getName(), "FAIL");
			/*
			 * if (i==2){ // stop execution here
			 * 
			 * afterclass(); aftersuite();
			 * 
			 * throw new SkipException ("Skipping Test: "); }
			 */
			
			
		} else {
		//	captureScreenShot(Result, "PASS", getDriver());
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

