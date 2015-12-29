package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblUserDimService
{
	public abstract TblUserDimBo findTblUserDimById(String UserId);

	public abstract Page findTblUserDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List<TblUserDimBo> findTblUserDimByWhere(String whereClause) throws Exception;

	public abstract void removeUserDim(String UserId);
	
	public abstract TblUserDimBo save(TblUserDimBo bo) throws Exception;
	
	public abstract void updatePassword(String logName, TblUserDimBo bo) throws Exception;
	
	public abstract boolean login(String loginName, String loginPwd);
	
	public abstract boolean findPassword(String emailAddress, String telNum);
	
	public abstract TblUserDimBo getLoginUserInfo(String loginName);
	
}
