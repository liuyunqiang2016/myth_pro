package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblCardDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblCardDimService {
	public abstract TblCardDimBo findTblCardDimById(String CardCode);


	public abstract Page findTblCardDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblCardDimByWhere(String whereClause) throws Exception;

	public abstract TblCardDimBo save(TblCardDimBo bo) throws Exception;

	public abstract void removeCardDim(String CardCode);

}
