package com.supercell.elmm.validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.supercell.elmm.entity.Merchant;
import com.supercell.elmm.util.RegularUtil;

public class MerchantValidator {
	private final static String NAME_NOT_NULL_MSG="手机号不能为空";
	private final static String NAME_FORMAT_ERROR_MSG="sorry,手机号格式应为11位，以1开头...";
	private final static String NAME_FORMAT="^1\\d{10}$";
	
	private final static String PASSWORD_NOT_NULL_MSG="密码不能为空";
	private final static String PASSWORD_FORMAT_ERROR_MSG="sorry，密码只能输入5-20个字母、数字、下划线";
	private final static String PASSWORD_FORMAT = "^(\\w){5,20}$";
	
	private final static String NOT_NULL_MSG="sorry，以上所有选项都不能为空";
	public static List<String> validatePhoneNumber(String s) {
		List<String> tips = new ArrayList<String>();
		if (s==null || "".equals(s)) {
			String errorTip = NAME_NOT_NULL_MSG;
			tips.add(errorTip);
		}
		if (!RegularUtil.isMatch(NAME_FORMAT, s)) {
			String errorTip = NAME_FORMAT_ERROR_MSG;
			tips.add(errorTip);
		}
		return tips;
	}
	
	public static List<String> validatePassword(String s) {
		List<String> tips = new ArrayList<String>();
		if (s==null||"".equals(s)) {
			String errorTip = PASSWORD_NOT_NULL_MSG;
			tips.add(errorTip);
		}
		if (!RegularUtil.isMatch(PASSWORD_FORMAT, s)) {
			String errorTip = PASSWORD_FORMAT_ERROR_MSG;
			tips.add(errorTip);
		}
		return tips;
	}
	
	public static List<String> validateNot_Null(String s) {
		List<String> tips = new ArrayList<String>();
		if (s==null || "".equals(s.trim())) {
			String errorTip = NOT_NULL_MSG;
			tips.add(errorTip);
		}
		return tips;
	}
	
	public static Set<String> validate(Merchant merchant){
		Set<String> errorTipsList = new HashSet<>();
		errorTipsList.addAll(validatePhoneNumber(merchant.getPhoneNumber()));
		errorTipsList.addAll(validatePassword(merchant.getPassword()));
		errorTipsList.addAll(validateNot_Null(merchant.getAddress()));
		errorTipsList.addAll(validateNot_Null(merchant.getShopName()));
		return errorTipsList;
	}
}
