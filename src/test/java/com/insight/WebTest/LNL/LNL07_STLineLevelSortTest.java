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

public class LNL07_STLineLevelSortTest extends LineLevelInfoLib{

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
			// #       Name of the Test         : LNL07_STLineLevelSort
			// #       Migration Author         : Cigniti Technologies
			// #
			// #       Date of Migration        : October 2019
			// #       DESCRIPTION              : This function is used to test Line Level CartDetails
			// #       Parameters               : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void TC_LNL07(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "LNL07_STLineLevelSort", TestData, "WEB_LineLevelInfo");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("LNL07_STLineLevelSort", TestData, "WEB_LineLevelInfo", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("STLineLevelSort");
						
							// Login to CMT
							cmtLib.loginToCMT(data.get("Header"));
							cmtLib.searchForWebGroup(data.get("WebGrp"));
							cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
							cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
							cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
							// login As
							cmtLib.loginAsAdminCMT();
							
							// Add first item  >> RAM 
							searchLib.searchInHomePage(data.get("SearchText1"));
							cartLib.selectFirstProductDisplay();
							String partNumber1=prodLib.getMFRNumberInProductInfopage();
							commonLib.addToCartAndVerify();
							orderLib.continueToCheckOutOnAddCart();
							cartLib.verifyItemInCart(partNumber1);
							
							// Add second item  >> Workstations
							searchLib.searchInHomePage(data.get("SearchText2"));
							cartLib.selectFirstProductDisplay();
							String partNumber2=prodLib.getMFRNumberInProductInfopage();
							commonLib.addToCartAndVerify();
							orderLib.continueToCheckOutOnAddCart();
							cartLib.verifyItemInCart(partNumber2);
							
							// Add second item  >> Netbooks
							searchLib.searchInHomePage(data.get("SearchText3"));
							cartLib.selectFirstProductDisplay();
							String partNumber3=prodLib.getMFRNumberInProductInfopage();
							commonLib.addToCartAndVerify();
							orderLib.continueToCheckOutOnAddCart();
							cartLib.verifyItemInCart(partNumber3);
							
							// Proceed to check out
							orderLib.proceedToCheckout();
							orderLib.continueButtonOnAdditionalInformationSection();
							
							// click on LLI 
							clickOnLineLevelOptionalLinkByPartNum(partNumber1);
							selectRP_LNL_Lst(data.get("RP_LNL_Lst"));
							enterRP_LNL_Date();
							enterRP_LNL_Email(partNumber1, data.get("Email"));
							enter_rP_LNL_Txt(data.get("RP_LNL_Txt"));
							 // Copy all
							clickCopyToAllLink(partNumber1);
							
							// click on LLI of 2nd item
							clickOnLineLevelOptionalLinkByPartNum(partNumber2);
							verifyInputFieldsInLLI(partNumber2,data.get("Email"),data.get("RP_LNL_Txt"),data.get("RP_LNL_Lst"));
							
							// click on LLI of 3rd item
							clickOnLineLevelOptionalLinkByPartNum(partNumber3);
							verifyInputFieldsInLLI(partNumber3,data.get("Email"),data.get("RP_LNL_Txt"),data.get("RP_LNL_Lst"));
							// Logout
							commonLib.clickLogOutLink(data.get("Logout"));
							
						} catch (Exception e) {
							ReportStatus.blnStatus = false;
							gErrorMessage = e.getMessage();
							gTestStatus = false;
						}
						ReportControl.fnEnableJoin();
						ReportStatus.fnUpdateResultStatus("STLineLevelSort", "TC_LNL07", ReportStatus.strMethodName, intCounter, browser);
						fnCloseTest();
					}
				} catch (Exception e) {
					e.printStackTrace();
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
					ReportStatus.fnUpdateResultStatus("STLineLevelSort", "TC_LNL07", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}

				ReportControl.fnNextTestJoin(nextTestJoin);
			}
}
