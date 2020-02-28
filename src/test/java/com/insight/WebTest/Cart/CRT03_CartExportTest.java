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

public class CRT03_CartExportTest extends CartLib{
	CommonLib commonLib = new CommonLib();	
	CartLib cartLib = new CartLib();
	CMTLib cmtLib = new CMTLib();
	OrderLib orderLib=new OrderLib();
	SearchLib searchLib=new SearchLib();
	   
	 // #############################################################################################################
    // #    Name of the Test         : CRT03_CartExport
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform search operations in the Product Research Request page.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
    @Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void Tc_CRT03(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT03_CartExport", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT03_CartExport", TestDataInsight, "Web_Cart",intCounter);
					this.reporter.initTestCaseDescription("CartExport");
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					String mainWindow = parentWindow();
					cmtLib.clickOnloginAs();
					switchToWindow(mainWindow);
					commonLib.searchProduct(data.get("SearchItem1"));
					commonLib.addToCartAndVerify();
					orderLib.continueToCheckOutAddWarrantyAndVerifyWarrentyInCart(data.get("SearchItem1"),data.get("Warrenty_Part_Number"));
					commonLib.searchProduct(data.get("SearchItem2"));
					commonLib.addToCartAndVerify();
					orderLib.continueToCheckOutAddWarrantyAndVerifyWarrentyInCart(data.get("SearchItem2"),data.get("Warrenty_Part_Number1"));
					commonLib.searchProduct(data.get("SearchItem3"));
					commonLib.addToCartAndVerify();
					orderLib.continueToCheckOutAddWarrantyAndVerifyWarrentyInCart(data.get("SearchItem3"),data.get("Warrenty_Part_Number2"));
					cartLib.clickAndVerifyExportCart(data.get("OrderUtilities"));				
					cartLib.verifyExportFile(data.get("Sheet_Name"),data.get("Row_number"),data.get("Column_Headers"));
					commonLib.spinnerImage();
					commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"), data.get("Product_Group"), data.get("Product_Name"));
					searchLib.clickAddToOrderOnCompanyStandardsScreen();
					commonLib.clickCart();
					commonLib.verifyBundleIsAddedToCart();
					cartLib.clickAndVerifyExportCart(data.get("OrderUtilities"));
					cartLib.verifyExportFile(data.get("Sheet_Name"),data.get("Row_number1"),data.get("Column_Headers1"));
					Thread.sleep(5000);
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("CartExport", "CRT03", ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("CartExport", "CRT03", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}
	
