package com.insight.WebTest.Canada;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN18_SearchIncludingFrenchTest extends  CanadaLib{
	
	ChinaLib chinaLib=new ChinaLib();
	SearchLib searchLib = new SearchLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	CartLib cartLib=new CartLib();
	CommonLib commonLib=new CommonLib();
	OrderLib orderLib=new OrderLib();
	ProductDetailLib prodLib=new ProductDetailLib();
	
			// #############################################################################################################
			// #       Name of the Test         :  CAN18_SearchIncludingFrench
			// #       Migration Author         :  Cigniti Technologies
			// #
			// #       Date of Migration        : November 2019
			// #       DESCRIPTION              : To Test basic Search Including French
			// #       Parameters               : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void TC_CAN18(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN18_SearchIncludingFrench", TestDataInsight, "Canada");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN18_SearchIncludingFrench", TestDataInsight, "Canada", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("SearchIncludingFrench");
	                    
							navigateToApplication("CANADA");
							// select language and country
							chinaLib.selectLanguageOnHomePage(data.get("Country"), data.get("Language"));
							verifyCountry(data.get("Country_Code"));
							
							
							// Search on HomePage
							searchLib.searchInHomePage(data.get("SearchText"));  // Workstations
							verifySortOption(data.get("Sort_Option")); // Meilleurs résultats
							
							verifyFilterByManufacturerOnSearchResultsPage();
							searchLib.filterSelectionInProductsSearchPage(data.get("Manufacturer_Name")); // HP INC
							pipLib.verifyManufacturerNameInsearchResults(data.get("Manufacturer_Name").replace("INC", "").trim());
							String firstProdPrice = pipLib.getFirtProductListPrice();
							cartLib.selectFirstProductDisplay();
							pipLib.verifyThePriceInProdDetailsPage(firstProdPrice); // Verifying price in product details page
							pipLib.backToResultsProductDetailsPage();
							
							// Click on add to my compare list  --  3 items to compare list
							int itemscount = Integer.valueOf(data.get("Items_Count"));
							searchLib.clickOnAddToMyCompareListLink(itemscount); 
							
							//Verify  products added to list
							String compareNum=searchLib.verifyFrenchCompareList();
							assertTextStringMatching(compareNum, data.get("Items_Count"));
							// click compare items link
							searchLib.clickOnComparedItemsLink();
							// verify products added in compare list
							chinaLib.verifyCompareProductsPage(itemscount);
							
							// Verify new part added >> L9K20UT#ABA
							chinaLib.addAnotherPartInCompareProductsPage(data.get("Part_Number"));
							chinaLib.verifyPartNumberAddedOnFrenchCompareProductListPage(data.get("Part_Number"));
							// Remove first product
							searchLib.clickOnCloseIconInCompareProductsPage();
							// verify product count after removal
							assertTextStringMatching(compareNum, data.get("Items_Count"));
							// add to cart 
							pipLib.addToCartForFrench();
					     	
							// End of test
							
						} catch (Exception e) {
							ReportStatus.blnStatus = false;
							gErrorMessage = e.getMessage();
							gTestStatus = false;
						}
						ReportControl.fnEnableJoin();
						ReportStatus.fnUpdateResultStatus("SearchIncludingFrench", "TC_CAN18", ReportStatus.strMethodName, intCounter, browser);
						fnCloseTest();
					}
				} catch (Exception e) {
					e.printStackTrace();
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
					ReportStatus.fnUpdateResultStatus("SearchIncludingFrench", "TC_CAN18", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}

				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

