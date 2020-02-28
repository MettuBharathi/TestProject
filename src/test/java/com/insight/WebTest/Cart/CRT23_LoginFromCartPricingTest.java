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

public class CRT23_LoginFromCartPricingTest extends CartLib{
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();

	// #############################################################################################################
    // #    Name of the Test         : CRT23_LoginFromCartPricing
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void Tc_CRT23(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT23_LoginFromCartPricing", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT23_LoginFromCartPricing", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("LoginFromCartPricing");
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					cmtLib.loginAsEndUserInMainPage(data.get("Header"),data.get("User_Name"),data.get("Password"));
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addToCartAndVerify();
					String priceInLogin=cartLib.getTotalPrice();
					commonLib.clickCart();
					
					cartLib.verifyItemInCart(data.get("Search_Item"));
					String summaryAmountInLogin=cartLib.getSummaryAmountInCart();
					commonLib.emptyCartAndVerify();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addToCartAndVerify();
					String priceWithoutLogin=cartLib.getTotalPrice();
					commonLib.clickCart();
					
					cartLib.verifyItemInCart(data.get("Search_Item"));
					String summaryAmountWithoutLogin=cartLib.getSummaryAmountInCart();
					System.out.println("summaryAmountInLogin"+summaryAmountInLogin);
					System.out.println("summaryAmountWithoutLogin"+summaryAmountWithoutLogin);
					cartLib.VerifyLoginPriceAndNonLoginPrice(priceInLogin,priceWithoutLogin);
					cartLib.verifySummaryPriceInLoginAndNonLogin(summaryAmountInLogin,summaryAmountWithoutLogin);
			}
			catch (Exception e) {
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
			}
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("LoginFromCartPricing", "CRT23", ReportStatus.strMethodName, intCounter, browser);
			fnCloseTest();
		}
	} catch (Exception e) {
		e.printStackTrace();
		ReportStatus.blnStatus = false;
		gErrorMessage = e.getMessage();
		gTestStatus = false;
		ReportStatus.fnUpdateResultStatus("LoginFromCartPricing", "CRT23", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}

	ReportControl.fnNextTestJoin(nextTestJoin);
}}