package com.insight.accelerators;

import java.io.IOException;

import com.insight.utilities.TestUtil;
import com.insight.utilities.Xls_Reader;

public class ReportControl {

	public static String PackageName = null;
	public static boolean blnJoin=false;  
	public static String strPlatformName=null;
	public static String strCurrentExecution=null;
	public static String strAutoStart=null;
	public static String strPreviousExecution=null;
	public static int intRowCount=0;
	public static String MethodName=null;
	public static String callID=null;
	public static String callIdDate = null;
	
	
	public static void fnReportDefault()
	{
		blnJoin=false;
	}
	
	public static void fnEnableJoin()
	{
		blnJoin=true;
	}
	
	public static void fnDisableJoin()
	{
		blnJoin=false;
	}
	
	public static void fnSwitchToMobile() throws Throwable
	{
		strAutoStart="mobile";
		if(blnJoin==true)
		{
			TestEngineWeb.reporter.SuccessReport("====== Context Switch ======", "===== Context Switch is Change to Mobile ====","");
			
		}
	}
	
	public static void fnSwitchToWeb() throws Throwable
	{
		strAutoStart="web";
		if(blnJoin==true)
		{
			TestEngineWeb.reporter.SuccessReport("====== Context Switch ======", "===== Context Switch is Change to Web ====","");
		}
	}
	
	public static void fnIntilizeControl(String strControl)
	{
		strAutoStart=strControl;
	}
	
	public static void fnNextTestJoin(boolean blnNextTest)
	{
		if(blnNextTest==true)
		{
			ReportControl.blnJoin=true;
		}
		else
		{
			ReportControl.blnJoin=false;
		}
	}
	
	public static int fnGetEndRowCunt(String RowCountSupply,String testCase, Xls_Reader xls,String sheetName)
	{
		int intEndRow=0;
		if(RowCountSupply==null)
		{
			intEndRow=TestUtil.getTotalRow(testCase, xls, sheetName);
		}
		else
		{
			intEndRow=Integer.parseInt(RowCountSupply);
		}
		return intEndRow;
	}
}
