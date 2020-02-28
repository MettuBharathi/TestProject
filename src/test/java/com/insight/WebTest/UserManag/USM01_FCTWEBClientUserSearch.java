package com.insight.WebTest.UserManag;

import java.util.Hashtable;

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
import com.insight.Lib.UserManagementLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class USM01_FCTWEBClientUserSearch extends UserManagementLib {

	// #############################################################################################################
			// # Name of the Test :USM01_FCTWEBClientUserSearch
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
			public void TC_USM01FCTWEBClientUserSearch(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
				int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_USM01FCTWEBClientUserSearch", TestDataInsight,
							"UserManagement");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_USM01FCTWEBClientUserSearch",TestDataInsight, "UserManagement", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("USM01FCTWEBClientUserSearch");
							reporter.SuccessReport("Iteration Number : ",
									"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
											+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
									"");
	 
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
							verifyNoUsersErrMsg(data.get("InvalidUser"));							
							verifyWithValidUserName(data.get("User"));
							verifyPagination(data.get("Pages1"));							
							verifyPagination(data.get("Pages3"));
							verifyPagination(data.get("Pages4"));
							verifyEmailUserName(data.get("EmailSearch"));
							verifyWithUserId(data.get("UserId"));
							clickOnExportToExcel();
							verifyDownloadedUsersExcelFile(data.get("FileName"));						
							verifyUserStatus();
							selectUserStatus(data.get("UserStatus1"));
							selectUsertype(data.get("Usertype1"));
							selectUsertype(data.get("Usertype2"));
							selectUsertypeUserStatus(data.get("Usertype3"),data.get("UserStatus2"));							
							selectWebGroup(data.get("webGroupRelation1"));
							cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
							cmtLib.searchForWebGroup( data.get("WebGrp1"));	
							cmtLib.manageUsers();
							selectUsertype(data.get("changedUsertype1"));
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
					//ReportStatus.fnUpdateResultStatus("USM01FCTWEBClientUserSearch", "TC_USM01FCTWEBClientUserSearch", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
	            finally {
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("USM01FCTWEBClientUserSearch", "TC_USM01FCTWEBClientUserSearch", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}
}

