package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblOwnerDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblOwnerDimDAO;
import com.viatt.zhjtpro.service.ITblOwnerDimService;

public class TblOwnerDimServiceSpringImpl implements ITblOwnerDimService {
	private ITblOwnerDimDAO tblOwnerDimDAO;


	public ITblOwnerDimDAO getTblOwnerDimDAO() {
		return tblOwnerDimDAO;
	}

	public void setTblOwnerDimDAO(ITblOwnerDimDAO tblOwnerDimDAO) {
		this.tblOwnerDimDAO = tblOwnerDimDAO;
	}

	public Page findTblOwnerDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblOwnerDimDAO.findTblOwnerDim(start, num, whereClause);
		return page;
	}

	public TblOwnerDimBo update(TblOwnerDimBo bo) throws Exception {
		return tblOwnerDimDAO.update(bo);
	}
	
	public TblOwnerDimBo save(TblOwnerDimBo bo) throws Exception {
		return tblOwnerDimDAO.save(bo);
	}

	public List<TblOwnerDimBo> findTblOwnerDimByWhere(String key, String value) throws Exception
	{
		return tblOwnerDimDAO.findTblOwnerDimByWhere(key, value);
	}

	public TblOwnerDimBo findTblOwnerDimById(String ownerCode) {
		return tblOwnerDimDAO.getById(ownerCode);
	}

	public void removeDim(String ownerCode) {
		tblOwnerDimDAO.deleteTblOwnerDim(ownerCode);
	}
	
	public String getMaxId(){
		return tblOwnerDimDAO.getMaxId();
	}

}
