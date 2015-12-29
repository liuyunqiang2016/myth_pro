package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblAdDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblAdDimDAO;
import com.viatt.zhjtpro.service.ITblAdDimService;

public class TblAdDimServiceSpringImpl implements ITblAdDimService {
	private ITblAdDimDAO tblAdDimDAO;


	public ITblAdDimDAO getTblAdDimDAO() {
		return tblAdDimDAO;
	}

	public void setTblAdDimDAO(ITblAdDimDAO tblAdDimDAO) {
		this.tblAdDimDAO = tblAdDimDAO;
	}


	public Page findTblAdDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblAdDimDAO.findTblAdDim(start, num, whereClause);
		return page;
	}


	public TblAdDimBo save(TblAdDimBo bo) throws Exception {
		return tblAdDimDAO.save(bo);
	}

	public List findTblAdDimByWhere(String whereClause) throws Exception {
		return tblAdDimDAO.findTblAdDimByWhere(whereClause);
	}

	public TblAdDimBo findTblAdDimById(String TblAdId) {
		return tblAdDimDAO.getById(TblAdId);
	}

	public void removeAdDim(String TblAdId) {
		tblAdDimDAO.deleteTblAdDim(TblAdId);
	}

}
