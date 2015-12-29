package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblInnerEquOpBo;
import com.viatt.zhjtpro.dao.ITblInnerEquOpDAO;
import com.viatt.zhjtpro.service.ITblInnerEquOpService;

public class TblInnerEquOpServiceSpringImpl implements ITblInnerEquOpService {
	private ITblInnerEquOpDAO tblInnerEquOpDAO;

	public ITblInnerEquOpDAO getTblInnerEquOpDAO() {
		return tblInnerEquOpDAO;
	}

	public void setTblInnerEquOpDAO(ITblInnerEquOpDAO tblInnerEquOpDAO) {
		this.tblInnerEquOpDAO = tblInnerEquOpDAO;
	}

	public void delete(String innerid, String outerid, String optype)
			throws Exception {
		tblInnerEquOpDAO.delete(innerid, outerid, optype);
		
	}

	public List findTblInnerEquOpByWhere(String whereClause) throws Exception {
		// TODO Auto-generated method stub
		return tblInnerEquOpDAO.findTblInnerEquOpByWhere(whereClause);
	}

	public TblInnerEquOpBo getById(String innerid, String outerid, String optype) {
		// TODO Auto-generated method stub
		return tblInnerEquOpDAO.getById(innerid, outerid, optype);
	}

	public TblInnerEquOpBo save(TblInnerEquOpBo bo) {
		// TODO Auto-generated method stub
		return tblInnerEquOpDAO.save(bo);
	}


}
