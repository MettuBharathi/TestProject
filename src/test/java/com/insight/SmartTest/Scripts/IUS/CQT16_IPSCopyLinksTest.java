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

public class CQT16_IPSCopyLinksTest extends HomeLib {
	
	loginLib loginlib=new loginLib();

	// #############################################################################################################
	// #       Name of the Test         :  CQT16_IPSCopyLinks_Action1_Script
	// #       Migration Author         :  Cigniti Technologies
	// #
	// #       Date of Migration        : November 2019
	// #       DESCRIPTION              : This method is to verify IPSCopyLinks
	// #       Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT16(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT16_IPSCopyLinks_Action1_Script",TestData_Smart,"Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

					
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT16_IPSCopyLinks_Action1_Script", TestData_Smart, "Create_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("IPSCopyLinks");

						
				navigateToApplication("SMART");
				loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"));
				String[] array = {data.get("Keyword1"),data.get("Keyword2"),data.get("Keyword3"),data.get("Keyword4")};
				clickOnQuoteandAddlineitemsfromProductSearch(array,data.get("SoldToAcct"));
			/*	clickOnCreateQuoteLink();
				ClickOnsideViewBar();
		        enterSoldToValue("10529929");
		        clickOnSoldToSearchIcon();

		        AddlineItemFromProductSearch("RC465AA#ABA");
		        AddlineItemFromProductSearch("CON-ISV1-VSSTD1A");
		        AddlineItemFromProductSearch("B2M-00012");
		        AddlineItemFromProductSearch("D4Q72US#ABA");*/
		        ClickOnXsymbolunderCon();
		        driver.switchTo().defaultContent();
		        ClickOnCopyContracttoallLines();
		        driver.switchTo().defaultContent();
		        SearchButton();
		        
		        SelectContractId(2);
		        EnterDatainDivercityPartner(data.get("DivercityPartner"));
		        SearchButtonOfDivercityPartner();
		        ClickOndivercityPartnerResult();
		        ClickOnCopyContracttoallLines();
		        SearchButton();
		        EnterUSCOMMember("U.S. COMM MEMBER ID #:",data.get("USCOMMmember"));
		        EnterUSCOMMember("REPORTING FIELD 4:",data.get("REPORTINGFIELD4"));
		        EnterUSCOMMember("REPORTING FIELD 5:",data.get("REPORTINGFIELD5"));
		        clickonCopyreportingfieldstoallthelines();
		        SearchButton();
		        clickDoneButton();
		        clickLineItemHeaderTab("Update Costing");
		        loadingSymbol();
		        ClickOnSaveAsQuoteButton();
		        loadingSymbol();
		        SelectAdherencetoflooroption("Client Satisfaction");
		        Adherencetofloorreason(data.get("AdherenceFloorReason"));
		        ClickOncancelbutton();
		        ClickOnXsymbolunderCon();
		        validatetheLineitemfiledsaftersaving();
		        clickDoneButton();
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
		//ReportStatus.fnUpdateResultStatus("CQT16_IPSCopyLinks_Action1_Script", "TC_CQT16", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}
    finally {
		ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("IPSCopyLinks", "CQT_16", ReportStatus.strMethodName, counter, browser);

		fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}



}

