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
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class QTH09_ConvertSmartQuoteOnWebTest extends QuoteHistoryLib {
	// #############################################################################################################
	// # Name of the Test :QTH09_ConvertSmartQuoteOnWebTest
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
	public void TC_QTH09(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		
		int counter = 0;
		try {
								
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_QTH09ConvertSmartQuoteOnWeb", TestDataInsight, "Quote_History");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_QTH09ConvertSmartQuoteOnWeb", TestDataInsight, "Quote_History", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("ConvertSmartQuoteOnWeb");

		
					CMTLib cmtLib = new CMTLib();
					CanadaLib canadaLib = new CanadaLib();
					InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
					CommonLib commonLib = new CommonLib();
					HomeLib Homelib=new HomeLib();
					loginLib loginlib=new loginLib();					
					SearchLib searchLib = new SearchLib();
					OrderLib orderLib=new OrderLib();					
					CartLib cartLib=new CartLib();
					ShipBillPayLib sbpLib=new ShipBillPayLib();
		            //LogintoSMART
					navigateToApplication("SMART");
					Thread.sleep(4000);
					loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"));//QTPSVC,!OffShore!​
					Thread.sleep(4000);
					Homelib.clickCreateQuoteButton();
					Thread.sleep(4000);
					Homelib.enterSoldTo(data.get("SoldToAcct"));//10285059
					Homelib.selectOrg(data.get("SalesOrg"));//2400
					Homelib.Addmaterail(data.get("Material1"));//OCN1
					Homelib.Addmaterail(data.get("Material1"));//OCN1
					Homelib.Addmaterail(data.get("Material1"));//OCN1
					Homelib.clickAdvancedHeader();
					Homelib.clickAdvancedHeaderTab(data.get("Tab1"));//Programs
					Homelib.clickonEnrolmentIdatProgramsTab(data.get("Enrolmentid"));//S9662270
					Homelib.enrollmentDropDown(data.get("Option"));//S9662270 - WEB test I changed the name
					Homelib.clickOnProductSearchButton();
					Homelib.verifyResultsofMaterailIdofKeyWordSearch(data.get("KeyWord1"));//SLP
					Homelib.clickOnAddToOrderButton();
					Homelib.loadingSymbol();
					Homelib.closebuttonInProductSearch();
					Homelib.clickonConXSystem(data.get("ItemNum"));//000040
					Homelib.clickDoneButton();
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
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					// Enable Quotes Check Box
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification(data.get("ContactName"));				
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),  data.get("Tools_Menu_DD"));							
					verifyQuoteHistory();			
			        quickSearchAndVerifySearchResults(data.get("SearchBy"),QuoteNum);
					verifyAndClickQuoteNumberOnHistory();
					verifyQuoteDetails();						
					orderLib.convertQuote();
					cartLib.verifyCartBreadCrumb();
					// Proceed to checkout
					orderLib.proceedToCheckout();
					orderLib.clickOnAdditionalInfoContinueButton() ;
					orderLib.clickContinueOnLLIAndShipBillPaySections();
					orderLib.addNewCardInPayment(data.get("cardNumber"), data.get("cardName"), data.get("month"),
							data.get("year"), data.get("poNumber"));
					orderLib.clickOnReviewOrderButton();
					// Place Order
					String summaryAmount = cartLib.getSummaryAmountInCart();
					orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
					// Verify Receipt
					orderLib.verifyReceiptVerbiage();
					String ReferenceNumber = sbpLib.getReferenceNum();
					orderLib.clickOrderDetailsLinkOnReceiptPage();
					verifyContactName(data.get("ContactName"));
					canadaLib.clickOnInvoiceHistory();
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),  data.get("Tools_Menu_DD"));	
					quickSearchAndVerifySearchResults(data.get("SearchBy1"),ReferenceNumber);
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
					ReportStatus.fnUpdateResultStatus("ConvertSmartQuoteOnWebTest", "TC_QTH09", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
	            finally {
	            	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("ConvertSmartQuoteOnWebTest", "TC_QTH09", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}

}