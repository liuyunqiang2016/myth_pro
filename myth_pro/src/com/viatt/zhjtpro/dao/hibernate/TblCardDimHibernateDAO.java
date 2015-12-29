package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblCardDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblCardDimDAO;

public class TblCardDimHibernateDAO extends HibernateDaoSupport implements
		ITblCardDimDAO {

	public TblCardDimBo getById(String strId) {
		return (TblCardDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblCardDimBo",
				strId);
	}

	public Page findTblCardDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblCardDimBo ");
			buf.append(whereClause);
			buf.append("order by card_no");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblCardDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblCardDimBo save(TblCardDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblCardDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblCardDimBo ");
			if(whereClause != null || !"".equals(whereClause))
				buf.append( whereClause );
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList();
		}
	}

	public void deleteTblCardDim(String CardCode) {
		
		this.getHibernateTemplate().delete(getById(CardCode));
//		this.getSession().createQuery("delete from TblCardDimBo where Card_code = ?")
//				.setString(0, TblCardId).executeUpdate();
	}


	public TblCardDimBo getTblCard(TblCardDimBo bo) {
		List list = this.getHibernateTemplate().find("from TblCardDimBo where usr_name = ?", new Object[]{bo.getUsrName()});
		if(list.size() != 0)
			return (TblCardDimBo) list.get(0);
		else 
			return null;
	}

}	
