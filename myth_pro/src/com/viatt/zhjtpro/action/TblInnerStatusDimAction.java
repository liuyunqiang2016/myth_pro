package com.viatt.zhjtpro.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.forms.TblInnerStatusDimForm;
import com.viatt.zhjtpro.service.ITblEquDimService;
import com.viatt.zhjtpro.service.ITblInnerEquOpService;
import com.viatt.zhjtpro.service.ITblInnerStatusDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblRoomDimService;

public class TblInnerStatusDimAction extends BaseAction {

	protected ITblEquDimService getTblEquDimService() {
		return (ITblEquDimService) getBean("tblEquDimService");
	}

	protected ITblRoomDimService getTblRoomDimService() {
		return (ITblRoomDimService) getBean("tblRoomDimService");
	}

	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	protected ITblInnerEquOpService getTblInnerEquOpService() {
		return (ITblInnerEquOpService) getBean("tblInnerEquOpService");
	}

	protected ITblInnerStatusDimService getTblInnerStatusDimService() {
		return (ITblInnerStatusDimService) getBean("tblInnerStatusDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("queryInnerStatusDim".equals(method)) {
			return queryInnerStatusDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryInnerStatusDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblInnerStatusDimForm TblInnerStatusDimForm = (TblInnerStatusDimForm) form;

		String where = "where 1=1 ";
		String strEquCode = MyRequest.GetString(request, "strEquCode");
		if (!strEquCode.equals("")) {
			where = where + " and outerid like '%" + strEquCode + "%' ";
		}
		request.setAttribute("strEquCode", strEquCode);

		int pageSize = TblInnerStatusDimForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblInnerStatusDims = getTblInnerStatusDimService().findTblInnerStatusDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage, TblInnerStatusDims
				.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblInnerStatusDims", TblInnerStatusDims);

		return mapping.findForward("queryInnerStatusDim");

	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblEquDim")
				.getPath());
		return mapping.findForward("FAIL");
	}
}
