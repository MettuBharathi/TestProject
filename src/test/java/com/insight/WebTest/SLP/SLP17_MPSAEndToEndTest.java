package com.insight.WebTest.SLP;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.RequisitionProcessingLib;
import com.insight.Lib.SLPLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SLP17_MPSAEndToEndTest extends SLPLib{
	// #############################################################################################################
	// # Name of the Test : SLP17_MPSAEndToEnd
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : Purpose of this test method is to verify the compare
	// functionality in the products display page.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void SLP17_MPSAEndToEnd(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SLP17_MPSAEndToEnd", TestData, "SLP");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SLP17_MPSAEndToEnd", TestData, "SLP",
					intCounter);
			// Test Steps execution
			try {
				fnOpenTest();
				CMTLib cmtLib = new CMTLib();
				CommonLib commonLib = new CommonLib();
				OrderLib orderLib = new OrderLib();
				CartLib cartLib = new CartLib();
				SearchLib searchLib = new SearchLib();
				ShipBillPayLib shipbLib = new ShipBillPayLib();
				MarriottIntlCorpLib marriottintlcorpLib=new MarriottIntlCorpLib();
				RequisitionProcessingLib ReqLib = new RequisitionProcessingLib();
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
				String[] permissions = data.get("Set_Permission").split(",");
				for (i = 0; i < permissions.length; i++) {
					cmtLib.setPermissions(data.get("Menu_Name"), permissions[i]);
				}
				cmtLib.loginAsAdminCMT();
				Thread.sleep(3000);
				// Login Verification 
				cmtLib.loginVerification(data.get("ContactName1"));
				commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu1"),
						data.get("Tools_Menu_DD1"), data.get("Product_Group"), data.get("Product_Name"));//Tools,Company Standards,TUMPSATestCat,tuMPSATestGrp
				searchLib.clickAddToOrderOnCompanyStandardsScreen();
				commonLib.clickCart();
				commonLib.verifyBundleIsAddedToCart();
				verifydeploydateandlicensetypeforcartbundle(data.get("Date"));
				marriottintlcorpLib.SaveAsQuote();
				marriottintlcorpLib.Toclicksaveasquoteincompanystandardspage();
				String RefNum=marriottintlcorpLib.VerifySuccessmsgofQuoteandRefNum();
				commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu2"), data.get("Tools_Menu_DD2"));
				orderLib.searchByInQuoteHistory(RefNum, data.get("DD_Option"));
				orderLib.convertQuote();
				orderLib.proceedToCheckout();
				orderLib.continueButtonOnAdditionalInformationSection();
				orderLib.clickContinueOnLineLevelInfo();
				orderLib.shippingBillPayContinueButton();
				orderLib.shippingBillPayContinueButton();
				shipbLib.ClickRviewrequesition();
				String summaryAmount = cartLib.getSummaryAmountInCart();
				clickPlaceRequisition();
				String ReferenceNumber1 = shipbLib.getReferenceNum(); 
				verifyReceiptPageOrderDetails(summaryAmount);
                //Click on Convert Quote-User(1)
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2"));
				cmtLib.loginAsAdminCMT();
				Thread.sleep(3000);
				commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu2"), data.get("Tools_Menu_DD3"));
				selectReferenceNumFromRequisitionSearchResults(ReferenceNumber1);
				selectApproveRadioButtonOnApprovalManagementPage();
				ReqLib.clickUpdateInApprovalManagmentPage();
				clickonEnterNewcard();
				ReqLib.enterNewcarDetails(data.get("cardtype"), data.get("cardNum"), data.get("cardName"));
				ReqLib.continuebutton();
				ReqLib.verifyApproveRequisitionStatus();
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
				String[] permissions1 = data.get("Set_Permission").split(",");
				for (i = 0; i < permissions1.length; i++) {
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), permissions1[i]);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				fnCloseTest();
			}
		}
	}

}
