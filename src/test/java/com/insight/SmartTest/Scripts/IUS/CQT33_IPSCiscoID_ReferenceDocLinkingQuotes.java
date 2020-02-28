package com.insight.SmartTest.Scripts.IUS;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT33_IPSCiscoID_ReferenceDocLinkingQuotes extends HomeLib {
	loginLib loginlib=new loginLib();
	// #############################################################################################################
			// # Name of the Test : CQT33_IPSCiscoID_ReferenceDocLinkingQuotes
			// # Migration Author : Cigniti Technologies
			// #
			// # Date of Migration : NOV 2019
			// # DESCRIPTION : IPSCiscoIDReferenceDocLinkingQuotes
			// functionality in the products display page.
			// # Parameters : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################

			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void TC_CQT33(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
				int counter = 0;
		        try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT33_IPSCiscoID_ReferenceDocLinkingQuotes",TestData_Smart,"Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT33_IPSCiscoID_ReferenceDocLinkingQuotes", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSContractpricing_InsightListminusContract");
					LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToAcct"));
						clickAcquireEstimateBtn();
						enterEstimateNumber(data.get("EstimateID"));
						clickAdvancedHeader();//Advance Header
						clickAdvancedHeaderTab(data.get("Tab"));//General
						enterDocReferenceNumberandGetText(data.get("RefNUmber"));
						//Collapse Advance Header tab
						clickAdvancedHeader();//Advance Header
						clickonConXSystem(data.get("itemNum"));//00010
						selectCOntractID(data.get("contactid"),data.get("contactTabName"));
						copyAllContractstoAllLines();
						clickDoneButton();
						clickUpdateCosting();
						driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
						clickSideBarSmart();
						clickonSaveasQuote();
						enterCancelButtonInPoupHdr();
						String QuoteNum1= GetQuoteNumber();
						selectDDoptionFromHistoryDD(data.get("HistoryOption"));
						String QuoteNumberDisplayed= getQuoteNUmFromDocumentFlowPopup(QuoteNum1);
						String ReferenceNumberDisplayed = getReferenceNUmFromDocumentFlowPopup(data.get("RefNUmber"));
						if(data.get("RefNUmber")==ReferenceNumberDisplayed){
							 reporter.SuccessReport("Doc Ref number in History Document Flow'", "Exists as Expected","");
						} else {
							reporter.failureReport("Doc Ref number in History Document Flow'", "Does not Match with Expected", "Actual Doc Ref #:"+data.get("RefNUmber")+"-- Expected Doc Ref#: "+ReferenceNumberDisplayed,driver);
						
						}
						if(QuoteNum1==QuoteNumberDisplayed){
							 reporter.SuccessReport("Quote number in History Document Flow'", "Exists as Expected","");
						} else {
							reporter.failureReport("Quote number in History Document Flow'", "Does not Exist", "Actual Quote #:"+QuoteNum1+"Expected Quote#:"+QuoteNumberDisplayed,driver);
						
						}
						clickSideBarSmart();
						clickClosthedocument(QuoteNum1);
						clickYesButtontocloseDocument();	
						System.out.println("Test completed");
					}  catch (Exception e) {
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

				//ReportStatus.fnUpdateResultStatus("CQT33_IPSCiscoID_ReferenceDocLinkingQuotes", "TC_CQT33", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
		    finally {
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("CQT33_IPSCiscoID_ReferenceDocLinkingQuotes", "TC_CQT33", ReportStatus.strMethodName, counter, browser);

				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}


}