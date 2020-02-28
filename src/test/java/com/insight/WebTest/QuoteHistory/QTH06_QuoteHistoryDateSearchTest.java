package com.insight.WebTest.QuoteHistory;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.QuoteHistoryLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class QTH06_QuoteHistoryDateSearchTest extends QuoteHistoryLib {
	// #############################################################################################################
	// # Name of the Test :QTH06_QuoteHistoryDateSearch
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
	public void TC_QTH06(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		
		int counter = 0;
		try {
								
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "QTH06_QuoteHistoryDateSearch", TestDataInsight, "Quote_History");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("QTH06_QuoteHistoryDateSearch", TestDataInsight, "Quote_History", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("QuoteHistoryDateSearch");

		
					CMTLib cmtLib = new CMTLib();
					CanadaLib canadaLib = new CanadaLib();
					InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
					CommonLib commonLib = new CommonLib();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));

					// Enable Quotes Check Box
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification(data.get("ContactName"));
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));

					// Perform Quote Search
					verifyQuoteHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					// calender date
					invoiceHistoryLib.datePickerStartDateCalender(data.get("FromDate"));
					clickOnAdvancedSearchSearchButton();
					invoiceHistoryLib.verifySearchResultsAreDisplayed();
					quoteNumberLink();
					canadaLib.verifyInvoiceHistoryPageOpened();
					verifyQuoteDetails(data.get("Quote_details"));
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
			ReportStatus.fnUpdateResultStatus("QuoteHistoryDateSearch", "TC_QTH06", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("QuoteHistoryDateSearch", "TC_QTH06", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
