package com.insight.SmartTest.Scripts.IUS;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT15_IPSContractPricingError extends HomeLib {

	loginLib loginlib = new loginLib();
	// #############################################################################################################
	// # Name of the Test : CQT15_IPSContractPricingError
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : Nov 2019
	// # DESCRIPTION :
	// functionality in the products display page.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void CQT15(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT15_IPSContractPricingError",TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT15_IPSContractPricingError", TestData_Smart, "Create_Quote", intCounter);
					fnOpenTest();
					navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
					clickOnCreateQuoteLink();
					enterSoldTo(data.get("SoldToValue"));
					// 10529929
					AddMaterialOnLineItem(data.get("MaterialID"));// D4Q72US#ABA
					clickOnCOntractIDinLineItemsList();
					selectCOntractID(data.get("contactid"), data.get("contactTabName")); //Contracts
					clickDoneButton();
					clickUpdateCosting();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					enterRepMarginPercent(data.get("RepPercentage"));
					clickUpdateCosting();
					clickSideBarSmart();
					clickonSaveasQuote();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					//Need to verify Error Message
					verifyErrorMsg(data.get("ErrorMsg"));//Item [000010] price is [2486.29], but it must be <= [2355.43]				
					clickOKinErrormsgBox();				
					clickSideBarSmart();
					Thread.sleep(3000);
					clickClosthedocument(data.get("Doctype"));
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
			ReportStatus.fnUpdateResultStatus("CQT15_IPSContractPricingError", "TC_15",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT15_IPSContractPricingError", "TC_15",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
