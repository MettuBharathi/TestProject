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

public class SBP08_SaveOrderTemplateIPSTest extends ShipBillPayLib{
	// #############################################################################################################
		// # Name of the Test : SBP08_SaveOrderTemplateIPS
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_SBP08(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP08_SaveOrderTemplateIPS", TestDataInsight,
					"Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP08_SaveOrderTemplateIPS", TestDataInsight,
						"Ship_Bill_Pay", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("SaveOrderTemplateIPS");
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
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.loginAsAdminCMT();
				commonLib.searchProduct(data.get("Search_Item1"));
				cartLib.selectFirstProductDisplay();
				commonLib.addToCartAndVerify();

				// view cart
				commonLib.clickCart();
				cartLib.verifyCartBreadCrumb();
				// add product by quick shop
				shipbLib.AdditemsbyQuickshop(data.get("searchitem2"));
				orderLib.proceedToCheckout();
				shipbLib.Addadtionalinformation(data.get("wG_HDL_Txt"), data.get("emailToEnter"), data.get("A"));
				shipbLib.enterReportingDetailsInLineLevelInfoSection(data.get("REPORTING FIELD_4"),
						data.get("REPORTING FIELD_5"));
				orderLib.clickContinueOnLineLevelInfo();
				shipbLib.clickstoredAddress(data.get("storedaddress"));
				shipbLib.Selectshippingcarrier();
				orderLib.shippingBillPayContinueButton(); 
				// save cart
				shipbLib.SaveCartandView(data.get("cartName"), data.get("toolsMenuName"), data.get("dropDown"));
				// after save cart remaining process
				cartLib.verifyCartBreadCrumb();
				orderLib.proceedToCheckout();
				shipbLib.Addadtionalinformation(data.get("wG_HDL_Txt"), data.get("emailToEnter"), data.get("A"));
				shipbLib.enterReportingDetailsInLineLevelInfoSection(data.get("REPORTING FIELD_4"),
						data.get("REPORTING FIELD_5"));
				orderLib.clickContinueOnLineLevelInfo();
				shipbLib.clickstoredAddress(data.get("storedaddress"));
				orderLib.shippingBillPayContinueButton(); 
				orderLib.clickOnReviewOrderButton();
				String summaryAmount = cartLib.getSummaryAmountInCart();
				orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
				orderLib.verifyShippingAddressOnReceiptPage(data.get("Section_Name1")); // verifying shipping address in receipt page.
				orderLib.verifyBillingAddressOnReceiptPage(data.get("Section_Name2")); // Verifying billing address in receipt page.
				//Deletesavedcarts
				shipbLib.Deletesavedcarts(data.get("cartName"), data.get("toolsMenuName"), data.get("dropDown"));
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				// permissions unchek
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					System.out.println(e.getMessage());
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("SaveOrderTemplateIPS", "SBP08", ReportStatus.strMethodName, intCounter,
						browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("SaveOrderTemplateIPS", "SBP08", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}
