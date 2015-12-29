/**
 * 
 */
package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblPropertyDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblPropertyDimDAO {

	public abstract TblPropertyDimBo getById(String strId);

	public abstract Page findTblPropertyDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblPropertyDimBo save(TblPropertyDimBo bo);

	public abstract List<TblPropertyDimBo> findTblPropertyDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblPropertyDim(String tblPropertyId);
	

}
