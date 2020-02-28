package com.insight.Lib;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.insight.ObjRepo.CanadaObj;
import com.insight.ObjRepo.CommonObj;
import com.insight.ObjRepo.InvoiceHistoryObj;
import com.insight.ObjRepo.OrderObj;
import com.insight.ObjRepo.ReportsObj;


public class ReportingLib extends ReportsObj {
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	OrderObj orderObj=new OrderObj();
    SearchLib searchLib = new SearchLib();
    CanadaObj canadaObj=new CanadaObj();
   
    InvoiceHistoryObj invoiceHistoryObj=new InvoiceHistoryObj();
    
	/**
	 * Method is to select delivery report
	 * 
	 * @throws Throwable
	 */
	public void clickOnDeliveryReport(String reportName) throws Throwable {
		waitForVisibilityOfElement(DELIVERYREPORT, "Select Delivery Method");
		if (isElementPresent(DELIVERYREPORT, "Select ADelivery Method", true)){		
			
           selectByVisibleText(DELIVERYREPORT,reportName,"Delivery Option");
		    reporter.SuccessReport("Verify  "+reportName+" is selected to DeliveryReport on Reports Page",
			"Delivery Option are selected to "+reportName+"", "");
		}
		else {
			reporter.failureReport("Verify  "+reportName+" is selected to Select DeliveryReport on Reports Page",
					"Delivery Option are Not selected to "+reportName+"", "");
		}
		}
	
	/**
	 * Method is to type custom name
	 * 
	 * @throws Throwable
	 */
	public void clickOnCustomName(String customName) throws Throwable {
		waitForVisibilityOfElement(CUSTOMNAME, "Select Delivery Method");
		if (isElementPresent(CUSTOMNAME, "Select ADelivery Method", true)){				
			type(CUSTOMNAME,customName ,"Delivery Options Custom Name");
		reporter.SuccessReport("Enter Custom Name On the Invoiced orders Report Page",
				"Custom Name Field Exists and Entered", "");
		}
		else {
			reporter.failureReport("Enter Custom Name On the Invoiced orders Report Page",
					"Custom Name Field Not Exists and Entered", "");
		}

	}
	
	
	/**
	 * Method is to verify custom name to downloaded excel file afer clicked on RUN button
	 * 
	 * @throws Throwable
	 */
	public void verifyCustomeName(String customName) throws Throwable {
             Thread.sleep(10000);
         	File root = new File(System.getProperty("user.dir") + "\\" + "DownloadedFiles" + "\\");
	        FilenameFilter beginswithm = new FilenameFilter()
	        {
	         public boolean accept(File directory, String filename) {
	              return filename.startsWith(customName);
	          }
	        };

	        File[] files = root.listFiles(beginswithm);
	        for (File f: files)
	        {
	        	if(f!=null){
	            System.out.println(f);
	           reporter.SuccessReport("Verify Custom Name on Excel","Custom Name of Report Exists on Excel", "");
	           f.deleteOnExit();
	        	}
	        	else{
	        		reporter.SuccessReport("Verify Custom Name on Excel","Custom Name of Report does not Exists on Excel", "");
	        	}
	        }
	    }
	/**
	 * Method is to verify Schedule Report
	 * 
	 * @throws Throwable
	 */
	public void verifyScheduleReport() throws Throwable {
		waitForVisibilityOfElement(SCHEDULEREPORT, "Select Delivery Method");
		if (isElementPresent(SCHEDULEREPORT, "Select ADelivery Method", true)){				
			
		reporter.SuccessReport("Verify Schedule report Options on Report Page"," Schedule Report Options exist on Report Page", "");
		}
		else {
			reporter.failureReport("Verify Schedule report Options on Report Page"," Schedule Report Options doesnot exist on Report Page", "");
		}

	}
	
	/**
	 * Method is to select Schedule report Options
	 * 
	 * @throws Throwable
	 */
	public void verifyScheduleReportOptions(List<String> Options) throws Throwable {
		
		for (int i=0 ; i <Options.size();i++){
		waitForVisibilityOfElement(SCHEDULEREPORT_OPTIONS, "Select Delivery Method");
		if (isElementPresent(SCHEDULEREPORT_OPTIONS, "Select ADelivery Method", true)){				
			selectByVisibleText(SCHEDULEREPORT_OPTIONS,Options.get(i),"Schedule Report");	
		reporter.SuccessReport("Verify Schedule report "+Options.get(i)+" Options on Report Page"," Schedule Report  "+Options.get(i)+" Options exist on Report Page", "");
		}
		else {
			reporter.failureReport("Verify Schedule report Options"+Options.get(i)+" on Report Page"," Schedule Report Options doesnot exist on Report Page", "");
		}
		}

	}
	
	/**
	 * Method is to select Schedule report Options Dates
	 * 
	 * @throws Throwable
	 */
	public void verifyScheduleReportOptionsDates(List<String> Options) throws Throwable {
		
		for (int i=0 ; i <Options.size();i++){
		waitForVisibilityOfElement(SCHEDULEREPORT_DATES, "Select Delivery Method");
		if (isElementPresent(SCHEDULEREPORT_DATES, "Select ADelivery Method", true)){				
			selectByVisibleText(SCHEDULEREPORT_DATES,Options.get(i),"Schedule Report");	
		reporter.SuccessReport("Verify Schedule report "+Options.get(i)+" Options on Report Page"," Schedule Report  "+Options.get(i)+" Options exist on Report Page", "");
		}
		else {
			reporter.failureReport("Verify Schedule report Options"+Options.get(i)+" on Report Page"," Schedule Report  "+Options.get(i)+" Options doesnot exist on Report Page", "");
		}
		}

	}
	
	/**
	 * Method is to select Delivery Method Options
	 * 
	 * @throws Throwable
	 */
	public void verifyDeliveryMethodOptions(List<String> Options) throws Throwable {
		
		for (int i=0 ; i <Options.size();i++){
		waitForVisibilityOfElement(DELIVERY_METHODOPTIONS, "Select Delivery Method");
		if (isElementPresent(DELIVERY_METHODOPTIONS, "Select Delivery Method", true)){				
			selectByVisibleText(DELIVERY_METHODOPTIONS,Options.get(i),"Schedule Report");	
		reporter.SuccessReport("Verify Schedule report "+Options.get(i)+" Options on Report Page"," Schedule Report  "+Options.get(i)+" Options exist on Report Page", "");
		}
		else {
			reporter.failureReport("Verify Schedule report Options"+Options.get(i)+" on Report Page"," Schedule Report  "+Options.get(i)+" Options doesnot exist on Report Page", "");
		}
		}

	}
	/**
	 * Method is to select account selections
	 * 
	 * @throws Throwable
	 */
	public void verifyDefaultAccountSelection(String accountSelection) throws Throwable {
		
		
		waitForVisibilityOfElement(getAcccountSelection(accountSelection), "Default Account Selection");
		if (isElementPresent(getAcccountSelection(accountSelection), "Default Account Selection", true)){				
		
		reporter.SuccessReport("Verify Account Selections Default to  "+accountSelection+" Options on Report Page"," Account Selections Default to  "+accountSelection+" Options exist on Report Page", "");
		}
		else {
			reporter.failureReport("Verify Account Selections Default to "+accountSelection+" on Report Page"," Account Selections Default to "+accountSelection+" Options doesnot exist on Report Page", "");
		}
		}
	
	/**
	 * Method is to select account selections
	 * 
	 * @throws Throwable
	 */
	public void verifyDefaultCurrentDate(String currentDate) throws Throwable {
		
		
		waitForVisibilityOfElement(getCurrentDate(currentDate), "Default Account Selection");
		if (isElementPresent(getCurrentDate(currentDate), "Default Account Selection", true)){				
		
		reporter.SuccessReport("Quick Date defaults to  "+currentDate+" Options on Report Page"," Quick Date defaults to  "+currentDate+"  exist on Report Page", "");
		}
		else {
			reporter.failureReport("Quick Date defaults to "+currentDate+" on Report Page"," Quick Date defaults to  "+currentDate+" doesnot exist on Report Page", "");
		}
		}
	
	/**
	 * Method is toclick smart tracker checkbox
	 * 
	 * @throws Throwable
	 */
	public void clickSmartTracker() throws Throwable {
		
		
		waitForVisibilityOfElement(SMARTTRACKER, "Smart Tracker");
		if (isElementPresent(SMARTTRACKER, "Smart Tracker", true)){				
		click(SMARTTRACKER, "Smart Tracker");
		reporter.SuccessReport("Click on Smart tracker "," Smart Traker checkbox exists and clicked", "");
		}
		else {
			reporter.failureReport("Click on Smart tracker "," Smart Traker checkbox not exists ", "");
		}
		}

	
	/**
	 * Method is to //Verify the data in export file 
	 * 
	 * @throws Throwable
	 */
	public void verifyExportFileInQuoteHistory(String sheetName,  String columnHeaders,
			List<String> excelActualData, String customName) throws Throwable {
	 int rowNumber = 0; // zero based index
	 int rowNumbers = 2;
		Thread.sleep(10000);
	
		File root = new File(System.getProperty("user.dir") + "\\" + "DownloadedFiles" + "\\");
		FilenameFilter beginswithm = new FilenameFilter() {
			public boolean accept(File directory, String filename) {
				return filename.startsWith(customName);
			}
		};

		File[] files = root.listFiles(beginswithm);

		if (files[0] != null) {
			System.out.println(files[0]);
			if(rowNumber==0)
			{
				List<String> downloadedExcelContent = CommonLib.readRowFromExcel(files[0].getPath(), sheetName,rowNumber);
				System.out.println("Compare content" + downloadedExcelContent.equals(columnHeaders));
				if (downloadedExcelContent.equals(columnHeaders)) {
					reporter.SuccessReport(columnHeaders, "are avilable", "");
				} else {
					reporter.failureReport(columnHeaders, "are not avilable", "");
				}
			}
			if(rowNumbers==2)
			{
				List<String> downloadedExcelContent = CommonLib.readRowFromExcel(files[0].getPath(), sheetName,rowNumber);
				System.out.println("Compare content" + downloadedExcelContent.equals(excelActualData));
				if (downloadedExcelContent.equals(excelActualData)) {
					reporter.SuccessReport("Excel data are avilable", "","");
				} else {
					reporter.failureReport("Excel data are not avilable", "","");
				}
			}

			if (files[0].exists()) {
				Runtime.getRuntime().exec("Excel.exe");
				reporter.SuccessReport("Verify  Excel File", " Excel File is Closed", "");
			}
		}

	}
	
	/**
	 * Method is to type Emails
	 * 
	 * @throws Throwable
	 */
	public void enterEmails(String emails) throws Throwable {
		waitForVisibilityOfElement(EMAILREPORT_TEXT, "Enter emails");
		if (isElementPresent(EMAILREPORT_TEXT, "Enter emails", true)){				
			type(EMAILREPORT_TEXT,emails ,"Enter emails");
		reporter.SuccessReport("Enter 2 Emails(1Valid and 1 Invalid) in Email Report  on Reports Page",
				"Email Report Textbox Exists and Entered", "");
		}
		else {
			reporter.failureReport("Enter 2 Emails(1Valid and 1 Invalid) in Email Report  on Reports Page",
					"Email Report Textbox Exists and Entered", "");
		}

	}
	
	/**
	 * Method is to verify warring message after clicking on RUN Button 
	 * 
	 * @throws Throwable
	 */
	public void verifWarrning() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver,
				300 /* timeout in seconds */);
		if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
			String alertMsg = driver.switchTo().alert().getText();
			if (alertMsg.contains("At least one email entered is invalid.")) {
				reporter.SuccessReport("Verify the Warrning Message on Reports Page",
						"Warrning Message exists",
						"");
				acceptAlert();

	}
			else{
				reporter.failureReport("Verify the Warrning Message on Reports Page",
						"Warrning Message doesnot exists","");
			}
		}	}
	
	
	
	/**
	 * Method is to type Emails
	 * 
	 * @throws Throwable
	 */
	public void enterTemplates(String templateName) throws Throwable {
		waitForVisibilityOfElement(SAVETEMPLETENAME, "Enter emails");
		if (isElementPresent(SAVETEMPLETENAME, "Enter emails", true)){				
			type(SAVETEMPLETENAME,templateName ,"Enter emails");
		reporter.SuccessReport("Enter theTemplate Name on Reports Page",
				"Template Name field exists and entered", "");
		}
		else {
			reporter.failureReport("Enter theTemplate Name on Reports Page",
					"Template Name field doesnot exists and entered", "");
			}
		}

	/**
	 * Method is to select Schedule report Options
	 * 
	 * @throws Throwable
	 */
	public void selectScheduleReport(String Options) throws Throwable {
		
		
		waitForVisibilityOfElement(SCHEDULEREPORT_OPTIONS, "Select Delivery Method");
		if (isElementPresent(SCHEDULEREPORT_OPTIONS, "Select ADelivery Method", true)){				
			selectByVisibleText(SCHEDULEREPORT_OPTIONS,Options,"Schedule Report");	
		reporter.SuccessReport("Verify Schedule report "+Options+" Options on Report Page"," Schedule Report  "+Options+" Options exist on Report Page", "");
		}
		else {
			reporter.failureReport("Verify Schedule report Options"+Options+" on Report Page"," Schedule Report Options doesnot exist on Report Page", "");
		}
		}

	
	/**
	 * Method is to select account selections
	 * 
	 * @throws Throwable
	 */
	public void clickOnSave() throws Throwable {
		waitForVisibilityOfElement(SAVEBUTTON, "Save button");
		if (isElementPresent(SAVEBUTTON,"Save button", true)){		
			
		 click(SAVEBUTTON, "Save button");
		reporter.SuccessReport("Verify  Click Save on Reports Page",
				"Save Reports Field Exists and Clicked", "");
		}
		else {
			reporter.failureReport("Verify  Click Save on Reports Page",
					"Save Reports Field Not Exists and Clicked", "",driver);
		}
		}
	

	/**
	 * Method is to verify report templates exist
	 * 
	 * @throws Throwable
	 */
	public void verifyReportTemplates() throws Throwable {
		waitForVisibilityOfElement(STATUS_MSG, "Enter emails");
		if (isElementPresent(STATUS_MSG, "Enter emails", true)){				
			
		reporter.SuccessReport("Report Templates on Reporting Management Page",
				"Report Templates exists and selected", "");
		}
		else {
			reporter.failureReport("Report Templates on Reporting Management Page",
					"Report Templates doesnot exists and selected", "");
			}
		}
	
	/**
	 * Method is to select account selections
	 * 
	 * @throws Throwable
	 */
	public void expandReportTemplateAndVerify(String templateName) throws Throwable {
		waitForVisibilityOfElement(REPORT_TEMPLATES, "Report Templates");
		if (isElementPresent(REPORT_TEMPLATES,"Report Templates", true)){		
			
		 click(REPORT_TEMPLATES, "Report Templates");
		 if (isElementPresent(getTemplateName(templateName),"Report Templates", true)){
			 
				reporter.SuccessReport("Verify the Report Name on Reporting Management Page",
						"Report Name exists", "");
		 }
		 else{
			 reporter.failureReport("Verify the Report Name on Reporting Management Page",
						"Report Name exists", "");
		 }
		 if (isElementPresent(LAST_RUN_DATE,"Last run Dates", true)){
			 SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			 String dateString = (format.getDateInstance()).toString();
			  
			 if(driver.findElement(LAST_RUN_DATE).getAttribute("innerText")==dateString)
			 
				reporter.SuccessReport("Last Run Date in Report Templates on Reporting Management Page",
						"Last Run Date exists with Not Today's Date", "");
			 isElementPresent(getTemplateName(templateName),"Report Templates", true);
			 reporter.SuccessReport("Report Name: QTP_Test_InvTemp on Reporting Management Page",
						"Report Name: QTP_Test_InvTemp exists and Deleted", "");
		 }
		 else{
			 reporter.failureReport("Last Run Date in Report Templates on Reporting Management Page",
						"Last Run Date doesnot exists with Not Today's Date", "");
		 }
		 
		reporter.SuccessReport("Report Templates on Reporting Management Page",
				"Report Templates exists and selected", "");
		
		}
		else {
			reporter.failureReport("Report Templates on Reporting Management Page",
					"Report Templates exists and selected", "");
		}
		}
	
	/**
	 * Method is to delete reporting name 
	 * @throws Throwable
	 */
	public void clickOnDelete(String reportName) throws Throwable {
		waitForVisibilityOfElement(getDeleteButton(reportName), "Delete button");
		if (isElementPresent(getDeleteButton(reportName),"Delete button", true)){		
			
		 click(getDeleteButton(reportName), "Delete button");
		reporter.SuccessReport("Verify  Report Name: QTP_Test_InvTemp on Reporting Management Page",
				"Report Name: QTP_Test_InvTemp exists and Deleted", "");
		}
		else {
			reporter.failureReport("Verify  Report Name: QTP_Test_InvTemp on Reporting Management Page",
					"Report Name: QTP_Test_InvTemp not exists and Deleted", "");
			}
		}

	/**
	 * Method is to verify whether invoice date is checked or not
	 * 
	 * @throws Throwable
	 */
	public void verifyInvoiceDateChecked() throws Throwable {
		waitForVisibilityOfElement(DATE_INVOICE, " Invoice Order radio button ");
		if (driver.findElement(DATE_INVOICE).isSelected()){				
			
		}
		else {
			 click(DATE_INVOICE, " Invoice Order radio button ");
			}
		}
	/**
	 * Method is to verify whether Manufacturer is default to ALL
	 * @throws Throwable
	 */
	public void verifyFilterByProduct() throws Throwable {
		waitForVisibilityOfElement(MANF_DEF, " Manufacturer product  ");
		if (isElementPresent(MANF_DEF, " Manufacturer product ")){				
			reporter.SuccessReport("Verify Manufacturers Filter Default to ALL  on Reports Page",
					"Manufacturers Filter Default to ' ALL ' on Reports Page", "");
		}
		else {
			reporter.failureReport("Verify Manufacturers Filter Default to ALL  on Reports Page",
					"Manufacturers Filter Not Default to 'ALL'  on Reports Page", "");
			}
		
		
	  if (isElementPresent(PROD_TYPES, " Product type ")){				
		reporter.SuccessReport("Verify Product type Filter Default to ALL  on Reports Page",
				"Product types Filter Default to ' ALL ' on Reports Page", "");
	   }
	 else {
		reporter.failureReport("Verify Product type Filter Default to ALL  on Reports Page",
				"Product types Filter Not Default to 'ALL'  on Reports Page", "");
		}
		
	  if (isElementPresent(PROD_CAT, " Product Categories product ")){				
		reporter.SuccessReport("Verify Manufacturers Filter Default to ALL  on Reports Page",
				"Product Categories Filter Default to ' ALL ' on Reports Page", "");
	   }
	 else {
		reporter.failureReport("Verify Product Categories Filter Default to ALL  on Reports Page",
				"Product Categories Filter Not Default to 'ALL'  on Reports Page", "");
		}
	  if (isElementPresent(PROD_SUB, " Product subCategories product ")){				
		reporter.SuccessReport("Verify Manufacturers Filter Default to ALL  on Reports Page",
				"Product SubCategories Filter Default to ' ALL ' on Reports Page", "");
	   }
	 else {
		reporter.failureReport("Verify Product SubCategories Filter Default to ALL  on Reports Page",
				"Product SubCategories Filter Not Default to 'ALL'  on Reports Page", "");
		}
	}


	/**
	 * Method is to click on insight partner checkbox
	 * 
	 * @throws Throwable
	 */
	public void clickOnInsightPartner() throws Throwable {
		waitForVisibilityOfElement(INS_PART, "Include Insight Partner data");
		if (isElementPresent(INS_PART, "Include Insight Partner data", true)){			
	      
		    reporter.SuccessReport("Verify  Include Insight Partner Data Checkbox on Reports Page",
			"Include Insight Partner Data Checkbox Exists", "");
		    
		    
		    if(!driver.findElement(INS_PART).isSelected()){
		    	 reporter.SuccessReport("Verify Include Insight Partner Data Checkbox Default to OFF on Reports Page",
		    				"Include Insight Partner Data Checkbox Default to OFF on Reports Page", "");
		    }
		    else{
		    	reporter.failureReport("Verify Include Insight Partner Data Checkbox Default to OFF on Reports Page",
	    				"Include Insight Partner Data Checkbox is not  Default to OFF on Reports Page", "");
		    }
		}
		else {
			reporter.failureReport("Verify  Include Insight Partner Data Checkbox on Reports Page",
					"Include Insight Partner Data Checkbox Exists", "",driver);
				}
		}

	/**
	 * Method is to click on insight partner checkbox
	 * 
	 * @throws Throwable
	 */
	public void verifyTreeForAllAccounts() throws Throwable {
		waitForVisibilityOfElement(TREE, "Hirearichy Tree");
		if (isElementPresent(TREE, "Hirearichy Tree", true)){			
	      
		    reporter.SuccessReport("Verify  Hierarchy tree displays with a list of soldto's that are selected on Reports Page",
			"soldto's are selected", "");
		    
		  
		}
		else {
			reporter.failureReport("Verify  Hierarchy tree displays with a list of soldto's that are selected on Reports Page",
					"soldto's are not selected", "");
				}
		}

	/**
	 * Method is to click on insight partner checkbox
	 * 
	 * @throws Throwable
	 */
	public void selctOrderType(String type) throws Throwable {
		waitForVisibilityOfElement(FILTERORDERS, "Filter Order Options");
		if (isElementPresent(FILTERORDERS, "Filter Order Options", true)){		
			
	        selectByVisibleText(FILTERORDERS,type,"Filter Order Options");
			    reporter.SuccessReport("Verify  "+type+" is selected to filter Options on Reports Page",
				"Delivery Option are selected to "+type+"", "");
			}
			else {
				reporter.failureReport("Verify  "+type+" is selected to filter Options on Reports Page",
						"Delivery Option are not selected to "+type+"", "",driver);
			}
	}

	/**
	 * Method to verify downloaded excel file
	 * @throws Throwable
	 */
	public void verifyDownloadedReportExcelFile(List<String> actualData) throws Throwable {
			Thread.sleep(10000);
			File root = new File(System.getProperty("user.dir") + "\\" + "DownloadedFiles" + "\\");
			FilenameFilter beginswithm = new FilenameFilter() {
				public boolean accept(File directory, String filename) {
					return filename.startsWith("Overall Sales History");
				}
			};
			File[] files = root.listFiles(beginswithm);

			if (files[0] != null) {	
			// PATH

			// load file
			FileInputStream fis = new FileInputStream(files[0]);
			// Load workbook
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh1 = wb.getSheetAt(0);
			String sales = sh1.getRow(0).getCell(0).getStringCellValue();//
			String opsCenter = sh1.getRow(3).getCell(0).getStringCellValue();
			String accountNo = sh1.getRow(3).getCell(4).getStringCellValue();
			String accountName = sh1.getRow(3).getCell(5).getStringCellValue();
			String billingAcctNo = sh1.getRow(3).getCell(6).getStringCellValue();
			String billingAccountName = sh1.getRow(3).getCell(7).getStringCellValue();
			String productCategory = sh1.getRow(3).getCell(8).getStringCellValue();
			List<String> expectedData=new ArrayList<String>();
			expectedData.add(sales);			
			expectedData.add(opsCenter);
			expectedData.add(accountNo);			
			expectedData.add(accountName);
			expectedData.add(billingAcctNo);			
			expectedData.add(billingAccountName);
			expectedData.add(productCategory);		
			Assert.assertEquals(actualData, expectedData);
			reporter.SuccessReport("Verify the Excel Data ", "Excel Data is present as expected", "");

		}
	}
		
	
	/**
	 * Method to verify downloaded PDF file
	 * @throws Throwable
	 */
	public void verifyDownloadedReportPDFFile() throws Throwable {
			Thread.sleep(10000);
			 try {
				 
					File root = new File(System.getProperty("user.dir") + "\\" + "DownloadedFiles" + "\\");
					FilenameFilter beginswithm = new FilenameFilter() {
						public boolean accept(File directory, String filename) {
							return filename.startsWith("Invoice Summary");
						}
					};
					File[] files = root.listFiles(beginswithm);
			       
					if (files[0].exists()) {
                    File file = files[0];
					
					        Desktop.getDesktop().open(file);
					      
					} else {

						System.out.println("File is not exists");

					}

					reporter.SuccessReport("Verify the PDF Data ", "Pdf file  is opened as expected", "");


			  	  } catch (Exception ex) {
					ex.printStackTrace();
				  }
		}
	

	/**
	 * Method is to click on  "Report As Admin Login"
	 * 
	 * @throws Throwable
	 */
	public void clickOnReportingAdminLogin() throws Throwable {
		waitForVisibilityOfElement(REPORT_ADMIN_LOGIN, "Report As Admin Login");
		if (isElementPresent(REPORT_ADMIN_LOGIN, "ReportAs  Admin Login", true)){		
			
			click(REPORT_ADMIN_LOGIN, "Report As  Admin Login");
		    reporter.SuccessReport("Select Reporting Admin Login As Link from User Management Page",
			"Reporting Admin Login As Link Exists and Clicked", "");
		}
		else {
			reporter.failureReport("Select Reporting Admin Login As Link from User Management Page",
					"Reporting Admin Login As Link does not Exist", "");
		}
		switchToChildWindow();
		}
	
	/**
	 * Method is to click on  "Report As Admin Login"
	 * 
	 * @throws Throwable
	 */
	public void VerifyWelcomePage(String toolsMenuName, String dropDown) throws Throwable {
		waitForVisibilityOfElement(WELCOME, "verifies welcome page ");
		isElementPresent(WELCOME, "verifies welcome page", true);			
				
		click(canadaObj.getAccountToolsMenu(toolsMenuName), "Account tools menu options");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
		
}
	
	/**
	 * Method is to select Accounts By Region 
	 * @throws Throwable
	 */
	public void selctAccountsByRegion(String region1,String region2) throws Throwable {
		waitForVisibilityOfElement(ACOUNTS_HIRERARCHY, "Regions Selection");
		if (isElementPresent(ACOUNTS_HIRERARCHY, "Regions Selection", true)){		
			
	        selectByVisibleText(ACOUNTS_HIRERARCHY,region1,"Regions Selection");
	        Actions builder = new Actions(driver);
			builder.keyDown(Keys.CONTROL);
	

			
	    	click(getRegion(region1),"Click on Region selected ");
	    	builder.keyDown(Keys.CONTROL);
	        selectByVisibleText(ACOUNTS_HIRERARCHY,region2,"Regions Selection");
	    	click(getRegion(region2),"Click on Region selected ");
	    	builder.keyUp(Keys.CONTROL);
	    	builder.build().perform();
			    reporter.SuccessReport("Select Insight North America and Insight EMEA in Select Operations Centers List",
				"Insight North America and Insight EMEA Items Exist", "");
			}
			else {
				reporter.failureReport("Select Insight North America and Insight EMEA in Select Operations Centers List",
						"Insight North America and Insight EMEA Items Does Not Exist", "");
			}
	}
	
	
	/**
	 * Method is to Select Accounts by hierarchy
	 * 
	 * @throws Throwable
	 */
	public void selctAccountsByList(String List1,String List2,String List3) throws Throwable {
		waitForVisibilityOfElement(ACCOUNTS_LIST, "Select Region List");
		if (isElementPresent(ACCOUNTS_LIST, "Select Region List", true)){				
	        selectByVisibleText(ACCOUNTS_LIST,List1,"Region1");	   
	        Actions builder = new Actions(driver);
	     			builder.keyDown(Keys.CONTROL);
	     	
	        click(getRegionList(List1),"Click on Region List selected ");
	        selectByVisibleText(ACCOUNTS_LIST,List2,"Region2");
	        click(getRegionList(List2),"Click on Region List selected ");
	        selectByVisibleText(ACCOUNTS_LIST,List3,"Region13");
	        click(getRegionList(List3),"Click on Region List selected ");
	        builder.keyUp(Keys.CONTROL);
	    	builder.build().perform();
			    reporter.SuccessReport("Select Insight Canada, Spain and United Kingdom in Select Regions List",
				"Insight North America and Insight EMEA Items Exists and Selected", "");
			}
			else {
				reporter.failureReport("Select Insight Canada, Spain and United Kingdom in Select Regions List",
						"Insight North America and Insight EMEA Items does not Exists and Selected", "");
			}
	}
	
	/**
	 * Method is to click on  "Report As Admin Login"
	 * 
	 * @throws Throwable
	 */
	public void clickUpdateFilterOption() throws Throwable {
		waitForVisibilityOfElement(UPDATEFILTER, "Upadate Filter Selection");
		if (isElementPresent(UPDATEFILTER, "Upadate Filter Selection", true)){		
			
			click(UPDATEFILTER, "Upadate Filter Selection");
		    reporter.SuccessReport("Select Update Filter Selection on Reports Page",
			"Reporting Admin Login As Link Exists and Clicked", "");
		}
		else {
			reporter.failureReport("Select Reporting Admin Login As Link from User Management Page",
					"Reporting Admin Login As Link does not Exist", "");
		}
		}
	/**
	 * Method is to click on  "Report As Admin Login"
	 * 
	 * @throws Throwable
	 */
	public void verifyHirearchyTree() throws Throwable {
		waitForVisibilityOfElement(HIR_TREE, "Verify HierarchyTree");
		if (isCheckBoxSelected(HIR_TREE)){				
			
		    reporter.SuccessReport("Hierarchy Tree on Reports Page",
			"Hierarchy Tree Exists and Selected", "");
		}
		else {
			reporter.failureReport("Hierarchy Tree on Reports Page",
					"Hierarchy Tree but Exists and not  Selected", "");
		}
		}
	
	/**
	 * Method is to get standard report options
	 * 
	 * @throws Throwable
	 */
	public void clickOnReportOptions(String reportOption) throws Throwable {
		waitForVisibilityOfElement(getReportOptions(reportOption), "Standard Report Options");
		if (isElementPresent(getReportOptions(reportOption), "Standard Report Options", true)){		
		click(getReportOptions(reportOption), "Enter A Card Button");
		reporter.SuccessReport("Click "+reportOption+" on Reports Page" ,
				""+reportOption+" Reports Page exists  ", "");
		}
		else{
			reporter.failureReport("Click "+reportOption+" on Reports Page" ," "+reportOption+" Reports Page exists  ", "",driver);
		}
	}
}
	
	
