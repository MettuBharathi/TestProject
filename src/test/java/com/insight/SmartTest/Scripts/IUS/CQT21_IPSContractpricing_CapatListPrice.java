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

public class CQT21_IPSContractpricing_CapatListPrice extends HomeLib {
	
	loginLib loginlib=new loginLib();
	// #############################################################################################################
				// # Name of the Test : CQT21_IPSContractpricing_CapatListPrice
				// # Migration Author : Cigniti Technologies
				// #
				// # Date of Migration : Nov 2019
				// # DESCRIPTION : This Test is used to verify IPSContractpricing_CapatListPrice
				// functionality in the products display page.
				// # Parameters : StartRow ,EndRow , nextTestJoin
				// #
				// ###############################################################################################################

				@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
				@Test
				public void TC_CQT21(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
					int counter = 0;
			        try {
				    
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT21_IPSContractpricing_CapatListPrice",TestData_Smart,"Create_Quote");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT21_IPSContractpricing_CapatListPrice", TestData_Smart, "Create_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("IPSContractpricing_CapatListPrice");
						LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToAcct"));
					/*
					 * navigateToApplication("SMART");
					 * loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"))
					 * ; clickOnCreateQuoteLink(); enterSoldToValue(data.get("SoldToValue"));
					 * clickOnSoldToSearchIcon();//10529929
					 */		AddMaterialOnLineItem(data.get("MaterialID"));	
							clickOnCOntractIDinLineItemsList();
							selectCOntractID(data.get("contactid"),data.get("contactTabName"));
							clickUpdateCosting();
							driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							clickSideBarSmart();
							clickonSaveasQuote();
							enterCancelButtonInPoupHdr();
							String QuoteNum= GetQuoteNumber();
							clickOnCOntractIDinLineItemsList();
							selectCOntractSubTabName(data.get("contactTab"));//Pricing
							
							//Get data from the pricing tab
							List<Float> Price = new ArrayList<>();
							int a = Integer.parseInt(data.get("Value"));
							Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"), a);// ZPFX--0
							System.out.println(Price.size());
							float Price1 = 0;
							float Price2 = 0;
							float Price3 = 0;
							float Price4 = 0;
							for (int i = 0; i < Price.size(); i++) {
								if (i == 0) { // ZPFX--0
									Price1 = Price.get(i);
								}
								if (i == 1) { // ZPLS--1
									Price2 = Price.get(i);
								}
								if (i == 2) { // ZP00--2
									Price3 = Price.get(i);
								}
								if (i == 3) { // YP00--3
									Price4 = Price.get(i);
								}
							}							
							selectCOntractID(data.get("contactid"),data.get("contactTabName"));//Contracts
							VerifyContractPriceShouldbeEqualToYPOO(Price4,data.get("contractid"));
							clickDoneButton();
							//Need to compare pricing
							VerifyZPFXShouldbeGreaterThanZPLS(Price1,Price2);
							VerifyZPLSShouldbeEqualToZPOOandYPOO(Price2,Price3,Price4 ); //	Verify ZPLS should equal YP00 & ZP00
  						    clickDoneButton();
							clickSideBarSmart();
							clickClosthedocument(QuoteNum);
							clickYesButtontocloseDocument();				
							
							
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
					//ReportStatus.fnUpdateResultStatus("CQT21_IPSContractpricing_CapatListPrice", "TC_CQT21", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
			    finally {
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("CQT21_IPSContractpricing_CapatListPrice", "TC_CQT21", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}


			}

