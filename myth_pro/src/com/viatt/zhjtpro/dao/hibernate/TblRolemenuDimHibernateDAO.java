package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblRolemenuDimBo;
import com.viatt.zhjtpro.dao.ITblRolemenuDimDAO;

public class TblRolemenuDimHibernateDAO extends HibernateDaoSupport implements
		ITblRolemenuDimDAO {

	public void deleteTblRolemenuDimByMenuCode(String menuCode) {
		try {
			List list = findTblRolemenuDimByWhere(" where menu_code="
					+ menuCode);
			for (int i = 0; i < list.size(); i++) {
				TblRolemenuDimBo bo = (TblRolemenuDimBo) list.get(i);
				getHibernateTemplate().delete(bo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteTblRolemenuDimByRoleCode(String roleCode)
			throws Exception {
		List list = findTblRolemenuDimByWhere(" where role_code=" + roleCode);
		for (int i = 0; i < list.size(); i++) {
			TblRolemenuDimBo bo = (TblRolemenuDimBo) list.get(i);
			getHibernateTemplate().delete(bo);
		}
	}

	public TblRolemenuDimBo save(TblRolemenuDimBo bo) {
		this.getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblRolemenuDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblRolemenuDimBo ");
			if (whereClause != null || !"".equals(whereClause))
				buf.append(whereClause);
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList();
		}
	}

}
