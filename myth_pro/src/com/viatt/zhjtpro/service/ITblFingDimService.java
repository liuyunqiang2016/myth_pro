package com.viatt.zhjtpro.service;

import java.util.List;

import biz.common.IModel;

import com.viatt.zhjtpro.bo.TblFingDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblFingDimService {
	public abstract TblFingDimBo findTblFingDimById(String FingCode);


	public abstract Page findTblFingDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblFingDimByWhere(String whereClause) throws Exception;

	public abstract TblFingDimBo save(TblFingDimBo bo) throws Exception;

	public abstract void removeFingDim(String usrNo);

	public void OPEfds(IModel bo)  ;
	
	public abstract List<TblFingDimBo> findTblFingDimByEquCode(String equ_code) ; 
}
