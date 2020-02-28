package com.insight.SmartTest.Scripts.IUS;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT34_IPSBearNonTaxTest extends HomeLib{
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : CQT34_IPSBearNonTax
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
		public void CQT34_IPSBearNonTax(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT34_IPSBearNonTax", TestData_Smart, "Create_Quote");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT34_IPSBearNonTax", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSBearTax");	
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"), data.get("Password"),
							data.get("SoldToAcct"), data.get("SalesOrg"));
					selectPaymentTerms(data.get("PaymentTerms"));
					Addmaterail(data.get("Material1"));
					Addmaterail(data.get("Material1"));
					Addmaterail(data.get("Material1"));
					clickonConXSystem(data.get("ItemNum1"));// 000010
					clickOnContractId(data.get("contactid"));
					enterTestinReportingField0(data.get("Reprotingfield0text"));
					enterTestinReportingField1(data.get("Reprotingfield1text"));
					enterTestinReportingField4(data.get("Reprotingfield4text"));
				    enterTestinReportingField3(data.get("Reprotingfield3text"));
					clickDoneButton();
					clickAdvancedHeader();
					// enter fields
					SelectSpecialOrderType(data.get("BEAR"));// BEAR
					enterQuotenameinAdvancedHeader(data.get("Quotename"));
					clickAdvancedHeaderTab(data.get("Tab"));// Partners
					enterErateLocationinpartnerstabAdvancedHeader(data.get("location"));//E_site Location Test
					clickonSaveasQuote();
					Thread.sleep(5000);
					getOrderNum();
					enterCancelButtonInPoupHdr();
					String QuoteNum = GetQuoteNumber();
					ClickOnDisplayMode();
					typePONumber(data.get("PONumber"));// 12345
					okPopUp();
					ClickOnConverToOrder();
					Thread.sleep(3000);
					clickonSaveasOrder();
					clickSaveorderwithoutAttachment();
					getOrderNum();
					clickCancel();
					// total tax
					VerifySummaryDataisPresentequaltoZero(data.get("Tax"));
					verifySpecialOrdertypeisdisabled();
					VerifySummaryDataisPresentequaltoZero(data.get("Quotename"));
					clickAdvancedHeaderTab(data.get("Tab"));// Partners
					verifyErateLocationswithGivenTextisPresent(data.get("location"));
					String QuoteNum1 = GetQuoteNumber();
					clickSideBarSmart();
					clickClosthedocument(QuoteNum1);
					clickYesButtontocloseDocument();				
					System.out.println("Test completed");
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
				//ReportStatus.fnUpdateResultStatus("IPSBearNonTax", "CQT_34", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("IPSBearNonTax", "CQT_34", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}


	}

