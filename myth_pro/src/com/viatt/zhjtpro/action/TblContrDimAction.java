package com.viatt.zhjtpro.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.forms.TblContrDimForm;
import com.viatt.zhjtpro.service.ITblContrDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;

public class TblContrDimAction extends BaseAction {
	protected ITblContrDimService getTblContrDimService() {
		return (ITblContrDimService) getBean("tblContrDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("queryTblContrDim".equals(method)) {
			return queryTblContrDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblContrDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblContrDimForm TblContrForm = (TblContrDimForm) form;
		
		String where="where 1=1 ";
		String strCardNo = MyRequest.GetString(request, "strCardNo");
		if(!strCardNo.equals("")){
			where = where + " and card_no like '%" + strCardNo +"%' ";
		}
		String strBegin = MyRequest.GetString(request, "strBegin");
		if(!strBegin.equals("")){
			where = where + " and create_time >= '" + strBegin +"' ";
		}
		String strEnd = MyRequest.GetString(request, "strEnd");
		if(!strEnd.equals("")){
			where = where + " and create_time <= '" + strEnd +"' ";
		}
		request.setAttribute("strCardNo", strCardNo);
		request.setAttribute("strBegin", strBegin);
		request.setAttribute("strEnd", strEnd);

		int pageSize = TblContrForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblContrDims = getTblContrDimService().findTblContrDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblContrDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblContrDims", TblContrDims);

		return mapping.findForward("queryContrDim");

	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblContrDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
