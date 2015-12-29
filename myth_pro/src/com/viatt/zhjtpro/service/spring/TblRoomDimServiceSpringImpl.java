package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblRoomDimBo;
import com.viatt.zhjtpro.bo.VRoomDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblRoomDimDAO;
import com.viatt.zhjtpro.service.ITblRoomDimService;

public class TblRoomDimServiceSpringImpl implements ITblRoomDimService {
	private ITblRoomDimDAO tblRoomDimDAO;

	public ITblRoomDimDAO getTblRoomDimDAO() {
		return tblRoomDimDAO;
	}

	public void setTblRoomDimDAO(ITblRoomDimDAO tblRoomDimDAO) {
		this.tblRoomDimDAO = tblRoomDimDAO;
	}

	public Page findTblRoomDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblRoomDimDAO.findTblRoomDim(start, num, whereClause);
		return page;
	}

	public TblRoomDimBo save(TblRoomDimBo bo) throws Exception {
		return tblRoomDimDAO.save(bo);
	}

	public List<TblRoomDimBo> findTblRoomDimByWhere(String whereClause) throws Exception {
		return tblRoomDimDAO.findTblRoomDimByWhere(whereClause);
	}
	
	public List<TblRoomDimBo> findVRoomDimByWhere(String whereClause) throws Exception {
		return tblRoomDimDAO.findVRoomDimByWhere(whereClause);
	}

	public TblRoomDimBo findTblRoomDimById(String buildingCode, String areaCode,
			String commCode, String unitCode, String roomCode) {
		return tblRoomDimDAO.getById(roomCode, areaCode, commCode, unitCode,
				buildingCode);
	}
	
	public String getJsonRoomInfo(String roomCode, String areaCode, String commCode, String unitCode, String buildingCode){
		return tblRoomDimDAO.getJsonRoomInfo(roomCode, areaCode, commCode, unitCode, buildingCode) ;
	}

	public void removeDim(String buildingCode, String areaCode,
			String commCode, String unitCode, String roomCode) {
		tblRoomDimDAO.deleteTblRoomDim(roomCode, areaCode, commCode, unitCode,
				buildingCode);
	}

	public String getJsonOutdoorInfo(String roomCode, String areaCode,
			String commCode, String unitCode, String buildingCode) {
		return tblRoomDimDAO.getJsonOutdoorInfo(roomCode, areaCode, commCode, unitCode, buildingCode);
	}

}
