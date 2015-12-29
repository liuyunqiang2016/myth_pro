package com.viatt.zhjtpro.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblAreaDimBo;
import com.viatt.zhjtpro.bo.TblCommDimBo;
import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblAreaDimForm;
import com.viatt.zhjtpro.service.ITblAreaDimService;
import com.viatt.zhjtpro.service.ITblBuildingDimService;
import com.viatt.zhjtpro.service.ITblCommDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblUserDimService;

import net.sf.json.JSONArray;

public class TblAreaDimAction extends BaseAction
{
	protected ITblUserDimService getUserDimService() {
		return (ITblUserDimService) getBean("tblUserDimService");
	}
	protected ITblAreaDimService getTblAreaDimService() {
		return (ITblAreaDimService) getBean("tblAreaDimService");
	}

	protected ITblCommDimService getTblCommDimService() {
		return (ITblCommDimService) getBean("tblCommDimService");
	}

	protected ITblBuildingDimService getTblBuildingDimService() {
		return (ITblBuildingDimService) getBean("tblBuildingDimService");
	}
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}
	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblAreaDim".equals(method)) {
			return forSaveTblAreaDim(mapping, form, request, response);
		} else if ("addTblAreaDim".equals(method)) {
			return saveTblAreaDim(mapping, form, request, response);
		} else if ("queryTblAreaDim".equals(method)) {
			return queryTblAreaDim(mapping, form, request, response);
		} else if ("removeTblAreaDim".equals(method)) {
			return removeTblAreaDim(mapping, form, request, response);
		} else if ("genAreaDim".equals(method)) {
			return genAreaDim(mapping, form, request, response);
		} else if ("addBatchAreaDim".equals(method)) {
			return saveBatchAreaDim(mapping, form, request, response);
		} else if ("checkAreaGen".equals(method)) {
			return checkAreaGen(mapping, form, request, response);
		} else if ("findAreaDimList".equals(method)) {
			return findAreaDimList(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblAreaDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception
	{
		TblAreaDimForm TblAreaForm = (TblAreaDimForm) form;
		
		TblUserDimBo userInfo = (TblUserDimBo) request.getSession().getAttribute(
				TblUserDimBo.USER_INFO_REFERENCE);
		
		if (userInfo.getUserState().equals("0")) {
			request.setAttribute("message", "用户已被禁用，请联系管理员");
			return mapping.findForward("LOGIN");
		}
		
		String where="where 1=1 ";
		String strAreaName = MyRequest.GetString(request, "strAreaName");
		if(!strAreaName.equals("")){
			where = where +" and area_name like '%"+strAreaName+"%' ";
		}
		String strCommCode = MyRequest.GetString(request, "strCommCode");
		if(!strCommCode.equals("")){
			where = where + " and comm_code="+strCommCode +" ";
		}
		request.setAttribute("strAreaName", strAreaName);
		request.setAttribute("strCommCode", strCommCode);

		
		String whereClause = StringUtils.isNotEmpty(userInfo.getPropertyId()) ? "where comm_pro = '" + userInfo.getPropertyId() + "'" : "";
		List<TblCommDimBo> commlist = getTblCommDimService().findTblCommDimByWhere(whereClause);
		request.setAttribute("commlist", commlist);
		
		int pageSize = TblAreaForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblAreaDims = getTblAreaDimService().findTblAreaDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblAreaDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblAreaDims", TblAreaDims);

		return mapping.findForward("queryAreaDim");

	}

	public ActionForward forSaveTblAreaDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblAreaDimForm TblAreaForm = (TblAreaDimForm) form;
		try {
			TblAreaDimBo bo = this.getTblAreaDimService().findTblAreaDimById(
					TblAreaForm.getAreaCode(), TblAreaForm.getCommCode());
			BeanUtils.copyProperties(TblAreaForm, bo);

			return mapping.findForward("addTblAreaDim");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/areaDim.do?method=queryTblAreaDim");
			return mapping.findForward("FAIL");
		}
	}

	public ActionForward saveTblAreaDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblAreaDimForm TblAreaForm = (TblAreaDimForm) form;
		TblAreaDimBo bo = new TblAreaDimBo();

		String strurl = getTblMenuDimService().getMenuString("/areaDim.do?method=queryTblAreaDim");
		try {
			BeanUtils.copyProperties(bo, TblAreaForm);// 属性拷贝
			getTblAreaDimService().save(bo);
			SysLogger.logInfo(request, "修改区域信息,区域编号："+bo.getAreaCode());
			request.setAttribute("message", "修改区域信息成功！");
			request.setAttribute("backurl",
					"/areaDim.do?method=queryTblAreaDim"+strurl);
			return mapping.findForward("SUCCESS");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/areaDim.do?method=queryTblAreaDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward saveBatchAreaDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblAreaDimForm TblAreaForm = (TblAreaDimForm) form;
		
		String areaName = TblAreaForm.getAreaName();
		areaName = new String(areaName.getBytes("ISO-8859-1"),"UTF-8");
		
		TblAreaDimBo bo = new TblAreaDimBo();

		String strurl = getTblMenuDimService().getMenuString("/areaDim.do?method=queryTblAreaDim");
		String commCode = MyRequest.GetString(request, "commCode");
		String areaCode = MyRequest.GetString(request, "areaCode");
		String commName = MyRequest.GetString(request, "commName");
		try {
			String[] sareaCode = areaCode.split(",");
			String[] sareaName = areaName.split(",");
			for (int i = 0; i < sareaCode.length; i++) {
				bo = new TblAreaDimBo();
				bo.setCommCode(commCode);
				bo.setAreaCode(sareaCode[i]);
				bo.setAreaName(sareaName[i]);
				bo.setCommName(commName);
				getTblAreaDimService().save(bo);
			}
			SysLogger.logInfo(request, "生成区域信息,小区编号："+commCode+",区域编号："+areaCode);
			request.setAttribute("message", "生成区域信息成功！");
			request.setAttribute("backurl",
					"/areaDim.do?method=queryTblAreaDim"+strurl);
			return mapping.findForward("SUCCESS");

		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/areaDim.do?method=queryTblAreaDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward genAreaDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblAreaDimForm TblAreaForm = (TblAreaDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/areaDim.do?method=queryTblAreaDim");
		String commCode = MyRequest.GetString(request, "commCode");
		if (commCode.equals("")) {
			request.setAttribute("message", "小区编号为空！");
			request.setAttribute("backurl",
					"/commDim.do?method=queryTblCommDim"+strurl);
			return mapping.findForward("FAIL");
		}
		TblCommDimBo bo = getTblCommDimService().findTblCommDimById(commCode);
		if (bo == null) {
			request.setAttribute("message", "小区编号[" + commCode + "]的小区不存在");
			request.setAttribute("backurl",
					"/commDim.do?method=queryTblCommDim"+strurl);
			return mapping.findForward("FAIL");
		}
		String commName = bo.getCommName();

		request.setAttribute("commName", commName);
		request.setAttribute("commCode", commCode);

		return mapping.findForward("forGenAreaDim");
	}
	
	public ActionForward findAreaDimList(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception 
	{
		String commCode = MyRequest.GetString(request, "commCode");
		if (StringUtils.isEmpty(commCode))
		{
			return mapping.findForward("null");
		}
		commCode = "where comm_code = '" + commCode + "'";
		List<TblAreaDimBo> boList = getTblAreaDimService().findTblAreaDimByWhere(commCode);
		request.setAttribute("boList", boList);
		
		try {
			JSONArray array = JSONArray.fromObject(boList);
			JSONArray ja = JSONArray.fromObject(array);
			request.setAttribute("ja", ja);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapping.findForward("null");
	}
	
	public ActionForward checkAreaGen(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String commCode = MyRequest.GetString(request, "commCode");
		String areaCode = MyRequest.GetString(request, "areaCode");
		
		response.setCharacterEncoding("UTF-8");
	    PrintWriter out =  response.getWriter();
		String[] sareaCode = areaCode.split(",");
		for(int i=0;i<sareaCode.length;i++){
			String where = " where area_code="+sareaCode[i] + " and comm_code="+commCode;
			List list = getTblAreaDimService().findTblAreaDimByWhere(where);
			if(list!=null && list.size()>0){
				out.println("{msg:'区域编号【"+sareaCode[i]+"】已存在'}");
				out.flush();
				out.close();
				return null;
			}
		}
	    out.println("{msg:'success'}");
    	out.flush();
		out.close();
		return null;
	}

	public ActionForward removeTblAreaDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String AreaCode = request.getParameter("areaCode");
		String commCode = request.getParameter("commCode");
		TblAreaDimForm TblAreaForm = (TblAreaDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/areaDim.do?method=queryTblAreaDim");
		String where ="where 1=1 and comm_code="+ commCode +" and area_code="+AreaCode;
		List list = getTblBuildingDimService().findTblBuildingDimByWhere(where);
		if(list!=null && list.size()>0){
			request.setAttribute("message", "区域信息下存在楼宇信息，请先删除楼宇信息！");
			request.setAttribute("backurl",
					"/areaDim.do?method=queryTblAreaDim"+strurl);
			return mapping.findForward("FAIL");
		}
		this.getTblAreaDimService().removeDim(AreaCode, commCode);
		SysLogger.logInfo(request, "删除区域信息,小区编号："+commCode+",区域编号："+AreaCode);
		request.setAttribute("message", "删除区域信息成功！");
		request.setAttribute("backurl", "/areaDim.do?method=queryTblAreaDim"+strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblAreaDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
