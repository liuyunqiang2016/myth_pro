package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblCommDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblCommDimDAO;
import com.viatt.zhjtpro.service.ITblCommDimService;

public class TblCommDimServiceSpringImpl implements ITblCommDimService 
{
	private ITblCommDimDAO tblCommDimDAO;


	public ITblCommDimDAO getTblCommDimDAO() {
		return tblCommDimDAO;
	}

	public void setTblCommDimDAO(ITblCommDimDAO tblCommDimDAO) {
		this.tblCommDimDAO = tblCommDimDAO;
	}


	public Page findTblCommDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblCommDimDAO.findTblCommDim(start, num, whereClause);
		return page;
	}


	public TblCommDimBo save(TblCommDimBo bo) throws Exception {
		return tblCommDimDAO.save(bo);
	}

	public List<TblCommDimBo> findTblCommDimByWhere(String whereClause) throws Exception {
		return tblCommDimDAO.findTblCommDimByWhere(whereClause);
	}

	public TblCommDimBo findTblCommDimById(String TblCommId) {
		return tblCommDimDAO.getById(TblCommId);
	}

	public void removeGrgDim(String TblCommId) {
		tblCommDimDAO.deleteTblCommDim(TblCommId);
	}

}
