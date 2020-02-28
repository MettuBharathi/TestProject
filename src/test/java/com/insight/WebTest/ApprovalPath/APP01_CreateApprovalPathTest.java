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

public class APP01_CreateApprovalPathTest extends ApprovalPathLib {

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib = new ProductDetailLib();
	OrderLib orderLib = new OrderLib();
	ProductDisplayInfoLib pipLib = new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib = new ShipBillPayLib();

	// #############################################################################################################
	// # Name of the Test : APP01_CreateApprovalPath
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : October 2019
	// # DESCRIPTION : This method is to delete the created approval path
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_APP01(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {			
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "APP01_CreateApprovalPath", TestData,
					"Approval_Path");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("APP01_CreateApprovalPath", TestData, "Approval_Path", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("CreateApprovalPath");
										
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

					// Get the existing Approval path name
					GetFirstApprovalPath();

					// Create Create New Approval Path
					CreateNewApprovalPathLink();

					// Get the Last name of Existing User to search in latter
					// steps
					EnterNewApprovalPath(data.get("Approvername"));

					// select approval path
					String approverName = CreateNewApprovalClick();

					// Click approval path button
					ClickCreateApprovalPathButton();

					// Get the Existing User Last name to Search
					ApproverSearchTextBox(approverName.split(",")[0]);

					// Search button click
					SearchClick();

					// Get the created approver from search list
					ValidateApproverFromSearch(data.get("Approvername"));

					// Click on Edit link for the created Approver
					ClickEditLinkButton(data.get("Approvername"));

					// Select Approvers and click Add
					String appName = SelectApprover(null);
					
					// Add button to add approver
					Add_Approver_Btn_Click();

					// Verify Approvers are Added to Approver path Sequence
					VerifyApproversAdded(appName);

					ClickUpdateButton();

					// Delete Created Approval path
					DeleteApprovers(data.get("Approvername"));

					// Confirm Delete
					ConfirmDelete(data.get("Approvername"));

					// Search for Inactive user
					SearchUser(data.get("Approvername"));

					// Verify Approval path is Retuned
					VerifyApprovalPathReturned();

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
			ReportStatus.fnUpdateResultStatus("APP01_CreateApprovalPath", "TC_APP01", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("APP01_CreateApprovalPath", "TC_APP01", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
