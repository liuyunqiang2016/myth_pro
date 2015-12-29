package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblFingOpBo;
public interface ITblFingOpDAO {
	public abstract TblFingOpBo getById(String cardNo,String opType,String equCode);

	public abstract TblFingOpBo save(TblFingOpBo bo);

	public abstract List findTblFingOpByWhere(String whereClause) throws Exception;

	public abstract void deleteTblFingOp(String cardNo,String opType,String equCode);
	
	public abstract void deleteBo(TblFingOpBo bo);
}
