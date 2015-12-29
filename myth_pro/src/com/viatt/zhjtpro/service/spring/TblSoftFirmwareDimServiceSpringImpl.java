package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblSoftDimBo;
import com.viatt.zhjtpro.bo.TblSoftFirmwareDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblSoftFirmwareDimDAO;
import com.viatt.zhjtpro.service.ITblSoftFirmwareDimService;

public class TblSoftFirmwareDimServiceSpringImpl implements ITblSoftFirmwareDimService {
	private ITblSoftFirmwareDimDAO tblSoftFirmwareDimDAO;


	public ITblSoftFirmwareDimDAO getTblSoftFirmwareDimDAO() {
		return tblSoftFirmwareDimDAO;
	}

	public void setTblSoftFirmwareDimDAO(ITblSoftFirmwareDimDAO tblSoftFirmwareDimDAO) {
		this.tblSoftFirmwareDimDAO = tblSoftFirmwareDimDAO;
	}


	public Page findTblSoftFirmwareDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblSoftFirmwareDimDAO.findTblSoftFirmwareDim(start, num, whereClause);
		return page;
	}


	public TblSoftFirmwareDimBo save(TblSoftFirmwareDimBo bo) throws Exception {
		return tblSoftFirmwareDimDAO.save(bo);
	}

	public List findTblSoftFirmwareDimByWhere(String whereClause) throws Exception {
		return tblSoftFirmwareDimDAO.findTblSoftFirmwareDimByWhere(whereClause);
	}

	public TblSoftFirmwareDimBo findTblSoftFirmwareDimById(String TblSoftId) {
		return tblSoftFirmwareDimDAO.getById(TblSoftId);
	}

	public void removeSoftFirmwareDim(String TblSoftId) {
		tblSoftFirmwareDimDAO.deleteTblSoftFirmwareDim(TblSoftId);
	}

}
