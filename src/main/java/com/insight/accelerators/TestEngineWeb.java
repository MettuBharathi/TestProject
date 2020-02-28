package com.insight.accelerators;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;
import com.insight.report.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.insight.googledrive.ReportStatus;
import com.insight.report.BrowserContext;
import com.insight.report.CReporterWeb;
import com.insight.report.ConfigFileReadWrite;
import com.insight.report.ReporterConstants;
import com.insight.support.ExcelReader;
import com.insight.support.MyListener;
import com.insight.utilities.CustomTestListenerCT;
import com.insight.utilities.DataBean;
import com.insight.utilities.ExtentReportThread;
import com.insight.utilities.WriteIntoExcel;


@Listeners(CustomTestListenerCT.class)
public class TestEngineWeb {
	public static Logger LOG = Logger.getLogger(TestEngineWeb.class);	
	public WebDriver WebDriver = null;
	public static EventFiringWebDriver driver = null;
	public static CReporterWeb reporter = null;
	public ITestContext ctx = null;
	public static String gTestCaseDesc = null;
	public static String gErrorMessage=null;
	public static boolean gTestStatus=false;
	public static String callIDCreated = null;

	/* cloud platform */
	public String browser = null;
	public String version = null;
	public String platform = null;
	public String seleniumurl = null;
	public String environment = null;
	public static String Environment = null;
	public String localBaseUrl = null;
	public String userName = null;
	public String accessKey = null;
	public String cloudImplicitWait = null;
	public String cloudPageLoadTimeOut = null;
	public String updateJira = null;
	public String buildNumber = "";
	public String jobName = "";
	public String executedFrom = null;
	public String executionType = null;
	public String suiteExecution = null;
	public String suiteStartTime = null;
	public String APP_BASE_URL = null;
	public String SUMMARY_REPORTER_BASEURL = null;
	public String CALL_RECEIVING_URL = null;
	public String DISPATCH_URL = null;
	public String RSO_WEB_URL = null;
	public String RSO_WEB_URL_MESTAG = null;
	public String RSP_WEB_URL = null;
	public static String LOCATION_CLIENT_LOGO = null;

	public static long startTime;
	public static String fileName = System.getProperty("user.dir") + "/TestData/TestData.xls";
	public static ExcelReader xlsrdr = new ExcelReader(fileName);
	public static DataBean dataBean = new DataBean();
	public static WriteIntoExcel excelEntry = new WriteIntoExcel();
	public static ReportXML reportXML = null;
	public HashMap<String, String> mapObj = new HashMap<>();
	public static String platformNameMobile=null; 
	// Below parameters are for update the test data into Excel at run time
	public static String globalsheetName = null;
	public static int gTestCaseStartRowNum = 0;

	public static String gTestData = System.getProperty("user.dir") + File.separator + "TestData" + File.separator
			+ "TestData.xlsx";
	public static ArrayList<String> listofTestCaseDescription = new ArrayList<>();
	public static int i = 0;

	public static Hashtable<String, String> Global_DataTable = null;
	public static boolean blnResultStatus=false;

	
	
	
	private final String msgTypeSuccess = "Successfully Entered value ";
	private final String msgTypeFailure = "Unable To Type On ";
	public String app;
	public String appPackage;
	public String testName;
	public String platformName;
	public String platformVersion;
	public String deviceName;
	public String appActivity;
	public String udid;
	
	public String automationName;
	

	@Parameters({ "executionType", "suiteExecuted"})
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(ITestContext ctx, String type, String suite) throws Throwable {
		System.out.println("----------------------START beforeSuite---------------");
		startTime = System.currentTimeMillis();
		ctx.setAttribute("browser", System.getenv("Browsers"));
		LOG.info(System.getenv("Browsers"));
		LOG.info("--------------------------------------------------------------------------");
		LOG.info("------------------Suite :: " + suite + "------------------------------");
		LOG.info("Execution Start Time :: " + startTime);
		LOG.info("Test Data Path :: " + fileName);
		LOG.info("Executing on :: " + System.getenv("Browsers"));
		LOG.info("---------------------------End Suite Details-----------------------------------");
		executionType = type;
		suiteExecution = suite;
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/Log.properties");

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy hh mm ss SSS");
		String formattedDate = sdf.format(date);
		suiteStartTime = formattedDate.replace(":", "_").replace(" ", "_");
		LOG.info("Suite time ==============>" + suiteStartTime);
		ReportControl.fnReportDefault();
		
		System.out.println("----------------------END beforeSuite---------------");
	}

	@BeforeClass(alwaysRun = true)
	@Parameters({ "automationName", "browser", "browserVersion", "environment","platformName","autoStart", "seleniumurl"})
	public void beforeClass(String automationName, String browser, String browserVersion, String environment,
			String platformName,String autoStart,String seleniumurl,ITestContext iTestContext)
					throws IOException, InterruptedException {
		// set and get system property at before class
		System.out.println("----------------------START beforeClass---------------");
		String testName = iTestContext.getName();
		APP_BASE_URL = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "insightWebURL");
		SUMMARY_REPORTER_BASEURL = APP_BASE_URL;
		LOG.info("---------------------");
		LOG.info("-----Before Class----");
		LOG.info("---------------------");
		LOG.info("Execution Start Time :: " + startTime);
		LOG.info("browser name :: " + browser + " Browser version :: " + browserVersion + " platform ::" + platformName);
		this.browser = browser;
		this.version = browserVersion;
		this.platform = platformName;
		this.environment = environment;
		Environment = environment;
		
		this.seleniumurl = seleniumurl;
		this.localBaseUrl = ReporterConstants.APP_BASE_URL;	
		this.executedFrom = System.getenv("COMPUTERNAME");		
		this.testName=testName;
		this.platformName=platformName;	
		
		this.automationName=automationName;
		if(ReportControl.strAutoStart==null)
		{
			//ReportControl.strAutoStart=autoStart;
			ReportControl.fnIntilizeControl(autoStart);
		}
		ReportControl.intRowCount=1;
		LOCATION_CLIENT_LOGO = ReporterConstants.CES_LOCATION_CLIENT_LOGO;
		LOG.info(environment);
		if(ReportControl.strAutoStart.equals("web"))
		{
			if(ReportControl.blnJoin==false)
			{
				if(reporter==null)
				{
					reporter = CReporterWeb.getCReporter(browser, platformName, environment, true);
				}
				else
				{
					BrowserContext browserContext = BrowserContext.getBrowserContext(browser, version, platform);
					CReporterWeb.mapBrowserContextReporter.put(browserContext, reporter);
				}
			}
			else
			{
				if(reporter==null)
				{
					reporter = CReporterWeb.getCReporter(browser, platformName, environment, true);
				}
				else
				{
					if(reporter!=null)
					{
						BrowserContext browserContext = BrowserContext.getBrowserContext(browser, version, platform);
						CReporterWeb.mapBrowserContextReporter.put(browserContext, reporter);		        			
					}
				}
			}
			ReportControl.strCurrentExecution="web";
		}
		else if(ReportControl.strAutoStart.equals("mobile"))
		{
			if (environment.equalsIgnoreCase("local")) {
				if (platformNameMobile.equalsIgnoreCase("android") && !app.equalsIgnoreCase("NA")) {
					if(ReportControl.blnJoin==false)
					{
						if(reporter==null) 
						{
							reporter = CReporterWeb.getCReporter(deviceName, platformName,platformVersion, true);
						}
						else
						{
							BrowserContext browserContext = BrowserContext.getBrowserContext(browser, version, platform);
							CReporterWeb.mapBrowserContextReporter.put(browserContext, reporter);
						}
					}
					else
					{
						if(reporter==null)
						{
							reporter = CReporterWeb.getCReporter(deviceName, platformName, environment, true);
						}
						else
						{
							if(reporter!=null)
							{
								BrowserContext browserContext = BrowserContext.getBrowserContext(browser, version, platform);
								CReporterWeb.mapBrowserContextReporter.put(browserContext, reporter);
							}
						}
					}
					reporter.calculateSuiteStartTime();
				}
				else if (platformNameMobile.equalsIgnoreCase("ios") && !app.equalsIgnoreCase("NA")) {
					if(ReportControl.blnJoin==false)
					{
						if(reporter==null) 
						{
							reporter = CReporterWeb.getCReporter(deviceName, platformName,platformVersion, true);
						}
						else
						{
							BrowserContext browserContext = BrowserContext.getBrowserContext(browser, version, platform);
							CReporterWeb.mapBrowserContextReporter.put(browserContext, reporter);
						}
					}
					else
					{
						if(reporter==null)
						{
							reporter = CReporterWeb.getCReporter(deviceName, platformName, environment, true);
						}
						else
						{
							if(reporter!=null)
							{
								BrowserContext browserContext = BrowserContext.getBrowserContext(browser, version, platform);
								CReporterWeb.mapBrowserContextReporter.put(browserContext, reporter);
							}

						}
					}
					reporter.calculateSuiteStartTime();    			    			
				}
				ReportControl.strCurrentExecution="mobile";
			}
		}
		// set test for ExtentReports
		ExtentReportThread.startTest(testName);
		System.out.println("----------------------END beforeClass---------------");
	}

	public void fnOpenTest() throws IOException, InterruptedException
	{
		System.out.println("----------------------START fnOpenTest---------------");
		try
		{
			if(ReportControl.strAutoStart.equals("web"))
			{
				if (environment.equalsIgnoreCase("local")) {
					LOG.info("Selenium  URL" + seleniumurl);
					this.setWebDriverForLocal(browser, seleniumurl);
				}			
				
				driver = new EventFiringWebDriver(this.WebDriver);
				
					MyListener myListener = new MyListener();
					driver.register(myListener);
				
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				driver.get(SUMMARY_REPORTER_BASEURL);
				System.out.println("Driver is Open after Iteration");
			}
			
					
				   	    
			
			reporter.calculateSuiteStartTime();  
		}
		catch(Exception e)
		{
			LOG.info("---------Exception in fnOpenTest -----" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("----------------------END fnOpenTest---------------");
	}

	public void fnCloseTest() throws Throwable
	{
		System.out.println("----------------------START fnCloseTest---------------");
		try
		{
			if(ReportControl.strCurrentExecution.equals("web"))
			{
				//driver.close();
				driver.quit();
				LOG.info("Driver quit ::" + browser);
				System.out.println("Driver is close after Iteration");
			}
			
		}
		catch(Exception e)
		{
			LOG.info("#---------fnCloseTest-----------------------# "+e.getMessage());
		}
		System.out.println("----------------------END fnCloseTest---------------");
		driver.quit();
		reporter.SuccessReport("END OF THE TEST : ", "************ END OF THE TEST ********************","");
	}


	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void afterClass(String browser) throws Throwable {
		System.out.println("----------------------START afterClass---------------");
		 if(ReportControl.strCurrentExecution.equals("web"))
		{
				System.out.println("Browser closed at fnClose method");
		}
		System.out.println("----------------------END afterClass---------------");
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		System.out.println("----------------------START beforeMethod---------------");
		int k;
		HashMap<String, Integer> tcDescriptionMapObject = new HashMap<>();
		for (int j = 0; j < listofTestCaseDescription.size(); j++) {
			if (tcDescriptionMapObject.get(listofTestCaseDescription.get(j).replace(" ", "")) == null)
				tcDescriptionMapObject.put(listofTestCaseDescription.get(j).replace(" ", ""), j);
		}
		if (listofTestCaseDescription.size() == 0) {
			k = 0;
		} else {
			k = tcDescriptionMapObject.get(listofTestCaseDescription.get(i).replace(" ", ""));
			i = i + 1;
		}
		if(ReportControl.blnJoin==false)
		{
			reporter.initTestCase(this.getClass().getName().substring(0, this.getClass().getName().lastIndexOf(".")),
					method.getName() + "-" + k, null, true);
		}
		ReportStatus.strMethodName=method.getName();
		ReportStatus.blnStatus=true;
		gTestStatus=true;
		gErrorMessage="";
		if(reportXML == null) {
			reportXML = new ReportXML();
			reportXML.setBrowserContext(reporter);
			reportXML.createDirectoryForReports();
			reportXML.checkTestCaseXML();
		}
		System.out.println("----------------------END beforeMethod---------------");
	}

	@AfterMethod
	public void afterMethod() throws Throwable {
		System.out.println("----------------------START afterMethod---------------");
		LOG.info("After method executing...");
		if(ReportControl.blnJoin==false)
		{
			reporter.calculateTestCaseExecutionTime();
			reporter.closeDetailedReport();
			reporter.updateTestCaseStatus();
			//Method to store test case execution details in xml
			reportXML.writeTestCaseResultToXML();
		}
		if (i == 0) {
			TestEngineWeb.reporter.initTestCaseDescriptionUpdated(
					gTestCaseDesc /*listofTestCaseDescription.get(i)*/ );
		} else {
			TestEngineWeb.reporter.initTestCaseDescriptionUpdated( gTestCaseDesc /*listofTestCaseDescription.get(i - 1)*/);
		}
		LOG.info("The value of the path is " + gTestData);
		LOG.info("The value of test case description" + gTestCaseDesc);
		LOG.info("Test Execution Status, Test Passed ?"+  gTestStatus);
		dataBean.cleanMapObj();
		
		
		System.out.println("----------------------END afterMethod---------------");
	}

	public void setWebDriverForLocal(String browser, String seleniumurl) throws IOException, InterruptedException {
		 System.out.println("----------------------START setWebDriverForLocal---------------");
		 
		 switch (browser) {
		 case "firefox":
		  
		  try{
		   System.setProperty("webdriver.gecko.driver", "Drivers\\geckodriver.exe");
		  }catch(Exception ex){
		   ex.printStackTrace(); 
		  }
		  if (seleniumurl.equalsIgnoreCase("local")) {
		   this.WebDriver = new FirefoxDriver();
		  }
		  LOG.info("Driver launch ::" + browser);
		  break;
		 case "ie":
		  
		  File file = new File("Drivers\\IEDriverServer.exe");
		  System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		  
		  if (seleniumurl.equalsIgnoreCase("local")) {
		   this.WebDriver = new InternetExplorerDriver();
		  }
		  break;
		 case "chrome":   
			  if(System.getProperty("os.name").toLowerCase().contains("windows")){
			   System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
			  }
			  if (seleniumurl.equalsIgnoreCase("local")) {
				  DesiredCapabilities capab;
				  HashMap<String, Object> chromePrefs = new HashMap<>();
					chromePrefs.put("profile.default_content_settings.popups", 0);
					System.out.println("PathDownload"+System.getProperty("user.dir"));
					chromePrefs.put("download.default_directory", System.getProperty("user.dir") + "\\" + "DownloadedFiles");
					capab = DesiredCapabilities.chrome();
					
					if(System.getProperty("os.name").toLowerCase().contains("windows")){
						//System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver_32Bit.exe");
					}
					ChromeOptions options = new ChromeOptions();
					
					capab.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					options.addArguments("--start-maximized");
					options.addArguments("test-type");
					options.addArguments("disable-infobars");
					options.addArguments("chrome.switches", "--disable-extensions");
					options.setExperimentalOption("prefs", chromePrefs);
					capab.setCapability(ChromeOptions.CAPABILITY, options);
			   this.WebDriver = new ChromeDriver(capab);
			  }
			  LOG.info("Driver launch ::" + browser);
			  break;
		 case "edge":
		  LOG.info("In the case Edge");
		  System.setProperty("webdriver.edge.driver", "Drivers\\MicrosoftWebDriver.exe");
		  if (seleniumurl.equalsIgnoreCase("local")) {
		   this.WebDriver = new EdgeDriver();
		  }
		  LOG.info("Driver launch ::" + browser);
		  break;
		 case "safari":
		  for (int i = 1; i <= 10; i++) {
		   try {
		    if (seleniumurl.equalsIgnoreCase("local")) {
		     this.WebDriver = new SafariDriver();
		    }
		    break;
		   } catch (Exception e1) {
		    e1.printStackTrace();
		   }
		  }
		 }
		   
		 System.out.println("----------------------END setWebDriverForLocal---------------");
		}	

	@Parameters({ "executionType", "suiteExecuted" })
	@AfterSuite(alwaysRun = true)
	public void afterSuite(ITestContext ctx, String type, String suite) throws Throwable {
		System.out.println("----------------------START afterSuite---------------");
		startTime = System.currentTimeMillis();
		ctx.setAttribute("browser", System.getenv("Browsers"));
		LOG.info("--------------------------------------------------------------------------");
		LOG.info("------------------Suite :: " + suite + "------------------------------");
		LOG.info("AfterSuite Start Time :: " + startTime);
		LOG.info("---------------------------End After Suite Details-----------------------------------");
		reporter.calculateSuiteExecutionTime();
		reportXML.createXMLForSummaryReport();
		reporter.createHtmlSummaryReport(SUMMARY_REPORTER_BASEURL, true);
		reporter.closeSummaryReport();
		reportXML.copyCompleteFolderForAjaxReporting();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy hh mm ss SSS");
		String formattedDate = sdf.format(date);
		suiteStartTime = formattedDate.replace(":", "_").replace(" ", "_");
		LOG.info("After Suite end time ==============>" + suiteStartTime);
		LOG.info("Before killing " + browser + " browser");
		LOG.info("After killing " + browser + " browser");
		System.out.println("----------------------END afterSuite---------------");
	}

	public boolean keyBoardOperations(By locator, Keys testData, String locatorName) throws Throwable {
		boolean status;
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			LOG.info("Method : Type  ::  Locator : " + locatorName + " :: Data :" + testData);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			LOG.info("Waiting for element :");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			LOG.info("Locator is Visible :: " + locator);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			
			LOG.info("Typed the Locator data :: " + testData);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

			reporter.SuccessReport("Enter text in :: " + locatorName, msgTypeSuccess +testData,"");
			status = true;
		} catch (Exception e) {
			status = false;
			LOG.info(e.getMessage());
			reporter.failureReport("Enter text in :: " + locatorName, msgTypeFailure + testData,"", driver);
		}
		return status;
	}

	

	public EventFiringWebDriver createDriver() throws InterruptedException, IOException{
		LOG.info("----------------------START createDriver---------------");
		DesiredCapabilities capab;
		WebDriver newDriver = null;
		switch (browser) {
		case "firefox":
			capab = DesiredCapabilities.firefox();
			System.setProperty("webdriver.gecko.driver", "Drivers\\geckodriver.exe");
			final FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.download.dir", System.getProperty("user.dir") + "\\Downloads");
			firefoxProfile.setPreference("browser.download.folderList", 2);
			firefoxProfile.setPreference("browser.helperApps.neverAsk.openFile", "application/octet-stream");
			firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;" + "application/pdf;"
							+ "application/vnd.openxmlformats-officedocument.wordprocessingml.document;" + "text/plain;"
							+ "text/csv;" + "application/octet-stream");
			firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
			firefoxProfile.setPreference("pdfjs.disabled", true);
			firefoxProfile.setPreference("xpinstall.signatures.required", false);
			capab.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
			newDriver = new EventFiringWebDriver(new FirefoxDriver(capab));
		case "ie":
			capab = DesiredCapabilities.internetExplorer();
			File file = new File("Drivers\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			// To disable popup blocker.
			capab.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
			// to enable protected mode settings
			capab.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capab.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			// to get window focus
			capab.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			// to set zoom level is set to 100% so that the native mouse events
			// can be set to the correct coordinates.
			capab.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capab.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			capab.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
			Process p = Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
			p.waitFor();
			newDriver = new EventFiringWebDriver(new InternetExplorerDriver(capab));
			break;
		case "chrome":
			HashMap<String, Object> chromePrefs = new HashMap<>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", System.getProperty("user.dir") + "\\Downloads");
			capab = DesiredCapabilities.chrome();
			
			if(System.getProperty("os.name").toLowerCase().contains("windows")){
				System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver_32Bit.exe");
			}
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			capab.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.addArguments("--start-maximized");
			options.addArguments("test-type");
			options.addArguments("disable-infobars");
			options.addArguments("chrome.switches", "--disable-extensions");
			capab.setCapability(ChromeOptions.CAPABILITY, options);
			newDriver = new EventFiringWebDriver(new ChromeDriver(capab));
			break;
		}
		LOG.info("----------------------START createDriver---------------");
		return (EventFiringWebDriver) newDriver;
	}




	private void setRemoteWebDriverForCloudSauceLabs(String testName) throws IOException, InterruptedException {
		String testBuild = System.getProperty("automation.name");
		String systemUserName = System.getProperty("saucelabs.user.name");
		String systemAccessKey = System.getProperty("saucelabs.access.key");
		String userName = systemUserName == null ? "test" : systemUserName;
		String accessKey = systemAccessKey == null ? "test" : systemAccessKey;
		String url = "https://" + userName + ":" + accessKey + "@ondemand.saucelabs.com:443/wd/hub";
		DesiredCapabilities desiredCapabilities;
		switch (this.browser.toLowerCase()) {
			case "safari":
				desiredCapabilities = DesiredCapabilities.safari();
				break;
			case "edge":
				desiredCapabilities = DesiredCapabilities.edge();
				break;
			case "ie":
				desiredCapabilities = DesiredCapabilities.internetExplorer();
				break;
			case "firefox":
				desiredCapabilities = DesiredCapabilities.firefox();
				break;
			case "chrome":
				desiredCapabilities = DesiredCapabilities.chrome();
				break;
			default:
				desiredCapabilities = DesiredCapabilities.chrome();
				break;

		}
		desiredCapabilities.setCapability(CapabilityType.VERSION, this.version);
		desiredCapabilities.setCapability(CapabilityType.PLATFORM, this.platform);
		desiredCapabilities.setCapability("build", testBuild);
		desiredCapabilities.setCapability("name", testName);
		desiredCapabilities.setCapability("requireWindowFocus", true);
		desiredCapabilities.setCapability("recordVideo", "true");
		desiredCapabilities.setCapability("recordScreenshots", "false");
		desiredCapabilities.setCapability("maxDuration", 10800);
		this.WebDriver = new RemoteWebDriver(new URL(url), desiredCapabilities);
	}

	private void updateConfigurationForCloudSauceLabsJenkins() {
		this.browser = System.getenv("Browsers");
		this.version = System.getenv("SELENIUM_VERSION");
		this.platform = System.getenv("SELENIUM_PLATFORM");
		this.userName = System.getenv("SAUCE_USER_NAME");
		this.accessKey = System.getenv("SAUCE_API_KEY");
		this.buildNumber = System.getenv("BUILD_NUMBER");
		this.jobName = System.getenv("JOB_NAME");
		/* For Debug Purpose */
		LOG.info("Debug: browser = " + this.browser);
		LOG.info("Debug: version = " + this.version);
		LOG.info("Debug: platform = " + this.platform);
		LOG.info("Debug: userName = " + this.userName);
		LOG.info("Debug: accessKey = " + this.accessKey);
		LOG.info("Debug: executedFrom = " + this.executedFrom);
		LOG.info("Debug: BUILD_NUMBER = " + this.buildNumber);
		LOG.info("Debug: jobName = " + this.jobName);
	}

	/* TBD: Not Implemented For Running Using Jenkins */
	private void updateConfigurationForCloudBrowserStackJenkins() {
	}


}
