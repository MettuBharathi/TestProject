package com.insight.WebTest;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.RequisitionProcessingLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class RequisitionProcessingTest extends CMTLib {
	CommonLib commonLib = new CommonLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodLib = new ProductDisplayInfoLib();
	OrderLib orderLib = new OrderLib();
	RequisitionProcessingLib ReqLib = new RequisitionProcessingLib();

	// #############################################################################################################
	// # Name of the Test : REQ08_TestRules
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : September 2019
	// # Description : To Test Place Order basic
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void REQ08_TestRules(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "REQ08_TestRules", TestData, "Requisition_Processing");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("REQ08_TestRules", TestData,
					"Requisition_Processing", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
							+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
					"");
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			RequisitionProcessingLib reqProcLib = new RequisitionProcessingLib();
			OrderLib orderLib = new OrderLib();

			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("ContactName"));
			cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
			cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Disable_Permission"));
			cmtLib.clickOnloginAs();
			switchToChildWindow();
			commonLib.spinnerImage();
			commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));

			// Verify Verify Approval Management Page - is Loaded
			reqProcLib.verifyApprovalManagementPage();

			// Click on - Requestor Group Users
			orderLib.clickonApprovalManagementTabs(data.get("Approval_Management_Tabs"));

			// Click on TestRules Edit link button
			reqProcLib.clickTestRulesLink();

			commonLib.spinnerImage();

			// clicking on Optional Rules
			reqProcLib.clickOptionalRules();

			// create rules
			reqProcLib.createRule(data.get("Cart_Type_Standard"), data.get("Min_Amt_0"), data.get("Max_Amt_99"),
					data.get("Result_Path1"), "1");
			Thread.sleep(1000);
			reqProcLib.createRule(data.get("Cart_Type_NonStandard"), data.get("Min_Amt_100"), data.get("Max_Amt_199"),
					data.get("Result_Path2"), "2");
			Thread.sleep(1000);
			reqProcLib.createRule(data.get("Cart_Type_Any"), data.get("Min_Amt_200"), data.get("Max_Amt_299"),
					data.get("Result_Path3"), "3");

			// Test Approval Rules
			reqProcLib.clickTestApprovalRulesLink();

			// Verify Rules
			reqProcLib.verifyRules(data.get("Amount_0"), data.get("Cart_Type_Standard"), data.get("Result_Path1"),
					false);
			reqProcLib.verifyRules(data.get("Amount_110"), data.get("Cart_Type_NonStandard"), data.get("Result_Path2"),
					false);
			reqProcLib.verifyRules(data.get("Amount_210"), data.get("Cart_Type_Standard"), data.get("Result_Path3"),
					false);
			reqProcLib.verifyRules(data.get("Amount_210"), data.get("Cart_Type_NonStandard"), data.get("Result_Path3"),
					false);
			reqProcLib.verifyRules(data.get("Amount_110"), data.get("Cart_Type_Standard"), data.get("Result_Path1"),
					false);

			// close test approvals window
			reqProcLib.closeTestApprovalRulesWindow();

			// Delete Created Rules
			reqProcLib.clickDeleteRule("First");
			reqProcLib.clickDeleteRule("Second");
			reqProcLib.clickDeleteRule("Third");

			// back to Requestor Group Search
			reqProcLib.backtoReqSearch();
			Thread.sleep(5000);

			// Click on TestRules Edit link button
			reqProcLib.clickSmartTrackerRulesLink();

			Thread.sleep(2000);

			// clicking on Optional Rules
			reqProcLib.clickOptionalRules();

			// select with rule listing factor dropdown
			reqProcLib.selectWithRuleListingFactor(data.get("CreateRule_WithListOption"));

			// click on ADD ROUTE button
			reqProcLib.clickAdRouteButton();

			// select HDLList after ADD ROUTE click
			reqProcLib.selectHDLListOption(data.get("SmartTracker_HDLLIstOption"));

			// click ADD RULE button
			reqProcLib.addRuleWithList(data.get("List1_Option"), data.get("Cart_Type_Standard"), data.get("Min_Amt_0"),
					data.get("Max_Amt_99"), data.get("Result_Path1"), "1");

			// Test Approval Rules
			reqProcLib.clickTestApprovalRulesLink();

			// Verify Rules
			reqProcLib.verifyRules(data.get("Amount_0"), data.get("Cart_Type_Standard"), data.get("Result_Path1"),
					false);
			reqProcLib.verifyRules(data.get("Amount_110"), data.get("Cart_Type_NonStandard"),
					data.get("Result_NoApprovalPath"), true);
			reqProcLib.verifyRules(data.get("Amount_210"), data.get("Cart_Type_Standard"), data.get("Result_Path3"),
					false);
			reqProcLib.verifyRules(data.get("Amount_210"), data.get("Cart_Type_NonStandard"), data.get("Result_Path3"),
					false);
			reqProcLib.verifyRules(data.get("Amount_110"), data.get("Cart_Type_Standard"),
					data.get("Result_NoApprovalPath"), true);

			// close test approvals window
			reqProcLib.closeTestApprovalRulesWindow();

			// Delete Created Rules
			reqProcLib.clickDeleteRule("WithRule");

			// Delete Routing List choice
			reqProcLib.deleteRoutingOption();
			commonLib.clickLogOutLink(data.get("Logout"));
			fnCloseTest();

		}
	}

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void REQ07_CreateDeleteRGRules(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "REQ07_CreateDeleteRGRules", TestData,
				"Requisition_Processing");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("REQ07_CreateDeleteRGRules", TestData,
					"Requisition_Processing", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
							+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
					"");
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			RequisitionProcessingLib reqProcLib = new RequisitionProcessingLib();
			OrderLib orderLib = new OrderLib();

			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("ContactName"));
			cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
			cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Disable_Permission"));
			cmtLib.clickOnloginAs();
			switchToChildWindow();
			commonLib.spinnerImage();
			commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));

			// Verify Verify Approval Management Page - is Loaded
			reqProcLib.verifyApprovalManagementPage();

			// Click on - Requestor Group Users
			orderLib.clickonApprovalManagementTabs(data.get("Approval_Management_Tabs"));
			Thread.sleep(2000);

			// Click on Create Rules Edit link button
			reqProcLib.clickCreateRulesLink();
			Thread.sleep(2000);

			// clicking on Optional Rules
			reqProcLib.clickOptionalRules();
			Thread.sleep(1000);

			reqProcLib.createRule(data.get("Cart_Type_Standard"), data.get("Min_Amt_0"), data.get("Max_Amt_99"),
					data.get("Result_Path1"), "1");
			// verify success add message
			reqProcLib.verifySuccessMsg();
			Thread.sleep(1000);
			reqProcLib.createRule(data.get("Cart_Type_NonStandard"), data.get("Min_Amt_100"), data.get("Max_Amt_199"),
					data.get("Result_Path2"), "2");

			// verify success add message
			reqProcLib.verifySuccessMsg();

			Thread.sleep(1000);
			reqProcLib.createRule(data.get("Cart_Type_Any"), data.get("Min_Amt_200"), data.get("Max_Amt_299"),
					data.get("Result_Path3"), "3");

			// verify success add message
			reqProcLib.verifySuccessMsg();


			// Delete Created Rules
			reqProcLib.clickDeleteRule("First");
			reqProcLib.clickDeleteRule("Second");
			reqProcLib.clickDeleteRule("Third");

			// back to Requestor Group Search
			reqProcLib.backtoReqSearchForCreateRules();

			Thread.sleep(3000);
			// Click on Create Rules Edit link button
			reqProcLib.clickCreateRulesLink();

			Thread.sleep(2000);
			// clicking on Optional Rules
			reqProcLib.clickOptionalRules();

			// select with rule listing factor dropdown
			reqProcLib.selectWithRuleListingFactor(data.get("CreateRule_WithListOption"));

			// click on ADD ROUTE button
			reqProcLib.clickAdRouteButton();

			// select HDLList after ADD ROUTE click
			reqProcLib.selectHDLListOption(data.get("SmartTracker_HDLLIstOption"));

			// click ADD RULE button
			reqProcLib.addRuleWithList(data.get("List1_Option"), data.get("Cart_Type_Standard"), data.get("Min_Amt_0"),
					data.get("Max_Amt_99"), data.get("Result_Path1"), "1");

			// verify success add message
			reqProcLib.verifySuccessMsg();

			// Delete Created Rules
			reqProcLib.clickDeleteRule("WithRule");

			// Delete Routing List choice
			reqProcLib.deleteRoutingOption();

			commonLib.clickLogOutLink(data.get("Logout"));
			fnCloseTest();

		}
	}

	// #############################################################################################################
	// # Name of the Test : REQ01_EndToEnd
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : October 2019
	// # Description : This Test is used to Test Requisition end to end flow
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void REQ01_EndToEnd(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "REQ01_EndToEnd", TestData, "Requisition_Processing");

		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("REQ01_EndToEnd", TestData,
					"Requisition_Processing", intCounter);

			// Test Steps execution
			fnOpenTest();

			// Login to CMT enable Display Additional Notes during the
			// transaction process,
			// Allow File Upload during Checkout,Display Invoice Notes during
			// the transaction process settings at web group level.
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			// LogIN with 1st User
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName")); // Req1
			cmtLib.setPermissions(data.get("menuName"), data.get("userPermissions"));
			cmtLib.loginAsAdminCMT();

			searchLib.searchInHomePage(data.get("SearchItem"));

			// Add a item to cart >> proceed To Checkout >> place order >>
			// Verify the review order details,Receipt Order And Date
			commonLib.addFirstDisplyedItemToCartAndVerify();
			orderLib.continueToCheckOutOnAddCart();
			// Add 2nd product to the cart
			searchLib.searchInHomePage(data.get("SearchItem1"));
			commonLib.addFirstDisplyedItemToCartAndVerify();
			orderLib.continueToCheckOutOnAddCart();
			// Select web requestor group name from dropdown
			ReqLib.selectRequestorGroupName(data.get("ReqName"));
			orderLib.proceedToCheckout();

			orderLib.continueButtonOnAdditionalInformationSection();
			orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line
														// level Info
			ReqLib.clearPhoneNumber();
			orderLib.shippingBillPayContinueButton(); // Click continue on
														// shipping address
			orderLib.shippingOptionsCarrierSelection(); // Click continue on
														// shipping options
			orderLib.shippingBillPayContinueButton(); // Billing address
														// continue button
			orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),
					data.get("Month"), data.get("Year"),data.get("PO_Number"));
			orderLib.clickOnReviewRequisitionButton();
			orderLib.verifyPlaceOrderLabel();
			orderLib.clickOnPlaceRequisitionButton();
			// Verify Receipt
			Thread.sleep(4000);
			orderLib.verifyReceiptVerbiage();
			String RefNumber = orderLib.getTextfromReferenceNumber();

			// LogIN with 2nd User
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1")); // app1
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
			orderLib.verifyandClickonRefLink(RefNumber);
			orderLib.verifyApprovalManagmentandClickUpdate();

			// LogIN with 3rd User
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2")); // app2
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
			orderLib.verifyandClickonRefLink(RefNumber);
			orderLib.verifyApprovalManagmentandClickUpdate();

			// LogIN with 2nd User in order to verify approved reference link
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1")); // app1
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
			ReqLib.verifyorderNumLinkinReqHistoryPage(RefNumber);

			// LogIN with 5th User
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname3"), data.get("ContactName3")); // app4
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
			orderLib.verifyandClickonRefLink(RefNumber);
			ReqLib.enterPOandPORelease(data.get("PONum"), data.get("PORelese"));
			// get PO and POrelase numbers
			String PO = data.get("PONum");
			String PORelease = data.get("PORelese");
			ReqLib.enterPOandPOReleaseandUpdate();
			// LogIN with 1st User
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName")); // req1
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown1")); // Order
																											// history
																											// page
			// Verifying PO Numbers
			ReqLib.verifyPOandPORelease(RefNumber, PO, PORelease);
			// Logout
			commonLib.clickLogOutLink(data.get("header1"));
			fnCloseTest();
		}
	}

	// #############################################################################################################
	// # Name of the Test : REQ03_DenyRequisition
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : October 2019
	// # Description : This Test is used to Test Requisition end to end flow
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void REQ03_DenyRequisition(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "REQ03_DenyRequisition", TestData,
				"Requisition_Processing");

		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("REQ03_DenyRequisition", TestData,
					"Requisition_Processing", intCounter);

			// Test Steps execution
			fnOpenTest();

			// Login to CMT enable Display Additional Notes during the
			// transaction process,
			// Allow File Upload during Checkout,Display Invoice Notes during
			// the transaction process settings at web group level.
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			// Login with requestor
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("lnameEmailUname"), data.get("ContactName"));
			cmtLib.loginAsAdminCMT();

			// search with HP Desktop
			searchLib.searchInHomePage(data.get("SearchItem"));
			commonLib.addFirstDisplyedItemToCartAndVerify();
			orderLib.continueToCheckOutOnAddCart();
			// Select web requestor group name from dropdown
			ReqLib.selectRequestorGroupName(data.get("ReqName"));
			orderLib.proceedToCheckout();
			orderLib.continueButtonOnAdditionalInformationSection();
			orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line
														// level Info
			ReqLib.clearPhoneNumber();
			orderLib.shippingBillPayContinueButton(); // Click continue on
														// shipping address
			orderLib.shippingOptionsCarrierSelection(); // Click continue on
														// shipping options
			orderLib.shippingBillPayContinueButton(); // Billing address
														// continue button
			orderLib.enterCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"),
					data.get("Year"), data.get("PONumber"));
			orderLib.clickOnReviewRequisitionButton();
			orderLib.verifyPlaceOrderLabel();

			orderLib.clickOnPlaceRequisitionButton();
			// Verify Receipt
			Thread.sleep(4000);
			orderLib.verifyReceiptVerbiage();
			String RefNumber = orderLib.getTextfromReferenceNumber();
			// LogIN with 2nd User
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
			orderLib.verifyandClickonRefLink(RefNumber);
			orderLib.verifyApprovalManagmentandClickUpdate();
			// Login with 3rd User
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2"));
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
			orderLib.verifyandClickonRefLink(RefNumber);
			ReqLib.verifyDeneyedStatusandUpdate(data.get("text"));
			// Login with 4th User
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname3"), data.get("ContactName3"));
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
			ReqLib.verifyDenyedstatusRefNum(data.get("status"), RefNumber);
			// Verify deneyed request reference number is displayed or not
			ReqLib.verifyorderNumLinkinDeneyedorders(RefNumber);

			fnCloseTest();
		}
	}

	// #############################################################################################################
	// # Name of the Test : REQ06_NOForceRequestorEnterCC
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : October 2019
	// # Description : This Test is used to Test Requisition end to end flow
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void REQ06_NOForceRequestorEnterCC(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "REQ06_NOForceRequestorEnterCC", TestData,
				"Requisition_Processing");

		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("REQ06_NOForceRequestorEnterCC", TestData,
					"Requisition_Processing", intCounter);

			// Test Steps execution
			fnOpenTest();

			// Login to CMT enable Display Additional Notes during the
			// transaction process,
			// Allow File Upload during Checkout,Display Invoice Notes during
			// the transaction process settings at web group level.
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			// LogIN with 1st User
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName")); // uftrequestor@mailnator.com
			cmtLib.setPermissions(data.get("menuName"), data.get("userPermissions"));
			cmtLib.loginAsAdminCMT();

			searchLib.searchInHomePage(data.get("SearchItem"));

			// Add a item to cart >> proceed To Checkout
			commonLib.addFirstDisplyedItemToCartAndVerify();
			orderLib.continueToCheckOutOnAddCart();

			// Select web requestor group name from dropdown
			ReqLib.selectRequestorGroupName(data.get("ReqName"));
			orderLib.proceedToCheckout();

			orderLib.continueButtonOnAdditionalInformationSection();
			orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line
														// level Info
			ReqLib.clearPhoneNumber();
			orderLib.shippingBillPayContinueButton(); // Click continue on
														// shipping address
			orderLib.shippingOptionsCarrierSelection(); // Click continue on
														// shipping options
			orderLib.shippingBillPayContinueButton(); // Billing address
														// continue button
			// Review requisition without entering payment info details and
			// observe the validation messages
			orderLib.clickOnReviewRequisitionButton();
			ReqLib.verifyCreditCardErrorMessages();
			// Login with the same user
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			// LogIN with 1st User
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName")); // uftrequestor@mailnator.com
			cmtLib.setPermissionsToDisable(data.get("menuName"), data.get("userPermissions"));
			cmtLib.loginAsAdminCMT();

			searchLib.searchInHomePage(data.get("SearchItem"));

			// Add a item to cart >> proceed To Checkout >> place order >>
			// Verify the review order details,Receipt Order And Date
			commonLib.addFirstDisplyedItemToCartAndVerify();
			orderLib.continueToCheckOutOnAddCart();

			// Select web requestor group name from dropdown
			ReqLib.selectRequestorGroupName(data.get("ReqName"));
			orderLib.proceedToCheckout();

			orderLib.continueButtonOnAdditionalInformationSection();
			orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line
														// level Info
			ReqLib.clearPhoneNumber();
			orderLib.shippingBillPayContinueButton(); // Click continue on
														// shipping address
			orderLib.shippingOptionsCarrierSelection(); // Click continue on
														// shipping options
			orderLib.shippingBillPayContinueButton(); // Billing address
														// continue button
			// Review requisition without entering payment info details
			orderLib.clickOnReviewRequisitionButton();
			orderLib.verifyPlaceOrderLabel();
			orderLib.clickOnPlaceRequisitionButton();
			// Verify Receipt
			Thread.sleep(4000);
			orderLib.verifyReceiptVerbiage();
			String RefNumber = orderLib.getTextfromReferenceNumber();

			// LogIN with 2nd User for Approve Requisition
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1")); // app1
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
			orderLib.verifyandClickonRefLink(RefNumber);
			orderLib.verifyApprovalManagmentandClickUpdate();

			// LogIN with 3rd User for Approve Requisition
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2")); // app2
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
			orderLib.verifyandClickonRefLink(RefNumber);
			orderLib.verifyApprovalManagmentandClickUpdate();

			// LogIN with 3rd User for final Approval
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname3"), data.get("ContactName3")); // app3
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
			orderLib.verifyandClickonRefLink(RefNumber);
			orderLib.verifyApprovalManagmentandClickUpdate();
			ReqLib.enterNewCardInformation(data.get("cardtype"), data.get("cardNum"), data.get("cardName"),
					data.get("month"), data.get("year"));
			orderLib.verifyOrderNumberExists(RefNumber);
			ReqLib.verifyApproveRequisitionStatus();
			// Logout
			commonLib.clickLogOutLink(data.get("header1"));

			fnCloseTest();
		}
	}

	// #############################################################################################################
	// # Name of the Test : REQ05_NOForceApprovalPath
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : October 2019
	// # Description : This Test is used to Test Requisition end to end flow
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void REQ05_NOForceApprovalPath(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "REQ05_NOForceApprovalPath", TestData,
				"Requisition_Processing");

		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("REQ05_NOForceApprovalPath", TestData,
					"Requisition_Processing", intCounter);

			// Test Steps execution
			fnOpenTest();

			// Login to CMT enable Display Additional Notes during the
			// transaction process,
			// Allow File Upload during Checkout,Display Invoice Notes during
			// the transaction process settings at web group level.
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			// LogIN with 1st User
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName")); // uftrequestor@mailnator.com
			cmtLib.loginAsAdminCMT();
			orderLib.clickOnSideMenuSelectAccountToolOptions(data.get("toolsMenuName"), data.get("dropDown"));
			ReqLib.verifyApprovalMangmntPagefromSideMenu();
			ReqLib.clickOnRequestorGroupID(data.get("ReqName"));
			ReqLib.verifyCreate_EditRequestoreGrpGeader();
			ReqLib.verifyApproverPathSettings();
			ReqLib.verifyApproverPathSettingsWithRadioCheck();

			searchLib.searchInHomePage(data.get("SearchItem"));

			// Add a item to cart >> proceed To Checkout >> place order >>
			// Verify the review order details,Receipt Order And Date
			commonLib.addFirstDisplyedItemToCartAndVerify();
			orderLib.continueToCheckOutOnAddCart();

			// Select web requestor group name from dropdown
			ReqLib.selectRequestorGroupName(data.get("ReqName"));
			orderLib.proceedToCheckout();

			orderLib.continueButtonOnAdditionalInformationSection();
			orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line
														// level Info
			ReqLib.clearPhoneNumber();
			orderLib.shippingBillPayContinueButton(); // Click continue on
														// shipping address
			orderLib.shippingOptionsCarrierSelection(); // Click continue on
														// shipping options
			orderLib.shippingBillPayContinueButton(); // Billing address
														// continue button
			orderLib.enterCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"),
					data.get("Year"), data.get("PONumber"));
			orderLib.clickOnReviewRequisitionButton();
			orderLib.verifyPlaceOrderLabel();
			orderLib.clickOnPlaceRequisitionButton();
			// Verify Receipt
			Thread.sleep(4000);
			orderLib.verifyReceiptVerbiage();
			String RefNumber = orderLib.getTextfromReferenceNumber();

			// LogIN with 2nd User for Approve Requisition
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2")); // QTPapp1
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName1"), data.get("dropDown1"));
			orderLib.verifyandClickonRefLink(RefNumber);
			orderLib.verifyApprovalManagmentandClickUpdate();

			// LogIN with 2nd User for Approve Requisition
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname3"), data.get("ContactName3")); // QTPapp2
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName1"), data.get("dropDown1"));
			orderLib.verifyandClickonRefLink(RefNumber);
			orderLib.verifyApprovalManagmentHeaderandClickonUpdateLink();

			// Logout
			commonLib.clickLogOutLink(data.get("header1"));

			fnCloseTest();
		}
	}

	// #############################################################################################################
		// # Name of the Test : REQ02_AllowApproversEdit
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : October 2019
		// # Description : This Test is used to Test Requisition end to end flow
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// #############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void REQ02_AllowApproversEdit(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "REQ02_AllowApproversEdit", TestData,
					"Requisition_Processing");

			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				// initilizing libraries and testdata
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("REQ02_AllowApproversEdit", TestData,
						"Requisition_Processing", intCounter);

				// Test Steps execution
				fnOpenTest();

				// Login to CMT enable Display Additional Notes during the
				// transaction process,
				// Allow File Upload during Checkout,Display Invoice Notes during
				// the transaction process settings at web group level.
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				// LogIN with 1st User
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName")); // QTPappAdmin
				cmtLib.setPermissions(data.get("menuName"), data.get("userPermissions"));
				cmtLib.setPermissionsToDisable(data.get("menuName"), data.get("userPermissions1"));
				cmtLib.loginAsAdminCMT();
				orderLib.clickOnSideMenuSelectAccountToolOptions(data.get("toolsMenuName"), data.get("dropDown"));
				ReqLib.verifyApprovalMangmntPagefromSideMenu();
				ReqLib.clickOnRequestorGroupID(data.get("ReqName"));
				ReqLib.verifyCreate_EditRequestoreGrpGeader();
				ReqLib.verifyApproverPathSettings();
				ReqLib.getCheckboxesCount();
				ReqLib.verifySuccessMessageForApproverPathSettings();

				// Login with 2nd user
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1")); // QTPReq1
				cmtLib.setPermissions(data.get("menuName"), data.get("userPermissions1")); // User
																							// Requires
																							// Approval
				cmtLib.loginAsAdminCMT();
				searchLib.searchInHomePage(data.get("SearchItem"));
				searchLib.clickallDesktopsLink();
				// Add a item to cart >> proceed To Checkout >> place order >>
				// Verify the review order details,Receipt Order And Date
				commonLib.addFirstDisplyedItemToCartAndVerify();
				orderLib.continueToCheckOutOnAddCart();

				// Select web requestor group name from dropdown
				ReqLib.selectRequestorGroupName(data.get("ReqName"));
				orderLib.proceedToCheckout();

				orderLib.continueButtonOnAdditionalInformationSection();
				orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line
															// level Info
				ReqLib.clearPhoneNumber();
				orderLib.shippingBillPayContinueButton(); // Click continue on
															// shipping address
				orderLib.shippingOptionsCarrierSelection(); // Click continue on
															// shipping options
				orderLib.shippingBillPayContinueButton(); // Billing address
															// continue button
				// MX card details
				orderLib.enterCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"),
						data.get("Year"), data.get("PONumber"));
				orderLib.clickOnReviewRequisitionButton();
				orderLib.verifyPlaceOrderLabel();
				orderLib.clickOnPlaceRequisitionButton();
				// Verify Receipt
				Thread.sleep(4000);
				orderLib.verifyReceiptVerbiage();
				String RefNumber = orderLib.getTextfromReferenceNumber();

				
				// Login with 3rd user
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2")); // QTPapp1
				cmtLib.setPermissions(data.get("menuName"), data.get("userPermissions2"));// Allow
																							// Approver
																							// to
																							// Edit
																							// Requisitions
				cmtLib.loginAsAdminCMT();
				searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName1"), data.get("dropDown1"));
				orderLib.verifyandClickonRefLink(RefNumber);
				ReqLib.verifyApprovalManagmentPage();
				ReqLib.verifyAllLinksInApprovalManagmntPage();
				ReqLib.clickUpdateInApprovalManagmentPage();
				orderLib.verifyOrderNumberExists(RefNumber);
				ReqLib.verifyApproveRequisitionStatus();

				// Login with 4th user
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname3"), data.get("ContactName3")); // QTPapp2
				cmtLib.setPermissions(data.get("menuName"), data.get("userPermissions2"));
				cmtLib.loginAsAdminCMT();
				searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName1"), data.get("dropDown1"));
				orderLib.verifyandClickonRefLink(RefNumber);
				ReqLib.verifyApprovalManagmentPage();
				ReqLib.clickEditPaymentsAndVerify(data.get("cardType"));
				ReqLib.enterPOandPORelease(data.get("PONum"), data.get("PORelese"));
				ReqLib.clickEditShipping();
				//ReqLib.clickEditBillingAddress();
				ReqLib.clickCartContents(data.get("QtyNum"));
				ReqLib.verifyQuantityInCartContents();
				ReqLib.clickUpdateInApprovalManagmentPage();
				ReqLib.enterNewCardInformation(data.get("cardtype"), data.get("cardNum"), data.get("cardName"),
						data.get("month"), data.get("year"));
				ReqLib.verifyApproveRequisitionStatus();
				// Login with 1st User
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName")); // QTPReq1
				cmtLib.loginAsAdminCMT();
				searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
				ReqLib.verifyApprovalMangmntPagefromSideMenu();
				ReqLib.clickOnRequestorGroupID(data.get("ReqName"));
				ReqLib.verifyCreate_EditRequestoreGrpGeader();
				ReqLib.verifyApproverPathSettings();
				ReqLib.UnCheckAllCheckboxesCount();
				ReqLib.verifySuccessMessageForApproverPathSettings();

				// Logout
				commonLib.clickLogOutLink(data.get("header1"));

				fnCloseTest();
			}
		}
	// #############################################################################################################
			// # Name of the Test : REQ04_CancelRequisition
			// # Migration Author : Cigniti Technologies
			// #
			// # Date of Migration : October 2019
			// # Description : This Test is used to Test Requisition end to end flow
			// # Parameters : StartRow ,EndRow , nextTestJoin
			// #
			// #############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void REQ04_CancelRequisition(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "REQ04_CancelRequisition", TestData,
						"Requisition_Processing");

				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

					// initilizing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("REQ04_CancelRequisition", TestData,
							"Requisition_Processing", intCounter);

					// Test Steps execution
					fnOpenTest();
					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					// LogIN with 1st User
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName")); //uftrequestor@mailnator.com
					cmtLib.loginAsAdminCMT();
					orderLib.clickOnSideMenuSelectAccountToolOptions(data.get("toolsMenuName"), data.get("dropDown"));
					ReqLib.verifyApprovalMangmntPagefromSideMenu();
					ReqLib.clickOnRequestorGroupID(data.get("ReqName1"));
					ReqLib.verifyCreate_EditRequestoreGrpGeader();
					ReqLib.verifyandclickManagerRequestors(data.get("tabName"));
					ReqLib.selectRequestorgrpNameFromList(data.get("ReqName UFT"));
					
					//Login with 4th User
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1")); // QTPReq1
					cmtLib.loginAsAdminCMT();
					searchLib.searchInHomePage(data.get("SearchItem"));
					// Add a item to cart >> proceed To Checkout >> place order >>
					// Verify the review order details,Receipt Order And Date
					commonLib.addFirstDisplyedItemToCartAndVerify();
					orderLib.continueToCheckOutOnAddCart();

					// Select web requestor group name from dropdown
					ReqLib.selectRequestorGroupName(data.get("ReqName"));
					orderLib.proceedToCheckout();

					orderLib.continueButtonOnAdditionalInformationSection();
					orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line
																// level Info
					ReqLib.clearPhoneNumber();
					orderLib.shippingBillPayContinueButton(); // Click continue on
																// shipping address
					orderLib.shippingOptionsCarrierSelection(); // Click continue on
																// shipping options
					orderLib.shippingBillPayContinueButton(); // Billing address
																// continue button
					// MX card details
					orderLib.enterCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"),
							data.get("Year"), data.get("PONumber"));
					orderLib.clickOnReviewRequisitionButton();
					orderLib.verifyPlaceOrderLabel();
					orderLib.clickOnPlaceRequisitionButton();
					// Verify Receipt
					Thread.sleep(4000);
					orderLib.verifyReceiptVerbiage();
					String RefNumber = orderLib.getTextfromReferenceNumber();
					searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName1"), data.get("dropDown1"));//Req History
					ReqLib.verifyDenyedstatusRefNum(data.get("status"),RefNumber);  //Open Req
					orderLib.verifyandClickonRefLink(RefNumber);     
					ReqLib.verifyApprovalManagmentPage();
					//Need to verify cancel button not exists
					ReqLib.verifyCancelRequisition();
					// LogIN with 1st User
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName")); //uftrequestor@mailnator.com
					cmtLib.loginAsAdminCMT();
					orderLib.clickOnSideMenuSelectAccountToolOptions(data.get("toolsMenuName"), data.get("dropDown"));
					ReqLib.verifyApprovalMangmntPagefromSideMenu();
					ReqLib.clickOnRequestorGroupID(data.get("ReqName1"));
					ReqLib.verifyCreate_EditRequestoreGrpGeader();
					ReqLib.verifyandclickManagerRequestors(data.get("tabName"));
					ReqLib.selectRequestorgrpNameFromList(data.get("ReqName UFT"));
					
					//Login with 4th User
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1")); // QTPReq1
					cmtLib.loginAsAdminCMT();
					searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName1"), data.get("dropDown1"));//Req History
					ReqLib.verifyDenyedstatusRefNum(data.get("status"),RefNumber);     //Open Req
					orderLib.verifyandClickonRefLink(RefNumber);
					ReqLib.verifyApprovalManagmentPage();
					ReqLib.verifyandClickCancelRequisitionBtn();
					ReqLib.verifyCancelRequisitionStatusMesssage();
					
					//LogIN with 1st User
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName")); //uftrequestor@mailnator.com
					cmtLib.loginAsAdminCMT();
					searchLib.searchInHomePage(data.get("SearchItem"));
					// Add a item to cart >> proceed To Checkout >> place order >>
					// Verify the review order details,Receipt Order And Date
					commonLib.addFirstDisplyedItemToCartAndVerify();
					orderLib.continueToCheckOutOnAddCart();

					// Select web requestor group name from dropdown
					ReqLib.selectRequestorGroupName(data.get("ReqName1"));
					orderLib.proceedToCheckout();

					orderLib.continueButtonOnAdditionalInformationSection();
					orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line
																// level Info
					ReqLib.clearPhoneNumber();
					orderLib.shippingBillPayContinueButton(); // Click continue on
																// shipping address
					orderLib.shippingOptionsCarrierSelection(); // Click continue on
																// shipping options
					orderLib.shippingBillPayContinueButton(); // Billing address
																// continue button
					// MX card details
					orderLib.enterCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"),
							data.get("Year"), data.get("PONumber"));
					orderLib.clickOnReviewRequisitionButton();
					orderLib.verifyPlaceOrderLabel();
					orderLib.clickOnPlaceRequisitionButton();
					// Verify Receipt
					Thread.sleep(4000);
					orderLib.verifyReceiptVerbiage();
					String RefNumber1 = orderLib.getTextfromReferenceNumber();
					
					//Login with 2nd User
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2")); //QTPapp1
					cmtLib.loginAsAdminCMT();
					searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName1"), data.get("dropDown1"));
					orderLib.verifyandClickonRefLink(RefNumber1);
					ReqLib.verifyApprovalManagmentPage();
					ReqLib.clickUpdateInApprovalManagmentPage();
					//LogIN with 1st User
					navigateBackToCMT();
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName")); //uftrequestor@mailnator.com
					cmtLib.loginAsAdminCMT();
					searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName1"), data.get("dropDown1"));//Req History
					ReqLib.verifyDenyedstatusRefNum(data.get("status"),RefNumber1);   //Open Req
					orderLib.verifyandClickonRefLink(RefNumber1);
					ReqLib.verifyApprovalManagmentPage();
					ReqLib.verifyandClickCancelRequisitionBtn();
					ReqLib.verifyCancelRequisitionStatusMesssage();
					
					//Login with 3rd User
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname3"), data.get("ContactName3")); //QTPapp2
					cmtLib.loginAsAdminCMT();
					searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName1"), data.get("dropDown1"));
					ReqLib.clickExpandSearchIcon();
					ReqLib.verifyDenyedstatusRefNum(data.get("status1"),RefNumber1); //Cancelled Req
					ReqLib.verifyorderNuminReqHistoryPage(RefNumber1);
								
					// Logout
					commonLib.clickLogOutLink(data.get("header1"));

					fnCloseTest();
				}
			}
			
}
