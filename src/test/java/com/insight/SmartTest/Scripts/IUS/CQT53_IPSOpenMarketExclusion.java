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

public class CQT53_IPSOpenMarketExclusion  extends HomeLib {

	loginLib loginlib = new loginLib();
	// #############################################################################################################
	// # Name of the Test : CQT53_IPSOpenMarketExclusion
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
	public void CQT53(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT53_IPSOpenMarketExclusion",TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT53_IPSOpenMarketExclusion", TestData_Smart, "Create_Quote", intCounter);
					fnOpenTest();
					navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
					clickOnCreateQuoteLink();
					enterSoldTo(data.get("SoldToValue"));
					// 0010600194
					AddMaterialOnLineItem(data.get("MaterialID1")); //F2CU040BTBLK
					AddMaterialOnLineItem(data.get("MaterialID2")); // F3W43AA
					AddMaterialOnLineItem(data.get("MaterialID3"));	// D2W67A8#ABA
					AddMaterialOnLineItem(data.get("MaterialID4"));	//  QY776AA
					clickOnCOntractIDinLineItemsList();					
					selectCOntractID(data.get("contactid"),data.get("Tab1"));//contracts					
					// Verify Enrollment Code//
					
					clickonRightArrowforLineItem();
					selectCOntractID(data.get("contactid"),data.get("Tab1"));					
					// Verify Enrollment Code//
					
					clickonRightArrowforLineItem();
					selectCOntractID(data.get("contactid"),data.get("Tab1"));
					// Verify Enrollment Code//
					clickonRightArrowforLineItem();
					selectCOntractID(data.get("contactid"),data.get("Tab1"));
					clickDoneButton();					 
					clickUpdateCosting();
					driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
					clickSideBarSmart();
					clickonSaveasQuote();
					enterCancelButtonInPoupHdr();
					String QuoteNum= GetQuoteNumber();					
					clickOnCOntractIDinLineItemsList();
					copyAllContractstoAllLines();
					clickonRightArrowforLineItem();//Line2
					clickonRightArrowforLineItem();//Line3
					clickDoneButton();
					clickUpdateCosting();
					ClickOnXsymbolunderCon();
					selectCOntractSubTabName(data.get("contactTabName1"));// Pricing		
					// Get data from the pricing tab
					List<Float> Price = new ArrayList<>();
					int a= Integer.parseInt(data.get("Value"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"),a);// Z0RC--0
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
					selectCOntractID(data.get("contactid"),data.get("Tab1"));
					clickDoneButton();
					//Need to compare pricing
					VerifyZ0RCPlusYMSMequalstheYP00andZP00( Price3, Price2, Price1 );
					VerifyZPOOShouldbeEqualToYPOO(Price3,Price4);
					VerifyContractPriceShouldbeEqualToYPOO(Price4,data.get("contractid"));
					
					clickSideBarSmart();
					clickClosthedocument(QuoteNum);
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
			ReportStatus.fnUpdateResultStatus("CQT53_IPSOpenMarketExclusion", "TC_053",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT53_IPSOpenMarketExclusion", "TC_053",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}
