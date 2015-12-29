package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblInfoStatusDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblInfoStatusDimService {
	public abstract TblInfoStatusDimBo findTblInfoStatusDimById(String InfoStatusId);

	public abstract Page findTblInfoStatusDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblInfoStatusDimByWhere(String whereClause) throws Exception;

	public abstract TblInfoStatusDimBo save(TblInfoStatusDimBo bo) throws Exception;
 
	public abstract void deleteList(String whereClause)throws Exception;
}
