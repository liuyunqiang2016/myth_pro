package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblLogDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblLogDimDAO;
import com.viatt.zhjtpro.service.ITblLogDimService;

public class TblLogDimServiceSpringImpl implements ITblLogDimService {
	private ITblLogDimDAO tblLogDimDAO;


	public ITblLogDimDAO getTblLogDimDAO() {
		return tblLogDimDAO;
	}

	public void setTblLogDimDAO(ITblLogDimDAO tblLogDimDAO) {
		this.tblLogDimDAO = tblLogDimDAO;
	}


	public Page findTblLogDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblLogDimDAO.findTblLogDim(start, num, whereClause);
		return page;
	}


	public TblLogDimBo save(TblLogDimBo bo) throws Exception {
		return tblLogDimDAO.save(bo);
	}

	public List findTblLogDimByWhere(String whereClause) throws Exception {
		return tblLogDimDAO.findTblLogDimByWhere(whereClause);
	}

}
