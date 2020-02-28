package com.insight.SmartTest.Scripts.IPS;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class ORD01_ZFRTHoldTest extends HomeLib {
	loginLib loginlib = new loginLib();

	// #############################################################################################################
	// # Name of the Test : ORD01_ZFRTHold
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
	public void ORD01_ZFRTHold(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ORD01_ZFRTHold", TestData_Smart, "Create_Order");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("ORD01_ZFRTHold", TestData_Smart,
					"Create_Order", intCounter);
			// Test Steps execution
			try {
				fnOpenTest();
				navigateToApplication("SMART");
				loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
				clickCreateQuoteButton();
				enterSoldTo(data.get("SoldToAcct"));
				Addmaterail(data.get("Material"));
				clickMaterail();
				Thread.sleep(3000);
				AddQuantity(data.get("Quantity"),data.get("LineItem"));
				clickMaterail();
				selectPaymentTerms(data.get("PaymentTerms"));
				typePONumber(data.get("PONumber"));
				SearchButtonPONumber();
				clickOKinPopUp();
				clickAdvancedHeader();
				clickAdvancedHeaderTab(data.get("Tab1"));
				selectCarrier(data.get("Carrier"));
				selectShippingOptions(data.get("Shipping_Conditions"));//10 Next day
				clickSideBarSmart();
				clickonSaveasOrder();
				clickSaveorderwithoutAttachment();
				getOrderNum();
				clickCancel();
				Thread.sleep(3000);
				ClickOnDisplayMode();
				clickAdvancedHeaderTab(data.get("Tab2"));//Hold
				verifyHoldsText(data.get("Holdtext"));
				rejectitem(data.get("LineItem"));
				clickYesToRejectItem();
				Thread.sleep(3000);
				selectReasonToRejectItem(data.get("reason"));
				clickYesToRejectItem();
				VerifycolourAfterRejectingItem(data.get("colour"));
				clickSideBarSmart();
				Thread.sleep(3000);
				clickClosthedocument(data.get("Doctype"));
				clickYesButtontocloseDocument();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				fnCloseTest();
			}
		}
	}

}
