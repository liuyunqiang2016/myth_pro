package com.viatt.zhjtpro.service.spring;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import biz.common.BGMLogger;
import biz.common.IModel;

import com.ccb.net.models.I0003Model;
import com.ccb.net.models.I0004Model;
import com.ccb.net.models.I0005Model;
import com.ccb.net.models.I1001Model;
import com.ccb.net.models.OutModel;
import com.viatt.zhjtpro.bo.TblEquDimBo;
import com.viatt.zhjtpro.bo.TblParamDimBo;
import com.viatt.zhjtpro.bo.TblSoftDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.common.PropertiesReader;
import com.viatt.zhjtpro.common.Smsclient;
import com.viatt.zhjtpro.dao.ITblEquDimDAO;
import com.viatt.zhjtpro.dao.ITblSoftDimDAO;
import com.viatt.zhjtpro.service.ITblEquDimService;
import com.viatt.zhjtpro.service.ITblParamDimService;

public class TblEquDimServiceSpringImpl implements ITblEquDimService {
	private ITblEquDimDAO tblEquDimDAO;
	private ITblSoftDimDAO tblSoftDimDAO;
	private ITblParamDimService paramService ;
	
	private static final long  INTERVAL_TIME = 35 * 60 * 1000 ;   //����豸������״̬(35������֮��Ϊ���ߡ�ԭ���������)
	
	public void setParamService(ITblParamDimService paramService) {
		this.paramService = paramService;
	}
	
	public ITblParamDimService getParamService() {
		return paramService;
	}

	/**
	 * @return the tblSoftDimDAO
	 */
	public ITblSoftDimDAO getTblSoftDimDAO() {
		return tblSoftDimDAO;
	}

	/**
	 * @param tblSoftDimDAO the tblSoftDimDAO to set
	 */
	public void setTblSoftDimDAO(ITblSoftDimDAO tblSoftDimDAO) {
		this.tblSoftDimDAO = tblSoftDimDAO;
	}

	public ITblEquDimDAO getTblEquDimDAO() {
		return tblEquDimDAO;
	}

	public void setTblEquDimDAO(ITblEquDimDAO tblEquDimDAO) {
		this.tblEquDimDAO = tblEquDimDAO;
	}


	public Page findTblEquDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblEquDimDAO.findTblEquDim(start, num, whereClause);
		return page;
	}


	public TblEquDimBo save(TblEquDimBo bo) throws Exception {
		return tblEquDimDAO.save(bo);
	}

	public List<TblEquDimBo> findTblEquDimByWhere(String whereClause) throws Exception {
		return tblEquDimDAO.findTblEquDimByWhere(whereClause);
	}

	public TblEquDimBo findTblEquDimById(String TblEquId) {
		return tblEquDimDAO.getById(TblEquId);
	}

	public void removeEquDim(String TblEquId) {
		tblEquDimDAO.deleteTblEquDim(TblEquId);
	}
	
	public IModel OPEqzt(IModel bo) throws HibernateException{
		I0004Model model = (I0004Model)bo;
		I0003Model outModel = new I0003Model();
		String upDir = PropertiesReader.getProperty("upDir");
		String appDir = PropertiesReader.getProperty("appDir");
		try{
			//����豸״̬
			Date date = new Date();
			Long ldate = date.getTime();
			//��ʱʱ������
			ldate = ldate - INTERVAL_TIME;
			String where = " where lastdate < " + ldate;
			List elist = tblEquDimDAO.findTblEquDimByWhere(where);
			if(elist!=null&&elist.size()>0){
				for(int i=0;i<elist.size();i++){
					TblEquDimBo equ = (TblEquDimBo)elist.get(i);
					equ.setEquState("0");
					tblEquDimDAO.save(equ);
				}
			}
			
			//��ȡ�豸����
			List eqlist = tblEquDimDAO.findTblEquDimByWhere(" where equ_code='"+model.getEquId()+"'");
			if(eqlist==null || eqlist.size()==0){
				outModel.setMsgId("I0000");
				outModel.setReCode("999999");
				outModel.setReMsg("�豸������");
			}else{
				TblEquDimBo ebo = (TblEquDimBo)eqlist.get(0);
				//�����豸״̬
				ebo.setEquState("1");
				Date date1 = new Date();
				ebo.setLastdate(Long.toString(date1.getTime()));
				tblEquDimDAO.save(ebo);
				
				List softlist = tblSoftDimDAO.findTblSoftDimByWhere(" where soft_state='1'" +
						" and equ_type='"+ebo.getEquType()+"'");
				if(softlist==null || softlist.size()==0){
					outModel.setMsgId("I0000");
					outModel.setReCode("999999");
					outModel.setReMsg("�����Ϣ������");
				}else{
					TblSoftDimBo soft = (TblSoftDimBo)softlist.get(0);
					String scode = soft.getSoftVision();
					if(Double.parseDouble(scode)>Double.parseDouble(model.getSoftVis())){
						outModel.setDownUrl(appDir+upDir+"/soft/"+soft.getSoftFn());
						outModel.setFileName(soft.getSoftName());
						outModel.setSoftID(soft.getSoftCode());
						outModel.setFileSize(soft.getSoftSize());
						outModel.setSoftVis(soft.getSoftVision());
						outModel.setMsgId("I0003");
						outModel.setReCode("000000");
						outModel.setReMsg("���ճɹ�");
					}else{
						outModel.setMsgId("I0000");
						outModel.setReCode("999999");
						outModel.setReMsg("��ǰ����Ѿ������°汾");
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			outModel.setMsgId("I0000");
			outModel.setReCode("999999");
			outModel.setReMsg("ϵͳ����");
			return outModel;
		}
		return outModel;
	}
	public IModel OPEqjr(IModel bo) throws HibernateException{
		I0005Model model = (I0005Model)bo;
		OutModel outModel = new OutModel();
		TblEquDimBo equ = tblEquDimDAO.getById(model.getEquId());
		if(equ==null){
			//�ж��豸IP�Ƿ��ظ�
			try{
		
				
				List list = tblEquDimDAO.findTblEquDimByWhere(" where ip_add like '%"+model.getIpAdd()+"%'");
				if(list!=null&&list.size()>0){
					outModel.setMsgId("I0000");
					outModel.setReCode("999999");
					outModel.setReMsg("IP��ַ�Ѵ���");
				}else{
					
					outModel.setMsgId("I0000");
					outModel.setReCode("000000");
					outModel.setReMsg("���ճɹ�");
				}
				Date date = new Date();
				equ = new TblEquDimBo();
				equ.setEquCode(model.getEquId());
				equ.setEquType(model.getEquType());
				equ.setIpAdd(model.getIpAdd());
				equ.setEquAdd(model.getRoomID());
				equ.setEquAccid(model.getAccessID());
				equ.setEquState(model.getEquState());
				equ.setMacAdd(model.getEquMac());
				equ.setAreaID(model.getAreaID());
				equ.setBuldID(model.getBuldID());
				equ.setCommID(model.getCommID());
				equ.setUnitID(model.getUnitID());
				equ.setLastdate(Long.toString(date.getTime()));
				tblEquDimDAO.save(equ);
			}catch (Exception e) {
				e.printStackTrace();
				outModel.setMsgId("I0000");
				outModel.setReCode("999999");
				outModel.setReMsg("ϵͳ����");
				return outModel;
			}
		}else{
			equ.setEquType(model.getEquType());
			equ.setEquAdd(model.getRoomID());
			equ.setEquAccid(model.getAccessID());
			equ.setMacAdd(model.getEquMac());
			equ.setAreaID(model.getAreaID());
			equ.setBuldID(model.getBuldID());
			equ.setCommID(model.getCommID());
			equ.setUnitID(model.getUnitID());
			equ.setEquState(model.getEquState());
			Date date = new Date();
			equ.setLastdate(Long.toString(date.getTime()));
			tblEquDimDAO.save(equ);
			
			outModel.setMsgId("I0000");
			outModel.setReCode("999999");
			outModel.setReMsg("�豸���Ѵ���");
		}
		return outModel;
	}
	
	/*
	 * ������
	 */
	public void OPFpop(IModel bo) throws HibernateException{
		I1001Model model = (I1001Model)bo;
		TblParamDimBo param = paramService.findTblParamDimById("100001");

		try {
			// ����֪ͨ����
			String sndbuf = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I1001</msgId></xml>";
			sndbuf = String.format("%06d", sndbuf.getBytes().length) + sndbuf;

			Smsclient.SmsClient(model.getIpAdd(), param.getParamValue().trim(), sndbuf, 6);
			BGMLogger.LogInfo("Sent IpAdd: " + model.getIpAdd() + sndbuf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
