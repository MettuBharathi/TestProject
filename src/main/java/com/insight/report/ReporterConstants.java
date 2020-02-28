package com.insight.report;

public interface ReporterConstants {
	String configReporterFile = "resources/cignitiReporter.properties";

	/*Test case status as pass*/
	String TEST_CASE_STATUS_PASS = "PASS";
	/*Test case status as fail*/
	String TEST_CASE_STATUS_FAIL = "FAIL";
	/*Client Name Being Used In Report*/
	String CLIENT_NAME = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "clientName");
	/*Device Name Being Used In Report*/
	String DEVICE_NAME = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "deviceName");
	/*Iteration Mode Being Used In Report*/
	String ITERAION_MODE = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "iterationMode");
	/*suite name */
	String SUITE_NAME = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "suiteName");
	/*on error action*/
	String ON_ERROR_ACTION = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "onErrorAction");
	/*Whether Being Run On Single Browser Or Multiple Browser In Multiple Threads*/
	Boolean BOOLEAN_SINGLE_THREAD = Boolean.parseBoolean(ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "singleThreadExecution"));
	/*application under test*/
	String APP_UNDER_TEST = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "AUT");
	/*base url of web application*/
	String APP_BASE_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "baseUrl");
	String CES_BASE_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "showroomUrl");
	
	

	
	String Timeout = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "Waittime");
	String DYNAMIC_TIMEOUT = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "DynamicWait");
	String MIN_TIMEOUT = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "MinWait");

	
	String INSIGHT_WEB_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "insightWebUrl");

	 String CMT_ADMIN_USERNAME =  ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "cmtUserName");

	     String CMT_ADMIN_PASSWORD =  ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "cmtPassWord");
	     String EMAIL_ID =  ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "emailID");
	/*browserName*/
	String BROWSER_NAME = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "browserName");
	/*version*/
	String BROWSER_VERSION = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "version");
	/*platform*/
	String BROWSER_PLATFORM = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "platform");
	/*ReportFormat*/
	String REPORT_FORMAT = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "reportFormat");
	/*#location of result*/
	String LOCATION_RESULT = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "locationResult");
	/*screenshot folder*/
	String FOLDER_SCREENRSHOTS = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "folderScreenShot");
	/*client logo*/
	String LOCATION_CLIENT_LOGO = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "locationClientLogo");
	/*Mobile logo*/
	String LOCATION_MOBILE_LOGO = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "locationMobileLogo");
	/*client logo*/
	String CES_LOCATION_CLIENT_LOGO = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "showroomClientLogo");
	/*#company logo*/
	String LOCATION_COMPANY_LOGO = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "locationCompanyLogo");
	/*#failed logo*/
	String LOCATION_FAILED_LOGO = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "locationFailedLogo");
	/*#location minus logo*/
	String LOCATION_MINUS_LOGO = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "locationMinusLogo");
	/*#location passed logo*/
	String LOCATION_PASSED_LOGO = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "locationPassedLogo");
	/*#location plus logo*/
	String LOCATION_PLUS_LOGO = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "locationPlusLogo");
	/*#location warning logo*/
	String LOCATION_WARNING_LOGO = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "locationWarningLogo");
	/*screen shot to be taken if test step is pass */
	Boolean BOOLEAN_ONSUCCESS_SCREENSHOT = Boolean.parseBoolean(ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "onSuccessScreenshot"));
	/*saucelabs user name*/
	String SAUCELAB_USERNAME = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "userName");
	/*saucelabs accessKey*/
	String SAUCELAB_ACCESSKEY = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "accessKey");
	/*cloudImplicitWait*/
	String CLOUD_IMPLICIT_WAIT = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "cloudImplicitWait");
	/*cloudPageLoadTimeOut*/
	String CLOUD_PAGELOAD_TIMEOUT = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "cloudPageLoadTimeOut");
	/*css folder*/
	String LOCATION_JQUERY_CSS_FOLDER = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "locationCssFolder");
	/*js folder*/
	String LOCATION_JQUERY_JS_FOLDER = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "locationJsFolder");
	/*locationImagesFolder*/
	String LOCATION_JQUERY_IMAGES_FOLDER = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "locationImagesFolder");
	String CALL_RECEIVING_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "callReceivingURL");
	String DISPATCH_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "dispatchURL");
	String RSO_WEB_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "RSOWebURL");
	String RSO_WEB_URL_MESTAG = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "RSOWebURLMestag");
	String CARVING_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "Carving");
	String SERVICETRACKER_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "ServiceTracker");
	String SERVICETRACKERQUICKLAUNCH_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "ServiceTrackerQuickLaunch");

}
