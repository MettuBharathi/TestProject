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
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class QTH03_QuoteHistoryViewSearchResultsTest extends QuoteHistoryLib {

	// #############################################################################################################
			// # Name of the Test :QuoteHistoryViewSearchResultsTest
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
			public void TC_QTH03(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
				
				int counter = 0;
				try {
										
							int intStartRow = StartRow;
							int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_QTH03QuoteHistoryViewSearchResults", TestDataInsight, "Quote_History");
							for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
								try {
									
									counter = intCounter;
									fnOpenTest();
									ReportStatus.fnDefaultReportStatus();
									ReportControl.intRowCount = intCounter;
									Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_QTH03QuoteHistoryViewSearchResults", TestDataInsight, "Quote_History", intCounter);
									TestEngineWeb.reporter.initTestCaseDescription("QuoteHistoryViewSearchResults");

	 
							CMTLib cmtLib = new CMTLib();
							SearchLib searchLib = new SearchLib();
							OrderLib orderLib=new OrderLib();
							CanadaLib canadaLib=new CanadaLib();
							CartLib cartLib=new CartLib();
							InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
							MarriottIntlCorpLib marriottIntlCorpLib=new MarriottIntlCorpLib();
							CommonLib commonLib = new CommonLib();
							cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp1"),
									data.get("LnameEmailUname1"), data.get("ContactName1"));
							cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
							cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
							// Login As to Web UAT
							cmtLib.loginAsAdminCMT();
							cmtLib.loginVerification(data.get("ContactName1"));
							// Select First Product and Add to cart
							searchLib.searchInHomePage(data.get("SearchItem1"));
							cartLib.selectFirstProductDisplay();
							commonLib.addToCartAndVerify();
							orderLib.continueToCheckOutOnAddCart();					
							// Create Quote
							orderLib.createQuote(data.get("Quote_Name1"));
							orderLib.getQuoteReferenceNumber();
							canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),data.get("Tools_Menu_DD"));
							verifyQuoteHistoryPageOpened();										
							invoiceHistoryLib.verifyAdvancedSearch();	
							invoiceHistoryLib.verifyQuickSearch();
							commonLib.clickLogOutLink(data.get("Logout_Header"));
							cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp2"),
									data.get("LnameEmailUname2"), data.get("ContactName2"));
							cmtLib.loginAsAdminCMT();
							cmtLib.loginVerification(data.get("ContactName2"));
							canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),data.get("Tools_Menu_DD"));
							invoiceHistoryLib.clickOnAdvancedSearch();
							// calender date
							invoiceHistoryLib.datePickerStartDateCalender(data.get("Date"));
							descInAdvSearch();
							invoiceHistoryLib.clickOnAdvancedSearchSearchButton();
						    String rowNumber 		= "1"; 		// zero based index
							clickExportToExcelButton();
							verifyExportFileInQuoteHistory(data.get("SheetName"),rowNumber,data.get("ColumnHeaders"));
							quoteNumberLink();
							canadaLib.verifyInvoiceHistoryPageOpened();
							verifyQuoteDetails(data.get("Quote_details"));
							getDelailsOnQuotePage();
							verifyPrint();
							verifyEmailIcon();
							verifyEditThisQuoteIcon();
							verifyDeleteThisQuoteIcon();
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
							ReportStatus.fnUpdateResultStatus("QuoteHistoryViewSearchResults", "TC_QTH03", ReportStatus.strMethodName, 1, browser);
							throw new RuntimeException(e);
						}
			            finally {
			            	ReportControl.fnEnableJoin();
							ReportStatus.fnUpdateResultStatus("QuoteHistoryViewSearchResults", "TC_QTH03", ReportStatus.strMethodName, counter, browser);
							fnCloseTest();
							ReportControl.fnNextTestJoin(nextTestJoin);
						}
					}

}
