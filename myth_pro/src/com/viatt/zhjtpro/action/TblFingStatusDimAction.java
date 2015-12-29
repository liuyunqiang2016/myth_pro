package com.viatt.zhjtpro.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.viatt.zhjtpro.bo.TblInfoStatusDimBo;
import com.viatt.zhjtpro.common.FileHelp;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.forms.TblFingStatusDimForm;
import com.viatt.zhjtpro.service.ITblFingStatusDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;

public class TblFingStatusDimAction extends BaseAction {
	protected ITblFingStatusDimService getTblFingStatusDimService() {
		return (ITblFingStatusDimService) getBean("tblFingStatusDimService");
	}
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("queryTblFingStatusDim".equals(method)) {
			return queryTblFingStatusDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblFingStatusDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblFingStatusDimForm TblFingStatusForm = (TblFingStatusDimForm) form;
		
		String where="where 1=1 ";
		String strEquCode = MyRequest.GetString(request, "strEquCode");
		if(!strEquCode.equals("")){
			where = where + " and Equ_Code ='" + strEquCode +"' ";
		}
		request.setAttribute("strEquCode", strEquCode);

		int pageSize = TblFingStatusForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblFingStatusDims = getTblFingStatusDimService().findTblFingStatusDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblFingStatusDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblFingStatusDims", TblFingStatusDims);

		return mapping.findForward("queryFingStatusDim");

	}


	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblFingStatusDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
