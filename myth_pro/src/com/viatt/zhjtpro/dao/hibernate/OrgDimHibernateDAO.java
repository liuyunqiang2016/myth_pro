 package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.OrgDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.IOrgDimDAO;

public class OrgDimHibernateDAO extends HibernateDaoSupport implements
		IOrgDimDAO
{

	public OrgDimBo findOrgById(String StrCode)
	{
		return (OrgDimBo) this.getHibernateTemplate().get(
				"com.viatt.zhjtpro.bo.OrgDimBo", StrCode);
	}

	public Page findOrgDim(int start, int num, String whereClause)
	{
		try
		{
			StringBuffer strSQL = new StringBuffer(100);
			strSQL.append("from OrgDimBo org ");
			if (whereClause != null && !"".equals(whereClause))
				strSQL.append(whereClause);
			strSQL.append(" order by org.orgCode");

			Query query = getSession().createQuery(strSQL.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();
			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from OrgDimBo org " + (whereClause == null
									|| "".equals(whereClause.trim()) ? ""
									: whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e)
		{
			return new Page();
		}
	}

	public OrgDimBo saveOrgDim(OrgDimBo orgDim)
	{
		getHibernateTemplate().saveOrUpdate(orgDim);
		return orgDim;
	}
	
	public List findByWhereClause(String whereClause) throws Exception
	{
		try
		{
			StringBuffer buf = new StringBuffer("from OrgDimBo ");
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e)
		{
			return new ArrayList();
		}
	}

	public void deleteOrgDim(String orgCode) throws HibernateException
	{
		this.getHibernateTemplate().delete(findOrgById(orgCode));
	}


}
