package com.insight.ObjRepo;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class CommonObj extends ActionEngine{
	
	//HomePage
    public static By SEARCH=By.xpath("//input[@class='c-input  c-header-search__input']");
    public static By RESULT_FOR_SEARCH=By.xpath("//div//a[@class='button call-to-action']");
    public static By SEARCH_SUGGESSIONS=By.xpath("//div[@class='c-header-search-suggestions  is-visible']//ul//li");
    public static By COMPARE_LIST_ITEMS=By.xpath("//a[2][contains(.,'Compare Your List')]//span[@class='js-compare-list-count']");
    public static By FRENCH_COMPARE_LIST_ITEMS=By.xpath("//a[2][contains(.,'Comparer votre liste')]//span[@class='js-compare-list-count']");

    public static By getPrimaryNavLink(String header){
		return By.xpath("//div[@class='o-grid__item  o-grid__item--shrink  u-push-auto@desktop']//nav//li//a[contains(text(),'"+header+"')]");
	}
    
    public static By getPrimaryMenuListItems(String headerList){
    	return By.xpath("//div[@class='o-grid__item  o-grid__item--shrink  u-push-auto@desktop']//nav//li//div//button[contains(text(),'"+headerList+"')]");
    }
    
    // *************Home page Secondary Header menus *********************// 
    public static By getSecondaryHeaderMenu(String header){
		return By.xpath("//button[@class='c-button  c-button--none c-header-main-nav__link c-header-main-nav__link--dropdown']/span[contains(text(),'"+ header +"')]");
	}
    
    public static By getSecondaryMenuListItems(String headerList){
    	return By.xpath("//div[@class='o-grid  o-grid--gutters']//div//a[contains(text(),'"+headerList+"')]");
    }
	
    public static By getShopAllBtnsFromMenuList(String shopall){
    	return By.xpath("//div[@class='o-grid  o-grid--gutters']//div//a//span[contains(text(),'"+shopall+"')]");
    }
	
    // Home Page Login
    public static By USER_NAME = By.id("username");
    public static By PASSWORD = By.id("password");
    public static By LOG_IN_BUTTON = By.id("login-submit-button");
    public static By CLOSE=By.xpath("//a[@class='close-reveal-modal']");
    
    
    //Shop all products
    public static By SHOP_ALL_PRODUCTS_MENUS_LIST=By.xpath("//ul//a[@class='linkHighlight']");
    
    public static By getShopAllLinksInHeaders(String shopallbtn){
    	return By.xpath("//ul//a[@class='linkHighlight'][contains(text(),'"+shopallbtn+"')]");
    }

    public static By getShopAllProductsByCategory(String shoppingCatogory){
    	return By.xpath("//div[@class='insight-layout section']//div[@class='result-item-wrapper']//a[@data-gtm-info='"+shoppingCatogory+"']");
    }
    
    
    // Shop All Brands
    public static By getTopBrands(String shopByBrands){
    	return By.xpath("//div[@class='top-inner-full-width parsys']//div//ul//li//a[@title='"+shopByBrands+"']");
    }
    
    //Should write it dynamically
    public static By BRAND_BY_ALPHABETS=By.xpath("//div[@id='js-partner-list-container']//div[@class='letter-container']//li//a[contains(text(),'BlackBerry')]");
    
    public static By getShopByBrandByAlphabet(String alphabetBrand){
		return By.xpath("//div[@id='js-partner-list-container']//div[@class='letter-container']//li//a[contains(text(),'"+alphabetBrand+"')]");
    	
    }
    
	public static By LOGIN_USER=By.id("username");
	public static By LOGIN_PSWD=By.id("password");
	public static By LOGIN_BTN=By.id("login-submit-button");
    
	/* ******************************************************************************************************************
	 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> LOCATORS IN ADDED TO CART POP UP<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	 * ******************************************************************************************************************
	 */

	public static By TOTAL_PRICE_IN_POPUP = By.xpath("//*[contains(text(),'Total')]/parent::li");
	/* ******************************************************************************************************************
	 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> LOCATORS IN Tools<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	 * ******************************************************************************************************************
	 */
	//Tools >> Company standards
	public static By ACCOUNT_TOOLS = By.xpath("//*[@id='manage-wrapper']/a/i");
	public static By TOOLS_DD_HEADER=By.id("headerToolsDropdown");
	public static By FAVORITES =By.xpath("//div[@class='o-grid  o-grid--gutters-small']//div[contains(text(),'Favorites')]");
	public static By USER_PREFERENCE=By.xpath("//nav[@class='tabs ']//a[@data-tab='userPref'][@class='active']");
	public static By ACCOUNT_FAVORITES=By.xpath("//nav[@class='tabs ']//a[@data-tab='accountFavorites' and @class='active']");
	public static By SHOW_KEYWORD_SUGGESSIONS_UNCHECKED=By.xpath("//label//input[@id='showKeyword']/following-sibling::span[@class='custom checkbox']");
	public static By SHOW_KEYWORD_SUGGESSIONS_CHECKED=By.xpath("//label//input[@id='showKeyword']/following-sibling::span[@class='custom checkbox checked']");
	public static By UPDATE_PREF_BTN=By.id("updatePref");
	public static By PROD_DESC_ACCOUNT_TOOLS=By.xpath("//div//table[@id='prodGroupTable']//tr[2]//td[1]//a");
	public static By PROD_DESC_MINI_PPP_WINDOW=By.xpath("//div[@class='popup_productDescription']//strong");
	public static By MINI_WINDOW=By.xpath("//div[@id='miniWindowHeader']");
	public static By productGroupTable=By.xpath("//table[@id='prodGroupTable']//tr//th");
	public static By ITEMS_ADDED_TO_CART_POPUP=By.xpath("//div[@id='header']//b[contains(text(),'Items added to Cart')]");
	public static By PROD_GRP_CLOSE_ICON=By.xpath("//span[@class='insightLightBoxClose frameworkIcons Deep_Close_Icon']");
	public static By ADD_ITEMS_RADIO_BUTTON=By.xpath("//table[@id='prodGroupTable']//td//input[@type='radio' and @checked='checked']");
    public static By ADD_ITEMS_CHECKBOX=By.xpath("//table[@id='prodGroupTable']//td//input[@type='checkbox']");
    public static By ADD_TO_ORDER=By.xpath("//div[@id='csProductGroupAddToOrder']//a//span[contains(text(),'ADD TO ORDER')]");
	public static By SAVEDCART=By.xpath("//div[@class='sidebar-nav']//a[text()='Saved Carts / Order Templates']");	
	public static By VIEW_CART_PRODUCT_GROUP=By.xpath("//a[@title='View Cart >>']");
	public static By QUANTITY_COMPANY_STANDARDS=By.xpath("//table[@id='prodGroupTable']//td//input[@type='text']");
	

	
	/* ******************************************************************************************************************
	 * >>>>>>>>>>>>>>>>>>>> LOCATORS TO SELECT ACCOUNT TOOLS  MENU AND SELECTS DROP DOWN LIST <<<<<<<<<<<<<<<<<<<<<<<<<<
	 * ******************************************************************************************************************/
	public static By getAccountToolsMenu(String toolsMenuName){
		return By.xpath("//div[@id='manage-wrapper']//a[contains(text(),'"+toolsMenuName+"')]");
	}
	
	public static By getAccountToolsDD(String toolsMenuName,String dropDown){
	   return By.xpath("//div[@id='manage-wrapper']//a[contains(text(),'"+toolsMenuName+"')]/following::ul//li//a[contains(text(),'"+dropDown+"')]");
	}
	
	
	/* ******************************************************************************************************************
	 * >>>>>>>>>>>>>>>> LOCATORS TO SELCT PRODUCT GROUP AND PRODUCT FROM GROUP ON ACCOUNT TOOLS SCREEN <<<<<<<<<<<<<<<<<<
	 * ******************************************************************************************************************/
	public static By getCompanyStandardsProductGroup(String productGroup,String productName){
		return By.xpath("//div//td[contains(text(),'"+productGroup+"')]/following::div[@class='csProductGroupMemberDiv']//td//a[contains(text(),'"+productName+"')]");
	}
	
	/* ******************************************************************************************************************
	 * >>>>>>>>>>>>>>>> LOCATORS TO VERIFY WHETHER CORRECT PRODUCT GROUP IS SELECTED<<<<<<<<<<<<<<<<<<
	 * ******************************************************************************************************************/
	public static By getProductGrpNavigation(String productGroup,String productName){
		return By.xpath("//div[@id='csProductGroupBreadCrumb' and contains(.,'"+productGroup+"')]//span[contains(text(),'"+productName+"')]");
	}
	
	public static By getCartOnProductGroupPage(String productName){
		return By.xpath("//div[@class='csProductGroupMemberDiv']//td//a[contains(text(),'"+productName+"')]/following::div//input[@name='addToCart']");
	}
	
	/* ******************************************************************************************************************
	 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> LOCATORS TO SELECT REQUIRED TAB IN TOOLS / FAVORITES <<<<<<<<<<<<
	 * ******************************************************************************************************************/
	public static By getFavoritesTabs(String tabName){
	return  By.xpath("//nav[@class='tabs ']//a//div[contains(.,'"+tabName+"')]");
	}
	
	
	
	
	//contract
	public static By CONTRACT_DD=By.xpath("//div[@class='c-header__middle']//button[@id='contractDropdown']");
	public static By SELECTED_CONTRACT=By.xpath("//div[@class='c-header__middle']//button[@id='contractDropdown']//span");
    
	public static By getContractsFromDD(String contractName){
		return By.xpath("//li[@class='o-list-bare__item  c-header-account-menu__item']//button[contains(text(),'"+contractName+"')]");
	}
	
	public static By CONTRACT_TITLE_PAGE=By.xpath("//div[@id='ipsContractDetailTitle']//label");
	public static By CONTRACT_TITLE=By.xpath("//div[@id='ipsContractDetailTitle']");
	public static By SPINNER_IMAGE=By.xpath("//div[@class='iw-loading iw-loading__size-giant']");
	public static By CONTRACT_NAME_ON_PRODUCT_DETAILS=By.xpath("//div[@class='approved-item']/following-sibling::p[@class='sewp-prices']");
	public static By CONTRACT_NAME_ON_RECEIPT_PAGE=By.xpath("//div[@class='cart__table-block']//div[contains(text(),'Contract')]/following-sibling::div//strong");
	
	public static By getContractInCart(String contractName){
		return By.xpath("//div[@class='cart__table-block']//div[contains(text(),'Contract')]/following-sibling::div//strong[contains(text(),'"+contractName+"')]");
	}
	public static By getClientfromProductStandards(String productName){
		return By.xpath("//div//td/a[contains(text(),'"+productName+"')]");
	}
	public static By PRODUCTSGRP_HDR=By.xpath("//div[@id='companyStandardsHeading']/h1[contains(text(),'Product Standards')]");
	public static By DESCRIPTION_COLUMN =By.xpath("//table[@id='prodGroupTable']/tbody/tr/th[contains(text(),'Description')]");
	public static By CONTRACT_VERIFY = By.xpath("//div[@class='columns']/strong");
	
	public static By  INSIGHT_LOGO_HOME_PAGE=By.xpath("//a[@class='c-button  c-button--none c-header-logo__btn']");
	
	// Inventory Blow Out 
	public static By FEATURED_TECH_DEALS_PRODUCTS=By.xpath("//div[@class='row product-summary-component product-summary-basic']//a[@class='select-prod']");
	public static By INVENTORY_BLOWOUT_LABEL=By.xpath("//div//p[@class='ips-price-label'][contains(text(),'Inventory Blowout')]");
	
	public static By SEARCH_RESULTS_PAGE=By.xpath("//div[@id='js-search-product-items']");
	public static By getAccountToolOrderMenuItem= By.xpath("//div[@id='manage-wrapper']//li[@class='has-child']/a[contains(text(),'Orders')]");
	public static By BROWSEALLDESKTOPS_LNK= By.xpath("//a[contains(text(),'Browse all desktops')]");
	public static By MESSAGE_ICON= By.cssSelector("td[id*='priceCell'] span img");
	public static By MESSAGE_TOOLTIP= By.xpath("//div[@id='tooltip']");
	public static By PDP_MESSAGEVERIFY= By.xpath("//div[@class='noticeAlert'][contains(text(),'The price displayed will be prorated in the cart based on the remaining agreement period.')]");
	
}
