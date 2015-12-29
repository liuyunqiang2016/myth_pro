package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblCommDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblCommDimDAO
{
	public abstract TblCommDimBo getById(String strId);

	public abstract TblCommDimBo getTblComm(TblCommDimBo bo);
	
	public abstract Page findTblCommDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblCommDimBo save(TblCommDimBo bo);

	public abstract List<TblCommDimBo> findTblCommDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblCommDim(String tblCommId);
	
}
