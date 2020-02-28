package com.insight.SmartTest.Scripts.IUS;

import java.util.Date;
import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;


public class CQT32_IPSERateTaxTest extends HomeLib {
	
	loginLib loginlib=new loginLib();
	// #############################################################################################################
				// #       Name of the Test         :  CQT32_IPSERateTaxTest
				// #       Migration Author         :  Cigniti Technologies
				// #
				// #       Date of Migration        : November 2019
				// #       DESCRIPTION              : This method is to verify IPSERateTax
				// #       Parameters               : StartRow ,EndRow , nextTestJoin
				// #
				// ###############################################################################################################


	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT32(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT32_IPSERateTax_Action1_Script", TestData_Smart,
					"Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT32_IPSERateTax_Action1_Script", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSERateNonTax");
					LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToAcct"));
					SwipeUpapplication();
					selectPaymentTermsdropdown(data.get("PaymentTerms"));
					AddLineItems("material", data.get("Material"), 0);
					AddLineItems("material", data.get("Material"), 1);
					ClickOnXsymbolunderCon();
					SelectContractId(2);
					EnterUSCOMMember("U.S. COMM MEMBER ID #:", data.get("USCOMMmember"));
					EnterUSCOMMember("REPORTING FIELD 4:", data.get("REPORTINGFIELD4"));
					EnterUSCOMMember("REPORTING FIELD 5:", data.get("REPORTINGFIELD5"));
					clickDoneButton();
					clickAdvancedHeader();
					SelectSpecialOrderType(data.get("SpecialOrderType"));
					enterQuoteNumber(data.get("QuoteNumber"));
					enterErateFRN(data.get("ErateFRN"));
					selectERateFundingYear(data.get("ERateFundingYear"));
					SwipeUpapplication();
					clickAdvancedHeaderTab("Pricing");
					Swipedownapplication();
					enterErateSLD(data.get("ErateSLD"));
					SwipeUpapplication();
					clickAdvancedHeaderTab("Partners");
					Swipedownapplication();
					enterErateLocation(data.get("ErateLocation"));
					SwipeUpapplication();
					clickAdvancedHeader();
					doubleclickOnLineItem("000020");
					clickOnVCTab("Pricing");
					entererateEligibilityPercent(data.get("erateEligibilityPercent"));
					ClickonArrowNextToLineitem();
					ClickonArrowNextToLineitem();
					String eerateeligibility = geterateEligibilityPercent();
					if (eerateeligibility.equals("0.00")) {
						System.out.println("erateEligibilityPercent: ");
					}
					clickDoneButton();
					clickonSaveasQuote();
					String quoteNumber = GetQuoteNumber();
					System.out.println(quoteNumber);
					String ErateFRN = getErateFRNfromAttnfield();
					String[] Eratevalues = ErateFRN.split("/");
					clickAdvancedHeader();
					clickAdvancedHeaderTab("General");
					//Swipedownapplication();
					String ErateFinancialYear = getErateFinancialYear();
					if (ErateFinancialYear.equals(Eratevalues[2]))
						System.out.println("Financial year displaying as expected");
					clickAdvancedHeaderTab("Partners");
					String ErateLocation = getErateLocation();
					if (ErateLocation.equals(Eratevalues[3]))
						System.out.println("Financial year displaying as expected");
					ClickOnDisplayMode();
					// loadingSymbol();
					Date today = new Date();
					String date = today.toString().replace(":", "").replace(" ", "");
					SwipeUpapplication();
					SwipeUpapplication();
					SwipeUpapplication();

					enterPONumber("CPSS" + date);
					ClickOnConverToOrder();
					clickonSaveasOrder();
					clickOnSaveorderwithoutAttchment();
					getSaveQuoteNumber();
					String MOvalue = getMaterialsOrderrevenue();
					String TaxOvalue = getTaxOrderrevenue();
					String TotalOvalue = getTotalOrderrevenue();
					if (TaxOvalue.equals("0.00"))
						System.out.println("Tax order revenue: " + TaxOvalue);
					else
						System.out.println("Tax order revenue  is not 0.00 ");
					if (MOvalue.equals(TotalOvalue))
						System.out.println("Tax not included in Total");
					else
						System.out.println("Tax included in total");
					clickAdvancedHeaderTab("General");
					Swipedownapplication();
					String dd = getSelectedSpecialOrderType();
					if (dd.contains(ErateFinancialYear))
						System.out.println("Special oprdert type is as expected");
					else
						System.out.println("Special oprdert type is not as expected");
					String QuoteName = GetQuoteName();
					if (QuoteName.equals(Eratevalues[1]))
						System.out.println("QuoteName is as expected");
					else
						System.out.println("QuoteName is not as expected");
					String EFRN = GetErateFRN();
					if (EFRN.equals(Eratevalues[1]))
						System.out.println("ErateFRN is as expected");
					else
						System.out.println("ErateFRN is not as expected");
					String ErateFY = getSelectedErateFY();
					if (ErateFY.contains(ErateFinancialYear))
						System.out.println("ErateFinancialYear is as expected");
					else
						System.out.println("ErateFinancialYear is not as expected");
					SwipeUpapplication();
					clickAdvancedHeaderTab("Pricing");
					Swipedownapplication();
					String Pricingvalue = getErateSLD();
					if (Pricingvalue.equals("90.00"))
						System.out.println("ErateSLD is as expected");
					else
						System.out.println("ErateSLD is not as expected");
					clickAdvancedHeaderTab("Partners");
					String location = getErateLocation();
					if (location.equals("Test"))
						System.out.println("ErateLocation is as expected");
					else
						System.out.println("ErateLocation is not as expected");
					SwipeUpapplication();
					clickAdvancedHeader();
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
			// ReportStatus.fnUpdateResultStatus("CQT32_IPSERateTax_Action1_Script",
			// "TC_CQT32", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT32_IPSERateTax_Action1_Script", "TC_CQT32",
					ReportStatus.strMethodName, counter, browser);
			// fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
