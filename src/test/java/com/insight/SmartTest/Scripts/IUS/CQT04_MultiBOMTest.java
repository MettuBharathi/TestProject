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

public class CQT04_MultiBOMTest extends HomeLib{
	
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : CQT04_MultiBOM
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION : Purpose of this test method is to verify MultiBOM
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT04(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT04_MultiBOM", TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT04_MultiBOM", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("MultiBOM");
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"),data.get("Password"),data.get("SoldToAcct"),data.get("SalesOrg"));
					Addmaterail(data.get("Material1"));//IUSA
					loadingSymbol();
					Addmaterail(data.get("Material2"));//AIUSA
					loadingSymbol();
					Addmaterail(data.get("Material3"));//SOFTWARE
					loadingSymbol();
					Addmaterail(data.get("Material4"));//PARTNERCTO
					loadingSymbol();
					verifyandClickonVCofLineitem(data.get("ItemNum"));
					AddmaterailAtVCIconInItemDetailsPopUp(data.get("material"));
					clickonNextLineItemArrowsymbolinPopUp();
					AddmaterailAtVCIconInItemDetailsPopUp(data.get("material"));
					clickonNextLineItemArrowsymbolinPopUp();
					AddmaterailAtVCIconInItemDetailsPopUp(data.get("material"));
					clickonNextLineItemArrowsymbolinPopUp();
					AddmaterailAtVCIconInItemDetailsPopUp(data.get("material"));
					clickonNextLineItemArrowsymbolinPopUp();
					labFeeDropDown(data.get("option"));//99-ASSET
					clickDoneButton();
					clickUpdateCosting();
					Thread.sleep(6000);
					clickSideBarSmart();
					clickonSaveasQuote();
					getQuoteNum();
					enterNewEmailIDandclickOKBtn(data.get("email"));
					/*
					 * EnterEmail(data.get("email"));//qatester01@insight.com ClickOnSendbutton();
					 * loadingSymbol(); clickOKinErrormsgBox();
					 */
					String QuoteNum= GetQuoteNumber();
					clickClosthedocument(QuoteNum);
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
			//ReportStatus.fnUpdateResultStatus("MultiBOM", "CQT_04", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("MultiBOM", "CQT_04", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}