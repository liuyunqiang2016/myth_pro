package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblParamDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblParamDimDAO;
import com.viatt.zhjtpro.service.ITblParamDimService;

public class TblParamDimServiceSpringImpl implements ITblParamDimService {
	private ITblParamDimDAO tblParamDimDAO;


	public ITblParamDimDAO getTblParamDimDAO() {
		return tblParamDimDAO;
	}

	public void setTblParamDimDAO(ITblParamDimDAO tblParamDimDAO) {
		this.tblParamDimDAO = tblParamDimDAO;
	}


	public Page findTblParamDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblParamDimDAO.findTblParamDim(start, num, whereClause);
		return page;
	}


	public TblParamDimBo save(TblParamDimBo bo) throws Exception {
		return tblParamDimDAO.save(bo);
	}

	public List findTblParamDimByWhere(String whereClause) throws Exception {
		return tblParamDimDAO.findTblParamDimByWhere(whereClause);
	}

	public TblParamDimBo findTblParamDimById(String TblParamId) {
		return tblParamDimDAO.getById(TblParamId);
	}

	public void removeParamDim(String TblParamId) {
		tblParamDimDAO.deleteTblParamDim(TblParamId);
	}

}
