package com.viatt.zhjtpro.service;

import java.util.List;

import org.hibernate.HibernateException;

import biz.common.IModel;

import com.viatt.zhjtpro.bo.TblServiceDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblServiceDimService {
	public abstract TblServiceDimBo findTblServiceDimById(String ServiceId);

	public abstract Page findTblServiceDim(int start, int num, String whereClause)
			throws Exception;

	public abstract List findTblServiceDimByWhere(String whereClause) throws Exception;

	public abstract void removeServiceDim(String ServiceId);
	
	public abstract TblServiceDimBo save(TblServiceDimBo bo) throws Exception;
	
	public abstract IModel OPFwxx(IModel bo) throws HibernateException;
 
}
