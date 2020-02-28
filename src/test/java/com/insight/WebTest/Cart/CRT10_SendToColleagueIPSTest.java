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

public class CRT10_SendToColleagueIPSTest extends CartLib{
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	CMTLib cmtLib = new CMTLib();
	   
	// #############################################################################################################
    // #    Name of the Test         : CRT10_SendToColleagueIPS
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to verify send to collegue order utility in shopping cart  page.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################
    @Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test(enabled = true)
	public void Tc_CRT10(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT10_SendToColleagueIPS", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT10_SendToColleagueIPS", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SendToColleagueIPS");
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					
					
					String searchItem1 = data.get("SearchItem1");
					String searchItem2 = data.get("SearchItem2");
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("menuName"),data.get("Enable_Purchasing_Popup"));
					String mainWindow = parentWindow();
					cmtLib.clickOnloginAs();
					switchToWindow(mainWindow);	
					commonLib.searchProduct(searchItem1);
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.searchProduct(searchItem2);
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.closePopUp();
					cartLib.clickAndVerifySendToAColleagueErrorMSG_IPS(data.get("OrderUtilities"));
					cartLib.verifySendToAColleague(data.get("OrderUtilities"),data.get("YourName"),data.get("YourEmail"),data.get("RecipientEmail"),data.get("YourComments"));
			} catch (Exception e) {
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
			}
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SendToColleagueIPS", "CRT10", ReportStatus.strMethodName, intCounter, browser);
			fnCloseTest();
		}
	} catch (Exception e) {
		e.printStackTrace();
		ReportStatus.blnStatus = false;
		gErrorMessage = e.getMessage();
		gTestStatus = false;
		ReportStatus.fnUpdateResultStatus("SendToColleagueIPS", "CRT10", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}

	ReportControl.fnNextTestJoin(nextTestJoin);
}
}