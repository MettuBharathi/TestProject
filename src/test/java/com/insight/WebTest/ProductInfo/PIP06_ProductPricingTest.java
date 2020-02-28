package com.insight.WebTest.ProductInfo;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class PIP06_ProductPricingTest extends ProductDisplayInfoLib{

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();


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
		public void TC_PIP06(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PIP06_ProductPricing", TestData, "Web_Product_Info");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("PIP06_ProductPricing", TestData, "Web_Product_Info", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("Product Pricing");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
								"");

						/*reporter.SuccessReport("Iteration Number : ", "**************Iteration Number::  " + intCounter
								+ " To validate:: Favorites_Logged In User_Verify user is able to Add products to favorites_Remove Favorites in PLP/PDP **************");*/



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
						//fnCloseTest();
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("PIP", "PIP06", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("PIP", "PIP06", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
			/*finally {
				Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
			}*/
			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}


