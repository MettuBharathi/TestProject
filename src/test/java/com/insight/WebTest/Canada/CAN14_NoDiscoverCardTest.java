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
import com.insight.Lib.SewpLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN14_NoDiscoverCardTest extends CanadaLib  {
	
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	SewpLib sewpLib=new SewpLib();
	ShipBillPayLib shipbLib=new ShipBillPayLib();
	MarriottIntlCorpLib mic=new MarriottIntlCorpLib();
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_CAN14(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try{
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC14_CAN14NoDiscoverCard", TestDataInsight, "Canada");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			try{
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("TC14_CAN14NoDiscoverCard", TestDataInsight,
					"Canada", intCounter);
			TestEngineWeb.reporter
					.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
							+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
					"");
			CommonLib commonLib = new CommonLib();
			CMTLib cmtLib = new CMTLib();
			CartLib cartLib = new CartLib();
			OrderLib orderLib = new OrderLib();
			CanadaLib canadaLib = new CanadaLib();

			navigateTo("https://ca-uat1.insight.com/insightweb/login");
			canadaLib.CandaHomePageVerification();
			cmtLib.loginAsEndUser(data.get("userName"),data.get("Password"));
			Thread.sleep(2000);
				
			shipbLib.PaymentandCardsTextverify(data.get("Tools_Menu"), data.get("Tools_Menu_DD"), data.get("tabName2"));
			clickOnEnterACard(data.get("Creditcard"));
			verifyNoDiscoverCard(data.get("ProcurementCard"));
			commonLib.searchProduct(data.get("ProductName"));
			commonLib.addFirstDisplyedItemToCartAndVerify();				
			continueToCheckout();			
			orderLib.proceedToCheckout();
			cartLib.clickOnContinueButtonInAddInformtion();
			canadaLib.verifySBP();
			orderLib.shippingBillPayContinueButton();
			orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
			orderLib.shippingBillPayContinueButton(); // Billing address continue button
			orderLib.selectPaymentInfoMethodCreditCard(data.get("cardNumber"), data.get("cardName"), data.get("month"),
					data.get("year"),data.get("PO_Number"));
			orderLib.clickOnReviewOrderButton();
			mic.SwitchWebGroup(data.get("webGroup"));
			verifyCountry(data.get("Country"));
			commonLib.searchProduct(data.get("ProductName"));
			commonLib.addFirstDisplyedItemToCartAndVerify();				
			continueToCheckout();			
			orderLib.proceedToCheckout();
			cartLib.clickOnContinueButtonInAddInformtion();
			orderLib.clickContinueOnLLIAndShipBillPaySections(); // Billing address continue button
			orderLib.selectPaymentInfoMethodCreditCard(data.get("discoverNumber"), data.get("cardName"), data.get("month"),
					data.get("year"),data.get("PO_Number"));
			orderLib.clickOnReviewOrderButton();
			
			commonLib.clickLogOutLink(data.get("Logout_Header"));
			
		  } catch (Exception e) 
			{
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
		}
		ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("AddRemoveFavoritesAsLoggedInUser", "TC_029", ReportStatus.strMethodName, intCounter, browser);
		fnCloseTest();
		}
		}
   
		catch (Exception e)
		{
	e.printStackTrace();
	ReportStatus.blnStatus = false;
	gErrorMessage = e.getMessage();
	gTestStatus = false;
	ReportStatus.fnUpdateResultStatus("AddRemoveFavoritesAsLoggedInUser", "TC_029", ReportStatus.strMethodName, 1, browser);
	throw new RuntimeException(e);
}

ReportControl.fnNextTestJoin(nextTestJoin);

	}
}

