package com.viatt.zhjtpro.service;

import java.util.List;

import com.viatt.zhjtpro.bo.OrgDimBo;
import com.viatt.zhjtpro.common.BusinessException;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.IOrgDimDAO;

public interface IOrgDimService {
	public void setOrgDimDAO(IOrgDimDAO orgDimDao);

	public OrgDimBo findOrgById(String strCode) throws Exception;

	public Page findOrgDim(int start, int num, String whereClause) throws Exception;
	
	public List findByWhereClause(String whereClause) throws Exception;

	public OrgDimBo saveOrgDim(OrgDimBo orgDim) throws BusinessException;

	public void removeOrgDim(String orgCode) throws Exception;

}
