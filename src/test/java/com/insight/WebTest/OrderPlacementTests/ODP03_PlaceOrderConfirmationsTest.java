package com.insight.WebTest.OrderPlacementTests;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ODP03_PlaceOrderConfirmationsTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodLib = new ProductDisplayInfoLib();
	OrderLib orderLib =new OrderLib();

	// #############################################################################################################
	// #    Name of the Test         : ODP03_PlaceOrderConfirmations
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order  Confirmations
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ODP03(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP03_PlaceOrderConfirmations", TestData,"Web_Order_Placement");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP03_PlaceOrderConfirmations", TestData,"Web_Order_Placement", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("PlaceOrderConfirmations");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
								"");



						// Login to CMT and disable Allow File Upload during Checkout,Override Payment Options
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));

						//Uncheck Override Payments Options in web Group Level
						cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));

						// select Contacts and Notifications
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));

						// Remove existing sales rep and add new one
						cmtLib.RemoveExistedsalesreps(data.get("Rep_Name"));
						cmtLib.addNewSalesRep(data.get("Rep_Email"));
						cmtLib.enableOrDisableOrdersOfSalesRepOnContactsAndNotifications(data.get("Rep_Name"), "OFF");  // sales rep "Orders >> OFF"
						cmtLib.enableOrDisableQuotesOfSalesRepOnContactsAndNotifications(data.get("Rep_Name"), "OFF");  // sales rep "Quotes >> OFF"

						// create client notifications
						cmtLib.createClientNotifications(data.get("Rep_Email"));

						cmtLib.enableOrDisableQuotesOfClientNotificationRep(data.get("Rep_Email"), "OFF");    // Client Notification Rep "Quotes >> OFF"
						cmtLib.enableOrDisableOrdersOfOfClientNotificationRep(data.get("Rep_Email"), "OFF");  // Client Notification Rep "Orders >> OFF"

						// Login as user selected
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options2"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.loginAsAdminCMT();

						//  Select First Product and Add to cart
						searchLib.searchInHomePage(data.get("SearchText"));
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						cartLib.verifyItemInCart(data.get("SearchText"));

						proceedToCheckout(); // proceed to checkout
						continueButtonOnAdditionalInformationSection();  // Click continue on Additional information Section
						clickContinueOnLineLevelInfo(); // Click continue on Line Level information Section
						shippingBillPayContinueButton(); // Click continue on shipping address Section
						shippingBillPayContinueButton(); // Click continue on Shipping options Section
						shippingBillPayContinueButton(); //Click continue on Billing address Section
						selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));
						clickOnReviewOrderButton();

						//Place Order
						String summaryAmount=cartLib.getSummaryAmountInCart();
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);

						//Verify Receipt
						verifyReceiptVerbiage();
						clickOrderDetailsLinkOnReceiptPage();

						verifyShippingAddressOnReceiptPage(data.get("Section_Name1")); // Verifying shipping address
						verifyBillingAddressOnReceiptPage(data.get("Section_Name2"));  // verifying billing address
						verifyPaymentInformationOnReceiptPage(data.get("Section_Name3"),data.get("Month"),data.get("Year"),data.get("Card_Name"),data.get("Ending_Card_Numbers"),data.get("Card_Type"));  // verifying payment info

						// Verifying the part in cart
						cartLib.verifyItemInCart(data.get("SearchText"));
						commonLib.clickLogOutLink(data.get("Logout"));

						//fnCloseTest();
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("ODP", "ODP03", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("ODP", "TC_ODP03", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}


