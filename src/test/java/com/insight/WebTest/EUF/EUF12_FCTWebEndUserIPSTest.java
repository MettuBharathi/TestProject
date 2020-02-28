package com.insight.WebTest.EUF;

import java.util.Hashtable;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.EndUserFeaturesLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class EUF12_FCTWebEndUserIPSTest extends EndUserFeaturesLib{

	
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib = new CanadaLib();
	
	    // #############################################################################################################
		// #       Name of the Test         :  EUF12_FCTWebEndUserIPS
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test FCTWebEndUserIPS
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_EUF12(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "EUF12_FCTWebEndUserIPS", TestDataInsight, "End_User");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("EUF12_FCTWebEndUserIPS", TestDataInsight, "End_User", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("FCTWebEndUserIPS");

							/***********  Shop >>> Federal government *************/
							searchLib.clickOnSecondaryDDAndSelectListitem(data.get("Header"), data.get("List_Option1"));  // Shop >>> Federal government
		                    
							// Select A Department 
		                    selectDropDown(data.get("DD_Name1"));  // Select A Department
		                    VerifyListElements(data.get("DD_Name1")); 
		                    selectFromDDListAndVerify(data.get("DD_Name1"), data.get("DD_List1"));  // Prime contractors
		                    
		                    //	Select a Contract
		                    selectDropDown(data.get("DD_Name2"));  
		                    VerifyListElements(data.get("DD_Name2")); 
		                    
		                    /*********** Shop >>> State & local government  *************/
		                    searchLib.clickOnSecondaryDDAndSelectListitem(data.get("Header"), data.get("List_Option2"));  // Shop >>> State & local government
		                    selectDropDown(data.get("DD_Name3"));  // State / Local  -- DD
		                    VerifyListElements(data.get("DD_Name3")); 
		                    selectFromDDListAndVerify(data.get("DD_Name3"), data.get("DD_List3"));  // State Contracts
		                    
		                   //  Select  a State contract
		                    selectDropDown(data.get("DD_Name4"));  // State -- DD
		                    VerifyListElements(data.get("DD_Name4")); 
		                    selectFromDDListAndVerify(data.get("DD_Name4"), data.get("DD_List4"));   // select - Alabama
							
		                    //	Select a Contract
		                    selectDropDown(data.get("DD_Name2"));  
		                    VerifyListElements(data.get("DD_Name2")); 
		                    
		                    searchLib.clickOnSecondaryDDAndSelectListitem(data.get("Header"), data.get("List_Option2"));  // Shop >>> State & local government
		                    selectDropDown(data.get("DD_Name3"));  // State / Local  -- DD
		                    selectFromDDListAndVerify(data.get("DD_Name3"), data.get("DD_List5"));  // Local  Contracts
		                    
		                    // Local contracts
		                    selectDropDown(data.get("DD_Name4"));  // State -- DD
		                    VerifyListElements(data.get("DD_Name4")); 
		                    selectFromDDListAndVerify(data.get("DD_Name4"), data.get("DD_List4"));   // select - Alabama
		                    
		                    //	Select a Contract
		                    selectDropDown(data.get("DD_Name2"));  
		                    VerifyListElements(data.get("DD_Name2")); 
		                    
		                    /***********  Education*************/
		                    searchLib.clickOnSecondaryDDAndSelectListitem(data.get("Header"), data.get("List_Option3"));  // Shop >>> Education
		                    selectDropDown(data.get("DD_Name5"));  //  K-12 / Higher Education
		                    selectFromDDListAndVerify(data.get("DD_Name5"), data.get("DD_List6"));  // K-12 Education 
		                    
		                   //	Select  a State link
		                    selectDropDown(data.get("DD_Name4"));  // State -- DD
		                    VerifyListElements(data.get("DD_Name4")); 
		                    selectFromDDListAndVerify(data.get("DD_Name4"), data.get("DD_List4"));   // select - Alabama
		                    
		                    //	Select a Contract
		                    selectDropDown(data.get("DD_Name2"));  
		                    VerifyListElements(data.get("DD_Name2")); 
		                    
		                    /***********  Education >> Higher Education *************/
		                    searchLib.clickOnSecondaryDDAndSelectListitem(data.get("Header"), data.get("List_Option3"));  // Shop >>> Education
		                    selectDropDown(data.get("DD_Name5"));  //  K-12 / Higher Education 
		                    selectFromDDListAndVerify(data.get("DD_Name5"), data.get("DD_List7"));  // Higher Education
		                    
		                   //	Select  a State link
		                    selectDropDown(data.get("DD_Name4"));  // State -- DD
		                    VerifyListElements(data.get("DD_Name4")); 
		                    selectFromDDListAndVerify(data.get("DD_Name4"), data.get("DD_List4"));   // select - Alabama
		                    
		                    //	Select a Contract
		                    selectDropDown(data.get("DD_Name2"));  
		                    VerifyListElements(data.get("DD_Name2")); 
							
							
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
					ReportStatus.fnUpdateResultStatus("FCTWebEndUserIPS", "TC_EUF12", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
	            finally {
	            	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("FCTWebEndUserIPS", "TC_EUF12", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}
		
}
