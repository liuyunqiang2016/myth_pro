package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblBuildingDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblBuildingDimService
{
	public abstract TblBuildingDimBo findTblBuildingDimById(String BuildingCode ,String areaCode ,String commCode);

	public abstract Page findTblBuildingDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List<TblBuildingDimBo> findTblBuildingDimByWhere(String whereClause) throws Exception;

	public abstract TblBuildingDimBo save(TblBuildingDimBo bo) throws Exception;

	public abstract void removeDim(String BuildingCode ,String areaCode,String commCode);

}
