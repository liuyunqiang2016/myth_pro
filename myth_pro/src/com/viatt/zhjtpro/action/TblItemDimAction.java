package com.viatt.zhjtpro.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblItemDimBo;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblItemDimForm;
import com.viatt.zhjtpro.service.ITblCalDimService;
import com.viatt.zhjtpro.service.ITblItemDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;

public class TblItemDimAction extends BaseAction {
	protected ITblItemDimService getTblItemDimService() {
		return (ITblItemDimService) getBean("tblItemDimService");
	}
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

		if ("forAddTblItemDim".equals(method)) {
			return forSaveTblItemDim(mapping, form, request, response);
		} else if ("addTblItemDim".equals(method)) {
			return saveTblItemDim(mapping, form, request, response);
		} else if ("queryTblItemDim".equals(method)) {
			return queryTblItemDim(mapping, form, request, response);
		} else if ("removeTblItemDim".equals(method)) {
			return removeTblItemDim(mapping, form, request, response);
		} else if ("viewTblItemDim".equals(method)) {
			return viewTblItemDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblItemDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblItemDimForm TblItemForm = (TblItemDimForm) form;
		
		String where="where 1=1 ";
		String strItemName = MyRequest.GetString(request, "strItemName");
		if(!strItemName.equals("")){
			where = where + " and Item_name like '%" + strItemName +"%' ";
		}
		request.setAttribute("strItemName", strItemName);

		int pageSize = TblItemForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblItemDims = getTblItemDimService().findTblItemDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblItemDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblItemDims", TblItemDims);

		return mapping.findForward("queryItemDim");

	}

	public ActionForward forSaveTblItemDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblItemDimForm TblItemForm = (TblItemDimForm) form;
		boolean isNew = TblItemForm.getItemCode() == null
				|| "".equals(TblItemForm.getItemCode());
		if (!isNew) {
			TblItemDimBo bo = this.getTblItemDimService().findTblItemDimById(
					TblItemForm.getItemCode());
			BeanUtils.copyProperties(TblItemForm, bo);
		}
		List list = getTblCalDimService().findTblCalDimByWhere("");
		request.setAttribute("calList", list);
		return mapping.findForward("addTblItemDim");
	}

	public ActionForward saveTblItemDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblItemDimForm TblItemForm = (TblItemDimForm) form;
		TblItemDimBo bo = new TblItemDimBo();
		boolean isNew = TblItemForm.getItemCode() == null
				|| "".equals(TblItemForm.getItemCode());

		String strurl = getTblMenuDimService().getMenuString("/itemDim.do?method=queryTblItemDim");
		try {
			if (isNew) {
				BeanUtils.copyProperties(bo, TblItemForm);// 属性拷贝
				bo.setItemCode(GetId.randomID());
				getTblItemDimService().save(bo);
				SysLogger.logInfo(request, "添加缴费项目信息,缴费项目编号："+bo.getItemCode());
				request.setAttribute("message", "添加缴费项目信息成功！");
				request.setAttribute("backurl",
						"/itemDim.do?method=queryTblItemDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, TblItemForm);// 属性拷贝
				getTblItemDimService().save(bo);
				SysLogger.logInfo(request, "修改缴费项目信息,缴费项目编号："+bo.getItemCode());
				request.setAttribute("message", "修改缴费项目信息成功！");
				request.setAttribute("backurl",
						"/itemDim.do?method=queryTblItemDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/itemDim.do?method=queryTblItemDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblItemDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String ItemCode = request.getParameter("itemCode");
		TblItemDimForm TblItemForm = (TblItemDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/itemDim.do?method=queryTblItemDim");
		this.getTblItemDimService().removeItemDim(ItemCode);
		SysLogger.logInfo(request, "删除缴费项目信息,缴费项目编号："+ItemCode);
		request.setAttribute("message", "删除缴费项目信息成功！");
		request.setAttribute("backurl", "/itemDim.do?method=queryTblItemDim"+strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward viewTblItemDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String ItemCode = request.getParameter("itemCode");
		TblItemDimForm TblItemForm = (TblItemDimForm) form;
		TblItemDimBo bo = this.getTblItemDimService().findTblItemDimById(
				ItemCode);
		BeanUtils.copyProperties(TblItemForm, bo);
		List list = getTblCalDimService().findTblCalDimByWhere("");
		request.setAttribute("calList", list);
		return mapping.findForward("viewTblItemDim");
	}


	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblItemDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
