package com.insight.Lib;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.insight.ObjRepo.ChinaObj;
import com.insight.ObjRepo.productsDisplayInfoObj;

public class ChinaLib extends ChinaObj{
	
  /**
   * This method is to select the country and language from the DD
   * @param country
   * @param language
   * @throws Throwable
   */
	public void selectLanguageOnHomePage(String country,String language) throws Throwable{
	 waitForVisibilityOfElement(COUNTRY_FLAG_DD,"country drop down");
	  click(COUNTRY_FLAG_DD, "flag selection drop down");
	  click(CHOOSE_COUNTRY, "Country");
	  selectByVisibleText(CHOOSE_COUNTRY,country , "country selection");
	  click(CHOOSE_LANGUAGE_DD, "Language");
	  click(getLanguangeOption(language),"Language");
	  click(GO, "Go button");
  }
	/**
	 * Method is to verify the filter selected displayed on the search results page
	 * @param manufacturer
	 * @throws Throwable
	 */
	public void verifyManufacturerOnSearchResultsPage(String manufacturer) throws Throwable{
		waitForVisibilityOfElement(productsDisplayInfoObj.FIRST_PROD_NAME, "First product description");
		String prodName=getText(productsDisplayInfoObj.FIRST_PROD_NAME, "First prod Description");
		if (prodName.contains(manufacturer.replace("INC", ""))) {
			reporter.SuccessReport("Validate the results narrow by Manufacturer", "Expected Manufacturer Exists", "");
		}else{
			reporter.failureReport("Validate the results narrow by Manufacturer", "Expected Manufacturer does not Exists", "");
		}
	}
	
	/**
	 * Method is to verify the products in the compare products page
	 * @param items
	 * @throws Throwable
	 */
	public void verifyCompareProductsPage(int items) throws Throwable{
		List<WebElement> elements=driver.findElements(COMPARE_PRODUCTS);
		int size=elements.size();
		if(items==size){
			reporter.SuccessReport("Verify Products in Product Compare Page", "Products are added to Compare Page", "");
		}else{
			reporter.failureReport("Verify Products in Product Compare Page", "Products are not added to Compare Page", "");
		}
	}
	
	/**
	 * Method is to add new part to the compare product list page
	 * @param partNum
	 * @throws Throwable
	 */
	public void addAnotherPartInCompareProductsPage(String partNum) throws Throwable{
		WebElement element = driver.findElement(ADD_PART_IN_COMPARE_PRODUCTS_PAGE);
		safeClearAndTypeAndClickEnter(element, partNum);
	}
	
	/**
	 * Method is to verify the new product added in the compare product list with french
	 * @param partNum
	 * @throws Throwable 
	 */
	public void verifyPartNumberAddedOnFrenchCompareProductListPage(String partNum) throws Throwable{
		if (isElementPresent(getpartNumberOnCompareProductsPageForFrench(partNum), "part number")) {
			reporter.SuccessReport("Verify new part added to Product Compare list","new part added to Product Compare list", "");
		}else{
			reporter.failureReport("Verify new part added to Product Compare list","new part does not added to Product Compare list", "");
		}
	}
	
	/**
	 * Method is to verify the new product added in the compare product list
	 * @param partNum
	 * @throws Throwable
	 */
	public void verifyPartNumberAddedInCompareProductListPage(String partNum) throws Throwable{
		if (isElementPresent(getpartNumberOnCompareProductsPage(partNum), "part number")) {
			reporter.SuccessReport("Verify new part added to Product Compare list","new part added to Product Compare list", "");
		}else{
			reporter.failureReport("Verify new part added to Product Compare list","new part does not added to Product Compare list", "");
		}
	}
	
}
