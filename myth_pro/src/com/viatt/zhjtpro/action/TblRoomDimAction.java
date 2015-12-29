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

import com.viatt.zhjtpro.bo.TblCommDimBo;
import com.viatt.zhjtpro.bo.TblOwnerDimBo;
import com.viatt.zhjtpro.bo.TblRoomDimBo;
import com.viatt.zhjtpro.bo.TblUnitDimBo;
import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblRoomDimForm;
import com.viatt.zhjtpro.service.ITblAreaDimService;
import com.viatt.zhjtpro.service.ITblBuildingDimService;
import com.viatt.zhjtpro.service.ITblCommDimService;
import com.viatt.zhjtpro.service.ITblHousetypeDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblOwnerDimService;
import com.viatt.zhjtpro.service.ITblRoomDimService;
import com.viatt.zhjtpro.service.ITblUnitDimService;

import net.sf.json.JSONArray;

public class TblRoomDimAction extends BaseAction
{
	protected ITblBuildingDimService getTblBuildingDimService() {
		return (ITblBuildingDimService) getBean("tblBuildingDimService");
	}

	protected ITblUnitDimService getTblUnitDimService() {
		return (ITblUnitDimService) getBean("tblUnitDimService");
	}

	protected ITblRoomDimService getTblRoomDimService() {
		return (ITblRoomDimService) getBean("tblRoomDimService");
	}

	protected ITblAreaDimService getTblAreaDimService() {
		return (ITblAreaDimService) getBean("tblAreaDimService");
	}

	protected ITblCommDimService getTblCommDimService() {
		return (ITblCommDimService) getBean("tblCommDimService");
	}

	protected ITblHousetypeDimService getTblHousetypeDimService() {
		return (ITblHousetypeDimService) getBean("tblHousetypeDimService");
	}

	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	protected ITblOwnerDimService getTblOwnerDimService() {
		return (ITblOwnerDimService) getBean("tblOwnerDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblRoomDim".equals(method)) {
			return forSaveTblRoomDim(mapping, form, request, response);
		} else if ("addTblRoomDim".equals(method)) {
			return saveTblRoomDim(mapping, form, request, response);
		} else if ("queryTblRoomDim".equals(method)) {
			return queryTblRoomDim(mapping, form, request, response);
		} else if ("removeTblRoomDim".equals(method)) {
			return removeTblRoomDim(mapping, form, request, response);
		} else if ("viewTblRoomDim".equals(method)) {
			return viewTblRoomDim(mapping, form, request, response);
		} else if ("genRoomDim".equals(method)) {
			return genRoomDim(mapping, form, request, response);
		} else if ("addBatchRoomDim".equals(method)) {
			return saveBatchRoomDim(mapping, form, request, response);
		} else if ("checkRoomGen".equals(method)) {
			return checkRoomGen(mapping, form, request, response);
		} else if ("getJsonRoomInfo".equals(method)) {
			return getJsonRoomInfo(mapping, form, request, response) ;
		}  else if ("getJsonOutdoorInfo".equals(method)) {
			return getJsonOutdoorInfo(mapping, form, request, response) ;
		}  else if ("findRoomDimList".equals(method)) {
			return findRoomDimList(mapping, form, request, response);
		}
		else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblRoomDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception
	{
		TblRoomDimForm TblRoomForm = (TblRoomDimForm) form;
		TblUserDimBo userInfo = (TblUserDimBo) request.getSession().getAttribute(
				TblUserDimBo.USER_INFO_REFERENCE);
		
		String where = "where 1=1 ";
		String strRoomUsr = MyRequest.GetString(request, "strRoomUsr");
		if (!strRoomUsr.equals("")) {
			where = where + " and room_usr =" + strRoomUsr + " ";
		}
		String strRoomState = MyRequest.GetString(request, "strRoomState");
		if (!strRoomState.equals("")) {
			where = where + " and room_state=" + strRoomState + " ";
		}
		
		if (StringUtils.isNotEmpty(userInfo.getPropertyId())) {
			
			List<TblCommDimBo> list = getTblCommDimService().findTblCommDimByWhere("where comm_pro = '" + userInfo.getPropertyId() + "'");
			String str = "";
			for (TblCommDimBo bo : list)
			{
				str += "'" + bo.getCommCode() + "',";
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
		}
		
		request.setAttribute("strRoomUsr", strRoomUsr);
		request.setAttribute("strRoomState", strRoomState);

		int pageSize = TblRoomForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblRoomDims = getTblRoomDimService().findTblRoomDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblRoomDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblRoomDims", TblRoomDims);

		return mapping.findForward("queryRoomDim");

	}

	public ActionForward forSaveTblRoomDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblRoomDimForm TblRoomForm = (TblRoomDimForm) form;
		try {
			TblRoomDimBo bo = this.getTblRoomDimService().findTblRoomDimById(
					TblRoomForm.getBuildingCode(), TblRoomForm.getAreaCode(),
					TblRoomForm.getCommCode(), TblRoomForm.getUnitCode(),
					TblRoomForm.getRoomCode());
			BeanUtils.copyProperties(TblRoomForm, bo);
			
			List list = getTblHousetypeDimService().findTblHousetypeDimByWhere("");
			request.setAttribute("htlist", list);
			
			List<TblOwnerDimBo> ownlist = getTblOwnerDimService().findTblOwnerDimByWhere("", "");
			request.setAttribute("ownlist", ownlist);
			
			return mapping.findForward("addTblRoomDim");
		}
		catch (Exception e)
		{
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/roomDim.do?method=queryTblRoomDim");
			return mapping.findForward("FAIL");
		}
	}

	public ActionForward saveTblRoomDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblRoomDimForm TblRoomForm = (TblRoomDimForm) form;
		TblRoomDimBo bo = new TblRoomDimBo();

		String strurl = getTblMenuDimService().getMenuString(
				"/roomDim.do?method=queryTblRoomDim");
		try {
			BeanUtils.copyProperties(bo, TblRoomForm);// 属性拷贝
			getTblRoomDimService().save(bo);
			
			SysLogger.logInfo(request, "修改房间信息,房号："+bo.getRoomCode());
			request.setAttribute("message", "修改房间信息成功！");
			request.setAttribute("backurl",
					"/roomDim.do?method=queryTblRoomDim" + strurl);
			return mapping.findForward("SUCCESS");
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/roomDim.do?method=queryTblRoomDim" + strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward saveBatchRoomDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblRoomDimForm TblRoomForm = (TblRoomDimForm) form;
		TblRoomDimBo bo = new TblRoomDimBo();

		String strurl = getTblMenuDimService().getMenuString(
				"/roomDim.do?method=queryTblRoomDim");
		String commCode = MyRequest.GetString(request, "commCode");
		String unitCode = MyRequest.GetString(request, "unitCode");
		String areaCode = MyRequest.GetString(request, "areaCode");
		String buildingCode = MyRequest.GetString(request, "buildingCode");
		String RoomCode = MyRequest.GetString(request, "roomCode");
		String htCode = MyRequest.GetString(request, "htCode");
		try {
			String[] sRoomCode = RoomCode.split(",");
			String[] shtCode = htCode.split(",");
			for (int i = 0; i < sRoomCode.length; i++) {
				bo = new TblRoomDimBo();
				bo.setBuildingCode(buildingCode);
				bo.setUnitCode(unitCode);
				bo.setCommCode(commCode);
				bo.setAreaCode(areaCode);
				bo.setRoomCode(sRoomCode[i]);
				bo.setHtCode(shtCode[i]);
				getTblRoomDimService().save(bo);
			}
			SysLogger.logInfo(request, "生成房号信息,单元号："+unitCode+",楼宇号："+buildingCode+"区域号："+areaCode+"小区号："+commCode);
			request.setAttribute("message", "生成房号信息成功！");
			request.setAttribute("backurl",
					"/roomDim.do?method=queryTblRoomDim" + strurl);
			return mapping.findForward("SUCCESS");

		} catch (Exception e) {
			request.setAttribute("message", "操作失败");
			request.setAttribute("backurl",
					"/roomDim.do?method=queryTblRoomDim" + strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward genRoomDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblRoomDimForm TblRoomForm = (TblRoomDimForm) form;
		String commCode = MyRequest.GetString(request, "commCode");
		String areaCode = MyRequest.GetString(request, "areaCode");
		String buildingCode = MyRequest.GetString(request, "buildingCode");
		String unitCode = MyRequest.GetString(request, "unitCode");
		String strurl = getTblMenuDimService().getMenuString(
				"/roomDim.do?method=queryTblRoomDim");
		if (commCode.equals("") || areaCode.equals("")
				|| buildingCode.equals("")) {
			request.setAttribute("message", "小区编号或区域编号为空！");
			request.setAttribute("backurl",
					"/unitDim.do?method=queryTblAreaDim" + strurl);
			return mapping.findForward("FAIL");
		}
		
		TblUnitDimBo bo = getTblUnitDimService().findTblUnitDimById(buildingCode,
				areaCode, commCode, unitCode);
		if (bo == null) {
			request.setAttribute("message", "小区编号[" + commCode + "],区域编号["
					+ areaCode + "]的区域不存在");
			request.setAttribute("backurl",
					"/unitDim.do?method=queryTblCommDim" + strurl);
			return mapping.findForward("FAIL");
		}

		List list = getTblHousetypeDimService().findTblHousetypeDimByWhere("");
		request.setAttribute("htlist", list);
		BeanUtils.copyProperties(TblRoomForm, bo);
		request.setAttribute("buildingCode", buildingCode);
		request.setAttribute("commCode", commCode);
		request.setAttribute("areaCode", areaCode);
		request.setAttribute("unitCode", unitCode);

		return mapping.findForward("forGenRoomDim");
	}

	public ActionForward checkRoomGen(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String commCode = MyRequest.GetString(request, "commCode");
		String areaCode = MyRequest.GetString(request, "areaCode");
		String BuildingCode = MyRequest.GetString(request, "buildingCode");
		String UnitCode = MyRequest.GetString(request, "unitCode");
		String RoomCode = MyRequest.GetString(request, "roomCode");

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String[] sRoomCode = RoomCode.split(",");
		for (int i = 0; i < sRoomCode.length; i++) {
			String where = " where room_code=" + sRoomCode[i]
					+ " and comm_code=" + commCode + " and area_code="
					+ areaCode + " and building_code=" + BuildingCode
					+ " and unit_code=" + UnitCode;
			List list = getTblRoomDimService().findTblRoomDimByWhere(where);
			if (list != null && list.size() > 0) {
				out.println("{msg:'房号【" + sRoomCode[i] + "】已存在'}");
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
	
	public ActionForward getJsonRoomInfo(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
	throws java.lang.Exception {
		String commCode = MyRequest.GetString(request, "commCode");
		String areaCode = MyRequest.GetString(request, "areaCode");
		String BuildingCode = MyRequest.GetString(request, "buildingCode");
		String UnitCode = MyRequest.GetString(request, "unitCode");
		String RoomCode = MyRequest.GetString(request, "roomCode");
		
//		getJsonRoomInfo(String roomCode, String areaCode, String commCode, String unitCode, String buildingCode)
		response.setCharacterEncoding("UTF-8");
		String jsonStr = getTblRoomDimService().getJsonRoomInfo(RoomCode, areaCode, commCode, UnitCode, BuildingCode);
		
		request.setAttribute("jsonStr", jsonStr);

		return mapping.findForward("JSON");
	}
	
	/**
	 * 门号信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward getJsonOutdoorInfo(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
	throws java.lang.Exception {
		String commCode = MyRequest.GetString(request, "commCode");
		String areaCode = MyRequest.GetString(request, "areaCode");
		String BuildingCode = MyRequest.GetString(request, "buildingCode");
		String UnitCode = MyRequest.GetString(request, "unitCode");
		String RoomCode = MyRequest.GetString(request, "roomCode");
		
//		getJsonRoomInfo(String roomCode, String areaCode, String commCode, String unitCode, String buildingCode)
		response.setCharacterEncoding("UTF-8");
		String jsonStr = getTblRoomDimService().getJsonOutdoorInfo(RoomCode, areaCode, commCode, UnitCode, BuildingCode);
		
		request.setAttribute("jsonStr", jsonStr);
		
		return mapping.findForward("JSON");
	}

	public ActionForward removeTblRoomDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String roomCode = request.getParameter("roomCode");
		String unitCode = request.getParameter("unitCode");
		String commCode = request.getParameter("commCode");
		String areaCode = request.getParameter("areaCode");
		String buildingCode = request.getParameter("buildingCode");
		TblRoomDimForm TblRoomForm = (TblRoomDimForm) form;
		String strurl = getTblMenuDimService().getMenuString(
				"/roomDim.do?method=queryTblRoomDim");
		this.getTblRoomDimService().removeDim(buildingCode, areaCode, commCode,
				unitCode, roomCode);
		SysLogger.logInfo(request, "删除房号,房号："+roomCode+",单元号："+unitCode+",楼宇号："+buildingCode+"区域号："+areaCode+"小区号："+commCode);
		request.setAttribute("message", "删除房号信息成功！");
		request.setAttribute("backurl", "/roomDim.do?method=queryTblRoomDim"
				+ strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward viewTblRoomDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String roomCode = request.getParameter("roomCode");
		String unitCode = request.getParameter("unitCode");
		String commCode = request.getParameter("commCode");
		String areaCode = request.getParameter("areaCode");
		String buildingCode = request.getParameter("buildingCode");
		TblRoomDimForm TblRoomForm = (TblRoomDimForm) form;
		
		TblRoomDimBo bo = getTblRoomDimService().findTblRoomDimById(buildingCode,
				areaCode, commCode, unitCode, roomCode);
		BeanUtils.copyProperties(TblRoomForm, bo);
		
		List list = getTblHousetypeDimService().findTblHousetypeDimByWhere("");
		request.setAttribute("htlist", list);
		
		List<TblOwnerDimBo> ownlist = getTblOwnerDimService().findTblOwnerDimByWhere("", "");
		request.setAttribute("ownlist", ownlist);
		
		return mapping.findForward("viewTblRoomDim");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblRoomDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

	public ActionForward findRoomDimList(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception 
	{
		String commCode = MyRequest.GetString(request, "commCode");
		String areaCode = MyRequest.GetString(request, "areaCode");
		String buildingCode = MyRequest.GetString(request, "buildingCode");
		String unitCode = MyRequest.GetString(request, "unitCode");
		if (StringUtils.isEmpty(commCode))
		{
			return mapping.findForward("null");
		}
		String whereStr = "where comm_code = '" + commCode + "'";
		if (StringUtils.isNotEmpty(areaCode))
		{
			whereStr += "and area_code = '" + areaCode + "'";
		}
		if (StringUtils.isNotEmpty(buildingCode))
		{
			whereStr += "and building_code = '" + buildingCode + "'";
		}
		if (StringUtils.isNotEmpty(unitCode))
		{
			whereStr += "and unit_code = '" + unitCode + "'";
		}
		List<TblRoomDimBo> boList = getTblRoomDimService().findTblRoomDimByWhere(whereStr);
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
