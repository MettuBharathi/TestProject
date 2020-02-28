package com.insight.ObjRepo;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class ProductDetailObj extends ActionEngine {
	// personal product list
	public static By DELETE = By.xpath("//button[@class='c-button  c-button--link c-item-card__actions-button']");
	public static By DLETED_MESSAGE = By.xpath("//span[@class='c-toast__header-title']");
	public static By ADD_TO_COMPARE_LIST = By.xpath("//button[@class='c-button  c-button--link c-item-card__compare']");
	public static By COMPARE_NOW = By.xpath("//a[@class='c-button  c-button--link c-item-card__compare-now']");
	public static By COMPARE_PRODUCTS = By.xpath("//h2[text()='Compare products']");
	public static By LicenseRadioButton = By.xpath(
			"//*[@aria-label='Filter By Software / License Type']//div//label//span[@data-gtm-event='advSoftware / License Type' and @data-label='License']");

	public static By deleteProduct(String partNumber) {
		return By.xpath("//div[@class='c-item-card__details o-grid__item u-1/1 u-1/2@tablet'][contains(.,'" + partNumber
				+ "')]/following-sibling::div//span[contains(text(),'Delete')]");
	}

	public static By ACCOUNT_TOOLS = By.xpath(
			"//button[@class='c-button  c-button--none c-header-account-menu__btn  c-header-account-menu__btn--logged-in  c-header-nav__link']");

	public static By getAccountToolsMenu(String toolsMenuName) {
		return By.xpath("//div[@id='flyoutNavPlaceholder']//a[contains(text(),'" + toolsMenuName + "')]");
	}

	public static By getAccountToolsDD(String toolsMenuName, String dropDown) {
		return By.xpath("//div[@id='flyoutNavPlaceholder']//a[contains(text(),'" + toolsMenuName
				+ "')]/following::ul//li//a[contains(text(),'" + dropDown + "')]");
	}

	// stock
	public static By STOCK = By.xpath("//p[@class='prod-stock']");
	public static By STOCK_PRODUCT_DETAIL_PAGE = By.xpath("//p[@id='product-avalialability-by-warehouse']");

	public static By narrowDown(String category, String option) {
		return By.xpath(
				"//p[text()='" + category + "']//following-sibling::div//span[text()='" + option + "']/..//span");
	}

	public static By UNLIMETED_AVAILABILITY_PRODUCT = By
			.xpath("//p[contains(.,'Unlimited availability')]//ancestor::div[@class='prod-section-container']/h3");
	// account tools
	public static By PRODUCT_GROUP_COI_CSI_RESERVED = By.xpath("//table[@id='prodGroupTable']//tbody//tr[3]//td[1]//a");
	public static By STOCK_ACCOUNT_TOOLS = By.xpath("//table[@id='prodGroupTable']//tbody//tr[3]//td[2]//a");
	public static By STOCK_MINI_WINDOW = By.xpath("//span[@id='showStockCurrencyElement']");

	// Recently Viewed Products
	public static By RECENTLYVIEWD_PRODUCTSLABEL = By.xpath(
			"//div[@class='js-product-recent-views-container recent-product-list clearfix']//h3[contains(text(),'Recently Viewed')]");
	public static By RECENTLYVIEWD_PRODUCTS = By.xpath(
			"//div[@class='js-product-recent-views-container recent-product-list clearfix']//h3[contains(text(),'Recently Viewed')]/following-sibling::div");
	public static By VIEWDETAILS_LINK = By.xpath("//a[contains(text(),'View Details') and @class='detail-link']");

	public static By Clickviewdetailsunderproduct(String product) {
		return By.xpath("//h4[contains(text(),'" + product
				+ "')]/parent::div/a[contains(text(),'View Details') and @class='detail-link']");
	}

	public static By Productinfopage(String product) {
		return By.xpath("//h1//a[contains(text(),'" + product + "')]");
	}

	public static By BACKTORESULTS = By.xpath("//a[@id='back-button' and contains(text(),'Back to Results')]");
	public static By CUSTOMCATALOG = By.xpath("//td[@class='contentline']//a[contains(text(),'Custom Catalog')]");
	public static By DELETEBUTTON_COUSTOMCATALOG = By.xpath(
			"//a[contains(text(),'Custom Catalog')]/parent::td/following-sibling::td//img[@src='/insightweb/assets/en_US/ccms_img/icons/Deep_Delete.png']");
	public static By CREATEBUTTON_COUSTOMCATALOG = By
			.xpath("//td[contains(text(),'Custom Catalog')]/parent::tr/td/div/a[@id='csCancelButton']");

	public static By MANUFACTURERS_CUSTUMCATALOG(String manufacturers) {
		return By.xpath(
				"//span[@id='paymentExpandSpan']/following-sibling::span[contains(text(),'" + manufacturers + "')]");
	}

	public static By DISPLAYEDITEMNAME = By.xpath("//div[@id='js-product-detail-target']/div/following-sibling::h1/a");
	public static By Breadcrumb(String product) {
		return By.xpath("//ul[@aria-label='breadcrumb']/li/a/span[contains(text(),'" + product + "')]");
	}

	public static By BREADCRUMB = By.xpath("//ul[@aria-label='breadcrumb']/li/a/span[contains(text(),'Shop')]");
	public static By AVAILABLETOEXCULDE_BOX = By.xpath("//select[@id='notExcludedManufacturers']");
	public static By CURRENTEXCLUDE_MANUFACTURERS = By.xpath("//select[@id='excludedManufacturers']");
	public static By RIGHTARROW_EXCLUDEMANFACT = By
			.xpath("//div[@id='excludeManufacturer']//a[@class='orange']/following-sibling::a");
	public static By UPDATEBUTTON = By
			.xpath("//div[@id='tab5a']/following-sibling::div/div/div[@class='buttons']/a[@id='csUpdateButton']");
	public static By OVERVIEWTAB = By.xpath("//div[@class='product-details-vtabs']//p/a[contains(text(),'overview')]");

	public static By OVERVIEWTABCONTENTS = By
			.xpath("//div[@id='tpl-specs-overview-target']//table[@class='product-specs']//td");
	public static By PRODUCTDETAILS = By.xpath("//table[@class='product-specs']//tr/td");
	public static By ADDACCESSORIES = By.xpath("//a[@class='section-title' and contains(text(),'Add Accessories')]");
	public static By UPDATEQUNTITY = By.xpath(
			"//input[@id='product-detail-order-number-picker']/parent::div/button[@class='number-picker-up ion-arrow-up-b']");
	public static By NUMBERPICKER = By.xpath("//input[@id='product-detail-order-number-picker']/parent::div");

	public static By PERMISSIONS_UPDATE = By.xpath("//a//span[contains(text(),'Update')]");
	public static By UPDATED_MSG = By.xpath("//span[contains(.,'Permissions Updated Succesfully')]");

	public static By LnameEmailUname(String LnameEmailUname) {
		return By.xpath(
				"//div[@class='c-header__middle']//span[@class='c-header-nav__text  u-truncate' and contains(text(),'User - "
						+ LnameEmailUname + "')]");
	}

	public static By PRICEESTIMATOR = By.xpath("//div[@id='price-estimator']//i");
	public static By PRICEESTIMATOR_POPUP = By.xpath("//div[@id='price-estimator-target-modal']");
	public static By TEXTFIELD_ZIPCODE = By.xpath("//input[@id='priceEstimatorZipCode']");
	public static By ESTIMATE_BUTTON = By.xpath("//input[@class='button tiny' and @value='Estimate']");
	public static By ESTIMATEDTAX_LABEL = By.xpath("//div[contains(text(),'Estimated Taxes:')]");
	public static By ESTIMATEDTAX = By
			.xpath("//div[contains(text(),'Estimated Taxes:')]/following-sibling::div[contains(text(),'USD')]");
	public static By ESTIMATEDSHIPPING_LABEL = By.xpath("//div[contains(text(),'Estimated Shipping')]");
	public static By ESTIMATEDSHIPPING = By
			.xpath("//div[contains(text(),'Estimated Shipping')]/following-sibling::div[contains(text(),'USD')]");
	public static By ESTIMATEDPRICE_LABEL = By.xpath("//div/strong[contains(text(),'Your total price estimate')]");
	public static By ESTIMATEDPRICE = By.xpath(
			"//strong[contains(text(),'Your total price estimate')]/parent::div/following-sibling::div/strong[contains(text(),'USD ')]");
	public static By PRICEESTIMATOR_POPUPCLOSE = By.xpath("//a[@class='close-reveal-modal']");
	public static By RATING_PRODUCTDISPLAY = By.xpath("//a[@class='js-ratings-reviews']");
	public static By REVIEWSTAB_PRODUCTDISPLAY = By
			.xpath("//section[@id='detail-reviews']/p/a[contains(text(),'Reviews')]");
	public static By LEAVEAREVIEW_BUTTON = By.xpath("//button[contains(text(),'Leave a review') and @type='button']");
	public static By REVIEWSUBMISSION_FORM = By.xpath("//div[@id='review-submission-modal']");
	public static By REVIEWSSYAMBOLS = By.xpath(
			"//div[@class='o-grid__item o-grid__item--shrink u-show@tablet']//div[@class='c-star-rating c-star-rating--editable c-star-rating']/label");
	public static By REVIEWTITLE = By.xpath("//input[@class='c-input' and @id='title']");
	public static By REVIEWTEXT = By.xpath("//textarea[@id='reviewText']");
	public static By NICKNAME = By.xpath("//input[@id='userNickName']");
	public static By RECOMNDEDPRODUCT_RADIO = By.xpath("//input[@id='recommended']");
	public static By TERMSANDCONDI = By.xpath("//input[@id='agreedToTermsAndConditions' and @type='checkbox']");
	public static By SUBMIT_BUTTON = By.xpath("//button[contains(text(),'Submit')]");
	public static By REVIEWSERROR_MSG = By.xpath("//span[@class='c-submit-review-modal__form-error-text']");
	public static By SUBMISSIONFORMCLOSE = By.xpath("//button[@class='c-button  c-button--subtle c-modal__close']");
	public static By SUBMISSIONSUCCES_BOX = By
			.xpath("//div[@class='c-modal__content c-submit-review-success-modal__body']");
	public static By SUBMISSIONSUCCES_MSG = By.xpath("//h2[contains(text(),'Thank you for submitting  a review')]");
	public static By CLOSEBUTTON_SUBMISSIONSUCCESMSG = By
			.xpath("//button[@class='c-button  c-button--subtle c-modal__close' and @type='button']");

	// contract

	public static By CONTRACT_TITLE = By.xpath("//td[text()='Contract Title ']//following-sibling::td");
	public static By CONTRACT_NUMBER = By.xpath("//td[text()='Contract Number']//following-sibling::td");
	public static By START_DATE = By.xpath("//td[text()='Start Date']//following-sibling::td");
	public static By CURRENT_END_DATE = By.xpath("//td[text()='Current End Date ']//following-sibling::td");
	public static By ELIGIBLE_CUSTOMERS = By.xpath("//td[text()='Eligible Customers ']//following-sibling::td");
	public static By PAYMENT_TERMS = By.xpath("//td[text()='Payment Terms']//following-sibling::td");
	public static By DELIVERY = By.xpath("//td[text()='Delivery ']//following-sibling::td");
	public static By RETURN_INFO = By.xpath("//td[text()='Return Information ']//following-sibling::td");
	public static By CONTRACT_IN_PRODUCTDETAIL = By.xpath("//div[@class='sewp-prices']");
	
	//recently viewed
	public static By FIRSTPRODUCT_MFRPARTNUM = By.xpath("//div[@id='search-item-0']//p[@class='prod-part-number show-for-medium-up']");
	public static By RECOMENDED_PRODUCT_MPRE_AVAILABLE_PRICE=By.xpath("//h3[text()='Recommended with Printers']//parent::div//section//p//a[text()='More Prices Available']");
	public static By MOST_OFTEN_PURCHASED_PRODUCT=By.xpath("//h3[text()='People who bought also bought']//parent::div//div//section//div[@class='rr-name-wrapper']");
}
