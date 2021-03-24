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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageobjects.FrontPage;
import testsuitebase.ReTryFail;
import testsuitebase.SuiteBase;
import testsuitebase.TestResultStatus;
import utility.Read_XLS;
import utility.SuiteUtility;

public class Whatif_TC extends SuiteBase {

	Read_XLS FilePath = null;
	String TestCaseName = null;
	String SheetName = "Whatif";
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
	/*
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC001(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

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
				WF.WIAaddscenarioSearch( getDriver(), viewName,ReportName, Verify2, Verify3,Verify,Report,Verify4, reportingPeriod);
				
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC002(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

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
				WF.WIAaddTradeview( getDriver(), viewName,ReportName, Verify2, Verify3,Verify,Report,Verify4,reportingPeriod);
				
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC003(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

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
				WF.AggregationCheckcommon(getDriver(), viewName, actualViews, ReportName,Verify);

			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC004(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
				WF.WIAheader(getDriver(), viewName, actualViews, ReportName,Verify);		
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC005(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
				WF.WIAaddscenarioCreatenew(getDriver(), viewName, ReportName, Verify2,Verify3,Verify,Report);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC006(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
				WF.WIAaddscenarioSearch(getDriver(), viewName, ReportName, Verify2, Verify3, Verify,Report);		
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC007(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
				WF.WIAEditScenario(getDriver(), viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report);	
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC008(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

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
				WF.WIAaddscenarioSearchfacilty( getDriver(), viewName,ReportName, Verify2, Verify3,Verify,Report,Verify4,reportingPeriod);
				
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC009(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
				WF.WIASortfilterScenario(getDriver(), viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report,actualViews);	
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC010(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
		String actualViews2 = getData(data, "actualViews2");
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
				WF.WIAExpsoureCreate(getDriver(), viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC011(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
		String actualViews2 = getData(data, "actualViews2");
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
				WF.WIAExpsoureCreatedelete(getDriver(), viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC012(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {
	
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
		String actualViews2 = getData(data, "actualViews2");
		String Report = getData(data, "Report");
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
				WF. WIAExpsoureCreatedownload(getDriver(), viewName,ReportName,Verify2,Verify3,Verify,Verify4,Report,actualViews,actualViews2, FileNameac,Fileformat);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC013(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {
	
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
		String actualViews2 = getData(data, "actualViews2");
		String Report = getData(data, "Report");
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
				WF. WIAExpsoureCreatedownloadselect(getDriver(), viewName,ReportName,Verify2,Verify3,Verify,Verify4,Report,actualViews,actualViews2, FileNameac,Fileformat);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC014(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {
	
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
		String actualViews2 = getData(data, "actualViews2");
		String Report = getData(data, "Report");
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
				WF.WIAExpsoureSortfilter(getDriver(), viewName,ReportName,Verify2,Verify3,Verify,Verify4,Report,actualViews,actualViews2);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC015(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

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
				WF.WIAExpsoureSearchnew(getDriver(),viewName,ReportName, Verify2, Verify3, Verify, Verify4, Report,reportingPeriod);
				
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC016(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

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
		String actualViews2 = getData(data, "actualViews2");
		String ID = getData(data, "ID");
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
				WF.WIAExpsoureEdit(getDriver(),viewName,ReportName, Verify2, Verify3, Verify, Verify4, Report,actualViews,actualViews2,ID);
		
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC017(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
		String actualViews2 = getData(data, "actualViews2");
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
				WF.WIACollCreate(getDriver(), viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC018(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
		String actualViews2 = getData(data, "actualViews2");
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
				WF.WIACollCreatedelete(getDriver(), viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC019(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {
	
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
		String actualViews2 = getData(data, "actualViews2");
		String Report = getData(data, "Report");
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
				WF. WIACollCreatedownload(getDriver(), viewName,ReportName,Verify2,Verify3,Verify,Verify4,Report,actualViews,actualViews2, FileNameac,Fileformat);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC020(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {
	
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
		String actualViews2 = getData(data, "actualViews2");
		String Report = getData(data, "Report");
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
				WF. WIACollCreatedownloadselect(getDriver(), viewName,ReportName,Verify2,Verify3,Verify,Verify4,Report,actualViews,actualViews2, FileNameac,Fileformat);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC021(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {
	
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
		String actualViews2 = getData(data, "actualViews2");
		String Report = getData(data, "Report");
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
				WF.WIACollSortfilter(getDriver(), viewName,ReportName,Verify2,Verify3,Verify,Verify4,Report,actualViews,actualViews2);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC022(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

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
				WF.WIACollSearchnew(getDriver(),viewName,ReportName, Verify2, Verify3, Verify, Verify4, Report,reportingPeriod);
				
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC023(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

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
		String actualViews2 = getData(data, "actualViews2");
		String ID = getData(data, "ID");
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
				WF.WIACollEdit(getDriver(),viewName,ReportName, Verify2, Verify3, Verify, Verify4, Report,actualViews,actualViews2,ID);
		
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC024(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
		String actualViews2 = getData(data, "actualViews2");
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
				WF.WIACollCreate(getDriver(), viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC025(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
		String actualViews2 = getData(data, "actualViews2");
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
				WF.WIACollCreatedelete(getDriver(), viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC026(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {
	
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
		String actualViews2 = getData(data, "actualViews2");
		String Report = getData(data, "Report");
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
				WF. WIACollCreatedownload(getDriver(), viewName,ReportName,Verify2,Verify3,Verify,Verify4,Report,actualViews,actualViews2, FileNameac,Fileformat);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC027(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {
	
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
		String actualViews2 = getData(data, "actualViews2");
		String Report = getData(data, "Report");
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
				WF. WIACollCreatedownloadselect(getDriver(), viewName,ReportName,Verify2,Verify3,Verify,Verify4,Report,actualViews,actualViews2, FileNameac,Fileformat);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC028(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {
	
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
		String actualViews2 = getData(data, "actualViews2");
		String Report = getData(data, "Report");
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
				WF.WIACollSortfilter(getDriver(), viewName,ReportName,Verify2,Verify3,Verify,Verify4,Report,actualViews,actualViews2);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC029(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

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
				WF.WIACollSearchnew(getDriver(),viewName,ReportName, Verify2, Verify3, Verify, Verify4, Report,reportingPeriod);
				
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC030(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

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
		String actualViews2 = getData(data, "actualViews2");
		String ID = getData(data, "ID");
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
				WF.WIACollEdit(getDriver(),viewName,ReportName, Verify2, Verify3, Verify, Verify4, Report,actualViews,actualViews2,ID);
		
			}
		}
	}
	
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC032(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

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
		String actualViews2 = getData(data, "actualViews2");
		String ID = getData(data, "ID");
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
				WF.WIAEligcheckerror(getDriver(),viewName,ReportName, Verify2, Verify3, Verify, Verify4, Report,actualViews,actualViews2,ID);
		
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC033(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

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
		String actualViews2 = getData(data, "actualViews2");
		String ID = getData(data, "ID");
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
				WF. WIAEligcheckdownload(getDriver(), viewName,ReportName,Verify2,Verify3,Verify,Verify4,Report, actualViews, actualViews2,FileNameac,Fileformat,ID);
		
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC034(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

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
				WF.WIAheader(getDriver(), viewName, actualViews, ReportName,Verify);

			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC035(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
				WF.WIAaddscenarioCreatenew(getDriver(), viewName, ReportName, Verify2,Verify3,Verify,Report);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC036(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
				WF.WIAaddscenarioSearch(getDriver(), viewName, ReportName, Verify2, Verify3, Verify,Report);		
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC037(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
				WF.WIAEditScenario(getDriver(), viewName, ReportName, Verify2, Verify3, Verify, Verify4, Report);	
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC038(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

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
				WF.WIAPortfolioIDsearch( getDriver(), viewName,ReportName, Verify2, Verify3,Verify,Report,Verify4);
				
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC039(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
		String Inputtext1 = getData(data, "Inputtext1");
		String Inputtext2 = getData(data, "Inputtext2");
		String Inputtext3 = getData(data, "Inputtext3");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String actualViews3 = getData(data, "actualViews3");
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
				WF.WIAaddTradeCreatenew(getDriver(), viewName, ReportName, Verify2,Verify3,Verify,Verify4,Report,Inputtext1,Inputtext2,Inputtext3,actualViews,actualViews2,actualViews3);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC040(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
		String Inputtext1 = getData(data, "Inputtext1");
		String Inputtext2 = getData(data, "Inputtext2");
		String Inputtext3 = getData(data, "Inputtext3");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String actualViews3 = getData(data, "actualViews3");
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
				WF.WIAaddTradeClonenew(getDriver(), viewName, ReportName, Verify2,Verify3,Verify,Verify4,Report,Inputtext1,Inputtext2,Inputtext3,actualViews,actualViews2,actualViews3);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC041(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
		String Inputtext1 = getData(data, "Inputtext1");
		String Inputtext2 = getData(data, "Inputtext2");
		String Inputtext3 = getData(data, "Inputtext3");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String actualViews3 = getData(data, "actualViews3");
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
				WF.WIAaddTradeDelete(getDriver(), viewName, ReportName, Verify2,Verify3,Verify,Verify4,Report,Inputtext1,Inputtext2,Inputtext3,actualViews,actualViews2,actualViews3);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC042(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {
	
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
		String Inputtext1 = getData(data, "Inputtext1");
		String Inputtext2 = getData(data, "Inputtext2");
		String Inputtext3 = getData(data, "Inputtext3");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String actualViews3 = getData(data, "actualViews3");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
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
				WF.WIAaddTradeDownload(getDriver(), viewName, ReportName, Verify2,Verify3,Verify,Verify4,Report,Inputtext1,Inputtext2,Inputtext3,actualViews,actualViews2,actualViews3,Fileformat,FileNameac);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC043(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {
	
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
		String Inputtext1 = getData(data, "Inputtext1");
		String Inputtext2 = getData(data, "Inputtext2");
		String Inputtext3 = getData(data, "Inputtext3");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String actualViews3 = getData(data, "actualViews3");
		String actualViews4 = getData(data, "actualViews4");
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
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
				WF.WIAaddTradeSortfilter(getDriver(), viewName, ReportName, Verify2,Verify3,Verify,Verify4,Report,Inputtext1,Inputtext2,Inputtext3,actualViews,actualViews2,actualViews3,actualViews4);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC044(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
		String actualViews2 = getData(data, "actualViews2");
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
				WF.WIACSAviewCreate(getDriver(), viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC045(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
		String actualViews2 = getData(data, "actualViews2");
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
				WF.WIACSAviewCloneEdit(getDriver(), viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC046(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
	
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
		String actualViews2 = getData(data, "actualViews2");
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
				WF.WICSADelete(getDriver(), viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC047(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {
	
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
		String actualViews2 = getData(data, "actualViews2");
		String Report = getData(data, "Report");
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
				WF.WICSADownload(getDriver(), viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2,Fileformat,FileNameac);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC048(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {
	
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
		String actualViews2 = getData(data, "actualViews2");
		String actualViews3 = getData(data, "actualViews3");
		String Report = getData(data, "Report");
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
				WF.WIACSASortfilter(getDriver(), viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2,actualViews3);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC049(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

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
		String actualViews2 = getData(data, "actualViews2");
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
				WF.WIPortfolioResultsDownload(getDriver(), viewName, ReportName,Verify2, Verify3, Verify, Verify4, Report, actualViews,actualViews2,Fileformat,FileNameac,reportingPeriod);
				
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "Whatif", dataProviderClass = utility.Xlsdataprovider.class, groups = "SACCR", alwaysRun = true)
	public void Whatif_TC050(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

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
		String Inputtext1 = getData(data, "Inputtext1");
		String Inputtext2 = getData(data, "Inputtext2");
		String Inputtext3 = getData(data, "Inputtext3");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String actualViews3 = getData(data, "actualViews3");
		String reportingPeriod = getData(data, "reportingPeriod");
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
				WF.WIATradeHypothetical(getDriver(), viewName, ReportName, Verify2,Verify3,Verify,Verify4,Report,Inputtext1,Inputtext2,Inputtext3,actualViews,actualViews2,actualViews3,reportingPeriod);
				
			}
		}
	}
*/

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

