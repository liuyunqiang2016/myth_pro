package com.viatt.zhjtpro.common;

import java.math.BigDecimal;

public class StringConver {
	public static String converString(String str, String str1, String str2) {
		String temp = "";
		try {
			String temp_p = str.trim();
			if (temp_p.equals("")) {
				return temp;
			}
			temp_p = temp_p.trim();
			temp = new String(temp_p.getBytes(str1), str2);
		} catch (Exception e) {
			// java.net.URLDecoder.decode("");
		}
		return temp;
	}

	public static String compare(double a, double b) {
		BigDecimal val1 = new BigDecimal(a);
		BigDecimal val2 = new BigDecimal(b);
		String result = "";
		if (val1.compareTo(val2) < 0) {
			result = "0";
		}
		if (val1.compareTo(val2) == 0) {
			result = "1";
		}
		if (val1.compareTo(val2) > 0) {
			result = "2";
		}
		return result;
	}
}
