package com.insight.ObjRepo;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class OrderObj extends ActionEngine{

	
	public static By CONTINUE_TO_CHECKOUT=By.xpath("//a[@class='button primary medium'][contains(text(),'Continue to Checkout')]");
	
	// warranty pop-up locators
	public static By ADD_WARRANTY_LINK=By.xpath("//span[@class='cart__table-col cart-item__add-warranty']");
	public static By WARRANTY_ITEMS_RADIO_BTN=By.xpath("//ul[@class='warranties-container__items-list']//li//div//input");
	public static By warrantyItemsRadioButton(String partNumber) {
		return By.xpath("//p[contains(.,'"+partNumber+"')]//ancestor::div[@class='row warranties-container__item-row']//input");
	}
	public static By ADD_TO_CART_IN_WARRANTY_POPUP=By.xpath("//button[@class='button expanded no-margin-bot' and contains(text(),'Add to cart')]");
	public static By FIRST_WARRANTY_ITEM=By.xpath("//li[@class='warranties-container__item'][1]//div//label[@class='warranties-container__item-label']");
	public static By warrentyItemDescription(String partNumber) {
		return By.xpath("//li[@class='warranties-container__item']//div//label[@class='warranties-container__item-label' and @for='"+partNumber+"']");
	}
	public static By WARRANTY_ITEM_DESC_ON_CART_SCREEN=By.xpath("//p[@class='selected-warranty__item-desc']");
	public static By warrentyItemDescOnCartScreen(String partNumber) {
		return By.xpath("//p[text()='"+partNumber+"']//ancestor::div[@class='columns medium-flex-child-grow cart__table-col--item']//p[@class='selected-warranty__item-desc']");
	}
	public static By REMOVE_WARRANTY_LINK=By.xpath("//div[@class='selected-warranty__item-actions text-left hide-for-print']//a[contains(text(),'Remove')]");
	public static By PROCEED_TO_CHECKOUT=By.xpath("//section[@class='cart cart']/following::div[@class='cart-summary-container']//button[contains(text(),'Proceed to checkout')]");
	public static By WARRANTY_LABEL_ON_PLACE_ORDER=By.xpath("//div[@class='cart-item__warranty']//h5[contains(text(),'Warranty')]");
	
	// ORDER & ITEM INFORMATION  >>> Additional information
	public static By ORDER_ITEM_INFO_LABEl=By.xpath("//h1[@class='shopping-cart__header-title'][contains(text(),'Order and item information')]");
	public static By RP_HDL_Txt=By.xpath("//label//span[contains(text(),'RP_HDL_Txt')]/following::input");
	public static By WG_HDL_Txt=By.xpath("//label//span[contains(text(),'WG_HDL_Txt')]/following::input");
	public static By ADDITIONAL_NOTES=By.xpath("//textarea[@name='additionalOrderInformation.orderNotes']");
	public static By INVOICE_NOTES=By.xpath("//textarea[@name='additionalOrderInformation.invoiceNotes']");
	public static By CONTINUE_BTN=By.xpath("//button[@type='submit']");
	public static By ADDITIONAL_INFO_NAME=By.xpath("//input[@name='warrantyContact.name']");
	public static By PMO=By.xpath("//span[@class='form__label__text'][contains(text(),'PMO #')] ");
	
	public static By REVIEW_ORDER_UPLOADED_FILE_NAME=By.xpath("//span[@class='form__label__text'][contains(text(),'File')]/following::p[contains(text(),'UploadFile.xls')]");
	
	// ORDER & ITEM INFORMATION  >>> Line level Information
	public static By LINE_LEVEL_INFO=By.xpath("//div[@class='cart__item']//a[contains(text(),'Line level information (optional)')]");
	public static By RP_LNL_Txt=By.xpath("//label//span[contains(text(),'RP_LNL_Txt')]/following-sibling::input");
	public static By WG_LNL_Txt=By.xpath("//label//span[contains(text(),'WG_LNL_Txt')]/following-sibling::input");
	public static By LLI_CONTINUE_BTN=By.xpath("//button[@class='button expanded cart-summary__button']");
	public static By LINE_LEVEL_INFO_LINK_BUNDLE=By.xpath("//div[@id='lineLevelForm____3__']//a//span");
	public static By SMART_TRACKER_LABEL=By.xpath("//div[@class='line-level__section']//div//h5[contains(text(),'SmartTracker')]");
	public static By REPORTING_FIELD_4  =By.xpath("//input[@name='contractSpecificInformation.REPORTING FIELD 4']");
	public static By REPORTING_FIELD_5  =By.xpath("//input[@name='contractSpecificInformation.REPORTING FIELD 5']");
	public static By REPORTING_FIELD_6  =By.xpath("//input[@name='contractSpecificInformation.REPORTING FIELD 6']");

	
	
	// Shipping/Billing  >>  payment info 
	public static By CARD_NUMBER_TEXTBX=By.xpath("//input[@name='cardInfo.storedCardToken']");
	public static By CARD_NAME_TEXTBOX=By.xpath("//input[@name='cardInfo.storedCardHolderName']");
	public static By EXPIRATION_MONTH=By.xpath("//select[@name='cardInfo.storedCardExpMonth']");
	public static By EXPIRATION_YEAR=By.xpath("//select[@name='cardInfo.storedCardExpYear']");
	public static By PO_NUMBER=By.xpath("//input[@name='POSection.poNumber']");
	public static By REVIEW_ORDER_BTN=By.xpath("//button[@type='submit' and contains(text(),'Review order')]");
	public static By DEFAULT_SAVED_CARD_DETAILS=By.xpath("//div[@class='iw-set-as-my-default iw-set-as-my-default__show edit-payment-card']");
	public static By PAYMENT_METHOD_SELECTION=By.xpath("//div[@class='Select-menu']//div[contains(text(),'Credit card')]");
	public static By PAYMENT_METHOD_DD=By.xpath("//div[@class='Select Select__Payment-method Select--single is-searchable has-value']");
	public static By PROD_DESC_PLACE_ORDER_PAGE=By.xpath("//h4[@class='cart__item-heading'] ");
	public static By PAYMENT_METHOD_TERM= By.xpath("//span[@class='Select-value-label'][contains(text(),'Terms')]"); 
	public static By PAYMENT_INFO_CREDIT_CARD_DEFAULT=By.xpath("//span[@class='Select-value-label'][contains(text(),'Credit card')]");
	
	public static By paymentSelection(String paymentType) {
		return By.xpath("//div[@class='Select-menu']//div[contains(text(),'"+paymentType+"')]");
	}
	// Add new card in payment Info
	public static By ADD_NEW_CARD=By.xpath("//section[@class='section payment-info']//a[contains(text(),'Add new')]");
	
	public By getCardNumberEndingvalue(String endingValue){
		return By.xpath("//section[@class='section payment-info']//p[contains(.,' ending in "+endingValue+"')]");
	}
	
	// ORDER & ITEM INFORMATION  >>> Shipping Options
	public static By SHIPPING_CARRIER_REQUIRED_MSG=By.xpath("//span[@class='columns iw-message__text'][contains(text(),'A shipping carrier is required')]");
	public static By SELECT_CARRIER_DD=By.xpath("//div[@class='Select-value']//span[contains(text(),'No carrier preference')]");
	public static By SELECT_UPS_CARRIER=By.xpath("//div[@class='Select-menu-outer']//div[@class='Select-option'][contains(text(),'UPS')]");
	public static By CARRIER_PRICE_RADIO_BTN=By.xpath("//input[@type='radio']");
	public static By UPLOAD_FILE=By.xpath("//input[@id='fileUpload']/following::div//label[@for='fileUpload']");
	public static By SHIPPING_ADDRESS_COMPANY_NAME=By.xpath("//div//h3[contains(text(),'Shipping address')]/following::div[1]//label[@class='form__label--readonly'][contains(text(),'Company:')]//p");
	public static By SHIPPING_ADDRESS_IN_RECEIPT=By.xpath("//div//h3[contains(text(),'Shipping address')]/following::div[1]//label[@class='form__label--readonly'][contains(text(),'Address:')]//p");
	public static By BILLING_ADDRESS_COMPANY_NAME=By.xpath("//div//h3[contains(text(),'Billing address')]/following::div//label[@class='form__label--readonly'][contains(text(),'Company:')]//p");
	public static By  BILLING_ADDRESS_IN_RECEIPT=By.xpath("//div//h3[contains(text(),'Billing address')]/following::div[1]//label[@class='form__label--readonly'][contains(text(),'Address:')]//p");
	public static By SELCET_CARRIER=By.xpath("//div[@class='Select Select__shipping-carrier Select--single is-searchable has-value']");
	
	public static By selectCarrier(String carrier) {
		return By.xpath("//div[@class='Select-menu-outer']//div[@class='Select-option'][contains(text(),'"+carrier+"')]");
	}
	
	public static By shippingCarrierCharges(String shippingMethod) {
		return By.xpath("//strong[text()='"+shippingMethod+"']//parent::span//span[@class='iw-currency__amount']");
	}
	public static By shippingCarrier(String shippingMethod) {
		return By.xpath("//strong[text()='"+shippingMethod+"']");
	}
	// Place Order 
	public static By PLACE_ORDER_LABEL=By.xpath("//div[@class='shopping-cart__header']//h1[contains(text(),'Place order')]");
	public static By REVIEW_ORDER_ADDITIONAL_NOTES=By.xpath("//label//span[contains(text(),'Additional order notes')]/following::p[1]");
	public static By REVIEW_ORDER_INVOICE_NOTES=By.xpath("//label//span[contains(text(),'Invoice notes')]/following::p[1]");
	public static By REVIEW_ORDER_RP_LNL_LST=By.xpath("//label[contains(text(),'RP_LNL_Lst:')]/following::p[1]");
	public static By REVIEW_ORDER_WG_LNL_LST=By.xpath("//label[contains(text(),'WG_LNL_Lst:')]//p");
	
	public static By PLACE_ORDER_BTN=By.xpath("//section[@class='cart cart']/following::div[@class='cart-summary-container']//button[contains(text(),'Place order')]");
	public static By SUMMARY_TOTAL_AMOUNT=By.xpath("//section/following::div[@class='row is-collapse-child cart-summary__total']//span[@class='iw-currency__amount']");
	public static By DEFAULT_TAX_AMOUNT=By.xpath("//section/following::div[@class='row is-collapse-child cart-summary__tax']//span[@class='iw-currency__amount']");
	public static By ADDLICENCE_TAX_AMOUNT=By.xpath("//section/following::div[@class='row is-collapse-child cart-summary__tax']//span[@class='iw-currency__amount']");
	public static By EWR_FEE_AMOUNT=By.xpath("//section/following::div[@class='row is-collapse-child cart-summary__ewr']//span[@class='iw-currency__amount']");
	
	// Recipt
	public static By RECEIPT_LABEL=By.xpath("//div[@class='shopping-cart__header']//div//h1[contains(text(),'Receipt')]");
	public static By REFERENCE_ORDER_NUM=By.xpath("//label[contains(text(),'Reference number:')]//p");
	public static By TOTAL_AMOUNT=By.xpath("//label//span[@class='iw-currency']//span[2]");
	public static By DATE_ORDERED=By.xpath("//label[@class='form__label--readonly' and contains(text(),'Date ordered:')]//p");
	public static By ORDER_DETAILS_LINK=By.xpath("//a//strong[contains(text(),'Order details')]");
	public static By THANK_YOU_FOR_ORDER_MSG=By.xpath("//div[@id='CartContainer']//div/h3[contains(text(),'Thank you for your order')]");
	public static By PART_NUMBER_ON_RECEIPT_PAGE=By.xpath("//p[@class='cart__item-part cart__font-size--sm'][contains(text(),'Mfr Part #')]");
	
	// Receipt >> Payment info
	
	// Getting Expiration date
	public static By getExpirationdate(String month, String Year){
			return By.xpath("//label[contains(text(),'Expiration date:')]//p[contains(.,'"+month+" / "+Year+"')]");
	}
	
	// Name of the card in payment info receipt page
	public static By getNameOfTheCard(String name){
		return By.xpath("//label[contains(text(),'Name on card:')]//p[contains(text(),'"+name+"')]");
	}

	// Number of the card in payment info receipt page
	public static By getEndingCardNumberOnReceiptPage(String endingCardNumber) {
       return By.xpath("//label[contains(text(),'Card:')]//p[contains(.,'ending in "+endingCardNumber+"')]");
	}

	// PO number in payment info receipt page
	public static By getPONumberOnReceiptPage(String poNumebr){
		return By.xpath("//label[contains(text(),'P.O. number:')]//p[contains(.,'"+poNumebr+"')]");
	}
	
	// Type of card in receipt page
	public static By getTypeOfCard(String cardType){
		return By.xpath("//span[@class='show-for-sr'][contains(text(),'"+cardType+"')]");
	}
	
	// Edit Order details
	public By getEditOnReviewOrderPage(String sectionName){
		return By.xpath("//section//div//h3[contains(text(),'"+sectionName+"')]/following-sibling::a[contains(text(),'Edit')]");
		}
	
	// To get the section names on Ship bill page
	public static By getSectionNameShipBillPage(String link){
		   return By.xpath("//h3[@class='columns shrink section__header-title'][contains(text(),' "+ link +"')]");
		   }
	
	// ORDER & ITEM INFORMATION  >>> shipping address Information

				public static By SHIPPING_ADDRESS=By.xpath("//h3[@class='columns shrink section__header-title'][contains(text(),'Shipping address')]");
				public static By ADDLINK= By.xpath("//a[@class='section__body-action'][contains(text(),'Add new')]");
				public static By COMPANY_NAME_LBL = By.name("companyName");
				public static By STREETLINE_LBL = By.name("street1");
				public static By CITY_LBL = By.name("city");
				public static By ZIPCODE = By.name("zipCode");
				public static By SELECT_STATE= By.name("state");
				public static By SELECT_COUNTRY=By.name("country");
				public static By SHIPPINGADDRESS_CONTINUE_BTN=By.xpath("//button[@class='button expanded section__button no-margin-bot']");
				public static By ADDRESSVALIDATION_WINDOW_HDR= By.xpath("//h3[@class='iw-modal__heading']");
				public static By SAVEADDRESS_BTN = By.xpath("//div[@class='column small-6 medium-shrink']/button");
				
				public static By SHIPPINGOPTIONS_CONTINUE_BTN = By.xpath("//div[@class='column small-12 medium-shrink']/BUTTON");
				public static By headerlinkCheck(String link){
					return By.xpath("//h3[@class='columns shrink section__header-title'][contains(text(),'"+link+"')]");
				}
				
				public static By TAXDECLERATION_MESSAGE= By.xpath("//span[@class='column form__label--checkbox'][contains(text(),'tax exemption certificate')]");
				public static By TAX_CHECKBOX = By.xpath("//input[@id='applyTaxCertificate']");
				public static By RETURNTOCART_LNK = By.xpath("//a[@class='shopping-cart__header-link'][contains(text(),'Return to cart')]");
				public static By CART_LABL = By.xpath("//h1[@class='shopping-cart__header-title'][contains(text(),'Cart')]");
				public static By PLACEORDER_LABL = By.xpath("//h1[@class='shopping-cart__header-title'][contains(text(),'Place order')]");
				public static By itemPartNumber_Qty(String partNumber){
					return By.xpath("//div[@class='cart__item']//p[contains(.,'"+partNumber+"')]/following::div[2]//input[@aria-label='Item quantity']");
				}
				public static By item_Qty_Update(String partNumber){
					return By.xpath("//div[@class='cart__item']//p[contains(.,'Mfr Part #: VE248H')]/following::div[2]//input[@aria-label='Item quantity']/following-sibling::a");
				}
				public static By FAVORITES_LABL = By.xpath("//label[@class='form__label'][contains(text(),'Favorite addresses:')]");
				public static By PART_NUM = By.xpath("//div[@class='cart__item']//p[2]");
	

	
	public static By SHIPPING_ADDRESS_CONTINUE_BTN = By.xpath("//button[@class='button expanded section__button no-margin-bot']");
	public static By ADDRESS_VALIDATION_WINDOW_HDR = By.xpath("//h3[@class='iw-modal__heading']");
	public static By SAVE_ADDRESS_BTN = By.xpath("//div[@class='column small-6 medium-shrink']/button");
	public static By SHIPPING_OPTIONS_CONTINUE_BTN = By.xpath("//div[@class='column small-12 medium-shrink']/BUTTON");
	

	// Recent Orders page
	public static By RECENT_ORDERS_LABEL=By.xpath("//div//h1[@class='orders-search__title'][contains(text(),'Recent Orders')]");
	public static By ADVANCED_ORDER_SEARCH=By.xpath("//select[@class='form__field form__select ']");
	public static By ADVANCED_SEARCH_TEXT_BOX=By.xpath("//label[@class='form__label js-gtm-orders__search-by-value']//span/following::input[1]");
	
	
	// QUOTE CREATION
	
	public static By QUOTE_NAME=By.xpath("//input[@id='quotenameinput']");
	public static By TAX_IN_SAVE_QUOTE=By.xpath("//span[@id='taxCostSpan']");
	public static By SAVE_AS_QUOTE_BTN=By.id("saveQuoteButton");
	public static By SAVE_QUOTE_MSG=By.xpath("//div[@id='savequotesuccesstext']//h1[contains(.,'Save as Quote - Successful')]");
	public static By QUOTE_REFERENCE_NUMBER=By.xpath("//div[@id='quotenumval']//span");
	public static By BUNDLE_TABLE_IN_SAVE_QUOTE=By.xpath("//div[@id='shoppinCartDiv']//div[contains(text(),'BUNDLE-1')]");
	
	
	// QUOTE HISTORY
	
     public static By getSearchByQuoteHistoryDDOption(String searchBy_Text){
         return By.xpath("//div[@class='nice-select  open']//ul//li[contains(text(),'"+searchBy_Text+"')]");
     }
     
     public static By SEARCH_BY_DD=By.xpath("//label[contains(text(),'Search by')]/following-sibling::div//span[@class='current']");
     public static By SEARCH_NUMBER=By.xpath("//input[@id='quoQuickInputNumber']");
	 public static By SEARCH_BTN=By.xpath("//input[@id='quickSearch']");
	 public static By QUOTE_NUMBER_HISTORY_PAGE=By.xpath("//table[@id='quoteSearch']//td//span/following-sibling::a");
	 public static By QUOTE_DETAILS_PAGE_LABEL=By.xpath("//header//h2[contains(text(),'Quote Details')]");
     public static By CONVERT_QUOTE_BTN=By.xpath("//p[@id='convertQuote']//a");
	 public static By EDIT_QUOTE=By.xpath("//a[@class='icon edit has-tip tip-top']");
	 
	 
	 // Approval Management
	 
	// Method to get the Approval management tabs.
	 public static By getApprovalmanagementtabs(String links){
		 return By.xpath("//ul[@id='tab_ul']//li//a[contains(text(),'"+links+"')]");
	 }
	
	// To get the edit link of a Requestor Group
	 public static By getRequesterGroupNameEditLink(String groupName){
		return By.xpath("//tr//td[@id='reqname'][contains(text(), '"+groupName+"')]/following-sibling::td//a[contains(text(),'Edit')]");
	 
	 }
	
	 // To click on the Requester Group Name Screen Tabs
	 public static By getRequesterGroupNameScreenEditTabs(String tabName){
		 return By.xpath("//div[@id='reqgrp-edit-leftNav']//span//a[contains(text(),'"+tabName+"')]");
	 }

	 public static By EXIST_PHNMBR=By.xpath("//input[@name='existingAddressAttention.phone']");
	 
	 public static By COPY_TOALL_LNK =By.xpath("//span[@class='vertical-separator']/following::a[contains(text(),'Copy to all')][1]");
		public static By REPORTINGFIELDS_CART =By.xpath("//h4[@class='line-level__subheading']/following::div/div/label[contains(text(),'REPORTING FIELD 4')]");
		public static By orderlinkInOrderHistory(String refNum){
			return By.xpath("//td[contains(text(),'"+refNum+"')]/preceding-sibling::td/div");
		}
			public static By REPORTINGFIELD4_ORDERHISTORY =By.xpath("//span[@class='line-item-info__label'][contains(text(),'REPORTING FIELD 4')]");
			public static By REPORTINGFIELD5_ORDERHISTORY =By.xpath("//span[@class='line-item-info__label'][contains(text(),'REPORTING FIELD 5')]");
			public static By REPORTINGFIELD6_ORDERHISTORY =By.xpath("//span[@class='line-item-info__label'][contains(text(),'REPORTING FIELD 6')]");
			public static By PLACE_REQUISITION_BTN =By.xpath("//section[@class='cart cart']/following::div[@class='cart-summary-container']/div/div[3]/div/div/div/button");
			public static By APPROVAL_MNGMNT_HDR_UPDATEPAGE =By.xpath("//div[@id='searchRequisition']/div/h1[contains(text(),'Approval Management')]");
			public static By APPROVAL_MNGMNT_HDR1 = By.xpath("//div[@id='denyMessage']/following::h1[contains(text(),'Approval Management')]");
			public static By ReferenceLink(String refNum){
				return By.xpath("//table[@id='reqSearch']/tbody/tr/td/a[contains(text(),'"+refNum+"')]");
			}
			public static By APPROVAL_MNGMNT_HDR2 =By.xpath("//div[@id='searchRequisition']/div/h1[contains(text(),'Approval Management')]");
			public static By UPDATE_BTN =By.xpath("//div[@id='rsUpdatebuttons']/a/span[contains(text(),'UPDATE')]");
			public static By UPDATE_BTN1= By.xpath("//div[@id='approverUpdateButton']/a/span[contains(text(),'UPDATE')]");
			public static By CONTINUE_BTNIN =By.xpath("//div[@class='buttons']/a/span[text()='CONTINUE']");
			public static By APPROVED_STATUS =By.xpath("//tr[@class='srApprovedDetails']/td[2]/div[1][text()='Approved Requisition']");
			public static By ADDINFO_NAME =By.xpath("//input[@name='userContact.name']");
			public static By ADDINFO_PHNMBR =By.xpath("//input[@name='userContact.phone']");
			public static By ADDINFO_EMAIL =By.xpath("//input[@name='userContact.email']");
			public static By tabNameinOrderDetails(String TabName){
				return By.xpath("//nav[@class='order-details__tabs hide-for-print']/a[contains(text(),'"+TabName+"')]");
			}
			public static By SMARTRAKER_HDR =By.xpath("//h4[@class='line-item-info__subheading'][contains(text(),'SmartTracker')]");
			public static By NAME_FIELD =By.xpath("//span[@class='form__label__text'][contains(text(),'Name')]");
			public static By PHONE_FIELD =By.xpath("//span[@class='form__label__text'][contains(text(),'Phone')]");
			public static By EMAIL_FIELD =By.xpath("//span[@class='form__label__text'][contains(text(),'Email')]");
			public static By NAME_FIELD_VERIFY_REQ =By.xpath("//div[@class='srAccountDetails']/div[4]");
			public static By PHONE_FIELD_VERIFY_REQ =By.xpath("//div[@class='srAccountDetails']/div[5]");
			public static By EMAIL_FIELD_VERIFY_REQ =By.xpath("//div[@class='srAccountDetails']/div[6]");
			public static By FINAL_ORDERNMBR =By.xpath("//div[@id='FinalApproverMessage1']");
			public static By REVIEW_REQUISITION_BTN=By.xpath("//button[@type='submit' and contains(text(),'Review requisition')]");

	 
	 // To get the selected group payment options in checkout settings
	 public static By getSelectGrpPaymentOptions(String option){
		 return By.xpath("//select[@id='GrpPaymntBoxFrom']//option[contains(text(),'"+option+"')]");
		 
	 } 
	 
	 public static By LEFT_TO_RIGHT_ARROW=By.xpath("//a[@id='chkSet_LtoR1']");
	 public static By SAVE_CHANGES_BTN=By.xpath("//a[@id='chkSetupdate']");
	 public static By SHIPPING_PAYMENTS_SUCCESS_MSG=By.xpath("//p[@class='successUpdated'][contains(text(),'Successfully updated shipping/payment options')]");
	 public static By PAYMENT_METHOD_CHK_BOX=By.xpath("//input[@id='ch-editPayMethod']");
	 public static By APPROVAL_PATH_SETNGS_SAVE_BTN=By.xpath("//a[@id='ApathSettngs_update']//span");
	 public static By APPROVAL_PATH_SUCCESS_MSG=By.xpath("//p[@class='successUpdated'][contains(text(),'Successfully updated approval path settings')]");
	 

}

