package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER07_MenuSearchTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();

	        // #############################################################################################################
			// #   Name of the Test               : SER07_MenuSearch
			// #   Migration Author               : Cigniti Technologies
			// #
			// #   Date of Migration              : August 2019
			// #   DESCRIPTION                    : This method is to navigate to the Shop All Products / Shop All Brands and
		    // #                                      select the particular category and verify navigations and menus.
			// #   Parameters                     : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################	

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_SER07(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER07_MenuSearch", TestData, "Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {

					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SER07_MenuSearch", TestData,
							"Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("MenuSearch");
					reporter.SuccessReport("Iteration Number : ",
							"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
									+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
									+ "  **************","");

					// Test Steps execution
					fnOpenTest();
					clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAll"));
					verifyMenusInShopAllProductsPage(data.get("Menus"));
					clickMenuItemCategoryInShopAllProductsPage(data.get("MenuItem"), data.get("Category"));
					selectTheProductByTypeAndVerifyNavigation(data.get("ProductType"));

					clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAllBrands"));
					selectTopBrandsInShopAllBrandsPage(data.get("Brand"), data.get("Url"));

					clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAllBrands"));
					selectBrandByAlphabetOrderSection(data.get("Url2"), data.get("Brand"));

				}

				catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("MenuSearch", "SER07",
						ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("MenuSearch", "SER07", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}
