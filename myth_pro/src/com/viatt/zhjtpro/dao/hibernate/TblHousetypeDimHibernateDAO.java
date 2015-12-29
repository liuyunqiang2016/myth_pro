package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblHousetypeDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblHousetypeDimDAO;

public class TblHousetypeDimHibernateDAO extends HibernateDaoSupport implements
		ITblHousetypeDimDAO {

	public TblHousetypeDimBo getById(String strId) {
		return (TblHousetypeDimBo) getHibernateTemplate().get(
				"com.viatt.zhjtpro.bo.TblHousetypeDimBo", strId);
	}

	public Page findTblHousetypeDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblHousetypeDimBo ");
			buf.append(whereClause);
			buf.append("order by Ht_name");

			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblHousetypeDimBo  "
							+ (whereClause == null
									|| "".equals(whereClause.trim()) ? ""
									: whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:" + e.getMessage());
			return new Page();
		}
	}

	public TblHousetypeDimBo save(TblHousetypeDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List<TblHousetypeDimBo> findTblHousetypeDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblHousetypeDimBo ");
			if (whereClause != null || !"".equals(whereClause))
			{
				buf.append(whereClause);
			}
			buf.append(" order by Ht_Name");
			Query query = getSession().createQuery(buf.toString());
			
			@SuppressWarnings("unchecked")
			List<TblHousetypeDimBo> list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList<TblHousetypeDimBo>();
		}
	}

	public void deleteTblHousetypeDim(String TblHousetypeId) {

		this.getHibernateTemplate().delete(getById(TblHousetypeId));
	}

	public TblHousetypeDimBo getTblHousetype(TblHousetypeDimBo bo) {
		List list = this.getHibernateTemplate().find(
				"from TblHousetypeDimBo where Ht_Name = ?",
				new Object[] { bo.getHtName() });
		if (list.size() != 0)
			return (TblHousetypeDimBo) list.get(0);
		else
			return null;
	}

	public String getMaxId() {
		String id = "1";
		try {
			StringBuffer buf = new StringBuffer(
					"select max(ht_code) ht_code from tbl_housetype_dim ");
			Query query = getSession().createSQLQuery(buf.toString());
			List list = query.list();
			if(list.size()!=0){
				String obj = (String) list.get(0);
				id = Integer.parseInt(obj)+1+"";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}
