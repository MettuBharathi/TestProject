package com.insight.ObjRepo;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class LineLevelInfoObj extends ActionEngine{

	// To get the contact email in line level info page
	 public static By getContactEmailLLInfo(String partNum){
		 return By.xpath("//p[contains(.,'Insight Part #: "+partNum+"')]/following::div[@class='row expanded is-collapse-child'][1]//input[@type='email']");
	 } 
	 
	// To get the contact name in line level info page
	 public static By getContactNameInLLInfo(String partNum){
		 return By.xpath("//p[contains(.,'Insight Part #: "+partNum+"')]/following::div[@class='row expanded is-collapse-child'][1]//input[@name='licenseInformation.CONTACT_NAME']");
	 }
	 
	  // To get the Quote name in line level info page
	 public static By getCutomerQuoteInLLInfo(String partNum){
		return By.xpath("//p[contains(.,'Insight Part #: "+partNum+"')]/following::div[@class='row expanded is-collapse-child'][1]//input[@name='licenseInformation.CUSTOMER_QUOTE_NO']");
	}
		 
		// To get the Contact number in line level info page
	 public static By getContactPhoneInLLInfo(String partNum){
		return By.xpath("//p[contains(.,'Insight Part #: "+partNum+"')]/following::div[@class='row expanded is-collapse-child'][1]//input[@name='licenseInformation.CONTACT_PHONE']");
	}
		 
	 public static By getCopyToAllLink(String partNum){
		 return By.xpath("//p[contains(.,'Insight Part #: "+partNum+"')]/following::div[@class='row expanded is-collapse-child'][1]//a[contains(text(),'Copy to all')]");
	 }
	 
	 public static By getClearLink(String partNum){
		 return By.xpath("//p[contains(.,'Insight Part #: "+partNum+"')]/following::div[@class='row expanded is-collapse-child'][1]//a[contains(text(),'Clear')]");
	 }
	 
	 public static By getLineLevelOptionalLink(String partNum){
		 return By.xpath("//p[contains(.,'Insight Part #: "+partNum+"')]/following::div[@class='row expanded is-collapse-child'][1]//h4[@class='line-level__heading line-level__heading--optional']//a");
	 }
	 
	 public static By getgetRP_LNL_TxtByPartNum(String partNum){
		 return By.xpath("//p[contains(.,'Insight Part #: "+partNum+"')]/following::div[@class='row expanded is-collapse-child'][1]//span[contains(text(),'RP_LNL_Txt')]/following-sibling::input");
	 }
	 public static By EMAIL_REQUIREMENT_ERROR_MSG=By.xpath("//div[@class='form__field-msg form__field-msg--error'][contains(text(),'Contact email is required.')]");
	 public static By PRORATED_PRICE_MSG_SEARCH_RESULTS=By.xpath("//div[@class='noticeAlert'][contains(text(),' The price displayed will be prorated in the cart based on the remaining agreement period.')]");
	 
	 /** Method is to get the RP_HDL_Txt text in PO page**/
	 public static By getRP_HDL_TxtInPlaceOrderPage(String text){
		return By.xpath("//section[@class='section additional-order-information']//label//span[contains(text(),'RP_HDL_Txt')]/following::p[contains(text(),'"+text+"')]");
	 }
	 /** Method is to get the RP_LNL_Txt text in PO page**/
	 public static By getRP_LNL_TxtInPlaceOrderpage(String text){
		 return By.xpath("//div[@class='line-level']//label[contains(text(),'RP_LNL_Txt:')]//p[contains(text(),'"+text+"')]");
	 }
	
	 /** Method is to get the RP_HDL_Txt text on receipt page*/
	 public static By getRP_HDL_TxtOnReceiptPage(String text){
		return By.xpath("//label[contains(text(),'RP_HDL_Txt')]//p[contains(text(),'"+text+"')]");
	 }
	 /** Method is to get the RP_LNL_Txt text on receipt page**/
	 public static By getRP_LNL_TxtOnReceiptPage(String text){
		 return By.xpath("//label[contains(text(),'RP_LNL_Txt:')]//p[contains(text(),'"+text+"')]");
	 }
	 
	 public static By getCountryOfUsageDD(String partNum){
		 return By.xpath("//p[contains(.,'Insight Part #: "+partNum+"')]/following::div[@class='row expanded is-collapse-child'][1]//select[@class='form__field form__select ']");
	 }
	 public static By WELCOME_INSIGHT_POPUP=By.xpath("//div//h2[contains(text(),'Welcome to Insight Beta!')]");
	 // Shipping address
	 public static By ADDRESS_VALIDATION_SAVE_BTN=By.xpath("//button[@class='button expanded'][contains(text(),'Save address')]");
	 
	 public static By SAME_AS_SHIPPING_ADD_CHECKBOX=By.xpath("//input[@class='form__field form__input--checkbox']");
	
	 // Place order
	 public static By CONTRACT_SPECIFIC_INFO_LABEL=By.xpath("//section[@class='line-level__section']//h4[contains(text(),'Contract specific information')]");
	 public static By EDIT_LINE_LEVEL_INFO=By.xpath("//span[@class='cart-item__info'][contains(text(),' Edit line level information')]");
	 
	 // smart Tracker
	 public static By RP_LNL_Lst_DD=By.xpath("//select[@class='form__field form__select ']");
	 public static By RP_LNL_DATE_PICKER=By.xpath("//input[@class='form__field date-picker__input']");
	 public static By RP_LNL_DATE_TODAY_DATE=By.xpath("//div[@class='react-datepicker__day react-datepicker__day--keyboard-selected react-datepicker__day--today']");
	 
	 public static By getRP_LNL_TodayDate(String partNum){
	 	return By.xpath("//p[contains(.,'Insight Part #: "+partNum+"')]/following::div[@class='row expanded is-collapse-child'][1]//input[@class='form__field date-picker__input']");
	 }
		
	 public static By getTotalInputFieldsInLLI(String partNum){
		 return By.xpath("//p[contains(.,'Insight Part #: "+partNum+"')]/following::div[@class='row expanded is-collapse-child'][1]//div[@class='line-level__section']//label");
		 
	 }
	 
	 // Line level info label
	 public static By LINE_LEVEL_INO_LABEL=By.xpath("//section[@class='section line-level-information']//h3[contains(text(),'Line level information')]");
	 public static By DiversityPartner(String partNum){
			 return By.xpath("//p[contains(.,'Insight Part #: "+partNum+"')]/following::div[@class='row expanded is-collapse-child'][1]//select[@name='contractSpecificInformation.diversityPartner']");
	 }
	 
	 // Split into individual lines
	 public static By SPLIT_INTO_INDIVIDUAL_LINES_LINK=By.xpath("//a[@class='line-level__split-link'][contains(text(),'Split into individual lines')]");
	 public static By TWO_LINE_ITEMS_LABEL=By.xpath("//span[contains(text(),' line items require information')]");
	 
	 public static By ORDER_DETAILS_ITEMS=By.xpath("//div[@class='item-body item-body--contract']");
	 
	 
	 // Product bundles in cart
	 public static By getBundlesAdded(String bundle){
		 return By.xpath("//div[@class='cart__item-bundle'][contains(.,'Insight Part #: "+bundle+"')]");
	 }
	 
	 public static By COUNTRY=By.xpath("//select[@id='country']");
}
