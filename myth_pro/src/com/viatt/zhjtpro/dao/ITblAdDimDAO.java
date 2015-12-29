package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblAdDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblAdDimDAO {
	public abstract TblAdDimBo getById(String strId);

	public abstract Page findTblAdDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblAdDimBo save(TblAdDimBo bo);

	public abstract List findTblAdDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblAdDim(String tblAdId);
	
}
