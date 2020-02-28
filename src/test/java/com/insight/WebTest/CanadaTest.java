package com.insight.WebTest;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ActionEngine;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CanadaTest extends ActionEngine {

	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	OrderLib orderLib = new OrderLib();
	ShipBillPayLib shipbLib = new ShipBillPayLib();
	CanadaLib canadaLib = new CanadaLib();

	// #############################################################################################################
	// # Name of the Test : CAN01_ShipBillPayTax
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void CAN01_ShipBillPayTax(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN01_ShipBillPayTax", TestDataInsight, "Canada");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN01_ShipBillPayTax", TestDataInsight, "Canada",
					intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
							+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
					"");

			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("ContactName"));
			cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
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
			commonLib.searchProduct(data.get("Search_Item1"));
			commonLib.addToCartAndVerify();
			commonLib.continueToShopping();
			commonLib.clickCart();
			cartLib.verifyItemInCart(data.get("Search_Item1"));

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
			fnCloseTest();

		}
	}

	// #############################################################################################################
	// # Name of the Test : CAN02_ShipBillPayEWRFee
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void CAN02_ShipBillPayEWRFee(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN02_ShipBillPayEWRFee", TestDataInsight, "Canada");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN02_ShipBillPayEWRFee", TestDataInsight,
					"Canada", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
							+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
					"");

			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("ContactName"));
			cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
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
			fnCloseTest();

		}
	}

	// #############################################################################################################
	// # Name of the Test : CAN07_CartBasic
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void CAN07_CartBasic(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN07_CartBasic", TestDataInsight, "Canada");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN07_CartBasic", TestDataInsight, "Canada",
					intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
							+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
					"");

			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("ContactName"));
			cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
			cmtLib.clickOnloginAs();
			switchToChildWindow();
			commonLib.searchProduct(data.get("Search_Item"));
			commonLib.addToCartAndVerify();

			commonLib.continueToShopping();
			commonLib.clickCart();
			cartLib.verifyItemInCart(data.get("Search_Item"));
			// Adding second product to cart
			commonLib.searchProduct(data.get("Search_Item1"));
			commonLib.addFirstDisplyedItemToCartAndVerify();
			commonLib.continueToShopping();
			commonLib.clickCart();

			// update quantity
			commonLib.updateCartQuantity(data.get("quantity"));

			// updating the quantity by 0 and abc
			commonLib.updateCartQuantityByZero(data.get("quantity1"));
			commonLib.updateCartQuantityByZero(data.get("quantity2"));
			commonLib.emptyCartAndVerify();
			commonLib.clickLogOutLink(data.get("Logout_Header"));

			// Login change
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp1"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
			cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
			cmtLib.clickOnloginAs();
			switchToChildWindow();
			canadaLib.verifyCanadaWebgroup();
			commonLib.searchProduct(data.get("Search_Item"));
			commonLib.addToCartAndVerify();

			commonLib.continueToShopping();
			commonLib.clickCart();
			cartLib.verifyItemInCart(data.get("Search_Item"));
			commonLib.verifyProceedToCheckOutIsNotVisible();
			commonLib.clickLogOutLink(data.get("Logout_Header"));
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
			cmtLib.searchForWebGroup(data.get("WebGrp1"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
			cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
			cmtLib.clickOnloginAs();
			switchToChildWindow();
			commonLib.searchProduct(data.get("Search_Item"));
			commonLib.addToCartAndVerify();

			commonLib.continueToShopping();
			commonLib.clickCart();
			cartLib.verifyItemInCart(data.get("Search_Item"));
			commonLib.verifyProceedToCheckOut();
			commonLib.clickLogOutLink(data.get("Logout_Header"));
			fnCloseTest();

		}
	}

	// #############################################################################################################
	// # Name of the Test : CAN13_CartBundles
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : 
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void CAN13_CartBundles(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN13_CartBundles", TestDataInsight, "Canada");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN13_CartBundles", TestDataInsight, "Canada",
					intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
							+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
					"");
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			CartLib cartLib = new CartLib();
			OrderLib orderLib = new OrderLib();
			ShipBillPayLib shipbLib = new ShipBillPayLib();
			CanadaLib canadaLib = new CanadaLib();
			SearchLib searchLib = new SearchLib();

			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("ContactName"));
			cmtLib.clickOnloginAs();
			switchToChildWindow();
			canadaLib.verifyCanadaWebgroup();
			commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"),
					data.get("Tools_Menu_DD"), data.get("Product_Group"), data.get("Product_Name"));
			searchLib.clickAddToOrderOnCompanyStandardsScreen();
			commonLib.clickCart();
			commonLib.verifyBundleIsAddedToCart();
			canadaLib.verifyAvailabilityOfTheProductInCart();
			shipbLib.verifyPriceIsCAD(data.get("CANDAIAN_DOLLAR"));
			commonLib.clickLogOutLink(data.get("Logout_Header"));
			fnCloseTest();
		}
	}
	
	// #############################################################################################################
		// # Name of the Test : CAN04_ShipBillPayFreight
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : 
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void CAN04_ShipBillPayFreight(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN04_ShipBillPayFreight", TestDataInsight, "Canada");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN04_ShipBillPayFreight", TestDataInsight, "Canada",
						intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
				reporter.SuccessReport("Iteration Number : ",
						"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
								+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
						"");
				CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
				CartLib cartLib = new CartLib();
				OrderLib orderLib = new OrderLib();
				ShipBillPayLib shipbLib = new ShipBillPayLib();
				CanadaLib canadaLib = new CanadaLib();
				SearchLib searchLib = new SearchLib();
				
				cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
						data.get("ContactName"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
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
				commonLib.clickLogOutLink(data.get("Logout_Header"));
			}
		}

		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void CAN11_SLPProrationMicrosoft(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN01_ShipBillPayTax", TestDataInsight, "Canada");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN11_SLPProrationMicrosoft", TestDataInsight,
						"Canada", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
				reporter.SuccessReport("Iteration Number : ",
						"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
								+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
						"");
				CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
				CartLib cartLib = new CartLib();
				OrderLib orderLib = new OrderLib();
				CanadaLib canadaLib = new CanadaLib();

				cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
						data.get("ContactName"));
				cmtLib.clickOnloginAs();
				switchToChildWindow();

				canadaLib.CandaHomePageVerification();
				Thread.sleep(2000);
				// SelectSoftwareLicAgrements
				commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
				canadaLib.selectProdCheckbox();
				canadaLib.verifyProductsVisible();
				// search product
				commonLib.searchProduct(data.get("Productname"));
				//commonLib.spinnerImage();
				Thread.sleep(2000);
				canadaLib.verifyProductPrice();
				Thread.sleep(2000);
				commonLib.addToCartAndVerify();
				String partNumber1 = cartLib.getPartNumber();
				canadaLib.continueToCheckout();
				// verify prorated price display
				canadaLib.verifyProratedPrice();
				// proceed to checkout
				orderLib.proceedToCheckout();
				// ******** Click continue on Line level Info, Ship and Bill pay
				// sections ********************//
				orderLib.clickContinueOnLineLevelInfo();
				orderLib.shippingBillPayContinueButton();
				Thread.sleep(1000);
				orderLib.shippingBillPayContinueButton();
				Thread.sleep(1000);
				// Select Terms- payment method and click Review Order
				orderLib.selectPaymentMethod(data.get("Payment_method"));
				// Verify Ship Bill Page
				canadaLib.veriyProratedPriceinSBP();
				// verify subtotal(prorated price), verify receipt, order and date
				String summaryAmount = cartLib.getSummaryAmountInCart();
				canadaLib.verifyReceiptOrderAndDate(summaryAmount);
				commonLib.clickLogOutLink(data.get("Logout_Header"));

			}
		}
		
		public void CAN14_NoDiscoverCard(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN14_NoDiscoverCard", TestDataInsight, "Canada");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN14_NoDiscoverCard", TestDataInsight,
						"Canada", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
				reporter.SuccessReport("Iteration Number : ",
						"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
								+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
						"");
				CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
				CartLib cartLib = new CartLib();
				OrderLib orderLib = new OrderLib();
				CanadaLib canadaLib = new CanadaLib();

				cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
						data.get("ContactName"));
				cmtLib.clickOnloginAs();
				switchToChildWindow();

				canadaLib.CandaHomePageVerification();
				Thread.sleep(2000);
				// SelectSoftwareLicAgrements
				commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
				canadaLib.selectProdCheckbox();
				canadaLib.verifyProductsVisible();
				// search product
				commonLib.searchProduct(data.get("Productname"));
				commonLib.spinnerImage();
				canadaLib.verifyProductPrice();
				Thread.sleep(2000);
				commonLib.addToCartAndVerify();
				String partNumber1 = cartLib.getPartNumber();
				canadaLib.continueToCheckout();
				// verify prorated price display
				canadaLib.verifyProratedPrice();
				// proceed to checkout
				orderLib.proceedToCheckout();
				// ******** Click continue on Line level Info, Ship and Bill pay
				// sections ********************//
				orderLib.clickContinueOnLineLevelInfo();
				orderLib.shippingBillPayContinueButton();
				orderLib.shippingBillPayContinueButton();
				// Select Terms- payment method and click Review Order
				orderLib.selectPaymentMethod(data.get("Payment_method"));
				// Verify Ship Bill Page
				canadaLib.veriyProratedPriceinSBP();
				// verify subtotal(prorated price), verify receipt, order and date
				String summaryAmount = cartLib.getSummaryAmountInCart();
				canadaLib.verifyReceiptOrderAndDate(summaryAmount);
				commonLib.clickLogOutLink(data.get("Logout_Header"));

			}
		}

}
