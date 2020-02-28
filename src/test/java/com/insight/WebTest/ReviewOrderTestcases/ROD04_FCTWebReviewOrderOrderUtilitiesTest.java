package com.insight.WebTest.ReviewOrderTestcases;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ROD04_FCTWebReviewOrderOrderUtilitiesTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib=new ProductDisplayInfoLib();
	CMTLib cmtLib=new CMTLib();
	SearchLib searchLib=new SearchLib();
	CommonLib commonLib=new CommonLib();
	CartLib cartLib=new CartLib();
	ProductDisplayInfoLib prodLib=new ProductDisplayInfoLib();

	// #############################################################################################################
	// #    Name of the Test         : ROD04_FCTWebReviewOrderOrderUtilities
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order basic
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ROD04(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD04_FCTWebReviewOrderOrderUtilities", TestData, "Web_Review_Order");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD04_FCTWebReviewOrderOrderUtilities", TestData, "Web_Review_Order", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("FCTWebReviewOrderOrderUtilities");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
								"");


						// Login to CMT  >>  Enable Saved Carts / Order Templates and Enable Quotes
						cmtLib.loginToCMTSearchForUserAndSelect(data.get("Header"), data.get("WebGrp"), data.get("WebGrp_Name"), data.get("Manage_Web_Grp_Options"), data.get("LnameEmailUname"), data.get("ContactName"));
						String[] permissions = data.get("Set_Permission").split(",");
						for (i = 0; i < permissions.length; i++) {
							cmtLib.setPermissions(data.get("Menu_Name"), permissions[i]);
						}
						// Login As to Web UAT
						cmtLib.loginAsAdminCMT();
						searchLib.searchInHomePage(data.get("SearchText1"));
						commonLib.verifyDisplayedProductDetails(data.get("SearchText1"));

						// Cart verification
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						cartLib.verifyItemAddedInCartByMfrNumber(data.get("SearchText1"));

						// proceed To Checkout >> Fill Additional Information section >>>
						// Fill Line level Information >>> Fill Order and Item Info page -
						// Review Order
						proceedToCheckout();
						addLineLevelInformation(data.get("RP_LNL_Txt"), data.get("WG_LNL_Txt"));
						clickContinueOnLineLevelInfo();
						shippingBillPayContinueButton();
						shippingOptionsCarrierSelection();
						shippingBillPayContinueButton();
						selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PO_Number"));
						cartLib.ClickExportCartAndVerify(data.get("Order_Utilities"));
						verifySaveOrderTemplateExistsOnPlaceOrderPage(data.get("Permission_Status1")); //save  Order Template, Save cart contents links should display

						// Navigate back to CMT
						cmtLib.navigateBackToCMT();

						// Disable Saved Carts / Order Templates and Enable Quotes.

						for (i = 0; i < permissions.length; i++) {
							cmtLib.setPermissionsToDisable(data.get("Menu_Name"), permissions[i]);
						}

						// Login As to Web UAT
						cmtLib.loginAsAdminCMT();
						searchLib.searchInHomePage(data.get("SearchText2"));
						commonLib.verifyDisplayedProductDetails(data.get("SearchText2"));

						// Cart verification
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						cartLib.verifyItemAddedInCartByMfrNumber(data.get("SearchText2"));

						// proceed To Checkout >> Fill Additional Information section >>>
						// Fill Line level Information >>> Fill Order and Item Info page -
						// Review Order
						proceedToCheckout();
						addLineLevelInformation(data.get("RP_LNL_Txt"), data.get("WG_LNL_Txt"));
						shippingBillPay(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"),
								data.get("Year"), data.get("PONumber"));
						verifySaveOrderTemplateExistsOnPlaceOrderPage(data.get("Permission_Status2")); // save  Order Template, Save cart contents links should not exist						//fnCloseTest();
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("ROD", "ROD04", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("ROD", "ROD04", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}


