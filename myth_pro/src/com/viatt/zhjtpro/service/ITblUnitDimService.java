package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblUnitDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblUnitDimService
{
	public abstract TblUnitDimBo findTblUnitDimById(String BuildingCode,
			String areaCode, String commCode, String UnitCode);

	public abstract Page findTblUnitDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List<TblUnitDimBo> findTblUnitDimByWhere(String whereClause)
			throws Exception;

	public abstract TblUnitDimBo save(TblUnitDimBo bo) throws Exception;

	public abstract void removeDim(String BuildingCode, String areaCode,
			String commCode, String UnitCode);

}
