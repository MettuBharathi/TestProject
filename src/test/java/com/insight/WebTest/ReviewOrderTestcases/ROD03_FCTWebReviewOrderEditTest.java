package com.insight.WebTest.ReviewOrderTestcases;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ROD03_FCTWebReviewOrderEditTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib=new ProductDisplayInfoLib();
	CMTLib cmtLib=new CMTLib();
	SearchLib searchLib=new SearchLib();
	CommonLib commonLib=new CommonLib();
	CartLib cartLib=new CartLib();
	ProductDisplayInfoLib prodLib=new ProductDisplayInfoLib();

	// #############################################################################################################
	// #    Name of the Test         : ROD03_FCTWebReviewOrderEdit
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order basic
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ROD03(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD03_FCTWebReviewOrderEdit", TestData, "Web_Review_Order");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD03_FCTWebReviewOrderEdit", TestData, "Web_Review_Order", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("FCTWebReviewOrderEdit");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
								"");


						//**** Login to CMT and Disable allow File Upload during Checkout and Override Payment Options *****//
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						String[] permissions = data.get("Customer_Permissions").split(",");
						for (i = 0; i < permissions.length; i++) {
							cmtLib.setCustomerLevelPermissionsOFF(permissions[i]);
						}
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.loginAsAdminCMT();
						//************* Login As to UAT web ******************************//
						searchLib.searchInHomePage(data.get("SearchText"));
						//***** Add a item to cart >> proceed To Checkout >> Review Order****//
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						proceedToCheckout();
						//******** Click continue on Line level Info, Ship and Bill pay sections ********************//
						continueButtonOnAdditionalInformationSection();
						clickContinueOnLineLevelInfo();
						shippingBillPayContinueButton();
						shippingOptionsCarrierSelection();
						shippingBillPayContinueButton();
						//**************************** Enter payment Info *****************************************//
						selectPaymentInfoMethodCreditCard(data.get("Card_Number1").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));
						clickOnReviewOrderButton();
						//******************************* Click on Edit link on Payment info section****************//
						editOrderInfo(data.get("Section_Name"));
						//******************************* Verify card Number Ending with the given details***********//
						verifyCardNumberOnEditPaymentInfoSection(data.get("Card_Ending_Digits1"));
						// ******************************* Add new card in payment Info *****************************//
						addNewCardInPaymentInfoSection(data.get("Card_Number2").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"), data.get("PO_Number"));
						//********************** Verify the newly added card ending digits***********************//
						editOrderInfo(data.get("Section_Name"));
						verifyCardNumberOnEditPaymentInfoSection(data.get("Card_Ending_Digits2"));
						//*********************** Review order **************************************//
						clickOnReviewOrderButton();
						//*************verify product description in place order screen*************//
						clickOnProdDescOnPlaceOrderScreen();
						commonLib.verifyDisplayedProductDetails(data.get("SearchText"));
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						//*****Proceed to check out and fill all the ship / Bill details and click review Order*****//
						proceedToCheckout();
						continueButtonOnAdditionalInformationSection();
						clickContinueOnLineLevelInfo();
						shippingBillPayContinueButton();
						shippingOptionsCarrierSelection();
						shippingBillPayContinueButton();
						addNewCardInPaymentInfoSection(data.get("Card_Number3").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"), data.get("PO_Number"));
						clickOnReviewOrderButton();
						//*************Verify card ending details of third card *********************************//
						editOrderInfo(data.get("Section_Name"));
						verifyCardNumberOnEditPaymentInfoSection(data.get("Card_Ending_Digits3"));
						commonLib.clickLogOutLink(data.get("Logout"));
						//fnCloseTest();
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("ROD", "ROD03", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("ROD", "ROD03", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}


