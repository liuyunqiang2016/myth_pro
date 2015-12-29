package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblBillsDimBo;
import com.viatt.zhjtpro.bo.TblBuildingDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblBillsDimDAO;

public class TblBillsDimHibernateDAO extends HibernateDaoSupport implements
		ITblBillsDimDAO {

	public TblBillsDimBo getById(String billCode,String itemCode,String roomCode) {
		List list = this.getHibernateTemplate().find(
				"from TblBillsDimBo where bill_code = ? and item_Code = ? and room_Code = ?",
				new Object[] { billCode,itemCode, roomCode });
		if (list.size() != 0)
			return (TblBillsDimBo) list.get(0);
		else
			return null;
	}

	public Page findTblBillsDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblBillsDimBo ");
			buf.append(whereClause);
			buf.append("order by bill_code desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblBillsDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblBillsDimBo save(TblBillsDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblBillsDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblBillsDimBo ");
//			if(whereClause != null || !"".equals(whereClause))
//				buf.append(" where orgCode = '" + whereClause + "'");
			buf.append(" order by bill_code");
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList();
		}
	}

	public void deleteTblBillsDim(String billCode,String itemCode,String roomCode) {
		
		this.getHibernateTemplate().delete(getById(billCode, itemCode, roomCode));
	}

}	
