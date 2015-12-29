package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblAreaDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblAreaDimDAO;
import com.viatt.zhjtpro.service.ITblAreaDimService;

public class TblAreaDimServiceSpringImpl implements ITblAreaDimService {
	private ITblAreaDimDAO tblAreaDimDAO;


	public ITblAreaDimDAO getTblAreaDimDAO() {
		return tblAreaDimDAO;
	}

	public void setTblAreaDimDAO(ITblAreaDimDAO tblAreaDimDAO) {
		this.tblAreaDimDAO = tblAreaDimDAO;
	}


	public Page findTblAreaDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblAreaDimDAO.findTblAreaDim(start, num, whereClause);
		return page;
	}


	public TblAreaDimBo save(TblAreaDimBo bo) throws Exception {
		return tblAreaDimDAO.save(bo);
	}

	public List<TblAreaDimBo> findTblAreaDimByWhere(String whereClause) throws Exception {
		return tblAreaDimDAO.findTblAreaDimByWhere(whereClause);
	}

	public TblAreaDimBo findTblAreaDimById(String areaCode ,String commCode) {
		return tblAreaDimDAO.getById(areaCode,commCode);
	}

	public void removeDim(String areaCode ,String commCode) {
		tblAreaDimDAO.deleteTblAreaDim(areaCode,commCode);
	}

}
