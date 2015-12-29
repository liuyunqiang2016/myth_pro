package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblCommDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblCommDimDAO;

public class TblCommDimHibernateDAO extends HibernateDaoSupport implements
		ITblCommDimDAO {

	public TblCommDimBo getById(String strId) {
		return (TblCommDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblCommDimBo",
				strId);
	}

	public Page findTblCommDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblCommDimBo ");
			buf.append(whereClause);
			buf.append("order by comm_name");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblCommDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblCommDimBo save(TblCommDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List<TblCommDimBo> findTblCommDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblCommDimBo ");
			if(StringUtils.isNotEmpty(whereClause))
			{
				buf.append(whereClause);
			}
			buf.append(" order by comm_name");
			Query query = getSession().createQuery(buf.toString());
			
			@SuppressWarnings("unchecked")
			List<TblCommDimBo> list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList<TblCommDimBo>();
		}
	}

	public void deleteTblCommDim(String TblCommId) {
		
		this.getHibernateTemplate().delete(getById(TblCommId));
//		this.getSession().createQuery("delete from TblCommDimBo where comm_code = ?")
//				.setString(0, TblCommId).executeUpdate();
	}


	public TblCommDimBo getTblComm(TblCommDimBo bo) {
		List list = this.getHibernateTemplate().find("from TblCommDimBo where comm_Name = ?", new Object[]{bo.getCommName()});
		if(list.size() != 0)
			return (TblCommDimBo) list.get(0);
		else 
			return null;
	}

}	
