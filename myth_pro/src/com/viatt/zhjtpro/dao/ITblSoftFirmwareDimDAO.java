package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblSoftFirmwareDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblSoftFirmwareDimDAO {
	public abstract TblSoftFirmwareDimBo getById(String strId);

	public abstract Page findTblSoftFirmwareDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblSoftFirmwareDimBo save(TblSoftFirmwareDimBo bo);

	public abstract List findTblSoftFirmwareDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblSoftFirmwareDim(String tblSoftId);
	
}
