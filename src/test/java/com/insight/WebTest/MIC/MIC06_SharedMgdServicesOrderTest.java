package com.insight.WebTest.MIC;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class MIC06_SharedMgdServicesOrderTest extends MarriottIntlCorpLib {//MIC06_SharedMgdServicesOrder

	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	OrderLib orderLib = new OrderLib();
	
	// #############################################################################################################
	// # Name of the Test : MIC06_SharedMgdServicesOrder
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_MIC06(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "MIC06_SharedMgdServicesOrder", TestDataInsight, "MarriottIntl_Corp");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("MIC06_SharedMgdServicesOrder", TestDataInsight,"MarriottIntl_Corp", intCounter);
					this.reporter.initTestCaseDescription("SharedMgdServicesOrder");
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")+ "  **************","");
							cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("Contact_Name"));
			cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));// Enable
			cmtLib.loginAsAdminCMT();
			handleinsightpopup();
			// METHOD TO SEARCH AND SWITCH ACCOUNT
			SearchAndswitchtoaccount(data.get("AccountName"));
			handleinsightpopup();
			// WELCOME TO E PROCUREMNET PAGE
			VerifyWelcometoeProcurementpage();
			// COMPANY STATNDARDS LINK
			CompanystandardslinkandProductGrp(data.get("productGroup"), data.get("productName"));
			handleinsightpopup();
			// verify quantity,price,part number
			Verifypartnum(data.get("partnum"));
			VerifypartPrice(data.get("partprice"));
			Addcheckbox(data.get("partnum"));
			Setquantity(data.get("value"), data.get("partnum"));
			Thread.sleep(5000);
			clickOnViewCart();
			handleinsightpopup();
			cartLib.verifyItemInCart(data.get("partnum"));
			orderLib.proceedToCheckout();
			additionalinfo(data.get("Brand_Identifier"),data.get("PC_Laptop"),data.get("Notes"), data.get("PC_User_Name"),
					data.get("PC_End_User_MARSHA"), data.get("MARSHA_of_Approver"),
					data.get("name"), data.get("phone"), data.get("email"));
			orderLib.shippingBillPayContinueButton();
			addShippingInfo(data.get("Ship_Attention"), data.get("Ship_Suite"), data.get("Ship_Phone"));
			addBillingInfo(data.get("Bill_Attention"), data.get("Bill_Suite"), data.get("Bill_Phone"));
			orderLib.clickOnReviewOrderButton();
			VerifyBrandidentifier(data.get("Brand_Identifier"));
			verifyordercontainapcornot(data.get("PC_Laptop"));
			verifyendusermarsha(data.get("PC_End_User_MARSHA"));
			verifypcusername(data.get("PC_User_Name"));
			verifyrequestoremail(data.get("MARSHA_of_Approver"));
			Verifynotes(data.get("Notes"));
			verifyShippingattention(data.get("Ship_Attention"));
			verifybillingattention(data.get("Bill_Attention"));
			verifyPayementInfo(data.get("PAYMENT_TYPE"));
			commonLib.clickLogOutLink(data.get("Logout_Header"));
			cmtLib.navigateBackToCMT();
			cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("Contact_Name"));
			cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
			} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("SharedMgdServicesOrder", "MIC06", ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("SharedMgdServicesOrder", "MIC06", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}
	}
