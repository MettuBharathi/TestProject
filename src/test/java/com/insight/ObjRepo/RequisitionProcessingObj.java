package com.insight.ObjRepo;

import org.openqa.selenium.By;

public class RequisitionProcessingObj extends CommonObj {
	public static By APPROVAL_MANAGEMENT_PAGE = By
			.xpath("//div[@id='static_cont']//h2[contains(text(),' Approval Path Management')]");
	public static By TESTRULES_EDIT_LINK = By
			.xpath("//td[@id='reqname' and contains(text(),'TestRules')]/..//td/a[contains(text(),'Edit')]");
	public static By TEST_APPROVAL_RULES_BTN = By.xpath("//a//span[contains(text(),'Test Approval Rules')]");
	public static By AMOUNT = By.xpath("//div[@class='width70 fll']//input[@id='test_currency']");
	public static By CART_TYPE = By.xpath("//div[@class='width70 fll']//select[@id='test_cartType']");
	
	public static By createCartType(String item) {
		return By.xpath("(//tr[@class='table_row']//td[@class='opt_rul_col_cart col3Header']//select[@class='cartTypeText normal1'])[" + item + "]");
	}
	
	public static By createMinAmt(String item) {
		return By.xpath("(//tr[@class='table_row']//td[@class='opt_rul_col_currency col4Header']//input[@id='currRange1'][@class='minAmt'])[" + item + "]");
	}
	
	public static By createMaxAmt(String item) {
		return By.xpath("(//tr[@class='table_row']//td[@class='opt_rul_col_currency col4Header']//input[@id='currRange2'][@class='maxAmt'])[" + item + "]");
	}
	
	public static By createPath(String item) {
		return By.xpath("(//tr[@class='table_row']//select[@class='approvalPathId normal'])[" + item + "]");
	}
	
	public static By saveRule(String item) {
		return By.xpath("(//tr[@class='table_row']//div[@class='createOptions']//a[@id='optrule_create_button']//img[@alt='create'])[" + item + "]");
	}
	
	public static By sureDelete(String item) {
		return By.xpath("(//a//span[contains(text(),'Delete')])[" + item + "]");
	}
	
	public static By deleteRule(String item) {
		return By.xpath("(//tr[@class='table_row']//div[@class='updateOptions']//a[@id='optrule_cncl_button']//img[@alt='create'])[" + item + "]");
	}
	
	public static By CREATE_RULE_CART_TYPE = By
			.xpath("//tr[@class='table_row']//td[@class='opt_rul_col_cart col3Header']//select[@class='cartTypeText normal1']");
	public static By SHOW_RESULTS_BTN = By.xpath("//a[@class='orange']//span[contains(text(),'SHOW RESULTS')]");
	public static By RESULT_PATH1 = By.xpath("//div[@id='test_app_path']//div[contains(text(),'path1')]");
	public static By RESULT_PATH2 = By.xpath("//div[@id='test_app_path']//div[contains(text(),'path2')]");
	public static By RESULT_PATH3 = By.xpath("//div[@id='test_app_path']//div[contains(text(),'path3')]");

	public static By getResultPath(String path) {
		return By.xpath("//div[@id='test_app_path']//div[contains(text(),'" + path + "')]");
	}

	public static By getNoApprovalResultPath(String path) {
		return By.xpath("//div[@id='test_app_path' and contains(text(),'" + path + "')]");
	}

	public static By OPTIONAL_RULES_LINK = By.xpath("//span[@id='li-OptionalRules']");

	public static By BACKTO_REQSEARCH = By.xpath("//span[@id='backtoReqSearch']//a");

	public static By SMARTTRACKERRULES_EDIT_LINK = By
			.xpath("//td[@id='reqname' and contains(text(),'SmartTrackerRules')]/..//td/a[contains(text(),'Edit')]");

	public static By CLOSE_TEST_APPROVAL = By
			.xpath("//span[@class='ui-icon ui-icon-closethick'][contains(text(),'close')]");

	public static By ADD_RULE = By
			.xpath("//div[@class='buttons addChoice']//a[@class='grey right']//span[contains(text(),'ADD RULE')]");

	public static By MIN_AMT = By.xpath(
			"//tr[@class='table_row']//td[@class='opt_rul_col_currency col4Header']//input[@id='currRange1'][@class='minAmt']");
	public static By MAX_AMT = By.xpath(
			"//tr[@class='table_row']//td[@class='opt_rul_col_currency col4Header']//input[@id='currRange2'][@class='maxAmt']");
	public static By APPROVAL_PATH = By.xpath("//tr[@class='table_row']//select[@class='approvalPathId normal']");
	public static By SAVE_RULE = By.xpath("//tr[@class='table_row']//div[@class='createOptions']//a[@id='optrule_create_button']//img[@alt='create']");
	public static By DELETE_RULE = By.xpath("//tr[@class='table_row']//div[@class='updateOptions']//a[@id='optrule_cncl_button']//img[@alt='create']");
	public static By RULE_SUCCESS_CREATE_MSG = By.xpath("//div[@id='mesg_bar']//p[contains(text(),'Routing rule successfully created')]");
	public static By SURE_DELETE = By.xpath("//a//span[contains(text(),'Delete')]");
	
	//Create Rules 
	public static By CREATERULES_EDIT_LINK = By
			.xpath("//td[@id='reqname' and contains(text(),'Create Rules')]/..//td/a[contains(text(),'Edit')]");
	
	
	public static By WITHRULE_LISTING_FACTOR = By.xpath("//select[@id='routing-selbox']");
	
	public static By SMARTRULE_LISTING_OPTION = By.xpath("//select[@id='smartTrackerNameList']");
	
	public static By ADD_ROUTE_BTN = By.xpath("//a//span[contains(text(),' ADD ROUTE ')]");
	
	public static By HDLLIST_OPTION = By.xpath("//ul[@id='sortable']//tr[@class='table_row']//div[@id='selbox_ST']//select[@class='smartTrackerName normal1']");
			
	public static By ADD_RULE_WITH_LIST = By.xpath("//tr[@id='addChoiceButton1']//a[@class='grey left']//span[contains(text(),'ADD RULE')]");
	
	public static By DELETE_RULE_WITH_LIST = By.xpath("//tr[@class='table_row']//div[@class='updateOptions flr']//a[@id='optrule_cncl_button']//img[@alt='create']");
	
	public static By createListValue(String item) {
		return By.xpath("(//tr[@class='table_row']//select[@class='opt_rul_col_lis selectWide normal1'])[" + item + "]");
	}
	
	public static By createApprovalPathWithList(String item) {
		return By.xpath("(//tr[@class='table_row']//select[@class='selectWide approvalPathId normal'])[" + item + "]");
	}
	
	public static By saveRuleWithList(String item) {
		return By.xpath("(//tr[@class='table_row']//div[@class='createOptions flr']//a[@id='optrule_create_button']//img[@alt='create'])[" + item + "]");
	}
	
	public static By DENY_RADIOBTN = By.xpath("//input[@id='denyRadioButton']");
	public static By TEXT_BOX = By.id("srTextAreaComments");
	public static By SEARCH_HDR = By.xpath("//h2[contains(text(),'Requisition Search')]");
	public static By STATUS_DROPDOWN = By.xpath("//select[@id='approval_status']");
	public static By REFERENCE_TEXTBOX = By.id("ref_num");
	public static By SERACH1_BTN = By.xpath("//div[@id='advSearch']/preceding::div[@class='buttons']/a/span");
	public static By REQ_GRP_DROPDOWN = By.xpath("//span[@id='react-select-6--value']/div[@class='Select-placeholder'][contains(text(),'Select Requestor Group...')]");
	public static By PHNMBR_TEXTBOX = By.name("existingAddressAttention.phone");
	public static By REQ_GRP_DROPDOWN_TYPE = By.xpath("//span[@id='react-select-6--value']//div[@class='Select-input']//input");
	public static By PO_RELEASE = By.xpath("//input[@id='porelNumber']");
	public static By PO_NUMBER = By.xpath("//input[@id='poNumber']");
	public static By VERIFY_PONUM(String refNum){
		return By.xpath("//table[@id='reqSearch']/tbody/tr/td/a[contains(text(),'"+refNum+"')]/following::td");
		
	}
	public static By VERIFY_PO_RELEASE(String refNum){
		return By.xpath("//table[@id='reqSearch']/tbody/tr/td/a[contains(text(),'"+refNum+"')]/following::td[2]");
		
	}
	public static By NEW_CARD = By.xpath("//div[@class='insightPaymentFormLabel']/a[contains(text(),'Enter New Card')]");
	public static By CARD_NMBR = By.id("insightPaymentCardNum");
	public static By CARD_NAME = By.id("insightPaymentNameOnCard");
	public static By CARD_TYPE = By.id("insightPaymentcardType"); //VISA
	public static By CARD_MONTH = By.id("insightPaymentExpMonth");
	public static By CARD_YEAR = By.id("insightPaymentExpYears");
	public static By CARD_ERRORMESSAGE= By.xpath("//div[@class='form__field-msg form__field-msg--error'][contains(text(),'Card number is required')]");
	public static By PONumInOrderHistory(String refNum){
		return By.xpath("//td[contains(text(),'"+refNum+"')]/preceding-sibling::td/div/following::td[3]");
	}
		public static By APPROVAL_MNGMNT2 = By.xpath("//span[@id='reqlink']/following::div/h1");
		public static By REQUESTOR_GROUPTAB = By.xpath("//a[@id='idRequestorGroups'][contains(text(),'Requestor Group')]");
		public static By REQUESTOR_GROUPHDR = By.xpath("//h2[contains(text(),' Requestor Group Management')]");
		public static By RequestorGroupEditBtn(String GroupName){
			return By.xpath("//td[@id='reqname'][contains(text(),'"+GroupName+"')]/following::td[4]/a");
		}
		public static By CREATE_EDITREQUESTOR_GROUPHDR = By.xpath("//h2[contains(text(),'Create/Edit Requestor Group')]");
		public static By APPROVEPATHSETTINGS_TAB = By.xpath("//span[@id='li-ApprovalPathSettings']/a");
		public static By NO_RADIOBTN = By.xpath("//input[@id='fpaNo']");
		public static By SAVE_CHANGESBTN = By.xpath("//a[@id='ApathSettngs_update']/span[contains(text(),'Save Changes')]");
		public static By SUCCESSFUL_MESSG = By.xpath("//div[@id='updateAppPath_message']/p[contains(text(),'Successfully updated approval path settings')]");
		public static By checkboxes(int i){
		              return By.xpath("(//input[@name='chk'])["+i+"]");
}
		public static By CHECK_BOX= By.xpath("//input[@name='chk']");
		public static By EDIT_PAYMENT= By.id("editPaymentInfo");
		public static By EDIT_CARRIER= By.id("editShippingOptions");
		public static By EDIT_SHIPPING= By.id("editShippingTo");
		public static By EDIT_BILLING= By.id("editBillTo");
		public static By CUSTOM_FIELDS= By.id("arCustomField");
		public static By EDITCART_CONTENTS= By.xpath("//div[@id='jelaEditCart']/span/following::a[contains(.,'Edit Cart Contents')]");
		public static By CREDITCARD_TYPE= By.id("payMethodOptions");
		public static By UPDATE_BTN_PAYMENT = By.xpath("//div[@class='buttons']/a/span[contains(text(),'UPDATE')]");
		public static By BACK_BTN_PAYMENT = By.xpath("//div[@class='buttons']/a/span[contains(text(),'BACK')]");
		public static By VERIFY_CARDTYPE = By.xpath("//div[@class='srPayment']/div/following::div[contains(.,'Procurement Card')]");
		public static By CHANGE_SHIPPINGOPTION = By.xpath("(//td[@class='colUseAddress']/a/img[@class='frameworkIcons'])[1]");
		public static By STATUS_MESSAGE = By.xpath("//input[@id='payType']/following::p[contains(text(),'Successfully Updated')]");
		public static By BILLING_HDR = By.xpath("//span[@id='ui-id-1']");
		public static By BILLING_CLOSEICON = By.xpath("//span[@class='ui-icon ui-icon-closethick'][contains(.,'close')]");
		public static By EDIT_ICON = By.xpath("//div[@id='editCart']/div/a/img[@alt='Edit Icon']");
		public static By UPDATE_QTY= By.xpath("//input[@name='contmatId']");
		public static By SAVE_ICON= By.xpath("//div[@id='cancelChanges']/div/a/img[@alt='save Icon']");
		public static By APPLY_CHANGES= By.xpath("//div[@id='checkOutDiv']/a/following::a/span[contains(text(),'APPLY CHANGES')]");
		public static By QNTY_INCARTITEMS= By.xpath("//div[@class='qtydiv']");
		public static By createtabNames(String tabName){
			return By.xpath("//span[@id='"+tabName+"']/a");
		}
		public static By REQUESTOR_AVAILABLE(String GroupName) {
				return By.xpath("//select[@id='AvailRequestorsList']//option[contains(text(),'"+GroupName+"')]");
		}
		public static By REQUESTOR_SAVEBTN= By.xpath("//a[@id='manageReqUpdate']/span[contains(text(),'Save Changes')]");
		public static By REQUESTOR_SELECTED(String GroupName) {
			return By.xpath("//select[@id='requestorGroupList']//option[contains(text(),'"+GroupName+"')]");
	}
		public static By RIGHT_ARROW= By.xpath("//a[@id='manageReq_LtoR']");
	
		public static By CANCEL_REQSTNBTN= By.xpath("//a[@id='cancelReq']/span[contains(text(),'CANCEL REQUISITION')]");
		public static By CANCELTN_STATUSMESSAGE= By.xpath("//div[@id='serach_msgbar2'][contains(text(),'Requisition Status Update Successful')]");
		public static By EXPANDSEARCH= By.xpath("//div[@id='expndsearch1']");
		
		
		
		
	
}
