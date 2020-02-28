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

public class REQ07_CreateDeleteRGRulesTest extends ChinaLib{
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	RequisitionProcessingLib reqProcLib = new RequisitionProcessingLib();
	OrderLib orderLib = new OrderLib();

	   
	// #############################################################################################################
		// # Name of the Test : REQ07_CreateDeleteRGRulesTest
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : September 2019
		// # Description : To Test Place Order basic
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// #############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_REQ07(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "REQ07_CreateDeleteRGRulesTest", TestData, "China");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("REQ07_CreateDeleteRGRulesTest", TestData, "China", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("CreateDeleteRGRulesTest");
						reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");

						// select language and country
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

						// create rules

						// reqProcLib.createRule(data.get("Cart_Type_Standard"),
						// data.get("Min_Amt_0"), data.get("Max_Amt_99"),
						// data.get("Result_Path1"), "1");
						// verify success add message
						// reqProcLib.verifySuccessMsg();
						// Thread.sleep(1000);
						// reqProcLib.createRule(data.get("Cart_Type_NonStandard"),
						// data.get("Min_Amt_100"), data.get("Max_Amt_199"),
						// data.get("Result_Path2"), "2");
						// verify success add message
						// reqProcLib.verifySuccessMsg();
						// Thread.sleep(1000);
						// reqProcLib.createRule(data.get("Cart_Type_Any"),
						// data.get("Min_Amt_200"), data.get("Max_Amt_299"),
						// data.get("Result_Path3"), "3");
						// verify success add message
						// reqProcLib.verifySuccessMsg();


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
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("TestRules", "REQ07", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("TestRules", "REQ07", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}

