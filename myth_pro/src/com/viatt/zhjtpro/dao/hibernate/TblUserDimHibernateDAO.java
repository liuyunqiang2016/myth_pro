/**
 * 
 */
package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblUserDimDAO;


public class TblUserDimHibernateDAO extends HibernateDaoSupport implements
		ITblUserDimDAO {

	public TblUserDimBo getById(String strId) {
		return (TblUserDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblUserDimBo",
				strId);
	}

	public Page findTblUserDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblUserDimBo ");
			buf.append(whereClause);
			buf.append("order by User_name desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			
			@SuppressWarnings("unchecked")
			List<TblUserDimBo> list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblUserDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblUserDimBo save(TblUserDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List<TblUserDimBo> findTblUserDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblUserDimBo ");
			if(whereClause != null || !"".equals(whereClause))
			{
				buf.append(whereClause);
			}
			buf.append(" order by User_Name");
			Query query = getSession().createQuery(buf.toString());
			
			@SuppressWarnings("unchecked")
			List<TblUserDimBo> list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList<TblUserDimBo>();
		}
	}

	public void deleteTblUserDim(String TblUserId) {
		
		this.getHibernateTemplate().delete(getById(TblUserId));
	}

	public TblUserDimBo getUserByLogName(String logName) {
		@SuppressWarnings("unchecked")
		List<TblUserDimBo> list = this.getSession().createQuery("from TblUserDimBo where log_Name = ?").setString(0, logName).list();
		if(!list.isEmpty()){
			return (TblUserDimBo) list.get(0);
		}
		return null;
	}
	
	public boolean findPassword(String emailAddress, String telNum) {
		@SuppressWarnings("unchecked")
		List<TblUserDimBo> list = this.getSession().createQuery("from TblUserDimBo where email_address = ? and tel_num = ?").setString(0, emailAddress).setString(1, telNum).list();
		if(!list.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public void updatePassword(String logName, TblUserDimBo bo) throws Exception {
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().update(logName, bo);
		this.getHibernateTemplate().flush();
	}

}
