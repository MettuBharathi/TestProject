package com.insight.WebTest.SLP;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SLPLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SLP06_SPLAZeroUsageTest extends SLPLib{
	
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib=new CanadaLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  SLP06_SPLAZeroUsageTest
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test SPLA Zero Usage Test
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_SLP06(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SLP06_SPLA-ZeroUsage", TestData, "SLP");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("SLP06_SPLA-ZeroUsage", TestData, "SLP", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("SLPProrationMicrosoft");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");
						
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						// "allow_unlimited_spla_ordering;off"
						cmtLib.setHostedLicensingPermissionsOFF(data.get("Set_Permission1"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
						// Clear usage
						cmtLib.AddMonthInHostedLicensingAdministrationPage(data.get("Month1"), data.get("Year1"), data.get("Type"),data.get("SoldTO"),data.get("SalesOrg"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options2"));
						
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// "user_requires_approval;off";
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
						cmtLib.loginAsAdminCMT();
						// Login verification
						cmtLib.loginVerification(data.get("ContactName"));
						// account tools >> Software License Agreements
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						// Select Software  Lic Agreements
				     	canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
						// verify search results 
				     	searchLib.verifysearchResultsPage();
				     	// search for a part / product and add to cart
				     	searchLib.searchInHomePage(data.get("SearchText"));
				     	commonLib.addToCartAndVerify();
				     	orderLib.continueToCheckOutOnAddCart();
				     	cartLib.verifyItemInCartByInsightPart(data.get("SearchText"));
				     	
				     // account tools >> Software License Agreements
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						retrieveLastUsageReport(data.get("Software_Agrement"));
						String subTotal=sbpLib.getTotalAmountInCart(data.get("SubTotal_label"));
						Float subTotalAmount = Float.parseFloat(subTotal.replace("$", ""));
						verifyAmount(subTotalAmount);
						canadaLib.clickOnReportZeroUsageLinkOnCart();
						// Verify Only Zero Usage Part in the Cart CAD $0.00"
						String subtotalAmt=cartLib.getSummaryAmountInCart();
						assertTextStringContains(subtotalAmt, data.get("Price")); 
						// verify reporting usage period warning message
						verifyReportingPeriodWarning();
						// Verify usage period on cart
						String cartUsagePeriod=canadaLib.verifyReportingUsagePeriod();
						//Proceed to checkout
						 orderLib.proceedToCheckout();
						 orderLib.clickOnAdditionalInfoContinueButton();
						 orderLib.clickContinueOnLineLevelInfo();
						 orderLib.shippingBillPayContinueButton();  // Click continue on  shipping address 
						 orderLib.shippingBillPayContinueButton();  // Billing address continue button
						 orderLib.addNewCardInPayment(data.get("cardNumber"), data.get("cardName"), data.get("month"), data.get("year"),data.get("poNumebr"));
						 orderLib.clickOnReviewOrderButton();  // Click Review order button
						// Verify usage period on place order page
						  String poUsagePeriod=verifyReportingUsagePeriod();
						  assertTextStringMatching(cartUsagePeriod, poUsagePeriod);
						 // Place Order
						 String amount = cartLib.getSummaryAmountInCart();
						orderLib.placeOrderAndVerifyReceiptOrderAndDate(amount);
						// Verify usage period on receipt page
						 String receiptUsagePeriod=verifyReportingUsagePeriod();
						 assertTextStringMatching(cartUsagePeriod, receiptUsagePeriod);
						
						 // account tools >> Software License Agreements
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						verifyAllReportingPeriodsCurrent();
						canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
						// verify search results 
				     	searchLib.verifysearchResultsPage();
				     	pipLib.selectFirstProductAddToCartAndVerifyCart();
				     	verifyAllReportingPeriodsCurrentinCartPage();
				     	commonLib.clickLogOutLink(data.get("Logout"));
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
						throw new RuntimeException(e);
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("SPLAZeroUsageTest", "SLP06", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("SPLAZeroUsageTest", "SLP06", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
			driver.quit();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}


}
