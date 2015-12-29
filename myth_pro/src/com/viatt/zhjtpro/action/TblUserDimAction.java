package com.viatt.zhjtpro.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblPropertyDimBo;
import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.CryptionData;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblUserDimForm;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblPropertyDimService;
import com.viatt.zhjtpro.service.ITblRoleDimService;
import com.viatt.zhjtpro.service.ITblUserDimService;

public class TblUserDimAction extends BaseAction
{

	protected ITblPropertyDimService getTblPropertyDimService() {
		return (ITblPropertyDimService) getBean("tblPropertyDimService");
	}
	
	protected ITblUserDimService getTblUserDimService() {
		return (ITblUserDimService) getBean("tblUserDimService");
	}
	
	protected ITblRoleDimService getTblRoleDimService() {
		return (ITblRoleDimService) getBean("tblRoleDimService");
	}
	
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblUserDim".equals(method)) {
			return forSaveTblUserDim(mapping, form, request, response);
		} else if ("addTblUserDim".equals(method)) {
			return saveTblUserDim(mapping, form, request, response);
		} else if ("queryTblUserDim".equals(method)) {
			return queryTblUserDim(mapping, form, request, response);
		} else if ("removeTblUserDim".equals(method)) {
			return removeTblUserDim(mapping, form, request, response);
		} else if ("viewTblUserDim".equals(method)) {
			return viewTblUserDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblUserDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblUserDimForm TblUserForm = (TblUserDimForm) form;
		
		String where="where 1=1 ";
		String strUserName = MyRequest.GetString(request, "strUserName");
		if(!strUserName.equals("")){
			where = where + " and User_name like '%" + strUserName +"%' ";
		}
		request.setAttribute("strUserName", strUserName);

		int pageSize = TblUserForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblUserDims = getTblUserDimService().findTblUserDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblUserDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblUserDims", TblUserDims);

		return mapping.findForward("queryUserDim");

	}

	public ActionForward forSaveTblUserDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblUserDimForm TblUserForm = (TblUserDimForm) form;
		boolean isNew = TblUserForm.getUserCode() == null
				|| "".equals(TblUserForm.getUserCode());
		if (!isNew) {
			TblUserDimBo bo = this.getTblUserDimService().findTblUserDimById(
					TblUserForm.getUserCode());
			BeanUtils.copyProperties(TblUserForm, bo);
		}
		List list = getTblRoleDimService().findTblRoleDimByWhere("");
		request.setAttribute("roleList", list);
		
		List<TblPropertyDimBo> propertyList = getTblPropertyDimService().findTblPropertyDimByWhere("");
		request.setAttribute("propertyList", propertyList);
		
		return mapping.findForward("addTblUserDim");
	}

	public ActionForward saveTblUserDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblUserDimForm TblUserForm = (TblUserDimForm) form;
		TblUserDimBo bo = new TblUserDimBo();
		boolean isNew = TblUserForm.getUserCode() == null
				|| "".equals(TblUserForm.getUserCode());
		
		String strurl = getTblMenuDimService().getMenuString("/userDim.do?method=queryTblUserDim");
		try {
			if (isNew) {
				TblUserDimBo user = getTblUserDimService().getLoginUserInfo(TblUserForm.getLogName());
				if(user!=null){
					request.setAttribute("message", "登录名已存在");
					request.setAttribute("backurl",
							"/userDim.do?method=queryTblUserDim"+strurl);
					return mapping.findForward("FAIL");
				}
				BeanUtils.copyProperties(bo, TblUserForm);// 属性拷贝
				bo.setUserCode(GetId.randomID());
				bo.setLogPwd((new CryptionData())
						.EncryptionStringData(TblUserForm.getLogPwd()));
				getTblUserDimService().save(bo);
				SysLogger.logInfo(request, "添加用户信息,用户编号："+bo.getUserCode());
				request.setAttribute("message", "添加用户信息成功！");
				request.setAttribute("backurl",
						"/userDim.do?method=queryTblUserDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, TblUserForm);// 属性拷贝
				getTblUserDimService().save(bo);
				SysLogger.logInfo(request, "修改用户信息,用户编号："+bo.getUserCode());
				request.setAttribute("message", "修改用户信息成功！");
				request.setAttribute("backurl",
						"/userDim.do?method=queryTblUserDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/userDim.do?method=queryTblUserDim");
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblUserDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String UserCode = request.getParameter("UserCode");
		TblUserDimForm TblUserForm = (TblUserDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/userDim.do?method=queryTblUserDim");
		this.getTblUserDimService().removeUserDim(UserCode);
		SysLogger.logInfo(request, "删除用户信息,用户编号："+UserCode);
		request.setAttribute("message", "删除用户信息成功！");
		request.setAttribute("backurl", "/userDim.do?method=queryTblUserDim"+strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward viewTblUserDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String UserCode = request.getParameter("UserCode");
		TblUserDimForm TblUserForm = (TblUserDimForm) form;
		TblUserDimBo bo = this.getTblUserDimService().findTblUserDimById(
				UserCode);
		BeanUtils.copyProperties(TblUserForm, bo);
		List list = getTblRoleDimService().findTblRoleDimByWhere("");
		request.setAttribute("roleList", list);
		return mapping.findForward("viewTblUserDim");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblUserDim")
				.getPath());
		return mapping.findForward("FAIL");
	}
}
