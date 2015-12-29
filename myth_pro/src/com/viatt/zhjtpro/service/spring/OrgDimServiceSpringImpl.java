package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.OrgDimBo;
import com.viatt.zhjtpro.common.BusinessException;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.IOrgDimDAO;
import com.viatt.zhjtpro.service.IOrgDimService;

public class OrgDimServiceSpringImpl implements IOrgDimService
{
	private IOrgDimDAO orgDimDAO;

	public void setOrgDimDAO(IOrgDimDAO orgDimDao)
	{
		this.orgDimDAO = orgDimDao;
	}

	public OrgDimBo findOrgById(String strCode) throws Exception
	{
		OrgDimBo orgBo = orgDimDAO.findOrgById(strCode);
		return orgBo;
	}

	public OrgDimBo saveOrgDim(OrgDimBo orgDim) throws BusinessException
	{
		orgDimDAO.saveOrgDim(orgDim);
		return orgDim;
	}


	public Page findOrgDim(int start, int num, String whereClause)
			throws Exception
	{
		Page page = orgDimDAO.findOrgDim(start, num, whereClause);
		return page;
	}
	


	public List findByWhereClause(String whereClause) throws Exception
	{
		return orgDimDAO.findByWhereClause(whereClause);
	}

	public void removeOrgDim(String orgCode) throws Exception
	{
		orgDimDAO.deleteOrgDim(orgCode);
	}

}
