
package testsuitebase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

import pageobjects.HomePage;
import utility.FetchExcelDataSet;
import utility.Read_XLS;
import utility.ScreenshotUtility;
import utility.SeleniumUtils;
import utility.decryptpassword;

public class SuiteBase {	
	
	public Read_XLS TestCaseListExcelsearchContains=null;
	public Logger Add_Log = null;
	public Properties Config = null;
	/*public static WebDriver driver = null;*/
	InheritableThreadLocal<WebDriver> driver = new InheritableThreadLocal<WebDriver>();
	public String CaseToRun = null;
	public SeleniumUtils utils = new SeleniumUtils();
	public ScreenshotUtility screenutility =new ScreenshotUtility();
	public HomePage HP = new HomePage();
	protected FetchExcelDataSet objFD = new FetchExcelDataSet();
	public decryptpassword dpass = new decryptpassword();	
	public String encPass;
	public String sysRating = null;
	public WebDriver ExistingchromeBrowser;
	public WebDriver ExistingmozillaBrowser;
	public WebDriver ExistingIEBrowser;		
	public HashMap<String, String> URLs = null;
	public ArrayList<HashMap<String, String>> credentials = null;
	public HashMap<String, String> users = null;	
	public String username = null;
	public String password = null;
	public String testBrowser = "chrome";
	static public HashMap<String, String> TestResult = new HashMap<>();
	public WebDriver getDriver() // call this method to get the driver object and launch the browser
	{
		return driver.get();
	}
	
	public void init() throws IOException{
		//To Initialize logger service.
		Add_Log = Logger.getLogger("rootLogger");								
		TestCaseListExcelsearchContains = new Read_XLS(System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\RegressionSh_Tests.xls");	
		//Bellow given syntax will Insert log In applog.log file.
		Add_Log.info("All Excel Files Initialised successfully.");
		
		
		
	}
	
	public void loadWebBrowser(){
		if(testBrowser.equalsIgnoreCase("Mozilla") && ExistingmozillaBrowser!=null){
			driver.set(ExistingmozillaBrowser);
			return;
		}else if(testBrowser.equalsIgnoreCase("chrome") && ExistingchromeBrowser!=null){
			driver.set(ExistingchromeBrowser);
			return;
		}else if(testBrowser.equalsIgnoreCase("IE") && ExistingIEBrowser!=null){
			driver.set(ExistingIEBrowser);
			return;
		}	
		
		if(testBrowser.equalsIgnoreCase("Mozilla")){
			//To Load Firefox driver Instance. 
			driver.set(new FirefoxDriver());		
			Add_Log.info("Firefox Driver Instance loaded successfully.");
			
		}else if(testBrowser.equalsIgnoreCase("Chrome")){
			//To Load Chrome driver Instance.
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\browserdrivers\\chromedriver.exe");
			String downloadFilepath = System.getProperty("user.dir")+"\\src\\main\\resources\\excelfiles\\";
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--start-maximized");
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("disable-infobars");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);			
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			driver.set(new ChromeDriver(cap));
			Add_Log.info("Chrome Driver Instance loaded successfully.");
			
			//for remote run
			/*DesiredCapabilities capability = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			options.addArguments("disable-infobars");
			options.addArguments("--start-maximized");
			options.setExperimentalOption("useAutomationExtension", false);
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.WINDOWS);
			capability.setCapability(ChromeOptions.CAPABILITY, options);						
			try {
				driver.set(new RemoteWebDriver(new URL("http://168.72.180.128:4455/wd/hub"), capability));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getDriver().manage().window().maximize();
			//For Remote run end			
			Add_Log.info("Chrome Driver Instance loaded successfully."); 
			*/
		}else if(testBrowser.equalsIgnoreCase("IE")){
			//To Load IE driver Instance.
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\browserdrivers\\IEDriverServer.exe");	
			/*System.setProperty("webdriver.chrome.driver", this.getClass().getClassLoader().getResource("browserdrivers/IEDriverServer.exe").getFile().toString());*/
			driver.set(new InternetExplorerDriver());
			Add_Log.info("IE Driver Instance loaded successfully.");			
		}				
	}
	
	public void closeWebBrowser(){
		getDriver().quit();		
	}
	
	public String getData(LinkedHashMap<String, String> data,String key){
		if(data.get(key)!=null && data.get(key).length()>0){
			return data.get(key);
		}else{
			return "";
		}
		
	}
	
	public void captureScreenShot(ITestResult result, String status, WebDriver driver){	
		String destDir = "";
		String passfailMethod = result.getMethod().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();
		//To capture screenshot.
		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		//File scrFile = ((TakesScreenshot) SuiteBase.driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		//If status = fail then set folder name "screenshots/Failures"
		if(status.equalsIgnoreCase("fail")){
			/*destDir = "target/screenshots/Failures";*/
			destDir = "screenshots/failures";
		}
		//If status = pass then set folder name "screenshots/Success"
		else if (status.equalsIgnoreCase("pass")){
			/*destDir = "target/screenshots/Success";*/
			/*destDir = this.getClass().getClassLoader().getResource("screenshots/success").toString();*/
			destDir = "screenshots/success";
		}
		
		//To create folder to store screenshots
		new File(destDir).mkdirs();
		//Set file name with combination of test class name + date time.
		String destFile = passfailMethod+" - "+dateFormat.format(new Date()) + ".jpeg";
		
		try {
			//Store file at destination folder location
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
