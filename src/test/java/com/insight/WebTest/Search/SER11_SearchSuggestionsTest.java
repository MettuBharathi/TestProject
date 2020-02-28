package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER11_SearchSuggestionsTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();

	    // #############################################################################################################
		// #    Name of the Test         : SER11_SearchSuggestions
		// #    Migration Author         : Cigniti Technologies
	    // #
		// #    Date of Migration        : August 2019
		// #    Description              : This method is used to Test Search Suggestions.
		// #    Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_SER11(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER11_SearchSuggestions", TestData, "Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initilizing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SER11_SearchSuggestions",
							TestData, "Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SearchSuggestions");
					reporter.SuccessReport("Iteration Number : ",
							"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
									+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
									+ "  **************","");

					// Test Steps execution
					fnOpenTest();

					searchInHeaderSelectFromSuggestions(data.get("SearchText"));

					// login to CMT
					cmtLib.loginToCMTSelectUserAndLoginAS(data.get("Login"), data.get("WebGrp"),
							data.get("WebGrp_Name"), data.get("Manage_Web_Grp_Options"), data.get("LnameEmailUname"),
							data.get("ContactName"));

					// Navigate back to UAT
					// Enable Show search Suggestions and verify
					enableOrDisableSearchSuggestions(data.get("FavoritesTabName"), data.get("FavoritesTabName1"),
							data.get("SearchText"));

					// Disable Show search Suggestions and verify
					enableOrDisableSearchSuggestions(data.get("FavoritesTabName"), data.get("FavoritesTabName1"),
							data.get("SearchText"));

					// selectAccountTools(data.get("FavoritesTabName"),data.get("FavoritesTabName1"));

				}

				catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("SearchSuggestions", "SER11",
						ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("SearchSuggestions", "SER11", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}
