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

public class CRT07_SaveCartTest extends CartLib{
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib=new SearchLib();
	

	// #############################################################################################################
    // #    Name of the Test         : CRT07_SaveCart
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void Tc_CRT07(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT07_SaveCart", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT07_SaveCart", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SaveCartTest" );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("menuName"),data.get("userPermission"));
					cmtLib.setPermissions(data.get("menuName"),data.get("Enable_Purchasing_Popup"));
					String mainWindow = parentWindow();
					cmtLib.clickOnloginAs();
					switchToWindow(mainWindow);	
					cartLib.verifyCartIsEmpty();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addSecondDisplyedItemToCartAndVerify();
					commonLib.searchProduct(data.get("Search_Item2"));
					cartLib.selectFirstProductDisplay();
					commonLib.addToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.searchProduct(data.get("Search_Item3"));
					cartLib.selectFirstProductDisplay();
					commonLib.addToCartAndVerify();
					commonLib.clickCart();
					String cartName=getRandomString(5)+'@';
					cartLib.clickOnSaveCartContentAndSaveCart(cartName);
					commonLib.clickCart();
					commonLib.verifyCartIsEMpty();
					cartLib.openSavedCartFromTools(cartName);
					commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"), data.get("Product_Group"), data.get("Product_Name"));
					searchLib.selectProductGroupAndVerify(data.get("Product_Group"), data.get("Product_Name"));
					commonLib.clickCart();
					commonLib.verifyBundleIsAddedToCart();
					String cartName1=getRandomString(5)+'@';
					cartLib.clickOnSaveCartContentAndSaveCart(cartName1);
					commonLib.clickCart();
					cartLib.openSavedCartFromTools(cartName1);
					cartLib.deleteCartFromAccountTools(cartName);
					cartLib.deleteCartFromAccountTools(cartName1);
					
			} catch (Exception e) {
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
			}
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SaveCartTest", "CRT07", ReportStatus.strMethodName, intCounter, browser);
			fnCloseTest();
		}
	} catch (Exception e) {
		e.printStackTrace();
		ReportStatus.blnStatus = false;
		gErrorMessage = e.getMessage();
		gTestStatus = false;
		ReportStatus.fnUpdateResultStatus("SaveCartTest", "CRT07", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}

	ReportControl.fnNextTestJoin(nextTestJoin);
}
}
