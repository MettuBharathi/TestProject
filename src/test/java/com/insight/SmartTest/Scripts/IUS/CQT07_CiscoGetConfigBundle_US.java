package com.insight.SmartTest.Scripts.IUS;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT07_CiscoGetConfigBundle_US extends HomeLib {

	loginLib loginlib = new loginLib();
	// #############################################################################################################
	// # Name of the Test : CQT15_IPSContractPricingError
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
	public void CQT07(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT07_CiscoGetConfigBundle_US",TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT07_CiscoGetConfigBundle_US", TestData_Smart, "Create_Quote", intCounter);
					fnOpenTest();
					navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
					clickOnCreateQuoteLink();
					enterSoldTo(data.get("SoldToValue"));
					// 10529929
					 clickAcquireEstimateBtn();
					 enterEstimateNumber(data.get("Config ID"));
					//To Get the Rep cost for Line 20
					 String RepPrice1= getRepCostValueFromLineItemTable(data.get("Pricevalue")); //2--Line 20
					 Float P1= Float.valueOf(RepPrice1);							 
					 //click on material ID
					 clickOnVCSymbolinLineItemsList(data.get("LineItem")); //000020
					 selectCOntractSubTabName(data.get("contactTabName"));// Pricing
					 String PricingRate= getRateValueinPricingTable(data.get("priceType"));// ZPMU					 
					 Float Rate= Float.valueOf(PricingRate);
					 String PricingRate1= getRateValueinPricingTable(data.get("priceType1"));// ZPML					 
					 Float Rate1= Float.valueOf(PricingRate1);
					 selectCOntractSubTabName(data.get("contactTabName1"));// Coverage/Billing
					 //Verify start and end date is blank or not
					 verifyStartDateandEndDate();
					 // Enter Start Date and End Date
					 EnterStartDateandEndDate(data.get("startdate"),data.get("endDate"));
					 clickDoneButton();
						clickUpdateCosting();
						driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
						//	To Get Updated  Rep cost for Line 20
						String UpdatedRepPrice1= getRepCostValueFromLineItemTable(data.get("Pricevalue")); //2--Line 20
						 Float UpdatedP1= Float.valueOf(UpdatedRepPrice1);		
						 if(P1!=UpdatedP1){
							 reporter.SuccessReport("Verify the 'Rep Cost' for line 20","The Rep Cost exists and noted.", "Rep cost # "+P1+" Updated Rep Cost # "+UpdatedP1);
							} else {
								reporter.failureReport("Verify the 'Rep Cost' for line 20", "The Rep Cost does not exist","Rep cost # "+P1+" Updated Rep Cost # "+UpdatedP1, driver);							
						 }
						 //click on material ID
						clickOnVCSymbolinLineItemsList(data.get("LineItem")); //000020
						 selectCOntractSubTabName(data.get("contactTabName"));// Pricing
						 String UpdatedPricingRate= getRateValueinPricingTable(data.get("priceType"));// ZPMU					 
						 Float UpdatedRate= Float.valueOf(UpdatedPricingRate);
						 String UpdatedPricingRate1= getRateValueinPricingTable(data.get("priceType1"));// ZPML					 
						 Float UpdatedRate1= Float.valueOf(UpdatedPricingRate1);					
						 clickDoneButton();				
					clickSideBarSmart();
					Thread.sleep(3000);
					clickClosthedocument(data.get("Doctype"));
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
			ReportStatus.fnUpdateResultStatus("CQT07_CiscoGetConfigBundle_US", "TC_07",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT07_CiscoGetConfigBundle_US", "TC_07",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}
