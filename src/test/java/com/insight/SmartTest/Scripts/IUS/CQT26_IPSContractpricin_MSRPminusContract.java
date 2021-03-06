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
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT26_IPSContractpricin_MSRPminusContract extends HomeLib {

	loginLib loginlib = new loginLib();
	// #############################################################################################################
	// # Name of the Test : CQT26_IPSContractpricin_MSRPminusContract
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
	public void CQT26(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT26_IPSContractpricin_MSRPminusContract",
					TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				// initilizing libraries and testdata
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo(
						"CQT26_IPSContractpricin_MSRPminusContract", TestData_Smart, "Create_Quote",
						intCounter);
				// Test Steps execution
				try {
					fnOpenTest();
					navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
					clickOnCreateQuoteLink();
					enterSoldTo(data.get("SoldToValue"));
					// 10529929
					AddMaterialOnLineItem(data.get("MaterialID1"));//PWR-400W-AC
					AddMaterialOnLineItem(data.get("MaterialID2"));//WS-C2960X-24PS-L
					AddMaterialOnLineItem(data.get("MaterialID3"));//CON-NSNT-C881WACC
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
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"),a);// ZPML--0
                    System.out.println(Price.size());
                    float	Price1=0;
                    float	Price2=0;
                    float	Price3=0;
                    float	Price4=0;
                    for(int i =0;i<Price.size();i++){
                    	if(i==0){ //ZPML--0
                     	Price1 = Price.get(i);
                    	}
                    	if(i==1){  // ZP00--1
                            Price2 = Price.get(i);
                    	}
                    	if(i==2){   // ZDML--2
                            Price3 = Price.get(i);
                    	}
                    	if(i==3){    // YP00--3
                           Price4 = Price.get(i);
                    	}
                    }
                    
                    selectCOntractSubTabName(data.get("contractTab2"));// Contracts
					clickDoneButton();
					// Need to compare pricing
					verifyZPOOequalstheYDLPandZPLS(Price2, Price1, Price3);

					if (Price2==Price4) { // compare ZPOO equals to YPOO
				reporter.SuccessReport("Verify that YP00 equals to ZP00 value.","ZP00:"+Price2+"YP00: "+Price4, "");
				} else {
				reporter.failureReport("Verify that YP00 equals to ZP00 value.","ZP00:"+Price2+"YP00: "+Price4 + "both are not same","", driver);
				}
					VerifyContractPriceShouldbeEqualToYPOO(Price4, data.get("contactid"));	

					clickSideBarSmart();
					clickClosthedocument(QuoteNum1);
					clickYesButtontocloseDocument();

					System.out.println("Test completed");
				} catch (Exception e) {
					ReportStatus.blnStatus = false;

					gErrorMessage = e.getMessage();
					System.out.println(gErrorMessage);
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("CQT26_IPSContractpricin_MSRPminusContract", "TC_26",
						ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("CQT26_IPSContractpricin_MSRPminusContract", "TC_26",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}



}
