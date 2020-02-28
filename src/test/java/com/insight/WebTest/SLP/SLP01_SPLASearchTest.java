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

public class SLP01_SPLASearchTest extends SLPLib{
	
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
			// #       Name of the Test         :  SLP01_SPLASearchTest
			// #       Migration Author         :  Cigniti Technologies
			// #
			// #       Date of Migration        : October 2019
			// #       DESCRIPTION              : This Test is used to Test Load of the Cart After Selecting SPLAProducts
			// #       Parameters               : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void TC_SLP01(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SLP01_SPLASearch", TestData, "SLP");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("SLP01_SPLASearch", TestData, "SLP", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription(" SearchIncludingChina ");
							reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
											+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");
							// Login to CMT
							cmtLib.loginToCMT(data.get("Header"));
							cmtLib.searchForWebGroup(data.get("WebGrp"));
							cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
							cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
							
							// Clear usage
							cmtLib.AddMonthInHostedLicensingAdministrationPage(data.get("Month1"), data.get("Year1"), data.get("Type"),data.get("SoldTO"),data.get("SalesOrg"));
							cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options2"));
							cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
							
							// enable_purchase_popup;ON";
							cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
							cmtLib.loginAsAdminCMT();
							
							// Login Verification 
							cmtLib.loginVerification(data.get("ContactName"));
							
							// account tools >> Software License Agreements
							commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
							
							// Select Software  Lic Agreements
					     	canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
							
					     	// verify search results and select first product
					     	searchLib.verifysearchResultsPage();
					     
					     	// Search for part or product and add to cart : part : 7NQ-00302-MSPLA
					     	searchLib.searchInHomePage(data.get("SearchText1"));
					     	commonLib.addToCartAndVerify();
					     	orderLib.continueToCheckOutOnAddCart();
					     	cartLib.verifyItemInCart(data.get("SearchText1"));
					     	
					        // search for product and add to cart  : Workstations
							searchLib.searchInHomePage(data.get("SearchText2"));
							pipLib.selectFirstProductAddToCartAndVerifyCart();
							// Verify Non Spla Items Message
							canadaLib.VerifyNonSplaItemsMessage();
							///	Remove Non Spla Items from the Cart
							commonLib.deleteItemFromCart();
							
							// Verify Usage Period
							canadaLib.verifyReportingUsagePeriod();
							//Proceed to checkout
							 orderLib.proceedToCheckout();
							 orderLib.clickOnAdditionalInfoContinueButton();
							 orderLib.clickContinueOnLLIAndShipBillPaySections();
							 orderLib.addNewCardInPayment(data.get("cardNumber"), data.get("cardName"), data.get("month"), data.get("year"),data.get("poNumebr"));
							 orderLib.clickOnReviewOrderButton();  // Click Review order button
							// Place Order
							String summaryAmount = cartLib.getSummaryAmountInCart();
							orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
							//Verify Receipt
							orderLib.verifyReceiptVerbiage();
							orderLib.clickOrderDetailsLinkOnReceiptPage();
							// Logout 
							commonLib.clickLogOutLink(data.get("Logout"));
							
							
						} catch (Exception e) {
							ReportStatus.blnStatus = false;
							gErrorMessage = e.getMessage();
							gTestStatus = false;
						}
						ReportControl.fnEnableJoin();
						ReportStatus.fnUpdateResultStatus("SPLASearch", "SLP01", ReportStatus.strMethodName, intCounter, browser);
						fnCloseTest();
					}
				} catch (Exception e) {
					e.printStackTrace();
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
					ReportStatus.fnUpdateResultStatus("SPLASearch", "SLP01", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}

				ReportControl.fnNextTestJoin(nextTestJoin);
			
			}
}
