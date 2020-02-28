package com.insight.WebTest.ProductDetails;

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
import com.insight.accelerators.ActionEngine;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class PID04_RecentlyViewedProductsTest extends ActionEngine{
	CommonLib commonLib = new CommonLib(); 
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	ProductDetailLib productdetLib = new ProductDetailLib();
	ProductDisplayInfoLib productDispinfoLib = new ProductDisplayInfoLib();
	// #############################################################################################################
		// # Name of the Test : PID04_RecentlyViewedProducts
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_PID04(int StartRow, String EndRow, boolean nextTestJoin)	throws Throwable {
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID04_RecentlyViewedProductsTest",TestDataInsight, "Product_Detail");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("PID04_RecentlyViewedProducts",TestDataInsight, "Product_Detail", intCounter);
				TestEngineWeb.reporter.initTestCaseDescription("RecentlyViewedProducts");
				reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");
				
				productdetLib.getrecentlyvieweditems(data.get("Search_Item1"));
				// verify recently viewed as expected it should not be visible
				productdetLib.Verifyrecentlyviwedproductslabel();
				productdetLib.getrecentlyvieweditems(data.get("Search_Item2"));
				productdetLib.Verifyrecentlyvieweditems(); // verify recently
				// viewed
				productdetLib.getrecentlyvieweditems(data.get("Search_Item3"));
				String Productname = productdetLib.getrecentlyvieweditems(data.get("Search_Item3"));
				productdetLib.Verifyrecentlyvieweditems();
				// view details productdetLib.Clickonviewdetails(Productname);
				// product info page display
				productdetLib.verifyproductinfopage(data.get("Search_Item3"));
				// back to results productdetLib.Clickonbacktoresults();
				// productinfo page display-------------
				productdetLib.verifyproductinfopage(data.get("Search_Item3"));
				productdetLib.Verifyrecentlyvieweditems();
				// verify recently viewed----------
				productdetLib.getrecentlyvieweditems(data.get("Search_Item4"));
				productdetLib.Verifyrecentlyvieweditems();
				// verify recently viewed-------
				productdetLib.getrecentlyvieweditems(data.get("Search_Item5"));
				productdetLib.Verifyrecentlyvieweditems();
				// verify recently viewed--------
				productdetLib.getrecentlyvieweditems(data.get("Search_Item6"));
				productdetLib.Verifyrecentlyvieweditems();
				// verify recently viewed--------
				productdetLib.getrecentlyvieweditems(data.get("Search_Item7"));
				productdetLib.Verifyrecentlyvieweditems();
				// verify recently viewed--------
				productdetLib.getrecentlyvieweditems(data.get("Search_Item8"));
				productdetLib.Verifyrecentlyvieweditems();
				// Login
				cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
						data.get("Contact_Name"));
				cmtLib.loginAsAdminCMT();
				commonLib.searchProduct(data.get("Search_Item4"));
				String MfrNum = productdetLib.getMfrpartnumofFirstproduct();
				cartLib.selectFirstProductDisplay();
				productDispinfoLib.verifyTheManufacturerNumberInProductDetailsPage(MfrNum);
				commonLib.addToCartAndVerify();
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				// Click Custom Catalog
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("CustomCatalog"));
				productdetLib.ClickonDeleteButtonofcustomcatalog();
				productdetLib.Createcustomcatalog();
				productdetLib.ClickonCustomcatalog(data.get("manufacturers"));
				productdetLib.Clickonmanufacturers(data.get("manufacturers1"));
				productdetLib.Clickonmanufacturers(data.get("manufacturers2"));
				productdetLib.Clickonmanufacturers(data.get("manufacturers3"));
				// Custom Catalog changes and update
				productdetLib.SelectOptionFromExculdeManufacturers(data.get("optionOKI"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.loginAsAdminCMT();
				commonLib.searchProduct(data.get("Search_Item4"));
				cartLib.selectFirstProductDisplay();
				// verify recently viewed as expected it should not be visible
				// productdetLib.Verifyrecentlyviwedproductslabel();
				commonLib.searchProduct(data.get("Search_Item4"));
				cartLib.selectFirstProductDisplay();
				// verify recently viewed----
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("CustomCatalog"));
				// delete Custom Catalog
				productdetLib.ClickonDeleteButtonofcustomcatalog();
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
			
		} 
		catch (Exception e) 
				{
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
				}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("SearchIncludingProduct", "PID04", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("SearchIncludingProduct", "PID04", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}

