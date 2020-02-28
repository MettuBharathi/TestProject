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

public class LNL08_STNoSmartTrackerRequiredTest extends LineLevelInfoLib{

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
				// #       Name of the Test         : FCT Web  LineLevel ST_NoSmartTrackerRequired
				// #       Migration Author         : Cigniti Technologies
				// #
				// #       Date of Migration        : November 2019
				// #       DESCRIPTION              : To Test LineLevel ST_NoSmartTrackerRequired
				// #       Parameters               : StartRow ,EndRow , nextTestJoin
				// #
				// ###############################################################################################################
				@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
				@Test
				public void TC_LNL08(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
					try {
						int intStartRow = StartRow;
						int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "LNL08_STNoSmartTrackerRequired", TestData, "WEB_LineLevelInfo");
						for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
							try {
								fnOpenTest();
								ReportStatus.fnDefaultReportStatus();
								ReportControl.intRowCount = intCounter;
								Hashtable<String, String> data = TestUtil.getDataByRowNo("LNL08_STNoSmartTrackerRequired", TestData, "WEB_LineLevelInfo", intCounter);
								TestEngineWeb.reporter.initTestCaseDescription("STNoSmartTrackerRequired");
							
								// Login to CMT
								cmtLib.loginToCMT(data.get("Header"));
								cmtLib.searchForWebGroup(data.get("WebGrp"));
								cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
								cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
								cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
								// login As
								cmtLib.loginAsAdminCMT();
								// Add first item  >> RAM 
								searchLib.searchInHomePage(data.get("SearchText1"));
								cartLib.selectFirstProductDisplay();
								String partNumber1=prodLib.getMFRNumberInProductInfopage();
								commonLib.addToCartAndVerify();
								orderLib.continueToCheckOutOnAddCart();
								cartLib.verifyItemInCart(partNumber1);
								// Proceed to check out
								orderLib.proceedToCheckout();
								orderLib.continueButtonOnAdditionalInformationSection();
								
								clickOnLinelevelInfoOptionalLink();
								orderLib.clickContinueOnLineLevelInfo();  // Click continue on LLI
								orderLib.shippingBillPayContinueButton(); // Click continue on shipping address Section
								orderLib.shippingBillPayContinueButton(); // Click continue on Shipping options Section
								orderLib.shippingBillPayContinueButton(); //Click continue on Billing address Section
								orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));  // VISA card
								orderLib.clickOnReviewOrderButton();
								String summaryAmount=cartLib.getSummaryAmountInCart();
								orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
								orderLib.getTextfromReferenceNumber();
								
								//Verify Receipt
								orderLib.verifyReceiptVerbiage();
								commonLib.clickLogOutLink(data.get("Logout"));
								
							} catch (Exception e) {
								ReportStatus.blnStatus = false;
								gErrorMessage = e.getMessage();
								gTestStatus = false;
							}
							ReportControl.fnEnableJoin();
							ReportStatus.fnUpdateResultStatus("STNoSmartTrackerRequired", "TC_LNL08", ReportStatus.strMethodName, intCounter, browser);
							fnCloseTest();
						}
					} catch (Exception e) {
						e.printStackTrace();
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
						ReportStatus.fnUpdateResultStatus("STNoSmartTrackerRequired", "TC_LNL08", ReportStatus.strMethodName, 1, browser);
						throw new RuntimeException(e);
					}

					ReportControl.fnNextTestJoin(nextTestJoin);
				}
}
