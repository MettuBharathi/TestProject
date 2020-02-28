package com.insight.WebTest;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.ObjRepo.CartObj;
import com.insight.accelerators.ActionEngine;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class ShipBillPayTest extends ActionEngine {

	// #############################################################################################################
	// # Name of the Test : SBP02_FreightFree
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void SBP02_FreightFree(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP02_FreightFree", TestDataInsight, "Ship_Bill_Pay");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP02_FreightFree", TestDataInsight,
					"Ship_Bill_Pay", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
							+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
					"");
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			CartLib cartLib = new CartLib();
			OrderLib orderLib = new OrderLib();
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("ContactName"));
			cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
			cmtLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
			cmtLib.selectAllDesignatedShippingOptions(data.get("Default_Shipping_Option"));
			cmtLib.clickupdateatDefaultShippingOption();
			cmtLib.clickOnloginAs();
			switchToChildWindow();
			commonLib.searchProduct(data.get("Search_Item"));
			commonLib.addFirstDisplyedItemToCartAndVerify();
			commonLib.continueToShopping();
			commonLib.clickCart();
			commonLib.spinnerImage();
			String shipingCharges = cartLib.getShippingEstimateInCart();

			if (shipingCharges.equalsIgnoreCase(data.get("Shipping_Charges"))) {
				reporter.SuccessReport("Shipping Charges", "Shipping Field Existed  with $0.00 Amount", "");
			} else {
				reporter.failureReport("Shipping Charges", "Shipping Field Existed, amount is not $0.00", "");
			}
			orderLib.proceedToCheckout();
			cartLib.clickOnContinueButtonInAddInformtion();
			cartLib.shippingOptionsCarrierSelectionInCheckOut(data.get("Carrier_Option"));
			String shippingCost = cartLib.getShippingMethodCost(data.get("Shiping_Method"));
			System.out.println("shipingCharges" + shippingCost);
			if (shippingCost.equalsIgnoreCase(data.get("Shipping_Charges"))) {
				reporter.SuccessReport("Shipping Charges", "Shipping Field Existed  with $0.00 Amount", "");
			} else {
				reporter.failureReport("Shipping Charges", "Shipping Field Existed, amount is not $0.00", "");
			}
			commonLib.clickLogOutLink(data.get("Logout_Header"));
			cmtLib.navigateBackToCMT();
			cmtLib.DeselectAllDesignatedShippingOptions();
			fnCloseTest();
		}
	}

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
	public void SBP01_StoredCardsLoginAs(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP01_StoredCardsLoginAs", TestDataInsight,
				"Ship_Bill_Pay");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP01_StoredCardsLoginAs", TestDataInsight,
					"Ship_Bill_Pay", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
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
			fnCloseTest();
		}
	}

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
	public void SBP05_CarrierHeavy(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP05_CarrierHeavy", TestDataInsight, "Ship_Bill_Pay");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP05_CarrierHeavy", TestDataInsight,
					"Ship_Bill_Pay", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
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
			fnCloseTest();

		}
	}

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
	public void SBP03_ASNShipNotes(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP03_ASNShipNotes", TestDataInsight, "Ship_Bill_Pay");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP03_ASNShipNotes", TestDataInsight,
					"Ship_Bill_Pay", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
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
			fnCloseTest();
		}
	}

	// #############################################################################################################
	// # Name of the Test : SBP09_SharedUserWGSwitch
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void SBP09_SharedUserWGSwitch(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP09_SharedUserWGSwitch", TestDataInsight,
				"Ship_Bill_Pay");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP09_SharedUserWGSwitch", TestDataInsight,
					"Ship_Bill_Pay", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
							+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
					"");
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			CartLib cartLib = new CartLib();
			OrderLib orderLib = new OrderLib();
			ShipBillPayLib shipbLib = new ShipBillPayLib();

			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("ContactName"));
			cmtLib.clickOnloginAs();
			switchToChildWindow();
			cmtLib.handleWelcomeToInsightBetaPopUp();
			shipbLib.verifyWebGroupIsUS();
			shipbLib.clickHere();
			cmtLib.handleWelcomeToInsightBetaPopUp();
			shipbLib.verifyWEbsiteIsCannada();
			commonLib.searchProduct(data.get("Search_Item"));
			commonLib.addFirstDisplyedItemToCartAndVerify();
			 String partNumber1=cartLib.getPartNumber();
			 commonLib.continueToShopping();
			 commonLib.clickCart();
			 cartLib.verifyItemInCart(partNumber1);
			shipbLib.verifyPriceIsCAD(data.get("CANDAIAN_DOLLAR"));
			shipbLib.getSummaryAmountsInCart(data.get("SubTotal"), data.get("Total"));
			orderLib.proceedToCheckout();
			shipbLib.addAddtionalInfo(data.get("Name"), data.get("Phone"), data.get("Email"));
			cartLib.clickOnContinueButtonInAddInformtion();
			commonLib.clickLogOutLink(data.get("Logout_Header"));
			fnCloseTest();

		}
	}

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
	public void SBP07_ThirdPartyCarrierAccount(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP07_ThirdPartyCarrierAccount", TestDataInsight,
				"Ship_Bill_Pay");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP07_ThirdPartyCarrierAccount", TestDataInsight,
					"Ship_Bill_Pay", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
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
			fnCloseTest();

		}

	}

	// #############################################################################################################
	// # Name of the Test : SBP12_SharedUserValidateStoredcardOption
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void SBP12_SharedUserValidateStoredcardOption(int StartRow, String EndRow, boolean nextTestJoin)
			throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP12_SharedUserValidateStoredcardOption",
				TestDataInsight, "Ship_Bill_Pay");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP12_SharedUserValidateStoredcardOption",
					TestDataInsight, "Ship_Bill_Pay", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
							+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
					"");
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			CartLib cartLib = new CartLib();
			OrderLib orderLib = new OrderLib();
			ShipBillPayLib shipbLib = new ShipBillPayLib();
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("ContactName"));
			cmtLib.clickOnSharedLinkUserInInformationTab();
			cmtLib.userLoginVerification(data.get("ContactName"));
			commonLib.searchProduct(data.get("SearchItem"));
			commonLib.addFirstDisplyedItemToCartAndVerify();
			String partNumber1 = cartLib.getPartNumber();
			commonLib.continueToShopping();
			commonLib.clickCart();
			cartLib.verifyItemInCart(partNumber1);
			orderLib.proceedToCheckout();
			shipbLib.addAddtionalInfo(data.get("Name"), data.get("Phone"), data.get("Email"));
			cartLib.clickOnContinueButtonInAddInformtion();
			orderLib.clickContinueOnLLIAndShipBillPaySections();
			// Verification

			commonLib.clickLogOutLink(data.get("Logout_Header"));
			fnCloseTest();

		}

	}

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
	public void SBP04_AvilableShippingAddress(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP04_AvilableShippingAddress", TestDataInsight,
				"Ship_Bill_Pay");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP04_AvilableShippingAddress", TestDataInsight,
					"Ship_Bill_Pay", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
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
			fnCloseTest();

		}
	}

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
	public void SBP08_SaveOrderTemplateIPS(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP08_SaveOrderTemplateIPS", TestDataInsight,
				"Ship_Bill_Pay");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP08_SaveOrderTemplateIPS", TestDataInsight,
					"Ship_Bill_Pay", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
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
			fnCloseTest();
		}
	}

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
	public void SBP06_CreditCardOverride(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP06_CreditCardOverride", TestDataInsight,
				"Ship_Bill_Pay");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP06_CreditCardOverride", TestDataInsight,
					"Ship_Bill_Pay", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
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

		}
	}

	// #############################################################################################################
	// # Name of the Test : SBP11_IPSOrder
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void SBP11_IPSOrder(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP11_IPSOrder", TestDataInsight, "Ship_Bill_Pay");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP11_IPSOrder", TestDataInsight, "Ship_Bill_Pay",
					intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
							+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
					"");
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			CartLib cartLib = new CartLib();
			OrderLib orderLib = new OrderLib();
			ShipBillPayLib shipbLib = new ShipBillPayLib();
			navigateTo(data.get("URL"));
			shipbLib.clickOnShop(data.get("Header"));
			shipbLib.clickOnStateAndCentralGovernment(data.get("Contract"));
			shipbLib.selectContract(data.get("State_Contract"), data.get("Country"), data.get("Contract_Option"));
			shipbLib.clickonBrowseProducts();
			commonLib.spinnerImage();
			shipbLib.verifyProducts(data.get("Contract_Option"));
			shipbLib.clickonchange();
			shipbLib.selectContractOption(data.get("Contract_Option"), data.get("Contract_Option1"));
			shipbLib.clickonBrowseProducts();
			commonLib.spinnerImage();
			shipbLib.verifyProducts(data.get("Contract_Option1"));
			commonLib.addFirstDisplyedItemToCartAndVerify();
			commonLib.continueToShopping();
			commonLib.clickCart();
			cmtLib.handleWelcomeToInsightBetaPopUp();
			// proceed to checkout
			orderLib.proceedToCheckout();
			shipbLib.verifyHomepgaeLogin(data.get("Login_Header"));
			fnCloseTest();
		}
	}
}
