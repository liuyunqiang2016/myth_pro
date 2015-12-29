package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblNoticeDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblNoticeDimDAO;
import com.viatt.zhjtpro.service.ITblNoticeDimService;

public class TblNoticeDimServiceSpringImpl implements ITblNoticeDimService {
	private ITblNoticeDimDAO tblNoticeDimDAO;


	public ITblNoticeDimDAO getTblNoticeDimDAO() {
		return tblNoticeDimDAO;
	}

	public void setTblNoticeDimDAO(ITblNoticeDimDAO tblNoticeDimDAO) {
		this.tblNoticeDimDAO = tblNoticeDimDAO;
	}


	public Page findTblNoticeDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblNoticeDimDAO.findTblNoticeDim(start, num, whereClause);
		return page;
	}


	public TblNoticeDimBo save(TblNoticeDimBo bo) throws Exception {
		return tblNoticeDimDAO.save(bo);
	}

	public List findTblNoticeDimByWhere(String whereClause) throws Exception {
		return tblNoticeDimDAO.findTblNoticeDimByWhere(whereClause);
	}

	public TblNoticeDimBo findTblNoticeDimById(String TblNoticeId) {
		return tblNoticeDimDAO.getById(TblNoticeId);
	}

	public void removeNoticeDim(String TblNoticeId) {
		tblNoticeDimDAO.deleteTblNoticeDim(TblNoticeId);
	}

}
