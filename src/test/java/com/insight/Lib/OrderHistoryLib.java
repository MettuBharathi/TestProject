package com.insight.Lib;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tools.ant.taskdefs.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.insight.ObjRepo.CommonObj;
import com.insight.ObjRepo.OrderHistoryObj;
import com.insight.ObjRepo.CommonObj;

public class OrderHistoryLib extends OrderHistoryObj {
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();

	/**
	 * Method is used to verify order history page
	 * 
	 * 
	 * @throws Throwable
	 */
	public void verifyOrderHistoryPage() throws Throwable {
		waitForVisibilityOfElement(RECENT_ORDERS, "Recent orders");
		if (isElementPresent(RECENT_ORDERS, "Recent orders")) {
			reporter.SuccessReport("Verifying order history page", "Order history page is opened", "");
		} else {
			reporter.failureReport("Verifying order history page", "Order history page is opened", "");
		}
	}

	/**
	 * Method is used to advanced search
	 *
	 * 
	 * @throws Throwable
	 */
	public void selectQuickSearchDropdown(String text, String value) throws Throwable {
		click(QUICK_SEARCH_DROPDOWN, "Quick search");
		click(setAdvancedSearchOption(text), "");
		type(ADVANCED_SEARCH_VALUE, value, "value");
		click(SEARCH_BUTTON, "search button");

		// verifySearchResultsAreDisplayed();
	}

	/**
	 * Method is used to verify advanced search results
	 *
	 * 
	 * @throws Throwable
	 */
	public void verifySearchResultsAreDisplayed() throws Throwable {
		waitForVisibilityOfElement(SEARCH_RESULTS, "Recent orders");
		if (isElementPresent(SEARCH_RESULTS, "Recent orders")) {
			reporter.SuccessReport("Verifying search results", "search results are displayed", "");
		} else {
			reporter.failureReport("Verifying search results", "search results are displayed", "");
		}
	}

	/**
	 * 
	 * @param This
	 *            method is to set Date for Unpaid License
	 * @throws Throwable
	 */
	public void startDateCalender(String date) throws Throwable {
		Thread.sleep(5000);
		System.out.println("date"+date);
		String day = date.split("-")[0];
		System.out.println("day" + day);
		String month = date.split("-")[1];
		System.out.println("month" + month);
		String year = date.split("-")[2];
		System.out.println("year" + year);
		click(START_DATE_CALENDER, "Calnder");
		String calenderYear = getText(SLPLib.SELECTEDYEAR, "Selected year");
		System.out.println("calenderYear" + calenderYear);
		String calenderMonth = getText(SLPLib.SELECTED_MONTH, "Selected month");
		System.out.println("SELECTED_MONTH" + calenderMonth);
		if (year.equals(calenderYear) && month.equals(calenderMonth)) {
			click(Day(day), "Selecting date");

		}
		if (year.equals(calenderYear) && !(month.equals(calenderMonth))) {
			click(SLPLib.MONTH_DROPDOWN, "Month dropdown");
			click(SLPLib.Month(month), "Select month");
			click(Day(day), "Selecting date");
		}

		else {
			click(SLPLib.YEAR_DROPDOWN, "year dropdown");
			click(SLPLib.Year(year), "Selecy year");
			click(SLPLib.MONTH_DROPDOWN, "Month dropdown");
			click(SLPLib.Month(month), "Select month");
			click(Day(day), "Selecting date");
		}
	}

	/**
	 * 
	 * @param This
	 *            method is to click on advanced search
	 * @throws Throwable
	 */
	public void clickOnAdvancedSearch() throws Throwable {
		click(ADVANCED_SEARCH, "Advanced search");
	}

	/**
	 * 
	 * @param This
	 *            method is to click on order number
	 * @throws Throwable
	 */
	public void clickOrderNumber() throws Throwable {
		click(ORDER_NUMBER, "order Number");
	}

	/**
	 * 
	 * @param This
	 *            method is to click on adavanced search search_button
	 * @throws Throwable
	 */
	public void clickAdvancedSearchButton() throws Throwable {
		click(ADVANCED_SEARCH_SEARCHBUTTON, "Advanced search button");
	}

	/**
	 * 
	 * @param This
	 *            method is to click on send to colleauge link
	 * @throws Throwable
	 */
	public void clickSendToColleauge() throws Throwable {
		click(SEND_TO_COLLEAUGE_LINK, "Send to colleauge");
	}

	/**
	 * Method is used to send mail to colleauge
	 * 
	 * @throws Throwable
	 */
	public void sendToColleauge(String name, String email, String emailReceipents, String comments) throws Throwable {
		if (isElementPresent(SEND_TO_COLLEAUGE, "Send to colleauge")) {
			reporter.SuccessReport("Verifying email pop", "Email pop is exists and opened", "");
		} else {
			reporter.failureReport("Verifying email pop", "Email pop is not exists", "");
		}
		if (isElementPresent(YOUR_NAME, "Your name")) {
			type(YOUR_NAME, name, "Your name");
		}
		if (isElementPresent(YOUR_EMAIL, "Your email")) {
			type(YOUR_EMAIL, email, "Your email");
		}
		if (isElementPresent(YOUR_RECEIPENT_EMAIL, " email Receipents")) {
			type(YOUR_RECEIPENT_EMAIL, emailReceipents, " email Receipents");
		}
		if (isElementPresent(YOUR_COMMENTS, "comments")) {
			type(YOUR_COMMENTS, comments, "comments");
		}
		click(SEND, "send email");
		waitForVisibilityOfElement(EMAIL_SENT_MESSAGE, "email sent message");
		if (isElementPresent(EMAIL_SENT_MESSAGE, "email sent message")) {
			reporter.SuccessReport("Verify email is sent", "Email was sucessfully sent", "");
		} else {
			reporter.failureReport("Verify email is sent", "Email was not sent", "");
		}

	}

	/**
	 * Method is used to click on print
	 * 
	 * @throws Throwable
	 */
	public void clickPrint() throws Throwable {
		click(PRINT, "Print");
	}

	/**
	 * Method is used to click on add tracking notification
	 * 
	 * @throws Throwable
	 */
	public void clickAddTrackingNotification() throws Throwable {
		click(ADD_TRACKING_NOTIFICATION, "Add tracking notiification");
	}

	public void verifyAddAdditionalNotificationEmail() throws Throwable {
		if (isElementPresent(TRACKING_NOTIFICATION, "Add additional notification email")) {
			reporter.SuccessReport("Verify Tracking notifications added Confirmation",
					"Tracking notifications added Confirmation Exist", "");
		} else {
			reporter.failureReport("Verify Tracking notifications added Confirmation",
					"Tracking notifications added Confirmation does not Exist", "");

		}
		click(CANCEL_BUTTON, "Cancel button");
	}

	/**
	 * Method is used to click on order again
	 * 
	 * @throws Throwable
	 */
	public void clickOrderAgain() throws Throwable {
		click(ORDER_AGAIN, "order again");

	}

	/**
	 * Method is used to verify print link
	 * 
	 * @throws Throwable
	 */
	public void verifyPrintLink() throws Throwable {
		if (isElementPresent(PRINT, "Print")) {
			reporter.SuccessReport("Verify print link", "Print link exists", "");
		} else {
			reporter.failureReport("Verify print link", "Print link not exists", "");

		}

	}

	/**
	 * Method is used to click on tools dropdwown in homepage
	 * 
	 * @throws Throwable
	 */
	public void selectToolsDropDownInHomepage() throws Throwable {
		click(CommonObj.TOOLS_DD_HEADER, "tools drop down");
		click(TRACK_AN_ORDER, "Track an order");
	}

	/**
	 * Method is used to verify track my order page is opened
	 * 
	 * @throws Throwable
	 */
	public void verifyTrackMyOrderPageOpened() throws Throwable {
		waitForVisibilityOfElement(TRACK_AN_ORDER_PAGE, "Track an order");
		if (isElementPresent(TRACK_AN_ORDER_PAGE, "Track an order")) {
			reporter.SuccessReport("Verifying track an order page", "Track an order page is opened ", "");
		} else {
			reporter.failureReport("Verifying track an order page", "Track an order page is not opened ", "");

		}
	}

	/**
	 * Method is used to generic search
	 * 
	 * @throws Throwable
	 */
	public void genericSearch(String tabName, String value, String tabName1, String value1) throws Throwable {
		click(genericSerachTab(tabName), tabName);
		type(genericSearchValues(tabName.toLowerCase()), value, value);
		click(genericSerachTab(tabName1), tabName1);
		type(genericSearchValues(tabName1.toLowerCase()), value1, value1);
		click(GENERIC_SEARCH_BUTTON, "Generic search button");
		commonLib.spinnerImage();
		waitForVisibilityOfElement(SEARCH_RESULTS_GENERIC_SEARCH, "Generic search results");
		if (isElementPresent(SEARCH_RESULTS_GENERIC_SEARCH, "Generic search results")) {
			reporter.SuccessReport("Verifying generic search results", "Search results are displayed : ", "");
		} else {
			reporter.failureReport("Verifying generic search results", "Search results are not displayed : ", "");
		}
		clearData(genericSearchValues(tabName.toLowerCase()));
		clearData(genericSearchValues(tabName1.toLowerCase()));
	}

	public void selectOrderStatusDropdown(String text) throws Throwable {
		selectByVisibleText(ORDER_STATUS_DROPDOW, text, "Order status dropdown");

	}

	/**
	 * Method is used to verify invoice preview details
	 * 
	 * @throws Throwable
	 */
	public void verifyOrderStatusResults(String orderStatus) throws Throwable {
		String result = null;
		List<WebElement> myList = driver.findElements(ON_HOLD_RESULTS);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			// loading text of each element in to array all_elements_text
			all_elements_text.add(myList.get(i).getText());
			result = myList.get(i).getText();

			if (result.equals(orderStatus)) {
				reporter.SuccessReport("Verify the On Hold Orders Filter in Results",
						orderStatus + " " + " only returns in Search Results", "");
			} else {
				reporter.failureReport("Verify the On Hold Orders Filter in Results",
						"orders returns other than" + " " + orderStatus + " " + "status in Search Results", "");
			}
		}

	}

	/**
	 * This method is to waits until the page loading completes
	 * 
	 * @throws Throwable
	 */
	public void spinnerImageODH() throws Throwable {
		Thread.sleep(2000);
		waitForInVisibilityOfElement(SPINNER_IMAGE, "spinner image");
		if (isElementNotPresent(SPINNER_IMAGE, "spinner image")) {
			reporter.SuccessReport("spinner image", "spinner image disapperaed", "");
		} else {
			reporter.failureReport("spinner image", "spinner image not disapperaed", "");
		}
	}

	public void clickClearcSearch() throws Throwable {
		click(CLEAR_SEARCH, "Clear search");
	}

	/**
	 * Method is used to verify invoice preview details
	 * 
	 * @throws Throwable
	 */
	public void verifyNonShippableQty(String shippingQty, String NonShippingQty) throws Throwable {
		String result = null;
		List<WebElement> myList = driver.findElements(SHIPPED_QTY);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			// loading text of each element in to array all_elements_text
			all_elements_text.add(myList.get(i).getText());
			// result = myList.get(i).getText();
		}
		System.out.println("all_elements_text" + all_elements_text);
		if (all_elements_text.contains(shippingQty) && all_elements_text.contains(NonShippingQty)) {
			reporter.SuccessReport(
					"Verify the item with Qty and not Qty Shipped when shipping type Non-shippable in Results",
					"item with Qty and not Qty Shipped when shipping type Non-shippable in Results", "");
		} else {
			reporter.failureReport(
					"Verify the item with Qty and not Qty Shipped when shipping type Non-shippable in Results",
					"item with Qty and not Qty Shipped when shipping type Non-shippable in Results", "");
		}

	}
	/**
	 * Method is used to select shipping type dropdown
	 * 
	 * @throws Throwable
	 */
	public void selectShippingTypeDropdown(String text) throws Throwable {
		selectByVisibleText(SHIPPING_TYPE, text, "Shipping type dropdown");

	}
	/**
	 * Method is used to select open or invoiced type dropdown
	 * 
	 * @throws Throwable
	 */
	public void selectOpenOrInvoicedTypeDropdown(String text) throws Throwable {
		selectByVisibleText(OPEN_INVOICED_ORDERS, text, "Open or invoiced orders dropdown");

	}
	/**
	 * Method is used to get the previous year date to current date
	 * 
	 * @throws Throwable
	 */
	public String previousYearToCurrentDate() {
		LocalDate today = LocalDate.now();
		String newDate = today.plusYears(-1).format(DateTimeFormatter.ofPattern("d-MMMM-uuuu"));
		System.out.println("newDate" + newDate);
		return newDate;

	}
	/**
	 * Method is used to get plus one year three months from current date
	 * 
	 * @throws Throwable
	 */
	public String previousYearPlusThreeMonthsToCurrentDate() {
		LocalDate today = LocalDate.now();
		String newDate = today.plusYears(-1).plusMonths(3).format(DateTimeFormatter.ofPattern("d-MMMM-uuuu"));
		System.out.println("newDate" + newDate);
		return newDate;
	}

	/**
	 * 
	 * @param This
	 *            method is to set Date for Unpaid License
	 * @throws Throwable
	 */
	public void endDateCalender(String date) throws Throwable {
		String day = date.split("-")[0];
		System.out.println("day" + day);
		String month = date.split("-")[1];
		System.out.println("month" + month);
		String year = date.split("-")[2];
		System.out.println("year" + year);
		click(END_DATE_CALENDER, "Calnder");
		String calenderYear = getText(SLPLib.SELECTEDYEAR, "Selected year");
		System.out.println("calenderYear" + calenderYear);
		String calenderMonth = getText(SLPLib.SELECTED_MONTH, "Selected month");
		System.out.println("SELECTED_MONTH" + calenderMonth);
		if (year.equals(calenderYear) && month.equals(calenderMonth)) {
			click(Day(day), "Selecting date");

		}
		if (year.equals(calenderYear) && !(month.equals(calenderMonth))) {
			click(SLPLib.MONTH_DROPDOWN, "Month dropdown");
			click(SLPLib.Month(month), "Select month");
			click(Day(day), "Selecting date");
		}

		else {
			click(SLPLib.YEAR_DROPDOWN, "year dropdown");
			click(SLPLib.Year(year), "Selecy year");
			click(SLPLib.MONTH_DROPDOWN, "Month dropdown");
			click(SLPLib.Month(month), "Select month");
			click(Day(day), "Selecting date");
		}
	}
	/**
	 * Method is used to select account selection dropdown
	 * 
	 * @throws Throwable
	 */
	public void selectAccountSelectionDropdown(String text) throws Throwable {
		selectByVisibleText(ACCOUNT_SELECTION_DROPDOWN, text, "Account selection");

	}
	
	/**
	 * Method is used to verify selected option for account selection dropdown
	 * 
	 * @throws Throwable
	 */
	public void verifySelectedOptionForAccountSelection(String actual) throws Throwable{
		String selectedOption=getSelectedDropdownOption(ACCOUNT_SELECTION_DROPDOWN);
		if(selectedOption.equals(actual)) {
			reporter.SuccessReport("Verify the account selection defalts to All of My Accounts", "Account selection defalts to All of My Accounts Exists", selectedOption);
		}
		else {
			reporter.failureReport("Verify the account selection defalts to All of My Accounts", "Account selection defalts to All of My Accounts Does not Exists", selectedOption);
		}
	}
	
	/**
	 * Method is used to verify options for account selection dropdown
	 * 
	 * @throws Throwable
	 */
	public void verifyOptionsForAccountSelectionDD(String options) throws Throwable {
		List<String> mylist=getDropDownData(ACCOUNT_SELECTION_DROPDOWN,"");
		System.out.println("mylist"+mylist);
		String strArray[] = options.split(",");
		for(int i=0;i<mylist.size();i++) {
			if(mylist.get(i).trim().equals(strArray[i])) {
				System.out.println("mylist.get(i)"+mylist.get(i));
				System.out.println("strArray[i]"+strArray[i]);
				reporter.SuccessReport("Verify the account selection Contains", "Account selection exists with expected list items", mylist.get(i));
			}
			else {
				reporter.failureReport("Verify the account selection Contains", "Account selection does not exists with expected list items", mylist.get(i));
			}
		}
	}
	
	/**
	 * Method is used to select display per page
	 * 
	 * @throws Throwable
	 */
	public void selectDisplayPerPage(String noOfResults) throws Throwable {
		click(DISPLAY_PER_PAGE, "Display per page");
		click(countPerPage(noOfResults), noOfResults);
		spinnerImageODH();
	}
	
	/**
	 * Method is used to verify search results are more than five
	 * 
	 * @throws Throwable
	 */
	public void verifySearchResultsMoreThanFive() throws Throwable {
		List<WebElement> myList = driver.findElements(ORDER_NUMBERS_IN_RESULT);
		if (myList.size() > 5) {
			reporter.SuccessReport("Verify the 20 orders are returned in Results ",
					"20 Orders are returned in Search Results", "");
		} else {
			reporter.failureReport("Verify the 20 orders are returned in Results ", "20 Orders are does not returned",
					"");
		}
	}
	
	/**
	 * Method is used to select sort results
	 * 
	 * @throws Throwable
	 */
	public void selectSortResults(String sortFilter) throws Throwable {
		click(SORT_RESULTS_BY, "Sort results");
		click(sortResultsFilter(sortFilter), sortFilter);
		spinnerImageODH();
	}
	
	/**
	 * Method is used to select sort order
	 * 
	 * @throws Throwable
	 */
	public void selectSortOrder(String sortOrder) throws Throwable {
		click(SORT_ORDER, "Sort order");
		click(sortOrderFilter(sortOrder), sortOrder);
		spinnerImageODH();
	}
	
	/**
	 * Method is used to verify search results are in ascending
	 * 
	 * @throws Throwable
	 */
	public void verifySearchResultsAreInAscending() throws Throwable {
		List<WebElement> myList = driver.findElements(ORDER_NUMBERS_IN_RESULT);
		for (int i = 0; i < myList.size() - 1; i++) {
			if (Integer.parseInt(myList.get(i + 1).getText()) > Integer.parseInt(myList.get(i).getText())) {
				reporter.SuccessReport("Verify the Validate sort order in Results  ",
						"Orders are returned ascending in Search Results", "");
			} else {
				reporter.failureReport("Verify the Validate sort order in Results ",
						"Orders are does not returned in ascending", "");
			}
		}

	}
	/**
	 * Method is used to verify dates order
	 * 
	 * @throws Throwable
	 */
	public void verifyDatesOrder() throws Throwable {
		List<WebElement> myList = driver.findElements(DATES_IN_RESULT);
		List<String> all_elements_text = new ArrayList<>();
		List<String> newList = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			// loading text of each element in to array all_elements_text
			all_elements_text.add(myList.get(i).getText());
		}
		System.out.println("all_elements_text"+all_elements_text);
		for (int j = 0; j < all_elements_text.size(); j++) {
			
			 try
			    {
			    //String strDate="09-Sep-2019";
			     SimpleDateFormat sdfSource = new SimpleDateFormat("dd-MMM-yyyy");
			      Date date = sdfSource.parse(all_elements_text.get(j));
			      SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
			      String strDate = sdfDestination.format(date);
			          System.out.println("strDate : " + strDate);  
			          newList.add(strDate);
			    }      
			catch(ParseException pe)
			    { 
			      System.out.println("Parse Exception : " + pe);
			    }
		}
		System.out.println("newList"+newList);
		for(int k=0;k<newList.size()-1;k++) {
			System.out.println("newList.get(k)"+newList.get(k));
			System.out.println("newList.get(k+1)"+newList.get(k+1));
			if(newList.get(k+1).compareTo(newList.get(k))>0 || newList.get(k+1).compareTo(newList.get(k+1))==0) {
				reporter.SuccessReport("Verify the Validate sort by date descending in Results", "Search Results Orders are returned in sort by date descending", newList.get(k)+" "+newList.get(k+1));
			}
			else {
				reporter.failureReport("Verify the Validate sort by date descending in Results", "Orders are does not returned in sort by date descending", newList.get(k)+" "+newList.get(k+1));
			}
			
			if(newList.get(k+1).compareTo(newList.get(k))<0 || newList.get(k+1).compareTo(newList.get(k+1))==0) {
				reporter.SuccessReport("Verify the Validate sort by date ascending in Results", "Search Results Orders are returned in sort by date ascending", newList.get(k)+" "+newList.get(k+1));
			}
			else {
				reporter.failureReport("Verify the Validate sort by date ascending in Results", "Orders are does not returned in sort by date ascending", newList.get(k)+" "+newList.get(k+1));
			}
		}
	}
	
	/**
	 * Method is used to verify search results are in descending
	 * 
	 * @throws Throwable
	 */
	public void verifySearchResultsAreInDescending() throws Throwable {
		List<WebElement> myList = driver.findElements(ORDER_NUMBERS_IN_RESULT);
		for (int i = 0; i < myList.size() - 1; i++) {
			if (Integer.parseInt(myList.get(i + 1).getText()) < Integer.parseInt(myList.get(i).getText())) {
				reporter.SuccessReport("Verify the Validate sort order in Results  ",
						"Orders are returned ascending in Search Results", "");
			} else {
				reporter.failureReport("Verify the Validate sort order in Results ",
						"Orders are does not returned in ascending", "");
			}
		}

	}
	
	/**
	 * Method is used to verify account name
	 * 
	 * @throws Throwable
	 */
	public void verifyAccountName(String contactName) throws Throwable {
		List<WebElement> myList = driver.findElements(ACCOUNT_NAME);
		List<String> all_elements_text = new ArrayList<>();
		
		for (int i = 0; i < myList.size(); i++) {
			// loading text of each element in to array all_elements_text
			all_elements_text.add(myList.get(i).getText());
		}
		//String accountName=getText(ACCOUNT_NAME, "ACCOUNT_NAME");
		for (int j = 0; j < myList.size(); j++) {
		if(all_elements_text.get(j).toLowerCase().contains(contactName.toLowerCase().split(" ")[0])) {
			reporter.SuccessReport("Verify the Validate  session soldto in Results", "Search Results Orders are returned with session soldto exists", "");
		}
		else {
			reporter.failureReport("Verify the Validate  session soldto in Results", "Orders are does not returned with session soldto", "");
		}
		}
	}
	
	/**
	 * Method is used to verify selected operation center dropdown
	 * 
	 * @throws Throwable
	 */
	public void verifySelectedOperationCenter(String text) throws Throwable {
		if(isElementPresent(selectedOperationsCenter(text), text)) {
			reporter.SuccessReport("Verify the Accounts by Region Contains", "operations centers exists with expected list items", text);
		}
		else {
			reporter.failureReport("Verify the Accounts by Region Contains", "operations centers does not exists with expected list items", text);
		
		}
	}
	/**
	 * Method is used to click selected operation center dropdown
	 * 
	 * @throws Throwable
	 */
	public void clickSelectedOperationCenter(String text) throws Throwable {
		click(selectedOperationsCenter(text), text);
	}
	
	/**
	 * Method is used to verify currency for insight APAC
	 * 
	 * @throws Throwable
	 */
	public void verifyCurrencyForAPAC() throws Throwable {
		String currencyCode=getText(ORDER_TOTAL_CURRENCY_CODE, "Currency");
		if(currencyCode!="USD") {
			reporter.SuccessReport("Verify the Validate Selected regions to All in Results", "Results do contain All Regions exists", "");
		}
		else {
			reporter.failureReport("Verify the Validate Selected regions to All in Results", "Results contain USA.", "");
		
		}
	}
	/**
	 * Method is used to click on insight austrlia
	 * 
	 * @throws Throwable
	 */
	public void clickInsightAustralia() throws Throwable {
		click(INSIGHT_AUSTRAILIA,"Insight australia");
	}
	/**
	 * Method is used to verify Australian currency
	 * 
	 * @throws Throwable
	 */
	public void verifyAustraliaCurrency() throws Throwable {
		String currencyCode=getText(ORDER_TOTAL_CURRENCY_CODE, "Currency");
		if(currencyCode.equals("AUD")) {
			reporter.SuccessReport("Verify the Validate only Australia cities in Results", "Results do contain only Australia cities exists", "");
		}
		else {
			reporter.failureReport("Verify the Validate only Australia cities in Results", "Results contain other than Australia cities", "");
		
		}
	}
}
