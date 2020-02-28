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
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class ODH01_OrderHistoryGenericSearchTest extends OrderHistoryLib{
	// #############################################################################################################
			// # Name of the Test :ODH01_OrderHistoryGenericSearch
			// # Migration Author : Cigniti Technologies
			// #
			// # Date of Migration : OCT 2019
			// # DESCRIPTION : This method is to perform Basic Cart operations.
			// # Parameters : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void Tc_ODH01(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODH01_OrderHistoryGenericSearch", TestDataInsight,
							"Order_History");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("ODH01_OrderHistoryGenericSearch",
									TestDataInsight, "Order_History", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription(
									"OrderHistoryGenericSearch");
							reporter.SuccessReport("Iteration Number : ",
									"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
											+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
											+ "  **************",
									"");

							CMTLib cmtLib = new CMTLib();
							CanadaLib canadaLib = new CanadaLib();
							InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
							CommonLib commonLib = new CommonLib();
							cmtLib.clickLoginLink(data.get("Header"));
							cmtLib.handleWelcomeToInsightBetaPopUp();
							selectToolsDropDownInHomepage();
							verifyTrackMyOrderPageOpened();
							//order number 
							genericSearch(data.get("Select_Order_Number"),data.get("Order_Number"),data.get("Select_Account_Number"),data.get("Account_Number"));
							genericSearch(data.get("Select_Order_Number"),data.get("Order_Number"),data.get("Select_Shipping_Postal_Code"),data.get("Shipping_Postal_Code"));
							genericSearch(data.get("Select_Order_Number"),data.get("Order_Number"),data.get("Select_CONTACT_NAME"),data.get("CONTACT_NAME"));
							genericSearch(data.get("Select_Order_Number"),data.get("Order_Number"),data.get("Select_Email"),data.get("Email"));
							//Invoice number
							genericSearch(data.get("Select_Invoice_Number"),data.get("Invoice_Number"),data.get("Select_Account_Number"),data.get("Account_Number"));
							genericSearch(data.get("Select_Invoice_Number"),data.get("Invoice_Number"),data.get("Select_Shipping_Postal_Code"),data.get("Shipping_Postal_Code"));
							genericSearch(data.get("Select_Invoice_Number"),data.get("Invoice_Number"),data.get("Select_CONTACT_NAME"),data.get("CONTACT_NAME"));
							genericSearch(data.get("Select_Invoice_Number"),data.get("Invoice_Number"),data.get("Select_Email"),data.get("Email"));
							//Reference number
							genericSearch(data.get("Select_Reference_Number"),data.get("Reference_Number"),data.get("Select_Account_Number"),data.get("Account_Number"));
							genericSearch(data.get("Select_Reference_Number"),data.get("Reference_Number"),data.get("Select_Shipping_Postal_Code"),data.get("Shipping_Postal_Code"));
							genericSearch(data.get("Select_Reference_Number"),data.get("Reference_Number"),data.get("Select_CONTACT_NAME"),data.get("CONTACT_NAME"));
							genericSearch(data.get("Select_Reference_Number"),data.get("Reference_Number"),data.get("Select_Email"),data.get("Email"));
							
							//Purchase number
							genericSearch(data.get("Select_Purchase_Number"),data.get("Purchase_Number"),data.get("Select_Account_Number"),data.get("Account_Number"));
							genericSearch(data.get("Select_Purchase_Number"),data.get("Purchase_Number"),data.get("Select_Shipping_Postal_Code"),data.get("Shipping_Postal_Code"));
							genericSearch(data.get("Select_Purchase_Number"),data.get("Purchase_Number"),data.get("Select_CONTACT_NAME"),data.get("CONTACT_NAME"));
							genericSearch(data.get("Select_Purchase_Number"),data.get("Purchase_Number"),data.get("Select_Email"),data.get("Email"));
							
						} catch (Exception e) {
							ReportStatus.blnStatus = false;
							gErrorMessage = e.getMessage();
							System.out.println(e.getMessage());
							gTestStatus = false;
						}
						ReportControl.fnEnableJoin();
						ReportStatus.fnUpdateResultStatus("OrderHistoryGenericSearch", "ODH01", ReportStatus.strMethodName,
								intCounter, browser);
						fnCloseTest();
					}
				} catch (Exception e) {
					e.printStackTrace();
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
					ReportStatus.fnUpdateResultStatus("OrderHistoryGenericSearch", "ODH01", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}

				ReportControl.fnNextTestJoin(nextTestJoin);
			}

}
