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

public class CQT43_IPSContractPricingSoftwareInsightListminusContractTest extends HomeLib{
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : CQT43_IPSContractPricingSoftwareInsightListminusContract
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
		public void CQT43_IPSContractPricingSoftwareInsightListminusContract(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT43_IPSContractPricingSoftwareInsightListminusContract", TestData_Smart, "Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT43_IPSContractPricingSoftwareInsightListminusContract", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSContractPricingSoftwareInsightListminusContract");
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"),data.get("Password"),data.get("SoldToAcct"),data.get("SalesOrg"));
					Addmaterail(data.get("Material1"));//65272405BB01A12
					clickMaterail();
					clickonConXSystem(data.get("ItemNum"));//000010
					clickOnContractId(data.get("contactid"));
					clickOnCopyContarctToallLineItems();
					clickYesButtontocloseDocument();
					clickDoneButton();
					clickUpdateCosting();
					clickSideBarSmart();
					clickonSaveasQuote();
					sendTextInReasonPopup(data.get("Text"));
					clickonSaveasQuoteinReasonWarningPopUp();
					ClickOnDisplayMode();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					String QuoteNum= GetQuoteNumber();
					clickonConXSystem(data.get("ItemNum"));//000010
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab1"));//Pricing
					
					 // Get data from the pricing tab
					List<Float> Price = new ArrayList<>();
					int a= Integer.parseInt(data.get("Value"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"),a);
                    System.out.println(Price.size());
                    float	Price1=0;
                    float	Price2=0;
                    float	Price3=0;
                    float	Price4=0;
                    for(int i =0;i<Price.size();i++){
                    	if(i==0){ //ZPLS--0
                     	Price1 = Price.get(i);
                    	}
                    	if(i==1){  // YDLP--1
                            Price2 = Price.get(i);
                    	}
                    	if(i==2){   // ZP00--2
                            Price3 = Price.get(i);
                    	}
                    	if(i==3){    // YP00--3
                           Price4 = Price.get(i);
                    	}
                    }
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab2"));//Contracts
					String ContractId=getContractId();
					clickDoneButton();
					//Need to compare pricings
					VerifyZPLSShouldbeEqualToZPOOandYPOO(Price3,Price1,Price2);
					VerifyZPOOShouldbeEqualToYPOO(Price3,Price4);
					VerifyContractPriceShouldbeEqualToYPOO(Price4,ContractId);
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
				//ReportStatus.fnUpdateResultStatus("IPSContractPricingSoftwareInsightListminusContract", "CQT_43", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("IPSContractPricingSoftwareInsightListminusContract", "CQT_43", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}


	}
