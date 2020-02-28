package com.insight.WebTest.MIC;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class MIC07_PAAprovalMIGobalTest extends MarriottIntlCorpLib {

	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	OrderLib orderLib = new OrderLib();
	ShipBillPayLib shipbLib = new ShipBillPayLib();
	
	// #############################################################################################################
	// # Name of the Test : MIC07_PAAprovalMIGobal
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_MIC07(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "MIC07_PAAprovalMIGobal", TestDataInsight, "MarriottIntl_Corp");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
		try {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("MIC07_PAAprovalMIGobal", TestDataInsight,"MarriottIntl_Corp", intCounter);
			this.reporter.initTestCaseDescription("PAAprovalMIGobal");
			reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")+ "  **************","");
					
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("Contact_Name"));
			cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
			cmtLib.loginAsAdminCMT();
			handleinsightpopup();
			// METHOD TO SEARCH AND SWITCH ACCOUNT
			SearchAndswitchtoaccount(data.get("AccountName"));
			handleinsightpopup();
			// WELCOME TO E PROCUREMNET PAGE
			VerifyWelcometoeProcurementpage();
			// COMPANY STATNDARDS LINK
			CompanystandardslinkandProductGrpWithbtag(data.get("productGroup"), data.get("productName"),data.get("FieldOnly"));
			handleinsightpopup();
			// verify quantity,price,part number
			Verifypartnum(data.get("partnum1"));
			VerifypartPrice(data.get("partprice"));
			Addcheckbox(data.get("partnum1"));
			Setquantity(data.get("value"), data.get("partnum"));
			Thread.sleep(10000);
			clickOnViewCart();
			handleinsightpopup();
			Thread.sleep(10000);
			cartLib.verifyItemInCart(data.get("partnum1"));
			shipbLib.AdditemsbyQuickshop(data.get("PartNum2"));
			Thread.sleep(2000);
			cartLib.verifyItemInCart(data.get("PartNum2"));
			shipbLib.AdditemsbyQuickshop(data.get("PartNum3"));
			Thread.sleep(2000);
			cartLib.verifyItemInCart(data.get("PartNum3"));
			orderLib.proceedToCheckout();
			addAdditionalInfoOfProduct(data.get("Brand_Identifier"), data.get("Requester_Name"),
					data.get("EndUser_PeopleSoftNumber"), data.get("Notes"), data.get("Customer_Reference_Number"),
					data.get("PC_User_Name"), data.get("PC_End_User_Div_Unit_Dept"), data.get("End_User_Email"),
					data.get("Approving_Manager_Email"), data.get("Non_IRFA_PC"));
			addAdditionalInfo(data.get("Name"),data.get("Phone"),data.get("Email"));
			addShippingInfo(data.get("Ship_Attention"), data.get("Ship_Suite"), data.get("Ship_Phone"));
			shippingOptionContinueButton();
			addBillingInfo(data.get("Bill_Attention"), data.get("Bill_Suite"), data.get("Bill_Phone"));
			shipbLib.ClickRviewrequesition();
			VerifyBrandidentifier(data.get("Brand_Identifier"));
			verifyrequestorname(data.get("Requester_Name"));
			verifypcusername(data.get("PC_User_Name"));
			Verifynotes(data.get("Notes"));
			Verifycustomerreference(data.get("Customer_Reference_Number"));
			VerifyEnduserText(data.get("EndUser_PeopleSoftNumber"));
			verifyapprovingmanageremail(data.get("Approving_Manager_Email"));
			verifyEnduseremail(data.get("End_User_Email"));
			verifynonirfapc( data.get("Non_IRFA_PC"));
			verifyenduserdiv( data.get("PC_End_User_Div_Unit_Dept"));
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
		ReportStatus.fnUpdateResultStatus("MIC07_PAAprovalMIGobal", "MIC07", ReportStatus.strMethodName, intCounter, browser);
		fnCloseTest();
	}
} catch (Exception e) {
	e.printStackTrace();
	ReportStatus.blnStatus = false;
	gErrorMessage = e.getMessage();
	gTestStatus = false;
	ReportStatus.fnUpdateResultStatus("MIC07_PAAprovalMIGobal", "MIC07", ReportStatus.strMethodName, 1, browser);
	throw new RuntimeException(e);
}

ReportControl.fnNextTestJoin(nextTestJoin);
}
}



