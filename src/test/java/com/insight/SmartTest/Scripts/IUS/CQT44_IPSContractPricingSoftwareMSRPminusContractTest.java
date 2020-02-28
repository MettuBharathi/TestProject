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

public class CQT44_IPSContractPricingSoftwareMSRPminusContractTest extends HomeLib{
	
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : CQT44_IPSContractPricingSoftwareMSRPminusContract
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
		public void CQT44_IPSContractPricingSoftwareMSRPminusContract(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT44_IPSContractPricingSoftwareMSRPminusContract", TestData_Smart, "Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo(
							"CQT44_IPSContractPricingSoftwareMSRPminusContract", TestData_Smart, "Create_Quote",
							intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSContractPricingSoftwareMSRPminusContract");
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"), data.get("Password"),
							data.get("SoldToAcct"), data.get("SalesOrg"));
					//Add Materails
					Addmaterail(data.get("Material1"));
					Addmaterail(data.get("Material2"));
					Addmaterail(data.get("Material3"));
					//Click On Contract
					clickonConXSystem(data.get("ItemNum"));// 000010
					clickOnContractId(data.get("contactid"));
					clickOnCopyContarctToallLineItems();
					clickYesButtontocloseDocument();
					clickDoneButton();
					//Update Costing 
					clickUpdateCosting();
					clickSideBarSmart();
					clickonSaveasQuote();
					enterCancelButtonInPoupHdr();
					//validate Quote Number
					String QuoteNum = GetQuoteNumber();
					ClickOnDisplayMode();
					clickonConXSystem(data.get("ItemNum"));// 000010
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab"));// Pricing
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
                    	if(i==0){ //ZPML--0
                     	Price1 = Price.get(i);
                    	}
                    	if(i==1){  // ZDML--1
                            Price2 = Price.get(i);
                    	}
                    	if(i==2){   // YP00--2
                            Price3 = Price.get(i);
                    	}
                    	if(i==3){    // ZP00--3
                           Price4 = Price.get(i);
                    	}
                    }
					
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab2"));//Contracts
					String ContractId=getContractId();
					VerifyContractPriceShouldbeEqualToYPOO(ContractId,Price3);
					clickDoneButton();
					VerifyZPMLMinusZDMLShouldbeEqualToYP00(Price1,Price2,Price3);
					VerifyZPMLMinusZDMLShouldbeEqualToZP00(Price1,Price2,Price4);
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
			// ReportStatus.fnUpdateResultStatus("IPSContractPricingSoftwareMSRPminusContract",
			// "CQT_44", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("IPSContractPricingSoftwareMSRPminusContract", "CQT_44",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
