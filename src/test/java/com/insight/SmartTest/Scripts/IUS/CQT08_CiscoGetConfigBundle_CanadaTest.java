package com.insight.SmartTest.Scripts.IUS;

import com.insight.SmartTest.Pages.HomePage;
import com.insight.accelerators.ActionEngine;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import com.insight.SmartTest.Lib.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class CQT08_CiscoGetConfigBundle_CanadaTest extends ActionEngine {

	// #############################################################################################################
	// # Name of the Test : CQT08_CiscoGetConfigBundle_Canada
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// #
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void CQT_08(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT08_CiscoGetConfigBundle_Canada", TestData_Smart,
					"Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT08_CiscoGetConfigBundle_Canada",
							TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("CiscoGetConfigBundle_Canada");

					HomeLib home = new HomeLib();
					loginLib login = new loginLib();
					ObjectsLib object = new ObjectsLib();
					home.LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToAcct"));
					// home.clickAdvancedHeader();
					// home.clickAdvancedHeaderTab(data.get("contracts"));
					home.clickOnProductSearchButton();
					if (object.verifyAvailabilityOfProductSearchPopup()) {
						object.enterKeywordInProductSearchWindow(data.get("keyword"));
						object.clickOnSearchButtonInProductSearchWindow();
						home.clickOnMaterialID();
						if (home.verifyAddToOrderPopup()) {
							home.clickOnAddToOrderButton();
							home.clickCloseButton();
							object.clickOnCrossiConUnderVCColSingleRow();
							if (object.availabilityOfItemDetailsPopup()) {
								object.clickOnAcquireEstimateAndQuote();
								if (object.availablilityOfAcquireEstimateAndQuotewindowpopup()) {
									object.enterEstimateNumberValue(data.get("number"));
									object.clickOnOKButtonOKOnAcquireEstimateAndQuote();
									object.clickOnOKButtonOnWaraningPopup();
									home.clickonOkButton();
									home.clickonSaveasQuote();
									home.enterNewEmailIDandclickOKBtn(data.get("emailid"));
									home.clickSideBarSmart();
									home.clickClosthedocument(data.get("Doctype"));
									home.clickYesButtontocloseDocument();

								} else {
									// Acquire and quote window is not opened
								}
							} else {
								// Item Details popup failure
							}

						} else

						{
							// close
						}

					} else {

					}

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
			// ReportStatus.fnUpdateResultStatus("CiscoGetConfigBundle_Canada", "CQT_08",
			// ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CiscoGetConfigBundle_Canada", "CQT_08", ReportStatus.strMethodName,
					counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}