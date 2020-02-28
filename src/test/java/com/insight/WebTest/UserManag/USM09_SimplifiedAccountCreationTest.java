package com.insight.WebTest.UserManag;

import java.util.Hashtable;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.LineLevelInfoLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.Lib.UserManagementLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class USM09_SimplifiedAccountCreationTest extends UserManagementLib{

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib = new CanadaLib();
	UserManagementLib userMgt=new UserManagementLib();
	LineLevelInfoLib lnlLib=new LineLevelInfoLib();
	
	        // #############################################################################################################
			// #       Name of the Test         :  USM09_SimplifiedAccountCreationTest
			// #       Migration Author         :  Cigniti Technologies
			// #
			// #       Date of Migration        : October 2019
			// #       DESCRIPTION              : This Test is used to Test FCTWebCreateCEPPuser
			// #       Parameters               : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################
	
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_USM09(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "USM09_SimplifiedAccountCreation", TestDataInsight, "UserManagement");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("USM09_SimplifiedAccountCreation", TestDataInsight, "UserManagement", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SimplifiedAccountCreation");
                
					// Create a new account 
					canadaLib.clickCreateAccountOnCanadaSearchPage();
					cmtLib.handleWelcomeToInsightBetaPopUp();
					// verify that Create an account - Login details has below fields
					verifyCreateAccountFields();
					// click on next
					canadaLib.clickOnNext();
					//	Please enter valid Email
					verifyEmailErrorMsg();
					//	Verify Please enter valid Email
					verifyValidPasswordMessage();
					
					///	Password and Confirm Password must match
					lnlLib.enterEmailInCreateAccount();
					enterpasswordInCreateAccount(data.get("Password1"),data.get("Confirm_Password1"));
					// click on next
					canadaLib.clickOnNext();
					
					///	Minimum of 8 characters in length.  Combination of letters and numbers  : Test_Automation@test.com
					enterEmailInCreateAccount(data.get("Email1"));
					enterpasswordInCreateAccount(data.get("Password2"),data.get("Confirm_Password2"));
					// click on next
					canadaLib.clickOnNext();
                    //	Verify Please enter valid Email
					verifyValidPasswordMessage();
					
					// 	An account with this email already exists on Insight.com  : tu_iusadmin@mailinator.com
					enterEmailInCreateAccount(data.get("Email2"));
					// click on next
					canadaLib.clickOnNext();
					verifyEmailAlreadyExistsErrorMsg();
					clickCancel();
					
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("SimplifiedAccountCreation", "TC_USM09", ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("SimplifiedAccountCreation", "TC_USM09", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

	
}
