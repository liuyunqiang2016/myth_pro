package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblFingStatusDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblFingStatusDimService {
	public abstract TblFingStatusDimBo findTblFingStatusDimById(String fingStatusId);

	public abstract Page findTblFingStatusDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblFingStatusDimByWhere(String whereClause) throws Exception;

	public abstract TblFingStatusDimBo save(TblFingStatusDimBo bo) throws Exception;
 
	public abstract void deleteList(String whereClause)throws Exception;
}
