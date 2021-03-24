package testcases;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testsuitebase.ReTryFail;
import testsuitebase.SuiteBase;
import testsuitebase.TestResultStatus;
import utility.Read_XLS;
import utility.SuiteUtility;

public class Time_Load extends SuiteBase {

	Read_XLS FilePath = null;
	Read_XLS FilePath1 = null;
	String TestCaseName = null;
	String SheetName = "Time_Load";
	String ResultSheetName = "Result";
	public boolean TestCasePass = true;
	public int DataSet = -1;
	public DecimalFormat df = new DecimalFormat("#.##");
	public String testName = null;
	public boolean Testskip = false;
	public boolean Testfail = false;
	SoftAssert s_assert = null;
	public double finish, start;
	public double end;
	public int k = 0;
	public HashMap<String, WebDriver> driverInstanceTL = new HashMap<>();
	static public HashMap<String, String> TestResultTL = new HashMap<>();
	static public HashMap<String, String> LoadTime = new HashMap<>();
	static public HashMap<String, String> Resmark = new HashMap<>();
	public static String resultout =null;
	String Environment;

	@BeforeTest(alwaysRun = true)
	public void setUp() throws IOException {
		init();
		FilePath = TestCaseListExcelsearchContains;
		URLs = TestCaseListExcelsearchContains.getEnvUrl("Environment");
		 System.out.println( URLs +" URLs");
		for (String env : URLs.keySet()) {
			Environment = env;
		}
	}
	@BeforeMethod(alwaysRun = true)	
	public void BeforeMethod() {
		sleepRandomly();
	}
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "EE", alwaysRun = true)
	public void Timeload_TC001(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");
		testName = getData(data, "Test_Name");
		String Role = getData(data, "Role");
		String Instance = getData(data, "Instance");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
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
				String totaltime = TL.summaryGridLoadTime(getDriver(),methodName, viewName, reportingPeriod,Verify);
				LoadTime.put(methodName, totaltime);
				
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "EE", alwaysRun = true)
	public void Timeload_TC002(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		testName = getData(data, "Test_Name");
		String Role = getData(data, "Role");
		String ReportName = getData(data, "ReportName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ID = getData(data, "ID");
		String RiskMetric = getData(data, "RiskMetric");
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
				String totaltime = TL.downloadLoadTime(getDriver(),methodName, ReportName, viewName, RiskMetric, Fileformat, reportingPeriod, ID,Environment,Verify);
				LoadTime.put(methodName, totaltime);
				
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "EE", alwaysRun = true)
	public void Timeload_TC003(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");
		String Instance = getData(data, "Instance");
		testName = getData(data, "Test_Name");
		String Role = getData(data, "Role");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
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
				String totaltime = TL.summaryGridLoadTime(getDriver(),methodName, viewName, reportingPeriod,Verify);
				LoadTime.put(methodName, totaltime);
				
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "EE", alwaysRun = true)
	public void Timeload_TC004(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		testName = getData(data, "Test_Name");
		String Role = getData(data, "Role");
		String ReportName = getData(data, "ReportName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ID = getData(data, "ID");
		String RiskMetric = getData(data, "RiskMetric");
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
				String totaltime = TL.downloadLoadTime(getDriver(),methodName, ReportName, viewName, RiskMetric, Fileformat, reportingPeriod, ID,Environment,Verify);
				LoadTime.put(methodName, totaltime);
				
			}
		}
	}
	
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "EE", alwaysRun = true)
	public void Timeload_TC005(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");
		String Instance = getData(data, "Instance");
		testName = getData(data, "Test_Name");
		String Role = getData(data, "Role");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
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
				String totaltime = TL.summaryGridLoadTime(getDriver(),methodName, viewName, reportingPeriod,Verify);
				LoadTime.put(methodName, totaltime);
				
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "EE", alwaysRun = true)
	public void Timeload_TC006(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		testName = getData(data, "Test_Name");
		String Role = getData(data, "Role");
		String ReportName = getData(data, "ReportName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ID = getData(data, "ID");
		String RiskMetric = getData(data, "RiskMetric");
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
				String totaltime = TL.downloadLoadTime(getDriver(),methodName, ReportName, viewName, RiskMetric, Fileformat, reportingPeriod, ID,Environment,Verify);
				LoadTime.put(methodName, totaltime);
				
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "EE", alwaysRun = true)
	public void Timeload_TC007(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");
		String Instance = getData(data, "Instance");
		testName = getData(data, "Test_Name");
		String Role = getData(data, "Role");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ID = getData(data, "ID");
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
				String totaltime = TL.exposureGridLoadTime(getDriver(), methodName,viewName, reportingPeriod, ID, RiskMetric,Verify);
				LoadTime.put(methodName, totaltime);
				
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "EE", alwaysRun = true)
	public void Timeload_TC008(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		testName = getData(data, "Test_Name");
		String Role = getData(data, "Role");
		String ReportName = getData(data, "ReportName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ID = getData(data, "ID");
		String RiskMetric = getData(data, "RiskMetric");
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
				String totaltime = TL.downloadLoadTime(getDriver(),methodName, ReportName, viewName, RiskMetric, Fileformat, reportingPeriod, ID,Environment,Verify);
				LoadTime.put(methodName, totaltime);
			
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "EE", alwaysRun = true)
	public void Timeload_TC009(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");
		testName = getData(data, "Test_Name");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ID = getData(data, "ID");
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
				String totaltime = TL.exposureGridLoadTime(getDriver(),methodName, viewName, reportingPeriod, ID, RiskMetric,Verify);
				LoadTime.put(methodName, totaltime);
				
			}
		}
	}
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "EE", alwaysRun = true)
	public void Timeload_TC010(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		testName = getData(data, "Test_Name");
		String Role = getData(data, "Role");
		String ReportName = getData(data, "ReportName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ID = getData(data, "ID");
		String RiskMetric = getData(data, "RiskMetric");
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
				String totaltime = TL.downloadLoadTime(getDriver(),methodName, ReportName, viewName, RiskMetric, Fileformat, reportingPeriod, ID,Environment,Verify);
				LoadTime.put(methodName, totaltime);
				
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "EE", alwaysRun = true)
	public void Timeload_TC011(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");
		testName = getData(data, "Test_Name");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ID = getData(data, "ID");
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
				String totaltime = TL.exposureGridLoadTime(getDriver(),methodName, viewName, reportingPeriod, ID, RiskMetric,Verify);
				LoadTime.put(methodName, totaltime);
				
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "SanityTest", dataProviderClass = utility.Xlsdataprovider.class, groups = "EE", alwaysRun = true)
	public void Timeload_TC012(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		TestCaseName = data.get("TestCaseName");
		CaseToRun = data.get("CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		testName = getData(data, "Test_Name");
		String Role = getData(data, "Role");
		String ReportName = getData(data, "ReportName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String viewName = getData(data, "viewName");
		String ID = getData(data, "ID");
		String RiskMetric = getData(data, "RiskMetric");
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
				String totaltime = TL.downloadLoadTime(getDriver(),methodName, ReportName, viewName, RiskMetric, Fileformat, reportingPeriod, ID,Environment,Verify);
				LoadTime.put(methodName, totaltime);
				
			}
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////

	@AfterMethod(alwaysRun = true)
	public void reporterDataResults(ITestResult Result) throws InterruptedException, IOException {
		Testfail = TestResultStatus.Testfail;
		if (Result.getStatus() == ITestResult.SKIP) {
			Resmark.put(Result.getName(), "");
			LoadTime.put(Result.getName(), "0");
		//	captureScreenShot(Result, "SKIP", getDriver());
			Reporter.log(Result.getName() + " is SKIPPED");
			Add_Log.info(Result.getName() + " is SKIPPED");
			TestResultTL.put(Result.getName(), "SKIP");
			if (!(getDriver() == null)) {
				closeWebBrowser();
			}
		} else if (Result.getStatus() == ITestResult.FAILURE) {
			// k++;  //increase counter here
			Collection<String> values = TestResultStatus.mpaskeysnew.get(Result.getName());
			System.out.println(values);
			String resultout = String.join(" | ", values);
			System.out.println(resultout);
			// String keysname = TestResultStatus.mpaskeysnew.keys().toString();
			Resmark.put(Result.getName(), resultout);
			LoadTime.put(Result.getName(), "0");
			captureScreenShot(Result, "FAIL", getDriver());
			Reporter.log(Result.getName() + " is FAIL");
			Add_Log.info(Result.getName() + " is FAIL");
			if (!(getDriver() == null)) {
				closeWebBrowser();
			}
			TestResultTL.put(Result.getName(), "FAIL");
			 
		} else {
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
	/*
	 * @AfterTest public void testUp() { getDriver().close(); getDriver().quit(); }
	 */

	@AfterClass(alwaysRun = true)
	public void afterclass() throws IOException {	
		SuiteUtility.WriteResultUtilitynew(FilePath, SheetName, "Remarkresult", Resmark);
		SuiteUtility.WriteResultUtility1(FilePath, SheetName, "TimeLoad", LoadTime);
		SuiteUtility.WriteResultUtility(FilePath, SheetName, "Pass/Fail/Skip", TestResultTL);

}
	@AfterSuite(alwaysRun = true)
	public void aftersuite() throws IOException {
		objFD.reportLog("Dashboard_Tests", "Report", "xls");
		if (System.getProperty("os.name").contains("Windows")) {
			Process process = Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
			process.destroy();
		}
		
	}


}

