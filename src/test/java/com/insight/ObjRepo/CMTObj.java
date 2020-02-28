package com.insight.ObjRepo;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.insight.accelerators.ActionEngine;

public class CMTObj extends ActionEngine {

	// ****************** CMT tool **************************** //
	public static By WEB_GROUP = By.id("webGroupInput");
	public static By SEARCH_BUTTON_CMT_TOOL = By.id("eAdminCustomerSearchButton");
	public static By MANAGE_USERS_BUTTON = By.id("manageUsersButton");
	public static By LNAME_EMAIL_USERNAME = By.id("userSearchText");
	public static By USERNAME_SEARCH_BUTTON = By.id("userSearchResultsButton");
	public static By USER_CONTACT_NAME = By
			.xpath("//table[@id='userSearchResultsTable']/tbody/tr/td[@id='userName']/a");
	public static By CMT_HOME_CLOSE_ICON = By.xpath("//span[@class='ui-icon ui-icon-closethick']");
	public static By CMT_WELCOME_POPUP = By.xpath("//span[@id='ui-id-1'][contains(text(),'Welcome to Insight Beta!')]");
	public static By MANAGE_WEBGRP = By.id("manageWebGroupOptionsButton");
	public static By LOGIN_AS_REPORTING_ADMIN = By.id("reportingAdminLoginAs");
	public static By LOGIN_AS = By.xpath("//div[@id='userLoginAs']//a");
	public static By MASTER_GROUP = By.xpath("//div[@id='masterGroupSale']");
	public static By CHANGE_MASTER_GRP = By
			.xpath("//div[@id='differentMasterGroup']//a[contains(text(),'Change Master Group')]");
	
	public static By USER_INPUT=By.xpath("//input[@id='userInput']");
	public static By WEB_GRP_LINK=By.xpath("//a[@id='webGroupId1']");
		

	// This method is to get the Logged in user to Verify the Same User Logged
	// into Insight from CMT
	public static By getLoginVerficationByContactNameOnHeader(String contactName) {
		return By.xpath("//div[@class='c-header__middle']//span[contains(text(),'User - " + contactName + "')]");
	}

	// Welcome Popup
	public static By EMAIL_INPUT = By.id("emailId");
	public static By SUBMIT = By.id("resetPasswordSubmit");

	// public String
	// CMT_WEBGRP_NAME__LINK="//td[@id='webGroupName']//a[contains(text(),'#')]";
	// public String
	// MANAGE_WEBGRP_DD="//div[@id='manageWebGroupDiv']//a[contains(text(),'#')]";

	public static By getWebGroupName(String webGrpName) {
		return By.xpath("//td[@id='webGroupName']//a[contains(text(),'" + webGrpName + "')]");
	}

	public static By getManageWebGroupDDLinks(String links) {
		return By.xpath("//div[@id='manageWebGroupDiv']//a[contains(text(),'" + links + "')]");
	}

	// Custom Catalogs
	public static By APPROVE_ITEM_CATALOG_LINK = By
			.xpath("//td[@class='contentline']//a[contains(text(),'Approved Item Catalog')]");
	public static By APPROVE_ITEM_CATALOG_CREATE_BTN = By.xpath(
			"//td[@class='contentline' and contains(text(),'Approved Item Catalog')]/following-sibling::td//a[@id='csCancelButton']");
	public static By MANUFACTURERS_TAB_CATALOG = By
			.xpath("//span[@class='STSpans'][contains(text(),'Include Manufacturers')]");
	public static By INCLUDE_MANUFACTURERS = By.xpath("//select[@id='notIncludedManufacturers']");
	public static By RIGHT_ARROW_INCLUDE_MFRS = By.xpath("//div[@id='iManfContents']//td//div[@class='buttons']//a[2]");
	public static By UPDATE_BTN = By.id("csUpdateButton");

	public static By getCurrentIncludedManufacturers(String manufacturer) {
		return By.xpath("//select[@id='includedManufacturers']//option[contains(text(),'" + manufacturer + "')]");
	}

	// Users
	public static By UPDATE_USER_BTN = By.xpath("//div[@id='updateButtonDiv']//a");
	public static By PERMISSION_UPDATE_MSG = By
			.xpath("//div[@id='rolePermissionsUpdateMessage']//span[contains(.,'Permissions Updated Succesfully')]");

	// Web Group Permissions
	public static By UPDATE_CUSTOMER_PERMISSIONS_BTN = By.xpath("//div[@id='updateWebGroupButtonDiv']//a");
	public static By CUSTOMER_PERMISSION_UPDATE_MSG = By
			.xpath("//div[@id='webGroupUpdateMessageText' and contains(text(),'WebGroup updated successfully')]");

	/*
	 * *************************************************************************
	 * *************************** >>>>>>>>>>>COMMON METHOD TO SEARCH CONTACT
	 * NAME/ LNAME,FNAME IN USERS SCREEN <<<<<<<<<<<<<<<<<<<
	 * *************************************************************************
	 * ***************************
	 */
	public static By getUser(String contactName) {
		return By.xpath("//table[@id='userSearchResultsTable']/tbody/tr/td[@id='userName']/a[contains(text(),'"
				+ contactName + "')]");
	}

	/*
	 * *************************************************************************
	 * *************************** >>>>>>>>>>>COMMON METHOD TO SEARCH WEB GROUP
	 * BY NUMBER <<<<<<<<<<<<<<<<<<<
	 * *************************************************************************
	 * ***************************
	 */
	public static By getWebGroupNumber(String webGroupNumber) {
		return By.xpath("//*[@id='webGroupId']/a[contains(text(),'" + webGroupNumber + "')]");
	}

	public static By CLOSE_ICON = By.xpath("//a[@class='close-reveal-modal']");

	/*
	 * *************************************************************************
	 * *************************** >>>>>>>>>>>COMMON METHOD TO GET CHECKBOX
	 * LOCATOR FOR ANY PERMISSION AT USER LEVEL<<<<<<<<<<<<<<<<<<<
	 * *************************************************************************
	 * ***************************
	 */
	public static By getUserPermission(String userPermission) {
		return By.xpath("//div[@class='permissionLabel']/a[contains(.,'" + userPermission
				+ "')]/parent::div/preceding-sibling::div//input");
	}

	public static By UPDATE_USER = By.xpath("//*[@id='updateButtonDiv']/a/span");

	/*
	 * *************************************************************************
	 * *************************** >>>>>>>>>>>COMMON METHOD TO GET INDIVIDUAL
	 * TABS IN THE USERS SCREEN <<<<<<<<<<<<<<<<<<< [TABS : Information, Roles
	 * and Permissions,Linked Accounts, Checkout Settings]
	 * *************************************************************************
	 * ***************************
	 */
	public static By getUsersTabMenus(String menuName) {
		return By.xpath("//div//ul[@id='tabMenuContainer']//li//a[contains(text(),'"+menuName+"')]");
	}

	/*
	 * *************************************************************************
	 * *************************** >>>>>>>>>>>COMMON METHOD TO SELECT THE USER
	 * PERMISSIONS FROM THE DROP DOWN IN CMT <<<<<<<<<<<<<<<<<<<
	 ****************************************************************************************************/
	public static By getPermissionDropDowns(String userPermission) {
		return By.xpath("//div[@class='permissionLabel']/a[contains(.,'" + userPermission + "')]/following::select");
	}

	/*
	 * *************************************************************************
	 * *************************** >>>>>>>>>>> COMMON METHOD TO SELECT CUSTOMER
	 * LEVEL PERMISSIONS FOR A WEB GROUP <<<<<<<<<<<<<<<<<<<
	 ****************************************************************************************************/
	public static By getCustomerLevelPermissionsForWebGrp(String customerPermission) {
		return By.xpath("//div[@id='webGroupPermissions']//a[contains(text(),'" + customerPermission
				+ "')]/parent::div/preceding-sibling::div//input");
	}

	// check out settings
	public static By optionsInCheckOutSettings(String optionToSelect) {
		return By.xpath("//span[text()='" + optionToSelect + "']//parent::div//a");
	}

	public static By DEFAULT_SHIPPING_OPTION = By.xpath("//select[@id='defaultShipTypeSelID1']");
	public static By UPDATE_BUTTON = By.xpath("//span[text()='Update']//parent::a");
	public static By defaultShippingOption(String text) {
		return By.xpath("//select[@id='defaultShipTypeSelID1']//option[text()='"+text+"']");
	}
	public static By getOptionsunderCkeckoutsettings(String user_Permissions) {
		return By.xpath("//span[@id='" + user_Permissions + "']");
	}

	public static By USER_SERVICE_LEVEL_SHIPPING = By.xpath("//div[@id='shipOptionsContainer']/div[1]/input");
	public static By DEAFULT_MAIL = By.xpath("//div[@id='emailAddress1']//input");
	public static By User_service_levelshipping = By.xpath("//div[@id='shipOptionsContainer']/div[1]/input");

	public static By DefaultShippingOption = By.xpath("//select[@id='defaultShipTypeSelID1']");
	public static By UPDATE_USER_ShippingEstimations = By.xpath("//span[text()='Update']//parent::a");
	public static By SUCCESS_UPDATE_MSG = By.xpath("//div[@id='checkoutSettUpdateMsg']");
	public static By DesignatedShippingOption_Button = By.xpath("//div[@id='shipOptionsContainer']/div[4]/input");
	public static By Designatedshippingoptions = By.xpath("//select[@id='shipAvailableSel']");
	public static By buttontoclickFedExoptin = By.xpath("//a[@id='shipOptionsRightArrowButton']");
	public static By DesignatedshippingFedoption_dropdown = By.xpath("//select[@id='defaultShipTypeSelID2']");
	public static By AVILABLE_OPTIONS = By.xpath("//div[@id='payAvailableOptions']");
	public static By ALL_AVILABLE_OPTIONS = By.xpath("//select[@id='shipAvailableSel']//option");
	public static By ALL_ALLOWED_OPTIONS = By.xpath("//select[@id='shipAllowedSel']//option");
	public static By LEFT_ARROW = By.xpath("//a[@id='shipOptionsLeftArrowButton']");

	public static By SELECT_ALLWOED_OPTION = By.xpath("//select[@id='shipAllowedSel']");

	// Manage Web Group>> Contacts and Notifications

	/*
	 * *************************************************************************
	 * *************************** >>>>>>>>>>> COMMON METHOD TO GET THE REMOVE
	 * ICON CORRESPONDING TO REP<<<<<<<<<<<<<<<<<<<
	 ****************************************************************************************************/
	public static By getRemoveExistedsalesreps(String rep) {
		return By.xpath("//td//a[contains(text(),'" + rep + "')]/following::td//a//img[@alt='Delete']");
	}

	/*
	 * *************************************************************************
	 * *************************** >>>>>>>>>>> COMMON METHOD TO GET OREDERS
	 * CHECK BOX OF A REP<<<<<<<<<<<<<<<<<<<
	 ****************************************************************************************************/
	public static By getOrdersCheckBoxsalesreps(String repName) {
		return By.xpath("//td//a[contains(text(),'" + repName + "')]/following::td//input[@value='SECR']");
	}

	/*
	 * *************************************************************************
	 * *************************** >>>>>>>>>>> COMMON METHOD TO GET QUOTES CHECK
	 * BOX OF A REP<<<<<<<<<<<<<<<<<<<
	 ****************************************************************************************************/
	public static By getQuotesCheckBoxOfRep(String repName) {
		return By.xpath("//td//a[contains(text(),'" + repName + "')]/following::td[2]//input[@type='checkbox']");
	}

	/*
	 * *************************************************************************
	 * *************************** >>>>>>>>>>> COMMON METHOD TO GET OREDERS
	 * CHECK BOX OF Client Notifications<<<<<<<<<<<<<<<<<<<
	 ****************************************************************************************************/
	public static By getOrdersOnClientNotifications(String repEmail) {
		return By.xpath("//td//b[contains(text(),'" + repEmail
				+ "')]/following::td[@id='receiveOrders']//input[@type='checkbox']");
	}

	/*
	 * *************************************************************************
	 * *************************** >>>>>>>>>>> COMMON METHOD TO GET QUOTES CHECK
	 * BOX OF Client Notifications<<<<<<<<<<<<<<<<<<<
	 ****************************************************************************************************/
	public static By getQuotesOnClientNotifications(String repEmail) {
		return By.xpath("//td//b[contains(text(),'" + repEmail
				+ "')]/following::td[@id='receiveQuotes']//input[@type='checkbox']");
	}

	public static By removeEmailInClientNotifications(String rep) {
		return By.xpath("//table[@id='emailContainer']//td//b[contains(text(),'" + rep
				+ "')]/following::td//a//img[@alt='Delete']");
	}

	public static By REP_REMOVED_MSG = By
			.xpath("//div[@class='successUpdated'][contains(text(),'The rep has been removed successfully ')]");
	public static By ADD_NEW_REP_LINK = By.xpath("//td//a[contains(text(),'Add New Rep')]");
	public static By NEW_REP_EMAIL = By.xpath("//input[@id='addNewRepEmailBox']");
	public static By NEW_REP_NAME = By.xpath("//input[@id='addNewRepNameBox']");
	public static By NEW_REP_TITLE = By.xpath("//input[@id='addNewRepTitleBox']");
	public static By NEW_REP_PHONE_NO = By.xpath("//input[@id='addNewRepPhoneNumberBox']");
	public static By NEW_REP_UPDATE_BTN = By.xpath("//a[@id='repSaveId']");
	public static By NEW_REP_MSG = By
			.xpath("//div[@class='successUpdated'][contains(text(),'New rep has been added successfully')]");
	public static By CLIENT_NOTIFICATION_EMAIL = By.xpath("//input[@id='addEmailAddress']");
	public static By ACTION_ICON_CLIENT_NOTIFICATIONS = By.xpath("//table[@id='emailContainer']//a//img[@alt='Add']");
	public static By EMAIL_ADDED = By.xpath("//td[@class='accRepSecondEmail0']");
	public static By CLIENT_NOTIFICATION_EMAIL_SUCCESS_MSG = By.xpath(
			"//div[@class='successUpdated'][contains(text(),'The secondary email has been added successfully')]");
	// Information tab
	public static By PERSONAL_INFORMATION_TEXT = By.xpath("//h3//b[text()='Personal information']");
	public static By SHARED_LINK_USER = By.xpath("//div[@id='sharedUserUrl']//a");

	// Check out settings
	public static By getCheckOutSettingsPaymentOptions(String options) {
		return By.xpath("//select[@id='payAvailableSel']//option[contains(text(),'" + options + "')]");
	}

	public static By PAYMENT_ALLOWED_OPTIONS = By
			.xpath("//select[@id='payAllowedSel']//option[contains(text(),'Procurement Card')]");
	public static By PAYMENTS_INFO_LEFT_TO_RIGHT_ARROW = By.xpath("//a[@id='paymentOptionsRightArrowButton']");
	public static By PAYMENTS_OPTION_UPDATE_BTN = By
			.xpath("//div[@id='paymentContents']//a//span[contains(text(),'Update')]");

	public static By loginVerificationForUser(String user) {
		return By.xpath("//div[@class='sidebar-nav']//p[contains(text(),'Welcome back " + user + "')]");
	}

	// webgroup default options
	public static By webGroupOption(String webGroupOption) {
		return By.xpath("//td[text()='" + webGroupOption + "']//following-sibling::td//input");
	}

	public static By UPDATED_MESSAGE = By.xpath("//div[@id='UpdateMsg'][contains(.,'was updated')]");

	public static By DISPLAY_ON_WEB = By.xpath("//div//img[@id = 'webNotificationSort']");
	public static By DISPLAY_ON_WEB_POPUP = By.xpath("//div[@class='webSortPara']");
	public static By CANCEL_BTN = By.xpath("//div//a[@title= 'CANCEL']");
	public static By UPDATE_BUTTN = By.xpath("//div//a[@title= 'UPDATE']");
	public static By REP_POPUP = By.xpath("//div[@id='webNotificationSortPopUp']");
	public static By PRODUCT_EXP = By.xpath("//div//img[@id='productExpImg']");
	public static By PRODUCT_EXP_NOTE = By.xpath("//div[@id='proExpPopUpNote']");
	public static By WEEKDAYS = By
			.xpath("//div[@id='proExpPopUpContainer']//div[@id='proExpPopUpCheckBoxes']//input/parent::div");
	public static By PRODUCT_EXP_CANCEL_BTN = By.xpath("//div//a[@alt='Cancel']//span[contains(text(),'CANCEL')]");
	public static By PRODUCT_EXP_REP_NAMES = By
			.xpath("//div[@id='webSortContent']//div[@class='webSortTextboxes']//input/parent::div");
	public static By PRODUCT_EXP_REP_IDS = By.xpath(
			"//div[@id='webSortContent']//div[@class='webSortTextboxes']//input/parent::div//input[@type='textbox']");
	public static By PRD_EXP_REP_MANAGEWEBGRPPAGE = By.xpath("//div[@id='repDataDiv']//tr[contains(@id,'repData')]");
	public static By SALESREPIMAGES = By.xpath("//div[@id='SalesRep']//table//tbody//tr");

	public static By SalesRepNamesAfterLogin(int i) {
		return By.xpath("(//div[@id='SalesRep']//table//tbody//tr//td//div//label//strong)[" + i + "]");
	}

	public static By getRepNamesOnDisplayWebPopup(int i) {
		return By.xpath("//input[@value='" + i + "']/following-sibling::label");

	}

	public static By getRepValuesOnDisplayWebPopup(int i) {
		return By.xpath("//input[@type='textbox'][@value='" + i + "']");
	}

	// second test case
	public static By COMP_STAND_WIZARD = By.xpath("//div//a[@id='CompStandardWizard']");
	public static By PROD_WEBGRP_NAME = By.xpath("//div//input[@id='cs-ProductNameInput']");
	public static By CREATE_NEW = By.xpath("//div[@class='floatL']//input[@id='cs-CreateNewInput']");
	public static By CONTINUE_BTN = By.xpath("//div//a[@id='cscontinueButton']");
	public static By CONF_SET_NAME = By.xpath("//div//input[@id='cs-configSetNameInput']");
	public static By SRCH_BY_KEYWORD = By.xpath("//div//input[@id='searchCSText']");
	public static By SEARCH_BTN = By.xpath("//div//img[@id='csSearchButton']");
	public static By CATEGORY = By.xpath("//select[@id='cs-catSelectId']");
	public static By PRODUCTMFR = By
			.xpath("(//div[@class='prod-top-section']//p[@class='prod-part-number show-for-medium-up'])[1]");
	public static By ADD_TO_SET = By.xpath("//div[@class='columns small-8']//a");
	public static By CONF_SET_SUCCESS_COMMENT = By.xpath("//div[@id='commentTag' and @class='successUpdated']");
	public static By QTPCATEGORY_LINK = By.xpath("(//div[@class='column medium-10']//a[text()='QTPCategory'])[1]");
	public static By QTPCATEGORY_NAME = By.xpath("//div[@class='cs-CategoryName']//span//input[@id='msCategoryName']");
	public static By CHECK_COLLAPSE = By.xpath("//input[@type='checkbox'][@id='displayCollapsed']");
	public static By UPDT_BTN = By.xpath("//div[@class='buttons ']//a[@id='csUpdateButton']");

	public static By PRD_GRP_NAME(String QTPCategoryTest1, String QTPProductGroup) {
		return By.xpath("//div[@class='column medium-10']//a[text()='" + QTPCategoryTest1
				+ "']/following::div[@class='column medium-10']//a[text()='" + QTPProductGroup + "']");
	}

	public static By PRD_GRP_TO_MODIFY = By
			.xpath("//div[@class='categoryNameInput']//span[2]//input[@id='msCategoryName']");

	public static By PRD_GRP_UPDATE_MSG = By
			.xpath("//p[@id='companyStandardsNotificationDiv' and @class='successUpdated']");
	public static By PRD_GRP_COLLAPSE = By.xpath("//a//span[@class='ui-icon ui-icon-triangle-1-e left']");
	public static By PRD_STANDARDS_PAGE = By
			.xpath("//div[@id='companyStandardsHeading']//h1[text()='Product Standards']");

	// WGP04_NewRep_Action1_Script
	public static By ADD_NEW_REP = By.xpath("//td[@class='webNotificationsAddRep']//a[text()='Add New Rep']");
	public static By ADD_NEW_REP_POPUP = By.xpath("//span[@class='ui-dialog-title'][text()='Add New Rep']");
	public static By EMAIL_ADDRESS = By.xpath("//div[@class='addNewRepTextboxDiv']//input[@id='addNewRepEmailBox']");
	public static By PHONE_NUMBER = By
			.xpath("//div[@class='addNewRepTextboxDiv']//input[@id='addNewRepPhoneNumberBox']");
	public static By FAX_NUMBER = By.xpath("//div[@class='addNewRepTextboxDiv']//input[@id='addNewRepfaxNumBox']");
	public static By BROWSE = By.xpath("");
	public static By REP_UPDATE = By.xpath("//a[@id='repSaveId']//span[text()='UPDATE']");
	public static By REP_ADD_SUCCESS_MESSAGE = By.xpath(
			"//div[@id='addRepSuccessOrError']//div[@class='successUpdated' and text()='New rep has been added successfully']");
	public static By ADDED_REP_NAME_WEBPAGE = By.xpath(
			"(//td[@class='webNotificationsAccountHeadercolCandN']//following-sibling::td//a[contains(@href,'Rachel')])[1]");
	public static By PROD_EXP_CHKBOX = By.xpath(
			"(//td[@class='webNotificationsAccountHeadercolCandN']//following-sibling::td//a[contains(@href,'Rachel')])[1]/..//following-sibling::td//input[contains(@id,'accTeamProGroup')]");
	public static By DISPLAY_ONWEB_CHKBOX = By.xpath(
			"(//td[@class='webNotificationsAccountHeadercolCandN']//following-sibling::td//a[contains(@href,'Rachel')])[1]/..//following-sibling::td//input[contains(@id,'accTeamDispWeb')]");
	public static By SAVE_BTN = By.xpath(
			"(//td[@class='webNotificationsAccountHeadercolCandN']//following-sibling::td//a[contains(@href,'Rachel')])[1]/..//following-sibling::td//img[contains(@alt,'Save')]");
	public static By REP_SAVE_SUCCESS_MESSAGE = By
			.xpath("//div[@id='addRepSuccessOrError']//div[@class='successUpdated' and text()='Saved successfully']");
	public static By DELETE_BTN = By.xpath(
			"(//td[@class='webNotificationsAccountHeadercolCandN']//following-sibling::td//a[contains(@href,'Rachel')])[1]/..//following-sibling::td//img[contains(@alt,'Delete')]");
	public static By REP_DELETE_SUCCESS_MESSAGE = By.xpath(
			"//div[@id='addRepSuccessOrError']//div[@class='successUpdated' and text()='The rep has been removed successfully ']");

	/** To get the hosted licensing permissions**/
	public static By getHostedLicensingPermissions(String permissions){
		return By.xpath("//div[@id='hostedLicensingDashboardPermissionView']//a[contains(text(),'"+permissions+"')]/parent::div/preceding-sibling::div//input");
	}
	
	public static By CREATE_NEW_PROD_GROUP = By.xpath(
			"//div[@class='cs-LeftTemp']//img[@class='frameworkIcons' and @id='CompStandardPlusImage' and @title='Add Category']");
	
	public static By NEW_CATEGORY_NAME = By.xpath("//input[@id='msCategoryName']");
	public static By CREATE_BUTTON = By.xpath("//a[@id='csCreateButton']");
	
	public static By categoryLink(String categoryName) {
		return By.xpath("//div[@class='column medium-10']//a[text()='" + categoryName + "']");
	}
	
	public static By CLICK_CATEGORY = By.xpath("//div[@class='column medium-10']//a[text()='TestCategory']");
	
	public static By DELETE_CATEGORY_LINK = By.xpath("//div[@class='cs-editDeleteButton']//a[contains(text(),' Delete this Category')]");
	public static By CONFIRM_DELETE_CHKBOX = By.xpath("//div[@class='deleteContainer']//input[@class='csDeleteCatCheckbox']");
	public static By UPDATE_TODEL_BTN = By.xpath("//div[@class='buttons']//a[@class='orange']//span[contains(text(),'UPDATE')]");
	public static By PRD_GRP_NEW = By.xpath("//div[@class='cs-ProductNameRight']//span//input[@id='csGroupName']");
	public static By PROD_GRPS_COLLAPSE = By.xpath("//div[@id='CompStandardRightTemplate']//span//h5[contains(text(),'Tips')]");
	
	// Manage current web Group >> Hosted Licensing Administration 
	public static By ADD_MONTH_RADIO_BTN=By.xpath("//div[@id='splaradioButtons']//input[@id='addMonth']");
	public static By MONTH=By.xpath("//select[@id='splaMonth']");
	public static By YEAR=By.xpath("//select[@id='splaYear']");
	public static By TYPE=By.xpath("//select[@id='splaType']");
	public static By ADD_MONTH_BTN=By.xpath("//span[@id='splaUpdateId']");
	public static By USAGE_PERIOD_ADDED_MSG=By.xpath("//div[@id='splaMessage'][contains(text(),'Added a usage period for the soldto.')]");
	public static By SOLD_TO_TXT_BOX=By.xpath("//input[@id='splaSolto']");
	public static By SALES_ORG_TXT_BOX=By.xpath("//input[@id='splaSalesOrg']");
		
		
	// smart trackers
	public static By SMART_TRACKER_LABEL=By.xpath("//div[@id='smartTrackerCoverDiv']//h1[contains(text(),'Manage SmartTrackers')]");
	public static By ADD_A_SMART_TRACKER=By.xpath("//a[@id='SmartTrackAddHeaderP']");
	public static By FIELD_LABEL=By.xpath("//input[@id='fieldName']");
	public static By SAVE_SMART_TRACKER=By.xpath("//a[@id='SmartTrackSaveChangesHeaderP']");
	public static By EDIT_SMART_TRACKER=By.xpath("//a[@id='SmartTrackEditHeaderP']");
	public static By MAKE_INACTIVE_CHECK_BOX=By.xpath("//input[@name='makeInactive']");
	public static By INACTIVE_SMART_TRACKER_ERROR=By.xpath("//span[@class='alertError'][contains(text(),'Inactive SmartTracker')]");
	public static By SMART_TRACKER_FIELD_TYPE_DD=By.xpath("//select[@id='fieldTypeId']");
	public static By SAVE_EDIT_SMARTTRACKER=By.xpath("//a//span[contains(text(),'Save Changes')]");
	public static By getSmartTrackerstabs(String tabName){
		return By.xpath("//div[@class='manageMenulabels']//b[contains(text(),'"+tabName+"')]");
	}
	// smart trackers headers
	public static By getManageSmartTrackersHeaders(String headerName){
		return By.xpath("(//li//a[contains(text(),'"+headerName+"')])[1]");
	}
			
	public static By getSmartTrackerLabel(String label){
		return By.xpath("//span[@class='STSpans'][contains(text(),'"+label+"')]");
	}
	
	public static By SMART_TRACKER_EXPEND=By.xpath("//span[@id='expandSpan']");
	
	// Linked Accounts
	public static By LINKED_ACCOUNTS_SEARCH=By.xpath("//input[@id='linkedAccountsSearchInput']");
	public static By SEARCH_ICON=By.xpath("//a[@title='Go']//img[@class='imageLocation frameworkIcons']");
	public static By LINKED_ACCOUNT_DD=By.xpath("//select[@id='LinkedAccountSelectOption']");
	public static By ACCOUNT_NAME=By.xpath("//tr[@id='linkedAccountsResultsResultsRow']//td[2]");
	public static By REMOVE_DEFAULT_LINK=By.xpath("//a[contains(text(),'Remove Default')]");
	public static By NO_DEFAULT_ACCOUNT_DETAILS=By.xpath("//div[@class='linkedAccountsDefaultSoldToDetails'][contains(.,' No Default Account set for this User')]");
	public static By DEFAULT_ACCOUNT_ADDRESS=By.xpath("//div[@id='defaultSoldToDetailsDivId']");
	public static By LEFT_PAGING=By.xpath("//a[contains(text(),' |<')]");
	public static By END_PAGE=By.xpath("//span[@id='showingEnd']");
	public static By SELECTED_RESULTS_PER_PAGE=By.xpath("//select[@id='shownRecords']//option[@selected='selected']");
		
	public static By getLinkedAccountsCheckBox(String accountNum){
		return By.xpath("//input[@id='"+accountNum+"_linkedAccountCheckBox']");
	}
	
	public static By getLinkedAccountCheckBoxByIndex(String i){
		return By.xpath("(//table[@id='linkedAccountsResults']//td//input[@type='checkbox'])["+i+"]");
	}
	
	public static By getDefaultLoginByIndex(String i){
		return By.xpath("(//input[@name='defaultLogin'])["+i+"]");
	}
	
	public static By LINKED_ACCOUNT_CHECKBOX=By.xpath("//table[@id='linkedAccountsResults']//td//input[@type='checkbox']");
	
	public static By LinkedAccountHeaders(String header){
		return By.xpath("//div[@class='linkedAccountsResultsDiv']//tr//th[contains(text(),'"+header+"')]");
	}
	
	public static By getDefaultLoginRadioButton(String accountNum){
		return By.xpath("//input[@id='"+accountNum+"_linkedAccountRadioButton']");
	}
	
	public static By UPDATEUSER_BTN=By.xpath("//a[@title='Update User']");
	public static By LINKED_ACCOUNT_UPADTE_MSG=By.xpath("//span[@id='linkedAccountsUpdateMessageText']");
	//payment options
	public static By SUCESS_MESSAGE_PAYMENT_OPTIONS=By.xpath("//div[@id='checkoutSettUpdateMsg'][contains(.,'Payment options updated successfully.')]");
    public static By paymentAllowedOption(String option) {
	return By.xpath("//select[@id='payAllowedSel']//option[contains(text(),'"+option+"')]");		
    }
    public static By ALL_PAYMENT_OPTIONS=By.xpath("//select[@id='payAllowedSel']//option");
    public static By ALLOWED_OPTION=By.xpath("//select[@id='payAllowedSel']");
    public static By PAYMENTS_LEFT_ARROW_BUTTON=By.xpath("//a[@id='paymentOptionsLeftArrowButton']");
    public static By DEFAULT_PAYMENT_OPTION=By.xpath("//select[@id='defaultPayTypeSelID']");
    
    public static By FORGET_PASSWORD_LINK=By.xpath("//a[@id='show-forgot-password']");
    public static By FORGET_PASSWORD_MAIL_ENTER_TEXTFIELD=By.xpath("//input[@id='forgot-password-email']");
    public static By FORGET_PASSWORD_SUBMIT_BUTTON=By.xpath("//a[@id='forgot-password-submit']");
    public static By FOGET_PASSWORD_ERROR_MESSAGE=By.xpath("//span[@class='input-error']");
    public static By FORGET_PASSWORD_RESET_SUCESS_MESSAGE=By.xpath("//span[@class='successUpdated']");
    
    public static By SALES_REP_NAMES=By.xpath("//table[@id='webNotificationsAccount']//tbody//tr//td[2]//a");	
    public static By clientNotifiationDeleteIcon(String repMail) {
    	return By.xpath("//b[text()='"+repMail+"']//ancestor::td[contains(@class,'accRepSecondEmail')]//..//a");
    }
    
    public static By RESET_PASSWORD_LINK=By.xpath("//a//span[text()='Reset Password']");
    
    public static By DEFAULT_LOGIN_LINKED_ACCOUNTS=By.xpath("//table[@id='linkedAccountsResults']//tbody//tr[3]//td[6]//input");
   public static By ADD_NEW_USER_LINK=By.xpath("//div[@id='addNewUserButton']//a");
   public static By USER_TYPE_DROPDOWN=By.xpath("//select[@id='userTypeSelectId']");
   public static By USER_NAME_FIELD=By.xpath("//input[@id='userName']");
   public static By CHECK_AVAILABLITY_BUTTON=By.xpath("//a[@id='checkBtn']");
   public static By CREATE_USER_BUTTON=By.xpath("//a[@title='Create']");
   public static By ERROR_MESSAGE=By.xpath("//div[@id='ErrorMsg']");
   public static By USER_URL=By.xpath("//div[@id='UserURLUrl']//a");
   public static By CREATE_AN_ACCOUNT_PAGE=By.xpath("//h1[text()='Create an Account']");
   public static By EMAIL=By.xpath("//input[@id='email']");
   public static By FIRST_NAME=By.xpath("//input[@id='firstName']");
   public static By LAST_NAME=By.xpath("//input[@id='lastName']");
   public static By PHONE_NUMBER_CREATE_ACC=By.xpath("//input[@id='phoneNumber']");
   public static By USER_NAME=By.xpath("//input[@id='userName']");
   public static By CHECK_AVAILABILITY=By.xpath("//a[@id='check-username']");
   public static By PASSWORD=By.xpath("//input[@id='password']");
   public static By CONFIRM_PASSWORD=By.xpath("//input[@id='confirmPassword']");
   public static By CREATE=By.xpath("//a[@id='create']");
   public static By WELCOME_PAGE=By.xpath("//div[@class='welcomePageUserInfoDivStyle']");
   public static By USER_NAME_MESSAGE=By.xpath("//span[@id='username-message' and text()='Username Not Available']");
   public static By SEARCH_USER_BY_EMAIL=By.xpath("//input[@id='userInput']");
   public static By AVAILABLE_MESSAGE=By.xpath("//div[@id='loginAvailable' and text()='Available']");
   public static By BILLING_ACCOUNT_NAME=By.xpath("//input[@id='companyName']");
   public static By ADRESSES=By.xpath("//input[@id='addressOne']");
   public static By CITY=By.xpath("//input[@id='city']");
   public static By selectState() {
	   return By.xpath("//select[@id='state']");
   }
   public static By ZIP_CODE=By.xpath("//input[@id='zipCode']");
   public static By SAVE_AND_CONTNUE=By.xpath("//a[@title='CONTINUE']");
   
    // Linked Accounts Link
	public static By LINKED_ACCOUNTS_SEARCH_FIELD=By.xpath("//input[@id='soldToManagementSearchInput']");
	public static By SEARCH_ICON_LINKED_ACC=By.xpath("//div[@class='soldToManagementSearchCriteria ']//a");
	
	
	
	public static By getDefaultLoginRadioButtonInLinkedAccounts(String accountNum){
		return By.xpath("//input[@id='"+accountNum+"_radioButton']");
	}
	
	public static By UPDATE_USER_BTN_LINKED_ACCOUNT=By.xpath("//a[@title='UPDATE']");
	public static By ERROR_MESSAGE_CREATE_ACCOUNT=By.xpath("//div[contains( text(),'Please')]");
	public static By USER_TYPE=By.xpath("//select[@id='userUserType']");
	public static By FIRST_USER_LINK=By.xpath("//td[@id='userName']//a");
	
	public static By PAGINATION=By.xpath("//a[contains(text(),'>>')]");

	
	public static By ERR_MSG=By.xpath("//div[@id='ErrorMsg']");
	public static By EMAILFORMAT_DD=By.xpath("//select[@id='emailFormat']");
	public static By TEXTAREA=By.xpath("//textarea[@id='notesAboutUserID']");
    public static By BACKTOUSERSEARCH_LINK=By.xpath("//div[@id='createNewUserContainer']//span/a");
	public static By emailSearchResultOfCreatedUser(String Email){
		return By.xpath("//td[@id='login']//div[contains(text(),'"+Email+"')]");
	}
	public static By EMAILADDRESS=By.xpath("//input[@id='emailAddress']");
	public static By SHAREDUSERURL=By.xpath("//div[@id='sharedUserUrl']/a");
	public static By ERRORMSG_CREATEUSER=By.xpath("//div[@id='ErrorMsg']");
	public static By ORDERSPLACED_CHECKBOX=By.xpath("//input[@id='receiveEmailonOrders']");
	public static By QUOTESPLACED_CHECKEDBOX=By.xpath("//input[@id='receiveEmailonQuotes']");
	public static By EMAILINADDTIONALNOTIFICATION_CHECKOUTSETTINGS= By.xpath("//div[@id='emailAddress1']/input");
	public static By DEFAULTBILLING_ADDRESS= By.xpath("//div[@id='defaultBillingAddrDiv']");
	public static By ACTION_DD_WEBLIST=By.xpath("//select[@id='userActionSelectOption']");
	public static By SELECT_USERUPDATE_STATUS=By.xpath("//select[@id='userSelectedStatus']");
	public static By UPDATE_BTN_INPOPUP=By.xpath("//span[contains(text(),'update')]/parent::a");
	public static By USERUPDATED_SUCCESS_MSG=By.xpath("//div[@id='usersUpdateMessageDiv']");
	public static By userCheckBox(String Email){
		return By.xpath("//table[@id='userSearchResultsTable']/tbody/tr/td[@id='login']/div[contains(text(),'"+Email+"')]/parent::td/parent::tr/td[@id='userCheckbox']/input");
	}
}    
   

   
   
