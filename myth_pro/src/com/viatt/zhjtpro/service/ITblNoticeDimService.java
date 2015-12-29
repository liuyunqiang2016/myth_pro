package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblNoticeDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblNoticeDimService {
	public abstract TblNoticeDimBo findTblNoticeDimById(String NoticeId);

	public abstract Page findTblNoticeDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblNoticeDimByWhere(String whereClause) throws Exception;

	public abstract void removeNoticeDim(String NoticeId);
	
	public abstract TblNoticeDimBo save(TblNoticeDimBo bo) throws Exception;
 
}
