package com.insight.ObjRepo;

import org.openqa.selenium.By;

public class productsDisplayInfoObj extends CommonObj{

	
    public static By getBreadCrumbs(String productCategory){
    	return By.xpath("//ul[@class='breadcrumbs js-fix-left-align js-insight-breadcrumbs']//li//span[contains(text(),'"+productCategory+"')]");
    }
    
    public static By getProductByType(String productType){
    	return By.xpath("//div[@class='result-item-wrapper text-center']//a[contains(text(),'"+productType+"')]");
    }

    public static By COMPARE_SIMILAR=By.xpath("//div[@class='columns medium-5']//a");
    public static By  NARROW_RESULTS_SEARCH=By.id("narrow-results-txt");
    
  //compare products
    public static By COMPARE_SIMILAR_PROD_HEADING=By.xpath("//div//table//tr[@class='compare-similar-heading']");
    public static By COMPARE_MASTER_PROD_NAME=By.xpath("//div[@class='table-overflow-container']//tr[4]//td[@class='compare-master-product']");
    public static By COMPARE_LIST_NUM=By.xpath("//a//span[@class='js-compare-list-count']");
    public static By COMPARE_YOUR_LIST_LINK=By.xpath("//a[@class='compare-list-button js-compare-list-btn show-for-medium-up']//span");
    public static By CLOSE_ICON=By.xpath("//a[@class='compare-remove-item js-remove-compare']");
   
  // Add to cart
    public static By ADD_TO_CART=By.xpath("//a[@class='small button expand js-add-to-cart']");
    public static By ADDED_TO_CART_LABEL=By.xpath("//div[@class='added-to-cart'][contains(text(),'Added to Your Cart')]");
    public static By ADD_TO_CART_FRENCH_MSG=By.xpath("//div[@class='added-to-cart'][contains(text(),'Ajouté à votre panier')]");
    
    public static By getAddToMyCompareListLink(String itemNo){
    	return By.xpath("//div[@id='search-item-"+itemNo+"']//div[@class='columns medium-7']/a");
    }
    
    public static By getFilterSelection(String filter){
    	return By.xpath("//span[@data-gtm-info='"+filter+"']");
    }
    
    // Price filters
    public static By MIN_PRICE=By.id("filter-price-min");
    public static By MAX_PRICE=By.id("filter-price-max");
    public static By PRICE_SUBMIT=By.id("filter-price-submit");
    public static By FILTER_ITEM=By.xpath("//div[@class='filter-item js-filter-item']//a");
    public static By PAGINATON=By.xpath("//div//ul[@class='pagination']//li[@class='nav-page__current-page']/following-sibling::li[1]//a");
    
    public static By getRemoveFilterName(String filter){
    	return By.xpath("//div[@class='filter-item js-filter-item']//a[contains(text(),'"+filter+"')]");
    }

 // Product Research Request
    public static By PRODUCT_RESEARCH=By.xpath("//a[@class='product-research-btn']");
    public static By PRODUCT_REQ_SEND_BTN=By.id("send-Prod-Request");
    public static By PRODUCT_REQ_CANCEL_BTN=By.id("cancel-prod-req");
    public static By ERROR_MSG=By.xpath("//div[@class='row alertError'][contains(text(),'Please enter  Name, Email, Part Number, Product Description.')]");
    public static By PROD_RESEARCH_NAME_TXT_BOX=By.id("prodResearchNameTextBox");
    public static By PROD_RESEARCH_EMAIL_TXT_BOX=By.id("prodResearchEmailTextBox");
    public static By PROD_RESEARCH_ADDEMAIL_TXT_BOX=By.id("productAddEmailTextBox");
    public static By PROD_RESEARCH_COUNTRY_TXT_BOX=By.id("prodResearchCountryTextBox");
    public static By PROD_RESEARCH_QUANTITY_TXT_BOX=By.id("prodResearchQuantityTextBox");
    public static By PROD_RESEARCH_PARTNO_TXT_BOX=By.id("prodResearchPartTextBox");
    public static By PROD_RESEARCH_MANFR_TXT_BOX=By.id("prodResearchMfrTextBox");
    public static By PROD_RESEARCH_PROD_DESC_TXT_BOX=By.id("prodResearchDescriptionTextBox");
    public static By PROD_REQ_SENT_MSG=By.xpath("//div[@id='productResearchRequestTemplate'][contains(text(),'Product Research Request sent')]");
    public static By FIRST_PROD_NAME=By.xpath("//div[@id='search-item-0']//div//a[@class='select-prod']");
    public static By FIRST_PROD_IMAGE=By.xpath("//div[@id='search-item-0']//a//img[@class='lazy']");
    public static By CLOSE=By.xpath("//a[@class='close-reveal-modal']");
    
    
    
    //sort By options
    public static By SORT_DD=By.id("buy-search-sort-drop-button");
    
    public static By getSortByOptions(String sortOption){
    	return By.xpath("//ul[@id='buy-search-sort-drop']//li//a[contains(text(),'"+sortOption+"')]");
    }

  //Product Details Page
    public static By BACK_TO_RESULTS=By.id("back-button");
    public static By PRODUCT_NAME=By.xpath("//div[@id='js-product-detail-target']//h1//a");
    public static By QUANTITY= By.xpath("//input[@id='product-detail-order-number-picker']");
    public static By ADD_TO_COMPANY_STANDARDS_LINK=By.xpath("//a[@class='prod-company-std' and contains(text(),'Add to Company Standards')]");
    
    public static By COMPANY_STANDARDS_POPUP=By.xpath("//div[@id='popUpDialogBody']//span//strong[contains(text(),'IUS Company Standards')]");
    public static By COMPANY_STANDARDS_CANCEL_BTN=By.xpath("//a[@id='company-standard-modal-cancel']");
    public static By COMPANY_STANDARDS_ADD_BTN=By.xpath("//a[@id='company-standard-modal-add']");
    public static By COMAPNY_STANDARDS_CHEKBOXES=By.xpath("//input[@type='checkbox']");
    public static By COMPANY_STANDARDS_ALREADY_ADDED_SET_MSG=By.xpath("//div[@id='duplicateMessage']");
    public static By COMPANY_STANDARDS_ADDED_SET_MSG=By.xpath("//div[@id='nonESD' and contains(.,'was successfully added to the set')]");
    
    public static By MFR_NUMBER_PRODUCT_DETAILS_PAGE=By.xpath("//div[@id='js-product-detail-pricing-target']//td[contains(.,'Mfr. #')]");
    public static By INSIGHT_PART_NUMBER_PROD_DETAILS=By.xpath("//div[@id='js-product-detail-pricing-target']//td[contains(.,' Insight # ')]");
    public static By STOCK_NUMBER_OF_FIRST_PROD=By.xpath("//div[@id='search-item-0']//p[@class='prod-stock']");
    public static By STOCK_IN_SEARCH_RESULTS=By.xpath("//p[@class='prod-stock'][contains(.,'in stock')]");
    public static By STOCK_NUMBER_IN_PROD_DETAIL=By.xpath("//p[@id='product-avalialability-by-warehouse']");
    public static By STOCK_AVAILABILITY_IN_COMPANY_STANDARDS=By.xpath("//a[contains(@href,'.insight.com/us/en/terms-conditions/stock-status.html')]");
    public static By MNR_NUM_OVERVIEW=By.xpath("//div[@id='tpl-specs-overview-target']//td[contains(.,'Mfr. #')]/following-sibling::td");
    public static By WARRANTIES_PROD_DETAILS=By.xpath("//section[@id='detail-warranties']//a[contains(text(),'Warranties')]");
    public static By PRICE_IN_WARRANTIES_PROD_DETAILS=By.xpath("//div[@id='tpl-specs-warranties-target']//div[@class='product-price']");
    public static By MFR_PART_WARRANTIES_TAB=By.xpath("//div[@id='tpl-specs-warranties-target']//div[@class='product-details'][contains(.,'Mfr Part #:')]");
    public static By ACCESSORIES_PROD_DETAILS=By.xpath("//section[@id='detail-accessories']//a[contains(text(),'accessories')]");
    public static By MFR_NUM_ACCESSORIES=By.xpath("//div[@class='content-inner']//div[@class='row'][1]//div[@class='product-details'][contains(.,'Mfr Part #:')]");
    public static By LONG_DESC_PROD_DETAILS=By.xpath("//div[@id='tpl-product-detail-order-target']/preceding-sibling::div[1]//p");
    
    // Accessories
    public static By ACCESSORIES_DESC=By.xpath("//div[@class='section-inner-accordion']//section[@class='active']//div[@class='row'][1]//div[@class='columns medium-8 large-5']//h4//a");
    public static By SEE_MORE_LINK=By.xpath("//div[@class='section-inner-accordion']//section[@class='active']//div[@class='row'][1]//a[@class='more-link']");
    public static By LONG_DEC_ACCESSORIES=By.xpath("//div[@class='row'][1]//a[@class='more-link hide']/following-sibling::p");
    
    // Most Often purchased items
    public static By MOST_OFTEN_PURCHASED_ITEM_DESC=By.xpath("//div[@id='pdp-recs-target-1']//section[@class='rr-tile slick-slide slick-active']//div[@class='rr-name-wrapper']");
    
    //product search results page
    public static By getproductImage(int itemno){
		return By.xpath("//div[@id='search-item-"+itemno+"']//a//img[@class='lazy']");
    }
    
    public static By getproductName(int itemno){
    	return By.xpath("//div[@id='search-item-"+itemno+"']//div//a[@class='select-prod']");
    }
    
    public static By getProductFeatures(int itemno){
    	return By.xpath("//div[@id='search-item-"+itemno+"']//div[@class='product-description']//ul");
    }
    
    public static By getProductPrice(int itemno){
    	return By.xpath("//div[@id='search-item-"+itemno+"']//p[@class='prod-price']");
    }
   
    public static By getPartNumber(int itemno){
    	return By.xpath("//div[@id='search-item-"+itemno+"']//p[@class='prod-part-number show-for-medium-up']");
    }
    
    public static By getProductStockNumber(int itemno){
    	return By.xpath("//div[@id='search-item-"+itemno+"']//p[@class='prod-stock'][contains(.,'in stock')]");
    }
    
    public static By LIST_OF_ITEMS_SEARCH_RESULTS=By.xpath("//div[@class='result-item-wrapper result-item-list']");
    public static By IMG_PRODUCT_DETAILS_FRONT=By.xpath("//div[@class='ccs-fancybox-gallery ccs-slick-slide ccs-slick-current ccs-slick-active']//img[@alt='Front']");
    public static By IMG_LEFT_ANGLE=By.xpath("//div[@class='ccs-cc-thumbnail-wrapper ccs-slick-slide ccs-slick-active']//img[@alt='Left-angle']");
    public static By FIRST_PRODUCT_PRICE=By.xpath("//div[@id='search-item-0']//p[@class='prod-price']");
    public static By PRICE_IN_PROD_DETAILS=By.xpath("//div[@id='js-product-detail-pricing-target']//p[@class='prod-price']");
    public static By QUANTITY_FIRST_PROD=By.xpath("//div[@id='search-item-0']//div[@class='number-picker']//input");
    public static By PROD_DISPLAY_INFO_BREAD_CRUMB=By.xpath("//a[@class='current']//span[@itemprop='name']");
    	
    
    // recently viewed products image
    public static By getRecentlyViewedProductImage(String src){
    	return By.xpath("//div[@class='js-product-recent-views-container recent-product-list clearfix']//img[@src='"+src+"']");
    }
    
    
   // Personal product list
    public static By ADD_TO_PERSONAL_PROD_LINK=By.xpath("//div//a[@class='add-to-personal-products']");
    public static By ADDED_TO_PERSONAL_PROD_LIST=By.xpath("//div//a[@class='added-to-personal-products']");
    public static By PROD_NAME_PERSONAL_PROD_LIST=By.xpath("//div[@class='o-grid__item']//a//h5[@class='c-item-card__details-description']");
    public static By ADD_ITEMS_TEXTBOX=By.xpath("//div//input[@id='add-favorite-item']");
    public static By ADD_BTN=By.xpath("//button[@class='c-button  c-button--primary c-favorites__header-parts__submit']//span[@class='c-button__content']");
    public static By MFR_PART=By.xpath("//div[@class='o-grid__item']//p[@class='c-item-card__details-text']");
    public static By ADDED_TO_CART_PPC_PART_NO=By.xpath("//ul[@class='c-item-card__modal-info__list']//li[1]");
    public static By CONTINUE_SHOPPING=By.xpath("//span[@class='c-button__text' and contains(text(),'Continue Shopping')]");
    public static By DELETE_BTN=By.xpath("//span[@class='c-button__text' and contains(text(),'Delete')]");
    public static By PART_DELETED_MSG=By.xpath("//span[@class='c-toast__header-title'][contains(text(),'Part deleted from your list.')]");
    public static By LIST_EMPTY_MSG=By.xpath("//div[@class='c-favorites__list--empty']");
    
    
    /* *****************************************************************************************************************************
	 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>LOCATOR IS TO GET THE ADD TO CART BUTTON USING THE MANUFACTURER NUMBER<<<<<<<<<<<<<<<<<<<<<<<<<
	 * *****************************************************************************************************************************
	 */

    public static By getAddToCartBtn(String mfr_Part){
    	return By.xpath("//div[@class='c-item-card__details o-grid__item u-1/1 u-1/2@tablet'][contains(.,'"+mfr_Part+"')]/following-sibling::div//span[contains(text(),'Add to cart')]");
    }
    
    // contract Prices available
    
    public static By MORE_PRICES=By.xpath("//div[@id='search-item-0']//p[@class='prod-more-prices']//a");
    public static By OPEN_MARKET=By.xpath("//div[@class='large-12 columns js-modal-pick-contract']//label[contains(.,'OPEN MARKET')]");
    public static By YOUR_PRICE=By.xpath("//div[@class='large-12 columns js-modal-pick-contract']//label[contains(.,'YOUR PRICE')]");
    public static By ALL_CONTRACT_PRICES=By.xpath("//div[@class='large-12 columns js-modal-pick-contract']//label");
    public static By CLOSE_CONTRACTS_POPUP =By.xpath("//a[@class='close-reveal-modal']");
    public static By DEFAULT_PRICE_OF_PRODUCT=By.xpath("//div[@id='search-item-0']//p[@class='prod-price']");
    public static By USD_PRICE=By.xpath("//div[@class='row js-more-prices-list'][1]//label[contains(.,'U.S. COMMUNITIES IT PRODUCTS & SERVICES # 4400006644')]");
    public static By PROD_DETAILS_PAGE_CONTRACT_NAME=By.xpath("//div[@id='js-product-detail-pricing-target']//p[@class='sewp-prices']");
    public static By DEFAULT_USC_LABEL_PROD_LIST=By.xpath("//div[@id='search-item-0']//p[@class='sewp-prices' and contains(.,'U.S. COMMUNITIE')]");
    public static By DEFAULT_CONTRACT_LABEL_PROD_LIST=By.xpath("//div[@id='search-item-0']//p[@class='sewp-prices']");
    public static By YOUR_PRICE_ON_PRODUCTS_LIST=By.xpath("//div[@id='search-item-0']//div/p[contains(text(),'Your price')]/following-sibling::p");
    public static By YOUR_PRICE_ON_PROD_DETAILS=By.xpath("//p[contains(text(),'Your price')]/following-sibling::p[@class='prod-price']");
    public static By OPEN_MARKET_PRICE_PRODUCT_LIST=By.xpath("//div[@id='search-item-0']//div/p[contains(text(),'Open Market Price')]/following-sibling::p");
    public static By OPEN_MARKET_PRICE_ON_PROD_DETAILS=By.xpath("//p[contains(text(),'Open Market Price')]/following-sibling::p[@class='prod-price']");
   
    //cart page Contract details
    public static By CART_CONTRACT_NAME=By.xpath("//div[@class='cart__table-block']//div[@class='columns']//strong");
    
    // Inventory Blow Out 
 	public static By FEATURED_TECH_DEALS_PRODUCTS=By.xpath("//div[@class='row product-summary-component product-summary-basic']//a[@class='select-prod']");
 	public static By INVENTORY_BLOWOUT_LABEL=By.xpath("//div//p[@class='ips-price-label'][contains(text(),'Inventory Blowout')]");
 	public static By PRODUCT_SEE_DETAILS_BTN=By.xpath("//a[contains(text(),'See the details')]/parent::div[@class='cart-wrapper show-for-medium-up']");
 	
 	public static By INVALID_PROD_SEARCH_MSG=By.xpath("//div[@id='js-search-product-items']//h4[contains(text(),'Sorry, no items were found. Please click on one of the other categories above, or try a different search.')]");
 	//warrenties
 	public static By PART_NUMBER_WARRENTIES_TAB=By.xpath("//div[@id='tpl-specs-warranties-target']//div[@class='content-inner']//div[@class='row'][3]//div[@class='product-details']");
    public static By QUANTITY_IN_WARRENTIES_TAB=By.xpath("//div[@id='tpl-specs-warranties-target']//div[@class='content-inner']//div[@class='row'][3]//div[@class='number-picker']//input");
    public static By ADD_TO_CART_IN_WARRENTIES_TAB=By.xpath("//div[@id='tpl-specs-warranties-target']//div[@class='content-inner']//div[@class='row'][3]//div[@class='add-to-order-wrapper']//a");
    
}
