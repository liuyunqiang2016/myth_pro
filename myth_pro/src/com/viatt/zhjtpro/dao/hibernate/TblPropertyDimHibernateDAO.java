/**
 * 
 */
package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblPropertyDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblPropertyDimDAO;


public class TblPropertyDimHibernateDAO extends HibernateDaoSupport implements
    ITblPropertyDimDAO {

	public TblPropertyDimBo getById(String strId) {
		return (TblPropertyDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblPropertyDimBo",
				strId);
	}

	public Page findTblPropertyDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblPropertyDimBo ");
			buf.append(whereClause);
			buf.append("order by property_id desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblPropertyDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblPropertyDimBo save(TblPropertyDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List<TblPropertyDimBo> findTblPropertyDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblPropertyDimBo ");
			if(whereClause != null || !"".equals(whereClause))
			{
				buf.append(whereClause);
			}
			buf.append(" order by property_id");
			Query query = getSession().createQuery(buf.toString());
			
			@SuppressWarnings("unchecked")
			List<TblPropertyDimBo> list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList<TblPropertyDimBo>();
		}
	}

	public void deleteTblPropertyDim(String tblPropertyId) {
		
		this.getHibernateTemplate().delete(getById(tblPropertyId));
	}

}
