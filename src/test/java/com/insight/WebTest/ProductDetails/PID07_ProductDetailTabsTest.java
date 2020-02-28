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

public class PID07_ProductDetailTabsTest extends ActionEngine{
	CommonLib commonLib = new CommonLib(); 
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	ProductDetailLib productDetailLib = new ProductDetailLib();
	// #############################################################################################################
		// # Name of the Test : PID07_ProductDetailTabs
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_PID07(int StartRow, String EndRow, boolean nextTestJoin)	throws Throwable {
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID07_ProductDetailTabsTest",TestDataInsight, "Product_Detail");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("PID07_ProductDetailTabs",TestDataInsight, "Product_Detail", intCounter);
				TestEngineWeb.reporter.initTestCaseDescription("ProductDetailTabs");
				reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");
				
				CommonLib commonLib = new CommonLib();
				ProductDetailLib productdetLib = new ProductDetailLib();
				commonLib.searchProduct(data.get("Search_Item1"));
				productdetLib.verifyBreadcrumb();
				productdetLib.OverviewTab();
				commonLib.searchProduct(data.get("Search_Item2"));
				// Verify Specifications
				productdetLib.Getproductdetails();
				// Verify Accessories
				productdetLib.VerifyAddAccessories();
				// Update Qauntiy
				productdetLib.Verifyupdatequantity();
			
		} 
		catch (Exception e) 
				{
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
				}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("SearchIncludingProduct", "PID07", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("SearchIncludingProduct", "PID07", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}

