package com.insight.ObjRepo;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class EndUserFeaturesObj extends ActionEngine{

	public static By PAYMENT_METHOD=By.xpath("//*[@id='js-chkoutDefalut-tab']/div/div/div[3]/div/div/div[2]/div");
	public static By PAYMNET_METHOD_DD_OPTIONS=By.xpath("//strong[text()='Payment Method']//parent::p//parent::div//div[@class='nice-select open']//ul//li");
	public static By PAYMENT_METHOD_SELECT_OPTION=By.xpath("//*[@id='js-chkoutDefalut-tab']/div/div/div[3]/div/div/div[2]/div//span");
	public static By UPDATE_BUTTON_CHECKOUT_DEFAULTS=By.xpath("//input[@id='updateChkoutDef']");
	public static By CHECK_OUT_DEFAULTS_UPDATE_SUCESS_MESSAGE=By.xpath("//div[@id='chkDefaultMessage']");	
	public static By SHIPPING_METHOD_SELECTED_OPTION=By.xpath("//*[@id='js-chkoutDefalut-tab']/div/div/div[1]/div/div/div[2]/div");

		
	
	// TC_03 related
	public static By AVAILABLEACC_LIST_FIRSTACCOUNT=By.xpath("//div[@id='availableAccountsSearchResults']/div/div/div[@class='favoriteAccountsAddressDiv']");	
	public static By RIGHT_TO_LEFT_ARROWBTN=By.xpath("//a[@id='moveToAvailable']/span");	
	public static By LEFT_TO_RIGHT_ARROWBTN=By.xpath("//a[@id='moveToFavorite']/span");
	public static By UPDATE_BTN=By.xpath("//input[@id='updateAcctFav']");
	public static By FAVACC_LIST_FIRSTACCOUNT=By.xpath("//div[@id='myFavoriteAccountsResults']/div/div/div[@class='favoriteAccountsAddressDiv']");	
	public static By CLEAR_FAV_ACCOUNTLIST= By.xpath("//div[@id='myFavoriteAccountsResults']/div");
	public static By UPDATE_SUCCESSMESG = By.xpath("//div[@id='acctFavMessage']");
	public static By ACCOUNT_SEARCHBOX = By.xpath("//input[@class='acctFavSearch']");
	public static By ACCOUNT_SEARCHICON = By.xpath("//span[@class='ion-search']");
	public static By ACCOUNT_FAVLINK = By.xpath("//h3[contains(text(),'Account Favorites')]");
	
	public static By ACCOUNT_DD_HOMEPAGE=By.xpath("//div[@class='c-dropdown is-open c-header-account-menu__dropdown']//button");
	public static By ACCOUNTDD_LIST=By.xpath("//div[@class='c-dropdown is-open c-header-account-menu__dropdown']//li");
	public static By ACCOUNTNAME=By.xpath("//div[@class='c-header__middle']//div/button[@id='accountDropdown']//span");
	public static By ACCOUNTSEARCHFIELD=By.xpath("//input[@type='Search']");
	  public static By accountNameBtn(String AccountName){
		  return By.xpath("//div[@class='c-dropdown is-open c-header-account-menu__dropdown']//li//button[contains(text(),'"+AccountName+"')]"); // select by value
	  }
   public static By SEEALLAVAILABLEACCOUNTLINKS=By.xpath("//a[contains(text(),'See all available accounts')]");
   public static By CURRENTACCOUNTPAGE=By.xpath("//h3[contains(text(),'Current Account')]");
   public static By REMOVEDEFUALT=By.xpath("//a[@class='removeDefault']");
   public static By USERNOTLINKEDTOANYWARNING_MSG=By.xpath("//span[@id='linkedAccountWarningText']");
   public static By NODEFUALTACCOUNT=By.xpath("//div[@id='accounToolsErrorMessage']");


	//favorite links
	public static By DELETE_FAVOURITE_LINK=By.xpath("//*[@id='QuickLinks__favorites']//div[@class='iw-item__actions']//div[@class='iw-item__clickaction iw-item__deleteclick ion-close']");
    public static By availableLink(String link) {
    	return By.xpath("//div[@class='iw-item__label'and text()='"+link+"']");
    }
    public static By availableItem(String item) {
    	return By.xpath("//div[@class='iw-item__label'and text()='"+item+"']");
    }
    
    public static By rightArrow(String item) {
    	return By.xpath("//div[@class='iw-item__label'and text()='"+item+"']/..//div[@class='iw-item__clickaction iw-item__sendclick ion-arrow-right-c']");
    }
    
    public static By favouriteLink(String item) {
    	return By.xpath("//*[@id='QuickLinks__favorites']//div[@class='iw-item__label' and text()='"+item+"']");
    }
    public static By UPDATE_BUTTON_FAVOURITE_LINKS=By.xpath("//input[@id='updatePref']");
    public static By toolsDropDownOptions(String favLink) {
    	return By.xpath("//li[@class='c-header-dropdown__item']//ul//a[text()='"+favLink+"']");
    	
    }
    
    public static By SETTINGS_ICON_IN_TOOLS_MENU=By.xpath("//div[@class='o-grid  o-grid--gutters-small']//div[@class='o-grid__item  o-grid__item--shrink']");
    public static By FAVOURITE_LINKS_HEADER=By.xpath("//div[@id='QuickLinks__permissionForm']");

    //current account
    public static By YOU_ARE_LOGGED_IN_AS_TEXT=By.xpath("//div[@class='medium-4 columns setPaddingLeft']//p[@class='m-b-md']//strong");
    public static By WEB_GROUP_TEXT=By.xpath("//div[@class='medium-5 columns setPaddingLeft']//p[@class='m-b-md']//strong");
    public static By YOUR_CURRENT_ACCOUNT=By.xpath("//div[@class='medium-4 columns currentAcctLvl']//strong");
    public static By SELECTED_RESULTS_DISPLAY=By.xpath("//div[@class='nice-select invert']//span");
    public static By RESULTS_DISPLAY_OPTIONS=By.xpath("//div[@class='nice-select invert open']//ul//li");
    public static By RESULTS_DISPLAY_DD=By.xpath("//*[@id='rowsPerPage']/div/div/div");
    public static By resultsDisplayDropdown(String resultsPerPage) {
    	return By.xpath("//div[@class='nice-select invert open']//ul//li[contains(text(),'"+resultsPerPage+"')]");
    	
    }
    public static By RESULTS_DISPLAYED=By.xpath("//table[@class='footable dark full m-t-md default footable-loaded']//tbody//tr");
    public static By CLOSE_RESULTS_DISPLAY_DD=By.xpath("//div[@class='nice-select invert open']");
    public static By pageNumberButton(String pageNumber) {
    	return By.xpath("//div[@id='js-pagination-content-currentAct']//div//li//a[text()='"+pageNumber+"']");
    }
    public static By activePageNumber(String pageNumber) {
    	return By.xpath("//div[@id='js-pagination-content-currentAct']//div//span[text()='"+pageNumber+"']//parent::li");
    }
    public static By SEARCH_FOR_AVAILABLE_ACCOUNT=By.xpath("//input[@class='currentActSearch']");
    public static By SEARCH_BUTTON=By.xpath("//button[@id='acctFav-search-submit']");
    public static By SWITCH_ACCOUNT_LINK=By.xpath("//form[@class='custom m-b-none']//a");
    public static By SEARCH_RESULTS=By.xpath("//div[@id='searchAcctResults']//div");
    public static By DEFAULT_ACCOUNT_REMOVED_MESSAGE=By.xpath("//div[text()='None selected, select a default account below']");

     
    public static By getContractOnHomePage(String contractName){
    	return By.xpath("//span[@class='c-header-nav__text  u-truncate'][contains(text(),'Contract – "+contractName+"')]");
    }

    public static By getListFromDD(String ContractName){
    	return By.xpath("//a[text()='"+ContractName+"']//following-sibling::ul//li");
    }
    
    public static By selectDDList(String ContractName,String list){
    	return By.xpath("//a[text()='"+ContractName+"']//following-sibling::ul//li//a[text()='"+list+"']");
    }
    
    public static By verifyDDSelected(String list){
    	return By.xpath("(//a[contains(text(),'"+list+"')])[1]");
    }
    

}
