package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblLogDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblLogDimDAO {
	public abstract TblLogDimBo getById(String strId);
	
	public abstract TblLogDimBo save(TblLogDimBo bo);

	public abstract List findTblLogDimByWhere(String whereClause) throws Exception;

	public abstract Page findTblLogDim(int start, int num, String whereClause)
	throws Exception;
}
