package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblSipAcountDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblSipAcountDimService
{
	public abstract TblSipAcountDimBo findTblSipAcountDimById(String acountId);

	public abstract Page findTblSipAcountDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List<TblSipAcountDimBo> findTblSipAcountDimByWhere(String whereClause) throws Exception;

	public abstract void removeSipAcountDim(String UserId);
	
	public abstract TblSipAcountDimBo save(TblSipAcountDimBo bo) throws Exception;
	
	public abstract void updateSipAcountBo(String username, TblSipAcountDimBo bo) throws Exception;
	
	public abstract TblSipAcountDimBo getLoginUserInfo(String username);
	
}
