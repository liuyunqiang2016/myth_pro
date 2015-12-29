package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblFingStatusDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblFingStatusDimDAO;
import com.viatt.zhjtpro.service.ITblFingStatusDimService;

public class TblFingStatusDimServiceSpringImpl implements ITblFingStatusDimService {
	private ITblFingStatusDimDAO tblFingStatusDimDAO;


	public ITblFingStatusDimDAO getTblInfoStatusDimDAO() {
		return tblFingStatusDimDAO;
	}

	public void setTblFingStatusDimDAO(ITblFingStatusDimDAO tblFingStatusDimDAO) {
		this.tblFingStatusDimDAO = tblFingStatusDimDAO;
	}


	public Page findTblFingStatusDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblFingStatusDimDAO.findTblFingStatusDim(start, num, whereClause);
		return page;
	}


	public TblFingStatusDimBo save(TblFingStatusDimBo bo) throws Exception {
		return tblFingStatusDimDAO.save(bo);
	}

	public List findTblFingStatusDimByWhere(String whereClause) throws Exception {
		return tblFingStatusDimDAO.findTblFingStatusDimByWhere(whereClause);
	}

	public TblFingStatusDimBo findTblFingStatusDimById(String TblFingStatusId) {
		return tblFingStatusDimDAO.getById(TblFingStatusId);
	}

	public void deleteList(String whereClause) throws Exception{
		tblFingStatusDimDAO.deleteList(whereClause);
	}
}
