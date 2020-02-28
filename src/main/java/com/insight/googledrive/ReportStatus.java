package com.insight.googledrive;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.testng.Reporter;

import com.insight.accelerators.TestEngineWeb;

public class ReportStatus extends TestEngineWeb {
	public static boolean blnStatus=true;
	public static String strScriptName="";
	public static String strMethodName="";
	public static String spreadsheetId="1S8XS3qn1XKSs7uHemi0KrestdCcrO2KhFHi1ZyZlD1A";
	public static void fnDefaultReportStatus()
	{
		blnStatus=true;
	}
	
	public static void fnUpdateResultStatus(String strModuleName,String strTestCaseID,String strScriptName,int Iteration,String strBrowserNameIn) throws Throwable
	{
		if(!blnStatus){
			reporter.failureReport("Test :"+strModuleName+" - "+strTestCaseID+"-"+strScriptName+"strScriptName","Execution Failed...!"+gErrorMessage,"");
			gTestStatus=blnStatus;
		}
		fnDefaultReportStatus();
		
}
	
	public static void fnUpdateResultStatusEE(String strModuleName,String strTestCaseID,String strScriptName,String Context,String strMethodName,int Iteration,String strBrowserName) throws IOException, InterruptedException
	{
		
}
}
