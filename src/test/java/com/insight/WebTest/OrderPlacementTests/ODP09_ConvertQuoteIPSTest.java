package com.insight.WebTest.OrderPlacementTests;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ODP09_ConvertQuoteIPSTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodLib = new ProductDisplayInfoLib();
	OrderLib orderLib =new OrderLib();

	// #############################################################################################################
	// #    Name of the Test         : ODP09_ConvertQuoteIPS
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order  Confirmations
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ODP09(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP09_ConvertQuoteIPS", TestData,"Web_Order_Placement");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP09_ConvertQuoteIPS", TestData,"Web_Order_Placement", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("ConvertQuoteIPS");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
								"");

						// Login to CMT Enable > view_quotes;ON" and Disable quote_track_only_my_quotes ; OFF;
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));

						// Login As to Web UAT
						cmtLib.loginAsAdminCMT();

						// Login Verification
						cmtLib.loginVerification(data.get("ContactName"));

						// Select First Product and Add to cart
						searchLib.searchInHomePage(data.get("SearchText1"));
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						// Verify Contract is present in the Cart Page
						cartLib.verifyTheItemIsAddedUnderContractInCartPage();

						// select new contract
						searchLib.selectNewcontract(data.get("Contract_Name"));

						// Add Item to the Cart
						searchLib.searchInHomePage(data.get("SearchText2"));
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();

						// Verify selected contract in cart page
						cartLib.verifyContractNameInCart(data.get("Contract_Name"));

						// Create Quote
						createQuote(data.get("Quote_Name"));
						String refNumber=getQuoteReferenceNumber();

						// Navigate to Quote History
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						searchByInQuoteHistory(refNumber,data.get("DD_Option"));
						convertQuote();
						cartLib.verifyCartBreadCrumb();

						// Click on Proceed to checkout
						proceedToCheckout();

						// Enter reporting details
						enterReportingDetailsInLineLevelInfoSection(data.get("REPORTING FIELD_4"), data.get("REPORTING FIELD_5"), data.get("REPORTING FIELD_6"));

						shippingBillPayContinueButton();  // continue button on Shipping address
						shippingOptionsCarrierSelection();  // carrier selection or continue in shipping options
						shippingBillPayContinueButton();  // Continue on billing address section

						// Fill payment Info
						selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));

						// Review Order
						clickOnReviewOrderButton();

						// Place Order
						String summaryAmount=cartLib.getSummaryAmountInCart();
						// Verify order and Date
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);

						// Verify Receipt
						verifyReceiptVerbiage();
						clickOrderDetailsLinkOnReceiptPage();

						// Verify selected contract in cart of Receipt page
						cartLib.verifyContractNameInCart(data.get("Contract_Name"));

						//Logout
						commonLib.clickLogOutLink(data.get("Logout"));

						//fnCloseTest();
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("ODP", "ODP09", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("ODP", "TC_ODP09", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}


