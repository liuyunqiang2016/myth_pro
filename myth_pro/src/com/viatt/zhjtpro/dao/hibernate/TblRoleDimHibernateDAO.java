package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblRoleDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblRoleDimDAO;

public class TblRoleDimHibernateDAO extends HibernateDaoSupport implements
		ITblRoleDimDAO {

	public TblRoleDimBo getById(String strId) {
		return (TblRoleDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblRoleDimBo",
				strId);
	}

	public Page findTblRoleDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblRoleDimBo ");
			buf.append(whereClause);
			buf.append("order by Role_name desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			
			@SuppressWarnings("unchecked")
			List<TblRoleDimBo> list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblRoleDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblRoleDimBo save(TblRoleDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List<TblRoleDimBo> findTblRoleDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblRoleDimBo ");
			if(whereClause != null || !"".equals(whereClause))
				buf.append(whereClause);
			buf.append(" order by Role_Name");
			Query query = getSession().createQuery(buf.toString());
			
			@SuppressWarnings("unchecked")
			List<TblRoleDimBo> list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList<TblRoleDimBo>();
		}
	}

	public void deleteTblRoleDim(String TblRoleId) {
		
		this.getHibernateTemplate().delete(getById(TblRoleId));
	}

}	
