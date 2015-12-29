package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblLogDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblLogDimService {

	public abstract Page findTblLogDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblLogDimByWhere(String whereClause) throws Exception;

	public abstract TblLogDimBo save(TblLogDimBo bo) throws Exception;
 
}
