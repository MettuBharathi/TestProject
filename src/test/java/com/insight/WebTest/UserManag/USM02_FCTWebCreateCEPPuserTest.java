package com.insight.WebTest.UserManag;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.Lib.UserManagementLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class USM02_FCTWebCreateCEPPuserTest extends UserManagementLib {

	// #############################################################################################################
			// # Name of the Test :USM01_FCTWEBClientUserSearch
			// # Migration Author : Cigniti Technologies
			// #
			// # Date of Migration : OCT 2019
			// # DESCRIPTION : This method is to perform Quote History search with date
			// operations.
			// # Parameters : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void TC_USM02FCTWebCreateCEPPuser(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
				int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_USM02FCTWebCreateCEPPuser", TestDataInsight,
							"UserManagement");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_USM02FCTWebCreateCEPPuser",TestDataInsight, "UserManagement", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("USM02FCTWebCreateCEPPuser");
							reporter.SuccessReport("Iteration Number : ",
									"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
											+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
									"");
	 
							CMTLib cmtLib = new CMTLib();
							SearchLib searchLib = new SearchLib();
							OrderLib orderLib=new OrderLib();
							CanadaLib canadaLib=new CanadaLib();
							CartLib cartLib=new CartLib();
							MarriottIntlCorpLib mic=new MarriottIntlCorpLib();
							InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
							MarriottIntlCorpLib marriottIntlCorpLib=new MarriottIntlCorpLib();
							CommonLib commonLib = new CommonLib();
							ShipBillPayLib sbp=new ShipBillPayLib();
							cmtLib.clickLoginLink(data.get("Header"));
							cmtLib.handleWelcomeToInsightBetaPopUp();
							cmtLib.loginAsAdmin();
							cmtLib.searchForWebGroup(data.get("WebGrp"));
							cmtLib.manageUsers();
							cmtLib.clickAddNewUserLink();
							cmtLib.selectUserTypeDropdown(data.get("User_Type"));
							cmtLib.verifyAvailabiltyOfUserName(data.get("UserName"));
							cmtLib.checkAvailability();
							cmtLib.verifyAvailabiltyOfUserName(data.get("UserName1"));
							cmtLib.checkAvailability();
							String userName=getRandomNumeric(4);
							cmtLib.enterUserName("QTPTest"+userName);
							String userName1=getRandomNumeric(4);
							cmtLib.verifyAvailabiltyOfUserName("QTPTest"+userName1);
							cmtLib.clickCreateUserButton();
							cmtLib.setPermissions(data.get("Menu_Name"),data.get("Set_Permission"));
							cmtLib.setPermissions(data.get("Menu_Name"),data.get("Set_Permission1"));
							cmtLib.clickInformationTab(data.get("Information_Tab"));
							cmtLib.clickOnUserURL();
							//create an account
							cmtLib.verifyCreateAnAccountPage();
							String email="QTPTest"+getRandomNumeric(4)+"@test.com";
							cmtLib.enterEmailInCreateAnAccount(email);
							String firstName="QTPTest"+getRandomNumeric(4);
							cmtLib.enterFirstNameInCreateAnAccount(firstName);
							String lastName="QTPTest"+getRandomNumeric(4);
							cmtLib.enterLastNameInCreateAnAccount(lastName);
							cmtLib.enterPhoneNumberInCreateAnAccount(data.get("Phone_Number"));
							//Billing addresses
							String billingAccountName="QTPTest"+getRandomNumeric(4);
							cmtLib.enterBillingAccountNameInCreateAnAccount(billingAccountName);
							cmtLib.enterAdressesInCreateAnAccount(data.get("Adressess1"));
							cmtLib.enterCityInCreateAnAccount(data.get("City"));
							cmtLib.selectStateInCreateAnAccount(data.get("State"));
							cmtLib.enterZipCodeInCreateAnAccount(data.get("ZipCode"));
							String userNameCreateAccount="QTPTest"+getRandomNumeric(4);
							String userName1CreateAccount="QTPTest"+getRandomNumeric(4);
							String userNameEntered=cmtLib.verifyAvailabilityCreateAccount(userNameCreateAccount,userName1CreateAccount);
							String password="QTPTest"+getRandomNumeric(4);
							cmtLib.enterPasswordInCreateAnAccount(password);
							cmtLib.enterConfirmPasswordInCreateAnAccount(password);
							cmtLib.clickCreateButtonInCreateAnAccount();
							cmtLib.verifyWelcomePage();
							//  Select First Product and Add to cart
							searchLib.searchInHomePage(data.get("SearchItem"));
							commonLib.addToCartAndVerify();
							orderLib.continueToCheckOutOnAddCart();
							cartLib.verifyItemInCart(data.get("SearchItem"));
							orderLib.proceedToCheckout();
							cartLib.clickOnContinueButtonInAddInformtion();
							
							orderLib.clickContinueOnLineLevelInfo();
							canadaLib.verifySBP();
							orderLib.shippingBillPayContinueButton();
							//cartLib.selectCarrier(data.get("Carrier_Option"));
							cartLib.selectShippingMeethod(data.get("Shipping_Methods"));
							
							orderLib.shippingBillPayContinueButton();
							orderLib.shippingBillPayContinueButton();
							orderLib.selectPaymentInfoMethodCreditCard(data.get("cardNumber"),data.get("cardName"),data.get("month"),data.get("year"),data.get("PO_Number"));
							orderLib.clickOnReviewOrderButton();
							
							String summaryAmount = cartLib.getSummaryAmountInCart();
							orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
							sbp.clickOrderDetailsButtonInREceipt();
							sbp.verifyShippingCarrierAFterReviewOrder(data.get("Shiping_Carrier_Verify_Receipt"));
							commonLib.clickLogOutLink(data.get("Logout_Header"));
						} catch (Exception e) {
							ReportStatus.blnStatus = false;
							gErrorMessage = e.getMessage();
							gTestStatus = false;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					ReportStatus.blnStatus = false;
					gErrorMessage = e.toString();
					gTestStatus = false;
					//ReportStatus.fnUpdateResultStatus("USM01FCTWEBClientUserSearch", "TC_USM01FCTWEBClientUserSearch", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
	            finally {
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("USM02FCTWebCreateCEPPuser", "TC_USM02FCTWebCreateCEPPuser", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}
}

