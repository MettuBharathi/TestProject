package com.insight.WebTest.MIC;

import java.util.Hashtable;

import org.testng.annotations.Parameters;

import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;



import com.insight.Lib.CommonLib;

import com.insight.Lib.MarriottIntlCorpLib;

import com.insight.Lib.OrderLib;

import com.insight.accelerators.ReportControl;

import com.insight.googledrive.ReportStatus;

import com.insight.utilities.TestUtil;

public class MIC01_MIntlUserTest extends MarriottIntlCorpLib {

	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();		
	OrderLib orderLib = new OrderLib();	
	
	// #############################################################################################################
	// # Name of the Test : MIC01_MIntlUser
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_MIC01(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "MIC01_MIntlUserTC", TestDataInsight, "MarriottIntl_Corp");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("MIC01_MIntlUserTC", TestDataInsight,"MarriottIntl_Corp", intCounter);
					this.reporter.initTestCaseDescription("MIntlUser");
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")+ "  **************","");
					
			       cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
			 		data.get("ContactName"));
			       cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
			       // cmtLib.setPermissions(data.get("Menu_Name"),
			       // data.get("Set_Permission"));//Enable Purchasing Popup

					cmtLib.loginAsAdminCMT();

					clickAccountToolsFromSideMenu(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
					CompanystandardsSelectProductGrp(data.get("CPG"), data.get("SelectCP"));
					setQuantityForLenovo(data.get("VerifyQty"));
					Verifypartnum(data.get("Verifypart"));
					clickOnViewCart();
					verifyPartInCartQuickShop(data.get("QuickShop_Part"));
					orderLib.proceedToCheckout();
					addAdditionalInfoOfProduct(data.get("Brand_Identifier"), data.get("Requester_Name"),
							data.get("EndUser_PeopleSoftNumber"), data.get("Notes"),
							data.get("Customer_Reference_Number"), data.get("PC_User_Name"),
							data.get("PC_End_User_Div_Unit_Dept"), data.get("End_User_Email"),
							data.get("Approving_Manager_Email"), data.get("Non_IRFA_PC"));

					addShippingInfo(data.get("Ship_Attention"), data.get("Ship_Suite"), data.get("Ship_Phone"));
					shippingOptionContinueButton();
					addBillingInfo(data.get("Bill_Attention"), data.get("Bill_Suite"), data.get("Bill_Phone"));
					clickReviewOrder();
					VerifyBrandidentifier(data.get("Brand_Identifier"));
					verifyrequestorname(data.get("Requester_Name"));
					verifypcusername(data.get("PC_User_Name"));
					Verifynotes(data.get("Notes"));
					Verifycustomerreference(data.get("Customer_Reference_Number"));
					VerifyEnduserText(data.get("EndUser_PeopleSoftNumber"));
					verifyapprovingmanageremail(data.get("Approving_Manager_Email"));
					verifyEnduseremail(data.get("End_User_Email"));
					verifynonirfapc(data.get("Non_IRFA_PC"));
					verifyenduserdiv(data.get("PC_End_User_Div_Unit_Dept"));
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
				ReportStatus.fnUpdateResultStatus("MIntlUser", "MIC01", ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("MIntlUser", "MIC01", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}
	}
