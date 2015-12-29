package biz.common.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTransform
{
	public static String GetCurrDate(String strFormat)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
		Date date1 = new Date();
		return formatter.format(date1);
	}
}
