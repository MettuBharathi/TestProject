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
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.ReportingLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.SewpLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class REP04_AdHocReporOutputFormatTest extends ReportingLib {
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib = new ProductDetailLib();
	OrderLib orderLib = new OrderLib();
	ProductDisplayInfoLib pipLib = new ProductDisplayInfoLib();
	SewpLib sewpLib = new SewpLib();
	ShipBillPayLib shipbLib = new ShipBillPayLib();
	MarriottIntlCorpLib mic = new MarriottIntlCorpLib();

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_REP04(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		
		
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_REP04AdHocReporOutputFormat", TestData, "Reporting");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_REP04AdHocReporOutputFormat", TestData,
							"Reporting", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("AdHocReporOutputFormat");
	
		
					CanadaLib canadaLib = new CanadaLib();				
					InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
							
					CommonLib commonLib = new CommonLib();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
					cmtLib.permissionFromDD(data.get("Set_Permission3"), data.get("Permission_Dropdown_Option"));
					cmtLib.loginAsAdminCMT();
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.clickOnReportOptions(data.get("ReportOption"));
					canadaLib.verifyReportsPage();
					canadaLib.verifySelectReport(data.get("SelectReport"));
					canadaLib.clickOnAccountSelections(data.get("AccountSelectionOpt"));
					invoiceHistoryLib.verifyTree();
					canadaLib.clickOnDeliveryMethod(data.get("DeliveryMethod"));
					enterEmails(data.get("Emails"));
					canadaLib.clickOnRun();
					verifWarrning();
					selectScheduleReport(data.get("ScheduleReport"));
					enterTemplates(data.get("TemplateName"));
					clickOnSave();
					verifyReportTemplates();
					expandReportTemplateAndVerify(data.get("TemplateName"));
					clickOnDelete(data.get("SelectReport"));
					
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.clickOnReportOptions(data.get("ReportOption"));
					canadaLib.verifyReportsPage();
					canadaLib.verifySelectReport(data.get("SelectReport"));
					verifyInvoiceDateChecked() ;
					canadaLib.clickOnDeliveryMethod(data.get("DeliveryMethod1"));
					canadaLib.clickOnDeliveryFormat(data.get("DeliveryFormat"));
					canadaLib.clickOnRun();	
					commonLib.spinnerImage();
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
							ReportStatus.fnUpdateResultStatus("AdHocReporOutputFormat", "TC_REP04", ReportStatus.strMethodName, 1, browser);
							throw new RuntimeException(e);
						}
				        finally {
				        	ReportControl.fnEnableJoin();
							ReportStatus.fnUpdateResultStatus("AdHocReporOutputFormat", "TC_REP04", ReportStatus.strMethodName, counter, browser);
							fnCloseTest();
							ReportControl.fnNextTestJoin(nextTestJoin);
						}
					}

}