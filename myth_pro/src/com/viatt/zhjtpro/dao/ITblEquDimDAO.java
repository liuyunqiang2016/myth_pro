package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblEquDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblEquDimDAO
{
	public abstract TblEquDimBo getById(String strId);

	public abstract Page findTblEquDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblEquDimBo save(TblEquDimBo bo);

	public abstract List<TblEquDimBo> findTblEquDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblEquDim(String tblEquId);
	
}
