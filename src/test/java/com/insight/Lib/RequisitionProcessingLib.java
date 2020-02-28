package com.insight.Lib;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.OrderObj;

import org.openqa.selenium.By;


import com.insight.ObjRepo.RequisitionProcessingObj;

public class RequisitionProcessingLib extends RequisitionProcessingObj {
	OrderLib orderLib = new OrderLib();
	public void verifyApprovalManagementPage() throws Throwable {
		waitForVisibilityOfElement(RequisitionProcessingObj.APPROVAL_MANAGEMENT_PAGE,
				"Approval management page display");

		if (isElementPresent(APPROVAL_MANAGEMENT_PAGE, "Approval management page")) {
			reporter.SuccessReport("Verify Approval Management  Page", "Approval Management Page is Exists", "");
		} else {
			reporter.failureReport("Verify Approval Management  Page", "Approval Management Page does not Exists", "");
		}

	}

	public void clickTestRulesLink() throws Throwable {
		waitForVisibilityOfElement(TESTRULES_EDIT_LINK, "TestRules Edit link click");

		if (isElementPresent(TESTRULES_EDIT_LINK, "TestRules Edit link click")) {
			click(TESTRULES_EDIT_LINK, "TestRules Edit link click");
			reporter.SuccessReport("Verify TestRules Edit link click in Approval Management  Page",
					"TestRules Edit link clicked", "");
		} else {
			reporter.failureReport("Verify TestRules Edit link click in Approval Management  Page",
					"TestRules Edit link could not clicked", "");
		}

	}

	public void clickTestApprovalRulesLink() throws Throwable {
		waitForVisibilityOfElement(TEST_APPROVAL_RULES_BTN, "Test Approval Rules link click");
		{
			click(TEST_APPROVAL_RULES_BTN, "Test Approvals Rules Amount");
		}

		if (isElementPresent(TEST_APPROVAL_RULES_BTN, "Test Approval Rules link click")) {
			reporter.SuccessReport("Click  Test Approval Rules  on Approval Management Approval Path Management Page",
					"Test Approval Rules  Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click  Test Approval Rules  on Approval Management Approval Path Management Page",
					"Test Approval Rules  Link Does Not Exists", "");
		}

	}

	public void clickSmartTrackerRulesLink() throws Throwable {
		waitForVisibilityOfElement(SMARTTRACKERRULES_EDIT_LINK, "Smart Tracker Rules Edit link click");

		if (isElementPresent(SMARTTRACKERRULES_EDIT_LINK, "Smart Tracker Rules Edit link click")) {
			click(SMARTTRACKERRULES_EDIT_LINK, "Smart Tracker Rules Edit link click");
			reporter.SuccessReport("Verify Smart Tracker Rules Edit link click in Approval Management  Page",
					"Smart Tracker Rules Edit link clicked", "");
		} else {
			reporter.failureReport("Verify Smart Tracker Rules Edit link click in Approval Management  Page",
					"Smart Tracker Rules Edit link could not clicked", "");
		}

	}

	public void verifyRules(String amount, String cart_type, String result_path, boolean isNoApprovalPath)
			throws Throwable {
		if (isElementPresent(AMOUNT, "Test Approvals Rules Amount")) {
			type(AMOUNT, amount, "Test Approvals Rules Amount");
		}
		if (isElementPresent(CART_TYPE, "Test Approvals Rules Cart Type")) {
			selectByVisibleText(CART_TYPE, cart_type, "Test Approvals Cart Type");
		}
		if (isElementPresent(SHOW_RESULTS_BTN, "Test Approvals Rules show results button")) {
			click(SHOW_RESULTS_BTN, "Click Test Approvals Rules show results button");
		}

		if (!isNoApprovalPath) {
			if (isElementPresent(getResultPath(result_path), "Test Approvals Rules result path")) {
				String pathName = getText(getResultPath(result_path), "Test Approvals result path");

				if (pathName.contains(result_path)) {
					reporter.SuccessReport("Evaluating rules in this order:Cart Type, Amount, Custom Routing List",
							"Approval Path Results are Verified for amount : " + amount + ", cart type : " + cart_type
									+ ", Result " + result_path,
							"");
				} else {
					reporter.failureReport("Evaluating rules in this order:Cart Type, Amount, Custom Routing List",
							"Approval Path Results are not Verified for amount : " + amount + ", cart type : "
									+ cart_type + ", Result " + result_path + " is failed",
							"");
				}
			}
		} else {
			if (isElementPresent(getNoApprovalResultPath(result_path), "Test Approvals Rules result path")) {
				String pathName = getText(getNoApprovalResultPath(result_path), "Test Approvals result path");

				if (pathName.contains(result_path)) {
					reporter.SuccessReport("Evaluating rules in this order:Cart Type, Amount, Custom Routing List",
							"Approval Path Results are Verified for amount : " + amount + ", cart type : " + cart_type
									+ ", Result " + result_path,
							"");
				} else {
					reporter.failureReport("Evaluating rules in this order:Cart Type, Amount, Custom Routing List",
							"Approval Path Results are not Verified for amount : " + amount + ", cart type : "
									+ cart_type + ", Result " + result_path + " is failed",
							"");
				}
			}
		}

	}

	public void clickOptionalRules() throws Throwable {
		click(OPTIONAL_RULES_LINK, "Optional Rules Link");
	}

	public void closeTestApprovalRulesWindow() throws Throwable {
		click(CLOSE_TEST_APPROVAL, "Close Test Approval Rules Window");
	}

	public void backtoReqSearch() throws Throwable {
		if (isElementPresent(BACKTO_REQSEARCH, "Back To Requestor Group Search")) {
			// click(BACKTO_REQSEARCH, "Click Back To Requestor Group Search
			// link");

			clickUntil(BACKTO_REQSEARCH, SMARTTRACKERRULES_EDIT_LINK, "Tab name of Requestor group");

		}
	}

	public void clickAddRule() throws Throwable {
		click(ADD_RULE, "Add Rules Link");
	}

	public void clickDeleteRule(String item) throws Throwable {
		if (item == "First") {
			if (isElementPresent(deleteRule("1"), "Delete rule icon")) {
				click(deleteRule("1"), "Delete rule icon");
				if (isElementPresent(sureDelete("1"), "Delete rule icon")) {
					click(sureDelete("1"), "Delete rule icon");
				}
			}
		}
		
		if (item == "Second") {
			if (isElementPresent(deleteRule("2"), "Delete rule icon")) {
				click(deleteRule("2"), "Delete rule icon");
				if (isElementPresent(sureDelete("2"), "Delete rule icon")) {
					click(sureDelete("2"), "Delete rule icon");
				}
			}
		}
		
		if (item == "Third") {
			if (isElementPresent(deleteRule("1"), "Delete rule icon")) {
				click(deleteRule("1"), "Delete rule icon");
				if (isElementPresent(sureDelete("3"), "Delete rule icon")) {
					click(sureDelete("3"), "Delete rule icon");
				}
			}
		}

		/*if (isElementPresent(deleteRule(item), "Delete rule icon")) {
			click(deleteRule(item), "Delete rule icon");

			if (isElementPresent(sureDelete(item), "Delete rule icon")) {
				click(sureDelete(item), "Delete rule icon");
				reporter.SuccessReport("Verify Delete Created Rules  on Create/Edit Requestor Group",
						"Created Rules are Deleted", "");
			}
		} else {
			reporter.failureReport("Verify Delete Created Rules  on Create/Edit Requestor Group",
					"Created Rules are Not Deleted", "");
		}*/
	}

	public void createRule(String cart_type, String Min_Amt, String Max_Amt, String path, String item)
			throws Throwable {
		// Click Add Rule

		clickAddRule();

		if (isElementPresent(createCartType(item), "Test Approvals Rules Cart Type")) {
			selectByVisibleText(createCartType(item), cart_type, "Test Approvals Cart Type");
		}

		if (isElementPresent(createMinAmt(item), "Test Approvals Rules Min Amount")) {
			type(createMinAmt(item), Min_Amt, "Test Approvals Rules Min Amount");
		}
		if (isElementPresent(createMaxAmt(item), "Test Approvals Rules Max Amount")) {
			type(createMaxAmt(item), Max_Amt, "Test Approvals Rules Max Amount");
		}
		if (isElementPresent(createPath(item), "Test Approvals Rules validation path")) {
			selectByVisibleText(createPath(item), path, "Test Approval Rules Validation Path");
		}

		if (isElementPresent(saveRule(item), "Click on save rule icon")) {
			click(saveRule(item), "Click on save rule icon");
		}

	}

	public void verifySuccessMsg() throws Throwable {
		waitForVisibilityOfElement(RULE_SUCCESS_CREATE_MSG, "Smart Tracker Rules Edit link click");

		if (isElementPresent(RULE_SUCCESS_CREATE_MSG, "Routing rule successfully created Message")) {
			reporter.SuccessReport("Verify Routing rule successfully created Message",
					"Routing rule successfully created is Exists", "");
		} else {
			reporter.failureReport("Verify Routing rule successfully created Message",
					"Routing rule successfully created Not Exists", "");
		}
	}

	public void clickCreateRulesLink() throws Throwable {
		waitForVisibilityOfElement(CREATERULES_EDIT_LINK, "CreateRules Edit link click");

		if (isElementPresent(CREATERULES_EDIT_LINK, "CreateRules Edit link click")) {
			click(CREATERULES_EDIT_LINK, "TestRules Edit link click");
			reporter.SuccessReport("Verify CreateRules Edit link click in Approval Management  Page",
					"TestRules Edit link clicked", "");
		} else {
			reporter.failureReport("Verify CreateRules Edit link click in Approval Management  Page",
					"TestRules Edit link could not clicked", "");
		}

	}

	public void selectWithRuleListingFactor(String createRuleWithListOption) throws Throwable {
		if (isElementPresent(WITHRULE_LISTING_FACTOR, "Create Rule With List")) {
			selectByVisibleText(WITHRULE_LISTING_FACTOR, createRuleWithListOption, "Create Rule With List");
		}
	}

	public void selectSmartTrackerWithHDLList(String selectSmartListOption) throws Throwable {
		if (isElementPresent(SMARTRULE_LISTING_OPTION, "Smart Tracker HDList Option")) {
			selectByVisibleText(SMARTRULE_LISTING_OPTION, selectSmartListOption, "Smart Tracker HDList Option");
		}
	}

	public void clickAdRouteButton() throws Throwable {
		if (isElementPresent(ADD_ROUTE_BTN, "Add Route Button")) {
			click(ADD_ROUTE_BTN, "Add Route Button click");
		}
	}

	public void selectHDLListOption(String hdlllistoption) throws Throwable {
		if (isElementPresent(HDLLIST_OPTION, "Select HDLLIst option")) {
			selectByVisibleText(HDLLIST_OPTION, hdlllistoption, "Select HDLLIst option");
		}
	}

	public void addRuleWithList(String List_Option, String cart_type, String Min_Amt, String Max_Amt, String path,
			String item) throws Throwable {
		if (isElementPresent(ADD_RULE_WITH_LIST, "ADD RULE Button")) {
			click(ADD_RULE_WITH_LIST, "ADD RULE Button");

			if (isElementPresent(createListValue(item), "Test Approvals Rules Cart Type")) {
				selectByVisibleText(createListValue(item), cart_type, "Test Approvals Cart Type");
			}

			if (isElementPresent(createMinAmt(item), "Test Approvals Rules Min Amount")) {
				type(createMinAmt(item), Min_Amt, "Test Approvals Rules Min Amount");
			}
			if (isElementPresent(createMaxAmt(item), "Test Approvals Rules Max Amount")) {
				type(createMaxAmt(item), Max_Amt, "Test Approvals Rules Max Amount");
			}
			if (isElementPresent(createApprovalPathWithList(item), "Test Approvals Rules validation path")) {
				selectByVisibleText(createApprovalPathWithList(item), path, "Test Approval Rules Validation Path");
			}

			if (isElementPresent(saveRuleWithList(item), "Click on save rule icon")) {
				click(saveRuleWithList(item), "Click on save rule icon");
			}

		}
	}
	
	public void deleteRoutingOption() throws Throwable {

		if (isElementPresent(By.xpath("//a[@class='deleteRoute']"), "Delete routing option")) {
			click(By.xpath("//a[@class='deleteRoute']"), "Delete routing option");
			if (isElementPresent(By.xpath("(//div[@class='buttons dbDiv']//a//span[contains(text(),'Delete')])[5]"),
					"Delete rule icon")) {
				click(By.xpath("(//div[@class='buttons dbDiv']//a//span[contains(text(),'Delete')])[5]"),
						"Delete rule icon");
			}
		}

	}

	public void backtoReqSearchForCreateRules() throws Throwable {
		if (isElementPresent(BACKTO_REQSEARCH, "Back To Requestor Group Search")) {
			// click(BACKTO_REQSEARCH, "Click Back To Requestor Group Search
			// link");

			clickUntil(BACKTO_REQSEARCH, CREATERULES_EDIT_LINK, "Tab name of Requestor group");

		}
	}

	
	public void checkDenyRadioBtn(String text)  throws Throwable{
		click(DENY_RADIOBTN,"Check Deny radio button");
	    type(TEXT_BOX,text,"Deneyed request");
	}
	/**
	 * 
	 * @param text
	 * @throws Throwable
	 */
	public void verifyDenyedstatusRefNum(String status,String refNum)  throws Throwable{
		if(isElementPresent(SEARCH_HDR,"Requisition search header")){
			if(isElementPresent(STATUS_DROPDOWN,"Requisition status dropdown")){
				selectByVisibleText(STATUS_DROPDOWN,status,"Requisition status dropdown");
				reporter.SuccessReport("Select Requsition Status on  Requisition Search Results Page",
						"Requsition Status Field Exist Selected","");
			} else {
				reporter.failureReport("Select Requsition Status on  Requisition Search Results Page",
						"Requsition Status Not Exists","");
			}
			 type(REFERENCE_TEXTBOX,refNum,"Deneyed request");
			 reporter.SuccessReport("Enter Reference Number on Requisition Search Results Page",
						"Reference Number Field Exist Entered","");
			 click(SERACH1_BTN,"Search button");
			 reporter.SuccessReport("Click  SEARCH  on Requisition Search Results Page",
						"SEARCH Link Exists and Clicked","");
		}else {
			reporter.failureReport("Verify search on  Requisition Search Results Page",
					"Requsition search header Not Exists","");
			Thread.sleep(3000);
			acceptAlert();
	}
	}
	
	/**
	 * 
	 * @param orderLink
	 * @throws Throwable
	 */
	public void verifyorderNumLinkinDeneyedorders(String orderLink) throws Throwable {
		if(isElementPresent(OrderObj.ReferenceLink(orderLink), "Deneyed Reference Number Link")){
			reporter.SuccessReport("Verify  Ref Number on Requisition on Denied Req List",
					"Ref Number Link Exists","");
		} else {
			reporter.failureReport("Verify  Ref Number on Requisition on Denied Req List",
					"Ref Number Link Not Exists","");
		}
		}
	/**
	 * 
	 * @param text
	 * @throws Throwable
	 */
	public void verifyDeneyedStatusandUpdate(String text) throws Throwable {
	if (isElementPresent(OrderObj.APPROVAL_MNGMNT_HDR2, "ApprovalManagement Header")) {
		reporter.SuccessReport("Verify Approval Management  Page", "Approval Management Page Exist","");
	} else
		reporter.failureReport("Verify Approval Management  Page", "Approval Management Page does Not Exists","");
	checkDenyRadioBtn(text);
	if(click(OrderObj.UPDATE_BTN, "Update Button")){
		reporter.SuccessReport("Verify Approval Management  Page", "Requisition placed has been Deneyed.","");
	} else
		reporter.failureReport("Verify Approval Management  Page", "Requisition not Denyed.","");
	}
	
	/**
	 * This method is to check deny radio button in approval management page
	 * @throws Throwable
	 */
	public void selectRequestorGroupName(String ReqName)  throws Throwable{
		click(REQ_GRP_DROPDOWN,"Requestor group name dropdown");
		waitForVisibilityOfElement(REQ_GRP_DROPDOWN_TYPE,"Requestor Group Name dropdown");
	    type(REQ_GRP_DROPDOWN_TYPE,ReqName,"Selected Requestor Group Name");
	    WebElement element = driver.findElement(REQ_GRP_DROPDOWN_TYPE);
		element.sendKeys(Keys.ENTER);
	}
	/**
	 * This method is to check deny radio button in approval management page
	 * @throws Throwable
	 */
	public void clearPhoneNumber()  throws Throwable{
		String PNum= getText(PHNMBR_TEXTBOX,"Phone number text box with PhoneNum");
		clearData(PHNMBR_TEXTBOX);
		if (PNum.isEmpty()) {
			reporter.SuccessReport("Verify Phone Number", "Phone Number cleared and empty","");
		} else
			reporter.failureReport("Verify Phone Number", "Phone number field is not empty","");
	}
	/**
	 * 
	 * @param orderLink
	 * @throws Throwable
	 */
	public void verifyorderNumLinkinReqHistoryPage(String orderLink) throws Throwable {
		if (isElementPresent(OrderObj.APPROVAL_MNGMNT_HDR1, "ApprovalManagement Header")) {
		if(isElementNotPresent(OrderObj.ReferenceLink(orderLink), "Approved Reference Number Link")){
			reporter.SuccessReport("Verify  Ref Number on Requisition List",
					"Ref Number Link Not Exists","");
		} else {
			reporter.failureReport("Verify  Ref Number on Requisition List",
					"Ref Number Link Exists","");
		}
		}
	}
	
	/**
	 * 
	 * @param PONum
	 * @param PORelese
	 * @throws Throwable
	 */
	public void enterPOandPORelease(String PONum,String PORelese) throws Throwable {
		clearData(PO_NUMBER);
		type(PO_NUMBER,PONum,"PO Number");
		type(PO_RELEASE,PORelese,"PO Release Number");
		click(OrderObj.UPDATE_BTN1, "Update Button");
	}
	/**
	 * 
	 * @return
	 * @throws Throwable
	 */
	public String getPONum() throws Throwable {
		String PONum =getText(PO_NUMBER, "Text in PO Number");
		
		return PONum;
	}
	/**
	 * 
	 * @return
	 * @throws Throwable
	 */
	public String getPO_ReleaseNum() throws Throwable {
		String PORelNum =getText(PO_RELEASE, "Text in PO Release Number");
		
		return PORelNum;
	}
	/**
	 * 
	 * @param text
	 * @param PONum
	 * @param PORelese
	 * @throws Throwable
	 */
	public void enterPOandPOReleaseandUpdate() throws Throwable {
		if(click(OrderObj.UPDATE_BTN, "Update Button")){
		reporter.SuccessReport("Verify Approval Management  Page", "Requisition placed has been updated.","");
	} else
		reporter.failureReport("Verify Approval Management  Page", "Requisition not updated.","");
	
	click(OrderObj.CONTINUE_BTNIN, "Continue Button");
	reporter.SuccessReport("Approve Requisition", "Continue Button Exists and Clicked","");
	navigateToBackPage();
	if (isElementPresent(OrderObj.APPROVAL_MNGMNT_HDR2, "ApprovalManagement Header")) {
		if (isElementPresent(OrderObj.APPROVED_STATUS, "ApprovalManagement Header")) {
			reporter.SuccessReport("Verify Requisition Status on  Approval Management Page ",
					"Requisition Aprroved","");
		} else
			reporter.failureReport("Verify Requisition Status on  Approval Management Page ",
					"Requisition Not Aprroved","");
}
	}
	public void verifyPOandPORelease(String refNum,String PO,String PORelease) throws Throwable {
		String PONUM_OH= getText(PONumInOrderHistory(refNum),"PO Number in order history page");
		String PORELEASE_OH = getText(PONumInOrderHistory(refNum),"PO Number in order history page");
		if(PONUM_OH.contains(PO)){
			reporter.SuccessReport("Verify PO Number in Order History ", "PO Number: " , PONUM_OH);
		}else{
			reporter.failureReport("Verify PO Number in Order History", "PO Number not updated correctly. ","");
		}
		if(PORELEASE_OH.contains(PORelease)){
			reporter.SuccessReport("Verify PO Release Number in Order History", "PO Release Number: " , PORELEASE_OH);
		}else{
			reporter.failureReport("Verify PO Release Number in Order History", "PO Release Number is not updated correctly. ","");
		}
	}
	public void enterNewCardInformation(String cardtype,String cardNum,String cardName,String month,String year) throws Throwable {
		click(OrderObj.CONTINUE_BTNIN, "Continue Button");	
		acceptAlert();
		click(NEW_CARD,"Enter New card");
		selectByVisibleText(CARD_TYPE, cardtype, "Type of card");
		type(CARD_NMBR,cardNum,"Card Number");
		type(CARD_NAME,cardName,"Card Name");
		selectByVisibleText(CARD_MONTH, month, "Expiration month");
		//selectByVisibleText(CARD_YEAR, year, "Expiration year");
		click(OrderObj.CONTINUE_BTNIN, "Continue Button");	
	}
	/**
	 * 
	 * @param RefNumber
	 * @throws Throwable
	 */
	public void verifyApproveRequisitionStatus() throws Throwable {
		navigateToBackPage();
		if (isElementPresent(OrderObj.APPROVAL_MNGMNT_HDR2, "ApprovalManagement Header")) {
			if (isElementPresent(OrderObj.APPROVED_STATUS, "ApprovalManagement Header")) {
				reporter.SuccessReport("Verify Requisition Status on  Approval Management Page ",
						"Requisition Aprroved","");
			} else
				reporter.failureReport("Verify Requisition Status on  Approval Management Page ",
						"Requisition Not Aprroved","");
	}
	}
	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyCreditCardErrorMessages() throws Throwable {
		if (isElementPresent(CARD_ERRORMESSAGE, "Credit card validations")) {
			reporter.SuccessReport("Verify Credit Card Validation on Ship, Bill & Pay :",
					"Verify Required fields can not be blank Exist Credit Card Information Required","");
		} else
			reporter.failureReport("Verify Credit Card Validation on Ship, Bill & Pay :",
					"Verify Required fields can not be blank Does Not Exist","");
		}
/**
 * 
 * @throws Throwable
 */
	public void verifyApprovalMangmntPagefromSideMenu() throws Throwable {
		if (isElementPresent(APPROVAL_MNGMNT2, "Approval Management Page")) {
			reporter.SuccessReport("Verify Approval Management  Page",
					"Approval Management Page is Exists","");
		} else
			reporter.failureReport("Verify Approval Management  Page",
					"Approval Management Page is Not Exists","");		
		}
		/**
		 * 
		 * @param GroupName
		 * @throws Throwable
		 */
	public void clickOnRequestorGroupID(String GroupName) throws Throwable {
		if (isElementPresent(REQUESTOR_GROUPTAB, "Requestor Group Tab")) {
			click(REQUESTOR_GROUPTAB, "Requestor Group Tab");	
			click(RequestorGroupEditBtn(GroupName), "Requestor group Edit Button");	
		}
	}
	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyCreate_EditRequestoreGrpGeader() throws Throwable {
		if (isElementPresent(CREATE_EDITREQUESTOR_GROUPHDR, "Create/Edit Requestor Group")) {
			reporter.SuccessReport("Verify Create/Edit Requestor Group Page",
					"Create/Edit Requestor Group page is Exists","");
		} else
			reporter.failureReport("Verify Create/Edit Requestor Group Page",
					"Create/Edit Requestor Group Page is Not Exists","");		
		}

	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyApproverPathSettings() throws Throwable {
		if (isElementPresent(APPROVEPATHSETTINGS_TAB, " Approver Path Settings Tab")) {
			click(APPROVEPATHSETTINGS_TAB, "Approver Path Settings Tab");
		}
		}
		/**
		 * 
		 */
		public void verifyApproverPathSettingsWithRadioCheck() throws Throwable {
			if (isCheckBoxSelected(NO_RADIOBTN)) {
				reporter.SuccessReport("Verify Radio Option",
						"No - approvers may approve in any order and the final approver may "
						+ "approve the requisition to an order at any time  Radio Option Selected","");
			} else
				reporter.failureReport("Verify Radio Option",
						"No - approvers may approve in any order and the final approver may approve the requisition to an order at any time  Radio Option Not Selected","");
			click(NO_RADIOBTN, "RADIO BUTTON CHECKED");	
			LOG.info("Radio Button checked");
			click(SAVE_CHANGESBTN, "Save changes button");	
			
	}
	/**
	 * 
	 * @throws Throwable
	 */
   public  void getCheckboxesCount()  throws Throwable {
	if (isElementPresent(CHECK_BOX, "Checkboxes checked")) {
		List<WebElement> checkList = driver.findElements(CHECK_BOX);		
		for (int j = 1; j < checkList.size(); j++) {
		if(!checkList.get(j).isSelected()){
			click(checkboxes(j),"Check particular checkbox");
			reporter.SuccessReport("Select All the Check Boxes For Allow approver to Edit: on Approval Path Settings",
					"Selected  All the Check Boxes For Allow approver to Edit: on Approval Path Settings","");
		}else
			reporter.failureReport("Select All the Check Boxes For Allow approver to Edit: on Approval Path Settings",
					"Select All the Check Boxes For Allow approver to Edit: on Approval Path Settings Not Exist","");
		}
		click(SAVE_CHANGESBTN, "Save changes button");	
	}
	}

   /**
    * 
    * @throws Throwable
    */
   public void verifySuccessMessageForApproverPathSettings() throws Throwable {
		if (isElementPresent(SUCCESSFUL_MESSG, "Create/Edit Requestor Group success message after updation")) {
			reporter.SuccessReport("Verify Confirmation Message",
					"Successfully updated approval path settings is Exists","");
		} else
			reporter.failureReport("Verify Confirmation Message",
					"Successfully updated approval path settings is Exists","");		
		}
			
   public void verifyEditPaymentInfoLink() throws Throwable {
		if (isElementPresent(EDIT_PAYMENT, "Edit Payment Information Link")) {
			reporter.SuccessReport("Verify Link on Requisition Page",
					"Link Exists and Verfied","");
		} else
			reporter.failureReport("Verify Link on Requisition Page",
					"Link  Not Exists","");		
		}
   public void verifyEditBillingInfoLink() throws Throwable {
		if (isElementPresent(EDIT_BILLING, "Edit Billing Information Link")) {
			reporter.SuccessReport("Verify Link on Requisition Page",
					"Link Exists and Verfied","");
		} else
			reporter.failureReport("Verify Link on Requisition Page",
					"Link  Not Exists","");		
		}
   public void verifyEditShippingInfoLink() throws Throwable {
		if (isElementPresent(EDIT_SHIPPING, "Edit Shipping Information Link")) {
			reporter.SuccessReport("Verify Link on Requisition Page",
					"Link Exists and Verfied","");
		} else
			reporter.failureReport("Verify Link on Requisition Page",
					"Link  Not Exists","");		
		}
   public void verifyEditCarrierInfoLink() throws Throwable {
		if (isElementPresent(EDIT_CARRIER, "Edit Carrier Link")) {
			reporter.SuccessReport("Verify Link on Requisition Page",
					"Link Exists and Verfied","");
		} else
			reporter.failureReport("Verify Link on Requisition Page",
					"Link  Not Exists","");		
		}
   public void verifyCustomFieldsLink() throws Throwable {
		if (isElementPresent(CUSTOM_FIELDS, "Custom Fields Link")) {
			reporter.SuccessReport("Verify Link on Requisition Page",
					"Link Exists and Verfied","");
		} else
			reporter.failureReport("Verify Link on Requisition Page",
					"Link  Not Exists","");		
		}
   public void verifyEditCartContentsLink() throws Throwable {
		if (isElementPresent(EDITCART_CONTENTS, "Edit Cart Content Link")) {
			reporter.SuccessReport("Verify Link on Requisition Page",
					"Link Exists and Verfied","");
		} else
			reporter.failureReport("Verify Link on Requisition Page",
					"Link  Not Exists","");		
		}
   public void verifyAllLinksInApprovalManagmntPage() throws Throwable {
	   verifyEditPaymentInfoLink();
	   verifyEditBillingInfoLink();
	   verifyEditShippingInfoLink();
	   verifyEditCarrierInfoLink();
	   verifyCustomFieldsLink();
	   verifyEditCartContentsLink();
   }
   
   public void verifyApprovalManagmentPage() throws Throwable {
		if (isElementPresent(OrderObj.APPROVAL_MNGMNT_HDR2, "ApprovalManagement Header")) {
			reporter.SuccessReport("Verify Approval Management  Page", "Approval Management Page Exist","");
		} else
			reporter.failureReport("Verify Approval Management  Page", "Approval Management Page does Not Exists","");
   }
   public void clickUpdateInApprovalManagmentPage() throws Throwable {
		if(click(OrderObj.UPDATE_BTN, "Update Button")){
			reporter.SuccessReport("Verify Approval Management  Page", "Requisition placed has been Approved.","");
		} else
			reporter.failureReport("Verify Approval Management  Page", "Requisition not Approved.","");
		}
   
   public void clickEditPaymentsAndVerify(String cardType) throws Throwable {
	   click(EDIT_PAYMENT, "Edit Payment Information Link");
	   selectByVisibleText(CREDITCARD_TYPE,cardType,"Type of card");
	   click(UPDATE_BTN_PAYMENT, "Update Button in Edit Payemnts");
	   click(BACK_BTN_PAYMENT, "Back Button in Edit Payemnts");
	   if (isElementPresent(VERIFY_CARDTYPE, "Changed card Type")) {
		   String[] cardtype=getText(VERIFY_CARDTYPE, "Card Type").split(":");
			if(cardtype[1].equals(cardType)){
				reporter.SuccessReport("Verify Payment Options are Updated", "Payment Type Procurement Card Updated and Verfied","");
			}else{
				reporter.failureReport("Verify Payment Options are Updated", "Payment Type Procurement Card Not Updated","");
			}
	   }
	   
   }
   
   public void clickEditShipping() throws Throwable {
	   click(EDIT_SHIPPING, "Edit shipping Address");
	   click(CHANGE_SHIPPINGOPTION, "Change shipping Address");
	   if (isElementPresent(STATUS_MESSAGE, "Shipping Address success Message")) {
			reporter.SuccessReport("Modify Shipping Address on  Requisition", "Shipping Address Modified","");
		} else
			reporter.failureReport("Modify Shipping Address on  Requisition", "Shipping Address Link  Not Exists","");   
   }
   
   public void clickEditBillingAddress() throws Throwable {
	   click(EDIT_BILLING, "Change Billing Address");
	   if (isElementPresent(BILLING_HDR, "Billing Address Header Message in Popup")) {
		   click(BILLING_CLOSEICON, "Change Billing Address popup close icon");
		   reporter.SuccessReport("Modify Billing Address on  Requisition", "Billing Address Modified","");
		} else
			reporter.failureReport("Modify Billing Address on  Requisition", "Billing Address Link  Not Exists",""); 
	   }
		   
	   
   public void clickCartContents(String QtyNum) throws Throwable {
	   click(EDITCART_CONTENTS, "Edit cart contents link");
	   click(EDIT_ICON, "Edit icon link");
	   clearData(UPDATE_QTY);
	   type(UPDATE_QTY,QtyNum,"Update Quantity Number");
	   click(SAVE_ICON, "Save icon link");
	   if(isEnabled(APPLY_CHANGES, "Apply Changes link")){
	   click(APPLY_CHANGES, "Apply Changes link");
	   }	   
   }
   public void verifyQuantityInCartContents() throws Throwable {
	   String Qntity= getText(QNTY_INCARTITEMS, "Updated quantity");
	   if(Qntity==null){
		   reporter.failureReport("Verify Updated Qunatity in the Cart", " Quantity is Verfied and not Updated",Qntity); 
	   }else
	   reporter.SuccessReport("Verify Updated Qunatity in the Cart", "Updated Quantity is Verfied in the Cart",Qntity);
   }
   
   /**
	 * 
	 * @throws Throwable
	 */
  public void UnCheckAllCheckboxesCount()  throws Throwable {
	if (isElementPresent(CHECK_BOX, "Checkboxes checked")) {
		List<WebElement> myList = driver.findElements(CHECK_BOX);		
		for (int m = 1; m < myList.size(); m++) {
		if(myList.get(m).isSelected()){
			click(checkboxes(i),"Checkboxes");
			for (int j = 1; j < myList.size(); j++) {
				if(!myList.get(j).isSelected()){
			reporter.SuccessReport("Select All the Check Boxes For Allow approver to Edit: on Approval Path Settings",
					"Unchecked  All the Check Boxes For Allow approver to Edit: on Approval Path Settings","");
			
		}else
			reporter.failureReport("Select All the Check Boxes For Allow approver to Edit: on Approval Path Settings",
					"Unchecked All the Check Boxes For Allow approver to Edit: on Approval Path Settings Not Exist","");	
		click(SAVE_CHANGESBTN, "Save changes button");	
	}
	}
}
	}
  }
  
  /**
	 * this method is to enter new card details
	 * @throws Throwable
	 */
   public void enterNewcarDetails(String cardtype,String cardNum,String cardName) throws Throwable {
	   
	   selectByVisibleText(CARD_TYPE, cardtype, "Type of card");
	   typeText(CARD_NMBR,cardNum,"Card Number");
	   typeText(CARD_NAME,cardName,"Card Name");
   }
   /**
  	 * this method is to click continue in payments and cards method
  	 * @throws Throwable
  	 */
   public void continuebutton()throws Throwable {
		click(OrderObj.CONTINUE_BTNIN, "Continue Button");
   }
   
   public void selectRequestorgrpNameFromList(String ReqName)  throws Throwable {
		if (isElementPresent(REQUESTOR_SELECTED(ReqName), "Requestor Group")) {
			click(REQUESTOR_SAVEBTN, "Save changes button");	
				reporter.SuccessReport("Requestor to cancel request before approval in Create/Edit Requestor Group",
						"Req Selection List Exists and Selected","");				         	  
           	  if(isElementPresent(REQUESTOR_AVAILABLE(ReqName), "Requestor Group")) {
			click(REQUESTOR_AVAILABLE(ReqName), "Requestor Group");	
			click(RIGHT_ARROW, "Right Arrow button");	
			if (isElementPresent(REQUESTOR_SELECTED(ReqName), "Requestor Group")) {
				reporter.SuccessReport("Requestor to cancel request before approval in Create/Edit Requestor Group",
						"Req Selection List Exists and Selected","");
				click(REQUESTOR_SAVEBTN, "Save changes button");				
			}
           	  }
 }else
		reporter.failureReport("Requestor to cancel request before approval in Create/Edit Requestor Group",
				"Req Selection List Does Not Exist","");
 }
 
 /**
	 * 
	 * @throws Throwable
	 */
	public void verifyandclickManagerRequestors(String tabName) throws Throwable {
		if (isElementPresent(createtabNames(tabName), tabName+"Tab")) {
			click(createtabNames(tabName), tabName+"Tab");
		}
		}
	
	 /**
		 * 
		 * @throws Throwable
		 */
		public void verifyandClickCancelRequisitionBtn() throws Throwable {
			if (isElementPresent(CANCEL_REQSTNBTN, "Cancel Requisition Button")) {
				click(CANCEL_REQSTNBTN, "Cancel Requisition Button");
			}
			}
		
		public void verifyCancelRequisitionStatusMesssage()  throws Throwable {
			if (isElementPresent(CANCELTN_STATUSMESSAGE, "Cancel Requisition Updated status")) {
					reporter.SuccessReport("Verify Requisition Status Update Successful Message",
							"Message Exists","");
			}else
						reporter.failureReport("Verify Requisition Status Update Successful Message",
								"Message Does Not Exist","");
			}
		
		public void clickExpandSearchIcon() throws Throwable {
			if (isElementPresent(EXPANDSEARCH,"Expand Icon" )) {
				click(EXPANDSEARCH,"Expand Icon" );
			}
			}
		
		public void verifyorderNuminReqHistoryPage(String orderLink) throws Throwable {
			if (isElementPresent(OrderObj.APPROVAL_MNGMNT_HDR1, "ApprovalManagement Header")) {
			if(isElementNotPresent(OrderObj.ReferenceLink(orderLink), "Approved Reference Number Link")){
				reporter.SuccessReport("Verify  Ref Number on Requisition List",
						"Ref Number Link Exists","");
			} else {
				reporter.failureReport("Verify  Ref Number on Requisition List",
						"Ref Number Link Not Exists","");
			}
			}
		}
		public void verifyCancelRequisition() throws Throwable {
			if (isElementPresent(CANCEL_REQSTNBTN, "Cancel Requisition Button")) {
				reporter.SuccessReport("CANCEL REQUISITION button in your order on Approval Managment",
						"Button Exist","");
		}else
					reporter.failureReport("CANCEL REQUISITION button in your order on Approval Managment",
							"Button Does Not Exist","");
			}

   }


