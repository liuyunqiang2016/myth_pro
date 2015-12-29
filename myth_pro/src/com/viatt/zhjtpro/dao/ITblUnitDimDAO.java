package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblUnitDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblUnitDimDAO
{
	public abstract TblUnitDimBo getById(String BuildingCode ,String areaCode,String commCode,String unitCode);

	public abstract TblUnitDimBo getTblUnit(TblUnitDimBo bo);
	
	public abstract Page findTblUnitDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblUnitDimBo save(TblUnitDimBo bo);

	public abstract List<TblUnitDimBo> findTblUnitDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblUnitDim(String BuildingCode ,String areaCode,String commCode,String unitCode);
	
}
