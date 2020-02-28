package com.insight.WebTest;

import java.io.File;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ActionEngine;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CartTest extends ActionEngine{
	// #############################################################################################################
    // #    Name of the Test         : CRT01_CartBasic
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################

	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test

	public void CRT01_CartBasic(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT01_CartBasic", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT01_CartBasic", TestDataInsight, "Web_Cart",intCounter);
					this.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					CommonLib CommonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					
					CommonLib.searchProduct(data.get("SearchItem1"));
					CommonLib.addToCartAndVerify();
					CommonLib.continueToShopping();
					CommonLib.searchProduct(data.get("SearchItem2"));
					CommonLib.addFirstDisplyedItemToCartAndVerify();
					CommonLib.clickCart();
					cmtLib.handleWelcomeToInsightBetaPopUp();
					CommonLib.updateCartQuantity(data.get("quantity"));
					CommonLib.deleteItemFromCart();
					CommonLib.searchProduct(data.get("SearchItem2"));
					CommonLib.addSecondDisplyedItemToCartAndVerify();					
					CommonLib.clickCart();
					CommonLib.emptyCartAndVerify();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					CommonLib.clickRolesAndPermissionsAtUserLevel();
					cmtLib.setPermissions(data.get("menuName"),data.get("userPermission"));
					CommonLib.cartBasics_verifyPermissionAtUserLevel(data.get("SearchItem2"),data.get("userPermission"),data.get("menuName"));
					cmtLib.navigateBackToCMT();
					cmtLib.setPermissions(data.get("menuName"),data.get("userPermission"));
				    fnCloseTest();
			}

	}
	// #############################################################################################################
    // #    Name of the Test         : CRT02_CartBasicIPS
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations using IPS USER.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################

	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
   public void CRT02_CartBasicIPS(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT02_CartBasicIPS", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT02_CartBasicIPS", TestDataInsight, "Web_Cart",intCounter);
					this.reporter.initTestCaseDescription("CartBasicIPS" );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					CommonLib commonLib = new CommonLib();
					CartLib cartLib = new CartLib();
					CMTLib cmtLib = new CMTLib();
					ShipBillPayLib shipbLib=new ShipBillPayLib();
					
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"),data.get("User_Permission"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cartLib.verifyCartIsEmpty();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					String partNumber1=cartLib.getPartNumber();
					commonLib.continueToShopping();
					commonLib.clickCart();
					String totalAmountMarketPriceoff=shipbLib.getTotalAmountInCart(data.get("Total"));
					cmtLib.navigateBackToCMT();
					cmtLib.setPermissions(data.get("Menu_Name"),data.get("User_Permission"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.searchProduct(partNumber1);
					cartLib.cartBasicsIPS_verifyPermissionAtUserLevel();
					commonLib.clickCart();
					String totalAmountMarketPriceOn=shipbLib.getTotalAmountInCart(data.get("Total"));
					if(!totalAmountMarketPriceoff.equals(totalAmountMarketPriceOn))
					{
						reporter.SuccessReport("Us commdity price and open market price", "are not equal","");
					}
					else {
						reporter.failureReport("Us commdity price and open market price", "are equal","");
					}
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				    cmtLib.searchForWebGroup(data.get("WebGrp2"));
				    cmtLib.manageUsers();
				    cmtLib.searchUsers(data.get("LnameEmailUname2"));
				    cmtLib.verifyUserandClick(data.get("ContactName2"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.searchProduct(data.get("Search_Item1"));
					cartLib.verifyDefaultContract();
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.clickCart();
					cartLib.verifyDefaultContractInCart();
					commonLib.searchProduct(data.get("SearchItem4"));
					cartLib.clickMorePricesAvilableInProductInfo();
					cartLib.clickOnOpenMarketPrice();
					cartLib.clickOnAddToCartInAllContractPrices();
					commonLib.clickCart();
					commonLib.emptyCartAndVerify();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
				fnCloseTest();
			}
		}

    // #############################################################################################################
    // #    Name of the Test         : CRT03_CartExport
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform search operations in the Product Research Request page.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
    @Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void CRT03_CartExport(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT03_CartExport", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
				
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT03_CartExport", TestDataInsight, "Web_Cart",intCounter);
					this.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					CommonLib commonLib = new CommonLib();	
					CartLib cartLib = new CartLib();
					CMTLib cmtLib = new CMTLib();
					OrderLib orderLib=new OrderLib();
					SearchLib searchLib=new SearchLib();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					String mainWindow = parentWindow();
					cmtLib.clickOnloginAs();
					switchToWindow(mainWindow);
					commonLib.searchProduct(data.get("SearchItem1"));
					commonLib.addToCartAndVerify();
					orderLib.continueToCheckOutAddWarrantyAndVerifyTheCart(data.get("Warrenty_Part_Number"));
					commonLib.searchProduct(data.get("SearchItem2"));
					commonLib.addToCartAndVerify();
					orderLib.continueToCheckOutAddWarrantyAndVerifyTheCart(data.get("Warrenty_Part_Number"));
					commonLib.searchProduct(data.get("SearchItem3"));
					commonLib.addToCartAndVerify();
					orderLib.continueToCheckOutAddWarrantyAndVerifyTheCart(data.get("Warrenty_Part_Number"));
					cartLib.clickAndVerifyExportCart(data.get("OrderUtilities"));				
					cartLib.verifyExportFile(data.get("Sheet_Name"),data.get("Row_number"),data.get("Column_Headers"));
					commonLib.spinnerImage();
					commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"), data.get("Product_Group"), data.get("Product_Name"));
					searchLib.clickAddToOrderOnCompanyStandardsScreen();
					commonLib.clickCart();
					commonLib.verifyBundleIsAddedToCart();
					cartLib.clickAndVerifyExportCart(data.get("OrderUtilities"));
					cartLib.verifyExportFile(data.get("Sheet_Name"),data.get("Row_number1"),data.get("Column_Headers1"));
					Thread.sleep(5000);
				fnCloseTest();
			}
			}
	
    // #############################################################################################################
    // #    Name of the Test         : CRT04_CartPrintFriendly
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform search operations in the Product Research Request page.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################
    @Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test(enabled = true)
	public void CRT04_CartPrintFriendly(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT04_CartPrintFriendly", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
				
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT04_CartPrintFriendly", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT_WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					CommonLib commonLib = new CommonLib();
					CartLib cartLib = new CartLib();
					commonLib.searchProduct(data.get("SearchItem1"));
					commonLib.addToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.searchProduct(data.get("SearchItem2"));
					commonLib.addToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.searchProduct(data.get("SearchItem3"));
					commonLib.addToCartAndVerify();
					commonLib.closePopUp();
					cartLib.clickAndVerifyViewPrintablePopUp(data.get("OrderUtilities"));
				    fnCloseTest();
			}
		}
 // #############################################################################################################
    // #    Name of the Test                       : CRT05_QuickSearch
    // #    Migration Customization Author         : Cigniti Technologies
    // #
    // #    Date of Migration                      : August 2019
    // #    DESCRIPTION                            : This method is to perform Quick Search operations in the shopping cart page.
    // #    Parameters                             : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################
    @Parameters({"StartRow","EndRow","nextTestJoin"})
    @Test()
    public void CRT05_QuickSearch(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
     
      int intStartRow=StartRow;
      int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT05_QuickSearch", TestDataInsight, "Web_Cart");
      for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
      {
       
        fnOpenTest();
        ReportStatus.fnDefaultReportStatus();
        ReportControl.intRowCount=intCounter;
        Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT05_QuickSearch", TestDataInsight, "Web_Cart",intCounter);
        TestEngineWeb.reporter.initTestCaseDescription("InSight Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
        reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
        
        CommonLib commonLib = new CommonLib();
        CartLib cartLib = new CartLib();
        CMTLib cmtLib = new CMTLib();
        
        commonLib.searchProduct(data.get("SearchItem1"));
        commonLib.addToCartAndVerify();
        commonLib.continueToShopping();
         commonLib.clickCart();
        cmtLib.handleWelcomeToInsightBetaPopUp();
        cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("SearchItem2"), data.get("quantity"));
        cartLib.verifyQuickShopWithInvalidPartNumber(data.get("SearchItem3"));
        cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
		//cartLib.verifyQuickShopPermissionAtUserLevel(data.get("SearchItem4"),  data.get("userPermission"));
        fnCloseTest();
      }
     
     }
    // #############################################################################################################
    // #    Name of the Test         : CRT09_SendToColleague
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to verify send to collegue order utility in shopping cart  page.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################
    @Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test(enabled = true)
	public void CRT09_SendToColleague(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT09_SendToColleague", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
				
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT09_SendToColleague", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					CommonLib commonLib = new CommonLib();
					CartLib cartLib = new CartLib();
					
					String searchItem1 = data.get("SearchItem1");
					String searchItem2 = data.get("SearchItem2");
					String searchItem3 = data.get("SearchItem3");
					commonLib.searchProduct(searchItem1);
					commonLib.addToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.searchProduct(searchItem2);
					commonLib.addToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.searchProduct(searchItem3);
					commonLib.addToCartAndVerify();
					commonLib.closePopUp();
					cartLib.clickAndVerifySendToAColleagueErrorMSG(data.get("OrderUtilities"));
					cartLib.verifySendToAColleague(data.get("OrderUtilities"),data.get("YourName"),data.get("YourEmail"),data.get("RecipientEmail"),data.get("YourComments"));
				    fnCloseTest();
			}
		}
 // #############################################################################################################
    // #    Name of the Test         : CRT10_SendToColleagueIPS
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to verify send to collegue order utility in shopping cart  page.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################
    @Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test(enabled = true)
	public void CRT10_SendToColleagueIPS(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT10_SendToColleagueIPS", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
				
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT10_SendToColleagueIPS", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					CommonLib commonLib = new CommonLib();
					CartLib cartLib = new CartLib();
					CMTLib cmtLib = new CMTLib();
					
					String searchItem1 = data.get("SearchItem1");
					String searchItem2 = data.get("SearchItem2");
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					String mainWindow = parentWindow();
					cmtLib.clickOnloginAs();
					switchToWindow(mainWindow);	
					commonLib.searchProduct(searchItem1);
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.searchProduct(searchItem2);
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.closePopUp();
					cartLib.clickAndVerifySendToAColleagueErrorMSG_IPS(data.get("OrderUtilities"));
					cartLib.verifySendToAColleague(data.get("OrderUtilities"),data.get("YourName"),data.get("YourEmail"),data.get("RecipientEmail"),data.get("YourComments"));
				    fnCloseTest();
			}
		}
 // #############################################################################################################
    // #    Name of the Test         : CRT13_DeleteCartItems
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test(enabled = true)
	public void CRT13_DeleteCartItems(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT13_DeleteCartItems", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT13_DeleteCartItems", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					commonLib.clickOnLoginAsForDeleteFunctionality(data.get("PartNumber"),data.get("SearchItem"));

				fnCloseTest();
			}
		
	}
	
	 // #############################################################################################################
    // #    Name of the Test         : CRT08_SaveCartIPS
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void CRT08_SaveCartIPS(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT08_SaveCartIPS", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT08_SaveCartIPS", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					CartLib cartLib = new CartLib();
					
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("menuName"),data.get("userPermission"));
					String mainWindow = parentWindow();
					cmtLib.clickOnloginAs();
					switchToWindow(mainWindow);	
					cartLib.verifyCartIsEmpty();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.searchProduct(data.get("Search_Item"));
					cartLib.clickMorePricesAvilable(1);
					cartLib.clickOnOpenMarketPrice();
					cartLib.clickOnAddToCartInAllContractPrices();
					commonLib.clickCart();
					String cartName=getRandomString(5)+'@';
					cartLib.clickOnSaveCartContentAndSaveCart(cartName);
					commonLib.clickCart();
					commonLib.verifyCartIsEMpty();
					cartLib.openSavedCartFromTools(cartName);
					cartLib.deleteCartFromAccountTools(cartName);
				fnCloseTest();
			}
		
	}
	 // #############################################################################################################
    // #    Name of the Test         : CRT07_SaveCart
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void CRT07_SaveCart(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT07_SaveCart", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT07_SaveCart", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					CartLib cartLib = new CartLib();
					SearchLib searchLib=new SearchLib();
					
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("menuName"),data.get("userPermission"));
					String mainWindow = parentWindow();
					cmtLib.clickOnloginAs();
					switchToWindow(mainWindow);	
					cartLib.verifyCartIsEmpty();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addSecondDisplyedItemToCartAndVerify();
					commonLib.searchProduct(data.get("Search_Item2"));
					cartLib.selectFirstProductDisplay();
					commonLib.addToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.searchProduct(data.get("Search_Item3"));
					cartLib.selectFirstProductDisplay();
					commonLib.addToCartAndVerify();
					commonLib.clickCart();
					String cartName=getRandomString(5)+'@';
					cartLib.clickOnSaveCartContentAndSaveCart(cartName);
					commonLib.clickCart();
					commonLib.verifyCartIsEMpty();
					cartLib.openSavedCartFromTools(cartName);
					commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"), data.get("Product_Group"), data.get("Product_Name"));
					searchLib.selectProductGroupAndVerify(data.get("Product_Group"), data.get("Product_Name"));
					commonLib.clickCart();
					commonLib.verifyBundleIsAddedToCart();
					String cartName1=getRandomString(5)+'@';
					cartLib.clickOnSaveCartContentAndSaveCart(cartName1);
					commonLib.clickCart();
					cartLib.openSavedCartFromTools(cartName1);
					cartLib.deleteCartFromAccountTools(cartName);
					cartLib.deleteCartFromAccountTools(cartName1);
					
				fnCloseTest();
			}
		
	}
	 // #############################################################################################################
    // #    Name of the Test         : CRT06_QuickSearchIPS
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void CRT06_QuickSearchIPS(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT06_QuickSearchIPS", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT06_QuickSearchIPS", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					CartLib cartLib = new CartLib();
					SearchLib searchLib=new SearchLib();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					
			   		cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
			   		cmtLib.setPermissionsToDisable(data.get("Menu_Name"),data.get("Set_Permission1"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					searchLib.verifyContractAllDisplayed();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.clickCart();
					cartLib.verifyItemInCart(data.get("Search_Item"));
					cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("Search_Item2"), data.get("quantity"));
					commonLib.clickCart();
					commonLib.emptyCartAndVerify();
					searchLib.selectNewcontract(data.get("Contract_name"));
					commonLib.searchProduct(data.get("Search_Item3"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.clickCart();
					cartLib.verifyItemInCart(data.get("Search_Item3"));
					cartLib.verifyContractNameInCart(data.get("Contract_name"));
					cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("Search_Item2"), data.get("quantity"));
					commonLib.clickCart();
					commonLib.emptyCartAndVerify();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.clickCart();
					cartLib.verifyItemInCart(data.get("Search_Item"));
					cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("Search_Item2"), data.get("quantity"));
					commonLib.clickCart();
					commonLib.emptyCartAndVerify();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"),data.get("Set_Permission"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.clickCart();
					cartLib.verifyItemInCart(data.get("Search_Item"));
					cartLib.verifyQuickShopIsDisable();
					fnCloseTest();
			}
		
	}
	// #############################################################################################################
    // #    Name of the Test         : CRT16_RequestorOptions
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void CRT16_RequestorOptions(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT16_RequestorOptions", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT16_RequestorOptions", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					CartLib cartLib = new CartLib();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.handleWelcomeToInsightBetaPopUp();
					commonLib.searchProduct(data.get("PartNumber"));
					commonLib.addToCartAndVerify();
					commonLib.clickCart();
					cartLib.verifySaveCartAsQuoteIsPresent();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.navigateBackToCMT();
				    cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				    cmtLib.searchForWebGroup(data.get("WebGrp"));
				    cmtLib.manageUsers();
				    cmtLib.searchUsers(data.get("LnameEmailUname1"));
				    cmtLib.verifyUserandClick(data.get("ContactName1"));
				    cmtLib.clickOnloginAs();
				    switchToChildWindow();
				    commonLib.searchProduct(data.get("PartNumber"));
				    commonLib.addToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.clickCart();
					cartLib.verifySelectRwquestorGroupDropdownIsPresent();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					fnCloseTest();
			}}
	// #############################################################################################################
    // #    Name of the Test         : CRT25_QuickCheckOut
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void CRT25_QuickCheckOut(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT25_QuickCheckOut", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT25_QuickCheckOut", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					CartLib cartLib = new CartLib();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.searchProduct(data.get("PartNumber"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.clickCart();
					cartLib.clickQuickCheckOutandVerify(data.get("ShippingCompany"),data.get("ShippingCarrier"),data.get("NotificationMail"),data.get("BillingAddresses"),data.get("PaymentType"));
					commonLib.spinnerImage();
					cartLib.clickOnFavouriteShippingAddressesandSelectanAddresses(data.get("LnameEmailUname"));
					cartLib.clickOnQuickCheckout();
					cartLib.validateShippingAddressesInQickCheckOut(data.get("LnameEmailUname"));
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					fnCloseTest();
			}}
	// #############################################################################################################
    // #    Name of the Test         : CRT17_SaveCartLineLevel
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void CRT17_SaveCartLineLevel(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT17_SaveCartLineLevel", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT17_SaveCartLineLevel", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					CartLib cartLib = new CartLib();
				
					OrderLib orderLib=new OrderLib();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.searchProduct(data.get("PartNumber"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.clickCart();
					orderLib.proceedToCheckout();
					cartLib.addAdditionalInformationInCheckOut(data.get("Url"), data.get("RP_HDL_Txt"));
					cartLib.addLineLevelInformationInCheckOut(data.get("RP_LNL_Txt"));
					cartLib.clearPhoneFieldInCheckOut();
					cartLib.shippingBillPayInCheckOut(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PONumber"));
					cartLib.verifyRpHdlTxt(data.get("RP_HDL_Txt"));
					String cartName=getRandomString(5)+'@';
					cartLib.clickOnSaveCartContentAndSaveCart(cartName);
					commonLib.clickCart();
					commonLib.verifyCartIsEMpty();
					cartLib.openSavedCartFromTools(cartName);
					cartLib.addToCartInSavedCart(cartName);
					cartLib.verifyRpHdlTxtisNotPresent(data.get("RP_HDL_Txt"));
					cartLib.verifyRpLnllTxtisNotPresent(data.get("RP_LNL_Txt"));
					cartLib.openSavedCartFromTools(cartName);
					cartLib.deleteCartFromAccountTools(cartName);
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					fnCloseTest();
			}
	}
	
	// #############################################################################################################
    // #    Name of the Test         : CRT12_TopNav
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void CRT12_TopNav(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT12_TopNav", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT12_TopNav", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					CartLib cartLib = new CartLib();
					SearchLib searchLib=new SearchLib();
					OrderLib orderLib=new OrderLib();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"), data.get("Product_Group"), data.get("Product_Name"));
					searchLib.clickAddToOrderOnCompanyStandardsScreen();
					commonLib.clickCart();
					commonLib.verifyBundleIsAddedToCart();
					commonLib.searchProduct(data.get("PartNumber"));
					commonLib.addToCartAndVerify();
					orderLib.continueToCheckOutAddWarrantyAndVerifyTheCart(data.get("Warrenty_Part_Number"));
					commonLib.spinnerImage();
					commonLib.searchProduct(data.get("Search_Product"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					String partNumber1=cartLib.getPartNumber();
					commonLib.clickCart();
					cartLib.verifyItemInCart(partNumber1);
					commonLib.searchProduct(data.get("Search_Product"));
					commonLib.addSecondDisplyedItemToCartAndVerify();
					String partNumber2=cartLib.getPartNumber();
					commonLib.clickCart();
					cartLib.verifyItemInCart(partNumber2);
					commonLib.emptyCartAndVerify();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					fnCloseTest();
					
					
			}
	}
	
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
	public void CRT18_SaveCartShipBillPay(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT18_SaveCartShipBillPay", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT18_SaveCartShipBillPay", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					CartLib cartLib = new CartLib();
					OrderLib orderLib=new OrderLib();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
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
					fnCloseTest();
			}
	}
	
	// #############################################################################################################
    // #    Name of the Test         : CRT24_WebGuidedPurchase
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void CRT24_WebGuidedPurchase(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT24_WebGuidedPurchase", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT24_WebGuidedPurchase", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					CartLib cartLib = new CartLib();
					SearchLib searchLib=new SearchLib();
					OrderLib OrderLib= new OrderLib();
				
					OrderLib orderLib=new OrderLib();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cartLib.verifyWelcomeHeader();
					cartLib.clickAndVerifyGetStartedLink();
					cartLib.clickOnAccesseriosLnk(data.get("link"));
					Thread.sleep(2000);
					cartLib.clickOnDellLink(data.get("link1"));
					//Get part id for the first product
					int Num= Integer.parseInt(data.get("size"));
					cartLib.clickOnChooseThisItemLnk(Num);
					cartLib.clickOnProductLink(data.get("ProductLink"));
					Thread.sleep(3000);
					String PartNum =cartLib.getPartNumAndswitchToParentWindow();
					//Get part id for the second product					
					cartLib.clickOnProductLink(data.get("ProductLink2"));
		            Thread.sleep(3000);
					String PartNum1 =cartLib.getPartNumAndswitchToParentWindow();
					cartLib.clickOnNextButton();
					//Click on continue shopping Link
					scrollUp();
					cartLib.clickOnchooseLink(data.get("ContinueLink"));
					cartLib.clickOnlaptopsLnk();
					int Num1= Integer.parseInt(data.get("size1"));
					cartLib.clickOnChooseThisItemLnk(Num1);
					cartLib.clickOnProductLink(data.get("ProductLink3"));
					//Get part id for the third product
					String PartNum2 =cartLib.getPartNumAndswitchToParentWindow();
					//Click on continue shopping Link					
					cartLib.clickOnNextButton();
					scrollUp();
					cartLib.clickOnchooseLink(data.get("Proceedlink"));
					// Verify landed to cart page or not
					OrderLib.verifyCartHeaderLabel();
					//Comparing part numbers which are added to cart 
					cartLib.verifyItemInCart(PartNum);
					cartLib.verifyItemInCart(PartNum1);
					cartLib.verifyItemInCart(PartNum2);																										
					fnCloseTest();
										
						}			
			}

	// #############################################################################################################
    // #    Name of the Test         : CRT11_ShippingEstimator
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	

	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void CRT11_ShippingEstimator(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable 
	{
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow,"CRT11_ShippingEstimator",TestDataInsight,"Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT11_ShippingEstimator", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					CartLib cartLib = new CartLib();
				    cmtLib.loginToCMT(data.get("header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
				    cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.setCustomerLevelPermissionsOFF(data.get("customerPermissions"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				    cmtLib.loginAsAdminCMT(); 
					commonLib.searchProduct(data.get("SearchItem1"));  
				    commonLib.addFirstDisplyedItemToCartAndVerify();
				    commonLib.clickCart();
				    cartLib.verifyShippingestimator();
				    commonLib.clickLogOutLink("Logout");
				    cmtLib.navigateBackToCMT();
				    cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.setCustomerLevelPermissionsON(data.get("customerPermissions"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
			        cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));	
					cmtLib.AssigntheusertoServiceLevelShippingwithnodefault(data.get("menuName"),data.get("user_Permissions"),data.get("indexvalue"));
					cmtLib.clickupdateatDefaultShippingOption();
					cmtLib.loginAsAdminCMT(); 
					commonLib.searchProduct(data.get("SearchItem1"));	  
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.clickCart();
					cartLib.verifyShippingestimator();
					cartLib.verifyShippingestimatorshippingCarrier(data.get("postalcode"),data.get("upsCarrier"),data.get("fedexCarrier"));
					cartLib.clickotherthanUSDandFedEx(data.get("postalcode"));
					commonLib.clickLogOutLink("Logout");
				    cmtLib.navigateBackToCMT();
				    cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.setCustomerLevelPermissionsON(data.get("customerPermissions"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));	
						cmtLib.usertoServiceLevelShippingwithOnlyFedex(data.get("menuName"),data.get("user_Permissions"),data.get("text1"));
						cmtLib.clickupdateatDefaultShippingOption();
						cmtLib.loginAsAdminCMT();
						commonLib.searchProduct(data.get("SearchItem1"));	  
						commonLib.addFirstDisplyedItemToCartAndVerify();
						commonLib.clickCart();
						cartLib.verifyShippingestimator();
						cartLib.VerifyonlyFedExoptions(data.get("postalcode"),data.get("fedexCarrier"));
						commonLib.clickLogOutLink("Logout");
						cmtLib.navigateBackToCMT();
				        cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.AssigntheusertoServiceLevelShippingwithnodefault(data.get("menuName"),data.get("user_Permissions"),data.get("indexvalue"));
						cmtLib.clickupdateatDefaultShippingOption();
						cmtLib.loginAsAdminCMT();
						Thread.sleep(3000);
						commonLib.searchProduct(data.get("SearchItem1"));	  
						commonLib.addFirstDisplyedItemToCartAndVerify();
						cmtLib.navigateBackToCMT();
				        cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.setCustomerLevelPermissionsOFF(data.get("customerPermissions"));
					    fnCloseTest();
			}

}
	
	// #############################################################################################################
    // #    Name of the Test         : CRT22_CartInventory
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void CRT22_CartInventory(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable 
	{
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow,"CRT22_CartInventory",TestDataInsight,"Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT22_CartInventory", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					CartLib cartLib = new CartLib();
					cmtLib.loginToCMT(data.get("Header"));
			        cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginAsAdminCMT();
					cartLib.verifyCOIpart(data.get("toolsMenuName"),data.get("dropDown"),data.get("productGroup"),data.get("productName"),data.get("COIText"));
					commonLib.clickLogOutLink("Logout");
					cmtLib.navigateBackToCMT();
			        cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginAsAdminCMT();
					cartLib.verifyCSIpart(data.get("toolsMenuName"),data.get("dropDown"),data.get("productGroup"),data.get("productName"),data.get("CSIText"));
					commonLib.clickLogOutLink("Logout");
					cmtLib.navigateBackToCMT();
			        cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginAsAdminCMT();
					cartLib.verifyReservedProducts(data.get("toolsMenuName"),data.get("dropDown"),data.get("productGroup"),data.get("productName"),data.get("ReservedText"));
					commonLib.clickLogOutLink("Logout");
					 fnCloseTest();
                    
	
	
	
	
}

	
	}
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void CRT23_LoginFromCartPricing(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
			int intStartRow=StartRow;
			int intEndRow=ReportControl.fnGetEndRowCunt(EndRow, "CRT23_LoginFromCartPricing", TestDataInsight, "Web_Cart");
			for(int intCounter=intStartRow;intCounter<=intEndRow;intCounter++)
			{
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount=intCounter;
					Hashtable<String, String> data=TestUtil.getDataByRowNo("CRT23_LoginFromCartPricing", TestDataInsight, "Web_Cart",intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase"+ " From Iteration " + StartRow + " to " + EndRow );
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  "+ intCounter+" For:: "+data.get("LoginName")+" ::and:: "+data.get("Password")+" To Validate::"+data.get("errorMessage") +"  **************", "");
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					CartLib cartLib = new CartLib();
					cmtLib.loginAsEndUserInMainPage(data.get("Header"),data.get("User_Name"),data.get("Password"));
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addToCartAndVerify();
					String priceInLogin=cartLib.getTotalPrice();
					commonLib.clickCart();
					
					cartLib.verifyItemInCart(data.get("Search_Item"));
					String summaryAmountInLogin=cartLib.getSummaryAmountInCart();
					commonLib.emptyCartAndVerify();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addToCartAndVerify();
					String priceWithoutLogin=cartLib.getTotalPrice();
					commonLib.clickCart();
					
					cartLib.verifyItemInCart(data.get("Search_Item"));
					String summaryAmountWithoutLogin=cartLib.getSummaryAmountInCart();
					System.out.println("summaryAmountInLogin"+summaryAmountInLogin);
					System.out.println("summaryAmountWithoutLogin"+summaryAmountWithoutLogin);
					cartLib.VerifyLoginPriceAndNonLoginPrice(priceInLogin,priceWithoutLogin);
					cartLib.verifySummaryPriceInLoginAndNonLogin(summaryAmountInLogin,summaryAmountWithoutLogin);
					fnCloseTest();
					
			}
		}

}
	
	