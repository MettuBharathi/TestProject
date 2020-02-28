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

public class IVH01_InvoiceQuickSearchTest extends InvoiceHistoryLib {
	// #############################################################################################################
	// # Name of the Test :IVH01_InvoiceQuickSearch
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void IVH01(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "IVH01_InvoiceQuickSearch", TestDataInsight,
					"Invoice_History");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("IVH01_InvoiceQuickSearch",
							TestDataInsight, "Invoice_History", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription(
							"IVH01" + " InvoiceQuickSearch " );
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
					// ######## GGP Level ################################
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.permissionFromDD(data.get("Set_Permission"), data.get("Permission_Dropdown_Option"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification(data.get("ContactName"));
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					canadaLib.clickOnInvoiceHistory();
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectOrder"),
							data.get("OrderNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectReference"),
							data.get("ReferenceNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectPurchaseOrder"),
							data.get("PurchaseNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectInvoice"),
							data.get("InvoiceNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectAssetTag"),
							data.get("AssetTag"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectSerial"),
							data.get("SerialNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectVendorPO"),
							data.get("PONumber"));
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					// ######## GP Level ################################
					cmtLib.navigateBackToCMT();
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.permissionFromDD(data.get("Set_Permission"), data.get("Permission_Dropdown_Option1"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification(data.get("ContactName"));
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					canadaLib.clickOnInvoiceHistory();
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectOrder"),
							data.get("OrderNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectReference"),
							data.get("ReferenceNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectPurchaseOrder"),
							data.get("PurchaseNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectInvoice"),
							data.get("InvoiceNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectAssetTag"),
							data.get("AssetTag"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectSerial"),
							data.get("SerialNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectVendorPO"),
							data.get("PONumber"));
					commonLib.clickLogOutLink(data.get("Logout_Header"));

					// ######## RP Level ################################
					cmtLib.navigateBackToCMT();
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.permissionFromDD(data.get("Set_Permission"), data.get("Permission_Dropdown_Option2"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification(data.get("ContactName"));
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					canadaLib.clickOnInvoiceHistory();
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectOrder"),
							data.get("OrderNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectReference"),
							data.get("ReferenceNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectPurchaseOrder"),
							data.get("PurchaseNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectInvoice"),
							data.get("InvoiceNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectAssetTag"),
							data.get("AssetTag"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectSerial"),
							data.get("SerialNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectVendorPO"),
							data.get("PONumber"));
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					// ######## ALL MY ACCOUNTS ################################
					cmtLib.navigateBackToCMT();
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.permissionFromDD(data.get("Set_Permission"), data.get("Permission_Dropdown_Option3"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification(data.get("ContactName"));
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					canadaLib.clickOnInvoiceHistory();
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectOrder"),
							data.get("OrderNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectReference"),
							data.get("ReferenceNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectPurchaseOrder"),
							data.get("PurchaseNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectInvoice"),
							data.get("InvoiceNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectAssetTag"),
							data.get("AssetTag"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectSerial"),
							data.get("SerialNumber"));
					invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectVendorPO"),
							data.get("PONumber"));
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					// ######## GGP Level ################################
					cmtLib.navigateBackToCMT();
					cmtLib.permissionFromDD(data.get("Set_Permission"), data.get("Permission_Dropdown_Option"));
					

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("InvoiceQuickSearch", "IVH01", ReportStatus.strMethodName,
						intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("InvoiceQuickSearch", "IVH01", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}
