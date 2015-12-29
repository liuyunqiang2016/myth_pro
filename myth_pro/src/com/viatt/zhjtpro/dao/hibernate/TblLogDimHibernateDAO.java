package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblLogDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblLogDimDAO;

public class TblLogDimHibernateDAO extends HibernateDaoSupport implements
		ITblLogDimDAO {

	public TblLogDimBo getById(String strId) {
		return (TblLogDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblLogDimBo",
				strId);
	}

	public TblLogDimBo save(TblLogDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblLogDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblLogDimBo ");
			if(whereClause != null || !"".equals(whereClause))
				buf.append(whereClause);
			buf.append(" order by cre_time desc");
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList();
		}
	}

	public Page findTblLogDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblLogDimBo ");
			buf.append(whereClause);
			buf.append("order by cre_time desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblLogDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}


}	
