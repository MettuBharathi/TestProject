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
import com.insight.Lib.QuoteHistoryLib;
import com.insight.Lib.SLPLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SLP15_AdobeVIPQuoteConversion extends SLPLib{
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib=new CanadaLib();
	QuoteHistoryLib quoteLib= new QuoteHistoryLib();
	
	   
	    // #############################################################################################################
		// #       Name of the Test         :  SLP15_AdobeVIPQuoteConversion
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to test VIP quote conversion
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_SLP15(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			/*try {*/
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SLP15_AdobeVIPQuoteConversion", TestData, "SLP");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					/*try {*/
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("SLP15_AdobeVIPQuoteConversion", TestData, "SLP", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("SLPProrationMicrosoft");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");
						
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// "enable_standard_reports;ON";
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.loginAsAdminCMT();
						// Login verification
						cmtLib.loginVerification(data.get("ContactName"));
						// Search for part or product and add to cart :65234076BA03A12
				     	searchLib.searchInHomePage(data.get("PartNum1"));
				     	commonLib.addToCartAndVerify();
				     	orderLib.continueToCheckOutOnAddCart();
				     // Search for part or product and add to cart : 65234098BA03A12
				     	searchLib.searchInHomePage(data.get("PartNum2"));
				     	commonLib.addToCartAndVerify();
				     	orderLib.continueToCheckOutOnAddCart();
				     // Search for part or product and add to cart : L9K19UT#ABA
				     	searchLib.searchInHomePage(data.get("PartNum3"));
				     	commonLib.addToCartAndVerify();
				     	orderLib.continueToCheckOutOnAddCart();
				     	verifyandClickchangeLink(data.get("PartNum1"));
				     	verifydeployedatewithcurrentdate();
				     	clickapply();
				     	Thread.sleep(2000);
				     	verifyandClickcopytoallifexists(data.get("PartNum1"));
				     	orderLib.proceedToCheckout();
				     	int i=1;
				     	int j=2;
				     	enterPAvalue(data.get("PA"),i);//PA_fieldinplaceorderpage
				     	enterPAvalue(data.get("PA1"),j);
				     	orderLib.clickContinueOnLineLevelInfo();
				     	orderLib.shippingBillPayContinueButton(); // Click continue on
						                                             // shipping address
                        orderLib.shippingOptionsCarrierSelection(); // Click continue on
						                                            // shipping options
                        orderLib.shippingBillPayContinueButton(); // Billing address
						                                              // continue button
                        orderLib.enterCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"),
            					data.get("Year"), data.get("PONumber"));
                        orderLib.clickOnReviewOrderButton();                       
                        orderLib.verifyCartHeaderLabel();
                        //Verify Deployed date displayed in cart page
                        verifydeployeDateinCartPage();
                        int noOfCharacters=8;
                        String quoteName= getRandomString(noOfCharacters).toString();
                        orderLib.createQuoteandGenerateName(quoteName);
                        String refNumber = orderLib.getQuoteReferenceNumber();
                        searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
                        quoteLib.clickQuoteNumberLink(quoteName);
                        orderLib.convertQuote();
                        orderLib.verifyCartHeaderLabel();
                        orderLib.proceedToCheckout();
                        orderLib.clickContinueOnLineLevelInfo();
				     	orderLib.shippingBillPayContinueButton(); // Click continue on
						                                             // shipping address
                        orderLib.shippingOptionsCarrierSelection(); // Click continue on
						                                            // shipping options
                        orderLib.shippingBillPayContinueButton(); // Billing address
						                                              // continue button
                        orderLib.enterCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"),
            					data.get("Year"), data.get("PONumber"));
                        orderLib.clickOnReviewOrderButton();                       
                        orderLib.verifyPlaceOrderLabel();
                      //Place Order
						String summaryAmountInLogin=cartLib.getSummaryAmountInCart();
						 orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmountInLogin);
						String RefNumber= orderLib.getTextfromReferenceNumber();
                        orderLib.clickOrderDetailsLinkOnReceiptPage();
                     // Logout
            			commonLib.clickLogOutLink(data.get("header1"));

            			fnCloseTest();
            		}
            	}											
		}
		