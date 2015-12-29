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
import com.viatt.zhjtpro.bo.TblNoticeDimBo;
import com.viatt.zhjtpro.bo.TblRoomDimBo;
import com.viatt.zhjtpro.bo.TblUnitDimBo;
import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblNoticeDimForm;
import com.viatt.zhjtpro.service.ITblBuildingDimService;
import com.viatt.zhjtpro.service.ITblCommDimService;
import com.viatt.zhjtpro.service.ITblEquDimService;
import com.viatt.zhjtpro.service.ITblInfoStatusDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblNoticeDimService;
import com.viatt.zhjtpro.service.ITblRoomDimService;
import com.viatt.zhjtpro.service.ITblUnitDimService;

public class TblNoticeDimAction extends BaseAction {
	protected ITblNoticeDimService getTblNoticeDimService() {
		return (ITblNoticeDimService) getBean("tblNoticeDimService");
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

		if ("forAddTblNoticeDim".equals(method)) {
			return forSaveTblNoticeDim(mapping, form, request, response);
		} else if ("addTblNoticeDim".equals(method)) {
			return saveTblNoticeDim(mapping, form, request, response);
		} else if ("queryTblNoticeDim".equals(method)) {
			return queryTblNoticeDim(mapping, form, request, response);
		} else if ("queryTblNoticeDimIndex".equals(method)) {
			return queryTblNoticeDimIndex(mapping, form, request, response);
		} else if ("removeTblNoticeDim".equals(method)) {
			return removeTblNoticeDim(mapping, form, request, response);
		} else if ("viewTblNoticeDim".equals(method)) {
			return viewTblNoticeDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblNoticeDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblNoticeDimForm TblNoticeForm = (TblNoticeDimForm) form;
		
		String where="where 1=1 ";
		String strTitile = MyRequest.GetString(request, "strTitile");
		if(!strTitile.equals("")){
			where = where + " and titile like '%" + strTitile +"%' ";
		}
		String strCreateTime = MyRequest.GetString(request, "strCreateTime");
		if(!strCreateTime.equals("")){
			where = where + " and create_time like '%" + strCreateTime +"%' ";
		}
		request.setAttribute("strTitile", strTitile);
		request.setAttribute("strCreateTime", strCreateTime);

		int pageSize = TblNoticeForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblNoticeDims = getTblNoticeDimService().findTblNoticeDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblNoticeDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblNoticeDims", TblNoticeDims);

		return mapping.findForward("queryNoticeDim");

	}
	public ActionForward queryTblNoticeDimIndex(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception
	{
		TblNoticeDimForm TblNoticeForm = (TblNoticeDimForm) form;
		TblUserDimBo userInfo = (TblUserDimBo) request.getSession().getAttribute(
				TblUserDimBo.USER_INFO_REFERENCE);
		
		int pageSize = TblNoticeForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblNoticeDims = getTblNoticeDimService().findTblNoticeDim(beginIndex,
				pageSize, "");
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblNoticeDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblNoticeDims", TblNoticeDims);
		
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

		return mapping.findForward("queryTblNoticeDimIndex");

	}

	public ActionForward forSaveTblNoticeDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblNoticeDimForm TblNoticeForm = (TblNoticeDimForm) form;
		boolean isNew = TblNoticeForm.getNoticeCode() == null
				|| "".equals(TblNoticeForm.getNoticeCode());
		if (!isNew) {
			TblNoticeDimBo bo = this.getTblNoticeDimService().findTblNoticeDimById(
					TblNoticeForm.getNoticeCode());
			BeanUtils.copyProperties(TblNoticeForm, bo);
		}
		
		return mapping.findForward("addTblNoticeDim");
	}

	public ActionForward saveTblNoticeDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblNoticeDimForm TblNoticeForm = (TblNoticeDimForm) form;
		TblNoticeDimBo bo = new TblNoticeDimBo();
		boolean isNew = TblNoticeForm.getNoticeCode() == null
				|| "".equals(TblNoticeForm.getNoticeCode());
		String createTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String strurl = getTblMenuDimService().getMenuString("/noticeDim.do?method=queryTblNoticeDim");
		try {
			if (isNew) {
				BeanUtils.copyProperties(bo, TblNoticeForm);// 属性拷贝
				TblUserDimBo user = (TblUserDimBo)request.getSession().getAttribute(TblUserDimBo.USER_INFO_REFERENCE);
				bo.setNoticeCode(GetId.randomID());
				bo.setCreateTime(createTime);
				bo.setCreateUsr(user.getLogName());
				getTblNoticeDimService().save(bo);
				
				List list = getTblEquDimService().findTblEquDimByWhere(" where equ_type='"+ bo.getEquType()+"'");
				if(list!=null && list.size()>0){
					for(int i=0;i<list.size();i++){
						TblEquDimBo equ = (TblEquDimBo)list.get(i);
						TblInfoStatusDimBo info = new TblInfoStatusDimBo();
						info.setEquCode(equ.getEquCode());
						info.setInfoCode(bo.getNoticeCode());
						info.setInfoType("n");
						info.setSendZt("0");
						info.setUpdType("0");
						info.setInfoTitle(bo.getTitile()) ;
						getTblInfoStatusDimService().save(info);
					}
				}
				SysLogger.logInfo(request, "添加公告信息,公告编号："+bo.getNoticeCode());
				request.setAttribute("message", "添加公告信息成功！");
				request.setAttribute("backurl",
						"/noticeDim.do?method=queryTblNoticeDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, TblNoticeForm);// 属性拷贝
				bo.setCreateTime(createTime);
				getTblNoticeDimService().save(bo);
				List list = getTblEquDimService().findTblEquDimByWhere(" where equ_type='"+ bo.getEquType()+"'");
				if(list!=null && list.size()>0){
					for(int i=0;i<list.size();i++){
						TblEquDimBo equ = (TblEquDimBo)list.get(i);
						TblInfoStatusDimBo info = new TblInfoStatusDimBo();
						info.setEquCode(equ.getEquCode());
						info.setInfoCode(bo.getNoticeCode());
						info.setInfoType("n");
						info.setSendZt("0");
						info.setUpdType("1");
						info.setInfoTitle(bo.getTitile()) ;
						getTblInfoStatusDimService().save(info);
					}
				}
				SysLogger.logInfo(request, "修改公告信息,公告编号："+bo.getNoticeCode());
				request.setAttribute("message", "修改公告信息成功！");
				request.setAttribute("backurl",
						"/noticeDim.do?method=queryTblNoticeDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/noticeDim.do?method=queryTblNoticeDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblNoticeDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String NoticeCode = request.getParameter("noticeCode");
		TblNoticeDimForm TblNoticeForm = (TblNoticeDimForm) form;
		TblNoticeDimBo bo = getTblNoticeDimService().findTblNoticeDimById(NoticeCode);
		this.getTblNoticeDimService().removeNoticeDim(NoticeCode);
		String strurl = getTblMenuDimService().getMenuString("/noticeDim.do?method=queryTblNoticeDim");
		
		//删除信息，同时删除信息发送表tbl_infostatus_dim的新增修改
		getTblInfoStatusDimService().deleteList(" where info_code='"+bo.getNoticeCode()+"' and info_type='n'");
		
		List list = getTblEquDimService().findTblEquDimByWhere(" where equ_type='"+ bo.getEquType()+"'");
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				TblEquDimBo equ = (TblEquDimBo)list.get(i);
				TblInfoStatusDimBo info = new TblInfoStatusDimBo();
				info.setEquCode(equ.getEquCode());
				info.setInfoCode(bo.getNoticeCode());
				info.setInfoType("n");
				info.setSendZt("0");
				info.setUpdType("2");
				getTblInfoStatusDimService().save(info);
			}
		}
		SysLogger.logInfo(request, "删除公告信息,公告编号："+NoticeCode);
		request.setAttribute("message", "删除公告信息成功！");
		request.setAttribute("backurl",
				"/noticeDim.do?method=queryTblNoticeDim"+strurl);
		return mapping.findForward("SUCCESS");
	}
	
	public ActionForward viewTblNoticeDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String NoticeCode = request.getParameter("noticeCode");
		TblNoticeDimForm TblNoticeForm = (TblNoticeDimForm) form;
		TblNoticeDimBo bo = this.getTblNoticeDimService().findTblNoticeDimById(NoticeCode);
		if(bo==null){
			String strurl = getTblMenuDimService().getMenuString("/noticeDim.do?method=queryTblNoticeDim");
			request.setAttribute("message", "该公告信息已删除");
			request.setAttribute("backurl", "/noticeDim.do?method=queryTblNoticeDim"+strurl);
			return mapping.findForward("FAIL");
		}
		BeanUtils.copyProperties(TblNoticeForm, bo);
		return mapping.findForward("viewTblNoticeDim");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblNoticeDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
