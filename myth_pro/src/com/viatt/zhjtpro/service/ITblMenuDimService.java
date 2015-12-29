package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.TblMenuDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblMenuDimService {
	public abstract TblMenuDimBo findTblMenuDimById(String MenuId);

	public abstract Page findTblMenuDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List<TblMenuDimBo> findTblMenuDimByWhere(String whereClause) throws Exception;

	public abstract void removeMenuDim(String MenuId);
	
	public abstract TblMenuDimBo save(TblMenuDimBo bo) throws Exception;
	
	public abstract String getMenuString(String url)throws Exception;
 
}
