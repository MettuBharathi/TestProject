package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER13_IPSPersonalProductListSearchResultsTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();

	    // ############################################################################################################
		// #    Name of the Test         : SER13_IPSPersonalProductListSearchResults
		// #    Migration Author         : Cigniti Technologies
	    // #
		// #    Date of Migration        : August 2019
		// #    Description              : This Test is used to Test  Product Center Search Results
		// #    Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_SER13(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER13_IPSPersonalProductListSearchResults", TestData,
					"Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initilizing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo(
							"SER13_IPSPersonalProductListSearchResults", TestData, "Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSPersonalProductListSearchResults");
					reporter.SuccessReport("Iteration Number : ",
							"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
									+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
									+ "  **************","");

					fnOpenTest();

					// Selecting the type of the product and verifying
					// navigation
					navigateToProductSearchResultsAndSearchProduct(data.get("HeaderName"), data.get("HeaderList"),
							data.get("ProductType"), data.get("ProductName"));
					// Add to Personal product list link should not display.
					prodInfoLib.selectProductAndverifyPersonalProductListLinkPresent();

					// login to CMT
					cmtLib.loginToCMTSetPermissions(data.get("Login"), data.get("WebGrp"), data.get("WebGrp_Name"),
							data.get("Manage_Web_Grp_Options"), data.get("LnameEmailUname"), data.get("ContactName"),
							data.get("Menu_Name"), data.get("Set_Permission"));

					// Back to UAT
					navigateToProductSearchResultsAndSearchProduct(data.get("HeaderName"), data.get("HeaderList"),
							data.get("ProductType"), data.get("ProductName"));
					// Add to Personal product list link should display after
					// setting in CMT
					prodInfoLib.selectProductAndverifyPersonalProductListLinkPresent();
					prodInfoLib.addItemsToProductList(data.get("Part_Number"));
					prodInfoLib.addToCartAndVerify(data.get("Part_Number"));
				}

				catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("IPSPersonalProductListSearchResults", "SER13",
						ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("IPSPersonalProductListSearchResults", "SER13", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}
