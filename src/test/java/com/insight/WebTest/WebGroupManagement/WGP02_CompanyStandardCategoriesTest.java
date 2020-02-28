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

public class WGP02_CompanyStandardCategoriesTest extends CMTLib {
	CommonLib commonLib = new CommonLib();

	// #############################################################################################################
	// # Name of the Test : WGP02_CompanyStandardCategories
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : September 2019
	// # Description : To Test Place Order basic
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test

	public void TC_WGP02(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "WGP02_CompanyStandardCategories", TestData,
					"Web_Group_Management");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("WGP02_CompanyStandardCategories",
							TestData, "Web_Group_Management", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("CompanyStandardCategoriesTest");

					// Login to CMT and disable Allow File Upload during
					// Checkout,Override
					loginToCMT(data.get("Header"));
					searchForWebGroup(data.get("WebGrp"));
					clickOnTheWebGroup(data.get("WebGrp_Name"));

					// select Company Standards
					hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));

					// Click on CompStandardWizard
					selectCompanyStandardsLink();

					// Enter Product Group Name on Product Group Creation Wizard
					enterProductGrpName(data.get("Product_Group_Name"));

					// Enter Create New
					enterCreateNewValue(data.get("Create_New"));

					// Click Continue
					clickContinueBtn();

					// Enter Configuration Set Name and Continue
					enterConfigurationSetName(data.get("Configuration_Set_Name"));

					// Search by Key Word
					searchByKeword(data.get("Search_Keyword"));

					// Verify Product
					verifyProduct();

					// Verify Insight home page is Launched
					AddToSet();

					// Verify Update
					VerifyUpdate();

					// Click on Category Link to Edit
					ClickCategoryLink();

					// Modify the Name
					ModifyCategory();

					// Check the Check Box to Collapse option
					CheckCollapseOption();

					// Click Update
					ClickUpdate();

					// Click on Product Group to Modify
					// ModifyProdGroup(data.get("Product_Group_Name"),
					// data.get("Create_New"));
					ModifyProdGroup(data.get("Product_Group_Name"), "TestCategory");

					// Verify Update
					VerifyUpdateMsg();

					// select user
					// Call the Function SelectUser
					hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));

					// Call the Function Login As
					loginAsAdminCMT();
					commonLib.spinnerImage();

					// Verify the Same User Logged into Insight
					commonLib.clickOnAccountToolsAndClickOnProductGrps(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));

					// Verify ICompany Standards
					VerifyICompanyStandards();

					// Logout and Close Insight Browser
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
			ReportStatus.fnUpdateResultStatus("WGP02_CompanyStandardCategoriesTest", "TC_WGP02",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("WGP02_CompanyStandardCategoriesTest", "TC_WGP02",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}
