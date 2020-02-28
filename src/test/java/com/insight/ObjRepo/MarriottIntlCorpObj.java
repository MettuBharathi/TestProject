package com.insight.ObjRepo;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class MarriottIntlCorpObj extends ActionEngine {

	public static By SEARCHFIELD = By.xpath("//div[@id='acctSearch-box']//i/following-sibling::input[@type='text']");
	public static By SEARCHBUTTON = By.xpath("//button[@id='acctFav-search-submit']");

	public static By SEARCHRESULT(String Account) {
		return By.xpath("//div[@id='searchAcctResults']//tr/td[contains(text(),'" + Account + "')]");// BOSNF
	}

	public static By SWITCHTOACCOUNT(String Account) {
		return By.xpath("//div[@id='searchAcctResults']//tr/td[contains(text(),'" + Account
				+ "')]/parent::tr//div/a[contains(text(),'Switch Account')]");
	}

	public static By SWITCHTOACC_POPUP = By
			.xpath("//div[@class='c-modal__container']//h2[contains(text(),'change your account')]");
	public static By CONTINUEBUTTON_SWITCHTOACC = By.xpath("//button[contains(text(),'Continue') and @type='button']");
	public static By WELCOMTO_E_PROCURMNTPAGE = By.xpath("//div[@id='welcomePageHeading']");
	public static By COMPANYSTANDARDS_PAGELINK = By.xpath("//form/input[@class='button' and @name='submit']");

	public static By getcompanystandardsproductgroup(String productgrp) {
		return By.xpath("//div//td[contains(text(),'" + productgrp + "')]");
	}

	public static By VERIFYPART = By.xpath("//form/input[@class='button' and @name='submit']");

	public static By VERIFYPART(String partNum) {
		return By.xpath("//a[contains(@href,'" + partNum + "')]");
	}

	public static By getQuantityOfPart(String Partnum) {
		return By.xpath("//input[@name='selectedquantity_"+Partnum+"']");
	}

	public static By ADDCHECKBOX(String partNum) {
		return By.xpath("//a[contains(@href,'" + partNum
				+ "')]/parent::div/parent::td/following-sibling::td/input[@type='checkbox']");
	}

	public static By ADDRADIOBUTTON(String partNum) {
		return By.xpath("//a[contains(@href,'" + partNum
				+ "')]/parent::div//parent::td/following-sibling::td/input[@type='radio']");
	}

	public static By CLOSE_ICON = By.xpath("//span[@class='insightLightBoxClose frameworkIcons Deep_Close_Icon']");
	public static By ADDTOCARTDAILOGUE_BOX = By.xpath("//div[@id='addToCartDialog']");

	public static By VERIFYPRICE(String price) {
		return By.xpath("//span[@class='yourPrice' and contains(text(),'" + price + "')]");
	}

	public static By ITMEPRICEINCART(String price) {
		return By.xpath("//span[@class='iw-currency__amount' and contains(text(),'" + price + "')]");
	}

	public static By ITEMQUNTITYINCART(String price) {
		return By.xpath("//span[@class='iw-currency__amount' and contains(text(),'" + price
				+ "')]/parent::span/parent::div/following-sibling::div//input[@aria-label='Item quantity']");
	}

	public static By SAVEASQUOTEPAGE = By.xpath("//div[@id='savequotetext']");
	public static By SAVEASQUOTE_NAME = By.xpath("//input[@id='quotenameinput']");
	public static By SAVEASQUOTE_NOTES = By.xpath("//div[@id='notesinputdiv']/textarea[@id='notesinput']");
	public static By ATTENTION_TEXTFIELD = By.xpath("//input[@class='shippingInputText']");
	public static By SAVEASQUOTE_BUTTON = By.xpath("//span[@id='saveQuoteButton']");
	public static By SAVEASQUOTE_SUCCESPAGE = By.xpath("//div[@id='savequotesuccesstext']");
	public static By REFNUM_SAVEASQUTESSUCCESSPG = By.xpath("//div[@id='quotenumval']/span");
	public static By QUOTENAME_SAVEASQUTESSUCCESSPG = By.xpath("//div[@id='quotenameval']/span");
	public static By QUOTENOTES_SAVEASQUTESSUCCESSPG = By.xpath("//div[@id='quotenotedisplaydiv']");
	// switchSbp
	public static By SHIPPINGADRESS_COMPANY = By.xpath(
			"//section[@class='section shipping-address']//label[contains(text(),'Company:')]/following-sibling::p[1]");
	public static By SHIPPINGADDRESS_BR = By.xpath(
			"//section[@class='section shipping-address']//label[contains(text(),'Address:')]/following-sibling::p/br");
	public static By SHIPPINGADDRESS = By.xpath(
			"//section[@class='section shipping-address']//label[contains(text(),'Address:')]/following-sibling::p");
	public static By WEBGRP_DROPDOWN = By.xpath("//div[@class='c-header__middle']//button[@id='webGroupDropdown']");
	// MI Global Software Catalog
	public static By END_USER_EMAIL = By
			.xpath("//span[contains(text(),'End User Email (Confirmation Notification)')]//following-sibling::input");
	public static By COUNTRYDROPDOWN = By.xpath("//span[contains(text(),'Country of Use')]//following-sibling::select");
	public static By ENDUSE_UNIT = By
			.xpath("//span[contains(text(),'End User - Div/Unit/Dept')]//following-sibling::input");
	public static By SWITCH_WEBGRP(String webgrp) {
		return By.xpath("//button[contains(text(),'" + webgrp + "') and  @type='button']");
	}

	public static By WEBGRPCHANGE_POPUP = By.xpath("//h2[contains(text(),'You are about to change your web group')]");
	public static By CONTINUEBUTTON_WEBGRPCHANGE = By.xpath("//button[contains(text(),'Continue') and @type='button']");

	public static By SWITCHED_WEBGRPONDASHBOARD(String webgrp) {
		return By.xpath(
				"//span[contains(text(),'Web Group – " + webgrp + "')]/ancestor::div[@class='c-header__middle']");
	}

	public static By VERIFYQUANTITY(String qty) {

		return By.xpath("//span[@class='yourPrice' and contains(text(),'" + qty + "')]");// 1

	}

	public static By BRAND_IDENTIFIER = By.xpath("//span[contains(text(),'Brand Identifier')]//following-sibling::input");
	public static By REQUESTOR_EMAIL=By.xpath("//span[contains(text(),'Requester Email')]//following-sibling::input");
    public static By PCUSER_NAME=By.xpath("//span[contains(text(),'PC User Name')]//following-sibling::input");
    public static By NOTES=By.xpath("//span[contains(text(),'Notes')]//following-sibling::input");
    public static By PCRLAPTOPDROPDOWN=By.xpath("//select[@class='form__field form__select ']");
    public static By ENDUSER_MARSHA = By.xpath("//span[contains(text(),'End User MARSHA')]//following-sibling::input");
    public static By Name = By.xpath("//input[@name='userContact.name']");
    public static By Phone = By.xpath("//input[@name='userContact.phone']");
    public static By Email = By.xpath("//input[@name='userContact.email']");
    //place order page
    public static By verifybrandidentifiertext(String Brand_Identifier){
    return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'Brand Identifier')]/parent::label/following-sibling::p[contains(text(),'"+Brand_Identifier+"')]");
    }
    public static By verifyordercontainapcornottext(String PC_Laptop){
        return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'contain a PC or Laptop')]/parent::label/following-sibling::p[contains(text(),'"+PC_Laptop+"')]");
        }
    public static By verifyendusermarshatext(String PC_End_User_MARSHA){
        return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'End User MARSHA')]/parent::label/following-sibling::p[contains(text(),'"+PC_End_User_MARSHA+"')]");
        }
    public static By verifypcusernametext(String PC_User_Name){
        return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'PC User Name')]/parent::label/following-sibling::p[contains(text(),'"+PC_User_Name+"')]");
        }
    public static By verifyrequestoremailtext(String Requester_Email){
        return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'Requester Email')]/parent::label/following-sibling::p[contains(text(),'"+Requester_Email+"')]");
        }
    public static By verifynotestext(String Notes){
        return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'Notes')]/parent::label/following-sibling::p[contains(text(),'"+Notes+"')]");
        }
    public static By shippingaddressattentiontext(String Ship_Attention){
        return By.xpath("//section[@class='section shipping-address']//span/label[contains(text(),'Attention:')]/following-sibling::p[contains(text(),'"+Ship_Attention+"')]");
        }
    public static By Billingaddressattentiontext(String Bill_Attention){
        return By.xpath("//section[@class='section billing-address']//span/label[contains(text(),'Attention:')]/following-sibling::p[contains(text(),'"+Bill_Attention+"')]");
        }
    public static By VIEW_CART = By.xpath("//a[@title='View Cart >>']");
	public static By PRODUCT_GROUPS = By.xpath("//div[@class='csProductGroupDiv'][3]//table//tr//td");
    public static By PRODUCTQTY_LENOVO = By.xpath("//input [@name='selectedquantity_20LAS0E900-MI']");
	public static By getCompanyStandardsProductGroup(String productGroup, String productName) {
		return By.xpath("//div//td[contains(text(),'" + productGroup
				+ "')]/following::div[@class='csProductGroupMemberDiv'][7]//td//a[contains(text(),'" + productName
				+ "')]");
	}

	public static By QUICK_SHOP_ITEM_FIELD = By
			.xpath("//div[@class='hide-for-small-only']//input[@class='quick-shop__materialID no-margin-bot']");
	public static By ADD_BUTTON = By
			.xpath("//div[@class='hide-for-small-only']//button[@class='button small expanded no-margin-bot']");
	public static By REQUESTER_NAME = By.xpath("//span[contains(text(),'Requester Name')]//following-sibling::select");
	public static By PEOPLE_SOFT_NUMBER = By.xpath("//span[contains(text(),'End User - PeopleSoft Number')]//following-sibling::input");
	public static By CUSTOMER_REFERENCE_NUMBER = By.xpath("//span[contains(text(),'Customer Reference Number')]//following-sibling::input");
	public static By PC_ENDUSER = By.xpath("//span[contains(text(),'PC User Name(s)-If shared or multiple PCs on order')]//following-sibling::input");
	public static By END_USER_DIV_UNIT = By.xpath("//span[contains(text(),'End User - Div/Unit/Dept')]//following-sibling::input");
	public static By APPROVING_MANGER_EMAIL = By.xpath("//span[contains(text(),'Approving Manager Email')]//following-sibling::input");

	public static By NON_IRFAPC = By.xpath("//span[contains(text(),'Select Yes if Field Non-IRFA PC on this order')]//following-sibling::select");

	public static By CONTINUE = By.xpath("//button[@class='button expanded section__button no-margin-bot']");

	public static By SHIP_ATTENTION = By.xpath("//input[@name='existingAddressAttention.attentionLine']");

	public static By SHIP_PHONE = By.xpath("//input[@name='existingAddressAttention.phone']");

	public static By SHIP_LOCATION_ID = By.xpath("//input[@name='existingAddressAttention.address3']");

	public static By BILLING_ATTENTION = By.xpath("//input[@name='existingAddressAttention.attentionLine']");

	public static By BILLING_PHONE = By.xpath("//input[@name='existingAddressAttention.phone']");

	public static By BILLING_LOCATION = By.xpath("//input[@name='existingAddressAttention.address3']");

	public static By VERITY_ADDITIONAL_INFO = By
			.xpath("//div[@class='row expanded is-collapse-child'][1]//div[" + i + "]/p]");

	public static By VERIFY_SHIPPING_INFO = By
			.xpath("//Section[@class='section shipping-address']//div[@class='columns small-12 medium-6'][2]/span[" + i
					+ "]/p");

	public static By VERIFY_BILLING_INFO = By
			.xpath("//Section[@class='section billing-address']//div[@class='columns small-12 medium-6'][2]/span[" + i
					+ "]/p");

	public static By PAYMENT_INFO = By.xpath(
			"//Section[@class='section payment-info']//div[@class='row expanded is-collapse-child'][1]/div/label/p");
	
	public static By NAME = By.xpath("//input[@name='userContact.name']");
	
	public static By PHONE = By.xpath("//input[@name='userContact.phone']");
	public static By EMAIL = By.xpath("//input[@name='userContact.email']");
	/* ******************************************************************************************************************
	 * >>>>>>>>>>>>>>>> LOCATORS TO SELCT PRODUCT GROUP AND PRODUCT FROM GROUP ON ACCOUNT TOOLS SCREEN <<<<<<<<<<<<<<<<<<
	 * ******************************************************************************************************************/
	public static By getCompanyStandardsProductGroupforField(String productGroup,String productName){
		return By.xpath("//div//td[contains(text(),'"+productGroup+"')]/following::div[@class='csProductGroupMemberDiv']//td//a/b[contains(text(),'"+productName+"')]");
	}
	
	public static By ADDPRODUCT = By.xpath("//input[@value='KV3-00367-SLP']");
	public static By getCompanyStandardsProductGroupwithbtext(String productGroup,String productName,String FieldOnly){
		return By.xpath("//div//td[contains(text(),'"+productGroup+"')]/following::div[@class='csProductGroupMemberDiv']//td//a[contains(text(),'"+productName+"')]//following-sibling::b[contains(text(),'"+FieldOnly+"')]");
	}

public static By BRAND_IDENTIFIERSELECT = By.xpath("//span[contains(text(),'Brand Identifier')]//following-sibling::select");
public static By verifyRequisitortext(String Requester_Name){
    return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'Requester Name')]/parent::label/following-sibling::p[contains(text(),'"+Requester_Name+"')]");
    }
public static By verifyEndusertext(String End_User_People){
    return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'End User - People')]/parent::label/following-sibling::p[contains(text(),'"+End_User_People+"')]");
    }
public static By Costumerrefernce(String Customer_Reference){
    return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'Customer Reference')]/parent::label/following-sibling::p[contains(text(),'"+Customer_Reference+"')]");
    }
public static By Approvingmanagermeail(String Approving_Manager){
    return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'Approving Manager Email')]/parent::label/following-sibling::p[contains(text(),'"+Approving_Manager+"')]");
    } 
public static By Enduseremail(String End_User_Email){
    return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'End User Email')]/parent::label/following-sibling::p[contains(text(),'"+End_User_Email+"')]");
    }
public static By nonirfapc(String Non_IRFA_PC){
    return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'Select Yes if Field Non-IRFA PC')]/parent::label/following-sibling::p[contains(text(),'"+Non_IRFA_PC+"')]");
    }
public static By enduserdiv(String PC_End_User_Div_Unit_Dept){
    return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'End User - Div/Unit/Dept')]/parent::label/following-sibling::p[contains(text(),'"+PC_End_User_Div_Unit_Dept+"')]");
    }
public static By DOMAIN = By.xpath("//span[contains(text(),'Domain')]//following-sibling::select");
public static By ENDUSERMARSHA = By.xpath("//span[contains(text(),'End User MARSHA')]//following-sibling::select");
public static By ENDUSERDIVSELECT= By.xpath("//span[contains(text(),'End User - Div/Unit/Dept')]//following-sibling::select");
public static By ENDUSEREID = By.xpath("//span[contains(text(),'End User EID')]//following-sibling::input");
public static By ENDUSERNAME = By.xpath("//span[contains(text(),'End User Name')]//following-sibling::input");
public static By verifydomain(String Domain){
    return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'Domain')]/parent::label/following-sibling::p[contains(text(),'"+Domain+"')]");
    }
public static By verifyEnduserEid(String End_User_Eid){
    return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'End User EID')]/parent::label/following-sibling::p[contains(text(),'"+End_User_Eid+"')]");
    }
public static By verifyEndusername(String End_User_Name){
    return By.xpath("//section[@class='section additional-order-information']//span[contains(text(),'End User Name')]/parent::label/following-sibling::p[contains(text(),'"+End_User_Name+"')]");
    }
public static By Shipinfoattention(String Attention){
    return By.xpath("//section[@class='section shipping-address']//label[contains(text(),'Attention')]/following-sibling::p[contains(text(),'"+Attention+"')]");
    }
public static By Billinfoattention(String Attention){
    return By.xpath("//section[@class='section billing-address']//label[contains(text(),'Attention')]/following-sibling::p[contains(text(),'"+Attention+"')]");
    }
}
