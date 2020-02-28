package com.insight.WebTest;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class ReviewOrderTest extends OrderLib{
	
	ProductDisplayInfoLib prodInfoLib=new ProductDisplayInfoLib();
	CMTLib cmtLib=new CMTLib();
	SearchLib searchLib=new SearchLib();
	CommonLib commonLib=new CommonLib();
	CartLib cartLib=new CartLib();
	ProductDisplayInfoLib prodLib=new ProductDisplayInfoLib();

	// #############################################################################################################
 	// #    Name of the Test         : ROD01_FCTWebReviewOrderAdditionalOrderInfo
 	// #    Migration Author         : Cigniti Technologies
    // #
 	// #    Date of Migration        : September 2019
 	// #    Description              : This Test is used to Test FCTWebReviewOrderAdditionalOrderInfo
 	// #    Parameters               : StartRow ,EndRow , nextTestJoin
 	// #
 	// ############################################################################################################# 

     @Parameters({ "StartRow", "EndRow", "nextTestJoin"})
     @Test
     public void ROD01_FCTWebReviewOrderAdditionalOrderInfo(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
       
    	int intStartRow = StartRow;
 		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD01_FCTWebReviewOrderAdditionalOrderInfo", TestData, "Web_Review_Order");
 		
 		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
             
 			// initilizing libraries and testdata
 			ReportStatus.fnDefaultReportStatus();
 			ReportControl.intRowCount = intCounter;
 			Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD01_FCTWebReviewOrderAdditionalOrderInfo", TestData, "Web_Review_Order", intCounter);
 			
 			
 		 // Test Steps execution
 		    fnOpenTest(); 
 		//********************Login to CMT enable Display Additional Notes during the transaction process,************************************************//
 		//****** Allow File Upload during Checkout,Display Invoice Notes during the transaction process settings at web group level***********************//
 		   cmtLib.loginToCMT(data.get("Header"));
 		   cmtLib.searchForWebGroup( data.get("WebGrp"));
 		   cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
 		   String[] permissions=data.get("Customer_Permissions").split(","); 
 		   for(i=0;i<permissions.length;i++){
 			   cmtLib.setCustomerLevelPermissionsON(permissions[i]); 
 		     }
 		   cmtLib. hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
 		   cmtLib.searchForaUserAndSelect(data.get("lnameEmailUname"),data.get("ContactName"));
 	       cmtLib.loginAsAdminCMT();
 	       // Back to UAT
 	       searchLib.searchInHomePage(data.get("IPP_API_Part"));
 	       // Add a item & warranty to cart >>  proceed To Checkout >> place order >> Verify the review order details,Receipt Order And Date
 	       commonLib.addToCartAndVerify();
 	       continueToCheckOutAddWarrantyAndVerifyTheCart(data.get("Warrenty_Part_Number"));
 	       proceedToCheckout();
 	       addRPandWGinfoInAddAdditionalInfo(data.get("RP_HDL_Txt"), data.get("WG_HDL_Txt"), data.get("Additional_Notes"), data.get("Invoice_Notes"));
 	       addLineLevelInformation(data.get("RP_LNL_Txt"), data.get("WG_LNL_Txt"));
 	       shippingBillPayContinueButton();  // continue button on Shipping address
		   shippingOptionsCarrierSelection();  // carrier selection or continue in shipping options
		   shippingBillPayContinueButton();  // Continue on billing address section
		   selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PO_Number"));
 	       String summaryAmount1=cartLib.getSummaryAmountInCart();
			placeOrderAndVerifyReceiptOrderAndDate(summaryAmount1);
 	       // Click company standards and add a bundle of items
 	       commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"),data.get("Product_Group"),data.get("Product_Name"));
 	       commonLib.addToOrderAndViewCart();
 	       cartLib.verifyProductGroupBundleAddedToCart(data.get("Product_Name"));
 	       proceedToCheckout();
	       addAdditionalInformation(data.get("Url"), data.get("RP_HDL_Txt"), data.get("WG_HDL_Txt"), data.get("Additional_Notes"), data.get("Invoice_Notes"));
	       addLineLevelInformation(data.get("RP_LNL_Txt"), data.get("WG_LNL_Txt"));
	       shippingBillPay(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PONumber"));
	       String summaryAmount2=cartLib.getSummaryAmountInCart();
			placeOrderAndVerifyReceiptOrderAndDate(summaryAmount2);
	       // Navigate back to CMT and Un-check the above checked Customer permissions.
	       cmtLib.navigateBackToCMT();
	       cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
	       cmtLib.searchForWebGroup( data.get("WebGrp"));
 		   cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
	       for(i=0;i<permissions.length;i++){
 			   cmtLib.setCustomerLevelPermissionsOFF(permissions[i]); 
 		     }
	       fnCloseTest();
         }
     }
     
    // #############################################################################################################
  	// #    Name of the Test         : ROD02_FCTWebReviewOrderCartFunctions
  	// #    Migration Author         : Cigniti Technologies
    // #
  	// #    Date of Migration        : September 2019
  	// #    Description              : This Test is used to Test FCTWebReviewOrderAdditionalOrderInfo
  	// #    Parameters               : StartRow ,EndRow , nextTestJoin
  	// #
  	// ############################################################################################################# 
     @Parameters({ "StartRow", "EndRow", "nextTestJoin"})
     @Test
     public void ROD02_FCTWebReviewOrderCartFunctions(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
       
    	int intStartRow = StartRow;
 		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD02_FCTWebReviewOrderCartFunctions", TestData, "Web_Review_Order");
 		
 		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
             
 			// initilizing libraries and testdata
 			ReportStatus.fnDefaultReportStatus();
 			ReportControl.intRowCount = intCounter;
 			Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD02_FCTWebReviewOrderCartFunctions", TestData, "Web_Review_Order", intCounter);
 			
 			
 		 // Test Steps execution
 		    fnOpenTest(); 
 		 
 		// Login to CMT tool and enable permission : Enable Crosssell 
 		    cmtLib.loginToCMTSetPermissions(data.get("Header"), data.get("WebGrp"), data.get("WebGrp_Name"), data.get("Manage_Web_Grp_Options"), data.get("LnameEmailUname"), data.get("ContactName"),data.get("Menu_Name"), data.get("Set_Permission"));
 		    
 		// Login As To UAT Web
 		   searchLib.searchInHomePage(data.get("SearchText1"));
 		   commonLib.addToCartAndVerify();
 		   continueToCheckOutAddWarrantyAndVerifyTheCart(data.get("Warrenty_Part_Number")); 
 		   cartLib.verifyItemAddedInCartByMfrNumber(data.get("SearchText1"));
 		   
 		 // Searching for second item
 		   searchLib.searchInHomePage(data.get("SearchText2"));
 		   searchLib.verifyTheResultsForSearchTerm(data.get("SearchText2"));
		   commonLib.addFirstDisplyedItemToCartAndVerify();
		   continueToCheckOutOnAddCart();  
		   proceedToCheckout();
		   addAdditionalInformation(data.get("Url"), data.get("RP_HDL_Txt"), data.get("WG_HDL_Txt"), data.get("Additional_Notes"), data.get("Invoice_Notes"));
	       addLineLevelInformation(data.get("RP_LNL_Txt"), data.get("WG_LNL_Txt"));
	       shippingBillPay(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PONumber"));
	       
	       //Navigate Back To CMT >>  Disable Crosssell and Enable Insight License View .
	       cmtLib.navigateBackToCMT();
		   cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission")); // Disable Crosssell
		  
		   fnCloseTest();
 		}
     }
   
     // #############################################################################################################
   	 // #    Name of the Test         : ROD03_FCTWebReviewOrderEdit
   	 // #    Migration Author         : Cigniti Technologies
     // #
   	 // #    Date of Migration        : September 2019
     // #    Description              : This Test is used to Test FCTWebReviewOrderEdit
     // #    Parameters               : StartRow ,EndRow , nextTestJoin
   	 // #
   	 // ############################################################################################################# 
     
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void ROD03_FCTWebReviewOrderEdit(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD03_FCTWebReviewOrderEdit", TestData,"Web_Review_Order");

		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

	  //********* Initilizing libraries and testdata**********//
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD03_FCTWebReviewOrderEdit", TestData,"Web_Review_Order", intCounter);
			TestEngineWeb.reporter.initTestCaseDescription("INSIGHT WEB Testcase Description is: "+"FCT_Web Review Order Edit" );
	   //********** Test Steps execution***************//
			fnOpenTest();
	   //**** Login to CMT and Disable allow File Upload during Checkout and Override Payment Options *****//
			cmtLib.loginToCMT(data.get("Header"));
			cmtLib.searchForWebGroup(data.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
			String[] permissions = data.get("Customer_Permissions").split(",");
			for (i = 0; i < permissions.length; i++) {
				cmtLib.setCustomerLevelPermissionsOFF(permissions[i]);
			}
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
			cmtLib.loginAsAdminCMT();
		//************* Login As to UAT web ******************************//
			searchLib.searchInHomePage(data.get("SearchText"));
		//***** Add a item to cart >> proceed To Checkout >> Review Order****//
			commonLib.addToCartAndVerify();
			continueToCheckOutOnAddCart();
			proceedToCheckout();
		//******** Click continue on Line level Info, Ship and Bill pay sections ********************//
			continueButtonOnAdditionalInformationSection();
			clickContinueOnLineLevelInfo();
			shippingBillPayContinueButton();
			shippingOptionsCarrierSelection();
			shippingBillPayContinueButton();
		//**************************** Enter payment Info *****************************************//	
			selectPaymentInfoMethodCreditCard(data.get("Card_Number1").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"));
            clickOnReviewOrderButton();
		//******************************* Click on Edit link on Payment info section****************//
			editOrderInfo(data.get("Section_Name"));
	    //******************************* Verify card Number Ending with the given details***********//
			verifyCardNumberOnEditPaymentInfoSection(data.get("Card_Ending_Digits1"));
	    // ******************************* Add new card in payment Info *****************************//
			addNewCardInPaymentInfoSection(data.get("Card_Number2").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"), data.get("PO_Number"));
	    //********************** Verify the newly added card ending digits***********************//
			editOrderInfo(data.get("Section_Name"));
			verifyCardNumberOnEditPaymentInfoSection(data.get("Card_Ending_Digits2"));
		//*********************** Review order **************************************//
			clickOnReviewOrderButton();
		//*************verify product description in place order screen*************//
			clickOnProdDescOnPlaceOrderScreen();
			commonLib.verifyDisplayedProductDetails(data.get("SearchText"));
			commonLib.addToCartAndVerify();
			continueToCheckOutOnAddCart();
	    //*****Proceed to check out and fill all the ship / Bill details and click review Order*****//
			proceedToCheckout();
			continueButtonOnAdditionalInformationSection();
			clickContinueOnLineLevelInfo();
			shippingBillPayContinueButton();
			shippingOptionsCarrierSelection();
			shippingBillPayContinueButton();
			addNewCardInPaymentInfoSection(data.get("Card_Number3").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"), data.get("PO_Number"));
			clickOnReviewOrderButton();
	   //*************Verify card ending details of third card *********************************//
			editOrderInfo(data.get("Section_Name"));
			verifyCardNumberOnEditPaymentInfoSection(data.get("Card_Ending_Digits3"));
			commonLib.clickLogOutLink(data.get("Logout"));
			fnCloseTest();
		}
      }
      
         // #############################################################################################################
    	 // #    Name of the Test         : ROD04_FCTWebReviewOrderOrderUtilities
    	 // #    Migration Author         : Cigniti Technologies
         // #
    	 // #    Date of Migration        : September 2019
         // #    Description              : This Test is used to Test FCTWebReviewOrderOrderUtilities
         // #    Parameters               : StartRow ,EndRow , nextTestJoin
    	 // #
    	 // ############################################################################################################# 
      
       @Parameters({ "StartRow", "EndRow", "nextTestJoin"})
       @Test
       public void ROD04_FCTWebReviewOrderOrderUtilities(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
         
      	int intStartRow = StartRow;
   		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD04_FCTWebReviewOrderOrderUtilities", TestData, "Web_Review_Order");
   		
   		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
               
   			// Initilizing libraries and testdata
   			ReportStatus.fnDefaultReportStatus();
   			ReportControl.intRowCount = intCounter;
   			Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD04_FCTWebReviewOrderOrderUtilities", TestData, "Web_Review_Order", intCounter);
   			TestEngineWeb.reporter.initTestCaseDescription("INSIGHT_WEB Testcase is : FCT_Web Review Order OrderUtilities ");
   			
   	    // Test Steps execution
   		    fnOpenTest(); 
   		    
   		    // Login to CMT  >>  Enable Saved Carts / Order Templates and Enable Quotes
   		    cmtLib.loginToCMTSearchForUserAndSelect(data.get("Header"), data.get("WebGrp"), data.get("WebGrp_Name"), data.get("Manage_Web_Grp_Options"), data.get("LnameEmailUname"), data.get("ContactName"));
			String[] permissions = data.get("Set_Permission").split(",");
			for (i = 0; i < permissions.length; i++) {
				cmtLib.setPermissions(data.get("Menu_Name"), permissions[i]);
			}
			// Login As to Web UAT
			cmtLib.loginAsAdminCMT();
			searchLib.searchInHomePage(data.get("SearchText1"));
			commonLib.verifyDisplayedProductDetails(data.get("SearchText1"));

			// Cart verification
			commonLib.addToCartAndVerify();
			continueToCheckOutOnAddCart();
			cartLib.verifyItemAddedInCartByMfrNumber(data.get("SearchText1"));

			// proceed To Checkout >> Fill Additional Information section >>>
			// Fill Line level Information >>> Fill Order and Item Info page -
			// Review Order
			proceedToCheckout();
			addLineLevelInformation(data.get("RP_LNL_Txt"), data.get("WG_LNL_Txt"));
			clickContinueOnLineLevelInfo();
			shippingBillPayContinueButton();
			shippingOptionsCarrierSelection();
			shippingBillPayContinueButton();
			 selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PO_Number"));
			cartLib.ClickExportCartAndVerify(data.get("Order_Utilities"));
			verifySaveOrderTemplateExistsOnPlaceOrderPage(data.get("Permission_Status1")); //save  Order Template, Save cart contents links should display
																							
			// Navigate back to CMT
			cmtLib.navigateBackToCMT();

			// Disable Saved Carts / Order Templates and Enable Quotes.

			for (i = 0; i < permissions.length; i++) {
				cmtLib.setPermissionsToDisable(data.get("Menu_Name"), permissions[i]);
			}

			// Login As to Web UAT
			cmtLib.loginAsAdminCMT();
			searchLib.searchInHomePage(data.get("SearchText2"));
			commonLib.verifyDisplayedProductDetails(data.get("SearchText2"));

			// Cart verification
			commonLib.addToCartAndVerify();
			continueToCheckOutOnAddCart();
			cartLib.verifyItemAddedInCartByMfrNumber(data.get("SearchText2"));

			// proceed To Checkout >> Fill Additional Information section >>>
			// Fill Line level Information >>> Fill Order and Item Info page -
			// Review Order
			proceedToCheckout();
			addLineLevelInformation(data.get("RP_LNL_Txt"), data.get("WG_LNL_Txt"));
			shippingBillPay(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"),
					data.get("Year"), data.get("PONumber"));
			verifySaveOrderTemplateExistsOnPlaceOrderPage(data.get("Permission_Status2")); // save  Order Template, Save cart contents links should not exist
			fnCloseTest();
		}
       }

       // #############################################################################################################
  	   // #    Name of the Test         : ROD06_FCTWebReviewExport
  	   // #    Migration Author         : Cigniti Technologies
       // #
  	   // #    Date of Migration        : September 2019
       // #    Description              : This Test is used to Test FCTWebReviewExport
       // #    Parameters               : StartRow ,EndRow , nextTestJoin
  	   // #
  	   // ############################################################################################################# 
    
     @Parameters({ "StartRow", "EndRow", "nextTestJoin"})
     @Test
     public void ROD06_FCTWebReviewExport(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
       
    	int intStartRow = StartRow;
 		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD06_FCTWebReviewExport", TestData, "Web_Review_Order");
 		
 		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
             
 			// Initilizing libraries and testdata
 			ReportStatus.fnDefaultReportStatus();
 			ReportControl.intRowCount = intCounter;
 			Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD06_FCTWebReviewExport", TestData, "Web_Review_Order", intCounter);
 			
 	     // Test Steps execution
 		    fnOpenTest(); 
 		    cmtLib.loginToCMTSelectUserAndLoginAS(data.get("Header"), data.get("WebGrp"), data.get("WebGrp_Name"), data.get("Manage_Web_Grp_Options"), data.get("LnameEmailUname"), data.get("ContactName"));
 		    searchLib.searchInHomePage(data.get("SearchText1"));
            prodLib.selectFirstProductAddToCartAndVerifyCart();
            cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("QuickShop_Part"), data.get("Quantity"));
           
            
         // ******** Need To Add verify Export Excel ***************//   -- Verify 1 item added
            cartLib.ClickExportCartAndVerify(data.get("Order_Utilities"));
         
            // proceed To Checkout >> Fill Additional Information section >>>  Fill Line level Information >>> Fill Order and Item Info page - Review Order
            proceedToCheckout();
            clickContinueOnLLIAndShipBillPaySections(); // Click continue
            selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PO_Number"));
            clickOnReviewOrderButton();
            
        // ******** Need To Add verify Export Excel ***************//   -- Verify 2 items added
            cartLib.ClickExportCartAndVerify(data.get("Order_Utilities"));
            
        // SearchPart OR Product
            searchLib.searchInHomePage(data.get("SearchText2"));
            prodLib.selectFirstProductAddToCartAndVerifyCart();
            
        // proceed To Checkout and click continue on Line Level information Section, shipping address,Shipping options, Billing address section >> Review Order
            proceedToCheckout();
            clickContinueOnLineLevelInfo();   
            shippingBillPayContinueButton();
            shippingOptionsCarrierSelection();
            shippingBillPayContinueButton();
            clickOnReviewOrderButton();
            
         // ******** Need To Add verify Export Excel ***************//   -- Verify 3 items added
            cartLib.ClickExportCartAndVerify(data.get("Order_Utilities"));
            
         // Navigate to Account tools >> Company Standards
            searchLib.selectAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"),data.get("Product_Group"),data.get("Product_Name"));
            searchLib.clickAddToOrderOnCompanyStandardsScreen();
           
         // Verifying Bundle added to cart
            cartLib.verifyProductGroupBundleAddedToCart(data.get("Product_Name"));
            
         // proceed To Checkout >> Fill Additional Information section >>>  Fill Line level Information >>> Fill Order and Item Info page - Review Order 
            proceedToCheckout();
           
            continueButtonOnAdditionalInformationSection();   // Click continue button on Add additional info
            clickContinueOnLLIAndShipBillPaySections(); // Click continue
            clickOnReviewOrderButton();       // Click Review order button
            
         // ******** Need To Add verify Export Excel ***************//   -- Verify bundle items added
               cartLib.ClickExportCartAndVerify(data.get("Order_Utilities"));
               fnCloseTest();
               
}
     }
     
       // #############################################################################################################
	   // #    Name of the Test         : ROD07_FCTWebReviewExportIPS
	   // #    Migration Author         : Cigniti Technologies
       // #
	   // #    Date of Migration        : September 2019
       // #    Description              : This Test is used to Test FCTWebReviewExportIPS
       // #    Parameters               : StartRow ,EndRow , nextTestJoin
	   // #
	   // ############################################################################################################# 
  
   @Parameters({ "StartRow", "EndRow", "nextTestJoin"})
   @Test
   public void ROD07_FCTWebReviewExportIPS(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
     
  	int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD07_FCTWebReviewExportIPS", TestData, "Web_Review_Order");
		
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
           
		// Initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD07_FCTWebReviewExportIPS", TestData, "Web_Review_Order", intCounter);
			
	     // Test Steps execution
		    fnOpenTest(); 
		   
		    // Login to CMT and disable override_payment_options;off"
		    cmtLib.loginToCMT(data.get("Header"));
	 		cmtLib.searchForWebGroup( data.get("WebGrp"));
	 		cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
	 		cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions"));
	 		cmtLib. hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
	 		cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"),data.get("ContactName"));
	 	    
	 		// Login As to UAT Web
	 		cmtLib.loginAsAdminCMT();
	 		
	 		searchLib.selectNewcontract(data.get("Contract_Name1"));
	 		searchLib.searchInHomePage(data.get("SearchText"));
            prodLib.selectFirstProductAddToCartAndVerifyCart();
            commonLib.updateCartQuantity(data.get("Quantity"));
            searchLib.selectNewcontract(data.get("Contract_Name2"));  // Selecting second contract
            searchLib.searchInHomePage(data.get("SearchText"));
            prodLib.selectFirstProductAddToCartAndVerifyCart();
            commonLib.updateCartQuantity(data.get("Quantity"));
            
            proceedToCheckout();
            enterReportingDetailsInLineLevelInfoSection(data.get("REPORTING FIELD_4"), data.get("REPORTING FIELD_5"), data.get("REPORTING FIELD_6"));
            
            shippingBillPayContinueButton();   // Click continue on Shipping address
            shippingOptionsCarrierSelection();   // Click continue on Shipping options
            shippingBillPayContinueButton();   // Billing address continue button
            selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PO_Number"));
            clickOnReviewOrderButton(); // Click Review order button on payment info
            
            // ******** Need To Add verify Export Excel ***************//  
            
            int rowNumber 		= 1; 		// zero based index
			String sfile = "C:/Users/e002542/Downloads/exportCart.xls";
            List<String> downloadedExcelContent = CommonLib.readRowFromExcel(sfile, data.get("Sheet_Name"),rowNumber);
			List<String> acutalContent          = Arrays.asList(data.get("Column_Headers").split(","));
			
			System.out.println("Compare content"+downloadedExcelContent.equals(acutalContent));
			
			cartLib.verifyTheExportedCart(downloadedExcelContent, acutalContent);
            // -- Verify excel columns: (MFR Part Number, Insight Part, Product, Stock, Quantity, Unit Price, Total Price,Contract Title, Contract Number,  REPORTING FIELD 4,5,6,)
            cartLib.ClickExportCartAndVerify(data.get("Order_Utilities"));
            commonLib.clickLogOutLink(data.get("Logout"));
            fnCloseTest();
		}
   }

  // #############################################################################################################
 	// #    Name of the Test         : ROD11_FCTWebReviewOrderESDParts_Action1
 	// #    Migration Author         : Cigniti Technologies
    // #
 	// #    Date of Migration        : September 2019
 	// #    Description              : This Test is used to Test FCTWebReviewOrderESDParts
 	// #    Parameters               : StartRow ,EndRow , nextTestJoin
 	// #
 	// ############################################################################################################# 

    @Parameters({ "StartRow", "EndRow", "nextTestJoin"})
    @Test
    public void ROD11_FCTWebReviewOrderESDParts(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
      
   	int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD11_FCTWebReviewOrderESDParts", TestData, "Web_Review_Order");
		
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
            
			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD11_FCTWebReviewOrderESDParts", TestData, "Web_Review_Order", intCounter);
			
			
		 // Test Steps execution
		    fnOpenTest(); 
		    
		   // Login to CMT enable Display Additional Notes during the transaction process,Allow File Upload during Checkout,Display Invoice Notes during the transaction process settings at web group level.
 		   cmtLib.loginToCMT(data.get("Header"));
 		   cmtLib.searchForWebGroup( data.get("WebGrp"));
 		   cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
 		   cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("manage_Web_Grp_Options"));
 		   cmtLib.searchForaUserAndSelect(data.get("lnameEmailUname"),data.get("ContactName"));
 		   cmtLib.loginAsAdminCMT();
 		   
 		// Login As to UAT web
 	       searchLib.searchInHomePage(data.get("SearchText"));
 	       cartLib.selectFirstProductDisplay();
 	       
 	       
 	    // Add a item >>  proceed To Checkout >> place order >> Review Order
  	       commonLib.addToCartAndVerify();
  	       continueToCheckOutOnAddCart();
  	       Thread.sleep(3000);
  	       String ActualTax= getText(DEFAULT_TAX_AMOUNT, "Tax displayed after adding 1st product");
  	       proceedToCheckout();
  	       addAdditionalInformation(data.get("Url"), data.get("RP_HDL_Txt"), data.get("WG_HDL_Txt"), data.get("Additional_Notes"), data.get("Invoice_Notes"));
  	       AddNewshippingAddress(data.get("link"),data.get("companyName"),data.get("street"),data.get("city"),data.get("zipcode"),data.get("state"));    	       
  	       termsInPaymentInfo(data.get("PONumber"));
  	      //click on edit cart item
  	       clickOnProdDescOnPlaceOrderScreen();
  	       Thread.sleep(5000);
  	       searchLib.searchInHomePage(data.get("SearchText1"));	     
	       	       
	    // Add a item with licence >>  proceed To Checkout >> place order >> Review Order
	       commonLib.addToCartAndVerify();
	       continueToCheckOutOnAddCart();
	       proceedToCheckout();	       
	       addAdditionalInformation(data.get("Url"), data.get("RP_HDL_Txt"), data.get("WG_HDL_Txt"), data.get("Additional_Notes"), data.get("Invoice_Notes"));	       
	       addLineLevelInformation(data.get("RP_LNL_Txt"), data.get("WG_LNL_Txt"));
	       shippingBillPay(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"), data.get("PONumber1"));
  	       
  	 //  Verify tax amount is same or not before and after adding licence to the product
	       verifyTheTaxForSearchTerm(ActualTax);
  	       
	     fnCloseTest();     		   
}
    }
    // #############################################################################################################
	// #    Name of the Test         : ROD10_FCTWebReviewOrderTax
	// #    Migration Author         : Cigniti Technologies
   // #
	// #    Date of Migration        : September 2019
	// #    Description              : This Test is used to Test FCTWebReviewOrderESDParts
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ############################################################################################################# 

   @Parameters({ "StartRow", "EndRow", "nextTestJoin"})
   @Test
   public void ROD10_FCTWebReviewOrderTax(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {        
  	int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD10_FCTWebReviewOrderTax", TestData, "Web_Review_Order");
		
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
           
			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD10_FCTWebReviewOrderTax", TestData, "Web_Review_Order", intCounter);
			
			
		   // Test Steps execution
		    fnOpenTest(); 
		    
		   // Login to CMT enable Display Additional Notes during the transaction process,Allow File Upload during Checkout,Display Invoice Notes during the transaction process settings at web group level.
		   cmtLib.loginToCMT(data.get("Header"));
		   cmtLib.searchForWebGroup( data.get("WebGrp"));
		   cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));   		 
		   cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("manage_Web_Grp_Options"));
		   cmtLib.searchForaUserAndSelect(data.get("lnameEmailUname"),data.get("ContactName"));
		   cmtLib.setPermissions(data.get("menuName"),data.get("userPermissions"));
		   cmtLib.loginAsAdminCMT();
		  
		   
		   // Login As to UAT web
		  
	       searchLib.searchInHomePage(data.get("SearchText"));
	       // Add a item  >>  proceed To Checkout >> place order >> Review Order
	       commonLib.addToCartAndVerify();
	       continueToCheckOutOnAddCart();
	      
	       proceedToCheckout();
	       addAdditionalInformation(data.get("Url"), data.get("RP_HDL_Txt"), data.get("WG_HDL_Txt"), data.get("Additional_Notes"), data.get("Invoice_Notes"));	       
	       AddNewshippingAddress(data.get("link1"),data.get("companyName"),data.get("street"),data.get("city"),data.get("zipcode"),data.get("state"));
	       Thread.sleep(2000);
	       addNewCardInPayment(data.get("cardNumber"),data.get("cardName"),data.get("month"),data.get("year"),data.get("poNumebr"));	    
	       
	       // Verify Tax exemption message is displayed or not
	       taxDeclerationCheckBoxON();
	       clickOnReviewOrderButton();
	      
	       //Click on return to cart link
	       clickOnReturnToCartLink();  
	       verifyCartHeaderLabel();
	       	       
	       //Add 1st part which requires EWR fee
	     
	       searchLib.searchInHomePage(data.get("SearchText1"));
	       cartLib.selectFirstProductDisplay();
	       commonLib.addToCartAndVerify();
	       continueToCheckOutOnAddCart();
	      
	       //Add 2nd part which requires EWR fee
	       searchLib.searchInHomePage(data.get("SearchText2"));
	       commonLib.addToCartAndVerify();
	       continueToCheckOutOnAddCart();
	       verifyCartHeaderLabel();
	      
	       //verify part items added to cart and update quantity
	       
	       cartLib.verifyItemInCart(data.get("SearchText1"));	       
	       editproductQTY(data.get("partNumber"),data.get("qntyNo"));
	       cartLib.verifyItemInCart(data.get("SearchText2"));
	       editproductQTY(data.get("partNumber1"),data.get("qntyNo1"));
	       proceedToCheckout();
	       addAdditionalInformation(data.get("Url"), data.get("RP_HDL_Txt"), data.get("WG_HDL_Txt"), data.get("Additional_Notes"), data.get("Invoice_Notes"));	
	       addLineLevelInfo();
	       shippingBillPay(data.get("Card_Number").toString(),data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PONumber"));
	       taxDeclerationCheckBoxOFF();
	       clickOnReviewOrderButton();
	       verifyPlaceOrderLabel();	       
	       editOrderInfo(data.get("sectionName"));
	     
	       //Uncheck the tax checkbox in payment Info
	       taxDeclerationUnCheck();
	       clickOnReviewOrderButton();
	       verifyTheTaxAfterUncheckingTaxExemptionCheckbox();
	       verifyPlaceOrderLabel();
	       editOrderInfo(data.get("sectionName"));
	       
	       //Check the tax checkbox in payment Info
	       taxDeclerationCheckBoxON();
	       clickOnReviewOrderButton();
	       
	       //Verify EWR fee after checking the tax checkbox
	       verifyEWRFeeAndTax();
	       
	       fnCloseTest();     		   
	  	     
		}
   }
}
