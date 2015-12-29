package com.viatt.zhjtpro.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.OrgDimBo;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.OrgDimForm;
import com.viatt.zhjtpro.service.IOrgDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;

public class OrgDimAction extends BaseAction {
	protected IOrgDimService getOrgDimService() {
		return (IOrgDimService) getBean("orgDimService");
	}
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblOrgDim".equals(method)) {
			return forSaveTblOrgDim(mapping, form, request, response);
		} else if ("addTblOrgDim".equals(method)) {
			return saveTblOrgDim(mapping, form, request, response);
		} else if ("queryTblOrgDim".equals(method)) {
			return queryTblOrgDim(mapping, form, request, response);
		} else if ("removeTblOrgDim".equals(method)) {
			return removeTblOrgDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblOrgDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		OrgDimForm TblOrgForm = (OrgDimForm) form;
		String where=" where 1=1 ";
		String strOrgName=MyRequest.GetString(request, "strOrgName");
		if(!strOrgName.equals("") ){
			where = where + " and Org_name like '%"+strOrgName+"%'";
		}
		request.setAttribute("strOrgName", strOrgName);

		int pageSize = TblOrgForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblOrgDims = getOrgDimService().findOrgDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblOrgDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblOrgDims", TblOrgDims);
		
		return mapping.findForward("queryOrgDim");

	}

	public ActionForward forSaveTblOrgDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		OrgDimForm TblOrgForm = (OrgDimForm) form;
		boolean isNew = TblOrgForm.getOrgCode() == null
				|| "".equals(TblOrgForm.getOrgCode());
		if (!isNew) {
			OrgDimBo bo = this.getOrgDimService().findOrgById(
					TblOrgForm.getOrgCode());
			BeanUtils.copyProperties(TblOrgForm, bo);
		}
		
		return mapping.findForward("addTblOrgDim");
	}

	public ActionForward saveTblOrgDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		OrgDimForm TblOrgForm = (OrgDimForm) form;
		OrgDimBo bo = new OrgDimBo();
		boolean isNew = TblOrgForm.getOrgCode() == null
				|| "".equals(TblOrgForm.getOrgCode());

		String strurl = getTblMenuDimService().getMenuString("/orgDim.do?method=queryTblOrgDim");
		try {
			if (isNew) {
				BeanUtils.copyProperties(bo, TblOrgForm);// 属性拷贝
				bo.setOrgCode(GetId.randomID());
				getOrgDimService().saveOrgDim(bo);
				SysLogger.logInfo(request, "添加机构信息,机构号："+bo.getOrgCode());
				request.setAttribute("message", "添加机构信息成功！");
				request.setAttribute("backurl",
						"/orgDim.do?method=queryTblOrgDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, TblOrgForm);// 属性拷贝
				getOrgDimService().saveOrgDim(bo);
				SysLogger.logInfo(request, "修改机构信息,机构号："+bo.getOrgCode());
				request.setAttribute("message", "修改机构信息成功！");
				request.setAttribute("backurl",
						"/orgDim.do?method=queryTblOrgDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/orgDim.do?method=queryTblOrgDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblOrgDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String OrgCode = request.getParameter("orgCode");
		OrgDimForm TblOrgForm = (OrgDimForm) form;
		
		String strurl = getTblMenuDimService().getMenuString("/orgDim.do?method=queryTblOrgDim");
		this.getOrgDimService().removeOrgDim(OrgCode);
		SysLogger.logInfo(request, "删除机构信息,机构号："+OrgCode);
		request.setAttribute("message", "删除机构信息成功！");
		request.setAttribute("backurl",
				"/orgDim.do?method=queryTblOrgDim"+strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblOrgDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
