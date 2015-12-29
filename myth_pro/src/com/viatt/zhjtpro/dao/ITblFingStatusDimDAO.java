package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblFingStatusDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblFingStatusDimDAO {
	public abstract TblFingStatusDimBo getById(String strId);

	public abstract Page findTblFingStatusDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblFingStatusDimBo save(TblFingStatusDimBo bo);

	public abstract List findTblFingStatusDimByWhere(String whereClause) throws Exception;

	public abstract void deleteList(String whereClause)throws Exception;
}
