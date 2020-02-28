package com.insight.SmartTest.Scripts.IUS;

import java.util.Calendar;
import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;



public class CQT19_IPSCiscoIDRefreshIconTest extends HomeLib {
	
	loginLib loginlib=new loginLib();
	// #############################################################################################################
	// #       Name of the Test         :  CQT19_IPSCiscoIDRefreshIcon
	// #       Migration Author         :  Cigniti Technologies
	// #
	// # Date of Migration : November 2019
	// # DESCRIPTION : This method is to verify IPSCiscoIDRefreshIcon
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT19(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT19_IPSCiscoIDRefreshIcon_Action1_Script",
					TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT19_IPSCiscoIDRefreshIcon_Action1_Script", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSCiscoIDRefreshIcon");
					LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"),
							data.get("SoldToAcct"));

					clickLineItemHeaderTab("Acquire Estimate/Quote");
					enterEstimateId(data.get("Material"));
					SearchButton();
					loadingSymbol();
					clickonSaveasQuote();

					SelectAdherencetoflooroption("Client Satisfaction");
					Adherencetofloorreason(data.get("Adherencetofloor"));
					ClickOncancelbutton();
					String quotenumber = GetQuoteNumber();
					ClickOnDisplayMode();
					doubleclickOnLineItem("000010");
					clickOnVCTab("Contracts");
					clickOnrefreshContracts();
					selectContractIdwithDesc("STATE OF TEXAS");
					ClickOnCopyContracttoallLines();
					SearchButton();
					clickDoneButton();
					clickOnVCTab("VC");
					String duration = getDurationFieldValue();
					if (duration.equals("12"))
						System.out.println("Duration is 12");
					VerifyDuration("24");
					VerifyDuration("36");
					clickDoneButton();
					clickAdvancedHeader();
					clickAdvancedHeaderTab("Cisco");
					enterDurationUnderAdvanceHeader("24");
					clickOnCopyDuartion();
					clickAdvancedHeader();
					clickUpdateCosting();

					ClickOncancelbutton();
					clickAdvancedHeader();
					clickAdvancedHeaderTab("Cisco");
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.MONTH, 36);
					Enter36MonthstoEndDateField(36);
					clickOnCopydates();
					clickOnLItem00020CON("00020", "con");
					clickOnVCTab("VC");
					String duration1 = getDurationFieldValue();
					if (duration1.equals("24"))
						System.out.println("Duration is 24");
					VerifyDuration(data.get("Duration1"));
					VerifyDuration(data.get("Duration1"));
					checkdurationfieldenabledordisabled();
					clickOnVCTab("Coverage/Billing");
					String Cstartdate = coverageStartDate();
					String CendDate = coverageEndDate();
					if (Cstartdate.equals(CendDate))
						System.out.println("Start date and End dates are matching");
					clickDoneButton();
					clickUpdateCosting();

					SearchButton();
					getmfgPricevalue("00020", "mfrList", "36.03");
					getmfgPricevalue("00040", "mfrList", "36.03");
					getmfgPricevalue("00060", "mfrList", "36.03");
					doubleclickOnLineItem("000010");
					clickOnVCTab("Coverage/Billing");
					ClearEnddateCoveragefield();
					ClickonArrowNextToLineitem();
					ClickonleftArrowpriorToLineitem();
					clickOnVCTab("VC");
					checkdurationfieldenabledordisabled();
					clickDoneButton();
					clickUpdateCosting();

					SearchButton();
					clickonSaveasQuote();

					ClickOncancelbutton();
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
			// ReportStatus.fnUpdateResultStatus("CQT19_IPSCiscoIDRefreshIcon_Action1_Script",
			// "TC_CQT19", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT19_IPSCiscoIDRefreshIcon_Action1_Script", "TC_CQT19",
					ReportStatus.strMethodName, counter, browser);
			// fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
