package com.insight.Lib;

import com.insight.ObjRepo.CMTObj;
import com.insight.ObjRepo.CanadaObj;
import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.CommonObj;
import com.insight.accelerators.ActionEngine;
import com.insight.report.ConfigFileReadWrite;
import com.insight.report.ReporterConstants;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;

public class CommonLib extends ActionEngine{
	
	
	public String CMT_ADMIN_USERNAME =  ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "cmtUserName");
	public String CMT_ADMIN_PASSWORD =  ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "cmtPassWord");
	public String EMAIL_ID =  ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "emailID");
	CanadaLib canadaLib = new CanadaLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib=new SearchLib();
	

	/* PURPOSE OF METHOD : REUASABLE METOHD TO click Login Link
	 *  
	 *  @author : Cigniti
	 */
	public void loginAsAdmin() throws Throwable
	{	
		waitForVisibilityOfElement(CartObj.USER_NAME,"user name");
		type(CartObj.USER_NAME,CMT_ADMIN_USERNAME,"user name");
		
		waitForVisibilityOfElement(CartObj.PASSWORD,"Password");
		type(CartObj.PASSWORD,CMT_ADMIN_PASSWORD,"Password");
		click(CartObj.LOG_IN_BUTTON,"LOG IN BUTTON");
				
	}
	
	
	
	public void searchForWebGroup(String webGroup) throws Throwable
	{	
		waitForVisibilityOfElement(CMTObj.WEB_GROUP,"WEB GROUP in CMT TOOL HOME PAGE");		
		type(CMTObj.WEB_GROUP,webGroup,"WEB GROUP in CMT TOOL");
		click(CartObj.SEARCH_BUTTON_CMT_TOOL,"SEARCH BUTTON in CMT TOOL HOME PAGE");
			
	}
	
	
	public void clickOnWebGroupNumber(String webGroupNumber) throws Throwable
	{	
		waitForVisibilityOfElement(CartObj.getWebGroupNumber(webGroupNumber),"web Group Number");	
		click(CartObj.getWebGroupNumber(webGroupNumber),"web Group Number");	
		
			
	}
	
	public void searchUsers(String lnameEmailUname) throws Throwable
	{	
		waitForVisibilityOfElement(CartObj.LNAME_EMAIL_USERNAME,"LNAME EMAIL USERNAME");
		type(CartObj.LNAME_EMAIL_USERNAME,lnameEmailUname,"LNAME EMAIL USERNAME");
		click(CartObj.USERNAME_SEARCH_BUTTON,"USERNAME SEARCH BUTTON");
			
	}
	
	public void verifyUserandClick(String contactName) throws Throwable
	{	
		String actualuser = getText(CartObj.getUser(contactName),"USER CONTACT NAME");
		if(actualuser.equalsIgnoreCase(contactName)){
			reporter.SuccessReport("Verifying User Contact Name :", "User Contact Name present is : ", contactName );
			click(CartObj.getUser(contactName),"USER CONTACT NAME");
		}else{
			reporter.failureReport("Verifying User Contact Name :", "User Contact Name not present is : ", contactName );
		}
	}
	/* PURPOSE OF METHOD : REUASABLE METOHD TO SEARCH A PRODUCT
	 *  
	 *  @author : Cigniti
	 */
	public void clickRolesAndPermissionsAtUserLevel() throws Throwable
	{	
		waitForVisibilityOfElement(CartObj.ROLES_AND_PERMISSIONS_USER_LEVEL,"ROLES AND PERMISSIONS AT USER LEVEL");
		click(CartObj.ROLES_AND_PERMISSIONS_USER_LEVEL,"ROLES AND PERMISSIONS AT USER LEVEL");
	}
	

	/* PURPOSE OF METHOD : Click on checkout settings
	 *  
	 *  @author : Cigniti
	 */
	
	public void clickCheckOutSettings(String checkOutSettings) throws Throwable
	{	
		waitForVisibilityOfElement(CMTObj.getUsersTabMenus(checkOutSettings),"Check out settings");
		click(CMTObj.getUsersTabMenus(checkOutSettings),"Check out settings");
	}
	
	/* PURPOSE OF METHOD : Select option in check out settings
	 *  
	 *  @author : Cigniti
	 */
	public void selectOptionInCheckoutSettings(String optionToSelect) throws Throwable {
		waitForVisibilityOfElement(CMTObj.optionsInCheckOutSettings(optionToSelect),"Wait for" + optionToSelect);
		click(CMTObj.optionsInCheckOutSettings(optionToSelect),"Click on" + optionToSelect);
	}
	
	public void verifyDeafultMail(String actualEmail) throws Throwable {
		
		String email=driver.findElement(CMTObj.DEAFULT_MAIL).getAttribute("value");
		if(email.equalsIgnoreCase(actualEmail)) {
			reporter.SuccessReport("Mail ", "Default ASN Email Address is already presnt",actualEmail);
		}
		else {
			clearData(CMTObj.DEAFULT_MAIL);
			type(CMTObj.DEAFULT_MAIL, actualEmail, "Default ASN Email Address" +actualEmail);
		}
	}
	
	/* PURPOSE OF METHOD : Select option in check out settings
	 *  
	 *  @author : Cigniti
	 */
	public void selectDefaultShippingOptionInCheckoutSettings(String optionToSelect) throws Throwable {
		click(CMTObj.User_service_levelshipping,"User level shipping option is selected ");
		waitForVisibilityOfElement(CMTObj.DEFAULT_SHIPPING_OPTION,"Wait for" + optionToSelect);
		selectByVisibleText(CMTObj.DEFAULT_SHIPPING_OPTION,optionToSelect,"Click on" + optionToSelect);
	}
	
	/* PURPOSE OF METHOD : click on update button in checkout settings
	 *  
	 *  @author : Cigniti
	 */
	public void clickOnUpdateButtonInUserSettings() throws Throwable {
		waitForVisibilityOfElement(CMTObj.UPDATE_BUTTON,"Update button");
		click(CMTObj.UPDATE_BUTTON,"Update button");
	}
	
	

	/* PURPOSE OF METHOD : REUASABLE METOHD TO SEARCH A PRODUCT
	 *  
	 *  @author : Cigniti
	 */
	public void changePermissionsAndVerify(String userPermission) throws Throwable
	{ 
	 waitForVisibilityOfElement(CMTObj.getUserPermission(userPermission),"PERMISSION IS :" + userPermission);
	 
	 if ( driver.findElement(CMTObj.getUserPermission(userPermission)).isSelected())
	 {
	  driver.findElement(CMTObj.getUserPermission(userPermission)).click();
	      reporter.SuccessReport("Verifying Checkbox :", "Checkbox is unchecked for:  " , userPermission);
	 }else{
	  driver.findElement(CMTObj.getUserPermission(userPermission)).click();
	      reporter.SuccessReport("Verifying Checkbox :", "Checkbox is not unchecked for :" , userPermission);
	 }
		click(CMTObj.UPDATE_USER,"clicked on UPDATE USER");
		waitForVisibilityOfElement(CartObj.PERMISSIONS_UPDATED_SUCCESSFULLY_MESSAGE,"PERMISSIONS UPDATED SUCCESSFULLY MESSAGE");
		isElementPresent(CartObj.PERMISSIONS_UPDATED_SUCCESSFULLY_MESSAGE,"PERMISSIONS UPDATED SUCCESSFULLY MESSAGE",true);
	}
	

	
	
		public void cartBasics_verifyPermissionAtUserLevel(String searchItem, String userPermission,String menuName) throws Throwable
		{	String mainWindow = parentWindow();
		
			cmtLib.clickOnloginAs();
			switchToWindow(mainWindow);
			searchProduct(searchItem);
			addSearchItem();
			closePopUp();
			clickCart();
			
			verifyProceedToCheckOut();	
			switchToParentWindow(mainWindow);
			changePermissionsAndVerify(userPermission);
			cmtLib.setPermissionsToDisable(menuName,userPermission);
			cmtLib.clickOnloginAs();
			switchToWindow(mainWindow);
			searchProduct(searchItem);
			addSearchItem();
			closePopUp();
			clickCart();
			verifyBuyingNotEnabledMessage();
		}
		
		/* PURPOSE OF METHOD : REUASABLE METOHD TO SEARCH A PRODUCT
		 *  
		 *  @author : Cigniti
		 */
		public void searchProduct(String SearchItem) throws Throwable
		{	
			waitForVisibilityOfElement(CartObj.SEARCH,"SEARCH FIELD");
			type(CartObj.SEARCH,SearchItem,"SEARCHFIELD");
			//click(CartObj.SEARCH_BUTTON," SEARCH BUTTON");
			sendKeysActionsEnter(CartObj.SEARCH);
				
		}
		
		/* PURPOSE OF METHOD : REUASABLE METHOD TO verify Displayed Product Details
		 *  
		 *  @author : Cigniti
		 */
		public void verifyDisplayedProductDetails(String SearchItem) throws Throwable
		{	
			waitForVisibilityOfElement(CartObj.INSIGHT_NUMBER_IN_PRODUCT_DISPLAY,"INSIGHT NUMBER IN PRODUCT DISPLAY");
			String productNo = getText(CartObj.INSIGHT_NUMBER_IN_PRODUCT_DISPLAY,"INSIGHT NUMBER IN PRODUCT DISPLAY");
			 String[] actualProduct=productNo.replace("\"", "").split("# ");
			if(actualProduct[1].equals(SearchItem)) {
				reporter.SuccessReport("Verifying Displayed Product Details :", "Actual and expected product details are verified and the Product is : ", SearchItem );
			}else{
				reporter.failureReport("Verifying Displayed Product Details :", "Actual and Expected missmatch.Expected is: " ,SearchItem);
			}
				
		}
		
		/* PURPOSE OF METHOD : REUASABLE METHOD TO add To Cart And Verify in product display page.
		 *  
		 *  @author : Cigniti
		 */
		public void addToCartAndVerify() throws Throwable
		{	
			
			 if(isElementPresent(CartObj.ADD_TO_CART_IN_PRODUCT_DISPLAY," ADD TO CART IN PRODUCT DISPLAY")) {
				click(CartObj.ADD_TO_CART_IN_PRODUCT_DISPLAY," ADD TO CART IN PRODUCT DISPLAY");
			}
			
			waitForVisibilityOfElement(CartObj.ADD_TO_CART_SUCCESS_MESSAGE,"ADD TO CART SUCCESS MESSAGE");					
			isElementPresent(CartObj.ADD_TO_CART_SUCCESS_MESSAGE,"ADD TO CART SUCCESS MESSAGE",true);
			
			
		}
		
		/* PURPOSE OF METHOD : REUASABLE METHOD TO continue To Shopping
		 *  
		 *  @author : Cigniti
		 */
		public void continueToShopping() throws Throwable
		{	
			click(CartObj.CONTINUE_TO_SHOPPING,"CONTINUE TO SHOPPING");
						
		}
		/* PURPOSE OF METHOD : METHOD TO add First Displyed Item To Cart And Verify
		 *  
		 *  @author : Cigniti
		 */
		public void addFirstDisplyedItemToCartAndVerify() throws Throwable
		{	
			if(isElementPresent(CartObj.ADD_TO_CART_BUTTON_FOR_FIRST_DISPLAYED_ITEM,"ADD TO CART BUTTON FOR FIRST DISPLAYED ITEM")) {
				click(CartObj.ADD_TO_CART_BUTTON_FOR_FIRST_DISPLAYED_ITEM,"ADD TO CART BUTTON FOR FIRST DISPLAYED ITEM");
			}
			
			waitForVisibilityOfElement(CartObj.ADD_TO_CART_SUCCESS_MESSAGE,"ADD TO CART SUCCESS MESSAGE");
			
			
			
		}
		/* PURPOSE OF METHOD : METHOD TO click Cart
		 *  
		 *  @author : 
		 */
		public void clickCart() throws Throwable
		{	
			click(CartObj.CART,"CART");
			if(isElementNotPresent(CanadaObj.CART_LABEL, "Cart header label displayed")) {
				refreshPage();	
			}
			
		}
		
		/* PURPOSE OF METHOD : METHOD to verify bundle added to cart
		 *  
		 *  @author : 
		 */
		public void verifyBundleIsAddedToCart() throws Throwable
		{	
			waitForVisibilityOfElement(CartObj.BUNDLE,"Bundle");
			if(isElementPresent(CartObj.BUNDLE,"Bundle",true)) {
				reporter.SuccessReport("Verify the Bundle  on Cart", "Bundle Field Exists", "");
			}
			else {
				reporter.failureReport("Verify the Bundle  on Cart", "Bundle Field Does Not Exist", "");
			}
			
				
		}
		/* PURPOSE OF METHOD : METHOD TO add First Displyed Item To Cart And Verify
		 *  
		 *  @author : 
		 */
		public void updateCartQuantity(String quantity) throws Throwable
		{	
			waitForVisibilityOfElement(CartObj.QUANTITY,"QUANTITY");
			clearData(CartObj.QUANTITY);
			type(CartObj.QUANTITY,quantity,"NUMBER OF ITEMS");
			click(CartObj.UPDATE,"UPDATE");
			
		}
		
		public void updateCartQuantityByZero(String quantity) throws Throwable
		{	
			waitForVisibilityOfElement(CartObj.QUANTITY,"QUANTITY");
			clearData(CartObj.QUANTITY);
			type(CartObj.QUANTITY,quantity,"NUMBER OF ITEMS");
			if(isElementNotPresent(CartObj.UPDATE,"UPDATE")) {
				reporter.SuccessReport("Upadting quantity ", "Quantity will not be updated for zero and alphebets", "");
			}
			else {
				reporter.SuccessReport("Upadting quantity ", "Quantity will be updated for zero and alphebets", "");
			}
			
		}
		/* PURPOSE OF METHOD : METHOD TO add First Displyed Item To Cart And Verify
		 *  
		 *  @author : 
		 */
		public void deleteItemFromCart() throws Throwable
		{	waitForVisibilityOfElement(CartObj.DELETE,"DELETE");
		    click(CartObj.DELETE,"DELETE");
			
		}
		/* PURPOSE OF METHOD : METHOD TO add First Displyed Item To Cart And Verify
		 *  
		 *  @author : 
		 */
		public void addSecondDisplyedItemToCartAndVerify() throws Throwable
		{	
			waitForVisibilityOfElement(CartObj.ADD_TO_CART_BUTTON_FOR_SECOND_DISPLAYED_ITEM,"ADD TO CART BUTTON FOR SECOND DISPLAYED ITEM");
			click(CartObj.ADD_TO_CART_BUTTON_FOR_SECOND_DISPLAYED_ITEM,"ADD TO CART BUTTON FOR SECOND DISPLAYED ITEM");
			isElementPresent(CartObj.ADD_TO_CART_SUCCESS_MESSAGE,"ADD TO CART SUCCESS MESSAGE",true);
							
		}
	
		/* PURPOSE OF METHOD : METHOD TO add First Displyed Item To Cart And Verify
		 *  
		 *  @author : 
		 */
		public void emptyCartAndVerify() throws Throwable
		{	
			waitForVisibilityOfElement(CartObj.EMPTY_CART,"EMPTY CART");
			click(CartObj.EMPTY_CART,"EMPTY CART");
			if(isElementPresent(CartObj.EMPTY_CART_MESSAGE,"EMPTY CART MESSAGE",true)) {
				reporter.SuccessReport("Verifying Cart is empty", "Cart is emptied", "");
			}
			else {
				reporter.failureReport("Verifying Cart is empty", "Cart is not emptied", "");
			}
							
		}
		
		/* PURPOSE OF METHOD : METHOD TO add First Displyed Item To Cart And Verify
		 *  
		 *  @author : 
		 */
		public void verifyCartIsEMpty() throws Throwable
		{	
			isElementPresent(CartObj.EMPTY_CART_MESSAGE,"EMPTY CART MESSAGE",true);
							
		}
	
		/* PURPOSE OF METHOD : METHOD TO add First Displyed Item To Cart And Verify
		 *  
		 *  @author : 
		 */
		public void verifyBuyingNotEnabledMessage() throws Throwable
		{	
			waitForVisibilityOfElement(CartObj.BUYING_NOT_ENBLED_MESSAGE,"BUYING NOT ENBLED MESSAGE");
			isElementPresent(CartObj.BUYING_NOT_ENBLED_MESSAGE,"BUYING NOT ENBLED MESSAGE",true);
							
		}
		/* PURPOSE OF METHOD : METHOD TO add First Displyed Item To Cart And Verify
		 *  
		 *  @author : 
		 */
		public void verifyProceedToCheckOut() throws Throwable
		{	
			waitForVisibilityOfElement(CartObj.PROCEED_TO_CHECKOUT,"PROCEED TO CHECKOUT");
			
			if(isElementPresent(CartObj.PROCEED_TO_CHECKOUT,"PROCEED TO CHECKOUT",true)) {
				reporter.SuccessReport("Verifying proceed checkout button", "Proceed to checkout button is visible", "");
			}
			else {
				reporter.failureReport("Verifying proceed checkout button", "Proceed to checkout button is not visible", "");
			}
		}
		
		public void verifyProceedToCheckOutIsNotVisible() throws Throwable
		{	
			
			if(isElementNotPresent(CartObj.PROCEED_TO_CHECKOUT,"PROCEED TO CHECKOUT")) {
				reporter.SuccessReport("Verifying proceed checkout button", "Proceed to checkout button is not visible", "");
			}
			else {
				reporter.failureReport("Verifying proceed checkout button", "Proceed to checkout button is visible", "");
			}
		}
	
		/* PURPOSE OF METHOD : METHOD TO add First Displyed Item To Cart And Verify
		 *  
		 *  @author : 
		 */
		public void addSearchItem() throws Throwable
		{	
			waitForVisibilityOfElement(CartObj.SEARCH_ITEM,"SEARCH ITEM");
			click(CartObj.SEARCH_ITEM,"SEARCH ITEM");
					
		}
		
		/* PURPOSE OF METHOD : METHOD TO add First Displyed Item To Cart And Verify
		 *  
		 *  @author : 
		 */
		public void closePopUp() throws Throwable
		{	
			waitForVisibilityOfElement(CartObj.CLOSE_POPUP,"CLOSE POPUP");
			click(CartObj.CLOSE_POPUP,"CLOSE POPUP");
					
		}
		
		public void clickOnLoginAsForDeleteFunctionality(String PartNumber , String searchItem) throws Throwable
		{	String mainWindow = parentWindow();
			click(CMTObj.LOGIN_AS,"login as");
			switchToWindow(mainWindow);
			searchProduct(PartNumber);			
			addFirstDisplyedItemToCartAndVerify();
			continueToShopping();
			searchProduct(searchItem);
			addFirstDisplyedItemToCartAndVerify();
			closePopUp();
			clickCart();
			deleteItemFromCart();
			emptyCartAndVerify();
			
			
		}
		
		/**
		 * This method is to click on the Account tools from the side menu and click on product group.
		 * @param toolsMenuName
		 * @param dropDown
		 * @param productGroup
		 * @param productName
		 * @throws Throwable
		 */
		public void clickAccountToolsFromSideMenuAndClickOnProductGrp(String toolsMenuName, String dropDown ,String productGroup,String productName) throws Throwable{
			   click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
			   click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
			   click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
			   click(CommonObj.getCompanyStandardsProductGroup(productGroup, productName), "select product from product group");
		}
		/**
		 * This method is to click on the Account tools from the side menu and click on product group.
		 * @param toolsMenuName
		 * @param dropDown
		 * @throws Throwable
		 */
		public void clickOnAccountToolsAndClickOnProductGrp(String toolsMenuName, String dropDown ) throws Throwable{
			   click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
			   click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
			   click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");	   
		}
		
		/**
		 * This method is to click on the account tools menu in the home page
		 * @throws Throwable 
		 * 
		 */
		public void clickOnAccountToolsMenuIcon(String toolsMenuName, String dropDown ) throws Throwable{
			click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
			   click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");	 
		}
		
		/**
		 * Method is to click on the account tools menu name
		 * @param toolsMenuName
		 * @throws Throwable
		 */
		public void clickOnAccountToolsMenuName(String toolsMenuName) throws Throwable{
			click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		}
		
		/**
		 * Method is to click on the product group in company standards
		 * @param productGroup
		 * @param productName
		 * @throws Throwable 
		 */
		public void clickOnProductGrpInCompanyStandard(String productGroup,String productName) throws Throwable{
			 click(CommonObj.getCompanyStandardsProductGroup(productGroup, productName), "select product from product group");
		}
		
		/**
		 * Method is to click on the account tools menu DD
		 * @param toolsMenuName
		 * @throws Throwable
		 */
		public void clickOnAccountToolsMenuDropDown(String toolsMenuName,String dropDown) throws Throwable{
			  click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");	 
		}
		
	
		
		/**
		 * This method is to click on Add to Order button and click on View cart link in the Items added to cart
		 * @throws Throwable
		 */
		public void addToOrderAndViewCart() throws Throwable{
			click(CommonObj.ADD_TO_ORDER, "Add to order button");
			isElementPresent(CommonObj.ITEMS_ADDED_TO_CART_POPUP, "items added to cart",true);
			click(CommonObj.VIEW_CART_PRODUCT_GROUP, "view cart link");
		}
		/**
		 * This method is to click on LogOut Link
		 * 
		 * @param header for logout link 
		 * @throws Throwable
		 * @author : CIGNITI
		 */
		public void clickLogOutLink(String header) throws Throwable
		{	
			isElementPresent(CommonObj.getPrimaryNavLink(header), "Logout link");
			click(CommonObj.getPrimaryNavLink(header), "Logout link");
		}
		/**
		 * This method is to click on LogOut Link
		 * 
		 * @param header for logout link 
		 * @throws Throwable
		 * @author : CIGNITI
		 */
		public void getToatalPrice(String header) throws Throwable
		{	
			isElementPresent(CommonObj.getPrimaryNavLink(header), "Logout link");
			click(CommonObj.getPrimaryNavLink(header), "Logout link");
		}
		
		/**
		 * This method is to waits until the page loading completes
		 * @throws Throwable
		 */
		public void spinnerImage() throws Throwable {
			Thread.sleep(2000);
			waitForInVisibilityOfElement(CommonObj.SPINNER_IMAGE, "spinner image");
			if(isElementNotPresent(CommonObj.SPINNER_IMAGE, "spinner image")) {
				reporter.SuccessReport("spinner image","spinner image disapperaed","");
			}else {
				reporter.failureReport("spinner image","spinner image not disapperaed","");
			}
		}
		
		/**
		 * Method is to get the contract name on the product details page.
		 * @throws Throwable
		 */
		public String contractOnProductDetailPage() throws Throwable{
			String contractName=null;
			if(isElementPresent(CommonObj.CONTRACT_NAME_ON_PRODUCT_DETAILS, "Contract name on produt details page")){
				contractName=getText(CommonObj.CONTRACT_NAME_ON_PRODUCT_DETAILS, "Contract name on produt details page");
				reporter.SuccessReport("verify the contract", "contract displayed on the product details page is : ",contractName);
			}else{
				reporter.failureReport("verify the contract", "Contract is not displayed on the product details page.Expected contract is : ",contractName);
			}
			return contractName;
		}
		
		
		/**
		 * Method is to verify the contract name on the cart page
		 * @throws Throwable
		 */
		public void verifyContractInCart(String contractName) throws Throwable{
		
		if(isElementPresent(CommonObj.getContractInCart(contractName), "Contract name")){
			reporter.SuccessReport("Verify contract in cart page","Contract verification is successful",contractName);
		}else{
			reporter.failureReport("Verify contract in cart page","Contract verification is not successful.Expecte is :",contractName);
		}
		}
		
		public static Integer getColumnNumberForString(String path, String sheet_name, int rowNumber, String rowValue) throws IOException {			
			Sheet sheet = getSheetObj( path, sheet_name);
			Row row = sheet.getRow(rowNumber);
			System.out.println("getFirstCellNum :"+row.getFirstCellNum());
			System.out.println("getLastCellNum  :"+row.getLastCellNum());
			for(int i = row.getFirstCellNum();i< row.getLastCellNum();i++) {			
				Cell cell = row.getCell(i);
				System.out.println("CellValue :"+cellToString(cell));
				if(cellToString(cell).equals(rowValue)) {
					return(i);
				}
			}
			return -1;			
		}
		
		public static Integer getRowNumberForString(String path, String sheet_name, int colNumber, String colValue) throws IOException {
			Sheet sheet = getSheetObj( path, sheet_name);
			for(int i = sheet.getFirstRowNum();i< sheet.getLastRowNum();i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(colNumber);
				System.out.println("CellValue :"+cellToString(cell));
				if(cellToString(cell).equals(colValue)) {
					return(i);
				}
			}
			return -1;			
		}

		public static String readFromExcel(String path, String sheet_name, int rowcount, int colcount) throws IOException {
			Sheet sheet = getSheetObj( path, sheet_name);
			Row row = sheet.getRow(rowcount);
			Cell cell = row.getCell(colcount);
			System.out.println("Cell Content :: "+cellToString(cell));
			return cellToString(cell);
		}
		
		public static List<String> readRowFromExcel(String path, String sheet_name, int rowNumber) throws IOException {
			List<String> values = new ArrayList<String>();
			Sheet sheet = getSheetObj( path, sheet_name);
			Row row = sheet.getRow(rowNumber);
			//System.out.println("getFirstCellNum :"+row.getFirstCellNum());
			//System.out.println("getLastCellNum  :"+row.getLastCellNum());
			for(int i = row.getFirstCellNum();i< row.getLastCellNum();i++) {			
				Cell cell = row.getCell(i);
				System.out.println("CellValue :"+cellToString(cell));
				values.add(cellToString(cell));
			}			
			return values;
		}
		
		private static Sheet getSheetObj(String path, String sheet_name) throws IOException  {
			Sheet sheet = null;
			Boolean sheetexist = false;
			System.out.println("Path :: "+path);
			try (FileInputStream fis = new FileInputStream(path)){
						if (path.endsWith("xls")) {
					try(Workbook hssfWorkbook = new HSSFWorkbook(fis)){
					for (int i = hssfWorkbook.getNumberOfSheets() - 1; i >= 0; i--) {
						Sheet tmpSheet = hssfWorkbook.getSheetAt(i);
						if (tmpSheet.getSheetName().equals(sheet_name)) {
							sheet = hssfWorkbook.getSheetAt(i);
							sheetexist = true;
						}
					}
				}
					catch(Exception e){
						Reporter.log("Exception during the Excel processing::", true);
						e.printStackTrace();
					}
				} else {
					try(Workbook xssfWorkbook = new XSSFWorkbook(fis)){
					for (int i = xssfWorkbook.getNumberOfSheets() - 1; i >= 0; i--) {
						Sheet tmpSheet = xssfWorkbook.getSheetAt(i);
						if (tmpSheet.getSheetName().equals(sheet_name)) {
							sheet = xssfWorkbook.getSheetAt(i);
							sheetexist = true;
						}
					}
				}
					catch(Exception e){
						Reporter.log("Exception during the Excel processing::", true);
						e.printStackTrace();
					}
				}
				
				if (!sheetexist) {
					Reporter.log(sheet_name + ": sheet does not exist in the " + path + " excel file", true);
				}
			
				
			} catch (Exception e) {
				Reporter.log("Exception during the Excel processing::", true);
				e.printStackTrace();
			}
			return sheet;

		}
		private static String cellToString(Cell cell) {

			int type = cell.getCellType();
			Object result;
			switch (type) {
			case Cell.CELL_TYPE_NUMERIC:
				result = cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_STRING:
				result = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				result = cell.getBooleanCellValue();
				break;
			default:
				throw new RuntimeException("There is no support for this type of cell");
			}

			return result.toString();
		}

	/**
	 * ' This method is to click on the insight logo on home page header
	 * 
	 * @throws Throwable
	 */
	public void clickOnInsightLogoOnHomePage() throws Throwable {
		click(CommonObj.INSIGHT_LOGO_HOME_PAGE, "Insight Logo link");
	}
		
	public void clickOnAccountToolsAndClickOnProductGrps(String toolsMenuName, String dropDown) throws Throwable {
		if (isElementNotPresent(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon")) {
			click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		}
		click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");

	}
		
	public void verifyPDPMesssageforAdobeProducts() throws Throwable {
		if (isElementPresent(CommonObj.PDP_MESSAGEVERIFY, "Prorated Agreement period message")) {
			reporter.SuccessReport("Verify Message in Product Details Page", "Proration message exists in Product details page","");
		}else{
			reporter.failureReport("Verify Message in Product Details Page", "The price displayed will be prorated in the Cart based on the remaining agreement period. is Not Exists","");
		}
		}

}