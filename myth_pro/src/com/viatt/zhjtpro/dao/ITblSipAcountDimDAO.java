/**
 * 
 */
package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblSipAcountDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblSipAcountDimDAO {

	public abstract TblSipAcountDimBo getById(String acountId);

	public abstract Page findTblSipAcountDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblSipAcountDimBo save(TblSipAcountDimBo bo);
	
	public abstract void updateSipAcountBo(String username, TblSipAcountDimBo bo) throws Exception;
	
	public abstract List<TblSipAcountDimBo> findTblSipAcountDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblSipAcountDim(String acountId);
	
	public abstract TblSipAcountDimBo getSipAcountByLogName(String username);
	
}
