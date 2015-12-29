package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblParamDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblParamDimDAO;

public class TblParamDimHibernateDAO extends HibernateDaoSupport implements
		ITblParamDimDAO {

	public TblParamDimBo getById(String strId) {
		return (TblParamDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblParamDimBo",
				strId);
	}

	public Page findTblParamDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblParamDimBo ");
			buf.append(whereClause);
			buf.append("order by Param_name desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblParamDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblParamDimBo save(TblParamDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblParamDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblParamDimBo ");
//			if(whereClause != null || !"".equals(whereClause))
//				buf.append(" where orgCode = '" + whereClause + "'");
			buf.append(" order by Param_Name");
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList();
		}
	}

	public void deleteTblParamDim(String TblParamId) {
		
		this.getHibernateTemplate().delete(getById(TblParamId));
	}

}	
