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

public class LNL05_CopyLineLevelSmartTrackerTest extends LineLevelInfoLib{

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
		// #       Name of the Test         : LNL05_CopyLineLevelSmartTrackerTest
		// #       Migration Author         : Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This function is used to CopyLineLevelSmartTracker
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_LNL05(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "LNL05_CopyLineLevelSmartTracker", TestData, "WEB_LineLevelInfo");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("LNL05_CopyLineLevelSmartTracker", TestData, "WEB_LineLevelInfo", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("CopyLineLevelSmartTracker");
                    
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
						
						// Search for part or product >> Thinkpad
						searchLib.searchInHomePage(data.get("SearchText1"));
						cartLib.selectFirstProductDisplay();
						String partNumber1=prodLib.getMFRNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCart(partNumber1);
						
					   // Search for 2nd product >> Workstations
						searchLib.searchInHomePage(data.get("SearchText2"));
						cartLib.selectFirstProductDisplay();
						String partNumber2=prodLib.getMFRNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCart(partNumber2);
						
					   // Search for 3rd product >> laser printers
						searchLib.searchInHomePage(data.get("SearchText3"));
						cartLib.selectFirstProductDisplay();
						String partNumber3=prodLib.getMFRNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCart(partNumber3);
						orderLib.proceedToCheckout();
						orderLib.clickOnAdditionalInfoContinueButton();
						clickOnLineLevelOptionalLinkByPartNum(partNumber1);
						selectRP_LNL_Lst(data.get("RP_LNL_Lst"));
						enterRP_LNL_Date();
						enterRP_LNL_Email(partNumber1, data.get("Email"));
						enter_rP_LNL_Txt(data.get("RP_LNL_Txt"));
						// Copy all
						clickCopyToAllLink(partNumber1);
						
						// Line level for >> second part
						clickOnLineLevelOptionalLinkByPartNum(partNumber2);
						
						// Verifying email - second item
						String actualEmail2=getContactEmail(partNumber2);
						assertTextStringMatching(actualEmail2, data.get("Email"));
						
						// Verifying RP_LNL_Txt - second item
						String RP_LNL_Txt2=getRP_LNL_Txt(partNumber2);
						assertTextStringMatching(RP_LNL_Txt2, data.get("RP_LNL_Txt"));
						
						// Verify  RP_LNL_DATE - second item
						String RP_LNL_DATE2=getRP_LNL_DATE_PICKER(partNumber2);
						String actualDate = getCurrentDateTime("dd-MMM-yyyy");
						assertTextStringMatching(RP_LNL_DATE2,actualDate );
						
						// Line level for >> Third part
						clickOnLineLevelOptionalLinkByPartNum(partNumber3);
						
						// Verifying email - second item
						String actualEmail3=getContactEmail(partNumber3);
						assertTextStringMatching(actualEmail3, data.get("Email"));
						
						// Verifying RP_LNL_Txt - second item
						String RP_LNL_Txt3=getRP_LNL_Txt(partNumber3);
						assertTextStringMatching(RP_LNL_Txt3, data.get("RP_LNL_Txt"));
						
						// Verify  RP_LNL_DATE - second item
						String RP_LNL_DATE3=getRP_LNL_DATE_PICKER(partNumber3);
						assertTextStringMatching(RP_LNL_DATE3,actualDate);
						
						// click clear for 3rd item
						clickClearLink(partNumber3);
						String actualEmailclear=getContactEmail(partNumber3);
						assertTextStringMatching(actualEmailclear, "");
						
						// Verifying RP_LNL_Txt - second item
						String RP_LNL_TxtClear=getRP_LNL_Txt(partNumber3);
						assertTextStringMatching(RP_LNL_TxtClear, "");
						
						// Verify  RP_LNL_DATE - second item
						String RP_LNL_DATEClear=getRP_LNL_DATE_PICKER(partNumber3);
						assertTextStringMatching(RP_LNL_DATEClear,"");
						scrollUp();
						// Copy all  >> 
						clickCopyToAllLink(partNumber1);
						orderLib.clickContinueOnLineLevelInfo();
						// logout
						commonLib.clickLogOutLink(data.get("Logout"));
						
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("CopyLineLevelSmartTracker", "TC_LNL05", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("CopyLineLevelSmartTracker", "TC_LNL05", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}
}
