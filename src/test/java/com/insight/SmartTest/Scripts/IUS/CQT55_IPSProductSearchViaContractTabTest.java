package com.insight.SmartTest.Scripts.IUS;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.utilities.TestUtil;

public class CQT55_IPSProductSearchViaContractTabTest extends HomeLib{ 
	
	loginLib loginlib=new loginLib();

// #############################################################################################################
	// # Name of the Test : CQT55_IPSProductSearchViaContractTab
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : Purpose of this test method is to verify the compare
	// functionality in the products display page.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT55(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT55_IPSProductSearchViaContractTab", TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT55_IPSProductSearchViaContractTab", TestData_Smart,
							"Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("ProductSearchViaContractTab");
					navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
					Thread.sleep(2000);
					clickCreateQuoteButton();
					Thread.sleep(2000);
					enterSoldTo(data.get("SoldToAcct"));
					selectOrg(data.get("SalesOrg"));// 2500
					String Quotenumber = GetQuoteNumber();
					clickAdvancedHeader();
					clickAdvancedHeaderTab(data.get("Tab1"));// Contract
					Productsearch(data.get("ContarctId"));// 1510000518
					SearchButton();
					clickOnMaterialID();
					clickOnAddToOrderButton();
					clickCloseButtonProductSearch();
					clickonConXSystem(data.get("ItemNum"));// 000010
					VerifycolourContractIdHighlitedwithYellow(data.get("ContarctId"), data.get("Colour"));// background-color:Yellow
					clickDoneButton();
					clickUpdateCosting();
					Thread.sleep(4000);
					clickSideBarSmart();
					ClickOnSaveAsQuoteButton();
					Thread.sleep(4000);
					CloseButtonofOutputform();
					Thread.sleep(2000);
					clickSideBarSmart();
					clickClosthedocument(Quotenumber);
					clickYesButtontocloseDocument();
					System.out.println("Test completed");
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.toString();
			gTestStatus = false;
			//ReportStatus.fnUpdateResultStatus("IPSProductSearchViaContractTab", "CQT_55", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("IPSProductSearchViaContractTab", "CQT_55", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}


}