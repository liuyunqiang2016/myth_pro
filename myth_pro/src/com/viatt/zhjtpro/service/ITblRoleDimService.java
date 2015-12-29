package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblRoleDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblRoleDimService {
	public abstract TblRoleDimBo findTblRoleDimById(String RoleId);

	public abstract Page findTblRoleDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblRoleDimByWhere(String whereClause) throws Exception;

	public abstract void removeRoleDim(String RoleId);
	
	public abstract TblRoleDimBo save(TblRoleDimBo bo) throws Exception;
 
}
