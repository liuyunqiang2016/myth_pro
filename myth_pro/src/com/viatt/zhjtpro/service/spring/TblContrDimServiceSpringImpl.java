package com.viatt.zhjtpro.service.spring;

import com.viatt.zhjtpro.bo.TblContrDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblContrDimDAO;
import com.viatt.zhjtpro.service.ITblContrDimService;

public class TblContrDimServiceSpringImpl implements ITblContrDimService {
	private ITblContrDimDAO tblContrDimDAO;


	public ITblContrDimDAO getTblContrDimDAO() {
		return tblContrDimDAO;
	}

	public void setTblContrDimDAO(ITblContrDimDAO tblContrDimDAO) {
		this.tblContrDimDAO = tblContrDimDAO;
	}


	public Page findTblContrDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblContrDimDAO.findTblContrDim(start, num, whereClause);
		return page;
	}

	public TblContrDimBo save(TblContrDimBo bo) throws Exception {
		return tblContrDimDAO.save(bo);
	}

}
