package com.insight.Lib;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.insight.ObjRepo.CanadaObj;
import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.CommonObj;
import com.insight.ObjRepo.InvoiceHistoryObj;
import com.insight.ObjRepo.OrderObj;
import com.insight.ObjRepo.QuoteHistoryObj;
import com.insight.accelerators.ActionEngine;

public class QuoteHistoryLib extends QuoteHistoryObj {
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	OrderObj orderObj=new OrderObj();
    SearchLib searchLib = new SearchLib();
    CanadaObj canadaObj=new CanadaObj();
    InvoiceHistoryObj invoiceHistoryObj=new InvoiceHistoryObj();
	
	CartLib cartLib = new CartLib();
	OrderLib orderLib = new OrderLib();
	/**
	 * Method is used to verify quote history page is loaded
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @throws Throwable
	 */
	public void verifyQuoteHistoryPageOpened() throws Throwable {
		if (isElementPresent(CanadaObj.QUICKSEARCH_DROPDOWN, "Quote history header ")) {
			reporter.SuccessReport("Verify Quote history page is loaded", "Quote history page is loaded", "");
		} else {
			reporter.failureReport("Verify Quote history page is loaded", "Quote history page is not loaded", "");
		}
	}

	/**
	 * Method is used to click on search button of advanced search in quote history
	 * 
	 * @throws Throwable
	 */
	public void clickOnAdvancedSearchSearchButton() throws Throwable{
		if (isElementPresent(QUOTE_HISTORY_SEARCHBUTTON, "Quote history header ")) {
			click(QUOTE_HISTORY_SEARCHBUTTON, "Advancedsearch search button");
		}
		
	}

	
	/**
	 * Method is used to verify quote details
	 * 
	 * @throws Throwable
	 */
	public void verifyQuoteDetails(String quoteDetails) throws Throwable {
		String result = null;
		List<WebElement> myList = driver.findElements(QUOTE_DETAILS);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			// loading text of each element in to array all_elements_text
			all_elements_text.add(myList.get(i).getText());
			result = myList.get(i).getText();
			String strArray[] = quoteDetails.split(",");
			System.out.println("result" + result);
			System.out.println("all_elements_text" + all_elements_text);
			if (result.equals(strArray[i])) {
				reporter.SuccessReport("Verfying quote preview details", strArray[i] + " " + "is avilable", "");
			} else {
				reporter.failureReport("Verfying quote preview details", strArray[i] + " " + "is not avilable", "");
			}
		}

	}

	/**
	 * Method is used to verify print pop up
	 * 
	 * @throws Throwable
	 */
	public void verifyPrintPopUp() throws Throwable {
		if (isElementPresent(By.xpath("//div[@id='js-print-quote-target']"), "print pop up")) {
			reporter.SuccessReport("Verify print pop up", "print pop up Exist", "");
		} else {
			reporter.failureReport("Verify print pop up", "print pop up not Exist", "");
		}

	}
	
	public void verifyShippingEstimateInCartSummary(String shippingCarrier) throws Throwable {
		if(isElementPresent(shippingEstimateInCartSummary(shippingCarrier), "Shipping estimate")) {
		reporter.SuccessReport("Verify CEVA 2 Day Shipping Carrier on Place Order Page", "Expected Shipping Carrier is Verfied", "");
	} else {
		reporter.failureReport("Verify CEVA 2 Day Shipping Carrier on Place Order Page", "Expected Shipping Carried Does not Exist", "");
	}
	}
	
	/**
	 * Method is to click on the save quote link and create quote and save it.
	 * @param quoteName
	 * @throws Throwable
	 */
	public void saveAsQuote(String shippingMethod) throws Throwable{
		clickUntil(CartObj.SAVE_AS_QUOTE,OrderLib.QUOTE_NAME ,"Save as quote Link");
		//type(OrderLib.QUOTE_NAME,quoteName, "Quote name");
		selectByVisibleText(SHIPPING_METHOD_SAVE_AS_QUOTE, shippingMethod, "shipping method");
		click(OrderLib.SAVE_AS_QUOTE_BTN, "save as quote button");
		if(isElementPresent(OrderLib.SAVE_QUOTE_MSG, "Success message")){
			 reporter.SuccessReport("Verify Success message", "Save as Quote - Successful message displayed","");
		 }else{
			 reporter.failureReport("Verify Success message ", "Save as Quote - Successful message not displayed ",""); 
		 
		}
	}
	
	/**
	 * Method to get the quote number of the quote
	 * @return
	 * @throws Throwable
	 */
	public String getQuoteNumber() throws Throwable{
		String quoteNumber=getText(GET_QUOTE_NUMBER, "Quote number");
		return quoteNumber;
	}
	
	
	/**
	 * Method is used to verify Standard Price
	 * 
	 * @throws Throwable
	 */
	public String verifyStandardPrice() throws Throwable {
		String standardPrice[] = null;
		String standardPrice1 = null;
		waitForVisibilityOfElement(STANDARD_PRICE, "Insight Standard Price ");
		if (isElementPresent(STANDARD_PRICE, "Insight Standard Price")) {
			String SPrice = getText(STANDARD_PRICE, "Regualar Price");

			standardPrice = SPrice.split(":");
			standardPrice = SPrice.split("USD");
			standardPrice1 = standardPrice[1].replace("$", "");
			standardPrice1 = standardPrice1.trim();
			reporter.SuccessReport("Verify Insight Standard Price on Products Details Page",
					"Insight Standard Price Exist", "");
		} else {
			reporter.failureReport("Verify Insight Standard Price on Products Details Page",
					"Insight Standard Price Not  Exist", "");
		}

		return standardPrice1;
	}

	/**
	 * Method is used to verify Discount
	 * 
	 * @throws Throwable
	 */
	public void verifyDiscount() throws Throwable {
		waitForVisibilityOfElement(DISCOUNT, "Discount % on Products Details Page");
		if (isElementPresent(DISCOUNT, "Discount % on Products Details Page")) {
			reporter.SuccessReport("Verify Discount % on Products Details Page", "Discount % Exist", "");
		} else {
			reporter.failureReport("Verify Discount % on Products Details Page", "Discount % Not  Exist", "");
		}
	}

	public String getDiscountPrice() throws Throwable {
		String amount[] = null;
		String price = null;
		waitForVisibilityOfElement(DISCOUNT_PRICE, "Regualar Price");
		if (isElementPresent(DISCOUNT_PRICE, "Regualar Price")) {
			String SPrice = getText(DISCOUNT_PRICE, "Regualar Price");

			amount = SPrice.split("USD");
			price = amount[1].replace("$", "");
			price = price.trim();

		}

		return price;
	}

	public void validateStandardDiscountPrice(String StandardPrice, String Discountprice) throws Throwable {
		if (!StandardPrice.equals(Discountprice)) {
			reporter.SuccessReport("Verify Standard Price and Discounted Price on Products Details Page",
					"Standard Price and Discounted Price are Different", "");
		} else {
			reporter.failureReport("Verify Standard Price and Discounted Price on Products Details Page",
					"Standard Price and Discounted Price are Not Different", "");
		}
	}

	public void verifyMSRPPrice() throws Throwable {
		if (isElementPresent(MSRP_PRICE, "MSRP Price")) {

			String amount[] = null;
			String price = null;

			String MSRPPrice = getText(MSRP_PRICE, "Regualar Price");

			amount = MSRPPrice.split("MSRP:");
			price = amount[1].split("Discount")[0];
			price = price.trim();

			if (price != null) {
				reporter.SuccessReport("Verify MSRP Price on Quote Details Page", "MSRP Price is Not Exists", "");
			} else {
				reporter.failureReport("Verify MSRP Price on Quote Details Page", "MSRP Price is Exists", "");
			}
		}
	}

	public void verifyDiscountPrice() throws Throwable {
		if (isElementPresent(MSRP_PRICE, "Discount Price")) {

			String amount[] = null;
			String discount = null;

			String DiscountPrice = getText(MSRP_PRICE, "Discount Price");

			amount = DiscountPrice.split("Discount");
			discount = amount[1].split(":")[1];

			if (discount != null) {
				reporter.SuccessReport("Verify Discount% on Quote Details Page", "Discount Of is Exists", "");
			} else {
				reporter.failureReport("Verify Discount% on Quote Details Page", "Discount Of is Not Exists", "");
			}
		}
	}

	public void continueToCheckout() throws Throwable {
		if (isElementPresent(CartObj.CONTINUE_TO_CHECKOUT, "Continue to check out"))
			click(CartObj.CONTINUE_TO_CHECKOUT, "Continue to check out");
	}
	/**
	 * Method is used to click on Quote number
	 * 
	 * @throws Throwable
	 */
	public void quoteNumberLink() throws Throwable {
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 500)");
		if (isElementPresent(QUOTE_NUMBER, "invoice number ")) {
			click(QUOTE_NUMBER, "Click on quote number ");
			reporter.SuccessReport("Verify quote Details POPUP", " quote Details POPUP exists", "");
		}

	}

	/**
	 * Method is used to click on Quote number
	 * 
	 * @throws Throwable
	 */
	public void quoteNumberInTable(String quoteName) throws Throwable {
		Thread.sleep(5000);
		
		if(isElementPresent(getValueFromQuoteTable(quoteName), "Quote Details ")) {		
		reporter.SuccessReport("Verify the Created Quote is saved in the Last Five Quotes", " Created Quote is saved in the Last Five Quotes", "");
		}
		}
	
	/**
	 * Method is used to click on Quote number
	 * 
	 * @throws Throwable
	 */
	public void descInAdvSearch() throws Throwable {
	
		if (isElementPresent(DESCENDING, "invoice number ")) {
			click(DESCENDING, "Click on quote number ");			
		}

	}
	/**
	 * Method is used to click on Quote number
	 * 
	 * @throws Throwable
	 */
	public void quoteNumberInAdvSrchTable(String quoteName,String referNumber) throws Throwable {
		Thread.sleep(5000);
		
		if (isElementPresent(getAdvSearchQuoteFormTable(quoteName), "Quote Details ")) {		
			click(getAdvSearchQuoteFormTable(referNumber), "Click on quote number in Quick History ");
			reporter.SuccessReport("Verify the Created Quote is saved ", " Created Quote is saved in the Last Five Quotes", "");
		}
		else{
			reporter.failureReport("Verify the Created Quote is saved ", " Created Quote is not  saved in the Last Five Quotes", "");
		}

	}
	
	/**
	 * Method is used to click on Quote number
	 * 
	 * @throws Throwable
	 */
	public void quoteNumberInRecentQuoteTable(String referNumber,String quoteName) throws Throwable {
		Thread.sleep(5000);
		
		if (isElementPresent(getValueFromRecentQuoteTable(quoteName), "Quote Details ")) {		
						reporter.SuccessReport("Verify the Created Quote is saved in the  Quotes", " Created Quote is saved in the Quotes", "");
		}
		else{
			reporter.failureReport("Verify the Created Quote is saved in the  Quotes", " Created Quote is not  saved in the Quotes", "");
		}

	}
	/**
	 * Method is used to click on Quote number
	 * 
	 * @throws Throwable
	 */
	public void verifyNoRecords() throws Throwable {
		Thread.sleep(5000);
		
		if (isElementPresent(RECORD_ERRORMSG, "Record error msg")) {		
						reporter.SuccessReport("Verify Search Results Page", " Results does not Exist", "");
		}
		else{
			reporter.failureReport("Verify Search Results Page", " Results  Exist", "");
		}

	}
	/**
	 * Method is used to click on Quote number
	 * 
	 * @throws Throwable
	 */
	public void clickQuoteNumberLink(String noOfCharacters) throws Throwable {
        refreshPage();
		if (isElementPresent(clickQuoteNumLink(noOfCharacters), "Quote Number Link in Quote History Page")) {
			click(clickQuoteNumLink(noOfCharacters), "Quote Number Link in Quote History Page");			
		}


	}
	
	/**
	 * Method is used to click on Quote number
	 * 
	 * @throws Throwable
	 */
	public List<String> actualContent() throws Throwable {
		Thread.sleep(5000);		
		
		String quoteNumber =getText(QUOTE_NAME,"Quote Number");
		String quoteName =getText(QUOTE_NAME,"Quote Name");
		String DateEntered=getText(QUOTE_NAME,"Date Entered");
		String ExpirationDate =getText(QUOTE_NAME,"ExpirationDate");
		String AccountName= getText(QUOTE_NAME,"Account Name");
    	String AccountNumber=getText(QUOTE_NAME,"Account Number");
    	ArrayList<String> actuelContent = new ArrayList<String>();
    	actuelContent.add(quoteNumber);
    	actuelContent.add(quoteName);
    	actuelContent.add(DateEntered);
    	actuelContent.add(ExpirationDate);
    	actuelContent.add(AccountName);
    	actuelContent.add(AccountNumber);
	  return actuelContent;
	}
	
	public void verifyExportFileInQuoteHistory(String sheetName, String rowNumber,String columnHeaders) throws Throwable {
		//int rowNumber 		= 1; 		// zero based index
		String sfile = "C:/Users/e004303/Downloads/orderhistory.xls";
		File file=new File(sfile);
		List<String> downloadedExcelContent = CommonLib.readRowFromExcel(sfile, sheetName,Integer.parseInt(rowNumber));
		List<String> acutalContent   = actualContent();
		System.out.println("Compare content"+downloadedExcelContent.equals(acutalContent));
		if(downloadedExcelContent.equals(acutalContent)) {
			reporter.SuccessReport(columnHeaders, "are avilable", "");
		}
		else {
			reporter.failureReport(columnHeaders, "are not avilable", "");
		}
		System.out.println("File Deletion :"+file.delete());
		if(file.exists()) {
			Runtime.getRuntime().exec("Excel.exe");
			reporter.SuccessReport("Verify ExportCart Excel File", " Excel File is Closed", "");
		}
		
		
	}
	
	/**
	 * Method is used to click on export to excel
	 * 
	 * @throws Throwable
	 */
	public void clickExportToExcelButton() throws Throwable {
		waitForVisibilityOfElement(EXPORTTOEXCEL, "ExportTO Excel");
		if (isElementPresent(EXPORTTOEXCEL, "Export to Excel ")) {
			click(EXPORTTOEXCEL, "Export to excel");			
		}

	}
	

	
	/**
	 * Method is used to click on Quote number
	 * 
	 * @throws Throwable
	 */
	public void getDelailsOnQuotePage() throws Throwable {
		
		waitForVisibilityOfElement(QUOTEHISTORY_GLOSS, "Quote History Glass Present");
		if (isElementPresent(QUOTEHISTORY_GLOSS, "Quote History Glass Present")) {
			reporter.SuccessReport("Verify Quote History Glossary on Quote Details Page", " Quote Summary  Exist", "");	
				String MFRPART=	getText(MFR_PART,"MFR PART");
				String insightPart=getText(INSIGHT_PART,"InsightPart");
				String description=	getText(DESCRIPTION,"Description");
				String stock=	getText(STOCK,"STOCK");
				String qty=	getText(QTY,"quantity");
				String quoteUnit=getText(QUOTEDUNIT,"QUOTEUNIT");
				String quotePrice=	getText(QUOTEDPRICE,"QUOTEDPRICE");
				String subTotal=	getText(SUBTOTAL,"Subtotal");
				String shipping=	getText(SHITPPING,"Shipping");
				String tax=	getText(TAX,"Tax");
				String total=	getText(QUOTEDPRICE,"total");
	   reporter.SuccessReport("MFRPART:"+MFRPART+"INSIGHT_PART:"+insightPart+"DESCRIPTION:"+description+"STOCK:"+stock+"Quantity:"+qty+"quoteUnit:"+quoteUnit+"QUOTEDPRICE:"+quotePrice+"", 
			  "Subtotal:"+subTotal+" Shipping:"+shipping+"Tax:"+tax+"Total:"+total+"", "");	
				
		}
		else {
			reporter.SuccessReport("Verify Quote History Glossary on Quote Details Page", " Quote Summary does not Exist", "");	
		}

	}
	
	/**
	 * Method is used to verify print popup
	 * 
	 * @throws Throwable
	 */
	public void verifyPrint() throws Throwable {
		waitForVisibilityOfElement(PRINT, "Print Popup");
		if (isElementPresent(PRINT, "Print Popup ")) {
			reporter.SuccessReport("Verify Print on Account Management - History Page", " Print Field  Exist", "");		
		}
		else{
			reporter.SuccessReport("Verify Print on Account Management - History Page", " Print Field doesnot Exist", "");	
		}

	}

	/**
	 * Method is used to verify email
	 * 
	 * @throws Throwable
	 */
	public void verifyEmailIcon() throws Throwable {
		waitForVisibilityOfElement(EMAIL, "Email Popup");
		if (isElementPresent(EMAIL, "Email Popup ")) {
			reporter.SuccessReport("Verify email on Account Management - History Page", " Email Field  Exist", "");		
		}
		else{
			reporter.SuccessReport("Verify email on Account Management - History Page", " Email Field doesnot Exist", "");	
		}

	}
	/**
	 * Method is used to verify edit this quote icon
	 * 
	 * @throws Throwable
	 */
	public void verifyEditThisQuoteIcon() throws Throwable {
		waitForVisibilityOfElement(EDIT, "Edit this Quote Popup");
		if (isElementPresent(EDIT, "Edit this Quote Popup ")) {
			reporter.SuccessReport("Verify Edit This Quote on Account Management - History Page", " Edit This Quote Field  Exist", "");		
		}
		else{
			reporter.SuccessReport("Verify Edit This Quote on Account Management - History Page", " Edit This Quote Field doesnot Exist", "");	
		}

	}
	/**
	 * Method is used to verify Delete this quote icon
	 * 
	 * @throws Throwable
	 */
	public void verifyDeleteThisQuoteIcon() throws Throwable {
		waitForVisibilityOfElement(DELETE, "Delete this Quote Popup");
		if (isElementPresent(DELETE, "Delete this Quote Popup ")) {
			reporter.SuccessReport("Verify Delete This Quote on Account Management - History Page", " Delete This Quote Field  Exist", "");		
		}
		else{
			reporter.SuccessReport("Verify Delete This Quote on Account Management - History Page", " Delete This Quote Field doesnot Exist", "");	
		}
	}
	/**
	 * Method is used to verify Quote History present
	 * 
	 * @throws Throwable
	 */
	public void verifyQuoteHistory() throws Throwable {
		waitForVisibilityOfElement(QUOTEHISTORY, "Quote History");
		if (isElementPresent(QUOTEHISTORY, "Quote History ")) {
			reporter.SuccessReport("Verify Quote History Results", "  Quote History Results  Exist", "");		
		}
		else{
			reporter.failureReport("Verify Quote History Results", "  Quote History Results  Doesnot Exist", "");	
		}
	}
	/**
	 * Method is used to verify Quote History present
	 * 
	 * @throws Throwable
	 */
	public void verifyQuoteDetails() throws Throwable {
		waitForVisibilityOfElement(QUOTEDETAILS, "Quote Details");
		if (isElementPresent(QUOTEDETAILS, " Quote Details ")) {
			reporter.SuccessReport("Verify  Quote Details Results", "  Quote Details Page  Exist", "");		
		}
		else{
			reporter.failureReport("Verify  Quote Details Results", "  Quote Details Page Doesnot  Exist", "");		
		}
	}
	

	/**
	 * Method is used to verify Delete this quote icon
	 * 
	 * @throws Throwable
	 */
	public void verifyAndClickQuoteNumberOnHistory( ) throws Throwable {
		waitForVisibilityOfElement(getQuoteNumberFRomQuickSearchHistory(), "Quote History");
		if (isElementPresent(getQuoteNumberFRomQuickSearchHistory(), "Quote History ")) {
			click(getQuoteNumberFRomQuickSearchHistory(),"click on QuoteNumber");
			reporter.SuccessReport("Click on the Quote Number on Quote History", " Quote Numbers Exists and Clickedt", "");		
		}
		else{
			reporter.failureReport("Click on the Quote Number on Quote History", " Quote Numbers Exists and Clickedt", "");	
		}
	}
	
	/**
	 * Method is used to verify Delete this quote icon
	 * 
	 * @throws Throwable
	 */
	public void clickOnQuickSearchQuoteNo(String quoteNumber) throws Throwable {
		waitForVisibilityOfElement(getQuickSearchQuoteFormTable(quoteNumber), "Quote Number");
		if (isElementPresent(getQuickSearchQuoteFormTable(quoteNumber), "Quote Number ")) {
			click(getQuickSearchQuoteFormTable(quoteNumber),"click on QuoteNumber");
			reporter.SuccessReport("Click on the Quote Number on Quick Search", " Quote Numbers Exists and Clicked on Quick Search", "");		
		}
		else{
			reporter.failureReport("Click on the Quote Number on Quick Search", " Quote Numbers Exists and Clicked Quick Search", "");	
		}
	}
	
	/**
	 * Method is used to verify Quote History present
	 * 
	 * @throws Throwable
	 */
	public void verifyConvertQuoteButton() throws Throwable {
	
		if (isElementPresent(orderObj.CONVERT_QUOTE_BTN, "Convert Quote")) {
			reporter.failureReport("Verify  Convert Quote button", " Convert Quote button  Exist", "");	
		}
		else{
		
			reporter.SuccessReport("Verify  Convert Quote button ", " Convert Quote button Doesnot Exist", "");	
		}
	}
	/**
	 * Method is used to quick search in invoice history
	 * 
	 * @param searchBy
	 * @param text
	 * 
	 * @throws Throwable
	 */
	public void quickSearchAndVerifySearchResults(String searchBy, String text) throws Throwable {

		
		waitForVisibilityOfElement(canadaObj.SEARCHBY_DROPDOWN, "Quick Search");
		if (isElementPresent(canadaObj.SEARCHBY_DROPDOWN, "Quick Search")) {
			click(canadaObj.SEARCHBY_DROPDOWN, "SearchBy");
			click(canadaObj.getSearchByTextOrder(searchBy), "Search By");
			click(QUICK_SEARCH_TEXT, "Click on Text");
			type(QUICK_SEARCH_TEXT, text, "Text ");
			Thread.sleep(5000);
			clickUntil(canadaObj.SEARCH,getQuoteNumberFRomQuickSearchHistory() ,"Search");
			waitForVisibilityOfElement(invoiceHistoryObj.searchResultsTable, "search results table");
			if (isElementPresent(invoiceHistoryObj.searchResultsTable, "search results table")) {
					reporter.SuccessReport("Verify searchresults in Invoice hisory page  ",
							" results are displayed",searchBy);
			}else {
				
					reporter.failureReport("Verify search results in Invoice hisory page   ",
							"Search results are not displayed", searchBy);
				}
			} 
		}
	/**
	 * Method is used to verify Quote History present
	 * 
	 * @throws Throwable
	 */
	public void verifyErrorMsg() throws Throwable {
	
		if (isElementPresent(ERROR_MSG, "Error Msg Present")) {
			reporter.SuccessReport("Verify  Erorr Message", "Error Message is present", "");	
		}
		else{
		
			reporter.failureReport("Verify  Erorr Message", "Error Message is not present", "");	
		}
	}
	
	/**
	 * Method is used to verify Quick shop error msg
	 * 
	 * @throws Throwable
	 */
	public void verifyQuickShopErrorMsg() throws Throwable {
	
		if (isElementPresent(QUICKSHOP_ERROR_MSG, "Error Msg Present")) {
			reporter.SuccessReport("Verify quick shop Erorr Message", " quick shop Error Message is present", "");	
		}
		else{
		
			reporter.failureReport("Verify quick shop Erorr Message", "quick shop Error Message is not present", "");	
		}
	}
	
	/**
	 * This method is to add products By Quick shop
	 * 
	 * @throws Throwable
	 */
	public void verifyAdditemsbyQuickshop(String searchItem) throws Throwable {
		waitForVisibilityOfElement(CartObj.QUICK_SHOP_ITEM_FIELD, "QUICK SHOP ITEM FIELD");
		type(CartObj.QUICK_SHOP_ITEM_FIELD, searchItem, "QUICK SHOP ITEM FIELD");
		if(isElementPresent(CartObj.ADD_BUTTON_IN_QUICK_SHOP, "ADD BUTTON IN QUICK SHOP")){
			click(CartObj.ADD_BUTTON_IN_QUICK_SHOP, "ADD BUTTON IN QUICK SHOP");
			reporter.SuccessReport("Add Items link  in Quick Shop on Cart Page", " Add Items button Exists and Clicked", "");	
		}
		else{
			reporter.failureReport("Add Items link  in Quick Shop on Cart Page", " Add Items button Exists and Clicked", "");	
		}
		
	}
	/**
	 * Method is used to verify Quote History present
	 * 
	 * @throws Throwable
	 */
	public void verifyContactName(String ContactName) throws Throwable {
		
		if (isElementPresent(getContactName(ContactName), " ContactName")) {
			reporter.SuccessReport("Validate the Contact name on Receipt page", " Expected Contact name Exis", "");		
		}
		else{
			reporter.failureReport("Validate the Contact name on Receipt page", " Expected Contact name does not Exis", "");		
		}
	}
	/**
	 * Method to Add additonal information 
	 * @param bussinessUnit
	 * @param operationalUnit
	 * @param locationCode
	 * @param department
	 * @param account
	 * @param entityName
	 * @param endUsercontact
	 * @param ordercontact
	 * @param licence
	 * @throws Throwable
	 */
		public void addAdditionalInformation(String bussinessUnit,String operationalUnit,String locationCode,String department,String account,String entityName,String endUsercontact,String ordercontact,String licence) throws Throwable{
			
			if(isElementPresent(BUSSINESSUNIT, "Bussiness Unit") ){
				type(BUSSINESSUNIT,bussinessUnit , "Bussiness Unit");
				type(OPERATIONAL_UNIT, operationalUnit, "Operational Unit");
				type(LOCATION_CODE, locationCode , "Bussiness Unit");
				type(DEPARTMENT, department, "Department");
				type(ACCOUNT, account , "Account");
				selectByVisibleText(ENTITYNAME, entityName, "Entity Name");
				type(END_USER_CONTACT, endUsercontact, "End User contact");
				type(ORDER_CONTACT, ordercontact , "OrderContact");
				click(OrderObj.CONTINUE_BTN, "Continue button");
				type(LICENCE_LINELEVEL1, licence , "Licence");
				type(LICENCE_LINELEVEL2, licence , "Licence");
				click(CONT_BTN, "Continue button");
			
	}
		}
		
		/**
		 * Method is to click on the account tools side menu and select options
		 * 
		 * @param toolsMenuName
		 * @param dropDown
		 * @throws Throwable
		 */
		public void clickToolsMenu(String toolsMenuName, String dropDown) throws Throwable {
			
			click(canadaObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
			click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
		}
}
