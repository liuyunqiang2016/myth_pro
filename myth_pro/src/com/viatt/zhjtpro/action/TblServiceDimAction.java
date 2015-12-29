package com.viatt.zhjtpro.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblBuildingDimBo;
import com.viatt.zhjtpro.bo.TblCommDimBo;
import com.viatt.zhjtpro.bo.TblEquDimBo;
import com.viatt.zhjtpro.bo.TblInfoStatusDimBo;
import com.viatt.zhjtpro.bo.TblRoomDimBo;
import com.viatt.zhjtpro.bo.TblServiceDimBo;
import com.viatt.zhjtpro.bo.TblUnitDimBo;
import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblServiceDimForm;
import com.viatt.zhjtpro.service.ITblBuildingDimService;
import com.viatt.zhjtpro.service.ITblCommDimService;
import com.viatt.zhjtpro.service.ITblEquDimService;
import com.viatt.zhjtpro.service.ITblInfoStatusDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblRoomDimService;
import com.viatt.zhjtpro.service.ITblServiceDimService;
import com.viatt.zhjtpro.service.ITblUnitDimService;

public class TblServiceDimAction extends BaseAction {
	protected ITblServiceDimService getTblServiceDimService() {
		return (ITblServiceDimService) getBean("tblServiceDimService");
	}
	protected ITblEquDimService getTblEquDimService() {
		return (ITblEquDimService) getBean("tblEquDimService");
	}
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}
	protected ITblInfoStatusDimService getTblInfoStatusDimService() {
		return (ITblInfoStatusDimService) getBean("tblInfoStatusDimService");
	}
	
	protected ITblCommDimService getTblCommDimService() {
		return (ITblCommDimService) getBean("tblCommDimService");
	}

	protected ITblBuildingDimService getTblBuildingDimService() {
		return (ITblBuildingDimService) getBean("tblBuildingDimService");
	}

	protected ITblUnitDimService getTblUnitDimService() {
		return (ITblUnitDimService) getBean("tblUnitDimService");
	}

	protected ITblRoomDimService getTblRoomDimService() {
		return (ITblRoomDimService) getBean("tblRoomDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblServiceDim".equals(method)) {
			return forSaveTblServiceDim(mapping, form, request, response);
		} else if ("addTblServiceDim".equals(method)) {
			return saveTblServiceDim(mapping, form, request, response);
		} else if ("queryTblServiceDim".equals(method)) {
			return queryTblServiceDim(mapping, form, request, response);
		} else if ("queryTblServiceDimForWarn".equals(method)) {
			return queryTblServiceDimForWarn(mapping, form, request, response);
		} else if ("queryTblServiceDimIndex".equals(method)) {
			return queryTblServiceDimIndex(mapping, form, request, response);
		} else if ("removeTblServiceDim".equals(method)) {
			return removeTblServiceDim(mapping, form, request, response);
		} else if ("viewTblServiceDim".equals(method)) {
			return viewTblServiceDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblServiceDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblServiceDimForm TblServiceForm = (TblServiceDimForm) form;
		
		String where="where 1=1 and type!='S001' ";
		String strCreateUsr = MyRequest.GetString(request, "strCreateUsr");
		if(!strCreateUsr.equals("")){
			where = where + " and create_usr like '%" + strCreateUsr +"%' ";
		}
		String strTitile = MyRequest.GetString(request, "strTitile");
		if(!strTitile.equals("")){
			where = where + " and titile like '%" + strTitile +"%' ";
		}
		String strChkUsr = MyRequest.GetString(request, "strChkUsr");
		if(!strChkUsr.equals("")){
			where = where + " and chk_usr like '%" + strChkUsr +"%' ";
		}
		String strChkState = MyRequest.GetString(request, "strChkState");
		if(!strChkState.equals("")){
			where = where + " and chk_state = '" + strChkState +"' ";
		}
		request.setAttribute("strCreateUsr", strCreateUsr);
		request.setAttribute("strTitile", strTitile);
		request.setAttribute("strChkUsr", strChkUsr);
		request.setAttribute("strChkState", strChkState);

		int pageSize = TblServiceForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblServiceDims = getTblServiceDimService().findTblServiceDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblServiceDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblServiceDims", TblServiceDims);

		return mapping.findForward("queryServiceDim");

	}
	
	public ActionForward queryTblServiceDimForWarn(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblServiceDimForm TblServiceForm = (TblServiceDimForm) form;
		
		String where="where 1=1 and type='S001' ";
		String strCreateUsr = MyRequest.GetString(request, "strCreateUsr");
		if(!strCreateUsr.equals("")){
			where = where + " and create_usr like '%" + strCreateUsr +"%' ";
		}
		String strTitile = MyRequest.GetString(request, "strTitile");
		if(!strTitile.equals("")){
			where = where + " and titile like '%" + strTitile +"%' ";
		}
		String strChkUsr = MyRequest.GetString(request, "strChkUsr");
		if(!strChkUsr.equals("")){
			where = where + " and chk_usr like '%" + strChkUsr +"%' ";
		}
		String strChkState = MyRequest.GetString(request, "strChkState");
		if(!strChkState.equals("")){
			where = where + " and chk_state = '" + strChkState +"' ";
		}
		request.setAttribute("strCreateUsr", strCreateUsr);
		request.setAttribute("strTitile", strTitile);
		request.setAttribute("strChkUsr", strChkUsr);
		request.setAttribute("strChkState", strChkState);

		int pageSize = TblServiceForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblServiceDims = getTblServiceDimService().findTblServiceDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblServiceDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblServiceDims", TblServiceDims);

		return mapping.findForward("queryServiceDimForWarn");

	}
	
	public ActionForward queryTblServiceDimIndex(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception
	{
		TblServiceDimForm TblServiceForm = (TblServiceDimForm) form;
		
		TblUserDimBo userInfo = (TblUserDimBo) request.getSession().getAttribute(
				TblUserDimBo.USER_INFO_REFERENCE);
		
		int pageSize = TblServiceForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblServiceDims = getTblServiceDimService().findTblServiceDim(beginIndex,
				pageSize, "");
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblServiceDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblServiceDims", TblServiceDims);
		
		String whereClause = StringUtils.isNotEmpty(userInfo.getPropertyId()) ? "where comm_pro = '" + userInfo.getPropertyId() + "'" : "";
		List<TblCommDimBo> comm = getTblCommDimService().findTblCommDimByWhere(whereClause);
		List<TblBuildingDimBo> build = getTblBuildingDimService().findTblBuildingDimByWhere(
				"");
		List<TblUnitDimBo> unit = getTblUnitDimService().findTblUnitDimByWhere("");
		List<TblRoomDimBo> room = getTblRoomDimService().findTblRoomDimByWhere("");
		List<TblEquDimBo> equ = getTblEquDimService().findTblEquDimByWhere(
				" where equ_state='1'");
		request.setAttribute("comm", new Integer(comm.size()));
		request.setAttribute("build", new Integer(build.size()));
		request.setAttribute("unit", new Integer(unit.size()));
		request.setAttribute("room", new Integer(room.size()));
		request.setAttribute("equ", new Integer(equ.size()));

		return mapping.findForward("queryServiceDimIndex");

	}

	public ActionForward forSaveTblServiceDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblServiceDimForm TblServiceForm = (TblServiceDimForm) form;
		boolean isNew = TblServiceForm.getSerCode() == null
				|| "".equals(TblServiceForm.getSerCode());
		if (!isNew) {
			TblServiceDimBo bo = this.getTblServiceDimService().findTblServiceDimById(
					TblServiceForm.getSerCode());
			BeanUtils.copyProperties(TblServiceForm, bo);
		}
		
		return mapping.findForward("addTblServiceDim");
	}

	public ActionForward saveTblServiceDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblServiceDimForm TblServiceForm = (TblServiceDimForm) form;
		TblServiceDimBo bo = new TblServiceDimBo();
		boolean isNew = TblServiceForm.getSerCode() == null
				|| "".equals(TblServiceForm.getSerCode());
		String createTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String strurl = getTblMenuDimService().getMenuString("/serviceDim.do?method=queryTblServiceDim");
		try {
			if (isNew) {
				BeanUtils.copyProperties(bo, TblServiceForm);// 属性拷贝
				bo.setSerCode(GetId.randomID());
				bo.setCreateTime(createTime);
				getTblServiceDimService().save(bo);
				SysLogger.logInfo(request, "添加服务信息,服务编号："+bo.getSerCode());
				request.setAttribute("message", "添加服务信息成功！");
				request.setAttribute("backurl",
						"/serviceDim.do?method=queryTblServiceDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, TblServiceForm);// 属性拷贝
				bo.setChkTime(createTime);
				TblUserDimBo user = (TblUserDimBo)request.getSession().getAttribute(TblUserDimBo.USER_INFO_REFERENCE);
				bo.setChkUsr(user.getLogName());
				getTblServiceDimService().save(bo);
				
				try{
				/*已处理，则推送到前端*/
				if(bo.getChkState().equals("01")){
					if(bo.getEquCode()!=null&&!bo.getEquCode().equals("")){
						TblInfoStatusDimBo info = new TblInfoStatusDimBo();
						info.setEquCode(bo.getEquCode());
						info.setInfoCode(bo.getSerId());
						info.setInfoType("s");
						info.setSendZt("0");
						info.setUpdType("1");
						info.setRemark(bo.getSerCode());
						info.setInfoTitle(bo.getTitile()) ;
						getTblInfoStatusDimService().save(info);
					}
				}
				}catch (Exception e1) {
					e1.printStackTrace();
				}
				SysLogger.logInfo(request, "修改服务信息,服务编号："+bo.getSerCode());
				request.setAttribute("message", "修改服务信息成功！");
				request.setAttribute("backurl",
						"/serviceDim.do?method=queryTblServiceDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/serviceDim.do?method=queryTblServiceDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblServiceDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String ServiceCode = request.getParameter("serCode");
		TblServiceDimForm TblServiceForm = (TblServiceDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/serviceDim.do?method=queryTblServiceDim");
		this.getTblServiceDimService().removeServiceDim(ServiceCode);
		SysLogger.logInfo(request, "删除服务信息,服务编号："+ServiceCode);
		request.setAttribute("message", "删除服务信息成功！");
		request.setAttribute("backurl",
				"/serviceDim.do?method=queryTblServiceDim"+strurl);
		return mapping.findForward("SUCCESS");
	}
	
	public ActionForward viewTblServiceDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String ServiceCode = request.getParameter("serCode");
		TblServiceDimForm TblServiceForm = (TblServiceDimForm) form;
		TblServiceDimBo bo = this.getTblServiceDimService().findTblServiceDimById(ServiceCode);
		if(bo==null){
			String strurl = getTblMenuDimService().getMenuString("/serviceDim.do?method=queryTblServiceDim");
			request.setAttribute("message", "该服务信息已删除");
			request.setAttribute("backurl",
					"/serviceDim.do?method=queryTblServiceDim"+strurl);
			return mapping.findForward("FAIL");
		}
		BeanUtils.copyProperties(TblServiceForm, bo);
		return mapping.findForward("viewTblServiceDim");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblServiceDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
