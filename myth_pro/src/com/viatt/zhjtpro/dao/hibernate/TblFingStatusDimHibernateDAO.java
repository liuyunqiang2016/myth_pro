package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblFingStatusDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblFingStatusDimDAO;

/*
 * Ö¸ÎÆ·¢ËÍ×´Ì¬DAO
 */
public class TblFingStatusDimHibernateDAO extends HibernateDaoSupport implements
		ITblFingStatusDimDAO {

	public TblFingStatusDimBo getById(String strId) {
		return (TblFingStatusDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblFingStatusDimBo",
				strId);
	}

	public Page findTblFingStatusDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblFingStatusDimBo ");
			buf.append(whereClause);
			buf.append("order by id");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblFingStatusDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblFingStatusDimBo save(TblFingStatusDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblFingStatusDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblFingStatusDimBo ");
			if(whereClause != null || !"".equals(whereClause))
				buf.append(whereClause);
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList();
		}
	}

	public void delete(TblFingStatusDimBo bo){
		getHibernateTemplate().delete(bo);
	}
	public void deleteList(String whereClause) throws Exception{
		StringBuffer buf = new StringBuffer("from TblFingStatusDimBo ");
		if(whereClause != null || !"".equals(whereClause))
			buf.append(whereClause);
		Query query = getSession().createQuery(buf.toString());
		List list = query.list();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				TblFingStatusDimBo info = (TblFingStatusDimBo)list.get(i);
				delete(info);
			}
		}
	}
}	
