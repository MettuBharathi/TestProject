package com.insight.utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.insight.accelerators.ActionEngine;
import com.insight.accelerators.TestEngineWeb;
import com.insight.report.ConfigFileReadWrite;
import com.insight.report.ReporterConstants;

import org.apache.log4j.Logger;

public class CoreUtilities extends ActionEngine {
	String APP_BASE_URL = ConfigFileReadWrite.read(
			ReporterConstants.configReporterFile, "baseUrl");
	private final String msgClickSuccess = "Successfully Clicked On ";
	private final String msgClickFailure = "Unable To Click On ";

	public static By username;
	public static By password;
	public static By login;
	public static By selectLocation;
	public static By userDropDown;
	public static By logout;
	public static By ncusername;
	public static By Emlogout;
	public static By NClogout;
	public static By error404;
	public static By error403;
	public static By locationselectiontext;

	static {

		username = By.cssSelector("#username");
		password = By.cssSelector("#password");
		login = By.cssSelector("#submitLogin");
		userDropDown = By.cssSelector("#headerUserName");
		logout = By.cssSelector("#logOutDiv");
		ncusername = By.xpath("//*[@id='username']");
		Emlogout = By.xpath("(//img[@class='nav-img'])[4]");
		NClogout = By.xpath("//a[@title='Logout']/img");
		error404 = By.xpath("//p[contains(text(),'404')]");
		error403 = By.xpath("//p[contains(text(),'403')]");
		locationselectiontext = By.xpath(".//*[@id='locationSelectionContent']/h4");
	}

	/**
	 * doLogin
	 *
	 * @throws Throwable the throwable
	 */
	public void doLogin(String UserName, String Password) throws Throwable {
		if (isElementPresent(CoreUtilities.login, "login")) {

			this.reporter.SuccessReport("Launch Application", " WebPage launch sucesfully","");
			type(CoreUtilities.username, UserName, "usename");
			type(CoreUtilities.password, Password, "Password");
			click(CoreUtilities.login, "login");
		} else {
			this.reporter.failureReport("Launch Application", "Failed to launch Application", "",driver);
			throw new RuntimeException();
		}
	}

	/**
	 * logout
	 *
	 * @throws Throwable the throwable
	 */
	public void logout() throws Throwable {

		JSClick(CoreUtilities.userDropDown, "User Dropdown");
		JSClick(CoreUtilities.logout, "Logout");
	}

	/**
	 * selectLocation
	 *
	 * @throws Throwable the throwable
	 */

	public void selectLocation(String value) throws Throwable {
		By valuidentifier = By.xpath("//table[@id='locationsTable']//div//span[text()='" + value + "']/..");
		JSClick(valuidentifier, "Location ");
	}

	/**
	 * Verifylogout
	 *
	 * @throws Throwable the throwable
	 */
	public void verifylogout() throws Throwable {
		if (!isElementPresent(CoreUtilities.login, "login")) {
			int a = getElementsSize(CoreUtilities.userDropDown);
			// boolean falg=isElementPresent(CoreLoginLogout.userDropDown, "User Dropdown");
			if (a == 0) {

				//backuntilLogoutbutton();
				logout();
			} else {
				logout();
			}
		}
	}

	/**
	 * backuntilLogoutbutton
	 *
	 * @throws Throwable the throwable
	 */
	public void backuntilLogoutbutton(org.openqa.selenium.WebDriver driver) throws Throwable {
		String Url = driver.getCurrentUrl();
		if (Url.contains("tmx")) {
			if (!isElementPresent(CoreUtilities.error404, "404 Not found") || !isElementPresent(CoreUtilities.error403, "403 Not found")) {
				boolean loginpagelist=isVisibleOnly(driver, CoreUtilities.login, "login");
				//List<WebElement> loginpagelist = driver.findElements(CoreUtilities.login);
				//int loginpagelistcount = loginpagelist.size();
				LOG.info("this element Status    : " + loginpagelist);
				boolean locationpagelist=isVisibleOnly(driver, CoreUtilities.locationselectiontext, "locationselectiontext");
				//List<WebElement> locationpagelist = driver.findElements(CoreUtilities.locationselectiontext);
				//int Locationselectionpagesize = locationpagelist.size();
				LOG.info("this element status    : " + locationpagelist);
				if (!loginpagelist) {
					if (!locationpagelist) {
						boolean flag3 = isVisibleOnly(driver, CoreUtilities.userDropDown, "userDropDown");
						if (!flag3) {

							for (int i = 0; i < 4; i++) {
								driver.navigate().back();
								LOG.info("Inside for loop  : " + i);
								dynamicWaitByLocator(driver, CoreUtilities.NClogout, 3);
								boolean flag4 = isVisibleOnly(driver, CoreUtilities.userDropDown, "userDropDown");

								if (flag4) {
									break;
								}
							}
							
							/*click(driver,CoreUtilities.userDropDown, "User Dropdown");
							click(driver,CoreUtilities.logout, "Logout");*/
							try {
								driver.findElement(CoreUtilities.userDropDown).click();
								//this.reporter.SuccessReport("Click : " + "userDropDown", msgClickSuccess + "userDropDown");
								LOG.info("Click : " + "userDropDown" + msgClickSuccess + "userDropDown");
								driver.findElement(CoreUtilities.logout).click();
								//this.reporter.SuccessReport("Click : " + "logout", msgClickSuccess + "logout");
								LOG.info("Click : " + "logout" + msgClickSuccess + "logout");
							} catch (Exception e) {
								LOG.info("Unable to Logout");
								e.printStackTrace();
							}
						} else {
							try {
								driver.findElement(CoreUtilities.userDropDown).click();
								//this.reporter.SuccessReport("Click : " + "userDropDown", msgClickSuccess + "userDropDown");
								driver.findElement(CoreUtilities.logout).click();
								//this.reporter.SuccessReport("Click : " + "logout", msgClickSuccess + "logout");
							} catch (Exception e) {
								LOG.info("Unable to  Logout");
								e.printStackTrace();
							}
							/*click(driver,CoreUtilities.userDropDown, "User Dropdown");
							click(driver,CoreUtilities.logout, "Logout");*/
						}
					} else {
						boolean flag3 = isVisibleOnly(driver, CoreUtilities.userDropDown, "userDropDown");
						if (!flag3) {
							for (int i = 0; i < 10; i++) {
								LOG.info("Inside for loop  : " + i);

								driver.navigate().back();
								dynamicWaitByLocator(driver, CoreUtilities.NClogout, 3);
								boolean flag4 = isVisibleOnly(driver, CoreUtilities.login, "login");

								if (flag4) {
									break;
								}
							}
						}
					}
				}
			} else
				LOG.info(" PAGE NOT FOUND");
		}
	}
	
	/**
	 * isElementPresent
	 *
	 * @throws Throwable the throwable
	 */

	public boolean isElementPresent(org.openqa.selenium.WebDriver driver, By by, String locatorName) throws Throwable {
		boolean status = false;
		try {

			dynamicWait(by);
			driver.findElement(by);
			status = true;
		} catch (Exception e) {
			status = false;

			//throw new RuntimeException(e);
		}
		return status;
	}
	/* */

	/**
	 * click
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean click(org.openqa.selenium.WebDriver driver, By locator, String locatorName) throws Throwable {
		boolean status = false;
		//isElementPresent(locator, locatorName);
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : click  ::  Locator : " + locatorName);
			//WebDriverWait wait = new WebDriverWait(driver, 30);
			//internalServerErrorHandler();
			LOG.info("Waiting for element");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			// wait.until(ExpectedConditions.elementToBeClickable(locator));
			LOG.info("Clicked on the Locator");
			driver.findElement(locator).click();
			LOG.info("identified the element :: " + locator);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			status = true;
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
			e.printStackTrace();
			//	reporter.failureReport("Click : " + locatorName, msgClickFailure + locatorName, driver);
			throw new RuntimeException(e);
		} finally {
			if (!status) {
				if (reportIndicator) {
					LOG.info("Click : " + locatorName + msgClickFailure + locatorName);//
				}
			} else {
				LOG.info("Click : " + locatorName + msgClickSuccess + locatorName);//
			}
			reportIndicator = true;
		}
		return status;
	}

	/**
	 * isVisible
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean isVisibleOnly(org.openqa.selenium.WebDriver driver, By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			//added loggers
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name :: " + getCallerClassName() + " Method name :: " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);
			//value = driver.findElement(locator).isDisplayed();
			flag = driver.findElement(locator).isDisplayed();
			//value = true;
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * dynamicWaitByLocator
	 *
	 * @param locator of (By)
	 * @param time    of (int)
	 * @return void
	 * @throws InterruptedException the throwable
	 */
	public void dynamicWaitByLocator(org.openqa.selenium.WebDriver driver, By locator, int time) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
}


