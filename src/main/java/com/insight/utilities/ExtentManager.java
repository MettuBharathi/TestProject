package com.insight.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExtentManager {
	private static ExtentReports extent;
	private static final String ROOT = System.getProperty("user.dir");
	private static final String SEPARATOR = File.separator;
	private static final String FILE_NAME = "extent.html";
	private static final Logger LOGGER = LogManager.getLogger(ExtentManager.class);

	public static ExtentReports getInstance() {
		if (extent == null) {
			String filePath = ROOT + SEPARATOR + "results" + SEPARATOR + "test-output";
			Path path = Paths.get(filePath);
			//if directory exists?
			if (!Files.exists(path)) {
				try {
					Files.createDirectories(path);
				} catch (IOException e) {
					//fail to create directory
					LOGGER.error(e.getMessage());
				}
			}
			createInstance(path + SEPARATOR + FILE_NAME);
		}
		return extent;
	}

	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("ExtentReports - Created by TestNG Listener");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("ExtentReports - Created by TestNG Listener");
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}


}