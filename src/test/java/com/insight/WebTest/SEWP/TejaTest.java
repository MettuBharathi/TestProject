package com.insight.WebTest.SEWP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.UserManagementLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import com.insight.utilities.Xls_Reader;

public class TejaTest extends UserManagementLib {

	public static By REGISTRATION_NUMBER = By.xpath("//input[@id='ctl00_OnlineContent_txtRegnNo']");
	public static By GET_TETAILS_BTN = By.xpath("//input[@id='ctl00_OnlineContent_BtnGetDetails']");
	public static By INSURANCE_VALID_UPTO = By.xpath("//td[@id='ctl00_OnlineContent_tdICVtdt']");
	public static By CLEAR_BUTTON=By.xpath("//input[@id='ctl00_OnlineContent_BtnClear']");


	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TEJA_TEST(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
	int counter = 0;

	int intStartRow = StartRow;
	int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "Insurance_test", TestData, "Insurance");
	fnOpenTest();

	String outputFile="C:\\outputInsurance.xlsx";
	File file = new File(outputFile);

	if(file.exists()){
	FileInputStream fis = new FileInputStream(file);
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet sh = wb.getSheetAt(0);
	String validDate =null;
	for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

	counter = intCounter;
	ReportStatus.fnDefaultReportStatus();
	ReportControl.intRowCount = intCounter;
	Hashtable<String, String> data = TestUtil.getDataByRowNo("Insurance_test", TestData, "Insurance",intCounter);

	String reg_Number = data.get("REG_NO");
	type(REGISTRATION_NUMBER, reg_Number, "register number", driver);
	click(GET_TETAILS_BTN, "get details button");
	validDate = getText(INSURANCE_VALID_UPTO, "Insurance valid upto");
	reporter.SuccessReport("Valid date", "Valid date", validDate);
	if(validDate==null || validDate.isEmpty()){
	// do nothing
	sh.createRow(intCounter).createCell(0).setCellValue(reg_Number);
	click(CLEAR_BUTTON, "Clear button");
	//click(CLEAR_BUTTON, "Clear button");
	}else{
	sh.createRow(intCounter).createCell(0).setCellValue(reg_Number);
	//sh.getRow(intCounter).createCell(1).setCellValue(reg_Number);
	FileOutputStream fos = new FileOutputStream(file);
	wb.write(fos);
	Thread.sleep(3000);
	click(CLEAR_BUTTON, "Clear button");
	}



	       }
	}else{

	XSSFWorkbook wb = new XSSFWorkbook();
	XSSFSheet sh = wb.createSheet("First Sheet");
	 
	//  Creating cell and entering values
	sh.createRow(0).createCell(0).setCellValue("Register_Number");
	sh.getRow(0).createCell(1).setCellValue("Date");
	FileOutputStream fos = new FileOutputStream(file);
	wb.write(fos);


	}
	fnCloseTest();
	}
}