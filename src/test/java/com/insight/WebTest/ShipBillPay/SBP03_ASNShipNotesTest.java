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

public class SBP03_ASNShipNotesTest extends ShipBillPayLib{
	// #############################################################################################################
		// # Name of the Test : SBP03_ASNShipNotes
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_SBP03(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP03_ASNShipNotes", TestDataInsight, "Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP03_ASNShipNotes", TestDataInsight,
						"Ship_Bill_Pay", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("ASNShipNotes");
				reporter.SuccessReport("Iteration Number : ",
						"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
								+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
						"");
				CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
				CartLib cartLib = new CartLib();
				OrderLib orderLib = new OrderLib();
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.setCustomerLevelPermissionsON(data.get("Customer_Permissions_ON"));
				cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				cmtLib.selectOptionInCheckoutSettings(data.get("Additional_Notifications"));
				commonLib.verifyDeafultMail(data.get("Default_Email"));
				cmtLib.clickupdateatDefaultShippingOption();
				cmtLib.clickOnloginAs();
				switchToChildWindow();
				commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Personalization_Menu"),
						data.get("Personalization_Menu_DD"));
				cartLib.clickCheckoutDefaults();
				commonLib.spinnerImage();
				cartLib.verifyShipmentNotificationInCheckoutDefaultsIsNotPresent();
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				orderLib.proceedToCheckout();
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				orderLib.shippingBillPayContinueButton();
				cartLib.verifyShipmentNotificationInCheckoutIsNotPresent();
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				cmtLib.navigateBackToCMT();
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.clickOnloginAs();
				switchToChildWindow();
				commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Personalization_Menu"),
						data.get("Personalization_Menu_DD"));
				cartLib.clickCheckoutDefaults();
				commonLib.spinnerImage();
				cartLib.verifyShipmentNotificationInCheckoutDefaults();
				cartLib.enterMailIdToNotificationFieldAndVerifyMessageNote(data.get("Default_Email"));
				cartLib.enterMailIdToNotificationFieldAndVerifyErrorMessage(data.get("Email"));
				cartLib.enterMailIdToNotificationFieldAndVerifyErrorMessage(data.get("Email1"));
				cartLib.enterMailIdToNotificationFieldAndVerifyMessageNote(data.get("Email2"));
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				orderLib.proceedToCheckout();
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				cartLib.shippingOptionsCarrierSelectionInCheckOut(data.get("Carrier_Option"));
				cartLib.selectShippingMeethod(data.get("Shipping_Method"));
				cartLib.verifyEmailAsInFormat(data.get("Email1_To_verify"));
				cartLib.verifyEmailAsInFormat(data.get("Email2_To_verify"));
				cartLib.verifyEmailAsInFormat(data.get("Email3_To_verify"));
				cartLib.clickAddAdditionalNotificationEmail();
				cartLib.enterInvalidAddtionalNotificationEmailAndVerifyErrorMessage(data.get("Invalid_Email1"));
				cartLib.enterInvalidAddtionalNotificationEmailAndVerifyErrorMessage(data.get("Invalid_Email2"));
				cartLib.enterValidAddtionalEmail(data.get("Valid_Email1"));
				cartLib.clickAddAdditionalNotificationEmail();
				cartLib.enterValidAddtionalEmail(data.get("Valid_Email2"));
				cartLib.shippingBillPayInCheckOut(data.get("Card_Number").toString(), data.get("Card_Name"),
						data.get("Month"), data.get("Year"), data.get("PONumber"));
				cartLib.verifyNotificationEmailInShippingAdresses(data.get("Email1_To_verify"));
				cartLib.verifyNotificationEmailInShippingAdresses(data.get("Email2_To_verify"));
				cartLib.verifyNotificationEmailInShippingAdresses(data.get("Email3_To_verify"));
				cartLib.verifyNotificationEmailInShippingAdresses(data.get("Valid_Email1"));
				cartLib.verifyNotificationEmailInShippingAdresses(data.get("Valid_Email2"));
				String summaryAmount = cartLib.getSummaryAmountInCart();
				orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					System.out.println(e.getMessage());
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ASNShipNotes", "SBP03", ReportStatus.strMethodName, intCounter,
						browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("ASNShipNotes", "SBP03", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}