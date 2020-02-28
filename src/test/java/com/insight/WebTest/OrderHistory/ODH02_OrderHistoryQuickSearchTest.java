package com.insight.WebTest.OrderHistory;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.OrderHistoryLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class ODH02_OrderHistoryQuickSearchTest extends OrderHistoryLib{
	// #############################################################################################################
		// # Name of the Test :ODH02_OrderHistoryQuickSearch
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_ODH02(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODH02_OrderHistoryQuickSearch", TestDataInsight,
						"Order_History");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("ODH02_OrderHistoryQuickSearch",
								TestDataInsight, "Order_History", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription(
								"OrderHistoryQuickSearch");
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
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
						cmtLib.permissionFromDD(data.get("Set_Permission"), data.get("Permission_Dropdown_Option"));
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						cmtLib.loginVerification(data.get("ContactName"));
						canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						verifyOrderHistoryPage();
						
						selectQuickSearchDropdown(data.get("SelectOrder"),data.get("OrderNumber"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
						selectQuickSearchDropdown(data.get("SelectReferenceNumber"),data.get("ReferenceNumber"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
						selectQuickSearchDropdown(data.get("SelectPurchaseorder"),data.get("Purchaseorder"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
						selectQuickSearchDropdown(data.get("SelectVendorPONumber"),data.get("VendorPONumber"));
						selectQuickSearchDropdown(data.get("SelectPOreleaseNumber"),data.get("POreleaseNumber"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
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
                        verifyOrderHistoryPage();
						
						selectQuickSearchDropdown(data.get("SelectOrder"),data.get("OrderNumber"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
						selectQuickSearchDropdown(data.get("SelectReferenceNumber"),data.get("ReferenceNumber"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
						selectQuickSearchDropdown(data.get("SelectPurchaseorder"),data.get("Purchaseorder"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
						selectQuickSearchDropdown(data.get("SelectVendorPONumber"),data.get("VendorPONumber"));
						selectQuickSearchDropdown(data.get("SelectPOreleaseNumber"),data.get("POreleaseNumber"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
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
                        verifyOrderHistoryPage();
						
						selectQuickSearchDropdown(data.get("SelectOrder"),data.get("OrderNumber"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
						selectQuickSearchDropdown(data.get("SelectReferenceNumber"),data.get("ReferenceNumber"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
						selectQuickSearchDropdown(data.get("SelectPurchaseorder"),data.get("Purchaseorder"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
						selectQuickSearchDropdown(data.get("SelectVendorPONumber"),data.get("VendorPONumber"));
						selectQuickSearchDropdown(data.get("SelectPOreleaseNumber"),data.get("POreleaseNumber"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
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
                        verifyOrderHistoryPage();
						
						selectQuickSearchDropdown(data.get("SelectOrder"),data.get("OrderNumber"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
						selectQuickSearchDropdown(data.get("SelectReferenceNumber"),data.get("ReferenceNumber"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
						selectQuickSearchDropdown(data.get("SelectPurchaseorder"),data.get("Purchaseorder"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
						selectQuickSearchDropdown(data.get("SelectVendorPONumber"),data.get("VendorPONumber"));
						selectQuickSearchDropdown(data.get("SelectPOreleaseNumber"),data.get("POreleaseNumber"));
						commonLib.spinnerImage();
						verifySearchResultsAreDisplayed();
						commonLib.clickLogOutLink(data.get("Logout_Header"));
						// ######## GGP Level ################################
						cmtLib.navigateBackToCMT();
						cmtLib.permissionFromDD(data.get("Set_Permission"), data.get("Permission_Dropdown_Option"));
						

					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						System.out.println(e.getMessage());
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("OrderHistoryQuickSearch", "ODH02", ReportStatus.strMethodName,
							intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("OrderHistoryQuickSearch", "ODH02", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}


}
