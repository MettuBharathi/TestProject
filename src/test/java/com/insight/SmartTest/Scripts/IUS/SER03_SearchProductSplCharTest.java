package com.insight.SmartTest.Scripts.IUS;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SER03_SearchProductSplCharTest  extends HomeLib{
	
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : SER03_SearchProductSplChar
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
		public void SER03_SearchProductSplChar(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER03_SearchProductSplChar",TestData_Smart,"Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				// initilizing libraries and testdata
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SER03_SearchProductSplChar", TestData_Smart,"Search",
						intCounter);
				// Test Steps execution
				try {
					fnOpenTest();
					LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToAcct"));
					selectsalesOrginPopUp(data.get("SalesOrg"));
					clickOnProductSearchButton();
					Thread.sleep(3000);
					enterSearchValue(data.get("KeyWord1"));
					clickOnSearchButtonInSearchWindow();
					Thread.sleep(2000);
					verifyResultsofMaterailIdofKeyWordSearch(data.get("KeyWord1"));
					clickOnTabOfMaterailIDProductSearchPopUp(data.get("Tab1"));//Accessories
					verifyICScoloumninAccesoriesTab();
					clickOnTabOfMaterailIDProductSearchPopUp(data.get("Tab2"));//Tech Specs
					clickEmailTechSpecsButton();
					sendEamilinEmailTechSpecsPopUp(data.get("Email"));//qatester01@insight.com
					clickSendEmailButton();
					Thread.sleep(3000);
					emailSentSuccessfull();
					Thread.sleep(3000);
					clickOnProductSearchButton();
					clickonHomeButtonInProductSearchPopup();
					enterSearchValue(data.get("KeyWord2"));
					clickOnSearchButtonInSearchWindow();
					Thread.sleep(2000);
					verifyResultsofMaterailIdofKeyWordSearch(data.get("KeyWord2"));
					clickCloseButtonProductSearch();
					Thread.sleep(3000);
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
