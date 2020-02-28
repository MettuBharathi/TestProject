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

public class LNL04_CopyLineLevelDataIPSTest  extends LineLevelInfoLib{
	
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
		// #       Name of the Test         : LNL04_CopyLineLevelDataIPSTest
		// #       Migration Author         : Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This function is used to generate the Copy LineLevel Data_IPS
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_LNL04(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "LNL04_CopyLineLevelDataIPS", TestData, "WEB_LineLevelInfo");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("LNL04_CopyLineLevelDataIPS", TestData, "WEB_LineLevelInfo", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("CopyLineLevelDataIPS");
                    
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// Enable purchasing popup
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						// login As
						cmtLib.loginAsAdminCMT();
						
						
						// Search for part or product  >> Thinkpad >>
						searchLib.searchInHomePage(data.get("SearchText1"));
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCart(data.get("SearchText1"));
						
						
					   // Search for another product >> Workstations >> 
						searchLib.searchInHomePage(data.get("SearchText2"));
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCart(data.get("SearchText2"));
						
						// Select new contract
						searchLib.selectNewcontract(data.get("Contract_Name1"));
						// Search for another product >> Thinkpad >>
						searchLib.searchInHomePage(data.get("SearchText3"));
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCart(data.get("SearchText3"));
						// Verify selected contract in cart page
						cartLib.verifyContractNameInCart(data.get("Contract_Name1"));
						
						// Select new contract
						searchLib.selectNewcontract(data.get("Contract_Name2"));
						// Search for another product >> Workstations
						searchLib.searchInHomePage(data.get("SearchText4"));
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCart(data.get("SearchText4"));
						// Verify selected contract in cart page
						cartLib.verifyContractNameInCart(data.get("OpenMarket"));
						cartLib.verifyContractNameInCart(data.get("Contract_Name1"));
						orderLib.proceedToCheckout();  // Proceed to checkout
						orderLib.enterReportingDetailsInLineLevelInfo(data.get("REPORTING_FIELD_4"), data.get("REPORTING_FIELD_5"), data.get("REPORTING_FIELD_6"));
						selectDiversityPartner(data.get("Diversity_Partner1"),data.get("SearchText3"));
						clickOnLinelevelInfoOptionalLink();
						orderLib.clickContinueOnLineLevelInfo(); // click continue on LLI 
						
						
						orderLib.shippingBillPay(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));
						// Contract Specific Information verification
						verifyContractSpecificInfoOnPlaceOrderPage();
						// click on edit LLI
						editLinelevelInfoOnPlaceOrderPage();
						// Verify OII bread crumb
						verifyOrderAndItemInfoBreadCrumb();
						// click on LLI optional
						clickOnLineLevelOptionalLinkByPartNum(data.get("SearchText1"));
						selectDiversityPartner(data.get("Diversity_Partner2"),data.get("SearchText1"));
						clickCopyToAllLink(data.get("SearchText1"));
						clickOnLineLevelOptionalLinkByPartNum(data.get("SearchText2"));
						verifyDiversityPartnerexists(data.get("Diversity_Partner2"),data.get("SearchText2"));
						clickClearLink(data.get("SearchText1"));
						scrollUp();
						clickClearLink(data.get("SearchText3"));
						String reportingfield4= getReportingField4();
						String reportingfield5= getReportingField5();
						String reportingfield6= getReportingField6();
					   // Verifying reporting fields are empty
						assertTextStringMatching(reportingfield4, "");
						assertTextStringMatching(reportingfield5, "");
						assertTextStringMatching(reportingfield6, "");
						orderLib.enterReportingDetailsInLineLevelInfo(data.get("REPORTING_FIELD_4"), data.get("REPORTING_FIELD_5"), data.get("REPORTING_FIELD_6"));
						selectDiversityPartner(data.get("Diversity_Partner1"),data.get("SearchText3"));
						orderLib.clickContinueOnLineLevelInfo(); // click continue on LLI 
						verifyContractSpecificInfoOnPlaceOrderPage();
						commonLib.clickLogOutLink(data.get("Logout"));
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("CopyLineLevelDataIPS", "TC_LNL04", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("CopyLineLevelDataIPS", "TC_LNL04", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}


}
