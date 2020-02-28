package com.insight.WebTest.LNL;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.LineLevelInfoLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class LNL11_LineLevellDataIPPTest  extends LineLevelInfoLib{
    
	        // Initializing libraries
			CMTLib cmtLib = new CMTLib();
			CommonLib commonLib = new CommonLib();
			CartLib cartLib = new CartLib();
			SearchLib searchLib = new SearchLib();
			ProductDetailLib prodDetailsLib=new ProductDetailLib();
			OrderLib orderLib=new OrderLib();
			ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
			ShipBillPayLib sbpLib=new ShipBillPayLib();
			CanadaLib canadaLib=new CanadaLib();
			ProductDetailLib prodLib=new ProductDetailLib();
			   
			    // #############################################################################################################
				// #       Name of the Test         :   LNL11_LineLevelDataIPP
				// #       Migration Author         : Cigniti Technologies
				// #
				// #       Date of Migration        : November 2019
				// #       DESCRIPTION              : This Test is used to Test LineLevelViewCart
				// #       Parameters               : StartRow ,EndRow , nextTestJoin
				// #
				// ###############################################################################################################
				@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
				@Test
				public void TC_LNL11(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
					try {
						int intStartRow = StartRow;
						int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "LNL11_LineLevellDataIPP", TestData, "WEB_LineLevelInfo");
						for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
							try {
								fnOpenTest();
								ReportStatus.fnDefaultReportStatus();
								ReportControl.intRowCount = intCounter;
								Hashtable<String, String> data = TestUtil.getDataByRowNo("LNL11_LineLevellDataIPP", TestData, "WEB_LineLevelInfo", intCounter);
								TestEngineWeb.reporter.initTestCaseDescription("LineLevellDataIPP");
							
								// Login to CMT
								cmtLib.loginToCMT(data.get("Header"));
								cmtLib.searchForWebGroup(data.get("WebGrp"));
								cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
								cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
								cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
								// Enable CrossSell;ON";
								cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
								// login As
								cmtLib.loginAsAdminCMT();
								
								// Search for part or product >> 20L50011US
								searchLib.searchInHomePage(data.get("SearchText"));
								commonLib.verifyDisplayedProductDetails(data.get("SearchText"));
								commonLib.addToCartAndVerify();
							    // Warranty number : 5WS0T36152 
								orderLib.continueToCheckOutAddWarrantyAndVerifyTheCart(data.get("warranty_Number"));
								//  remove added warranty
								orderLib.clickRemoveWarrantyLink();
								canadaLib.verifyPlaceCartLabel();
								orderLib.proceedToCheckout();
								orderLib.continueButtonOnAdditionalInformationSection();
								orderLib.clickContinueOnLineLevelInfo();
								canadaLib.verifySBP();
								orderLib.clearPhnumberInShippinAddress();
								orderLib.shippingBillPayContinueButton(); // Click continue on shipping address Section
								orderLib.shippingBillPayContinueButton(); // Click continue on Shipping options Section
								orderLib.shippingBillPayContinueButton(); //Click continue on Billing address Section
								orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));  // VISA card
								orderLib.clickOnReviewOrderButton();
								verifywarrantyOnPlaceOrderPage("NotAvailable");
								commonLib.clickLogOutLink(data.get("Logout"));
								
								// navigate back to CMT
								cmtLib.navigateBackToCMT();
								// login As
								cmtLib.loginAsAdminCMT();
								// Search for part or product >> 20L50011US
								searchLib.searchInHomePage(data.get("SearchText"));
								commonLib.verifyDisplayedProductDetails(data.get("SearchText"));
								commonLib.addToCartAndVerify();
								orderLib.continueToCheckOutOnAddCart();
								cartLib.verifyItemInCart(data.get("SearchText"));
								verifyWarrantyDescOncartpage("OFF");
								
								// Logout
								commonLib.clickLogOutLink(data.get("Logout"));
								
							} catch (Exception e) {
								ReportStatus.blnStatus = false;
								gErrorMessage = e.getMessage();
								gTestStatus = false;
							}
							ReportControl.fnEnableJoin();
							ReportStatus.fnUpdateResultStatus("LineLevellDataIPP", "TC_LNL11", ReportStatus.strMethodName, intCounter, browser);
							fnCloseTest();
						}
					} catch (Exception e) {
						e.printStackTrace();
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
						ReportStatus.fnUpdateResultStatus("LineLevellDataIPP", "TC_LNL11", ReportStatus.strMethodName, 1, browser);
						throw new RuntimeException(e);
					}

					ReportControl.fnNextTestJoin(nextTestJoin);
				}

}
