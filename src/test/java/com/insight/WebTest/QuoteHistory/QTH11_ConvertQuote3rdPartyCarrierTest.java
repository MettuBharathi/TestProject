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
import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class QTH11_ConvertQuote3rdPartyCarrierTest extends QuoteHistoryLib {
	
	CMTLib cmtLib = new CMTLib();
	CanadaLib canadaLib = new CanadaLib();
	InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
	CommonLib commonLib = new CommonLib();
	ShipBillPayLib shipbLib = new ShipBillPayLib();
	CartLib cartLib = new CartLib();
	OrderLib orderLib = new OrderLib();
	QuoteHistoryLib quoteHistoryLib=new QuoteHistoryLib();
	HomeLib Homelib=new HomeLib();
	loginLib loginlib=new loginLib();
	
	// #############################################################################################################
	// # Name of the Test :ConvertQuote3rdPartyCarrier
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
	public void TC_QTH11(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
								
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "QTH11_ConvertQuote3rdPartyCarrier", TestDataInsight, "Quote_History");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("QTH11_ConvertQuote3rdPartyCarrier", TestDataInsight, "Quote_History", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("ConvertQuote3rdPartyCarrier");

					navigateToApplication("SMART");
					Thread.sleep(4000);
					loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"));//QTPSVC,!OffShore!​
					Thread.sleep(4000);
					Homelib.clickCreateQuoteButton();
					Thread.sleep(4000);
					Homelib.enterSoldTo(data.get("SoldToAcct"));//0010285059
					Homelib.selectOrg(data.get("SalesOrg"));//2400
					Homelib.EnterSalesDocNumber(data.get("SalesDoc"));//0220702721
					Homelib.clickOnSalesDocSearch();
					Homelib.clickSalesDocDropdown(data.get("DDOption"));//Copy to Quote
					Thread.sleep(4000);
					Homelib.clickYesButtontocloseDocument();
					Thread.sleep(4000);
					Homelib.clickSideBarSmart();
					Homelib.clickonSaveasQuote();
					Homelib.enterCancelButtonInPoupHdr();
					Thread.sleep(4000);
					String QuoteNum= Homelib.getSaveQuoteNumber();
					Thread.sleep(4000);
					Homelib.clickSideBarSmart();
					Homelib.clickClosthedocument(QuoteNum);
					Thread.sleep(4000);
					Homelib.clickYesButtontocloseDocument();
					Thread.sleep(4000);
					Homelib.clickClosthedocument(data.get("DoccumentType"));//Create Document
					Homelib.clickYesButtontocloseDocument();
					Thread.sleep(4000);
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));

					// Enable Quotes Check Box
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification(data.get("ContactName"));				
												
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					verifyQuoteHistory();				      
				
					//quick search
					orderLib.searchByInQuoteHistory(QuoteNum,data.get("Quote_DD_option"));
					verifyQuoteDetails();	
					orderLib.convertQuote();
					canadaLib.verifyPlaceCartLabel();
					//proceed to checkout
					orderLib.proceedToCheckout();
					cartLib.clickOnContinueButtonInAddInformtion();
					
					orderLib.clickContinueOnLineLevelInfo();
					canadaLib.verifySBP();
					orderLib.shippingBillPayContinueButton();
					//cartLib.selectCarrier(data.get("Carrier_Option"));
					cartLib.selectShippingMeethod(data.get("Shipping_Methods"));
					
					orderLib.shippingBillPayContinueButton();
					orderLib.shippingBillPayContinueButton();
					orderLib.selectPaymentInfoMethodCreditCard(data.get("cardNumber"),data.get("cardName"),data.get("month"),data.get("year"),data.get("PO_Number"));
					orderLib.clickOnReviewOrderButton();
					
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
					ReportStatus.fnUpdateResultStatus("ConvertQuote3rdPartyCarrier", "TC_QTH11", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
	            finally {
	            	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("ConvertQuote3rdPartyCarrier", "TC_QTH11", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}

}
