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

public class IVH08_InvoiceHistoryDetailsTest extends InvoiceHistoryLib {
	// #############################################################################################################
	// # Name of the Test :IVH08_InvoiceHistoryDetails
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # 
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void IVH08(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "IVH08_InvoiceHistoryDetails", TestDataInsight,
					"Invoice_History");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("IVH08_InvoiceHistoryDetails",
							TestDataInsight, "Invoice_History", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription(
							"IVH08_InvoiceHistoryDetails");
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

					cmtLib.loginVerification(data.get("ContactName"));
					 canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
					 data.get("Tools_Menu_DD"));
					 canadaLib.verifyInvoiceHistoryPageOpened();
					 canadaLib.clickOnInvoiceHistory();
					 invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectInvoice"),
					 data.get("InvoiceNumber"));
					 canadaLib.invoiceNumberLink();
					 canadaLib.verifyInvoiceHistoryPageOpened();
					 verifyHeaderLevelInfo();
					 verifyLineLevelInfo();
					 verifyLicenseProofLinkAndClick();
					 verifyLicenseProofPopUp();
					 verifyLicenseProofPopUpDetails(data.get("Invoice_Number"),data.get("Inovice_date"),data.get("Sales_Order_Number"),data.get("Customer_PO_Number"));
					
					// Quick search
					 canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
					 data.get("Tools_Menu_DD"));
					 canadaLib.verifyInvoiceHistoryPageOpened();
					 canadaLib.clickOnInvoiceHistory();
					 invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectInvoice"),
					 data.get("InvoiceNumber1"));
					 String invoiceNumber=canadaLib.getInvoiceNumber();
					 canadaLib.invoiceNumberLink();
					 canadaLib.verifyInvoiceHistoryPageOpened();
					 clickEmailLink();
					 sendToColleauge(data.get("YourName"),data.get("YourEmail"),data.get("RecipientEmail"),data.get("YourComments"));
					 canadaLib.printIcon();
					 verifyPrintPopUp();
					 canadaLib.closeIcon();
					 canadaLib.clickOnDownloadLink();
					 canadaLib.verifyDownloadedFile(invoiceNumber);

					/// View Preview and See full invoice
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					// calender date
					invoiceHistoryLib.datePickerStartDateCalender(data.get("Date"));
					clickOnAdvancedSearchSearchButton();
					verifySearchResultsAreDisplayed();
					clickInvoicePreviewLink();
					verifyInvoicePreviewPopUp();
					verifyInvoicePreviewDetails(data.get("Invoice_details"));
					verifyInvoicePreviewShipToAndBillTo();
					verifyInvoicePreviewHeaderDetails(data.get("Header_Details"));
					verifyInvoicePreviewFootDetails(data.get("Foot_Details"));
					clickOnInvoiceFullPreview();
					canadaLib.verifyInvoiceHistoryPageOpened();
					canadaLib.printIcon();
					verifyPrintPopUp();
					canadaLib.closeIcon();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					//change user
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
					cmtLib.searchForWebGroup(data.get("WebGrp1"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					// calender date
					invoiceHistoryLib.datePickerStartDateCalender(data.get("Date"));
					clickOnAdvancedSearchSearchButton();
					verifySearchResultsAreDisplayed();
					canadaLib.invoiceNumberLink();
					canadaLib.verifyInvoiceHistoryPageOpened();
					clickTrackLink();
					verifyOrderDetailsPage();
					clickCustomerDetailsTab();
					verifyContactName();
					clickBackToOrders();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("InvoiceHistoryDetails", "IVH08", ReportStatus.strMethodName,
						intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("InvoiceHistoryDetails", "IVH08", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}
