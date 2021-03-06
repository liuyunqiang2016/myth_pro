package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblNoticeDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblNoticeDimDAO;

public class TblNoticeDimHibernateDAO extends HibernateDaoSupport implements
		ITblNoticeDimDAO {

	public TblNoticeDimBo getById(String strId) {
		return (TblNoticeDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblNoticeDimBo",
				strId);
	}

	public Page findTblNoticeDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblNoticeDimBo ");
			buf.append(whereClause);
			buf.append("order by create_time desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblNoticeDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblNoticeDimBo save(TblNoticeDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblNoticeDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblNoticeDimBo ");
			if(whereClause != null || !"".equals(whereClause))
				buf.append(whereClause);
			buf.append(" order by create_time desc");
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public void deleteTblNoticeDim(String TblNoticeId) {
		
		this.getHibernateTemplate().delete(getById(TblNoticeId));
	}

}	
