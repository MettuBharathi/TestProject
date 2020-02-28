package com.insight.accelerators;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.github.javafaker.Faker;
import com.insight.report.ConfigFileReadWrite;
import com.insight.report.ReporterConstants;
import com.insight.utilities.Xls_Reader;

public class ActionEngine extends TestEngineWeb {
	//declarations
	public final static Logger LOG = Logger.getLogger(ActionEngine.class);
	private final String msgClickSuccess = "Successfully Clicked On ";
	private final static String msgClickFailure = "Unable To Click On ";
	private final String msgTypeSuccess = "Successfully Entered value ";
	private final String msgTypeFailure = "Unable To Type On ";
	private final String msgIsElementFoundSuccess = "Successfully Found Element ";
	private final String msgIsElementFoundFailure = "Unable To Found Element ";
	protected static boolean reportIndicator = true;
	public static Xls_Reader TestData = new Xls_Reader(System.getProperty("user.dir") + "/TestData/TestData.xlsx");
	protected String SMART_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "smart");
	protected String CANADA_URL =ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "canada");
	public static Xls_Reader TestDataInsight = new Xls_Reader(System.getProperty("user.dir") + "/TestData/TestData_Insight.xlsx");
	public static Xls_Reader TestData_Smart = new Xls_Reader(System.getProperty("user.dir") + "/TestData/TestData_Smart.xlsx");
	protected String EMW_BASE_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "emBaseUrl");
	protected String NC_BASE_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "ncBaseUrl");
	private static final long DEFAULT_TIMEOUT_SEC = 90;
	private static final int SLEEP_MILLI_SEC = 1000;
	protected String ADMIN_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "adminURL");
	protected String CALL_RECEIVING_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "callReceivingURL");
	protected String DISPATCH_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "dispatchURL");
	protected String RSO_WEB_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "RSOWebURL");
	protected String RSO_WEB_URL_MESTAG = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "RSOWebURLMestag");
	protected String RSP_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "RSP");
	protected String CARVING_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "Carving");
	protected String SERVICETRACKER_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "ServiceTracker");
	protected String SERVICETRACKERQUICKLAUNCH_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "ServiceTrackerQuickLaunch");
	protected String landingURL;
	//Action Engine Methods
	/**
	 * selectByIndex
	 *
	 * @param locator     of (By)
	 * @param index       of (int)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean selectByIndex(By locator, int index, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);
			Select s = new Select(driver.findElement(locator));
			s.selectByIndex(index);
			flag = true;
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			return true;
		} catch (Exception e) {
			LOG.info("++++++++++++++++++++++++++++Catch Block Start+++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("++++++++++++++++++++++++++++Catch Block End+++++++++++++++++++++++++++++++++++++++++++");
			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				if (reportIndicator) {
					reporter.failureReport("Select Value from the Dropdown :: " + locatorName,
							"Option at index :: " + index + " is Not Select from the DropDown :: " + locatorName,String.valueOf(index), driver);
				} else {
					reporter.SuccessReport("Select Value from the Dropdown :: " + locatorName,
							"Option at index :: " + index + "is Selected from the DropDown :: " + locatorName,String.valueOf(index));
				}
			}
			reportIndicator = true;
		}
	}

	/**
	 * assertTrue
	 *
	 * @param condition of (boolean)
	 * @param message   of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean assertTrue(boolean condition, String message) throws Throwable {
		if (!condition) {
			reporter.failureReport("<b>Expected :: " + message, message + " is :: false","", driver);
		} else {
			if (!message.equals("")) {
				reporter.SuccessReport("<b>Expected :: " + message, message + " is :: true","");
			}
		}
		return condition;
	}

	/**
	 * assertFalse
	 *
	 * @param condition of (boolean)
	 * @param message   of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean assertFalse(boolean condition, String message) throws Throwable {
			if (condition) {
				reporter.failureReport("<b>Expected :: " + message, message + " is :: true","", driver);
			} else {
				reporter.SuccessReport("<b>Expected :: " + message, message + " is :: false","");
			}
			return condition;
	}

	/**
	 * dynamicWaitByLocator
	 *
	 * @param locator of (By)
	 * @param time    of (int)
	 * @throws InterruptedException the throwable
	 */
	public void dynamicWaitByLocator(By locator, int time) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}
	}

	/**
	 * dynamicWaitByLocator
	 *
	 * @param locator of (By)
	 * @throws InterruptedException the throwable
	 */
	public void dynamicWaitByLocator(By locator) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT_SEC, SLEEP_MILLI_SEC);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}
	}

	/**
	 * VerifyJIRATicket
	 *
	 * @param flag       the flag
	 * @param JIRATicket the JIRATicket
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	protected boolean VerifyJIRATicket(boolean flag, String JIRATicket) throws Throwable {
		try {
			if (!flag) {
				reporter.failureReport(JIRATicket + " ", "Retested Successfully Completed ", "",driver);
				return false;
			} else {
				reporter.SuccessReport(JIRATicket + " ", "Retested Unsuccessfully Completed","");
			}
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}
		return flag;
	}

	/**
	 * assertElementPresent
	 *
	 * @param by          of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean assertElementPresent(By by, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			Assert.assertTrue(isElementPresent(by, locatorName, true));
			flag = true;
		} catch (Exception e) {
			LOG.info("++++++++++++++++++++++++++++Catch Block Start+++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);

			LOG.info("++++++++++++++++++++++++++++Catch Block End+++++++++++++++++++++++++++++++++++++++++++");
		} finally {
			if (!flag) {
				reporter.failureReport("AssertElementPresent :: ", locatorName + " is not present in the page :: ","", driver);
				//return false;
			} else {
				reporter.SuccessReport("AssertElementPresent :: ", locatorName + " is present in the page :: ","");
			}
		}
		return flag;
	}
	
	/**
	 * mouseHoverByJavaScript
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean mouseHoverByJavaScript(By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);
			WebElement mo = driver.findElement(locator);
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			((JavascriptExecutor) WebDriver).executeScript(javaScript, mo);
			flag = true;
			LOG.info("MoveOver action is done on  :: " + locatorName);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			return true;
		} catch (Exception e) {
			LOG.info("++++++++++++++++++++++++++++Catch Block Start+++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("++++++++++++++++++++++++++++Catch Block End+++++++++++++++++++++++++++++++++++++++++++");
			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				reporter.failureReport("MouseOver :: ", "MouseOver action is not perform on :: " + locatorName, "",driver);
			} else {
				reporter.SuccessReport("MouseOver :: ", "MouserOver Action is Done on :: " + locatorName,"");
			}
		}
	}

	/**
	 * waitForVisibilityOfElement
	 *
	 * @param by          of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean waitForVisibilityOfElement(By by, String locatorName,WebDriver... spDrivers) throws Throwable {
		boolean flag = false;
		EventFiringWebDriver driver1 = driver;
		if(spDrivers.length != 0){
			driver1=(EventFiringWebDriver)(spDrivers[0]);
		}
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
		LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);
		WebDriverWait wait = new WebDriverWait(driver1, 25);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			flag = true;
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			return true;
		} catch (Exception e) {
			LOG.info("++++++++++++++++++++++++++++Catch Block Start+++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());

			LOG.info("++++++++++++++++++++++++++++Catch Block End+++++++++++++++++++++++++++++++++++++++++++");
			return false;
		} finally {
			if (!flag) {
				reporter.failureReport("Visible of element is false :: ", "Element :: " + locatorName + " is not visible","", driver);
			} else {
				reporter.SuccessReport("Visible of element is true :: ", "Element :: " + locatorName + "  is visible","");
			}
		}
	}


	/**
	 * waitForInVisibilityOfElement
	 *
	 * @param by          of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean waitForInVisibilityOfElement(By by, String locatorName) throws Throwable {
		boolean flag = false;
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
		LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			flag = true;
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			return true;
		} catch (Exception e) {
			LOG.info("++++++++++++++++++++++++++++Catch Block Start+++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());

			LOG.info("++++++++++++++++++++++++++++Catch Block End+++++++++++++++++++++++++++++++++++++++++++");
			return false;
		} finally {
			if (!flag) {
				reporter.failureReport("InVisible of element is false :: ", "Element :: " + locatorName + " is visible","", driver);
			} else {
				reporter.SuccessReport("InVisible of element is true :: ", "Element :: " + locatorName + "  is not visible","");
			}
		}
	}

	/**
	 * clickUsingJavascriptExecutor
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean clickUsingJavascriptExecutor(By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);
			WebElement element = driver.findElement(locator);
			isElementPresent(locator, locatorName);
			((JavascriptExecutor) WebDriver).executeScript("arguments[0].click();", element);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			flag = true;
			LOG.info("clicked : " + locatorName);
		} catch (Exception e) {
			LOG.info("++++++++++++++++++++++++++++Catch Block Start+++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("++++++++++++++++++++++++++++Catch Block End+++++++++++++++++++++++++++++++++++++++++++");
			flag = false;
		} finally {
			if (!flag) {
				if (reportIndicator) {
					reporter.failureReport("Click : " + locatorName, msgClickFailure + locatorName,"", driver);
				}
			} else {
				reporter.SuccessReport("Click : " + locatorName, msgClickSuccess + locatorName,"");
			}
			reportIndicator = true;
		}
		return flag;
	}

	/**
	 * selectByValue
	 *
	 * @param locator     of (By)
	 * @param value       of (String)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean selectByValue(By locator, String value, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);
			Select s = new Select(driver.findElement(locator));
			s.selectByValue(value);
			flag = true;
			LOG.info("Successfully selected the value" + locatorName);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				reporter.failureReport("Select",
						"Option with value attribute : " + value + " is Not Select from the DropDown : " + locatorName,value, driver);
			} else {
				reporter.SuccessReport("Select",
						"Option with value attribute : " + value + " is  Selected from the DropDown : " + locatorName,value);
			}
		}
	}

	/**
	 * selectByVisibleText
	 *
	 * @param locator     of (By)
	 * @param visibleText of (String)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean selectByVisibleText(By locator, String visibleText, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByVisibleText(visibleText.trim());
			flag = true;
			return true;
		} catch (Exception e) {
			//return false;

			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				reporter.failureReport("Select", visibleText + " is Not Select from the DropDown" + locatorName,visibleText, driver);
			} else {
				reporter.SuccessReport("Select", visibleText + "  is Selected from the DropDown" + locatorName,visibleText);
			}
		}
	}

	/**
	 * isVisible
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean isVisible(By locator, String locatorName) throws Throwable {
		boolean flag = false;

		try {
			//added loggers
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name :: " + getCallerClassName() + " Method name :: " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);
			//value = driver.findElement(locator).isDisplayed();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			flag = driver.findElement(locator).isDisplayed();
			//value = true;
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
		} catch (Exception e) {
			flag = false;
		} finally {
			if (!flag) {
				reporter.failureReport("IsVisible : ", locatorName + " Element is Not Visible : "+flag, "",driver);
			} else {
				reporter.SuccessReport("IsVisible : ", locatorName + " Element is Visible : "+flag,"");
			}
		}
		return flag;
	}

	/**
	 * getElementsSize
	 *
	 * @param locator of (By)
	 * @return int
	 */
	public int getElementsSize(By locator) {
		int a = 0;
		try {
			List<WebElement> rows = driver.findElements(locator);
			a = rows.size();
		} catch (Exception e) {
			e.getMessage();
		}
		return a;
	}

	/**
	 * assertTextMatching
	 *
	 * @param by          of (By)
	 * @param text        of (String)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean assertTextMatching(By by, String text, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);
			String ActualText = getText(by, locatorName).trim();
			LOG.info("ActualText is : " + ActualText);

			if (ActualText.contains(text.trim())) {
				flag = true;
				LOG.info("String comparison with actual text :: " + "actual text is : " + ActualText + "And expected text is : " + text);
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				return true;
			} else {
				LOG.info("String comparison with actual text :: " + "actual text is : " + ActualText + "And expected text is : " + text);
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				reporter.failureReport("Verify : " + locatorName, text + " is not present in the element : ",text, driver);
				//return false;
			} else {
				reporter.SuccessReport("Verify : " + locatorName, text + " is  present in the element : " + locatorName,text);
			}
		}
	}

	/**
	 * assertTextMatchingWithAttribute
	 *
	 * @param by          of (By)
	 * @param text        of (String)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean assertTextMatchingWithAttribute(By by, String text, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			// added loggers
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);
			String ActualText = getAttributeByValue(by, text).trim();
			LOG.info("ActualText is" + ActualText);
			if (ActualText.contains(text.trim())) {
				flag = true;
				// added loggers
				LOG.info("String comparison with actual text :: " + "actual text is :" + ActualText + "And expected text is : " + text);
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				reporter.failureReport("Verify : " + locatorName, text + " is not present in the element : ", text,driver);
				//return false;
			} else {
				reporter.SuccessReport("Verify : " + locatorName, text + " is  present in the element : ",text);
			}
		}
	}

	/**
	 * assertTextStringMatching
	 *
	 * @param actText of (String)
	 * @param expText of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean assertTextStringMatching(String actText, String expText) throws Throwable {
		boolean flag = false;
		try {
			// added loggers
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			String ActualText = actText.trim();
			LOG.info("act - " + ActualText);
			LOG.info("exp - " + expText);
			if (ActualText.equalsIgnoreCase(expText.trim())) {
				LOG.info("in if loop");
				flag = true;
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				return true;
			} else {
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				reporter.failureReport("Verify : " + expText, actText + " is not present in the element : ", expText,driver);
				//return false;
			} else {
				reporter.SuccessReport("Verify : " + expText, actText + " is  present in the element : ",expText);
			}
		}
	}

	/**
	 * click
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public static boolean click(By locator, String locatorName, WebDriver... spDrivers) throws Throwable {
		boolean status = false;
		EventFiringWebDriver driver1 = driver;

		//isElementPresent(locator, locatorName);
		try {
			if(spDrivers.length != 0){
				driver1=(EventFiringWebDriver)(spDrivers[0]);
			}
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : click  ::  Locator : " + locatorName);
			WebDriverWait wait = new WebDriverWait(driver1, 30);
			//internalServerErrorHandler();
			LOG.info("Waiting for element");
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			LOG.info("Clicked on the Locator");
			driver1.findElement(locator).click();
			LOG.info("identified the element :: " + locator);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			status = true;
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
			reporter.failureReport("Click : " + locatorName, msgClickFailure + locatorName,"NA",driver1);
			throw e;
		} finally {
			if (!status) {
				if (reportIndicator) {
					reporter.failureReport("Click : " + locatorName, msgClickFailure + locatorName,"NA", driver1);
				}
			} else {
				/*
				 * reporter.SuccessReport("Click : " + locatorName, msgClickSuccess +
				 * locatorName,TestData);
				 */
			}
			reportIndicator = true;
		}
		return status;
	}

	/**
	 * getTimeStamp
	 *
	 * @return String
	 */
	public String getTimeStamp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy hh mm ss SSS");
		String formattedDate = sdf.format(date);
		suiteStartTime = formattedDate.replace(":", "_").replace(" ", "_");
		return suiteStartTime;
	}

	/**
	 * isElementPresent
	 *
	 * @param by          of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean isElementPresent(By by, String locatorName, boolean expected) throws Throwable {
		boolean status = expected;
		String msgIsElementFoundFailure = "Unable To Found Element ";
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			waitTime();
			driver.findElement(by);
			status = true;
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
		} finally {
			if (!status) {
				if (reportIndicator) {
					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					reporter.failureReport("isElementPresent : ", msgIsElementFoundFailure + locatorName, "NA",driver);
				}
			} else {
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				TestEngineWeb.reporter.SuccessReport("isElementPresent : " + locatorName, this.msgIsElementFoundSuccess + locatorName,"NA");
			}
			reportIndicator = true;
		}
		return status;
	}

	/**
	 * isElementPresent
	 *
	 * @param by          of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean isElementPresent(By by, String locatorName) throws Throwable {
		boolean status;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			dynamicWait(by);
			driver.findElement(by);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			status = true;
			reporter.SuccessReport("isElementPresent : " + locatorName, locatorName + " is visible","");
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
		}
		return status;
	}

	/**
	 * isElementPresent
	 *
	 * @param by          of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean isElementNotPresent(By by, String locatorName) throws Throwable {
		boolean status;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			// dynamicWait(by);
			String time = ReporterConstants.MIN_TIMEOUT;
			int timevalue = Integer.parseInt(time);
			WebDriverWait wait = new WebDriverWait(driver, timevalue);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			driver.findElement(by);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			status = false;
			reporter.SuccessReport("isElementNotPresent : " + locatorName, locatorName + " is not visible","");
		} catch (Exception e) {
			status = true;
			LOG.info(e.getMessage());
		}
		return status;
	}

	/**
	 * scroll
	 *
	 * @param by          of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean scroll(By by, String locatorName) throws Throwable {
		boolean status = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName());
			WebElement element = TestEngineWeb.driver.findElement(by);
			Actions actions = new Actions(TestEngineWeb.driver);
			actions.moveToElement(element);
			actions.build().perform();
			LOG.info("Scroll is performed : " + locatorName);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			status = true;
		} catch (Exception e) {
			e.getMessage();
		}
		return status;
	}

	/**
	 * JSScroll
	 *
	 * @param by          of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean JSScroll(By by, String locatorName) throws Throwable {
		boolean status = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName());
			WebElement element = driver.findElement(by);
			((JavascriptExecutor) WebDriver).executeScript("arguments[0].scrollIntoView(true);", element);
			LOG.info("Scroll is performed : " + locatorName);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			status = true;
		} catch (Exception e) {
			e.getMessage();
		}
		return status;
	}

	/**
	 * verifyElementPresent
	 *
	 * @param by          of (By)
	 * @param locatorName of (String)
	 * @param expected    of (boolean)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean verifyElementPresent(By by, String locatorName, boolean expected) throws Throwable {
		boolean status = expected;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName());
			if (TestEngineWeb.driver.findElement(by).isDisplayed()) {
				TestEngineWeb.reporter.SuccessReport("VerifyElementPresent : " + locatorName,
						this.msgIsElementFoundSuccess + locatorName,"");
				LOG.info("Element is available :: " + locatorName);
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				status = true;
			} else {
				status = false;
			}
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
		} finally {
			if (!status) {
				if (reportIndicator) {
					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					reporter.failureReport("verifyElementPresent : ", "Element is not present: " + locatorName + " : false", "",driver);
				}
			} else {
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				reporter.SuccessReport("verifyElementPresent : ", "Element is present: " + locatorName + " : true","");
			}
			reportIndicator = true;
		}
		return status;
	}

	private void waitTime() throws Throwable {
		String time = ReporterConstants.Timeout;
		long timeValue = Long.parseLong(time);
		LOG.info("Time out value is : " + timeValue);
	}


	/**
	 * type
	 *
	 * @param locator     of (By)
	 * @param testData    of (String)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean type(By locator,String testData, String locatorName,WebDriver... spDrivers) throws Throwable {
		boolean status = false;
		EventFiringWebDriver driver1 = driver;

		try {
			if(spDrivers.length != 0) {
				driver1 = (EventFiringWebDriver) (spDrivers[0]);
			}
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : Type  ::  Locator : " + locatorName + " :: Data :" + testData);
			WebDriverWait wait = new WebDriverWait(driver1, 30);
			LOG.info("Waiting for element :");
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
//			wait.until(ExpectedConditions.elementToBeClickable(locator));
			/*driver.findElement(locator).click();
			LOG.info("Clicked on the Locator : ");*/
			driver1.findElement(locator).clear();
			LOG.info("Cleared the existing Locator data : ");
			//WebElement webElement = (WebElement)driver.findElement(locator);
			driver1.findElement(locator).sendKeys(testData);
			LOG.info("Typed the Locator data :: " + testData);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			status = true;
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
		} finally {
			if (!status) {
				if (reportIndicator) {
					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					reporter.failureReport("Enter text in :: " + locatorName, msgTypeFailure,testData,driver1);
				}
			} else {
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				reporter.SuccessReport("Enter text in :: " + locatorName, msgTypeSuccess,testData);
			}
			reportIndicator = true;
		}
		return status;
	}

	/**
	 * typeUntil
	 *
	 * @param locator     of (By)
	 * @param testData    of (String)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean typeUntil(By locator, String testData, String locatorName) throws Throwable {
		boolean status = false;
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : Type  ::  Locator : " + locatorName + " :: Data :" + testData);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			LOG.info("Waiting for element :");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			driver.findElement(locator).click();
			LOG.info("Clicked on the Locator : ");
			int counter = 0;
			do {
				counter = counter + 1;
				driver.findElement(locator).clear();
				LOG.info("Cleared the existing Locator data : ");
				driver.findElement(locator).sendKeys(testData);
				if (counter >= 4) {
					break;
				}
				if (driver.findElement(locator).getAttribute("value").equalsIgnoreCase(testData)) {
					flag = true;
					break;
				}
			} while (!flag);
			if (counter >= 4) {
				status = false;
			} else {
				LOG.info("Typed the Locator data :: " + testData);
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				status = true;
			}
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
		} finally {
			if (!status) {
				if (reportIndicator) {
					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					reporter.failureReport("Enter text in :: " + locatorName, msgTypeFailure + "'"+testData+"'",testData, driver);
				}
			} else {
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				reporter.SuccessReport("Enter text in :: " + locatorName, msgTypeSuccess ,testData);
			}
			reportIndicator = true;
		}
		return status;
	}

	/**
	 * type text with out clear
	 *
	 * @param locator     of (By)
	 * @param testData    of (String)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean typeText(By locator, String testData, String locatorName) throws Throwable {
		boolean status;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : Type  ::  Locator : " + locatorName + " :: Data :" + testData);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			LOG.info("Waiting for element :");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			driver.findElement(locator).sendKeys(testData);
			LOG.info("Typed the Locator data :: " + testData);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

			reporter.SuccessReport("Enter text in :: " + locatorName, msgTypeSuccess +"'"+ testData+"'",testData);
			status = true;
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
			reporter.failureReport("Enter text in :: " + locatorName, msgTypeFailure + "'"+testData+"'", testData,driver);
		}
		return status;
	}

	/**
	 * typeUsingJavaScriptExecutor
	 *
	 * @param locator     of (By)
	 * @param testData    of (String)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean typeUsingJavaScriptExecutor(By locator, String testData, String locatorName) throws Throwable {
		boolean status;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + locatorName);
			waitTime();
			WebElement searchbox = driver.findElement(locator);
			JavascriptExecutor myExecutor = ((JavascriptExecutor) WebDriver);
			myExecutor.executeScript("arguments[0].value=' " + testData + "'; ", searchbox);
			reporter.SuccessReport("Enter text in :: " + locatorName, msgTypeSuccess + locatorName,testData);
			LOG.info("Clicked on  : " + locatorName);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			status = true;
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
			reporter.failureReport("Enter text in :: " + locatorName, msgTypeFailure + locatorName, testData,driver);
		}
		return status;
	}

	/**
	 * Moves the mouse to the middle of the element. The element is scrolled
	 * into view and its location is calculated using getBoundingClientRect.
	 *
	 * @param locator : Action to be performed on element (Get it from Object
	 *                repository)
	 */
	private boolean waitForTitlePresent(By locator) throws Throwable {
		boolean flag = false;
		boolean bValue = false;
		try {
			for (int i = 0; i < 200; i++) {
				if (driver.findElements(locator).size() > 0) {
					flag = true;
					bValue = true;
					break;
				} else {
					driver.wait(50);
				}
			}
		} catch (Exception e) {

			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				reporter.failureReport("WaitForTitlePresent :: ", "Title is wrong : ", "",driver);
			} else {
				reporter.SuccessReport("WaitForTitlePresent :: ", "Launched successfully expected Title : ","");
			}
		}
		return bValue;
	}

	/**
	 * getTitle
	 *
	 * @return String
	 * @throws Throwable the throwable
	 */
	public String getTitle() throws Throwable {
		String text = driver.getTitle();
		{
			reporter.SuccessReport("Title :: ", "Title of the page is :: " + text,"");
		}
		return text;
	}

	/**
	 * assertText
	 *
	 * @param by   of (By)
	 * @param text of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean assertText(By by, String text) throws Throwable {
		boolean flag = false;
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
		try {
			Assert.assertEquals(getText(by, text).trim(), text.trim());
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				reporter.failureReport("AssertText :: ", text + " is not present in the element : ","", driver);
			} else {
				reporter.SuccessReport("AssertText :: ", text + " is  present in the element : ","");
			}
		}
	}

	/**
	 * assertTitle
	 *
	 * @param title of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean assertTitle(String title) throws Throwable {
		boolean flag = false;
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
		try {
			By windowTitle = By.xpath("//title[contains(text(),'" + title + "')]");
			if (waitForTitlePresent(windowTitle)) {
				Assert.assertEquals(getTitle(), title);
				flag = true;
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				return true;
			} else {
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				reporter.failureReport("AsserTitle :: ", "Page title is not matched with : " + title,title, driver);
			} else {
				reporter.SuccessReport("AsserTitle :: ", " Page title is verified with : " + title,title);
			}
		}
	}

	/**
	 * getText
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return String
	 * @throws Throwable the throwable
	 */
	public String getText(By locator, String locatorName) throws Throwable {
		String text = "";
		boolean flag = false;
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ ");
		LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
		try {
			waitTime();
			boolean value = isVisibleOnly(locator, locatorName);
			if (value) {
				text = driver.findElement(locator).getText();
				LOG.info("Locator is Visible and text is retrieved :: " + text);
				flag = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				reporter.warningReport("GetText :: ", "Unable to get Text from :: " + locatorName);
				LOG.info("GetText :: Unable to get Text from :: " + locatorName);
			} else {
				reporter.SuccessReport("GetText :: " + locatorName, "" + locatorName + " is :" + text,"");
				LOG.info("Locator is Visible and text is retrieved :: " + text);
			}
		}
		return text;
	}
	
	/**
	 * getAttributeByValue
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return String
	 * @throws Throwable the throwable
	 */
	/*protected String getAttributeByValue(By locator, String locatorName) throws Throwable {
		String text = "";
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName());
			waitTime();
			if (isElementPresent(locator, locatorName, true)) {
				text = driver.findElement(locator).getAttribute("value");
				LOG.info("Locator is Visible and attribute value is retrieved :: " + text);
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				flag = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				reporter.warningReport("GetAttribute :: ", "Unable to get Attribute value from :: " + locatorName);
				LOG.info("GetAttribute :: Unable to get Attribute value from :: " + locatorName);
			} else {
				reporter.SuccessReport("GetAttribute :: ", "" + locatorName + " is" + text);
				LOG.info("Locator is Visible and attribute value is retrieved :: " + text);
			}
		}
		return text;
	}
*/
	protected String getAttributeByValue(By locator, String locatorName) throws Throwable {
		String text = null;
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName());
			waitTime();
			boolean waitForValue=isVisibleOnly(locator, locatorName);
			if(waitForValue){
				text = driver.findElement(locator).getAttribute("value");
         /*if(text.equals("")){
             String value=null;
               }*/
				if(text.equals("")){
					LOG.info("Locator is Visible and attribute value is retrieved :: and it is Null");
					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					flag = false;
				}else {
					LOG.info("Locator is Visible and attribute value is retrieved :: " + text);
					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					flag = true;
				}
			}
      /*if (isElementPresent(locator, locatorName, true)) {
         text = driver.findElement(locator).getAttribute("value");
         LOG.info("Locator is Visible and attribute value is retrieved :: " + text);
         LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
         flag = true;
      }*/
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				//reporter.warningReport("GetAttribute :: ", "Unable to get Attribute value from :: " + locatorName+" is null ");
				reporter.SuccessReport("GetAttribute :: ", "Locator is Visible and attribute value of:: " + locatorName+" is Null","");
				LOG.info("GetAttribute :: Unable to get Attribute value from :: " + locatorName);
			} else {
				reporter.SuccessReport("GetAttribute :: ", "" + locatorName + " is " + text,"");
				LOG.info("Locator is Visible and attribute value is retrieved :: " + text);
			}
		}
		return text;
	}

	/**
	 * getAttributeByValue
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return String
	 * @throws Throwable the throwable
	 */
	protected String getAttributeByClass(By locator, String locatorName) throws Throwable {
		String text = "";
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName());
			waitTime();
			if (isElementPresent(locator, locatorName, true)) {
				text = driver.findElement(locator).getAttribute("class");
				LOG.info("Locator is Visible and attribute value is retrieved :: " + text);
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				flag = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				reporter.warningReport("GetAttribute :: ", "Unable to get Attribute value from :: " + locatorName);
				LOG.info("GetAttribute :: Unable to get Attribute value from :: " + locatorName);
			} else {
				reporter.SuccessReport("GetAttribute :: ", "" + locatorName + " is" + text,"");
				LOG.info("Locator is Visible and attribute value is retrieved :: " + text);
			}
		}
		return text;
	}

	/**
	 * Moves the mouse to the middle of the element. The element is scrolled
	 * into view and its location is calculated using getBoundingClientRect.
	 *
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * @param locatorName : Meaningful name to the element (Ex:link,menus etc..)
	 */
	public boolean mouseHover(By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Mouse over start :: " + locatorName);
			WebElement mo = TestEngineWeb.driver.findElement(locator);
			new Actions(TestEngineWeb.driver).moveToElement(mo).build().perform();


			flag = true;
			LOG.info("Mouse over End :: " + locatorName);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++");
			return true;
		} catch (Exception e) {
			//return false;
			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				TestEngineWeb.reporter.failureReport("MouseOver :: ", "MouseOver action is not perform on ::" + locatorName,"",
						TestEngineWeb.driver);
			} else {
				TestEngineWeb.reporter.SuccessReport("MouseOver :: ", "MouserOver Action is Done on  :: " + locatorName,"");
			}
		}
	}

	/**
	 * JSClick
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean JSClick(By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			//added the loggers for click method

			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);
			LOG.info("Method : click  ::  Locator : " + locatorName);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			LOG.info("Waiting for element");
			//internalServerErrorHandler();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			WebElement element = TestEngineWeb.driver.findElement(locator);
			((JavascriptExecutor) WebDriver).executeScript("arguments[0].click();", element);
			element.click();
			flag = true;
		} catch (Exception e) {
			flag = false;
		} finally {
			if (!flag) {
				LOG.info("Inside Finally block");
				TestEngineWeb.reporter.failureReport("Click : " + locatorName, "Click is not performed on : " + locatorName, "",driver);
			} else {
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				TestEngineWeb.reporter.SuccessReport("Click : " + locatorName, "Successfully click on  : " + locatorName,"");
			}
		}
		return flag;
	}

	/**
	 * JSClickUntil
	 *
	 * @param locator     of (By)
	 * @param waitLocator of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean JSClickUntil(By locator, By waitLocator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			WebElement element = TestEngineWeb.driver.findElement(locator);
			int icounter = 0;
			//((JavascriptExecutor) WebDriver).executeScript("arguments[0].click();", element);
			do {
				icounter = icounter + 1;
				try {
					/* wait = new WebDriverWait(driver, 10*icounter);
					 wait.until(ExpectedConditions.visibilityOfElementLocated(waitLocator));*/
					//wait.until(ExpectedConditions.visibilityOfElementLocated(waitLocator));
					if (isVisibleOnly(waitLocator, "Wait for Element : " + locatorName)) {
						flag = true;
						break;
					} else {
					}
					if (icounter >= 3) {
						flag = false;
						break;
					}
					if (isVisibleOnly(locator, "Wait for Element : " + locatorName)) {
						((JavascriptExecutor) WebDriver).executeScript("arguments[0].click();", element);
					}
				} catch (Exception e) {
					LOG.info("Retrying for the object :: " + waitLocator
							+ " :: Iteration : " + icounter);
				}
			} while (icounter <= 3);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				TestEngineWeb.reporter.failureReport("Click : ",
						"Click action is not perform on : " + locatorName,"", driver);
				//return flag;
			} else {
				TestEngineWeb.reporter.SuccessReport("Click : ", "Clicked : " + locatorName,"");
				//return flag;
			}
		}
		return flag;
	}

	/**
	 * clickUntil
	 *
	 * @param locator     of (By)
	 * @param waitLocator of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean clickUntil(By locator, By waitLocator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method :" + getCallerMethodName() + "  ::  Locator : " + locatorName);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			LOG.info("Waiting for element");
			//internalServerErrorHandler();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			LOG.info("Clicked on the Locator");
			int icounter = 0;
			//driver.findElement(locator).click();
			do {
				icounter = icounter + 1;
				try {
					if (isVisibleOnly(waitLocator, "Wait for Element : " + locatorName)) {
						flag = true;
						break;
					} else {
					}
					if (icounter >= 3) {
						flag = false;
						break;
					}
					driver.findElement(locator).click();
				} catch (Exception e) {
					LOG.info("Retrying for the object :: " + waitLocator
							+ " :: Iteration : " + icounter);
				}
			} while (icounter <= 3);

			LOG.info("identified the element :: " + locator);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			reporter.SuccessReport("Click : " + locatorName, msgClickSuccess + locatorName,"");
		} catch (Exception e) {
			flag = false;
			LOG.info(e.getMessage());
			reporter.failureReport("Click : " + locatorName, msgClickFailure + locatorName, "",driver);
		}
		return flag;
	}

	/**
	 * methodName: clickUntilByProperty
	 * description: Clicking web element until property value exists
	 * date: 06/21/2017
	 * param: click locator, propertyName, propertyValue for comparision,locatorName for reporting
	 * return: void
	 * author: GallopAuthor004
	 * Updated on:06/21/2017
	 * Updated By:GallopAuthor004
	 * throws: Throwable
	 */
	public boolean clickUntilByProperty(By locator, String propertyName, String propertyValue, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method :" + getCallerMethodName() + "  ::  Locator : " + locatorName);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			LOG.info("Waiting for element");
			//internalServerErrorHandler();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			LOG.info("Clicked on the Locator");

			int icounter = 0;
			//driver.findElement(locator).click();
			do {
				icounter = icounter + 1;
				try {
					//verifying propertyName and propertyValue Exists
					if (driver.findElement(locator).getAttribute(propertyName).contains(propertyValue)) {
						flag = true;
						break;
					} else {
					}
					if (icounter >= 3) {
						flag = false;
						break;
					}
					//Trying best and worst case scenario
					switch (icounter) {
						case 1:
							driver.findElement(locator).click();
							LOG.info("By Native Click");
							break;

						case 2:
							WebElement element = TestEngineWeb.driver.findElement(locator);
							((JavascriptExecutor) WebDriver).executeScript("arguments[0].click();", element);
							LOG.info("By JSClick");
							break;
						case 3:
							icounter = 2;
							WebElement mo = TestEngineWeb.driver.findElement(locator);
							new Actions(TestEngineWeb.driver).click(mo).build().perform();
							LOG.info("By MouseClick");
							break;

					}
				} catch (Exception e) {
				}
			} while (icounter <= 3);

			LOG.info("identified the element :: " + locator);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			reporter.SuccessReport("clickUntilByProperty : " + locatorName, msgClickSuccess + locatorName,"");
		} catch (Exception e) {
			flag = false;
			LOG.info(e.getMessage());
			reporter.failureReport("clickUntilByProperty : " + locatorName, msgClickFailure + locatorName, "",driver);
		}
		return flag;
	}


	/**
	 * jsMouseHover
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean jsMouseHover(By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method :" + getCallerMethodName() + "  ::  Locator : " + locatorName);
			WebElement HoverElement = TestEngineWeb.driver.findElement(locator);
			String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			((JavascriptExecutor) this.WebDriver).executeScript(mouseOverScript, HoverElement);
			LOG.info("JSmousehover is performed  on :: " + locatorName);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			if (!flag) {
				TestEngineWeb.reporter.failureReport("MouseOver : ", "MouseOver action is not perform on : " + locatorName, "",driver);
			} else {
				TestEngineWeb.reporter.SuccessReport("MouseOver : ", "MouserOver Action is Done on" + locatorName,"");
			}
		}
		return flag;
	}

	/**
	 * getWebElementList
	 *
	 * @param by          of (By)
	 * @param locatorName of (String)
	 * @return List<WebElement>
	 * @throws Throwable the throwable
	 */
	public List<WebElement> getWebElementList(By by, String locatorName) throws Throwable {
		List<WebElement> elements = null;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method :" + getCallerMethodName() + "  ::  Locator : " + locatorName);
			WebDriverWait wait = new WebDriverWait(TestEngineWeb.driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			elements = driver.findElements(by);
			LOG.info("Size of List ::" + elements.size());
			for (WebElement element : elements) {
				LOG.info("List value are :: " + element.getText());
			}
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (Exception e) {
			e.getMessage();
		}
		return elements;
	}

	/**
	 * elementVisibleTime
	 *
	 * @param locator of (By)
	 * @throws Throwable the throwable
	 */
	public void elementVisibleTime(By locator) throws Throwable {
		float timeTaken;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			long start = System.currentTimeMillis();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			long stop = System.currentTimeMillis();
			timeTaken = (stop - start) / 1000;
			LOG.info("Took : " + timeTaken + " secs to display the results : ");
			reporter.SuccessReport("Total time taken for element visible :: ", "Time taken load the element :: " + timeTaken + " seconds","");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * Moves the mouse to the middle of the element. The element is scrolled
	 * into view and its location is calculated using getBoundingClientRect.
	 *
	 * @param destinationLocator : Action to be performed on element (Get it from Object
	 *                           repository)
	 * @param locatorName        : Meaningful name to the element (Ex:link,menus etc..)
	 */
	protected boolean dragAndDrop(By souceLocator, By destinationLocator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);
			Actions builder = new Actions(TestEngineWeb.driver);
			WebElement souceElement = TestEngineWeb.driver.findElement(souceLocator);
			WebElement destinationElement = TestEngineWeb.driver.findElement(destinationLocator);
			/*Action dragAndDrop = builder.clickAndHold(souceElement).moveToElement(destinationElement)
					.release(destinationElement).build();
			dragAndDrop.perform();*/
			builder.dragAndDrop(souceElement, destinationElement).build().perform();
			flag = true;
			LOG.info("drag and drop performed ");
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				TestEngineWeb.reporter.failureReport("DragDrop : ", "Drag and Drop action is not performed on : " + locatorName,"",
						TestEngineWeb.driver);
			} else {
				TestEngineWeb.reporter.SuccessReport("DragDrop : ", "Drag and Drop Action is Done on : " + locatorName,"");
			}
		}
	}

	/**
	 * navigateTo
	 *
	 * @param Url of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean navigateTo(String Url) throws Throwable {
		boolean flag = false;
		try {
			waitTime();
			WebDriver.navigate().to(Url);
			LOG.info("Navigated URL is : " + Url);
			flag = true;
		} catch (Exception e) {
			flag = false;
			LOG.info(e.getMessage());
		} finally {
			if (!flag) {
				reporter.failureReport("Unable to Open : ", Url, Url,driver);
			} else {
				reporter.SuccessReport("Successfully Opened : ", Url,Url);
			}
		}
		return flag;
	}

	/**
	 * generateRandomNumber
	 *description :: This is used to generate random number with 8 digit as current Year(YY)and system time
	 * @return int
	 * @throws Throwable the throwable
	 */
	public long generateRandomNumber() throws Throwable {
		/*Random generator = new Random();
		return generator.nextInt(9999) + 10000;*/
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyhhmmss");
		String currentdate = sdf.format(date);
		long ran = Long.parseLong(currentdate);
		return ran;

	}

	/**
	 * generateRandomNumber
	 *description :: This is used to generate random number with 8 digit as current Year(YY)and system time
	 * @return int
	 * @throws Throwable the throwable
	 */
	public String generateRandomNumberBasedOnLength(int length) throws Throwable {
		/*Random generator = new Random();
		return generator.nextInt(9999) + 10000;*/
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmssSdSMSs");
		String currentdate = sdf.format(date);
		return new StringBuffer().append(currentdate).reverse().substring(0,length);
	}

	/**
	  * param :: Hashtable with string inputs
	  * return ::String
	  * throws :: throwable
	  * methodName :: generateRandomNumber
	  * description :: To select ---
	  * date :: 05-Dec-2017
	  * author ::Parameswar Yenduri
	  */
	public static String generateRandomNumber(int length) {
		String randomNumber = "1";
		int retryCount = 1;
		while (retryCount > 0) {
			String number = Double.toString(Math.random());
			number = number.replace(".", "");
			if (number.length() > length) {
				randomNumber = number.substring(0, length);
			} else {
				int remainingLength = length - number.length() + 1;
				randomNumber = generateRandomNumber(remainingLength);
			}
			if (randomNumber.length() < length) {
				retryCount++;
			} else {
				retryCount = 0;
			}
		}
		return randomNumber;
	}

	/**
	 * rightClick
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean rightClick(By locator, String locatorName) throws Throwable {
		boolean status;
		String msgRightClickSuccess = "Successfully Mouse Right Clicked On ";
		String msgRightClickFailure = "Unable To Right Click On ";
		try {
			//added loggers
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			Actions action = new Actions(driver);
			action.contextClick(driver.findElement(locator)).build().perform();

		//Commenting the below line of click as its not allowing to right click. This will be issue for all so commenting this.
			//driver.findElement(locator).click();
			reporter.SuccessReport("Click : " + locatorName, msgRightClickSuccess + locatorName,"");
			LOG.info("Right click performed  on :: " + locatorName);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			status = true;
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
			reporter.failureReport("Click : " + locatorName, msgRightClickFailure + locatorName,"", driver);
		}
		return status;
	}

	/**
	 * dynamicWait
	 *
	 * @param locator of (By)
	 * @throws Throwable the throwable
	 */
	public void dynamicWait(By locator) throws Throwable {
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locator);
			String time = ReporterConstants.DYNAMIC_TIMEOUT;
			int timevalue = Integer.parseInt(time);
			WebDriverWait wait = new WebDriverWait(driver, timevalue);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			LOG.info(locator + ":: displayed succussfully");
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (Exception e) {
			LOG.info(e.getMessage());
			//reporter.failureReport("Unable to find Element :: " + locator, msgIsElementFoundFailure + locator, driver);
			//throw new RuntimeException(e);

		}
	}

	/**
	 * getCallerClassName
	 *
	 * @return String
	 */
	protected static String getCallerClassName() {
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		return stElements[3].getClassName();
	}

	/**
	 * getCallerMethodName
	 *
	 * @return String
	 */
	protected static String getCallerMethodName() {
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		return stElements[3].getMethodName();
	}

	/**
	 * Double click the mouse to the middle of the element. The element is scrolled
	 * into view and its location is calculated using getBoundingClientRect.
	 *
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * @param locatorName : Meaningful name to the element (Ex:link,menus etc..)
	 */
	public boolean mouseDoubleClick(By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Mouse Double Click start :: " + locatorName);
			WebElement mo = TestEngineWeb.driver.findElement(locator);
			new Actions(TestEngineWeb.driver).moveToElement(mo).doubleClick(mo).build().perform();
			flag = true;
			LOG.info("Mouse Double Click :: " + locatorName);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++");
			return true;
		} catch (Exception e) {
			//return false;
			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				TestEngineWeb.reporter.failureReport("double Click :: ", "double Click action is not perform on ::" + locatorName,"",
						TestEngineWeb.driver);
			} else {
				TestEngineWeb.reporter.SuccessReport("double Click :: ", "double Click Action is Done on  :: " + locatorName,"");
			}
		}
	}

	/**
	 * click the mouse to the middle of the element. The element is scrolled
	 * into view and its location is calculated using getBoundingClientRect.
	 *
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * @param locatorName : Meaningful name to the element (Ex:link,menus etc..)
	 */
	public boolean mouseClick(By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Mouse Double Click start :: " + locatorName);
			WebElement mo = TestEngineWeb.driver.findElement(locator);
			new Actions(TestEngineWeb.driver).click(mo).build().perform();
			flag = true;
			LOG.info("Mouse Double Click :: " + locatorName);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++");
			return true;
		} catch (Exception e) {
			//return false;
			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				TestEngineWeb.reporter.failureReport("Click :: ", "Click action is not perform on ::" + locatorName,"",
						TestEngineWeb.driver);
			} else {
				TestEngineWeb.reporter.SuccessReport(" Click :: ", " Click Action is Done on  :: " + locatorName,"");
			}
		}
	}

	/**
	 * getYear, Function to get required year e.g: 0-Current year, 1-Next year,
	 *
	 * @param number of (int) Number to get year (e.g: -1,0,1 etc)
	 * @return int
	 * @throws Throwable the throwable
	 */
	protected int getYear(int number) throws Throwable {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR) + number;
		LOG.info("Year is : " + year);
		return year;
	}

	/**
	 * dateFormatVerification, Function to verify date format by giving actual date
	 *
	 * @param actualDate     of (String) actual date e.g: 21-11-2015
	 * @param formatToVerify of (String) format type e.g: dd-MM-yyyy
	 * @return boolean
	 */
	protected boolean dateFormatVerification(String actualDate, String formatToVerify) {
		boolean flag = false;
		if (actualDate.toLowerCase().contains("am")) {
			flag = formatVerify(actualDate, formatToVerify);
		} else if (actualDate.toLowerCase().contains("pm")) {
			flag = formatVerify(actualDate, formatToVerify);
		} else if (!actualDate.toLowerCase().contains("am") || !actualDate.toLowerCase().contains("pm")) {
			flag = formatVerify(actualDate, formatToVerify);
		}
		return flag;
	}

	/**
	 * formatVerify, Reusable Function to verify date format by giving actual date
	 *
	 * @param actualDate     of (String)e.g: 21-11-2015
	 * @param formatToVerify of (String) type e.g: dd-MM-yyyy
	 * @return : boolean
	 */
	public boolean formatVerify(String actualDate, String formatToVerify) {
		boolean flag;
		try {
			SimpleDateFormat sdf;
			sdf = new SimpleDateFormat(formatToVerify);
			Date date = sdf.parse(actualDate);
			String formattedDate = sdf.format(date);
			flag = actualDate.equals(formattedDate);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	/**
	 * replaceAll, Function to replace the regular expression values with client required values
	 *
	 * @param text        of (String)
	 * @param pattern     of (String), regular expression of actual value
	 * @param replaceWith of (String), value to replace the actual
	 * @return : String
	 */
	public String replaceAll(String text, String pattern, String replaceWith) {
		String flag = null;
		try {
			flag = text.replaceAll(pattern, replaceWith);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	/**
	 * subString, Function to get sub string of given actual string text
	 *
	 * @param text       of (String), Actual text
	 * @param startIndex of (int), Start index of sub string
	 * @param endIndex   of (int), end index of sub string
	 * @return : String
	 */
	protected String subString(String text, int startIndex, int endIndex) {
		String flag = null;
		try {
			flag = text.substring(startIndex, endIndex);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	/**
	 * getCssValue, Function to get the value of a given CSS property (e.g. width)
	 *
	 * @param locator  of (By)
	 * @param cssValue of (String), CSS property
	 * @return : String
	 */
	public String getCssValue(By locator, String cssValue) {
		String result = "";
		try {
			result = TestEngineWeb.driver.findElement(locator).getCssValue(cssValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * getBackGroundColor, Function to get the background color of a given web element (e.g. background-color)
	 *
	 * @param locator  of (By)
	 * @param cssValue of (String), CSS property (e.g. background-color)
	 * @return : String
	 */
	public String getBackGroundColor(By locator, String cssValue) {
		String hexColor = "";
		try {
			String bColor = TestEngineWeb.driver.findElement(locator).getCssValue(cssValue);
			hexColor = Color.fromString(bColor).asHex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hexColor;
	}

	/**
	 * switchToFrame, Function to switch to frame
	 *
	 * @param locator of (By)
	 */
	protected void switchToFrame(By locator) {
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
		WebDriverWait wait = new WebDriverWait(driver, 30);
		LOG.info("Waiting for element");
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		LOG.info("Locator is Visible :: " + locator);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		driver.switchTo().frame(driver.findElement(locator));
	}

	/**
	 * getCurrentDateTime, Function to get current time in client required format
	 *
	 * @param dateTimeFormat of (String), format to get date and time (e.g: h:mm)
	 * @return : String
	 */
	public String getCurrentDateTime(String dateTimeFormat) throws Throwable {
		DateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * getFutureDateTime, Function to get future or past date in client required format
	 *
	 * @param dateTimeFormat of (String), format to get date and time (e.g: MM/dd/yyyy)
	 * @param days           of (int), number to get date E.g. 1:Tomorrow date, -1: Yesterday date
	 * @return : String
	 */
	public String getFutureDateTime(String dateTimeFormat, int days) throws Throwable {
		SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, days);
		Date tomorrow = calendar.getTime();
		return sdf.format(tomorrow);
	}

	/**
	 * getCountryDateTime, Function to get future or past date in client required format
	 *
	 * @param dateTimeFormat of (String), format to get date and time (e.g: MM/dd/yyyy)
	 * @param days           of (int), number to get date E.g. 1:Tomorrow date, -1: Yesterday date
	 * @param timeZone       of (String), time format to get date E.g. :America/New_York
	 * @return : String
	 */
	public String getCountryDateTime(String dateTimeFormat, int days, String timeZone) throws Throwable {
		Calendar calNewYork = Calendar.getInstance();
		calNewYork.add(Calendar.DAY_OF_YEAR, 0);
		Date date = calNewYork.getTime();
		DateFormat formatter = new SimpleDateFormat(dateTimeFormat);
		formatter.setTimeZone(TimeZone.getTimeZone(timeZone));
		return formatter.format(date);
	}

	/**
	 * assertTextStringContains, Assert text string matching.
	 *
	 * @param actText of (String)
	 * @param expText of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean assertTextStringContains(String actText, String expText) throws Throwable {
		boolean flag = false;
		try {
			// added loggers
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			String ActualText = actText.trim();
			LOG.info("act - " + ActualText);
			LOG.info("exp - " + expText);
			if (ActualText.contains(expText.trim())) {
				LOG.info("in if loop");
				flag = true;
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				return true;
			} else {
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				reporter.failureReport("Verify : " + expText, actText + " is not present in the element : ", expText,driver);
			} else {
				reporter.SuccessReport("Verify : " + expText, actText + " is  present in the element : ",expText);
			}
		}
	}

	/**
	 * deleteDirectory, Delete directory from local machine
	 *
	 * @param directoryPath of (String),  path for the directory to delete
	 */
	public void deleteDirectory(String directoryPath) throws IOException {
		FileUtils.deleteDirectory(new File(directoryPath));
	}

	/**
	 * getRandomString, Get random String
	 *
	 * @param noOfCharacters of (int), Number of characters to get randomly
	 * @return String
	 */
	public String getRandomString(int noOfCharacters) throws IOException {
		return RandomStringUtils.randomAlphabetic(noOfCharacters);
	}

	/**
	 * getRandomNumeric, Get random Numeric
	 *
	 * @param noOfCharacters of (int),  Number of characters to get randomly
	 * @return String
	 */
	protected String getRandomNumeric(int noOfCharacters) throws IOException {
		return RandomStringUtils.randomNumeric(noOfCharacters);
	}

	/**
	 * getAttributeValue, Function to get the value of a given attribute (e.g. class)
	 *
	 * @param locator       of (By)
	 * @param attributeName of (String)
	 * @return : String
	 */
	public static String getAttributeValue(By locator, String attributeName) {
		String result = "";
		try {
			result = TestEngineWeb.driver.findElement(locator).getAttribute(attributeName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * refreshPage
	 */
	public void refreshPage() throws Throwable {
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName());
			driver.navigate().refresh();
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (Exception e) {
			LOG.info("++++++++++++++++++++++++++++Catch Block Start+++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());

			LOG.info("++++++++++++++++++++++++++++Catch Block End+++++++++++++++++++++++++++++++++++++++++++");
		}
	}

	/**
	 * clearData, Clear value from textBox
	 *
	 * @param locator of (By)
	 */
	protected void clearData(By locator) throws Throwable {
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName());
			WebElement element = driver.findElement(locator);
			element.sendKeys(Keys.CONTROL + "a");
			element.sendKeys(Keys.DELETE);
			element.clear();
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (Exception e) {
			LOG.info("++++++++++++++++++++++++++++Catch Block Start+++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());

			LOG.info("++++++++++++++++++++++++++++Catch Block End+++++++++++++++++++++++++++++++++++++++++++");
		}
	}

	public void sendKeysActionsEsc(By locator) throws Throwable {
		try {
			Thread.sleep(3000);
			WebElement element = driver.findElement(locator);
			element.sendKeys(Keys.ESCAPE);
		}catch (Exception e) {

		}
	}
	/**
	 * keyBoardOperations
	 *
	 * @param locator     of (By)
	 * @param testData    of (Keys)
	 * @param locatorName of (String)
	 * @return boolean
	 */
	public boolean keyBoardOperations(By locator, Keys testData, String locatorName) throws Throwable {
		boolean status;
		try {

			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : Type  ::  Locator : " + locatorName + " :: Data :" + testData);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			LOG.info("Waiting for element :");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			driver.findElement(locator).sendKeys(testData);
			LOG.info("Typed the Locator data :: " + testData);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

			reporter.SuccessReport("Enter text in :: " + locatorName, msgTypeSuccess +"'" +testData+"'","");
			status = true;
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
			reporter.failureReport("Enter text in :: " + locatorName, msgTypeFailure +"'" + testData+"'","", driver);
		}
		return status;
	}

	/**
	 * Switch to frame using index value
	 *
	 * @param index of (int), frame number to switch
	 */
	public void switchToFrameByIndex(int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * come out from frame
	 */
	public void comeOutFromFrame() {
		driver.switchTo().defaultContent();
	}

	/**
	 * Click on OK button on alert
	 */
	protected void acceptAlert() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * findWebElement
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return WebElement
	 */
	protected WebElement findWebElement(By locator, String locatorName) throws Throwable {
		WebElement element;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : click  ::  Locator : " + locatorName);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			LOG.info("Waiting for element");
			LOG.info("Locator is Visible :: " + locator);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			LOG.info("Clicked on the Locator");
			element = driver.findElement(locator);
			LOG.info("identified the element :: " + locator);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (Exception e) {
			LOG.info(e.getMessage());

			throw new RuntimeException(e);
		}
		return element;
	}

	/**
	 * checkBoxIsChecked
	 *
	 * @param by          of (By)
	 * @param locatorName of (String)
	 * @param expected    of (boolean)
	 * @return boolean
	 */
	protected boolean checkBoxIsChecked(By by, String locatorName, boolean expected) throws Throwable {
		boolean status = expected;
		String msgCheckboxisnotChecked = "Checkbox is not Selected";
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			waitTime();
			driver.findElement(by).isSelected();
			TestEngineWeb.reporter.SuccessReport("checkBoxIsChecked : " + locatorName,
					this.msgIsElementFoundSuccess + locatorName,"");
			status = true;
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
		} finally {
			if (!status) {
				if (reportIndicator) {
					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					reporter.failureReport("checkBoxIsChecked : ", msgCheckboxisnotChecked + locatorName, "",driver);
				}
			} else {
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				reporter.SuccessReport("checkBoxIsChecked : ", locatorName + ", checkBoxIsChecked : true","");
			}
			reportIndicator = true;
		}
		return status;
	}

	/**
	 * switchToWindow, Function to switch to latest window
	 */
	public void switchToWindow() {
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
	}

	/**
	 * switchToWindow, Function to switch to latest window
	 */
	public void switchToWindow(String mainWindow) {
		for (String handle : driver.getWindowHandles()) {
			if (handle != mainWindow)
				driver.switchTo().window(handle);
		}
	}

	/**
	 * switchToParentWindow, Function to switch to parent window
	 *
	 * @param handle of (String), window handle to switch
	 */
	public void switchToParentWindow(String handle) {
		driver.switchTo().window(handle);
	}

	/**
	 * closeWindow, Function to close the currently focused window
	 */
	protected void closeWindow() {
		driver.close();
	}

	/**
	 * getWindowHandle, Function to get the current window handle
	 *
	 * @return : String
	 */
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	/**
	 * scrollToWebElement, Function to scroll to a particular element
	 *
	 * @param element of (By)
	 */
	public void scrollToWebElement(By element) throws Throwable {
		boolean status = false;
		try {
			if (isVisibleOnly(element, "element")) {
				((JavascriptExecutor) WebDriver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
				status = true;
			}
		} catch (Exception e) {
			status = false;
		} finally {
			if (!status) {
				if (reportIndicator) {
					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					reporter.failureReport("scroll to element : ", "Unable to scroll to " + element, "",driver);
				}
			} else {
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				reporter.SuccessReport("scroll to element : ", "Able to scroll to " + element,"");
			}
			reportIndicator = true;
		}
	}

	protected void deleteSpecificFile(String fileName) throws InterruptedException {
		try {
			File file = new File(fileName);
			if (file.delete()) {
				LOG.info(file.getName() + " is deleted!");
			} else {
				LOG.info("Delete operation is failed.");
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * findWebElementVisibility, Function returns WebElement
	 *
	 * @return : WebElement
	 */

	protected WebElement findWebElementVisibility(By locator, String locatorName) throws Throwable {
		WebElement element;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : click  ::  Locator : " + locatorName);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			LOG.info("Waiting for element");
			LOG.info("Locator is Visible :: " + locator);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Element Found on the Locator");
			element = driver.findElement(locator);
			LOG.info("identified the element :: " + locator);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (Exception e) {
			LOG.info(e.getMessage());

			throw new RuntimeException(e);
		}
		return element;
	}

	protected boolean isCheckBoxSelected(By locator) {
		return driver.findElement(locator).isSelected();
	}

	/**
	 * isVisible
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean isVisibleOnly(By locator, String locatorName) throws Throwable {
		boolean flag;
		try {
			//added loggers
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name :: " + getCallerClassName() + " Method name :: " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);
			//value = driver.findElement(locator).isDisplayed();
			WebDriverWait wait = new WebDriverWait(driver, 3);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			flag = driver.findElement(locator).isDisplayed();
			//value = true;
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	protected long differenceBetweenTwoDates(String date1, String date2, String dateFormat) throws Throwable {
		long diffDays = 0;
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			Date d1 = format.parse(date1);
			Date d2 = format.parse(date2);
			long diff = d2.getTime() - d1.getTime();
			diffDays = diff / (24 * 60 * 60 * 1000) + 1;
		} catch (Exception e) {
			e.getMessage();
		}
		return diffDays;
	}

	/**
	 * clickUntilElementNotVisiable
	 *
	 * @param locator     of (By)
	 * @param waitLocator of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean clickUntilElementNotVisiable(By locator, By waitLocator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method :" + getCallerMethodName() + "  ::  Locator : " + locatorName);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			LOG.info("Waiting for element");
			//internalServerErrorHandler();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			LOG.info("Clicked on the Locator");
			int icounter = 0;
			do {
				icounter = icounter + 1;
				try {
					if (icounter > 0) {

						if (!isVisibleOnly(waitLocator, "Wait for Element : " + locatorName)) {
							flag = true;
							break;
						}
					}
					if (icounter >= 3) {
						flag = false;
						break;
					}
					WebElement element = TestEngineWeb.driver.findElement(locator);
					((JavascriptExecutor) WebDriver).executeScript("arguments[0].click();", element);
				} catch (Exception e) {
					LOG.info("Retrying for the object :: " + waitLocator
							+ " :: Iteration : " + icounter);
				}
			} while (icounter <= 3);

			LOG.info("identified the element :: " + locator);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			reporter.SuccessReport("Click : " + locatorName, msgClickSuccess + locatorName,"");
		} catch (Exception e) {
			flag = false;
			LOG.info(e.getMessage());
			reporter.failureReport("Click : " + locatorName, msgClickFailure + locatorName,"", driver);
		}
		return flag;
	}

	/**
	 * convertDateFormatToAnotherDateFormat, Function to convert one date format to another date format
	 *
	 * @param actualDate        of (String), Actual date (e.g: Dec 5, 2017)
	 * @param sourceFormat      of (String), format of actualDate (e.g: MMM dd, yyyy)
	 * @param destinationFormat of (String), Format what we required (e.g: dd/MM/yyyy)
	 * @return : String
	 */
	protected String convertDateFormatToAnotherDateFormat(String actualDate, String sourceFormat, String destinationFormat) throws Throwable {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sourceFormat);
		SimpleDateFormat sdf = new SimpleDateFormat(destinationFormat);
		Date date = simpleDateFormat.parse(actualDate);
		return sdf.format(date);
	}

	/**
	 * clickUntil
	 *
	 * @param locator     of (By)
	 * @param waitLocator of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean mouseDoubleClickUntil(By locator, By waitLocator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method :" + getCallerMethodName() + "  ::  Locator : " + locatorName);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			LOG.info("Waiting for element");
			//internalServerErrorHandler();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			LOG.info("Clicked on the Locator");

			int icounter = 0;
			do {
				icounter = icounter + 1;
				try {
					if (isVisibleOnly(waitLocator, "Wait for Element : " + locatorName)) {
						flag = true;
						break;
					} else {
					}
					if (icounter >= 3) {
						flag = false;
						break;
					}
					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++");
					LOG.info("Mouse Double Click start :: " + locatorName);
					WebElement mo = TestEngineWeb.driver.findElement(locator);
					new Actions(TestEngineWeb.driver).moveToElement(mo).doubleClick(mo).build().perform();
					flag = true;
					LOG.info("Mouse Double Click :: " + locatorName);
					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++");
					return true;
				} catch (Exception e) {
					LOG.info("Retrying for the object :: " + waitLocator
							+ " :: Iteration : " + icounter);
				}
			} while (icounter <= 3);

			LOG.info("identified the element :: " + locator);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			reporter.SuccessReport("Click : " + locatorName, msgClickSuccess + locatorName,"");
		} catch (Exception e) {
			flag = false;
			LOG.info(e.getMessage());
			reporter.failureReport("Click : " + locatorName, msgClickFailure + locatorName,"", driver);
		}
		return flag;
	}

	/**
	 * mouseJSDoubleClickUntil
	 *
	 * @param locator     of (By)
	 * @param waitLocator of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean mouseJSDoubleClickUntil(By locator, By waitLocator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method :" + getCallerMethodName() + "  ::  Locator : " + locatorName);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			LOG.info("Waiting for element");
			//internalServerErrorHandler();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			LOG.info("Clicked on the Locator");

			int icounter = 0;
			do {
				icounter = icounter + 1;
				try {
					if (isVisibleOnly(waitLocator, "Wait for Element : " + locatorName)) {
						flag = true;
						break;
					} else {
					}
					if (icounter >= 3) {
						flag = false;
						break;
					}
					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++");
					LOG.info("Mouse Double Click start :: " + locatorName);
					WebElement mo = TestEngineWeb.driver.findElement(locator);
					((JavascriptExecutor) driver).executeScript("var evt = document.createEvent('MouseEvents'); evt.initMouseEvent('dblclick',true, "
							+ "true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null); arguments[0].dispatchEvent(evt);", mo);
					flag = true;
					LOG.info("Mouse Double Click :: " + locatorName);
					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++");
					return true;
				} catch (Exception e) {
					LOG.info("Retrying for the object :: " + waitLocator
							+ " :: Iteration : " + icounter);
				}
			} while (icounter <= 3);

			LOG.info("identified the element :: " + locator);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			reporter.SuccessReport("Click : " + locatorName, msgClickSuccess + locatorName,"");
		} catch (Exception e) {
			flag = false;
			LOG.info(e.getMessage());
			reporter.failureReport("Click : " + locatorName, msgClickFailure + locatorName, "",driver);
		}
		return flag;
	}

	/**
	 * Double click the mouse to the middle of the element. The element is scrolled
	 * into view and its location is calculated using getBoundingClientRect.
	 *
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * @param locatorName : Meaningful name to the element (Ex:link,menus etc..)
	 */
	public boolean mouseJSDoubleClick(By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Mouse Double Click start :: " + locatorName);
			WebElement mo = TestEngineWeb.driver.findElement(locator);
			((JavascriptExecutor) driver).executeScript("var evt = document.createEvent('MouseEvents'); evt.initMouseEvent('dblclick',true, "
					+ "true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null); arguments[0].dispatchEvent(evt);", mo);
			flag = true;
			LOG.info("Mouse Double Click :: " + locatorName);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++");
			return true;
		} catch (Exception e) {
			//return false;
			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				TestEngineWeb.reporter.failureReport("double Click :: ", "double Click action is not perform on ::" + locatorName,"",
						TestEngineWeb.driver);
			} else {
				TestEngineWeb.reporter.SuccessReport("double Click :: ", "double Click Action is Done on  :: " + locatorName,"");
			}
		}
	}

	/**
	 * mouseHoverUntil
	 *
	 * @param locator     of (By)
	 * @param waitLocator of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean mouseHoverUntil(By locator, By waitLocator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method :" + getCallerMethodName() + "  ::  Locator : " + locatorName);
			LOG.info("Mouse Hover start :: " + locatorName);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			LOG.info("Waiting for element");
			//internalServerErrorHandler();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			LOG.info("Clicked on the Locator");

			int icounter = 0;
			do {
				icounter = icounter + 1;
				try {
					WebElement mo = TestEngineWeb.driver.findElement(locator);
					new Actions(TestEngineWeb.driver).moveToElement(mo).build().perform();
					if (isVisibleOnly(waitLocator, "Wait for Element : " + locatorName)) {
						flag = true;
						break;
					} else {
						LOG.info("Mouse Hover Attempt >>> " + icounter);
					}
					if (icounter >= 3) {
						flag = false;
						break;
					}

					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++");
					return true;
				} catch (Exception e) {
					LOG.info("Retrying for the object :: " + waitLocator
							+ " :: Iteration : " + icounter);
				}
			} while (icounter <= 3);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (Exception e) {
			flag = false;
			LOG.info(e.getMessage());
		} finally {
			if (!flag) {//flag=true
				if (reportIndicator) {//yes
					reporter.failureReport("Mouse Hover :: " + locatorName, "Unable To Mouse Hover On " + locatorName,"", driver);
				}
			} else {
				reporter.SuccessReport("Mouse Hover :: " + locatorName, "Successfully Mouse Hover On" + locatorName,"");
			}
		}
		reportIndicator = true;

		return flag;
	}

	/**
	 * mouseHoverUntilGetProperty
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 * @paramwaitLocator of (By)
	 */
	protected String mouseHoverUntilGetProperty(By locator, By propertyLocator, String locatorName) throws Throwable {
		boolean flag = false;
		String text = "";
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method :" + getCallerMethodName() + "  ::  Locator : " + locatorName);
			LOG.info("Mouse Hover start :: " + locatorName);
			WebDriverWait wait = new WebDriverWait(driver, 3);
			LOG.info("Waiting for element");
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			LOG.info("Clicked on the Locator");

			int icounter = 0;
			do {
				icounter = icounter + 1;
				try {
					WebElement mo = TestEngineWeb.driver.findElement(locator);
					new Actions(TestEngineWeb.driver).moveToElement(mo).build().perform();
					if (isVisibleOnly(propertyLocator, "Wait for Element : " + locatorName)) {
						flag = true;
						text = driver.findElement(propertyLocator).getText().trim();
						break;
					} else {
						LOG.info("Mouse Hover Attempt >>> " + icounter);
					}
					if (icounter >= 3) {
						flag = false;
						break;
					}

					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++");
					flag = true;
				} catch (Exception e) {
					LOG.info("Retrying for the object :: " + propertyLocator
							+ " :: Iteration : " + icounter);
				}
			} while (icounter <= 3);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (Exception e) {
			flag = false;
			LOG.info(e.getMessage());
		} finally {
			if (!flag) {//flag=true
				if (reportIndicator) {//yes
					reporter.failureReport("Mouse Hover :: " + locatorName, "Unable To Mouse Hover On " + locatorName,"", driver);
				}
			} else {
				reporter.SuccessReport("Mouse Hover :: " + locatorName, "Successfully Mouse Hover On" + locatorName,"");
			}
		}
		reportIndicator = true;
		return text;
	}

	protected static float roundToDecimals(double d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Double.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

	/**
	 * Get text from PDF file.
	 *
	 * @param pdfFilePath (String) path of the PDF file
	 * @return (String) text from PDF file
	 * @throws Throwable the throwable
	 */
	public String getTextFromPDF(String pdfFilePath) throws Throwable {
		String parsedText = null;
		try {
			File pdfFile = new File(pdfFilePath);

			PDFParser parser = new PDFParser(new FileInputStream(pdfFile));
			parser.parse();

			COSDocument cosDoc = parser.getDocument();
			PDDocument pdDoc = new PDDocument(cosDoc);

			PDFTextStripper pdfStripper = new PDFTextStripper();
			parsedText = pdfStripper.getText(pdDoc);
			LOG.info("Text in PDF is: \n" + parsedText);
			parser.getPDDocument().close();
		} catch (Exception e) {
			e.printStackTrace();
			reporter.failureReport("Get text from PDF", "Exception while reading data from PDF file", "",driver);
		}
		return parsedText;
	}

	/**
	 * Get text from PDF file.
	 *
	 * @param pdfFilePath (String) path of the PDF file
	 * @return (String) text from PDF file
	 * @throws Throwable the throwable
	 */
	protected String getTextFromPDFWithWaitLocater(String pdfFilePath, By WaitLocater) throws Throwable {
		String parsedText = null;
		try {
			File pdfFile = new File(pdfFilePath);

			PDFParser parser = new PDFParser(new FileInputStream(pdfFile));
			parser.parse();

			COSDocument cosDoc = parser.getDocument();
			PDDocument pdDoc = new PDDocument(cosDoc);

			PDFTextStripper pdfStripper = new PDFTextStripper();
			dynamicWaitByLocator(WaitLocater, 4);
			parsedText = pdfStripper.getText(pdDoc);
			LOG.info("Text in PDF is: \n" + parsedText);
			parser.getPDDocument().close();
		} catch (Exception e) {
			reporter.failureReport("Get text from PDF", "Exception while reading data from PDF file", "",driver);
		}
		return parsedText;
	}

	/**
	 * Function to get attribute name from focused element
	 * If we use this method it will return required attribute value of focused element
	 *
	 * @throws Throwable the throwable
	 */
	protected String getAttributeFromFocusedElement(String attributeName) throws Throwable {
		WebElement activeElement = driver.switchTo().activeElement();
		return activeElement.getAttribute(attributeName);
	}

	/**
	 * Function to get highlighted text
	 * If we use this method it will return required text of highlighted element
	 *
	 * @throws Throwable the throwable
	 */
	protected String getHighlightedText() throws Throwable {
		return (String) driver.executeScript("return window.getSelection().toString();");
	}

	/**
	 * Function to get value from XML file
	 * If we use this method it will return required value based on tag name
	 *
	 * @param xmlFileName (String) path of the XML file
	 * @param parentTag   (String) Parent tag name
	 * @param childTag    (String) Child tag name
	 */
	protected String readXML(String xmlFileName, String parentTag, String childTag) {
		String tagName = "";
		try {
			File fXmlFile = new File(xmlFileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName(parentTag);
			Node nNode;
			Element eElement;
			for (int temp = 0; temp < nList.getLength(); temp++) {
				nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					eElement = (Element) nNode;
					tagName = eElement.getElementsByTagName(childTag).item(0).getTextContent();
				}
			}
		} catch (Exception e) {
		}
		return tagName;
	}

	protected void scrollBottom() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1500)");

	}

	/**
	 * Function to get check the sessions in multiple tabs
	 * If we use this method it will return required text of highlighted element
	 *
	 * @throws Throwable the throwable
	 */
	protected void openURLInNewTab(String URL) throws Throwable {
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, "t");
		driver.findElement(By.tagName("body")).sendKeys(selectLinkOpeninNewTab);
		driver.get(URL);
	}


	protected void openURLInNewWindow(String URL) throws Throwable {

		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String OpenNewtab = tabs.get(1);
		if (OpenNewtab!=null) {
			reporter.SuccessReport("OpenNewtab","New tab opened",URL);
		}
		driver.get(URL);
	}
	/**
	 * Function to perform keyboard action
	 * If we use this method it will perform keyboard actions
	 *
	 * @throws Throwable the throwable
	 */
	protected void sendKeys(String combineKeys,String combineKeysName) throws Throwable {
		Actions keyAction = new Actions(driver);
		keyAction.sendKeys(combineKeys).build().perform();
		TestEngineWeb.reporter.SuccessReport("Sending Keys :: " + combineKeysName, "Successfully send keys :: " +combineKeysName,combineKeysName);
	}
	public void navigateToApplication(String url) throws Throwable{
		try {
			LOG.info("++++++++++++++++++++++++++++Navigate to URL start+++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("++++++++++++++++++++++++++++"+url+"+++++++++++++++++++++++++++++++++++++++++++");
			Thread.sleep(2000);
			//driver.close();
			switch (url){
				
				case "SMART":
					LOG.info("++++++++++++++++++++++++++++"+SMART_URL+"+++++++++++++++++++++++++++++++++++++++++++");
					LOG.info("++++++++++++++++++++++++++++"+url+"+++++++++++++++++++++++++++++++++++++++++++");

					driver.get(SMART_URL);
					acceptAlert();
					driver.navigate().to(SMART_URL);
					landingURL = SMART_URL;
					break;
				case "CANADA":
					LOG.info("++++++++++++++++++++++++++++"+CANADA_URL+"+++++++++++++++++++++++++++++++++++++++++++");
					LOG.info("++++++++++++++++++++++++++++"+url+"+++++++++++++++++++++++++++++++++++++++++++");

					driver.get(CANADA_URL);
					acceptAlert();
					driver.navigate().to(CANADA_URL);
					acceptAlert();
					landingURL = CANADA_URL;
					break;
					
			}
			Thread.sleep(5000);
			reporter.SuccessReport("Navigate to URL::<b>" + url+"</b>",
					"Navigate to URL is successfull:" ,landingURL);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static int IntRandomNumberGenerator(int min, int max) {
		Random rand = new Random();
		int randomNum = min + rand.nextInt((max - min) + 1);
		return randomNum;
	}

	/**
	 * isEnabled
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	protected boolean isEnabled(By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			//added loggers
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name :: " + getCallerClassName() + " Method name :: " + getCallerMethodName());
			LOG.info("Method : " + getCallerMethodName() + "  ::  Locator : " + locatorName);
			flag = driver.findElement(locator).isEnabled();
			//value = true;
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (Exception e) {
			flag = false;
		} finally {
			if (!flag) {
				reporter.failureReport("IsEnabled : ", locatorName + " Element is Not Enabled : ", "",driver);
			} else {
				reporter.SuccessReport("IsEnabled : ", locatorName + " Element is Enabled : ","");
			}
		}
		return flag;
	}




	/**
	 * click
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean setFocusAndClick(By locator, String locatorName) throws Throwable {
		boolean status = false;
		//isElementPresent(locator, locatorName);
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : click  ::  Locator : " + locatorName);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			//internalServerErrorHandler();
			LOG.info("Waiting for element");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			//wait.until(ExpectedConditions.elementToBeClickable(locator));
			LOG.info("focused on the Locator");
			WebElement element=driver.findElement(locator);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().build().perform();
			LOG.info("clicked  on the Locator");
			//driver.findElement(locator).click();
			LOG.info("identified the element to focus :: " + locator);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			status = true;
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
			reporter.failureReport("Click : " + locatorName, msgClickFailure + locatorName, "",driver);
		} finally {
			if (!status) {
				if (reportIndicator) {
					reporter.failureReport("Click : " + locatorName, msgClickFailure + locatorName, "",driver);
				}
			} else {
				reporter.SuccessReport("Click : " + locatorName, msgClickSuccess + locatorName,"");
			}
			reportIndicator = true;
		}
		return status;
	}
	/**
	 * type
	 *
	 * @param locator     of (By)
	 * @param testData    of (String)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean typeOnly(By locator, String testData, String locatorName) throws Throwable {
		boolean status = false;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name : " + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : Type  ::  Locator : " + locatorName + " :: Data :" + testData);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			LOG.info("Waiting for element :");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
//			wait.until(ExpectedConditions.elementToBeClickable(locator));
			/*driver.findElement(locator).click();
			LOG.info("Clicked on the Locator : ");*/
			driver.findElement(locator).clear();
			LOG.info("Cleared the existing Locator data : ");
			driver.findElement(locator).sendKeys(testData);
			LOG.info("Typed the Locator data :: " + testData);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			status = true;
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
		} finally {
			if (!status) {
				if (reportIndicator) {
					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					//reporter.failureReport("Enter text in :: " + locatorName, msgTypeFailure +"'" +testData+"'", driver);
				}
			} else {
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				//reporter.SuccessReport("Enter text in :: " + locatorName, msgTypeSuccess +"'"+ testData+"'");
			}
			reportIndicator = true;
		}
		return status;
	}

	/**
	 * click
	 *
	 * @param locator     of (By)
	 * @param locatorName of (String)
	 * @return boolean
	 * @throws Throwable the throwable
	 */
	public boolean clickOnly(By locator, String locatorName) throws Throwable {
		boolean status = false;
		//isElementPresent(locator, locatorName);
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
			LOG.info("Method : click  ::  Locator : " + locatorName);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			//internalServerErrorHandler();
			LOG.info("Waiting for element");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			LOG.info("Clicked on the Locator");
			driver.findElement(locator).click();
			LOG.info("identified the element :: " + locator);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			status = true;
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
			//reporter.failureReport("Click : " + locatorName, msgClickFailure + locatorName, driver);
		} finally {
			if (!status) {
				if (reportIndicator) {
					//reporter.failureReport("Click : " + locatorName, msgClickFailure + locatorName, driver);
				}
			} else {
				//reporter.SuccessReport("Click : " + locatorName, msgClickSuccess + locatorName);
			}
			reportIndicator = true;
		}
		return status;
	}

	public boolean selectAndElementVerifyByName(By locator,String locatorName,String elementName) throws Throwable {
		boolean status=false;
		List<WebElement> elements=getWebElementList(locator,locatorName);
		for(WebElement elementNames : elements)
		{

			if(elementName.equalsIgnoreCase(elementNames.getText()))
			{
				reporter.SuccessReport("successfully found",elementName,elementName);
				//By element=elementNames;
				System.out.println(elementNames);
			}
			else
			{
				reporter.failureReport("successfully found",elementName,elementName,driver);
			}
		}
		return status;
	}
	
	public String getCurrentUrl(){
		return driver.getCurrentUrl();
	}
	
	/**
	 * Click on OK button on alert
	 */
	protected void acceptAlert(EventFiringWebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	
	public String getAttributeValue(WebElement element, String attributeName) throws Throwable {

		String text = "";
		boolean flag = false;
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ ");
		LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
		try {
				text = element.getAttribute(attributeName);
				LOG.info("Element is located and attribute is retrieved :: " + text);
				flag = true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (!flag) {
				reporter.warningReport("GetAttributeValue :: ", "Unable to get attribute value");
				LOG.info("GetAttributeValue :: Unable to get Attribute");
			} else {
				reporter.SuccessReport("GetAttributeValue :: ", "Attribute value"+ text,attributeName);
				LOG.info("GetAttributeValue :: Attribute is retrieved :: " + text);
			}
		}
		return text;
	}
	

	public List<String> getDropDownData(By locator,String dataType){
		boolean flag = false;
		List<String> dropdownData=new ArrayList<String>();
		try {
			Select s = new Select(driver.findElement(locator));
			List<WebElement> allOptions=s.getOptions();
			for(WebElement option : allOptions){
				String data;
				if(dataType.equalsIgnoreCase("value")){
					data=option.getAttribute("value");
				}
				else{
					data=option.getText();
				}
				dropdownData.add(data);
			}

		} catch (Exception e) {
			reporter.failureReport("Get data from drop down","Failed to read data from dropdown "+e.getMessage(), dataType,driver);
			throw new RuntimeException(e);
		} 
		finally{
			return dropdownData;
		}
	}

	public String getSelectedDropdownOption(By locator){
		String selectedItem = null;
		try {
			Select select = new Select(driver.findElement(locator));
			WebElement option = select.getFirstSelectedOption();
			selectedItem = option.getText();
		}
		catch(Exception ex){
			reporter.failureReport("Get Selected Drop down Option","Failed to get selected Drop down Option"+ex.getMessage(),"", driver);
			throw new RuntimeException(ex);
		} 
		finally{
			return selectedItem;
		}
	}
	/**
	 * param ::
	 * return ::void
	 * throws :: throwable
	 * methodName :: javaScriptOpenNewTab
	 * description :: to open new tab in chrome
	 * date ::
	 * 
	 */
	public void javaScriptOpenNewTab()
	{
		((JavascriptExecutor)driver).executeScript("window.open()");
	}
	
	//this is to get the class script name on the report
		public static String getCurrentClassAndMethodNames() {
	        final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
	        final String s = e.getClassName();
	        return s.substring(s.lastIndexOf('.') + 1, s.length()) + "." + e.getMethodName();
	    } 
		
		/**
		 * radioClick
		 *
		 * @param locator     of (By)
		 * @param locatorName of (String)
		 * @return boolean
		 * @throws Throwable the throwable
		 */
		public boolean radioClick(By locator, String locatorName, WebDriver... spDrivers) throws Throwable {
			boolean status = false;
			EventFiringWebDriver driver1 = driver;

			//isElementPresent(locator, locatorName);
			try {
				if(spDrivers.length != 0){
					driver1=(EventFiringWebDriver)(spDrivers[0]);
				}
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
				LOG.info("Method : click  ::  Locator : " + locatorName);
				//WebDriverWait wait = new WebDriverWait(driver1, 30);
				//internalServerErrorHandler();
				LOG.info("Waiting for element");
				//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				LOG.info("Locator is Visible :: " + locator);
				//wait.until(ExpectedConditions.elementToBeClickable(locator));
				LOG.info("Clicked on the Locator");
				driver.findElement(locator).click();
				LOG.info("identified the element :: " + locator);
				LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				status = true;
			} catch (Exception e) {
				status = false;
				LOG.info(e.getMessage());
				reporter.failureReport("Click : " + locatorName, msgClickFailure ,locatorName, driver1);
			} finally {
				if (!status) {
					if (reportIndicator) {
						reporter.failureReport("Click : " + locatorName, msgClickFailure , locatorName, driver1);
					}
				} else {
					reporter.SuccessReport("Click : " + locatorName, msgClickSuccess , locatorName);
				}
				reportIndicator = true;
			}
			return status;
		}
		public void sendKeysActionsEnter(By locator) throws Throwable {
			try {
				Thread.sleep(3000);
				WebElement element = driver.findElement(locator);
				element.sendKeys(Keys.ENTER);
			}catch (Exception e) {

			}
		}
		/**
		 * Method is to go to the particular element
		 * 
		 * @param element
		 * @param driver
		 */
		 public static void scrollIntoElementView(WebElement element, WebDriver driver) {
		  try {

		   ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		  } catch (Exception e) {

		   System.out.println("Element with " + element + "is not attached to the page document");
		  }

		 } 
		 
		 /**
		  * method is to click on web element
		  * @param driver
		  * @param element
		  */
		 public static void safeclick(WebDriver driver, WebElement element) {
		  try {
		   (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
		   scrollIntoElementView(element, driver);
		   element.click();
		   LOG.info("Method : click  ::  Locator : " + element);
		   LOG.info("Clicked on the Locator");
		  } catch (Exception e) {
		   // simply retry finding the element in the refreshed DOM
		   // highlightElement(driver, element);
		   element.click();
		   System.out.println("Element identified by " + element.toString() + " was not clickable after 10 seconds");

		  } 
		 } 

		 public static boolean verify_url(WebDriver driver, String url) {
		  System.out.println("Current url=" + driver.getCurrentUrl());
		  return driver.getCurrentUrl().contains(url);
		 }
		 
		 /**
		  * 
		  * @param element
		  * @param driver
		  *            Purpose:Method to hover on an element based on locator using
		  *            Actions,it waits until the element is loaded and then hovers
		  *            on the element
		  */

		 public static void mouseHover(WebElement element, WebDriver driver) {
		  try {
		   Actions builder = new Actions(driver);
		   builder.moveToElement(element).build().perform();
		   try {
		    Thread.sleep(2000);
		   } catch (InterruptedException e) {
		    System.out.println("Exception" + e);
		   }
		   System.out.println("Hovered on element " + element);
		  } catch (NoSuchElementException e) {

		   System.out.println("Element " + element + " was not found in DOM");
		  } catch (Exception e) {

		   System.out.println("Unable to hover the cursor on " + element);
		  }
		 }
		 
		 /**
		  * @author Bharathi
		  * @param driver
		  * @param element
		  */
		 public static void highlightElement(WebDriver driver, WebElement element) {

		  String attributevalue = "border:3px solid red;";
		  JavascriptExecutor executor = (JavascriptExecutor) driver;
		  String getattrib = element.getAttribute("style");
		  executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
		  try {
		   Thread.sleep(300);
		  } catch (InterruptedException e) {
		   System.out.println("Sleep interrupted - " + e);
		  }
		  executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, getattrib);
		 }

		 /**
		  *
		  * @author Bharathi
		  * @param driver
		  *            This method is to click on any element
		  */
		 public static void JSclick(WebDriver driver, WebElement element) {
		  highlightElement(driver, element);
		  JavascriptExecutor jse = (JavascriptExecutor) driver;
		  jse.executeScript("arguments[0].click();", element);

		 }

		 /**
		  *
		  * @author Bharathi
		  *            This method is used to navigate to back page
		  */
		 public void navigateToBackPage() {
		  try {
		   driver.navigate().back();
		  } catch (Exception e) {
		   System.out.println("Some exception occured while navigating to back page" + e);
		  }
		 }

		 
		 public static void safeClearAndTypeAndClickEnter(WebElement element, String text) {
		  try {
		   // highlightElement(driver, element);
		   Actions builder = new Actions(driver);
		   builder.moveToElement(element).build().perform();
		   element.clear();
		   element.sendKeys(text);
		   element.sendKeys(Keys.ENTER);
		  } catch (Exception ex) {
		   System.out.println("Inside safe clear and type-" + element + " at [" + driver.getTitle() + "]");

		  }

		 }
		 
		 /** Method is to switch over from a Parent window to a Child window 
		 */
			public void switchToChildWindow() {
				String parentWindow = driver.getWindowHandle();
				Set<String> handles = driver.getWindowHandles();
				for (String windowHandle : handles) {
					if (!windowHandle.equals(parentWindow)) {
						driver.switchTo().window(windowHandle);
					}
				}
			}
			
			/**
			 * This method is to scroll up 
			 * @throws InterruptedException
			 */
			public void scrollUp() throws InterruptedException{
				Thread.sleep(5000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
				
			}
			
		/**
		 * This method is to scroll page to bottom
		 * @throws InterruptedException
		 */
			public void scrollToBottom() throws InterruptedException{
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo(0, 1250)");
			}
			
			/**
			 * getWindowHandle, Function to get the current window handle
			 *
			 * @return : String
			 */
			public String parentWindow() {
				return driver.getWindowHandle();
			}
			
			protected String getAttributeBySrc(By locator, String locatorName) throws Throwable {
				String text = "";
				boolean flag = false;
				try {
					LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
					LOG.info("Method : " + getCallerMethodName());
					waitTime();
					if (isElementPresent(locator, locatorName, true)) {
						text = driver.findElement(locator).getAttribute("src");
						LOG.info("Locator is Visible and attribute value is retrieved :: " + text);
						LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						flag = true;
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					if (!flag) {
						reporter.warningReport("GetAttribute :: ", "Unable to get Attribute value from :: " + locatorName);
						LOG.info("GetAttribute :: Unable to get Attribute value from :: " + locatorName);
					} else {
						reporter.SuccessReport("GetAttribute :: ", "" + locatorName + " is" + text,"");
						LOG.info("Locator is Visible and attribute value is retrieved ::"+text);
					}
				}
				return text;
			}
		public void scrollToBottomWithCordinate(String yCordinate) throws Throwable {
			Thread.sleep(5000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, "+yCordinate+")");
		}

	public boolean isElementClickable(By locator,int timeout,String locatorName) throws Throwable
	{
		boolean status = false;
		Long startime=System.currentTimeMillis();
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
		LOG.info("Method : isElementClickable  ::  Locator : " + locator);
		Thread.sleep(1000);
		for(int slpcnt=1;slpcnt<=timeout;slpcnt++)
		{
			try
			{
				Thread.sleep(1000);
				this.driver.findElement(locator).click();
				status = true;
				this.reporter.SuccessReport("Click :" + locatorName, this.msgClickSuccess + locatorName,"");
				break;
			}
			catch(Exception ex)
			{
				status = false;
                this.reporter.failureReport("Click :" + locatorName, this.msgClickFailure + locatorName,"",driver);

            }
		}
		return status;
	}

	public void isElementType(By locator,String data,int timeout,String locatorName) throws Throwable
	{
		boolean status = false;
		Long startime=System.currentTimeMillis();
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
		LOG.info("Method : isElementClickable  ::  Locator : " + locator);
		Thread.sleep(1000);
		for(int slpcnt=1;slpcnt<=timeout;slpcnt++)
		{
			try
			{
				Thread.sleep(1000);
				this.driver.findElement(locator).clear();
				Thread.sleep(500);
				this.driver.findElement(locator).sendKeys(data);
				status = true;
				this.reporter.SuccessReport("Type :" + locatorName, this.msgTypeSuccess + locatorName,data);
				break;
			}
			catch(Exception ex)
			{
				status = false;
                this.reporter.failureReport("Type :" + locatorName, this.msgTypeFailure + locatorName,data,driver);

            }
		}
	}

	public boolean isElementVisible(By locator,int timeout,String locatorName) throws Throwable
	{
		boolean status = false;
		Long startime=System.currentTimeMillis();
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		LOG.info("Class name" + getCallerClassName() + "Method name : " + getCallerMethodName());
		LOG.info("Method : isElementClickable  ::  Locator : " + locator);
		Thread.sleep(1000);
		for(int slpcnt=1;slpcnt<=timeout;slpcnt++)
		{
			try
			{
				Thread.sleep(1000);
				this.driver.findElement(locator).isDisplayed();
				status = true;
				this.reporter.SuccessReport("Visibility Of Elemenet :" + locatorName, this.msgIsElementFoundSuccess + locatorName,"");
				break;
			}
			catch(Exception ex)
			{
				status = false;
                this.reporter.failureReport("Visibility Of Element :" + locatorName, this.msgIsElementFoundFailure + locatorName,"",driver);

            }
		}
		return status;

	}

}
