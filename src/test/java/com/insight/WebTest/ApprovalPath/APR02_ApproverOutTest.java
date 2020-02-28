package com.insight.WebTest.ApprovalPath;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.api.client.util.DateTime;
import com.insight.Lib.ApprovalPathLib;
import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
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

public class APR02_ApproverOutTest extends ApprovalPathLib {

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib = new ProductDetailLib();
	OrderLib orderLib = new OrderLib();
	ProductDisplayInfoLib pipLib = new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib = new ShipBillPayLib();

	// #############################################################################################################
	// # Name of the Test : APR02_ApproverOut
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : November 2019
	// # DESCRIPTION : This method is to work with approval path
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_APR02(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "APR02_ApproverOut", TestData, "Approval_Path");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("APR02_ApproverOut", TestData,
							"Approval_Path", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("ApproverOut");

					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					RequisitionProcessingLib reqProcLib = new RequisitionProcessingLib();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Disable_Permission"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.spinnerImage();
					commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));

					// Verify Verify Approval Management Page - is Loaded
					reqProcLib.verifyApprovalManagementPage();
					
					// Click on General Settings
					ClickGeneralSettings();

					// Click Create with out Entering Any thing - Click on
					// Create
					ClickCreateLink();

					// Verify the Name is Required
					verifyNameIsRequiredAlert();

					String strApproverId = null;
					String strReplacementApproverId = null;
					String strApprover = null;
					String strReplacementApprover = null;
					String strCurrDay = null;
					
					String strReplacementType = data.get("ReplacementType");
					String forwardRequestReplacementType = data.get("ForwardRequestReplacementType");
					String permanentReplaceReplacementType = data.get("PermanentReplaceReplacementType");

					// Get the Approver First
					strApproverId = GetApproverId();
					strApprover = GetApproverName(strApproverId);

					// Get Replacement Approver
					
					strReplacementApproverId = GetReplacementApproverId();
					strReplacementApprover = GetReplacementApproverName(strReplacementApproverId);
					
					// Get Date
					strCurrDay = GetCurrDay();

					// Create Approver Out
					CreateApproverOut(strApproverId, strReplacementType, strReplacementApproverId, strCurrDay);

					strApprover = strApprover.replace(",  ", ", ");

					Thread.sleep(2000);

					// Verify Created Approver Out
					VerifyCreateApproverOut(strApprover);

					// Create with Same Rule
					CreateApproverOut(strApproverId, strReplacementType, strReplacementApprover, strCurrDay);

					// System warning click ok
					VerifyOverLapRulePopup();

					// Edit and Delete Created Approver Out
					ModifyDeleteApproverOut(strApprover, strCurrDay);

					// Get Data as Forward request					
					CreateApproverOut(strApproverId, forwardRequestReplacementType, strReplacementApproverId, strCurrDay);

					// Verify Created Approver Out
					VerifyCreateApproverOut(strApprover);
					
					// Edit and Delete Created Approver Out
					ModifyDeleteApproverOut(strApprover, strCurrDay);

					// Get Data as peramanent request					
					CreateApproverOut(strApproverId, permanentReplaceReplacementType, strReplacementApproverId, strCurrDay);

					// Verify Created Approver Out
					VerifyCreateApproverOut(strApprover);
					
					// Edit and Delete Created Approver Out
					ModifyDeleteApproverOut(strApprover, strCurrDay);

										
					commonLib.clickLogOutLink(data.get("Logout"));

					System.out.println("Test completed");
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.toString();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("APR02_ApproverOut", "TC_APR02", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("APR02_ApproverOut", "TC_APR02", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
