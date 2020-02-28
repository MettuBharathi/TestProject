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

public class LNL01_InactiveSTTest extends LineLevelInfoLib{
	
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib=new CanadaLib();
	LineLevelInfoLib lnlLib=new LineLevelInfoLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         : LNL01_InactiveSTTest
		// #       Migration Author         : Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : Test to Ensure inactive smart trackers do no display
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_LNL01(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "LNL01_InactiveST", TestData, "WEB_LineLevelInfo");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("LNL01_InactiveST", TestData, "WEB_LineLevelInfo", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("InactiveST");
                    
						// Create a new account 
						canadaLib.clickCreateAccountOnCanadaSearchPage();
						cmtLib.handleWelcomeToInsightBetaPopUp();
						String userName=enterEmailInCreateAccount();
						enterpasswordInCreateAccount();
						canadaLib.clickCustomCheckBox();
						canadaLib.clickOnNext();
						selectCountryDisplayed(data.get("Country"));  // United States Of America
						canadaLib.selectJobTitle(data.get("Jobtitle"));
						canadaLib.selectOption(data.get("ShoppingFor"));
						canadaLib.clickCreateButton();
						// Search for part or product
						searchLib.searchInHomePage(data.get("SearchText"));
						pipLib.selectFirstProductAddToCartAndVerifyCart();
						orderLib.proceedToCheckout();
						// Enter add additional info
						String contactName=canadaLib.enterNameOnAdditionalInfo();
						canadaLib.enterPhoneOnAdditionalInfo(data.get("Phone"));
						orderLib.continueButtonOnAdditionalInformationSection();
						
						// enter Shipping address
						// street1 >> 3480 Lotus Dr  // city >> PLANO // state >> Texas // zipCode >> 75075
						canadaLib.addShippingAddress(data.get("Company_Name"), data.get("Name"), data.get("Street1"), data.get("City"), data.get("State"), data.get("Zipcode"));  
						orderLib.shippingBillPayContinueButton(); // continue on shipping address
						// Save address  
						acceptShippingAddressVerification();
						orderLib.shippingBillPayContinueButton(); // continue on shipping options
						checkSameAsShippingAddress();
						orderLib.shippingBillPayContinueButton(); // continue on Billing  options
						orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));
						orderLib.clickOnReviewOrderButton();
						cartLib.getSummaryAmountInCart();
					    canadaLib.clickPlaceOrderButton();
						
						/// Login 
						cmtLib.clickLoginLink(data.get("Header"));
						cmtLib.loginAsAdmin();
						// search for user
						cmtLib.searchForUser(userName);
						// Click on Web Group Link 
						cmtLib.clickOnWebGrpLink();
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));  // SmartTrackers
						cmtLib.verifySmartTrackerPage();
						// Add a smart tracker
						cmtLib.clickOnAddSmartTrackerLink();
						cmtLib.addFieldLabelInSmartTracker(data.get("Field_Label"));
						cmtLib.saveChangesAndVerify(data.get("Field_Label"));
						// inActive smart tracker
						cmtLib.editSmartTracker(data.get("Field_Label"));
						cmtLib.verifyInactiveSmartTrackerError();
						
						// click on Line level tab
						cmtLib.selectSmartTrackersHeaders(data.get("Smart_Header"));
						cmtLib.clickOnAddSmartTrackerLink();
						cmtLib.addFieldLabelInSmartTracker(data.get("Field_Label"));
						cmtLib.addSmartTrackerFieldType(data.get("Field_Type"));  // Date - type
						cmtLib.saveChangesAndVerify(data.get("Field_Label"));
						
						// inActive smart tracker
						cmtLib.editSmartTracker(data.get("Field_Label"));
						cmtLib.verifyInactiveSmartTrackerError();
						
						// click on Manage Reporting Parent SmartTrackers
						refreshPage();
						cmtLib.selectmanageSmartTrackertabs(data.get("tab_Name"));
						cmtLib.clickOnAddSmartTrackerLink();
						cmtLib.addFieldLabelInSmartTracker(data.get("Field_Label"));
						cmtLib.addSmartTrackerFieldType(data.get("Field_Type"));  // Date - type
						cmtLib.saveChangesAndVerify(data.get("Field_Label"));
						
						// inActive smart tracker
						cmtLib.editSmartTracker(data.get("Field_Label"));
						cmtLib.verifyInactiveSmartTrackerError();
						
						// click on Line level tab
						cmtLib.selectSmartTrackersHeaders(data.get("Smart_Header"));
						cmtLib.clickOnAddSmartTrackerLink();
						cmtLib.addFieldLabelInSmartTracker(data.get("Field_Label"));
						cmtLib.addSmartTrackerFieldType(data.get("Field_Type"));  // Date - type
						cmtLib.saveChangesAndVerify(data.get("Field_Label"));
						
						// inActive smart tracker
						cmtLib.editSmartTracker(data.get("Field_Label"));
						cmtLib.verifyInactiveSmartTrackerError();
						
						cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						// search for user
						cmtLib.searchForUser(userName);
						// Click on Web Group Link 
						cmtLib.clickOnWebGrpLink();
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));  // Users
						cmtLib.searchForaUserAndSelect(userName,contactName);  // contact 
						// login As
						cmtLib.loginAsAdminCMT();
						
						// search for item and add to cart
						searchLib.searchInHomePage(data.get("SearchText"));
						cartLib.selectFirstProductDisplay();
						String partNumber1=prodDetailsLib.getMFRNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCart(partNumber1);
						
						// Proceed to check out
						proceedToCheckout();
						lnlLib.verifyLineLevelInfoExists("NotAvailable");
						
						orderLib.shippingBillPayContinueButton(); // Click continue on shipping address Section
						orderLib.shippingBillPayContinueButton(); // Click continue on Shipping options Section
						orderLib.shippingBillPayContinueButton(); //Click continue on Billing address Section
						orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));  // VISA card
						orderLib.clickOnReviewOrderButton();
						
						// logout
						commonLib.clickLogOutLink(data.get("Logout"));
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("InactiveST", "TC_LNL01", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("InactiveST", "TC_LNL01", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}
