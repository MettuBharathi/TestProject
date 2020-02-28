package com.insight.ObjRepo;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class QuoteHistoryObj extends ActionEngine {
	public static By QUOTE_HISTORY_SEARCHBUTTON = By.xpath("//input[@id='quoteAdvSearch']");
	public static By QUOTE_DETAILS=By.xpath("//section[@id='quoteDetails']//dl//dt");
	public static By QUOTE_NUMBER = By.xpath("//table[@id='quoteSearch']//tr//td[1]/a");
	public static By shippingEstimateInCartSummary(String shippingCarrier) {
		return By.xpath("//section/following::div[@class='columns small-12 large-3 print-5 print-offset-7']//span[text()='Shipping estimate']//span//p[text()='"+shippingCarrier+"']");
	}
	public static By SHIPPING_METHOD_SAVE_AS_QUOTE=By.xpath("//select[@id='shippingmethodselectbox']");
	public static By GET_QUOTE_NUMBER=By.xpath("//div[@id='quotenameval']//span");

	public static By STANDARD_PRICE = By.xpath("//div[@class='insight-std-price']//span[contains(text(),'Standard')]");
	public static By DISCOUNT = By.xpath("//div[@class='insight-discount-price'][contains(text(),'Discount')]");
	public static By DISCOUNT_PRICE = By.xpath("//div[@id='js-product-detail-pricing-target']/p[@class='prod-price']");
	public static By MSRP_PRICE = By.xpath("//td[@class='footable-visible footable-last-column footable-first-column'][contains(text(),'MSRP')]");
	
	
	 public static By getValueFromQuoteTable(String quoteName){
		 return By.xpath("//table[@class='footable clean full footable-loaded default']//tbody//tr[1]//td[contains(.,'"+quoteName+"')][1]");
	 }
	 
	 public static By getReferenceNumber(String quoteReferenceNum){
		 return By.xpath("//table[@class='footable clean full footable-loaded default']//tbody//tr[1]//td//a[contains(.,'"+quoteReferenceNum+"')]");
	 }
	 public static By DESCENDING =By.xpath("//input[@id='descQuote']");
		
	 public static By getAdvSearchQuoteFormTable(String quoteName){
		 return By.xpath("//table[@id='quoteSearch']//tbody//tr//td[contains(.,'"+quoteName+"')]");
	 }
	 public static By getValueFromRecentQuoteTable(String quoteName){
		 return By.xpath("//table[@class='footable clean full default footable-loaded']//tbody//tr[1]//td[contains(.,'"+quoteName+"')][1]");
	 }
	 public static By RECORD_ERRORMSG = By.xpath("//div[@id='advSearchErrMsg']");

	 public static By clickQuoteNumLink(String QuoteName){
		 return By.xpath("//table[@class='footable clean full footable-loaded default']//tbody//tr//td[contains(.,'"+QuoteName+"')]/ancestor::tr/td/a");
	 }

	 public static By QUOTE_NAME = By.xpath("//table[@id='quoteSearch']//tr//td[1]/a");
	 
	 public static By DATE_ENTERED = By.xpath("//table[@id='quoteSearch']//tr//td[3]");
	 public static By EXPIRATION_DATE = By.xpath("//table[@id='quoteSearch']//tr//td[4]/div");
	 public static By ACCOUNTNAME = By.xpath("//table[@id='quoteSearch']//tr//td[5]");
	 public static By ACCOUNTNUMBER = By.xpath("//table[@id='quoteSearch']//tr//td[6]");
	 public static By EXPORTTOEXCEL = By.xpath("//a[@class='icon export has-tip tip-top exportToExcel']");
	 public static By QUOTEHISTORY_GLOSS = By.xpath("//p[@class='m-b-md']//a");
	 public static By MFR_PART = By.xpath("//div[@id='quoteGlossary']//following::table[@class='footable dark full m-t-md default footable-loaded']//thead//following-sibling::tbody//tr//td[1]");
	 public static By INSIGHT_PART = By.xpath("//div[@id='quoteGlossary']//following::table[@class='footable dark full m-t-md default footable-loaded']//thead//following-sibling::tbody//tr//td[2]");
	 public static By DESCRIPTION = By.xpath("//div[@id='quoteGlossary']//following::table[@class='footable dark full m-t-md default footable-loaded']//thead//following-sibling::tbody//tr//td[3]/a");
	 public static By QTY = By.xpath("//div[@id='quoteGlossary']//following::table[@class='footable dark full m-t-md default footable-loaded']//thead//following-sibling::tbody//tr//td[4]");
	 public static By STOCK = By.xpath("//div[@id='quoteGlossary']//following::table[@class='footable dark full m-t-md default footable-loaded']//thead//following-sibling::tbody//tr//td[5]");
	 public static By QUOTEDUNIT = By.xpath("//div[@id='quoteGlossary']//following::table[@class='footable dark full m-t-md default footable-loaded']//thead//following-sibling::tbody//tr//td[6]");
	 public static By QUOTEDPRICE = By.xpath("//div[@id='quoteGlossary']//following::table[@class='footable dark full m-t-md default footable-loaded']//thead//following-sibling::tbody//tr//td[6]");
     public static By SUBTOTAL = By.xpath("//table[@class='m-b-none']//tbody//tr[1]//td[2]");
	 public static By SHITPPING = By.xpath("//table[@class='m-b-none']//tbody//tr[2]//td[2]");
	 public static By TAX = By.xpath("//table[@class='m-b-none']//tbody//tr[3]//td[2]");
	 public static By TOTAL = By.xpath("//table[@class='m-b-none']//tbody//tr[4]//td[2]");
	 public static By PRINT = By.xpath("//a[@data-tooltip='print']");
	 public static By EMAIL = By.xpath("//a[@data-tooltip='email']");
	 public static By EDIT = By.xpath("//a[@data-tooltip='edit']");
	 public static By DELETE = By.xpath("//a[@data-tooltip='delete']");
	 public static By QUOTEHISTORY = By.xpath("//h2[contains(.,'Quote History')]");
	 public static By QUOTEDETAILS = By.xpath("//h2[contains(.,'Quote Details')]");
	 public static By getQuickSearchQuoteFormTable(String QuoteNumber){
		 return By.xpath("//table[@id='quoteSearch']//tbody//tr//td//a[contains(.,'"+QuoteNumber+"')]");
	 }
		public static By QUICK_SEARCH_TEXT = By.xpath("//input[@id='quoQuickInputNumber']");
		 public static By getQuoteNumberFRomQuickSearchHistory(){
			 return By.xpath("//table[@id='quoteSearch']//tbody//tr//td//a");
		 }
		 public static By ERROR_MSG = By.xpath("//div[@class='alert']");
		 public static By QUICKSHOP_ERROR_MSG = By.xpath("//span[@class='columns iw-message__text']");
		 
		 public static By getContactName(String contactName){
			 return By.xpath("//div[@class='columns small-12 medium-4']//p[contains(.,'"+contactName+"')]");
		 }
		 //Add additional information
		 public static By BUSSINESSUNIT = By.xpath("//input[@name='smartTracker.st-578530']");	
		 public static By OPERATIONAL_UNIT = By.xpath("//input[@name='smartTracker.st-578531']");
		 public static By LOCATION_CODE = By.xpath("//input[@name='smartTracker.st-578532']");
		 public static By DEPARTMENT = By.xpath("//input[@name='smartTracker.st-578533']");
		 public static By ACCOUNT = By.xpath("//input[@name='smartTracker.st-578534']");
		 public static By PRODUCT = By.xpath("//input[@name='smartTracker.st-578535']");
		 public static By ENTITYNAME = By.xpath("//select[@name='smartTracker.st-579583']");
		 public static By END_USER_CONTACT = By.xpath("//input[@name='smartTracker.st-579584']");
		 public static By ORDER_CONTACT = By.xpath("//input[@name='smartTracker.st-579585']");
		 public static By LICENCE_LINELEVEL1 = By.xpath("(//input[@name='licenseInformation.LICENSE'])[1]");
		 public static By LICENCE_LINELEVEL2 = By.xpath("(//input[@name='licenseInformation.LICENSE'])[2]");
		 public static By CONT_BTN = By.xpath("//button[contains(.,'Continue')]");
		 
		 
		 
}
