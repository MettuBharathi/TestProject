package com.insight.WebTest.Canada;

import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN08_ConverQuoteBundlesTest extends CanadaLib{

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	MarriottIntlCorpLib micLib=new MarriottIntlCorpLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  CAN08_ConverQuoteBundles
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : US261 to verify Tax in Save as Quote and Order Receipt
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
 @Parameters({ "StartRow", "EndRow", "nextTestJoin" })
 @Test
 public void Tc_CAN08(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
	try {
				int intStartRow=StartRow;
				int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CAN08_ConverQuoteBundles", TestDataInsight, "Canada");
				for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
				{
		try {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount=intCounter;
			Hashtable<String, String> data=TestUtil.getDataByRowNo("CAN08_ConverQuoteBundles", TestDataInsight, "Canada",intCounter);
		    this.reporter.initTestCaseDescription("ConverQuoteBundles");
			reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
						
			// Login to CMT
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
			// Enable Quotes - ON
			cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
			// Login as to UAT
			cmtLib.loginAsAdminCMT();
			// Login Verification
			cmtLib.loginVerification(data.get("ContactName"));

			// Add Bundles from company standards screen and verify
			commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
			searchLib.selectProductGroupAndVerify(data.get("Product_Group"), data.get("Product_Name"));
			searchLib.verifyTheProductGroupTable(data.get("Product_Grp_Columns"));
			// Set quantity for each item
			micLib.setQuantityForProductGroup(data.get("Quantity"));
			// Add to Order
			commonLib.addToOrderAndViewCart();
			// Verify Bundles in cart
			cartLib.verifyProductGroupBundleAddedToCart(data.get("Product_Name"));
			List<String> quantity = orderLib.getCartProductQuantityForBundleOfProducts();
			cartLib.verifyProductGroupQuantityInCart(quantity, data.get("Quantity"));
			// save Quote
			orderLib.createQuote(data.get("Quote_Name"));
			String refNumber = orderLib.getQuoteReferenceNumber();
			orderLib.verifyProductBundleTableLoadedInSaveQuoteScreen();
			// Verify EWR Fees
			String ESRFee=verifyEWRFeesForCartOnQuoteConfirmationPage();
			// Verify EWR Total in the Cart
			verifyEWRTotalFeesOnQuotesScreen();
			// verify GST 
			String GST=verifyGSTEstimateOnQuotesScreen();
			// Verify  P.S.T/Q.S.T Estimates
			verifyPST_QSTEstimates();
			// Navigate to Quote History
			commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu1"), data.get("Tools_Menu_DD1"));
			orderLib.searchByInQuoteHistory(refNumber, data.get("DD_Option"));
			// Verify ESR Fee on Quote history screen
			verifySummaryTableAmountsOnQuotesHistoryPage(data.get("Label"),ESRFee);
			// Convert Quote
			orderLib.convertQuote();
			cartLib.verifyCartBreadCrumb();
			
			//Proceed to checkout
			 orderLib.proceedToCheckout();
			 orderLib.clickContinueOnLLIAndShipBillPaySections();
			 orderLib.addNewCardInPayment(data.get("cardNumber"), data.get("cardName"), data.get("month"), data.get("year"),data.get("poNumebr"));
			 orderLib.clickOnReviewOrderButton();
			 
			//Place Order
			String summaryAmount=cartLib.getSummaryAmountInCart();
			orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
			//Verify Receipt
			orderLib.verifyReceiptVerbiage();
			orderLib.clickOrderDetailsLinkOnReceiptPage();
			/// Verify Tax in Receipt page
			String actualGST=getGSTInSummary(data.get("GST_LABEL"));
			verifyGSTAmonunts(actualGST, GST);
			verifyEWRInCartPage();
			
			// Logout
			commonLib.clickLogOutLink(data.get("Logout"));
			} catch (Exception e) {
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
			}
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("ConverQuoteBundles", "CAN08", ReportStatus.strMethodName, intCounter, browser);
			fnCloseTest();
		}
	} catch (Exception e) {
		e.printStackTrace();
		ReportStatus.blnStatus = false;
		gErrorMessage = e.getMessage();
		gTestStatus = false;
		ReportStatus.fnUpdateResultStatus("ConverQuoteBundles", "CRT08", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}

	ReportControl.fnNextTestJoin(nextTestJoin);
}
}
