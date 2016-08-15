package com.supercell.elmm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegularUtil {
	public static boolean isMatch(String regex,String input) {
		if (input!=null&&!"".equals(input)) {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(input);
			if (matcher.matches()) {
				return true;
			}
		}
		return false;
	}
}
