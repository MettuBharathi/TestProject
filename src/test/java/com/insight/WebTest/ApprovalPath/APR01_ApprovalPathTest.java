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

public class APR01_ApprovalPathTest extends ApprovalPathLib {

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib = new ProductDetailLib();
	OrderLib orderLib = new OrderLib();
	ProductDisplayInfoLib pipLib = new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib = new ShipBillPayLib();

	// #############################################################################################################
	// # Name of the Test : APR01_ApprovalPath
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : November 2019
	// # DESCRIPTION : This method is to work with approval path
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_APR01(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "APR01_ApprovalPath", TestData, "Approval_Path");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("APR01_ApprovalPath", TestData,
							"Approval_Path", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("ApprovalPath");

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

					// Verify App Path Names
					VerifyAppPathNames();

					// Click on Reports
					ClickReports();

					// Verify the Types of Reports are Loaded
					VerifyReportTypes();

					// Click on Approver path Report
					ClickApprovalPathReportLink();

					// Verify Approval Path Report
					String appPathName = VerifyApprovalPathReport();

					// Click on Path Report
					ClickApprovalPathLink(appPathName);

					// Verify Edit mode
					VerifyIsInEditMode();

					// Click on Reports
					ClickReports();

					// Verify the Types of Reports are Loaded
					VerifyReportTypes();

					// Click on Approver path Report
					ClickApprovalPathReportLink();

					// Verify Approval Path Report
					VerifyApprovalPathReport();

					// Click on Requestor Group
					ClickRequestorGrpLink();

					// Click On TU_IUS Requestor Group - Edit option
					ClickEditLinkINRequestorGrpPage(data.get("Approvername"));

					// Verify Requestor Group in Edit Mode
					VerifyReqGrpInEditMode();

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
			ReportStatus.fnUpdateResultStatus("APR01_ApprovalPath", "TC_APR01", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("APR01_ApprovalPath", "TC_APR01", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
