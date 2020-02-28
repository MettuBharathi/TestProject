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

public class CRT04_CartPrintFriendlyTest extends CartLib{
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	   
	// #############################################################################################################
    // #    Name of the Test         : CRT04_CartPrintFriendly
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform search operations in the Product Research Request page.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################
    @Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test(enabled = true)
	public void Tc_CRT04(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT04_CartPrintFriendly", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT04_CartPrintFriendly", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("CartPrintFriendly");
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					commonLib.searchProduct(data.get("SearchItem1"));
					commonLib.addToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.searchProduct(data.get("SearchItem2"));
					commonLib.addToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.searchProduct(data.get("SearchItem3"));
					commonLib.addToCartAndVerify();
					commonLib.closePopUp();
					cartLib.clickAndVerifyViewPrintablePopUp(data.get("OrderUtilities"));
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("CartPrintFriendly", "CRT04", ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("CartPrintFriendly", "CRT04", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}