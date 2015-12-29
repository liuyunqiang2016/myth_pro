package com.viatt.zhjtpro.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblCardDimBo;
import com.viatt.zhjtpro.bo.TblFingOpBo;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblCardDimForm;
import com.viatt.zhjtpro.service.ITblCardDimService;
import com.viatt.zhjtpro.service.ITblEquDimService;
import com.viatt.zhjtpro.service.ITblFingOpService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblRoomDimService;

public class TblCardDimAction extends BaseAction {
	protected ITblCardDimService getTblCardDimService() {
		return (ITblCardDimService) getBean("tblCardDimService");
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
	protected ITblEquDimService getTblEquDimService() {
		return (ITblEquDimService) getBean("tblEquDimService");
	}

	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblCardDim".equals(method)) {
			return forSaveTblCardDim(mapping, form, request, response);
		} else if ("addTblCardDim".equals(method)) {
			return saveTblCardDim(mapping, form, request, response);
		} else if ("queryTblCardDim".equals(method)) {
			return queryTblCardDim(mapping, form, request, response);
		} else if ("removeTblCardDim".equals(method)) {
			return removeTblCardDim(mapping, form, request, response);
		} else if ("viewTblCardDim".equals(method)) {
			return viewTblCardDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblCardDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblCardDimForm TblCardForm = (TblCardDimForm) form;
		
		String where="where 1=1 ";
		String strUsrName = MyRequest.GetString(request, "strUsrName");
		if(!strUsrName.equals("")){
			where = where + " and usr_name like '%" + strUsrName +"%' ";
		}
		String strUserNo = MyRequest.GetString(request, "strUserNo");
		if(!strUserNo.equals("")){
			where = where + " and user_no like '%" + strUserNo +"%' ";
		}
		String strState = MyRequest.GetString(request, "strState");
		if(!strState.equals("")){
			where = where + " and state='" + strState +"' ";
		}
		String strCardNo = MyRequest.GetString(request, "strCardNo");
		if(!strCardNo.equals("")){
			where = where + " and card_no like '%" + strCardNo +"%' ";
		}
		request.setAttribute("strUsrName", strUsrName);
		request.setAttribute("strUserNo", strUserNo);
		request.setAttribute("strState", strState);
		request.setAttribute("strCardNo", strCardNo);

		int pageSize = TblCardForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblCardDims = getTblCardDimService().findTblCardDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblCardDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblCardDims", TblCardDims);

		return mapping.findForward("queryCardDim");

	}

	public ActionForward forSaveTblCardDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblCardDimForm TblCardForm = (TblCardDimForm) form;
		boolean isNew = TblCardForm.getCardNo() == null
				|| "".equals(TblCardForm.getCardNo());
		List fingop = new ArrayList();
		if (!isNew) {
			TblCardDimBo bo = this.getTblCardDimService().findTblCardDimById(
					TblCardForm.getCardNo());
			BeanUtils.copyProperties(TblCardForm, bo);
			fingop = getTblFingOpService().findTblFingOpByWhere(
					" where op_type='1' and card_no='" + bo.getCardNo()+"'");
		}
		List list = getTblRoomDimService().findVRoomDimByWhere("");
		List equ = getTblEquDimService().findTblEquDimByWhere(
			" where equ_type='d' and equ_state='1' and equ_acc_id is not null");
		request.setAttribute("RoomList", list);
		request.setAttribute("FingOpList", fingop);
		request.setAttribute("EquList", equ);
		
		return mapping.findForward("addTblCardDim");
	}

	public ActionForward saveTblCardDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblCardDimForm TblCardForm = (TblCardDimForm) form;
		TblCardDimBo bo = new TblCardDimBo();

		String strurl = getTblMenuDimService().getMenuString("/cardDim.do?method=queryTblCardDim");
		try {
			//新增
			if (TblCardForm.getOpType().equals("0")) {
				BeanUtils.copyProperties(bo, TblCardForm);// 属性拷贝
				List list = getTblCardDimService().findTblCardDimByWhere(" where card_no='"+bo.getCardNo()+"'");
				if(list!=null&&list.size()>0){
					request.setAttribute("message", "门禁卡【"+bo.getCardNo()+"】已存在！");
					request.setAttribute("backurl",
							"/cardDim.do?method=queryTblCardDim"+strurl);
					return mapping.findForward("FAIL");
				}
				getTblCardDimService().save(bo);
				// 登记门禁权限表 指纹
				String op[] = request.getParameterValues("fingop");
				String usrno = bo.getCardNo();
				getTblFingOpService().removeDim(usrno, "1");
				if (op != null && op.length > 0) {
					for (int i = 0; i < op.length; i++) {
						String accid= (String)request.getParameter(op[i]);
						TblFingOpBo fing = new TblFingOpBo();
						fing.setCardNo(usrno);
						fing.setEquCode(op[i]);
						fing.setOpType("1");// 门禁
						fing.setModCode(accid);
						getTblFingOpService().save(fing);
					}
				}
				SysLogger.logInfo(request, "添加门禁卡信息,门禁卡编号："+bo.getCardNo());
				request.setAttribute("message", "添加门禁卡信息成功！");
				request.setAttribute("backurl",
						"/cardDim.do?method=queryTblCardDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, TblCardForm);// 属性拷贝
				getTblCardDimService().save(bo);
				// 登记门禁权限表 指纹
				String op[] = request.getParameterValues("fingop");
				String usrno = bo.getCardNo();
				getTblFingOpService().removeDim(usrno, "1");
				if (op != null && op.length > 0) {
					for (int i = 0; i < op.length; i++) {
						String accid= (String)request.getParameter(op[i]);
						TblFingOpBo fing = new TblFingOpBo();
						fing.setCardNo(usrno);
						fing.setEquCode(op[i]);
						fing.setOpType("1");// 门禁
						fing.setModCode(accid);
						getTblFingOpService().save(fing);
					}
				}
				SysLogger.logInfo(request, "修改门禁卡信息,门禁卡编号："+bo.getCardNo());
				request.setAttribute("message", "修改门禁卡信息成功！");
				request.setAttribute("backurl",
						"/cardDim.do?method=queryTblCardDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/cardDim.do?method=queryTblCardDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblCardDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String cardNo = request.getParameter("cardNo");
		TblCardDimForm TblCardForm = (TblCardDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/cardDim.do?method=queryTblCardDim");
		this.getTblCardDimService().removeCardDim(cardNo);
		SysLogger.logInfo(request, "删除门禁卡信息,门禁卡编号："+cardNo);
		request.setAttribute("message", "删除门禁卡信息成功！");
		request.setAttribute("backurl",
				"/cardDim.do?method=queryTblCardDim"+strurl);
		return mapping.findForward("SUCCESS");
	}
	public ActionForward viewTblCardDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String cardNo = request.getParameter("cardNo");
		TblCardDimForm TblCardForm = (TblCardDimForm) form;
		
		String strurl = getTblMenuDimService().getMenuString("/cardDim.do?method=queryTblCardDim");
		TblCardDimBo bo = getTblCardDimService().findTblCardDimById(cardNo);
		if(bo==null){
			request.setAttribute("message", "门禁卡"+cardNo+"的信息不存在！");
			request.setAttribute("backurl",
					"/cardDim.do?method=queryTblCardDim"+strurl);
			return mapping.findForward("FAIL");
		}
		
		List equ = getTblEquDimService().findTblEquDimByWhere(" where equ_type='d' and equ_state='1' and equ_acc_id is not null");
//		request.setAttribute("RoomList", list);
//		request.setAttribute("EquList", equ);
		
		BeanUtils.copyProperties(TblCardForm, bo);
		return mapping.findForward("viewTblCardDim");
	}

	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblCardDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
