package com.insight.WebTest.Cart;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CRT13_DeleteCartItemsTest extends CartLib {
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();

	// #############################################################################################################
	// # Name of the Test : CRT13_DeleteCartItems
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test(enabled = true)
	public void Tc_CRT13(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT13_DeleteCartItems", TestDataInsight, "Web_Cart");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT13_DeleteCartItems", TestDataInsight,
							"Web_Cart", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("DeleteCartItems");
					reporter.SuccessReport("Iteration Number : ",
							"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
									+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
									+ "  **************",
							"");

					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					commonLib.clickOnLoginAsForDeleteFunctionality(data.get("PartNumber"), data.get("SearchItem"));

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("DeleteCartItems", "CRT13", ReportStatus.strMethodName, intCounter,
						browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("DeleteCartItems", "CRT13", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}
