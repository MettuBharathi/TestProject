package com.insight.WebTest.Canada;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CAN01_ShipBillPayTaxTest extends CanadaLib{


	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  CAN01_ShipBillPayTaxTest
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This method is to verify MenuSearch for canada
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_CAN01(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN01_ShipBillPayTax", TestDataInsight, "Canada");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN01_ShipBillPayTax", TestDataInsight, "Canada", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("ShipBillPayTax");
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
						commonLib.continueToShopping();
						commonLib.clickCart();
						cartLib.verifyItemInCart(partNumber1);
						// Adding second product to cart
						commonLib.searchProduct(data.get("Search_Item"));
						commonLib.addSecondDisplyedItemToCartAndVerify();
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
						String PSTAMOUNT = canadaLib.getPSTInSummary(data.get("PST"));
						String GSTAMOUNT = canadaLib.getGSTInSummary(data.get("GST"));
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

						canadaLib.verifyGSTAndPSTInCartPage(data.get("PST"), data.get("GST"));
						orderLib.termsInPaymentInfo(data.get("PONumber"));
						canadaLib.verifyGSTAndPSTInCartPage(data.get("PST"), data.get("GST"));
						String PSTAMOUNT1 = canadaLib.getPSTInSummary(data.get("PST"));
						String GSTAMOUNT1 = canadaLib.getGSTInSummary(data.get("GST"));
						canadaLib.verifyGSTAmonunts(GSTAMOUNT, GSTAMOUNT1);
						String summaryAmount = cartLib.getSummaryAmountInCart();
						orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
						shipbLib.clickOrderDetailsButtonInREceipt();
						canadaLib.verifyGSTAndPSTInCartPage(data.get("PST"), data.get("GST"));
						String PSTAMOUNT2 = canadaLib.getPSTInSummary(data.get("PST"));
						String GSTAMOUNT2 = canadaLib.getGSTInSummary(data.get("GST"));
						canadaLib.verifyGSTAmonunts(GSTAMOUNT, GSTAMOUNT2);
						commonLib.clickLogOutLink(data.get("Logout_Header"));
						
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("ShipBillPayTax", "CAN_01", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("ShipBillPayTax", "CAN_01", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
			
			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}
