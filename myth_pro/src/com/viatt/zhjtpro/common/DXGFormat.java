/*
 * Description	:	user information factory class
 * Author		:	SuHongBo
 * Create Date	:	2005.03.10
 */

package com.viatt.zhjtpro.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Date;

public class DXGFormat
{
	public static String format(Timestamp ts)
	{
		if(ts == null)
		{
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String str = sdf.format( ts );
		int index = str.lastIndexOf("00:00:00");
		if(index != -1)
        {
    		str = str.substring(0, index-1);
    		return str;
    	}
    	index = str.lastIndexOf("12:00:00");
    	if(index != -1)
    	{
    		str = str.substring(0, index-1);
    		return str;
    	}
		return str;
	}

	public static String format(Date dt)
	{
		if(dt == null)
		{
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format( dt );
		return str;
	}

	
	public static String format1(Timestamp ts)
	{
		if(ts == null)
		{
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format( ts );
		
		return str;
	}

	public static String format(double d)
	{
		DecimalFormat df = new DecimalFormat("#,##0.00");
		String str = df.format( d );
		return str;
	}
	
	public static String format1(double d)
	{
		DecimalFormat df = new DecimalFormat("###0.00");
		String str = df.format( d );
		return str;
	}
	
	public static String format(float f)
	{
		DecimalFormat df = new DecimalFormat("#0.0000");
		String str = df.format( new Float(f) );
		return str;
	}
}