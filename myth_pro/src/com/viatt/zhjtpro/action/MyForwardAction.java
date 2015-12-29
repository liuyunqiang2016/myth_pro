/**
 * 
 */
package com.viatt.zhjtpro.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.common.MyRequest;

/**
 * @author wuyingbiao
 * 
 */
public class MyForwardAction extends BaseAction {

	protected ActionForward proccess(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		String page = MyRequest.GetString(arg2, "page", arg0.findForward("right").getPath());
		return new ActionForward(page);
	}

}
