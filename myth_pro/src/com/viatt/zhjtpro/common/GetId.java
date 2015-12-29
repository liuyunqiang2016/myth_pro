package com.viatt.zhjtpro.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class GetId 
{
	private GetId()
	{
	}

	public static String randomID()
	{
		String str = "";
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = new Date();
			Date date2 = formatter.parse("1900-01-01");
			long i = date1.getTime() - date2.getTime();
			str = String.valueOf(i);
		} catch (Exception e)
		{
			str = java.util.UUID.randomUUID().toString();
		}
		return str;
	}

	public static String GetId2() throws Exception {
		return java.util.UUID.randomUUID().toString();
	}

	public static String GetDate() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		return formatter.format(date1);
	}

	public static String GetDate2() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date1 = new Date();
		return formatter.format(date1);
	}

	public static String GetDateAndTime() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date1 = new Date();
		return formatter.format(date1);
	}
}
