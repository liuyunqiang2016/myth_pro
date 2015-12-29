package com.viatt.zhjtpro.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.viatt.zhjtpro.bo.TblBillsDimBo;
import com.viatt.zhjtpro.bo.TblEquDimBo;
import com.viatt.zhjtpro.bo.TblInfoStatusDimBo;
import com.viatt.zhjtpro.bo.TblItemDimBo;
import com.viatt.zhjtpro.bo.TblParamDimBo;
import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.MyRequest;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PageRoll;
import com.viatt.zhjtpro.common.Smsclient;
import com.viatt.zhjtpro.common.SysLogger;
import com.viatt.zhjtpro.forms.TblBillsDimForm;
import com.viatt.zhjtpro.service.ITblBillsDimService;
import com.viatt.zhjtpro.service.ITblEquDimService;
import com.viatt.zhjtpro.service.ITblInfoStatusDimService;
import com.viatt.zhjtpro.service.ITblItemDimService;
import com.viatt.zhjtpro.service.ITblMenuDimService;
import com.viatt.zhjtpro.service.ITblParamDimService;
import com.viatt.zhjtpro.service.ITblRoomDimService;

public class TblBillsDimAction extends BaseAction {
	protected ITblBillsDimService getTblBillsDimService() {
		return (ITblBillsDimService) getBean("tblBillsDimService");
	}
	protected ITblItemDimService getTblItemDimService() {
		return (ITblItemDimService) getBean("tblItemDimService");
	}
	protected ITblRoomDimService getTblRoomDimService() {
		return (ITblRoomDimService) getBean("tblRoomDimService");
	}
	protected ITblMenuDimService getTblMenuDimService() {
		return (ITblMenuDimService) getBean("tblMenuDimService");
	}
	protected ITblEquDimService getTblEquDimService() {
		return (ITblEquDimService) getBean("tblEquDimService");
	}
	protected ITblParamDimService getTblParamDimService() {
		return (ITblParamDimService) getBean("tblParamDimService");
	}
	protected ITblInfoStatusDimService getTblInfoStatusDimService() {
		return (ITblInfoStatusDimService) getBean("tblInfoStatusDimService");
	}
	
	public ActionForward proccess(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String method = MyRequest.GetString(request, "method");

		if ("forAddTblBillsDim".equals(method)) {
			return forSaveTblBillsDim(mapping, form, request, response);
		} else if ("addTblBillsDim".equals(method)) {
			return saveTblBillsDim(mapping, form, request, response);
		} else if ("queryTblBillsDim".equals(method)) {
			return queryTblBillsDim(mapping, form, request, response);
		} else if ("removeTblBillsDim".equals(method)) {
			return removeTblBillsDim(mapping, form, request, response);
		} else if ("viewTblBillsDim".equals(method)) {
			return viewTblBillsDim(mapping, form, request, response);
		} else
			return unSpecified(mapping, form, request, response);
	}

	public ActionForward queryTblBillsDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblBillsDimForm TblBillsForm = (TblBillsDimForm) form;
		
		String where="where 1=1 ";
		String strJfDate = MyRequest.GetString(request, "strJfDate");
		if(!strJfDate.equals("")){
			where = where + " and jf_date = '" + strJfDate +"' ";
		}
		request.setAttribute("strJfDate", strJfDate);

		int pageSize = TblBillsForm.getPageSize();
		int beginIndex = pageSize * (this.currPage - 1);

		Page TblBillsDims = getTblBillsDimService().findTblBillsDim(beginIndex,
				pageSize, where);
		PageRoll pr = new PageRoll(request, pageSize, this.currPage,
				TblBillsDims.getTotalNumber());
		request.setAttribute("pr", pr.Show(true, true, true, true));
		request.setAttribute("tblBillsDims", TblBillsDims);
		List list = getTblItemDimService().findTblItemDimByWhere("");
		request.setAttribute("itemList", list);
		List roomList = getTblRoomDimService().findVRoomDimByWhere("");
		request.setAttribute("roomList", roomList);

		return mapping.findForward("queryBillsDim");

	}

	public ActionForward forSaveTblBillsDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblBillsDimForm TblBillsForm = (TblBillsDimForm) form;
		boolean isNew = TblBillsForm.getItemCode() == null
				|| "".equals(TblBillsForm.getItemCode());
		if (!isNew) {
			TblBillsDimBo bo = this.getTblBillsDimService().findTblBillsDimById(
					TblBillsForm.getBillCode(),TblBillsForm.getItemCode(),TblBillsForm.getRoomCode());
			BeanUtils.copyProperties(TblBillsForm, bo);
		}

		List list = getTblItemDimService().findTblItemDimByWhere("");
		request.setAttribute("itemList", list);
		List roomList = getTblRoomDimService().findVRoomDimByWhere("");
		request.setAttribute("roomList", roomList);
		return mapping.findForward("addTblBillsDim");
	}

	public ActionForward saveTblBillsDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TblBillsDimForm TblBillsForm = (TblBillsDimForm) form;
		TblBillsDimBo bo = new TblBillsDimBo();
		boolean isNew = TblBillsForm.getBillCode() == null
				|| "".equals(TblBillsForm.getBillCode());
		String strurl = getTblMenuDimService().getMenuString("/billsDim.do?method=queryTblBillsDim");
		TblParamDimBo param = getTblParamDimService().findTblParamDimById(
				"100001");
		if (param == null) {
			request.setAttribute("message", "设备端口参数未配置！");
			request.setAttribute("backurl",
					"/billsDim.do?method=queryTblBillsDim"+strurl);
			return mapping.findForward("FAIL");
		}
		String message="";
		//获取缴费项目名称
		String itemName="";
		List item = getTblItemDimService().findTblItemDimByWhere(" where item_code='"
				+TblBillsForm.getItemCode()+"'");
		if(item!=null&&item.size()>0){
			TblItemDimBo ite = (TblItemDimBo)item.get(0);
			itemName = ite.getItemName();
		}
		TblUserDimBo user = (TblUserDimBo)request.getSession().getAttribute(TblUserDimBo.USER_INFO_REFERENCE);
		try {
			if (isNew) {
				BeanUtils.copyProperties(bo, TblBillsForm);// 属性拷贝
				String roomcode = bo.getRoomCode();
				String[] li = roomcode.split(";");
				bo.setCommID(li[0]);
				bo.setAreaID(li[1]);
				bo.setBuldID(li[2]);
				bo.setUnitID(li[3]);
				bo.setRoomCode(li[4]);
				bo.setBillCode(GetId.randomID());
				getTblBillsDimService().save(bo);
				SysLogger.logInfo(request, "添加缴费单据信息,缴费单据编号："+bo.getBillCode());
				
				//获取设备，发送信息
				String where =" where comm_code='"+li[0]+"' and area_code='"+li[1]
				   +"' and unit_code='"+li[3]+"' and building_code='"+
				   li[2]+"' and equ_add='"+li[4]+"'";
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				List list = getTblEquDimService().findTblEquDimByWhere(where);
				if(list!=null&&list.size()>0){
					TblEquDimBo equ = (TblEquDimBo)list.get(0);
					
					TblInfoStatusDimBo info = new TblInfoStatusDimBo();
					info.setEquCode(equ.getEquCode());
					info.setInfoCode(bo.getBillCode());
					info.setInfoType("f");
					info.setSendZt("0");
					info.setUpdType("0");
					info.setInfoTitle("缴费项目:"+itemName) ;
					String mark = "缴费单据编号:"+bo.getBillCode()+"， 缴费项目："+itemName+"， 缴费金额："
						+bo.getBillAmt()+"， 缴费时间："
						+bo.getJfDate()+"， 缴费截至时间："+bo.getJzDate()+"|"+sf.format(new Date())+"|"+user.getLogName();
					info.setRemark(mark);
					getTblInfoStatusDimService().save(info);
					
					
//					if(equ.getEquState().equals("0"))
//						message="设备不在线，请通过修改缴费单据的方式重新发送缴费信息";
//					String snd = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0001</msgId><auunId>"
//						+bo.getBillCode()+"</auunId><updType>0</updType><auunTitle>缴费信息</auunTitle><context>"
//						+"缴费单据编号:"+bo.getBillCode()+"， 缴费项目："+itemName+"， 缴费金额："
//						+bo.getBillAmt()+"， 缴费时间："
//						+bo.getJfDate()+"， 缴费截至时间："+bo.getJzDate()+"</context><creTime>"
//						+sf.format(new Date())+"</creTime><creAuthor>"
//						+user.getLogName()+"</creAuthor></xml>";
//					try{
//						snd = String.format("%06d", snd.getBytes().length)+ snd;
//						Smsclient.SmsClient(equ.getIpAdd(), param.getParamValue(), snd, 6);
//					}catch (Exception e1) {
//						e1.printStackTrace();
//					}
				}
				
				request.setAttribute("message", "添加缴费单据信息成功！");
				request.setAttribute("backurl",
						"/billsDim.do?method=queryTblBillsDim"+strurl);
				return mapping.findForward("SUCCESS");
			} else {
				BeanUtils.copyProperties(bo, TblBillsForm);// 属性拷贝
				if(TblBillsForm.getOpType()!=null&&TblBillsForm.getOpType().equals("1")){
					TblBillsDimBo bill = getTblBillsDimService().findTblBillsDimById(bo.getBillCode(), bo.getItemCode(), bo.getRoomCode());
					if(bill!=null){
						request.setAttribute("message", "缴费单据编号"+bo.getBillCode()+"的缴费项目"+bo.getItemCode()+"已存在");
						request.setAttribute("backurl",
								"/billsDim.do?method=queryTblBillsDim"+strurl);
						return mapping.findForward("FAIL");
					}
				}
				getTblBillsDimService().save(bo);
				SysLogger.logInfo(request, "修改缴费单据信息,缴费单据编号："+bo.getBillCode());
				
				//获取设备，发送信息
				String where =" where comm_code='"+bo.getCommID()+"' and area_code='"+bo.getAreaID()
				   +"' and unit_code='"+bo.getUnitID()+"' and building_code='"+
				   bo.getBuldID()+"' and equ_add='"+bo.getRoomCode()+"'";
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				List list = getTblEquDimService().findTblEquDimByWhere(where);
				if(list!=null&&list.size()>0){
					TblEquDimBo equ = (TblEquDimBo)list.get(0);
					TblInfoStatusDimBo info = new TblInfoStatusDimBo();
					info.setEquCode(equ.getEquCode());
					info.setInfoCode(bo.getBillCode());
					info.setInfoType("f");
					info.setSendZt("0");
					info.setUpdType("1");
					info.setInfoTitle("缴费项目:"+itemName) ;
					String mark = "缴费单据编号:"+bo.getBillCode()+"， 缴费项目："+itemName+"， 缴费金额："
						+bo.getBillAmt()+"， 缴费时间："
						+bo.getJfDate()+"， 缴费截至时间："+bo.getJzDate()+"|"+sf.format(new Date())+"|"+user.getLogName();
					info.setRemark(mark);
					getTblInfoStatusDimService().save(info);
//					if(equ.getEquState().equals("0"))
//						message="设备不在线，请通过修改缴费单据的方式重新发送缴费信息";
//					String snd = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0001</msgId><auunId>"
//						+bo.getBillCode()+"</auunId><updType>0</updType><auunTitle>缴费信息</auunTitle><context>"
//						+"缴费单据编号:"+bo.getBillCode()+"， 缴费项目："+itemName+"，缴费金额："
//						+bo.getBillAmt()+"， 缴费时间："
//						+bo.getJfDate()+"， 缴费截至时间："+bo.getJzDate()+"</context><creTime>"
//						+sf.format(new Date())+"</creTime><creAuthor>"
//						+user.getLogName()+"</creAuthor></xml>";
//					try{
//						snd = String.format("%06d", snd.getBytes().length)+ snd;
//						Smsclient.SmsClient(equ.getIpAdd(), param.getParamValue(), snd, 6);
//					}catch (Exception e1) {
//						e1.printStackTrace();
//					}
				}
				
				request.setAttribute("message", "修改缴费单据信息成功！"+message);
				request.setAttribute("backurl",
						"/billsDim.do?method=queryTblBillsDim"+strurl);
				return mapping.findForward("SUCCESS");
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("backurl",
					"/billsDim.do?method=queryTblBillsDim"+strurl);
			return mapping.findForward("FAIL");
		}

	}

	public ActionForward removeTblBillsDim(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String BillsCode = request.getParameter("billCode");
		String roomCode = request.getParameter("roomCode");
		String itemCode = request.getParameter("itemCode");
		TblBillsDimForm TblBillsForm = (TblBillsDimForm) form;
		String strurl = getTblMenuDimService().getMenuString("/billsDim.do?method=queryTblBillsDim");
		this.getTblBillsDimService().removeBillsDim(BillsCode, itemCode, roomCode);
		
		//删除信息，同时删除信息发送表tbl_infostatus_dim的新增修改
//		getTblInfoStatusDimService().deleteList(" where info_code='"+BillsCode+"' and info_type='f'");
		SysLogger.logInfo(request, "删除缴费单据信息,缴费单据编号："+BillsCode);
		request.setAttribute("message", "删除缴费单据信息成功！");
		request.setAttribute("backurl", "/billsDim.do?method=queryTblBillsDim"+strurl);
		return mapping.findForward("SUCCESS");
	}

	public ActionForward viewTblBillsDim(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		String BillsCode = request.getParameter("billCode");
		String roomCode = request.getParameter("roomCode");
		String itemCode = request.getParameter("itemCode");
		TblBillsDimForm TblBillsForm = (TblBillsDimForm) form;
		TblBillsDimBo bo = this.getTblBillsDimService().findTblBillsDimById(BillsCode, itemCode, roomCode);
		BeanUtils.copyProperties(TblBillsForm, bo);
		List list = getTblItemDimService().findTblItemDimByWhere("");
		request.setAttribute("itemList", list);
		return mapping.findForward("viewTblBillsDim");
	}


	public ActionForward unSpecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "操作有误，请返回！");
		request.setAttribute("backurl", mapping.findForward("tblBillsDim")
				.getPath());
		return mapping.findForward("FAIL");
	}

}
