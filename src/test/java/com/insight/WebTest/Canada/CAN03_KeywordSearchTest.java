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
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN03_KeywordSearchTest extends CanadaLib{

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ProductDetailLib productDetailLib = new ProductDetailLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  SER03_KeywordSearch
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This method is to verify Keyword Search for canada
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_CAN03(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN03_KeywordSearch", TestDataInsight, "Canada");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN03_KeywordSearch", TestDataInsight, "Canada",intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("KeywordSearch " );
						navigateToApplication("CANADA");;
						
						//	Search on HomePage
						searchLib.searchInHomePage(data.get("SearchText1"));
						verifySortOption(data.get("Sort_Options"));
						
						///	Search On Product List Page
						searchLib.searchInHomePage(data.get("SearchText2"));
						verifySortOption(data.get("Sort_Options"));
						
						//	Search On Product List Page
						searchLib.searchInHomePage(data.get("SearchText3"));
						verifySortOption(data.get("Sort_Options"));
						
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						
						// Disable -- custom_catalog;OFF";
					    cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
					    // Enable Shop by brand
					    cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
					    
					    // Login as to UAT
						cmtLib.loginAsAdminCMT();
						
						// Canada Home Page Verification
						verifyCanadaWebgroup();
						///	Search with Microsite pages ex: HP, IBM
						searchLib.searchInHomePage(data.get("SearchText4"));
						searchLib.verifysearchResultsPage();
						pipLib.selectFirstProductAddToCartAndVerifyCart();
						
						
						///	Search  category ex: WorkStations
						searchLib.searchInHomePage(data.get("SearchText5"));
						searchLib.verifysearchResultsPage();
						pipLib.selectFirstProductAddToCartAndVerifyCart();
						searchLib.searchInHomePage(data.get("SearchText6"));
						searchLib.verifysearchResultsPage();
						String desc=pipLib.getFirstProdDescription();
						cartLib.selectFirstProductDisplay();
						
						// Verifying description on product details page 
						pipLib.verifyShortDescriptionOnProductDetailsPage(desc);
						
						///	Search Partial Product  ID
						searchLib.searchInHomePage(data.get("SearchText7"));
						verifyNoResultsFoundMessgeInProductSearchResults();
					
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("KeywordSearch", "TC_CAN03", ReportStatus.strMethodName, intCounter, browser);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("KeywordSearch", "TC_CAN03", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
			driver.quit();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}

    }
  

