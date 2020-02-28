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

public class CQT49_IPSCappingAtListPriceWithTICTest extends HomeLib{
	
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : CQT49_IPSCappingAtListPriceWithTIC
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
		public void TC_CQT49(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT49_IPSCappingAtListPriceWithTIC", TestData_Smart, "Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT49_IPSCappingAtListPriceWithTIC",
							TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSCappingAtListPriceWithTIC");
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"), data.get("Password"),
							data.get("SoldToAcct"), data.get("SalesOrg"));
					Addmaterail(data.get("Material1"));
					Addmaterail(data.get("Material2"));
					Addmaterail(data.get("Material3"));
					clickonConXSystem(data.get("ItemNum1"));// 000010
					clickOnContractId(data.get("contactid"));
					clickOnCopyContarctToallLineItems();
					clickYesButtontocloseDocument();
					clickonNextLineItemArrowsymbolinPopUp();
					clickonNextLineItemArrowsymbolinPopUp();
					clickDoneButton();
					Thread.sleep(4000);
					clickUpdateCosting();
					Thread.sleep(4000);
					clickonConXSystem(data.get("ItemNum1"));// 000010
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab1"));// Pricing
					// Get data from the pricing tab
					List<Float> Price = new ArrayList<>();
					int a= Integer.parseInt(data.get("Value1"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue1"),a);
                    System.out.println(Price.size());
                    float	Price1=0;
                    float	Price2=0;
                    float	Price3=0;
                    float	Price4=0;
                    for(int i =0;i<Price.size();i++){
                    	if(i==0){ //ZPLS--0
                     	Price1 = Price.get(i);
                    	}
                    	if(i==1){  // ZP00--1
                            Price2 = Price.get(i);
                    	}
                    	if(i==2){   // YP00--2
                            Price3 = Price.get(i);
                    }
                    }
                  
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab2"));//Contracts
					String ContractId=getContractId();
					VerifyContractPriceShouldbeEqualToYPOO(Price3,ContractId);
					VerifyContractPriceShouldbeEqualToZPOO(Price2,ContractId);
					VerifyContractPriceShouldbeEqualToZPLS(Price1,ContractId);
					clickDoneButton();
                    //Update Trade in Valude for line item 10
					clickUpdateCosting();
				    Thread.sleep(4000);
				    clickonConXSystem(data.get("ItemNum1"));//000010
				    clickOnTabsInLineItemDetailsPopUp(data.get("Tab1"));//Pricing
					//Get data from the pricing tab
					 List<Float> Pricelineitem1 = new ArrayList<>();
					int a1= Integer.parseInt(data.get("Value2"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue2"),a1);// ZPLS--0
                    System.out.println(Price.size());
                    float	price1=0;
                    float	price2=0;
                    float	price3=0;
                    float	price4=0;
                    for(int i =0;i<Price.size();i++){
                    	if(i==0){ //ZPLS1--0
                     	price1 = Pricelineitem1.get(i);
                    	}
                    	if(i==1){  // ZTIC1--1
                            price2 = Pricelineitem1.get(i);
                    	}
                    	if(i==2){   // ZP001--2
                            price3 = Pricelineitem1.get(i);
                    }
                    if(i==3){    // YP001--3
                           price4 = Pricelineitem1.get(i);
                    	}
                    }
					
					clickonNextLineItemArrowsymbolinPopUp();
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab"));//Pricing
					//Get data from the pricing tab
					 List<Float> Pricelineitem2 = new ArrayList<>();
					int b= Integer.parseInt(data.get("Value2"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue2"),a1);// ZPLS--0
                    System.out.println(Price.size());
                    float	Price5=0;
                    float	Price6=0;
                    float	Price7=0;
                    float	Price8=0;
                    for(int i =0;i<Price.size();i++){
                    	if(i==0){ //ZPLS2--0
                    		Price5 = Pricelineitem2.get(i);
                    	}
                    	if(i==1){  // ZTIC2--1
                    		Price6 = Pricelineitem2.get(i);
                    	}
                    	if(i==2){   // ZP002--2
                    		Price7 = Pricelineitem2.get(i);
                    }
                    if(i==3){    // YP002--3
                    	Price8 = Pricelineitem2.get(i);
                    	}
                    }
					clickonNextLineItemArrowsymbolinPopUp();
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab1"));//Pricing
					//Get data from the pricing tab
					 List<Float> Pricelineitem3 = new ArrayList<>();
					int c= Integer.parseInt(data.get("Value2"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue2"),a1);// ZPLS--0
                    System.out.println(Price.size());
                    float	Price9=0;
                    float	Price10=0;
                    float	Price11=0;
                    float	Price12=0;
                    for(int i =0;i<Price.size();i++){
                    	if(i==0){ //ZPLS3--0
                    		Price9 = Pricelineitem3.get(i);
                    	}
                    	if(i==1){  // ZTIC3--1
                    		Price10 = Pricelineitem3.get(i);
                    	}
                    	if(i==2){   // ZP003--2
                    		Price11 = Pricelineitem3.get(i);
                    }
                    if(i==3){    // YP003--3
                    	Price12 = Pricelineitem3.get(i);
                    	}
                    }
					clickDoneButton();
				    VerifyYP00houldbeEqualToAllLineItems(price4,Price8,Price12);
				    VerifyZP00houldbeEqualToAllLineItems(price3, Price7 ,Price11);
				    VerifyZPLSShouldbeEqualToAllLineItems(price1,Price5,Price9);
				    VerifyZTICMinusYP00ShouldbeEqualToZP00(price3,Price7,Price11,price3,Price8,Price12,price1,Price6,Price10);
				    clickUpdateCosting();
				    Thread.sleep(4000);
				    clickSideBarSmart();
				    Thread.sleep(4000);
				    clickonSaveasQuote();
				    Thread.sleep(4000);
				    enterCancelButtonInPoupHdr();
				    Thread.sleep(4000);
				    String QuoteNum= GetQuoteNumber();
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
				//ReportStatus.fnUpdateResultStatus("IPSCappingAtListPriceWithTIC", "CQT_49", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("IPSCappingAtListPriceWithTIC", "CQT_49", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}


	}