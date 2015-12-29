package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblHousetypeDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblHousetypeDimDAO {
	public abstract TblHousetypeDimBo getById(String strId);

	public abstract TblHousetypeDimBo getTblHousetype(TblHousetypeDimBo bo);
	
	public abstract Page findTblHousetypeDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblHousetypeDimBo save(TblHousetypeDimBo bo);

	public abstract List findTblHousetypeDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblHousetypeDim(String tblHousetypeId);
	
	public abstract String getMaxId();
	
}
