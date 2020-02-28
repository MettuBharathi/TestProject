package com.insight.Lib;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebElement;
import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.CommonObj;
import com.insight.ObjRepo.OrderObj;
import com.insight.ObjRepo.ShipBillPayObj;
import com.insight.ObjRepo.productsDisplayInfoObj;
import com.insight.accelerators.ActionEngine;

public class CartLib extends ActionEngine {

	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	OrderObj orderObj = new OrderObj();
	ShipBillPayLib shipbLib = new ShipBillPayLib();
	String openMarketPrice;

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void increaseNumberOfDisplayedProduct() throws Throwable {
		waitForVisibilityOfElement(CartObj.NUMBER_PICKER_IN_PRODUCT_DISPLAY, "NUMBER PICKER IN PRODUCT DISPLAY");
		click(CartObj.NUMBER_PICKER_IN_PRODUCT_DISPLAY, "NUMBER PICKER IN PRODUCT DISPLAY");
	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void clickOnPrint() throws Throwable {
		waitForVisibilityOfElement(CartObj.PRINT, "PRINT LINK");
		click(CartObj.PRINT, "PRINT LINK");
	}

	/**
	 * This method is to click on More available prices
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void clickMorePricesAvilable(int index) throws Throwable {
		isElementPresent(CartObj.moreAvilablePrices(index), "More AVilable Prices");
		click(CartObj.moreAvilablePrices(index), "More AVilable Prices");
	}

	/**
	 * This method is to click on More available prices in product info
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void clickMorePricesAvilableInProductInfo() throws Throwable {
		isElementPresent(CartObj.MORE_AVAILABLE_PRICE_IN_PRODUCT_INFO, "More Available Prices");
		click(CartObj.MORE_AVAILABLE_PRICE_IN_PRODUCT_INFO, "More Available Prices");
	}

	/**
	 * This method is to verify deafult contarct search results page
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void verifyDefaultContract() throws Throwable {
		if (isElementPresent(CartObj.DEFAULT_CONTARCT, "Default contarct")) {
			reporter.SuccessReport("Verify Default Contract in the Search Results Page",
					"Default Contract: US COMMUNITIES IT PRODUCTS & SERVICES", "");
		} else {
			reporter.failureReport("Verify Default Contract in the Search Results Page",
					"Default Contract is not: US COMMUNITIES IT PRODUCTS & SERVICES", "", driver);
		}
	}

	/**
	 * This method is to verify default contarct in cart page
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void verifyDefaultContractInCart() throws Throwable {
		if (isElementPresent(CartObj.DEAFULT_CONTARCT_IN_CART, "Default contarct")) {
			reporter.SuccessReport("Verify Default Contract in the cart Page",
					"Default Contract: US COMMUNITIES IT PRODUCTS & SERVICES", "");
		} else {
			reporter.failureReport("Verify Default Contract in the cart Page",
					"Default Contract is not: US COMMUNITIES IT PRODUCTS & SERVICES", "", driver);
		}
	}

	/**
	 * This method is to select desired price
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void clickOnOpenMarketPrice() throws Throwable {
		isElementPresent(productsDisplayInfoObj.OPEN_MARKET, "open market price");
		click(productsDisplayInfoObj.OPEN_MARKET, "open market price");
	}

	/**
	 * This method is to select desired price
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void clickOnUSCommuditiesPrice() throws Throwable {
		isElementPresent(CartObj.US_COMMIDITIES, "Us commodities price");
		click(CartObj.US_COMMIDITIES, "Us commodities price");
	}

	/**
	 * This method is to click Add to cart in avilable prices list
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void clickOnAddToCartInAllContractPrices() throws Throwable {
		isElementPresent(CartObj.ADD_TO_CART_IN_ALL_CONTRACT_PRICES, "Add to cart");
		click(CartObj.ADD_TO_CART_IN_ALL_CONTRACT_PRICES, "Add to cart");
		waitForVisibilityOfElement(CartObj.ADD_TO_CART_SUCCESS_MESSAGE, "ADD TO CART SUCCESS MESSAGE");
		isElementPresent(CartObj.ADD_TO_CART_SUCCESS_MESSAGE, "ADD TO CART SUCCESS MESSAGE", true);
	}

	/**
	 * This method is to click on save cart contents and save the cart
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void clickOnSaveCartContentAndSaveCart(String cartName) throws Throwable {
		isElementPresent(CartObj.SAVE_CART_CONTENTS, "Save cart contents");
		click(CartObj.SAVE_CART_CONTENTS, "Save cart contents");
		waitForVisibilityOfElement(CartObj.SAVE_CART_CONTENTS_POPUP, "SAVE CART CONTENTS POPUP");
		click(CartObj.SAVE_BUTTON, "Save button");
		if (isElementPresent(CartObj.SAVE_CART_ERROR_MESSAGE, "Save cart error message")) {
			reporter.SuccessReport("Save cart error message ", "Please enter a name for your cart message is displayed",
					"");
		} else {
			reporter.failureReport("Save cart error message ",
					"Please enter a name for your cart message is not displayed", "", driver);

		}
		// String cartName=getRandomString(5)+'@';
		type(CartObj.SAVE_CART_INPUT_FIELD, cartName, "cart name");
		click(CartObj.SAVE_BUTTON, "Save button");
		waitForVisibilityOfElement(CartObj.CART_SAVED_SUCESS_MESSAGE, "cart save sucess message");
		if (isElementPresent(CartObj.CART_SAVED_SUCESS_MESSAGE, "Save cart sucess message")) {
			reporter.SuccessReport("Save cart sucess message ",
					"Your cart has been successfully saved message is displayed", "");
		} else {
			reporter.failureReport("Save cart error message ",
					"Your cart has been successfully saved message is not displayed", "", driver);

		}
		click(CartObj.CONTINUE, "Continue button");
	}

	/*
	 * PURPOSE OF METHOD : verify save cart as quote is present
	 * 
	 * @author : CIGNITI
	 */
	public void verifySaveCartAsQuoteIsPresent() throws Throwable {
		waitForVisibilityOfElement(CartObj.SAVE_AS_QUOTE, "cart save as quote");
		if (isElementPresent(CartObj.SAVE_AS_QUOTE, "cart save as quote")) {
			reporter.SuccessReport("cart save as quote ", "cart save as quote is present ", "");
		} else {
			reporter.failureReport("cart save as quote ", "cart save as quote is not present", "", driver);
		}
	}

	/*
	 * PURPOSE OF METHOD : click on quick checkout and verify shipping adresses and
	 * contact details
	 * 
	 * @author : CIGNITI
	 */
	public void clickQuickCheckOutandVerify(String shippingCompany, String shippingCarrier, String NotificationMail,
			String BillingAddresses, String PaymentType) throws Throwable {
		waitForVisibilityOfElement(CartObj.QUICK_CHECKOUT, "quick check out");
		click(CartObj.QUICK_CHECKOUT, "quick check out");
		if (isElementPresent(CartObj.PLACE_ORDER_PAGE_TEXT, "place order page")) {
			reporter.SuccessReport("Place order ", "Place order page is opened ", "");
		} else {
			reporter.failureReport("Place order ", "Place order page is not opened ", "", driver);
		}
		if (isElementPresent(CartObj.validationsInPlaceOrderPage(shippingCompany), "Shipping Company")) {
			reporter.SuccessReport("Shipping Company", "Shipping company is present", shippingCompany);

		} else {
			reporter.failureReport("Shipping Company", "Shipping company is not present", shippingCompany, driver);
		}
		if (isElementPresent(CartObj.validationsInPlaceOrderPage(shippingCarrier), "Shipping Carrier")) {
			reporter.SuccessReport("Shipping Carrier", "Shipping Carrier is present", shippingCarrier);

		} else {
			reporter.failureReport("Shipping Carrier", "Shipping Carrier is not present", shippingCarrier, driver);
		}

		if (isElementPresent(CartObj.validationsInPlaceOrderPage(NotificationMail), "Notification Mail")) {
			reporter.SuccessReport("Notification Mail", "Notification Mail is present", NotificationMail);

		} else {
			reporter.failureReport("Notification Mail", "Notification Mail is not present", NotificationMail, driver);
		}

		if (isElementPresent(CartObj.validationsInPlaceOrderPage(BillingAddresses), "Billing Addresses")) {
			reporter.SuccessReport("Billing Addresses", "Billing Addresses is present", BillingAddresses);

		} else {
			reporter.failureReport("NBilling Addresses", "Billing Addresses is not present", BillingAddresses, driver);
		}

		if (isElementPresent(CartObj.validationsInPlaceOrderPage(PaymentType), "Payment Type")) {
			reporter.SuccessReport("Payment Type", "Payment Type is present", PaymentType);

		} else {
			reporter.failureReport("Payment Type", "Payment Type" + PaymentType + "is not present", "", driver);
		}

		click(CartObj.RETURN_TO_CART, "Return to cart");

	}

	/*
	 * PURPOSE OF METHOD : click on favorite shipping address and select an adresses
	 * 
	 * @author : CIGNITI
	 */
	public void clickOnFavouriteShippingAddressesandSelectanAddresses(String shippingAddresses) throws Throwable {
		waitForVisibilityOfElement(CartObj.FAVOURITE_SHIPPING_ADDRESSES_DROPDOWN, "Favourite shipping addresses");
		click(CartObj.FAVOURITE_SHIPPING_ADDRESSES_DROPDOWN, "Favourite shipping addresses");
		click(CartObj.selectFavouriteShippingAdresses(shippingAddresses), "Shipping Adresses");

	}

	/*
	 * PURPOSE OF METHOD : click on quick checkout
	 * 
	 * @author : CIGNITI
	 */
	public void clickOnQuickCheckout() throws Throwable {
		waitForVisibilityOfElement(CartObj.QUICK_CHECKOUT, "quick check out");
		click(CartObj.QUICK_CHECKOUT, "quick check out");
	}

	/*
	 * PURPOSE OF METHOD : validate Shipping address in quick ckeck out page
	 * 
	 * @author : CIGNITI
	 */
	public void validateShippingAddressesInQickCheckOut(String shippingCompany) throws Throwable {
		if (isElementPresent(CartObj.PLACE_ORDER_PAGE_TEXT, "place order page")) {
			reporter.SuccessReport("Place order ", "Place order page is opened ", "");
		} else {
			reporter.failureReport("Place order ", "Place order page is not opened ", "", driver);
		}
		if (isElementPresent(CartObj.validationsInPlaceOrderPage(shippingCompany), "Shipping Company")) {
			reporter.SuccessReport("Shipping Company", "Shipping company is present", shippingCompany);

		} else {
			reporter.failureReport("Shipping Company", "Shipping company" + shippingCompany + "is not present",
					shippingCompany, driver);
		}
	}

	/*
	 * PURPOSE OF METHOD : verify save cart as quote is present
	 * 
	 * @author : CIGNITI
	 */
	public void verifySelectRwquestorGroupDropdownIsPresent() throws Throwable {
		waitForVisibilityOfElement(CartObj.SELECT_REQUEST_GROUP, "SELECT REQUEST GROUP DROPDOWN");
		if (isElementPresent(CartObj.SELECT_REQUEST_GROUP, "SELECT REQUEST GROUP DROPDOWN")) {
			reporter.SuccessReport("SELECT REQUEST GROUP DROPDOWN ", "SELECT REQUEST GROUP DROPDOWN IS PRESENT", "");
		} else {
			reporter.failureReport("SELECT REQUEST GROUP DROPDOWN ", "SELECT REQUEST GROUP DROPDOWN IS NOT PRESENT", "",
					driver);
		}
	}

	/*
	 * PURPOSE OF METHOD : open saved cart from account tools
	 * 
	 * @author : CIGNITI
	 */
	public void openSavedCartFromTools(String cartName) throws Throwable {
		waitForVisibilityOfElement(CartObj.ACCOUNT_TOOLS, "ACCOUNT TOOLS");
		click(CartObj.ACCOUNT_TOOLS, "ACCOUNT TOOLS");
		click(CartObj.TOOLS, "TOOLS");
		click(CartObj.SAVEDCART, "SAVED CART");
		isElementPresent(CartObj.SAVED_CART_TEXT, "Saved cart");
		click(CartObj.loadCart(cartName), "Load cart");
		if (isElementPresent(CartObj.CURRIENCES, "Curriences are displayed")) {
			reporter.SuccessReport("Curriences are displayed ", "Curriences are successfully displayed", "");
		} else {
			reporter.failureReport("Curriences are displayed ", "Curriences are not displayed", "", driver);

		}

	}

	/**
	 * This method is to click add to cart in saved cart
	 * 
	 * @throws Throwable
	 */
	public void addToCartInSavedCart(String cartName) throws Throwable {
		click(CartObj.addToCartInSavedCart(cartName), "Add to cart in saved cart");
		waitForVisibilityOfElement(CartObj.CONTINUE_TO_CHECKOUT, "Continue to check out");
		click(CartObj.CONTINUE_TO_CHECKOUT, "Continue to check out");
	}

	/**
	 * This method is to click on continue button on add info
	 * 
	 * @throws Throwable
	 */
	public void clickOnContinueButtonInAddInformtion() throws Throwable {
		if (isElementPresent(OrderObj.ORDER_ITEM_INFO_LABEl, "order and inforamtion page")) {
			click(OrderObj.CONTINUE_BTN, "Continue button");
			Thread.sleep(2000);
		}
	}

	/**
	 * This method is to click on Continue to check out
	 * 
	 * @throws Throwable
	 */
	public void clickCheckoutDefaults() throws Throwable {
		waitForVisibilityOfElement(CartObj.CHECKOUT_DEFAULTS, "Continue to check out");
		click(CartObj.CHECKOUT_DEFAULTS, "Checkout defaults");
	}

	/**
	 * This method is to verify notification email field in account tools is not
	 * present
	 * 
	 * @throws Throwable
	 */
	public void verifyShipmentNotificationInCheckoutDefaultsIsNotPresent() throws Throwable {

		if (isElementPresent(CartObj.SHIPMENT_NOTIFICATION, "ASN field")) {
			reporter.failureReport("Verify ASN Field on  Account Management - Account Tools Page", "ASN Field is Exist",
					"", driver);
		} else {
			reporter.SuccessReport("Verify ASN Field on  Account Management - Account Tools Page",
					"ASN Field does not Exist", "");
		}
	}

	/**
	 * This method is to verify notification email field in account tools is present
	 * 
	 * @throws Throwable
	 */
	public void verifyShipmentNotificationInCheckoutDefaults() throws Throwable {

		if (isElementPresent(CartObj.SHIPMENT_NOTIFICATION, "ASN field")) {
			reporter.SuccessReport("Verify ASN Field on  Account Management - Account Tools Page", "ASN Field is Exist",
					"");
		} else {
			reporter.failureReport("Verify ASN Field on  Account Management - Account Tools Page",
					"ASN Field does not Exist", "", driver);
		}
	}

	public void enterMailIdToNotificationFieldAndVerifyErrorMessage(String mail) throws Throwable {
		clearData(CartObj.SHIPMENT_NOTIFICATION);
		type(CartObj.SHIPMENT_NOTIFICATION, mail, "ASN field");
		click(CartObj.UPDATE_BUTTON, "Update button");
		String errorMessage = getText(CartObj.ERROR_MESSAGE, "Error meassage");
		if (isElementPresent(CartObj.ERROR_MESSAGE, "Error meassage")) {

			reporter.SuccessReport("Verify Shipment Notification Recipients Field error message",
					"Shipment Notification Recipients Field Error Message in Checkout Defaults - Account Tools",
					errorMessage);
		} else {
			reporter.failureReport("Verify Shipment Notification Recipients Field error message",
					"Shipment Notification Recipients Field Error Message in Checkout Defaults - Account Tools", "",
					driver);
		}

	}

	public void enterMailIdToNotificationFieldAndVerifyMessageNote(String mail) throws Throwable {
		clearData(CartObj.SHIPMENT_NOTIFICATION);
		type(CartObj.SHIPMENT_NOTIFICATION, mail, "ASN field");
		click(CartObj.UPDATE_BUTTON, "Update button");
		String messageNote = getText(CartObj.MESSAGE_NOTE, "Error meassage");
		if (isElementPresent(CartObj.MESSAGE_NOTE, "Error meassage")) {

			reporter.SuccessReport("Verify Shipment Notification Recipients Field Error Message",
					"Shipment Notification Recipients Field Error Message in Checkout Defaults - Account Tools"
							+ messageNote,
					"");
		} else {
			reporter.failureReport("Verify Shipment Notification Recipients Field error message",
					"Shipment Notification Recipients Field Error Message in Checkout Defaults - Account Tools", "",
					driver);
		}

	}

	/**
	 * This method is to verify notification email field in shiiping checkout
	 * 
	 * @throws Throwable
	 */
	public void verifyShipmentNotificationInCheckoutIsNotPresent() throws Throwable {

		if (isElementPresent(CartObj.NOTIFICATION_EMAIL, "ASN field")) {
			reporter.failureReport("Verify Shipment Notification  Confirm via email Ship Bill Page",
					"Shipment Notification  Confirm via email is Exists", "", driver);
		} else {
			reporter.SuccessReport("Verify Shipment Notification  Confirm via email Ship Bill Page",
					"Shipment Notification  Confirm via email is Exists", "");
		}
	}

	public void verifyEmailAsInFormat(String emailToVerify) throws Throwable {
		if (isElementPresent(CartObj.verifyEmail(emailToVerify), "Email " + emailToVerify)) {
			reporter.SuccessReport("Verify Shipment Notification Email" + emailToVerify + " Format on Ship bill page",
					"Shipment Notification email Exists as expected ", emailToVerify);
		} else {
			reporter.failureReport("Verify Shipment Notification Email " + emailToVerify + " Format on Ship bill page",
					"Shipment Notification email is not as expected", "", driver);
		}
	}

	/**
	 * This method is to click on Add additional notification email
	 * 
	 * @throws Throwable
	 */
	public void clickAddAdditionalNotificationEmail() throws Throwable {
		click(CartObj.ADD_ADDITIONAL_NOTIFICATION_EMAIL, "Add additional notification email");
	}

	/**
	 * This method is to enter additional notification email-inavlid and verify
	 * error message
	 * 
	 * @throws Throwable
	 */
	public void enterInvalidAddtionalNotificationEmailAndVerifyErrorMessage(String emailToEnter) throws Throwable {
		// clearData(CartObj.ADDITIONAL_NOTIFICATION_EMAIL);
		type(CartObj.ADDITIONAL_NOTIFICATION_EMAIL, emailToEnter, "additional notification email" + emailToEnter);
		click(CartObj.ADD_ADDITIONAL_NOTIFICATION_EMAIL, "Add additional notification email");
		Thread.sleep(5000);
		String errorMessage = getText(CartObj.ERROR_MESSAGE_INVALID_EMAIL, "Error message");
		if (isElementPresent(CartObj.ERROR_MESSAGE_INVALID_EMAIL, "Error message")) {
			reporter.SuccessReport("Verify error message for invalid mail", "Error message", errorMessage);
		}
		clearData(CartObj.clearNotificationEmail(emailToEnter));
	}

	/**
	 * This method is to enter additional notification email
	 * 
	 * @throws Throwable
	 */
	public void enterValidAddtionalEmail(String emailToEnter) throws Throwable {
		// clearData(CartObj.ADDITIONAL_NOTIFICATION_EMAIL);
		type(CartObj.ADDITIONAL_NOTIFICATION_EMAIL, emailToEnter, "additional notification email" + emailToEnter);

	}

	/**
	 * This method is to fill the Additional information in the Order and item
	 * information page.
	 * https://loginas-uat1.insight.com/insightweb/editLineLevelInfo
	 * 
	 * @param url
	 * @param rP_HDL_Txt
	 * @throws Throwable
	 */
	public void addAdditionalInformationInCheckOut(String url, String rP_HDL_Txt) throws Throwable {
		verify_url(driver, url);
		if (isElementPresent(OrderObj.ORDER_ITEM_INFO_LABEl, "order and inforamtion page")
				&& isElementPresent(OrderObj.RP_HDL_Txt, "RP_HDL_Txt")) {
			type(OrderObj.RP_HDL_Txt, rP_HDL_Txt, "RP_HDL_Txt text box");
			click(OrderObj.CONTINUE_BTN, "Continue button");
		}
	}

	/**
	 * This method is to fill the add Line Level Information in the Order and item
	 * information page.
	 * 
	 * @param rP_LNL_Txt
	 * @throws Throwable
	 */
	public void addLineLevelInformationInCheckOut(String rP_LNL_Txt) throws Throwable {
		if (isElementPresent(OrderObj.LINE_LEVEL_INFO, "Line level information link")) {
			click(OrderObj.LINE_LEVEL_INFO, "Line Level Information");
			if (isElementPresent(OrderObj.SMART_TRACKER_LABEL, "Smart tracker in LL info section")) {
				type(OrderObj.RP_LNL_Txt, rP_LNL_Txt, "RP_LNL_Txt text box");
				click(OrderObj.LLI_CONTINUE_BTN, "Continue button");
			}
		}
	}

	/**
	 * This method is to clear phone field in checkout page
	 * 
	 * @throws Throwable
	 * 
	 */
	public void clearPhoneFieldInCheckOut() throws Throwable {
		if (isElementPresent(CartObj.PHONE_FIELD, "Phone field")) {
			clearData(CartObj.PHONE_FIELD);
			click(OrderObj.CONTINUE_BTN, "Continue button");
			Thread.sleep(2000);
		}
	}

	/**
	 * This method is to fill payment info
	 * 
	 * @throws Throwable
	 * 
	 */

	public void shippingBillPayInCheckOut(String cardNumber, String cardName, String month, String year,
			String PONumber) throws Throwable {
		click(OrderObj.CONTINUE_BTN, "Continue button of Shipping address"); // clicking continue in Shipping address
		Thread.sleep(2000);
		click(OrderObj.CONTINUE_BTN, "Continue button of Shipping options");// clicking continue in Shipping options
		Thread.sleep(2000);
		click(OrderObj.PAYMENT_METHOD_DD, "payment method drop down");
		click(OrderObj.PAYMENT_METHOD_SELECTION, "payment method selection");
		type(OrderObj.CARD_NUMBER_TEXTBX, cardNumber, "Card number"); // Entering Card details in payment info
		type(OrderObj.CARD_NAME_TEXTBOX, cardName, "Card name");
		click(OrderObj.EXPIRATION_MONTH, "Expiration month");
		selectByValue(OrderObj.EXPIRATION_MONTH, month, "Expiration month");
		click(OrderObj.EXPIRATION_YEAR, "Expiration year");
		selectByValue(OrderObj.EXPIRATION_YEAR, year, "Expiration year");
		click(OrderObj.REVIEW_ORDER_BTN, "review order button");
		type(OrderObj.PO_NUMBER, PONumber, "PO number");
		Thread.sleep(2000);
		click(OrderObj.REVIEW_ORDER_BTN, "review order button of payment Info");
		// expectedSummaryTotalAmount=getText(SUMMARY_TOTAL_AMOUNT,
		// "summaryTotalAmount");

	}

	public void verifyNotificationEmailInShippingAdresses(String email) throws Throwable {
		if (isElementPresent(CartObj.verifyNotificationEmailInShippingAdresses(email), "Verifying email")) {
			reporter.SuccessReport("Verify Notification emails on Place Order page", "is present", email);
		}
	}

	/**
	 * This method is to verify RP_HDL_Txt text
	 * 
	 * @throws Throwable
	 * 
	 */
	public void verifyRpHdlTxt(String rpHdlText) throws Throwable {
		if (isElementPresent(CartObj.verifyRpHdlText(rpHdlText), "rpHdl Text")) {
			reporter.SuccessReport("Verify smart tracker ", "" + rpHdlText + " is displayed", "");
		} else {
			reporter.failureReport("Verify smart tracker", "" + rpHdlText + " is not displayed", "", driver);

		}
	}

	/**
	 * This method is to verify RP_LNL_Txt text
	 * 
	 * @throws Throwable
	 * 
	 */
	public void verifyRpLnllTxt(String rpLnlText) throws Throwable {
		JSScroll(CartObj.verifyRpHdlText(rpLnlText), "rpHdl Text");
		if (isElementPresent(CartObj.verifyRpHdlText(rpLnlText), "rpHdl Text")) {
			reporter.SuccessReport("Verify smart tracker ", "" + rpLnlText + " is displayed", rpLnlText);
		} else {
			reporter.failureReport("Verify smart tracker", "" + rpLnlText + " is not displayed", rpLnlText, driver);

		}
	}

	/**
	 * Method is to select carrier option in the ship bill page
	 * 
	 * @throws Throwable
	 */
	public void shippingOptionsCarrierSelectionInCheckOut(String carrier) throws Throwable {

		click(OrderObj.CONTINUE_BTN, "Continue button of Shipping address");
		if (isElementPresent(OrderObj.SHIPPING_CARRIER_REQUIRED_MSG, "A shipping carrier is required message")) {
			click(OrderObj.SELECT_CARRIER_DD, "carrier Drop down");
			click(OrderObj.selectCarrier(carrier), "select carrier");

		} else {
			// do nothing
		}
	}

	/**
	 * Method is to verify carriers in checkout
	 * 
	 * @throws Throwable
	 */
	public void verifyCarriersInCheckOut(String carrier) throws Throwable {
		if (isElementPresent(OrderObj.SELECT_CARRIER_DD, "carrier Drop down")) {
			click(OrderObj.SELECT_CARRIER_DD, "carrier Drop down");
			String carriers[] = carrier.split(",");
			for (i = 0; i < carriers.length; i++) {
				if (isElementPresent(OrderObj.selectCarrier(carriers[i]), carrier)) {
					reporter.SuccessReport("verify carrier options", carriers[i] + " is present", carriers[i]);
				} else {
					reporter.failureReport("verify carrier options", carriers[i] + " is not present", carriers[i],
							driver);
				}
			}
		} else if (isElementPresent(OrderObj.SELCET_CARRIER, "carrier Drop down")) {
			click(OrderObj.SELCET_CARRIER, "carrier Drop down");
			String carriers[] = carrier.split(",");
			for (i = 0; i < carriers.length; i++) {
				if (isElementPresent(OrderObj.selectCarrier(carriers[i]), carrier)) {
					reporter.SuccessReport("verify carrier options", carriers[i] + " is present", carriers[i]);
				} else {
					reporter.failureReport("verify carrier options", carriers[i] + " is not present", carriers[i],
							driver);
				}
			}
		}
	}

	public void selectCarrier(String carrier) throws Throwable {

		click(OrderObj.SELCET_CARRIER, "carrier Drop down");
		if (isElementPresent(ShipBillPayObj.selectCarrierDD(carrier), "shipping carrier Dropdown")) {
			click(ShipBillPayObj.selectCarrierDD(carrier), "carrier Drop down");

		}
	}

	/**
	 * Method is to get shipping method cost
	 * 
	 * @throws Throwable
	 */
	public String getShippingMethodCost(String shippingCarrier) throws Throwable {
		String shippingCost = getText(OrderObj.shippingCarrierCharges(shippingCarrier), "Shipping charges");
		return shippingCost;
	}

	/**
	 * Method is to select shipping method
	 * 
	 * @throws Throwable
	 */

	public void selectShippingMeethod(String shippingCarrier) throws Throwable {
		click(OrderObj.shippingCarrier(shippingCarrier), "shipping carrier: " + shippingCarrier);
	}

	/**
	 * Purpose of this method is to delete Cart From Account Tools
	 * 
	 * @param cartName
	 * @throws Throwable
	 */
	public void deleteCartFromAccountTools(String cartName) throws Throwable {
		click(CartObj.ACCOUNT_TOOLS, "ACCOUNT TOOLS");
		click(CartObj.TOOLS, "TOOLS");
		click(CartObj.SAVEDCART, "SAVED CART");
		click((CartObj.deleteButton(cartName)), "Delete cart");
		waitForVisibilityOfElement(CartObj.YES_BUTTON_INCONFORMATION_POP_UP, "Yes in conformation pop up");
		click(CartObj.YES_BUTTON_INCONFORMATION_POP_UP, "Yes in conformation pop up");
		waitForVisibilityOfElement(CartObj.DELETE_CART_MEASSAGE, "ACCOUNT TOOLS");
		if (isElementPresent(CartObj.DELETE_CART_MEASSAGE, "Delete cart sucess meassage")) {
			reporter.SuccessReport("Delete cart meassage ", "Cart is sucessfully deleted", "");
		} else {
			reporter.failureReport("Delete cart meassage ", "Cart is sucessfully not deleted", "", driver);

		}
	}

	/*
	 * PURPOSE OF METHOD : Verify cart is empty
	 * 
	 * @author :
	 */

	public void verifyCartIsEmpty() throws Throwable {
		waitForVisibilityOfElement(CartObj.CART_ITEMS, "CART ITEMS");
		if (isElementPresent(CartObj.CART_ITEMS, "cart items")) {
			reporter.SuccessReport("cart message ", "Cart is empty", "");
		} else {
			click(CartObj.CART, "CART");
			commonLib.emptyCartAndVerify();
			reporter.failureReport("Delete cart meassage ", "Cart is not empty", "", driver);

		}
	}

	/**
	 * This method is to compare two prices
	 * 
	 * @param open
	 *            market price and us commodity price
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void VerifyOpenMarketPriceAndUSCommodityPrice(String openMarketPrice, String USCommodityPrice)
			throws Throwable {
		if (openMarketPrice.contains(USCommodityPrice)) {
			reporter.failureReport("Verify the Open Market Price And US Commodit Price",
					" Open Market Price And US Commodit Price are same ", "", driver);
		} else {
			reporter.SuccessReport("Verify the Open Market Price And US Commodit Price",
					"  Open Market Price And US Commodit Price are not same ", "");
		}

	}

	public void VerifyLoginPriceAndNonLoginPrice(String priceInLogin, String priceWithoutLogin) throws Throwable {
		if (priceInLogin.equalsIgnoreCase(priceWithoutLogin)) {
			reporter.failureReport("Verify the login price and non login price",
					" Login price and non login prices are same", "", driver);
		} else {
			reporter.SuccessReport("Verify the login price and non login price",
					"  Login price and non login prices are different ", "");
		}

	}

	public void verifySummaryPriceInLoginAndNonLogin(String priceInLogin, String priceWithoutLogin) throws Throwable {
		if (priceInLogin.equalsIgnoreCase(priceWithoutLogin)) {
			reporter.failureReport("Verify the login price and non login price in summary",
					" Login price and non login prices are same", "", driver);
		} else {
			reporter.SuccessReport("Verify the login price and non login price in summary",
					"  Login price and non login prices are different ", "");
		}

	}

	public String getSummaryAmountInCart() throws Throwable {
		Thread.sleep(5000);
		String summaryAmount = getText(CartObj.SUMMARY_TOTAL, "summaryTotalAmount");

		return summaryAmount;
	}

	public String getShippingEstimateInCart() throws Throwable {
		String shipingCharges = getText(CartObj.SHIPPING_ESTIMATE, "SHipping Charges");
		return shipingCharges;

	}

	/**
	 * This method is to verify RP_HDL_Txt text
	 * 
	 * @throws Throwable
	 * 
	 */
	public void verifyRpHdlTxtisNotPresent(String rpHdlText) throws Throwable {
		if (isElementPresent(CartObj.verifyRpHdlText(rpHdlText), "rpHdl Text")) {
			reporter.failureReport("Verify smart tracker ", "" + rpHdlText + " is displayed", "", driver);
		} else {
			reporter.SuccessReport("Verify smart tracker", "" + rpHdlText + " is not displayed", "");

		}
	}

	/**
	 * This method is to verify RP_LNL_Txt text
	 * 
	 * @throws Throwable
	 * 
	 */
	public void verifyRpLnllTxtisNotPresent(String rpLnlText) throws Throwable {
		if (isElementPresent(CartObj.verifyRpHdlText(rpLnlText), "rpHdl Text")) {
			reporter.failureReport("Verify smart tracker ", "" + rpLnlText + " is displayed", rpLnlText, driver);
		} else {
			reporter.SuccessReport("Verify smart tracker", "" + rpLnlText + " is not displayed", rpLnlText);

		}
	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void verifyPrintPopUp() throws Throwable {
		waitForVisibilityOfElement(CartObj.VIEW_PRINTABLE_POPUP, "PRINT POPUP");
		isElementPresent(CartObj.INSIGHT_LOGO_IN_PRINT_POPUP, "INSIGHT LOGO IN PRINT POPUP");
		isElementPresent(CartObj.QUANTITY_IN_PRINT_POPUP, "QUANTITY IN PRINT POPUP");
		isElementPresent(CartObj.UNIT_PRICE_IN_PRINT_POPUP, "UNIT PRICE IN PRINT POPUP");
	}

	/**
	 * This method is to search for a web group in the CMT home page.
	 * 
	 * @param webGroup
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public String getTotalPrice() throws Throwable {
		isElementPresent(CartObj.TOTAL_PRICE_IN_POPUP, "TOTAL PRICE OF ITEM IN POPUP");
		String totalPrice = getText(CartObj.TOTAL_PRICE_IN_POPUP, "TOTAL PRICE OF ITEM IN POPUP");
		totalPrice = totalPrice.split(":")[1];

		return totalPrice;

	}

	/**
	 * This method is to search for a web group in the CMT home page.
	 * 
	 * @param webGroup
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void cartBasicsIPS_verifyPermissionAtUserLevel() throws Throwable {

		clickMorePricesAvilableInProductInfo();
		clickOnOpenMarketPrice();
		clickOnAddToCartInAllContractPrices();

		commonLib.closePopUp();

	}

	/**
	 * This method is to Add a second part
	 * 
	 * @param webGroup
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void cartBasicsIPS_verifyPermissionAtUserLevelAddingSecondPart(String menuName, String userPermission,
			String searchItem, String quantity, String logoutHeader) throws Throwable {

		commonLib.clickRolesAndPermissionsAtUserLevel();
		cmtLib.setPermissionsToDisable(menuName, userPermission);
		String mainWindow = parentWindow();
		cmtLib.clickOnloginAs();
		switchToWindow(mainWindow);
		commonLib.searchProduct(searchItem);
		clickMorePricesAvilable(0);
		clickOnUSCommuditiesPrice();
		clickOnAddToCartInAllContractPrices();
		String usCommodityPrice = getTotalPrice();
		VerifyOpenMarketPriceAndUSCommodityPrice(openMarketPrice, usCommodityPrice);
		commonLib.closePopUp();
		commonLib.clickCart();
		commonLib.updateCartQuantity(quantity);
		commonLib.clickLogOutLink(logoutHeader);
		switchToWindow(mainWindow);
	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI/SOWJANYA
	 */
	public void clickAndVerifyViewPrintablePopUp(String orderUtilities) throws Throwable {
		commonLib.clickCart();
		cmtLib.handleWelcomeToInsightBetaPopUp();
		waitForVisibilityOfElement(CartObj.getShoppingCartOrderUtilities(orderUtilities), "PRINT LINK");
		click(CartObj.getShoppingCartOrderUtilities(orderUtilities), "PRINT LINK");
		verifyPrintPopUp();
		// clickPrintInPopUp();
		// NEED TO DO VALIDATION IN PRINT PDF
		closePrintPopUp();
	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void clickAndVerifySendToAColleagueErrorMSG(String orderUtilities) throws Throwable {
		commonLib.clickCart();
		cmtLib.handleWelcomeToInsightBetaPopUp();
		waitForVisibilityOfElement(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Send to a colleague");
		click(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Send to a colleague");
		waitForVisibilityOfElement(CartObj.SEND_TO_A_COLLEGUE_POPUP, "SEND TO A COLLEGUE POPUP");
		waitForVisibilityOfElement(CartObj.SEND_BUTTON_IN_SEND_TO_A_COLLEGUE_POPUP,
				"SEND BUTTON IN SEND TO A COLLEGUE POPUP");
		click(CartObj.SEND_BUTTON_IN_SEND_TO_A_COLLEGUE_POPUP, "SEND BUTTON IN SEND TO A COLLEGUE POPUP");
		verifyErrorMessagesInSendToAColleaguePopUp();
		click(CartObj.CLOSE_SEND_TO_A_COLLEGUE_POPUP, "CLOSE SEND TO A COLLEGUE POPUP");
	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI/SOWJANYA
	 */
	public void verifyErrorMessagesInSendToAColleaguePopUp() throws Throwable {
		isElementPresent(CartObj.YOUR_NAME_ERROR_MESSAGE, "YOUR NAME ERROR MESSAGE");
		isElementPresent(CartObj.YOUR_EMAIL_ERROR_MESSAGE, "YOUR EMAIL ERROR MESSAGE");
		isElementPresent(CartObj.RECIPIENT_EMAIL_ERROR_MESSAGE, "RECIPIENT EMAIL ERROR MESSAGE");
	}

	/**
	 * PURPOSE:
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void verifySendToAColleague(String orderUtilities, String yourName, String yourEmail, String recipientEmail,
			String yourComments) throws Throwable {
		waitForVisibilityOfElement(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Send to a colleague");
		click(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Send to a colleague");

		waitForVisibilityOfElement(CartObj.YOUR_NAME_TEXT_FIELD, "YOUR NAME TEXT FIELD");
		clickOnly(CartObj.YOUR_NAME_TEXT_FIELD, "YOUR NAME TEXT FIELD");
		typeUntil(CartObj.YOUR_NAME_TEXT_FIELD, yourName, "YOUR NAME TEXT FIELD");

		waitForVisibilityOfElement(CartObj.YOUR_EMAIL_TEXT_FIELD, "YOUR EMAIL TEXT FIELD");
		clickOnly(CartObj.YOUR_EMAIL_TEXT_FIELD, "YOUR EMAIL TEXT FIELD");
		typeUntil(CartObj.YOUR_EMAIL_TEXT_FIELD, yourEmail, "YOUR EMAIL TEXT FIELD");

		waitForVisibilityOfElement(CartObj.RECIPIENT_EMAIL_TEXT_FIELD, "RECIPIENT EMAIL TEXT FIELD");
		clickOnly(CartObj.RECIPIENT_EMAIL_TEXT_FIELD, "RECIPIENT EMAIL TEXT FIELD");
		typeUntil(CartObj.RECIPIENT_EMAIL_TEXT_FIELD, recipientEmail, "RECIPIENT EMAIL TEXT FIELD");

		waitForVisibilityOfElement(CartObj.YOUR_COMMENTS_TEXT_FIELD, "YOUR COMMENTS TEXT FIELD");
		clickOnly(CartObj.YOUR_COMMENTS_TEXT_FIELD, "YOUR COMMENTS TEXT FIELD");
		typeUntil(CartObj.YOUR_COMMENTS_TEXT_FIELD, yourComments, "YOUR COMMENTS TEXT FIELD");

		waitForVisibilityOfElement(CartObj.SEND_BUTTON_IN_SEND_TO_A_COLLEGUE_POPUP,
				"SEND BUTTON IN SEND TO A COLLEGUE POPUP");
		click(CartObj.SEND_BUTTON_IN_SEND_TO_A_COLLEGUE_POPUP, "SEND BUTTON IN SEND TO A COLLEGUE POPUP");

		waitForVisibilityOfElement(CartObj.MAIL_SEND_TO_A_COLLEGUE_SUCCESS_MSG, "SUCCESS MSG");
		isElementPresent(CartObj.MAIL_SEND_TO_A_COLLEGUE_SUCCESS_MSG, "SUCCESS MSG", true);

	}

	/**
	 * PURPOSE:
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void clickOnCompanyStandards() throws Throwable {
		waitForVisibilityOfElement(CartObj.COMPANY_STANDARDS, "COMPANY STANDARDS");
		click(CartObj.COMPANY_STANDARDS, "COMPANY STANDARDS");
	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI/SOWJANYA
	 */
	public void clickAndVerifySendToAColleagueErrorMSG_IPS(String orderUtilities) throws Throwable {
		commonLib.clickCart();

		waitForVisibilityOfElement(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Send to a colleague");
		click(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Send to a colleague");
		waitForVisibilityOfElement(CartObj.SEND_TO_A_COLLEGUE_POPUP, "SEND TO A COLLEGUE POPUP");
		waitForVisibilityOfElement(CartObj.SEND_BUTTON_IN_SEND_TO_A_COLLEGUE_POPUP,
				"SEND BUTTON IN SEND TO A COLLEGUE POPUP");
		click(CartObj.SEND_BUTTON_IN_SEND_TO_A_COLLEGUE_POPUP, "SEND BUTTON IN SEND TO A COLLEGUE POPUP");
		verifyErrorMessagesInSendToAColleaguePopUp();
		click(CartObj.CLOSE_SEND_TO_A_COLLEGUE_POPUP, "CLOSE SEND TO A COLLEGUE POPUP");
	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void clickAndVerifyExportCart(String orderUtilities) throws Throwable {
		commonLib.clickCart();
		waitForVisibilityOfElement(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Export as a file");
		mouseClick(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Export as a file");

	}

	/**
	 * This method is to click on the Export cart and validate th eexported excel
	 * 
	 * @param orderUtilities
	 * @throws Throwable
	 */
	public void ClickExportCartAndVerify(String orderUtilities) throws Throwable {
		scrollUp();
		mouseClick(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Export as a file");
		validateCartExport();

	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void verifyQuickShopWithValidSinglePartNumber(String searchItem, String quantity) throws Throwable {
		waitForVisibilityOfElement(CartObj.QUICK_SHOP_ITEM_FIELD, "QUICK SHOP ITEM FIELD");
		clearData(CartObj.QUICK_SHOP_ITEM_FIELD);
		type(CartObj.QUICK_SHOP_ITEM_FIELD, searchItem, "QUICK SHOP ITEM FIELD");
		clearData(CartObj.QUICK_SHOP_QUANTITY_FIELD);
		type(CartObj.QUICK_SHOP_QUANTITY_FIELD, quantity, "QUICK SHOP QUANTITY FIELD");
		click(CartObj.ADD_BUTTON_IN_QUICK_SHOP, "ADD BUTTON IN QUICK SHOP");
		commonLib.spinnerImage();
		verifyItemInCart(searchItem);

	}

	public void verifyQuickShopIsDisable() throws Throwable {

		if (isElementNotPresent(CartObj.QUICK_SHOP_SECTION, "quick shop section")) {
			reporter.SuccessReport("verifying quick shop section :: ", "is  not present", "");
		} else {

			reporter.failureReport("verifying quick shop section :: ", " is present", "", driver);

		}
	}

	/**
	 * PURPOSE: This method is to verify whether item is added to cart or not in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void verifyItemInCart(String itemInCart) throws Throwable {
		waitForVisibilityOfElement(CartObj.getItemInCart(itemInCart), "Item in cart");
		if (driver.findElement(CartObj.getItemInCart(itemInCart)).isDisplayed()) {
			reporter.SuccessReport("verifying item added to cart :: ", " ITEM ADDED TO CART IS :", itemInCart);
		} else {
			reporter.failureReport("verifying item added to cart :: ", "ITEM " + itemInCart + "is not ADDED TO CART",
					itemInCart, driver);

		}
	}

	public void verifyItemInCartByInsightPart(String itemInCart) throws Throwable {
		waitForVisibilityOfElement(CartObj.getItemIncartByInsightPartNumber(itemInCart), "Item in cart");
		if (isElementPresent(CartObj.getItemIncartByInsightPartNumber(itemInCart), "part number")) {
			reporter.SuccessReport("verifying item added to cart :: ", " ITEM ADDED TO CART IS :", itemInCart);
		} else {
			reporter.failureReport("verifying item added to cart :: ", "ITEM " + itemInCart + "is not ADDED TO CART",
					itemInCart, driver);

		}
	}

	/**
	 * 
	 * @param contractName
	 * @throws Throwable
	 */
	public void verifyContractNameInCart(String contractName) throws Throwable {
		if (driver.findElement(CartObj.getContractNameInCart(contractName)).isDisplayed()) {
			reporter.SuccessReport("verifying item added to cart :: ", " with contract name-", contractName);
		} else {
			reporter.failureReport("verifying item is not added to cart :: ", " with contract name-", contractName,
					driver);

		}
	}

	/**
	 * PURPOSE: This method is to verify QuickShop With Invalid PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void verifyQuickShopWithInvalidPartNumber(String searchItem) throws Throwable {
		waitForVisibilityOfElement(CartObj.QUICK_SHOP_ITEM_FIELD, "QUICK SHOP ITEM FIELD");
		clearData(CartObj.QUICK_SHOP_ITEM_FIELD);
		type(CartObj.QUICK_SHOP_ITEM_FIELD, searchItem, "QUICK SHOP ITEM FIELD");
		click(CartObj.ADD_BUTTON_IN_QUICK_SHOP, "ADD BUTTON IN QUICK SHOP");
		if (isElementPresent(CartObj.INAVLID_PART_NO_QUICK_SHOP_ERROR_MESSAGE, "error message")) {
			reporter.SuccessReport("Error Message for invalid Part #s on the Cart Page",
					" Error Message for invalid Part #s Exists", "");
		} else {
			reporter.failureReport("Error Message for invalid Part #s on the Cart Page",
					" Error Message for invalid Part #s does not  Exists", "", driver);
		}
	}

	public String getPartNumber() throws Throwable {

		String[] partNumberArray = getText(CartObj.PART_NUMBER_IN_ADDED_TO_YOUR_CART_POPUP,
				"MFR_NUMBER_PRODUCT_DETAILS_PAGE").replace("\"", "").replace(" ", "").trim().split(":");
		String partNumber = partNumberArray[1].trim();
		return partNumber;
	}

	/**
	 * PURPOSE: This method is to verify verify QuickShop Error Message in shopping
	 * cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void verifyQuickShopErrorMessage() throws Throwable {
		if (driver.findElement(CartObj.QUICK_SHOP_ERROR_MESSAGE).isDisplayed()) {
			reporter.SuccessReport("verifying QUICK SHOP ERROR MESSAGE :: ", " QUICK SHOP ERROR MESSAGE is  visible",
					"");
		} else {
			reporter.failureReport("verifying QUICK SHOP ERROR MESSAGE :: ", " QUICK SHOP ERROR MESSAGE is not visible",
					"", driver);

		}

	}

	/**
	 * This method is to validate the columns in the exported cart excel file.
	 * 
	 * @throws Throwable
	 */
	public void validateCartExport() throws Throwable {

		File src = new File("C:\\Users\\E002542\\Downloads\\exportCart.xls"); // CHANGE PATH ACCORDINGLY
		if (src.exists()) {
			System.out.println("file found");
			reporter.SuccessReport("Verify the file existance ", "File Found", "");
		} else {
			System.out.println("file not found");
			reporter.failureReport("Verify the file existance ", "File not Found", "");
		}
		Workbook wb = WorkbookFactory.create(src);
		Sheet sh = wb.getSheet("send_cart");
		int rownum = sh.getLastRowNum();
		System.out.println(rownum);
		for (int i = 1; i < rownum; i++) {
			Row r = sh.getRow(i);
			short cell = r.getLastCellNum();
			for (int k = 0; k < cell; k++) {
				Cell c = r.getCell(k);
				String val = c.getStringCellValue();

				System.out.println(val);
				reporter.SuccessReport("Getting the values from columns ", "The column value is : ", val);

			}
		}

	}

	/**
	 * This method is to close Print PopUp
	 * 
	 * @throws Exception
	 */
	public void closePrintPopUp() throws Throwable {
		waitForVisibilityOfElement(CartObj.CLOSE_PRINT_POPUP, "CLOSE PRINT POPUP");
		click(CartObj.CLOSE_PRINT_POPUP, "CLOSE PRINT POPUP");

	}

	/**
	 * This method TO VERIFY PRINT POP UP
	 * 
	 * @throws Exception
	 */
	public void clickPrintInPopUp() throws Throwable {
		waitForVisibilityOfElement(CartObj.PRINT_SYMBOL_IN_PRINT_POPUP, "PRINT SYMBOL IN PRINT POPUP");
		click(CartObj.PRINT_SYMBOL_IN_PRINT_POPUP, "PRINT SYMBOL IN PRINT POPUP");

	}

	/**
	 * Method is to click on the first product and click on add to cart.
	 * 
	 * @throws Throwable
	 */
	public void selectFirstProductDisplay() throws Throwable {
		waitForVisibilityOfElement(productsDisplayInfoObj.FIRST_PROD_NAME, "First product in search results page");
		click(productsDisplayInfoObj.FIRST_PROD_NAME, "First product in search results page");
		waitForVisibilityOfElement(productsDisplayInfoObj.BACK_TO_RESULTS, "Back to results");
	}

	/**
	 * This method is to verify the added product group is displayed in the cart
	 * screen.
	 * 
	 * @param productName
	 * @throws Throwable
	 */
	public void verifyProductGroupBundleAddedToCart(String productName) throws Throwable {
		String actualprodGroupName = getText(CartObj.PROD_GROUP_NAME_IN_CART, "Product group name in cart");
		if (actualprodGroupName.equals(productName) && isElementPresent(CartObj.BUNDLEONE, "Bundle one in cart")) {
			reporter.SuccessReport("Verify product group displayed in the cart screen",
					"correct product group is displayed as bundle : ", actualprodGroupName);
		} else {
			reporter.failureReport("Verify product group displayed in the cart screen",
					"correct product group is not displayed : " + actualprodGroupName + " .Expected is: ", productName,
					driver);
		}
	}

	/**
	 * This method is to verify the Manufacture Number/part number/ Item added In
	 * the Cart.
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void verifyItemAddedInCartByMfrNumber(String mfrNumber) throws Throwable {

		waitForVisibilityOfElement(CartObj.PART_NUMBER, "Part Number in cart");
		String MfrNum = getText(CartObj.PART_NUMBER, "Mfr number in cart");
		String[] actualMfrNumber = MfrNum.replace("\" ", "").replace(" ", "").trim().split(":");
		if (actualMfrNumber[1].equals(mfrNumber)) {
			reporter.SuccessReport("Verify the part added in the cart", "Item added to cart verfication is successfull",
					mfrNumber);
		} else {
			reporter.failureReport("Verify the part added in the cart",
					"Item added to cart verfication is not successfull.. Expected : " + mfrNumber + "Actual : "
							+ actualMfrNumber[1],
					"", driver);
		}
		Thread.sleep(3000);
	}

	/**
	 * This method is to verify the page landed to NVIDIA Page
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void verifyWelcomeHeader() throws Throwable {
		if (waitForVisibilityOfElement(CartObj.WELCOME_MESSAGE, "Welcome header Message displayed")) {
			reporter.SuccessReport("Verify wether user navigates to Welcome page or not",
					"User successfully navigated to Welcome NVIDIA page", "");
		} else
			reporter.failureReport("Verify wether user navigates to Welcome page or not",
					"User not navigated to Welcome NVIDIA page", "", driver);
	}
	/*
	 * This method is to verify the page landed to NVIDIA Page and click on Get
	 * started Link
	 * 
	 * @param mfrNumber
	 * 
	 * @throws Throwable
	 */

	public void clickAndVerifyGetStartedLink() throws Throwable {
		if (isElementPresent(CartObj.GETSTARTED_IMG, "Get started Image displayed")) {
			if (click(CartObj.GETSTARTED_IMG, "Get started Image link"))
				reporter.SuccessReport("Verify the Get Started Image and click", "Images exists and clicked", "");
			else {
				reporter.failureReport("Verify the Get Started Image and click", "Unable to click on Image get started",
						"");
			}
		} else
			reporter.failureReport("Verify the Get Started Image and click", "Images does not exist", "", driver);

	}

	/**
	 * This method is to select accessories link
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void clickOnAccesseriosLnk(String link) throws Throwable {
		if (isElementPresent(CartObj.NVIDIALIST_HDR, "NVIDIA Product List")) {
			click(CartObj.ACCESSORIES_LNK, "Accesserios Link");
			scrollUp();
			clickUntil(CartObj.listofAccessories(link), CartObj.DELL_CURRENTTAB, "Dell Accessories Link");
		}
	}

	/**
	 * This method is to select category link
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void clickOnDellLink(String link) throws Throwable {
		click(CartObj.listofAccessories(link), "Dell Power Adapter");
	}

	/**
	 * This method is to select laptops category link
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void clickOnlaptopsLnk() throws Throwable {
		if (isElementPresent(CartObj.NVIDIALIST_HDR, "NVIDIA Product List")) {
			click(CartObj.LENOVOLAPTOPS_LNK, "Lenovo Laptops Link");
		}
	}

	/**
	 * This method is to iterate by clicking on choose this link
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void clickOnChooseThisItemLnk(int size) throws Throwable {
		for (int i = 0; i <= size; i++) {
			click(CartObj.chooseThisItem_lnk(i), "Choose this item Link for the selected product");
			System.out.println(i);
		}
	}

	/**
	 * This method is to select product link
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */

	public void clickOnProductLink(String ProductLink) throws Throwable {
		click(CartObj.product_link(ProductLink), "Product Link");
	}

	/**
	 * This method is to click on Next button
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void clickOnNextButton() throws Throwable {
		click(CartObj.NEXT_BTN, "Next Button");
	}

	/**
	 * This method is to click on main category links
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void clickOnchooseLink(String link) throws Throwable {
		click(CartObj.listofAccessories(link), "Continue Shopping/Proceed to checkout Link");
	}

	/**
	 * This method is to verify the list of part items added to cart
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public List<String> getAddedPartNumberFromCart() throws Throwable {
		List<WebElement> myList = driver.findElements(OrderObj.PART_NUM);
		List<String> addedCartItems = new ArrayList<String>();
		for (int i = 0; i <= myList.size(); i++) {
			addedCartItems.add(myList.get(i).getText());
		}
		return addedCartItems;
	}

	/**
	 * This method is to verify part number and switch to parent Window
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public String getPartNumAndswitchToParentWindow() throws Throwable {
		String[] mfrNum = null;
		Set<String> handle = driver.getWindowHandles();
		if (handle.size() > 2) {
			switchToChildWindow();
			waitForVisibilityOfElement(CartObj.PDP_INSIGHT_IMG,
					"Insight Image is loaded in product description Window");
			mfrNum = getText(CartObj.CART_IDINPRODUCT_PAGE, "Part Number in Product description Page")
					.replace("Mfr Part #", "").replace("UNSPSC", "").replace(" ", "").split(":");
			if (mfrNum[0].isEmpty()) {
				reporter.failureReport("verify the Part Number for the selected product",
						"PDP window is not opened and unable to verify part number", "", driver);
			} else
				reporter.SuccessReport("verify the Part Number for the selected product",
						"PDP window opened and successfully verified the part number", "");
			Thread.sleep(3000);
			driver.close();
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
		}
		return mfrNum[0];
	}

	public void verifyShippingestimator() throws Throwable {
		if (isElementPresent(CartObj.Shipping_EStimator, "Shipping Estimator is Present")) {
			waitForVisibilityOfElement(CartObj.Shipping_EStimator, "Shipping Estimator is Present");
			reporter.SuccessReport("Shipping Estimator is present", "Shipping Estimator is displayed", "");
		} else {
			reporter.failureReport("Shipping Estimator is not present", "Shipping Estimator is not displayed", "",
					driver);
		}
	}

	public void verifyShippingestimatorshippingCarrier(String Postal_code, String upsCarrier, String fedexCarrier)
			throws Throwable {
		waitForVisibilityOfElement(CartObj.Shipping_EStimator, "Shipping Estimator is Present");
		typeText(CartObj.Shipping_Estimator_Textfield, Postal_code, "postalcode");
		click(CartObj.Shipping_Estimator_Applybutton, "Click Apply Button of Shipping Estimator ");
		click(CartObj.Shipping_Estimator_seeallcarriers, "Succesfully Clicked on See all Carriers");
		List<WebElement> myList = driver.findElements(CartObj.verifyshippingCarrier(upsCarrier));
		for (int i = 0; i < myList.size(); i++) {

			if (myList.get(i).isDisplayed()) {

				reporter.SuccessReport("UPS shpping carriers ", "" + myList.get(i).getText() + "are displayed", "");
			} else {
				reporter.failureReport("UPS shpping carriers ", "" + myList.get(i).getText() + "are not displayed", "",
						driver);
			}
		}
		List<WebElement> myList1 = driver.findElements(CartObj.verifyshippingCarrier(fedexCarrier));
		for (int i = 0; i < myList1.size(); i++) {

			if (myList1.get(i).isDisplayed()) {

				reporter.SuccessReport("FedEx shpping carriers ", "" + myList1.get(i).getText() + "are displayed", "");
			} else {
				reporter.failureReport("FedEx shpping carriers ", "" + myList1.get(i).getText() + "are not displayed",
						"", driver);
			}
		}
		click(CartObj.Close_seeallcarriers, "Succesfully see all carriers option is closed");

	}

	public void VerifyonlyFedExoptions(String Postal_code, String fedexCarrier) throws Throwable {
		waitForVisibilityOfElement(CartObj.Shipping_EStimator, "Shipping Estimator is Present");
		typeText(CartObj.Shipping_Estimator_Textfield, Postal_code, "postalcode");
		click(CartObj.Shipping_Estimator_Applybutton, "Click Apply Button of Shipping Estimator ");
		List<WebElement> myList = driver.findElements(CartObj.verifyshippingCarrier(fedexCarrier));
		for (int i = 0; i < myList.size(); i++) {

			if (myList.get(i).isDisplayed()) {

				reporter.SuccessReport("FedEx shpping carriers ", "" + myList.get(i).getText() + "are displayed", "");
			} else {
				reporter.failureReport("FedEx shpping carriers ", "" + myList.get(i).getText() + "are not displayed",
						"", driver);
			}
		}
		click(CartObj.Close_seeallcarriers, "Succesfully see all carriers option is closed");

	}

	public void clickotherthanUSDandFedEx(String Postal_code) throws Throwable {
		waitForVisibilityOfElement(CartObj.Shipping_EStimator, "Shipping Estimator is Present");
		typeText(CartObj.Shipping_Estimator_Textfield, Postal_code, "postalcode");
		click(CartObj.Shipping_Estimator_Applybutton, "Click Apply Button of Shipping Estimator ");
		click(CartObj.otherthanUSDandFedEx, "clicked on Option which is not USD or FedEX");
		click(CartObj.submit_Button, "The cart shipping estimate is updated in the cart");

	}

	////////////////////////// cart inventory///////////
	/**
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @param productGroup
	 * @param productName
	 * @param Text_COI
	 * @throws Throwable
	 */
	public void verifyCOIpart(String toolsMenuName, String dropDown, String productGroup, String productName,
			String Text_COI) throws Throwable {
		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");// ---Tools,Customer-Owned-Inventory
		isElementPresent(CartObj.Current_product_groups, " Current Product Groups page is opened");
		click(CommonObj.getCompanyStandardsProductGroup(productGroup, productName),
				"select product from product group");
		List<WebElement> myList = driver.findElements(CartObj.verificationText(Text_COI));
		for (int i = 0; i < myList.size(); i++) {

			if (myList.get(i).isDisplayed()) {

				reporter.SuccessReport("Products With COI", "" + myList.get(i).getText() + "are displayed", "");
			} else {
				reporter.failureReport("Product With COI", "" + myList.get(i).getText() + "are not displayed", "",
						driver);
			}
		}
		List<WebElement> myList1 = driver.findElements(CartObj.ADD_Checkbox_forCOIproducts);
		int l1 = myList1.size();
		for (int i = 0; i < myList1.size(); i++) {
			if (myList1.get(i).isDisplayed()) {
				myList1.get(i).click();
				reporter.SuccessReport("Add Check Box is Clicked", "Add Check Box is Clicked", "");
			} else {
				reporter.failureReport("Add Check Box is Not Clicked ", "Add Check Box Not is Clicked", "", driver);
			}
		}
		click(CommonObj.ADD_TO_ORDER, "ADD To Order Button is Clicked");
		click(CartObj.closeicon_addtocart, "Add to cart Popup is Closed");
		commonLib.clickCart();
		Thread.sleep(5000);
		List<WebElement> myList2 = driver.findElements(CartObj.Productname_at_cart);
		int l2 = myList2.size();
		if (l1 == l2) {
			reporter.SuccessReport("Products with COI added to CART", "Products with COI added to CART", "");
		} else {
			reporter.failureReport("Products with COI not added to CART", "Products with COI not added to CART", "",
					driver);
		}
		click(CartObj.EMPTY_CART, "Cart is Empty");
	}

	/**
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @param productGroup
	 * @param productName
	 * @param Text_COI
	 * @throws Throwable
	 */
	public void verifyCSIpart(String toolsMenuName, String dropDown, String productGroup, String productName,
			String Text_COI) throws Throwable {
		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");// ---Tools,Customer-Owned-Inventory
		isElementPresent(CartObj.Current_product_groups, " Current Product Groups page is opened");
		click(CommonObj.getCompanyStandardsProductGroup(productGroup, productName),
				"select product from product group");
		List<WebElement> myList = driver.findElements(CartObj.verificationText(Text_COI));
		for (int i = 0; i < myList.size(); i++) {

			if (myList.get(i).isDisplayed()) {

				reporter.SuccessReport("Products With ", "" + myList.get(i).getText() + "are displayed", "");
			} else {
				reporter.failureReport("Product With COI", "" + myList.get(i).getText() + "are not displayed", "",
						driver);
			}
		}
		List<WebElement> myList1 = driver.findElements(CartObj.ADD_Checkbox_forCOIproducts);
		int l1 = myList1.size();
		for (int i = 0; i < myList1.size(); i++) {
			if (myList1.get(i).isDisplayed()) {
				myList1.get(i).click();
				reporter.SuccessReport("Add Check Box is Clicked", "Add Check Box is Clicked", "");
			} else {
				reporter.failureReport("Add Check Box is Not Clicked ", "Add Check Box Not is Clicked", "", driver);
			}
		}
		click(CommonObj.ADD_TO_ORDER, "ADD To Order Button is Clicked");
		click(CartObj.closeicon_addtocart, "Add to cart Popup is Closed");
		commonLib.clickCart();
		List<WebElement> myList2 = driver.findElements(CartObj.Productname_at_cart);
		int l2 = myList2.size();
		if (l1 == l2) {
			reporter.SuccessReport("Products with CSI added to CART", "Products with CSI added to CART", "");
		} else {
			reporter.failureReport("Products with CSI not added to CART", "Products with CSI not added to CART", "",
					driver);
		}
		click(CartObj.EMPTY_CART, "Cart is Empty");
	}

	/**
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @param productGroup
	 * @param productName
	 * @param Text_COI
	 * @throws Throwable
	 */
	public void verifyReservedProducts(String toolsMenuName, String dropDown, String productGroup, String productName,
			String Text_COI) throws Throwable {
		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");// ---Tools,Customer-Owned-Inventory
		isElementPresent(CartObj.Current_product_groups, " Current Product Groups page is opened");
		click(CommonObj.getCompanyStandardsProductGroup(productGroup, productName),
				"select product from product group");
		List<WebElement> myList = driver.findElements(CartObj.verificationText(Text_COI));
		for (int i = 0; i < myList.size(); i++) {

			if (myList.get(i).isDisplayed()) {

				reporter.SuccessReport("Products With ", "" + myList.get(i).getText() + "are displayed", "");
			} else {
				reporter.failureReport("Product With COI", "" + myList.get(i).getText() + "are not displayed", "",
						driver);
			}
		}
		List<WebElement> myList1 = driver.findElements(CartObj.ADD_Checkbox_forCOIproducts);
		int l1 = myList1.size();
		for (int i = 0; i < myList1.size(); i++) {
			if (myList1.get(i).isDisplayed()) {
				myList1.get(i).click();
				reporter.SuccessReport("Add Check Box is Clicked", "Add Check Box is Clicked", "");
			} else {
				reporter.failureReport("Add Check Box is Not Clicked ", "Add Check Box Not is Clicked", "", driver);
			}
		}
		click(CommonObj.ADD_TO_ORDER, "ADD To Order Button is Clicked");
		click(CartObj.closeicon_addtocart, "Add to cart Popup is Closed");
		commonLib.clickCart();
		List<WebElement> myList2 = driver.findElements(CartObj.Productname_at_cart);
		int l2 = myList2.size();
		if (l1 == l2) {
			reporter.SuccessReport("Products with Reserved option added to CART",
					"Products with Reserved option added to CART", "");
		} else {
			reporter.failureReport("Products with Reserved option not added to CART",
					"Products with Reserved option not added to CART", "", driver);
		}
		click(CartObj.EMPTY_CART, "Cart is Empty");
	}

	/**
	 * Method is to verify the cart bread crumb.
	 * 
	 * @throws Throwable
	 */
	public void verifyCartBreadCrumb() throws Throwable {
		if (isElementPresent(CartObj.CART_LABEL_ON_CART_PAGE, "cart page")) {
			reporter.SuccessReport("Verify cart page", "Cart page is displayed", "");
		} else {
			reporter.failureReport("Verify cart page", "Cart page is not displayed", "", driver);
		}
	}

	/**
	 * This method is to verify the contract is present in the cart page.
	 * 
	 * @throws Throwable
	 */
	public void verifyTheItemIsAddedUnderContractInCartPage() throws Throwable {
		String contractName = getText(CartObj.CONTRACT_IN_CART, "contract name");
		if (contractName.isEmpty() || contractName == null) {
			reporter.failureReport("Verify contract present in cart page", "Contract in Cart page is not displayed", "",
					driver);
		} else {
			reporter.SuccessReport("Verify contract present in cart page",
					"Contract in Cart page is displayed as : " + contractName, "");
		}
	}

	public void verifyTheExportedCart(List<String> downloadedExcelContent, List<String> acutalContent)
			throws Throwable {
		if (downloadedExcelContent.equals(acutalContent)) {
			reporter.SuccessReport("Verify export cart content", "Export cart content verification is successfull",
					acutalContent.get(i));
		} else {
			reporter.failureReport("Verify export cart content", "Export cart content verification is successfull",
					acutalContent.get(i), driver);
		}

	}

	/**
	 * Method is to verify the quantity given in the product group displayed in the
	 * cart page
	 * 
	 * @param quantity
	 * @param expectedQty
	 * @throws Throwable
	 */
	public void verifyProductGroupQuantityInCart(List<String> quantity, String expectedQty) throws Throwable {
		for (i = 0; i < quantity.size(); i++) {
			if (quantity.get(i).equals(expectedQty)) {
				reporter.SuccessReport("Verify product Quantity ", "Product Quantity : ", "");
			} else {
				reporter.failureReport("Verify product Quantity ", "Product Quantity verification failed. Actual is: ",
						"", driver);
			}
		}

	}

	public void verifyExportFile(String sheetName, String rowNumber, String columnHeaders) throws Throwable {
		Thread.sleep(10000);
		String sfile = System.getProperty("user.dir") + "\\" + "DownloadedFiles" + "\\" + "exportCart.xls";
		File file = new File(sfile);
		List<String> downloadedExcelContent = CommonLib.readRowFromExcel(sfile, sheetName, Integer.parseInt(rowNumber));
		List<String> acutalContent = Arrays.asList(columnHeaders.split(","));
		System.out.println("Compare content" + downloadedExcelContent.equals(acutalContent));
		if (downloadedExcelContent.equals(acutalContent)) {
			reporter.SuccessReport(columnHeaders, "are avilable", "");
		} else {
			reporter.failureReport(columnHeaders, "are not avilable", "", driver);
		}
		System.out.println("File Deletion :" + file.delete());
		if (file.exists()) {
			file.delete();
		}
	}

	public String getCartQuantity() throws Throwable {
		String quantity = getAttributeByValue(CartObj.QUANTITY_IN_CART, "Quantity in cart");
		return quantity;
	}

	/**
	 * This method is to verify RP_LNL_Txt text
	 * 
	 * @throws Throwable
	 * 
	 */
	public void verifyOnlyOneItemInCartPage() throws Throwable {
		List<WebElement> trashicon = driver.findElements(CartObj.TRASH_ICON);
		if (trashicon.size() == 1) {
			if (isVisibleOnly(CartObj.TRASH_ICON, "Item added to cart is displayed")) {
				reporter.failureReport("Only Zero Usage Part in the Cart",
						"The Cart Removes all parts except for the CITRIX Zero Usage part.", "", driver);
			} else {
				reporter.SuccessReport("Only Zero Usage Part in the Cart",
						"The Cart Not Removes all parts except for the CITRIX Zero Usage part.", "");

			}
		}
	}

}