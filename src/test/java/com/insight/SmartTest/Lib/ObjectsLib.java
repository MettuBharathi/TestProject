package com.insight.SmartTest.Lib;

import com.insight.SmartTest.Pages.HomePage;
import com.insight.SmartTest.Pages.ObjectsPage;
import com.insight.accelerators.ActionEngine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.swing.plaf.TableHeaderUI;
import java.util.List;

public class ObjectsLib extends ObjectsPage {

	public boolean verifyAvailabilityOfProductSearchPopup() throws Throwable{
		return isElementVisible(HomePage.PRODUCTSEARCH_HDR,3,"Product Search");
	}
	public void enterKeywordInProductSearchWindow(String keyword)throws Throwable{
		isElementType(HomePage.txtKeywordSearch, keyword, 2,"keyword");
	}
	public void clickOnSearchButtonInProductSearchWindow() throws Throwable{
		isElementClickable(HomePage.SEARCHBUTTONKEYWORDSEARCH,3,"Search Button");
	}
	public void clickOnCrossiConUnderVCColSingleRow() throws Throwable{
		isElementClickable(crossIconUnderVCColoumn,3,"Cross Icon");
	}

	public boolean availabilityOfItemDetailsPopup() throws Throwable {

		return isElementVisible(HomePage.ITEMDETAILSLABEL,2,"ItemDetailsLable popup window");
	}

	public void clickOnAcquireEstimateAndQuote() throws Throwable{
		isElementClickable(btnAcquireEstimateAndQuote,2,"Acquire Estimate And Quote");
	}
	public boolean availablilityOfAcquireEstimateAndQuotewindowpopup()throws Throwable
	{
		return isElementVisible(windowAcquireEstimateAndQuote,2,"AcquireEstimateAndQuoteWindow");
	}

	public void enterEstimateNumberValue(String number) throws Throwable{

		isElementType(txtEstimateNumber,number,2,"Estimate Number");
	}

	public void clickOnOKButtonOKOnAcquireEstimateAndQuote() throws Throwable{
		isElementClickable(btnOKOnAcquireEstimateAndQuote,2,"Acquire Estimate and Quote");
	}
	public void clickOnOKButtonOnWaraningPopup() throws Throwable{
		isElementClickable(btnOKOnWarning,4,"OK Button on warning");
	}
	public void enterSoldTo(String Soldto)throws Throwable{
		typeText(HomePage.SOLDTO_CREATEQUOTE,Soldto,"Sod-To");
		click(HomePage.SEARCH_SOLDTO,"Sold-To Search Button");
	}
	public boolean verifyAvailabilityOfProductSearchButton() throws Throwable{
		return isElementVisible(btnProductSearch,3,"Product ButtonSearch");
	}
	public void closebuttonInProductSearch() throws Throwable{
		isElementClickable(icnProductSearchCloseInPopup,3,"Close product Search icon");
	}
	public boolean clickOnMaterialID() throws Throwable {
		// lblMaterialID
		boolean status = false;
		List<WebElement> materialList = driver.findElements(HomePage.lblMaterialID);
		if (materialList.size() > 0) {
			Actions action = new Actions(driver);
			for (int j =0; j < materialList.size(); j++) {
				materialList.get(j);
				action.doubleClick(materialList.get(j)).build().perform();
				status=true;
				break;
			}
		} else {
			status=false;
			reporter.failureReport("Resutls", "Search results are not found", "", driver);
		}
		return status;
	}



	
	
	
	//Added recently
public boolean verifyAvailabilityOfKeywordSearchTextField() throws Throwable{
		return isElementVisible(HomePage.txtKeywordSearch,1,"Keyword search field");
}
public boolean verifyAvailabilityOfNarrowKeyword() throws Throwable{
		return isElementVisible(txtNarrowByKeyword,3,"Availability of keyword search results");
}

public boolean verifyAvailabilityOfConIcon() throws Throwable{
	return isElementVisible(conIconInLineItems,3,"Availability of Contract Icon in line items");
}

	public boolean clickOnCrossiConUnderConColSingleRow() throws Throwable {
		boolean status = false;
		if (verifyAvailabilityOfConIcon()) {
			List<WebElement> materialList = driver.findElements(icnXIconUnderConCol);
			if (materialList.size() > 0) {
				Actions action = new Actions(driver);
				for (int j = 0; j < materialList.size(); j++) {
					materialList.get(j);
					materialList.get(j).click();
					status = true;
					break;
				}
			} else {
				status = false;
				reporter.failureReport("Resutls", "Search results are not found", "", driver);
			}

		}
		return status;
	}

	public boolean clickOnContactIDByPassingValueFromExcel(String contract) throws Throwable {
		boolean status = false;
		if (verifyAvailabilityOfConIcon()) {
			List<WebElement> materialList = driver.findElements(listContractIDFromLineDetailsPopup);
			if (materialList.size() > 0) {
				Actions action = new Actions(driver);
				for (int j = 0; j < materialList.size(); j++) {
					String contractID= materialList.get(j).getText();
					if(contractID.equals(contract)){
						materialList.get(j).click();
						status = true;
						break;
					}

				}
			} else {
				status = false;
				reporter.failureReport("Resutls", "Search results are not found", "", driver);
			}

		}
		return status;
	}

	public void clickOnDoneButtonInContractsTab() throws Throwable{
		isElementClickable(HomePage.CONTRACTTAB_DONEBTN,2,"Done Button");

	}
	public String getQuoteNumberValue() throws Throwable{
		return getAttributeValue(HomePage.GET_QUOTENMBR,"value");
	}
    /*
     * This method is to Click on Tabs in ItemDetails PopUp
     */
    public String getSellPriceFromContract(String contractid) throws Throwable {
        return getText(getcontractsellingPrice(contractid),"Selling Price of ContractId"+contractid+"");
    }

    public String getContractPricingFromTable(String text) throws Throwable{
    	return getText(HomePage.pricing_ValueClmn(text),"Text Value");
	}
	public String selectproductinPricingTable(String text)throws Throwable{
		String data="";
		// ((JavascriptExecutor)driver).executeScript("window.scrollBy(2000,0)");

		List<WebElement> elem = getWebElementList(HomePage.PricingTabTable, "Pricing List");
		int size = elem.size();
		if(size>0){
			for(int i=1;i<size;i++) {
				elem.get(i).getText();
				if(text.equalsIgnoreCase(elem.get(i).getText())){
					data = driver.findElement(HomePage.pricing_ValueClmn(text)).getText();
					break;
				}
			}
		}
		return data;
	}

}
