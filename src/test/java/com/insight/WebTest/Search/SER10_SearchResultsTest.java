package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER10_SearchResultsTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();

	    // #############################################################################################################
		// #    Name of the Test         : SER10_SearchResults
		// #    Migration Author         : Cigniti Technologies
		// #
		// #    Date of Migration        : August 2019
		// #    DESCRIPTION              : This method is to perform search operations in the Search results screen using filters.
		// #    Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_SER10(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER10_SearchResults", TestData, "Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initilizing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SER10_SearchResults", TestData,
							"Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SearchResults");
					reporter.SuccessReport("Iteration Number : ",
							"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
									+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
									+ "  **************","");

					// Test Steps execution
					fnOpenTest();
					searchInHomePage(data.get("SearchText"));
					verifyTheResultsForSearchTerm(data.get("SearchText"));

					searchInHomePage(data.get("SearchText2"));
					verifyTheResultsForSearchTerm(data.get("SearchText2"));
					filterSelectionInProductsSearchPage(data.get("StockFilter"));
					removeTheFilterForInStockOnly(data.get("In_Stock"));

					filterSelectionInProductsSearchPage(data.get("MnfrFilter"));
					removeTheFilter(data.get("MnfrFilter"));
					prodInfoLib.enterPriceDetailsFilters(data.get("Min_Price"), data.get("Max_Price"));

					searchProductInProductDisplayPage(data.get("ProductName"));
					removeTheFilter(data.get("ProductName"));

					searchInHomePage(data.get("SearchText_adobe"));
					verifyTheResultsForSearchTerm(data.get("SearchText_adobe"));
					prodInfoLib.selectFirstProductAndReturnBack();
					prodInfoLib.selectFirstProductImageAndReturnBack();
					prodInfoLib.verifyTheSearchResultsDisplayed();
					prodInfoLib.selectSortByOptions(data.get("Sort_By"));

				}

				catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("SearchResults", "SER10",
						ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("SearchResults", "SER10", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}
