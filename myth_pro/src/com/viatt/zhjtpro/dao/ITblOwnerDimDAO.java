package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblOwnerDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblOwnerDimDAO
{
	public abstract TblOwnerDimBo getById(String strId);

	public abstract TblOwnerDimBo getTblOwner(TblOwnerDimBo bo);
	
	public abstract Page findTblOwnerDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblOwnerDimBo update(TblOwnerDimBo bo);
	
	public abstract TblOwnerDimBo save(TblOwnerDimBo bo);

	public abstract List<TblOwnerDimBo> findTblOwnerDimByWhere(String key, String value) throws Exception;

	public abstract void deleteTblOwnerDim(String ownerCode);
	
	public abstract String getMaxId();
	
}
