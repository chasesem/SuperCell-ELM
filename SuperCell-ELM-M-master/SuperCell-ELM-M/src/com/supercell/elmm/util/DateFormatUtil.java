package com.supercell.elmm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
	public static String dateToString(Date input,String outputFormat){
		SimpleDateFormat format = new SimpleDateFormat(outputFormat);
		String output = format.format(input);
		return output;
	}
	
	public static Date stringToDate(String input,String outputFormat){
		SimpleDateFormat format = new SimpleDateFormat(outputFormat);
		Date outputDate = null;
		try {
			outputDate = format.parse(input);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputDate;
	}
}
