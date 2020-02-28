package com.insight.Lib;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.CommonObj;
import com.insight.ObjRepo.MarriottIntlCorpObj;
import com.insight.ObjRepo.OrderObj;
import com.insight.ObjRepo.productsDisplayInfoObj;

public class MarriottIntlCorpLib extends MarriottIntlCorpObj {

	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CartLib cartLib = new CartLib();
	CommonLib commonLib = new CommonLib();

	public void handleinsightpopup() throws Throwable {
		CMTLib cmtLib = new CMTLib();
		if (driver.findElement(CartObj.POP_UP_EMAILID).isDisplayed()) {
			cmtLib.handleWelcomeToInsightBetaPopUp();
		} else {
			// do nothing
		}
	}

	public void SearchAndswitchtoaccount(String AccountName) throws Throwable {
		waitForVisibilityOfElement(SEARCHFIELD, "Search field");
		click(SEARCHFIELD, "Search field");
		type(SEARCHFIELD, AccountName, "Account Name");
		click(SEARCHBUTTON, "Search Button");
		waitForVisibilityOfElement(SEARCHRESULT(AccountName), "Search Result");
		if (isElementPresent(SEARCHRESULT(AccountName), "Search Result")) {
			click(SWITCHTOACCOUNT(AccountName), "Switch to account");
			waitForVisibilityOfElement(SWITCHTOACC_POPUP, "Switch to account popup");
			click(CONTINUEBUTTON_SWITCHTOACC, "Continue Button");
			reporter.SuccessReport("Switch to Account", "Switched To Gitve Account", "");
		} else {
			reporter.failureReport("Switch to Account", "Unable to Switch To Gitve Account", "");
		}
	}

	/**
	 * This method is to verify Welcome to e Procurement page
	 * 
	 * 
	 * @throws Throwable
	 */
	public void VerifyWelcometoeProcurementpage() throws Throwable {
		waitForVisibilityOfElement(WELCOMTO_E_PROCURMNTPAGE, "Welcome to e Procurement page");
		if (isElementPresent(WELCOMTO_E_PROCURMNTPAGE, "User ID")) {
			reporter.SuccessReport("Welcome to e Procurement page", "Welcome to e Procurement page is Visible", "");

		} else {
			reporter.failureReport("Welcome to e Procurement page", "Welcome to e Procurement page is Not Visible", "");
		}
	}

	/**
	 * This method is to verify Company standards link
	 * 
	 * 
	 * @throws Throwable
	 */
	public void CompanystandardslinkandProductGrp(String productGroup, String productName) throws Throwable {
		click(COMPANYSTANDARDS_PAGELINK, "Company Standards PageLink");
		isElementPresent(CartObj.Current_product_groups, " Current Product Groups page is opened");
		click(getcompanystandardsproductgroup(productGroup), "Product Group");
		click(CommonObj.getCompanyStandardsProductGroup(productGroup, productName),
				"select product from product group");
	}

	/**
	 * This method is to verify pratNum in Company standards Page
	 * 
	 * 
	 * @throws Throwable
	 */
	public void Verifypartnum(String partnum) throws Throwable {
		waitForVisibilityOfElement(VERIFYPART(partnum), "PartNum in Selected Product group");
		if (isElementPresent(VERIFYPART(partnum), "PartNum")) {
			reporter.SuccessReport("PartNum::", "PartNum in Selected Product group " + partnum + "", "");

		} else {
			reporter.failureReport("PartNum::", "PartNum in Selected Product group is Not Visible", "");
		}
	}

	/**
	 * This method is to verify Pratprice in Company standards Page
	 * 
	 * 
	 * @throws Throwable
	 */
	public void VerifypartPrice(String partprice) throws Throwable {
		waitForVisibilityOfElement(VERIFYPRICE(partprice), "Price of Partnum in Selected Product group");
		if (isElementPresent(VERIFYPRICE(partprice), "PartPrice")) {
			reporter.SuccessReport("Price::", "Price of PartNum in Selected Product group " + partprice + "", "");

		} else {
			reporter.failureReport("Price of PartNum in Selected Product group",
					"Price of PartNum in Selected Product group is Not Visible", "");
		}
	}

	/**
	 * This method is to set quantity and click on Addtoorder in Company
	 * standards Page
	 * 
	 * 
	 * @throws Throwable
	 */
	public void Setquantity(String value, String PartNum) throws Throwable {
		if (isElementPresent(getQuantityOfPart(PartNum), "Part Quantity")) {
			click(getQuantityOfPart(PartNum), "Qunatity");
			type(getQuantityOfPart(PartNum), value, "Qunatity");
			reporter.SuccessReport("Quantity of PartNum in Selected Product group",
					"Qunatity of PartNum in Selected Product group Is updated to::" + value + "", "");

		} else {
			reporter.failureReport("Quantity of  PartNum in Selected Product group",
					"PartNum in Selected Product group is Not Visible", "");
		}
		click(CommonObj.ADD_TO_ORDER, "ADD To Order Button is Clicked");
		waitForVisibilityOfElement(ADDTOCARTDAILOGUE_BOX, "Item Added To cart");
	}

	/**
	 * Method is to set quantity for all the product group items
	 * 
	 * @param quantity
	 * @throws Throwable
	 */
	public void setQuantityForProductGroup(String quantity) throws Throwable {

		if (isElementPresent(CommonObj.QUANTITY_COMPANY_STANDARDS, "Quantity")) {
			List<WebElement> myQtyList = driver.findElements(CommonObj.QUANTITY_COMPANY_STANDARDS);
			for (int i = 0; i < myQtyList.size(); i++) {
				// type(CommonObj.QUANTITY_COMPANY_STANDARDS, quantity,
				// "Quantity");
				myQtyList.get(i).clear();
				myQtyList.get(i).sendKeys(quantity);
			}
		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void Closeiconofaddtocartatproductgrps() throws Throwable {
		click(CLOSE_ICON, "Close Pop_up");
	}

	/**
	 * This method is to Click on Cart And verify Cart Page
	 * 
	 * 
	 * @throws Throwable
	 */
	public void Clickoncart() throws Throwable {
		click(CartObj.CART, "Cart");
		click(CartObj.CART, "Cart");
		Thread.sleep(4000);
		if (isElementPresent(CartObj.CART, "Cart")) {
			reporter.SuccessReport("Cart Bread Crumb", "Cart Bread Crumb is verified", "");

		} else {
			reporter.failureReport("Cart Bread Crumb", "Cart Bread Crumb is Not verified", "");
		}
	}

	/**
	 * This method is to verify Price of Item in Cart Page
	 * 
	 * 
	 * @throws Throwable
	 */
	public void VerifyItemPriceincart(String partprice) throws Throwable {
		waitForVisibilityOfElement(ITMEPRICEINCART(partprice), "Price of Partnum in Selected Product group  in Cart");
		if (isElementPresent(ITMEPRICEINCART(partprice), "PartPrice")) {
			reporter.SuccessReport("Price of PartNum in Selected Product group in Cart ",
					"PartNum in Selected Product group" + partprice + " in Cart", "");

		} else {
			reporter.failureReport("Price of PartNum in Selected Product group in Cart",
					"Price of PartNum in Selected Product group is Not Visible  in Cart", "");
		}
	}

	/**
	 * This method is to verify Quantity of Item in Cart Page
	 * 
	 * @throws Throwable
	 */
	public void VerifyItemQuantityincart(String partprice) throws Throwable {
		waitForVisibilityOfElement(ITEMQUNTITYINCART(partprice), "Price of Partnum in Selected Product group  in Cart");
		if (isElementPresent(ITEMQUNTITYINCART(partprice), "PartPrice")) {
			reporter.SuccessReport("Qunatity of PartNum in Selected Product group in Cart ",
					"Qunatity of PartNum in Selected Product group is visible in Cart", "");

		} else {
			reporter.failureReport("Quantity of PartNum in Selected Product group in Cart",
					"Price of PartNum in Selected Product group is Not Visible  in Cart", "");
		}
	}

	/**
	 * This method is to Clickon saveasQuote And Navigate To SaveQuotepage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void SaveAsQuote() throws Throwable {
		Thread.sleep(4000);
		clickUntil(CartObj.SAVE_AS_QUOTE, SAVEASQUOTEPAGE, "Save as Quote");
		waitForVisibilityOfElement(SAVEASQUOTEPAGE, "Price of Partnum in Selected Product group");
		if (isElementPresent(SAVEASQUOTE_NAME, "Quote Name")) {
			reporter.SuccessReport("Save As Quote:: ", "Quote is saved", "");

		} else {
			reporter.failureReport("Save As Quote::", "Quote is Unable to save", "");
		}
	}

	public void typeattention(String Name) throws Throwable {
		click(ATTENTION_TEXTFIELD, "Attention Text filed");
		type(ATTENTION_TEXTFIELD, Name, "Attention Textfield");
	}
	/**
	 * This method is to verify QuoteName of Item in SaveQuotepage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void VerifyQuoteNameinsaveasquotepage() throws Throwable {
		if (isElementPresent(SAVEASQUOTE_NAME, "Quote Name")) {
			reporter.SuccessReport("Quote name", "Quote name is Visible", "");
		} else {
			reporter.failureReport("Quote name", "Quote name is Not Visible", "");
		}
	}

	/**
	 * This method is to verify QuoteText of Item in SaveQuotepage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void VerifyQuoteNotesinsaveasquotepage() throws Throwable {
		if (isElementPresent(SAVEASQUOTE_NOTES, "PartPrice")) {
			reporter.SuccessReport("Quote Notes ", "Quote Notes in Quote success msg", "");
		} else {
			reporter.failureReport("Quote Notes", "Quote Notes in Quote success msg is Not Visible", "");
		}
	}

	/**
	 * This method is to Click on Save as Quote in SaveQuotepage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void Toclicksaveasquoteincompanystandardspage() throws Throwable {
		click(SAVEASQUOTE_BUTTON, "Save as Quote Button");
	}

	/**
	 * This method is to verify QuoteRefNum in SaveQuoteSuccesspage
	 * 
	 * 
	 * @throws Throwable
	 */
	public String VerifySuccessmsgofQuoteandRefNum() throws Throwable {
		waitForVisibilityOfElement(SAVEASQUOTE_SUCCESPAGE, "Save as Quote Success Msg");
		String RefNum = getText(REFNUM_SAVEASQUTESSUCCESSPG, "RefNum");
		if (isElementPresent(REFNUM_SAVEASQUTESSUCCESSPG, "RefNum")) {
			reporter.SuccessReport("RefNum Of Quote ", "RefNum Of Quote" + RefNum + "", "");
		} else {
			reporter.failureReport("RefNum Of Quote", "RefNum Of Quote is Not Visible", "");
		}
		return RefNum;
	}

	/**
	 * This method is to verify QuoteName in SaveQuoteSuccesspage
	 * 
	 * @throws Throwable
	 */
	public String VerifyQuoteName() throws Throwable {
		String Quote_name = getText(QUOTENAME_SAVEASQUTESSUCCESSPG, "Quote Name");
		if (isElementPresent(QUOTENAME_SAVEASQUTESSUCCESSPG, "Quote Name")) {
			reporter.SuccessReport("Quote name", "Quote name is visible::"+Quote_name+"", "");
		} else {
			reporter.failureReport("Quote name", "Quote name is Not Visible", "");
		}
		return Quote_name;
	}

	/**
	 * This method is to verify QuoteNotes in SaveQuoteSuccesspage
	 * 
	 * @throws Throwable
	 */
	public void VerifyQuoteNotes() throws Throwable {
		if (isElementPresent(QUOTENOTES_SAVEASQUTESSUCCESSPG, "PartPrice")) {
			reporter.SuccessReport("Quote Notes ", "Quote Notes in Quote success msg is visible", "");
		} else {
			reporter.failureReport("Quote Notes", "Quote Notes in Quote success msg is Not Visible", "");
		}
	}

	public void SelectCPPFRomAccounttools(String toolsMenuName, String dropDown, String productGroup,
			String productName) throws Throwable {
		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
		isElementPresent(CartObj.Current_product_groups, " Current Product Groups page is opened");
		click(getcompanystandardsproductgroup(productGroup), "Product Group");
		click(CommonObj.getCompanyStandardsProductGroup(productGroup, productName),
				"select product from product group");

	}

	/**
	 * This method is to click Add checkbox for selected CPP
	 * 
	 * 
	 * @throws Throwable
	 */
	public void Addcheckbox(String partnum) throws Throwable {
		if (driver.findElement(ADDCHECKBOX(partnum)).isSelected()) {
			reporter.SuccessReport("ADD CheckBox", "ADD CheckBox is Clicked", "");
		} else {
			click(ADDCHECKBOX(partnum), "ADD CheckBox");
		}
	}

	/**
	 * This method is to click ADDRADIOBUTTON for selected CPP
	 * 
	 * 
	 * @throws Throwable
	 */
	public void Addradiobutton(String partnum) throws Throwable {
		if (driver.findElement(ADDRADIOBUTTON(partnum)).isSelected()) {
			reporter.SuccessReport("ADD CheckBox", "ADD CheckBox is Clicked", "");
		} else {
			click(ADDCHECKBOX(partnum), "ADD CheckBox");
		}
	}

	public String TakeshippingaddressCompany() throws Throwable {
		waitForVisibilityOfElement(SHIPPINGADRESS_COMPANY, "Shipping Address Company");
		String company = getText(SHIPPINGADRESS_COMPANY, "Shipping Address Company Name").trim();
		return company;
	}

	public List<String> Takeshippingaddress() throws Throwable {
		waitForVisibilityOfElement(SHIPPINGADDRESS, "Shipping Address");
		List<String> address = new ArrayList<>();
		String address1 = getText(SHIPPINGADDRESS, "MFR_NUMBER_PRODUCT_DETAILS_PAGE").trim();
		address.add(address1);
		List<WebElement> myList = driver.findElements(SHIPPINGADDRESS_BR);
		for (int i = 0; i < myList.size(); i++) {
			address.add(myList.get(i).getText().trim());
		}
		reporter.SuccessReport("Shipping Address", "Shipping Address is taken", "");
		return address;
	}

	public void Compareaddress(Object mylist1, Object mylist2) throws Throwable {
		if (mylist1.equals(mylist2)) {
			reporter.failureReport("Shipping addresses Company::", "Shipping Address Companies Are Matching", "");
		} else {
			reporter.SuccessReport("Shipping addresses Company::", "Shipping Address Companies Are Not Matching", "");
		}
	}

	public void CompareaddressCompanyname(String Company1, String Company2) throws Throwable {
		if (Company1.equals(Company2)) {
			reporter.failureReport("Shipping address::", "Shipping Address Are Matching", "");
		} else {
			reporter.SuccessReport("Shipping address::", "Shipping Address Are Not Matching", "");
		}
	}

	public void SwitchWebGroup(String webgrp) throws Throwable {
		if (isElementPresent(WEBGRP_DROPDOWN, "WebGroup Dropdown")) {
			click(WEBGRP_DROPDOWN, "WebGroup Dropdown");
			click(SWITCH_WEBGRP(webgrp), "Switch To WebGroup::" + webgrp + "");
			waitForVisibilityOfElement(WEBGRPCHANGE_POPUP, "Switch To Webgrp PopUp");
			click(CONTINUEBUTTON_WEBGRPCHANGE, "Continue");
			waitForVisibilityOfElement(SWITCHED_WEBGRPONDASHBOARD(webgrp), "DashBoard");
			reporter.SuccessReport("Switch to WebGroup", "WebGroup is Switched to " + webgrp + "", "");
		} else {
			reporter.failureReport("Switch to WebGroup", "WebGroup is unable to Switch", "");
		}
	}

	public void addAdditionalInfoOfProductSWP(String endUserEmail, String country, String enduserunit,
			String endusermarsha) throws Throwable {
		if (isElementPresent(COUNTRYDROPDOWN, "Country Dropdown")) {
			selectByVisibleText(COUNTRYDROPDOWN, country, "Country");
			click(END_USER_EMAIL, "End User Email");
			type(END_USER_EMAIL, endUserEmail, "End User Email");
			click(ENDUSE_UNIT, "End User Unit");
			type(ENDUSE_UNIT, enduserunit, "End User Unit");
			click(ENDUSER_MARSHA, "End User Marsha");
			type(ENDUSER_MARSHA, endusermarsha, "End User Marsha");
			reporter.SuccessReport("Additional Info In Swb", "Additional Info In Swb is Updated", "");
		} else {
			reporter.failureReport("Additional Info In Swb", "Additional Info In Swb is Unable To Update", "");
		}
	}

	/**
	 * 
	 * Method for set quantity for lenovo
	 * 
	 * @param value
	 * 
	 * @throws Throwable
	 * 
	 */

	public void setQuantityForLenovo(String value) throws Throwable {

		waitForVisibilityOfElement(PRODUCTQTY_LENOVO, " Quantity");

		if (isElementPresent(PRODUCTQTY_LENOVO, "Quantity")) {

			click(PRODUCTQTY_LENOVO, "Qunatity");

			type(PRODUCTQTY_LENOVO, value, "Qunatity");

			reporter.SuccessReport("Quantity of PartNum in Selected Product group",
					"Qunatity of PartNum in Selected Product group Is updated to" + value + "", "");

		} else {

			reporter.failureReport("Quantity of  PartNum in Selected Product group",
					"PartNum in Selected Product group is Not Visible", "");

		}

		click(CommonObj.ADD_TO_ORDER, "ADD To Order Button is Clicked");

		waitForVisibilityOfElement(ADDTOCARTDAILOGUE_BOX, "Item Added To cart");

	}

	public void clickOnViewCart() throws Throwable {
		if (isElementPresent(VIEW_CART, "Cart")) {
			click(VIEW_CART, "Cart");
			reporter.SuccessReport("Cart Bread Crumb", "Cart Bread Crumb is verified", "");
		} else {
			reporter.failureReport("Cart Bread Crumb", "Cart Bread Crumb is Not verified", "");
		}
		Thread.sleep(5000);
	}

	/**
	 * 
	 * This method is to click on the Account tools from the side menu and click
	 * on product group.
	 * 
	 * @param toolsMenuName
	 * 
	 * @param dropDown
	 * 
	 * @param productGroup
	 * 
	 * @param productName
	 * 
	 * @throws Throwable
	 * 
	 */

	public void clickAccountToolsFromSideMenuAndClickOnProductGrp(String toolsMenuName, String dropDown,
			String productGroup, String productName) throws Throwable {

		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");

		click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");

		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");

		click(PRODUCT_GROUPS, "Company Product groups");

		click(getCompanyStandardsProductGroup(productGroup, productName), "select product from product group");

	}

	public void clickAccountToolsFromSideMenu(String toolsMenuName, String dropDown) throws Throwable {

		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");

		click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");

		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
	}

	public void verifyPartInCartQuickShop(String value) throws Throwable {

		if (isElementPresent(QUICK_SHOP_ITEM_FIELD, "Quickshop")) {
			click(QUICK_SHOP_ITEM_FIELD, "Quickshop");
			type(QUICK_SHOP_ITEM_FIELD, value, "Quickshop");
			click(ADD_BUTTON, "Add Button");
			waitForVisibilityOfElement(CartObj.CART_LABEL_ON_CART_PAGE, "Item added to cart");
			Verifypartnum(value);
			reporter.SuccessReport("Verify  Part through Quick Shop ", "Added Part through Quick Shop", "");
		} else {

			reporter.failureReport("Price of PartNum in Selected Product group in Cart",
					"Price of PartNum in Selected Product group is Not Visible  in Cart", "");

		}

	}

	/**
	 * 
	 * Method to add additional Information
	 * 
	 * @param brandIdentifier
	 * 
	 * @param requesterName
	 * 
	 * @param peopleSoftNumber
	 * 
	 * @param Notes
	 * 
	 * @param customerReferenceNumber
	 * 
	 * @param pcUserName
	 * 
	 * @param pcEndUserdivUnit
	 * 
	 * @param endUserEmail
	 * 
	 * @param approvingManagerEmail
	 * 
	 * @param noIRFA
	 * 
	 * @throws Throwable
	 * 
	 */

	public void addAdditionalInfoOfProduct(String brandIdentifier, String requesterName, String peopleSoftNumber,
			String Notes,

			String customerReferenceNumber, String pcUserName, String pcEndUserdivUnit, String endUserEmail,
			String approvingManagerEmail, String noIRFA)

			throws Throwable {

		clearData(BRAND_IDENTIFIERSELECT);
		selectByValue(BRAND_IDENTIFIERSELECT, brandIdentifier, "Brand Identifier"); // Entering
		clearData(REQUESTER_NAME);
		selectByValue(REQUESTER_NAME, requesterName, "Requester Name "); // Entering
		click(PEOPLE_SOFT_NUMBER, "People SoftNmuber");
		type(PEOPLE_SOFT_NUMBER, peopleSoftNumber, "People SoftNmuber");// Enter
		click(NOTES, "Notes");
		type(NOTES, Notes, "Notes");
		click(CUSTOMER_REFERENCE_NUMBER, "Customer reference Number ");
		type(CUSTOMER_REFERENCE_NUMBER, customerReferenceNumber, "Customer Reference Number");
		click(PC_ENDUSER, "PC User Name");
		type(PC_ENDUSER, pcUserName, "PC User Name");
		click(END_USER_DIV_UNIT, "End User Div Unit");
		type(END_USER_DIV_UNIT, pcEndUserdivUnit, "End User Div Unit");
		click(END_USER_EMAIL, "End user email");
		type(END_USER_EMAIL, endUserEmail, "End user email");
		click(APPROVING_MANGER_EMAIL, "Approvingmanager email");
		type(APPROVING_MANGER_EMAIL, approvingManagerEmail, "Approvingmanager email");
		click(NON_IRFAPC, "No IRFA");
		selectByValue(NON_IRFAPC, noIRFA, "No IRFA ");
		if (isElementPresent(NAME, "name")) {

		} else {
			click(CONTINUE, "Continue button of Shipping address");
		}

		reporter.SuccessReport("Verify  added additional information of the product ", "Added Part through Quick Shop",
				"");
	}

	public void addAdditionalInfo(String name, String phone, String email)throws Throwable {
		waitForVisibilityOfElement(Name, "Name");
		typeText(Name,name, "Name");
		typeText(Phone, phone, "Phone");
		typeText(Email, email, "Email");
		click(OrderObj.CONTINUE_BTN, "Continue button");
		}


	/**
	 * 
	 * Method to add Shipping Info of the product
	 * 
	 * @param shipAttention
	 * 
	 * @param shipSuite
	 * 
	 * @param phoneNumber
	 * 
	 * @throws Throwable
	 * 
	 */

	public void addShippingInfo(String shipAttention, String shipSuite, String phoneNumber)

			throws Throwable {

		waitForVisibilityOfElement(SHIP_ATTENTION, "Ship Attention");
		Thread.sleep(5000);

		if (isElementPresent(SHIP_ATTENTION, "Ship Attention")) {

			click(SHIP_ATTENTION, "Ship Attention");

			type(SHIP_ATTENTION, shipAttention, "Ship Attention");

			clearData(SHIP_PHONE);

			click(SHIP_PHONE, "Phone Number ");

			type(SHIP_PHONE, phoneNumber, "Phone Number");

			clearData(SHIP_LOCATION_ID);

			click(SHIP_LOCATION_ID, "Location");

			type(SHIP_LOCATION_ID, shipSuite, "Location");

			click(CONTINUE, "Continue button of Shipping address");

			reporter.SuccessReport("Enter Shipping Information ", "Successfully entered shipping Information ", "");

		}

	}

	/**
	 * 
	 * Method to add Shipping Info of the product
	 * 
	 * @param shipAttention
	 * 
	 * @param shipSuite
	 * 
	 * @param phoneNumber
	 * 
	 * @throws Throwable
	 * 
	 */

	public void addBillingInfo(String billAttention, String billSuite, String billphoneNumber)

			throws Throwable {

		waitForVisibilityOfElement(BILLING_ATTENTION, "Bill Attention");

		if (isElementPresent(BILLING_ATTENTION, "Bill Attention")) {

			click(BILLING_ATTENTION, "Quickshop");

			type(BILLING_ATTENTION, billAttention, "Billing Attention");

			clearData(BILLING_PHONE);

			click(BILLING_PHONE, "Phone Number ");

			type(BILLING_PHONE, billphoneNumber, "Phone Number");

			clearData(BILLING_LOCATION);

			click(BILLING_LOCATION, "Location");

			type(BILLING_LOCATION, billSuite, "Location");

			click(CONTINUE, "Continue button of Review Order");

			reporter.SuccessReport("Enter billing Information ", "Successfully entered billing Information ", " ");

		}

	}

	/**
	 * 
	 * Method to add Payment Info of the product
	 * 
	 * 
	 * 
	 * @throws Throwable
	 * 
	 */

	public void clickReviewOrder() throws Throwable {

		waitForVisibilityOfElement(CONTINUE, "Review Order");

		if (isElementPresent(CONTINUE, "Review Order")) {

			click(CONTINUE, "Review Order");

			reporter.SuccessReport("Verify Review Order  ", "Successfully clicked on review order", " ");

		}

	}

	/**
	 * 
	 * Method to add Payment Info of the product
	 * 
	 * 
	 * 
	 * @throws Throwable
	 * 
	 */

	public void shippingOptionContinueButton()

			throws Throwable {

		waitForVisibilityOfElement(CONTINUE, "Shipping Options continue");

		if (isElementPresent(CONTINUE, "Shipping Options continue")) {

			click(CONTINUE, "Shipping Options continue");

		}

	}

	public void verifyPayementInfo(String paymentType)

			throws Throwable {

		String ActualText;

		ActualText = getText(PAYMENT_INFO, "Payment type ").trim();

		if (ActualText.equalsIgnoreCase(paymentType))
			;

		reporter.SuccessReport("Verify Payment type Terms on Place Order Page ",
				"Payment type is loacated in Order page", "");

	}

	public void additionalinfo(String Brand_Identifier, String PC_Laptop, String Notes, String PC_User_Name,
			String PC_End_User_MARSHA, String MARSHA_of_Approver, String name, String phone, String email)
			throws Throwable {
		clearData(BRAND_IDENTIFIER);
		click(BRAND_IDENTIFIER, "People SoftNmuber");
		type(BRAND_IDENTIFIER, Brand_Identifier, "People SoftNmuber");
		selectByVisibleText(PCRLAPTOPDROPDOWN, PC_Laptop, "Order Contains PC(or) Laptop Options");
		click(NOTES, "Notes");
		type(NOTES, Notes, "Notes");
		click(PCUSER_NAME, "PC User Name");
		type(PCUSER_NAME, PC_User_Name, "PC User Name");
		click(ENDUSER_MARSHA, "PC_End_User_MARSHA");
		type(ENDUSER_MARSHA, PC_End_User_MARSHA, "PC_End_User_MARSHA");
		click(REQUESTOR_EMAIL, "Requestor Email");
		type(REQUESTOR_EMAIL, MARSHA_of_Approver, "Requestor Email");
		click(Name, "Name");
		type(Name, name, "Name");
		click(Phone, "Requestor Email");
		type(Phone, phone, "Phone");
		click(Email, "Email");
		type(Email, email, "Email");
		click(CONTINUE, "Continue Button");
		reporter.SuccessReport("Verify  added additional information of the product ", "Added Part through Quick Shop",
				"");

	}

	/**
	 * This method is to verify Brand_Identifier in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void VerifyBrandidentifier(String Brand_Identifier) throws Throwable {
		waitForVisibilityOfElement(verifybrandidentifiertext(Brand_Identifier), "Brand_Identifier");
		if (isElementPresent(verifybrandidentifiertext(Brand_Identifier), "Brand_Identifier")) {
			reporter.SuccessReport("Brand_Identifier::", "" + Brand_Identifier + "- Is visible", "");
		} else {
			reporter.failureReport("Brand_Identifier::", "Brand_Identifier is Not Visible", "");
		}
	}

	/**
	 * This method is to verify order contain a pc (or) not in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifyordercontainapcornot(String PC_Laptop) throws Throwable {
		waitForVisibilityOfElement(verifyordercontainapcornottext(PC_Laptop), "PC_Laptop");
		if (isElementPresent(verifyordercontainapcornottext(PC_Laptop), "PC_Laptop")) {
			reporter.SuccessReport("Order Contain PC(or)Laptop::", "" + PC_Laptop + " Is visible On Place Order Page",
					"");
		} else {
			reporter.failureReport("Order Contain PC(or)Laptop::", "Option is Not Visible", "");
		}
	}

	/**
	 * This method is to verify PC_End_User_MARSHA in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifyendusermarsha(String PC_End_User_MARSHA) throws Throwable {
		waitForVisibilityOfElement(verifyendusermarshatext(PC_End_User_MARSHA), "PC_End_User_MARSHA");
		if (isElementPresent(verifyendusermarshatext(PC_End_User_MARSHA), "PC_End_User_MARSHA")) {
			reporter.SuccessReport("PC_End_User_MARSHA::", "" + PC_End_User_MARSHA + " Is visible On Place Order Page",
					"");
		} else {
			reporter.failureReport("PC_End_User_MARSHA::", "PC_End_User_MARSHA is Not Visible", "");
		}
	}

	/**
	 * This method is to verify PC_User_Name in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifypcusername(String PC_User_Name) throws Throwable {
		waitForVisibilityOfElement(verifypcusernametext(PC_User_Name), "PC_User_Name");
		if (isElementPresent(verifypcusernametext(PC_User_Name), "PC_User_Name")) {
			reporter.SuccessReport("PC UserName::", "" + PC_User_Name + " Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("PC UserName::", "PC_User_Name is Not Visible", "");
		}
	}

	/**
	 * This method is to verify Requester_Email in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifyrequestoremail(String Requester_Email) throws Throwable {
		waitForVisibilityOfElement(verifyrequestoremailtext(Requester_Email), "Requester_Email");
		if (isElementPresent(verifyrequestoremailtext(Requester_Email), "Requester_Email")) {
			reporter.SuccessReport("Requester Email::", "" + Requester_Email + " Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("Requester Email::", "Requester Email is Not Visible", "");
		}
	}

	/**
	 * This method is to verify Notes in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void Verifynotes(String Notes) throws Throwable {
		waitForVisibilityOfElement(verifynotestext(Notes), "Notes");
		if (isElementPresent(verifynotestext(Notes), "Notes")) {
			reporter.SuccessReport("Notes::", "" + Notes + " Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("Notes::", "Notes is Not Visible", "");
		}
	}

	/**
	 * This method is to verify shippingaddress attention in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifyshippingaddressattention(String Ship_Attention) throws Throwable {
		waitForVisibilityOfElement(shippingaddressattentiontext(Ship_Attention), "Shiiping address Attention");
		if (isElementPresent(shippingaddressattentiontext(Ship_Attention), "Shiiping address Attention")) {
			reporter.SuccessReport("Shiiping address Attention::",
					"" + Ship_Attention + " Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("Shiiping address Attention::", "Shiiping address Attention is Not Visible", "");
		}
	}

	/**
	 * This method is to verify billingaddress attention in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifyBillingaddressattention(String Notes) throws Throwable {
		waitForVisibilityOfElement(Billingaddressattentiontext(Notes), "Billing address Attention");
		if (isElementPresent(Billingaddressattentiontext(Notes), "Billing address Attention")) {
			reporter.SuccessReport("Billing address Attention::", "" + Notes + "Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("Billing address Attention::", "Billing address Attention is Not Visible", "");
		}
	}

	/**
	 * This method is to set quantity and click on Addtoorder in Company
	 * standards Page
	 * 
	 * 
	 * @throws Throwable
	 */
	public void setQuantityAddProduct(String value, String PartNum) throws Throwable {
		waitForVisibilityOfElement(getQuantityOfPart(PartNum), "Part Quantity");
		if (isElementPresent(getQuantityOfPart(PartNum), "Part Quantity")) {
			click(getQuantityOfPart(PartNum), "Qunatity");
			type(getQuantityOfPart(PartNum), value, "Qunatity");
			reporter.SuccessReport("Quantity of PartNum in Selected Product group",
					"Qunatity of PartNum in Selected Product group Is updated to" + value + "", "");

		} else {
			reporter.failureReport("Quantity of  PartNum in Selected Product group",
					"PartNum in Selected Product group is Not Visible", "");
		}
		click(ADDPRODUCT, "ADD Product");
		waitForVisibilityOfElement(CommonObj.ADD_TO_ORDER, "ADD To Order Button is Clicked");
		click(CommonObj.ADD_TO_ORDER, "ADD To Order Button is Clicked");

		waitForVisibilityOfElement(ADDTOCARTDAILOGUE_BOX, "Item Added To cart");

	}

	/**
	 * This method is to verify Company standards link
	 * 
	 * 
	 * @throws Throwable
	 */
	public void CompanystandardsSelectProductGrp(String productGroup, String productName) throws Throwable {
		waitForVisibilityOfElement(CartObj.Current_product_groups, " Current Product Groups page is opened");
		isElementPresent(CartObj.Current_product_groups, " Current Product Groups page is opened");
		click(getcompanystandardsproductgroup(productGroup), "Product Group");
		if (productName.equals("Field Only")) {
			click(getCompanyStandardsProductGroupforField(productGroup, productName),
					"select product from product group");
		} else {

			click(CommonObj.getCompanyStandardsProductGroup(productGroup, productName),
					"select product from product group");
		}
	}

	/**
	 * This method is to Click on Product in Product grp by Company standards
	 * link
	 * 
	 * 
	 * @throws Throwable
	 */
	public void CompanystandardslinkandProductGrpWithbtag(String productGroup, String productName, String FieldOnly)
			throws Throwable {
		waitForVisibilityOfElement(COMPANYSTANDARDS_PAGELINK, "Company Standards Page Link");
		click(COMPANYSTANDARDS_PAGELINK, "Company Standards PageLink");
		isElementPresent(CartObj.Current_product_groups, " Current Product Groups page is opened");
		click(getcompanystandardsproductgroup(productGroup), "Product Group");
		click(getCompanyStandardsProductGroupwithbtext(productGroup, productName, FieldOnly),
				"select product from product group");
	}

	/**
	 * This method is to verify Requester_Name in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifyrequestorname(String Requester_Name) throws Throwable {
		waitForVisibilityOfElement(verifyRequisitortext(Requester_Name), "Requester_Name");
		if (isElementPresent(verifyRequisitortext(Requester_Name), "Requester_Name")) {
			reporter.SuccessReport("Requester Name::", "" + Requester_Name + " -Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("Requester Name::", "Requester Name is Not Visible", "");
		}
	}

	/**
	 * This method is to verify Customer_Reference in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void Verifycustomerreference(String Customer_Reference) throws Throwable {
		waitForVisibilityOfElement(Costumerrefernce(Customer_Reference), "Customer_Reference");
		if (isElementPresent(Costumerrefernce(Customer_Reference), "Customer_Reference")) {
			reporter.SuccessReport("Customer Reference::", "" + Customer_Reference + " -Is visible On Place Order Page",
					"");
		} else {
			reporter.failureReport("Customer Reference::", "Customer_Reference is Not Visible", "");
		}
	}

	/**
	 * This method is to verify End_User_People in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void VerifyEnduserText(String End_User_People) throws Throwable {
		waitForVisibilityOfElement(verifyEndusertext(End_User_People), "End_User_People");
		if (isElementPresent(verifyEndusertext(End_User_People), "End_User_People")) {
			reporter.SuccessReport("End_User_People::", "" + End_User_People + " -Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("End_User_People::", "End_User_People is Not Visible", "");
		}
	}

	/**
	 * This method is to verify Approving_Manager_Email in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifyapprovingmanageremail(String Approving_Manager) throws Throwable {
		waitForVisibilityOfElement(Approvingmanagermeail(Approving_Manager), "Approving_Manager_Email");
		if (isElementPresent(Approvingmanagermeail(Approving_Manager), "Approving_Manager_Email")) {
			reporter.SuccessReport("Approving_Manager_Email::",
					"" + Approving_Manager + " - Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("Approving_Manager_Email::", "Approving_Manager_Email is Not Visible", "");
		}
	}

	/**
	 * This method is to verify Approving_Manager_Email in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifyEnduseremail(String End_User_Email) throws Throwable {
		waitForVisibilityOfElement(Enduseremail(End_User_Email), "End_User_Email");
		if (isElementPresent(Enduseremail(End_User_Email), "End_User_Email")) {
			reporter.SuccessReport("End_User_Email::", "" + End_User_Email + " - Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("End_User_Email::", "End_User_Email is Not Visible", "");
		}
	}

	/**
	 * This method is to verify Non_IRFA_PC in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifynonirfapc(String Non_IRFA_PC) throws Throwable {
		waitForVisibilityOfElement(nonirfapc(Non_IRFA_PC), "Non_IRFA_PC");
		if (isElementPresent(nonirfapc(Non_IRFA_PC), "Non_IRFA_PC")) {
			reporter.SuccessReport("Non_IRFA_PC::", "" + Non_IRFA_PC + " - Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("Non_IRFA_PC::", "Non_IRFA_PC is Not Visible", "");
		}
	}

	/**
	 * This method is to verify Approving_Manager_Email in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifyenduserdiv(String PC_End_User_Div_Unit_Dept) throws Throwable {
		waitForVisibilityOfElement(enduserdiv(PC_End_User_Div_Unit_Dept), "End_User_Div_Unit_Dept");
		if (isElementPresent(enduserdiv(PC_End_User_Div_Unit_Dept), "End_User_Div_Unit_Dept")) {
			reporter.SuccessReport("End_User_Div_Unit_Dept::",
					"" + PC_End_User_Div_Unit_Dept + " - Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("End_User_Div_Unit_Dept::", "End_User_Div_Unit_Dept is Not Visible", "");
		}
	}

	public void Additionalinfo(String approvingManagerEmail, String domain, String MARSHA_of_Approver,
			String Endusermarsha, String Enduserdiv, String EndUserEID, String EndUserName,String Notes) throws Throwable {
		waitForVisibilityOfElement(APPROVING_MANGER_EMAIL, "Approvingmanager Email");
		click(APPROVING_MANGER_EMAIL, "Approvingmanager email");
		type(APPROVING_MANGER_EMAIL, approvingManagerEmail, "Approvingmanager Email");
		selectByVisibleText(DOMAIN, domain, "Domain");
		click(REQUESTOR_EMAIL, "Requestor Email");
		type(REQUESTOR_EMAIL, MARSHA_of_Approver, "Requestor Email");
		selectByVisibleText(ENDUSERMARSHA, Endusermarsha, "End User Marsha");
		selectByVisibleText(ENDUSERDIVSELECT, Enduserdiv, "End User Div");
		click(ENDUSEREID, "End User EID");
		type(ENDUSEREID, EndUserEID, "End User EID");
		click(ENDUSERNAME, "End User Name");
		type(ENDUSERNAME, EndUserName, "End User Name");
		click(NOTES, "Notes");
		type(NOTES, Notes, "Notes");
	}

	/**
	 * This method is to verify Domain in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifyDomain(String Domain) throws Throwable {
		if (isElementPresent(verifydomain(Domain), "Non_IRFA_PC")) {
			reporter.SuccessReport("Non_IRFA_PC::", "" + Domain + " - Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("Non_IRFA_PC::", "Non_IRFA_PC is Not Visible", "");
		}
	}

	/**
	 * This method is to verify End_User_Eid in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifyendusereid(String End_User_Eid) throws Throwable {
		if (isElementPresent(verifyEnduserEid(End_User_Eid), "End_User_Eid")) {
			reporter.SuccessReport("End_User_Eid::", "" + End_User_Eid + " -Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("End_User_Eid::", "End_User_Eid is Not Visible", "");
		}
	}

	/**
	 * This method is to verify End_User_Eid in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifyendusername(String End_User_Name) throws Throwable {
		if (isElementPresent(verifyEndusername(End_User_Name), "End_User_Name")) {
			reporter.SuccessReport("End_User_Name::", "" + End_User_Name + " -Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("End_User_Name::", "End_User_Name is Not Visible", "");
		}
	}

	/**
	 * This method is to verify End_User_Eid in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifyShippingattention(String Attention) throws Throwable {
		if (isElementPresent(Shipinfoattention(Attention), "Attention")) {
			reporter.SuccessReport("Shipping Address::", "" + Attention + " -Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("Shipping Address::", "Attention is Not Visible", "");
		}
	}

	/**
	 * This method is to verify End_User_Eid in PlaceOrderpage
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifybillingattention(String Attention) throws Throwable {
		if (isElementPresent(Billinfoattention(Attention), "Attention")) {
			reporter.SuccessReport("Billing Address::", "" + Attention + " -Is visible On Place Order Page", "");
		} else {
			reporter.failureReport("Billing Address::", "Attention is Not Visible", "");
		}
	}

}
