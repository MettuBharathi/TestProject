package com.insight.ObjRepo;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class ReportsObj extends ActionEngine {
	
	public static By DELIVERYREPORT = By.xpath("//select[@id='ddlReportNameType']");
	
	public static By CUSTOMNAME = By.xpath("//input[@id='customFileName']");
	
	public static By SCHEDULEREPORT = By.xpath("//div[@class='subHeader']//span");
     public static By SCHEDULEREPORT_OPTIONS = By.xpath("//select[@id='ddlInterval']");
     public static By SCHEDULEREPORT_DATES = By.xpath("//select[@id='ddlQuickDates']");
     public static By DELIVERY_METHODOPTIONS = By.xpath("//select[@id='ddlDeliveryMethod']");
 

     public static By getAcccountSelection(String defaultOption){
			return By.xpath("//select[@id='selectionList']//option[contains(.,'"+defaultOption+"')]");
			
		}
     public static By getCurrentDate(String defaultOption){
			return By.xpath("//select[@id='ddlQuickDates']//option[contains(.,'"+defaultOption+"')]");
			
		}
     public static By SMARTTRACKER = By.xpath("//input[@id='Include Smart Tracker Info']");
     public static By EMAILREPORT_TEXT = By.xpath("//input[@id='txtEmail']");
     public static By SAVETEMPLETENAME = By.xpath("//input[@id='saveTemplateName']");
     public static By SAVEBUTTON = By.xpath("//div[@id='button']/a/span");
     public static By STATUS_MSG = By.xpath("//p[@id='statusMsg']");
     public static By REPORT_TEMPLATES = By.xpath("//div[@id='reportTemplatesTitle_e']//following-sibling::span");

     public static By getTemplateName(String templateName){
			return By.xpath("//table[@id='templateReptTable']//tr//*[contains(.,'"+templateName+"')]");
			
		}
   
     public static By getLastRunDate(String runDate){
  			return By.xpath("//table[@id='templateReptTable']//tr//*[contains(.,'"+runDate+"')]");
  			
  		}
     public static By LAST_RUN_DATE = By.xpath("//table[@id='templateReptTable']//tr//td[4]");  
    

  
  public static By DELETE = By.xpath("//table[@id='templateReptTable']//tr//td[contains(.,'Ad-Hoc')]//following::a[contains(.,'Delete')]");
  public static By getDeleteButton(String reportName){
			return By.xpath("//table[@id='templateReptTable']//tr//td[contains(.,'"+reportName+"')]//following::a[contains(.,'Delete')]");
			
		}
  public static By DATE_INVOICE = By.xpath("//input[@value='2']");
  public static By MANF_DEF = By.xpath("//select[@id='manufacturers']//option[@value='all']");
  public static By PROD_TYPES = By.xpath("//select[@id='productTypes']//option[@value='all']");
  
  public static By PROD_CAT = By.xpath("//select[@id='categories']//option[@value='all']");
  public static By PROD_SUB = By.xpath("//select[@id='subCategories']//option[@value='all']");
  public static By INS_PART = By.xpath("//input[@id='partnerdataFlag']");
  public static By TREE = By.xpath("//input[@id='3-10672348']");
  
  public static By FILTERORDERS = By.xpath("//select[@id='orderStatus']");
  
  public static By REPORT_ADMIN_LOGIN = By.xpath("//a[@title='Reporting Admin Log In As']//span");
  public static By WELCOME = By.xpath("//div[@class='welcome-content']//h1");
  public static By ACOUNTS_HIRERARCHY = By.xpath("//select[@id='salesOrgHierarchyList']");
  public static By ACCOUNTS_LIST = By.xpath("//select[@id='salesOrgSelectionList']");
  
  public static By UPDATEFILTER = By.xpath("//a[@title='Update Region Selection']//span");
  
  public static By HIR_TREE = By.xpath("//input[@id='c0-9009687']");
  public static By getRegion(String region){
		return By.xpath("//select[@id='salesOrgHierarchyList']//option[contains(.,'"+region+"')]");
		
	}

  public static By getRegionList(String region){
		return By.xpath("//select[@id='salesOrgSelectionList']//option[contains(.,'"+region+"')]");
		
	}
  public static By getReportOptions(String reportOption){
		return By.xpath("//div[@id='reportContents2']//div//a[contains(.,'"+reportOption+"')]");				
	}
	

}
