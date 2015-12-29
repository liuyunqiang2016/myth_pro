package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblCalDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblCalDimDAO;
import com.viatt.zhjtpro.service.ITblCalDimService;

public class TblCalDimServiceSpringImpl implements ITblCalDimService {
	private ITblCalDimDAO tblCalDimDAO;


	public ITblCalDimDAO getTblCalDimDAO() {
		return tblCalDimDAO;
	}

	public void setTblCalDimDAO(ITblCalDimDAO tblCalDimDAO) {
		this.tblCalDimDAO = tblCalDimDAO;
	}


	public Page findTblCalDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblCalDimDAO.findTblCalDim(start, num, whereClause);
		return page;
	}


	public TblCalDimBo save(TblCalDimBo bo) throws Exception {
		return tblCalDimDAO.save(bo);
	}

	public List findTblCalDimByWhere(String whereClause) throws Exception {
		return tblCalDimDAO.findTblCalDimByWhere(whereClause);
	}

	public TblCalDimBo findTblCalDimById(String TblCalId) {
		return tblCalDimDAO.getById(TblCalId);
	}

	public void removeCalDim(String TblCalId) {
		tblCalDimDAO.deleteTblCalDim(TblCalId);
	}

}
