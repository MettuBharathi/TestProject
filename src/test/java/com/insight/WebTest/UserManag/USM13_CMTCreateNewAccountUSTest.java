package com.insight.WebTest.UserManag;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.LineLevelInfoLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.Lib.UserManagementLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.DynamicTestDataGenerator;
import com.insight.utilities.TestUtil;

public class USM13_CMTCreateNewAccountUSTest extends UserManagementLib{
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib = new CanadaLib();
	UserManagementLib userMgt=new UserManagementLib();
	LineLevelInfoLib lnlLib=new LineLevelInfoLib();
	
	            // #############################################################################################################
				// #       Name of the Test         :  USM13_CMTCreateNewAccountUSTest
				// #       Migration Author         :  Cigniti Technologies
				// #
				// #       Date of Migration        : October 2019
				// #       DESCRIPTION              : This Test is used to Test CMTCreate New Account US
				// #       Parameters               : StartRow ,EndRow , nextTestJoin
				// #
				// ###############################################################################################################
		
		
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_USM13(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "USM13_CMTCreateNewAccountUS", TestDataInsight, "UserManagement");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("USM13_CMTCreateNewAccountUS", TestDataInsight, "UserManagement", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("SimplifiedAccountCreation");
	                    
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						//cmtLib.clickWebToolsAndSelectOptionFromDD(data.get("Option"));
						cmtLib.verifyCreateAnAccountPage();
						String email=DynamicTestDataGenerator.generateRandomEmail();
						cmtLib.enterEmailInCreateAnAccount(email);
						//cmtLib.selectMyPurchasesAreFor(data.get("My_Purchase_Option"));
						String firstName=DynamicTestDataGenerator.generateRandomFirstName();
						cmtLib.enterFirstNameInCreateAnAccount(firstName);
						String lastName=DynamicTestDataGenerator.generateRandomLastName();
						cmtLib.enterLastNameInCreateAnAccount(lastName);
						String phoneNumber=DynamicTestDataGenerator.generateRandomPhoneNumber();
						cmtLib.enterPhoneNumberInCreateAnAccount(phoneNumber);
						/*cmtLib.selectJobTitle(data.get("Job_Title"));
						cmtLib.selectCountry(data.get("Country"));
						
						/// Billing Address
						cmtLib.enterAddressOne(data.get("Address"));
						cmtLib.enterCityName(data.get("City"));*/
						cmtLib.selectStateInCreateAnAccount(data.get("State"));
						cmtLib.enterZipCodeInCreateAnAccount(data.get("ZipCode"));
						// Login Information
						cmtLib.enterUserNameInCreateAnAccount(data.get("UserName"));
						cmtLib.enterPasswordInCreateAnAccount(data.get("Password"));
						cmtLib.enterConfirmPasswordInCreateAnAccount(data.get("Password"));
						
						
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("CMTCreateNewAccountUS", "TC_USM13", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("CMTCreateNewAccountUS ", "TC_USM13", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}


}
