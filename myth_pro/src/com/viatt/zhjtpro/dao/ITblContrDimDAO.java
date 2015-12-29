package com.viatt.zhjtpro.dao;

import com.viatt.zhjtpro.bo.TblContrDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblContrDimDAO {
	public abstract Page findTblContrDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblContrDimBo save(TblContrDimBo bo) throws Exception;
}
