package com.insight.ObjRepo;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class ChinaObj extends ActionEngine{
	public static By COUNTRY_FLAG_DD=By.xpath("//ul[@class='o-list-inline  c-header-top__list']//li//button[@id='headerLocaleDropdown']/*[@class='c-icon c-icon--dropdown']");
	  public static By CHOOSE_COUNTRY=By.xpath("//select[@name='selectCountry']");
	 
	  public static By CHOOSE_LANGUAGE_DD=By.xpath("//select[@name='selectLanguage']");
	  public static By GO=By.xpath("//button[@type='submit']");
	  public static By getCountryFlag(String flag){
			return By.xpath("//li//button[@id='headerLocaleDropdown']/*[name()='svg']/*[name()='use' and @*='#flag-"+flag+"']");
		}
	  public static By COMPARE_PRODUCTS=By.xpath("//table[@class='compare-products-table responsive']//td//a//img");
	  public static By ADD_PART_IN_COMPARE_PRODUCTS_PAGE=By.xpath("//input[@id='js-add-material-id']");
	  
	  /** To verify part number in compare product list page **/
	  public static By getpartNumberOnCompareProductsPage(String partNum){
	      return By.xpath("//tr//th[contains(text(),'Manufacturer Part Number')]/following-sibling::td[contains(text(),'"+partNum+"')]");
	  }
	  
	  /** To verify part number in compare product list page **/
	  public static By getpartNumberOnCompareProductsPageForFrench(String partNum){
	      return By.xpath("//tr//th[contains(text(),'# de pièce du fabricant')]/following-sibling::td[contains(text(),'"+partNum+"')]");
	  }
	  
		
	  public static By getLanguangeOption(String language){
		  return By.xpath("//select[@name='selectLanguage']//option[@value='"+language+"']"); // select by value
	  }
			
}
