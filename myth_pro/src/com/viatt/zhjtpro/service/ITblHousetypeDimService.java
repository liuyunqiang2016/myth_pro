package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblHousetypeDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblHousetypeDimService {
	public abstract TblHousetypeDimBo findTblHousetypeDimById(String HousetypeId);


	public abstract Page findTblHousetypeDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblHousetypeDimByWhere(String whereClause) throws Exception;

	public abstract TblHousetypeDimBo save(TblHousetypeDimBo bo) throws Exception;

	public abstract void removeDim(String HousetypeId);

	public abstract String getMaxId();
}
