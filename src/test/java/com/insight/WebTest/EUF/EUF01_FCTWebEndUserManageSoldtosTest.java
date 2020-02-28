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
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class EUF01_FCTWebEndUserManageSoldtosTest extends EndUserFeaturesLib{

	
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
		// #       Name of the Test         :  EUF01_FCTWebEndUserManageSoldtos
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test FCTWebEndUserManageSoldtos
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_EUF01(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "EUF01_FCTWebEndUserManageSoldtosTest", TestDataInsight, "End_User");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("EUF01_FCTWebEndUserManageSoldtosTest", TestDataInsight, "End_User", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("FCTWebEndUserManageSoldtos");
                        
						cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
								data.get("LnameEmailUname"), data.get("ContactName"));
						// Click on Linked Accounts
						cmtLib.clickCheckOutSettings(data.get("Linked_Accounts"));
						cmtLib.clickDefaultRadioButtonInLinkedAccounts();
						cmtLib.clickUpdateButtonOnLinkedAccountsScreen();
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						ivhLib.closeAccountTools();
						canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						clickOnTabInUserProfile(data.get("Tab_Name"));
						verifyLoggedInAs(data.get("LnameEmailUname"));
						verifyWebGroup(data.get("Web_Group_Name"));
						verifyCurrentAccount();
						verifyRemoveDefualtLink();
						verifyResultsDisplayedPerPage(data.get("Results_Per_Page"));
						verifyResultsPerPagrDDOptions(data.get("Results_Per_Page_Options"));
						selectResultsPerPageDD(data.get("Results_Per_Page_20"));
						selectResultsPerPageDD(data.get("Results_Per_Page_50"));
						selectResultsPerPageDD(data.get("Results_Per_Page_100"));
						clickOnPageNumber(data.get("Page_Number_3"));
						clickOnPageNumber(data.get("Page_Number_5"));
						searchForAvailableAccount(data.get("Account_Name"));
						clickSwitchAccountLink();
						clickContinueButton();
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						//cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
								//data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.manageUsers();
						cmtLib.searchUsers(data.get("LnameEmailUname"));
						cmtLib.verifyUserandClick(data.get("ContactName"));
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						ivhLib.closeAccountTools();
						canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						clickOnTabInUserProfile(data.get("Tab_Name"));
						verifyLoggedInAs(data.get("LnameEmailUname"));
						searchForAvailableAccount(data.get("Account_Name1"));
						verifySearchResults();
						searchForAvailableAccount(data.get("Account_Name2"));
						verifySearchResults();
						verifyRemoveDefualtLinkandSelect();
						verifyDefaultAccountIsRemoved();
						cmtLib.navigateBackToCMT();
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						ivhLib.closeAccountTools();
						canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						clickOnTabInUserProfile(data.get("Tab_Name"));
						clickSwitchAccountLink();
						clickContinueButton();
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("FCTWebEndUserManageSoldtos", "TC_EUF01", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("FCTWebEndUserManageSoldtos", "TC_EUF01", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}
