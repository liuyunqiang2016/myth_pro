package com.viatt.zhjtpro.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblCardDimBo;
import com.viatt.zhjtpro.bo.TblEquDimBo;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblCardDimForm;
import com.viatt.zhjtpro.forms.TblEquDimForm;
import com.viatt.zhjtpro.service.ITblEquDimService;
import com.viatt.zhjtpro.service.ITblInfoStatusDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblRoomDimService;

public class TblEquDimAction extends BaseAction {
	protected ITblEquDimService getTblEquDimService() {
		return (ITblEquDimService) getBean("tblEquDimService");
	}
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}
	protected ITblRoomDimService getTblRoomDimService() {
		return (ITblRoomDimService) getBean("tblRoomDimService");
	}
	protected ITblInfoStatusDimService getTblInfoStatusDimService() {
		return (ITblInfoStatusDimService) getBean("tblInfoStatusDimService");
	}
	

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblEquDim".equals(method)) {
			return forSaveTblEquDim(mapping, form, request, response);
		} else if ("addTblEquDim".equals(method)) {
			return saveTblEquDim(mapping, form, request, response);
		} else if ("queryTblEquDim".equals(method)) {
			return queryTblEquDim(mapping, form, request, response);
		} else if ("removeTblEquDim".equals(method)) {
			return removeTblEquDim(mapping, form, request, response);
		} else if ("viewTblEquDim".equals(method)) {
			return viewTblEquDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblEquDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblEquDimForm TblEquForm = (TblEquDimForm) form;
		
		String where="where 1=1 ";
		String strEquCode = MyRequest.GetString(request, "strEquCode");
		if(!strEquCode.equals("")){
			where = where + " and equ_code like '%" + strEquCode +"%' ";
		}
		String strEquName = MyRequest.GetString(request, "strEquName");
		if(!strEquName.equals("")){
			where = where + " and equ_name like '%" + strEquName +"%' ";
		}
		String strIpAdd = MyRequest.GetString(request, "strIpAdd");
		if(!strIpAdd.equals("")){
			where = where + " and ip_add like '%" + strIpAdd +"%' ";
		}
		request.setAttribute("strEquCode", strEquCode);
		request.setAttribute("strEquName", strEquName);
		request.setAttribute("strIpAdd", strIpAdd);

		int pageSize = TblEquForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblEquDims = getTblEquDimService().findTblEquDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblEquDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblEquDims", TblEquDims);

		return mapping.findForward("queryEquDim");

	}

	public ActionForward forSaveTblEquDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblEquDimForm TblEquForm = (TblEquDimForm) form;
		boolean isNew = TblEquForm.getEquCode() == null
				|| "".equals(TblEquForm.getEquCode());
		if (!isNew) {
			TblEquDimBo bo = this.getTblEquDimService().findTblEquDimById(
					TblEquForm.getEquCode());
			BeanUtils.copyProperties(TblEquForm, bo);
		}
		List list = getTblRoomDimService().findTblRoomDimByWhere("");
		request.setAttribute("roomList", list);
		
		return mapping.findForward("addTblEquDim");
	}

	public ActionForward saveTblEquDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblEquDimForm TblEquForm = (TblEquDimForm) form;
		TblEquDimBo bo = new TblEquDimBo();
		boolean isNew = TblEquForm.getEquCode() == null
				|| "".equals(TblEquForm.getEquCode());
		String strurl = getTblMenuDimService().getMenuString("/equDim.do?method=queryTblEquDim");
		try {
			if (isNew) {
				BeanUtils.copyProperties(bo, TblEquForm);// 属性拷贝
				bo.setEquCode(GetId.randomID());
				getTblEquDimService().save(bo);
				SysLogger.logInfo(request, "添加设备信息,设备编号："+bo.getEquCode());
				request.setAttribute("message", "添加设备信息成功！");
				request.setAttribute("backurl",
						"/equDim.do?method=queryTblEquDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, TblEquForm);// 属性拷贝
				getTblEquDimService().save(bo);
				SysLogger.logInfo(request, "修改设备信息,设备编号："+bo.getEquCode());
				request.setAttribute("message", "修改设备信息成功！");
				request.setAttribute("backurl",
						"/equDim.do?method=queryTblEquDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/equDim.do?method=queryTblEquDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblEquDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String EquCode = request.getParameter("equCode");
		TblEquDimForm TblEquForm = (TblEquDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/equDim.do?method=queryTblEquDim");
		this.getTblEquDimService().removeEquDim(EquCode);
		//删除设备，同时删除信息发送表tbl_infostatus_dim的发送记录
		getTblInfoStatusDimService().deleteList(" where equ_code='"+EquCode+"'");
		SysLogger.logInfo(request, "删除设备信息,设备编号："+EquCode);
		request.setAttribute("message", "删除设备信息成功！");
		request.setAttribute("backurl",
				"/equDim.do?method=queryTblEquDim"+strurl);
		return mapping.findForward("SUCCESS");
	}
	public ActionForward viewTblEquDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String equCode = request.getParameter("equCode");
		TblEquDimForm TblEquForm = (TblEquDimForm) form;
		
		String strurl = getTblMenuDimService().getMenuString("/equDim.do?method=queryTblEquDim");
		TblEquDimBo bo = getTblEquDimService().findTblEquDimById(equCode);
		if(bo==null){
			request.setAttribute("message", "设备信息"+equCode+"的信息不存在！");
			request.setAttribute("backurl",
					"/equDim.do?method=queryTblEquDim"+strurl);
			return mapping.findForward("FAIL");
		}
		BeanUtils.copyProperties(TblEquForm, bo);
		List list = getTblRoomDimService().findTblRoomDimByWhere("");
		request.setAttribute("roomList", list);
		return mapping.findForward("viewTblEquDim");
	}
	
	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblEquDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
