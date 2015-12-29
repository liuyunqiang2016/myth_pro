package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblEquDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblEquDimDAO;

public class TblEquDimHibernateDAO extends HibernateDaoSupport implements
		ITblEquDimDAO {

	public TblEquDimBo getById(String strId) {
		return (TblEquDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblEquDimBo",
				strId);
	}

	public Page findTblEquDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblEquDimBo ");
			buf.append(whereClause);
			buf.append("order by equ_name desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblEquDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblEquDimBo save(TblEquDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List<TblEquDimBo> findTblEquDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblEquDimBo ");
			if(whereClause != null || !"".equals(whereClause))
				buf.append(whereClause);
			buf.append(" order by Equ_Name");
			Query query = getSession().createQuery(buf.toString());
			
			@SuppressWarnings("unchecked")
			List<TblEquDimBo> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<TblEquDimBo>();
		}
	}

	public void deleteTblEquDim(String TblEquId) {
		
		this.getHibernateTemplate().delete(getById(TblEquId));
	}

}	
