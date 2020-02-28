package com.insight.Lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.insight.ObjRepo.CMTObj;
import com.insight.ObjRepo.CanadaObj;
import com.insight.ObjRepo.SewpObj;
import com.insight.ObjRepo.UserManagementObj;
import com.insight.utilities.DynamicTestDataGenerator;

public class UserManagementLib extends UserManagementObj {	
	
	CMTLib cmtLib = new CMTLib();
	/**
	 * Method is used to verify user is logged to canada webgrp
	 */
	public void verifyNoUsersErrMsg(String invalidUser) throws Throwable {
				
		  cmtLib.searchUsers(invalidUser);
		if (isElementPresent(NOUSRS_ERRMSG, "No User results")) {
			reporter.SuccessReport("Verify that no users are returned on User Management Page", "No users are returned and error message is displayed", "");
		} else {
			reporter.failureReport("Verify that no users are returned on User Management Page", " users are returned and no error message is displayed", "",driver);
		}
	}

	/**
	 * Method is used to verify user is logged to canada webgrp
	 */
	public void verifyWithValidUserName(String user) throws Throwable {
		 cmtLib.searchUsers(user);
		if (isElementPresent(USERS_TOT, "No User results")) {
			
			String users=getText(USERS_TOT,"");
			
			reporter.SuccessReport("Verify that users having the given search input are returned", "Users having search input returned are "+users+"", "");
		} else {
			reporter.failureReport("Verify that users having the given search input are returned", "Users having search input  has not returnedreturned", "",driver);
		}
	}	
	
	
	/**
	 * Method is used to verify user is logged to canada webgroup
	 */
	public void verifyPagination(String intEndPaging) throws Throwable {
		  selectByVisibleText(RECORDS,intEndPaging,"");
		   if (isElementPresent(getPagination(intEndPaging), "Pagnation")) {
			 
			reporter.SuccessReport("Verify "+intEndPaging+" users are displayed ", intEndPaging+" users are displayed", "");
		 } else {
			reporter.failureReport("Verify "+intEndPaging+" users are displayed ", intEndPaging+" users are  not displayed", "",driver);
		}
	}
	
	
	/**
	 * Method is used to verify user is logged to canada webgrp
	 */
	public void verifyEmailUserName(String emailUser) throws Throwable {
		
	    cmtLib.searchUsers(emailUser);
		if (isElementPresent(getEmailUser(emailUser), "Email User")) {					
			reporter.SuccessReport("Verify that user matching the email address are returned", "User matching the email address returned - "+emailUser+"", "");
		} 
		}
	
	/**
	 * Method is used to verify user is logged to canada webgrp
	 */
	public void verifyWithUserId(String userId) throws Throwable {
		
	    cmtLib.searchUsers(userId);
		if (isElementPresent(getEmailUser(userId), "Email User")) {					
			reporter.SuccessReport("Verify that user matching the UserId are returned", "User matching the UserId returned - "+userId+"", "");
		} else {
			reporter.failureReport("Verify that user matching the UserId are returned", "User matching the UserId is not returned ", "");
		} 
		}
	
	/**
	 * Method is used to verify user is logged to canada webgrp
	 */
	public void clickOnExportToExcel() throws Throwable {
		
	   
		if (isElementPresent(EXPORT, "Exprt To Excel ")) {	
			click(EXPORT, "Exprt To Excel ");
			reporter.SuccessReport("Click on Export To Excel button on User Management Page", "Export To Excel button was  found and clicked ", "");
		} else {
			reporter.failureReport("Click on Export To Excel button on User Management Page", "Export To Excel button was not found ", "");
		} 
		}
	
	/**
	 * Method to verify download excel file
	 * 
	 * @throws Throwable
	 */
	public void verifyDownloadedUsersExcelFile(String filePath) throws Throwable {
		Thread.sleep(10000);
		File root = new File("C:\\Users\\e004303\\Downloads");
		FilenameFilter beginswithm = new FilenameFilter() {
			public boolean accept(File directory, String filename) {
				return filename.startsWith(filePath);
			}
		};
		File[] files = root.listFiles(beginswithm);

		if (files[0] != null) {
			// PATH
			reporter.SuccessReport("File Download Dialog With Open Button ", "File Download Dialog Exists and Selected Open Button", "");
			// load file
			FileInputStream fis = new FileInputStream(files[0]);
			// Load workbook
			HSSFWorkbook wb =new HSSFWorkbook(fis);			
			HSSFSheet sh1 =wb.getSheet("Sheet0");
			int rows = (sh1.getLastRowNum())-1;	
			fis.close();
			reporter.SuccessReport("All Users are included in the Export to Excel File ", "No. of Users:"+rows+"", "");
			reporter.SuccessReport("Verify that t Export to Excel File ", "Excel File is Closed", "");
          
		}
		else {
			reporter.failureReport("File Download Dialog With Open Button ", "File Download Dialog does not  Exists ", "",driver);
		}
	}
	
	
	/**
	 * Method is used to verify user status
	 */
	public void verifyUserStatus() throws Throwable {
		
		String option="";
		if (isElementPresent(STATUS, "Status ")) {	
		 option =getSelectedDropdownOption(STATUS);
			reporter.SuccessReport("Verify User Status", "User status is "+option+"", "");
		} else {
			reporter.failureReport("Verify User Status", "User status is not "+option+"", "",driver);
		} 
		}

	/**
	 * Method is used to verify user status
	 */
	public void selectUserStatus(String userStatus) throws Throwable {

		 
		if (isElementPresent(STATUS, "Status ")) {	
			  selectByVisibleText(STATUS,userStatus,"");
			  cmtLib.searchUsers("");
			reporter.SuccessReport("Verify User Status", "User status is "+userStatus+"", "");
		} else {
			reporter.failureReport("Verify User Status", "User status is not "+userStatus+"", "",driver);
		} 
		}

	/**
	 * Method is used to verify user status
	 */
	public void selectPagination(String pageNo) throws Throwable {
	if(isElementPresent(PAGINATION(pageNo), "Pagination")){
		click(PAGINATION(pageNo),"Pagination");
		reporter.SuccessReport("Click on page 3 to verify results", "Page 3 results displayed","");
	}
		else{
		reporter.failureReport("Click on page 3 to verify results", "No Pagination","");
	}
}
	
	
	
	/**
	 * Method is used to verify UserType
	 */
	public void selectUsertypeUserStatus(String userType ,String userStatus) throws Throwable {		
		
		if (isElementPresent(USER_TYPE, "User Type ")) {	
		selectByVisibleText(STATUS,userStatus,"");
	    selectByVisibleText(USER_TYPE,userType,"");
	    cmtLib.searchUsers("");
		reporter.SuccessReport("Only "+userType+" users are returned in First Page", "Only "+userType+" and "+userStatus+" users are there","");
		} else {
			reporter.failureReport("Only "+userType+" users are returned in First Page", "Only "+userType+" and "+userStatus+"  users are not there","");
		}	
			}
	
	
	/**
	 * Method is used to verify UserType
	 */
	public void selectUsertype(String userType ) throws Throwable {			
		if (isElementPresent(USER_TYPE, "User Type ")) {	
	    selectByVisibleText(USER_TYPE,userType,"User type");
	    cmtLib.searchUsers("");
			reporter.SuccessReport("Only "+userType+" users are returned in First Page", "Only "+userType+" users are there","");
		} else {
			reporter.failureReport("Only "+userType+" users are returned in First Page", "Only "+userType+" users are not there","");
		}	
			}
	
	
	/**
	 * Method is used to verify UserType
	 */
	public void selectWebGroup(String webGroup ) throws Throwable {		
		
		if (isElementPresent(USER_WEBGROUP, "Web Group Relationship ")) {	
	    selectByVisibleText(USER_WEBGROUP,webGroup,"");
	    cmtLib.searchUsers("");
			reporter.SuccessReport("Only "+webGroup+" users are returned in First Page", "Only "+webGroup+" users are there","");
		} else {
			reporter.failureReport("Only "+webGroup+" users are returned in First Page", "Only "+webGroup+" users are not there","");
		}	
			}

	/**
	 * Method is used to verify WelcomePage
	 */
	public void verifyWelcomePage() throws Throwable {		
		if (isElementPresent(HEADER_LOGO, "WelcomePage")) {	
			reporter.SuccessReport("Shared User WelCome Page", "WelCome Page exists","");
		} else {
			reporter.failureReport("Shared User WelCome Page", "WelCome Page does not exist","");
		}	
			}

	
	
	/**
	 * verify Create Account Fields
	 * @throws Throwable
	 */
	public void verifyCreateAccountFields() throws Throwable{
		// verify email account field
		if(isElementPresent(SewpObj.EMAIL_ADDRESS,"Email Address")){
			reporter.SuccessReport("Verify email present", "Email field is present","");
		}else{
			reporter.failureReport("Verify email present", "Email field is not  present", "",driver);
		}
		
		// verify password field
		if(isElementPresent(SewpObj.PWD, "Password")){
			reporter.SuccessReport("Verify password field present", "password field is present","");
		}else{
			reporter.failureReport("Verify password field present", "password field is not  present", "",driver);
		}
		
		
		// verify confirm  password field
		if (isElementPresent(SewpObj.CRM_PWD, "Confirm Password")) {
			reporter.SuccessReport("Verify Confirm password field present", "Confirm password field is present","");
		} else {
			reporter.failureReport("Verify Confirm password field present", "Confirm password field is not  present", "", driver);
		}
		
		// verify I would like to receive Insight email newsletter > .CUSTOM_CHECKBOX
		if (isElementPresent(CanadaObj.CUSTOM_CHECKBOX, "CUSTOM CHECKBOX")) {
			reporter.SuccessReport("Verify I would like to receive Insight email newsletter field present", "I would like to receive Insight email newsletter field is present","");
		} else {
			reporter.failureReport("Verify I would like to receive Insight email newsletter field present", "I would like to receive Insight email newsletter field is not  present", "", driver);
		}
	}
	
	/**
	 * Verify enter valid Email message
	 * @throws Throwable
	 */
	public void verifyEmailErrorMsg() throws Throwable{
		if(isElementPresent(EMAIl_ERROR_MSG, "enter valid Email msg")){
			reporter.SuccessReport("Verify enter valid Email msg", "enter valid Email msg verification is successfull","");
		}else{
			reporter.failureReport("Verify enter valid Email msg", "enter valid Email msg verification is not successfull","",driver);
		}
	}
	
	/**
	 * Verify valid password message
	 * @throws Throwable
	 */
	public void verifyValidPasswordMessage() throws Throwable{
		if(isElementPresent(PASSWORD_ERROR_MSG, "enter valid password msg")){
			reporter.SuccessReport("Verify enter valid password msg", "enter valid password msg verification is successfull","");
		}else{
			reporter.failureReport("Verify enter valid password msg", "enter valid password msg verification is not successfull","",driver);
		}
	}
	
	/**
	 * Enter password in create account page
	 * @throws Throwable
	 */
	public void enterpasswordInCreateAccount(String Password, String confirmPassword) throws Throwable{
		typeText(SewpObj.PWD, Password , "Password");
		typeText(SewpObj.CRM_PWD,confirmPassword, "Confirm Password");
	}
	
	/**
	 * Enter email address in create account page
	 * @throws Throwable
	 */
	public void enterEmailInCreateAccount(String email) throws Throwable{
		waitForVisibilityOfElement(SewpObj.EMAIL_ADDRESS, "Email Address");
		typeText(SewpObj.EMAIL_ADDRESS, email, "Email Address");
	}
	
	/**
	 * Verify his email has already been registered message
	 * @throws Throwable
	 */
	public void verifyEmailAlreadyExistsErrorMsg() throws Throwable{
		if(isElementPresent(EMALI_ALREADY_REGISTERED, "enter valid Email message")){
			reporter.SuccessReport("Verify This email has already been registered msg", "This email has already been registered msg verification is successfull","");
		}else{
			reporter.failureReport("Verify This email has already been registered msg", "This email has already been registered msg verification is not successfull","",driver);
		}
	}
	
	/**
	 * Method is to click on the cancel button
	 * @throws Throwable
	 */
	public void clickCancel() throws Throwable{
		if(isElementVisible(CANCEL, 3, "cancel")){
			click(CANCEL, "cancel");
		}else{
			reporter.failureReport("Verify cancel button", "cancel button is not present","",driver);
		}
		
	}
	
}
