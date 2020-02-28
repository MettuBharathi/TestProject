package com.insight.WebTest.Canada;

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
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN04_ShipBillPayFreightTest extends CanadaLib{
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  CAN04_ShipBillPayFreightTest
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This method is to verify MenuSearch for canada
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_CAN04(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN04_ShipBillPayFreight", TestDataInsight, "Canada");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN04_ShipBillPayFreight", TestDataInsight, "Canada", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
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
						commonLib.addToCartAndVerify();

						commonLib.continueToShopping();
						commonLib.clickCart();
						cartLib.verifyItemInCart(data.get("Search_Item"));

						commonLib.searchProduct(data.get("Search_Item1"));
						commonLib.addFirstDisplyedItemToCartAndVerify();
						String partNumber1 = cartLib.getPartNumber();
						System.out.println("partNumber1"+partNumber1);
						commonLib.continueToShopping();
						commonLib.clickCart();
						cartLib.verifyItemInCart(partNumber1);

						commonLib.searchProduct(data.get("Search_Item2"));
						commonLib.addFirstDisplyedItemToCartAndVerify();
						String partNumber2 = cartLib.getPartNumber();
						System.out.println("partNumber2"+partNumber2);
						commonLib.continueToShopping();
						commonLib.clickCart();
						cartLib.verifyItemInCart(partNumber2);
						// proceed to check out
						orderLib.proceedToCheckout();
						cartLib.clickOnContinueButtonInAddInformtion();
						orderLib.clickContinueOnLineLevelInfo();
						canadaLib.verifySBP();
						orderLib.shippingBillPayContinueButton();

						canadaLib.verifyGroundIsDefaultShippingOption();
						orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
						orderLib.shippingBillPayContinueButton();
						orderLib.termsInPaymentInfo(data.get("PONumber"));
						orderLib.verifyPlaceOrderLabel();
						shipbLib.Verifyshippingcarrier(data.get("Shipping_carrier"));
						String summaryAmount = cartLib.getSummaryAmountInCart();
						orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
						commonLib.clickLogOutLink(data.get("Logout_Header"));//fnCloseTest();
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("CANADA", "CAN04", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("CANADA", "CAN04", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}
