package com.insight.WebTest.ReviewOrderTestcases;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class ROD07_FCTWebReviewExportIPSTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib=new ProductDisplayInfoLib();
	CMTLib cmtLib=new CMTLib();
	SearchLib searchLib=new SearchLib();
	CommonLib commonLib=new CommonLib();
	CartLib cartLib=new CartLib();
	ProductDisplayInfoLib prodLib=new ProductDisplayInfoLib();

	// #############################################################################################################
	// #    Name of the Test         : ROD07_FCTWebReviewExportIPS
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order basic
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ROD07(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD07_FCTWebReviewExportIPS", TestData, "Web_Review_Order");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD07_FCTWebReviewExportIPS", TestData, "Web_Review_Order", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("FCTWebReviewExportIPS");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
								"");



						// Login to CMT and disable override_payment_options;off"
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup( data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions"));
						cmtLib. hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"),data.get("ContactName"));

						// Login As to UAT Web
						cmtLib.loginAsAdminCMT();

						searchLib.selectNewcontract(data.get("Contract_Name1"));
						searchLib.searchInHomePage(data.get("SearchText"));
						prodLib.selectFirstProductAddToCartAndVerifyCart();
						commonLib.updateCartQuantity(data.get("Quantity"));
						searchLib.selectNewcontract(data.get("Contract_Name2"));  // Selecting second contract
						searchLib.searchInHomePage(data.get("SearchText"));
						prodLib.selectFirstProductAddToCartAndVerifyCart();
						commonLib.updateCartQuantity(data.get("Quantity"));

						proceedToCheckout();
						enterReportingDetailsInLineLevelInfoSection(data.get("REPORTING FIELD_4"), data.get("REPORTING FIELD_5"), data.get("REPORTING FIELD_6"));

						shippingBillPayContinueButton();   // Click continue on Shipping address
						shippingOptionsCarrierSelection();   // Click continue on Shipping options
						shippingBillPayContinueButton();   // Billing address continue button
						selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PO_Number"));
						clickOnReviewOrderButton(); // Click Review order button on payment info

						// ******** Need To Add verify Export Excel ***************//

						int rowNumber 		= 1; 		// zero based index
						String sfile = "C:/Users/e002542/Downloads/exportCart.xls";
						List<String> downloadedExcelContent = CommonLib.readRowFromExcel(sfile, data.get("Sheet_Name"),rowNumber);
						List<String> acutalContent          = Arrays.asList(data.get("Column_Headers").split(","));

						System.out.println("Compare content"+downloadedExcelContent.equals(acutalContent));

						cartLib.verifyTheExportedCart(downloadedExcelContent, acutalContent);
						// -- Verify excel columns: (MFR Part Number, Insight Part, Product, Stock, Quantity, Unit Price, Total Price,Contract Title, Contract Number,  REPORTING FIELD 4,5,6,)
						cartLib.ClickExportCartAndVerify(data.get("Order_Utilities"));
						commonLib.clickLogOutLink(data.get("Logout"));
						//fnCloseTest();
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("ROD", "ROD07", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("ROD", "ROD07", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}


