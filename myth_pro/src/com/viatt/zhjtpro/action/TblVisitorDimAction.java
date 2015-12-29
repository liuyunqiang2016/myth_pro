package com.viatt.zhjtpro.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.forms.TblVisitorDimForm;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblVisitorDimService;

public class TblVisitorDimAction extends BaseAction {
	protected ITblVisitorDimService getTblVisitorDimService() {
		return (ITblVisitorDimService) getBean("tblVisitorDimService");
	}
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("queryTblVisitorDim".equals(method)) {
			return queryTblVisitorDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblVisitorDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblVisitorDimForm TblVisitorForm = (TblVisitorDimForm) form;
		
		String where="where 1=1 ";
		String strEquCode = MyRequest.GetString(request, "strEquCode");
		if(!strEquCode.equals("")){
			where = where + " and equ_code like '%" + strEquCode +"%' ";
		}
		request.setAttribute("strEquCode", strEquCode);
		String strBegin = MyRequest.GetString(request, "strBegin");
		if(!strBegin.equals("")){
			where = where + " and visi_time >= '" + strBegin +"' ";
		}
		String strEnd = MyRequest.GetString(request, "strEnd");
		if(!strEnd.equals("")){
			where = where + " and visi_time <= '" + strEnd +"' ";
		}
		request.setAttribute("strBegin", strBegin);
		request.setAttribute("strEnd", strEnd);

		int pageSize = TblVisitorForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblVisitorDims = getTblVisitorDimService().findTblVisitorDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblVisitorDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblVisitorDims", TblVisitorDims);

		return mapping.findForward("queryVisitorDim");

	}



	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblVisitorDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
