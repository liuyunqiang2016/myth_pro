package com.viatt.zhjtpro.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblInnerEquOpBo;
import com.viatt.zhjtpro.bo.TblInnerStatusDimBo;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblEquDimForm;
import com.viatt.zhjtpro.service.ITblEquDimService;
import com.viatt.zhjtpro.service.ITblInnerEquOpService;
import com.viatt.zhjtpro.service.ITblInnerStatusDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblRoomDimService;

public class TblInnerEquOpAction extends BaseAction{
	
	protected ITblEquDimService getTblEquDimService() {
		return (ITblEquDimService) getBean("tblEquDimService");
	}
	
	protected ITblRoomDimService getTblRoomDimService() {
		return (ITblRoomDimService) getBean("tblRoomDimService");
	}
	
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}
	
	protected ITblInnerEquOpService getTblInnerEquOpService() {
		return (ITblInnerEquOpService) getBean("tblInnerEquOpService");
	}
	protected ITblInnerStatusDimService getTblInnerStatusDimService() {
		return (ITblInnerStatusDimService) getBean("tblInnerStatusDimService");
	}
	
	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("queryOuterEquDim".equals(method)) {
			return queryOuterEquDim(mapping, form, request, response);
		}else if("queryInnerEquDim".equals(method)){
			return queryInnerEquDim(mapping, form, request, response);
		}else if("queryAssignInnerEquDim".equals(method)){
			return queryAssignInnerEquDim(mapping, form, request, response);
		}else if("saveInnerEquDim".equals(method)){
			return saveInnerEquDim(mapping, form, request, response);
		}else if("removeInnerEquDim".equals(method)){
			return removeInnerEquDim(mapping, form, request, response);
		}else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryOuterEquDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblEquDimForm TblEquForm = (TblEquDimForm) form;
		
		String where="where 1=1 and equ_type='d' ";
		String strEquCode = MyRequest.GetString(request, "strEquCode");
		if(!strEquCode.equals("")){
			where = where + " and equ_code like '%" + strEquCode +"%' ";
		}
		String strIpAdd = MyRequest.GetString(request, "strIpAdd");
		if(!strIpAdd.equals("")){
			where = where + " and ip_add like '%" + strIpAdd +"%' ";
		}
		request.setAttribute("strEquCode", strEquCode);
		request.setAttribute("strIpAdd", strIpAdd);

		int pageSize = TblEquForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblEquDims = getTblEquDimService().findTblEquDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblEquDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblEquDims", TblEquDims);
		List list = getTblRoomDimService().findVRoomDimByWhere("");
		request.setAttribute("roomList", list);

		return mapping.findForward("queryOuterEquDim");

	}
	
	public ActionForward queryInnerEquDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblEquDimForm TblEquForm = (TblEquDimForm) form;
		
		String where="where 1=1 and equ_type='s' ";
		String strEquCode = MyRequest.GetString(request, "strEquCode");
		if(!strEquCode.equals("")){
			where = where + " and equ_code like '%" + strEquCode +"%' ";
		}
		request.setAttribute("strEquCode", strEquCode);
		
		String outerEquCode = MyRequest.GetString(request, "outerEquCode");
		request.setAttribute("outerEquCode", outerEquCode);

		int pageSize = TblEquForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);
		
		List innerlist = getTblInnerEquOpService().findTblInnerEquOpByWhere(" where optype='1' and outerid="+outerEquCode);
		String equlist="";
		if(innerlist!=null && innerlist.size()>0){
			for(int i=0;i<innerlist.size();i++){
				TblInnerEquOpBo bo = (TblInnerEquOpBo)innerlist.get(i);
				equlist = equlist+bo.getInnerid()+",";
			}
			equlist = equlist.substring(0, equlist.length()-1);
			if(!equlist.equals(""))
				where = where + " and equ_code not in ("+equlist+")";
		}
		Page TblEquDims = getTblEquDimService().findTblEquDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblEquDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblEquDims", TblEquDims);
		List list = getTblRoomDimService().findVRoomDimByWhere("");
		request.setAttribute("roomList", list);
		
		return mapping.findForward("queryInnerEquDim");
	}
	
	public ActionForward queryAssignInnerEquDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblEquDimForm TblEquForm = (TblEquDimForm) form;
		
		String where="where 1=1 and equ_type='s' ";
		String strEquCode = MyRequest.GetString(request, "strEquCode");
		if(!strEquCode.equals("")){
			where = where + " and equ_code like '%" + strEquCode +"%' ";
		}
		request.setAttribute("strEquCode", strEquCode);
		
		String outerEquCode = MyRequest.GetString(request, "outerEquCode");
		request.setAttribute("outerEquCode", outerEquCode);

		int pageSize = TblEquForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);
		
		List innerlist = getTblInnerEquOpService().findTblInnerEquOpByWhere(" where optype='1' and outerid="+outerEquCode);
		String equlist="";
		if(innerlist!=null && innerlist.size()>0){
			for(int i=0;i<innerlist.size();i++){
				TblInnerEquOpBo bo = (TblInnerEquOpBo)innerlist.get(i);
				equlist = equlist+bo.getInnerid()+",";
			}
			equlist = equlist.substring(0, equlist.length()-1);
			if(!equlist.equals(""))
				where = where + " and equ_code in ("+equlist+")";
		}else{
			where =" where 1!=1";
		}
		Page TblEquDims = getTblEquDimService().findTblEquDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblEquDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblEquDims", TblEquDims);
		List list = getTblRoomDimService().findVRoomDimByWhere("");
		request.setAttribute("roomList", list);
		return mapping.findForward("queryAssignInnerEquDim");
	}
	
	public ActionForward saveInnerEquDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String outerId = request.getParameter("outerId");
		String equlist = request.getParameter("equlist");
		String equ[] = equlist.split(",");
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		for(int i=0;i<equ.length;i++){
			TblInnerEquOpBo bo = new TblInnerEquOpBo();
			bo.setOuterid(outerId);
			bo.setInnerid(equ[i]);
			bo.setOptype("1");
			getTblInnerEquOpService().save(bo);
			TblInnerStatusDimBo status = new TblInnerStatusDimBo();
			status.setInnerid(equ[i]);
			status.setOuterid(outerId);
			status.setOptype("1");
			status.setSendzt("0");
			status.setPertype("0");
			status.setBak1(sf.format(new Date()));
			getTblInnerStatusDimService().save(status);
		}
		String strurl = getTblMenuDimService().getMenuString("/innerEquOp.do?method=queryOuterEquDim");
		SysLogger.logInfo(request, "设置室内机开门权限,设备编号："+outerId);
		request.setAttribute("message", "设置室内机开门权限成功！");
		request.setAttribute("backurl",
				"/innerEquOp.do?method=queryOuterEquDim"+strurl);
		return mapping.findForward("SUCCESS");
	}
	
	public ActionForward removeInnerEquDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String outerId = request.getParameter("outerId");
		String equlist = request.getParameter("equlist");
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String equ[] = equlist.split(",");
		for(int i=0;i<equ.length;i++){
			getTblInnerEquOpService().delete(equ[i], outerId, "1");
			TblInnerStatusDimBo status = new TblInnerStatusDimBo();
			status.setInnerid(equ[i]);
			status.setOuterid(outerId);
			status.setOptype("1");
			status.setSendzt("0");
			status.setPertype("1");
			status.setBak1(sf.format(new Date()));
			getTblInnerStatusDimService().save(status);
		}
		String strurl = getTblMenuDimService().getMenuString("/innerEquOp.do?method=queryOuterEquDim");
		SysLogger.logInfo(request, "取消室内机开门权限,设备编号："+outerId);
		request.setAttribute("message", "取消室内机开门权限成功！");
		request.setAttribute("backurl",
				"/innerEquOp.do?method=queryOuterEquDim"+strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblEquDim")
				.getPath());
		return mapping.findForward("FAIL");
	}
}
