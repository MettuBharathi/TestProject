package com.insight.WebTest.LNL;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.LineLevelInfoLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class LNL06_LineLevelCartDetailsTest extends LineLevelInfoLib{
	
	// Initilizing libraries
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib=new CanadaLib();
	ProductDetailLib prodLib=new ProductDetailLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         : LNL06_LineLevelCartDetails
		// #       Migration Author         : Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This function is used to test Line Level CartDetails
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_LNL06(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "LNL06_LineLevelCartDetails", TestData, "WEB_LineLevelInfo");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("LNL06_LineLevelCartDetails", TestData, "WEB_LineLevelInfo", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("LineLevelCartDetails");
					
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						// Display Insight Warehouse Inventory;OFF";
						cmtLib.setCustomerLevelPermissionsOFF(data.get("Set_Permission"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// Click on Linked Accounts
						cmtLib.clickCheckOutSettings(data.get("Linked_Accounts"));
						cmtLib.enterLinkedAccountSearch(data.get("Account_Number"));
						cmtLib.checkLinkedAccountCheckBox(data.get("Account_Number"));
						cmtLib.clickRadioDefaultAtLogin(data.get("Account_Number"));
						cmtLib.clickUpdateButtonOnLinkedAccountsScreen();
						// Enable My Software License Agreements - ON
						//Enable Browse My Software License Agreements - ON
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
						// login As
						cmtLib.loginAsAdminCMT();
						
						// Search for part or product >> LENOVO
						searchLib.searchInHomePage(data.get("SearchText1"));
						cartLib.selectFirstProductDisplay();
						String partNumber1=prodLib.getMFRNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCart(partNumber1);
						orderLib.proceedToCheckout();
						commonLib.clickLogOutLink(data.get("Logout"));
						// navigate back to cmt
						cmtLib.navigateBackToCMT();
						// login As
						cmtLib.loginAsAdminCMT();
						// Login Verification 
						cmtLib.loginVerification(data.get("ContactName"));
						
						// account tools >> Software License Agreements
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						// Select Software  Lic Agreements >MICROSOFT SELECT PLUS
				     	canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
				    	// verify search results and select first product
				     	searchLib.verifysearchResultsPage();
				     	
				     // Search for part or product >> H22-00489-SLP
						searchLib.searchInHomePage(data.get("SearchText2"));
						commonLib.verifyDisplayedProductDetails(data.get("SearchText2"));
						// Cart verification
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCartByInsightPart(data.get("SearchText2"));
						orderLib.proceedToCheckout();
						orderLib.continueButtonOnAdditionalInformationSection();
						verifyCountryOfUsageDD(data.get("SearchText2"));
						commonLib.clickLogOutLink(data.get("Logout"));
						// navigate back to cmt
						cmtLib.navigateBackToCMT();
						
						
						cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						// Display Insight Warehouse Inventory - ON
						cmtLib.setCustomerLevelPermissionsON(data.get("Set_Permission3"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// login As
						cmtLib.loginAsAdminCMT();
						
						// Search for part or product >> Printers
						searchLib.searchInHomePage(data.get("SearchText3"));
						String[] prod=verifyTheStockNumbersInSearchResultsPageAndSelectProduct();
						String stockNum=prod[1];
						// Cart verification
						String partNumberPrinter1=prodLib.getInsightPartNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCartByInsightPart(partNumberPrinter1);
						
						// Verify Stock 
						String cartStock=getStockNumberInCart().replace("Stock: ", "").replace(",", "").trim();
						assertTextStringMatching(stockNum, cartStock);
						commonLib.clickLogOutLink(data.get("Logout")); // Logout
						
						// navigate back to CMT
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						// Display Insight Warehouse Inventory - OFF
						cmtLib.setCustomerLevelPermissionsOFF(data.get("Set_Permission3"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// login As
						cmtLib.loginAsAdminCMT();
						
						// Search for part or product >> Printers
						searchLib.searchInHomePage(data.get("SearchText3"));
						String[] products=verifyTheStockNumbersInSearchResultsPageAndSelectProduct();
						String stockNum1=products[1].replace(",", "");
						// Cart verification
						String partNumberPrinter2=prodLib.getInsightPartNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCartByInsightPart(partNumberPrinter2);
						
						// Verify Stock 
						String cartStock1=getStockNumberInCart().replace("Stock: ", "").replace(",", "").trim();
						assertTextStringMatching(stockNum1, cartStock1);
						commonLib.clickLogOutLink(data.get("Logout")); // Logout
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("LineLevelCartDetails", "TC_LNL06", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("LineLevelCartDetails", "TC_LNL06", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}
