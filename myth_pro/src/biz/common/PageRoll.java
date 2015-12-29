package biz.common;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: ��ҳ��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: 
 * </p>
 * 
 * @author 
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
		strPrint.append("<table border=0> <tr><td>");
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
			strPrint.append(ShowJumpPage());
		}
		strPrint.append("</td></tr></table>");
		return strPrint.toString();
	}

	private String ShowTotalRecord() throws IOException
	{
		return "��" + total + "����¼&nbsp;";
	}

	private String ShowPageInfo() throws IOException
	{
		return "��" + currPage + "/" + totalPage + "ҳ&nbsp";
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
			sb.append("<a href=\'" + currentUrl + "?page=1" + strParams
					+ "\'>��ҳ</a>&nbsp;");
		} else
		{
			sb.append("��ҳ&nbsp;");
		}
		if (hasPrev)
		{
			sb.append("<a href=\'" + currentUrl + "?page=" + prevPageNumber
					+ strParams + "\'>��ҳ</a>&nbsp;");
		} else
		{
			sb.append("��ҳ&nbsp;");
		}

		if (hasNext)
		{
			sb.append("<a href=\'" + currentUrl + "?page=" + nextPageNumber
					+ strParams + "\'>��ҳ</a>&nbsp;");
		} else
		{
			sb.append("��ҳ&nbsp;");
		}
		if (totalPage > 1)
		{
			sb.append("<a href=\'" + currentUrl + "?page=" + totalPage
					+ strParams + "\'>βҳ</a>&nbsp;");
		} else
		{
			sb.append("βҳ&nbsp;");
		}
		return sb.toString();
	}

	private String ShowJumpPage() throws IOException
	{
		StringBuffer sb = new StringBuffer();
		sb
				.append("ת����<input type='text' name='page' size='4'>ҳ<input type=button value='GO' onclick='goToPage()'>");
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
