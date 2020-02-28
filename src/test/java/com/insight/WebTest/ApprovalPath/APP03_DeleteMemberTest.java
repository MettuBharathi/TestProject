package com.insight.WebTest.ApprovalPath;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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

public class APP03_DeleteMemberTest extends ApprovalPathLib {

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib = new ProductDetailLib();
	OrderLib orderLib = new OrderLib();
	ProductDisplayInfoLib pipLib = new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib = new ShipBillPayLib();

	// #############################################################################################################
	// # Name of the Test : APP03_DeleteMember
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : October 2019
	// # DESCRIPTION : This method is to delete the created approval path
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_APP03(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "APP03_DeleteMember", TestData, "Approval_Path");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("APP03_DeleteMember", TestData, "Approval_Path", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("DeleteMember");
									

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

					// System Displays All Approval paths and Details
					VerifyApprovalPathAndApprovers();

					// Get the First Approver path, Add Approver and Remove
					String firstPathName = GetFirstPathAddRemoveApprover();

					// Click on Edit link for the created Approver
					ClickEditLinkButton(firstPathName);

					// Select Approvers and add approver
					String approverAdded = SelectApprover(null);
					
					Add_Approver_Btn_Click();

					// Verify Approval path is added
					VerifyApproversAdded(approverAdded);

					// Now Remove the same user- Click Remove
					RemoveApprovers(approverAdded);

					// Update Approval path
					ClickUpdateButton();

					// Verify - update message
					VerifySuccessUpdate();

					// Select Approval path and Edit
					ClickEditLinkButton(data.get("AppPathName"));

					// Verify Edit Mode
					VerifyIsInEditMode();

					// click remove
					RemoveApprovers(data.get("Select_Approver"));

					// click update
					ClickUpdateButton();

					// Verify error message on popup
					VerifyMessage();

					// Click Cancel
					CancelOnErrorPage();
					
					//Cancel on edit/update page
					ClickCancelButton();

					// Select Approval path and Edit
					ClickEditLinkButton(data.get("AppPathName"));

					// Verify Edit Mode
					VerifyIsInEditMode();

					// click remove
					RemoveApprovers(data.get("Select_Approver"));

					// click update
					ClickUpdateButton();

					// Verify error message on popup
					VerifyMessage();

					// Count Pending Req
					CountPendingRequest();

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
			ReportStatus.fnUpdateResultStatus("APP03_DeleteMember", "TC_APP03", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("APP03_DeleteMember", "TC_APP03", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
