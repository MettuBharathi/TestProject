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

public class CQT30_IPSCPSSNonTaxTest extends HomeLib {
	
	loginLib loginlib=new loginLib();
	// #############################################################################################################
			// #       Name of the Test         :  CQT30_IPSCPSSNonTax
			// #       Migration Author         :  Cigniti Technologies
			// #
			// #       Date of Migration        : November 2019
			// #       DESCRIPTION              : This method is to verify IPSCPSSNonTax
			// #       Parameters               : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT30(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT30_IPSCPSSNonTax_Action1_Script",TestData_Smart,"Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

		try {
				counter = intCounter;
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT30_IPSCPSSNonTax_Action1_Script", TestData_Smart, "Create_Quote", intCounter);
				TestEngineWeb.reporter.initTestCaseDescription("IPSCiscoIDRefreshIcon");
				LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToAcct"));
		        clickAdvancedHeader();
		        SelectSpecialOrderType(data.get("SpecialOrderType"));
		        clickAdvancedHeader();
		        AddLineItems("material",data.get("Material"),0);
		        AddLineItems("material",data.get("Material"),1);
		        ClickOnXsymbolunderCon();
		        SelectContractId(2);
		        clickDoneButton(); 
		        clickUpdateCosting();	
		       
		        AddfrtChargeInLneitems("0.00", 0);
		        AddfrtChargeInLneitems("0.00", 1);
		        clickonSaveasQuote();
		       
		        GetQuoteNumber();
		       ClickOnDisplayMode();
		      
		       Date today = new Date();
		       String date = today.toString().replace(":", "");
		       SwipeUpapplication();
		       enterPONumber("CPSS"+date);
		       ClickOnConverToOrder();
		       
		       clickonSaveasOrder();
		       
		       clickOnSaveorderwithoutAttchment();
		       
		       getOrderNum();
		       clickAdvancedHeader();
		       getSpecialOrderType(data.get("SpecialOrderType"));
		       clickAdvancedHeader();
		       String MOvalue = getMaterialsOrderrevenue();
		       String TaxOvalue = getTaxOrderrevenue();
		       String TotalOvalue = getTotalOrderrevenue();
		       if(TaxOvalue.equals("0.00"))
		    	   System.out.println("Tax order revenue: "+TaxOvalue);
		       else
		    	   System.out.println("Tax order revenue  is not 0.00 "); 
		       if(MOvalue.equals(TotalOvalue))
		    	   System.out.println("Tax not included in Total");
		       else
		    	   System.out.println("Tax included in total");
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
		//ReportStatus.fnUpdateResultStatus("CQT30_IPSCPSSNonTax_Action1_Script", "TC_CQT30", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}
    finally {
		ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("CQT30_IPSCPSSNonTax_Action1_Script", "TC_CQT30", ReportStatus.strMethodName, counter, browser);
		//fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}


}


