package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblPropertyDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblPropertyDimService 
{
	public abstract TblPropertyDimBo findTblPropertyDimById(String propertyId);

	public abstract Page findTblPropertyDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List<TblPropertyDimBo> findTblPropertyDimByWhere(String whereClause) throws Exception;

	public abstract void removePropertyDim(String propertyId);
	
	public abstract TblPropertyDimBo save(TblPropertyDimBo bo) throws Exception;
	
	
}
