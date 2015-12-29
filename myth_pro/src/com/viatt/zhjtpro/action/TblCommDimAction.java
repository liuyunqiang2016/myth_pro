package com.viatt.zhjtpro.action;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.viatt.zhjtpro.bo.TblCommDimBo;
import com.viatt.zhjtpro.bo.TblPropertyDimBo;
import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.FileHelp;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblCommDimForm;
import com.viatt.zhjtpro.service.ITblAreaDimService;
import com.viatt.zhjtpro.service.ITblCommDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblPropertyDimService;

public class TblCommDimAction extends BaseAction
{
	protected ITblPropertyDimService getTblPropertyDimService() {
		return (ITblPropertyDimService) getBean("tblPropertyDimService");
	}
	
	protected ITblCommDimService getTblCommDimService() {
		return (ITblCommDimService) getBean("tblCommDimService");
	}
	
	protected ITblAreaDimService getTblAreaDimService() {
		return (ITblAreaDimService) getBean("tblAreaDimService");
	}
	
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblCommDim".equals(method)) {
			return forSaveTblCommDim(mapping, form, request, response);
		} else if ("addTblCommDim".equals(method)) {
			return saveTblCommDim(mapping, form, request, response);
		} else if ("queryTblCommDim".equals(method)) {
			return queryTblCommDim(mapping, form, request, response);
		} else if ("removeTblCommDim".equals(method)) {
			return removeTblCommDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblCommDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblCommDimForm TblCommForm = (TblCommDimForm) form;
		
		TblUserDimBo userBo = (TblUserDimBo) request.getSession().getAttribute(TblUserDimBo.USER_INFO_REFERENCE);
		
		String where=" where 1=1 ";
		String userWhere = "";
		if (StringUtils.isNotEmpty(userBo.getPropertyId()))
		{
			userWhere = "and comm_pro = '" + userBo.getPropertyId() + "'";
		}
		where += userWhere;
		
		String strCommName=MyRequest.GetString(request, "strCommName");
		String strCommPro = MyRequest.GetString(request, "strCommPro");
		if(!strCommName.equals("") ){
			where = where + " and comm_name like '%"+strCommName+"%' ";
		}
		if(!strCommPro.equals("") ){
			where = where + " and comm_pro like '%"+strCommPro+"%' ";
		}
		request.setAttribute("strCommName", strCommName);
		request.setAttribute("strCommPro", strCommPro);

		int pageSize = TblCommForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblCommDims = getTblCommDimService().findTblCommDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblCommDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblCommDims", TblCommDims);

		return mapping.findForward("queryCommDim");

	}

	public ActionForward forSaveTblCommDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblCommDimForm tblCommForm = (TblCommDimForm) form;
		
		TblUserDimBo user = (TblUserDimBo) request.getSession().getAttribute(
				TblUserDimBo.USER_INFO_REFERENCE);
		
		boolean isNew = tblCommForm.getCommCode() == null
				|| "".equals(tblCommForm.getCommCode());
		if (!isNew) {
			TblCommDimBo bo = this.getTblCommDimService().findTblCommDimById(
					tblCommForm.getCommCode());
			BeanUtils.copyProperties(tblCommForm, bo);
		}
		
		if (StringUtils.isNotEmpty(user.getPropertyId())) {
			TblPropertyDimBo property = this.getTblPropertyDimService().findTblPropertyDimById(user.getPropertyId());
			if (null != property)
			{
				request.setAttribute("property", property);
				tblCommForm.setCommPro(property.getPropertyId());
			}
			else
			{
				List<TblPropertyDimBo> propertyList = this.getTblPropertyDimService().findTblPropertyDimByWhere("");
				request.setAttribute("propertyList", propertyList);
			}
		}
		else
		{
			List<TblPropertyDimBo> propertyList = this.getTblPropertyDimService().findTblPropertyDimByWhere("");
			request.setAttribute("propertyList", propertyList);
		}
		
		
		return mapping.findForward("addTblCommDim");
	}

	public ActionForward saveTblCommDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblCommDimForm tblCommForm = (TblCommDimForm) form;
		TblCommDimBo bo = new TblCommDimBo();
		boolean isNew = tblCommForm.getCommCode() == null
				|| "".equals(tblCommForm.getCommCode());
		if (!isNew)
		{
			TblCommDimBo tblCommDimBo = getTblCommDimService().findTblCommDimById(tblCommForm.getCommCode());
			if (null == tblCommDimBo)
			{
				isNew = true;
			}
		}
		request.setAttribute("menuPare", tblCommForm.getMenuPare());
		request.setAttribute("menuChild", tblCommForm.getMenuChild());
		String strurl = getTblMenuDimService().getMenuString("/commDim.do?method=queryTblCommDim");
		FormFile file = tblCommForm.getFile();
		String fileSuffix  = "" ;
		if (file != null && StringUtils.isNotBlank(file.getFileName()))
		{
			fileSuffix = file.getFileName().substring(file.getFileName().lastIndexOf(".")).toUpperCase();
		}
        String newFileName = UUID.randomUUID() + fileSuffix ;
		String msg = FileHelp.upLoadFile(file, "commDim","pic", newFileName);
		if(!msg.equals("")){
			request.setAttribute("message", msg);
			request.setAttribute("backurl",
					"/commDim.do?method=queryTblCommDim"+strurl);
			return mapping.findForward("FAIL");
		}
		try {
			if (isNew) {
				BeanUtils.copyProperties(bo, tblCommForm);// 属性拷贝
				if(!file.getFileName().equals("")){
					bo.setCommUrl(newFileName);
				}
				getTblCommDimService().save(bo);
				SysLogger.logInfo(request, "添加小区信息,小区编号："+bo.getCommCode());
				request.setAttribute("message", "添加小区信息成功！");
				request.setAttribute("backurl",
						"/commDim.do?method=queryTblCommDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, tblCommForm);// 属性拷贝
				if(!file.getFileName().equals("")){
					bo.setCommUrl(newFileName);
				}
				getTblCommDimService().save(bo);
				SysLogger.logInfo(request, "修改小区信息,小区编号："+bo.getCommCode());
				request.setAttribute("message", "修改小区信息成功！");
				request.setAttribute("backurl",
						"/commDim.do?method=queryTblCommDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/commDim.do?method=queryTblCommDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblCommDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String commCode = request.getParameter("commCode");
		TblCommDimForm TblCommForm = (TblCommDimForm) form;
		
		String strurl = getTblMenuDimService().getMenuString("/commDim.do?method=queryTblCommDim");
		String where ="where 1=1 and comm_code="+ commCode ;
		List list = getTblAreaDimService().findTblAreaDimByWhere(where);
		if(list!=null && list.size()>0){
			request.setAttribute("message", "小区信息下存在区域信息，请先删除区域信息！");
			request.setAttribute("backurl",
					"/commDim.do?method=queryTblCommDim"+strurl);
			return mapping.findForward("FAIL");
		}
		
		this.getTblCommDimService().removeGrgDim(commCode);
		SysLogger.logInfo(request, "删除小区信息,小区编号："+commCode);
		request.setAttribute("message", "删除小区信息成功！");
		request.setAttribute("backurl",
				"/commDim.do?method=queryTblCommDim"+strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblCommDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
