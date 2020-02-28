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

public class ROD11_FCTWebReviewOrderESDPartsTest extends OrderLib{

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
		public void TC_ROD11(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD11_FCTWebReviewOrderESDParts", TestData, "Web_Review_Order");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD11_FCTWebReviewOrderESDParts", TestData, "Web_Review_Order", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("FCTWebReviewOrderESDParts");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
								"");


						// Login to CMT enable Display Additional Notes during the transaction process,Allow File Upload during Checkout,Display Invoice Notes during the transaction process settings at web group level.
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup( data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("lnameEmailUname"),data.get("ContactName"));
						cmtLib.loginAsAdminCMT();

						// Login As to UAT web
						searchLib.searchInHomePage(data.get("SearchText"));
						cartLib.selectFirstProductDisplay();


						// Add a item >>  proceed To Checkout >> place order >> Review Order
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						Thread.sleep(3000);
						String ActualTax= getText(DEFAULT_TAX_AMOUNT, "Tax displayed after adding 1st product");
						proceedToCheckout();
						addAdditionalInformation(data.get("Url"), data.get("RP_HDL_Txt"), data.get("WG_HDL_Txt"), data.get("Additional_Notes"), data.get("Invoice_Notes"));
						AddNewshippingAddress(data.get("link"),data.get("companyName"),data.get("street"),data.get("city"),data.get("zipcode"),data.get("state"));
						termsInPaymentInfo(data.get("PONumber"));
						//click on edit cart item
						clickOnProdDescOnPlaceOrderScreen();
						Thread.sleep(5000);
						searchLib.searchInHomePage(data.get("SearchText1"));

						// Add a item with licence >>  proceed To Checkout >> place order >> Review Order
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						proceedToCheckout();
						addAdditionalInformation(data.get("Url"), data.get("RP_HDL_Txt"), data.get("WG_HDL_Txt"), data.get("Additional_Notes"), data.get("Invoice_Notes"));
						addLineLevelInformation(data.get("RP_LNL_Txt"), data.get("WG_LNL_Txt"));
						shippingBillPay(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"), data.get("PONumber1"));

						//  Verify tax amount is same or not before and after adding licence to the product
						verifyTheTaxForSearchTerm(ActualTax);

						//fnCloseTest();
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("ROD", "ROD11", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("ROD", "ROD11", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}


