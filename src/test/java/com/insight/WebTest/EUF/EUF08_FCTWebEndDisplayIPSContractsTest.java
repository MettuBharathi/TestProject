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

public class EUF08_FCTWebEndDisplayIPSContractsTest extends EndUserFeaturesLib{

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
	// #       Name of the Test         :  EUF08_FCTWebEndDisplayIPSContracts
	// #       Migration Author         :  Cigniti Technologies
	// #
	// #       Date of Migration        : October 2019
	// #       DESCRIPTION              : To Test IP Contracts Drop Down after Login As
	// #       Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_EUF08(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "EUF08_FCTWebEndDisplayIPSContracts", TestDataInsight, "End_User");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("EUF08_FCTWebEndDisplayIPSContracts", TestDataInsight, "End_USer", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("FCTWebEndDisplayIPSContracts");
                    
					// Login to CMT
					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					// Enable Open Market ; ON
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
					// Enable Contracts/Agencies;ON";
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
					// login As
					cmtLib.loginAsAdminCMT();
					//Select > U.S. COMMUNITIES IT PRODUCTS
					searchLib.selectNewcontract(data.get("Contract_name1"));
					prodDetailsLib.verifyContractDetails();
					commonLib.clickLogOutLink(data.get("Logout"));
					
					// Navigate back to CMT >>  Enable Open Market ; OFF;
					cmtLib.navigateBackToCMT();
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
					// login As
					cmtLib.loginAsAdminCMT();
					//Select > Open market
					verifyContractExistsInDropDownOrNot(data.get("Contract_name2"), "OFF");
					commonLib.clickLogOutLink(data.get("Logout"));  // Logout 
					
					// Navigate back to CMT >>  Enable Open Market ; ON;
					cmtLib.navigateBackToCMT();
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
					// Enable Contracts/Agencies;OFF";
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
					// login As
					cmtLib.loginAsAdminCMT();
					pipLib.getSelectedContract(data.get("Contract_name2"));
					commonLib.clickLogOutLink(data.get("Logout"));
					
					// navigate back t CMT
					cmtLib.navigateBackToCMT();
					// Enable Contracts/Agencies;ON";
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
					// End of test
					
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("FCTWebEndDisplayIPSContracts", "TC_EUF08", ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("FCTWebEndDisplayIPSContracts", "TC_EUF08", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}
	
	
	
}
