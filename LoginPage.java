package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import property.ILoginPage;
import utility.SeleniumUtils;

public class LoginPage extends SeleniumUtils implements ILoginPage{

	public void login(WebDriver driver, String username, String password, String url, String Instance) throws InterruptedException {
		/*if(Instance.equalsIgnoreCase("EM"))
		{
			driver.get("https://uat.citiriskcredit.citigroup.net/OneMartEM/?app=OneMartReportingApplication");
		System.out.println("https://uat.citiriskcredit.citigroup.net/OneMartEM/?app=OneMartReportingApplication");
		}
		else
		{
			
		}
		if(Instance.equalsIgnoreCase("CECL"))
		{
			driver.get("https://uat.citiriskcredit.citigroup.net/OneMartCCAREM_Pfix/?app=OneMartReportingApplication");
			System.out.println("https://uat.citiriskcredit.citigroup.net/OneMartCCAREM_Pfix/?app=OneMartReportingApplication");
		}
		else
		{
			
		}
		if(Instance.equalsIgnoreCase("CCAR"))
		{
			driver.get("https://uat.citiriskcredit.citigroup.net/OneMartCCAREM/?app=OneMartReportingApplication");
			System.out.println("https://uat.citiriskcredit.citigroup.net/OneMartCCAREM/?app=OneMartReportingApplication");
		}
		else
		{
		}*/

		if(Instance.equalsIgnoreCase("CitiRiskPortal")) 
			if(url.contains("uat")) {
			driver.get("https://uat.riskmanagement.portal.citigroup.net");
		System.out.println("https://uat.riskmanagement.portal.citigroup.net");
		Reporter.log("https://uat.riskmanagement.portal.citigroup.net");
		Add_Log.info("https://uat.riskmanagement.portal.citigroup.net");
			}
			else {
				driver.get("https://riskmanagement.portal.citigroup.net");
				System.out.println("https://riskmanagement.portal.citigroup.net");
				Reporter.log("https://riskmanagement.portal.citigroup.net");
				Add_Log.info("https://riskmanagement.portal.citigroup.net");
			}		
			
		else{
		driver.get(url);	
		Reporter.log(url);
		Add_Log.info(url);
		}
		waitForElementPresent(driver, 900, txt_username);
		setText(driver, txt_username, username);
		setTextpassword(driver, txt_password, password);
		click(driver, btn_signon);
		Thread.sleep(2000);
		waitForLoad(driver, 2000);
		waitForLoad(driver, 2000);
		if(Instance.equalsIgnoreCase("CitiRiskPortal")) 
		{
		}
		else
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(summary_relationship_option.getValue())));
			}
			catch (Exception e1)  {
				driver.navigate().refresh();
				System.out.println("Page refresh");
			}		
			waitForElementPresent(driver, 900, summary_relationship_option);
		}
		

	}
}

