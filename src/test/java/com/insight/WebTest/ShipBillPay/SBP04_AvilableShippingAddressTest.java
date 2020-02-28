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

public class SBP04_AvilableShippingAddressTest extends ShipBillPayLib{
	// #############################################################################################################
		// # Name of the Test : SBP04_AvilableShippingAddress
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_SBP04(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP04_AvilableShippingAddress", TestDataInsight,
					"Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP04_AvilableShippingAddress", TestDataInsight,
						"Ship_Bill_Pay", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("AvilableShippingAddress");
				reporter.SuccessReport("Iteration Number : ",
						"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
								+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
						"");
				CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
				CartLib cartLib = new CartLib();
				OrderLib orderLib = new OrderLib();
				ShipBillPayLib shipbLib = new ShipBillPayLib();
				// login
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				// --------RoleandPermission "change_ship_to;ON"
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				// Uncheck all shipping options
				cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				cmtLib.selectOptionInCheckoutSettings(data.get("Shipping Addresses"));
				shipbLib.SelectAllLinkedaddresses(data.get("Linkuseraddresses"));
				cmtLib.loginAsAdminCMT();
				// add product to cart
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				// proceed to checkout
				orderLib.proceedToCheckout();
				orderLib.clickContinueOnLineLevelInfo();
				// verify shipping address
				shipbLib.VerifyStoredAddress(data.get("storedaddress"));
				orderLib.shippingBillPayContinueButton();
				// login
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				cmtLib.selectOptionInCheckoutSettings(data.get("Shipping Addresses"));
				// shipping options
				// Check all shipping options CheckUnCheckCheckBoxes("ON");
				shipbLib.SelectAllLinkedaddresses(data.get("Linkuseraddresses1"));
				shipbLib.SelectdefualtAddress();
				cmtLib.loginAsAdminCMT();
				// add to cart
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				orderLib.proceedToCheckout();
				// cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				shipbLib.VerifyStoredAddress(data.get("Defualtaddress"));
				orderLib.shippingBillPayContinueButton();
				// login
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				cmtLib.selectOptionInCheckoutSettings(data.get("Shipping Addresses"));
				shipbLib.SelectAllLinkedaddresses(data.get("Linkuseraddresses1"));
				cmtLib.loginAsAdminCMT();
				// add to cart
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				orderLib.proceedToCheckout();
				orderLib.clickContinueOnLineLevelInfo();
				shipbLib.AddNewshippingAddressWithcountry(data.get("link"), data.get("companyName"), data.get("street"),
						data.get("city"), data.get("zipcode"), data.get("state"), data.get("Country"));
				shipbLib.VerifyCreatedAddress(data.get("Company"));
				// Search for the Account Name
				orderLib.shippingBillPayContinueButton();
				// login
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				cmtLib.selectOptionInCheckoutSettings(data.get("Shipping Addresses"));
				shipbLib.SelectAllLinkedaddresses(data.get("Linkuseraddresses1"));
				cmtLib.loginAsAdminCMT();
				// add to cart
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				orderLib.proceedToCheckout();
				orderLib.clickContinueOnLineLevelInfo();
				shipbLib.AddNewshippingAddressWithcountry(data.get("link"), data.get("companyName"), data.get("street"),
						data.get("city"), data.get("zipcode"), data.get("state"), data.get("Country"));
				shipbLib.VerifyCreatedAddress(data.get("Company"));
				shipbLib.Searchforstoredaddress(data.get("companyName"));
				// permissions unchek
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				cmtLib.selectOptionInCheckoutSettings(data.get("Shipping Addresses"));
				shipbLib.SelectAllLinkedaddresses(data.get("Linkuseraddresses1"));
				

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					System.out.println(e.getMessage());
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("AvilableShippingAddress", "SBP04", ReportStatus.strMethodName, intCounter,
						browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("AvilableShippingAddress", "SBP04", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}