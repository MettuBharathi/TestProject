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

public class SBP01_StoredCardsLoginAsTest extends ShipBillPayLib {
	// #############################################################################################################
		// # Name of the Test : SBP01_StoredCardsLoginAs
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_SBP01(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP01_StoredCardsLoginAs", TestDataInsight,
					"Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
			
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP01_StoredCardsLoginAs", TestDataInsight,
						"Ship_Bill_Pay", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("StoredCardsLoginAs");
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
				// reference num creation---TU_StoredcardtestUser
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.loginAsAdminCMT();
				commonLib.searchProduct(data.get("SearchItem1"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				orderLib.proceedToCheckout();
				// continue button for all shipping sections
				orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line level Info
				orderLib.shippingBillPayContinueButton(); // Click continue on shipping address
				orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
				orderLib.shippingBillPayContinueButton(); // Billing address continue button
				orderLib.selectPaymentInfoMethodCreditCard(data.get("cardNumber"), data.get("cardName"), data.get("month"),
						data.get("year"),data.get("PO_Number"));
				shipbLib.ReviewrequisitionnumPage();
				shipbLib.getReferenceNum();
				String ReferenceNumber = shipbLib.getReferenceNum();
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				// first login
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.setCustomerLevelPermissionsOFF(data.get("customerPermissions"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
				cmtLib.AssigntheusertoServiceLevelShippingwithnodefault(data.get("menuName"), data.get("user_Permissions"),
						data.get("indexvalue"));
				cmtLib.clickupdateatDefaultShippingOption();
				cmtLib.loginAsAdminCMT();
				shipbLib.ApproveRequesition(data.get("toolsMenuName1"), data.get("dropDown1"), ReferenceNumber);
				shipbLib.PaymentandCardsTextverify(data.get("toolsMenuName2"), data.get("dropDown2"), data.get("tabName2"));
				commonLib.searchProduct(data.get("SearchItem1"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.clickCart();
				orderLib.proceedToCheckout();
				orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line level Info
				orderLib.shippingBillPayContinueButton(); // Click continue on shipping address
				orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
				orderLib.shippingBillPayContinueButton(); // Billing address continue button
				shipbLib.verifyTextatPaymentinfoCreditcard();
				orderLib.selectPaymentInfoMethodCreditCard(data.get("cardNumber"), data.get("cardName"), data.get("month"),
						data.get("year"),data.get("PO_Number"));
				orderLib.clickOnReviewOrderButton();
				String summaryAmount = cartLib.getSummaryAmountInCart();
				orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				// End user Login
				cmtLib.handleWelcomeToInsightBetaPopUp();
				cmtLib.clickLoginLink(data.get("Header"));
				cmtLib.loginAsEndUser(data.get("userName"), data.get("password"));
				shipbLib.UserProfCardVerification(data.get("toolsMenuName2"), data.get("dropDown2"), data.get("tabName2"));
				shipbLib.SelectstoredCardinRequistion(data.get("toolsMenuName1"), data.get("dropDown1"), data.get("Value"),
						ReferenceNumber);
				commonLib.searchProduct(data.get("SearchItem1"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.clickCart();
				orderLib.proceedToCheckout();
				orderLib.continueButtonOnAdditionalInformationSection();
				orderLib.clickContinueOnLLIAndShipBillPaySections();
				orderLib.clickOnReviewOrderButton();
				String summaryAmount1 = cartLib.getSummaryAmountInCart();
				orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount1);
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					System.out.println(e.getMessage());
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("StoredCardsLoginAs", "SBP01", ReportStatus.strMethodName, intCounter,
						browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("StoredCardsLoginAs", "SBP01", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}