package com.insight.WebTest.SLP;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.SLPLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SLP18_SoftwareprorationTaxIPSTest extends SLPLib {
	// #############################################################################################################
	// # Name of the Test : SLP18_SoftwareprorationTaxIPS
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
	public void SLP18_SoftwareprorationTaxIPS(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SLP18_SoftwareprorationTaxIPS", TestData, "SLP");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SLP18_SoftwareprorationTaxIPS", TestData, "SLP",
					intCounter);
			// Test Steps execution
			try {
				fnOpenTest();
				CMTLib cmtLib = new CMTLib();
				CommonLib commonLib = new CommonLib();
				OrderLib orderLib = new OrderLib();
				CartLib cartLib = new CartLib();
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.loginAsAdminCMT();
				commonLib.searchProduct(data.get("PartNum"));
				Double Actualprice = Double.valueOf(getpricefromProductdetailpage());
				commonLib.addToCartAndVerify();
				commonLib.clickCart();
				verifyProrationincartpage(data.get("PartNum"), Actualprice);
				orderLib.proceedToCheckout();
				orderLib.clickContinueOnLineLevelInfo();
				orderLib.shippingBillPayContinueButton();
				orderLib.shippingBillPayContinueButton();
				orderLib.termsInPaymentInfo(data.get("PONumber"));
				verifyProrationinplaceorderpage(data.get("Subtotal"), Actualprice);
				String amount = cartLib.getSummaryAmountInCart();
				orderLib.placeOrderAndVerifyReceiptOrderAndDate(amount);
				verifyProrationinrecieptpage(Actualprice);
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				fnCloseTest();
			}
		}
	}

}
