package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblBillsDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblBillsDimDAO {
	public abstract TblBillsDimBo getById(String billCode,String itemCode,String roomCode);

	public abstract Page findTblBillsDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblBillsDimBo save(TblBillsDimBo bo);

	public abstract List findTblBillsDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblBillsDim(String billCode,String itemCode,String roomCode);
	
}
