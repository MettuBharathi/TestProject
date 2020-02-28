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

public class CAN05_MenuSearchTest extends CanadaLib{

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  CAN05_MenuSearch
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This method is to verify MenuSearch for canada
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_CAN05(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
				int intStartRow=StartRow;
				int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CAN05_MenuSearch", TestDataInsight, "Canada");
				for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
				{
		   try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
			    ReportControl.intRowCount=intCounter;
				Hashtable<String, String> data=TestUtil.getDataByRowNo("CAN05_MenuSearch", TestDataInsight, "Canada",intCounter);
				this.reporter.initTestCaseDescription("MenuSearch");
				reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
						
				navigateToApplication("CANADA");
				
				// Perform Menu Search For Non Logged in   >> Shop all products
				searchLib.clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAll"));
				searchLib.verifyMenusInShopAllProductsPage(data.get("Menus"));
				// Shop all brands
				searchLib.clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAllBrands"));
				searchLib.selectTopBrandsInShopAllBrandsPage(data.get("BrandLogo"), data.get("Url1"));
				// Shop all brands  >> select by alphabetical order
				searchLib.clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAllBrands"));
				searchLib.selectBrandByAlphabetOrderSection(data.get("Url2"),data.get("Brand"));
				// Login to CMT
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				// Enable permissions
				cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
				// Disable -- Enable Custom Catalog
			    cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
				 // Login as to UAT
				cmtLib.loginAsAdminCMT();
				// Perform Menu Search For Non Logged in   >> Shop all products
				searchLib.clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAll"));
				searchLib.verifyMenusInShopAllProductsPage(data.get("Menus"));
				// Shop all brands
				searchLib.clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAllBrands"));
				searchLib.selectTopBrandsInShopAllBrandsPage(data.get("BrandLogo"), data.get("Url1"));
				// Shop all brands  >> select by alphabetical order
				searchLib.clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAllBrands"));
				searchLib.selectBrandByAlphabetOrderSection(data.get("Url2"),data.get("Brand"));
				// End of  test
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("MenuSearch", "CAN05", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("MenuSearch", "CAN05", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}


