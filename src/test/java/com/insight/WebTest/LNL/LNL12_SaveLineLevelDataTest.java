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

public class LNL12_SaveLineLevelDataTest extends LineLevelInfoLib{

	
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
		// #       Name of the Test         :   LNL12_SaveLinLevelData
		// #       Migration Author         : Cigniti Technologies
		// #
		// #       Date of Migration        : November 2019
		// #       DESCRIPTION              : This function is used to generate the SaveLinLevelData
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_LNL12(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "LNL12_SaveLinLevelData", TestData, "WEB_LineLevelInfo");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("LNL12_SaveLinLevelData", TestData, "WEB_LineLevelInfo", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("SaveLinLevelData");
						String partNumber1=null;
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp1"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name1"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
						// login As
						cmtLib.loginAsAdminCMT();
						
						// Add 2 items  >> RAM 
						for(i=0;i<2;i++){
							searchLib.searchInHomePage(data.get("SearchText1"));
							cartLib.selectFirstProductDisplay();
							partNumber1=prodLib.getMFRNumberInProductInfopage();
							commonLib.addToCartAndVerify();
							orderLib.continueToCheckOutOnAddCart();
							cartLib.verifyItemInCart(partNumber1);
						}
						commonLib.clickLogOutLink(data.get("Logout"));
						// navigate back to CMT
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						cmtLib.searchForWebGroup(data.get("WebGrp2"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name2"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2"));
						// login As
						cmtLib.loginAsAdminCMT();
						
						// search item >> RAM
						searchLib.searchInHomePage(data.get("SearchText1"));
						cartLib.selectFirstProductDisplay();
						String partNumber2=prodLib.getMFRNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCart(partNumber2);
						

						// Add second item  >> Laser Printers
						searchLib.searchInHomePage(data.get("SearchText2"));
						cartLib.selectFirstProductDisplay();
						String partNumber3=prodLib.getMFRNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCart(partNumber3);
						
						// Proceed to check out
						orderLib.proceedToCheckout();
						String[] partNum={partNumber2,partNumber3};
						for(i=0;i<2;i++){
							verifyLineLevelInfoOptionalIsPresent(partNum[i]);
						}
						clickOnLineLevelOptionalLinkByPartNum(partNumber3);
						selectDiversityPartner(data.get("Diversity_Partner"),partNumber3);
						
						commonLib.clickLogOutLink(data.get("Logout"));
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("SaveLinLevelData", "TC_LNL12", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("SaveLinLevelData", "TC_LNL12", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}
}
