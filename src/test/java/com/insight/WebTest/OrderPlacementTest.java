package com.insight.WebTest;


import com.insight.Lib.OrderLib;
import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class OrderPlacementTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodLib = new ProductDisplayInfoLib();
	OrderLib orderLib =new OrderLib();

	
	
	    // #############################################################################################################
	 	// #    Name of the Test         : ODP01_PlaceOrderBasicFileUpload
	 	// #    Migration Author         : Cigniti Technologies
	    // #
	 	// #    Date of Migration        : September 2019
	 	// #    Description              : To Test Place Order basic
	 	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	 	// #
	 	// ############################################################################################################# 
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void ODP01_PlaceOrderBasicFileUpload(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP01_PlaceOrderBasicFileUpload", TestData,
				"Web_Order_Placement");

		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP01_PlaceOrderBasicFileUpload", TestData,"Web_Order_Placement", intCounter);

			// Test Steps execution
			fnOpenTest();

			// Login to CMT
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.setCustomerLevelPermissionsON(data.get("Customer_Permissions_ON"));
     		cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
			cmtLib.loginAsAdminCMT();

			// Login As to UAT / WEB
			searchLib.searchInHomePage(data.get("SearchText"));
			commonLib.verifyDisplayedProductDetails(data.get("SearchText"));

			// Cart verification
			commonLib.addToCartAndVerify();
			continueToCheckOutOnAddCart();
			cartLib.verifyItemAddedInCartByMfrNumber(data.get("SearchText"));

			// Proceed to Checkout
			proceedToCheckout();
			verifyFileUploadOption(data.get("File_Path")); // Need to add verification for File upload
			
		
			clickContinueOnLineLevelInfo();
			shippingBillPayContinueButton();
			shippingOptionsCarrierSelection();
			shippingBillPayContinueButton();
			selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),
					data.get("Month"), data.get("Year"),data.get("PO_Number"));
			clickOnReviewOrderButton();

			verifyUploadedFileInReviewOrderPage(data.get("File_Name")); // Need to add verification
			verifyReceiptVerbiage();
			String summaryAmount=cartLib.getSummaryAmountInCart();
			placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);

			verifyShippingAddressOnReceiptPage(data.get("Section_Name1")); // verifying shipping address in receipt page.
			verifyBillingAddressOnReceiptPage(data.get("Section_Name2"));  // Verifying billing address in receipt page.

			// verifying cart in Receipt page
			verifyYourCartOnReceiptPage(data.get("SearchText"));
		}
	}
	
	        // #############################################################################################################
		 	// #    Name of the Test         : ODP02_PlaceOrderBasicIPS
		 	// #    Migration Author         : Cigniti Technologies
		    // #
		 	// #    Date of Migration        : September 2019
		 	// #    Description              : To Test Place Order basic
		 	// #    Parameters               : StartRow ,EndRow , nextTestJoin
		 	// #
		 	// ############################################################################################################# 
		
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void ODP02_PlaceOrderBasicIPS(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP02_PlaceOrderBasicIPS", TestData,"Web_Order_Placement");

			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				// initilizing libraries and testdata
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP02_PlaceOrderBasicIPS", TestData,"Web_Order_Placement", intCounter);

				// Test Steps execution
				fnOpenTest();
				
				// Login to CMT and disable Allow File Upload during Checkout,Override Payment Options
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				String[] permissions=data.get("Customer_Permissions_OFF").split(","); 
		 		for(i=0;i<permissions.length;i++){
				cmtLib.setCustomerLevelPermissionsOFF(permissions[i]);
		 		   }
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				
				// navigate to checkout settings >>  check User Service Level Shipping and select SLS ground
				cmtLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
				cmtLib.selectDefaultShippingOptionInCheckoutSettings(data.get("Default_Shipping_Option"));
				cmtLib.clickupdateatDefaultShippingOption();
				cmtLib.loginAsAdminCMT();
				
				searchLib.searchInHomePage(data.get("SearchText1"));
				commonLib.verifyDisplayedProductDetails(data.get("SearchText1"));

				// Cart verification
				commonLib.contractOnProductDetailPage();
				commonLib.addToCartAndVerify();
				continueToCheckOutOnAddCart();
				
				// Select New contract
	            searchLib.selectNewcontract(data.get("Contract_Name"));
	            searchLib.searchInHomePage(data.get("SearchText2"));
				commonLib.verifyDisplayedProductDetails(data.get("SearchText2"));
				
				// Verify contract selected
				commonLib.addToCartAndVerify();
				continueToCheckOutOnAddCart();
				commonLib.verifyContractInCart(data.get("Contract_Name"));
				proceedToCheckout();
				enterReportingDetailsInLineLevelInfoSection(data.get("REPORTING FIELD_4"), data.get("REPORTING FIELD_5"), data.get("REPORTING FIELD_6"));
				
				shippingBillPayContinueButton();  // continue button on Shipping address
				shippingOptionsCarrierSelection();  // carrier selection or continue in shipping options
				shippingBillPayContinueButton();  // Continue on billing address section
				
				selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PO_Number"));
				clickOnReviewOrderButton();  // Click review order button
				
				//Place Order
				String summaryAmountInLogin=cartLib.getSummaryAmountInCart();
				placeOrderAndVerifyReceiptOrderAndDate(summaryAmountInLogin);
				
				//Verify Receipt
				verifyReceiptVerbiage();
				clickOrderDetailsLinkOnReceiptPage();
				
				verifyShippingAddressOnReceiptPage(data.get("Section_Name1")); // Verify shipping address
				verifyBillingAddressOnReceiptPage(data.get("Section_Name2"));  // verify billing address
				
				// Verify contract on Receipt page
				scrollToBottom();
				commonLib.verifyContractInCart(data.get("Contract_Name"));
				fnCloseTest();
}
		}


		// #############################################################################################################
	 	// #    Name of the Test         : ODP03_PlaceOrderConfirmations
	 	// #    Migration Author         : Cigniti Technologies
	    // #
	 	// #    Date of Migration        : September 2019
	 	// #    Description              : To Test Place Order  Confirmations
	 	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	 	// #
	 	// ############################################################################################################# 
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void ODP03_PlaceOrderConfirmations(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP03_PlaceOrderConfirmations", TestData,"Web_Order_Placement");

		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP03_PlaceOrderConfirmations", TestData,"Web_Order_Placement", intCounter);

			// Test Steps execution
			fnOpenTest();
			
			// Login to CMT and disable Allow File Upload during Checkout,Override Payment Options
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			
			//Uncheck Override Payments Options in web Group Level	 	
			cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
			
			// select Contacts and Notifications
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
			
			// Remove existing sales rep and add new one
			cmtLib.RemoveExistedsalesreps(data.get("Rep_Name"));
			cmtLib.addNewSalesRep(data.get("Rep_Email"));
			cmtLib.enableOrDisableOrdersOfSalesRepOnContactsAndNotifications(data.get("Rep_Name"), "OFF");  // sales rep "Orders >> OFF"
			cmtLib.enableOrDisableQuotesOfSalesRepOnContactsAndNotifications(data.get("Rep_Name"), "OFF");  // sales rep "Quotes >> OFF"
			
			// create client notifications
			cmtLib.createClientNotifications(data.get("Rep_Email"));
			
			cmtLib.enableOrDisableQuotesOfClientNotificationRep(data.get("Rep_Email"), "OFF");    // Client Notification Rep "Quotes >> OFF"
			cmtLib.enableOrDisableOrdersOfOfClientNotificationRep(data.get("Rep_Email"), "OFF");  // Client Notification Rep "Orders >> OFF"
			
			// Login as user selected
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options2"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
			cmtLib.loginAsAdminCMT();
			
			//  Select First Product and Add to cart
			searchLib.searchInHomePage(data.get("SearchText"));
			commonLib.addToCartAndVerify();
	        continueToCheckOutOnAddCart();
	        cartLib.verifyItemInCart(data.get("SearchText"));
			
            proceedToCheckout(); // proceed to checkout
            continueButtonOnAdditionalInformationSection();  // Click continue on Additional information Section
            clickContinueOnLineLevelInfo(); // Click continue on Line Level information Section
			shippingBillPayContinueButton(); // Click continue on shipping address Section
			shippingBillPayContinueButton(); // Click continue on Shipping options Section
			shippingBillPayContinueButton(); //Click continue on Billing address Section
			selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));
			clickOnReviewOrderButton();
             
			//Place Order
			String summaryAmount=cartLib.getSummaryAmountInCart();
			placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
			
			//Verify Receipt
			verifyReceiptVerbiage();
			clickOrderDetailsLinkOnReceiptPage();
			
			verifyShippingAddressOnReceiptPage(data.get("Section_Name1")); // Verifying shipping address
			verifyBillingAddressOnReceiptPage(data.get("Section_Name2"));  // verifying billing address
			verifyPaymentInformationOnReceiptPage(data.get("Section_Name3"),data.get("Month"),data.get("Year"),data.get("Card_Name"),data.get("Ending_Card_Numbers"),data.get("Card_Type"));  // verifying payment info
			
			// Verifying the part in cart
			cartLib.verifyItemInCart(data.get("SearchText"));
			commonLib.clickLogOutLink(data.get("Logout"));
            fnCloseTest();
}
	}

	        // #############################################################################################################
		 	// #    Name of the Test         : ODP04_PlaceOrderCreditCard
		 	// #    Migration Author         : Cigniti Technologies
		    // #
		 	// #    Date of Migration        : September 2019
		 	// #    Description              : To Test Place Order  with Credit Card Types
		 	// #    Parameters               : StartRow ,EndRow , nextTestJoin
		 	// #
		 	// ############################################################################################################# 
		
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void ODP04_PlaceOrderCreditCard(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP04_PlaceOrderCreditCard", TestData,"Web_Order_Placement");

			Hashtable<String, String> data1 = TestUtil.getDataByRowNo("ODP04_PlaceOrderCreditCard", TestData,"Web_Order_Placement", intStartRow);
			// Test Steps execution
			fnOpenTest();
			
			// Login to CMT and disable Allow File Upload during Checkout,Override Payment Options
			cmtLib.loginToCMT(data1.get("Header"));
			cmtLib.searchForWebGroup(data1.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data1.get("WebGrp_Name"));
			
			//  Uncheck Override Payments Options in web Group Level	 	
			cmtLib.setCustomerLevelPermissionsOFF(data1.get("Customer_Permissions_OFF"));
			
			// select Users and Login As
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data1.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data1.get("LnameEmailUname"), data1.get("ContactName"));
			cmtLib.loginAsAdminCMT();
			
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				// initilizing libraries and testdata
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP04_PlaceOrderCreditCard", TestData,"Web_Order_Placement", intCounter);
	
			   // Select First Product and Add to cart
				searchLib.searchInHomePage(data.get("SearchText"));
				commonLib.addToCartAndVerify();
		        continueToCheckOutOnAddCart();
		        cartLib.verifyItemInCart(data.get("SearchText"));
				proceedToCheckout();
				
				continueButtonOnAdditionalInformationSection();  // Click continue on Additional information Section
	            clickContinueOnLineLevelInfo(); // Click continue on Line Level information Section
				shippingBillPayContinueButton(); // Click continue on shipping address Section
				shippingBillPayContinueButton(); // Click continue on Shipping options Section
				shippingBillPayContinueButton(); //Click continue on Billing address Section
				selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));  // VISA card
				
				clickOnReviewOrderButton();
				
				//Place Order
				String summaryAmount=cartLib.getSummaryAmountInCart();
				placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
				
				//Verify Receipt
				verifyReceiptVerbiage();
				clickOrderDetailsLinkOnReceiptPage();
				
				 // verifying payment info
				verifyPaymentInformationOnReceiptPage(data.get("Section_Name3"),data.get("Month"),data.get("Year"),data.get("Card_Name"),data.get("Ending_Card_Numbers"),data.get("Card_Type")); 
				
				// Verifying the part in cart
				cartLib.verifyItemInCart(data.get("SearchText"));
				
}
			fnCloseTest();
		}
		

		// #############################################################################################################
	 	// #    Name of the Test         : ODP05_PlaceOrderPrinterFirendly
	 	// #    Migration Author         : Cigniti Technologies
	    // #
	 	// #    Date of Migration        : September 2019
	 	// #    Description              : To Test Place Order  Confirmations
	 	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	 	// #
	 	// ############################################################################################################# 
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void ODP05_PlaceOrderPrinterFirendly(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP05_PlaceOrderPrinterFirendly", TestData,"Web_Order_Placement");

		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP05_PlaceOrderPrinterFirendly", TestData,"Web_Order_Placement", intCounter);

			// Test Steps execution
			fnOpenTest();
			
			// Login to CMT and disable Allow File Upload during Checkout,Override Payment Options
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));

			// Uncheck Override Payments Options in web Group Level
			cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));

			// select Users and Login As
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
			cmtLib.loginAsAdminCMT();
			
			// Select First Product and Add to cart
			searchLib.searchInHomePage(data.get("SearchText"));
			commonLib.addFirstDisplyedItemToCartAndVerify();
	        continueToCheckOutOnAddCart();

	     // Select warranty Product to cart
	        searchLib.searchInHomePage(data.get("WarrPartNumber"));
	        commonLib.addToCartAndVerify();
	        continueToCheckOutOnAddCart();
	        //verify part item added in cart
	        cartLib.verifyItemInCart(data.get("WarrPartNumber"));
	        		
	      // verify print popup 
	        List<String> prodDesc=getProductDescriptionOfCartProduct();
	        List<String> quantity=getCartProductQuantity();
	        List<String> stock=getCartProductStock();
	        List<String> totalPrice=getCartProductTotalPrice();
	        List<String> unitPrice=getCartProductUnitPrice();
	        
	        clickPrintIconOnCartPage(data.get("Order_Utilities"));
	        VerifyPrintPopup(prodDesc,quantity,stock,totalPrice,unitPrice);
	        
	        	 proceedToCheckout();
	        	 continueButtonOnAdditionalInformationSection();  // Click continue on Additional information Section
		            clickContinueOnLineLevelInfo(); // Click continue on Line Level information Section
					shippingBillPayContinueButton(); // Click continue on shipping address Section
					shippingBillPayContinueButton(); // Click continue on Shipping options Section
					shippingBillPayContinueButton(); //Click continue on Billing address Section
					
					selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));  // American Express card
					
					clickOnReviewOrderButton();
					//Place Order
					String summaryAmount=cartLib.getSummaryAmountInCart();
					placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
					//Verify Receipt
					verifyReceiptVerbiage();
					//selecting bundle from company standards page
					commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("toolsMenuName"),data.get("dropDown") ,data.get("productGroup"),data.get("productName"));
					searchLib.clickAddToOrderOnCompanyStandardsScreen();
					orderLib.verifyCartHeaderLabel();  
					 //verify print popup
					
					// verify print popup 
			        List<String> prodDesc1=getProductDescriptionOfCartProduct();
			        List<String> quantity1=getCartProductQuantity();
			        List<String> stock1=getCartProductStock();
			        List<String> totalPrice1=getCartProductTotalPrice();
			        List<String> unitPrice1=getCartProductUnitPrice();
			        
			        clickPrintIconOnCartPage(data.get("Order_Utilities"));
			        VerifyPrintPopup(prodDesc1,quantity1,stock1,totalPrice1,unitPrice1);
					
					proceedToCheckout();
				    continueButtonOnAdditionalInformationSection();  // Click continue on Additional information Section
		            clickContinueOnLineLevelInfo(); // Click continue on Line Level information Section
					shippingBillPayContinueButton(); // Click continue on shipping address Section
					shippingBillPayContinueButton(); // Click continue on Shipping options Section
					shippingBillPayContinueButton(); //Click continue on Billing address Section
					
					selectPaymentInfoMethodCreditCard(data.get("Card_Number1").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));  // American Express card
					
					clickOnReviewOrderButton();
					//Place Order
					String summaryAmount1=cartLib.getSummaryAmountInCart();
					placeOrderAndVerifyReceiptOrderAndDate(summaryAmount1);
					//Verify Receipt
					verifyReceiptVerbiage();
					//Logout 					
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
					//Login with 2nd User
					cmtLib.searchForWebGroup(data.get("WebGrp1"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name1"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
					cmtLib.loginAsAdminCMT();
					
			     // Select warranty Product to cart
			        searchLib.searchInHomePage(data.get("SearchText2"));
			        commonLib.addFirstDisplyedItemToCartAndVerify();
			        continueToCheckOutOnAddCart();
			        //Verify print popup
			     // verify print popup 
			        List<String> prodDesc2=getProductDescriptionOfCartProduct();
			        List<String> quantity2=getCartProductQuantity();
			        List<String> stock2=getCartProductStock();
			        List<String> totalPrice2=getCartProductTotalPrice();
			        List<String> unitPrice2=getCartProductUnitPrice();
			        
			        clickPrintIconOnCartPage(data.get("Order_Utilities"));
			        VerifyPrintPopup(prodDesc2,quantity2,stock2,totalPrice2,unitPrice2);
			        
			        proceedToCheckout();
		        	 continueButtonOnAdditionalInformationSection();  // Click continue on Additional information Section
			            clickContinueOnLineLevelInfo(); // Click continue on Line Level information Section
						shippingBillPayContinueButton(); // Click continue on shipping address Section
						shippingBillPayContinueButton(); // Click continue on Shipping options Section
						shippingBillPayContinueButton(); //Click continue on Billing address Section
						
						enterCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"), data.get("poNumebr"));  // American Express card
						
						clickOnReviewOrderButton();
						//Place Order
						String summaryAmount2=cartLib.getSummaryAmountInCart();
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmount2);
						//Verify Receipt
						verifyReceiptVerbiage();
						//Logout 
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						//Login with 3rd User						
						cmtLib.searchForWebGroup(data.get("WebGrp2"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name2"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2"));
						cmtLib.loginAsAdminCMT();
						
				     // Select warranty Product to cart
				        searchLib.searchInHomePage(data.get("SearchText3"));
				        commonLib.addFirstDisplyedItemToCartAndVerify();
				        continueToCheckOutOnAddCart();
				        //Verify print popup
				       
				        List<String> prodDesc3=getProductDescriptionOfCartProduct();
				        List<String> quantity3=getCartProductQuantity();
				        List<String> stock3=getCartProductStock();
				        List<String> totalPrice3=getCartProductTotalPrice();
				        List<String> unitPrice3=getCartProductUnitPrice();
				        
				        clickPrintIconOnCartPage(data.get("Order_Utilities"));
				        VerifyPrintPopup(prodDesc3,quantity3,stock3,totalPrice3,unitPrice3);
				        
				        proceedToCheckout();
				        addAdditionalInformation(data.get("Url"), data.get("RP_HDL_Txt"), data.get("WG_HDL_Txt"), data.get("Additional_Notes"), data.get("Invoice_Notes"));
				        continueButtonOnAdditionalInformationSection();  // Click continue on Additional information Section
					       addLineLevelInfoSmartTracker(data.get("rP_LNL_Txt"));	
					       clearPhnumberInShippinAddress();
							shippingBillPayContinueButton(); // Click continue on shipping address Section
							shippingBillPayContinueButton(); // Click continue on Shipping options Section
							shippingBillPayContinueButton(); //Click continue on Billing address Section
							
							//Adding Visa card
							enterCreditCard(data.get("Card_Number2").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"), data.get("poNumebr")); 
							clickOnReviewOrderButton();
							verifyPlaceOrderLabel();
							//Verify print popup Window
							  List<String> prodDesc4=getProductDescriptionOfCartProduct();
						        List<String> quantity4=getCartProductQuantity();
						        List<String> stock4=getCartProductStock();
						        List<String> totalPrice4=getCartProductTotalPrice();
						        List<String> unitPrice4=getCartProductUnitPrice();
						        
						        clickPrintIconOnCartPage(data.get("Order_Utilities"));
						        VerifyPrintPopup(prodDesc4,quantity4,stock4,totalPrice4,unitPrice4);
							
							//Place Order
							String summaryAmount3=cartLib.getSummaryAmountInCart();
							placeOrderAndVerifyReceiptOrderAndDate(summaryAmount3);
							//Verify Receipt
							verifyReceiptVerbiage();
							commonLib.clickLogOutLink(data.get("Logout"));
							fnCloseTest();
						
	        	 
	        }

		}

			// #############################################################################################################
		 	// #    Name of the Test         : ODP07_ConvertQuote
		 	// #    Migration Author         : Cigniti Technologies
		    // #
		 	// #    Date of Migration        : September 2019
		 	// #    Description              : To Test Convert Quote
		 	// #    Parameters               : StartRow ,EndRow , nextTestJoin
		 	// #
		 	// ############################################################################################################# 
		
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void ODP07_ConverQuote(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP07_ConverQuote", TestData,"Web_Order_Placement");

			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				// initilizing libraries and testdata
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP07_ConverQuote", TestData,"Web_Order_Placement", intCounter);

				// Test Steps execution
				fnOpenTest();
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
				cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
				
				// Login As to Web UAT
				cmtLib.loginAsAdminCMT();
				
			   // Select First Product and Add to cart
			   searchLib.searchInHomePage(data.get("SearchText"));
			   commonLib.addToCartAndVerify();
			   continueToCheckOutOnAddCart();
			   
			   // Create Quote
			   createQuote(data.get("Quote_Name"));
			   verifyTaxInSaveAsQuotePage();   // Verify Tax in save as quote page 
			   String taxAmount=verifyTaxInSaveAsQuotePage();
			   String refNumber=getQuoteReferenceNumber();
			   
			   commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
			   searchByInQuoteHistory(refNumber,data.get("DD_Option"));
			   convertQuote();
			   cartLib.verifyCartBreadCrumb();
			   verifyTheQuantityIsdisabled();
			   
			   commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
			   searchByInQuoteHistory(refNumber,data.get("DD_Option"));
			   editQuote();
			   cartLib.verifyCartBreadCrumb();
			   commonLib.updateCartQuantity(data.get("Quantity"));
			   
			   //Proceed to checkout
			   proceedToCheckout();
			   
			    continueButtonOnAdditionalInformationSection();  // Click continue on Additional information Section
	            shippingBillPayContinueButton(); // Click continue on shipping address Section
	            shippingOptionsCarrierSelection();  // carrier selection or continue in shipping options
				shippingBillPayContinueButton(); //Click continue on Billing address Section
				selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));
			   
				// Review Order 
				clickOnReviewOrderButton();
				
				/// Verify Updated Qty on Place Order Page
				verifyTheQuantityOfCartProductOnReceiptPage(data.get("Quantity"));
				
				//Place Order
				String summaryAmount=cartLib.getSummaryAmountInCart();
				placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
				
				//Verify Receipt
				verifyReceiptVerbiage();
				clickOrderDetailsLinkOnReceiptPage();
				
				// verify Ship Bill details
				verifyShippingAddressOnReceiptPage(data.get("Section_Name1")); // Verifying shipping address
				verifyBillingAddressOnReceiptPage(data.get("Section_Name2"));  // verifying billing address
				
				// Verify Updated Qty on Receipt Page
				verifyTheQuantityOfCartProductOnReceiptPage(data.get("Quantity"));
				verifyTheTaxForSearchTerm(taxAmount);
				commonLib.clickLogOutLink(data.get("Logout"));
				 fnCloseTest();
				
			}
		}
	

        // #############################################################################################################
	 	// #    Name of the Test         : ODP08_ConvertQuoteBundles
	 	// #    Migration Author         : Cigniti Technologies
	    // #
	 	// #    Date of Migration        : September 2019
	 	// #    Description              : To Test Convert Quote for Bundles
	 	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	 	// #
	 	// ############################################################################################################# 
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void ODP08_ConvertQuoteBundles(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP08_ConvertQuoteBundles", TestData,"Web_Order_Placement");

		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP08_ConvertQuoteBundles", TestData,"Web_Order_Placement", intCounter);

		 // Test Steps execution
			fnOpenTest();
			
		 // Login to CMT enable view_quotes;ON";
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
			cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
			
		 // Login As to Web UAT
			cmtLib.loginAsAdminCMT();
		
		// Login Verification
			cmtLib.loginVerification(data.get("ContactName"));
			
		 // Navigate to Account tools >> Company Standards >> Select bundle of products and add to order
            commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"), data.get("Product_Group"), data.get("Product_Name"));
            searchLib.clickAddToOrderOnCompanyStandardsScreen();
            cartLib.verifyProductGroupBundleAddedToCart(data.get("Product_Name"));
            
         // Create Quote
			createQuote(data.get("Quote_Name"));
			String refNumber=getQuoteReferenceNumber();
			verifyProductBundleTableLoadedInSaveQuoteScreen();
			
		 // Navigate to Quote History
			commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu1"), data.get("Tools_Menu_DD1"));
			searchByInQuoteHistory(refNumber,data.get("DD_Option"));
			convertQuote();
			cartLib.verifyCartBreadCrumb();
			
		 // Click on Proceed to checkout
			proceedToCheckout();
		 // Click continue on Additional information Section
			continueButtonOnAdditionalInformationSection(); 
			
		 // Click continue on LL info , shipping and billing sections
			clickContinueOnLLIAndShipBillPaySections();
		 
		 // Fill payment Info
			selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));
			   
		 // Review Order 
			clickOnReviewOrderButton();

		 // Place Order
			String summaryAmount=cartLib.getSummaryAmountInCart();
		 // Verify order and Date
			placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
			
	     // Verify Receipt
			verifyReceiptVerbiage();
			clickOrderDetailsLinkOnReceiptPage();
			
		  //Logout 
			commonLib.clickLogOutLink(data.get("Logout"));
			 fnCloseTest();
}
	}
	
	// #############################################################################################################
    // #    Name of the Test         : ODP09_ConvertQuoteIPS
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Convert Quote for Bundles
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
	// ############################################################################################################# 
		
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void ODP09_ConvertQuoteIPS(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP09_ConvertQuoteIPS", TestData,"Web_Order_Placement");

			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				// initilizing libraries and testdata
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP09_ConvertQuoteIPS", TestData,"Web_Order_Placement", intCounter);

			 // Test Steps execution
				fnOpenTest();
			
			// Login to CMT Enable > view_quotes;ON" and Disable quote_track_only_my_quotes ; OFF;
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
				cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
				
			// Login As to Web UAT
				cmtLib.loginAsAdminCMT();
				
			// Login Verification
				cmtLib.loginVerification(data.get("ContactName"));
				
		    // Select First Product and Add to cart
				searchLib.searchInHomePage(data.get("SearchText1"));
				commonLib.addToCartAndVerify();
				continueToCheckOutOnAddCart();
			// Verify Contract is present in the Cart Page	
				cartLib.verifyTheItemIsAddedUnderContractInCartPage();
				
			// select new contract
				searchLib.selectNewcontract(data.get("Contract_Name"));
				
		   // Add Item to the Cart
				searchLib.searchInHomePage(data.get("SearchText2"));
				commonLib.addToCartAndVerify();
				continueToCheckOutOnAddCart();
				
			// Verify selected contract in cart page
				cartLib.verifyContractNameInCart(data.get("Contract_Name"));
		  
		    // Create Quote
				createQuote(data.get("Quote_Name"));
				String refNumber=getQuoteReferenceNumber();
				
		    // Navigate to Quote History
				commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
				searchByInQuoteHistory(refNumber,data.get("DD_Option"));
				convertQuote();
				cartLib.verifyCartBreadCrumb();
				
			 // Click on Proceed to checkout
				proceedToCheckout();
			 
			// Enter reporting details
				enterReportingDetailsInLineLevelInfoSection(data.get("REPORTING FIELD_4"), data.get("REPORTING FIELD_5"), data.get("REPORTING FIELD_6"));
				
				shippingBillPayContinueButton();  // continue button on Shipping address
				shippingOptionsCarrierSelection();  // carrier selection or continue in shipping options
				shippingBillPayContinueButton();  // Continue on billing address section
				
			 // Fill payment Info
				selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));
			
			 // Review Order 
				clickOnReviewOrderButton();

			 // Place Order
				String summaryAmount=cartLib.getSummaryAmountInCart();
			 // Verify order and Date
				placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
				
		     // Verify Receipt
				verifyReceiptVerbiage();
				clickOrderDetailsLinkOnReceiptPage();
			
			 // Verify selected contract in cart of Receipt page
				cartLib.verifyContractNameInCart(data.get("Contract_Name"));
				
				 //Logout 
				commonLib.clickLogOutLink(data.get("Logout"));
				 fnCloseTest();
			}
		}
		
		// #############################################################################################################
	    // #    Name of the Test         : ODP06_CreditCardOverridePayment
		// #    Migration Author         : Cigniti Technologies
		// #
		// #    Date of Migration        : September 2019
		// #    Description              : To Test Credit Card Override Payment
		// #    Parameters               : StartRow ,EndRow , nextTestJoin
	    // #
		// ############################################################################################################# 
			
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void ODP06_CreditCardOverridePayment(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP06_CreditCardOverridePayment", TestData,"Web_Order_Placement");

				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			     // ***************initilizing libraries and testdata************************//
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP06_CreditCardOverridePayment", TestData,"Web_Order_Placement", intCounter);

				// Test Steps execution
					fnOpenTest();
					
			    // Login to CMT Enable Override Payment Options at web group level.
					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));

			    // check Override Payments Options in web Group Level
					cmtLib.setCustomerLevelPermissionsON(data.get("Customer_Permissions_ON"));

			    // select Users and Login As
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				// user_requires_approval;off";
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
					
			    // Login As to Web UAT
					cmtLib.loginAsAdminCMT();
				
				// Login Verification
					cmtLib.loginVerification(data.get("ContactName"));
					
			    // Select First Product and Add to cart
					searchLib.searchInHomePage(data.get("SearchText1"));
					commonLib.addToCartAndVerify();
					continueToCheckOutOnAddCart();
					
				// proceed to checkout
					proceedToCheckout();
					
				//	continueButtonOnAdditionalInformationSection();  // Continue on additional info
				
			    // Click continue on LL info , shipping and billing sections
					clickContinueOnLLIAndShipBillPaySections();
					
			    // Fill payment Info
					//selectPaymentInfoMethodCreditCard(data.get("Card_Number_Error").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"));
					selectPaymentInfoMethodCreditCard(data.get("Card_Number1").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));
				    clickOnReviewOrderButton();

				 // ******************************* need to verify the Credit Card Validation on Cart : Ship, Bill & Pay : Place Order Page">> "Warning Message Exists"************

				 
				// go back to CMT tool 
					cmtLib.navigateBackToCMT();
					
			    //approval_process_setup;ON";;
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
					
			    // Login As to Web UAT
					cmtLib.loginAsAdminCMT();
					
			     // Click on Approval Management
					commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
					
					clickonApprovalManagementTabs(data.get("Approval_Management_Tabs"));
					clickOnTheEditLinkOfRequestorGroupNameEditLink(data.get("Requestor_Group"));
					clickOnTheRequestorGroupNameTabs(data.get("Requestor_Group_Tab"));  // clicking on checkout settings
					selectTheGroupPaymentOptions(data.get("Payment_Option"));
					clickOnTheRequestorGroupNameTabs(data.get("Requestor_Group_Tab2"));// Clicking on Approval path settings
					checkPaymentMethodCheckBox();   // Check payments method check box in Approval path settings
					
			     // Select First Product and Add to cart
					searchLib.searchInHomePage(data.get("SearchText2"));
					commonLib.addToCartAndVerify();
					continueToCheckOutOnAddCart();
					
				// proceed to checkout
					proceedToCheckout();
					
					continueButtonOnAdditionalInformationSection();  // Continue on additional info section
					clickContinueOnLLIAndShipBillPaySections(); // continue on Line level info, Ship bill pay sections
					
			    // Fill payment Info
					selectPaymentInfoMethodCreditCard(data.get("Card_Number1").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));
					clickOnReviewOrderButton();
					String summaryAmount=cartLib.getSummaryAmountInCart();
					placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
						
				// navigate back to CMt
				    cmtLib.navigateBackToCMT();
					cmtLib.clickCheckOutSettings(data.get("checkOut_Settings"));
						
				// navigate to checkout settings >>  payment options
				    cmtLib.selectOptionInCheckoutSettings(data.get("Payment_Options"));
				    cmtLib.clearPaymentOptionsInCheckoutSettings();
				    cmtLib.selectpaymentOptionsInCheckOutSettings(data.get("Options"));
					
				// Login As to Web UAT
				    cmtLib.loginAsAdminCMT();
				    
				// Select First Product and Add to cart
					searchLib.searchInHomePage(data.get("SearchText3"));
					commonLib.addToCartAndVerify();
					continueToCheckOutOnAddCart();
					
					// proceed to checkout
					proceedToCheckout();
					
					continueButtonOnAdditionalInformationSection();  // Continue on additional info section
					clickContinueOnLLIAndShipBillPaySections(); // continue on Line level info, Ship bill pay sections
					// Fill payment Info
					selectPaymentInfoMethodCreditCard(data.get("Card_Number1").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));
					clickOnReviewOrderButton();
					
					// navigate back to CMT
				    cmtLib.navigateBackToCMT();
					cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
					
			   // Login to CMT and disable Override Payment Options
					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));

			    // Uncheck Override Payments Options in web Group Level
					cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
					 //Logout 
					commonLib.clickLogOutLink(data.get("Logout"));
					 fnCloseTest();
				}
				
			}
			 // #############################################################################################################
			// #    Name of the Test         : ODP12_ReportingFieldsIPS
			// #    Migration Author         : Cigniti Technologies
		    // #
			// #    Date of Migration        : September 2019
			// #    Description              : This Test is used to Test ReportingFieldsIPS
			// #    Parameters               : StartRow ,EndRow , nextTestJoin
			// #
			// ############################################################################################################# 

		   @Parameters({ "StartRow", "EndRow", "nextTestJoin"})
		   @Test
		   public void ODP12_ReportingFieldsIPS(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {        
		  	int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP12_ReportingFieldsIPS", TestData, "Web_Order_Placement");
				
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
		           
					// initilizing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP12_ReportingFieldsIPS", TestData, "Web_Order_Placement", intCounter);
					
					
				   // Test Steps execution
				    fnOpenTest(); 
				    
				   // Login to CMT enable Display Additional Notes during the transaction process,Allow File Upload during Checkout,Display Invoice Notes during the transaction process settings at web group level.
				    cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginAsAdminCMT();

				   //Click on bundle Link
				   
				   searchLib.selectNewcontract(data.get("contractName"));
				   searchLib.verifyAccountToolsFromSideMenuAndClickOnProductGrp(data.get("toolsMenuName"),data.get("dropDown") ,data.get("productGroup"),data.get("productName"));
				   orderLib.verifyCartHeaderLabel();
				   //orderLib.editproductQTY(data.get("partNumber"),data.get("qntyNo"));
				   commonLib.updateCartQuantity(data.get("quantity"));
				   //Select another contract
				   searchLib.selectContractInCartPage(data.get("contractName1"));
				   searchLib.searchInHomePage(data.get("SearchText"));
				   cartLib.selectFirstProductDisplay();
			       commonLib.addToCartAndVerify();
			       continueToCheckOutOnAddCart();
			       orderLib.verifyCartHeaderLabel();
			       //Verify contract name in cart page
			     //  commonLib.verifyContractInCart(data.get("ContractName1"));		       
			       proceedToCheckout();
			       enterReportingDetailsInLineLevelInfoSection(data.get("REPORTING FIELD_4"), data.get("REPORTING FIELD_5"), data.get("REPORTING FIELD_6"));
			       orderLib.clickAndVerifyCopytoAllLink();
			       clickContinueOnLineLevelInfo();
			       shippingBillPayContinueButton();
			       shippingBillPayContinueButton();
			       selectPaymentInfoMethodCreditCard(data.get("cardNumber"),data.get("cardName"),data.get("month"),data.get("year"),data.get("PO_Number"));
			       clickOnReviewOrderButton();
			       //Verify reporting fields exists in cart page
			       verifyLineLvlInfoReportingFieldsInCartPage();
			       
			     //Place Order
					String summaryAmountInLogin=cartLib.getSummaryAmountInCart();
					placeOrderAndVerifyReceiptOrderAndDate(summaryAmountInLogin);
					
					//Verify Receipt
					verifyReceiptVerbiage();
					//Verifying reporting fields in order history page		
					verifyReportingFieldsinOrderHistoryPage(data.get("toolsMenuName1"),data.get("dropDown1") ,data.get("productGroup1"),data.get("refNum"));
			       
					fnCloseTest();		   
				   
	}
		   }
		   
		    // #############################################################################################################
			// #    Name of the Test         : ODP11_SmartTrackersPlaceOrder
			// #    Migration Author         : Cigniti Technologies
		    // #
			// #    Date of Migration        : September 2019
			// #    Description              : This Test is used to Test SmartTrackersPlaceOrder
			// #    Parameters               : StartRow ,EndRow , nextTestJoin
			// #
			// ############################################################################################################# 

		   @Parameters({ "StartRow", "EndRow", "nextTestJoin"})
		   @Test
		   public void ODP11_SmartTrackersPlaceOrder(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {        
		  	int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP11_SmartTrackersPlaceOrder", TestData, "Web_Order_Placement");
				
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
		           
					// initilizing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP11_SmartTrackersPlaceOrder", TestData, "Web_Order_Placement", intCounter);
					
					
				   // Test Steps execution
				    fnOpenTest(); 
				    
				   // Login to CMT enable Display Additional Notes during the transaction process,Allow File Upload during Checkout,Display Invoice Notes during the transaction process settings at web group level.
				    cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginAsAdminCMT();
					//Select product and add to cart
					searchLib.searchInHomePage(data.get("SearchText"));
					   cartLib.selectFirstProductDisplay();
				       commonLib.addToCartAndVerify();
				       continueToCheckOutOnAddCart();
				       orderLib.verifyCartHeaderLabel();
				       proceedToCheckout();
				       addAdditionalInformation(data.get("Url"), data.get("RP_HDL_Txt"), data.get("WG_HDL_Txt"), data.get("Additional_Notes"), data.get("Invoice_Notes"));
				       clickOnAdditionalInfoContinueButton();
				       addLineLevelInfoSmartTracker(data.get("rP_LNL_Txt"));
				       shippingBillPayContinueButton();
				       shippingBillPayContinueButton();
				       shippingBillPayContinueButton();
				       selectPaymentInfoMethodCreditCard(data.get("cardNumber"),data.get("cardName"),data.get("month"),data.get("year"),data.get("PO_Number"));
				       // Verify PO NUmber is empty
				       verifyPONumberisEmpty();
				       clickOnReviewOrderButton();
				       verifyPlaceOrderLabel();
				     //Place Order
					   String summaryAmountInLogin=cartLib.getSummaryAmountInCart();
				       placeOrderAndVerifyReceiptOrderAndDate(summaryAmountInLogin);
				       String RefNumber= orderLib.getTextfromReferenceNumber();
				       //Verifying order details
				       clickOrderDetailsLinkOnReceiptPage();
				       searchLib.verifyAccountToolsFromSideMenuAndClick(data.get("toolsMenuName"),data.get("dropDown"));
				       clickonorderNumLinkinRecentorders(RefNumber);
				       //Order details Page verification
				       verifytabsinOrderDetailsPage(data.get("TabName"));
				       verifySmartTrackerHeaderInOrderDetails();
				       verifytabsinOrderDetailsPage(data.get("TabName1"));		
				       commonLib.clickLogOutLink(data.get("Logout"));
				       fnCloseTest();		   
	}
		   }
		    // #############################################################################################################
			// #    Name of the Test         : ODP15_OrderswithMultipleBOMsRequisition
			// #    Migration Author         : Cigniti Technologies
		    // #
			// #    Date of Migration        : September 2019
			// #    Description              : This Test is used to Test OrderswithMultipleBOMsRequisition
			// #    Parameters               : StartRow ,EndRow , nextTestJoin
			// #
			// ############################################################################################################# 

		   @Parameters({ "StartRow", "EndRow", "nextTestJoin"})
		   @Test
		   public void ODP15_OrderswithMultipleBOMsRequisition(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {        
		  	int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP15_OrderswithMultipleBOMsRequisition", TestData, "Web_Order_Placement");
				
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
		           
					// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP15_OrderswithMultipleBOMsRequisition",
					TestData, "Web_Order_Placement", intCounter);
					
	 // Test Steps execution
		   fnOpenTest(); 
				    
	  // Login to CMT enable Display Additional Notes during the transaction process,Allow File Upload during Checkout,Display Invoice Notes during the transaction process settings at web group level.
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
			cmtLib.loginAsAdminCMT();

			// Select client in product standards page
			searchLib.verifyProductWStandardsPage();
			searchLib.verifyClientAndClickOnProductGrp(data.get("productName"));
			orderLib.verifyCartHeaderLabel();
			// Verify added bundle exists in cart screen
			cartLib.verifyProductGroupBundleAddedToCart(data.get("productName"));
			proceedToCheckout();
			clickOnAdditionalInfoContinueButton();
			// Click continue on shipping address
			shippingOptionsCarrierSelection();
			scrollToBottom();
			shippingBillPayContinueButton(); // Click continue on shipping
												// options
			shippingBillPayContinueButton();
			addNewCardInPayment(data.get("cardNumber"), data.get("cardName"), data.get("month"), data.get("year"),
					data.get("poNumebr"));
			clickOnReviewRequisitionButton();
			verifyPlaceOrderLabel();
			clickOnPlaceRequisitionButton();
			// Verify Receipt
			verifyReceiptVerbiage();
			String RefNumber = orderLib.getTextfromReferenceNumber();
			commonLib.clickLogOutLink(data.get("header1"));
			// Login with 2nd user
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
			cmtLib.loginAsAdminCMT();

			searchLib.verifyProductWStandardsPage();
			searchLib.verifyAccountToolsFromSideMenuAndClick(data.get("toolsMenuName"), data.get("dropDown"));
			verifyandClickonRefLink(RefNumber);
			verifyApprovalManagmentHeaderandClickonUpdateLink();
			commonLib.clickLogOutLink(data.get("header1"));
			// Login with 1st user
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolsFromSideMenuAndClick(data.get("toolsMenuName"), data.get("dropDown1"));
			clickonorderNumLinkinRecentorders(RefNumber);
			verifyPartNumberInOrderdetails(data.get("item1"));
			commonLib.clickLogOutLink(data.get("Logout"));
			fnCloseTest();
		}
	}
	// #############################################################################################################
	// #   Name of the Test                : ODP10_SharedUser
	// #   Migration Author                : Cigniti Technologies
	// #
	// #   Date of Migration               : September 2019
	// #   Description                     : This Test is used to Test shared users
	// #   Parameters                      : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void ODP10_SharedUser(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP10_SharedUser", TestData, "Web_Order_Placement");

		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			           
			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP10_SharedUser", TestData,"Web_Order_Placement", intCounter);

			// Test Steps execution
			fnOpenTest();
					    
					   // Login to CMT enable Display Additional Notes during the transaction process,Allow File Upload during Checkout,Display Invoice Notes during the transaction process settings at web group level.
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("menuName"), data.get("userPermission"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
			cmtLib.loginAsAdminCMT();
			searchLib.searchInHomePage(data.get("SearchText"));
			cartLib.selectFirstProductDisplay();
			commonLib.addToCartAndVerify();
			continueToCheckOutOnAddCart();
			orderLib.verifyCartHeaderLabel();
			proceedToCheckout();
			addAdditionalInformationByNameFields(data.get("url"), data.get("name"), data.get("phonembr"),
					data.get("email"));
			clickOnAdditionalInfoContinueButton();
			clickContinueOnLineLevelInfo();
			shippingOptionsCarrierSelection();
			scrollToBottom();
			shippingBillPayContinueButton();
			shippingBillPayContinueButton();
			addNewCardInPayment(data.get("cardNumber"), data.get("cardName"), data.get("month"), data.get("year"),data.get("poNumebr"));
			clickOnReviewRequisitionButton();
			verifyPlaceOrderLabel();
			clickOnPlaceRequisitionButton();
			VerifyOrderPlaceByFields();
			// Verify Receipt
			verifyReceiptVerbiage();

			String RefNumber = orderLib.getTextfromReferenceNumber();
			// Logout
			commonLib.clickLogOutLink(data.get("header1"));
			// Login with 2nd user admin
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("menuName"), data.get("userPermission1"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolsFromSideMenuAndClick(data.get("toolsMenuName"), data.get("dropDown"));
			verifyandClickonRefLink(RefNumber);
			verifyApprovalManagmentHeaderandClickonUpdateLink();
			commonLib.clickLogOutLink(data.get("header1"));
			// Login with 3rd user Approver
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("menuName"), data.get("userPermission1"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2"));
			cmtLib.loginAsAdminCMT();
			searchLib.verifyAccountToolsFromSideMenuAndClick(data.get("toolsMenuName"), data.get("dropDown"));
			verifyandClickonRefLink(RefNumber);
			verifyApprovalManagmentHeaderandClickonUpdateLink();
			verifyOrderNumberExists(RefNumber);
			commonLib.clickLogOutLink(data.get("Logout"));
			fnCloseTest();

					}
			   }
}
