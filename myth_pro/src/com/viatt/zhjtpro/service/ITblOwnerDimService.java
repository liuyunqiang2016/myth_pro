package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblOwnerDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblOwnerDimService 
{
	
	public abstract TblOwnerDimBo findTblOwnerDimById(String ownerCode);

	public abstract Page findTblOwnerDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List<TblOwnerDimBo> findTblOwnerDimByWhere(String key, String value) throws Exception;

	public abstract TblOwnerDimBo update(TblOwnerDimBo bo) throws Exception;
	
	public abstract TblOwnerDimBo save(TblOwnerDimBo bo) throws Exception;

	public abstract void removeDim(String ownerCode);
	
	public abstract String getMaxId();

}
