package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblItemDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblItemDimDAO {
	public abstract TblItemDimBo getById(String strId);

	public abstract Page findTblItemDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblItemDimBo save(TblItemDimBo bo);

	public abstract List findTblItemDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblItemDim(String tblItemId);
	
}
