package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblInfoStatusDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblInfoStatusDimDAO;

public class TblInfoStatusDimHibernateDAO extends HibernateDaoSupport implements
		ITblInfoStatusDimDAO {

	public TblInfoStatusDimBo getById(String strId) {
		return (TblInfoStatusDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblInfoStatusDimBo",
				strId);
	}

	public Page findTblInfoStatusDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblInfoStatusDimBo ");
			buf.append(whereClause);
			buf.append("order by info_code desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblInfoStatusDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblInfoStatusDimBo save(TblInfoStatusDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblInfoStatusDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblInfoStatusDimBo ");
			if(whereClause != null || !"".equals(whereClause))
				buf.append(whereClause);
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList();
		}
	}

	public void delete(TblInfoStatusDimBo bo){
		getHibernateTemplate().delete(bo);
	}
	public void deleteList(String whereClause) throws Exception{
		StringBuffer buf = new StringBuffer("from TblInfoStatusDimBo ");
		if(whereClause != null || !"".equals(whereClause))
			buf.append(whereClause);
		Query query = getSession().createQuery(buf.toString());
		List list = query.list();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				TblInfoStatusDimBo info = (TblInfoStatusDimBo)list.get(i);
				delete(info);
			}
		}
	}
}	
