package com.insight.SmartTest.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.insight.accelerators.ActionEngine;

public class HomePage extends ActionEngine
{
	
	public static By lnkCreateQuote = By.cssSelector("a[class='navbar-icon icon-create-quote']");
	public static By lblAccountInfo = By.xpath("//*[text()='Account Info']");
	public static By txtSoldTo = By.xpath("//*[@id='sold-to']");
	//Product Search Page
	public static By btnProductSearch = By.xpath("//button[text()='Product Search']");
	public static By drpDwnQuoateProgram = By.xpath("//*[@name='program']");
	public static By drpDwnQuoateProgramValue = By.xpath("//*[@name='program']//option[@value='A000000001']");
	public static By drpDwnBlankOption = By.xpath("//*[@name='program']//option[1]");
	public static By SEARCHBUTTONKEYWORDSEARCH=By.xpath("//button[@type='submit']");
	public static By txtKeywordSearch=By.xpath("//input[@name='searchText']");
	//Select OptionsAndLevels from  popup
	public static By chkBoxLICAndMaint = By.xpath("//*[text()='Lic & Maint']");
	public static By chkBoxVipCommercial= By.xpath("//label[text()='VIP Commercial']");
	public static By btnSaveButtonInOptionsAndLevels=By.xpath("//*[@class='btn btn-primary pull-right']");
	public static By btnOptionsandLevels=By.xpath("//a[contains(text(),'Options / Levels')]");
	//ResultsGrid Value locators
	public static By lblMaterialID =  By.xpath("//*[@colid='materialId' and  @tabindex]");
	//Product Search Details
	public static By lbl = By.xpath("//*[@class='btn btn-primary btn-block']");
	//SalesDoc Number field
	public static By txtSalesDocNumber = By.cssSelector("input[placeholder='Sales Doc Number']");
	//Select option from history
	public  static By HistoryDropdown(String dropdownName) {
	return By.xpath("//button[@name='"+dropdownName+"']");
	}
	public static By Historyoptions(String option) {
		return By.xpath("//a[@role='menuitem' and contains(text(),'"+option+"')]");
	}

	public static By columnNamesUderFrieghtpopup(String ColumnName) {
		return By.xpath("//h3[contains(text(),'Freight Shop')]//following::span[contains(text(),'"+ColumnName+"')]");
	}
	public static By InvoiceLink = By.xpath("//*[@class='line-height-double']");	 
	public static By Sideviewbar = By.xpath("//*[@class='sidebar-brand']//i");
	public static By ORDERSEARCH = By.xpath("//a[@class='navbar-icon icon-document']");
	public static By PRODUCTSEARCH=By.xpath("//a[@class='navbar-icon icon-product']");
	//Account Search
	public static By ACCOUNTSEARCH=By.xpath("//a[@class='navbar-icon icon-account']");
	public static By checkboxes(String Type){
	    	return By.xpath("//div[@class='checkbox']//input[@name='retrieve"+Type+"']");//SoldTo//Contact//ShipTo//BillTo
	    }
	public static By textfieldsinaccountsearch(String Textfield){
	return By.xpath("//label[contains(text(),'"+Textfield+"')]/parent::div//following-sibling::div/input[@type='text']");
	}
	 public static By SEARCHBUTTON=By.xpath("//button[@type='submit' and contains(text(),'Search')]");
	 public static By STREET=By.xpath("//div[@colid='street']");
	 public static By NAME=By.xpath("//div[@colid='partnerName']");
	 public static By NUMBER=By.xpath("//div[@colid='partnerNo']");
	 public static By EMAIL=By.xpath("//div[@colid='email']");
	 public static By nameResults(String Name) {
		 return By.xpath("//div[@colid='partnerName' and contains(text(),'"+Name+"')]");//KOVATOVICH SAM
	 }
	 public static By streetResults(String Name) {
		 return By.xpath("//div[@colid='street' and contains(text(),'"+Name+"')]");//KOVATOVICH SAM
	 }
	 public static By fnameResults(String FName) {
		 return By.xpath("//div[@colid='partnerName' and contains(text(),'"+FName+"')]");//SAM
	 }
	 public static By eamilResults(String Email) {
		 return By.xpath("//div[@colid='email' and contains(text(),'"+Email+"')]");
	 }
	//view Quote
	 public static By MATERIAL=By.xpath("//span[contains(text(),'Material')]/ancestor::div[@colid='material']");
     public static By SALESDOCMENTSEARCHFIELD=By.xpath("//div[@id='navbar-collapse']//div/input[@placeholder='Sales Doc Number' and @type='text']");
	 public static By SEARCHBUTTON_SALESDOCNUM=By.xpath("//div[@id='navbar-collapse']//div/input[@placeholder='Sales Doc Number' and @type='text']/parent::div//button[@type='button']");
	 public static By doneButtonQuotepage=By.xpath("//button[contains(text(),'Done')]");
	 public static By CHANGEMODE_ICON=By.xpath("//a[@class='navbar-icon icon-doc-flow-change-mode']");
	 public static By CON=By.xpath("//span[contains(text(),'CON')]/ancestor::div[@colid='con']");
	 public static By xConSymbol(String itemnum) {
		 return By.xpath("//div[@colid='itemNumber' and contains(text(),'"+itemnum+"')]/following-sibling::div[@colid='con']");
	 }
	 public static By priceofLineitem(String itemnum,String Price) {
		 return By.xpath("//div[@colid='itemNumber' and contains(text(),'"+itemnum+"')]/following-sibling::div[@colid='price']//div//span[contains(text(),'"+Price+"')]");
	 }
	 public static By repCostofLineitem(String itemnum) {
		 return By.xpath("//div[@colid='itemNumber' and contains(text(),'"+itemnum+"')]/following-sibling::div[@colid='repCost']//div//span");
	 }
	 public static By clientCostofLineitem(String itemnum) {
		 return By.xpath("//div[@colid='itemNumber' and contains(text(),'"+itemnum+"')]/following-sibling::div[@colid='clientCost']");
	 }
	 public static By getPriceofLineitem(String itemnum) {
		 return By.xpath("//div[@colid='itemNumber' and contains(text(),'"+itemnum+"')]/following-sibling::div[@colid='price']//div//span");
	 }
	 public static By ITEMDETAILSLABEL=By.xpath("//h3[@class='panel-title' and contains(text(),'Item Details')]");
	 public static By DIVERSITYPARTNER=By.xpath("//label[contains(text(),'Diversity Partner')]/following-sibling::div/input[@id='diversity-partner']");
	 public static By COPYCONTRACT=By.xpath("//button[contains(text(),'Copy Contract to all Lines')]");
	 public static By COPYTOLLREPORTINGFIELDS=By.xpath("//button[contains(text(),'Copy Reporting Fields to all Lines')]");
	 public static By ADVANCEDHEADER=By.xpath("//div[@class='panel-heading']//h3[contains(text(),'Advanced Header')]");
	 public static By summaryTabs(String Tab) {
	 return By.xpath("//li/a[text()='"+Tab+"']");
	 }
	 
	 public static By summaryTabsSummaryData(String Type,String Text) {
		 return By.xpath("//th[contains(text(),'"+Type+"')]/following-sibling::td/span[contains(text(),'"+Text+"')]");
	 }
	 
	 public static By summaryTabsSummaryDataValue(String Type) {
		 return By.xpath("//th[contains(text(),'"+Type+"')]/following-sibling::td/span");
	 }
	 public static By tabinAdvancedHeader(String Tab) {
		 return By.xpath("//ul[@class='nav nav-tabs']//li//a[contains(text(),'"+Tab+"')]");
	 }
	 public static By tabinLineItemHeader(String Tab) {
		 return By.xpath("//button[contains(text(),'"+Tab+"')]");
	 }
	 
	 public static By SHIPPINGCONDITIONSDRPDOWN_ANDVANCEDHEADER=By.xpath("//select[@id='shipping-conditions']");
	 
 
	 public static By btnConvertToOrder = By.xpath("//button[contains(text(),'Convert to Order')]");
	 public static By txtEnterKeywordSearh = By.cssSelector("input[name='searchText']");
		 public static By SearchButton = By.cssSelector("button[type='submit']"); 
		 
		 public static By closebuttonInProductSearch = By.xpath("//*[@class='modal-body']//button[@class='btn btn-hollow pull-right']");
		 
//CQT16_IPSCopyLinks_Action1_Script		 
	 public static By AvailabilityStock = By.xpath("//span[contains(text(),'Avail.')]//following::div[@colid='totalStock']");
	 public static By XsymbolUnderCon = By.xpath("//span[contains(text(),'CON')]//following::span[contains(text(),'X')]");
	 public static By btnCopyContracttoallLines=By.xpath("//button[contains(text(),'Copy Contract to all Lines')]");
	 public static By txtDivercityPartner = By.cssSelector("input[id='diversity-partner']");
	 public static By SearchButtonOfDivercityPartner = By.xpath("//input[@id='diversity-partner']//following::i");
	 public static By DivercityPartnerresults =By.xpath("//span[contains(text(),'Name')]//following::div[@colid='name']");
	 public static By txtUSCOMMemberId = By.xpath("//label[contains(text(),'U.S. COMM MEMBER ID #:')]//following-sibling::input");
	 
	 public static By EntertextfiledsInLineItemsmodelbox(String fieldname) throws Throwable{
		 return By.xpath("//label[contains(text(),'"+fieldname+"')]//following-sibling::input");
		
	}
	 public static By btncopyreportingfieldstoallthelines = By.xpath("//button[contains(text(),'Copy Reporting Fields to all Lines')]");
	 
	 public static By Adherencetofloorradiobuttonoptions(String radiobutton){
		 return By.xpath("//label[contains(text(),'"+radiobutton+"')]//preceding-sibling::input");
	 }
	 
	 public static By txtAdherencetofloorreason = By.xpath("//textarea[contains(text(),'Reason')]");
	 public static By btnCancel = By.xpath("//a[contains(text(),'Cancel')]");
	 public static By loadingsymbol = By.xpath("//div[@class='custom-loader open']");
	 public static By txtIpsContarctId =By.id("reporting-field-5");
	 public static By txtReportingField5 =By.id("reporting-field-4");
	 public static By txtReportingField4 =By.id("reporting-field-3");
	 public static By txtUSCOMMEMBERID =By.id("reporting-field-0");
	 public static By txtBiodivercitypartner = By.id("diversity-partner");
	 
	 
	 public static By btnArrowicon = By.xpath("//button[@class='btn btn-alternate']//following::i[@class='fa fa-chevron-right']");
	 public static By btnLeftArrowicon = By.xpath("//button[@class='btn btn-alternate']//following::i[@class='fa fa-chevron-left']");
	
	 public static By LineitemText = By.cssSelector("input[id='line-item']");
	 public static By txtPoNumber = By.cssSelector("input[id='po-number']");
	 public static By txtPoRelNumber = By.cssSelector("input[id='po-release-number']");
	 public static By txtSoldToAttn = By.cssSelector("input[id='sold-to-attn']");

	 public static By btnSaveOrderwithoutAttachment = By.xpath("//button[contains(text(),'Save Order without Attachment')]");
	 public static By txtsubject  =By.cssSelector("input[id='subject']");
	 public static By txtEnterEstimateID = By.cssSelector("input[name='estimateNumber']");
	 public static By SELECT_MANUFACTURER_DD =By.xpath("//div[@class='Select-placeholder']/preceding::label[contains(text(),'Manufacturer')]/following::div[2]");
	 public static By SELECT_MANUFACTURER_DATA =By.xpath("//div[@class='Select-input ']/preceding::label[contains(text(),'Manufacturer')]/following::div[2]//input");
	 public static By PRODUCTSEARCH_HDR =By.xpath("//h3[@class='panel-title'][contains(text(),'Product Search')]");
	 public static By checkboxinProductSearch(String CheckboxName) {
		 return By.xpath("//input[@name='"+CheckboxName+"']");
	 }
	 public static By SELECT_PRICING =By.xpath("//select[@name='retrieveCustomerPricing']");
	 //create order
	 public static By CREATEQUOTE_BUTTON=By.xpath("//a[@class='navbar-icon icon-create-quote']");
	 public static By SIDEBAR=By.xpath("//div[@class='sidebar-brand']//i");
	 public static By SOLDTO_CREATEQUOTE=By.xpath("//input[@id='sold-to']");
	 public static By SEARCH_SOLDTO=By.xpath("//input[@id='sold-to']/following-sibling::span/button[@type='button']");
	 public static By AddMeterialInLineItems = By.cssSelector("input[class='form-control ignore-focus-cancel']"); 
	 public static By AddMaterialLineItem1 (String Material, int row)
	 {
		 return By.xpath("//div[@row = '"+row+"']//div[@colid='"+Material+"']");
	 }
	 
	 public static By AddfrtCharge (int row)
	 {
		 return By.xpath("//div[@row = '"+row+"']//div[@colid='frtCharge']");
	 }
	 public static By txtfrtchrage = By.cssSelector("input[id='amount']");
	 public static By contractforId(String SW_contarct){//1510000002
		 return By.xpath("//div[@colid='contractId' and contains(text(),'"+SW_contarct+"')]/following-sibling::div[@colid='longDescription']//span");//getText
	 }
	 public static By FIRSTREPORTINGFIELDLABEL=By.xpath("//input[@id='reporting-field-0']/parent::div//label");
	 public static By UPDATECOSTING=By.xpath("//button[@type='button' and contains(text(),'Update Costing')]");
	 public static By SAVEAVSORDERBUTTON=By.xpath("//button[contains(text(),'Save as Order')]");	
	 public static By NOPOATTACHEDPOPUP=By.xpath("//h3[contains(text(),'NO PO Attached!')]");
	 public static By SAVERORDERWITHOUTATTACHMENTBUTTON=By.xpath("//button[@type='button' and contains(text(),'Save Order without Attachment')]");
	 public static By ERRORMSGTAG=By.xpath("//p[contains(text(),'Error creating sales order in SAP.')]");
	 public static By errormsgReportingfields=By.xpath("//span[contains(text(),'ERROR MESSAGE(S)')]");
	 
	 public static By OKBUTTON=By.xpath("//button[@type='button' and contains(text(),'OK')]");
	 public static By OkButtonInPONumber  = By.xpath("//button[@type='button' and contains(text(),'Ok')]");
	 public static By closeButtonofSidebar(String Docc_type){//Create Document
		 return By.xpath("//a[contains(text(),'"+Docc_type+"')]/parent::li/a[@class='sidebar-list-close']");
	 }
	 public static By YESBUTTONINWARNINGMSG=By.xpath("//button[@type='submit']");
	 public static By SALESORGDROPDOWN=By.xpath("//select[@id='sales-org']");
	 //ORD01_ZFRTHold
	 public static By PAYMENT_TERMS=By.xpath("//select[@id='payment-terms']");//select 2001 Net 30 days
	 public static By PONUMBER_ACCOUNTINFO=By.xpath("//input[@id='po-number']");
	 public static By PONUMBERSEARCHBUTTON_ACCOUNTINFO=By.xpath("//input[@id='po-number']/parent::div//button");
	 public static By CARRIER_ADVANCEDHEADER=By.xpath("//select[@id='carrier']");
	 public static By HOLDS_TEXTAREA=By.xpath("//textarea[@class='form-control']");
	 public static By DISPLAYMODE_BUTTON = By.xpath("//*[@id='main']//..//button//i[@class='fa fa-exchange']"); 
	 public static By QUANTITY_LINEITEMS=By.xpath("//span[contains(text(),'Qty')]/ancestor::div[@colid='qty']");
	 public static By DescriptionofanItem(String itemnum) {
		 return By.xpath("//div[@colid='itemNumber' and contains(text(),'"+itemnum+"')]/following-sibling::div[@colid='description']");
	 }
	 public static By QuantityofanItem(String itemnum) {
		 return By.xpath("//div[@colid='itemNumber' and contains(text(),'"+itemnum+"')]/following-sibling::div[@colid='qty']");
	 }
	 public static By TYPEQUANTITYOFANITM= By.xpath("//div[@colid='qty']//input");
	 public static By OKBUTTONINPOPUP=By.xpath("//button[@type='button' and contains(text(),'Ok')]");
	 public static By DELETEITEM=By.xpath("//span[@id='eName']");
	 public static By SUBJECTTOGETORDERNUM=By.xpath("//input[@id='subject']");
     public static By CANCELBUTTONIN=By.xpath("//a[contains(text(),'Cancel')]");
     public static By COLOUROFITEM=By.xpath("//div[@class='ag-body-container']//div");
     public static By SUBMITBUTTONTOREJECTITEM=By.xpath("//button[@type='submit']");
     public static By REASONFORREJECTION=By.xpath("//select[@id='reason']");
     public static By TOTAL_WEIGHT =By.xpath("//input[@id='total-weight']");
     public static By SAVEASQUOTE_BUTTON=By.xpath("//button[contains(text(),'Save as Quote')]");
     public static By OUTPUT_POPUHDR = By.xpath("//h3[contains(text(),'Output')]");
     public static By EMAIL_TEXT = By.xpath("//textarea[@class='form-control'][contains(text(),'@INSIGHT.COM')]");
     public static By SEND_BTN = By.xpath("//button[@class='btn btn-primary pull-right'][contains(text(),'Send')]");
     public static By CANCEL_BTN = By.xpath("//a[@class='btn btn-alternate pull-right'][contains(text(),'Cancel')]");
     public static By INFO_POPUP = By.xpath("//div[@class='panel-heading']/h3[contains(text(),'Info')]");
     public static By INFO_POPUP_OKBTN = By.xpath("//button[@class='btn btn-primary pull-right']"); 
     
     public static By DISPLAY_MODE_BTN = By.xpath("//button[@class='btn btn-alternate']/i[@class='fa fa-exchange']");
     public static By SELECT_GROUPING_DD = By.xpath("//select[@id='select-grouping']");
     public static By NEW_REPMARGIN_PRCNT = By.xpath("//input[@name='new-rep-margin-percent']");
     public static By GET_QUOTENMBR = By.cssSelector("input[id='quote-number']");
     public static By SHIP_TO_ATTN = By.cssSelector("input[id='ship-to-attn']");
     public static By ACCOUNT_NUMBERSEARCH = By.xpath("//div[@class='navbar-form navbar-right']//input[@class='form-control']");
     public static By ACCOUNT_SEARCHBTN = By.xpath("//div[@class='navbar-form navbar-right']//input[@class='form-control']/following::span/button[@class='btn btn-default']");
     public static By ExpandTabs(String Tab) {//Account Options
		 return By.xpath("//div[@class='panel-heading']//h3[contains(text(),'"+Tab+"')]");
	 }
     public static By MAKE_DEFAULTBTN = By.xpath("//span[@id='eName'][contains(text(),'Make Default')]");
     public static By PARTNER_LIST = By.xpath("//*[@colid='id' and  @tabindex]");
     public static By SAVE_CHANGESBTN = By.xpath("//button[@class='btn btn-primary pull-right'][contains(text(),'Save Changes')]");
     public static By TXTSHIPTO = By.xpath("//*[@id='ship-to']");
     //Search03
     public static By salesOrgPopUp(String saleOrg) {
		 return By.xpath("//th[contains(text(),'Sales Org')]/ancestor::table//tr/td[contains(text(),'"+saleOrg+"')]");
	 }
     public static By materialIdforSearchResult(String MaterialId) {
		 return By.xpath("//div[@colid='materialId' and contains(text(),'"+MaterialId+"')]");
	 }
     public static By tabsinMaterailIdProductsearchPOpUp(String Tab) {
		 return By.xpath("//ul[@class='nav nav-tabs']//li//a[contains(text(),'"+Tab+"')]");
	 }
     public static By ICSTABACCESSORIESTABINPRODUCTSEARCH=By.xpath("//div[@colid='rrFlag']//span[contains(text(),'ICS')]");
     public static By EMAILTECHSEPCSINTECHSEPCSTAB=By.xpath("//a[contains(text(),'Tech Specs') and @class='btn btn-alternate']");
     public static By EMAILTECHSPECSPOPUPTOSENDEMAIL=By.xpath("//h3[contains(text(),'Email Tech Specs')]");
     public static By TOTEXTFIELDINEAMILTECHSPECSPOPUP=By.xpath("//input[@id='to']");
     public static By SENDEMAILBUTTON=By.xpath("//button[@type='submit' and contains(text(),'Send Email')]");
     public static By EMAILSENTSUCCESSPOPUP=By.xpath("//span[contains(text(),'Email sent successfully.')] "); 
     public static By HOMEBUTTONINPRODUCTSEARCHPOPUP=By.xpath("//a//i[@class='fa fa-home']"); 
     public static By MATERAILAIDINPRODUCTSEARCHPOPUP= By.xpath("//h4[@class='product-header']/following-sibling::p");
     public static By CLOSEBTN_PRODUCTSEARCH=By.xpath("//div[@class='modal-body']//button[@class='btn btn-hollow pull-right']//i[@class='fa fa-times']"); 
     //Create Quote
     public static By CONTRACTIDLABEL_ADVANCEDHEADERCONTRACT=By.xpath("//div[@colid='contractId']//span[contains(text(),'ContractId')]");
     public static By contractIdItem(String contactid) {
		 return By.xpath("//div[@class='ag-body-container']//div[@colid='contractId' and contains(text(),'"+contactid+"')]");
	 }
     public static By PRODUCTSEARCHCONTRACTID=By.xpath("//span[@id='eName' and contains(text(),'Product Search')]");
     public static By RESULTSOFKEYWORDSEARCHCONTRACTID=By.xpath("//div[@class='ag-body']//div[@colid='materialId']");
     public static By contractIDwithHiletedClour(String contactid) {
		 return By.xpath("//div[@id='ccs-logo-inline-legacy']/following-sibling::div//div[@colid='contractId' and contains(text(),'"+contactid+"')]/parent::div");
	 }
     public static By tabsInItemDetailsPopup(String TabName) {
		 return By.xpath("//div[@class='modal-backdrop fade in']/following-sibling::div//div//ul[@class='nav nav-tabs']/li/a[contains(text(),'"+TabName+"')]");
	 }

     public static By Lineitem10 = By.xpath("//div[contains(text(),'000010')]");


     public static By Lineitem10(String LineItem) {
    	 return By.xpath("//div[contains(text(),'"+LineItem+"')]");
     }


     public static By getContractId=By.xpath("//div[@class='ag-body-container']//div[@colid='contractId' and contains(text(),'')]");

 //CQT09_ChangeShipToAttn_Action1_Script
	 
	 public static By DesciptionInLineItems  = By.xpath("//*[@colid='description' and  @tabindex]");
	 public static By txtShiptoAttn= By.cssSelector("input[id=ship-to-attn]");
	 public static By btnSaveAsQuote = By.xpath("//button[contains(text(),'Save as Quote')]");
	 public static By CloseButtonofOutputform = By.xpath("//*[@class='modal-body']//..//i");
	 public static By DisplayModeButton = By.xpath("//*[@id='main']//..//button//i[@class='fa fa-exchange']");
	 public static By QuoteNumber = By.cssSelector("input[name='quote-number']");
	 
	 //CQT03_UpdQtyRepMargin_Action1_Script
	 public static By rowsunderProgramsTab = By.xpath("//*[@colid='contractId']");
	 public static By ContractIdField = By.xpath("//div[@colid='contractId' and @tabindex=-1]");
	 public static By ItemOnGrid(int row) {
		 return By.xpath("//div[@row='"+row+"']//div[@colid='itemNumber']");
	 }
	 public static By Lineitemrightclickoptions(String options) {
		 return By.xpath("//span[contains(text(),'"+options+"')]");
	 }
	 //public static By productsearchundercontractid = By.xpath("//span[contains(text(),'Product Search')]");
	 public static By Manufacturer = By.xpath("//*[contains(text(),'Manufacturer')]//..//div[@class='Select-placeholder']");
	 public static By Manufacturer1 = By.xpath("//*[contains(text(),'Manufacturer')]//..//div[@class='Select-placeholder']//following-sibling::div/input");
	 
	 public static By InStockOnlyCheckbox = By.cssSelector("input[name='stockMode']");
	 public static By PricingDropdown = By.id("pricing");
	 public static By KEYWORDSEARCHTEXTFIELD = By.cssSelector("input[name='searchText']");
	 public static By SEARCHBUTTONBKEYWORDSEARCH = By.cssSelector("button[type='submit']"); 
	 public static By productselect = By.cssSelector("div[colid='totalStock']");
	 public static By TotalWeight  = By.cssSelector("input[id='total-weight']");
	 public static By Email = By.cssSelector("textarea[type='email']");
	 public static By Sendbutton = By.xpath("//button[contains(text(),'Send')]");
	 
	//ResultsGrid Value in lineItems
		public static By lineItemID =  By.xpath("(//*[@colid='material' and  @tabindex])[1]");
		public static By CONTRACT_SYMBOL = By.xpath("//div[@class='ag-react-container']/span[contains(text(),'X')]");
		
		public static By REDCROSS_SYMBOL = By.xpath("//i[@class='fa fa-times text-red']");
		public static By UPDATE_BTN = By.xpath("//button[@class='btn btn-alternate pull-left'][contains(text(),'Update')]");
		public static By ITEM_DETAILS_POPUP = By.xpath("//div[@class='custom-modal modal-lg modal-dialog']");
		 public static By tabinItemDetailsPopup(String TabName) {
			 return By.xpath("//ul[@class='nav nav-tabs']/li/a[contains(text(),'"+TabName+"')]");
		 }
		 public static By CONTRACTTAB_DONEBTN = By.xpath("//button[@class='btn btn-primary pull-right'][contains(text(),'Done')]"); 
		 public static By PricingTabTable =  By.xpath("//*[@colid='id' and  @tabindex]");
		 public static By selectContractID(String ID){
			 return By.xpath("//*[@colid='contractId' and  text()='"+ID+"']");
		 }
		 public static By pricing_ValueClmn(String Pricingtext)
		 {
			 return By.xpath("//*[@colid='id' and  text()='"+Pricingtext+"']/following::div[3]");
		 }
		 public static By getcontractsellingPrice(String contractid)
		 {
			 return By.xpath("//div[@colid='contractId' and contains(text(),'"+contractid+"')]/following-sibling::div[@colid='price']");
		 }
		 public static By TAXEXEMPT_SEARCHBTN=  By.xpath("//input[@id='tax-exempt-partner']/following-sibling::span/button/i[@class='fa fa-search']");
		 public static By PARTNERSELECTION_POPUP = By.xpath("//h3[contains(text(),'Partner Selection')]");
		 public static By TAXEXEMPT_TEXTBOX = By.xpath("//input[@id='tax-exempt-partner']");
		 public static By TAX_IN_SUMMARY = By.xpath("//th[contains(text(),'Tax')]/following-sibling::td/span");
		 public static By TAX_REMOVEBTN = By.xpath("//span[@class='input-group-btn']/button[contains(text(),'Remove')]");
		 public static By SHIPTONAME_SEARCCHBTN = By.xpath("//label[contains(text(),'Ship-To Name')]/following-sibling::div/span/button/i");
		 public static By EDIT_ADDRESSPOPUP = By.xpath("//h3[contains(text(),'Edit Address')]");
		 public static By EDITADDRESS_FIELD = By.xpath("//input[@name='address.street1']");
		 public static By EDITZIPCODE_FIELD = By.xpath("//input[@name='address.zipCode']");
		 public static By TAX_JURISDICTION_CODE = By.xpath("//input[@name='address.taxJurisdictionCode']");
		 public static By OVERIDE_CHECKBOX = By.xpath("//input[@name='overrideAddress']");
		 public static By EDITADDRESSWIN_CANCELBTN = By.xpath("//a[@class='btn btn-alternate pull-right'][contains(.,'Cancel')]");
		 public static By SALES_DOC_DD = By.xpath("//button[@name='sales-document']");
		 public static By SALES_DOC_DD_SELECT(String DDoption){ 
			 return By.xpath("//ul[@class='dropdown-menu']/li/a[contains(text(),'"+DDoption+"')]");
			
		 }
		 public static By btnSearchIconForSoldto = By.xpath("//input[@id='sold-to']//following-sibling::span//button");
		 public static By cloumnsUnderLineItems(String ColumnName,String ColumnCell) {
			return  By.xpath("//div[contains(text(),'"+ColumnName+"')]//following-sibling::div[@colid='"+ColumnCell+"']"); 
		 }
		 public static By btnVCtab(String tabname) {
			 
			 return By.xpath("//h3[contains(text(),'Item Details')]//following::a[contains(text(),'"+tabname+"')]");
		 }
		 public static By txtDurationField  = By.xpath("//label[contains(text(),'Duration (months)')]//following::input");
		 public static By txtDurationFieldUnderAdvanceHeader = By.cssSelector("input[id='duration']");
		 public static By btnCopyDuration  =By.xpath("//button[contains(text(),'Copy Duration')]");
		 public static By txtEndDateFiel = By.cssSelector("input[id='end-date']");
		 public static By btnCopyDates = By.xpath("//button[contains(text(),'Copy Dates')]");
		 public static By coverageEndDate = By.cssSelector("input[id='coverage-end-date']");
		 public static By btnRefreshContracts = By.xpath("//button[contains(text(),'Refresh Contracts')]");
		 public static By ContractIdDesc(String desc) {
			 return By.xpath("//span[starts-with(text(), '"+desc+"')]");
		 }
		 public static By txtCoverageStartDate = By.cssSelector("coverage-start-date");
		 public static By txtCoverageEndDate = By.cssSelector("coverage-end-date");
		 public static By ACQUIRE_ESTIMATEBTN = By.xpath("//button[@type='button' and contains(text(),'Acquire Estimate/Quote')]");
		 public static By ESTIMATE_NUMTEXTBOX = By.xpath("//input[@name='estimateNumber']");
		 public static By ACQUIRE_ESTIMATE_HDR = By.xpath("//h3[@class='panel-title'][contains(text(),'Acquire Estimate/Quote')]");
		 public static By REF_DOCTEXTBOX = By.xpath("//input[@name='ref-doc-number']");
		 public static By COPYALLCONTRACTS_BTN = By.xpath("//button[@class='btn btn-alternate pull-right'][contains(text(),'Copy Contract to all Lines')]");
		 public static By WARNING_YESBTN = By.xpath("//button[@class='btn btn-primary pull-right'][contains(text(),'Yes')]");
		 public static By HISTORY_DD = By.xpath("//button[@name='history']");
		 public static By QUOTENUM_DOCFLOW(String QuoteNum){
				 return By.xpath("(//*[@colid='docNumber' and text()='"+QuoteNum+"'])[1]");
		 }
		 
		 public static By LINEITEM_INPOPUP = By.xpath("//input[@id='line-item']");
		 public static By TIME_DURATION = By.xpath("//input[@id='vc-field-9']");
		 public static By RIGHT_ARROW = By.xpath("//i[@class='fa fa-chevron-right']");
		 public static By lblAddToOrder= By.xpath("");
		 
		 public static By PRICINGID_VALUE(String value){
			 return By.xpath("//*[@colid='id' and  text()='"+value+"']");
		 }
		 

		 public static By MATERIALID_VCTAB(String value){
			 return By.xpath("(//div[@class='ag-body-container']/div/div[@colid='material'])["+value+"]");
		 }
		 
		 public static By PRICEVALUE_INLINEITEMS(String value){
			 return By.xpath("(//*[@colid='price' and  @tabindex])["+value+"]");
		 }
		 
		 public static By REPCOST_INLINEITEMS(String value){
			 return By.xpath("(//*[@colid='repCost' and  @tabindex])["+value+"]");
		 }
		 
		 public static By FIRSTELM_PRICEVALUE_INLINEITEMS = By.xpath("//*[@id='center']/div/div[4]/div[3]/div/div/div[1]/div[13]/div/span"); 
		 
		 public static By REPMARGIN_PERCENT= By.xpath("(//*[@id='center']/div/div[4]/div[3]/div/div/div/div[14]/div/span([2]");
		 public static By PRICE_FIRSTCELLVALUE= By.xpath("(//*[@class='ag-cell ag-cell-not-inline-editing ag-cell-value ag-cell-no-focus'])[2]");


		 public static By getPricingValues(String contractid)
		 {
			 return By.xpath("//div[@colid='id' and contains(text(),'"+contractid+"')]/following-sibling::div[@colid='1']");
		 }
		 
		 public static By getPricingRateValue(String priceValue)
		 {
			 return By.xpath("//div[@colid='id' and contains(text(),'"+priceValue+"')]/following-sibling::div[@colid='0']");
		 }
		 public static By getPricingID(String contractid)
		 {
			 return By.xpath("//div[@colid='id' and contains(text(),'"+contractid+"')]");
		 }

		 public static By REASONPOPUPHEADER = By.xpath("//h3[contains(text(),'Adherence to floor')]");
		 public static By TEXTAREA = By.xpath("//textarea[@class='form-control']");
		 public static By SAVEASQUOTEBTNINWARNINGPOPUP = By.xpath("//button[@type='submit' and contains(text(),'Save as Quote')]");
		 public static By NEXTLINEITEMARROWSYMBOL = By.xpath("//label[contains(text(),'Line Item')]/parent::div//div//span//button//i[@class='fa fa-chevron-right']");
		 public static By REASONFORREJECTIONDD = By.xpath("//select[@id='reason-for-rejection']");
		 public static By REPORTINGFIELD1 = By.xpath("//input[@name='reporting-field-1']");
		 public static By REPORTINGFIELD4 = By.xpath("//input[@name='reporting-field-4']");
		 public static By REPORTINGFIELD5 = By.xpath("//input[@name='reporting-field-5']");
		 public static By REPORTINGFIELD0 = By.xpath("//input[@name='reporting-field-0']");
		 public static By REPORTINGFIELD3 = By.xpath("//input[@name='reporting-field-3']");
		 public static By REPORTINGFIELD6 = By.xpath("//input[@name='reporting-field-5' and @disabled]");
		 
		 //CQT30_IPSCPSSNonTax_Action1_Script
		 public static By ddspecialordertype = By.id("special-order-type");
		 public static By getMaterialsOrderrevenue = By.xpath("//th[contains(text(),'Materials')]//following-sibling::td[1]//span");
		 public static By getTaxOrderrevenue = By.xpath("//th[contains(text(),'Tax')]//following-sibling::td[1]//span");
		 public static By getTotalOrderrevenue = By.xpath("//th[contains(text(),'Total')]//following-sibling::td[1]//span");
		 public static By ddPaymentTerms = By.id("payment-terms");
		 public static By ddShippingConditions = By.id("shipping-conditions");
		 public static By txtQuoteName = By.cssSelector("input[id='quote-name']");
		 public static By txtERateFRN = By.cssSelector("input[id='erateFrn']");
		 public static By txtERateFundingYear = By.id("funding-year");
		 public static By txtErateSLD = By.cssSelector("input[id='e-rate-sld-percent']");
		 public static By txtErateLocation = By.cssSelector("input[id='erate-location']");
		 public static By txterateEligibilityPercent = By.cssSelector("input[id='erateEligibilityPercent']");
		 public static By txtErateFRNvaluefronAttnfield = By.cssSelector("input[id='ship-to-attn']");
		 public static By ddErateFundingYear = By.cssSelector("Select[id='funding-year']");
		

		 public static By QUOTENAME_FIELD_ADVANCEDHEADER = By.id("quote-name");
		 public static By ERATESITE_LOCATIN_INPARTNERSTAB = By.id("erate-location");
		 public static By ddspecialordertypeDisabled = By.xpath("//select[@id='special-order-type' and @disabled]"); 
         public static By quotenamefieldwithgivenvalue(String Text){
        	 return By.xpath("//input[@value='"+Text+"' and @id='quote-name']");
         }
         public static By e_ratelocationfieldwithgivenvalue(String Text){
        	 return By.xpath("//input[@value='"+Text+"' and @id='erate-location']");
         }
         public static By enrolmentidatProgramstabAvancedheader(String enromentid){
        	 return By.xpath("//div[@class='ag-body-container']//div[@colid='contractId']/following-sibling::div[@colid='enrollmentId' and contains(text(),'"+enromentid+"')]");
         }
         public static By ENROLLMENTDROPDOWN = By.xpath("//select[@name='filterBySoftwareContracts']"); 
         //ZZ Contract Product & Price Review
         public static By REASONBFORREJCTN_DD = By.xpath("//select[@id='reason-for-rejection']"); 
         public static By REPMARGIN_PERCENTAGE(int i){
        	 return By.xpath("(//*[@colid='repMargin' and  @tabindex])["+i+"]");
         }
        public static By REP_MARGIN_COUNT = By.xpath("//*[@colid='repMargin' and  @tabindex]"); 
     	public static By RED_CONTRACT_SYMBOL = By.xpath("//i[@class='fa fa-times text-red']");
     	public static By PRODUCT_SEARCHBTN_ITEMDETAILPOPUP = By.xpath("//div[@class='row']/button[contains(text(),'Product Search')]");
     	public static By LAB_FEE_DD = By.xpath("//select[@id='vc-field-0']"); //99-BRONZE
     	public static By LAB_NOTES = By.xpath("//div/textarea[@id='note']");
     	public static By sourceNamePlusIcon(String sourceName){
       	 return By.xpath("//div[@class='table-lg']//table//tr/td[1][contains(.,'"+sourceName+"')]//i");
        }
         
     	public static By supplierNames(int supplierNameValue){
          	 return By.xpath("//div[@class='table-lg']//table//tr["+supplierNameValue+"]/td[2][contains(.,'CANADA')]");
           }
         
     	public static By SOURCE_DD = By.xpath("(//*[@colid='customerOwnedSource' and  @tabindex='-1'])[1]");
     	public static By SOURCE_DD_VALUE(String DDValue){
     	  return By.xpath("//*[@colid='customerOwnedSource' and  @tabindex='-1']//following::option[contains(text(),'"+DDValue+"')]");//Insight Main Warehouse
     	
}
     	public static By costTypes(int type){
         	 return By.xpath("//td[contains(text(),'"+type+"')]/following-sibling::td");
          } 
         
         
         

         public static By vcIconforLineitem(String lineitem){
        	 return By.xpath("//div[@colid='itemNumber' and contains(text(),'"+lineitem+"')]/following-sibling::div[@colid='vc']");
         }
         public static By LABFEEDD = By.xpath("//select[@id='vc-field-0']");
         
         public static By addMaterialAtVcIcon= By.xpath("//div[@id='vc-grid']//div[@class='ag-body-viewport']//div[@colid='material']/input[@class='ag-cell-edit-input']");

         public static By MATERIALATVCINITEMDETAILS = By.xpath("//div[@id='vc-grid']//div[@class='ag-body-viewport']//div[@colid='material']"); 
         public static By POOLS_DROPDOWN=By.xpath("//div[@class='form-group pool-dropdown']//div[@class='Select-input ']");
         public static By POOLS_DROPDOWNINPUT=By.xpath("//div[@class='form-group pool-dropdown']//div[@class='Select-input ']/input");
         public static By BLANCKVALUE_PROGRAM_DD=By.xpath("//select[@name='program']/option[@value='']");
         public static By TYPE_PRICE_OFANITM= By.xpath("//div[@colid='price']//input");

         //CQT02_FreightESDScript
         
        
         
         public static By ORDER_SEARCH= By.xpath("//a[@class='navbar-icon icon-document']");
         public static By ACCT_REPIDNUM_INPUT= By.xpath("//input[@name='customerNumber']");
         public static By ACCT_REPIDNUM_SEARCHBTN= By.xpath("//input[@name='customerNumber']/parent::div//span//button");
         public static By PARTNER_TYPE= By.xpath("//select[@name='partnerType']");
         public static By SEARCBTN_ORDERSEARCH=By.xpath("//button[@type='submit']");
         public static By enrollmentDropdown=By.xpath("//select[@name='filterBySoftwareContracts']");
         public static By enrollmentDropdownValue=By.xpath("//option[contains(text(),'4100012345 - Insight Automated KBP Account 2400')]");
         public static By enrollmentBlankOption=By.xpath("//select[@name='filterBySoftwareContracts']//option[1]");
         public static By carrierOptionValue = By.xpath("//option[contains(text(),'CR_ESD')]");
         public static By shippingConditinOption = By.xpath("//option[contains(text(),'80 Electronic Delivery')]"); 
         public static By txtTAAvalue = By.cssSelector("input[id='taa']");
         public static By txtChangeMode = By.xpath("//div[contains(text(),'CHANGE')]");


          
        
         
         public static By ERROR_MESSG_TEXT = By.xpath("//div[@class='radio-toggle-desc']/span");
         public static By clickOnVCGreenSymbol(String LineItem){ //000020
        	 return By.xpath("//*[@colid='itemNumber' and  text()='"+LineItem+"']/following::div[6]/i");
         }

         public static By START_DATE = By.id("coverage-start-date");
         public static By END_DATE = By.id("coverage-end-date");
         public static By START_DATE_CALICON = By.id("(//input[@id='coverage-start-date']/following::button/i[@class='fa fa-calendar'])[1]");
         public static By END_DATE_CALICON = By.id("//input[@id='coverage-end-date']/following::button/i[@class='fa fa-calendar']");
         public static By lblEnrollment=By.xpath("//label[contains(text(),'Enrollment')]");
}
