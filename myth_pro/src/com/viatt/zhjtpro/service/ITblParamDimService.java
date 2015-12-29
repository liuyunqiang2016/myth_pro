package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblParamDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblParamDimService {
	public abstract TblParamDimBo findTblParamDimById(String ParamId);

	public abstract Page findTblParamDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblParamDimByWhere(String whereClause) throws Exception;

	public abstract void removeParamDim(String ParamId);
	
	public abstract TblParamDimBo save(TblParamDimBo bo) throws Exception;
 
}
