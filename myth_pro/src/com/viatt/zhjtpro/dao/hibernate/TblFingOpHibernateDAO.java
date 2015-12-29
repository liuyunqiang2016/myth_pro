package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblFingOpBo;
import com.viatt.zhjtpro.dao.ITblFingOpDAO;

public class TblFingOpHibernateDAO extends HibernateDaoSupport implements
		ITblFingOpDAO {

	public TblFingOpBo getById(String cardNo ,String opType,String equCode) {
		List list = this.getHibernateTemplate().find(
				"from TblFingOpBo where card_no = ? and op_type = ? and equ_code=?",
				new Object[] { cardNo, opType,equCode });
		if (list.size() != 0)
			return (TblFingOpBo) list.get(0);
		else
			return null;
	}

	public TblFingOpBo save(TblFingOpBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblFingOpByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblFingOpBo ");
			if(whereClause != null || !"".equals(whereClause))
				buf.append(whereClause);
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList();
		}
	}

	public void deleteTblFingOp(String cardNo ,String opType,String equCode) {
		
		this.getHibernateTemplate().delete(getById(cardNo,opType,equCode));
	}
	
	public void deleteBo(TblFingOpBo bo){
		this.getHibernateTemplate().delete(bo);
	}

}	
