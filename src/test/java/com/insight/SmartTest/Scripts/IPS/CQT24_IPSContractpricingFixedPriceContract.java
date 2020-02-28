package com.insight.SmartTest.Scripts.IPS;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.ObjectsLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ActionEngine;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class CQT24_IPSContractpricingFixedPriceContract extends ActionEngine {

	/**
	 * param :: String inputs return ::void throws :: throwable 
	 * TestName :: TC_024
	 * description :: This Test is used to CreateQuote with heavy weight part
	 * Date :: Nov-2019 
	 * author :: Priyanka
	 */
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test()

	public void TC_CQT24(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT24", TestData_Smart,"Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT24", TestData_Smart,"Create_Quote", intCounter);
					this.reporter.initTestCaseDescription(
							"IPSContractpricingFixedPriceContract");

					reporter.SuccessReport("Iteration Number : ", "**************Iteration Number::  " + intCounter
							+ " To validate:: Favorites_Logged In User_Verify user is able to Add products to favorites_Remove Favorites in PLP/PDP **************","");

					HomeLib home=new HomeLib();
					loginLib  login=new loginLib();
					ObjectsLib  object=new ObjectsLib();
					navigateToApplication("SMART");
					//Login functionality
					login.loginIntoSmartApplication(data.get("username"),data.get("password"));
					//Home Page
					home.clickCreateQuoteButton();
					home.clickSideBarSmart();
					home.enterSoldTo("0010529929");
					//home.clickAdvancedHeader();
					//home.clickAdvancedHeaderTab(data.get("contracts"));
					home.clickOnProductSearchButton();
					if(object.verifyAvailabilityOfProductSearchPopup())
					{

						String inputValue = data.get("keyword");
						String facilityCodeValue[] = inputValue.split("#");
						for(int i=0;i<facilityCodeValue.length;i++) {
							if(!object.verifyAvailabilityOfKeywordSearchTextField()){
								home.clickOnProductSearchButton();
							}
							object.enterKeywordInProductSearchWindow(facilityCodeValue[i]);
							object.clickOnSearchButtonInProductSearchWindow();

							if (object.clickOnMaterialID()) {
								Thread.sleep(20000);
								if (home.verifyAddToOrderPopup()) {
									home.clickOnAddToOrderButton();
									object.closebuttonInProductSearch();
								} else {
									reporter.failureReport("Add to Order Popup", "Add to order popup is not visible", "", driver);
								}
							}else {
								reporter.failureReport("Results found Status","No results found","",driver);
							}
						}

									object.clickOnCrossiConUnderConColSingleRow();
									if(object.availabilityOfItemDetailsPopup())
									{
										home.clickOnTabsInLineItemDetailsPopUp(data.get("Contracts"));
										object.clickOnContactIDByPassingValueFromExcel(data.get("ContractID"));
										home.clickOnCopyContarctToallLineItems();
										home.clickYesButtontocloseDocument();
										object.clickOnDoneButtonInContractsTab();
										Thread.sleep(25000);
										home.clickUpdateCosting();
										Thread.sleep(25000);
										home.clickonSaveasQuote();
										Thread.sleep(25000);
										home.clickCancel();
										String QuoteNumber=object.getQuoteNumberValue();
										object.clickOnCrossiConUnderConColSingleRow();
										object.clickOnContactIDByPassingValueFromExcel(data.get("ContractID"));
										String sellingPrice=object.getContractPricingFromTable(data.get("ContractID"));
										String sellingPrice1=object.getContractPricingFromTable(data.get("ContractID"));
										//String sellingPrice=object.getSellPriceFromContract(data.get("ContractID"));

										home.clickOnTabsInLineItemDetailsPopUp("Pricing");

										String priceValue=object.selectproductinPricingTable(data.get("priceType"));
										String priceValue1=object.selectproductinPricingTable(data.get("priceType1"));

										//******************* Validations
if(data.get("TC_ID").equalsIgnoreCase("CQT24")) {
	if (priceValue.equalsIgnoreCase(priceValue1)) {
		reporter.SuccessReport("Price value comparision", data.get("priceType") + "Value is " + priceValue + " and  " + data.get("priceType1") + "Value is " + priceValue1 + "both are same", "");
	} else {
		reporter.failureReport("Price value comparision", data.get("priceType") + "Value is " + priceValue + " and  " + data.get("priceType1") + "Value is " + priceValue1 + "both are same", "", driver);
	}
	if (sellingPrice.equalsIgnoreCase(priceValue)) {
		reporter.SuccessReport("Price value comparision", "both are same", "");
	} else {
		reporter.failureReport("Price value comparision", "both are same", "", driver);
	}
}

									}else{
										//Item Details popup failure
									}











					}
					else{

					}



					System.out.println("Test completed");
				} catch (Exception e) {
					ReportStatus.blnStatus = false;

					gErrorMessage = e.getMessage();
					System.out.println(gErrorMessage);
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("AddRemoveFavoritesAsLoggedInUser", "TC_029", ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
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