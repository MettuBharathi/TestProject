package com.insight.WebTest.ProductInfo;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class PIP05_ProductImageTest extends ProductDisplayInfoLib{

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();

	// #############################################################################################################
	// #     Name of the Test             : PIP05_ProductImage
	// #     Migration Author             : Cigniti Technologies
	// #
	// #     Date of Migration            : October 2019
	// #     DESCRIPTION                  : Purpose of this test method is to test Product Image
	// #     Parameters                   : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_PIP05(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PIP05_ProductImage", TestData, "Web_Product_Info");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("PIP05_ProductImage", TestData, "Web_Product_Info", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("INSIGHT WEB Testcase" + " From Iteration " + StartRow + " to " + EndRow);
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
								"");

						/*reporter.SuccessReport("Iteration Number : ", "**************Iteration Number::  " + intCounter
								+ " To validate:: Favorites_Logged In User_Verify user is able to Add products to favorites_Remove Favorites in PLP/PDP **************");*/


						// search for first product
						searchLib.searchInHomePage(data.get("SearchText1"));
						String imageSrc=verifyProductImage();
						cartLib.selectFirstProductDisplay();
						verifyFrontImageInProductDetailsPage();
						backToResultsProductDetailsPage();

						// search for second product
						searchLib.searchInHomePage(data.get("SearchText2"));
						String imageSrc2=verifyProductImage();
						cartLib.selectFirstProductDisplay();
						verifyLeftAngleImageInProductDetailsPage();
						backToResultsProductDetailsPage();
						cartLib.selectFirstProductDisplay();

						// Verify image in recently viewed products
						prodDetailsLib.Verifyrecentlyviwedproductslabel();
						scrollToBottom();
						verifyRecentlyViewedProductsImage(imageSrc);
						verifyRecentlyViewedProductsImage(imageSrc2);

						//fnCloseTest();
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("PIP", "PIP05", ReportStatus.strMethodName, intCounter, browser);
					fnCloseTest();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("PIP", "PIP05", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
			/*finally {
				Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
			}*/

			ReportControl.fnNextTestJoin(nextTestJoin);
		}

}


