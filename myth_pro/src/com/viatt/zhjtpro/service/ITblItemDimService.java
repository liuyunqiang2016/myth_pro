package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblItemDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblItemDimService {
	public abstract TblItemDimBo findTblItemDimById(String ItemId);

	public abstract Page findTblItemDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblItemDimByWhere(String whereClause) throws Exception;

	public abstract void removeItemDim(String ItemId);
	
	public abstract TblItemDimBo save(TblItemDimBo bo) throws Exception;
 
}
