package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblSoftDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblSoftDimDAO;
import com.viatt.zhjtpro.service.ITblSoftDimService;

public class TblSoftDimServiceSpringImpl implements ITblSoftDimService {
	private ITblSoftDimDAO tblSoftDimDAO;


	public ITblSoftDimDAO getTblSoftDimDAO() {
		return tblSoftDimDAO;
	}

	public void setTblSoftDimDAO(ITblSoftDimDAO tblSoftDimDAO) {
		this.tblSoftDimDAO = tblSoftDimDAO;
	}


	public Page findTblSoftDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblSoftDimDAO.findTblSoftDim(start, num, whereClause);
		return page;
	}


	public TblSoftDimBo save(TblSoftDimBo bo) throws Exception {
		return tblSoftDimDAO.save(bo);
	}

	public List findTblSoftDimByWhere(String whereClause) throws Exception {
		return tblSoftDimDAO.findTblSoftDimByWhere(whereClause);
	}

	public TblSoftDimBo findTblSoftDimById(String TblSoftId) {
		return tblSoftDimDAO.getById(TblSoftId);
	}

	public void removeSoftDim(String TblSoftId) {
		tblSoftDimDAO.deleteTblSoftDim(TblSoftId);
	}

}
