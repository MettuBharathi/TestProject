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

public class CQT09_ChangeShipToAttnTest extends HomeLib {

	loginLib loginlib = new loginLib();
	// #############################################################################################################
	// # Name of the Test : CQT09_ChangeShipToAttn
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : November 2019
	// # DESCRIPTION : This method is to verify ChangeShipToAttn
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT09(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT09_ChangeShipToAttn_Action1_Script",
					TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT09_ChangeShipToAttn_Action1_Script",
							TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("ChangeShipToAttn");

					LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToAcct"));
					AddLineItems("material", data.get("Material"), 0);
					// AddLineItems(data.get("Material"));
					EnterDataInShipToAttn(data.get("ShipToAttn"));
					ClickOnSaveAsQuoteButton();
					driver.switchTo().defaultContent();
					CloseButtonofOutputform();
					driver.switchTo().parentFrame();
					String QuoteNumber = GetQuoteNumber();
					if (QuoteNumber.contains(data.get("SoldToAcct"))) {
						reporter.SuccessReport("QuoteNUmber::","Quote Numbere reflected correclty","");
					} else {
						reporter.failureReport("QuoteNUmber::","Quote Numbere not reflected correclty","");
					}
					String ActualdataDisplayed = ValidatedataInShipToAttn(data.get("ShipToAttn"));
					if (ActualdataDisplayed.equals(data.get("ShipToAttn"))) {
						System.out.println("Data entered in ShitTo Attn is : " + ActualdataDisplayed);
						reporter.SuccessReport("QuoteNUmber::","Quote Numbere reflected correclty","");
					} else {
						reporter.failureReport("QuoteNUmber::","Quote Numbere not reflected correclty","");
					}
					ClickOnDisplayMode();
					EnterDataInShipToAttn(data.get("UpdatedShipToAttn"));
					ClickOnSaveAsQuoteButton();
					String UdatedSHipToAttnData = ValidatedataInShipToAttn(data.get("UpdatedShipToAttn"));

					if (UdatedSHipToAttnData.equals(data.get("UpdatedShipToAttn"))) {
						System.out.println("Data Updated in ShitTo Attn is : " + UdatedSHipToAttnData);
						reporter.SuccessReport("ShitTo Attn::","ShitTo Attn reflected correclty","");
					} else {
						reporter.failureReport("ShitTo Attn::","ShitTo Attn not reflected correclty","");
					}
					System.out.println("Testcase completed");

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
			// ReportStatus.fnUpdateResultStatus("CQT09_ChangeShipToAttn_Action1_Script",
			// "TC_CQT09", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT09_ChangeShipToAttn_Action1_Script", "TC_CQT09",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
