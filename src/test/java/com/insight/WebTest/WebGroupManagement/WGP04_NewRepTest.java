package com.insight.WebTest.WebGroupManagement;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CommonLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class WGP04_NewRepTest extends CMTLib {
	CommonLib commonLib = new CommonLib();

	// #############################################################################################################
	// # Name of the Test : WGP04_NewRep_Action1_Script
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : September 2019
	// # Description : To Test Place Order basic
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test

	public void TC_WGP04(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "WGP04_NewRep", TestData, "Web_Group_Management");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("WGP04_NewRep", TestData,
							"Web_Group_Management", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("CompanyStandardCategoriesTest");

					// Login to CMT and disable Allow File Upload during
					// Checkout,Override
					loginToCMT(data.get("Header"));
					searchForWebGroup(data.get("WebGrp"));
					clickOnTheWebGroup(data.get("WebGrp_Name"));

					// Click on Contacts and Notifications
					hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));

					// Click on Add New Rep
					addNewRepDetails(data.get("Rep_Email"), data.get("Rep_PhoneNumber"), data.get("Rep_FaxNumber"));

					// New Rep successfully added message
					verifySuccessRepMsg();

					// Verify the Rep has been added and check product Exp and
					// Display Web
					// permissions
					verifyNewRepAdded();

					// check permissions
					checkPermissions();

					// save the Rep details
					saveRepDetails();

					// Verify successfully saved message
					verifySuccessSaveMsg();

					// Click on Contacts and Notifications
					hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					commonLib.spinnerImage();

					// Verify and Delete the newly added Rep
					deleteNewRepAdded();

					// Verify Rep deleted successfully message
					verifySuccessDeleteMsg();

					// Logout and Close Insight Browser
					logoutSite();

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
			ReportStatus.fnUpdateResultStatus("WGP04_NewRepTest", "TC_WGP04", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("WGP04_NewRepTest", "TC_WGP04", ReportStatus.strMethodName, counter,
					browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}
