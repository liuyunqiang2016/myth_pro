package com.viatt.zhjtpro.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblContrDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblContrDimDAO;

public class TblContrDimHibernateDAO extends HibernateDaoSupport implements
		ITblContrDimDAO {

	public Page findTblContrDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblContrDimBo ");
			buf.append(whereClause);
			buf.append("order by create_time desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblContrDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}
	
	public TblContrDimBo save(TblContrDimBo bo) throws Exception{
		this.getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

}	
