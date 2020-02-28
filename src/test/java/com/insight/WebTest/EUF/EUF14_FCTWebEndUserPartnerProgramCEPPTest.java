package com.insight.WebTest.EUF;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.EndUserFeaturesLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class EUF14_FCTWebEndUserPartnerProgramCEPPTest extends EndUserFeaturesLib{
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib = new CanadaLib();
	MarriottIntlCorpLib marriottintlib=new MarriottIntlCorpLib();
	    // #############################################################################################################
		// #       Name of the Test         :  EUF14_FCTWebEndUserPartnerProgramCEPP
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test FCTWebEndUserManageSoldtos
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_EUF17(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "EUF14_FCTWebEndUserPartnerProgramCEPP", TestDataInsight, "End_User");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("EUF14_FCTWebEndUserPartnerProgramCEPP", TestDataInsight, "End_User", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("FCTWebEndUserPartnerProgramCEPP");
					
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.manageUsers();
						
						// Click on Linked Accounts
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.linkedAccountSearch(data.get("Account_Number"));
						cmtLib.clickRadioDefaultAtLinkedAccounts(data.get("Account_Number"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options2"));
						//cmtLib.searchUsers(data.get("LnameEmailUname"));
						//cmtLib.verifyUserandClick(data.get("ContactName"));
						cmtLib.selectUserType(data.get("LnameEmailUname"));
						cmtLib.selectFirstUser();
						cmtLib.clickOnUserURL();
						//create an account
						cmtLib.verifyCreateAnAccountPage();
						//verify error message
						cmtLib.enterAdressesInCreateAnAccount(data.get("Adressess"));
						cmtLib.enterZipCodeInCreateAnAccount(data.get("ZipCode"));
						cmtLib.clickCreateButtonInCreateAnAccount();
						cmtLib.verifyErrorMessageICrateAccount();
						//create an account
						String email="QTPTest"+getRandomNumeric(4)+"@test.com";
						cmtLib.enterEmailInCreateAnAccount(email);
						String firstName="QTPTest"+getRandomNumeric(4);
						cmtLib.enterFirstNameInCreateAnAccount(firstName);
						String lastName="QTPTest";
						cmtLib.enterLastNameInCreateAnAccount(lastName);
						cmtLib.enterPhoneNumberInCreateAnAccount(data.get("Phone_Number"));
						
						//Billing addresses
						String billingAccountName="QTPTest"+getRandomNumeric(4);
						cmtLib.enterBillingAccountNameInCreateAnAccount(billingAccountName);
						cmtLib.enterAdressesInCreateAnAccount(data.get("Adressess1"));
						cmtLib.enterCityInCreateAnAccount(data.get("City"));
						cmtLib.selectStateInCreateAnAccount(data.get("State"));
						cmtLib.enterZipCodeInCreateAnAccount(data.get("ZipCode"));
						//Login info
						String userNameCreateAccount="QTPTest"+getRandomNumeric(4);
						String userName1CreateAccount="QTPTest"+getRandomNumeric(4);
						String userNameEntered=cmtLib.verifyAvailabilityCreateAccount(userNameCreateAccount,userName1CreateAccount);
						String password="QTPTest"+getRandomNumeric(4);
						cmtLib.enterPasswordInCreateAnAccount(password);
						cmtLib.enterConfirmPasswordInCreateAnAccount(password);
						cmtLib.clickCreateButtonInCreateAnAccount();
						cmtLib.clickContinueButtonInCreateAnAccount();
						cmtLib.verifyWelcomePage();
						commonLib.clickLogOutLink(data.get("Logout_Header"));
						//relogin
						cmtLib.loginAsAdmin();
						cmtLib.searchForUserByEmail(email);
						cmtLib.manageUsers();
						cmtLib.searchUsers(userNameEntered);
						cmtLib.verifyUserandClick(firstName+" "+lastName);
						cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
						//cmtLib.logoutSite();
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("FCTWebEndUserPartnerProgramCEPP", "TC_EUF14", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("FCTWebEndUserPartnerProgramCEPP", "TC_EUF14", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}
