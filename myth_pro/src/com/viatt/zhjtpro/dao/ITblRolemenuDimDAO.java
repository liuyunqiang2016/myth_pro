package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblRolemenuDimBo;
public interface ITblRolemenuDimDAO {
	public abstract TblRolemenuDimBo save(TblRolemenuDimBo bo);

	public abstract void deleteTblRolemenuDimByRoleCode(String roleCode) throws Exception;
	
	public abstract void deleteTblRolemenuDimByMenuCode(String menuCode);
	
	public abstract List findTblRolemenuDimByWhere(String whereClause) throws Exception;
	
}
