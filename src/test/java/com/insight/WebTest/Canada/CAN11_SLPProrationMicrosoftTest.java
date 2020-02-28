package com.insight.WebTest.Canada;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN11_SLPProrationMicrosoftTest extends CanadaLib {

	// #############################################################################################################
	// # Name of the Test : CAN11_SLPProrationMicrosoft
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : October 2019
	// # DESCRIPTION : This method is to verify Proration price for canada
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CAN11(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN01_ShipBillPayTax", TestDataInsight, "Canada");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN11_SLPProrationMicrosoft",
							TestDataInsight, "Canada", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription(
							"SLPProrationMicrosoft Testcase" + " From Iteration " + StartRow + " to " + EndRow);
					reporter.SuccessReport("Iteration Number : ",
							"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
									+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
									+ "  **************",
							"");
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					CartLib cartLib = new CartLib();
					OrderLib orderLib = new OrderLib();
					CanadaLib canadaLib = new CanadaLib();

					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();

					canadaLib.CandaHomePageVerification();
					Thread.sleep(2000);
					// SelectSoftwareLicAgrements
					commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.selectProdCheckbox();
					canadaLib.verifyProductsVisible();
					// search product
					commonLib.searchProduct(data.get("Productname"));
					// commonLib.spinnerImage();
					Thread.sleep(2000);
					canadaLib.verifyProductPrice();
					Thread.sleep(2000);
					commonLib.addToCartAndVerify();
					String partNumber1 = cartLib.getPartNumber();
					canadaLib.continueToCheckout();
					// verify prorated price display
					canadaLib.verifyProratedPrice();
					// proceed to checkout
					orderLib.proceedToCheckout();
					// ******** Click continue on Line level Info, Ship and Bill pay sections ********************//
					orderLib.clickContinueOnLineLevelInfo();
					orderLib.shippingBillPayContinueButton();
					Thread.sleep(1000);
					orderLib.shippingBillPayContinueButton();
					Thread.sleep(1000);
					// Select Terms- payment method and click Review Order
					orderLib.selectPaymentMethod(data.get("Payment_method"));
					// Verify Ship Bill Page
					canadaLib.veriyProratedPriceinSBP();
					// verify subtotal(prorated price), verify receipt, order and date
					String summaryAmount = cartLib.getSummaryAmountInCart();
					canadaLib.verifyReceiptOrderAndDate(summaryAmount);
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					System.out.println("Test completed");
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("AddRemoveFavoritesAsLoggedInUser", "TC_CAN11",
						ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("AddRemoveFavoritesAsLoggedInUser", "TC_CAN11",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
	}
}
