package com.viatt.zhjtpro.action;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblEquDimBo;
import com.viatt.zhjtpro.bo.TblFingDimBo;
import com.viatt.zhjtpro.bo.TblFingOpBo;
import com.viatt.zhjtpro.bo.TblFingStatusDimBo;
import com.viatt.zhjtpro.bo.TblParamDimBo;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.Smsclient;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblFingDimForm;
import com.viatt.zhjtpro.service.ITblEquDimService;
import com.viatt.zhjtpro.service.ITblFingDimService;
import com.viatt.zhjtpro.service.ITblFingOpService;
import com.viatt.zhjtpro.service.ITblFingStatusDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblParamDimService;
import com.viatt.zhjtpro.service.ITblRoomDimService;

public class TblFingDimAction extends BaseAction {
	protected ITblFingDimService getTblFingDimService() {
		return (ITblFingDimService) getBean("tblFingDimService");
	}

	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}

	protected ITblRoomDimService getTblRoomDimService() {
		return (ITblRoomDimService) getBean("tblRoomDimService");
	}

	protected ITblFingOpService getTblFingOpService() {
		return (ITblFingOpService) getBean("tblFingOpService");
	}

	protected ITblFingStatusDimService getTblFingStatusDimService() {
		return (ITblFingStatusDimService) getBean("tblFingStatusDimService");
	}
	
	protected ITblEquDimService getTblEquDimService() {
		return (ITblEquDimService) getBean("tblEquDimService");
	}

	protected ITblParamDimService getTblParamDimService() {
		return (ITblParamDimService) getBean("tblParamDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblFingDim".equals(method)) {
			return forSaveTblFingDim(mapping, form, request, response);
		} else if ("addTblFingDim".equals(method)) {
			return saveTblFingDim(mapping, form, request, response);
		} else if ("queryTblFingDim".equals(method)) {
			return queryTblFingDim(mapping, form, request, response);
		} else if ("removeTblFingDim".equals(method)) {
			return removeTblFingDim(mapping, form, request, response);
		} else if ("viewTblFingDim".equals(method)) {
			return viewTblFingDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblFingDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblFingDimForm TblFingForm = (TblFingDimForm) form;

		String where = "where 1=1 ";
		String strFingUsr = MyRequest.GetString(request, "strFingUsr");
		if (!strFingUsr.equals("")) {
			where = where + " and fing_usr like '%" + strFingUsr + "%' ";
		}
		String strUserNo = MyRequest.GetString(request, "strUserNo");
		if (!strUserNo.equals("")) {
			where = where + " and usr_no like '%" + strUserNo + "%' ";
		}
		String strFingState = MyRequest.GetString(request, "strFingState");
		if (!strFingState.equals("")) {
			where = where + " and fing_state='" + strFingState + "' ";
		}
		request.setAttribute("strFingUsr", strFingUsr);
		request.setAttribute("strUserNo", strUserNo);
		request.setAttribute("strFingState", strFingState);

		int pageSize = TblFingForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblFingDims = getTblFingDimService().findTblFingDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblFingDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblFingDims", TblFingDims);

		return mapping.findForward("queryFingDim");

	}

	public ActionForward forSaveTblFingDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblFingDimForm TblFingForm = (TblFingDimForm) form;
		boolean isNew = TblFingForm.getUsrNo() == null
				|| "".equals(TblFingForm.getUsrNo());

		List fingop = new ArrayList();
		if (!isNew) {
			TblFingDimBo bo = this.getTblFingDimService().findTblFingDimById(
					TblFingForm.getUsrNo());
			BeanUtils.copyProperties(TblFingForm, bo);
			fingop = getTblFingOpService().findTblFingOpByWhere(
					" where op_type='2' and card_no='" + bo.getUsrNo()+"'");
		}
		List list = getTblRoomDimService().findVRoomDimByWhere("");
		List equ = getTblEquDimService().findTblEquDimByWhere(
				" where equ_type='d'");
		request.setAttribute("RoomList", list);
		request.setAttribute("EquList", equ);
		request.setAttribute("FingOpList", fingop);
		return mapping.findForward("addTblFingDim");
	}

	public ActionForward saveTblFingDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblFingDimForm TblFingForm = (TblFingDimForm) form;
		TblFingDimBo bo = new TblFingDimBo();
		boolean isNew = TblFingForm.getUsrNo() == null
				|| "".equals(TblFingForm.getUsrNo());

		String strurl = getTblMenuDimService().getMenuString(
				"/fingDim.do?method=queryTblFingDim");
		TblParamDimBo param = getTblParamDimService().findTblParamDimById(
				"100001");
		if (param == null) {
			request.setAttribute("message", "设备端口参数未配置！");
			request.setAttribute("backurl",
					"/fingDim.do?method=queryTblFingDim" + strurl);
			return mapping.findForward("FAIL");
		}
		try {
			String op[] = request.getParameterValues("fingop");
			String sndbuf = "";
			// 新增
			String optype = "";
			if (TblFingForm.getOpType().equals("1")) {
				optype = "新增";
				BeanUtils.copyProperties(bo, TblFingForm);// 属性拷贝
				getTblFingDimService().save(bo);
				
				// 发送通知报文
				sndbuf = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0009</msgId><cardNo>"
						+ bo.getUsrNo()
						+ "</cardNo><fing1>"
						+ bo.getFingImg1()
						+ "</fing1><fing2>"
						+ bo.getFingImg2()
						+ "</fing2><updType>1</updType></xml>";
				sndbuf = String.format("%06d", sndbuf.getBytes().length)
						+ sndbuf;
				if (op != null && op.length > 0) {
					for (int i = 0; i < op.length; i++) {
						TblFingStatusDimBo fingStatusBo = new TblFingStatusDimBo() ;
						TblEquDimBo equ = getTblEquDimService().findTblEquDimById(op[i]);
						
						fingStatusBo.setEquCode(equ.getEquCode()) ;
						fingStatusBo.setFingImg1(bo.getFingImg1()) ;
						fingStatusBo.setFingImg2(bo.getFingImg2());
						fingStatusBo.setUsrNo(bo.getUsrNo()) ;
						fingStatusBo.setIpAdd(equ.getIpAdd()) ;
						fingStatusBo.setOpType("1") ;
						fingStatusBo.setSendZt("0") ;
						getTblFingStatusDimService().save(fingStatusBo) ;
						 
						//时时发送改成任务队列，防止门口机休眠的情况下发送不出去
					/*	if (equ != null) {   
							Smsclient.SmsClient(equ.getIpAdd(), param
									.getParamValue().trim(), sndbuf, 6);
						}*/
					}
				}

			} else {
				optype = "修改";
				BeanUtils.copyProperties(bo, TblFingForm);// 属性拷贝
				getTblFingDimService().save(bo);
				
				
				//查找权限列表中不存在的设备
				// 发送通知报文 修改
				String temStatue = "" ;
				if(bo.getFingState().equals("01")){
					temStatue = "3" ;
				} else if (bo.getFingState().equals("02")) {
					temStatue = "2" ;
				}
/*				sndbuf = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0009</msgId><cardNo>"
						+ bo.getUsrNo()
						+ "</cardNo><fing1>"
						+ bo.getFingImg1()
						+ "</fing1><fing2>"
						+ bo.getFingImg2()
						+ "</fing2><updType>2</updType></xml>";
				sndbuf = String.format("%06d", sndbuf.getBytes().length)
						+ sndbuf;*/
				String strop=",";
				if (op != null && op.length > 0) {
					for (int i = 0; i < op.length; i++) {
						strop = strop +op[i]+",";
					}
				}

				List oplist = getTblFingOpService().findTblFingOpByWhere(" where op_type='2' and " +
						"card_no='" + bo.getUsrNo() + "'");
				if(oplist!=null && oplist.size()>0){
					for(int j=0;j<oplist.size();j++){
						TblFingOpBo opbo = (TblFingOpBo)oplist.get(j);
//						if(!strop.contains(","+opbo.getEquCode().trim()+",")){
							TblEquDimBo equ = getTblEquDimService().findTblEquDimById(opbo.getEquCode());
							if (equ != null) {
								TblFingStatusDimBo fingStatusBo = new TblFingStatusDimBo() ;
								
								fingStatusBo.setEquCode(equ.getEquCode()) ;
								fingStatusBo.setFingImg1(bo.getFingImg1()) ;
								fingStatusBo.setFingImg2(bo.getFingImg2());
								fingStatusBo.setUsrNo(bo.getUsrNo()) ;
								fingStatusBo.setIpAdd(equ.getIpAdd()) ;
								fingStatusBo.setOpType(temStatue) ;
								fingStatusBo.setSendZt("0") ;
								getTblFingStatusDimService().save(fingStatusBo) ;  //存入到指纹发送数据库，通过发送队列进行指纹发送
								
								/*new Thread(new Runnable() {
									public void run() {
									final TblParamDimBo mParam = param ;
									final String mSndbuf = sndbuf ;
									new Thread(new Runnable() {
										
										public void run() {
											try {
												Smsclient.SmsClient(equ.getIpAdd(), mParam     //发送删除报文通知到原有的指纹信息设备中(如果IP地址不在线，ping不通会报错，要进行处理 x)
														.getParamValue().trim(), mSndbuf, 6);
											} catch (UnknownHostException e) {
												e.printStackTrace();
											} catch (IOException e) {
												e.printStackTrace();
											}
										}
									}).start() ; 
									}
								}).start();*/
							}
//						}
					}
				}
				// 发送通知报文 修改
			/*	sndbuf = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0009</msgId><cardNo>"
						+ bo.getUsrNo()
						+ "</cardNo><fing1>"
						+ bo.getFingImg1()
						+ "</fing1><fing2>"
						+ bo.getFingImg2()
						+ "</fing2><updType>" + temStatue + "</updType></xml>";
				sndbuf = String.format("%06d", sndbuf.getBytes().length)
						+ sndbuf;
				if (op != null && op.length > 0) {
					for (int i = 0; i < op.length; i++) {
						TblEquDimBo equ = getTblEquDimService()
								.findTblEquDimById(op[i]);
						if (equ != null) {
							Smsclient.SmsClient(equ.getIpAdd(), param
									.getParamValue().trim(), sndbuf, 6);
						}
					}
				}*/
			}

			// 登记门禁权限表 指纹
			String usrno = bo.getUsrNo();
			getTblFingOpService().removeDim(usrno, "2");
			if (op != null && op.length > 0) {
				for (int i = 0; i < op.length; i++) {
					TblFingOpBo fing = new TblFingOpBo();
					fing.setCardNo(usrno); 
					fing.setEquCode(op[i]);
					fing.setOpType("2");// 指纹
					getTblFingOpService().save(fing);
				}
			}
			SysLogger.logInfo(request, optype+"指纹门禁信息,指纹门禁编号："+bo.getUsrNo());
			request.setAttribute("message", optype + "指纹门禁信息成功！");
			request.setAttribute("backurl",
					"/fingDim.do?method=queryTblFingDim" + strurl);
			return mapping.findForward("SUCCESS");
		} catch (Exception e) {
			request.setAttribute("message", "操作失败");
			request.setAttribute("backurl", "/fingDim.do?method=queryTblFingDim" + strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblFingDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String UsrNo = request.getParameter("usrNo");
		TblFingDimForm TblFingForm = (TblFingDimForm) form;
		String strurl = getTblMenuDimService().getMenuString(
				"/fingDim.do?method=queryTblFingDim");

		TblParamDimBo param = getTblParamDimService().findTblParamDimById(
				"100001");
		if (param == null) {
			request.setAttribute("message", "设备端口参数未配置！");
			request.setAttribute("backurl",
					"/fingDim.do?method=queryTblFingDim" + strurl);
			return mapping.findForward("FAIL");
		}
		TblFingDimBo fing = getTblFingDimService().findTblFingDimById(UsrNo);
		// 发送通知报文
		String sndbuf = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0009</msgId><cardNo>"
				+ fing.getUsrNo()
				+ "</cardNo><fing1>"
				+ fing.getFingImg1()
				+ "</fing1><fing2>"
				+ fing.getFingImg2()
				+ "</fing2><updType>2</updType></xml>";
		sndbuf = String.format("%06d", sndbuf.getBytes().length) + sndbuf;
		List list = getTblFingOpService().findTblFingOpByWhere(
				" where op_type='2' and card_no=" + UsrNo);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				TblFingOpBo bo = (TblFingOpBo)list.get(i);
				TblEquDimBo equ = getTblEquDimService().findTblEquDimById(bo.getEquCode());
				if (bo != null) {
					TblFingStatusDimBo fingStatusBo = new TblFingStatusDimBo() ;
					fingStatusBo.setEquCode(equ.getEquCode()) ;
					fingStatusBo.setFingImg1(fing.getFingImg1()) ;
					fingStatusBo.setFingImg2(fing.getFingImg2());
					fingStatusBo.setUsrNo(fing.getUsrNo()) ;
					fingStatusBo.setIpAdd(equ.getIpAdd()) ;
					fingStatusBo.setOpType("2") ;
					fingStatusBo.setSendZt("0") ;
					getTblFingStatusDimService().save(fingStatusBo) ;
//					Smsclient.SmsClient(bo.getIpAdd(), param.getParamValue().trim(), sndbuf, 6);
				}
			}
		}
		this.getTblFingDimService().removeFingDim(UsrNo);
		SysLogger.logInfo(request, "删除指纹门禁信息,指纹门禁编号："+UsrNo);
		request.setAttribute("message", "删除指纹门禁信息成功！");
		request.setAttribute("backurl", "/fingDim.do?method=queryTblFingDim"
				+ strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward viewTblFingDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String UsrNo = request.getParameter("usrNo");
		TblFingDimForm TblFingForm = (TblFingDimForm) form;
		String strurl = getTblMenuDimService().getMenuString(
				"/fingDim.do?method=queryTblFingDim");
		TblFingDimBo bo = getTblFingDimService().findTblFingDimById(UsrNo);
		if (bo == null) {
			request.setAttribute("message", "指纹门禁" + UsrNo + "的信息不存在！");
			request.setAttribute("backurl",
					"/fingDim.do?method=queryTblFingDim" + strurl);
			return mapping.findForward("FAIL");
		}
		BeanUtils.copyProperties(TblFingForm, bo);
		return mapping.findForward("viewTblFingDim");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblFingDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
