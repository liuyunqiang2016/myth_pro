package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblRoomDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblRoomDimService
{
	public abstract TblRoomDimBo findTblRoomDimById(String BuildingCode,
			String areaCode, String commCode, String unitCode, String roomCode);

	public abstract Page findTblRoomDim(int start, int num, String whereClause)
			throws Exception;

	public abstract String getJsonRoomInfo(String roomCode, String areaCode, String commCode, String unitCode, String buildingCode) ;
	
	public abstract String getJsonOutdoorInfo(String roomCode, String areaCode, String commCode, String unitCode, String buildingCode) ;
			
	public abstract List<TblRoomDimBo> findTblRoomDimByWhere(String whereClause)
			throws Exception;
	
	public abstract List<TblRoomDimBo> findVRoomDimByWhere(String whereClause)
	throws Exception;

	public abstract TblRoomDimBo save(TblRoomDimBo bo) throws Exception;

	public abstract void removeDim(String BuildingCode, String areaCode,
			String commCode, String unitCode, String RoomCode);

}
