package com.insight.SmartTest.Scripts.IUS;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SER01_AccountSearchTest extends HomeLib{
	
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : SER01_AccountSearch
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
		public void SER01_AccountSearch(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER01_AccountSearch",TestData_Smart,"Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				// initilizing libraries and testdata
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SER01_AccountSearch", TestData_Smart,"Search",
						intCounter);
				// Test Steps execution
				try {
					fnOpenTest();
					navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"));
					accountsearch();
					clickOnCheckoxinAccountSearch(data.get("type1"));//Contact
					textfieldsinAccountSearchpage(data.get("TextField1"),data.get("Text1"));
					searchinAccountSearchPage();
					verifyResultsofLastname(data.get("Lname"));
					clearTextfieldsinAccountSearchpage(data.get("TextField1"));
					clickOnCheckoxinAccountSearch(data.get("type1"));
					clickOnCheckoxinAccountSearch(data.get("type2"));//SoldTo
					textfieldsinAccountSearchpage(data.get("TextField2"),data.get("Text2"));//Street
					searchinAccountSearchPage();
					verifyResultsofStreet(data.get("Street"));
					clearTextfieldsinAccountSearchpage(data.get("TextField2"));
					clickOnCheckoxinAccountSearch(data.get("type2"));//SoldTo
					clickOnCheckoxinAccountSearch(data.get("type3"));//contact
					textfieldsinAccountSearchpage(data.get("TextField3"),data.get("Text3"));//Street
					textfieldsinAccountSearchpage(data.get("TextField1"),data.get("Text1"));
					searchinAccountSearchPage();
					verifyResultsofLastname(data.get("Lname"));
					verifyResultsofFirstname(data.get("Fname"));
					clearTextfieldsinAccountSearchpage(data.get("TextField3"));
					clearTextfieldsinAccountSearchpage(data.get("TextField1"));
					clickOnCheckoxinAccountSearch(data.get("type3"));//contact
					clickOnCheckoxinAccountSearch(data.get("type4"));//contact
					textfieldsinAccountSearchpage(data.get("TextField4"),data.get("Text4"));
					searchinAccountSearchPage();
					verifyResultsofEmail(data.get("Email"));
					clearTextfieldsinAccountSearchpage(data.get("TextField4"));
					clickOnCheckoxinAccountSearch(data.get("type4"));//contact
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					fnCloseTest();
				}
			}
		}


}
