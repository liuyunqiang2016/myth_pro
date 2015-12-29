package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblParamDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblParamDimDAO {
	public abstract TblParamDimBo getById(String strId);

	public abstract Page findTblParamDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblParamDimBo save(TblParamDimBo bo);

	public abstract List findTblParamDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblParamDim(String tblParamId);
	
}
