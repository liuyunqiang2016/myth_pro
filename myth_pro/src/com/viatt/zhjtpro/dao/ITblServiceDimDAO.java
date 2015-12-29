package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblServiceDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblServiceDimDAO {
	public abstract TblServiceDimBo getById(String strId);

	public abstract Page findTblServiceDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblServiceDimBo save(TblServiceDimBo bo);

	public abstract List findTblServiceDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblServiceDim(String tblServiceId);
	
}
