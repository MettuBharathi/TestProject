package com.insight.Lib;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.insight.ObjRepo.CanadaObj;
import com.insight.ObjRepo.CommonObj;
import com.insight.ObjRepo.EndUserFeaturesObj;
import com.insight.ObjRepo.MarriottIntlCorpObj;
import com.insight.ObjRepo.ShipBillPayObj;


public class EndUserFeaturesLib extends EndUserFeaturesObj{
	/**
	 * PURPOSE: This method is to click on a tab in accountTools<personolization<Userprofile
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 * **/
	public void clickOnTabInUserProfile(String tabName) throws Throwable {
		click(CommonObj.getFavoritesTabs(tabName), ""+tabName+" Tab in UserProfile");
	}
	/**
	 * PURPOSE: This method is to get selected option for payment method
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 * **/
	
	public void selectedOptionPaymentMethod(String option) throws Throwable {
		String selectedOption=getText(PAYMENT_METHOD_SELECT_OPTION, "selected option");
		System.out.println("selectedOption"+selectedOption);
		if(selectedOption.equalsIgnoreCase(option)) {
			reporter.SuccessReport("Verifyig default selected option", "Default selected option is "+option, option);
		}
		else {
			reporter.failureReport("Verifyig default selected option", "Default selected option is not "+option, selectedOption,driver);
		}
	}
	/**
	 * PURPOSE: This method is to click on update button in checkout defaults
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 * **/
	public void clickUpdateButtonInCheckoutDefaults() throws Throwable{
		click(UPDATE_BUTTON_CHECKOUT_DEFAULTS,"Update buttton in check out settings");
		if(isElementPresent(CHECK_OUT_DEFAULTS_UPDATE_SUCESS_MESSAGE, "Sucess message")) {
			reporter.SuccessReport("Verifying update sucess message", "user deafults saved sucessfully ", "");
		}
		else {
			reporter.failureReport("Verifying update sucess message", "user deafults not saved sucessfully  ", "",driver);
		}
	}
	
	/**
	 * PURPOSE: This method is to get selected shipping method
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 * **/
	public void selectedShippingMethod(String option) throws Throwable {
		String selectedOption=getText(SHIPPING_METHOD_SELECTED_OPTION, "selected option");
		System.out.println("selectedOption"+selectedOption);
		if(selectedOption.equalsIgnoreCase(option)) {
			reporter.SuccessReport("Verifying default Shipping option", "Default Shipping option is "+option, option);
		}
		else {
			reporter.failureReport("Verifying default Shipping option", "Default Shipping option is not "+option, selectedOption,driver);
		}
	}
	
	/**
	 * Method is to click on the account tools side menu and verify manage cloud option
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @throws Throwable
	 */
	public void verifyAccountToolsManageCloudDropdownOption(String toolsMenuName, String dropDown) throws Throwable {
		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		click(CanadaObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		if(isElementPresent(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools")) {
			reporter.SuccessReport("Verifying manage cloud in account tools", "Manage Cloud Link is Exists",dropDown);
		}
		else {
			reporter.failureReport("Verifying manage cloud in account tools", "Manage Cloud Link is does not Exist", dropDown,driver);
		
		}
	}
	
	/**
	 * Method is to click on the account tools side menu and verify manage cloud option is not present
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @throws Throwable
	 */
	public void verifyAccountToolsManageCloudDropdownOptionIsNotPresent(String toolsMenuName, String dropDown) throws Throwable {
		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		click(CanadaObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		if(isElementNotPresent(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools")) {
			reporter.SuccessReport("Verifying manage cloud in account tools", "Manage Cloud Link is does not Exist",dropDown);
		}
		else {
			reporter.failureReport("Verifying manage cloud in account tools", "Manage Cloud Link is Exist", dropDown,driver);
		
		}
	}

	
	/**
	 * Method is to click on the account drop down in Home Page
	 */
	public void toClickOnAcoountDropdown() throws Throwable {
		clickUntil(ACCOUNT_DD_HOMEPAGE,ACCOUNTDD_LIST,"Account DropDown");
	}
	

	/**
	 * Method is to Verify 20 Soldto's in account drop down in Home Page
	 */
	public void verifySoldtosInAccountDropdown(String SoldtosCount) throws Throwable {
		List<WebElement> myList = driver.findElements(ACCOUNTDD_LIST);
		int count= myList.size();
		int Soldtoscount=Integer.parseInt(SoldtosCount);
		if(count==Soldtoscount){
			reporter.SuccessReport("Verify 20 Soldto's should display::", "20 Soldto's Exists",""+count+"");
		}
		else {
			reporter.failureReport("Verifying manage cloud in account tools::", "20 Soldto's not Exists",""+count+"",driver);
		
		}
	}
	
	/**
	 * Method is to verify Account Name from account drop down in Home Page
	 */
	public String verifyAccountName() throws Throwable {
		String Accountname = null;
		if(isElementPresent(ACCOUNTNAME,"Account Name")){
		 Accountname=getText(ACCOUNTNAME,"Account Name").replace("Account –", "").trim();
			reporter.SuccessReport("Verify Account Name", "Account Name Exists and Displyed correctly","Current Account::"+Accountname+"");
		}
		else {
			reporter.failureReport("Verify Account Name", "Account Name not Exists","",driver);
		
		}
		return Accountname;
	}
	
	/**
	 * Method is to verify Account SearchBar from account drop down in Home Page
	 */
	public void verifyAccountSearchBar() throws Throwable {
		if(isElementPresent(ACCOUNTSEARCHFIELD,"Web Account Search Bar")){
			reporter.SuccessReport("Verify Web Account Search Bar", "Web Account Search Bar Exists and Displyed ","");
		}
		else {
			reporter.failureReport("Verify Web Account Search Bar", "Web Account Search Bar not Exists","",driver);
		
		}
	}
	
	/**
	 * Method is to select First Soldto from account drop down in Home Page
	 */
	public void selectFirstSoldto() throws Throwable {
		List<WebElement> myList = driver.findElements(ACCOUNTDD_LIST);
			if (myList.get(0).isDisplayed()) {
				myList.get(i).click();
				reporter.SuccessReport("Select a Soldto From Account Name List in Welcome Page","1st Solodto's Exists and Selected" ,"");
			} else  {
				reporter.failureReport("Select a Soldto From Account Name List in Welcome Page","1st Solodto's does Not Exists","",driver);
			}
	}

	/**
	 * PURPOSE: This method is to delete all fav links in user preferences
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 * **/
	public void deleteAllFavouriteLinks() throws Throwable {
		Thread.sleep(5000);
		
		if(isElementPresent(DELETE_FAVOURITE_LINK, "Delete favourite link")) {
			List<WebElement> myList=driver.findElements(DELETE_FAVOURITE_LINK);
		for (int i=0; i<myList.size();i++) {
			click(DELETE_FAVOURITE_LINK,"delete favourite");
		 }
		}
		else {
			LOG.info("No favourites links");
		}
		
	}
	/**
	 * PURPOSE: This method is to click on a available link in fav links page
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 * **/
	public void clickAvailableLink(String link) throws Throwable {
		scrollToBottomWithCordinate("800");
		click(availableLink(link),"click on link");
	}
	/**
	 * PURPOSE: This method is to select fav links
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 * **/
	
	public void selectFavourite(String item) throws Throwable {
		
		click(availableItem(item),"Hover on item");
		click(availableItem(item),"Hover on item");
		click(rightArrow(item),"Right arrow");
		if(isElementPresent(favouriteLink(item), "favourite link")) {
			reporter.SuccessReport("Verifying favourite links", "Favourite link is added","");
		}
		else {
			reporter.failureReport("Verifying favourite links", "Favourite link is not added","",driver);
		
		
		}
	}
	/**
	 * PURPOSE: This method is to click on update button in user preferences
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 * **/
	public void clickUpdateButtonInFavouritesLinks() throws Throwable {
		click(UPDATE_BUTTON_FAVOURITE_LINKS,"Update button");
	}
	
/**
 * 
 * @throws Throwable
 */
	public void clearMyFavoriteAccountsList()  throws Throwable {
		if (isElementPresent(CLEAR_FAV_ACCOUNTLIST, "My favorite account list")) {
			 List<WebElement> myList=driver.findElements(CLEAR_FAV_ACCOUNTLIST);
             for (int i=0; i<myList.size();i++) {
			click(FAVACC_LIST_FIRSTACCOUNT, "Account selected in favorites list");
			click (RIGHT_TO_LEFT_ARROWBTN,"Right to left Button"+ "<");
				reporter.SuccessReport("Account Management - Account Tools Account Favorites Page",
						"Account Link Exists and navigated back to available list","");
		}
		}else
           	 	LOG.info("No accounts are added to favorite list");		
			
           	  }
 /**
  * 
  * @throws Throwable
  */
	public void addSearchListtoFavorites()  throws Throwable {
		if (isElementPresent(AVAILABLEACC_LIST_FIRSTACCOUNT, "My favorite account list in available search")) {
			 List<WebElement> myList=driver.findElements(AVAILABLEACC_LIST_FIRSTACCOUNT);
             for (int i=0; i<4;i++) {
			click(AVAILABLEACC_LIST_FIRSTACCOUNT, "First Account selected in Available search list");
			if(isElementPresent(LEFT_TO_RIGHT_ARROWBTN,"Left to Right Button"+ ">")){
	               click (LEFT_TO_RIGHT_ARROWBTN,"Left to Right Button"+ ">");
	               reporter.SuccessReport("Click on Move To Favorite Link - Account Tools in Account Favorites Page",
							"> Link Exists and Clicked","");
			}else
				reporter.failureReport("Click on Move To Favorite Link - Account Tools in Account Favorites Page",
						"> Link Does Not Exists","");
             }
				reporter.SuccessReport("Account Management - Account Tools Account Favorites Page",
						" Account Link Exists in Available list is Clicked","");
		}else
			reporter.failureReport("Account Management - Account Tools Account Favorites Page",
					"First AccountLink Not Exists","");
	
	 if(isElementPresent(CLEAR_FAV_ACCOUNTLIST, "My favorite account list")) {
		 reporter.SuccessReport("Account Management - Account Tools Account Favorites Page",
					"First Account is Added to My Favorite Accounts","");
	}else
		reporter.failureReport("Account Management - Account Tools Account Favorites Page",
				"First Account is Not Added to My Favorite Accounts","");
	          
	}
	
	public void clickUpdateButton() throws Throwable {
		click(UPDATE_BTN,"Update Button");
	}
/**
 * 
 * @throws Throwable
 */
	public void verifyupdateSuccessMessage() throws Throwable {
		isElementPresent(UPDATE_SUCCESSMESG, "Update Success Message");
	}
	/**
	 * 
	 * @param AccountName
	 * @throws Throwable
	 */
	public void clearAndSearchWithAccountNum(String AccountName) throws Throwable {
		if(isElementPresent(ACCOUNT_SEARCHBOX, "Account search textbox")){
			clearData(ACCOUNT_SEARCHBOX);
			type(ACCOUNT_SEARCHBOX,AccountName,"Account Name");
			click(ACCOUNT_SEARCHICON,"Search icon");
		}
	}
	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyAccountFavorites() throws Throwable {
		if(isElementPresent(ACCOUNT_FAVLINK, "Account favorites lInk")){
			 reporter.SuccessReport("Account Management - Account Tools Account Favorites Page",
						"Account Favorites  Link Exist Under Address Book","");
		}else{
			reporter.failureReport("Account Management - Account Tools Account Favorites Page",
					"Account Favorites  Not Exist Under Address Book","");
	}
	}

	/**
	 * Method is used to click on tools dropdwown in homepage
	 * 
	 * @throws Throwable
	 */
	public void selectToolsDropDownInHomepage(String favLink) throws Throwable {
		click(CommonObj.TOOLS_DD_HEADER, "tools drop down");
		click(toolsDropDownOptions(favLink), favLink);
	}
	/**
	 * PURPOSE: This method is to click on tools link in top navigation and verify fav links
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 * **/
	public void selectToolsDropdownAndVerifyFavouriteLinkIsNotPresent(String favLink) throws Throwable {
		click(CommonObj.TOOLS_DD_HEADER, "tools drop down");
		if(isElementNotPresent(toolsDropDownOptions(favLink), favLink)) {
			reporter.SuccessReport("Verifying favourite links", "Favourite link is not available",favLink);
		}
		else {
			reporter.failureReport("Verifying favourite links", "Favourite link is available",favLink,driver);
		
		}
	}
	/**
	 * PURPOSE: This method is to click on tools link and click on settings icon
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 * **/
	public void clickSettingsIconInToolsDD() throws Throwable {
		click(CommonObj.TOOLS_DD_HEADER, "tools drop down");
		click(SETTINGS_ICON_IN_TOOLS_MENU,"Settings icon in tools menu");
	}
	/**
	 * PURPOSE: This method is to verify fav page is opened
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 * **/
	public void verifyFavouritesLinksPageOpened() throws Throwable {
		if(isElementPresent(FAVOURITE_LINKS_HEADER, "Favourites links header")) {
			reporter.SuccessReport("Verify Manage Favorite Links Page", "Page Loaded","");
		}
		else {
			reporter.failureReport("Verify Manage Favorite Links Page", "Page dows Not Exists","",driver);
		
		}

	}


	/**
	 * Method is to click to Continue Button
	 */
	public void clickContinueButton() throws Throwable {
	click(MarriottIntlCorpObj.CONTINUEBUTTON_WEBGRPCHANGE, "Continue");
	}
	
	

	/**
	 * Method is to verify Account SearchBar from account drop down in Home Page
	 */
	public void clickonAccountName(String Accountname) throws Throwable {
		if(isElementPresent(accountNameBtn(Accountname),"Account Name")){
			click(accountNameBtn(Accountname),"Default Account Name");
			reporter.SuccessReport("Select a Same Soldto From Account Name List in Welcome Page", "Previous Solodto's Exists and Selected","");
		}
		else {
			reporter.failureReport("Select a Same Soldto From Account Name List in Welcome Page", "Previous Solodto's does Not Exists","",driver);
		
		}
	}
	
	
	/**
	 * Method is to verify Account SearchBar from account drop down in Home Page
	 */
	public void verifyAccountNamechanged(String accountName,String accountname) throws Throwable {
		if(accountName.equals(accountname)){
			reporter.failureReport("Account Name in Changed in Welcome Page", "Account Name does Not Changed","",driver);
		}
		else {
			reporter.SuccessReport("Account Name is Changed in Welcome Page", "Account Name Changed","");
		}
	}
	
	/**
	 * Method is to verify and select See all available accounts Link from account drop down in Home Page
	 */
	public void verifySeeAllAvailableAccounts() throws Throwable {
		if(isElementPresent(SEEALLAVAILABLEACCOUNTLINKS,"See all available accounts Link")){
			click(SEEALLAVAILABLEACCOUNTLINKS,"See all available accounts Link");
			reporter.SuccessReport("Select a See all Available Accounts Link From Account Name List in Welcome Page", "See all Available Accounts Link Exists and Selected","");
		}
		else {
			reporter.failureReport("Select a See all Available Accounts Link From Account Name List in Welcome Page", "See all Available Accounts Link does Not Exist","",driver);
		
		}
	}
	/**
	 * Method is to verify Current Account on Account Management-Account Tools Page
	 */
	public void verifyCurrentaccountPageinAccounttools() throws Throwable {
		if(isElementPresent(CURRENTACCOUNTPAGE,"Current Account")){
			reporter.SuccessReport("Current Account on Account Management-Account Tools Page", "Current Account Page is Exists","");
		}
		else {
			reporter.failureReport("Current Account on Account Management-Account Tools Page", "Current Account Page is Not Exists","",driver);
		
		}
	}
	
	/**
	 * Method is to verify Current Account on Account Management-Account Tools Page
	 */
	public void verifyAccountFavertesTab(String tabName) throws Throwable {
		if(isElementPresent(CommonObj.getFavoritesTabs(tabName),"Account Favorites Tab")){
			click(CommonObj.getFavoritesTabs(tabName),"Account Favorites Tab");
			reporter.SuccessReport("Select Account Favorites Tab From on Account Management-Account Tools Page", "Account Favorites Tab is Exists and Selected","");
		}
		else {
			reporter.failureReport("Select Account Favorites Tab From on Account Management-Account Tools Page", "Account Favorites Tab is Not Exists","",driver);
		
		}
	}
	/**
	 * Method is to Verify 20 Soldto's in account drop down in Home Page
	 */
	public void verifyFavoriteAccountssoldto(String SoldtosCount) throws Throwable {
		List<WebElement> myList = driver.findElements(ACCOUNTDD_LIST);
		int count= myList.size();
		int Soldtoscount=Integer.parseInt(SoldtosCount);
		if(count==Soldtoscount){
			reporter.SuccessReport("Hover on Account Name in Insight Home Page::", "Favorites 3 Solodto's are Exist",""+count+"");
		}
		else {
			reporter.failureReport("Hover on Account Name Insight Home Page::", "Favorites 3 Solodto's are Not Exist",""+count+"",driver);
		
		}
	}
	
	/**
	 * Method is to verify Remove default account Link Current Account on Account Management-Account Tools Page
	 */
	public void verifyRemoveDefualtLink() throws Throwable {
		if(isElementPresent(REMOVEDEFUALT,"Remove default account")){
			reporter.SuccessReport("System displays Remove Link on Current Account Tab in Account Management -Account Tools Page", "Remove Link on Current Account Tab is Existss","");
		}
		else {
			reporter.failureReport("System displays Remove Link on Current Account Tab in Account Management -Account Tools Page", "Remove Link on Current Account Tab is Not Exists","",driver);
		
		}
	}
	/**
	 * Method is to verify default account Removed Link Current Account on Account Management-Account Tools Page
	 */
	public void verifyDefualtLinkRemovedwarning() throws Throwable {
		if(isElementPresent(NODEFUALTACCOUNT,"default account Removed")){
			reporter.SuccessReport("Default Login Account Removed Message on Current Account Tab", "Default Login Account Removed Message on Current Account Tab is Exists","");
		}
		else {
			reporter.failureReport("Default Login Account Removed Message on Current Account Tab", "Default Login Account Removed Message on Current Account Tab does Not Exist","",driver);
		
		}
	}
	
	/**
	 * Method is to verify Remove default account Link Current Account on Account Management-Account Tools Page
	 */
	public void verifyRemoveDefualtLinkandSelect() throws Throwable {
			click(REMOVEDEFUALT,"Remove default account");
	}
	
	/**
	 * Method is to verify User is not linked to any Account
	 */
	public void verifyUserisnotlinkedtoanyAccount() throws Throwable {
		if(isElementPresent(USERNOTLINKEDTOANYWARNING_MSG,"Remove default account")){
			String Warningsg=getText(USERNOTLINKEDTOANYWARNING_MSG,"Remove default account");
			reporter.SuccessReport("Verify the user is linked to any Account", "User is not linked to any Account",Warningsg);
		}
		else {
			reporter.failureReport("Verify the user is linked to any Account", "User is linked to any Account","",driver);
		
		}
	}
	
	public void verifyLoggedInAs(String loggedInUser) throws Throwable {
		String text=getText(YOU_ARE_LOGGED_IN_AS_TEXT, "You are logged in as");
		if(text.contains(loggedInUser.toLowerCase())) {
			reporter.SuccessReport("System displays User Name on Current Account Tab in Account Management -Account Tools Page", "User Name on Current Account Tab is Exists","");
		}
		else {
			reporter.failureReport("System displays User Name on Current Account Tab in Account Management -Account Tools Page", "User Name on Current Account Tab is Not Exists","",driver);
		
		}
	}
	
	public void verifyWebGroup(String webGroup) throws Throwable {
		String text=getText(WEB_GROUP_TEXT, "webgroup");
		if(text.contains(webGroup)) {
			reporter.SuccessReport("System displays Current WebGroup on Current Account Tab in Account Management -Account Tools Page", "Current WebGroup on Current Account Tab is Exists","");
		}
		else {
			reporter.failureReport("System displays Current WebGroup on Current Account Tab in Account Management -Account Tools Page", "Current WebGroup on Current Account Tab is Not Exists","",driver);
		
		}
	}
	
	public void verifyCurrentAccount() throws Throwable {
		if(isElementPresent(YOUR_CURRENT_ACCOUNT, "Current account")) {
			reporter.SuccessReport("System displays Account Name and Address on Current Account Tab in Account Management -Account Tools Page", "Account Name and Address on Current Account Tab is Exists","");
		}
		else {
			reporter.failureReport("System displays Account Name and Address on Current Account Tab in Account Management -Account Tools Page", "Account Name and Address on Current Account Tab is Not Exists","",driver);
		
		}
	}
	

	public void verifyResultsDisplayedPerPage(String resultsPerPage) throws Throwable {
		String text=getText(SELECTED_RESULTS_DISPLAY, "Results per page");
		if(text.contains(resultsPerPage)) {
			reporter.SuccessReport("List Default Show 10 results per page on Current Account Tab in Account Management -Account Tools Page", "List Default Show 10 results per page on Current Account Tab is Exists","");
		}
		else {
			reporter.failureReport("List Default Show 10 results per page on Current Account Tab in Account Management -Account Tools Page", "List Default Show 10 results per page on Current Account Tab is Not Exists","",driver);
		}
	}
	
	public void verifyResultsPerPagrDDOptions(String options) throws Throwable {
		click(RESULTS_DISPLAY_DD, "Results per page");
		List<WebElement> myList=driver.findElements(RESULTS_DISPLAY_OPTIONS);
		String[] strArray=options.split(",");
		for (int i=0;i<myList.size();i++) {
			System.out.println("myList.get(i).getText()"+myList.get(i).getText());
			if(myList.get(i).getText().equalsIgnoreCase(strArray[i])) {
				reporter.SuccessReport("List Shows "+strArray[i]+" results per page on Current Account Tab in Account Management -Account Tools Page", "List Shows "+strArray[i]+" results per page on Current Account Tab is Exists", strArray[i]);
			}
			else {
				reporter.failureReport("List Shows "+strArray[i]+" results per page on Current Account Tab in Account Management -Account Tools Page", "List Shows "+strArray[i]+" results per page on Current Account Tab is does not Exists", strArray[i],driver);
			}
		}
		click(CLOSE_RESULTS_DISPLAY_DD,"Close results display dropdown");
	}

	/**
	 * This method is to select a new contract in the home page contracts DD.
	 * @param contractName
	 * @throws Throwable
	 */
	public void verifyContractExistsInDropDownOrNot(String contractName,String status) throws Throwable{
		isElementClickable(CommonObj.CONTRACT_DD, 3, "contract drop down");
		
		switch (status) {
		case "ON":
			if (isElementPresent(CommonObj.getContractsFromDD(contractName) ,"Contract name")) {
				reporter.SuccessReport("Verify the contracts page displayed ","contract is displayed successfully as : ",contractName );
			} else {
				reporter.failureReport("Verify the contracts page displayed ","contract is not displayed successfully",contractName);
			}
			break;

		case "OFF":
			if (isElementNotPresent(CommonObj.getContractsFromDD(contractName),"Contract name")) {
				reporter.SuccessReport("Verify the contracts page displayed ","contract is not displayed. : ",contractName );
			} else {
				reporter.failureReport("Verify the contracts page displayed ","contract is  displayed as ",contractName);
			}
		default:
			break;
		}
		
	}
	/**
	 * 
	 * @param option
	 * @throws Throwable
	 */
	public void selectResultsPerPageDD(String option) throws Throwable {
		click(RESULTS_DISPLAY_DD, "Results per page");
		click(resultsDisplayDropdown(option),"Select results per page");
		List<WebElement> myList=driver.findElements(RESULTS_DISPLAYED);
		if(myList.size()==Integer.parseInt(option)) {
			reporter.SuccessReport("System returns "+option+" results per page on Current Account Tab in Account Management -Account Tools Page", ""+option+" results per page on Current Account Tab is Exists", option);
		}
		else {
			reporter.failureReport("System returns "+option+"20 results per page on Current Account Tab in Account Management -Account Tools Page", ""+option+" results per page on Current Account Tab does not Exists", option,driver);
		}
	}
	
	/**
	 * 
	 * @param pageNumber
	 * @throws Throwable
	 */
	public void clickOnPageNumber(String pageNumber) throws Throwable {
		click(pageNumberButton(pageNumber),"Page Number "+pageNumber);
		String status=getAttributeByClass(activePageNumber(pageNumber),"Active status");
		if(status.equalsIgnoreCase("active")) {
			reporter.SuccessReport("Select a Page "+pageNumber+" on Current Account Tab in Account Management -Account Tools Page", "Page "+pageNumber+" Results on Current Account Tab is Exists", pageNumber);
		}
		else {
			reporter.failureReport("Select a Page "+pageNumber+" on Current Account Tab in Account Management -Account Tools Page", "Page "+pageNumber+" Results on Current Account Tab does not Exists", pageNumber);
		}
	}
	
	/**
	 * 
	 * @param account
	 * @throws Throwable
	 */
	public void searchForAvailableAccount(String account) throws Throwable {
		type(SEARCH_FOR_AVAILABLE_ACCOUNT, account, "Account search field");
		click(SEARCH_BUTTON,"Search button");
		
	}
	
	/**
	 * 
	 * @throws Throwable
	 */
	public void clickSwitchAccountLink() throws Throwable {
		click(SWITCH_ACCOUNT_LINK,"Switch account");
	}
	
	/**
	 * 
	 * @throws Throwable
	 */
	public void verifySearchResults() throws Throwable {
		if(isElementPresent(SEARCH_RESULTS, "Search results")) {
			reporter.SuccessReport("Verifying search results", "Search results are displayed", "");
		}
		else {
			reporter.failureReport("Verifying search results", "Search results are not displayed", "",driver);
		
		}
	}
	
	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyDefaultAccountIsRemoved() throws Throwable {
		if(isElementPresent(DEFAULT_ACCOUNT_REMOVED_MESSAGE, "Deafult account removed message")) {
			reporter.SuccessReport("Verifying default account is removed", "Default account does not exists", "");
		}
		else {
			reporter.failureReport("Verifying default account is removed", "Default account exists", "",driver);
		}
	}
	
	/**
	 * Method is to select contracts DD
	 * @param contract
	 * @throws Throwable
	 */
	public void selectDropDown(String contract) throws Throwable{
	   clickUntil(ShipBillPayObj.selectDropdownOption(contract),getListFromDD(contract),"Select "+contract+" ");
	
	}
	
	/**
	 * Method is to verify the Drop down list exists for contracts
	 * @param ContractName
	 * @throws Throwable
	 */
	public void VerifyListElements(String ContractName) throws Throwable{
		List <WebElement> list=driver.findElements(getListFromDD(ContractName));
		if(list.size()>0){
			for(i=0;i<list.size();i++){
				String DDElement=list.get(i).getText();
				reporter.SuccessReport("Verify List elements in Drop down", "Drop down exist in "+ContractName+ " and is displayed : "+DDElement, "");
		  }
		}else{
			reporter.failureReport("Verify List elements in Drop down", "Drop down list does not exist", "", driver);
		}
	}
	
	/**
	 * This method is to select the DD list and verify.
	 * @param DDName
	 * @param list
	 * @throws Throwable
	 */
	public void selectFromDDListAndVerify(String DDName,String list) throws Throwable{
		click(selectDDList(DDName, list),"Drop down list");
		if(isElementPresent(verifyDDSelected(list), "selected list")){
			reporter.SuccessReport("verify selected DD is displayed", "Selected option is displayed", list);
		}else{
			reporter.failureReport("verify selected DD is displayed", "Selected option is not displayed", list,driver);
		}
	}
}