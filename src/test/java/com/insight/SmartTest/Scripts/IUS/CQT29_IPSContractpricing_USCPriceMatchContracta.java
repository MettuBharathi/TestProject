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

public class CQT29_IPSContractpricing_USCPriceMatchContracta extends HomeLib {

	loginLib loginlib = new loginLib();
	// #############################################################################################################
	// # Name of the Test : CQT29_IPSContractpricing_USCPriceMatchContracta
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
	public void CQT29(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT29_IPSContractpricing_USCPriceMatchContracta",TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT29_IPSContractpricing_USCPriceMatchContracta", TestData_Smart, "Create_Quote", intCounter);
					fnOpenTest();
					navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
					clickOnCreateQuoteLink();
					enterSoldTo(data.get("SoldToValue"));
					// 10529929
					AddMaterialOnLineItem(data.get("MaterialID1"));// CON-ISV1-VSSTD1A
					AddMaterialOnLineItem(data.get("MaterialID2"));// PWR-400W-AC
					AddMaterialOnLineItem(data.get("MaterialID3"));// WS-C2960X-24PS-L
					clickOnCOntractIDinLineItemsList();
					selectCOntractID(data.get("contactid"), data.get("contactTabName"));
					copyAllContractstoAllLines();
					clickDoneButton();
					clickUpdateCosting();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					selectCOntractSubTabName(data.get("contactTabName"));// Contracts
					// Need to verify reporting field6
					String strRepfld6contract= VerifyReportingField6(data.get("contactid"));
					float strRepfld6 = Float.valueOf(strRepfld6contract);
					String contractid2SellPrice = VerifyReportingField6SellPriceValue();
					float field6Sellprice = Float.valueOf(contractid2SellPrice);
					int contract1 =getSellPriceFromContract(data.get("contractid"));
					float sellprice1 = Float.valueOf(contract1);
					selectCOntractSubTabName(data.get("contactTabName1"));// Pricing
					// Get data from the pricing tab
					List<Float> Price = new ArrayList<>();
					int a = Integer.parseInt(data.get("Value"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"), a);// ZP00--0
					System.out.println(Price.size());
					float Price1 = 0;
					for (int i = 0; i < Price.size(); i++) {
						if (i == 0) { // ZP00--0
							Price1 = Price.get(i);
						}
					}
					clickDoneButton(); 
					if(Price1==field6Sellprice ){  //Verify contract price equals to ZP00
						reporter.SuccessReport("verify the Contract "+strRepfld6+" price equals to ZP00 value.", "Contract price equals to ZP00",
								"Contract Sell Price:"+field6Sellprice+"- ZP00:"+Price1);
					} else {
						reporter.failureReport("verify the Contract "+strRepfld6+" price equals to ZP00 value.","Contract price not equals to ZP00"
								,"Contract Sell Price:"+field6Sellprice+"- ZP00:"+Price1, driver);
					
					}
					clickSideBarSmart();
					clickonSaveasQuote();
					enterCancelButtonInPoupHdr();
					String QuoteNum1= GetQuoteNumber();
					clickOnCOntractIDinLineItemsList();
					selectCOntractSubTabName(data.get("contactTabName1"));// Pricing
					// Get data from the pricing tab
					List<Float> Price12 = new ArrayList<>();
					int b = Integer.parseInt(data.get("Value"));
					Price12 = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"), a);// ZP00--0
					System.out.println(Price12.size());
					float Price2 = 0;
					for (int i = 0; i < Price12.size(); i++) {
						if (i == 0) { // ZP00--0
							Price2 = Price.get(i);
						}
					}
					clickDoneButton(); 
					if(Price1==Price2){
						reporter.SuccessReport("verify the  ZP00 value for line item 10", "Exists as Expected",
								"ZP00:"+Price1);
					} else {
						reporter.failureReport("verify the  ZP00 value for line item 10","Does not exists"
								,"ZP00:"+Price2, driver);
					}
					
					clickSideBarSmart();
					Thread.sleep(3000);
					clickClosthedocument(data.get("Doctype"));
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
			ReportStatus.fnUpdateResultStatus("CQT29_IPSContractpricing_USCPriceMatchContracta", "TC_029",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT29_IPSContractpricing_USCPriceMatchContracta", "TC_029",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
