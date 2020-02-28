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

public class APP04_SearchTest extends ApprovalPathLib {

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib = new ProductDetailLib();
	OrderLib orderLib = new OrderLib();
	ProductDisplayInfoLib pipLib = new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib = new ShipBillPayLib();

	// #############################################################################################################
	// # Name of the Test : APP04_Search
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : October 2019
	// # DESCRIPTION : This method is to delete the created approval path
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_APP04(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "APP04_Search", TestData, "Approval_Path");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("APP04_Search", TestData, "Approval_Path", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SearchTest");
								

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

					// Create Create New Approval Path - QTPTesting1
					CreateNewApprovalPathLink();

					String AppPathName1 = data.get("AppPathName");
					AppPathName1 = AppPathName1.concat(getRandomNumeric(1));

					// Enter Name
					EnterNewApprovalPath(AppPathName1);

					// Select Approvers
					SelectApprover(null);

					// --- '>' icon
					Add_Approver_Btn_Click();

					// Create Approval path
					ClickCreateApprovalPathButton();

					// Create Create New Approval Path - QTPTesting1
					CreateNewApprovalPathLink();

					String AppPathName2 = data.get("AppPathName");
					AppPathName2 = AppPathName2.concat(getRandomNumeric(1));

					// Enter Name
					EnterNewApprovalPath(AppPathName2);

					// Select Approvers
					SelectApprover(null);

					// --- '>' icon
					Add_Approver_Btn_Click();

					// Create Approval path
					ClickCreateApprovalPathButton();
					
					Thread.sleep(2000);
					
					// Verify Approval is added - first approval path
					VerifyFirstApproverAdded(AppPathName1);
					
					Thread.sleep(1000);

					// Verify Approval is Added - second approval path
					VerifySecondApproverAdded(AppPathName2);

					// Enter Partial name and Search
					String[] strApprovalPathName = (AppPathName1).split("Testing");

					// Search with QTP - and click on search button
					SearchUser(strApprovalPathName[0]);

					// Verify Search results - QTPTesting1 - first random number
					VerifyAppovalPathCreated(AppPathName1);

					// Verify Search results - QTPTesting2 - second random
					// number
					VerifyAppovalPathCreated(AppPathName2);

					// Edit first approval path
					ClickEditLinkButton(AppPathName1);

					// Select Approvers and click Add
					SelectApprover(null);

					// Add button to add approver
					Add_Approver_Btn_Click();

					ClickUpdateButton();

					// Search with QTP - and click on search button
					SearchUser(strApprovalPathName[0]);

					// Edit second approval path
					ClickEditLinkButton(AppPathName2);

					// Select Approvers and click Add
					SelectApprover(null);

					// Add button to add approver
					Add_Approver_Btn_Click();

					ClickUpdateButton();

					// Perform Last name Search - "Automation"
					String strLastName = data.get("Select_Approver");
					ApproverSearchTextBox(strLastName);

					// Click Viewing all Available Approvers'
					ClickViewAllPathDetails();

					// System Displays All Approval paths and Details
					VerifyApprovalPathAndApprovers();

					Thread.sleep(2000);

					// Delete Created Approval path - QTPTesting with 1st random
					// number
					DeleteApprovers(AppPathName1);

					Thread.sleep(1000);

					// Confirm Delete
					ConfirmDelete(AppPathName1);

					Thread.sleep(2000);

					// Delete Created Approval path - QTPTesting with 2nd random
					// number
					DeleteApprovers(AppPathName2);

					Thread.sleep(1000);

					// Confirm Delete
					ConfirmDelete(AppPathName2);

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
			ReportStatus.fnUpdateResultStatus("APP04_SearchTest", "TC_APP04", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("APP04_SearchTest", "TC_APP04", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

	}


