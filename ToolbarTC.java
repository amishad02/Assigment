package pageobjects;

import java.awt.Window;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import property.IHomePage;
import testsuitebase.SuiteBase;
import testsuitebase.TestResultStatus;
import utility.SeleniumUtils;
import utility.WebPageElements;
import pageobjects.SnackBar;

public class ToolbarTC extends SeleniumUtils implements IHomePage {

	public DecimalFormat df = new DecimalFormat("#.##");
	public String testName = null;
	public boolean Testskip = false;
	public boolean Testfail = false;
	SoftAssert s_assert = null;
	public double finish, start;
	public double end;
	boolean allFlag = false;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	public static String methodName() {
		String methodname = null;
		StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
		for (StackTraceElement elem : stackTraceElements) {
			// System.out.println(elem);
			if (elem.getClassName().contains("testcases")) {
				methodname = elem.getMethodName();
				// System.out.println(methodname);
			}

		}
		return methodname;
	}

	public void verifyContainsText(String ActualValue, String ExpValue) {
		// pass the value as per requirement , some times we need check contain text
		if (!ActualValue.contains(ExpValue)) {
			Add_Log.info(ExpValue + " Unable to Verify with  " + ActualValue);
			Reporter.log(ExpValue + " Unable to Verify with " + ActualValue);
			TestResultStatus.mpaskeysnew.put(methodName(), ExpValue + " Unable to Verify with " + ActualValue);
			TestResultStatus.Testfail = true;
			Assert.fail();
		} else {
			Add_Log.info(ExpValue + " Successfully Verified with " + ActualValue);
			Reporter.log(ExpValue + " Successfully Verified with " + ActualValue);
		}
	}
	
	public void verifyContainsChildWindowURL(WebDriver driver, String methodName,WebPageElements Element, String actualViews) throws InterruptedException {

		click(driver, Element);
		Thread.sleep(3000);
		String parentwindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		while (itr.hasNext()) {
			String childwindow = itr.next();
			if (!parentwindow.equals(childwindow)) {
				driver.switchTo().window(childwindow);
				String url = driver.getCurrentUrl().toString();
				if (url.contains(actualViews)) {
					Add_Log.info("Successfully displayed " + actualViews + " in the URL");
					Reporter.log("Successfully displayed " + actualViews + " in the URL");
				} else {
					Add_Log.info("Unable to displayed " + actualViews + " in the URL");
					Reporter.log("Unable to displayed " + actualViews + " in the URL");
					TestResultStatus.mpaskeysnew.put(methodName, actualViews);
					Assert.fail();
				}

			}
			
		}
		driver.close();
		driver.switchTo().window(parentwindow);
		
	}


	public void toolSearchx(WebDriver driver, String methodName, String actualViews, String Verify, String ReportName)
			throws InterruptedException {
		String[] actualViewsp = actualViews.split(",");
		String[] Verifyp = Verify.split(",");
		String tablename = null;
		allFlag = headerToolSearchx(driver, methodName, actualViewsp[0], Verifyp[0], ReportName, tablename);
		 allFlag=headerToolSearchx(driver,methodName,actualViewsp[1],Verifyp[1],ReportName,tablename);
		 allFlag=headerToolSearchx(driver,methodName,actualViewsp[2],Verifyp[2],ReportName,tablename);
		if (allFlag) {
			TestResultStatus.Testfail = true;
			Reporter.log("No data under one of the Verify ");
			Add_Log.info("No data under one of the Verify ");
			TestResultStatus.mpaskeysnew.put(methodName, "No data under one of the Verify ");
			ITestResult result = Reporter.getCurrentTestResult();
			result.setAttribute("DontRetry", Boolean.TRUE.toString());
			System.out.println(result);
			TestResultStatus.Testfail = true;
			Assert.fail();
		}

	}

	public boolean headerToolSearchx(WebDriver driver, String methodName, String actualViews, String Verify,
			String ReportName, String tablename) throws InterruptedException {
		boolean flag = false;
		if (actualViews.equalsIgnoreCase("Relationship")) {
			tablename = "Relationship Summary";
		} else if (actualViews.equalsIgnoreCase("Obligor")) {
			tablename = "Obligor Summary";
		} else if (actualViews.equalsIgnoreCase("Facility")) {
			tablename = "Facility Summary";
		}

		By ele1 = By.xpath("//input[@name='searchType']");
		waitForElementPresent(driver, 120, ele1, " expand Search box");
		click(driver, ele1, "by drilling down to " + actualViews + "expand Search box");
		By searchvalue = By.xpath("//li[text()='" + actualViews + "']");
		click(driver, searchvalue, actualViews);
		String sort1 = "//input[@name='searchEnhancementInToolbar']";
		By sort = By.xpath(sort1);
		Thread.sleep(2000);
		WebPageElements sort_dll = new WebPageElements(actualViews, "xpath", sort1);
		WebElement sort_dlle = driver.findElement(sort);
		clearText(driver, sort_dll);
		Thread.sleep(1000);
		setTextenter(driver, sort_dll, Verify);
		By inputValue = By.xpath("//*[contains(text(),'" + Verify + "')]");
		waitForElementPresentnoAssert(driver, 90, inputValue, Verify + " Search drop down");
		clicknoassert(driver, inputValue, Verify + " Search drop down");
		Thread.sleep(1000);
		waitForLoad(driver, 900);

		WebElement element = driver.findElement(By.xpath(
				"//span[text()='" + ReportName + "']//following::span[text()='" + actualViews + "']//ancestor::td"));
		System.out.println(element.getCssValue("background-color"));
		String color = element.getCssValue("background-color");
		if (color.equals("rgba(52, 56, 60, 1)")) {
			Reporter.log("Successfully Matching found under " + actualViews);
			Add_Log.info("Successfully Matching found under " + actualViews);
		} else {
			Reporter.log("Unable Matching found under " + actualViews);
			Add_Log.info("Unable Matching found under " + actualViews);
			flag = true;
			TestResultStatus.Testfail = true;
		}

		Thread.sleep(2000);
		waitForLoad(driver, 90);
		By tabledata = By.xpath("//div[text()='" + tablename+ "']//following::label[contains(text(),'Exposure Summary')]//following::tr[1]/td/div[@class='x-grid-cell-inner ']");
		try {
			waitForElementPresentnoAssert(driver, 90, tabledata, Verify);
			driver.findElement(tabledata).isDisplayed();
			Add_Log.info("Successfully display " + Verify + " table data for " + actualViews);
			Reporter.log("Successfully display " + Verify + " table data for " + actualViews);

		} catch (Exception e) {
			Add_Log.info("Not able to view " + Verify + " data  table with  all columns for" + actualViews);
			Reporter.log("Not able to view " + Verify + " data  table with  all columns for" + actualViews);
			TestResultStatus.mpaskeysnew.put(methodName(),
					"Not able to view " + Verify + " data  table with  all columns for" + actualViews);
			TestResultStatus.Testfail = true;
			flag = true;
			
		}
		if (flag) {
			TestResultStatus.Testfail = true;
			allFlag = true;
			
		}

		return allFlag;

	}

	public void iconClick(WebDriver driver, WebPageElements ele) {
		WebElement element = null;
		try {
			element = getWebElement(driver, ele);
			element.click();
			Add_Log.info("Successfully clicked on  " + ele.getName() + " element.");
			Reporter.log("Successfully clicked on " + ele.getName() + " element.");
		} catch (Exception e) {
			Add_Log.info("Not able to click " + ele.getName() + " element.");
			Reporter.log("Not able to click " + ele.getName() + " element.");
			TestResultStatus.mpaskeysnew.put(methodName(), "Not able to click " + ele.getName() + " element.");
			TestResultStatus.Testfail = true;
			Assert.fail();
		}

	}

	public boolean verifyPopup(WebDriver driver, String methodName, String actualViews) {
		boolean flag = false;
		String Element = driver.findElement(By.xpath("//*[text()='" + actualViews + "']")).getText();

		if (actualViews.equalsIgnoreCase(Element)) {
			Add_Log.info("Successfully displayed " + actualViews);
			Reporter.log("Successfully displayed " + actualViews);
		} else {
			Add_Log.info("Unable to display " + actualViews);
			Reporter.log("Unable to display " + actualViews);
			TestResultStatus.mpaskeysnew.put(methodName, actualViews);
			flag = true;
		}
		if (flag) {
			allFlag = true;
		}
		return allFlag;

	}

	public boolean verifyTextPresentinPopWindow(WebDriver driver, String methodName, String actualViews, String Verify)
			throws InterruptedException {
		boolean flag = false;
		click(driver, iconhelp);
		Thread.sleep(1000);

		String mainMenu = "//span[contains(text(),'" + actualViews + "')]";
		WebPageElements mainmenu = new WebPageElements(actualViews, "xpath", mainMenu);
		waitForElementPresent(driver, 90, mainmenu);
		click(driver, mainmenu);
		Thread.sleep(2000);
		String Element = "//*[text()='" + Verify + "']";
		WebPageElements ele = new WebPageElements(actualViews, "xpath", Element);
		waitForElementPresent(driver, 90, ele);
		verifyTextPresent(driver, ele, Verify);

		String Element1 = "//*[contains(@class,'x-window-close-btn')]";
		WebPageElements ele1 = new WebPageElements("Close", "xpath", Element1);
		click(driver, ele1);
		return flag;

	}
public void verifyTextPresentChildWindow1(WebDriver driver, String methodName,WebPageElements Element, String actualViews) {
		
		boolean flag = false;

		try {
			String mainURL=driver.getCurrentUrl();
			click(driver, Element);
			Thread.sleep(1000);

			String parentwindow = driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			Iterator<String> itr = windows.iterator();
			while (itr.hasNext()) {
				String childwindow = itr.next();
				if (!parentwindow.equals(childwindow)) {
					driver.switchTo().window(childwindow);
					try {
						

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String url=driver.getCurrentUrl();
					
					
					verifyContainsText(url,actualViews);
					
					driver.close();
				}
			}
			driver.switchTo().window(parentwindow);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}


	
	public boolean verifyTextPresentChildWindow(WebDriver driver, String methodName,WebPageElements Element, String actualViewsp,
			String Verify) {
		boolean flag = false;

		try {
			click(driver, Element);
			Thread.sleep(1000);

			String mainMenu = "//span[contains(text(),'" + actualViewsp + "')]";

			WebPageElements mainmenu = new WebPageElements(actualViewsp, "xpath", mainMenu);
			waitForElementPresent(driver, 90, mainmenu);
			click(driver, mainmenu);
			Thread.sleep(2000);

			String parentwindow = driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			Iterator<String> itr = windows.iterator();
			while (itr.hasNext()) {
				String childwindow = itr.next();
				if (!parentwindow.equals(childwindow)) {
					driver.switchTo().window(childwindow);
					Thread.sleep(3000);
					String Value = "//*[text()='" + Verify + "']";
					WebPageElements value = new WebPageElements(actualViewsp, "xpath", Value);
					waitForElementPresent(driver, 90, value);

					verifyTextPresent(driver, value, Verify);
					driver.close();
				}
			}
			driver.switchTo().window(parentwindow);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}
	

	public boolean iconHelp(WebDriver driver, String methodName, String actualViews, String Verify)
			throws InterruptedException {
		String[] actualViewsp = actualViews.split(",");
		String[] Verifyp = Verify.split(",");

		allFlag = verifyTextPresentChildWindow(driver, methodName,iconhelp, actualViewsp[0], Verifyp[0]);
		allFlag = verifyTextPresentChildWindow(driver, methodName,iconhelp, actualViewsp[1], Verifyp[1]);
		// allFlag=helpSupport(driver,methodName,actualViewsp[2],iconhelp,Verifyp[2]);
		allFlag = verifyTextPresentChildWindow(driver, methodName,iconhelp, actualViewsp[3], Verifyp[3]);
		allFlag = verifyTextPresentinPopWindow(driver, methodName, actualViewsp[4], Verifyp[4]);
		allFlag = verifyTextPresentinPopWindow(driver, methodName, actualViewsp[5], Verifyp[5]);

		if (allFlag) {
			TestResultStatus.Testfail = true;

			TestResultStatus.mpaskeysnew.put(methodName, "Unable to Displayed ");
			ITestResult result = Reporter.getCurrentTestResult();
			result.setAttribute("DontRetry", Boolean.TRUE.toString());
			System.out.println(result);
			Assert.fail();
		}
		return allFlag;

	}

	public boolean mouseOver(WebDriver driver, String methodName, String actualViews, String ValueSet)
			throws InterruptedException {
		boolean flag = false;
		int j = 0;

		String[] actualViews2p = ValueSet.split(",");

		String windowHandle = driver.getWindowHandle();

		try {
			for (j = 0; j < actualViews2p.length; j++) {

				click(driver, adhocanalysis);
				Thread.sleep(3000);
				By MainMenu = By.xpath("//span[text()='" + actualViews + "']");
				WebElement mainMenu = driver.findElement(MainMenu);
				Actions action = new Actions(driver);
				action.moveToElement(mainMenu).perform();
				Thread.sleep(1000);
				By submenu = By.xpath("//a[@class='x-menu-item-link']//span[text()='" + actualViews2p[j] + "']");
				WebElement Submenu = driver.findElement(submenu);

				if (Submenu.isDisplayed()) {
					Actions action1 = new Actions(driver);
					action1.moveToElement(Submenu).click().build().perform();
					Thread.sleep(5000);
					Set<String> windows = driver.getWindowHandles();
					Iterator<String> itr = windows.iterator();
					while (itr.hasNext()) {
						String childwindow = itr.next();
						if (!windowHandle.equals(childwindow)) {
							driver.switchTo().window(childwindow);
							Thread.sleep(3000);
							String PerspectiveCombo = "//input[@name='perspectiveCombo']";
							WebPageElements perspectivecombo = new WebPageElements("Perspective Combobox", "xpath",
									PerspectiveCombo);
							waitForElementPresent(driver, 90, perspectivecombo);
							String ele2 = getValue(driver, perspectivecombo);
							// verifyValuePresent(driver, perspectivecombo, actualViews2p[j]);

							if (ele2.equalsIgnoreCase(actualViews2p[j])) {

								Add_Log.info(
										"Successfully displayed " + actualViews2p[j] + " On Perspective Combo Box  ");
								Reporter.log(
										"Successfully displayed " + actualViews2p[j] + "  On Perspective Combo Box  ");
							} else {
								Add_Log.info(
										" Unable to Displayed " + actualViews2p[j] + "  On Perspective Combo Box  ");
								Reporter.log(
										" Unable to Displayed " + actualViews2p[j] + "  On Perspective Combo Box  ");
								TestResultStatus.mpaskeysnew.put(methodName, actualViews2p[j]);
								flag = true;
								 Assert.fail();
							}

							driver.close();
						}
					}
				}

				else {
				
						Add_Log.info(" Unable to Displayed " + actualViews2p[j] + "  On Option Tree  ");
						Reporter.log(" Unable to Displayed " + actualViews2p[j] + "  On Option Tree  ");
						TestResultStatus.mpaskeysnew.put(methodName, actualViews2p[j]);
						flag = true;
						 Assert.fail();

					

				}
				driver.switchTo().window(windowHandle);

			}

		} catch (Exception e) {
			try {
				By MainMenu = By.xpath("//span[text()='" + actualViews + "']");
				WebElement mainMenu = driver.findElement(MainMenu);
				Actions action = new Actions(driver);
				action.moveToElement(mainMenu).perform();
				Thread.sleep(1000);
				clicknoassert(driver, MainMenu, actualViews);
				Thread.sleep(5000);
				Set<String> windows = driver.getWindowHandles();
				Iterator<String> itr = windows.iterator();
				while (itr.hasNext()) {
					String childwindow = itr.next();
					if (!windowHandle.equals(childwindow)) {

						driver.switchTo().window(childwindow);
						Thread.sleep(3000);
						String PerspectiveCombo = "//input[@name='perspectiveCombo']";
						WebPageElements perspectivecombo = new WebPageElements("Perspective Combobox", "xpath",
								PerspectiveCombo);
						waitForElementPresent(driver, 90, perspectivecombo);
						String ele2 = getValue(driver, perspectivecombo);

						if (!actualViews.contains(ele2)) {
							Add_Log.info(ele2 + " Unable to Verify with  " + actualViews);
							Reporter.log(ele2 + " Unable to Verify with " + actualViews);
							TestResultStatus.mpaskeysnew.put(methodName(),
									ele2 + " Unable to Verify with " + actualViews);
							TestResultStatus.Testfail = true;
							 Assert.fail();
						} else {
							Add_Log.info(ele2 + " Successfully Verified with " + actualViews);
							Reporter.log(ele2 + " Successfully Verified with " + actualViews);
						}
						driver.close();
					}

				}
				driver.switchTo().window(windowHandle);

			} catch (Exception e1) {
				
				Add_Log.info(" Unable to find  " + actualViews+" on option tree" );
				Reporter.log(" Unable to find  " + actualViews+" on option tree");
				TestResultStatus.mpaskeysnew.put(methodName(),
						" Unable to find  " + actualViews+" on option tree");
				TestResultStatus.Testfail = true;
			 Assert.fail();

			}

		}

		return allFlag;
	}

	public boolean adhocSingleClick(WebDriver driver, String methodName, String actualViews)
			throws InterruptedException {
		boolean flag = false;
		iconClick(driver, adhocanalysis);

		Thread.sleep(1000);
		String windowHandle = driver.getWindowHandle();
		String mainMenu = "//span[text()='" + actualViews + "']";
		System.out.println("view from excel : " + actualViews);
		WebPageElements mainmenu = new WebPageElements(actualViews, "xpath", mainMenu);
		waitForElementPresent(driver, 90, mainmenu);
		click(driver, mainmenu);
		Thread.sleep(2000);

		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		while (itr.hasNext()) {
			String childwindow = itr.next();

			if (!windowHandle.equals(childwindow)) {
				driver.switchTo().window(childwindow);
				Thread.sleep(3000);
				String Ele = driver.findElement(By.xpath("//input[@name='perspectiveCombo']")).getAttribute("value");
				System.out.println("Contains Text" + Ele);
				Thread.sleep(3000);
				if (actualViews.contains(Ele)) {
					Add_Log.info("Successfully displayed " + actualViews );
					Reporter.log("Successfully displayed "+ actualViews );
				} else {
					Add_Log.info("Unable to displayed " + actualViews);
					Reporter.log("Unable to displayed "+ actualViews );
					TestResultStatus.mpaskeysnew.put(methodName, "Unable to displayed ");
					flag = true;
					// Assert.fail();
				}

			}

		}
		if (flag) {
			allFlag = true;
		}
		return allFlag;
	}

	public boolean singleClick(WebDriver driver, String methodName, String actualViews) {
		boolean flag = false;
		try {

			
				click(driver, operationalreports);

				Thread.sleep(1000);
				String windowHandle = driver.getWindowHandle();
				String mainMenu = "//a[contains(@class,'x-menu-item-link')]//span[contains(text(),'" + actualViews
						+ "')]";
				
				WebPageElements mainmenu = new WebPageElements(actualViews, "xpath", mainMenu);
				waitForElementPresent(driver, 90, mainmenu);
				click(driver, mainmenu);
				Thread.sleep(2000);

				Set<String> windows = driver.getWindowHandles();
				Iterator<String> itr = windows.iterator();
				while (itr.hasNext()) {
					String childwindow = itr.next();

					if (!windowHandle.equals(childwindow)) {
						driver.switchTo().window(childwindow);
						Thread.sleep(3000);
						String Ele = "//input[@name='perspectiveCombo']";
						By Ele1=By.xpath(Ele);
						WebPageElements ele = new WebPageElements(" perspective Combo box ", "xpath", Ele);
						waitForElementPresent(driver, 90, ele);
						//String Element = driver.findElement(By.xpath(Ele)).getAttribute("value");
						String Element=getattvalue(driver,Ele1," perspective Combo box ");
						verifyContainsText(Element, actualViews);

					}

				}
				driver.close();

				driver.switchTo().window(windowHandle);

			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TestResultStatus.mpaskeysnew.put(methodName, "No Data Found ");
			flag = true;
		}
		if (flag) {
			allFlag = true;
		}

		
		return allFlag;

	}
	public boolean operationalReportsCheck(WebDriver driver, String methodName, String actualViews)
			throws InterruptedException {
		String[] actualViewsp = actualViews.split(",");
		allFlag=singleClick(driver,methodName,actualViewsp[0]);
		allFlag=singleClick(driver,methodName,actualViewsp[1]);
		
		if (allFlag) {
			TestResultStatus.Testfail = true;

			TestResultStatus.mpaskeysnew.put(methodName, "Unable to Displayed ");
			ITestResult result = Reporter.getCurrentTestResult();
			result.setAttribute("DontRetry", Boolean.TRUE.toString());
			System.out.println(result);
			Assert.fail();
		}
		return allFlag;
	
	}
	public boolean adjustmentsCheck1(WebDriver driver, String methodName, String actualViews, String Verify, String Verify2)
			throws InterruptedException {
		String[] actualViewsp = actualViews.split(",");
		allFlag=verifySelectandClickURL(driver,methodName,actualViewsp[0],Verify);
		allFlag=verifyTextPresentChildWindow(driver,methodName,adjustments,actualViewsp[1],Verify2);
		
		if (allFlag) {
			TestResultStatus.Testfail = true;

			TestResultStatus.mpaskeysnew.put(methodName, "Unable to Displayed ");
			ITestResult result = Reporter.getCurrentTestResult();
			result.setAttribute("DontRetry", Boolean.TRUE.toString());
			System.out.println(result);
			Assert.fail();
		}
		return allFlag;
	
	}

	public boolean verifySelectandClickURL(WebDriver driver, String methodName, String actualViews, String Verify) throws InterruptedException {
		click(driver, adjustments);
		Thread.sleep(1000);
		
		String mainMenu = "//span[text()='" + actualViews + "']";
		WebPageElements mainmenu = new WebPageElements(actualViews, "xpath", mainMenu);
		waitForElementPresent(driver, 90, mainmenu);
		verifyContainsChildWindowURL(driver, methodName,mainmenu, Verify);
		return allFlag;
		

	}

	public boolean adjustmentsCheck(WebDriver driver, String methodName, String actualViews, String Verify2)
			throws InterruptedException {

		try {
			click(driver, adjustments);
			Thread.sleep(1000);
			
			String windowHandle = driver.getWindowHandle();
			String mainMenu = "//span[text()='" + actualViews + "']";
			WebPageElements mainmenu = new WebPageElements(actualViews, "xpath", mainMenu);
			waitForElementPresent(driver, 90, mainmenu);
			click(driver, mainmenu);
			Thread.sleep(2000);

			Set<String> windows = driver.getWindowHandles();
			Iterator<String> itr = windows.iterator();
			while (itr.hasNext()) {
				String childwindow = itr.next();

				if (!windowHandle.equals(childwindow)) {
					driver.switchTo().window(childwindow);
					Thread.sleep(3000);
					String Ele = "//*[contains(text(),'Data Visualization Criteria :')]";
					WebPageElements ele = new WebPageElements("CECL page", "xpath", Ele);
					waitForElementPresent(driver, 90, ele);
					String Element = getText(driver, ele);

					verifyContainsText(Element, Verify2);
				}

			}

			driver.switchTo().window(windowHandle);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allFlag;

	}

	public void breadCrumbCheck(WebDriver driver, String methodName, String ReportName, String viewName, String Verify)
			throws InterruptedException {
		
		try {
			waitForElementPresent(driver, 300, expand_btn);
			click(driver, expand_btn);
			Thread.sleep(1000);
			By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
			click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName + " aggregation");
			Thread.sleep(2000);
			By ele1 = By.xpath("//a[contains(@class,'breadcrumbBtn')]");
			click(driver, ele1, "Summary on BreadCrumb ");
			Thread.sleep(3000);

			WebElement SumRelation = driver.findElement(By.xpath("//span[text()='" + Verify + "']//following::span[text()='" + viewName + "'][1]//ancestor::td"));
			System.out.println(SumRelation.getCssValue("background-color"));
			String color = SumRelation.getCssValue("background-color");
			if (color.equals("rgba(52, 56, 60, 1)")) {
				Reporter.log(
						"Successfully Matching found under " + Verify + " With " + viewName + " aggregation");
				Add_Log.info(
						"Successfully Matching found under " + Verify + " With " + viewName + " aggregation");
			} else {
				Reporter.log("Unable Matching found under " + Verify + " With " + viewName + " aggregation");
				Add_Log.info("Unable Matching found under " + Verify + " With " + viewName + " aggregation");
				TestResultStatus.Testfail = true;
				Assert.fail();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public void cobCompare(WebDriver driver, String methodName, String ReportName, String viewName)
			throws InterruptedException {

		waitForLoad(driver, 120);
		Thread.sleep(1000);

		By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
		click(driver, ele, ReportName + " and select " + viewName + " aggregation");
		Thread.sleep(2000);

		String cobdate = "//span[contains(text(),'Best Available')]";
		WebPageElements Cobdate = new WebPageElements("COB date", "xpath", cobdate);
		waitForElementPresent(driver, 90, Cobdate);
		String CobDate = getText(driver, Cobdate);

		
		String Asofdate = "//b[contains(text(),'AS OF')]/parent::div/parent::div/parent::div/following-sibling::div[1]";
		WebPageElements AsofDate = new WebPageElements("AS OF date", "xpath", Asofdate);
		waitForElementPresent(driver, 90, AsofDate);
		String AsOfDate = getText(driver, AsofDate);

		verifyContainsText(CobDate, AsOfDate);

	}

	public void greenLightCheck(WebDriver driver, String methodName, String actualViews) throws InterruptedException {

		click(driver, greenlight);
		Thread.sleep(3000);
		String Ele = "//*[text()='" + actualViews + "']";
		WebPageElements ele = new WebPageElements("Green light window", "xpath", Ele);
		verifyTextPresent(driver, ele, actualViews);

	}

	public void costOfCreditCheck(WebDriver driver, String methodName, String actualViews) throws InterruptedException {
		
		click(driver, costofcredit);
		Thread.sleep(3000);
		String Ele = "//*[text()='" + actualViews + "']";
		WebPageElements ele = new WebPageElements("CostOfCredit Window", "xpath", Ele);
		verifyTextPresent(driver, ele, actualViews);
	}

	public void dataDictionaryCheck(WebDriver driver, String methodName, String actualViews)
			throws InterruptedException {
		
		click(driver, datadictionary);
		Thread.sleep(3000);
		String Ele = "//*[text()='" + actualViews + "']";
		WebPageElements ele = new WebPageElements("Data Dictionary  Window", "xpath", Ele);
		verifyTextPresent(driver, ele, actualViews);
	}

	public void BBTCCARcheck(WebDriver driver, String methodName, String actualViews) throws InterruptedException {
//		click(driver, BankBookTransformCCAR);
//		Thread.sleep(3000);
		verifyContainsChildWindowURL(driver, methodName, BankBookTransformCCAR,actualViews);
	}

	public void baselViewCheck(WebDriver driver, String methodName, String actualViews) throws InterruptedException {
//		click(driver, baselview);
//		Thread.sleep(3000);
		verifyContainsChildWindowURL(driver, methodName,baselview, actualViews);
	}

	public void managementReportingCheck(WebDriver driver, String methodName, String actualViews)
			throws InterruptedException {
//		click(driver, managementreporting);
//		Thread.sleep(3000);
		verifyContainsChildWindowURL(driver, methodName,managementreporting, actualViews);

	}

	public void overlaysGatewayCheck(WebDriver driver, String methodName, String actualViews)
			throws InterruptedException {
//		click(driver, overlaysgateway);
//		Thread.sleep(3000);
		verifyContainsChildWindowURL(driver, methodName,overlaysgateway, actualViews);

	}

	public boolean adhocAnalysischeck(WebDriver driver, String methodName, String actualViews, String actualViews2,
			String Verify, String Verify2) throws InterruptedException {

		
		String[] actualViewsp = actualViews.split(",");

		allFlag = mouseOver(driver, methodName, actualViewsp[0], actualViews2);
		allFlag = mouseOver(driver, methodName, actualViewsp[1],Verify);
		allFlag = mouseOver(driver, methodName, actualViewsp[2], Verify2);
//		allFlag = adhocSingleClick(driver, methodName, actualViewsp[3]);
		allFlag = mouseOver(driver, methodName, actualViewsp[3],"");
		
		if (allFlag) {
			TestResultStatus.Testfail = true;

			TestResultStatus.mpaskeysnew.put(methodName, "Unable to Displayed ");
			ITestResult result = Reporter.getCurrentTestResult();
			result.setAttribute("DontRetry", Boolean.TRUE.toString());
			System.out.println(result);
			//Assert.fail();
		}
		return allFlag;

	}

	public void greenLightWindowCheck(WebDriver driver, String methodName, String actualViews,String Verify) throws InterruptedException 
		{
		boolean flag = false;
		String[] Verifyp = Verify.split(",");
		List<String> views = new ArrayList<>();
		click(driver, greenlight);
		Thread.sleep(3000);
		
		By workstream = By.xpath("//*[@name='workstream']/parent::div/following-sibling::div");
		waitForElementPresent(driver, 120, workstream, " Workstream Selector ");
		click(driver, workstream, "by drilling down to " + actualViews + " Workstream Selector");
		By workstreamvalue = By.xpath("//li[text()='" + actualViews + "']");
		click(driver, workstreamvalue, actualViews);
		Thread.sleep(3000);
		
		waitForLoad(driver, 90);
		waitForElementPresent(driver, 90, greenlightfields);
		int size = driver.findElements(By.xpath(GreenlightFields)).size();
		for (int i = 1; i <= size; i++) {
			views.add(getText(driver, By.xpath("(" + GreenlightFields + ")[" + i + "]"),"GreenLight window Field #" + i));
		}
		System.out.println(views + " New View");
		for (int i = 0; i < Verifyp.length; i++) {
			// System.out.println(actualViews[i] + " Actual");
			// System.out.println(views + " Array");
			if (views.get(i).equals(Verifyp[i].trim().toString())) {
				Add_Log.info("Successfully displayed " + Verifyp[i].trim().toString()+ " Field in Green Light Window ");
				Reporter.log("Successfully displayed " + Verifyp[i].trim().toString()+ " Field in Green Light Window ");
			} else {
				Add_Log.info(Verifyp[i].trim().toString()+ " Field is not displayed in Green Light Window");
				Reporter.log(Verifyp[i].trim().toString()+ " Field is not displayed in Green Light Window");
				TestResultStatus.mpaskeysnew.put(methodName, Verifyp[i].trim().toString()+ " Field is not displayed in Green Light Window with with WorkStream: "+actualViews);
			
				flag = true;
			}
		}
		if (flag) {
			TestResultStatus.Testfail = true;
			Assert.fail();
		} else {
			Add_Log.info("Successfully all Fields are displayed in Green Light Window with WorkStream: "+actualViews);
			Reporter.log("Successfully all Fields are displayed in Green Light Window with WorkStream: "+actualViews);
		}
		
	/*	String cobdate = "//span[contains(text(),'Best Available')]";
		WebPageElements Cobdate = new WebPageElements("COB date", "xpath", cobdate);
		waitForElementPresent(driver, 90, Cobdate);
		String CobDate = getText(driver, Cobdate);
		System.out.println("Cobdate: "+CobDate);
		String[] COB=CobDate.split(": "); 
		
		By ReportingPeriod = By.xpath("//*[contains(@name,'reportingPeriod')]/parent::div/following-sibling::div");
		waitForElementPresent(driver, 120, ReportingPeriod, " COB selector");
		click(driver, ReportingPeriod, "COB selector");
		By COBvalue = By.xpath("//li[text()='" + COB[1] + "']");
		try {
			if(driver.findElements(COBvalue).size()!=0)
			{
				click(driver, COBvalue, COB[1]);
				Thread.sleep(3000);
				By ResultData=By.xpath("//*[contains(text(),'Green Light Status')]/parent::div/parent::div/parent::div/following-sibling::div[2]//div//div//table//tbody//tr//td[2]//div");
				List<WebElement> list=driver.findElements(ResultData);
				if(list.size()!=0)
				{
					Add_Log.info("Data available For this Cob");
					Reporter.log("Data available For this Cob");
				}
				else
				{
					Add_Log.info("Data Not available For this Cob");
					Reporter.log("Data Not available For this Cob");
					WebElement COBData=driver.findElement(By.xpath("//*[contains(text(),'"+COB[1]+"')]"));
					click(driver, ReportingPeriod, "by drilling down to Another COB selector");
					Actions action=new Actions(driver);
					action.sendKeys(Keys.UP).build().perform();
					Thread.sleep(3000);
					Actions action1=new Actions(driver);
					action1.sendKeys(Keys.ENTER).build().perform();
					String ELE=driver.findElement(ReportingPeriod).getText();
					System.out.println(ELE);	
				}
			}
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		By Round=By.xpath("//*[contains(@name,'round')]/parent::div/following-sibling::div");
		if(driver.findElements(Round).size()!=0)
		{
			Thread.sleep(1000);
			click(driver, Round, "Select Round");
			By ROUND = By.xpath("//li[1]");
			//waitForElementPresent(driver, 120, ROUND, " Round Values");
			String round=driver.findElement(ROUND).getText();
			
			if(driver.findElements(ROUND).size()!=0)
			{
				click(driver, ROUND, round);
			}
			else
			{
				Add_Log.info("No Data available To Select Round");
				Reporter.log("No Data available To Select Round");	
			}			
		}	
		By Batch=By.xpath("//*[contains(@name,'batch') or contains(@name,'Batch')]/parent::div/following-sibling::div");
		if(driver.findElements(Batch).size()!=0)
		{
			Thread.sleep(1000);
			click(driver, Batch, "Select Batch");
				try {
				By batchid = By.xpath("//li[1]");
				String batch=driver.findElement(batchid).getText();	
				if(driver.findElements(batchid).size()!=0)
				{
					click(driver, batchid, batch);
				}
				else
				{
					Add_Log.info("No Data available To Select Batch");
					Reporter.log("No Data available To Select Batch");			
				}	
			} catch (Exception e) {
				By NoMatch=By.xpath("//*[contains(text(),'No matching found.')]");
				if(driver.findElements(NoMatch).size()!=0)
				{
					Add_Log.info("No matching found under Select Batch option ");
					Reporter.log("No matching found under Select Batch option ");
					}
			}
		} 
		//By ResultData=By.xpath("//table[contains(@class,'x-grid-item-selected')]//tbody//tr//td[2]//div");
		By ResultData=By.xpath("//*[contains(text(),'Green Light Status')]/parent::div/parent::div/parent::div/following-sibling::div[2]//div//div//table//tbody//tr//td[2]//div");
		List<WebElement> list=driver.findElements(ResultData);
		
		if(list.size()!=0)		
		{
			String Ele=getText(driver,ResultData," Green Light Status Result Data ");
			if (!Ele.contains(actualViews)) {
				Add_Log.info(actualViews + " Unable to Verify with  " + Ele);
				Reporter.log(actualViews + " Unable to Verify with " + Ele);
				TestResultStatus.mpaskeysnew.put(methodName(), actualViews + " Unable to Verify with " + Ele);
				TestResultStatus.Testfail = true;
				Assert.fail();
			} else {
				Add_Log.info(actualViews + " Successfully Verified with " + Ele);
				Reporter.log(actualViews + " Successfully Verified with " + Ele);
			}
		}
		else
		{
			Add_Log.info("No Data found Under Green Light Status With Workstream : "+actualViews);
			Reporter.log("No Data found Under Green Light Status With Workstream : "+actualViews);
			TestResultStatus.mpaskeysnew.put(methodName, "No Data found Under Green Light Status ");
			ITestResult result = Reporter.getCurrentTestResult();
			result.setAttribute("DontRetry", Boolean.TRUE.toString());
			System.out.println(result);
			Assert.fail();
		}
	*/
		
			
		}
	public void verifyReset(WebDriver driver, String methodName, String actualViews,String Verify) throws InterruptedException 
	{
		click(driver, greenlight);
		Thread.sleep(3000);
		
		By workstream = By.xpath("//*[@name='workstream']/parent::div/following-sibling::div");
		waitForElementPresent(driver, 120, workstream, " Workstream Selector ");
		click(driver, workstream, "by drilling down to " + actualViews + " Workstream Selector");
		By workstreamvalue = By.xpath("//li[text()='" + actualViews + "']");
		click(driver, workstreamvalue, actualViews);
		Thread.sleep(3000);
		
		By Reset=By.xpath("//*[contains(text(),'Reset')]");
		waitForElementPresent(driver, 120, Reset, " Reset Button ");
		clicknoassert(driver,Reset," Reset Button ");
		
		By workstream1 = By.xpath("//input[@name='workstream']");
		waitForElementPresent(driver, 120, workstream1, " WorkStream Placeholder ");
		String placeholder=driver.findElement(workstream1).getAttribute("placeholder");		
		if(placeholder.equalsIgnoreCase(Verify))
		{
			Add_Log.info("Successfully Verified Reset button");
			Reporter.log("Successfully Verified Reset button");
		}
		else
		{
			Add_Log.info("Unable to Reset");
			Reporter.log("Unable to Reset");
			TestResultStatus.mpaskeysnew.put(methodName(), "Unable to Reset");
			TestResultStatus.Testfail = true;
			Assert.fail();
			
		}
		
		
		
	}
	
	public void onDemandRevolutionFilterCheck(WebDriver driver, String methodName, String actualViews, String ReportName,
			String viewName) throws InterruptedException {

		try {

			waitForElementPresent(driver, 120, expand_btnotools);
			click(driver, expand_btnotools);
			Thread.sleep(1000);
			By ele = By.xpath("//span[text()='" + ReportName + "']//following::span[text()='" + viewName + "']");
			click(driver, ele, "by drilling down to " + ReportName + " and select " + viewName);
			Thread.sleep(2000);

			By clearFilter = By.xpath(
					"//*[(text()='On Demand Revaluation Request')]/following-sibling::a[@data-qtip='Clear All Filters']");
		
			waitForElementPresent(driver, 120, clearFilter, " Clear filter ");
			click(driver, clearFilter, " Clear Filter ");

			By runTypeSearch = By.xpath(
					"//*[(text()='On Demand Revaluation Request')]/parent::div/parent::div/parent::div/following-sibling::div[2]/div/div/div[4]/div/div/div/div/input");
			waitForElementPresent(driver, 120, runTypeSearch, " Run type Search Filter ");
			setText(driver, runTypeSearch, actualViews, "Run type Search Filter");
			Thread.sleep(2000);
			try {

				By anchorItemList = By.xpath(
						"//*[(text()='On Demand Revaluation Request')]/parent::div/parent::div/parent::div/following-sibling::div[3]/div/div/table[1]/tbody/tr/td[2]/div/a");
				waitForElementPresent(driver, 120, anchorItemList, "Filtered Request ID List");
				String text = getText(driver, anchorItemList, " Request ID");
				clicknoassert(driver, anchorItemList, text);
				Thread.sleep(3000);
				waitForLoad(driver, 200);

				By ResultData = By.xpath(
						"//*[contains(text(),'On Demand BAU Transactions')]/parent::div/parent::div/parent::div/following-sibling::div[3]/div/div/table[1]/tbody/tr/td[2]/div");
				List<WebElement> list = driver.findElements(ResultData);
System.out.println(list);
				if (list.size() != 0) {
					Add_Log.info(" Successfully Viewed Data under On Demand BAU Transactions : " + text);
					Reporter.log(" Successfully Viewed Data under On Demand BAU Transactions : " + text);

				} else {
					Add_Log.info(" No Data under On Demand BAU Transactions " + text);
					Reporter.log(" No Data under On Demand BAU Transactions " + text);
					By anchorItemList1 = By.xpath(
							"//*[(text()='On Demand Revaluation Request')]/parent::div/parent::div/parent::div/following-sibling::div[3]/div/div/table[2]/tbody/tr/td[2]/div/a");
					waitForElementPresent(driver, 120, anchorItemList1, "Filtered Request ID List");
					String text1 = getText(driver, anchorItemList1, " Request ID");
					clicknoassert(driver, anchorItemList1, text1);
					Thread.sleep(3000);
					waitForLoad(driver, 200);

					List<WebElement> list1 = driver.findElements(ResultData);
					System.out.println(list1);
					if (list1.size() != 0) {
						Add_Log.info(" Successfully Viewed Data under On Demand BAU Transactions : " + text1);
						Reporter.log(" Successfully Viewed Data under On Demand BAU Transactions : " + text1);

					} else {
						Add_Log.info(" No Data under On Demand BAU Transactions " + text + " , " + text1);
						Reporter.log(" No Data under On Demand BAU Transactions " + text + " , " + text1);
						TestResultStatus.mpaskeysnew.put(methodName, " No Data under On Demand BAU Transactions ");

						Assert.fail();
					}

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
		

}
