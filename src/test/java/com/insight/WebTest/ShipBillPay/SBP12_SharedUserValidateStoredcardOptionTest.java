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

public class SBP12_SharedUserValidateStoredcardOptionTest extends ShipBillPayLib {
	// #############################################################################################################
		// # Name of the Test : SBP12_SharedUserValidateStoredcardOption
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_SBP12(int StartRow, String EndRow, boolean nextTestJoin)
				throws Throwable {
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP12_SharedUserValidateStoredcardOption",
					TestDataInsight, "Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP12_SharedUserValidateStoredcardOption",
						TestDataInsight, "Ship_Bill_Pay", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("SharedUserValidateStoredcardOption");
				reporter.SuccessReport("Iteration Number : ",
						"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
								+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
						"");
				CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
				CartLib cartLib = new CartLib();
				OrderLib orderLib = new OrderLib();
				ShipBillPayLib shipbLib = new ShipBillPayLib();
				cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
						data.get("ContactName"));
				cmtLib.clickOnSharedLinkUserInInformationTab();
				cmtLib.userLoginVerification(data.get("ContactName"));
				commonLib.searchProduct(data.get("SearchItem"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				String partNumber1 = cartLib.getPartNumber();
				commonLib.continueToShopping();
				commonLib.clickCart();
				cartLib.verifyItemInCart(partNumber1);
				orderLib.proceedToCheckout();
				shipbLib.addAddtionalInfo(data.get("Name"), data.get("Phone"), data.get("Email"));
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLLIAndShipBillPaySections();
				// Verification
				verifyStoredCard();
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				
			} catch (Exception e) {
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				System.out.println(e.getMessage());
				gTestStatus = false;
			}
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SharedUserValidateStoredcardOption", "SBP12", ReportStatus.strMethodName, intCounter,
					browser);
			fnCloseTest();
		}
	} catch (Exception e) {
		e.printStackTrace();
		ReportStatus.blnStatus = false;
		gErrorMessage = e.getMessage();
		gTestStatus = false;
		ReportStatus.fnUpdateResultStatus("SharedUserValidateStoredcardOption", "SBP12", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}

	ReportControl.fnNextTestJoin(nextTestJoin);
}

}