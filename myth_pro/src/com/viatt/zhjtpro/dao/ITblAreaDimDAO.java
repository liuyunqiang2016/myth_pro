package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblAreaDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblAreaDimDAO
{
	public abstract TblAreaDimBo getById(String areaCode ,String commCode);

	public abstract TblAreaDimBo getTblArea(TblAreaDimBo bo);
	
	public abstract Page findTblAreaDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblAreaDimBo save(TblAreaDimBo bo);

	public abstract List<TblAreaDimBo> findTblAreaDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblAreaDim(String areaCode ,String commCode);
	
}
