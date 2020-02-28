package com.insight.SmartTest.Scripts.IUS;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT22_IPSContractpricing_ContractReviewContract extends HomeLib {

	loginLib loginlib = new loginLib();
	// #############################################################################################################
	// # Name of the Test : CQT22_IPSContractpricing_ContractReviewContract
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : Nov 2019
	// # DESCRIPTION :
	// functionality in the products display page.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void CQT22(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT22_IPSContractpricing_ContractReviewContract",TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT22_IPSContractpricing_ContractReviewContract", TestData_Smart, "Create_Quote", intCounter);
					fnOpenTest();
					navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
					clickOnCreateQuoteLink();
					enterSoldTo(data.get("SoldToValue"));
					// 10529929
					AddMaterialOnLineItem(data.get("MaterialID1"));// WS-C2960X-24PS-L
					AddMaterialOnLineItem(data.get("MaterialID2"));// U4414E
					AddMaterialOnLineItem(data.get("MaterialID3"));// 4X90F84315
					clickOnCOntractIDinLineItemsList();
					selectCOntractID(data.get("contactid"), data.get("contactTabName"));
					copyAllContractstoAllLines();
					clickDoneButton();
					clickUpdateCosting();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					clickOnCOntractIDinLineItemsList();
					selectCOntractSubTabName(data.get("contractTab"));// General
					selectReasonforRejectionDD(data.get("DropdownValue"));
					selectReasonforRejectionDD(data.get("DropdownValue"));
					selectReasonforRejectionDD(data.get("DropdownValue"));
					clickDoneButton();
                    // Need to modify Rep margin percentage to 10%
					enterRepMarginPercentinLineItem();
					clickUpdateCosting();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					clickSideBarSmart();
					clickonSaveasQuote();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					enterCancelButtonInPoupHdr();
					String QuoteNum1 = GetQuoteNumber();
					clickOnCOntractIDinLineItemsList();
					selectCOntractSubTabName(data.get("contractTab1"));// Pricing
					// Get data from the pricing tab
					List<Float> Price = new ArrayList<>();
					int a = Integer.parseInt(data.get("Value"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"), a);// ZPFX--0
					System.out.println(Price.size());
					float Price1 = 0;
					float Price2 = 0;
					float Price3 = 0;
					float Price4 = 0;
					for (int i = 0; i < Price.size(); i++) {
						if (i == 0) { // ZPFX--0
							Price1 = Price.get(i);
						}
						if (i == 1) { // Z0RC--1
							Price2 = Price.get(i);
						}
						if (i == 2) { // ZMAM--2
							Price3 = Price.get(i);
						}
						if (i == 3) { // ZP00--3
							Price4 = Price.get(i);
						}
					}
					selectCOntractSubTabName(data.get("contactTabName"));// Contracts
					VerifyContractPriceShouldbeEqualToYPOO(Price1, data.get("contactid"));// ZPFX
																							// equals
																							// to
																							// contract
																							// id
																							// sell
																							// price
					clickDoneButton();
					verifyZPOOequalstheYDLPandZPLS(Price4, Price3, Price2);// ZP=ZMAM+Z0RC
					verifyZPFXValue(Price1);
					clickSideBarSmart();
					clickClosthedocument(QuoteNum1);
					clickYesButtontocloseDocument();

					System.out.println("Test completed");

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.toString();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("CQT22_IPSContractpricing_ContractReviewContract", "TC_022",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT22_IPSContractpricing_ContractReviewContract", "TC_022",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
