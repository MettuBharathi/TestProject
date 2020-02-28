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

public class SLP14_AdobeVIP extends SLPLib{
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
	// #       Name of the Test         :  SLP14_AdobeVIP
	// #       Migration Author         :  Cigniti Technologies
	// #
	// #       Date of Migration        : October 2019
	// #       DESCRIPTION              : This Test is used to Test SPLA Zero Usage Test
	// #       Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_SLP14(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SLP14_AdobeVIP", TestData, "SLP");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SLP14_AdobeVIP", TestData, "SLP", intCounter);
					TestEngineWeb.reporter
							.initTestCaseDescription("SLPProrationMicrosoft");
					reporter.SuccessReport("Iteration Number : ",
							"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
									+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");
					
					// Login to CMT
					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));//TU_QTPAdobeVIPAccount
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					// "enable_standard_reports;ON";
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.loginAsAdminCMT();
					// Login verification
					cmtLib.loginVerification(data.get("ContactName"));
					// account tools >> Company Standards					
					searchLib.selectAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"),data.get("productGroup"),data.get("productName"));
					searchLib.verifyProductWStandardsPage();
					searchLib.verifyClientAndClickOnProductGrpName(data.get("productName"));
					searchLib.checkMessageiconforAdobeProducts();
					// Search for part or product and add to cart : part : 65234076BA03A12
			     	searchLib.searchInHomePage(data.get("PartNum1"));
			     	commonLib.verifyPDPMesssageforAdobeProducts();
			     	commonLib.addToCartAndVerify();
			     	orderLib.continueToCheckOutOnAddCart();
			     // Search for Adobe part or product and add to cart : part : 65234098BA03A12
			     	searchLib.searchInHomePage(data.get("PartNum2"));
			     	commonLib.verifyPDPMesssageforAdobeProducts();
			     	commonLib.addToCartAndVerify();
			     	orderLib.continueToCheckOutOnAddCart();
			     // Search for Non Adobe part or product and add to cart : part :L9K19UT#ABA 
			     	searchLib.searchInHomePage(data.get("PartNum3"));
			     	commonLib.addToCartAndVerify();
			     	orderLib.continueToCheckOutOnAddCart();
			     	verifyandClickchangeLink(data.get("PartNum1"));
			     	verifydeployedatewithcurrentdate();
			     	calenderforUnpaidLicense(data.get("Date1"));
			     	clickapply();
			     	verifyandClickchangeLink(data.get("PartNum2"));
			     	verifydeployedatewithcurrentdate();
			     	calenderforUnpaidLicense(data.get("Date2"));
			     	clickapply();
			     	 //Verify Deployed date displayed in cart page
                    String DeployedDate= getTextfromdeployedateinCartPage();
			     	orderLib.proceedToCheckout();
			     	int i=1;
			     	int j=2;
			     	enterPAvalue(data.get("PA"),i);
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
                            
                    orderLib.verifyPlaceOrderLabel();
                    String OrderDate=getTextfromdeployedateinPlaceOrderPage();
                    assertTrue(DeployedDate.contains(OrderDate), "Both dates are matched");
                    //Verifying PA fields in Place order page
                    verifyPAvalueinPlaceOrderPage();                              
                    //Place Order
						String summaryAmountInLogin=cartLib.getSummaryAmountInCart();
						 orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmountInLogin);
						String RefNumber= orderLib.getTextfromReferenceNumber();
                      orderLib.clickOrderDetailsLinkOnReceiptPage();
                      					
					commonLib.clickLogOutLink(data.get("Logout"));
					
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
					throw new RuntimeException(e);
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("AdobeVIP", "SLP14", ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("AdobeVIP", "SLP14", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
		driver.quit();
		ReportControl.fnNextTestJoin(nextTestJoin);
		}
}
					

