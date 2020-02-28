package com.insight.WebTest.QuoteHistory;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.QuoteHistoryLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class QTH13_CQTRecalculatesOnFreightChangeTest extends QuoteHistoryLib {
	// #############################################################################################################
		// # Name of the Test :QTH13_CQTRecalculatesOnFreightChange
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION : This method is to perform Quote History search with date
		// operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_QTH13(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
									
						int intStartRow = StartRow;
						int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "QTH13_CQTRecalculatesOnFreightChange", TestDataInsight, "Quote_History");
						for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
							try {
								
								counter = intCounter;
								fnOpenTest();
								ReportStatus.fnDefaultReportStatus();
								ReportControl.intRowCount = intCounter;
								Hashtable<String, String> data = TestUtil.getDataByRowNo("QTH13_CQTRecalculatesOnFreightChange", TestDataInsight, "Quote_History", intCounter);
								TestEngineWeb.reporter.initTestCaseDescription("CQTRecalculatesOnFreightChange");

		

						CMTLib cmtLib = new CMTLib();
						CanadaLib canadaLib = new CanadaLib();
						InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
						CommonLib commonLib = new CommonLib();
						ShipBillPayLib shipbLib = new ShipBillPayLib();
						CartLib cartLib = new CartLib();
						OrderLib orderLib = new OrderLib();
						QuoteHistoryLib quoteHistoryLib=new QuoteHistoryLib();
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// Enable Quotes Check Box
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission3"));
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						cmtLib.loginVerification(data.get("ContactName"));
						
						//search for a product
						commonLib.searchProduct(data.get("Search_Item"));
						commonLib.addToCartAndVerify();
						commonLib.continueToShopping();
						commonLib.clickCart();
						orderLib.proceedToCheckout();
						cartLib.clickOnContinueButtonInAddInformtion();
						
						orderLib.clickContinueOnLineLevelInfo();
						canadaLib.verifySBP();
						shipbLib.selectUSShippingAddressesAccount();
						orderLib.shippingBillPayContinueButton();
						shipbLib.selectshippingcarrierAsRequired(data.get("Carrier_Option"),data.get("Shipping_Methods"));
						scrollUp();
						quoteHistoryLib.verifyShippingEstimateInCartSummary(data.get("Shiping_Carrier_Verify"));
						
						String shippingEstimateAmount=shipbLib.getShippingEstimateAmount();
						saveAsQuote(data.get("Shipping_Method"));
						String referenceNumber=orderLib.getQuoteReferenceNumber();
						String quoteNumber=quoteHistoryLib.getQuoteNumber();
						
						//quote history
						canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						//quick search
						orderLib.searchByInQuoteHistory(referenceNumber,data.get("Quote_DD_option"));
						orderLib.convertQuote();
						canadaLib.verifyPlaceCartLabel();
						//proceed to checkout
						orderLib.proceedToCheckout();
						cartLib.clickOnContinueButtonInAddInformtion();
						
						orderLib.clickContinueOnLineLevelInfo();
						canadaLib.verifySBP();
						orderLib.shippingBillPayContinueButton();
						cartLib.selectCarrier(data.get("Carrier_Option1"));
						cartLib.selectShippingMeethod(data.get("Shipping_Methods1"));
						//shipbLib.selectshippingcarrierAsRequired(data.get("Carrier_Option1"),data.get("Shipping_Methods1"));
						orderLib.shippingBillPayContinueButton();
						orderLib.shippingBillPayContinueButton();
						orderLib.selectPaymentInfoMethodCreditCard(data.get("cardNumber"),data.get("cardName"),data.get("month"),data.get("year"),data.get("PO_Number"));
						orderLib.clickOnReviewOrderButton();
						quoteHistoryLib.verifyShippingEstimateInCartSummary(data.get("Shiping_Carrier_Verify1"));
						String shippingEstimateAmount1=shipbLib.getShippingEstimateAmount();
						String summaryAmount = cartLib.getSummaryAmountInCart();
						orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
						shipbLib.clickOrderDetailsButtonInREceipt();
						shipbLib.verifyShippingCarrierAFterReviewOrder(data.get("Shiping_Carrier_Verify_Receipt"));
						commonLib.clickLogOutLink(data.get("Logout_Header"));

							} catch (Exception e) {
								ReportStatus.blnStatus = false;
								gErrorMessage = e.getMessage();
								gTestStatus = false;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						ReportStatus.blnStatus = false;
						gErrorMessage = e.toString();
						gTestStatus = false;
						ReportStatus.fnUpdateResultStatus("CQTRecalculatesOnFreightChange", "TC_QTH13", ReportStatus.strMethodName, 1, browser);
						throw new RuntimeException(e);
					}
			        finally {
			        	ReportControl.fnEnableJoin();
						ReportStatus.fnUpdateResultStatus("CQTRecalculatesOnFreightChange", "TC_QTH13", ReportStatus.strMethodName, counter, browser);
						fnCloseTest();
						ReportControl.fnNextTestJoin(nextTestJoin);
					}
				}
}
