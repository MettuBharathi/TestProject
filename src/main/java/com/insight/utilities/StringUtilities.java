package com.insight.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtilities {

	
	public static boolean isDigitInString(String input){
		Pattern p = Pattern.compile("([0-9])");
		Matcher m = p.matcher(input);
		if(m.find()){
			System.out.println(input+" has digit "+m.find());
			return true;
		}else{
			return false; 
		}
	}
   	
	public static void main(String[] args) {
		isDigitInString("ETA: 12:12");
	}

}
