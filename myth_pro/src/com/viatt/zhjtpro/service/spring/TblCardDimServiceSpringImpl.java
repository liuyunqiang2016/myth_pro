package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblCardDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblCardDimDAO;
import com.viatt.zhjtpro.service.ITblCardDimService;

public class TblCardDimServiceSpringImpl implements ITblCardDimService {
	private ITblCardDimDAO tblCardDimDAO;


	public ITblCardDimDAO getTblCardDimDAO() {
		return tblCardDimDAO;
	}

	public void setTblCardDimDAO(ITblCardDimDAO tblCardDimDAO) {
		this.tblCardDimDAO = tblCardDimDAO;
	}


	public Page findTblCardDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblCardDimDAO.findTblCardDim(start, num, whereClause);
		return page;
	}


	public TblCardDimBo save(TblCardDimBo bo) throws Exception {
		return tblCardDimDAO.save(bo);
	}

	public List findTblCardDimByWhere(String whereClause) throws Exception {
		return tblCardDimDAO.findTblCardDimByWhere(whereClause);
	}

	public TblCardDimBo findTblCardDimById(String CardCode) {
		return tblCardDimDAO.getById(CardCode);
	}

	public void removeCardDim(String CardCode) {
		tblCardDimDAO.deleteTblCardDim(CardCode);
	}

}
