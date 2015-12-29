package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblRoleDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblRoleDimDAO {
	public abstract TblRoleDimBo getById(String strId);

	public abstract Page findTblRoleDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblRoleDimBo save(TblRoleDimBo bo);

	public abstract List<TblRoleDimBo> findTblRoleDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblRoleDim(String tblRoleId);
	
}
