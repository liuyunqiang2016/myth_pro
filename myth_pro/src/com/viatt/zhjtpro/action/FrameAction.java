/**
 * 
 */
package com.viatt.zhjtpro.action;

import java.net.URLDecoder;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.common.BGMLogger;

/**
 * @author wuyingbiao
 * 
 */
public class FrameAction extends BaseAction {
	BGMLogger log = new BGMLogger();

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		// 解析用户的菜单点选，使机构的树形菜单能够动态的改变链接地址
		String qryStr = request.getQueryString();
		StringBuffer buf = new StringBuffer();
		String title = "";// title的默认值
		qryStr = URLDecoder.decode(qryStr);

		// if (qryStr.startsWith("action=")) {
		// qryStr = qryStr.substring(7);
		// String[] array = qryStr.split("&");
		// buf.append(array[0] + "?");
		// for (int i = 1; i < array.length; i++){
		// if(array[i].startsWith("title")){
		// title = array[i].substring(6);
		// continue;
		// }
		// buf.append(array[i] + "&amp;");
		// }
		// qryStr = buf.toString();
		// } else
		// qryStr = "/share/right.jsp";
		request.getSession().setAttribute("requrl",
				qryStr.substring(7, qryStr.length()));
		//System.out.println("=====" + qryStr.substring(8, qryStr.length()));
		request.getSession().setAttribute("title", title);
		return mapping.findForward("frame");
	}
}
