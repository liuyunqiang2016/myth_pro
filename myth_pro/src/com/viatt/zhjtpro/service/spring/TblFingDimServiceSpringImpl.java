package com.viatt.zhjtpro.service.spring;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import biz.common.BGMLogger;
import biz.common.IModel;

import com.ccb.net.models.I0003Model;
import com.ccb.net.models.I0010Model;
import com.viatt.zhjtpro.bo.TblEquDimBo;
import com.viatt.zhjtpro.bo.TblFingDimBo;
import com.viatt.zhjtpro.bo.TblParamDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.Smsclient;
import com.viatt.zhjtpro.dao.ITblFingDimDAO;
import com.viatt.zhjtpro.service.ITblEquDimService;
import com.viatt.zhjtpro.service.ITblFingDimService;
import com.viatt.zhjtpro.service.ITblParamDimService;

public class TblFingDimServiceSpringImpl implements ITblFingDimService {
	private ITblFingDimDAO tblFingDimDAO;
	private ITblParamDimService paramService ;
	private ITblEquDimService equDimService;

	public ITblFingDimDAO getTblFingDimDAO() {
		return tblFingDimDAO;
	}

	public void setTblFingDimDAO(ITblFingDimDAO tblFingDimDAO) {
		this.tblFingDimDAO = tblFingDimDAO;
	}

	public ITblParamDimService getParamService() {
		return paramService;
	}

	public void setParamService(ITblParamDimService paramService) {
		this.paramService = paramService;
	}

	public ITblEquDimService getEquDimService() {
		return equDimService;
	}

	public void setEquDimService(ITblEquDimService equDimService) {
		this.equDimService = equDimService;
	}

	public Page findTblFingDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblFingDimDAO.findTblFingDim(start, num, whereClause);
		return page;
	}


	public TblFingDimBo save(TblFingDimBo bo) throws Exception {
		return tblFingDimDAO.save(bo);
	}

	public List findTblFingDimByWhere(String whereClause) throws Exception {
		return tblFingDimDAO.findTblFingDimByWhere(whereClause);
	}

	public TblFingDimBo findTblFingDimById(String FingCode) {
		return tblFingDimDAO.getById(FingCode);
	}

	public void removeFingDim(String usrNo) {
		tblFingDimDAO.deleteTblFingDim(usrNo);
	}

	public List<TblFingDimBo> findTblFingDimByEquCode(String equCode) {
		return tblFingDimDAO.findTblFingDimByEquCode(equCode);
	}

	/*
	 * 发送一系列指纹到门口机
	 */
	public void sendFingerListToOutdoor(ITblParamDimService paramService, ITblEquDimService equDimService, String equID) throws UnknownHostException, IOException 	{
		
		TblParamDimBo param = paramService.findTblParamDimById("100001");
		TblEquDimBo equ = equDimService.findTblEquDimById(equID);
		
		String hintStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0000</msgId><reCode>000000</reCode><reMsg>发送成功</reMsg></xml>" ;

		try{
			hintStr = String.format("%06d", hintStr.getBytes().length)+ hintStr;
			Smsclient.SmsClient(equ.getIpAdd(), param.getParamValue().trim(), hintStr, 6);
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		List<TblFingDimBo> list = findTblFingDimByEquCode(equID);
		BGMLogger.LogInfo("TblFingDimBo Size" + list.size());
//System.out.println("FingerBoSize--->" +list.size());
//		List<TblFingDimBo> list = new ArrayList<TblFingDimBo>();
		
		if (list != null && list.size() >0) {
			for (TblFingDimBo bo : list) {
				String sndbuf = "";
				
				// 发送通知报文
				sndbuf = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0009</msgId><cardNo>"
					+ bo.getUsrNo()
					+ "</cardNo><fing1>"
					+ bo.getFingImg1()
					+ "</fing1><fing2>"
					+ bo.getFingImg2()
					+ "</fing2><updType>1</updType></xml>";
				sndbuf = String.format("%06d", sndbuf.getBytes().length) + sndbuf;
				
				if (equ != null) {
					Smsclient.SmsClient(equ.getIpAdd(), param.getParamValue().trim(), sndbuf, 6);
					try {
						Thread.sleep(2000) ;  //隔一秒再发送
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}
		
	}

	public void OPEfds(IModel bo) {
		I0010Model model = (I0010Model) bo;
		I0003Model outModel = new I0003Model();

		try {
			sendFingerListToOutdoor(paramService, equDimService, model
					.getEquId());
			} catch (Exception e) {
/*			e.printStackTrace();
			outModel.setMsgId("I0000");
			outModel.setReCode("999999");
			outModel.setReMsg("系统故障");
			return outModel;*/
			e.printStackTrace() ;
		}
//		return outModel;
	}
}
