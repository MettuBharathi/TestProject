package com.insight.WebTest.InvoiceHistory;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class IVH09_InvoiceHistoryViewPDFInvoiceDetailsIPSTest extends InvoiceHistoryLib {
	// #############################################################################################################
	// # Name of the Test :IVH09_InvoiceHistoryViewPDFInvoiceDetailsIPS
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// #
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void IVH09(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "IVH09_InvoiceHistoryViewPDFInvoiceDetailsIPS",
					TestDataInsight, "Invoice_History");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo(
							"IVH09_InvoiceHistoryViewPDFInvoiceDetailsIPS", TestDataInsight, "Invoice_History",
							intCounter);
					TestEngineWeb.reporter.initTestCaseDescription(
							"IVH09" + " InvoiceHistoryViewPDFInvoiceDetailsIPS ");
					reporter.SuccessReport("Iteration Number : ",
							"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
									+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
									+ "  **************",
							"");

					CMTLib cmtLib = new CMTLib();
					CanadaLib canadaLib = new CanadaLib();
					InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
					CommonLib commonLib = new CommonLib();

					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));

					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.permissionFromDD(data.get("Set_Permission"), data.get("Permission_Dropdown_Option"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					closeAccountTools();
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					cmtLib.loginVerification(data.get("ContactName"));
					// Advanced search
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					// calender date
					invoiceHistoryLib.datePickerStartDateCalender(data.get("Date"));
					clickOnAdvancedSearchSearchButton();
					verifySearchResultsAreDisplayed();
					scrollToBottomWithCordinate(data.get("Scroll_length"));
					String invoiceNumber=canadaLib.getInvoiceNumber();
					canadaLib.invoiceNumberLink();
					canadaLib.verifyInvoiceHistoryPageOpened();
					verifyInvoiceDetails(data.get("Invoice_Details"));
					canadaLib.printIcon();
					verifyPrintPopUp();
					canadaLib.closeIcon();
					canadaLib.clickOnDownloadLink();
					canadaLib.verifyDownloadedFile(invoiceNumber);
					commonLib.clickLogOutLink(data.get("Logout_Header"));

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("InvoiceHistoryViewPDFInvoiceDetailsIPS", "IVH09",
						ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("InvoiceHistoryViewPDFInvoiceDetailsIPS", "IVH09",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}
