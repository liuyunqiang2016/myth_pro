package com.viatt.zhjtpro.common;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 分页栏
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: 
 * </p>
 * 
 * @author 余振坤 2005年9月15日
 * @version 1.0
 */ 
public class PageRoll
{
	private StringBuffer strPrint;

	private String strParams = "";

	private String currentUrl = "";

	private int total;

	private int totalPage;

	private int pageSize;

	private int currPage;

	private boolean hasPrev = false;

	private boolean hasNext = false;

	private int prevPageNumber = 0;

	private int nextPageNumber = 0;

	private HttpServletRequest request;

	public PageRoll(HttpServletRequest request, int pageSize, int currPage,
			int iTotal)
	{
		total = iTotal;
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.request = request;
		currentUrl = request.getRequestURL().toString();
		Calculate();

		strParams = GetParamsFromCurrentUrl();

		strPrint = new StringBuffer();

	}

	public String Show(boolean allRecord, boolean pageInfo, boolean pageNav,
			boolean jumpPage) throws IOException
	{
		strPrint.append("");
		if (allRecord)
		{
			strPrint.append(ShowTotalRecord());
		}
		if (pageInfo)
		{
			strPrint.append(ShowPageInfo());
		}
		if (pageNav)
		{
			strPrint.append(ShowPageNav());
		}
		if (jumpPage)
		{
			strPrint.append("");
//			strPrint.append(ShowJumpPage());
		}
		strPrint.append("");
		return strPrint.toString();
	}

	private String ShowTotalRecord() throws IOException
	{
		return "<span>显示"+(pageSize*(currPage-1)+1)+"-"+(pageSize*currPage)+"条,共" + total + "条</span>";
	}

	private String ShowPageInfo() throws IOException
	{
//		return "第" + currPage + "/" + totalPage + "页&nbsp";
		return "";
	}

	private void Calculate()
	{
		totalPage = (total + pageSize - 1) / pageSize;
		if (currPage > totalPage)
		{
			currPage = totalPage;
		}
		if (currPage <= 1)
		{
			hasPrev = false;
			prevPageNumber = 1;
		} else
		{
			hasPrev = true;
			prevPageNumber = currPage - 1;
		}
		if (currPage >= totalPage)
		{
			hasNext = false;
			nextPageNumber = totalPage;
			return;
		} else
		{
			hasNext = true;
			nextPageNumber = currPage + 1;
		}
	}

	private String ShowPageNav() throws IOException
	{
		StringBuffer sb = new StringBuffer();
		if (totalPage > 1)
		{
			sb.append("<input type='button' class='first btn' onclick=\"document.location.href=\'"+
				currentUrl+ "?page=1" + strParams
				+"\'\"/>");
		} else
		{
			sb.append("<input type='button' class='first btn' />&nbsp;");
		}
		if (hasPrev)
		{
			sb.append("<input type='button' class='prev btn' onclick=\"document.location.href=\'"+
				currentUrl+ "?page=" + prevPageNumber + strParams
				+"\'\"/>");
		} else
		{
			sb.append("<input type='button' class='prev btn' />&nbsp;");
		}
		sb.append("第<input type='text' name='page' class='text' value='"+currPage+"' />页<label>共"+totalPage+"页</label>");
		
		if (hasNext)
		{
			sb.append("<input type='button' class='next btn' onclick=\"document.location.href=\'"+
				currentUrl+ "?page=" + nextPageNumber + strParams
				+"\'\"/>");
		} else
		{
			sb.append("<input type='button' class='next btn' />&nbsp;");
		}
		if (totalPage > 1)
		{
			sb.append("<input type='button' class='last btn' onclick=\"document.location.href=\'"+
				currentUrl+ "?page=" + totalPage + strParams
				+"\'\"/>");
		} else
		{
			sb.append("<input type='button' class='last btn' />&nbsp;");
		}
		
		sb.append("<input type='button' class='refresh btn' onclick='goToPage()'/><script language=\'javascript\'>");
		sb.append("function goToPage(){");
		sb.append("value=document.all.page.value;");
		sb
				.append("if(value.indexOf(\".\")==-1 && value.indexOf(\"-\")==-1 && value!==\"\" && !isNaN(value) && value>0 && value<"
						+ (totalPage + 1) + "){");
		sb.append("location.assign(\'" + currentUrl + "?page=\'+value+\'"
				+ strParams + "\')");
		sb.append("}");
		sb.append("}");
		sb.append("</script>");
		return sb.toString();
	}

	private String ShowJumpPage() throws IOException
	{
		StringBuffer sb = new StringBuffer();
		sb.append("转到第<input type='text' name='page' size='4'>页<input type=button value='GO' onclick='goToPage()'>");
		sb.append("<script language=\'javascript\'>");
		sb.append("function goToPage(){");
		sb.append("value=document.all.page.value;");
		sb
				.append("if(value.indexOf(\".\")==-1 && value.indexOf(\"-\")==-1 && value!==\"\" && !isNaN(value) && value>0 && value<"
						+ (totalPage + 1) + "){");
		sb.append("location.assign(\'" + currentUrl + "?page=\'+value+\'"
				+ strParams + "\')");
		sb.append("}");
		sb.append("}");
		sb.append("</script>");
		return sb.toString();
	}

	private String GetParamsFromCurrentUrl()
	{
		String str1 = "";
		String paraValue = "";
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements())
		{
			String paraName = (String) enu.nextElement();
			paraValue = request.getParameter(paraName);
			if (paraName.equals("page") == false)
			{
				str1 = str1 + "&" + paraName + "=" + paraValue;
			}
		}
		return str1;
	}

}
