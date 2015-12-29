/**
 * 
 */
package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblSipAcountDimBo;
import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblSipAcountDimDAO;

public class TblSipAcountDimHibernateDAO extends HibernateDaoSupport implements
	ITblSipAcountDimDAO {

	public TblSipAcountDimBo getById(String acountId) {
		return (TblSipAcountDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblSipAcountDimBo",
				acountId);
	}

	public Page findTblSipAcountDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblSipAcountDimBo ");
			buf.append(whereClause);
			buf.append("order by username desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			
			@SuppressWarnings("unchecked")
			List<TblUserDimBo> list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblSipAcountDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblSipAcountDimBo save(TblSipAcountDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List<TblSipAcountDimBo> findTblSipAcountDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblSipAcountDimBo ");
			if(whereClause != null || !"".equals(whereClause))
			{
				buf.append(whereClause);
			}
			buf.append(" order by username");
			Query query = getSession().createQuery(buf.toString());
			
			@SuppressWarnings("unchecked")
			List<TblSipAcountDimBo> list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList<TblSipAcountDimBo>();
		}
	}

	public void deleteTblSipAcountDim(String acountId) {
		
		this.getHibernateTemplate().delete(getById(acountId));
	}

	public TblSipAcountDimBo getSipAcountByLogName(String username) {
		@SuppressWarnings("unchecked")
		List<TblSipAcountDimBo> list = this.getSession().createQuery("from TblSipAcountDimBo where username = ?").setString(0, username).list();
		if(!list.isEmpty()){
			return (TblSipAcountDimBo) list.get(0);
		}
		return null;
	}

	@Override
	public void updateSipAcountBo(String username, TblSipAcountDimBo bo) throws Exception {
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().update(username, bo);
		this.getHibernateTemplate().flush();
	}

}
