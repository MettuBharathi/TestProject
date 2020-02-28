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

public class CQT25_IPSContractpricing_InsightListminusContract extends HomeLib {

	loginLib loginlib = new loginLib();
	// #############################################################################################################
	// # Name of the Test : CQT25_IPSContractpricing_InsightListminusContract
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
	public void TC_CQT25(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
        try {
	    
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT25_IPSContractpricing_InsightListminusContract",TestData_Smart,"Create_Quote");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			try {
			counter = intCounter;
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT25_IPSContractpricing_InsightListminusContract", TestData_Smart, "Create_Quote", intCounter);
			TestEngineWeb.reporter.initTestCaseDescription("IPSContractpricing_InsightListminusContract");
			LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToAcct"));
					AddMaterialOnLineItem(data.get("MaterialID1"));
					AddMaterialOnLineItem(data.get("MaterialID2"));
					AddMaterialOnLineItem(data.get("MaterialID3"));
					clickOnCOntractIDinLineItemsList();
					selectCOntractID(data.get("contactid"), data.get("contactTabName"));
					copyAllContractstoAllLines();
					clickDoneButton();
					clickUpdateCosting();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					clickSideBarSmart();
					clickonSaveasQuote();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					enterCancelButtonInPoupHdr();
					String QuoteNum1 = GetQuoteNumber();
					clickOnCOntractIDinLineItemsList();
					selectCOntractSubTabName(data.get("contractTab1"));// Pricing

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
                    	if(i==0){ //ZPLS--0
                     	Price1 = Price.get(i);
                    	}
                    	if(i==1){  // ZPOO--1
                            Price2 = Price.get(i);
                    	}
                    	if(i==2){   // YPOO--2
                            Price3 = Price.get(i);
                    	}
                    	if(i==3){    // YDLP--3
                           Price4 = Price.get(i);
                    	}
                    }
					// Need to compare pricing
					verifyZPOOequalstheYDLPandZPLS(Price2, Price4, Price1);

					if (Price3==Price2) { // compare ZPOO equals to YPOO
				reporter.SuccessReport("Price value comparision", data.get("text3") + "Value is " + Price3
				+ " and  " + data.get("text4") + "Value is " + Price4 + "both are same", "");
				} else {
				reporter.failureReport("Price value comparision", data.get("text3") + "Value is " + Price3
				+ " and  " + data.get("text4") + "Value is " + Price4 + "both are same", "", driver);
				}
					selectCOntractSubTabName(data.get("contractTab2"));// Contracts
					VerifyContractPriceShouldbeEqualToYPOO(Price3, data.get("contactid"));
					clickDoneButton();

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
			//ReportStatus.fnUpdateResultStatus("CQT25_IPSContractpricing_InsightListminusContract", "TC_CQT25", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
	    finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT25_IPSContractpricing_InsightListminusContract", "TC_CQT25", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}


	}

