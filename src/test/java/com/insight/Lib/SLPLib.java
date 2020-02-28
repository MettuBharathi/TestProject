package com.insight.Lib;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebElement;

import com.insight.ObjRepo.CanadaObj;
import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.OrderObj;
import com.insight.ObjRepo.SLPObj;
import com.insight.ObjRepo.ShipBillPayObj;
import com.insight.ObjRepo.productsDisplayInfoObj;

public class SLPLib extends SLPObj {
	CommonLib commonLib=new CommonLib();
	String expectedWarrantyItemDec;
	String expectedSummaryTotalAmount;
	String referenceNumber;

	/**
	 * Method is used to get price from Product detail page
	 * 
	 * @throws Throwable
	 */
	public String getpricefromProductdetailpage() throws Throwable {
		String Price = getText(PRICE_PRODUCTDETAILPAGE, "Actual Price");
		return Price;
	}

	/**
	 * 
	 * @param Actualprice
	 * @return
	 * @throws Throwable
	 */
	public void verifyProrationincartpage(String PartNum, double Actualprice) throws Throwable {
		String Proratedprice = getText(Priceincart(PartNum), "Price in cart page").replace("$", "");
		Double actualProratedprice = Double.valueOf(Proratedprice);
		if (Proratedprice.equals(Actualprice) && actualProratedprice >= Actualprice) {
			reporter.failureReport("Prorartion::", "ProratedPrice Matches With Actual Price:", Proratedprice);
		} else {
			reporter.SuccessReport("Prorartion::",
					"ProratedPrice Matches does not With Actual Price. Proratedprice-" + Proratedprice + "",
					"Actualprice:: " + Actualprice + "");
		}
	}

	/**
	 * 
	 * @param Actualprice
	 * @throws Throwable
	 */
	public void verifyProrationinplaceorderpage(String AmountType, double Actualprice) throws Throwable {
		String Subtotalamount = getText(ShipBillPayObj.getAmountsInSummary(AmountType),"Subtotal Amount in Place Order Page").replace("USD $", "");
		Double actualSubTotalAmt = Double.valueOf(Subtotalamount);
		if (Subtotalamount.equals(Actualprice) && actualSubTotalAmt >= Actualprice) {
			reporter.failureReport("Prorartion::", "ProratedPrice Matches With Actual Price:", Subtotalamount);
		} else {
			reporter.SuccessReport("Prorartion::",
					"ProratedPrice Matches does not With Actual Price. Proratedprice-" + Subtotalamount + "",
					"Actualprice:: " + Actualprice + "");

		}
	}

	/**
	 * 
	 * @param Actualprice
	 * @throws Throwable
	 */
	public void verifyProrationinrecieptpage(double Actualprice) throws Throwable {
		String Proratedprice = getText(OrderObj.TOTAL_AMOUNT,"Total Amount In Reciept Page");
		Double actualProratedprice = Double.valueOf(Proratedprice);
		if (Proratedprice.equals(Actualprice) && actualProratedprice >= Actualprice) {
			reporter.failureReport("Prorartion::", "ProratedPrice Matches With Actual Price:", Proratedprice);
		} else {
			reporter.SuccessReport("Prorartion::",
					"ProratedPrice Matches does not With Actual Price. Proratedprice-" + Proratedprice + "",
					"Actualprice:: " + Actualprice + "");
		}
	}

	
	/**
	 * Method is to compare the prorated price with other prices
	 * @param Actualprice
	 * @throws Throwable 
	 */
	public void verifyProrationPrice(double totalAmount,double Actualprice) throws Throwable{
		if (totalAmount == Actualprice && totalAmount >= Actualprice) {
			reporter.failureReport("Prorartion::", "ProratedPrice Matches With Actual Price:"+ totalAmount,"");
		} else {
			reporter.SuccessReport("Prorartion::",
					"ProratedPrice Matches does not With Actual Price. Proratedprice-" + totalAmount + "",
					"Actualprice:: " + Actualprice + "");
		}
	}
	
	/**
	 * This method is to click on the reference number in the requisition history
	 * @param refNum
	 * @throws Throwable 
	 */
	public void selectReferenceNumFromRequisitionSearchResults(String refNum) throws Throwable{
		click(ShipBillPayObj.Requesitor(refNum), "Click on requesitor");
		isElementPresent(ShipBillPayObj.APPROVAL_MANAGEMENT_LABEL, "Approval Management",true);
	}
	
	/**
	 * This method is to select approve radio button in the approval management page - requisition history 
	 * @throws Throwable
	 */
	public void selectApproveRadioButtonOnApprovalManagementPage() throws Throwable{
		if(isCheckBoxSelected(APPROVE_RADIO_BTN)){
			LOG.info("Radio button alreaded selected");
			
		}else{
			click(APPROVE_RADIO_BTN, "update approval status - Approve radio button");
		}
	}
	
	/**
	 * This method is to click on update button in the approval management page and verify the success message.
	 * @param refNum
	 * @throws Throwable
	 */
	public void updateRequisitionAndVerify(String refNum) throws Throwable{
		click(ShipBillPayObj.APPROVEREQUISITOR_UPDATEBUTTON, "Update button");
		if(isElementPresent(getRequisitionApprovedMsg(refNum),"Requisition approval message")){
			reporter.SuccessReport("Verify requisition success message", "Requisition has been approved for ref num"+refNum, "");
		}else{
			reporter.failureReport("Verify requisition success message", "Requisition has not approved for ref num"+refNum, "");
		}
	}
	


	/**
	 * 
	 * @param This method is to verify deploydate in cart page
	 * @throws Throwable
	 */
	public void verifydeploydate(String partnum) throws Throwable {
		String Deploydate = getText(deploy_date(partnum), "Deploy Date");
		if (isElementPresent(deploy_date(partnum), "Deploy Date")) {
			reporter.SuccessReport("Deploy Date::", "Deploy Date Exists As Expected in Cart Page", ""+Deploydate+"");
		} else {
			reporter.failureReport("Deploy Date::", "Deploy Date Doesnot Exists As Expected", "");
		}

	}

	/**
	 * 
	 * @param This method is to verify license type in cart page
	 * @throws Throwable
	 */
	public void verifylicensetype(String partnum) throws Throwable {
		String LicenseType = getText(licensetype(partnum), "License type");
		if (isElementPresent(licensetype(partnum), "License Type")) {
			reporter.SuccessReport("License Type::", "New  for MPSA Products in the Cart Exists As Expected",
					LicenseType);
		} else {
			reporter.failureReport("License Type::", "New  for MPSA Products in the Cart Doesnot Exists", "");
		}

	}

	/**
	 * 
	 * @param This method is to verify license type Not Exists in cart page
	 * @throws Throwable
	 */
	public void verifylicensetypenotexists(String partnum) throws Throwable {
		if (isElementPresent(licensetype(partnum), "License Type")) {
			reporter.failureReport("License Type::", "New  for MPSA Products in the Cart Exists", "");
		} else {
			reporter.SuccessReport("License Type::", "New  for MPSA Products in the Cart Doesnot Exists As Expected",
					"");
		}
	}
	
	/*
	 * Method is to click on place requisition button on the place order page
	 */
	public void clickPlaceRequisition() throws Throwable{
		click(PLACE_REQUISITION,"PLACE REQUISITION");
	}
	
	public void verifyReceiptPageOrderDetails(String totalSummary) throws Throwable{

		if (isElementPresent(OrderObj.RECEIPT_LABEL, "receipt")) {

			// Reference number verification
			if (isElementPresent(OrderObj.REFERENCE_ORDER_NUM, "Reference number")) {
				referenceNumber = getText(OrderObj.REFERENCE_ORDER_NUM, "Reference number");
				if(referenceNumber.isEmpty()){
					reporter.failureReport("Verify the Reference number ", "The reference number is null or empty. ","");
					
				}else{
					reporter.SuccessReport("Verify the Reference number ", "The reference number: " , referenceNumber);
				}
			} else{
				reporter.failureReport("Verify the Reference number ", "The reference number is null or empty.","");
			}
			
			// Total Amount verification
			if (isElementPresent(OrderObj.TOTAL_AMOUNT, "Total Amount")) {
				String totalAmount = getText(OrderObj.TOTAL_AMOUNT, "Total Amount");
				if(totalSummary.equals(totalAmount)){
					reporter.SuccessReport("Verify the Total Amount ", "The Total Amount verification is successfull: " , totalAmount);
				}else{
					reporter.failureReport("Verify the Total Amount ", "The Total Amount is not updated correctly. ","");
				}
			} else {
				reporter.failureReport("Verify the Total Amount ", "The Total Amount is not updated. ","");
			}

			// date ordered verification
			if (isElementPresent(OrderObj.DATE_ORDERED, "Date ordered")) {
				String dateOrdered = getText(OrderObj.DATE_ORDERED, "Date ordered");
				String actualDate = getCurrentDateTime("dd-MMM-yyyy");
				if (actualDate.contains(dateOrdered)) {
					reporter.SuccessReport("Verify the Date ordered ", " date ordered verification is successfull","");
				} else {
					reporter.failureReport("Verify the Date ordered ", " date ordered verification is not successfull : "+dateOrdered+" .Expected Date :",actualDate);
				}
			}
		}
	}

	/**
	 * 
	 * @param This method is to verify copy to all Link Not Exists in cart page
	 * @throws Throwable
	 */
	public void verifycopytoallnotexists(String partnum) throws Throwable {
		if (isElementPresent(copytoall(partnum), "Copy to All")) {
			reporter.failureReport("Copy to All::", "Copy To All Link Cart Exists", "");
		} else {
			reporter.SuccessReport("Copy to All::", "Copy To All Link in Cart not Exists As Expected", "");
		}
	}

	/**
	 * 
	 * @param This method is to verify copy to all Link Not Exists in cart page
	 * @throws Throwable
	 */
	public void verifycopytoallexists(String partnum) throws Throwable {
		if (isElementPresent(copytoall(partnum), "Copy to All")) {
			clickUntil(copytoall(partnum),DEPLOY_DATEINPOPUP, "Copy to All");
			reporter.SuccessReport("Copy to All::", "Copy To All Link Cart Exists As Expected and Selected", "");
		} else {
			reporter.failureReport("Copy to All::", "Copy To All Link Cart Not Exists", "");
		}
	}
	
	
	public void verifydeployedatewithcurrentdate()throws Throwable {
		if (isElementPresent(DEPLOY_DATEINPOPUP, "Deploy Date")) {
			String deploydate = getText(DEPLOY_DATEINPOPUP, "Date ordered");
			String actualDate = getCurrentDateTime("dd-MMM-yyyy");
			if (actualDate.contains(deploydate)) {
				reporter.SuccessReport("Deploy Date:: ", "verification of deploy date with current date is successfull","");
			} else {
				reporter.failureReport("Deploy Date:: ", "verification of deploy date with current date is Not successfull : "+deploydate+" .Expected Date :",actualDate);
			}
		}
		
	}
	/**
	 * 
	 * @param This method is to verify Change Link Exists and clicked in cart page
	 * @throws Throwable
	 */
	public void verifychangeexists(String partnum) throws Throwable {
		if (isElementPresent(Change(partnum), "Change")) {
			clickUntil(Change(partnum),DEPLOY_DATEINPOPUP, "Change");
			reporter.SuccessReport("Change::", "Change Link Cart Exists As Expected and Selected", "");
		} else {
			reporter.failureReport("Change::", "Change Link Cart Not Exists", "");
		}
	}
	
	/**
	 * 
	 * @param This method is to get Popup data
	 * @throws Throwable
	 */
	public void POPUPdata(String PopUpData) throws Throwable {
		if (isElementPresent(PRORATIONPOPUP_DATA, "Change")) {
			List<WebElement> myList = driver.findElements(productsDisplayInfoObj.MFR_PART);
			List<String> all_elements_text = new ArrayList<>();
			for (int i = 0; i < myList.size(); i++) {
				all_elements_text.add(myList.get(i).getText());
				PopUpData = myList.get(i).getText().trim();
				LOG.info(PopUpData);
		}
			reporter.SuccessReport("PopUpData::", "PopUpData Qty,Mfr Num and Description  Exists As Expected", "");
		}
			else {
			reporter.failureReport("PopUpData::", "PopUpData Not Exists", "");
		}
	}
	
	/**
	 * 
	 * @param This method is to verify PartNum in PopUp Data 
	 * @throws Throwable
	 */
	public void verifyPartNuminPopUpData(String partnum) throws Throwable {
		String PartNum = getText(PartNuminpopupdata(partnum), "PartNum");
		if (PartNum.equals(partnum)) {
			reporter.SuccessReport("PartNum::", "PartNum in popupdata Matches SerachedProduct PartNum", "");
		} else {
			reporter.failureReport("PartNum::", "PartNum in popupdata not Matches SerachedProduct PartNum", "");
		}
	}
	/**
	 * 
	 * @param This method is to Click on Apply Button
	 * @throws Throwable
	 */
	public void clickapply() throws Throwable {
		click(APPLYBUTTON,"Applybutton");
		
	}
	/**
	 * 
	 * @param This method is to verify PartNum in PopUp Data 
	 * @throws Throwable
	 */
	public void verifyUpdatedLisence(String partnum,String previouseLicense) throws Throwable {
		
		String UpadtedLicense =getText(licensetype(partnum),"License");
		System.out.println("UpadtedLicense::"+UpadtedLicense);
		if (UpadtedLicense.equals(previouseLicense)) {
			reporter.failureReport("Updated License::", "Updated License is Not Displayed", "");
		} else {
			reporter.SuccessReport("Updated License::", "License is Updated and Displayed", "");
		}
	}
	/**
	 * 
	 * @param This method is to get license 
	 * @throws Throwable
	 */
	public String getlicense(String partnum) throws Throwable {
		waitForVisibilityOfElement(licensetype(partnum), "License");
		String lisence = getText(licensetype(partnum), "License").trim();
		return lisence;
	}
	/**
	 * 
	 * @param This method is to get deploydate 
	 * @throws Throwable
	 */
	public String getDeploydate(String partnum) throws Throwable {
		waitForVisibilityOfElement(deploy_date(partnum), "deploy_date");
		String deploydate = getText(deploy_date(partnum), "deploy_date").trim();
		return deploydate;
	}
	/**
	 * 
	 * @param This method is to verify PartNum in PopUp Data 
	 * @throws Throwable
	 */
	public void verifydeploydateandlicensetypeforcartbundle(String Date) throws Throwable {
		waitForVisibilityOfElement(CartObj.PART_NUMBER, "Part Number in cart");
		List<WebElement> myList1=driver.findElements(CartObj.PART_NUMBER);
		for (int j = 0;j<2; j++) {
			String[] Mfrtnum=myList1.get(j).getText().split(":");
			verifydeploydate(Mfrtnum[1].trim());
			verifyProarationText(Mfrtnum[1].trim());
			if(isElementPresent(Change(Mfrtnum[1].trim()),"Change Link Exists"))
			{
			String Lisence=getlicense(Mfrtnum[1].trim());
			verifylicensetype(Mfrtnum[1].trim());
			verifychangeexists(Mfrtnum[1].trim());
			Clickonunpaidlicense();
			verifydeployedatewithcurrentdate();
	     	calenderforUnpaidLicense(Date);
	     	verifyPartNuminPopUpData(Mfrtnum[1].trim());
	     	clickapply();
	     	Thread.sleep(6000);
	     	verifyUpdatedLisence(Mfrtnum[1].trim(),Lisence);
	     	verifycopytoallexists(Mfrtnum[1].trim());
	     	Thread.sleep(3000);
			}
			else{
				verifylicensetypenotexists(Mfrtnum[1].trim());
				verifycopytoallnotexists(Mfrtnum[1].trim());
			}
				
		}
		
	}
	/**
	 * 
	 * @param This method is to verify PartNum in PopUp Data 
	 * @throws Throwable
	 */
	public void verifyProarationText(String Partnum) throws Throwable {
		String  ProrationText= getText(Prorationtext(Partnum), "License").trim();
		if (isElementPresent(Prorationtext(Partnum), "Proration Text")) {
			reporter.SuccessReport("Proration Text::", "Proration Text Exists", ""+ProrationText+"");
		} else {
			reporter.failureReport("Proration Text::", "Proration Text not Exists", "");
		}
	}
	
	/**
	 * 
	 * @param This method is to set Date for Unpaid License
	 * @throws Throwable
	 */
	public void calenderforUnpaidLicense(String date)throws Throwable {
		String day=date.split("-")[0];
		String month=date.split("-")[1];
		String year=date.split("-")[2];
		click(DEPLOY_DATEINPOPUP,"Calnder");
		String calenderYear=getText(SELECTEDYEAR, "Selected year");
		System.out.println("calenderYear"+calenderYear);
		String calenderMonth=getText(SELECTED_MONTH, "Selected month");
		System.out.println("SELECTED_MONTH"+SELECTED_MONTH);
		if(year.equals(calenderYear) && month.equals(calenderMonth)){
			click(Day(day),"Selecting date");
			
		}
		else{
			click(YEAR_DROPDOWN,"year dropdown");
			click(Year(year),"Selecy year");
			click(MONTH_DROPDOWN,"Month dropdown");
			click(Month(month),"Select month");
			click(Day(day),"Selecting date");
		}
		}
	
		/**
		 * 
		 * @param This method is to Click on Unpaid license
		 * @throws Throwable
		 */
		public void Clickonunpaidlicense() throws Throwable {
			clickUntil(UNPAIDLISENCE,DEPLOY_DATEINPOPUP,"Unpaid License");
		}
		
	
		/**
		 * This method is to validate whether download export_cart file exists and sheet send_cart exists.
		 * @throws Throwable 
		 */
		public  void validateCartExportAndSheetName() throws Throwable {
		  
		  File src=new File("C:\\Users\\E002542\\Downloads\\exportCart.xls");  //CHANGE PATH ACCORDINGLY
		 Thread.sleep(2000);
		  if(src.exists()){
			  Thread.sleep(2000);
		   reporter.SuccessReport("Verify the file existance ", "File Found","");
		  }
		  else{
		   reporter.failureReport("Verify the file existance ", "File not Found","");
		  }
		  
		  Workbook wb=WorkbookFactory.create(src);
		  String sheetName=wb.getSheetName(0);
		  if(sheetName.equals("send_cart")){
			  reporter.SuccessReport("Verify send_cart in Export Cart Excel File", "send_cart Exists", "");
			  
		  }else{
			  reporter.failureReport("Verify send_cart in Export Cart Excel File", "send_cart does not Exists", "");
		  }
		  wb.close();
		  if(src.delete()) { 
			  reporter.SuccessReport("Verify file deleted", "File deleted successfully", "");
	        } 
	        else{ 
	        	reporter.failureReport("Verify file deleted","Failed to delete the file","");
	        } 
		}
		/**
		 * Method is to verify the invisibility of proceed to checkout button
		 * @throws Throwable
		 */
		public void verifyProccedToCheckOutbuttonExists() throws Throwable{
			commonLib.spinnerImage();
			if(isElementPresent(OrderLib.PROCEED_TO_CHECKOUT, "Proceed to checkout") && isEnabled(OrderLib.PROCEED_TO_CHECKOUT, "Proceed to checkout")){
				reporter.failureReport("Verify proceed to checkout button", "Proceed to checkout button exists","");
			}else{
				reporter.SuccessReport("Verify proceed to checkout button", "Proceed to checkout button does not exists", "");
			}
		}
		
		/**
		 * This method is to verify the alert message user requires approval in the cart page
		 * @throws Throwable
		 */
		public void verifyUserRequiresApprovelAlertMessage() throws Throwable{
			if(isElementPresent(USER_REQUIRES_APPROVAL_MSG, "user requires alert message")){
				reporter.SuccessReport("Verify Alert message displayed on CART Page", "Alert Exists", "");
			}else{
				reporter.failureReport("Verify Alert message displayed on CART Page", "Alert does not Exists", "");
			}
		}
		
		/**
		 * This method is to verify the citrix item in cart
		 * @throws Throwable
		 */
		public void verifyCitrixItemsInCart() throws Throwable{
			if(isElementPresent(CITRIX_ITEMS_IN_CART, "Citrix item")){
				reporter.SuccessReport("Verify CITRIX item in cart", "CITRIX Product is Existed and Verified", "");
			}else{
				reporter.failureReport("Verify CITRIX item in cart", "CITRIX Product does not exist", "");
			}
		}
		
		/**
		 * This method is to verify the Non Service Provider Items Removal Message
		 * @throws Throwable
		 */
		public void verifyNonServiceProviderItemsRemovalMessage() throws Throwable{
			if(isElementPresent(NON_CITRIX_ITEMS_REMOVE_MESSAGE, "NON CITRIX ITEMS REMOVE MESSAGE")){
				reporter.SuccessReport("Verify NON CITRIX Past Message on CART Page", "In order to report usage please remove items that do not apply to the selected service provider Exist", "");
			}else{
				reporter.failureReport("Verify NON CITRIX Past Message on CART Page", "In order to report usage please remove items that do not apply to the selected service provider Not Exists", "");
			}
		}
		
		/**
		 * Method is to delete item from cart
		 * @param partNum
		 * @throws Throwable
		 */
		public void deleteParticularItemInCart(String partNum) throws Throwable{
			click(getdeleteIconIncartBypartNumber(partNum), "Delete item in cart");
		}
		/**
		 * Method is used to click on Quote number
		 * 
		 * @throws Throwable
		 * 
		 */
		public void quoteNumberInTable(String quoteName, String referNumber) throws Throwable {
			Thread.sleep(5000);
			if (isElementPresent(getQuoteFormTable(quoteName), "Quote Details ")) {
				click(getReferenceNumber(referNumber), "Click on quote number in Quick History ");
				reporter.SuccessReport("Verify the Created Quote is saved in the Last Five Quotes",
						" Created Quote is saved in the Last Five Quotes", "");
			}
			else {
				reporter.failureReport("Verify the Created Quote is saved in the Last Five Quotes",
						" Created Quote is not  saved in the Last Five Quotes", "");
			}
		     }
		
		
		/**
		 * Method is used to click on Enter new card Button
		 * 
		 * @throws Throwable
		 * 
		 */
		public void clickonEnterNewcard()throws Throwable {
			click(ENTER_NEWCARDBUTTON,"Enter NewCard");
		}
		
		/**
		 * Method is to verify the reporting usage period on RECEIPT PAGE
		 * 
		 * @throws Throwable
		 */
		public String verifyReportingUsagePeriod() throws Throwable {
			String period = null;
			if (isElementPresent(REPORTING_USAGE_ON_RECEIPT_PAGE, "reporting usage period on Cart PAGE")) {
				 period = getText(REPORTING_USAGE_ON_RECEIPT_PAGE, "reporting usage period on Cart PAGE");
				reporter.SuccessReport("verify reporting usage period on RECEIPT PAGE",
						"Usage Field Exists and Verified. " + period, "");
			} else {
				reporter.failureReport("verify reporting usage period on RECEIPT PAGE", "Usage Field does not Exists. ", "");
			}
			return period;
		}
		
		/**
		 * Method is to click on the 
		 * @param softwareAgreement
		 * @throws Throwable
		 */
		public void retrieveLastUsageReport(String softwareAgreement) throws Throwable{
			if (isElementPresent(CanadaObj.SPLA_LABEL, "SPLA LABEL")) {
				if (!isCheckBoxSelected(CanadaObj.getMySoftwareLicenseAgreementscheckBoxes(softwareAgreement))) {
					click(CanadaObj.getMySoftwareLicenseAgreementscheckBoxes(softwareAgreement),"SPLA Details Product CheckBox");
					click(RETRIEVE_LAST_USAGE_REPORT, "Select retrieve last usage report button");
				} else {
					LOG.info("Checkbox already selected");
				}
			}
		}
		
		/**
		 * Method is to verify the amount is not equals to zero
		 * @param amount
		 * @throws Throwable
		 */
		public void verifyAmount(float amount) throws Throwable{
			if(amount!=0){
				reporter.SuccessReport("Verify amount exits or not ", "Amount exists and not equal to null", "");
			}else{
				reporter.failureReport("Verify amount exits or not ", "Amount does not exists", "");
			}
			
		}
		
		/**
		 * This method is to verify the reporting usage period warning message in cart page
		 * @throws Throwable
		 */
		public void verifyReportingPeriodWarning() throws Throwable{
			if(isElementPresent(REPORTING_PERIOD_WARNING_MSG, "verify repoting warning message")){
				reporter.SuccessReport("Verify reporting period warning message", "Reporting period warning message", "");
			}else{
				reporter.failureReport("Verify reporting period warning message", "Reporting period warning message does not exist", "");
			}
		}
		
		/**
 * This method is to verify the  All Reporting Periods Current label in Software license agreement page
		 * @throws Throwable
		 */
		public void verifyAllReportingPeriodsCurrent() throws Throwable{
			if(isElementPresent(ALL_REPORTING_PERIODS_CURRENT_LABEL_SLPA_PAGE, "verify All Reporting Periods Current")){
				reporter.SuccessReport("Verify All Reporting Periods Current message", "All Reporting Periods Current message", "");
			}else{
				reporter.failureReport("Verify All Reporting Periods Current message", "All Reporting Periods Current message does not exist", "");

			}
		}
		 /**
		 * @param This method is to verify copy to all Link Not Exists in cart page
		 * @throws Throwable
		 */
		public void verifyandClickcopytoallifexists(String PartNum) throws Throwable {
			if (isElementPresent(copytoallLink(PartNum), "Copy to All")) {
				click(copytoallLink(PartNum), "Copy to All");
				reporter.SuccessReport("Select Copy to all items in cart on Line Level Page", "COPY TO ALL link Exists and Selected", "");
			} else {
				reporter.failureReport("Select Copy to all items in cart on Line Level Page", "COPY TO ALL link does not Exist", "");
			}
		
		}
		
		/**
		 * 
		 * @param This method is to verify PartNum in PopUp Data 
		 * @throws Throwable
		 */
		public void enterPAvalue(String PA,int i) throws Throwable {
			waitForVisibilityOfElement(PA_FIELD, "PA field in line level Information");
					type(PA_fieldincartpage(i),PA,"Enter PA# field in LLI");
				}
		
		public void verifydeployeDateinCartPage()throws Throwable {
			if (isElementPresent(DEPLOYEDDATE_CARTPAGE, "Deployed Date")) {
				String deploydate = getText(DEPLOYEDDATE_CARTPAGE, "Date Displayed in cart Page");
				String actualDate = getCurrentDateTime("dd-MMM-yyyy");
				if (actualDate.contains(deploydate)) {
					reporter.SuccessReport("Deploy Date:: ", "verification of deploy date with current date is successfull","");
				} else {
					reporter.failureReport("Deploy Date:: ", "verification of deploy date with current date is Not successfull : "+deploydate+" .Expected Date :",actualDate);
				}
			}
		}
		
		public void verifyandClickchangeLink(String partNum) throws Throwable {
			if (isElementPresent(change_field(partNum), "Change")) {
				clickUntil(change_field(partNum),DEPLOY_DATEINPOPUP, "Change");
				reporter.SuccessReport("Change::", "Change Link Cart Exists As Expected and Selected", "");
			} else {
				reporter.failureReport("Change::", "Change Link Cart Not Exists", "");
			}
		}
		/*
		 * This method is to verify the  All Reporting Periods Current label in cart page
		 * @throws Throwable
		 */
		public void verifyAllReportingPeriodsCurrentinCartPage() throws Throwable{
			if(isElementPresent(ALL_REPORTING_PERIODS_CURRENT_LABEL_CART_PAGE, "verify All Reporting Periods Current")){
				reporter.SuccessReport("Verify All Reporting Periods Current message", "All Reporting Periods Current message", "");
			}else{
				reporter.failureReport("Verify All Reporting Periods Current message", "All Reporting Periods Current message does not exist", "");

			}
		}
		public String getTextfromdeployedateinCartPage() throws Throwable {
			String deploydate = null;
			if (isElementPresent(DEPLOYEDDATE_CARTPAGE, "Deploy Date")) {
				deploydate = getText(DEPLOYEDDATE_CARTPAGE, "Date ordered");
				if (deploydate.isEmpty()) {
					reporter.failureReport("Verify the Deploy Date in cart page ", "The Deploy Date is null or empty. ","");

				} else
					reporter.SuccessReport("Verify the Deploy Date in cart page", "The Deploy Date: " , deploydate);
			}
				return deploydate;
			}
		
		public String getTextfromdeployedateinPlaceOrderPage() throws Throwable {
			String deploydate = null;
			if (isElementPresent(DEPLOYEDDATE_CARTPAGE, "Deploy Date")) {
				deploydate = getText(DEPLOYEDDATE_CARTPAGE, "Date ordered");
				if (deploydate.isEmpty()) {
					reporter.failureReport("Verify the Deploy Date in PlaceOrder page ", "The Deploy Date is null or empty. ","");

				} else
					reporter.SuccessReport("Verify the Deploy Date in PlaceOrder page", "The Deploy Date: " , deploydate);
			}
				return deploydate;
			}
		
		/**
		 * 
		 * @param This method is to verify PA value in Place order Page 
		 * @throws Throwable
		 */
		public void verifyPAvalueinPlaceOrderPage() throws Throwable {
			int i=1;
			List<WebElement> PAList =  driver.findElements(PA_fieldinplaceorderpage(i));
			for(i=1; i<=PAList.size(); i++){
			if(isElementPresent(PA_fieldinplaceorderpage(i), "PA field in line level Information in Place order Page")){
				String PA_Value = getText(PA_fieldinplaceorderpage(i), "PA field in line level Information in Place order Page").replace("PA #:", " ").trim();
				reporter.SuccessReport("Verify PA# Field in Place Order Page ", "PA#"+i+" Field  Exists and Verified",PA_Value);

			} else
				reporter.failureReport("Verify PA# Field in Place Order Page", "PA# Field does not exist" , "");
				}
		}
}

