package biz.common.tools;

import biz.common.BGMLogger;

public class DataTransform
{
	public static String Trim(Object obj)
	{
		if (obj != null)
		{
			return obj.toString().trim();
		}
		return "";
	}

	public static int TransToInt(Object obj)
	{
		try
		{
			if (obj != null && !obj.toString().equals(""))
			{
				return Integer.parseInt(obj.toString());
			} else
			{
				return 0;
			}
		} catch (Exception ex)
		{
			BGMLogger.LogError("data transform is fail:" + ex.getMessage());
			return 0;
		}
	}
	public static double TransToDouble(Object obj)
	{
		try
		{
			if (obj != null && !obj.toString().equals(""))
			{
				return Double.parseDouble(obj.toString());
			} else
			{
				return 0.00;
			}
		} catch (Exception ex)
		{
			BGMLogger.LogError("data transform is fail:" + ex.getMessage());
			return 0.00;
		}
	}
	public static long TransToLong(Object obj)
	{
		try
		{
			if (obj != null && !obj.toString().equals(""))
			{
				return Long.parseLong(obj.toString());
			} else
			{
				return 0l;
			}
		} catch (Exception ex)
		{
			BGMLogger.LogError("data transform is fail:" + ex.getMessage());
			return 0l;
		}
	}
	public static int TransToInt(String obj)
	{
		try
		{
			if (obj != null && !obj.toString().equals(""))
			{
				return Integer.parseInt(obj.toString());
			} else
			{
				return 0;
			}
		} catch (Exception ex)
		{
			BGMLogger.LogError("data transform is fail:" + ex.getMessage());
			return 0;
		}
	}

	public static boolean TransToBool(Object obj)
	{
		try
		{
			if (obj != null && !obj.toString().equals(""))
			{
				return Boolean.parseBoolean(obj.toString());
			} else
			{
				return false;
			}
			
		} catch (Exception ex)
		{
			BGMLogger.LogError("data transform is fail:" + ex.getMessage());
			return false;
		}
	}

	public static String ChageChartset(String strChg, String strCs1,
			String strCs2)
	{
		String temp = "";
		try
		{
			String temp_p = strChg.trim();
			if (temp_p.equals(""))
			{
				return temp;
			}
			temp_p = temp_p.trim();
			temp = new String(temp_p.getBytes(strCs1), strCs2);
		} catch (Exception e)
		{
			// java.net.URLDecoder.decode("");
			BGMLogger.LogError("ChageChartset:" + e.getMessage());
		}
		return temp;
	}
	
	public static Object GetValue(String strType,Object objVlaue)
	{
		Object obj=objVlaue;
		String type = Trim(strType);
		if(type.equals("Integer"))
		{
			obj = new Integer(TransToInt(obj));
		}
		else if(type.equals("Double"))
		{
			obj = new Double(TransToDouble(obj));
		}
		else if(type.equals("Long"))
		{
			obj = new Long(TransToLong(obj));
		}
		else if(type.equals("Boolean"))
		{
			obj = new Boolean(TransToBool(obj));
		}
		else
		{
			obj = obj.toString();
		}
		return obj;
	}
	
	public static void main(String argv[])
	{
		int i=11111;
		System.out.print(DataTransform.GetValue("Integer", i));
	}
}
