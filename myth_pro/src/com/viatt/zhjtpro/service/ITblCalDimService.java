package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblCalDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblCalDimService {
	public abstract TblCalDimBo findTblCalDimById(String CalId);

	public abstract Page findTblCalDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblCalDimByWhere(String whereClause) throws Exception;

	public abstract void removeCalDim(String CalId);
	
	public abstract TblCalDimBo save(TblCalDimBo bo) throws Exception;
 
}
