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

public class REQ04_CancelRequisitionTest extends ChinaLib{
	CommonLib commonLib = new CommonLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodLib = new ProductDisplayInfoLib();
	OrderLib orderLib = new OrderLib();
	RequisitionProcessingLib ReqLib = new RequisitionProcessingLib();
	   
	   
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
		public void TC_REQ04(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "REQ04_CancelRequisition", TestData, "China");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("REQ04_CancelRequisition", TestData, "China", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("CancelRequisition");
						reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");

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
						cmtLib.navigateBackToCMT();
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
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("TestRules", "REQ04", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("TestRules", "REQ04", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}

