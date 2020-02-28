package com.insight.Lib;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.insight.ObjRepo.CanadaObj;
import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.CommonObj;
import com.insight.ObjRepo.MarriottIntlCorpObj;
import com.insight.ObjRepo.OrderObj;
import com.insight.ObjRepo.SewpObj;
import com.insight.ObjRepo.ShipBillPayObj;
import com.insight.ObjRepo.productsDisplayInfoObj;
import com.insight.utilities.DynamicTestDataGenerator;

public class CanadaLib extends CanadaObj {
	
	ReportingLib reportingLib=new ReportingLib();
	SearchLib searchLib=new SearchLib();
	/**
	 * Method is used to verify user is logged to canada webgrp
	 */
	public void verifyCanadaWebgroup() throws Throwable {
		if (isElementPresent(CANADA_WEBGROUP, "Canada webgroup")) {
			reporter.SuccessReport("Verify Canada Web Site Login", "User is Logged into Canada Insight", "");
		} else {
			reporter.failureReport("Verify Canada Web Site Login", "User is not Logged into Canada Insight", "",driver);
		}
	}

	/**
	 * Method is used to verify SBP
	 */
	public void verifySBP() throws Throwable {
		if (isElementPresent(SHIP_BILL, "Ship bill")) {
			reporter.SuccessReport("Verify ship bill pay section", "Ship bill section is present ", "");
		} else {
			reporter.failureReport("Verify ship bill pay section", "Ship bill section is not present", "",driver);
		}
	}

	/**
	 * Method is used to make a note of PST in cart summary
	 * 
	 * @throws Throwable
	 */
	public String getPSTInSummary(String pst) throws Throwable {
		String PSTAmount = getText(ShipBillPayLib.getAmountsInSummary(pst), "PST amount");
		return PSTAmount;
	}

	/**
	 * Method is used to make a note of GST in cart summary
	 * 
	 * @throws Throwable
	 */
	public String getGSTInSummary(String gst) throws Throwable {
		String GSTAmount = getText(ShipBillPayLib.getAmountsInSummary(gst), "GST amount");
		return GSTAmount;
	}

	/**
	 * Method is used to make a note of PST in cart summary
	 * 
	 * @throws Throwable
	 */
	public String getEWRFeeInSummary() throws Throwable {
		String EWRAmount = getText(EWR_AMOUNT, "EWR Amount");
		return EWRAmount;
	}

	/**
	 * Method is used to click on return to cart
	 * 
	 * @throws Throwable
	 */
	public void clickReturnToCart() throws Throwable {
		click(CartObj.RETURN_TO_CART, "Return to cart");
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyPlaceCartLabel() throws Throwable {
		if (isElementPresent(CART_LABEL, "Cart header label displayed")) {
			reporter.SuccessReport("Verify wether user navigates to cart page or not",
					"User successfully navigated to cart page", "");
		} else {
			reporter.failureReport("Verify wether user navigates to cart page or not",
					"User not navigated to cart page", "",driver);
		}
	}

	/**
	 * 
	 * @param pst
	 * @param Gst
	 * @throws Throwable
	 */
	public void verifyGSTAndPSTInCartPage(String pst, String Gst) throws Throwable {
		if (isElementPresent(ShipBillPayLib.getAmountsInSummary(pst), "pst")) {
			String subTotalAmount = getText(ShipBillPayLib.getAmountsInSummary(pst), "PST amount");
			reporter.SuccessReport("Verify PST/QST estimates", "PST/QST Estimates in SBP page Exist: ", subTotalAmount);
		} else {
			reporter.failureReport("Verify PST/QST estimates", "PST/QST Estimates in SBP page does not Exist:", "",driver);

		}

		if (isElementPresent(ShipBillPayLib.getAmountsInSummary(Gst), "Gst")) {
			String subTotalAmount = getText(ShipBillPayLib.getAmountsInSummary(pst), "GST amount");
			reporter.SuccessReport("Verify GST/HST estimates", "GST/HST Estimates in SBP page Exist ", subTotalAmount);
		} else {
			reporter.failureReport("Verify GST/HST estimates", "GST/HST Estimates in SBP page do not Exist", "",driver);

		}
	}

	public void verifyEWRInCartPage() throws Throwable {
		String EWRAmount = getText(EWR_AMOUNT, "EWR Amount");
		if (isElementPresent(EWR_AMOUNT, "EWR Amount") && (!EWRAmount.isEmpty() || !(EWRAmount == null))) {

			reporter.SuccessReport("Verify EWR Fee and Taxes in the Cart", "EWR Fee and Taxes Exist in the Cart ",
					EWRAmount);
		} else {
			reporter.failureReport("Verify EWR Fee and Taxes in the Cart", "EWR Fee and Taxes Do Not Exist in the Cart",
					"",driver);

		}
	}

	/**
	 * 
	 * @param gstAmount
	 * @param GstAmount1
	 * @throws Throwable
	 */
	public void verifyGSTAmonunts(String gstAmount, String GstAmount1) throws Throwable {
		if (!gstAmount.equals(GstAmount1)) {
			reporter.SuccessReport("Verify GST ,PST and QST Taxes in the Place Order Page",
					"Cart GST, PST and QST Taxes are Verified", "");
		} else {
			reporter.failureReport("Verify GST ,PST and QST Taxes in the Place Order Page",
					"Cart GST, PST and QST Taxes are Not Verified", "",driver);
		}
	}

	/**
	 * 
	 * @param ewrAmount
	 * @param ewrAmount1
	 * @throws Throwable
	 */
	public void verifyEWRAmonunts(String ewrAmount, String ewrAmount1) throws Throwable {
		if (!ewrAmount.equals(ewrAmount1)) {
			reporter.SuccessReport("Verify EWR Fees  in the Place Order Page", "Cart EWR Fees is Verified", "");
		} else {
			reporter.failureReport("Verify EWR Fees  in the Place Order Page", "EWR Fees is Not Verified", "",driver);
		}
	}

	/**
	 * This method is to Select sort options in sort By drop down results page.
	 * 
	 * @param sortOption
	 * @throws Throwable
	 */
	public void verifySortOption(String sortOption) throws Throwable {

		if (isElementPresent(productsDisplayInfoObj.getSortByOptions(sortOption), "Select sort option")) {
			reporter.SuccessReport("Verify sort options in search results page",
					"Sort option in search results verified successfully. Option is :" + sortOption, "");
		} else {
			reporter.failureReport("Verify sort options in search results page",
					"Sort option in search results verified successfully. Option is :" + sortOption, "",driver);
		}
	}

	/**
	 * Method is to verify the products not found message in the search results
	 * page
	 * 
	 * @throws Throwable
	 */
	public void verifyNoResultsFoundMessgeInProductSearchResults() throws Throwable {
		if (isElementPresent(productsDisplayInfoObj.INVALID_PROD_SEARCH_MSG, "invalid product search message")) {
			reporter.SuccessReport("Verify invalid search message", "No results found message displayed", "");
		} else {
			reporter.failureReport("Verify invalid search message", "No results found displayed not displayed", "",driver);
		}
	}

	/**
	 * This method is used to verify products are in stock in cart page
	 * 
	 * @param option
	 * @throws Throwable
	 */
	public void verifyAvailabilityOfTheProductInCart() throws Throwable {

		List<WebElement> mylist = driver.findElements(STOCK_CART);
		for (int i = 0; i < mylist.size(); i++) {
			if (isElementPresent(STOCK_CART, "Stock")) {
				reporter.SuccessReport("Verify Item is Added to Cart on Cart Page Page ",
						"Stock Exists and Verified in the Cart ", "");
			} else {
				reporter.failureReport("Verify Item is Added to Cart on Cart Page Page ",
						"Stock Field is not Verified in the Cart ", "",driver);
			}
		}
	}

	/**
	 * Method to search invoice history
	 * 
	 * @param searchBy
	 * @param text
	 * @param orderNumber
	 * @throws Throwable
	 */

	public void searchOnInvoicePageOrder(String searchBy, String text, String orderNumber) throws Throwable {

		Thread.sleep(10000);

		waitForVisibilityOfElement(SEARCHBY_DROPDOWN, "Quick Search");
		if (isElementPresent(SEARCHBY_DROPDOWN, "Quick Search")) {

			click(SEARCHBY_DROPDOWN, "SearchBy");

			click(getSearchByTextOrder(searchBy), "Search By");
			click(QUICK_SEARCH_TEXT, "Click on Text");
			type(QUICK_SEARCH_TEXT, text, "Text ");
			Thread.sleep(5000);
			click(SEARCH, "Search");

			reporter.SuccessReport("Perform Quick Search " + searchBy + "  ",
					" Quick Search is Performed" + searchBy + " ", "");
			String actualText;

			waitForVisibilityOfElement(ORDERNUM_INVOICEHISTORY, "OrderNumber in invoice history");
			if (isElementPresent(ORDERNUM_INVOICEHISTORY, "OrderNumber in invoice history")) {
				actualText = getText(ORDERNUM_INVOICEHISTORY, "OrderNumber in invoice history").trim();

				if (actualText.equalsIgnoreCase(orderNumber)) {
					reporter.SuccessReport("Verify OderNumber displayed in Invoice hisory page  ",
							" OderNumber is displayed in Invoice hisory page", "");
				} else {
					reporter.failureReport("Verify OderNumber displayed in Invoice hisory page  ",
							" OderNumber is not  displayed in Invoice hisory page", "");
				}
			} else {
				reporter.failureReport(
						"Verify Ordernumber invoice histoery element is present  in Invoice hisory page  ",
						" Ordernumber invoice histoery element is not present in Invoice hisory page", "");
			}
		} else {
			reporter.failureReport("Perform Quick Search " + searchBy + "  ",
					" No Quick Search is Performed" + searchBy + " ", "");
		}
	}

	/**
	 * Method is used to click on invoice number
	 * 
	 * @throws Throwable
	 */
	public void invoiceNumberLink() throws Throwable {
		if (isElementPresent(INVOICE_NUMBER, "invoice number ")) {

			click(INVOICE_NUMBER, "Click on invoice number ");
			reporter.SuccessReport("Verify invoice Details POPUP", " invoice Details POPUP exists", "");
		}

	}
	public String getInvoiceNumber() throws Throwable{
		String invoiceNumber=getText(INVOICE_NUMBER, "invoice number ");
		return invoiceNumber;
	}
	/**
	 * Method is used to make click on print icon
	 * 
	 * @throws Throwable
	 */
	public void printIcon() throws Throwable {
		if (isElementPresent(PRINTICON, "Print Icon")) {

			click(PRINTICON, "Print Icon ");

		}
	}

	/**
	 * Method is to click on the account tools side menu and select options
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @throws Throwable
	 */
	public void clickOnSideMenuSelectAccountToolOptions(String toolsMenuName, String dropDown) throws Throwable {
		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		click(getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
	}

	public void verifyGroundIsDefaultShippingOption() throws Throwable {
		waitForVisibilityOfElement(DEFAULT_SHIIPING_OPTION_GROUND, "default shipping option");
		if (isCheckBoxSelected(DEFAULT_SHIIPING_OPTION_GROUND)) {
			reporter.SuccessReport("Verify Canada Ground is the Default Shipping Option",
					"Canada Ground is the Default Shipping Option", "");
		} else {
			reporter.failureReport("Verify Canada Ground is the Default Shipping Option",
					"Canada Ground is Not Default Shipping Option", "",driver);
		}

	}

	/**
	 * Method is to click on the InvoiceHistory
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @throws Throwable
	 */
	public void clickOnInvoiceHistory() throws Throwable {
		click(QUICKSEARCH_DROPDOWN, "Quick Search + icon");

	}
	/**
	 * Method is used to verify invoice history page is loaded
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @throws Throwable
	 */
	public void verifyInvoiceHistoryPageOpened() throws Throwable {
		if(isElementPresent(QUICKSEARCH_DROPDOWN, "Invoice history header ")) {
			reporter.SuccessReport("Verify invoice history page is loaded", "Invoice history page is loaded","");
		}
		else {
			reporter.failureReport("Verify invoice history page is loaded", "Invoice history page is not loaded","",driver);
		}
	}

	/**
	 * Method is used to close
	 * 
	 * @throws Throwable
	 */
	public void closeIcon() throws Throwable {
		waitForVisibilityOfElement(CLOSE, "close Icon");
		if (isElementPresent(CLOSE, "close Icon")) {
			click(CLOSE, "close Icon ");
		}
	}

	/**
	 * Method is used to click on download link
	 * 
	 * @throws Throwable
	 */
	public void clickOnDownloadLink() throws Throwable {
		waitForVisibilityOfElement(DOWNLOADLINK, "DownloadLink");
		if (isElementPresent(DOWNLOADLINK, "DownloadLink")) {
			click(DOWNLOADLINK, "DownloadLink ");

		}

	}

	public void verifyDownloadedFile(String filename) throws Throwable {

		Desktop desktop = Desktop.getDesktop();
		if (!Desktop.isDesktopSupported()) {
			System.out.println("Desktop is not supported");
			return;
		}
		//File file = new File("C:\\Users\\e002106\\Downloads\\1100635152.PDF");// CHANGE
		String sfile = System.getProperty("user.dir") + "\\" + "DownloadedFiles" + "\\" + filename+".PDF";																	// PATH
		File file = new File(sfile);
		Thread.sleep(5000);																		// ACCORDINGLY
		if (file.exists()) {
			System.out.println("file found");
			desktop.open(file);
			reporter.SuccessReport("Verify the file existance ", "File Found", "");
		} else {
			System.out.println("file not found");
			reporter.failureReport("Verify the file existance ", "File not Found", "",driver);
		}
		file.deleteOnExit();
	}

	/**
	 * Method is to verify the EWR Fees for individual product in quotes screen
	 * 
	 * @throws Throwable
	 */
	public String verifyEWRFeesForCartOnQuoteConfirmationPage() throws Throwable {
		String ewrFee = getText(EWR_FEE_FOR_PRODUCT, "EWR Fees");
		if (isElementPresent(EWR_FEE_FOR_PRODUCT, "EWR fees") && (!ewrFee.isEmpty() || !(ewrFee == null))) {
			reporter.SuccessReport("Verify EWR Fees For Each in the Cart on Quote Confirmation page",
					"EWR Fee Exists : " + ewrFee, "");
		} else {
			reporter.failureReport("Verify EWR Fees For Each in the Cart on Quote Confirmation page",
					"EWR Fee does not Exists", "",driver);
		}
		return ewrFee;
	}

	/**
	 * Method is to Verify EWR Total Field in the Cart on Quote Confirmation
	 * Page
	 * 
	 * @throws Throwable
	 */
	public void verifyEWRTotalFeesOnQuotesScreen() throws Throwable {
		String ewrFeeTotal = getText(EWR_TOTAL_FEES, "EWR total Fee");
		if (isElementPresent(EWR_TOTAL_FEES, "EWR fees") && (!ewrFeeTotal.isEmpty() || !(ewrFeeTotal == null))) {
			reporter.SuccessReport("Verify EWR Total Field in the Cart on Quote Confirmation Page",
					"Total EWR Fee Exists in the Cart : " + ewrFeeTotal, "");
		} else {
			reporter.failureReport("Verify EWR Total Field in the Cart on Quote Confirmation Page",
					"EWR Fee does not Exists", "",driver);
		}
	}

	/**
	 * Method is to verify the GST estimate on the save quotes screen
	 * 
	 * @throws Throwable
	 */
	public String verifyGSTEstimateOnQuotesScreen() throws Throwable {
		String GST = getText(GST_ESTIMATE_QUOTES_PAGE, "GST estimate");
		if (isElementPresent(GST_ESTIMATE_QUOTES_PAGE, " GST estimate") && (!GST.isEmpty() || !(GST == null))) {
			reporter.SuccessReport("Verify G.S.T Estimate on Quote Confirmation Page", "G.S.T Estimate exist : " + GST,
					"");
		} else {
			reporter.failureReport("Verify G.S.T Estimate on Quote Confirmation Page", "G.S.T Estimate not exist", "",driver);
		}
		return GST;
	}

	/**
	 * Method is to verify the P.S.T/Q.S.T estimate on the save quotes screen
	 * 
	 * @throws Throwable
	 */
	public String verifyPST_QSTEstimates() throws Throwable {
		String PST_QST = getText(PST_QST_ESTIMATE_QUOTES_PAGE, " P.S.T/Q.S.T Estimates");
		if (isElementPresent(PST_QST_ESTIMATE_QUOTES_PAGE, "  P.S.T/Q.S.T Estimates")
				&& (!PST_QST.isEmpty() || !(PST_QST == null))) {
			reporter.SuccessReport("Verify  P.S.T/Q.S.T Estimates on Quote Confirmation Page",
					" P.S.T/Q.S.T Estimates exist : " + PST_QST, "");
		} else {
			reporter.failureReport("Verify  P.S.T/Q.S.T Estimates on Quote Confirmation Page",
					" P.S.T/Q.S.T Estimates not exist", "",driver);
		}
		return PST_QST;
	}

	/**
	 * This method is to verify the amount in Quotes History page
	 * 
	 * @param label
	 * @param summaryamount
	 * @throws Throwable
	 */
	public void verifySummaryTableAmountsOnQuotesHistoryPage(String label, String summaryamount) throws Throwable {
		String amount = getText(getSummaryAmounts(label), "Summay amounts").replace("CAD ", "");
		if (summaryamount.equals(amount)) {
			reporter.SuccessReport("Verify summary amount on quote history Page", "summary amount exists" + amount, "");
		} else {
			reporter.failureReport("Verify summary amount on quote history Page", "summary amount not exists", "",driver);
		}
	}

	public void verifyTaxAmountOnReceiptOrderDetails() {

	}

	/**
	 * Method to search invoice history
	 * 
	 * @param searchBy
	 * @param text
	 * @param orderNumber
	 * @throws Throwable
	 */

	public void advanceSearchInOrderHistory(String searchBy, String text) throws Throwable {
		Thread.sleep(10000);

		waitForVisibilityOfElement(ADVANCE_SEARCH_ORDERHISTORY, "Advance Search");
		if (isElementPresent(ADVANCE_SEARCH_ORDERHISTORY, "Advance Search")) {

			selectByValue(ADVANCE_SEARCH_ORDERHISTORY, searchBy, "Select ");
			click(ADVANCE_SEARCH_TEXTBOX, "Click on Text");
			type(ADVANCE_SEARCH_TEXTBOX, text, "Text ");
			Thread.sleep(5000);
			click(ADVANCE_SEARCHBUTTON, "Search");

			reporter.SuccessReport("Perform Quick Search " + searchBy + "  ",
					" Quick Search is Performed" + searchBy + " ", "");
			String actualText;

			if (text.equals("Order1")) {
				if (isElementPresent(getOrderNumber(text), text + " in Order history")) {
					actualText = getText(getOrderNumber(text), "OrderNumber in Order history").trim();

				} else {

					reporter.failureReport("Verify OderNumber displayed in Invoice hisory page  ",
							" OderNumber is not displayed in Invoice hisory page", "");

					reporter.failureReport("Verify Element is  present  ", " Element is not present", "",driver);

				}
			}

			if (text.equals("Order2")) {
				if (isElementPresent(getProductDetail(text), text + " in Order history")) {
					actualText = getText(getProductDetail(text), "OrderNumber in Order history").trim();

					if (actualText.equalsIgnoreCase(text)) {
						reporter.SuccessReport("Verify " + text + " displayed in Order hisory page  ",
								" OderNumber is displayed in Invoice hisory page", "");
					} else {
						reporter.failureReport("Verify " + text + " displayed in Order hisory page  ",
								" OderNumber is not displayed in Order hisory page", "",driver);
					}
				} else {
					reporter.failureReport("Verify Element is  present  ", " Element is not present", "",driver);
				}
			}
			if (text.equals("Order3")) {
				if (isElementPresent(getProductDetail(text), text + " in Order history")) {
					actualText = getText(getProductDetail(text), "OrderNumber in Order history").trim();

					if (actualText.equalsIgnoreCase(text)) {
						reporter.SuccessReport("Verify " + text + " displayed in Order hisory page  ",
								" OderNumber is displayed in Invoice hisory page", "");
					} else {
						reporter.failureReport("Verify " + text + " displayed in Order hisory page  ",
								" OderNumber is not displayed in Order hisory page", "",driver);
					}
				} else {
					reporter.failureReport("Verify Element is  present  ", " Element is not present", "",driver);
				}
			}
		} else {
			reporter.failureReport("Perform Quick Search " + searchBy + "  ",
					" No Quick Search is Performed" + searchBy + " ", "",driver);
		}
	}

	/**
	 * Method is to select SPLA Details Product CheckBox and click on View Products For Selected Agreements button
	 * 
	 * @param spla
	 * @throws Throwable
	 */
	public void selectSPLADetailsProductCheckBox(String spla) throws Throwable {
		if (isElementPresent(SPLA_LABEL, "SPLA LABEL")) {
			if (!isCheckBoxSelected(getMySoftwareLicenseAgreementscheckBoxes(spla))) {
				click(getMySoftwareLicenseAgreementscheckBoxes(spla),"SPLA Details Product CheckBox");
				click(SELECT_PRODUCT, "Select product checkbox");
			} else {
				LOG.info("Checkbox already selected");
			}
		}
	}

	/**
	 * Method is to verify the SPLA page
	 * @throws Throwable
	 */
	public void verifySPLAPage() throws Throwable{
		if(isElementPresent(SPLA_LABEL, "SPLA LABEL")){
		   reporter.SuccessReport("verify SPLA page", "SPLA page verification is successfull", "");
		  }else{
			  reporter.failureReport("verify SPLA page", "SPLA page verification is not successfull", "",driver);
		  }
		}
	
	/**
	 * Method is to click on the Order History - Order Number to view details
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @throws Throwable
	 */
	public void clickOnOrderNumberToViewDetails(String orderNumber) throws Throwable {
		click(getOrderNumber(orderNumber), "Click on OrderNumber");

	}

	/**
	 * Method is to verify order details in order history page
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @throws Throwable
	 */
	public void verifyDetailsInOrderPage(String orderNumber, String referenceNumber, String purchaseNumber, String EWR)
			throws Throwable {

		String actualText;
		if (isElementPresent(ORDERNUMBER_DETAILSPAGE, "OrderNumber in Order history")) {
			actualText = getText(ORDERNUMBER_DETAILSPAGE, "OrderNumber in Order history").trim();

			if (actualText.equalsIgnoreCase(orderNumber)) {
				reporter.SuccessReport("Verify OderNumber displayed in Order hisory page  ",
						" OderNumber is displayed in Order hisory page", "");
			} else {
				reporter.failureReport("Verify OderNumber displayed in Order hisory page  ",
						" OderNumber is not displayed in Order hisory page", "",driver);
			}
		}

		if (isElementPresent(REFERENCENUMBER_DETAILSPAGE, "Reference Number in Order history details Page")) {
			actualText = getText(REFERENCENUMBER_DETAILSPAGE, "Reference Number in Order history details Page");

			if (actualText.equalsIgnoreCase(referenceNumber)) {
				reporter.SuccessReport("Verify Reference Number  displayed in Order hisory page  ",
						" Reference Number is dispalyed Order history details Page", referenceNumber);
			} else {
				reporter.failureReport("Verify Reference Number  displayed in Order hisory page  ",
						" OrderNumber is not displayed in Order hisory page", referenceNumber,driver);
			}
		}
		if (isElementPresent(PONUMBER_DETAILSPAGE, "Purchase Number in Order history details Page")) {
			actualText = getAttributeByValue(PONUMBER_DETAILSPAGE, "Purchase Number in Order history details Page");

			if (actualText.equalsIgnoreCase(purchaseNumber)) {
				reporter.SuccessReport("Verify Purchase Number displayed in Order hisory page  ",
						" Purchase Number is dispalyed Order history details Page", purchaseNumber);
			} else {
				reporter.failureReport("Verify Purchase Number displayed in Order hisory page  ",
						" Purchase Number is not displayed in Order hisory page", purchaseNumber,driver);
			}
		}

		if (isElementPresent(getEWRNumber(EWR), "EWR Fee in Order history details Page")) {
			actualText = getAttributeByValue(getEWRNumber(EWR), "EWR Fee in Order history details Page");

			if (actualText.equalsIgnoreCase(EWR)) {
				reporter.SuccessReport("Verify EWR fee displayed in Order hisory page  ",
						" EWR fee is dispalyed Order history details Page", EWR);
			} else {
				reporter.failureReport("Verify EWR feer displayed in Order hisory page  ",
						" EWR fee  is not displayed in Order hisory page", EWR,driver);
			}
		}

	}

	/**
	 * This method is to verify the non SPLA message on cart screen
	 * 
	 * @throws Throwable
	 */
	public void VerifyNonSplaItemsMessage() throws Throwable {
		if (isElementPresent(NON_SPLA_MSG, "NON SPLA MESSASGE")) {
			reporter.SuccessReport("Verify non SPLA message", "non SPLA message displayed", "");
		} else {
			reporter.failureReport("Verify non SPLA message", "non SPLA message is not displayed", "");
		}
	}

	/**
	 * Method is to verify the reporting usage period
	 * 
	 * @throws Throwable
	 */
	public String verifyReportingUsagePeriod() throws Throwable {
		String period = null;
		if (isElementPresent(REPORTING_USAGE_PERIOD, "reporting usage period")) {
			 period = getText(REPORTING_USAGE_PERIOD, "reporting usage period");
			reporter.SuccessReport("verify reporting usage period in cart page",
					"Usage Field Exists and Verified. " + period, "");
		} else {
			reporter.failureReport("verify reporting usage period in cart page", "Usage Field does not Exists. ", "",driver);
		}
		return period;
	}

	public void clickOnLastUsageReportBtn(String softwareAgreement) throws Throwable {
		if (isElementPresent(CanadaObj.SPLA_LABEL, "SPLA LABEL")) {
			if (!isCheckBoxSelected(CanadaObj.getMySoftwareLicenseAgreementscheckBoxes(softwareAgreement))) {
				click(CanadaObj.getMySoftwareLicenseAgreementscheckBoxes(softwareAgreement),"SPLA Details Product CheckBox");
		if (isElementPresent(CITRIX_LASTUSAGE_REPORTBTN, "Retrive last usage report button")) {
			click(CITRIX_LASTUSAGE_REPORTBTN, " Citrix Retrive last usage report button");
		}
			}
		}
		
		}
	/**
	 * Method is to verify the reporting usage period in Receipt Page
	 * 
	 * @throws Throwable
	 */
	public void verifyReportingUsagePeriodInReceiptPage() throws Throwable {
		String period = null;
		if (isElementPresent(REPORTING_USAGEINRECEIPT_PAGE, "reporting usage period")) {
			 period = getText(REPORTING_USAGEINRECEIPT_PAGE, "reporting usage period");
			reporter.SuccessReport("Verify Usage Period on Receipt Page",
					"Usage Field Exists in Receipt Page " , period);
		} else {
			reporter.failureReport("Verify Usage Period on Receipt Page", "Usage report messgae was not found ", "",driver);
		}
	}
	public void CandaHomePageVerification() throws Throwable {
		if (isElementPresent(CANADA_FLAG, "Canada Flag")) {
			reporter.SuccessReport("Verify Canada Web Site Login", "User is Logged into Canada Insight", "");
		} else {
			reporter.failureReport("Verify Canada Web Site Login", "User is not Logged into Canada Insight", "",driver);
		}
	}

	public void selectProdCheckbox() throws Throwable {
		waitForVisibilityOfElement(CHKBOX_SELECT, "Product select checkbox");
		// isElementPresent(CartObj.ADD_TO_CART_SUCCESS_MESSAGE,"ADD TO CART
		// SUCCESS MESSAGE",true);
		if (isElementPresent(CHKBOX_SELECT, "product check box")) {
			if (!isCheckBoxSelected(CHKBOX_SELECT)) {
				driver.findElement(CHKBOX_SELECT).click();

				click(SELECT_PRODUCT, "Select product checkbox");
			}
			reporter.SuccessReport("Verify My Software License Agreements checkbox selected",
					"My Software License Agreements checkbox is selected", "");
		} else {
			reporter.failureReport("Verify My Software License Agreements checkbox selected",
					"My Software License Agreements checkbox has not been selected", "",driver);
		}
	}

	/**
	 * Method to search a product
	 * 
	 * @param product_name
	 * @throws Throwable
	 */

	public void SearchProduct(String product_name) throws Throwable {
		if (isElementPresent(SEARCH_PRODUCT, "Canada Search Product")) {
			type(SEARCH_PRODUCT, product_name, "Search product  name");
			reporter.SuccessReport("Verify Search Product textbox",
					"Search product textbox clicked and searched the required item", "");
		} else {
			reporter.failureReport("Verify Search Product textbox", "Search product textbox could not clicked", "",driver);
		}
	}

	public void verifyProductPrice() throws Throwable {
		waitForVisibilityOfElement(PROD_PRICE, "Canada Product Price");
		if (isElementPresent(PROD_PRICE, "Canada Product Price")) {
			reporter.SuccessReport("Price on Product Detail Page", "Price Exists", "");
		} else {
			reporter.failureReport("Price on Product Detail Page", "Price Does Not Exist", "",driver);
		}
	}

	public void verifyProratedPrice() throws Throwable {
		waitForVisibilityOfElement(PRORATED_PRICE, "Canada Product Prorated Price");
		if (isElementPresent(PRORATED_PRICE, "Canada Product Prorated Price")) {
			reporter.SuccessReport("Prorated Price displayed in the Cart Page", "Prorated Price is displayed", "");
		} else {
			reporter.failureReport("Prorated Price displayed in the Cart Page", "Prorated Price is Not displayed", "",driver);
		}
	}

	public void veriyProratedPriceinSBP() throws Throwable {
		waitForVisibilityOfElement(PRORATED_PRICE, "Canada Product Prorated Price");
		if (isElementPresent(FINAL_PRORATED_PRICE, "Canada Product Prorated Price")) {
			reporter.SuccessReport("Prorated Price displayed in the SBP Page", "Prorated Price is displayed", "");
		} else {
			reporter.failureReport("Prorated Price displayed in the SBP Page", "Prorated Price is Not displayed", "",driver);
		}
	}

	public void verifyProductsVisible() throws Throwable {
		waitForVisibilityOfElement(By.xpath("//a[@data-label='Solutions']"), "wait till products text displays");
	}

	public void continueToCheckout() throws Throwable {
		click(CONTINUE_TO_CHECKOUT, "Continue to checkout");
	}

	public void verifyReceiptOrderAndDate(String totalSummary) throws Throwable {
		String referenceNumber = null;
		clickUntil(OrderObj.PLACE_ORDER_BTN, OrderObj.RECEIPT_LABEL, "Place order button");
		reporter.SuccessReport("Verify Place order on Place order Page ", "Place order on Place order Page", "");
		Thread.sleep(3000);

		if (isElementPresent(OrderObj.RECEIPT_LABEL, "receipt")) {

			// Reference number verification
			if (isElementPresent(OrderObj.REFERENCE_ORDER_NUM, "Reference number")) {
				referenceNumber = getText(OrderObj.REFERENCE_ORDER_NUM, "Reference number");
				if (referenceNumber.isEmpty()) {
					reporter.failureReport("Verify the Reference number ", "The reference number is null or empty. ",
							"",driver);

				} else {
					reporter.SuccessReport("Verify the Reference number ", "The reference number: ", referenceNumber);
				}
			} else {
				reporter.failureReport("Verify the Reference number ", "The reference number is null or empty.", "",driver);
			}

			// Total Amount verification
			if (isElementPresent(OrderObj.TOTAL_AMOUNT, "Total Amount")) {
				String totalAmount = getText(OrderObj.TOTAL_AMOUNT, "Total Amount");

				if (totalSummary != null) {
					reporter.SuccessReport("Prorated Price displayed in the Receipt Page ",
							"Prorated Price is displayed ", totalAmount);
				} else {
					reporter.failureReport("Prorated Price displayed in the Receipt Page",
							"Prorated Price is not displayed ", "",driver);
				}
			}

			// date ordered verification
			if (isElementPresent(OrderObj.DATE_ORDERED, "Date ordered")) {
				String dateOrdered = getText(OrderObj.DATE_ORDERED, "Date ordered");
				if (dateOrdered != null) {
					reporter.SuccessReport("Verify the Date ordered ", " date ordered verification is successfull", "");
				} else {
					reporter.failureReport("Verify the Date ordered ",
							" date ordered verification is not successfull : ", "",driver);
				}
			}
		}
	}
	/**
	 * Method is to click on the report usage button in SLPA screen
	 * @param button
	 * @throws Throwable
	 */
	public void selectReportUsageButtonsInMySoftwareLicenseAgreements(String button) throws Throwable{
		
		if(isElementPresent(reportUsageButtons(button), "report usage button")){
			clickUntil(reportUsageButtons(button), CART_LABEL, "report usage button");
		}else{
			reporter.failureReport("Verify report usage buttons ", "Report usage button for product is not displayed", "",driver);
		}
	}
	/**
	 * method is to click on the Report zero usage for this period link
	 * @throws Throwable
	 */
	public void clickOnReportZeroUsageLinkOnCart() throws Throwable{
		waitForVisibilityOfElement(REPORT_ZERO_USAGE_LINK, "report zero usage");
		click(REPORT_ZERO_USAGE_LINK, "report zero usgae link");
	}
	
	/**
	 * Method to click on CreateAccount icon search page of Canada Page
	 * @throws Throwable 
	 * 
	 */
	
	public void clickCreateAccountOnCanadaSearchPage() throws Throwable{
		waitForVisibilityOfElement(CREATE_AN_ACCOUNT, "CREATE AN ACCOUNT");
		if (isElementPresent(CREATE_AN_ACCOUNT, "CREATE AN ACCOUNT", true)) {
			click(CREATE_AN_ACCOUNT, "CREATE AN ACCOUNT");
			reporter.SuccessReport("Verify create an Account is displayed and clicked  ", " create an Account is displayed and clicked , verification is successfull", "");
		}
		else 
		{
			reporter.failureReport("Verify CreateAccount is displayed and clicked ", " CreateAccount is not displayed and clicked", "",driver);
		}
	}

	
	/**
	 * This method is to click on Create an Account
	 * 
	 * @throws Throwable
	 */
	public void enterMailDetails(String userName) throws Throwable {
	
		waitForVisibilityOfElement(SewpObj.EMAIL_ADDRESS, "Email Address");
		if (isElementPresent(SewpObj.EMAIL_ADDRESS, "Email Address", true)) {	   
		typeText(SewpObj.EMAIL_ADDRESS, "UFT"+userName + "@Automation.com", "Email Address");		
		// Select agency
		typeText(SewpObj.PWD, "UFT"+userName+"$" , "Password");
		typeText(SewpObj.CRM_PWD,"UFT"+userName+"$", "Confirm Password");	
		Thread.sleep(3000);
		clickCustomCheckBox();
		clickOnNext();
	  }
	}
	

/*
 * PURPOSE OF METHOD : click on favorite shipping address and select an
 * adresses
 * 
 * @author : CIGNITI
 */
public void clickCustomCheckBox() throws Throwable {
	waitForVisibilityOfElement(CUSTOM_CHECKBOX, "Custom Check box");
	click(CUSTOM_CHECKBOX, "Custom Check box");
	
}
	


/*
 * PURPOSE OF METHOD : click on Next button on create account page
 * adresses
 * 
 * @author : CIGNITI
 */
public void clickOnNext() throws Throwable {
	waitForVisibilityOfElement(NEXT_BUTTON, "Custom Check box");
	click(NEXT_BUTTON, "Custom Check box");
	
}

/*
 * PURPOSE OF METHOD : verification of country displayed 
 * adresses
 * 
 * @author : CIGNITI
 */
public void verifyCountryDisplayed(String actualCountry) throws Throwable {
	
	waitForVisibilityOfElement(COUNTRY, "Country");
	if (isElementPresent(COUNTRY, "Country")) {		
	String expectedCountryName=driver.findElement(COUNTRY).getAttribute("innerText"); 
	if(actualCountry.equalsIgnoreCase(expectedCountryName)){
		reporter.SuccessReport("Verify Verify Country should default based on domain  ", "Verified country displayed as expected ", "");
	}
	
	else
	{
		reporter.failureReport("Verify Verify Country should default based on domain  ", " Verified country is not displayed as expected ", "",driver);
	  }
	 }
	}


/*
 * PURPOSE OF METHOD : click on Next button on create account page
 * adresses
 * 
 * @author : CIGNITI
 */
public void selectJobTitle(String jobTitle) throws Throwable {
	waitForVisibilityOfElement(JOBTITLE, "JobTitle");
	if (isElementPresent(JOBTITLE, "Jobtitle", true)){		
		selectByValue(JOBTITLE, jobTitle, "Select");
	}
	
}

/*
 * PURPOSE OF METHOD : Selects Option 
 * adresses
 * 
 * @author : CIGNITI
 */
public void selectOption(String option) throws Throwable {
	waitForVisibilityOfElement(getOptions(option), "JobTitle");
	if (isElementPresent(getOptions(option), "OtherOptions", true)){		
		click(getOptions(option), "Select ");
		
	}
	
}


	/*
	 * PURPOSE OF METHOD : Selects Option adresses
	 * 
	 * @author : CIGNITI
	 */
	public void clickCreateButton() throws Throwable {
		waitForVisibilityOfElement(CREATE, "Create button");
		if (isElementPresent(CREATE, "Create Button", true)) {
			click(CREATE, "Create Button ");
			waitForInVisibilityOfElement(ShipBillPayObj.WELCOMEMSG_DASHBOARD, "Welcome message");
		} else {
			reporter.failureReport("verify the create button presence", "Create button is not present or disabled", "",
					driver);
		}
	}

	/**
	 * method is to verify the Product Price With Given Price
	 * @param price
	 * @throws Throwable
	 */
	public void verifyProductPriceWithGivenPrice(String price) throws Throwable {
		waitForVisibilityOfElement(PROD_PRICE, "Canada Product Price");
		if (isElementPresent(PROD_PRICE, "Canada Product Price")) {
			String expectedPrice = getText(PROD_PRICE, "Canada Product Price").trim();
			if (price.equals(expectedPrice))
				reporter.SuccessReport("Price on Product Detail Page", "Price Exists", "");
		} else {
			reporter.failureReport("Price on Product Detail Page", "Price Does Not Exist", "", driver);
		}
	}


	/**
	 * This method is to enter the phone in add additional info
	 * 
	 * @return
	 * @throws Throwable
	 */
	public String enterPhoneOnAdditionalInfo(String phone) throws Throwable {
		//String phone = DynamicTestDataGenerator.generateRandomPhoneNumber();
		if (isElementPresent(MarriottIntlCorpObj.PHONE, "Phone Number")) {
			click(MarriottIntlCorpObj.PHONE, "Phone Number");
			type(MarriottIntlCorpObj.PHONE, phone, "Phone Number");
		}
		return phone;
	}
	/**
	 * This method is to enter the name in add additional info
	 * 
	 * @return
	 * @throws Throwable
	 */
	public String enterNameOnAdditionalInfo() throws Throwable {
		String name = DynamicTestDataGenerator.generateRandomFirstName();
		if (isElementPresent(MarriottIntlCorpObj.NAME, "Name")) {
			click(MarriottIntlCorpObj.NAME, "Name");
			type(MarriottIntlCorpObj.NAME, name, "Name");
		}
		return name;
	}

public void addShippingAddress(String name, String userName,String street1,String city,String state,String zipcode)

		throws Throwable {

	waitForVisibilityOfElement(COMPANY_NAME, "Company Name");
	if (isElementPresent(COMPANY_NAME, "Company Name")) {
		click(COMPANY_NAME, "Company Name");
		type(COMPANY_NAME, name, "Company Name");
		clearData(ATTENTION);
		click(ATTENTION, "attention");
		type(ATTENTION,"ShipTo:"+ userName, "attention");	
		clearData(STREET1);
		click(STREET1, "street1");
		type(STREET1, street1, "street1");	
		
		clearData(CITY);
		click(CITY, "CITY");
		type(CITY, city, "CITY");	
				
		selectByValue(STATE, state, "CITY");	
		clearData(ZIPCODE);
		click(ZIPCODE, "zipcode");
		type(ZIPCODE, zipcode, "zipcode");	
		
		
	}

}
  
/*
 * PURPOSE OF METHOD : Selects Option 
 * adresses
 * 
 * @author : CIGNITI
 */
public void verifyShippingMethod(String shipMehtod) throws Throwable {
	waitForVisibilityOfElement(getCarriers(shipMehtod), "JobTitle");
	if (isElementPresent(getCarriers(shipMehtod), "OtherOptions", true)){		
		
		String expectedShipMethod=driver.findElement(getCarriers(shipMehtod)).getAttribute("innerText"); 
		if(shipMehtod.equalsIgnoreCase(expectedShipMethod)){
			reporter.SuccessReport("Verify Canada "+shipMehtod+" Shipping is not 0.00 and Verify Canada Ground Shipping ", "Canada "+shipMehtod+" Shipping is not 0.00 and  ", "");
		}
		
		else
		{
			reporter.failureReport("Verify Canada "+shipMehtod+" Shipping is not 0.00 and Verify Canada Ground Shipping", "Canada "+shipMehtod+" Shipping is  0.00 ", "",driver);
		}
	}
}


/**
 * Method is used to click on Place order button 
 * 
 * @throws Throwable
 */
public void clickPlaceOrderButton() throws Throwable {
	clickUntil(OrderObj.PLACE_ORDER_BTN, OrderObj.RECEIPT_LABEL,"Place order button");
}


/**
 * Method is used to click on Place order button 
 * 
 * @throws Throwable
 */
public void getReferenceNo() throws Throwable {
	waitForVisibilityOfElement(REFERENCE_NO, "Reference Number");
	if (isElementPresent(REFERENCE_NO, "Reference Number", true) && isElementPresent(THANK_YOU_ORDER_MSG, "Thank you for order message")){		
	
	String refno= getText(REFERENCE_NO,"Reference Number");
	reporter.SuccessReport("Verify reference Number  ", "Reference Number  ", refno);
	}
}

/**
 * Method is used to click on enter a card 
 * 
 * @throws Throwable
 */
public void clickOnEnterACard(String cardOpt) throws Throwable {
	waitForVisibilityOfElement(getEnterACard(cardOpt), "Enter A Card Button");
	if (isElementPresent(getEnterACard(cardOpt), "Enter A Card Button", true)){		
	click(getEnterACard(cardOpt), "Enter A Card Button");
	reporter.SuccessReport("Click Enter a new card link on "+cardOpt+"",
			"Enter a new card Link  Exist ", "");
	}
	reporter.failureReport("Click Enter a new card link on "+cardOpt+"",
			"Enter a new card Link doesnot Exist  ", "",driver);
	}

	/**
	 * Method is used to click on Card type in enter a card details
	 * 
	 * @throws Throwable
	 */
	public void verifyNoDiscoverCard(String cardType) throws Throwable {
		waitForVisibilityOfElement(CLICK_CARDTYPE, "Card Type");
		if (isElementPresent(CLICK_CARDTYPE, "Card Type", true)) {
			click(CLICK_CARDTYPE, "Card Type");
			waitForVisibilityOfElement(CLICK_CARDTYPE, "Card Type");
			if (isElementPresent(CLICK_CARDTYPE, "Card Type", true)) {
				click(getCardType(cardType), "Card Type");
				reporter.SuccessReport("Verify Card Types on  Page ",
						"Discover Credit Card Does Not Exist  ", "");
			} else {
				reporter.failureReport("Verify Card Types on  Page ", "Discover Credit Card Exist  ","",driver);
			}

		}
		else{
			reporter.SuccessReport("Verify Card Types on  Page ",
					"Element is not present  ", "");
		}

	}
	
	
	/**
	 * Method is to verify country
	 * 
	 * @throws Throwable
	 */
	public void verifyCountry(String countryName) throws Throwable {
		waitForVisibilityOfElement(getCountryFlag(countryName), "Enter A Card Button");
		if (isElementPresent(getCountryFlag(countryName), "Enter A Card Button", true)){		
		//click(getCountryFlag(countryName), "Enter A Card Button");
		reporter.SuccessReport("Verify country name as "+countryName+"",
				"CountryName verified ", countryName);
		}
		reporter.failureReport("Verify country name as "+countryName+"",
				"CountryName not existed ", countryName,driver);
		}
	
	/**
	 * Method is to get standard report options
	 * 
	 * @throws Throwable
	 */
	public void clickOnReportOptions(String reportOption) throws Throwable {
		waitForVisibilityOfElement(getReportOptions(reportOption), "Standard Report Options");
		if (isElementPresent(getReportOptions(reportOption), "Standard Report Options", true)){		
		click(getReportOptions(reportOption), "Enter A Card Button");
		reporter.SuccessReport("Click "+reportOption+" on Reports Page" ,
				""+reportOption+" Reports Page exists  ", "");
		}
		else{
			reporter.failureReport("Click "+reportOption+" on Reports Page" ," "+reportOption+" Reports Page exists  ", "",driver);
		}
	}
	
	
	/**
	 * Method is to verify reports page
	 * 
	 * @throws Throwable
	 */
	public void verifyReportsPage() throws Throwable {
		waitForVisibilityOfElement(REPORTS_PAGE, "Reports");
		if (isElementPresent(REPORTS_PAGE, "Reports", true)){		
		click(REPORTS_PAGE, "Reports");
		reporter.SuccessReport("Verify Reports Page",
				"Reports Page Loaded", "");
		}
		else{
			reporter.failureReport("Verify Reports Page",
					"Reports Page not Loaded", "");
		}
		}
	
	/**
	 * Method is to verify Select A Report
	 * 
	 * @throws Throwable
	 */
	public void verifySelectReport(String selectReport) throws Throwable {
		waitForVisibilityOfElement(getSelectAReport(selectReport), "Select A Report");
		if (isElementPresent(getSelectAReport(selectReport), "Select A Report", true)){			
		reporter.SuccessReport("Verify  "+selectReport+" is Default to Select Report on Reports Page",
				"Select Report Defaulted to "+selectReport+" Reports", "");
		}
		else{
			reporter.failureReport("Verify  "+selectReport+" is Default to Select Report on Reports Page",
					"Select Report Not Defaulted to "+selectReport+" Reports", "",driver);
		}
		}
	
	/**
	 * Method is to verify account selections
	 * 
	 * @throws Throwable
	 */
	public void verifyAccountSelections(String account) throws Throwable {
		waitForVisibilityOfElement(getAccountSelections(account), "Select A Report");
		if (isElementPresent(getAccountSelections(account), "Select A Report", true)){			
		reporter.SuccessReport("Verify  "+account+" is Default to Account Selections on Reports Page",
				"Account Selections are  Defaulted to "+account+"", "");
		}
		else {
			reporter.failureReport("Verify  "+account+" is Default to Select Report on Reports Page",
					"Account Selections are Not Defaulted to "+account+"", "",driver);
		}
		}
	
	
	/**
	 * Method is to verify filter by currency
	 * 
	 * @throws Throwable
	 */
	public void verifyFilterbyCurrency(String currency) throws Throwable {
		waitForVisibilityOfElement(getFilterByCurrency(currency), "Select A Report");
		if (isElementPresent(getFilterByCurrency(currency), "Select A Report", true)){			
		reporter.SuccessReport("Verify 'Convert all transactions to' is Default to CAD on Reports Page",
				"Convert all transactions to' Amount is Not Default to CAD", "");
		}
		else{
			reporter.failureReport("Verify 'Convert all transactions to' is Default to CAD on Reports Page",
					"'Convert all transactions to' Amount is Not Default to CAD", "",driver);
		}
		}
	

	/**
	 * Method is to verify filter radio button
	 * 
	 * @throws Throwable
	 */
	public void verifyFilterOption() throws Throwable {
		waitForVisibilityOfElement(FILTERCURRENT_OPTION, "Filter Option");
		if (isElementPresent(FILTERCURRENT_OPTION, "Filter Option", true)){			
		reporter.SuccessReport("Verify Convert all transactions to Filter Option Default on Reports Page",
				"Convert all transactions to' Filter is not Defaulted Option", "");
		}
		else{
			reporter.failureReport("Verify Convert all transactions to Filter Option Default on Reports Page",
					"Convert all transactions to' Filter is not Defaulted Option", "",driver);
		}
		}
	/**
	 * Method is to verify filter radio button
	 * 
	 * @throws Throwable
	 */
	public void verifyScheduleReport(String scheduleOption) throws Throwable {
		waitForVisibilityOfElement(getScheduleReport(scheduleOption), "Schedule Report Option ");
		if (isElementPresent(getScheduleReport(scheduleOption), "Select A Report", true)){			
		reporter.SuccessReport("Verify Schedule Report is Defaulted to 'Run Report Now' on Reports Page",
				"Schedule Report is Defaulted to 'Run Report Now'", "");
		}
		else{
			reporter.failureReport("Verify Schedule Report Defaulted to 'Run Report Now' on Reports Page",
					"Schedule Report is not Defaulted to 'Run Report Now", "",driver);
		}
		}
	/**
	 * Method is to verify filter radio button
	 * 
	 * @throws Throwable
	 */
	public void verifyDeliveryOption() throws Throwable {
		waitForVisibilityOfElement(DELIVERY_OPTION, "Delivery Option");
		if (isElementPresent(DELIVERY_OPTION, "Delivery Option", true)){			
		reporter.SuccessReport("View Delivery Options on Reports Page",
				"Report Delivery Options Field Exists", "");
		}
		else{
			reporter.failureReport("View Delivery Options on Reports Page",
					"Report Delivery Options Field  Not Exists", "",driver);
		}
		}
	
	/**
	 * Method is to select account selections
	 * 
	 * @throws Throwable
	 */
	public void clickOnAccountSelections(String accountSelctionOtion) throws Throwable {
		waitForVisibilityOfElement(ACCOUT_SELECTION, "Select Account selections");
		if (isElementPresent(ACCOUT_SELECTION, "Select Account selections", true)){		
			
			selectByVisibleText(ACCOUT_SELECTION,accountSelctionOtion,"Account Selections");
		reporter.SuccessReport("Verify  "+accountSelctionOtion+" is selected to Account Selections on Reports Page",
				"Account Selections are selected to "+accountSelctionOtion+"", "");
		}
		else {
			reporter.failureReport("Verify  "+accountSelctionOtion+" is selected to Select Report on Reports Page",
					"Account Selections are Not selected to "+accountSelctionOtion+"", "",driver);
		}
		}
	
	/**
	 * Method is to verify Quick Date Option
	 * 
	 * @throws Throwable
	 */
	public void verifyQuickDateOption(String quickDateOption) throws Throwable {
		waitForVisibilityOfElement(getQuickDate(quickDateOption), "Delivery Option");
		if (isElementPresent(getQuickDate(quickDateOption), "Delivery Option", true)){			
		reporter.SuccessReport("Verify Quick Date Options is Defaulted to 'Current Month' on Reports Page",
				"Quick Date is Defaulted to 'Current Month'", "");
		}
		else{
			reporter.failureReport("Verify Quick Date Options is Defaulted to "+quickDateOption+" on Reports Page",
					"Quick Date is Not Defaulted to "+quickDateOption+" ", "",driver);
		}
		}
	
	
	/**
	 * Method is to verify start and end custome dates 
	 * @throws Throwable
	 */
	public void verifyCustomDate() throws Throwable {
		waitForVisibilityOfElement(START_DATE, "START DATE");
		if (isElementPresent(START_DATE, "START DATE", true) && isElementPresent(END_DATE, "END DATE", true)){
			
		  reporter.SuccessReport("Verify Start Date and End Date Defaults to 'Custom Dates' on Reports Page",
					"Custom Start Date and End Date is Defaulted to 'Custom Dates'", "");
			
		}
		else{
			reporter.failureReport("Verify Start Date and End Date  is present on Reports Page",
					"Start Date and End Date  is  not present on Reports Page", "",driver);
		}
	}

	/**
	 * @throws Throwable 
	 * 
	 */
		public void verifyDates() throws Throwable{
		String startDateUI=getAttributeByValue(START_DATE, "START DATE");
		String endDateUI=getAttributeByValue(END_DATE, "START DATE");			
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddHHMM");
		Date startDate1=dateFormat1.parse(startDateUI);
		dateFormat1=new SimpleDateFormat("YYYY-MM-DD");
		String Final_startDT=dateFormat1.format(startDate1);
		Date endDate1=dateFormat1.parse(endDateUI);
		dateFormat1=new SimpleDateFormat("YYYY-MM-DD");
		String Final_endDT=dateFormat1.format(endDate1);
		LocalDate today = LocalDate.now();
		String startDate= today.withDayOfMonth(1).toString();
		String endDate= today.withDayOfMonth(today.lengthOfMonth()).toString();
		
		if(Final_startDT.equals(startDate) && Final_endDT.equals(endDate) ){
			
			reporter.SuccessReport("Verify Custom Start Date Default on Reports Page",
					"Custom Start Date is Defaulted to First Day of Current Month", "");
			reporter.SuccessReport("Verify Custom End Date Default on Reports Page",
					"Custom Start Date is Defaulted to End Day of Current Month", "");
		}

	else{
		reporter.failureReport("Verify Custom Start Date Default on Reports Page",
				"Custom Start Date is Not Defaulted to First Day of Current Month", "",driver);
		reporter.failureReport("Verify Custom End Date Default on Reports Page",
				"Custom Start Date is Not Defaulted to First Day of Current Month", "",driver);
			}
	}
	/**
	 * Method is to verify Quick Date Option
	 * 
	 * @throws Throwable
	 */
	public void verifyFilterOrder() throws Throwable {
		waitForVisibilityOfElement(FILTER_ORDERTYPE, "FilterOrder type");
		if (isElementPresent(FILTER_ORDERTYPE, "FilterOrder type", true)){			
		reporter.SuccessReport("Verify Filter by Order Type is Default to 'All' on Reports Page",
				"Filter by Order Type  is Defaulted to 'All'", "");
		}
		else{
			reporter.failureReport("Verify Filter by Order Type is Default to 'All' on Reports Page",
					"Filter by Order Type  is  Not Defaulted to 'All'", "",driver);
		}
		}
	
	/**
	 * Method is to verify Delivery Format
	 * 
	 * @throws Throwable
	 */
	public void verifySmartcheck() throws Throwable {
		waitForVisibilityOfElement(SMART_CHECK, "Smart Check");
		if (isElementPresent(SMART_CHECK, "Smart Check", true)){			
		reporter.SuccessReport("Verify Smart Tracker Check Box on Reports Page",
				"Smart Tracker Check Box Exists and UnChecked", "");
		}
		else{
			reporter.failureReport("Verify Smart Tracker Check Box on Reports Page",
					"Smart Tracker Check Box Exists and Checked", "",driver);
		}
		}
	
	
	/**
* Method is to verify All Available Fields
* 
* @throws Throwable
*/
public void verifyAllFields() throws Throwable {

String option ="Account City";
waitForVisibilityOfElement(AVAILABLE_FIELDS, "Available fields");
if (isElementPresent(AVAILABLE_FIELDS, "Available fields", true)) {

  new Select(driver.findElement(AVAILABLE_FIELDS)).selectByVisibleText(option);
  driver.findElement(AVAILABLE_FIELDS).sendKeys(Keys.CONTROL + "a");;
	click(ADD_FIELD, "Add field");				

reporter.SuccessReport("VerifyAll Available Fields are Moved to Select Fields to Return Web List on Reports Page ",
		"Available Fields are Moved to Selected Fields to Return Web List", "");

} else {
reporter.failureReport("Select  All Fields in the Available Fields on Reports Page",
		"All Fields are not Selected and Maoved in the Available Fields List", "",driver);
}



}
	/**
	 * Method is to select account selections
	 * 
	 * @throws Throwable
	 */
	public void clickOnDeliveryMethod(String deliveryMethod) throws Throwable {
		waitForVisibilityOfElement(DELIVERYMETOD, "Select Delivery Method");
		if (isElementPresent(DELIVERYMETOD, "Select ADelivery Method", true)){		
			
			selectByVisibleText(DELIVERYMETOD,deliveryMethod ,"Delivery Option");
		reporter.SuccessReport("Verify  "+deliveryMethod+" is selected to Account Selections on Reports Page",
				"Delivery Option are selected to "+deliveryMethod+"", "");
		}
		else {
			reporter.failureReport("Verify  "+deliveryMethod+" is selected to Select Delivery Options on Reports Page",
					"Delivery Option are Not selected to "+deliveryMethod+"", "",driver);
		}
		}
	/**
	 * Method is to select account selections
	 * 
	 * @throws Throwable
	 */
	public void clickOnDeliveryFormat(String deliveryFormat) throws Throwable {
		waitForVisibilityOfElement(DELIVERY_FORMAT, "Select Delivery  format");
		if (isElementPresent(DELIVERY_FORMAT, "Select Delivery  format", true)){		
			
			selectByVisibleText(DELIVERY_FORMAT, deliveryFormat,"Delivery Option");
		reporter.SuccessReport("Verify  "+deliveryFormat+" is selected to Account Selections on Reports Page",
				"Delivery Option are selected to "+deliveryFormat+"", "");
		}
		else {
			reporter.failureReport("Verify  "+deliveryFormat+" is selected to Select Delivery Options on Reports Page",
					"Delivery Option are Not selected to "+deliveryFormat+"", "",driver);
		}
		}

	
	/**
	 * Method is to select account selections
	 * 
	 * @throws Throwable
	 */
	public void clickOnRun() throws Throwable {
		waitForVisibilityOfElement(RUN, "Run button");
		if (isElementPresent(RUN,"Run button", true)){		
			
		 click(RUN, "Run button");
		reporter.SuccessReport("Verify  Click Run on Reports Page",
				"Run Reports Field Exists and Clicked", "");
		}
		else {
			reporter.failureReport("Verify  Click Run on Reports Page",
					"Run Reports Field Not Exists and Clicked", "",driver);
		}
		}
	
	/**
	 * Method to verify download excel file
	 * 
	 * @throws Throwable
	 */
	public void verifyDownloadedReportExcelFile(List<String> actualData, String filePath) throws Throwable {
		Thread.sleep(10000);
		File root = new File("C:\\Users\\e004303\\Downloads");
		FilenameFilter beginswithm = new FilenameFilter() {
			public boolean accept(File directory, String filename) {
				return filename.startsWith(filePath);
			}
		};
		File[] files = root.listFiles(beginswithm);

		if (files[0] != null) {
			// PATH

			// load file
			FileInputStream fis = new FileInputStream(files[0]);
			// Load workbook
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			List<String> expectedData = new ArrayList<String>();
			XSSFSheet sh1 = wb.getSheetAt(0);
			String data1 = sh1.getRow(0).getCell(0).getStringCellValue();
			expectedData.add(data1);
			if (sh1.getRow(2).getCell(0).getStringCellValue() != null) {
				String data3 = sh1.getRow(2).getCell(0).getStringCellValue();
				expectedData.add(data3);
			} else {
				String data3 = sh1.getRow(3).getCell(0).getStringCellValue();
				expectedData.add(data3);
			}

			Assert.assertEquals(actualData, expectedData);
			reporter.SuccessReport("Verify the Excel Data ", "Excel Data is present as expected", "");

		}
	}
	
	/**
	 * This method is to verify the Filter By manufacturer in the search results page
	 * @throws Throwable
	 */
	public void verifyFilterByManufacturerOnSearchResultsPage() throws Throwable{
		if(isElementPresent(FILTER_BY_MANUFACTURER, "Filter By manufacturer")){
			reporter.SuccessReport("Verify filter by manufacturer ", "Filter by manufacturer is present", "");
		}else{
			reporter.failureReport("Verify filter by manufacturer ", "Filter by manufacturer is not present", "");
		}
	}
	
	/**
	 * Add line level info name & Phone number
	 * @param name
	 * @param phone
	 * @throws Throwable
	 */
	public void addAdditionalInfo(String name, String phone)throws Throwable {

		waitForVisibilityOfElement(MarriottIntlCorpObj.NAME, "Name");
		if (isElementPresent(MarriottIntlCorpObj.NAME, "Name")) {
			click(MarriottIntlCorpObj.NAME, "Name");
			type(MarriottIntlCorpObj.NAME, DynamicTestDataGenerator.generateRandomFirstName(), "Name");
			clearData(MarriottIntlCorpObj.PHONE);
			click(MarriottIntlCorpObj.PHONE, "Phone Number ");
			type(MarriottIntlCorpObj.PHONE, DynamicTestDataGenerator.generateRandomPhoneNumber(), "Phone Number");	
			click(MarriottIntlCorpObj.CONTINUE, "Continue button of Shipping address");		
		}

	}
	
	/**
	 * Method is to click on  "Report As Admin Login"
	 * 
	 * @throws Throwable
	 */
	public void verifyWelcomePage(String email) throws Throwable {
		waitForVisibilityOfElement(getwelcomemsg(email), "verifies welcome page ");
		isElementPresent(getwelcomemsg(email), "verifies welcome page", true);			
				
				
}
	
	/**
	 * Method is to vaerify Contract Dropdown
	 * 
	 * @throws Throwable
	 */
	public void verifyContractDropdown() throws Throwable {
		if(isElementPresent(searchLib.CONTRACT_DD, "contract title")){
			reporter.SuccessReport("Verify that the system will not display soldto","Contract Link does not Exist : ","" );
		}else{
			reporter.failureReport("Verify that the system will not display soldto","Contract Link  Exist : ","" );
		}
				
}
	/**
	 * Method is to click on  "Report As Admin Login"
	 * 
	 * @throws Throwable
	 */
	public void verifyOrdersLinkOnAcctTools(String toolsMenuName) throws Throwable {
		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		
		if(isElementPresent(getAccountToolsMenu(toolsMenuName), "Account tools menu"))
				{
			reporter.failureReport("Verify that Orders link should not available in Account Tool","Orders Link does  Exist : ","" );
		}else{
			reporter.SuccessReport("Verify that Orders link should not available in Account Tool","Orders Link does not Exist : ","" );
		}
				
}
	
	
}
