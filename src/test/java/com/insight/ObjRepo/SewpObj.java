package com.insight.ObjRepo;

import org.openqa.selenium.By;

public class SewpObj extends CommonObj{

	public static By PRINT = By.xpath("//*[@class='columns shopping-cart__share text-right']/a/span[contains(.,'Print')]");
	public static By MORE_PRICES=By.xpath("//div[@id='js-product-detail-pricing-target']//p[@class='prod-more-prices']//a[contains(text(),'More Prices Available')]");
	
	public static By CART = By.xpath("//div[@class='o-grid__item  o-grid__item--shrink  u-push-auto@desktop']/nav/ul/li[7]/a");
	public static By CONTINUE_TO_CHECKOUT = By.xpath("//*[text()='Continue to Checkout']");
	
	public static By CREATE_AN_ACCOUNT = By.xpath("//div[@class='o-grid__item  o-grid__item--shrink  u-push-auto@desktop']/nav/ul/li[3]/a");
	

	public static By ADD_TO_CART = By.xpath("//div[@class='columns small-7']/a");
	public static By ADD_TO_CARTS = By.xpath("//div[@class='columns small-12 medium-5']/a");
	
	
	public static By EMAIL_ADDRESS = By.xpath("//input[@type='text'][@id='email']");
	
	
	public static By SELECT_AN_ORGANISATION=By.xpath("//div[@class='medium-6 columns']/select[@id='organizationDropDown']//*[text()='CIVILIAN AGENCIES']");

	public static By selectFavouriteOrganisation(String Organization) {
		return By.xpath("//*[text()='"+Organization+"']");
	}
	public static By SELECT_AGENCY=By.xpath("//div[@class='medium-6 columns']/select[@id='agencyDropDown']//*[text()='DEPARTMENT OF TRANSPORTATION']");
	public static By selectAgency(String agency) {
		return By.xpath("//*[text()='"+agency+"']");
	}
	
	public static By SELECT_SUBAGENCY=By.xpath("//div[@class='medium-6 columns']/select[@id='subAgencyDropDown']//*[text()='FEDERAL HIGHWAY ADMIN']");
	public static By selectSubAgency(String SubAgency) {
		return By.xpath("//*[text()='"+SubAgency+"']");
	}
	
	public static By FIRST_NAME=By.xpath("//input[@id='firstName']");
	public static By LAST_NAME=By.xpath("//input[@id='lastName']");
	public static By PHONE_NUMBER=By.xpath("//input[@id='phoneNumber']");
	public static By BILLING_ACCOUNT_NAME=By.xpath("//input[@id='companyName']");
	public static By ADDRESS_ONE=By.xpath("//input[@id='addressOne']");
	public static By CITY=By.xpath("//input[@id='city']");	
	public static By SELECT_STATE=By.xpath("//div[@class='medium-6 columns']/select[@id='state']//*[text()='Texas']");


	public static By selectStates(String State) {
		return By.xpath("//*[text()='"+State+"']");
	}
	
	
	public static By SELECT_JOBTITLE=By.xpath("//div[@class='medium-6 columns']/select[@id='jobTitle']//*[text()='IT Engineer']");

	public static By selectJob(String title) {
		return By.xpath("//*[text()='"+title+"']");
	}
	
	public static By ZIP_CODE=By.xpath("//input[@id='zipCode']");
	public static By USER_NAME=By.xpath("//input[@id='userName']");
	public static By PWD=By.xpath("//input[@id='password']");
	public static By CRM_PWD=By.xpath("//input[@id='confirmPassword']");
	
	public static By CREATE=By.xpath("//a[@id='create']/span");
	
	
	public static By EMAIL_ERRORMSG=By.xpath("//input[@id='email']//following::div");
	
	public static By CREATE_AN_ACCOUNT_SEARCHPAGE = By.xpath("//div[@class='columns medium-6 small-12']//p//a[@class='button primary small']");
	public static By SAVEAND_CREATE_AN_ACCOUNT = By.xpath("//div[@class='small-12 columns']//a//span");
	public static By SEARCH = By.xpath("//input[@type='search']");
	//Report
	public static By REPORTING_FIELD_4  =By.xpath("//input[@name='contractSpecificInformation.REPORT TEST 4']");
	public static By REPORTING_FIELD_5  =By.xpath("//input[@name='contractSpecificInformation.REPORT TEST 5']");
	public static By REPORTING_FIELD_6  =By.xpath("//input[@name='contractSpecificInformation.REPORT TEST 6']");
	public static By LLI_CONTINUE_BTN=By.xpath("//button[@class='button expanded cart-summary__button']");
	public static By SHIPPINGADDRESS_CONTINUE_BTN=By.xpath("//div[@class='column small-12 medium-shrink']//button[@class='button expanded section__button no-margin-bot']");
	
	public static By orderlinkInOrderHistory(String refNum){
		return By.xpath("//td[contains(text(),'"+refNum+"')]/preceding-sibling::td/div/a");
	}
	 public static By getPartNuminOrderdetails= By.xpath(" //span[@class='item-details__part']//span[@class='nowrap item-details__part-number'][1]");

	 public static By NUMBER_PICKER_IN_PRODUCTQA_DISPLAY_PAGE = By.xpath("//input[@id='qty-3']/parent::div/button[@class='number-picker-up ion-arrow-up-b']"); 
	//SWEP 
			public static By SEARCH_PRODUCT_SWEP=By.xpath("//input[@type= 'search' and @id='js-custom-search-field']");
			public static By MFR_SWEP=By.xpath("//table[@class='product-specs top-horizontal']/tbody/tr/td[2]");
			public static By INSIGHT_SWEP=By.xpath("//table[@class='product-specs top-horizontal']/tbody/tr/td[1]");
			public static By SWEP_PRICES =By.xpath("//div[@class='cart-wrapper']/p[@class='sewp-prices']");
			public static By MORE_PRICES_AVAIL =By.xpath("//div[@id='js-product-detail-target']//p//a[text()='More Prices Available']");
			 public static By QUANTITY= By.xpath("//input[@id='qty-3']");

			 public static By SEWPPART=By.xpath("//table[@class='product-specs top-horizontal']/tbody/tr/td[2]");
	
}
    

