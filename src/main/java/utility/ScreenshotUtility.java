//Find More Tutorials On WebDriver at -> http://software-testing-tutorials-automation.blogspot.com
package utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class ScreenshotUtility implements ITestListener{	
	String screenShotOnFail = "yes";
	String screenShotOnPass = "no";
	
	//This method will execute before starting of Test suite.
	@Override
	public void onStart(ITestContext tr) {	
		System.out.println("Start");
	}

	//This method will execute, Once the Test suite is finished.
	@Override
	public void onFinish(ITestContext tr) {
		
	}

	//This method will execute only when the test is pass.
	@Override
	public void onTestSuccess(ITestResult tr) {
		//If screenShotOnPass = yes, call captureScreenShot.
		if(screenShotOnPass.equalsIgnoreCase("yes"))
		{
			//captureScreenShot(tr,"pass");
		}
	}

	//This method will execute only on the event of fail test.
	@Override
	public void onTestFailure(ITestResult tr) {		
		//If screenShotOnFail = yes, call captureScreenShot.
		/*if(screenShotOnFail.equalsIgnoreCase("yes"))
		{
			captureScreenShot(tr,"fail");
		}
		System.out.println(tr.getName());*/
		//SuiteUtility.WriteResultUtility(FilePath, SheetName, "Pass/Fail/Skip", DataSet+1, "FAIL");
	}

	// This method will execute before the main test start (@Test)
	@Override
	public void onTestStart(ITestResult tr) {
		
	}

	// This method will execute only if any of the main test(@Test) get skipped
	@Override
	public void onTestSkipped(ITestResult tr) {		
		
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
	}
	
	//Function to capture screenshot.
	/*public void captureScreenShot(ITestResult result, String status){	
		String destDir = "";
		String passfailMethod = result.getMethod().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();
		//To capture screenshot.
		File scrFile = ((TakesScreenshot) SuiteBase.driver).getScreenshotAs(OutputType.FILE);
    	DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
    	//If status = fail then set folder name "screenshots/Failures"
    	if(status.equalsIgnoreCase("fail")){
    		destDir = "target/screenshots/Failures";
    		destDir = "screenshots/failures";
    	}
    	//If status = pass then set folder name "screenshots/Success"
    	else if (status.equalsIgnoreCase("pass")){
    		destDir = "target/screenshots/Success";
    		destDir = this.getClass().getClassLoader().getResource("screenshots/success").toString();
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
   } */
}