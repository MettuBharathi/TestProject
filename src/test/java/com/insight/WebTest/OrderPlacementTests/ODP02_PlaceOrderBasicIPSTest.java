package com.insight.WebTest.OrderPlacementTests;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ODP02_PlaceOrderBasicIPSTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodLib = new ProductDisplayInfoLib();
	OrderLib orderLib =new OrderLib();

	// #############################################################################################################
	// #    Name of the Test         : ODP02_PlaceOrderBasicIPS
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order basic
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ODP02(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP02_PlaceOrderBasicIPS", TestData,"Web_Order_Placement");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP02_PlaceOrderBasicIPS", TestData,"Web_Order_Placement", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("PlaceOrderBasicIPS");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
								"");


						// Login to CMT and disable Allow File Upload during Checkout,Override Payment Options
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						String[] permissions=data.get("Customer_Permissions_OFF").split(",");
						for(i=0;i<permissions.length;i++){
							cmtLib.setCustomerLevelPermissionsOFF(permissions[i]);
						}
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));

						// navigate to checkout settings >>  check User Service Level Shipping and select SLS ground
						cmtLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
						cmtLib.selectDefaultShippingOptionInCheckoutSettings(data.get("Default_Shipping_Option"));
						cmtLib.clickupdateatDefaultShippingOption();
						cmtLib.loginAsAdminCMT();

						searchLib.searchInHomePage(data.get("SearchText1"));
						commonLib.verifyDisplayedProductDetails(data.get("SearchText1"));

						// Cart verification
						commonLib.contractOnProductDetailPage();
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();

						// Select New contract
						searchLib.selectNewcontract(data.get("Contract_Name"));
						searchLib.searchInHomePage(data.get("SearchText2"));
						commonLib.verifyDisplayedProductDetails(data.get("SearchText2"));

						// Verify contract selected
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						commonLib.verifyContractInCart(data.get("Contract_Name"));
						proceedToCheckout();
						enterReportingDetailsInLineLevelInfoSection(data.get("REPORTING FIELD_4"), data.get("REPORTING FIELD_5"), data.get("REPORTING FIELD_6"));

						shippingBillPayContinueButton();  // continue button on Shipping address
						shippingOptionsCarrierSelection();  // carrier selection or continue in shipping options
						shippingBillPayContinueButton();  // Continue on billing address section

						selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PO_Number"));
						clickOnReviewOrderButton();  // Click review order button

						//Place Order
						String summaryAmountInLogin=cartLib.getSummaryAmountInCart();
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmountInLogin);

						//Verify Receipt
						verifyReceiptVerbiage();
						clickOrderDetailsLinkOnReceiptPage();

						verifyShippingAddressOnReceiptPage(data.get("Section_Name1")); // Verify shipping address
						verifyBillingAddressOnReceiptPage(data.get("Section_Name2"));  // verify billing address

						// Verify contract on Receipt page
						scrollToBottom();
						commonLib.verifyContractInCart(data.get("Contract_Name"));

						//fnCloseTest();
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("ODP", "ODP02", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("ODP", "TC_ODP02", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}


