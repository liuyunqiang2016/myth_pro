package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblInnerStatusDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblInnerStatusDimDAO;
import com.viatt.zhjtpro.service.ITblInnerStatusDimService;

public class TblInnerStatusDimServiceSpringImpl implements ITblInnerStatusDimService {
	private ITblInnerStatusDimDAO tblInnerStatusDimDAO;

	public ITblInnerStatusDimDAO getTblInnerStatusDimDAO() {
		return tblInnerStatusDimDAO;
	}

	public void setTblInnerStatusDimDAO(ITblInnerStatusDimDAO tblInnerStatusDimDAO) {
		this.tblInnerStatusDimDAO = tblInnerStatusDimDAO;
	}

	public void delete(String innerid, String outerid, String optype)
			throws Exception {
		// TODO Auto-generated method stub
		tblInnerStatusDimDAO.delete(innerid, outerid, optype);
	}

	public Page findTblInnerStatusDim(int start, int num, String whereClause)
			throws Exception {
		// TODO Auto-generated method stub
		return tblInnerStatusDimDAO.findTblInnerStatusDim(start, num, whereClause);
	}

	public List findTblInnerStatusDimByWhere(String whereClause)
			throws Exception {
		// TODO Auto-generated method stub
		return tblInnerStatusDimDAO.findTblInnerStatusDimByWhere(whereClause);
	}

	public TblInnerStatusDimBo getById(String innerid, String outerid,
			String optype) {
		// TODO Auto-generated method stub
		return tblInnerStatusDimDAO.getById(innerid, outerid, optype);
	}

	public TblInnerStatusDimBo save(TblInnerStatusDimBo bo) {
		// TODO Auto-generated method stub
		return tblInnerStatusDimDAO.save(bo);
	}

	public void deleteList(String whereClause) throws Exception{
		tblInnerStatusDimDAO.deleteList(whereClause);
	}

}
