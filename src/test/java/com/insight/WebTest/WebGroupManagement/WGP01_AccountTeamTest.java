package com.insight.WebTest.WebGroupManagement;

import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CommonLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class WGP01_AccountTeamTest extends CMTLib {
	CommonLib commonLib = new CommonLib();

	// #############################################################################################################
	// # Name of the Test : WGP01_AccountTeam
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : September 2019
	// # Description : To Test Place Order basic
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test

	public void TC_WGP01(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "WGP01_AccountTeam", TestData,
					"Web_Group_Management");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("WGP01_AccountTeam", TestData,
							"Web_Group_Management", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("AccountTeam");

					loginToCMT(data.get("Header"));
					searchForWebGroup(data.get("WebGrp"));
					clickOnTheWebGroup(data.get("WebGrp_Name"));

					// Click on Contacts and Notifications
					hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));

					// Click on Display Icon
					List<String> RepNamesInDisplayOnWeb = verifyDisplayWebIcon();

					// Click on Product Exp
					verifyProductExpIconFeature();

					// Call the Function SelectUser
					hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));

					// Call the Function Login As
					loginAsAdminCMT();
					commonLib.spinnerImage();

					// Verify the Same User Logged into Insight
					commonLib.clickOnAccountToolsAndClickOnProductGrps(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));

					// Compare Sequence entered in CMT and after Login As
					VerifyRepNamesDisplayOrder(RepNamesInDisplayOnWeb);

					// Logout
					commonLib.clickLogOutLink(data.get("Logout"));

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
			ReportStatus.fnUpdateResultStatus("AccountTeam", "TC_WGP01", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("AccountTeam", "TC_WGP01", ReportStatus.strMethodName, counter,
					browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}
