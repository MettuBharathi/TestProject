package com.insight.WebTest.SLP;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.SLPLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SLP16_MPSACartTest extends SLPLib{
	// #############################################################################################################
		// # Name of the Test : SLP16_MPSACart
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
		public void SLP16_MPSACart(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SLP16_MPSACart", TestData, "SLP");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				// initilizing libraries and testdata
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SLP16_MPSACart", TestData, "SLP",
						intCounter);
				// Test Steps execution
				try {
					fnOpenTest();
					CMTLib cmtLib = new CMTLib();
					CommonLib commonLib = new CommonLib();
					OrderLib orderLib = new OrderLib();
					CartLib cartLib = new CartLib();
					CanadaLib canadaLib=new CanadaLib();
					SearchLib searchLib = new SearchLib();
					ShipBillPayLib shipbLib = new ShipBillPayLib();
					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.loginAsAdminCMT();
					Thread.sleep(3000);
					//navigate to SLP
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),data.get("Tools_Menu_DD"));
					Thread.sleep(2000);
					canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
					Thread.sleep(4000);
					// verify search results and select first product
			     	searchLib.verifysearchResultsPage();
			     	// Search for part or product and add to cart : part : 
			     	searchLib.searchInHomePage(data.get("PartNum1"));
			     	commonLib.addToCartAndVerify();
			     	orderLib.continueToCheckOutOnAddCart();
			     	Thread.sleep(3000);
					verifydeploydate(data.get("PartNum1"));
					verifylicensetypenotexists(data.get("PartNum1"));
					verifycopytoallnotexists(data.get("PartNum1"));
					Thread.sleep(3000);
					//navigate to SLP
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),data.get("Tools_Menu_DD"));
					canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
					// verify search results and select first product
			     	searchLib.verifysearchResultsPage();
			     	// Search for part or product and add to cart : part : 
			     	searchLib.searchInHomePage(data.get("PartNum2"));
			     	commonLib.addToCartAndVerify();
			     	orderLib.continueToCheckOutOnAddCart();
			     	Thread.sleep(3000);
					verifydeploydate(data.get("PartNum2"));
					verifylicensetype(data.get("PartNum2"));
					Thread.sleep(3000);
					//navigate to SLP
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),  data.get("Tools_Menu_DD"));
					canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
					// verify search results and select first product
			     	searchLib.verifysearchResultsPage();
			     	// Search for part or product and add to cart : part : AAA-04091
			     	searchLib.searchInHomePage(data.get("PartNum3"));
			    	commonLib.addToCartAndVerify();
			     	orderLib.continueToCheckOutOnAddCart();
			     	Thread.sleep(3000);
			     	String Lisence=getlicense(data.get("PartNum3"));
			     	verifydeploydate(data.get("PartNum3"));
			     	verifylicensetype(data.get("PartNum3"));
			     	verifychangeexists(data.get("PartNum3"));
			     	Clickonunpaidlicense();
			     	verifydeployedatewithcurrentdate();
			     	calenderforUnpaidLicense(data.get("Date"));
			     	verifyPartNuminPopUpData(data.get("PartNum3"));
			     	clickapply();
			     	Thread.sleep(6000);
			     	verifyUpdatedLisence(data.get("PartNum3"),Lisence);
			     	verifycopytoallexists(data.get("PartNum3"));
			     	Thread.sleep(3000);
			     	scrollUp();
					orderLib.proceedToCheckout();
					orderLib.continueButtonOnAdditionalInformationSection();
					orderLib.clickContinueOnLineLevelInfo();
					orderLib.shippingBillPayContinueButton();
					orderLib.shippingBillPayContinueButton();
					shipbLib.ClickRviewrequesition();
					verifyUpdatedLisence(data.get("PartNum3"),Lisence);
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					fnCloseTest();
				}
			}
		}

	}

