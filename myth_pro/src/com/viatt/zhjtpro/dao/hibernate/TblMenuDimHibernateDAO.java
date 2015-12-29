package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblMenuDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblMenuDimDAO;

public class TblMenuDimHibernateDAO extends HibernateDaoSupport implements
		ITblMenuDimDAO {

	public TblMenuDimBo getById(String strId) {
		return (TblMenuDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblMenuDimBo",
				strId);
	}

	public Page findTblMenuDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblMenuDimBo ");
			buf.append(whereClause);
			buf.append("order by Menu_name desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			
			@SuppressWarnings("unchecked")
			List<TblMenuDimBo> list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblMenuDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblMenuDimBo save(TblMenuDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List<TblMenuDimBo> findTblMenuDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblMenuDimBo ");
			if(StringUtils.isNotEmpty(whereClause))
			{
				buf.append(whereClause);
			}
			buf.append(" order by Menu_Name");
			Query query = getSession().createQuery(buf.toString());
			
			@SuppressWarnings("unchecked")
			List<TblMenuDimBo> list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList<TblMenuDimBo>();
		}
	}

	public void deleteTblMenuDim(String TblMenuId) {
		
		this.getHibernateTemplate().delete(getById(TblMenuId));
	}

}	
