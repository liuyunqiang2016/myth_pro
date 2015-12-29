package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblSoftFirmwareDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblSoftFirmwareDimDAO;

public class TblSoftFirmwareDimHibernateDAO extends HibernateDaoSupport implements
		ITblSoftFirmwareDimDAO {

	public TblSoftFirmwareDimBo getById(String strId) {
		return (TblSoftFirmwareDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblSoftFirmwareDimBo",
				strId);
	}

	public Page findTblSoftFirmwareDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblSoftFirmwareDimBo ");
			buf.append(whereClause);
			buf.append("order by soft_name desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblSoftFirmwareDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblSoftFirmwareDimBo save(TblSoftFirmwareDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblSoftFirmwareDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblSoftFirmwareDimBo ");
			if(whereClause != null || !"".equals(whereClause))
				buf.append(whereClause);
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList();
		}
	}

	public void deleteTblSoftFirmwareDim(String TblSoftId) {
		
		this.getHibernateTemplate().delete(getById(TblSoftId));
	}

}	
