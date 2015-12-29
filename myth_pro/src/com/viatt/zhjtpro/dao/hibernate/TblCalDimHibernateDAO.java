package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblCalDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblCalDimDAO;

public class TblCalDimHibernateDAO extends HibernateDaoSupport implements
		ITblCalDimDAO {

	public TblCalDimBo getById(String strId) {
		return (TblCalDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblCalDimBo",
				strId);
	}

	public Page findTblCalDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblCalDimBo ");
			buf.append(whereClause);
			buf.append("order by Cal_name desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblCalDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblCalDimBo save(TblCalDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblCalDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblCalDimBo ");
//			if(whereClause != null || !"".equals(whereClause))
//				buf.append(" where orgCode = '" + whereClause + "'");
			buf.append(" order by Cal_Name");
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList();
		}
	}

	public void deleteTblCalDim(String TblCalId) {
		
		this.getHibernateTemplate().delete(getById(TblCalId));
	}

}	
