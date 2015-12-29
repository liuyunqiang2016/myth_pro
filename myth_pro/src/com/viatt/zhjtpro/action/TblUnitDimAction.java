package com.viatt.zhjtpro.action;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.viatt.zhjtpro.bo.TblAreaDimBo;
import com.viatt.zhjtpro.bo.TblBuildingDimBo;
import com.viatt.zhjtpro.bo.TblCommDimBo;
import com.viatt.zhjtpro.bo.TblUnitDimBo;
import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.FileHelp;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblUnitDimForm;
import com.viatt.zhjtpro.service.ITblAreaDimService;
import com.viatt.zhjtpro.service.ITblBuildingDimService;
import com.viatt.zhjtpro.service.ITblCommDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblRoomDimService;
import com.viatt.zhjtpro.service.ITblUnitDimService;

import net.sf.json.JSONArray;

public class TblUnitDimAction extends BaseAction
{
	protected ITblBuildingDimService getTblBuildingDimService() {
		return (ITblBuildingDimService) getBean("tblBuildingDimService");
	}

	protected ITblUnitDimService getTblUnitDimService() {
		return (ITblUnitDimService) getBean("tblUnitDimService");
	}

	protected ITblAreaDimService getTblAreaDimService() {
		return (ITblAreaDimService) getBean("tblAreaDimService");
	}

	protected ITblCommDimService getTblCommDimService() {
		return (ITblCommDimService) getBean("tblCommDimService");
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

		if ("forAddTblUnitDim".equals(method)) {
			return forSaveTblUnitDim(mapping, form, request, response);
		} else if ("addTblUnitDim".equals(method)) {
			return saveTblUnitDim(mapping, form, request, response);
		} else if ("queryTblUnitDim".equals(method)) {
			return queryTblUnitDim(mapping, form, request, response);
		} else if ("removeTblUnitDim".equals(method)) {
			return removeTblUnitDim(mapping, form, request, response);
		} else if ("genUnitDim".equals(method)) {
			return genUnitDim(mapping, form, request, response);
		} else if ("addBatchUnitDim".equals(method)) {
			return saveBatchUnitDim(mapping, form, request, response);
		} else if ("checkUnitGen".equals(method)) {
			return checkUnitGen(mapping, form, request, response);
		} else if ("findUnitDimList".equals(method)) {
			return findUnitDimList(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblUnitDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception
	{
		TblUnitDimForm TblUnitForm = (TblUnitDimForm) form;
		
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
		String strBuildingCode = MyRequest.GetString(request, "strBuildingCode");
		if(!strBuildingCode.equals("")){
			where = where + " and building_code="+strBuildingCode +" ";
		}
		String strUnitName = MyRequest.GetString(request, "strUnitName");
		if(!strUnitName.equals("")){
			where = where + " and unit_name like '%" + strUnitName +"%' ";
		}
		request.setAttribute("strAreaCode", strAreaCode);
		request.setAttribute("strCommCode", strCommCode);
		request.setAttribute("strBuildingCode", strBuildingCode);
		request.setAttribute("strUnitName", strUnitName);

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
		List<TblBuildingDimBo> buildinglist = getTblBuildingDimService().findTblBuildingDimByWhere("");
		request.setAttribute("buildinglist", buildinglist);

		int pageSize = TblUnitForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblUnitDims = getTblUnitDimService().findTblUnitDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblUnitDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblUnitDims", TblUnitDims);

		return mapping.findForward("queryUnitDim");

	}

	public ActionForward forSaveTblUnitDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblUnitDimForm TblUnitForm = (TblUnitDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/unitDim.do?method=queryTblUnitDim");
		try {
			TblUnitDimBo bo = this.getTblUnitDimService().findTblUnitDimById(
					TblUnitForm.getBuildingCode(), TblUnitForm.getAreaCode(),
					TblUnitForm.getCommCode(), TblUnitForm.getUnitCode());
			BeanUtils.copyProperties(TblUnitForm, bo);

			return mapping.findForward("addTblUnitDim");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/unitDim.do?method=queryTblUnitDim"+strurl);
			return mapping.findForward("FAIL");
		}
	}

	public ActionForward saveTblUnitDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblUnitDimForm TblUnitForm = (TblUnitDimForm) form;
		TblUnitDimBo bo = new TblUnitDimBo();

		request.setAttribute("menuPare", TblUnitForm.getMenuPare());
		request.setAttribute("menuChild", TblUnitForm.getMenuChild());
		String strurl = getTblMenuDimService().getMenuString("/unitDim.do?method=queryTblUnitDim");
		FormFile file = TblUnitForm.getFile();
		String	fileSuffix = "" ;
		if (file != null && file.getFileName() != "") {
			fileSuffix = file.getFileName().substring(file.getFileName().lastIndexOf(".")).toUpperCase() ;
		}
		String newFileName = UUID.randomUUID() + fileSuffix;
		String msg = FileHelp.upLoadFile(file, "unitDim","pic", newFileName);
		if(!msg.equals("")){
			request.setAttribute("message", msg);
			request.setAttribute("backurl",
				"/unitDim.do?method=queryTblUnitDim"+strurl);
			return mapping.findForward("FAIL");
		}
		try {
			BeanUtils.copyProperties(bo, TblUnitForm);// 属性拷贝
			if(!file.getFileName().equals("")){
				bo.setUnitUrl(newFileName);
			}
			getTblUnitDimService().save(bo);
			SysLogger.logInfo(request, "修改单元信息,单元编号："+bo.getUnitCode()+",区域编号："+bo.getAreaCode()+",楼宇编号："+bo.getBuildingCode()+",小区编号："+bo.getCommCode());
			request.setAttribute("message", "修改单元信息成功！");
			request.setAttribute("backurl",
					"/unitDim.do?method=queryTblUnitDim"+strurl);
			return mapping.findForward("SUCCESS");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/unitDim.do?method=queryTblUnitDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward saveBatchUnitDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblUnitDimForm TblUnitForm = (TblUnitDimForm) form;
		TblUnitDimBo bo = new TblUnitDimBo();

		String commCode = MyRequest.GetString(request, "commCode");
		String areaCode = MyRequest.GetString(request, "areaCode");
		String buildingCode = MyRequest.GetString(request, "buildingCode");
		String UnitCode = MyRequest.GetString(request, "unitCode");
		String UnitName = MyRequest.GetString(request, "unitName");
		String strurl = getTblMenuDimService().getMenuString("/unitDim.do?method=queryTblUnitDim");
		try {
			String[] sUnitCode = UnitCode.split(",");
			String[] sUnitName = UnitName.split(",");
			for (int i = 0; i < sUnitCode.length; i++) {
				bo = new TblUnitDimBo();
				bo.setBuildingCode(buildingCode);
				bo.setCommCode(commCode);
				bo.setAreaCode(areaCode);
				bo.setUnitCode(sUnitCode[i]);
				bo.setUnitName(sUnitName[i]);
				getTblUnitDimService().save(bo);
			}
			SysLogger.logInfo(request, "生成单元信息,单元编号："+UnitCode+",区域编号："+areaCode+",楼宇编号："+buildingCode+",小区编号："+commCode);
			request.setAttribute("message", "生成单元信息成功！");
			request.setAttribute("backurl",
					"/unitDim.do?method=queryTblUnitDim"+strurl);
			return mapping.findForward("SUCCESS");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/unitDim.do?method=queryTblUnitDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward genUnitDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblUnitDimForm TblUnitForm = (TblUnitDimForm) form;
		String commCode = MyRequest.GetString(request, "commCode");
		String areaCode = MyRequest.GetString(request, "areaCode");
		String buildingCode = MyRequest.GetString(request, "buildingCode");
		String strurl = getTblMenuDimService().getMenuString("/unitDim.do?method=queryTblUnitDim");
		if (commCode.equals("") || areaCode.equals("")
				|| buildingCode.equals("")) {
			request.setAttribute("message", "小区编号或区域编号为空！");
			request.setAttribute("backurl",
					"/buildingDim.do?method=queryTblAreaDim"+strurl);
			return mapping.findForward("FAIL");
		}
		TblBuildingDimBo bo = getTblBuildingDimService()
				.findTblBuildingDimById(buildingCode, areaCode, commCode);
		if (bo == null) {
			request.setAttribute("message", "小区编号[" + commCode + "],区域编号["
					+ areaCode + "]的区域不存在");
			request.setAttribute("backurl",
					"/buildingDim.do?method=queryTblBuildingDim"+strurl);
			return mapping.findForward("FAIL");
		}

		request.setAttribute("buildingCode", buildingCode);
		request.setAttribute("commCode", commCode);
		request.setAttribute("areaCode", areaCode);
		BeanUtils.copyProperties(TblUnitForm, bo);

		return mapping.findForward("forGenUnitDim");
	}
	
	public ActionForward checkUnitGen(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String commCode = MyRequest.GetString(request, "commCode");
		String areaCode = MyRequest.GetString(request, "areaCode");
		String BuildingCode = MyRequest.GetString(request, "buildingCode");
		String UnitCode = MyRequest.GetString(request, "unitCode");
		
		response.setCharacterEncoding("UTF-8");
	    PrintWriter out =  response.getWriter();
		String[] sUnitCode = UnitCode.split(",");
		for(int i=0;i<sUnitCode.length;i++){
			String where = " where unit_code="+sUnitCode[i] + " and comm_code="+commCode
				+" and area_code="+areaCode +" and building_code="+BuildingCode;
			List list = getTblUnitDimService().findTblUnitDimByWhere(where);
			if(list!=null && list.size()>0){
				out.println("{msg:'单元编号【"+sUnitCode[i]+"】已存在'}");
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

	public ActionForward removeTblUnitDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String UnitCode = request.getParameter("unitCode");
		String commCode = request.getParameter("commCode");
		String areaCode = request.getParameter("areaCode");
		String buildingCode = request.getParameter("buildingCode");
		TblUnitDimForm TblUnitForm = (TblUnitDimForm) form;
		
		String strurl = getTblMenuDimService().getMenuString("/unitDim.do?method=queryTblUnitDim");
		String where ="where 1=1 and comm_code="+ commCode +" and area_code="+areaCode
			+" and building_code="+buildingCode + " and unit_code=" + UnitCode;
		List list = getTblRoomDimService().findTblRoomDimByWhere(where);
		if(list!=null && list.size()>0){
			request.setAttribute("message", "单元信息下存在房号信息，请先删除房号信息！");
			request.setAttribute("backurl",
					"/unitDim.do?method=queryTblUnitDim"+strurl);
			return mapping.findForward("FAIL");
		}
		
		this.getTblUnitDimService().removeDim(buildingCode, areaCode, commCode,UnitCode);
		SysLogger.logInfo(request, "生成单元信息,单元编号："+UnitCode+",区域编号："+areaCode+",楼宇编号："+buildingCode+",小区编号："+commCode);
		request.setAttribute("message", "删除单元信息成功！");
		request.setAttribute("backurl", "/unitDim.do?method=queryTblUnitDim"+strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblUnitDim")
				.getPath());
		return mapping.findForward("FAIL");
	}
	
	public ActionForward findUnitDimList(ActionMapping mapping, ActionForm form,
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
		List<TblUnitDimBo> boList = getTblUnitDimService().findTblUnitDimByWhere(whereStr);
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
