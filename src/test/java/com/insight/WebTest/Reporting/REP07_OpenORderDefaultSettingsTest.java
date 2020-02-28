package com.insight.WebTest.Reporting;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

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

public class REP07_OpenORderDefaultSettingsTest extends CanadaLib{
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
	public void TC_REP07(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_REP07OpenORderDefaultSettings", TestData, "Reporting");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_REP07OpenORderDefaultSettings", TestData,
							"Reporting", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("OpenORderDefaultSettings");
	
	

			//	CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
			//	OrderLib orderLib = new OrderLib();
				CanadaLib canadaLib=new CanadaLib();
				cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),data.get("ContactName"));
				cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.loginAsAdminCMT();
				canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
						data.get("Tools_Menu_DD"));	
				clickOnReportOptions(data.get("ReportOption"));
				verifyReportsPage();
				verifySelectReport(data.get("SelectReport"));
				verifyAccountSelections(data.get("AccountSelections"));
				
				verifyFilterbyCurrency(data.get("Currency"));
				verifyFilterOption();
				verifyScheduleReport(data.get("ScheduleOption"));
				verifyDeliveryOption();
				clickOnAccountSelections(data.get("AccountSelectionsOpt"));
				verifyQuickDateOption(data.get("QuickDateOptions"));
				verifyCustomDate();
				verifySmartcheck();
				
				clickOnDeliveryMethod(data.get("DeliveryMethod"));
				clickOnDeliveryFormat(data.get("DeliveryFormat"));
				clickOnRun();	
				List<String> excelOptions= Arrays.asList(data.get("ExcelOptions").split(","));
			    canadaLib.verifyDownloadedReportExcelFile(excelOptions,data.get("ReportOption"));
				commonLib.clickLogOutLink(data.get("Logout_Header"));
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
			ReportStatus.fnUpdateResultStatus("OpenORderDefaultSettings", "TC_REP07", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("OpenORderDefaultSettings", "TC_REP07", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}