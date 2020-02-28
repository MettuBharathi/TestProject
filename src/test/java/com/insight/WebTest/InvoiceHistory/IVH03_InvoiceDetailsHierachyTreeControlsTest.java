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

public class IVH03_InvoiceDetailsHierachyTreeControlsTest extends InvoiceHistoryLib {
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void IVH03(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "IVH03_InvoiceDetailsHierachyTreeControls",
					TestDataInsight, "Invoice_History");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("IVH03_InvoiceDetailsHierachyTreeControls",
							TestDataInsight, "Invoice_History", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IVH03_InvoiceDetailsHierachyTreeControls");
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
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));

					cmtLib.clickOnloginAs();
					switchToChildWindow();

					cmtLib.loginVerification(data.get("ContactName"));
					closeAccountTools();
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					scrollUp();
					clickShowAccountHirerachy();
					verifyAccountHirearchyPopUp();
					verifyTree();
					closeHierarchyPopUp();

					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					scrollUp();
					verifySelectedOptionInAccountSelectionDD(data.get("Default_Option"));
					// ######## GGP Level ################################
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					scrollUp();
					verifySelectedOptionInAccountSelectionDD(data.get("Default_Option"));
					closeAccountHirearchyDropdow();
					setAccountHirerachydropdown(data.get("GGP"));
					clickShowAccountHirerachy();
					verifyAccountHirearchyPopUp();
					verifyTree();
					getAccountNumber(data.get("Account_Number"));
					getAccountName(data.get("Account_Name"));
					closeHierarchyPopUp();
					// ######## GGP Level default account ################################
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					scrollUp();
					verifySelectedOptionInAccountSelectionDD(data.get("Default_Option"));
					closeAccountHirearchyDropdow();
					setAccountHirerachydropdown(data.get("GGP"));
					clickShowAccountHirerachy();
					verifyAccountHirearchyPopUp();
					verifyTree();
					getAccountNumber(data.get("Account_Number1"));
					getAccountName(data.get("Account_Name1"));
					verifyDefaultSelectedAccount(data.get("Default_Account"));
					closeHierarchyPopUp();
					// ######## RP Level default account ################################
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					scrollUp();
					 verifySelectedOptionInAccountSelectionDD(data.get("Default_Option"));
					 closeAccountHirearchyDropdow();
					setAccountHirerachydropdown(data.get("RP"));
					clickShowAccountHirerachy();
					verifyAccountHirearchyPopUp();
					verifyTree();
					verifyDefaultSelectedAccount(data.get("Default_Account1"));
					closeHierarchyPopUp();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					System.out.println(e.getMessage());
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("InvoiceDetailsHierachyTreeControls", "IVH03",
						ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("InvoiceDetailsHierachyTreeControls", "IVH03", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}
