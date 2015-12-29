package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblBillsDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblBillsDimDAO;
import com.viatt.zhjtpro.service.ITblBillsDimService;

public class TblBillsDimServiceSpringImpl implements ITblBillsDimService {
	private ITblBillsDimDAO tblBillsDimDAO;


	public ITblBillsDimDAO getTblBillsDimDAO() {
		return tblBillsDimDAO;
	}

	public void setTblBillsDimDAO(ITblBillsDimDAO tblBillsDimDAO) {
		this.tblBillsDimDAO = tblBillsDimDAO;
	}


	public Page findTblBillsDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblBillsDimDAO.findTblBillsDim(start, num, whereClause);
		return page;
	}


	public TblBillsDimBo save(TblBillsDimBo bo) throws Exception {
		return tblBillsDimDAO.save(bo);
	}

	public List findTblBillsDimByWhere(String whereClause) throws Exception {
		return tblBillsDimDAO.findTblBillsDimByWhere(whereClause);
	}

	public TblBillsDimBo findTblBillsDimById(String billCode,String itemCode,String roomCode) {
		return tblBillsDimDAO.getById(billCode, itemCode, roomCode);
	}

	public void removeBillsDim(String billCode,String itemCode,String roomCode) {
		tblBillsDimDAO.deleteTblBillsDim(billCode, itemCode, roomCode);
	}

}
