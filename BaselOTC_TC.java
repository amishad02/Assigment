package testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.FrontPage;
import testsuitebase.ReTryFail;
import testsuitebase.SuiteBase;
import testsuitebase.TestResultStatus;
import utility.Read_XLS;
import utility.SuiteUtility;

public class BaselOTC_TC extends SuiteBase {
	
	Read_XLS FilePath = null;
	String TestCaseName = null;
	String SheetName = "BaselEE";
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
	
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC001(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.Adhoc(getDriver(), viewName, Verify,ReportName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC002(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.Adhoc(getDriver(), viewName, Verify,ReportName);
				BOTC.OTCHeader1(getDriver(), viewName, Verify, ReportName);
			}
		}
	}


	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC003(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
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
				BOTC.portfoliodownload(getDriver(),  viewName,  Verify,  Verify3,  ReportName, Fileformat, FileNameac);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC004(LinkedHashMap<String, String> data) throws InterruptedException {

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
				//BOTC.Adhoc( getDriver(),  viewName,  Verify,  ReportName);
				//BOTC.OTCHeader1(getDriver(), viewName,  ReportName,  Verify);
				BOTC.portfoliochart(getDriver(), viewName,  Verify, Verify3, ReportName);
				//BOTC.selectorinalportfolioID1(getDriver(), viewName, Verify3, ReportName, actualViews, actualViews2);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC005(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.portfoliocalculate(getDriver(), viewName,  Verify, Verify3, ReportName);
				
				
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC006(LinkedHashMap<String, String> data) throws InterruptedException {

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
				//BOTC.Adhoc(getDriver(), viewName, Verify);
				//BOTC.OTCHeader1(getDriver(), viewName,  ReportName,  Verify);
				BOTC.portfoliofilter(getDriver(), viewName,  Verify, Verify3, Verify4, ReportName);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC007(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
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
				//BOTC.Adhoc(getDriver(), viewName, Verify);
				//BOTC.OTCHeader1(getDriver(), viewName,  ReportName,  Verify);
				BOTC.pricingstrategydownload( getDriver(),  viewName,  Verify,  Verify3,  ReportName, Fileformat, FileNameac);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC008(LinkedHashMap<String, String> data) throws InterruptedException {

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
				//BOTC.Adhoc(getDriver(), viewName, Verify);
				//BOTC.OTCHeader1(getDriver(), viewName,  ReportName,  Verify);
				BOTC.pricingstrategychart(getDriver(), viewName,  Verify, Verify3, ReportName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC009(LinkedHashMap<String, String> data) throws InterruptedException {

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
				//BOTC.Adhoc(getDriver(), viewName, Verify);
				//BOTC.OTCHeader1(getDriver(), viewName,  ReportName,  Verify);
				BOTC.pricingstrategycalculate(getDriver(), viewName,  Verify, Verify3, ReportName);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC010(LinkedHashMap<String, String> data) throws InterruptedException {

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
				//BOTC.Adhoc(getDriver(), viewName, Verify);
				//BOTC.OTCHeader1(getDriver(), viewName,  ReportName,  Verify);
				BOTC.pricingstrategyfilter(getDriver(), viewName,  Verify, Verify3, Verify4, ReportName);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC011(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
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
				//BOTC.Adhoc( getDriver(),  viewName,  Verify,  ReportName);
				//BOTC.OTCHeader1( getDriver(),  viewName,  Verify3,  ReportName);
				BOTC.StressTypedownload(getDriver(), viewName, Verify4, Verify3, ReportName, Fileformat, FileNameac);
				
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC012(LinkedHashMap<String, String> data) throws InterruptedException {

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
				//BOTC.Adhoc(getDriver(), viewName, Verify);
				//BOTC.OTCHeader1(getDriver(), viewName,  ReportName,  Verify);
				BOTC.StressTypechart(getDriver(), viewName,  Verify, Verify3, ReportName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC013(LinkedHashMap<String, String> data) throws InterruptedException {

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
				//BOTC.Adhoc(getDriver(), viewName, Verify);
				//BOTC.OTCHeader1(getDriver(), viewName,  ReportName,  Verify);
				BOTC.StressTypecalculate(getDriver(), viewName,  Verify, Verify3, ReportName);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC014(LinkedHashMap<String, String> data) throws InterruptedException {

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
				//BOTC.Adhoc(getDriver(), viewName, Verify);
				//BOTC.OTCHeader1(getDriver(), viewName,  ReportName,  Verify);
				BOTC.StressTypefilter( getDriver(),  viewName,  Verify,  Verify3,   Verify4,  ReportName);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC015(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.Adhoc( getDriver(),  viewName,  Verify,  ReportName);
				BOTC.OTCHeader1( getDriver(),  viewName,  Verify,  ReportName);
				BOTC.multisorting(getDriver(), Verify, Verify3);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC016(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String FileNameac = getData(data, "FileNameac");
		String Fileformat = getData(data, "Fileformat");
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
				BOTC.Adhoc( getDriver(),  viewName,  Verify,  ReportName);
				BOTC.OTCHeader1( getDriver(),  viewName,  Verify,  ReportName);
				BOTC.Editdesignpanel(getDriver(), Verify,actualViews,viewName,  Verify3,  ReportName,reportingPeriod,  Fileformat, FileNameac);
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC017(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.Adhoc( getDriver(),  viewName,  Verify,  ReportName);
				BOTC.OTCHeader1( getDriver(),  viewName,  Verify3,  ReportName);
				BOTC.currncydropdown( getDriver(),  Verify);
				
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC018(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.Adhoc( getDriver(),  viewName,  Verify,  ReportName);
				BOTC.OTCHeader1( getDriver(),  viewName,  Verify3,  ReportName);
				BOTC.profilegraph( getDriver(), viewName,  Verify3,  ReportName);
				
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC019(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.Adhoc( getDriver(),  viewName,  Verify,  ReportName);
				BOTC.OTCHeader1( getDriver(),  viewName,  Verify3,  ReportName);
				BOTC.comparevalues( getDriver(), viewName,  Verify3,  ReportName,reportingPeriod);
				
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC020(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.Adhoc( getDriver(),  viewName,  Verify,  ReportName);
				BOTC.OTCHeader1( getDriver(),  viewName,  Verify3,  ReportName);
				BOTC.compareprofile(getDriver(), viewName, Verify3, Verify4, ReportName, reportingPeriod);
				
				
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC021(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.Adhoc( getDriver(),  viewName,  Verify,  ReportName);
				BOTC.OTCHeader1( getDriver(),  viewName,  Verify3,  ReportName);
				BOTC.selectorinalportfolioID(getDriver(), viewName, Verify3, ReportName);
				BOTC.headercount(getDriver(), actualViews,Verify);
				//BOTC.selectfunction(getDriver(), actualViews);
				//BOTC.headerclick(getDriver());
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC022(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.globalfilter(getDriver(), viewName, Verify2, Verify3, Verify4, ReportName, actualViews);
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC023(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.Locatetree(getDriver(), viewName, Verify2, Verify3, Verify4, ReportName, actualViews);
			}
		}
	} 
	
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC024(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.data_check(getDriver(), viewName, Verify4, Verify2, Verify3, ReportName, actualViews, actualViews2);
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC025(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.data_check(getDriver(), viewName, Verify4, Verify2, Verify3, ReportName, actualViews, actualViews2);
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC026(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.data_check(getDriver(), viewName, Verify4, Verify2, Verify3, ReportName, actualViews, actualViews2);
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC027(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.data_check(getDriver(), viewName, Verify4, Verify2, Verify3, ReportName, actualViews, actualViews2);
			}
		}
	} 
	
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC028(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.SCCC_comparevalues(getDriver(), viewName, Verify, Verify2, Verify3, Verify4, Report, ReportName, reportingPeriod);
				
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC029(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.Trade_comparevalues(getDriver(), viewName, Verify, Verify2, Verify3, Verify4, Report, ReportName, reportingPeriod);
				
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC030(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");	
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				BOTC.Trade_ViewMoment(getDriver(), viewName, Verify, Verify2, Verify3, Verify4, Report, ReportName, actualViews, actualViews2, reportingPeriod, Fileformat, FileNameac);
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC031(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");	
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				BOTC.Tradedetails(getDriver(), viewName, Verify, ReportName, Verify2, Verify3, Verify4, Report, reportingPeriod, Fileformat, FileNameac);
			
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC032(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");	
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				BOTC.OTC_Transaction(getDriver(), viewName, Verify, ReportName, Verify2, Report, reportingPeriod);
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC033(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");	
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				BOTC.open_transactions(getDriver(), viewName, Verify, ReportName, Verify2, Report,actualViews);
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC034(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");
		String Filter = getData(data, "Filter");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				BOTC.BVI_profilegraph(getDriver(), viewName, ReportName, Verify,Filter,Report,Verify2);
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC035(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");
		String Filter = getData(data, "Filter");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				BOTC.CR01_profilegraph(getDriver(), viewName, ReportName, Verify,Filter,Report,Verify2);
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC036(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");
		String Filter = getData(data, "Filter");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				BOTC.CR01_comparevalues(getDriver(), viewName, ReportName, Verify, Filter, Report, Verify2, Verify4,reportingPeriod);
				
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC037(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");
		String Filter = getData(data, "Filter");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				BOTC.CR01_compareprofile(getDriver(), viewName, ReportName, Verify, Filter, Report, Verify2, Verify4,reportingPeriod);
				
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC038(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");
		String Filter = getData(data, "Filter");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC039(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");
		String Filter = getData(data, "Filter");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				BOTC.CR01_profilegraph(getDriver(), viewName, ReportName, Verify,Filter,Report,Verify2);
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC040(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");
		String Filter = getData(data, "Filter");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				BOTC.CR01_comparevalues(getDriver(), viewName, ReportName, Verify, Filter, Report, Verify2, Verify4,reportingPeriod);
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC041(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");
		String Filter = getData(data, "Filter");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				BOTC.CR01_compareprofile(getDriver(), viewName, ReportName, Verify, Filter, Report, Verify2, Verify4,reportingPeriod);
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC042(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");
		String Filter = getData(data, "Filter");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				BOTC.CR01_comparevalues(getDriver(), viewName, ReportName, Verify, Filter, Report, Verify2, Verify4,reportingPeriod);
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC043(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");
		String Filter = getData(data, "Filter");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				BOTC.Credit_Spread_profilegraph(getDriver(), viewName, ReportName, Verify, Filter, Report, Verify2);
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC044(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");
		String Filter = getData(data, "Filter");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				BOTC.Credit_Spread_comparevalues(getDriver(), viewName, ReportName, Verify, Filter, Report, Verify2, Verify4, reportingPeriod);
			}
		}
	} 
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC045(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");
		String Filter = getData(data, "Filter");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				BOTC.Credit_Spread_compareprofile(getDriver(), viewName, ReportName, Verify, Filter, Report, Verify2, Verify4, reportingPeriod);
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC046(LinkedHashMap<String, String> data) throws InterruptedException, IOException {

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
		String reportingPeriod = getData(data, "reportingPeriod");
		String Filter = getData(data, "Filter");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
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
				
			}
		}
	} 
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC047(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.RWA_comparevalues(getDriver(), viewName, Verify, Verify2, Verify3, Verify4, Report, ReportName, reportingPeriod);
				
			}
		}
	} 

	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC048(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.SFTCounterparty_comparevalues(getDriver(), viewName, Verify, Verify2, Verify3, Verify4, Report, ReportName, reportingPeriod);
				
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC049(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.SFTExposure_comparevalues(getDriver(), viewName, Verify, Verify2, Verify3, Verify4, Report, ReportName, reportingPeriod);
				
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC050(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.SFTTrade_comparevalues(getDriver(), viewName, Verify, Verify2, Verify3, Verify4, Report, ReportName, reportingPeriod);
				
			}
		}
	} 
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "BaselEE", dataProviderClass = utility.Xlsdataprovider.class, groups = "OTC", alwaysRun = true)
	public void BaselEE_TC051(LinkedHashMap<String, String> data) throws InterruptedException {

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
				BOTC.SFTTrade_Portfolio_Decision_Tree(getDriver(), viewName, ReportName, actualViews, Verify, Verify2, Report);
				
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


