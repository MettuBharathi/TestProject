package com.insight.Lib;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.EndUserFeaturesObj;
import com.insight.ObjRepo.productsDisplayInfoObj;

public class ProductDisplayInfoLib extends productsDisplayInfoObj {

	SearchLib searchLib = new SearchLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	OrderLib orderLib = new OrderLib();

	/**
	 * This method is to fill the Product Research Request details and submit it
	 * and verify the success message.
	 * 
	 * @param name
	 * @param email
	 * @param country
	 * @param quantity
	 * @param partNo
	 * @param mnfr
	 * @param prodDesc
	 * @throws Throwable
	 */
	public void clickProductResearchRequestAndFillDetails(String name, String email, String country, String quantity,
			String partNo, String mnfr, String prodDesc) throws Throwable {
		click(productsDisplayInfoObj.PRODUCT_RESEARCH, "Product research request link");
		waitForVisibilityOfElement(productsDisplayInfoObj.PROD_RESEARCH_NAME_TXT_BOX, "Name");
		type(productsDisplayInfoObj.PROD_RESEARCH_NAME_TXT_BOX, name, "Your name text box");
		type(productsDisplayInfoObj.PROD_RESEARCH_EMAIL_TXT_BOX, email, "email text box");
		type(productsDisplayInfoObj.PROD_RESEARCH_COUNTRY_TXT_BOX, country, "Country text box");
		type(productsDisplayInfoObj.PROD_RESEARCH_QUANTITY_TXT_BOX, quantity, "Quantity text box");
		type(productsDisplayInfoObj.PROD_RESEARCH_PARTNO_TXT_BOX, partNo, "Part number text box");
		type(productsDisplayInfoObj.PROD_RESEARCH_MANFR_TXT_BOX, mnfr, "Manufacturer text box");
		type(productsDisplayInfoObj.PROD_RESEARCH_PROD_DESC_TXT_BOX, prodDesc, "Product description text box");
		click(productsDisplayInfoObj.PRODUCT_REQ_SEND_BTN, "Product Research request screen send button");

		if (isElementPresent(productsDisplayInfoObj.PROD_REQ_SENT_MSG, "Success message")) {

			reporter.SuccessReport("Verify the success message", "Product Research Request sent successfully", "");
		} else {
			reporter.failureReport("Verify the success message", "Product Research Request not sent.", "");
		}
		click(productsDisplayInfoObj.CLOSE, "Close button");
	}

	/**
	 * This method is to verify the product search results displayed correctly
	 * or not.
	 * 
	 * @throws Throwable
	 */
	public void verifyTheSearchResultsDisplayed() throws Throwable {

		List<WebElement> myList = driver.findElements(LIST_OF_ITEMS_SEARCH_RESULTS);
		for (int i = 0; i < myList.size(); i++) {
			String productName = getText(productsDisplayInfoObj.getproductName(i), "Get product name");
			System.out.println(productName);
			if (!productName.isEmpty()) {
				reporter.SuccessReport("Verify the product name", "Product name is displayed as: ", productName);
			} else {
				reporter.failureReport("Verify the product name", "Product name is not displayed", "");
			}

			String productImage = getAttributeBySrc(productsDisplayInfoObj.getproductImage(i), "Getting Src");
			System.out.println(productImage);
			if (productImage.isEmpty() || productImage.contains("noImageAvailable")) {

				reporter.failureReport("Verify the product Image", "Product image is not displayed", "",driver);
			} else {
				reporter.SuccessReport("Verify the product Image", "Product image is displayed", "");
			}

			String features = getText(productsDisplayInfoObj.getProductFeatures(i), "get product features");
			System.out.println(features);
			if (!features.isEmpty()) {
				reporter.SuccessReport("Verify the product features", "Product features are displayed as : ", features);
			} else {
				reporter.failureReport("Verify the product features", "Product features are not displayed", "",driver);
			}

			String price = getText(productsDisplayInfoObj.getProductPrice(i), "get product price");
			System.out.println(price);
			if (!price.isEmpty()) {
				reporter.SuccessReport("Verify the product price", "Product price is displayed as: ", price);
			} else {
				reporter.failureReport("Verify the product price", "Product price is not displayed", "",driver);
			}

			String partNumber = getText(productsDisplayInfoObj.getPartNumber(i), "get product number");
			System.out.println(partNumber);
			if (!partNumber.isEmpty()) {
				reporter.SuccessReport("Verify the product part Number", "Product part Number is displayed as : ",
						partNumber);
			} else {
				reporter.failureReport("Verify the product part Number", "Product part Number is not displayed", "",driver);
			}
		}
	}

	/**
	 * Method is to click on the Product research request link in the Product
	 * search results page.
	 * 
	 * @throws Throwable
	 */
	public void clickProductResearchRequest() throws Throwable {
		click(productsDisplayInfoObj.PRODUCT_RESEARCH, "Product research request link");
	}

	/**
	 * 
	 * @param productName
	 * @throws Throwable
	 */
	public void clickSendWithoutFillingRequestProductAndVerify(String productName) throws Throwable {
		click(productsDisplayInfoObj.PRODUCT_REQ_SEND_BTN, "Product Research request screen send button");
		isElementPresent(productsDisplayInfoObj.ERROR_MSG, "Error message in Product Research Request acreen");
		click(productsDisplayInfoObj.PRODUCT_REQ_CANCEL_BTN, "Product Research request screen CANCEL button");
		searchLib.verifyTheResultsForSearchTerm(productName);
	}

	/**
	 * Method is to click on Compare similar link in the products page.
	 * 
	 * @throws Throwable
	 */
	public void clickOnCompareSimilarLink() throws Throwable {
		clickOnly(productsDisplayInfoObj.COMPARE_SIMILAR, "Compare similar link");
	}

	/**
	 * Method is to click on Add to Cart button
	 * 
	 * @throws Throwable
	 */
	public void addToCart() throws Throwable {
		click(productsDisplayInfoObj.ADD_TO_CART, "Add to cart");
		if (isElementPresent(productsDisplayInfoObj.ADDED_TO_CART_LABEL, "Add to cart screen")) {
			reporter.SuccessReport("Verify the product added  to cart ", "Product added to cart sucessfully", "");
		} else {
			reporter.failureReport("Verify the product added  to cart ", "Product not added to cart", "");
		}
	}
	
	/**
	 * Method is to click on Add to cart and verify
	 * @throws Throwable
	 */
	public void addToCartForFrench() throws Throwable {
		click(productsDisplayInfoObj.ADD_TO_CART, "Add to cart");
		if (isElementPresent(productsDisplayInfoObj.ADD_TO_CART_FRENCH_MSG, "Add to cart screen")) {
			reporter.SuccessReport("Verify the product added  to cart ", "Product added to cart sucessfully", "");
		} else {
			reporter.failureReport("Verify the product added  to cart ", "Product not added to cart", "");
		}
	}
	

	/**
	 * Method is to enter the price details in the filter and verify.
	 * 
	 * @param minPrice
	 * @param maxPrice
	 * @throws Throwable
	 */
	public void enterPriceDetailsFilters(String minPrice, String maxPrice) throws Throwable {

		String priceFilter = null;
		String result = null;
		boolean flag = true;
		type(productsDisplayInfoObj.MIN_PRICE, minPrice, "Minimum price");
		type(productsDisplayInfoObj.MAX_PRICE, maxPrice, "Maximum price");
		click(productsDisplayInfoObj.PRICE_SUBMIT, "filter price GO button");
		if (flag) {
			// adding the filter elements to list
			List<WebElement> myList = driver.findElements(productsDisplayInfoObj.FILTER_ITEM);
			List<String> all_elements_text = new ArrayList<>();
			for (int i = 0; i < myList.size(); i++) {
				all_elements_text.add(myList.get(i).getText());
				result = myList.get(i).getText().replace(",", "");

				Thread.sleep(2000);
				priceFilter = "$" + minPrice.replace(",", "").replace(".00", "") + "-$" + maxPrice;
				if (result.replace(".00", "").contains(priceFilter)) {
					Thread.sleep(2000);
					reporter.SuccessReport("Verify the results for search term in products display page ",
							"Verification is sucessfull. Expected filter is:", result);
				}
			}
		} else {
			reporter.failureReport("Verify the results for search term in products display page ",
					"Verification is not sucessfull. Actual filter is:" + result + " Expected is: " + priceFilter, "");
		}
	}

	/**
	 * This method is to click on pagination numbers.
	 * 
	 * @throws Throwable
	 */
	public void pagination() throws Throwable {
		// scrollToWebElement(productsDisplayInfoObj.PAGINATON);
		waitForVisibilityOfElement(productsDisplayInfoObj.PAGINATON, "PAGINATON", driver);
		click(productsDisplayInfoObj.PAGINATON, "PAGINATON");
	}

	/**
	 * Method is to click on the first product and navigate to the product
	 * details page and return back.
	 * 
	 * @throws Throwable
	 */
	public void selectFirstProductAndReturnBack() throws Throwable {

		Thread.sleep(3000);
		click(productsDisplayInfoObj.FIRST_PROD_NAME, "First product in search results page");
		isElementPresent(productsDisplayInfoObj.BACK_TO_RESULTS, "Back to results", true);
		click(productsDisplayInfoObj.BACK_TO_RESULTS, "Back to results link");
	}

	/**
	 * Method is to click on the first image and navigate to the product details
	 * page and return back.
	 * 
	 * @throws Throwable
	 */
	public void selectFirstProductImageAndReturnBack() throws Throwable {
		click(productsDisplayInfoObj.FIRST_PROD_IMAGE, "First name in product search page");
		click(productsDisplayInfoObj.BACK_TO_RESULTS, "Back to results link");
	}

	/**
	 * This method is to select the sort by options in the product search
	 * results page.
	 * 
	 * @param sortOption
	 * @throws Throwable
	 */
	public void selectSortByOptions(String sortOption) throws Throwable {

		String strArray[] = sortOption.split(",");
		for (i = 0; i <= strArray.length; i++) {
			click(productsDisplayInfoObj.SORT_DD, "Sort By drop down");
			click(productsDisplayInfoObj.getSortByOptions(strArray[i]), "Select sort option");
			waitForVisibilityOfElement(productsDisplayInfoObj.SORT_DD, "Sort option");
			Thread.sleep(1000);
			String actualSort = getText(productsDisplayInfoObj.SORT_DD, " Actual sort");
			if (actualSort.equals(strArray[i])) {
				reporter.SuccessReport("Verify the results for sort By filter option ",
						"Verification is sucessfull. Expected filter is:", strArray[i]);
			} else {
				reporter.failureReport("Verify the results for sort By filter option ",
						"Verification is not sucessfull. Actual filter is:" + actualSort + " .Expected is :",
						strArray[i]);
			}
		}
	}

	/**
	 * This method is to select the first product in the search results and
	 * verify the presence of Personal product list link and click on it.
	 * 
	 * @throws Throwable
	 */
	public void selectProductAndverifyPersonalProductListLinkPresent() throws Throwable {

		click(productsDisplayInfoObj.FIRST_PROD_NAME, "First product in search results page");
		isElementPresent(productsDisplayInfoObj.BACK_TO_RESULTS, "Back to results");
		if (isElementNotPresent(ADD_TO_PERSONAL_PROD_LINK, "Add to personal product link")) {
			reporter.SuccessReport("Verify personal product link present ",
					"Personal product is not present. Enable the settings and Login As", "");
			LOG.info("Enable the settings in CMT and Login As to get the Add to Personal product list link");
		} else {
			if (isElementPresent(ADD_TO_PERSONAL_PROD_LINK, "Add to personal product link")) {
				click(ADD_TO_PERSONAL_PROD_LINK, "Add to personal product link");
			}
		}
	}

	/**
	 * Method is to verify the products added to the personal product list.
	 * 
	 * @param url
	 * @throws Throwable
	 */
	public void verifyPersonalProductListPage(String url) throws Throwable {

		String productName = getText(PRODUCT_NAME, "Product  name - product details page");
		click(ADDED_TO_PERSONAL_PROD_LIST, "Added to personal product link");
		verify_url(WebDriver, url);
		String actualName = getText(PROD_NAME_PERSONAL_PROD_LIST, "product name n personal product list");
		if (productName.equals(actualName)) {
			reporter.SuccessReport("Verify personal product page product name ",
					"Product is added to the personal product list", "");
		} else {
			reporter.failureReport("Verify personal product page product name ",
					"Product is not added to the personal product list", "");
		}
	}

	/**
	 * This method is to add part item to the Personal Product List and verify
	 * the products added.
	 * 
	 * @param partNo
	 * @throws Throwable
	 */
	public void addItemsToProductList(String partNo) throws Throwable {
		String result = null;
		boolean flag = true;
		click(ADDED_TO_PERSONAL_PROD_LIST, "ADDED TO PERSONAL PRODUCT LIST");
		type(ADD_ITEMS_TEXTBOX, partNo, "part number");
		click(ADD_BTN, "Add button");

		if (flag) {
			List<WebElement> myList = driver.findElements(MFR_PART);
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
	 * This method is to add the personal product list to the cart and verify
	 * it. Later deletes the personal product list.
	 * 
	 * @param partNumber
	 * @throws Throwable
	 */
	public void addToCartAndVerify(String partNumber) throws Throwable {

		click(getAddToCartBtn(partNumber), "Add cart button");
		waitForVisibilityOfElement(ADDED_TO_CART_PPC_PART_NO, "Part number");
		String partNo = getText(ADDED_TO_CART_PPC_PART_NO, "part number in the personal product list cart");
		if (partNo.contains(partNumber)) {
			reporter.SuccessReport("Verify the part added to cart ", "Part sucessfully added to cart. number is : ",
					partNo);
		} else {
			reporter.failureReport("Verify the part added to cart ", "Part is not added to cart.", "");
		}
		click(CONTINUE_SHOPPING, "Continue shopping");
		click(DELETE_BTN, "Delete button");
		Thread.sleep(1000);
		click(DELETE_BTN, "Delete button");
		isElementPresent(PART_DELETED_MSG, "Part deleted from your list", true);
		isVisible(LIST_EMPTY_MSG, "Your Personal Product List is currently empty.");
	}

	/**
	 * This method is to verify the contract name in the product display page
	 * and add product to cart.
	 * 
	 * @param contractName
	 * @param quantity
	 * @throws Throwable
	 */
	public void verifyContractNameInProdDetailsPageAndAddToCart(String contractName, String quantity) throws Throwable {
		String expectedContractName = getText(SELECTED_CONTRACT, "Selected contract").replace("Contract – ", "")
				.replace("...", "");
		click(productsDisplayInfoObj.FIRST_PROD_NAME, "First product in search results page");
		isElementPresent(PROD_DETAILS_PAGE_CONTRACT_NAME, "PROD DETAILS PAGE CONTRACT NAME");
		String actualContractName = getText(PROD_DETAILS_PAGE_CONTRACT_NAME, "PRODUCT DETAILS PAGE CONTRACT NAME");
		if (actualContractName.contains(expectedContractName)) {
			reporter.SuccessReport("Verify the contract name", " Contract name verified successfully ", "");
		} else {
			reporter.failureReport("Verify the contract name", " Contract name not displayed correctly", "");
		}
		typeOnly(QUANTITY, quantity, "quantity");
		commonLib.addToCartAndVerify();
		click(CartObj.CONTINUE_TO_SHOPPING, "continue shoping");
	}

	/**
	 * 
	 * @param contractName
	 * @throws Throwable
	 */
	public void verifyContractInCartScreen(String contractName) throws Throwable {
		String actualcontractName = getText(CART_CONTRACT_NAME, "contract name");
		if (contractName.contains(actualcontractName)) {
			reporter.SuccessReport("Verify the contract name", " Contract name verified successfully ", "");
		} else {
			reporter.failureReport("Verify the contract name", " Contract name not displayed correctly", "");
		}
	}

	/**
	 * Method is to verify YOUR PRICE in product search results
	 * @throws Throwable
	 */
	public void verifyYourPriceExists() throws Throwable {
		if (isElementPresent(YOUR_PRICE_ON_PRODUCTS_LIST, "Your price in the product list page")) {
			String yourPrice = getText(YOUR_PRICE_ON_PRODUCTS_LIST, "Your price ");
			reporter.SuccessReport("Verify your price displayed", " Your price is displayed as:  ", yourPrice);
		} else {
			reporter.failureReport("Verify your price displayed", " Your price is not displayed", "",driver);
		}
	}

	/**
	 * This method is to verify the mfr number displayed in the product details
	 * page and cart are same
	 * 
	 * @throws Throwable
	 */
	public void selectFirstProductAddToCartAndVerifyCart() throws Throwable {

		cartLib.selectFirstProductDisplay();
		waitForVisibilityOfElement(MFR_NUMBER_PRODUCT_DETAILS_PAGE, "Mfr number");
		String prodMfrNumber = getText(MFR_NUMBER_PRODUCT_DETAILS_PAGE, "MFR_NUMBER_PRODUCT_DETAILS_PAGE")
				.replace("\"", "").replace("Mfr. #", "").trim();
		commonLib.addToCartAndVerify();
		orderLib.continueToCheckOutOnAddCart();
		cartLib.verifyItemInCart(prodMfrNumber);
	}
	
	
	
	/**
	 * Method is to verify the stock number for the displayed search results
	 * @throws Throwable
	 */
	public void verifyProductStockNumberInSearchResultsPage() throws Throwable{
		
		Thread.sleep(2000);
		List<WebElement> myList = driver.findElements(STOCK_IN_SEARCH_RESULTS);
		for(i=0;i<myList.size();i++){
			String stockNumber=getText(getProductStockNumber(i), "Stock Number");
			if(stockNumber.isEmpty() || stockNumber==null){
				reporter.failureReport("Verify the stock number for products displayed", "Stok number is empty or null for "+i+ " product","",driver);
			}else{
				reporter.SuccessReport("Verify the stock number for products displayed", "Stock number is displayed for " +i+ " Product as :"+stockNumber, "");
			}
		}
	}
	
	/**
	 * Method is to get the stock number of first product in search results page.
	 * @return
	 * @throws Throwable
	 */
	public String getStockNumebrOfFirstProductInSearchResults() throws Throwable{
		return getText(STOCK_NUMBER_OF_FIRST_PROD, "Stock number");
	}
	
	/**
	 * Method is to verify the stock number in first product in search results page and product details page
	 * @param stockNumber
	 * @throws Throwable
	 */
	public void verifyStockNumberInProductDetailsPage(String stockNumber) throws Throwable{
		Thread.sleep(2000);
		String actualStockNumber=getText(STOCK_NUMBER_IN_PROD_DETAIL, "stock number").replace("Stock", "").trim();
		
		if(actualStockNumber.equals(stockNumber)){
			reporter.SuccessReport("Verify the stock number in the product details page", "Stock Availability Exists and Same as in Product List Page", "");
		}else{
			reporter.failureReport("Verify the stock number in the product details page", "Stock Availability verification is not successful", "",driver);
		}
	}
	
	/**
	 * This method is to verify the stock availability in the company standards screen.
	 * @throws Throwable 
	 * 
	 */
	public void verifyStockNumberInCompanyStandardsProductGroup() throws Throwable{
		if(isVisible(STOCK_AVAILABILITY_IN_COMPANY_STANDARDS, "stock availability link")){
			List<WebElement> myList=driver.findElements(ADD_ITEMS_CHECKBOX);
			List<WebElement> stockLinks=driver.findElements(STOCK_AVAILABILITY_IN_COMPANY_STANDARDS);
			for (int j = 0; j < myList.size(); j++) {
				String text=stockLinks.get(j).getText();
				if(text.isEmpty() || text == null){
					reporter.failureReport("verify stock link in company standards", "Stock link is not present","",driver );
					
				}else{
					reporter.SuccessReport("verify stock link in company standards", "Stock link is present "+text, "");
				}
			}
		}
	}
	
	/**
	 * Method is to Verify Inventory Blowout in Technology deals page
	 * @throws Throwable
	 */
	public void verifyInventoryBlowOutInTechnologyDealsPage() throws Throwable {
		List<WebElement> myList = driver.findElements(FEATURED_TECH_DEALS_PRODUCTS);
		List<WebElement> inventory_Blowout = driver.findElements(INVENTORY_BLOWOUT_LABEL);
		for (i = 0; i < myList.size(); i++) {
			if (inventory_Blowout.get(i).isDisplayed()) {
				reporter.SuccessReport("verify inventory blow out label in Featured tech deals",
						"inventory blow out label is present for "+i+1+ " product", "");
			} else {
				reporter.failureReport("verify inventory blow out label in Featured tech deals",
						"inventory blow out label is not present", "");
			}
		}
	}
	
	/**
	 * This method is to click on the see details button of the product in Technology deals page and verify Inventory BlowOut label
	 * @throws Throwable
	 */
	public void clickSeeDetailsVerifyInventoryBlowOutOfProductDetails() throws Throwable{
		click(PRODUCT_SEE_DETAILS_BTN, "see details button");
		if(isVisible(INVENTORY_BLOWOUT_LABEL,"Inventory blow out")){
			reporter.SuccessReport("verify inventory blow out label in Featured tech deals",
					"inventory blow out label is present in product details page", "");
		}else{
			reporter.failureReport("verify inventory blow out label in Featured tech deals",
					"inventory blow out label is not present in product details page", "",driver);
		}
	}
	
	/**
	 * This method is to verify whether correct manufacturer number displayed on the product details page.
	 * @param mnfNumber
	 * @throws Throwable
	 */
	public void verifyTheManufacturerNumberInProductDetailsPage(String mnfNumber) throws Throwable{
		String prodMfrNumber = getText(MFR_NUMBER_PRODUCT_DETAILS_PAGE, "MFR_NUMBER_PRODUCT_DETAILS_PAGE")
				.replace("\"", "").replace("Mfr. #", "").trim();
		if(mnfNumber.equals(prodMfrNumber)){
			reporter.SuccessReport("Verify manufacturer number in product details page", "Manufacturer number displayed correctly", "");
		}else{
			reporter.failureReport("Verify manufacturer number in product details page", "Manufacturer number is not displayed correctly", "",driver);
		}
	}
	
	/**
	 * Method is to verify the manufacturer number in the overview tab of product details page.
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void verifyManufacturerNumberInOverviewTab(String mfrNumber) throws Throwable{
		String actualmfrnum=getText(MNR_NUM_OVERVIEW, "overview tab mfr number").trim();
		if(mfrNumber.equals(actualmfrnum)){
			reporter.SuccessReport("Verify manufacturer number in product details page overview tab", "Manufacturer number displayed correctly", "");
		}else{
			reporter.failureReport("Verify manufacturer number in product details page overview tab", "Manufacturer number is not displayed correctly", "",driver);
		}
	}
	
	/**
	 * Method is to verify the part number and description in the company standards screen.
	 * @throws Throwable
	 */
	public void verifyProductDescAndPartNumberInCompanyStandards() throws Throwable{
		List<WebElement> desc=driver.findElements(By.xpath("//table[@id='prodGroupTable']//td//div//a"));
		List<WebElement> partNum=driver.findElements(By.xpath("//table[@id='prodGroupTable']//td//div"));
		for (i = 0; i < desc.size(); i++) {
			if(desc.get(i).getText().isEmpty() || partNum.get(i).getText().isEmpty()){
				reporter.failureReport("Verify part number and description in company standards product group", "Part number or discription is empty", "",driver);
			}else{
				reporter.SuccessReport("Verify part number and description in company standards product group", "Part number or discription is displayed for "+i+" product", "");
			}
		}
	}
	
	/**
	 * Method is to verify the images present in the search results page 
	 * @throws Throwable
	 */
	public String verifyProductImage() throws Throwable{
		String actualImgSrc=null;
		List<WebElement> myList = driver.findElements(LIST_OF_ITEMS_SEARCH_RESULTS);
		for (int i = 0; i < myList.size();i++) {
			Thread.sleep(2000);
			String productImage = getAttributeBySrc(productsDisplayInfoObj.getproductImage(i), "Getting Src");
			if (!productImage.isEmpty() || !productImage.contains("noImageAvailable")) {
				actualImgSrc=productImage;
				reporter.SuccessReport("Verify image in search results page", "Image displayed in search results page", "");
				break;
			}
		  }
		return actualImgSrc;
		}
	
	/**
	 * This method is to verify the image present in the product details page.s
	 * @throws Throwable
	 */
	public void verifyFrontImageInProductDetailsPage() throws Throwable{
		String prodImage=getAttributeBySrc(IMG_PRODUCT_DETAILS_FRONT, "getting image src");
		if (prodImage.isEmpty() || prodImage.contains("noImageAvailable")) {

			reporter.failureReport("Verify the product Image", "Product image is not displayed", "",driver);
		} else {
			reporter.SuccessReport("Verify the product Image", "Product image is displayed", "");
		}
	}
	
	/**
	 * Method is to click on Back to results in the product details page
	 * @throws Throwable
	 */
	public void backToResultsProductDetailsPage() throws Throwable{
		click(productsDisplayInfoObj.BACK_TO_RESULTS, "Back to results link");
	  }
	
	
	/**
	 * This method is to verify the image present in the product details page.s
	 * @throws Throwable
	 */
	public void verifyLeftAngleImageInProductDetailsPage() throws Throwable{
		String prodImage=getAttributeBySrc(IMG_LEFT_ANGLE, "getting image src");
		if (prodImage.isEmpty() || prodImage.contains("noImageAvailable")) {

			reporter.failureReport("Verify the product Image", "Product image is not displayed", "",driver);
		} else {
			reporter.SuccessReport("Verify the product Image", "Product image is displayed", "");
		}
	  }
	
	/**
	 * This method is to verify the recently viewed products 
	 * @param imgSrc
	 * @throws Throwable
	 */
	public void verifyRecentlyViewedProductsImage(String imgSrc) throws Throwable{
		waitForVisibilityOfElement(getRecentlyViewedProductImage(imgSrc), "img src");
		if(isElementPresent(getRecentlyViewedProductImage(imgSrc), "image src")){
			reporter.SuccessReport("Verify the product Image in recently viewed products", "Product image is displayed in recently viewed products", "");
		}else{
			reporter.failureReport("Verify the product Image in recently viewed products", "Product image is not displayed in recently viewed products", "",driver);
		}
	  }
	
	/**
	 * This method is to verify the List prices available in the Search results page
	 * @throws Throwable
	 */
	public void verifyTheProductPricesInSearchResultsPage() throws Throwable{
		List<WebElement> myList = driver.findElements(LIST_OF_ITEMS_SEARCH_RESULTS);
		for (int i = 0; i < myList.size(); i++) {
		String price = getText(productsDisplayInfoObj.getProductPrice(i), "get product price");
		System.out.println(price);
		if (!price.isEmpty()) {
			reporter.SuccessReport("Verify the product price", "Product price is displayed as: ", price);
		} else {
			reporter.failureReport("Verify the product price", "Product price is not displayed", "",driver);
		}
	}
}
	/**
	 * Method is to get the price of the first product in the search results page
	 * @return
	 * @throws Throwable
	 */
	public String getFirtProductListPrice() throws Throwable{
		 return getText(FIRST_PRODUCT_PRICE, "List price");
	  }
	 
	/**
	 * Method is to verify the list price in product details page
	 * @param Actualprice
	 * @throws Throwable
	 */
	public void verifyThePriceInProdDetailsPage(String Actualprice) throws Throwable{
	  String price=getText(PRICE_IN_PROD_DETAILS, "Product detail price");
	  if(Actualprice.equals(price)){
		  reporter.SuccessReport("Verify the product price", "Product price is displayed as: ", Actualprice);
	  }else{
		  reporter.failureReport("Verify the product price", "Product price is not displayed", "",driver);
	  }
	}
	
	/**
	 * Method is to get the first product quantity in the search results page
	 * @return
	 * @throws Throwable
	 */
	public String getFirstProdQuantity() throws Throwable{
		return getAttributeByValue(QUANTITY_FIRST_PROD, "First prod quantity");
	}
	
	/**
	 * method is to verify the quantity in product details page
	 * @param actaulQty
	 * @throws Throwable
	 */
	public void verifyQuantityInProdDetailsPage(String actaulQty) throws Throwable{
		String quantity=getAttributeByValue(QUANTITY, "quantity");
		 if(quantity.equals(actaulQty)){
			  reporter.SuccessReport("Verify the product quantity", "Product quantity is displayed as: ", actaulQty);
		  }else{
			  reporter.failureReport("Verify the product quantity", "Product quantity is not displayed correctly", "",driver);
		  }
	}
	
	/**
	 * Method is to click on Add to cart button in Product details page
	 * @throws Throwable
	 */
	public void addToCartInProductDetailsPage() throws Throwable{
		click(CartObj.ADD_TO_CART_IN_PRODUCT_DISPLAY," ADD TO CART IN PRODUCT DISPLAY");
	}
	
	/**
	 * Method to verify Your price in product details page
	 * @throws Throwable
	 */
	public void verifyYourPriceInProductDetailsPage() throws Throwable{
		String yourPrice=getText(productsDisplayInfoObj.YOUR_PRICE_ON_PROD_DETAILS, "Your price");
		if(isElementPresent(productsDisplayInfoObj.YOUR_PRICE_ON_PROD_DETAILS, "Your price")){
			reporter.SuccessReport("Verify the product your price in product details", "Your price is displayed as: ", yourPrice);
		  }else{
			  reporter.failureReport("Verify the product your price in product details", "Your price is not displayed correctly", "",driver);
		  }
		}
	
	/**
	 * Method is to click on warranties tab in product details page
	 * @throws Throwable
	 */
	public void clickOnWarrantiesTabOnProductDetailsPage() throws Throwable{
		click(WARRANTIES_PROD_DETAILS, "warranties");
	}
	
	/**
	 * Method is to check the price in the warranties tab.
	 * @throws Throwable
	 */
	public void verifyPriceInWarrantiesTab() throws Throwable{
		if(isElementPresent(PRICE_IN_WARRANTIES_PROD_DETAILS, "price in warranties tab")){
			reporter.SuccessReport("Verify price in warranties tab", "Price displayed in warranties tab","");
		  }else{
			  reporter.failureReport("Verify price in warranties tab", "Price is not displayed in warranties tab", "",driver);
		  }
		}
	
	/**
	 * Method is to verify Open market PRICE in product search results
	 * @throws Throwable
	 */
	public void verifyOpenMarketPriceExists() throws Throwable {
		if (isElementPresent(OPEN_MARKET_PRICE_PRODUCT_LIST, "Open market price in the product list page")) {
			String openMarketPrice = getText(OPEN_MARKET_PRICE_PRODUCT_LIST, "Open market price ");
			reporter.SuccessReport("Verify your price displayed", " Open market price is displayed as:  "+openMarketPrice,"" );
		} else {
			reporter.failureReport("Verify your price displayed", " Open market price is displayed", "",driver);
		}
	}
	
	/**
	 * Method to verify Open market price in product details page
	 * @throws Throwable
	 */
	public void verifyOpenMarketPriceInProductDetailsPage() throws Throwable{
		String openaMarketPrice=getText(productsDisplayInfoObj.OPEN_MARKET_PRICE_ON_PROD_DETAILS, "Open market price");
		if(isElementPresent(productsDisplayInfoObj.OPEN_MARKET_PRICE_ON_PROD_DETAILS, "Open market price")){
			reporter.SuccessReport("Verify the product Open market price in product details", "Open market price is displayed as: "+ openaMarketPrice,"");
		  }else{
			  reporter.failureReport("Verify the product Open market price in product details", "Open market price is not displayed correctly", "",driver);
		  }
		}
	/**
	 * Method is to test the US contract present in the search results page.
	 * @throws Throwable
	 */
	public void verifyUSDContractPricePresentInSearchResults() throws Throwable{
			String contractLabel=getText(productsDisplayInfoObj.DEFAULT_CONTRACT_LABEL_PROD_LIST, "DEFAULT CONTRACT LABEL PRODUCT LIST");
			if(isElementPresent(productsDisplayInfoObj.DEFAULT_USC_LABEL_PROD_LIST, "USC label") ){
				
				reporter.SuccessReport("Verify the default contracts displayed in product search page first product ","USC contract price is displayed by default for the product.Displayed contract is: "+contractLabel,"");
			}else{
				reporter.failureReport("Verify the default contracts displayed in product search page first product ","USC is displayed by default"+contractLabel,"",driver);
			}
		}
	
	/**
	 * Method is to check the mfr part in the warranties tab.
	 * @throws Throwable
	 */
	public void verifyMfrpartInWarrantiesTab() throws Throwable{
		if(isElementPresent(MFR_PART_WARRANTIES_TAB, "price in warranties tab")){
			reporter.SuccessReport("Verify Mfr part in warranties tab", "Mfr part displayed in warranties tab","");
		  }else{
			  reporter.failureReport("Verify Mfr part in warranties tab", "Mfr part is not displayed in warranties tab", "",driver);
		  }
		}
	


	/**
	 * Method is to click on warranties tab in product details page
	 * @throws Throwable
	 */
	public void clickOnAccessoriesTabInProductDetailsPage() throws Throwable{
		click(ACCESSORIES_PROD_DETAILS, "Accessories");
	}
	
	/**
	 * Method is to verify the accessories mfr part in the product details page.
	 * @throws Throwable
	 */
	public void verifyMfrpartInAccessorirs() throws Throwable{
		if(isElementPresent(MFR_NUM_ACCESSORIES, "Part in accessories tab")){
			reporter.SuccessReport("Verify Mfr part in Accessories tab", "Mfr part displayed in Accessories tab","");
		  }else{
			  reporter.failureReport("Verify Mfr part in Accessories tab", "Mfr part is not displayed in Accessories tab", "",driver);
		  }
	}
	
	/**
	 * This method is to verify the short description in the product details page matching with the search results page
	 * @throws Throwable
	 */
	
	public void verifyShortDescriptionOnProductDetailsPage(String actualDesc) throws Throwable{
		waitForVisibilityOfElement(PRODUCT_NAME, "Product name");
		String productName = getText(PRODUCT_NAME, "Product  name - product details page");
		if(isElementPresent(PRODUCT_NAME, "Product displayed") && !productName.isEmpty() && productName.contains(actualDesc)){
			reporter.SuccessReport("Verify the Short description on Product Details Page", "Product Short description exists. Description is : "+productName, "");
		}else{
			reporter.failureReport("Verify the Short description on Product Details Page", "Product Short description does not exist or is empty", "",driver);
		}
	}
	
	/**
	 * Method is to get the first product Description in the search results page
	 * @return
	 * @throws Throwable
	 */
	public String getFirstProdDescription() throws Throwable{
		waitForVisibilityOfElement(FIRST_PROD_NAME, "First product description");
		return getText(FIRST_PROD_NAME, "First prod Description");
	}
	
	/**
	 * Method is to verify the Long description in product details page
	 * @throws Throwable
	 */
	public void verifyLongDescOnProductDetails() throws Throwable{
		String longDesc=getText(LONG_DESC_PROD_DETAILS, "Product long desc");
		if(isElementPresent(LONG_DESC_PROD_DETAILS, "Product long description") && !longDesc.isEmpty()){
			reporter.SuccessReport("Verify the Long description on Product Details Page", "Product Long description exists. Description is : "+longDesc, "");
		}else{
			reporter.failureReport("Verify the Long description on Product Details Page", "Product Long description does not exist or is empty", "",driver);
		}
	}
	
	/**
	 * Method is to verify the short description in the accessories tab.
	 * @throws Throwable 
	 * 
	 */
	public void verifyShortDescOnAccessoriesTab() throws Throwable{
		String shortDesc=getText(ACCESSORIES_DESC,"Accessories short description");
	if(isElementPresent(ACCESSORIES_DESC, "Accessories short description") && !shortDesc.isEmpty()){
		reporter.SuccessReport("Verify prod short description of Accessorries Tab on Product Details Page", "Short description of Accessorries Tab is present", "");
	}else{
		reporter.failureReport("Verify prod short description of Accessorries Tab on Product Details Page", "Short description of Accessorries Tab is not present", "",driver);
	  }
	}
	
	/**
	 * Method is to verify the long description on the Accessories tab
	 * @throws Throwable
	 */
	public void verifyLongDescriptionOnAccessoriesTab() throws Throwable{
		click(SEE_MORE_LINK, "See more link");
		String shortDesc=getText(LONG_DEC_ACCESSORIES,"Accessories Long description");
		if(isElementPresent(LONG_DEC_ACCESSORIES, "Accessories Long description") && !shortDesc.isEmpty()){
			reporter.SuccessReport("Verify prod Long description of Accessorries Tab on Product Details Page", "Long description of Accessorries Tab is present", "");
		}else{
			reporter.failureReport("Verify prod Long description of Accessorries Tab on Product Details Page", "Long description of Accessorries Tab is not present", "",driver);
		  }
	}
	
	/**
	 * Method is to verify the description of most often purchased item s in product details tab
	 * @throws Throwable
	 */
	public void verifyProductDescForMostOftenPurchasedItems() throws Throwable{
	    List<WebElement> elements=driver.findElements(MOST_OFTEN_PURCHASED_ITEM_DESC);
	     for(i=0;i<elements.size();i++){
	    	String Desc=elements.get(i).getText();
	    	if(Desc.isEmpty()||Desc==null){
	    		reporter.failureReport("Verify prod description of most often purchased items", "product description of most often purchased item is not present", "",driver);
	     }else{
	    	 reporter.SuccessReport("Verify prod description of most often purchased items", "product description of most often purchased item is present", "");
	     }
	}
  }
	/**
	 * Method is to select new contract
	 * @param contractName
	 * @throws Throwable
	 */
	public void selectNewcontract(String contractName) throws Throwable{
		clickUntil(CONTRACT_DD, getContractsFromDD(contractName), "contract drop down");
		click(getContractsFromDD(contractName),"Contract name");
	}
	
	/**
	 * Method is to get the product display info bread crumb
	 * @return
	 * @throws Throwable
	 */
	public String getProductDisplayInfoBreadCrumb() throws Throwable{
		return getText(PROD_DISPLAY_INFO_BREAD_CRUMB, "product display info bread crumb");
		
	}
	
	/**
	 * Method is to change the first product quantity
	 * @param qty
	 * @throws Throwable
	 */
	public void changeFirstProductQuantity(String qty) throws Throwable{
		
		if(isElementPresent(QUANTITY_FIRST_PROD, "First prod quantity")){
		  type(QUANTITY_FIRST_PROD,qty , "Quantity");	
		}else{
			reporter.failureReport("verify Quantity present", "Quantity input is not present", "", driver);
		}
	}
	
	/**
	 * 
	 * @param contractName
	 * @throws Throwable
	 */
	public void getSelectedContract(String contractName) throws Throwable{
		if(isElementPresent(EndUserFeaturesObj.getContractOnHomePage(contractName),"Contract on Home page")){
			reporter.SuccessReport("Verify selected contract ", "Selected contract verified successfully", contractName);
		}else{
			reporter.failureReport("Verify selected contract ", "Selected contract not verified.", contractName,driver);
		}
	}
	
	/**
	 * Method is to verify the manufacturer name in search results
	 * @param manufacturer
	 * @throws Throwable
	 */
    public void verifyManufacturerNameInsearchResults(String manufacturer) throws Throwable{
    	
    	List<WebElement> myList = driver.findElements(LIST_OF_ITEMS_SEARCH_RESULTS);
    	for (int i = 0; i < myList.size(); i++) {
			String productName = getText(productsDisplayInfoObj.getproductName(i), "Get product name");
			
			if (!productName.isEmpty() && productName.contains(manufacturer)) {
				reporter.SuccessReport("Verify the product name", "Product name is displayed as: ", productName);
			} else {
				reporter.failureReport("Verify the product name", "Product name is not displayed", "");
			}
	    }
     }
}
  
	

