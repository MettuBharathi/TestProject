package com.insight.SmartTest.Scripts.IUS;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class NAV01_InvoiceHistoryLinkTest extends HomeLib {
	
	loginLib loginlib=new loginLib();
	// #############################################################################################################
	// #       Name of the Test         :  NAV01_InvoiceHistoryLinkTest
	// #       Migration Author         :  Cigniti Technologies
	// #
	// #       Date of Migration        : November 2019
	// #       DESCRIPTION              : This method is to verify InvoiceHistoryLink
	// #       Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_NAV01(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "NAV01_InvoiceHistoryLinkTest",TestData_Smart,"Search");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

					
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("NAV01_InvoiceHistoryLinkTest", TestData_Smart, "Search", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("InvoiceHistoryLink");

					
				
					//Login functionality
					navigateToApplication("SMART");
				    driver.switchTo().defaultContent();
				   
					loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"));
					
					//Home Page
					EnterSalesDocNumber(data.get("SalesDocNum"));
					clickOnSalesDocSearch();
					
					//to see history dropdown,have to close side view bar
					ClickOnsideViewBar();
					SelectoptionfromDropdown("Invoice History", "history");
					driver.switchTo().defaultContent();
					ClickOnInvoiceLink();
					CheckInvoiceIsopenedorNot();
					System.out.println("Testcase completed");

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
			//ReportStatus.fnUpdateResultStatus("NAV01_InvoiceHistoryLinkTest", "TC_NAV01", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
	    finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("NAV01_InvoiceHistoryLinkTest", "TC_NAV01", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}


	}
