package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblCalDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblCalDimDAO {
	public abstract TblCalDimBo getById(String strId);

	public abstract Page findTblCalDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblCalDimBo save(TblCalDimBo bo);

	public abstract List findTblCalDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblCalDim(String tblCalId);
	
}
