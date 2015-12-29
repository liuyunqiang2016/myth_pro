package com.viatt.zhjtpro.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblAreaDimBo;
import com.viatt.zhjtpro.bo.TblBuildingDimBo;
import com.viatt.zhjtpro.bo.TblCommDimBo;
import com.viatt.zhjtpro.bo.TblOwnerDimBo;
import com.viatt.zhjtpro.bo.TblRoomDimBo;
import com.viatt.zhjtpro.bo.TblSipAcountDimBo;
import com.viatt.zhjtpro.bo.TblUnitDimBo;
import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblOwnerDimForm;
import com.viatt.zhjtpro.service.ITblAreaDimService;
import com.viatt.zhjtpro.service.ITblBuildingDimService;
import com.viatt.zhjtpro.service.ITblCardDimService;
import com.viatt.zhjtpro.service.ITblCommDimService;
import com.viatt.zhjtpro.service.ITblFingDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblOwnerDimService;
import com.viatt.zhjtpro.service.ITblRoomDimService;
import com.viatt.zhjtpro.service.ITblSipAcountDimService;
import com.viatt.zhjtpro.service.ITblUnitDimService;

public class TblOwnerDimAction extends BaseAction
{
	
	protected ITblAreaDimService getTblAreaDimService() {
		return (ITblAreaDimService) getBean("tblAreaDimService");
	}
	protected ITblUnitDimService getTblUnitDimService() {
		return (ITblUnitDimService) getBean("tblUnitDimService");
	}
	protected ITblBuildingDimService getTblBuildingDimService() {
		return (ITblBuildingDimService) getBean("tblBuildingDimService");
	}
	protected ITblCommDimService getTblCommDimService() {
		return (ITblCommDimService) getBean("tblCommDimService");
	}
	protected ITblSipAcountDimService getTblSipAcountDimService() {
		return (ITblSipAcountDimService) getBean("tblSipAcountDimService");
	}
	protected ITblOwnerDimService getTblOwnerDimService() {
		return (ITblOwnerDimService) getBean("tblOwnerDimService");
	}
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}
	protected ITblRoomDimService getTblRoomDimService() {
		return (ITblRoomDimService) getBean("tblRoomDimService");
	}
	protected ITblCardDimService getTblCardDimService() {
		return (ITblCardDimService) getBean("tblCardDimService");
	}
	protected ITblFingDimService getTblFingDimService() {
		return (ITblFingDimService) getBean("tblFingDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblOwnerDim".equals(method)) {
			return forSaveTblOwnerDim(mapping, form, request, response);
		} else if ("addTblOwnerDim".equals(method)) {
			return saveTblOwnerDim(mapping, form, request, response);
		} else if ("queryTblOwnerDim".equals(method)) {
			return queryTblOwnerDim(mapping, form, request, response);
		} else if ("removeTblOwnerDim".equals(method)) {
			return removeTblOwnerDim(mapping, form, request, response);
		} else if ("viewTblOwnerDim".equals(method)) {
			return viewTblOwnerDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblOwnerDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblOwnerDimForm TblOwnerForm = (TblOwnerDimForm) form;
		
		String where="where 1=1 ";
		String strOwnerName = MyRequest.GetString(request, "strOwnerName");
		if(!strOwnerName.equals("")){
			where = where + " and owner_name like '%" + strOwnerName +"%' ";
		}
		String strOwnerNo = MyRequest.GetString(request, "strOwnerNo");
		if(!strOwnerNo.equals("")){
			where = where + " and owner_no like '%" + strOwnerNo +"%' ";
		}
		request.setAttribute("strOwnerName", strOwnerName);
		request.setAttribute("strOwnerNo", strOwnerNo);

		int pageSize = TblOwnerForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblOwnerDims = getTblOwnerDimService().findTblOwnerDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblOwnerDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblOwnerDims", TblOwnerDims);

		return mapping.findForward("queryOwnerDim");

	}

	public ActionForward forSaveTblOwnerDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblOwnerDimForm TblOwnerForm = (TblOwnerDimForm) form;
		boolean isNew = TblOwnerForm.getOwnerCode() == null
				|| "".equals(TblOwnerForm.getOwnerCode());
		if (!isNew) {
			TblOwnerDimBo bo = this.getTblOwnerDimService().findTblOwnerDimById(
					TblOwnerForm.getOwnerCode());
			BeanUtils.copyProperties(TblOwnerForm, bo);
		}
		
		//得到登录管理员（权限管理）
		TblUserDimBo userBo = (TblUserDimBo) request.getSession().getAttribute(TblUserDimBo.USER_INFO_REFERENCE);
		String where = " where 1=1 ";
		if (StringUtils.isNotEmpty(userBo.getPropertyId()))
		{
			where += "and comm_pro = '" + userBo.getPropertyId() + "'";
		}
		List<TblCommDimBo> commDimList = getTblCommDimService().findTblCommDimByWhere(where);
		request.setAttribute("commDimList", commDimList);
		
		return mapping.findForward("addTblOwnerDim");
	}

	public ActionForward saveTblOwnerDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception 
	{
		TblOwnerDimForm TblOwnerForm = (TblOwnerDimForm) form;
		TblOwnerDimBo bo = new TblOwnerDimBo();
		boolean isNew = TblOwnerForm.getOwnerCode() == null
				|| "".equals(TblOwnerForm.getOwnerCode());
		
		String strurl = getTblMenuDimService().getMenuString("/ownerDim.do?method=queryTblOwnerDim");
		try {
			if (isNew) 
			{
				BeanUtils.copyProperties(bo, TblOwnerForm);// 属性拷贝
//				bo.setOwnerCode(getTblOwnerDimService().getMaxId());
				
				//从数据库查询用户输入的身份证号是否使用
				List<TblOwnerDimBo> ownlist = getTblOwnerDimService().findTblOwnerDimByWhere("owner_no", TblOwnerForm.getOwnerNo());
				if (null != ownlist && ownlist.size() > 0)
				{
					request.setAttribute("message", "此身份证号码已经被注册，请重新输入!");
					request.setAttribute("backurl", "/ownerDim.do?method=queryTblOwnerDim"+strurl);
					return mapping.findForward("FAIL");
				}
				
				//是否生成sip账号
				if ("0".equals(TblOwnerForm.getIsSipAcount()))
				{
					TblSipAcountDimBo sipAcountBo = new TblSipAcountDimBo();
					sipAcountBo.setAcountId(GetId.randomID());
					sipAcountBo.setCommPro(TblOwnerForm.getCommCode());
					sipAcountBo.setGroupId(TblOwnerForm.getCommCode() + TblOwnerForm.getCommCode() +TblOwnerForm.getCommCode());
					sipAcountBo.setPassword(TblOwnerForm.getOwnerMob());
					sipAcountBo.setRemark("手机");
					sipAcountBo.setType("手机");
					sipAcountBo.setUsername(TblOwnerForm.getOwnerMob());
					getTblSipAcountDimService().save(sipAcountBo);
				}
				
				String roomInfo = "";
				if (StringUtils.isNotEmpty(TblOwnerForm.getCommCode()))
				{
					TblCommDimBo tblCommDimBo = getTblCommDimService().findTblCommDimById(TblOwnerForm.getCommCode());
					if (null != tblCommDimBo)
					{
						roomInfo += tblCommDimBo.getCommName();
					}
				}
				if (StringUtils.isNotEmpty(TblOwnerForm.getAreaCode()))
				{
					TblAreaDimBo tblAreaDimBo = getTblAreaDimService().findTblAreaDimById(TblOwnerForm.getAreaCode(), TblOwnerForm.getCommCode());
					if (null != tblAreaDimBo)
					{
						roomInfo += tblAreaDimBo.getAreaName();
					}
				}
				if (StringUtils.isNotEmpty(TblOwnerForm.getUnitCode()))
				{
					TblUnitDimBo tblUnitDimBo = getTblUnitDimService().findTblUnitDimById(TblOwnerForm.getBuildingCode(), TblOwnerForm.getAreaCode(), TblOwnerForm.getCommCode(), TblOwnerForm.getUnitCode());
					if (null != tblUnitDimBo)
					{
						roomInfo += tblUnitDimBo.getUnitName();
					}
				}
				if (StringUtils.isNotEmpty(TblOwnerForm.getBuildingCode()))
				{
					TblBuildingDimBo tblBuildingDimBo = getTblBuildingDimService().findTblBuildingDimById(TblOwnerForm.getBuildingCode(), TblOwnerForm.getAreaCode(), TblOwnerForm.getCommCode());
					if (null != tblBuildingDimBo)
					{
						roomInfo += tblBuildingDimBo.getBuildingName();
					}
				}
				if (StringUtils.isNotEmpty(TblOwnerForm.getRoomId()))
				{
					TblRoomDimBo tblRoomDimBo = getTblRoomDimService().findTblRoomDimById(TblOwnerForm.getBuildingCode(), TblOwnerForm.getAreaCode(), TblOwnerForm.getCommCode(), TblOwnerForm.getUnitCode(), TblOwnerForm.getRoomId());
					if (null != tblRoomDimBo)
					{
						roomInfo += tblRoomDimBo.getRoomCode() + "房";
					}
				}
				
				
				
				bo.setRoomInfo(roomInfo);
				bo.setOwnerCode(GetId.randomID());
				getTblOwnerDimService().save(bo);
				SysLogger.logInfo(request, "添加业主信息,业主编号："+bo.getOwnerCode());
				request.setAttribute("message", "添加业主信息成功！");
				request.setAttribute("backurl", "/ownerDim.do?method=queryTblOwnerDim"+strurl);
				return mapping.findForward("SUCCESS");
			} 
			else
			{
				BeanUtils.copyProperties(bo, TblOwnerForm);// 属性拷贝
				getTblOwnerDimService().save(bo);
				SysLogger.logInfo(request, "修改业主信息,业主编号："+bo.getOwnerCode());
				request.setAttribute("message", "修改业主信息成功！");
				request.setAttribute("backurl", "/ownerDim.do?method=queryTblOwnerDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/ownerDim.do?method=queryTblOwnerDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblOwnerDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String OwnerCode = request.getParameter("ownerCode");
		TblOwnerDimForm TblOwnerForm = (TblOwnerDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/ownerDim.do?method=queryTblOwnerDim");
		this.getTblOwnerDimService().removeDim(OwnerCode);
		SysLogger.logInfo(request, "删除业主信息,业主编号："+OwnerCode);
		request.setAttribute("message", "删除业主信息成功！");
		request.setAttribute("backurl",
				"/ownerDim.do?method=queryTblOwnerDim"+strurl);
		return mapping.findForward("SUCCESS");
	}
	public ActionForward viewTblOwnerDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String OwnerCode = request.getParameter("ownerCode");
		TblOwnerDimForm TblOwnerForm = (TblOwnerDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/ownerDim.do?method=queryTblOwnerDim");
		TblOwnerDimBo bo = getTblOwnerDimService().findTblOwnerDimById(OwnerCode);
		if(bo==null){
			request.setAttribute("message", "业主编号"+OwnerCode+"的信息不存在！");
			request.setAttribute("backurl",
					"/ownerDim.do?method=queryTblOwnerDim"+strurl);
			return mapping.findForward("FAIL");
		}
		BeanUtils.copyProperties(TblOwnerForm, bo);
		
		List room = getTblRoomDimService().findTblRoomDimByWhere(" where owner_code='"+bo.getOwnerCode()+"'");
		List card = getTblCardDimService().findTblCardDimByWhere(" where user_no='"+ bo.getOwnerNo()+"'");
		List fing = getTblFingDimService().findTblFingDimByWhere(" where usr_no='"+ bo.getOwnerNo()+"'");
		
		request.setAttribute("roomList", room);
		request.setAttribute("cardList", card);
		request.setAttribute("fingList", fing);
		return mapping.findForward("viewTblOwnerDim");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblOwnerDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
