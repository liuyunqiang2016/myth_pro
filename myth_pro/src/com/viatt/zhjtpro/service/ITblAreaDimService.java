package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblAreaDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblAreaDimService
{
	public abstract TblAreaDimBo findTblAreaDimById(String areaCode ,String commCode);

	public abstract Page findTblAreaDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List<TblAreaDimBo> findTblAreaDimByWhere(String whereClause) throws Exception;

	public abstract TblAreaDimBo save(TblAreaDimBo bo) throws Exception;

	public abstract void removeDim(String areaCode ,String commCode);

}
