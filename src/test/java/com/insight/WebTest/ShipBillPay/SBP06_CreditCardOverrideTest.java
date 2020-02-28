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

public class SBP06_CreditCardOverrideTest extends ShipBillPayLib {
	// #############################################################################################################
		// # Name of the Test : SBP06_CreditCardOverride
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_SBP06(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP06_CreditCardOverride", TestDataInsight,
					"Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP06_CreditCardOverride", TestDataInsight,
						"Ship_Bill_Pay", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("CreditCardOverride");
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
				// override_payment_options
				cmtLib.setCustomerLevelPermissionsON(data.get("Customer_Permissions_ON"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.loginAsAdminCMT();
				// add product to cart
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				// proceed to checkout
				orderLib.proceedToCheckout();
				cartLib.clickOnContinueButtonInAddInformtion();
				// continue till billing address
				orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line level Info
				orderLib.shippingBillPayContinueButton(); // Click continue on shipping address
				orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
				orderLib.shippingBillPayContinueButton(); // Billing address continue button
				shipbLib.VerifypaymentOptions(data.get("Creditcard"));
				// orderLib.clickOnReviewOrderButton();
				// Verify Visa card Error
				shipbLib.VisaCardErrorPayment(data.get("Visa_Card_Number").toString(), data.get("Card_Name"),
						data.get("Month"), data.get("Year"), data.get("PONumber"));
				// AMX Credit Card Function
				shipbLib.emexCardPayment(data.get("Amex_Card_Number").toString(), data.get("Card_Name"),
						data.get("Amex_Month"), data.get("Year"), data.get("PONumber"));
				String summaryAmount = cartLib.getSummaryAmountInCart();
				orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				// Search For a Requesitor Group
				// Click Save Changes
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));// approval_process_setup;ON
				cmtLib.loginAsAdminCMT();
				// Click on Approval Management
				commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
				orderLib.clickonApprovalManagementTabs(data.get("Approval_Management_Tabs"));
				orderLib.clickOnTheEditLinkOfRequestorGroupNameEditLink(data.get("Requestor_Group"));
				orderLib.clickOnTheRequestorGroupNameTabs(data.get("Requestor_Group_Tab")); // clicking on checkout settings
				shipbLib.selectTheGroupPaymentOptions(data.get("Payment_Option"));
				orderLib.clickOnTheRequestorGroupNameTabs(data.get("Requestor_Group_Tab2"));// Clicking on Approval path
																							// settings
				orderLib.checkPaymentMethodCheckBox(); // Check payments method check box in A
				// add product to cart
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				// proceed to checkout
				orderLib.proceedToCheckout();
				cartLib.clickOnContinueButtonInAddInformtion();
				// continue till billing address
				orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line level Info
				orderLib.shippingBillPayContinueButton(); // Click continue on shipping address
				orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
				orderLib.shippingBillPayContinueButton(); // Billing address continue button
				// CREDIT CARD VERIFICATION
				orderLib.selectPaymentInfoMethodCreditCard(data.get("cardNumber"), data.get("Card_Name"), data.get("Month"),
						data.get("year"),data.get("PO_Number"));
				String summaryAmount1 = cartLib.getSummaryAmountInCart();
				orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount1);
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				// Click Save Changes
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
				cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				cmtLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
				// Payment_Option2
				shipbLib.SelectAllowedoptionspaymentcheckoutsettings(data.get("Payment_Option2"));
				cmtLib.loginAsAdminCMT();
				// Click on Approval Management
				// add product to cart
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				// proceed to checkout
				orderLib.proceedToCheckout();
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line level Info
				orderLib.shippingBillPayContinueButton(); // Click continue on shipping address
				orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
				orderLib.shippingBillPayContinueButton(); // Billing address continue button
				// CREDIT CARD VERIFICATION
				orderLib.selectPaymentInfoMethodCreditCard(data.get("cardNumber"), data.get("Card_Name"), data.get("Month"),
						data.get("year"),data.get("PO_Number"));
				String summaryAmount2 = cartLib.getSummaryAmountInCart();
				orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount2);
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				// override_payment_options
				cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_ON"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				// approval_process_setup;OFF
				cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.loginAsAdminCMT();
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				fnCloseTest();

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					System.out.println(e.getMessage());
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("CreditCardOverride", "SBP06", ReportStatus.strMethodName, intCounter,
						browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("CreditCardOverride", "SBP06", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}
