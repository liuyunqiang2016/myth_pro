package com.viatt.zhjtpro.service;

import java.util.List;

import org.hibernate.HibernateException;

import biz.common.IModel;

import com.viatt.zhjtpro.bo.TblEquDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblEquDimService 
{
	public abstract TblEquDimBo findTblEquDimById(String EquId);

	public abstract Page findTblEquDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List<TblEquDimBo> findTblEquDimByWhere(String whereClause) throws Exception;

	public abstract void removeEquDim(String EquId);
	
	public abstract TblEquDimBo save(TblEquDimBo bo) throws Exception;
 
	public IModel OPEqzt(IModel bo) throws HibernateException;
	
	public IModel OPEqjr(IModel bo) throws HibernateException;
	
	public void OPFpop(IModel bo) throws HibernateException;
}
