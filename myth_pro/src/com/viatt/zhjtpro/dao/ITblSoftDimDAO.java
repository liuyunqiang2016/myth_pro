package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblSoftDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblSoftDimDAO {
	public abstract TblSoftDimBo getById(String strId);

	public abstract Page findTblSoftDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblSoftDimBo save(TblSoftDimBo bo);

	public abstract List findTblSoftDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblSoftDim(String tblSoftId);
	
}
