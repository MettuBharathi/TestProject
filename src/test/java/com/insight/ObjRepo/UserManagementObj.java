package com.insight.ObjRepo;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class UserManagementObj extends ActionEngine{
	
	
	public static By NOUSRS_ERRMSG = By.xpath("//div[@id='userSearchEmptyResultsText'][contains(.,'No results were found based on your search criteria')]");
	
	
	public  static By  getPagination(String pages){
		return By.xpath("//span[@id='showingEnd'][contains(.,'"+pages+"')]");
	}
	
	public static By USERS_TOT = By.xpath("//span[@id='showingTotal']");
	
	
	public  static By  getEmailUser(String emailUser){
		return By.xpath("//table[@id='userSearchResultsTable']//tr[2]//td[contains(.,'"+emailUser+"')]//div");
	}
	public static By EXPORT = By.xpath("//a[@title='Export To Excel']");
	
	public static By STATUS = By.xpath("//select[@id='userStatus']");
	public static By RECORDS = By.xpath("//select[@id='shownRecords']");
	
	public static By USER_TYPE = By.xpath("//select[@id='userUserType']");
	
	public static By USER_WEBGROUP = By.xpath("//select[@id='userWebGroupRelationship']");
	
	public static By PAGINATION (String pageNo){
		return By.xpath("//strong//a[contains(text(),'"+pageNo+"')]");
	}
			
	public  static By  getUserType(String userType){
		return By.xpath("(//td[@id='UserType'][contains(.,'"+userType+"')])[1]");
	 }
	
	public static By EMAIl_ERROR_MSG=By.xpath("//div[@class='email-input-error']//p[text()='Please enter a valid email address.']");
	public static By PASSWORD_ERROR_MSG=By.xpath("//div[@class='password-input-error']//p[text()='Please enter a valid password.']");
	public static By EMALI_ALREADY_REGISTERED=By.xpath("//div[@class='email-exists-error']//p[text()='This email has already been registered. You can:']");
	public static By CANCEL=By.xpath("//a[@id='cla-cancel']//span");
	public static By HEADER_LOGO=By.xpath("//div[@class='c-header-logo']");
}
	
