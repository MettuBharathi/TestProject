package com.insight.WebTest;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SearchTest extends SearchLib {
	
	ProductDisplayInfoLib prodInfoLib=new ProductDisplayInfoLib();
	CMTLib cmtLib=new CMTLib();

	//#############################################################################################################
	//#		Name of the Test			:	   SER03_Compare
	//#		Migration Author 	        : 	   Cigniti Technologies
	//#		
	//#		Date of Migration			:      August 2019
	//#		DESCRIPTION 				:	   Purpose of this test method is to verify the compare functionality in the products display page.
	//#		Parameters                  :      StartRow ,EndRow , nextTestJoin
	//#      												
	//###############################################################################################################
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void SER03_Compare(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER03_Compare", TestData, "Web_Search");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SER03_Compare", TestData, "Web_Search", intCounter);
			ProductDisplayInfoLib prodInfoLib=new ProductDisplayInfoLib();

			// Test Steps execution
			try {
				fnOpenTest();
				navigateToProductSearchResultsAndSearchProduct(data.get("HeaderName"), data.get("HeaderList"), data.get("ProductType"), data.get("ProductName"));
				prodInfoLib.clickOnCompareSimilarLink();
				verifyTheProductNameInCompareSimilarProductsPage(data.get("ProductName"));
				navigateToBackPage();
				int itemscount = Integer.valueOf(data.get("Items_Count"));
				clickOnAddToMyCompareListLink(itemscount);
				//Verify  products added to list
				String compareNum=verifyCompareList();
				assertTextStringMatching(compareNum, data.get("Items_Count"));
				clickOnComparedItemsLink();
				clickOnCloseIconInCompareProductsPage();
				prodInfoLib.addToCart();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
			fnCloseTest();
			}
		}
	}
	
	// #############################################################################################################
	// #   Name of the Test               : SER06_KeywordSearch
	// #   Migration Author               : Cigniti Technologies
	// #
	// #   Date of Migration              : August 2019
	// #   DESCRIPTION                    : Purpose of this test case is to perform keyword search and verify the filters operation in the Product details page.
	// #   Parameters                     : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void SER06_KeywordSearch(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER06_KeywordSearch", TestData, "Web_Search");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SER06_KeywordSearch", TestData, "Web_Search", intCounter);
			
			
			// Test Steps execution
			fnOpenTest();
			searchInHomePage(data.get("SearchText"));
			verifyTheResultsForSearchTerm(data.get("SearchText"));
			filterSelectionInProductsSearchPage(data.get("Category"));
			filterSelectionInProductsSearchPage(data.get("Manufacturer1"));

			// Logging into CMT tool
			cmtLib.loginToCMT(data.get("Login"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
			cmtLib.clickApproveItemCatlog();
			IncludeManufacturersInCatlogAndUpdate(data.get("Mfrs_Type"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Users"));

			cmtLib.loginAsAdminCMT();
			clickOnSecondaryDDAndSelectListitem(data.get("HeaderName"), data.get("HeaderList"));
			filterSelectionInProductsSearchPage(data.get("Manufacturer2"));
			prodInfoLib.enterPriceDetailsFilters(data.get("Min_Price"), data.get("Max_Price"));
			fnCloseTest();
		}
	}

    	// #############################################################################################################
		// #   Name of the Test               : SER07_MenuSearch
		// #   Migration Author               : Cigniti Technologies
		// #
		// #   Date of Migration              : August 2019
		// #   DESCRIPTION                    : This method is to navigate to the Shop All Products / Shop All Brands and
	    // #                                      select the particular category and verify navigations and menus.
		// #   Parameters                     : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################	
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void SER07_MenuSearch(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER07_MenuSearch", TestData, "Web_Search");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SER07_MenuSearch", TestData, "Web_Search", intCounter);
			TestEngineWeb.reporter.initTestCaseDescription("MenuSearch");
			// Test Steps execution
			fnOpenTest();
			clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAll"));
			verifyMenusInShopAllProductsPage(data.get("Menus"));
			clickMenuItemCategoryInShopAllProductsPage(data.get("MenuItem"), data.get("Category"));
			selectTheProductByTypeAndVerifyNavigation(data.get("ProductType"));

			clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAllBrands"));
			selectTopBrandsInShopAllBrandsPage(data.get("Brand"), data.get("Url"));

			clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAllBrands"));
			selectBrandByAlphabetOrderSection(data.get("Url2"),data.get("Brand"));
			fnCloseTest();
		}
	}

	
	// #############################################################################################################
	// #    Name of the Test         : SER10_SearchResults
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : August 2019
	// #    DESCRIPTION              : This method is to perform search operations in the Search results screen using filters.
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void SER10_SearchResults(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER10_SearchResults", TestData, "Web_Search");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SER10_SearchResults", TestData, "Web_Search", intCounter);
			
			
			// Test Steps execution
			fnOpenTest();
			searchInHomePage(data.get("SearchText"));
			verifyTheResultsForSearchTerm(data.get("SearchText"));

			searchInHomePage(data.get("SearchText2"));
			verifyTheResultsForSearchTerm(data.get("SearchText2"));
			filterSelectionInProductsSearchPage(data.get("StockFilter"));
			removeTheFilterForInStockOnly(data.get("In_Stock"));

			filterSelectionInProductsSearchPage(data.get("MnfrFilter"));
			removeTheFilter(data.get("MnfrFilter"));
			prodInfoLib.enterPriceDetailsFilters(data.get("Min_Price"), data.get("Max_Price"));

			searchProductInProductDisplayPage(data.get("ProductName"));
			removeTheFilter(data.get("ProductName"));

			searchInHomePage(data.get("SearchText_adobe"));
			verifyTheResultsForSearchTerm(data.get("SearchText_adobe"));
			prodInfoLib.selectFirstProductAndReturnBack();
			prodInfoLib.selectFirstProductImageAndReturnBack();
			prodInfoLib.verifyTheSearchResultsDisplayed();
			prodInfoLib.selectSortByOptions(data.get("Sort_By"));
			fnCloseTest();

		}
	}
	
	    // #############################################################################################################
		// #    Name of the Test         : SER08_ProductResearchRequest
		// #    Migration Author         : Cigniti Technologies
		// #
		// #    Date of Migration        : August 2019
		// #    DESCRIPTION              : This method is to perform search operations in the Product Research Request page.
		// #    Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// #############################################################################################################
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void SER08_ProductResearchRequest(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER08_ProductResearchRequest", TestData, "Web_Search");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SER08_ProductResearchRequest", TestData, "Web_Search", intCounter);
			
			// Test Steps execution
			fnOpenTest();

			// Logging into CMT tool
			cmtLib.loginToCMT(data.get("Login"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));

			// Login As into UAT
			cmtLib.loginAsAdminCMT();
			searchInHomePage(data.get("SearchText"));
			verifyTheResultsForSearchTerm(data.get("SearchText"));
			prodInfoLib.clickProductResearchRequest();
			prodInfoLib.clickSendWithoutFillingRequestProductAndVerify(data.get("SearchText"));
			prodInfoLib.clickProductResearchRequestAndFillDetails(data.get("Name"), data.get("Email"), data.get("Country"),data.get("Quantity"), data.get("PartNo."), data.get("Mnfr_Name"),data.get("Prod_Desc"));

			// go back to CMT tool 
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));

			cmtLib.loginAsAdminCMT();
			searchInHomePage(data.get("SearchText"));
			verifyTheResultsForSearchTerm(data.get("SearchText"));
			prodInfoLib.clickProductResearchRequest();
			prodInfoLib.clickProductResearchRequestAndFillDetails(data.get("Name"), data.get("Email"), data.get("Country"),data.get("Quantity"), data.get("PartNo."), data.get("Mnfr_Name"),data.get("Prod_Desc"));
			fnCloseTest();
		}
	}
	
	// #############################################################################################################
	// #    Name of the Test         : SER04_CompanyStandards
	// #    Migration Author         : Cigniti Technologies
    // #
	// #    Date of Migration        : August 2019
	// #    Description              : This method is used to Test Company Standards.
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

   @Parameters({ "StartRow", "EndRow", "nextTestJoin" })
   @Test
   public void SER04_CompanyStandards(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
	int intStartRow = StartRow;
	int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER04_CompanyStandards", TestData, "Web_Search");
	for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

		// initilizing libraries and testdata
		ReportStatus.fnDefaultReportStatus();
		ReportControl.intRowCount = intCounter;
		Hashtable<String, String> data = TestUtil.getDataByRowNo("SER04_CompanyStandards", TestData, "Web_Search", intCounter);
		
		// Test Steps execution
		fnOpenTest();
		
		// Logging into CMT tool
		// Login to CMT enable Open Market and Contracts/Agencies are enabled by default

		     cmtLib.loginToCMT(data.get("Login"));
		     cmtLib. searchForWebGroup(data.get("WebGrp"));
		     cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
		     cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			 cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
			 cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
			 cmtLib.loginAsAdminCMT();
			 
		// Back to UAT 	verify company standards
			 selectAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"),data.get("Product_Group"),data.get("Product_Name"));
			 selectNewHireStandardAndverifyCart(data.get("Product_Group2"),data.get("Product_Name2"),data.get("Product_Grp_Columns"));
			 selectNewHireStandardAndverifyCart(data.get("Product_Group3"),data.get("Product_Name3"),data.get("Product_Grp_Columns"));
			 selectProductGroupAndVerify(data.get("Product_Group3"),data.get("Product_Name4"));
			 searchInHomePage(data.get("SearchText"));
			 selectFirstProductVerifyAddToCompanyStandardsLink();
			 fnCloseTest();

	}
}

    // #############################################################################################################
	// #    Name of the Test         : SER14_ValidateSubTabsInSearchResults
	// #    Migration Author         : Cigniti Technologies
    // #
	// #    Date of Migration        : August 2019
	// #    Description              : This method To Test Menu Search.
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

   @Parameters({ "StartRow", "EndRow", "nextTestJoin" })
   @Test
   public void SER14_ValidateSubTabsInSearchResults(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
	int intStartRow = StartRow;
	int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER14_SubTabs_SearchResults", TestData, "Web_Search");
	for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

		// initilizing libraries and testdata
		ReportStatus.fnDefaultReportStatus();
		ReportControl.intRowCount = intCounter;
		Hashtable<String, String> data = TestUtil.getDataByRowNo("SER14_SubTabs_SearchResults", TestData, "Web_Search", intCounter);
		
		// Test Steps execution
		fnOpenTest();
		
		//Login to CMT tool
		cmtLib.loginToCMT(data.get("Login"));
		cmtLib.searchForWebGroup(data.get("WebGrp"));
		cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
		cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
		cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
		cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
		
		// Login AS to UAT
		cmtLib.loginAsAdminCMT();
		verifyMenusDisabledOnHomePage(data.get("HeaderName"),data.get("headerlist"),data.get("ShopAllBrands"));
		
		//Navigate Back to CMT tool
		cmtLib.navigateBackToCMT();
		cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
		cmtLib.loginAsAdminCMT();
		
		// login As to UAT
		verifyMenuEnabledOnHomeScreen(data.get("HeaderName"),data.get("headerlist"),data.get("ShopAllBrands"));
		fnCloseTest();   // end of test
	}
  }
   
    // #############################################################################################################
	// #    Name of the Test         : SER11_SearchSuggestions
	// #    Migration Author         : Cigniti Technologies
    // #
	// #    Date of Migration        : August 2019
	// #    Description              : This method is used to Test Search Suggestions.
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

   @Parameters({ "StartRow", "EndRow", "nextTestJoin"})
   @Test
   public void SER11_SearchSuggestions(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
	
	    int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER11_SearchSuggestions", TestData, "Web_Search");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SER11_SearchSuggestions", TestData, "Web_Search", intCounter);
			
		// Test Steps execution
		fnOpenTest();
			
		searchInHeaderSelectFromSuggestions(data.get("SearchText"));

		//login to CMT
		cmtLib.loginToCMTSelectUserAndLoginAS(data.get("Login"), data.get("WebGrp"), data.get("WebGrp_Name"), data.get("Manage_Web_Grp_Options"), data.get("LnameEmailUname"), data.get("ContactName"));
		
		// Navigate back to UAT
		//Enable Show search Suggestions and verify
		enableOrDisableSearchSuggestions(data.get("FavoritesTabName"), data.get("FavoritesTabName1"),data.get("SearchText"));
		
		//Disable Show search Suggestions and verify
		enableOrDisableSearchSuggestions(data.get("FavoritesTabName"),data.get("FavoritesTabName1"), data.get("SearchText"));
		
		//selectAccountTools(data.get("FavoritesTabName"),data.get("FavoritesTabName1"));
		fnCloseTest();   // end of test
		}
  }
       
    // ############################################################################################################
	// #    Name of the Test         : SER09_PersonalProductListSearchResults
	// #    Migration Author         : Cigniti Technologies
    // #
	// #    Date of Migration        : August 2019
	// #    Description              : This Test is used to Test  Product Center Search Results
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
      
     @Parameters({ "StartRow", "EndRow", "nextTestJoin"})
     @Test
     public void SER09_PersonalProductListSearchResults(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
       
    	 int intStartRow = StartRow;
 		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER09_PersonalProductListSearchResults", TestData, "Web_Search");
 		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

 			// initilizing libraries and testdata
 			ReportStatus.fnDefaultReportStatus();
 			ReportControl.intRowCount = intCounter;
 			Hashtable<String, String> data = TestUtil.getDataByRowNo("SER09_PersonalProductListSearchResults", TestData, "Web_Search", intCounter);
 			
 		 // Test Steps execution
 		    fnOpenTest(); 
 		   
 		 // Selecting the type of the product and verifying navigation
 		    navigateToProductSearchResultsAndSearchProduct(data.get("HeaderName"), data.get("HeaderList"), data.get("ProductType"), data.get("ProductName"));
		    prodInfoLib.selectProductAndverifyPersonalProductListLinkPresent();   // Add to Personal product list link should not display.
		   
		 //login to CMT
		    cmtLib.loginToCMTSetPermissions(data.get("Login"), data.get("WebGrp"), data.get("WebGrp_Name"), data.get("Manage_Web_Grp_Options"), data.get("LnameEmailUname"), data.get("ContactName"),data.get("Menu_Name"), data.get("Set_Permission"));
			
		 //Back to UAT 
			navigateToProductSearchResultsAndSearchProduct(data.get("HeaderName"), data.get("HeaderList"), data.get("ProductType"), data.get("ProductName"));
		    prodInfoLib.selectProductAndverifyPersonalProductListLinkPresent();  // Add to Personal product list link should display after setting in CMT
		    prodInfoLib.addItemsToProductList(data.get("Part_Number"));
		    prodInfoLib.addToCartAndVerify(data.get("Part_Number"));
		    fnCloseTest();   // end of test
		}
	}
     
     
    // ############################################################################################################
	// #    Name of the Test         : SER13_IPSPersonalProductListSearchResults
	// #    Migration Author         : Cigniti Technologies
    // #
	// #    Date of Migration        : August 2019
	// #    Description              : This Test is used to Test  Product Center Search Results
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
     
     @Parameters({ "StartRow", "EndRow", "nextTestJoin"})
     @Test
     public void SER13_IPSPersonalProductListSearchResults(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

    	 int intStartRow = StartRow;
 		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER13_IPSPersonalProductListSearchResults", TestData, "Web_Search");
 		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

 			// initilizing libraries and testdata
 			ReportStatus.fnDefaultReportStatus();
 			ReportControl.intRowCount = intCounter;
 			Hashtable<String, String> data = TestUtil.getDataByRowNo("SER13_IPSPersonalProductListSearchResults", TestData, "Web_Search", intCounter);
 			
 		 // Test Steps execution
 		    fnOpenTest(); 
 		   
 		 // Selecting the type of the product and verifying navigation
 		    navigateToProductSearchResultsAndSearchProduct(data.get("HeaderName"), data.get("HeaderList"), data.get("ProductType"), data.get("ProductName"));
		    prodInfoLib.selectProductAndverifyPersonalProductListLinkPresent();   // Add to Personal product list link should not display.
		   
		 //login to CMT
		    cmtLib.loginToCMTSetPermissions(data.get("Login"), data.get("WebGrp"), data.get("WebGrp_Name"), data.get("Manage_Web_Grp_Options"), data.get("LnameEmailUname"), data.get("ContactName"),data.get("Menu_Name"), data.get("Set_Permission"));
			
		 //Back to UAT 
			navigateToProductSearchResultsAndSearchProduct(data.get("HeaderName"), data.get("HeaderList"), data.get("ProductType"), data.get("ProductName"));
		    prodInfoLib.selectProductAndverifyPersonalProductListLinkPresent();  // Add to Personal product list link should display after setting in CMT
		    prodInfoLib.addItemsToProductList(data.get("Part_Number"));
		    prodInfoLib.addToCartAndVerify(data.get("Part_Number"));
		    fnCloseTest();   // end of test
		} 
     }
     
    
    // #############################################################################################################
 	// #    Name of the Test         : SER16_SearchResults-PermissionsAndContractsIPS
 	// #    Migration Author         : Cigniti Technologies
    // #
 	// #    Date of Migration        : August 2019
 	// #    Description              : Web - Search - Logged in IPS - default permissions
 	// #    Parameters               : StartRow ,EndRow , nextTestJoin
 	// #
 	// ############################################################################################################# 

     @Parameters({ "StartRow", "EndRow", "nextTestJoin"})
     @Test
     public void SER16_SearchResultsPermissionsAndContractsIPS(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
       
    	int intStartRow = StartRow;
 		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER16_SearchResultsPermissionsAndContractsIPS", TestData, "Web_Search");
 		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

 			// initilizing libraries and testdata
 			ReportStatus.fnDefaultReportStatus();
 			ReportControl.intRowCount = intCounter;
 			Hashtable<String, String> data = TestUtil.getDataByRowNo("SER16_SearchResultsPermissionsAndContractsIPS", TestData, "Web_Search", intCounter);
 			
 		 // Test Steps execution
 		    fnOpenTest(); 
 		   
 		// Login to CMT enable Open Market and Contracts/Agencies are enabled by default
			cmtLib.loginToCMTSetPermissions(data.get("Login"), data.get("WebGrp"), data.get("WebGrp_Name"),
				data.get("Manage_Web_Grp_Options"), data.get("LnameEmailUname"), data.get("ContactName"),
				data.get("Menu_Name"), data.get("Set_Permission"));
			
		//Back to UAT and verify the above enabled settings
			verifyContractAllDisplayed();
			searchInHomePage(data.get("SearchText"));
			verifyTheResultsForSearchTerm(data.get("SearchText"));
			clickMorePricesAndViewContracts();
			VerifyDefaultUSDContractPrice();
			selectNewcontract(data.get("Contract_Name"));
			searchInHomePage(data.get("SearchText"));
			verifyTheResultsForSearchTerm(data.get("SearchText"));
			prodInfoLib.verifyContractNameInProdDetailsPageAndAddToCart(data.get("Contract_Name"),data.get("Quantity"));
			        prodInfoLib.verifyContractInCartScreen(data.get("Contract_Name"));
			cmtLib.navigateBackToCMT();
			
		// Navigate to CMT and Enable "Remove US Communities as Default Contract" setting
			cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
			cmtLib.permissionFromDD(data.get("Set_Permission3"), data.get("Permission_Drop_Down"));
			
		// Navigate to UAT and verify the USC price is not displayed as default for products
			cmtLib.loginAsAdminCMT();
			verifyContractAllDisplayed();
			searchInHomePage(data.get("SearchText"));
			verifyTheResultsForSearchTerm(data.get("SearchText"));
			verifyUSCcontractNotPresent();   
			clickMorePricesAndViewContracts();
			cmtLib.navigateBackToCMT();
			
		// Navigate back to CMT and Disable Open Market
			cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
			
		// Verify the changes effected.
			cmtLib.loginAsAdminCMT();
			verifyContractAllDisplayed();
			searchInHomePage(data.get("SearchText"));
			verifyTheResultsForSearchTerm(data.get("SearchText"));
			verifyUSCcontractNotPresent();
			
		// Navigate to CMT, enable Open Market - Your Price and disable fed_view_contracts
			cmtLib.navigateBackToCMT();
			cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
			cmtLib.permissionFromDD(data.get("Set_Permission3"), data.get("Permission_Drop_Down"));
			cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission4"));
			
	   // Verify that "Your Price" for all parts in search results page
			cmtLib.loginAsAdminCMT();
			searchInHomePage(data.get("SearchText"));
			verifyTheResultsForSearchTerm(data.get("SearchText"));
			prodInfoLib.verifyYourPriceExists();
			
	   // Navigate to CMT, fed_open_market;on and "fed_view_contracts;on"
			cmtLib.navigateBackToCMT();
			 String[] permissions=data.get("Set_Permission5").split(","); 
			 for(i=0;i<permissions.length;i++){
				   cmtLib.setPermissions(data.get("Menu_Name"),permissions[i]); 
			     }
			cmtLib.loginAsAdminCMT();
			verifyContractAllDisplayed();
			selectNewcontract(data.get("Contract_Name"));
			fnCloseTest();   // end of test
			
 		}
     }
     
}

