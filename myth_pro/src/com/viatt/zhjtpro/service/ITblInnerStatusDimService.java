package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblInnerEquOpBo;
import com.viatt.zhjtpro.bo.TblInnerStatusDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblInnerStatusDimService {
	public abstract TblInnerStatusDimBo getById(String innerid,String outerid,String optype);

	public abstract TblInnerStatusDimBo save(TblInnerStatusDimBo bo);

	public abstract List findTblInnerStatusDimByWhere(String whereClause) throws Exception;

	public abstract void delete(String innerid,String outerid,String optype)throws Exception;
	
	public abstract Page findTblInnerStatusDim(int start, int num, String whereClause)
	throws Exception;
	
	public abstract void deleteList(String whereClause)throws Exception;
}
