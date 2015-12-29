package com.viatt.zhjtpro.action;

import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.ActionSupport;

import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.CryptionData;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.forms.BaseForm;
import com.viatt.zhjtpro.service.ITblUserDimService;


public class BaseAction extends ActionSupport {
	
	protected int currPage;//分页时用户需要的页码
	protected String menuPare;
	protected String menuChild;
	
	protected Object getBean(String name) {
		return getWebApplicationContext().getBean(name);
	}

	public ActionForward execute(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		
		currPage = MyRequest.GetInt(arg2, "page", 1);
		
//		/*用户是否登录的过滤*/
//		if (!isLogin(arg2)){
//			arg3.getWriter().println("<script>window.parent.navigate('" + MyRequest.GetPath(arg2, "login.jsp") + "')</script>");
//			arg3.flushBuffer();
//		}
				
		/*action的访问控制（用户是否有权限执行这样的操作）*/
		/*if(isLogin(arg2) && !isAccessable(arg0, arg1, arg2)){
			arg2.setAttribute("message", "对不起，你不具有这样的权限！");
			arg2.setAttribute("backurl", "");
			return arg0.findForward("FAIL");
		}*/
		
		/*执行自定义的处理方法，BaseAction子类应对此方法覆盖*/
		return proccess(arg0, arg1, arg2, arg3);
	}

	/**
	 * 以.do结尾的请求是否需要验证，如果需要，返回用户是否登录的信息
	 */
	protected boolean isLogin(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String method = request.getParameter("method");
		if ("/user.do".equals(uri) && "login".equals(method))
			return true;

		TblUserDimBo bo = (TblUserDimBo) request.getSession().getAttribute(TblUserDimBo.USER_INFO_REFERENCE);
		if (bo == null)
			return false;
		else
			return true;
	}

	protected boolean isAccessable(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2) {
		/*过滤请求地址*/
		String uri = arg2.getRequestURI();
		String method = arg2.getParameter("method");
		if ("/user.do".equals(uri) && "login".equals(method))
			return true;
		if(uri.startsWith("/share"))
			return true;
		
		boolean isAccessable = false;
		
		BaseForm baseForm = (BaseForm) arg1;
		String reqUrl = arg2.getRequestURI() + "?method=" + baseForm.getMethod();//用户的请求资源
		TblUserDimBo bo = (TblUserDimBo) arg2.getSession().getAttribute(TblUserDimBo.USER_INFO_REFERENCE);
//		java.util.List list = bo.getResourcesAccessable();//用户的可访问资源数组
		java.util.List list = null;
		
		for (Iterator it = list.iterator(); it.hasNext();) {
			String resource = (String) it.next();
			if(resource.equals(reqUrl)){
				isAccessable = true;
				break;
			}
		}
		
		return isAccessable;
	}
	
	protected boolean needsAuthorization(ActionMapping arg0, HttpServletRequest arg1) {
		/* 从应用上下文读取配置（true or false），并据此判定用户操作是否需要进一步的认证和授权 */
		//String actionPath = arg0.getPath();//截获用户的请求路径，并检查该请求是否需要进一步的审核	
		ServletContext ctx = arg1.getSession().getServletContext();
		Boolean needsAuthorization = (Boolean) ctx.getAttribute("needsAuthorization");
		//String needsAuthorization = PropertiesReader.getProperty("needsAuthorization");
		//boolean authorization = Boolean.valueOf(needsAuthorization).booleanValue();
		return needsAuthorization.booleanValue();
		
	}
	
	protected void displayAuthorizationMenu(ActionMapping arg0, HttpServletRequest arg1) {
		if(needsAuthorization(arg0, arg1))
			arg1.setAttribute("needs_authorization", new Boolean(true));
		else
			arg1.setAttribute("needs_authorization", new Boolean(false));
	}
	
	protected boolean advancedAuthorization(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2) {
		if(!needsAuthorization(arg0, arg2))
			return true;
		
		BaseForm form = (BaseForm) arg1;
		TblUserDimBo user = (TblUserDimBo) arg2.getSession().getAttribute(TblUserDimBo.USER_INFO_REFERENCE);		
		String advancedPassword = null;
		try {
			advancedPassword = (new CryptionData()).EncryptionStringData(form.getAdvancedPassword());
		} catch (Exception e) {
			advancedPassword = "";
		}
		ITblUserDimService userDimService = (ITblUserDimService) this.getBean("userDimService");
		TblUserDimBo assessor = null;
//		userDimService.getLoginUserInfo(form.getAdvancedUserName());//审核人
		
		if(assessor != null){
			boolean isUserAuditingable = true;//用户输入的审核人是否具有审核本模块的权限
//			String authorityUrl = arg2.getRequestURI() + "?authorityCode=" + FuncAttrDimBo.COMMON_AUTHORITY_AUDITING;
//			java.util.List list = assessor.getResourcesAccessable();//审核人的可访问资源数组（隐式权限以url的形式显示表示）			
//			for (Iterator it = list.iterator(); it.hasNext();) {
//				String resource = (String) it.next();
//				if(resource.equals(authorityUrl)){
//					isUserAuditingable = true;
//					break;
//				}
//			}
			
//			if(assessor.getLoginPwd().equals(advancedPassword)){//审核人信息必须正确
//				if(assessor.getOrgCode().equals(user.getOrgCode()) && !assessor.getLoginName().equals(user.getLoginName())){//审核人和被审核人必须是同一机构下人员
//					if(isUserAuditingable)//审核人必须具有本模块的审核权
//						return true;
//					else					
//						arg2.setAttribute("message", "复核人对本模块不具有审核的权限，请重试!");
//				}else{
//					arg2.setAttribute("message", "复核人和操作人必须是同一机构下的人员并不能是操作人自己，请重试!");
//				}					
//			}else{
//				arg2.setAttribute("message", "复核人的用户名或密码错误，请重试!");
//			}
					
		}else{
			arg2.setAttribute("message", "复核人的用户名或密码错误，请重试!");
		}

		return false;
		
	}
	
	protected boolean advancedAuthorization1(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2) {
		BaseForm form = (BaseForm) arg1;
		TblUserDimBo user = (TblUserDimBo) arg2.getSession().getAttribute(TblUserDimBo.USER_INFO_REFERENCE);		
		String advancedPassword = null;
		try {
			advancedPassword = (new CryptionData()).EncryptionStringData(form.getAdvancedPassword());
		} catch (Exception e) {
			advancedPassword = "";
		}
		ITblUserDimService userDimService = (ITblUserDimService) this.getBean("userDimService");
		TblUserDimBo assessor = null;
//			userDimService.getLoginUserInfo(form.getAdvancedUserName());//审核人
		
		if(assessor != null){
			boolean isUserAuditingable = true;//用户输入的审核人是否具有审核本模块的权限
//			String authorityUrl = arg2.getRequestURI() + "?authorityCode=" + FuncAttrDimBo.COMMON_AUTHORITY_AUDITING;
//			java.util.List list = assessor.getResourcesAccessable();//审核人的可访问资源数组（隐式权限以url的形式显示表示）			
//			for (Iterator it = list.iterator(); it.hasNext();) {
//				String resource = (String) it.next();
//				if(resource.equals(authorityUrl)){
//					isUserAuditingable = true;
//					break;
//				}
//			}
//			
//			if(assessor.getLoginPwd().equals(advancedPassword)){//审核人信息必须正确
//				if(assessor.getOrgCode().equals(user.getOrgCode()) && !assessor.getLoginName().equals(user.getLoginName())){//审核人和被审核人必须是同一机构下人员
//					if(isUserAuditingable)//审核人必须具有本模块的审核权
//						return true;
//					else					
//						arg2.setAttribute("message", "复核人对本模块不具有审核的权限，请重试!");
//				}else{
//					arg2.setAttribute("message", "复核人和操作人必须是同一机构下的人员并不能是操作人自己，请重试!");
//				}					
//			}else{
//				arg2.setAttribute("message", "复核人的用户名或密码错误，请重试!");
//			}
					
		}else{
			arg2.setAttribute("message", "复核人的用户名或密码错误，请重试!");
		}

		return false;
		
	}

	protected ActionForward proccess(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		return super.execute(arg0, arg1, arg2, arg3);
	}
}