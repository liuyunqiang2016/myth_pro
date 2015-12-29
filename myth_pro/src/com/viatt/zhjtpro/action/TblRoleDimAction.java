package com.viatt.zhjtpro.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblMenuDimBo;
import com.viatt.zhjtpro.bo.TblRoleDimBo;
import com.viatt.zhjtpro.bo.TblRolemenuDimBo;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblMenuDimForm;
import com.viatt.zhjtpro.forms.TblRoleDimForm;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblRoleDimService;
import com.viatt.zhjtpro.service.ITblRolemenuDimService;

public class TblRoleDimAction extends BaseAction {
	protected ITblRoleDimService getTblRoleDimService() {
		return (ITblRoleDimService) getBean("tblRoleDimService");
	}
	protected ITblRolemenuDimService getTblRolemenuDimService() {
		return (ITblRolemenuDimService) getBean("tblRolemenuDimService");
	}
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblRoleDim".equals(method)) {
			return forSaveTblRoleDim(mapping, form, request, response);
		} else if ("addTblRoleDim".equals(method)) {
			return saveTblRoleDim(mapping, form, request, response);
		} else if ("queryTblRoleDim".equals(method)) {
			return queryTblRoleDim(mapping, form, request, response);
		} else if ("removeTblRoleDim".equals(method)) {
			return removeTblRoleDim(mapping, form, request, response);
		} else if("signTblRolemenuDim".equals(method)){
			return signTblRolemenuDim(mapping, form, request, response);
		} else if("saveTblRolemenuDim".equals(method)){
			return saveTblRolemenuDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblRoleDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblRoleDimForm TblRoleForm = (TblRoleDimForm) form;
		
		String where="where 1=1 ";
		String strRoleName = MyRequest.GetString(request, "strRoleName");
		if(!strRoleName.equals("")){
			where = where + " and role_name like '%" + strRoleName +"%' ";
		}
		request.setAttribute("strRoleName", strRoleName);

		int pageSize = TblRoleForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblRoleDims = getTblRoleDimService().findTblRoleDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblRoleDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblRoleDims", TblRoleDims);

		return mapping.findForward("queryRoleDim");

	}

	public ActionForward forSaveTblRoleDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblRoleDimForm TblRoleForm = (TblRoleDimForm) form;
		boolean isNew = TblRoleForm.getRoleCode() == null
				|| "".equals(TblRoleForm.getRoleCode());
		if (!isNew) {
			TblRoleDimBo bo = this.getTblRoleDimService().findTblRoleDimById(
					TblRoleForm.getRoleCode());
			BeanUtils.copyProperties(TblRoleForm, bo);
		}

		return mapping.findForward("addTblRoleDim");
	}

	public ActionForward saveTblRoleDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblRoleDimForm TblRoleForm = (TblRoleDimForm) form;
		TblRoleDimBo bo = new TblRoleDimBo();
		boolean isNew = TblRoleForm.getRoleCode() == null
				|| "".equals(TblRoleForm.getRoleCode());

		String strurl = getTblMenuDimService().getMenuString("/roleDim.do?method=queryTblRoleDim");
		try {
			if (isNew) {
				BeanUtils.copyProperties(bo, TblRoleForm);// 属性拷贝
				bo.setRoleCode(GetId.randomID());
				getTblRoleDimService().save(bo);
				SysLogger.logInfo(request, "添加角色信息,角色编号："+bo.getRoleCode());
				request.setAttribute("message", "添加角色信息成功！");
				request.setAttribute("backurl",
						"/roleDim.do?method=queryTblRoleDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, TblRoleForm);// 属性拷贝
				getTblRoleDimService().save(bo);
				SysLogger.logInfo(request, "修改角色信息,角色编号："+bo.getRoleCode());
				request.setAttribute("message", "修改角色信息成功！");
				request.setAttribute("backurl",
						"/roleDim.do?method=queryTblRoleDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/roleDim.do?method=queryTblRoleDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblRoleDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String RoleCode = request.getParameter("roleCode");
		TblRoleDimForm TblRoleForm = (TblRoleDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/roleDim.do?method=queryTblRoleDim");
		List list = getTblRolemenuDimService().findTblRolemenuDimByWhere(" where role_code="+RoleCode);
		if(list!=null&&list.size()>0){
			request.setAttribute("message", "请先解除角色与菜单的关联关系！");
			request.setAttribute("backurl", "/roleDim.do?method=queryTblRoleDim"+strurl);
			return mapping.findForward("FAIL");
		}
		this.getTblRoleDimService().removeRoleDim(RoleCode);
		SysLogger.logInfo(request, "删除角色信息,角色编号："+RoleCode);
		request.setAttribute("message", "删除角色信息成功！");
		request.setAttribute("backurl", "/roleDim.do?method=queryTblRoleDim"+strurl);
		return mapping.findForward("SUCCESS");
	}
	
	public ActionForward signTblRolemenuDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String roleCode = request.getParameter("roleCode");
		TblRoleDimBo role = getTblRoleDimService().findTblRoleDimById(roleCode);
		List roleMenu = getTblRolemenuDimService().findTblRolemenuDimByWhere(" where role_code="+roleCode);
		List menupar = getTblMenuDimService().findTblMenuDimByWhere(" where menu_lev='1'");
		List menu = new ArrayList();
		if(menupar.size()>0){
			for(int i=0;i<menupar.size();i++){
				TblMenuDimBo bo = (TblMenuDimBo)menupar.get(i);
				List menu1 = getTblMenuDimService().findTblMenuDimByWhere(" where menu_par="+bo.getMenuCode());
				TblMenuDimForm form1 = new TblMenuDimForm();
				BeanUtils.copyProperties(form1, bo);
				form1.setParMenu(menu1);
				menu.add(form1);
			}
		}
		request.setAttribute("menu", menu);
		request.setAttribute("roleMenu", roleMenu);
		request.setAttribute("role", role);
		
		return mapping.findForward("signTblRolemenuDim");
	}
	public ActionForward saveTblRolemenuDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String roleCode = request.getParameter("roleCode");
		TblRoleDimForm TblRoleForm = (TblRoleDimForm) form;
		request.setAttribute("menuPare", TblRoleForm.getMenuPare());
		request.setAttribute("menuChild", TblRoleForm.getMenuChild());
		getTblRolemenuDimService().deleteTblRolemenuDimByRoleCode(roleCode);
		List list = getTblMenuDimService().findTblMenuDimByWhere(" where menu_lev='1'");
		for(int i=0;i<list.size();i++){
			TblMenuDimBo bo = (TblMenuDimBo)list.get(i);
			String[] str = request.getParameterValues(bo.getMenuCode());
			if(str!=null&&str.length>0){
				for(int j=0;j<str.length;j++){
					TblRolemenuDimBo rmenu = new TblRolemenuDimBo();
					rmenu.setRoleCode(roleCode);
					rmenu.setMenuCode(str[j]);
					getTblRolemenuDimService().save(rmenu);
				}
			}
		}
		String strurl = getTblMenuDimService().getMenuString("/roleDim.do?method=queryTblRoleDim");
		SysLogger.logInfo(request, "分配角色菜单信息,角色编号："+roleCode);
		request.setAttribute("message", "分配角色菜单信息成功！");
		request.setAttribute("backurl",
				"/roleDim.do?method=queryTblRoleDim"+strurl);
		return mapping.findForward("SUCCESS");
	}


	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblRoleDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}