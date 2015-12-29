package com.viatt.zhjtpro.service;

import com.viatt.zhjtpro.bo.TblContrDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblContrDimService {
	public abstract Page findTblContrDim(int start, int num, String whereClause)
			throws Exception;

	public abstract TblContrDimBo save(TblContrDimBo bo) throws Exception;
}
