package com.insight.WebTest.SEWP;

import com.insight.Lib.SewpLib;
import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.SearchLib;

import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SWP02_SEWPIPSContractTest extends SewpLib{
	        // #############################################################################################################
			// # Name of the Test :SWP02_SEWPIPSContractTest
			// # Migration Author : Cigniti Technologies
			// #
			// # Date of Migration : August 2019
			// # DESCRIPTION : This method is to perform Basic Cart operations.
			// # Parameters : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################
			

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void SWP02_SEWPIPSContract(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SWP02_SEWPIPSContract", TestDataInsight, "SEWP");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SWP02_SEWPIPSContract", TestDataInsight, "SEWP", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SEWPIPSContract");
								 
		CommonLib commonLib = new CommonLib();
			SearchLib searchLib = new SearchLib();
			CartLib cartLib=new CartLib();
		
			navigateTo(data.get("URL"));
			CreateAnAccount(data.get("TC"));
			enterBillingInfo(data.get("Organization"), data.get("Agency"), data.get("SubAgency"));
			enterLoginInfo(data.get("lastName"), data.get("PhoneNumber"), data.get("Address1"), data.get("City"),
					data.get("State"), data.get("Title"), data.get("ZipCode"));
			saveAndCreateAccount();
			searchLib.verifyContractAllDisplayed();
			searchLib.selectContract(data.get("Contract"));
			searchProduct(data.get("SEWPPart"));
			// Verify SEWP Part details
			verifyMfr(data.get("SEWPPart"));
			verifyInsight(data.get("SEWPPart"));
			clickOnAddtoCart();
			orderLib.proceedToCheckout();
			enterReportingDetailsInLineLevelInfoSection(data.get("REPORTINGFIELD_4"), data.get("REPORTINGFIELD_5"),data.get("REPORTINGFIELD_6"));
			orderLib.shippingBillPayContinueButton(); // Click continue on shipping address
			orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
			clickBillingAddressContinueButton();
			orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number"), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));
			orderLib.clickOnReviewOrderButton();
			// Place Order
			String summaryAmountInLogin = cartLib.getSummaryAmountInCart();
			orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmountInLogin);			
			String RefNumber= orderLib.getTextfromReferenceNumber().trim();
		       //Verifying order details
			orderLib.clickOrderDetailsLinkOnReceiptPage();
			searchLib.verifyAccountToolsFromSideMenuAndClick(data.get("toolsMenuName"),data.get("dropDown"));			
			clickonorderNumLinkinRecentorders(RefNumber);
			verifyPartNumberInOrderdetails(data.get("SEWPPart"));
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
			ReportStatus.fnUpdateResultStatus("SEWPIPSContract", "SWP02_SEWPIPSContract", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SEWPIPSContract", "SWP02_SEWPIPSContract", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}