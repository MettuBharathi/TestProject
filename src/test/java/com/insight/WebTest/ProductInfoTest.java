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
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class ProductInfoTest extends ProductDisplayInfoLib {

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();

	// #############################################################################################################
	// #    Name of the Test              : PIP01_Availability
	// #    Migration Author              : Cigniti Technologies
	// #
	// #    Date of Migration             : October 2019
	// #    DESCRIPTION                   : Purpose of this test method is to test Part Availability
	// #    Parameters                    : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void PIP01_Availability(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PIP01_Availability", TestData, "Web_Product_Info");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			//  initializing libraries and test data
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("PIP01_Availability", TestData, "Web_Product_Info",intCounter);
			TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase : PIP_Availability");
			// Test Steps execution
			fnOpenTest(); 

			// Login to CMT
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));

			// Login as to UAT
			cmtLib.loginAsAdminCMT();

			// search for a product and verify the stock number in the search
			// results and product details page
			searchLib.searchInHomePage(data.get("SearchText1"));
			verifyProductStockNumberInSearchResultsPage();
			String StockNumber1 = getStockNumebrOfFirstProductInSearchResults();
			cartLib.selectFirstProductDisplay();
			verifyStockNumberInProductDetailsPage(StockNumber1);

			// Search for another product
			searchLib.searchInHomePage(data.get("SearchText2"));
			verifyProductStockNumberInSearchResultsPage();
			String StockNumber2 = getStockNumebrOfFirstProductInSearchResults();
			cartLib.selectFirstProductDisplay();
			verifyStockNumberInProductDetailsPage(StockNumber2);

			commonLib.clickOnInsightLogoOnHomePage();
			// navigate to company standards and select product group
			commonLib.clickOnAccountToolsMenuName(data.get("toolsMenuName"));
			commonLib.clickOnAccountToolsMenuDropDown(data.get("toolsMenuName"), data.get("dropDown"));
			commonLib.clickOnProductGrpInCompanyStandard(data.get("productGroup"), data.get("productName"));

			// Verifying stock availability in company standards
			verifyStockNumberInCompanyStandardsProductGroup();

			// Log out
			commonLib.clickLogOutLink(data.get("Logout"));

			// Close test
			fnCloseTest();
		}
	}

	// #############################################################################################################
	// #      Name of the Test        : PIP02_InventoryBlowout
	// #      Migration Author        : Cigniti Technologies
	// #
	// #      Date of Migration       : October 2019
	// #      DESCRIPTION             : Purpose of this test method is to test InventoryBlowout
	// #      Parameters              : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void PIP02_InventoryBlowout(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PIP02_InventoryBlowout", TestData, "Web_Product_Info");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			//  initializing libraries and test data
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("PIP02_InventoryBlowout", TestData,"Web_Product_Info", intCounter);

			// Test Steps execution
			fnOpenTest();

			// Navigate to Shop >> Technology deals and verify Inventory BlowOut In Technology Deals Page
			searchLib.clickOnSecondaryDDAndSelectListitem(data.get("Header"), data.get("Header_List"));
			verifyInventoryBlowOutInTechnologyDealsPage();
			
			// Verify Inventory blow out in product details page
			clickSeeDetailsVerifyInventoryBlowOutOfProductDetails();
			
			// End of test
			fnCloseTest();
		}

	}

	// #############################################################################################################
	// #     Name of the Test             : PIP03_PartDescriptions
	// #     Migration Author             : Cigniti Technologies
	// #
	// #     Date of Migration            : October 2019
	// #     DESCRIPTION                  : Purpose of this test method is to test PartDescriptions
	// #     Parameters                   : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void PIP03_PartDescriptions(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PIP03_PartDescriptions", TestData, "Web_Product_Info");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			//  initializing libraries and test data
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("PIP03_PartDescriptions", TestData,"Web_Product_Info", intCounter);
			TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase : PartDescriptions");
			// Test Steps execution
			fnOpenTest();

			// search for a part or a product
			searchLib.searchInHomePage(data.get("SearchText1"));
			String shortDesc=getFirstProdDescription();
			cartLib.selectFirstProductDisplay();
			
			// Verifying short description on product details page 
			verifyShortDescriptionOnProductDetailsPage(shortDesc);
			
			// Verifying Long description on product details page 
			verifyLongDescOnProductDetails();
			
			// search for a part or a product
			searchLib.searchInHomePage(data.get("SearchText2"));
			cartLib.selectFirstProductDisplay();
			
			// Verifying short and Long description on Accessories tab 
			clickOnAccessoriesTabInProductDetailsPage();
			verifyShortDescOnAccessoriesTab();
			verifyLongDescriptionOnAccessoriesTab();
			
		   // Verify the Most Often Purchased Item(s)
			verifyProductDescForMostOftenPurchasedItems();

			// End of test
			fnCloseTest();
		}
	}
	
	    // #############################################################################################################
		// #     Name of the Test             : PIP04_PartNumbers
		// #     Migration Author             : Cigniti Technologies
		// #
		// #     Date of Migration            : October 2019
		// #     DESCRIPTION                  : Purpose of this test method is to test Part Numbers
		// #     Parameters                   : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void PIP04_PartNumbers(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PIP04_PartNumbers", TestData, "Web_Product_Info");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				// initializing libraries and test data
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("PIP04_PartNumbers", TestData,"Web_Product_Info", intCounter);
				TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase : PartNumbers");
				
				// Test Steps execution
				fnOpenTest();
				
				// Login to CMT
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
                
				// Login as to UAT
				cmtLib.loginAsAdminCMT();
				cmtLib.loginVerification(data.get("ContactName"));
				
				// search for a product
				searchLib.searchInHomePage(data.get("SearchText"));
				// verifying manufacturer number in product details page
				verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchText"));
				// verifying manufacturer number in overview tab product details page.
				verifyManufacturerNumberInOverviewTab(data.get("SearchText"));
				
				// verify warranties part number in product details page
				clickOnWarrantiesTabOnProductDetailsPage();
				verifyMfrpartInWarrantiesTab();
				
				// verify accessories part number in product details page
				clickOnAccessoriesTabInProductDetailsPage();
				verifyMfrpartInAccessorirs();
				
				commonLib.clickOnInsightLogoOnHomePage();
				// navigate to company standards and select product group
				commonLib.clickOnAccountToolsMenuName(data.get("toolsMenuName"));
				commonLib.clickOnAccountToolsMenuDropDown(data.get("toolsMenuName"), data.get("dropDown"));
				commonLib.clickOnProductGrpInCompanyStandard(data.get("productGroup"), data.get("productName"));
				
				// Verify the part number and description in company standards screen
				verifyProductDescAndPartNumberInCompanyStandards();
				
				// End of test
				fnCloseTest();
				
			}
		}
	
		// #############################################################################################################
		// #     Name of the Test             : PIP05_ProductImage
		// #     Migration Author             : Cigniti Technologies
		// #
		// #     Date of Migration            : October 2019
		// #     DESCRIPTION                  : Purpose of this test method is to test Product Image
		// #     Parameters                   : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void PIP05_ProductImage(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PIP05_ProductImage", TestData, "Web_Product_Info");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				// initializing libraries and test data
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("PIP05_ProductImage", TestData,"Web_Product_Info", intCounter);
				TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase : ProductImage");
				
				// Test Steps execution
				fnOpenTest();
				
				// search for first product
				searchLib.searchInHomePage(data.get("SearchText1"));
				String imageSrc=verifyProductImage();
				cartLib.selectFirstProductDisplay();
				verifyFrontImageInProductDetailsPage();
				backToResultsProductDetailsPage();
				
				// search for second product
				searchLib.searchInHomePage(data.get("SearchText2"));
				String imageSrc2=verifyProductImage();
				cartLib.selectFirstProductDisplay();
				verifyLeftAngleImageInProductDetailsPage();
				backToResultsProductDetailsPage();
				cartLib.selectFirstProductDisplay();
				
				// Verify image in recently viewed products
				prodDetailsLib.Verifyrecentlyviwedproductslabel();
				scrollToBottom();
				verifyRecentlyViewedProductsImage(imageSrc);
				verifyRecentlyViewedProductsImage(imageSrc2);
				
				// End of test
				fnCloseTest();
			}
		}
		
	// #############################################################################################################
	// #       Name of the Test               : PIP06_ProductPricing
	// #       Migration Author               : Cigniti Technologies
	// #
	// #       Date of Migration              : October 2019
	// #       DESCRIPTION                    : Purpose of this test method is to test Product Pricing
	// #       Parameters                     : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void PIP06_ProductPricing(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PIP06_ProductPricing", TestData, "Web_Product_Info");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initializing libraries and test data
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("PIP06_ProductPricing", TestData, "Web_Product_Info",intCounter);
			TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase : ProductPricing");
			
			// Test Steps execution
			fnOpenTest();

			// search for first product
			searchLib.searchInHomePage(data.get("SearchText1"));
			String firstProdPrice = getFirtProductListPrice();
			String firstProdQty = getFirstProdQuantity();
			verifyTheProductPricesInSearchResultsPage();
			scrollUp();
			cartLib.selectFirstProductDisplay();
			verifyThePriceInProdDetailsPage(firstProdPrice); // Verifying price in product details page
			verifyQuantityInProdDetailsPage(firstProdQty); // Verifying quantity in product details page
			addToCartInProductDetailsPage(); // Add to cart
			orderLib.continueToCheckOutOnAddCart();
			
			// Login  as internal user
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
            
			// Login as to UAT
			cmtLib.loginAsAdminCMT();
			cmtLib.loginVerification(data.get("ContactName"));
			
			// search for first product
			searchLib.searchInHomePage(data.get("SearchText1"));
			verifyYourPriceExists();
			cartLib.selectFirstProductDisplay();
			verifyYourPriceInProductDetailsPage();
			
			clickOnWarrantiesTabOnProductDetailsPage();
			verifyPriceInWarrantiesTab();
			
			cmtLib.navigateBackToCMT();
			// Work with a different Master Group in Web Group Management Page
			cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
			cmtLib.searchForWebGroup(data.get("WebGrp1"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name1"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
			cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));;
			cmtLib.loginAsAdminCMT();
			
			// Select new contract  - Open Market
			selectNewcontract(data.get("Contract_Name1"));
			
			// search for first product  
			searchLib.searchInHomePage(data.get("SearchText2"));
			cartLib.selectFirstProductDisplay();
			verifyOpenMarketPriceExists();
			cartLib.selectFirstProductDisplay();
			verifyOpenMarketPriceInProductDetailsPage();
			
			// Select new contract
			searchLib.selectNewcontract(data.get("Contract_Name2"));
			searchLib.searchInHomePage(data.get("SearchText1"));
			verifyContractNameInProdDetailsPageAndAddToCart(data.get("Contract_Name2"),data.get("Quantity"));
			verifyContractInCartScreen(data.get("Contract_Name2"));
			commonLib.clickLogOutLink(data.get("Logout"));
			// End of test
			fnCloseTest();

		}
	}
}
