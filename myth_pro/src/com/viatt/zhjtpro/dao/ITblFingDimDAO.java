package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblFingDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblFingDimDAO {
	public abstract TblFingDimBo getById(String strId);

	public abstract TblFingDimBo getTblFing(TblFingDimBo bo);
	
	public abstract Page findTblFingDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblFingDimBo save(TblFingDimBo bo);

	public abstract List findTblFingDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblFingDim(String usrNO);
	
	public abstract List<TblFingDimBo> findTblFingDimByEquCode(String equ_code) ; 
	
}
