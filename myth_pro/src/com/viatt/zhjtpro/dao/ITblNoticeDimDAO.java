package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblNoticeDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblNoticeDimDAO {
	public abstract TblNoticeDimBo getById(String strId);

	public abstract Page findTblNoticeDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblNoticeDimBo save(TblNoticeDimBo bo);

	public abstract List findTblNoticeDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblNoticeDim(String tblNoticeId);
	
}
