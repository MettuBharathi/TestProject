package com.insight.WebTest.LNL;

import java.util.Hashtable;
import java.util.List;

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

public class LNL15_ValidateSplitBundleInOrderTest extends LineLevelInfoLib{

	
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
		// #       Name of the Test         : LNL15_ValidateSplitBundleInOrder
		// #       Migration Author         : Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This function is used to Validate Split Bundle in Order
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_LNL15(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "LNL15_ValidateSplitBundleInOrder", TestData, "WEB_LineLevelInfo");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("LNL15_ValidateSplitBundleInOrder", TestData, "WEB_LineLevelInfo", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("ValidateSplitBundleInOrder");
                    
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// login As
						cmtLib.loginAsAdminCMT();
						commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu1"),data.get("Tools_Menu_DD1"), data.get("Product_Group"), data.get("Product_Name"));
						searchLib.clickAddToOrderOnCompanyStandardsScreen();
						commonLib.clickCart();
						cartLib.verifyProductGroupBundleAddedToCart(data.get("Product_Name"));
						commonLib.verifyBundleIsAddedToCart();
						commonLib.updateCartQuantity(data.get("Quantity"));
						// Verify quantity updated
				     	List<String> quantity = orderLib.getCartProductQuantityForBundleOfProducts();
						cartLib.verifyProductGroupQuantityInCart(quantity, data.get("Quantity"));
						// Proceed to check out
						orderLib.proceedToCheckout();
				     	orderLib.continueButtonOnAdditionalInformationSection();
				     	clickOnSplitIntoIndividualLines();
				     	verifySplitLineItemsLabel();
				     	verifyBundleIsAddedToCart(data.get("Bundle_One"));
				     	verifyBundleIsAddedToCart(data.get("Bundle_Two"));
				     	orderLib.clickContinueOnLineLevelInfo();
				     	canadaLib.verifySBP();
				     	orderLib.shippingBillPayContinueButton(); // Click continue on shipping address Section
						orderLib.shippingBillPayContinueButton(); // Click continue on Shipping options Section
						orderLib.shippingBillPayContinueButton(); //Click continue on Billing address Section
						orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));  // VISA card
                        orderLib.clickOnReviewOrderButton();
						
						//Place Order
						String summaryAmount2=cartLib.getSummaryAmountInCart();
						orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount2);
						String refNumber=orderLib.getTextfromReferenceNumber();
						//Verify Receipt
						orderLib.verifyReceiptVerbiage();
						
						canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu2"),data.get("Tools_Menu_DD2"));
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
					ReportStatus.fnUpdateResultStatus("ValidateSplitBundleInOrder", "TC_LNL15", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("ValidateSplitBundleInOrder", "TC_LNL15", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}
}
