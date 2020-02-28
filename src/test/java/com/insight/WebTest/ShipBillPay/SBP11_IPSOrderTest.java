package com.insight.WebTest.ShipBillPay;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SBP11_IPSOrderTest extends ShipBillPayLib {
	    // #############################################################################################################
		// # Name of the Test : SBP11_IPSOrder
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_SBP11(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP11_IPSOrder", TestDataInsight, "Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP11_IPSOrder", TestDataInsight, "Ship_Bill_Pay",
						intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("IPSOrder");
				reporter.SuccessReport("Iteration Number : ",
						"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
								+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
						"");
				CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
				CartLib cartLib = new CartLib();
				OrderLib orderLib = new OrderLib();
				ShipBillPayLib shipbLib = new ShipBillPayLib();
				navigateTo(data.get("URL"));
				shipbLib.clickOnShop(data.get("Header"));
				shipbLib.clickOnStateAndCentralGovernment(data.get("Contract"));
				shipbLib.selectContract(data.get("State_Contract"), data.get("Country"), data.get("Contract_Option"));
				shipbLib.clickonBrowseProducts();
				commonLib.spinnerImage();
				shipbLib.verifyProducts(data.get("Contract_Option"));
				shipbLib.clickonchange();
				shipbLib.selectContractOption(data.get("Contract_Option"), data.get("Contract_Option1"));
				shipbLib.clickonBrowseProducts();
				commonLib.spinnerImage();
				shipbLib.verifyProducts(data.get("Contract_Option1"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				commonLib.continueToShopping();
				commonLib.clickCart();
				cmtLib.handleWelcomeToInsightBetaPopUp();
				// proceed to checkout
				orderLib.proceedToCheckout();
				shipbLib.verifyHomepgaeLogin(data.get("Login_Header"));

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					System.out.println(e.getMessage());
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("IPSOrder", "SBP11", ReportStatus.strMethodName, intCounter,
						browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("IPSOrder", "SBP11", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}
