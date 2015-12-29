package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblRolemenuDimBo;
import com.viatt.zhjtpro.dao.ITblRolemenuDimDAO;
import com.viatt.zhjtpro.service.ITblRolemenuDimService;

public class TblRolemenuDimServiceSpringImpl implements ITblRolemenuDimService {
	private ITblRolemenuDimDAO tblRolemenuDimDAO;


	public ITblRolemenuDimDAO getTblRolemenuDimDAO() {
		return tblRolemenuDimDAO;
	}

	public void setTblRolemenuDimDAO(ITblRolemenuDimDAO tblRolemenuDimDAO) {
		this.tblRolemenuDimDAO = tblRolemenuDimDAO;
	}

	public void deleteTblRolemenuDimByMenuCode(String menuCode) {
		tblRolemenuDimDAO.deleteTblRolemenuDimByMenuCode(menuCode);
	}

	public void deleteTblRolemenuDimByRoleCode(String roleCode) throws Exception{
		tblRolemenuDimDAO.deleteTblRolemenuDimByRoleCode(roleCode);
	}

	public TblRolemenuDimBo save(TblRolemenuDimBo bo) {
		return tblRolemenuDimDAO.save(bo);
	}

	public List findTblRolemenuDimByWhere(String whereClause) throws Exception {
		return tblRolemenuDimDAO.findTblRolemenuDimByWhere(whereClause);
	}



}
