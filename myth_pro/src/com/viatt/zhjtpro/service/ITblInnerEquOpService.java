package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblInnerEquOpBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblInnerEquOpService {
	public abstract TblInnerEquOpBo getById(String innerid,String outerid,String optype);

	public abstract TblInnerEquOpBo save(TblInnerEquOpBo bo);

	public abstract List findTblInnerEquOpByWhere(String whereClause) throws Exception;

	public abstract void delete(String innerid,String outerid,String optype)throws Exception;
}
