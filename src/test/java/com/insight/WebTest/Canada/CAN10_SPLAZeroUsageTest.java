package com.insight.WebTest.Canada;

import java.util.Hashtable;

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

public class CAN10_SPLAZeroUsageTest extends CanadaLib{
	
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
		// #       Name of the Test         :  CAN10_SPLAZeroUsage
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test Load of the Cart After Selecting SPLA Products
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_CAN10(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN10_SPLAZeroUsage", TestData, "SLP");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN10_SPLAZeroUsage", TestDataInsight,"Canada", intCounter);
			TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase Description is : ConverQuoteBundles ");
			
			// Login to CMT
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			/// allow_unlimited_spla_ordering;off";
			cmtLib.setHostedLicensingPermissionsOFF(data.get("Set_Permission"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
			// Clear usage
			cmtLib.AddMonthInHostedLicensingAdministrationPage(data.get("Month1"), data.get("Year1"), data.get("Type"),data.get("SoldTO"),data.get("SalesOrg"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options2"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
			
			// Login as to UAT
			cmtLib.loginAsAdminCMT();
			// Login Verification
			cmtLib.loginVerification(data.get("ContactName"));
			CandaHomePageVerification();
			
			// account tools >> Software License Agreements
			commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
			// Select Software Lic Agreements
			selectSPLADetailsProductCheckBox(data.get("SPLA"));
			// Search for a product and add to cart
			searchLib.verifysearchResultsPage();
			searchLib.searchInHomePage(data.get("SearchItem1"));
			commonLib.addToCartAndVerify();
			orderLib.continueToCheckOutOnAddCart();
			cartLib.verifyItemInCartByInsightPart(data.get("SearchText1"));
			
			// Search for a product and add to cart
			searchLib.verifysearchResultsPage();
			searchLib.searchInHomePage(data.get("SearchItem2"));
			commonLib.addToCartAndVerify();
			orderLib.continueToCheckOutOnAddCart();
			cartLib.verifyItemInCart(data.get("SearchItem2"));
			
			// account tools >> Software License Agreements
			commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
			verifySPLAPage();
			
			selectReportUsageButtonsInMySoftwareLicenseAgreements(data.get("Btnlabel"));
			clickOnReportZeroUsageLinkOnCart();
			
			// Verify Only Zero Usage Part in the Cart CAD $0.00"
			String summaryAmount=cartLib.getSummaryAmountInCart();
			assertTextStringContains(summaryAmount, data.get("Price"));
			// Verify usage period on cart
			verifyReportingUsagePeriod();
			//Proceed to checkout
			 orderLib.proceedToCheckout();
			 orderLib.shippingBillPayContinueButton();  // Click continue on  shipping address 
			 orderLib.shippingBillPayContinueButton();  // Billing address continue button
			 orderLib.addNewCardInPayment(data.get("cardNumber"), data.get("cardName"), data.get("month"), data.get("year"),data.get("poNumebr"));
			 orderLib.clickOnReviewOrderButton();  // Click Review order button
				// Place Order
			 String amount = cartLib.getSummaryAmountInCart();
			orderLib.placeOrderAndVerifyReceiptOrderAndDate(amount);
			
			// account tools >> Software License Agreements
			commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
			// Select Software  Lic Agreements
	     	selectSPLADetailsProductCheckBox(data.get("SPLA"));
			// verify search results and select first product
	     	searchLib.verifysearchResultsPage();
			pipLib.selectFirstProductAddToCartAndVerifyCart();
			verifyReportingUsagePeriod();
			// Logout 
			commonLib.clickLogOutLink(data.get("Logout"));
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
						throw new RuntimeException(e);
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("CAN10_SPLAZeroUsage", "CAN10", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("CAN10_SPLAZeroUsage", "CAN10", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}
}
