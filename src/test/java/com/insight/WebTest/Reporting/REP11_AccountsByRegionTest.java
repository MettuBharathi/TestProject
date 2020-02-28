package com.insight.WebTest.Reporting;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ReportingLib;
import com.insight.Lib.SearchLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class REP11_AccountsByRegionTest  extends ReportingLib {

	// #############################################################################################################
			// # Name of the Test :TC_REP11AccountsByRegion
			// # Migration Author : Cigniti Technologies
			// #
			// # Date of Migration : OCT 2019
			// # DESCRIPTION : This method is to perform Quote History search with date
			// operations.
			// # Parameters : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void TC_REP11(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
				int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_REP11AccountsByRegion", TestData, "Reporting");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_REP11AccountsByRegion", TestData,
									"Reporting", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("AccountsByRegion");
				
				
				
	 
							CMTLib cmtLib = new CMTLib();
							SearchLib searchLib = new SearchLib();
							OrderLib orderLib=new OrderLib();
							CanadaLib canadaLib=new CanadaLib();
							CartLib cartLib=new CartLib();
							MarriottIntlCorpLib mic=new MarriottIntlCorpLib();
							InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
							MarriottIntlCorpLib marriottIntlCorpLib=new MarriottIntlCorpLib();
							CommonLib commonLib = new CommonLib();
							cmtLib.loginToCMT(data.get("Header"));
							cmtLib.searchForWebGroup( data.get("WebGrp"));						
							cmtLib.manageUsers();
							clickOnReportingAdminLogin();
							VerifyWelcomePage(data.get("Tools_Menu"),data.get("Tools_Menu_DD"));
						
							clickOnReportOptions(data.get("ReportOption"));
							canadaLib.verifyReportsPage();
							canadaLib.verifySelectReport(data.get("SelectReport"));
							canadaLib.clickOnAccountSelections(data.get("AccountSelectionOpt"));
							selctAccountsByRegion(data.get("Region1"),data.get("Region2"));
							selctAccountsByList(data.get("RegionList1"),data.get("RegionList2"),data.get("RegionList3"));
							clickUpdateFilterOption();
							verifyHirearchyTree();
							commonLib.clickLogOutLink(data.get("Logout_Header"));
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
					ReportStatus.fnUpdateResultStatus("AccountsByRegion", "TC_REP11", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
		        finally {
		        	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("AccountsByRegion", "TC_REP11", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}



	}
