package com.insight.ObjRepo;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class OrderHistoryObj extends ActionEngine{
	public static By RECENT_ORDERS=By.xpath("//h1[@class='orders-search__title']");
	public static By QUICK_SEARCH_DROPDOWN=By.xpath("//select[@name='searchBy']");
	public static  By ADVANCED_SEARCH_VALUE=By.xpath("//input[@class='form__field']");
	public static By SEARCH_BUTTON=By.xpath("//button[@class='button search-form__btn js-gtm-orders__quick-search']");
	public static By SEARCH_RESULTS=By.xpath("//div[@class='search-results']");
	public static By setAdvancedSearchOption(String option) {
		return By.xpath("//select[@name='searchBy']//option[text()='"+option+"']");
	}
	
	public static By START_DATE_CALENDER=By.xpath("//label[@for='startDate']//parent::div//input[@class='form__field date-picker__input']");
	public static By ADVANCED_SEARCH=By.xpath("//a[text()='Advanced order search']");
	public static By ORDER_NUMBER=By.xpath("//div[@class='search-results']//tbody//a[@class='orders__link order-card-header__link']");
	public static By ADVANCED_SEARCH_SEARCHBUTTON=By.xpath("//button[@class='button search-form__btn js-gtm-orders__advanced-search']");
	//send to colleauge
	public static By SEND_TO_COLLEAUGE_LINK=By.xpath("//span[text()='Send to a colleague']//parent::a");
	public static By SEND_TO_COLLEAUGE=By.xpath("//h3[text()='Send to colleague']");
	public static By YOUR_EMAIL=By.xpath("//label[@for='yourEmail']//input[@type='email']");
	public static By YOUR_RECEIPENT_EMAIL=By.xpath("//label[@for='recpEmails']//input[@type='email']");
	public static By YOUR_COMMENTS=By.xpath("//label[@for='comment']//textarea[@class='form__field']");
	public static By YOUR_NAME=By.xpath("//label[@for='yourName']//input[@type='text']");
	public static By SEND=By.xpath("//button[@class='button iw-modal__button expanded no-margin-bot']");
	public static By EMAIL_SENT_MESSAGE=By.xpath("//p[@class='hosted-licensing__message hosted-licensing__message--success']");
	//print
	public static By PRINT=By.xpath("//a//span[text()='Print']");
	//Add tracking notification
	public static By ADD_TRACKING_NOTIFICATION=By.xpath("//a//span[text()='Add tracking notifications']");
	public static By TRACKING_NOTIFICATION=By.xpath("//h3[text()='Tracking notifications']");
	public static By CANCEL_BUTTON=By.xpath("//button[@class='button iw-modal__button expanded hollow no-margin-bot']");
	public static By ORDER_AGAIN=By.xpath("//a//span[text()='Order again']");
	//Tools homepage
	public static By TRACK_AN_ORDER=By.xpath("//a[text()='Track an order']");
	//Generic search
	public static By genericSerachTab(String tabName) {
		return By.xpath("//div[@class='generic-search__list']//button[contains(text(),'"+tabName+"')]");
	}
	
	public static By genericSearchValues(String inputField) {
		return By.xpath("//label[@class='form__label']//input[contains(@name,'"+inputField+"')]");
	}
	public static By GENERIC_SEARCH_BUTTON=By.xpath("//button[@class='button expand generic-search__btn']");
	public static By SEARCH_RESULTS_GENERIC_SEARCH=By.xpath("//div[@class='search-results']");
	public static By TRACK_AN_ORDER_PAGE=By.xpath("//h1[text()='Track my order']");
	
	//advanced search
	public static By ORDER_STATUS_DROPDOW=By.xpath("//select[@name='status']");
	public static By ON_HOLD_RESULTS=By.xpath("//table[@class='iw-table']//tbody//tr/td[@class='iw-table__column text-center']//span");
	public static By Day(String day){
    	return By.xpath("//div[contains(@class,'react-datepicker__day') and text()='"+day+"']");
    }
	public static By SPINNER_IMAGE=By.xpath("//span[@class='iw-loading iw-loading__size-medium']");
	public static By CLEAR_SEARCH=By.xpath("//button[@class='button clear search-form__btn']");
	public static By SHIPPING_TYPE=By.xpath("//select[@name='shippingType']");
	public static By SHIPPED_QTY=By.xpath("//table[@class='order-card-section__table']//span[@class='item-body__label']");
	public static By OPEN_INVOICED_ORDERS=By.xpath("//select[@name='orderType']");
	
	public static By END_DATE_CALENDER=By.xpath("//label[@for='endDate']//parent::div//input[@class='form__field date-picker__input']");
	public static By ACCOUNT_SELECTION_DROPDOWN=By.xpath("//select[@name='levelIndex']");
	public static By DISPLAY_PER_PAGE=By.xpath("//span[text()='Display per page']//parent::label//div");
	public static By countPerPage(String noOfResults) {
		return By.xpath("//*[contains(text(),'"+noOfResults+"')]");
	}
	public static By ORDER_NUMBERS_IN_RESULT=By.xpath("//table[@class='iw-table']//tbody//a[@class='orders__link order-card-header__link']");
	public static By SORT_RESULTS_BY=By.xpath("//span[text()='Sort results by']//parent::label//div");
	public static By SORT_ORDER=By.xpath("//span[text()='Sort order']//parent::label//div");
	public static By sortResultsFilter(String noOfResults) {
		return By.xpath("//*[contains(text(),'"+noOfResults+"')]");
	}
	
	public static By sortOrderFilter(String noOfResults) {
		return By.xpath("//*[contains(text(),'"+noOfResults+"')]");
	}
	public static By DATES_IN_RESULT=By.xpath("//table[@class='iw-table']//tbody//tr//td[4]//span");
	public static By ACCOUNT_NAME=By.xpath("//table[@class='iw-table']//tbody//tr/td[7]");
	public static By selectedOperationsCenter(String text) {
		return By.xpath("//select[@name='opsCenter']//option[text()='"+text+"']");
	}
	public static By ORDER_TOTAL_CURRENCY_CODE=By.xpath("//table[@class='iw-table']//tbody//tr/td[@class='iw-table__column text-right']//span[@class='iw-currency__code']");
	public static By INSIGHT_AUSTRAILIA=By.xpath("//select[@name='region']//option[text()='Insight Australia']");
	
}
