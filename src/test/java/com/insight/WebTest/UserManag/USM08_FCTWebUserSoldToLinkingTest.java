package com.insight.WebTest.UserManag;

import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.Lib.UserManagementLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class USM08_FCTWebUserSoldToLinkingTest extends UserManagementLib{
	
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib = new CanadaLib();
	UserManagementLib userMgt=new UserManagementLib();
	
	    // #############################################################################################################
		// #       Name of the Test         : USM08_FCTWebUserSoldToLinking
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test FCT Web User Sold To Linking
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_USM08(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "USM08_FCTWebUserSoldToLinking", TestDataInsight, "UserManagement");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("USM08_FCTWebUserSoldToLinking", TestDataInsight, "UserManagement", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("FCTWebUserSoldToLinking");

							
							// Login to CMT
							cmtLib.loginToCMT(data.get("Header"));
							cmtLib.searchForWebGroup(data.get("WebGrp"));
							cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
							cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
							cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
							// Click on Linked Accounts
							cmtLib.clickCheckOutSettings(data.get("Linked_Accounts"));
							
							// Verify AccountName,AccountNumber,Address,AccountStatus,DefaultLogin
							String[] headers=data.get("Linked_Account_header").split(",");
							for(i=0;i<headers.length;i++){
								cmtLib.verifyLinkedAccountHeaders(headers[i]);
							}
							// Select >>  Link User to All Available Accounts
							cmtLib.selectFromLinkedAccountDD(data.get("Option1"));
							cmtLib.VerifytheCheckBoxStatus("Checked");
							
							// Select >>   Unlink User from All Accounts
							cmtLib.selectFromLinkedAccountDD(data.get("Option2"));
							cmtLib.VerifytheCheckBoxStatus("UnChecked");
							// Select 100 per Page AND VERIFY
							userMgt.verifyPagination(data.get("Ending_Page"));
							// Click Next
							cmtLib.clickOnNextPagination();
							
							//Verify page is changed >> 13
							int pageNum1=Integer.parseInt(data.get("Paging_Num1"));
							
						  // cmtLib.verifyPagenavigationOnLinkedAccountsPage(pageNum1);
							
							// Click on 2nd sold to
							cmtLib.clickLinkedAccountCheckBox(data.get("Account_Number1"));
							cmtLib.clickOnDefaultAccountLoginByIndex(data.get("Account_Number1"));
							// search by ABE
							cmtLib.enterLinkedAccountSearch(data.get("Account_Search1"));
							
							List<String> accountName=cmtLib.getAccountNameInLinkedAccounts();
							cmtLib.verifyAccountNameStartsWith(accountName, data.get("Account_Search1"));
							
							// Select >>  Link User to All Available Accounts
							cmtLib.selectFromLinkedAccountDD(data.get("Option1"));
							cmtLib.VerifytheCheckBoxStatus("Checked");
							// select last default login >> 10
							cmtLib.clickOnDefaultAccountLoginByIndex(data.get("Account_Number2"));
							cmtLib.clickUpdateButtonOnLinkedAccountsScreen();
							cmtLib.removeDefaultLink();
							cmtLib.verifyNoDefaultAccountISPresent();
							
							// search by AB
							cmtLib.enterLinkedAccountSearch(data.get("Account_Search2"));
							
							List<String> accountName2=cmtLib.getAccountNameInLinkedAccounts();
							cmtLib.verifyAccountNameStartsWith(accountName2, data.get("Account_Search2"));
							// Clear the Results
							cmtLib.clearSearch();
							// select page - 3
							userMgt.selectPagination(data.get("Paging_Num2"));
							int pageNum2=Integer.parseInt(data.get("Paging_Num2"));
							//Verify page is changed
							cmtLib.verifyPagenavigationOnLinkedAccountsPage(pageNum2);
							
							// Select >>  Link User to All Available Accounts
							cmtLib.selectFromLinkedAccountDD(data.get("Option1"));
							
							// Select >>  Unlink User from All Accounts
							cmtLib.selectFromLinkedAccountDD(data.get("Option2"));
							cmtLib.VerifytheCheckBoxStatus("UnChecked");
							// Select the 4th Sold to
							cmtLib.clickLinkedAccountCheckBox(data.get("Account_Number3"));
							cmtLib.clickOnDefaultAccountLoginByIndex(data.get("Account_Number3"));
							// Click on " |< " paging
							cmtLib.clickOnLeftpaging();
							// select page - 2
							userMgt.selectPagination(data.get("Paging_Num3"));
							//Verify page is changed
							int pageNum3=Integer.parseInt(data.get("Paging_Num3"));
							cmtLib.verifyPagenavigationOnLinkedAccountsPage(pageNum3);
							cmtLib.VerifytheCheckBoxStatus("UnChecked");
							
							// Select >>  Link User to All Available Accounts
							cmtLib.selectFromLinkedAccountDD(data.get("Option1"));
							cmtLib.verifyAccountNameStartsWith(accountName2, data.get("Account_Search2"));
							
							// Click on 2nd sold to
							cmtLib.clickOnDefaultAccountLoginByIndex(data.get("Account_Number1"));
							cmtLib.clickUpdateButtonOnLinkedAccountsScreen();
							// Verify default account
							cmtLib.verifyDefaultAccountAddress();
							
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
					ReportStatus.fnUpdateResultStatus("FCTWebUserSoldToLinking", "TC_USM08", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
	            finally {
	            	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("FCTWebUserSoldToLinking", "TC_USM08", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}

}
