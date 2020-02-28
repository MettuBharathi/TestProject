package com.insight.WebTest.ShipBillPay;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SBP07_ThirdPartyCarrierAccountTest extends ShipBillPayLib{
	// #############################################################################################################
		// # Name of the Test : SBP07_ThirdPartyCarrierAccount
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_SBP07(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP07_ThirdPartyCarrierAccount", TestDataInsight,
					"Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP07_ThirdPartyCarrierAccount", TestDataInsight,
						"Ship_Bill_Pay", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("ThirdPartyCarrierAccount");
				reporter.SuccessReport("Iteration Number : ",
						"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
								+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
						"");
				CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
				CartLib cartLib = new CartLib();
				OrderLib orderLib = new OrderLib();
				ShipBillPayLib shipbLib = new ShipBillPayLib();
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				// set roles and permissions
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
				String[] permissions = data.get("Set_Permission2").split(",");
				for (i = 0; i < permissions.length; i++) {
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), permissions[i]);
				}
				// checkout settings
				cmtLib.AssigntheusertoServiceLevelShippingwithnodefault(data.get("menuname"), data.get("Set_Permission3"),
						data.get("Value"));
				cmtLib.clickupdateatDefaultShippingOption();
				cmtLib.loginAsAdminCMT();
				// add item to cart
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				orderLib.proceedToCheckout();
				// click continue in place order page
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				orderLib.shippingBillPayContinueButton();
				// verify Bill my carrier
				shipbLib.verifyBillmycarrier();
				orderLib.shippingOptionsCarrierSelection();
				orderLib.shippingBillPayContinueButton();// billing address continue
				orderLib.clickOnReviewOrderButton();
				shipbLib.Verifyshippingcarrier(data.get("carrierName"));
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				// set roles and permissions
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
				String[] permissions1 = data.get("Set_Permission2").split(",");
				for (i = 0; i < permissions1.length; i++) {
					cmtLib.setPermissions(data.get("Menu_Name"), permissions1[i]);
				}
				// checkout settings
				cmtLib.AssigntheusertoServiceLevelShippingwithnodefault(data.get("menuname"), data.get("Set_Permission3"),
						data.get("Value"));
				cmtLib.clickupdateatDefaultShippingOption();
				cmtLib.loginAsAdminCMT();
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				orderLib.proceedToCheckout();
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				orderLib.shippingBillPayContinueButton();
				// SelectcarrierAtBillmycarrier
				shipbLib.VeifyChoosenCarrierMsg();
				shipbLib.SelectcarrierAtBillmycarrier(data.get("DefCarrier"));
				// continue button shipping Options
				orderLib.shippingOptionsCarrierSelection();
				// account num verify
				shipbLib.verifyCarrierAccountinplaceorder();
				orderLib.shippingBillPayContinueButton();// billing address continue
				orderLib.clickOnReviewOrderButton();
				// logout
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
				cmtLib.AssigntheusertoServiceLevelShippingwithnodefault(data.get("menuname"), data.get("Set_Permission3"),
						data.get("Value"));
				cmtLib.clickupdateatDefaultShippingOption();
				cmtLib.loginAsAdminCMT();
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				orderLib.proceedToCheckout();
				// click continue in place order page
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				orderLib.shippingBillPayContinueButton();
				shipbLib.VeifyChoosenCarrierMsg();
				shipbLib.Verifycarrieroptionsdiabled(data.get("disabledcarrier"));
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					System.out.println(e.getMessage());
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ThirdPartyCarrierAccount", "SBP07", ReportStatus.strMethodName, intCounter,
						browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("ThirdPartyCarrierAccount", "SBP07", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}
