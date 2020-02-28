package com.insight.WebTest.OrderHistory;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.OrderHistoryLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class ODH11_OrderHistoryAccountSelectionTest extends OrderHistoryLib {
	// #############################################################################################################
		// # Name of the Test :ODH11_OrderHistoryAccountSelection
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION 
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
				@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
				@Test
				public void Tc_ODH11(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
					try {
						int intStartRow = StartRow;
						int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODH11_OrderHistoryAccountSelection", TestDataInsight, "Order_History");
						for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
							try {
								fnOpenTest();
								ReportStatus.fnDefaultReportStatus();
								ReportControl.intRowCount = intCounter;
								Hashtable<String, String> data = TestUtil.getDataByRowNo("ODH11_OrderHistoryAccountSelection", TestDataInsight, "Order_History", intCounter);
								this.reporter.initTestCaseDescription(
										"OrderHistoryAccountSelection");

								/*reporter.SuccessReport("Iteration Number : ", "**************Iteration Number::  " + intCounter
										+ " To validate:: Favorites_Logged In User_Verify user is able to Add products to favorites_Remove Favorites in PLP/PDP **************");*/

								CMTLib cmtLib = new CMTLib();
								CanadaLib canadaLib = new CanadaLib();
								CommonLib commonLib = new CommonLib();
								InvoiceHistoryLib invoiceHistoryLib=new InvoiceHistoryLib();
								cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
										data.get("LnameEmailUname"), data.get("ContactName"));
								// ######## GGP Level ################################
								cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
								cmtLib.permissionFromDD(data.get("Set_Permission"), data.get("Permission_Dropdown_Option"));
								cmtLib.clickOnloginAs();
								switchToChildWindow();
								cmtLib.handleWelcomeToInsightBetaPopUp();
								cmtLib.loginVerification(data.get("ContactName"));
								invoiceHistoryLib.closeAccountTools();
								canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
										data.get("Tools_Menu_DD"));
								verifyOrderHistoryPage();
								clickOnAdvancedSearch();
								verifySelectedOptionForAccountSelection(data.get("Default_Selected_Option"));
								verifyOptionsForAccountSelectionDD(data.get("Dropdown_Options"));
								// ######## Select Account I am logged ################################
								selectAccountSelectionDropdown(data.get("Default_Selected_Option"));
								startDateCalender(data.get("Date"));
								clickAdvancedSearchButton();
								spinnerImageODH();
								scrollToBottomWithCordinate("300");
								verifySearchResultsAreDisplayed();
								selectDisplayPerPage(data.get("Results_Per_Page"));
								spinnerImageODH();
								verifySearchResultsMoreThanFive();
								verifyAccountName(data.get("ContactName"));
								// ######## Select Accounts under the RP ################################
								selectAccountSelectionDropdown(data.get("Reporting_Parent_Option"));
								clickAdvancedSearchButton();
								spinnerImageODH();
								scrollToBottomWithCordinate("300");
								verifySearchResultsAreDisplayed();
								selectDisplayPerPage(data.get("Results_Per_Page"));
								spinnerImageODH();
								verifySearchResultsMoreThanFive();
								verifyAccountName(data.get("ContactName"));
								// ######## Select Accounts under the GP ################################
								selectAccountSelectionDropdown(data.get("Grand_Parent_Option"));
								clickAdvancedSearchButton();
								spinnerImageODH();
								scrollToBottomWithCordinate("300");
								verifySearchResultsAreDisplayed();
								selectDisplayPerPage(data.get("Results_Per_Page"));
								spinnerImageODH();
								verifySearchResultsMoreThanFive();
								verifyAccountName(data.get("ContactName"));
								// ######## Select Accounts under the GGP ################################
								selectAccountSelectionDropdown(data.get("Great_Grand_Parent_Option"));
								clickAdvancedSearchButton();
								spinnerImageODH();
								scrollToBottomWithCordinate("300");
								verifySearchResultsAreDisplayed();
								selectDisplayPerPage(data.get("Results_Per_Page"));
								spinnerImageODH();
								verifySearchResultsMoreThanFive();
								verifyAccountName(data.get("ContactName"));
								// ######## Select Accounts by region ################################
								selectAccountSelectionDropdown(data.get("Accounts_By_Region_Option"));
								verifySelectedOperationCenter(data.get("Accounts_Insight_APAC"));
								verifySelectedOperationCenter(data.get("Accounts_NA"));
								clickSelectedOperationCenter(data.get("Accounts_Insight_APAC"));
								scrollUp();
								startDateCalender(data.get("Date1"));
								clickAdvancedSearchButton();
								spinnerImageODH();
								scrollToBottomWithCordinate("300");
								verifySearchResultsAreDisplayed();
								verifyCurrencyForAPAC();
								// ######## Validate results for only Australia cities return ################################
								clickInsightAustralia();
								clickAdvancedSearchButton();
								spinnerImageODH();
								scrollToBottomWithCordinate("300");
								verifySearchResultsAreDisplayed();
								verifyAustraliaCurrency();
								commonLib.clickLogOutLink(data.get("Logout_Header"));
								System.out.println("Test completed");
							} catch (Exception e) {
								ReportStatus.blnStatus = false;
								gErrorMessage = e.getMessage();
								System.out.println(e.getMessage());
								gTestStatus = false;
							}
							ReportControl.fnEnableJoin();
							ReportStatus.fnUpdateResultStatus("OrderHistoryAccountSelection", "ODH11", ReportStatus.strMethodName, intCounter, browser);
							fnCloseTest();
						}
					} catch (Exception e) {
						e.printStackTrace();
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
						ReportStatus.fnUpdateResultStatus("OrderHistoryAccountSelection", "ODH11", ReportStatus.strMethodName, 1, browser);
						throw new RuntimeException(e);
					}

					ReportControl.fnNextTestJoin(nextTestJoin);
				}

}
