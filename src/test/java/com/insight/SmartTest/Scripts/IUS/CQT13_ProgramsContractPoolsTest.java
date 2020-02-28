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

public class CQT13_ProgramsContractPoolsTest  extends HomeLib {
	
	loginLib loginlib = new loginLib();

	// #############################################################################################################
	// # Name of the Test : CQT13_ProgramsContractPools
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : Nov 2019
	// # DESCRIPTION : Purpose of this test method is to verify the compare
	// functionality in the products display page.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void CQT13_ProgramsContractPools(int StartRow, String EndRow, boolean nextTestJoin)
			throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT13_ProgramsContractPools",
					TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo(
							"CQT13_ProgramsContractPools", TestData_Smart, "Create_Quote",
							intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("ProgramsContractPools");
					navigateToApplication("SMART");
					Thread.sleep(4000);
					loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
					Thread.sleep(4000);
					clickCreateQuoteButton();
					Thread.sleep(4000);
					enterSoldTo(data.get("SoldToAcct"));
					Thread.sleep(4000);
					selectsalesOrginPopUp(data.get("SalesOrg"));
					clickAdvancedHeader();
					clickAdvancedHeaderTab(data.get("Tab1"));//programs Tab
					Productsearch(data.get("ContractId"));
					verifyEnrollmentDropDown();
					selectOptionFromPoolsDropDown(data.get("Option"));
					selectQuotePrograme();
					clickOnLICandMaintCheckBox();
					clickOnSaveButtonOnOptionsAndLevelsPopup();
					clickOnOptionsAndLevelsButton();
					clickOnLICandMaintCheckBox();
					clickOnSaveButtonOnOptionsAndLevelsPopup();
					enrollmentDropDown(data.get("enrollment"));
					selectOptionFromPoolsDropDown(data.get("Option1"));
					verifyQuoteProgramDropDown();
					String Value=getQuoteProgrameValue();
					verifyQuoteProgramDropDownValue(Value);
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
			// ReportStatus.fnUpdateResultStatus("IPSContractPricingRoundingWithMultipleQtys",
			// "CQT_45", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SoftwareContractParts", "CQT_01",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
