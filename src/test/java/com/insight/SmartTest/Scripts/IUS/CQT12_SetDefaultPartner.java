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

public class CQT12_SetDefaultPartner extends HomeLib {
	loginLib loginlib=new loginLib();
	// #############################################################################################################
			// # Name of the Test : CQT12_SetDefaultPartner
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
			public void CQT03_UpdQtyRepMargin(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
				int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT03_UpdQtyRepMargin", TestData_Smart, "Create_Quote");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT03_UpdQtyRepMargin", TestData_Smart, "Create_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("UpdQtyRepMargin");
						navigateToApplication("SMART");
						loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"));
						enterAccountNumber(data.get("AccountNum"));
						clickonExpandedMenuName(data.get("SubTab"),data.get("TabName"));
						clickPartnerID();
						clickonSaveChangesButton();
						clickOnCreateQuoteLink();
						getTextfromShipToField();
						clickOnSoldToSearchIcon();//10933584
		                String Shipto= getTextfromShipToField();
		                enterAccountNumber(data.get("AccountNum"));
						clickonExpandedMenuName(data.get("SubTab"),data.get("TabName"));
						clickPartnerID();
						clickonSaveChangesButton();
						clickOkButton();
						clickSideBarSmart();
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
					//ReportStatus.fnUpdateResultStatus("UpdQtyRepMargin", "CQT_12", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
		        finally {
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("UpdQtyRepMargin", "CQT_12", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}


		}
