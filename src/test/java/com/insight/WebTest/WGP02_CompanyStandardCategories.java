package com.insight.WebTest;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CommonLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class WGP02_CompanyStandardCategories extends CMTLib {

	CommonLib commonLib = new CommonLib(); 

	// #############################################################################################################
	// # Name of the Test : WGP02_CompanyStandardCategories
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : September 2019
	// # Description : To Test Company Standards Categories
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_WGP02(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try { 

			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_WGP02", TestData,
					"Web_Group_Management");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				try {
					// Test Steps execution
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_WGP02",
							TestData, "Web_Group_Management", intStartRow);
					TestEngineWeb.reporter.initTestCaseDescription(
							"SLPProrationMicrosoft Testcase" + " From Iteration " + StartRow + " to " + EndRow);
					reporter.SuccessReport("Iteration Number : ",
							"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
									+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
									+ "  **************",
							"");
					
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
					ModifyCategory(data.get("NewQTPCategory"));

					// Check the Check Box to Collapse option
					CheckCollapseOption();

					// Click Update
					ClickUpdate();

					// Click on Product Group to Modify
					ModifyProdGroup(data.get("Product_Group_Name"), data.get("New_Product_Group_Name"));

					// Verify Update
					VerifyUpdateMsg();

					// Create New Product Group
					ClickOnProdGroupImg();

					// Provide Category Name and click Create
					CreateNewCategory(data.get("NewQTPCategory"));

					// Delete created categories
					DeleteCategory(data.get("NewQTPCategory"));

					// select user
					// Call the Function SelectUser
					hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));

					// verify product groups are collapsed
					verifyProdGroupsCollapsed();

					// Logout and Close Insight Browser
					logoutSite();

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("AddRemoveFavoritesAsLoggedInUser", "TC_WGP02",
						ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("AddRemoveFavoritesAsLoggedInUser", "TC_WGP02",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
	}
}
