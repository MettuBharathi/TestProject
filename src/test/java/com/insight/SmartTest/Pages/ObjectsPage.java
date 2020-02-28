package com.insight.SmartTest.Pages;

import com.insight.accelerators.ActionEngine;
import org.openqa.selenium.By;

public class ObjectsPage extends ActionEngine
{
	public static By txtLoginName = By.cssSelector("input[name='userName']");
	public static By txtPassword = By.cssSelector("input[name='password']" );
	public static By btnSubmit = By.cssSelector("button[type='submit']");
	public static By crossIconUnderVCColoumn = By.cssSelector("[class='fa fa-times text-red']");
	public static By btnAcquireEstimateAndQuote = By.xpath("//*[text()='Update']//following-sibling::button[text()='Acquire Estimate/Quote']");
	public static By windowAcquireEstimateAndQuote = By.xpath("//*[@class='panel-title' and text()='Acquire Estimate/Quote']");
	public static By txtEstimateNumber = By.cssSelector("input[name='estimateNumber']");
	public static By btnOKOnAcquireEstimateAndQuote = By.xpath("//button[@type='submit' and text()='OK']");
	public static By btnOKOnWarning = By.xpath("//*[@class='panel-body panel-body-sm']//button[text()='Ok']");
	public static By btnProductSearch = By.xpath("//*[@class='btn btn-alternate pull-right' and text()='Product Search']");
	public static By icnProductSearchCloseInPopup = By.xpath("//h3[text()='Product Search']//..//button[@class='btn btn-hollow pull-right']");

	
	
	///Yesterday added 

	
	public static By txtNarrowByKeyword = By.xpath("//*[text()='Results Parameters']");
	public static By conIconInLineItems = By.xpath("(//*[@colid='con']//div//span[text()='X'])[1]");
	public static By icnXIconUnderConCol = By.xpath("//*[@colid='con']//div//span[text()='X']");
	public static By listContractIDFromLineDetailsPopup = By.xpath("//div[@colid='contractId' and @tabindex=-1]");
	public static By lblSellingContractPriceValue = By.xpath("//div[@colid='lowestCost' and @tabindex=-1]//following-sibling::div[@colid='price']");
	public static By lblPricingValueForTypes = By.xpath("//*[@colid='1']");
	public static By getcontractsellingPrice(String contractid)
	{
		return By.xpath("//div[@colid='contractId' and contains(text(),'"+contractid+"')]//following-sibling::div[@colid='price']");
	}
	public static By getPricingDetailsForSelectedLinetype(String type)
	{
		return By.xpath("//div[contains(text(),'"+type+"')]//following-sibling::div[@colid='1']");
	}

}
