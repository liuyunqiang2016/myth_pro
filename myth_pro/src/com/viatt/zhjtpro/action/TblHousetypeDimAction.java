package com.viatt.zhjtpro.action;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.viatt.zhjtpro.bo.TblHousetypeDimBo;
import com.viatt.zhjtpro.common.FileHelp;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblHousetypeDimForm;
import com.viatt.zhjtpro.service.ITblHousetypeDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblRoomDimService;

public class TblHousetypeDimAction extends BaseAction {
	protected ITblHousetypeDimService getTblHousetypeDimService() {
		return (ITblHousetypeDimService) getBean("tblHousetypeDimService");
	}
	
	protected ITblRoomDimService getTblRoomDimService() {
		return (ITblRoomDimService) getBean("tblRoomDimService");
	}
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblHousetypeDim".equals(method)) {
			return forSaveTblHousetypeDim(mapping, form, request, response);
		} else if ("addTblHousetypeDim".equals(method)) {
			return saveTblHousetypeDim(mapping, form, request, response);
		} else if ("queryTblHousetypeDim".equals(method)) {
			return queryTblHousetypeDim(mapping, form, request, response);
		} else if ("removeTblHousetypeDim".equals(method)) {
			return removeTblHousetypeDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblHousetypeDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblHousetypeDimForm TblHousetypeForm = (TblHousetypeDimForm) form;

		int pageSize = TblHousetypeForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblHousetypeDims = getTblHousetypeDimService().findTblHousetypeDim(beginIndex,
				pageSize, "");
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblHousetypeDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblHousetypeDims", TblHousetypeDims);

		return mapping.findForward("queryHousetypeDim");

	}

	public ActionForward forSaveTblHousetypeDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblHousetypeDimForm TblHousetypeForm = (TblHousetypeDimForm) form;
		boolean isNew = TblHousetypeForm.getHtCode() == null
				|| "".equals(TblHousetypeForm.getHtCode());
		if (!isNew) {
			TblHousetypeDimBo bo = this.getTblHousetypeDimService().findTblHousetypeDimById(
					TblHousetypeForm.getHtCode());
			BeanUtils.copyProperties(TblHousetypeForm, bo);
		}
		
		return mapping.findForward("addTblHousetypeDim");
	}

	public ActionForward saveTblHousetypeDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblHousetypeDimForm TblHousetypeForm = (TblHousetypeDimForm) form;
		TblHousetypeDimBo bo = new TblHousetypeDimBo();
		boolean isNew = TblHousetypeForm.getHtCode() == null
				|| "".equals(TblHousetypeForm.getHtCode());
		
		request.setAttribute("menuPare", TblHousetypeForm.getMenuPare());
		request.setAttribute("menuChild", TblHousetypeForm.getMenuChild());
		String strurl = getTblMenuDimService().getMenuString("/housetypeDim.do?method=queryTblHousetypeDim");
		FormFile file = TblHousetypeForm.getFile();
		String fileSuffix = "" ;
		if (file != null && file.getFileName() != ""){
			fileSuffix = file.getFileName().substring(file.getFileName().lastIndexOf(".")).toUpperCase();
		}
        String newFileName = UUID.randomUUID() + fileSuffix ;
		String msg = FileHelp.upLoadFile(file, "housetypeDim","pic", newFileName);
		if(!msg.equals("")){
			request.setAttribute("message", msg);
			request.setAttribute("backurl",
				"/housetypeDim.do?method=queryTblHousetypeDim"+strurl);
			return mapping.findForward("FAIL");
		}

		try {
			if (isNew) {
				BeanUtils.copyProperties(bo, TblHousetypeForm);// 属性拷贝
				if(!file.getFileName().equals("")){
					bo.setHtUrl(newFileName);
				}
				String maxid = getTblHousetypeDimService().getMaxId();
				bo.setHtCode(maxid+"");
				getTblHousetypeDimService().save(bo);
				SysLogger.logInfo(request, "添加户型信息,户型编号："+bo.getHtCode());
				request.setAttribute("message", "添加户型信息成功！");
				request.setAttribute("backurl",
						"/housetypeDim.do?method=queryTblHousetypeDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, TblHousetypeForm);// 属性拷贝
				if(!file.getFileName().equals("")){
					bo.setHtUrl(newFileName);
				}
				getTblHousetypeDimService().save(bo);
				SysLogger.logInfo(request, "修改户型信息,户型编号："+bo.getHtCode());
				request.setAttribute("message", "修改户型信息成功！");
				request.setAttribute("backurl",
						"/housetypeDim.do?method=queryTblHousetypeDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/housetypeDim.do?method=queryTblHousetypeDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblHousetypeDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String HousetypeCode = request.getParameter("htCode");
		TblHousetypeDimForm TblHousetypeForm = (TblHousetypeDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/housetypeDim.do?method=queryTblHousetypeDim");
		
		String where ="where 1=1 and ht_code="+ HousetypeCode;
		List list = getTblRoomDimService().findTblRoomDimByWhere(where);
		if(list!=null && list.size()>0){
			request.setAttribute("message", "户型信息关联着房号信息，不可删除户型信息！");
			request.setAttribute("backurl",
					"/housetypeDim.do?method=queryTblHousetypeDim"+strurl);
			return mapping.findForward("FAIL");
		}
		
		this.getTblHousetypeDimService().removeDim(HousetypeCode);
		SysLogger.logInfo(request, "删除户型信息,户型编号："+HousetypeCode);
		request.setAttribute("message", "删除户型信息成功！");
		request.setAttribute("backurl",
				"/housetypeDim.do?method=queryTblHousetypeDim"+strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblHousetypeDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
