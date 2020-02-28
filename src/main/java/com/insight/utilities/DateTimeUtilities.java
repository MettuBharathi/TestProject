package com.insight.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtilities {

	
    /**
     * param :: TimeFormat,TimeZone
     * return ::date
     * throws :: throwable
     * methodName :: getCurrentSystemTime
     * description :: Getting system current time
     * date ::
     * author ::
     */

   	public static String getCurrentSystemTime(String format,String timeZone){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		sdf.format(new Date()).toString();
		return sdf.format(new Date());
	}
   	
   	public static String convertTime(String time,String inputTimeFormat,String ExpectedTimeformat) throws Exception {
        SimpleDateFormat displayFormat = new SimpleDateFormat(ExpectedTimeformat);
        SimpleDateFormat parseFormat = new SimpleDateFormat(inputTimeFormat);
        Date date = parseFormat.parse(time);
        System.out.println(parseFormat.format(date) + " = " + displayFormat.format(date));
		return displayFormat.format(date);
    }
   	
   	public static String getTimeFromNow(int timeFromNow,String format){
   		Calendar now = Calendar.getInstance();
   		now.add(Calendar.MINUTE, timeFromNow);
   		SimpleDateFormat df = new SimpleDateFormat(format);
   		return df.format(now.getTime());
   	}
   	
	public static void main(String[] args) throws Exception {
		String format="h:mm a";
		try {
			System.out.println(convertTime("4:21 PM",format,"HH:mm"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getTimeFromNow(15, format);
		String date="05/15/2018";
		System.out.println(date.substring(0,date.lastIndexOf("/")));
	}
	public static String getDateTimeFromNow(String type,int dateTimeFromNow,String format){
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		switch(type.toLowerCase()){
		case "year":
			cal.add(Calendar.YEAR, dateTimeFromNow);
			break;
		case "month":
			cal.add(Calendar.MONTH, dateTimeFromNow);
			break;
		case "date":
			cal.add(Calendar.DATE, dateTimeFromNow);
			break;
		}
		
		Date desiredDateTime = cal.getTime();
		DateFormat newDate = new SimpleDateFormat(format);
		return newDate.format(desiredDateTime);
	
}
}
