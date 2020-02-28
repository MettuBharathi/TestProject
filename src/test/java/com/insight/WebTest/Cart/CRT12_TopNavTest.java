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

public class CRT12_TopNavTest extends CartLib{
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib=new SearchLib();
	OrderLib orderLib=new OrderLib();
	   

	// #############################################################################################################
    // #    Name of the Test         : CRT12_TopNav
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void Tc_CRT12(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT12_TopNav", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT12_TopNav", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("TopNav");
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("menuName"),data.get("Enable_Purchasing_Popup"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"), data.get("Product_Group"), data.get("Product_Name"));
					searchLib.clickAddToOrderOnCompanyStandardsScreen();
					commonLib.clickCart();
					commonLib.verifyBundleIsAddedToCart();
					commonLib.searchProduct(data.get("PartNumber"));
					commonLib.addToCartAndVerify();
					orderLib.continueToCheckOutAddWarrantyAndVerifyWarrentyInCart(data.get("PartNumber"),data.get("Warrenty_Part_Number"));
					commonLib.spinnerImage();
					commonLib.searchProduct(data.get("Search_Product"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					String partNumber1=cartLib.getPartNumber();
					commonLib.clickCart();
					cartLib.verifyItemInCart(partNumber1);
					commonLib.searchProduct(data.get("Search_Product"));
					commonLib.addSecondDisplyedItemToCartAndVerify();
					String partNumber2=cartLib.getPartNumber();
					commonLib.clickCart();
					cartLib.verifyItemInCart(partNumber2);
					commonLib.emptyCartAndVerify();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
			} catch (Exception e) {
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
			}
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("TopNav", "CRT12", ReportStatus.strMethodName, intCounter, browser);
			fnCloseTest();
		}
	} catch (Exception e) {
		e.printStackTrace();
		ReportStatus.blnStatus = false;
		gErrorMessage = e.getMessage();
		gTestStatus = false;
		ReportStatus.fnUpdateResultStatus("TopNav", "CRT12", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}

	ReportControl.fnNextTestJoin(nextTestJoin);
}}