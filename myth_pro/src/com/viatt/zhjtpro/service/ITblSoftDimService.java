package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblSoftDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblSoftDimService {
	public abstract TblSoftDimBo findTblSoftDimById(String SoftId);

	public abstract Page findTblSoftDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblSoftDimByWhere(String whereClause) throws Exception;

	public abstract void removeSoftDim(String SoftId);
	
	public abstract TblSoftDimBo save(TblSoftDimBo bo) throws Exception;
 
}
