package com.insight.WebTest.LNL;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.LineLevelInfoLib;
import com.insight.Lib.OrderHistoryLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class LNL14_ValidateSplitFeatureinOrderTest extends LineLevelInfoLib{
	
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib=new CanadaLib();
	OrderHistoryLib odhLib=new OrderHistoryLib();
	InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
	
	   
	    // #############################################################################################################
		// #       Name of the Test         : LNL14_ValidateSplitFeatureinOrder
		// #       Migration Author         : Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This function is used to test Validate Split Feature in Order
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_LNL14(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "LNL14_ValidateSplitFeatureinOrder", TestData, "WEB_LineLevelInfo");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("LNL14_ValidateSplitFeatureinOrder", TestData, "WEB_LineLevelInfo", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("ValidateSplitFeatureinOrder");
                    
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// Enable Buying
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						// login As
						cmtLib.loginAsAdminCMT();
						
						// account tools >> Software License Agreements
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu1"), data.get("Tools_Menu_DD1"));
						// Select Software  Lic Agreements
				     	canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
				        // verify search results and select first product
				     	searchLib.verifysearchResultsPage();
				     	pipLib.changeFirstProductQuantity(data.get("Quantity"));
				     	commonLib.addFirstDisplyedItemToCartAndVerify();
				     	orderLib.continueToCheckOutOnAddCart();
				     	orderLib.proceedToCheckout();
				     	orderLib.continueButtonOnAdditionalInformationSection();
				     	clickOnSplitIntoIndividualLines();
				     	verifySplitLineItemsLabel();
				     	orderLib.clickContinueOnLineLevelInfo();
				     	canadaLib.verifySBP();
				     	orderLib.shippingBillPayContinueButton(); // Click continue on shipping address Section
						orderLib.shippingBillPayContinueButton(); // Click continue on Billing address Section
						orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));  // VISA card
						orderLib.clickOnReviewOrderButton();
						
						//Place Order
						String summaryAmount2=cartLib.getSummaryAmountInCart();
						
						orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount2);
						String refNumber=orderLib.getTextfromReferenceNumber();
						//Verify Receipt
						orderLib.verifyReceiptVerbiage();
						canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu2"),data.get("Tools_Menu_DD2"));
						// Order quick search
						odhLib.verifyOrderHistoryPage();
						odhLib.selectQuickSearchDropdown(data.get("Search_By"),refNumber);
						commonLib.spinnerImage();
						odhLib.verifySearchResultsAreDisplayed();
						odhLib.clickOrderNumber();
						invoiceHistoryLib.verifyOrderDetailsPage();
						int itemNo=Integer.valueOf(data.get("Quantity"));
						verifyItemDescOnOrderDetailsPage(itemNo);
						commonLib.clickLogOutLink(data.get("Logout"));
						
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("ValidateSplitFeatureinOrder", "TC_LNL14", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("ValidateSplitFeatureinOrder", "TC_LNL14", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}
}
