package com.insight.WebTest.RequisitionProcessing;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.RequisitionProcessingLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class REQ06_NOForceRequestorEnterCCTest extends ChinaLib{
	CommonLib commonLib = new CommonLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodLib = new ProductDisplayInfoLib();
	OrderLib orderLib = new OrderLib();
	RequisitionProcessingLib ReqLib = new RequisitionProcessingLib();
	   

	   
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
		public void TC_REQ06(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "REQ06_NOForceRequestorEnterCCTest", TestData, "China");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("REQ06_NOForceRequestorEnterCCTest", TestData, "China", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("NOForceRequestorEnterCC");
						reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");

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
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("TestRules", "REQ06", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("TestRules", "REQ06", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}

