package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblBillsDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblBillsDimService {
	public abstract TblBillsDimBo findTblBillsDimById(String billCode,String itemCode,String roomCode);

	public abstract Page findTblBillsDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblBillsDimByWhere(String whereClause) throws Exception;

	public abstract void removeBillsDim(String billCode,String itemCode,String roomCode);
	
	public abstract TblBillsDimBo save(TblBillsDimBo bo) throws Exception;
 
}
