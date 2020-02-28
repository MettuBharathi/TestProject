package com.insight.Lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.CommonObj;
import com.insight.ObjRepo.ProductDetailObj;
import com.insight.ObjRepo.productsDisplayInfoObj;

public class ProductDetailLib extends ProductDetailObj {
	/**
	 * This method is to select the first product in the search results and
	 * verify the presence of Personal product list link and click on it.
	 * 
	 * @throws Throwable
	 */
	public void selectProductAndAddToPersonalProductList() throws Throwable {

		// click(productsDisplayInfoObj.FIRST_PROD_NAME, "First product in
		// search results page");
		isElementPresent(productsDisplayInfoObj.BACK_TO_RESULTS, "Back to results");
		if (isElementNotPresent(productsDisplayInfoObj.ADD_TO_PERSONAL_PROD_LINK, "Add to personal product link")) {
			reporter.SuccessReport("Verify personal product link present ",
					"Personal product is not present. Enable the settings and Login As", "");
			LOG.info("Enable the settings in CMT and Login As to get the Add to Personal product list link");
		} else {
			if (isElementPresent(productsDisplayInfoObj.ADD_TO_PERSONAL_PROD_LINK, "Add to personal product link")) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo(0, 250)");
				click(productsDisplayInfoObj.ADD_TO_PERSONAL_PROD_LINK, "Add to personal product link");
			}
		}
	}

	/**
	 * This method is to get the mfr number displayed in the product details
	 * @throws Throwable
	 */
	public String getMFRNumberInProductInfopage() throws Throwable {
		waitForVisibilityOfElement(productsDisplayInfoObj.MFR_NUMBER_PRODUCT_DETAILS_PAGE,
				"MFR_NUMBER_PRODUCT_DETAILS_PAGE");
		String prodMfrNumber = getText(productsDisplayInfoObj.MFR_NUMBER_PRODUCT_DETAILS_PAGE,
				"MFR_NUMBER_PRODUCT_DETAILS_PAGE").replace("\"", "").replace("Mfr. #", "").trim();
		return prodMfrNumber;
	}
	
	public String getInsightPartNumberInProductInfopage() throws Throwable {
		waitForVisibilityOfElement(productsDisplayInfoObj.INSIGHT_PART_NUMBER_PROD_DETAILS,
				"MFR_NUMBER_PRODUCT_DETAILS_PAGE");
		String prodMfrNumber = getText(productsDisplayInfoObj.INSIGHT_PART_NUMBER_PROD_DETAILS,
				"MFR_NUMBER_PRODUCT_DETAILS_PAGE").replace("\"", "").replace("Insight #", "").trim();
		return prodMfrNumber;
	}
	
	
	/**
	 * This method is to get the mfr number displayed in the product details in warrenties tab
	 * 
	 * 
	 * @throws Throwable
	 */
	public String getMFRNumberInProductInfopageInWarrentiesTab() throws Throwable {
		waitForVisibilityOfElement(productsDisplayInfoObj.PART_NUMBER_WARRENTIES_TAB,
				"MFR number in product details page in warrenties tab");
		String prodMfrNumber = getText(productsDisplayInfoObj.PART_NUMBER_WARRENTIES_TAB,
				"MFR number in product details page in warrenties tab").replace("\"", "").replace("Mfr Part #:", "").replace("| Insight Part #:","").trim();
		return prodMfrNumber;
	}
	/**
	 * This method is to increase the quantity of product in warrenties tab
	 * 
	 * 
	 * @throws Throwable
	 */
	public void updateQuantityInWarrentiesTab(String quantity) throws Throwable {
		waitForVisibilityOfElement(productsDisplayInfoObj.QUANTITY_IN_WARRENTIES_TAB, "Quantity");
		clearData(productsDisplayInfoObj.QUANTITY_IN_WARRENTIES_TAB);
		type(productsDisplayInfoObj.QUANTITY_IN_WARRENTIES_TAB, quantity, "quantity");
		
	}
	
	/**
	 * This method is used to click on add to cart in warrienties tab
	 * 
	 * 
	 * @throws Throwable
	 */
	public void clickAddToCartInWarrientiesTab() throws Throwable {
		click(productsDisplayInfoObj.ADD_TO_CART_IN_WARRENTIES_TAB, "Add to cart");
		
	}

	/**
	 * This method is to verify personal prodcut list and delete the list
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifyPersonalProductList(String partNo) throws Throwable {
		String result = null;
		boolean flag = true;
		click(productsDisplayInfoObj.ADDED_TO_PERSONAL_PROD_LIST, "ADDED TO PERSONAL PRODUCT LIST");
		if (flag) {
			List<WebElement> myList = driver.findElements(productsDisplayInfoObj.MFR_PART);
			List<String> all_elements_text = new ArrayList<>();
			for (int i = 0; i < myList.size(); i++) {
				all_elements_text.add(myList.get(i).getText());
				result = myList.get(i).getText();
				if (result.contains(partNo)) {
					reporter.SuccessReport("Verify the part number", "Part number verification is sucessful", result);
				}
			}
		} else {
			reporter.failureReport("Verify the part number",
					"Part number verification is not successful. expected is : " + partNo + "Actual is : " + result,
					"");
		}

	}

	/**
	 * This method is to delete the personal product list
	 * 
	 * 
	 * @throws Throwable
	 */
	public void deletePersonalProductList(String partNo) throws Throwable {
		if (isElementPresent(deleteProduct(partNo), "Delete button")) {
			System.out.println("Inside if ======");
			click(deleteProduct(partNo), "Delete button");
		}

		if (isElementPresent(DLETED_MESSAGE, "Deleted message")) {
			reporter.SuccessReport("Verifying Expected product should be deleted on Personal products List",
					"Product is sucessfully deleted", partNo);
		} else {
			reporter.failureReport("Verifying Expected product should be deleted on Personal products List",
					"Product is not sucessfully deleted", partNo);
		}
	}

	/**
	 * This method is to add part item to the Personal Product List and verify
	 * the products added.
	 * 
	 * @param partNo
	 * @throws Throwable
	 */
	public void addItemsToProductListToPersonalProdcutList(String partNo) throws Throwable {
		String result = null;
		boolean flag = true;

		type(productsDisplayInfoObj.ADD_ITEMS_TEXTBOX, partNo, "part number");
		click(productsDisplayInfoObj.ADD_BTN, "Add button");

		if (flag) {
			List<WebElement> myList = driver.findElements(productsDisplayInfoObj.MFR_PART);
			List<String> all_elements_text = new ArrayList<>();
			for (int i = 0; i < myList.size(); i++) {
				all_elements_text.add(myList.get(i).getText());
				result = myList.get(i).getText();
				if (result.contains(partNo)) {
					reporter.SuccessReport("Verify the part number", "Part number verification is sucessful", result);
				}
			}
		} else {
			reporter.failureReport("Verify the part number",
					"Part number verification is not successful. expected is : " + partNo + "Actual is : " + result,
					"");
		}

	}

	/**
	 * This method is to click on add to compare list and to compare products
	 *
	 * 
	 * 
	 * @throws Throwable
	 */
	public void clickAddtoCompareList() throws Throwable {
		waitForVisibilityOfElement(ADD_TO_COMPARE_LIST, "Add to compare list");
		click(ADD_TO_COMPARE_LIST, "Add to compare list");
		click(ADD_TO_COMPARE_LIST, "Add to compare list");
		waitForVisibilityOfElement(COMPARE_NOW, "Compare now");
		click(COMPARE_NOW, "Compare now");
		waitForVisibilityOfElement(COMPARE_PRODUCTS, "compare products");
		if (isElementPresent(COMPARE_PRODUCTS, "compare products")) {
			reporter.SuccessReport("Verify Compare products Page", "Product Compare List  Exists", "");
		} else {
			reporter.failureReport("Verify Compare products Page", "Product Compare List  Exists", "");
		}
	}

	public void clickOnAccountToolsAndClickOnProductGrp(String toolsMenuName, String dropDown) throws Throwable {
		click(ACCOUNT_TOOLS, "Account tools menu icon");
		click(getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
	}

	List<String> Products = new ArrayList<String>();

	/**
	 * This method is to verify recently viewed products column is Visible or
	 * Not
	 * 
	 * @throws Throwable
	 */
	public void Verifyrecentlyviwedproductslabel() throws Throwable {
		waitForVisibilityOfElement(RECENTLYVIEWD_PRODUCTSLABEL, "Recently viewed");
		if (isElementPresent(RECENTLYVIEWD_PRODUCTSLABEL, "User ID")) {
			reporter.failureReport("Recently viewed products", "Recently viewed products is Visible", "");

		} else {
			reporter.SuccessReport("Recently viewed products", "Recently viewed products is Not Visible", "");
		}
	}

	/**
	 * This method is to Click on back to results
	 * 
	 * @throws Throwable
	 */
	public void Clickonbacktoresults() throws Throwable {
		click(BACKTORESULTS, "back to results");
	}

	/**
	 * This method is to Click on view details under particular product in
	 * recently viewed products
	 * 
	 * @throws Throwable
	 */
	public void Clickonviewdetails(String product) throws Throwable {

		click(Clickviewdetailsunderproduct(product), "view details");
	}

	/**
	 * This method is to verify product info page
	 * 
	 * @throws Throwable
	 */
	public void verifyproductinfopage(String product) throws Throwable {
		click(Clickviewdetailsunderproduct(product), "view details");
		waitForVisibilityOfElement(Productinfopage(product), "Product Info Page of::" + product + "");
		if (isElementPresent(Productinfopage(product), "Product Info Page")) {
			reporter.SuccessReport("Product Info Page", "Product Info Page of::" + product + " is displayed", "");
		} else {
			reporter.SuccessReport("Product Info Page", "Product Info Page is not displayed", "");
		}
	}

	/**
	 * This method is to verify recently viewed items
	 * 
	 * @return
	 * @throws Throwable
	 */
	public void Verifyrecentlyvieweditems() throws Throwable {

		waitForVisibilityOfElement(RECENTLYVIEWD_PRODUCTS, "Recently Viewd");
		List<WebElement> myList2 = driver.findElements(RECENTLYVIEWD_PRODUCTS);
		for (int i = 0; i < myList2.size(); i++) {
			if (myList2.get(i).isDisplayed()) {
				reporter.SuccessReport("Recently viewd products ::", "" + myList2.get(i).getText() + "", "");
			} else {
				reporter.failureReport("Recently viewd products Not Displayed ",
						"Recently viewd products Not Displayed", "");
			}
			Thread.sleep(2000);
			if (myList2.equals(Products)) {
				reporter.SuccessReport("Recently viewd products ::", "" + Products + "", "");
			} else {
				reporter.failureReport("Recently viewd products Not Displayed ",
						"Recently viewd products Not Displayed", "");
			}
		}
	}

	/**
	 * This method is to get the recently viewed items
	 * 
	 * @return
	 * @return
	 * @return
	 * @throws Throwable
	 */
	public String getrecentlyvieweditems(String Search_Item) throws Throwable {
		CommonLib commonLib = new CommonLib();
		CartLib cartLib = new CartLib();
		commonLib.searchProduct(Search_Item);
		String productName = getText(productsDisplayInfoObj.FIRST_PROD_NAME, "Get product name");
		cartLib.selectFirstProductDisplay();
		Products.add(productName);
		return productName;
	}

	/**
	 * This method is to Click on back to results
	 * 
	 * @throws Throwable
	 */
	public void ClickonDeleteButtonofcustomcatalog() throws Throwable {
		click(DELETEBUTTON_COUSTOMCATALOG, "Delete custom catalog");
		Thread.sleep(2000);
		acceptAlert();
	}

	/**
	 * This method is to Create custom catalog
	 * 
	 * @throws Throwable
	 */
	public void Createcustomcatalog() throws Throwable {
		click(CREATEBUTTON_COUSTOMCATALOG, "Create custom catalog");
		Thread.sleep(2000);
	}

	/**
	 * This method is to Click on Custom catalog
	 * 
	 * @throws Throwable
	 */
	public void ClickonCustomcatalog(String manufacturers) throws Throwable {
		waitForVisibilityOfElement(CUSTOMCATALOG, "Custom catalog");
		clickUntil(CUSTOMCATALOG, MANUFACTURERS_CUSTUMCATALOG(manufacturers), "Custom Catalog");
		Thread.sleep(2000);
	}

	/**
	 * This method is to Click on manufacturers in Custom catalog
	 * 
	 * @throws Throwable
	 */
	public void Clickonmanufacturers(String manufacturers) throws Throwable {
		waitForVisibilityOfElement(MANUFACTURERS_CUSTUMCATALOG(manufacturers), "" + manufacturers + "");
		click(MANUFACTURERS_CUSTUMCATALOG(manufacturers), "" + manufacturers + "");
		Thread.sleep(2000);// Include Manufacturers//Exclude
							// Manufacturers//Override Manufacturers
	}

	/**
	 * This method is to Select an Option From ExculdeManufacturers in custom
	 * catalog
	 * 
	 * @throws Throwable
	 */
	public void SelectOptionFromExculdeManufacturers(String optionOKI) throws Throwable {
		waitForVisibilityOfElement(AVAILABLETOEXCULDE_BOX, "Available To Exclude");
		selectByVisibleText(AVAILABLETOEXCULDE_BOX, optionOKI, "Exclude Manufacturer option::" + optionOKI + "");
		Thread.sleep(2000);// OKI
		click(RIGHTARROW_EXCLUDEMANFACT, "Right Arrow");
		selectByVisibleText(CURRENTEXCLUDE_MANUFACTURERS, optionOKI, "Exclude Manufacturer option::" + optionOKI + "");
		click(UPDATEBUTTON, "Update button");
		Thread.sleep(2000);
	}

	/**
	 * This method is to verify Bread crumb
	 * 
	 * @throws Throwable
	 */
	public void verifyBreadcrumb() throws Throwable {
		CartLib cartLib = new CartLib();
		String productName = getText(productsDisplayInfoObj.FIRST_PROD_NAME, "Get product name");
		cartLib.selectFirstProductDisplay();
		waitForVisibilityOfElement(BREADCRUMB, "Bread Crumb");
		if (isElementPresent(BREADCRUMB, "Bread Crumb")) {
			reporter.SuccessReport("Verify the navigation", "Sucessfully Navigated to : ", productName);
		} else {
			reporter.failureReport("Verify the navigation", "Navigation is not Sucessfully : ", productName);
		}
	}

	public void OverviewTab() throws Throwable {
		waitForVisibilityOfElement(OVERVIEWTAB, "over view");
		List<WebElement> myList2 = driver.findElements(OVERVIEWTABCONTENTS);
		for (int i = 0; i < myList2.size(); i++) {
			if (myList2.get(i).isDisplayed()) {
				reporter.SuccessReport("over view tab contents", "" + myList2.get(i).getText() + "", "");
			} else {
				reporter.failureReport("over view tab contents Not Visible ", "over view tab contents Not Visible ",
						"");
			}

		}
	}
	/**
	 * This method is to get Product details at Product info page
	 * 
	 * @return
	 * @throws Throwable
	 */
	public void Getproductdetails() throws Throwable {

		waitForVisibilityOfElement(PRODUCTDETAILS, "Product details Are");
		List<WebElement> myList2 = driver.findElements(PRODUCTDETAILS);
		for (int i = 0; i < myList2.size(); i++) {
			if (myList2.get(i).isDisplayed()) {
				reporter.SuccessReport("Product Details ::", "" + myList2.get(i).getText() + "", "");
			} else {
				reporter.failureReport("Product Details Not Displayed ", "Product Details Not Displayed", "");
			}
		}
	}
	/**
	 * This method is to verify Add Accessroies
	 * 
	 * @throws Throwable
	 */
	public void VerifyAddAccessories() throws Throwable {
		clickUntil(CartObj.ADD_TO_CART_IN_PRODUCT_DISPLAY, ADDACCESSORIES, "add to cart");
		Thread.sleep(1000);
		waitForVisibilityOfElement(ADDACCESSORIES, "Add Accessroies");
		if (isElementPresent(ADDACCESSORIES, "Add Accessroies")) {
			reporter.SuccessReport("Add Accessroies", "Add Accessroies", "");

		} else {
			reporter.failureReport("Add Accessroies", "Add Accessroies Not Visible", "");
		}
		click(CartObj.CONTINUE_TO_SHOPPING, "Continue Shopping");
	}

	/**
	 * This method is to update quantity
	 * 
	 * @throws Throwable
	 */
	public void Verifyupdatequantity() throws Throwable {
		Thread.sleep(3000);
		waitForVisibilityOfElement(NUMBERPICKER, "Number Picker");
		if (isElementPresent(UPDATEQUNTITY, "Update Quantity")) {
			click(UPDATEQUNTITY, "Update Quantity");
			reporter.SuccessReport("Update Quantity", "Quantity is updated", "");

		} else {
			reporter.failureReport("Update Quantity", "Quantity is Not updated", "");
		}
		click(CartObj.ADD_TO_CART_IN_PRODUCT_DISPLAY, "Add To Cart");
		click(CartObj.CONTINUE_TO_SHOPPING, "Continue shopping");

	}

	
	/**
	 * This method is used to verify products are in stock
	 * @param option
	 * @throws Throwable
	 */
	public void verifyAvailability( ) throws Throwable {
		
		List<WebElement> mylist=driver.findElements(STOCK);
		for (int i = 0; i < mylist.size(); i++) {
		if(isElementPresent(STOCK, "Stock")) {
			reporter.SuccessReport("Verify the Stock/Availability on Search Results page","Stock and Avaialbility exists " , "");
		}
		else {
			reporter.failureReport("Verify the Stock/Availability on Search Results page","Stock and Avaialbility does not exist  " , "");
		}
		}
	}
	
	
	/**
	 * This method is used to verify product is in stock in product detail page
	 * @param option
	 * @throws Throwable
	 */
	public void verifyAvailabilityInProductDetailPage( ) throws Throwable {
		
		
		if(isElementPresent(STOCK_PRODUCT_DETAIL_PAGE, "Stock in product detail page")) {
			reporter.SuccessReport("Verify the Stock/Availability on product Results page","Stock and Avaialbility exists " , "");
		}
		else {
			reporter.failureReport("Verify the Stock/Availability on product Results page","Stock and Avaialbility does not exist  " , "");
		}
		
	}
	
	/**
	 * This method is used to click on narrow down option --- left side of the page
	 * @param option
	 * @throws Throwable
	 */
	public void narrowDownFilters(String category, String option) throws Throwable {
		mouseClick(narrowDown(category,option), "");
//		if(isElementNotPresent(narrowDown(category,option),"")) {
//			reporter.SuccessReport("Narrow down by License", "'License' Option exists and selected",option);
//		}
//		else {
//			reporter.failureReport("Narrow down by License", "'License' Option is exists and not selected",option);
//		}
		
	}
	/**
	 * This method is used to click on unlimeted availability product
	 * @param option
	 * @throws Throwable
	 */
	public void selectUnlimetedAvailabilityProduct() throws Throwable {
		scrollUp();
		click(UNLIMETED_AVAILABILITY_PRODUCT,"unlimeted availability product");
	}
	
	/**
	 * This method is to select account tools from the side menu and click on product from the product group and verify description. 
	 * @param toolsMenuName
	 * @param dropDown
	 * @param productGroup
	 * @param productName
	 * @throws Throwable
	 */
	public void selectAccountToolsFromSideMenuAndClickOnProductGrp(String toolsMenuName, String dropDown ,String productGroup,String productName) throws Throwable{
		  
		   click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		   click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		   click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
		   click(CommonObj.getCompanyStandardsProductGroup(productGroup, productName), "select product from product group");
		   Thread.sleep(1000);
		   String prodDesc=getText(STOCK_ACCOUNT_TOOLS, "product description account tools");
		   clickUntil(PRODUCT_GROUP_COI_CSI_RESERVED, CommonObj.MINI_WINDOW, "product description account tools");
		   
		   Thread.sleep(10000);
		   
		   Set<String> handle=driver.getWindowHandles();
		   if (handle.size()>2) {
			   switchToChildWindow();
			   Thread.sleep(10000);
			   String actualDesc=getText(STOCK_MINI_WINDOW, "product description in PPP window");
			   if(actualDesc.contains(prodDesc)){
				   reporter.SuccessReport("verify the PPP window displayed for the selected product","PPP mini window displayed and the selected product displayed : \n ",actualDesc);
			   }
	    	}else{
			reporter.failureReport("verify the PPP window displayed for the selected product", "PPP window is not opened","");
		}
		   driver.close();
		   ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		   driver.switchTo().window(tabs.get(1));
	}

	/**
	 * This method is to verify the LoginUser
	 * 
	 * @throws Throwable
	 */
	public void verifytheLoginUser(String LnameEmailUname) throws Throwable {
		waitForVisibilityOfElement(LnameEmailUname(LnameEmailUname), "LnameEmailUname");
		if (isElementPresent(LnameEmailUname(LnameEmailUname), "LnameEmailUname")) {
			reporter.SuccessReport("The Loggedin UserName::", "" + LnameEmailUname + "", "");

		} else {
			reporter.failureReport("LnameEmailUname is Not valid", "LnameEmailUname is Not valid", "");
		}
	}

	/**
	 * This method is to Estimate total price at Product display page
	 * 
	 * @throws Throwable
	 */

	public void Estimatetotalprice(String ZIPcode) throws Throwable {
		click(PRICEESTIMATOR, "Price Estimator");
		waitForVisibilityOfElement(PRICEESTIMATOR_POPUP, "Price Estimator PopUp");
		if (isElementPresent(TEXTFIELD_ZIPCODE, "ZIP code Textfield")) {
			click(TEXTFIELD_ZIPCODE, "ZIP code Textfield");
			type(TEXTFIELD_ZIPCODE, ZIPcode, "ZIP code");
			reporter.SuccessReport("ZIP code Textfield", "ZIP code Textfield", "");
		} else {
			reporter.failureReport("ZIP code Textfield unable to type", "ZIP code Textfield unable to type", "");
		}
		Thread.sleep(2000);
		click(ESTIMATE_BUTTON, "Estimate Button");
	}
	
	/**
	 * This method is to verify Estimated tax
	 * 
	 * @throws Throwable
	 */
	public void verifyEstimatedtax() throws Throwable {
		waitForVisibilityOfElement(ESTIMATEDTAX_LABEL, "Estimated Tax");
		if (isElementPresent(ESTIMATEDTAX_LABEL, "Estimated Tax")) {
			String EstimatedTax = getText(ESTIMATEDTAX, "Estimated Tax").trim();
			reporter.SuccessReport("Estimated Tax", "USD " + EstimatedTax + "", "");
		} else {
			reporter.failureReport("Estimated Tax", "Estimated Tax is not visible", "");
		}
	}
	/**
	 * This method is to verify Estimated Shipping
	 * 
	 * @throws Throwable
	 */
	public void verifyEstimatedshipping() throws Throwable {
		if (isElementPresent(ESTIMATEDSHIPPING_LABEL, "Estimated Shipping")) {
			String Estimatedshipping = getText(ESTIMATEDSHIPPING, "Estimated Tax").trim();
			reporter.SuccessReport("Estimated Shipping", "USD " + Estimatedshipping + "", "");
		} else {
			reporter.failureReport("Estimated Shipping", "Estimated Shipping is not visible", "");
		}
	}
	
	/**
	 * This method is to verify Estimated Price
	 * 
	 * @throws Throwable
	 */
	public void verifyEstimatedPrice() throws Throwable {
		if (isElementPresent(ESTIMATEDPRICE, "Estimated Price")) {
			String EstimatedPrice = getText(ESTIMATEDPRICE, "Estimated Price").trim();
			reporter.SuccessReport("Estimated Price", "USD " + EstimatedPrice + "", "");
		} else {
			reporter.failureReport("Estimated Price", "Estimated Price is not visible", "");
		}
		click(PRICEESTIMATOR_POPUPCLOSE, "Close Popup");

	}

	/**
	 * This method is to verify the Contract Information Center info
	 * 
	 * @throws Throwable
	 */
	public void verifyContractDetails() throws Throwable {
		if (isElementPresent(CONTRACT_TITLE, "Contract title")) {
			
			String contractTitle=getText(CONTRACT_TITLE, "Contract title");
			if (!getText(CONTRACT_TITLE, "Contract title").equals("")) {
				reporter.SuccessReport("Verifying contarct title", "Contract title exists",
						contractTitle);
			} else {
				reporter.failureReport("Verifying contarct title", "Contract title does not exists", "");
			}
		}

		 String contractNumber=getText(CONTRACT_NUMBER, "Contract number");
		if (isElementPresent(CONTRACT_NUMBER, "Contract number")) {
			if (!getText(CONTRACT_NUMBER, "Contract number").equals("")) {
				reporter.SuccessReport("Verifying contract number", "Contract number exists",
						contractNumber);
			} else {
				reporter.failureReport("Verifying contarct number", "Contract number does not exists", "");
			}
		}

		String startDate=getText(START_DATE, "Start date");
		if (isElementPresent(START_DATE, "Start date")) {
			if (!getText(START_DATE, "Start date").equals("")) {
				reporter.SuccessReport("Verifying Start date ", "Start date exists", startDate);
			} else {
				reporter.failureReport("Verifying Start date ", "Start date does not exists", "");
			}
		}

		String currentDate=getText(CURRENT_END_DATE, "Current end date");
		if (isElementPresent(CURRENT_END_DATE, "Current end date")) {
			if (!getText(CURRENT_END_DATE, "Current end date").equals("")) {
				reporter.SuccessReport("Verifying Current end datee ", "Current end date exists",
						currentDate);
			} else {
				reporter.failureReport("Verifying Current end date ", "Current end date does not exists", "");
			}
		}

		String customers=getText(ELIGIBLE_CUSTOMERS, "Elgible customers");
		if (isElementPresent(ELIGIBLE_CUSTOMERS, "Elgible customers")) {
			if (!getText(ELIGIBLE_CUSTOMERS, "Elgible customers").equals("")) {
				reporter.SuccessReport("Verifying Elgible customers", "Elgible customers exists",
						customers);
			} else {
				reporter.failureReport("Verifying Elgible customers", "Elgible customers does not exists", "");
			}
		}

		String paymentTerms=getText(PAYMENT_TERMS, "Payment terms");
		if (isElementPresent(PAYMENT_TERMS, "Payment terms")) {
			if (!getText(PAYMENT_TERMS, "Payment terms").equals("")) {
				reporter.SuccessReport("Verifying Payment terms", "Payment terms exists",
						paymentTerms);
			} else {
				reporter.failureReport("Verifying Payment terms", "Payment terms does not exists", "");
			}
		}

		String delivery=getText(DELIVERY, "Delivery");
		if (isElementPresent(DELIVERY, "Delivery")) {
			if (!getText(DELIVERY, "Delivery").equals("")) {
				reporter.SuccessReport("Verifying Delivery", "Delivery exists",delivery );
			} else {
				reporter.failureReport("Verifying Delivery", "Delivery does not exists", "");
			}
		}
		
		String return_info=getText(RETURN_INFO, "Return info");

		if (isElementPresent(RETURN_INFO, "Return info")) {
			if (!getText(RETURN_INFO, "Return info").equals("")) {
				reporter.SuccessReport("Verifying Return info", "Return info exists",
						return_info);
			} else {
				reporter.failureReport("Verifying Return info", "Return info does not exists", "");
			}
		}

	}
	
	/**
	 * Method is to verify the Contract In product Detail Page
	 * @throws Throwable
	 */
		public void verifyContractInproductDetailPage() throws Throwable {
			if(isElementPresent(CONTRACT_IN_PRODUCTDETAIL, "contract in product detail")) {
				reporter.SuccessReport("Products Details Page", "Contract exists", "");
		}
			else {
				reporter.failureReport("Products Details Page", "Contract does not exists", "");
			}
	}
	


	/**
	 * This method is to verify Reviews at Product Display
	 * 
	 * @throws Throwable
	 */
	public void VerifyreviwsatProductDisplay() throws Throwable {
		if (isElementPresent(RATING_PRODUCTDISPLAY, "Reviews Visible")) {
			reporter.SuccessReport("Reviews", "Reviews Visible in Product Display", "");
		} else {
			reporter.failureReport("Reviews Visible", "Reviews not Visible", "");
		}
	}
	
	/**
	 * This method is to Click on Reviews in Review Submission From
	 * 
	 * @throws Throwable
	 */
	public void ToclickonReviews() throws Throwable{
		waitForVisibilityOfElement(REVIEWSSYAMBOLS, "Reviews Symbols in Submission From");
		List<WebElement> myList2 = driver.findElements(REVIEWSSYAMBOLS);
		for (int i = 0; i < myList2.size(); i++) {
		if (myList2.get(i).isDisplayed()) {
			myList2.get(i).click();
				reporter.SuccessReport("Reviews", "Clicked on Reviews", "");
			} else {
				reporter.failureReport("Reviews ",
						"Reviews Not Clicked", "");
			}
	}
	}
	/**
	 * This method is to verify Review Submission from Error Msg
	 * 
	 * @throws Throwable
	 */
	public void Verifyreviewserrormsg() throws Throwable{
		if (isElementPresent(REVIEWSERROR_MSG, "Reviews Submission Form")) {
			reporter.SuccessReport("Reviews Error Msg", "Reviews Error Msg Already Exists", "");
		}
		else{
			reporter.failureReport("Reviews Error Msg", "Reviews Error Msg Not Exists", "");
		}
	}
	/**
	 * This method is to Fill Review Submission From
	 * 
	 * @throws Throwable
	 */
	public void FillReviewsubmissionform(String title,String Text,String Nickname) throws Throwable {
		if (isElementPresent(REVIEWSUBMISSION_FORM, "Reviews Submission Form")) {
			click(REVIEWTITLE,"Review Title");
			type(REVIEWTITLE,title,"Review Title");
			click(REVIEWTEXT,"Review Text");
			type(REVIEWTEXT,Text,"Review Text");
			click(NICKNAME,"Nick name");
			type(NICKNAME,Nickname,"Nick name");
			click(RECOMNDEDPRODUCT_RADIO,"Recomnded Product Button");
			click(TERMSANDCONDI,"Accept Terms&conditions");
		}
		else{
			reporter.failureReport("Reviews Submission Form", "Reviews Submission Form Unable To Fill", "");
		}
	}
	
	/**
	 * This method is to verify Review Submission from Error Msg
	 * 
	 * @throws Throwable
	 */
	public void clicksubmitandverifyerrormsgreviewsubmissionform() throws Throwable {
		click(SUBMIT_BUTTON,"Submit");
		if (isElementPresent(REVIEWSERROR_MSG, "Reviews Submission Form")){
			click(SUBMISSIONFORMCLOSE,"Close Button");
			reporter.SuccessReport("Reviews Error Msg", "Reviews Error Msg Exists", "");
		}
		else{
			reporter.failureReport("Reviews Error Msg", "Reviews Error Msg Not Exists", "");
		}
	}

	/**
	 * This method is to verify Review Tab And Click on LeaveReview
	 * 
	 * @throws Throwable
	 */
	public void verifyReviewtab()throws Throwable{
		waitForVisibilityOfElement(REVIEWSTAB_PRODUCTDISPLAY, "Reviews Tab");
		if (isElementPresent(REVIEWSTAB_PRODUCTDISPLAY, "Reviews Tab")){
			click(REVIEWSTAB_PRODUCTDISPLAY,"Close Button");
			click(LEAVEAREVIEW_BUTTON,"Leave Review Button");
			reporter.SuccessReport("Reviews Submission Form", "Reviews Submission Form Is Opened", "");
		}
		else{
			reporter.failureReport("Reviews Submission Form", "Reviews Submission Form Is Not Opened", "");
		}
	}
	public String getMfrpartnumofFirstproduct()throws Throwable{
		String prodMfrNumber = getText(FIRSTPRODUCT_MFRPARTNUM, "MFR_NUMBER_PRODUCT_DETAILS_PAGE")
				.replace("\"", "").replace("Mfr. #", "").trim();
		return prodMfrNumber;
	}
	
	public void recomendedProductMoreAvailablePriceAndVerifyContracts() throws Throwable {
		
			String result = null;
			boolean flag = true;
			click(RECOMENDED_PRODUCT_MPRE_AVAILABLE_PRICE, "More prices available link");
			
			if (flag) {
				List<WebElement> myList = driver.findElements(productsDisplayInfoObj.ALL_CONTRACT_PRICES);
				List<String> all_elements_text = new ArrayList<>();
				for (int i = 0; i < myList.size(); i++) {
					all_elements_text.add(myList.get(i).getText());
					result = myList.get(i).getText();
					if(myList.size()<1 ){
						reporter.failureReport("Verify the contract prices displayed ",
								"contract price are not displayed ","");
					}else{
						reporter.SuccessReport("Verify the contract prices displayed ",
								"contract price is displayed as : " , result);
					}
					
					if (isElementPresent(productsDisplayInfoObj.OPEN_MARKET, "Open Market")) {
						String openMarket = getText(productsDisplayInfoObj.OPEN_MARKET, "Open Market"); // To get the open market price to verify
						if(result.contains(openMarket)){
						reporter.SuccessReport("Verify the Open Market price",
								"Open Market price is displayed as : " , openMarket);
					   }
					}
					else if (isElementPresent(productsDisplayInfoObj.YOUR_PRICE, "Your price")) {   // verifying your price is present 
					String yourPrice=getText(productsDisplayInfoObj.YOUR_PRICE, "Your price");
						reporter.SuccessReport("Verify the Your price",
								"Your price is displayed as : " , yourPrice);
				  }
				}
				click(productsDisplayInfoObj.CLOSE_CONTRACTS_POPUP, "close popup");
			} else {
				reporter.failureReport("Verify the Open Market price", "Open Market price is not displayed","");
			}
		}
	
	public void clickMostOftenPurchasedProduct() throws Throwable {
		click(MOST_OFTEN_PURCHASED_PRODUCT,"Most often purchased product");
	}
}


