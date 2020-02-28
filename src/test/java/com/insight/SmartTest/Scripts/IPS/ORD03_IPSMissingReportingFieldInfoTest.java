package com.insight.SmartTest.Scripts.IPS;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class ORD03_IPSMissingReportingFieldInfoTest extends HomeLib {
	loginLib loginlib = new loginLib();

	// #############################################################################################################
	// # Name of the Test : ORD03_IPSMissingReportingFieldInfo
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
	public void ORD03_IPSMissingReportingFieldInfo(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ORD03_IPSMissingReportingFieldInfo", TestData_Smart,
				"Create_Order");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("ORD03_IPSMissingReportingFieldInfo",
					TestData_Smart, "Create_Order", intCounter);
			// Test Steps execution
			try {
				fnOpenTest();
				navigateToApplication("SMART");
				loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
				clickCreateQuoteButton();
				enterSoldTo(data.get("SoldToAcct"));
				Addmaterail(data.get("Material"));
				clickMaterail();
				clickonConXSystem(data.get("ItemNum"));// 000010
				verifyContarct(data.get("SW_Contract"));
				verifycontractwithreporingfield(data.get("Contract"));
				clickDoneButton();
				clickUpdateCosting();
				clickSideBarSmart();
				clickonSaveasOrder();
				clickSaveorderwithoutAttachment();
				verifyErrorMsg(data.get("Erromsg"));
				clickOKinErrormsgBox();
				clickClosthedocument(data.get("Doctype"));
				clickYesButtontocloseDocument();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				fnCloseTest();
			}
		}
	}

}
