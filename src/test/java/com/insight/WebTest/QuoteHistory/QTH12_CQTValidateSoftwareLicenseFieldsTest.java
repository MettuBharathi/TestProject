package com.insight.WebTest.QuoteHistory;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.QuoteHistoryLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class QTH12_CQTValidateSoftwareLicenseFieldsTest extends QuoteHistoryLib {
	
	CMTLib cmtLib = new CMTLib();
	CanadaLib canadaLib = new CanadaLib();
	InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
	CommonLib commonLib = new CommonLib();
	ShipBillPayLib shipbLib = new ShipBillPayLib();
	CartLib cartLib = new CartLib();
	OrderLib orderLib = new OrderLib();
	QuoteHistoryLib quoteHistoryLib=new QuoteHistoryLib();
	HomeLib Homelib=new HomeLib();
	loginLib loginlib=new loginLib();
	
	// #############################################################################################################
	// # Name of the Test :QTH12_CQTValidateSoftwareLicenseFields
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
	public void TC_QTH12(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		
		int counter = 0;
		try {
								
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "QTH12_CQTValidateSoftwareLicenseFields", TestDataInsight, "Quote_History");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("QTH12_CQTValidateSoftwareLicenseFields", TestDataInsight, "Quote_History", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("CQTValidateSoftwareLicenseFields");

	

					ShipBillPayLib sbpLib=new ShipBillPayLib();
				
					navigateToApplication("SMART");
					Thread.sleep(4000);
					loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"));//QTPSVC,!OffShore!​
					Thread.sleep(4000);
					Homelib.EnterSalesDocNumber(data.get("SalesDoc"));//0219334395
					Homelib.clickOnSalesDocSearch();
					Homelib.clickSalesDocDropdown(data.get("DDOption"));//Copy to Quote
					Thread.sleep(4000);
					Homelib.clickYesButtontocloseDocument();
					Thread.sleep(4000);
					Homelib.clickSideBarSmart();
					Homelib.clickonSaveasQuote();
					Homelib.enterCancelButtonInPoupHdr();
					Thread.sleep(4000);
					String QuoteNum= Homelib.getSaveQuoteNumber();
					Thread.sleep(4000);
					Homelib.clickSideBarSmart();
					Homelib.clickClosthedocument(QuoteNum);
					Homelib.clickYesButtontocloseDocument();
					Thread.sleep(4000);
					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					// Enable Quotes Check Box
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification(data.get("ContactName"));				
					clickToolsMenu(data.get("Tools_Menu"),  data.get("Tools_Menu_DD"));							
					verifyQuoteHistory();			
			       quickSearchAndVerifySearchResults(data.get("SearchBy"),QuoteNum);
					verifyAndClickQuoteNumberOnHistory();
					verifyQuoteDetails();						
					orderLib.convertQuote();
					cartLib.verifyCartBreadCrumb();
					// Proceed to checkout
					orderLib.proceedToCheckout();
					addAdditionalInformation(data.get("BUSINESSUNIT"),data.get("OPERATINGUNIT"),data.get("LOCATIONCODE"),data.get("DEPARTMENT"),data.get("ACCOUNT"),data.get("ENTITYNAME"),data.get("ENDUSERCONTACT"),data.get("ORDERCONTACT"),data.get("LICENCE"));
					orderLib.shippingBillPayContinueButton();  // Click continue on  shipping address 
					orderLib. shippingBillPayContinueButton(); 
					orderLib.clickOnReviewOrderButton();
					// Place Order
					String summaryAmount = cartLib.getSummaryAmountInCart();
					orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
					// Verify Receipt
					orderLib.verifyReceiptVerbiage();
					String ReferenceNumber = sbpLib.getReferenceNum();
					orderLib.clickOrderDetailsLinkOnReceiptPage();
					verifyContactName(data.get("ContactName"));
					canadaLib.clickOnInvoiceHistory();
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),  data.get("Tools_Menu_DD"));	
					quickSearchAndVerifySearchResults(data.get("SearchBy1"),ReferenceNumber);
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
			ReportStatus.fnUpdateResultStatus("CQTValidateSoftwareLicenseFields", "TC_QTH12", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQTValidateSoftwareLicenseFields", "TC_QTH12", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
