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
import org.apache.struts.upload.FormFile;

import com.viatt.zhjtpro.bo.TblAreaDimBo;
import com.viatt.zhjtpro.bo.TblBuildingDimBo;
import com.viatt.zhjtpro.bo.TblCommDimBo;
import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.FileHelp;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblBuildingDimForm;
import com.viatt.zhjtpro.service.ITblAreaDimService;
import com.viatt.zhjtpro.service.ITblBuildingDimService;
import com.viatt.zhjtpro.service.ITblCommDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblUnitDimService;

import net.sf.json.JSONArray;

public class TblBuildingDimAction extends BaseAction
{
	protected ITblBuildingDimService getTblBuildingDimService() {
		return (ITblBuildingDimService) getBean("tblBuildingDimService");
	}
	
	protected ITblAreaDimService getTblAreaDimService() {
		return (ITblAreaDimService) getBean("tblAreaDimService");
	}

	protected ITblCommDimService getTblCommDimService() {
		return (ITblCommDimService) getBean("tblCommDimService");
	}
	
	protected ITblUnitDimService getTblUnitDimService() {
		return (ITblUnitDimService) getBean("tblUnitDimService");
	}
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblBuildingDim".equals(method)) {
			return forSaveTblBuildingDim(mapping, form, request, response);
		} else if ("addTblBuildingDim".equals(method)) {
			return saveTblBuildingDim(mapping, form, request, response);
		} else if ("queryTblBuildingDim".equals(method)) {
			return queryTblBuildingDim(mapping, form, request, response);
		} else if ("removeTblBuildingDim".equals(method)) {
			return removeTblBuildingDim(mapping, form, request, response);
		} else if ("genBuildingDim".equals(method)) {
			return genBuildingDim(mapping, form, request, response);
		} else if ("addBatchBuildingDim".equals(method)) {
			return saveBatchBuildingDim(mapping, form, request, response);
		} else if ("checkBuildingGen".equals(method)) {
			return checkBuildingGen(mapping, form, request, response);
		} else if ("findBuildingDimList".equals(method)) {
			return findBuildingDimList(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblBuildingDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception
	{
		TblBuildingDimForm TblBuildingForm = (TblBuildingDimForm) form;
		
		TblUserDimBo userInfo = (TblUserDimBo) request.getSession().getAttribute(
				TblUserDimBo.USER_INFO_REFERENCE);
		
		String where="where 1=1 ";
		String strAreaCode = MyRequest.GetString(request, "strAreaCode");
		if(!strAreaCode.equals("")){
			where = where +" and area_code ="+strAreaCode+" ";
		}
		String strCommCode = MyRequest.GetString(request, "strCommCode");
		if(!strCommCode.equals("")){
			where = where + " and comm_code="+strCommCode +" ";
		}
		String strBuildingName = MyRequest.GetString(request, "strBuildingName");
		if(!strBuildingName.equals("")){
			where = where + " and building_name like '%" + strBuildingName +"%' ";
		}
		request.setAttribute("strAreaCode", strAreaCode);
		request.setAttribute("strCommCode", strCommCode);
		request.setAttribute("strBuildingName", strBuildingName);

		String whereClause = StringUtils.isNotEmpty(userInfo.getPropertyId()) ? "where comm_pro = '" + userInfo.getPropertyId() + "'" : "";
		List<TblCommDimBo> commlist = getTblCommDimService().findTblCommDimByWhere(whereClause);
		request.setAttribute("commlist", commlist);
		String str = "";
		for (TblCommDimBo bo : commlist)
		{
			if (StringUtils.isNotEmpty(bo.getCommCode()))
			{
				str += "'" + bo.getCommCode() + "',";
			}
		}
		if (StringUtils.isNotEmpty(str))
		{
			str = str.substring(0, str.length() - 1);
			where = where + " and comm_code in(" + str + ") ";
		}
		else
		{
			where = where + " and comm_code in('test') ";
		}
		
		List<TblAreaDimBo> arealist = getTblAreaDimService().findTblAreaDimByWhere("");
		request.setAttribute("arealist", arealist);

		int pageSize = TblBuildingForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblBuildingDims = getTblBuildingDimService().findTblBuildingDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblBuildingDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblBuildingDims", TblBuildingDims);

		return mapping.findForward("queryBuildingDim");

	}

	public ActionForward forSaveTblBuildingDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblBuildingDimForm TblBuildingForm = (TblBuildingDimForm) form;
		try {
			TblBuildingDimBo bo = this.getTblBuildingDimService().findTblBuildingDimById(
					TblBuildingForm.getBuildingCode(), TblBuildingForm.getAreaCode(),
					TblBuildingForm.getCommCode());
			BeanUtils.copyProperties(TblBuildingForm, bo);

			return mapping.findForward("addTblBuildingDim");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/buildingDim.do?method=queryTblBuildingDim");
			return mapping.findForward("FAIL");
		}
	}

	public ActionForward saveTblBuildingDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblBuildingDimForm TblBuildingForm = (TblBuildingDimForm) form;
		TblBuildingDimBo bo = new TblBuildingDimBo();
		
		request.setAttribute("menuPare", TblBuildingForm.getMenuPare());
		request.setAttribute("menuChild", TblBuildingForm.getMenuChild());
		String strurl = getTblMenuDimService().getMenuString("/buildingDim.do?method=queryTblBuildingDim");
		FormFile file = TblBuildingForm.getFile();
		String fileName = TblBuildingForm.getCommCode()+"_"+TblBuildingForm.getAreaCode()+"_"+TblBuildingForm.getBuildingCode()+".jpg";
		String msg = FileHelp.upLoadFile1(file, "buildingDim","pic",fileName);
		if(!msg.equals("")){
			request.setAttribute("message", msg);
			request.setAttribute("backurl",
			"/buildingDim.do?method=queryTblBuildingDim"+strurl);
			return mapping.findForward("FAIL");
		}

		try {
			BeanUtils.copyProperties(bo, TblBuildingForm);// 属性拷贝
			if(!file.getFileName().equals("")){
				bo.setBuildingUrl(fileName);
			}
			getTblBuildingDimService().save(bo);
			SysLogger.logInfo(request, "修改楼宇信息,楼宇编号："+bo.getBuildingCode()+",区域编号："+bo.getAreaCode()+",小区编号："+bo.getCommCode());
			request.setAttribute("message", "修改楼宇信息成功！");
			request.setAttribute("backurl",
					"/buildingDim.do?method=queryTblBuildingDim"+strurl);
			return mapping.findForward("SUCCESS");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/buildingDim.do?method=queryTblBuildingDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward saveBatchBuildingDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblBuildingDimForm TblBuildingForm = (TblBuildingDimForm) form;
		TblBuildingDimBo bo = new TblBuildingDimBo();
		String strurl = getTblMenuDimService().getMenuString("/buildingDim.do?method=queryTblBuildingDim");

		String commCode = MyRequest.GetString(request, "commCode");
		String areaCode = MyRequest.GetString(request, "areaCode");
		String BuildingCode = MyRequest.GetString(request, "buildingCode");
		String BuildingName = MyRequest.GetString(request, "buildingName");
		try {
			String[] sBuildingCode = BuildingCode.split(",");
			String[] sBuildingName = BuildingName.split(",");
			for (int i = 0; i < sBuildingCode.length; i++) {
				bo = new TblBuildingDimBo();
				bo.setCommCode(commCode);
				bo.setAreaCode(areaCode);
				bo.setBuildingCode(sBuildingCode[i]);
				bo.setBuildingName(sBuildingName[i]);
				getTblBuildingDimService().save(bo);
			}
			SysLogger.logInfo(request, "生成楼宇信息,楼宇编号："+BuildingCode+",区域编号："+areaCode+",小区编号："+commCode);
			request.setAttribute("message", "生成楼宇信息成功！");
			request.setAttribute("backurl",
					"/buildingDim.do?method=queryTblBuildingDim"+strurl);
			return mapping.findForward("SUCCESS");

		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/buildingDim.do?method=queryTblBuildingDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward genBuildingDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblBuildingDimForm TblBuildingForm = (TblBuildingDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/buildingDim.do?method=queryTblBuildingDim");
		String commCode = MyRequest.GetString(request, "commCode");
		String areaCode = MyRequest.GetString(request, "areaCode");
		if (commCode.equals("")||areaCode.equals("")) {
			request.setAttribute("message", "小区编号或区域编号为空！");
			request.setAttribute("backurl",
					"/areaDim.do?method=queryTblAreaDim"+strurl);
			return mapping.findForward("FAIL");
		}
		TblAreaDimBo bo = getTblAreaDimService().findTblAreaDimById(areaCode, commCode);
		if (bo == null) {
			request.setAttribute("message", "小区编号[" + commCode + "],区域编号["+areaCode+"]的区域不存在");
			request.setAttribute("backurl",
					"/commDim.do?method=queryTblCommDim"+strurl);
			return mapping.findForward("FAIL");
		}
		String commName = bo.getCommCode();
		String areaName = bo.getAreaName();

		request.setAttribute("commName", commName);
		request.setAttribute("areaName", areaName);
		request.setAttribute("commCode", commCode);
		request.setAttribute("areaCode", areaCode);

		return mapping.findForward("forGenBuildingDim");
	}
	
	public ActionForward checkBuildingGen(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String commCode = MyRequest.GetString(request, "commCode");
		String areaCode = MyRequest.GetString(request, "areaCode");
		String BuildingCode = MyRequest.GetString(request, "buildingCode");
		
		response.setCharacterEncoding("UTF-8");
	    PrintWriter out =  response.getWriter();
		String[] sBuildingCode = BuildingCode.split(",");
		for(int i=0;i<sBuildingCode.length;i++){
			String where = " where building_code="+sBuildingCode[i] + " and comm_code="+commCode
				+" and area_code="+areaCode;
			List list = getTblBuildingDimService().findTblBuildingDimByWhere(where);
			if(list!=null && list.size()>0){
				out.println("{msg:'楼宇编号【"+sBuildingCode[i]+"】已存在'}");
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

	public ActionForward removeTblBuildingDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String BuildingCode = request.getParameter("buildingCode");
		String commCode = request.getParameter("commCode");
		String areaCode = request.getParameter("areaCode");
		TblBuildingDimForm TblBuildingForm = (TblBuildingDimForm) form;
		
		String strurl = getTblMenuDimService().getMenuString("/buildingDim.do?method=queryTblBuildingDim");
		
		String where ="where 1=1 and comm_code="+ commCode +" and area_code="+areaCode
			+" and building_code = "+BuildingCode;
		List list =getTblUnitDimService().findTblUnitDimByWhere(where);
		if(list!=null && list.size()>0){
			request.setAttribute("message", "楼宇信息下存在单元信息，请先删除单元信息！");
			request.setAttribute("backurl",
					"/buildingDim.do?method=queryTblBuildingDim"+strurl);
			return mapping.findForward("FAIL");
		}
		
		this.getTblBuildingDimService().removeDim(BuildingCode, areaCode,commCode);
		SysLogger.logInfo(request, "删除楼宇信息,楼宇编号："+BuildingCode+",区域编号："+areaCode+",小区编号："+commCode);
		request.setAttribute("message", "删除区域信息成功！");
		request.setAttribute("backurl", "/buildingDim.do?method=queryTblBuildingDim"+strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblBuildingDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

	public ActionForward findBuildingDimList(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception 
	{
		String commCode = MyRequest.GetString(request, "commCode");
		String areaCode = MyRequest.GetString(request, "areaCode");
		if (StringUtils.isEmpty(commCode))
		{
			return mapping.findForward("null");
		}
		String whereStr = "where comm_code = '" + commCode + "'";
		if (StringUtils.isNotEmpty(areaCode))
		{
			whereStr += "and area_code = '" + areaCode + "'";
		}
		List<TblBuildingDimBo> boList = getTblBuildingDimService().findTblBuildingDimByWhere(whereStr);
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
}
