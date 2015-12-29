package com.viatt.zhjtpro.service.spring;

import java.util.List;

import org.hibernate.HibernateException;

import biz.common.IModel;

import com.ccb.net.models.I0002Model;
import com.ccb.net.models.OutModel;
import com.viatt.zhjtpro.bo.TblServiceDimBo;
import com.viatt.zhjtpro.common.GetId;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblServiceDimDAO;
import com.viatt.zhjtpro.service.ITblServiceDimService;

public class TblServiceDimServiceSpringImpl implements ITblServiceDimService {
	private ITblServiceDimDAO tblServiceDimDAO;

	public ITblServiceDimDAO getTblServiceDimDAO() {
		return tblServiceDimDAO;
	}

	public void setTblServiceDimDAO(ITblServiceDimDAO tblServiceDimDAO) {
		this.tblServiceDimDAO = tblServiceDimDAO;
	}

	public Page findTblServiceDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblServiceDimDAO.findTblServiceDim(start, num, whereClause);
		return page;
	}

	public TblServiceDimBo save(TblServiceDimBo bo) throws Exception {
		return tblServiceDimDAO.save(bo);
	}

	public List findTblServiceDimByWhere(String whereClause) throws Exception {
		return tblServiceDimDAO.findTblServiceDimByWhere(whereClause);
	}

	public TblServiceDimBo findTblServiceDimById(String TblServiceId) {
		return tblServiceDimDAO.getById(TblServiceId);
	}

	public void removeServiceDim(String TblServiceId) {
		tblServiceDimDAO.deleteTblServiceDim(TblServiceId);
	}

	public IModel OPFwxx(IModel bo) throws HibernateException {
		I0002Model model = (I0002Model) bo;
		OutModel outModel = new OutModel();
		try {
			TblServiceDimBo serbo = new TblServiceDimBo();
			serbo.setSerCode(GetId.randomID());
			serbo.setTitile(model.getSerTitle());
			serbo.setType(model.getSerType().toUpperCase());
			serbo.setContext(model.getContext());
			serbo.setCreateUsr(model.getCreAuthor());
			serbo.setCreateTime(model.getCreTime());
			serbo.setSerExp(model.getCreTel());
			serbo.setEquCode(model.getEquId());
			serbo.setSerId(model.getSerId());
			serbo.setChkState("02");
			tblServiceDimDAO.save(serbo);
			outModel.setMsgId("I0000");
			outModel.setReCode("000000");
			outModel.setReMsg("���ճɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outModel;
	}
}
