package com.viatt.zhjtpro.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblCalDimBo;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblCalDimForm;
import com.viatt.zhjtpro.service.ITblCalDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;

public class TblCalDimAction extends BaseAction {
	protected ITblCalDimService getTblCalDimService() {
		return (ITblCalDimService) getBean("tblCalDimService");
	}
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblCalDim".equals(method)) {
			return forSaveTblCalDim(mapping, form, request, response);
		} else if ("addTblCalDim".equals(method)) {
			return saveTblCalDim(mapping, form, request, response);
		} else if ("queryTblCalDim".equals(method)) {
			return queryTblCalDim(mapping, form, request, response);
		} else if ("removeTblCalDim".equals(method)) {
			return removeTblCalDim(mapping, form, request, response);
		} else if ("viewTblCalDim".equals(method)) {
			return viewTblCalDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblCalDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblCalDimForm TblCalForm = (TblCalDimForm) form;
		
		String where="where 1=1 ";
		String strCalName = MyRequest.GetString(request, "strCalName");
		if(!strCalName.equals("")){
			where = where + " and cal_name like '%" + strCalName +"%' ";
		}
		request.setAttribute("strCalName", strCalName);

		int pageSize = TblCalForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblCalDims = getTblCalDimService().findTblCalDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblCalDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblCalDims", TblCalDims);

		return mapping.findForward("queryCalDim");

	}

	public ActionForward forSaveTblCalDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblCalDimForm TblCalForm = (TblCalDimForm) form;
		boolean isNew = TblCalForm.getCalCode() == null
				|| "".equals(TblCalForm.getCalCode());
		if (!isNew) {
			TblCalDimBo bo = this.getTblCalDimService().findTblCalDimById(
					TblCalForm.getCalCode());
			BeanUtils.copyProperties(TblCalForm, bo);
		}

		return mapping.findForward("addTblCalDim");
	}

	public ActionForward saveTblCalDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblCalDimForm TblCalForm = (TblCalDimForm) form;
		TblCalDimBo bo = new TblCalDimBo();
		boolean isNew = TblCalForm.getCalCode() == null
				|| "".equals(TblCalForm.getCalCode());

		String strurl = getTblMenuDimService().getMenuString("/calDim.do?method=queryTblCalDim");
		try {
			if (isNew) {
				BeanUtils.copyProperties(bo, TblCalForm);// 属性拷贝
				bo.setCalCode(GetId.randomID());
				getTblCalDimService().save(bo);
				SysLogger.logInfo(request, "添加计算方式信息,计算方式编号："+bo.getCalCode());
				request.setAttribute("message", "添加计算方式信息成功！");
				request.setAttribute("backurl",
						"/calDim.do?method=queryTblCalDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, TblCalForm);// 属性拷贝
				getTblCalDimService().save(bo);
				SysLogger.logInfo(request, "修改计算方式信息,计算方式编号："+bo.getCalCode());
				request.setAttribute("message", "修改计算方式信息成功！");
				request.setAttribute("backurl",
						"/calDim.do?method=queryTblCalDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/calDim.do?method=queryTblCalDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblCalDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String CalCode = request.getParameter("calCode");
		TblCalDimForm TblCalForm = (TblCalDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/calDim.do?method=queryTblCalDim");
		this.getTblCalDimService().removeCalDim(CalCode);
		SysLogger.logInfo(request, "删除计算方式信息,计算方式编号："+CalCode);
		request.setAttribute("message", "删除计算方式信息成功！");
		request.setAttribute("backurl", "/calDim.do?method=queryTblCalDim"+strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward viewTblCalDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String CalCode = request.getParameter("calCode");
		TblCalDimForm TblCalForm = (TblCalDimForm) form;
		TblCalDimBo bo = this.getTblCalDimService().findTblCalDimById(
				CalCode);
		BeanUtils.copyProperties(TblCalForm, bo);
		return mapping.findForward("viewTblCalDim");
	}


	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblCalDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
