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

public class LNL10_LineLevelViewCartTest extends LineLevelInfoLib{
	
	// Initializing libraries
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
			// #       Name of the Test         :  FCT Web  LineLevel ST_RP_Line_LevelTypes
			// #       Migration Author         : Cigniti Technologies
			// #
			// #       Date of Migration        : November 2019
			// #       DESCRIPTION              : To Test LineLevel ST_RP_Line_LevelTypes
			// #       Parameters               : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void TC_LNL10(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "LNL10_LineLevelViewCart", TestData, "WEB_LineLevelInfo");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("LNL10_LineLevelViewCart", TestData, "WEB_LineLevelInfo", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("LineLevelViewCart");
						
							
							// Login to CMT
							cmtLib.loginToCMT(data.get("Header"));
							cmtLib.searchForWebGroup(data.get("WebGrp"));
							cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
							cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
							cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
							// login As
							cmtLib.loginAsAdminCMT();
							
							// account tools >> Company standards >> Desktops121 >>Publicis Desktops121
							commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"),data.get("Tools_Menu_DD") ,data.get("Product_Group"),data.get("Product_Name"));
							addItemsInCompanyStandart(data.get("size"));
							commonLib.addToOrderAndViewCart();
							canadaLib.verifyPlaceCartLabel();
							// Proceed to check out
							orderLib.proceedToCheckout();
							verifyOrderAndItemInfoBreadCrumb();
							// verify LLI does not exists
							verifyLineLevelInfoExists("NotAvailable");
							
							// search for part or product >> KV3-00367-SLP
							searchLib.searchInHomePage(data.get("SearchText"));
							commonLib.verifyDisplayedProductDetails(data.get("SearchText"));
							// Cart verification
							commonLib.addToCartAndVerify();
							orderLib.continueToCheckOutOnAddCart();
							cartLib.verifyItemInCartByInsightPart(data.get("SearchText"));
							orderLib.proceedToCheckout();
							orderLib.continueButtonOnAdditionalInformationSection();
							// Verify line level info exists
							verifyLineLevelInfoExists("Available");
							// Click continue on LLI
							orderLib.clickContinueOnLineLevelInfo();
							canadaLib.verifySBP();
							commonLib.clickLogOutLink(data.get("Logout"));
							
						} catch (Exception e) {
							ReportStatus.blnStatus = false;
							gErrorMessage = e.getMessage();
							gTestStatus = false;
						}
						ReportControl.fnEnableJoin();
						ReportStatus.fnUpdateResultStatus("LineLevelViewCart", "TC_LNL10", ReportStatus.strMethodName, intCounter, browser);
						fnCloseTest();
					}
				} catch (Exception e) {
					e.printStackTrace();
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
					ReportStatus.fnUpdateResultStatus("LineLevelViewCart", "TC_LNL10", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}

				ReportControl.fnNextTestJoin(nextTestJoin);
			}

}
