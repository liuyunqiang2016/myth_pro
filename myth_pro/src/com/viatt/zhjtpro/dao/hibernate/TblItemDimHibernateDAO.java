package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblItemDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblItemDimDAO;

public class TblItemDimHibernateDAO extends HibernateDaoSupport implements
		ITblItemDimDAO {

	public TblItemDimBo getById(String strId) {
		return (TblItemDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblItemDimBo",
				strId);
	}

	public Page findTblItemDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblItemDimBo ");
			buf.append(whereClause);
			buf.append("order by Item_name desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblItemDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblItemDimBo save(TblItemDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblItemDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblItemDimBo ");
//			if(whereClause != null || !"".equals(whereClause))
//				buf.append(" where orgCode = '" + whereClause + "'");
			buf.append(" order by Item_Name");
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList();
		}
	}

	public void deleteTblItemDim(String TblItemId) {
		
		this.getHibernateTemplate().delete(getById(TblItemId));
	}

}	
