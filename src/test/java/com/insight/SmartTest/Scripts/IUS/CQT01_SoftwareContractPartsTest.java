package com.insight.SmartTest.Scripts.IUS;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT01_SoftwareContractPartsTest extends HomeLib {
	
	loginLib loginlib = new loginLib();

	// #############################################################################################################
	// # Name of the Test : CQT01_SoftwareContractParts
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
	public void CQT01_SoftwareContractParts(int StartRow, String EndRow, boolean nextTestJoin)
			throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT01_SoftwareContractParts",
					TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo(
							"CQT01_SoftwareContractParts", TestData_Smart, "Create_Quote",
							intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SoftwareContractParts");
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"), data.get("Password"),
							data.get("SoldToAcct"), data.get("SalesOrg"));
					clickAdvancedHeader();
					clickAdvancedHeaderTab(data.get("Tab1"));//programs Tab
					Productsearch(data.get("ContractId"));//0040049088
					SearchButton();
					verifyResultsofMaterailIdofKeyWordSearch(data.get("MaterialId"));//6529107
					clickOnAddToOrderButton();
					clickCloseButton();
					clickonConXSystem(data.get("ItemNum1"));// 000010
					//verify same contract is in the popup
					ClickOnCopyContracttoallLines();
					clickYesButtontocloseDocument();
					clickDoneButton();
					clickUpdateCosting();
					String repCost1=getRepCostofLineItem(data.get("ItemNum1"));
					String clinetCost1=getClientCostofLineItem(data.get("ItemNum1"));
					String price1=getPriceofLineItem(data.get("ItemNum1"));
					verifyRepCostClientCostPriceoflineitem(repCost1,clinetCost1,price1);
					clickonSaveasQuote();
					EnterEmail(data.get("Email"));
					ClickOnSendbutton();
					ClickOnDisplayMode();
					clickOnProductSearchButton();
					enrollmentDropDown(data.get("enrollment"));
					clickOnVIPCheckBox();
					clickOnSaveButtonOnOptionsAndLevelsPopup();
					SearchButton();
					verifyResultsofMaterailIdofKeyWordSearch(data.get("MaterialId"));
					clickOnAddToOrderButton();
					clickCloseButton();
					clickUpdateCosting();
					String repCost2=getRepCostofLineItem(data.get("ItemNum2"));
					String clinetCost2=getClientCostofLineItem(data.get("ItemNum2"));
					String price2=getPriceofLineItem(data.get("ItemNum2"));
					verifyRepCostClientCostPriceoflineitem(repCost2,clinetCost2,price2);
					verifyConvertToOrderBtnIsNotAvailable();
					rejectitem(data.get("ItemNum2"));
					clickYesToRejectItem();
					Thread.sleep(3000);
					selectReasonToRejectItem(data.get("reason"));//Delivery date too late
					clickYesToRejectItem();
					VerifycolourAfterRejectingItem(data.get("colour"));
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
			// ReportStatus.fnUpdateResultStatus("IPSContractPricingRoundingWithMultipleQtys",
			// "CQT_45", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SoftwareContractParts", "CQT_01",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
