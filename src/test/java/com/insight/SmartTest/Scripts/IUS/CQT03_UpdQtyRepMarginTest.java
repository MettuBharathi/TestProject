package com.insight.SmartTest.Scripts.IUS;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT03_UpdQtyRepMarginTest extends HomeLib {

	loginLib loginlib = new loginLib();

	// #############################################################################################################
	// # Name of the Test : CQT03_UpdQtyRepMargin_Action1_Script
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : November 2019
	// # DESCRIPTION : This method is to verify UpdQtyRepMargin
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT03(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {

			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT03_UpdQtyRepMargin_Action1_Script",
					TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT03_UpdQtyRepMargin_Action1_Script",
							TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("UpdQtyRepMargin");
					// Test Steps execution
					LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"),
							data.get("SoldToAcct"));
					EnterDataInShipToAttn(data.get("ShipToAttn"));
					clickAdvancedHeader();
					clickAdvancedHeaderTab("Programs");
					VerifyRowsUnderContractsTab();
					clickAdvancedHeaderTab("Contracts");
					// AddLineItems(data.get("Material"));
					clickLineItemHeaderTab("Product Search");
					driver.switchTo().defaultContent();
					selectoptionfromManufacturer(data.get("Manufacturer"));
					checkStokInOnlyCheckbox();
					selectpricing();
					EnterkeywordSearch(data.get("search"));
					SearchButton();
					selectproduct();

					driver.switchTo().defaultContent();
					clickOnAddToOrderButton();
					closebuttonInProductSearch();
					driver.switchTo().parentFrame();
					float displayedtotalweight = TotalWeight();
					if (displayedtotalweight > 0.008) {
						reporter.SuccessReport("Product Weight::"," Product weight is more than 0.008 and the weight is: ","");
					} else {
						System.out.println("Product weight is less than 0.008");
					}
					ClickOnSaveAsQuoteButton();
					driver.switchTo().defaultContent();
					EnterEmail(data.get("emailid"));
					ClickOnSendbutton();
					closebuttonInProductSearch();
					ClickOnDisplayMode();
					ClickOnSaveAsQuoteButton();
					System.out.println("Test case completed");

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
			// ReportStatus.fnUpdateResultStatus("CQT03_UpdQtyRepMargin_Action1_Script","TC_CQT03",
			// ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT03_UpdQtyRepMargin_Action1_Script", "TC_CQT03",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
