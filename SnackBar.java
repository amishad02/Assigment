package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import property.IHomePage;
import testsuitebase.TestResultStatus;
import utility.SeleniumUtils;

public class SnackBar extends SeleniumUtils implements IHomePage  {

	public void Snackbar(WebDriver driver, String methodName) throws InterruptedException {
		try {
			By ErrorSnackBar = By.xpath("//img[@class='StausClose']");
			if (driver.findElements(ErrorSnackBar).size() > 0) {
				WebElement errormessage = driver.findElement(By.xpath("//img[@class='StausClose']/following::span[1]"));
				Reporter.log("Status Bar Appear with message: " + errormessage.getText());
				Add_Log.info("Status  Bar Appear with message: " + errormessage.getText());
				TestResultStatus.mpaskeysnew.put(methodName,"Status  Bar Appear with message: " + errormessage.getText());
			}
		} catch (NoSuchElementException e1) {
			
		}
	}
	
	
}

