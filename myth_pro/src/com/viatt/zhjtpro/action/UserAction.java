package com.viatt.zhjtpro.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblBuildingDimBo;
import com.viatt.zhjtpro.bo.TblCommDimBo;
import com.viatt.zhjtpro.bo.TblEquDimBo;
import com.viatt.zhjtpro.bo.TblMenuDimBo;
import com.viatt.zhjtpro.bo.TblRoomDimBo;
import com.viatt.zhjtpro.bo.TblUnitDimBo;
import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.CryptionData;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblUserDimForm;
import com.viatt.zhjtpro.service.ITblBuildingDimService;
import com.viatt.zhjtpro.service.ITblCommDimService;
import com.viatt.zhjtpro.service.ITblEquDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblNoticeDimService;
import com.viatt.zhjtpro.service.ITblRolemenuDimService;
import com.viatt.zhjtpro.service.ITblRoomDimService;
import com.viatt.zhjtpro.service.ITblServiceDimService;
import com.viatt.zhjtpro.service.ITblUnitDimService;
import com.viatt.zhjtpro.service.ITblUserDimService;

public class UserAction extends BaseAction
{

	protected ITblUserDimService getUserDimService() {
		return (ITblUserDimService) getBean("tblUserDimService");
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

	protected ITblServiceDimService getTblServiceDimService() {
		return (ITblServiceDimService) getBean("tblServiceDimService");
	}

	protected ITblNoticeDimService getTblNoticeDimService() {
		return (ITblNoticeDimService) getBean("tblNoticeDimService");
	}

	protected ITblEquDimService getTblEquDimService() {
		return (ITblEquDimService) getBean("tblEquDimService");
	}

	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	protected ITblRolemenuDimService getTblRolemenuDimService() {
		return (ITblRolemenuDimService) getBean("tblRolemenuDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("login".equals(method))
			return login(mapping, form, request, response);
		else if ("logout".equals(method))
			return logout(mapping, form, request, response);
		else if ("edit".equals(method))
			return edit(mapping, form, request, response);
		else if ("save".equals(method))
			return save(mapping, form, request, response);
		else if ("findPassword".equals(method))
			return findPassword(mapping, form, request, response);
		else if ("changePw".equals(method))
			return changePw(mapping, form, request, response);
		else if ("loginindex".equals(method))
			return loginindex(mapping, form, request, response);
		else if ("updatePw".equals(method))
			return updatePw(mapping, form, request, response);
		else
			return unspecified(mapping, form, request, response);
	}

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TblUserDimForm userForm = (TblUserDimForm) form;
		String securityCode = (String) request.getSession().getAttribute(
				"securityCode");
		if (!securityCode.toUpperCase().equals(userForm.getAuthCode().toUpperCase())) {
			request.setAttribute("message", "对不起，验证码错误！");
			request.setAttribute("logName", userForm.getLogName());
			request.setAttribute("logPwd", userForm.getLogPwd());
			return mapping.findForward("LOGIN");
		}
		boolean login = this.getUserDimService().login(userForm.getLogName(),
				userForm.getLogPwd());
		if (login) {
			/* 保存登录用户的信息（基本信息+可访问菜单+可访问资源） */
			TblUserDimBo userInfo = this.getUserDimService().getLoginUserInfo(
					userForm.getLogName());
			if (userInfo.getUserState().equals("0")) {
				request.setAttribute("message", "用户已被禁用，请联系管理员");
				return mapping.findForward("LOGIN");
			}
			request.getSession().setAttribute(TblUserDimBo.USER_INFO_REFERENCE,
					userInfo);

			String whereClause = StringUtils.isNotEmpty(userInfo.getPropertyId()) ? "where comm_pro = '" + userInfo.getPropertyId() + "'" : "";
			List<TblCommDimBo> comm = getTblCommDimService().findTblCommDimByWhere(whereClause);
			
			if (null != comm && comm.size() > 0)
			{
				StringBuilder sb = new StringBuilder();
				for (TblCommDimBo tblCommDimBo : comm)
				{
					sb.append("'");
					sb.append(tblCommDimBo.getCommCode());
					sb.append("'");
					sb.append(",");
				}
				if (sb.toString().contains(","))
				{
					sb.deleteCharAt(sb.length()-1);
				}
				
				String commonWhere = (sb.toString().length() > 0) ? "where comm_code in(" + sb.toString() + ")" : "";
				List<TblBuildingDimBo> build = getTblBuildingDimService().findTblBuildingDimByWhere(commonWhere);
				List<TblUnitDimBo> unit = getTblUnitDimService().findTblUnitDimByWhere(commonWhere);
				List<TblRoomDimBo> room = getTblRoomDimService().findTblRoomDimByWhere(commonWhere);
				
				String equDimWhere = (sb.toString().length() > 0) ? " where equ_state='1' and comm_code in(" + sb.toString() + ")" : " where equ_state='1'";
				List<TblEquDimBo> equ = getTblEquDimService().findTblEquDimByWhere(equDimWhere);
				request.setAttribute("comm", new Integer(comm.size()));
				request.setAttribute("build", new Integer(build.size()));
				request.setAttribute("unit", new Integer(unit.size()));
				request.setAttribute("room", new Integer(room.size()));
				request.setAttribute("equ", new Integer(equ.size()));
			}
			else
			{
				request.setAttribute("comm", 0);
				request.setAttribute("build", 0);
				request.setAttribute("unit", 0);
				request.setAttribute("room", 0);
				request.setAttribute("equ", 0);
			}
			

			// 公告信息
			Page TblServiceDims = getTblServiceDimService().findTblServiceDim(0, 15, "");
			List serv = TblServiceDims.getList();
			Page tblnotic = getTblNoticeDimService().findTblNoticeDim(0, 15, "");
			List notic = tblnotic.getList();
			request.setAttribute("service", serv);
			request.setAttribute("notice", notic);

			List rolemenu = getTblRolemenuDimService()
					.findTblRolemenuDimByWhere(
							" where role_code=" + userInfo.getRoleCode());
			List<TblMenuDimBo> firstmenu = getTblMenuDimService().findTblMenuDimByWhere(
					" where menu_lev='1'");
			TblMenuDimBo systemBo = null;
			for (TblMenuDimBo bo : firstmenu)
			{
				if ("系统管理".equals(bo.getMenuName()))
				{
					systemBo = bo;
					break;
				}
			}
			firstmenu.remove(systemBo);//移除系统管理
			firstmenu.add(systemBo);//把系统管理加载到最后
			
			List secmenu = getTblMenuDimService().findTblMenuDimByWhere(
					" where menu_lev='2'");
			request.getSession().setAttribute("firstmenu", firstmenu);
			request.getSession().setAttribute("rolemenu", rolemenu);
			request.getSession().setAttribute("secmenu", secmenu);

			// 如果登录的时间已过期(30天之外)，则直接跳转到修改密码页
			// SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			// Date passDate = format.parse(userInfo.getPassTime());
			// Date currentDate = new Date();
			// if (passDate.before(currentDate))
			// return mapping.findForward("CHPW");

			return mapping.findForward("loginindex");
		} else {
			/* 未成功登录 */
			request.setAttribute("message", "对不起，用户名或密码输错！");
			return mapping.findForward("LOGIN");
		}
	}

	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().removeAttribute(TblUserDimBo.USER_INFO_REFERENCE);
		return mapping.findForward("LOGOUT");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("edit");
	}

	public ActionForward findPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TblUserDimForm userForm = (TblUserDimForm) form;
		List<TblUserDimBo> userList = this.getUserDimService().findTblUserDimByWhere("log_name = '" + userForm.getLogName() + "'");
		if (null == userList)
		{
			request.setAttribute("logName", userForm.getLogName());
			request.setAttribute("emailAddress", userForm.getEmailAddress());
			request.setAttribute("telNum", userForm.getTelNum());
			request.setAttribute("message", "不存在此帐号信息");
			return mapping.findForward("FINDPASSWORD");
		}
		
		boolean login = this.getUserDimService().findPassword(userForm.getEmailAddress(),
				userForm.getTelNum());
		if (login) {
			request.setAttribute("logName", userForm.getLogName());
			return mapping.findForward("UPDATEPASSWORD");
		} else {
			request.setAttribute("logName", userForm.getLogName());
			request.setAttribute("emailAddress", userForm.getEmailAddress());
			request.setAttribute("telNum", userForm.getTelNum());
			request.setAttribute("message", "你输入的信息有误，验证失败");
			return mapping.findForward("FINDPASSWORD");
		}
	}
	
	public ActionForward updatePw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TblUserDimForm userForm = (TblUserDimForm) form;
		TblUserDimBo user = (TblUserDimBo) request.getSession().getAttribute(
				TblUserDimBo.USER_INFO_REFERENCE);

		Calendar cld = Calendar.getInstance();
		cld.add(Calendar.DATE, 30);
		// 设置该用户密码的过期时间为30天后
		// user.setPassTime(format.format(cld.getTime()));
		if (null == user)
		{
			user = new TblUserDimBo();
			List<TblUserDimBo> userList = this.getUserDimService().findTblUserDimByWhere("where log_name = '" + userForm.getLogName() + "'");
			user.setUserCode(userList.get(0).getUserCode());
			user.setLogName(userForm.getLogName());
			user.setUserName(userList.get(0).getUserName());
			user.setLastLogtime(userList.get(0).getLastLogtime());
			user.setDeptName(userList.get(0).getDeptName());
			user.setEmailAddress(userList.get(0).getEmailAddress());
			user.setRoleCode(userList.get(0).getRoleCode());
			user.setTelNum(userList.get(0).getTelNum());
			user.setOrgCode(userList.get(0).getOrgCode());
			user.setUserState(userList.get(0).getUserState());
			user.setPropertyId(userList.get(0).getPropertyId());
		}
		user.setLogPwd((new CryptionData()).EncryptionStringData(userForm
				.getConfirmPwd()));
		getUserDimService().updatePassword(userForm.getLogName(), user);
		SysLogger.logInfo(request, "修改用户密码,用户编号："+user.getUserCode());
		request.setAttribute("message", "修改密码成功，请重新登录！");
		request.getSession().removeAttribute(TblUserDimBo.USER_INFO_REFERENCE);
		return mapping.findForward("LOGIN");
	}
	
	public ActionForward changePw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TblUserDimForm userForm = (TblUserDimForm) form;
		TblUserDimBo user = (TblUserDimBo) request.getSession().getAttribute(
				TblUserDimBo.USER_INFO_REFERENCE);
		String pass = (new CryptionData()).EncryptionStringData(userForm
				.getLogPwd());

		String strurl = getTblMenuDimService().getMenuString("/changePwd.jsp");
		if (!user.getLogPwd().equals(pass)) {
			request.setAttribute("message", "原密码输入有误，请返回！");
			request.setAttribute("backurl", "/changePwd.jsp?" + strurl);
			return mapping.findForward("FAIL");
		} else {
			String whereClause = StringUtils.isNotEmpty(user.getPropertyId()) ? "where comm_pro = '" + user.getPropertyId() + "'" : "";
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

			// 公告信息
			Page TblServiceDims = getTblServiceDimService().findTblServiceDim(0,
					15, "");
			List serv = TblServiceDims.getList();
			Page tblnotic = getTblNoticeDimService().findTblNoticeDim(0, 15, "");
			List notic = tblnotic.getList();
			request.setAttribute("service", serv);
			request.setAttribute("notice", notic);

			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Calendar cld = Calendar.getInstance();
			cld.add(Calendar.DATE, 30);
			// user.setPassTime(format.format(cld.getTime()));//
			// 设置该用户密码的过期时间为30天后
			user.setLogPwd((new CryptionData()).EncryptionStringData(userForm
					.getConfirmPwd()));
			getUserDimService().save(user);
			SysLogger.logInfo(request, "修改用户密码,用户编号："+user.getUserCode());
			
			request.setAttribute("message", "修改密码成功，请重新登录！");
			
			request.getSession().removeAttribute(TblUserDimBo.USER_INFO_REFERENCE);
			return mapping.findForward("LOGIN");
		}
	}

	public ActionForward loginindex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		TblUserDimForm userForm = (TblUserDimForm) form;
		
		TblUserDimBo user = (TblUserDimBo) request.getSession().getAttribute(
				TblUserDimBo.USER_INFO_REFERENCE);

		String whereClause = StringUtils.isNotEmpty(user.getPropertyId()) ? "where comm_pro = '" + user.getPropertyId() + "'" : "";
		List<TblCommDimBo> comm = getTblCommDimService().findTblCommDimByWhere(whereClause);
		List<TblBuildingDimBo> build = getTblBuildingDimService().findTblBuildingDimByWhere("");
		List<TblUnitDimBo> unit = getTblUnitDimService().findTblUnitDimByWhere("");
		List<TblRoomDimBo> room = getTblRoomDimService().findTblRoomDimByWhere("");
		List<TblEquDimBo> equ = getTblEquDimService().findTblEquDimByWhere(
				" where equ_state='1'");
		request.setAttribute("comm", new Integer(comm.size()));
		request.setAttribute("build", new Integer(build.size()));
		request.setAttribute("unit", new Integer(unit.size()));
		request.setAttribute("room", new Integer(room.size()));
		request.setAttribute("equ", new Integer(equ.size()));

//		List serv = getTblServiceDimService().findTblServiceDimByWhere(" ");
//		List notic = getTblNoticeDimService().findTblNoticeDimByWhere("");
//
//		request.setAttribute("service", serv);
//		request.setAttribute("notice", notic);
		// 公告信息
		Page TblServiceDims = getTblServiceDimService().findTblServiceDim(0,
				15, "");
		List serv = TblServiceDims.getList();
		Page tblnotic = getTblNoticeDimService().findTblNoticeDim(0, 15, "");
		List notic = tblnotic.getList();
		request.setAttribute("service", serv);
		request.setAttribute("notice", notic);

		return mapping.findForward("loginindex");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TblUserDimForm userForm = (TblUserDimForm) form;
		TblUserDimBo user = (TblUserDimBo) request.getSession().getAttribute(
				TblUserDimBo.USER_INFO_REFERENCE);
		String pass = (new CryptionData()).EncryptionStringData(userForm
				.getLogPwd());
		if (!user.getLogPwd().equals(pass)) {
			request.setAttribute("message", "原密码输入有误，请返回！");
			request.setAttribute("backurl", "/user.do?method=edit");
			return mapping.findForward("FAIL");
		} else {
			// SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			// Calendar cld = Calendar.getInstance();
			// cld.add(Calendar.DATE, 30);
			// user.setPassTime(format.format(cld.getTime()));//
			// 设置该用户密码的过期时间为30天后
			// user.setLoginPwd(userForm.getConfirmPwd());
			// getUserDimService().resetPassword(user);
			// request.setAttribute("message", "密码修改成功，请重新登录！");
			// request.setAttribute("backurl", "/logout.jsp");
			return mapping.findForward("SUCCESS");
		}
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("MAIN").getPath());
		return mapping.findForward("FAIL");
	}

}
