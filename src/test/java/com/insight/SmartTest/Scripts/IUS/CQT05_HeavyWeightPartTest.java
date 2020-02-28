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

public class CQT05_HeavyWeightPartTest  extends HomeLib {
	
	loginLib loginlib=new loginLib();

	// #############################################################################################################
	// #       Name of the Test         :  CQT05_HeavyWeightPart
	// #       Migration Author         :  Cigniti Technologies
	// #
	// #       Date of Migration        : November 2019
	// #       DESCRIPTION              : This method is to verify HeavyWeightPart
	// #       Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT05(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT05_HeavyWeightPart",TestData_Smart,"Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				counter = intCounter;
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT05_HeavyWeightPart", TestData_Smart, "Create_Quote", intCounter);
				TestEngineWeb.reporter.initTestCaseDescription("HeavyWeightPart");
				MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"),data.get("Password"),data.get("SoldToAcct"),data.get("SalesOrg"));
				clickOnProductSearchButton();
				selectoptionfromManufacturer(data.get("manufacturer"));
				EnterkeywordSearch(data.get("Keyword search"));
		        SearchButton();
		        verifyResultsofMaterailIdofKeyWordSearch(data.get("materialid"));
		        clickOnAddToOrderButton();
		        closebuttonInProductSearch();
		        float displayedtotalweight= TotalWeight();
		        AddQuantity(data.get("Quantity"),data.get("LineItem"));
				clickUpdateCosting();
				float displayedtotalweightafteraddingheavyweight= TotalWeight();
				totalweightwithaddedquantity(displayedtotalweight,displayedtotalweightafteraddingheavyweight,data.get("Quantity"));
				clickonSaveasQuote();
				enterCancelButtonInPoupHdr();
				ClickOnDisplayMode();
				clickAdvancedHeader();
				clickAdvancedHeaderTab(data.get("Tab1"));//Freight
				selectCarrier(data.get("ShippingCarrier"));//CR_CEVA
				selectShippingOptions(data.get("Shipping_Conditions"));//10 Next day
				clickUpdateCosting();
				clickonSaveasQuote();
				enterCancelButtonInPoupHdr();
				String QuoteNum= getSaveQuoteNumber();
				clickSideBarSmart();
				clickClosthedocument(QuoteNum);
				clickYesButtontocloseDocument();
		        System.out.println("testcase completed");
			}  catch (Exception e) {
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
		//ReportStatus.fnUpdateResultStatus("CQT05_HeavyWeightPart", "TC_CQT05", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}
    finally {
		ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("CQT05_HeavyWeightPart", "TC_CQT05", ReportStatus.strMethodName, counter, browser);
		fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}



}

