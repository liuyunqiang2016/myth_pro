package com.viatt.zhjtpro.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblParamDimBo;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblParamDimForm;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblParamDimService;

public class TblParamDimAction extends BaseAction {

	protected ITblParamDimService getTblParamDimService() {
		return (ITblParamDimService) getBean("tblParamDimService");
	}
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblParamDim".equals(method)) {
			return forSaveTblParamDim(mapping, form, request, response);
		} else if ("addTblParamDim".equals(method)) {
			return saveTblParamDim(mapping, form, request, response);
		} else if ("queryTblParamDim".equals(method)) {
			return queryTblParamDim(mapping, form, request, response);
		} else if ("removeTblParamDim".equals(method)) {
			return removeTblParamDim(mapping, form, request, response);
		} else if ("viewTblParamDim".equals(method)) {
			return viewTblParamDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblParamDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblParamDimForm TblParamForm = (TblParamDimForm) form;
		
		String where="where 1=1 ";
		String strParamName = MyRequest.GetString(request, "strParamName");
		if(!strParamName.equals("")){
			where = where + " and Param_name like '%" + strParamName +"%' ";
		}
		request.setAttribute("strParamName", strParamName);

		int pageSize = TblParamForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblParamDims = getTblParamDimService().findTblParamDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblParamDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblParamDims", TblParamDims);

		return mapping.findForward("queryParamDim");

	}

	public ActionForward forSaveTblParamDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblParamDimForm TblParamForm = (TblParamDimForm) form;
		boolean isNew = TblParamForm.getParamCode() == null
				|| "".equals(TblParamForm.getParamCode());
		
		if (!isNew) {
			TblParamDimBo bo = this.getTblParamDimService().findTblParamDimById(
					TblParamForm.getParamCode());
			BeanUtils.copyProperties(TblParamForm, bo);
		}

		return mapping.findForward("addTblParamDim");
	}

	public ActionForward saveTblParamDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblParamDimForm TblParamForm = (TblParamDimForm) form;
		TblParamDimBo bo = new TblParamDimBo();
//		boolean isNew = TblParamForm.getParamCode() == null
//				|| "".equals(TblParamForm.getParamCode());

		String strurl = getTblMenuDimService().getMenuString("/paramDim.do?method=queryTblParamDim");
		try {
			//新增
			if (TblParamForm.getOpType().equals("1")) {
				BeanUtils.copyProperties(bo, TblParamForm);// 属性拷贝
				getTblParamDimService().save(bo);
				SysLogger.logInfo(request, "添加参数信息,参数编号："+bo.getParamCode());
				request.setAttribute("message", "添加参数信息成功！");
				request.setAttribute("backurl",
						"/paramDim.do?method=queryTblParamDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, TblParamForm);// 属性拷贝
				getTblParamDimService().save(bo);
				SysLogger.logInfo(request, "修改参数信息,参数编号："+bo.getParamCode());
				request.setAttribute("message", "修改参数信息成功！");
				request.setAttribute("backurl",
						"/paramDim.do?method=queryTblParamDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/paramDim.do?method=queryTblParamDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblParamDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String ParamCode = request.getParameter("paramCode");
		TblParamDimForm TblParamForm = (TblParamDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/paramDim.do?method=queryTblParamDim");
		this.getTblParamDimService().removeParamDim(ParamCode);
		SysLogger.logInfo(request, "删除参数信息,参数编号："+ParamCode);
		request.setAttribute("message", "删除参数信息成功！");
		request.setAttribute("backurl", "/paramDim.do?method=queryTblParamDim"+strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward viewTblParamDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String ParamCode = request.getParameter("paramCode");
		TblParamDimForm TblParamForm = (TblParamDimForm) form;
		TblParamDimBo bo = this.getTblParamDimService().findTblParamDimById(
				ParamCode);
		BeanUtils.copyProperties(TblParamForm, bo);
		return mapping.findForward("viewTblParamDim");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblParamDim")
				.getPath());
		return mapping.findForward("FAIL");
	}
}
