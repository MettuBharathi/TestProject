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

public class LNL03_SellRequirementsTest extends LineLevelInfoLib{
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib=new CanadaLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  LNL03_SellRequirementsTest
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : To Test Line Level Sell Requirements
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_LNL03(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "LNL03_SellRequirements", TestData, "WEB_LineLevelInfo");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("LNL03_SellRequirements", TestData, "WEB_LineLevelInfo", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("RequireOverrideTest");
                    
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// Disable Enable My Software License Agreements ;OFF";
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
						// enable_purchase_popup;ON";
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
						// login As
						cmtLib.loginAsAdminCMT();
						// Login Verification 
						cmtLib.loginVerification(data.get("ContactName"));
						// account tools >> Software License Agreements
						clickOnAccountToolsAndVerifyOptionsnotDisplayed(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						
						// search for a part number
						searchLib.searchInHomePage(data.get("SearchText1"));
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCartByInsightPart(data.get("SearchText1"));
						
						// Search For another part
						searchLib.searchInHomePage(data.get("SearchText2"));
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCartByInsightPart(data.get("SearchText2"));
						
						orderLib.proceedToCheckout(); // proceed to check out 
						orderLib.clickOnAdditionalInfoContinueButton();
						// Enter line level info for part 1
						enterLineLevelInfo(data.get("Email"), data.get("Contact_Name"), data.get("Contact_Number"), data.get("Customer_Quote"), data.get("SearchText1"));
						clickCopyToAllLink(data.get("SearchText1"));
						String actualEmail=getContactEmail(data.get("SearchText2"));
						// Verifying email
						assertTextStringMatching(actualEmail, data.get("Email"));
						String contactName=getContactName(data.get("SearchText2"));
						// verifying contact name
						assertTextStringMatching(contactName, data.get("Contact_Name"));
						String phoneNumber=getContactPhoneNumber(data.get("SearchText2"));
						// verifying contact number
						assertTextStringMatching(phoneNumber, data.get("Contact_Number"));
						String quoteName=getCustomerQuote(data.get("SearchText2"));
						// verifying Quote
						assertTextStringMatching(quoteName, data.get("Customer_Quote"));
						
						scrollUp();
						// click clear for 2nd item
						clickClearLink(data.get("SearchText2"));
						orderLib.clickContinueOnLineLevelInfo();
						// verify Manufacturer Requirement in the Cart
						verifyEmailRequiredFieldsErrorMessage();
						
						// Logout 
						commonLib.clickLogOutLink(data.get("Logout"));
						// navigate back to CMT
						cmtLib.navigateBackToCMT();
						
						// Enable My Software License Agreements - ON 
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
						// Enable Browse My Software License Agreements - ON
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission3"));
						cmtLib.loginAsAdminCMT();
						// account tools >> verify Software License Agreements displayed
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						// Select Software  Lic Agreements
				     	canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
						// verify search results and select first product
				     	searchLib.verifysearchResultsPage();
				     	verifyProratedPriceMessageOnSearchResultsPage();
				     	
				       // search for a part number
						searchLib.searchInHomePage(data.get("SearchText1"));
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCartByInsightPart(data.get("SearchText1"));
						canadaLib.verifyProratedPrice();
						
						// Search For another part
						searchLib.searchInHomePage(data.get("SearchText3"));
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCartByInsightPart(data.get("SearchText3"));
						canadaLib.verifyProratedPrice();
						
						orderLib.proceedToCheckout(); // proceed to check out 
						orderLib.clickOnAdditionalInfoContinueButton();
						// Enter line level info for part 1
						enterLineLevelInfo(data.get("Email"), data.get("Contact_Name"), data.get("Contact_Number"), data.get("Customer_Quote"), data.get("SearchText1"));
						clickCopyToAllLink(data.get("SearchText1"));
						String actualEmail1=getContactEmail(data.get("SearchText3"));
						// Verifying email
						assertTextStringMatching(actualEmail1, data.get("Email"));
						String contactName1=getContactName(data.get("SearchText3"));
						// verifying contact name
						assertTextStringMatching(contactName1, data.get("Contact_Name"));
						String phoneNumber1=getContactPhoneNumber(data.get("SearchText3"));
						// verifying contact number
						assertTextStringMatching(phoneNumber1, data.get("Contact_Number"));
						String quoteName1=getCustomerQuote(data.get("SearchText3"));
						// verifying Quote
						assertTextStringMatching(quoteName1, data.get("Customer_Quote"));
						scrollUp();
						// click clear for 2nd item
						clickClearLink(data.get("SearchText3"));
						orderLib.clickContinueOnLineLevelInfo();
						// verify Manufacturer Requirement in the Cart
						verifyEmailRequiredFieldsErrorMessage();
				     	
						// Logout 
						commonLib.clickLogOutLink(data.get("Logout"));
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("SellRequirements", "TC_LNL03", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("SellRequirements", "TC_LNL03", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}
}
