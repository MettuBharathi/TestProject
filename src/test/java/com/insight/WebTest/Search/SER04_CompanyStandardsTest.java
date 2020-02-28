package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER04_CompanyStandardsTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();

	    // #############################################################################################################
		// #    Name of the Test         : SER04_CompanyStandards
		// #    Migration Author         : Cigniti Technologies
	    // #
		// #    Date of Migration        : August 2019
		// #    Description              : This method is used to Test Company Standards.
		// #    Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// #############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_SER04(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER04_CompanyStandards", TestData, "Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initilizing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SER04_CompanyStandards",
							TestData, "Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("CompanyStandards");
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
									+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
									+ "  **************","");

					// Test Steps execution
					fnOpenTest();

					// Logging into CMT tool
					// Login to CMT enable Open Market and Contracts/Agencies
					// are enabled by default

					cmtLib.loginToCMT(data.get("Login"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginAsAdminCMT();

					// Back to UAT verify company standards
					selectAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"), data.get("Product_Group"), data.get("Product_Name"));
					selectNewHireStandardAndverifyCart(data.get("Product_Group2"), data.get("Product_Name2"),
							data.get("Product_Grp_Columns"));
					selectNewHireStandardAndverifyCart(data.get("Product_Group3"), data.get("Product_Name3"),
							data.get("Product_Grp_Columns"));
					selectProductGroupAndVerify(data.get("Product_Group3"), data.get("Product_Name4"));
					searchInHomePage(data.get("SearchText"));
					selectFirstProductVerifyAddToCompanyStandardsLink();

				}

				catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("CompanyStandards", "SER04",
						ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("CompanyStandards", "SER04", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}
