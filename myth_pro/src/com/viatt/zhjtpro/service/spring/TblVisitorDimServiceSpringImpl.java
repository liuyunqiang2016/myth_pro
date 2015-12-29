package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblVisitorDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblVisitorDimDAO;
import com.viatt.zhjtpro.service.ITblVisitorDimService;

public class TblVisitorDimServiceSpringImpl implements ITblVisitorDimService {
	private ITblVisitorDimDAO tblVisitorDimDAO;


	public ITblVisitorDimDAO getTblVisitorDimDAO() {
		return tblVisitorDimDAO;
	}

	public void setTblVisitorDimDAO(ITblVisitorDimDAO tblVisitorDimDAO) {
		this.tblVisitorDimDAO = tblVisitorDimDAO;
	}


	public Page findTblVisitorDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblVisitorDimDAO.findTblVisitorDim(start, num, whereClause);
		return page;
	}


	public TblVisitorDimBo save(TblVisitorDimBo bo) throws Exception {
		return tblVisitorDimDAO.save(bo);
	}

	public List findTblVisitorDimByWhere(String whereClause) throws Exception {
		return tblVisitorDimDAO.findTblVisitorDimByWhere(whereClause);
	}



}
