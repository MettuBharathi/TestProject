package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER06_KeywordSearchTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();

	    // #############################################################################################################
		// #   Name of the Test               : SER06_KeywordSearch
		// #   Migration Author               : Cigniti Technologies
		// #
		// #   Date of Migration              : August 2019
		// #   DESCRIPTION                    : Purpose of this test case is to perform keyword search and verify the filters operation in the Product details page.
		// #   Parameters                     : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_SER06(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER06_KeywordSearch", TestData, "Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initilizing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SER06_KeywordSearch", TestData,
							"Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("KeywordSearch");
					reporter.SuccessReport("Iteration Number : ",
							"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
									+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
									+ "  **************","");

					// Test Steps execution

					fnOpenTest();
					searchInHomePage(data.get("SearchText"));
					verifyTheResultsForSearchTerm(data.get("SearchText"));
					filterSelectionInProductsSearchPage(data.get("Category"));
					filterSelectionInProductsSearchPage(data.get("Manufacturer1"));

					// Logging into CMT tool
					cmtLib.loginToCMT(data.get("Login"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.clickApproveItemCatlog();
					IncludeManufacturersInCatlogAndUpdate(data.get("Mfrs_Type"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Users"));

					cmtLib.loginAsAdminCMT();
					clickOnSecondaryDDAndSelectListitem(data.get("HeaderName"), data.get("HeaderList"));
					filterSelectionInProductsSearchPage(data.get("Manufacturer2"));
					prodInfoLib.enterPriceDetailsFilters(data.get("Min_Price"), data.get("Max_Price"));
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("KeywordSearch", "SER06",
						ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("KeywordSearch", "SER06", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}
