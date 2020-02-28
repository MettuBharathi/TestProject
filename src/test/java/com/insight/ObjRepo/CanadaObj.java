package com.insight.ObjRepo;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class CanadaObj extends ActionEngine {
	public static By CANADA_WEBGROUP = By
			.xpath("//span[@class='c-header-nav__text  u-truncate' and contains(text(),'Canada')]");
	public static By SHIP_BILL = By.xpath("//h1[text()='Shipping/Billing']");
	public static By CART_LABEL = By.xpath("//h1[@class='shopping-cart__header-title'][contains(text(),'Cart')]");

	public static By STOCK_CART = By
			.xpath("//span[@class='ion-checkmark-circled cart__stock-icon cart__stock-icon--in-stock']");

	public static By QUICK_SEARCH_DROPDOWN = By.xpath("");
	public static By QUICK_SEARCH_TEXT = By.xpath("//input[@id='invQuickInputNumber']");
	public static By SEARCH = By.xpath("//input[@id='quickSearch']");

	public static By ORDERNUM_INVOICEHISTORY = By.xpath("//table[@id='invoiceSearch']//tr//td[6]");
	public static By STARTDATE = By.xpath("//input[@id='startDate']");

	public static By INVOICE_NUMBER = By.xpath("//table[@id='invoiceSearch']//tr//td[1]/a");

	public static By PRINTICON = By.xpath("//div[@class='medium-3 columns']//div/a[2]");
	public static By SEARCHBY_ORDER = By.xpath("//div[@class='medium-4 columns']//div[@class='nice-select ']");
	public static By SEARCHBY_DROPDOWN = By.xpath("//div[@class='medium-4 columns']//div[@class='nice-select']");

	public static By QUICKSEARCH_DROPDOWN = By.xpath("//div[@class='column']//h2");

	public static By CLOSE = By.xpath("//div[@id='js-print-invoice-target']//a[@class='close-reveal-modal']");
	public static By DOWNLOADLINK = By
			.xpath("//div[@class='medium-3 columns']//a[@class='icon download has-tip tip-left']");

	/*
	 * *************************************************************************
	 * ***************************************** >>>>>>>>>>>>>>>>>>>> LOCATORS
	 * TO SELECT ACCOUNT TOOLS MENU AND SELECTS DROP DOWN LIST
	 * <<<<<<<<<<<<<<<<<<<<<<<<<<
	 ******************************************************************************************************************/
	public static By getAccountToolsMenu(String toolsMenuName) {
		return By.xpath("//div[@id='offCanvasNavPlaceholder']//li//a[contains(text(),'" + toolsMenuName + "')]");
	}

	/*
	 * *************************************************************************
	 * ***************************************** >>>>>>>>>>>>>>>>>>>> LOCATORS
	 * TO SELECT ACCOUNT TOOLS MENU AND SELECTS DROP DOWN LIST
	 * <<<<<<<<<<<<<<<<<<<<<<<<<<
	 ******************************************************************************************************************/
	public static By getSearchby(String searchBy) {
		return By.xpath("//div[@class='nice-select  open']//ul/li[contains(text(),'" + searchBy + "')]");
	}

	public static By getSearchByTextOrder(String searchBy) {
		return By.xpath("//div[@class='nice-select open']//ul/li[contains(text(),'" + searchBy + "')]");
	}

	// summary amounts in cart
	public static By EWR_AMOUNT = By.xpath(
			"//section/following::div[@class='columns small-12 large-3 print-5 print-offset-7']//span[text()='EWR ']//parent::div/..//span[@class='iw-currency__amount']");

	// save Quotes
	public static By EWR_FEE_FOR_PRODUCT = By.xpath(
			"//td//a/following-sibling::div//a[contains(text(),'EWR Fees:')]/following-sibling::span[contains(.,'$')]");
	public static By EWR_TOTAL_FEES = By
			.xpath("//tr[@id='ewrFeeRowID']//td[@id='ewrvalueDiv']//span[@id='EWRFeeValue']");
	public static By GST_ESTIMATE_QUOTES_PAGE = By.xpath("//span[@id='taxEstimate']//span[@id='gstHstTaxCostSpan']");
	public static By PST_QST_ESTIMATE_QUOTES_PAGE = By.xpath("//span[@id='taxEstimate']//span[@id='pstTaxCostSpan']");

	// Quotes History
	public static By getSummaryAmounts(String label) {
		return By.xpath("//td[contains(text(),'" + label + "')]/following-sibling::td[contains(.,'CAD')]");
	}

	
	// software license
	public static By getMySoftwareLicenseAgreementscheckBoxes(String spla){
	      return By.xpath("//td//strong[contains(text(),'"+spla+"')]//ancestor::tr//td//input");
	}
	
	public static By SPLA_LABEL=By.xpath("//div[@id='results']//h2[contains(text(),'My Software License Agreements')]");
	public static By NON_SPLA_MSG=By.xpath("//span[@class='columns iw-message__text'][contains(text(),'In order to report usage please remove items that do not apply to the selected service provider.')]");
	public static By REPORTING_USAGE_PERIOD=By.xpath("//div[@class='usage-reporting__detail row row__gutter--tiny collapse expanded']//span[contains(text(),'Report usage for:')]/following-sibling::span//strong");
    public static By CITRIX_LASTUSAGE_REPORTBTN = By.xpath("//td/strong[contains(text(),'CITRIX SERVICE PROVIDER PROGRAM (CSP)')]/following::div/a/span[contains(.,'Retrieve Last Usage Report')]");
    public static By REPORTING_USAGEINRECEIPT_PAGE= By.xpath("//div[@class='row row__gutter--tiny collapse expanded']//span[contains(text(),'Report usage for:')]/following-sibling::span//strong");
	// shipping options
	public static By DEFAULT_SHIIPING_OPTION_GROUND = By
			.xpath("//Strong[text()='Ground']//parent::span//parent::label//input");

	public static By ADVANCE_SEARCH_ORDERHISTORY = By.xpath("//select[@name='searchBy']");

	public static By ADVANCE_SEARCH_TEXTBOX = By.xpath("//input[@class='form__field']");

	public static By ADVANCE_SEARCHBUTTON = By
			.xpath("//button[@class='button search-form__btn js-gtm-orders__quick-search']");

	// Order Number in Order history page
	public static By getOrderNumber(String orderNumber) {
		return By.xpath("//table[@class='iw-table']//tbody//tr//td//a[contains(text(),'" + orderNumber + "')]");
	}

	// Details in Order History Page
	public static By ORDERNUMBER_DETAILSPAGE = By.xpath("//span[@class='order-details__header-sales-doc-number']");
	public static By REFERENCENUMBER_DETAILSPAGE = By.xpath(
			"//ul[@class='row expanded order-details__table orders__list orders__list--no-bullets']//li[2]/span[2]");
	public static By PONUMBER_DETAILSPAGE = By.xpath(
			"//ul[@class='row expanded order-details__table orders__list orders__list--no-bullets']//li[3]/span[2]");

	
	public static By CANADA_FLAG = By
			.xpath("(//button[@class='c-button  c-button--subtle c-dropdown__button c-header-top__link'])[3]");
	public static By CHKBOX_SELECT = By.xpath("//td[@class='col1']//input[@class='selectedId' and @type='checkbox']");
	public static By SELECT_PRODUCT = By.xpath("//div[@class='buttons inline']//a[@id='viewProductsForSelected']//span[contains(text(),'View Products For Selected Agreements')]");
	public static By SEARCH_PRODUCT = By.xpath("//input[@id= 'search-tab-banner-box-input']");
	public static By PROD_PRICE = By.xpath("//div[@class='columns small-12']//p[@class='prod-price']");
	public static By PRORATED_PRICE = By.xpath("//p[@class='proration__text' and contains(text(),'prorated')]");
	public static By FINAL_PRORATED_PRICE = By.xpath("//span[@class='iw-currency__amount']");
	public static By CONTINUE_TO_CHECKOUT = By.xpath("//a[@class='button primary medium'][contains(text(),'Continue to Checkout')]");

	public static By getEWRNumber(String ewr) {
		return By.xpath("//div[@class='columns shrink iw-summary__value']//span[@class='iw-currency__amount' and text()='"+ ewr + "']");
	}
			
	// Purchase Number, Reference Number in Order history page
	public static By getProductDetail(String productDetail) {
		return By.xpath("//table[@class='iw-table']//tbody//tr//td[contains(text(),'" + productDetail + "')]");
	}
	
	// To get the Report usage buttons in My Software License Agreements screen
	public static By reportUsageButtons(String label){
		return By.xpath("//div[@class='reportUsageClass']//a//span[contains(text(),'"+label+"')]");
	}
	
	public static By REPORT_ZERO_USAGE_LINK=By.xpath("//a//strong[contains(text(),'Report zero usage for this period')]");
	
	//CREATEACCOUNT
		//Create Account on Main menu for Cananda
		public static By CREATE_AN_ACCOUNT = By.xpath("//div[@class='o-grid__item  o-grid__item--shrink  u-push-auto@desktop']/nav/ul/li//a[contains(text(),'Create an account')]");	
	    public static By CUSTOM_CHECKBOX = By.xpath("//input[@id='receiveEmails']");//Custom checkbox	
		public static By NEXT_BUTTON = By.xpath("//a[@id='cla-next']");//Next Buuton	
		public static By COUNTRY = By.xpath("//select[@id='country']/option[@value='CA']");//Country
		public static By JOBTITLE = By.xpath("//select[@id='jobTitle']");//JobTitle
		public static By COORPORATE_ENTERPRISE = By.xpath("//input[@id='at2']");
		public static By CREATE = By.xpath("//a[@id='create']");
		public static By getOptions(String option){
			return By.xpath("//input//following::label[contains(.,'"+option+"')]");
			
		}
		
		
		//ShippingBill 
		
		public static By COMPANY_NAME = By.xpath("//input[@name='companyName']");
		public static By ATTENTION = By.xpath("//input[@name='attentionForm.attentionLine']");
		public static By STREET1 = By.xpath("//input[@name='street1']");
		public static By STREET2 = By.xpath("//input[@name='street2']");
		public static By LOCATIONID = By.xpath("//input[@name='attentionForm.address3']");
		public static By CITY = By.xpath("//input[@name='city']");
		public static By STATE = By.xpath("//select[@name='state']");
		public static By ZIPCODE = By.xpath("//input[@name='zipCode']");
		public static By SAVE_ADDRESS = By.xpath("//button[@class='button expanded']");
		
		// ShippingOptions
			public static By getCarriers(String shippingOptions) {
				return By.xpath("//label[@class='form__label--inline']//span//strong[contains(text(),'"+shippingOptions+"')]//following-sibling::span//span[@class='iw-currency__amount']");
			}
		
			public static By SAMEADDRESS_CHECKBOX = By.xpath("//input[@class='form__field form__input--checkbox']");
			public static By REFERENCE_NO = By.xpath("//div[@id='refNo']");
			public static By getCountryFlag(String flag){
				return By.xpath("//li//button[@id='headerLocaleDropdown']/*[name()='svg']/*[name()='use' and @*='#flag-"+flag+"']");
				
			}
			
						
			public static By getEnterACard(String creditcardOption){
				return By.xpath("//a[@class='link-button create createNewCard'][@data-cardtype='"+creditcardOption+"']");
				
			}
			
			public static By getCardType(String cardType){
				return By.xpath("//div[@class='nice-select paymentField open']//ul[@class='list']/li[contains(.,'"+cardType+"')]");
				
			}	
			public static By CLICK_CARDTYPE = By.xpath("//div[@class='nice-select paymentField']");
			public static By CANCEL_ENTER_CARD = By.xpath("//input[@type='submit'and @value='Cancel']");
			public static By getReportOptions(String reportOption){
				return By.xpath("//div[@id='reportContents0']//div//a[contains(.,'"+reportOption+"')]");				
			}
			
			public static By REPORTS_PAGE = By.xpath("//div[@id='masterDiv']//h1");
			
			public static By getSelectAReport(String selectReport){
				return By.xpath("//select[@id='reportType']/*[contains(.,'"+selectReport+"')]");
				
			}
			public static By getAccountSelections(String account){
				return By.xpath("//select[@id='selectionList']/*[contains(.,'"+account+"')]");
				
			}
			public static By ACCOUT_SELECTION=By.xpath("//select[@id='selectionList']");
			
			public static By FILTERCURRENT_OPTION = By.xpath("//input[@name='currencyoptionsFilterID']");
			public static By getFilterByCurrency(String currency){
				return By.xpath("//select[@id='currencyOptionsSelectSUPPLY' ]/*[contains(.,'"+currency+"')]");
				
			}
			
			public static By getScheduleReport(String scheduleOption){
				return By.xpath("//select[@id='ddlInterval']/*[contains(.,'"+scheduleOption+"')]");
				
			}
			public static By DELIVERY_OPTION = By.xpath("//div[@id='deliveryOptionsLabel']");
			
			public static By getQuickDate(String quickDateOption){
				return By.xpath("//select[@id='ddlQuickDates']/*[contains(.,'"+quickDateOption+"')]");
				
			}
			public static By START_DATE = By.xpath("//input[@id='ordFullStartDateAlt']");
			public static By END_DATE = By.xpath("//input[@id='ordFullStartDateAlt']");
		
			
			public static By getDeliveryformat(String deliveryFormat){
				return By.xpath("//select[@id='ddlDeliveryFormat']/*[contains(.,'"+deliveryFormat+"')]");
				
			}
			public static By FILTER_ORDERTYPE = By.xpath("//select[@id='AdHocOrdTypeSelID']/*[contains(.,'All')]");
			
			public static By SMART_CHECK = By.xpath("//input[@name='smartTrackerId']");
			
			public static By AVAILABLE_FIELDS = By.xpath("//select[@id='AdHocAvailableSel']");
			public static By ADD_FIELD =By.xpath("//a[@id='manageReq_LtoR']");
			
			public static By getDeliveryMethod(String deliveryMethod){
				return By.xpath("//select[@id='ddlDeliveryMethod']/*[contains(.,'"+deliveryMethod+"')]");
				
			}
			public static By DELIVERYMETOD =By.xpath("//select[@id='ddlDeliveryMethod']");
			public static By DELIVERY_FORMAT =By.xpath("//select[@id='ddlDeliveryFormat']");
			
			public static By RUN =By.xpath("//a[@title='Run']/span");
			
			public static By THANK_YOU_ORDER_MSG=By.xpath("//h1[contains(text(),'Thank you for your order.')]");
			
			// Filters
			public static By FILTER_BY_MANUFACTURER=By.xpath("//section[@aria-label='Filter By Manufacturers']");
			public static By getwelcomemsg(String email){
				return By.xpath("	//div[@id='offCanvasNavPlaceholder']//p[contains(.,'Welcome back "+email+".')]");

			}
			}
		
