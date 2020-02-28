package com.insight.WebTest.MIC;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class MIC05_MGSPropAssocQuoteTest extends MarriottIntlCorpLib {

	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	
	// #############################################################################################################
	// # Name of the Test : MIC05_MGSPropAssocQuote
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_MIC05(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
	try {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "MIC05_MGSPropAssocQuote", TestDataInsight, "MarriottIntl_Corp");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
	try {
			fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("MIC05_MGSPropAssocQuote", TestDataInsight,"MarriottIntl_Corp", intCounter);
			this.reporter.initTestCaseDescription("MGSPropAssocQuote");
			reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")+ "  **************","");
					
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("Contact_Name"));
			cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));// Enable
																													// Purchasing
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
			// verify qunatitiy price partnum
			Verifypartnum(data.get("partnum"));
			VerifypartPrice(data.get("partprice"));
			Addcheckbox(data.get("partnum"));
			Setquantity(data.get("value"), data.get("partnum"));
			Closeiconofaddtocartatproductgrps();
			// click add to order
			// cart
			handleinsightpopup();
			// VERIFY ITEM IN CART
			Clickoncart();
			cartLib.verifyItemInCart(data.get("partnum"));
			VerifyItemPriceincart(data.get("price"));
			VerifyItemQuantityincart(data.get("price"));
			SaveAsQuote();
			typeattention(data.get("AccountName"));
			VerifyQuoteNameinsaveasquotepage();
			VerifyQuoteNotesinsaveasquotepage();
			Toclicksaveasquoteincompanystandardspage();
			// CLICK QUOTE
			handleinsightpopup();
			VerifySuccessmsgofQuoteandRefNum();
			VerifyQuoteName();
			VerifyQuoteNotes();
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
		ReportStatus.fnUpdateResultStatus("MGSPropAssocQuote", "MIC05", ReportStatus.strMethodName, intCounter, browser);
		fnCloseTest();
	}
} catch (Exception e) {
	e.printStackTrace();
	ReportStatus.blnStatus = false;
	gErrorMessage = e.getMessage();
	gTestStatus = false;
	ReportStatus.fnUpdateResultStatus("MGSPropAssocQuote", "MIC095", ReportStatus.strMethodName, 1, browser);
	throw new RuntimeException(e);
}

ReportControl.fnNextTestJoin(nextTestJoin);
}
}




