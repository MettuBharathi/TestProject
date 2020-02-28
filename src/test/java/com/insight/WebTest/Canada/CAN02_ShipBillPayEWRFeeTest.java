package com.insight.WebTest.Canada;

import com.insight.Lib.CanadaLib;
import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CAN02_ShipBillPayEWRFeeTest extends CanadaLib{
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  CAN02_ShipBillPayEWRFeeTest
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This method is to verify MenuSearch for canada
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_CAN02(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN02_ShipBillPayEWRFee", TestDataInsight, "Canada");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN02_ShipBillPayEWRFee", TestDataInsight, "Canada", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("ShipBillPayEWRFee");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
								"");

						/*reporter.SuccessReport("Iteration Number : ", "**************Iteration Number::  " + intCounter
								+ " To validate:: Favorites_Logged In User_Verify user is able to Add products to favorites_Remove Favorites in PLP/PDP **************");*/


						CommonLib commonLib = new CommonLib();
						CMTLib cmtLib = new CMTLib();
						CartLib cartLib = new CartLib();
						OrderLib orderLib = new OrderLib();
						ShipBillPayLib shipbLib = new ShipBillPayLib();
						CanadaLib canadaLib = new CanadaLib();


						cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
								data.get("ContactName"));
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.setPermissions(data.get("Menu_Name"),data.get("Enable_Purchasing_Popup"));
						cmtLib.clickOnloginAs();
						switchToChildWindow();

						canadaLib.verifyCanadaWebgroup();
						// Adding first product to cart
						commonLib.searchProduct(data.get("Search_Item"));
						commonLib.addFirstDisplyedItemToCartAndVerify();
						String partNumber1 = cartLib.getPartNumber();
						System.out.println("partNumber1"+partNumber1);
						commonLib.continueToShopping();
						commonLib.clickCart();
						cartLib.verifyItemInCart(partNumber1);
						// Adding second product to cart
						commonLib.searchProduct(data.get("Search_Item1"));
						commonLib.addFirstDisplyedItemToCartAndVerify();
						String partNumber2 = cartLib.getPartNumber();
						commonLib.continueToShopping();
						commonLib.clickCart();
						cartLib.verifyItemInCart(partNumber2);

						shipbLib.verifyPriceIsCAD(data.get("CANDAIAN_DOLLAR"));
						shipbLib.getSummaryAmountsInCart(data.get("SubTotal"), data.get("Total"));
						// proceed to check out
						orderLib.proceedToCheckout();
						cartLib.clickOnContinueButtonInAddInformtion();
						canadaLib.verifySBP();
						orderLib.shippingBillPayContinueButton();
						orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
						orderLib.shippingBillPayContinueButton();
						orderLib.termsInPaymentInfo(data.get("PONumber"));
						orderLib.verifyPlaceOrderLabel();

						String EWRAMOUNT = canadaLib.getEWRFeeInSummary();
						canadaLib.verifyEWRInCartPage();

						canadaLib.clickReturnToCart();
						commonLib.spinnerImage();
						canadaLib.verifyPlaceCartLabel();
						commonLib.updateCartQuantity(data.get("quantity"));
						// proceed to check out
						orderLib.proceedToCheckout();
						cartLib.clickOnContinueButtonInAddInformtion();
						canadaLib.verifySBP();
						orderLib.shippingBillPayContinueButton();
						orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
						orderLib.shippingBillPayContinueButton();

						orderLib.termsInPaymentInfo(data.get("PONumber"));

						String EWRAMOUNT1 = canadaLib.getEWRFeeInSummary();
						canadaLib.verifyEWRAmonunts(EWRAMOUNT, EWRAMOUNT1);
						String summaryAmount = cartLib.getSummaryAmountInCart();
						orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
						shipbLib.clickOrderDetailsButtonInREceipt();
						String EWRAMOUNT2 = canadaLib.getEWRFeeInSummary();

						// Adding first product to cart
						commonLib.searchProduct(data.get("Search_Item2"));
						commonLib.addToCartAndVerify();

						commonLib.continueToShopping();
						commonLib.clickCart();
						cartLib.verifyItemInCart(data.get("Search_Item2"));
						// proceed to check out
						orderLib.proceedToCheckout();
						cartLib.clickOnContinueButtonInAddInformtion();
						canadaLib.verifySBP();
						orderLib.shippingBillPayContinueButton();
						orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
						orderLib.shippingBillPayContinueButton();
						orderLib.termsInPaymentInfo(data.get("PONumber"));
						orderLib.verifyPlaceOrderLabel();

						String EWRAMOUNT3 = canadaLib.getEWRFeeInSummary();
						canadaLib.verifyEWRAmonunts(EWRAMOUNT2, EWRAMOUNT3);
						String summaryAmount1 = cartLib.getSummaryAmountInCart();
						orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount1);
						shipbLib.clickOrderDetailsButtonInREceipt();
						commonLib.clickLogOutLink(data.get("Logout_Header"));
						//fnCloseTest();
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("CAN", "ShipBillPayEWRFeeTest", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("CAN", "ShipBillPayEWRFeeTest", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
}
