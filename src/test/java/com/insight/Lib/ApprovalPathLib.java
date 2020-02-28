package com.insight.Lib;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.api.client.util.DateTime;
import com.insight.ObjRepo.ApprovalPathObj;
import com.insight.ObjRepo.CommonObj;

public class ApprovalPathLib extends ApprovalPathObj {
	OrderLib orderLib = new OrderLib();

	public void CreateNewApprovalPathLink() throws Throwable {
		if (isElementPresent(CREATE_APP_PATH, "Approval management page")) {
			click(CREATE_APP_PATH, "Approval management page");
			reporter.SuccessReport(
					"Click Create New Approval Path on Approval Management Approval Path Management Page",
					"Create New Approval Path Link Exists and Clicked", "");
		} else {
			reporter.failureReport(
					"Click Create New Approval Path on Approval Management Approval Path Management Page",
					"Create New Approval Path Link Does Not Exist", "");
		}
	}

	public void EnterNewApprovalPath(String Approver_Name) throws Throwable {
		if (isElementPresent(APPROVAL_PATH_NAME, "Approval management page")) {
			type(APPROVAL_PATH_NAME, Approver_Name, "Approver Name");
			reporter.SuccessReport("Enter Approval Path Name on  Approval Path Management Page",
					"Approval Path Name Field Exists and Entered", "");
		} else {
			reporter.failureReport("Enter Approval Path Name on  Approval Path Management Page",
					"Approval Path Name Field Does Not Exist", "");
		}
	}

	public String CreateNewApprovalClick() throws Throwable {
		// select approver name
		List<WebElement> myList = driver.findElements(ALL_APPROVER_OPTIONS);
		String text = (myList.get(0)).getText();

		// select first option
		if (isElementPresent(selectAppNameFromList(text), "Select Approver name from list box")) {
			click(selectAppNameFromList(text), "Select Approver name from list box");
		}

		Add_Approver_Btn_Click();

		return text;

	}

	public void ClickCreateApprovalPathButton() throws Throwable {
		if (isElementPresent(CREATE_APPROVALPATH_BTN, "Approval management page")) {
			click(CREATE_APPROVALPATH_BTN, "Approver Path Button click");
			reporter.SuccessReport(
					"Click Create New Approval Path on Approval Management Approval Path Management Page",
					"Create New Approval Path Link Exists and Clicked", "");
		} else {
			reporter.failureReport(
					"Click Create New Approval Path on Approval Management Approval Path Management Page",
					"Create New Approval Path Link Does Not Exist", "");
		}
	}

	public void VerifyAppovalPathCreated(String Approver_Name) throws Throwable {
		if (isElementPresent(getCreatedApproverPath(Approver_Name), "Approval management page")) {
			reporter.SuccessReport("Verify Approval Path is Added on Approval Management Approval Path Management Page",
					"Created Approver Path is Exists and Verified", "");
		} else {
			reporter.failureReport("Verify Approval Path is Added on Approval Management Approval Path Management Page",
					"Created Approver Path Does Not Exist", "");
		}
	}

	public void ClickEditLinkButton(String Approver_Name) throws Throwable {
		if (isElementPresent(getCreatedApproverPathEditLink(Approver_Name), "Approval management page ")) {
			click(getCreatedApproverPathEditLink(Approver_Name), "Approval management page");
			reporter.SuccessReport("Click Edit on Approval Management Approval Path Management Page",
					"Edit Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Edit on Approval Management Approval Path Management Page",
					"Edit Link Does not Exists", "");
		}
	}

	public void ClickUpdateButton() throws Throwable {
		if (isElementPresent(UPDATE_APPROVER_BTN, "Select Update option")) {
			click(UPDATE_APPROVER_BTN, "Select Update option");
			reporter.SuccessReport("Approval Path Management Page", "Update Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Path Management Page", "Update Link Does Not Exist", "");
		}
	}

	public void VerifyApproversAdded(String AppName) throws Throwable {
		if (isElementPresent(selectAppNameFromListToRemove(AppName), "Approval management page")) {
			reporter.SuccessReport("Click Update on  Approval Path Management Page", "Update Link Exists and Clicked",
					"");
		} else {
			reporter.failureReport("Click Update on  Approval Path Management Page", "Update  Link Does Not Exist", "");
		}
	}

	public void VerifyFirstApproverAdded(String AppName) throws Throwable {
		if (isElementPresent(getCreatedApproverPath(AppName), "Approval management page")) {
			reporter.SuccessReport("Approval Path Management Page", "First Approver Path Exists and Verified", "");
		} else {
			reporter.failureReport("Approval Path Management Page", "First Approval Path Does Not Exist", "");
		}
	}

	public void VerifySecondApproverAdded(String AppName) throws Throwable {
		if (isElementPresent(getCreatedApproverPath(AppName), "Approval management page")) {
			reporter.SuccessReport("Approval Path Management Page", "Second Approver Path Exists and Verified", "");
		} else {
			reporter.failureReport("Approval Path Management Page", "Second Approval Path Does Not Exist", "");
		}
	}

	public void DeleteApprovers(String Approver_Name) throws Throwable {
		if (isElementPresent(getCreatedApproverPathDeleteLink(Approver_Name), "Approval management page ")) {
			click(getCreatedApproverPathDeleteLink(Approver_Name), "Approval management page");
			reporter.SuccessReport("Delete Approval Path on Approval Management Approval Path Management Page",
					"Approval Path Exists and Delete Option is Clicked", "");
		} else {
			reporter.failureReport("Delete Approval Path on Approval Management Approval Path Management Page",
					"Approval Path Link Does Not Exist", "");
		}
	}

	public void ConfirmDelete(String Approver_Name) throws Throwable {
		if (isElementPresent(Confirm_Delete_Path(Approver_Name), "Delete Confirmation Button")) {
			click(Confirm_Delete_Path(Approver_Name), "Confirm delete button clicked");
			reporter.SuccessReport("Confirm to Delete on  Approval Path Management Page",
					"POPUP Exists and Delete Link is Clicked", "");
		} else {
			reporter.failureReport("Confirm to Delete on  Approval Path Management Page", "POPUP Does Not Exist", "");
		}
	}

	public void SearchUser(String Approver_Name) throws Throwable {
		if (isElementPresent(SEARCH_TXTBOX, "Serach text box")) {
			type(SEARCH_TXTBOX, Approver_Name, "Serach text box");
			reporter.SuccessReport("Enter Approver Name on Approval Management Approval Path Management Page",
					"Approval Path Name Field Exists and Entered", "");
		} else {
			reporter.failureReport("Enter Approver Name on Approval Management Approval Path Management Page",
					"Approval Path Name Field Does Not Exist", "");
		}

		// Click Search
		if (isElementPresent(SEARCH_BTN, "Search Button")) {
			click(SEARCH_BTN, "Click on search button");
			reporter.SuccessReport("Approval Path Management Page", "Search Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Path Management Page", "Search Link Does Not Exist", "");
		}
	}

	public void VerifyApprovalPathReturned() throws Throwable {
		if (isElementPresent(DELETE_SUCCESS, "Approval management page")) {
			reporter.SuccessReport("Approval Management Approval Path Management Page", "Inactive User does not Exists",
					"");
		} else {
			reporter.failureReport("Approval Management Approval Path Management Page", "Inactive User Exists", "");
		}
	}

	public String SelectApprover(String Approver_Name) throws Throwable {
		String text = null;
		if (Approver_Name != null) {
			SelectSpecificApprover(Approver_Name);
		} else {
			// select approver name
			List<WebElement> myList = driver.findElements(ALL_APPROVER_OPTIONS);
			text = (myList.get(0)).getText();

			if (isElementPresent(selectAppNameFromList(text), "Select Approver name from list box")) {
				click(selectAppNameFromList(text), "Select Approver name from list box");
				reporter.SuccessReport("Approval Path Management Page", "Available Approvers Field Exists and Selected",
						text);
			} else {
				reporter.failureReport("Approval Path Management Page", "Available Approvers Field Does Not Exist", "");
			}

		}
		return text;
	}

	public void Add_Approver_Btn_Click() throws Throwable {
		if (isElementPresent(SELECT_PATH_BUTTON, "Select Path option")) {
			reporter.SuccessReport("Click Add on Approval Management Approval Path Management Page",
					"> Link Exists and Clicked", "");
			click(SELECT_PATH_BUTTON, "Select Path option");
		} else {
			reporter.failureReport("Click Add on Approval Management Approval Path Management Page",
					"> Link does not Exists", "");
		}
	}

	public void SelectSpecificApprover(String AppName) throws Throwable {

		if (isElementPresent(selectAppNameFromList(AppName), "Select Approver name from list box")) {
			reporter.SuccessReport("Select Approver on  Approval Path Management Page",
					"Approval Path Order Approver Field Exists and Selected", "");
			click(selectAppNameFromList(AppName), "Select Approver name from list box");
		} else {
			reporter.failureReport("Select Approver on  Approval Path Management Page",
					"Approval Path Order Approver Field Does Not Exist", "");
		}

		Add_Approver_Btn_Click();

	}

	public void SelectSpecificRequestor(String strRequestorOption) throws Throwable {

		if (isElementPresent(selectRequestorByText(strRequestorOption), "Approver ")) {
			selectByVisibleText(createRequestorType(), strRequestorOption, "Approver");
			reporter.SuccessReport("Add Requestors on Create/Edit Requestor Group Page",
					"Requestors Exist in the Available Requestors' List and Selected", strRequestorOption);
		} /*
			 * else { reporter.
			 * failureReport("Select Approver on  Approval Path Management Page"
			 * , "Approval Path Order Approver Field Does Not Exist", ""); }
			 */

	}

	public void VerifySuccessDelete() throws Throwable {
		if (isElementPresent(SUCCESS_DELETE_MSG, "Delete approver success message")) {
			reporter.SuccessReport("Verify Deleted Approval Path on Approval Management Approval Path Management Page",
					"Approval path was deleted successfully Message Exists", "");
		} else {
			reporter.failureReport("Verify Deleted Approval Path on Approval Management Approval Path Management Page",
					"Approval path was deleted successfully Message Does Not Exists", "");
		}
	}

	public void ApproverSearchTextBox(String appLastName) throws Throwable {
		if (appLastName != null) {
			if (isElementPresent(APP_LNAME_TEXTBOX, "Approver Last Name")) {
				type(APP_LNAME_TEXTBOX, appLastName, "Enter Approver Last Name");
				reporter.SuccessReport("Enter Approver Name on Approval Management Approval Path Management Page",
						"Approval Path Name Field Exists and Entered", "");
			} else {
				reporter.failureReport("Enter Approver Name on Approval Management Approval Path Management Page",
						"Approval Path Name Field Does Not Exist", "");
			}
		}
	}

	public void SearchClick() throws Throwable {
		if (isElementPresent(LAST_NAME_SEARCH_BTN, "Last Name search button")) {
			click(LAST_NAME_SEARCH_BTN, "Last Name search button");
			reporter.SuccessReport("Click Search on Approval Management Approval Path Management Page",
					"Search Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Search on Approval Management Approval Path Management Page",
					"Search Link Does Not Exist", "");
		}
	}

	public void ValidateApproverFromSearch(String Approver_Name) throws Throwable {
		if (isElementPresent(getCreatedApproverPathEditLink(Approver_Name), "Approval management page")) {
			reporter.SuccessReport("Approval Management Approval Path Management Page",
					"Approver Last name Exists and Verified", "");
		} else {
			reporter.failureReport("Approval Management Approval Path Management Page",
					"Approver Last Name Does Not Exist", "");
		}
	}

	public void VerifyApprovalPathAndApprovers() throws Throwable {
		if (isElementPresent(GET_APPROVALPATH_COUNT, "Approval path details")) {
			List<WebElement> appCount = driver.findElements(GET_APPROVALPATH_COUNT);
			int count = appCount.size();
			if (count > 1) {
				String firstPathName = driver.findElement(GET_FIRST_APP_PATH_NAME).getText();
				String numOfApprovers = driver.findElement(GET_NUMOF_APPROVERS).getText();
				String result = "Approval Path Name:" + firstPathName.trim() + "***" + "# of Approvers:"
						+ numOfApprovers.trim();
				reporter.SuccessReport("System Displays All the Approval Paths on  Approval Path Management Page",
						"Approval Path Exists", result);
			} else {
				reporter.failureReport("System Displays All the Approval Paths on  Approval Path Management Page",
						"Approval Path Does Not Exist", "");
			}
		}
	}

	public void GetFirstApprovalPath() throws Throwable {
		if (isElementPresent(GET_FIRST_APP_PATH_NAME, "Approval management page")) {
			String firstPathName = driver.findElement(GET_FIRST_APP_PATH_NAME).getText();
			reporter.SuccessReport(
					"Get The First Approval Path on Approval Management Page Approver Out of Office Settings",
					"After Creating the Approver Out", firstPathName);
		}
	}

	public String GetFirstPathAddRemoveApprover() throws Throwable {
		String firstPathName = null;
		if (isElementPresent(GET_APPROVALPATH_COUNT, "Approval path details")) {
			List<WebElement> appCount = driver.findElements(GET_APPROVALPATH_COUNT);
			int count = appCount.size();
			if (count > 1) {
				firstPathName = driver.findElement(GET_FIRST_APP_PATH_NAME).getText();

				reporter.SuccessReport("System Displays All the Approval Paths on  Approval Path Management Page",
						"Approval Path Exists", "");
			} else {
				reporter.failureReport("System Displays All the Approval Paths on  Approval Path Management Page",
						"Approval Path Does Not Exist", "");
			}
		}
		return firstPathName;
	}

	public void RemoveApprovers(String approverAdded) throws Throwable {

		if (approverAdded != null) {
			if (isElementPresent(selectAppNameFromListToRemove(approverAdded),
					"Select Approver name from list box  ")) {
				click(selectAppNameFromListToRemove(approverAdded), "Select Approver name from list box");
				if (isElementPresent(SELECT_REMOVEPATH_BUTTON, "Select Path option")) {
					reporter.SuccessReport("Remove Approvers on Approval Path Management Page",
							"< Link Exists and Clicked", "");
					click(SELECT_REMOVEPATH_BUTTON, "Select Path option");
					// click(SELECT_REMOVEPATH_BUTTON, "Select Path option");
				} else {
					reporter.failureReport("Remove Approvers on Approval Path Management Page",
							"< Link does not Exists", "");
				}
			}
		}
	}

	public void VerifySuccessUpdate() throws Throwable {
		if (isElementPresent(SUCCESS_UPDATE_MSG, "Update approver success message")) {
			reporter.SuccessReport("Verify Approval Path Management Page", "Successfully Edited Approval Path", "");
		} else {
			reporter.failureReport("Verify Approval Path Management Page",
					"Successfully edited approval Message path Does Not Exist", "");
		}
	}

	public void VerifyIsInEditMode() throws Throwable {
		if (isElementPresent(UPDATE_APPROVER_BTN, "Verify Edit mode")) {
			reporter.SuccessReport("Verify Approval Path is in Edit Mode on Approval Path Management Page",
					"Approval Path is in Edit Mode", "");
		} else {
			reporter.failureReport("Verify Approval Path is in Edit Mode on Approval Path Management Page 	",
					"Approval Path is Not in Edit Mode", "");
		}
	}

	public void ClickCancelButton() throws Throwable {

		if (isElementPresent(CANCEL_BUTTON, "Verify cancel button")) {
			click(CANCEL_BUTTON, "Click cancel button");
			reporter.SuccessReport("Click Cancel on Approval Path Management Page", "Cancel Link Exists and Clicked",
					"");
		} else {
			reporter.failureReport("Click Cancel on Approval Path Management Page", "Cancel Link Does Not Exist", "");
		}
	}

	public void CancelOnErrorPage() throws Throwable {

		if (isElementPresent(CANCEL_ON_ERROR_PAGE, "Verify cancel button")) {
			click(CANCEL_ON_ERROR_PAGE, "Click cancel button");
			reporter.SuccessReport("Click Cancel on Approval Path Management Page", "Cancel Link Exists and Clicked",
					"");
		} else {
			reporter.failureReport("Click Cancel on Approval Path Management Page", "Cancel Link Does Not Exist", "");
		}
	}

	public void VerifyMessage() throws Throwable {
		if (isElementPresent(ERROR_POPUP_MSG, "Verify Error message")) {
			reporter.SuccessReport("Verify Message on Approval Path Management Page",
					"The selected approver/(s/) have open/pending requisitions requiring approval  Message Exists", "");
		} else {
			reporter.failureReport("Verify Message on Approval Path Management Page",
					"The approver(s) can not be removed when open/pending requisitions exist requiring their approval: Does Not Exist",
					"");
		}
	}

	public void CountPendingRequest() throws Throwable {
		if (isElementPresent(PENDING_REQ_COUNT, "Pending request count")) {

			List<WebElement> appCount = driver.findElements(PENDING_REQ_COUNT);
			int count = appCount.size();
			String strComm = null;
			List<WebElement> myList = driver.findElements(PENDING_REQ_COUNT);
			List<String> all_elements_text = new ArrayList<>();
			for (int i = 1; i < myList.size(); i++) {
				all_elements_text.add(myList.get(i).getText());

				String ReqNum = all_elements_text.get(0);

				String str[] = ReqNum.split(" ");
				strComm = "Req Number Count: " + count + "***" + "Requisition Number:" + str[0] + "***"
						+ "Next Approver:" + str[1];

			}
			if (count > 1) {
				reporter.SuccessReport("Approval Path Management Page", "Pending Requisitions Exists", strComm);
			} else {
				reporter.failureReport("Approval Path Management Page", "Pending Requisitions does not Exists", "");
			}
		}

	}

	public void CountApproversOnEditMode() throws Throwable {
		if (isElementPresent(APPROVERS_LIST_ADDED, "Approvers count ")) {

			List<WebElement> appCount = driver.findElements(APPROVERS_LIST_ADDED);
			int count = appCount.size();
			List<String> all_elements_text = new ArrayList<>();
			for (int i = 0; i < appCount.size(); i++) {
				all_elements_text.add(appCount.get(i).getText());

				String ReqNum = all_elements_text.get(0);

				String str[] = ReqNum.split("\n");
				reporter.SuccessReport("Approval Path Management Page", "Approvers are Added to Approval path Sequence",
						"Approver Name : " + "+i+" + " : " + str[0]);
			}
			if (count == 1) {
				reporter.failureReport("Approval Path Management Page",
						"Approvers are Not Added to Approval path Sequence", "");
			}
		}
	}

	public void ClickViewAllPathDetails() throws Throwable {
		if (isElementPresent(VIEW_ALL_IMG_ICON, "Verify View all image icon")) {
			click(VIEW_ALL_IMG_ICON, "Click View all image icon");
			reporter.SuccessReport("Approval Path Management Page",
					"Viewing all Available Approvers Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Path Management Page",
					"Viewing all Available Approvers Link Does Not Exist", "");
		}
	}

	public void VerifyAppPathNames() throws Throwable {

		List<WebElement> appCount = driver.findElements(GET_APPROVALPATH_COUNT);

		if (appCount.size() > 1) {
			reporter.SuccessReport("Approval Path Management Page", "Approval Path Names Exists", "");

		} else {
			reporter.failureReport("Approval Path Management Page", "Approval Path Names Does Not Exist", "");
		}

		String strComm = null;
		for (int i = 1; i < appCount.size(); i++) {

			String pathName = driver.findElement(getAppPathName(String.valueOf(i))).getText();
			String numOfApprovers = driver.findElement(getApproverCount(String.valueOf(i))).getText();
			strComm = "Approval Path Name with Number of Approvers:" + pathName.trim() + "****" + numOfApprovers.trim();
			reporter.SuccessReport("Approval Path Management Page", "Approval Path Names Exists", strComm);
		}
	}

	public void ClickReports() throws Throwable {
		if (isElementPresent(REPORTS_LINK, "Verify Reports Link")) {
			click(REPORTS_LINK, "Click Reports Link");
			reporter.SuccessReport("Approval Path Management Page", "Reports Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Path Management Page", "Reports Link Does Not Exist", "");
		}
	}

	public void VerifyReportTypes() throws Throwable {
		String Req_Status_report = null;
		String Req_grp_users = null;
		String Req_Grp_rules_report = null;
		String Approval_Path_report = null;
		String App_out_report = null;
		String Req_grp_smart_tracker_rep = null;
		String strComm = null;

		if (isElementPresent(REPORTS_DIV, "Report div")) {

			if (isElementPresent(REQUISITION_STAUS_REPORT, "Verify Requision Status Report")) {
				Req_Status_report = driver.findElement(REQUISITION_STAUS_REPORT).getText();
			}

			if (isElementPresent(REQUESTOR_GROUP_USERS, "Verify Requestor Group Users")) {
				Req_grp_users = driver.findElement(REQUESTOR_GROUP_USERS).getText();
			}

			if (isElementPresent(REQUESTOR_GROUPRULES_REPORT, "Verify Requestor Group Rules Report")) {
				Req_Grp_rules_report = driver.findElement(REQUESTOR_GROUPRULES_REPORT).getText();
			}

			if (isElementPresent(APPROVAL_PATH_REPORT, "Verify Approval Path report")) {
				Approval_Path_report = driver.findElement(APPROVAL_PATH_REPORT).getText();
			}

			if (isElementPresent(APPROVER_OUT_REPORT, "Verify Approver Our Report")) {
				App_out_report = driver.findElement(APPROVER_OUT_REPORT).getText();
			}

			if (isElementPresent(REQUESTOR_GROUP_SMARTTRACKER_REPORT, "Verify Requestor Group Smart Tracker Report")) {
				Req_grp_smart_tracker_rep = driver.findElement(REQUESTOR_GROUP_SMARTTRACKER_REPORT).getText();
			}
			strComm = "Report Links:" + Req_Status_report.trim() + "****" + Req_grp_users.trim() + "****"
					+ Req_Grp_rules_report.trim() + "*****" + Approval_Path_report.trim() + "*****"
					+ App_out_report.trim() + "*****" + Req_grp_smart_tracker_rep.trim();

			reporter.SuccessReport("Approval Path Management Page", "Approval Management Reports Exists", strComm);
		} else {
			reporter.failureReport("Approval Path Management Page", "Report Types Do Not Exist", Req_Status_report);
		}
	}

	public void ClickApprovalPathReportLink() throws Throwable {
		if (isElementPresent(APPROVAL_PATH_REPORT, "Verify Approval Path Link")) {
			click(APPROVAL_PATH_REPORT, "Click Approval Path Link");
			reporter.SuccessReport("Approval Path Management Page", "Approval Path Report Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Path Management Page", "Approval Path Report Link Does Not Exist", "");
		}
	}

	public String VerifyApprovalPathReport() throws Throwable {
		if (isElementPresent(APP_PATH_REPORT_PAGE_TITLE, "Approval Path Report Page ")) {
			reporter.SuccessReport("Approval Path Management Page", "Approval Path Names Exists", "");
		} else {
			reporter.failureReport("Approval Path Management Page", "Approval Path Names Does Not Exist", "");
		}

		List<WebElement> appCount = driver.findElements(GET_APPROVALPATH_REPORT_COUNT);
		String strComm = null;
		String strApprovalPathNm = null;
		for (int row = 1; row < appCount.size(); row++) {
			int col = 0;

			String strApprovalPathName = driver
					.findElement(getApprovalPathDetails(String.valueOf(col + 1), String.valueOf(row))).getText();
			String strNumberOfApprovers = driver
					.findElement(getApprovalPathDetails(String.valueOf(col + 2), String.valueOf(row))).getText();
			String strRequestorGroup = driver
					.findElement(getApprovalPathDetails(String.valueOf(col + 3), String.valueOf(row))).getText();

			strComm = "Approval Path Report:" + strApprovalPathName.trim() + "****" + strNumberOfApprovers.trim()
					+ "*****" + strRequestorGroup.trim();
			reporter.SuccessReport("Approval Path Management Page", "Approval Path Names Exists", strComm);
		}

		if (appCount.size() > 1) {
			strApprovalPathNm = driver.findElement(getApprovalPathDetails(String.valueOf(1), String.valueOf(1)))
					.getText();
		}
		return strApprovalPathNm;
	}

	public void ClickApprovalPathLink(String appPathName) throws Throwable {
		if (isElementPresent(getApprovalPathNameLink(appPathName), "Verify Approval Path Link")) {
			click(getApprovalPathNameLink(appPathName), "Click Approval Path Link");
			reporter.SuccessReport("Approval Path Management Page", "Approval Path Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Path Management Page", "Approval Path Link Does Not Exist", "");
		}
	}

	public void ClickRequestorGrpLink() throws Throwable {
		if (isElementPresent(REQUESTOR_GRP_LINK, "Approval Path Report Page ")) {
			click(REQUESTOR_GRP_LINK, "Click Approval Path Link");
			reporter.SuccessReport("Approval Management Reports Page", "Requestor Group Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Management Reports Page", "Requestor Group Link Does Not Exist", "");
		}
	}

	public void ClickEditLinkINRequestorGrpPage(String reqGrpName) throws Throwable {
		if (isElementPresent(clickRequestorGrpEdit(reqGrpName), "Requestor Group Management page")) {
			click(clickRequestorGrpEdit(reqGrpName), "Requestor Group Management page");
			reporter.SuccessReport("Click Edit on Requestor Group Management Page", "Edit Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Edit on Requestor Group Management Page", "Edit Link Does not Exists", "");
		}
	}

	public void VerifyReqGrpInEditMode() throws Throwable {
		if (isElementPresent(SAVE_CHANGES_BTN, "Verify Edit mode")) {
			reporter.SuccessReport("Verify Requestor Group Management page", "Requestor Group Loads In Edit Mode", "");
		} else {
			reporter.failureReport("Requestor Group Management page", "Requestor Group Does Not Exist", "");
		}
	}

	public void ClickGeneralSettings() throws Throwable {
		if (isElementPresent(GENERAL_SETTINGS_LINK, "General Settings Link ")) {
			click(GENERAL_SETTINGS_LINK, "Click General Settings Link");
			reporter.SuccessReport("Click General Settings on Approval Management Page",
					"General Settings Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click General Settings on Approval Management Page",
					"General Settings Link Does Not Exist", "");
		}
	}

	public void ClickCreateLink() throws Throwable {
		if (isElementPresent(CREATE_LINK, "Create Link ")) {
			click(CREATE_LINK, "Click Create Link");
			reporter.SuccessReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"CREATE Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"CREATE Link  Does Not Exist", "");
		}
	}

	public void verifyNameIsRequiredAlert() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver,
				300 /* timeout in seconds */);
		if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
			String alertMsg = driver.switchTo().alert().getText();
			if (alertMsg.contains("Name")) {
				reporter.SuccessReport("Click ok on Approval Management Page Approver Out of Office Settings",
						"POPUP Exists and OK is Clicked", alertMsg);
				acceptAlert();

			} else {
				reporter.failureReport("Click ok on Approval Management Page Approver Out of Office Settings",
						"POPUP Does Not Exist", "");
			}
		}
	}

	public void CreateRequisitionRejectionTypes(String RejectionType) throws Throwable {
		if (isElementPresent(REQ_REJECTION_DESC_TXTBOX, "Rejection Type")) {
			type(REQ_REJECTION_DESC_TXTBOX, RejectionType, "Rejection Type");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Rejection Type Field Exists and Entered", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Rejection Type Field Does Not Exist", "");
		}

		// Click on create button

		if (isElementPresent(REQ_REJ_ADD_LINK, "Rejection Type Add link")) {
			click(REQ_REJ_ADD_LINK, "Rejection Type Add link");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Rejection Type Field entered and clicked + link", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					" + link does not exists to add a rejection type", "");
		}
	}

	public void VerifyAddedRejection(String RejectionType) throws Throwable {
		if (isElementPresent(getReqRejTypeDesc(RejectionType), "Create Link ")) {
			reporter.SuccessReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"Rejection is Added and Verified", "");
		} else {
			reporter.failureReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"Rejection Does Not Exist", "");
		}
	}

	public void ClickEditToModifyRejType(String RejectionType) throws Throwable {
		if (isElementPresent(editRejType(RejectionType), "Edit Link ")) {
			click(editRejType(RejectionType), "Rejection Type Edit Link");
			reporter.SuccessReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"Requisition RejectionTypes Edit Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"Requisition RejectionTypes Edit Link does not Exists", "");
		}
	}

	public void ModifyRejectionName(String NewRejectionType, String RejectionType) throws Throwable {
		if (isElementPresent(modifyRejDesc(RejectionType), "Rejection Type")) {
			type(modifyRejDesc(RejectionType), NewRejectionType, "Rejection Type");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Rejection Type Field Exists and Entered", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Rejection Type Field Does Not Exist", "");
		}

		// click on save to modify the name
		if (isElementPresent(saveNewRejType(RejectionType), "Rejection Type")) {
			click(saveNewRejType(RejectionType), "Rejection Type");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Save Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Save Link Does Not Exist", "");
		}
	}

	public void VerifyNameExistsPopup() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver,
				300 /* timeout in seconds */);
		if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
			String alertMsg = driver.switchTo().alert().getText();
			if (alertMsg.contains("unique")) {
				reporter.SuccessReport("Click ok on Approval Management Page Approver Out of Office Settings",
						"POPUP Exists and OK is Clicked", alertMsg);
				acceptAlert();

			} else {
				reporter.failureReport("Click ok on Approval Management Page Approver Out of Office Settings",
						"POPUP Does Not Exist", "");
			}
		}
	}

	public void VerifyOverLapRulePopup() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver,
				300 /* timeout in seconds */);
		if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
			String alertMsg = driver.switchTo().alert().getText();
			if (alertMsg.contains("Please edit existing rule")) {
				reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
						"POPUP Exists and OK is Clicked", alertMsg);
				acceptAlert();

			} else {
				reporter.failureReport("Approval Management Page Approver Out of Office Settings",
						"POPUP Does Not Exist", "");
			}
		}
	}

	public void DeleteRequisitionRejectionTypes(String RejectionType) throws Throwable {
		if (isElementPresent(deleteNewRejType(RejectionType), "Rejection Type ")) {
			click(deleteNewRejType(RejectionType), "Rejection Type");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Save Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Save Link Does Not Exist", "");
		}
	}

	public void VerifyDeleteRejctionType(String RejectionType) throws Throwable {
		if (isElementPresent(getReqRejTypeDesc(RejectionType), "Create Link ")) {
			reporter.failureReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"Deleted Rejection Name Exists", RejectionType);

		} else {
			reporter.SuccessReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"Deleted Rejection Name is Deleted and Verified", RejectionType);
		}
	}

	public void CreateApproverOut(String strApproverId, String strReplacementType, String strReplacementApproverId,
			String strCurrDay) throws Throwable {
		// Select Approver
		if (isElementPresent(selectApprover(strApproverId), "Approver ")) {
			selectByValue(createApproverType(), strApproverId, "Approver");
			reporter.SuccessReport("Select Approver on Approval Management  Page Approver Out of Office Settings",
					"Approver Field Exists and Selected", strApproverId);
		} else {
			reporter.failureReport("Select Approver on Approval Management  Page Approver Out of Office Settings",
					"Approver Field  Does Not Exist", "");
		}

		// Select Replacement Type
		if (isElementPresent(createReplacementType(), "Replacement Type")) {
			selectByVisibleText(createReplacementType(), strReplacementType, "Replacement Type");
			reporter.SuccessReport(
					"Select Replacement Approver on Approval Management  Page Approver Out of Office Settings",
					"Replacement Approver Field Exists and Selected", strReplacementType);
		} else {
			reporter.failureReport(
					"Select Replacement Approver on Approval Management  Page Approver Out of Office Settings",
					"Replacement Approver Field  Does Not Exist", "");
		}

		if (strReplacementType.trim().contains("Skip Approver")) {
			// Verify Replacement Approver is Disabled
			if (isElementPresent(DISABLED_REPLACEMENT_APPROVER, "Replacement Approver")) {
				reporter.SuccessReport("Approval Management  Page Approver Out of Office Settings",
						"Replacement Approver Field Exists and in Disabled Mode", "");
			} else {
				reporter.failureReport("Approval Management  Page Approver Out of Office Settings",
						"Replacement Approver Field Exists and not in Disabled Mode", "");
			}
		} else {
			// Select Replacement Approver
			if (isElementPresent(selectRepApprover(strReplacementApproverId), "Replacement Approver")) {
				selectByValue(createRepApproverType(), strReplacementApproverId, "Approver");
				reporter.SuccessReport(
						"Select Replacement Approver on Approval Management  Page Approver Out of Office Settings",
						"Replacement Approver Field Exists and Selected", strReplacementApproverId);
			} else {
				reporter.failureReport(
						"Select Replacement Approver on Approval Management  Page Approver Out of Office Settings",
						"Replacement Approver Field  Does Not Exist", "");
			}
		}

		// From Date From Date Range Calender
		if (isElementPresent(START_DATE_CALENDER, "From Date")) {
			click(START_DATE_CALENDER, "From Date");
			reporter.SuccessReport("Select From Date on  Approval Management Page Approver Out of Office Settings",
					"From Date Calender Image Exists and Selected", "");
		} else {
			reporter.failureReport("Select From Date on  Approval Management Page Approver Out of Office Settings",
					"From Date Calender Image is Does Not Exist", "");
		}

		// Select Day

		String day = strCurrDay.split("-")[0];
		String month = strCurrDay.split("-")[1];
		String year = strCurrDay.split("-")[2];

		WebElement element = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']"));
		String monthAndYear = element.getText();
		LocalDate today = LocalDate.now();
		String monthAndDateCheck = today.format(DateTimeFormatter.ofPattern("MMMM uuuu"));

		if (monthAndYear.equals(monthAndDateCheck)) {
			if (isElementPresent(dayInStartDayCalender(day), "From Date ")) {
				click(dayInStartDayCalender(day), "Day");
			}
		} else {
			while (!monthAndYear.equals(monthAndDateCheck)) {
				if (isElementPresent(PREV_MONTH_ARROW, "From Date ")) {
					click(PREV_MONTH_ARROW, "Day");
				}
			}
		}

		// Verify From Date Selection
		if (isElementPresent(START_DATE_TXTBOX, "From Date")) {
			String fromDate = getText(START_DATE_TXTBOX, "From date value");
			if (fromDate != null) {
				reporter.SuccessReport("Select From Date on  Approval Management Page Approver Out of Office Settings",
						"Selected From Date Exists", "");
			} else {
				reporter.failureReport("Select From Date on  Approval Management Page Approver Out of Office Settings",
						"Selected From Date Does Not Exist", "");
			}
		}

		// Check To Date will be disabled for permanent Replace -
		// ReplacementType
		// To Date From Date Range Calender

		if (!strReplacementType.trim().contains("Permanent")) {
			if (isElementPresent(END_DATE_CALENDER, "To Date")) {
				click(END_DATE_CALENDER, "To Date");
				reporter.SuccessReport("Select To Date on  Approval Management Page Approver Out of Office Settings",
						"To Date Calender Image Exists and Selected", "");
			} else {
				reporter.failureReport("Select From Date on  Approval Management Page Approver Out of Office Settings",
						"To Date Calender Image is Does Not Exist", "");
			}

			// Change the Month
			if (isElementPresent(NEXT_MONTH_ARROW, "Next month")) {
				click(NEXT_MONTH_ARROW, "Next konth");
				reporter.SuccessReport("Change Month on Approval Management Page Approver Out of Office Settings",
						"Arrow Button Exists and Clicked to Change the Month", "");
			} else {
				reporter.failureReport("Change Month on Approval Management Page Approver Out of Office Settings",
						"Arrow Button Does Not Exist", "");
			}

			// Select Day
			if (isElementPresent(dayInStartDayCalender(day), "From Date ")) {
				click(dayInStartDayCalender(day), "Day");
			}

			// Verify To Date Selection
			if (isElementPresent(END_DATE_TXTBOX, "To Date")) {
				String fromDate = getText(END_DATE_TXTBOX, "To date value");
				if (fromDate != null) {
					reporter.SuccessReport(
							"Select To Date on  Approval Management Page Approver Out of Office Settings",
							"Selected To Date Exists", "");
				} else {
					reporter.failureReport(
							"Select To Date on  Approval Management Page Approver Out of Office Settings",
							"Selected To Date Does Not Exist", "");
				}
			}
		}

		// Click on Create
		if (isElementPresent(ADD_IMG_BTN, "Create link")) {
			click(ADD_IMG_BTN, "Create link");
			reporter.SuccessReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"CREATE Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"CREATE Link does not Exists", "");
		}

	}

	public void VerifyCreateApproverOut(String strApprover) throws Throwable {

		if (isElementPresent(verifyApproverOutCreated(strApprover), "Verify Approver")) {

			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings ",
					"Created Approver Out Exists", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Approver Out Does Not Exist", "");
		}
	}

	public void ModifyDeleteApproverOut(String strApprover, String modifyDate) throws Throwable {
		if (isElementPresent(editApprover(strApprover), "Verify Approver ")) {
			click(editApprover(strApprover), "Verify Approver");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Created Approver Out is in Edit Mode", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Approver Out Does Not Exist", "");
		}

		if (isElementPresent(getCalenderToUpdate, "To Date")) {
			click(getCalenderToUpdate, "To date");
		}

		String day = modifyDate.split("-")[0];

		// Modify the date
		if (isElementPresent(PREV_MONTH_ARROW, "Next month ")) {
			click(PREV_MONTH_ARROW, "Next konth");
			reporter.SuccessReport("Change Month on Approval Management Page Approver Out of Office Settings",
					"Arrow Button Exists and Clicked to Change the Month", "");
		} else {
			reporter.failureReport("Change Month on Approval Management Page Approver Out of Office Settings",
					"Arrow Button Does Not Exist", "");
		}

		// Select Day

		int date = Integer.parseInt(day);
		date = date + 1;
		day = String.valueOf(date);

		if (isElementPresent(dayInStartDayCalender(day), "From Date ")) {
			click(dayInStartDayCalender(day), "Day");
		}

		// Save
		if (isElementPresent(SAVE_APPROVER, "Save Approver")) {
			click(SAVE_APPROVER, "Save Approver");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Created Approver Out is Modified and Saved", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Approver Out Does Not Exist", "");
		}

		// Delete

		if (isElementPresent(deleteApprover(strApprover), "Delete Approver")) {
			click(deleteApprover(strApprover), "Delete Approver");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Created Approver Out is Deleted", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Approver Out Does Not Exist", "");
		}
	}

	public String GetApproverId() throws Throwable {
		String strApproverOption = null;
		List<WebElement> myList = driver.findElements(ALL_APPROVER_NAME_OPTIONS);
		if (myList.size() > 0) {
			strApproverOption = (myList.get(1)).getAttribute("value");
		}
		return strApproverOption;
	}

	public String GetReplacementApproverId() throws Throwable {
		String strRepApproverOption = null;
		List<WebElement> myList = driver.findElements(ALL_REPLACEMENT_APPROVER_NAME_OPTIONS);
		if (myList.size() > 0) {
			strRepApproverOption = (myList.get(2)).getAttribute("value");
		}
		return strRepApproverOption;
	}

	public String GetApproverName(String approverId) throws Throwable {
		String strApproverName = null;

		strApproverName = driver.findElement(selectApprover(approverId)).getText();

		return strApproverName;
	}

	public String GetReplacementApproverName(String strReplacementApproverId) throws Throwable {
		String strRepApproverName = null;

		strRepApproverName = driver.findElement(selectRepApprover(strReplacementApproverId)).getText();

		return strRepApproverName;
	}

	public String GetReplacementApprover() throws Throwable {
		String strReplacementApproverId = null;
		String strReplacementApprover = null;

		List<WebElement> myList = driver.findElements(ALL_REPLACEMENT_APPROVER_NAME_OPTIONS);
		if (myList.size() > 1) {
			strReplacementApproverId = (myList.get(2)).getAttribute("value");
			strReplacementApprover = driver.findElement(selectApprover(strReplacementApproverId)).getText();
		}
		return strReplacementApprover;
	}

	public String GetCurrDay() throws Throwable {

		LocalDate today = LocalDate.now();
		String fromDate = today.format(DateTimeFormatter.ofPattern("d-MMM-uuuu"));
		return fromDate;
	}

	public void ClickReqGrpRulesReport() throws Throwable {
		if (isElementPresent(REQUESTOR_GROUPRULES_REPORT, "Requestor Group Rules Report")) {
			click(REQUESTOR_GROUPRULES_REPORT, "Click Requestor Group Rules Report");
			reporter.SuccessReport("Click Requestor Group Rules Report on Approval Management Reports Page",
					"Requestor Group Rules Report Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Requestor Group Rules Report on Approval Management Reports Page",
					"Requestor Group Rules Report Link Does Not Exist", "");
		}
	}

	public void ClickShowUsers() throws Throwable {
		if (isElementPresent(SHOW_USERS_CHECKBOX, "Show Users Field")) {
			click(SHOW_USERS_CHECKBOX, "Click Show Users Field");
			reporter.SuccessReport("Check Show Users Field on Approval Management Requestor Group Rules Report Page",
					"Show Users Field Exists and Checked", "");
		} else {
			reporter.failureReport("Check Show Users Field on Approval Management Requestor Group Rules Report Page",
					"Show Users Field Does Not Exist", "");
		}
	}

	public void ClickShowRules() throws Throwable {
		if (isElementPresent(SHOW_RULES_CHECKBOX, "Show Users Field")) {
			click(SHOW_RULES_CHECKBOX, "Click Show Users Field");
			reporter.SuccessReport("Check Show Rules Field on Approval Management Requestor Group Rules Report Page",
					"Show Rules Field Exists and Checked", "");
		} else {
			reporter.failureReport("Check Show Rules Field on Approval Management Requestor Group Rules Report Page",
					"Show Rules Field Does Not Exist", "");
		}
	}

	public void ClickSearchButton() throws Throwable {
		if (isElementPresent(SEARCH_BUTN, "SEARCH Link")) {
			click(SEARCH_BUTN, "Click SEARCH Link");
			reporter.SuccessReport("Check Search on Approval Management Requestor Group Rules Report Page",
					"SEARCH Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Check Search on Approval Management Requestor Group Rules Report Page",
					"SEARCH Link Does Not Exist", "");
		}
	}

	public void VerifyReqGroupNames() throws Throwable {
		if (isElementPresent(SEARCH_RESULTS, "SEARCH Results table")) {
			String strApprovalPathNm = null;
			List<WebElement> appCount = driver.findElements(SEARCH_RESULTS);
			for (int row = 1; row < appCount.size(); row++) {
				strApprovalPathNm = driver.findElement(getReqGroupDetails(String.valueOf(1), String.valueOf(row)))
						.getText();
				reporter.SuccessReport("Approval Management Requestor Group Rules Report Page",
						"ReqestorGroupName Exists and Displayed in Alphabetical Order", strApprovalPathNm);
			}
		} else {
			reporter.failureReport("Approval Management Requestor Group Rules Report Page",
					"ReqestorGroupName Does Not Exist", "");
		}

	}

	public void VerifyDisplayUsers() throws Throwable {
		if (isElementPresent(DISPLAY_USERS, "SEARCH display users")) {
			List<WebElement> searchResults = driver.findElements(DISPLAY_USERS);
			String strUsers = null;
			for (int row = 1; row < searchResults.size(); row++) {
				strUsers = driver.findElement(getReqGroupDetails(String.valueOf(4), String.valueOf(row))).getText();
				strUsers = strUsers.split("Users:")[1];
				reporter.SuccessReport("Approval Management Requestor Group Rules Report Page", "Users Exist",
						strUsers);
			}
		} else {
			reporter.failureReport("Approval Management Requestor Group Rules Report Page", "Users Does Not Exist", "");
		}
	}

	public void VerifyDisplayRules() throws Throwable {
		if (isElementPresent(DISPLAY_RULES, "SEARCH display Rules")) {
			List<WebElement> searchResults = driver.findElements(DISPLAY_RULES);
			int count = searchResults.size();

			String strCartType = null;
			String Currency_Range = null;
			String strMin = null;
			String strMax = null;
			String strApprovalPathName = null;
			String strComm = null;

			if (count > 1) {
				List<WebElement> noOfRows = driver.findElements(Cart_Type);
				int k = 1;
				for (int i = 3; k <= noOfRows.size(); i++) {
					int j = 2;

					strCartType = getText(getReqRulesDetails(String.valueOf(i), String.valueOf(j)), "CartType");
					Currency_Range = getText(getReqRulesDetails(String.valueOf(i), String.valueOf(j + 1)),
							"Currency Range");
					strApprovalPathName = getText(getReqRulesDetails(String.valueOf(i), String.valueOf(j + 2)),
							"Approval Path Name");

					strMin = Currency_Range.split("-")[0];
					strMax = Currency_Range.split("-")[1];

					k = k + 1;
					strComm = "Rules CartType:" + strCartType + " Rules Min:" + strMin + " Rules Max:" + strMax
							+ " Rules ApprovalPathName: " + strApprovalPathName;
					reporter.SuccessReport("Approval Management Requestor Group Rules Report Page", "Rules Exist",
							strComm);
				}

			}

		} else {
			reporter.failureReport("Approval Management Requestor Group Rules Report Page", "Rules Does Not Exist", "");
		}
	}

	public void EnterReqGrpName() throws Throwable {
		if (isElementPresent(REQ_GRP_NAME_TXTBOX, "Req Group Name")) {

			// Get Requestor group name
			String reqGroupName = driver.findElement(getReqGroupDetails(String.valueOf(1), String.valueOf(1)))
					.getText();

			type(REQ_GRP_NAME_TXTBOX, reqGroupName, "Enter Req Group Name");

			reporter.SuccessReport(
					"Enter Requestor Group Name on  Approval Management Requestor Group Rules Report Page",
					"Requestor Group Name Field Exists", reqGroupName);
		} else {
			reporter.failureReport(
					"Enter Requestor Group Name on  Approval Management Requestor Group Rules Report Page",
					"Requestor Group Name Field Does Not Exist", "");
		}
	}

	public void VerifyReqGroupExists() throws Throwable {
		if (isElementPresent(SEARCH_RESULTS, "SEARCH Results table")) {
			String strReqGrpNm = null;
			List<WebElement> appCount = driver.findElements(SEARCH_RESULTS);
			for (int row = 1; row < appCount.size(); row++) {
				strReqGrpNm = driver.findElement(getReqGroupDetails(String.valueOf(1), String.valueOf(row))).getText();
				reporter.SuccessReport("Approval Management Requestor Group Rules Report Page",
						"ReqestorGroupName Exists and Verified", strReqGrpNm);
			}
		} else {
			reporter.failureReport("Approval Management Requestor Group Rules Report Page",
					"ReqestorGroupName Does Not Exist", "");
		}

	}

	public void ClickRequestorGroupSmartTrackerReportLink() throws Throwable {
		if (isElementPresent(REQUESTOR_GROUP_SMARTTRACKER_REPORT, "Requestor Group SmartTracker Report Link")) {
			click(REQUESTOR_GROUP_SMARTTRACKER_REPORT, "Click Requestor Group SmartTracker Report Link");
			reporter.SuccessReport("Approval Management Reports Page",
					"Requestor Group SmartTracker Report Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Management Reports Page",
					"Requestor Group SmartTracker Report Link Does Not Exist", "");
		}
	}

	public void VerifySmartTrackerResults() throws Throwable {
		String strSmartTrackername = null;
		String strReqGroupName = null;
		String strComm = null;
		if (isElementPresent(SMART_TRACKER_TABLE, "Smart Tracker Results table")) {

			List<WebElement> appCount = driver.findElements(SMART_TRACKER_TABLE);

			if (appCount.size() > 1) {
				for (int row = 1; row < appCount.size(); row++) {

					strSmartTrackername = driver.findElement(By.xpath("(//table[@id='StReport']//tbody//tr//td//a)[1]"))
							.getText();
					strReqGroupName = driver.findElement(By.xpath("(//table[@id='StReport']//tbody//tr//td//a)[2]"))
							.getText();
					strComm = "Smart Tracker Name:" + strSmartTrackername + " Requestor Group Name:" + strReqGroupName;
					reporter.SuccessReport("Approval Management Requestor Group Rules Report Page",
							"ReqestorGroupName Exists and Verified", strComm);
				}
			}

		} else {
			reporter.failureReport("Approval Management Requestor Group Rules Report Page",
					"ReqestorGroupName Does Not Exist", "");
		}
	}

	public void ClickSmartTrackerLink() throws Throwable {
		if (isElementPresent(SMART_TRACKER_TABLE, "Requestor Group SmartTracker Report Link")) {

			List<WebElement> appCount = driver.findElements(SMART_TRACKER_TABLE);

			if (appCount.size() > 1) {
				click(By.xpath("(//table[@id='StReport']//tbody//tr//td//a)[1]"),
						"Click Requestor Group SmartTracker Report Link");
			}
			reporter.SuccessReport("Approval Management Reports Page",
					"Requestor Group SmartTracker Report Link Exists and Clicked", "");

			if (isElementPresent(MANAGE_SMART_TRACKER_PAGE, "Smart Tracker Page")) {
				reporter.SuccessReport("Approval Management Reports Page", "Manage SmartTrackers Page Exists", "");
			} else
				reporter.failureReport("Approval Management Reports Page",
						"Requestor Group SmartTracker Report Link Does Not Exist", "");

		} else {
			reporter.failureReport("Approval Management Reports Page", "Manage SmartTrackers  Page Does Not Exist", "");
		}
	}

	public void AddRequestorsRequestorGroup(String ReqGroupName) throws Throwable {
		// Click on Requestor Requestor Group Users
		ClickRequestorGrpLink();

		// Verify
		ClickReqGroupEditLinkButton(ReqGroupName);

		// Click on Manage Requestors
		clickManageRequestors();

		// Click Refresh Icon To display Requestors
		ClickRefreshIcon();

		// Select Requestors
		SelectRequestor(null);

		// Add
		Add_Requestor_Btn_Click();

		// Click Save Changes
		ClickSaveChangesButton();

	}

	public void ClickReqGroupEditLinkButton(String reqGroupName) throws Throwable {
		if (isElementPresent(RequestorGroupEditLink(reqGroupName), "Approval management page ")) {
			click(RequestorGroupEditLink(reqGroupName), "Approval management page");
			reporter.SuccessReport("Click Requestor Group Edit", "Requestor Group Edit Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Requestor Group Edit", "Requestor Group Edit  Link Does Not Exist", "");
		}
	}

	public void clickManageRequestors() throws Throwable {
		if (isElementPresent(MANAGE_REQUESTOR_LINK, "Approval management page ")) {
			click(MANAGE_REQUESTOR_LINK, "Approval management page");
			reporter.SuccessReport("Click Manage Requestors on Create/Edit Requestor Group Page",
					"Manage Requestors Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Manage Requestors on Create/Edit Requestor Group Page",
					"Manage Requestors Link Does Not Exist", "");
		}
	}

	public void ClickRefreshIcon() throws Throwable {
		if (isElementPresent(REFRESH_ICON, "Refresh icon")) {
			click(REFRESH_ICON, "Click Refresh icon");
		}
	}

	public String SelectRequestor(String Requestor_Name) throws Throwable {
		String strRequestorOption = null;
		if (Requestor_Name != null) {
			SelectSpecificRequestor(Requestor_Name);
		} else {
			// select Requestor id value
			List<WebElement> myList = driver.findElements(ALL_REQUESTOR_OPTIONS);

			strRequestorOption = (myList.get(1)).getAttribute("value");

			if (isElementPresent(selectRequestor(strRequestorOption), "Approver ")) {
				selectByValue(createRequestorType(), strRequestorOption, "Approver");
				reporter.SuccessReport("Add Requestors on Create/Edit Requestor Group Page",
						"Requestors Exist in the Available Requestors' List and Selected", strRequestorOption);
			}

			else {
				reporter.failureReport("Add Requestors on Create/Edit Requestor Group Page",
						"Requestors Do Not Exist in the Available Requestors' List", "");
			}

		}
		return strRequestorOption;
	}

	public void Add_Requestor_Btn_Click() throws Throwable {
		if (isElementPresent(SELECT_PATH_ADD_BUTTON, "Select Path option")) {
			click(SELECT_PATH_ADD_BUTTON, "Select Path option");
			reporter.SuccessReport("Add Requestors on Create/Edit Requestor Group Page",
					"Add Arrow Link Exists and Clicked", "");

		} else {
			reporter.failureReport("Add Requestors on Create/Edit Requestor Group Page",
					"Add Arrow Link Does Not Exist", "");
		}
	}

	public void Remove_Requestor_Btn_Click() throws Throwable {
		if (isElementPresent(SELECT_PATH_REMOVE_BUTTON, "Select Path option")) {
			click(SELECT_PATH_REMOVE_BUTTON, "Select Path option");
			reporter.SuccessReport("Remove Requestors on Create/Edit Requestor Group Page",
					"Remove Arrow Link Exists and Clicked", "");

		} else {
			reporter.failureReport("Remove Requestors on Create/Edit Requestor Group Page",
					"Remove Arrow Link Does Not Exist", "");
		}
	}

	public void ClickSaveChangesButton() throws Throwable {
		if (isElementPresent(REQ_SAVE_CHANGES_BTN, "Select Update option")) {
			click(REQ_SAVE_CHANGES_BTN, "Select Update option");
			reporter.SuccessReport("Add Requestors on Create/Edit Requestor Group Page",
					"Save changes button Exists and Clicked", "");
		} else {
			reporter.failureReport("Add Requestors on Create/Edit Requestor Group Page",
					"Save changes button Does Not Exist", "");
		}
	}

	public void UpdatedSuccessMsg() throws Throwable {
		if (isElementPresent(SUCCESS_UPDATED_MSG, "Updated success message")) {
			reporter.SuccessReport("Add Requestors on Create/Edit Requestor Group Page", "Changes updated successfully",
					"");
		} else {
			ClickSaveChangesButton();
			Thread.sleep(2000);
			if (isElementPresent(SUCCESS_UPDATED_MSG, "Updated success message")) {
				reporter.SuccessReport("Add Requestors on Create/Edit Requestor Group Page",
						"Changes updated successfully", "");
			} else {
				reporter.failureReport("Add Requestors on Create/Edit Requestor Group Page",
						"Changes Not updated successfully", "");
			}
		}
	}

	public void ClickRequestorGroupUsersLink() throws Throwable {
		if (isElementPresent(REQUESTOR_GROUP_USERS, "Requestor Group Users Link")) {
			click(REQUESTOR_GROUP_USERS, "Click Requestor Group Users");
			reporter.SuccessReport("Approval Management Reports Page", "Requestor Group Users Link Exists and Clicked",
					"");
		} else {
			reporter.failureReport("Approval Management Reports Page", "Requestor Group Users Link Does Not Exist", "");
		}
	}

	public void ClickRequestorGroupUsersLinkToDeleteRequestor(String ReqGroupName, String reqName) throws Throwable {
		String reqName2 = reqName.split(",")[0];
		if (isElementPresent(SELECT_REQUESTOR_GRP_FROM_SELECTOR(ReqGroupName, reqName2),
				"Requestor Group Users Link ")) {
			click(SELECT_REQUESTOR_GRP_FROM_SELECTOR(ReqGroupName, reqName2), "Click Requestor Group Users");
			reporter.SuccessReport("Approval Management Reports Page", "Requestor Group Users Link Exists and Clicked",
					"");

			// Click on Manage Requestors
			clickManageRequestors();

			// Remove Added Users
			if (isElementPresent(selectRequestorFromAddedList(reqName2), "Approver ")) {
				// selectByVisibleText(selectRequestorType(), reqName2,
				// "Approver");
				click(selectRequestorFromAddedList(reqName2), "Requestor");
				reporter.SuccessReport("Remove Requestors on Create/Edit Requestor Group Page",
						"Requestors Exist in the Available Requestors' List and Selected", reqName);
			} else
				reporter.failureReport("Remove Requestors on Create/Edit Requestor Group Page",
						"Requestors does not Exist in the Available Requestors' List", reqName);

			Remove_Requestor_Btn_Click();

			// Click Save Changes
			ClickSaveChangesButton();

		} else {
			reporter.failureReport("Approval Management Reports Page", "Requestor Group Users Link Does Not Exist", "");
		}
	}

	public void ClickRequestorPageRefreshIcon() throws Throwable {
		if (isElementPresent(REQUESTOR_GRP_REFRESH_LINK, "Refresh icon")) {
			click(REQUESTOR_GRP_REFRESH_LINK, "Click Refresh icon");
		}
	}

	public String DisplayRequestors() throws Throwable {
		String requestor = null;
		String requestorGroup = null;
		String strComm = null;
		if (isElementPresent(GET_REQUESTOR_GROUP_COUNT, "Requestor Group details")) {
			List<WebElement> reqCount = driver.findElements(GET_REQUESTOR_GROUP_COUNT);
			int count = reqCount.size();
			if (count > 1) {
				count = (count * 2) - 2;
				for (int i = 1; i < count; i = i + 2) {
					requestor = driver.findElement(getRequestorName(String.valueOf(i))).getText();
					requestorGroup = driver.findElement(getRequestorName(String.valueOf(i + 1))).getText();
					strComm = "strRequestor:" + requestor.trim() + "\n" + "Requestor Group:" + requestorGroup.trim();
					reporter.SuccessReport("Approval Management Reports Page", "Requestor Group Users Exist", strComm);
				}

			} else {
				reporter.failureReport("Approval Management Reports Page", "Requestor Group Users Do Not Exist", "");
			}
		}

		List<WebElement> reqCount = driver.findElements(GET_REQUESTOR_GROUP_COUNT);
		int count1 = reqCount.size();
		String requestor1 = null;
		if (count1 > 1) {
			requestor1 = driver.findElement(getRequestorName(String.valueOf(1))).getText();

		}

		return requestor1;
	}

	public void VerifyPageCount50() throws Throwable {

		if (isElementPresent(GET_SELECTED_COUNT, "Page count")) {
			String pageCount = getSelectedDropdownOption(GET_SELECTED_COUNT);
			if (pageCount.equals("50")) {
				reporter.SuccessReport("Approval Management Reports Page", "Paging Count is Verified", pageCount);
			}

		} else {
			reporter.failureReport("Approval Management Reports Page", "Paging Count is not Verified", "");
		}
	}

	public void ChangePageCount(String NewPageCount) throws Throwable {
		if (isElementPresent(GET_SELECTED_COUNT, "Page count")) {
			selectByVisibleText(GET_SELECTED_COUNT, NewPageCount, "Update page count");
			reporter.SuccessReport("Approval Management Reports Page", "Paging Field Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Management Reports Page", "Paging Link Does Not Exist", NewPageCount);
		}
	}

	public void ClickNextPageAndVerify50() throws Throwable {
		if (isElementPresent(NEXT_PAGE_LINK, "Next Page ")) {
			click(NEXT_PAGE_LINK, "Click Next Page");
			// Verify the Paging Count 50
			VerifyPageCount50();
			reporter.SuccessReport("Approval Management Reports Page", "Next Page Link Exists and Clicked", "");
		} else {
			reporter.SuccessReport("Approval Management Reports Page",
					"Next Page Link Does Not Exist Because There is No Enough data", "");
		}

	}

	public void ClickTU_IUSRequestorGroupTieredLink() throws Throwable {
		scrollUp();
		if (isElementPresent(TU_IUS_Tired_Requestor_Group_Link, "Requestor Group Name ")) {
			// mouseHover(TU_IUS_Tired_Requestor_Group_Link, "Requestor Group
			// Name");
			// scrollToWebElement(TU_IUS_Tired_Requestor_Group_Link);
			// scrollUp();
			click(TU_IUS_Tired_Requestor_Group_Link, "Click Requestor Group Name");
			reporter.SuccessReport("Approval Management Create/Edit Requestor Group",
					"TU_IUS Requestor Group Tiered Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Management Create/Edit Requestor Group",
					"TU_IUS Requestor Group Tiered Link Does Not Exist", "");
		}
	}

	public void ClickTU_IUSRequestorGroupLink() throws Throwable {
		scrollUp();
		if (isElementPresent(TU_IUS_Requestor_Group_Link, "Requestor Group Name")) {
			mouseHover(TU_IUS_Requestor_Group_Link, "Requestor Group Name");
			click(TU_IUS_Requestor_Group_Link, "Click Requestor Group Name");
			reporter.SuccessReport("Approval Management Create/Edit Requestor Group",
					"TU_IUS Requestor Group Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Management Create/Edit Requestor Group",
					"TU_IUS Requestor Group Link Does Not Exist", "");
		}
	}

	public void Verify_Create_Edit_Requestor_GroupPage() throws Throwable {
		if (isElementPresent(REQ_SAVE_CHANGES_BTN, "Create/Edit Requestor Group Page")) {
			reporter.SuccessReport("Approval Management Create/Edit Requestor Group",
					"Create/Edit Requestor Group Page Exists", "");
		} else {
			reporter.failureReport("Approval Management Create/Edit Requestor Group",
					"Create/Edit Requestor Group Page Does Not Exist", "");
		}
	}

	public void SearchByLastName(String reqLastName) throws Throwable {
		String lastName = reqLastName.split(",")[0];
		if (isElementPresent(REQUESTOR_GRP_NAME_TXTBOX, "Requestor Group name textbox")) {
			type(REQUESTOR_GRP_NAME_TXTBOX, lastName, "Click Requestor Group name textbox");
			reporter.SuccessReport("Approval Management Reports Page", "Last Name or Account Number Field Exists", "");
		} else {
			reporter.failureReport("Approval Management Reports Page",
					"Last Name or Account Number Field Does Not Exist", "");
		}
	}

	public void Click_Search_Icon() throws Throwable {
		if (isElementPresent(SEARCH_REQ_GRP_ICON, "Requestor Group name search icon")) {
			click(SEARCH_REQ_GRP_ICON, "Click Requestor Group name search icon");
			reporter.SuccessReport("Click Search on Approval Management Requestor Group Rules Report Page",
					"SEARCH Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Search on Approval Management Requestor Group Rules Report Page",
					"SEARCH Link Does Not Exist", "");
		}
	}

	public void RemoveUsers(String ReqGroupName, String reqName) throws Throwable {

		// Click on General Settings to click Reports
		ClickGeneralSettings();

		// Click on Reports
		ClickReports();

		// Click on Requestor Requestor Group Users
		ClickRequestorGroupUsersLink();

		// Click Requestor group to delete the member
		ClickRequestorGroupUsersLinkToDeleteRequestor(ReqGroupName, reqName);

	}

	/**
	 * click on Requisition Status Report
	 * 
	 * @throws Throwable
	 */

	public void clickRequisitionStatusReport() throws Throwable {
		if (isElementPresent(REQUISITION_STAUS_REPORT, "Requestor Group Users Link")) {
			click(REQUISITION_STAUS_REPORT, "Click Requestor Group Users");
			reporter.SuccessReport("Approval Management Reports Page",
					"Requisition Status Report Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Management Reports Page", "Requisition Status Report Link Does Not Exist",
					"", driver);
		}
	}

	/**
	 * click on Date Picker for 13 months
	 * 
	 * @throws Throwable
	 */

	public void datePicker(int months, String strCurrDay) throws Throwable {

		String day = strCurrDay.split("-")[0];
		String month = strCurrDay.split("-")[1];
		String year = strCurrDay.split("-")[2];

		click(CALENDAR, "Click on calendar");
		if (isElementPresent(NEXT_MONTH_ARROW, "Next month")) {
			for (int i = 0; i <= months; i++) {
				click(NEXT_MONTH_ARROW, "Next konth");
			}
			// Select Day
			if (isElementPresent(dayInStartDayCalender(day), "From Date ")) {
				click(dayInStartDayCalender(day), "Day");
			}

			reporter.SuccessReport("Change Month on Approval Management Page Approver Out of Office Settings",
					"Arrow Button Exists and Clicked to Change the Month", "");
		} else {
			reporter.failureReport("Change Month on Approval Management Page Approver Out of Office Settings",
					"Arrow Button Does Not Exist", "");
		}
	}

	/**
	 * click on Requisition Status Report
	 * 
	 * @throws Throwable
	 */

	public void clickSearch() throws Throwable {

		click(SEARCH, "Click on search");
	}

	/**
	 * click on Requisition Status Report
	 * 
	 * @throws Throwable
	 */

	public void verifyMessage(String actualtext) throws Throwable {

		String expectedtext = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		if (expectedtext.equalsIgnoreCase(actualtext)) {
			reporter.SuccessReport("Approval Management Requisition Status Reports Page",
					"Report cannot be retrieved for more than one year", "");
		} else {
			reporter.failureReport("Approval Management Requisition Status Reports Page",
					"Report cannot be retrieved for more than one year does not exist", "", driver);
		}

	}

	public void changeFilterStatus(String filters) throws Throwable {
		if (isElementPresent(FILTER_BY_STATUS, "Filter by Statsus")) {
			selectByVisibleText(FILTER_BY_STATUS, filters, "Filter Status");
			reporter.SuccessReport("Approval Management Reports Page", "FilterStatus is changed ", "");
		} else {
			reporter.failureReport("Approval Management Reports Page", "FilterStatus is not selected ", "");
		}
	}

	public void displayRequestorsRecords() throws Throwable {
		String RequestedDate = null;
		String OrderDate = null;
		String Requestor = null;
		String RequestorGroup = null;
		String Approver = null;
		String DaysOpen = null;
		String reference = null;
		String status = null;
		String order = null;

		if (isElementPresent(RESULTS_TABLE, "Requestor Group details")) {
			int i = 1;
			RequestedDate = driver.findElement(getRequestortableDetails(i, i)).getAttribute("innerText");
			OrderDate = driver.findElement(getRequestortableDetails(i, i + 1)).getText();
			Requestor = driver.findElement(getRequestortableDetails(i, i + 2)).getText();
			RequestorGroup = driver.findElement(getRequestortableDetails(i, i + 3)).getText();
			Approver = driver.findElement(getRequestortableDetails(i, i + 4)).getText();
			DaysOpen = driver.findElement(getRequestortableDetails(i, i + 5)).getText();
			reference = driver.findElement(getRequestortableDetails(i, i + 6)).getText();
			status = driver.findElement(getRequestortableDetails(i, i + 7)).getText();
			order = driver.findElement(getRequestortableDetails(i, i + 8)).getText();

			reporter.SuccessReport("Approval Management Requisition Status Reports Page",
					"RequestedDate :" + RequestedDate + "OrderDate : " + OrderDate + " Requestor : " + Requestor
							+ " RequestorGroup: " + RequestorGroup + " DaysOpen : " + DaysOpen + "  reference : "
							+ reference + " status : " + status + " Approver : " + Approver + " ",
					"");

		} else {
			reporter.failureReport("Approval Management Reports Page", "Requestor Group Users Do Not Exist", "");
		}
	}

}
