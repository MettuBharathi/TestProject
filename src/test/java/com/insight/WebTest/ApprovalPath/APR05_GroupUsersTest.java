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

public class APR05_GroupUsersTest extends ApprovalPathLib {

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib = new ProductDetailLib();
	OrderLib orderLib = new OrderLib();
	ProductDisplayInfoLib pipLib = new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib = new ShipBillPayLib();

	// #############################################################################################################
	// # Name of the Test : APR05_GroupUsers
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : November 2019
	// # DESCRIPTION : This method is to verify Group Users
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_APR05(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "APR05_GroupUsers", TestData, "Approval_Path");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("APR05_GroupUsers", TestData,
							"Approval_Path", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("GroupUsers");

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

					// Verify Approval Management Page - is Loaded
					reqProcLib.verifyApprovalManagementPage();

					String ReqGroupName1 = data.get("RequestorGroup1");
					String ReqGroupName2 = data.get("RequestorGroup2");

					// Add Requestors to Requestor Group - TU_IUS Requestor
					// Group
					AddRequestorsRequestorGroup(ReqGroupName1);

					// Click on Reports
					ClickReports();

					// Click on Requestor Requestor Group Users
					ClickRequestorGroupUsersLink();

					// Add Requestors to Requestor Group - TU_IUS Requestor
					// Group Tiered
					AddRequestorsRequestorGroup(ReqGroupName2);

					Thread.sleep(1000);
					// Click on Reports
					ClickReports();

					// Click on Requestor Requestor Group Users
					ClickRequestorGroupUsersLink();

					// Click Refresh Icon To display Requestors
					ClickRequestorPageRefreshIcon();

					// Displays Requestors
					DisplayRequestors();

					// Verify the Paging Count 50
					VerifyPageCount50();

					// Change the paging to 20
					ChangePageCount(data.get("PageCount"));

					// Click on the Next Page and verify page count 50
					ClickNextPageAndVerify50();

					Thread.sleep(2000);
					// click Requestor group link
					ClickTU_IUSRequestorGroupTieredLink();

					// Verify Create/Edit Requestor Group Page
					Verify_Create_Edit_Requestor_GroupPage();

					// Click on Manage Requestors
					clickManageRequestors();

					// Click Refresh Icon To display Requestors
					ClickRefreshIcon();

					// Select the User and Add the Name to Requestors Included
					// in the Requestor Group
					// Select Requestors
					SelectRequestor(data.get("Requestor1"));

					// Add
					Add_Requestor_Btn_Click();

					// Click Save Changes
					ClickSaveChangesButton();

					Thread.sleep(2000);

					UpdatedSuccessMsg();

					// Click on General Settings to click Reports
					ClickGeneralSettings();

					// Click on Reports
					ClickReports();

					// Click on Requestor Requestor Group Users
					ClickRequestorGroupUsersLink();

					// Displays Requestors
					String reqName = DisplayRequestors();

					// Search by Last name
					SearchByLastName(reqName);

					// Click Search
					Click_Search_Icon();

					// Click on Requestor Requestor Group Users
					ClickRequestorGrpLink();

					// Verify
					ClickReqGroupEditLinkButton(ReqGroupName1);

					// Click on Manage Requestors
					clickManageRequestors();

					// Click Refresh Icon To display Requestors
					ClickRefreshIcon();

					// Select Requestors
					SelectRequestor(data.get("Requestor1"));

					// Add
					Add_Requestor_Btn_Click();

					// Click Save Changes
					ClickSaveChangesButton();

					Thread.sleep(2000);

					UpdatedSuccessMsg();

					// Click on General Settings to click Reports
					ClickGeneralSettings();

					// Click on Reports
					ClickReports();

					// Click on Requestor Requestor Group Users
					ClickRequestorGroupUsersLink();

					// Displays Requestors
					String reqName1 = DisplayRequestors();

					// Search by Last name
					SearchByLastName(reqName1);

					// Click Search
					Click_Search_Icon();

					// Displays Requestors
					DisplayRequestors();

					// Remove users
					RemoveUsers(ReqGroupName1, data.get("Requestor1"));

					Thread.sleep(1000);

					RemoveUsers(ReqGroupName2, data.get("Requestor1"));

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
			ReportStatus.fnUpdateResultStatus("APR05_GroupUsers", "TC_APR05", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("APR05_GroupUsers", "TC_APR05", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
