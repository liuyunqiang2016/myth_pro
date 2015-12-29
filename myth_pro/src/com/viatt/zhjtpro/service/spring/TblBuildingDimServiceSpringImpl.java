package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblBuildingDimBo;
import com.viatt.zhjtpro.bo.VBuildingDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblBuildingDimDAO;
import com.viatt.zhjtpro.service.ITblBuildingDimService;

public class TblBuildingDimServiceSpringImpl implements ITblBuildingDimService {
	private ITblBuildingDimDAO tblBuildingDimDAO;


	public ITblBuildingDimDAO getTblBuildingDimDAO() {
		return tblBuildingDimDAO;
	}

	public void setTblBuildingDimDAO(ITblBuildingDimDAO tblBuildingDimDAO) {
		this.tblBuildingDimDAO = tblBuildingDimDAO;
	}


	public Page findTblBuildingDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblBuildingDimDAO.findTblBuildingDim(start, num, whereClause);
		return page;
	}


	public TblBuildingDimBo save(TblBuildingDimBo bo) throws Exception {
		return tblBuildingDimDAO.save(bo);
	}

	public List<TblBuildingDimBo> findTblBuildingDimByWhere(String whereClause) throws Exception {
		return tblBuildingDimDAO.findTblBuildingDimByWhere(whereClause);
	}

	public TblBuildingDimBo findTblBuildingDimById(String BuildingCode ,String areaCode ,String commCode) {
		return tblBuildingDimDAO.getById(BuildingCode,areaCode,commCode);
	}

	public void removeDim(String BuildingCode ,String areaCode,String commCode) {
		tblBuildingDimDAO.deleteTblBuildingDim(BuildingCode,areaCode,commCode);
	}

}
