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

public class SBP02_FreightFreeTest extends ShipBillPayLib {
	// #############################################################################################################
	// # Name of the Test : SBP02_FreightFree
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_SBP02(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP02_FreightFree", TestDataInsight,
					"Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP02_FreightFree", TestDataInsight,
							"Ship_Bill_Pay", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription(
							"FreightFree");
					reporter.SuccessReport("Iteration Number : ",
							"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
									+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
									+ "  **************",
							"");
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					CartLib cartLib = new CartLib();
					OrderLib orderLib = new OrderLib();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
					cmtLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
					cmtLib.selectAllDesignatedShippingOptions(data.get("Default_Shipping_Option"));
					cmtLib.clickupdateatDefaultShippingOption();
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.clickCart();
					commonLib.spinnerImage();
					String shipingCharges = cartLib.getShippingEstimateInCart();

					if (shipingCharges.equalsIgnoreCase(data.get("Shipping_Charges"))) {
						reporter.SuccessReport("Shipping Charges", "Shipping Field Existed  with $0.00 Amount", "");
					} else {
						reporter.failureReport("Shipping Charges", "Shipping Field Existed, amount is not $0.00", "");
					}
					orderLib.proceedToCheckout();
					cartLib.clickOnContinueButtonInAddInformtion();
					cartLib.shippingOptionsCarrierSelectionInCheckOut(data.get("Carrier_Option"));
					String shippingCost = cartLib.getShippingMethodCost(data.get("Shiping_Method"));
					System.out.println("shipingCharges" + shippingCost);
					if (shippingCost.equalsIgnoreCase(data.get("Shipping_Charges"))) {
						reporter.SuccessReport("Shipping Charges", "Shipping Field Existed  with $0.00 Amount", "");
					} else {
						reporter.failureReport("Shipping Charges", "Shipping Field Existed, amount is not $0.00", "");
					}
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.navigateBackToCMT();
					cmtLib.DeselectAllDesignatedShippingOptions();

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					System.out.println(e.getMessage());
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("FreightFree", "SBP02", ReportStatus.strMethodName, intCounter,
						browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("FreightFree", "SBP02", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}