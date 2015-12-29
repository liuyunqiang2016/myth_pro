package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblVisitorDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblVisitorDimService {
	public abstract Page findTblVisitorDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblVisitorDimByWhere(String whereClause) throws Exception;

	public abstract TblVisitorDimBo save(TblVisitorDimBo bo) throws Exception;
 
}
