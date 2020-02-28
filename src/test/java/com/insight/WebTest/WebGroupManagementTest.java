package com.insight.WebTest;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CommonLib;
import com.insight.ObjRepo.CommonObj;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class WebGroupManagementTest extends CMTLib {

	CommonLib commonLib = new CommonLib();

	// #############################################################################################################
	// # Name of the Test : WGP01_AccountTeam
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : September 2019
	// # Description : To Test Place Order basic
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void WGP01_AccountTeam(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		Hashtable<String, String> data = TestUtil.getDataByRowNo("WGP01_AccountTeam", TestData, "Web_Group_Management",
				intStartRow);

		// Test Steps execution
		fnOpenTest();

		// Login to CMT and disable Allow File Upload during Checkout,Override
		loginToCMT(data.get("Header"));
		searchForWebGroup(data.get("WebGrp"));
		clickOnTheWebGroup(data.get("WebGrp_Name"));

		// Click on Contacts and Notifications
		hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));

		// Click on Display Icon
		List<String> RepNamesInDisplayOnWeb = verifyDisplayWebIcon();

		// Click on Product Exp
		verifyProductExpIconFeature();

		// Call the Function SelectUser
		hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));

		// Call the Function Login As
		loginAsAdminCMT();
		commonLib.spinnerImage();

		// Verify the Same User Logged into Insight
		commonLib.clickOnAccountToolsAndClickOnProductGrps(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));

		// Compare Sequence entered in CMT and after Login As
		VerifyRepNamesDisplayOrder(RepNamesInDisplayOnWeb);

		// Logout
		commonLib.clickLogOutLink(data.get("Logout"));
		fnCloseTest();

	}

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void WGP02_CompanyStandardCategories(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		Hashtable<String, String> data = TestUtil.getDataByRowNo("WGP02_CompanyStandardCategories", TestData,
				"Web_Group_Management", intStartRow);

		// Test Steps execution
		fnOpenTest();

		// Login to CMT and disable Allow File Upload during Checkout,Override
		loginToCMT(data.get("Header"));
		searchForWebGroup(data.get("WebGrp"));
		clickOnTheWebGroup(data.get("WebGrp_Name"));

		// select Company Standards
		hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));

		// Click on CompStandardWizard
		selectCompanyStandardsLink();

		// Enter Product Group Name on Product Group Creation Wizard
		enterProductGrpName(data.get("Product_Group_Name"));

		// Enter Create New
		enterCreateNewValue(data.get("Create_New"));

		// Click Continue
		clickContinueBtn();

		// Enter Configuration Set Name and Continue
		enterConfigurationSetName(data.get("Configuration_Set_Name"));

		// Search by Key Word
		searchByKeword(data.get("Search_Keyword"));

		// Verify Product
		verifyProduct();

		// Verify Insight home page is Launched
		AddToSet();

		// Verify Update
		VerifyUpdate();

		// Click on Category Link to Edit
		ClickCategoryLink();

		// Modify the Name
		ModifyCategory();

		// Check the Check Box to Collapse option
		CheckCollapseOption();

		// Click Update
		ClickUpdate();

		// Click on Product Group to Modify
		// ModifyProdGroup(data.get("Product_Group_Name"),
		// data.get("Create_New"));
		ModifyProdGroup(data.get("Product_Group_Name"), "TestCategory");

		// Verify Update
		VerifyUpdateMsg();

		// select user
		// Call the Function SelectUser
		hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));

		// Call the Function Login As
		loginAsAdminCMT();
		commonLib.spinnerImage();

		// Verify the Same User Logged into Insight
		commonLib.clickOnAccountToolsAndClickOnProductGrps(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));

		// Verify ICompany Standards
		VerifyICompanyStandards();

		// Logout and Close Insight Browser
		commonLib.clickLogOutLink(data.get("Logout"));
		fnCloseTest();
	}

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void WGP04_NewRep_Action1_Script(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		Hashtable<String, String> data = TestUtil.getDataByRowNo("WGP04_NewRep_Action1_Script", TestData,
				"Web_Group_Management", intStartRow);

		// Test Steps execution
		fnOpenTest();

		// Login to CMT and disable Allow File Upload during Checkout,Override
		loginToCMT(data.get("Header"));
		searchForWebGroup(data.get("WebGrp"));
		clickOnTheWebGroup(data.get("WebGrp_Name"));

		// Click on Contacts and Notifications
		hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));

		// Click on Add New Rep
		addNewRepDetails(data.get("Rep_Email"), data.get("Rep_PhoneNumber"), data.get("Rep_FaxNumber"));

		// New Rep successfully added message
		verifySuccessRepMsg();

		// Verify the Rep has been added and check product Exp and Display Web
		// permissions
		verifyNewRepAdded();

		// check permissions
		checkPermissions();

		// save the Rep details
		saveRepDetails();

		// Verify successfully saved message
		verifySuccessSaveMsg();

		// Click on Contacts and Notifications
		hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
		commonLib.spinnerImage();

		// Verify and Delete the newly added Rep
		deleteNewRepAdded();

		// Verify Rep deleted successfully message
		verifySuccessDeleteMsg();

		// Logout and Close Insight Browser
		// commonLib.clickLogOutLink(data.get("Logout"));
		logoutSite();
		fnCloseTest();
	}
}
