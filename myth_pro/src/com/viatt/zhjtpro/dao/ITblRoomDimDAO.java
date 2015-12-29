package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblRoomDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblRoomDimDAO
{
	public abstract TblRoomDimBo getById(String roomCode, String areaCode,
			String commCode, String unitCode, String buildingCode);

	public abstract TblRoomDimBo getTblRoom(TblRoomDimBo bo);
	
	public abstract String getJsonRoomInfo(String roomCode, String areaCode, String commCode, String unitCode, String buildingCode);
	
	/**
	 * ??o?
	 * @param roomCode D????������o? 
	 * @param areaCode  ??��?����?������o?
	 * @param commCode  ?��衧???������o?
	 * @param unitCode  |�����?ao?
	 * @param buildingCode
	 * @return
	 */
	public abstract String getJsonOutdoorInfo(String roomCode, String areaCode, String commCode, String unitCode, String buildingCode);

	public abstract Page findTblRoomDim(int start, int num, String whereClause)
			throws Exception;

	public abstract TblRoomDimBo save(TblRoomDimBo bo);

	public abstract List<TblRoomDimBo> findTblRoomDimByWhere(String whereClause)
			throws Exception;
	
	public abstract List<TblRoomDimBo> findVRoomDimByWhere(String whereClause)
	throws Exception;

	public abstract void deleteTblRoomDim(String roomCode, String areaCode,
			String commCode, String unitCode, String buildingCode);

}
