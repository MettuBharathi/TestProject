package com.insight.WebTest.Canada;

import com.insight.Lib.CanadaLib;
import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class CAN07_CartBasicTest extends CanadaLib{

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  CAN07_CartBasicTest
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This method is to verify MenuSearch for canada
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_CAN07(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN07_CartBasic", TestDataInsight, "Canada");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN07_CartBasic", TestDataInsight, "Canada", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("CartBasic");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
								"");

						/*reporter.SuccessReport("Iteration Number : ", "**************Iteration Number::  " + intCounter
								+ " To validate:: Favorites_Logged In User_Verify user is able to Add products to favorites_Remove Favorites in PLP/PDP **************");*/


						CommonLib commonLib = new CommonLib();
						CMTLib cmtLib = new CMTLib();
						CartLib cartLib = new CartLib();
						OrderLib orderLib = new OrderLib();
						ShipBillPayLib shipbLib = new ShipBillPayLib();
						CanadaLib canadaLib = new CanadaLib();


						cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
								data.get("ContactName"));
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						commonLib.searchProduct(data.get("Search_Item"));
						commonLib.addToCartAndVerify();

						commonLib.continueToShopping();
						commonLib.clickCart();
						cartLib.verifyItemInCart(data.get("Search_Item"));
						// Adding second product to cart
						commonLib.searchProduct(data.get("Search_Item1"));
						commonLib.addFirstDisplyedItemToCartAndVerify();
						commonLib.continueToShopping();
						commonLib.clickCart();

						// update quantity
						commonLib.updateCartQuantity(data.get("quantity"));

						// updating the quantity by 0 and abc
						commonLib.updateCartQuantityByZero(data.get("quantity1"));
						commonLib.updateCartQuantityByZero(data.get("quantity2"));
						commonLib.emptyCartAndVerify();
						commonLib.clickLogOutLink(data.get("Logout_Header"));

						// Login change
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp1"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						canadaLib.verifyCanadaWebgroup();
						commonLib.searchProduct(data.get("Search_Item"));
						commonLib.addToCartAndVerify();

						commonLib.continueToShopping();
						commonLib.clickCart();
						cartLib.verifyItemInCart(data.get("Search_Item"));
						commonLib.verifyProceedToCheckOutIsNotVisible();
						commonLib.clickLogOutLink(data.get("Logout_Header"));
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						cmtLib.searchForWebGroup(data.get("WebGrp1"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						commonLib.searchProduct(data.get("Search_Item"));
						commonLib.addToCartAndVerify();

						commonLib.continueToShopping();
						commonLib.clickCart();
						cartLib.verifyItemInCart(data.get("Search_Item"));
						commonLib.verifyProceedToCheckOut();
						commonLib.clickLogOutLink(data.get("Logout_Header"));
						//fnCloseTest();
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("CartBasic", "CAN07", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("CartBasic", "CAN07", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}
