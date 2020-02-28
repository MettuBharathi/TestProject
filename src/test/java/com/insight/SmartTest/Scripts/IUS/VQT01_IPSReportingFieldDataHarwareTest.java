package com.insight.SmartTest.Scripts.IUS;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class VQT01_IPSReportingFieldDataHarwareTest extends HomeLib{

	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : VQT01_IPSReportingFieldDataHarware
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
		public void VQT01_IPSReportingFieldDataHarware(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "VQT01_IPSReportingFieldDataHarware",TestData_Smart,"View_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				// initilizing libraries and testdata
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("VQT01_IPSReportingFieldDataHarware", TestData_Smart,"View_Quote",
						intCounter);
				// Test Steps execution
				try {
					fnOpenTest();
					navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"));
					searchSalesDocNum(data.get("SalesDocNum"));//0220701229
					verifyResultsofmaterail();
					clickonConXSystem(data.get("ItemNum"));//000010
					VerifyDiversityPartner();
					VerifyCopyContract();
					VerifyCopyReportingfield();
					clickDoneButton();
					//Change Mode
					clickChangeModeIcon();
					clickDoneButton();
					clickonConXSystem(data.get("ItemNum"));//000010
					VerifyDiversityPartner();
					VerifyCopyContract();
					VerifyCopyReportingfield();
					clickDoneButton();
					clickChangeModeIcon();
					clickDoneButton();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					fnCloseTest();
				}
			}
		}


}
