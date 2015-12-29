package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblAdDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblAdDimDAO;

public class TblAdDimHibernateDAO extends HibernateDaoSupport implements
		ITblAdDimDAO {

	public TblAdDimBo getById(String strId) {
		return (TblAdDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblAdDimBo",
				strId);
	}

	public Page findTblAdDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblAdDimBo ");
			buf.append(whereClause);
			buf.append("order by create_date desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblAdDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblAdDimBo save(TblAdDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblAdDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblAdDimBo ");
//			if(whereClause != null || !"".equals(whereClause))
//				buf.append(" where orgCode = '" + whereClause + "'");
			buf.append(" order by Ad_Name");
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList();
		}
	}

	public void deleteTblAdDim(String TblAdId) {
		
		this.getHibernateTemplate().delete(getById(TblAdId));
	}

}	
