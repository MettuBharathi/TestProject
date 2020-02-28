package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER03_CompareTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();

	    //#############################################################################################################
		//#		Name of the Test			:	   SER03_Compare
		//#		Migration Author 	        : 	   Cigniti Technologies
		//#		
		//#		Date of Migration			:      August 2019
		//#		DESCRIPTION 				:	   Purpose of this test method is to verify the compare functionality in the products display page.
		//#		Parameters                  :      StartRow ,EndRow , nextTestJoin
		//#      												
		//###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_SER03(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER03_Compare", TestData, "Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initilizing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SER03_Compare", TestData,"Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("Compare");
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")+ "  **************","");

					// Test Steps execution

					fnOpenTest();
					navigateToProductSearchResultsAndSearchProduct(data.get("HeaderName"), data.get("HeaderList"),
							data.get("ProductType"), data.get("ProductName"));
					prodInfoLib.clickOnCompareSimilarLink();
					verifyTheProductNameInCompareSimilarProductsPage(data.get("ProductName34"));
					navigateToBackPage();
					int itemscount = Integer.valueOf(data.get("Items_Count"));
					clickOnAddToMyCompareListLink(itemscount);
					// Verify products added to list
					String compareNum = verifyCompareList();
					assertTextStringMatching(compareNum, data.get("Items_Count"));
					clickOnComparedItemsLink();
					clickOnCloseIconInCompareProductsPage();
					prodInfoLib.addToCart();
				
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("Compare", "SER03",
						ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("Compare", "SER03", ReportStatus.strMethodName,1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}
