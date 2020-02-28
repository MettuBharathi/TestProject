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

public class CQT37_IPSContractPricing_CiscoFixedPriceContract extends HomeLib {
	loginLib loginlib=new loginLib();
	// #############################################################################################################
			// # Name of the Test : CQT37_IPSContractPricing_CiscoFixedPriceContract
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
			public void TC_CQT37(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
				int counter = 0;

		        try {
			    
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT37_IPSContractPricing_CiscoFixedPriceContract",TestData_Smart,"Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT37_IPSContractPricing_CiscoFixedPriceContract", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSContractpricing_InsightListminusContract");
					LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToAcct"));
						clickAcquireEstimateBtn();
						enterEstimateNumber(data.get("EstimateID"));
						/*clickAdvancedHeaderTab(data.get("Tab"));//General
						enterDocReferenceNumberandGetText(data.get("RefNUmber"));*/
						//Collapse Advance Header tab
						clickonConXSystem(data.get("LineItem1"));//00020
						selectCOntractSubTabName(data.get("contractTab"));//VC
						getDurationTime(data.get("LineItem1"),data.get("Duration1"));
						clickonRightArrowforLineItem();
						clickonRightArrowforLineItem();
						getDurationTime(data.get("LineItem2"),data.get("Duration2"));
						clickonRightArrowforLineItem();
						clickonRightArrowforLineItem();
						getDurationTime(data.get("LineItem3"),data.get("Duration3"));
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
						clickCloseButton();
						clickOnCOntractIDinLineItemsList();
						selectCOntractSubTabName(data.get("contractTab1"));//Pricing
						//Get data from the pricing tab
						String ZPFX= selectproductinPricingTable(data.get("text1"));
						String ZPOO= selectproductinPricingTable(data.get("text2"));
						String YPOO= selectproductinPricingTable(data.get("text3"));
						//Need to compare pricing
						//VerifyZPFXShouldbeEqualToZP00(ZPFX,ZPOO);
						//VerifyZPFXShouldbeEqualToYP00(ZPFX,YPOO);
						clickDoneButton();
						clickSideBarSmart();
						clickClosthedocument(QuoteNum1);
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
					//ReportStatus.fnUpdateResultStatus("IPSContractPricing_CiscoFixedPriceContract", "CQT_37", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
		        finally {
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("IPSContractPricing_CiscoFixedPriceContract", "CQT_37", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}



	
		}
