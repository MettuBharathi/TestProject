package com.insight.Lib;

import java.util.List;

import org.mortbay.log.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.insight.ObjRepo.CanadaObj;
import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.CommonObj;
import com.insight.ObjRepo.LineLevelInfoObj;
import com.insight.ObjRepo.OrderObj;
import com.insight.ObjRepo.SewpObj;
import com.insight.ObjRepo.productsDisplayInfoObj;
import com.insight.utilities.DynamicTestDataGenerator;

public class LineLevelInfoLib extends LineLevelInfoObj{
	CommonLib commonLib = new CommonLib();
	
	
	/**
	 * This method is to enter the ContactName,ContactNumber,customerQuote and email in the LLI section
	 * @param email
	 * @param ContactName
	 * @param ContactNumber
	 * @param customerQuote
	 * @param partNum
	 * @throws Throwable
	 */
	public void enterLineLevelInfo(String email,String ContactName,String ContactNumber, String customerQuote,String partNum) throws Throwable{
		
		clearData(getContactEmailLLInfo(partNum)); 
		type(getContactEmailLLInfo(partNum),email,"Email");
		clearData(getContactNameInLLInfo(partNum)); 
		type(getContactNameInLLInfo(partNum),ContactName,"Contact Name");
		 type(getContactPhoneInLLInfo(partNum),ContactNumber,"Contact Number");
		 type(getCutomerQuoteInLLInfo(partNum),customerQuote,"customer Quote");
	}
	
	/**
	 * This method is to click on Copy To All link
	 * @throws Throwable 
	 * 
	 */
	public void clickCopyToAllLink(String partNum) throws Throwable{
		
		if(isElementPresent(getCopyToAllLink(partNum),"Copy to all ")){
			click(getCopyToAllLink(partNum), "Copy to all ");
		}else{
			reporter.failureReport("verify copy to all link exists", "Copy to all link does not exists", "",driver);
		}
	}

	/**
	 * This method is to get the email in Line level info
	 * @param partNum
	 * @return
	 * @throws Throwable
	 */
	public String getContactEmail(String partNum) throws Throwable{
		return getAttributeByValue(getContactEmailLLInfo(partNum), "email");
	}
	
	/**
	 * This method is to get the contact name in Line level info
	 * @param partNum
	 * @return
	 * @throws Throwable
	 */
	public String getContactName(String partNum) throws Throwable{
		return getAttributeByValue(getContactNameInLLInfo(partNum), "contact name");
	}
	
	/**
	 * This method is to get the contact number in Line level info
	 * @param partNum
	 * @return
	 * @throws Throwable
	 */
	public String getContactPhoneNumber(String partNum) throws Throwable{
		return getAttributeByValue(getContactPhoneInLLInfo(partNum), "contact phone number");
	}
	
	/**
	 * This method is to get the customer Quote  in Line level info
	 * @param partNum
	 * @return
	 * @throws Throwable
	 */
	public String getCustomerQuote(String partNum) throws Throwable{
		return getAttributeByValue(getCutomerQuoteInLLInfo(partNum), "customer Quote");
	}
	
	/**
	 * Method is to click on the clear link
	 * @param partNum
	 * @throws Throwable
	 */
	public void clickClearLink(String partNum) throws Throwable{
		click(getClearLink(partNum), "part number");
	}
	
	/**
	 * This method is to verify the unfilled contact email error message 
	 * @throws Throwable
	 */
	public void verifyEmailRequiredFieldsErrorMessage() throws Throwable{
		if(isElementPresent(EMAIL_REQUIREMENT_ERROR_MSG,"EMAIL REQUIREMENT ERROR MSG")){
			reporter.SuccessReport("Verify email requirement error message", "Email required error message exists","");
		}else{
			reporter.failureReport("Verify email requirement error message", "Email required error message does not exists","",driver);
		}
	}
	
	/**
	 * Method is to verify SOFTWARE AGREEMENT prorated Price Message On Search ResultsPage
	 * @throws Throwable
	 */
	public void verifyProratedPriceMessageOnSearchResultsPage() throws Throwable{
		if(isElementPresent(PRORATED_PRICE_MSG_SEARCH_RESULTS, "prorated price message")){
			reporter.SuccessReport("verify SOFTWARE AGREEMENT Prorated Price Message On Search Results Page", "Message exists", "");
		}else{
			reporter.failureReport("verify SOFTWARE AGREEMENT Prorated Price Message On Search Results Page", "Message does not exists", "",driver);
		}
	}
	
	/**
	 * Method is to verify whether the account tool options are displayed or not
	 * @param toolsMenuName
	 * @param dropDown
	 * @throws Throwable
	 */
	public void clickOnAccountToolsAndVerifyOptionsnotDisplayed(String toolsMenuName, String dropDown) throws Throwable{
		 click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		   click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		   if(isElementNotPresent(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "account tools")){
			   reporter.SuccessReport("Verify options displayed", "Account tools aoptions not displayed","");
		   }else{
			   reporter.failureReport("Verify options displayed", "Account tools aoptions  displayed","",driver); 
		   }	   
	}
	
	/**
	 * This method is to verify the RP_HDL_Txt entered on PO page
	 * @param RP_HDL_Txt
	 * @throws Throwable
	 */
	public void verifyRP_HDL_TxtOnPlaceOrderPage(String RP_HDL_Txt) throws Throwable{
		if(isElementPresent(getRP_HDL_TxtInPlaceOrderPage(RP_HDL_Txt),"RP_HDL_Txt")){
			reporter.SuccessReport("Verify RP_HDL_Txt On Place Order Page", "RP_HDL_Txt On Place Order Page is present", "");
			
		}else{
			reporter.failureReport("Verify RP_HDL_Txt On Place Order Page", "RP_HDL_Txt On Place Order Page is not present", "",driver);
		}
	}

	/**
	 * This method is to verify the RP_LNL_Txt entered 
	 * @param RP_HDL_Txt
	 * @throws Throwable
	 */
	public void verifyRP_LNL_TxtOnPlaceOrderPage(String RP_LNL_Txt) throws Throwable{
		if(isElementPresent(getRP_LNL_TxtInPlaceOrderpage(RP_LNL_Txt),"RP_HDL_Txt")){
			reporter.SuccessReport("Verify RP_LNL_Txt On Place Order Page", "RP_LNL_Txt On Place Order Page is present", "");
			
		}else{
			reporter.failureReport("Verify RP_LNL_Txt On Place Order Page", "RP_LNL_Txt On Place Order Page is not present", "",driver);
		}
	}
	
	/**
	 * This method is to verify the RP_HDL_Txt entered on receipt page
	 * @param RP_HDL_Txt
	 * @throws Throwable
	 */
	public void verifyRP_HDL_TxtOnReceiptPage(String RP_HDL_Txt) throws Throwable{
		if(isElementPresent(getRP_HDL_TxtOnReceiptPage(RP_HDL_Txt),"RP_HDL_Txt")){
			reporter.SuccessReport("Verify RP_HDL_Txt On Receipt Page", "RP_HDL_Txt On Receipt Page is present", "");
			
		}else{
			reporter.failureReport("Verify RP_HDL_Txt On Receipt Page", "RP_HDL_Txt OnReceipt Page is not present", "",driver);
		}
	}
	/**
	 * This method is to verify the RP_LNL_Txt entered  on Receipt page
	 * @param RP_HDL_Txt
	 * @throws Throwable
	 */
	public void verifyRP_LNL_TxtOnReceiptPage(String RP_LNL_Txt) throws Throwable{
		if(isElementPresent(getRP_LNL_TxtOnReceiptPage(RP_LNL_Txt),"RP_HDL_Txt")){
			reporter.SuccessReport("Verify RP_LNL_Txt On Receipt Page", "RP_LNL_Txt On Receipt Page is present", "");
			
		}else{
			reporter.failureReport("Verify RP_LNL_Txt On Receipt Page", "RP_LNL_Txt On PReceipt Page is not present", "",driver);
		}
	}
	
	/**
	 * This method is to click close icon on Welcome popup
	 * @throws Throwable
	 */
	public void clickCloseIconOnWelcomePopUp() throws Throwable{
		click(CommonObj.CLOSE, "Close icon");
	}
	
	/**
	 * Method is to verify the Welcome insight beta popup
	 * @throws Throwable
	 */
	public void verifyWelcomeToInsight() throws Throwable{
		if(isElementPresent(WELCOME_INSIGHT_POPUP, "Welcome insight beta popup")){
			reporter.SuccessReport("Verify Welcome insight beta popup", "Welcome insight beta popup is displayed", "");
		}else{
			reporter.failureReport("Verify Welcome insight beta popup", "Welcome insight beta popup is not displayed", "",driver);
		}
	}
	
	/**
	 * Method is to click on the shipping address save button
	 * @throws Throwable
	 */
	public void acceptShippingAddressVerification() throws Throwable{
		Thread.sleep(2000);
		if(isElementPresent(ADDRESS_VALIDATION_SAVE_BTN, "shipping address validation save")){
			click(ADDRESS_VALIDATION_SAVE_BTN, "save button");
		}
		Thread.sleep(2000);
	}
	
	public void checkSameAsShippingAddress() throws Throwable{
		click(SAME_AS_SHIPPING_ADD_CHECKBOX, "SAME AS SHIPPING ADDRESS CHECKBOX");
	}
	
	/**
	 * Enter email address in create account page
	 * @throws Throwable
	 */
	public String enterEmailInCreateAccount() throws Throwable{
		waitForVisibilityOfElement(SewpObj.EMAIL_ADDRESS, "Email Address");
		String email ="UFT"+DynamicTestDataGenerator.generateRandomEmail();
		typeText(SewpObj.EMAIL_ADDRESS, email, "Email Address");
	    return email;
		
	}
	
	/**
	 * Enter password in create account page
	 * @throws Throwable
	 */
	public String enterpasswordInCreateAccount() throws Throwable{
		String password=DynamicTestDataGenerator.generateRandomPassword();
		typeText(SewpObj.PWD, password , "Password");
		typeText(SewpObj.CRM_PWD,password, "Confirm Password");
		return password;
	}
	
	/**
	 * Method is to verify and click on Line level information optional link
	 * @throws Throwable
	 */
	public void clickOnLinelevelInfoOptionalLink() throws Throwable{
		if(isElementPresent(OrderObj.LINE_LEVEL_INFO, "Line level information link")){
			reporter.SuccessReport("verify Line level information optional link", "Line level information link exists", "");
			click(OrderObj.LINE_LEVEL_INFO, "Line level information link");
		}else{
			reporter.failureReport("verify Line level information optional link", "Line level information link does not exists", "",driver);
		}
	}
	
	/**
	 * This method is to verify the contract specific info is present in place order page
	 * @throws Throwable
	 */
	public void verifyContractSpecificInfoOnPlaceOrderPage() throws Throwable{
		if(isElementPresent(CONTRACT_SPECIFIC_INFO_LABEL, "contract spcific info")){
			reporter.SuccessReport("Verify contract specific info", "contract specific info is present in place order page", "");
		}else{
			reporter.failureReport("Verify contract specific info", "contract specific info is not present in place order page", "",driver);
		}
	}
	
	/**
	 * This method is to click on edit line level info on place order 
	 * @throws Throwable
	 */
	public void editLinelevelInfoOnPlaceOrderPage() throws Throwable{
		click(EDIT_LINE_LEVEL_INFO, "Edit line level info");
	}
	
	/**
	 * Method is to verify the Order And Item Info BreadCrumb
	 * @throws Throwable
	 */
	public void verifyOrderAndItemInfoBreadCrumb() throws Throwable{
		if(isElementPresent(OrderObj.ORDER_ITEM_INFO_LABEl, "Order And Item Info BreadCrumb")){
			reporter.SuccessReport("Verify Order And Item Info BreadCrumb", "Order And Item Info BreadCrumb is present", "");
		}else{
			reporter.failureReport("Verify Order And Item Info BreadCrumb", "Order And Item Info BreadCrumb is not present ", "",driver);
		}
	}
	
	/**
	 * Method is to enter the RP_LNL_Date in line level info
	 * @throws Throwable
	 */
	public void enterRP_LNL_Date() throws Throwable{
		if(isElementPresent(RP_LNL_DATE_PICKER, "RP_LNL_DATE_PICKER")){
			click(RP_LNL_DATE_PICKER, "Calnder");
			click(RP_LNL_DATE_TODAY_DATE, "RP_LNL_DATE_TODAY_DATE", driver);
		}else{
			reporter.failureReport("verify RP_LNL_DATE_PICKER", "RP_LNL_DATE_PICKER is not visible", "", driver);
		}
	}
	
	/**
	 * Method is to select RP_LNL_Lst_DD in LLI
	 * @param option
	 * @throws Throwable 
	 */
	public void selectRP_LNL_Lst(String option) throws Throwable{
		if(isElementPresent(RP_LNL_Lst_DD,"RP_LNL_Lst")){
			selectByVisibleText(RP_LNL_Lst_DD, option, "RP_LNL_Lst");
		}else{
			reporter.failureReport(" verify RP_LNL_Lst_DD", "RP_LNL_Lst_DD is not present", option, driver);
		}
	}
	
	/**
	 * 
	 * @param partNum
	 * @param email
	 * @throws Throwable 
	 */
	public void enterRP_LNL_Email(String partNum,String email) throws Throwable{
		if(isElementPresent(getContactEmailLLInfo(partNum), "RP_LNL_Email")){
			type(getContactEmailLLInfo(partNum),email,"Email");
		}else{
			reporter.failureReport("verify RP_LNL_Email", "RP_LNL_Email is not present for "+partNum, "", driver);
		}
	}
	
	/**
	 *  click on Line level information optional link
	 * @param partNum
	 * @throws Throwable
	 */
	public void clickOnLineLevelOptionalLinkByPartNum(String partNum) throws Throwable{
		if(isElementPresent(getLineLevelOptionalLink(partNum), "Line level optional link")){
			click(getLineLevelOptionalLink(partNum),"Line level optional link for "+partNum);
		}else{
			reporter.failureReport("verify line level optional link present", "Line level info optional link does not exists for "+partNum, "", driver);
		}
	}
	
	/**
	 * This method  is to verify the Line Level Info Optional link
	 * @param partNum
	 * @throws Throwable
	 */
	public void verifyLineLevelInfoOptionalIsPresent(String partNum) throws Throwable{
		if(isElementPresent(getLineLevelOptionalLink(partNum), "Line level optional link")){
			reporter.SuccessReport("verify line level optional link present", "Line level info optional link exists for "+partNum, "");
			
		}else{
			reporter.failureReport("verify line level optional link present", "Line level info optional link does not exists for "+partNum, "", driver);
		}
	}
	
	/**
	 * Method is to enter the rP_LNL_Txt 
	 * @param rP_LNL_Txt
	 * @throws Throwable
	 */
	public void enter_rP_LNL_Txt(String rP_LNL_Txt) throws Throwable{
		if(isElementPresent(OrderObj.RP_LNL_Txt, "rP_LNL_Txt")){
			type(OrderObj.RP_LNL_Txt, rP_LNL_Txt, "RP_LNL_Txt text box");
		}else{
			reporter.failureReport("Verify rP_LNL_Txt text", "rP_LNL_Txt is nt present", "", driver);
		}
	}
	
	/**
	 * Method is to get the RP_LNL_Txt
	 * @return
	 * @throws Throwable
	 */
	public String getRP_LNL_Txt(String partNum) throws Throwable{
		return getAttributeByValue(getgetRP_LNL_TxtByPartNum(partNum), "RP_LNL_Txt");
	}
	
	/**
	 * This method is to get the RP_LNL_DATE_PICKER
	 * @return
	 * @throws Throwable 
	 */
	public String getRP_LNL_DATE_PICKER(String partNum) throws Throwable{
		return getAttributeByValue(getRP_LNL_TodayDate(partNum), "RP_LNL_DATE_PICKER");
	}
	
	/**
	 * This method is to verify the CountryOfUsage drop down in the LLI
	 * @param partNum
	 * @throws Throwable
	 */
	public void verifyCountryOfUsageDD(String partNum) throws Throwable{
		if(isElementPresent(getCountryOfUsageDD(partNum), "CountryOfUsage")){
			reporter.SuccessReport("Verify CountryOfUsage DropDown", "Country Of Usage DropDown is present", "");
		}else{
			reporter.failureReport("Verify CountryOfUsage DropDown", "Country Of Usage DropDown is not present", "");
		}
	}
	
	/**
	 * Method is to get the stock number in cart
	 * @return
	 * @throws Throwable 
	 */
	public String getStockNumberInCart() throws Throwable{
		return getText(CanadaObj.STOCK_CART, "stock cart");
	}
	
	/**
	 * 
	 * @throws Throwable
	 */
	public String[] verifyTheStockNumbersInSearchResultsPageAndSelectProduct() throws Throwable{
		
		String prodnum=null;
		String stockNumber=null;
		List<WebElement> myList = driver.findElements(productsDisplayInfoObj.STOCK_IN_SEARCH_RESULTS);
		if(myList.size()>0){
		for(i=0;i<myList.size();i++){
			stockNumber=getText(productsDisplayInfoObj.getProductStockNumber(i), "Stock Number").replace("in stock", "").trim();
			if(stockNumber.isEmpty() || stockNumber==null){
				
				reporter.failureReport("Verify the stock number for products displayed", "Stok number is empty or null for "+i+ " product","",driver);
			}else{
				reporter.SuccessReport("Verify the stock number for products displayed", "Stock number is displayed for " +i+ " Product as :"+stockNumber, "");
				prodnum=getText(productsDisplayInfoObj.getPartNumber(i), "product name");
				click(productsDisplayInfoObj.getproductName(i),"product stock");
			}
			break;
		  }
		}	
		else{
		     reporter.failureReport("Verify the stock number for products displayed","Stock numbers are not displayed","",driver);
	}
		return  new String[]{ prodnum, stockNumber};
 }
	
	/**
	 * Method is to verify Smart Trackers Exists and Sorted
	 * @param partNum
	 * @throws Throwable
	 */
	public void verifyInputFieldsInLLI(String partNum,String email,String rP_LNL_Txt,String rP_LNL_Lst_DD) throws Throwable{
		List<WebElement> element = driver.findElements(getTotalInputFieldsInLLI(partNum));
		if(element.size()>0){
			if(isElementPresent(getContactEmailLLInfo(partNum),"email contact") && !getAttributeByValue(getContactEmailLLInfo(partNum),"email contact").isEmpty() && getAttributeByValue(getContactEmailLLInfo(partNum),"email contact").equals(email)){
				reporter.SuccessReport("Verify Smart Trakers email contact Exists and Sorted", "Smart Trakers email contact Exists and Sorted for "+partNum+" part number", "");
			}else{
				reporter.failureReport("Verify Smart Trakers email contact Exists and Sorted", "Smart Trakers email contact does not Exists or not Sorted in order for "+partNum+" part number", "", driver);
			  }
			
			if(isElementPresent(getgetRP_LNL_TxtByPartNum(partNum),"RP_LNL_Txt") && !getAttributeByValue(getgetRP_LNL_TxtByPartNum(partNum),"RP_LNL_Txt").isEmpty() && getAttributeByValue(getgetRP_LNL_TxtByPartNum(partNum),"RP_LNL_Txt").equals(rP_LNL_Txt)){
				reporter.SuccessReport("Verify Smart Trakers RP_LNL_Txt Exists and Sorted", "Smart Trakers RP_LNL_Txt Exists and Sorted for "+partNum+" part number", "");
			}else{
				reporter.failureReport("Verify Smart Trakers RP_LNL_Txt Exists and Sorted", "Smart Trakers RP_LNL_Txt does not  Exists or not Sorted in order for "+partNum+" part number", "", driver);
			  }
			
			if(isElementPresent(getRP_LNL_TodayDate(partNum),"RP_LNL_TODAY_DATE") && !getAttributeByValue(getRP_LNL_TodayDate(partNum),"RP_LNL_TODAY_DATE").isEmpty() && getAttributeByValue(getRP_LNL_TodayDate(partNum),"RP_LNL_TODAY_DATE").equals(getCurrentDateTime("dd-MMM-yyyy"))){
				reporter.SuccessReport("Verify Smart Trakers RP_LNL_TODAY_DATE Exists and Sorted", "Smart Trakers RP_LNL_TODAY_DATE Exists and Sorted for "+partNum+" part number", "");
			}else{
				reporter.failureReport("Verify Smart Trakers RP_LNL_TODAY_DATE Exists and Sorted", "Smart Trakers RP_LNL_TODAY_DATE does not Exists or not Sorted in order for "+partNum+" part number", "", driver);
			  }
			
			if(isElementPresent(RP_LNL_Lst_DD,"RP_LNL_Lst_DD") && !getAttributeByValue(RP_LNL_Lst_DD,"RP_LNL_Lst_DD").isEmpty() && getAttributeByValue(RP_LNL_Lst_DD,"RP_LNL_Lst_DD").equals(rP_LNL_Lst_DD)){
				reporter.SuccessReport("Verify Smart Trakers RP_LNL_Lst_DD Exists and Sorted", "Smart Trakers RP_LNL_Lst_DD Exists and Sorted for "+partNum+" part number", "");
			}else{
				reporter.failureReport("Verify Smart Trakers RP_LNL_Lst_DD Exists and Sorted", "Smart Trakers RP_LNL_Lst_DD does not Exists or not Sorted in order for "+partNum+" part number", "", driver);
			  }
			}
			
			}
		
	/**
	 * Method is to add items from product group
	 * @throws InterruptedException
	 * @throws Throwable
	 */
	public void addItemsInCompanyStandart(String size) throws InterruptedException, Throwable{
		int length=Integer.valueOf(size);
		if(isElementPresent(CommonObj.ADD_ITEMS_CHECKBOX, "Add items check box")){
			List<WebElement> myList1=driver.findElements(CommonObj.ADD_ITEMS_CHECKBOX);
			for (int j = 0; j < length; j++) {
				myList1.get(j).click();
				Thread.sleep(3000);
	 		}
	    }
	}
	
	/**
	 * This method is to make sure that the line level information section is not present
	 * @throws Throwable
	 */
	public void verifyLineLevelInfoExists(String status) throws Throwable{
		
		switch (status) {
		case "Available":
			if(isElementPresent(LINE_LEVEL_INO_LABEL, "Line level info")){
				reporter.SuccessReport("Verify line levle info present", "Line level information is present", "");
			}else{
				reporter.failureReport("Verify line levle info present", "Line level information is not present", "", driver);
			}
			break;
			
		case "NotAvailable":
			if(isElementNotPresent(LINE_LEVEL_INO_LABEL, "Line level info")){
				reporter.SuccessReport("Verify line levle info present", "Line level information is not present", "");
			}else{
				reporter.failureReport("Verify line levle info present", "Line level information is present", "", driver);
			}
		
		default:
			break;
		}
    }
	
	/**
	 * Method is to select the diversity Partner 
	 * @param diversityPartner
	 * @throws Throwable
	 */
	public void selectDiversityPartner(String diversityPartner,String partNum) throws Throwable{
		if(isElementPresent(DiversityPartner(partNum), "DIVERSITY PARTNER")){
			click(DiversityPartner(partNum), "Diversity partner");
			selectByVisibleText(DiversityPartner(partNum), diversityPartner, "diversityPartner");
		}else{
			reporter.failureReport("Verify diversity Partner exists", "diversity Partner option does not exists", "",driver);
		}
	}
	/**
	 * Method is to verify the selected diversity Partner exists or not
	 * @param diversityPartner
	 * @throws Throwable
	 */
	public void verifyDiversityPartnerexists(String diversityPartner,String partNum) throws Throwable{
		if(isElementPresent(DiversityPartner(partNum), "DIVERSITY PARTNER")){
			String option =getSelectedDropdownOption(DiversityPartner(partNum));
			assertTextStringMatching(option, diversityPartner);
			reporter.SuccessReport("Verify diversity Partner exists", "diversity Partner selected option exists", "");
		}else{
			reporter.failureReport("Verify diversity Partner exists", "diversity Partner option does not exists", "",driver);
		}
	}
	
	/**
	 * get reporting field 4.
	 * @return
	 * @throws Throwable
	 */
	public String getReportingField4() throws Throwable{
		return getAttributeByValue(OrderObj.REPORTING_FIELD_4, "REPORTING FIELD 4");
	}
	
	/**
	 * get reporting field 5.
	 * @return
	 * @throws Throwable
	 */
	public String getReportingField5() throws Throwable{
		return getAttributeByValue(OrderObj.REPORTING_FIELD_5, "REPORTING FIELD 5");
	}
	
	/**
	 * get reporting field 6.
	 * @return
	 * @throws Throwable
	 */
	public String getReportingField6() throws Throwable{
		return getAttributeByValue(OrderObj.REPORTING_FIELD_6, "REPORTING FIELD 6");
	}
	
	/**
	 * This method is to verify the warranty link present in the place order page
	 * @param status
	 * @throws Throwable
	 */
	public void verifywarrantyOnPlaceOrderPage(String status ) throws Throwable{
		switch (status) {
		case "Available":
			if(isElementPresent(OrderObj.WARRANTY_LABEL_ON_PLACE_ORDER, "waranty link")){
				reporter.SuccessReport("Verify warranty link on place order page", "Warranty link exists", "");
			}else{
				reporter.failureReport("Verify warranty link on place order page", "Warranty link does not exists", "", driver);
			}
			break;
			
		case "NotAvailable":
			if(isElementNotPresent(OrderObj.WARRANTY_LABEL_ON_PLACE_ORDER, "waranty link")){
				reporter.SuccessReport("Verify warranty link on place order page", "Warranty link does not exists", "");
			}else{
				reporter.failureReport("Verify warranty link on place order page", "Warranty link exists", "", driver);
			}
		
		default:
			break;
		}
	}
	
	/**
	 * Method is to verify the warranty desc on cart page
	 * 
	 * @throws Throwable
	 * 
	 */
	public void verifyWarrantyDescOncartpage(String status) throws Throwable {

		switch (status) {
		case "ON":
			if (isElementPresent(OrderObj.WARRANTY_ITEM_DESC_ON_CART_SCREEN, "waranty link")) {
				reporter.SuccessReport("Verify warranty link desc on cart page", "Warranty desc exists", "");
			} else {
				reporter.failureReport("Verify warranty link desc on cart page", "Warranty desc does not exists", "",
						driver);
			}
			break;

		case "OFF":
			if (isElementNotPresent(OrderObj.WARRANTY_ITEM_DESC_ON_CART_SCREEN, "waranty link")) {
				reporter.SuccessReport("Verify warranty link desc on cart page", "Warranty desc does not exists", "");
			} else {
				reporter.failureReport("Verify warranty link desc on cart page", "Warranty desc exists", "", driver);
			}

		default:
			break;
		}
	}
	
	/**
	 * Method is to click on the Split Into Individual Lines link
	 * @throws Throwable
	 */
	public void clickOnSplitIntoIndividualLines() throws Throwable{
		if(isElementPresent(SPLIT_INTO_INDIVIDUAL_LINES_LINK,"Split into lines link")){
			clickUntil(SPLIT_INTO_INDIVIDUAL_LINES_LINK, CommonObj.SPINNER_IMAGE, "Split into lines link");
		}else{
			reporter.failureReport("Verify Split into link exists", "Split into link does not exists", "", driver);
		}
	}
	
	/**
	 * method is to verify split line level items
	 * @throws Throwable
	 */
	public void verifySplitLineItemsLabel() throws Throwable{
		if(isElementPresent(TWO_LINE_ITEMS_LABEL,"Line items label")){
			reporter.SuccessReport("Verify line items", "Line itmes exists", "");
		}else{
			reporter.failureReport("Verify line items", "Line itmes does exists", "");
		}
	}
	
	/**
	 * @throws Throwable 
	 * 
	 */
	public void verifyItemDescOnOrderDetailsPage(int itemNum) throws Throwable{
		List<WebElement> element=driver.findElements(ORDER_DETAILS_ITEMS);
		if(element.size()==itemNum){
			reporter.SuccessReport("Verify Split into individual lines in Order Details Section", " individual lines Exists", "");
		}else{
			reporter.failureReport("Verify Split into individual lines in Order Details Section", " individual lines does not Exists", "");
		}
			
		}
	
	/**
	 * Method is to verify the bundles added to cart
	 * @throws Throwable
	 */
	public void verifyBundleIsAddedToCart(String bundle) throws Throwable{	
		waitForVisibilityOfElement(getBundlesAdded(bundle),"Bundle");
		if(isElementPresent(getBundlesAdded(bundle),"Bundle",true)) {
			reporter.SuccessReport("Verify the Bundle  on Cart", "Bundle Field Exists", "");
		}
		else {
			reporter.failureReport("Verify the Bundle  on Cart", "Bundle Field Does Not Exist", "");
		}
	  }
	
	/**
	 * This method is to enter the country
	 * @param country
	 * @throws Throwable
	 */
	public void selectCountryDisplayed(String country) throws Throwable {
		
		waitForVisibilityOfElement(COUNTRY, "Country");
		String option=getSelectedDropdownOption(COUNTRY);
		if (option.equals(country)) {	
			Log.info("Country already selected"+country);
		}else{
			click(COUNTRY,"COUNTRY");
		    selectByVisibleText(COUNTRY, country, "country");
		   }
		  }
	
	/**
	 * Method is to click on proceed to checkout
	 * @throws Throwable
	 */
	public void proceedToCheckout() throws Throwable{
		commonLib.spinnerImage();
		if(isElementPresent(OrderObj.PROCEED_TO_CHECKOUT, "Proceed to checkout") && isEnabled(OrderObj.PROCEED_TO_CHECKOUT, "Proceed to checkout")){
			clickUntil(OrderObj.PROCEED_TO_CHECKOUT, OrderObj.ORDER_ITEM_INFO_LABEl, "Proceed to checkout");
		}else{
			reporter.failureReport("Verify the Proceed to checkout button visibility","Proceed to checkout is not visible or disabled","",driver);
		}
		}
}


		
	   

