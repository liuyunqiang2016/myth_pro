package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblBillsDimBo;
import com.viatt.zhjtpro.bo.TblInnerEquOpBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblInnerEquOpDAO;

public class TblInnerEquOpHibernateDAO extends HibernateDaoSupport implements
		ITblInnerEquOpDAO {

	public TblInnerEquOpBo getById(String innerid,String outerid,String optype) {
		List list = this.getHibernateTemplate().find(
				"from TblInnerEquOpBo where innerid = ? and outerid = ? and optype = ?",
				new Object[] { innerid,outerid, optype });
		if (list.size() != 0)
			return (TblInnerEquOpBo) list.get(0);
		else
			return null;
	}

	public TblInnerEquOpBo save(TblInnerEquOpBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblInnerEquOpByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblInnerEquOpBo ");
			if(whereClause != null || !"".equals(whereClause))
				buf.append(whereClause);
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList();
		}
	}

	public void delete(String innerid,String outerid,String optype){
		this.getHibernateTemplate().delete(getById(innerid, outerid, optype));
	}
}	
