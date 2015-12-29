package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblMenuDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblMenuDimDAO
{
	public abstract TblMenuDimBo getById(String strId);

	public abstract Page findTblMenuDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblMenuDimBo save(TblMenuDimBo bo);

	public abstract List<TblMenuDimBo> findTblMenuDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblMenuDim(String tblMenuId);
	
}
