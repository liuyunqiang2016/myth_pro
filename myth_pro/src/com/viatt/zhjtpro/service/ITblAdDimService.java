package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblAdDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblAdDimService {
	public abstract TblAdDimBo findTblAdDimById(String AdId);

	public abstract Page findTblAdDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblAdDimByWhere(String whereClause) throws Exception;

	public abstract void removeAdDim(String AdId);
	
	public abstract TblAdDimBo save(TblAdDimBo bo) throws Exception;
 
}
