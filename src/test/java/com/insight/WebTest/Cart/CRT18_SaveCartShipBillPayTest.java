package com.insight.WebTest.Cart;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CRT18_SaveCartShipBillPayTest extends CartLib{
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	OrderLib orderLib=new OrderLib();

	// #############################################################################################################
    // #    Name of the Test         : CRT18_SaveCartShipBillPay
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void Tc_CRT18(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		try {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT18_SaveCartShipBillPay", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
				try {
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT18_SaveCartShipBillPay", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SaveCartShipBillPay");
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.setPermissions(data.get("Menu_Name"),data.get("Enable_Purchasing_Popup"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cartLib.verifyCartIsEmpty();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.clickCart();
					String cartName=getRandomString(5)+'@';
					cartLib.clickOnSaveCartContentAndSaveCart(cartName);
					commonLib.clickCart();
					commonLib.verifyCartIsEMpty();
					cartLib.openSavedCartFromTools(cartName);
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.navigateBackToCMT();
				    cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				    cmtLib.searchForWebGroup(data.get("WebGrp"));
				    cmtLib.manageUsers();
				    cmtLib.searchUsers(data.get("LnameEmailUname"));
				    cmtLib.verifyUserandClick(data.get("ContactName"));
				    cmtLib.clickOnloginAs();
				    switchToChildWindow();
				    cartLib.openSavedCartFromTools(cartName);
				    cartLib.addToCartInSavedCart(cartName);
				    orderLib.proceedToCheckout();
				    cartLib.clickOnContinueButtonInAddInformtion();
				    cartLib.addLineLevelInformationInCheckOut(data.get("RP_LNL_Txt"));
				    cartLib.clearPhoneFieldInCheckOut();
					cartLib.shippingBillPayInCheckOut(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PONumber"));
					cartLib.verifyItemInCart(data.get("Search_Item"));
					cartLib.openSavedCartFromTools(cartName);
					cartLib.deleteCartFromAccountTools(cartName);
					commonLib.clickLogOutLink(data.get("Logout_Header"));
				}
					catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("SaveCartShipBillPay", "CRT18", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("SaveCartShipBillPay", "CRT18", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}}
