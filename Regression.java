package testcases;

import java.io.IOException;
import java.sql.SQLException;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.FrontPage;
import testsuitebase.ReTryFail;
import testsuitebase.SuiteBase;
import testsuitebase.TestResultStatus;
import utility.Read_XLS;
import utility.SuiteUtility;

public class Regression extends SuiteBase {
	Read_XLS FilePath = null;
	String TestCaseName = null;
	String SheetName = "Regression";
	String ResultSheetName = "Result";
	public boolean TestCasePass = true;
	String Collateral_view = null;
	public static String Sub_FolderName = "";
	public static String FolderName_New = "";

	public boolean Testskip = false;
	public boolean Testfail = false;
	public double totalTime;
	int fileCount = 0;
	public int j = 0;
	public HashMap<String, WebDriver> driverInstanceTL = new HashMap<>();
	static public HashMap<String, String> TestResultTL = new HashMap<>();
	static public HashMap<String, String> Resmark = new HashMap<>();
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
		// System.out.println("------------" +FilePath +"FilePath " + URLs +"
		// URLs");
		// System.out.println(URLs.size());
		/*
		 * for (String env : URLs.keySet()) { Environment = env; }
		 */

	}
	@BeforeMethod(alwaysRun = true)	
	public void BeforeMethod() {
		sleepRandomly();
	}
	
 
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC001(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
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
				RP.summaryGridLoad(getDriver(), methodName, viewName,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC002(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");	
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

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC003(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");	
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
				RP.summaryGridLoad(getDriver(), methodName, viewName,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC004(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");		
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

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC005(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
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
				RP.summaryGridLoad(getDriver(), methodName, viewName,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC006(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
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

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC007(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
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
				RP.exposureGridLoad(getDriver(), methodName, viewName, ID, RiskMetric,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC008(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String ID = getData(data, "ID");
		String RiskMetric = getData(data, "RiskMetric");
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
				RP.exposureGridLoad(getDriver(), methodName, viewName, ID, RiskMetric,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC009(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");		
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
				RP.exposureGridLoad(getDriver(), methodName, viewName, ID, RiskMetric,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC010(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				RP.DDLExpanddown(getDriver(), methodName, actualViews, actualViews2, Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC011(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.DDLdragdrop2(getDriver(), methodName, actualViews, actualViews2, Verify2,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC012(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.DDLExpand1(getDriver(), methodName, actualViews, actualViews2, Verify2,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC013(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.DDLdragdrop2(getDriver(), methodName, actualViews, actualViews2, Verify2,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC014(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.DDLdragdrop2(getDriver(), methodName, actualViews, actualViews2, Verify2,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC015(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				RP.DDLExpanddown(getDriver(), methodName, actualViews, actualViews2, Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC016(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.DDLdragdrop2(getDriver(), methodName, actualViews, actualViews2, Verify2,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC017(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.DDList(getDriver(), methodName, actualViews, actualViews2,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC018(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.DDLdragdrop2(getDriver(), methodName, actualViews, actualViews2, Verify2,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC019(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.DDLdragdrop2(getDriver(), methodName, actualViews, actualViews2, Verify2,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC020(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.DDLdragdrop2(getDriver(), methodName, actualViews, actualViews2, Verify2,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC021(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.DDLdragdrop2(getDriver(), methodName, actualViews, actualViews2, Verify2,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC022(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.DDList(getDriver(), methodName, actualViews, actualViews2,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC023(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.DDLdragdrop2(getDriver(), methodName, actualViews, actualViews2, Verify2,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC024(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.DDLdragdrop2(getDriver(), methodName, actualViews, actualViews2, Verify2,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC025(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
		String Role = getData(data, "Role");
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
				RP.DDLdragdrop2(getDriver(), methodName, actualViews, actualViews2, Verify2,Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC026(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String actualViews = getData(data, "actualViews");
		String viewName = getData(data, "viewName");
		String Report = getData(data, "Report");
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
				RP.summaryGridExposurereport(getDriver(), methodName, viewName, actualViews, Report, Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC027(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");	
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
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
				RP.summaryGridExposurereport(getDriver(), methodName, viewName, actualViews, Report, Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC028(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");	
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
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
				RP.summaryGridExposurereport(getDriver(), methodName, viewName, actualViews, Report, Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC029(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");		
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
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
				RP.summaryGridheader(getDriver(), methodName, viewName, actualViews, Report, Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC030(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");	
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
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
				RP.summaryGridheader(getDriver(), methodName, viewName, actualViews, Report, Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC031(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");		
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
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
				RP.summaryGridheader(getDriver(), methodName, viewName, actualViews, Report, Verify);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC032(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");		
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.summaryGridExposureFilter(getDriver(), methodName, viewName, actualViews,Report,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC033(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");	
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.summaryGridExposureFilter(getDriver(), methodName, viewName, actualViews,Report,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC034(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");		
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.summaryGridExposureFilter(getDriver(), methodName, viewName, actualViews,Report,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
 /*
	// Remove LLR test cases 
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC035(LinkedHashMap<String, String> data) throws InterruptedException, SQLException{

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");		
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.summaryGridExposureFilter(getDriver(), methodName, viewName, actualViews,Report,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC036(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");	
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.summaryGridExposureFilter(getDriver(), methodName, viewName, actualViews,Report,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC037(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");	
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.summaryGridExposureFilter(getDriver(), methodName, viewName, actualViews,Report,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
*/
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC038(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.summaryGridExposureFilter(getDriver(), methodName, viewName, actualViews,Report,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC039(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");	
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.summaryGridExposureFilter(getDriver(), methodName, viewName, actualViews,Report,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC040(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");		
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.summaryGridExposureFilter(getDriver(), methodName, viewName, actualViews,Report,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC041(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.summaryGridExposureFilter(getDriver(), methodName, viewName, actualViews,Report,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC042(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");	
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.summaryGridExposureFilter(getDriver(), methodName, viewName, actualViews,Report,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC043(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");	
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");	
		String Report = getData(data, "Report");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.summaryGridExposureFilter(getDriver(), methodName, viewName, actualViews,Report,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC044(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");		
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.summaryGridExposureFilter(getDriver(), methodName, viewName, actualViews,Report,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC045(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");
		String Report = getData(data, "Report");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.summaryGridExposureFilter(getDriver(), methodName, viewName, actualViews,Report,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC046(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");		
		String viewName = getData(data, "viewName");
		String actualViews = getData(data, "actualViews");	
		String Report = getData(data, "Report");
		String actualViews2 = getData(data, "actualViews2");
		String Verify2 = getData(data, "Verify2");
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
				RP.summaryGridExposureFilter(getDriver(), methodName, viewName, actualViews,Report,actualViews2,Verify,Verify2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC047(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String ids = getData(data, "ID");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.orsortfilterreg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, Verify3, ids);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
/*
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC048(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String ids = getData(data, "ID");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.orsortfilterreg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, Verify3, ids);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
*/
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC049(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String ids = getData(data, "ID");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.orsortfilterreg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, Verify3, ids);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC050(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String ids = getData(data, "ID");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.orsortfilterreg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, Verify3, ids);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC051(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String ids = getData(data, "ID");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.orsortfilterreg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, Verify3, ids);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC052(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String ids = getData(data, "ID");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.orsortfilterreg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, Verify3, ids);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC053(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String ids = getData(data, "ID");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.orsortfilterreg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, Verify3, ids);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC054(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String ids = getData(data, "ID");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.orsortfilterreg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, Verify3, ids);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC055(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String reportingPeriod = getData(data, "reportingPeriod");
		String actualViews = getData(data, "actualViews");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String Verify3 = getData(data, "Verify3");
		String ids = getData(data, "ID");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.riskmeasurecheckfilterreg(getDriver(), methodName, actualViews, Verify, Verify2, Verify3,
						reportingPeriod, viewName, ReportName, ids);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
	 

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC056(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.ordownloadfilereg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, ids, Fileformat,
						Environment, FileNameac, actualViews, actualViews2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
/*
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC057(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.ordownloadfilereg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, ids, Fileformat,
						Environment, FileNameac, actualViews, actualViews2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}
*/
	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC058(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.ordownloadfilereg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, ids, Fileformat,
						Environment, FileNameac, actualViews, actualViews2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC059(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.ordownloadfilereg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, ids, Fileformat,
						Environment, FileNameac, actualViews, actualViews2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC060(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.ordownloadfilereg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, ids, Fileformat,
						Environment, FileNameac, actualViews, actualViews2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC061(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.ordownloadfilereg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, ids, Fileformat,
						Environment, FileNameac, actualViews, actualViews2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC062(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.ordownloadfilereg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, ids, Fileformat,
						Environment, FileNameac, actualViews, actualViews2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC063(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String FileNameac = getData(data, "FileNameac");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		if (CaseToRun.equalsIgnoreCase("N")) {
			System.out.println(TestCaseName + " : CaseToRun = N for So Skipping Execution.");
			Testskip = true;
			throw new SkipException(
					TestCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + TestCaseName);
		} else {
			for (String Key : URLs.keySet()) {
				System.out.println(URLs.get(Key));
				credentials = TestCaseListExcelsearchContains.getLoginCredentials("Users", Role);
				for (int j = 0; j < credentials.size(); j++) {
					users = credentials.get(j);
					username = users.get("username");
					encPass = users.get("password");
				}
				password = dpass.decryptUserPassword(encPass);
				loadWebBrowser();
				objLoginPage.login(getDriver(), username, password, URLs.get(Key), Instance);
				DR.ordownloadfilereg(getDriver(), methodName, viewName, ReportName, Verify, Verify2, ids, Fileformat,
						Environment, FileNameac, actualViews, actualViews2);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC064(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Environment = getData(data, "Environment");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String actualViews = getData(data, "actualViews");
		String Verify3 = getData(data, "Verify3");
		String reportingPeriod = getData(data, "reportingPeriod");
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
				DR.riskmeasuredownloadfilereg(getDriver(), methodName, actualViews, Verify, Verify2, Verify3,
						reportingPeriod, viewName, ReportName, ids, Fileformat, Environment, FileNameac);
				SB.Snackbar(getDriver(), methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC065(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify3 = getData(data, "Verify3");
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

				DR.eachaggMIDQNumerical(getDriver(), methodName, actualViews, actualViews2, Verify, Verify2, Verify3,
						reportingPeriod, viewName, ReportName, ids, Fileformat);
				// SB.Snackbar(getDriver(),methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC066(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify3 = getData(data, "Verify3");
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
				DR.eachaggMIDQCateroical(getDriver(), methodName, actualViews, actualViews2, Verify, Verify2, Verify3,
						reportingPeriod, viewName, ReportName, ids, Fileformat);
				// SB.Snackbar(getDriver(),methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC067(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify3 = getData(data, "Verify3");
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
				DR.CECLMovementReport(getDriver(), methodName, actualViews, actualViews2, Verify, Verify2, Verify3,
						reportingPeriod, viewName, ReportName, ids, Fileformat);
				// SB.Snackbar(getDriver(),methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC068(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify3 = getData(data, "Verify3");
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
				DR.trbobligor(getDriver(), methodName, actualViews, actualViews2, Verify, Verify2, Verify3,
						reportingPeriod, viewName, ReportName, ids, Fileformat);
				// SB.Snackbar(getDriver(),methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC069(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify3 = getData(data, "Verify3");
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
				DR.trbobligor(getDriver(), methodName, actualViews, actualViews2, Verify, Verify2, Verify3,
						reportingPeriod, viewName, ReportName, ids, Fileformat);
				// SB.Snackbar(getDriver(),methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC070(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify3 = getData(data, "Verify3");
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
				DR.ratingmigdata(getDriver(), methodName, actualViews, actualViews2, Verify, Verify2, Verify3,
						reportingPeriod, viewName, ReportName, ids, Fileformat);
				// SB.Snackbar(getDriver(),methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC071(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify3 = getData(data, "Verify3");
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
				DR.ratingmigdata(getDriver(), methodName, actualViews, actualViews2, Verify, Verify2, Verify3,
						reportingPeriod, viewName, ReportName, ids, Fileformat);
				// SB.Snackbar(getDriver(),methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC072(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify3 = getData(data, "Verify3");
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
				DR.ratingmigdata(getDriver(), methodName, actualViews, actualViews2, Verify, Verify2, Verify3,
						reportingPeriod, viewName, ReportName, ids, Fileformat);
				// SB.Snackbar(getDriver(),methodName);
			}
		}
	}

	@Test(retryAnalyzer = ReTryFail.class, dataProvider = "Regression", dataProviderClass = utility.Xlsdataprovider.class, groups = "Dashboard", alwaysRun = true)
	public void Reg_TC073(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

		TestCaseName = getData(data, "TestCaseName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		CaseToRun = getData(data, "CaseToRun");
		String Instance = getData(data, "Instance");
		String Role = getData(data, "Role");
		String viewName = getData(data, "viewName");
		getData(data, "Fileformat");
		getData(data, "ID");
		getData(data, "RiskMetric");
		String ReportName = getData(data, "ReportName");
		String Verify = getData(data, "Verify");
		String Verify2 = getData(data, "Verify2");
		String ids = getData(data, "ID");
		String Fileformat = getData(data, "Fileformat");
		String actualViews = getData(data, "actualViews");
		String actualViews2 = getData(data, "actualViews2");
		String Verify3 = getData(data, "Verify3");
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
				DR.ratingmigdata(getDriver(), methodName, actualViews, actualViews2, Verify, Verify2, Verify3,
						reportingPeriod, viewName, ReportName, ids, Fileformat);
				// SB.Snackbar(getDriver(),methodName);
			}
		}
	}
	 

	

	@AfterMethod(alwaysRun = true)
	public void reporterDataResults(ITestResult Result) throws InterruptedException, IOException {
		Testfail = TestResultStatus.Testfail;
		System.out.println("test fail flag in AfterMethod: " + Testfail);
		if (Result.getStatus() == ITestResult.SKIP) {
			Resmark.put(Result.getName(), "");
			Reporter.log(Result.getName() + " is SKIPPED");
			Add_Log.info(Result.getName() + " is SKIPPED");
			//captureScreenShot(Result, "SKIP", getDriver());
			TestResultTL.put(Result.getName(), "SKIP");
			if (!(getDriver() == null)) {
				closeWebBrowser();
			}
		} else if (Result.getStatus() == ITestResult.FAILURE) {
			// j++;  //increase counter here

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
			Resmark.put(Result.getName(), "");
			//captureScreenShot(Result, "PASS", getDriver());
			Reporter.log(Result.getName() + " is PASS");
			Add_Log.info(Result.getName() + " is PASS");
			if (!(getDriver() == null)) {
				closeWebBrowser();
			}
			TestResultTL.put(Result.getName(), "PASS");
		}
		
	}


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
		
	}

	
}

