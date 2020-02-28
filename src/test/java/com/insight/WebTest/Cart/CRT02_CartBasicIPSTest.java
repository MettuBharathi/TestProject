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

public class CRT02_CartBasicIPSTest extends CartLib{
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	CMTLib cmtLib = new CMTLib();
	ShipBillPayLib shipbLib=new ShipBillPayLib();
	   
	// #############################################################################################################
    // #    Name of the Test         : CRT02_CartBasicIPS
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations using IPS USER.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################

	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
   public void Tc_CRT02(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT02_CartBasicIPS", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
				try
				{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT02_CartBasicIPS", TestDataInsight, "Web_Cart",intCounter);
					this.reporter.initTestCaseDescription("CartBasicIPS");
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"),data.get("User_Permission"));
					cmtLib.setPermissions(data.get("Menu_Name"),data.get("Enable_Purchasing_Popup"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cartLib.verifyCartIsEmpty();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					String partNumber1=cartLib.getPartNumber();
					commonLib.continueToShopping();
					commonLib.clickCart();
					String totalAmountMarketPriceoff=shipbLib.getTotalAmountInCart(data.get("Total"));
					cmtLib.navigateBackToCMT();
					cmtLib.setPermissions(data.get("Menu_Name"),data.get("User_Permission"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.searchProduct(partNumber1);
					cartLib.cartBasicsIPS_verifyPermissionAtUserLevel();
					commonLib.clickCart();
					String totalAmountMarketPriceOn=shipbLib.getTotalAmountInCart(data.get("Total"));
					if(!totalAmountMarketPriceoff.equals(totalAmountMarketPriceOn))
					{
						reporter.SuccessReport("Us commdity price and open market price", "are not equal","");
					}
					else {
						reporter.failureReport("Us commdity price and open market price", "are equal","");
					}
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				    cmtLib.searchForWebGroup(data.get("WebGrp2"));
				    cmtLib.manageUsers();
				    cmtLib.searchUsers(data.get("LnameEmailUname2"));
				    cmtLib.verifyUserandClick(data.get("ContactName2"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.searchProduct(data.get("Search_Item1"));
					cartLib.verifyDefaultContract();
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.clickCart();
					cartLib.verifyDefaultContractInCart();
					commonLib.searchProduct(data.get("SearchItem4"));
					cartLib.clickMorePricesAvilableInProductInfo();
					cartLib.clickOnOpenMarketPrice();
					cartLib.clickOnAddToCartInAllContractPrices();
					commonLib.clickCart();
					commonLib.emptyCartAndVerify();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("CartBasicIPS", "CRT02", ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("CartBasicIPS", "CRT02", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}

