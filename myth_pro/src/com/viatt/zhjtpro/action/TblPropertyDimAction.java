package com.viatt.zhjtpro.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblPropertyDimBo;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblPropertyDimForm;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblPropertyDimService;

public class TblPropertyDimAction extends BaseAction

{
	protected ITblPropertyDimService getTblPropertyDimService() {
		return (ITblPropertyDimService) getBean("tblPropertyDimService");
	}

	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblPropertyDim".equals(method)) {
			return forSaveTblPropertyDim(mapping, form, request, response);
		} else if ("addTblPropertyDim".equals(method)) {
			return saveTblPropertyDim(mapping, form, request, response);
		} else if ("queryTblPropertyDim".equals(method)) {
			return queryTblPropertyDim(mapping, form, request, response);
		} else if ("removeTblPropertyDim".equals(method)) {
			return removeTblPropertyDim(mapping, form, request, response);
		}else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblPropertyDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		
		TblPropertyDimForm TblPropertyForm = (TblPropertyDimForm) form;
		String where="where 1=1 ";

		int pageSize = TblPropertyForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblPropertyDims = getTblPropertyDimService().findTblPropertyDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblPropertyDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblPropertyDims", TblPropertyDims);

		return mapping.findForward("queryPropertyDim");

	}

	public ActionForward forSaveTblPropertyDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblPropertyDimForm TblPropertyForm = (TblPropertyDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/propertyDim.do?method=queryTblPropertyDim");
		
		try {
			if (StringUtils.isNotEmpty(TblPropertyForm.getPropertyId()))
			{
				TblPropertyDimBo tblPropertyDimBo = getTblPropertyDimService().findTblPropertyDimById(TblPropertyForm.getPropertyId());
				BeanUtils.copyProperties(TblPropertyForm, tblPropertyDimBo);
			}
			request.setAttribute("TblPropertyForm", TblPropertyForm);
			return mapping.findForward("addTblPropertyDim");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/propertyDim.do?method=queryTblPropertyDim"+strurl);
			return mapping.findForward("FAIL");
		}
	}

	public ActionForward saveTblPropertyDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblPropertyDimForm TblPropertyForm = (TblPropertyDimForm) form;
		TblPropertyDimBo bo = new TblPropertyDimBo();

		request.setAttribute("menuPare", TblPropertyForm.getMenuPare());
		request.setAttribute("menuChild", TblPropertyForm.getMenuChild());
		
		String strurl = getTblMenuDimService().getMenuString("/PropertyDim.do?method=queryTblPropertyDim");
		try {
			if (StringUtils.isNotEmpty(TblPropertyForm.getPropertyId()))
			{
				BeanUtils.copyProperties(bo, TblPropertyForm);// 属性拷贝
				getTblPropertyDimService().save(bo);
				SysLogger.logInfo(request, "修改物业公司成功");
				request.setAttribute("message", "修改物业公司成功！");
				request.setAttribute("backurl",
						"/propertyDim.do?method=queryTblPropertyDim"+strurl);
			}
			else
			{
				BeanUtils.copyProperties(bo, TblPropertyForm);// 属性拷贝
				bo.setPropertyId(GetId.randomID());
				getTblPropertyDimService().save(bo);
				SysLogger.logInfo(request, "新增物业公司成功");
				request.setAttribute("message", "新增物业公司成功！");
				request.setAttribute("backurl",
						"/propertyDim.do?method=queryTblPropertyDim"+strurl);
			}
			return mapping.findForward("SUCCESS");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/propertyDim.do?method=queryTblPropertyDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblPropertyDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		
		String propertyId = request.getParameter("propertyId");
		TblPropertyDimForm TblPropertyForm = (TblPropertyDimForm) form;
		
		String strurl = getTblMenuDimService().getMenuString("/propertyDim.do?method=queryTblPropertyDim");
		String where ="where 1=1 and property_id="+ propertyId;
		
		getTblPropertyDimService().removePropertyDim(propertyId);
		SysLogger.logInfo(request, "删除物业公司成功！");
		request.setAttribute("message", "删除物业公司成功！");
		request.setAttribute("backurl", "/propertyDim.do?method=queryTblPropertyDim"+strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblPropertyDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
