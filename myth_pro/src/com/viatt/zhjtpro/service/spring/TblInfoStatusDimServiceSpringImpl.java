package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblInfoStatusDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblInfoStatusDimDAO;
import com.viatt.zhjtpro.service.ITblInfoStatusDimService;

public class TblInfoStatusDimServiceSpringImpl implements ITblInfoStatusDimService {
	private ITblInfoStatusDimDAO tblInfoStatusDimDAO;


	public ITblInfoStatusDimDAO getTblInfoStatusDimDAO() {
		return tblInfoStatusDimDAO;
	}

	public void setTblInfoStatusDimDAO(ITblInfoStatusDimDAO tblInfoStatusDimDAO) {
		this.tblInfoStatusDimDAO = tblInfoStatusDimDAO;
	}


	public Page findTblInfoStatusDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblInfoStatusDimDAO.findTblInfoStatusDim(start, num, whereClause);
		return page;
	}


	public TblInfoStatusDimBo save(TblInfoStatusDimBo bo) throws Exception {
		return tblInfoStatusDimDAO.save(bo);
	}

	public List findTblInfoStatusDimByWhere(String whereClause) throws Exception {
		return tblInfoStatusDimDAO.findTblInfoStatusDimByWhere(whereClause);
	}

	public TblInfoStatusDimBo findTblInfoStatusDimById(String TblInfoStatusId) {
		return tblInfoStatusDimDAO.getById(TblInfoStatusId);
	}

	public void deleteList(String whereClause) throws Exception{
		tblInfoStatusDimDAO.deleteList(whereClause);
	}
}
