package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblCommDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblCommDimService
{
	public abstract TblCommDimBo findTblCommDimById(String commId);

	public abstract Page findTblCommDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List<TblCommDimBo> findTblCommDimByWhere(String whereClause) throws Exception;

	public abstract TblCommDimBo save(TblCommDimBo bo) throws Exception;

	public abstract void removeGrgDim(String commId);

}
