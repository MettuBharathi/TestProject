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

public class SBP05_CarrierHeavyTest extends ShipBillPayLib{
	// #############################################################################################################
		// # Name of the Test : SBP05_CarrierHeavy
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_SBP05(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP05_CarrierHeavy", TestDataInsight, "Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				
			try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP05_CarrierHeavy", TestDataInsight,
						"Ship_Bill_Pay", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("CarrierHeavy");
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

				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				commonLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				commonLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
				commonLib.selectDefaultShippingOptionInCheckoutSettings(data.get("Default_Shipping_Option"));
				commonLib.clickOnUpdateButtonInUserSettings();

				cmtLib.clickOnloginAs();
				switchToChildWindow();
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				commonLib.updateCartQuantity(data.get("Quantity"));
				orderLib.proceedToCheckout();
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				orderLib.shippingBillPayContinueButton();
				cartLib.verifyCarriersInCheckOut(data.get("Carriers"));
				orderLib.shippingBillPayContinueButton();
				orderLib.shippingBillPayContinueButton();
				orderLib.selectPaymentMethod(data.get("Payment_method"));
				shipbLib.verifyShippingCarrierAFterReviewOrder(data.get("Shiping_Carrier_Verify"));

				String summaryAmount = cartLib.getSummaryAmountInCart();
				orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);

				shipbLib.clickOrderDetailsButtonInREceipt();
				shipbLib.verifyShippingCarrierAFterReviewOrder(data.get("Shiping_Carrier_Verify_Receipt"));
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				cmtLib.navigateBackToCMT();
				commonLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				commonLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
				cmtLib.selectParticularDesignatedShippingOptions(data.get("Options_TO_SELECT"),
						data.get("Default_Shipping_Option1"));
				commonLib.clickOnUpdateButtonInUserSettings();
				cmtLib.clickOnloginAs();
				switchToChildWindow();
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				commonLib.updateCartQuantity(data.get("Quantity"));
				orderLib.proceedToCheckout();
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				orderLib.shippingBillPayContinueButton();
				cartLib.verifyCarriersInCheckOut(data.get("Carriers1"));
				orderLib.shippingBillPayContinueButton();
				orderLib.shippingBillPayContinueButton();
				orderLib.selectPaymentMethod(data.get("Payment_method"));
				shipbLib.verifyShippingCarrierAFterReviewOrder(data.get("Shiping_Carrier_Verify1"));
				String summaryAmount1 = cartLib.getSummaryAmountInCart();
				orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount1);
				shipbLib.clickOrderDetailsButtonInREceipt();
				shipbLib.verifyShippingCarrierAFterReviewOrder(data.get("Shiping_Carrier_Verify_Receipt1"));
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				

			} catch (Exception e) {
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				System.out.println(e.getMessage());
				gTestStatus = false;
			}
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CarrierHeavy", "SBP05", ReportStatus.strMethodName, intCounter,
					browser);
			fnCloseTest();
		}
	} catch (Exception e) {
		e.printStackTrace();
		ReportStatus.blnStatus = false;
		gErrorMessage = e.getMessage();
		gTestStatus = false;
		ReportStatus.fnUpdateResultStatus("CarrierHeavy", "SBP05", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}

	ReportControl.fnNextTestJoin(nextTestJoin);
}

}