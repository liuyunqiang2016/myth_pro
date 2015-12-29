package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblInfoStatusDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblInfoStatusDimDAO {
	public abstract TblInfoStatusDimBo getById(String strId);

	public abstract Page findTblInfoStatusDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblInfoStatusDimBo save(TblInfoStatusDimBo bo);

	public abstract List findTblInfoStatusDimByWhere(String whereClause) throws Exception;

	public abstract void deleteList(String whereClause)throws Exception;
}
