package com.insight.SmartTest.Scripts.IUS;

import static org.testng.Assert.assertEquals;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT11_TaxPartnerOverwriteAndCopyToQuote extends HomeLib {
	loginLib loginlib=new loginLib();
	// #############################################################################################################
			// # Name of the Test : CQT11_TaxPartnerOverwriteAndCopyToQuote
			// # Migration Author : Cigniti Technologies
			// #
			// # Date of Migration : OCT 2019
			// # DESCRIPTION : Purpose of this test method is to verify the compare
			// functionality in the products display page.
			// # Parameters : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################

			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void CQT11(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
				int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT11_TaxPartnerOverwriteAndCopyToQuote", TestData_Smart, "Create_Quote");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT11_TaxPartnerOverwriteAndCopyToQuote", TestData_Smart, "Create_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("TaxPartnerOverwriteAndCopyToQuote");
						LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToAcct"));
					/*
					 * navigateToApplication("SMART");
					 * loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"))
					 * ; clickOnCreateQuoteLink(); enterSoldToValue(data.get("SoldToValue"));
					 * clickOnSoldToSearchIcon();//10933584
					 */						AddMaterialOnLineItem(data.get("MaterialID"));	
						clickUpdateCosting();
						//clickAdvancedHeader();			
						clickOnSideheaderTabs(data.get("MainTab"));
						clickAdvancedHeaderTab(data.get("Tab"));//Partners
						clickTaxExemptSearchIcon();
						if(PartnerIDverification()){
							int IDinPopup = verifyandgetTextPartnerID();
							int IDintextbox = partnerIDcomparision();
							if(IDinPopup== IDintextbox){
								 reporter.SuccessReport("Tax Partner ID Validation - Comparison", "The 'Tax Exempt Partner' ID in the Advanced Header section matches the Partner ID in the Tax Exempt in the popup. Tax Partner ID: ","");
							} else {
								reporter.failureReport("Tax Partner ID Validation - Comparison", "The 'Tax Exempt Partner' ID in the Advanced Header section DOES NOT match the Partner ID in the Tax Exempt in the popup.", "",driver);
							}
							
							clickUpdateCosting();
							taxValidationequalstozerocheck();
							removeTaxExempt();
							clickUpdateCosting();
							taxValidationNotequalstozerocheck();
							clickTaxExemptSearchIcon();
							if(verifyEditAddresspopupWindow()){
								verifyTaxJurisdictionCode(data.get("TaxJurisCode"));
								editAddressandZipcode(data.get("address"),data.get("Zipcode"));
								clickSideBarSmart();
								clickonSaveasQuote();
								driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
								enterCancelButtonInPoupHdr();
								getSaveQuoteNumber();
								clickonExpandedMenuName(data.get("Subtab"),data.get("Tab1"));//Advance Header
								clickonExpandedMenuName(data.get("Subtab"),data.get("Tab2"));//Summary
								clickSalesDocDropdown(data.get("SalesDD"));
								clickonSaveasQuote();
								driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
								enterCancelButtonInPoupHdr();
								String QuoteNum= getSaveQuoteNumber();
								clickSideBarSmart();
								clickClosthedocument(QuoteNum);
								clickYesButtontocloseDocument();
								
							}
							else {
								reporter.failureReport("Verify ship to popup window", "Edit Address popup window is not displayed as expected", "",driver);
							}
							
							
						} else {
							reporter.failureReport("Linked Tax Exempt Partner Popup Not Found", "The 'Tax Exempt Partner' popup was NOT found.", "",driver);
						}						
						
						System.out.println("Test completed");
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
					//ReportStatus.fnUpdateResultStatus("TaxPartnerOverwriteAndCopyToQuote", "CQT_11", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
		        finally {
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("TaxPartnerOverwriteAndCopyToQuote", "CQT_11", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}


		}