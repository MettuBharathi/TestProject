package com.insight.Lib;

import com.insight.ObjRepo.CMTObj;
import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.CommonObj;
import com.insight.ObjRepo.SewpObj;
import com.insight.ObjRepo.productsDisplayInfoObj;
import com.insight.accelerators.ActionEngine;
import com.insight.report.ConfigFileReadWrite;
import com.insight.report.ReporterConstants;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class SewpLib extends SewpObj {

	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CartLib cartLib = new CartLib();
	CommonLib commonLib = new CommonLib();
	public OrderLib orderLib = new OrderLib();
	public ShipBillPayLib shipbLib = new ShipBillPayLib();

	/**
	 * Method is to verify the contract name on the cart page
	 * 
	 * @throws Throwable
	 */
	public void verifyMfr(String searchItem) throws Throwable {
		String mfrDetails = null;
		if (isElementPresent(MFR_SWEP, "Mfr.#")) {

			reporter.SuccessReport("Verify Mfr Part # on Product Details page",
					"Mfr Part # on Product Details page verification is successful", "");
			mfrDetails = getText(MFR_SWEP, "Mfr.#");
			if (mfrDetails.equals(searchItem)) {
				reporter.SuccessReport("Verify  is dispalyed beside 'Mfr.#' ", " is dispalyed beside 'Mfr.#'",
						searchItem);
			}
		 else {
			reporter.failureReport("Verify  is dispalyed beside 'Mfr.#' ", " is not dispalyed beside 'Mfr.#'",
					searchItem);
		}
		}
	}



	
	/*
	 * PURPOSE OF METHOD : REUASABLE METOHD TO SEARCH A PRODUCT
	 * 
	 * @author : Cigniti
	 */
	public void searchProductforSWEP(String SearchItem) throws Throwable {
		waitForVisibilityOfElement(SEARCH_PRODUCT_SWEP, "SEARCH FIELD");
		if(isElementPresent(SEARCH_PRODUCT_SWEP, "SEARCH FIELD")){
			type(SEARCH_PRODUCT_SWEP, SearchItem, "SEARCHFIELD");
			// click(CartObj.SEARCH_BUTTON," SEARCH BUTTON");
			sendKeysActionsEnter(SEARCH_PRODUCT_SWEP);
			reporter.SuccessReport("Search field on SEWP landing page","Search field exists and Part Number is entered", SearchItem);
		
		}
		else
		{
			reporter.failureReport("Search field on SEWP landing page","Search field does not exists", SearchItem);
		}
		
	}

	/**
	 * Method is to verify the contract name on the cart page
	 * 
	 * @throws Throwable
	 */
	public void verifyInsight(String searchItem) throws Throwable {
		String insightDetails = null;
		if (isElementPresent(INSIGHT_SWEP, "Insight Part # / CLIN#")) {

			reporter.SuccessReport("Verify Insight Part # / CLIN# on Product Details page",
					"Insight Part # / CLIN# on Product Details page verification is successful", "");
			insightDetails = getText(INSIGHT_SWEP, "Insight Part # / CLIN#");
			
			if (insightDetails.equals(searchItem)) {
				reporter.SuccessReport("Verify  is dispalyed beside 'Insight Part # / CLIN#' ",
						" is dispalyed beside 'Insight Part # / CLIN#'", searchItem);
			}
		} else {
			reporter.failureReport("Verify  is dispalyed beside 'Insight Part # / CLIN#' ",
					" is not dispalyed beside 'Insight Part # / CLIN#'", searchItem);
		}

	}

	/**
	 * PURPOSE: This method is to increase quantity in Add to cart
	 * in shopping cart page.
	 * @customization author : CIGNITI
	 */
	public void increaseNumberOfDisplayedProduct() throws Throwable {
		waitForVisibilityOfElement(NUMBER_PICKER_IN_PRODUCTQA_DISPLAY_PAGE, "NUMBER PICKER IN PRODUCT DISPLAY");
		if(isElementPresent(NUMBER_PICKER_IN_PRODUCTQA_DISPLAY_PAGE, "NUMBER PICKER IN PRODUCT DISPLAY")){
		
		click(NUMBER_PICKER_IN_PRODUCTQA_DISPLAY_PAGE, "NUMBER PICKER IN PRODUCT DISPLAY");
		reporter.SuccessReport("Verify and enter Quantity on Product Detail Page "," Quantity Field exists and entered", "");
		}
		else
		{
			reporter.failureReport("Verify and enter Quantity on Product Detail Page "," Quantity Field does not exist", "");
		
		}
	}

	/**
	 * This method is to click on More prices Available link and verify whether
	 * all the contracts are available , Open market price availability and your
	 * price availability.
	 * 
	 * @throws Throwable
	 */
	public void clickOnProductQA() throws Throwable {
		if (isElementPresent(CartObj.NUMBER_PICKER_IN_PRODUCTQA_DISPLAY_PAGE, "NUMBER PICKER IN PRODUCT DISPLAY")) {
			
			reporter.SuccessReport("Verify  Product Detail Page Exists "," Product Detail Page is existed and Clicked", "");
			increaseNumberOfDisplayedProduct();
			clickOnAddtoCart();
			verifyContinueToCheckout();
			verifyContinueShopping();
		}
		else
		{
			reporter.failureReport("Verify  Product Detail Page Exists "," Product Detail Pagedoes not exist", "");	
		}
	}
	
	/**
	 * This method is to click on More prices Available link and verify whether
	 * all the contracts are available , Open market price availability and your
	 * price availability.
	 * 
	 * @throws Throwable
	 */
	public void clickOnProduct() throws Throwable {
		if (isElementPresent(CartObj.NUMBER_PICKER_IN_PRODUCTQA_DISPLAY_PAGE, "NUMBER PICKER IN PRODUCT DISPLAY")) {			
			clickOnAddtoCart();
			verifyContinueShopping();
		}
	}

	/**
	 * This method is to click on More prices Available link and verify whether
	 * all the contracts are available , Open market price availability and your
	 * price availability.
	 * 
	 * @throws Throwable
	 */
	public void clickMorePricesAndViewContractsinProductsQA() throws Throwable {
       if(isElementPresent(MORE_PRICES, "More prices available link",true)){
    	  click(MORE_PRICES, "More prices available link");
    	  reporter.SuccessReport("Verify and click 'More Prices Available' in Product Detail page"," 'More Prices Available' exists and clicked", "");
       }
       else 
       {
    	   reporter.failureReport("Verify and click 'More Prices Available' in Product Detail page"," 'More Prices Available' does not exists", "");  
       }
       
   		 cartLib.clickOnOpenMarketPrice();
       	
		increaseNumberOfDisplayedProduct();
		clickOnAddtoCartMorePrices();

		verifyContinueToCheckout();
		commonLib.closePopUp();
		if(isElementPresent(CART, "CART",true)){
		click(CART, "CART");
		 reporter.SuccessReport("Verify and click the Cart in the top nav"," Cart exists and clicked", "");
		}
		else
		{
			 reporter.failureReport("Verify and click the Cart in the top nav"," Cart does not exist", "");
		}
		cmtLib.handleWelcomeToInsightBetaPopUp();
	
		

	}

	/**
	 * This method is to click on Add To Cart More Prices available
	 * 
	 * @throws Throwable
	 */
	public void clickOnAddtoCartMorePrices() throws Throwable {
		waitForVisibilityOfElement(ADD_TO_CART, "ADD TO CART IN PRODUCT DISPLAY");
		if(isElementPresent(ADD_TO_CART, " ADD TO CART IN PRODUCT DISPLAY")){
		click(ADD_TO_CART, " ADD TO CART IN PRODUCT DISPLAY");
		reporter.SuccessReport("Click on ADD TO CART Button in Product Details Page ","ADD TO CART button exists and clicked", "");
		}
		else 
		{
			reporter.failureReport("Click on ADD TO CART Button in Product Details Page ","ADD TO CART button does not exists", "");	
		}

	}

	/**
	 * This method is to click on Add To Cart
	 * 
	 * @throws Throwable
	 */
	public void clickOnAddtoCart() throws Throwable {
		waitForVisibilityOfElement(ADD_TO_CARTS, "CONTINUE TO CHECKOUT");
		if(isElementPresent(ADD_TO_CARTS, " ADD TO CART IN PRODUCT DISPLAY")){
		click(ADD_TO_CARTS, " ADD TO CART IN PRODUCT DISPLAY");
		reporter.SuccessReport("Click on ADD TO CART Button in Product Details Page "," ADD TO CART button exists and clicked", "");
		}
		else 
		{
			reporter.failureReport("Click on ADD TO CART Button in Product Details Page "," ADD TO CART button does not exists", "");	
		}

	}

	/**
	 * This method is to click on continue to checkout
	 * 
	 * @throws Throwable
	 */
	public void verifyContinueToCheckout() throws Throwable {
		Thread.sleep(5000);
		
		waitForVisibilityOfElement(CONTINUE_TO_CHECKOUT, "CONTINUE TO CHECKOUT");
		if(isElementPresent(CONTINUE_TO_CHECKOUT, "CONTINUE TO CHECKOUT", true)){
			reporter.SuccessReport("Verify  Continue to Checkout on Purchasing Pop Up "," Continue To checkout existed ", "");
		}
		else 
		{
			reporter.failureReport("Verify  Continue to Checkout on Purchasing Pop Up "," Continue To checkout existed ", "");
		}
	}

	/**
	 * This method is to click on continue to checkout
	 * 
	 * @throws Throwable
	 */
	public void clickContinueToCheckout() throws Throwable {

		waitForVisibilityOfElement(CartObj.CONTINUE_TO_CHECKOUT, "CONTINUE TO CHECKOUT");
		if(isElementPresent(CONTINUE_TO_CHECKOUT, "CONTINUE TO CHECKOUT", true)){
			click(CartObj.CONTINUE_TO_CHECKOUT, "CONTINUE TO CHECKOUT");
			reporter.SuccessReport("Click Continue to Checkout on Purchasing Pop Up "," Click to continue to check out  exists and clicked", "");
		}
		else{
			reporter.SuccessReport("Click Continue to Checkout on Purchasing Pop Up "," Click to continue to checkout does not exist", "");
		}
	}

	/*
	 * PURPOSE OF METHOD : REUASABLE METHOD TO continue To Shopping
	 * 
	 * @author : Cigniti
	 */
	public void verifyContinueShopping() throws Throwable {
		waitForVisibilityOfElement(CartObj.CONTINUE_TO_SHOPPING, "CONTINUE TO SHOPPING");
		if(isElementPresent(CartObj.CONTINUE_TO_SHOPPING, "CONTINUE TO SHOPPING", true))
		{
			click(CartObj.CONTINUE_TO_SHOPPING, "continue shoping");
			reporter.SuccessReport("Verify and click Continue Shopping in Popup "," Continue Shopping button exists and clicked", "");
		}
		else{
			reporter.SuccessReport("Verify and click Continue Shopping in Popup "," Continue Shopping button does not  exists ", "");
		}
		

	}

	/**
	 * This method is to click on Create an Account
	 * 
	 * @throws Throwable
	 */
	public void CreateAnAccount(String testCaseName) throws Throwable {

		if (testCaseName.equalsIgnoreCase("SWP01_SEWPEndToEnd")) {

			waitForVisibilityOfElement(CREATE_AN_ACCOUNT, "CREATE AN ACCOUNT");
			if (isElementPresent(CREATE_AN_ACCOUNT, "CREATE AN ACCOUNT", true)) {
				click(CREATE_AN_ACCOUNT, "CREATE AN ACCOUNT");
			} else {

			}
		}

		else {
			waitForVisibilityOfElement(CREATE_AN_ACCOUNT_SEARCHPAGE, "CREATE AN ACCOUNT");
			if (isElementPresent(CREATE_AN_ACCOUNT_SEARCHPAGE, "CREATE AN ACCOUNT", true)) {
				click(CREATE_AN_ACCOUNT_SEARCHPAGE, "CREATE AN ACCOUNT");
			}

		}

	}

	/**
	 * This method is to click on Create an Account
	 * 
	 * @throws Throwable
	 */
	public void enterBillingInfo(String Orgnisation, String agency, String subAgency) throws Throwable {

		String SWEP_USERNAME = getRandomString(8);

		
	//	cmtLib.handleWelcomeToInsightBetaPopUp();
		typeText(EMAIL_ADDRESS, SWEP_USERNAME + "@test.com", "Email Address");
		selectOrganisation(Orgnisation);
		// Select agency
		selectAnAgency(agency);
		Thread.sleep(3000);
		// Select sub agency
		selectAnSubAgency(subAgency);
	}

	/**
	 * This method is to click on Create an Account
	 * 
	 * @throws Throwable
	 */
	public void enterLoginInfo(String lastName, String phoneNumber, String addressOne, String city, String state,
			String title, String zipCode) throws Throwable {

		String SWEP_USERNAME = getRandomString(8);
		// Enter first Name
		typeText(FIRST_NAME, SWEP_USERNAME, "First Name");
		// Enter Last Name
		typeText(LAST_NAME, lastName, "Last Name");
		// Enter Phone Number
		typeText(PHONE_NUMBER, phoneNumber, "PhoneNumber");
		// Enter title
		selectJobTitle(title);
		// Enter billing account Number
		typeText(BILLING_ACCOUNT_NAME, SWEP_USERNAME, "User Name");
		typeText(ADDRESS_ONE, addressOne, "Address One");
		typeText(CITY, city, "City");
		selectState(state);
		typeText(ZIP_CODE, zipCode, "Zipcode");
		typeText(USER_NAME, SWEP_USERNAME, "UserName");
		typeText(PWD, SWEP_USERNAME + "$", "Password");
		typeText(CRM_PWD, SWEP_USERNAME + "$", "Confirm Password");
		waitForVisibilityOfElement(CREATE, "Create");
		click(CREATE, "Create");

		if (isElementPresent(EMAIL_ERRORMSG, "Save cart error message")) {
			reporter.SuccessReport("Enter Correct Email Address  ",
					"Please provide a valid Federal Email address is displayed", "");
			clearData(EMAIL_ADDRESS);
			typeText(EMAIL_ADDRESS, SWEP_USERNAME + "@mail.gov", "Email Address");
		
			click(CREATE, "Create");
		} 
	}

	/*
	 * PURPOSE OF METHOD : click on favorite shipping address and select an
	 * adresses
	 * 
	 * @author : CIGNITI
	 */
	public void selectJobTitle(String jobTitle) throws Throwable {
		waitForVisibilityOfElement(SELECT_JOBTITLE, "JobTitle");
		click(SELECT_JOBTITLE, "JobTitle");
		click(selectJob(jobTitle), "JobTitle");

	}

	/*
	 * PURPOSE OF METHOD : click on favorite shipping address and select an
	 * adresses
	 * 
	 * @author : CIGNITI
	 */
	public void selectOrganisation(String Organization) throws Throwable {
		waitForVisibilityOfElement(SELECT_AN_ORGANISATION, "Organisation");
		click(SELECT_AN_ORGANISATION, "Organisation");
		click(selectFavouriteOrganisation(Organization), "Organisation");

	}

	/*
	 * PURPOSE OF METHOD : click on agency and selct agency
	 * 
	 * @author : CIGNITI
	 */
	public void selectAnAgency(String agency) throws Throwable {
		waitForVisibilityOfElement(SELECT_AGENCY, "Agency");
		click(SELECT_AGENCY, "Agency");
		click(selectAgency(agency), "Agency");

	}

	/*
	 * PURPOSE OF METHOD : click on agency and selct agency
	 * 
	 * @author : CIGNITI
	 */
	public void selectAnSubAgency(String subAgency) throws Throwable {
		waitForVisibilityOfElement(SELECT_SUBAGENCY, "SubAgency");
		click(SELECT_SUBAGENCY, "SubAgency");
		click(selectSubAgency(subAgency), "SubAgency");

	}

	/*
	 * PURPOSE OF METHOD : click on agency and selct agency
	 * 
	 * @author : CIGNITI
	 */
	public void selectState(String state) throws Throwable {
		waitForVisibilityOfElement(SELECT_STATE, "State");
		click(SELECT_STATE, "state");
		click(selectStates(state), "State");

	}
	
	/*
	 * PURPOSE OF METHOD : click on Save and create account
	 *  
	 * @author : CIGNITI
	 */
	public void saveAndCreateAccount() throws Throwable {
		waitForVisibilityOfElement( SAVEAND_CREATE_AN_ACCOUNT, "Save and create an Account");
		if(isElementPresent(SAVEAND_CREATE_AN_ACCOUNT, "Save and create an Account")){		
		click(SAVEAND_CREATE_AN_ACCOUNT, "Save and create an Account");
		reporter.SuccessReport("Click SAVE AND CREATE ACCOUNT button in address confirmation popup", " SAVE AND CREATE ACCOUNT button exists and clicked" ,"" );
		}
		else
		{
			reporter.failureReport(" Click SAVE AND CREATE ACCOUNT button in address confirmation popupp", " SAVE AND CREATE ACCOUNT button does not exists" ,"" );
		}
		
		
		}
	
	public void searchProduct(String SearchItem) throws Throwable{
		waitForVisibilityOfElement(SEARCH, "SEARCH FIELD");
		type(SEARCH, SearchItem, "SEARCHFIELD");
		// click(CartObj.SEARCH_BUTTON," SEARCH BUTTON");
		sendKeysActionsEnter(SEARCH);
        Thread.sleep(5000);
	}
	/**
	 * Method is to fill the Reporting Fields on the Line level info section
	 * @param reportingField4
	 * @param reportingField5
	 * @param reportingField6
	 * @throws Throwable
	 */
	public void enterReportingDetailsInLineLevelInfoSection(String reportingField4,String reportingField5,String reportingField6) throws Throwable{
		if(isElementPresent(REPORTING_FIELD_4, "Reporting Field 4")){
		type(REPORTING_FIELD_4, reportingField4, "Reporting Field 4");
		type(REPORTING_FIELD_5, reportingField5, "Reporting Field 5");
		type(REPORTING_FIELD_6, reportingField6, "Reporting Field 6");
		click(LLI_CONTINUE_BTN, "Continue button");
		reporter.SuccessReport("Verify reporting fields displayed in the Line level information section","Reporting fields are displayed Line level information","");
		}else{
			reporter.failureReport("Verify reporting fields displayed in the Line level information section","Reporting fields are not displayed Line level information","");
		}
	}
	
	/**
	 * Method is to click on the Continue button in the Ship Bill pay page.
	 * @throws Throwable
	 */
	public void clickBillingAddressContinueButton() throws Throwable{
		waitForVisibilityOfElement(SHIPPINGADDRESS_CONTINUE_BTN, "Continue button of Billing address");
		if(isElementPresent(SHIPPINGADDRESS_CONTINUE_BTN, "Continue button of Billing address ",true)){
		Thread.sleep(1000);
		click(SHIPPINGADDRESS_CONTINUE_BTN, "Continue button of Billing address address");  // clicking continue in Shipping address ,Shipping options
		 
	  }
	
	}
	
	/**
	 * 
	 * @param orderLink
	 * @throws Throwable
	 */
	public void clickonorderNumLinkinRecentorders(String orderLink) throws Throwable {
		Thread.sleep(5000);
		click(orderlinkInOrderHistory(orderLink), "select placed order from recent orders");
	}

	/**
	 * 
	 * @param item1
	 * @throws Throwable
	 */
	public void verifyPartNumberInOrderdetails(String item1) throws Throwable {

		String PartNum = getText(getPartNuminOrderdetails, "Part Number item in order details page");
		
			if (PartNum.equals(item1)) {
				reporter.SuccessReport("Verify product part Number displayed in order details",
						"Part Number is displayed",item1);
			} else {
				reporter.failureReport("Verify product part Number displayed in order details",
						"Part Number displayed is : " + PartNum + " .Expected is: " , item1);
			}
		

	}


}


