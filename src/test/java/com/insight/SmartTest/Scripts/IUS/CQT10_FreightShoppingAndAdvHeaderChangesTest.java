package com.insight.SmartTest.Scripts.IUS;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT10_FreightShoppingAndAdvHeaderChangesTest extends HomeLib {
	
	loginLib loginlib=new loginLib();
	// #############################################################################################################
	// #       Name of the Test         :  CQT10_FreightShoppingAndAdvHeaderChanges
	// #       Migration Author         :  Cigniti Technologies
	// #
	// #       Date of Migration        : November 2019
	// #       DESCRIPTION              : This method is to verify FreightShoppingAndAdvHeaderChanges
	// #       Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT10(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT10_FreightShoppingAndAdvHeaderChanges_Action1_Script",TestData_Smart,"Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

					
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT10_FreightShoppingAndAdvHeaderChanges_Action1_Script", TestData_Smart, "Create_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("FreightShoppingAndAdvHeaderChanges");
						LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToAcct"));
					/*
					 * navigateToApplication("SMART");
					 * loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"))
					 * ; clickOnCreateQuoteLink(); ClickOnsideViewBar();
					 * enterSoldToValue(data.get("SoldToAcct")); clickOnSoldToSearchIcon();
					 */				        
				        
				        AddLineItems("material",data.get("Material"),0);
				        clickAdvancedHeader();	
				        clickAdvancedHeaderTab("Freight");
				        selectShippingCodnition("10 Next day");
				        clickUpdateCosting();
				        SwipeUpapplication();
				        clickAdvancedHeader();	
				        String FrghtCost = getmfgPricevalue("000010","frtCost","");		        
				        String FrghtCharge = getmfgPricevalue("000010","frtCharge","");
				        String FrghtFloor =getmfgPricevalue("000010","frtFloor","");
				        String FrghtTarget =getmfgPricevalue("000010","frtTarget","");
				        SelectoptionfromDropdown("Freight", "sales-analysis");
				        sortingofcolumns("Cost");
				        doubleclickonCostcell("Cost", 0);
				        clickUpdateCosting();
				        //After adding cost from freight popup
				        String FrghtCost1 = getmfgPricevalue("000010","frtCost","");		        
				        String FrghtCharge1 = getmfgPricevalue("000010","frtCharge","");
				        String FrghtFloor1 =getmfgPricevalue("000010","frtFloor","");
				        String FrghtTarget1 =getmfgPricevalue("000010","frtTarget","");
				        if(FrghtCost!=FrghtCost1) {
				        	System.out.println("The FrghtCharge freight costs were updated as expected."+ FrghtCost1);
				        }
				        else {
				        	System.out.println("Testcase failed");
				        	System.out.println("The FrghtCharge freight costs were not updated as expected.");
				        }
				        if(FrghtCharge!=FrghtCharge1) {
				        	System.out.println("The FrghtCharge freight costs were updated as expected."+ FrghtCharge1);
				        }
				        else {
				        	System.out.println("Testcase failed");
				        	System.out.println("The FrghtCharge freight costs were not updated as expected.");
				        }
				        if(FrghtFloor!=FrghtFloor1) {
				        	System.out.println("The FrghtCharge freight costs were updated as expected."+ FrghtFloor1);
				        }
				        else {
				        	System.out.println("Testcase failed");
				        	System.out.println("The FrghtCharge freight costs were not updated as expected.");
				        }
				        if(FrghtTarget!=FrghtTarget1) {
				        	System.out.println("The FrghtCharge freight costs were updated as expected."+ FrghtTarget1);
				        }
				        else {
				        	System.out.println("Testcase failed");
				        	System.out.println("The FrghtCharge freight costs were not updated as expected.");
				        }
				        clickAdvancedHeader();
				        selectShippingCodnition("05 Next day AM");
				        clickUpdateCosting();
				        clickAdvancedHeader();
				        String FrghtCost2 = getmfgPricevalue("000010","frtCost","");		        
				        String FrghtCharge2 = getmfgPricevalue("000010","frtCharge","");
				        String FrghtFloor2 =getmfgPricevalue("000010","frtFloor","");
				        String FrghtTarget2 =getmfgPricevalue("000010","frtTarget","");
				        System.out.println(FrghtCost2+" "+FrghtCharge2+" "+FrghtFloor2+" "+FrghtTarget2);
				        
				        
				     
					       System.out.println("Testcase completed");
					}      
				        catch (Exception e) {
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
					//ReportStatus.fnUpdateResultStatus("CQT31_IPSERateNonTax_Action1_Script", "TC_CQT31", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
			    finally {
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("CQT31_IPSERateNonTax_Action1_Script", "TC_CQT31", ReportStatus.strMethodName, counter, browser);
					//fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}


			}


