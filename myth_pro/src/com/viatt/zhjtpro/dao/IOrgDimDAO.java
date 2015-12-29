package com.viatt.zhjtpro.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.viatt.zhjtpro.bo.OrgDimBo;
import com.viatt.zhjtpro.common.Page;

public interface IOrgDimDAO {
	public abstract OrgDimBo findOrgById(String strCode);
	
	public abstract Page findOrgDim(int start, int num, String whereClause) throws Exception;
	

	public abstract OrgDimBo saveOrgDim(OrgDimBo orgDim);

	public abstract List findByWhereClause(String whereClause) throws Exception;

	public abstract void deleteOrgDim(String orgCode) throws HibernateException;

}
