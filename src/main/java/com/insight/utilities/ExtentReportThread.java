package com.insight.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentReportThread {
	private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
	private static Map<Integer, ExtentTest> extentChildTestMap = new HashMap<>();
	private static ExtentReports extent = ExtentManager.getInstance();

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {
		ExtentTest test = extent.createTest(testName, desc);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}

	public static synchronized ExtentTest startTest(String testName) {
		return startTest(testName, "");
	}

	public static synchronized ExtentTest getChildTest() {
		return extentChildTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized ExtentTest startChildTest(String childTestName, String desc) {
		ExtentTest childTest = getTest().createNode(childTestName, desc);
		extentChildTestMap.put((int) (long) (Thread.currentThread().getId()), childTest);
		return childTest;
	}

	public static synchronized ExtentTest startChildTest(String childTestName) {
		return startChildTest(childTestName, "");
	}
}