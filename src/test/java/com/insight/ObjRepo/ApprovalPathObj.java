package com.insight.ObjRepo;

import org.openqa.selenium.By;

public class ApprovalPathObj extends CommonObj {
	public static By CREATE_APP_PATH = By.xpath("(//span[@class='right']//a)[1]");
	public static By APPROVAL_PATH_NAME = By.xpath("//input[@id='approvalPath_name_text']");
	public static By CREATE_APPROVALPATH_BTN = By
			.xpath("//a[@id='CreateApprovalPath_ok_button']//span[contains(text(),'Create Approval Path')]");
	public static By ALL_APPROVER_OPTIONS = By.xpath("//select[@id='ListFrom']//option");

	public static By selectAppNameFromList(String appName) {
		return By.xpath("//select[@id='ListFrom']//option[contains(text(),'" + appName + "')]");
	}

	public static By SELECT_PATH_BUTTON = By.xpath("//a[@class='orange'][@alt='LtoR_button1']");
	public static By SELECT_REMOVEPATH_BUTTON = By.xpath("//a[@class='orange'][@alt='RtoL_button1']");

	public static By getCreatedApproverPath(String appName) {
		return By.xpath("//table[@id='apptempl']//td[@class='Cl_AppPathName'][contains(text(),'" + appName + "')]");
	}

	public static By getCreatedApproverPathEditLink(String appName) {
		return By.xpath("//table[@id='apptempl']//td[@class='Cl_AppPathName'][contains(text(),'" + appName
				+ "')]/..//td[@class='Cl_Options ']//a[contains(text(),'Edit')]");
	}

	public static By UPDATE_APPROVER_BTN = By
			.xpath("//a[@class='orange'][@id='CreateApprovalPath_ok_button'][span[contains(text(),'Update')]]");
	public static By APPROVERS_LIST_ADDED = By.xpath("//div[@id='listToBox']//ol[@id='ListTo']//li");

	public static By CANCEL_BUTTON = By
			.xpath("//a[@id='CreateApprovalPath_cancel_button'][span[contains(text(),'Cancel')]]");
	public static By CANCEL_ON_ERROR_PAGE = By
			.xpath("//a[@id='ErrorApprovalPath_cancel_button'][span[contains(text(),'Cancel')]]");

	public static By selectAppNameFromListToRemove(String appName) {
		return By.xpath("//div[@id='listToBox']//ol[@id='ListTo']//li[contains(text(),'" + appName + "')]");
	}

	public static By getCreatedApproverPathDeleteLink(String appName) {
		return By.xpath("//table[@id='apptempl']//td[@class='Cl_AppPathName'][contains(text(),'" + appName
				+ "')]/..//td[@class='Cl_Options ']//a[contains(text(),'Delete')]");

	}

	public static By CONFIRM_DELETE = By.xpath(
			"//div[@class='ui-dialog-content ui-widget-content']//div[@class='buttons dbDiv']//a//span[contains(text(),'Delete')]");

	public static By Confirm_Delete_Path(String pathName) {
		return By.xpath("//div[@class='ui-dialog-content ui-widget-content' and contains(text(),'" + pathName
				+ "')]//div[@class='buttons dbDiv']//a//span[contains(text(),'Delete')]");
	}

	public static By SEARCH_TXTBOX = By.xpath("//div[@class='inp_searchapp']//input[@id='search_path_text']");
	public static By SEARCH_BTN = By.xpath("//div[@class='buttons']//a[@id='search_path_button']");
	public static By DELETE_SUCCESS = By.xpath("//p[@class='noticeAlert'][contains(text(),'No results were found')]");
	public static By SUCCESS_DELETE_MSG = By.xpath(
			"//div[@id='ApprovalPathCont']//div[@class='successUpdated' and contains(text(),'Approval path was deleted successfully')]");
	public static By SUCCESS_UPDATE_MSG = By.xpath(
			"//div[@id='ApprovalPathCont']//div[@class='successUpdated' and contains(text(),'Successfully edited approval path')]");
	public static By APP_LNAME_TEXTBOX = By.xpath("//div[@class='inp_searchapp']//input[@id='search_lastname_text']");
	public static By LAST_NAME_SEARCH_BTN = By.xpath("//a[@id='search_lastname_button']");

	public static By GET_APPROVALPATH_COUNT = By.xpath("//table[@id='apptempl']//tbody//tr");
	public static By GET_FIRST_APP_PATH_NAME = By.xpath("(//table[@id='apptempl']//td[@class='Cl_AppPathName'])[1]");
	public static By GET_NUMOF_APPROVERS = By.xpath("(//table[@id='apptempl']//td[@class='Cl_NumApprover'])[1]");
	public static By ERROR_POPUP_MSG = By.xpath("//div[@id='errorPopUp']//p[contains(text(),'have open/pending')]");
	public static By PENDING_REQ_COUNT = By.xpath("//table[@id='table1']//tbody//tr");
	public static By VIEW_ALL_IMG_ICON = By.xpath("//div[@class='inner_row2 view_link']//a//img");

	public static By getAppPathName(String i) {
		return By.xpath("(//table[@id='apptempl']//td[@class='Cl_AppPathName'])[" + i + "]");
	}

	public static By getApproverCount(String i) {
		return By.xpath("(//table[@id='apptempl']//td[@class='Cl_NumApprover'])[" + i + "]");
	}

	public static By REPORTS_LINK = By.xpath("//ul[@id='tab_ul']//li[@id='reportli']");

	public static By REQUISITION_STAUS_REPORT = By.xpath(
			"//div[@id='reportsMainDiv']//div[@id='requiStatus']//a[contains(text(),'Requisition Status Report')]");
	public static By REQUESTOR_GROUP_USERS = By.xpath(
			"//div[@id='reportsMainDiv']//div[@id='requestorGrpUser']//a[contains(text(),'Requestor Group Users')]");
	public static By REQUESTOR_GROUPRULES_REPORT = By.xpath(
			"//div[@id='reportsMainDiv']//div[@id='grpRules']//a[contains(text(),'Requestor Group Rules Report')]");
	public static By APPROVAL_PATH_REPORT = By.xpath(
			"//div[@id='reportsMainDiv']//div[@id='aprroversReport']//a[contains(text(),'Approval Path Report')]");
	public static By APPROVER_OUT_REPORT = By
			.xpath("//div[@id='reportsMainDiv']//div[@id='appOutReport']//a[contains(text(),'Approver Out Report')]");
	public static By REQUESTOR_GROUP_SMARTTRACKER_REPORT = By.xpath(
			"//div[@id='reportsMainDiv']//div[@id='stReport']//a[contains(text(),'Requestor Group SmartTracker Report')]");

	public static By REPORTS_DIV = By.xpath("//div[@id='reportsMainDiv']");

	public static By GET_APPROVALPATH_REPORT_COUNT = By.xpath("//table[@id='appreport']//tbody//tr");

	public static By getAppPathReportDetails(String i) {
		return By.xpath("(//table[@id='apptempl']//td[@class='Cl_AppPathName'])[" + i + "]");
	}

	public static By getApprovalPathDetails(String col, String row) {
		return By.xpath(
				"(//table[@id='appreport']//tbody//tr//td[@class='verticalAlignTop'][" + col + "])[" + row + "]");
	}

	public static By getReqGroupDetails(String col, String row) {
		return By
				.xpath("(//table[@id='reqtable']//tbody//tr//td[@class='verticalAlignTop'][" + col + "])[" + row + "]");
	}

	public static By getReqRulesDetails(String col, String row) {
		return By.xpath(
				"(//table[@id='rulesTable']//tr[" + col + "]//td//div[@class='rulesSubTableComumn'])[" + row + "]");
	}

	public static By APP_PATH_REPORT_PAGE_TITLE = By
			.xpath("//h2[@class='subtitle'][contains(text(),'Approval Path Report ')]");

	public static By REQUESTOR_GRP_LINK = By.xpath(
			"//ul[@id='tab_ul']//li[@aria-labelledby='idRequestorGroups']//a[contains(text(),'Requestor Group')]");

	public static By clickRequestorGrpEdit(String appPathName) {
		return By.xpath("//table[@id='requestor_search_table']//td[text()='" + appPathName
				+ "']/..//td//a[contains(text(),'Edit')]");
	}

	public static By getApprovalPathNameLink(String appPathName) {
		return By.xpath(
				"//table[@id='appreport']//td[@class='verticalAlignTop']//a[contains(text(),'" + appPathName + "')]");
	}

	public static By SAVE_CHANGES_BTN = By.xpath("//a[@id='nameDescUpdate']//span[contains(text(),'Save Changes')]");

	public static By GENERAL_SETTINGS_LINK = By.xpath(
			"//ul[@id='tab_ul']//li[@aria-labelledby='idGeneralSetting']//a[contains(text(),'General Settings')]");

	public static By CREATE_LINK = By.xpath("//table[@id='createApproveTableId']//td//a[@title='Create']");

	public static By REQ_REJECTION_DESC_TXTBOX = By
			.xpath("//table[@id='rejectionTypesId']//tbody//tr//td//input[@id='requestRejectDiscription']");

	public static By REQ_REJ_ADD_LINK = By
			.xpath("//table[@id='rejectionTypesId']//tbody//tr//td//a//img[@alt='Delete']");

	public static By modifyRejDesc(String description) {
		return By.xpath("//table[@id='rejectionTypesId']//tbody//tr//td//input[@value='" + description + "']");
	}

	public static By getReqRejTypeDesc(String description) {
		return By.xpath("//table[@id='rejectionTypesId']//tbody//tr//td[text()='" + description + "']");
	}

	public static By editRejType(String description) {
		return By.xpath("//table[@id='rejectionTypesId']//tbody//tr//td[text()='" + description
				+ "']/..//td//a//img[contains(@src,'Deep_Edit')]");
	}

	public static By saveNewRejType(String description) {
		return By.xpath("//table[@id='rejectionTypesId']//tbody//tr//td//input[@value='" + description
				+ "']/../..//td//img[contains(@src,'Deep_Save')]");
	}

	public static By deleteNewRejType(String description) {
		return By.xpath("//table[@id='rejectionTypesId']//tbody//tr//td[text()='" + description
				+ "']/..//td//a//img[contains(@src,'Deep_Delete')]");
	}

	public static By ALL_APPROVER_NAME_OPTIONS = By.xpath("//select[@id='oofApproverName']//option");

	public static By createReplacementType() {
		return By.xpath("//select[@id='oofreplacementName']");
	}

	public static By createApproverType() {
		return By.xpath("//select[@id='oofApproverName']");
	}
	
	public static By createRequestorType() {
		return By.xpath("//select[@id='AvailRequestorsList']");
	}

	public static By createRepApproverType() {
		return By.xpath("//select[@id='oofReplacementApprover']");
	}

	public static By selectApprover(String id) {
		return By.xpath("//*[@id='oofApproverName']//option[@value='" + id + "']");
	}
	
	public static By ALL_REQUESTOR_OPTIONS = By.xpath("//select[@id='AvailRequestorsList']//option");
	
	public static By selectRequestor(String id) {
		return By.xpath("//*[@id='AvailRequestorsList']//option[@value='" + id + "']");
	}
	
	public static By selectRequestorFromAddedList(String name) {
		return By.xpath("//select[@id='requestorGroupList']//option[contains(text(),'"+name+"')]");
	}
	
	public static By selectRequestorType() {
		return By.xpath("//select[@id='requestorGroupList']");
	}
	
	public static By selectRequestorByText(String requestorName){
		return By.xpath("//select[@id='AvailRequestorsList']//option[contains(text(),'"+requestorName+"')]");
	}

	public static By selectRepApprover(String id) {
		return By.xpath("//*[@id='oofReplacementApprover']//option[@value='" + id + "']");
	}

	public static By ALL_REPLACEMENT_APPROVER_NAME_OPTIONS = By.xpath("//select[@id='oofReplacementApprover']//option");

	public static By DISABLED_REPLACEMENT_APPROVER = By.xpath("//select[@id='oofReplacementApprover' and @disabled]");

	public static By START_DATE_CALENDER = By
			.xpath("//td//input[@id='oofStart_dt']/..//img[@class='ui-datepicker-trigger']");

	public static By END_DATE_CALENDER = By
			.xpath("//td//input[@id='oofEnd_dt']/..//img[@class='ui-datepicker-trigger']");
	public static By START_DATE_TXTBOX = By.xpath("//td//input[@id='oofStart_dt']");
	public static By END_DATE_TXTBOX = By.xpath("//td//input[@id='oofEnd_dt']");
	public static By NEXT_MONTH_ARROW = By.xpath(
			"//div[@class = 'ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']//a//span[contains(text(),'Next')]");
	public static By PREV_MONTH_ARROW = By.xpath(
			"//div[@class = 'ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']//a//span[contains(text(),'Prev')]");
	public static By ADD_IMG_BTN = By
			.xpath("//table[@id='createApproveTableId']//td//a//img[contains(@src,'Deep_Add')]");

	public static By verifyApproverOutCreated(String approverName) {
		return By.xpath("//table[@id='createApproveTableId']//tbody//tr//td[text()='" + approverName + "']");
	}

	public static By editApprover(String approverName) {
		return By.xpath("//table[@id='createApproveTableId']//tbody//tr//td[text()='" + approverName
				+ "']//..//a//img[contains(@src,'Deep_Edit')]");
	}

	public static By SAVE_APPROVER = By
			.xpath("//table[@id='createApproveTableId']//tbody//tr//td//..//a//img[contains(@src,'Deep_Save')]");

	public static By deleteApprover(String approverName) {
		return By.xpath(
				"//table[@id='createApproveTableId']//tbody//tr//td[text()='Automation, QTP10016']//..//a//img[contains(@src,'Deep_Delete')]");
	}

	public static By SHOW_USERS_CHECKBOX = By.xpath("//div//input[@id='rulesShowUser']");

	public static By SHOW_RULES_CHECKBOX = By.xpath("//div//input[@id='rulesShowRules']");

	public static By SEARCH_BUTN = By.xpath("//div[@class='GRSearchButton']//a//span[contains(text(),'SEARCH')]");

	public static By SEARCH_RESULTS = By.xpath("//table[@id='reqtable']//tr");

	public static By DISPLAY_USERS = By.xpath("//table[@id='reqtable']//tr//td//b[contains(text(),'Users:')]");

	public static By DISPLAY_RULES = By.xpath("//div[@class='rulesSubTableComumn']");
	public static By Cart_Type = By.xpath("//td[3]//div[@class='rulesSubTableComumn']");
	public static By Currency_Range = By
			.xpath("//table[@id='rulesTable']//tr//td[4]//div[@class='rulesSubTableComumn']");
	public static By Approval_Path = By.xpath("//td[5]//div[@class='rulesSubTableComumn']");

	public static By dayInStartDayCalender(String day) {
		return By.xpath("//table[@class='ui-datepicker-calendar']//tbody//tr//td//a[text()='" + day + "']");
	}

	public static By getToDateByApproverName(String approverName) {
		return By.xpath("//*[contains(@class,'createApproveRow')]//*[contains(text(),'" + approverName
				+ "')]/..//td[contains(@id,'OofEndDateId')]");
	}

	public static By getCalenderToUpdate = By.xpath("*//td[contains(@id,'OofStartDateId')]//img");

	public static By REQ_GRP_NAME_TXTBOX = By.xpath("//input[@id='requestorGroupName']");

	public static By SMART_TRACKER_TABLE = By.xpath("//table[@id='StReport']//tbody//tr//td");

	public static By getSmartTrackerData(int row) {
		return By.xpath("(//table[@id='StReport']//tbody//tr//td//a)['" + row + "']");
	}

	public static By MANAGE_SMART_TRACKER_PAGE = By.xpath("//h1[text()='Manage SmartTrackers']");

	public static By RequestorGroupEditLink(String reqName) {
		return By.xpath("//table[@id='requestor_search_table']//td[@id='reqname'][text()='"+reqName+"']/..//td//a[contains(text(),'Edit')]");
	}
	
	public static By MANAGE_REQUESTOR_LINK = By.xpath("//span[@id='li-ManageRequestors']");
	
	public static By REFRESH_ICON = By.xpath("//a[@id='viewAllLink']") ;
	
	
	
	public static By selectRequestorNameFromList(String appName) {
		return By.xpath("//select[@id='ListFrom']//option[contains(text(),'" + appName + "')]");
	}

	public static By SELECT_PATH_ADD_BUTTON = By.xpath("//a[@class='orange'][@id='manageReq_LtoR']");
	public static By SELECT_PATH_REMOVE_BUTTON = By.xpath("//a[@class='orange'][@id='manageReq_RtoL']");
	
	public static By REQ_SAVE_CHANGES_BTN = By.xpath("//a[@id='manageReqUpdate']//span[contains(text(),'Save Changes')]");
	
	public static By REQUESTOR_GRP_REFRESH_LINK = By.xpath("//a[@title='View All']");
	
	public static By GET_REQUESTOR_GROUP_COUNT = By.xpath("//table[@id='reqGrpUserReport']//tbody//tr");
	
	public static By getRequestorName(String i) {
		return By.xpath("(//table[@id='reqGrpUserReport']//td[@class='verticalAlignTop'])[" + i + "]");
	}
	
	public static By GET_SELECTED_COUNT = By.xpath("//select[@id='nowshowing']");
	public static By NEXT_PAGE_LINK = By.xpath("//a[@id='pageLink_Next'][contiains(text(),'Next')]");
	public static By TU_IUS_Requestor_Group_Link = By.xpath("(//td[@class='verticalAlignTop']//div//a[text()='TU_IUS Requestor Group'])[1]");
	public static By TU_IUS_Tired_Requestor_Group_Link = By.xpath("(//td[@class='verticalAlignTop']//div//a[text()='TU_IUS Requestor Group Tiered'])[1]");
	public static By REQUESTOR_GRP_NAME_TXTBOX = By.xpath("//div[@class='rguSearchTextbox']//input[@id='repSkey']");
	public static By SEARCH_REQ_GRP_ICON = By.xpath("//a[@title='SEARCH']");
	

	public static By SELECT_REQUESTOR_GRP_FROM_SELECTOR(String Requestor_Group, String Req_Name) {
		return By.xpath("//td[@class='verticalAlignTop']//div//a[text()='"+Requestor_Group+"']//../../..//td[contains(text(),'"+Req_Name+"')]//..//div//a[text()='"+Requestor_Group+"']");
	}
	
	public static By SUCCESS_UPDATED_MSG = By.xpath("//div[@id='updateManageRequestors_message']//p[contains(text(),'Successfully')]");

	public static By CALENDAR = By.xpath("//div[@class='RSREndDate']//img");
	
	public static By SEARCH = By.xpath("//div[@class='RSREndDate']//following::div//a[@class='grey']//img");
	
	
	public static By FILTER_BY_STATUS = By.xpath("//select[@id='Req_type']");
	public static By RESULTS_TABLE = By.xpath("//table[@id='RSRResultTable']");
	
	public static By getRequestortableDetails(int i,int j) {
		return By.xpath("//table[@id='RSRResultTable']//tbody//tr["+i+1+"]//td["+j+"]");
	}

	
}
