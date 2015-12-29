package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblItemDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblItemDimDAO;
import com.viatt.zhjtpro.service.ITblItemDimService;

public class TblItemDimServiceSpringImpl implements ITblItemDimService {
	private ITblItemDimDAO tblItemDimDAO;


	public ITblItemDimDAO getTblItemDimDAO() {
		return tblItemDimDAO;
	}

	public void setTblItemDimDAO(ITblItemDimDAO tblItemDimDAO) {
		this.tblItemDimDAO = tblItemDimDAO;
	}


	public Page findTblItemDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblItemDimDAO.findTblItemDim(start, num, whereClause);
		return page;
	}


	public TblItemDimBo save(TblItemDimBo bo) throws Exception {
		return tblItemDimDAO.save(bo);
	}

	public List findTblItemDimByWhere(String whereClause) throws Exception {
		return tblItemDimDAO.findTblItemDimByWhere(whereClause);
	}

	public TblItemDimBo findTblItemDimById(String TblItemId) {
		return tblItemDimDAO.getById(TblItemId);
	}

	public void removeItemDim(String TblItemId) {
		tblItemDimDAO.deleteTblItemDim(TblItemId);
	}

}
