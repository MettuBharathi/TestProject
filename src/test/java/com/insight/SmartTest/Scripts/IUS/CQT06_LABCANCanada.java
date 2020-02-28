package com.insight.SmartTest.Scripts.IUS;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.insight.SmartTest.Lib.ObjectsLib;
import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT06_LABCANCanada extends HomeLib {

	loginLib loginlib = new loginLib();
	ObjectsLib Objlib = new ObjectsLib();
	// #############################################################################################################
	// # Name of the Test : CQT06_LABCANCanada
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
	public void CQT06(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT06_LABCANCanada",TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT06_LABCANCanada", TestData_Smart, "Create_Quote", intCounter);
					fnOpenTest();
					navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
					clickOnCreateQuoteLink();
					enterSoldTo(data.get("SoldToValue"));
					// 10529929
					AddMaterialOnLineItem(data.get("MaterialID1"));  //LAB_CAN
					clickOnRedCrrossSymbolinLineItemsList();
					if(Objlib.verifyAvailabilityOfProductSearchPopup())
					{
					checkStokInOnlyCheckbox();
					Objlib.enterKeywordInProductSearchWindow(data.get("Keyword"));
					Objlib.clickOnSearchButtonInProductSearchWindow();
					verifyResultsofMaterailIdofKeyWordSearch(data.get("MaterailId"));
					Thread.sleep(3000);
						if (verifyAddToOrderPopup()) {
							clickOnTabsInLineItemDetailsPopUp(data.get("Tab")); //Availability
							clickOnSourceNamePlusIcon(data.get("SourceName")); //Channel
							int j=3;
							checkSupplierNameContainsCanada(j);							
						}
					//	 Price = new ArrayList<>();
						List<String> Price= verifyCurrencyTypes(data.get("inputValue"));		                    
		                    for(int i =0;i<Price.size();i++){		                    	
		                     	 String resultprice=Price.get(i);
		                     	reporter.SuccessReport("Currency value ", resultprice, "");
		                    	}
						clickOnAddToOrderButton();
							Objlib.closebuttonInProductSearch();
					}
					selectSourceDDValue(data.get("DDValue"));
					selectLabFeeAndNotes(data.get("LABDDValue"));
					clickDoneButton();
					clickUpdateCosting();
					clickSideBarSmart();
					clickonSaveasQuote();
					Thread.sleep(3000);
					ClickOnSendbutton();										
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
			ReportStatus.fnUpdateResultStatus("CQT06_LABCANCanada", "TC_06",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT06_LABCANCanada", "TC_06",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
