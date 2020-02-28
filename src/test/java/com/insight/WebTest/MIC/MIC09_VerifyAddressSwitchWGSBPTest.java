package com.insight.WebTest.MIC;

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

public class MIC09_VerifyAddressSwitchWGSBPTest extends MarriottIntlCorpLib {
	
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	ShipBillPayLib shipbLib = new ShipBillPayLib();
	OrderLib orderLib = new OrderLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib productDispinfoLib=new ProductDisplayInfoLib();
	
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
	public void Tc_MIC09(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "MIC09_VerifyAddressSwitchWGSBP", TestDataInsight, "MarriottIntl_Corp");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("MIC09_VerifyAddressSwitchWGSBP", TestDataInsight,"MarriottIntl_Corp", intCounter);
					this.reporter.initTestCaseDescription("VerifyAddressSwitchWGSBP");
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")+ "  **************","");
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("Contact_Name"));
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
					String CompanyName1 = TakeshippingaddressCompany();
					Thread.sleep(1000);
					Object Address1 = Takeshippingaddress();
					SwitchWebGroup(data.get("webgrp"));
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.clickCart();
					commonLib.clickCart();
					// proceed to checkout
					orderLib.proceedToCheckout();
					addAdditionalInfoOfProductSWP(data.get("endUserEmail"), data.get("country"),
							data.get("enduserunit"), data.get("endusermarsha"));
					cartLib.clickOnContinueButtonInAddInformtion();
					orderLib.shippingBillPayContinueButton();
					orderLib.shippingOptionsCarrierSelection();
					orderLib.shippingBillPayContinueButton();
					Thread.sleep(2000);
					String CompanyName2 = TakeshippingaddressCompany();
					Thread.sleep(1000);
					Object Address2 = Takeshippingaddress();
					Thread.sleep(3000);
					CompareaddressCompanyname(CompanyName1, CompanyName2);
					Thread.sleep(3000);
					Compareaddress(Address1, Address2);
					commonLib.clickLogOutLink(data.get("Logout_Header"));
			} catch (Exception e) {
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
			}
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("VerifyAddressSwitchWGSBP", "MIC09", ReportStatus.strMethodName, intCounter, browser);
			fnCloseTest();
		}
	} catch (Exception e) {
		e.printStackTrace();
		ReportStatus.blnStatus = false;
		gErrorMessage = e.getMessage();
		gTestStatus = false;
		ReportStatus.fnUpdateResultStatus("VerifyAddressSwitchWGSBP", "MIC09", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}

	ReportControl.fnNextTestJoin(nextTestJoin);
}
}




