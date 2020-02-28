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

public class CQT57_IPSQuoteWithYCGETest extends HomeLib{
	
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : CQT57_IPSQuoteWithYCGE
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
	public void TC_CQT57(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT57_IPSQuoteWithYCGE", TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT57_IPSQuoteWithYCGE", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSQuoteWithYCGE");
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"), data.get("Password"),
							data.get("SoldToAcct"), data.get("SalesOrg"));
					Addmaterail(data.get("Material1"));
					Addmaterail(data.get("Material2"));
					clickonConXSystem(data.get("ItemNum1"));// 000010
					clickOnContractId(data.get("contactid"));
					clickOnCopyContarctToallLineItems();
					clickYesButtontocloseDocument();
					clickDoneButton();
					clickUpdateCosting();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					Thread.sleep(4000);
					String RepCost1=getRepCostofLineItem(data.get("ItemNum1"));
					String RepCost2=getRepCostofLineItem(data.get("ItemNum1"));
					clickonConXSystem(data.get("ItemNum1"));// 000010
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab"));// Pricing
					// Get data from the pricing tab
					List<Float> Price = new ArrayList<>();
					int a= Integer.parseInt(data.get("Value"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"),a);// ZPLS--0
                    System.out.println(Price.size());
                  
                    float	Price1=0;
                    float	Price2=0;
                    for(int i =0;i<Price.size();i++){
                    	if(i==0){ //YCGE1--0
                     	Price1 = Price.get(i);
                    	}
                    	if(i==1){  // ZMOV1--1
                            Price2 = Price.get(i);
                    	}
                    }
					clickonNextLineItemArrowsymbolinPopUp();
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab"));// Pricing
					// Get data from the pricing tab
					List<Float> price = new ArrayList<>();
					int b= Integer.parseInt(data.get("Value"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"),a);// ZPLS--0
                    System.out.println(Price.size());
                    float	price1=0;
                    float	price2=0;
                    for(int i =0;i<Price.size();i++){
                    	if(i==0){ //YCGE1--0
                     	price1 = price.get(i);
                    	}
                    	if(i==1){  // ZMOV1--1
                            price2 = price.get(i);
                    	}
                    }
					clickDoneButton();
					VerifyYCGEPlusZMOVShouldbeEqualToRepCost(Price1, Price2,RepCost1, data.get("ItemNum1"));
					VerifyYCGEPlusZMOVShouldbeEqualToRepCost(price1, price2,RepCost2, data.get("ItemNum1"));
					Thread.sleep(4000);
					clickSideBarSmart();
					Thread.sleep(4000);
					clickonSaveasQuote();
					Thread.sleep(4000);
					enterCancelButtonInPoupHdr();
					Thread.sleep(4000);
					String QuoteNum = GetQuoteNumber();
					Thread.sleep(4000);
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
			//ReportStatus.fnUpdateResultStatus("IPSQuoteWithYCGE", "CQT_57", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("IPSQuoteWithYCGE", "CQT_57", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}


}
