package com.insight.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.insight.accelerators.TestEngineWeb;

public class CustomTestListenerCT extends TestListenerAdapter {
	private static final String ROOT = System.getProperty("user.dir");
	private static final String SEPARATOR = File.separator;
	private static ExtentReports extent = ExtentManager.getInstance();
	private static final Logger LOGGER = LogManager.getLogger(CustomTestListenerCT.class);

	/**
	 * Override method to implement
	 * to some custom logging and ExtentReport
	 */

	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("----------------------START onStart---------------");
		super.onStart(context);
		String testSuite = context.getSuite().getName();
		LOGGER.info("[Test Suite START (Thread=" + Thread.currentThread().getId() + ")] " + testSuite);
		//set test suite
		extent.setSystemInfo("SuiteExecuted", context.getSuite().getParameter("suiteExecuted"));
		extent.setSystemInfo("SuiteExecutionType", context.getSuite().getParameter("executionType"));
		extent.setSystemInfo("OS", context.getCurrentXmlTest().getParameter("platformName"));
		extent.setSystemInfo("Browser", context.getCurrentXmlTest().getParameter("browser"));
		extent.setSystemInfo("BrowserVersion", context.getCurrentXmlTest().getParameter("browserVersion"));
		extent.setSystemInfo("AutomationEnv", context.getCurrentXmlTest().getParameter("environment"));
		extent.setSystemInfo("AutomationName", context.getCurrentXmlTest().getParameter("automationName"));
		extent.setSystemInfo("SeleniumGridUrl", context.getCurrentXmlTest().getParameter("seleniumgridurl"));
		System.out.println("----------------------END onStart---------------");
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println("----------------------START onFinish---------------");
		super.onFinish(context);
		LOGGER.info("[Test Suite Finish (Thread=" + Thread.currentThread().getId() + ")] " + context.getSuite().getName());
		ExtentManager.getInstance().flush();
		System.out.println("----------------------END onFinish---------------");
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		System.out.println("----------------------START onTestStart---------------");
		super.onTestStart(result);
		String testClass = result.getTestClass().getName().replaceAll(".+\\.", "");
		String testMethod = result.getMethod().getMethodName();
		String[] testGroups = result.getMethod().getGroups();
		String testStart = "[TEST START (Thread=" + Thread.currentThread().getId() + ")] " + testClass + " - " + testMethod;
		LOGGER.info(testStart);
		ExtentReportThread.startChildTest(testClass + "." + testMethod).assignCategory(testGroups);
		System.out.println("----------------------END onTestStart---------------");	
	}

	@Override
	public synchronized void onTestSuccess(ITestResult tr) {
		System.out.println("----------------------START onTestSuccess---------------");
		super.onTestSuccess(tr);
		String testName = tr.getTestClass().getName().replaceAll(".+\\.", "") + " - " + tr.getMethod().getMethodName();
		String testPass = "[***TEST PASS (Thread=" + Thread.currentThread().getId() + ")***] " + testName;
		LOGGER.info(testPass);
		Markup m = MarkupHelper.createLabel(testPass, ExtentColor.GREEN);
		ExtentReportThread.getChildTest().pass(m);
		System.out.println("----------------------END onTestSuccess---------------");
	}

	@Override
	public synchronized void onTestFailure(ITestResult tr) {
		System.out.println("----------------------START onTestFailure---------------");
		super.onTestFailure(tr);
		Object currentClass = tr.getInstance();
		WebDriver driver = null ;
		try{
		driver= ((TestEngineWeb) currentClass).driver;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		if (TestEngineWeb.Environment.equalsIgnoreCase("cloudSauceLabs")) {
			((JavascriptExecutor) driver).executeScript("sauce:job-result=failed");
		}
		//Timestamp the screen shot
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yy-HH-mm-ss");
		Calendar calendar = Calendar.getInstance();
		String timeStamp = simpleDateFormat.format(calendar.getTime());
		String baseDir = ROOT + SEPARATOR + "results" + SEPARATOR + "test-output";
		try {
		File scrFile;
		scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String fileName = tr.getMethod().getMethodName() + "_" + timeStamp + "_TestFAILED.png";
		File destination = new File(baseDir + SEPARATOR + fileName);
			FileUtils.copyFile(scrFile, destination);
			ExtentReportThread.getChildTest().addScreenCaptureFromPath(fileName);
		} catch (IOException e) {
			ExtentReportThread.getChildTest().fail(e);
		}
		String errHeader = " [***TEST FAIL (Thread=" + Thread.currentThread().getId() + ")***] " + tr.getTestClass().getName().replaceAll(".+\\.", "") + " - " + tr.getMethod().getMethodName();
		String errMsg = errHeader + "\r\n" + tr.getThrowable().getMessage() + "\r\n\t" +Arrays.toString(tr.getThrowable().getStackTrace()).replaceAll(",", "\r\n\t");
		LOGGER.error(errMsg);
		Markup m;
		
		
			
		
		m = MarkupHelper.createLabel(errHeader, ExtentColor.RED);
		ExtentReportThread.getChildTest().fail(m);
		ExtentReportThread.getChildTest().fail(tr.getThrowable());
		System.out.println("----------------------END onTestFailure---------------");
	}

	@Override
	public synchronized void onTestSkipped(ITestResult tr) {
		try {
			System.out.println("----------------------START TEST SKIPPED---------------");
			super.onTestSkipped(tr);
			System.out.println("----------------------TEST skip Reason-------------------"+tr.getThrowable().getMessage());
			String skipErr = " [TEST SKIP (Thread=" + Thread.currentThread().getId() + ")] " + tr.getTestClass().getName().replaceAll(".+\\.", "") + " - " + tr.getMethod().getMethodName();
			LOGGER.info("------------------Test Skipped as : "+skipErr+"------------------------");
			LOGGER.info(skipErr);
			System.out.println("----------------------TEST SKIP REASON---------------"+skipErr);
			Markup m = MarkupHelper.createLabel(skipErr, ExtentColor.RED);
			ExtentReportThread.getChildTest().skip(m);
			ExtentReportThread.getChildTest().skip(tr.getThrowable());
			System.out.println("----------------------END TEST SKIPPED---------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*@Override
	public synchronized void onConfigurationFailure(ITestResult tr) {
		System.out.println("----------------------START onConfigurationFailure---------------");
		super.onConfigurationFailure(tr);
		System.out.println("----------------------Configuration Failure REASON-------------------"+tr.getThrowable().getMessage());
		String testClass = tr.getTestClass().getName().replaceAll(".+\\.", "");
		String testMethod = tr.getMethod().getMethodName();
		Object currentClass = tr.getInstance();
		String errHeader = " [TEST CONFIG FAIL (Thread=" + Thread.currentThread().getId() + ")] " + testClass + " - " + testMethod;
		WebDriver driver = null;
		AppiumDriver ad=null;
		//Timestamp the screen shot
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yy-HH-mm-ss");
		Calendar calendar = Calendar.getInstance();
		String timeStamp = simpleDateFormat.format(calendar.getTime());
		String baseDir = ROOT + SEPARATOR + "results" + SEPARATOR + "test-output";
		File scrFile=null;
		try{
			driver = ((TestEngineWeb) currentClass).driver;
			ad=((TestEngineWeb)currentClass).appiumDriver;
		if(driver!=null)
		{
			scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
		}
		else if(ad!=null)
		{
			scrFile = ((TakesScreenshot) ad).getScreenshotAs(OutputType.FILE);
			System.out.println(scrFile.getAbsolutePath());
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		String fileName = testMethod + "_ConfigFail_" + timeStamp + ".png";
		File destination = new File(baseDir + SEPARATOR + fileName);
		// adding screenshots to log
		try {
			FileUtils.copyFile(scrFile, destination);
			ExtentReportThread.getChildTest().addScreenCaptureFromPath(fileName);
		} catch (IOException e) {
			ExtentReportThread.getChildTest().fail(e);
		}
		String err = errHeader + "\r\n" + tr.getThrowable().getMessage() + "\r\n\t" +
				Arrays.toString(tr.getThrowable().getStackTrace()).replaceAll(",", "\r\n\t");
		LOGGER.error(err);
		Markup m = MarkupHelper.createLabel(errHeader, ExtentColor.RED);
		ExtentReportThread.getChildTest().fail(m);
		ExtentReportThread.getChildTest().fail(tr.getThrowable());
		System.out.println("----------------------END onConfigurationFailure---------------");
	}
*/
	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}
}