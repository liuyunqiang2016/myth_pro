package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblBuildingDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblBuildingDimDAO
{
	public abstract TblBuildingDimBo getById(String BuildingCode ,String areaCode,String commCode);

	public abstract TblBuildingDimBo getTblBuilding(TblBuildingDimBo bo);
	
	public abstract Page findTblBuildingDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblBuildingDimBo save(TblBuildingDimBo bo);

	public abstract List<TblBuildingDimBo> findTblBuildingDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblBuildingDim(String buildingCode ,String areaCode,String commCode);
	
}
