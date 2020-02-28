package com.insight.WebTest;

import java.awt.List;
import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class MarriottIntlCorpTest extends MarriottIntlCorpLib {

	// #############################################################################################################
	// # Name of the Test : MIC05_MGSPropAssocQuote
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void MIC05_MGSPropAssocQuote(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "MIC05_MGSPropAssocQuote", TestDataInsight,
				"MarriottIntl_Corp");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) { 

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("MIC05_MGSPropAssocQuote", TestDataInsight,
					"MarriottIntl_Corp", intCounter);

			// Test Steps execution
			fnOpenTest();
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			CartLib cartLib = new CartLib();
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("Contact_Name"));
			cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));// Enable
																													// Purchasing
			cmtLib.loginAsAdminCMT();
			handleinsightpopup();
			// METHOD TO SEARCH AND SWITCH ACCOUNT
			SearchAndswitchtoaccount(data.get("AccountName"));
			handleinsightpopup();
			// WELCOME TO E PROCUREMNET PAGE
			VerifyWelcometoeProcurementpage();
			// COMPANY STATNDARDS LINK
			CompanystandardslinkandProductGrp(data.get("productGroup"), data.get("productName"));
			handleinsightpopup();
			// verify qunatitiy price partnum
			Verifypartnum(data.get("partnum"));
			VerifypartPrice(data.get("partprice"));
			Addcheckbox(data.get("partnum"));
			Setquantity(data.get("value"), data.get("partnum"));
			Closeiconofaddtocartatproductgrps();
			// click add to order
			// cart
			handleinsightpopup();
			// VERIFY ITEM IN CART
			Clickoncart();
			cartLib.verifyItemInCart(data.get("partnum"));
			VerifyItemPriceincart(data.get("price"));
			VerifyItemQuantityincart(data.get("price"));
			SaveAsQuote();
			typeattention(data.get("AccountName"));
			VerifyQuoteNameinsaveasquotepage();
			VerifyQuoteNotesinsaveasquotepage();
			Toclicksaveasquoteincompanystandardspage();
			// CLICK QUOTE
			handleinsightpopup();
			VerifySuccessmsgofQuoteandRefNum();
			VerifyQuoteName();
			VerifyQuoteNotes();
			commonLib.clickLogOutLink(data.get("Logout_Header"));
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("Contact_Name"));
			cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
			fnCloseTest();

		}
	}

	// #############################################################################################################
	// # Name of the Test : MIC04_PAAPPQuote
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void MIC04_PAAPPQuote(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "MIC04_PAAPPQuote", TestDataInsight, "MarriottIntl_Corp");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("MIC04_PAAPPQuote", TestDataInsight,
					"MarriottIntl_Corp", intCounter);

			// Test Steps execution
			fnOpenTest();
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			CartLib cartLib = new CartLib();
			ShipBillPayLib shipbLib = new ShipBillPayLib();
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("Contact_Name"));
			cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
			cmtLib.loginAsAdminCMT();
			handleinsightpopup();
			// METHOD TO SEARCH AND SWITCH ACCOUNT
			SearchAndswitchtoaccount(data.get("AccountName"));
			handleinsightpopup();
			// WELCOME TO E PROCUREMNET PAGE
			VerifyWelcometoeProcurementpage();
			// COMPANY STATNDARDS LINK
			CompanystandardslinkandProductGrp(data.get("productGroup1"), data.get("productName1"));
			handleinsightpopup();
			// verify qunatitiy price partnum
			Verifypartnum(data.get("partnum1"));
			VerifypartPrice(data.get("partprice1"));
			Addradiobutton(data.get("partnum1"));
			Setquantity(data.get("value"), data.get("partnum1"));
			// cart
			handleinsightpopup();
			// VERIFY ITEM IN CART
			Clickoncart();
			commonLib.spinnerImage();
			handleinsightpopup();
			cartLib.verifyItemInCart(data.get("partnum1"));
			Thread.sleep(4000);
			// company standrads
			SelectCPPFRomAccounttools(data.get("toolsMenuName"), data.get("dropDown"), data.get("productGroup2"),
					data.get("productName2"));
			handleinsightpopup();
			// verify qunatitiy price partnum
			Verifypartnum(data.get("partnum2"));
			VerifypartPrice(data.get("partprice2"));
			Addcheckbox(data.get("partnum2"));
			Setquantity(data.get("value"), data.get("partnum2"));
			// click add to order
			// cart
			handleinsightpopup();
			// VERIFY ITEM IN CART
			Clickoncart();
			Thread.sleep(4000);
			cartLib.verifyItemInCart(data.get("partnum2"));
			Thread.sleep(4000);
			shipbLib.AdditemsbyQuickshop(data.get("PartNum3"));
			Thread.sleep(4000);
			cartLib.verifyItemInCart(data.get("PartNum3"));
			SaveAsQuote();
			typeattention(data.get("AccountName"));
			VerifyQuoteNameinsaveasquotepage();
			VerifyQuoteNotesinsaveasquotepage();
			Toclicksaveasquoteincompanystandardspage();
			// CLICK QUOTE
			handleinsightpopup();
			VerifySuccessmsgofQuoteandRefNum();
			VerifyQuoteName();
			VerifyQuoteNotes();
			commonLib.clickLogOutLink(data.get("Logout_Header"));
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("Contact_Name"));
			cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
			fnCloseTest();

		}
	}

	// #############################################################################################################
	// # Name of the Test : MIC09_VerifyAddressSwitchWGSBP
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void MIC09_VerifyAddressSwitchWGSBP(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "MIC09_VerifyAddressSwitchWGSBP", TestDataInsight,
				"MarriottIntl_Corp");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("MIC09_VerifyAddressSwitchWGSBP", TestDataInsight,
					"MarriottIntl_Corp", intCounter);

			// Test Steps execution
			fnOpenTest();
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			ShipBillPayLib shipbLib = new ShipBillPayLib();
			OrderLib orderLib = new OrderLib();
			CartLib cartLib = new CartLib();
			ProductDisplayInfoLib productDispinfoLib=new ProductDisplayInfoLib();
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("Contact_Name"));
			cmtLib.loginAsAdminCMT();
			commonLib.searchProduct(data.get("Search_Item"));
			cartLib.selectFirstProductDisplay();
			productDispinfoLib.addToCartInProductDetailsPage();
			commonLib.clickCart();
			commonLib.clickCart();
			// proceed to checkout
			orderLib.proceedToCheckout();
			// verify shipping address
			shipbLib.VerifyStoredAddress(data.get("storedaddress"));
			orderLib.shippingBillPayContinueButton();
			orderLib.shippingOptionsCarrierSelection();
			orderLib.shippingBillPayContinueButton();
			Thread.sleep(3000);
			String CompanyName1=TakeshippingaddressCompany();
			Thread.sleep(1000);
			Object Address1=Takeshippingaddress();
			SwitchWebGroup(data.get("webgrp"));
			commonLib.searchProduct(data.get("Search_Item"));
			commonLib.addFirstDisplyedItemToCartAndVerify();
			commonLib.continueToShopping();
			commonLib.clickCart();
			commonLib.clickCart();
			// proceed to checkout
			orderLib.proceedToCheckout();
			addAdditionalInfoOfProductSWP(data.get("endUserEmail"),data.get("country"),data.get("enduserunit"),data.get("endusermarsha"));
			cartLib.clickOnContinueButtonInAddInformtion();
			orderLib.shippingBillPayContinueButton();
			orderLib.shippingOptionsCarrierSelection();
			orderLib.shippingBillPayContinueButton();
			Thread.sleep(2000);
			String CompanyName2=TakeshippingaddressCompany();
			Thread.sleep(1000);
			Object Address2=Takeshippingaddress();
			Thread.sleep(3000);
			CompareaddressCompanyname(CompanyName1,CompanyName2);
			Thread.sleep(3000);
			Compareaddress(Address1,Address2);
			commonLib.clickLogOutLink(data.get("Logout_Header"));
			fnCloseTest();
		}
	}
	 
}
