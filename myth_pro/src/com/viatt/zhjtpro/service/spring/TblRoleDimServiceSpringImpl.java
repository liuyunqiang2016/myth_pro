package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblRoleDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblRoleDimDAO;
import com.viatt.zhjtpro.service.ITblRoleDimService;

public class TblRoleDimServiceSpringImpl implements ITblRoleDimService {
	private ITblRoleDimDAO tblRoleDimDAO;


	public ITblRoleDimDAO getTblRoleDimDAO() {
		return tblRoleDimDAO;
	}

	public void setTblRoleDimDAO(ITblRoleDimDAO tblRoleDimDAO) {
		this.tblRoleDimDAO = tblRoleDimDAO;
	}


	public Page findTblRoleDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblRoleDimDAO.findTblRoleDim(start, num, whereClause);
		return page;
	}


	public TblRoleDimBo save(TblRoleDimBo bo) throws Exception {
		return tblRoleDimDAO.save(bo);
	}

	public List findTblRoleDimByWhere(String whereClause) throws Exception {
		return tblRoleDimDAO.findTblRoleDimByWhere(whereClause);
	}

	public TblRoleDimBo findTblRoleDimById(String TblRoleId) {
		return tblRoleDimDAO.getById(TblRoleId);
	}

	public void removeRoleDim(String TblRoleId) {
		tblRoleDimDAO.deleteTblRoleDim(TblRoleId);
	}

}
