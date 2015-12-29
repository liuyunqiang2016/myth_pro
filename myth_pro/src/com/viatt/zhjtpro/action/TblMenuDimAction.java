package com.viatt.zhjtpro.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblMenuDimBo;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblMenuDimForm;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblRolemenuDimService;

public class TblMenuDimAction extends BaseAction {
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}
	protected ITblRolemenuDimService getTblRolemenuDimService() {
		return (ITblRolemenuDimService) getBean("tblRolemenuDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblMenuDim".equals(method)) {
			return forSaveTblMenuDim(mapping, form, request, response);
		} else if ("addTblMenuDim".equals(method)) {
			return saveTblMenuDim(mapping, form, request, response);
		} else if ("queryTblMenuDim".equals(method)) {
			return queryTblMenuDim(mapping, form, request, response);
		} else if ("removeTblMenuDim".equals(method)) {
			return removeTblMenuDim(mapping, form, request, response);
		} else if ("viewTblMenuDim".equals(method)) {
			return viewTblMenuDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblMenuDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblMenuDimForm TblMenuForm = (TblMenuDimForm) form;
		
		String where="where 1=1 ";
		String strMenuName = MyRequest.GetString(request, "strMenuName");
		if(!strMenuName.equals("")){
			where = where + " and menu_name like '%" + strMenuName +"%' ";
		}
		request.setAttribute("strMenuName", strMenuName);

		int pageSize = TblMenuForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblMenuDims = getTblMenuDimService().findTblMenuDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblMenuDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblMenuDims", TblMenuDims);

		List list = this.getTblMenuDimService().findTblMenuDimByWhere(" where menu_lev='1'");
		request.setAttribute("menuList", list);
		return mapping.findForward("queryMenuDim");

	}

	public ActionForward forSaveTblMenuDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblMenuDimForm TblMenuForm = (TblMenuDimForm) form;
		boolean isNew = TblMenuForm.getMenuCode() == null
				|| "".equals(TblMenuForm.getMenuCode());
		
		if (!isNew) {
			TblMenuDimBo bo = this.getTblMenuDimService().findTblMenuDimById(
					TblMenuForm.getMenuCode());
			BeanUtils.copyProperties(TblMenuForm, bo);
		}
		List list = this.getTblMenuDimService().findTblMenuDimByWhere(" where menu_lev='1'");
		TblMenuForm.setParMenu(list);
		return mapping.findForward("addTblMenuDim");
	}

	public ActionForward saveTblMenuDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblMenuDimForm TblMenuForm = (TblMenuDimForm) form;
		TblMenuDimBo bo = new TblMenuDimBo();
		boolean isNew = TblMenuForm.getMenuCode() == null
				|| "".equals(TblMenuForm.getMenuCode());

		String strurl = getTblMenuDimService().getMenuString("/menuDim.do?method=queryTblMenuDim");
		try {
			if (isNew) {
				BeanUtils.copyProperties(bo, TblMenuForm);// 属性拷贝
				bo.setMenuCode(GetId.randomID());
				getTblMenuDimService().save(bo);
				SysLogger.logInfo(request, "添加菜单信息,菜单编号："+bo.getMenuCode());
				request.setAttribute("message", "添加菜单信息成功！");
				request.setAttribute("backurl",
						"/menuDim.do?method=queryTblMenuDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, TblMenuForm);// 属性拷贝
				getTblMenuDimService().save(bo);
				SysLogger.logInfo(request, "修改菜单信息,菜单编号："+bo.getMenuCode());
				request.setAttribute("message", "修改菜单信息成功！");
				request.setAttribute("backurl",
						"/menuDim.do?method=queryTblMenuDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/menuDim.do?method=queryTblMenuDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblMenuDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String MenuCode = request.getParameter("menuCode");
		TblMenuDimForm TblMenuForm = (TblMenuDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/menuDim.do?method=queryTblMenuDim");
		
		List list = getTblRolemenuDimService().findTblRolemenuDimByWhere(" where menu_code="+MenuCode);
		if(list!=null&&list.size()>0){
			request.setAttribute("message", "菜单关联角色，请先解除关联关系！");
			request.setAttribute("backurl", "/menuDim.do?method=queryTblMenuDim"+strurl);
			return mapping.findForward("FAIL");
		}
		this.getTblMenuDimService().removeMenuDim(MenuCode);
		SysLogger.logInfo(request, "删除菜单信息,菜单编号："+MenuCode);
		request.setAttribute("message", "删除菜单信息成功！");
		request.setAttribute("backurl", "/menuDim.do?method=queryTblMenuDim"+strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward viewTblMenuDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String MenuCode = request.getParameter("menuCode");
		TblMenuDimForm TblMenuForm = (TblMenuDimForm) form;
		TblMenuDimBo bo = this.getTblMenuDimService().findTblMenuDimById(
				MenuCode);
		BeanUtils.copyProperties(TblMenuForm, bo);
		List list = this.getTblMenuDimService().findTblMenuDimByWhere(" where menu_lev='1'");
		request.setAttribute("menuList", list);
		return mapping.findForward("viewTblMenuDim");
	}


	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblMenuDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
