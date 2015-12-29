package com.viatt.zhjtpro.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.viatt.zhjtpro.bo.TblLogDimBo;
import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.service.ITblLogDimService;
import com.viatt.zhjtpro.time.StartupServlet;

public class SysLogger {

	public static void logInfo(HttpServletRequest request, String content)
			throws Exception {
		ITblLogDimService log = (ITblLogDimService) StartupServlet.bean
				.getBean("tblLogDimService");
		String userName="";
		TblUserDimBo user = (TblUserDimBo) request.getSession().getAttribute(
				TblUserDimBo.USER_INFO_REFERENCE);
		if(user!=null){
			userName=user.getLogName();
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		TblLogDimBo bo = new TblLogDimBo();
		bo.setLogCode(GetId.randomID());
		bo.setCreTime(sf.format(new Date()));
		bo.setOperName(userName);
		bo.setLogContent(content);
		log.save(bo);
	}
}
