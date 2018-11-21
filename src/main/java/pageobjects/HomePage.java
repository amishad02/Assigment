package pageobjects;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import property.IHomePage;
import utility.SeleniumUtils;

public class HomePage extends SeleniumUtils implements IHomePage {
	
	
	public void controlUnitGeography(WebDriver driver,String AddRule,String Level1,String Level2,String Level3,String Level4) throws InterruptedException{
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);
		click(driver, iconforward);
		Thread.sleep(2000);
		click(driver, iconforward);
		Thread.sleep(2000);
		By controlunit = By.xpath("//span[text()='"+AddRule+"']");
		click(driver, controlunit, AddRule+" checkbox");
		Thread.sleep(2000);	
		click(driver, iconclose);
		Thread.sleep(2000);	
		waitForElementPresent(driver, 30, iconadd);
		click(driver, iconadd);
		Thread.sleep(2000);
		By level1 = By.xpath("//span[text()='"+Level1+"']");
		waitForElementPresent(driver, 30, level1, Level1+"checkbox level 1");
		click(driver, level1, Level1+" checkbox level 1");
		Thread.sleep(2000);
		By level1Next = By.xpath("(//span[text()='Next'])[1]");
		waitForElementPresent(driver, 30, level1, "Level1 Next Button");
		click(driver, level1Next, "Level1 Next Button");
		Thread.sleep(2000);
		By level2 = By.xpath("//span[text()='"+Level2+"']");
		waitForElementPresent(driver, 30, level2, Level2+" checkbox level 2");
		click(driver, level2, Level2+" checkbox level 2");
		Thread.sleep(1000);
		By level2Next = By.xpath("(//span[text()='Next'])[2]");
		waitForElementPresent(driver, 30, level2Next, "Level2 Next Button");
		click(driver, level2Next, "Level2 Next Button");
		Thread.sleep(2000);
		By level3 = By.xpath("(//span[text()='"+Level3+"'])[2]");
		waitForElementPresent(driver, 30, level3, Level3+" checkbox level 3");
		click(driver, level3, Level3+" checkbox level 3");
		Thread.sleep(2000);
		By level3Next = By.xpath("(//span[text()='Next'])[3]");
		waitForElementPresent(driver, 30, level3Next, "Level3 Next Button");
		click(driver, level3Next, "Level3 Next Button");
		Thread.sleep(2000);
		By level4 = By.xpath("//span[text()='"+Level4+"']");
		waitForElementPresent(driver, 30, level4, Level4+" checkbox level 4");
		click(driver, level4, Level4+" checkbox level 4");
		click(driver, btnclose);
		Thread.sleep(2000);
		WebElement geography = driver.findElement(By.xpath("//mat-chip[contains(text(),'BRAZIL')]"));
		waitForElementPresent(driver, 30, geography, "Brazil geography filter on Home Page");				
	}
		
	public void filtersCheck(WebDriver driver, String AddRule, String Filtername) throws InterruptedException{
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);	
		/*waitForElementPresent(driver, 30, iconforward);*/
		waitForElementPresent(driver, 30, filtersearch);
		setText(driver, filtersearch, AddRule);
		Thread.sleep(2000);
		List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'mat-progress-bar')]"));
		waitForElementInvisibilty(driver, 60, ele, "Add Filters Progress Bar");
		By controlunit = By.xpath("//span[text()='"+AddRule+"']");
		click(driver, controlunit, AddRule+" checkbox");
		Thread.sleep(1000);	
		click(driver, iconclose);
		Thread.sleep(1000);	
		waitForElementPresent(driver, 30, iconadd);
		click(driver, iconadd);
		Thread.sleep(2000);
		String fliters[] =Filtername.split("\\|");
		for (int i = 0; i < fliters.length; i++) {
			By filtername = By.xpath("//span[text()='"+fliters[i]+"'][@class='mat-checkbox-label']");
			waitForElementPresent(driver, 60, filtername, fliters[i]+" checkbox");
			click(driver, filtername, fliters[i]+" checkbox");			
		}		
		click(driver, iconclose);
		Thread.sleep(2000);
		for (int i = 0; i < fliters.length; i++) {
			WebElement geography = driver.findElement(By.xpath("//mat-chip[contains(text(),'"+fliters[i]+"')]"));
			waitForElementPresent(driver, 30, geography, fliters[i]+" filter on Home Page");
		}
	}

	public void topExposuresPopupCheck(WebDriver driver, String AddRule, String FilterName) throws InterruptedException{
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, filtersearch);
		setText(driver, filtersearch, AddRule);
		Thread.sleep(2000);
		List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'mat-progress-bar')]"));
		waitForElementInvisibilty(driver, 60, ele, "Add Filters Progress Bar");	
		By controlunit = By.xpath("//span[text()='"+AddRule+"']");
		click(driver, controlunit, AddRule+" checkbox");
		Thread.sleep(2000);	
		click(driver, iconclose);
		Thread.sleep(2000);		
		WebElement yesradio = driver.findElement(By.xpath("//div[text()='"+FilterName+"']"));
		waitForElementPresent(driver, 30, yesradio, FilterName+" radio button "+AddRule);
		click(driver, yesradio, FilterName+" radio button "+AddRule);
		waitForElementPresent(driver, 30, iconreview);
		click(driver, iconreview);
		Thread.sleep(1000);	
		By progressbar = By.xpath("//div[contains(@class,'loading-indicator')]");
		waitForElementInvisibilty(driver, 30, progressbar, "Progress Bar");
		By ExitSnackBar = By.xpath("//span[text()='EXIT']");
		
		if(driver.findElements(ExitSnackBar).size()>0){
			WebElement errormessage = driver.findElement(By.xpath("//simple-snack-bar[contains(@class,'mat-simple-snackbar')]/span"));
			Reporter.log("Error message: "+errormessage.getText());
			Add_Log.info("Error message: "+errormessage.getText());
			Assert.fail();			
		}		
		WebElement labeltop10 = driver.findElement(By.xpath("//h2[text()='Top Exposures']"));
		waitForElementPresent(driver, 120, labeltop10, "Top 10 Exposures");
		WebElement firstcell  = driver.findElement(By.xpath("(//mat-cell[text()])[1]"));
		waitForElementPresent(driver, 30, firstcell, "Top Exposures pop up first cell data");			
	}
	
	public void BestEffortsDistributionGFMIS(WebDriver driver,String AddRule,String Level1,String Level2,String Level3,String Level4,String verifyLevel) throws InterruptedException{
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);		
		By controlunit = By.xpath("//span[text()='"+AddRule+"']");
		click(driver, controlunit, AddRule+" checkbox");
		Thread.sleep(2000);	
		click(driver, iconclose);
		Thread.sleep(2000);		
		waitForElementPresent(driver, 30, iconadd);
		click(driver, iconadd);
		Thread.sleep(2000);	
		By level1 = By.xpath("//span[text()='"+Level1+"']");
		waitForElementPresent(driver, 30, level1, Level1+" checkbox level 1");
		click(driver, level1, Level1+" checkbox level 1");
		Thread.sleep(1000);	
		By level1Next = By.xpath("(//span[text()='Next'])[1]");
		waitForElementPresent(driver, 30, level1, "Level1 Next Button");
		click(driver, level1Next, "Level1 Next Button");
		Thread.sleep(1000);	
		By level2 = By.xpath("(//span[text()='"+Level2+"'])[2]");
		waitForElementPresent(driver, 30, level2, Level2+" checkbox level 2");
		click(driver, level2, Level2+" checkbox level 2");
		Thread.sleep(1000);	
		By level2Next = By.xpath("(//span[text()='Next'])[2]");
		waitForElementPresent(driver, 30, level2Next, "Level2 Next Button");
		click(driver, level2Next, "Level2 Next Button");
		Thread.sleep(1000);	
		By level3 = By.xpath("(//span[text()='"+Level3+"'])[3]");
		waitForElementPresent(driver, 30, level3, Level3+" checkbox level 3");
		click(driver, level3, Level3+" checkbox level 3");
		Thread.sleep(1000);	
		By level3Next = By.xpath("(//span[text()='Next'])[3]");
		waitForElementPresent(driver, 30, level3Next, "Level3 Next Button");
		click(driver, level3Next, "Level3 Next Button");
		Thread.sleep(1000);	
		By level4 = By.xpath("(//span[text()='"+Level4+"'])[4]");
		waitForElementPresent(driver, 30, level4, Level4+" checkbox level 4");
		click(driver, level4, Level4+" checkbox level 4");
		click(driver, btnclose);
		Thread.sleep(2000);
		WebElement geography = driver.findElement(By.xpath("//mat-chip[contains(text(),'"+verifyLevel+"')]"));
		waitForElementPresent(driver, 30, geography, Level1+" filter on Home Page");
	}
	
	public void IndependentRiskUnitGeography(WebDriver driver,String AddRule,String Level1,String Level2,String Level3,String Level4,String verifyLevel) throws InterruptedException{
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);		
		By controlunit = By.xpath("//span[text()='"+AddRule+"']");
		click(driver, controlunit, AddRule+" checkbox");
		Thread.sleep(1000);	
		click(driver, iconclose);
		Thread.sleep(1000);		
		waitForElementPresent(driver, 30, iconadd);
		click(driver, iconadd);
		Thread.sleep(1000);	
		By level1 = By.xpath("//span[text()='"+Level1+"']");
		waitForElementPresent(driver, 30, level1, Level1+" checkbox level 1");
		click(driver, level1, Level1+" checkbox level 1");
		Thread.sleep(1000);	
		By level1Next = By.xpath("(//span[text()='Next'])[1]");
		waitForElementPresent(driver, 30, level1, "Level1 Next Button");
		click(driver, level1Next, "Level1 Next Button");
		Thread.sleep(1000);	
		By level2 = By.xpath("//span[text()='"+Level2+"']");
		waitForElementPresent(driver, 30, level2, Level2+" checkbox level 2");
		click(driver, level2, Level2+" checkbox level 2");
		Thread.sleep(1000);	
		By level2Next = By.xpath("(//span[text()='Next'])[2]");
		waitForElementPresent(driver, 30, level2Next, "Level2 Next Button");
		click(driver, level2Next, "Level2 Next Button");
		Thread.sleep(1000);	
		By level3 = By.xpath("(//span[text()='"+Level3+"'])[2]");
		waitForElementPresent(driver, 30, level3, Level3+" checkbox level 3");
		click(driver, level3, Level3+" checkbox level 3");
		Thread.sleep(1000);	
		By level3Next = By.xpath("(//span[text()='Next'])[3]");
		waitForElementPresent(driver, 30, level3Next, "Level3 Next Button");
		click(driver, level3Next, "Level3 Next Button");
		Thread.sleep(1000);	
		By level4 = By.xpath("(//span[text()='"+Level4+"'])[3]");
		waitForElementPresent(driver, 30, level4, Level4+" checkbox level 4");
		click(driver, level4, Level4+" checkbox level 4");
		click(driver, btnclose);
		Thread.sleep(2000);
		WebElement geography = driver.findElement(By.xpath("//mat-chip[contains(text(),'"+verifyLevel+"')]"));
		waitForElementPresent(driver, 30, geography, Level1+" filter on Home Page");
	}
	
	public void checkLevel(WebDriver driver, String AddRule,String Level, String verifyLevel) throws InterruptedException, AWTException{		
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, filtersearch);
		setText(driver, filtersearch, AddRule);
		Thread.sleep(2000);
		List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'mat-progress-bar')]"));
		waitForElementInvisibilty(driver, 60, ele, "Add Filters Progress Bar");	
		By controlunit = By.xpath("//span[text()='"+AddRule+"']");
		click(driver, controlunit, AddRule+" checkbox");
		Thread.sleep(2000);	
		click(driver, iconclose);
		Thread.sleep(2000);		
		waitForElementPresent(driver, 30, iconadd);
		click(driver, iconadd);
		Thread.sleep(2000);	
		String levels[] = Level.split("\\|");
		for (int i = 0; i < levels.length; i++) {
			Thread.sleep(1000);	
			waitForElementPresent(driver, 30, filtersearch);
			ele = driver.findElements(By.xpath("//div[contains(@class,'mat-progress-bar')]"));
			waitForElementInvisibilty(driver, 60, ele, "Add Filters Progress Bar");
			setText(driver, filtersearch, levels[i]);
			Thread.sleep(2000);			
			waitForElementInvisibilty(driver, 60, ele, "Add Filters Progress Bar");
			int radioelesize = driver.findElements(By.xpath("//div[@class='mat-radio-outer-circle']")).size();
			if (radioelesize > 0) {
				By level = By.xpath("//div[contains(text() ,'"+levels[i]+"')]");
				int size = driver.findElements(level).size();
				if (size == 1) {
					level = By.xpath("//div[contains(text() ,'"+levels[i]+"')]");
				} else {
					for (int j = 0; j < size; j++) {
						WebElement ele1 = driver.findElement(By.xpath("(//div[contains(text() ,'"+levels[i]+"')])["+(j+1)+"]"));
						String text = ele1.getText().trim();
						if(text.length() == levels[i].length()){
							level = By.xpath("(//div[contains(text() ,'"+levels[i]+"')])["+(j+1)+"]");
						}
					}					
				}
				waitForElementPresent(driver, 30, level, levels[i]+" radio button");
				click(driver, level, levels[i]+" radio button");
			} else {
				By level = By.xpath("//span[contains(text() ,'"+levels[i]+"')]");
				int size = driver.findElements(level).size();
				if (size == 1) {
					level = By.xpath("//span[contains(text() ,'"+levels[i]+"')]");
				} else {
					for (int j = 0; j < size; j++) {
						WebElement ele1 = driver.findElement(By.xpath("(//span[contains(text() ,'"+levels[i]+"')])["+(j+1)+"]"));
						String text = ele1.getText().trim();
						if(text.length() == levels[i].length()){
							level = By.xpath("(//span[contains(text() ,'"+levels[i]+"')])["+(j+1)+"]");
						}
					}
					
				}
				waitForElementPresent(driver, 30, level, levels[i]+" checkbox");
				click(driver, level, levels[i]+" checkbox");
			}
			
			clearText(driver, filtersearch);
			Thread.sleep(2000);
			/*By levelNext = null;
			if (i < 3) {
				
				levelNext = By.xpath("(//span[text()='Next'])["+(i+1)+"]");
				
				waitForElementPresent(driver, 30, levelNext, "Level "+(i+1)+" Next Button");
				click(driver, levelNext, "Level "+(i+1)+" Next Button");
				Thread.sleep(1000);	
			}*/			
						
		}		
		click(driver, btnclose);
		Thread.sleep(2000);
		String[] verify = verifyLevel.split("\\|");
		for (int i = 0; i < verify.length; i++) {
			WebElement geography = driver.findElement(By.xpath("//mat-chip[contains(text(),'"+verify[i]+"')]"));
			waitForElementPresent(driver, 30, geography, verify[i]+" filter on Home Page");
		}				
	}
	
	public void TC36(WebDriver driver, String AddRule, String Filtername1,String Filtername2,String Filtername3) throws InterruptedException{
		String[] rules = AddRule.split("\\|");
		filtersCheck(driver, rules[0], Filtername1);
		WebElement addgrp1 = driver.findElement(By.xpath("//i[@class='pf-icon fa fa-sitemap']"));
		waitForElementPresent(driver, 30, addgrp1, "Add group icon");
		click(driver, addgrp1, "Add group icon");
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);
		By controlunit = By.xpath("//span[text()='"+rules[1]+"']");
		click(driver, controlunit, rules[1]+" checkbox");
		Thread.sleep(1000);	
		click(driver, iconclose);
		Thread.sleep(1000);	
		WebElement iconadd2 = driver.findElement(By.xpath("(//i[text()='add_circle'])[2]"));
		waitForElementPresent(driver, 30, iconadd2, "Icon Add 2");
		click(driver, iconadd2, "Icon Add 2");
		Thread.sleep(1000);
		String fliters[] =Filtername2.split("\\|");
		for (int i = 0; i < fliters.length; i++) {
			By filtername = By.xpath("//span[text()='"+fliters[i]+"']");
			waitForElementPresent(driver, 60, filtername, fliters[i]+" checkbox");
			click(driver, filtername, fliters[i]+" checkbox");			
		}		
		click(driver, iconclose);
		Thread.sleep(2000);
		for (int i = 0; i < fliters.length; i++) {
			WebElement geography = driver.findElement(By.xpath("//mat-chip[contains(text(),'"+fliters[i]+"')]"));
			waitForElementPresent(driver, 30, geography, fliters[i]+" filter on Home Page");
		}
		WebElement andlabel = driver.findElement(By.xpath("//span[text()='AND']"));
		waitForElementPresent(driver, 30, andlabel, "AND label");
		click(driver, andlabel, "AND label");
		Thread.sleep(2000);
		WebElement orlabel = driver.findElement(By.xpath("//span[text()='OR']"));
		waitForElementPresent(driver, 30, orlabel, "OR label");
	}
	
	public void TC37(WebDriver driver, String AddRule, String Filtername1,String Filtername2,String Filtername3) throws InterruptedException{
		String[] rules = AddRule.split("\\|");
		filtersCheck(driver, rules[0], Filtername1);
		WebElement addgrp1 = driver.findElement(By.xpath("//i[@class='pf-icon fa fa-sitemap']"));
		waitForElementPresent(driver, 30, addgrp1, "Add group icon");
		click(driver, addgrp1, "Add group icon");
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);
		By controlunit = By.xpath("//span[text()='"+rules[1]+"']");
		click(driver, controlunit, rules[1]+" checkbox");
		Thread.sleep(1000);	
		click(driver, iconclose);
		Thread.sleep(1000);	
		WebElement iconadd2 = driver.findElement(By.xpath("(//i[text()='add_circle'])[2]"));
		waitForElementPresent(driver, 30, iconadd2, "Icon Add 2");
		click(driver, iconadd2, "Icon Add 2");
		Thread.sleep(1000);
		String fliters[] =Filtername2.split("\\|");
		for (int i = 0; i < fliters.length; i++) {
			By filtername = By.xpath("//span[text()='"+fliters[i]+"']");
			waitForElementPresent(driver, 60, filtername, fliters[i]+" checkbox");
			click(driver, filtername, fliters[i]+" checkbox");			
		}		
		click(driver, iconclose);
		Thread.sleep(2000);
		for (int i = 0; i < fliters.length; i++) {
			WebElement geography = driver.findElement(By.xpath("//mat-chip[contains(text(),'"+fliters[i]+"')]"));
			waitForElementPresent(driver, 30, geography, fliters[i]+" filter on Home Page");
		}
		WebElement andlabel = driver.findElement(By.xpath("(//span[text()='AND'])[2]"));
		waitForElementPresent(driver, 30, andlabel, "AND label");
		click(driver, andlabel, "AND label");
		Thread.sleep(2000);
		WebElement orlabel = driver.findElement(By.xpath("//span[text()='OR']"));
		waitForElementPresent(driver, 30, orlabel, "OR label");
		
		WebElement addgrp2 = driver.findElement(By.xpath("(//i[@class='pf-icon fa fa-sitemap'])[2]"));
		waitForElementPresent(driver, 30, addgrp2, "Add group icon");
		click(driver, addgrp2, "Add group icon");
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);
		By controlunit2 = By.xpath("//span[text()='"+rules[2]+"']");
		click(driver, controlunit2, rules[2]+" checkbox");
		Thread.sleep(1000);	
		click(driver, iconclose);
		Thread.sleep(1000);	
		WebElement iconadd3 = driver.findElement(By.xpath("(//i[text()='add_circle'])[3]"));
		waitForElementPresent(driver, 30, iconadd3, "Icon Add 3");
		click(driver, iconadd3, "Icon Add 3");
		Thread.sleep(1000);
		String fliters2[] =Filtername3.split("\\|");
		for (int i = 0; i < fliters.length; i++) {
			By filtername = By.xpath("//span[text()='"+fliters2[i]+"']");
			waitForElementPresent(driver, 60, filtername, fliters2[i]+" checkbox");
			click(driver, filtername, fliters2[i]+" checkbox");			
		}		
		click(driver, iconclose);
		Thread.sleep(2000);
		for (int i = 0; i < fliters.length; i++) {
			WebElement geography = driver.findElement(By.xpath("//mat-chip[contains(text(),'"+fliters[i]+"')]"));
			waitForElementPresent(driver, 30, geography, fliters[i]+" filter on Home Page");
		}		
	}
	
	public void TC38(WebDriver driver, String AddRule, String Filtername1,String Filtername2,String Filtername3) throws InterruptedException{
		String[] rules = AddRule.split("\\|");
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);
		By controlunit = By.xpath("//span[text()='"+rules[0]+"']");
		click(driver, controlunit, rules[0]+" checkbox");
		Thread.sleep(1000);
		controlunit = By.xpath("//span[text()='"+rules[1]+"']");
		click(driver, controlunit, rules[1]+" checkbox");
		Thread.sleep(1000);
		controlunit = By.xpath("//span[text()='"+rules[2]+"']");
		click(driver, controlunit, rules[2]+" checkbox");
		Thread.sleep(1000);
		click(driver, iconclose);
		Thread.sleep(1000);	
		By addfilter = By.xpath("(//i[text()='add_circle'])[1]");
		waitForElementPresent(driver, 30, addfilter, "Add Filter Icon "+rules[0]);
		click(driver, addfilter, "Add Filter Icon "+rules[0]);
		Thread.sleep(2000);
		String fliters[] =Filtername1.split("\\|");
		for (int i = 0; i < fliters.length; i++) {
			By filtername = By.xpath("//span[text()='"+fliters[i]+"']");
			waitForElementPresent(driver, 60, filtername, fliters[i]+" checkbox");
			click(driver, filtername, fliters[i]+" checkbox");			
		}		
		click(driver, iconclose);
		Thread.sleep(2000);
		for (int i = 0; i < fliters.length; i++) {
			WebElement geography = driver.findElement(By.xpath("//mat-chip[contains(text(),'"+fliters[i]+"')]"));
			waitForElementPresent(driver, 30, geography, fliters[i]+" filter on Home Page");
		}
		
		addfilter = By.xpath("(//i[text()='add_circle'])[2]");
		waitForElementPresent(driver, 30, addfilter, "Add Filter Icon "+rules[1]);
		click(driver, addfilter, "Add Filter Icon "+rules[1]);
		Thread.sleep(2000);
		String fliters2[] =Filtername2.split("\\|");
		for (int i = 0; i < fliters2.length; i++) {
			By filtername = By.xpath("//span[text()='"+fliters2[i]+"']");
			waitForElementPresent(driver, 60, filtername, fliters2[i]+" checkbox");
			click(driver, filtername, fliters2[i]+" checkbox");			
		}		
		click(driver, iconclose);
		Thread.sleep(2000);
		for (int i = 0; i < fliters2.length; i++) {
			WebElement geography = driver.findElement(By.xpath("//mat-chip[contains(text(),'"+fliters2[i]+"')]"));
			waitForElementPresent(driver, 30, geography, fliters2[i]+" filter on Home Page");
		}
		
		By INoperator = By.xpath("(//div[contains(text(),'IN')])[2]");
		waitForElementPresent(driver, 30, INoperator, "In Operator "+rules[1]);
		click(driver, INoperator, "In Operator "+rules[1]);	
		Thread.sleep(2000);
		By NOTINOperator = By.xpath("//div[contains(text(),'NOT IN')]");
		waitForElementPresent(driver, 30, NOTINOperator, "Not In Operator "+rules[1]);
		
		WebElement noradio = driver.findElement(By.xpath("//div[text()='No']"));
		waitForElementPresent(driver, 30, noradio, "No radio button "+rules[2]);
		click(driver, noradio, "No radio button "+rules[2]);
		waitForElementPresent(driver, 30, iconreview);
		click(driver, iconreview);
		Thread.sleep(1000);	
		By progressbar = By.xpath("//div[contains(@class,'loading-indicator')]");
		waitForElementInvisibilty(driver, 30, progressbar, "Progress Bar");
		By ExitSnackBar = By.xpath("//span[text()='EXIT']");
		
		if(driver.findElements(ExitSnackBar).size()>0){
			WebElement errormessage = driver.findElement(By.xpath("//simple-snack-bar[contains(@class,'mat-simple-snackbar')]/span"));
			Reporter.log("Error message: "+errormessage.getText());
			Add_Log.info("Error message: "+errormessage.getText());
			Assert.fail();			
		}
		By top10header = By.xpath("//h2[text()='Top Exposures']");
		waitForElementPresent(driver, 120, top10header, "Top10 Exposures Header");
		WebElement firstcell  = driver.findElement(By.xpath("(//mat-cell[text()])[1]"));
		waitForElementPresent(driver, 30, firstcell, "Top Exposures pop up first cell data");
			
	}
		
	public void TC39(WebDriver driver, String AddRule,String Level, String Filtername1,String Filtername2,String Filtername3) throws InterruptedException{
		String[] rules = AddRule.split("\\|");
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);
		By controlunit = By.xpath("//span[text()='"+rules[0]+"']");
		click(driver, controlunit, rules[0]+" checkbox");
		Thread.sleep(1000);
		controlunit = By.xpath("//span[text()='"+rules[1]+"']");
		click(driver, controlunit, rules[1]+" checkbox");
		Thread.sleep(1000);
		controlunit = By.xpath("//span[text()='"+rules[2]+"']");
		click(driver, controlunit, rules[2]+" checkbox");
		Thread.sleep(1000);
		click(driver, iconclose);
		Thread.sleep(1000);	
		WebElement noradio = driver.findElement(By.xpath("(//div[text()='No'])[2]"));
		waitForElementPresent(driver, 30, noradio, "No radio button "+rules[2]);
		click(driver, noradio, "No radio button "+rules[2]);
		
		By addfilter = By.xpath("(//i[text()='add_circle'])[1]");
		waitForElementPresent(driver, 30, addfilter, "Add Filter Icon "+rules[0]);
		click(driver, addfilter, "Add Filter Icon "+rules[0]);
		Thread.sleep(2000);
		String fliters[] =Filtername1.split("\\|");
		for (int i = 0; i < fliters.length; i++) {
			By filtername = By.xpath("//span[text()='"+fliters[i]+"']");
			waitForElementPresent(driver, 60, filtername, fliters[i]+" checkbox");
			click(driver, filtername, fliters[i]+" checkbox");			
		}		
		click(driver, iconclose);
		Thread.sleep(2000);
		for (int i = 0; i < fliters.length; i++) {
			WebElement geography = driver.findElement(By.xpath("//mat-chip[contains(text(),'"+fliters[i]+"')]"));
			waitForElementPresent(driver, 30, geography, fliters[i]+" filter on Home Page");
		}
		
		WebElement yesradio = driver.findElement(By.xpath("(//div[text()='Yes'])[1]"));
		waitForElementPresent(driver, 30, yesradio, "Yes radio button "+rules[1]);
		click(driver, yesradio, "Yes radio button "+rules[1]);
		
		WebElement addgrp1 = driver.findElement(By.xpath("//i[@class='pf-icon fa fa-sitemap']"));
		waitForElementPresent(driver, 30, addgrp1, "Add group icon");
		click(driver, addgrp1, "Add group icon");
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);
		controlunit = By.xpath("//span[text()='"+rules[3]+"']");
		click(driver, controlunit, rules[3]+" checkbox");
		Thread.sleep(1000);
		controlunit = By.xpath("//span[text()='"+rules[4]+"']");
		click(driver, controlunit, rules[4]+" checkbox");
		Thread.sleep(1000);
		click(driver, iconclose);
		Thread.sleep(1000);	
		
		addfilter = By.xpath("(//i[text()='add_circle'])[2]");
		waitForElementPresent(driver, 30, addfilter, "Add Filter Icon "+rules[3]);
		click(driver, addfilter, "Add Filter Icon "+rules[3]);
		Thread.sleep(2000);
		String levels[] = Level.split("\\|");
		for (int i = 0; i < levels.length; i++) {
			Thread.sleep(1000);	
			By level = By.xpath("//span[text()='"+levels[i]+"']");
			int size = driver.findElements(level).size();
			if (size == 1) {
				level = By.xpath("//span[text()='"+levels[i]+"']");
			} else {
				level = By.xpath("(//span[text()='"+levels[i]+"'])["+size+"]");
			}
			
			waitForElementPresent(driver, 30, level, levels[i]+" checkbox level "+(i+1));
			click(driver, level, levels[i]+" checkbox level "+(i+1));
			
			By levelNext = null;
			if (i < 3) {
				levelNext = By.xpath("//span[text()='Next']");
				levelNext = By.xpath("(//span[text()='Next'])["+(i+1)+"]");
				waitForElementPresent(driver, 30, levelNext, "Level "+(i+1)+" Next Button");
				click(driver, levelNext, "Level "+(i+1)+" Next Button");
				Thread.sleep(1000);	
			}			
						
		}
		
		click(driver, btnclose);
		Thread.sleep(2000);
		WebElement geography = driver.findElement(By.xpath("//mat-chip[contains(text(),'"+levels[levels.length -1]+"')]"));
		waitForElementPresent(driver, 30, geography, levels[levels.length - 1]+" filter on Home Page");
		
		addfilter = By.xpath("(//i[text()='add_circle'])[3]");
		waitForElementPresent(driver, 30, addfilter, "Add Filter Icon "+rules[4]);
		click(driver, addfilter, "Add Filter Icon "+rules[4]);
		Thread.sleep(2000);
		for (int i = 0; i < levels.length; i++) {
			Thread.sleep(1000);	
			By level = By.xpath("//span[text()='"+levels[i]+"']");
			int size = driver.findElements(level).size();
			if (size == 1) {
				level = By.xpath("//span[text()='"+levels[i]+"']");
			} else {
				level = By.xpath("(//span[text()='"+levels[i]+"'])["+size+"]");
			}
			
			waitForElementPresent(driver, 30, level, levels[i]+" checkbox level "+(i+1));
			click(driver, level, levels[i]+" checkbox level "+(i+1));
			
			By levelNext = null;
			if (i < 3) {
				levelNext = By.xpath("//span[text()='Next']");
				levelNext = By.xpath("(//span[text()='Next'])["+(i+1)+"]");
				waitForElementPresent(driver, 30, levelNext, "Level "+(i+1)+" Next Button");
				click(driver, levelNext, "Level "+(i+1)+" Next Button");
				Thread.sleep(1000);	
			}			
						
		}
		
		click(driver, btnclose);
		Thread.sleep(2000);
		geography = driver.findElement(By.xpath("(//mat-chip[contains(text(),'"+levels[levels.length - 1]+"')])[2]"));
		waitForElementPresent(driver, 30, geography, levels[levels.length - 1]+" filter on Home Page");
		WebElement andlabel = driver.findElement(By.xpath("(//span[text()='AND'])[2]"));
		waitForElementPresent(driver, 30, andlabel, "AND label");
		click(driver, andlabel, "AND label");
		Thread.sleep(2000);
		WebElement orlabel = driver.findElement(By.xpath("//span[text()='OR']"));
		waitForElementPresent(driver, 30, orlabel, "OR label");
		
		waitForElementPresent(driver, 30, iconreview);
		click(driver, iconreview);
		Thread.sleep(1000);	
		By progressbar = By.xpath("//div[contains(@class,'loading-indicator')]");
		waitForElementInvisibilty(driver, 30, progressbar, "Progress Bar");
		By ExitSnackBar = By.xpath("//span[text()='EXIT']");
		
		if(driver.findElements(ExitSnackBar).size()>0){
			WebElement errormessage = driver.findElement(By.xpath("//simple-snack-bar[contains(@class,'mat-simple-snackbar')]/span"));
			Reporter.log("Error message: "+errormessage.getText());
			Add_Log.info("Error message: "+errormessage.getText());
			Assert.fail();			
		}
		By top10header = By.xpath("//h2[text()='Top Exposures']");
		waitForElementPresent(driver, 120, top10header, "Top10 Exposures Header");
		WebElement firstcell  = driver.findElement(By.xpath("(//mat-cell[text()])[1]"));
		waitForElementPresent(driver, 30, firstcell, "Top Exposures pop up first cell data");
			
	}

	public void TC40(WebDriver driver, String AddRule) throws InterruptedException{
		String[] rules = AddRule.split("\\|");
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);
		By controlunit = By.xpath("//span[text()='"+rules[0]+"']");
		click(driver, controlunit, rules[0]+" checkbox");
		Thread.sleep(1000);		
		click(driver, iconclose);
		Thread.sleep(1000);	
		waitForElementPresent(driver, 30, iconreview);
		click(driver, iconreview);
		Thread.sleep(1000);	
		By progressbar = By.xpath("//div[contains(@class,'loading-indicator')]");
		waitForElementInvisibilty(driver, 30, progressbar, "Progress Bar");
		By ExitSnackBar = By.xpath("//span[text()='EXIT']");
		
		if(driver.findElements(ExitSnackBar).size()>0){
			By snackbar = By.xpath("//span[contains(text(),'A rule cannot be empty.Please add values to the rule [ CIS Hierarchy:Level 2 Client Owner ] or remove it all together')]");
			waitForElementInvisibilty(driver, 60, snackbar, "A rule cannot be empty Snack bar message");			
		}

	}
	
	public void TC41(WebDriver driver, String AddRule) throws InterruptedException{
		String[] rules = AddRule.split("\\|");
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);
		By controlunit = By.xpath("//span[text()='"+rules[0]+"']");
		click(driver, controlunit, rules[0]+" checkbox");
		Thread.sleep(1000);		
		click(driver, iconclose);
		Thread.sleep(1000);	
		WebElement addgrp1 = driver.findElement(By.xpath("//i[@class='pf-icon fa fa-sitemap']"));
		waitForElementPresent(driver, 30, addgrp1, "Add group icon");
		click(driver, addgrp1, "Add group icon");
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);
		click(driver, iconclose);
		Thread.sleep(1000);	
		waitForElementPresent(driver, 30, iconreview);
		click(driver, iconreview);
		Thread.sleep(1000);	
		By progressbar = By.xpath("//div[contains(@class,'loading-indicator')]");
		waitForElementInvisibilty(driver, 30, progressbar, "Progress Bar");
		By ExitSnackBar = By.xpath("//span[text()='EXIT']");
		
		if(driver.findElements(ExitSnackBar).size()>0){
			By snackbar = By.xpath("//span[contains(text(),'Group cannot be empty. Please add a rule or remove it all together.A rule cannot be empty.Please add values to the rule [ CIS Hierarchy:Level 2 Client Owner ] or remove it all together')]");
			waitForElementInvisibilty(driver, 60, snackbar, "Group cannot be empty Snack bar message");		
		}
		
	}
	
	public void TC42(WebDriver driver, String AddRule) throws InterruptedException{
		String[] rules = AddRule.split("\\|");
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);
		By controlunit = By.xpath("//span[text()='"+rules[0]+"']");
		click(driver, controlunit, rules[0]+" checkbox");
		Thread.sleep(1000);		
		click(driver, iconclose);
		Thread.sleep(1000);	
		waitForElementPresent(driver, 60, sidenavbutton);
		click(driver, sidenavbutton);
		Thread.sleep(1000);	
		waitForElementPresent(driver, 60, viewcopyportfolio);
		click(driver, viewcopyportfolio);
		Thread.sleep(1000);	
		waitForElementPresent(driver, 60, savepopup);
		waitForElementPresent(driver, 60, savebuttonpopup);
		click(driver, savebuttonpopup);
		Thread.sleep(1000);	
		WebElement activeEle = driver.switchTo().activeElement();
		String placeholderAttrValue = activeEle.getAttribute("placeholder");
		if (placeholderAttrValue.equalsIgnoreCase("Porfolio Name")) {
			Reporter.log("Cursor focus  at Portfolio Name text field");
			Add_Log.info("Cursor focus  at Portfolio Name text field");
		} else {
			Reporter.log("Cursor focus is not at Portfolio Name text field");
			Add_Log.info("Cursor focus is not at Portfolio Name text field");
			Assert.fail();
		}
	}

	public void TC43(WebDriver driver, String AddRule) throws InterruptedException{
		boolean flag = false;
		String[] rules = AddRule.split("\\|");
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);
		By controlunit = By.xpath("//span[text()='"+rules[0]+"']");
		click(driver, controlunit, rules[0]+" checkbox");
		Thread.sleep(1000);		
		click(driver, iconclose);
		Thread.sleep(1000);	
		waitForElementPresent(driver, 60, sidenavbutton);
		click(driver, sidenavbutton);
		Thread.sleep(1000);	
		waitForElementPresent(driver, 60, viewcopyportfolio);
		click(driver, viewcopyportfolio);
		Thread.sleep(1000);	
		waitForElementPresent(driver, 60, savepopup);
		waitForElementPresent(driver, 60, nothnksbuttonpopup);
		click(driver, nothnksbuttonpopup);
		Thread.sleep(1000);	
		waitForElementPresent(driver, 60, porfoliosheader);				
	}

	public void TC44(WebDriver driver, String AddRule, String Filter) throws InterruptedException{
		boolean flag = false;
		String[] rules = AddRule.split("\\|");
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);
		By controlunit = By.xpath("//span[text()='"+rules[0]+"']");
		click(driver, controlunit, rules[0]+" checkbox");
		Thread.sleep(1000);		
		click(driver, iconclose);
		Thread.sleep(1000);	
		waitForElementPresent(driver, 30, iconadd);
		click(driver, iconadd);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, filtersearch);
		setText(driver, filtersearch, Filter);
		Thread.sleep(2000);
		List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'mat-progress-bar')]"));
		waitForElementInvisibilty(driver, 60, ele, "Add Filters Progress Bar");
		List<WebElement> filterlabels = driver.findElements(By.xpath("//span[@class='mat-checkbox-label']"));
		waitForElementPresent(driver, 60, filterlabels, "Filter labels");
		for (WebElement filterLabel : filterlabels) {
			String label = filterLabel.getText();
			if (!label.contains("GOOGLE")) {
				Reporter.log(label+" search value does not contains GOOGLE");
				Add_Log.info(label+" search value does not contains GOOGLE");
				flag = true;
			} 
		}		
		if (flag) {
			Assert.fail();
		}		
	}

	public void TC45(WebDriver driver, String AddRule, String Filter) throws InterruptedException{
		String[] rules = AddRule.split("\\|");
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);
		By controlunit = By.xpath("//span[text()='"+rules[0]+"']");
		click(driver, controlunit, rules[0]+" checkbox");
		Thread.sleep(1000);		
		click(driver, iconclose);
		Thread.sleep(1000);	
		waitForElementPresent(driver, 30, iconadd);
		click(driver, iconadd);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, filtersearch);
		setText(driver, filtersearch, Filter);
		Thread.sleep(2000);
		List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'mat-progress-bar')]"));
		waitForElementInvisibilty(driver, 60, ele, "Add Filters Progress Bar");
		List<WebElement> filterlabels = driver.findElements(By.xpath("//span[@class='mat-checkbox-label']"));	
		if (filterlabels.size() == 0) {
			Reporter.log("No values shown in dailog for Search value Indians");
			Add_Log.info("No values shown in dailog for Search value Indians");
		}else{
			for (WebElement filterLabel : filterlabels) {
				String label = filterLabel.getText();
				Reporter.log(label+" value shown in dailog for Search value Indians");
				Add_Log.info(label+" value shown in dailog for Search value Indians");
			}	
			Assert.fail();
		}		
	}

	public void TC46(WebDriver driver, String AddRule, String Filter) throws InterruptedException{
		boolean flag = false;
		String[] rules = AddRule.split("\\|");
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, iconforward);
		By controlunit = By.xpath("//span[text()='"+rules[0]+"']");
		click(driver, controlunit, rules[0]+" checkbox");
		Thread.sleep(1000);		
		click(driver, iconclose);
		Thread.sleep(1000);	
		waitForElementPresent(driver, 30, iconadd);
		click(driver, iconadd);
		Thread.sleep(2000);
		WebElement firmexpansionicon  = driver.findElement(By.xpath("//mat-card-title[contains(text(),'FIRM')]//i"));
		waitForElementPresent(driver, 60, firmexpansionicon, "Expansion icon FIRM filter");
		click(driver, firmexpansionicon, "Expansion icon FIRM filter");
		By firmheader = By.xpath("//div[text()='FIRM']");
		waitForElementPresent(driver, 60, firmheader, "FIRM Header Expansion Dialog");
		setText(driver, filtersearch, Filter);
		Thread.sleep(2000);
		List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'mat-progress-bar')]"));
		waitForElementInvisibilty(driver, 60, ele, "Add Filters Progress Bar");
		List<WebElement> filterlabels = driver.findElements(By.xpath("//span[@class='ng-star-inserted']//following-sibling::span[@class='mat-checkbox-label']"));
		ArrayList<String> filters = new  ArrayList<>();
		waitForElementPresent(driver, 60, filterlabels, "Filter labels");
		if (filterlabels.size() == 6) {
			Reporter.log("6 values appeared when searched NEW YORK");
			Add_Log.info("6 values appeared when searched NEW YORK");
		} else {
			Reporter.log(filterlabels.size()+" values appeared when searched NEW YORK");
			Add_Log.info(filterlabels.size()+" values appeared when searched NEW YORK");
			flag = true;
		}
		for (WebElement filterLabel : filterlabels) {
			String label = filterLabel.getText();
			if (label.contains("NEW YORK")) {
				click(driver, filterLabel, label);	
				filters.add(label);
			} 
		}	
		click(driver, arrowback);
		Thread.sleep(1000);
		firmexpansionicon  = driver.findElement(By.xpath("//mat-card-title[contains(text(),'FIRM')]//i"));
		waitForElementPresent(driver, 60, firmexpansionicon, "Expansion icon FIRM filter");
		click(driver, iconclose);
		Thread.sleep(2000);		
		for (int i = 0; i < filters.size(); i++) {
			WebElement geography = driver.findElement(By.xpath("//mat-chip[contains(text(),'"+filters.get(i)+"')]"));
			waitForElementPresent(driver, 30, geography, filters.get(i)+" filter on Home Page");
		}
		
		waitForElementPresent(driver, 30, iconreview);
		click(driver, iconreview);
		Thread.sleep(1000);	
		By progressbar = By.xpath("//div[contains(@class,'loading-indicator')]");
		waitForElementInvisibilty(driver, 30, progressbar, "Progress Bar");
		By ExitSnackBar = By.xpath("//span[text()='EXIT']");
		
		if(driver.findElements(ExitSnackBar).size()>0){
			WebElement errormessage = driver.findElement(By.xpath("//simple-snack-bar[contains(@class,'mat-simple-snackbar')]/span"));
			Reporter.log("Error message: "+errormessage.getText());
			Add_Log.info("Error message: "+errormessage.getText());
			Assert.fail();			
		}
		By top10header = By.xpath("//h2[text()='Top Exposures']");
		waitForElementPresent(driver, 120, top10header, "Top10 Exposures Header");
		WebElement firstcell  = driver.findElement(By.xpath("(//mat-cell[text()])[1]"));
		waitForElementPresent(driver, 30, firstcell, "Top Exposures pop up first cell data");
		
		if (flag) {
			Assert.fail();
		}
		
	}

	public void TC47(WebDriver driver) throws InterruptedException
	{
		boolean flag = false;
		waitForElementPresent(driver, 60, sidenavbutton);
		click(driver, sidenavbutton);
		waitForElementPresent(driver, 60, viewcopyportfolio);
		click(driver, viewcopyportfolio);
		By editiconportfolio  = By.xpath("((//mat-cell[contains(text(),'Power_ys17146')])[2]//following::i)[1]");
		waitForElementPresent(driver, 60, editiconportfolio, "Edit icon Power_ys17146 porfolio");
		click(driver, editiconportfolio, "Edit icon Power_ys17146 porfolio");
		Thread.sleep(2000);
		waitForElementPresent(driver, 60, viewcopyportfolio);
		
		click(driver, sidenavbutton);
		Thread.sleep(2000);	
		
		By facilityintercompanyflag = By.xpath("//h2[contains(text(),' Facility:Intercompany Flag')]");
		waitForElementPresent(driver, 60, facilityintercompanyflag, "Facility:Intercompany Flag");
				
		By nofacilityintercompany = By.xpath("(//div[@class='mat-radio-outer-circle'])[2]");
		String backgroundColor = driver.findElement(nofacilityintercompany).getCssValue("border-color");
		if (backgroundColor.equalsIgnoreCase("rgb(63, 81, 181)")) {
			Reporter.log("Facility:Intercompany Flag with option No selected");
			Add_Log.info("Facility:Intercompany Flag with option No selected");
		} else {
			Reporter.log("Facility:Intercompany Flag with option No is not selected");
			Add_Log.info("Facility:Intercompany Flag with option No us not selected");
			flag = true;
		}
		
		By RelationshipIndependentRiskUnitCreditOrganization = By.xpath("//h2[contains(text(),'Relationship:Independent Risk Unit Credit Organization')]"); 
		waitForElementPresent(driver, 60, RelationshipIndependentRiskUnitCreditOrganization, "Relationship:Independent Risk Unit Credit Organization");
		WebElement girm = driver.findElement(By.xpath("//mat-chip[contains(text(),'GIRM')]"));
		waitForElementPresent(driver, 30, girm, " GRIM filter Relationship:Independent Risk Unit Credit Organization ");
		
		By RelationshipRiskManagementIndustry = By.xpath("//h2[contains(text(),'Relationship:Relationship Risk Management Industry')]"); 
		waitForElementPresent(driver, 60, RelationshipRiskManagementIndustry, "Relationship:Relationship Risk Management Industry");
		WebElement power = driver.findElement(By.xpath("//mat-chip[contains(text(),'Power')]"));
		waitForElementPresent(driver, 30, power, "Power filter Relationship:Relationship Risk Management Industry");
		
		
		waitForElementPresent(driver, 30, iconreview);
		click(driver, iconreview);
		Thread.sleep(1000);	
		By progressbar = By.xpath("//div[contains(@class,'loading-indicator')]");
		waitForElementInvisibilty(driver, 30, progressbar, "Progress Bar");
		By ExitSnackBar = By.xpath("//span[text()='EXIT']");
		
		if(driver.findElements(ExitSnackBar).size()>0){
			WebElement errormessage = driver.findElement(By.xpath("//simple-snack-bar[contains(@class,'mat-simple-snackbar')]/span"));
			Reporter.log("Error message: "+errormessage.getText());
			Add_Log.info("Error message: "+errormessage.getText());
			Assert.fail();			
		}
		waitForElementPresent(driver, 120, labeltop10expsoures);
		WebElement firstcell  = driver.findElement(By.xpath("(//mat-cell[text()])[1]"));
		waitForElementPresent(driver, 30, firstcell, "Top Exposures pop up first cell data");
		
		if (flag) {
			Assert.fail();
		}
	}
	
	public void TC48(WebDriver driver) throws InterruptedException
	{
		waitForElementPresent(driver, 60, sidenavbutton);
		click(driver, sidenavbutton);
		Thread.sleep(2000);
		waitForElementPresent(driver, 60, viewcopyportfolio);
		click(driver, viewcopyportfolio);
		Thread.sleep(1000);
		waitForElementPresent(driver, 30, porfoliosheader);
		
		String pageLabel = driver.findElement(By.xpath("(//label[contains(@class,'pf-pageNo-label')])[2]")).getText();
		int pageCount = Integer.parseInt(pageLabel.substring(3));
		for (int i = 1; i <=pageCount; i++) {
			if (i!=1) {
				waitForElementPresent(driver, 30, pagenumtextbox);
				setText(driver, pagenumtextbox, Integer.toString(i));
				Thread.sleep(4000);
			}
			List<WebElement> editicons = driver.findElements(By.xpath("//i[@class='material-icons']"));			
			for (int j = 1; j <= editicons.size(); j++) {				
				String porfolioName = driver.findElement(By.xpath("(//mat-table//mat-row)["+j+"]//mat-cell[2]")).getText();
				WebElement editicon = driver.findElement(By.xpath("(//i[@class='material-icons'])["+j+"]"));
				click(driver, editicon, porfolioName+" portfolio edit icon");
				Thread.sleep(1000);
				waitForElementPresent(driver, 30, buildportfolioheader);
				Reporter.log("Succesfully rendered to Portfolio Definition Screen for Portfolio "+porfolioName);
				Add_Log.info("Succesfully rendered to Portfolio Definition Screen for Portfolio "+porfolioName);
				click(driver, sidenavbutton);
				Thread.sleep(2000);
				try {
					WebElement element = driver.findElement(By.xpath("//strong[text()='View / Copy Portfolio']"));
					new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(element));		
					Add_Log.info("Successfully waited for View / Copy Portfolio to be present on page");
					Reporter.log("Successfully waited for View / Copy Portfolio to be present on page");
				} catch (Exception e) {
					click(driver, sidenavbutton);
					Thread.sleep(2000);
					WebElement element = driver.findElement(By.xpath("//strong[text()='View / Copy Portfolio']"));
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
					Add_Log.info("Successfully waited for View / Copy Portfolio to be present on page");
					Reporter.log("Successfully waited for View / Copy Portfolio to be present on page");
				}
				/*waitForElementPresent(driver, 60, viewcopyportfolio);*/
				click(driver, viewcopyportfolio);
				Thread.sleep(1000);
				waitForElementPresent(driver, 30, porfoliosheader);								
			}
		}		
	}
		
	public void TC49(WebDriver driver) throws InterruptedException
	{
		waitForElementPresent(driver, 60, sidenavbutton);
		click(driver, sidenavbutton);
		Thread.sleep(1000);
		waitForElementPresent(driver, 30, manageportfolio);
		click(driver, manageportfolio);
		Thread.sleep(1000);
		waitForElementPresent(driver, 30, porfoliosheader);
		click(driver, sidenavbutton);
		Thread.sleep(1000);
		List<WebElement> tableRows = driver.findElements(By.xpath("(//mat-table//mat-row)"));
		waitForElementPresent(driver, 30, tableRows, "Portfolios table Manage Portfolios page");
	}
	
	public void TC50(WebDriver driver) throws InterruptedException
	{
		waitForElementPresent(driver, 60, sidenavbutton);
		click(driver, sidenavbutton);
		Thread.sleep(1000);
		waitForElementPresent(driver, 30, approveportfolio);
		click(driver, approveportfolio);
		waitForElementPresent(driver, 30, porfoliosheader);
		click(driver, sidenavbutton);
		Thread.sleep(1000);
		List<WebElement> tableRows = driver.findElements(By.xpath("(//mat-table//mat-row)"));
		waitForElementPresent(driver, 30, tableRows, "Portfolios table Approve Portfolios page");
	}

	public void IDReviewResultCheck(WebDriver driver,String AddRule,String FilterName) throws InterruptedException{
		boolean flag = false;
		waitForElementPresent(driver, 30, btnaddrule);
		click(driver, btnaddrule);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, filtersearch);
		setText(driver, filtersearch, AddRule);
		Thread.sleep(2000);
		List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'mat-progress-bar')]"));
		waitForElementInvisibilty(driver, 60, ele, "Add Filters Progress Bar");	
		By controlunit = By.xpath("//span[text()='"+AddRule+"']");
		click(driver, controlunit, AddRule+" checkbox");
		Thread.sleep(2000);	
		click(driver, iconclose);
		Thread.sleep(2000);
		waitForElementPresent(driver, 30, textarea);
		setText(driver, textarea, FilterName);
		waitForElementPresent(driver, 30, iconreview);
		click(driver, iconreview);
		Thread.sleep(1000);	
		By progressbar = By.xpath("//div[contains(@class,'loading-indicator')]");
		waitForElementInvisibilty(driver, 30, progressbar, "Progress Bar");		
		By top10header = By.xpath("//h2[text()='Top Exposures']");
		waitForElementPresent(driver, 120, top10header, "Top10 Exposures Header");
		int rowcount = driver.findElements(By.xpath("//mat-row")).size();
		if (rowcount<2) {
			Reporter.log("Top exposures dailog results contain "+rowcount+" record. It should show 2 records.");
			Add_Log.info("Top exposures dailog results contain "+rowcount+" record. It should show 2 records.");
			flag = true;
		}
		
		if (flag) {
			Assert.fail();
		}
	}



}







