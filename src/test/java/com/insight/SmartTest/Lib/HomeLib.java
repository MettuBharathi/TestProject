
package com.insight.SmartTest.Lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.FileReader;
import java.io.BufferedReader;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.insight.SmartTest.Pages.HomePage;

public class HomeLib extends HomePage {

	loginLib loginlib = new loginLib();

	/*
	 * Click on Create quoteLink
	 */
	public void clickOnCreateQuoteLink() throws Throwable {
		waitForVisibilityOfElement(By.xpath("//div[@class='container']//h2[contains(text(),'Welcome')]"),
				"Welcome");
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (isVisibleOnly(HomePage.lnkCreateQuote, "CreateQuote")) {

			click(HomePage.lnkCreateQuote, "Create Quote");
			waitForVisibilityOfElement(HomePage.txtSoldTo, "Sold to Value");


			// JSClick(HomePage.lnkCreateQuote, "Create Quote");


		}
	}

	/*
	 * Enter Value in sold to text field
	 */
	public String enterSoldToValue(String SoldToValue) throws Throwable {
		if (isVisibleOnly(HomePage.txtSoldTo, "Sold to Value")) {
			type(HomePage.txtSoldTo, SoldToValue, "Sold To Value");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		return SoldToValue;
	}

	/*
	 * Clicking on Sold TO search iCon
	 */

	public void clickOnSoldToSearchIcon() throws Throwable {
		driver.findElement(HomePage.btnSearchIconForSoldto).click();

		loadingSymbol();
	}

	/*
	 * Click on product search button
	 */
	public void clickOnProductSearchButton() throws Throwable {
		isElementClickable(btnProductSearch, 3, "Product Search");
	}

	/*
	 * Selecting quote programe dropdown value
	 */
	public void selectQuotePrograme() throws Throwable {
		click(HomePage.drpDwnQuoateProgram, "Quote Program");
		click(HomePage.drpDwnQuoateProgramValue, "ADOBE CLPC 5.0 value selected");
	}
	
	public String getQuoteProgrameValue() throws Throwable {
		String value = getSelectedDropdownOption(drpDwnQuoateProgram);
		System.out.println("value: "+value);
		return value;
		
	}
	public void selectBlankQuotePrgram() throws Throwable {
		click(HomePage.drpDwnQuoateProgram, "Quote Program");
		click(HomePage.drpDwnBlankOption, "Cleare Quote program option");
	}

	public void clickOnLICandMaintCheckBox() throws Throwable {
		if (isVisibleOnly(HomePage.chkBoxLICAndMaint, "LIC and Maint")) {
			click(HomePage.chkBoxLICAndMaint, "LIC and Maint");
		}
	}

	public boolean availabilityOfOptionsandLevelsPopup() throws Throwable {
		return isVisibleOnly(HomePage.btnSaveButtonInOptionsAndLevels, "Options and  Levels popup ");
	}

	public void clickOnSaveButtonOnOptionsAndLevelsPopup() throws Throwable {
		click(HomePage.btnSaveButtonInOptionsAndLevels, "Options and  Levels popup ");
	}
	
	public void clickOnOptionsAndLevelsButton() throws Throwable {
		click(btnOptionsandLevels, "Options and  Levels Link");
	}

	public void clickOnSearchButtonInSearchWindow() throws Throwable {
		click(SEARCHBUTTONKEYWORDSEARCH, "Search Button Keyword Search");
	}

	public void enterSearchValue(String search) throws Throwable {
		type(txtKeywordSearch, search, "KeyWord Search Text::" + search + "");

	}

	public void clickOnMaterialID() throws Throwable {
		// lblMaterialID
		List<WebElement> materialList = driver.findElements(HomePage.lblMaterialID);
		if (materialList.size() > 0) {
			Actions action = new Actions(driver);
			for (int j = 0; j < materialList.size(); j++) {
				materialList.get(j);

				action.doubleClick(materialList.get(j)).perform();

				break;
			}
			reporter.SuccessReport("Serach Result for Street::", "Search Results Displayed", "");
		} else {
			reporter.failureReport("Resutls", "Search results are not found", "", driver);
		}
	}

	public boolean verifyAddToOrderPopup() throws Throwable {
		return isVisibleOnly(HomePage.lblAddToOrder, "Add To Order Popup");
	}

	public void clickOnAddToOrderButton() throws Throwable {
		if (verifyAddToOrderPopup()) {
			isElementClickable(HomePage.lblAddToOrder, 3, "Add To Order");
		}
	}

	/**
	 * Method is to click on Account search button
	 * 
	 * @throws Throwable
	 */
	public void accountsearch() throws Throwable {
		click(ACCOUNTSEARCH, "Account search");
	}

	/**
	 * Method is to click on CheckBox
	 * 
	 * @throws Throwable
	 */
	public void clickOnCheckoxinAccountSearch(String Type) throws Throwable {
		click(checkboxes(Type), "Check Box::" + Type + "");
	}

	/**
	 * Method is to click on search button in Account Search Page
	 * 
	 * @throws Throwable
	 */
	public void searchinAccountSearchPage() throws Throwable {
		click(SEARCHBUTTON, "Account search");
	}

	/**
	 * Method is to Type TextinTextFields in Account Search Page
	 * 
	 * @throws Throwable
	 */
	public void textfieldsinAccountSearchpage(String Textfield, String Text) throws Throwable {
		typeText(textfieldsinaccountsearch(Textfield), Text, "" + Textfield + "::" + Text + "");
	}

	/**
	 * Method is to Clear TextinTextFields in Account Search Page
	 * 
	 * @throws Throwable
	 */
	public void clearTextfieldsinAccountSearchpage(String Textfield) throws Throwable {
		clearData(textfieldsinaccountsearch(Textfield));
	}

	/**
	 * Method is to verify search results of Lname in Account Search Page
	 * 
	 * @throws Throwable
	 */
	public void verifyResultsofLastname(String name) throws Throwable {
		waitForVisibilityOfElement(nameResults(name), "Search Result for Lname");
		if (isElementPresent(nameResults(name), "Search Result")) {
			reporter.SuccessReport("Serach Result for Lname::", "Search Results Displayed", "");
		} else {
			reporter.failureReport("Serach Result for Lname::", "Search Results are not Displayed", "");
		}
	}

	/**
	 * Method is to verify search results of Street in Account Search Page
	 * 
	 * @throws Throwable
	 */
	public void verifyResultsofStreet(String Street) throws Throwable {
		waitForVisibilityOfElement(streetResults(Street), "Search Result for Street");
		if (isElementPresent(streetResults(Street), "Search Result Street")) {
			reporter.SuccessReport("Serach Result for Street::", "Search Results Displayed", "");
		} else {
			reporter.failureReport("Serach Result for Street::", "Search Results are not Displayed", "");
		}
	}

	/**
	 * Method is to verify results of Firstname in Account Search Page
	 * 
	 * @throws Throwable
	 */
	public void verifyResultsofFirstname(String name) throws Throwable {
		waitForVisibilityOfElement(fnameResults(name), "Search Result for Fname");
		if (isElementPresent(fnameResults(name), "Search Result")) {
			reporter.SuccessReport("Serach Result for Fname::", "Search Results Displayed", "");
		} else {
			reporter.failureReport("Serach Result for Fname::", "Search Results are not Displayed", "");
		}
	}

	/**
	 * Method is to verify results of Email in Account Search Page
	 * 
	 * @throws Throwable
	 */
	public void verifyResultsofEmail(String Email) throws Throwable {
		waitForVisibilityOfElement(eamilResults(Email), "Search Result for Email");
		if (isElementPresent(eamilResults(Email), "Search Result")) {
			reporter.SuccessReport("Serach Result for Email::", "Search Results Displayed", "");
		} else {
			reporter.failureReport("Serach Result for Email::", "Search Results are not Displayed", "");
		}
	}

	/**
	 * Method is to Search With Seals Doccument Number
	 * 
	 * @throws Throwable
	 */
	public void searchSalesDocNum(String Sales_Docnum) throws Throwable {
		waitForVisibilityOfElement(SALESDOCMENTSEARCHFIELD, "Sales Doccument Search");
		clearData(SALESDOCMENTSEARCHFIELD);
		typeText(SALESDOCMENTSEARCHFIELD, Sales_Docnum, "Sales Doccument number");
		click(SEARCHBUTTON_SALESDOCNUM, "Search Button");
	}

	/**
	 * Method is to verify results of Material in Line items
	 * 
	 * @throws Throwable
	 */
	public void verifyResultsofmaterail() throws Throwable {
		waitForVisibilityOfElement(MATERIAL, "Materilas in Line items");
		if (isElementPresent(MATERIAL, "Search Result")) {
			List<WebElement> myList = driver.findElements(MATERIAL);
			List<String> all_elements_text = new ArrayList<>();
			for (int i = 0; i < myList.size(); i++) {
				all_elements_text.add(myList.get(i).getText());
				LOG.info(all_elements_text);
			}
			reporter.SuccessReport("Materails::", "Materails Results Displayed", "");
		} else {
			reporter.failureReport("Materails::", "Materails Results are not Displayed", "");
		}

	}

	/**
	 * Method is to Click on Done Button In Item deatils Pop_up
	 * 
	 * @throws Throwable
	 */
	public void clickDoneButton() throws Throwable {
		isElementClickable(doneButtonQuotepage, 3, "Done Button");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	/**
	 * Method is to Click on Change Mode Icon
	 * 
	 * @throws Throwable
	 */
	public void clickChangeModeIcon() throws Throwable {
		click(CHANGEMODE_ICON, "Change Mode");
	}

	/**
	 * Method is to Click on X symbol of Con
	 * 
	 * @throws Throwable
	 */
	public void clickonConXSystem(String itemNum) throws Throwable {
		if (isElementPresent(CON, "Con")) {// 000010
			click(xConSymbol(itemNum), "X Symbel of item");
			reporter.SuccessReport("X Symbol of item::", "Con Symbol of LineItem " + itemNum + "", "");
		} else {
			reporter.failureReport("X Symbol of item::", "X Symbol of item are not Displayed", "");
		}

	}

	/**
	 * Method is to Verify diversity Partner
	 * 
	 * @throws Throwable
	 */
	public void VerifyDiversityPartner() throws Throwable {
		waitForVisibilityOfElement(ITEMDETAILSLABEL, "Item Details");
		if (isElementPresent(DIVERSITYPARTNER, "Diversity Partner")) {
			reporter.SuccessReport("Diversity Partner::", "Diversity Partner is Displayed", "");
		} else {
			reporter.failureReport("Diversity Partner::", "Diversity Partner is not Displayed", "");
		}
	}

	/**
	 * Method is to Verify Contract
	 * 
	 * @throws Throwable
	 */
	public void VerifyCopyContract() throws Throwable {
		if (isElementPresent(COPYCONTRACT, "Copy Contract")) {
			reporter.SuccessReport("Copy Contract::", "Copy Contract is Displayed", "");
		} else {
			reporter.failureReport("Copy Contract::", "Copy Contract is not Displayed", "");
		}
	}

	/**
	 * Method is to Verify diversity Partner
	 * 
	 * @throws Throwable
	 */
	public void VerifyCopyReportingfield() throws Throwable {
		if (isElementPresent(COPYTOLLREPORTINGFIELDS, "Reporting Fields")) {
			reporter.SuccessReport("Reporting Fields::", "Copy To ALL Reporting Fields is Displayed", "");
		} else {
			reporter.failureReport("Reporting Fields::", "Copy To ALL Reporting Fields is not Displayed", "");
		}
	}

	/**
	 * Method is to Click on Advanced header
	 * 
	 * @throws Throwable
	 */

	public void clickAdvancedHeader() throws Throwable {
		isElementPresent(ADVANCEDHEADER,"Advanced Header");
		Thread.sleep(3000);
		click(ADVANCEDHEADER,"Advanced Header");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

	/**
	 * Method is to Click on Summary Tabs
	 * 
	 * @throws Throwable
	 */
	public void clickOnSummaryTabs(String Tab) throws Throwable {
		click(summaryTabs(Tab), "Summary Tab::" + Tab + "");
	}

	/**
	 * Method is to Verify Summary Data in Table
	 * 
	 * @throws Throwable
	 */
	public void VerifySummaryDatainTable(String type, String Data) throws Throwable {

		if (isElementPresent(summaryTabsSummaryData(type, Data), "Summary Data")) {
			reporter.SuccessReport("Summary Data::" + type + "", "Summary Data is Displayed::" + Data + "", "");
		} else {
			reporter.failureReport("Summary Data::", "Summary Data is not Displayed", "");
		}
	}

	/**
	 * Method is to Verify Summary Data in Table
	 * 
	 * @throws Throwable
	 */
	public void VerifySummaryDataisPresent(String type) throws Throwable {

		if (isElementPresent(summaryTabsSummaryDataValue(type), "Summary Data")) {
			String Value = getText(summaryTabsSummaryDataValue(type), "" + type + "::value");
			int value = Integer.parseInt(Value);
			if (value != 0) {
				reporter.SuccessReport("Summary Data of " + type + "::",
						"Value is Not Zero As Expected and Displayed as :: " + Value + "", "");
			}
		} else {
			reporter.failureReport("Summary Data::", "Summary Data is not Displayed", "", driver);
		}
	}

	/**
	 * Method is to Verify Summary Data in Table
	 * 
	 * @throws Throwable
	 */
	public void VerifySummaryDataisPresentequaltoZero(String type) throws Throwable {

		if (isElementPresent(summaryTabsSummaryDataValue(type), "Summary Data")) {
			String Value = getText(summaryTabsSummaryDataValue(type), "" + type + "::value");
			int value = Integer.parseInt(Value);
			if (value == 0) {
				reporter.SuccessReport("Summary Data of " + type + "::",
						"Value is Not Zero As Expected and Displayed as :: " + Value + "", "");
			}
		} else {
			reporter.failureReport("Summary Data::", "Summary Data is not Displayed", "", driver);
		}
	}

	/**
	 * Method is to Click on Advanced header
	 * 
	 * @throws Throwable
	 */
	public void clickAdvancedHeaderTab(String Tab) throws Throwable {
		click(tabinAdvancedHeader(Tab), "Advanced Header::" + Tab + "");
	}

	public void clickLineItemHeaderTab(String Tab) throws Throwable {
		click(tabinLineItemHeader(Tab), "Line Item::" + Tab + "");
	}

	/**
	 * Method is to verify Selected option in Shippingconditions
	 * 
	 * @throws Throwable
	 */

	public void verifySelectedoptioninShippingconditions(String Option) throws Throwable {

		Select select = new Select(driver.findElement(SHIPPINGCONDITIONSDRPDOWN_ANDVANCEDHEADER));
		WebElement option = select.getFirstSelectedOption();
		String text = option.getText().trim();
		if (text.equals(Option)) {
			reporter.SuccessReport("Shipping Option::", "" + option + " is Selected As Expected", "");
		} else {
			reporter.failureReport("Shipping Option::", "Shipping Option is not Displayed", "");
		}
	}

	/**
	 * 
	 * @param Tab
	 * @throws Throwable
	 */
	public void selectManufacturerName(String data, String CheckboxName, String pricingstatus) throws Throwable {
		click(SELECT_MANUFACTURER_DD, "Manufacturer dropdow");
		driver.findElement(SELECT_MANUFACTURER_DATA).sendKeys(data);
		click(checkboxinProductSearch(CheckboxName), CheckboxName + "Checkbox Selected");
		selectByVisibleText(SELECT_PRICING, pricingstatus, "Pricing selected to" + pricingstatus);

	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void VerifyProductSearchPopupHeader() throws Throwable {
		waitForInVisibilityOfElement(PRODUCTSEARCH_HDR, "Product search header");
	}

	// Clicking on Sold TO search iCon

	public void clickOnSalesDocSearch() throws Throwable {
		// Thread.sleep(2000);
		if (waitForVisibilityOfElement(SALESDOCMENTSEARCHFIELD, "Sales Doc Search Icon")) {
			click(HomePage.SEARCHBUTTON_SALESDOCNUM, "Sales Doc Search Icon");
			reporter.SuccessReport("Sales Doc Search Icon::", "", "displaying");
		} else {
			reporter.failureReport("Sales Doc Search Icon::", "Sales Doc Search Icon is not Displayed", "");
		}

	}
	// Select Invoice History from History dropdown

	public void SelectoptionfromDropdown(String option, String DropdownName) throws Throwable {
		waitForVisibilityOfElement(HistoryDropdown(DropdownName), "Dropdown name");
		click(HomePage.HistoryDropdown(DropdownName), "Dropdown name");
		Thread.sleep(2000);
		click(HomePage.Historyoptions(option), "dropdownvalue");
		loadingSymbol();
	}

	// Click on invoice link
	public void ClickOnInvoiceLink() throws Throwable {
		click(HomePage.InvoiceLink, "Sales Doc Search Icon");
		loadingSymbol();
	}

	public void CheckInvoiceIsopenedorNot() throws Throwable {

		@SuppressWarnings("unchecked")
		List<String> allWindowHandles = (List<String>) driver.getWindowHandles();
		if (allWindowHandles.size() > 1) {
			System.out.println("Pdf invoice is opened in new browser");
		} else {
			System.out.println("Pdf invoice is not opened in new browser");
		}
		driver.close();
	}

	// Add line items

	public void AddLineItems(String columnName, String Material, int rowNumber) throws Throwable {
		click(AddMaterialLineItem1(columnName, rowNumber), "Material");
		try {
			driver.findElement(AddMeterialInLineItems).sendKeys(Material);
		} catch (Exception e) {
			driver.findElement(AddMeterialInLineItems).sendKeys(Material);
		}

		click(HomePage.DesciptionInLineItems, "DesciptionInLineItems");
		loadingSymbol();
	}

	public void doubleclickonCostcell(String columnName, int rowNumber) {
		Actions action = new Actions(driver);
		WebElement ele = driver.findElement(AddMaterialLineItem1(columnName, rowNumber));
		action.doubleClick(ele).perform();
	}

	public String getMaterialfromLineItems(String columnName, int rowNumber) throws Throwable {
		String material = getText(AddMaterialLineItem1(columnName, rowNumber), "Material");
		return material;

	}

	public void AddfrtChargeInLneitems(String freightcahrge, int rowNumber) throws Throwable {
		Actions ac = new Actions(driver);
		WebElement elme = driver.findElement(AddfrtCharge(rowNumber));
		ac.moveToElement(elme).perform();
		try {
			String frtcharge = getText(AddfrtCharge(rowNumber), "freightcahrge");
			if (frtcharge.equals("0.00"))
				System.out.println("Fdrieght chargers are : " + frtcharge);
			else {
				click(AddfrtCharge(rowNumber), "freightcahrge");
				driver.findElement(txtfrtchrage).sendKeys(freightcahrge);
			}
		} catch (Exception e) {
			driver.findElement(txtfrtchrage).sendKeys(freightcahrge);
		}
		WebElement elme1 = driver.findElement(DesciptionInLineItems);
		ac.moveToElement(elme1).perform();
		click(DesciptionInLineItems, "DesciptionInLineItems");
		loadingSymbol();
	}

	public void EnterSalesDocNumber(String SalesDocNumber) throws Throwable {
		if (isVisibleOnly(HomePage.txtSalesDocNumber, "SalesDocNumber")) {
			type(HomePage.txtSalesDocNumber, SalesDocNumber, "SalesDocNumber");
		}
	}

	// Click on side view bar
	public void ClickOnsideViewBar() throws Throwable {
		// waitForInVisibilityOfElement(HomePage.HistoryDropdown("history"), "History
		// option");
		waitForVisibilityOfElement(Sideviewbar, "SideViewbar");
		click(HomePage.Sideviewbar, "SideViewbar");
	}

	public void sortingofcolumns(String ColumnName) throws Throwable {
		if (waitForVisibilityOfElement(columnNamesUderFrieghtpopup(ColumnName), "Frieght popup")) {
			System.out.println("The Freight Shopping popup displays as expected.");
			if (isElementClickable(columnNamesUderFrieghtpopup(ColumnName), 2, "Frieght popup")) {

				click(columnNamesUderFrieghtpopup(ColumnName), "Frieght popup");
				System.out
						.println("Successfully sorted the 'Cost' column of the 'Freight Shopping' popup as expected.");
			} else {
				System.out.println("Unable to sort the 'Cost' column in the 'Freight Shopping' popup.");
			}
		} else {
			System.out.println("The Freight Shopping popup was not displays as expected.");
		}
	}
	// Add line items

	public void AddLineItems(String Material) throws Throwable {
		click(HomePage.AddMeterialInLineItems, "Material");
		driver.findElement(HomePage.AddMeterialInLineItems).sendKeys(Material);
		// can't use type method as the material field is not user editable
		// field
		// typeText(HomePage.AddMeterialInLineItems, Material, "Material");
		click(HomePage.DesciptionInLineItems, "DesciptionInLineItems");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	/**
	 * Method is to Click on Yes Button to close document
	 * 
	 * @throws Throwable
	 */
	public void clickYesButtontocloseDocument() throws Throwable {
		click(YESBUTTONINWARNINGMSG, "Yes Button In Warning PopUp");
	}

	/**
	 * Method is to Click on UpdateCosting
	 * 
	 * @throws Throwable
	 */
	public void clickUpdateCosting() throws Throwable {
		isElementClickable(UPDATECOSTING, 3, "Update Costing");
		loadingSymbol();
	}

	/**
	 * Method is to Click on Side Bar of Smart Welcome page
	 * 
	 * @throws Throwable
	 */
	public void clickSideBarSmart() throws Throwable {
		isElementClickable(SIDEBAR, 3, "Side Bar of Smart");
	}

	/**
	 * Method is to Click on CreateQuoteButton On top of the Smart APP
	 * 
	 * @throws Throwable
	 */
	public void clickCreateQuoteButton() throws Throwable {
		clickUntil(CREATEQUOTE_BUTTON, ADVANCEDHEADER, "Create Quote Button");
	}

	/**
	 * Method is to enter SoldTo in Create Quote Page
	 * 
	 * @throws Throwable
	 */
	public void enterSoldTo(String Soldto) throws Throwable {
		typeText(SOLDTO_CREATEQUOTE, Soldto, "Sod-To");
		// Thread.sleep(4000);

		click(SEARCH_SOLDTO, "Sold-To Search Button");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

	/**
	 * Method is to Select_Org
	 * 
	 * @throws Throwable
	 */
	public void selectOrg(String Option) throws Throwable {
		selectByVisibleText(SALESORGDROPDOWN, Option, "Sales_Org::" + Option + "");

	}

	/**
	 * Method is to Add Line Item
	 * 
	 * @throws Throwable
	 */
	public void Addmaterail(String Material) throws Throwable {
		click(AddMeterialInLineItems, "Material Line item");
		driver.findElement(AddMeterialInLineItems).sendKeys(Material);
		driver.findElement(AddMeterialInLineItems).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

	/**
	 * Method is to Verify Contract
	 * 
	 * @return
	 * 
	 * @throws Throwable
	 */
	public String verifyContarct(String SW_Contract) throws Throwable {
		String Contract = getText(contractforId(SW_Contract), "Contarct Description");
		if (isElementPresent(contractforId(SW_Contract), "Contarct Id")) {
			click(contractforId(SW_Contract), "Required Contract");
			reporter.SuccessReport("Contract::", "For the Contract Id" + SW_Contract + " Contact is" + Contract + "",
					"");
		} else {
			reporter.failureReport("Contract::", "Contract is not Displayed", "");
		}
		return Contract;
	}

	/**
	 * Method is to Verify Contract With Reporting Fields
	 * 
	 * @throws Throwable
	 */
	public void verifycontractwithreporingfield(String Contract) throws Throwable {
		String reportingfield = getText(FIRSTREPORTINGFIELDLABEL, "Reporitng Filed");
		if (Contract.contains(reportingfield)) {
			reporter.SuccessReport("Reporting Field::",
					"Reporting Field::" + reportingfield + "is Matching With Contract Selected", "");
		} else {
			reporter.failureReport("Reporting Field::", "Reporting Field is not Matching With Contract Selected", "");
		}
	}

	/**
	 * Method is to Click on Save As Order
	 * 
	 * @throws Throwable
	 */
	public void clickonSaveasOrder() throws Throwable {
		click(SAVEAVSORDERBUTTON, "Save As Order");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		loadingSymbol();
	}

	/**
	 * Method is to click Save order without Attachment
	 * 
	 * @throws Throwable
	 */
	public void clickSaveorderwithoutAttachment() throws Throwable {
		waitForVisibilityOfElement(NOPOATTACHEDPOPUP, "No PO Attached POPUP");
		click(SAVERORDERWITHOUTATTACHMENTBUTTON, "Save Order without Attachment Button");
	}

	/**
	 * Method is to Verify Contract With Reporting Fields
	 * 
	 * @throws Throwable
	 */
	public void verifyErrorMsg(String ErrorMsg) throws Throwable {
		waitForVisibilityOfElement(ERRORMSGTAG, "Error Msg For Reporting Fields Tag");
		String errorMsg = getText(errormsgReportingfields, "Error MsgFor Reporting Fields");
		if (errorMsg.contains(ErrorMsg)) {
			reporter.SuccessReport("Error Msg::", "" + ErrorMsg + " ::is Visible", "");
		} else {
			reporter.failureReport("Error Msg::", "Error Msg is not Visible", "");
		}
	}

	/**
	 * Method is to Click Close the document
	 * 
	 * @throws Throwable
	 */
	public void clickClosthedocument(String Doctype) throws Throwable {
		if (isElementPresent(closeButtonofSidebar(Doctype), "Close the Doccumnet")) {
			click(closeButtonofSidebar(Doctype), "Close the Doccument");
		} else {
			clickSideBarSmart();
			click(closeButtonofSidebar(Doctype), "Close the Doccument");
		}
	}

	/**
	 * Method is to Click Close the document
	 * 
	 * @throws Throwable
	 */
	public void clickMaterail() throws Throwable {
		click(MATERIAL, "Materail");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

	/**
	 * Method is to Click on Side Bar of Smart Welcome page
	 * 
	 * @throws Throwable
	 */
	public void clickOKinErrormsgBox() throws Throwable {
		click(OKBUTTON, "Ok Button in PopUp");
	}

	// ORD01_ZFRTHold
	/**
	 * Method is to Select_payment Terms in ACCount Info
	 * 
	 * @throws Throwable
	 */
	public void selectPaymentTerms(String Option) throws Throwable {
		selectByVisibleText(PAYMENT_TERMS, Option, "Payment Terms::" + Option + "");
	}

	/**
	 * Method is to Type PO Number
	 * 
	 * @throws Throwable
	 */
	public void typePONumber(String POnumber) throws Throwable {
		click(PONUMBER_ACCOUNTINFO, "PO Number Text Field");
		type(PONUMBER_ACCOUNTINFO, POnumber, "PO Number");
	}

	/**
	 * Method is to Click on Search button Po Number
	 * 
	 * @throws Throwable
	 */
	public void SearchButtonPONumber() throws Throwable {
		click(PONUMBERSEARCHBUTTON_ACCOUNTINFO, "PO Number Search Button");
	}

	/**
	 * Method is to Select_payment Terms in ACCount Info
	 * 
	 * @throws Throwable
	 */
	public void selectCarrier(String Option) throws Throwable {
		selectByVisibleText(CARRIER_ADVANCEDHEADER, Option, "Carrier::" + Option + " is Selected");
	}

	/**
	 * Method is to Shipping Options in Advanced Header
	 * 
	 * @throws Throwable
	 */
	public void selectShippingOptions(String Option) throws Throwable {
		selectByVisibleText(SHIPPINGCONDITIONSDRPDOWN_ANDVANCEDHEADER, Option,
				"Shipping Option::" + Option + " is Selected");
	}

	/**
	 * Method is to Verify Hold In Advanced header
	 * 
	 * @throws Throwable
	 */
	public void verifyHoldsText(String holdText) throws Throwable {
		waitForVisibilityOfElement(HOLDS_TEXTAREA, "Hold");
		String HoldText = getText(HOLDS_TEXTAREA, "Hold Text For Order");
		if (HoldText.contains(holdText)) {
			reporter.SuccessReport("Hold Text For Order::", "" + HoldText + " ::is Visible", "");
		} else {
			reporter.failureReport("Hold Text For Order::", "Is not Visible", "");
		}
	}

	/**
	 * Method is to Click on ChangeMode Button
	 * 
	 * @throws Throwable
	 */
	public void ClickOnDisplayMode() throws Throwable {
		if (isElementVisible(DISPLAYMODE_BUTTON, 2, "Display Mode button")) {
			click(HomePage.DISPLAYMODE_BUTTON, "Display Mode button");
			loadingSymbol();
			waitForVisibilityOfElement(txtChangeMode, "ChangeMode");
		} else {
			reporter.failureReport("ChangeMode::", "Is not Visible", "");
		}

	}

	/**
	 * Method is to Add Quantity in LineItems
	 * 
	 * @throws Throwable
	 */
	public void AddQuantity(String quantity, String item) throws Throwable {
		click(QuantityofanItem(item), "Quantity");
		driver.findElement(TYPEQUANTITYOFANITM).sendKeys(quantity);
		driver.findElement(TYPEQUANTITYOFANITM).sendKeys(Keys.ENTER);
	}

	/**
	 * Method is to Click on X symbol of Con
	 * 
	 * @throws Throwable
	 */
	public void VerifycolourAfterRejectingItem(String colour) throws Throwable {
		String Colour[] = driver.findElement(COLOUROFITEM).getAttribute("style").split(";");
		if (colour.contains(Colour[0])) {
			reporter.SuccessReport("Colour Of Item::",
					"Colour Of Item after Deleted/Rejected is Changed in to :: " + Colour[0] + "", "");
		} else {
			reporter.failureReport("Colour Of Item::", "Colour Of Item after Deleted/Rejected is Not Change", "");
		}
	}

	/**
	 * Method is to Reject or Delete The Item
	 * 
	 * @throws Throwable
	 */
	public void rejectitem(String LineItem) throws Throwable {
		rightClick(DescriptionofanItem(LineItem), "Description of LineItem::" + LineItem + "");
		click(DELETEITEM, "Delete/Reject Line Item");
	}

	/**
	 * Method is to Click on Ok Button in PONumber Popup
	 * 
	 * @throws Throwable
	 */
	public void clickOKinPopUp() throws Throwable {
		click(OKBUTTONINPOPUP, "Ok Button in PopUp");
	}

	/**
	 * Method is to Click on X symbol of Con
	 * 
	 * @throws Throwable
	 */
	public void okPopUp() throws Throwable {
		if (isElementPresent(OKBUTTONINPOPUP, "Ok Button in PopUp")) {
			click(OKBUTTONINPOPUP, "Ok Button in PopUp");
			reporter.SuccessReport("Ok Button in PopUp::", "Ok Button in PopUp Is Present And Handled", "");
		} else {
			// do Noting
		}
	}

	/**
	 * Method is to get Order Number
	 * 
	 * @throws Throwable
	 */
	public void getOrderNum() throws Throwable {
		if (isElementPresent(SUBJECTTOGETORDERNUM, "Order Number")) {
			String OrderNum[] = driver.findElement(SUBJECTTOGETORDERNUM).getAttribute("Value").split("-");
			reporter.SuccessReport("Order Number::", "Order Number Exists and Text:: " + OrderNum[0] + "", "");
		} else {
			reporter.failureReport("Order Number::", "Order Number is not Displayed", "");
		}
	}

	/**
	 * Method is to get Quote Number
	 * 
	 * @throws Throwable
	 */
	public void getQuoteNum() throws Throwable {
		if (isElementPresent(SUBJECTTOGETORDERNUM, "Quote Number")) {
			String QuoteNum[] = driver.findElement(SUBJECTTOGETORDERNUM).getAttribute("Value").split("-");
			reporter.SuccessReport("Quote Number::", "Quote Number Exists and Text:: " + QuoteNum[0] + "", "");
		} else {
			reporter.failureReport("Quote Number::", "Quote Number is not Displayed", "");
		}
	}

	/**
	 * Method is to Click on Cancel Button In Output Popup
	 * 
	 * @throws Throwable
	 */
	public void clickCancel() throws Throwable {
		click(CANCELBUTTONIN, "Cancel Button For Output POPUP");
	}

	/**
	 * Method is to Reject or Delete The Item
	 * 
	 * @throws Throwable
	 */
	public void clickYesToRejectItem() throws Throwable {
		click(SUBMITBUTTONTOREJECTITEM, "Yes Button to Reject item in PopUp");
	}

	/**
	 * Method is to Select reason to Reject/Delete the Lineitem
	 * 
	 * @throws Throwable
	 */
	public void selectReasonToRejectItem(String Option) throws Throwable {
		selectByVisibleText(REASONFORREJECTION, Option, "Reason to Reject Item::" + Option + " is Selected");
	}

	/**
	 * Method is to Select reason to Reject/Delete the Lineitem
	 * 
	 * @throws Throwable
	 */

	public void clickCloseButton() throws Throwable {
		click(CLOSEBTN_PRODUCTSEARCH, "Close Button");
	}

	/**
	 * Method is to Verify Total Weight in Summary Tab
	 * 
	 * @throws Throwable
	 */
	public void verifyTotalWeight(String weight) throws Throwable {
		isVisibleOnly(TOTAL_WEIGHT, "Total Weight");
		String totalWeight = getText(TOTAL_WEIGHT, "Total Weight displayed");
		if (weight.equals(totalWeight)) {
			reporter.SuccessReport("Verify Total Weight ",
					"Actual weight" + totalWeight + " and Expected Weight " + weight + " Both are same", weight);
		} else {
			reporter.failureReport("Verify Total Weight ",
					"Actual weight" + totalWeight + " and Expected Weight " + weight + " Both are not same", weight,
					driver);
		}

	}

	/**
	 * Method is to Click on Save As Quote
	 * 
	 * @throws Throwable
	 */
	public void clickonSaveasQuote() throws Throwable {

		isElementClickable(SAVEASQUOTE_BUTTON, 3, "Save As Quote Button");
		loadingSymbol();
	}

	public void enterNewEmailIDandclickOKBtn(String emailid) throws Throwable {
		if (isElementPresent(OUTPUT_POPUHDR, "Output Header Popup")) {
			clearData(EMAIL_TEXT);
			type(EMAIL_TEXT, emailid, "Email ID displayed" + emailid);
			click(SEND_BTN, "Send Button");
			if (waitForInVisibilityOfElement(INFO_POPUP, "Info header popup")) {
				click(INFO_POPUP_OKBTN, "Ok Button");
			}

		}
	}

	/**
	 * Method is to Click Ok Button
	 * 
	 * @throws Throwable
	 */

	public void clickonOkButton() throws Throwable {
		if (waitForInVisibilityOfElement(INFO_POPUP, "Info header popup")) {
			click(INFO_POPUP_OKBTN, "Ok Button");
		}
	}

	/**
	 * Method is to Click on select details in pricing tab under Advance Header
	 * 
	 * @throws Throwable
	 */
	public void enterPricingDetails(String DDoption, String Prcent) throws Throwable {
		selectByVisibleText(SELECT_GROUPING_DD, DDoption, "Select Grouping Dropdown::" + DDoption + " is Selected");
		type(NEW_REPMARGIN_PRCNT, Prcent, "New Rep Margin % :" + Prcent);
	}

	/**
	 * Method is to get Quote number
	 * 
	 * @throws Throwable
	 */

	public String getSaveQuoteNumber() throws Throwable {
		String QuoteNum="" ;
		if(waitForVisibilityOfElement(GET_QUOTENMBR, "Quot numbeer")) {
			QuoteNum = getAttributeByValue(GET_QUOTENMBR, "Quote Number");
		}
		return QuoteNum;
	}

	/**
	 * this Method is Replace Ship To AttenData
	 * 
	 * @return
	 * @throws Throwable
	 */
	public void replaceShiptoAttenData() throws Throwable {
		if (isElementPresent(SHIP_TO_ATTN, "Ship to Atten Text Box")) {
			clearData(SHIP_TO_ATTN);
			type(SHIP_TO_ATTN, "TEST", "Ship to Atten Text is replaced with TEST");
		}
	}

	/**
	 * this Method is to Click On Partner Id
	 * 
	 * @throws Throwable
	 */

	public void clickPartnerID() throws Throwable {
		List<WebElement> partnerlList = driver.findElements(HomePage.PARTNER_LIST);
		if (partnerlList.size() > 4) {
			for (int j = 0; j < partnerlList.size(); j++) {
				if (j == 4) {
					Actions action = new Actions(driver);
					action.contextClick(partnerlList.get(j)).build().perform();
					click(PARTNER_LIST, "Make Default Option");
				}

			}
		}

	}

	/**
	 * this Method is to enter Account Number
	 * 
	 * @throws Throwable
	 */

	public void enterAccountNumber(String AccountNum) throws Throwable {
		if (isElementPresent(ACCOUNT_NUMBERSEARCH, "Account Number search box", true)) {
			type(ACCOUNT_NUMBERSEARCH, AccountNum, "Account Number");
			click(ACCOUNT_SEARCHBTN, "Account NUmber search Button");
		}
	}

	public void clickonExpandedMenuName(String SubTab, String TabName) throws Throwable {
		if (isElementNotPresent(tabinAdvancedHeader(SubTab), TabName + "already expanded")) {
			click(ExpandTabs(TabName), TabName + "Expanded");
		} else {
			LOG.info(TabName + "already expanded");
		}

	}

	public void clickonSaveChangesButton() throws Throwable {
		click(SAVE_CHANGESBTN, "Save changes Button");
	}

	public String getTextfromShipToField() throws Throwable {
		String Shipto = getText(TXTSHIPTO, "Ship to text field");
		return Shipto;
	}

	public void clickOkButton() throws Throwable {
		if (waitForVisibilityOfElement(INFO_POPUP, "Info header popup")) {
			click(INFO_POPUP_OKBTN, "Ok Button");
		} else {
			click(INFO_POPUP_OKBTN, "Ok Button");
		}
	}

	// search 03
	/**
	 * Method is to Select Sales_Org
	 * 
	 * @throws Throwable
	 */
	public void selectsalesOrginPopUp(String Option) throws Throwable {
		waitForVisibilityOfElement(salesOrgPopUp(Option), "Sales Org");
		mouseDoubleClick(salesOrgPopUp(Option), "Sales_Org::" + Option + "");

	}

	/**
	 * Method is to verify search results of Materail Id for Keyword Search In
	 * Product Search
	 * 
	 * @throws Throwable
	 */
	public void verifyResultsofMaterailIdofKeyWordSearch(String MaterailId) throws Throwable {
		waitForVisibilityOfElement(materialIdforSearchResult(MaterailId),
				"Search Result of MaterailId for KeyWordsearch");
		if (isElementPresent(materialIdforSearchResult(MaterailId), "Search Result")) {
			mouseDoubleClick(materialIdforSearchResult(MaterailId), "Search Result of MaterailId for KeyWordsearch");
			reporter.SuccessReport("Serach Result for MaterailId::",
					"Search Results of " + MaterailId + " Displayed As Expected", "" + MaterailId + "");
		} else {
			reporter.failureReport("Serach Result for MaterailId::", "Search Results are not Displayed", "");
		}
	}

	/**
	 * Method is to click On Tab Of MaterailID In ProductSearch PopUp
	 * 
	 * @throws Throwable
	 */
	public void clickOnTabOfMaterailIDProductSearchPopUp(String tab) throws Throwable {
		waitForVisibilityOfElement(tabsinMaterailIdProductsearchPOpUp(tab), "" + tab + " in Product Search PopUp");
		if (isElementPresent(tabsinMaterailIdProductsearchPOpUp(tab), "" + tab + " in Product Search PopUp")) {
			click(tabsinMaterailIdProductsearchPOpUp(tab), "" + tab + " in Product Search PopUp");
			reporter.SuccessReport("" + tab + " in Product Search PopUp::",
					"" + tab + " in Product Search PopUp is Displayed As Expected", "" + tab + "");
		} else {
			reporter.failureReport("" + tab + " in Product Search PopUp::",
					"" + tab + " in Product Search PopUp are not Displayed", "");
		}
	}

	/**
	 * Method is to click On Tab Of MaterailID In ProductSearch PopUp
	 * 
	 * @throws Throwable
	 */
	public void verifyICScoloumninAccesoriesTab() throws Throwable {
		if (isElementPresent(ICSTABACCESSORIESTABINPRODUCTSEARCH, "ICS Column")) {
			reporter.SuccessReport("ICS Column::", "ICS Column is Displayed As Expected", "");
		} else {
			reporter.failureReport("ICS Column::", "ICS Column is not Displayed", "");
		}
	}

	/**
	 * Method is to click Email TechSpecs Button in Techsepcs Tab
	 * 
	 * @throws Throwable
	 */
	public void clickEmailTechSpecsButton() throws Throwable {
		isElementClickable(EMAILTECHSEPCSINTECHSEPCSTAB, 2, "Email TechSpecs Button");
	}

	/**
	 * Method is to send Email in EmailTechSpecs PopUp
	 * 
	 * @throws Throwable
	 */
	public void sendEamilinEmailTechSpecsPopUp(String Email) throws Throwable {
		waitForVisibilityOfElement(EMAILTECHSPECSPOPUPTOSENDEMAIL, "Email TechSpecs PopUp");
		if (isElementPresent(TOTEXTFIELDINEAMILTECHSPECSPOPUP, "To TextField In EmailTechSpecs PopUp")) {
			click(TOTEXTFIELDINEAMILTECHSPECSPOPUP, "To TextField In EmailTechSpecs PopUp");
			type(TOTEXTFIELDINEAMILTECHSPECSPOPUP, Email, "To TextField In EmailTechSpecs PopUp");
			reporter.SuccessReport("To TextField In EmailTechSpecs PopUp::",
					"To TextField In EmailTechSpecs PopUp is Displayed and Email Is Typed", "");
		} else {
			reporter.failureReport("To TextField In EmailTechSpecs PopUp::",
					"To TextField In EmailTechSpecs PopUp is not Displayed", "");
		}
	}

	/**
	 * Method is to Click Send Email Item
	 * 
	 * @throws Throwable
	 */
	public void clickSendEmailButton() throws Throwable {

		isElementClickable(SENDEMAILBUTTON, 3, "Send Email Item");

		click(SENDEMAILBUTTON, "Send Email Item");

	}

	/**
	 * Method is to Verify Email Sent Successfully and click Button
	 * 
	 * @throws Throwable
	 */
	public void emailSentSuccessfull() throws Throwable {
		waitForVisibilityOfElement(EMAILSENTSUCCESSPOPUP, "Email Sending Success PopUp");
		if (isElementPresent(EMAILSENTSUCCESSPOPUP, "Email Sending Success PopUp")) {
			clickOKinPopUp();
			reporter.SuccessReport("To TextField In EmailTechSpecs PopUp::",
					"To TextField In EmailTechSpecs PopUp is Displayed and Email Is Typed", "");
		} else {
			reporter.failureReport("To TextField In EmailTechSpecs PopUp::",
					"To TextField In EmailTechSpecs PopUp is not Displayed", "");
		}
	}

	/**
	 * Method is to click on HomeButton In ProductSearch Popup
	 * 
	 * @throws Throwable
	 */
	public void clickonHomeButtonInProductSearchPopup() throws Throwable {
		if (isElementPresent(HOMEBUTTONINPRODUCTSEARCHPOPUP, "Home Button In Popup")) {
			mouseDoubleClick(HOMEBUTTONINPRODUCTSEARCHPOPUP, "Home Button");
			reporter.SuccessReport("Home Button In Popup::", "Home Button In Popup Exists And Clicked", "");
		} else {
			reporter.failureReport("Home Button In Popup::", "Home Button In Popup is not Displayed", "");
		}
	}

	/**
	 * Method is to Click on X symbol of Con
	 * 
	 * @throws Throwable
	 */
	public void VerfyProductresultinProductSearchPopUp(String MaterialId) throws Throwable {
		String MATERAILID = getText(MATERAILAIDINPRODUCTSEARCHPOPUP, "MaterailId In Product Searc PopUp");
		if (MATERAILID.equals(MaterialId)) {
			reporter.SuccessReport("Product Search Popup::", "Materail Id is Verified in Product Search Popup", "");
		} else {
			reporter.failureReport("Product Search Popup::", "Materail Id is Not Verified in Product Search Popup", "");
		}
	}

	public void clickCloseButtonProductSearch() throws Throwable {
		click(CLOSEBTN_PRODUCTSEARCH, "Close Button");
	}

	/**
	 * Method is to Reject or Delete The Item
	 * 
	 * @throws Throwable
	 */
	public void Productsearch(String ContractId) throws Throwable {
		rightClick(contractIdItem(ContractId), "Click On ContactId of an Item::" + ContractId + "");
		click(PRODUCTSEARCHCONTRACTID, "Product Search Of an Item");
	}

	// CQT0309

	public void EnterDataInShipToAttn(String ShiptoAttn) throws Throwable {
		clearData(HomePage.txtShiptoAttn);
		type(HomePage.txtShiptoAttn, ShiptoAttn, "Ship To Attn");
	}

	public void ClickOnSaveAsQuoteButton() throws Throwable {
		click(HomePage.btnSaveAsQuote, "Save As Quote");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void CloseButtonofOutputform() throws Throwable {
		click(HomePage.CloseButtonofOutputform, "CloseButtonofOutputform");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		/*
		 * List<WebElement> elem =
		 * driver.findElements(HomePage.CloseButtonofOutputform); for(WebElement e:
		 * elem) {
		 * 
		 * if(isEnabled(HomePage.CloseButtonofOutputform, "CloseButtonofOutputform")) {
		 * click(HomePage.CloseButtonofOutputform, "CloseButtonofOutputform"); } }
		 */

	}

	public String ValidatedataInShipToAttn(String ShiptoAttn) throws Throwable {

		String ShiptoAttndata = getAttributeByValue(HomePage.txtShiptoAttn, "Ship To Attn");
		if (ShiptoAttndata.equals(ShiptoAttn)) {
			System.out.println("Data entered Successfully");
		} else {
			System.out.println("Data not entered successfully");
		}

		return ShiptoAttndata;
	}

	// CQT03_UpdQtyRepMargin_Action1_Script

	public void VerifyRowsUnderContractsTab() throws Throwable {

		List<WebElement> elem = driver.findElements(HomePage.rowsunderProgramsTab);
		int size = elem.size();
		if (size > 1) {
			System.out.println("Details are displaying");
		} else {
			System.out.println("No data found under Programs tab");
		}
	}

	public void SelectoptionsUnderContractId(String options) throws Throwable {

		Actions action = new Actions(driver);
		WebElement elem = driver.findElement(HomePage.ContractIdField);
		action.contextClick(elem).perform();
		click(HomePage.Lineitemrightclickoptions(options), "right click options");
	}

	public void SelectoptionsUnderItemLine(String options, int row) throws Throwable {

		Actions action = new Actions(driver);
		WebElement elem = driver.findElement(ItemOnGrid(row));
		action.contextClick(elem).perform();
		for (int i = 0; i < 5; i++) {
			if (isVisible(Lineitemrightclickoptions(options), "options")) {
				break;
			} else {
				action.contextClick(elem).perform();
			}
		}
		click(HomePage.Lineitemrightclickoptions(options), "right click options");
	}

	public void selectoptionfromManufacturer(String manufacturer) throws Throwable {

		click(HomePage.Manufacturer, "manufacturer dropdown");
		WebElement elem = driver.findElement(HomePage.Manufacturer1);

		// typeUsingJavaScriptExecutor(HomePage.Manufacturer1, "CISCO sysytem",
		// "manufacturer");
		elem.sendKeys("CISCO system");
		// type(HomePage.Manufacturer, manufacturer, "Manufacturer value");

		// click(HomePage.Manufacturer, "manufacturer dropdown");
		elem.sendKeys(Keys.DOWN);

		// elem.sendKeys(Keys.DOWN);
		elem.sendKeys(Keys.ENTER);

	}

	public void checkStokInOnlyCheckbox() throws Throwable {
		click(HomePage.InStockOnlyCheckbox, "Stock In Only");
	}

	public void selectpricing() throws Throwable {

		selectByValue(HomePage.PricingDropdown, "true", "PricingDropdown");

	}

	public void closebuttonInProductSearch() throws Throwable {

		click(HomePage.closebuttonInProductSearch, "closebuttonInProductSearch");
	}

	public void EnterkeywordSearch(String keyword) throws Throwable {
		type(KEYWORDSEARCHTEXTFIELD, keyword, "keyword");
	}

	public void SearchButton() throws Throwable {
		click(SEARCHBUTTONBKEYWORDSEARCH, "Search button");
	}

	public void selectproductt() throws Throwable {
		// lblMaterialID

		Actions acta = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"center\"]/div/div[4]/div[1]/div/div[4]/div[1]"));
		ele.click();
		// acta.click(ele);
		acta.doubleClick(ele).perform();

		/*
		 * List<WebElement> materialList = driver.findElements(HomePage.lblMaterialID);
		 * if (materialList.size() > 0) { Actions action = new Actions(driver); for (int
		 * j = 2; j < materialList.size(); j++) { materialList.get(j);
		 * action.doubleClick(materialList.get(j)); break; } } else {
		 * reporter.failureReport("Resutls", "Search results are not found", "",
		 * driver); }
		 */
	}

	public void selectproduct() throws Throwable {

		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		((JavascriptExecutor) driver).executeScript("window.scrollBy(2000,0)");

		List<WebElement> elem = driver.findElements(HomePage.productselect);
		int size = elem.size();
		while (size > 0) {
			for (int i = 1; i < size; i++) {

				String text = elem.get(i).getText();
				int data = Integer.parseInt(text);

				if (data > 0) {

					Actions action = new Actions(driver);
					action.doubleClick(elem.get(i));
					break;
				} else {

					System.out.println("No product found");
				}

			}
		}
	}

	public float TotalWeight() throws Throwable {
		String totalweight = getText(HomePage.TotalWeight, "TotalWeight");
		float tW = new Float(totalweight);
		return tW;

	}

	public void EnterEmail(String email) throws Throwable {
		clearData(Email);
		typeText(Email, email, "Email address");

	}

	public void ClickOnSendbutton() throws Throwable {
		click(HomePage.Sendbutton, "Send button");
		loadingSymbol();
	}

	// CQT16_IPSCopyLinks_Action1_Script

	public void AddlineItemFromProductSearch(String Lineitem) throws Throwable {
		clickLineItemHeaderTab("Product Search");
		driver.switchTo().defaultContent();
		EnterkeywordSearch(Lineitem);
		SearchButton();
		loadingSymbol();
		clickOnMaterialID();
		loadingSymbol();

		driver.switchTo().defaultContent();

		clickOnAddToOrderButton();
		loadingSymbol();

		closebuttonInProductSearch();
		driver.switchTo().parentFrame();

	}

	public void ClickOnXsymbolunderCon() throws Throwable {
	
		if(waitForVisibilityOfElement(XsymbolUnderCon, "XsymbolUnderCon"))
		click(HomePage.XsymbolUnderCon, "XsymbolUnderCon");
		loadingSymbol();
	}

	public void ClickOnCopyContracttoallLines() throws Throwable {
		click(HomePage.btnCopyContracttoallLines, "btnCopyContracttoallLines");
	}

	public void clickonCopyreportingfieldstoallthelines() throws Throwable {
		click(HomePage.btncopyreportingfieldstoallthelines, "Copyreportingfieldstoallthelines");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void SelectAdherencetoflooroption(String radiobutton) throws Throwable {
		click(HomePage.Adherencetofloorradiobuttonoptions(radiobutton), "Adherencetoflooroption");

	}

	public void EnterDatainDivercityPartner(String contractNumber) throws Throwable {
		type(HomePage.txtDivercityPartner, contractNumber, "DivercityPartner");

	}

	public void SearchButtonOfDivercityPartner() throws Throwable {
		click(HomePage.SearchButtonOfDivercityPartner, "SearchButtonOfDivercityPartner");

	}

	public void ClickOndivercityPartnerResult() throws Throwable {
		Actions action = new Actions(driver);
		WebElement elem = driver.findElement(HomePage.DivercityPartnerresults);
		action.doubleClick(elem).perform();
	}

	public void EnterUSCOMMember(String textfiled, String USCommember) throws Throwable {
		type(HomePage.EntertextfiledsInLineItemsmodelbox(textfiled), USCommember, "USCommember");
	}

	public void Adherencetofloorreason(String text) throws Throwable {
		driver.findElement(txtAdherencetofloorreason).click();
		// click(HomePage.txtAdherencetofloorreason, "Adherencetofloorreason");
		type(HomePage.txtAdherencetofloorreason, text, "Adherencetofloorreason");

	}

	public void ClickOncancelbutton() throws Throwable {
		click(HomePage.btnCancel, "Cancel Buttonb");

	}

	public static  void ClickonArrowNextToLineitem() throws Throwable {
		click(HomePage.btnArrowicon, "ArrowNextToLineitem");
	}

	public void ClickonleftArrowpriorToLineitem() throws Throwable {
		click(HomePage.btnLeftArrowicon, "ArrowNextToLineitem");
	}

	public void validatetheLineitemfiledsaftersaving() throws Throwable {
		ArrayList<String> al = new ArrayList<String>();

		String USCOMMember = getAttributeValue(HomePage.txtUSCOMMEMBERID, "value");
		String IpsContarctId = getAttributeValue(HomePage.txtIpsContarctId, "value");
		String ReportingField5 = getAttributeValue(HomePage.txtReportingField5, "value");
		String ReportingField4 = getAttributeValue(HomePage.txtReportingField4, "value");

		String divercityPartner = getAttributeValue(HomePage.txtBiodivercitypartner, "value");
		al.add(USCOMMember);
		al.add(IpsContarctId);
		al.add(ReportingField5);
		al.add(ReportingField4);
		al.add(divercityPartner);
		System.out.println(al);
		if (al.size() <= 0) {
			System.out.println("data is not displaying");
		} else {
			System.out.println("Data displayed");
		}
	}

	public void loadingSymbol() throws Throwable {
		try {
			boolean loadingsymbol = driver.findElement(HomePage.loadingsymbol).isDisplayed();
			if (loadingsymbol == true) {
				waitForInVisibilityOfElement(HomePage.loadingsymbol, "loadingsymbol");
			}
		} catch (Exception e) {
			System.out.println(" ");
		}

	}

	public void SelectContractId(int contract) throws Throwable {
		Thread.sleep(2000);
		String value = "";
		List<WebElement> el = driver.findElements(HomePage.rowsunderProgramsTab);
		if (el.size() > 0) {
			for (int i = contract; i <= el.size(); i++) {
				value = el.get(i).getText();
				if (value != null) {
					el.get(i).click();
					break;
				} else {
					reporter.failureReport("Contracts", "Search results are not found", "", driver);
				}
			}
		}

	}

	public void clickOnQuoteandAddlineitemsfromProductSearch(String[] array, String SoldTovalue) throws Throwable {

		clickOnCreateQuoteLink();
		ClickOnsideViewBar();
		enterSoldToValue(SoldTovalue);
		clickOnSoldToSearchIcon();
		loadingSymbol();
		for (int i = 0; i <= array.length - 1; i++) {
			if (array[i] != null) {
				AddlineItemFromProductSearch(array[i]);
			} else if (array[i] == null) {
				break;
			}
		}

		/*
		 * AddlineItemFromProductSearch(array[0]);
		 * AddlineItemFromProductSearch(array[1]);
		 * AddlineItemFromProductSearch(array[2]);
		 * AddlineItemFromProductSearch(array[3]);
		 */

	}

	public static String getLineItemText() throws Throwable {
		String text = getAttributeValue(HomePage.LineitemText, "value");
		return text;
	}

	public void reportingFiledData() throws Throwable {
		String reportingFileddataofLine1 = getAttributeValue(HomePage.txtUSCOMMEMBERID, "value");
		if (reportingFileddataofLine1.equals("Test")) {
			System.out.println("Test Dispalyed");
		} else {
			System.out.println("Test not displayed");
		}


	}

	public void ClickOnConverToOrder() throws Throwable {
		click(HomePage.btnConvertToOrder, "ConverToOrder");
		loadingSymbol();
		boolean SaveasOrder = driver.findElement(SAVEAVSORDERBUTTON).isDisplayed();
		if (SaveasOrder == true) {
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		} else {
			click(HomePage.btnConvertToOrder, "ConverToOrder");
			loadingSymbol();
		}

	}

	public void enterPONumber(String data) throws Throwable {
		click(HomePage.txtPoNumber, "PO Number");
		type(HomePage.txtPoNumber, data, "PO Number");
		
		}

	public String getPONumber() throws Throwable {
		String PONUmber = getAttributeByValue(txtPoNumber, "PO Number");
		return PONUmber;
	}

	public String getPOrelNumber() throws Throwable {
		String POrelNumber = getAttributeByValue(HomePage.txtPoRelNumber, "PO Release Number");
		return POrelNumber;
	}

	public String getSoldtoAttn() throws Throwable {
		String SoldtoAttn = getAttributeByValue(txtSoldToAttn, "Sold to Attn");
		return SoldtoAttn;
	}

	public String getShiptoAttn() throws Throwable {
		String ShiptoAttn = getAttributeByValue(HomePage.SHIP_TO_ATTN, "Ship to Attn");
		return ShiptoAttn;
	}

	public void enterPOrelNumber(String data) throws Throwable {
		click(HomePage.txtPoRelNumber, "PO Release Number");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try{
			if (waitForVisibilityOfElement(OkButtonInPONumber, "Ok button after entering PO nuMber")) {
				clickOkButton();
			}
	}
	catch(Exception e ){
	System.out.println("List of po numbers are not displayed");}
	click(HomePage.txtPoRelNumber, "PO Release Number");
			type(HomePage.txtPoRelNumber, data, "PO Release Number");
		}

	

	public void enterSoldtoAttn(String data) throws Throwable {
		click(HomePage.txtSoldToAttn, "Sold to Attn");
		type(HomePage.txtSoldToAttn, data, "Sold to Attn");
	}

	public void enterShiptoAttn(String data) throws Throwable {
		click(HomePage.SHIP_TO_ATTN, "Ship to Attn");
		type(HomePage.SHIP_TO_ATTN, data, "Ship to Attn");
	}

	/**
	 * 
	 * @return
	 * @throws Throwable
	 */

	public String GetQuoteName() throws Throwable {
		String QuoteName = getAttributeByValue(txtQuoteName, "QuoteName");
		System.out.println(QuoteName);
		if (QuoteName != null) {
			reporter.SuccessReport("QuoteName ::", "QuoteName is displayed", "");

		} else {
			reporter.failureReport("QuoteName::", "QuoteName is not Displayed or null", "");

		}
		return QuoteName;
	}
 
	 /**
	  * 
	  * @return
	  * @throws Throwable
	  */
	 
	 
	
	public String GetErateFRN() throws Throwable {
		String ERateFRN = getAttributeByValue(HomePage.txtERateFRN, "ERateFRN");

		return ERateFRN;
	}

	public void clickOnSaveorderwithoutAttchment() throws Throwable {
		click(HomePage.btnSaveOrderwithoutAttachment, "SaveOrderwithoutAttachment");
		loadingSymbol();
	}

	public String GetSubjectlinevalue() throws Throwable {

		String subvalue = getAttributeValue(HomePage.txtsubject, "value");
		return subvalue;
	}

	public void enterEstimateId(String data) throws Throwable {
		type(HomePage.txtEnterEstimateID, data, "Estimate Id");
	}

	/**
	 * 
	 * @return
	 * @throws Throwable
	 */
	public String GetQuoteNumber() throws Throwable {
		String QuoteNUmber = getAttributeByValue(HomePage.QuoteNumber, "Quote Number");

		return QuoteNUmber;
	}

	/**
	 * Method is to Add Line Item
	 *
	 * @throws Throwable
	 */
	public void AddMaterialOnLineItem(String MaterialID) throws Throwable {

		isElementClickable(AddMeterialInLineItems, 3, "Material Line item");
		isElementType(AddMeterialInLineItems, MaterialID, 2, "MaterialID");
		// driver.findElement(AddMeterialInLineItems).sendKeys(MaterialID);
		driver.findElement(AddMeterialInLineItems).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	/*
	 * click(AddMeterialInLineItems, "Material Line item");
	 * driver.findElement(AddMeterialInLineItems).sendKeys(MaterialID); WebElement
	 * element = driver.findElement(lineItemID); element.sendKeys(Keys.ENTER);
	 * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); String
	 * AddedMaterialI = getText(HomePage.lineItemID,"Entered ID");
	 * if(AddedMaterialI.contains(MaterialID)){ reporter.SuccessReport(
	 * "Enter Line Items", "Requested data was  found and added.",MaterialID); }
	 * else { reporter.failureReport("Enter Line Items",
	 * "Data grid was not found to add Materials", "",driver); } }
	 */

	public void clickOnCOntractIDinLineItemsList() throws Throwable {
		if (isElementPresent(CONTRACT_SYMBOL, "CON X Symbol", true)) {
			click(CONTRACT_SYMBOL, "CON X Symbol");
		} else
			reporter.failureReport("Click on CON Symbol", "Element not present to click", "", driver);

	}

	public void selectCOntractID(String contactid, String contactTabName) throws Throwable {
		isElementClickable(tabinItemDetailsPopup(contactTabName), 4, "Contract ID");
		click(tabinItemDetailsPopup(contactTabName), "Highlighted Contract ID");
		JSClick(selectContractID(contactid), "Selected contract:" + contactid);
		reporter.SuccessReport("Select "+contactid+"  Contract", "Contract was selected", "");

	}

	public void selectCOntractSubTabName(String contactTabName) throws Throwable {
		if (isElementPresent(tabinItemDetailsPopup(contactTabName), "Contract ID", true)) {
			click(tabinItemDetailsPopup(contactTabName), "Highlighted Contract ID");
		}
	}

	public void enterCancelButtonInPoupHdr() throws Throwable {
		if (waitForVisibilityOfElement(OUTPUT_POPUHDR, "Output Header Popup")) {
			click(CANCEL_BTN, "Cancel Button");
		}
	}

	public String selectproductinPricingTable(String text) throws Throwable {
		String data = "";
		int pricevalue = 0;
		List<WebElement> elem = getWebElementList(HomePage.PricingTabTable, "Pricing List");
		int size = elem.size();
		if (isVisibleOnly(PRICINGID_VALUE(text), "Pricing ID")) {
			String price = getText(HomePage.pricing_ValueClmn(text), "Pricing value");
		}

		else {
			elem.get(size).click();
			for (i = size; i != 0; i++) {
				driver.findElement(PricingTabTable).sendKeys(Keys.ARROW_DOWN);
			}
		}
		return data;
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void downarrow() throws Throwable {
		Thread.sleep(3000);
		String combineKeys3 = Keys.chord(Keys.ARROW_DOWN);
		sendKeys(combineKeys3, "DOWN");
	}


	/**
	 * 
	 * @param text
	 * @return
	 * @throws Throwable
	 */

	/*
	 * public String pricingValue(String text) throws Throwable { String elementText
	 * = ""; String PriceVal = ""; WebElement yourElement =
	 * driver.findElement(By.xpath("(//div[@class='ag-body-container']/div[1])[2]"))
	 * ; // WebElement yourElement = //
	 * driver.findElement(By.xpath("//div[@class='ag-body-container']//div[@class='
	 * ag-row // ag-row-no-animation ag-row-level-0 ag-row-even ag-row-focus'and //
	 * row='0']")); yourElement.click(); //
	 * click(By.xpath("//div[@class='ag-body-container']//div[@class='ag-row //
	 * ag-row-no-animation ag-row-level-0 ag-row-even ag-row-focus']"), //
	 * "Testing"); click(By.xpath("(//div[@class='ag-body-container']/div[1])[2]"),
	 * "testing"); int i = 0; while (i < 60) {
	 * yourElement.sendKeys(Keys.ARROW_DOWN); WebElement element =
	 * driver.findElement(By.xpath("(//*[@colid='id' and  @tabindex])[i]"));
	 * elementText = element.getText(); if (elementText.equals(text)) { PriceVal =
	 * getText(HomePage.pricing_ValueClmn(text), "Pricing value"); break; } else
	 * ++i;
	 */

	public void VerifyContractPriceShouldbeEqualToYPOO(String YP00, String contractid) throws Throwable {
		String ContractPrice = getText(getcontractsellingPrice(contractid), "get sell price");
		if (ContractPrice.equals(YP00)) {
			reporter.SuccessReport("Verify that YP00 should equal to contractid",
					"YP00" + YP00 + " equals to contractid" + ContractPrice + "", "");
		} else {
			reporter.failureReport("Verify that YP00 should equal to contractid", "YP00 not equals to contractid ", "",
					driver);
		}
	}

	public void VerifyContractPriceShouldbeEqualToYPOO(String contractid,float YP00) throws Throwable {
		 String ContractPrice = getText(getcontractsellingPrice(contractid), "get sell price");
			if (ContractPrice.equals(YP00)) {
				reporter.SuccessReport("Verify that YP00 should equal to contractid", "YP00"+YP00+" equals to contractid"+ContractPrice+"", "");
			} else {
				reporter.failureReport("Verify that YP00 should equal to contractid", "YP00 not equals to contractid ","", driver);
			}
	 }
		


	public String getPricingValue(String text) throws Throwable {
		/*
		 * if(isElementPresent(HomePage.pricing_ValueClmn(text), text+
		 * "Element is present")){ LOG.info(text+"Element is displayed"); }
		 */
		String data = driver.findElement(HomePage.pricing_ValueClmn(text)).getText();
		System.out.println("data" + data);
		return data;
	}

	/**
	 * 
	 * @param text
	 * @return
	 * @throws Throwable
	 */
	public String getPricingValuefromCell(String text) throws Throwable {

		String price = null;
		for (int i = 0; i < 10; i++) {
			if (isVisibleOnly(PRICINGID_VALUE(text), "Pricing ID")) {

				price = getText(HomePage.pricing_ValueClmn(text), "Pricing value");
				break;
			} else {
				// ((WebElement) driver).sendKeys(Keys.DOWN);
				setFocusAndClick(PRICINGID_VALUE(text), "Pricing ID");
			}
		}

		return price;
	}

	/**
	 * 
	 * @param ZPFX
	 * @param ZPLS
	 * @throws Throwable
	 */
	public void VerifyZPFXShouldbeGreaterThanZPLS(float ZPFX, float ZPLS) throws Throwable {
		if (ZPFX > ZPLS) {
			reporter.SuccessReport("Verify that ZPFX price is higher than ZPLS", "ZPFX price higher than ZPLS",
					ZPFX + "is greater than" + ZPLS);
		} else {
			reporter.failureReport("Verify that ZPFX price is higher than ZPLS", "ZPFX price not higher than ZPLS", "",
					driver);
		}
	}

	public void VerifyZ0RCPlusYMSMequalstheYP00andZP00(float ZP00, float YMSM, float Z0RC) throws Throwable {

		if ((ZP00 == YMSM + Z0RC)) {
			reporter.SuccessReport("Verify that Z0RC Plus YMSM equals the YPOO and ZPOO value.",
					"YMSM Plus Z0RC equals to ZP00", "ZPOO:" + ZP00 + " / YMSM+ Z0RC:" + (Z0RC + YMSM));
		} else {
			reporter.failureReport("Verify that Z0RC Plus YMSM equals the YPOO and ZPOO value.",
					"YMSM Plus Z0RC not equals to ZP", " ZPOO:" + ZP00 + " / YMSM+ Z0RC:" + ((Z0RC + YMSM)), driver);
		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void clickTaxExemptSearchIcon() throws Throwable {
		click(TAXEXEMPT_SEARCHBTN, "Tax Exempt Searcg button");
	}

	public Boolean PartnerIDverification() throws Throwable {
		return isVisibleOnly(PARTNERSELECTION_POPUP, "Tax Exempt Partner popup");
	}

	public int verifyandgetTextPartnerID() throws Throwable {
		isVisibleOnly(PricingTabTable, "Tax Exempt Partner ID");
		String PArtnerID = getText(PricingTabTable, "Tax Exempt Partner ID");
		int ID = Integer.parseInt(PArtnerID);
		mouseDoubleClick(PricingTabTable, "Tax Exempt Partner ID");
		if (PartnerIDverification()) {
			LOG.info("Tax Exemption popup closed");
		}
		return ID;

	}

	public int partnerIDcomparision() throws Throwable {
		String partnerIDinTExtbox = getText(TAXEXEMPT_TEXTBOX, "Tax Exempt Partner ID displayed in textbox");
		int tax = Integer.parseInt(partnerIDinTExtbox);
		return tax;
	}

	public void taxValidationequalstozerocheck() throws Throwable {
		int i = 0;
		String tax_Exemptvalue = getText(TAX_IN_SUMMARY, "Tax in summary panel");
		int tax = Integer.valueOf(tax_Exemptvalue);
		if (tax == i) {
			reporter.SuccessReport("Costing Updated - Verify Tax",
					"Order Tax is displaying as $0.00 in Summary Data as expected.", "");
		} else {
			reporter.failureReport("Costing Updated - Verify Tax",
					"Order Tax is NOT displaying as $0.00 in Summary Data as expected.", "", driver);
		}

	}

	public void removeTaxExempt() throws Throwable {
		String taxid = getText(TAXEXEMPT_TEXTBOX, "Tax Exemption id dislayed in textbox");
		click(TAX_REMOVEBTN, "Tax Exempt Remove button");
		if (taxid.isEmpty()) {
			reporter.SuccessReport("Tax Partner ID Removal Validation",
					"The 'Tax Exempt Partner' ID in the Advanced Header section was removed as expected.", "");
		} else {
			reporter.failureReport("Tax Partner ID Removal Validation",
					"The 'Tax Exempt Partner' ID in the Advanced Header section appears NOT to have been removed.", "",
					driver);

		}
	}

	public void taxValidationNotequalstozerocheck() throws Throwable {
		int i = 0;
		String tax_Exemptvalue = getText(TAX_IN_SUMMARY, "Tax in summary panel");
		int tax = Integer.valueOf(tax_Exemptvalue);
		if (tax != i) {
			reporter.SuccessReport("Costing Updated - Tax Amount Does Display",
					"The Order Tax displays in the Summary Data section as expected." + tax, "");
		} else {
			reporter.failureReport("Costing Updated - Tax Amount Does Display",
					"The Order Tax DOES NOT displaying in Summary Data as expected.", "", driver);
		}

	}

	public void verifyTaxJurisdictionCode(String TaxJurisCode) throws Throwable {
		String code = getText(TAX_JURISDICTION_CODE, "Tax Jurisdiction Code");
		if (TaxJurisCode.equals(code)) {
			reporter.SuccessReport("Capture Tax Jurisdiction Code - Pass",
					"The Tax Jurisdiction Code was found in the Edit Address popup as expected.", "");
		} else {
			reporter.failureReport("Capture Tax Jurisdiction Code - Fail",
					"The Tax Jurisdiction Code COULD NOT be found in the Edit Address popup as expected.", "", driver);
		}
	}

	public void editAddressandZipcode(String address, String Zipcode) throws Throwable {
		click(OVERIDE_CHECKBOX, "Overwrite address checkbox checked");
		if (isEnabled(EDIT_ADDRESSPOPUP, "Fields are enabled and can overwritten the data")) {
			clearData(EDITADDRESS_FIELD);
			type(EDITADDRESS_FIELD, address, "Address overwritten");
			clearData(EDITZIPCODE_FIELD);
			type(EDITZIPCODE_FIELD, Zipcode, "Zipcode");
			click(EDITADDRESSWIN_CANCELBTN, "Cancel button in Edit address window");

		}
	}

	public Boolean verifyEditAddresspopupWindow() throws Throwable {
		return isVisibleOnly(EDIT_ADDRESSPOPUP, "Edit Address popup");
	}

	public void clickSalesDocDropdown(String SalesDD) throws Throwable {
		click(SALES_DOC_DD, "Sales document dropdown ");
		click(SALES_DOC_DD_SELECT(SalesDD), SalesDD + "is selected");
	}

	public void clickOnSideheaderTabs(String TabName) throws Throwable {
		click(ExpandTabs(TabName), TabName + "Expanded");
	}

	/**
	 * 
	 * @param YPOO
	 * @param ZPLS
	 * @param ZPOO
	 * @throws Throwable
	 */
	public void VerifyZPLSShouldbeEqualToZPOOandYPOO(float YPOO, float ZPLS, float ZPOO) throws Throwable {
		if ((ZPLS==YPOO) && (ZPLS==ZPOO)) {
			reporter.SuccessReport("Verify that ZPLS should equal to YP00 & ZP00", "ZPLS equals to YP00 & ZP00 ", "");
		} else {
			reporter.failureReport("Verify that ZPLS should equal to YP00 & ZP00", "ZPLS not equals to YP00 & ZP00 ",
					"ZPLS:" + ZPLS + " not equals to YP00:" + YPOO + "and ZP00: " + ZPOO, driver);
		}
	}

	public void VerifyContractPriceShouldbeEqualToYPOO(float YP00,String contractid) throws Throwable {
		String ContractPrice = getText(getcontractsellingPrice(contractid), "get sell price");
		float cellpric = Float.valueOf(ContractPrice);

		if (cellpric == YP00) {
			reporter.SuccessReport("Verify that ZPLS should equal to YP00 & ZP00", "ZPLS equals to YP00 & ZP00 ", "");

		if (cellpric==YP00) {
			reporter.SuccessReport("Verify that contractid should equal to YP00", "Contract price equals to YP00", "");

		} else {
			reporter.failureReport("Verify that contractid should equal to YP00", "Contract price not equals to YP00",
					"Contract Sell Price:" + ContractPrice + " not equals to YPOO:" + YP00, driver);
		}
	}
	}

	/**
	 * Method is to Select First Product in Results Of contract Id in AdvancedHeader
	 * 
	 * @throws Throwable
	 */
	public void selectFirstProductinResultsOfKeywordsearchcontractIdinAdvancedHeader(String ContractId)
			throws Throwable {
		waitForVisibilityOfElement(RESULTSOFKEYWORDSEARCHCONTRACTID, "Search Results for Keywordsearch");
		List<WebElement> Results = driver.findElements(HomePage.RESULTSOFKEYWORDSEARCHCONTRACTID);
		for (int i = 0; i < 1; i++) {

		}
		rightClick(contractIdItem(ContractId), "Click On ContactId of an Item::" + ContractId + "");
		click(PRODUCTSEARCHCONTRACTID, "Product Search Of an Item");
	}

	/**
	 * Method is to Verify Highlighted Colour is Yellow for Given Contract
	 * 
	 * @throws Throwable
	 */
	public void VerifycolourContractIdHighlitedwithYellow(String Contactid, String colour) throws Throwable {
		String Colour[] = driver.findElement(contractIDwithHiletedClour(Contactid)).getAttribute("style").split(";");
		if (Colour[0].contains(colour)) {
			reporter.SuccessReport("Colour Of ContactId::", "Colour Of ContactId Highlighted With:: " + Colour[0] + "",
					"");
		} else {
			reporter.failureReport("Colour Of ContactId::", "Colour Of ContactId not Highlighted", "");
		}
	}

	public String clickOnLItem00020CON(String LineitemName, String ColumnName) throws Throwable {
		click(cloumnsUnderLineItems(LineitemName, ColumnName), "LineItem 00020");
		return "";
	}

	public void clickOnVCTab(String tabname) throws Throwable {
		click(HomePage.btnVCtab(tabname), "VC tab");

	}

	public void enterDurationUnderVC(String duration) throws Throwable {
		type(HomePage.txtDurationField, duration, "duration data");
	}

	public void enterDurationUnderAdvanceHeader(String duration) throws Throwable {
		type(HomePage.txtDurationFieldUnderAdvanceHeader, duration, "duration data");
	}

	public void clickOnCopyDuartion() throws Throwable {
		click(HomePage.btnCopyDuration, "Copy Duration");
	}

	public String getDurationFieldValue() throws Throwable {
		String value = getAttributeValue(HomePage.txtDurationField, "value");
		return value;
	}

	public void VerifyDuration(String Durationvalue) throws Throwable {
		ClickonArrowNextToLineitem();
		ClickonArrowNextToLineitem();
		String duration = getDurationFieldValue();
		if (duration.equals(Durationvalue))
			System.out.println("Duration is " + Durationvalue);
		else
			System.out.println(Durationvalue + " is not updated");
	}

	
		
		public String getmfgPricevalue(String LineitemName,String ColumnName,String durationvalue)throws Throwable{
			Actions action = new Actions(driver);
			WebElement elem = driver.findElement(cloumnsUnderLineItems(LineitemName, ColumnName));
			action.moveToElement(elem).build().perform();
			if(ColumnName!="frtCharge") {
				clickOnLItem00020CON(LineitemName,ColumnName);
			}
			String price = getText(cloumnsUnderLineItems(LineitemName, ColumnName),"freight values");
			if(price.equals(durationvalue))
	        	System.out.println("LineItem "+LineitemName+ " mfg Price is : " +price );
			else {
				
			}
			return price;
		}
		

	public void Enter36MonthstoEndDateField(int Months) throws Throwable {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MONTH, Months);
		Date dateAsObjAfterAMonth = date.getTime();
		String todaysDate = dateFormat.format(dateAsObjAfterAMonth);
		System.out.println(todaysDate);
		type(HomePage.txtEndDateFiel, todaysDate, "duration data");
	}

	public void clickOnCopydates() throws Throwable {
		click(HomePage.btnCopyDates, "Copy dates");
	}

	public void checkdurationfieldenabledordisabled() throws Throwable {
		boolean df = false;
		df = isEnabled(HomePage.txtDurationField, "Duration");
		if (df) {
			System.out.println("Durarion field is editable");
		} else
			System.out.println("Durarion field is not editable");

	}

	public void ClearEnddateCoveragefield() throws Throwable {
		clearData(HomePage.coverageEndDate);
	}

	public void doubleclickOnLineItem(String LineItem) throws Throwable {
		Actions action = new Actions(driver);
		WebElement elem = driver.findElement(HomePage.Lineitem10(LineItem));
		action.doubleClick(elem).perform();

	}

	public void clickOnrefreshContracts() throws Throwable {
		click(HomePage.btnRefreshContracts, "RefreshContracts");
	}

	public void selectContractIdwithDesc(String desc) throws Throwable {
		click(HomePage.ContractIdDesc(desc), "Contract desc");
	}

	public String coverageStartDate() throws Throwable {
		String coveragestartdate = getAttributeValue(HomePage.txtCoverageStartDate, "Value");
		return coveragestartdate;
	}

	public String coverageEndDate() throws Throwable {
		String coverageEnddate = getAttributeValue(HomePage.txtCoverageEndDate, "Value");
		return coverageEnddate;
	}

	/**
	 * 
	 * @param YPOO
	 * @param ZPOO
	 * @throws Throwable
=======
	/*
	 * This Method is to Verify Contract Price Equals To YP00
>>>>>>> b41fcb294868b016ba44292849e2b3a21a2c8eea
	 */

	public void VerifyContractPriceShouldbeEqualToYPOO(int contractSellingPrice, int YPOO) throws Throwable {
		if (contractSellingPrice == YPOO) {
			reporter.SuccessReport("Verify that contractSellingPrice should equal to YPOO",
					"contractSellingPrice equals to YP00", "" + contractSellingPrice + "," + YPOO + "");
		} else {
			reporter.failureReport("Verify that contractSellingPrice should equal to YPOO",
					"contractSellingPrice is Not equals to YP00", "", driver);
		}

	}


	public void VerifyZPOOShouldbeEqualToYPOO(float ZPOO, float YPOO) throws Throwable {
		if ((ZPOO == YPOO)) {
			reporter.SuccessReport("Verify that ZPOO equals to YPOO value.", "ZPOO equals to YPOO",
					"ZPOO:" + ZPOO + " equals to YPOO" + YPOO);
		} else {
			reporter.failureReport("Verify that YPOO equals to ZPOO value.", "YPOO not equals to ZPOO",
					"ZPOO:" + ZPOO + " not equals to YP00" + YPOO, driver);
		}
	}

	/*
	 * 
	 * @param YMSM
	 * @param Z0RC
	 * @param ZPOO
	 * @throws Throwable
	 */

	public void VerifyZ0RCPlusYMSMequalstheYP00andZP00(String ZPOO, String YMSM, String Z0RC) throws Throwable {

		if ((ZPOO == YMSM + Z0RC)) {
			reporter.SuccessReport("Verify that Z0RC Plus YMSM equals the YPOO and ZPOO value.",
					"YMSM Plus Z0RC equals to ZP00", "ZPOO:" + ZPOO + " / YMSM+ Z0RC:" + (Z0RC + YMSM));
		} else {
			reporter.failureReport("Verify that Z0RC Plus YMSM equals the YPOO and ZPOO value.",
					"YMSM Plus Z0RC not equals to ZP", " ZPOO:" + ZPOO + " / YMSM+ Z0RC:" + ((Z0RC + YMSM)), driver);
		}
	}

	/*
	 * This method is to Click on Copy Contract to all Lines Button
	 */

	public void clickOnCopyContarctToallLineItems() throws Throwable {
		click(COPYCONTRACT, "Copy Contract to all Lines");
	}

	/*
	 * This method is to Click on Tabs in ItemDetails PopUp
	 */
	public void clickOnTabsInLineItemDetailsPopUp(String Tab) throws Throwable {
		click(tabsInItemDetailsPopup(Tab), "Item Details Tab::" + Tab + "");
	}

	/*
	 * This method is to Click on Tabs in ItemDetails PopUp
	 */
	public int getSellPriceFromContract(String contractid) throws Throwable {
		String ContractPrice = getText(getcontractsellingPrice(contractid),
				"Selling Price of ContractId" + contractid + "");
		int SellingPrice = Integer.parseInt(ContractPrice);
		return SellingPrice;
	}

	/*
	 * Method is to Click on Aquire Estimate Button
	 * 
	 * @throws Throwable
	 */
	public void clickAcquireEstimateBtn() throws Throwable {
		click(ACQUIRE_ESTIMATEBTN, "Acquire Estimate/Quote button");
	}
	/*
	 * Method is to Enter Estimate Number
	 * 
	 * @throws Throwable
	 */
	public void enterEstimateNumber(String CiscoID) throws Throwable {
		if (isVisibleOnly(ACQUIRE_ESTIMATE_HDR, "Acquire Estimate Header")) {
			type(ESTIMATE_NUMTEXTBOX, CiscoID, "Acquire Estimate/Quote button");
			click(INFO_POPUP_OKBTN, "Ok Button");
		}

	}
	/*
	 * Method is to Click on Copy Contract To ALL LineItems
	 * 
	 * @throws Throwable
	 */
	public void copyAllContractstoAllLines() throws Throwable {
		click(COPYALLCONTRACTS_BTN, "Copy to all contrcats lines button");
		click(WARNING_YESBTN, "Warning Popup Yes Button");

	}

	public void selectDDoptionFromHistoryDD(String HistoryOption) throws Throwable {
		click(HISTORY_DD, "History Dropdown");
		click(SALES_DOC_DD_SELECT(HistoryOption), HistoryOption + "selected");
	}

	public String getQuoteNUmFromDocumentFlowPopup(String QuoteNumber) throws Throwable {
		String Quotenum = driver.findElement(By.xpath("(//*[@colid='docNumber' and text()='" + QuoteNumber + "'])[1]"))
				.getAttribute("innerText");
		return Quotenum;

	}

	public void enterDocReferenceNumberandGetText(String RefNUmber) throws Throwable {
		type(REF_DOCTEXTBOX, RefNUmber, "Reference Document Number");
	}
	/*
	 * This Method is to Get Reference Number Doccument Flow PopUp
	 */
	public String getReferenceNUmFromDocumentFlowPopup(String RefNUmber) throws Throwable {
		String Quotenum = driver.findElement(By.xpath("(//*[@colid='docNumber' and text()='" + RefNUmber + "'])[1]"))
				.getAttribute("innerText");
		return Quotenum;

	}
	/*
	 * This Method is to Get Duration Time
	 */
	public void getDurationTime(String LineItem, String Duration) throws Throwable {
		String ItemValue = driver.findElement(LINEITEM_INPOPUP).getAttribute("value");
		if (ItemValue.equals(LineItem)) {
			String timeDuration = driver.findElement(TIME_DURATION).getAttribute("value");
			if (timeDuration.equals(Duration)) {
				reporter.SuccessReport("Verify Duration in VC tab", "particular Line item verified successfully ", "");
			} else
				reporter.failureReport("Verify Duration in VC tab",
						"particular Line item is not equal to the given Actual duration: " + timeDuration
								+ "Expected Deration:" + Duration,
						"", driver);

		}

	}
	
	/*
	 * This Method is to Click right Arrow Symbol of Line Item
	 */
	public void clickonRightArrowforLineItem() throws Throwable {
		click(RIGHT_ARROW, "> Symbol");

	}

	/*
	 * 
	 * @param ZPFX
	 * @param ZPOO
	 * @throws Throwable
	 */
	public void VerifyZPFXShouldbeEqualToZPOO(String ZPFX, String ZPOO) throws Throwable {
		if ((ZPOO == ZPFX)) {
			reporter.SuccessReport("Verify that ZPFX equals the ZP00", "ZPFX equals to the ZP00 value",
					"ZPOO:" + ZPOO + " equals to ZPFX" + ZPFX);
		} else {
			reporter.failureReport("Verify that ZPFX equals the ZP00", "ZPFX not equals to the ZP00 value",
					"ZPOO:" + ZPOO + " not equals to ZPFX" + ZPFX, driver);
		}
	}

	/**
	 * 
	 * @param YPOO
	 * @param ZPOO
	 * @throws Throwable
	 */
	public void VerifyZPFXShouldbeEqualToYPOO(String ZPFX, String YPOO) throws Throwable {
		if ((YPOO == ZPFX)) {
			reporter.SuccessReport("Verify that ZPFX equals the YP00", "ZPFX equals to the ZP00 value",
					"ZPOO:" + YPOO + " equals to ZPFX" + ZPFX);
		} else {
			reporter.failureReport("Verify that ZPFX equals the YP00", "ZPFX not equals to the ZP00 value",
					"ZPOO:" + YPOO + " not equals to ZPFX" + ZPFX, driver);
		}
	}


	/**
	 * 
	 * @param ZPFX
	 * @param ZPOO
	 * @throws Throwable
	 */
	public void VerifyZPFXShouldbeEqualToZP00(String ZPFX, String ZP00) throws Throwable {
		if ((ZP00 == ZPFX)) {
			reporter.SuccessReport("Verify that ZPFX equals the ZP00", "ZPFX equals to the ZP00 value",
					"ZPOO:" + ZP00 + " equals to ZPFX" + ZPFX);
		} else {
			reporter.failureReport("Verify that ZPFX equals the ZP00", "ZPFX not equals to the ZP00 value",
					"ZPOO:" + ZP00 + " not equals to ZPFX" + ZPFX, driver);
		}
	}

	/**
	 * 
	 * @param YPOO
	 * @param ZPOO
	 * @throws Throwable
	 */
	public void VerifyZPFXShouldbeEqualToYP00(String ZPFX, String YPOO) throws Throwable {
		if ((YPOO == ZPFX)) {
			reporter.SuccessReport("Verify that ZPFX equals the YP00", "ZPFX equals to the ZP00 value",
					"ZPOO:" + YPOO + " equals to ZPFX" + ZPFX);
		} else {
			reporter.failureReport("Verify that ZPFX equals the YP00", "ZPFX not equals to the ZP00 value",
					"ZPOO:" + YPOO + " not equals to ZPFX" + ZPFX, driver);
		}
	}
	
		/**
		 * 
		 * @param ZPFX
		 * @param ZPOO
		 * @throws Throwable
		 */
		 public void VerifyZPFXShouldbeEqualToZP00(float ZPFX,float ZP00) throws Throwable {
			 if((ZP00==ZPFX)) {
				 reporter.SuccessReport("Verify that ZPFX equals the ZP00", "ZPFX equals to the ZP00 value", "ZPOO:"+ZP00+" equals to ZPFX"+ ZPFX);
				} else {
					reporter.failureReport("Verify that ZPFX equals the ZP00", "ZPFX not equals to the ZP00 value","ZPOO:"+ZP00+" not equals to ZPFX"+ZPFX,driver);
				} 
		 }
		
		 /**
			 * 
			 * @param YPOO
			 * @param ZPOO
			 * @throws Throwable
			 */
			 public void VerifyZPFXShouldbeEqualToYP00(float ZPFX,float YPOO ) throws Throwable {
				 if((YPOO==ZPFX)) {
					 reporter.SuccessReport("Verify that ZPFX equals the YP00", "ZPFX equals to the ZP00 value", "ZPOO:"+YPOO+" equals to ZPFX"+ ZPFX);
					} else {
						reporter.failureReport("Verify that ZPFX equals the YP00", "ZPFX not equals to the ZP00 value","ZPOO:"+YPOO+" not equals to ZPFX"+ZPFX,driver);
					} 
			 }
		
		/*
		 * This method is to Click on Tabs in ItemDetails PopUp
		 */	 
		
		
		/*
		 * This method is to Click on Tabs in ItemDetails PopUp
		 */	 
		
	

	/*
	 * This method is to Click on Tabs in ItemDetails PopUp
	 */
	public String getContractId() throws Throwable {
		String ContractId = getText(getContractId, "Selling Price of ContractId");
		return ContractId;
	}

	/*
	 * This method is to Click on Tabs in ItemDetails PopUp
	 */
	public String getRepCostofLineItem(String Lineitem) throws Throwable {
		String RepCost = getText(repCostofLineitem(Lineitem), "RepCost Of LineItem" + Lineitem + "");
		return RepCost;
	}

	/*
	 * This method is to Click on Given ContractId in ItemDetails PopUp
	 */
	public void clickOnContractId(String ContractId) throws Throwable {
		if (isElementPresent(contractIdItem(ContractId), "Required Contract Id")) {
			click(contractIdItem(ContractId), "Select Contract Id::" + ContractId + "");
			reporter.SuccessReport("Contract Id::", "Contract ID is Selected", "" + ContractId + "");

		} else {
			reporter.failureReport("Contract Id::", "Contract ID is Not Selected", "");
		}
	}

	public int getRequiredTypePriceInItemDetailsTab(String text) throws Throwable {
		int pricevalue = 0;
		if (isElementPresent(pricing_ValueClmn(text), "Price Value Of Type::" + text + "")) {
			String data = getText(HomePage.pricing_ValueClmn(text), "value of :" + text);
			// int pricevalue1=Integer.parseInt(pricevalue);
			pricevalue = Integer.parseInt(data);
			reporter.SuccessReport("Price::", "Price For Required Type " + text + " is: " + pricevalue + "",
					"" + pricevalue + "");
		} else {
			reporter.failureReport("Price::", "Price For Required Type is Not Visible", "");
		}
		return pricevalue;
	}

	public void verifyZPOOequalstheYDLPandZPLS(float ZPOO, float YDLS, float ZPLS) throws Throwable {
		float a = ZPOO;
		float b = YDLS;
		float c = ZPLS;
		if ((a == b + c)) {
			reporter.SuccessReport("Verify that ZPOO equals the YDLP and ZPLS value.",
					"ZP00:" + a + " equals to ZPLS plus YDLP " + (b + c), "");
		} else {
			reporter.failureReport("Verify that ZPOO equals the YDLP and ZPLS value.",
					" ZP00:" + a + " not equals to ZPLS minus YDLP " + (b + c), "", driver);
		}
	}

	
	public void verifyZPOOequalstheZPFXandYIPRPercent(float ZPOO, float ZPFX, float YIPR) throws Throwable {
		float a = ZPOO;
		float b = ZPFX;
		float c = YIPR;
		if ((a ==(( b + (b*c))*0.01))) {
			reporter.SuccessReport("Verify that ZPFX plus YIPR % equals to ZP00",
					"ZPFX plus YIPR % equals to ZP00", "");
		} else {
			reporter.failureReport("Verify that ZPFX plus YIPR % equals to ZP00",
					"ZPFX plus YIPR % not equals to ZP00 ", "", driver);
		}
	}
	
	

	public void clickOnRedCrossSymbolinLineItemsList() throws Throwable {
		if (isElementPresent(REDCROSS_SYMBOL, "CON X Symbol", true)) {
			click(REDCROSS_SYMBOL, "CON X Symbol");
		} else
			reporter.failureReport("Click on Red CON Symbol", "Element not present to click", "", driver);

	}

	public void clickOnVCTAbUpdateButton() throws Throwable {
		click(UPDATE_BTN, "Update Button");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	/*
	 * public void enterMaterialItemsInVCTab(String Value) throws Throwable { int
	 * i=3; for(i=3;i!=0;i++) { click(MATERIALID_VCTAB(i),"Material ID value");
	 * driver.findElement(MATERIALID_VCTAB(i)).sendKeys(Keys.ENTER);
	 * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	 * 
	 * 
	 * WebElement element = driver.findElement(By.xpath(
	 * "(//div[@class='ag-body-container']/div/div[@colid='material'])[i]"));
	 * 
	 * elementText=element.getText(); if(elementText.equals(text)){ PriceVal =
	 * getText(HomePage.pricing_ValueClmn(text), "Pricing value"); break;
	 * 
	 * } }
	 */

	public String getPriceValueFromLineItemTable(String Pricevalue) throws Throwable {
		isVisibleOnly(PRICEVALUE_INLINEITEMS(Pricevalue), "Price Value");
		String Price = getText(PRICEVALUE_INLINEITEMS(Pricevalue), "Price Value");
		return Price;
	}

	public void enterRepMarginPercent(String MArginNum) throws Throwable {
		isVisibleOnly(REPMARGIN_PERCENT, "Rep Margin Percent Value");
		click(REPMARGIN_PERCENT, "Rep Margin Percent Value");
		type(REPMARGIN_PERCENT, MArginNum, "Rep Margin Percent Value");
		driver.findElement(REPMARGIN_PERCENT).sendKeys(Keys.ENTER);
		

	}


	/**
	 * Method is to Click on Save As Quote
	 * 
	 * @throws Throwable
	 */
	public void clickonSaveasQuoteinReasonWarningPopUp() throws Throwable {
		click(SAVEASQUOTEBTNINWARNINGPOPUP, "Save As Quote Button");
	}

	/*
	 * public String selectProductinPricingTable(String text)throws Throwable{
	 * String data=""; //
	 * ((JavascriptExecutor)driver).executeScript("window.scrollBy(2000,0)");
	 * 
	 * List<WebElement> elem = getWebElementList(HomePage.PricingTabTable,
	 * "Pricing List"); int size = elem.size(); if(size>0){ for(int i=1;i<size;i++)
	 * { elem.get(i).getText(); if(text.equalsIgnoreCase(elem.get(i).getText())){
	 * data = driver.findElement(HomePage.pricing_ValueClmn(text)).getText(); break;
	 * } } } return data; }
	 */

	/**
	 * Method is to Send text in reason PopUp Save As Quote
	 * 
	 * @throws Throwable
	 */

	public void sendTextInReasonPopup(String Text) throws Throwable {
		driver.findElement(TEXTAREA).click();
		driver.findElement(TEXTAREA).sendKeys(Text);
	}

	
	

	

	/**
	 * Method is to Click on Next Line Item Details PopUp
	 * 
	 * @throws Throwable
	 */
	public void selectReasonForRejectioninItemdetailsPopup(String Reason) throws Throwable {
		selectByVisibleText(REASONFORREJECTIONDD, Reason, "Reason For Rejection::" + Reason + "");

	}

	/**
	 * This method is to Click on Given ContractId in ItemDetails PopUp
	 */
	public void verifyPriceOfLineItem(String LineItem, String Price) throws Throwable {
		if (isElementPresent(priceofLineitem(LineItem, Price), "Required Contract Id")) {
			reporter.SuccessReport("Price of LineItem::", "Price of LineItem::" + LineItem + " is" + Price + "",
					"" + Price + "");
		} else {
			reporter.failureReport("Price of LineItem::", "Price of Lineitem is not Visible", "");
		}
	}

	/**
	 * Method is to Type Text in Reporting Field one
	 * 
	 * @throws Throwable
	 */
	public void enterTestinReportingField1(String Reprotingfield1text) throws Throwable {
		typeText(REPORTINGFIELD1, Reprotingfield1text,
				"Enter RP1 in the second Reporting Field as::" + Reprotingfield1text + "");

	}

	/**
	 * Method is to Type Text in Reporting Field four
	 * 
	 * @throws Throwable
	 */
	public void enterTestinReportingField4(String Reprotingfield4text) throws Throwable {
		typeText(REPORTINGFIELD4, Reprotingfield4text,
				"Enter RP4 Text in the Reporting Field as::" + Reprotingfield4text + "");

	}

	/**
	 * Method is to Type Text in Reporting Field four
	 * 
	 * @throws Throwable
	 */
	public void enterTestinReportingField5(String Reprotingfield5text) throws Throwable {
		typeText(REPORTINGFIELD5, Reprotingfield5text,
				"Enter RP5 Text in the Reporting Field as::" + Reprotingfield5text + "");

	}

	/**
	 * Method is to Type Text in Reporting Field four
	 * 
	 * @throws Throwable
	 */
	public void enterTestinReportingField0(String Reprotingfield0text) throws Throwable {
		typeText(REPORTINGFIELD0, Reprotingfield0text,
				"Enter RP5 Text in the Reporting Field as::" + Reprotingfield0text + "");

	}

	/**
	 * Method is to Type Text in Reporting Field four
	 * 
	 * @throws Throwable
	 */
	public void enterTestinReportingField3(String Reprotingfield3text) throws Throwable {
		typeText(REPORTINGFIELD3, Reprotingfield3text,
				"Enter RP5 Text in the Reporting Field as::" + Reprotingfield3text + "");

	}

	public void getSpecialOrderType(String value) throws Throwable {
		String optionvalue = getSelectedDropdownOption(ddspecialordertype);
		if (optionvalue.equalsIgnoreCase(value))
			System.out.println(" Selected Special ordertype is :" + optionvalue);
		else {
			System.out.println("Special order type is not matching");
		}
	}

		
		/*
		 * Method is from SmartLogin Till SalesOrg selection
		 * 
		 * @throws Throwable
		 */
		
		public void MethodfromSmartLoginTillSalesOrgselection(String UserName,String Password,String SoldToAcct,String SalesOrg)throws Throwable{
			navigateToApplication("SMART");
			loginlib.loginIntoSmartApplication(UserName,Password);
			clickCreateQuoteButton();
			ClickOnsideViewBar();
			enterSoldToValue(SoldToAcct);
			clickOnSoldToSearchIcon();
			selectOrg(SalesOrg);//2500
		}
		
		/*
		 * Method is to Click on Next Line Item Details PopUp
		 * 
		 * @throws Throwable
		 */
		public void clickonNextLineItemArrowsymbolinPopUp() throws Throwable {
			click(NEXTLINEITEMARROWSYMBOL, "Next Line Item Right Side Arrow In Line Item Details PopUp");
		}

		 /**
			 * 
			 * @param ZPOO
			 * @param ZPML
			 * @param ZDML
			 * @throws Throwable
			 */
			 public void VerifyYP00ShouldbeEqualToZP00(float YP00,float ZP00) throws Throwable {
				 if((YP00==ZP00)) {
					 reporter.SuccessReport("Verify that YP00 equals to ZP00 value", "YP00 equals to ZP00 ", "");
					} else {
						reporter.failureReport("Verify that YP00 equals to ZP00 value.", "YP00 not equals to ZP00","",driver);
					} 
			 }
			 
			
			
	public String getMaterialsOrderrevenue() throws Throwable {
		String getMaterialsOrderrevenue1 = getText(getMaterialsOrderrevenue, "MaterialsOrderrevenue");
		return getMaterialsOrderrevenue1;

	}

	public String getTaxOrderrevenue() throws Throwable {
		String getTaxOrderrevenue1 = getText(getTaxOrderrevenue, "TaxOrderrevenue");
		return getTaxOrderrevenue1;

	}

	public String getTotalOrderrevenue() throws Throwable {
		String getTotalOrderrevenue1 = getText(getTotalOrderrevenue, "TotalOrderrevenue");
		return getTotalOrderrevenue1;

	}

	public void selectPaymentTermsdropdown(String value) throws Throwable {
		selectByVisibleText(ddPaymentTerms, value, "Payments Terms dropdown");
	}

	public void selectShippingCodnition(String value) throws Throwable {
		selectByVisibleText(ddShippingConditions, value, "ShippingCodnition");
	}

	public void enterQuoteNumber(String value) throws Throwable {
		type(txtQuoteName, value, "QuoteNumber");
	}

	public void enterErateFRN(String value) throws Throwable {
		type(txtERateFRN, value, "QuoteNumber");
	}

	public void selectERateFundingYear(String value) throws Throwable {
		selectByValue(txtERateFundingYear, value, "value");
	}

	public void enterErateSLD(String value) throws Throwable {
		type(txtErateSLD, value, "ErateSLD");
	}

	public String getErateSLD() throws Throwable {
		String text = getAttributeByValue(txtErateSLD, "ErateSLD");
		return text;
	}

	public void enterErateLocation(String value) throws Throwable {
		type(txtErateLocation, value, "ErateLocation");
	}

	public void entererateEligibilityPercent(String value) throws Throwable {
		if (isElementVisible(txterateEligibilityPercent, 2, "erateEligibilityPercent"))
			type(txterateEligibilityPercent, value, "ErateLocation");
		else {
			click(btnVCtab("Pricing"), "Pricing tab");
		}
	}

	public String geterateEligibilityPercent() throws Throwable {
		String eerateeligibility = getAttributeByValue(txterateEligibilityPercent, "value");
		return eerateeligibility;
	}

	public String getErateFRNfromAttnfield() throws Throwable {
		String eerateFRN = getAttributeByValue(txtErateFRNvaluefronAttnfield, "value");
		return eerateFRN;
	}

	public String getErateFinancialYear() throws Throwable {
		String eeratefinancialyear = getAttributeByValue(ddErateFundingYear, "value");
		return eeratefinancialyear;
	}

	public String getErateLocation() throws Throwable {
		String eeratefinancialyear = getAttributeByValue(txtErateLocation, "value");
		return eeratefinancialyear;
	}

	public String getSelectedSpecialOrderType() throws Throwable {

		String SpecialOrderType = getText(ddspecialordertype, "SpecialOrderType");
		return SpecialOrderType;
	}

	public String getSelectedErateFY() throws Throwable {

		String ErateFY = getText(ddErateFundingYear, "ErateFundingYear");
		return ErateFY;
	}

	public static void SwipeUpapplication() {

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -350)", "");

	}

	public static void Swipedownapplication() {

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, +420)", "");

	}

	public String getOrderNumber() throws Throwable {
		String eeratefinancialyear = getAttributeByValue(txtErateLocation, "value");
		return eeratefinancialyear;
	}

	/**
	 * 
	 * @param ZPOO
	 * @param Z0RC
	 * @param YMSM
	 * @throws Throwable
	 */
	public void VerifyZ0RCPlusYMSMShouldbeEqualToZP00(float Z0RC, float YMSM, float ZP00) throws Throwable {
		float ZpmlminusZdml = Z0RC + YMSM;
		if ((ZP00 == ZpmlminusZdml)) {
			reporter.SuccessReport("Verify that Z0RC Plus YMSM equals the ZP00 rate",
					"Z0RC Plus YMSM rate::" + ZpmlminusZdml + " equals the ZP00 :" + ZP00 + "value ", "");
		} else {
			reporter.failureReport("Verify that Z0RC Plus YMSM equals the ZP00 rate",
					"Z0RC Plus YMSM not equalsl to the ZP00 rate", "", driver);
		}
	}

	/**
	 * 
	 * @param YPOO
	 * @param Z0RC
	 * @param YMSM
	 * @throws Throwable
	 */
	public void VerifyZ0RCPlusYMSMShouldbeEqualToYP00(float Z0RC, float YMSM, float YP00) throws Throwable {
		float ZpmlminusZdml = Z0RC + YMSM;
		if ((YP00 == ZpmlminusZdml)) {
			reporter.SuccessReport("Verify that Z0RC Plus YMSM equals the YP00 rate",
					"Z0RC Plus YMSM rate::" + ZpmlminusZdml + " equals the YP00 :" + YP00 + "value ", "");
		} else {
			reporter.failureReport("Verify that Z0RC Plus YMSM equals the YP00 rate",
					"Z0RC Plus YMSM not equalsl to the YP00 rate", "", driver);
		}
	}

	/**
	 * 
	 * @param YPOO
	 * @param ZPML
	 * @param ZDML
	 * @throws Throwable
	 */
	public void VerifyZPMLMinusZDMLShouldbeEqualToYP00(float ZPML, float ZDML, float YP00) throws Throwable {
		float ZpmlminusZdml = ZPML - ZDML;
		if ((YP00 == ZpmlminusZdml)) {
			reporter.SuccessReport("Verify that ZPML minus  ZDML equals the YP00 value",
					"ZPML Minus ZDML::" + ZpmlminusZdml + " equals the YP00 :" + YP00 + "value ", "");
		} else {
			reporter.failureReport("Verify that ZPML Minus ZDML equals the YP00 value",
					"ZPML Minus ZDML Not equals the YP00 value", "", driver);
		}
	}

	/**
	 * 
	 * @param YPOO
	 * @param ZPML
	 * @param ZDML
	 * @throws Throwable
	 */
	public void VerifyZPMLMinusZDMLShouldbeEqualToZP00(float ZPML, float ZDML, float YP00) throws Throwable {
		float ZpmlminusZdml = ZPML - ZDML;
		if ((YP00 == ZpmlminusZdml)) {
			reporter.SuccessReport("Verify that ZPML minus  ZDML equals the YP00 value",
					"ZPML Minus ZDML::" + ZpmlminusZdml + " equals the YP00 :" + YP00 + "value ", "");
		} else {
			reporter.failureReport("Verify that ZPML Minus ZDML equals the YP00 value",
					"ZPML Minus ZDML Not equals the YP00 value", "", driver);
		}
	}
	/**
	 * 
	 * @param ZPOO
	 * @param ZPML
	 * @param ZDML
	 * @throws Throwable
	 */
	public void VerifyZPLSMinusYDLPShouldbeEqualToZP00(float ZPLS, float YDLP, float ZP00) throws Throwable {
		float Zplsminusydlp = ZPLS - YDLP;
		if ((ZP00 == Zplsminusydlp)) {
			reporter.SuccessReport("Verify that ZPLS minus YDLP equals the ZP00 value",
					"ZPLS Minus YDLP::" + Zplsminusydlp + " equals the ZP00 :" + ZP00 + "value ", "");
		} else {
			reporter.failureReport("Verify that ZPLS Minus YDLP equals the ZP00 value",
					"ZPLS Minus YDLP Not equals the ZP00 value", "", driver);
		}
	}

	/**
	 * 
	 * @param YPOO
	 * @param ZPML
	 * @param ZDML
	 * @throws Throwable
	 */
	public void VerifyZPLSMinusYDLPShouldbeEqualToYP00(float ZPLS, float YDLP, float YP00) throws Throwable {
		float Zplsminusydlp = ZPLS - YDLP;
		if ((YP00 == Zplsminusydlp)) {
			reporter.SuccessReport("Verify that ZPLS minus YDLP equals the YP00 value",
					"ZPLS Minus YDLP::" + Zplsminusydlp + " equals the ZP00 :" + YP00 + "value ", "");
		} else {
			reporter.failureReport("Verify that ZPLS Minus YDLP equals the YP00 value",
					"ZPLS Minus YDLP Not equals the YP00 value", "", driver);
		}
	}

	/**
	 * 
	 * @param ZPOO
	 * @param ContractPrice
	 * @throws Throwable
	 */
	public void VerifyContractPriceShouldbeEqualToZPOO(float ZP00, String contractid) throws Throwable {
		String ContractPrice = getText(getcontractsellingPrice(contractid), "");
		if (ContractPrice.equals(ZP00)) {
			reporter.SuccessReport("Verify that ZP00 should equal to contractid",
					"ZP00 " + ZP00 + " equals to contractid" + ContractPrice + "", "");
		} else {
			reporter.failureReport("Verify that ZP00 should equal to contractid", "ZP00 not equals to contractid ", "",
					driver);
		}
	}

	/**
	 * 
	 * @param ZPOO
	 * @param ContractPrice
	 * @throws Throwable
	 */
	public void VerifyContractPriceShouldbeEqualToZPLS(float ZPLS,String contractid) throws Throwable {
		String ContractPrice = getText(getcontractsellingPrice(contractid), "");
		if (ContractPrice.equals(ZPLS)) {
			reporter.SuccessReport("Verify that ZPLS should equal to contractid",
					"ZPLS " + ZPLS + " equals to contractid" + ContractPrice + "", "");
		} else {
			reporter.failureReport("Verify that ZPLS should equal to contractid", "ZPLS not equals to contractid ", "",
					driver);
		}
	}

	/**
	 * 
	 * @param YPOO
	 * @param ZPML
	 * @param ZDML
	 * @throws Throwable
	 */
	
	public void VerifyYP00houldbeEqualToAllLineItems(float YP001, float YP002, float YP003) throws Throwable {
		if ((YP001 == YP002)&&(YP002 == YP003)&&(YP003 == YP001)) {

			reporter.SuccessReport("Verify the YP00 value remains same", "YP00  price remains same", "");
		} else {
			reporter.failureReport("Verify the YP00 value remains same", "YP00  price not remains same", "", driver);
		}
	}

	/**
	 * 
	 * @param YPOO
	 * @param ZPML
	 * @param ZDML
	 * @throws Throwable
	 */
	
	public void VerifyZP00houldbeEqualToAllLineItems(float ZP001, float ZP002, float ZP003) throws Throwable {
		if ((ZP001 == ZP002)&&(ZP002 == ZP003)&&(ZP003 == ZP001)) {
			reporter.SuccessReport("Verify the ZP00 value remains same", "ZP00  price remains same", "");
		} else {
			reporter.failureReport("Verify the ZP00 value remains same", "ZP00  price not remains same", "", driver);
		}
	}

	/**
	 * 
	 * @param YPOO
	 * @param ZPML
	 * @param ZDML
	 * @throws Throwable
	 */
	
	public void VerifyZPLSShouldbeEqualToAllLineItems(float ZPLS1, float ZPLS2, float ZPLS3) throws Throwable {
		if ((ZPLS1 == ZPLS2)&&(ZPLS2 == ZPLS3)&&(ZPLS3 == ZPLS1)) {
			reporter.SuccessReport("Verify the YP00 value remains same", "YP00  price remains same", "");
		} else {
			reporter.failureReport("Verify the YP00 value remains same", "YP00  price not remains same", "", driver);
		}
	}

	/**
	 * 
	 * @param YPOO
	 * @param ZPML
	 * @param ZDML
	 * @throws Throwable
	 */
	public void VerifyZTICMinusYP00ShouldbeEqualToZP00(float ZP001, float ZP002, float ZP003, float YP001,
			float YP002, float YP003, float ZITC1, float ZITC2, float ZITC3) throws Throwable {
		float zitcminuyp001 = YP001 - ZITC1;
		float zitcminuyp002 = YP002 - ZITC2;
		float zitcminuyp003 = YP003 - ZITC3;
		if ((zitcminuyp001 == ZP001) && (zitcminuyp002 == ZP002) && (zitcminuyp003 == ZP003)) {
			reporter.SuccessReport("Verify the ZP00 should be equal to YP00 - ZTIC. YP00",
					"ZP00 equals to YP00 - ZTIC ", "");
		} else {
			reporter.failureReport("Verify the ZP00 should be equal to YP00 - ZTIC. YP00",
					"ZP00 not equals to YP00 - ZTIC", "", driver);
		}
	}

	/**
	 * 
	 * @param YPOO
	 * @param Z0RC
	 * @param YMSM
	 * @throws Throwable
	 */
	public void VerifyYCGEPlusZMOVShouldbeEqualToRepCost(float YCGE, float ZMOV, String RepCost, String Lineitem)
			throws Throwable {
		float ycge = YCGE;
		float zmov = ZMOV;
		float repcost = Integer.parseInt(RepCost);
		float ycgepluszmov = ycge + zmov;
		if ((repcost == ycgepluszmov)) {
			reporter.SuccessReport("Verify the Rep Cost total is equal to YCGE + ZMOV for Lineitem" + Lineitem + "",
					"YCGE + ZMOV::" + ycgepluszmov + " equals the Rep Cost :" + repcost + "value ", "");
		} else {
			reporter.failureReport("Verify the Rep Cost total is equal to YCGE + ZMOV",
					"Rep Cost total is not equal to YCGE + ZMOV", "", driver);
		}
	}

	/**
	 * This Method is to Select Special Order Type
	 */
	public void SelectSpecialOrderType(String text) throws Throwable {
		selectByVisibleText(ddspecialordertype, text, "special-order-type");
	}

	/**
	 * Method is to enter Quote name in AdvancedHeader
	 * 
	 * @throws Throwable
	 */
	public void enterQuotenameinAdvancedHeader(String Text) throws Throwable {
		
		typeText(QUOTENAME_FIELD_ADVANCEDHEADER, Text, "Quotename in Advanced Header:: "+ Text + "");
	}

	/**
	 * Method is to enter ErateLocation in partners tab in AdvancedHeader
	 * 
	 * @throws Throwable
	 */
	public void enterErateLocationinpartnerstabAdvancedHeader(String text) throws Throwable {
		
		typeText(ERATESITE_LOCATIN_INPARTNERSTAB,text,"Erate Location::"+text+"");

	}
	/**
	 * Method is to Verify Special Order type is disabled
	 * 
	 * @throws Throwable
	 */
	public void verifySpecialOrdertypeisdisabled() throws Throwable {
		if (isElementPresent(ddspecialordertypeDisabled, "Special Order Type")) {
			reporter.SuccessReport("Confirm that Special Order Type didn't disappear", "Exists as Expected ", "");

		} else {
			reporter.failureReport("Confirm that Special Order Type didn't disappear", "does not exist as expected", "",
					driver);
		}
	}

	/**
	 * Method is to Verify Special Order type is disabled
	 * 
	 * @throws Throwable
	 */
	public void verifyQuotenamewithGivenTextisPresent(String Text) throws Throwable {
		if (isElementPresent(quotenamefieldwithgivenvalue(Text), "Special Order Type")) {
			reporter.SuccessReport("Quote Name didn't disappear", "Exists as Expected", "" + Text + "");

		} else {
			reporter.failureReport("Quote Name disappeared", "does not exist as expected", "", driver);
		}
	}

	/**
	 * Method is to Verify E-rate Location Exists As Expected
	 * 
	 * @throws Throwable
	 */
	public void verifyErateLocationswithGivenTextisPresent(String Text) throws Throwable {
		if (isElementPresent(e_ratelocationfieldwithgivenvalue(Text), "Special Order Type")) {
			reporter.SuccessReport("E-Rate Site Location didn't disappear", "Exists as Expected", "" + Text + "");

		} else {
			reporter.failureReport("E-Rate Site Location disappeared::", "does not exist as expected", "", driver);
		}
	}

	/**
	 * Method is to verify search results of Enrolment Id
	 * 
	 * @throws Throwable
	 */
	public void clickonEnrolmentIdatProgramsTab(String enrolmentid) throws Throwable {
		if (isElementPresent(enrolmentidatProgramstabAvancedheader(enrolmentid), "Enrolment Id")) {
			rightClick(enrolmentidatProgramstabAvancedheader(enrolmentid), "Enrolment Id::" + enrolmentid + "");
			isElementClickable(PRODUCTSEARCHCONTRACTID, 2, "Product Search Of an Item");
			reporter.SuccessReport("Serach Result for Enrolment Id::",
					"" + enrolmentid + " is Displayed and product search is selected", "");
		} else {
			reporter.failureReport("Serach Result for Enrolment Id::", "Search Results are not Displayed", "");
		}
	}

	/**
	 * Method is to Enrollment Drop down in Product Search
	 * 
	 * @throws Throwable
	 */
	public void enrollmentDropDown(String Option) throws Throwable {
		selectByVisibleText(ENROLLMENTDROPDOWN, Option, "Enrolment Option:" + Option + "");

	}

	/**
	 * Method is to verify Vc icon and select
	 * 
	 * @throws Throwable
	 */
	public void verifyandClickonVCofLineitem(String lineItem) throws Throwable {
		if (isElementPresent(vcIconforLineitem(lineItem), "Vc Icon of lineitem:" + lineItem + "")) {
			isElementClickable(vcIconforLineitem(lineItem), 2,
					"Vc Icon of lineitem: " + lineItem + " is visible and selected");
			reporter.SuccessReport("Vc Icon of lineitem:" + lineItem + "::", "VC exists and clicked", "");
		} else {
			reporter.failureReport("Vc Icon of lineitem:" + lineItem + "::", "VC icon does not exist", "");
		}
	}

	/**
	 * Method is to LabFee Drop down in Item Details Tab
	 * 
	 * @throws Throwable
	 */
	public void labFeeDropDown(String Option) throws Throwable {
		selectByVisibleText(LABFEEDD, Option, "LabFee::" + Option + "");
	}

	/**
	 * Method is to Add Line Item
	 * 
	 * @throws Throwable
	 */
	public void AddmaterailAtVCIconInItemDetailsPopUp(String Material) throws Throwable {
		isElementClickable(MATERIALATVCINITEMDETAILS, 3, "VC Material Line item and Adding Material::" + Material + "");
		driver.findElement(addMaterialAtVcIcon).sendKeys(Material);
		driver.findElement(addMaterialAtVcIcon).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

	public String getFirstElementPriceValueFromLineItem() throws Throwable {
		isVisibleOnly(FIRSTELM_PRICEVALUE_INLINEITEMS, "Price Value for Line item 10");
		String Price = getText(FIRSTELM_PRICEVALUE_INLINEITEMS, "Price Value of line item 10");
		return Price;

	}

	public List getPriceValueFromPricingTab(String idValue, String expValue, int val) throws Throwable {
		WebElement yourElement = null;
		String cellPrice = "";
		String inputValue = idValue;
		String typeCodeValue[] = inputValue.split("#");
		String expecetedTypeCode[] = expValue.split("#");
		List<Float> all_elements_pricevalue = new ArrayList<>();
		try {
			label: for (int j = 0; j < typeCodeValue.length; j++) {
				yourElement = driver.findElement(getPricingID(typeCodeValue[j]));
				isElementClickable(getPricingID(typeCodeValue[j]), 1, "Type Value");
				String value = getText(getPricingID(typeCodeValue[j]), "Type Value");
				if (value == null) {

					for (int i = 0; i <= 2; i++) {
						yourElement.sendKeys(Keys.ARROW_DOWN);
					}
				}
				for (int i = 0; i < expecetedTypeCode.length; i++) {

					if (expecetedTypeCode[i].contains(value)) {

						cellPrice = getText(getPricingValues(typeCodeValue[j]), "Price Value");
						reporter.SuccessReport("Price Value for Selected Type",
								"the Price Value for Selected " + typeCodeValue[j] + " is " + cellPrice,
								expecetedTypeCode[i]);

						all_elements_pricevalue.add(Float.valueOf(cellPrice));
						int size = all_elements_pricevalue.size();
						if (size == val) {
							break label;
						}
					}
				}
				yourElement.sendKeys(Keys.ARROW_DOWN);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		/*
		 * finally{ for(int i=0;i<=2;i++){ yourElement.sendKeys(Keys.ARROW_DOWN); }
		 */

		return all_elements_pricevalue;
	}

	// CQT02_FreightESDScript
	

	

	public String getRateValueinPricingTable(String priceValue) throws Throwable {
		isElementVisible(getPricingRateValue(priceValue), 2,"Rate Value for the selected type");
		String Rate = getText(getPricingRateValue(priceValue), "Rate Value for the selected type").replace("%"," ").trim();
		return Rate;
		
	}
	
	public void selectReasonforRejectionDD(String ddValue) throws Throwable {
		if(isElementVisible(REASONBFORREJCTN_DD, 2, "Reason for rejection dropdown value.")){
			reporter.SuccessReport("Verify the  Reason for Rejection for Line Item ", "Reason for Rejection exists", "");
		selectByVisibleText(REASONBFORREJCTN_DD, " ", "Blank option is selected from dropdown value");
		clickonRightArrowforLineItem();
		} else {
			reporter.failureReport("Verify the  Reason for Rejection for Line Item ", "Reason for Rejection does not exist/not matching with expected values", "",driver);
		}	
		}
	
	public void verifyZPFXValue(float ZPFX) throws Throwable {
		float a = ZPFX;
		if ((a == 99999.99)) {
			reporter.SuccessReport("Verify the  ZPFX value should be $99,999.99",
					"ZPFX equals to $99,999.99" , "");
		} else {
			reporter.failureReport("Verify the  ZPFX value should be $99,999.99",
					" ZPFX not equals to $99,999.99", "", driver);
		}
	}
	

	public void enterRepMarginPercentinLineItem() throws Throwable {
		
		List<WebElement> marginList = driver.findElements(HomePage.REP_MARGIN_COUNT);
		for(int i=1;i<marginList.size()-1;i++){
		isVisibleOnly(REPMARGIN_PERCENTAGE(i), "Rep Margin Percent Value");
		click(REPMARGIN_PERCENTAGE(i), "Rep Margin Percent Value");
		type(REPMARGIN_PERCENTAGE(i), "4", "Rep Margin Percent Value");
		driver.findElement(REPMARGIN_PERCENTAGE(i)).sendKeys(Keys.ENTER);
		}

	}
	
	//PRICEVALUE_INLINEITEMS
	public void comparePriceValuesInLineitems(String value1,String value2) throws Throwable {
		String Price1= getText(PRICEVALUE_INLINEITEMS(value1),"Lineitem 10 price value");
		String Price2= getText(PRICEVALUE_INLINEITEMS(value2),"Lineitem 20 price value");
		if(Price1.equals(Price2)){
			reporter.SuccessReport("Line 10 and Line 20 should display the exact same price.",
					"Line 10 and 20 Prices are same" , "Line 10 price: "+Price1+" and  Line 20 price: "+Price2);
		} else {
			reporter.failureReport("Line 10 and Line 20 should display the exact same price.",
					" Line 10 and 20 Prices are not same", "Line 10 price: "+Price1+" and  Line 20 price: "+Price2, driver);
		}
			
		}
	
	public String VerifyReportingField6(String contractid) throws Throwable {
		String Reportingvalue[] = driver.findElement(REPORTINGFIELD5).getAttribute("value").split("##");
		if (!contractid.equals(Reportingvalue[0])) {
			reporter.SuccessReport("IPS Report Field 6 value should not be "+contractid+" on Contracts Tab",
					"Exists as Expected", "IPS Report 6# : "+Reportingvalue[0]);
		} else {
			reporter.failureReport("IPS Report Field 6 value should not be "+contractid+" on Contracts Tab", "Does not exist as expected", "IPS Report 6# : "+Reportingvalue[0]);
		}
		return Reportingvalue[0];
	}
	
	public String VerifyReportingField6SellPriceValue() throws Throwable {
		String Reportingvalue[] = driver.findElement(REPORTINGFIELD5).getAttribute("value").split("##");
		if (!Reportingvalue[1].equals(null)) {
			reporter.SuccessReport("Select "+Reportingvalue[0]+"  Contract"," Contract found", "Contract Id # "+Reportingvalue[0]);
		} else {
			reporter.failureReport("Select "+Reportingvalue[0]+"  Contract"," Contract was not found", "Contract Id # "+Reportingvalue[0]);
		}
		return Reportingvalue[1];
	}
	

	//CQT02_FreightESDScript

	public void selectEnrollment() throws Throwable{
		click(HomePage.enrollmentDropdown,"Enrollment dropdown");
		click(HomePage.enrollmentDropdownValue,"4100012345 - Insight Automated KBP Account 2400 - MICROSOFT MPSA value selected");
	}

	
	public void selectBlankEnrollment() throws Throwable{
		click(HomePage.enrollmentDropdown,"Enrollment dropdown");
		click(HomePage.enrollmentBlankOption,"Clear enrollment option ");
	}
	
	public void verifyQuoteProgramIsBlank() throws Throwable {
		if (findWebElement(drpDwnBlankOption, "Blank Option").isSelected()) {
			reporter.SuccessReport("Quote Program Value", "Quote program Value is blank", "");
		} else {
			reporter.failureReport("Quote Program Value", "Quote program Value is not blank", "", driver);
		}
	}

	public void carrierOptionValidate() throws Throwable {
		String carrierDropdownText = getText(HomePage.CARRIER_ADVANCEDHEADER, "Carrier dropdown text");
		String carrierDropdownOption = "CR_ESD";

		if ((carrierDropdownText).contains(carrierDropdownOption)) {
			
			reporter.SuccessReport("Carrier dropdown value", "Carrier dropdown value is CR_ESD", "");
		} else {
			reporter.failureReport("Carrier dropdown value", "Carrier dropdown value is not CR_ESD", "", driver);
		}
	}


	public void shippingConditionsOptionValidate() throws Throwable {

		String shippingConditionsText = getText(HomePage.SHIPPINGCONDITIONSDRPDOWN_ANDVANCEDHEADER,
				"Shipping conditions text");

		String shippingConditionOption = getText(HomePage.shippingConditinOption, "Shipping condition option");
		//String shippingConditionOption = "80 Electronic Delivery";
		if ((shippingConditionsText).equalsIgnoreCase(shippingConditionOption)) {
			reporter.SuccessReport("shipping Condition value", "shipping Condition value is 80 Electronic Delivery",
					"");
		} else {
			reporter.failureReport("shipping Condition value", "shipping Condition value is not 80 Electronic Delivery",
					"", driver);
		}
	}


	public String getTaavalue() throws Throwable {
		String taa = getAttributeValue(txtTAAvalue, "value");
		return taa;
	}

	
	

public void clickOnRedCrrossSymbolinLineItemsList() throws Throwable {
	if (isElementVisible(RED_CONTRACT_SYMBOL,2, "CON Red X Symbol")) {
		reporter.SuccessReport("Click VC ", "VC exists and clicked", "");
		click(RED_CONTRACT_SYMBOL, "CON X Symbol");
	} else
		reporter.failureReport("Click VC", "VC icon does not exist", "", driver);

}

public void clickOnSourceNamePlusIcon(String SourceName) throws Throwable {
	if (isElementVisible(sourceNamePlusIcon(SourceName),2, SourceName+"source icon")) {
		reporter.SuccessReport("To Expand the options under 'Channel' by clicking on the '+' sign", "'+' sign exists and clicked", "");
		click(sourceNamePlusIcon(SourceName), SourceName+"source icon");
	} else
		reporter.failureReport("To Expand the options under 'Channel' by clicking on the '+' sign", "'+' sign does not exists", "", driver);

}
	
public void checkSupplierNameContainsCanada(int j) throws Throwable {	
	String a= "CANADA";
	for(i=3;i<=6;i++){
	if (isElementPresent(supplierNames(j),j+"Supplier name")) {	
			String b = getText(supplierNames(i),"Supplier name");
			if(b.contains(a)){
				reporter.SuccessReport("Verify that the suppliers have a 'CA' before their name and 'Canada' in their name", "'CA' and 'Canada' exists in their name","");
			}		
	} else
		reporter.failureReport("Verify that the suppliers have a 'CA' before their name and 'Canada' in their name","'CA' and 'Canada' does not exist in their name", "", driver);

	}
}

public void selectSourceDDValue(String DDValue) throws Throwable {
	if (isElementVisible(SOURCE_DD,2,"source dropdown")) {
		click(SOURCE_DD_VALUE(DDValue),"Drop down selected");
	}
}

public void selectLabFeeAndNotes(String DDValue) throws Throwable{
	selectByVisibleText(LAB_FEE_DD, DDValue, "LAB fee");
	type(LAB_NOTES,"Lab Test Notes","LAB notes");
}

	public List<String> verifyCurrencyTypes(String inputValue) throws Throwable {
		WebElement Element;

		String typeValue[] = inputValue.split("#");
		List<String> all_elements_Currencyvalues = new ArrayList<>();
		for (int j = 0; j < typeValue.length; j++) {
			Element = driver.findElement(costTypes(j));
			String currencyType = Element.getText().replace("CAD", " ").trim();
			all_elements_Currencyvalues.add((currencyType));
		}
		return all_elements_Currencyvalues;
	}
		


public String verifyDownloadedTextFile() throws Throwable {
	String data ="";
	File dir = new File(System.getenv("USERPROFILE")+"\\Downloads");
	  File[] dirContents = dir.listFiles();
	label:
	  for (int i = 0; i < dirContents.length; i++) {
		  String filename = dirContents[i].getName();
		  FileReader FR = new FileReader(dir+"\\"+filename);
		  BufferedReader BR = new BufferedReader(FR);
		  String Content = "";
		  while((Content = BR.readLine())!= null){
			  System.out.println("File downloaded successfully");
			 data = Content;
			 //delete a file
				/*
				 * File file = new File(dir+"\\"+filename); file.delete() ;
				 */
				break label;

			}

		}
		return data;
	}

	public void totalweightwithaddedquantity(float dispalayedweight1, float dispalayedweight2, String quantity)
			throws Throwable {
		int value = Integer.parseInt(quantity);
		float dispalayedweight3 = dispalayedweight1 * value;
		if (dispalayedweight2 == dispalayedweight3) {
			reporter.SuccessReport("Totalweight::",
					"Totoal weight after adding Heavy Weight::" + quantity + " is equal to displayed total weight",
					"" + dispalayedweight3 + "");
		} else {
			reporter.failureReport("Totalweight::", "Totoal weight after adding Heavy Weight is not not matching", "",
					driver);
		}

	}

	/**
=======
	
    public void totalweightwithaddedquantity(float dispalayedweight1,float dispalayedweight2,String quantity)throws Throwable{
    	int value=Integer.parseInt(quantity);
    	float dispalayedweight3=dispalayedweight1*value;
    	if(dispalayedweight2==dispalayedweight3){
    		 reporter.SuccessReport("Totalweight::","Totoal weight after adding Heavy Weight::"+quantity+" is equal to displayed total weight",""+dispalayedweight3+"");
    	}
    	else{
    		reporter.failureReport("Totalweight::", "Totoal weight after adding Heavy Weight is not not matching", "", driver);
    	}
	
}
    /*
>>>>>>> b41fcb294868b016ba44292849e2b3a21a2c8eea
	 * This method is to verify Reporting Field six in ItemDetails PopUp
	 */
	public void verifyReportingFieldsix() throws Throwable {
		if (isElementPresent(REPORTINGFIELD6, "Reporting Field-06")) {
			reporter.SuccessReport("IPS Report Field 6::", "IPS Report Field 6 value Does not exist as expected", "");
		} else {
			reporter.failureReport("IPS Report Field 6::", "IPS Report Field 6 value exists", "", driver);
		}
	}


	
	public void verifyReportingFieldsixpriceandZP00(float ZP00, float reportingfieldprice) throws Throwable {
		String rpPrice[] = driver.findElement(REPORTINGFIELD6).getAttribute("value").split("##");
		float RPvalue = Integer.parseInt(rpPrice[1]);
		if (RPvalue == ZP00) {
			reporter.SuccessReport("IPS Report Field 6::", "IPS Report Field 6 value Does not exist as expected", "");
		} else {
			reporter.failureReport("IPS Report Field 6::", "IPS Report Field 6 value exists", "", driver);
		}
	}


	

	/*
	 * Method is to get Order Number
	 * 
	 * @throws Throwable
	 */
	public void verifyConverToOrderNotVisible() throws Throwable {
	if (isElementPresent(btnConvertToOrder, "Order Number")) {
			String  OrderNum[]=driver.findElement(SUBJECTTOGETORDERNUM).getAttribute("Value").split("-");
			reporter.SuccessReport("Order Number::", "Order Number Exists and Text:: " + OrderNum[0]+ "", "");
		} else {
			reporter.failureReport("Order Number::", "Order Number is not Displayed", "");
		}
	}
	/*
	 * This method is to verify Enrollment DropDown
	 */	 
	public void verifyEnrollmentDropDown() throws Throwable {
		if(isElementPresent(ENROLLMENTDROPDOWN,"Enrollment DropDown")){
		reporter.SuccessReport("Verify program contract data and Enrollment Number in popup menu", "Enrollment exists", "");
	} else {
		reporter.failureReport("Verify program contract data and Enrollment Number in popup menu", "Enrollment was not found", "",driver);
	}
}
	
	/*
	 * This method is to Select Option From Pools DropDown
	 */	 
	public void selectOptionFromPoolsDropDown(String Data) throws Throwable {
		typeText(POOLS_DROPDOWNINPUT,""+Data+"","Pools::");
		driver.findElement(POOLS_DROPDOWNINPUT).sendKeys(Keys.ENTER);
}
	
	/*
	 * This method is to verify Quote Program DropDown
	 */	 
	public void verifyQuoteProgramDropDown() throws Throwable {
		if(isElementPresent(drpDwnQuoateProgram,"Quote Program Dropdown")){
		reporter.SuccessReport("Verify Quote Program Dropdown in popup menu", "Quote Program Dropdown exists", "");
	} else {
		reporter.failureReport("Verify Quote Program Dropdown in popup menu", "Quote Program Dropdown was not found", "",driver);
	}
}
	
	/*
	 * This method is to verify Quote Program DropDown value
	 */	 
	public void verifyQuoteProgramDropDownValue(String value) throws Throwable {
		if(value==" "){
		reporter.SuccessReport("Verify Quote Program Dropdown in popup menu", "Programs Dropdown Equals to Blank", "");
	} else {
		reporter.failureReport("Verify Quote Program Dropdown in popup menu", "Programs Dropdown Not Equals to Blank", "",driver);
	}
}
	/*
	 * This method is to Click on VIP checkbox in options 
	 */	
	public void clickOnVIPCheckBox() throws Throwable {
		if (isVisibleOnly(HomePage.chkBoxVipCommercial, "VIP Commercial")) {
			click(HomePage.chkBoxVipCommercial, "VIP Commercial::ON");
		}
	}

	/*
	 * This method is to get client cost in LineItems Grid
	 */	 
	public String getClientCostofLineItem(String Lineitem) throws Throwable {
    String clientCost= getText(clientCostofLineitem(Lineitem),"ClientCost Of LineItem"+Lineitem+"");
    return clientCost;
	}
	
	/*
	 * This method is to get client cost in LineItems Grid
	 */	 
	public String getPriceofLineItem(String Lineitem) throws Throwable {
    String price= getText(getPriceofLineitem(Lineitem),"Price Of LineItem"+Lineitem+"");
    return price;
	}
	/*
	 * This method is to verify repcost clientcost price of lineitem are not zero
	 */
	public void verifyRepCostClientCostPriceoflineitem(String RepCost,String ClientCost,String Price)throws Throwable {
		float repcost=Integer.parseInt(RepCost);
		float clientcost=Integer.parseInt(ClientCost);
		float price=Integer.parseInt(Price);
		if((repcost!=0)&&(clientcost!=0)&(price!=0)){
			reporter.SuccessReport("Validate Price, Rep and Client Cost", "Price::"+price+", Rep::"+repcost+" and Client Cost ::"+clientcost+" validated", "");
		} else {
			reporter.failureReport("Validate Price, Rep and Client Cost", "One of the/or All the Price, Rep and Client Cost values of lineitem are 0.0", "", driver);
		}
	}
	/*
	 * This method is to verify there is no convert to order button
	 */
	public void verifyConvertToOrderBtnIsNotAvailable()throws Throwable {
		if(isElementPresent(btnConvertToOrder,"Convert to Order")){
			reporter.failureReport("Validate the Convert To Order button", "Convert Order button does exists NOT as expected", "", driver);
		}
		else{
			reporter.SuccessReport("Validate the Convert To Order button", "Convert Order button does not exist as expected", "");
		
		}
	}

	public void LoginToApplicationAndSearchForSoldToAct(String Username,String Password,String Soldtoact)throws Throwable {

		navigateToApplication("SMART");
		loginlib.loginIntoSmartApplication(Username, Password);
		clickOnCreateQuoteLink();
		ClickOnsideViewBar();
		enterSoldToValue(Soldtoact);
		clickOnSoldToSearchIcon();
		loadingSymbol();
	}


	public static void VerifyEratevalue(float price, int partialtextfield) throws Throwable {
		
		  if(price!=0)
			  reporter.SuccessReport("Price value:: ", "Price exists as expected ","");
		  else
			  reporter.failureReport("Price value:: ", "Price not exists as expected ","");
           if(partialtextfield!=0)
        	   reporter.SuccessReport("partialtextfield value:: ", "partialtextfield exists as expected ","");
           else
        	   reporter.failureReport("partialtextfield value:: ", "partialtextfield not exists as expected ","");
		 

	}
	public static void VerifyLineItems(String lineitem)throws Throwable{
        
        if(lineitem!="000010") {
			ClickonArrowNextToLineitem();
		}
        String lineitemvalue0=getLineItemText();
			if(lineitemvalue0.equals(lineitem))
			reporter.SuccessReport("LineItem "+lineitem+"::","LineItem "+lineitem+" is displayed","");
			else
				reporter.failureReport("LineItem "+lineitem+"::","LineItem "+lineitem+"  is not displayed","",driver);
			
		/*
		 * ClickonArrowNextToLineitem(); String lineitemvalue=getLineItemText();
		 * if(lineitemvalue.equals("000020"))
		 * reporter.SuccessReport("LineItem 000020::","LineItem 000020 is displayed","")
		 * ; else
		 * reporter.failureReport("LineItem 000020::","LineItem 000020 is not displayed"
		 * ,"",driver); ClickonArrowNextToLineitem(); String
		 * lineitemvalue1=getLineItemText(); if(lineitemvalue1.equals("000030"))
		 * reporter.SuccessReport("LineItem 000030::","LineItem 000030 is displayed","")
		 * ; else
		 * reporter.failureReport("LineItem 000030::","LineItem 000030 is not displayed"
		 * ,"",driver);
		 */
			ClickonArrowNextToLineitem();
	}


	public String getRepCostValueFromLineItemTable(String Pricevalue) throws Throwable {
		isVisibleOnly(REPCOST_INLINEITEMS(Pricevalue), "Price Value");
		String Price = getText(REPCOST_INLINEITEMS(Pricevalue), "Price Value");
		return Price;
	}

	public void clickOnVCSymbolinLineItemsList(String LineItem) throws Throwable {
		if (isElementPresent(clickOnVCGreenSymbol(LineItem), "VC tab column Green Symbol", true)) {
			click(clickOnVCGreenSymbol(LineItem), "VC tab column Green Symbol");
		} else
			reporter.failureReport("Click on VC tab column Green Symbol", "Element not present to click", "", driver);
	}
	/*
	 * Method is to set Price in LineItems
	 * 
	 * @throws Throwable
	 */
	public void setPrice(String item, String price) throws Throwable {
		click(getPriceofLineitem(item), "Price");
		driver.findElement(TYPE_PRICE_OFANITM).sendKeys(price);
		driver.findElement(TYPE_PRICE_OFANITM).sendKeys(Keys.ENTER);
	}

	/*
	 * Method is to Verify Price in LineItems
	 * 
	 * @throws Throwable
	 */
	public void verifyPriceofLineitem(String price,String Price,String Lineitem) throws Throwable {
		float PricefromItemdetailsgrid=Integer.parseInt(price);
		float expectedprice=Integer.parseInt(Price);
		float CalcExtPrice=expectedprice*1500;
		if(CalcExtPrice==PricefromItemdetailsgrid){
			reporter.SuccessReport("Verify that the Ext Price is correct", "Ext Price is correct for LineItem::"+Lineitem+"", "Extprice :- "+PricefromItemdetailsgrid+"");
		}else
		{
			reporter.failureReport("Verify that the Ext Price is correct", "Ext Price is not correct", "", driver);
		}
	}
	/*
	 * Method is to Verify Price in LineItems
	 * 
	 * @throws Throwable
	 */
	public void verifyTotalOrderRevenue(String totalorderrevenue,String expectedrevenue) throws Throwable {
		float totalrevenue=Integer.parseInt(totalorderrevenue);
		float ExpectedRevenue=Integer.parseInt(expectedrevenue);
		if(totalrevenue==ExpectedRevenue){
			reporter.SuccessReport("Verify that the Ext total oreder Revenue is correct", "Ext total oreder Revenue is correct ", "Ext Order Revenue:- "+ExpectedRevenue+"");
		}else
		{
			reporter.failureReport("Verify that the Ext total oreder Revenue is correct", "Ext total oreder Revenue is not correct", "", driver);
		}
	}
	/*
	 * Method is to click on order search btn
	 * 
	 * @throws Throwable
	 */
	public void clickOrderSearch() throws Throwable {
		click(ORDER_SEARCH, "Order Search");
	}
	/*
	 * Method is to enter Act/Rep Number in Order Search
	 * @throws Throwable
	 */
	public void enterActOrRepNumber(String Number) throws Throwable {
		typeText(ACCT_REPIDNUM_INPUT, Number,"Customer number::"+Number+"");
	}
	
	/*
	 * Method is to click on search btn of Act/Rep Number
	 * 
	 * @throws Throwable
	 */
	public void clickSearchBtnOfActOrRepNumber() throws Throwable {
		click(ACCT_REPIDNUM_SEARCHBTN, "Act/Rep Search");
	}
	
	/**
	 * Method is to Select Partner Type
	 * 
	 * @throws Throwable
	 */
	public void selectPartnerType(String Option)throws Throwable{
		selectByVisibleText(PARTNER_TYPE,Option,"Partner Type::"+Option+"");

	}

	
	public void verifyStartDateandEndDate() throws Throwable {
	String value = getText(START_DATE, "Start date");
	String value1 = getText(END_DATE, "End date");
	if( (value.isEmpty()) && (value1. isEmpty())) {
		reporter.SuccessReport("Verify that the Coverage Start Date and End Date are Blank", "Start Date and End Date fields are Blank", "");
	}
	else{
		reporter.failureReport("Verify that the Coverage Start Date and End Date are Blank", "Start Date and End Date fields are not Blank", "", driver);
	}
	}
	
	public void EnterStartDateandEndDate(String startdate,String endDate) throws Throwable {
		if (isElementPresent(START_DATE, "Start date text box", true)) {
	     type(START_DATE,startdate,"Start date");
	     type(END_DATE,endDate,"End date");
	     String value = getText(START_DATE, "Start date");
	 	String value1 = getText(END_DATE, "End date");
	 	if( (!value.isEmpty()) && (!value1.isEmpty())) {
	 		reporter.SuccessReport("Enter Start Date and End Date", "Start Date and End Date fields exists and entered dates", "Start Date - "+value+" / End Date - "+value1);
	 	}
		}
	 	else{
	 		reporter.failureReport("Enter Start Date and End Date", "Start Date and End Date fields exists and entered dates", "", driver);
	 	}
		
	}
	
	public void clickLabelEnrollment() throws Throwable{
		click(lblEnrollment,"Enrollment label");
		}
		
	
	
	
	
	
}
	
	
	
	
	
	

