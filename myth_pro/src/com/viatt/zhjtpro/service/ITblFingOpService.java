package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblFingOpBo;

public interface ITblFingOpService {

	public abstract List findTblFingOpByWhere(String whereClause) throws Exception;

	public abstract TblFingOpBo save(TblFingOpBo bo) throws Exception;

	public abstract void removeDim(String cardNo ,String opType) throws Exception;

}
