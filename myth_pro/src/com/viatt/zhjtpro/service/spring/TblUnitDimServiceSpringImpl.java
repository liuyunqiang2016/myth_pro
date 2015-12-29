package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblUnitDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblUnitDimDAO;
import com.viatt.zhjtpro.service.ITblUnitDimService;

public class TblUnitDimServiceSpringImpl implements ITblUnitDimService
{
	private ITblUnitDimDAO tblUnitDimDAO;


	public ITblUnitDimDAO getTblUnitDimDAO() {
		return tblUnitDimDAO;
	}

	public void setTblUnitDimDAO(ITblUnitDimDAO tblUnitDimDAO) {
		this.tblUnitDimDAO = tblUnitDimDAO;
	}


	public Page findTblUnitDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblUnitDimDAO.findTblUnitDim(start, num, whereClause);
		return page;
	}


	public TblUnitDimBo save(TblUnitDimBo bo) throws Exception {
		return tblUnitDimDAO.save(bo);
	}

	public List<TblUnitDimBo> findTblUnitDimByWhere(String whereClause) throws Exception {
		return tblUnitDimDAO.findTblUnitDimByWhere(whereClause);
	}

	public TblUnitDimBo findTblUnitDimById(String buildingCode ,String areaCode ,String commCode,String UnitCode) {
		return tblUnitDimDAO.getById(buildingCode,areaCode,commCode,UnitCode);
	}

	public void removeDim(String buildingCode ,String areaCode,String commCode,String UnitCode) {
		tblUnitDimDAO.deleteTblUnitDim(buildingCode,areaCode,commCode,UnitCode);
	}

}
