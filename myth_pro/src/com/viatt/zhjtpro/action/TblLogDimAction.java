package com.viatt.zhjtpro.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.forms.TblLogDimForm;
import com.viatt.zhjtpro.service.ITblLogDimService;

public class TblLogDimAction extends BaseAction {
	protected ITblLogDimService getTblLogDimService() {
		return (ITblLogDimService) getBean("tblLogDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("queryTblLogDim".equals(method)) {
			return queryTblLogDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblLogDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblLogDimForm TblLogForm = (TblLogDimForm) form;
		
		String where="where 1=1 ";
		
		String strBegin = MyRequest.GetString(request, "strBegin");
		if(!strBegin.equals("")){
			where = where + " and cre_time >= '" + strBegin +"' ";
		}
		String strEnd = MyRequest.GetString(request, "strEnd");
		if(!strEnd.equals("")){
			where = where + " and cre_time <= '" + strEnd +"' ";
		}
		request.setAttribute("strBegin", strBegin);
		request.setAttribute("strEnd", strEnd);

		int pageSize = TblLogForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblLogDims = getTblLogDimService().findTblLogDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblLogDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblLogDims", TblLogDims);

		return mapping.findForward("queryLogDim");

	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblLogDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
