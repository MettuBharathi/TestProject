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

public class PID08_TotalPriceEstimateTest extends ActionEngine{
	CommonLib commonLib = new CommonLib(); 
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	ProductDetailLib productdetLib = new ProductDetailLib();
	// #############################################################################################################
		// # Name of the Test : PID08_TotalPriceEstimate
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_PID08(int StartRow, String EndRow, boolean nextTestJoin)	throws Throwable {
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID08_TotalPriceEstimate",TestDataInsight, "Product_Detail");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("PID08_TotalPriceEstimate",TestDataInsight, "Product_Detail", intCounter);
				TestEngineWeb.reporter.initTestCaseDescription("TotalPriceEstimate");
				reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");
				
				cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
						data.get("ContactName"));
				cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				cmtLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
				cmtLib.selectDefaultShippingOptionInCheckoutSettings(data.get("Default_Shipping_Option"));
				cmtLib.clickupdateatDefaultShippingOption();
				cmtLib.loginAsAdminCMT();
				productdetLib.verifytheLoginUser(data.get("LnameEmailUname"));
				commonLib.searchProduct(data.get("Search_Item"));
				cartLib.selectFirstProductDisplay();
				// verif estimated Price
				productdetLib.Estimatetotalprice(data.get("ZIPcode"));
				productdetLib.verifyEstimatedtax();
				productdetLib.verifyEstimatedshipping();
				productdetLib.verifyEstimatedPrice();
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				cmtLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
				// disable permissions
				cmtLib.selectDefaultShippingOptionInCheckoutSettings(data.get("Default_Shipping_Option1"));
				cmtLib.clickupdateatDefaultShippingOption();
			
		} 
		catch (Exception e) 
				{
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
				}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("SearchIncludingProduct", "PID08", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("SearchIncludingProduct", "PID08", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}

