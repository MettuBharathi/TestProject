package com.insight.WebTest.Canada;

import java.util.Hashtable;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN09_SPLASearchTest  extends CanadaLib{
	
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	MarriottIntlCorpLib micLib=new MarriottIntlCorpLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  CAN09_SPLASearch
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test Load of the Cart After Selecting SPLA Products
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_CAN09(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow=StartRow;
				int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CAN09_SPLASearch", TestDataInsight, "Canada");
				for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
				{
		try {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount=intCounter;
			Hashtable<String, String> data=TestUtil.getDataByRowNo("CAN09_SPLASearch", TestDataInsight, "Canada",intCounter);
		    this.reporter.initTestCaseDescription("ConverQuoteBundles");
			reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
			// Login to CMT
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
			///	Enable Purchasing Popup - ON
			cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
			// Login as to UAT
			cmtLib.loginAsAdminCMT();
			// Login Verification 
			cmtLib.loginVerification(data.get("ContactName"));
			// account tools >> Software License Agreements
			commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
			// Select Software  Lic Agreements
	     	selectSPLADetailsProductCheckBox(data.get("SPLA"));
			// verify search results and select first product
	     	searchLib.verifysearchResultsPage();
			pipLib.selectFirstProductAddToCartAndVerifyCart();
			// search for product and add to cart
			searchLib.searchInHomePage(data.get("SearchText1"));
			pipLib.selectFirstProductAddToCartAndVerifyCart();
			// Verify Non Spla Items Message
			VerifyNonSplaItemsMessage();
			///	Remove Non Spla Items from the Cart
			commonLib.deleteItemFromCart();
			verifyReportingUsagePeriod();
			//Proceed to checkout
			 orderLib.proceedToCheckout();
			 orderLib.clickContinueOnLLIAndShipBillPaySections();
			 orderLib.addNewCardInPayment(data.get("cardNumber"), data.get("cardName"), data.get("month"), data.get("year"),data.get("poNumebr"));
			 orderLib.clickOnReviewOrderButton();  // Click Review order button
			// Place Order
			String summaryAmount = cartLib.getSummaryAmountInCart();
			orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
			//Verify Receipt
			orderLib.verifyReceiptVerbiage();
			orderLib.clickOrderDetailsLinkOnReceiptPage();
			// Logout 
			commonLib.clickLogOutLink(data.get("Logout"));
			
			// End of test 
		} catch (Exception e) {
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
		}
		ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("SPLASearch", "CAN09", ReportStatus.strMethodName, intCounter, browser);
		fnCloseTest();
	}
} catch (Exception e) {
	e.printStackTrace();
	ReportStatus.blnStatus = false;
	gErrorMessage = e.getMessage();
	gTestStatus = false;
	ReportStatus.fnUpdateResultStatus("SPLASearch", "CRT09", ReportStatus.strMethodName, 1, browser);
	throw new RuntimeException(e);
}

ReportControl.fnNextTestJoin(nextTestJoin);
}
}

