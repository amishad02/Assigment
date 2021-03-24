package testcases;

import java.io.IOException;
import java.sql.SQLException;
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

public class SFT_User_Portal_TC extends SuiteBase{
	Read_XLS FilePath = null;
	String TestCaseName = null;
	String SheetName = "SFT_User";
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
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "SFT_User", dataProviderClass = utility.Xlsdataprovider.class, groups = "SFT_Portal", alwaysRun = true)
	public void SFT_TC001(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

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
				SFTP.AggregationCheckcommon(getDriver(), viewName, actualViews, ReportName, Verify);

			}
		}
	}
	
	
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "SFT_User", dataProviderClass = utility.Xlsdataprovider.class, groups = "SFT_Portal", alwaysRun = true)
	public void SFT_TC002(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

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
				SFTP.SFTPheader(getDriver(), viewName, actualViews, ReportName, Verify);

			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "SFT_User", dataProviderClass = utility.Xlsdataprovider.class, groups = "SFT_Portal", alwaysRun = true)
	public void SFT_TC003(LinkedHashMap<String, String> data) throws InterruptedException, SQLException {

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
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
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
				SFTP.nettingsetdata(getDriver(), viewName, ReportName, Verify, reportingPeriod, Verify3, Verify4);

			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "SFT_User", dataProviderClass = utility.Xlsdataprovider.class, groups = "SFT_Portal", alwaysRun = true)
	public void SFT_TC004(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

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
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
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
				SFTP.nettingsetdatadownload(getDriver(), viewName, ReportName, Verify, reportingPeriod, Verify3, Verify4, Fileformat, FileNameac);

			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "SFT_User", dataProviderClass = utility.Xlsdataprovider.class, groups = "SFT_Portal", alwaysRun = true)
	public void SFT_TC005(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

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
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
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
				SFTP.transactionToview(getDriver(), viewName, ReportName, Verify, reportingPeriod, Verify3, Verify4);

			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "SFT_User", dataProviderClass = utility.Xlsdataprovider.class, groups = "SFT_Portal", alwaysRun = true)
	public void SFT_TC006(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

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
		String Report = getData(data, "Report");
		String Verify2 = getData(data, "Verify2");
		String actualViews4 = getData(data, "actualViews4");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
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
				//SFTP.nettingsetdatafilter(getDriver(), viewName, ReportName, Verify, reportingPeriod, Verify3, Verify4, actualViews);
               SFTP.nettingsetdatafilter1(getDriver(), viewName, ReportName, Verify, reportingPeriod, Verify3, Verify4, actualViews, Verify2, Report, actualViews4);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "SFT_User", dataProviderClass = utility.Xlsdataprovider.class, groups = "SFT_Portal", alwaysRun = true)
	public void SFT_TC007(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

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
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
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
				SFTP.Transactionsetdatadownload(getDriver(), viewName, ReportName, Verify, reportingPeriod, Verify3, Verify4, Fileformat, FileNameac);

			}
		}
	}
	
	@Test(dataProvider = "SFT_User", dataProviderClass = utility.Xlsdataprovider.class, groups = "SFT_Portal", alwaysRun = true)
	public void SFT_TC008(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

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
		String Report = getData(data, "Report");
		String Verify2 = getData(data, "Verify2");
		String actualViews4 = getData(data, "actualViews4");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
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
				//SFTP.Transactionsetdatafilter(getDriver(), viewName, ReportName, Verify, reportingPeriod, Verify3, Verify4, actualViews);
               SFTP.Transactionsetdatafilter1(getDriver(), viewName, ReportName, Verify, reportingPeriod, Verify3, Verify4, actualViews, Verify2, Report, actualViews4);
			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "SFT_User", dataProviderClass = utility.Xlsdataprovider.class, groups = "SFT_Portal", alwaysRun = true)
	public void SFT_TC009(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

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
		String actualViews = getData(data, "actualViews");
		String actualViews4 = getData(data, "actualViews4");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
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
				SFTP.TransactionsIddatadownload(getDriver(), viewName, ReportName, Verify, Verify2, reportingPeriod, Verify3, Verify4, Fileformat, FileNameac, Report, actualViews, actualViews4);

			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "SFT_User", dataProviderClass = utility.Xlsdataprovider.class, groups = "SFT_Portal", alwaysRun = true)
	public void SFT_TC010(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

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
		String actualViews = getData(data, "actualViews");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String Report = getData(data, "Report");
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
				SFTP.NonzeroTransactionsdisplay(getDriver(), viewName, ReportName, Verify, reportingPeriod, Verify3, Verify4, actualViews, Report);

			}
		}
	}
	
	@Test(retryAnalyzer = ReTryFail.class,dataProvider = "SFT_User", dataProviderClass = utility.Xlsdataprovider.class, groups = "SFT_Portal", alwaysRun = true)
	public void SFT_TC011(LinkedHashMap<String, String> data) throws InterruptedException, SQLException, IOException {

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
		String actualViews = getData(data, "actualViews");
		String Verify3 = getData(data, "Verify3");
		String Verify4 = getData(data, "Verify4");
		String Report = getData(data, "Report");
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
				SFTP.RedirecttoSFTPortal(getDriver(), viewName, ReportName, Verify, Verify2, reportingPeriod, Verify3, Verify4, actualViews, Report);

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

