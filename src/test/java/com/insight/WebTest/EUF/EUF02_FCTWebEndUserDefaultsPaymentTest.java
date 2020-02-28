package com.insight.WebTest.EUF;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.EndUserFeaturesLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class EUF02_FCTWebEndUserDefaultsPaymentTest extends EndUserFeaturesLib{
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib = new CanadaLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  EUF02_FCTWebEndUserDefaultsPayment
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test FCTWebEndUserManageSoldtos
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_EUF02(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "EUF02_FCTWebEndUserDefaultsPayment", TestDataInsight, "End_User");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("EUF02_FCTWebEndUserDefaultsPayment", TestDataInsight, "End_USer", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("FCTWebEndUserDefaultsPayment");
                        
						cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
								data.get("ContactName"));
						cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
						// navigate to checkout settings >>  payment options
						cmtLib.selectOptionInCheckoutSettings(data.get("Payment_Options"));
						cmtLib.clearPaymentOptionsInCheckoutSettings();
						cmtLib.selectpaymentOptionsInCheckOutSettings(data.get("Options"));
						cmtLib.selectDefaultPaymentOption(data.get("Default_Payment_Option"));
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						clickOnTabInUserProfile(data.get("Tab_Name"));
						selectedOptionPaymentMethod(data.get("Default_Payment_Option"));
						clickUpdateButtonInCheckoutDefaults();
						cmtLib.navigateBackToCMT();
						// navigate to checkout settings >>  payment options
						cmtLib.clearPaymentOptionsInCheckoutSettings();
						cmtLib.selectpaymentOptionsInCheckOutSettings(data.get("Options1"));
						cmtLib.selectDefaultPaymentOption(data.get("Default_Payment_Option_None"));
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						clickOnTabInUserProfile(data.get("Tab_Name"));
						selectedOptionPaymentMethod(data.get("Default_Payment_Option1"));
						commonLib.clickLogOutLink(data.get("Logout_Header"));
						System.out.println("Test completed");
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("FCTWebEndUserDefaultsPayment", "TC_EUF02", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("FCTWebEndUserDefaultsPayment", "TC_EUF02", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}
