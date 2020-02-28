package com.insight.WebTest.QuoteHistory;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.QuoteHistoryLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class QTH05_QuoteHistoryDirectBillCartTest  extends QuoteHistoryLib {

	// #############################################################################################################
			// # Name of the Test :QuoteHistoryDirectBillCartTest
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
			public void TC_QTH05(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
				
				int counter = 0;
				try {
										
							int intStartRow = StartRow;
							int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_QTH05QuoteHistoryDirectBillCartTest", TestDataInsight, "Quote_History");
							for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
								try {
									
									counter = intCounter;
									fnOpenTest();
									ReportStatus.fnDefaultReportStatus();
									ReportControl.intRowCount = intCounter;
									Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_QTH05QuoteHistoryDirectBillCartTest", TestDataInsight, "Quote_History", intCounter);
									TestEngineWeb.reporter.initTestCaseDescription("QuoteHistoryDirectBillCartTest");

	 
							CMTLib cmtLib = new CMTLib();
							SearchLib searchLib = new SearchLib();
							OrderLib orderLib=new OrderLib();
							CanadaLib canadaLib=new CanadaLib();
							CartLib cartLib=new CartLib();
							ShipBillPayLib shipbLib = new ShipBillPayLib();
							InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
							MarriottIntlCorpLib marriottIntlCorpLib=new MarriottIntlCorpLib();
							CommonLib commonLib = new CommonLib();
							cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
									data.get("LnameEmailUname"), data.get("ContactName"));
							cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));					
							// Login As to Web UAT
							cmtLib.loginAsAdminCMT();
							cmtLib.loginVerification(data.get("ContactName"));							
							commonLib.searchProduct(data.get("ProductName"));
							cartLib.selectFirstProductDisplay();
							commonLib.addToCartAndVerify();							
							// view cart
							commonLib.clickCart();
							cartLib.verifyCartBreadCrumb();
							// add product by quick shop
							verifyAdditemsbyQuickshop(data.get("QuickShopPart"));
							verifyQuickShopErrorMsg();
							orderLib.createQuote(data.get("Quote_Name"));
							String refNumber = orderLib.getQuoteReferenceNumber();
							canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),  data.get("Tools_Menu_DD"));							
							verifyQuoteHistory();
							canadaLib.clickOnInvoiceHistory();
							quickSearchAndVerifySearchResults(data.get("SearchBy"),refNumber);
							verifyAndClickQuoteNumberOnHistory();
							verifyQuoteDetails();						
							verifyConvertQuoteButton();	
							verifyErrorMsg();
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
					ReportStatus.fnUpdateResultStatus("QuoteHistoryDirectBillCartTest", "TC_QTH05", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
	            finally {
	            	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("QuoteHistoryDirectBillCartTest", "TC_QTH05", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}

}
