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

public class CQT54_IPSConfigurationPartnerCTOBOM extends HomeLib {
	
	loginLib loginlib=new loginLib();
	
	// #############################################################################################################
				// # Name of the Test : CQT54_IPSConfigurationPartnerCTOBOM
				// # Migration Author : Cigniti Technologies
				// #
				// # Date of Migration : Nov 2019
				// # DESCRIPTION : 
				// functionality in the products display page.
				// # Parameters : StartRow ,EndRow , nextTestJoin
				// #
				// ###############################################################################################################


				@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
				@Test
				public void TC_CQT54(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
					int counter = 0;
			        try {
				    
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT54_IPSConfigurationPartnerCTOBOM",TestData_Smart,"Create_Quote");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT54_IPSConfigurationPartnerCTOBOM", TestData_Smart, "Create_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("IPSContractpricing_InsightListminusContract");
						LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToAcct"));
							AddMaterialOnLineItem(data.get("MaterialID"));
							clickOnRedCrossSymbolinLineItemsList();
							//Need to add materials in VC tab
							
							
							clickOnVCTAbUpdateButton();
							clickDoneButton();
							driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							clickOnCOntractIDinLineItemsList();
							selectCOntractID(data.get("contactid"),data.get("contactTabName"));
							copyAllContractstoAllLines();
							clickDoneButton();
							clickUpdateCosting();
							driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							
							String Price1= getPriceValueFromLineItemTable(data.get("PriceValue1"));//2
							Float P1= Float.valueOf(Price1);
							String Price2= getPriceValueFromLineItemTable(data.get("PriceValue2"));//3
							Float P2= Float.valueOf(Price2);
							String Price3= getPriceValueFromLineItemTable(data.get("PriceValue3"));//4
							Float P3= Float.valueOf(Price3);
							String Price4= getPriceValueFromLineItemTable(data.get("PriceValue4"));	//5
							Float P4= Float.valueOf(Price4);
							Float totalPriceofFour = P1+P2+P3+P4;
							String TotalPrice = getFirstElementPriceValueFromLineItem();
							Float PricefoLine10= Float.valueOf(TotalPrice);
							//Need to validate price values
							if (PricefoLine10==totalPriceofFour) {
								reporter.SuccessReport("Confirm the sell price on line 10 equals the combined total of lines 20-50","The sell price on line 10 equals the combined total of lines 20-50",
										"Sell Price on Line 10: "+PricefoLine10+"/ Total Price of lines 10-50:"+totalPriceofFour);
							} else {
								reporter.failureReport("Confirm the sell price on line 10 equals the combined total of lines 20-50", "The sell price on line 10 not equals to total of lines 20-50",
										"", driver);
							}
							
							clickSideBarSmart();
							clickonSaveasQuote();
							driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							enterCancelButtonInPoupHdr();
							String QuoteNum1= GetQuoteNumber();
							//click change mode button
							clickChangeModeIcon();					
							enterRepMarginPercent(data.get("RepPercentage")); //4
							enterRepMarginPercent(data.get("RepPercentage"));
							enterRepMarginPercent(data.get("RepPercentage"));
							enterRepMarginPercent(data.get("RepPercentage"));
							clickUpdateCosting();
							String FinalPrice1= getPriceValueFromLineItemTable(data.get("PriceValue1"));
							Float FP1= Float.valueOf(FinalPrice1);
							String FinalPrice2= getPriceValueFromLineItemTable(data.get("PriceValue2"));
							Float FP2= Float.valueOf(FinalPrice2);
							String FinalPrice3= getPriceValueFromLineItemTable(data.get("PriceValue3"));
							Float FP3= Float.valueOf(FinalPrice3);
							String FinalPrice4= getPriceValueFromLineItemTable(data.get("PriceValue4"));
							Float FP4= Float.valueOf(FinalPrice4);
							Float FinalPriceofFour = FP1+FP2+FP3+FP4;
							String TotalPriceAfterUpdation = getFirstElementPriceValueFromLineItem();
						    Float PricefoLine10Value= Float.valueOf(TotalPriceAfterUpdation);

							if (PricefoLine10Value==FinalPriceofFour) {
								reporter.SuccessReport("Confirm the sell price on line 10 equals the combined total of lines 20-50","The sell price on line 10 equals the combined total of lines 20-50",
										"Sell Price on Line 10: "+PricefoLine10Value+"/ Total Price of lines 10-50:"+FinalPriceofFour);
							} else {
								reporter.failureReport("Confirm the sell price on line 10 equals the combined total of lines 20-50", "The sell price on line 10 not equals to total of lines 20-50",
										"", driver);
							}
													
							clickSideBarSmart();
							clickClosthedocument(QuoteNum1);
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
			//ReportStatus.fnUpdateResultStatus("CQT54_IPSConfigurationPartnerCTOBOM", "CQT_54", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT54_IPSConfigurationPartnerCTOBOM", "CQT_54", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}


}