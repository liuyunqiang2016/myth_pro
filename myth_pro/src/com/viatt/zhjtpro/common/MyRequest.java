package com.viatt.zhjtpro.common;

import javax.servlet.http.*;     

/**
 *
 * <p>Title: </p>
 * <p>Description:��ȡrequest����Ĺ�����</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author ������ 2005��9��15��
 * @version 1.0
 */

public class MyRequest {
  public MyRequest() {
  }
 
  /**
   * ��ȡ����ֵ
   * @param request ��ǰҳ���request HttpServletRequest
   * @param strParamName ������� String
   * @return int
   */
  public static int GetInt(HttpServletRequest request, String strParamName) {
    try {
      if (request.getParameter(strParamName) != null) {
        int iValue = Integer.parseInt(request.getParameter(strParamName).
                                      toString());
        return iValue;
      }
      else {
        return 0;
      }
    }
    catch (Exception ex) {
      return 0;
    }
  }

  public static String GetPath(HttpServletRequest request, String strUrl) {
    String strContextPath = request.getRequestURL().toString();
    int i = strContextPath.substring(7, strContextPath.length()).indexOf("/", 3);
    String str = request.getContextPath();
    if (str == null || str.trim().length() <= 0) {
      strContextPath = strContextPath.substring(0, i + 7) + "/";
    }
    else {
      strContextPath = strContextPath.substring(0, i + 7) + "" +
          request.getContextPath() + "/";
    }
    return strContextPath + strUrl;
  }

  /**
   * ��ȡ����ֵ
   * @param request ��ǰҳ���request HttpServletRequest
   * @param strParamName ������� String
   * @param iDefault �Ҳ����ò�����߸ò������ʹ���ʱ��Ĭ��ֵ int
   * @return int
   */
  public static int GetInt(HttpServletRequest request, String strParamName,
                           int iDefault) {
    try {
      if (request.getParameter(strParamName) != null) {
        int iValue = Integer.parseInt(request.getParameter(strParamName).
                                      toString());
        return iValue;
      }
      else {
        return iDefault;
      }
    }
    catch (Exception ex) {
      return iDefault;
    }
  }

  public static long GetLong(HttpServletRequest request, String strParamName) {
    try {
      if (request.getParameter(strParamName) != null) {
        long iValue = Long.parseLong(request.getParameter(strParamName).
                                     toString());
        return iValue;
      }
      else {
        return 0;
      }
    }
    catch (Exception ex) {
      return 0;
    }
  }

  public static long GetLong(HttpServletRequest request, String strParamName,
                             long i) {
    try {
      if (request.getParameter(strParamName) != null) {
        long iValue = Long.parseLong(request.getParameter(strParamName).
                                     toString());
        return iValue;
      }
      else {
        return i;
      }
    }
    catch (Exception ex) {
      return i;
    }
  }

  /**
   * ��ȡ�ַ�
   * @param request ��ǰҳ���request HttpServletRequest
   * @param strParamName ������� String
   * @return String
   */
  public static String GetString(HttpServletRequest request, String strParamName) {
    String paramValue = request.getParameter(strParamName);
    if(paramValue != null)
    	return paramValue;
    return "";
  }

  /**
   * ��ȡ�ַ�
   * @param request ��ǰҳ���request HttpServletRequest
   * @param strParamName ������� String
   * @param strDefault Ĭ��ֵ String
   * @return String
   */
  public static String GetString(HttpServletRequest request, String strParamName, String strDefault) {
    String paramValue = request.getParameter(strParamName);
    if(paramValue != null && paramValue.length() != 0)
    	return paramValue;
    return strDefault;
  }

  /**
   * ��ȡURL�в���ֵ��������Ĭ��ֵ��
   * @param request HttpServletRequest
   * @param strParamName String
   * @param strDefault double
   * @return double
   */
  public static double GetDouble(HttpServletRequest request,
                                 String strParamName, double strDefault) {
    double str = 0.00;
    if (request.getParameter(strParamName) != null) {
      str = Double.parseDouble(request.getParameter(strParamName).toString());
    }
    else {
      str = strDefault;
    }
    return str;
  }

  /**
   * ��ȡURL�в���ֵ
   * @param request HttpServletRequest
   * @param strParamName String
   * @return double
   */
  public static double GetDouble(HttpServletRequest request,
                                 String strParamName) {
    double str = 0.00;
    if (request.getParameter(strParamName) != null) {
      str = Double.parseDouble(request.getParameter(strParamName).toString());
    }
    return str;
  }
  /**
   * ��ȡURL�в���ֵ��������Ĭ��ֵ��
   * @param request HttpServletRequest
   * @param strParamName String
   * @param strDefault double
   * @return double
   */
  public static float GetFloat(HttpServletRequest request,
                                 String strParamName, float strDefault) {
    float str = 0.00f;
    if (request.getParameter(strParamName) != null&&!request.getParameter(strParamName).equals("")) {
      str = Float.parseFloat(request.getParameter(strParamName).toString());
    }
    else {
      str = strDefault;
    }
    return str;
  }

  /**
   * ��ȡURL�в���ֵ
   * @param request HttpServletRequest
   * @param strParamName String
   * @return double
   */
  public static float GetFloat(HttpServletRequest request,
                                 String strParamName) {
    float str = 0.00f;
    if (request.getParameter(strParamName) != null&&!request.getParameter(strParamName).equals("")) {
      str = Float.parseFloat(request.getParameter(strParamName).toString());
    }
    return str;
  }
  /**
   * ��ȡ�ַ�
   * @param request ��ǰҳ���request HttpServletRequest
   * @param strParamName ������� String
   * @return String
   */
  public static String[] GetStringE(HttpServletRequest request,
                                    String strParamName) {
    String[] str = new String[] {};
    if (request.getParameter(strParamName) != null) {
      str = request.getParameterValues(strParamName);
    }
    return str;
  }

  public static String getString(String str){
	  return str==null ? "" : str.trim();
  }
  
  
}
