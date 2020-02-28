package com.insight.Lib;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;

import com.insight.ObjRepo.CanadaObj;
import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.OrderObj;
import com.insight.ObjRepo.CommonObj;
import com.insight.ObjRepo.ShipBillPayObj;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;



public class OrderLib extends OrderObj{

	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	ShipBillPayObj shipBillPayObj=new ShipBillPayObj();
	String expectedWarrantyItemDec;
	String expectedSummaryTotalAmount;
	String referenceNumber;
	/**
	 * This method is to click on continue to check out and add warranty part to the item and verify it.
	 * @throws Throwable
	 */
	public void continueToCheckOutAddWarrantyAndVerifyTheCart(String partNumber) throws Throwable{
		
		click(CONTINUE_TO_CHECKOUT, "Continue to checkout");
		if(isElementPresent(ADD_WARRANTY_LINK,"Warranty link" )){
			click(ADD_WARRANTY_LINK, "Warranty link");
			waitForVisibilityOfElement(warrantyItemsRadioButton(partNumber), "warranty items");
			click(warrantyItemsRadioButton(partNumber), "select warranty part");
			Thread.sleep(3000);
			expectedWarrantyItemDec=driver.findElement(warrentyItemDescription(partNumber)).getAttribute("innerText");
			System.out.println("expectedWarrantyItemDec"+expectedWarrantyItemDec);
			click(ADD_TO_CART_IN_WARRANTY_POPUP, "Add to cart in warranty screen");
			Thread.sleep(30000);
			String actaulWarrantyItemDec=getText(WARRANTY_ITEM_DESC_ON_CART_SCREEN, "item description");
			if (expectedWarrantyItemDec.equals(actaulWarrantyItemDec)) {
				reporter.SuccessReport("Verify the warranty item added.","Warranty added successfully",actaulWarrantyItemDec);
			}else{
				reporter.failureReport("Verify the warranty item added.","Warranty not added successfully.Expected warranty is : ",actaulWarrantyItemDec,driver);
			}
		}else{
			reporter.failureReport("Verify the warranty link.","Warranty Link is not displayed","",driver);
		}
	}
	
	/**
	 * This method is to click on continue to check out and add warranty part to the item and verify it.
	 * @throws Throwable
	 */
	public void continueToCheckOutAddWarrantyAndVerifyWarrentyInCart(String partNumber,String warrentyPartNumber) throws Throwable{
		
		click(CONTINUE_TO_CHECKOUT, "Continue to checkout");
		if(isElementPresent(ADD_WARRANTY_LINK,"Warranty link" )){
			click(ADD_WARRANTY_LINK, "Warranty link");
			waitForVisibilityOfElement(warrantyItemsRadioButton(warrentyPartNumber), "warranty items");
			click(warrantyItemsRadioButton(warrentyPartNumber), "select warranty part");
			Thread.sleep(3000);
			expectedWarrantyItemDec=driver.findElement(warrentyItemDescription(warrentyPartNumber)).getAttribute("innerText");
			System.out.println("expectedWarrantyItemDec"+expectedWarrantyItemDec);
			click(ADD_TO_CART_IN_WARRANTY_POPUP, "Add to cart in warranty screen");
			Thread.sleep(30000);
			String actaulWarrantyItemDec=getText(warrentyItemDescOnCartScreen(partNumber), "item description");
			if (expectedWarrantyItemDec.equals(actaulWarrantyItemDec)) {
				reporter.SuccessReport("Verify the warranty item added.","Warranty added successfully",actaulWarrantyItemDec);
			}else{
				reporter.failureReport("Verify the warranty item added.","Warranty not added successfully.Expected warranty is : ",actaulWarrantyItemDec,driver);
			}
		}else{
			reporter.failureReport("Verify the warranty link.","Warranty Link is not displayed","",driver);
		}
	}
	
	/**
	 * This method is to click on continue to checkOut on the Added to Cart popup.
	 * @throws Throwable
	 */
	public void continueToCheckOutOnAddCart() throws Throwable{
		click(CONTINUE_TO_CHECKOUT, "Continue to checkout");
		if(isElementNotPresent(CanadaObj.CART_LABEL, "Cart header label displayed")) {
			refreshPage();	
		}
			
	}
	/**
	 * This method is to click on proceed to checkout.
	 * @throws Throwable
	 */
	public void proceedToCheckout() throws Throwable{
		commonLib.spinnerImage();
		if(isElementPresent(PROCEED_TO_CHECKOUT, "Proceed to checkout") && isEnabled(PROCEED_TO_CHECKOUT, "Proceed to checkout")){
			clickUntil(PROCEED_TO_CHECKOUT, ORDER_ITEM_INFO_LABEl, "Proceed to checkout");
		}else{
			reporter.failureReport("Verify the Proceed to checkout button visibility","Proceed to checkout is not visible or disabled","",driver);
		}
		isElementPresent(ORDER_ITEM_INFO_LABEl, "order and inforamtion page",true);
	}
	
	
	/**
	 * This method is to fill the Additional information in the Order and item information page.
	 * @param url
	 * @param rP_HDL_Txt
	 * @param wG_HDL_Txt
	 * @param additionalNotes
	 * @param invoiceNotes
	 * @throws Throwable
	 */
	public void addAdditionalInformation(String url,String rP_HDL_Txt,String wG_HDL_Txt,String additionalNotes,String invoiceNotes) throws Throwable{
		verify_url(driver, url);
		if(isElementPresent(ORDER_ITEM_INFO_LABEl, "order and inforamtion page") && isElementPresent(RP_HDL_Txt, "RP_HDL_Txt")){
			type(RP_HDL_Txt,rP_HDL_Txt , "RP_HDL_Txt text box");
			type(WG_HDL_Txt, wG_HDL_Txt, "wG_HDL_Txt tet box");
			click(CONTINUE_BTN, "Continue button");
			}
		else if(isElementPresent(ORDER_ITEM_INFO_LABEl, "order and inforamtion page") && isElementPresent(ADDITIONAL_NOTES, "Additional Notes for this order")){
			type(ADDITIONAL_NOTES,additionalNotes , "Additional Notes for this order");
			type(INVOICE_NOTES, invoiceNotes, "Invoice notes");
			click(CONTINUE_BTN, "Continue button");
		}
	   else if(isElementPresent(ORDER_ITEM_INFO_LABEl, "order and inforamtion page") && (isElementPresent(ADDITIONAL_INFO_NAME,"contact name") || isElementPresent(PMO, "PMO number"))){
				click(CONTINUE_BTN, "Continue button");
			}else{
			reporter.failureReport("Verify the the Order and item information page","Order and item information page is not displayed","",driver);
		}
}

	public void addRPandWGinfoInAddAdditionalInfo(String rP_HDL_Txt,String wG_HDL_Txt,String additionalNotes,String invoiceNotes) throws Throwable{
		type(RP_HDL_Txt,rP_HDL_Txt , "RP_HDL_Txt text box");
		type(WG_HDL_Txt, wG_HDL_Txt, "wG_HDL_Txt tet box");
		click(CONTINUE_BTN, "Continue button");
		type(ADDITIONAL_NOTES,additionalNotes , "Additional Notes for this order");
		type(INVOICE_NOTES, invoiceNotes, "Invoice notes");
	}
	
	
	/**
	 * This method is to fill the add Line Level Information in the  Order and item information page.
	 * @param rP_LNL_Txt
	 * @param wG_LNL_Txt
	 * @throws Throwable
	 */
	public void addLineLevelInformation(String rP_LNL_Txt,String wG_LNL_Txt) throws Throwable{
		if(isElementPresent(LINE_LEVEL_INFO, "Line level information link")){
		click(LINE_LEVEL_INFO, "Line Level Information");
		 if(isElementPresent(SMART_TRACKER_LABEL,"Smart tracker in LL info section")){
			click(LLI_CONTINUE_BTN, "Continue button");
	  }else{
		type(RP_LNL_Txt, rP_LNL_Txt, "RP_LNL_Txt text box");
		type(WG_LNL_Txt, wG_LNL_Txt, "wG_LNL_Txt text box");
		click(LLI_CONTINUE_BTN, "Continue button");
	    }
	 }
		else{
		click(LINE_LEVEL_INFO_LINK_BUNDLE, "Line level information link for bundle products");
		type(RP_LNL_Txt, rP_LNL_Txt, "RP_LNL_Txt text box");
		type(WG_LNL_Txt, wG_LNL_Txt, "wG_LNL_Txt text box");
		click(LLI_CONTINUE_BTN, "Continue button");
	  }
	}
	
	/**
	 * this method is to fill payment info details
	 * @param cardNumber
	 * @param cardName
	 * @param month
	 * @param year
	 * @throws Throwable
	 */

	public void shippingBillPay(String cardNumber,String cardName,String month,String year,String PONumber) throws Throwable{
		 click(CONTINUE_BTN, "Continue button of Shipping address");  // clicking continue in Shipping address 
		 Thread.sleep(2000);
		 click(CONTINUE_BTN, "Continue button of Shipping options");// clicking continue in Shipping options
		 Thread.sleep(2000);
		 if(isElementPresent(SHIPPING_CARRIER_REQUIRED_MSG, "A shipping carrier is required message")){
			 click(SELECT_CARRIER_DD, "carrier Drop down");
			 click(SELECT_UPS_CARRIER, "select carrier");
			 click(CARRIER_PRICE_RADIO_BTN, "carrier price - days");
			 click(CONTINUE_BTN, "Continue button of Shipping options");
		 }
		 click(CONTINUE_BTN, "Continue button of Billing Address");  // clicking continue in Billing Address
		 Thread.sleep(2000);
		 
		 if(isElementPresent(DEFAULT_SAVED_CARD_DETAILS, "Default card details")){
		  LOG.info("Card details are already saved");
		 }else
		  if(isElementPresent(PAYMENT_METHOD_TERM,"Terms is selected in dropdown")){
		  type(PO_NUMBER, PONumber, "PO number");
		  click(REVIEW_ORDER_BTN, "review order button of payment Info");  // clicking review order button in Payment info
		  }
		 
		 else{
		  click(PAYMENT_METHOD_DD, "payment method drop down");
		  click(PAYMENT_METHOD_SELECTION, "payment method selection");
		  type(CARD_NUMBER_TEXTBX, cardNumber, "Card number"); // Entering Card details in payment info
		  type(CARD_NAME_TEXTBOX, cardName, "Card name");
		  click(EXPIRATION_MONTH, "Expiration month");
		  selectByValue(EXPIRATION_MONTH,month , "Expiration month");
		  click(EXPIRATION_YEAR, "Expiration year");
		  selectByValue(EXPIRATION_YEAR,year , "Expiration year");
		  click(REVIEW_ORDER_BTN, "review order button");
		  Thread.sleep(2000);
		  expectedSummaryTotalAmount=getText(SUMMARY_TOTAL_AMOUNT, "summaryTotalAmount");
		 
		 }
		}
	
	/**
	 * This method is to verify the Review order details.
	 * @param additionalNotes
	 * @param invoiceNotes
	 * @throws Throwable
	 */
	public void verifyReviewOrderPageDetails(String additionalNotes,String invoiceNotes) throws Throwable{
		 if(isElementPresent(PLACE_ORDER_LABEL, "Place order label")){
			
			 String actualAdditionalNotes=getText(REVIEW_ORDER_ADDITIONAL_NOTES, "Additional Notes");
			if(additionalNotes.equals(actualAdditionalNotes)){
				reporter.SuccessReport("Verify the Additional Notes in Review order page.","Additional Notes verification is successfull.","");
			}else{
				reporter.failureReport("Verify the Additional Notes in Review order page.","Additional Notes verification is not successfull.","");
			}
			
			String actualInvoiceNotes=getText(REVIEW_ORDER_INVOICE_NOTES, "Invoice review order");
			if(invoiceNotes.equals(actualInvoiceNotes)){
				reporter.SuccessReport("Verify the Invoice Notes in Review order page.","Invoice Notes verification is successfull.","");
			}else{
				reporter.failureReport("Verify the Invoice Notes in Review order page.","Invoice Notes verification is not successfull.","");
			}
			
			String actaulWarrantyItemDec=getText(WARRANTY_ITEM_DESC_ON_CART_SCREEN, "item description");
			if(expectedWarrantyItemDec.equals(actaulWarrantyItemDec)){
				reporter.SuccessReport("Verify the warranty info in Review order page.","warranty info verification is successfull.","");
			}else{
				reporter.failureReport("Verify the warranty info in Review order page.","warranty info verification is not successfull.","");
			}
			click(PLACE_ORDER_BTN, "place order button");
		 }
	}
	
	/**
	 * This method is to verify the order and date in the receipt page.
	 * @throws Throwable
	 */
	public void placeOrderAndVerifyReceiptOrderAndDate(String totalSummary) throws Throwable {

		clickUntil(PLACE_ORDER_BTN, RECEIPT_LABEL,"Place order button");
		Thread.sleep(3000);

		if (isElementPresent(RECEIPT_LABEL, "receipt")) {

			// Reference number verification
			if (isElementPresent(REFERENCE_ORDER_NUM, "Reference number")) {
				referenceNumber = getText(REFERENCE_ORDER_NUM, "Reference number");
				if(referenceNumber.isEmpty()){
					reporter.failureReport("Verify the Reference number ", "The reference number is null or empty. ","",driver);
					
				}else{
					reporter.SuccessReport("Verify the Reference number ", "The reference number: " , referenceNumber);
				}
			} else{
				reporter.failureReport("Verify the Reference number ", "The reference number is null or empty.","",driver);
			}
			
			// Total Amount verification
			if (isElementPresent(TOTAL_AMOUNT, "Total Amount")) {
				String totalAmount = getText(TOTAL_AMOUNT, "Total Amount");
				if(totalSummary.equals(totalAmount)){
					reporter.SuccessReport("Verify the Total Amount ", "The Total Amount verification is successfull: " , totalAmount);
				}else{
					reporter.failureReport("Verify the Total Amount ", "The Total Amount is not updated correctly. ","",driver);
				}
			} else {
				reporter.failureReport("Verify the Total Amount ", "The Total Amount is not updated. ","",driver);
			}

			// date ordered verification
			if (isElementPresent(DATE_ORDERED, "Date ordered")) {
				String dateOrdered = getText(DATE_ORDERED, "Date ordered");
				String actualDate = getCurrentDateTime("dd-MMM-yyyy");
				if (actualDate.contains(dateOrdered)) {
					reporter.SuccessReport("Verify the Date ordered ", " date ordered verification is successfull","");
				} else {
					reporter.failureReport("Verify the Date ordered ", " date ordered verification is not successfull : "+dateOrdered+" .Expected Date :",actualDate,driver);
				}
			}
		}
	}
	
	/**
	 * This method is to click on the edit link on the place order page.
	 * @param sectionName
	 * @throws Throwable
	 */
	public void editOrderInfo(String sectionName) throws Throwable{
		JSClick(getEditOnReviewOrderPage(sectionName), "Edit link of "+sectionName);
	}
	
	/**
	 * This method is to verify the card ending digits on the payment info section.
	 * @param endingDigits
	 * @throws Throwable
	 */
	public void verifyCardNumberOnEditPaymentInfoSection(String endingDigits) throws Throwable{
		
		if (isElementPresent(getCardNumberEndingvalue(endingDigits), "Card Ending digits")) {
			reporter.SuccessReport("Verify card ending digits ", " Card ending digits verification is successfull","");
		} else {
			reporter.failureReport("Verify card ending digits ",
					"  Card ending digits verification is not successfull","",driver);
		}
	}
	
	/**
	 * This method is to add new card details in the payment info section.
	 * @param cardNumber
	 * @param cardName
	 * @param month
	 * @param year
	 * @param poNumebr
	 * @throws Throwable
	 */
	public void addNewCardInPaymentInfoSection(String cardNumber,String cardName,String month,String year, String poNumebr) throws Throwable{
		click(ADD_NEW_CARD, "Add new card link");
		type(CARD_NUMBER_TEXTBX, cardNumber, "Card number"); // Entering details in payment info
		type(CARD_NAME_TEXTBOX, cardName, "Card name");
		click(EXPIRATION_MONTH, "Expiration month");
		selectByValue(EXPIRATION_MONTH,month , "Expiration month");
		click(EXPIRATION_YEAR, "Expiration year");
		selectByValue(EXPIRATION_YEAR,year , "Expiration year");
		type(PO_NUMBER, poNumebr, "P.O. Number");
		click(REVIEW_ORDER_BTN, "review order button");
	  }
	
	/**
	 * This method is to click on the product description on the place order page.
	 * @throws Throwable
	 */
	public void clickOnProdDescOnPlaceOrderScreen() throws Throwable{
		Thread.sleep(2000);
		clickUntil(PROD_DESC_PLACE_ORDER_PAGE, CartObj.INSIGHT_NUMBER_IN_PRODUCT_DISPLAY, "Product description");
	}
	
	/**
	 * This method is to click on the Review order button on the payment info section.
	 * @throws Throwable
	 */
	public void clickOnReviewOrderButton() throws Throwable{
		clickUntil(REVIEW_ORDER_BTN,PLACEORDER_LABL, "review order button");
		
	}
	
	public void continueButtonOnAdditionalInformationSection() throws Throwable{
		click(OrderObj.CONTINUE_BTN, "Continue button");
	}
	
	/**
	 * This method is to verify the save Order template link in the Order review/ Place order page
	 * @throws Throwable
	 */
	public void verifySaveOrderTemplateExistsOnPlaceOrderPage(String permissionStatus) throws Throwable{
	
		switch(permissionStatus){
		case "ON": 
			if(isElementPresent(CartObj.SAVE_ORDER_TEMPLATE, "Save Order template link") && isElementPresent(CartObj.SAVE_CART_CONTENTS, "Saved Cart Contents")){
				click(CartObj.SAVE_CART_CONTENTS, "Saved Cart Contents");
				Thread.sleep(2000);
				if(isElementPresent(CartObj.SAVE_CART_CONTENTS_POPUP, "Saved Cart Contents popup")){
					reporter.SuccessReport("Verify saved cart contents popup in Review Order page", " Save order Template link exists in Review Order page","");
					click(CartObj.SAVED_CART_CANCEL_BTN, "Saved cart popup cancel button");
				}else{
					reporter.failureReport("Verify saved cart contents popup in Review Order page", " Save cart contents popup does not exist in Review Order page","",driver);
				}
				
				click(CartObj.SAVE_ORDER_TEMPLATE, "Saved order template");
				Thread.sleep(2000);
				if(isElementPresent(CartObj.SAVE_ORDER_TEMPLATE_POPUP, "Saved order template popup")){
					reporter.SuccessReport("Verify Saved order template popup in Review Order page", " Saved order template popup exists in Review Order page","");
					click(CartObj.SAVED_CART_CANCEL_BTN, "Saved cart popup cancel button");
				}else{
					reporter.failureReport("Verify Saved order template popup in Review Order page", " Saved order template popup  does not exist in Review Order page","",driver);
				}
			}
			
		case "OFF":
			if(isElementNotPresent(CartObj.SAVE_ORDER_TEMPLATE, "Save Order template link") && isElementNotPresent(CartObj.SAVE_CART_CONTENTS, "Saved Cart Contents")){
				reporter.SuccessReport("Verify Save order Template and saved cart contents links in Review Order page", " Save order Template does not exists in Review Order page","");
			}
			
		 default: 
				//Do nothing
		}
	}

	/**
	 * Method is to click on the Continue button in the Ship Bill pay page.
	 * @throws Throwable
	 */
	public void shippingBillPayContinueButton() throws Throwable{
		click(CONTINUE_BTN,"Continue button of Shipping address");  // clicking continue in Shipping address ,Shipping options
		 Thread.sleep(1000);
	  }
	
	/**
	 * Method is to click on Continue button on the Line level info section
	 * @throws Throwable
	 */
	public void clickContinueOnLineLevelInfo() throws Throwable{
		if(isVisible(LLI_CONTINUE_BTN, "Continue button")){
			click(LLI_CONTINUE_BTN, "Continue button");
		}
		Thread.sleep(2000);
	}
	/**
	 * Method is to select carrier option in the ship bill page
	 * @throws Throwable
	 */
	public void shippingOptionsCarrierSelection() throws Throwable{
		
		click(CONTINUE_BTN, "Continue button of Shipping address");
		if(isElementPresent(SHIPPING_CARRIER_REQUIRED_MSG, "A shipping carrier is required message")){
			 click(SELECT_CARRIER_DD, "carrier Drop down");
			 click(SELECT_UPS_CARRIER, "select carrier");
			 click(CARRIER_PRICE_RADIO_BTN, "carrier price - days");
			 click(CONTINUE_BTN, "Continue button of Shipping options");
		 }else{
			 //do nothing
		 }
	}
	
	/**
	 * Method is to fill the card details in the payment info section
	 * @param cardNumber
	 * @param cardName
	 * @param month
	 * @param year
	 * @param PONumber
	 * @throws Throwable
	 */
	public void selectPaymentInfoMethodCreditCard(String cardNumber,String cardName,String month,String year,String PONumber) throws Throwable{
		 
		if(isElementPresent(PAYMENT_METHOD_DD, "payment DD")){
			click(PAYMENT_METHOD_DD, "payment method drop down");
			click(PAYMENT_METHOD_SELECTION, "payment method selection");
		}
			  type(CARD_NUMBER_TEXTBX, cardNumber, "Card number"); // Entering Card details in payment info
			  type(CARD_NAME_TEXTBOX, cardName, "Card name");
			  click(EXPIRATION_MONTH, "Expiration month");
			  selectByValue(EXPIRATION_MONTH,month , "Expiration month");
			  click(EXPIRATION_YEAR, "Expiration year");
			  selectByValue(EXPIRATION_YEAR,year , "Expiration year");
			  type(PO_NUMBER, PONumber, "PO number");
		}
		 
/**
 * 	  
 * @param cardNumber
 * @param cardName
 * @param month
 * @param year
 * @param PONumber
 * @throws Throwable
 */
	public void enterCreditCard(String cardNumber,String cardName,String month,String year,String PONumber ) throws Throwable{
		  type(CARD_NUMBER_TEXTBX, cardNumber, "Card number"); // Entering Card details in payment info
		  type(CARD_NAME_TEXTBOX, cardName, "Card name");
		  click(EXPIRATION_MONTH, "Expiration month");
		  selectByValue(EXPIRATION_MONTH,month , "Expiration month");
		  click(EXPIRATION_YEAR, "Expiration year");
		  selectByValue(EXPIRATION_YEAR,year , "Expiration year");
		  type(PO_NUMBER, PONumber, "PO number");		
	  }

	/**
	 * Method is to fill the card details in the payment info section
	 * @param paymentMethod
	 * @throws Throwable
	 */
	public void selectPaymentMethod(String paymentMethod) throws Throwable {
		 click(PAYMENT_METHOD_DD, "payment method drop down");
		  click(paymentSelection(paymentMethod), "payment method selection");
		  click(REVIEW_ORDER_BTN, "review order button of payment Info"); 
	}
	
	/**
	 * Method is to click on the continue button 
	 * @throws Throwable
	 */
	public void clickContinueOnLLIAndShipBillPaySections() throws Throwable{
		 clickContinueOnLineLevelInfo();   // Click continue on Line level Info
         shippingBillPayContinueButton();  // Click continue on  shipping address 
         shippingOptionsCarrierSelection();  // Click continue on shipping options
         shippingBillPayContinueButton();  // Billing address continue button
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
		
		}else{
			reporter.failureReport("Verify reporting fields displayed in the Line level information section","Reporting fields are not displayed Line level information","");
		}
	}

	/**
	 * Method is to fill the Reporting Fields on the Line level info section
	 * @param reportingField4
	 * @param reportingField5
	 * @param reportingField6
	 * @throws Throwable
	 */
	public void enterReportingDetailsInLineLevelInfo(String reportingField4,String reportingField5,String reportingField6) throws Throwable{
		if(isElementPresent(REPORTING_FIELD_4, "Reporting Field 4")){
		type(REPORTING_FIELD_4, reportingField4, "Reporting Field 4");
		type(REPORTING_FIELD_5, reportingField5, "Reporting Field 5");
		type(REPORTING_FIELD_6, reportingField6, "Reporting Field 6");
		
		
		}else{
			reporter.failureReport("Verify reporting fields displayed in the Line level information section","Reporting fields are not displayed Line level information","");
		}
	}
	/**
	 * This method is to add shipping address details.
	 * @param companyname
	 * @param street
	 * @param city
	 * @param zipcode
	 * @throws Throwable
	 */
	
	public void AddNewshippingAddress(String link,String companyName,String street,String city,String zipcode,String state) throws Throwable{
		Thread.sleep(2000);
		if(isElementPresent(headerlinkCheck(link),"Header link check")){
		JSClick(ADDLINK,"Add link");
		type(COMPANY_NAME_LBL,companyName,"company Name");
		type(STREETLINE_LBL,street,"Street Name");
		type(CITY_LBL,city,"City name");
		type(ZIPCODE,zipcode,"ZIP/Postal code");
		selectByVisibleText(SELECT_STATE,state,"STATE"); 
		click(SHIPPING_ADDRESS_CONTINUE_BTN,"Continue button");
		if(isElementPresent(ADDRESS_VALIDATION_WINDOW_HDR, "Address validation popup")){
			click(SAVE_ADDRESS_BTN, "Save address button");
			click(CONTINUE_BTN, "Continue button of Shipping options");// clicking continue in Shipping options
			 Thread.sleep(2000);
			 click(CONTINUE_BTN, "Continue button of Billing Address");  // clicking continue in Billing Address
			}
		}
	}
	
	/**
	 * 
	 * @param PONumber
	 * @throws Throwable
	 */

	public void termsInPaymentInfo(String PONumber) throws Throwable {
		if (isElementPresent(PAYMENT_METHOD_TERM, "Terms is selected in dropdown")) {
			type(PO_NUMBER, PONumber, "PO number");
			click(REVIEW_ORDER_BTN, "review order button of payment Info"); // Clicking Review order button in Payment Info
	   }
	}
	/**
	 * 
	 * @param ActualTax
	 * @throws Throwable
	 */
	public void verifyTheTaxForSearchTerm(String ActualTax) throws Throwable {
		String result = getText(ADDLICENCE_TAX_AMOUNT, "Tax displayed after adding LICENCE");
		if (result.equals(ActualTax)) {
			reporter.SuccessReport("Verify the TAX before and after adding licence",
					"Before and after adding licence tax is same as :" , result);
		} else {
			reporter.failureReport("Verify the TAX before and after adding licence",
					"Before and after adding licence tax is not equal which is" + ActualTax + "and" , result);
		}

	}

	/**
	 * 
	 * @param cardNumber
	 * @param cardName
	 * @param month
	 * @param year
	 * @param poNumebr
	 * @throws Throwable
	 */
	public void addNewCardInPayment(String cardNumber, String cardName, String month, String year, String poNumebr)
			throws Throwable {
		click(PAYMENT_METHOD_DD, "payment method drop down");
		click(PAYMENT_METHOD_SELECTION, "payment method selection");
		type(CARD_NUMBER_TEXTBX, cardNumber, "Card number"); // Entering details
																// in payment
																// info
		type(CARD_NAME_TEXTBOX, cardName, "Card name");
		click(EXPIRATION_MONTH, "Expiration month");
		selectByValue(EXPIRATION_MONTH, month, "Expiration month");
		click(EXPIRATION_YEAR, "Expiration year");
		selectByValue(EXPIRATION_YEAR, year, "Expiration year");
		type(PO_NUMBER, poNumebr, "PO Number");

	}
	

	/**
	 * 
	 * @throws Throwable
	 * 
	 */
	public void taxDeclerationCheckBoxON() throws Throwable {
		if (isElementPresent(TAXDECLERATION_MESSAGE, "Tax Exemption Message displayed")) {
			if (isCheckBoxSelected(TAX_CHECKBOX)) {
				reporter.SuccessReport("Verify the tax check box is checked or not",
						"Tax Exemption Field Exists and checked","");
			} else {
				reporter.failureReport("Verify the tax check box is checked or not",
						"Tax Exemption Field is not checked","",driver);
			}
		} else
			reporter.failureReport("VerifyTax Exemption Field on Ship Bill Pay Place Order Page",
					"Tax Exemption Field doesn't Exists","",driver);
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void clickOnReturnToCartLink() throws Throwable {

		clickUntil(RETURNTOCART_LNK,CART_LABL, "Return to cart link");		
}

	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyCartHeaderLabel() throws Throwable {
		if (isElementPresent(CART_LABL, "Cart header label displayed")) {
			reporter.SuccessReport("Verify wether user navigates to cart page or not",
					"User successfully navigated to cart page","");
		} else {
			reporter.failureReport("Verify wether user navigates to cart page or not",
					"User not navigated to cart page","",driver);
		}
	}

	/**
	 * 
	 * @param partNumber
	 * @param qntyNo
	 * @throws Throwable
	 */
	public void editproductQTY(String partNumber, String qntyNo) throws Throwable {

		clearData(itemPartNumber_Qty(partNumber));
		Thread.sleep(2000);
		type(itemPartNumber_Qty(partNumber), qntyNo, "Update Quantity number");
		click(item_Qty_Update(partNumber), "Updated" + partNumber + " Quantity to:" + qntyNo);
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyPlaceOrderLabel() throws Throwable {
		if (isElementPresent(PLACEORDER_LABL, "Cart header label displayed")) {
			reporter.SuccessReport("Verify wether user navigates to cart page or not",
					"User successfully navigated to Place order page","");
		} else {
			reporter.failureReport("Verify wether user navigates to cart page or not",
					"User not navigated to Place Order page","",driver);
		}
	}

	/**
	 * 
	 * @param productName
	 * @throws Throwable
	 */
	public void searchInCartPage(String productName) throws Throwable {
		if (isElementPresent(CommonObj.SEARCH, "Search input")) {
			type(CommonObj.SEARCH, productName, "Part number");

		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void addLineLevelInfo() throws Throwable {
		if (isElementPresent(SMART_TRACKER_LABEL, "Smart tracker in LL info section")) {
			click(LLI_CONTINUE_BTN, "Continue button");

		}
	}

	/**
	 * 
	 * @throws Throwable
	 * 
	 */
	public void taxDeclerationCheckBoxOFF() throws Throwable {
		if (isElementPresent(TAXDECLERATION_MESSAGE, "Tax Exemption Message displayed")) {
			if (isCheckBoxSelected(TAX_CHECKBOX)) {
				click(TAX_CHECKBOX, "Tax exemption checkbox");
				if (!isCheckBoxSelected(TAX_CHECKBOX)) {
					reporter.SuccessReport("Verify checkbox is unchecked or not",
							"Tax exemption checkbox unchecked successfully","");
				}
			}
		} else
			reporter.failureReport("VerifyTax Exemption Field on Ship Bill Pay Place Order Page",
					"Tax Exemption Field doesn't Exists","");
	}
    /**
     * 
     * @throws Throwable
     */
	public void taxDeclerationUnCheck() throws Throwable {
		if (isElementPresent(TAXDECLERATION_MESSAGE, "Tax Exemption Message displayed")) {
			if (!isCheckBoxSelected(TAX_CHECKBOX)) {
				reporter.SuccessReport("Verify the tax check box is checked or not",
						"Tax Exemption Field Exists and Unchecked Successfully","");
			} else
				reporter.failureReport("Verify the tax check box is checked or not",
						"Tax Exemption Field Exists and checked","",driver);
		} else
			reporter.failureReport("VerifyTax Exemption Field on Ship Bill Pay Place Order Page",
					"Tax Exemption Field doesn't Exists","",driver);
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyTheTaxAfterUncheckingTaxExemptionCheckbox() throws Throwable {
		String result = getText(ADDLICENCE_TAX_AMOUNT, "Tax displayed after adding LICENCE").replace("$", "");
		if (isElementPresent(ADDLICENCE_TAX_AMOUNT, "Tax displayed", true) && (Float.valueOf(result)) > 0) {
			reporter.SuccessReport("Verify Taxes on Place Order Page", "Taxes Exist and shows:" , result);
		} else
			reporter.failureReport("Verify Taxes on Place Order Page", "Place Order Page Shows Tas as 0.00","",driver);
		if (isElementPresent(ADDLICENCE_TAX_AMOUNT, "Tax displayed", true)) {
			reporter.SuccessReport("Verify Taxes on Place Order Page", "Taxes Exist","");
		} else
			reporter.failureReport("Verify Taxes on Place Order Page", "Taxes does not Exist","",driver);
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyEWRFeeAndTax() throws Throwable {
		String result = getText(EWR_FEE_AMOUNT, "EWR Fee displayed after checking the tax checkbox").replace("$", "");
		if (isElementPresent(EWR_FEE_AMOUNT, "Tax displayed", true)) {
			reporter.SuccessReport("Verify Total EWR Fee on Pace Order Page", "Total EWR Fee Exists","");
		} else
			reporter.failureReport("Verify Total EWR Fee on Pace Order Page", "Total EWR Fee Not Exists","",driver);
		if (isElementPresent(EWR_FEE_AMOUNT, "Tax displayed", true) && (Float.valueOf(result)) > 0) {
			reporter.SuccessReport("Verify Total EWR Fee on Pace Order Page", "Total EWR Fee Exists","");
			// Tax Verification
		} else if (isElementPresent(ADDLICENCE_TAX_AMOUNT, "Tax displayed", true)) {
			reporter.SuccessReport("Verify Taxes on Place Order Page", "Taxes verified and Exists","");
		} else {
			reporter.failureReport("Verify Taxes on Place Order Page", "Taxes does not Exist","",driver);
		}
		if (isElementPresent(ADDLICENCE_TAX_AMOUNT, "Tax displayed", true) && Float.valueOf(result) == 0) {
			reporter.SuccessReport("Verify Taxes on Place Order Page", "Place Order Page Shows Tas as 0.00","");
		} else {
			reporter.failureReport("Verify Taxes on Place Order Page", "Place Order Page does not show tax as 0.00","",driver);
		}
	}

	
	/*public void verifyFileUploadOption() throws Throwable{
		if(isElementPresent(UPLOAD_FILE, "File upload")){
			//String fileName="//label[@for='fileUpload'][contains(text(),'UploadFile.xls')]";
			click(UPLOAD_FILE, "Upload file");
			
			// Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "C:\\Users\\e002542\\Documents\\UploadFile.xls");
			
			Runtime.getRuntime().exec("C:\\Users\\e002542\\Documents\\UploadFile.xls");
		   
		}
	}*/
	
	/**
	 * 
	 * @param FileName
	 * @throws Throwable
	 */
	public void verifyUploadedFileInReviewOrderPage(String FileName) throws Throwable{
		String uploadeFile=getText(REVIEW_ORDER_UPLOADED_FILE_NAME, "file uploaded");
		
	}
	
	/**
	 * Method is to verify the Thanks for order message on the Receipt screen
	 * @throws Throwable
	 */
	public void verifyReceiptVerbiage() throws Throwable{
		if(isElementPresent(THANK_YOU_FOR_ORDER_MSG, "Thank you message")){
			reporter.SuccessReport("Verify Receipt Verbiage", "Thank you for order message displayed","");
		}else{
			reporter.failureReport("Verify Receipt Verbiage", "Thank you for order message displayed","",driver);
		}
	}
	
	/**
	 * This method is to verify the Shipping Address details in the Receipt page.
	 * @throws Throwable
	 */
	public void verifyShippingAddressOnReceiptPage(String sectionName) throws Throwable{
		  
		if(isElementPresent(headerlinkCheck(sectionName),"Header link check")){
		// verifying Company name 
		String comapanyName=getText(SHIPPING_ADDRESS_COMPANY_NAME, "Company name");
		  if(comapanyName.isEmpty()){
			  reporter.failureReport("Verify company name on Shipping address section", "Company name does not exist exists ","",driver);
		  }else{
			  reporter.SuccessReport("Verify company name on Shipping address section", "Company name exists: ", comapanyName);
		  }
		
		 // Verifying address 
		  String address=getText(SHIPPING_ADDRESS_IN_RECEIPT, "Address ");
		  if(address.isEmpty()){
			  reporter.failureReport("Verify address on Shipping address section", "Address does not exist exists ","",driver);
		  }else{
			  reporter.SuccessReport("Verify address on Shipping address section", "address exists: ", address);
		  } 
		} else{
			 reporter.failureReport("Verify Shipping address section", "Shipping address section is not visible","",driver);
		}
	}
	
	
	/**
	 * This method is to verify the Billing Address details in the Receipt page.
	 * @throws Throwable
	 */
	public void verifyBillingAddressOnReceiptPage(String sectionName) throws Throwable{
		  
		if(isElementPresent(headerlinkCheck(sectionName),"Header link check")){
		
			// verifying Company name 
		String comapanyName=getText(BILLING_ADDRESS_COMPANY_NAME, "Company name");
		  if(comapanyName.isEmpty()){
			  reporter.failureReport("Verify company name on Billing address section", "Company name does not exist exists ","",driver);
		  }else{
			  reporter.SuccessReport("Verify company name on Billing address section", "Company name exists: ", comapanyName);
		  }
		
		 // Verifying address 
		  String address=getText(BILLING_ADDRESS_IN_RECEIPT, "Address ");
		  if(address.isEmpty()){
			  reporter.failureReport("Verify address on Billing address section", "Address does not exist exists ","",driver);
		  }else{
			  reporter.SuccessReport("Verify address on Billing address section", "address exists: ", address);
		  } 
		} else{
			 reporter.failureReport("Verify Billing address section", "Billing address section is not visible","",driver);
		}
	}
	
	/**
	 * @throws Throwable 
	 * 
	 */
	public void verifyYourCartOnReceiptPage(String mnfrNumber) throws Throwable{
		String[] mnfNo=getText(PART_NUMBER_ON_RECEIPT_PAGE, "Manufacturer number").replace("\"", "").replace(" ", "").trim().split(":");
		if(mnfNo[1].equals(mnfrNumber)){
			reporter.SuccessReport("Verify manufacturer numebr in Receipt page", "Your cart verification is successful","");
		}else{
			reporter.failureReport("Verify manufacturer numebr in Receipt page", "Your cart verification is not successful","");
		}
	}
	
	/**
	 * Method is to click on the account tools side menu and select options
	 * @param toolsMenuName
	 * @param dropDown
	 * @throws Throwable
	 */
	public void clickOnSideMenuSelectAccountToolOptions(String toolsMenuName,String dropDown) throws Throwable{
		   click(CommonObj.ACCOUNT_TOOLS,"Account tools menu icon");   
		   click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		   click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");	
	}
	/**
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @throws Throwable
	 */
	public void refOrderNumberQuickSearch(String toolsMenuName,String dropDown) throws Throwable{
		String[] refNo= referenceNumber.replace("\"", "").trim().split("(");
		clickOnSideMenuSelectAccountToolOptions(toolsMenuName, dropDown);
		if(isElementPresent(RECENT_ORDERS_LABEL, "Recent orders")){
			
		}
	}
	
	/**
	 * 
	 * @param string
	 */
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	
	/**
	 * 
	 * @param exeFileNameOfAutoITWithExetension
	 * @throws Throwable
	 */
	public void verifyFileUploadOption(String exeFileNameOfAutoITWithExetension) throws Throwable {
		 
		try {
			if (isElementPresent(UPLOAD_FILE, "Upload file")) {
				System.out.println(System.getProperty("user.dir") + "\\AutoIt\\" + exeFileNameOfAutoITWithExetension);
				Thread.sleep(2000);
				click(UPLOAD_FILE, "Upload file");
				Runtime.getRuntime()
						.exec(System.getProperty("user.dir") + "\\AutoIt\\" + exeFileNameOfAutoITWithExetension);
				Thread.sleep(3000);

				Thread.sleep(2000);
			} else {
				System.out.println(System.getProperty("user.dir") + "\\AutoIt\\" + exeFileNameOfAutoITWithExetension);
				Runtime.getRuntime()
						.exec(System.getProperty("user.dir") + "\\AutoIt\\" + exeFileNameOfAutoITWithExetension);
				Thread.sleep(3000);
			}

		} catch (FileNotFoundException fe) {
			reporter.failureReport("File Upload", "File not uploaded successfully","");
			fe.printStackTrace();
		}
 }	
	
	/**
	 * Method is to click on the Order details link on receipt page
	 * @throws Throwable
	 */
	public void clickOrderDetailsLinkOnReceiptPage() throws Throwable{
		click(ORDER_DETAILS_LINK, "Order details link");
	}
	
	public void verifyPaymentInformationOnReceiptPage(String sectionName,String month,String year,String name,String endingCardNumber,String cardType) throws Throwable {
		
		if(isElementPresent(headerlinkCheck(sectionName),"Header link check")){
			
			// verify expiration date
			String expDate=getText(getExpirationdate(month, year), "expiration date");
			if(isElementPresent(getExpirationdate(month, year), "expiration date")){
				reporter.SuccessReport("Verify Expiration date in payment info ", "Expiration date verification is successfull. Expiration Date is : ",expDate);
			}else{
				reporter.failureReport("Verify Expiration date in payment info", "Expiration date verification is not successfull","",driver);
			}
			
			// verify name of the card
			String cardName=getText(getNameOfTheCard(name), "card Name");
			if(isElementPresent(getNameOfTheCard(name), "name of the card")){
				reporter.SuccessReport("Verify name of the card in payment info ", "Name of the card  verification is successful.Card Nem is : ",cardName);
			}else{
				reporter.failureReport("Verify name of the card in payment info ", "Name of the card  verification is not successful","");
			}
			
			// Verify card Ending numbers
			if(isElementPresent(getEndingCardNumberOnReceiptPage(endingCardNumber), "number of the card")){
				reporter.SuccessReport("Verify number of the card in payment info ", "Numebr of the card  verification is successful",endingCardNumber);
			}else{
				reporter.failureReport("Verify number of the card in payment info ", "Number of the card  verification is not successful","");
			}
			
			
			// Verify Type of card 
			if(isElementPresent(getTypeOfCard(cardType), "Card type")){
				reporter.SuccessReport("Verify type of the card in payment info ", "Type of the card  verification is successful",cardType);
			}else{
				reporter.failureReport("Verify type of the card in payment info ", "Type of the card  verification is not successful","");
			}
		}
	}
	
	/**
	 * Verify PO number in payment info
	 * @param poNumber
	 * @throws Throwable
	 */
	public void verifyPONumberOnReceiptPage(String poNumber) throws Throwable {
		
		if (isElementPresent(getPONumberOnReceiptPage(poNumber), "PO number")) {
			reporter.SuccessReport("Verify PO number in payment info ", "PO Numebr verification is successful",poNumber);
		} else {
			reporter.failureReport("Verify PO number in payment info ", "PO Number verification is not successful","");
		}
	}
	
	/**
	 * Method is to click on Print icon in cart page
	 * @throws Throwable
	 */
	public void clickPrintIconOnCartPage(String orderUtilities) throws Throwable{
		clickUntil(CartObj.getShoppingCartOrderUtilities(orderUtilities),QUOTE_NAME,"print icon");
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String>  getProductDescriptionOfCartProduct(){
		
		List<WebElement> myList = driver.findElements(CartObj.CART_PROD_DESC);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			all_elements_text.add(myList.get(i).getText());
		}
		return  all_elements_text;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getCartProductQuantity() {
		List<WebElement> myList = driver.findElements(CartObj.CART_PROD_QTY);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			all_elements_text.add(myList.get(i).getAttribute("value"));
		}
		return  all_elements_text;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getCartProductStock() {

		List<WebElement> myList = driver.findElements(CartObj.CART_PROD_STOCK);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			all_elements_text.add(myList.get(i).getText());
		}
		
		return all_elements_text;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getCartProductTotalPrice() {
		
		List<WebElement> myList = driver.findElements(CartObj.CART_PROD_TOTAL_PRICE);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			all_elements_text.add(myList.get(i).getText());
		
	    }return all_elements_text;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getCartProductUnitPrice() {
		
		List<WebElement> myList = driver.findElements(CartObj.CART_PROD_UNIT_PRICE);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			all_elements_text.add(myList.get(i).getText());
		
	    }return all_elements_text;
	}
	
	/**
	 * 
	 * @param prodDesc
	 * @param quantity2
	 * @param stock2
	 * @param totalPrice
	 * @param unitPrice
	 * @throws Throwable
	 */
	public void VerifyPrintPopup(List<String> prodDesc,List<String> quantity2,List<String> stock2,List<String> totalPrice,List<String> unitPrice) throws Throwable{
		
		// verifying the Product description
		List<WebElement> myList = driver.findElements(CartObj.ITEM_DESC);
		List<WebElement> qty = driver.findElements(CartObj.QUANTITY_PRINT_POPUP);
		List<WebElement> stock = driver.findElements(CartObj.STOCK_PRINT_POPUP);
		List<WebElement> total_price = driver.findElements(CartObj.TOTAL_PRICE_PRINT_POPUP);
		List<WebElement> unit_price = driver.findElements(CartObj.TOTAL_PRICE_PRINT_POPUP);
		
		for (int i = 0; i <myList.size() ; i++) {
		    String desc = myList.get(i).getText();
			 if(desc.equals(prodDesc.get(i))){
				 reporter.SuccessReport("Verify product description ", "Product description : ","");
			 }else{
				 reporter.failureReport("Verify product description ", "Product description verification failed. Actual is: ",""); 
			 }
			 
			 String quantity = qty.get(i).getAttribute("value");
			 if(quantity.equals(quantity2.get(i))){
				 reporter.SuccessReport("Verify product Quantity ", "Product Quantity : ",quantity2.get(i));
			 }else{
				 reporter.failureReport("Verify product Quantity ", "Product Quantity verification failed. Actual is: ",""); 
			 }
			 
			 String expectedstock = stock.get(i).getText();
			 if(expectedstock.equals(stock2.get(i))){
				 reporter.SuccessReport("Verify product stock ", "Product stock : ","");
			 }else{
				 reporter.failureReport("Verify product stock ", "Product stock verification failed. Actual is: ",""); 
			 } 
			 
			 String expectedtoatalprice= total_price.get(i).getText();
			 if(expectedtoatalprice.equals(totalPrice.get(i))){
				 reporter.SuccessReport("Verify total price ", "Product total price : ",expectedtoatalprice);
			 }else{
				 reporter.SuccessReport("Verify total price ", "Product total price verification failed. Actual is: ",""); 
			 } 
			
			 String expectedUnitPrice= unit_price.get(i).getText();
			 if(expectedUnitPrice.equals(unitPrice.get(i))){
				 reporter.SuccessReport("Verify total price ", "Product total price : ",expectedUnitPrice);
			 }else{
				 reporter.failureReport("Verify total price ", "Product total price verification failed. Actual is: ",""); 
			 } 
		}
	}

	
	/**
	 * Method is to click on the save quote link and create quote and save it.
	 * @param quoteName
	 * @throws Throwable
	 */
	public void createQuote(String quoteName) throws Throwable{
		clickUntil(CartObj.SAVE_AS_QUOTE,QUOTE_NAME ,"Save as quote Link");
		type(QUOTE_NAME,quoteName, "Quote name");
		click(SAVE_AS_QUOTE_BTN, "save as quote button");
		if(isElementPresent(SAVE_QUOTE_MSG, "Success message")){
			 reporter.SuccessReport("Verify Success message", "Save as Quote - Successful message displayed","");
		 }else{
			 reporter.failureReport("Verify Success message ", "Save as Quote - Successful message not displayed ",""); 
		 
		}
	}
	
	/**
	 * Method is to verify the Tax in save as quote page
	 * @throws Throwable
	 */
	public String verifyTaxInSaveAsQuotePage() throws Throwable{
		String taxAmount=getText(TAX_IN_SAVE_QUOTE, "tax in quote");
		if(taxAmount==null || taxAmount.isEmpty()){
			reporter.failureReport("Verify tax amount in save quote page ", "Tax amount is empty",""); 
		}else{
			reporter.SuccessReport("Verify tax amount in save quote page ", "Tax amount is present",""); 
		}
		return taxAmount;
	}
	
	/**
	 * Method to get the reference number of the quote
	 * @return
	 * @throws Throwable
	 */
	public String getQuoteReferenceNumber() throws Throwable{
		String quoteReferenceNumber=getText(QUOTE_REFERENCE_NUMBER, "Reference number").trim();
		return quoteReferenceNumber;
	}
	
	/**
	 * Method is to Search by Quote Number/ Reference Number in the save as quote screen
	 * @param refNumber
	 * @param quoteDDOption
	 * @throws Throwable
	 */
	public void searchByInQuoteHistory(String refNumber,String quoteDDOption) throws Throwable{
		Thread.sleep(2000);// Waiting for the quote to load
		clickUntil(SEARCH_BY_DD,getSearchByQuoteHistoryDDOption(quoteDDOption), "Search drop down");
		click(getSearchByQuoteHistoryDDOption(quoteDDOption), "Quote search by option");
		type(SEARCH_NUMBER,refNumber , "Reference number");
		System.out.println(refNumber+refNumber);
		click(SEARCH_BTN, "search button");
		Thread.sleep(20000);
		clickUntil(SEARCH_BTN,QUOTE_NUMBER_HISTORY_PAGE, "search button");
		click(QUOTE_NUMBER_HISTORY_PAGE, "Quote Number");
		if(isElementPresent(QUOTE_DETAILS_PAGE_LABEL, "Quote details page")){
			reporter.SuccessReport("Verify Quote details page", "Quote details page is displayed","");
		 }else{
			 reporter.failureReport("Verify Quote details page", "Quote details page not displayed",""); 

		}
	}
	
	/**
	 * Click on convert Quote
	 * @throws Throwable
	 */
	public void convertQuote() throws Throwable{
		if(isElementPresent(CONVERT_QUOTE_BTN, "Convert quote button")){
		click(CONVERT_QUOTE_BTN, "Convert quote button");
		reporter.SuccessReport("Click on Covert Quote Button", "Covert Quote Button is Exists and Selected","");
		 }else{
			 reporter.failureReport("Click on Covert Quote Button", "Covert Quote Button is Not Exists",""); 

		}
	}
	
	/**
	 * Method is to verify the quantity in the cart is disabled
	 * @throws Throwable
	 */
	public void verifyTheQuantityIsdisabled() throws Throwable{
		WebElement element =driver.findElement(CartObj.QUANTITY_DISABLED);
		if(element.isDisplayed()){
			reporter.SuccessReport("Verify Quantity disabled", "Quantity is disabled","");
		}else{
			 
			 reporter.failureReport("Verify Quantity disabled", "Quantity is not disabled",""); 
		}
	}
	/**
	 * Method is to click on edit quote
	 * @throws Throwable
	 */
	public void editQuote() throws Throwable{
		click(EDIT_QUOTE, "Edit quote");
	}
	
	/**
	 * Verify the quantity added in the cart receipt page
	 * @param quantity
	 * @throws Throwable
	 */
	public void verifyTheQuantityOfCartProductOnReceiptPage(String quantity) throws Throwable{
		String actualQuantity=getAttributeByValue(CartObj.QUANTITY, "Quantity");
		if(quantity.equals(actualQuantity)){
			reporter.SuccessReport("Verify Quantity added in cart", "Quantity addede succesfully",quantity); 
		}else{
			reporter.failureReport("Verify Quantity added in cart", "Quantity is not added to the cart correctly. actaul quantity is : "+actualQuantity+ "Expected is : ",quantity); 
		
		}
	}
	
	/**
	 * Method is to verify whether the product bundle table is loaded in the save quote screen
	 * @throws Throwable
	 */
	public void verifyProductBundleTableLoadedInSaveQuoteScreen() throws Throwable{
		if(isElementPresent(BUNDLE_TABLE_IN_SAVE_QUOTE, "Product bundle table in save Quote")){
			reporter.SuccessReport("Verify Product bundle table in save Quote", "Product bundle table is loaded successfully","");
		}else{
			reporter.failureReport("Verify Product bundle table in save Quote", "Product bundle table is not loaded.","");
		}
	}
	
	/**
	 * This method is to click on the approval management link tabs
	 * @param link
	 * @throws Throwable
	 */
	
	public void clickonApprovalManagementTabs(String link) throws Throwable{
		click(getApprovalmanagementtabs(link), "Approval Management Link");
		}
	
	
	/**
	 * Method is to click on the Edit link of a Requestor Group Name
	 * @param groupName
	 * @throws Throwable
	 */
	public void clickOnTheEditLinkOfRequestorGroupNameEditLink(String groupName) throws Throwable{
		click(getRequesterGroupNameEditLink(groupName), "Approval Management Link edit link");
	}
	
	/**
	 * method is to click on the requestor group name tabs.
	 * @param tabName
	 * @throws Throwable
	 */
	public void clickOnTheRequestorGroupNameTabs(String tabName) throws Throwable{
		clickUntil(getRequesterGroupNameScreenEditTabs(tabName),PAYMENT_METHOD_CHK_BOX, "Tab name of Requestor group");
	}
/**
 * 
 * @param rP_LNL_Txt
 * @throws Throwable
 */
	 public void addLineLevelInfoSmartTracker(String rP_LNL_Txt) throws Throwable{
			if(isElementPresent(LINE_LEVEL_INFO, "Line level information link")){
			click(LINE_LEVEL_INFO, "Line Level Information");
			 if(isElementPresent(SMART_TRACKER_LABEL,"Smart tracker in LL info section")){
			type(RP_LNL_Txt, rP_LNL_Txt, "RP_LNL_Txt text box");
			click(LLI_CONTINUE_BTN, "Continue button");
		    }
			}
	 }
	 /**
	  * 
	  * @throws Throwable
	  */
	 public void clearPhnumberInShippinAddress() throws Throwable{
			if(isElementPresent(EXIST_PHNMBR, "Shipping address phone number")){
				clearData(EXIST_PHNMBR);
				String Phnmbr= getText(EXIST_PHNMBR, "Shipping address phone number");
				if(Phnmbr.isEmpty()){
					reporter.SuccessReport("Verify Phone number is cleared", "Phone Number is empty","");
				}else{
					reporter.failureReport("Verify Phone number is cleared", "Phone Number is not null","");
				}
			}
			}
	 /**
	  * 
	  * @throws Throwable
	  */
	 public void clickAndVerifyCopytoAllLink() throws Throwable{
			if (isElementPresent(COPY_TOALL_LNK, "Copy to all link")) {
			click(COPY_TOALL_LNK, "Copy to all link");
				reporter.SuccessReport("Click Copy to all on Line Level Information Page","Copy to all Link Exists and Clicked","");
			}else
				reporter.failureReport("Click Copy to all on Line Level Information Page","Copy to all Link Does Not Exist","");
		}
	 /**
	  * 
	  * @throws Throwable
	  */
	 public void verifyLineLvlInfoReportingFieldsInCartPage() throws Throwable{
			if(isElementPresent(REPORTINGFIELDS_CART, "Reporting Fields in cart page")){
				List<WebElement> fieldsList=driver.findElements(REPORTINGFIELDS_CART);
		         for (int i = 0; i < fieldsList.size(); i++) {
			         if(fieldsList.get(i).isSelected()){
				reporter.SuccessReport("Verify reporting fields cart page","REPORTING FIELDS 4,5 and 6 exists in cart page","");
			}else
				reporter.failureReport("Verify reporting fields cart page","REPORTING FIELDS 4,5 and 6 doesn't exists in cart page","");
			}
			}
	}

	/**
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @param productGroup
	 * @param refNum
	 * @throws Throwable
	 */
	public void verifyReportingFieldsinOrderHistoryPage(String toolsMenuName, String dropDown, String productGroup,
			String refNum) throws Throwable {

		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
		click(orderlinkInOrderHistory(refNum), "select placed order from recent orders");
		Thread.sleep(1000);
		if (isElementPresent(REPORTINGFIELD4_ORDERHISTORY, "Add items check box")) {
			List<WebElement> myList4 = driver.findElements(REPORTINGFIELD4_ORDERHISTORY);
			for (int j = 0; j < myList4.size(); j++) {
				if (myList4.get(j).isDisplayed()) {
					reporter.SuccessReport("Verify Reporting Field4 in the Order Details",
							"Reporting Fields are Exist and Verfied","");
				} else
					reporter.failureReport("Verify Reporting Field4 in the Order Details",
							"Reporting Fields are Not Exist","");
				if (isElementPresent(REPORTINGFIELD5_ORDERHISTORY, "Add items check box")) {
					List<WebElement> myList1 = driver.findElements(REPORTINGFIELD5_ORDERHISTORY);
					for (int k = 0; k < myList1.size(); k++) {
						if (myList1.get(j).isDisplayed()) {
							reporter.SuccessReport("Verify Reporting Field5 in the Order Details",
									"Reporting Fields are Exist and Verfied","");
						} else
							reporter.failureReport("Verify Reporting Field5 in the Order Details",
									"Reporting Fields are Not Exist","");
					}
					if (isElementPresent(REPORTINGFIELD6_ORDERHISTORY, "Add items check box")) {
						List<WebElement> myList = driver.findElements(REPORTINGFIELD6_ORDERHISTORY);
						for (int k = 0; k < myList.size(); k++) {
							if (myList.get(j).isDisplayed()) {
								reporter.SuccessReport("Verify Reporting Field6 in the Order Details",
										"Reporting Fields are Exist and Verfied","");
							} else
								reporter.failureReport("Verify Reporting Field6 in the Order Details",
										"Reporting Fields are Not Exist","");

						}

					}
				}

			}
		}
	}

	/**
	 * This method is to click on the Additional Info Continue button on the
	 * payment info section.
	 * 
	 * @throws Throwable
	 */
	public void clickOnAdditionalInfoContinueButton() throws Throwable {
		click(CONTINUE_BTN, "Continue button");
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyPONumberisEmpty() throws Throwable {
		String PONum = getText(PO_NUMBER, "P.O. Number");
		if (PONum.isEmpty()) {
			reporter.SuccessReport("Delete PO Number in Ship, Bill & Pay Page", "PO Number Field Exists and Deleted","");
		} else
			reporter.failureReport("Delete PO Number in Ship, Bill & Pay Page", "PONumber Field does not Exist","");

	}

	/**
	 * 
	 * @return
	 * @throws Throwable
	 */
	public String getTextfromReferenceNumber() throws Throwable {
		String referenceNum = null;
		if (isElementPresent(RECEIPT_LABEL, "receipt")) {

			// Reference number verification
			if (isElementPresent(REFERENCE_ORDER_NUM, "Reference number")) {
				referenceNum = getText(REFERENCE_ORDER_NUM, "Reference number").replace("(In process)", " ").trim();
				if (referenceNum.isEmpty()) {
					reporter.failureReport("Verify the Reference number ", "The reference number is null or empty. ","");

				} else
					reporter.SuccessReport("Verify the Reference number ", "The reference number: " + referenceNumber,"");
			}
		}
		return referenceNum;
	}

	/**
	 * 
	 * @param orderLink
	 * @throws Throwable
	 */
	public void clickonorderNumLinkinRecentorders(String orderLink) throws Throwable {
		click(orderlinkInOrderHistory(orderLink), "select placed order from recent orders");
	}

	/**
	 * 
	 * @param TabName
	 * @throws Throwable
	 */
	public void verifytabsinOrderDetailsPage(String TabName) throws Throwable {
		if (isElementPresent(tabNameinOrderDetails(TabName), TabName + "link")) {
			click(tabNameinOrderDetails(TabName), TabName + "link");
			reporter.SuccessReport("Verify" + TabName + "link in the Order Detail ","Link Exist and Clicked",TabName);
		} else
			reporter.failureReport("Verify" + TabName + " link in the Order Details ",  "Link Does not Exist",TabName);
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void verifySmartTrackerHeaderInOrderDetails() throws Throwable {
		if (isElementPresent(SMARTRAKER_HDR, "Smart Tracker Header")) {
			reporter.SuccessReport("Header Level Smart Trackers Verification", "Header Level Smart Tracker exists","");
		} else
			reporter.failureReport("Header Level Smart Trackers Verification",
					"Header Level Smart Tracker Does not Exist","");
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void clickOnReviewRequisitionButton() throws Throwable {
		clickUntil(REVIEW_REQUISITION_BTN, PLACE_ORDER_LABEL, "review Requisition button");
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void clickOnPlaceRequisitionButton() throws Throwable {
		clickUntil(PLACE_REQUISITION_BTN,THANK_YOU_FOR_ORDER_MSG, "place Requisition button");
	}

	/**
	 * 
	 * @param refNum
	 * @throws Throwable
	 */
	public void verifyandClickonRefLink(String refNum) throws Throwable {
		if (isElementPresent(APPROVAL_MNGMNT_HDR1, "ApprovalManagement Header")) {
			if(isElementPresent(ReferenceLink(refNum), "Reference Number Link"))
			click(ReferenceLink(refNum), "Reference Number Link");
			reporter.SuccessReport("Click  Ref Number on Requisition Search Results Page",
					"Ref Number Link Exists and Clicked",refNum);
		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyApprovalManagmentHeaderandClickonUpdateLink() throws Throwable {
		if (isElementPresent(APPROVAL_MNGMNT_HDR2, "ApprovalManagement Header")) {
			reporter.SuccessReport("Verify Approval Management  Page", "Approval Management Page Exist","");
		} else
			reporter.failureReport("Verify Approval Management  Page", "Approval Management Page does Not Exists","");
		click(UPDATE_BTN, "Update Button");
		click(CONTINUE_BTNIN, "Continue Button");
		reporter.SuccessReport("Approve Requisition", "Continue Button Exists and Clicked","");
		navigateToBackPage();
		if (isElementPresent(APPROVAL_MNGMNT_HDR2, "ApprovalManagement Header")) {
			if (isElementPresent(APPROVAL_MNGMNT_HDR2, "ApprovalManagement Header")) {
				reporter.SuccessReport("Verify Requisition Status on  Approval Management Page ",
						"Requisition Aprroved","");
			} else
				reporter.failureReport("Verify Requisition Status on  Approval Management Page ",
						"Requisition Not Aprroved","");
		}
	}

	/**
	 * 
	 * @param item1
	 * @throws Throwable
	 */
	public void verifyPartNumberInOrderdetails(String item1) throws Throwable {

		String PartNum[] = getText(CartObj.getPartNuminOrderdetails, "Part Number item in order details page")
				.split(",");
		for (i = 0; i < PartNum.length; i++) {
			if (PartNum[i].equals(item1)) {
				reporter.SuccessReport("Verify product part Number displayed in order details",
						"Part Number is displayed",item1);
			} else {
				reporter.failureReport("Verify product part Number displayed in order details",
						"Part Number displayed is : " + PartNum + " .Expected is: " , item1);
			}
		}
	}

	/**
	 * 
	 * @param url
	 * @param name
	 * @param phonembr
	 * @param email
	 * @throws Throwable
	 */
	public void addAdditionalInformationByNameFields(String url, String name, String phonembr, String email)
			throws Throwable {
		verify_url(driver, url);
		if (isElementPresent(ORDER_ITEM_INFO_LABEl, "order and inforamtion page")
				&& isElementPresent(ADDINFO_NAME, "Name")) {
			type(ADDINFO_NAME, name, "Name field");
			type(ADDINFO_PHNMBR, phonembr, "Phone Number");
			type(ADDINFO_EMAIL, email, "Email");
		} else {
			reporter.failureReport("Verify the the Order and item information page",
					"Order and item information page is not displayed","");
		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void VerifyOrderPlaceByFields() throws Throwable {
		if (isElementPresent(NAME_FIELD, "Name") && isElementPresent(PHONE_FIELD, "Phone")
				&& isElementPresent(EMAIL_FIELD, "Email")) {
			reporter.SuccessReport("Verify Order Placed By fields on Cart : Ship, Bill & Pay : Place Requisition Page",
					"Order Placed By Name,Phone,Email Fields is Verfied","");
		} else {
			reporter.failureReport("Verify Order Placed By fields on Cart : Ship, Bill & Pay : Place Requisition Page",
					"Order Placed By Name,Phone,Email Fields is not Verfied","");
		}
	}

	/**
	 * 
	 * @param RefNumber
	 * @throws Throwable
	 */
	public void verifyOrderNumberExists(String RefNumber) throws Throwable {
		String OrdNum = getText(FINAL_ORDERNMBR, "Order Number");
		if (OrdNum.contains(RefNumber)) {
			reporter.SuccessReport("Get The Order Number on Aproval management Page", "Order Number Exist","");
		} else
			reporter.failureReport("Get The Order Number on Aproval management Page", "Order Number Not Exists","");

	}

	/**
	 * 
	 * @param option
	 * @throws Throwable
	 */
	public void selectTheGroupPaymentOptions(String option) throws Throwable {
		if (isElementPresent(getSelectGrpPaymentOptions(option), "payment group options")) {
			click(getSelectGrpPaymentOptions(option), "payment group options");
			click(LEFT_TO_RIGHT_ARROW, "left to right arrow");
			click(SAVE_CHANGES_BTN, "save changes button");
			if (isElementPresent(SHIPPING_PAYMENTS_SUCCESS_MSG, "Success message")) {
				reporter.SuccessReport("Verify success message",
						"Shipping payments options success message is displayed","");
			} else {
				reporter.failureReport("Verify success message", "Success message is not displayed","");
			}
		} else {
			// Do nothing
		}
	}

	/**
	 * Method is to check the payments method check box in approval path
	 * settings tab
	 * 
	 * @throws Throwable
	 */
	public void checkPaymentMethodCheckBox() throws Throwable {
		if (isCheckBoxSelected(PAYMENT_METHOD_CHK_BOX)) {
			LOG.info("Checkbox already checked");
		} else {
			click(PAYMENT_METHOD_CHK_BOX, "Payment method check box");
			click(APPROVAL_PATH_SETNGS_SAVE_BTN, "save changes button");
			isElementPresent(APPROVAL_PATH_SUCCESS_MSG, "success message", true);
		}
	}
	
	/**
	 * This method is to get the quantity for bundle of products in the cart screen
	 * @return
	 */
	public List<String> getCartProductQuantityForBundleOfProducts() {
		List<WebElement> myList = driver.findElements(CartObj.CART_PROD_QTY);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 1; i < myList.size(); i++) {
			all_elements_text.add(myList.get(i).getAttribute("value"));
		}
		return  all_elements_text;
	}
	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyApprovalManagmentandClickUpdate() throws Throwable {
		if (isElementPresent(APPROVAL_MNGMNT_HDR2, "ApprovalManagement Header")) {
			reporter.SuccessReport("Verify Approval Management  Page", "Approval Management Page Exist","");
		} else
			reporter.failureReport("Verify Approval Management  Page", "Approval Management Page does Not Exists","");
		if(click(UPDATE_BTN, "Update Button")){
			reporter.SuccessReport("Verify Approval Management  Page", "Requisition placed has been Approved.","");
		} else
			reporter.failureReport("Verify Approval Management  Page", "Requisition not Approved.","");
		}
	
	/**
	 * 
	 * @param quoteName
	 * @throws Throwable
	 */
	public void createQuoteandGenerateName(String quoteName) throws Throwable{		
		clickUntil(CartObj.SAVE_AS_QUOTE,QUOTE_NAME ,"Save as quote Link");
		type(QUOTE_NAME,quoteName, "Quote name");
		click(SAVE_AS_QUOTE_BTN, "save as quote button");
		if(isElementPresent(SAVE_QUOTE_MSG, "Success message")){
			 reporter.SuccessReport("Verify Success message", "Save as Quote - Successful message displayed","");
		 }else{
			 reporter.failureReport("Verify Success message ", "Save as Quote - Successful message not displayed ",""); 
		}
	}
	/**
	 * This method is to click on Remove warranty link on cart page
	 * @throws Throwable
	 */
	
	public void clickRemoveWarrantyLink() throws Throwable{
		if(isElementPresent(REMOVE_WARRANTY_LINK, "Remove warranty link")){
			clickUntil(REMOVE_WARRANTY_LINK, ADD_WARRANTY_LINK,"Remove warranty ");
		}else{
			reporter.failureReport("Verify remove warranty link ", "Remove warranty link dose not exists", "", driver);
			
		}
	}
	
}
