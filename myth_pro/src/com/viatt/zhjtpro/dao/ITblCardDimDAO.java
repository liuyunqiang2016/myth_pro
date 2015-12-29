package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblCardDimBo;
import com.viatt.zhjtpro.common.Page;
public interface ITblCardDimDAO {
	public abstract TblCardDimBo getById(String strId);

	public abstract TblCardDimBo getTblCard(TblCardDimBo bo);
	
	public abstract Page findTblCardDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblCardDimBo save(TblCardDimBo bo);

	public abstract List findTblCardDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblCardDim(String CardCode);
	
}
