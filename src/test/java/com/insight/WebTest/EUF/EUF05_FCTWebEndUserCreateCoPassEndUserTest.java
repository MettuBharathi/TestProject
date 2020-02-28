package com.insight.WebTest.EUF;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.EndUserFeaturesLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.ObjRepo.CartObj;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class EUF05_FCTWebEndUserCreateCoPassEndUserTest extends EndUserFeaturesLib{
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib = new CanadaLib();
	InvoiceHistoryLib ivhLib=new InvoiceHistoryLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  EUF05_FCTWebEndUserCreateCoPassEndUser
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test FCTWebEndUserManageSoldtos
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_EUF05(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "EUF05_FCTWebEndUserCreateCoPassEndUser", TestDataInsight, "End_User");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("EUF05_FCTWebEndUserCreateCoPassEndUser", TestDataInsight, "End_User", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("EUF05_FCTWebEndUserCreateCoPassEndUser");
						cmtLib.clickLoginLink(data.get("Header"));
						cmtLib.handleWelcomeToInsightBetaPopUp();
						cmtLib.loginAsAdmin();
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.manageUsers();
						cmtLib.clickAddNewUserLink();
						cmtLib.selectUserTypeDropdown(data.get("User_Type"));
						String userName=getRandomNumeric(4);
						cmtLib.enterUserName("QTPTest"+userName);
						String userName1=getRandomNumeric(4);
						cmtLib.verifyAvailabiltyOfUserName("QTPTest"+userName1);
						cmtLib.clickCreateUserButton();
						cmtLib.setPermissions(data.get("Menu_Name"),data.get("Set_Permission"));
						cmtLib.clickInformationTab(data.get("Information_Tab"));
						cmtLib.clickOnUserURL();
						//create an account
						cmtLib.verifyCreateAnAccountPage();
						String email="QTPTest"+getRandomNumeric(4)+"@test.com";
						cmtLib.enterEmailInCreateAnAccount(email);
						String firstName="QTPTest"+getRandomNumeric(4);
						cmtLib.enterFirstNameInCreateAnAccount(firstName);
						String lastName="QTPTest"+getRandomNumeric(4);
						cmtLib.enterLastNameInCreateAnAccount(lastName);
						cmtLib.enterPhoneNumberInCreateAnAccount(data.get("Phone_Number"));
						String userNameCreateAccount="QTPTest"+getRandomNumeric(4);
						String userName1CreateAccount="QTPTest"+getRandomNumeric(4);
						String userNameEntered=cmtLib.verifyAvailabilityCreateAccount(userNameCreateAccount,userName1CreateAccount);
						String password="QTPTest"+getRandomNumeric(4);
						cmtLib.enterPasswordInCreateAnAccount(password);
						cmtLib.enterConfirmPasswordInCreateAnAccount(password);
						cmtLib.clickCreateButtonInCreateAnAccount();
						cmtLib.verifyWelcomePage();
						commonLib.clickLogOutLink(data.get("Logout_Header"));
						//relogin
						cmtLib.loginAsAdmin();
						cmtLib.searchForUserByEmail(email);
						cmtLib.manageUsers();
						cmtLib.searchUsers(userNameEntered);
						cmtLib.verifyUserandClick(firstName+" "+lastName);
						cmtLib.verifyPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.logoutSite();
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("FCTWebEndUserCreateCoPassEndUser", "TC_EUF05", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("FCTWebEndUserCreateCoPassEndUser", "TC_EUF05", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}
