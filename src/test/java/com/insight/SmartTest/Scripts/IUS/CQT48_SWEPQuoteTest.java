package com.insight.SmartTest.Scripts.IUS;

import java.util.Date;
import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT48_SWEPQuoteTest extends HomeLib {
	
	loginLib loginlib=new loginLib();
	// #############################################################################################################
	// #       Name of the Test         :  CQT48_SWEPQuote_Action1_Script
	// #       Migration Author         :  Cigniti Technologies
	// #
	// #       Date of Migration        : November 2019
	// #       DESCRIPTION              : This method is to verify SWEPQuote
	// #       Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT16(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT48_SWEPQuote_Action1_Script",TestData_Smart,"Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

					
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT48_SWEPQuote_Action1_Script", TestData_Smart, "Create_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("SWEPQuote");
						LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToAcct"));				        
				        selectPaymentTermsdropdown(data.get("PaymentTerms"));
				        AddLineItems("material",data.get("Material"),0);
				        
				        ClickOnXsymbolunderCon();
				        SelectContractId(3);
				        clickOnCopyContarctToallLineItems();
				        SearchButton();
				        EnterUSCOMMember("SPREADSHEET QUOTE USED? (Y/N):",data.get("SPREADSHEETQUOTE"));
				        String ActualTaavalue = getTaavalue();
				        if(ActualTaavalue.equalsIgnoreCase(data.get("SPREADSHEETQUOTE")))
				        	reporter.SuccessReport("TAA value::","Taa value is as expected : ","");
				        else
				        	reporter.failureReport("TAA value:: ","Testcase failed as taa value is not as expected","");
				        clickDoneButton();
				        
				        clickUpdateCosting();
				        clickAdvancedHeader();				       
				        getSpecialOrderType(data.get("SpecialOrderType"));
				        enterQuotenameinAdvancedHeader(data.get("QuoteName"));
				        SwipeUpapplication();
				        clickAdvancedHeader();
				        clickonSaveasQuote();				       
			            EnterEmail(data.get("Email"));
				        ClickOnSendbutton();
				        clickOkButton();
				        String QuoteNumber = GetQuoteNumber();
				        Swipedownapplication();
				        SelectoptionsUnderItemLine("Save CLIN File",0);
				       String filedata =  verifyDownloadedTextFile();
				       String filedatasplit[] = filedata.split("\\|");
				       String Material = filedatasplit[0];
				       String Price = filedatasplit[1];
				       String Part = filedatasplit[2];
				       String Materialongrid = getMaterialfromLineItems("material",0);
				       String Priceongrid = getMaterialfromLineItems("price",0);
				       float price =  Float.parseFloat(Priceongrid);
				       int partintextfiel = Integer.parseInt(Part);
					/*
					 * if(price!=0) System.out.println("Price exists as expected "+price); else
					 * System.out.println("Price not exists as expected "); int partintextfiel =
					 * Integer.parseInt(Part); if(partintextfiel!=0)
					 * System.out.println("Part exists as expected "+partintextfiel); else
					 * System.out.println("Part not exists as expected ");
					 */
				       VerifyEratevalue(price,partintextfiel);
				       ClickOnDisplayMode();
				       SwipeUpapplication();
				       SwipeUpapplication();
				       enterPONumber(data.get("setPoNum"));
				       enterPOrelNumber(data.get("setPORel"));
				    // clickOkButton();
				       enterSoldtoAttn(data.get("setSoldAttn"));
				       enterShiptoAttn(data.get("setShipAttn")); 
				       ClickOnConverToOrder();				       
				       clickonSaveasOrder();				       
				       clickOnSaveorderwithoutAttchment();	
				       clickCancel();
				       String Quotnumbr  = getSaveQuoteNumber();
				       System.out.println(Quotnumbr);
				       clickAdvancedHeader();
				       Swipedownapplication();
				       String ActualQuotename = GetQuoteName();
				       getSpecialOrderType(data.get("SpecialOrderType"));
				       if(ActualQuotename.equals(data.get("QuoteName")))
				    	   reporter.SuccessReport("QuoteName::","Quotename is displaying as expected","");
				       else
				    	 reporter.failureReport("QuoteName::","Quotename is not displaying as expected","");
				       SwipeUpapplication();
				       clickAdvancedHeader();
				       Swipedownapplication();
				      
				       ClickOnXsymbolunderCon();
				       String ActualTaavalue1 = getTaavalue();
				        if(ActualTaavalue1.equals(data.get("SPREADSHEETQUOTE")))
				        	reporter.SuccessReport("TAA::","Taa value is as expected : ","");
				        else
				        	reporter.failureReport("TAA::","Taa value is not as expected : ","");
				       clickDoneButton();
				       SwipeUpapplication();
				      String PONUM = getPONumber();
				      String PORELNUM =getPOrelNumber();
				      String SHIPTOATTN =getShiptoAttn();
				      String SOLDTOATTN =getSoldtoAttn();
				      if(data.get("setPoNum").equalsIgnoreCase(PONUM) && data.get("setPORel").equalsIgnoreCase(PORELNUM) && data.get("setSoldAttn").equalsIgnoreCase(SOLDTOATTN) && data.get("setShipAttn").equalsIgnoreCase(SHIPTOATTN))
				    	  reporter.SuccessReport("PO and Release number and Shipto & SoldTo Attn::","PO and Release number and Shipto & SoldTo Attn fields exits","");
				      else
				    	 reporter.failureReport("PO and Release number and Shipto & SoldTo Attn::","PO and Release number and Shipto & SoldTo Attn fields not exits","");
				      
				       filedata=  verifyDownloadedTextFile();
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
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}


			}


