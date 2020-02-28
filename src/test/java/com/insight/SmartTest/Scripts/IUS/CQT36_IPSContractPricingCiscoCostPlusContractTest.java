package com.insight.SmartTest.Scripts.IUS;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT36_IPSContractPricingCiscoCostPlusContractTest extends HomeLib{
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : CQT36_IPSContractPricingCiscoCostPlusContract
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
		public void CQT36_IPSContractPricingCiscoCostPlusContract(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT36_IPSContractPricingCiscoCostPlusContract", TestData_Smart, "Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT36_IPSContractPricingCiscoCostPlusContract", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSQuoteWithYCGE");
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"),data.get("Password"),data.get("SoldToAcct"),data.get("SalesOrg"));
					clickAcquireEstimateBtn();
					enterEstimateNumber(data.get("EstimateID"));
					clickonConXSystem(data.get("LineItem1"));//000010
					clickonRightArrowforLineItem();
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab1"));//VC
					getDurationTime(data.get("LineItem2"),data.get("Duration1"));
					clickonRightArrowforLineItem();
					clickonRightArrowforLineItem();
					getDurationTime(data.get("LineItem3"),data.get("Duration2"));
					clickonRightArrowforLineItem();
					clickonRightArrowforLineItem();
					getDurationTime(data.get("LineItem4"),data.get("Duration3"));
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab2"));//Contracts
					clickOnContractId(data.get("contactid"));
					clickOnCopyContarctToallLineItems();
					clickYesButtontocloseDocument();
					clickDoneButton();
					clickUpdateCosting();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					clickSideBarSmart();
					clickonSaveasQuote();
					enterCancelButtonInPoupHdr();
					String QuoteNum= GetQuoteNumber();
					clickonConXSystem(data.get("LineItem1"));//000010
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab3"));//Pricing
					// Get data from the pricing tab
					List<Float> Price = new ArrayList<>();
					int a= Integer.parseInt(data.get("Value"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"),a);// ZPLS--0
                    System.out.println(Price.size());
                    float	Price1=0;
                    float	Price2=0;
                    float	Price3=0;
                    float	Price4=0;
                    for(int i =0;i<Price.size();i++){
                    	if(i==0){ //Z0RC--0
                     	Price1 = Price.get(i);
                    	}
                    	if(i==1){  // YMSM--1
                            Price2 = Price.get(i);
                    	}
                    	if(i==2){   // ZP00--2
                            Price3 = Price.get(i);
                    	}
                    	if(i==3){    // YP00--3
                           Price4 = Price.get(i);
                    	}
                    }
					// Need to compare pricing
					clickDoneButton();
					VerifyZ0RCPlusYMSMShouldbeEqualToZP00(Price1,Price2,Price3);
					VerifyZ0RCPlusYMSMShouldbeEqualToYP00(Price1,Price2,Price4);
					clickSideBarSmart();
					clickClosthedocument(QuoteNum);
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
			//ReportStatus.fnUpdateResultStatus("IPSContractPricingCiscoCostPlusContract", "CQT_36", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("IPSContractPricingCiscoCostPlusContract", "CQT_36", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}


}