package com.insight.WebTest;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ActionEngine;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class ProductDetailTest extends ActionEngine {
	// #############################################################################################################
	// # Name of the Test : PID01_AddandDeleteToPersonalProductsList
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void PID01_AddandDeleteToPersonalProductsList(int StartRow, String EndRow, boolean nextTestJoin)
			throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID01_AddandDeleteToPersonalProductsList",
				TestDataInsight, "Product_Detail");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("PID01_AddandDeleteToPersonalProductsList",
					TestDataInsight, "Product_Detail", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
							+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
					"");
			CommonLib commonLib = new CommonLib(); 
			CMTLib cmtLib = new CMTLib();
			CartLib cartLib = new CartLib();
			ProductDetailLib productDetailLib = new ProductDetailLib();
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("ContactName"));
			cmtLib.clickOnloginAs();
			switchToChildWindow();
			commonLib.searchProduct(data.get("Search_Item"));
			cartLib.selectFirstProductDisplay();
			String partNumber = productDetailLib.getMFRNumberInProductInfopage();
			productDetailLib.selectProductAndAddToPersonalProductList();
			productDetailLib.verifyPersonalProductList(partNumber);
			productDetailLib.deletePersonalProductList(partNumber);
			productDetailLib.addItemsToProductListToPersonalProdcutList(data.get("Part_Number"));
			productDetailLib.deletePersonalProductList(data.get("Part_Number"));
			fnCloseTest();
		}
	}
	// #############################################################################################################
	// # Name of the Test : PID09_AddToCompareListFromPersonalProductsList
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void PID09_AddToCompareListFromPersonalProductsList(int StartRow, String EndRow, boolean nextTestJoin)
			throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID09_AddToCompareListFromPersonalProductsList",
				TestDataInsight, "Product_Detail");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("PID09_AddToCompareListFromPersonalProductsList",
					TestDataInsight, "Product_Detail", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
							+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
					"");
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			CartLib cartLib = new CartLib();

			ProductDetailLib productDetailLib = new ProductDetailLib();
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("ContactName"));
			cmtLib.clickOnloginAs();
			switchToChildWindow();
			commonLib.searchProduct(data.get("Search_Item"));
			cartLib.selectFirstProductDisplay();
			String partNumber = productDetailLib.getMFRNumberInProductInfopage();
			productDetailLib.selectProductAndAddToPersonalProductList();
			productDetailLib.verifyPersonalProductList(partNumber);
			productDetailLib.addItemsToProductListToPersonalProdcutList(data.get("Part_Number"));
			productDetailLib.clickAddtoCompareList();
			productDetailLib.clickOnAccountToolsAndClickOnProductGrp(data.get("ToolsMenu"), data.get("Dropdown"));
			productDetailLib.deletePersonalProductList(data.get("Part_Number"));
			productDetailLib.deletePersonalProductList(partNumber);

			fnCloseTest();
		}
	}

	// #############################################################################################################
	// # Name of the Test : PID06_StockLocations
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void PID06_StockLocations(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID06_StockLocations", TestDataInsight,
				"Product_Detail");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("PID06_StockLocations", TestDataInsight,
					"Product_Detail", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
							+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
					"");
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			CartLib cartLib = new CartLib();
			ProductDetailLib productDetailLib = new ProductDetailLib();
			commonLib.searchProduct(data.get("Search_Item"));
			productDetailLib.verifyAvailability();
			cartLib.selectFirstProductDisplay();
			productDetailLib.verifyAvailabilityInProductDetailPage();
			commonLib.searchProduct(data.get("Search_Item1"));
			productDetailLib.narrowDownFilters(data.get("Category"), data.get("Option"));
			commonLib.spinnerImage();
			productDetailLib.selectUnlimetedAvailabilityProduct();
			productDetailLib.verifyAvailabilityInProductDetailPage();
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("Contact_Name"));
			cmtLib.setWebGroupDefaultOption(data.get("WebGrp_Option"));
			cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
			cmtLib.clickOnloginAs();
			switchToChildWindow();

			productDetailLib.selectAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"),
					data.get("Tools_Menu_DD"), data.get("Product_Group"), data.get("Product_Name"));
			fnCloseTest();
		}
	}

	// #############################################################################################################
	// # Name of the Test : PID04_RecentlyViewedProducts
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void PID04_RecentlyViewedProducts(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID04_RecentlyViewedProducts", TestDataInsight,
				"Product_Detail");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			fnOpenTest();
			driver.manage().deleteAllCookies();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("PID04_RecentlyViewedProducts", TestDataInsight,
					"Product_Detail", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
							+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
					"");
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			CartLib cartLib = new CartLib();
			ProductDetailLib productdetLib = new ProductDetailLib();
			ProductDisplayInfoLib productDispinfoLib = new ProductDisplayInfoLib();
			productdetLib.getrecentlyvieweditems(data.get("Search_Item1"));
			// verify recently viewed as expected it should not be visible
			productdetLib.Verifyrecentlyviwedproductslabel();
			productdetLib.getrecentlyvieweditems(data.get("Search_Item2"));
			productdetLib.Verifyrecentlyvieweditems(); // verify recently
			// viewed
			productdetLib.getrecentlyvieweditems(data.get("Search_Item3"));
			String Productname = productdetLib.getrecentlyvieweditems(data.get("Search_Item3"));
			productdetLib.Verifyrecentlyvieweditems();
			// view details productdetLib.Clickonviewdetails(Productname);
			// product info page display
			productdetLib.verifyproductinfopage(data.get("Search_Item3"));
			// back to results productdetLib.Clickonbacktoresults();
			// productinfo page display-------------
			productdetLib.verifyproductinfopage(data.get("Search_Item3"));
			productdetLib.Verifyrecentlyvieweditems();
			// verify recently viewed----------
			productdetLib.getrecentlyvieweditems(data.get("Search_Item4"));
			productdetLib.Verifyrecentlyvieweditems();
			// verify recently viewed-------
			productdetLib.getrecentlyvieweditems(data.get("Search_Item5"));
			productdetLib.Verifyrecentlyvieweditems();
			// verify recently viewed--------
			productdetLib.getrecentlyvieweditems(data.get("Search_Item6"));
			productdetLib.Verifyrecentlyvieweditems();
			// verify recently viewed--------
			productdetLib.getrecentlyvieweditems(data.get("Search_Item7"));
			productdetLib.Verifyrecentlyvieweditems();
			// verify recently viewed--------
			productdetLib.getrecentlyvieweditems(data.get("Search_Item8"));
			productdetLib.Verifyrecentlyvieweditems();
			// Login
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("Contact_Name"));
			cmtLib.loginAsAdminCMT();
			commonLib.searchProduct(data.get("Search_Item4"));
			String MfrNum = productdetLib.getMfrpartnumofFirstproduct();
			cartLib.selectFirstProductDisplay();
			productDispinfoLib.verifyTheManufacturerNumberInProductDetailsPage(MfrNum);
			commonLib.addToCartAndVerify();
			commonLib.clickLogOutLink(data.get("Logout_Header"));
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			// Click Custom Catalog
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("CustomCatalog"));
			productdetLib.ClickonDeleteButtonofcustomcatalog();
			productdetLib.Createcustomcatalog();
			productdetLib.ClickonCustomcatalog(data.get("manufacturers"));
			productdetLib.Clickonmanufacturers(data.get("manufacturers1"));
			productdetLib.Clickonmanufacturers(data.get("manufacturers2"));
			productdetLib.Clickonmanufacturers(data.get("manufacturers3"));
			// Custom Catalog changes and update
			productdetLib.SelectOptionFromExculdeManufacturers(data.get("optionOKI"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
			cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
			cmtLib.loginAsAdminCMT();
			commonLib.searchProduct(data.get("Search_Item4"));
			cartLib.selectFirstProductDisplay();
			// verify recently viewed as expected it should not be visible
			// productdetLib.Verifyrecentlyviwedproductslabel();
			commonLib.searchProduct(data.get("Search_Item4"));
			cartLib.selectFirstProductDisplay();
			// verify recently viewed----
			commonLib.clickLogOutLink(data.get("Logout_Header"));
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("CustomCatalog"));
			// delete Custom Catalog
			productdetLib.ClickonDeleteButtonofcustomcatalog();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
			cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
			fnCloseTest();

		}
	}

	// #############################################################################################################
	// # Name of the Test : PID07_ProductDetailTabs
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void PID07_ProductDetailTabs(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID07_ProductDetailTabs", TestDataInsight,
				"Product_Detail");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("PID07_ProductDetailTabs", TestDataInsight,
					"Product_Detail", intCounter);

			// Test Steps execution
			fnOpenTest();
			CommonLib commonLib = new CommonLib();
			ProductDetailLib productdetLib = new ProductDetailLib();
			commonLib.searchProduct(data.get("Search_Item1"));
			productdetLib.verifyBreadcrumb();
			productdetLib.OverviewTab();
			commonLib.searchProduct(data.get("Search_Item2"));
			// Verify Specifications
			productdetLib.Getproductdetails();
			// Verify Accessories
			productdetLib.VerifyAddAccessories();
			// Update Qauntiy
			productdetLib.Verifyupdatequantity();
			fnCloseTest();
		}
	}

	// #############################################################################################################
	// # Name of the Test : PID08_TotalPriceEstimate
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void PID08_TotalPriceEstimate(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID08_TotalPriceEstimate", TestDataInsight,
				"Product_Detail");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("PID08_TotalPriceEstimate", TestDataInsight,
					"Product_Detail", intCounter);

			// Test Steps execution
			fnOpenTest();
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			CartLib cartLib = new CartLib();
			ProductDetailLib productdetLib = new ProductDetailLib();
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("ContactName"));
			cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
			cmtLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
			cmtLib.selectDefaultShippingOptionInCheckoutSettings(data.get("Default_Shipping_Option"));
			cmtLib.clickupdateatDefaultShippingOption();
			cmtLib.loginAsAdminCMT();
			productdetLib.verifytheLoginUser(data.get("LnameEmailUname"));
			commonLib.searchProduct(data.get("Search_Item"));
			cartLib.selectFirstProductDisplay();
			// verif estimated Price
			productdetLib.Estimatetotalprice(data.get("ZIPcode"));
			productdetLib.verifyEstimatedtax();
			productdetLib.verifyEstimatedshipping();
			productdetLib.verifyEstimatedPrice();
			commonLib.clickLogOutLink(data.get("Logout_Header"));
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
			cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
			cmtLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
			// disable permissions
			cmtLib.selectDefaultShippingOptionInCheckoutSettings(data.get("Default_Shipping_Option1"));
			cmtLib.clickupdateatDefaultShippingOption();
			fnCloseTest();

		}
	}

	// #############################################################################################################
	// # Name of the Test : PID05_RecommendedProducts
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void PID05_RecommendedProducts(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID05_RecommendedProducts", TestDataInsight,
				"Product_Detail");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("PID05_RecommendedProducts", TestDataInsight,
					"Product_Detail", intCounter);

			// Test Steps execution
			fnOpenTest();
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			CartLib cartLib = new CartLib();
			SearchLib searchLib = new SearchLib();
			ProductDetailLib productdetLib = new ProductDetailLib();
			commonLib.searchProduct(data.get("Search_Item"));
			cartLib.selectFirstProductDisplay();
			commonLib.addToCartAndVerify();
			commonLib.continueToShopping();
			commonLib.searchProduct(data.get("Search_Item1"));
			commonLib.addToCartAndVerify();
			commonLib.continueToShopping();
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("Contact_Name"));

			cmtLib.clickOnloginAs();
			switchToChildWindow();
			commonLib.searchProduct(data.get("Search_Item1"));

			// recomended product
			productdetLib.recomendedProductMoreAvailablePriceAndVerifyContracts();
			commonLib.searchProduct(data.get("Search_Item2"));
			cartLib.selectFirstProductDisplay();
			// Most often purchased product
			productdetLib.clickMostOftenPurchasedProduct();
			// cartLib.selectFirstProductDisplay();

			searchLib.selectNewcontract(data.get("Contract_Name"));
			productdetLib.verifyContractDetails();

			commonLib.searchProduct(data.get("Search_Item3"));
			cartLib.selectFirstProductDisplay();
			productdetLib.verifyContractInproductDetailPage();
			commonLib.clickLogOutLink(data.get("Logout_Header"));
		}
	}

	// #############################################################################################################
	// # Name of the Test : PID10_AddProductReviewsandRatingToPDP
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void PID10_AddProductReviewsandRatingToPDP(int StartRow, String EndRow, boolean nextTestJoin)
			throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID10_AddProductReviewsandRatingToPDP", TestDataInsight,
				"Product_Detail");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("PID10_AddProductReviewsandRatingToPDP",
					TestDataInsight, "Product_Detail", intCounter);

			// Test Steps execution
			fnOpenTest();
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			CartLib cartLib = new CartLib();
			ProductDetailLib productdetLib = new ProductDetailLib();
			commonLib.searchProduct(data.get("Search_Item"));
			cartLib.selectFirstProductDisplay();
			productdetLib.VerifyreviwsatProductDisplay();
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("Contact_Name"));
			cmtLib.loginAsAdminCMT();
			commonLib.searchProduct(data.get("Search_Item"));
			cartLib.selectFirstProductDisplay();
			productdetLib.verifyReviewtab();
			productdetLib.ToclickonReviews();
			productdetLib.FillReviewsubmissionform(data.get("title"), data.get("Text"), data.get("Nickname"));
			productdetLib.clicksubmitandverifyerrormsgreviewsubmissionform();
			productdetLib.VerifyreviwsatProductDisplay();
			commonLib.clickLogOutLink(data.get("Logout_Header"));
			fnCloseTest();

		}
	}
	
	// #############################################################################################################
		// # Name of the Test : PID02_Warranties
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void PID02_Warranties(int StartRow, String EndRow, boolean nextTestJoin)
				throws Throwable {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID02_Warranties", TestDataInsight,
					"Product_Detail");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				// initilizing libraries and testdata
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("PID02_Warranties",
						TestDataInsight, "Product_Detail", intCounter);

				// Test Steps execution
				fnOpenTest();
				CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
				CartLib cartLib = new CartLib();
				OrderLib orderLib=new OrderLib();
				ProductDetailLib productdetLib = new ProductDetailLib();
				ProductDisplayInfoLib productDisplayInfoLib=new ProductDisplayInfoLib();
				SearchLib searchLib=new SearchLib();
				cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
						data.get("Contact_Name"));

				cmtLib.clickOnloginAs();
				switchToChildWindow();
				
				commonLib.searchProduct(data.get("SearchItem1"));
				commonLib.addToCartAndVerify();
				orderLib.continueToCheckOutAddWarrantyAndVerifyTheCart(data.get("Warrenty_Part_Number"));
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				cmtLib.handleWelcomeToInsightBetaPopUp();
				commonLib.searchProduct(data.get("SearchItem2"));
				
				// verify warranties part number in product details page
				productDisplayInfoLib.clickOnWarrantiesTabOnProductDetailsPage();
				productDisplayInfoLib.verifyMfrpartInWarrantiesTab();
				String partNumber=productdetLib.getMFRNumberInProductInfopageInWarrentiesTab();
				
				productdetLib.updateQuantityInWarrentiesTab(data.get("quantity"));
				productdetLib.clickAddToCartInWarrientiesTab();
				commonLib.continueToShopping();
				commonLib.clickCart();
				cartLib.verifyItemInCart(partNumber);
				fnCloseTest();
				
			}
		}
}