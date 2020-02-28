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

public class CQT42_IPSContractPricingSoftwareFixedPriceContractTest extends HomeLib{
	
	loginLib loginlib=new loginLib();
	// #############################################################################################################
		// # Name of the Test : CQT42_IPSContractPricingSoftwareFixedPriceContract
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
		public void CQT42_IPSContractPricingSoftwareFixedPriceContract(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT42_IPSContractPricingSoftwareFixedPriceContract", TestData_Smart, "Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT42_IPSContractPricingSoftwareFixedPriceContract", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSContractPricingSoftwareFixedPriceContract");
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"),data.get("Password"),data.get("SoldToAcct"),data.get("SalesOrg"));
					//ADD Materails
					Addmaterail(data.get("Material1"));
					Addmaterail(data.get("Material2"));
					Addmaterail(data.get("Material3"));
					//Click on Contract ID
					clickonConXSystem(data.get("ItemNum"));
					clickOnContractId(data.get("contactid"));
					clickonNextLineItemArrowsymbolinPopUp();
					clickOnContractId(data.get("contactid"));
					clickonNextLineItemArrowsymbolinPopUp();
					clickOnContractId(data.get("contactid"));
					clickDoneButton();
					//Update Costing 
					clickUpdateCosting();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					clickSideBarSmart();
					clickonSaveasQuote();
					enterCancelButtonInPoupHdr();
					String QuoteNum= GetQuoteNumber();
					ClickOnDisplayMode();
					clickonConXSystem(data.get("ItemNum"));//000010
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab"));//Pricing
					//Get data from the pricing tab
					// Get data from the pricing tab
					List<Float> Price = new ArrayList<>();
					int a= Integer.parseInt(data.get("Value"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"),a);// ZPLS--0
                    System.out.println(Price.size());
                    float	Price1=0;
                    float	Price2=0;
                    float	Price3=0;
                    for(int i =0;i<Price.size();i++){
                    	if(i==0){ //ZPFX--0
                     	Price1 = Price.get(i);
                    	}
                    	if(i==1){  // ZP00--1
                            Price2 = Price.get(i);
                    	}
                    	if(i==2){   // YP00--2
                            Price3 = Price.get(i);
                    	}
                    }
				
					clickDoneButton();
					VerifyZPFXShouldbeEqualToYP00(Price1,Price3);
					VerifyZPFXShouldbeEqualToZP00(Price1,Price2);
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
				//ReportStatus.fnUpdateResultStatus("IPSContractPricingSoftwareFixedPriceContract", "CQT_42", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("IPSContractPricingSoftwareFixedPriceContract", "CQT_42", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}


	}
