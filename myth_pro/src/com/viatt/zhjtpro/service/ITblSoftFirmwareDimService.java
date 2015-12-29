package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblSoftDimBo;
import com.viatt.zhjtpro.bo.TblSoftFirmwareDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblSoftFirmwareDimService {
	public abstract TblSoftFirmwareDimBo findTblSoftFirmwareDimById(String SoftId);

	public abstract Page findTblSoftFirmwareDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblSoftFirmwareDimByWhere(String whereClause) throws Exception;

	public abstract void removeSoftFirmwareDim(String SoftId);
	
	public abstract TblSoftFirmwareDimBo save(TblSoftFirmwareDimBo bo) throws Exception;
 
}
