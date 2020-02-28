package com.insight.report;

import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Class Name: ReportXML
 */

public class ReportXML {
    private static final Logger LOG = Logger.getLogger(ReportXML.class);
    private static BrowserContext browserContext = null;
    private static String browserName = null;
    private static String strDate = null;
    private static String osName = System.getProperty("os.name").toLowerCase();
    private static String reportDirName = "REPORT_XML";
    public static String strReportPath = ReporterConstants.LOCATION_RESULT + File.separator + reportDirName;
    public static String backupFolderName = "Backup";
    public static String htmlReportsFolderName = "HTMLReports";
    public static String LogoFolderName = "Logos";
    private static String destFol = "C:\\xampp\\htdocs\\Report";
    private static String detailReporterXSL = "resources/page.xsl";
    private static String logosFolder = "resources/Logos";
	private static String summaryHTML = "resources/ISummaryReport.html";
    private static String summaryReporterXSL = "resources/summary.xsl";
    public static List<String> failedTestCaseList = new ArrayList<String>();

    /**
     * Initialise browserContext
     *
     * @param cReporterweb
     */
    public BrowserContext setBrowserContext(CReporterWeb cReporterweb) {
        browserContext = cReporterweb.browserContext;
        //BrowserContext.getBrowserContext(browserName, version, platform);
        browserName = browserContext.getBrowserName();
        //BrowserContext browserContext = BrowserContext.getBrowserContext(browser, version, platform);
        LOG.info("browserContext set to : " + browserContext);
        return browserContext;
    }

    /**
     * copy logo's to screenshots folder
     * @param logos A variable of type array
     */
    private static void copyLogos(String... logos) {
        String sReportPath = ReportXML.strReportPath + "_" + browserName + "-" + strDate;
        File destFolder = new File(sReportPath + File.separator + htmlReportsFolderName +
                File.separator + ReporterConstants.FOLDER_SCREENRSHOTS);
        String logosFolders = logosFolder;
        File SourceLogoFolder= new File(logosFolders);
        Path sourceHTML= Paths.get(summaryHTML);

        File file = new File(sReportPath+File.separator+LogoFolderName);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        File destinationFolder = file;

        try {
    Path targetDirectory = Paths.get(sReportPath+"\\"+sourceHTML.getFileName());


    //copy source to target using Files Class
            FileUtils.copyDirectory(SourceLogoFolder, destinationFolder);
            Files.copy(sourceHTML, targetDirectory);
}
catch (Exception ex){
LOG.info("Exception dir" + ex.getMessage());
        }
        if (destFolder.exists() == false) {
            try {
                destFolder.mkdirs();
            } catch (Exception ex) {
                LOG.info("Exception in creating screenhots dir : " + ex.getMessage());
            }
        }

        for (String logo : logos) {
            LOG.info("Current logo : " + logo);
            File logoFile = new File(logo);

            if (logoFile.isDirectory()) {
                try {
                    FileUtils.copyDirectoryToDirectory(logoFile, destFolder);
                    LOG.info(logoFile + "copied to " + destFolder);
                } catch (IOException e) {
                    LOG.info(logoFile + "could not be copied to " + destFolder);
                    LOG.info("IOException Encountered : " + e.getMessage());
                    e.printStackTrace();
                }
            }

            if (logoFile.isDirectory() == false) {
                if (logoFile.exists()) {
                    try {
                        FileUtils.copyFileToDirectory(logoFile, destFolder);
                        LOG.info(logoFile + "copied to " + destFolder);
                    } catch (IOException e) {
                        LOG.info(logoFile + "could not be copied to " + destFolder);
                        LOG.info("IOException encountered : " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Method to capture screen shot
     * @param driver
     * @param fileName
     */
    private static void screenShot(WebDriver driver, String fileName) {
        WebDriver driverScreenShot = new Augmenter().augment(driver);
        File scrFile = ((TakesScreenshot) driverScreenShot).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to create new directory for storing xml's
     */
    public static void createDirectoryForReports() {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //strDate = formatter.format(today).replace(":", "_").replace(" ", "_");
        strDate = formatter.format(today);
        String sReportPath = ReportXML.strReportPath + "_" + browserName + "-" + strDate;
        File resultDir = new File(sReportPath);
        LOG.info("resultDir = " + resultDir);

        if (!resultDir.exists()) {
            try {
                resultDir.mkdirs();
            } catch (Exception e) {
                LOG.info("Exception in creating directory for Reports : " + e.getMessage());
            }

            File screenShotDir = new File(ReporterConstants.LOCATION_RESULT +
                    File.separator + reportDirName + "_" + browserName + "-" +
                    strDate + File.separator + htmlReportsFolderName +
                    File.separator + ReporterConstants.FOLDER_SCREENRSHOTS);

            if (screenShotDir.exists() == false) {
                try {
                    LOG.info("inside screenshot dir exists");
                    screenShotDir.mkdirs();
                    copyLogos(ReporterConstants.LOCATION_CLIENT_LOGO,
                            ReporterConstants.LOCATION_COMPANY_LOGO,
                            ReporterConstants.LOCATION_FAILED_LOGO,
                            ReporterConstants.LOCATION_MINUS_LOGO,
                            ReporterConstants.LOCATION_PASSED_LOGO,
                            ReporterConstants.LOCATION_PLUS_LOGO,
                            ReporterConstants.LOCATION_WARNING_LOGO,
                            ReporterConstants.LOCATION_JQUERY_CSS_FOLDER,
                            ReporterConstants.LOCATION_JQUERY_IMAGES_FOLDER,
                            ReporterConstants.LOCATION_JQUERY_JS_FOLDER);
                } catch (Exception e) {
                    LOG.info("Exception Encountered : " + e.getMessage());
                }
            }
        }
    }

    /**
     * Method to append Test case results to XML
     */
    public void writeTestCaseResultToXML() throws Throwable {
        try {
            Document doc = null;
            Node root = null;
            String fileType = "xml";

            //create a new DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // create document builder object
            DocumentBuilder builder = factory.newDocumentBuilder();

            String tcName = TestResult.tc_name.get(this.browserContext);
            LOG.info("test case name : " + tcName);
//            LOG.info("Test case description : " + TestResult.testDescription.get(ReportXML.browserContext).get(TestResult.tc_name.get(ReportXML.browserContext)));
            String testCaseDesc = TestResult.testDescription.get(ReportXML.browserContext).get(TestResult.tc_name);
            String browserName = ReportXML.browserContext.getBrowserName();
            String sReportPath = ReportXML.strReportPath + "_" + browserName + "-" + strDate;

            File file = new File(sReportPath + File.separator + ReportStatus.strMethodName + "." + fileType);

            if (file.exists()) {
                System.out.println("in file exists,  file name : " + file.getName());
                FileInputStream inputStream = new FileInputStream(file);
                InputSource input = new InputSource(inputStream);

                //create a new document from input source
                doc = builder.parse(file);
                root = doc.getFirstChild();

                Element testCaseElement = doc.createElement("TestCase");
                root.appendChild(testCaseElement);

                // Browser Name
                Element browser = doc.createElement("Browser");
                browser.appendChild(doc.createTextNode(browserName));
                testCaseElement.appendChild(browser);

                //Browser Version
                Element version = doc.createElement("Version");
                version.appendChild(doc.createTextNode(ReporterConstants.BROWSER_VERSION));
                testCaseElement.appendChild(version);

                // OS name
                Element osName = doc.createElement("OS");
                osName.appendChild(doc.createTextNode(ReportXML.osName));
                testCaseElement.appendChild(osName);

                // Test Case Start time
                String todayDate = java.time.LocalDate.now().toString();
                Element tcStartTime = doc.createElement("ExecutionDate");
                tcStartTime.appendChild(doc.createTextNode(todayDate));
                testCaseElement.appendChild(tcStartTime);

                // Host Name
                Element hostName = doc.createElement("HostName");
                hostName.appendChild(doc.createTextNode(InetAddress.getLocalHost().getHostName()));
                testCaseElement.appendChild(hostName);

                //Test Case Name
                Element testCaseName = doc.createElement("TestCaseName");
                //testCaseName.appendChild(doc.createTextNode(TestResult.tc_name.get(ReportXML.browserContext)));
                testCaseName.appendChild(doc.createTextNode(ReportStatus.strMethodName));
                testCaseElement.appendChild(testCaseName);
                //Test Case Description
                Element testCaseDescription = doc.createElement("TestCaseDescription");
                testCaseDescription.appendChild(doc.createTextNode(TestResult.testDescription.get(ReportXML.browserContext).get(TestResult.tc_name.get(ReportXML.browserContext))));
                testCaseElement.appendChild(testCaseDescription);
                //Pass Step count
                Element stepsPassed = doc.createElement("PassCount");
                stepsPassed.appendChild(doc.createTextNode(String.valueOf(TestResult.PassNum.get(ReportXML.browserContext))));
                testCaseElement.appendChild(stepsPassed);

                //Fail Step count
                Element stepsFailed = doc.createElement("FailCount");
                stepsFailed.appendChild(doc.createTextNode(String.valueOf(TestResult.FailNum.get(ReportXML.browserContext))));
                testCaseElement.appendChild(stepsFailed);

                // Test case execution time
                Element executionTime = doc.createElement("TestCaseExecutionTimeInSeconds");
                String timeInSec = TestResult.executionTime.get(ReportXML.browserContext).get(TestResult.tc_name.get(ReportXML.browserContext)) + " " + "seconds";
                executionTime.appendChild(doc.createTextNode(timeInSec));
                testCaseElement.appendChild(executionTime);

                // Test case status
                Element status = doc.createElement("Status");
                String testCaseStatus = TestResult.testResults.get(ReportXML.browserContext).get(
                        TestResult.tc_name.get(ReportXML.browserContext));
                status.appendChild(doc.createTextNode(testCaseStatus));
                testCaseElement.appendChild(status);

                //write the content to xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new FileWriter(file));
                transformer.transform(source, result);
                LOG.info("Test Case details saved");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to check if the test case xml exists
     */
    public void checkTestCaseXML() {
        String fileType = "xml";
        String tcName = ReportStatus.strMethodName;
        String summaryXMLName = "SummaryResults";
        LOG.info("tcName : " + tcName);
        String browserName = ReportXML.browserContext.getBrowserName();
        String sReportPath = ReportXML.strReportPath + "_" + browserName + "-" + strDate;

        File file = new File(sReportPath + File.separator + tcName + "." + fileType);
        File summaryFile = new File(sReportPath + File.separator + summaryXMLName + "." + fileType);
        if (file.exists()) {
            file.delete();
        }

        if(summaryFile.exists()) {
            summaryFile.delete();
        }
    }

    /**
     * Converts XML files to HTML reports and stores in specified path
     */
    public void convertXMLToHTML() {
        String sReportPath = ReportXML.strReportPath + "_" + browserName + "-" + strDate;
        String htmlReportPath = sReportPath + File.separator + htmlReportsFolderName;
        String destinationPath = ReporterConstants.LOCATION_RESULT + File.separator + backupFolderName;
        List<String> xmlFilesList = new ArrayList<>();
        List<String> htmlFilesList = new ArrayList<>();
        File reportsFolder = new File(sReportPath);
        File htmlReportsFolder = new File(htmlReportPath);
        String fromFileType = "xml";
        String toFileType = "html";
        StringWriter stringWriter = null;
        Date today = new Date();
        SimpleDateFormat formatObj = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String todaysDate = formatObj.format(today).replace(":", "_").replace(" ", "_");

        Source xsltDetail = new StreamSource(detailReporterXSL);
        Source xsltSummary = new StreamSource(summaryReporterXSL);
        TransformerFactory tFactory = TransformerFactory.newInstance();

        //Get a list of all xml files and existing html files
        if (reportsFolder.exists() & reportsFolder.isDirectory()) {
            xmlFilesList = listAllFiles(reportsFolder, fromFileType);
        }

        if(htmlReportsFolder.exists() & htmlReportsFolder.isDirectory()) {
            htmlFilesList = listAllFiles(htmlReportsFolder, toFileType);
        }

        File destinationFolder = new File(destinationPath);
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        if (xmlFilesList.size() > 0) {
            for (String file : xmlFilesList) {
                String sourceFileName = new File(file).getName();

                //move html report to Backup folder if exists already
                if (htmlFilesList.size() > 0) {
                    for (String fileName : htmlFilesList) {
                        if (removeExtension(sourceFileName).equals(removeExtension(fileName))) {
                            String fileNameWithDateTime = removeExtension(fileName) + "_" + todaysDate + "." + toFileType;
                            if (destinationFolder.exists() & destinationFolder.isDirectory()) {
                                try {
                                    Files.move(Paths.get(htmlReportPath + File.separator + fileName),
                                            Paths.get(destinationPath + File.separator + fileNameWithDateTime),
                                            StandardCopyOption.REPLACE_EXISTING);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }

                if (sourceFileName.contains("Summary")) {
                    StreamSource source = new StreamSource(new File(sReportPath + File.separator + sourceFileName));
                    stringWriter = new StringWriter();
                    try {
                        File fileObj = new File(htmlReportPath + File.separator + removeExtension(sourceFileName) + "." + toFileType);
                        if (!fileObj.exists()) {
                            fileObj.createNewFile();
                        }

                        FileWriter fw = new FileWriter(fileObj);
                        Transformer transformerObj = tFactory.newTransformer(xsltSummary);
                        transformerObj.transform(source, new StreamResult(stringWriter));
                        fw.write(stringWriter.toString());
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TransformerConfigurationException e) {
                        e.printStackTrace();
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    }
                } else {
                    StreamSource source = new StreamSource(new File(sReportPath + File.separator + sourceFileName));
                    stringWriter = new StringWriter();
                    try {
                        File fileObj = new File(htmlReportPath + File.separator + removeExtension(sourceFileName) + "." + toFileType);
                        if (!fileObj.exists()) {
                            fileObj.createNewFile();
                        }

                        FileWriter fw = new FileWriter(fileObj);
                        Transformer transformerObj = tFactory.newTransformer(xsltDetail);
                        transformerObj.transform(source, new StreamResult(stringWriter));
                        fw.write(stringWriter.toString());
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TransformerConfigurationException e) {
                        e.printStackTrace();
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    }
                }
            }
            LOG.info("converted all xml files to html files");
        }
    }

    /**
     * Removes extension from file name and returns the file name
     * @param fileName
     * @return
     */
    private static String removeExtension(String fileName) {
        if (fileName == null) return null;

        int position = fileName.lastIndexOf(".");
        if (position == -1) {
            return fileName;
        } else {
            return fileName.substring(0, position);
        }
    }

    /**
     * Store the test case execution details step wise
     *
     * @param stepNum         A variable of type integer
     * @param strStepName     A variable of type String
     * @param strStepDesc     A variable of type String
     * @param input           A variable of type String
     * @param strStepExecTime A variable of type String
     * @param status          A variable of type String
     */
    public static void captureTestStepDetails(int stepNum, String strStepName, String strStepDesc, String input,
                                              String strStepExecTime, String status) {
        try {
            String fileType = "xml";
            String imageFileType = "jpeg";
            String imgSrc = "";
            String href = "";
            int startIndex = ReporterConstants.LOCATION_FAILED_LOGO.lastIndexOf('/');
            String sReportPath = ReportXML.strReportPath + "_" + browserName + "-" + strDate;
            String a="";
            String b="";
            String c="";
            String imgPath="";

            LOG.info("step description : " + strStepDesc);

            if (status == "FAIL") {
            /*
            Modify the test step description to replace special characters
            */
                String imgFileDesc = strStepDesc;
                imgFileDesc = imgFileDesc.replaceAll(":", "_");
                imgFileDesc = imgFileDesc.replaceAll("&", "_");
                imgFileDesc = imgFileDesc.replaceAll(",", "_");
                imgFileDesc = imgFileDesc.replaceAll(" ", "_");
                imgFileDesc = imgFileDesc.replaceAll("<b>", "").replaceAll("</b>", "");
                href = href + imgFileDesc + "." + imageFileType;
                imgSrc = imgSrc + ReporterConstants.LOCATION_FAILED_LOGO.substring(startIndex + 1);
                String anc="<";
                String les=">";
                a ="![CDATA[";
                b="a" + " href=\"screenshots/"+ href +"\"";
                c= "i style=\"width:30px;color:red\" class=\"fa fa-close fa-3x\"";
                imgSrc=a+b+c;
                imgSrc= "<"+a+"<"+b+">"+"<"+c+">"+"<"+"/i"+">"+"<"+"/a>"+"]]"+">";
                System.out.println(imgSrc);
            }
//<![CDATA[<i style="width:30px;color:red" class="fa fa fa-close fa-2x"></i>]]>
 // &lt;![CDATA[&lt;i style="width:30px;color:green" class="fa fa-check-circle fa-3x"&gt;&lt;/i&gt;]]&gt;
            if (status == "PASS") {
               // imgSrc = imgSrc + ReporterConstants.LOCATION_PASSED_LOGO.substring(startIndex + 1);
            	 a ="![CDATA[";
            	String d = c= "i style=\"width:30px;color:green\" class=\"fa fa-check-circle fa-3x\"";
                imgSrc = "<![CDATA[<i style=\"width:30px;color:green\" class=\"fa fa-check-circle fa-3x\"></i>]]>";
                imgSrc="<"+a+"<"+d+">"+"<"+"/i"+">"+"]]"+">";
                
            }

            if (status == "WARNING") {
                imgSrc = imgSrc + ReporterConstants.LOCATION_WARNING_LOGO.substring(startIndex + 1);
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = null;
            Node root = null;

            File file = new File(sReportPath + File.separator + ReportStatus.strMethodName + "." + fileType);
            LOG.info("Test method name : " + ReportStatus.strMethodName);

            if (file.exists() == false) {
                //create a new document
                doc = builder.newDocument();
                //root element : TestCaseDetails
                Element testCaseDetails = doc.createElement("TestCaseDetails");
                doc.appendChild(testCaseDetails);
                root = doc.getFirstChild();
            } else {
                FileInputStream inputStream = new FileInputStream(file);
                InputSource inputSource = new InputSource(inputStream);
                //create a new document from input source
                doc = builder.parse(inputSource);
                root = doc.getFirstChild();

            }
           /* NodeList node = document.getElementsByTagName("TestCase");
            Node nodeData = node.item(index);
            Element tcData = (Element) nodeData;
            String tcName = tcData.getElementsByTagName("TestCaseName").item(index).getTextContent();
            */
            String tcName = ReportStatus.strMethodName;
            Element stepDetails = doc.createElement(tcName);
            //Element stepDetails = doc.createElement("Step");
            root.appendChild(stepDetails);

            Element stepNumber = doc.createElement("SerialNo");
            stepNumber.appendChild(doc.createTextNode(String.valueOf(stepNum)));
            stepDetails.appendChild(stepNumber);

            Element stepName = doc.createElement("StepName");
            if (strStepName.contentEquals("END OF THE TEST : ")) {
                String newStepName = "End of the test for iteration : " + ReportControl.intRowCount;
                stepName.appendChild(doc.createTextNode(newStepName));
                stepDetails.appendChild(stepName);
            } else {
                stepName.appendChild(doc.createTextNode(strStepName));
                stepDetails.appendChild(stepName);
            }

            Element stepDesc = doc.createElement("StepDescription");
            stepDesc.appendChild(doc.createTextNode(strStepDesc));
            stepDetails.appendChild(stepDesc);

            Element testData = doc.createElement("TestData");
            if(doc.createTextNode(input) != null) {
            	testData.appendChild(doc.createTextNode(input));
            	}
            	else
            	{
            	testData.appendChild(doc.createTextNode("NA"));
            	} 
            stepDetails.appendChild(testData);

            //Test step status
            Element stepStatus = doc.createElement("Status");
            stepStatus.appendChild(doc.createTextNode(imgSrc));
            stepDetails.appendChild(stepStatus);
            stepDetails.setNodeValue(imgSrc);
            // Include failed step screen shot
            if (status == "FAIL") {
                Element scrShot = doc.createElement("Image");
                scrShot.appendChild(doc.createTextNode(href));
                stepDetails.appendChild(scrShot);
            }

            //Test step execution time
            Element stepExecutionTime = doc.createElement("ExecutionTime");
            stepExecutionTime.appendChild(doc.createTextNode(strStepExecTime));
            stepDetails.appendChild(stepExecutionTime);
           

            //write the content to xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(file);
            
            transformer.transform(source, result);
            LOG.info("File saved");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Store test case execution details in Summary xml
     *
     * @param summaryFile A variable of type File
     * @param files       A variable of type List<String></String>
     * @throws Throwable
     */
    public void updateXMLForSummary(File summaryFile, List<String> files) throws Throwable {
        int index = 0, serialNo = 0;
        Node rootNode = null;
        String href = "";
        String htmlFileType = "html";

        String url = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "insightWebURL");
        String sReportPath = ReportXML.strReportPath + "_" + browserName + "-" + strDate;

        LOG.info("inside method: updateXMLForSummary");

        try {
            //Get Document Builder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            /*factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);*/
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Get Pass/Fail test cases count
            Integer passTCCount = TestResult.passCounter
                    .get(ReportXML.browserContext) == null ? 0
                    : TestResult.passCounter.get(ReportXML.browserContext);
            Integer failTCCount = TestResult.failCounter
                    .get(ReportXML.browserContext) == null ? 0
                    : TestResult.failCounter.get(ReportXML.browserContext);

            // Iterate through list of detail xml files
            for (int fileCount = 0; fileCount < files.size(); fileCount++) {
                Document document = builder.parse(new File(sReportPath + String.valueOf(File.separatorChar) +
                        files.get(fileCount)));
                Document doc = builder.newDocument();
                //Normalize the XML Structure; It's just too important !!
                document.getDocumentElement().normalize();

                //Here comes the root node
                Element root = document.getDocumentElement();
                LOG.info("root node name : " + root.getNodeName());

                NodeList node = document.getElementsByTagName("TestCase");
                Node nodeData = node.item(index);
                Element tcData = (Element) nodeData;

                String tcName = tcData.getElementsByTagName("TestCaseName").item(index).getTextContent();
                String tcDesc = tcData.getElementsByTagName("TestCaseDescription").item(index).getTextContent();
                String passCount = tcData.getElementsByTagName("PassCount").item(index).getTextContent();
                String failCount = tcData.getElementsByTagName("FailCount").item(index).getTextContent();
                String executionTime = tcData.getElementsByTagName("TestCaseExecutionTimeInSeconds").item(index).getTextContent();
                String status = tcData.getElementsByTagName("Status").item(index).getTextContent();

                if (fileCount == 0) {
                    Element summary = doc.createElement("Summary");
                    doc.appendChild(summary);
                    rootNode = doc.getFirstChild();

                    //populate details in header node
                    Element header = doc.createElement("Header");
                    rootNode.appendChild(header);

                    Element suiteName = doc.createElement("SuiteExecuted");
                    suiteName.appendChild(doc.createTextNode(ReporterConstants.SUITE_NAME));
                    header.appendChild(suiteName);

                    Element browser = doc.createElement("Browser");
                    browser.appendChild(doc.createTextNode(ReportXML.browserName));
                    header.appendChild(browser);

                    Element version = doc.createElement("Version");
                    if(ReporterConstants.BROWSER_VERSION != null) {
                        version.appendChild(doc.createTextNode(ReporterConstants.BROWSER_VERSION));
                    }
                    else
                    {
                        version.appendChild(doc.createTextNode("Null"));
                    }
                    header.appendChild(version);

                    // OS name
                    Element osName = doc.createElement("OS");
                    osName.appendChild(doc.createTextNode(ReportXML.osName));
                    header.appendChild(osName);

                    // Test Case Start time
                    Element dateTime = doc.createElement("DateTime");
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                    dateTime.appendChild(doc.createTextNode(formatter.format(new Date())));
                    header.appendChild(dateTime);

                    // Host Name
                    Element hostName = doc.createElement("HostName");
                    hostName.appendChild(doc.createTextNode(InetAddress.getLocalHost().getHostName()));
                    header.appendChild(hostName);

                    Element environment = doc.createElement("Environment");
                    environment.appendChild(doc.createTextNode(url));
                    header.appendChild(environment);

                    Element passTestCount = doc.createElement("PassTestCases");
                    passTestCount.appendChild(doc.createTextNode(String.valueOf(passTCCount)));
                    header.appendChild(passTestCount);

                    Element failTestCount = doc.createElement("FailTestCases");
                    failTestCount.appendChild(doc.createTextNode(String.valueOf(failTCCount)));
                    header.appendChild(failTestCount);

                    double suiteExecTime = TestResult.iSuiteExecutionTime.get(ReportXML.browserContext);
                    String suiteExecTimeInSeconds = String.valueOf((int) suiteExecTime) + " " + "seconds";
                    Element suiteExecutionTime = doc.createElement("SuiteExecutionTime");
                    suiteExecutionTime.appendChild(doc.createTextNode(suiteExecTimeInSeconds));
                    header.appendChild(suiteExecutionTime);
                } else {
                    FileInputStream inputStream = new FileInputStream(summaryFile);
                    InputSource inputSource = new InputSource(inputStream);

                    //create a new document from input source
                    doc = builder.parse(inputSource);
                    rootNode = doc.getFirstChild();
                }

                Element testDetails = doc.createElement("TestDetails");
                rootNode.appendChild(testDetails);

                Element sno = doc.createElement("SerialNo");
                serialNo = fileCount + 1;
                sno.appendChild(doc.createTextNode(String.valueOf(serialNo)));
                testDetails.appendChild(sno);

                Element testName = doc.createElement("TestCase");
                testName.appendChild(doc.createTextNode(tcName));
                testDetails.appendChild(testName);

                //populate href for each Test case
                href = tcName + "." + htmlFileType;
                Element testLink = doc.createElement("href");
                testLink.appendChild(doc.createTextNode(href));
                testDetails.appendChild(testLink);

                Element testDesc = doc.createElement("Description");
                testDesc.appendChild(doc.createTextNode(tcDesc));
                testDetails.appendChild(testDesc);

                Element passCnt = doc.createElement("PassCount");
                passCnt.appendChild(doc.createTextNode(passCount));
                testDetails.appendChild(passCnt);

                Element failCnt = doc.createElement("FailCount");
                failCnt.appendChild(doc.createTextNode(failCount));
                testDetails.appendChild(failCnt);

                Element time = doc.createElement("Time");
                time.appendChild(doc.createTextNode(executionTime));
                testDetails.appendChild(time);

                Element tcStatus = doc.createElement("Status");
                tcStatus.appendChild(doc.createTextNode(status));
                testDetails.appendChild(tcStatus);

                //write the content to xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                DOMSource source = new DOMSource(doc);

                StreamResult result = new StreamResult(summaryFile);
                transformer.transform(source, result);
                LOG.info("File saved");
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the current date time
     *
     * @return datetime A variable of type String.
     */
    private static String getDateTime() {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss a");
        String formattedDate = formatter.format(today);
        return formattedDate;
    }

    /**
     * Create XML for generating Summary Report
     *
     * @throws Throwable
     */
    public void createXMLForSummaryReport() throws Throwable {
        String sReportPath = ReportXML.strReportPath + "_" + browserName + "-" + strDate;
        String destinationPath = ReporterConstants.LOCATION_RESULT + File.separator + backupFolderName;
        String fileType = "xml";
        File reportDir = new File(sReportPath);
        File backupDir = new File(destinationPath);

        if (reportDir.exists() && reportDir.isDirectory()) {
            List<String> xmlFiles = listAllFiles(reportDir, fileType);

            //create summary xml
            File summaryFile = new File(sReportPath + File.separator + "SummaryResults" + "." + fileType);
            if (summaryFile.exists()) {
                LOG.info("Summary file exists");
                updateXMLForSummary(summaryFile, xmlFiles);
            } else {
                try {
                    summaryFile.createNewFile();
                    LOG.info("summary file newly created");
                    updateXMLForSummary(summaryFile, xmlFiles);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if(!backupDir.exists()) {
            backupDir.mkdirs();
        }

        convertXMLToHTML();
    }

    /**
     * Method to read the detail xml file names and store in a list
     * @param folder   A variable of type File
     * @param fileType A variable of type String
     * @return
     */
    public List<String> listAllFiles(File folder, String fileType) {
        LOG.info("in listAllFiles method ");
        File[] fileNames = folder.listFiles();
        List<String> fileList = new ArrayList<String>();

        for (File file : fileNames) {
            LOG.info("file name : " + file.getName());
            if (file.getName().endsWith(("." + fileType))) {
                fileList.add(file.getName());
            }
        }
        return fileList;
    }

    public long generateRandomNumber() {
		/*Random generator = new Random();
		return generator.nextInt(9999) + 10000;*/
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyhhmmss");
        String currentdate = sdf.format(date);
        long ran = Long.parseLong(currentdate);
        return ran;

    }    /**
     * Create image file for failed test step
     * @param strStepDes A variable of type String
     */
    public static void createImageForFailedStep(String strStepDes) {
        String browserName = ReportXML.browserContext.getBrowserName();
        String sReportPath = ReportXML.strReportPath + "_" + browserName + "-" + strDate;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyhhmmss");
        String currentdate = sdf.format(date);
        long ran = Long.parseLong(currentdate);
        strStepDes = strStepDes.replaceAll(":", "_");
        strStepDes = strStepDes.replaceAll(",", "_");
        strStepDes = strStepDes.replaceAll("&", "_");
        strStepDes = strStepDes.replaceAll(" ", "_");

        String fileName = sReportPath + File.separator + htmlReportsFolderName +
                File.separator + ReporterConstants.FOLDER_SCREENRSHOTS + File.separator +
                strStepDes.replaceAll("<b>", "").replaceAll("</b>", "") +ran+
                ".jpeg";

        String newFileName = fileName;
        try {
            // If the file with same name already exists, append the numbers 1,2 so on to the file name.
            File myPngImage = new File(fileName);
            int counter = 1;
            while (myPngImage.exists()) {
                newFileName = fileName + "_" + counter;
                myPngImage = new File(newFileName);
                counter++;
            }
            ReportXML.screenShot(TestEngineWeb.driver, newFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void copyCompleteFolderForAjaxReporting(String... logos) {

        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //strDate = formatter.format(today).replace(":", "_").replace(" ", "_");
        strDate = formatter.format(today);
        String sReportPath = ReportXML.strReportPath + "_" + browserName + "-" + strDate;
        File destFolder = new File(destFol+ "-" + strDate);
        File SourceFolder= new File(summaryHTML);
        /*String logosFolders = logosFolder;
        File SourceLogoFolder= new File(logosFolders);
        Path sourceHTML= Paths.get(summaryHTML);*/

        File file = new File(destFol+ "-" + strDate);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        File destinationFolder = file;

        try {
            Path targetDirectory = Paths.get(sReportPath);


            //copy source to target using Files Class
            FileUtils.copyDirectory(SourceFolder, destinationFolder);
/*
            Files.copy(sourceHTML, targetDirectory);
*/
        }
        catch (Exception ex){
            LOG.info("Exception dir" + ex.getMessage());
        }
        if (destFolder.exists() == false) {
            try {
                destFolder.mkdirs();
            } catch (Exception ex) {
                LOG.info("Exception in creating screenhots dir : " + ex.getMessage());
            }
        }

        for (String logo : logos) {
            LOG.info("Current logo : " + logo);
            File logoFile = new File(logo);

            if (logoFile.isDirectory()) {
                try {
                    FileUtils.copyDirectoryToDirectory(logoFile, destFolder);
                    LOG.info(logoFile + "copied to " + destFolder);
                } catch (IOException e) {
                    LOG.info(logoFile + "could not be copied to " + destFolder);
                    LOG.info("IOException Encountered : " + e.getMessage());
                    e.printStackTrace();
                }
            }

            if (logoFile.isDirectory() == false) {
                if (logoFile.exists()) {
                    try {
                        FileUtils.copyFileToDirectory(logoFile, destFolder);
                        LOG.info(logoFile + "copied to " + destFolder);
                    } catch (IOException e) {
                        LOG.info(logoFile + "could not be copied to " + destFolder);
                        LOG.info("IOException encountered : " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
    }




}