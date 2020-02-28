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

public class REQ03_DenyRequisitionTest extends ChinaLib{
	CommonLib commonLib = new CommonLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodLib = new ProductDisplayInfoLib();
	OrderLib orderLib = new OrderLib();
	RequisitionProcessingLib ReqLib = new RequisitionProcessingLib();
	   
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
		public void TC_REQ03(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "REQ03_DenyRequisitionTest", TestData, "China");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("REQ03_DenyRequisition", TestData, "China", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("DenyRequisition");
						reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");

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
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("TestRules", "REQ03", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("TestRules", "REQ03", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}

