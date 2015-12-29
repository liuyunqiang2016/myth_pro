package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblHousetypeDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblHousetypeDimDAO;
import com.viatt.zhjtpro.service.ITblHousetypeDimService;

public class TblHousetypeDimServiceSpringImpl implements ITblHousetypeDimService {
	private ITblHousetypeDimDAO tblHousetypeDimDAO;


	public ITblHousetypeDimDAO getTblHousetypeDimDAO() {
		return tblHousetypeDimDAO;
	}

	public void setTblHousetypeDimDAO(ITblHousetypeDimDAO tblHousetypeDimDAO) {
		this.tblHousetypeDimDAO = tblHousetypeDimDAO;
	}


	public Page findTblHousetypeDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblHousetypeDimDAO.findTblHousetypeDim(start, num, whereClause);
		return page;
	}


	public TblHousetypeDimBo save(TblHousetypeDimBo bo) throws Exception {
		return tblHousetypeDimDAO.save(bo);
	}

	public List findTblHousetypeDimByWhere(String whereClause) throws Exception {
		return tblHousetypeDimDAO.findTblHousetypeDimByWhere(whereClause);
	}

	public TblHousetypeDimBo findTblHousetypeDimById(String TblHousetypeId) {
		return tblHousetypeDimDAO.getById(TblHousetypeId);
	}

	public void removeDim(String TblHousetypeId) {
		tblHousetypeDimDAO.deleteTblHousetypeDim(TblHousetypeId);
	}

	public String getMaxId(){
		return tblHousetypeDimDAO.getMaxId();
	}
}
