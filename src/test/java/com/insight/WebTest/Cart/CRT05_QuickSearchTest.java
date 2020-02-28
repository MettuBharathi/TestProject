package com.insight.WebTest.Cart;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CRT05_QuickSearchTest extends CartLib {
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	CMTLib cmtLib = new CMTLib();

	// #############################################################################################################
	// # Name of the Test : CRT05_QuickSearch
	// # Migration Customization Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Quick Search operations in the
	// shopping cart page.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test()
	public void Tc_CRT05(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT05_QuickSearch", TestDataInsight, "Web_Cart");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT05_QuickSearch", TestDataInsight,
							"Web_Cart", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("QuickSearch");
					reporter.SuccessReport("Iteration Number : ",
							"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
									+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
									+ "  **************",
							"");
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("Menu_Name"),data.get("Enable_Purchasing_Popup"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.searchProduct(data.get("SearchItem1"));
					cartLib.selectFirstProductDisplay();
					
					commonLib.clickCart();
					
					cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("SearchItem2"), data.get("quantity"));
					cartLib.verifyQuickShopWithInvalidPartNumber(data.get("SearchItem3"));
					cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("SearchItem2"), data.get("quantity"));
					String quantity=getCartQuantity();
					if(Integer.parseInt(data.get("quantity"))<Integer.parseInt(quantity)) {
						reporter.SuccessReport("Quantity is increased on the Cart Page", "Quantity Exists and increased", "");
					}
					else {
						reporter.failureReport("Quantity is increased on the Cart Page", "Quantity Exists and not increased", "");
					}
					
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("QuickSearch", "CRT05",
						ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("QuickSearch", "CRT05", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}